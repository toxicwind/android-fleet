package org.conscrypt;

import io.ktor.util.NonceKt$$ExternalSyntheticApiModelOutline0;
import j$.util.DesugarCollections;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXParameters;
import java.security.cert.PKIXRevocationChecker;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509ExtendedTrustManager;
import org.conscrypt.ct.CTLogStore;
import org.conscrypt.ct.CTPolicy;
import org.conscrypt.ct.CTVerifier;
import retrofit2.Reflection$Java8$$ExternalSyntheticApiModelOutline0;

/* JADX INFO: loaded from: classes8.dex */
public final class TrustManagerImpl extends X509ExtendedTrustManager {
    private static ConscryptHostnameVerifier defaultHostnameVerifier;
    private final X509Certificate[] acceptedIssuers;
    private final CertBlocklist blocklist;
    private boolean ctEnabledOverride;
    private CTPolicy ctPolicy;
    private CTVerifier ctVerifier;
    private final Exception err;
    private final CertificateFactory factory;
    private ConscryptHostnameVerifier hostnameVerifier;
    private final TrustedCertificateIndex intermediateIndex;
    private CertPinManager pinManager;
    private final KeyStore rootKeyStore;
    private final TrustedCertificateIndex trustedCertificateIndex;
    private final ConscryptCertStore trustedCertificateStore;
    private final CertPathValidator validator;
    private static final Logger logger = Logger.getLogger(TrustManagerImpl.class.getName());
    private static final TrustAnchorComparator TRUST_ANCHOR_COMPARATOR = new TrustAnchorComparator();

    public TrustManagerImpl(KeyStore keyStore) {
        this(keyStore, null);
    }

    public TrustManagerImpl(KeyStore keyStore, CertPinManager certPinManager) {
        this(keyStore, certPinManager, null);
    }

    public TrustManagerImpl(KeyStore keyStore, CertPinManager certPinManager, ConscryptCertStore conscryptCertStore) {
        this(keyStore, certPinManager, conscryptCertStore, null);
    }

    public TrustManagerImpl(KeyStore keyStore, CertPinManager certPinManager, ConscryptCertStore conscryptCertStore, CertBlocklist certBlocklist) {
        this(keyStore, certPinManager, conscryptCertStore, certBlocklist, null, null, null);
    }

