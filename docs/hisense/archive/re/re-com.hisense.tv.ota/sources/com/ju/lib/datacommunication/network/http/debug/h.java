package com.ju.lib.datacommunication.network.http.debug;

/* JADX INFO: loaded from: classes.dex */
class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    DebugCheckType f110a;
    public String b;

    h(DebugCheckType debugCheckType, String str) {
        this.b = "";
        this.f110a = debugCheckType;
        this.b = str == null ? "" : str;
    }

    String a(i iVar) {
        StringBuilder sb;
        String string;
        String string2 = iVar.toString();
        if (this.b.contains(string2)) {
            com.ju.lib.datacommunication.network.http.i.b.d("DebugHelper", " has the same stackTrace, do not save ");
            return null;
        }
        int i = g.f109a[this.f110a.ordinal()];
        if (i != 1) {
            if (i == 2 || i == 3) {
                String str = (string2 + "params: " + iVar.e.toString() + "\n") + "respCode:" + iVar.d + "\n";
                sb = new StringBuilder();
                sb.append(str);
                sb.append("respBody:");
                string = iVar.f;
            }
            this.b += string2;
            return string2;
        }
        sb = new StringBuilder();
        sb.append(string2);
        sb.append("params: ");
        string = iVar.e.toString();
        sb.append(string);
        sb.append("\n");
        string2 = sb.toString();
        this.b += string2;
        return string2;
    }
}
