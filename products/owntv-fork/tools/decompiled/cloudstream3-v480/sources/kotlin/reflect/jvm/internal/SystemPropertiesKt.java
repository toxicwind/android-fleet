package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: compiled from: SystemProperties.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0014\u0010\u0006\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0003¨\u0006\b"}, d2 = {"useK1Implementation", "", "getUseK1Implementation", "()Z", "newFakeOverridesImplementation", "getNewFakeOverridesImplementation", "loadMetadataDirectly", "getLoadMetadataDirectly", "kotlin-reflection"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SystemPropertiesKt {
    private static final boolean loadMetadataDirectly;
    private static final boolean newFakeOverridesImplementation;
    private static final boolean useK1Implementation;

    static {
        Object objM8936constructorimpl;
        Object objM8936constructorimpl2;
        Object objM8936constructorimpl3;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(System.getProperty("kotlin.reflect.jvm.useK1Implementation"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
            objM8936constructorimpl = null;
        }
        String str = (String) objM8936constructorimpl;
        useK1Implementation = str != null && Boolean.parseBoolean(str);
        try {
            Result.Companion companion3 = Result.INSTANCE;
            objM8936constructorimpl2 = Result.m8936constructorimpl(System.getProperty("kotlin.reflect.jvm.newFakeOverridesImplementation"));
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
            objM8936constructorimpl2 = null;
        }
        String str2 = (String) objM8936constructorimpl2;
        newFakeOverridesImplementation = str2 != null && Boolean.parseBoolean(str2);
        try {
            Result.Companion companion5 = Result.INSTANCE;
            objM8936constructorimpl3 = Result.m8936constructorimpl(System.getProperty("kotlin.reflect.jvm.loadMetadataDirectly"));
        } catch (Throwable th3) {
            Result.Companion companion6 = Result.INSTANCE;
            objM8936constructorimpl3 = Result.m8936constructorimpl(ResultKt.createFailure(th3));
        }
        String str3 = (String) (Result.m8942isFailureimpl(objM8936constructorimpl3) ? null : objM8936constructorimpl3);
        loadMetadataDirectly = str3 != null && Boolean.parseBoolean(str3);
    }

    public static final boolean getUseK1Implementation() {
        return useK1Implementation;
    }

    public static final boolean getNewFakeOverridesImplementation() {
        return newFakeOverridesImplementation;
    }

    public static final boolean getLoadMetadataDirectly() {
        return loadMetadataDirectly;
    }
}