    /* JADX WARN: Code duplicated, block: B:35:0x007f  */
    /* JADX WARN: Code duplicated, block: B:37:0x0085  */
    /* JADX WARN: Code duplicated, block: B:39:0x008b  */
    /* JADX WARN: Multi-variable type inference failed */
    public TrustManagerImpl(KeyStore keyStore, CertPinManager certPinManager, ConscryptCertStore conscryptCertStore, CertBlocklist certBlocklist, CTLogStore cTLogStore, CTVerifier cTVerifier, CTPolicy cTPolicy) {
        X509Certificate[] x509CertificateArr;
        Object obj;
        CertPathValidator certPathValidator;
        Exception exc;
        CertificateFactory certificateFactory;
        ConscryptCertStore conscryptCertStore2;
        CertificateFactory certificateFactory2;
        CertPathValidator certPathValidator2;
        TrustedCertificateIndex trustedCertificateIndex;
        ConscryptCertStore conscryptCertStore3;
        TrustedCertificateIndex trustedCertificateIndex2 = null;
        try {
            certPathValidator2 = CertPathValidator.getInstance("PKIX");
            try {
                certificateFactory2 = CertificateFactory.getInstance("X509");
                try {
                    if ("AndroidCAStore".equals(keyStore.getType()) && Platform.supportsConscryptCertStore()) {
                        if (conscryptCertStore == null) {
                            try {
                                conscryptCertStore = Platform.newDefaultCertStore();
                            } catch (Exception e) {
                                e = e;
                                x509CertificateArr = null;
                                exc = e;
                                certificateFactory = certificateFactory2;
                                certPathValidator = certPathValidator2;
                                obj = x509CertificateArr;
                                CertificateFactory certificateFactory3 = certificateFactory;
                                conscryptCertStore2 = obj;
                                certPathValidator2 = certPathValidator;
                                certificateFactory2 = certificateFactory3;
                                certBlocklist = certBlocklist == null ? Platform.newDefaultBlocklist() : certBlocklist;
                                cTLogStore = cTLogStore == null ? Platform.newDefaultLogStore() : cTLogStore;
                                cTPolicy = cTPolicy == null ? Platform.newDefaultPolicy(cTLogStore) : cTPolicy;
                                this.pinManager = certPinManager;
                                this.rootKeyStore = keyStore;
                                this.trustedCertificateStore = conscryptCertStore2;
                                this.validator = certPathValidator2;
                                this.factory = certificateFactory2;
                                this.trustedCertificateIndex = trustedCertificateIndex2;
                                this.intermediateIndex = new TrustedCertificateIndex();
                                this.acceptedIssuers = x509CertificateArr;
                                this.err = exc;
                                this.blocklist = certBlocklist;
                                this.ctVerifier = new CTVerifier(cTLogStore);
                                this.ctPolicy = cTPolicy;
                            }
                        }
                        try {
                            trustedCertificateIndex = new TrustedCertificateIndex();
                            conscryptCertStore3 = conscryptCertStore;
                            x509CertificateArr = null;
                            TrustedCertificateIndex trustedCertificateIndex3 = trustedCertificateIndex;
                            exc = null;
                            trustedCertificateIndex2 = trustedCertificateIndex3;
                            conscryptCertStore2 = conscryptCertStore3;
                        } catch (Exception e2) {
                            exc = e2;
                            certificateFactory = certificateFactory2;
                            certPathValidator = certPathValidator2;
                            obj = conscryptCertStore;
                            x509CertificateArr = null;
                            CertificateFactory certificateFactory4 = certificateFactory;
                            conscryptCertStore2 = obj;
                            certPathValidator2 = certPathValidator;
                            certificateFactory2 = certificateFactory4;
                        }
                    } else {
                        try {
                            X509Certificate[] x509CertificateArrAcceptedIssuers = acceptedIssuers(keyStore);
                            try {
                                trustedCertificateIndex = new TrustedCertificateIndex(trustAnchors(x509CertificateArrAcceptedIssuers));
                                conscryptCertStore3 = conscryptCertStore;
                                x509CertificateArr = x509CertificateArrAcceptedIssuers;
                                keyStore = null;
                                TrustedCertificateIndex trustedCertificateIndex4 = trustedCertificateIndex;
                                exc = null;
                                trustedCertificateIndex2 = trustedCertificateIndex4;
                                conscryptCertStore2 = conscryptCertStore3;
                            } catch (Exception e3) {
                                exc = e3;
                                certificateFactory = certificateFactory2;
                                certPathValidator = certPathValidator2;
                                obj = conscryptCertStore;
                                x509CertificateArr = x509CertificateArrAcceptedIssuers;
                                keyStore = null;
                                CertificateFactory certificateFactory5 = certificateFactory;
                                conscryptCertStore2 = obj;
                                certPathValidator2 = certPathValidator;
                                certificateFactory2 = certificateFactory5;
                            }
                        } catch (Exception e4) {
                            keyStore = null;
                            exc = e4;
                            certificateFactory = certificateFactory2;
                            certPathValidator = certPathValidator2;
                            obj = conscryptCertStore;
                            x509CertificateArr = null;
                        }
                    }
                } catch (Exception e5) {
                    e = e5;
                    keyStore = null;
                    x509CertificateArr = null;
                }
            } catch (Exception e6) {
                keyStore = null;
                x509CertificateArr = null;
                certPathValidator = certPathValidator2;
                exc = e6;
                obj = null;
                certificateFactory = null;
            }
        } catch (Exception e7) {
            keyStore = null;
            x509CertificateArr = null;
            obj = null;
            certPathValidator = null;
            exc = e7;
            certificateFactory = null;
        }
        if (certBlocklist == null) {
        }
        if (cTLogStore == null) {
        }
        if (cTPolicy == null) {
        }
        this.pinManager = certPinManager;
        this.rootKeyStore = keyStore;
        this.trustedCertificateStore = conscryptCertStore2;
        this.validator = certPathValidator2;
        this.factory = certificateFactory2;
        this.trustedCertificateIndex = trustedCertificateIndex2;
        this.intermediateIndex = new TrustedCertificateIndex();
        this.acceptedIssuers = x509CertificateArr;
        this.err = exc;
        this.blocklist = certBlocklist;
        this.ctVerifier = new CTVerifier(cTLogStore);
        this.ctPolicy = cTPolicy;
    }

