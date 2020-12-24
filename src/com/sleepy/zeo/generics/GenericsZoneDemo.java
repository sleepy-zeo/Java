package com.sleepy.zeo.generics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GenericsZoneDemo {

    static class Manager<T extends Number> {

        void print(T t) {
            System.out.println("print manager: " + t);
        }
    }

    static class Company<T extends Comparator<T>> {

        void print(T t) {
            System.out.println("print company: " + t);
        }
    }

    static class IntegerComparator implements Comparator<IntegerComparator>{

        @Override
        public int compare(IntegerComparator o1, IntegerComparator o2) {
            return 0;
        }
    }

    /*

    java泛型

public class Configurer<T> {

	void init(T builder);

	void configure(T builder);

}


public interface Configurer<B extends SecurityBuilder> {

	void init(B builder);

	void configure(B builder);

}


T

?

T extends SecurityBuilder
T super SecurityBuilder

? extends SecurityBuilder
? super SecurityBuilder

     */



    public static void main(String[] args) {

        Manager manager1 = new Manager();
        manager1.print(12);

        Manager<Integer> manager2 = new Manager<>();
        manager2.print(12);

        List<String> list = new ArrayList<>();
        list.add("steven");
        list.add("sleepy");


        Company<IntegerComparator> company2 = new Company<IntegerComparator>();

    }
}
