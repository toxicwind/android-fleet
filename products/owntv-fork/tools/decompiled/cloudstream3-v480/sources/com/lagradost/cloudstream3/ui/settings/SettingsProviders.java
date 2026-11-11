package com.lagradost.cloudstream3.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.FragmentKt;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.DubStatus;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.APIRepository;
import com.lagradost.cloudstream3.ui.BasePreferenceFragmentCompat;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.AtomicList;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.SingleSelectionHelper;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.enums.EnumEntries;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SettingsProviders.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u001c\u0010\n\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/SettingsProviders;", "Lcom/lagradost/cloudstream3/ui/BasePreferenceFragmentCompat;", "<init>", "()V", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "onCreatePreferences", "rootKey", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SettingsProviders extends BasePreferenceFragmentCompat {
    @Override // com.lagradost.cloudstream3.ui.BasePreferenceFragmentCompat, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        SettingsFragment.INSTANCE.setUpToolbar(this, R.string.category_providers);
        SettingsProviders settingsProviders = this;
        SettingsFragment.INSTANCE.setPaddingBottom(settingsProviders);
        SettingsFragment.INSTANCE.setToolBarScrollFlags((PreferenceFragmentCompat) settingsProviders);
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        UIHelper.INSTANCE.hideKeyboard(this);
        setPreferencesFromResource(R.xml.settings_providers, rootKey);
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        SettingsProviders settingsProviders = this;
        Preference pref = SettingsFragment.INSTANCE.getPref(settingsProviders, R.string.display_sub_key);
        if (pref != null) {
            pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsProviders$$ExternalSyntheticLambda3
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsProviders.onCreatePreferences$lambda$0(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
        Preference pref2 = SettingsFragment.INSTANCE.getPref(settingsProviders, R.string.test_providers_key);
        if (pref2 != null) {
            pref2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsProviders$$ExternalSyntheticLambda4
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsProviders.onCreatePreferences$lambda$1(this.f$0, preference);
                }
            });
        }
        Preference pref3 = SettingsFragment.INSTANCE.getPref(settingsProviders, R.string.prefer_media_type_key);
        if (pref3 != null) {
            pref3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsProviders$$ExternalSyntheticLambda5
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsProviders.onCreatePreferences$lambda$2(defaultSharedPreferences, this, preference);
                }
            });
        }
        Preference pref4 = SettingsFragment.INSTANCE.getPref(settingsProviders, R.string.provider_lang_key);
        if (pref4 != null) {
            pref4.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsProviders$$ExternalSyntheticLambda6
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsProviders.onCreatePreferences$lambda$3(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
    }

    static final boolean onCreatePreferences$lambda$0(final SettingsProviders settingsProviders, final SharedPreferences sharedPreferences, Preference it) {
        HashSet<DubStatus> apiDubstatusSettings;
        Intrinsics.checkNotNullParameter(it, "it");
        FragmentActivity activity = settingsProviders.getActivity();
        if (activity == null || (apiDubstatusSettings = AppContextUtils.INSTANCE.getApiDubstatusSettings(activity)) == null) {
            return true;
        }
        final EnumEntries<DubStatus> entries = DubStatus.getEntries();
        EnumEntries<DubStatus> enumEntries = entries;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(enumEntries, 10));
        Iterator<DubStatus> it2 = enumEntries.iterator();
        while (it2.hasNext()) {
            arrayList.add(it2.next().name());
        }
        final ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList();
        Iterator<DubStatus> it3 = apiDubstatusSettings.iterator();
        Intrinsics.checkNotNullExpressionValue(it3, "iterator(...)");
        while (it3.hasNext()) {
            DubStatus next = it3.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            arrayList3.add(Integer.valueOf(entries.indexOf(next)));
        }
        FragmentActivity activity2 = settingsProviders.getActivity();
        if (activity2 == null) {
            return true;
        }
        String string = settingsProviders.getString(R.string.display_subbed_dubbed_settings);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        SingleSelectionHelper.INSTANCE.showMultiDialog(activity2, arrayList2, arrayList3, string, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsProviders$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsProviders$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsProviders.onCreatePreferences$lambda$0$0$2(sharedPreferences, entries, settingsProviders, arrayList2, (List) obj);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit onCreatePreferences$lambda$0$0$2(SharedPreferences sharedPreferences, EnumEntries enumEntries, SettingsProviders settingsProviders, List list, List selectedList) {
        Intrinsics.checkNotNullParameter(selectedList, "selectedList");
        APIRepository.Companion companion = APIRepository.INSTANCE;
        List list2 = selectedList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add((DubStatus) enumEntries.get(((Number) it.next()).intValue()));
        }
        companion.setDubStatusActive(CollectionsKt.toHashSet(arrayList));
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        String string = settingsProviders.getString(R.string.display_sub_key);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            arrayList2.add((String) list.get(((Number) it2.next()).intValue()));
        }
        editorEdit.putStringSet(string, CollectionsKt.toMutableSet(arrayList2));
        editorEdit.apply();
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$1(SettingsProviders settingsProviders, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        FragmentKt.findNavController(settingsProviders).navigate(R.id.navigation_test_providers, (Bundle) null, new NavOptions.Builder().setEnterAnim(R.anim.enter_anim).setExitAnim(R.anim.exit_anim).setPopEnterAnim(R.anim.pop_enter).setPopExitAnim(R.anim.pop_exit).build());
        return true;
    }

    static final boolean onCreatePreferences$lambda$2(final SharedPreferences sharedPreferences, final SettingsProviders settingsProviders, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        List listSorted = ArraysKt.sorted(TvType.values());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSorted, 10));
        Iterator it2 = listSorted.iterator();
        while (it2.hasNext()) {
            arrayList.add(((TvType) it2.next()).name());
        }
        ArrayList arrayList2 = arrayList;
        List listSorted2 = ArraysKt.sorted(TvType.values());
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : listSorted2) {
            if (((TvType) obj) != TvType.NSFW) {
                arrayList3.add(obj);
            }
        }
        ArrayList arrayList4 = arrayList3;
        ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, 10));
        Iterator it3 = arrayList4.iterator();
        while (it3.hasNext()) {
            arrayList5.add(Integer.valueOf(((TvType) it3.next()).ordinal()));
        }
        ArrayList arrayList6 = arrayList5;
        ArrayList arrayList7 = arrayList6;
        ArrayList arrayList8 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList7, 10));
        Iterator it4 = arrayList7.iterator();
        while (it4.hasNext()) {
            arrayList8.add(String.valueOf(((Number) it4.next()).intValue()));
        }
        Set<String> set = CollectionsKt.toSet(arrayList8);
        ArrayList arrayList9 = null;
        try {
            Set<String> stringSet = sharedPreferences.getStringSet(settingsProviders.getString(R.string.prefer_media_type_key), set);
            if (stringSet != null) {
                Set<String> set2 = stringSet;
                ArrayList arrayList10 = new ArrayList(CollectionsKt.collectionSizeOrDefault(set2, 10));
                for (String str : set2) {
                    Intrinsics.checkNotNull(str);
                    arrayList10.add(Integer.valueOf(Integer.parseInt(str)));
                }
                arrayList9 = arrayList10;
            }
        } catch (Throwable unused) {
        }
        ArrayList arrayList11 = arrayList9 == null ? arrayList6 : arrayList9;
        FragmentActivity activity = settingsProviders.getActivity();
        if (activity == null) {
            return true;
        }
        String string = settingsProviders.getString(R.string.preferred_media_settings);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        SingleSelectionHelper.INSTANCE.showMultiDialog(activity, arrayList2, arrayList11, string, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsProviders$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsProviders$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return SettingsProviders.onCreatePreferences$lambda$2$6(sharedPreferences, settingsProviders, (List) obj2);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$2$6(SharedPreferences sharedPreferences, SettingsProviders settingsProviders, List selectedList) {
        Intrinsics.checkNotNullParameter(selectedList, "selectedList");
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        String string = settingsProviders.getString(R.string.prefer_media_type_key);
        List list = selectedList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(((Number) it.next()).intValue()));
        }
        editorEdit.putStringSet(string, CollectionsKt.toMutableSet(arrayList));
        editorEdit.apply();
        DataStoreHelper.INSTANCE.setCurrentHomePage(null);
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$3(final SettingsProviders settingsProviders, final SharedPreferences sharedPreferences, Preference it) {
        HashSet<String> apiProviderLangSettings;
        Intrinsics.checkNotNullParameter(it, "it");
        FragmentActivity activity = settingsProviders.getActivity();
        if (activity == null || (apiProviderLangSettings = AppContextUtils.INSTANCE.getApiProviderLangSettings(activity)) == null) {
            return true;
        }
        final List list = (List) APIHolder.INSTANCE.getApis().withLock(new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsProviders$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SettingsProviders.onCreatePreferences$lambda$3$0$0(this.f$0);
            }
        });
        HashSet<String> hashSet = apiProviderLangSettings;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(hashSet, 10));
        for (String str : hashSet) {
            Iterator it2 = list.iterator();
            int i = 0;
            while (true) {
                if (!it2.hasNext()) {
                    i = -1;
                    break;
                }
                if (Intrinsics.areEqual(((Pair) it2.next()).getFirst(), str)) {
                    break;
                }
                i++;
            }
            arrayList.add(Integer.valueOf(i));
        }
        ArrayList arrayList2 = arrayList;
        FragmentActivity activity2 = settingsProviders.getActivity();
        if (activity2 == null) {
            return true;
        }
        SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
        FragmentActivity fragmentActivity = activity2;
        List list2 = list;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it3 = list2.iterator();
        while (it3.hasNext()) {
            arrayList3.add((String) ((Pair) it3.next()).getSecond());
        }
        String string = settingsProviders.getString(R.string.provider_lang_settings);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        singleSelectionHelper.showMultiDialog(fragmentActivity, arrayList3, arrayList2, string, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsProviders$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsProviders$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsProviders.onCreatePreferences$lambda$3$0$4(sharedPreferences, settingsProviders, list, (List) obj);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List onCreatePreferences$lambda$3$0$0(SettingsProviders settingsProviders) {
        List listListOf = CollectionsKt.listOf(new Pair(MainAPIKt.AllLanguagesName, settingsProviders.getString(R.string.all_languages_preference)));
        AtomicList<MainAPI> apis = APIHolder.INSTANCE.getApis();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(apis, 10));
        for (MainAPI mainAPI : apis) {
            String lang = mainAPI.getLang();
            String nameNextToFlagEmoji$default = SubtitleHelper.getNameNextToFlagEmoji$default(SubtitleHelper.INSTANCE, mainAPI.getLang(), null, 2, null);
            if (nameNextToFlagEmoji$default == null) {
                nameNextToFlagEmoji$default = mainAPI.getLang();
            }
            arrayList.add(new Pair(lang, nameNextToFlagEmoji$default));
        }
        return CollectionsKt.plus((Collection) listListOf, (Iterable) CollectionsKt.sortedWith(CollectionsKt.toSet(arrayList), new Comparator() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsProviders$onCreatePreferences$lambda$3$0$0$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                String lowerCase = StringsKt.substringAfter$default((String) ((Pair) t).getSecond(), " ", (String) null, 2, (Object) null).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                String lowerCase2 = StringsKt.substringAfter$default((String) ((Pair) t2).getSecond(), " ", (String) null, 2, (Object) null).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                return ComparisonsKt.compareValues(lowerCase, lowerCase2);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$3$0$4(SharedPreferences sharedPreferences, SettingsProviders settingsProviders, List list, List selectedList) {
        Intrinsics.checkNotNullParameter(selectedList, "selectedList");
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        String string = settingsProviders.getString(R.string.provider_lang_key);
        List list2 = selectedList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add((String) ((Pair) list.get(((Number) it.next()).intValue())).getFirst());
        }
        editorEdit.putStringSet(string, CollectionsKt.toSet(arrayList));
        editorEdit.apply();
        return Unit.INSTANCE;
    }
}
