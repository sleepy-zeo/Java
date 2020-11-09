package com.sleepy.zeo.pool;

public class Message {
    private static final int MAX_POOL_SIZE = 64;
    private static Message sPool;
    private static int sPoolSize = 0;
    private static final Object sPoolLock = new Object();

    private String type;
    private Object data;
    private Message next;

    private Message() {
    }

    private static Message obtain() {
        synchronized (sPoolLock) {
            if (sPool != null) {
                Message m = sPool;
                sPool = m.next;
                m.next = null;
                sPoolSize--;
                return m;
            }
        }
        return new Message();
    }

    public static Message obtain(String type, Object data) {
        Message req = obtain();
        req.type = type;
        req.data = data;
        return req;
    }

    public void recycle() {
        data = null;
        type = null;

        synchronized (sPoolLock) {
            if (sPoolSize < MAX_POOL_SIZE) {
                next = sPool;
                sPool = this;
                sPoolSize++;
            }
        }
    }
}