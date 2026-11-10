package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(serializable = true)
public class EmptyImmutableListMultimap extends ImmutableListMultimap<Object, Object> {
    public static final EmptyImmutableListMultimap INSTANCE = new EmptyImmutableListMultimap();
    public static final long serialVersionUID = 0;

    public EmptyImmutableListMultimap() {
        super(ImmutableMap.of(), 0);
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
