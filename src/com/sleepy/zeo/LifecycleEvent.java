package com.sleepy.zeo;

public class LifecycleEvent {

    public static final String TYPE_BEFORE_START = "before_start";
    public static final String TYPE_AFTER_START = "after_start";
    public static final String TYPE_BEFORE_STOP = "before_stop";
    public static final String TYPE_AFTER_STOP = "after_stop";

    private Lifecycle lifecycle;
    private String type;
    private Object data;

    public LifecycleEvent(Lifecycle lifecycle, String type, Object data) {
        this.lifecycle = lifecycle;
        this.type = type;
        this.data = data;
    }

    public Lifecycle getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;

    }
}
