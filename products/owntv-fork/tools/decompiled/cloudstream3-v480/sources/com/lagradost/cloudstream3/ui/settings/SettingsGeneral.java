package com.lagradost.cloudstream3.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.AddRemoveSitesBinding;
import com.lagradost.cloudstream3.databinding.AddSiteInputBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.network.RequestsHelperKt;
import com.lagradost.cloudstream3.ui.BasePreferenceFragmentCompat;
import com.lagradost.cloudstream3.ui.settings.utils.DirectoryPickerKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.BatteryOptimizationChecker;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreKt;
import com.lagradost.cloudstream3.utils.SingleSelectionHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.downloader.DownloadFileManagement;
import com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager;
import com.lagradost.safefile.SafeFile;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.json.JsonNames;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: SettingsGeneral.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00112\u00020\u0001:\u0002\u0010\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u001c\u0010\r\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/SettingsGeneral;", "Lcom/lagradost/cloudstream3/ui/BasePreferenceFragmentCompat;", "<init>", "()V", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "pathPicker", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/net/Uri;", "onCreatePreferences", "rootKey", "", "CustomSite", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SettingsGeneral extends BasePreferenceFragmentCompat {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ActivityResultLauncher<Uri> pathPicker = DirectoryPickerKt.getChooseFolderLauncher(this, new Function2() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda14
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return SettingsGeneral.pathPicker$lambda$0(this.f$0, (Uri) obj, (String) obj2);
        }
    });

    @Override // com.lagradost.cloudstream3.ui.BasePreferenceFragmentCompat, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        SettingsFragment.INSTANCE.setUpToolbar(this, R.string.category_general);
        SettingsGeneral settingsGeneral = this;
        SettingsFragment.INSTANCE.setPaddingBottom(settingsGeneral);
        SettingsFragment.INSTANCE.setToolBarScrollFlags((PreferenceFragmentCompat) settingsGeneral);
    }

    /* JADX INFO: compiled from: SettingsGeneral.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 +2\u00020\u0001:\u0002*+B/\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bBC\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J1\u0010\u001c\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\nHÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0001¢\u0006\u0002\b)R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u001c\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011R\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0017\u0010\u0011¨\u0006,"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/SettingsGeneral$CustomSite;", "", "parentClassName", "", "name", ImagesContract.URL, "lang", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getParentClassName$annotations", "()V", "getParentClassName", "()Ljava/lang/String;", "getName$annotations", "getName", "getUrl$annotations", "getUrl", "getLang$annotations", "getLang", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class CustomSite {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("lang")
        private final String lang;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("parentClassName")
        @JsonAlias({"parentJavaClass"})
        private final String parentClassName;

        @JsonProperty(ImagesContract.URL)
        private final String url;

        public static /* synthetic */ CustomSite copy$default(CustomSite customSite, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = customSite.parentClassName;
            }
            if ((i & 2) != 0) {
                str2 = customSite.name;
            }
            if ((i & 4) != 0) {
                str3 = customSite.url;
            }
            if ((i & 8) != 0) {
                str4 = customSite.lang;
            }
            return customSite.copy(str, str2, str3, str4);
        }

        @SerialName("lang")
        public static /* synthetic */ void getLang$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("parentClassName")
        @JsonNames(names = {"parentJavaClass"})
        public static /* synthetic */ void getParentClassName$annotations() {
        }

        @SerialName(ImagesContract.URL)
        public static /* synthetic */ void getUrl$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getParentClassName() {
            return this.parentClassName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getLang() {
            return this.lang;
        }

        public final CustomSite copy(@JsonProperty("parentClassName") @JsonAlias({"parentJavaClass"}) String parentClassName, @JsonProperty("name") String name, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("lang") String lang) {
            Intrinsics.checkNotNullParameter(parentClassName, "parentClassName");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(lang, "lang");
            return new CustomSite(parentClassName, name, url, lang);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CustomSite)) {
                return false;
            }
            CustomSite customSite = (CustomSite) other;
            return Intrinsics.areEqual(this.parentClassName, customSite.parentClassName) && Intrinsics.areEqual(this.name, customSite.name) && Intrinsics.areEqual(this.url, customSite.url) && Intrinsics.areEqual(this.lang, customSite.lang);
        }

        public int hashCode() {
            return (((((this.parentClassName.hashCode() * 31) + this.name.hashCode()) * 31) + this.url.hashCode()) * 31) + this.lang.hashCode();
        }

        public String toString() {
            return "CustomSite(parentClassName=" + this.parentClassName + ", name=" + this.name + ", url=" + this.url + ", lang=" + this.lang + ')';
        }

        /* JADX INFO: compiled from: SettingsGeneral.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/SettingsGeneral$CustomSite$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/ui/settings/SettingsGeneral$CustomSite;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<CustomSite> serializer() {
                return SettingsGeneral$CustomSite$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ CustomSite(int i, String str, String str2, String str3, String str4, SerializationConstructorMarker serializationConstructorMarker) {
            if (15 != (i & 15)) {
                PluginExceptionsKt.throwMissingFieldException(i, 15, SettingsGeneral$CustomSite$$serializer.INSTANCE.getDescriptor());
            }
            this.parentClassName = str;
            this.name = str2;
            this.url = str3;
            this.lang = str4;
        }

        public CustomSite(@JsonProperty("parentClassName") @JsonAlias({"parentJavaClass"}) String parentClassName, @JsonProperty("name") String name, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("lang") String lang) {
            Intrinsics.checkNotNullParameter(parentClassName, "parentClassName");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(lang, "lang");
            this.parentClassName = parentClassName;
            this.name = name;
            this.url = url;
            this.lang = lang;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(CustomSite self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.parentClassName);
            output.encodeStringElement(serialDesc, 1, self.name);
            output.encodeStringElement(serialDesc, 2, self.url);
            output.encodeStringElement(serialDesc, 3, self.lang);
        }

        public final String getParentClassName() {
            return this.parentClassName;
        }

        public final String getName() {
            return this.name;
        }

        public final String getUrl() {
            return this.url;
        }

        public final String getLang() {
            return this.lang;
        }
    }

    /* JADX INFO: compiled from: SettingsGeneral.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n¨\u0006\u000b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/SettingsGeneral$Companion;", "", "<init>", "()V", "pickDownloadPath", "", "Landroidx/fragment/app/Fragment;", "uri", "Landroid/net/Uri;", "path", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void pickDownloadPath(Fragment fragment, Uri uri, String str) {
            Intrinsics.checkNotNullParameter(fragment, "<this>");
            if (uri == null) {
                return;
            }
            Context context = fragment.getContext();
            if (context == null && (context = CloudStreamApp.INSTANCE.getContext()) == null) {
                return;
            }
            if (str == null) {
                str = uri.toString();
                Intrinsics.checkNotNullExpressionValue(str, "toString(...)");
            }
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "getDefaultSharedPreferences(...)");
            SharedPreferences.Editor editorEdit = defaultSharedPreferences.edit();
            editorEdit.putString(fragment.getString(R.string.download_path_key), uri.toString());
            editorEdit.putString(context.getString(R.string.download_path_key_visual), str);
            editorEdit.apply();
        }
    }

    static final Unit pathPicker$lambda$0(SettingsGeneral settingsGeneral, Uri uri, String str) {
        INSTANCE.pickDownloadPath(settingsGeneral, uri, str);
        return Unit.INSTANCE;
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        String string;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Preference preferenceHideOn;
        UIHelper.INSTANCE.hideKeyboard(this);
        setPreferencesFromResource(R.xml.settings_general, rootKey);
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        SettingsGeneral settingsGeneral = this;
        Preference pref = SettingsFragment.INSTANCE.getPref(settingsGeneral, R.string.locale_key);
        if (pref != null) {
            pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda22
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsGeneral.onCreatePreferences$lambda$0(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
        Preference pref2 = SettingsFragment.INSTANCE.getPref(settingsGeneral, R.string.battery_optimisation_key);
        if (pref2 != null && (preferenceHideOn = SettingsFragment.INSTANCE.hideOn(pref2, 6)) != null) {
            preferenceHideOn.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda23
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsGeneral.onCreatePreferences$lambda$1(this.f$0, preference);
                }
            });
        }
        Preference pref3 = SettingsFragment.INSTANCE.getPref(settingsGeneral, R.string.override_site_key);
        if (pref3 != null) {
            pref3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda24
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsGeneral.onCreatePreferences$lambda$12(this.f$0, preference);
                }
            });
        }
        Preference pref4 = SettingsFragment.INSTANCE.getPref(settingsGeneral, R.string.legal_notice_key);
        if (pref4 != null) {
            pref4.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda25
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsGeneral.onCreatePreferences$lambda$13(preference);
                }
            });
        }
        Preference pref5 = SettingsFragment.INSTANCE.getPref(settingsGeneral, R.string.dns_key);
        if (pref5 != null) {
            pref5.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda1
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsGeneral.onCreatePreferences$lambda$14(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
        Intrinsics.checkNotNull(defaultSharedPreferences);
        SharedPreferences.Editor editorEdit = defaultSharedPreferences.edit();
        String string2 = getString(R.string.jsdelivr_proxy_key);
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String string3 = getString(R.string.jsdelivr_proxy_key);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        Object obj = false;
        Context context = companion.getContext();
        if (context != null) {
            try {
                String string4 = DataStore.INSTANCE.getSharedPrefs(context).getString(string3, null);
                if (string4 != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(BooleanSerializer.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Boolean.class), null, 2, null));
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
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string4);
                        } catch (SerializationException e) {
                            ArchComponentExtKt.logError(e);
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string4, new TypeReference<Boolean>() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$onCreatePreferences$lambda$16$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string4, new TypeReference<Boolean>() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$onCreatePreferences$lambda$16$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string4, new TypeReference<Boolean>() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$onCreatePreferences$lambda$16$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
                obj = null;
            }
        } else {
            obj = null;
        }
        Boolean bool = (Boolean) obj;
        editorEdit.putBoolean(string2, bool != null ? bool.booleanValue() : false);
        editorEdit.apply();
        Preference pref6 = SettingsFragment.INSTANCE.getPref(settingsGeneral, R.string.jsdelivr_proxy_key);
        if (pref6 != null) {
            pref6.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda2
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public final boolean onPreferenceChange(Preference preference, Object obj2) {
                    return SettingsGeneral.onCreatePreferences$lambda$17(this.f$0, preference, obj2);
                }
            });
        }
        Preference pref7 = SettingsFragment.INSTANCE.getPref(settingsGeneral, R.string.download_parallel_key);
        if (pref7 != null) {
            pref7.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda3
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public final boolean onPreferenceChange(Preference preference, Object obj2) {
                    return SettingsGeneral.onCreatePreferences$lambda$18(preference, obj2);
                }
            });
        }
        Preference pref8 = SettingsFragment.INSTANCE.getPref(settingsGeneral, R.string.download_path_key);
        if (pref8 != null) {
            pref8.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda4
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsGeneral.onCreatePreferences$lambda$19(defaultSharedPreferences, this, preference);
                }
            });
        }
        try {
            Globals.INSTANCE.setBeneneCount(defaultSharedPreferences.getInt(getString(R.string.benene_count), 0));
            Preference pref9 = SettingsFragment.INSTANCE.getPref(this, R.string.benene_count);
            if (pref9 != null) {
                if (Globals.INSTANCE.getBeneneCount() <= 0) {
                    string = getString(R.string.benene_count_text_none);
                } else {
                    String string5 = getString(R.string.benene_count_text);
                    Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                    String str = String.format(string5, Arrays.copyOf(new Object[]{Integer.valueOf(Globals.INSTANCE.getBeneneCount())}, 1));
                    Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                    string = str;
                }
                pref9.setSummary(string);
                pref9.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda5
                    @Override // androidx.preference.Preference.OnPreferenceClickListener
                    public final boolean onPreferenceClick(Preference preference) {
                        return SettingsGeneral.onCreatePreferences$lambda$20$0(this.f$0, defaultSharedPreferences, preference);
                    }
                });
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static final List<CustomSite> onCreatePreferences$getCurrent() {
        List<CustomSite> mutableList;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Context context = CloudStreamApp.INSTANCE.getContext();
        Object obj = null;
        if (context != null) {
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString(DataStoreKt.USER_PROVIDER_API, null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(CustomSite.class), CustomSite.INSTANCE.serializer()));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(CustomSite[].class), null, 2, null));
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<CustomSite[]>() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$onCreatePreferences$getCurrent$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<CustomSite[]>() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$onCreatePreferences$getCurrent$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<CustomSite[]>() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$onCreatePreferences$getCurrent$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
        }
        CustomSite[] customSiteArr = (CustomSite[]) obj;
        return (customSiteArr == null || (mutableList = ArraysKt.toMutableList(customSiteArr)) == null) ? new ArrayList() : mutableList;
    }

    static final boolean onCreatePreferences$lambda$0(final SettingsGeneral settingsGeneral, final SharedPreferences sharedPreferences, Preference pref) {
        Intrinsics.checkNotNullParameter(pref, "pref");
        Context context = pref.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        String currentLocale = SettingsGeneralKt.getCurrentLocale(context);
        List<Pair<String, String>> appLanguages = SettingsGeneralKt.getAppLanguages();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(appLanguages, 10));
        Iterator<T> it = appLanguages.iterator();
        while (it.hasNext()) {
            arrayList.add((String) ((Pair) it.next()).getSecond());
        }
        final ArrayList arrayList2 = arrayList;
        List<Pair<String, String>> appLanguages2 = SettingsGeneralKt.getAppLanguages();
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(appLanguages2, 10));
        Iterator<T> it2 = appLanguages2.iterator();
        while (it2.hasNext()) {
            arrayList3.add(SettingsGeneralKt.nameNextToFlagEmoji((Pair) it2.next()));
        }
        ArrayList arrayList4 = arrayList3;
        int iIndexOf = arrayList2.indexOf(currentLocale);
        FragmentActivity activity = settingsGeneral.getActivity();
        if (activity == null) {
            return true;
        }
        String string = settingsGeneral.getString(R.string.app_language);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        SingleSelectionHelper.INSTANCE.showDialog(activity, arrayList4, iIndexOf, string, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsGeneral.onCreatePreferences$lambda$0$3(arrayList2, settingsGeneral, sharedPreferences, ((Integer) obj).intValue());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$0$3(List list, SettingsGeneral settingsGeneral, SharedPreferences sharedPreferences, int i) {
        try {
            String str = (String) list.get(i);
            CommonActivity.INSTANCE.setLocale(settingsGeneral.getActivity(), str);
            Intrinsics.checkNotNull(sharedPreferences);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString(settingsGeneral.getString(R.string.locale_key), str);
            editorEdit.apply();
            FragmentActivity activity = settingsGeneral.getActivity();
            if (activity != null) {
                activity.recreate();
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$1(SettingsGeneral settingsGeneral, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Context context = settingsGeneral.getContext();
        if (context == null) {
            return false;
        }
        if (BatteryOptimizationChecker.INSTANCE.isAppRestricted(context)) {
            BatteryOptimizationChecker.INSTANCE.showBatteryOptimizationDialog(context);
            return true;
        }
        CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.app_unrestricted_toast, (Integer) null, 2, (Object) null);
        return true;
    }

    private static final void onCreatePreferences$showAdd(final SettingsGeneral settingsGeneral) {
        String string;
        final List listSortedWith = CollectionsKt.sortedWith(APIHolder.INSTANCE.getAllProviders().distinctBy(new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsGeneral.onCreatePreferences$showAdd$lambda$2((MainAPI) obj);
            }
        }), new Comparator() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$onCreatePreferences$showAdd$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((MainAPI) t).getName(), ((MainAPI) t2).getName());
            }
        });
        FragmentActivity activity = settingsGeneral.getActivity();
        if (activity != null) {
            SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
            FragmentActivity fragmentActivity = activity;
            List<MainAPI> list = listSortedWith;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (MainAPI mainAPI : list) {
                arrayList.add(mainAPI.getName() + " (" + mainAPI.getMainUrl() + ')');
            }
            ArrayList arrayList2 = arrayList;
            Context context = settingsGeneral.getContext();
            if (context == null || (string = context.getString(R.string.add_site_pref)) == null) {
                return;
            }
            singleSelectionHelper.showDialog(fragmentActivity, arrayList2, -1, string, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SettingsGeneral.onCreatePreferences$showAdd$lambda$6(listSortedWith, settingsGeneral, ((Integer) obj).intValue());
                }
            });
        }
    }

    static final Object onCreatePreferences$showAdd$lambda$2(MainAPI it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Reflection.getOrCreateKotlinClass(it.getClass());
    }

    static final Unit onCreatePreferences$showAdd$lambda$6(List list, final SettingsGeneral settingsGeneral, int i) {
        final MainAPI mainAPI = (MainAPI) CollectionsKt.getOrNull(list, i);
        if (mainAPI == null) {
            return Unit.INSTANCE;
        }
        final AddSiteInputBinding addSiteInputBindingInflate = AddSiteInputBinding.inflate(settingsGeneral.getLayoutInflater(), null, false);
        Intrinsics.checkNotNullExpressionValue(addSiteInputBindingInflate, "inflate(...)");
        Context context = settingsGeneral.getContext();
        if (context == null) {
            return Unit.INSTANCE;
        }
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(context, R.style.AlertDialogCustom).setView(addSiteInputBindingInflate.getRoot()).create();
        Intrinsics.checkNotNullExpressionValue(alertDialogCreate, "create(...)");
        alertDialogCreate.show();
        addSiteInputBindingInflate.text2.setText(mainAPI.getName());
        addSiteInputBindingInflate.applyBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsGeneral.onCreatePreferences$showAdd$lambda$6$0(addSiteInputBindingInflate, mainAPI, alertDialogCreate, settingsGeneral, view);
            }
        });
        addSiteInputBindingInflate.cancelBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda19
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsGeneral.onCreatePreferences$showAdd$lambda$6$1(alertDialogCreate, settingsGeneral, view);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreatePreferences$showAdd$lambda$6$0(AddSiteInputBinding addSiteInputBinding, MainAPI mainAPI, AlertDialog alertDialog, SettingsGeneral settingsGeneral, View view) {
        String str;
        Editable text = addSiteInputBinding.siteNameInput.getText();
        String string = text != null ? text.toString() : null;
        Editable text2 = addSiteInputBinding.siteUrlInput.getText();
        String string2 = text2 != null ? text2.toString() : null;
        Editable text3 = addSiteInputBinding.siteLangInput.getText();
        String string3 = text3 != null ? text3.toString() : null;
        String str2 = string3;
        if (str2 == null || StringsKt.isBlank(str2)) {
            string3 = mainAPI.getLang();
        }
        String simpleName = Reflection.getOrCreateKotlinClass(mainAPI.getClass()).getSimpleName();
        String str3 = string2;
        if (str3 == null || StringsKt.isBlank(str3) || (str = string) == null || StringsKt.isBlank(str) || simpleName == null) {
            CommonActivity.INSTANCE.showToast(R.string.error_invalid_data, (Integer) 0);
            return;
        }
        List<CustomSite> listOnCreatePreferences$getCurrent = onCreatePreferences$getCurrent();
        listOnCreatePreferences$getCurrent.add(new CustomSite(simpleName, string, string2, string3));
        CloudStreamApp.INSTANCE.setKey(DataStoreKt.USER_PROVIDER_API, listOnCreatePreferences$getCurrent.toArray(new CustomSite[0]));
        MainActivity.INSTANCE.getAfterPluginsLoadedEvent().invoke(false);
        UIHelper.INSTANCE.dismissSafe(alertDialog, settingsGeneral.getActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreatePreferences$showAdd$lambda$6$1(AlertDialog alertDialog, SettingsGeneral settingsGeneral, View view) {
        UIHelper.INSTANCE.dismissSafe(alertDialog, settingsGeneral.getActivity());
    }

    private static final void onCreatePreferences$showDelete(SettingsGeneral settingsGeneral) {
        String string;
        final List<CustomSite> listOnCreatePreferences$getCurrent = onCreatePreferences$getCurrent();
        FragmentActivity activity = settingsGeneral.getActivity();
        if (activity != null) {
            SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
            FragmentActivity fragmentActivity = activity;
            List<CustomSite> list = listOnCreatePreferences$getCurrent;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((CustomSite) it.next()).getName());
            }
            ArrayList arrayList2 = arrayList;
            List<Integer> listEmptyList = CollectionsKt.emptyList();
            Context context = settingsGeneral.getContext();
            if (context == null || (string = context.getString(R.string.remove_site_pref)) == null) {
                return;
            }
            singleSelectionHelper.showMultiDialog(fragmentActivity, arrayList2, listEmptyList, string, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SettingsGeneral.onCreatePreferences$showDelete$lambda$9(listOnCreatePreferences$getCurrent, (List) obj);
                }
            });
        }
    }

    static final Unit onCreatePreferences$showDelete$lambda$9(List list, List indexes) {
        Intrinsics.checkNotNullParameter(indexes, "indexes");
        List list2 = indexes;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add((CustomSite) list.get(((Number) it.next()).intValue()));
        }
        list.removeAll(arrayList);
        CloudStreamApp.INSTANCE.setKey(DataStoreKt.USER_PROVIDER_API, list.toArray(new CustomSite[0]));
        return Unit.INSTANCE;
    }

    private static final void onCreatePreferences$showAddOrDelete(final SettingsGeneral settingsGeneral) {
        AddRemoveSitesBinding addRemoveSitesBindingInflate = AddRemoveSitesBinding.inflate(settingsGeneral.getLayoutInflater(), null, false);
        Intrinsics.checkNotNullExpressionValue(addRemoveSitesBindingInflate, "inflate(...)");
        Context context = settingsGeneral.getContext();
        if (context == null) {
            return;
        }
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(context, R.style.AlertDialogCustom).setView(addRemoveSitesBindingInflate.getRoot()).create();
        Intrinsics.checkNotNullExpressionValue(alertDialogCreate, "create(...)");
        alertDialogCreate.show();
        addRemoveSitesBindingInflate.addSite.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsGeneral.onCreatePreferences$showAddOrDelete$lambda$10(alertDialogCreate, settingsGeneral, view);
            }
        });
        addRemoveSitesBindingInflate.removeSite.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsGeneral.onCreatePreferences$showAddOrDelete$lambda$11(alertDialogCreate, settingsGeneral, view);
            }
        });
    }

    static final void onCreatePreferences$showAddOrDelete$lambda$10(AlertDialog alertDialog, SettingsGeneral settingsGeneral, View view) {
        onCreatePreferences$showAdd(settingsGeneral);
        UIHelper.INSTANCE.dismissSafe(alertDialog, settingsGeneral.getActivity());
    }

    static final void onCreatePreferences$showAddOrDelete$lambda$11(AlertDialog alertDialog, SettingsGeneral settingsGeneral, View view) {
        onCreatePreferences$showDelete(settingsGeneral);
        UIHelper.INSTANCE.dismissSafe(alertDialog, settingsGeneral.getActivity());
    }

    static final boolean onCreatePreferences$lambda$12(SettingsGeneral settingsGeneral, Preference preference) {
        Intrinsics.checkNotNullParameter(preference, "<unused var>");
        if (onCreatePreferences$getCurrent().isEmpty()) {
            onCreatePreferences$showAdd(settingsGeneral);
            return true;
        }
        onCreatePreferences$showAddOrDelete(settingsGeneral);
        return true;
    }

    static final boolean onCreatePreferences$lambda$13(Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        AlertDialog.Builder builder = new AlertDialog.Builder(it.getContext(), R.style.AlertDialogCustom);
        builder.setTitle(R.string.legal_notice);
        builder.setMessage(R.string.legal_notice_text);
        builder.show();
        return true;
    }

    static final boolean onCreatePreferences$lambda$14(final SettingsGeneral settingsGeneral, final SharedPreferences sharedPreferences, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String[] stringArray = settingsGeneral.getResources().getStringArray(R.array.dns_pref);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        final int[] intArray = settingsGeneral.getResources().getIntArray(R.array.dns_pref_values);
        Intrinsics.checkNotNullExpressionValue(intArray, "getIntArray(...)");
        int i = sharedPreferences.getInt(settingsGeneral.getString(R.string.dns_pref), 0);
        FragmentActivity activity = settingsGeneral.getActivity();
        if (activity == null) {
            return true;
        }
        List<String> list = ArraysKt.toList(stringArray);
        int iIndexOf = ArraysKt.indexOf(intArray, i);
        String string = settingsGeneral.getString(R.string.dns_pref);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        SingleSelectionHelper.INSTANCE.showBottomDialog(activity, list, iIndexOf, string, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsGeneral.onCreatePreferences$lambda$14$1(sharedPreferences, settingsGeneral, intArray, ((Integer) obj).intValue());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$14$1(SharedPreferences sharedPreferences, SettingsGeneral settingsGeneral, int[] iArr, int i) {
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt(settingsGeneral.getString(R.string.dns_pref), iArr[i]);
        editorEdit.apply();
        Context context = settingsGeneral.getContext();
        if (context == null) {
            context = CloudStreamApp.INSTANCE.getContext();
        }
        if (context != null) {
            RequestsHelperKt.initClient(MainActivityKt.getApp(), context);
        }
        return Unit.INSTANCE;
    }

    private static final List<String> onCreatePreferences$getDownloadDirs(final SettingsGeneral settingsGeneral) {
        List<String> list = (List) ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SettingsGeneral.onCreatePreferences$getDownloadDirs$lambda$15(this.f$0);
            }
        });
        return list == null ? CollectionsKt.emptyList() : list;
    }

    static final List onCreatePreferences$getDownloadDirs$lambda$15(SettingsGeneral settingsGeneral) {
        String second;
        Context context = settingsGeneral.getContext();
        if (context == null) {
            return null;
        }
        SafeFile defaultDir = DownloadFileManagement.INSTANCE.getDefaultDir(context);
        List listListOf = CollectionsKt.listOf(defaultDir != null ? defaultDir.filePath() : null);
        try {
            Pair<SafeFile, String> basePath$app = DownloadFileManagement.INSTANCE.getBasePath$app(context);
            SafeFile first = basePath$app.getFirst();
            if (first == null || (second = first.filePath()) == null) {
                second = basePath$app.getSecond();
            }
            List list = listListOf;
            File[] externalFilesDirs = context.getExternalFilesDirs("");
            Intrinsics.checkNotNullExpressionValue(externalFilesDirs, "getExternalFilesDirs(...)");
            ArrayList arrayList = new ArrayList();
            for (File file : externalFilesDirs) {
                String path = file.getPath();
                if (path != null) {
                    arrayList.add(path);
                }
            }
            listListOf = CollectionsKt.plus((Collection<? extends String>) CollectionsKt.plus((Collection) list, (Iterable) arrayList), second);
        } catch (Exception unused) {
        }
        return CollectionsKt.distinct(CollectionsKt.filterNotNull(listListOf));
    }

    static final boolean onCreatePreferences$lambda$17(SettingsGeneral settingsGeneral, Preference preference, Object obj) {
        Intrinsics.checkNotNullParameter(preference, "<unused var>");
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String string = settingsGeneral.getString(R.string.jsdelivr_proxy_key);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        companion.setKey(string, obj);
        return true;
    }

    static final boolean onCreatePreferences$lambda$18(Preference preference, Object obj) {
        Intrinsics.checkNotNullParameter(preference, "<unused var>");
        DownloadQueueManager.INSTANCE.forceRefreshQueue();
        return true;
    }

    static final boolean onCreatePreferences$lambda$19(final SharedPreferences sharedPreferences, final SettingsGeneral settingsGeneral, Preference it) {
        SafeFile defaultDir;
        Intrinsics.checkNotNullParameter(it, "it");
        final List<String> listOnCreatePreferences$getDownloadDirs = onCreatePreferences$getDownloadDirs(settingsGeneral);
        String str = null;
        String string = sharedPreferences.getString(settingsGeneral.getString(R.string.download_path_key_visual), null);
        if (string != null) {
            str = string;
        } else {
            Context context = settingsGeneral.getContext();
            if (context != null && (defaultDir = DownloadFileManagement.INSTANCE.getDefaultDir(context)) != null) {
                string = defaultDir.filePath();
                str = string;
            }
        }
        FragmentActivity activity = settingsGeneral.getActivity();
        if (activity == null) {
            return true;
        }
        List<String> listPlus = CollectionsKt.plus((Collection) listOnCreatePreferences$getDownloadDirs, (Iterable) CollectionsKt.listOf(settingsGeneral.getString(R.string.custom)));
        int iIndexOf = CollectionsKt.indexOf((List<? extends String>) listOnCreatePreferences$getDownloadDirs, str);
        String string2 = settingsGeneral.getString(R.string.download_path_pref);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        SingleSelectionHelper.INSTANCE.showBottomDialog(activity, listPlus, iIndexOf, string2, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneral$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsGeneral.onCreatePreferences$lambda$19$2(listOnCreatePreferences$getDownloadDirs, settingsGeneral, sharedPreferences, ((Integer) obj).intValue());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$19$2(List list, SettingsGeneral settingsGeneral, SharedPreferences sharedPreferences, int i) {
        if (i == list.size()) {
            try {
                settingsGeneral.pathPicker.launch(Uri.EMPTY);
            } catch (Exception e) {
                ArchComponentExtKt.logError(e);
            }
        } else {
            Intrinsics.checkNotNull(sharedPreferences);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString(settingsGeneral.getString(R.string.download_path_key), (String) list.get(i));
            editorEdit.putString(settingsGeneral.getString(R.string.download_path_key_visual), (String) list.get(i));
            editorEdit.apply();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreatePreferences$lambda$20$0(SettingsGeneral settingsGeneral, SharedPreferences sharedPreferences, Preference it) {
        FragmentActivity activity;
        Intrinsics.checkNotNullParameter(it, "it");
        try {
            Globals.INSTANCE.setBeneneCount(Globals.INSTANCE.getBeneneCount() + 1);
            if (Globals.INSTANCE.getBeneneCount() % 20 == 0 && (activity = settingsGeneral.getActivity()) != null) {
                UIHelper.navigate$default(UIHelper.INSTANCE, activity, R.id.action_navigation_settings_general_to_easterEggMonkeFragment, null, null, 6, null);
            }
            Intrinsics.checkNotNull(sharedPreferences);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt(settingsGeneral.getString(R.string.benene_count), Globals.INSTANCE.getBeneneCount());
            editorEdit.apply();
            String string = settingsGeneral.getString(R.string.benene_count_text);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(Globals.INSTANCE.getBeneneCount())}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            it.setSummary(str);
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
        return true;
    }
}
