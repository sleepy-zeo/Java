package com.sleepy.zeo.generics;

// 通配符
//
// T用于定义阶段
// ?用于使用阶段
public class GenericsUncertainDemo {

    interface Wrapper<T> {
        void print(T t);
    }

    static class WrapperImpl<T> implements Wrapper<T> {

        @Override
        public void print(T t) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {

        // 实际的类型可以是任何类型，相当于 Wrapper<? extends Object> unknownWrapper
        Wrapper<?> unknownWrapper;
        // 实际类型是Number或其子类，于是不能传入number类型的值，因为number不能强制转化为其子类的类型
        Wrapper<? extends Number> numberWrapper;
        // 实际类型是String或其父类，于是可以传入string类型的值，因为不管实际类型是啥，都可以由子类的String强制转化成
        Wrapper<? super String> objectWrapper;

        unknownWrapper = new WrapperImpl<Integer>();
        unknownWrapper.print(null);

        Number number = 12;
        numberWrapper = new WrapperImpl<>();
        numberWrapper.print(null);

        objectWrapper = new WrapperImpl<>();
        objectWrapper.print("abcd");
    }
}
