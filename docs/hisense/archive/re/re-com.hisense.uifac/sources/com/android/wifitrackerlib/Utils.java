package com.android.wifitrackerlib;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkKey;
import android.net.NetworkScoreManager;
import android.net.ScoredNetwork;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiNetworkScoreCache;
import android.os.UserHandle;
import android.provider.Settings;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.DateUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
class Utils {
    private static NetworkScoreManager sNetworkScoreManager;

    private static int roundToClosestSpeedEnum(int i) {
        if (i == 0) {
            return 0;
        }
        if (i < 7) {
            return 5;
        }
        if (i < 15) {
            return 10;
        }
        return i < 25 ? 20 : 30;
    }

    private static String getActiveScorerPackage(Context context) {
        if (sNetworkScoreManager == null) {
            sNetworkScoreManager = (NetworkScoreManager) context.getSystemService(NetworkScoreManager.class);
        }
        return sNetworkScoreManager.getActiveScorerPackage();
    }

    static ScanResult getBestScanResultByLevel(List<ScanResult> list) {
        if (list.isEmpty()) {
            return null;
        }
        return (ScanResult) Collections.max(list, Comparator.comparingInt(new ToIntFunction() { // from class: com.android.wifitrackerlib.-$$Lambda$Utils$wGn2sVTZ5l1wFLkqd7rxKtPh0RU
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((ScanResult) obj).level;
            }
        }));
    }

    static List<Integer> getSecurityTypesFromScanResult(ScanResult scanResult) {
        ArrayList arrayList = new ArrayList();
        String str = scanResult.capabilities;
        if (str == null) {
            arrayList.add(0);
        } else if (str.contains("PSK") && scanResult.capabilities.contains("SAE")) {
            arrayList.add(2);
            arrayList.add(5);
        } else if (scanResult.capabilities.contains("OWE_TRANSITION")) {
            arrayList.add(0);
            arrayList.add(4);
        } else if (scanResult.capabilities.contains("OWE")) {
            arrayList.add(4);
        } else if (scanResult.capabilities.contains("WEP")) {
            arrayList.add(1);
        } else if (scanResult.capabilities.contains("SAE")) {
            arrayList.add(5);
        } else if (scanResult.capabilities.contains("PSK")) {
            arrayList.add(2);
        } else if (scanResult.capabilities.contains("EAP_SUITE_B_192")) {
            arrayList.add(6);
        } else if (scanResult.capabilities.contains("EAP")) {
            arrayList.add(3);
        } else {
            arrayList.add(0);
        }
        return arrayList;
    }

    static int getSecurityTypeFromWifiConfiguration(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.allowedKeyManagement.get(8)) {
            return 5;
        }
        if (wifiConfiguration.allowedKeyManagement.get(1)) {
            return 2;
        }
        if (wifiConfiguration.allowedKeyManagement.get(10)) {
            return 6;
        }
        if (wifiConfiguration.allowedKeyManagement.get(2) || wifiConfiguration.allowedKeyManagement.get(3)) {
            return 3;
        }
        if (wifiConfiguration.allowedKeyManagement.get(9)) {
            return 4;
        }
        return wifiConfiguration.wepKeys[0] != null ? 1 : 0;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0205  */
    /* JADX WARN: Code duplicated, block: B:101:0x0207 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:105:0x0216  */
    /* JADX WARN: Code duplicated, block: B:107:0x0219  */
    /* JADX WARN: Code duplicated, block: B:108:0x021b A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:111:0x0227  */
    /* JADX WARN: Code duplicated, block: B:112:0x022f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:113:0x0231  */
    /* JADX WARN: Code duplicated, block: B:114:0x0233 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:118:0x0241  */
    /* JADX WARN: Code duplicated, block: B:121:0x024f  */
    /* JADX WARN: Code duplicated, block: B:125:0x0260  */
    /* JADX WARN: Code duplicated, block: B:127:0x0276  */
    /* JADX WARN: Code duplicated, block: B:137:0x0246 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:138:0x01ca A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:139:0x01bc A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:148:0x027e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:54:0x014c  */
    /* JADX WARN: Code duplicated, block: B:57:0x015a  */
    /* JADX WARN: Code duplicated, block: B:59:0x0165  */
    /* JADX WARN: Code duplicated, block: B:60:0x016a  */
    /* JADX WARN: Code duplicated, block: B:64:0x0178  */
    /* JADX WARN: Code duplicated, block: B:67:0x0186 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:71:0x0196  */
    /* JADX WARN: Code duplicated, block: B:74:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:76:0x01a7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:77:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:78:0x01ab A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:85:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:86:0x01cf A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:92:0x01e7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:93:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:94:0x01eb A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:97:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:98:0x0202  */
    static Map<String, List<ScanResult>> mapScanResultsToKey(List<ScanResult> list, boolean z, Map<String, WifiConfiguration> map, boolean z2, boolean z3, boolean z4) {
        int i;
        int i2;
        boolean z5;
        boolean z6;
        boolean z7;
        int i3;
        int i4;
        boolean z8;
        HashMap map2;
        boolean z9;
        int i5;
        Iterator it;
        String strSsidAndSecurityToStandardWifiEntryKey;
        HashMap map3;
        char c;
        Map<String, WifiConfiguration> map4 = map == null ? new HashMap<>() : map;
        Map map5 = (Map) list.stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$Utils$YXgA7eQ3EufOS8jlgf9HRQs4bfM
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Utils.lambda$mapScanResultsToKey$1((ScanResult) obj);
            }
        }).collect(Collectors.groupingBy(new Function() { // from class: com.android.wifitrackerlib.-$$Lambda$Utils$_MVjtMEczmHvXav1qgSkgxMj5iE
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ScanResult) obj).SSID;
            }
        }));
        HashMap map6 = new HashMap();
        Iterator it2 = map5.keySet().iterator();
        while (it2.hasNext()) {
            String str = (String) it2.next();
            int i6 = 2;
            boolean zContainsKey = map4.containsKey(StandardWifiEntry.ssidAndSecurityToStandardWifiEntryKey(str, 2));
            int i7 = 5;
            boolean zContainsKey2 = map4.containsKey(StandardWifiEntry.ssidAndSecurityToStandardWifiEntryKey(str, 5));
            boolean zContainsKey3 = map4.containsKey(StandardWifiEntry.ssidAndSecurityToStandardWifiEntryKey(str, 0));
            boolean zContainsKey4 = map4.containsKey(StandardWifiEntry.ssidAndSecurityToStandardWifiEntryKey(str, 4));
            Iterator it3 = ((List) map5.get(str)).iterator();
            boolean z10 = false;
            boolean z11 = false;
            boolean z12 = false;
            boolean z13 = false;
            while (it3.hasNext()) {
                List<Integer> securityTypesFromScanResult = getSecurityTypesFromScanResult((ScanResult) it3.next());
                if (securityTypesFromScanResult.contains(Integer.valueOf(i6))) {
                    z10 = true;
                }
                if (securityTypesFromScanResult.contains(5)) {
                    z11 = true;
                }
                if (securityTypesFromScanResult.contains(4)) {
                    z12 = true;
                }
                if (securityTypesFromScanResult.contains(0)) {
                    z13 = true;
                }
                i6 = 2;
            }
            Iterator it4 = ((List) map5.get(str)).iterator();
            while (it4.hasNext()) {
                ScanResult scanResult = (ScanResult) it4.next();
                List<Integer> securityTypesFromScanResult2 = getSecurityTypesFromScanResult(scanResult);
                ArrayList arrayList = new ArrayList();
                map4 = map4;
                if (!z2) {
                    securityTypesFromScanResult2.remove(Integer.valueOf(i7));
                }
                if (!z3) {
                    securityTypesFromScanResult2.remove((Object) 6);
                }
                if (z4) {
                    i = i7;
                } else {
                    securityTypesFromScanResult2.remove((Object) 4);
                    i = 5;
                }
                if (securityTypesFromScanResult2.contains(Integer.valueOf(i))) {
                    i2 = 2;
                    z5 = securityTypesFromScanResult2.contains(2) ? false : true;
                    z6 = (securityTypesFromScanResult2.contains(Integer.valueOf(i2)) || securityTypesFromScanResult2.contains(5)) ? false : true;
                    it2 = it2;
                    if (securityTypesFromScanResult2.contains(2) || !securityTypesFromScanResult2.contains(5)) {
                        z7 = false;
                    } else {
                        z7 = true;
                    }
                    if (securityTypesFromScanResult2.contains(4)) {
                        i3 = 0;
                        if (!securityTypesFromScanResult2.contains(0)) {
                            i4 = 1;
                        }
                        z8 = !securityTypesFromScanResult2.contains(Integer.valueOf(i3)) && securityTypesFromScanResult2.contains(4);
                        map2 = map6;
                        if (securityTypesFromScanResult2.contains(0) || securityTypesFromScanResult2.contains(4)) {
                            z9 = false;
                        } else {
                            z9 = true;
                        }
                        if (z) {
                            if (!z6) {
                                c = 2;
                                if (!z7) {
                                    if (z5) {
                                        i5 = 5;
                                        if (i4 != 0) {
                                            if (z13 || !zContainsKey3 || zContainsKey4) {
                                                arrayList.add(4);
                                            }
                                        } else if (z8) {
                                            if (!zContainsKey4 || !zContainsKey3) {
                                                arrayList.add(4);
                                            } else {
                                                arrayList.add(0);
                                            }
                                        } else if (!z9) {
                                            arrayList.addAll(securityTypesFromScanResult2);
                                        } else if (z12 || (!zContainsKey4 && zContainsKey3)) {
                                            arrayList.add(0);
                                        }
                                    } else if (zContainsKey && (zContainsKey2 || !z10)) {
                                        i5 = 5;
                                        arrayList.add(5);
                                    } else {
                                        i5 = 5;
                                        arrayList.add(2);
                                    }
                                    i7 = i5;
                                    map6 = map2;
                                } else if (zContainsKey && zContainsKey2) {
                                    arrayList.add(5);
                                } else {
                                    arrayList.add(2);
                                    i5 = 5;
                                }
                            } else if (zContainsKey && zContainsKey2 && z11) {
                                map6 = map2;
                                i7 = 5;
                            } else {
                                c = 2;
                                arrayList.add(2);
                            }
                            i5 = 5;
                        } else {
                            i5 = 5;
                            arrayList.addAll(securityTypesFromScanResult2);
                            if (z5) {
                                arrayList.add(2);
                            }
                        }
                        it = arrayList.iterator();
                        while (it.hasNext()) {
                            strSsidAndSecurityToStandardWifiEntryKey = StandardWifiEntry.ssidAndSecurityToStandardWifiEntryKey(str, ((Integer) it.next()).intValue());
                            map3 = map2;
                            if (!map3.containsKey(strSsidAndSecurityToStandardWifiEntryKey)) {
                                map3.put(strSsidAndSecurityToStandardWifiEntryKey, new ArrayList());
                            }
                            ((List) map3.get(strSsidAndSecurityToStandardWifiEntryKey)).add(scanResult);
                            map2 = map3;
                        }
                        i7 = i5;
                        map6 = map2;
                    } else {
                        i3 = 0;
                    }
                    i4 = i3;
                    if (securityTypesFromScanResult2.contains(Integer.valueOf(i3))) {
                    }
                    map2 = map6;
                    if (securityTypesFromScanResult2.contains(0)) {
                        z9 = false;
                    } else {
                        z9 = false;
                    }
                    if (z) {
                        if (!z6) {
                            if (zContainsKey) {
                            }
                            c = 2;
                            arrayList.add(2);
                        } else {
                            c = 2;
                            if (!z7) {
                                if (zContainsKey) {
                                }
                                arrayList.add(2);
                                i5 = 5;
                            } else {
                                if (z5) {
                                    i5 = 5;
                                    if (i4 != 0) {
                                        if (z13) {
                                        }
                                        arrayList.add(4);
                                    } else if (z8) {
                                        if (!zContainsKey4) {
                                            arrayList.add(4);
                                        } else {
                                            arrayList.add(4);
                                        }
                                    } else if (!z9) {
                                        if (z12) {
                                        }
                                        arrayList.add(0);
                                    } else {
                                        arrayList.addAll(securityTypesFromScanResult2);
                                    }
                                } else if (zContainsKey) {
                                    i5 = 5;
                                    arrayList.add(2);
                                } else {
                                    i5 = 5;
                                    arrayList.add(2);
                                }
                                i7 = i5;
                                map6 = map2;
                            }
                        }
                        i5 = 5;
                    } else {
                        i5 = 5;
                        arrayList.addAll(securityTypesFromScanResult2);
                        if (z5) {
                            arrayList.add(2);
                        }
                    }
                    it = arrayList.iterator();
                    while (it.hasNext()) {
                        strSsidAndSecurityToStandardWifiEntryKey = StandardWifiEntry.ssidAndSecurityToStandardWifiEntryKey(str, ((Integer) it.next()).intValue());
                        map3 = map2;
                        if (!map3.containsKey(strSsidAndSecurityToStandardWifiEntryKey)) {
                            map3.put(strSsidAndSecurityToStandardWifiEntryKey, new ArrayList());
                        }
                        ((List) map3.get(strSsidAndSecurityToStandardWifiEntryKey)).add(scanResult);
                        map2 = map3;
                    }
                    i7 = i5;
                    map6 = map2;
                } else {
                    i2 = 2;
                }
                if (securityTypesFromScanResult2.contains(Integer.valueOf(i2))) {
                }
                it2 = it2;
                if (securityTypesFromScanResult2.contains(2)) {
                    z7 = false;
                } else {
                    z7 = false;
                }
                if (securityTypesFromScanResult2.contains(4)) {
                    i3 = 0;
                    if (!securityTypesFromScanResult2.contains(0)) {
                        i4 = 1;
                    }
                    if (securityTypesFromScanResult2.contains(Integer.valueOf(i3))) {
                    }
                    map2 = map6;
                    if (securityTypesFromScanResult2.contains(0)) {
                        z9 = false;
                    } else {
                        z9 = false;
                    }
                    if (z) {
                        if (!z6) {
                            if (zContainsKey) {
                            }
                            c = 2;
                            arrayList.add(2);
                        } else {
                            c = 2;
                            if (!z7) {
                                if (zContainsKey) {
                                }
                                arrayList.add(2);
                                i5 = 5;
                            } else {
                                if (z5) {
                                    i5 = 5;
                                    if (i4 != 0) {
                                        if (z13) {
                                        }
                                        arrayList.add(4);
                                    } else if (z8) {
                                        if (!zContainsKey4) {
                                            arrayList.add(4);
                                        } else {
                                            arrayList.add(4);
                                        }
                                    } else if (!z9) {
                                        if (z12) {
                                        }
                                        arrayList.add(0);
                                    } else {
                                        arrayList.addAll(securityTypesFromScanResult2);
                                    }
                                } else if (zContainsKey) {
                                    i5 = 5;
                                    arrayList.add(2);
                                } else {
                                    i5 = 5;
                                    arrayList.add(2);
                                }
                                i7 = i5;
                                map6 = map2;
                            }
                        }
                        i5 = 5;
                    } else {
                        i5 = 5;
                        arrayList.addAll(securityTypesFromScanResult2);
                        if (z5) {
                            arrayList.add(2);
                        }
                    }
                    it = arrayList.iterator();
                    while (it.hasNext()) {
                        strSsidAndSecurityToStandardWifiEntryKey = StandardWifiEntry.ssidAndSecurityToStandardWifiEntryKey(str, ((Integer) it.next()).intValue());
                        map3 = map2;
                        if (!map3.containsKey(strSsidAndSecurityToStandardWifiEntryKey)) {
                            map3.put(strSsidAndSecurityToStandardWifiEntryKey, new ArrayList());
                        }
                        ((List) map3.get(strSsidAndSecurityToStandardWifiEntryKey)).add(scanResult);
                        map2 = map3;
                    }
                    i7 = i5;
                    map6 = map2;
                } else {
                    i3 = 0;
                }
                i4 = i3;
                if (securityTypesFromScanResult2.contains(Integer.valueOf(i3))) {
                }
                map2 = map6;
                if (securityTypesFromScanResult2.contains(0)) {
                    z9 = false;
                } else {
                    z9 = false;
                }
                if (z) {
                    if (!z6) {
                        if (zContainsKey) {
                        }
                        c = 2;
                        arrayList.add(2);
                    } else {
                        c = 2;
                        if (!z7) {
                            if (zContainsKey) {
                            }
                            arrayList.add(2);
                            i5 = 5;
                        } else {
                            if (z5) {
                                i5 = 5;
                                if (i4 != 0) {
                                    if (z13) {
                                    }
                                    arrayList.add(4);
                                } else if (z8) {
                                    if (!zContainsKey4) {
                                        arrayList.add(4);
                                    } else {
                                        arrayList.add(4);
                                    }
                                } else if (!z9) {
                                    if (z12) {
                                    }
                                    arrayList.add(0);
                                } else {
                                    arrayList.addAll(securityTypesFromScanResult2);
                                }
                            } else if (zContainsKey) {
                                i5 = 5;
                                arrayList.add(2);
                            } else {
                                i5 = 5;
                                arrayList.add(2);
                            }
                            i7 = i5;
                            map6 = map2;
                        }
                    }
                    i5 = 5;
                } else {
                    i5 = 5;
                    arrayList.addAll(securityTypesFromScanResult2);
                    if (z5) {
                        arrayList.add(2);
                    }
                }
                it = arrayList.iterator();
                while (it.hasNext()) {
                    strSsidAndSecurityToStandardWifiEntryKey = StandardWifiEntry.ssidAndSecurityToStandardWifiEntryKey(str, ((Integer) it.next()).intValue());
                    map3 = map2;
                    if (!map3.containsKey(strSsidAndSecurityToStandardWifiEntryKey)) {
                        map3.put(strSsidAndSecurityToStandardWifiEntryKey, new ArrayList());
                    }
                    ((List) map3.get(strSsidAndSecurityToStandardWifiEntryKey)).add(scanResult);
                    map2 = map3;
                }
                i7 = i5;
                map6 = map2;
            }
        }
        return map6;
    }

    static /* synthetic */ boolean lambda$mapScanResultsToKey$1(ScanResult scanResult) {
        return !TextUtils.isEmpty(scanResult.SSID);
    }

    static int getAverageSpeedFromScanResults(WifiNetworkScoreCache wifiNetworkScoreCache, List<ScanResult> list) {
        int iCalculateBadge;
        int i = 0;
        int i2 = 0;
        for (ScanResult scanResult : list) {
            ScoredNetwork scoredNetwork = wifiNetworkScoreCache.getScoredNetwork(scanResult);
            if (scoredNetwork != null && (iCalculateBadge = scoredNetwork.calculateBadge(scanResult.level)) != 0) {
                i++;
                i2 += iCalculateBadge;
            }
        }
        if (i == 0) {
            return 0;
        }
        return roundToClosestSpeedEnum(i2 / i);
    }

    static int getSpeedFromWifiInfo(WifiNetworkScoreCache wifiNetworkScoreCache, WifiInfo wifiInfo) {
        ScoredNetwork scoredNetwork = wifiNetworkScoreCache.getScoredNetwork(NetworkKey.createFromWifiInfo(wifiInfo));
        if (scoredNetwork == null) {
            return 0;
        }
        return roundToClosestSpeedEnum(scoredNetwork.calculateBadge(wifiInfo.getRssi()));
    }

    static String getAppLabel(Context context, String str) {
        try {
            String string = Settings.Global.getString(context.getContentResolver(), "use_open_wifi_package");
            if (!TextUtils.isEmpty(string) && TextUtils.equals(str, getActiveScorerPackage(context))) {
                str = string;
            }
            return context.getPackageManager().getApplicationInfoAsUser(str, 0, UserHandle.getUserId(-2)).loadLabel(context.getPackageManager()).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    static String getDisconnectedStateDescription(Context context, WifiEntry wifiEntry) {
        if (context != null && wifiEntry != null) {
            WifiConfiguration wifiConfiguration = wifiEntry.getWifiConfiguration();
            if (wifiConfiguration == null) {
                return null;
            }
            if (wifiConfiguration.hasNoInternetAccess()) {
                return context.getString(wifiConfiguration.getNetworkSelectionStatus().getNetworkSelectionStatus() == 2 ? R$string.wifi_no_internet_no_reconnect : R$string.wifi_no_internet);
            }
            if (wifiConfiguration.getNetworkSelectionStatus().getNetworkSelectionStatus() != 0) {
                int networkSelectionDisableReason = wifiConfiguration.getNetworkSelectionStatus().getNetworkSelectionDisableReason();
                if (networkSelectionDisableReason == 1) {
                    return context.getString(R$string.wifi_disabled_generic);
                }
                if (networkSelectionDisableReason == 2) {
                    return context.getString(R$string.wifi_disabled_password_failure);
                }
                if (networkSelectionDisableReason == 3) {
                    return context.getString(R$string.wifi_disabled_network_failure);
                }
                if (networkSelectionDisableReason == 8) {
                    return context.getString(R$string.wifi_check_password_try_again);
                }
            } else if (wifiEntry.getLevel() != -1 && wifiConfiguration.getRecentFailureReason() == 17) {
                return context.getString(R$string.wifi_ap_unable_to_handle_new_sta);
            }
        }
        return "";
    }

    static String getAutoConnectDescription(Context context, WifiEntry wifiEntry) {
        return (context == null || wifiEntry == null || !wifiEntry.canSetAutoJoinEnabled() || wifiEntry.isAutoJoinEnabled()) ? "" : context.getString(R$string.auto_connect_disable);
    }

    static String getMeteredDescription(Context context, WifiEntry wifiEntry) {
        if (context == null || wifiEntry == null) {
            return "";
        }
        if (!wifiEntry.canSetMeteredChoice() && wifiEntry.getMeteredChoice() != 1) {
            return "";
        }
        if (wifiEntry.getMeteredChoice() == 1) {
            return context.getString(R$string.wifi_metered_label);
        }
        if (wifiEntry.getMeteredChoice() == 2) {
            return context.getString(R$string.wifi_unmetered_label);
        }
        return wifiEntry.isMetered() ? context.getString(R$string.wifi_metered_label) : "";
    }

    static String getSpeedDescription(Context context, WifiEntry wifiEntry) {
        if (context == null || wifiEntry == null) {
            return "";
        }
        int speed = wifiEntry.getSpeed();
        if (speed == 5) {
            return context.getString(R$string.speed_label_slow);
        }
        if (speed == 10) {
            return context.getString(R$string.speed_label_okay);
        }
        if (speed != 20) {
            return speed != 30 ? "" : context.getString(R$string.speed_label_very_fast);
        }
        return context.getString(R$string.speed_label_fast);
    }

    static String getVerboseLoggingDescription(WifiEntry wifiEntry) {
        if (!BaseWifiTracker.isVerboseLoggingEnabled() || wifiEntry == null) {
            return "";
        }
        StringJoiner stringJoiner = new StringJoiner(" ");
        String wifiInfoDescription = wifiEntry.getWifiInfoDescription();
        if (!TextUtils.isEmpty(wifiInfoDescription)) {
            stringJoiner.add(wifiInfoDescription);
        }
        String scanResultDescription = wifiEntry.getScanResultDescription();
        if (!TextUtils.isEmpty(scanResultDescription)) {
            stringJoiner.add(scanResultDescription);
        }
        String networkSelectionDescription = wifiEntry.getNetworkSelectionDescription();
        if (!TextUtils.isEmpty(networkSelectionDescription)) {
            stringJoiner.add(networkSelectionDescription);
        }
        return stringJoiner.toString();
    }

    static String getNetworkSelectionDescription(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        WifiConfiguration.NetworkSelectionStatus networkSelectionStatus = wifiConfiguration.getNetworkSelectionStatus();
        if (networkSelectionStatus.getNetworkSelectionStatus() != 0) {
            sb.append(" (" + networkSelectionStatus.getNetworkStatusString());
            if (networkSelectionStatus.getDisableTime() > 0) {
                sb.append(" " + DateUtils.formatElapsedTime((System.currentTimeMillis() - networkSelectionStatus.getDisableTime()) / 1000));
            }
            sb.append(")");
        }
        int maxNetworkSelectionDisableReason = WifiConfiguration.NetworkSelectionStatus.getMaxNetworkSelectionDisableReason();
        for (int i = 0; i <= maxNetworkSelectionDisableReason; i++) {
            int disableReasonCounter = networkSelectionStatus.getDisableReasonCounter(i);
            if (disableReasonCounter != 0) {
                sb.append(" ");
                sb.append(WifiConfiguration.NetworkSelectionStatus.getNetworkSelectionDisableReasonString(i));
                sb.append("=");
                sb.append(disableReasonCounter);
            }
        }
        return sb.toString();
    }

    static String getCurrentNetworkCapabilitiesInformation(Context context, NetworkCapabilities networkCapabilities) {
        if (context != null && networkCapabilities != null) {
            if (networkCapabilities.hasCapability(17)) {
                return context.getString(context.getResources().getIdentifier("network_available_sign_in", "string", "android"));
            }
            if (networkCapabilities.hasCapability(24)) {
                return context.getString(R$string.wifi_limited_connection);
            }
            if (!networkCapabilities.hasCapability(16)) {
                if (networkCapabilities.isPrivateDnsBroken()) {
                    return context.getString(R$string.private_dns_broken);
                }
                return context.getString(R$string.wifi_connected_no_internet);
            }
        }
        return "";
    }

    static String getNetworkDetailedState(Context context, NetworkInfo networkInfo) {
        NetworkInfo.DetailedState detailedState;
        if (context == null || networkInfo == null || (detailedState = networkInfo.getDetailedState()) == null) {
            return "";
        }
        String[] stringArray = context.getResources().getStringArray(R$array.wifi_status);
        int iOrdinal = detailedState.ordinal();
        return iOrdinal >= stringArray.length ? "" : stringArray[iOrdinal];
    }

    static String getCarrierNameForSubId(Context context, int i) {
        TelephonyManager telephonyManager;
        TelephonyManager telephonyManagerCreateForSubscriptionId;
        CharSequence simCarrierIdName;
        if (i == -1 || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null || (telephonyManagerCreateForSubscriptionId = telephonyManager.createForSubscriptionId(i)) == null || (simCarrierIdName = telephonyManagerCreateForSubscriptionId.getSimCarrierIdName()) == null) {
            return null;
        }
        return simCarrierIdName.toString();
    }

    static int getSubIdForConfig(Context context, WifiConfiguration wifiConfiguration) {
        SubscriptionManager subscriptionManager;
        int subscriptionId = -1;
        if (wifiConfiguration.carrierId == -1 || (subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service")) == null) {
            return -1;
        }
        List<SubscriptionInfo> activeSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
        if (activeSubscriptionInfoList != null && !activeSubscriptionInfoList.isEmpty()) {
            int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
            for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
                if (subscriptionInfo.getCarrierId() == wifiConfiguration.carrierId && (subscriptionId = subscriptionInfo.getSubscriptionId()) == defaultDataSubscriptionId) {
                    break;
                }
            }
        }
        return subscriptionId;
    }
}
