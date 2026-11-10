package com.ju.lib.datacommunication.network.http.impl;

import com.ju.lib.datacommunication.network.http.core.HttpException;
import com.ju.lib.datacommunication.network.http.core.p;
import java.io.IOException;
import okhttp3.e0;

/* JADX INFO: loaded from: classes.dex */
final class i extends p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private e0 f148a;

    public i(e0 e0Var) {
        this.f148a = e0Var;
    }

    @Override // com.ju.lib.datacommunication.network.http.core.p
    public String a() throws HttpException {
        try {
            return this.f148a.Q();
        } catch (IOException e) {
            throw new HttpException(1001, "Connect error! ", e);
        }
    }
}
