package com.hisense.uifac.nav.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.hisense.uifac.R;
import com.hisense.uifac.nav.NavIntegration;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SourceListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mSourceList;
    private Drawable mSourceSelectedIcon;
    private Drawable mSourceUnSelectedIcon;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder {
        TextView mTextView;

        private ViewHolder(SourceListAdapter sourceListAdapter) {
        }
    }

    public SourceListAdapter(Context context, List<String> list, Drawable drawable, Drawable drawable2) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mSourceList = list;
        this.mSourceSelectedIcon = drawable;
        this.mSourceUnSelectedIcon = drawable2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mSourceList.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.mInflater.inflate(R.layout.nav_source_item, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = (TextView) view.findViewById(R.id.nav_source_list_item_tv);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mTextView.setText(this.mSourceList.get(i));
        if (NavIntegration.getInstance(this.mContext).getCurrentInputSourceDisplayName().equals(this.mSourceList.get(i))) {
            viewHolder.mTextView.setCompoundDrawables(this.mSourceSelectedIcon, null, null, null);
        } else {
            viewHolder.mTextView.setCompoundDrawables(this.mSourceUnSelectedIcon, null, null, null);
        }
        return view;
    }
}
