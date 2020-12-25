package com.sleepy.zeo.generics;

import java.util.List;

public class GenericsZoneDemo {

    // TYPE 1. 简单的泛型边界
    // 限制类型T必须是Number的子类，否则报错
    static class Manager<T extends Number> {

        void print(T t) {
            System.out.println("print manager: " + t);
        }
    }

    static class Password {

        String originPassword;
        String encodedPassword;
        boolean configured;

        @Override
        public String toString() {
            return "Password[" +
                    "originPassword='" + originPassword + '\'' +
                    ", encodedPassword='" + encodedPassword + '\'' +
                    ", configured=" + configured +
                    ']';
        }
    }

    // 对类型T的init和config
    interface Configurer<T> {
        void init(T data);
        void config(T data);
    }

    // TYPE 2. 复杂的泛型边界
    // Comparator<D>
    //      D类型数据的相关配置
    // C extends Comparator<D>
    //      C具有'D类型数据的相关配置'的功能的类
    static class Controller<D extends Password, C extends Configurer<D>> {

        private D data;
        private C configurer;

        public Controller(D data, C configurer) {
            this.data = data;
            this.configurer = configurer;
        }

        void start() {
            configurer.init(data);
            configurer.config(data);

            System.out.println(data);
        }
    }

    static class SecurityConfigurer<T extends Password> implements Configurer<T> {

        @Override
        public void init(T data) {
            System.out.println("init data");
            // TODO: 将T限制为T extends XX而不是默认的T extends Object
            data.encodedPassword = "&&" + data.originPassword + "@#$%";
        }

        @Override
        public void config(T data) {
            System.out.println("config data");
            // TODO: 将T限制为T extends XX而不是默认的T extends Object
            data.configured = true;
        }
    }

    public static void main(String[] args) {

        Manager manager1 = new Manager();
        manager1.print(12);

        Manager<Integer> manager2 = new Manager<>();
        manager2.print(12);

        SecurityConfigurer<Password> securityConfigurer = new SecurityConfigurer<>();
        Controller<Password, SecurityConfigurer<Password>> controller = new Controller<>(new Password(), securityConfigurer);
        controller.start();

    }
}
