package com.android.wifitrackerlib;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.NetworkScoreManager;
import android.net.NetworkScorerAppData;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.core.util.Preconditions;
import com.android.internal.annotations.VisibleForTesting;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public class StandardWifiEntry extends WifiEntry {
    private final Context mContext;
    private final List<ScanResult> mCurrentScanResults;
    private boolean mIsUserShareable;
    private final String mKey;
    private final Object mLock;
    private String mRecommendationServiceLabel;
    private final int mSecurity;
    private final String mSsid;
    private WifiConfiguration mWifiConfig;

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isSubscription() {
        return false;
    }

    StandardWifiEntry(Context context, Handler handler, String str, List<ScanResult> list, WifiManager wifiManager, WifiNetworkScoreCache wifiNetworkScoreCache, boolean z) throws IllegalArgumentException {
        this(context, handler, str, wifiManager, wifiNetworkScoreCache, z);
        Preconditions.checkNotNull(list, "Cannot construct with null ScanResult list!");
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Cannot construct with empty ScanResult list!");
        }
        updateScanResultInfo(list);
        updateRecommendationServiceLabel();
    }

    StandardWifiEntry(Context context, Handler handler, String str, WifiConfiguration wifiConfiguration, WifiManager wifiManager, WifiNetworkScoreCache wifiNetworkScoreCache, boolean z) throws IllegalArgumentException {
        this(context, handler, str, wifiManager, wifiNetworkScoreCache, z);
        Preconditions.checkNotNull(wifiConfiguration, "Cannot construct with null config!");
        Preconditions.checkNotNull(wifiConfiguration.SSID, "Supplied config must have an SSID!");
        this.mWifiConfig = wifiConfiguration;
        updateRecommendationServiceLabel();
    }

    StandardWifiEntry(Context context, Handler handler, String str, WifiManager wifiManager, WifiNetworkScoreCache wifiNetworkScoreCache, boolean z) {
        super(handler, wifiManager, wifiNetworkScoreCache, z);
        this.mLock = new Object();
        this.mCurrentScanResults = new ArrayList();
        this.mIsUserShareable = false;
        this.mContext = context;
        this.mKey = str;
        try {
            int iIndexOf = str.indexOf(":");
            int iLastIndexOf = str.lastIndexOf(",");
            this.mSsid = str.substring(iIndexOf + 1, iLastIndexOf);
            this.mSecurity = Integer.valueOf(str.substring(iLastIndexOf + 1)).intValue();
            updateRecommendationServiceLabel();
        } catch (NumberFormatException | StringIndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("Malformed key: " + str);
        }
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getKey() {
        return this.mKey;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getTitle() {
        return this.mSsid;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getSummary(boolean z) {
        StringJoiner stringJoiner = new StringJoiner(this.mContext.getString(R$string.summary_separator));
        if (!z && this.mForSavedNetworksPage && isSaved()) {
            String appLabel = Utils.getAppLabel(this.mContext, this.mWifiConfig.creatorName);
            if (!TextUtils.isEmpty(appLabel)) {
                stringJoiner.add(this.mContext.getString(R$string.saved_network, appLabel));
            }
        }
        if (getConnectedState() == 0) {
            String disconnectedStateDescription = Utils.getDisconnectedStateDescription(this.mContext, this);
            if (!TextUtils.isEmpty(disconnectedStateDescription)) {
                stringJoiner.add(disconnectedStateDescription);
            } else if (z) {
                stringJoiner.add(this.mContext.getString(R$string.wifi_disconnected));
            } else if (!this.mForSavedNetworksPage) {
                if (isSuggestion()) {
                    Context context = this.mContext;
                    String carrierNameForSubId = Utils.getCarrierNameForSubId(context, Utils.getSubIdForConfig(context, this.mWifiConfig));
                    String appLabel2 = Utils.getAppLabel(this.mContext, this.mWifiConfig.creatorName);
                    if (TextUtils.isEmpty(appLabel2)) {
                        appLabel2 = this.mWifiConfig.creatorName;
                    }
                    Context context2 = this.mContext;
                    int i = R$string.available_via_app;
                    Object[] objArr = new Object[1];
                    if (carrierNameForSubId == null) {
                        carrierNameForSubId = appLabel2;
                    }
                    objArr[0] = carrierNameForSubId;
                    stringJoiner.add(context2.getString(i, objArr));
                } else if (isSaved()) {
                    stringJoiner.add(this.mContext.getString(R$string.wifi_remembered));
                }
            }
        } else {
            String connectStateDescription = getConnectStateDescription();
            if (!TextUtils.isEmpty(connectStateDescription)) {
                stringJoiner.add(connectStateDescription);
            }
        }
        String speedDescription = Utils.getSpeedDescription(this.mContext, this);
        if (!TextUtils.isEmpty(speedDescription)) {
            stringJoiner.add(speedDescription);
        }
        String autoConnectDescription = Utils.getAutoConnectDescription(this.mContext, this);
        if (!TextUtils.isEmpty(autoConnectDescription)) {
            stringJoiner.add(autoConnectDescription);
        }
        String meteredDescription = Utils.getMeteredDescription(this.mContext, this);
        if (!TextUtils.isEmpty(meteredDescription)) {
            stringJoiner.add(meteredDescription);
        }
        if (!z) {
            String verboseLoggingDescription = Utils.getVerboseLoggingDescription(this);
            if (!TextUtils.isEmpty(verboseLoggingDescription)) {
                stringJoiner.add(verboseLoggingDescription);
            }
        }
        return stringJoiner.toString();
    }

    private String getConnectStateDescription() {
        if (getConnectedState() == 2) {
            WifiInfo wifiInfo = this.mWifiInfo;
            String carrierNameForSubId = null;
            String requestingPackageName = wifiInfo != null ? wifiInfo.getRequestingPackageName() : null;
            if (!TextUtils.isEmpty(requestingPackageName)) {
                WifiConfiguration wifiConfiguration = this.mWifiConfig;
                if (wifiConfiguration != null) {
                    Context context = this.mContext;
                    carrierNameForSubId = Utils.getCarrierNameForSubId(context, Utils.getSubIdForConfig(context, wifiConfiguration));
                }
                String appLabel = Utils.getAppLabel(this.mContext, requestingPackageName);
                if (!TextUtils.isEmpty(appLabel)) {
                    requestingPackageName = appLabel;
                }
                Context context2 = this.mContext;
                int i = R$string.connected_via_app;
                Object[] objArr = new Object[1];
                if (carrierNameForSubId == null) {
                    carrierNameForSubId = requestingPackageName;
                }
                objArr[0] = carrierNameForSubId;
                return context2.getString(i, objArr);
            }
            if (!isSaved() && !isSuggestion()) {
                return !TextUtils.isEmpty(this.mRecommendationServiceLabel) ? String.format(this.mContext.getString(R$string.connected_via_network_scorer), this.mRecommendationServiceLabel) : this.mContext.getString(R$string.connected_via_network_scorer_default);
            }
            String currentNetworkCapabilitiesInformation = Utils.getCurrentNetworkCapabilitiesInformation(this.mContext, this.mNetworkCapabilities);
            if (!TextUtils.isEmpty(currentNetworkCapabilitiesInformation)) {
                return currentNetworkCapabilitiesInformation;
            }
        }
        return Utils.getNetworkDetailedState(this.mContext, this.mNetworkInfo);
    }

    public String getSsid() {
        return this.mSsid;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public int getSecurity() {
        return this.mSecurity;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isMetered() {
        if (getMeteredChoice() == 1) {
            return true;
        }
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        return wifiConfiguration != null && wifiConfiguration.meteredHint;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isSaved() {
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        return (wifiConfiguration == null || wifiConfiguration.isEphemeral()) ? false : true;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isSuggestion() {
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        return wifiConfiguration != null && wifiConfiguration.fromWifiNetworkSuggestion;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public WifiConfiguration getWifiConfiguration() {
        if (isSaved()) {
            return this.mWifiConfig;
        }
        return null;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public WifiEntry.ConnectedInfo getConnectedInfo() {
        return this.mConnectedInfo;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public int getMeteredChoice() {
        if (getWifiConfiguration() == null) {
            return 0;
        }
        int i = getWifiConfiguration().meteredOverride;
        if (i == 1) {
            return 1;
        }
        return i == 2 ? 2 : 0;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canSetMeteredChoice() {
        return getWifiConfiguration() != null;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isAutoJoinEnabled() {
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        if (wifiConfiguration == null) {
            return false;
        }
        return wifiConfiguration.allowAutojoin;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canSetAutoJoinEnabled() {
        return isSaved() || isSuggestion();
    }

    void updateScanResultInfo(List<ScanResult> list) throws IllegalArgumentException {
        if (list == null) {
            list = new ArrayList<>();
        }
        for (ScanResult scanResult : list) {
            if (!TextUtils.equals(scanResult.SSID, this.mSsid)) {
                throw new IllegalArgumentException("Attempted to update with wrong SSID! Expected: " + this.mSsid + ", Actual: " + scanResult.SSID + ", ScanResult: " + scanResult);
            }
        }
        synchronized (this.mLock) {
            this.mCurrentScanResults.clear();
            this.mCurrentScanResults.addAll(list);
        }
        ScanResult bestScanResultByLevel = Utils.getBestScanResultByLevel(list);
        if (bestScanResultByLevel != null) {
            updateEapType(bestScanResultByLevel);
            updatePskType(bestScanResultByLevel);
        }
        if (getConnectedState() == 0) {
            this.mLevel = bestScanResultByLevel != null ? this.mWifiManager.calculateSignalLevel(bestScanResultByLevel.level) : -1;
            synchronized (this.mLock) {
                this.mSpeed = Utils.getAverageSpeedFromScanResults(this.mScoreCache, this.mCurrentScanResults);
            }
        }
        notifyOnUpdated();
    }

    private void updateEapType(ScanResult scanResult) {
        if (scanResult.capabilities.contains("RSN-EAP")) {
            return;
        }
        scanResult.capabilities.contains("WPA-EAP");
    }

    private void updatePskType(ScanResult scanResult) {
        if (this.mSecurity != 2) {
            return;
        }
        scanResult.capabilities.contains("WPA-PSK");
        scanResult.capabilities.contains("RSN-PSK");
    }

    void updateConfig(WifiConfiguration wifiConfiguration) throws IllegalArgumentException {
        if (wifiConfiguration != null) {
            if (!TextUtils.equals(this.mSsid, WifiInfo.sanitizeSsid(wifiConfiguration.SSID))) {
                throw new IllegalArgumentException("Attempted to update with wrong SSID! Expected: " + this.mSsid + ", Actual: " + WifiInfo.sanitizeSsid(wifiConfiguration.SSID) + ", Config: " + wifiConfiguration);
            }
            if (this.mSecurity != Utils.getSecurityTypeFromWifiConfiguration(wifiConfiguration)) {
                throw new IllegalArgumentException("Attempted to update with wrong security! Expected: " + this.mSecurity + ", Actual: " + Utils.getSecurityTypeFromWifiConfiguration(wifiConfiguration) + ", Config: " + wifiConfiguration);
            }
        }
        this.mWifiConfig = wifiConfiguration;
        notifyOnUpdated();
    }

    void setUserShareable(boolean z) {
        this.mIsUserShareable = z;
    }

    boolean isUserShareable() {
        return this.mIsUserShareable;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    protected boolean connectionInfoMatches(WifiInfo wifiInfo, NetworkInfo networkInfo) {
        WifiConfiguration wifiConfiguration;
        return (wifiInfo.isPasspointAp() || wifiInfo.isOsuAp() || (wifiConfiguration = this.mWifiConfig) == null || wifiConfiguration.networkId != wifiInfo.getNetworkId()) ? false : true;
    }

    private void updateRecommendationServiceLabel() {
        NetworkScorerAppData activeScorer = ((NetworkScoreManager) this.mContext.getSystemService("network_score")).getActiveScorer();
        if (activeScorer != null) {
            this.mRecommendationServiceLabel = activeScorer.getRecommendationServiceLabel();
        }
    }

    static String ssidAndSecurityToStandardWifiEntryKey(String str, int i) {
        return "StandardWifiEntry:" + str + "," + i;
    }

    static String wifiConfigToStandardWifiEntryKey(WifiConfiguration wifiConfiguration) {
        Preconditions.checkNotNull(wifiConfiguration, "Cannot create key with null config!");
        Preconditions.checkNotNull(wifiConfiguration.SSID, "Cannot create key with null SSID in config!");
        return "StandardWifiEntry:" + WifiInfo.sanitizeSsid(wifiConfiguration.SSID) + "," + Utils.getSecurityTypeFromWifiConfiguration(wifiConfiguration);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    String getScanResultDescription() {
        synchronized (this.mLock) {
            if (this.mCurrentScanResults.size() == 0) {
                return "";
            }
            return "[" + getScanResultDescription(2400, 2500) + ";" + getScanResultDescription(4900, 5900) + ";" + getScanResultDescription(5925, 7125) + "]";
        }
    }

    private String getScanResultDescription(final int i, final int i2) {
        List list;
        synchronized (this.mLock) {
            list = (List) this.mCurrentScanResults.stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$StandardWifiEntry$lKgEQcmtM1x3SpHuutK3I2-nfI0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return StandardWifiEntry.lambda$getScanResultDescription$2(i, i2, (ScanResult) obj);
                }
            }).sorted(Comparator.comparingInt(new ToIntFunction() { // from class: com.android.wifitrackerlib.-$$Lambda$StandardWifiEntry$Lr4BrIBW8EpwljEjYsXvjw-UzPU
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return StandardWifiEntry.lambda$getScanResultDescription$3((ScanResult) obj);
                }
            })).collect(Collectors.toList());
        }
        int size = list.size();
        if (size == 0) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(size);
        sb.append(")");
        if (size > 4) {
            int asInt = list.stream().mapToInt(new ToIntFunction() { // from class: com.android.wifitrackerlib.-$$Lambda$StandardWifiEntry$ulMGK6KYyQVXHFy8lpHK9UIg2Q4
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((ScanResult) obj).level;
                }
            }).max().getAsInt();
            sb.append("max=");
            sb.append(asInt);
            sb.append(",");
        }
        final long jElapsedRealtime = SystemClock.elapsedRealtime();
        list.forEach(new Consumer() { // from class: com.android.wifitrackerlib.-$$Lambda$StandardWifiEntry$HDaxgAFxNOzpZGjcKD6Vxnrfnp4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$getScanResultDescription$5$StandardWifiEntry(sb, jElapsedRealtime, (ScanResult) obj);
            }
        });
        return sb.toString();
    }

    static /* synthetic */ boolean lambda$getScanResultDescription$2(int i, int i2, ScanResult scanResult) {
        int i3 = scanResult.frequency;
        return i3 >= i && i3 <= i2;
    }

    static /* synthetic */ int lambda$getScanResultDescription$3(ScanResult scanResult) {
        return scanResult.level * (-1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$getScanResultDescription$5, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$getScanResultDescription$5$StandardWifiEntry(StringBuilder sb, long j, ScanResult scanResult) {
        sb.append(getScanResultDescription(scanResult, j));
    }

    private String getScanResultDescription(ScanResult scanResult, long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(" \n{");
        sb.append(scanResult.BSSID);
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo != null && scanResult.BSSID.equals(wifiInfo.getBSSID())) {
            sb.append("*");
        }
        sb.append("=");
        sb.append(scanResult.frequency);
        sb.append(",");
        sb.append(scanResult.level);
        int i = ((int) (j - (scanResult.timestamp / 1000))) / 1000;
        sb.append(",");
        sb.append(i);
        sb.append("s");
        sb.append("}");
        return sb.toString();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    String getNetworkSelectionDescription() {
        return Utils.getNetworkSelectionDescription(getWifiConfiguration());
    }
}
