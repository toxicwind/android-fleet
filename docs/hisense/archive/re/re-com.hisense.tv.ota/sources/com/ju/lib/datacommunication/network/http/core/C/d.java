package com.ju.lib.datacommunication.network.http.core.C;

import com.ju.lib.datacommunication.network.http.core.k;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class d extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List f81a;
    private final HashMap b;
    private final String c;

    private d(b bVar, HashMap map) {
        this.f81a = new ArrayList(bVar.f79a);
        this.b = map;
        this.c = bVar.c;
    }

    @Override // com.ju.lib.datacommunication.network.http.core.k
    public long a() {
        int size = this.f81a.size();
        long length = 0;
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                length++;
            }
            length += (long) (((c) this.f81a.get(i)).a().getBytes("UTF-8").length + 1 + ((c) this.f81a.get(i)).b().getBytes("UTF-8").length);
        }
        if (length <= 0) {
            return -1L;
        }
        return length;
    }

    @Override // com.ju.lib.datacommunication.network.http.core.k
    public String b() {
        return this.c;
    }

    @Override // com.ju.lib.datacommunication.network.http.core.k
    public void c(OutputStream outputStream) throws IOException {
        int size = this.f81a.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                outputStream.write(38);
            }
            c cVar = (c) this.f81a.get(i);
            outputStream.write(cVar.a().getBytes("UTF-8"));
            outputStream.write(61);
            outputStream.write(cVar.b().getBytes("UTF-8"));
        }
    }

    public HashMap d() {
        return this.b;
    }
}
