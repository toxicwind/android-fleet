package com.ju.lib.datacommunication.network.http.core.signature;

/* JADX INFO: loaded from: classes2.dex */
public final class NoneSignature implements ISignature {
    @Override // com.ju.lib.datacommunication.network.http.core.signature.ISignature
    public final String verifySigner(String str) {
        return str;
    }

    @Override // com.ju.lib.datacommunication.network.http.core.signature.ISignature
    public final String verifySigner(String str, String str2) {
        return str;
    }
}
