package com.mediatek.twoworlds.tv.model;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvEventAudioComponentBase {
    public short streamContent = 0;
    public short componentType = 0;
    public short componentTag = 0;
    public short streamType = 0;
    public short simulcastGrpTag = 0;
    public short qualityIndicator = 0;
    public short sampleRate = 0;
    public boolean multiLingualFlag = false;
    public boolean mainComponentFlag = false;
    public String language1 = "";
    public String language2 = "";
    public String languageName1 = "";
    public String languageName2 = "";

    public short getComponentTag() {
        return this.componentTag;
    }

    public short getComponentType() {
        return this.componentType;
    }

    public String getLanguage1() {
        return this.language1;
    }

    public String getLanguage2() {
        return this.language2;
    }

    public String getLanguageName1() {
        return this.languageName1;
    }

    public String getLanguageName2() {
        return this.languageName2;
    }

    public boolean getMainComponentFlag() {
        return this.mainComponentFlag;
    }

    public boolean getMultiLingualFlag() {
        return this.multiLingualFlag;
    }

    public short getQualityIndicator() {
        return this.qualityIndicator;
    }

    public short getSampleRate() {
        return this.sampleRate;
    }

    public short getSimulcastGrpTag() {
        return this.simulcastGrpTag;
    }

    public short getStreamContent() {
        return this.streamContent;
    }

    public short getStreamType() {
        return this.streamType;
    }

    public void setComponentTag(short s) {
        this.componentTag = s;
    }

    public void setComponentType(short s) {
        this.componentType = s;
    }

    public void setLanguage1(String str) {
        this.language1 = str;
    }

    public void setLanguage2(String str) {
        this.language2 = str;
    }

    public void setLanguageName1(String str) {
        this.languageName1 = str;
    }

    public void setLanguageName2(String str) {
        this.languageName2 = str;
    }

    public void setMainComponentFlag(boolean z) {
        this.mainComponentFlag = z;
    }

    public void setMultiLingualFlag(boolean z) {
        this.multiLingualFlag = z;
    }

    public void setQualityIndicator(short s) {
        this.qualityIndicator = s;
    }

    public void setSampleRate(short s) {
        this.sampleRate = s;
    }

    public void setSimulcastGrpTag(short s) {
        this.simulcastGrpTag = s;
    }

    public void setStreamContent(short s) {
        this.streamContent = s;
    }

    public void setStreamType(short s) {
        this.streamType = s;
    }

    public String toString() {
        StringBuilder sbA = a.a("MtkTvEventAudioComponentDescriptor: streamContent=");
        sbA.append((int) this.streamContent);
        sbA.append(", componentType=");
        sbA.append((int) this.componentType);
        sbA.append(", componentTag=");
        sbA.append((int) this.componentTag);
        sbA.append(", streamType =");
        sbA.append((int) this.streamType);
        sbA.append(", simulcastGrpTag =");
        sbA.append((int) this.simulcastGrpTag);
        sbA.append(", qualityIndicator =");
        sbA.append((int) this.qualityIndicator);
        sbA.append(", sampleRate =");
        sbA.append((int) this.sampleRate);
        sbA.append(", multiLingualFlag =");
        sbA.append(this.multiLingualFlag);
        sbA.append(", mainComponentFlag=");
        sbA.append(this.mainComponentFlag);
        sbA.append(", language1 =");
        sbA.append(this.language1);
        sbA.append(", language2");
        sbA.append(this.language2);
        sbA.append(", languageName1 =");
        sbA.append(this.languageName1);
        sbA.append(", languageName2");
        sbA.append(this.languageName2);
        return sbA.toString();
    }
}
