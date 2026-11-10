package com.ju.lib.datalayer.database.annotation;

import com.ju.lib.datalayer.database.enums.Strategy;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.RUNTIME)
public @interface Conflict {
    Strategy value();
}
