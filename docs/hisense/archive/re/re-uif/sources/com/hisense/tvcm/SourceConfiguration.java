package com.hisense.tvcm;

/* JADX INFO: loaded from: classes.dex */
public class SourceConfiguration {
    private int countOfThisSource;
    private String displayName;
    private boolean isEnabled;
    private boolean isShown;
    private String oriSourceName;
    private int tilValue;

    public String getOriSourceName() {
        return this.oriSourceName;
    }

    public void setOriSourceName(String str) {
        this.oriSourceName = str;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    public int getCountOfThisSource() {
        return this.countOfThisSource;
    }

    public void setCountOfThisSource(int i) {
        this.countOfThisSource = i;
    }

    public int getTilValue() {
        return this.tilValue;
    }

    public void setTilValue(int i) {
        this.tilValue = i;
    }

    public boolean isShown() {
        return this.isShown;
    }

    public void setShown(boolean z) {
        this.isShown = z;
    }
}
