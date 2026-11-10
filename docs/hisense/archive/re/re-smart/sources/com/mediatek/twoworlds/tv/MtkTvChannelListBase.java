package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.common.MtkTvExceptionBase;
import com.mediatek.twoworlds.tv.model.MtkTvATSCChannelInfo;
import com.mediatek.twoworlds.tv.model.MtkTvAnalogChannelInfo;
import com.mediatek.twoworlds.tv.model.MtkTvChannelInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvChannelQuery;
import com.mediatek.twoworlds.tv.model.MtkTvDvbChannelInfo;
import com.mediatek.twoworlds.tv.model.MtkTvISDBChannelInfo;
import com.mediatek.twoworlds.tv.model.TvProviderChannelInfoBase;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
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
    public static final String TAG = "MtkTvChannelList";

    public static int cleanChannelList(int i) {
        return TVNativeWrapper.cleanChannelList_native(i, true);
    }

    public static int createSnapshot(int i) {
        return TVNativeWrapper.createSnapshot_native(i);
    }

    public static int freeSnapshot(int i) {
        return TVNativeWrapper.freeSnapshot_native(i);
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
                a.b("Invalid channel type--", channelType_native, "!\n", TAG);
            }
            Log.d(TAG, a.a("channelId = ", currentChannelId_native, ", channelType = ", channelType_native, "...\n"));
            if (TVNativeWrapper.getCurrentChannel_native(mtkTvISDBChannelInfo) == 0) {
                Log.i(TAG, mtkTvISDBChannelInfo.toString());
                return mtkTvISDBChannelInfo;
            }
            Log.e(TAG, "TVNativeWrapper.getCurrentChannel_native failed!\n");
        }
        MtkTvChannelInfoBase mtkTvChannelInfoBase = new MtkTvChannelInfoBase();
        mtkTvChannelInfoBase.setChannelId(-1);
        return mtkTvChannelInfoBase;
    }

    public static int getCurrentChannelId() {
        return TVNativeWrapper.getCurrentChannelId_native();
    }

    public static int restoreSnapshot(int i) {
        return TVNativeWrapper.restoreSnapshot_native(i);
    }

    public static int set2ndCurrentChannelBySvlId(MtkTvChannelInfoBase mtkTvChannelInfoBase) {
        return TVNativeWrapper.set2ndCurrentChannelBySvlId_native(mtkTvChannelInfoBase.getSvlId(), mtkTvChannelInfoBase.getChannelId());
    }

    public static int setCurrentChannel(MtkTvChannelInfoBase mtkTvChannelInfoBase) {
        return TVNativeWrapper.setCurrentChannel_native(mtkTvChannelInfoBase.getChannelId());
    }

    public static int setCurrentChannelBySvlId(MtkTvChannelInfoBase mtkTvChannelInfoBase) {
        return TVNativeWrapper.setCurrentChannelBySvlId_native(mtkTvChannelInfoBase.getSvlId(), mtkTvChannelInfoBase.getChannelId());
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
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        a.b("- addFavoritelistChannel. ret=", iAddFavoritelistChannel_native, TAG);
        return iAddFavoritelistChannel_native;
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x002c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int addFavoritelistChannelByIndex(int r4) throws java.lang.Throwable {
        /*
            r3 = this;
            java.lang.String r0 = "+ addFavoritelistChannelByIndex: index="
            java.lang.String r1 = "MtkTvChannelList"
            b.a.a.a.a.b(r0, r4, r1)
            r0 = 0
            monitor-enter(r3)     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L2e
            java.lang.String r1 = "MtkTvChannelList"
            java.lang.String r2 = "addFavoritelistChannelByIndex_native begin"
            android.util.Log.d(r1, r2)     // Catch: java.lang.Throwable -> L23
            int r4 = com.mediatek.twoworlds.tv.TVNativeWrapper.addFavoritelistChannelByIndex_native(r4)     // Catch: java.lang.Throwable -> L23
            if (r4 != 0) goto L18
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L20
            goto L33
        L18:
            com.mediatek.twoworlds.tv.common.MtkTvExceptionBase r0 = new com.mediatek.twoworlds.tv.common.MtkTvExceptionBase     // Catch: java.lang.Throwable -> L20
            java.lang.String r1 = "addFavoritelistChannelByIndex_native fail"
            r0.<init>(r4, r1)     // Catch: java.lang.Throwable -> L20
            throw r0     // Catch: java.lang.Throwable -> L20
        L20:
            r0 = move-exception
            r1 = r4
            goto L26
        L23:
            r4 = move-exception
            r1 = r0
            r0 = r4
        L26:
            r4 = r3
        L27:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L2c
            throw r0     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L29
        L29:
            r4 = move-exception
            r0 = r1
            goto L2f
        L2c:
            r0 = move-exception
            goto L27
        L2e:
            r4 = move-exception
        L2f:
            r4.printStackTrace()
            r4 = r0
        L33:
            java.lang.String r0 = "- addFavoritelistChannelByIndex. ret="
            java.lang.String r1 = "MtkTvChannelList"
            b.a.a.a.a.b(r0, r4, r1)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.twoworlds.tv.MtkTvChannelListBase.addFavoritelistChannelByIndex(int):int");
    }

    public void bookChannel(MtkTvChannelInfoBase mtkTvChannelInfoBase, long j) {
    }

    public int deleteChannelByBrdcstType(int i, int i2) {
        Log.d(TAG, "Enter deleteChannelByBrdcstType(svlId:%d, brdcstType:%d).\n");
        return TVNativeWrapper.deleteChannelByBrdcstType_native(i, i2, false);
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

    public int getChannelCountByBrdcst(int i, int i2, int i3) {
        a.a(a.b("Enter getChannelCountBybrdcst(", i, ",", i2, ","), i3, ")\n", TAG);
        int channelCountByBrdcst_native = TVNativeWrapper.getChannelCountByBrdcst_native(i, i2, i3);
        if (channelCountByBrdcst_native >= 0) {
            return channelCountByBrdcst_native;
        }
        a.b("TVNativeWrapper.getChannelCountByQueryInfo_native failed! return ", channelCountByBrdcst_native, ".\n", TAG);
        return -1;
    }

    public int getChannelCountByFilter(int i, int i2) {
        Log.d(TAG, a.a("Enter getChannelCountByFilter(", i, ",", i2, ")\n"));
        int channelCountByFilter_native = TVNativeWrapper.getChannelCountByFilter_native(i, i2);
        if (channelCountByFilter_native < 0) {
            return -1;
        }
        return channelCountByFilter_native;
    }

    public int getChannelCountByMask(int i, int i2, int i3) {
        a.a(a.b("Enter getChannelCountByMask(", i, ",", i2, ","), i3, ")\n", TAG);
        int channelCountByMask_native = TVNativeWrapper.getChannelCountByMask_native(i, i2, i3, 0);
        if (channelCountByMask_native < 0) {
            return -1;
        }
        return channelCountByMask_native;
    }

    public int getChannelCountByMaskAndSat(int i, int i2, int i3, int i4) {
        Log.d(TAG, a.a(a.b("Enter getChannelCountByMaskAndSat(", i, ",", i2, ","), i3, ",", i4, ")\n"));
        int channelCountByMask_native = TVNativeWrapper.getChannelCountByMask_native(i, i2, i3, i4);
        if (channelCountByMask_native < 0) {
            return -1;
        }
        return channelCountByMask_native;
    }

    public int getChannelCountByQueryInfo(int i, MtkTvChannelQuery mtkTvChannelQuery) {
        Log.d(TAG, "Enter getChannelCountByQueryInfo(" + i + "," + mtkTvChannelQuery + ")\n");
        int channelCountByQueryInfo_native = TVNativeWrapper.getChannelCountByQueryInfo_native(i, mtkTvChannelQuery);
        if (channelCountByQueryInfo_native >= 0) {
            return channelCountByQueryInfo_native;
        }
        a.b("TVNativeWrapper.getChannelCountByQueryInfo_native failed! return ", channelCountByQueryInfo_native, ".\n", TAG);
        return -1;
    }

    public MtkTvChannelInfoBase getChannelInfoBySvlRecId(int i, int i2) {
        MtkTvChannelInfoBase channelInfoBySvlRecId_native;
        new MtkTvChannelInfoBase();
        synchronized (this) {
            channelInfoBySvlRecId_native = TVNativeWrapper.getChannelInfoBySvlRecId_native(i, i2);
        }
        return channelInfoBySvlRecId_native;
    }

    public List<MtkTvChannelInfoBase> getChannelListByBrdcst(int i, int i2, int i3, int i4) {
        Log.d(TAG, a.a(a.b("Enter getChannelListByBrdcst(", i, ",", i2, ","), i3, ",", i4, ")\n"));
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            int channelListByBrdcst_native = TVNativeWrapper.getChannelListByBrdcst_native(i, i2, i3, i4, arrayList);
            if (channelListByBrdcst_native != 0) {
                Log.e(TAG, "TVNativeWrapper.getChannelListByFilter_native failed! return " + channelListByBrdcst_native + ".\n");
            }
        }
        StringBuilder sbA = a.a("leave getChannelListByBrdcst(), size=");
        sbA.append(arrayList.size());
        sbA.append("\n");
        Log.d(TAG, sbA.toString());
        return arrayList;
    }

    public List<MtkTvChannelInfoBase> getChannelListByFilter(int i, int i2, int i3, int i4, int i5) {
        StringBuilder sbB = a.b("Enter getChannelListByFilter(", i, ",", i2, ",");
        sbB.append(i3);
        sbB.append(",");
        sbB.append(i4);
        sbB.append(",");
        sbB.append(i5);
        sbB.append(")\n");
        Log.d(TAG, sbB.toString());
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            int channelListByFilter_native = TVNativeWrapper.getChannelListByFilter_native(i, i2, i3, i4, i5, arrayList);
            if (channelListByFilter_native != 0) {
                Log.e(TAG, "TVNativeWrapper.getChannelListByFilter_native failed! return " + channelListByFilter_native + ".\n");
            }
        }
        StringBuilder sbA = a.a("leave getChannelListByFilter(), size=");
        sbA.append(arrayList.size());
        sbA.append("\n");
        Log.d(TAG, sbA.toString());
        return arrayList;
    }

    public List<MtkTvChannelInfoBase> getChannelListByMask(int i, int i2, int i3, int i4, int i5, int i6) {
        StringBuilder sbB = a.b("Enter getChannelListByMask(", i, ",", i2, ",");
        sbB.append(i3);
        sbB.append(",");
        sbB.append(i4);
        sbB.append(",");
        Log.d(TAG, a.a(sbB, i5, ",", i6, ")\n"));
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

    public List<MtkTvChannelInfoBase> getChannelListByMaskAndSat(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        StringBuilder sbB = a.b("Enter getChannelListByMaskAndSat(", i, ",", i2, ",");
        sbB.append(i3);
        sbB.append(",");
        sbB.append(i4);
        sbB.append(",");
        sbB.append(i5);
        sbB.append(",");
        sbB.append(i6);
        sbB.append(",");
        sbB.append(i7);
        sbB.append(")\n");
        Log.d(TAG, sbB.toString());
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
        a.b("Enter getChannelPumpVer. svlId = ", i, TAG);
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

    public MtkTvChannelInfoBase getCurrentChannelFromNav(int i) {
        Log.d(TAG, "Enter getCurrentChannelFromNav: winId = " + i);
        return TVNativeWrapper.getCurrentChannelFromNav_native(i);
    }

    public int getFavoriteIdx(int i, int i2, int i3, int i4) {
        Log.d(TAG, a.a(a.b("Enter getFavoriteIdx(", i, ",", i2, ","), i3, ",", i4, ")\n"));
        int favoriteIdx_native = TVNativeWrapper.getFavoriteIdx_native(i, i2, i3, i4);
        if (favoriteIdx_native < 0) {
            return -1;
        }
        return favoriteIdx_native;
    }

    public List<MtkTvChannelInfoBase> getFavoriteListByFilter(int i, int i2, int i3, boolean z, int i4, int i5, int i6) {
        StringBuilder sbB = a.b("Enter getChannelList(", i, ",", i2, ",");
        sbB.append(i3);
        sbB.append(",");
        sbB.append(i4);
        sbB.append(",");
        sbB.append(i5);
        sbB.append(i6);
        sbB.append(")\n");
        Log.d(TAG, sbB.toString());
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            int channelListByFavoriteIdx_native = TVNativeWrapper.getChannelListByFavoriteIdx_native(i, i2, i3, z, i4, i5, i6, arrayList);
            if (channelListByFavoriteIdx_native != 0) {
                Log.e(TAG, "TVNativeWrapper.getChannelListByFavoriteIdx_native failed! return " + channelListByFavoriteIdx_native + ".\n");
            }
        }
        StringBuilder sbA = a.a("leave getChannelList(), size=");
        sbA.append(arrayList.size());
        sbA.append("\n");
        Log.d(TAG, sbA.toString());
        return arrayList;
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x0034 */
    /* JADX WARN: Code duplicated, block: B:27:0x005c A[LOOP:0: B:25:0x0056->B:27:0x005c, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<com.mediatek.twoworlds.tv.model.MtkTvFavoritelistInfoBase> getFavoritelistByFilter() throws java.lang.Throwable {
        /*
            r5 = this;
            java.lang.String r0 = "MtkTvChannelList"
            java.lang.String r1 = "+ getFavoritelistByFilter."
            android.util.Log.d(r0, r1)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            monitor-enter(r5)     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L36
            java.lang.String r2 = "MtkTvChannelList"
            java.lang.String r3 = "getFavoritelistByFilter_native begin"
            android.util.Log.d(r2, r3)     // Catch: java.lang.Throwable -> L2a
            int r2 = com.mediatek.twoworlds.tv.TVNativeWrapper.getFavoritelistByFilter_native(r0)     // Catch: java.lang.Throwable -> L2a
            if (r2 != 0) goto L1d
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L25
            goto L40
        L1d:
            com.mediatek.twoworlds.tv.common.MtkTvExceptionBase r3 = new com.mediatek.twoworlds.tv.common.MtkTvExceptionBase     // Catch: java.lang.Throwable -> L25
            java.lang.String r4 = "getFavoritelistByFilter_native fail"
            r3.<init>(r2, r4)     // Catch: java.lang.Throwable -> L25
            throw r3     // Catch: java.lang.Throwable -> L25
        L25:
            r3 = move-exception
            r4 = r2
            r2 = r3
            r3 = r1
            goto L2d
        L2a:
            r2 = move-exception
            r3 = r1
            r4 = r3
        L2d:
            r1 = r0
            r0 = r5
        L2f:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L34
            throw r2     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L31
        L31:
            r0 = move-exception
            r2 = r4
            goto L3b
        L34:
            r2 = move-exception
            goto L2f
        L36:
            r2 = move-exception
            r3 = r1
            r1 = r0
            r0 = r2
            r2 = r3
        L3b:
            r0.printStackTrace()
            r0 = r1
            r1 = r3
        L40:
            java.lang.String r3 = "getFavoritelistByFilter: count="
            java.lang.StringBuilder r3 = b.a.a.a.a.a(r3)
            int r4 = r0.size()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "MtkTvChannelList"
            android.util.Log.d(r4, r3)
        L56:
            int r3 = r0.size()
            if (r1 >= r3) goto Lb1
            java.lang.String r3 = " getFavoritelistByFilter: svlId="
            java.lang.StringBuilder r3 = b.a.a.a.a.a(r3)
            java.lang.Object r4 = r0.get(r1)
            com.mediatek.twoworlds.tv.model.MtkTvFavoritelistInfoBase r4 = (com.mediatek.twoworlds.tv.model.MtkTvFavoritelistInfoBase) r4
            int r4 = r4.getSvlId()
            r3.append(r4)
            java.lang.String r4 = ",svlRecId="
            r3.append(r4)
            java.lang.Object r4 = r0.get(r1)
            com.mediatek.twoworlds.tv.model.MtkTvFavoritelistInfoBase r4 = (com.mediatek.twoworlds.tv.model.MtkTvFavoritelistInfoBase) r4
            int r4 = r4.getSvlRecId()
            r3.append(r4)
            java.lang.String r4 = ",ChannelId="
            r3.append(r4)
            java.lang.Object r4 = r0.get(r1)
            com.mediatek.twoworlds.tv.model.MtkTvFavoritelistInfoBase r4 = (com.mediatek.twoworlds.tv.model.MtkTvFavoritelistInfoBase) r4
            int r4 = r4.getChannelId()
            r3.append(r4)
            java.lang.String r4 = ",ChannelName="
            r3.append(r4)
            java.lang.Object r4 = r0.get(r1)
            com.mediatek.twoworlds.tv.model.MtkTvFavoritelistInfoBase r4 = (com.mediatek.twoworlds.tv.model.MtkTvFavoritelistInfoBase) r4
            java.lang.String r4 = r4.getChannelName()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "MtkTvChannelList"
            android.util.Log.d(r4, r3)
            int r1 = r1 + 1
            goto L56
        Lb1:
            java.lang.String r1 = "- getFavoritelistByFilter. ret="
            java.lang.String r3 = "MtkTvChannelList"
            b.a.a.a.a.b(r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.twoworlds.tv.MtkTvChannelListBase.getFavoritelistByFilter():java.util.List");
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

    public boolean isChannelNumberExsit(int i, int i2) {
        Log.d(TAG, "Enter isChannelNumberExsit(svlId:%d, ch_num:%d).\n");
        return TVNativeWrapper.isChannelNumberExsit_native(i, i2);
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

    /* JADX WARN: Bottom block not found for handler: all -> 0x002c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int removeFavoritelistChannel(int r4) throws java.lang.Throwable {
        /*
            r3 = this;
            java.lang.String r0 = "+ removeFavoritelistChannel. index="
            java.lang.String r1 = "MtkTvChannelList"
            b.a.a.a.a.b(r0, r4, r1)
            r0 = 0
            monitor-enter(r3)     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L2e
            java.lang.String r1 = "MtkTvChannelList"
            java.lang.String r2 = "removeFavoritelistChannel_native begin"
            android.util.Log.d(r1, r2)     // Catch: java.lang.Throwable -> L23
            int r4 = com.mediatek.twoworlds.tv.TVNativeWrapper.removeFavoritelistChannel_native(r4)     // Catch: java.lang.Throwable -> L23
            if (r4 != 0) goto L18
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L20
            goto L33
        L18:
            com.mediatek.twoworlds.tv.common.MtkTvExceptionBase r0 = new com.mediatek.twoworlds.tv.common.MtkTvExceptionBase     // Catch: java.lang.Throwable -> L20
            java.lang.String r1 = "removeFavoritelistChannel_native fail"
            r0.<init>(r4, r1)     // Catch: java.lang.Throwable -> L20
            throw r0     // Catch: java.lang.Throwable -> L20
        L20:
            r0 = move-exception
            r1 = r4
            goto L26
        L23:
            r4 = move-exception
            r1 = r0
            r0 = r4
        L26:
            r4 = r3
        L27:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L2c
            throw r0     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L29
        L29:
            r4 = move-exception
            r0 = r1
            goto L2f
        L2c:
            r0 = move-exception
            goto L27
        L2e:
            r4 = move-exception
        L2f:
            r4.printStackTrace()
            r4 = r0
        L33:
            java.lang.String r0 = "- removeFavoritelistChannel. ret="
            java.lang.String r1 = "MtkTvChannelList"
            b.a.a.a.a.b(r0, r4, r1)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.twoworlds.tv.MtkTvChannelListBase.removeFavoritelistChannel(int):int");
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x002c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int removeFavoritelistChannelByIndexWithoutShowFavIcon(int r4) throws java.lang.Throwable {
        /*
            r3 = this;
            java.lang.String r0 = "+ removeFavoritelistChannelByIndexWithoutShowFavIcon. index="
            java.lang.String r1 = "MtkTvChannelList"
            b.a.a.a.a.b(r0, r4, r1)
            r0 = 0
            monitor-enter(r3)     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L2e
            java.lang.String r1 = "MtkTvChannelList"
            java.lang.String r2 = "removeFavoritelistChannelByIndexWithoutShowFavIcon_native begin"
            android.util.Log.d(r1, r2)     // Catch: java.lang.Throwable -> L23
            int r4 = com.mediatek.twoworlds.tv.TVNativeWrapper.removeFavoritelistChannelByIndexWithoutShowFavIcon_native(r4)     // Catch: java.lang.Throwable -> L23
            if (r4 != 0) goto L18
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L20
            goto L33
        L18:
            com.mediatek.twoworlds.tv.common.MtkTvExceptionBase r0 = new com.mediatek.twoworlds.tv.common.MtkTvExceptionBase     // Catch: java.lang.Throwable -> L20
            java.lang.String r1 = "removeFavoritelistChannelByIndexWithoutShowFavIcon_native fail"
            r0.<init>(r4, r1)     // Catch: java.lang.Throwable -> L20
            throw r0     // Catch: java.lang.Throwable -> L20
        L20:
            r0 = move-exception
            r1 = r4
            goto L26
        L23:
            r4 = move-exception
            r1 = r0
            r0 = r4
        L26:
            r4 = r3
        L27:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L2c
            throw r0     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L29
        L29:
            r4 = move-exception
            r0 = r1
            goto L2f
        L2c:
            r0 = move-exception
            goto L27
        L2e:
            r4 = move-exception
        L2f:
            r4.printStackTrace()
            r4 = r0
        L33:
            java.lang.String r0 = "- removeFavoritelistChannelByIndexWithoutShowFavIcon. ret="
            java.lang.String r1 = "MtkTvChannelList"
            b.a.a.a.a.b(r0, r4, r1)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.twoworlds.tv.MtkTvChannelListBase.removeFavoritelistChannelByIndexWithoutShowFavIcon(int):int");
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
            a.b("Invalid channelOperator ", i, "!\n", TAG);
            return -1;
        }
        MtkTvChannelInfoBase mtkTvChannelInfoBase = list.get(0);
        Log.d(TAG, a.a("", size, " channels will be ", i, " in 'channelList'!\n"));
        Log.d(TAG, "svlId = " + mtkTvChannelInfoBase.getSvlId() + "...\n");
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
            a.b("Invalid channelOperator ", i, "!\n", TAG);
            return -1;
        }
        MtkTvChannelInfoBase mtkTvChannelInfoBase = list.get(0);
        Log.d(TAG, a.a("", size, " channels will be ", i, " in 'channelList'!\n"));
        Log.d(TAG, "svlId = " + mtkTvChannelInfoBase.getSvlId() + "...\n");
        return TVNativeWrapper.setChannelListWithoutNotify_native(i, mtkTvChannelInfoBase.getSvlId(), list) == 0 ? 0 : -1;
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

    public int slideForOnePartChannelId(int i, int i2, int i3) {
        a.a(a.b("Enter slideForOneChannelId(", i, ",", i2, ","), i3, ")\n", TAG);
        return TVNativeWrapper.slideForOnePartChannelId_native(i, i2, i3);
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
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        a.b("- storeFavoritelistChannel. ret=", iStoreFavoritelistChannel_native, TAG);
        return iStoreFavoritelistChannel_native;
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

    /* JADX WARN: Bottom block not found for handler: all -> 0x0040 */
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
            monitor-enter(r3)     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L42
            java.lang.String r1 = "MtkTvChannelList"
            java.lang.String r2 = "swapFavoritelistByIndex_native begin"
            android.util.Log.d(r1, r2)     // Catch: java.lang.Throwable -> L3b
            int r4 = com.mediatek.twoworlds.tv.TVNativeWrapper.swapFavoritelistByIndex_native(r4, r5)     // Catch: java.lang.Throwable -> L3b
            if (r4 != 0) goto L2f
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L37
            goto L47
        L2f:
            com.mediatek.twoworlds.tv.common.MtkTvExceptionBase r5 = new com.mediatek.twoworlds.tv.common.MtkTvExceptionBase     // Catch: java.lang.Throwable -> L37
            java.lang.String r0 = "swapFavoritelistByIndex_native fail"
            r5.<init>(r4, r0)     // Catch: java.lang.Throwable -> L37
            throw r5     // Catch: java.lang.Throwable -> L37
        L37:
            r5 = move-exception
            r0 = r4
        L39:
            r4 = r3
            goto L3e
        L3b:
            r4 = move-exception
            r5 = r4
            goto L39
        L3e:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L40
            throw r5     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L42
        L40:
            r5 = move-exception
            goto L3e
        L42:
            r4 = move-exception
            r4.printStackTrace()
            r4 = r0
        L47:
            java.lang.String r5 = "- swapFavoritelistByIndex. ret="
            java.lang.String r0 = "MtkTvChannelList"
            b.a.a.a.a.b(r5, r4, r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.twoworlds.tv.MtkTvChannelListBase.swapFavoritelistByIndex(int, int):int");
    }

    public static int cleanChannelList(int i, boolean z) {
        return TVNativeWrapper.cleanChannelList_native(i, z);
    }

    public int deleteChannelByBrdcstType(int i, int i2, boolean z) {
        Log.d(TAG, "Enter deleteChannelByBrdcstType(svlId:%d, brdcstType:%d).\n");
        return TVNativeWrapper.deleteChannelByBrdcstType_native(i, i2, z);
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
}
