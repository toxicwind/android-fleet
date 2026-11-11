package org.conscrypt;

import io.ktor.util.NonceKt$$ExternalSyntheticApiModelOutline0;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SNIServerName;

/* JADX INFO: loaded from: classes8.dex */
class Java8ExtendedSSLSession extends Java7ExtendedSSLSession {
    public Java8ExtendedSSLSession(ExternalSession externalSession) {
        super(externalSession);
    }

    @Override // javax.net.ssl.ExtendedSSLSession
    public final List<SNIServerName> getRequestedServerNames() {
        String requestedServerName = this.delegate.getRequestedServerName();
        if (requestedServerName == null) {
            return Collections.EMPTY_LIST;
        }
        return Collections.singletonList(NonceKt$$ExternalSyntheticApiModelOutline0.m8846m(requestedServerName));
    }
}
