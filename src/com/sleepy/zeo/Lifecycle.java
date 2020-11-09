package com.sleepy.zeo;

/**
 * 对象是具有生命周期的，从初始化、运行、回收等 会经历几个不同的阶段，于是我们就可以抽象出来Lifecycle
 *
 * 但是由于很多对象类型都是具有生命周期的，如果每个需要生命周期的类都去实现一个Lifecycle接口实现，就会出现很多冗余代码，于是有两种方案：
 * 1. 写一个基类实现Lifecycle接口，然后其他类都继承该基类， 但是由于java单继承限制，不太合适
 * 2. LifecycleSupport
 */
public interface Lifecycle {

    void addLifecycleListener(LifecycleListener listener);

    void removeLifecycleListener(LifecycleListener listener);

    void fireLifecycleEvent(String type, Object data);

    LifecycleListener[] getLifecycleListeners();

    void start();

    void stop();
}