    private static X509Certificate[] acceptedIssuers(KeyStore keyStore) {
        try {
            ArrayList arrayList = new ArrayList();
            Enumeration<String> enumerationAliases = keyStore.aliases();
            while (enumerationAliases.hasMoreElements()) {
                X509Certificate x509Certificate = (X509Certificate) keyStore.getCertificate(enumerationAliases.nextElement());
                if (x509Certificate != null) {
                    arrayList.add(x509Certificate);
                }
            }
            return (X509Certificate[]) arrayList.toArray(new X509Certificate[arrayList.size()]);
        } catch (KeyStoreException unused) {
            return new X509Certificate[0];
        }
    }

    private static Set<TrustAnchor> trustAnchors(X509Certificate[] x509CertificateArr) {
        HashSet hashSet = new HashSet(x509CertificateArr.length);
        for (X509Certificate x509Certificate : x509CertificateArr) {
            hashSet.add(new TrustAnchor(x509Certificate, null));
        }
        return hashSet;
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        checkTrusted(x509CertificateArr, str, null, null, true);
    }

    public List<X509Certificate> checkClientTrusted(X509Certificate[] x509CertificateArr, String str, String str2) throws CertificateException {
        return checkTrusted(x509CertificateArr, null, null, str, str2, true);
    }

