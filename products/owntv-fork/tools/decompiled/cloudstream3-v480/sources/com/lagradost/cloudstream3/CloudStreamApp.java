package com.lagradost.cloudstream3;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.media3.extractor.text.ttml.TtmlNode;
import coil3.ImageLoader;
import coil3.SingletonImageLoader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.api.ContextHelper_androidKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.AppDebug;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStore;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: CloudStreamApp.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u00012\u00020\u0002:\u0001\u000eB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u0014\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\tj\u0002`\rH\u0016¨\u0006\u000f"}, d2 = {"Lcom/lagradost/cloudstream3/CloudStreamApp;", "Landroid/app/Application;", "Lcoil3/SingletonImageLoader$Factory;", "<init>", "()V", "onCreate", "", "attachBaseContext", TtmlNode.RUBY_BASE, "Landroid/content/Context;", "newImageLoader", "Lcoil3/ImageLoader;", "context", "Lcoil3/PlatformContext;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CloudStreamApp extends Application implements SingletonImageLoader.Factory {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static WeakReference<Context> _context;
    private static ExceptionHandler exceptionHandler;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        File filesDir = getFilesDir();
        Intrinsics.checkNotNullExpressionValue(filesDir, "getFilesDir(...)");
        ExceptionHandler exceptionHandler2 = new ExceptionHandler(FilesKt.resolve(filesDir, "last_error"), new Function0() { // from class: com.lagradost.cloudstream3.CloudStreamApp$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CloudStreamApp.onCreate$lambda$0(this.f$0);
            }
        });
        exceptionHandler = exceptionHandler2;
        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler2);
        AppDebug.INSTANCE.setDebug(false);
    }

    static final Unit onCreate$lambda$0(CloudStreamApp cloudStreamApp) {
        Companion companion = INSTANCE;
        Context context = companion.getContext();
        Intrinsics.checkNotNull(context);
        PackageManager packageManager = context.getPackageManager();
        Context context2 = companion.getContext();
        Intrinsics.checkNotNull(context2);
        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(context2.getPackageName());
        Intrinsics.checkNotNull(launchIntentForPackage);
        cloudStreamApp.startActivity(Intent.makeRestartActivityTask(launchIntentForPackage.getComponent()));
        return Unit.INSTANCE;
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        INSTANCE.setContext(base);
    }

    @Override // coil3.SingletonImageLoader.Factory
    public ImageLoader newImageLoader(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        com.lagradost.cloudstream3.utils.ImageLoader imageLoader = com.lagradost.cloudstream3.utils.ImageLoader.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return imageLoader.buildImageLoader$app(applicationContext);
    }

    /* JADX INFO: compiled from: CloudStreamApp.kt */
    @Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\fH\u0086\u0010J-\u0010\u0015\u001a\u0004\u0018\u0001H\u0016\"\b\b\u0000\u0010\u0016*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00160\u001a¢\u0006\u0002\u0010\u001bJ%\u0010\u001c\u001a\u00020\u001d\"\b\b\u0000\u0010\u0016*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u0002H\u0016¢\u0006\u0002\u0010\u001eJ\u0015\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\u0018¢\u0006\u0002\u0010\"J!\u0010#\u001a\u00020\u001d\"\u0004\b\u0000\u0010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u0002H\u0016¢\u0006\u0002\u0010\u001eJ)\u0010#\u001a\u00020\u001d\"\u0004\b\u0000\u0010\u00162\u0006\u0010!\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u0002H\u0016¢\u0006\u0002\u0010$J.\u0010%\u001a\u0004\u0018\u0001H\u0016\"\n\b\u0000\u0010\u0016\u0018\u0001*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010&\u001a\u0004\u0018\u0001H\u0016H\u0086\b¢\u0006\u0002\u0010'J$\u0010%\u001a\u0004\u0018\u0001H\u0016\"\n\b\u0000\u0010\u0016\u0018\u0001*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0018H\u0086\b¢\u0006\u0002\u0010(J,\u0010%\u001a\u0004\u0018\u0001H\u0016\"\n\b\u0000\u0010\u0016\u0018\u0001*\u00020\u00012\u0006\u0010!\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0018H\u0086\b¢\u0006\u0002\u0010)J6\u0010%\u001a\u0004\u0018\u0001H\u0016\"\n\b\u0000\u0010\u0016\u0018\u0001*\u00020\u00012\u0006\u0010!\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010&\u001a\u0004\u0018\u0001H\u0016H\u0086\b¢\u0006\u0002\u0010*J\u0016\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010,2\u0006\u0010!\u001a\u00020\u0018J\u0016\u0010-\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010-\u001a\u00020\u001d2\u0006\u0010\u0017\u001a\u00020\u0018J$\u0010.\u001a\u00020\u001d2\u0006\u0010/\u001a\u00020\u00182\b\b\u0002\u00100\u001a\u0002012\n\b\u0002\u00102\u001a\u0004\u0018\u000103J\u0018\u0010.\u001a\u00020\u001d2\u0006\u0010/\u001a\u00020\u00182\b\u00104\u001a\u0004\u0018\u000105R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u0010\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\f8F@BX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u00066"}, d2 = {"Lcom/lagradost/cloudstream3/CloudStreamApp$Companion;", "", "<init>", "()V", "exceptionHandler", "Lcom/lagradost/cloudstream3/ExceptionHandler;", "getExceptionHandler", "()Lcom/lagradost/cloudstream3/ExceptionHandler;", "setExceptionHandler", "(Lcom/lagradost/cloudstream3/ExceptionHandler;)V", "getActivity", "Landroid/app/Activity;", "Landroid/content/Context;", "_context", "Ljava/lang/ref/WeakReference;", ES6Iterator.VALUE_PROPERTY, "context", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getKeyClass", ExifInterface.GPS_DIRECTION_TRUE, "path", "", "valueType", "Ljava/lang/Class;", "(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", "setKeyClass", "", "(Ljava/lang/String;Ljava/lang/Object;)V", "removeKeys", "", "folder", "(Ljava/lang/String;)Ljava/lang/Integer;", "setKey", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "getKey", "defVal", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/String;)Ljava/lang/Object;", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "getKeys", "", "removeKey", "openBrowser", ImagesContract.URL, "fallbackWebView", "", "fragment", "Landroidx/fragment/app/Fragment;", "activity", "Landroidx/fragment/app/FragmentActivity;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ExceptionHandler getExceptionHandler() {
            return CloudStreamApp.exceptionHandler;
        }

        public final void setExceptionHandler(ExceptionHandler exceptionHandler) {
            CloudStreamApp.exceptionHandler = exceptionHandler;
        }

        public final Activity getActivity(Context context) {
            Intrinsics.checkNotNullParameter(context, "<this>");
            while (!(context instanceof Activity)) {
                if (!(context instanceof ContextWrapper)) {
                    return null;
                }
                context = ((ContextWrapper) context).getBaseContext();
                Intrinsics.checkNotNullExpressionValue(context, "getBaseContext(...)");
            }
            return (Activity) context;
        }

        public final Context getContext() {
            WeakReference weakReference = CloudStreamApp._context;
            if (weakReference != null) {
                return (Context) weakReference.get();
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setContext(Context context) {
            CloudStreamApp._context = new WeakReference(context);
            ContextHelper_androidKt.setContext(new WeakReference(context));
        }

        public final <T> T getKeyClass(String path, Class<T> valueType) {
            Intrinsics.checkNotNullParameter(path, "path");
            Intrinsics.checkNotNullParameter(valueType, "valueType");
            Context context = getContext();
            if (context != null) {
                return (T) DataStore.INSTANCE.getKey(context, path, (Class) valueType);
            }
            return null;
        }

        public final <T> void setKeyClass(String path, T value) {
            Intrinsics.checkNotNullParameter(path, "path");
            Intrinsics.checkNotNullParameter(value, "value");
            Context context = getContext();
            if (context != null) {
                DataStore.INSTANCE.setKey(context, path, value);
            }
        }

        public final Integer removeKeys(String folder) {
            Intrinsics.checkNotNullParameter(folder, "folder");
            Context context = getContext();
            if (context != null) {
                return Integer.valueOf(DataStore.INSTANCE.removeKeys(context, folder));
            }
            return null;
        }

        public final <T> void setKey(String path, T value) {
            Intrinsics.checkNotNullParameter(path, "path");
            Context context = getContext();
            if (context != null) {
                DataStore.INSTANCE.setKey(context, path, value);
            }
        }

        public final <T> void setKey(String folder, String path, T value) {
            Intrinsics.checkNotNullParameter(folder, "folder");
            Intrinsics.checkNotNullParameter(path, "path");
            Context context = getContext();
            if (context != null) {
                DataStore.INSTANCE.setKey(context, folder, path, value);
            }
        }

        public final /* synthetic */ <T> T getKey(String path, T defVal) {
            Object objM8936constructorimpl;
            Object objDecodeFromString;
            Intrinsics.checkNotNullParameter(path, "path");
            Context context = getContext();
            if (context == null) {
                return null;
            }
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString(path, null);
                if (string == null) {
                    return defVal;
                }
                AppUtils appUtils = AppUtils.INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
                    MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.serializer((KType) null));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
                Throwable thM8939exceptionOrNullimpl = Result.m8939exceptionOrNullimpl(objM8936constructorimpl);
                if (thM8939exceptionOrNullimpl != null) {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        Throwable th2 = thM8939exceptionOrNullimpl;
                        SerializersModule serializersModule = MainAPIKt.getJson().getSerializersModule();
                        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(serializersModule, Reflection.getOrCreateKotlinClass(Object.class), null, 2, null));
                    } catch (Throwable th3) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                    }
                }
                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                    objM8936constructorimpl = null;
                }
                KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
                if (kSerializer != null) {
                    try {
                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                    } catch (SerializationException e) {
                        ArchComponentExtKt.logError(e);
                        JsonMapper mapper = MainAPIKt.getMapper();
                        Intrinsics.needClassReification();
                        objDecodeFromString = mapper.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.CloudStreamApp$Companion$getKey$$inlined$getKey$1
                        });
                    } catch (Throwable unused) {
                        JsonMapper mapper2 = MainAPIKt.getMapper();
                        Intrinsics.needClassReification();
                        objDecodeFromString = mapper2.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.CloudStreamApp$Companion$getKey$$inlined$getKey$1
                        });
                    }
                } else {
                    JsonMapper mapper3 = MainAPIKt.getMapper();
                    Intrinsics.needClassReification();
                    objDecodeFromString = mapper3.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.CloudStreamApp$Companion$getKey$$inlined$getKey$1
                    });
                }
                return (T) objDecodeFromString;
            } catch (Exception unused2) {
                return null;
            }
        }

        public final /* synthetic */ <T> T getKey(String path) {
            Object objM8936constructorimpl;
            Object objDecodeFromString;
            Intrinsics.checkNotNullParameter(path, "path");
            Context context = getContext();
            if (context == null) {
                return null;
            }
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString(path, null);
                if (string == null) {
                    return null;
                }
                AppUtils appUtils = AppUtils.INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
                    MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.serializer((KType) null));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
                Throwable thM8939exceptionOrNullimpl = Result.m8939exceptionOrNullimpl(objM8936constructorimpl);
                if (thM8939exceptionOrNullimpl != null) {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        Throwable th2 = thM8939exceptionOrNullimpl;
                        SerializersModule serializersModule = MainAPIKt.getJson().getSerializersModule();
                        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(serializersModule, Reflection.getOrCreateKotlinClass(Object.class), null, 2, null));
                    } catch (Throwable th3) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                    }
                }
                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                    objM8936constructorimpl = null;
                }
                KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
                if (kSerializer != null) {
                    try {
                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                    } catch (SerializationException e) {
                        ArchComponentExtKt.logError(e);
                        JsonMapper mapper = MainAPIKt.getMapper();
                        Intrinsics.needClassReification();
                        objDecodeFromString = mapper.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.CloudStreamApp$Companion$getKey$$inlined$getKey$2
                        });
                    } catch (Throwable unused) {
                        JsonMapper mapper2 = MainAPIKt.getMapper();
                        Intrinsics.needClassReification();
                        objDecodeFromString = mapper2.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.CloudStreamApp$Companion$getKey$$inlined$getKey$2
                        });
                    }
                } else {
                    JsonMapper mapper3 = MainAPIKt.getMapper();
                    Intrinsics.needClassReification();
                    objDecodeFromString = mapper3.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.CloudStreamApp$Companion$getKey$$inlined$getKey$2
                    });
                }
                return (T) objDecodeFromString;
            } catch (Exception unused2) {
                return null;
            }
        }

        public final /* synthetic */ <T> T getKey(String folder, String path) {
            Object objM8936constructorimpl;
            Object objDecodeFromString;
            Intrinsics.checkNotNullParameter(folder, "folder");
            Intrinsics.checkNotNullParameter(path, "path");
            Context context = getContext();
            if (context == null) {
                return null;
            }
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(folder, path), null);
                if (string == null) {
                    return null;
                }
                AppUtils appUtils = AppUtils.INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
                    MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.serializer((KType) null));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
                Throwable thM8939exceptionOrNullimpl = Result.m8939exceptionOrNullimpl(objM8936constructorimpl);
                if (thM8939exceptionOrNullimpl != null) {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        Throwable th2 = thM8939exceptionOrNullimpl;
                        SerializersModule serializersModule = MainAPIKt.getJson().getSerializersModule();
                        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(serializersModule, Reflection.getOrCreateKotlinClass(Object.class), null, 2, null));
                    } catch (Throwable th3) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                    }
                }
                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                    objM8936constructorimpl = null;
                }
                KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
                if (kSerializer != null) {
                    try {
                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                    } catch (SerializationException e) {
                        ArchComponentExtKt.logError(e);
                        JsonMapper mapper = MainAPIKt.getMapper();
                        Intrinsics.needClassReification();
                        objDecodeFromString = mapper.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.CloudStreamApp$Companion$getKey$$inlined$getKey$3
                        });
                    } catch (Throwable unused) {
                        JsonMapper mapper2 = MainAPIKt.getMapper();
                        Intrinsics.needClassReification();
                        objDecodeFromString = mapper2.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.CloudStreamApp$Companion$getKey$$inlined$getKey$3
                        });
                    }
                } else {
                    JsonMapper mapper3 = MainAPIKt.getMapper();
                    Intrinsics.needClassReification();
                    objDecodeFromString = mapper3.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.CloudStreamApp$Companion$getKey$$inlined$getKey$3
                    });
                }
                return (T) objDecodeFromString;
            } catch (Exception unused2) {
                return null;
            }
        }

        public final /* synthetic */ <T> T getKey(String folder, String path, T defVal) {
            Object objM8936constructorimpl;
            Object objDecodeFromString;
            Intrinsics.checkNotNullParameter(folder, "folder");
            Intrinsics.checkNotNullParameter(path, "path");
            Context context = getContext();
            Object obj = null;
            if (context == null) {
                return null;
            }
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(folder, path), null);
                if (string == null) {
                    obj = defVal;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
                        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.serializer((KType) null));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    Throwable thM8939exceptionOrNullimpl = Result.m8939exceptionOrNullimpl(objM8936constructorimpl);
                    if (thM8939exceptionOrNullimpl != null) {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            Throwable th2 = thM8939exceptionOrNullimpl;
                            SerializersModule serializersModule = MainAPIKt.getJson().getSerializersModule();
                            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(serializersModule, Reflection.getOrCreateKotlinClass(Object.class), null, 2, null));
                        } catch (Throwable th3) {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                        }
                    }
                    if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                        objM8936constructorimpl = null;
                    }
                    KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
                    if (kSerializer != null) {
                        try {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                        } catch (SerializationException e) {
                            ArchComponentExtKt.logError(e);
                            JsonMapper mapper = MainAPIKt.getMapper();
                            Intrinsics.needClassReification();
                            objDecodeFromString = mapper.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.CloudStreamApp$Companion$getKey$$inlined$getKey$4
                            });
                        } catch (Throwable unused) {
                            JsonMapper mapper2 = MainAPIKt.getMapper();
                            Intrinsics.needClassReification();
                            objDecodeFromString = mapper2.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.CloudStreamApp$Companion$getKey$$inlined$getKey$4
                            });
                        }
                    } else {
                        JsonMapper mapper3 = MainAPIKt.getMapper();
                        Intrinsics.needClassReification();
                        objDecodeFromString = mapper3.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.CloudStreamApp$Companion$getKey$$inlined$getKey$4
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
            return obj == null ? defVal : (T) obj;
        }

        public final List<String> getKeys(String folder) {
            Intrinsics.checkNotNullParameter(folder, "folder");
            Context context = getContext();
            if (context != null) {
                return DataStore.INSTANCE.getKeys(context, folder);
            }
            return null;
        }

        public final void removeKey(String folder, String path) {
            Intrinsics.checkNotNullParameter(folder, "folder");
            Intrinsics.checkNotNullParameter(path, "path");
            Context context = getContext();
            if (context != null) {
                DataStore.INSTANCE.removeKey(context, folder, path);
            }
        }

        public final void removeKey(String path) {
            Intrinsics.checkNotNullParameter(path, "path");
            Context context = getContext();
            if (context != null) {
                DataStore.INSTANCE.removeKey(context, path);
            }
        }

        public static /* synthetic */ void openBrowser$default(Companion companion, String str, boolean z, Fragment fragment, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            if ((i & 4) != 0) {
                fragment = null;
            }
            companion.openBrowser(str, z, fragment);
        }

        public final void openBrowser(String url, boolean fallbackWebView, Fragment fragment) {
            Intrinsics.checkNotNullParameter(url, "url");
            Context context = getContext();
            if (context != null) {
                AppContextUtils.INSTANCE.openBrowser(context, url, fallbackWebView, fragment);
            }
        }

        public final void openBrowser(String url, FragmentActivity activity) {
            FragmentManager supportFragmentManager;
            List<Fragment> fragments;
            Intrinsics.checkNotNullParameter(url, "url");
            openBrowser(url, Globals.INSTANCE.isLayout(6), (activity == null || (supportFragmentManager = activity.getSupportFragmentManager()) == null || (fragments = supportFragmentManager.getFragments()) == null) ? null : (Fragment) CollectionsKt.lastOrNull((List) fragments));
        }
    }
}
