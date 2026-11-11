package com.lagradost.cloudstream3.syncproviders;

import android.content.Context;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.syncproviders.providers.Addic7ed;
import com.lagradost.cloudstream3.syncproviders.providers.AniListApi;
import com.lagradost.cloudstream3.syncproviders.providers.KitsuApi;
import com.lagradost.cloudstream3.syncproviders.providers.LocalList;
import com.lagradost.cloudstream3.syncproviders.providers.MALApi;
import com.lagradost.cloudstream3.syncproviders.providers.OpenSubtitlesApi;
import com.lagradost.cloudstream3.syncproviders.providers.SimklApi;
import com.lagradost.cloudstream3.syncproviders.providers.SubDlApi;
import com.lagradost.cloudstream3.syncproviders.providers.SubSourceApi;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.videoskip.AnimeSkipAuth;
import io.ktor.util.date.GMTDateParser;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: AccountManager.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b&\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/AccountManager;", "", "<init>", "()V", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class AccountManager {
    public static final String ACCOUNT_IDS = "auth_ids";
    public static final String ACCOUNT_TOKEN = "auth_tokens";
    public static final String APP_STRING = "cloudstreamapp";
    public static final String APP_STRING_PLAYER = "cloudstreamplayer";
    public static final String APP_STRING_REPO = "cloudstreamrepo";
    public static final String APP_STRING_RESUME_WATCHING = "cloudstreamcontinuewatching";
    public static final String APP_STRING_SEARCH = "cloudstreamsearch";
    public static final String APP_STRING_SHARE = "csshare";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int NONE_ID = -1;
    private static final Addic7ed addic7ed;
    private static final AuthRepo[] allApis;
    private static final AniListApi aniListApi;
    private static final AnimeSkipAuth animeSkipApi;
    private static Map<String, Integer> cachedAccountIds;
    private static Map<String, AuthData[]> cachedAccounts;
    private static final KitsuApi kitsuApi;
    private static final LocalList localListApi;
    private static final MALApi malApi;
    private static final OpenSubtitlesApi openSubtitlesApi;
    private static final SimklApi simklApi;
    private static final SubDlApi subDlApi;
    private static final SubSourceApi subSourceApi;
    private static final SubtitleRepo[] subtitleProviders;
    private static final SyncRepo[] syncApis;

    /* JADX INFO: compiled from: AccountManager.kt */
    @Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010<\u001a\b\u0012\u0004\u0012\u000202012\u0006\u0010=\u001a\u000200¢\u0006\u0002\u0010>J!\u0010?\u001a\u00020@2\u0006\u0010=\u001a\u0002002\f\u0010A\u001a\b\u0012\u0004\u0012\u00020201¢\u0006\u0002\u0010BJ\u0016\u0010C\u001a\u00020@2\u0006\u0010=\u001a\u0002002\u0006\u0010D\u001a\u00020\u0005J\u0006\u0010J\u001a\u00020@J\u0006\u0010K\u001a\u00020@J\u0016\u0010\\\u001a\u0002002\u0006\u0010]\u001a\u00020\u00052\u0006\u0010^\u001a\u000200R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020#¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010&\u001a\u00020'¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010*\u001a\u00020+¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R,\u0010.\u001a\u0014\u0012\u0004\u0012\u000200\u0012\n\u0012\b\u0012\u0004\u0012\u000202010/X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R&\u00107\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u00050/X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00104\"\u0004\b9\u00106R\u000e\u0010:\u001a\u000200X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u000200X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010E\u001a\b\u0012\u0004\u0012\u00020F01¢\u0006\n\n\u0002\u0010I\u001a\u0004\bG\u0010HR\u0019\u0010L\u001a\b\u0012\u0004\u0012\u00020M01¢\u0006\n\n\u0002\u0010P\u001a\u0004\bN\u0010OR\u0019\u0010Q\u001a\b\u0012\u0004\u0012\u00020R01¢\u0006\n\n\u0002\u0010U\u001a\u0004\bS\u0010TR\u000e\u0010V\u001a\u000200X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u000200X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u000200X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u000200X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u000200X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u000200X\u0086T¢\u0006\u0002\n\u0000¨\u0006_"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/AccountManager$Companion;", "", "<init>", "()V", "NONE_ID", "", "malApi", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi;", "getMalApi", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi;", "kitsuApi", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi;", "getKitsuApi", "()Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi;", "aniListApi", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi;", "getAniListApi", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi;", "simklApi", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi;", "getSimklApi", "()Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi;", "localListApi", "Lcom/lagradost/cloudstream3/syncproviders/providers/LocalList;", "getLocalListApi", "()Lcom/lagradost/cloudstream3/syncproviders/providers/LocalList;", "openSubtitlesApi", "Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi;", "getOpenSubtitlesApi", "()Lcom/lagradost/cloudstream3/syncproviders/providers/OpenSubtitlesApi;", "addic7ed", "Lcom/lagradost/cloudstream3/syncproviders/providers/Addic7ed;", "getAddic7ed", "()Lcom/lagradost/cloudstream3/syncproviders/providers/Addic7ed;", "subDlApi", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi;", "getSubDlApi", "()Lcom/lagradost/cloudstream3/syncproviders/providers/SubDlApi;", "subSourceApi", "Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi;", "getSubSourceApi", "()Lcom/lagradost/cloudstream3/syncproviders/providers/SubSourceApi;", "animeSkipApi", "Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth;", "getAnimeSkipApi", "()Lcom/lagradost/cloudstream3/utils/videoskip/AnimeSkipAuth;", "cachedAccounts", "", "", "", "Lcom/lagradost/cloudstream3/syncproviders/AuthData;", "getCachedAccounts", "()Ljava/util/Map;", "setCachedAccounts", "(Ljava/util/Map;)V", "cachedAccountIds", "getCachedAccountIds", "setCachedAccountIds", "ACCOUNT_TOKEN", "ACCOUNT_IDS", "accounts", "prefix", "(Ljava/lang/String;)[Lcom/lagradost/cloudstream3/syncproviders/AuthData;", "updateAccounts", "", "array", "(Ljava/lang/String;[Lcom/lagradost/cloudstream3/syncproviders/AuthData;)V", "updateAccountsId", "id", "allApis", "Lcom/lagradost/cloudstream3/syncproviders/AuthRepo;", "getAllApis", "()[Lcom/lagradost/cloudstream3/syncproviders/AuthRepo;", "[Lcom/lagradost/cloudstream3/syncproviders/AuthRepo;", "updateAccountIds", "initMainAPI", "subtitleProviders", "Lcom/lagradost/cloudstream3/syncproviders/SubtitleRepo;", "getSubtitleProviders", "()[Lcom/lagradost/cloudstream3/syncproviders/SubtitleRepo;", "[Lcom/lagradost/cloudstream3/syncproviders/SubtitleRepo;", "syncApis", "Lcom/lagradost/cloudstream3/syncproviders/SyncRepo;", "getSyncApis", "()[Lcom/lagradost/cloudstream3/syncproviders/SyncRepo;", "[Lcom/lagradost/cloudstream3/syncproviders/SyncRepo;", "APP_STRING", "APP_STRING_REPO", "APP_STRING_PLAYER", "APP_STRING_SEARCH", "APP_STRING_RESUME_WATCHING", "APP_STRING_SHARE", "secondsToReadable", "seconds", "completedValue", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MALApi getMalApi() {
            return AccountManager.malApi;
        }

        public final KitsuApi getKitsuApi() {
            return AccountManager.kitsuApi;
        }

        public final AniListApi getAniListApi() {
            return AccountManager.aniListApi;
        }

        public final SimklApi getSimklApi() {
            return AccountManager.simklApi;
        }

        public final LocalList getLocalListApi() {
            return AccountManager.localListApi;
        }

        public final OpenSubtitlesApi getOpenSubtitlesApi() {
            return AccountManager.openSubtitlesApi;
        }

        public final Addic7ed getAddic7ed() {
            return AccountManager.addic7ed;
        }

        public final SubDlApi getSubDlApi() {
            return AccountManager.subDlApi;
        }

        public final SubSourceApi getSubSourceApi() {
            return AccountManager.subSourceApi;
        }

        public final AnimeSkipAuth getAnimeSkipApi() {
            return AccountManager.animeSkipApi;
        }

        public final Map<String, AuthData[]> getCachedAccounts() {
            return AccountManager.cachedAccounts;
        }

        public final void setCachedAccounts(Map<String, AuthData[]> map) {
            Intrinsics.checkNotNullParameter(map, "<set-?>");
            AccountManager.cachedAccounts = map;
        }

        public final Map<String, Integer> getCachedAccountIds() {
            return AccountManager.cachedAccountIds;
        }

        public final void setCachedAccountIds(Map<String, Integer> map) {
            Intrinsics.checkNotNullParameter(map, "<set-?>");
            AccountManager.cachedAccountIds = map;
        }

        public final AuthData[] accounts(String prefix) {
            Object objM8936constructorimpl;
            Object objDecodeFromString;
            Intrinsics.checkNotNullParameter(prefix, "prefix");
            if (Intrinsics.areEqual(prefix, "NONE")) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
            String str = prefix + '/' + DataStoreHelper.INSTANCE.m8635getCurrentAccount();
            Context context = companion.getContext();
            Object obj = null;
            if (context != null) {
                DataStore dataStore = DataStore.INSTANCE;
                try {
                    String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(AccountManager.ACCOUNT_TOKEN, str), null);
                    if (string != null) {
                        AppUtils appUtils = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(AuthData.class), AuthData.INSTANCE.serializer()));
                        } catch (Throwable th) {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                            try {
                                Result.Companion companion4 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(AuthData[].class), null, 2, null));
                            } catch (Throwable th2) {
                                Result.Companion companion5 = Result.INSTANCE;
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
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<AuthData[]>() { // from class: com.lagradost.cloudstream3.syncproviders.AccountManager$Companion$accounts$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<AuthData[]>() { // from class: com.lagradost.cloudstream3.syncproviders.AccountManager$Companion$accounts$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<AuthData[]>() { // from class: com.lagradost.cloudstream3.syncproviders.AccountManager$Companion$accounts$$inlined$getKey$1
                            });
                        }
                        obj = objDecodeFromString;
                    }
                } catch (Exception unused2) {
                }
            }
            AuthData[] authDataArr = (AuthData[]) obj;
            return authDataArr == null ? new AuthData[0] : authDataArr;
        }

        public final void updateAccounts(String prefix, AuthData[] array) {
            Intrinsics.checkNotNullParameter(prefix, "prefix");
            Intrinsics.checkNotNullParameter(array, "array");
            if (Intrinsics.areEqual(prefix, "NONE")) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            CloudStreamApp.INSTANCE.setKey(AccountManager.ACCOUNT_TOKEN, prefix + '/' + DataStoreHelper.INSTANCE.m8635getCurrentAccount(), array);
            synchronized (getCachedAccounts()) {
                AccountManager.INSTANCE.getCachedAccounts().put(prefix, array);
                Unit unit = Unit.INSTANCE;
            }
        }

        public final void updateAccountsId(String prefix, int id) {
            Intrinsics.checkNotNullParameter(prefix, "prefix");
            if (Intrinsics.areEqual(prefix, "NONE")) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            CloudStreamApp.INSTANCE.setKey(AccountManager.ACCOUNT_IDS, prefix + '/' + DataStoreHelper.INSTANCE.m8635getCurrentAccount(), Integer.valueOf(id));
            synchronized (getCachedAccountIds()) {
                AccountManager.INSTANCE.getCachedAccountIds().put(prefix, Integer.valueOf(id));
                Unit unit = Unit.INSTANCE;
            }
        }

        public final AuthRepo[] getAllApis() {
            return AccountManager.allApis;
        }

        /* JADX WARN: Code duplicated, block: B:35:0x00ca A[PHI: r10
          0x00ca: PHI (r10v1 java.lang.Object) = (r10v0 java.lang.Object), (r10v2 java.lang.Object) binds: [B:5:0x0041, B:33:0x00c7] A[DONT_GENERATE, DONT_INLINE]] */
        public final void updateAccountIds() {
            Object objM8936constructorimpl;
            Object objDecodeFromString;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (AuthRepo authRepo : getAllApis()) {
                String idPrefix = authRepo.getIdPrefix();
                CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
                String str = authRepo.getIdPrefix() + '/' + DataStoreHelper.INSTANCE.m8635getCurrentAccount();
                int iIntValue = -1;
                Object obj = -1;
                Context context = companion.getContext();
                Object obj2 = null;
                if (context != null) {
                    DataStore dataStore = DataStore.INSTANCE;
                    try {
                        String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(AccountManager.ACCOUNT_IDS, str), null);
                        if (string == null) {
                            obj2 = obj;
                        } else {
                            AppUtils appUtils = AppUtils.INSTANCE;
                            try {
                                Result.Companion companion2 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(IntSerializer.INSTANCE);
                            } catch (Throwable th) {
                                Result.Companion companion3 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                            }
                            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                                try {
                                    Result.Companion companion4 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Integer.class), null, 2, null));
                                } catch (Throwable th2) {
                                    Result.Companion companion5 = Result.INSTANCE;
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
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.syncproviders.AccountManager$Companion$updateAccountIds$$inlined$getKey$1
                                    });
                                } catch (Throwable unused) {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.syncproviders.AccountManager$Companion$updateAccountIds$$inlined$getKey$1
                                    });
                                }
                            } else {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.syncproviders.AccountManager$Companion$updateAccountIds$$inlined$getKey$1
                                });
                            }
                            obj2 = objDecodeFromString;
                        }
                    } catch (Exception unused2) {
                    }
                    obj = obj2 != null ? obj2 : -1;
                }
                Integer num = (Integer) obj;
                if (num != null) {
                    iIntValue = num.intValue();
                }
                linkedHashMap.put(idPrefix, Integer.valueOf(iIntValue));
            }
            synchronized (getCachedAccountIds()) {
                AccountManager.INSTANCE.setCachedAccountIds(linkedHashMap);
                Unit unit = Unit.INSTANCE;
            }
        }

        public final void initMainAPI() {
            LoadResponse.INSTANCE.setMalIdPrefix(getMalApi().getIdPrefix());
            LoadResponse.INSTANCE.setKitsuIdPrefix(getKitsuApi().getIdPrefix());
            LoadResponse.INSTANCE.setAniListIdPrefix(getAniListApi().getIdPrefix());
            LoadResponse.INSTANCE.setSimklIdPrefix(getSimklApi().getIdPrefix());
        }

        public final SubtitleRepo[] getSubtitleProviders() {
            return AccountManager.subtitleProviders;
        }

        public final SyncRepo[] getSyncApis() {
            return AccountManager.syncApis;
        }

        public final String secondsToReadable(int seconds, String completedValue) {
            String str;
            Intrinsics.checkNotNullParameter(completedValue, "completedValue");
            long j = seconds;
            TimeUnit timeUnit = TimeUnit.SECONDS;
            long j2 = j / 86400;
            long seconds2 = j - TimeUnit.DAYS.toSeconds(j2);
            TimeUnit timeUnit2 = TimeUnit.SECONDS;
            long j3 = seconds2 / 3600;
            long seconds3 = seconds2 - TimeUnit.HOURS.toSeconds(j3);
            TimeUnit timeUnit3 = TimeUnit.SECONDS;
            long j4 = seconds3 / 60;
            TimeUnit.MINUTES.toSeconds(j4);
            if (j4 < 0) {
                return completedValue;
            }
            StringBuilder sb = new StringBuilder();
            String str2 = "";
            if (j2 != 0) {
                str = j2 + "d ";
            } else {
                str = "";
            }
            sb.append(str);
            if (j3 != 0) {
                str2 = j3 + "h ";
            }
            sb.append(str2);
            sb.append(j4);
            sb.append(GMTDateParser.MINUTES);
            return sb.toString();
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0171  */
    /* JADX WARN: Code duplicated, block: B:31:0x0187 A[Catch: Exception -> 0x0199, TRY_LEAVE, TryCatch #4 {Exception -> 0x0199, blocks: (B:7:0x011c, B:10:0x012a, B:15:0x0140, B:22:0x016b, B:25:0x0172, B:31:0x0187, B:30:0x0182, B:21:0x0161, B:14:0x0136, B:18:0x0147, B:11:0x012c, B:27:0x0176), top: B:49:0x011c, inners: #0, #2, #5 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x0176 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static {
        char c;
        Object objDecodeFromString;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        MALApi mALApi = new MALApi();
        malApi = mALApi;
        KitsuApi kitsuApi2 = new KitsuApi();
        kitsuApi = kitsuApi2;
        AniListApi aniListApi2 = new AniListApi();
        aniListApi = aniListApi2;
        SimklApi simklApi2 = new SimklApi();
        simklApi = simklApi2;
        LocalList localList = new LocalList();
        localListApi = localList;
        OpenSubtitlesApi openSubtitlesApi2 = new OpenSubtitlesApi();
        openSubtitlesApi = openSubtitlesApi2;
        Addic7ed addic7ed2 = new Addic7ed();
        addic7ed = addic7ed2;
        SubDlApi subDlApi2 = new SubDlApi();
        subDlApi = subDlApi2;
        SubSourceApi subSourceApi2 = new SubSourceApi();
        subSourceApi = subSourceApi2;
        AnimeSkipAuth animeSkipAuth = new AnimeSkipAuth();
        animeSkipApi = animeSkipAuth;
        SyncRepo syncRepo = new SyncRepo(kitsuApi2);
        boolean z = true;
        SyncRepo syncRepo2 = new SyncRepo(simklApi2);
        char c2 = 3;
        AuthRepo[] authRepoArr = {new SyncRepo(mALApi), syncRepo, new SyncRepo(aniListApi2), syncRepo2, new SyncRepo(localList), new SubtitleRepo(openSubtitlesApi2), new SubtitleRepo(addic7ed2), new SubtitleRepo(subDlApi2), new PlainAuthRepo(animeSkipAuth), new SubtitleRepo(subSourceApi2)};
        allApis = authRepoArr;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        int length = authRepoArr.length;
        int i = 0;
        while (i < length) {
            AuthRepo authRepo = authRepoArr[i];
            linkedHashMap.put(authRepo.getIdPrefix(), INSTANCE.accounts(authRepo.getIdPrefix()));
            String idPrefix = authRepo.getIdPrefix();
            CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
            String str = authRepo.getIdPrefix() + '/' + DataStoreHelper.INSTANCE.m8635getCurrentAccount();
            int iIntValue = -1;
            Object obj = -1;
            Context context = companion.getContext();
            boolean z2 = z;
            if (context != null) {
                DataStore dataStore = DataStore.INSTANCE;
                c = c2;
                try {
                    String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(ACCOUNT_IDS, str), null);
                    if (string == null) {
                        objDecodeFromString = obj;
                    } else {
                        AppUtils appUtils = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(IntSerializer.INSTANCE);
                        } catch (Throwable th) {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                            if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                objM8936constructorimpl = null;
                            }
                            kSerializer = (KSerializer) objM8936constructorimpl;
                            if (kSerializer != null) {
                                try {
                                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                                } catch (SerializationException e) {
                                    ArchComponentExtKt.logError(e);
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.syncproviders.AccountManager$special$$inlined$getKey$1
                                    });
                                } catch (Throwable unused) {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.syncproviders.AccountManager$special$$inlined$getKey$1
                                    });
                                }
                            } else {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.syncproviders.AccountManager$special$$inlined$getKey$1
                                });
                            }
                        } else {
                            try {
                                Result.Companion companion4 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Integer.class), null, 2, null));
                            } catch (Throwable th2) {
                                Result.Companion companion5 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                            }
                            if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                objM8936constructorimpl = null;
                            }
                            kSerializer = (KSerializer) objM8936constructorimpl;
                            if (kSerializer != null) {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                            } else {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.syncproviders.AccountManager$special$$inlined$getKey$1
                                });
                            }
                        }
                    }
                } catch (Exception unused2) {
                    objDecodeFromString = null;
                }
                if (objDecodeFromString != null) {
                    obj = objDecodeFromString;
                }
            } else {
                c = c2;
                obj = null;
            }
            Integer num = (Integer) obj;
            if (num != null) {
                iIntValue = num.intValue();
            }
            linkedHashMap2.put(idPrefix, Integer.valueOf(iIntValue));
            i++;
            z = z2;
            c2 = c;
        }
        boolean z3 = z;
        char c3 = c2;
        cachedAccounts = linkedHashMap;
        cachedAccountIds = linkedHashMap2;
        SubtitleRepo[] subtitleRepoArr = new SubtitleRepo[4];
        subtitleRepoArr[0] = new SubtitleRepo(openSubtitlesApi);
        subtitleRepoArr[z3 ? 1 : 0] = new SubtitleRepo(addic7ed);
        subtitleRepoArr[2] = new SubtitleRepo(subDlApi);
        subtitleRepoArr[c3] = new SubtitleRepo(subSourceApi);
        subtitleProviders = subtitleRepoArr;
        SyncRepo[] syncRepoArr = new SyncRepo[5];
        syncRepoArr[0] = new SyncRepo(malApi);
        syncRepoArr[z3 ? 1 : 0] = new SyncRepo(kitsuApi);
        syncRepoArr[2] = new SyncRepo(aniListApi);
        syncRepoArr[c3] = new SyncRepo(simklApi);
        syncRepoArr[4] = new SyncRepo(localListApi);
        syncApis = syncRepoArr;
    }
}
