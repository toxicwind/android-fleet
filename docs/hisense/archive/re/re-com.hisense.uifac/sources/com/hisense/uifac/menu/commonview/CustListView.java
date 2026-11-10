package com.hisense.uifac.menu.commonview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.hisense.uifac.menu.adapter.SetConfigListViewAdapter;
import com.hisense.uifac.menu.util.PageImp;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CustListView extends ListView {
    private int mBeforeSelectedPosition;
    private int mCurrentSelectedPosition;
    private int mFirstEnableItemPosition;
    private int mLastEnableItemPosition;
    private PageImp mPageImp;
    private UpDateListView mUpdate;
    private int pageNum;

    public interface UpDateListView {
        void updata();
    }

    public CustListView(Context context) {
        super(context);
        this.mCurrentSelectedPosition = 0;
        this.mBeforeSelectedPosition = 0;
    }

    public CustListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentSelectedPosition = 0;
        this.mBeforeSelectedPosition = 0;
    }

    public CustListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentSelectedPosition = 0;
        this.mBeforeSelectedPosition = 0;
    }

    public List<?> getCurrentList() {
        return this.mPageImp.getCurrentList();
    }

    public int getCurrentPageNum() {
        return this.mPageImp.getCurrentPage();
    }

    public void initData(List<?> list, int i, UpDateListView upDateListView, int i2) {
        PageImp pageImp = new PageImp(list, i);
        this.mPageImp = pageImp;
        this.pageNum = pageImp.getPageNum();
        this.mUpdate = upDateListView;
        if (i2 > 0) {
            this.mPageImp.gotoPage(i2);
        }
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 19) {
            int selectedItemPosition = getSelectedItemPosition();
            this.mCurrentSelectedPosition = selectedItemPosition;
            if (selectedItemPosition < 0) {
                this.mCurrentSelectedPosition = this.mBeforeSelectedPosition;
            }
            int i2 = this.mCurrentSelectedPosition;
            this.mBeforeSelectedPosition = i2;
            if (i2 == this.mFirstEnableItemPosition) {
                if (this.mPageImp.getCurrentPage() != 1) {
                    this.mPageImp.prePage();
                    UpDateListView upDateListView = this.mUpdate;
                    if (upDateListView != null) {
                        upDateListView.updata();
                    }
                    setSelection(this.mLastEnableItemPosition);
                } else {
                    int i3 = this.pageNum;
                    if (i3 == 1) {
                        setSelection(this.mLastEnableItemPosition);
                    } else if (i3 > 1 && this.mPageImp.getCurrentPage() == 1) {
                        this.mPageImp.lastPage();
                        UpDateListView upDateListView2 = this.mUpdate;
                        if (upDateListView2 != null) {
                            upDateListView2.updata();
                        }
                        setSelection(this.mLastEnableItemPosition);
                    }
                }
            }
        } else if (i == 20) {
            int selectedItemPosition2 = getSelectedItemPosition();
            this.mCurrentSelectedPosition = selectedItemPosition2;
            if (selectedItemPosition2 < 0) {
                this.mCurrentSelectedPosition = this.mBeforeSelectedPosition;
            }
            int i4 = this.mCurrentSelectedPosition;
            this.mBeforeSelectedPosition = i4;
            if (i4 == this.mLastEnableItemPosition) {
                int currentPage = this.mPageImp.getCurrentPage();
                int i5 = this.pageNum;
                if (currentPage != i5) {
                    this.mPageImp.nextPage();
                    UpDateListView upDateListView3 = this.mUpdate;
                    if (upDateListView3 != null) {
                        upDateListView3.updata();
                    }
                    setSelection(this.mFirstEnableItemPosition);
                } else if (i5 == 1) {
                    setSelection(this.mFirstEnableItemPosition);
                } else if (this.mPageImp.getCurrentPage() == this.mPageImp.getPageNum()) {
                    this.mPageImp.headPage();
                    UpDateListView upDateListView4 = this.mUpdate;
                    if (upDateListView4 != null) {
                        upDateListView4.updata();
                    }
                    setSelection(this.mFirstEnableItemPosition);
                }
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.widget.AdapterView
    public void setAdapter(ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
        this.mLastEnableItemPosition = getLastEnableItemPosition();
        this.mFirstEnableItemPosition = getFirstEnableItemPosition();
    }

    private int getFirstEnableItemPosition() {
        return ((SetConfigListViewAdapter) getAdapter()).getFirstEnableItemPosition();
    }

    private int getLastEnableItemPosition() {
        return ((SetConfigListViewAdapter) getAdapter()).getLastEnableItemPosition();
    }
}
