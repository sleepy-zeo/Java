package com.sleepy.zeo.lifecycle.demo;

import com.sleepy.zeo.lifecycle.Lifecycle;
import com.sleepy.zeo.lifecycle.LifecycleEvent;
import com.sleepy.zeo.lifecycle.LifecycleListener;

public class Application {

    public static void main(String[] args) throws InterruptedException {

        Light leftLight = new Light("LLight");
        Light rightLight = new Light("RLight");

        Engine engine = new Engine();

        Car car = new Car();
        car.setLeftLight(leftLight);
        car.setRightLight(rightLight);
        car.setEngine(engine);

        // 这里我们监听汽车的启动和停止
        car.addLifecycleListener(new LifecycleListener() {
            @Override
            public void lifecycleEvent(LifecycleEvent event) {
                if (LifecycleEvent.TYPE_BEFORE_START.equals(event.getType())) {
                    System.out.println("start to boot...");
                    Lifecycle carLifecycle = event.getLifecycle();
                    if (carLifecycle instanceof Car) {
                        System.out.println(((Car) carLifecycle).getLeftLight().getName());
                    }
                }
                if (LifecycleEvent.TYPE_AFTER_START.equals(event.getType())) {
                    System.out.println("boot successful");
                }
                if (LifecycleEvent.TYPE_BEFORE_STOP.equals(event.getType())) {
                    System.out.println("try to stop...");
                }
                if (LifecycleEvent.TYPE_AFTER_STOP.equals(event.getType())) {
                    System.out.println("stop successfully");
                }

            }
        });

        //启动汽车
        car.run();

        System.out.println("\n\n\n");

        //模拟让桑塔拉跑3秒
        Thread.sleep(3000);

        //关闭汽车
        car.shutdown();

    }

}
