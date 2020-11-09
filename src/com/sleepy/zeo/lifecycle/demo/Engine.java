package com.sleepy.zeo.lifecycle.demo;

import com.sleepy.zeo.lifecycle.Lifecycle;
import com.sleepy.zeo.lifecycle.LifecycleEvent;
import com.sleepy.zeo.lifecycle.LifecycleListener;
import com.sleepy.zeo.lifecycle.LifecycleSupport;

public class Engine implements Lifecycle {

    private LifecycleSupport lifecycleSupport = new LifecycleSupport(this);

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
        System.out.println("engine starting...");
        fireLifecycleEvent(LifecycleEvent.TYPE_AFTER_START, null);
    }

    @Override
    public void stop() {
        fireLifecycleEvent(LifecycleEvent.TYPE_BEFORE_STOP, null);
        System.out.println("engine stopping...");
        fireLifecycleEvent(LifecycleEvent.TYPE_AFTER_STOP, null);
    }
}
