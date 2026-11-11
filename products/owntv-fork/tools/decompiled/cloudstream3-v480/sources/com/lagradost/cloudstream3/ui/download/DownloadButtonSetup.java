package com.lagradost.cloudstream3.ui.download;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.player.DownloadFileGenerator;
import com.lagradost.cloudstream3.ui.player.ExtractorUri;
import com.lagradost.cloudstream3.ui.player.GeneratorPlayer;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreKt;
import com.lagradost.cloudstream3.utils.SnackbarHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: DownloadButtonSetup.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/DownloadButtonSetup;", "", "<init>", "()V", "handleDownloadClick", "", "click", "Lcom/lagradost/cloudstream3/ui/download/DownloadClickEvent;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DownloadButtonSetup {
    public static final DownloadButtonSetup INSTANCE = new DownloadButtonSetup();

    private DownloadButtonSetup() {
    }

    /* JADX WARN: Code duplicated, block: B:111:0x02b7  */
    /* JADX WARN: Code duplicated, block: B:118:0x02cd A[Catch: Exception -> 0x02df, TRY_LEAVE, TryCatch #7 {Exception -> 0x02df, blocks: (B:94:0x0260, B:97:0x026c, B:102:0x0286, B:109:0x02b1, B:112:0x02b8, B:118:0x02cd, B:117:0x02c8, B:108:0x02a7, B:101:0x027c, B:105:0x028d, B:98:0x026e, B:114:0x02bc), top: B:206:0x0260, inners: #2, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:160:0x03be  */
    /* JADX WARN: Code duplicated, block: B:167:0x03d4 A[Catch: Exception -> 0x03e6, TRY_LEAVE, TryCatch #13 {Exception -> 0x03e6, blocks: (B:143:0x0367, B:146:0x0373, B:151:0x038d, B:158:0x03b8, B:161:0x03bf, B:167:0x03d4, B:166:0x03cf, B:157:0x03ae, B:150:0x0383, B:147:0x0375, B:154:0x0394, B:163:0x03c3), top: B:212:0x0367, inners: #0, #4, #14 }] */
    /* JADX WARN: Code duplicated, block: B:216:0x02bc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:218:0x0208 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:220:0x03c3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x0203  */
    /* JADX WARN: Code duplicated, block: B:80:0x0219 A[Catch: Exception -> 0x022b, TRY_LEAVE, TryCatch #1 {Exception -> 0x022b, blocks: (B:56:0x01ac, B:59:0x01b8, B:64:0x01d2, B:71:0x01fd, B:74:0x0204, B:80:0x0219, B:79:0x0214, B:70:0x01f3, B:63:0x01c8, B:60:0x01ba, B:76:0x0208, B:67:0x01d9), top: B:199:0x01ac, inners: #3, #6, #9, #15 }] */
    public final void handleDownloadClick(DownloadClickEvent click) {
        Object objDecodeFromString;
        ArrayList arrayList;
        List<DownloadObjects.DownloadEpisodeCached> listSortedWith;
        Object objDecodeFromString2;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        Object objDecodeFromString3;
        Object objM8936constructorimpl2;
        KSerializer kSerializer2;
        Object objM8936constructorimpl3;
        KSerializer kSerializer3;
        Intrinsics.checkNotNullParameter(click, "click");
        final int id = click.getData().getId();
        int action = click.getAction();
        int i = 0;
        if (action != 0) {
            if (action == 1) {
                final Activity activity = CommonActivity.INSTANCE.getActivity();
                if (activity != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadButtonSetup$$ExternalSyntheticLambda0
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i2) {
                            DownloadButtonSetup.handleDownloadClick$lambda$0$0(activity, id, dialogInterface, i2);
                        }
                    };
                    try {
                        AppContextUtils appContextUtils = AppContextUtils.INSTANCE;
                        AlertDialog.Builder title = builder.setTitle(R.string.delete_file);
                        String string = activity.getString(R.string.delete_message);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        String str = String.format(string, Arrays.copyOf(new Object[]{AppContextUtils.INSTANCE.getNameFull(activity, click.getData().getName(), Integer.valueOf(click.getData().getEpisode()), click.getData().getSeason())}, 1));
                        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                        AlertDialog alertDialogShow = title.setMessage(str).setPositiveButton(R.string.delete, onClickListener).setNegativeButton(R.string.cancel, onClickListener).show();
                        Intrinsics.checkNotNullExpressionValue(alertDialogShow, "show(...)");
                        AppContextUtils.setDefaultFocus$default(appContextUtils, alertDialogShow, 0, 1, null);
                    } catch (Exception e) {
                        ArchComponentExtKt.logError(e);
                    }
                    Unit unit = Unit.INSTANCE;
                    Unit unit2 = Unit.INSTANCE;
                    return;
                }
                return;
            }
            if (action == 2) {
                Activity activity2 = CommonActivity.INSTANCE.getActivity();
                if (activity2 != null) {
                    if (VideoDownloadManager.INSTANCE.getDownloadStatus().containsKey(Integer.valueOf(id)) && VideoDownloadManager.INSTANCE.getDownloadStatus().get(Integer.valueOf(id)) == VideoDownloadManager.DownloadType.IsPaused) {
                        VideoDownloadManager.INSTANCE.getDownloadEvent().invoke(new Pair<>(Integer.valueOf(click.getData().getId()), VideoDownloadManager.DownloadActionType.Resume));
                        Unit unit3 = Unit.INSTANCE;
                        return;
                    }
                    DownloadObjects.DownloadResumePackage downloadResumePackage = VideoDownloadManager.INSTANCE.getDownloadResumePackage(activity2, id);
                    if (downloadResumePackage != null) {
                        DownloadQueueManager.INSTANCE.addToQueue(downloadResumePackage.toWrapper());
                        return;
                    } else {
                        VideoDownloadManager.INSTANCE.getDownloadEvent().invoke(new Pair<>(Integer.valueOf(click.getData().getId()), VideoDownloadManager.DownloadActionType.Resume));
                        Unit unit4 = Unit.INSTANCE;
                        return;
                    }
                }
                return;
            }
            if (action == 3) {
                VideoDownloadManager.INSTANCE.getDownloadEvent().invoke(new Pair<>(Integer.valueOf(click.getData().getId()), VideoDownloadManager.DownloadActionType.Pause));
                return;
            }
            if (action != 5) {
                if (action != 6) {
                    return;
                }
                DownloadQueueManager.INSTANCE.cancelDownload(id);
                return;
            }
            Activity activity3 = CommonActivity.INSTANCE.getActivity();
            if (activity3 != null) {
                DownloadObjects.DownloadedFileInfoResult downloadFileInfo = VideoDownloadManager.INSTANCE.getDownloadFileInfo(activity3, click.getData().getId());
                if ((downloadFileInfo != null ? downloadFileInfo.getFileLength() : 0L) > 0) {
                    SnackbarHelper.showSnackbar$default(SnackbarHelper.INSTANCE, activity3, R.string.offline_file, 0, (Integer) null, (Function0) null, 24, (Object) null);
                }
                Unit unit5 = Unit.INSTANCE;
                Unit unit6 = Unit.INSTANCE;
                return;
            }
            return;
        }
        Activity activity4 = CommonActivity.INSTANCE.getActivity();
        if (activity4 != null) {
            CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
            String strValueOf = String.valueOf(click.getData().getParentId());
            Context context = companion.getContext();
            if (context != null) {
                DataStore dataStore = DataStore.INSTANCE;
                try {
                    String string2 = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(DataStoreKt.DOWNLOAD_HEADER_CACHE, strValueOf), null);
                    if (string2 == null) {
                        objDecodeFromString = null;
                    } else {
                        AppUtils appUtils = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM8936constructorimpl3 = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadHeaderCached"));
                        } catch (Throwable th) {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl3 = Result.m8936constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl3) == null) {
                            if (Result.m8942isFailureimpl(objM8936constructorimpl3)) {
                                objM8936constructorimpl3 = null;
                            }
                            kSerializer3 = (KSerializer) objM8936constructorimpl3;
                            if (kSerializer3 != null) {
                                try {
                                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer3, string2);
                                } catch (SerializationException e2) {
                                    ArchComponentExtKt.logError(e2);
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadButtonSetup$handleDownloadClick$lambda$3$$inlined$getKey$1
                                    });
                                } catch (Throwable unused) {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadButtonSetup$handleDownloadClick$lambda$3$$inlined$getKey$1
                                    });
                                }
                            } else {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadButtonSetup$handleDownloadClick$lambda$3$$inlined$getKey$1
                                });
                            }
                        } else {
                            try {
                                Result.Companion companion4 = Result.INSTANCE;
                                objM8936constructorimpl3 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadHeaderCached.class), null, 2, null));
                            } catch (Throwable th2) {
                                Result.Companion companion5 = Result.INSTANCE;
                                objM8936constructorimpl3 = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                            }
                            if (Result.m8942isFailureimpl(objM8936constructorimpl3)) {
                                objM8936constructorimpl3 = null;
                            }
                            kSerializer3 = (KSerializer) objM8936constructorimpl3;
                            if (kSerializer3 != null) {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer3, string2);
                            } else {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadButtonSetup$handleDownloadClick$lambda$3$$inlined$getKey$1
                                });
                            }
                        }
                    }
                } catch (Exception unused2) {
                }
            } else {
                objDecodeFromString = null;
            }
            DownloadObjects.DownloadHeaderCached downloadHeaderCached = (DownloadObjects.DownloadHeaderCached) objDecodeFromString;
            if (downloadHeaderCached == null) {
                return;
            }
            List<String> keys = CloudStreamApp.INSTANCE.getKeys(DataStoreKt.DOWNLOAD_EPISODE_CACHE);
            if (keys != null) {
                ArrayList arrayList2 = new ArrayList();
                for (String str2 : keys) {
                    Context context2 = CloudStreamApp.INSTANCE.getContext();
                    if (context2 != null) {
                        try {
                            String string3 = DataStore.INSTANCE.getSharedPrefs(context2).getString(str2, null);
                            if (string3 == null) {
                                objDecodeFromString3 = null;
                            } else {
                                AppUtils appUtils2 = AppUtils.INSTANCE;
                                try {
                                    Result.Companion companion6 = Result.INSTANCE;
                                    objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadEpisodeCached"));
                                } catch (Throwable th3) {
                                    Result.Companion companion7 = Result.INSTANCE;
                                    objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                                }
                                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl2) == null) {
                                    if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                                        objM8936constructorimpl2 = null;
                                    }
                                    kSerializer2 = (KSerializer) objM8936constructorimpl2;
                                    if (kSerializer2 != null) {
                                        try {
                                            objDecodeFromString3 = MainAPIKt.getJson().decodeFromString(kSerializer2, string3);
                                        } catch (SerializationException e3) {
                                            ArchComponentExtKt.logError(e3);
                                            objDecodeFromString3 = MainAPIKt.getMapper().readValue(string3, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadButtonSetup$handleDownloadClick$lambda$3$0$$inlined$getKey$1
                                            });
                                        } catch (Throwable unused3) {
                                            objDecodeFromString3 = MainAPIKt.getMapper().readValue(string3, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadButtonSetup$handleDownloadClick$lambda$3$0$$inlined$getKey$1
                                            });
                                        }
                                    } else {
                                        objDecodeFromString3 = MainAPIKt.getMapper().readValue(string3, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadButtonSetup$handleDownloadClick$lambda$3$0$$inlined$getKey$1
                                        });
                                    }
                                } else {
                                    try {
                                        Result.Companion companion8 = Result.INSTANCE;
                                        objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadEpisodeCached.class), null, 2, null));
                                    } catch (Throwable th4) {
                                        Result.Companion companion9 = Result.INSTANCE;
                                        objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th4));
                                    }
                                    if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                                        objM8936constructorimpl2 = null;
                                    }
                                    kSerializer2 = (KSerializer) objM8936constructorimpl2;
                                    if (kSerializer2 != null) {
                                        objDecodeFromString3 = MainAPIKt.getJson().decodeFromString(kSerializer2, string3);
                                    } else {
                                        objDecodeFromString3 = MainAPIKt.getMapper().readValue(string3, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadButtonSetup$handleDownloadClick$lambda$3$0$$inlined$getKey$1
                                        });
                                    }
                                }
                            }
                        } catch (Exception unused4) {
                            objDecodeFromString3 = null;
                        }
                    } else {
                        objDecodeFromString3 = null;
                    }
                    DownloadObjects.DownloadEpisodeCached downloadEpisodeCached = (DownloadObjects.DownloadEpisodeCached) objDecodeFromString3;
                    if (downloadEpisodeCached != null) {
                        arrayList2.add(downloadEpisodeCached);
                    }
                }
                ArrayList arrayList3 = new ArrayList();
                for (Object obj : arrayList2) {
                    if (((DownloadObjects.DownloadEpisodeCached) obj).getParentId() == click.getData().getParentId()) {
                        arrayList3.add(obj);
                    }
                }
                arrayList = arrayList3;
            } else {
                arrayList = null;
            }
            ArrayList arrayList4 = new ArrayList();
            if (arrayList != null) {
                final Comparator comparator = new Comparator() { // from class: com.lagradost.cloudstream3.ui.download.DownloadButtonSetup$handleDownloadClick$lambda$3$$inlined$compareBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        Integer season = ((DownloadObjects.DownloadEpisodeCached) t).getSeason();
                        Integer num = season != null ? season : (Comparable) 0;
                        Integer season2 = ((DownloadObjects.DownloadEpisodeCached) t2).getSeason();
                        return ComparisonsKt.compareValues(num, season2 != null ? season2 : (Comparable) 0);
                    }
                };
                listSortedWith = CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.lagradost.cloudstream3.ui.download.DownloadButtonSetup$handleDownloadClick$lambda$3$$inlined$thenBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        int iCompare = comparator.compare(t, t2);
                        return iCompare != 0 ? iCompare : ComparisonsKt.compareValues(Integer.valueOf(((DownloadObjects.DownloadEpisodeCached) t).getEpisode()), Integer.valueOf(((DownloadObjects.DownloadEpisodeCached) t2).getEpisode()));
                    }
                });
            } else {
                listSortedWith = null;
            }
            if (listSortedWith != null) {
                for (DownloadObjects.DownloadEpisodeCached downloadEpisodeCached2 : listSortedWith) {
                    CloudStreamApp.Companion companion10 = CloudStreamApp.INSTANCE;
                    String strValueOf2 = String.valueOf(downloadEpisodeCached2.getId());
                    Context context3 = companion10.getContext();
                    if (context3 != null) {
                        DataStore dataStore2 = DataStore.INSTANCE;
                        try {
                            String string4 = dataStore2.getSharedPrefs(context3).getString(dataStore2.getFolderName(VideoDownloadManager.KEY_DOWNLOAD_INFO, strValueOf2), null);
                            if (string4 == null) {
                                objDecodeFromString2 = null;
                            } else {
                                AppUtils appUtils3 = AppUtils.INSTANCE;
                                try {
                                    Result.Companion companion11 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadedFileInfo"));
                                } catch (Throwable th5) {
                                    Result.Companion companion12 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th5));
                                }
                                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                                    if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                        objM8936constructorimpl = null;
                                    }
                                    kSerializer = (KSerializer) objM8936constructorimpl;
                                    if (kSerializer != null) {
                                        try {
                                            objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer, string4);
                                        } catch (SerializationException e4) {
                                            ArchComponentExtKt.logError(e4);
                                            objDecodeFromString2 = MainAPIKt.getMapper().readValue(string4, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadButtonSetup$handleDownloadClick$lambda$3$4$$inlined$getKey$1
                                            });
                                        } catch (Throwable unused5) {
                                            objDecodeFromString2 = MainAPIKt.getMapper().readValue(string4, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadButtonSetup$handleDownloadClick$lambda$3$4$$inlined$getKey$1
                                            });
                                        }
                                    } else {
                                        objDecodeFromString2 = MainAPIKt.getMapper().readValue(string4, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadButtonSetup$handleDownloadClick$lambda$3$4$$inlined$getKey$1
                                        });
                                    }
                                } else {
                                    try {
                                        Result.Companion companion13 = Result.INSTANCE;
                                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadedFileInfo.class), null, 2, null));
                                    } catch (Throwable th6) {
                                        Result.Companion companion14 = Result.INSTANCE;
                                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th6));
                                    }
                                    if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                        objM8936constructorimpl = null;
                                    }
                                    kSerializer = (KSerializer) objM8936constructorimpl;
                                    if (kSerializer != null) {
                                        objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer, string4);
                                    } else {
                                        objDecodeFromString2 = MainAPIKt.getMapper().readValue(string4, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadButtonSetup$handleDownloadClick$lambda$3$4$$inlined$getKey$1
                                        });
                                    }
                                }
                            }
                        } catch (Exception unused6) {
                            objDecodeFromString2 = null;
                        }
                    } else {
                        objDecodeFromString2 = null;
                    }
                    DownloadObjects.DownloadedFileInfo downloadedFileInfo = (DownloadObjects.DownloadedFileInfo) objDecodeFromString2;
                    if (downloadedFileInfo != null) {
                        Uri EMPTY = Uri.EMPTY;
                        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
                        int id2 = downloadEpisodeCached2.getId();
                        int parentId = downloadEpisodeCached2.getParentId();
                        String name = downloadEpisodeCached2.getName();
                        if (name == null) {
                            name = activity4.getString(R.string.downloaded_file);
                            Intrinsics.checkNotNullExpressionValue(name, "getString(...)");
                        }
                        arrayList4.add(new ExtractorUri(EMPTY, name, downloadedFileInfo.getBasePath(), downloadedFileInfo.getRelativePath(), downloadedFileInfo.getDisplayName(), Integer.valueOf(id2), Integer.valueOf(parentId), Integer.valueOf(downloadEpisodeCached2.getEpisode()), downloadEpisodeCached2.getSeason(), downloadHeaderCached.getName(), downloadHeaderCached.getType()));
                    }
                }
                Unit unit7 = Unit.INSTANCE;
            }
            UIHelper uIHelper = UIHelper.INSTANCE;
            int i2 = R.id.global_to_navigation_player;
            GeneratorPlayer.Companion companion15 = GeneratorPlayer.INSTANCE;
            DownloadFileGenerator downloadFileGenerator = new DownloadFileGenerator(arrayList4);
            Iterator it = arrayList4.iterator();
            while (it.hasNext()) {
                Integer id3 = ((ExtractorUri) it.next()).getId();
                int id4 = click.getData().getId();
                if (id3 != null && id3.intValue() == id4) {
                    UIHelper.navigate$default(uIHelper, activity4, i2, GeneratorPlayer.Companion.newInstance$default(companion15, downloadFileGenerator, i, null, 4, null), null, 4, null);
                    Unit unit8 = Unit.INSTANCE;
                    Unit unit9 = Unit.INSTANCE;
                }
                i++;
            }
            i = -1;
            UIHelper.navigate$default(uIHelper, activity4, i2, GeneratorPlayer.Companion.newInstance$default(companion15, downloadFileGenerator, i, null, 4, null), null, 4, null);
            Unit unit10 = Unit.INSTANCE;
            Unit unit11 = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void handleDownloadClick$lambda$0$0(Activity activity, int i, DialogInterface dialogInterface, int i2) {
        if (i2 != -1) {
            return;
        }
        VideoDownloadManager.deleteFilesAndUpdateSettings$default(VideoDownloadManager.INSTANCE, activity, SetsKt.setOf(Integer.valueOf(i)), CoroutineScopeKt.MainScope(), null, 8, null);
    }
}
