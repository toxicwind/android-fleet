package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvTeletextPageBase {
    public static final int TTX_ANY_PAGE_SUB_CODE = 16255;
    public static final int TTX_NULL_PAGE_NUMBER = 255;
    public int pageNumber;
    public int pageSubCode;

    public MtkTvTeletextPageBase() {
        this.pageNumber = 255;
        this.pageSubCode = TTX_ANY_PAGE_SUB_CODE;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public int getPageSubCode() {
        return this.pageSubCode;
    }

    public void setPageNumber(int i) {
        this.pageNumber = i;
    }

    public void setPageSubCode(int i) {
        this.pageSubCode = i;
    }

    public MtkTvTeletextPageBase(int i, int i2) {
        this.pageNumber = i;
        this.pageSubCode = i2;
    }
}
