package com.ju.lib.datareport.dynamic;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicUrl {
    public String dot;
    public String exc;
    public String excfile;
    public String hea;
    public String policy;
    public String ter;

    public DynamicUrl() {
        this.ter = "";
        this.exc = "";
        this.excfile = "";
        this.hea = "";
        this.dot = "";
        this.policy = "";
    }

    public String getDot() {
        return this.dot;
    }

    public String getExc() {
        return this.exc;
    }

    public String getExcfile() {
        return this.excfile;
    }

    public String getHea() {
        return this.hea;
    }

    public String getPolicy() {
        return this.policy;
    }

    public String getTer() {
        return this.ter;
    }

    public boolean isValide() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6 = this.ter;
        return (str6 == null || str6.isEmpty() || (str = this.exc) == null || str.isEmpty() || (str2 = this.excfile) == null || str2.isEmpty() || (str3 = this.hea) == null || str3.isEmpty() || (str4 = this.dot) == null || str4.isEmpty() || (str5 = this.policy) == null || str5.isEmpty()) ? false : true;
    }

    public void setDot(String str) {
        this.dot = str;
    }

    public void setExc(String str) {
        this.exc = str;
    }

    public void setExcfile(String str) {
        this.excfile = str;
    }

    public void setHea(String str) {
        this.hea = str;
    }

    public void setPolicy(String str) {
        this.policy = str;
    }

    public void setTer(String str) {
        this.ter = str;
    }

    public String toString() {
        StringBuilder sbA = a.a("DynamicUrl{ter='");
        a.a(sbA, this.ter, '\'', ", exc='");
        a.a(sbA, this.exc, '\'', ", excfile='");
        a.a(sbA, this.excfile, '\'', ", hea='");
        a.a(sbA, this.hea, '\'', ", dot='");
        a.a(sbA, this.dot, '\'', ", policy='");
        sbA.append(this.policy);
        sbA.append('\'');
        sbA.append('}');
        return sbA.toString();
    }

    public DynamicUrl(String str, String str2, String str3, String str4, String str5, String str6) {
        this.ter = "";
        this.exc = "";
        this.excfile = "";
        this.hea = "";
        this.dot = "";
        this.policy = "";
        this.ter = str;
        this.exc = str2;
        this.excfile = str3;
        this.hea = str4;
        this.dot = str5;
        this.policy = str6;
    }
}
