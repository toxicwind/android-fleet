package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvEventComponentDescriptorBase {
    private short streamContent = 0;
    private short componentType = 0;
    private short componentTag = 0;
    private short streamContentExt = 0;
    private String Lang = "";

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

    public short getStreamContentExt() {
        return this.streamContentExt;
    }

    public void setStreamContentExt(short s) {
        this.streamContentExt = s;
    }

    public String getComponentLang() {
        return this.Lang;
    }

    public void setComponentLang(String str) {
        this.Lang = str;
    }

    public String toString() {
        return "MtkTvEventComponentDescriptor: streamContent=" + ((int) this.streamContent) + ", componentType=" + ((int) this.componentType) + ", componentTag=" + ((int) this.componentTag) + ", streamContentExt =" + ((int) this.streamContentExt) + ", ComponentLang =" + this.Lang;
    }
}
