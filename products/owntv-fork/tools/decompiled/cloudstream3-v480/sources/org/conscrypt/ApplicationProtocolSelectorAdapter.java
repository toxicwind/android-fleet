package org.conscrypt;

import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes8.dex */
final class ApplicationProtocolSelectorAdapter {
    private static final int NO_PROTOCOL_SELECTED = -1;
    private final SSLEngine engine;
    private final ApplicationProtocolSelector selector;
    private final SSLSocket socket;

    ApplicationProtocolSelectorAdapter(SSLEngine sSLEngine, ApplicationProtocolSelector applicationProtocolSelector) {
        this.engine = (SSLEngine) Preconditions.checkNotNull(sSLEngine, "engine");
        this.socket = null;
        this.selector = (ApplicationProtocolSelector) Preconditions.checkNotNull(applicationProtocolSelector, "selector");
    }

    ApplicationProtocolSelectorAdapter(SSLSocket sSLSocket, ApplicationProtocolSelector applicationProtocolSelector) {
        this.engine = null;
        this.socket = (SSLSocket) Preconditions.checkNotNull(sSLSocket, "socket");
        this.selector = (ApplicationProtocolSelector) Preconditions.checkNotNull(applicationProtocolSelector, "selector");
    }

    int selectApplicationProtocol(byte[] bArr) {
        String strSelectApplicationProtocol;
        if (bArr != null && bArr.length != 0) {
            List<String> listAsList = Arrays.asList(SSLUtils.decodeProtocols(bArr));
            SSLEngine sSLEngine = this.engine;
            ApplicationProtocolSelector applicationProtocolSelector = this.selector;
            if (sSLEngine != null) {
                strSelectApplicationProtocol = applicationProtocolSelector.selectApplicationProtocol(sSLEngine, listAsList);
            } else {
                strSelectApplicationProtocol = applicationProtocolSelector.selectApplicationProtocol(this.socket, listAsList);
            }
            if (strSelectApplicationProtocol != null && !strSelectApplicationProtocol.isEmpty()) {
                int length = 0;
                for (String str : listAsList) {
                    if (strSelectApplicationProtocol.equals(str)) {
                        return length;
                    }
                    length += str.length() + 1;
                }
            }
        }
        return -1;
    }
}
