package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableMultimap;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public interface ServiceManagerBridge {
    ImmutableMultimap<Service.State, Service> servicesByState();
}
