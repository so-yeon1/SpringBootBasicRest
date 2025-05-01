package com.mycom.myapp.basic;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// assert___ 메소드 통해 판단. (같다, 다르다, null, not null ...)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Assert_1 {

    @Test
    @Order(1)
    void testAssertEquals() {
//        assertEquals(1,2, "1==1");
        int lagacyNum = LegacySystem.getNum();
        int testNum = TestSystem.getNum();
        assertEquals(lagacyNum, testNum, "lagacyNum과 testNum 비교");
    }

    @Test
    @Order(2)
    void testAssertNotEquals() {
        int lagacyNum = 3;
        int testNum = 2;
        assertNotEquals(lagacyNum, testNum, "lagacyNum과 testNum 비교");
    }

    @Test
    @Order(3)
    void testAssertEqualsObject() {
        // MyClass 에 equals(), hashcode() 재정의하지 않으면 실패, 재정의 후 성공
        MyClass mc = new MyClass();
        MyClass mc2 = new MyClass();
        assertEquals(mc, mc2, "mc와 mc2 equals 비교");
    }

    @Test
    @Order(4)
    void testAssertTrue() {
        MyClass mc = new MyClass();
        assertTrue(mc.getResult(), "mc.getResult()의 결과가 true여야 한다.");
    }

    @Test
    @Order(5)
    void testAssertFalse() {
        MyClass mc = new MyClass();
        assertFalse(mc.getResult(), "mc.getResult()의 결과가 false여야 한다.");
    }

    @Test
    @Order(6)
    void testAssertNull() {
        MyClass mc = new MyClass();
        assertNull(mc.getString(), "mc.getString()의 결과가 null 이어야 한다.");
    }

    @Test
    @Order(7)
    void testAssertNotNull() {
        MyClass mc = new MyClass();
        assertNotNull(mc.getString(), "mc.getString()의 결과가 null 이 아니어야 한다.");
    }
}
