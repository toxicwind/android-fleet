package com.hmct.cloud.sdk.bean.upgrade;

import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DevUpgradeReply extends ReplyInfo {
    private static final long serialVersionUID = -8608708547035026764L;
    private String areaId;
    private String areaName;
    private String dppChkSum;
    private String dppSHA256Sum;
    private String dppUrl;
    private String eulaChkSum;
    private String eulaSHA256Sum;
    private String eulaUrl;
    private List fileList;
    private int fileNum;
    private String latestVersion;
    private int packageType = 0;
    private String releaseTime;
    private int ruleId;
    private int updateFlag;
    private int updateType;
    private String versionDesc;

    public String f() {
        return this.eulaUrl;
    }

    public List g() {
        return this.fileList;
    }

    public String h() {
        return this.latestVersion;
    }

    public int i() {
        return this.updateFlag;
    }

    public String j() {
        return this.versionDesc;
    }

    public void k(String str) {
        this.areaId = str;
    }

    public void l(String str) {
        this.areaName = str;
    }

    public void m(String str) {
        this.eulaChkSum = str;
    }

    public void n(String str) {
        this.eulaSHA256Sum = str;
    }

    public void o(String str) {
        this.eulaUrl = str;
    }

    public void p(List list) {
        this.fileList = list;
    }

    public void q(String str) {
        this.latestVersion = str;
    }

    public void r(int i) {
        this.packageType = i;
    }

    public void s(String str) {
        this.releaseTime = str;
    }

    public void t(int i) {
        this.ruleId = i;
    }

    public void u(int i) {
        this.updateFlag = i;
    }

    public void v(int i) {
        this.updateType = i;
    }

    public void w(String str) {
        this.versionDesc = str;
    }
}
