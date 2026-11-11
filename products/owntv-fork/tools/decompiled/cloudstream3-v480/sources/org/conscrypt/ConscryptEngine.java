package org.conscrypt;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECKey;
import java.security.spec.ECParameterSpec;
import java.util.Arrays;
import javax.crypto.SecretKey;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: loaded from: classes8.dex */
final class ConscryptEngine extends AbstractConscryptEngine implements NativeCrypto.SSLHandshakeCallbacks, SSLParametersImpl.AliasChooser, SSLParametersImpl.PSKCallbacks {
    private ActiveSession activeSession;
    private BufferAllocator bufferAllocator;
    private OpenSSLKey channelIdPrivateKey;
    private SessionSnapshot closedSession;
    private final SSLSession externalSession;
    private boolean handshakeFinished;
    private HandshakeListener handshakeListener;
    private ByteBuffer lazyDirectBuffer;
    private int maxSealOverhead;
    private final NativeSsl.BioWrapper networkBio;
    private String peerHostname;
    private final PeerInfoProvider peerInfoProvider;
    private final ByteBuffer[] singleDstBuffer;
    private final ByteBuffer[] singleSrcBuffer;
    private final NativeSsl ssl;
    private final SSLParametersImpl sslParameters;
    private int state;
    private static final SSLEngineResult NEED_UNWRAP_OK = new SSLEngineResult(SSLEngineResult.Status.OK, SSLEngineResult.HandshakeStatus.NEED_UNWRAP, 0, 0);
    private static final SSLEngineResult NEED_UNWRAP_CLOSED = new SSLEngineResult(SSLEngineResult.Status.CLOSED, SSLEngineResult.HandshakeStatus.NEED_UNWRAP, 0, 0);
    private static final SSLEngineResult NEED_WRAP_OK = new SSLEngineResult(SSLEngineResult.Status.OK, SSLEngineResult.HandshakeStatus.NEED_WRAP, 0, 0);
    private static final SSLEngineResult NEED_WRAP_CLOSED = new SSLEngineResult(SSLEngineResult.Status.CLOSED, SSLEngineResult.HandshakeStatus.NEED_WRAP, 0, 0);
    private static final SSLEngineResult CLOSED_NOT_HANDSHAKING = new SSLEngineResult(SSLEngineResult.Status.CLOSED, SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING, 0, 0);
    private static BufferAllocator defaultBufferAllocator = null;

    @Override // javax.net.ssl.SSLEngine
    public Runnable getDelegatedTask() {
        return null;
    }

    @Override // org.conscrypt.NativeCrypto.SSLHandshakeCallbacks
    public long serverSessionRequested(byte[] bArr) {
        return 0L;
    }

    ConscryptEngine(SSLParametersImpl sSLParametersImpl) {
        this.bufferAllocator = defaultBufferAllocator;
        this.state = 0;
        this.externalSession = Platform.wrapSSLSession(new ExternalSession(new ExternalSession.Provider() { // from class: org.conscrypt.ConscryptEngine.1
            @Override // org.conscrypt.ExternalSession.Provider
            public ConscryptSession provideSession() {
                return ConscryptEngine.this.provideSession();
            }
        }));
        this.singleSrcBuffer = new ByteBuffer[1];
        this.singleDstBuffer = new ByteBuffer[1];
        this.sslParameters = sSLParametersImpl;
        this.peerInfoProvider = PeerInfoProvider.nullProvider();
        NativeSsl nativeSslNewSsl = newSsl(sSLParametersImpl, this, this);
        this.ssl = nativeSslNewSsl;
        this.networkBio = nativeSslNewSsl.newBio();
    }

    ConscryptEngine(String str, int i, SSLParametersImpl sSLParametersImpl) {
        this.bufferAllocator = defaultBufferAllocator;
        this.state = 0;
        this.externalSession = Platform.wrapSSLSession(new ExternalSession(new ExternalSession.Provider() { // from class: org.conscrypt.ConscryptEngine.1
            @Override // org.conscrypt.ExternalSession.Provider
            public ConscryptSession provideSession() {
                return ConscryptEngine.this.provideSession();
            }
        }));
        this.singleSrcBuffer = new ByteBuffer[1];
        this.singleDstBuffer = new ByteBuffer[1];
        this.sslParameters = sSLParametersImpl;
        this.peerInfoProvider = PeerInfoProvider.forHostAndPort(str, i);
        NativeSsl nativeSslNewSsl = newSsl(sSLParametersImpl, this, this);
        this.ssl = nativeSslNewSsl;
        this.networkBio = nativeSslNewSsl.newBio();
    }

    ConscryptEngine(SSLParametersImpl sSLParametersImpl, PeerInfoProvider peerInfoProvider, SSLParametersImpl.AliasChooser aliasChooser) {
        this.bufferAllocator = defaultBufferAllocator;
        this.state = 0;
        this.externalSession = Platform.wrapSSLSession(new ExternalSession(new ExternalSession.Provider() { // from class: org.conscrypt.ConscryptEngine.1
            @Override // org.conscrypt.ExternalSession.Provider
            public ConscryptSession provideSession() {
                return ConscryptEngine.this.provideSession();
            }
        }));
        this.singleSrcBuffer = new ByteBuffer[1];
        this.singleDstBuffer = new ByteBuffer[1];
        this.sslParameters = sSLParametersImpl;
        this.peerInfoProvider = (PeerInfoProvider) Preconditions.checkNotNull(peerInfoProvider, "peerInfoProvider");
        NativeSsl nativeSslNewSsl = newSsl(sSLParametersImpl, this, aliasChooser);
        this.ssl = nativeSslNewSsl;
        this.networkBio = nativeSslNewSsl.newBio();
    }

    private static NativeSsl newSsl(SSLParametersImpl sSLParametersImpl, ConscryptEngine conscryptEngine, SSLParametersImpl.AliasChooser aliasChooser) {
        try {
            return NativeSsl.newInstance(sSLParametersImpl, conscryptEngine, aliasChooser, conscryptEngine);
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }
    }

    static void setDefaultBufferAllocator(BufferAllocator bufferAllocator) {
        defaultBufferAllocator = bufferAllocator;
    }

    static BufferAllocator getDefaultBufferAllocator() {
        return defaultBufferAllocator;
    }

    @Override // org.conscrypt.AbstractConscryptEngine
    void setBufferAllocator(BufferAllocator bufferAllocator) {
        synchronized (this.ssl) {
            if (isHandshakeStarted()) {
                throw new IllegalStateException("Could not set buffer allocator after the initial handshake has begun.");
            }
            this.bufferAllocator = bufferAllocator;
        }
    }

    @Override // org.conscrypt.AbstractConscryptEngine
    int maxSealOverhead() {
        return this.maxSealOverhead;
    }

    @Override // org.conscrypt.AbstractConscryptEngine
    void setChannelIdEnabled(boolean z) {
        synchronized (this.ssl) {
            if (getUseClientMode()) {
                throw new IllegalStateException("Not allowed in client mode");
            }
            if (isHandshakeStarted()) {
                throw new IllegalStateException("Could not enable/disable Channel ID after the initial handshake has begun.");
            }
            this.sslParameters.channelIdEnabled = z;
        }
    }