    private static SSLSession getHandshakeSessionOrThrow(SSLSocket sSLSocket) throws CertificateException {
        SSLSession handshakeSession = sSLSocket.getHandshakeSession();
        if (handshakeSession != null) {
            return handshakeSession;
        }
        throw new CertificateException("Not in handshake; no session available");
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        SSLSession sSLSession;
        SSLParameters sSLParameters;
        if (socket instanceof SSLSocket) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            SSLSession handshakeSessionOrThrow = getHandshakeSessionOrThrow(sSLSocket);
            sSLParameters = sSLSocket.getSSLParameters();
            sSLSession = handshakeSessionOrThrow;
        } else {
            sSLSession = null;
            sSLParameters = null;
        }
        checkTrusted(x509CertificateArr, str, sSLSession, sSLParameters, true);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        SSLSession handshakeSession = sSLEngine.getHandshakeSession();
        if (handshakeSession == null) {
            throw new CertificateException("Not in handshake; no session available");
        }
        checkTrusted(x509CertificateArr, str, handshakeSession, sSLEngine.getSSLParameters(), true);
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        checkTrusted(x509CertificateArr, str, null, null, false);
    }

    public List<X509Certificate> checkServerTrusted(X509Certificate[] x509CertificateArr, String str, String str2) throws CertificateException {
        return checkTrusted(x509CertificateArr, null, null, str, str2, false);
    }

    public List<X509Certificate> getTrustedChainForServer(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        SSLSession sSLSession;
        SSLParameters sSLParameters;
        if (socket instanceof SSLSocket) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            SSLSession handshakeSessionOrThrow = getHandshakeSessionOrThrow(sSLSocket);
            sSLParameters = sSLSocket.getSSLParameters();
            sSLSession = handshakeSessionOrThrow;
        } else {
            sSLSession = null;
            sSLParameters = null;
        }
        return checkTrusted(x509CertificateArr, str, sSLSession, sSLParameters, false);
    }

    public List<X509Certificate> getTrustedChainForServer(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        SSLSession handshakeSession = sSLEngine.getHandshakeSession();
        if (handshakeSession == null) {
            throw new CertificateException("Not in handshake; no session available");
        }
        return checkTrusted(x509CertificateArr, str, handshakeSession, sSLEngine.getSSLParameters(), false);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        getTrustedChainForServer(x509CertificateArr, str, socket);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        getTrustedChainForServer(x509CertificateArr, str, sSLEngine);
    }

    public List<X509Certificate> checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLSession sSLSession) throws CertificateException {
        return checkTrusted(x509CertificateArr, str, sSLSession, null, false);
    }

    public void handleTrustStorageUpdate() {
        X509Certificate[] x509CertificateArr = this.acceptedIssuers;
        TrustedCertificateIndex trustedCertificateIndex = this.trustedCertificateIndex;
        if (x509CertificateArr == null) {
            trustedCertificateIndex.reset();
        } else {
            trustedCertificateIndex.reset(trustAnchors(x509CertificateArr));
        }
    }

    private List<X509Certificate> checkTrusted(X509Certificate[] x509CertificateArr, String str, SSLSession sSLSession, SSLParameters sSLParameters, boolean z) throws CertificateException {
        byte[] ocspDataFromSession;
        byte[] tlsSctDataFromSession;
        String peerHost;
        if (sSLSession != null) {
            peerHost = sSLSession.getPeerHost();
            ocspDataFromSession = getOcspDataFromSession(sSLSession);
            tlsSctDataFromSession = getTlsSctDataFromSession(sSLSession);
        } else {
            ocspDataFromSession = null;
            tlsSctDataFromSession = null;
            peerHost = null;
        }
        if (sSLSession != null && sSLParameters != null && "HTTPS".equalsIgnoreCase(sSLParameters.getEndpointIdentificationAlgorithm()) && !getHttpsVerifier().verify(x509CertificateArr, peerHost, sSLSession)) {
            throw new CertificateException("No subjectAltNames on the certificate match");
        }
        return checkTrusted(x509CertificateArr, ocspDataFromSession, tlsSctDataFromSession, str, peerHost, z);
    }

    private static byte[] getOcspDataFromSession(SSLSession sSLSession) {
        List<byte[]> statusResponses;
        if (sSLSession instanceof ConscryptSession) {
            statusResponses = ((ConscryptSession) sSLSession).getStatusResponses();
        } else {
            try {
                Method declaredMethod = sSLSession.getClass().getDeclaredMethod("getStatusResponses", null);
                declaredMethod.setAccessible(true);
                Object objInvoke = declaredMethod.invoke(sSLSession, null);
                statusResponses = objInvoke instanceof List ? (List) objInvoke : null;
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException unused) {
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        if (statusResponses == null || statusResponses.isEmpty()) {
            return null;
        }
        return statusResponses.get(0);
    }

    private byte[] getTlsSctDataFromSession(SSLSession sSLSession) {
        if (sSLSession instanceof ConscryptSession) {
            return ((ConscryptSession) sSLSession).getPeerSignedCertificateTimestamp();
        }
        try {
            Method declaredMethod = sSLSession.getClass().getDeclaredMethod("getPeerSignedCertificateTimestamp", null);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(sSLSession, null);
            if (objInvoke instanceof byte[]) {
                return (byte[]) objInvoke;
            }
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException unused) {
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getCause());
        }
        return null;
    }

    private List<X509Certificate> checkTrusted(X509Certificate[] x509CertificateArr, byte[] bArr, byte[] bArr2, String str, String str2, boolean z) throws CertificateException {
        if (x509CertificateArr == null || x509CertificateArr.length == 0 || str == null || str.length() == 0) {
            throw new IllegalArgumentException("null or zero-length parameter");
        }
        if (this.err != null) {
            throw new CertificateException(this.err);
        }
        HashSet hashSet = new HashSet();
        ArrayList<X509Certificate> arrayList = new ArrayList<>();
        ArrayList<TrustAnchor> arrayList2 = new ArrayList<>();
        X509Certificate x509Certificate = x509CertificateArr[0];
        TrustAnchor trustAnchorFindTrustAnchorBySubjectAndPublicKey = findTrustAnchorBySubjectAndPublicKey(x509Certificate);
        if (trustAnchorFindTrustAnchorBySubjectAndPublicKey != null) {
            arrayList2.add(trustAnchorFindTrustAnchorBySubjectAndPublicKey);
            hashSet.add(trustAnchorFindTrustAnchorBySubjectAndPublicKey.getTrustedCert());
        } else {
            arrayList.add(x509Certificate);
        }
        hashSet.add(x509Certificate);
        return checkTrustedRecursive(x509CertificateArr, bArr, bArr2, str2, z, arrayList, arrayList2, hashSet);
    }

    private List<X509Certificate> checkTrustedRecursive(X509Certificate[] x509CertificateArr, byte[] bArr, byte[] bArr2, String str, boolean z, ArrayList<X509Certificate> arrayList, ArrayList<TrustAnchor> arrayList2, Set<X509Certificate> set) throws CertificateException {
        X509Certificate trustedCert;
        if (arrayList2.isEmpty()) {
            trustedCert = arrayList.get(arrayList.size() - 1);
        } else {
            trustedCert = arrayList2.get(arrayList2.size() - 1).getTrustedCert();
        }
        X509Certificate x509Certificate = trustedCert;
        checkBlocklist(x509Certificate);
        if (x509Certificate.getIssuerDN().equals(x509Certificate.getSubjectDN())) {
            return verifyChain(arrayList, arrayList2, str, z, bArr, bArr2);
        }
        boolean z2 = false;
        CertificateException certificateException = null;
        for (TrustAnchor trustAnchor : sortPotentialAnchors(findAllTrustAnchorsByIssuerAndSignature(x509Certificate))) {
            X509Certificate trustedCert2 = trustAnchor.getTrustedCert();
            if (!set.contains(trustedCert2)) {
                set.add(trustedCert2);
                arrayList2.add(trustAnchor);
                try {
                    return this.checkTrustedRecursive(x509CertificateArr, bArr, bArr2, str, z, arrayList, arrayList2, set);
                } catch (CertificateException e) {
                    certificateException = e;
                    arrayList2.remove(arrayList2.size() - 1);
                    set.remove(trustedCert2);
                    z2 = true;
                }
            }
        }
        if (!arrayList2.isEmpty()) {
            if (!z2) {
                return this.verifyChain(arrayList, arrayList2, str, z, bArr, bArr2);
            }
            throw certificateException;
        }
        for (int i = 1; i < x509CertificateArr.length; i++) {
            X509Certificate x509Certificate2 = x509CertificateArr[i];
            if (!set.contains(x509Certificate2) && x509Certificate.getIssuerDN().equals(x509Certificate2.getSubjectDN())) {
                try {
                    x509Certificate2.checkValidity();
                    ChainStrengthAnalyzer.checkCert(x509Certificate2);
                    set.add(x509Certificate2);
                    arrayList.add(x509Certificate2);
                    try {
                        return this.checkTrustedRecursive(x509CertificateArr, bArr, bArr2, str, z, arrayList, arrayList2, set);
                    } catch (CertificateException e2) {
                        set.remove(x509Certificate2);
                        arrayList.remove(arrayList.size() - 1);
                        certificateException = e2;
                    }
                } catch (CertificateException e3) {
                    certificateException = new CertificateException("Unacceptable certificate: " + x509Certificate2.getSubjectX500Principal(), e3);
                }
            }
        }
        Iterator<TrustAnchor> it = sortPotentialAnchors(this.intermediateIndex.findAllByIssuerAndSignature(x509Certificate)).iterator();
        while (it.hasNext()) {
            X509Certificate trustedCert3 = it.next().getTrustedCert();
            if (!set.contains(trustedCert3)) {
                set.add(trustedCert3);
                arrayList.add(trustedCert3);
                try {
                    return this.checkTrustedRecursive(x509CertificateArr, bArr, bArr2, str, z, arrayList, arrayList2, set);
                } catch (CertificateException e4) {
                    certificateException = e4;
                    arrayList.remove(arrayList.size() - 1);
                    set.remove(trustedCert3);
                }
            }
        }
        if (certificateException != null) {
            throw certificateException;
        }
        throw new CertificateException(new CertPathValidatorException("Trust anchor for certification path not found.", null, this.factory.generateCertPath(arrayList), -1));
    }

    private List<X509Certificate> verifyChain(List<X509Certificate> list, List<TrustAnchor> list2, String str, boolean z, byte[] bArr, byte[] bArr2) throws CertificateException {
        try {
            CertPath certPathGenerateCertPath = this.factory.generateCertPath(list);
            if (list2.isEmpty()) {
                throw new CertificateException(new CertPathValidatorException("Trust anchor for certification path not found.", null, certPathGenerateCertPath, -1));
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(list);
            Iterator<TrustAnchor> it = list2.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getTrustedCert());
            }
            CertPinManager certPinManager = this.pinManager;
            if (certPinManager != null) {
                certPinManager.checkChainPinning(str, arrayList);
            }
            Iterator<X509Certificate> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                checkBlocklist(it2.next());
            }
            if (!z && (this.ctEnabledOverride || (str != null && Platform.isCTVerificationRequired(str)))) {
                checkCT(str, arrayList, bArr, bArr2);
            }
            if (!list.isEmpty()) {
                ChainStrengthAnalyzer.check(list);
                try {
                    HashSet hashSet = new HashSet();
                    hashSet.add(list2.get(0));
                    PKIXParameters pKIXParameters = new PKIXParameters(hashSet);
                    pKIXParameters.setRevocationEnabled(false);
                    X509Certificate x509Certificate = list.get(0);
                    setOcspResponses(pKIXParameters, x509Certificate, bArr);
                    pKIXParameters.addCertPathChecker(new ExtendedKeyUsagePKIXCertPathChecker(z, x509Certificate));
                    this.validator.validate(certPathGenerateCertPath, pKIXParameters);
                    for (int i = 1; i < list.size(); i++) {
                        this.intermediateIndex.index(list.get(i));
                    }
                } catch (InvalidAlgorithmParameterException e) {
                    throw new CertificateException("Chain validation failed", e);
                } catch (CertPathValidatorException e2) {
                    throw new CertificateException("Chain validation failed", e2);
                }
            }
            return arrayList;
        } catch (CertificateException e3) {
            logger.fine("Rejected candidate cert chain due to error: " + e3.getMessage());
            throw e3;
        }
    }

    private void checkBlocklist(X509Certificate x509Certificate) throws CertificateException {
        CertBlocklist certBlocklist = this.blocklist;
        if (certBlocklist == null || !certBlocklist.isPublicKeyBlockListed(x509Certificate.getPublicKey())) {
            return;
        }
        throw new CertificateException("Certificate blocklisted by public key: " + x509Certificate);
    }

    private void checkCT(String str, List<X509Certificate> list, byte[] bArr, byte[] bArr2) throws CertificateException {
        if (!this.ctPolicy.doesResultConformToPolicy(this.ctVerifier.verifySignedCertificateTimestamps(list, bArr2, bArr), str, (X509Certificate[]) list.toArray(new X509Certificate[list.size()]))) {
            throw new CertificateException("Certificate chain does not conform to required transparency policy.");
        }
    }

    private void setOcspResponses(PKIXParameters pKIXParameters, X509Certificate x509Certificate, byte[] bArr) {
        PKIXRevocationChecker pKIXRevocationCheckerM11035m;
        if (bArr == null) {
            return;
        }
        List<PKIXCertPathChecker> arrayList = new ArrayList<>(pKIXParameters.getCertPathCheckers());
        Iterator<PKIXCertPathChecker> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                pKIXRevocationCheckerM11035m = null;
                break;
            }
            PKIXCertPathChecker next = it.next();
            if (NonceKt$$ExternalSyntheticApiModelOutline0.m$7(next)) {
                pKIXRevocationCheckerM11035m = Reflection$Java8$$ExternalSyntheticApiModelOutline0.m11035m((Object) next);
                break;
            }
        }
        if (pKIXRevocationCheckerM11035m == null) {
            try {
                pKIXRevocationCheckerM11035m = Reflection$Java8$$ExternalSyntheticApiModelOutline0.m11035m((Object) this.validator.getRevocationChecker());
                arrayList.add(pKIXRevocationCheckerM11035m);
                pKIXRevocationCheckerM11035m.setOptions(Collections.singleton(PKIXRevocationChecker.Option.ONLY_END_ENTITY));
            } catch (UnsupportedOperationException unused) {
                return;
            }
        }
        pKIXRevocationCheckerM11035m.setOcspResponses(Collections.singletonMap(x509Certificate, bArr));
        pKIXParameters.setCertPathCheckers(arrayList);
    }

    private static Collection<TrustAnchor> sortPotentialAnchors(Set<TrustAnchor> set) {
        if (set.size() <= 1) {
            return set;
        }
        ArrayList arrayList = new ArrayList(set);
        Collections.sort(arrayList, TRUST_ANCHOR_COMPARATOR);
        return arrayList;
    }

    private static class TrustAnchorComparator implements Comparator<TrustAnchor> {
        private static final CertificatePriorityComparator CERT_COMPARATOR = new CertificatePriorityComparator();

        private TrustAnchorComparator() {
        }

        @Override // java.util.Comparator
        public int compare(TrustAnchor trustAnchor, TrustAnchor trustAnchor2) {
            return CERT_COMPARATOR.compare(trustAnchor.getTrustedCert(), trustAnchor2.getTrustedCert());
        }
    }

    private static class ExtendedKeyUsagePKIXCertPathChecker extends PKIXCertPathChecker {
        private static final String EKU_anyExtendedKeyUsage = "2.5.29.37.0";
        private static final String EKU_clientAuth = "1.3.6.1.5.5.7.3.2";
        private static final String EKU_msSGC = "1.3.6.1.4.1.311.10.3.3";
        private static final String EKU_nsSGC = "2.16.840.1.113730.4.1";
        private static final String EKU_serverAuth = "1.3.6.1.5.5.7.3.1";
        private final boolean clientAuth;
        private final X509Certificate leaf;
        private static final String EKU_OID = "2.5.29.37";
        private static final Set<String> SUPPORTED_EXTENSIONS = DesugarCollections.unmodifiableSet(new HashSet(Arrays.asList(EKU_OID)));

        @Override // java.security.cert.PKIXCertPathChecker, java.security.cert.CertPathChecker
        public void init(boolean z) throws CertPathValidatorException {
        }

        @Override // java.security.cert.PKIXCertPathChecker, java.security.cert.CertPathChecker
        public boolean isForwardCheckingSupported() {
            return true;
        }

        private ExtendedKeyUsagePKIXCertPathChecker(boolean z, X509Certificate x509Certificate) {
            this.clientAuth = z;
            this.leaf = x509Certificate;
        }

        @Override // java.security.cert.PKIXCertPathChecker
        public Set<String> getSupportedExtensions() {
            return SUPPORTED_EXTENSIONS;
        }

        @Override // java.security.cert.PKIXCertPathChecker
        public void check(Certificate certificate, Collection<String> collection) throws CertPathValidatorException {
            X509Certificate x509Certificate = this.leaf;
            if (certificate != x509Certificate) {
                return;
            }
            try {
                List<String> extendedKeyUsage = x509Certificate.getExtendedKeyUsage();
                if (extendedKeyUsage == null) {
                    return;
                }
                for (String str : extendedKeyUsage) {
                    if (!str.equals(EKU_anyExtendedKeyUsage)) {
                        if (this.clientAuth) {
                            if (str.equals(EKU_clientAuth)) {
                            }
                        } else if (!str.equals(EKU_serverAuth) && !str.equals(EKU_nsSGC) && !str.equals(EKU_msSGC)) {
                        }
                    }
                    collection.remove(EKU_OID);
                    return;
                }
                throw new CertPathValidatorException("End-entity certificate does not have a valid extendedKeyUsage.");
            } catch (CertificateParsingException e) {
                throw new CertPathValidatorException(e);
            }
        }
    }

    private Set<TrustAnchor> findAllTrustAnchorsByIssuerAndSignature(X509Certificate x509Certificate) {
        ConscryptCertStore conscryptCertStore;
        Set<TrustAnchor> setFindAllByIssuerAndSignature = this.trustedCertificateIndex.findAllByIssuerAndSignature(x509Certificate);
        if (setFindAllByIssuerAndSignature.isEmpty() && (conscryptCertStore = this.trustedCertificateStore) != null) {
            Set<X509Certificate> setFindAllIssuers = conscryptCertStore.findAllIssuers(x509Certificate);
            if (!setFindAllIssuers.isEmpty()) {
                setFindAllByIssuerAndSignature = new HashSet<>(setFindAllIssuers.size());
                Iterator<X509Certificate> it = setFindAllIssuers.iterator();
                while (it.hasNext()) {
                    setFindAllByIssuerAndSignature.add(this.trustedCertificateIndex.index(it.next()));
                }
            }
        }
        return setFindAllByIssuerAndSignature;
    }

    private TrustAnchor findTrustAnchorBySubjectAndPublicKey(X509Certificate x509Certificate) {
        X509Certificate trustAnchor;
        TrustAnchor trustAnchorFindBySubjectAndPublicKey = this.trustedCertificateIndex.findBySubjectAndPublicKey(x509Certificate);
        if (trustAnchorFindBySubjectAndPublicKey != null) {
            return trustAnchorFindBySubjectAndPublicKey;
        }
        ConscryptCertStore conscryptCertStore = this.trustedCertificateStore;
        if (conscryptCertStore == null || (trustAnchor = conscryptCertStore.getTrustAnchor(x509Certificate)) == null) {
            return null;
        }
        return new TrustAnchor(trustAnchor, null);
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        X509Certificate[] x509CertificateArr = this.acceptedIssuers;
        return x509CertificateArr != null ? (X509Certificate[]) x509CertificateArr.clone() : acceptedIssuers(this.rootKeyStore);
    }

    static synchronized void setDefaultHostnameVerifier(ConscryptHostnameVerifier conscryptHostnameVerifier) {
        defaultHostnameVerifier = conscryptHostnameVerifier;
    }

    static synchronized ConscryptHostnameVerifier getDefaultHostnameVerifier() {
        return defaultHostnameVerifier;
    }

    void setHostnameVerifier(ConscryptHostnameVerifier conscryptHostnameVerifier) {
        this.hostnameVerifier = conscryptHostnameVerifier;
    }

    ConscryptHostnameVerifier getHostnameVerifier() {
        return this.hostnameVerifier;
    }

    private ConscryptHostnameVerifier getHttpsVerifier() {
        ConscryptHostnameVerifier conscryptHostnameVerifier = this.hostnameVerifier;
        return conscryptHostnameVerifier != null ? conscryptHostnameVerifier : Platform.getDefaultHostnameVerifier();
    }

    public void setCTEnabledOverride(boolean z) {
        this.ctEnabledOverride = z;
    }

    public void setCTVerifier(CTVerifier cTVerifier) {
        this.ctVerifier = cTVerifier;
    }

    public void setCTPolicy(CTPolicy cTPolicy) {
        this.ctPolicy = cTPolicy;
    }
}
