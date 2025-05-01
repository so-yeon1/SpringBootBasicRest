package com.mycom.myapp.basic;

import org.junit.jupiter.api.*;

// 테스트하려는 내용을 메소드로 만들고 메소드에 @Test 추가
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Basic {

    @Test
    @Order(5)
    @DisplayName("test1 메소드를 테스트합니다.")
    void test1(){
        System.out.println("test1()");
    }
    @Test
    @Order(1)
    void test2(){
        System.out.println("test2()");
    }

    @Test
    @Order(3)
    void test3(){
        System.out.println("test3()");
    }

    @Test
    @Order(4)
    @DisplayName("RuntimeException 예외 발생")
    void test4(){
        System.out.println("test4()");
        String s = null;
        s.length();
    }

    // @BeforeAll, @AfterAll 은 static 으로 만들어야 함.
    // 테스트 전 static으로 호출되고, Test_Basic 객체 생성 후 호출되는 UI에는 보이지 않는다.
    // 테스트를 붙이지 않았기 때문에 테스트모듈 아님. 준비, 마무리하는 역할
    // 전체 테스트 전 리소스 확인, 테스트 데이터 생성...
    @BeforeAll
    @DisplayName("전체 테스트 메소드 수행 전 한 번 실행")
    static void beforeAll(){
        System.out.println("before()");
    }

    // 전체 테스트 전 리소스 종료, 테스트 데이터 정리...
    @AfterAll
    @DisplayName("전체 테스트 메소드 수행 전 한 번 실행")
    static void afterAll(){
        System.out.println("after()");
    }

    @BeforeEach
    @DisplayName("전체 테스트 메소드 수행 전 매번 실행")
    void beforeEach(){
        System.out.println("beforeEach()");
    }

    @AfterEach
    @DisplayName("전체 테스트 메소드 수행 후 매번 실행")
    void afterEach(){
        System.out.println("afterEach()");
    }
}