    @Override // org.conscrypt.AbstractConscryptEngine
    byte[] getChannelId() throws SSLException {
        byte[] tlsChannelId;
        synchronized (this.ssl) {
            if (getUseClientMode()) {
                throw new IllegalStateException("Not allowed in client mode");
            }
            if (isHandshakeStarted()) {
                throw new IllegalStateException("Channel ID is only available after handshake completes");
            }
            tlsChannelId = this.ssl.getTlsChannelId();
        }
        return tlsChannelId;
    }

    @Override // org.conscrypt.AbstractConscryptEngine
    void setChannelIdPrivateKey(PrivateKey privateKey) {
        if (!getUseClientMode()) {
            throw new IllegalStateException("Not allowed in server mode");
        }
        synchronized (this.ssl) {
            if (isHandshakeStarted()) {
                throw new IllegalStateException("Could not change Channel ID private key after the initial handshake has begun.");
            }
            SSLParametersImpl sSLParametersImpl = this.sslParameters;
            if (privateKey == null) {
                sSLParametersImpl.channelIdEnabled = false;
                this.channelIdPrivateKey = null;
                return;
            }
            sSLParametersImpl.channelIdEnabled = true;
            try {
                ECParameterSpec params = privateKey instanceof ECKey ? ((ECKey) privateKey).getParams() : null;
                if (params == null) {
                    params = OpenSSLECGroupContext.getCurveByName("prime256v1").getECParameterSpec();
                }
                this.channelIdPrivateKey = OpenSSLKey.fromECPrivateKeyForTLSStackOnly(privateKey, params);
            } catch (InvalidKeyException unused) {
            }
        }
    }

    @Override // org.conscrypt.AbstractConscryptEngine
    void setHandshakeListener(HandshakeListener handshakeListener) {
        synchronized (this.ssl) {
            if (isHandshakeStarted()) {
                throw new IllegalStateException("Handshake listener must be set before starting the handshake.");
            }
            this.handshakeListener = handshakeListener;
        }
    }

    private boolean isHandshakeStarted() {
        int i = this.state;
        return (i == 0 || i == 1) ? false : true;
    }

    @Override // org.conscrypt.AbstractConscryptEngine
    void setHostname(String str) {
        this.sslParameters.setUseSni(str != null);
        this.peerHostname = str;
    }

    @Override // org.conscrypt.AbstractConscryptEngine
    String getHostname() {
        String str = this.peerHostname;
        return str != null ? str : this.peerInfoProvider.getHostname();
    }

    @Override // org.conscrypt.AbstractConscryptEngine, javax.net.ssl.SSLEngine
    public String getPeerHost() {
        String str = this.peerHostname;
        return str != null ? str : this.peerInfoProvider.getHostnameOrIP();
    }

    @Override // org.conscrypt.AbstractConscryptEngine, javax.net.ssl.SSLEngine
    public int getPeerPort() {
        return this.peerInfoProvider.getPort();
    }

    @Override // javax.net.ssl.SSLEngine
    public void beginHandshake() throws SSLException {
        synchronized (this.ssl) {
            beginHandshakeInternal();
        }
    }

