package com.mediatek.twoworlds.tv.model;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvEventComponentDescriptorBase {
    public short streamContent = 0;
    public short componentType = 0;
    public short componentTag = 0;
    public short streamContentExt = 0;
    public String Lang = "";

    public String getComponentLang() {
        return this.Lang;
    }

    public short getComponentTag() {
        return this.componentTag;
    }

    public short getComponentType() {
        return this.componentType;
    }

    public short getStreamContent() {
        return this.streamContent;
    }

    public short getStreamContentExt() {
        return this.streamContentExt;
    }

    public void setComponentLang(String str) {
        this.Lang = str;
    }

    public void setComponentTag(short s) {
        this.componentTag = s;
    }

    public void setComponentType(short s) {
        this.componentType = s;
    }

    public void setStreamContent(short s) {
        this.streamContent = s;
    }

    public void setStreamContentExt(short s) {
        this.streamContentExt = s;
    }

    public String toString() {
        StringBuilder sbA = a.a("MtkTvEventComponentDescriptor: streamContent=");
        sbA.append((int) this.streamContent);
        sbA.append(", componentType=");
        sbA.append((int) this.componentType);
        sbA.append(", componentTag=");
        sbA.append((int) this.componentTag);
        sbA.append(", streamContentExt =");
        sbA.append((int) this.streamContentExt);
        sbA.append(", ComponentLang =");
        sbA.append(this.Lang);
        return sbA.toString();
    }
}
