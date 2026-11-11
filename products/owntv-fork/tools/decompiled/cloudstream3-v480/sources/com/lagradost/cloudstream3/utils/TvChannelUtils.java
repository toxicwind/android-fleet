package com.lagradost.cloudstream3.utils;

import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import androidx.tvprovider.media.tv.Channel;
import androidx.tvprovider.media.tv.PreviewProgram;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: TvChannelUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n*\u00020\u0006J\u0012\u0010\u000b\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001d\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J&\u0010\u0011\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\nH\u0007J\u000e\u0010\u0015\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0006J\u000e\u0010\u0016\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0006¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/utils/TvChannelUtils;", "", "<init>", "()V", "saveProgramId", "", "Landroid/content/Context;", "programId", "", "getStoredProgramIds", "", "removeProgramId", "getChannelId", "context", "channelName", "", "(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/Long;", "addPrograms", "channelId", "items", "Lcom/lagradost/cloudstream3/SearchResponse;", "deleteStoredPrograms", "createTvChannel", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TvChannelUtils {
    public static final TvChannelUtils INSTANCE = new TvChannelUtils();

    private TvChannelUtils() {
    }

    public final void saveProgramId(Context context, long j) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object obj = null;
        try {
            String string = DataStore.INSTANCE.getSharedPrefs(context).getString(TvChannelUtilsKt.PROGRAM_ID_LIST_KEY, null);
            if (string != null) {
                AppUtils appUtils = AppUtils.INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(new ArrayListSerializer(LongSerializer.INSTANCE));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(List.class), null, 2, null));
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
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
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<List<? extends Long>>() { // from class: com.lagradost.cloudstream3.utils.TvChannelUtils$saveProgramId$$inlined$getKey$1
                        });
                    } catch (Throwable unused) {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<List<? extends Long>>() { // from class: com.lagradost.cloudstream3.utils.TvChannelUtils$saveProgramId$$inlined$getKey$1
                        });
                    }
                } else {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<List<? extends Long>>() { // from class: com.lagradost.cloudstream3.utils.TvChannelUtils$saveProgramId$$inlined$getKey$1
                    });
                }
                obj = objDecodeFromString;
            }
        } catch (Exception unused2) {
        }
        List listEmptyList = (List) obj;
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        DataStore.INSTANCE.setKey(context, TvChannelUtilsKt.PROGRAM_ID_LIST_KEY, CollectionsKt.distinct(CollectionsKt.plus((Collection<? extends Long>) listEmptyList, Long.valueOf(j))));
    }

    public final List<Long> getStoredProgramIds(Context context) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object obj = null;
        try {
            String string = DataStore.INSTANCE.getSharedPrefs(context).getString(TvChannelUtilsKt.PROGRAM_ID_LIST_KEY, null);
            if (string != null) {
                AppUtils appUtils = AppUtils.INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(new ArrayListSerializer(LongSerializer.INSTANCE));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(List.class), null, 2, null));
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
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
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<List<? extends Long>>() { // from class: com.lagradost.cloudstream3.utils.TvChannelUtils$getStoredProgramIds$$inlined$getKey$1
                        });
                    } catch (Throwable unused) {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<List<? extends Long>>() { // from class: com.lagradost.cloudstream3.utils.TvChannelUtils$getStoredProgramIds$$inlined$getKey$1
                        });
                    }
                } else {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<List<? extends Long>>() { // from class: com.lagradost.cloudstream3.utils.TvChannelUtils$getStoredProgramIds$$inlined$getKey$1
                    });
                }
                obj = objDecodeFromString;
            }
        } catch (Exception unused2) {
        }
        List<Long> list = (List) obj;
        return list == null ? CollectionsKt.emptyList() : list;
    }

    public final void removeProgramId(Context context, long j) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object obj = null;
        try {
            String string = DataStore.INSTANCE.getSharedPrefs(context).getString(TvChannelUtilsKt.PROGRAM_ID_LIST_KEY, null);
            if (string != null) {
                AppUtils appUtils = AppUtils.INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(new ArrayListSerializer(LongSerializer.INSTANCE));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(List.class), null, 2, null));
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
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
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<List<? extends Long>>() { // from class: com.lagradost.cloudstream3.utils.TvChannelUtils$removeProgramId$$inlined$getKey$1
                        });
                    } catch (Throwable unused) {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<List<? extends Long>>() { // from class: com.lagradost.cloudstream3.utils.TvChannelUtils$removeProgramId$$inlined$getKey$1
                        });
                    }
                } else {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<List<? extends Long>>() { // from class: com.lagradost.cloudstream3.utils.TvChannelUtils$removeProgramId$$inlined$getKey$1
                    });
                }
                obj = objDecodeFromString;
            }
        } catch (Exception unused2) {
        }
        List listEmptyList = (List) obj;
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : listEmptyList) {
            if (((Number) obj2).longValue() != j) {
                arrayList.add(obj2);
            }
        }
        DataStore.INSTANCE.setKey(context, TvChannelUtilsKt.PROGRAM_ID_LIST_KEY, arrayList);
    }

    public final Long getChannelId(Context context, String channelName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(channelName, "channelName");
        try {
            Cursor cursorQuery = context.getContentResolver().query(TvContractCompat.Channels.CONTENT_URI, new String[]{"_id", "display_name"}, null, null, null);
            if (cursorQuery == null) {
                return null;
            }
            Cursor cursor = cursorQuery;
            try {
                Cursor cursor2 = cursor;
                while (cursor2.moveToNext()) {
                    long j = cursor2.getLong(cursor2.getColumnIndexOrThrow("_id"));
                    if (Intrinsics.areEqual(cursor2.getString(cursor2.getColumnIndexOrThrow("display_name")), channelName)) {
                        Long lValueOf = Long.valueOf(j);
                        CloseableKt.closeFinally(cursor, null);
                        return lValueOf;
                    }
                    Log.e("TvChannelUtils", "Query failed: " + e.getMessage(), e);
                    return null;
                }
                CloseableKt.closeFinally(cursor, null);
                return null;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursor, th);
                    throw th2;
                }
            }
        } catch (Exception e) {
            Log.e("TvChannelUtils", "Query failed: " + e.getMessage(), e);
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:47:0x015e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:48:0x0133 A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r0v27 */
    /* JADX WARN: Type inference failed for: r0v28 */
    public final void addPrograms(Context context, long channelId, List<? extends SearchResponse> items) {
        String str;
        boolean z;
        String str2;
        ?? r0;
        PreviewProgram.Builder posterArtAspectRatio;
        String str3;
        Uri uriInsert;
        boolean z2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(items, "items");
        for (SearchResponse searchResponse : items) {
            try {
                byte[] bytes = searchResponse.getApiName().getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                String strBase64Encode = MainAPIKt.base64Encode(bytes);
                byte[] bytes2 = searchResponse.getUrl().getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
                String str4 = "csshare:" + strBase64Encode + '?' + MainAPIKt.base64Encode(bytes2);
                String posterUrl = searchResponse.getPosterUrl();
                try {
                    T title = new PreviewProgram.Builder().setChannelId(channelId).setTitle(searchResponse.getName());
                    PreviewProgram.Builder builder = (PreviewProgram.Builder) title;
                    Score score = searchResponse.getScore();
                    if (score != null) {
                        str = str4;
                        z2 = false;
                        String stringNull$default = Score.toStringNull$default(score, 0.1d, 10, 1, false, (char) 0, 24, null);
                        if (stringNull$default != null) {
                            str2 = " - " + TextUtilKt.txt(R.string.rating_format, stringNull$default).asString(context);
                            if (str2 == null) {
                                z = z2;
                                r0 = z2;
                                z = z2;
                            }
                        }
                        z = z2;
                        r0 = z2;
                        builder.setDescription(searchResponse.getApiName() + str2);
                        posterArtAspectRatio = ((PreviewProgram.Builder) ((PreviewProgram.Builder) title).setContentId(searchResponse.getUrl()).setType(r0)).setIntentUri(Uri.parse(str)).setPosterArtAspectRatio(4);
                        str3 = posterUrl;
                        if (str3 != null && !StringsKt.isBlank(str3) && StringsKt.startsWith$default(posterUrl, "http", (boolean) r0, 2, (Object) null)) {
                            posterArtAspectRatio.setPosterArtUri(Uri.parse(posterUrl));
                        }
                        uriInsert = context.getContentResolver().insert(TvContractCompat.PreviewPrograms.CONTENT_URI, posterArtAspectRatio.build().toContentValues());
                        if (uriInsert != null) {
                            long id = ContentUris.parseId(uriInsert);
                            try {
                                saveProgramId(context, id);
                                Log.d("TvChannelUtils", "Inserted program " + searchResponse.getName() + ", ID=" + id);
                            } catch (Exception e) {
                                e = e;
                                Log.e("TvChannelUtils", "Error inserting " + searchResponse.getName() + ": " + e);
                            }
                        } else {
                            Log.e("TvChannelUtils", "Insert failed for " + searchResponse.getName());
                        }
                    } else {
                        str = str4;
                        z = false;
                    }
                    z = z2;
                    str2 = "";
                    r0 = z;
                    z = z2;
                    r0 = z2;
                    builder.setDescription(searchResponse.getApiName() + str2);
                    posterArtAspectRatio = ((PreviewProgram.Builder) ((PreviewProgram.Builder) title).setContentId(searchResponse.getUrl()).setType(r0)).setIntentUri(Uri.parse(str)).setPosterArtAspectRatio(4);
                    str3 = posterUrl;
                    if (str3 != null) {
                        posterArtAspectRatio.setPosterArtUri(Uri.parse(posterUrl));
                    }
                    uriInsert = context.getContentResolver().insert(TvContractCompat.PreviewPrograms.CONTENT_URI, posterArtAspectRatio.build().toContentValues());
                    if (uriInsert != null) {
                        long id2 = ContentUris.parseId(uriInsert);
                        saveProgramId(context, id2);
                        Log.d("TvChannelUtils", "Inserted program " + searchResponse.getName() + ", ID=" + id2);
                    } else {
                        Log.e("TvChannelUtils", "Insert failed for " + searchResponse.getName());
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
    }

    public final void deleteStoredPrograms(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Iterator<Long> it = getStoredProgramIds(context).iterator();
        while (it.hasNext()) {
            long jLongValue = it.next().longValue();
            Uri uriWithAppendedId = ContentUris.withAppendedId(TvContractCompat.PreviewPrograms.CONTENT_URI, jLongValue);
            Intrinsics.checkNotNullExpressionValue(uriWithAppendedId, "withAppendedId(...)");
            try {
                if (context.getContentResolver().delete(uriWithAppendedId, null, null) > 0) {
                    removeProgramId(context, jLongValue);
                } else {
                    Log.w("ProgramDelete", "No program found for ID: " + jLongValue);
                }
            } catch (Exception e) {
                Log.e("ProgramDelete", "Failed to delete program ID: " + jLongValue, e);
            }
        }
        Log.d("ProgramDelete", "Finished deleting stored programs");
    }

    public final void createTvChannel(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ComponentName componentName = new ComponentName(context, (Class<?>) MainActivity.class);
        Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/mipmap/ic_launcher");
        String strBuildInputId = TvContractCompat.buildInputId(componentName);
        Channel.Builder displayName = new Channel.Builder().setType(TvContractCompat.Channels.TYPE_PREVIEW).setAppLinkIconUri(uri).setDisplayName(context.getString(R.string.app_name));
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("cloudstreamapp://open"));
        Uri uriInsert = context.getContentResolver().insert(TvContractCompat.Channels.CONTENT_URI, displayName.setAppLinkIntent(intent).setInputId(strBuildInputId).build().toContentValues());
        if (uriInsert != null) {
            long id = ContentUris.parseId(uriInsert);
            TvContractCompat.requestChannelBrowsable(context, id);
            Log.d("TvChannelUtils", "Channel Created: " + id);
        }
    }
}
