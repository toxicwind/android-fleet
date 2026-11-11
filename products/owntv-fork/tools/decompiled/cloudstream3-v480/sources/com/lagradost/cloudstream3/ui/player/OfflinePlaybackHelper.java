package com.lagradost.cloudstream3.ui.player;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavOptions;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.safefile.SafeFile;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: OfflinePlaybackHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/OfflinePlaybackHelper;", "", "<init>", "()V", "replacePlayerNavOptions", "Landroidx/navigation/NavOptions;", "playLink", "", "activity", "Landroid/app/Activity;", ImagesContract.URL, "", "playIntent", "", "intent", "Landroid/content/Intent;", "playUri", "uri", "Landroid/net/Uri;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class OfflinePlaybackHelper {
    public static final OfflinePlaybackHelper INSTANCE = new OfflinePlaybackHelper();
    private static final NavOptions replacePlayerNavOptions = new NavOptions.Builder().setPopUpTo(R.id.navigation_player, true, false).build();

    private OfflinePlaybackHelper() {
    }

    public final void playLink(Activity activity, String url) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(url, "url");
        UIHelper.INSTANCE.navigate(activity, R.id.global_to_navigation_player, GeneratorPlayer.Companion.newInstance$default(GeneratorPlayer.INSTANCE, new LinkGenerator(CollectionsKt.listOf(new BasicLink(url, null, 2, null)), false, null, Integer.valueOf(url.hashCode()), 6, null), 0, null, 4, null), replacePlayerNavOptions);
    }

    /* JADX WARN: Code duplicated, block: B:109:0x011f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:111:0x007a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:25:0x0075  */
    /* JADX WARN: Code duplicated, block: B:32:0x008b A[Catch: Exception -> 0x0029, TRY_LEAVE, TryCatch #4 {Exception -> 0x0029, blocks: (B:16:0x0044, B:23:0x006f, B:26:0x0076, B:32:0x008b, B:31:0x0086, B:22:0x0065, B:15:0x003a, B:19:0x004b, B:12:0x002c, B:28:0x007a), top: B:101:0x0044, inners: #2, #6, #8, #9 }] */
    /* JADX WARN: Code duplicated, block: B:62:0x011a  */
    /* JADX WARN: Code duplicated, block: B:69:0x0130 A[Catch: Exception -> 0x00ce, TRY_LEAVE, TryCatch #1 {Exception -> 0x00ce, blocks: (B:53:0x00e9, B:60:0x0114, B:63:0x011b, B:69:0x0130, B:68:0x012b, B:59:0x010a, B:52:0x00df, B:56:0x00f0, B:49:0x00d1, B:65:0x011f), top: B:97:0x00e9, inners: #5, #9, #10 }] */
    public final boolean playIntent(Activity activity, Intent intent) {
        ArrayList arrayListEmptyList;
        ArrayList arrayListEmptyList2;
        long j;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        Object objDecodeFromString;
        Object objM8936constructorimpl2;
        KSerializer kSerializer2;
        Object objDecodeFromString2;
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (intent == null) {
            return false;
        }
        String[] stringArrayExtra = intent.getStringArrayExtra(CloudStreamPackage.LINKS_EXTRA);
        if (stringArrayExtra == null) {
            arrayListEmptyList = CollectionsKt.emptyList();
        } else {
            ArrayList arrayList = new ArrayList();
            for (String str : stringArrayExtra) {
                AppUtils appUtils = AppUtils.INSTANCE;
                if (str == null) {
                    objDecodeFromString2 = null;
                } else {
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl2 = Result.m8936constructorimpl(CloudStreamPackage.MinimalVideoLink.INSTANCE.serializer());
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    try {
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl2) == null) {
                            if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                                objM8936constructorimpl2 = null;
                            }
                            kSerializer2 = (KSerializer) objM8936constructorimpl2;
                            if (kSerializer2 != null) {
                                try {
                                    objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer2, str);
                                } catch (SerializationException e) {
                                    ArchComponentExtKt.logError(e);
                                    objDecodeFromString2 = MainAPIKt.getMapper().readValue(str, new TypeReference<CloudStreamPackage.MinimalVideoLink>() { // from class: com.lagradost.cloudstream3.ui.player.OfflinePlaybackHelper$playIntent$lambda$0$$inlined$tryParseJson$1
                                    });
                                } catch (Throwable unused) {
                                    objDecodeFromString2 = MainAPIKt.getMapper().readValue(str, new TypeReference<CloudStreamPackage.MinimalVideoLink>() { // from class: com.lagradost.cloudstream3.ui.player.OfflinePlaybackHelper$playIntent$lambda$0$$inlined$tryParseJson$1
                                    });
                                }
                            } else {
                                objDecodeFromString2 = MainAPIKt.getMapper().readValue(str, new TypeReference<CloudStreamPackage.MinimalVideoLink>() { // from class: com.lagradost.cloudstream3.ui.player.OfflinePlaybackHelper$playIntent$lambda$0$$inlined$tryParseJson$1
                                });
                            }
                        } else {
                            try {
                                Result.Companion companion3 = Result.INSTANCE;
                                objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(CloudStreamPackage.MinimalVideoLink.class), null, 2, null));
                            } catch (Throwable th2) {
                                Result.Companion companion4 = Result.INSTANCE;
                                objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                            }
                            if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                                objM8936constructorimpl2 = null;
                            }
                            kSerializer2 = (KSerializer) objM8936constructorimpl2;
                            if (kSerializer2 != null) {
                                objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer2, str);
                            } else {
                                objDecodeFromString2 = MainAPIKt.getMapper().readValue(str, new TypeReference<CloudStreamPackage.MinimalVideoLink>() { // from class: com.lagradost.cloudstream3.ui.player.OfflinePlaybackHelper$playIntent$lambda$0$$inlined$tryParseJson$1
                                });
                            }
                        }
                    } catch (Exception unused2) {
                        objDecodeFromString2 = null;
                    }
                }
                CloudStreamPackage.MinimalVideoLink minimalVideoLink = (CloudStreamPackage.MinimalVideoLink) objDecodeFromString2;
                if (minimalVideoLink != null) {
                    arrayList.add(minimalVideoLink);
                }
            }
            arrayListEmptyList = arrayList;
        }
        if (arrayListEmptyList.isEmpty()) {
            return false;
        }
        String[] stringArrayExtra2 = intent.getStringArrayExtra(CloudStreamPackage.SUBTITLE_EXTRA);
        if (stringArrayExtra2 == null) {
            arrayListEmptyList2 = CollectionsKt.emptyList();
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (String str2 : stringArrayExtra2) {
                AppUtils appUtils2 = AppUtils.INSTANCE;
                if (str2 == null) {
                    objDecodeFromString = null;
                } else {
                    try {
                        Result.Companion companion5 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(CloudStreamPackage.MinimalSubtitleLink.INSTANCE.serializer());
                    } catch (Throwable th3) {
                        Result.Companion companion6 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                    }
                    try {
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                            if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                objM8936constructorimpl = null;
                            }
                            kSerializer = (KSerializer) objM8936constructorimpl;
                            if (kSerializer != null) {
                                try {
                                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, str2);
                                } catch (SerializationException e2) {
                                    ArchComponentExtKt.logError(e2);
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<CloudStreamPackage.MinimalSubtitleLink>() { // from class: com.lagradost.cloudstream3.ui.player.OfflinePlaybackHelper$playIntent$lambda$1$$inlined$tryParseJson$1
                                    });
                                } catch (Throwable unused3) {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<CloudStreamPackage.MinimalSubtitleLink>() { // from class: com.lagradost.cloudstream3.ui.player.OfflinePlaybackHelper$playIntent$lambda$1$$inlined$tryParseJson$1
                                    });
                                }
                            } else {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<CloudStreamPackage.MinimalSubtitleLink>() { // from class: com.lagradost.cloudstream3.ui.player.OfflinePlaybackHelper$playIntent$lambda$1$$inlined$tryParseJson$1
                                });
                            }
                        } else {
                            try {
                                Result.Companion companion7 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(CloudStreamPackage.MinimalSubtitleLink.class), null, 2, null));
                            } catch (Throwable th4) {
                                Result.Companion companion8 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th4));
                            }
                            if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                objM8936constructorimpl = null;
                            }
                            kSerializer = (KSerializer) objM8936constructorimpl;
                            if (kSerializer != null) {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, str2);
                            } else {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<CloudStreamPackage.MinimalSubtitleLink>() { // from class: com.lagradost.cloudstream3.ui.player.OfflinePlaybackHelper$playIntent$lambda$1$$inlined$tryParseJson$1
                                });
                            }
                        }
                    } catch (Exception unused4) {
                        objDecodeFromString = null;
                    }
                }
                CloudStreamPackage.MinimalSubtitleLink minimalSubtitleLink = (CloudStreamPackage.MinimalSubtitleLink) objDecodeFromString;
                if (minimalSubtitleLink != null) {
                    arrayList2.add(minimalSubtitleLink);
                }
            }
            arrayListEmptyList2 = arrayList2;
        }
        int intExtra = intent.getIntExtra("id", -1);
        long longExtra = intent.getLongExtra(CloudStreamPackage.POSITION_EXTRA, -1L);
        long longExtra2 = intent.getLongExtra(CloudStreamPackage.DURATION_EXTRA, -1L);
        if (intExtra != -1 && longExtra != -1) {
            if (longExtra2 == -1) {
                DataStoreHelper.PosDur viewPos = DataStoreHelper.INSTANCE.getViewPos(Integer.valueOf(intExtra));
                if (viewPos != null) {
                    longExtra2 = viewPos.getDuration();
                    j = longExtra2;
                } else {
                    j = longExtra;
                }
            } else {
                j = longExtra2;
            }
            DataStoreHelper.INSTANCE.setViewPos(Integer.valueOf(intExtra), longExtra, j);
        }
        UIHelper.INSTANCE.navigate(activity, R.id.global_to_navigation_player, GeneratorPlayer.Companion.newInstance$default(GeneratorPlayer.INSTANCE, new MinimalLinkGenerator(arrayListEmptyList, arrayListEmptyList2, intExtra != -1 ? Integer.valueOf(intExtra) : null), 0, null, 4, null), replacePlayerNavOptions);
        return true;
    }

    public final void playUri(Activity activity, Uri uri) {
        String string;
        int iHashCode;
        Long longOrNull;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(uri, "uri");
        if (Intrinsics.areEqual(uri.getScheme(), "magnet")) {
            String string2 = uri.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            playLink(activity, string2);
            return;
        }
        Activity activity2 = activity;
        SafeFile safeFileFromUri = SafeFile.INSTANCE.fromUri(activity2, uri);
        Integer numValueOf = null;
        String strName = safeFileFromUri != null ? safeFileFromUri.name() : null;
        UIHelper uIHelper = UIHelper.INSTANCE;
        int i = R.id.global_to_navigation_player;
        GeneratorPlayer.Companion companion = GeneratorPlayer.INSTANCE;
        String str = strName;
        if (str == null) {
            string = ContextCompat.getString(activity2, R.string.downloaded_file);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        } else {
            string = str;
        }
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment == null || (longOrNull = StringsKt.toLongOrNull(lastPathSegment)) == null) {
            String lastPathSegment2 = uri.getLastPathSegment();
            if (lastPathSegment2 != null) {
                iHashCode = lastPathSegment2.hashCode();
            }
            uIHelper.navigate(activity, i, GeneratorPlayer.Companion.newInstance$default(companion, new DownloadFileGenerator(CollectionsKt.listOf(new ExtractorUri(uri, string, null, null, null, numValueOf, null, null, null, null, null, 2012, null))), 0, null, 4, null), replacePlayerNavOptions);
        }
        iHashCode = NextAiring$$ExternalSyntheticBackport0.m(longOrNull.longValue());
        numValueOf = Integer.valueOf(iHashCode);
        uIHelper.navigate(activity, i, GeneratorPlayer.Companion.newInstance$default(companion, new DownloadFileGenerator(CollectionsKt.listOf(new ExtractorUri(uri, string, null, null, null, numValueOf, null, null, null, null, null, 2012, null))), 0, null, 4, null), replacePlayerNavOptions);
    }
}
