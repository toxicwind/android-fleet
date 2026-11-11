package io.ktor.util.network;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NetworkAddressJvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a!\u0010\u0006\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007\"\u0019\u0010\u0001\u001a\u00020\u0000*\u00060\u0004j\u0002`\u00058F¢\u0006\u0006\u001a\u0004\b\b\u0010\t\"\u0019\u0010\u000b\u001a\u00020\u0000*\u00060\u0004j\u0002`\u00058F¢\u0006\u0006\u001a\u0004\b\n\u0010\t\"\u0019\u0010\u0003\u001a\u00020\u0002*\u00060\u0004j\u0002`\u00058F¢\u0006\u0006\u001a\u0004\b\f\u0010\r*\n\u0010\u0006\"\u00020\u00042\u00020\u0004*\n\u0010\u000f\"\u00020\u000e2\u00020\u000e¨\u0006\u0010"}, d2 = {"", "hostname", "", "port", "Ljava/net/SocketAddress;", "Lio/ktor/util/network/NetworkAddress;", "NetworkAddress", "(Ljava/lang/String;I)Ljava/net/SocketAddress;", "getHostname", "(Ljava/net/SocketAddress;)Ljava/lang/String;", "getAddress", "address", "getPort", "(Ljava/net/SocketAddress;)I", "Ljava/nio/channels/UnresolvedAddressException;", "UnresolvedAddressException", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class NetworkAddressJvmKt {
    public static final String getHostname(SocketAddress socketAddress) {
        InetAddress address;
        String hostName;
        Intrinsics.checkNotNullParameter(socketAddress, "<this>");
        boolean z = socketAddress instanceof InetSocketAddress;
        String hostName2 = null;
        InetSocketAddress inetSocketAddress = z ? (InetSocketAddress) socketAddress : null;
        if (inetSocketAddress != null && (hostName = inetSocketAddress.getHostName()) != null) {
            return hostName;
        }
        InetSocketAddress inetSocketAddress2 = z ? (InetSocketAddress) socketAddress : null;
        if (inetSocketAddress2 != null && (address = inetSocketAddress2.getAddress()) != null) {
            hostName2 = address.getHostName();
        }
        return hostName2 == null ? "" : hostName2;
    }

    public static final String getAddress(SocketAddress socketAddress) {
        String hostString;
        Intrinsics.checkNotNullParameter(socketAddress, "<this>");
        InetSocketAddress inetSocketAddress = socketAddress instanceof InetSocketAddress ? (InetSocketAddress) socketAddress : null;
        return (inetSocketAddress == null || (hostString = inetSocketAddress.getHostString()) == null) ? "" : hostString;
    }

    public static final int getPort(SocketAddress socketAddress) {
        Intrinsics.checkNotNullParameter(socketAddress, "<this>");
        InetSocketAddress inetSocketAddress = socketAddress instanceof InetSocketAddress ? (InetSocketAddress) socketAddress : null;
        if (inetSocketAddress != null) {
            return inetSocketAddress.getPort();
        }
        return 0;
    }

    public static final SocketAddress NetworkAddress(String hostname, int i) {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        return new InetSocketAddress(hostname, i);
    }
}
