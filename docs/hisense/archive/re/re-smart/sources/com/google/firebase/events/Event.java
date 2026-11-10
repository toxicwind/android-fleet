package com.google.firebase.events;

/* JADX INFO: loaded from: classes.dex */
public class Event<T> {
    public final T payload;
    public final Class<T> type;

    public Event(Class<T> cls, T t) {
        if (cls == null) {
            throw new NullPointerException();
        }
        this.type = cls;
        if (t == null) {
            throw new NullPointerException();
        }
        this.payload = t;
    }

    public T getPayload() {
        return this.payload;
    }

    public Class<T> getType() {
        return this.type;
    }

    public String toString() {
        return String.format("Event{type: %s, payload: %s}", this.type, this.payload);
    }
}
