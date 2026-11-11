package com.lagradost.cloudstream3.utils;

import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import java.io.Reader;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: AppUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0001J\f\u0010\u0006\u001a\u00020\u0005*\u00020\u0001H\u0007J-\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00012\u0006\u0010\t\u001a\u00020\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0007¢\u0006\u0002\u0010\fJ\"\u0010\u0007\u001a\u0002H\b\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\u00012\u0006\u0010\t\u001a\u00020\u0005H\u0086\b¢\u0006\u0002\u0010\rJ,\u0010\u0007\u001a\u0002H\b\"\u0006\b\u0000\u0010\b\u0018\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\b0\u0011H\u0087\b¢\u0006\u0002\u0010\u0012J\"\u0010\u0013\u001a\u0004\u0018\u0001H\b\"\u0006\b\u0000\u0010\b\u0018\u00012\b\u0010\t\u001a\u0004\u0018\u00010\u0005H\u0086\b¢\u0006\u0002\u0010\r¨\u0006\u0014"}, d2 = {"Lcom/lagradost/cloudstream3/utils/AppUtils;", "", "<init>", "()V", "toJson", "", "toJsonLiteral", "parseJson", ExifInterface.GPS_DIRECTION_TRUE, ES6Iterator.VALUE_PROPERTY, "kClass", "Lkotlin/reflect/KClass;", "(Ljava/lang/String;Lkotlin/reflect/KClass;)Ljava/lang/Object;", "(Ljava/lang/String;)Ljava/lang/Object;", "reader", "Ljava/io/Reader;", "valueType", "Ljava/lang/Class;", "(Ljava/io/Reader;Ljava/lang/Class;)Ljava/lang/Object;", "tryParseJson", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AppUtils {
    public static final AppUtils INSTANCE = new AppUtils();

    private AppUtils() {
    }

    public final String toJson(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        return obj instanceof String ? (String) obj : toJsonLiteral(obj);
    }

    public final String toJsonLiteral(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        KSerializer kSerializerSerializerOrNull = SerializersKt.serializerOrNull(Reflection.getOrCreateKotlinClass(obj.getClass()));
        if (kSerializerSerializerOrNull == null) {
            kSerializerSerializerOrNull = SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(obj.getClass()), null, 2, null);
        }
        if (kSerializerSerializerOrNull != null) {
            try {
                return MainAPIKt.getJson().encodeToString(kSerializerSerializerOrNull, obj);
            } catch (SerializationException e) {
                ArchComponentExtKt.logError(e);
                String strWriteValueAsString = MainAPIKt.getMapper().writeValueAsString(obj);
                Intrinsics.checkNotNull(strWriteValueAsString);
                return strWriteValueAsString;
            }
        }
        String strWriteValueAsString2 = MainAPIKt.getMapper().writeValueAsString(obj);
        Intrinsics.checkNotNull(strWriteValueAsString2);
        return strWriteValueAsString2;
    }

    public final <T> T parseJson(String value, KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        KSerializer kSerializerSerializerOrNull = SerializersKt.serializerOrNull(kClass);
        if (kSerializerSerializerOrNull == null) {
            kSerializerSerializerOrNull = SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), kClass, null, 2, null);
        }
        if (kSerializerSerializerOrNull != null) {
            try {
                return (T) MainAPIKt.getJson().decodeFromString(kSerializerSerializerOrNull, value);
            } catch (SerializationException e) {
                ArchComponentExtKt.logError(e);
            }
        }
        T t = (T) MainAPIKt.getMapper().readValue(value, JvmClassMappingKt.getJavaClass((KClass) kClass));
        Intrinsics.checkNotNullExpressionValue(t, "readValue(...)");
        return t;
    }

    public final /* synthetic */ <T> T parseJson(String value) {
        Object objM8936constructorimpl;
        Intrinsics.checkNotNullParameter(value, "value");
        try {
            Result.Companion companion = Result.INSTANCE;
            AppUtils appUtils = this;
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
        KSerializer kSerializer = (KSerializer) (Result.m8942isFailureimpl(objM8936constructorimpl) ? null : objM8936constructorimpl);
        if (kSerializer != null) {
            try {
                return (T) MainAPIKt.getJson().decodeFromString(kSerializer, value);
            } catch (SerializationException e) {
                ArchComponentExtKt.logError(e);
            } catch (Throwable unused) {
            }
        }
        JsonMapper mapper = MainAPIKt.getMapper();
        Intrinsics.needClassReification();
        return (T) mapper.readValue(value, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.utils.AppUtils$parseJson$$inlined$readValue$1
        });
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This overload was only ever used for BasePlugin.Manifest which has since been migrated. No other code should be using this. Use reader.readText() and call parseJson(String) instead.", replaceWith = @ReplaceWith(expression = "parseJson<T>(reader.readText())", imports = {}))
    public final /* synthetic */ <T> T parseJson(Reader reader, Class<T> valueType) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(valueType, "valueType");
        return (T) MainAPIKt.getMapper().readValue(reader, valueType);
    }

    public final /* synthetic */ <T> T tryParseJson(String value) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        try {
            if (value == null) {
                return null;
            }
            try {
                Result.Companion companion = Result.INSTANCE;
                AppUtils appUtils = this;
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
                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, value);
                } catch (SerializationException e) {
                    ArchComponentExtKt.logError(e);
                    JsonMapper mapper = MainAPIKt.getMapper();
                    Intrinsics.needClassReification();
                    objDecodeFromString = mapper.readValue(value, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.utils.AppUtils$tryParseJson$$inlined$parseJson$1
                    });
                } catch (Throwable unused) {
                    JsonMapper mapper2 = MainAPIKt.getMapper();
                    Intrinsics.needClassReification();
                    objDecodeFromString = mapper2.readValue(value, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.utils.AppUtils$tryParseJson$$inlined$parseJson$1
                    });
                }
            } else {
                JsonMapper mapper3 = MainAPIKt.getMapper();
                Intrinsics.needClassReification();
                objDecodeFromString = mapper3.readValue(value, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.utils.AppUtils$tryParseJson$$inlined$parseJson$1
                });
            }
            return (T) objDecodeFromString;
        } catch (Exception unused2) {
            return null;
        }
    }
}
