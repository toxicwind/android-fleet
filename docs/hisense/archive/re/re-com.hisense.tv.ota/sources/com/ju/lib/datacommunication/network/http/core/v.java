package com.ju.lib.datacommunication.network.http.core;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f99a;
    private List b;
    private List c;
    private long d;
    private long e;

    private v(u uVar) {
        this.f99a = uVar.f98a;
        this.b = new ArrayList(uVar.b);
        this.c = new ArrayList(uVar.c);
        this.d = uVar.d;
        this.e = uVar.e;
    }

    public u d() {
        return new u(this);
    }

    public String e() {
        return this.f99a;
    }

    public List f() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(InetAddress.getByAddress(e(), InetAddress.getByName((String) it.next()).getAddress()));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public List g() {
        return Collections.unmodifiableList(this.b);
    }

    public List h() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(InetAddress.getByAddress(e(), InetAddress.getByName((String) it.next()).getAddress()));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public List i() {
        return Collections.unmodifiableList(this.c);
    }

    public long j() {
        return this.e;
    }

    public long k() {
        return this.d;
    }

    public int l() {
        return this.b.size();
    }

    public String toString() {
        return "HostInfo: " + this.f99a + ", " + this.d + ", " + this.b + ", " + this.c;
    }
}
