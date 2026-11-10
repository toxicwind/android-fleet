package com.hisense.support.tv;

import android.content.Context;
import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.MtkTvBanner;
import com.mediatek.twoworlds.tv.MtkTvBroadcast;
import com.mediatek.twoworlds.tv.MtkTvBroadcastBase;
import com.mediatek.twoworlds.tv.MtkTvChannelListBase;
import com.mediatek.twoworlds.tv.MtkTvConfig;
import com.mediatek.twoworlds.tv.common.MtkTvChCommonBase;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import com.mediatek.twoworlds.tv.model.MtkTvChannelInfoBase;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class HisenseFactoryTvAPI extends HisenseTvAPIBase {
    public static HisenseFactoryTvAPI hisenseFactoryTvAPI;
    public MtkTvBroadcastBase broadbase;
    public MtkTvChannelListBase chlst;
    public List<MtkTvChannelInfoBase> list_air;
    public List<MtkTvChannelInfoBase> list_cable;
    public MtkTvBanner mMtkTvBanner;
    public MtkTvConfig mMtkTvConfig;
    public int num_air;
    public int num_cable;

    public HisenseFactoryTvAPI(Context context) {
        super(context);
        this.num_air = 0;
        this.num_cable = 0;
        this.chlst = new MtkTvChannelListBase();
        this.list_air = new ArrayList();
        this.list_cable = new ArrayList();
        MtkTvBroadcast.getInstance();
        this.mMtkTvConfig = MtkTvConfig.getInstance();
        this.mMtkTvBanner = MtkTvBanner.getInstance();
    }

    public static HisenseFactoryTvAPI getInstance(Context context) {
        HisenseFactoryTvAPI hisenseFactoryTvAPI2 = hisenseFactoryTvAPI;
        if (hisenseFactoryTvAPI2 != null) {
            return hisenseFactoryTvAPI2;
        }
        hisenseFactoryTvAPI = new HisenseFactoryTvAPI(context);
        return hisenseFactoryTvAPI;
    }

    private void setCurrentChannel(int i, int i2) {
        Log.i(HisenseTvAPIBase.TAG, "setCurrentChannel channel = " + i);
        if (i2 == 0) {
            if (this.list_air == null && this.num_air == 0) {
                return;
            }
            for (MtkTvChannelInfoBase mtkTvChannelInfoBase : this.list_air) {
                StringBuilder sbA = a.a("air channel infobase number (i) = ");
                sbA.append(mtkTvChannelInfoBase.getChannelNumber());
                Log.i(HisenseTvAPIBase.TAG, sbA.toString());
                if (mtkTvChannelInfoBase.getChannelNumber() == i) {
                    StringBuilder sbA2 = a.a("air mtkTvChannelInfoBase.getChannelNumber() = ");
                    sbA2.append(mtkTvChannelInfoBase.getChannelNumber());
                    Log.i(HisenseTvAPIBase.TAG, sbA2.toString());
                    MtkTvChannelListBase mtkTvChannelListBase = this.chlst;
                    MtkTvChannelListBase.setCurrentChannel(mtkTvChannelInfoBase);
                    return;
                }
            }
            return;
        }
        if (this.list_cable == null && this.num_cable == 0) {
            return;
        }
        for (MtkTvChannelInfoBase mtkTvChannelInfoBase2 : this.list_cable) {
            StringBuilder sbA3 = a.a("cable channel infobase number (i) = ");
            sbA3.append(mtkTvChannelInfoBase2.getChannelNumber());
            Log.i(HisenseTvAPIBase.TAG, sbA3.toString());
            if (mtkTvChannelInfoBase2.getChannelNumber() == i) {
                StringBuilder sbA4 = a.a("cable mtkTvChannelInfoBase.getChannelNumber() = ");
                sbA4.append(mtkTvChannelInfoBase2.getChannelNumber());
                Log.i(HisenseTvAPIBase.TAG, sbA4.toString());
                MtkTvChannelListBase mtkTvChannelListBase2 = this.chlst;
                MtkTvChannelListBase.setCurrentChannel(mtkTvChannelInfoBase2);
                return;
            }
        }
    }

    public void changeChannel(int i) {
        int tunerMode = getTunerMode();
        Log.i(HisenseTvAPIBase.TAG, "change channel to " + i);
        changeChannel(i, tunerMode);
    }

    public void changeChannelForChannelUPAndDown(int i) {
        Log.i(HisenseTvAPIBase.TAG, "channelIndex = " + i);
        if (getTunerMode() == 0) {
            int size = this.list_air.size();
            StringBuilder sbA = a.a("num_air = ");
            sbA.append(this.num_air);
            sbA.append("  air_size = ");
            sbA.append(size);
            Log.i(HisenseTvAPIBase.TAG, sbA.toString());
            if (size <= 0) {
                Log.i(HisenseTvAPIBase.TAG, "air has not channel");
                return;
            }
            int i2 = size - 1;
            if (i > i2) {
                i = 0;
            }
            if (i < 0) {
                i = i2;
            }
            this.broadbase.channelSelect(this.list_air.get(i), false);
            return;
        }
        int size2 = this.list_cable.size();
        StringBuilder sbA2 = a.a("num_cable = ");
        sbA2.append(this.num_cable);
        sbA2.append("   cable size = ");
        sbA2.append(size2);
        Log.i(HisenseTvAPIBase.TAG, sbA2.toString());
        if (size2 <= 0) {
            Log.i(HisenseTvAPIBase.TAG, "cable has not channel");
            return;
        }
        int i3 = size2 - 1;
        if (i > i3) {
            i = 0;
        }
        if (i < 0) {
            i = i3;
        }
        this.broadbase.channelSelect(this.list_cable.get(i), false);
    }

    public void changeChannelForId(int i, int i2) {
        Log.i(HisenseTvAPIBase.TAG, "changeChannelForId majorNo is " + i);
        Log.i(HisenseTvAPIBase.TAG, "changeChannelForId minorNo is " + i2);
        this.broadbase.channelSelectByChannelNumber(i, i2);
    }

    public void channelDown() {
        int channelIndex = getChannelIndex() - 1;
        Log.i(HisenseTvAPIBase.TAG, "channel down chnum = " + channelIndex);
        changeChannelForChannelUPAndDown(channelIndex);
    }

    public void channelUp() {
        int channelIndex = getChannelIndex() + 1;
        Log.i(HisenseTvAPIBase.TAG, "channel up chnum = " + channelIndex);
        changeChannelForChannelUPAndDown(channelIndex);
    }

    public void cleanPresetChannel() {
        List<MtkTvChannelInfoBase> list = this.list_air;
        if (list != null) {
            list.clear();
        }
        List<MtkTvChannelInfoBase> list2 = this.list_cable;
        if (list2 != null) {
            list2.clear();
        }
    }

    public void cleanchannel() {
        Log.d(HisenseTvAPIBase.TAG, "cleanChannelList(1), return value = " + MtkTvChannelListBase.cleanChannelList(1, true) + "\n");
        a.a("cleanChannelList(2), return value = ", MtkTvChannelListBase.cleanChannelList(2, true), "\n", HisenseTvAPIBase.TAG);
        this.mMtkTvConfig.setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE, 1);
    }

    public List<MtkTvChannelInfoBase> getAirChannelList() {
        new ArrayList();
        this.num_air = this.chlst.getChannelCountByFilter(1, MtkTvChCommonBase.SB_VNET_ACTIVE | MtkTvChCommonBase.SB_VNET_VISIBLE);
        for (MtkTvChannelInfoBase mtkTvChannelInfoBase : this.chlst.getChannelListByFilter(1, MtkTvChCommonBase.SB_VNET_ACTIVE | MtkTvChCommonBase.SB_VNET_VISIBLE, 0, 0, this.num_air)) {
            int nwMask = mtkTvChannelInfoBase.getNwMask();
            int i = MtkTvChCommonBase.SB_VNET_FAKE;
            if ((nwMask & i) != i && (nwMask & MtkTvChCommonBase.SB_VNET_VISIBLE) > 0) {
                this.list_air.add(mtkTvChannelInfoBase);
            }
        }
        return this.list_air;
    }

    public List<MtkTvChannelInfoBase> getCableChannelList() {
        new ArrayList();
        this.num_cable = this.chlst.getChannelCountByFilter(2, MtkTvChCommonBase.SB_VNET_ACTIVE | MtkTvChCommonBase.SB_VNET_VISIBLE);
        for (MtkTvChannelInfoBase mtkTvChannelInfoBase : this.chlst.getChannelListByFilter(2, MtkTvChCommonBase.SB_VNET_ACTIVE | MtkTvChCommonBase.SB_VNET_VISIBLE, 0, 0, this.num_cable)) {
            int nwMask = mtkTvChannelInfoBase.getNwMask();
            int i = MtkTvChCommonBase.SB_VNET_FAKE;
            if ((nwMask & i) != i && (nwMask & MtkTvChCommonBase.SB_VNET_VISIBLE) > 0) {
                this.list_cable.add(mtkTvChannelInfoBase);
            }
        }
        return this.list_cable;
    }

    public int getChannelIndex() {
        int i = 0;
        int i2 = 1;
        if (getTunerMode() == 0) {
            StringBuilder sbA = a.a("list air size = ");
            sbA.append(this.list_air.size());
            Log.i(HisenseTvAPIBase.TAG, sbA.toString());
            while (i < this.list_air.size()) {
                StringBuilder sbA2 = a.a("list_air i get id = ");
                sbA2.append(this.list_air.get(i).getChannelId());
                sbA2.append("channnel number = ");
                sbA2.append(this.list_air.get(i).getChannelNumber());
                sbA2.append("  chlst get id = ");
                MtkTvChannelListBase mtkTvChannelListBase = this.chlst;
                sbA2.append(MtkTvChannelListBase.getCurrentChannelId());
                Log.i(HisenseTvAPIBase.TAG, sbA2.toString());
                int channelId = this.list_air.get(i).getChannelId();
                MtkTvChannelListBase mtkTvChannelListBase2 = this.chlst;
                if (channelId == MtkTvChannelListBase.getCurrentChannelId()) {
                    Log.i(HisenseTvAPIBase.TAG, "i = " + i);
                    i2 = i;
                }
                i++;
            }
            return i2;
        }
        StringBuilder sbA3 = a.a("list cable size = ");
        sbA3.append(this.list_cable.size());
        Log.i(HisenseTvAPIBase.TAG, sbA3.toString());
        while (i < this.list_cable.size()) {
            StringBuilder sbA4 = a.a("list_cable i get id = ");
            sbA4.append(this.list_cable.get(i).getChannelId());
            sbA4.append("channnel number = ");
            sbA4.append(this.list_cable.get(i).getChannelNumber());
            sbA4.append("  chlst get id = ");
            MtkTvChannelListBase mtkTvChannelListBase3 = this.chlst;
            sbA4.append(MtkTvChannelListBase.getCurrentChannelId());
            Log.i(HisenseTvAPIBase.TAG, sbA4.toString());
            int channelId2 = this.list_cable.get(i).getChannelId();
            MtkTvChannelListBase mtkTvChannelListBase4 = this.chlst;
            if (channelId2 == MtkTvChannelListBase.getCurrentChannelId()) {
                Log.i(HisenseTvAPIBase.TAG, "i = " + i);
                i2 = i;
            }
            i++;
        }
        return i2;
    }

    public String getCurrentChannelNumberString() {
        String channelNumber = this.mMtkTvBanner.getChannelNumber();
        Log.i(HisenseTvAPIBase.TAG, "getChannelDisPlayName = " + channelNumber);
        return channelNumber == null ? "" : this.mMtkTvBanner.getChannelNumber();
    }

    public int getTunerMode() {
        StringBuilder sbA = a.a("getTunerMode = ");
        sbA.append(this.mMtkTvConfig.getConfigValue(MtkTvConfigTypeBase.CFG_BS_BS_SRC));
        Log.i(HisenseTvAPIBase.TAG, sbA.toString());
        return this.mMtkTvConfig.getConfigValue(MtkTvConfigTypeBase.CFG_BS_BS_SRC);
    }

    public void changeChannel(int i, int i2) {
        Log.i(HisenseTvAPIBase.TAG, "change channel = " + i);
        if (i2 == 0) {
            if (this.list_air == null && this.num_air == 0) {
                return;
            }
            for (MtkTvChannelInfoBase mtkTvChannelInfoBase : this.list_air) {
                StringBuilder sbA = a.a("air channel infobase number (i) = ");
                sbA.append(mtkTvChannelInfoBase.getChannelNumber());
                Log.i(HisenseTvAPIBase.TAG, sbA.toString());
                if (mtkTvChannelInfoBase.getChannelNumber() == i) {
                    StringBuilder sbA2 = a.a("air mtkTvChannelInfoBase.getChannelNumber() = ");
                    sbA2.append(mtkTvChannelInfoBase.getChannelNumber());
                    Log.i(HisenseTvAPIBase.TAG, sbA2.toString());
                    this.broadbase.channelSelect(mtkTvChannelInfoBase, false);
                    return;
                }
            }
            return;
        }
        if (this.list_cable == null && this.num_cable == 0) {
            return;
        }
        for (MtkTvChannelInfoBase mtkTvChannelInfoBase2 : this.list_cable) {
            StringBuilder sbA3 = a.a("cable channel infobase number (i) = ");
            sbA3.append(mtkTvChannelInfoBase2.getChannelNumber());
            Log.i(HisenseTvAPIBase.TAG, sbA3.toString());
            if (mtkTvChannelInfoBase2.getChannelNumber() == i) {
                StringBuilder sbA4 = a.a("cable mtkTvChannelInfoBase.getChannelNumber() = ");
                sbA4.append(mtkTvChannelInfoBase2.getChannelNumber());
                Log.i(HisenseTvAPIBase.TAG, sbA4.toString());
                this.broadbase.channelSelect(mtkTvChannelInfoBase2, false);
                return;
            }
        }
    }
}
