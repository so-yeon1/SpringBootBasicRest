package com.mycom.myapp.basic;

import java.util.Objects;

public class MyClass {
    int n = 10;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MyClass myClass = (MyClass) o;
        return n == myClass.n;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(n);
    }


    public boolean getResult(){
        return false;
    }

    public String getString(){
        return "null";
    }

    public int getStringLength(String str){
        return str.length();
    }
}
