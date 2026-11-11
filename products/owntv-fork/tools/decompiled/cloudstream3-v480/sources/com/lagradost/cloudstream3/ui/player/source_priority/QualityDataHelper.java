package com.lagradost.cloudstream3.ui.player.source_priority;

import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.common.Scopes;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.DebugException;
import com.lagradost.cloudstream3.utils.AppDebug;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UiText;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: QualityDataHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002-.B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\u0010\u001a\u00020\fJ\u001e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\fJ\u0018\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\fJ\u0016\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001cJ\u001e\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\fJ\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010\u0010\u001a\u00020\fJ\u0016\u0010!\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\"\u001a\u00020 J\u0016\u0010#\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\"\u001a\u00020 J\f\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0013J\u0018\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\f2\b\u0010(\u001a\u0004\u0018\u00010)J\u0017\u0010*\u001a\u00020\u001c2\b\u0010+\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0002\u0010,R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\u00020\u00058\u0002X\u0083T¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0003R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/source_priority/QualityDataHelper;", "", "<init>", "()V", "VIDEO_SOURCE_PRIORITY", "", "VIDEO_PROFILE_NAME", "VIDEO_QUALITY_PRIORITY", "VIDEO_PROFILE_TYPE", "getVIDEO_PROFILE_TYPE$annotations", "VIDEO_PROFILE_TYPES", "DEFAULT_SOURCE_PRIORITY", "", "AUTO_SKIP_PRIORITY", "PROFILE_COUNT", "getSourcePriority", Scopes.PROFILE, "name", "getAllSourcePriorityNames", "", "setSourcePriority", "", "priority", "setProfileName", "getProfileName", "Lcom/lagradost/cloudstream3/utils/UiText;", "getQualityPriority", "quality", "Lcom/lagradost/cloudstream3/utils/Qualities;", "setQualityPriority", "getQualityProfileTypes", "", "Lcom/lagradost/cloudstream3/ui/player/source_priority/QualityDataHelper$QualityProfileType;", "addQualityProfileType", "type", "removeQualityProfileType", "getProfiles", "Lcom/lagradost/cloudstream3/ui/player/source_priority/QualityDataHelper$QualityProfile;", "getLinkPriority", "qualityProfile", "linkData", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "closestQuality", TypedValues.AttributesType.S_TARGET, "(Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/utils/Qualities;", "QualityProfileType", "QualityProfile", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class QualityDataHelper {
    public static final int AUTO_SKIP_PRIORITY = 10;
    private static final int DEFAULT_SOURCE_PRIORITY = 1;
    public static final QualityDataHelper INSTANCE = new QualityDataHelper();
    private static final int PROFILE_COUNT = 7;
    private static final String VIDEO_PROFILE_NAME = "video_profile_name";
    private static final String VIDEO_PROFILE_TYPE = "video_profile_type";
    private static final String VIDEO_PROFILE_TYPES = "video_profile_types_2";
    private static final String VIDEO_QUALITY_PRIORITY = "video_quality_priority";
    private static final String VIDEO_SOURCE_PRIORITY = "video_source_priority";

    @Deprecated(message = "Changed to support multiple types per profile")
    private static /* synthetic */ void getVIDEO_PROFILE_TYPE$annotations() {
    }

    private QualityDataHelper() {
    }

    /* JADX INFO: compiled from: QualityDataHelper.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001b\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/source_priority/QualityDataHelper$QualityProfileType;", "", "stringRes", "", "unique", "", "<init>", "(Ljava/lang/String;IIZ)V", "getStringRes", "()I", "getUnique", "()Z", "None", "WiFi", "Data", "Download", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum QualityProfileType {
        None(R.string.none, false),
        WiFi(R.string.wifi, true),
        Data(R.string.mobile_data, true),
        Download(R.string.download, true);

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
        private final int stringRes;
        private final boolean unique;

        public static EnumEntries<QualityProfileType> getEntries() {
            return $ENTRIES;
        }

        QualityProfileType(int i, boolean z) {
            this.stringRes = i;
            this.unique = z;
        }

        public final int getStringRes() {
            return this.stringRes;
        }

        public final boolean getUnique() {
            return this.unique;
        }
    }

    /* JADX INFO: compiled from: QualityDataHelper.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J-\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/source_priority/QualityDataHelper$QualityProfile;", "", "name", "Lcom/lagradost/cloudstream3/utils/UiText;", "id", "", "types", "", "Lcom/lagradost/cloudstream3/ui/player/source_priority/QualityDataHelper$QualityProfileType;", "<init>", "(Lcom/lagradost/cloudstream3/utils/UiText;ILjava/util/Set;)V", "getName", "()Lcom/lagradost/cloudstream3/utils/UiText;", "getId", "()I", "getTypes", "()Ljava/util/Set;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class QualityProfile {
        private final int id;
        private final UiText name;
        private final Set<QualityProfileType> types;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ QualityProfile copy$default(QualityProfile qualityProfile, UiText uiText, int i, Set set, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                uiText = qualityProfile.name;
            }
            if ((i2 & 2) != 0) {
                i = qualityProfile.id;
            }
            if ((i2 & 4) != 0) {
                set = qualityProfile.types;
            }
            return qualityProfile.copy(uiText, i, set);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final UiText getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getId() {
            return this.id;
        }

        public final Set<QualityProfileType> component3() {
            return this.types;
        }

        public final QualityProfile copy(UiText name, int id, Set<? extends QualityProfileType> types) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(types, "types");
            return new QualityProfile(name, id, types);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof QualityProfile)) {
                return false;
            }
            QualityProfile qualityProfile = (QualityProfile) other;
            return Intrinsics.areEqual(this.name, qualityProfile.name) && this.id == qualityProfile.id && Intrinsics.areEqual(this.types, qualityProfile.types);
        }

        public int hashCode() {
            return (((this.name.hashCode() * 31) + this.id) * 31) + this.types.hashCode();
        }

        public String toString() {
            return "QualityProfile(name=" + this.name + ", id=" + this.id + ", types=" + this.types + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public QualityProfile(UiText name, int i, Set<? extends QualityProfileType> types) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(types, "types");
            this.name = name;
            this.id = i;
            this.types = types;
        }

        public final UiText getName() {
            return this.name;
        }

        public final int getId() {
            return this.id;
        }

        public final Set<QualityProfileType> getTypes() {
            return this.types;
        }
    }

    /* JADX WARN: Code duplicated, block: B:36:0x00b2 A[PHI: r2
      0x00b2: PHI (r2v4 java.lang.Object) = (r2v3 java.lang.Object), (r2v5 java.lang.Object) binds: [B:6:0x0029, B:34:0x00af] A[DONT_GENERATE, DONT_INLINE]] */
    public final int getSourcePriority(int profile, String name) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        if (name == null) {
            return 1;
        }
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str = DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/video_source_priority/" + profile;
        Object obj = 1;
        Context context = companion.getContext();
        Object obj2 = null;
        if (context != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(str, name), null);
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper$getSourcePriority$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper$getSourcePriority$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper$getSourcePriority$$inlined$getKey$1
                        });
                    }
                    obj2 = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
            obj = obj2 != null ? obj2 : 1;
        }
        Integer num = (Integer) obj;
        if (num != null) {
            return num.intValue();
        }
        return 1;
    }

    public final List<String> getAllSourcePriorityNames(int profile) {
        String str = DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/video_source_priority/" + profile;
        List<String> keys = CloudStreamApp.INSTANCE.getKeys(str);
        if (keys == null) {
            return CollectionsKt.emptyList();
        }
        List<String> list = keys;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(StringsKt.substringAfter$default((String) it.next(), str + '/', (String) null, 2, (Object) null));
        }
        return arrayList;
    }

    public final void setSourcePriority(int profile, String name, int priority) {
        Intrinsics.checkNotNullParameter(name, "name");
        String str = DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/video_source_priority/" + profile;
        if (priority == 1) {
            CloudStreamApp.INSTANCE.removeKey(str, name);
        } else {
            CloudStreamApp.INSTANCE.setKey(str, name, Integer.valueOf(priority));
        }
    }

    public final void setProfileName(int profile, String name) {
        String str = DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/video_profile_name/" + profile;
        if (name == null) {
            CloudStreamApp.INSTANCE.removeKey(str);
        } else {
            CloudStreamApp.INSTANCE.setKey(str, StringsKt.trim((CharSequence) name).toString());
        }
    }

    public final UiText getProfileName(int profile) {
        UiText uiTextTxt;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str = DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/video_profile_name/" + profile;
        Context context = companion.getContext();
        Object obj = null;
        if (context != null) {
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString(str, null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(StringSerializer.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(String.class), null, 2, null));
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper$getProfileName$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper$getProfileName$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper$getProfileName$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
        }
        String str2 = (String) obj;
        return (str2 == null || (uiTextTxt = TextUtilKt.txt(str2)) == null) ? TextUtilKt.txt(R.string.profile_number, Integer.valueOf(profile)) : uiTextTxt;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x00bf A[PHI: r2
      0x00bf: PHI (r2v1 java.lang.Object) = (r2v0 java.lang.Object), (r2v2 java.lang.Object) binds: [B:3:0x0036, B:31:0x00bc] A[DONT_GENERATE, DONT_INLINE]] */
    public final int getQualityPriority(int profile, Qualities quality) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Intrinsics.checkNotNullParameter(quality, "quality");
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str = DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/video_quality_priority/" + profile;
        String strValueOf = String.valueOf(quality.getValue());
        Object objValueOf = Integer.valueOf(quality.getDefaultPriority());
        Context context = companion.getContext();
        Object obj = null;
        if (context != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(str, strValueOf), null);
                if (string == null) {
                    obj = objValueOf;
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper$getQualityPriority$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper$getQualityPriority$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper$getQualityPriority$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
            if (obj != null) {
                objValueOf = obj;
            }
        } else {
            objValueOf = obj;
        }
        Integer num = (Integer) objValueOf;
        return num != null ? num.intValue() : quality.getDefaultPriority();
    }

    public final void setQualityPriority(int profile, Qualities quality, int priority) {
        Intrinsics.checkNotNullParameter(quality, "quality");
        CloudStreamApp.INSTANCE.setKey(DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/video_quality_priority/" + profile, String.valueOf(quality.getValue()), Integer.valueOf(priority));
    }

    /* JADX WARN: Code duplicated, block: B:22:0x008f  */
    /* JADX WARN: Code duplicated, block: B:29:0x00a5 A[Catch: Exception -> 0x00b7, TRY_LEAVE, TryCatch #6 {Exception -> 0x00b7, blocks: (B:5:0x0028, B:8:0x0034, B:13:0x005e, B:20:0x0089, B:23:0x0090, B:29:0x00a5, B:28:0x00a0, B:19:0x007f, B:12:0x0054, B:9:0x0036, B:16:0x0065, B:25:0x0094), top: B:84:0x0028, inners: #2, #9, #10 }] */
    /* JADX WARN: Code duplicated, block: B:87:0x0094 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public final Set<QualityProfileType> getQualityProfileTypes(int profile) {
        Object objDecodeFromString;
        Object objM8936constructorimpl;
        Object objDecodeFromString2;
        Object objM8936constructorimpl2;
        KSerializer kSerializer;
        String str = DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/video_profile_types_2/" + profile;
        Context context = CloudStreamApp.INSTANCE.getContext();
        Object obj = null;
        if (context != null) {
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString(str, null);
                if (string == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl2 = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(QualityProfileType.class), new EnumSerializer("com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper.QualityProfileType", QualityProfileType.values())));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl2) == null) {
                        if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                            objM8936constructorimpl2 = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl2;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<QualityProfileType[]>() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper$getQualityProfileTypes$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<QualityProfileType[]>() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper$getQualityProfileTypes$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<QualityProfileType[]>() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper$getQualityProfileTypes$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(QualityProfileType[].class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                            objM8936constructorimpl2 = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl2;
                        if (kSerializer != null) {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<QualityProfileType[]>() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper$getQualityProfileTypes$$inlined$getKey$1
                            });
                        }
                    }
                }
            } catch (Exception unused2) {
                objDecodeFromString = null;
            }
        } else {
            objDecodeFromString = null;
        }
        QualityProfileType[] qualityProfileTypeArr = (QualityProfileType[]) objDecodeFromString;
        Set<QualityProfileType> set = qualityProfileTypeArr != null ? ArraysKt.toSet(qualityProfileTypeArr) : null;
        if (set != null) {
            return set;
        }
        CloudStreamApp.Companion companion5 = CloudStreamApp.INSTANCE;
        String str2 = DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/video_profile_type/" + profile;
        Context context2 = companion5.getContext();
        if (context2 != null) {
            try {
                String string2 = DataStore.INSTANCE.getSharedPrefs(context2).getString(str2, null);
                if (string2 != null) {
                    AppUtils appUtils2 = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion6 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(new EnumSerializer("com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper.QualityProfileType", QualityProfileType.values()));
                    } catch (Throwable th3) {
                        Result.Companion companion7 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion8 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(QualityProfileType.class), null, 2, null));
                        } catch (Throwable th4) {
                            Result.Companion companion9 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th4));
                        }
                    }
                    if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                        objM8936constructorimpl = null;
                    }
                    KSerializer kSerializer2 = (KSerializer) objM8936constructorimpl;
                    if (kSerializer2 != null) {
                        try {
                            objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer2, string2);
                        } catch (SerializationException e2) {
                            ArchComponentExtKt.logError(e2);
                            objDecodeFromString2 = MainAPIKt.getMapper().readValue(string2, new TypeReference<QualityProfileType>() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper$getQualityProfileTypes$$inlined$getKey$2
                            });
                        } catch (Throwable unused3) {
                            objDecodeFromString2 = MainAPIKt.getMapper().readValue(string2, new TypeReference<QualityProfileType>() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper$getQualityProfileTypes$$inlined$getKey$2
                            });
                        }
                    } else {
                        objDecodeFromString2 = MainAPIKt.getMapper().readValue(string2, new TypeReference<QualityProfileType>() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper$getQualityProfileTypes$$inlined$getKey$2
                        });
                    }
                    obj = objDecodeFromString2;
                }
            } catch (Exception unused4) {
            }
        }
        QualityProfileType qualityProfileType = (QualityProfileType) obj;
        QualityProfileType[] qualityProfileTypeArr2 = qualityProfileType != null ? new QualityProfileType[]{qualityProfileType} : new QualityProfileType[0];
        CloudStreamApp.INSTANCE.setKey(str, qualityProfileTypeArr2);
        return ArraysKt.toSet(qualityProfileTypeArr2);
    }

    public final void addQualityProfileType(int profile, QualityProfileType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        String str = DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/video_profile_types_2/" + profile;
        Set<QualityProfileType> qualityProfileTypes = getQualityProfileTypes(profile);
        if (type != QualityProfileType.None) {
            CloudStreamApp.INSTANCE.setKey(str, SetsKt.plus(qualityProfileTypes, type).toArray(new QualityProfileType[0]));
        }
    }

    public final void removeQualityProfileType(int profile, QualityProfileType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        String str = DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/video_profile_types_2/" + profile;
        Set<QualityProfileType> qualityProfileTypes = getQualityProfileTypes(profile);
        if (type != QualityProfileType.None) {
            CloudStreamApp.INSTANCE.setKey(str, SetsKt.minus(qualityProfileTypes, type).toArray(new QualityProfileType[0]));
        }
    }

    public final List<QualityProfile> getProfiles() throws DebugException {
        List mutableList = CollectionsKt.toMutableList((Collection) QualityProfileType.getEntries());
        boolean z = true;
        IntRange intRange = new IntRange(1, 7);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        Iterator<Integer> it = intRange.iterator();
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt();
            Set<QualityProfileType> qualityProfileTypes = INSTANCE.getQualityProfileTypes(iNextInt);
            ArrayList arrayList2 = new ArrayList();
            for (QualityProfileType qualityProfileType : qualityProfileTypes) {
                if (qualityProfileType.getUnique() && !mutableList.remove(qualityProfileType)) {
                    qualityProfileType = null;
                }
                if (qualityProfileType != null) {
                    arrayList2.add(qualityProfileType);
                }
            }
            arrayList.add(new QualityProfile(INSTANCE.getProfileName(iNextInt), iNextInt, CollectionsKt.toSet(arrayList2)));
        }
        List<QualityProfile> mutableList2 = CollectionsKt.toMutableList((Collection) arrayList);
        for (QualityProfileType qualityProfileType2 : QualityProfileType.getEntries()) {
            if (qualityProfileType2.getUnique()) {
                getProfiles$insertType(mutableList2, qualityProfileType2);
            }
        }
        if (AppDebug.INSTANCE.isDebug()) {
            EnumEntries<QualityProfileType> entries = QualityProfileType.getEntries();
            if (!(entries instanceof Collection) || !entries.isEmpty()) {
                loop3: for (QualityProfileType qualityProfileType3 : entries) {
                    if (qualityProfileType3.getUnique()) {
                        List<QualityProfile> list = mutableList2;
                        if (!(list instanceof Collection) || !list.isEmpty()) {
                            Iterator<T> it2 = list.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    if (((QualityProfile) it2.next()).getTypes().contains(qualityProfileType3)) {
                                    }
                                }
                            }
                        }
                        z = false;
                        break;
                    }
                }
            }
            if (!z) {
                throw new DebugException("All unique quality types do not exist");
            }
        }
        if (AppDebug.INSTANCE.isDebug() && mutableList2.isEmpty()) {
            throw new DebugException("No profiles!");
        }
        return mutableList2;
    }

    private static final void getProfiles$insertType(List<QualityProfile> list, QualityProfileType qualityProfileType) {
        List<QualityProfile> list2 = list;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                if (((QualityProfile) it.next()).getTypes().contains(qualityProfileType)) {
                    return;
                }
            }
        }
        synchronized (list) {
            QualityProfile qualityProfile = (QualityProfile) CollectionsKt.firstOrNull((List) list);
            if (qualityProfile == null) {
                return;
            }
            list.set(0, QualityProfile.copy$default(qualityProfile, null, 0, SetsKt.plus(qualityProfile.getTypes(), qualityProfileType), 3, null));
        }
    }

    public final int getLinkPriority(int qualityProfile, ExtractorLink linkData) {
        return getQualityPriority(qualityProfile, closestQuality(linkData != null ? Integer.valueOf(linkData.getQuality()) : null)) + getSourcePriority(qualityProfile, linkData != null ? linkData.getSource() : null);
    }

    private final Qualities closestQuality(Integer target) {
        if (target == null) {
            return Qualities.Unknown;
        }
        Iterator<Qualities> it = Qualities.getEntries().iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        Qualities next = it.next();
        if (it.hasNext()) {
            int iAbs = Math.abs(next.getValue() - target.intValue());
            do {
                Qualities next2 = it.next();
                int iAbs2 = Math.abs(next2.getValue() - target.intValue());
                if (iAbs > iAbs2) {
                    next = next2;
                    iAbs = iAbs2;
                }
            } while (it.hasNext());
        }
        return next;
    }
}
