package com.lagradost.cloudstream3.actions.temp.fcast;

import android.net.nsd.NsdServiceInfo;
import android.os.Build;
import android.os.ext.SdkExtensions;
import java.net.InetAddress;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: FcastManager.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\t¨\u0006\u000e"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/fcast/PublicDeviceInfo;", "", "serviceInfo", "Landroid/net/nsd/NsdServiceInfo;", "<init>", "(Landroid/net/nsd/NsdServiceInfo;)V", "rawName", "", "getRawName", "()Ljava/lang/String;", "host", "getHost", "name", "getName", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PublicDeviceInfo {
    private final String host;
    private final String name;
    private final String rawName;

    public PublicDeviceInfo(NsdServiceInfo serviceInfo) {
        String hostAddress;
        Intrinsics.checkNotNullParameter(serviceInfo, "serviceInfo");
        String serviceName = serviceInfo.getServiceName();
        Intrinsics.checkNotNullExpressionValue(serviceName, "getServiceName(...)");
        this.rawName = serviceName;
        String str = null;
        if (Build.VERSION.SDK_INT >= 30 && SdkExtensions.getExtensionVersion(33) >= 7) {
            List hostAddresses = serviceInfo.getHostAddresses();
            Intrinsics.checkNotNullExpressionValue(hostAddresses, "getHostAddresses(...)");
            InetAddress inetAddress = (InetAddress) CollectionsKt.firstOrNull(hostAddresses);
            hostAddress = inetAddress != null ? inetAddress.getHostAddress() : null;
        } else {
            hostAddress = serviceInfo.getHost().getHostAddress();
        }
        this.host = hostAddress;
        StringBuilder sb = new StringBuilder();
        sb.append(StringsKt.replace$default(serviceName, "-", Stream.ID_UNKNOWN, false, 4, (Object) null));
        if (hostAddress != null) {
            str = Stream.ID_UNKNOWN + hostAddress;
        }
        sb.append(str);
        this.name = sb.toString();
    }

    public final String getRawName() {
        return this.rawName;
    }

    public final String getHost() {
        return this.host;
    }

    public final String getName() {
        return this.name;
    }
}
