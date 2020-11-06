package com.sleepy.zeo;

public final class LifecycleSupport {

    private Lifecycle lifecycle;
    private LifecycleListener[] listeners;

    public LifecycleSupport(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
        listeners = new LifecycleListener[0];
    }

    public LifecycleListener[] getLifecycleListeners() {
        return listeners;
    }

    public void addLifecycleListener(LifecycleListener listener) {
        synchronized (listeners) {
            LifecycleListener[] results = new LifecycleListener[listeners.length + 1];
            for (int i = 0; i < listeners.length; ++i) {
                results[i] = listeners[i];
            }
            results[listeners.length] = listener;
            listeners = results;
        }
    }

    public void removeLifecycleListener(LifecycleListener listener) {
        synchronized (listeners) {
            int index = -1;
            for (int i = 0; i < listeners.length; ++i) {
                if (listeners[i] == listener) {
                    index = i;
                    break;
                }
            }
            if (index < 0) {
                return;
            }
            LifecycleListener[] results = new LifecycleListener[listeners.length - 1];
            int j = 0;
            for (int i = 0; i < listeners.length; ++i) {
                if (i == index) {
                    continue;
                }
                results[j++] = listeners[i];
            }
            listeners = results;
        }
    }

    public void fireLifecycleEvent(String type, Object data) {
        LifecycleEvent event = new LifecycleEvent(lifecycle, type, data);//使用lifecycle作为参数
        LifecycleListener[] interested;
        synchronized (listeners) {
            interested = listeners.clone();
        }
        for (LifecycleListener lifecycleListener : interested) {
            lifecycleListener.lifecycleEvent(event);
        }
    }
}
