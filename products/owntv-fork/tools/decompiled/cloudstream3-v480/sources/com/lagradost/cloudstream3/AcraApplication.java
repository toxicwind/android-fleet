package com.lagradost.cloudstream3;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStore;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
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

/* JADX INFO: compiled from: AcraApplication.kt */
/* JADX INFO: loaded from: classes6.dex */
@Deprecated(level = DeprecationLevel.ERROR, message = "AcraApplication is deprecated, use CloudStreamApp instead", replaceWith = @ReplaceWith(expression = "com.lagradost.cloudstream3.CloudStreamApp", imports = {}))
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/lagradost/cloudstream3/AcraApplication;", "", "<init>", "()V", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AcraApplication {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: AcraApplication.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\rJ#\u0010\u000e\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u00102\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u0002H\u0010H\u0007¢\u0006\u0002\u0010\u0013J+\u0010\u000e\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u0002H\u0010H\u0007¢\u0006\u0002\u0010\u0014J.\u0010\u0015\u001a\u0004\u0018\u0001H\u0010\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\u00012\u0006\u0010\u0011\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u0001H\u0010H\u0087\b¢\u0006\u0002\u0010\u0017J$\u0010\u0015\u001a\u0004\u0018\u0001H\u0010\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\u00012\u0006\u0010\u0011\u001a\u00020\fH\u0087\b¢\u0006\u0002\u0010\u0018J,\u0010\u0015\u001a\u0004\u0018\u0001H\u0010\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\fH\u0087\b¢\u0006\u0002\u0010\u0019J6\u0010\u0015\u001a\u0004\u0018\u0001H\u0010\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u0001H\u0010H\u0087\b¢\u0006\u0002\u0010\u001aR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\b¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/AcraApplication$Companion;", "", "<init>", "()V", "context", "Landroid/content/Context;", "getContext$annotations", "getContext", "()Landroid/content/Context;", "removeKeys", "", "folder", "", "(Ljava/lang/String;)Ljava/lang/Integer;", "setKey", "", ExifInterface.GPS_DIRECTION_TRUE, "path", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/String;Ljava/lang/Object;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "getKey", "defVal", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/String;)Ljava/lang/Object;", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "AcraApplication is deprecated, use CloudStreamApp instead", replaceWith = @ReplaceWith(expression = "com.lagradost.cloudstream3.CloudStreamApp.context", imports = {}))
        public static /* synthetic */ void getContext$annotations() {
        }

        private Companion() {
        }

        public final Context getContext() {
            return CloudStreamApp.INSTANCE.getContext();
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "AcraApplication is deprecated, use CloudStreamApp instead", replaceWith = @ReplaceWith(expression = "com.lagradost.cloudstream3.CloudStreamApp.removeKeys(folder)", imports = {}))
        public final Integer removeKeys(String folder) {
            Intrinsics.checkNotNullParameter(folder, "folder");
            return CloudStreamApp.INSTANCE.removeKeys(folder);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "AcraApplication is deprecated, use CloudStreamApp instead", replaceWith = @ReplaceWith(expression = "com.lagradost.cloudstream3.CloudStreamApp.setKey(path, value)", imports = {}))
        public final <T> void setKey(String path, T value) {
            Intrinsics.checkNotNullParameter(path, "path");
            CloudStreamApp.INSTANCE.setKey(path, value);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "AcraApplication is deprecated, use CloudStreamApp instead", replaceWith = @ReplaceWith(expression = "com.lagradost.cloudstream3.CloudStreamApp.setKey(folder, path, value)", imports = {}))
        public final <T> void setKey(String folder, String path, T value) {
            Intrinsics.checkNotNullParameter(folder, "folder");
            Intrinsics.checkNotNullParameter(path, "path");
            CloudStreamApp.INSTANCE.setKey(folder, path, value);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "AcraApplication is deprecated, use CloudStreamApp instead", replaceWith = @ReplaceWith(expression = "com.lagradost.cloudstream3.CloudStreamApp.getKey(path, defVal)", imports = {}))
        public final /* synthetic */ <T> T getKey(String path, T defVal) {
            Object objM8936constructorimpl;
            Object objM8936constructorimpl2;
            Object objDecodeFromString;
            Intrinsics.checkNotNullParameter(path, "path");
            Context context = CloudStreamApp.INSTANCE.getContext();
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
                        objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(serializersModule, Reflection.getOrCreateKotlinClass(Object.class), null, 2, null));
                    } catch (Throwable th3) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                    }
                    objM8936constructorimpl = objM8936constructorimpl2;
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
                        objDecodeFromString = mapper.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.AcraApplication$Companion$getKey$$inlined$getKey$1
                        });
                    } catch (Throwable unused) {
                        JsonMapper mapper2 = MainAPIKt.getMapper();
                        Intrinsics.needClassReification();
                        objDecodeFromString = mapper2.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.AcraApplication$Companion$getKey$$inlined$getKey$1
                        });
                    }
                } else {
                    JsonMapper mapper3 = MainAPIKt.getMapper();
                    Intrinsics.needClassReification();
                    objDecodeFromString = mapper3.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.AcraApplication$Companion$getKey$$inlined$getKey$1
                    });
                }
                return (T) objDecodeFromString;
            } catch (Exception unused2) {
                return null;
            }
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "AcraApplication is deprecated, use CloudStreamApp instead", replaceWith = @ReplaceWith(expression = "com.lagradost.cloudstream3.CloudStreamApp.getKey(path)", imports = {}))
        public final /* synthetic */ <T> T getKey(String path) {
            Object objM8936constructorimpl;
            Object objM8936constructorimpl2;
            Object objDecodeFromString;
            Intrinsics.checkNotNullParameter(path, "path");
            Context context = CloudStreamApp.INSTANCE.getContext();
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
                        objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(serializersModule, Reflection.getOrCreateKotlinClass(Object.class), null, 2, null));
                    } catch (Throwable th3) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                    }
                    objM8936constructorimpl = objM8936constructorimpl2;
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
                        objDecodeFromString = mapper.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.AcraApplication$Companion$getKey$$inlined$getKey$2
                        });
                    } catch (Throwable unused) {
                        JsonMapper mapper2 = MainAPIKt.getMapper();
                        Intrinsics.needClassReification();
                        objDecodeFromString = mapper2.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.AcraApplication$Companion$getKey$$inlined$getKey$2
                        });
                    }
                } else {
                    JsonMapper mapper3 = MainAPIKt.getMapper();
                    Intrinsics.needClassReification();
                    objDecodeFromString = mapper3.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.AcraApplication$Companion$getKey$$inlined$getKey$2
                    });
                }
                return (T) objDecodeFromString;
            } catch (Exception unused2) {
                return null;
            }
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "AcraApplication is deprecated, use CloudStreamApp instead", replaceWith = @ReplaceWith(expression = "com.lagradost.cloudstream3.CloudStreamApp.getKey(folder, path)", imports = {}))
        public final /* synthetic */ <T> T getKey(String folder, String path) {
            Object objM8936constructorimpl;
            Object objM8936constructorimpl2;
            Object objDecodeFromString;
            Intrinsics.checkNotNullParameter(folder, "folder");
            Intrinsics.checkNotNullParameter(path, "path");
            Context context = CloudStreamApp.INSTANCE.getContext();
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
                        objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(serializersModule, Reflection.getOrCreateKotlinClass(Object.class), null, 2, null));
                    } catch (Throwable th3) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                    }
                    objM8936constructorimpl = objM8936constructorimpl2;
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
                        objDecodeFromString = mapper.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.AcraApplication$Companion$getKey$$inlined$getKey$3
                        });
                    } catch (Throwable unused) {
                        JsonMapper mapper2 = MainAPIKt.getMapper();
                        Intrinsics.needClassReification();
                        objDecodeFromString = mapper2.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.AcraApplication$Companion$getKey$$inlined$getKey$3
                        });
                    }
                } else {
                    JsonMapper mapper3 = MainAPIKt.getMapper();
                    Intrinsics.needClassReification();
                    objDecodeFromString = mapper3.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.AcraApplication$Companion$getKey$$inlined$getKey$3
                    });
                }
                return (T) objDecodeFromString;
            } catch (Exception unused2) {
                return null;
            }
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "AcraApplication is deprecated, use CloudStreamApp instead", replaceWith = @ReplaceWith(expression = "com.lagradost.cloudstream3.CloudStreamApp.getKey(folder, path, defVal)", imports = {}))
        public final /* synthetic */ <T> T getKey(String folder, String path, T defVal) {
            Object objM8936constructorimpl;
            Object objM8936constructorimpl2;
            Object objDecodeFromString;
            Intrinsics.checkNotNullParameter(folder, "folder");
            Intrinsics.checkNotNullParameter(path, "path");
            Context context = CloudStreamApp.INSTANCE.getContext();
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
                            objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(serializersModule, Reflection.getOrCreateKotlinClass(Object.class), null, 2, null));
                        } catch (Throwable th3) {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                        }
                        objM8936constructorimpl = objM8936constructorimpl2;
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
                            objDecodeFromString = mapper.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.AcraApplication$Companion$getKey$$inlined$getKey$4
                            });
                        } catch (Throwable unused) {
                            JsonMapper mapper2 = MainAPIKt.getMapper();
                            Intrinsics.needClassReification();
                            objDecodeFromString = mapper2.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.AcraApplication$Companion$getKey$$inlined$getKey$4
                            });
                        }
                    } else {
                        JsonMapper mapper3 = MainAPIKt.getMapper();
                        Intrinsics.needClassReification();
                        objDecodeFromString = mapper3.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.AcraApplication$Companion$getKey$$inlined$getKey$4
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
            return obj == null ? defVal : (T) obj;
        }
    }
}
