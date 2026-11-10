package com.hisense.support.tv;

/* JADX INFO: loaded from: classes2.dex */
public interface IFactoryCleanAppsListener {
    void notifyCompleted();

    void notifyError(String str, int i);

    void notifyProgress(int i);
}
