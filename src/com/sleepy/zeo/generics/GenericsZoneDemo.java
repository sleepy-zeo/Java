package com.sleepy.zeo.generics;

public class GenericsZoneDemo {

    // ==================== TYPE 1. 简单的泛型边界 ====================
    // 限制类型T必须是Number的子类，否则报错
    static class Manager<N extends Number> {

        void print(N n) {
            System.out.println("print manager: " + n);
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
    // ==================== TYPE 1. 简单的泛型边界 ====================

    // ==================== TYPE 2. 复杂的泛型边界 ====================
    interface Configurer<P> {
        void init(P data);
        void config(P data);
    }

    static class SecurityConfigurer<P extends Password> implements Configurer<P> {

        @Override
        public void init(P data) {
            System.out.println("init data");
            // TODO: 将T限制为T extends XX而不是默认的T extends Object
            data.encodedPassword = "&&" + data.originPassword + "@#$%";
        }

        @Override
        public void config(P data) {
            System.out.println("config data");
            // TODO: 将T限制为T extends XX而不是默认的T extends Object
            data.configured = true;
        }
    }

    static class Controller<P extends Password, C extends Configurer<P>> {

        private P data;
        private C configurer;

        public Controller(P data, C configurer) {
            this.data = data;
            this.configurer = configurer;
        }

        void start() {
            configurer.init(data);
            configurer.config(data);

            System.out.println(data);
        }
    }
    // ==================== TYPE 2. 复杂的泛型边界 ====================

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
