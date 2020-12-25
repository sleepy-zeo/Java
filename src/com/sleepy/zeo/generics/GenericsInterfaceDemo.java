package com.sleepy.zeo.generics;

public class GenericsInterfaceDemo {

    public interface Father<T1, T2> {
        void printAge(T1 age);
        void printName(T2 name);
    }

    static class C1<T1, T2, A> implements Father<T1, T2> {

        @Override
        public void printAge(T1 age) {
            System.out.println("C1 age:" + age);
        }

        @Override
        public void printName(T2 name) {
            System.out.println("C1 name:" + name);
        }

        public void printPhone(A phone) {
            System.out.println("C1 phone:" + phone);
        }
    }

    static class C2<T2> implements Father<Integer, T2> {

        @Override
        public void printAge(Integer age) {
            System.out.println("C2 age:" + age);
        }

        @Override
        public void printName(T2 name) {
            System.out.println("C2 name:" + name);
        }
    }

    static class C3 implements Father<Integer, String> {

        @Override
        public void printAge(Integer age) {
            System.out.println("C3 age:" + age);
        }

        @Override
        public void printName(String name) {
            System.out.println("C3 name:" + name);
        }
    }

    // 等价于
    // static class C4 implements Father<Object, Object>
    static class C4 implements Father {

        @Override
        public void printAge(Object age) {
            System.out.println("C4 age:" + age);
        }

        @Override
        public void printName(Object name) {
            System.out.println("C4 name:" + name);
        }
    }
}
