package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvGamCmdInfoBase {
    public static final String TAG = "MtkTvGamCmdInfo";
    public String s_ack;
    public String s_command;
    public String s_destination;
    public String s_parameter;
    public String s_source;

    public MtkTvGamCmdInfoBase() {
        this.s_source = null;
        this.s_destination = null;
        this.s_ack = null;
        this.s_command = null;
        this.s_parameter = null;
    }

    public String getack() {
        return this.s_ack;
    }

    public String getcommand() {
        return this.s_command;
    }

    public String getdestination() {
        return this.s_destination;
    }

    public String getparameter() {
        return this.s_parameter;
    }

    public String getsource() {
        return this.s_source;
    }

    public void setack(String str) {
        this.s_ack = str;
    }

    public void setcommand(String str) {
        this.s_command = str;
    }

    public void setdestination(String str) {
        this.s_destination = str;
    }

    public void setparameter(String str) {
        this.s_parameter = str;
    }

    public void setsource(String str) {
        this.s_source = str;
    }

    public MtkTvGamCmdInfoBase(String str, String str2, String str3, String str4, String str5) {
        this.s_source = str;
        this.s_destination = str2;
        this.s_ack = str3;
        this.s_command = str4;
        this.s_parameter = str5;
    }
}
