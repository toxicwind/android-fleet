package io.ktor.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.NonCancellable;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: Nonce.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0001\u0010\u0002\u001a\u000f\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001d\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\b\u0010\t\u001a\u001f\u0010\r\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000e\"\u001c\u0010\u0011\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012\"\u0014\u0010\u0013\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014\"\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017\"\u0014\u0010\u0018\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0018\u0010\u0014\"\u0014\u0010\u0019\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a\"\u0014\u0010\u001b\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001a\"\u0014\u0010\u001c\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001a\"\u0014\u0010\u001d\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001a\" \u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060\u001e8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%\"\u001a\u0010'\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\f\n\u0004\b'\u0010(\u0012\u0004\b)\u0010\u0002¨\u0006*"}, d2 = {"", "ensureNonceGeneratorRunning", "()V", "Ljava/security/SecureRandom;", "lookupSecureRandom", "()Ljava/security/SecureRandom;", "", "name", "getInstanceOrNull", "(Ljava/lang/String;)Ljava/security/SecureRandom;", "key", "", "default", "getSystemPropertyInt", "(Ljava/lang/String;I)I", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "logger", "Lorg/slf4j/Logger;", NonceKt.SHA1PRNG, "Ljava/lang/String;", "", "SECURE_RANDOM_PROVIDERS", "Ljava/util/List;", "SYSTEM_PROPERTY_PREFIX", "SECURE_RESEED_PERIOD", "I", "SECURE_NONCE_COUNT", "SECURE_RESEED_BYTES", "INSECURE_NONCE_COUNT_FACTOR", "Lkotlinx/coroutines/channels/Channel;", "nonceChannel", "Lkotlinx/coroutines/channels/Channel;", "getNonceChannel", "()Lkotlinx/coroutines/channels/Channel;", "Lkotlinx/coroutines/CoroutineName;", "NonceGeneratorCoroutineName", "Lkotlinx/coroutines/CoroutineName;", "Lkotlinx/coroutines/Job;", "nonceGeneratorJob", "Lkotlinx/coroutines/Job;", "getNonceGeneratorJob$annotations", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class NonceKt {
    private static final CoroutineName NonceGeneratorCoroutineName;
    private static final String SHA1PRNG = "SHA1PRNG";
    private static final String SYSTEM_PROPERTY_PREFIX = "io.ktor.random.secure";
    private static final Job nonceGeneratorJob;
    private static final Logger logger = LoggerFactory.getLogger("io.ktor.util.random");
    private static final List<String> SECURE_RANDOM_PROVIDERS = kotlin.collections.CollectionsKt.listOf((Object[]) new String[]{"NativePRNGNonBlocking", "WINDOWS-PRNG", "DRBG"});
    private static final int SECURE_RESEED_PERIOD = getSystemPropertyInt("reseed-period", 30000);
    private static final int SECURE_NONCE_COUNT = getSystemPropertyInt("nonce.buffer-size", 64);
    private static final int SECURE_RESEED_BYTES = getSystemPropertyInt("reseed-bytes", 256);
    private static final int INSECURE_NONCE_COUNT_FACTOR = getSystemPropertyInt("insecure-factor", 4);
    private static final Channel<String> nonceChannel = ChannelKt.Channel$default(getSystemPropertyInt("nonce.channel-size", 128), null, null, 6, null);

    private static /* synthetic */ void getNonceGeneratorJob$annotations() {
    }

    static {
        CoroutineName coroutineName = new CoroutineName("nonce-generator");
        NonceGeneratorCoroutineName = coroutineName;
        nonceGeneratorJob = BuildersKt.launch(GlobalScope.INSTANCE, Dispatchers.getDefault().plus(NonCancellable.INSTANCE).plus(coroutineName), CoroutineStart.LAZY, new NonceKt$nonceGeneratorJob$1(null));
    }

    public static final Channel<String> getNonceChannel() {
        return nonceChannel;
    }

    public static final void ensureNonceGeneratorRunning() {
        nonceGeneratorJob.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SecureRandom lookupSecureRandom() {
        SecureRandom instanceOrNull;
        String property = System.getProperty("io.ktor.random.secure.random.provider");
        if (property != null && (instanceOrNull = getInstanceOrNull(property)) != null) {
            return instanceOrNull;
        }
        Iterator<String> it = SECURE_RANDOM_PROVIDERS.iterator();
        while (it.hasNext()) {
            SecureRandom instanceOrNull2 = getInstanceOrNull(it.next());
            if (instanceOrNull2 != null) {
                return instanceOrNull2;
            }
        }
        logger.warn("None of the " + kotlin.collections.CollectionsKt.joinToString$default(SECURE_RANDOM_PROVIDERS, null, null, null, 0, null, null, 63, null) + " found, falling back to the JDK strong default");
        try {
            SecureRandom instanceStrong = SecureRandom.getInstanceStrong();
            Intrinsics.checkNotNullExpressionValue(instanceStrong, "getInstanceStrong(...)");
            return instanceStrong;
        } catch (NoSuchAlgorithmException unused) {
            logger.warn("None of the JDK determined strong SecureRandom providers were available, falling back to the default");
            SecureRandom instanceOrNull$default = getInstanceOrNull$default(null, 1, null);
            if (instanceOrNull$default != null) {
                return instanceOrNull$default;
            }
            throw new IllegalStateException("No SecureRandom implementation found".toString());
        }
    }

    static /* synthetic */ SecureRandom getInstanceOrNull$default(String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return getInstanceOrNull(str);
    }

    private static final SecureRandom getInstanceOrNull(String str) {
        try {
            if (str != null) {
                return SecureRandom.getInstance(str);
            }
            return new SecureRandom();
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    private static final int getSystemPropertyInt(String str, int i) {
        String property = System.getProperty("io.ktor.random.secure." + str, null);
        if (property != null) {
            try {
                return Integer.parseInt(property);
            } catch (NumberFormatException unused) {
                logger.warn("Invalid integer '" + property + "' for property io.ktor.random.secure." + str + ", falling back to default");
            }
        }
        return i;
    }
}
