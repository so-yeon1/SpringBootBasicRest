package com.mycom.myapp.basic;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// assert___ 메소드 통해 판단. (같다, 다르다, null, not null ...)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Assert_2 {

    @Test
    @Order(1)
    void testAssertThrows() {
    MyClass mc = new MyClass();
//    String str = "hello";
    String str = null;
    // (비교하려는 예외, 실행할 코드) ** 실행할 코드를 람다식으로 해야 함. 실행코드 호출을 직접하면 안됨.
    assertThrows(
            NullPointerException.class,
            () -> mc.getStringLength(str),
            "mc.getStringLength() 는 NullPointerException을 발생시켜야 한다.");
    }



    int m1() {return 1;}
    boolean m2() {return true;}
    String m3() {return "hi";}
    @Test
    @Order(2)
    void testAsserAll() {
        // 하나의 단위로 묶어서 테스트
        assertAll(
                "묶음 테스트",
                () -> assertEquals(1,m1()),
                () -> assertTrue(m2()),
                () -> assertNotNull(m3())
                );
    }



    int[] expectedArray = {1,2,3};
    int[] actualArray = {1,2,3};
    @Test
    @Order(3)
    void testAssertArrayEquals() {
        // 배열 비교. (assertArrayNotEquals는 없음)
        assertArrayEquals(expectedArray, actualArray, "두 배열은 같아야 한다.");
    }


    List<String> expectedLsit = Arrays.asList("aaa","bbb","ccc");
    List<String> actualLsit = Arrays.asList("aaa","bbb","ccc");
    @Test
    @Order(4)
    void testAssertIterableEquals() {
        // 컬렉션 비교
        assertIterableEquals(expectedLsit, actualLsit, "두 컬렉션은 같아야 한다.");
    }



    List<MyClass> expectedList2 = Arrays.asList(new MyClass(), new MyClass(), new MyClass());
    List<MyClass> actualList2 = Arrays.asList(new MyClass(), new MyClass(), new MyClass());
    @Test
    @Order(5)
    void testAssertIterableEquals2() {
        // MyClass의 equals(), hashcode가 없으면 테스트 실패.
        assertIterableEquals(expectedList2, actualList2, "두 컬렉션은 같아야 한다.");
    }


    @Test
    @Order(6)
    void testAssertSame() {
        String str1 = "JUnit";
        String str2 = str1;
        String str3 = "JUnit";

        assertSame(str1, str3, "두 변수는 같은 객체를 가리킨다.");
    }


    @Test
    @Order(7)
    void testAssertNotSame() {
        String str1 = "JUnit";
        String str2 = new String("JUnit");
        String str3 = "JUnit";

        assertNotSame(str1, str2, "두 변수는 다른 객체를 가리킨다.");
    }


    void myFunction() {
        try{
            Thread.sleep(2200); // 0.5초
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @Order(8)
    void testAssertTimeout() {
        assertTimeout(
                Duration.ofSeconds(1L),
                () -> {
                    myFunction();
                },
                "myFunctoin은 0.5 초 이내로 수행되어야 한다."
                );
    }

}
