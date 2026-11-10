package com.hisense.uifac.commonview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.ListView;
import com.hisense.uifac.util.PageImp;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CustListView extends ListView {
    private PageImp mPageImp;
    private int mSelectItem;
    private UpDateListView mUpdate;

    public interface UpDateListView {
        void updata();
    }

    public CustListView(Context context) {
        super(context);
    }

    public CustListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public List<?> getCurrentList() {
        return this.mPageImp.getCurrentList();
    }

    public void initData(List<?> list, int i, UpDateListView upDateListView) {
        this.mPageImp = new PageImp(list, i);
        this.mUpdate = upDateListView;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        this.mSelectItem = getSelectedItemPosition();
        if (i != 19) {
            if (i == 20) {
                if (this.mPageImp.getCount() <= 0) {
                    return false;
                }
                if ((this.mSelectItem + 1) % this.mPageImp.getPerPage() == 0 || (this.mSelectItem + 1) % this.mPageImp.getCount() == 0) {
                    this.mPageImp.nextPage();
                    UpDateListView upDateListView = this.mUpdate;
                    if (upDateListView != null) {
                        upDateListView.updata();
                    }
                    setSelection(0);
                }
                if ((this.mSelectItem + 1) % this.mPageImp.getPerPage() == 0 && this.mPageImp.getPageNum() == 1) {
                    setSelection(0);
                }
            }
        } else {
            if (this.mPageImp.getCount() <= 0) {
                return false;
            }
            if (this.mSelectItem % this.mPageImp.getPerPage() <= 0) {
                UpDateListView upDateListView2 = this.mUpdate;
                if (upDateListView2 != null) {
                    upDateListView2.updata();
                }
                if (this.mPageImp.getPerPage() < this.mPageImp.getCount()) {
                    setSelection(this.mPageImp.getPerPage() - 1);
                } else {
                    setSelection(this.mPageImp.getCount() - 1);
                }
            }
            if (this.mSelectItem % this.mPageImp.getPerPage() == 0 && this.mPageImp.getPageNum() == 1) {
                setSelection(this.mPageImp.getPerPage() - 1);
            }
        }
        return super.onKeyDown(i, keyEvent);
    }
}
