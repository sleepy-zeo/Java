package com.sleepy.zeo.lifecycle.demo;

import com.sleepy.zeo.lifecycle.Lifecycle;
import com.sleepy.zeo.lifecycle.LifecycleEvent;
import com.sleepy.zeo.lifecycle.LifecycleListener;
import com.sleepy.zeo.lifecycle.LifecycleSupport;

public class Car implements Lifecycle {

    private Light leftLight; //左大灯
    private Light rightLight; //右大灯

    private Engine engine; //引擎

    private LifecycleSupport lifecycleSupport = new LifecycleSupport(this);

    public void run() {
        start();
        System.out.println("car is running...");
    }

    public void shutdown() {
        stop();
        System.out.println("car is shutting down...");
    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        lifecycleSupport.addLifecycleListener(listener);
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        lifecycleSupport.removeLifecycleListener(listener);
    }

    @Override
    public void fireLifecycleEvent(String type, Object data) {
        lifecycleSupport.fireLifecycleEvent(type, data);
    }

    @Override
    public LifecycleListener[] getLifecycleListeners() {
        return lifecycleSupport.getLifecycleListeners();
    }

    @Override
    public void start() {
        fireLifecycleEvent(LifecycleEvent.TYPE_BEFORE_START, null);
        //先初始化其他组件
        if (engine != null && engine instanceof Lifecycle) {
            ((Lifecycle) engine).start();
        }
        if (leftLight != null && leftLight instanceof Lifecycle) {
            ((Lifecycle) leftLight).start();
        }
        if (rightLight != null && rightLight instanceof Lifecycle) {
            ((Lifecycle) rightLight).start();
        }

        System.out.println("car is starting...");
        fireLifecycleEvent(LifecycleEvent.TYPE_AFTER_START, null);
    }

    @Override
    public void stop() {
        //先停止其他组件
        if (engine != null && engine instanceof Lifecycle) {
            ((Lifecycle) engine).stop();
        }
        if (leftLight != null && leftLight instanceof Lifecycle) {
            ((Lifecycle) leftLight).stop();
        }
        if (rightLight != null && rightLight instanceof Lifecycle) {
            ((Lifecycle) rightLight).stop();
        }

        fireLifecycleEvent(LifecycleEvent.TYPE_BEFORE_STOP, null);
        System.out.println("car is stopping...");
        fireLifecycleEvent(LifecycleEvent.TYPE_AFTER_STOP, null);
    }

    public Light getLeftLight() {
        return leftLight;
    }

    public void setLeftLight(Light leftLight) {
        this.leftLight = leftLight;
    }

    public Light getRightLight() {
        return rightLight;
    }

    public void setRightLight(Light rightLight) {
        this.rightLight = rightLight;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

}