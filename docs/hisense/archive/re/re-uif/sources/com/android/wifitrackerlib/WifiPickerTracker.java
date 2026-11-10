package com.android.wifitrackerlib;

import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.hotspot2.OsuProvider;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.core.util.Preconditions;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* JADX INFO: loaded from: classes.dex */
public class WifiPickerTracker extends BaseWifiTracker {
    private WifiEntry mConnectedWifiEntry;
    private NetworkInfo mCurrentNetworkInfo;
    private final WifiPickerTrackerCallback mListener;
    private final Object mLock;
    private NetworkRequestEntry mNetworkRequestEntry;
    private final Map<String, OsuWifiEntry> mOsuWifiEntryCache;
    private final Map<String, PasspointConfiguration> mPasspointConfigCache;
    private final Map<String, PasspointWifiEntry> mPasspointWifiEntryCache;
    private final Map<String, StandardWifiEntry> mStandardWifiEntryCache;
    private final Map<String, WifiConfiguration> mSuggestedConfigCache;
    private final Map<String, StandardWifiEntry> mSuggestedWifiEntryCache;
    private final Map<String, WifiConfiguration> mWifiConfigCache;
    private final List<WifiEntry> mWifiEntries;

    public interface WifiPickerTrackerCallback {
        void onNumSavedNetworksChanged();

        void onNumSavedSubscriptionsChanged();

        void onWifiEntriesChanged();
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleOnStart() {
        updateWifiConfigurations(this.mWifiManager.getPrivilegedConfiguredNetworks());
        updatePasspointConfigurations(this.mWifiManager.getPasspointConfigurations());
        this.mScanResultUpdater.update(this.mWifiManager.getScanResults());
        conditionallyUpdateScanResults(true);
        WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
        Network currentNetwork = this.mWifiManager.getCurrentNetwork();
        NetworkInfo networkInfo = this.mConnectivityManager.getNetworkInfo(currentNetwork);
        this.mCurrentNetworkInfo = networkInfo;
        updateConnectionInfo(connectionInfo, networkInfo);
        handleLinkPropertiesChanged(this.mConnectivityManager.getLinkProperties(currentNetwork));
        notifyOnNumSavedNetworksChanged();
        notifyOnNumSavedSubscriptionsChanged();
        updateWifiEntries();
    }

    protected void handleLinkPropertiesChanged(LinkProperties linkProperties) {
        WifiEntry wifiEntry = this.mConnectedWifiEntry;
        if (wifiEntry == null || wifiEntry.getConnectedState() != 2) {
            return;
        }
        this.mConnectedWifiEntry.updateLinkProperties(linkProperties);
    }

