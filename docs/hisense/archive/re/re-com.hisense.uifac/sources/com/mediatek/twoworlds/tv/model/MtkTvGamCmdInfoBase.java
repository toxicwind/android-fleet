package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvGamCmdInfoBase {
    private static final String TAG = "MtkTvGamCmdInfo";
    private String s_ack;
    private String s_command;
    private String s_destination;
    private String s_parameter;
    private String s_source;

    public MtkTvGamCmdInfoBase() {
        this.s_source = null;
        this.s_destination = null;
        this.s_ack = null;
        this.s_command = null;
        this.s_parameter = null;
    }

    public MtkTvGamCmdInfoBase(String str, String str2, String str3, String str4, String str5) {
        this.s_source = str;
        this.s_destination = str2;
        this.s_ack = str3;
        this.s_command = str4;
        this.s_parameter = str5;
    }

    public void setsource(String str) {
        this.s_source = str;
    }

    public void setdestination(String str) {
        this.s_destination = str;
    }

    public void setack(String str) {
        this.s_ack = str;
    }

    public void setcommand(String str) {
        this.s_command = str;
    }

    public void setparameter(String str) {
        this.s_parameter = str;
    }

    public String getsource() {
        return this.s_source;
    }

    public String getdestination() {
        return this.s_destination;
    }

    public String getack() {
        return this.s_ack;
    }

    public String getcommand() {
        return this.s_command;
    }

    public String getparameter() {
        return this.s_parameter;
    }
}
