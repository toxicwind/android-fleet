package okhttp3;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public enum Protocol {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2"),
    H2_PRIOR_KNOWLEDGE("h2_prior_knowledge"),
    QUIC("quic");

    private final String protocol;

    Protocol(String str) {
        this.protocol = str;
    }

    public static Protocol a(String str) throws IOException {
        Protocol protocol = QUIC;
        Protocol protocol2 = SPDY_3;
        Protocol protocol3 = HTTP_2;
        Protocol protocol4 = H2_PRIOR_KNOWLEDGE;
        Protocol protocol5 = HTTP_1_1;
        Protocol protocol6 = HTTP_1_0;
        if (str.equals(protocol6.protocol)) {
            return protocol6;
        }
        if (str.equals(protocol5.protocol)) {
            return protocol5;
        }
        if (str.equals(protocol4.protocol)) {
            return protocol4;
        }
        if (str.equals(protocol3.protocol)) {
            return protocol3;
        }
        if (str.equals(protocol2.protocol)) {
            return protocol2;
        }
        if (str.equals(protocol.protocol)) {
            return protocol;
        }
        throw new IOException("Unexpected protocol: " + str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.protocol;
    }
}
