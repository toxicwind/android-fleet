package com.hisense.uifac.util;

/* JADX INFO: loaded from: classes.dex */
public interface IRootMenuUiListener {
    void notifyZDMicTestResult(boolean z);

    void setMicTestResult(String str);

    void setMicTestResultToLog(String str, boolean z);

    void showToast(String str);
}
