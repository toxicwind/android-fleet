package com.hisense.uifac.menu.util;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PageImp {
    private int allNum;
    private List<?> currentList;
    private List<?> list;
    private int perPage = 9;
    private int currentPage = 1;
    private int pageNum = 1;

    public PageImp(List<?> list, int i) {
        init(list, i);
    }

    public List<?> getCurrentList() {
        int i = this.currentPage;
        if (i == this.pageNum) {
            List<?> list = this.list;
            if (list == null) {
                return null;
            }
            this.currentList = list.subList((i - 1) * this.perPage, this.allNum);
        } else {
            int size = this.list.size();
            int i2 = this.perPage;
            int i3 = this.currentPage;
            if (size >= i2 * i3) {
                this.currentList = this.list.subList((i3 - 1) * i2, i2 * i3);
            } else {
                this.currentList = this.list;
            }
        }
        return this.currentList;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void gotoPage(int i) {
        int i2 = this.pageNum;
        if (i > i2) {
            this.currentPage = i2;
        } else {
            this.currentPage = i;
        }
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

    public boolean hasPrePage() {
        int i = this.currentPage - 1;
        this.currentPage = i;
        if (i > 0) {
            return true;
        }
        this.currentPage = 1;
        return false;
    }

    public void headPage() {
        this.currentPage = 1;
    }

    public void lastPage() {
        this.currentPage = this.pageNum;
    }

    public void nextPage() {
        hasNextPage();
    }

    public void prePage() {
        hasPrePage();
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
            int i3 = size2 % i;
            int i4 = size2 / i;
            if (i3 != 0) {
                i4++;
            }
            this.pageNum = i4;
            if (i4 == 0) {
                this.pageNum = 1;
            }
        }
    }
}
