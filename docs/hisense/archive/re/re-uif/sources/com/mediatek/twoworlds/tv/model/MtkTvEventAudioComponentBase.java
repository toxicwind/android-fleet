package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvEventAudioComponentBase {
    private short streamContent = 0;
    private short componentType = 0;
    private short componentTag = 0;
    private short streamType = 0;
    private short simulcastGrpTag = 0;
    private short qualityIndicator = 0;
    private short sampleRate = 0;
    private boolean multiLingualFlag = false;
    private boolean mainComponentFlag = false;
    private String language1 = "";
    private String language2 = "";
    private String languageName1 = "";
    private String languageName2 = "";

    public short getStreamContent() {
        return this.streamContent;
    }

    public void setStreamContent(short s) {
        this.streamContent = s;
    }

    public short getComponentType() {
        return this.componentType;
    }

    public void setComponentType(short s) {
        this.componentType = s;
    }

    public short getComponentTag() {
        return this.componentTag;
    }

    public void setComponentTag(short s) {
        this.componentTag = s;
    }

    public short getStreamType() {
        return this.streamType;
    }

    public void setStreamType(short s) {
        this.streamType = s;
    }

    public short getSimulcastGrpTag() {
        return this.simulcastGrpTag;
    }

    public void setSimulcastGrpTag(short s) {
        this.simulcastGrpTag = s;
    }

    public short getQualityIndicator() {
        return this.qualityIndicator;
    }

    public void setQualityIndicator(short s) {
        this.qualityIndicator = s;
    }

    public short getSampleRate() {
        return this.sampleRate;
    }

    public void setSampleRate(short s) {
        this.sampleRate = s;
    }

    public boolean getMultiLingualFlag() {
        return this.multiLingualFlag;
    }

    public void setMultiLingualFlag(boolean z) {
        this.multiLingualFlag = z;
    }

    public boolean getMainComponentFlag() {
        return this.mainComponentFlag;
    }

    public void setMainComponentFlag(boolean z) {
        this.mainComponentFlag = z;
    }

    public String getLanguage1() {
        return this.language1;
    }

    public void setLanguage1(String str) {
        this.language1 = str;
    }

    public String getLanguage2() {
        return this.language2;
    }

    public void setLanguage2(String str) {
        this.language2 = str;
    }

    public String getLanguageName1() {
        return this.languageName1;
    }

    public void setLanguageName1(String str) {
        this.languageName1 = str;
    }

    public String getLanguageName2() {
        return this.languageName2;
    }

    public void setLanguageName2(String str) {
        this.languageName2 = str;
    }

    public String toString() {
        return "MtkTvEventAudioComponentDescriptor: streamContent=" + ((int) this.streamContent) + ", componentType=" + ((int) this.componentType) + ", componentTag=" + ((int) this.componentTag) + ", streamType =" + ((int) this.streamType) + ", simulcastGrpTag =" + ((int) this.simulcastGrpTag) + ", qualityIndicator =" + ((int) this.qualityIndicator) + ", sampleRate =" + ((int) this.sampleRate) + ", multiLingualFlag =" + this.multiLingualFlag + ", mainComponentFlag=" + this.mainComponentFlag + ", language1 =" + this.language1 + ", language2" + this.language2 + ", languageName1 =" + this.languageName1 + ", languageName2" + this.languageName2;
    }
}
