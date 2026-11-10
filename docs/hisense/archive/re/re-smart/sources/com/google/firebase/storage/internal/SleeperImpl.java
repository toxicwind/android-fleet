package com.google.firebase.storage.internal;

/* JADX INFO: loaded from: classes.dex */
public class SleeperImpl implements Sleeper {
    @Override // com.google.firebase.storage.internal.Sleeper
    public void sleep(int i) throws InterruptedException {
        Thread.sleep(i);
    }
}
