package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvATSCScanParaBase {
    public int endIndex;
    public int freqPlan;
    public int modMask;
    public int startIndex;

    public enum ATSCFreqPlan {
        FREQ_PLAN_NONE,
        FREQ_PLAN_STD,
        FREQ_PLAN_IRC,
        FREQ_PLAN_HRC,
        FREQ_PLAN_AUTO
    }

    public enum ATSCModulation {
        MOD_NONE,
        MOD_VSB_8,
        MOD_QAM_64,
        MOD_QAM_256,
        MOD_ALL
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public int getFreqPlan() {
        return this.freqPlan;
    }

    public int getModMask() {
        return this.modMask;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public void setEndIndex(int i) {
        this.endIndex = i;
    }

    public void setFreqPlan(int i) {
        this.freqPlan = i;
    }

    public void setModMask(int i) {
        this.modMask = i;
    }

    public void setStartIndex(int i) {
        this.startIndex = i;
    }
}
