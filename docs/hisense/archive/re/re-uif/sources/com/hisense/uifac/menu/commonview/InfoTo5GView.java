package com.hisense.uifac.menu.commonview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.util.MenuConfigManager;

/* JADX INFO: loaded from: classes.dex */
public class InfoTo5GView extends LinearLayout {
    private TextView channelName;
    private Context context;
    private Handler mHandler;
    private LayoutInflater mInflater;
    private boolean mIsGettingVersionInfo;
    private MenuConfigManager mMenuConfigManager;

    public InfoTo5GView(Context context) {
        super(context);
        this.mIsGettingVersionInfo = false;
        this.context = context;
        this.mHandler = new Handler(context.getMainLooper());
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        this.mInflater = layoutInflaterFrom;
        layoutInflaterFrom.inflate(R.layout.menu_5g_info, this);
        init(true);
    }

    public InfoTo5GView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsGettingVersionInfo = false;
        this.context = context;
        this.mHandler = new Handler(context.getMainLooper());
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        this.mInflater = layoutInflaterFrom;
        layoutInflaterFrom.inflate(R.layout.menu_5g_info, this);
        init(false);
    }

    public void init(boolean z) {
        this.mMenuConfigManager = MenuConfigManager.getInstance(this.context);
        this.channelName = (TextView) findViewById(R.id.common_channel_name_r);
        setValue();
    }

    public void setValue() {
        getVersionInfoBackground();
    }

    private void getVersionInfoBackground() {
        if (this.mIsGettingVersionInfo) {
            return;
        }
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.commonview.InfoTo5GView.1
            @Override // java.lang.Runnable
            public void run() {
                InfoTo5GView.this.mIsGettingVersionInfo = true;
                VersionInfoBean versionInfoBean = InfoTo5GView.this.getVersionInfoBean();
                InfoTo5GView.this.mIsGettingVersionInfo = false;
                if (versionInfoBean != null) {
                    InfoTo5GView.this.mHandler.post(InfoTo5GView.this.new SetValueRunnable(versionInfoBean));
                }
            }
        }).start();
    }

    class SetValueRunnable implements Runnable {
        private VersionInfoBean mVersionInfoBean;

        public SetValueRunnable(VersionInfoBean versionInfoBean) {
            this.mVersionInfoBean = versionInfoBean;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mVersionInfoBean != null) {
                InfoTo5GView.this.channelName.setText(this.mVersionInfoBean.getChannelValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public VersionInfoBean getVersionInfoBean() {
        VersionInfoBean versionInfoBean = new VersionInfoBean(this);
        versionInfoBean.setChannelValue(this.mMenuConfigManager.get5GChannel());
        return versionInfoBean;
    }

    class VersionInfoBean {
        private String channelValue;

        VersionInfoBean(InfoTo5GView infoTo5GView) {
        }

        public String getChannelValue() {
            String str = this.channelValue;
            return str == null ? "" : str;
        }

        public void setChannelValue(String str) {
            this.channelValue = str;
        }
    }
}
