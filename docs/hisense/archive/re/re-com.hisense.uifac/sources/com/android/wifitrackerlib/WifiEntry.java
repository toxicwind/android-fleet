package com.android.wifitrackerlib;

import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkUtils;
import android.net.RouteInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.os.Handler;
import androidx.core.util.Preconditions;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public abstract class WifiEntry implements Comparable<WifiEntry> {
    protected Handler mCallbackHandler;
    protected ConnectCallback mConnectCallback;
    protected ConnectedInfo mConnectedInfo;
    protected DisconnectCallback mDisconnectCallback;
    final boolean mForSavedNetworksPage;
    private WifiEntryCallback mListener;
    protected NetworkCapabilities mNetworkCapabilities;
    protected NetworkInfo mNetworkInfo;
    protected WifiNetworkScoreCache mScoreCache;
    protected WifiInfo mWifiInfo;
    protected final WifiManager mWifiManager;
    protected int mLevel = -1;
    protected int mSpeed = 0;
    protected boolean mCalledConnect = false;
    protected boolean mCalledDisconnect = false;

    public interface ConnectCallback {
        void onConnectResult(int i);
    }

    public interface DisconnectCallback {
        void onDisconnectResult(int i);
    }

    public interface WifiEntryCallback {
        void onUpdated();
    }

    public abstract boolean canSetAutoJoinEnabled();

    public abstract boolean canSetMeteredChoice();

    protected abstract boolean connectionInfoMatches(WifiInfo wifiInfo, NetworkInfo networkInfo);

    public abstract String getKey();

    public abstract int getMeteredChoice();

    String getNetworkSelectionDescription() {
        return "";
    }

    abstract String getScanResultDescription();

    public abstract int getSecurity();

    public abstract String getSummary(boolean z);

    public abstract String getTitle();

    public abstract WifiConfiguration getWifiConfiguration();

    public abstract boolean isAutoJoinEnabled();

    public abstract boolean isMetered();

    public abstract boolean isSaved();

    public abstract boolean isSubscription();

    public abstract boolean isSuggestion();

    WifiEntry(Handler handler, WifiManager wifiManager, WifiNetworkScoreCache wifiNetworkScoreCache, boolean z) throws IllegalArgumentException {
        Preconditions.checkNotNull(handler, "Cannot construct with null handler!");
        Preconditions.checkNotNull(wifiManager, "Cannot construct with null WifiManager!");
        this.mCallbackHandler = handler;
        this.mForSavedNetworksPage = z;
        this.mWifiManager = wifiManager;
        this.mScoreCache = wifiNetworkScoreCache;
    }

    public int getConnectedState() {
        NetworkInfo networkInfo = this.mNetworkInfo;
        if (networkInfo == null) {
            return 0;
        }
        switch (AnonymousClass1.$SwitchMap$android$net$NetworkInfo$DetailedState[networkInfo.getDetailedState().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return 1;
            case 7:
                return 2;
            default:
                return 0;
        }
    }

    /* JADX INFO: renamed from: com.android.wifitrackerlib.WifiEntry$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$net$NetworkInfo$DetailedState;

        static {
            int[] iArr = new int[NetworkInfo.DetailedState.values().length];
            $SwitchMap$android$net$NetworkInfo$DetailedState = iArr;
            try {
                iArr[NetworkInfo.DetailedState.SCANNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$DetailedState[NetworkInfo.DetailedState.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$DetailedState[NetworkInfo.DetailedState.AUTHENTICATING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$DetailedState[NetworkInfo.DetailedState.OBTAINING_IPADDR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$DetailedState[NetworkInfo.DetailedState.VERIFYING_POOR_LINK.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$DetailedState[NetworkInfo.DetailedState.CAPTIVE_PORTAL_CHECK.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$DetailedState[NetworkInfo.DetailedState.CONNECTED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public String getSummary() {
        return getSummary(true);
    }

    public int getLevel() {
        return this.mLevel;
    }

    public int getSpeed() {
        return this.mSpeed;
    }

    public ConnectedInfo getConnectedInfo() {
        if (getConnectedState() != 2) {
            return null;
        }
        return this.mConnectedInfo;
    }

    public static class ConnectedInfo {
        public List<String> dnsServers;
        public int frequencyMhz;
        public String gateway;
        public String ipAddress;
        public List<String> ipv6Addresses;
        public int linkSpeedMbps;
        public String subnetMask;

        public ConnectedInfo() {
            new ArrayList();
            new ArrayList();
        }
    }

    public void setListener(WifiEntryCallback wifiEntryCallback) {
        this.mListener = wifiEntryCallback;
    }

    protected void notifyOnUpdated() {
        if (this.mListener != null) {
            this.mCallbackHandler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiEntry$Z7qIuj7K1pqbGQNunqibzqO18s0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$notifyOnUpdated$0$WifiEntry();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$notifyOnUpdated$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$notifyOnUpdated$0$WifiEntry() {
        this.mListener.onUpdated();
    }

    void updateConnectionInfo(WifiInfo wifiInfo, NetworkInfo networkInfo) {
        if (wifiInfo != null && networkInfo != null && connectionInfoMatches(wifiInfo, networkInfo)) {
            this.mWifiInfo = wifiInfo;
            this.mNetworkInfo = networkInfo;
            int rssi = wifiInfo.getRssi();
            if (rssi != -127) {
                this.mLevel = this.mWifiManager.calculateSignalLevel(rssi);
                this.mSpeed = Utils.getSpeedFromWifiInfo(this.mScoreCache, wifiInfo);
            }
            if (getConnectedState() == 2) {
                if (this.mCalledConnect) {
                    this.mCalledConnect = false;
                    this.mCallbackHandler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiEntry$mJZ4Rvce3-rMD9mRMpz-vWu5ItY
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$updateConnectionInfo$1$WifiEntry();
                        }
                    });
                }
                if (this.mConnectedInfo == null) {
                    this.mConnectedInfo = new ConnectedInfo();
                }
                this.mConnectedInfo.frequencyMhz = wifiInfo.getFrequency();
                this.mConnectedInfo.linkSpeedMbps = wifiInfo.getLinkSpeed();
            }
        } else {
            this.mNetworkInfo = null;
            this.mNetworkCapabilities = null;
            this.mConnectedInfo = null;
            if (this.mCalledDisconnect) {
                this.mCalledDisconnect = false;
                this.mCallbackHandler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiEntry$Wbjrmqbh-0TpH4DVTSRzGNL2Aks
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$updateConnectionInfo$2$WifiEntry();
                    }
                });
            }
        }
        notifyOnUpdated();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateConnectionInfo$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateConnectionInfo$1$WifiEntry() {
        ConnectCallback connectCallback = this.mConnectCallback;
        if (connectCallback != null) {
            connectCallback.onConnectResult(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateConnectionInfo$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateConnectionInfo$2$WifiEntry() {
        DisconnectCallback disconnectCallback = this.mDisconnectCallback;
        if (disconnectCallback != null) {
            disconnectCallback.onDisconnectResult(0);
        }
    }

    void updateLinkProperties(LinkProperties linkProperties) {
        if (linkProperties == null || getConnectedState() != 2) {
            this.mConnectedInfo = null;
            notifyOnUpdated();
            return;
        }
        if (this.mConnectedInfo == null) {
            this.mConnectedInfo = new ConnectedInfo();
        }
        ArrayList arrayList = new ArrayList();
        for (LinkAddress linkAddress : linkProperties.getLinkAddresses()) {
            if (linkAddress.getAddress() instanceof Inet4Address) {
                this.mConnectedInfo.ipAddress = linkAddress.getAddress().getHostAddress();
                try {
                    this.mConnectedInfo.subnetMask = NetworkUtils.getNetworkPart(InetAddress.getByAddress(new byte[]{-1, -1, -1, -1}), linkAddress.getPrefixLength()).getHostAddress();
                } catch (UnknownHostException unused) {
                }
            } else if (linkAddress.getAddress() instanceof Inet6Address) {
                arrayList.add(linkAddress.getAddress().getHostAddress());
            }
        }
        this.mConnectedInfo.ipv6Addresses = arrayList;
        for (RouteInfo routeInfo : linkProperties.getRoutes()) {
            if (routeInfo.isIPv4Default() && routeInfo.hasGateway()) {
                this.mConnectedInfo.gateway = routeInfo.getGateway().getHostAddress();
                break;
            }
        }
        this.mConnectedInfo.dnsServers = (List) linkProperties.getDnsServers().stream().map(new Function() { // from class: com.android.wifitrackerlib.-$$Lambda$XZAGhHrbkIDyusER4MAM6luKcT0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((InetAddress) obj).getHostAddress();
            }
        }).collect(Collectors.toList());
        notifyOnUpdated();
    }

    String getWifiInfoDescription() {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (getConnectedState() == 2 && this.mWifiInfo != null) {
            stringJoiner.add("f = " + this.mWifiInfo.getFrequency());
            String bssid = this.mWifiInfo.getBSSID();
            if (bssid != null) {
                stringJoiner.add(bssid);
            }
            stringJoiner.add("standard = " + this.mWifiInfo.getWifiStandard());
            stringJoiner.add("rssi = " + this.mWifiInfo.getRssi());
            stringJoiner.add("score = " + this.mWifiInfo.getScore());
            stringJoiner.add(String.format(" tx=%.1f,", Double.valueOf(this.mWifiInfo.getSuccessfulTxPacketsPerSecond())));
            stringJoiner.add(String.format("%.1f,", Double.valueOf(this.mWifiInfo.getRetriedTxPacketsPerSecond())));
            stringJoiner.add(String.format("%.1f ", Double.valueOf(this.mWifiInfo.getLostTxPacketsPerSecond())));
            stringJoiner.add(String.format("rx=%.1f", Double.valueOf(this.mWifiInfo.getSuccessfulRxPacketsPerSecond())));
        }
        return stringJoiner.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(WifiEntry wifiEntry) {
        if (getLevel() != -1 && wifiEntry.getLevel() == -1) {
            return -1;
        }
        if (getLevel() == -1 && wifiEntry.getLevel() != -1) {
            return 1;
        }
        if (isSubscription() && !wifiEntry.isSubscription()) {
            return -1;
        }
        if (!isSubscription() && wifiEntry.isSubscription()) {
            return 1;
        }
        if (isSaved() && !wifiEntry.isSaved()) {
            return -1;
        }
        if (!isSaved() && wifiEntry.isSaved()) {
            return 1;
        }
        if (isSuggestion() && !wifiEntry.isSuggestion()) {
            return -1;
        }
        if (!isSuggestion() && wifiEntry.isSuggestion()) {
            return 1;
        }
        if (getLevel() > wifiEntry.getLevel()) {
            return -1;
        }
        if (getLevel() < wifiEntry.getLevel()) {
            return 1;
        }
        return getTitle().compareTo(wifiEntry.getTitle());
    }

    public boolean equals(Object obj) {
        if (obj instanceof WifiEntry) {
            return getKey().equals(((WifiEntry) obj).getKey());
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getKey());
        sb.append(",title:");
        sb.append(getTitle());
        sb.append(",summary:");
        sb.append(getSummary());
        sb.append(",isSaved:");
        sb.append(isSaved());
        sb.append(",isSubscription:");
        sb.append(isSubscription());
        sb.append(",isSuggestion:");
        sb.append(isSuggestion());
        sb.append(",level:");
        sb.append(getLevel());
        sb.append(",security:");
        sb.append(getSecurity());
        sb.append(",connected:");
        sb.append(getConnectedState() == 2 ? "true" : "false");
        sb.append(",connectedInfo:");
        sb.append(getConnectedInfo());
        return sb.toString();
    }
}
