package com.google.common.eventbus;

import androidx.core.app.NotificationCompat;
import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.firebase.analytics.FirebaseAnalytics;

/* JADX INFO: loaded from: classes.dex */
@Beta
public class DeadEvent {
    public final Object event;
    public final Object source;

    public DeadEvent(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException();
        }
        this.source = obj;
        if (obj2 == null) {
            throw new NullPointerException();
        }
        this.event = obj2;
    }

    public Object getEvent() {
        return this.event;
    }

    public Object getSource() {
        return this.source;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(FirebaseAnalytics.Param.SOURCE, this.source).add(NotificationCompat.CATEGORY_EVENT, this.event).toString();
    }
}
