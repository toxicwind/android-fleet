package com.hisense.uifac.util;

import android.util.Log;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PageImp {
    private int allNum;
    private List<?> currentList;
    private List<?> list;
    private int perPage = 6;
    private int currentPage = 1;
    private int pageNum = 1;

    public PageImp(List<?> list, int i) {
        init(list, i);
    }

    public int getCount() {
        return this.allNum;
    }

    public List<?> getCurrentList() {
        Log.d("Fac_PageImp", "getCurrentList()currentPage:" + this.currentPage + "pageNum:" + this.pageNum);
        int i = this.currentPage;
        if (i == this.pageNum) {
            this.currentList = this.list.subList((i - 1) * this.perPage, this.allNum);
        } else {
            List<?> list = this.list;
            int i2 = this.perPage;
            this.currentList = list.subList((i - 1) * i2, i2 * i);
        }
        Log.d("Fac_PageImp", "currentList size" + this.currentList.size());
        return this.currentList;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public int getPerPage() {
        return this.perPage;
    }

    public boolean hasNextPage() {
        int i = this.currentPage + 1;
        this.currentPage = i;
        int i2 = this.pageNum;
        if (i <= i2) {
            return true;
        }
        this.currentPage = i2;
        return false;
    }

    public void nextPage() {
        Log.d("Fac_PageImp", "nextPage");
        hasNextPage();
    }

    private void init(List<?> list, int i) {
        this.list = list;
        if (i >= 1) {
            this.perPage = i;
        }
        if (list != null) {
            int size = list.size();
            int i2 = this.perPage;
            if (size > i2) {
                this.currentList = list.subList(0, i2 - 1);
            }
        }
        if (list != null) {
            int size2 = list.size();
            this.allNum = size2;
            int i3 = this.perPage;
            this.pageNum = ((size2 + i3) - 1) / i3;
        }
        Log.d("Fac_PageImp", "init()perPage:" + this.perPage + "allnum:" + this.allNum);
    }
}
