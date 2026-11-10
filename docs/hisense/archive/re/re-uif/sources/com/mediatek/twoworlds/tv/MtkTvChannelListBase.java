package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.common.MtkTvExceptionBase;
import com.mediatek.twoworlds.tv.model.MtkTvATSCChannelInfo;
import com.mediatek.twoworlds.tv.model.MtkTvAnalogChannelInfo;
import com.mediatek.twoworlds.tv.model.MtkTvChannelInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvChannelQuery;
import com.mediatek.twoworlds.tv.model.MtkTvDvbChannelInfo;
import com.mediatek.twoworlds.tv.model.MtkTvFavoritelistInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvISDBChannelInfo;
import com.mediatek.twoworlds.tv.model.TvProviderChannelInfoBase;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvChannelListBase {
    public static final int CHLST_ITERATE_DIR_FROM_FIRST = 0;
    public static final int CHLST_ITERATE_DIR_FROM_LAST = 1;
    public static final int CHLST_ITERATE_DIR_NEXT = 2;
    public static final int CHLST_ITERATE_DIR_PREV = 3;
    public static final int CHLST_OPERATOR_ADD = 0;
    public static final int CHLST_OPERATOR_DEL = 2;
    public static final int CHLST_OPERATOR_MOD = 1;
    public static final int CHLST_RET_FAIL = -1;
    public static final int CHLST_RET_OK = 0;
    static final String TAG = "MtkTvChannelList";

    public void bookChannel(MtkTvChannelInfoBase mtkTvChannelInfoBase, long j) {
    }

    public static int getCurrentChannelId() {
        return TVNativeWrapper.getCurrentChannelId_native();
    }

    public static MtkTvChannelInfoBase getCurrentChannel() {
        MtkTvChannelInfoBase mtkTvISDBChannelInfo;
        Log.d(TAG, "Enter getCurrentChannel:\n");
        int currentChannelId_native = TVNativeWrapper.getCurrentChannelId_native();
        if (currentChannelId_native != -1) {
            int channelType_native = TVNativeWrapper.getChannelType_native(currentChannelId_native);
            if (1 == channelType_native) {
                mtkTvISDBChannelInfo = new MtkTvAnalogChannelInfo();
            } else if (2 == channelType_native) {
                mtkTvISDBChannelInfo = new MtkTvDvbChannelInfo();
            } else if (3 == channelType_native) {
                mtkTvISDBChannelInfo = new MtkTvATSCChannelInfo();
            } else if (5 == channelType_native) {
                mtkTvISDBChannelInfo = new MtkTvISDBChannelInfo();
            } else {
                Log.e(TAG, "Invalid channel type--" + channelType_native + "!\n");
            }
            Log.d(TAG, "channelId = " + currentChannelId_native + ", channelType = " + channelType_native + "...\n");
            if (TVNativeWrapper.getCurrentChannel_native(mtkTvISDBChannelInfo) != 0) {
                Log.e(TAG, "TVNativeWrapper.getCurrentChannel_native failed!\n");
            } else {
                Log.i(TAG, mtkTvISDBChannelInfo.toString());
                return mtkTvISDBChannelInfo;
            }
        }
        MtkTvChannelInfoBase mtkTvChannelInfoBase = new MtkTvChannelInfoBase();
        mtkTvChannelInfoBase.setChannelId(-1);
        return mtkTvChannelInfoBase;
    }

    public static int setCurrentChannel(MtkTvChannelInfoBase mtkTvChannelInfoBase) {
        return TVNativeWrapper.setCurrentChannel_native(mtkTvChannelInfoBase.getChannelId());
    }

    public static int set2ndCurrentChannelBySvlId(MtkTvChannelInfoBase mtkTvChannelInfoBase) {
        return TVNativeWrapper.set2ndCurrentChannelBySvlId_native(mtkTvChannelInfoBase.getSvlId(), mtkTvChannelInfoBase.getChannelId());
    }

    public static int setCurrentChannelBySvlId(MtkTvChannelInfoBase mtkTvChannelInfoBase) {
        return TVNativeWrapper.setCurrentChannelBySvlId_native(mtkTvChannelInfoBase.getSvlId(), mtkTvChannelInfoBase.getChannelId());
    }

    public MtkTvChannelInfoBase getCurrentChannelFromNav(int i) {
        Log.d(TAG, "Enter getCurrentChannelFromNav: winId = " + i);
        return TVNativeWrapper.getCurrentChannelFromNav_native(i);
    }

    public int getFavoriteIdx(int i, int i2, int i3, int i4) {
        Log.d(TAG, "Enter getFavoriteIdx(" + i + "," + i2 + "," + i3 + "," + i4 + ")\n");
        int favoriteIdx_native = TVNativeWrapper.getFavoriteIdx_native(i, i2, i3, i4);
        if (favoriteIdx_native < 0) {
            return -1;
        }
        return favoriteIdx_native;
    }

    public List<MtkTvChannelInfoBase> getFavoriteListByFilter(int i, int i2, int i3, boolean z, int i4, int i5, int i6) {
        Log.d(TAG, "Enter getChannelList(" + i + "," + i2 + "," + i3 + "," + i4 + "," + i5 + i6 + ")\n");
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            int channelListByFavoriteIdx_native = TVNativeWrapper.getChannelListByFavoriteIdx_native(i, i2, i3, z, i4, i5, i6, arrayList);
            if (channelListByFavoriteIdx_native != 0) {
                Log.e(TAG, "TVNativeWrapper.getChannelListByFavoriteIdx_native failed! return " + channelListByFavoriteIdx_native + ".\n");
            }
        }
        Log.d(TAG, "leave getChannelList(), size=" + arrayList.size() + "\n");
        return arrayList;
    }

    public List<MtkTvChannelInfoBase> getChannelListByFilter(int i, int i2, int i3, int i4, int i5) {
        Log.d(TAG, "Enter getChannelListByFilter(" + i + "," + i2 + "," + i3 + "," + i4 + "," + i5 + ")\n");
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            int channelListByFilter_native = TVNativeWrapper.getChannelListByFilter_native(i, i2, i3, i4, i5, arrayList);
            if (channelListByFilter_native != 0) {
                Log.e(TAG, "TVNativeWrapper.getChannelListByFilter_native failed! return " + channelListByFilter_native + ".\n");
            }
        }
        Log.d(TAG, "leave getChannelListByFilter(), size=" + arrayList.size() + "\n");
        return arrayList;
    }

    public int getChannelCountByFilter(int i, int i2) {
        Log.d(TAG, "Enter getChannelCountByFilter(" + i + "," + i2 + ")\n");
        int channelCountByFilter_native = TVNativeWrapper.getChannelCountByFilter_native(i, i2);
        if (channelCountByFilter_native < 0) {
            return -1;
        }
        return channelCountByFilter_native;
    }

    public List<MtkTvChannelInfoBase> getChannelListByQueryInfo(int i, MtkTvChannelQuery mtkTvChannelQuery) {
        Log.d(TAG, "Enter getChannelListByQueryInfo(" + i + "," + mtkTvChannelQuery + ")\n");
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            int channelListByQueryInfo_native = TVNativeWrapper.getChannelListByQueryInfo_native(i, mtkTvChannelQuery, arrayList, 0);
            if (channelListByQueryInfo_native != 0) {
                Log.e(TAG, "TVNativeWrapper.getChannelListByQueryInfo_native failed! return " + channelListByQueryInfo_native + ".\n");
            }
        }
        return arrayList;
    }

    public List<MtkTvChannelInfoBase> getChannelListByQueryInfo(int i, MtkTvChannelQuery mtkTvChannelQuery, int i2) {
        Log.d(TAG, "Enter getChannelListByQueryInfo(" + i + "," + mtkTvChannelQuery + "," + i2 + ")\n");
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            int channelListByQueryInfo_native = TVNativeWrapper.getChannelListByQueryInfo_native(i, mtkTvChannelQuery, arrayList, i2);
            if (channelListByQueryInfo_native != 0) {
                Log.e(TAG, "TVNativeWrapper.getChannelListByQueryInfo_native failed! return " + channelListByQueryInfo_native + ".\n");
            }
        }
        return arrayList;
    }

    public int getChannelCountByQueryInfo(int i, MtkTvChannelQuery mtkTvChannelQuery) {
        Log.d(TAG, "Enter getChannelCountByQueryInfo(" + i + "," + mtkTvChannelQuery + ")\n");
        int channelCountByQueryInfo_native = TVNativeWrapper.getChannelCountByQueryInfo_native(i, mtkTvChannelQuery);
        if (channelCountByQueryInfo_native >= 0) {
            return channelCountByQueryInfo_native;
        }
        Log.e(TAG, "TVNativeWrapper.getChannelCountByQueryInfo_native failed! return " + channelCountByQueryInfo_native + ".\n");
        return -1;
    }

    public int getChannelCountByBrdcst(int i, int i2, int i3) {
        Log.d(TAG, "Enter getChannelCountBybrdcst(" + i + "," + i2 + "," + i3 + ")\n");
        int channelCountByBrdcst_native = TVNativeWrapper.getChannelCountByBrdcst_native(i, i2, i3);
        if (channelCountByBrdcst_native >= 0) {
            return channelCountByBrdcst_native;
        }
        Log.e(TAG, "TVNativeWrapper.getChannelCountByQueryInfo_native failed! return " + channelCountByBrdcst_native + ".\n");
        return -1;
    }

    public List<MtkTvChannelInfoBase> getChannelListByBrdcst(int i, int i2, int i3, int i4) {
        Log.d(TAG, "Enter getChannelListByBrdcst(" + i + "," + i2 + "," + i3 + "," + i4 + ")\n");
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            int channelListByBrdcst_native = TVNativeWrapper.getChannelListByBrdcst_native(i, i2, i3, i4, arrayList);
            if (channelListByBrdcst_native != 0) {
                Log.e(TAG, "TVNativeWrapper.getChannelListByFilter_native failed! return " + channelListByBrdcst_native + ".\n");
            }
        }
        Log.d(TAG, "leave getChannelListByBrdcst(), size=" + arrayList.size() + "\n");
        return arrayList;
    }

    public int getChannelCountByMask(int i, int i2, int i3) {
        Log.d(TAG, "Enter getChannelCountByMask(" + i + "," + i2 + "," + i3 + ")\n");
        int channelCountByMask_native = TVNativeWrapper.getChannelCountByMask_native(i, i2, i3, 0);
        if (channelCountByMask_native < 0) {
            return -1;
        }
        return channelCountByMask_native;
    }

    public int getChannelCountByMaskAndSat(int i, int i2, int i3, int i4) {
        Log.d(TAG, "Enter getChannelCountByMaskAndSat(" + i + "," + i2 + "," + i3 + "," + i4 + ")\n");
        int channelCountByMask_native = TVNativeWrapper.getChannelCountByMask_native(i, i2, i3, i4);
        if (channelCountByMask_native < 0) {
            return -1;
        }
        return channelCountByMask_native;
    }

    public List<MtkTvChannelInfoBase> getChannelListByMask(int i, int i2, int i3, int i4, int i5, int i6) {
        Log.d(TAG, "Enter getChannelListByMask(" + i + "," + i2 + "," + i3 + "," + i4 + "," + i5 + "," + i6 + ")\n");
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            int channelListByMask_native = TVNativeWrapper.getChannelListByMask_native(i, i2, i3, 0, i4, i5, i6, arrayList);
            if (channelListByMask_native != 0) {
                Log.e(TAG, "TVNativeWrapper.getChannelListByMask_native failed! return " + channelListByMask_native + ".\n");
            }
        }
        if (i4 != 1 && i4 != 3) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList2.add((MtkTvChannelInfoBase) arrayList.get(size));
        }
        return arrayList2;
    }

    public int slideForOnePartChannelId(int i, int i2, int i3) {
        Log.d(TAG, "Enter slideForOneChannelId(" + i + "," + i2 + "," + i3 + ")\n");
        return TVNativeWrapper.slideForOnePartChannelId_native(i, i2, i3);
    }

    public List<MtkTvChannelInfoBase> getChannelListByMaskAndSat(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        Log.d(TAG, "Enter getChannelListByMaskAndSat(" + i + "," + i2 + "," + i3 + "," + i4 + "," + i5 + "," + i6 + "," + i7 + ")\n");
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            int channelListByMask_native = TVNativeWrapper.getChannelListByMask_native(i, i2, i3, i4, i5, i6, i7, arrayList);
            if (channelListByMask_native != 0) {
                Log.e(TAG, "TVNativeWrapper.getChannelListByMask_native failed! return " + channelListByMask_native + ".\n");
            }
        }
        if (i5 != 1 && i5 != 3) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList2.add((MtkTvChannelInfoBase) arrayList.get(size));
        }
        return arrayList2;
    }

    public int setChannelList(int i, List<MtkTvChannelInfoBase> list) {
        Log.d(TAG, "Enter setChannelList:\n");
        if (list == null) {
            Log.e(TAG, "The channelsToSet is null!\n");
            return -1;
        }
        int size = list.size();
        if (size == 0) {
            Log.e(TAG, "There is no any channel in 'channelsToSet'!\n");
            return -1;
        }
        if (i > 2 || i < 0) {
            Log.e(TAG, "Invalid channelOperator " + i + "!\n");
            return -1;
        }
        MtkTvChannelInfoBase mtkTvChannelInfoBase = list.get(0);
        Log.d(TAG, "" + size + " channels will be " + i + " in 'channelList'!\n");
        StringBuilder sb = new StringBuilder();
        sb.append("svlId = ");
        sb.append(mtkTvChannelInfoBase.getSvlId());
        sb.append("...\n");
        Log.d(TAG, sb.toString());
        return TVNativeWrapper.setChannelList_native(i, mtkTvChannelInfoBase.getSvlId(), list) == 0 ? 0 : -1;
    }

    public int setChannelListWithoutNotify(int i, List<MtkTvChannelInfoBase> list) {
        Log.d(TAG, "Enter setChannelListWithoutNotify:\n");
        if (list == null) {
            Log.e(TAG, "The channelsToSet is null!\n");
            return -1;
        }
        int size = list.size();
        if (size == 0) {
            Log.e(TAG, "There is no any channel in 'channelsToSet'!\n");
            return -1;
        }
        if (i > 2 || i < 0) {
            Log.e(TAG, "Invalid channelOperator " + i + "!\n");
            return -1;
        }
        MtkTvChannelInfoBase mtkTvChannelInfoBase = list.get(0);
        Log.d(TAG, "" + size + " channels will be " + i + " in 'channelList'!\n");
        StringBuilder sb = new StringBuilder();
        sb.append("svlId = ");
        sb.append(mtkTvChannelInfoBase.getSvlId());
        sb.append("...\n");
        Log.d(TAG, sb.toString());
        return TVNativeWrapper.setChannelListWithoutNotify_native(i, mtkTvChannelInfoBase.getSvlId(), list) == 0 ? 0 : -1;
    }

    public static int cleanChannelList(int i) {
        return TVNativeWrapper.cleanChannelList_native(i, true);
    }

    public static int cleanChannelList(int i, boolean z) {
        return TVNativeWrapper.cleanChannelList_native(i, z);
    }

    public static int createSnapshot(int i) {
        return TVNativeWrapper.createSnapshot_native(i);
    }

    public static int restoreSnapshot(int i) {
        return TVNativeWrapper.restoreSnapshot_native(i);
    }

    public static int freeSnapshot(int i) {
        return TVNativeWrapper.freeSnapshot_native(i);
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0052 A[LOOP:0: B:22:0x004c->B:24:0x0052, LOOP_END] */
    /* JADX WARN: Instruction removed from duplicated block: B:24:0x0052, please report this as an issue */
    public List<MtkTvFavoritelistInfoBase> getFavoritelistByFilter() throws Throwable {
        Log.d(TAG, "+ getFavoritelistByFilter.");
        ArrayList arrayList = new ArrayList();
        int favoritelistByFilter_native = 0;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "getFavoritelistByFilter_native begin");
                        favoritelistByFilter_native = TVNativeWrapper.getFavoritelistByFilter_native(arrayList);
                        if (favoritelistByFilter_native != 0) {
                            throw new MtkTvExceptionBase(favoritelistByFilter_native, "getFavoritelistByFilter_native fail");
                        }
                        Log.d(TAG, "getFavoritelistByFilter: count=" + arrayList.size());
                        while (favoritelistByFilter_native < arrayList.size()) {
                            Log.d(TAG, " getFavoritelistByFilter: svlId=" + ((MtkTvFavoritelistInfoBase) arrayList.get(favoritelistByFilter_native)).getSvlId() + ",svlRecId=" + ((MtkTvFavoritelistInfoBase) arrayList.get(favoritelistByFilter_native)).getSvlRecId() + ",ChannelId=" + ((MtkTvFavoritelistInfoBase) arrayList.get(favoritelistByFilter_native)).getChannelId() + ",ChannelName=" + ((MtkTvFavoritelistInfoBase) arrayList.get(favoritelistByFilter_native)).getChannelName());
                            favoritelistByFilter_native++;
                        }
                        Log.d(TAG, "- getFavoritelistByFilter. ret=" + favoritelistByFilter_native);
                        return arrayList;
                    } catch (Throwable th) {
                        th = th;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e) {
                            e = e;
                            e.printStackTrace();
                            Log.d(TAG, "getFavoritelistByFilter: count=" + arrayList.size());
                            while (favoritelistByFilter_native < arrayList.size()) {
                                Log.d(TAG, " getFavoritelistByFilter: svlId=" + ((MtkTvFavoritelistInfoBase) arrayList.get(favoritelistByFilter_native)).getSvlId() + ",svlRecId=" + ((MtkTvFavoritelistInfoBase) arrayList.get(favoritelistByFilter_native)).getSvlRecId() + ",ChannelId=" + ((MtkTvFavoritelistInfoBase) arrayList.get(favoritelistByFilter_native)).getChannelId() + ",ChannelName=" + ((MtkTvFavoritelistInfoBase) arrayList.get(favoritelistByFilter_native)).getChannelName());
                                favoritelistByFilter_native++;
                            }
                            Log.d(TAG, "- getFavoritelistByFilter. ret=" + favoritelistByFilter_native);
                            return arrayList;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e2) {
                e = e2;
                e.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public int addFavoritelistChannel() {
        Log.d(TAG, "+ addFavoritelistChannel.");
        int iAddFavoritelistChannel_native = 0;
        try {
            synchronized (this) {
                Log.d(TAG, "addFavoritelistChannel_native begin");
                iAddFavoritelistChannel_native = TVNativeWrapper.addFavoritelistChannel_native();
                if (iAddFavoritelistChannel_native != 0) {
                    throw new MtkTvExceptionBase(iAddFavoritelistChannel_native, "addFavoritelistChannel_native fail");
                }
            }
        } catch (MtkTvExceptionBase e) {
            e.printStackTrace();
        }
        Log.d(TAG, "- addFavoritelistChannel. ret=" + iAddFavoritelistChannel_native);
        return iAddFavoritelistChannel_native;
    }

    public int addFavoritelistChannelByIndex(int i) throws Throwable {
        int iAddFavoritelistChannelByIndex_native;
        Log.d(TAG, "+ addFavoritelistChannelByIndex: index=" + i);
        int i2 = 0;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "addFavoritelistChannelByIndex_native begin");
                        iAddFavoritelistChannelByIndex_native = TVNativeWrapper.addFavoritelistChannelByIndex_native(i);
                        if (iAddFavoritelistChannelByIndex_native != 0) {
                            throw new MtkTvExceptionBase(iAddFavoritelistChannelByIndex_native, "addFavoritelistChannelByIndex_native fail");
                        }
                        Log.d(TAG, "- addFavoritelistChannelByIndex. ret=" + iAddFavoritelistChannelByIndex_native);
                        return iAddFavoritelistChannelByIndex_native;
                    } catch (Throwable th) {
                        th = th;
                        i = 0;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e) {
                            e = e;
                            i2 = i;
                            e.printStackTrace();
                            iAddFavoritelistChannelByIndex_native = i2;
                            Log.d(TAG, "- addFavoritelistChannelByIndex. ret=" + iAddFavoritelistChannelByIndex_native);
                            return iAddFavoritelistChannelByIndex_native;
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (MtkTvExceptionBase e2) {
            e = e2;
            e.printStackTrace();
            iAddFavoritelistChannelByIndex_native = i2;
        }
    }

    public int removeFavoritelistChannelByIndexWithoutShowFavIcon(int i) throws Throwable {
        int iRemoveFavoritelistChannelByIndexWithoutShowFavIcon_native;
        Log.d(TAG, "+ removeFavoritelistChannelByIndexWithoutShowFavIcon. index=" + i);
        int i2 = 0;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "removeFavoritelistChannelByIndexWithoutShowFavIcon_native begin");
                        iRemoveFavoritelistChannelByIndexWithoutShowFavIcon_native = TVNativeWrapper.removeFavoritelistChannelByIndexWithoutShowFavIcon_native(i);
                        if (iRemoveFavoritelistChannelByIndexWithoutShowFavIcon_native != 0) {
                            throw new MtkTvExceptionBase(iRemoveFavoritelistChannelByIndexWithoutShowFavIcon_native, "removeFavoritelistChannelByIndexWithoutShowFavIcon_native fail");
                        }
                        Log.d(TAG, "- removeFavoritelistChannelByIndexWithoutShowFavIcon. ret=" + iRemoveFavoritelistChannelByIndexWithoutShowFavIcon_native);
                        return iRemoveFavoritelistChannelByIndexWithoutShowFavIcon_native;
                    } catch (Throwable th) {
                        th = th;
                        i = 0;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e) {
                            e = e;
                            i2 = i;
                            e.printStackTrace();
                            iRemoveFavoritelistChannelByIndexWithoutShowFavIcon_native = i2;
                            Log.d(TAG, "- removeFavoritelistChannelByIndexWithoutShowFavIcon. ret=" + iRemoveFavoritelistChannelByIndexWithoutShowFavIcon_native);
                            return iRemoveFavoritelistChannelByIndexWithoutShowFavIcon_native;
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (MtkTvExceptionBase e2) {
            e = e2;
            e.printStackTrace();
            iRemoveFavoritelistChannelByIndexWithoutShowFavIcon_native = i2;
        }
    }

    public int removeFavoritelistChannel(int i) throws Throwable {
        int iRemoveFavoritelistChannel_native;
        Log.d(TAG, "+ removeFavoritelistChannel. index=" + i);
        int i2 = 0;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "removeFavoritelistChannel_native begin");
                        iRemoveFavoritelistChannel_native = TVNativeWrapper.removeFavoritelistChannel_native(i);
                        if (iRemoveFavoritelistChannel_native != 0) {
                            throw new MtkTvExceptionBase(iRemoveFavoritelistChannel_native, "removeFavoritelistChannel_native fail");
                        }
                        Log.d(TAG, "- removeFavoritelistChannel. ret=" + iRemoveFavoritelistChannel_native);
                        return iRemoveFavoritelistChannel_native;
                    } catch (Throwable th) {
                        th = th;
                        i = 0;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e) {
                            e = e;
                            i2 = i;
                            e.printStackTrace();
                            iRemoveFavoritelistChannel_native = i2;
                            Log.d(TAG, "- removeFavoritelistChannel. ret=" + iRemoveFavoritelistChannel_native);
                            return iRemoveFavoritelistChannel_native;
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (MtkTvExceptionBase e2) {
            e = e2;
            e.printStackTrace();
            iRemoveFavoritelistChannel_native = i2;
        }
    }

    public int storeFavoritelistChannel() {
        Log.d(TAG, "+ storeFavoritelistChannel.");
        int iStoreFavoritelistChannel_native = 0;
        try {
            synchronized (this) {
                Log.d(TAG, "storeFavoritelistChannel_native begin");
                iStoreFavoritelistChannel_native = TVNativeWrapper.storeFavoritelistChannel_native();
                if (iStoreFavoritelistChannel_native != 0) {
                    throw new MtkTvExceptionBase(iStoreFavoritelistChannel_native, "storeFavoritelistChannel_native fail");
                }
            }
        } catch (MtkTvExceptionBase e) {
            e.printStackTrace();
        }
        Log.d(TAG, "- storeFavoritelistChannel. ret=" + iStoreFavoritelistChannel_native);
        return iStoreFavoritelistChannel_native;
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x003a */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x003b, code lost:
    
        r5 = th;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int swapFavoritelistByIndex(int r4, int r5) throws java.lang.Throwable {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "+ swapFavoritelistByIndex. index1="
            r0.append(r1)
            r0.append(r4)
            java.lang.String r1 = ", index2="
            r0.append(r1)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "MtkTvChannelList"
            android.util.Log.d(r1, r0)
            r0 = 0
            monitor-enter(r3)     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L3d
            java.lang.String r1 = "MtkTvChannelList"
            java.lang.String r2 = "swapFavoritelistByIndex_native begin"
            android.util.Log.d(r1, r2)     // Catch: java.lang.Throwable -> L3a
            int r4 = com.mediatek.twoworlds.tv.TVNativeWrapper.swapFavoritelistByIndex_native(r4, r5)     // Catch: java.lang.Throwable -> L3a
            if (r4 != 0) goto L2f
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L37
            goto L42
        L2f:
            com.mediatek.twoworlds.tv.common.MtkTvExceptionBase r5 = new com.mediatek.twoworlds.tv.common.MtkTvExceptionBase     // Catch: java.lang.Throwable -> L37
            java.lang.String r0 = "swapFavoritelistByIndex_native fail"
            r5.<init>(r4, r0)     // Catch: java.lang.Throwable -> L37
            throw r5     // Catch: java.lang.Throwable -> L37
        L37:
            r5 = move-exception
            r0 = r4
            goto L3b
        L3a:
            r5 = move-exception
        L3b:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L3a
            throw r5     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L3d
        L3d:
            r3 = move-exception
            r3.printStackTrace()
            r4 = r0
        L42:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "- swapFavoritelistByIndex. ret="
            r3.append(r5)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r5 = "MtkTvChannelList"
            android.util.Log.d(r5, r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.twoworlds.tv.MtkTvChannelListBase.swapFavoritelistByIndex(int, int):int");
    }

    public List<TvProviderChannelInfoBase> getTvproviderBySvlId(int i) {
        Log.d(TAG, "Enter getTvproviderBySvlId \n");
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            int tvproviderBySvlId_native = TVNativeWrapper.getTvproviderBySvlId_native(i, arrayList);
            if (tvproviderBySvlId_native != 0) {
                Log.e(TAG, "TVNativeWrapper.getTvproviderBySvlId_native failed! return " + tvproviderBySvlId_native + ".\n");
            }
        }
        Log.d(TAG, "leave getTvproviderBySvlId() \n");
        return arrayList;
    }

    public List<TvProviderChannelInfoBase> getAllTvprovider() {
        Log.d(TAG, "Enter getAllTvprovider\n");
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            int allTvprovider_native = TVNativeWrapper.getAllTvprovider_native(arrayList);
            if (allTvprovider_native != 0) {
                Log.e(TAG, "TVNativeWrapper.getAllTvprovider_native failed! return " + allTvprovider_native + ".\n");
            }
        }
        Log.d(TAG, "leave getAllTvprovider() \n");
        return arrayList;
    }

    public TvProviderChannelInfoBase getTvproviderBySvlRecId(int i, int i2) {
        Log.d(TAG, "Enter getTvproviderBySvlRecId\n");
        TvProviderChannelInfoBase tvProviderChannelInfoBase = new TvProviderChannelInfoBase();
        synchronized (this) {
            int tvproviderBySvlRecId_native = TVNativeWrapper.getTvproviderBySvlRecId_native(i, i2, tvProviderChannelInfoBase);
            if (tvproviderBySvlRecId_native != 0) {
                Log.e(TAG, "TVNativeWrapper.getTvproviderBySvlRecId_native failed! return " + tvproviderBySvlRecId_native + ".\n");
            }
        }
        Log.d(TAG, "leave getTvproviderBySvlRecId() \n");
        return tvProviderChannelInfoBase;
    }

    public MtkTvChannelInfoBase getChannelInfoBySvlRecId(int i, int i2) {
        MtkTvChannelInfoBase channelInfoBySvlRecId_native;
        new MtkTvChannelInfoBase();
        synchronized (this) {
            channelInfoBySvlRecId_native = TVNativeWrapper.getChannelInfoBySvlRecId_native(i, i2);
        }
        return channelInfoBySvlRecId_native;
    }

    public int deleteChannelByBrdcstType(int i, int i2) {
        Log.d(TAG, "Enter deleteChannelByBrdcstType(svlId:%d, brdcstType:%d).\n");
        return TVNativeWrapper.deleteChannelByBrdcstType_native(i, i2, false);
    }

    public int deleteChannelByBrdcstType(int i, int i2, boolean z) {
        Log.d(TAG, "Enter deleteChannelByBrdcstType(svlId:%d, brdcstType:%d).\n");
        return TVNativeWrapper.deleteChannelByBrdcstType_native(i, i2, z);
    }

    public boolean isChannelNumberExsit(int i, int i2) {
        Log.d(TAG, "Enter isChannelNumberExsit(svlId:%d, ch_num:%d).\n");
        return TVNativeWrapper.isChannelNumberExsit_native(i, i2);
    }

    public List<TvProviderChannelInfoBase> getTvproviderOcl() {
        Log.d(TAG, "Enter getTvproviderOcl \n");
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            int tvproviderOcl_native = TVNativeWrapper.getTvproviderOcl_native(arrayList);
            if (tvproviderOcl_native != 0) {
                Log.e(TAG, "TVNativeWrapper.getTvproviderOcl_native failed! return " + tvproviderOcl_native + ".\n");
            }
        }
        Log.d(TAG, "leave getTvproviderOcl() \n");
        return arrayList;
    }

    public int oneChannelListArrange(int i, int i2) {
        int iOneChannelListArrange_native;
        Log.d(TAG, "Enter oneChannelListArrange, svlIdT=" + i + ", svlIdC=" + i2);
        synchronized (this) {
            iOneChannelListArrange_native = TVNativeWrapper.oneChannelListArrange_native(i, i2);
            if (iOneChannelListArrange_native != 0) {
                Log.e(TAG, "TVNativeWrapper.oneChannelListArrange_native failed! return " + iOneChannelListArrange_native + ".\n");
            }
        }
        Log.d(TAG, "leave oneChannelListArrange() \n");
        return iOneChannelListArrange_native;
    }

    public int getChannelListMode() {
        int channelListMode_native;
        Log.d(TAG, "Enter getChannelListMode. \n");
        synchronized (this) {
            channelListMode_native = TVNativeWrapper.getChannelListMode_native();
            if (channelListMode_native < 0) {
                Log.e(TAG, "TVNativeWrapper.getChannelListMode_native failed! return " + channelListMode_native + ".\n");
                channelListMode_native = -1;
            }
        }
        Log.d(TAG, "leave getChannelListMode() \n");
        return channelListMode_native;
    }

    public int getChannelPumpVer(int i) {
        int channelPumpVer_native;
        Log.d(TAG, "Enter getChannelPumpVer. svlId = " + i);
        synchronized (this) {
            channelPumpVer_native = TVNativeWrapper.getChannelPumpVer_native(i);
            if (channelPumpVer_native < 0) {
                Log.e(TAG, "TVNativeWrapper.getChannelPumpVer_native failed! return " + channelPumpVer_native + ".\n");
                channelPumpVer_native = -1;
            }
        }
        Log.d(TAG, "leave getChannelPumpVer() svlId = " + i + ", pumpVersion = " + channelPumpVer_native);
        return channelPumpVer_native;
    }

    public int setChannelPumpVer(int i, int i2) {
        int channelPumpVer_native;
        Log.d(TAG, "Enter setChannelPumpVer. svlId = " + i + ",pumpVer = " + i2);
        synchronized (this) {
            channelPumpVer_native = TVNativeWrapper.setChannelPumpVer_native(i, i2);
            if (channelPumpVer_native < 0) {
                Log.e(TAG, "TVNativeWrapper.setChannelPumpVer_native failed! return " + channelPumpVer_native + ".\n");
                channelPumpVer_native = -1;
            }
        }
        Log.d(TAG, "leave setChannelPumpVer(). svlId = " + i + ",pumpVer = " + i2);
        return channelPumpVer_native;
    }

    public int oneChannelListAddToMax(int i, int i2) {
        int iOneChannelListAddToMax_native;
        Log.d(TAG, "Enter oneChannelListAddToMax. svlId = " + i + ",svlRecId = " + i2);
        synchronized (this) {
            iOneChannelListAddToMax_native = TVNativeWrapper.oneChannelListAddToMax_native(i, i2);
            if (iOneChannelListAddToMax_native < 0) {
                Log.e(TAG, "TVNativeWrapper.oneChannelListAddToMax_native failed! return " + iOneChannelListAddToMax_native + ".\n");
                iOneChannelListAddToMax_native = -1;
            }
        }
        Log.d(TAG, "leave oneChannelListAddToMax(). svlId = " + i + ",svlRecId = " + i2);
        return iOneChannelListAddToMax_native;
    }

    public int swapChannelInfo(int i, int i2, int i3) {
        int iSwapChannelInfo_native;
        Log.d(TAG, "Enter swapChannelInfo \n");
        synchronized (this) {
            iSwapChannelInfo_native = TVNativeWrapper.swapChannelInfo_native(i, i2, i3);
            if (iSwapChannelInfo_native != 0) {
                Log.e(TAG, "TVNativeWrapper.swapChannelInfo_native failed! return " + iSwapChannelInfo_native + ".\n");
            }
        }
        Log.d(TAG, "leave swapChannelInfo() \n");
        return iSwapChannelInfo_native;
    }

    public int favoriteListAddTail(int i, int i2, int i3) {
        int iFavoriteListAddTail_native;
        Log.d(TAG, "Enter favoriteListAddTail \n");
        synchronized (this) {
            iFavoriteListAddTail_native = TVNativeWrapper.favoriteListAddTail_native(i, i2, i3);
            if (iFavoriteListAddTail_native != 0) {
                Log.e(TAG, "TVNativeWrapper.favoriteListAddTail failed! return " + iFavoriteListAddTail_native + ".\n");
            }
        }
        Log.d(TAG, "leave swapChannelInfo() \n");
        return iFavoriteListAddTail_native;
    }

    public int favoriteListInsertMove(int i, int i2, int i3, int i4) {
        int iFavoriteListInsertMove_native;
        Log.d(TAG, "Enter favoriteListInsertMove \n");
        synchronized (this) {
            iFavoriteListInsertMove_native = TVNativeWrapper.favoriteListInsertMove_native(i, i2, i3, i4);
            if (iFavoriteListInsertMove_native != 0) {
                Log.e(TAG, "TVNativeWrapper.favoriteListInsertMove failed! return " + iFavoriteListInsertMove_native + ".\n");
            }
        }
        Log.d(TAG, "leave favoriteListInsertMove() \n");
        return iFavoriteListInsertMove_native;
    }
}
