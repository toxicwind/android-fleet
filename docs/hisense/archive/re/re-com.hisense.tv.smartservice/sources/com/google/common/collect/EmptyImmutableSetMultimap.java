package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(serializable = true)
public class EmptyImmutableSetMultimap extends ImmutableSetMultimap<Object, Object> {
    public static final EmptyImmutableSetMultimap INSTANCE = new EmptyImmutableSetMultimap();
    public static final long serialVersionUID = 0;

    public EmptyImmutableSetMultimap() {
        super(ImmutableMap.of(), 0, null);
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
