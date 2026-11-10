package com.hisense.uifac.network.adapter;

import android.net.wifi.ScanResult;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FacWifiAdapter extends BaseAdapter {
    List<ScanResult> list = new ArrayList();

    public abstract void setList(List<ScanResult> list);
}
