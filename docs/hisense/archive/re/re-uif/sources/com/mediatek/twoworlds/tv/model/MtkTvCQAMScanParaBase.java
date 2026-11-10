package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvCQAMScanParaBase {
    private int countOfWideScanChannel;
    private int endIndex;
    private int freqPlan;
    private int modMask;
    private int startIndex;
    private int[] wideScanChannel;

    public int getStartIndex() {
        return this.startIndex;
    }

    public void setStartIndex(int i) {
        this.startIndex = i;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public void setEndIndex(int i) {
        this.endIndex = i;
    }

    public int getModMask() {
        return this.modMask;
    }

    public void setModMask(int i) {
        this.modMask = i;
    }

    public int getFreqPlan() {
        return this.freqPlan;
    }

    public void setFreqPlan(int i) {
        this.freqPlan = i;
    }

    public int getCountOfWideScanChannel() {
        return this.countOfWideScanChannel;
    }

    public void setCountOfWideScanChannel(int i) {
        this.countOfWideScanChannel = i;
    }

    public int[] getWideScanChannel() {
        return this.wideScanChannel;
    }

    public void setWideScanChannel(int[] iArr) {
        this.wideScanChannel = iArr;
    }
}
