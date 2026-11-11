package com.lagradost.cloudstream3.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.exifinterface.media.ExifInterface;
import androidx.preference.PreferenceManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
import kotlin.reflect.KType;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DataStore.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\n\u0010\r\u001a\u00020\n*\u00020\fJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fJ\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\u0015J\n\u0010\u0016\u001a\u00020\n*\u00020\fJ\u0018\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0018*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000fJ\u001a\u0010\u0019\u001a\u00020\u001a*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fJ\u001a\u0010\u001b\u001a\u00020\u0015*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fJ\u0012\u0010\u001b\u001a\u00020\u0015*\u00020\f2\u0006\u0010\u0011\u001a\u00020\u000fJ\u0012\u0010\u0019\u001a\u00020\u001a*\u00020\f2\u0006\u0010\u0011\u001a\u00020\u000fJ\u0012\u0010\u001c\u001a\u00020\u001d*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000fJ%\u0010\u001e\u001a\u00020\u001a\"\u0004\b\u0000\u0010\u001f*\u00020\f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010 \u001a\u0002H\u001f¢\u0006\u0002\u0010!J1\u0010\"\u001a\u0004\u0018\u0001H\u001f\"\b\b\u0000\u0010\u001f*\u00020\u0001*\u00020\f2\u0006\u0010\u0011\u001a\u00020\u000f2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u001f0$¢\u0006\u0002\u0010%J-\u0010\u001e\u001a\u00020\u001a\"\u0004\b\u0000\u0010\u001f*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010 \u001a\u0002H\u001f¢\u0006\u0002\u0010&J\u001e\u0010'\u001a\u0002H\u001f\"\n\b\u0000\u0010\u001f\u0018\u0001*\u00020\u0001*\u00020\u000fH\u0086\b¢\u0006\u0002\u0010(J'\u0010'\u001a\u0002H\u001f\"\b\b\u0000\u0010\u001f*\u00020\u0001*\u00020\u000f2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u001f0$¢\u0006\u0002\u0010)J2\u0010\"\u001a\u0004\u0018\u0001H\u001f\"\n\b\u0000\u0010\u001f\u0018\u0001*\u00020\u0001*\u00020\f2\u0006\u0010\u0011\u001a\u00020\u000f2\b\u0010*\u001a\u0004\u0018\u0001H\u001fH\u0086\b¢\u0006\u0002\u0010+J(\u0010\"\u001a\u0004\u0018\u0001H\u001f\"\n\b\u0000\u0010\u001f\u0018\u0001*\u00020\u0001*\u00020\f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0086\b¢\u0006\u0002\u0010,J0\u0010\"\u001a\u0004\u0018\u0001H\u001f\"\n\b\u0000\u0010\u001f\u0018\u0001*\u00020\u0001*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0086\b¢\u0006\u0002\u0010-J:\u0010\"\u001a\u0004\u0018\u0001H\u001f\"\n\b\u0000\u0010\u001f\u0018\u0001*\u00020\u0001*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\b\u0010*\u001a\u0004\u0018\u0001H\u001fH\u0086\b¢\u0006\u0002\u0010.R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\b¨\u0006/"}, d2 = {"Lcom/lagradost/cloudstream3/utils/DataStore;", "", "<init>", "()V", "mapper", "Lcom/fasterxml/jackson/databind/json/JsonMapper;", "getMapper$annotations", "getMapper", "()Lcom/fasterxml/jackson/databind/json/JsonMapper;", "getPreferences", "Landroid/content/SharedPreferences;", "context", "Landroid/content/Context;", "getSharedPrefs", "getFolderName", "", "folder", "path", "editor", "Lcom/lagradost/cloudstream3/utils/Editor;", "isEditingAppSettings", "", "getDefaultSharedPrefs", "getKeys", "", "removeKey", "", "containsKey", "removeKeys", "", "setKey", ExifInterface.GPS_DIRECTION_TRUE, ES6Iterator.VALUE_PROPERTY, "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Object;)V", "getKey", "valueType", "Ljava/lang/Class;", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "toKotlinObject", "(Ljava/lang/String;)Ljava/lang/Object;", "(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", "defVal", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/Object;", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DataStore {
    public static final DataStore INSTANCE = new DataStore();
    private static final JsonMapper mapper = MainAPIKt.getMapper();

    @Deprecated(level = DeprecationLevel.ERROR, message = "Please do not use the mapper version from DataStore. Preferably use methods from AppUtils to parse JSON. However, you can use the stable-API version of the mapper at com.lagradost.cloudstream3.mapper to access the mapper directly if necessary.", replaceWith = @ReplaceWith(expression = "com.lagradost.cloudstream3.mapper", imports = {}))
    public static /* synthetic */ void getMapper$annotations() {
    }

    private DataStore() {
    }

    public final JsonMapper getMapper() {
        return mapper;
    }

    private final SharedPreferences getPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DataStoreKt.PREFERENCES_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        return sharedPreferences;
    }

    public final SharedPreferences getSharedPrefs(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return getPreferences(context);
    }

    public final String getFolderName(String folder, String path) {
        Intrinsics.checkNotNullParameter(folder, "folder");
        Intrinsics.checkNotNullParameter(path, "path");
        return folder + '/' + path;
    }

    public static /* synthetic */ Editor editor$default(DataStore dataStore, Context context, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return dataStore.editor(context, z);
    }

    public final Editor editor(Context context, boolean isEditingAppSettings) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences.Editor editorEdit = (isEditingAppSettings ? getDefaultSharedPrefs(context) : getSharedPrefs(context)).edit();
        Intrinsics.checkNotNullExpressionValue(editorEdit, "edit(...)");
        return new Editor(editorEdit);
    }

    public final SharedPreferences getDefaultSharedPrefs(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "getDefaultSharedPreferences(...)");
        return defaultSharedPreferences;
    }

    public final List<String> getKeys(Context context, String folder) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(folder, "folder");
        String str = StringsKt.trimEnd(folder, '/') + '/';
        Set<String> setKeySet = getSharedPrefs(context).getAll().keySet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : setKeySet) {
            String str2 = (String) obj;
            Intrinsics.checkNotNull(str2);
            if (StringsKt.startsWith$default(str2, str, false, 2, (Object) null)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final void removeKey(Context context, String folder, String path) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(folder, "folder");
        Intrinsics.checkNotNullParameter(path, "path");
        removeKey(context, getFolderName(folder, path));
    }

    public final boolean containsKey(Context context, String folder, String path) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(folder, "folder");
        Intrinsics.checkNotNullParameter(path, "path");
        return containsKey(context, getFolderName(folder, path));
    }

    public final boolean containsKey(Context context, String path) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        return getSharedPrefs(context).contains(path);
    }

    public final void removeKey(Context context, String path) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        try {
            SharedPreferences sharedPrefs = getSharedPrefs(context);
            if (sharedPrefs.contains(path)) {
                SharedPreferences.Editor editorEdit = sharedPrefs.edit();
                editorEdit.remove(path);
                editorEdit.apply();
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
    }

    public final int removeKeys(Context context, String folder) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(folder, "folder");
        List<String> keys = getKeys(context, folder + '/');
        try {
            SharedPreferences.Editor editorEdit = getSharedPrefs(context).edit();
            Iterator<T> it = keys.iterator();
            while (it.hasNext()) {
                editorEdit.remove((String) it.next());
            }
            editorEdit.apply();
            return keys.size();
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
            return 0;
        }
    }

    public final <T> void setKey(Context context, String path, T t) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        try {
            SharedPreferences.Editor editorEdit = getSharedPrefs(context).edit();
            editorEdit.putString(path, t != null ? AppUtils.INSTANCE.toJsonLiteral(t) : null);
            editorEdit.apply();
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
    }

    public final <T> T getKey(Context context, String path, Class<T> valueType) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(valueType, "valueType");
        try {
            String string = getSharedPrefs(context).getString(path, null);
            if (string == null) {
                return null;
            }
            return (T) AppUtils.INSTANCE.parseJson(string, JvmClassMappingKt.getKotlinClass(valueType));
        } catch (Exception unused) {
            return null;
        }
    }

    public final <T> void setKey(Context context, String folder, String path, T t) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(folder, "folder");
        Intrinsics.checkNotNullParameter(path, "path");
        setKey(context, getFolderName(folder, path), t);
    }

    public final /* synthetic */ <T> T toKotlinObject(String str) {
        Object objM8936constructorimpl;
        Object objM8936constructorimpl2;
        Intrinsics.checkNotNullParameter(str, "<this>");
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
        KSerializer kSerializer = (KSerializer) (Result.m8942isFailureimpl(objM8936constructorimpl) ? null : objM8936constructorimpl);
        if (kSerializer != null) {
            try {
                return (T) MainAPIKt.getJson().decodeFromString(kSerializer, str);
            } catch (SerializationException e) {
                ArchComponentExtKt.logError(e);
            } catch (Throwable unused) {
            }
        }
        JsonMapper mapper2 = MainAPIKt.getMapper();
        Intrinsics.needClassReification();
        return (T) mapper2.readValue(str, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.utils.DataStore$toKotlinObject$$inlined$parseJson$1
        });
    }

    public final <T> T toKotlinObject(String str, Class<T> valueType) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(valueType, "valueType");
        return (T) AppUtils.INSTANCE.parseJson(str, JvmClassMappingKt.getKotlinClass(valueType));
    }

    public final /* synthetic */ <T> T getKey(Context context, String path, T t) {
        Object objM8936constructorimpl;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        try {
            String string = getSharedPrefs(context).getString(path, null);
            if (string == null) {
                return t;
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
                    return (T) MainAPIKt.getJson().decodeFromString(kSerializer, string);
                } catch (SerializationException e) {
                    ArchComponentExtKt.logError(e);
                } catch (Throwable unused) {
                }
            }
            JsonMapper mapper2 = MainAPIKt.getMapper();
            Intrinsics.needClassReification();
            return (T) mapper2.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.utils.DataStore$getKey$$inlined$toKotlinObject$1
            });
        } catch (Exception unused2) {
            return null;
        }
    }

    public final /* synthetic */ <T> T getKey(Context context, String folder, String path) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(folder, "folder");
        Intrinsics.checkNotNullParameter(path, "path");
        try {
            String string = getSharedPrefs(context).getString(getFolderName(folder, path), null);
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
                    JsonMapper mapper2 = MainAPIKt.getMapper();
                    Intrinsics.needClassReification();
                    objDecodeFromString = mapper2.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.utils.DataStore$getKey$$inlined$getKey$2
                    });
                } catch (Throwable unused) {
                    JsonMapper mapper3 = MainAPIKt.getMapper();
                    Intrinsics.needClassReification();
                    objDecodeFromString = mapper3.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.utils.DataStore$getKey$$inlined$getKey$2
                    });
                }
            } else {
                JsonMapper mapper4 = MainAPIKt.getMapper();
                Intrinsics.needClassReification();
                objDecodeFromString = mapper4.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.utils.DataStore$getKey$$inlined$getKey$2
                });
            }
            return (T) objDecodeFromString;
        } catch (Exception unused2) {
            return null;
        }
    }

    public final /* synthetic */ <T> T getKey(Context context, String folder, String path, T t) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(folder, "folder");
        Intrinsics.checkNotNullParameter(path, "path");
        String folderName = getFolderName(folder, path);
        Object obj = null;
        try {
            String string = getSharedPrefs(context).getString(folderName, null);
            if (string == null) {
                obj = t;
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
                        JsonMapper mapper2 = MainAPIKt.getMapper();
                        Intrinsics.needClassReification();
                        objDecodeFromString = mapper2.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.utils.DataStore$getKey$$inlined$getKey$3
                        });
                    } catch (Throwable unused) {
                        JsonMapper mapper3 = MainAPIKt.getMapper();
                        Intrinsics.needClassReification();
                        objDecodeFromString = mapper3.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.utils.DataStore$getKey$$inlined$getKey$3
                        });
                    }
                } else {
                    JsonMapper mapper4 = MainAPIKt.getMapper();
                    Intrinsics.needClassReification();
                    objDecodeFromString = mapper4.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.utils.DataStore$getKey$$inlined$getKey$3
                    });
                }
                obj = objDecodeFromString;
            }
        } catch (Exception unused2) {
        }
        return obj == null ? t : (T) obj;
    }

    public final /* synthetic */ <T> T getKey(Context context, String path) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        try {
            String string = getSharedPrefs(context).getString(path, null);
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
                    JsonMapper mapper2 = MainAPIKt.getMapper();
                    Intrinsics.needClassReification();
                    objDecodeFromString = mapper2.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.utils.DataStore$getKey$$inlined$getKey$1
                    });
                } catch (Throwable unused) {
                    JsonMapper mapper3 = MainAPIKt.getMapper();
                    Intrinsics.needClassReification();
                    objDecodeFromString = mapper3.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.utils.DataStore$getKey$$inlined$getKey$1
                    });
                }
            } else {
                JsonMapper mapper4 = MainAPIKt.getMapper();
                Intrinsics.needClassReification();
                objDecodeFromString = mapper4.readValue(string, new TypeReference<T>() { // from class: com.lagradost.cloudstream3.utils.DataStore$getKey$$inlined$getKey$1
                });
            }
            return (T) objDecodeFromString;
        } catch (Exception unused2) {
            return null;
        }
    }
}
