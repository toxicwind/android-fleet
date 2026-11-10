package com.hmct.cloud.sdk.bean.global;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class StartupInfo implements Serializable {
    public static final long serialVersionUID = -209324660329486529L;
    public boolean bundleFlag;
    public String category;
    public int intentFlags;
    public String intentType;
    public String packageName;
    public String startupCommand;
    public int startupType;
    public String startupUrl;

    public String getCategory() {
        return this.category;
    }

    public int getIntentFlags() {
        return this.intentFlags;
    }

    public String getIntentType() {
        return this.intentType;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getStartupCommand() {
        return this.startupCommand;
    }

    public int getStartupType() {
        return this.startupType;
    }

    public String getStartupUrl() {
        return this.startupUrl;
    }

    public boolean isBundleFlag() {
        return this.bundleFlag;
    }

    public void setBundleFlag(boolean z) {
        this.bundleFlag = z;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public void setIntentFlags(int i) {
        this.intentFlags = i;
    }

    public void setIntentType(String str) {
        this.intentType = str;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setStartupCommand(String str) {
        this.startupCommand = str;
    }

    public void setStartupType(int i) {
        this.startupType = i;
    }

    public void setStartupUrl(String str) {
        this.startupUrl = str;
    }
}
