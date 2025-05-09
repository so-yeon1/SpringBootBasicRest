package com.mycom.myapp.jwt;

import com.mycom.myapp.config.MyUserDetailsService;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

// jwt 생성, 검증...(관리자같은 느낌)
@Component
@RequiredArgsConstructor
@Getter
public class JwtUtil {

    @Value("${myapp.jwt.secret}")  // application.properties에 설정한 값을 secretKeyStr에 주입
    private String secretKeyStr;

    private SecretKey secretKey;
    private final long tokenValidDuration = 1000L * 60 * 60 * 24; // 토큰 유효시간: 1일

//    private final MyUserDetailsService myUserDetailsService;

    @PostConstruct // bean 생성 후 자동실행
    public void init() {
        System.out.println(secretKeyStr); // application.properties에 있는 내용.
        secretKey = new SecretKeySpec(
                secretKeyStr.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm()); // HS256 암호화 알고리즘 적용한 key 생성
        System.out.println(secretKey);
    }

    // jwt 생성
    // jwt에 담을 내용: username (subject), role
    public String createToken(String username, List<String> roles) {
        // 발급일자, 만료일자
        Date now = new Date();

        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(now) // 발급일자
                .expiration(new Date(now.getTime() + tokenValidDuration)) // 기한
                .signWith(secretKey,Jwts.SIG.HS256)
                .compact();
    }

    // UserDetailsService를 통해 사용자 UserDetails 객체를 얻고
    // 이를 통해 UsernamePasswordAuthenticationToken (Authenticaiton을 상속받은것) 객체 만들어 리턴.
    // loaduserByUsername: db 갔다옴.
    // 유효성 검증을 아래 메소들르 통해서 DB를 통한 검증을 진행하는 건 token 발급 기간이 길면 발급시점의 UserDetails와 현재 UserDetails가 다를 수 있다는 점 강조.
    // 반대로, 클라이언트가 접속할 때마다 DB에 access해야함 <= 부담.
//    public Authentication getAuthentication(String token) {
//        UserDetails userDetails = myUserDetailsService.loadUserByUsername(this.getUsernameFromToken(token));
//        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "", userDetails.getAuthorities());
//    }

    // jwt로부터 username 추출
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token).getPayload()
                .getSubject(); // username (subject) 추출.
    }

    // request 로부터 token 획득
    // client가 header에 "X-AUTH-TOKEN".
    public String getTokenFromHeader(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // jwt 유효성 검증.
    // 여기선 일단 만료일자만 검증.
    public boolean validateToken(String token) {
        return ! Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token).getPayload()
                .getExpiration().before(new Date()); // 일자가 만료되었는지 확인
    }
}
