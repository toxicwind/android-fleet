package org.schabi.newpipe.extractor.services.youtube;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes8.dex */
public final class InnertubeClientRequestInfo {

    @Nonnull
    public ClientInfo clientInfo;

    @Nonnull
    public DeviceInfo deviceInfo;

    public static final class ClientInfo {

        @Nonnull
        public String clientId;

        @Nonnull
        public String clientName;

        @Nullable
        public String clientScreen;

        @Nonnull
        public String clientVersion;

        @Nullable
        public String visitorData;

        private ClientInfo(@Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nullable String str4, @Nullable String str5) {
            this.clientName = str;
            this.clientVersion = str2;
            this.clientId = str3;
            this.clientScreen = str4;
            this.visitorData = str5;
        }
    }

    public static final class DeviceInfo {
        public int androidSdkVersion;

        @Nullable
        public String deviceMake;

        @Nullable
        public String deviceModel;

        @Nullable
        public String osName;

        @Nullable
        public String osVersion;

        @Nullable
        public String platform;

        private DeviceInfo(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, int i) {
            this.platform = str;
            this.deviceMake = str2;
            this.deviceModel = str3;
            this.osName = str4;
            this.osVersion = str5;
            this.androidSdkVersion = i;
        }
    }

    private InnertubeClientRequestInfo(@Nonnull ClientInfo clientInfo, @Nonnull DeviceInfo deviceInfo) {
        this.clientInfo = clientInfo;
        this.deviceInfo = deviceInfo;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nonnull
    public static InnertubeClientRequestInfo ofWebClient() {
        String str = null;
        return new InnertubeClientRequestInfo(new ClientInfo("WEB", "2.20260120.01.00", "1", "WATCH", str), new DeviceInfo("DESKTOP", null, null, str, 0 == true ? 1 : 0, -1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nonnull
    public static InnertubeClientRequestInfo ofWebEmbeddedPlayerClient() {
        String str = null;
        return new InnertubeClientRequestInfo(new ClientInfo("WEB_EMBEDDED_PLAYER", "1.20260122.01.00", "56", "EMBED", str), new DeviceInfo("DESKTOP", null, null, str, 0 == true ? 1 : 0, -1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nonnull
    public static InnertubeClientRequestInfo ofWebMusicAnalyticsChartsClient() {
        String str = null;
        String str2 = null;
        return new InnertubeClientRequestInfo(new ClientInfo("WEB_MUSIC_ANALYTICS", "2.0", "31", str2, str), new DeviceInfo(null, null, str2, str, 0 == true ? 1 : 0, -1));
    }

    @Nonnull
    public static InnertubeClientRequestInfo ofAndroidClient() {
        return new InnertubeClientRequestInfo(new ClientInfo("ANDROID", "21.03.36", "3", "WATCH", null), new DeviceInfo("MOBILE", null, null, "Android", "16", 36));
    }

    @Nonnull
    public static InnertubeClientRequestInfo ofIosClient() {
        return new InnertubeClientRequestInfo(new ClientInfo("IOS", "21.03.2", "5", "WATCH", null), new DeviceInfo("MOBILE", "Apple", "iPhone16,2", "iOS", "18.7.2.22H124", -1));
    }

    @Nonnull
    public static InnertubeClientRequestInfo ofVisionOsClient() {
        return new InnertubeClientRequestInfo(new ClientInfo("VISIONOS", "1.02", "101", "WATCH", null), new DeviceInfo("MOBILE", "Apple", "RealityDevice14,1", "visionOS", "25.6.0.23O471", -1));
    }
}