    private void beginHandshakeInternal() throws SSLException {
        NativeSslSession cachedSession;
        int i = this.state;
        if (i == 0) {
            throw new IllegalStateException("Client/server mode must be set before handshake");
        }
        if (i != 1) {
            if (i == 6 || i == 7 || i == 8) {
                throw new SSLHandshakeException("Engine has already been closed");
            }
            return;
        }
        transitionTo(2);
        try {
            try {
                this.ssl.initialize(getHostname(), this.channelIdPrivateKey);
                if (getUseClientMode() && (cachedSession = clientSessionContext().getCachedSession(getHostname(), getPeerPort(), this.sslParameters)) != null) {
                    cachedSession.offerToResume(this.ssl);
                }
                this.maxSealOverhead = this.ssl.getMaxSealOverhead();
                handshake();
            } catch (IOException e) {
                if (e.getMessage().contains("unexpected CCS")) {
                    Platform.logEvent(String.format("ssl_unexpected_ccs: host=%s", getPeerHost()));
                }
                closeAll();
                throw SSLUtils.toSSLHandshakeException(e);
            }
        } catch (Throwable th) {
            closeAndFreeResources();
            throw th;
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public void closeInbound() {
        synchronized (this.ssl) {
            int i = this.state;
            if (i != 8 && i != 6) {
                if (isHandshakeStarted()) {
                    if (this.state == 7) {
                        transitionTo(8);
                    } else {
                        transitionTo(6);
                    }
                    freeIfDone();
                } else {
                    closeAndFreeResources();
                }
            }
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public void closeOutbound() {
        synchronized (this.ssl) {
            int i = this.state;
            if (i != 8 && i != 7) {
                if (isHandshakeStarted()) {
                    if (this.state == 6) {
                        transitionTo(8);
                    } else {
                        transitionTo(7);
                    }
                    sendSSLShutdown();
                    freeIfDone();
                } else {
                    closeAndFreeResources();
                }
            }
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public String[] getEnabledCipherSuites() {
        return this.sslParameters.getEnabledCipherSuites();
    }

    @Override // javax.net.ssl.SSLEngine
    public String[] getEnabledProtocols() {
        return this.sslParameters.getEnabledProtocols();
    }

    @Override // javax.net.ssl.SSLEngine
    public boolean getEnableSessionCreation() {
        return this.sslParameters.getEnableSessionCreation();
    }

    @Override // javax.net.ssl.SSLEngine
    public SSLParameters getSSLParameters() {
        SSLParameters sSLParameters = super.getSSLParameters();
        Platform.getSSLParameters(sSLParameters, this.sslParameters, this);
        return sSLParameters;
    }

    @Override // javax.net.ssl.SSLEngine
    public void setSSLParameters(SSLParameters sSLParameters) {
        super.setSSLParameters(sSLParameters);
        Platform.setSSLParameters(sSLParameters, this.sslParameters, this);
    }

    @Override // javax.net.ssl.SSLEngine
    public SSLEngineResult.HandshakeStatus getHandshakeStatus() {
        SSLEngineResult.HandshakeStatus handshakeStatusInternal;
        synchronized (this.ssl) {
            handshakeStatusInternal = getHandshakeStatusInternal();
        }
        return handshakeStatusInternal;
    }

    private SSLEngineResult.HandshakeStatus getHandshakeStatusInternal() {
        if (this.handshakeFinished) {
            return SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
        }
        switch (this.state) {
            case 0:
            case 1:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
            case 2:
                return pendingStatus(pendingOutboundEncryptedBytes());
            case 3:
                return SSLEngineResult.HandshakeStatus.NEED_WRAP;
            default:
                throw new IllegalStateException("Unexpected engine state: " + this.state);
        }
    }

    int pendingOutboundEncryptedBytes() {
        return this.networkBio.getPendingWrittenBytes();
    }

    private int pendingInboundCleartextBytes() {
        return this.ssl.getPendingReadableBytes();
    }

    private static SSLEngineResult.HandshakeStatus pendingStatus(int i) {
        return i > 0 ? SSLEngineResult.HandshakeStatus.NEED_WRAP : SSLEngineResult.HandshakeStatus.NEED_UNWRAP;
    }

    @Override // javax.net.ssl.SSLEngine
    public boolean getNeedClientAuth() {
        return this.sslParameters.getNeedClientAuth();
    }

    @Override // org.conscrypt.AbstractConscryptEngine
    SSLSession handshakeSession() {
        synchronized (this.ssl) {
            if (this.state != 2) {
                return null;
            }
            return Platform.wrapSSLSession(new ExternalSession(new ExternalSession.Provider() { // from class: org.conscrypt.ConscryptEngine.2
                @Override // org.conscrypt.ExternalSession.Provider
                public ConscryptSession provideSession() {
                    return ConscryptEngine.this.provideHandshakeSession();
                }
            }));
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public SSLSession getSession() {
        return this.externalSession;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ConscryptSession provideSession() {
        synchronized (this.ssl) {
            int i = this.state;
            if (i == 8) {
                ConscryptSession nullSession = this.closedSession;
                if (nullSession == null) {
                    nullSession = SSLNullSession.getNullSession();
                }
                return nullSession;
            }
            if (i < 3) {
                return SSLNullSession.getNullSession();
            }
            return this.activeSession;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ConscryptSession provideHandshakeSession() {
        ConscryptSession nullSession;
        synchronized (this.ssl) {
            nullSession = this.state == 2 ? this.activeSession : SSLNullSession.getNullSession();
        }
        return nullSession;
    }

    private ConscryptSession provideAfterHandshakeSession() {
        if (this.state < 2) {
            return SSLNullSession.getNullSession();
        }
        return provideSession();
    }

    @Override // javax.net.ssl.SSLEngine
    public String[] getSupportedCipherSuites() {
        return NativeCrypto.getSupportedCipherSuites();
    }

    @Override // javax.net.ssl.SSLEngine
    public String[] getSupportedProtocols() {
        return NativeCrypto.getSupportedProtocols();
    }

    @Override // javax.net.ssl.SSLEngine
    public boolean getUseClientMode() {
        return this.sslParameters.getUseClientMode();
    }

    @Override // javax.net.ssl.SSLEngine
    public boolean getWantClientAuth() {
        return this.sslParameters.getWantClientAuth();
    }

    @Override // javax.net.ssl.SSLEngine
    public boolean isInboundDone() {
        boolean z;
        synchronized (this.ssl) {
            int i = this.state;
            z = (i == 8 || i == 6 || this.ssl.wasShutdownReceived()) && pendingInboundCleartextBytes() == 0;
        }
        return z;
    }

    @Override // javax.net.ssl.SSLEngine
    public boolean isOutboundDone() {
        boolean z;
        synchronized (this.ssl) {
            int i = this.state;
            z = (i == 8 || i == 7 || this.ssl.wasShutdownSent()) && pendingOutboundEncryptedBytes() == 0;
        }
        return z;
    }

    @Override // javax.net.ssl.SSLEngine
    public void setEnabledCipherSuites(String[] strArr) {
        this.sslParameters.setEnabledCipherSuites(strArr);
    }

    @Override // javax.net.ssl.SSLEngine
    public void setEnabledProtocols(String[] strArr) {
        this.sslParameters.setEnabledProtocols(strArr);
    }

    @Override // javax.net.ssl.SSLEngine
    public void setEnableSessionCreation(boolean z) {
        this.sslParameters.setEnableSessionCreation(z);
    }

    @Override // javax.net.ssl.SSLEngine
    public void setNeedClientAuth(boolean z) {
        this.sslParameters.setNeedClientAuth(z);
    }

    @Override // javax.net.ssl.SSLEngine
    public void setUseClientMode(boolean z) {
        synchronized (this.ssl) {
            if (isHandshakeStarted()) {
                throw new IllegalArgumentException("Can not change mode after handshake: state == " + this.state);
            }
            transitionTo(1);
            this.sslParameters.setUseClientMode(z);
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public void setWantClientAuth(boolean z) {
        this.sslParameters.setWantClientAuth(z);
    }

    @Override // org.conscrypt.AbstractConscryptEngine, javax.net.ssl.SSLEngine
    public SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws SSLException {
        SSLEngineResult sSLEngineResultUnwrap;
        synchronized (this.ssl) {
            try {
                sSLEngineResultUnwrap = unwrap(singleSrcBuffer(byteBuffer), singleDstBuffer(byteBuffer2));
                resetSingleSrcBuffer();
                resetSingleDstBuffer();
            } catch (Throwable th) {
                resetSingleSrcBuffer();
                resetSingleDstBuffer();
                throw th;
            }
        }
        return sSLEngineResultUnwrap;
    }

    @Override // org.conscrypt.AbstractConscryptEngine, javax.net.ssl.SSLEngine
    public SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr) throws SSLException {
        SSLEngineResult sSLEngineResultUnwrap;
        synchronized (this.ssl) {
            try {
                sSLEngineResultUnwrap = unwrap(singleSrcBuffer(byteBuffer), byteBufferArr);
                resetSingleSrcBuffer();
            } catch (Throwable th) {
                resetSingleSrcBuffer();
                throw th;
            }
        }
        return sSLEngineResultUnwrap;
    }

    @Override // org.conscrypt.AbstractConscryptEngine, javax.net.ssl.SSLEngine
    public SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr, int i, int i2) throws SSLException {
        ConscryptEngine conscryptEngine;
        SSLEngineResult sSLEngineResultUnwrap;
        synchronized (this.ssl) {
            try {
                try {
                    conscryptEngine = this;
                    try {
                        sSLEngineResultUnwrap = conscryptEngine.unwrap(singleSrcBuffer(byteBuffer), 0, 1, byteBufferArr, i, i2);
                        conscryptEngine.resetSingleSrcBuffer();
                    } catch (Throwable th) {
                        th = th;
                        Throwable th2 = th;
                        conscryptEngine.resetSingleSrcBuffer();
                        throw th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    conscryptEngine = this;
                }
            } catch (Throwable th4) {
                throw th4;
            }
        }
        return sSLEngineResultUnwrap;
    }

    @Override // org.conscrypt.AbstractConscryptEngine
    SSLEngineResult unwrap(ByteBuffer[] byteBufferArr, ByteBuffer[] byteBufferArr2) throws SSLException {
        Preconditions.checkArgument(byteBufferArr != null, "srcs is null");
        Preconditions.checkArgument(byteBufferArr2 != null, "dsts is null");
        return unwrap(byteBufferArr, 0, byteBufferArr.length, byteBufferArr2, 0, byteBufferArr2.length);
    }

    @Override // org.conscrypt.AbstractConscryptEngine
    SSLEngineResult unwrap(ByteBuffer[] byteBufferArr, int i, int i2, ByteBuffer[] byteBufferArr2, int i3, int i4) throws SSLException {
        int encryptedPacketLength;
        int i5;
        int i6;
        boolean z = true;
        int i7 = 0;
        Preconditions.checkArgument(byteBufferArr != null, "srcs is null");
        Preconditions.checkArgument(byteBufferArr2 != null, "dsts is null");
        int i8 = i2 + i;
        Preconditions.checkPositionIndexes(i, i8, byteBufferArr.length);
        int i9 = i3 + i4;
        Preconditions.checkPositionIndexes(i3, i9, byteBufferArr2.length);
        int iCalcDstsLength = calcDstsLength(byteBufferArr2, i3, i4);
        long jCalcSrcsLength = calcSrcsLength(byteBufferArr, i, i8);
        synchronized (this.ssl) {
            int i10 = this.state;
            if (i10 == 0) {
                throw new IllegalStateException("Client/server mode must be set before calling unwrap");
            }
            if (i10 == 1) {
                beginHandshakeInternal();
            } else if (i10 == 6 || i10 == 8) {
                freeIfDone();
                return new SSLEngineResult(SSLEngineResult.Status.CLOSED, getHandshakeStatusInternal(), 0, 0);
            }
            SSLEngineResult.HandshakeStatus handshakeStatusInternal = SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
            if (!this.handshakeFinished) {
                handshakeStatusInternal = handshake();
                if (handshakeStatusInternal == SSLEngineResult.HandshakeStatus.NEED_WRAP) {
                    return NEED_WRAP_OK;
                }
                if (this.state == 8) {
                    return NEED_WRAP_CLOSED;
                }
            }
            if (pendingInboundCleartextBytes() > 0) {
                z = false;
            }
            if (jCalcSrcsLength <= 0 || !z) {
                if (z) {
                    return new SSLEngineResult(SSLEngineResult.Status.BUFFER_UNDERFLOW, getHandshakeStatus(), 0, 0);
                }
                encryptedPacketLength = 0;
            } else {
                if (jCalcSrcsLength < 5) {
                    return new SSLEngineResult(SSLEngineResult.Status.BUFFER_UNDERFLOW, getHandshakeStatus(), 0, 0);
                }
                encryptedPacketLength = SSLUtils.getEncryptedPacketLength(byteBufferArr, i);
                if (encryptedPacketLength < 0) {
                    throw new SSLException("Unable to parse TLS packet header");
                }
                if (jCalcSrcsLength < encryptedPacketLength) {
                    return new SSLEngineResult(SSLEngineResult.Status.BUFFER_UNDERFLOW, getHandshakeStatus(), 0, 0);
                }
            }
            if (encryptedPacketLength <= 0 || i >= i8) {
                i5 = 0;
                break;
            }
            i5 = 0;
            do {
                ByteBuffer byteBuffer = byteBufferArr[i];
                int iRemaining = byteBuffer.remaining();
                if (iRemaining != 0) {
                    int iWriteEncryptedData = writeEncryptedData(byteBuffer, Math.min(encryptedPacketLength, iRemaining));
                    if (iWriteEncryptedData <= 0) {
                        NativeCrypto.SSL_clear_error();
                        break;
                    }
                    i5 += iWriteEncryptedData;
                    encryptedPacketLength -= iWriteEncryptedData;
                    if (encryptedPacketLength == 0 || iWriteEncryptedData != iRemaining) {
                        break;
                        break;
                    }
                    i++;
                } else {
                    i++;
                }
            } while (i < i8);
            try {
                if (iCalcDstsLength > 0) {
                    i6 = 0;
                    while (i3 < i9) {
                        try {
                            ByteBuffer byteBuffer2 = byteBufferArr2[i3];
                            if (byteBuffer2.hasRemaining()) {
                                int plaintextData = readPlaintextData(byteBuffer2);
                                if (plaintextData <= 0) {
                                    if (plaintextData != -6) {
                                        if (plaintextData != -3 && plaintextData != -2) {
                                            closeAll();
                                            throw newSslExceptionWithMessage("SSL_read");
                                        }
                                        return newResult(i5, i6, handshakeStatusInternal);
                                    }
                                    closeAll();
                                    return new SSLEngineResult(SSLEngineResult.Status.CLOSED, pendingOutboundEncryptedBytes() > 0 ? SSLEngineResult.HandshakeStatus.NEED_WRAP : SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING, i5, i6);
                                }
                                i6 += plaintextData;
                                if (byteBuffer2.hasRemaining()) {
                                    break;
                                }
                            }
                            i3++;
                        } catch (InterruptedIOException unused) {
                            i7 = i6;
                            return newResult(i5, i7, handshakeStatusInternal);
                        }
                    }
                } else {
                    try {
                        this.ssl.forceRead();
                        i6 = 0;
                    } catch (InterruptedIOException unused2) {
                        return newResult(i5, i7, handshakeStatusInternal);
                    }
                }
                if ((this.handshakeFinished ? pendingInboundCleartextBytes() : 0) > 0) {
                    SSLEngineResult.Status status = SSLEngineResult.Status.BUFFER_OVERFLOW;
                    if (handshakeStatusInternal != SSLEngineResult.HandshakeStatus.FINISHED) {
                        handshakeStatusInternal = getHandshakeStatusInternal();
                    }
                    return new SSLEngineResult(status, mayFinishHandshake(handshakeStatusInternal), i5, i6);
                }
                return newResult(i5, i6, handshakeStatusInternal);
            } catch (IOException e) {
                closeAll();
                throw convertException(e);
            }
        }
    }

    private static int calcDstsLength(ByteBuffer[] byteBufferArr, int i, int i2) {
        int iRemaining = 0;
        for (int i3 = 0; i3 < byteBufferArr.length; i3++) {
            ByteBuffer byteBuffer = byteBufferArr[i3];
            Preconditions.checkArgument(byteBuffer != null, "dsts[%d] is null", Integer.valueOf(i3));
            if (byteBuffer.isReadOnly()) {
                throw new ReadOnlyBufferException();
            }
            if (i3 >= i && i3 < i + i2) {
                iRemaining += byteBuffer.remaining();
            }
        }
        return iRemaining;
    }

    private static long calcSrcsLength(ByteBuffer[] byteBufferArr, int i, int i2) {
        long jRemaining = 0;
        while (i < i2) {
            ByteBuffer byteBuffer = byteBufferArr[i];
            if (byteBuffer == null) {
                throw new IllegalArgumentException("srcs[" + i + "] is null");
            }
            jRemaining += (long) byteBuffer.remaining();
            i++;
        }
        return jRemaining;
    }

    private SSLEngineResult.HandshakeStatus handshake() throws SSLException {
        try {
            try {
                int iDoHandshake = this.ssl.doHandshake();
                if (iDoHandshake == 2) {
                    return pendingStatus(pendingOutboundEncryptedBytes());
                }
                if (iDoHandshake == 3) {
                    return SSLEngineResult.HandshakeStatus.NEED_WRAP;
                }
                this.activeSession.onPeerCertificateAvailable(getPeerHost(), getPeerPort());
                finishHandshake();
                return SSLEngineResult.HandshakeStatus.FINISHED;
            } catch (IOException e) {
                closeAll();
                throw e;
            }
        } catch (Exception e2) {
            throw SSLUtils.toSSLHandshakeException(e2);
        }
    }

    private void finishHandshake() throws SSLException {
        this.handshakeFinished = true;
        HandshakeListener handshakeListener = this.handshakeListener;
        if (handshakeListener != null) {
            handshakeListener.onHandshakeFinished();
        }
    }

    private int writePlaintextData(ByteBuffer byteBuffer, int i) throws SSLException {
        int iWritePlaintextDataHeap;
        try {
            int iPosition = byteBuffer.position();
            if (byteBuffer.isDirect()) {
                iWritePlaintextDataHeap = writePlaintextDataDirect(byteBuffer, iPosition, i);
            } else {
                iWritePlaintextDataHeap = writePlaintextDataHeap(byteBuffer, iPosition, i);
            }
            if (iWritePlaintextDataHeap > 0) {
                byteBuffer.position(iPosition + iWritePlaintextDataHeap);
            }
            return iWritePlaintextDataHeap;
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    private int writePlaintextDataDirect(ByteBuffer byteBuffer, int i, int i2) throws IOException {
        return this.ssl.writeDirectByteBuffer(directByteBufferAddress(byteBuffer, i), i2);
    }

    private int writePlaintextDataHeap(ByteBuffer byteBuffer, int i, int i2) throws IOException {
        ByteBuffer orCreateLazyDirectBuffer;
        AllocatedBuffer allocatedBufferAllocateDirectBuffer = null;
        try {
            BufferAllocator bufferAllocator = this.bufferAllocator;
            if (bufferAllocator != null) {
                allocatedBufferAllocateDirectBuffer = bufferAllocator.allocateDirectBuffer(i2);
                orCreateLazyDirectBuffer = allocatedBufferAllocateDirectBuffer.nioBuffer();
            } else {
                orCreateLazyDirectBuffer = getOrCreateLazyDirectBuffer();
            }
            int iLimit = byteBuffer.limit();
            int iMin = Math.min(i2, orCreateLazyDirectBuffer.remaining());
            byteBuffer.limit(i + iMin);
            orCreateLazyDirectBuffer.put(byteBuffer);
            orCreateLazyDirectBuffer.flip();
            byteBuffer.limit(iLimit);
            byteBuffer.position(i);
            return writePlaintextDataDirect(orCreateLazyDirectBuffer, 0, iMin);
        } finally {
            if (allocatedBufferAllocateDirectBuffer != null) {
                allocatedBufferAllocateDirectBuffer.release();
            }
        }
    }

    private int readPlaintextData(ByteBuffer byteBuffer) throws IOException {
        try {
            int iPosition = byteBuffer.position();
            int iMin = Math.min(16709, byteBuffer.limit() - iPosition);
            if (byteBuffer.isDirect()) {
                int plaintextDataDirect = readPlaintextDataDirect(byteBuffer, iPosition, iMin);
                if (plaintextDataDirect > 0) {
                    byteBuffer.position(iPosition + plaintextDataDirect);
                }
                return plaintextDataDirect;
            }
            return readPlaintextDataHeap(byteBuffer, iMin);
        } catch (CertificateException e) {
            throw convertException(e);
        }
    }

    private int readPlaintextDataDirect(ByteBuffer byteBuffer, int i, int i2) throws IOException, CertificateException {
        return this.ssl.readDirectByteBuffer(directByteBufferAddress(byteBuffer, i), i2);
    }

    private int readPlaintextDataHeap(ByteBuffer byteBuffer, int i) throws IOException, CertificateException {
        ByteBuffer orCreateLazyDirectBuffer;
        AllocatedBuffer allocatedBufferAllocateDirectBuffer = null;
        try {
            BufferAllocator bufferAllocator = this.bufferAllocator;
            if (bufferAllocator != null) {
                allocatedBufferAllocateDirectBuffer = bufferAllocator.allocateDirectBuffer(i);
                orCreateLazyDirectBuffer = allocatedBufferAllocateDirectBuffer.nioBuffer();
            } else {
                orCreateLazyDirectBuffer = getOrCreateLazyDirectBuffer();
            }
            int plaintextDataDirect = readPlaintextDataDirect(orCreateLazyDirectBuffer, 0, Math.min(i, orCreateLazyDirectBuffer.remaining()));
            if (plaintextDataDirect > 0) {
                orCreateLazyDirectBuffer.position(plaintextDataDirect);
                orCreateLazyDirectBuffer.flip();
                byteBuffer.put(orCreateLazyDirectBuffer);
            }
            return plaintextDataDirect;
        } finally {
            if (allocatedBufferAllocateDirectBuffer != null) {
                allocatedBufferAllocateDirectBuffer.release();
            }
        }
    }

    private SSLException convertException(Throwable th) {
        if ((th instanceof SSLHandshakeException) || !this.handshakeFinished) {
            return SSLUtils.toSSLHandshakeException(th);
        }
        return SSLUtils.toSSLException(th);
    }

    private int writeEncryptedData(ByteBuffer byteBuffer, int i) throws SSLException {
        int iWriteEncryptedDataHeap;
        try {
            int iPosition = byteBuffer.position();
            if (byteBuffer.isDirect()) {
                iWriteEncryptedDataHeap = writeEncryptedDataDirect(byteBuffer, iPosition, i);
            } else {
                iWriteEncryptedDataHeap = writeEncryptedDataHeap(byteBuffer, iPosition, i);
            }
            if (iWriteEncryptedDataHeap > 0) {
                byteBuffer.position(iPosition + iWriteEncryptedDataHeap);
            }
            return iWriteEncryptedDataHeap;
        } catch (IOException e) {
            closeAll();
            throw new SSLException(e);
        }
    }

    private int writeEncryptedDataDirect(ByteBuffer byteBuffer, int i, int i2) throws IOException {
        return this.networkBio.writeDirectByteBuffer(directByteBufferAddress(byteBuffer, i), i2);
    }

    private int writeEncryptedDataHeap(ByteBuffer byteBuffer, int i, int i2) throws IOException {
        ByteBuffer orCreateLazyDirectBuffer;
        AllocatedBuffer allocatedBufferAllocateDirectBuffer = null;
        try {
            BufferAllocator bufferAllocator = this.bufferAllocator;
            if (bufferAllocator != null) {
                allocatedBufferAllocateDirectBuffer = bufferAllocator.allocateDirectBuffer(i2);
                orCreateLazyDirectBuffer = allocatedBufferAllocateDirectBuffer.nioBuffer();
            } else {
                orCreateLazyDirectBuffer = getOrCreateLazyDirectBuffer();
            }
            int iLimit = byteBuffer.limit();
            int iMin = Math.min(Math.min(iLimit - i, i2), orCreateLazyDirectBuffer.remaining());
            byteBuffer.limit(i + iMin);
            orCreateLazyDirectBuffer.put(byteBuffer);
            byteBuffer.limit(iLimit);
            byteBuffer.position(i);
            int iWriteEncryptedDataDirect = writeEncryptedDataDirect(orCreateLazyDirectBuffer, 0, iMin);
            byteBuffer.position(i);
            return iWriteEncryptedDataDirect;
        } finally {
            if (allocatedBufferAllocateDirectBuffer != null) {
                allocatedBufferAllocateDirectBuffer.release();
            }
        }
    }

    private ByteBuffer getOrCreateLazyDirectBuffer() {
        if (this.lazyDirectBuffer == null) {
            this.lazyDirectBuffer = ByteBuffer.allocateDirect(Math.max(16384, 16709));
        }
        this.lazyDirectBuffer.clear();
        return this.lazyDirectBuffer;
    }

    private long directByteBufferAddress(ByteBuffer byteBuffer, int i) {
        return NativeCrypto.getDirectBufferAddress(byteBuffer) + ((long) i);
    }

    private SSLEngineResult readPendingBytesFromBIO(ByteBuffer byteBuffer, int i, int i2, SSLEngineResult.HandshakeStatus handshakeStatus) throws SSLException {
        try {
            int iPendingOutboundEncryptedBytes = pendingOutboundEncryptedBytes();
            if (iPendingOutboundEncryptedBytes <= 0) {
                return null;
            }
            if (byteBuffer.remaining() < iPendingOutboundEncryptedBytes) {
                SSLEngineResult.Status status = SSLEngineResult.Status.BUFFER_OVERFLOW;
                if (handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED) {
                    handshakeStatus = getHandshakeStatus(iPendingOutboundEncryptedBytes);
                }
                return new SSLEngineResult(status, mayFinishHandshake(handshakeStatus), i, i2);
            }
            int encryptedData = readEncryptedData(byteBuffer, iPendingOutboundEncryptedBytes);
            if (encryptedData <= 0) {
                NativeCrypto.SSL_clear_error();
            } else {
                i2 += encryptedData;
                iPendingOutboundEncryptedBytes -= encryptedData;
            }
            SSLEngineResult.Status engineStatus = getEngineStatus();
            if (handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED) {
                handshakeStatus = getHandshakeStatus(iPendingOutboundEncryptedBytes);
            }
            return new SSLEngineResult(engineStatus, mayFinishHandshake(handshakeStatus), i, i2);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    private int readEncryptedData(ByteBuffer byteBuffer, int i) throws SSLException {
        try {
            int iPosition = byteBuffer.position();
            if (byteBuffer.remaining() < i) {
                return 0;
            }
            int iMin = Math.min(i, byteBuffer.limit() - iPosition);
            if (byteBuffer.isDirect()) {
                int encryptedDataDirect = readEncryptedDataDirect(byteBuffer, iPosition, iMin);
                if (encryptedDataDirect > 0) {
                    byteBuffer.position(iPosition + encryptedDataDirect);
                }
                return encryptedDataDirect;
            }
            return readEncryptedDataHeap(byteBuffer, iMin);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    private int readEncryptedDataDirect(ByteBuffer byteBuffer, int i, int i2) throws IOException {
        return this.networkBio.readDirectByteBuffer(directByteBufferAddress(byteBuffer, i), i2);
    }

    private int readEncryptedDataHeap(ByteBuffer byteBuffer, int i) throws IOException {
        ByteBuffer orCreateLazyDirectBuffer;
        AllocatedBuffer allocatedBufferAllocateDirectBuffer = null;
        try {
            BufferAllocator bufferAllocator = this.bufferAllocator;
            if (bufferAllocator != null) {
                allocatedBufferAllocateDirectBuffer = bufferAllocator.allocateDirectBuffer(i);
                orCreateLazyDirectBuffer = allocatedBufferAllocateDirectBuffer.nioBuffer();
            } else {
                orCreateLazyDirectBuffer = getOrCreateLazyDirectBuffer();
            }
            int encryptedDataDirect = readEncryptedDataDirect(orCreateLazyDirectBuffer, 0, Math.min(i, orCreateLazyDirectBuffer.remaining()));
            if (encryptedDataDirect > 0) {
                orCreateLazyDirectBuffer.position(encryptedDataDirect);
                orCreateLazyDirectBuffer.flip();
                byteBuffer.put(orCreateLazyDirectBuffer);
            }
            return encryptedDataDirect;
        } finally {
            if (allocatedBufferAllocateDirectBuffer != null) {
                allocatedBufferAllocateDirectBuffer.release();
            }
        }
    }

    private SSLEngineResult.HandshakeStatus mayFinishHandshake(SSLEngineResult.HandshakeStatus handshakeStatus) throws SSLException {
        return (this.handshakeFinished || handshakeStatus != SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) ? handshakeStatus : handshake();
    }

    private SSLEngineResult.HandshakeStatus getHandshakeStatus(int i) {
        return !this.handshakeFinished ? pendingStatus(i) : SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
    }

    private SSLEngineResult.Status getEngineStatus() {
        int i = this.state;
        if (i == 6 || i == 7 || i == 8) {
            return SSLEngineResult.Status.CLOSED;
        }
        return SSLEngineResult.Status.OK;
    }

    private void closeAll() {
        closeOutbound();
        closeInbound();
    }

    private void freeIfDone() {
        if (isInboundDone() && isOutboundDone()) {
            closeAndFreeResources();
        }
    }

    private SSLException newSslExceptionWithMessage(String str) {
        if (!this.handshakeFinished) {
            return new SSLException(str);
        }
        return new SSLHandshakeException(str);
    }

    private SSLEngineResult newResult(int i, int i2, SSLEngineResult.HandshakeStatus handshakeStatus) throws SSLException {
        SSLEngineResult.Status engineStatus = getEngineStatus();
        if (handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED) {
            handshakeStatus = getHandshakeStatusInternal();
        }
        return new SSLEngineResult(engineStatus, mayFinishHandshake(handshakeStatus), i, i2);
    }

    @Override // org.conscrypt.AbstractConscryptEngine, javax.net.ssl.SSLEngine
    public SSLEngineResult wrap(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws SSLException {
        SSLEngineResult sSLEngineResultWrap;
        synchronized (this.ssl) {
            try {
                sSLEngineResultWrap = wrap(singleSrcBuffer(byteBuffer), byteBuffer2);
                resetSingleSrcBuffer();
            } catch (Throwable th) {
                resetSingleSrcBuffer();
                throw th;
            }
        }
        return sSLEngineResultWrap;
    }

    @Override // org.conscrypt.AbstractConscryptEngine, javax.net.ssl.SSLEngine
    public SSLEngineResult wrap(ByteBuffer[] byteBufferArr, int i, int i2, ByteBuffer byteBuffer) throws SSLException {
        int iBytesProduced;
        int iWritePlaintextData;
        SSLEngineResult pendingBytesFromBIO;
        boolean z = true;
        Preconditions.checkArgument(byteBufferArr != null, "srcs is null");
        Preconditions.checkArgument(byteBuffer != null, "dst is null");
        int i3 = i + i2;
        Preconditions.checkPositionIndexes(i, i3, byteBufferArr.length);
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        if (i != 0 || i2 != byteBufferArr.length) {
            byteBufferArr = (ByteBuffer[]) Arrays.copyOfRange(byteBufferArr, i, i3);
        }
        BufferUtils.checkNotNull(byteBufferArr);
        synchronized (this.ssl) {
            int i4 = this.state;
            if (i4 == 0) {
                throw new IllegalStateException("Client/server mode must be set before calling wrap");
            }
            if (i4 == 1) {
                beginHandshakeInternal();
            } else if (i4 == 7 || i4 == 8) {
                SSLEngineResult pendingBytesFromBIO2 = readPendingBytesFromBIO(byteBuffer, 0, 0, SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING);
                if (pendingBytesFromBIO2 != null) {
                    freeIfDone();
                    return pendingBytesFromBIO2;
                }
                return new SSLEngineResult(SSLEngineResult.Status.CLOSED, getHandshakeStatusInternal(), 0, 0);
            }
            SSLEngineResult.HandshakeStatus handshakeStatusHandshake = SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
            if (!this.handshakeFinished) {
                handshakeStatusHandshake = handshake();
                if (handshakeStatusHandshake == SSLEngineResult.HandshakeStatus.NEED_UNWRAP) {
                    return NEED_UNWRAP_OK;
                }
                if (this.state == 8) {
                    return NEED_UNWRAP_CLOSED;
                }
            }
            int iMin = (int) Math.min(BufferUtils.remaining(byteBufferArr), 16384L);
            if (byteBuffer.remaining() < SSLUtils.calculateOutNetBufSize(iMin)) {
                return new SSLEngineResult(SSLEngineResult.Status.BUFFER_OVERFLOW, getHandshakeStatusInternal(), 0, 0);
            }
            if (iMin > 0) {
                ByteBuffer bufferLargerThan = BufferUtils.getBufferLargerThan(byteBufferArr, 16384);
                if (bufferLargerThan == null) {
                    bufferLargerThan = BufferUtils.copyNoConsume(byteBufferArr, getOrCreateLazyDirectBuffer(), 16384);
                } else {
                    z = false;
                }
                iWritePlaintextData = writePlaintextData(bufferLargerThan, Math.min(16384, bufferLargerThan.remaining()));
                if (iWritePlaintextData > 0) {
                    if (z) {
                        BufferUtils.consume(byteBufferArr, iWritePlaintextData);
                    }
                    SSLEngineResult pendingBytesFromBIO3 = readPendingBytesFromBIO(byteBuffer, iWritePlaintextData, 0, handshakeStatusHandshake);
                    if (pendingBytesFromBIO3 == null) {
                        iBytesProduced = 0;
                    } else {
                        if (pendingBytesFromBIO3.getStatus() != SSLEngineResult.Status.OK) {
                            return pendingBytesFromBIO3;
                        }
                        iBytesProduced = pendingBytesFromBIO3.bytesProduced();
                    }
                } else {
                    int error = this.ssl.getError(iWritePlaintextData);
                    if (error == 2) {
                        SSLEngineResult pendingBytesFromBIO4 = readPendingBytesFromBIO(byteBuffer, 0, 0, handshakeStatusHandshake);
                        if (pendingBytesFromBIO4 == null) {
                            pendingBytesFromBIO4 = new SSLEngineResult(getEngineStatus(), SSLEngineResult.HandshakeStatus.NEED_UNWRAP, 0, 0);
                        }
                        return pendingBytesFromBIO4;
                    }
                    if (error == 3) {
                        SSLEngineResult pendingBytesFromBIO5 = readPendingBytesFromBIO(byteBuffer, 0, 0, handshakeStatusHandshake);
                        if (pendingBytesFromBIO5 == null) {
                            pendingBytesFromBIO5 = NEED_WRAP_CLOSED;
                        }
                        return pendingBytesFromBIO5;
                    }
                    if (error == 6) {
                        closeAll();
                        SSLEngineResult pendingBytesFromBIO6 = readPendingBytesFromBIO(byteBuffer, 0, 0, handshakeStatusHandshake);
                        if (pendingBytesFromBIO6 == null) {
                            pendingBytesFromBIO6 = CLOSED_NOT_HANDSHAKING;
                        }
                        return pendingBytesFromBIO6;
                    }
                    closeAll();
                    throw newSslExceptionWithMessage("SSL_write: error " + error);
                }
            } else {
                iBytesProduced = 0;
                iWritePlaintextData = 0;
            }
            return (iWritePlaintextData != 0 || (pendingBytesFromBIO = readPendingBytesFromBIO(byteBuffer, 0, iBytesProduced, handshakeStatusHandshake)) == null) ? newResult(iWritePlaintextData, iBytesProduced, handshakeStatusHandshake) : pendingBytesFromBIO;
        }
    }

    @Override // org.conscrypt.NativeCrypto.SSLHandshakeCallbacks
    public int clientPSKKeyRequested(String str, byte[] bArr, byte[] bArr2) {
        return this.ssl.clientPSKKeyRequested(str, bArr, bArr2);
    }

    @Override // org.conscrypt.NativeCrypto.SSLHandshakeCallbacks
    public int serverPSKKeyRequested(String str, String str2, byte[] bArr) {
        return this.ssl.serverPSKKeyRequested(str, str2, bArr);
    }

    @Override // org.conscrypt.NativeCrypto.SSLHandshakeCallbacks
    public void onSSLStateChange(int i, int i2) {
        synchronized (this.ssl) {
            try {
                if (i == 16) {
                    transitionTo(2);
                } else if (i == 32) {
                    int i3 = this.state;
                    if (i3 != 2 && i3 != 4) {
                        throw new IllegalStateException("Completed handshake while in mode " + this.state);
                    }
                    transitionTo(3);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // org.conscrypt.NativeCrypto.SSLHandshakeCallbacks
    public void serverCertificateRequested() throws IOException {
        synchronized (this.ssl) {
            this.ssl.configureServerCertificate();
        }
    }

    @Override // org.conscrypt.NativeCrypto.SSLHandshakeCallbacks
    public void onNewSessionEstablished(long j) {
        try {
            NativeCrypto.SSL_SESSION_up_ref(j);
            sessionContext().cacheSession(NativeSslSession.newInstance(new NativeRef.SSL_SESSION(j), this.activeSession));
        } catch (Exception unused) {
        }
    }

    @Override // org.conscrypt.NativeCrypto.SSLHandshakeCallbacks
    public void verifyCertificateChain(byte[][] bArr, String str) throws CertificateException {
        if (bArr != null) {
            try {
                if (bArr.length != 0) {
                    X509Certificate[] x509CertificateArrDecodeX509CertificateChain = SSLUtils.decodeX509CertificateChain(bArr);
                    X509TrustManager x509TrustManager = this.sslParameters.getX509TrustManager();
                    if (x509TrustManager == null) {
                        throw new CertificateException("No X.509 TrustManager");
                    }
                    this.activeSession.onPeerCertificatesReceived(getPeerHost(), getPeerPort(), x509CertificateArrDecodeX509CertificateChain);
                    if (getUseClientMode()) {
                        Platform.checkServerTrusted(x509TrustManager, x509CertificateArrDecodeX509CertificateChain, str, this);
                        return;
                    } else {
                        Platform.checkClientTrusted(x509TrustManager, x509CertificateArrDecodeX509CertificateChain, x509CertificateArrDecodeX509CertificateChain[0].getPublicKey().getAlgorithm(), this);
                        return;
                    }
                }
            } catch (CertificateException e) {
                throw e;
            } catch (Exception e2) {
                throw new CertificateException(e2);
            }
        }
        throw new CertificateException("Peer sent no certificate");
    }

    @Override // org.conscrypt.NativeCrypto.SSLHandshakeCallbacks
    public void clientCertificateRequested(byte[] bArr, int[] iArr, byte[][] bArr2) throws SSLException, CertificateEncodingException {
        this.ssl.chooseClientCertificate(bArr, iArr, bArr2);
    }

    private void sendSSLShutdown() {
        try {
            this.ssl.shutdown();
        } catch (IOException unused) {
        }
    }

    private void closeAndFreeResources() {
        transitionTo(8);
        NativeSsl nativeSsl = this.ssl;
        if (nativeSsl != null) {
            nativeSsl.close();
        }
        NativeSsl.BioWrapper bioWrapper = this.networkBio;
        if (bioWrapper != null) {
            bioWrapper.close();
        }
    }

    protected void finalize() throws Throwable {
        try {
            closeAndFreeResources();
        } finally {
            super.finalize();
        }
    }

    @Override // org.conscrypt.SSLParametersImpl.AliasChooser
    public String chooseServerAlias(X509KeyManager x509KeyManager, String str) {
        if (x509KeyManager instanceof X509ExtendedKeyManager) {
            return ((X509ExtendedKeyManager) x509KeyManager).chooseEngineServerAlias(str, null, this);
        }
        return x509KeyManager.chooseServerAlias(str, null, null);
    }

    @Override // org.conscrypt.SSLParametersImpl.AliasChooser
    public String chooseClientAlias(X509KeyManager x509KeyManager, X500Principal[] x500PrincipalArr, String[] strArr) {
        if (x509KeyManager instanceof X509ExtendedKeyManager) {
            return ((X509ExtendedKeyManager) x509KeyManager).chooseEngineClientAlias(strArr, x500PrincipalArr, this);
        }
        return x509KeyManager.chooseClientAlias(strArr, x500PrincipalArr, null);
    }

    @Override // org.conscrypt.SSLParametersImpl.PSKCallbacks
    public String chooseServerPSKIdentityHint(PSKKeyManager pSKKeyManager) {
        return pSKKeyManager.chooseServerKeyIdentityHint(this);
    }

    @Override // org.conscrypt.SSLParametersImpl.PSKCallbacks
    public String chooseClientPSKIdentity(PSKKeyManager pSKKeyManager, String str) {
        return pSKKeyManager.chooseClientKeyIdentity(str, this);
    }

    @Override // org.conscrypt.SSLParametersImpl.PSKCallbacks
    public SecretKey getPSKKey(PSKKeyManager pSKKeyManager, String str, String str2) {
        return pSKKeyManager.getKey(str, str2, this);
    }

    @Override // org.conscrypt.AbstractConscryptEngine
    void setUseSessionTickets(boolean z) {
        this.sslParameters.setUseSessionTickets(z);
    }

    @Override // org.conscrypt.AbstractConscryptEngine
    String[] getApplicationProtocols() {
        return this.sslParameters.getApplicationProtocols();
    }

    @Override // org.conscrypt.AbstractConscryptEngine
    void setApplicationProtocols(String[] strArr) {
        this.sslParameters.setApplicationProtocols(strArr);
    }

    @Override // org.conscrypt.AbstractConscryptEngine
    void setApplicationProtocolSelector(ApplicationProtocolSelector applicationProtocolSelector) {
        setApplicationProtocolSelector(applicationProtocolSelector == null ? null : new ApplicationProtocolSelectorAdapter(this, applicationProtocolSelector));
    }

    @Override // org.conscrypt.AbstractConscryptEngine
    byte[] getTlsUnique() {
        return this.ssl.getTlsUnique();
    }

    @Override // org.conscrypt.AbstractConscryptEngine
    byte[] exportKeyingMaterial(String str, byte[] bArr, int i) throws SSLException {
        synchronized (this.ssl) {
            int i2 = this.state;
            if (i2 >= 3 && i2 != 8) {
                return this.ssl.exportKeyingMaterial(str, bArr, i);
            }
            return null;
        }
    }

    void setApplicationProtocolSelector(ApplicationProtocolSelectorAdapter applicationProtocolSelectorAdapter) {
        this.sslParameters.setApplicationProtocolSelector(applicationProtocolSelectorAdapter);
    }

    @Override // org.conscrypt.NativeCrypto.SSLHandshakeCallbacks
    public int selectApplicationProtocol(byte[] bArr) {
        ApplicationProtocolSelectorAdapter applicationProtocolSelector = this.sslParameters.getApplicationProtocolSelector();
        if (applicationProtocolSelector == null) {
            return 3;
        }
        return applicationProtocolSelector.selectApplicationProtocol(bArr);
    }

    @Override // org.conscrypt.AbstractConscryptEngine, javax.net.ssl.SSLEngine
    public String getApplicationProtocol() {
        return provideAfterHandshakeSession().getApplicationProtocol();
    }

    @Override // org.conscrypt.AbstractConscryptEngine, javax.net.ssl.SSLEngine
    public String getHandshakeApplicationProtocol() {
        String applicationProtocol;
        synchronized (this.ssl) {
            applicationProtocol = this.state >= 2 ? getApplicationProtocol() : null;
        }
        return applicationProtocol;
    }

    private ByteBuffer[] singleSrcBuffer(ByteBuffer byteBuffer) {
        ByteBuffer[] byteBufferArr = this.singleSrcBuffer;
        byteBufferArr[0] = byteBuffer;
        return byteBufferArr;
    }

    private void resetSingleSrcBuffer() {
        this.singleSrcBuffer[0] = null;
    }

    private ByteBuffer[] singleDstBuffer(ByteBuffer byteBuffer) {
        ByteBuffer[] byteBufferArr = this.singleDstBuffer;
        byteBufferArr[0] = byteBuffer;
        return byteBufferArr;
    }

    private void resetSingleDstBuffer() {
        this.singleDstBuffer[0] = null;
    }

    private ClientSessionContext clientSessionContext() {
        return this.sslParameters.getClientSessionContext();
    }

    private AbstractSessionContext sessionContext() {
        return this.sslParameters.getSessionContext();
    }

    private void transitionTo(int i) {
        int i2;
        if (i == 2) {
            this.handshakeFinished = false;
            this.activeSession = new ActiveSession(this.ssl, this.sslParameters.getSessionContext());
        } else if (i == 8 && !this.ssl.isClosed() && (i2 = this.state) >= 2 && i2 < 8) {
            this.closedSession = new SessionSnapshot(this.activeSession);
        }
        this.state = i;
    }
}
