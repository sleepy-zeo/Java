package com.sleepy.zeo.generics;

public class GenericsClassDemo {

    public abstract static class Father<T1, T2> {

        public abstract void printAge(T1 age);
        public abstract void printName(T2 name);
    }

    static class C1<T1, T2, A> extends Father<T1, T2> {

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

    static class C2<T2> extends Father<Integer, T2> {

        @Override
        public void printAge(Integer age) {
            System.out.println("C2 age:" + age);
        }

        @Override
        public void printName(T2 name) {
            System.out.println("C2 name:" + name);
        }
    }

    static class C3 extends Father<Integer, String> {

        @Override
        public void printAge(Integer age) {
            System.out.println("C3 age:" + age);
        }

        @Override
        public void printName(String name) {
            System.out.println("C3 name:" + name);
        }
    }

    // 等价于Father<Object, Object>
    static class C4 extends Father {

        @Override
        public void printAge(Object age) {
            System.out.println("C4 age:" + age);
        }

        @Override
        public void printName(Object name) {
            System.out.println("C4 name:" + name);
        }
    }

    public static void main(String[] args) {
        C1<Integer, String, Long> c1 = new C1<>();
        c1.printAge(12);
        c1.printName("steven");
        c1.printPhone(15313967539L);

        C4 c4 = new C4();
        c4.printAge("age");
        c4.printName("name");

        Father<Integer,String> father = new C1<Integer, String,Long>();
        father.printAge(12);
        father.printName("steven");
        ((C1) father).printPhone(15313967539L);
        ((C1) father).printPhone("hhh");
    }

}