    private void updateWifiEntries() {
        synchronized (this.mLock) {
            StandardWifiEntry standardWifiEntryOrElse = this.mStandardWifiEntryCache.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$0TUEBOvt53oJDxdg7kKxPrqyWlc
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return WifiPickerTracker.lambda$updateWifiEntries$0((StandardWifiEntry) obj);
                }
            }).findAny().orElse(null);
            this.mConnectedWifiEntry = standardWifiEntryOrElse;
            if (standardWifiEntryOrElse == null) {
                this.mConnectedWifiEntry = this.mSuggestedWifiEntryCache.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$qhgVsdlHowIElQHRJK_fI8WN1nM
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return WifiPickerTracker.lambda$updateWifiEntries$1((StandardWifiEntry) obj);
                    }
                }).findAny().orElse(null);
            }
            if (this.mConnectedWifiEntry == null) {
                this.mConnectedWifiEntry = this.mPasspointWifiEntryCache.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$ij2wZKo1q0VBIHIbqkDbWHwhHcA
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return WifiPickerTracker.lambda$updateWifiEntries$2((PasspointWifiEntry) obj);
                    }
                }).findAny().orElse(null);
            }
            if (this.mConnectedWifiEntry == null && this.mNetworkRequestEntry != null && this.mNetworkRequestEntry.getConnectedState() != 0) {
                this.mConnectedWifiEntry = this.mNetworkRequestEntry;
            }
            this.mWifiEntries.clear();
            for (String str : this.mStandardWifiEntryCache.keySet()) {
                if (this.mConnectedWifiEntry == null || !TextUtils.equals(str, this.mConnectedWifiEntry.getKey())) {
                    StandardWifiEntry standardWifiEntry = this.mStandardWifiEntryCache.get(str);
                    StandardWifiEntry standardWifiEntry2 = this.mSuggestedWifiEntryCache.get(str);
                    if (!standardWifiEntry.isSaved() && standardWifiEntry2 != null && standardWifiEntry2.isUserShareable()) {
                        if (standardWifiEntry2.getConnectedState() == 0) {
                            this.mWifiEntries.add(standardWifiEntry2);
                        }
                    } else if (standardWifiEntry.getConnectedState() == 0) {
                        this.mWifiEntries.add(standardWifiEntry);
                    }
                }
            }
            this.mWifiEntries.addAll((Collection) this.mPasspointWifiEntryCache.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$QROaltglkm0qmJQUXPRJYd0Po2c
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return WifiPickerTracker.lambda$updateWifiEntries$3((PasspointWifiEntry) obj);
                }
            }).collect(Collectors.toList()));
            this.mWifiEntries.addAll((Collection) this.mOsuWifiEntryCache.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$RLU9K1ZzG3pjvkYPNdVuba88eBA
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return WifiPickerTracker.lambda$updateWifiEntries$4((OsuWifiEntry) obj);
                }
            }).collect(Collectors.toList()));
            Collections.sort(this.mWifiEntries);
            if (BaseWifiTracker.isVerboseLoggingEnabled()) {
                Log.v("WifiPickerTracker", "Connected WifiEntry: " + this.mConnectedWifiEntry);
                Log.v("WifiPickerTracker", "Updated WifiEntries: " + Arrays.toString(this.mWifiEntries.toArray()));
            }
        }
        notifyOnWifiEntriesChanged();
    }

    static /* synthetic */ boolean lambda$updateWifiEntries$0(StandardWifiEntry standardWifiEntry) {
        int connectedState = standardWifiEntry.getConnectedState();
        return connectedState == 2 || connectedState == 1;
    }

    static /* synthetic */ boolean lambda$updateWifiEntries$1(StandardWifiEntry standardWifiEntry) {
        int connectedState = standardWifiEntry.getConnectedState();
        return connectedState == 2 || connectedState == 1;
    }

    static /* synthetic */ boolean lambda$updateWifiEntries$2(PasspointWifiEntry passpointWifiEntry) {
        int connectedState = passpointWifiEntry.getConnectedState();
        return connectedState == 2 || connectedState == 1;
    }

    static /* synthetic */ boolean lambda$updateWifiEntries$3(PasspointWifiEntry passpointWifiEntry) {
        return passpointWifiEntry.getConnectedState() == 0;
    }

    static /* synthetic */ boolean lambda$updateWifiEntries$4(OsuWifiEntry osuWifiEntry) {
        return osuWifiEntry.getConnectedState() == 0 && !osuWifiEntry.isAlreadyProvisioned();
    }

    private void updateStandardWifiEntryScans(List<ScanResult> list) {
        Preconditions.checkNotNull(list, "Scan Result list should not be null!");
        final Map<String, List<ScanResult>> mapMapScanResultsToKey = Utils.mapScanResultsToKey(list, true, this.mWifiConfigCache, this.mWifiManager.isWpa3SaeSupported(), this.mWifiManager.isWpa3SuiteBSupported(), this.mWifiManager.isEnhancedOpenSupported());
        this.mStandardWifiEntryCache.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$BCwaIQQiP0fvxsNUSnDq9UqTFVQ
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return WifiPickerTracker.lambda$updateStandardWifiEntryScans$5(mapMapScanResultsToKey, (Map.Entry) obj);
            }
        });
        for (Map.Entry<String, List<ScanResult>> entry : mapMapScanResultsToKey.entrySet()) {
            StandardWifiEntry standardWifiEntry = new StandardWifiEntry(this.mContext, this.mMainHandler, entry.getKey(), entry.getValue(), this.mWifiManager, this.mWifiNetworkScoreCache, false);
            standardWifiEntry.updateConfig(this.mWifiConfigCache.get(standardWifiEntry.getKey()));
            this.mStandardWifiEntryCache.put(standardWifiEntry.getKey(), standardWifiEntry);
        }
    }

    static /* synthetic */ boolean lambda$updateStandardWifiEntryScans$5(Map map, Map.Entry entry) {
        String str = (String) entry.getKey();
        StandardWifiEntry standardWifiEntry = (StandardWifiEntry) entry.getValue();
        standardWifiEntry.updateScanResultInfo((List) map.remove(str));
        return standardWifiEntry.getLevel() == -1;
    }

    private void updateSuggestedWifiEntryScans(List<ScanResult> list) {
        Preconditions.checkNotNull(list, "Scan Result list should not be null!");
        Map<String, List<ScanResult>> mapMapScanResultsToKey = Utils.mapScanResultsToKey(list, true, this.mWifiConfigCache, this.mWifiManager.isWpa3SaeSupported(), this.mWifiManager.isWpa3SuiteBSupported(), this.mWifiManager.isEnhancedOpenSupported());
        Map map = (Map) this.mWifiManager.getWifiConfigForMatchedNetworkSuggestionsSharedWithUser(list).stream().collect(Collectors.toMap($$Lambda$eRhiL3TPu1j8op3nmit378jGeyk.INSTANCE, Function.identity()));
        final TreeSet treeSet = new TreeSet();
        for (String str : map.keySet()) {
            treeSet.add(str);
            if (!this.mSuggestedWifiEntryCache.containsKey(str)) {
                this.mSuggestedWifiEntryCache.put(str, new StandardWifiEntry(this.mContext, this.mMainHandler, str, (WifiConfiguration) map.get(str), this.mWifiManager, this.mWifiNetworkScoreCache, false));
            }
            StandardWifiEntry standardWifiEntry = this.mSuggestedWifiEntryCache.get(str);
            standardWifiEntry.setUserShareable(true);
            standardWifiEntry.updateScanResultInfo(mapMapScanResultsToKey.get(str));
        }
        this.mSuggestedWifiEntryCache.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$b19vs3Vj3eXbrjFN5lP9dtI7hsE
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$updateSuggestedWifiEntryScans$6$WifiPickerTracker(treeSet, (Map.Entry) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateSuggestedWifiEntryScans$6, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$updateSuggestedWifiEntryScans$6$WifiPickerTracker(Set set, Map.Entry entry) {
        StandardWifiEntry standardWifiEntry = (StandardWifiEntry) entry.getValue();
        String str = (String) entry.getKey();
        if (!set.contains(str)) {
            standardWifiEntry.updateConfig(this.mSuggestedConfigCache.get(str));
            standardWifiEntry.setUserShareable(false);
        }
        return !standardWifiEntry.isSuggestion();
    }

    private void updatePasspointWifiEntryScans(List<ScanResult> list) {
        Preconditions.checkNotNull(list, "Scan Result list should not be null!");
        final TreeSet treeSet = new TreeSet();
        for (Pair pair : this.mWifiManager.getAllMatchingWifiConfigs(list)) {
            WifiConfiguration wifiConfiguration = (WifiConfiguration) pair.first;
            List<ScanResult> list2 = (List) ((Map) pair.second).get(0);
            List<ScanResult> list3 = (List) ((Map) pair.second).get(1);
            String strUniqueIdToPasspointWifiEntryKey = PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(wifiConfiguration.getKey());
            treeSet.add(strUniqueIdToPasspointWifiEntryKey);
            if (!this.mPasspointWifiEntryCache.containsKey(strUniqueIdToPasspointWifiEntryKey)) {
                if (wifiConfiguration.fromWifiNetworkSuggestion) {
                    this.mPasspointWifiEntryCache.put(strUniqueIdToPasspointWifiEntryKey, new PasspointWifiEntry(this.mContext, this.mMainHandler, wifiConfiguration, this.mWifiManager, this.mWifiNetworkScoreCache, false));
                } else if (this.mPasspointConfigCache.containsKey(strUniqueIdToPasspointWifiEntryKey)) {
                    this.mPasspointWifiEntryCache.put(strUniqueIdToPasspointWifiEntryKey, new PasspointWifiEntry(this.mContext, this.mMainHandler, this.mPasspointConfigCache.get(strUniqueIdToPasspointWifiEntryKey), this.mWifiManager, this.mWifiNetworkScoreCache, false));
                }
            }
            this.mPasspointWifiEntryCache.get(strUniqueIdToPasspointWifiEntryKey).updateScanResultInfo(wifiConfiguration, list2, list3);
        }
        this.mPasspointWifiEntryCache.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$b5PKKEVNG30K9l5cXQHsuoupJmU
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return WifiPickerTracker.lambda$updatePasspointWifiEntryScans$7(treeSet, (Map.Entry) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$updatePasspointWifiEntryScans$7(Set set, Map.Entry entry) {
        return ((PasspointWifiEntry) entry.getValue()).getLevel() == -1 || !set.contains(entry.getKey());
    }

    private void updateOsuWifiEntryScans(List<ScanResult> list) {
        Preconditions.checkNotNull(list, "Scan Result list should not be null!");
        Map matchingOsuProviders = this.mWifiManager.getMatchingOsuProviders(list);
        final Map matchingPasspointConfigsForOsuProviders = this.mWifiManager.getMatchingPasspointConfigsForOsuProviders(matchingOsuProviders.keySet());
        for (OsuWifiEntry osuWifiEntry : this.mOsuWifiEntryCache.values()) {
            osuWifiEntry.updateScanResultInfo((List) matchingOsuProviders.remove(osuWifiEntry.getOsuProvider()));
        }
        for (OsuProvider osuProvider : matchingOsuProviders.keySet()) {
            OsuWifiEntry osuWifiEntry2 = new OsuWifiEntry(this.mContext, this.mMainHandler, osuProvider, this.mWifiManager, this.mWifiNetworkScoreCache, false);
            osuWifiEntry2.updateScanResultInfo((List) matchingOsuProviders.get(osuProvider));
            this.mOsuWifiEntryCache.put(OsuWifiEntry.osuProviderToOsuWifiEntryKey(osuProvider), osuWifiEntry2);
        }
        this.mOsuWifiEntryCache.values().forEach(new Consumer() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$v1LdO2tPR3JJiIf1xFFNAIzpUTI
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$updateOsuWifiEntryScans$8$WifiPickerTracker(matchingPasspointConfigsForOsuProviders, (OsuWifiEntry) obj);
            }
        });
        this.mOsuWifiEntryCache.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$ZvrS1GLiBdiVh6rqhik8skRXPAw
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return WifiPickerTracker.lambda$updateOsuWifiEntryScans$9((Map.Entry) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateOsuWifiEntryScans$8, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateOsuWifiEntryScans$8$WifiPickerTracker(Map map, OsuWifiEntry osuWifiEntry) {
        PasspointConfiguration passpointConfiguration = (PasspointConfiguration) map.get(osuWifiEntry.getOsuProvider());
        if (passpointConfiguration == null) {
            osuWifiEntry.setAlreadyProvisioned(false);
            return;
        }
        osuWifiEntry.setAlreadyProvisioned(true);
        PasspointWifiEntry passpointWifiEntry = this.mPasspointWifiEntryCache.get(PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(passpointConfiguration.getUniqueId()));
        if (passpointWifiEntry == null) {
            return;
        }
        passpointWifiEntry.setOsuWifiEntry(osuWifiEntry);
    }

    static /* synthetic */ boolean lambda$updateOsuWifiEntryScans$9(Map.Entry entry) {
        return ((OsuWifiEntry) entry.getValue()).getLevel() == -1;
    }

    private void updateNetworkRequestEntryScans(List<ScanResult> list) {
        Preconditions.checkNotNull(list, "Scan Result list should not be null!");
        NetworkRequestEntry networkRequestEntry = this.mNetworkRequestEntry;
        if (networkRequestEntry == null) {
            return;
        }
        final String ssid = networkRequestEntry.getSsid();
        final int security = this.mNetworkRequestEntry.getSecurity();
        this.mNetworkRequestEntry.updateScanResultInfo((List) list.stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$pqxDzYwc6dZqh70kHuMaEBXjaV4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return WifiPickerTracker.lambda$updateNetworkRequestEntryScans$10(ssid, security, (ScanResult) obj);
            }
        }).collect(Collectors.toList()));
    }

    static /* synthetic */ boolean lambda$updateNetworkRequestEntryScans$10(String str, int i, ScanResult scanResult) {
        return TextUtils.equals(scanResult.SSID, str) && Utils.getSecurityTypesFromScanResult(scanResult).contains(Integer.valueOf(i));
    }

    private void conditionallyUpdateScanResults(boolean z) {
        if (this.mWifiManager.getWifiState() == 1) {
            updateStandardWifiEntryScans(Collections.emptyList());
            updateSuggestedWifiEntryScans(Collections.emptyList());
            updatePasspointWifiEntryScans(Collections.emptyList());
            updateOsuWifiEntryScans(Collections.emptyList());
            updateNetworkRequestEntryScans(Collections.emptyList());
            return;
        }
        long j = this.mMaxScanAgeMillis;
        if (z) {
            this.mScanResultUpdater.update(this.mWifiManager.getScanResults());
        } else {
            j += this.mScanIntervalMillis;
        }
        List<ScanResult> scanResults = this.mScanResultUpdater.getScanResults(j);
        updateStandardWifiEntryScans(scanResults);
        updateSuggestedWifiEntryScans(scanResults);
        updatePasspointWifiEntryScans(scanResults);
        updateOsuWifiEntryScans(scanResults);
        updateNetworkRequestEntryScans(scanResults);
    }

    private void updateWifiConfigurations(List<WifiConfiguration> list) {
        Preconditions.checkNotNull(list, "Config list should not be null!");
        this.mWifiConfigCache.clear();
        this.mSuggestedConfigCache.clear();
        boolean z = false;
        for (WifiConfiguration wifiConfiguration : list) {
            if (wifiConfiguration.fromWifiNetworkSuggestion) {
                this.mSuggestedConfigCache.put(StandardWifiEntry.wifiConfigToStandardWifiEntryKey(wifiConfiguration), wifiConfiguration);
            } else if (wifiConfiguration.fromWifiNetworkSpecifier) {
                z = true;
                updateNetworkRequestConfig(wifiConfiguration);
            } else {
                this.mWifiConfigCache.put(StandardWifiEntry.wifiConfigToStandardWifiEntryKey(wifiConfiguration), wifiConfiguration);
            }
        }
        if (!z) {
            updateNetworkRequestConfig(null);
        }
        this.mWifiConfigCache.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$Ge-PaFPqD_aenaiEba32ZGYPo9E
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return WifiPickerTracker.lambda$updateWifiConfigurations$12((WifiConfiguration) obj);
            }
        }).count();
        this.mStandardWifiEntryCache.entrySet().forEach(new Consumer() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$nziraTiXnLOckrKYTwmswRtPvWo
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$updateWifiConfigurations$13$WifiPickerTracker((Map.Entry) obj);
            }
        });
        this.mSuggestedWifiEntryCache.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$n1MQJLze79Jma9-HXue3y6-3vJ8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$updateWifiConfigurations$14$WifiPickerTracker((Map.Entry) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$updateWifiConfigurations$12(WifiConfiguration wifiConfiguration) {
        return (wifiConfiguration.isEphemeral() || wifiConfiguration.isPasspoint()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateWifiConfigurations$13, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateWifiConfigurations$13$WifiPickerTracker(Map.Entry entry) {
        StandardWifiEntry standardWifiEntry = (StandardWifiEntry) entry.getValue();
        WifiConfiguration wifiConfiguration = this.mWifiConfigCache.get(standardWifiEntry.getKey());
        if (wifiConfiguration == null || !wifiConfiguration.isPasspoint()) {
            standardWifiEntry.updateConfig(wifiConfiguration);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateWifiConfigurations$14, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$updateWifiConfigurations$14$WifiPickerTracker(Map.Entry entry) {
        StandardWifiEntry standardWifiEntry = (StandardWifiEntry) entry.getValue();
        WifiConfiguration wifiConfiguration = this.mSuggestedConfigCache.get(standardWifiEntry.getKey());
        if (wifiConfiguration == null || wifiConfiguration.isPasspoint()) {
            return true;
        }
        standardWifiEntry.updateConfig(wifiConfiguration);
        return false;
    }

    private void updateNetworkRequestConfig(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration == null) {
            this.mNetworkRequestEntry = null;
            return;
        }
        String strWifiConfigToNetworkRequestEntryKey = NetworkRequestEntry.wifiConfigToNetworkRequestEntryKey(wifiConfiguration);
        NetworkRequestEntry networkRequestEntry = this.mNetworkRequestEntry;
        if (networkRequestEntry == null || !TextUtils.equals(strWifiConfigToNetworkRequestEntryKey, networkRequestEntry.getKey())) {
            this.mNetworkRequestEntry = new NetworkRequestEntry(this.mContext, this.mMainHandler, strWifiConfigToNetworkRequestEntryKey, this.mWifiManager, this.mWifiNetworkScoreCache, false);
        }
        this.mNetworkRequestEntry.updateConfig(wifiConfiguration);
    }

    private void updatePasspointConfigurations(List<PasspointConfiguration> list) {
        Preconditions.checkNotNull(list, "Config list should not be null!");
        this.mPasspointConfigCache.clear();
        this.mPasspointConfigCache.putAll((Map) list.stream().collect(Collectors.toMap(new Function() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$NZj8llmsS2xh549r2-eluZN8xzY
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(((PasspointConfiguration) obj).getUniqueId());
            }
        }, Function.identity())));
        this.mPasspointWifiEntryCache.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$pk3mnes5HX5A_t5O7BAlvARDDy4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$updatePasspointConfigurations$16$WifiPickerTracker((Map.Entry) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updatePasspointConfigurations$16, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$updatePasspointConfigurations$16$WifiPickerTracker(Map.Entry entry) {
        PasspointWifiEntry passpointWifiEntry = (PasspointWifiEntry) entry.getValue();
        passpointWifiEntry.updatePasspointConfig(this.mPasspointConfigCache.get(passpointWifiEntry.getKey()));
        return (passpointWifiEntry.isSubscription() || passpointWifiEntry.isSuggestion()) ? false : true;
    }

    private void updateConnectionInfo(WifiInfo wifiInfo, NetworkInfo networkInfo) {
        Iterator<StandardWifiEntry> it = this.mStandardWifiEntryCache.values().iterator();
        while (it.hasNext()) {
            it.next().updateConnectionInfo(wifiInfo, networkInfo);
        }
        Iterator<StandardWifiEntry> it2 = this.mSuggestedWifiEntryCache.values().iterator();
        while (it2.hasNext()) {
            it2.next().updateConnectionInfo(wifiInfo, networkInfo);
        }
        Iterator<PasspointWifiEntry> it3 = this.mPasspointWifiEntryCache.values().iterator();
        while (it3.hasNext()) {
            it3.next().updateConnectionInfo(wifiInfo, networkInfo);
        }
        Iterator<OsuWifiEntry> it4 = this.mOsuWifiEntryCache.values().iterator();
        while (it4.hasNext()) {
            it4.next().updateConnectionInfo(wifiInfo, networkInfo);
        }
        NetworkRequestEntry networkRequestEntry = this.mNetworkRequestEntry;
        if (networkRequestEntry != null) {
            networkRequestEntry.updateConnectionInfo(wifiInfo, networkInfo);
        }
        conditionallyCreateConnectedStandardWifiEntry(wifiInfo, networkInfo);
        conditionallyCreateConnectedSuggestedWifiEntry(wifiInfo, networkInfo);
        conditionallyCreateConnectedPasspointWifiEntry(wifiInfo, networkInfo);
    }

    private void conditionallyCreateConnectedStandardWifiEntry(final WifiInfo wifiInfo, final NetworkInfo networkInfo) {
        if (wifiInfo.isPasspointAp() || wifiInfo.isOsuAp()) {
            return;
        }
        final int networkId = wifiInfo.getNetworkId();
        this.mWifiConfigCache.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$DitgtO9_1wALYexVACURnNqwLlI
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$conditionallyCreateConnectedStandardWifiEntry$17$WifiPickerTracker(networkId, (WifiConfiguration) obj);
            }
        }).findAny().ifPresent(new Consumer() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$PEwbf5ZKF_Aj2ns46UMv4q1HGVo
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$conditionallyCreateConnectedStandardWifiEntry$18$WifiPickerTracker(wifiInfo, networkInfo, (WifiConfiguration) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$conditionallyCreateConnectedStandardWifiEntry$17, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$conditionallyCreateConnectedStandardWifiEntry$17$WifiPickerTracker(int i, WifiConfiguration wifiConfiguration) {
        return wifiConfiguration.networkId == i && !this.mStandardWifiEntryCache.containsKey(StandardWifiEntry.wifiConfigToStandardWifiEntryKey(wifiConfiguration));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$conditionallyCreateConnectedStandardWifiEntry$18, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$conditionallyCreateConnectedStandardWifiEntry$18$WifiPickerTracker(WifiInfo wifiInfo, NetworkInfo networkInfo, WifiConfiguration wifiConfiguration) {
        StandardWifiEntry standardWifiEntry = new StandardWifiEntry(this.mContext, this.mMainHandler, StandardWifiEntry.wifiConfigToStandardWifiEntryKey(wifiConfiguration), wifiConfiguration, this.mWifiManager, this.mWifiNetworkScoreCache, false);
        standardWifiEntry.updateConnectionInfo(wifiInfo, networkInfo);
        this.mStandardWifiEntryCache.put(standardWifiEntry.getKey(), standardWifiEntry);
    }

    private void conditionallyCreateConnectedSuggestedWifiEntry(final WifiInfo wifiInfo, final NetworkInfo networkInfo) {
        if (wifiInfo.isPasspointAp() || wifiInfo.isOsuAp()) {
            return;
        }
        final int networkId = wifiInfo.getNetworkId();
        this.mSuggestedConfigCache.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$tJL8mW_AZGayOGupUDCgP3_UlzQ
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$conditionallyCreateConnectedSuggestedWifiEntry$19$WifiPickerTracker(networkId, (WifiConfiguration) obj);
            }
        }).findAny().ifPresent(new Consumer() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$BFLWShdPM2NcgUJrV0IHcg4MXWc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$conditionallyCreateConnectedSuggestedWifiEntry$20$WifiPickerTracker(wifiInfo, networkInfo, (WifiConfiguration) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$conditionallyCreateConnectedSuggestedWifiEntry$19, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$conditionallyCreateConnectedSuggestedWifiEntry$19$WifiPickerTracker(int i, WifiConfiguration wifiConfiguration) {
        return wifiConfiguration.networkId == i && !this.mSuggestedWifiEntryCache.containsKey(StandardWifiEntry.wifiConfigToStandardWifiEntryKey(wifiConfiguration));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$conditionallyCreateConnectedSuggestedWifiEntry$20, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$conditionallyCreateConnectedSuggestedWifiEntry$20$WifiPickerTracker(WifiInfo wifiInfo, NetworkInfo networkInfo, WifiConfiguration wifiConfiguration) {
        StandardWifiEntry standardWifiEntry = new StandardWifiEntry(this.mContext, this.mMainHandler, StandardWifiEntry.wifiConfigToStandardWifiEntryKey(wifiConfiguration), wifiConfiguration, this.mWifiManager, this.mWifiNetworkScoreCache, false);
        standardWifiEntry.updateConnectionInfo(wifiInfo, networkInfo);
        this.mSuggestedWifiEntryCache.put(standardWifiEntry.getKey(), standardWifiEntry);
    }

    private void conditionallyCreateConnectedPasspointWifiEntry(final WifiInfo wifiInfo, final NetworkInfo networkInfo) {
        if (wifiInfo.isPasspointAp()) {
            final int networkId = wifiInfo.getNetworkId();
            Stream.concat(this.mWifiConfigCache.values().stream(), this.mSuggestedConfigCache.values().stream()).filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$bjLRrQ4OQ8BTAnYZyvpe1EHxYpE
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.f$0.lambda$conditionallyCreateConnectedPasspointWifiEntry$21$WifiPickerTracker(networkId, (WifiConfiguration) obj);
                }
            }).findAny().ifPresent(new Consumer() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$g9rZ4NCkczW2OW23M8uCJ7orWlk
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$conditionallyCreateConnectedPasspointWifiEntry$22$WifiPickerTracker(wifiInfo, networkInfo, (WifiConfiguration) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$conditionallyCreateConnectedPasspointWifiEntry$21, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$conditionallyCreateConnectedPasspointWifiEntry$21$WifiPickerTracker(int i, WifiConfiguration wifiConfiguration) {
        return wifiConfiguration.isPasspoint() && wifiConfiguration.networkId == i && !this.mPasspointWifiEntryCache.containsKey(PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(wifiConfiguration.getKey()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$conditionallyCreateConnectedPasspointWifiEntry$22, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$conditionallyCreateConnectedPasspointWifiEntry$22$WifiPickerTracker(WifiInfo wifiInfo, NetworkInfo networkInfo, WifiConfiguration wifiConfiguration) {
        PasspointWifiEntry passpointWifiEntry;
        PasspointConfiguration passpointConfiguration = this.mPasspointConfigCache.get(PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(wifiConfiguration.getKey()));
        if (passpointConfiguration != null) {
            passpointWifiEntry = new PasspointWifiEntry(this.mContext, this.mMainHandler, passpointConfiguration, this.mWifiManager, this.mWifiNetworkScoreCache, false);
        } else {
            passpointWifiEntry = new PasspointWifiEntry(this.mContext, this.mMainHandler, wifiConfiguration, this.mWifiManager, this.mWifiNetworkScoreCache, false);
        }
        passpointWifiEntry.updateConnectionInfo(wifiInfo, networkInfo);
        this.mPasspointWifiEntryCache.put(passpointWifiEntry.getKey(), passpointWifiEntry);
    }

    private void notifyOnWifiEntriesChanged() {
        final WifiPickerTrackerCallback wifiPickerTrackerCallback = this.mListener;
        if (wifiPickerTrackerCallback != null) {
            Handler handler = this.mMainHandler;
            Objects.requireNonNull(wifiPickerTrackerCallback);
            handler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$5hHTXQ3X9FmFmB6qtFasRAuw8jY
                @Override // java.lang.Runnable
                public final void run() {
                    wifiPickerTrackerCallback.onWifiEntriesChanged();
                }
            });
        }
    }

    private void notifyOnNumSavedNetworksChanged() {
        final WifiPickerTrackerCallback wifiPickerTrackerCallback = this.mListener;
        if (wifiPickerTrackerCallback != null) {
            Handler handler = this.mMainHandler;
            Objects.requireNonNull(wifiPickerTrackerCallback);
            handler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$fOs_tKYRPCSHYuhDjdNG_-Oy-no
                @Override // java.lang.Runnable
                public final void run() {
                    wifiPickerTrackerCallback.onNumSavedNetworksChanged();
                }
            });
        }
    }

    private void notifyOnNumSavedSubscriptionsChanged() {
        final WifiPickerTrackerCallback wifiPickerTrackerCallback = this.mListener;
        if (wifiPickerTrackerCallback != null) {
            Handler handler = this.mMainHandler;
            Objects.requireNonNull(wifiPickerTrackerCallback);
            handler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$pqRUdfn3mQVx4DYE6gYgaQJgj0E
                @Override // java.lang.Runnable
                public final void run() {
                    wifiPickerTrackerCallback.onNumSavedSubscriptionsChanged();
                }
            });
        }
    }
}
