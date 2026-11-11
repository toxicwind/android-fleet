package com.lagradost.cloudstream3.ui.settings;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SeekBarPreference;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.SearchQuality;
import com.lagradost.cloudstream3.databinding.ActivityMainBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.BaseAdapterKt;
import com.lagradost.cloudstream3.ui.BasePreferenceFragmentCompat;
import com.lagradost.cloudstream3.ui.home.HomeChildItemAdapter;
import com.lagradost.cloudstream3.ui.home.ParentItemAdapter;
import com.lagradost.cloudstream3.ui.search.SearchAdapter;
import com.lagradost.cloudstream3.ui.search.SearchResultBuilder;
import com.lagradost.cloudstream3.utils.SingleSelectionHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SettingsUI.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u001c\u0010\n\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/SettingsUI;", "Lcom/lagradost/cloudstream3/ui/BasePreferenceFragmentCompat;", "<init>", "()V", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "onCreatePreferences", "rootKey", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SettingsUI extends BasePreferenceFragmentCompat {
    @Override // com.lagradost.cloudstream3.ui.BasePreferenceFragmentCompat, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        SettingsFragment.INSTANCE.setUpToolbar(this, R.string.category_ui);
        SettingsUI settingsUI = this;
        SettingsFragment.INSTANCE.setPaddingBottom(settingsUI);
        SettingsFragment.INSTANCE.setToolBarScrollFlags((PreferenceFragmentCompat) settingsUI);
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        UIHelper.INSTANCE.hideKeyboard(this);
        setPreferencesFromResource(R.xml.settings_ui, rootKey);
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        SettingsUI settingsUI = this;
        Preference pref = SettingsFragment.INSTANCE.getPref(settingsUI, R.string.overscan_key);
        Preference preferenceHideOn = pref != null ? SettingsFragment.INSTANCE.hideOn(pref, 5) : null;
        SeekBarPreference seekBarPreference = preferenceHideOn instanceof SeekBarPreference ? (SeekBarPreference) preferenceHideOn : null;
        if (seekBarPreference != null) {
            seekBarPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda21
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public final boolean onPreferenceChange(Preference preference, Object obj) {
                    return SettingsUI.onCreatePreferences$lambda$0(preference, obj);
                }
            });
        }
        Preference pref2 = SettingsFragment.INSTANCE.getPref(settingsUI, R.string.bottom_title_key);
        if (pref2 != null) {
            pref2.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda1
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public final boolean onPreferenceChange(Preference preference, Object obj) {
                    return SettingsUI.onCreatePreferences$lambda$1(preference, obj);
                }
            });
        }
        Preference pref3 = SettingsFragment.INSTANCE.getPref(settingsUI, R.string.poster_size_key);
        if (pref3 != null) {
            pref3.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda2
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public final boolean onPreferenceChange(Preference preference, Object obj) {
                    return SettingsUI.onCreatePreferences$lambda$2(this.f$0, preference, obj);
                }
            });
        }
        Preference pref4 = SettingsFragment.INSTANCE.getPref(settingsUI, R.string.poster_ui_key);
        if (pref4 != null) {
            pref4.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda3
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsUI.onCreatePreferences$lambda$3(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
        Preference pref5 = SettingsFragment.INSTANCE.getPref(settingsUI, R.string.app_layout_key);
        if (pref5 != null) {
            pref5.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda4
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsUI.onCreatePreferences$lambda$4(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
        Preference pref6 = SettingsFragment.INSTANCE.getPref(settingsUI, R.string.app_theme_key);
        if (pref6 != null) {
            pref6.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda5
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsUI.onCreatePreferences$lambda$5(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
        Preference pref7 = SettingsFragment.INSTANCE.getPref(settingsUI, R.string.primary_color_key);
        if (pref7 != null) {
            pref7.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda6
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsUI.onCreatePreferences$lambda$6(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
        Preference pref8 = SettingsFragment.INSTANCE.getPref(settingsUI, R.string.pref_filter_search_quality_key);
        if (pref8 != null) {
            pref8.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda7
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsUI.onCreatePreferences$lambda$7(defaultSharedPreferences, this, preference);
                }
            });
        }
        Preference pref9 = SettingsFragment.INSTANCE.getPref(settingsUI, R.string.confirm_exit_key);
        if (pref9 != null) {
            pref9.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda8
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsUI.onCreatePreferences$lambda$8(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
    }

    static final boolean onCreatePreferences$lambda$0(Preference pref, Object obj) {
        ActivityMainBinding binding;
        FrameLayout frameLayout;
        Intrinsics.checkNotNullParameter(pref, "pref");
        Integer num = obj instanceof Integer ? (Integer) obj : null;
        if (num != null) {
            int toPx = UIHelper.INSTANCE.getToPx(num.intValue());
            CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
            Context context = pref.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            Activity activity = companion.getActivity(context);
            MainActivity mainActivity = activity instanceof MainActivity ? (MainActivity) activity : null;
            if (mainActivity != null && (binding = mainActivity.getBinding()) != null && (frameLayout = binding.homeRoot) != null) {
                frameLayout.setPadding(toPx, toPx, toPx, toPx);
            }
        }
        return true;
    }

    static final boolean onCreatePreferences$lambda$1(Preference preference, Object obj) {
        Intrinsics.checkNotNullParameter(preference, "<unused var>");
        BaseAdapterKt.clear(HomeChildItemAdapter.INSTANCE.getSharedPool());
        BaseAdapterKt.clear(ParentItemAdapter.INSTANCE.getSharedPool());
        BaseAdapterKt.clear(SearchAdapter.INSTANCE.getSharedPool());
        return true;
    }

    static final boolean onCreatePreferences$lambda$2(SettingsUI settingsUI, Preference preference, Object obj) {
        Intrinsics.checkNotNullParameter(preference, "<unused var>");
        BaseAdapterKt.clear(HomeChildItemAdapter.INSTANCE.getSharedPool());
        BaseAdapterKt.clear(ParentItemAdapter.INSTANCE.getSharedPool());
        BaseAdapterKt.clear(SearchAdapter.INSTANCE.getSharedPool());
        Context context = settingsUI.getContext();
        if (context == null) {
            return true;
        }
        HomeChildItemAdapter.INSTANCE.updatePosterSize(context, obj instanceof Integer ? (Integer) obj : null);
        return true;
    }

    static final boolean onCreatePreferences$lambda$3(SettingsUI settingsUI, final SharedPreferences sharedPreferences, final Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String[] stringArray = settingsUI.getResources().getStringArray(R.array.poster_ui_options);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        final String[] stringArray2 = settingsUI.getResources().getStringArray(R.array.poster_ui_options_values);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "getStringArray(...)");
        ArrayList arrayList = new ArrayList(stringArray2.length);
        int i = 0;
        for (String str : stringArray2) {
            arrayList.add(Boolean.valueOf(sharedPreferences.getBoolean(str, true)));
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Integer numValueOf = ((Boolean) obj).booleanValue() ? Integer.valueOf(i) : null;
            if (numValueOf != null) {
                arrayList2.add(numValueOf);
            }
            i = i2;
        }
        ArrayList arrayList3 = arrayList2;
        FragmentActivity activity = settingsUI.getActivity();
        if (activity != null) {
            List<String> list = ArraysKt.toList(stringArray);
            String string = settingsUI.getString(R.string.poster_ui_settings);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            SingleSelectionHelper.INSTANCE.showMultiDialog(activity, list, arrayList3, string, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return SettingsUI.onCreatePreferences$lambda$3$3(sharedPreferences, it, stringArray2, (List) obj2);
                }
            });
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$3$3(SharedPreferences sharedPreferences, Preference preference, String[] strArr, List list) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            editorEdit.putBoolean(strArr[i], list.contains(Integer.valueOf(i)));
        }
        editorEdit.apply();
        SearchResultBuilder.INSTANCE.updateCache(preference.getContext());
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$4(final SettingsUI settingsUI, final SharedPreferences sharedPreferences, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String[] stringArray = settingsUI.getResources().getStringArray(R.array.app_layout);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        final int[] intArray = settingsUI.getResources().getIntArray(R.array.app_layout_values);
        Intrinsics.checkNotNullExpressionValue(intArray, "getIntArray(...)");
        int i = sharedPreferences.getInt(settingsUI.getString(R.string.app_layout_key), -1);
        FragmentActivity activity = settingsUI.getActivity();
        if (activity == null) {
            return true;
        }
        List<String> list = ArraysKt.toList(stringArray);
        int iIndexOf = ArraysKt.indexOf(intArray, i);
        String string = settingsUI.getString(R.string.app_layout);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        SingleSelectionHelper.INSTANCE.showBottomDialog(activity, list, iIndexOf, string, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsUI.onCreatePreferences$lambda$4$1(sharedPreferences, settingsUI, intArray, ((Integer) obj).intValue());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$4$1(SharedPreferences sharedPreferences, SettingsUI settingsUI, int[] iArr, int i) {
        try {
            Intrinsics.checkNotNull(sharedPreferences);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt(settingsUI.getString(R.string.app_layout_key), iArr[i]);
            editorEdit.apply();
            Context context = settingsUI.getContext();
            if (context != null) {
                Globals.INSTANCE.updateTv(context);
            }
            FragmentActivity activity = settingsUI.getActivity();
            if (activity != null) {
                activity.recreate();
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$5(final SettingsUI settingsUI, final SharedPreferences sharedPreferences, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String[] stringArray = settingsUI.getResources().getStringArray(R.array.themes_names);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        final List mutableList = ArraysKt.toMutableList(stringArray);
        String[] stringArray2 = settingsUI.getResources().getStringArray(R.array.themes_names_values);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "getStringArray(...)");
        final List mutableList2 = ArraysKt.toMutableList(stringArray2);
        Function1 function1 = new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsUI.onCreatePreferences$lambda$5$0(mutableList2, mutableList, (String) obj);
            }
        };
        if (Build.VERSION.SDK_INT < 31) {
            function1.invoke("Monet");
        }
        if (Build.VERSION.SDK_INT < 29) {
            function1.invoke("System");
        }
        String string = sharedPreferences.getString(settingsUI.getString(R.string.app_theme_key), (String) CollectionsKt.first(mutableList2));
        FragmentActivity activity = settingsUI.getActivity();
        if (activity == null) {
            return true;
        }
        List<String> list = CollectionsKt.toList(mutableList);
        int iIndexOf = mutableList2.indexOf(string);
        String string2 = settingsUI.getString(R.string.app_theme_settings);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        SingleSelectionHelper.INSTANCE.showBottomDialog(activity, list, iIndexOf, string2, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsUI.onCreatePreferences$lambda$5$2(sharedPreferences, settingsUI, mutableList2, ((Integer) obj).intValue());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$5$0(List list, List list2, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        List list3 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
        int i = 0;
        int i2 = 0;
        for (Object obj : list3) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            Intrinsics.checkNotNull(str);
            Integer numValueOf = null;
            if (StringsKt.startsWith$default(str, text, false, 2, (Object) null)) {
                numValueOf = Integer.valueOf(i2);
            }
            arrayList.add(numValueOf);
            i2 = i3;
        }
        Iterator it = CollectionsKt.filterNotNull(arrayList).iterator();
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue() - i;
            list2.remove(iIntValue);
            list.remove(iIntValue);
            i++;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$5$2(SharedPreferences sharedPreferences, SettingsUI settingsUI, List list, int i) {
        try {
            Intrinsics.checkNotNull(sharedPreferences);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString(settingsUI.getString(R.string.app_theme_key), (String) list.get(i));
            editorEdit.apply();
            FragmentActivity activity = settingsUI.getActivity();
            if (activity != null) {
                activity.recreate();
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$6(final SettingsUI settingsUI, final SharedPreferences sharedPreferences, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String[] stringArray = settingsUI.getResources().getStringArray(R.array.themes_overlay_names);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        List mutableList = ArraysKt.toMutableList(stringArray);
        String[] stringArray2 = settingsUI.getResources().getStringArray(R.array.themes_overlay_names_values);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "getStringArray(...)");
        final List mutableList2 = ArraysKt.toMutableList(stringArray2);
        if (Build.VERSION.SDK_INT < 31) {
            List list = mutableList2;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            int i = 0;
            int i2 = 0;
            for (Object obj : list) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str = (String) obj;
                Intrinsics.checkNotNull(str);
                Integer numValueOf = null;
                if (StringsKt.startsWith$default(str, "Monet", false, 2, (Object) null)) {
                    numValueOf = Integer.valueOf(i2);
                }
                arrayList.add(numValueOf);
                i2 = i3;
            }
            Iterator it2 = CollectionsKt.filterNotNull(arrayList).iterator();
            while (it2.hasNext()) {
                int iIntValue = ((Number) it2.next()).intValue() - i;
                mutableList.remove(iIntValue);
                mutableList2.remove(iIntValue);
                i++;
            }
        }
        String string = sharedPreferences.getString(settingsUI.getString(R.string.primary_color_key), (String) CollectionsKt.first(mutableList2));
        FragmentActivity activity = settingsUI.getActivity();
        if (activity != null) {
            List<String> list2 = CollectionsKt.toList(mutableList);
            int iIndexOf = mutableList2.indexOf(string);
            String string2 = settingsUI.getString(R.string.primary_color_settings);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            SingleSelectionHelper.INSTANCE.showDialog(activity, list2, iIndexOf, string2, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return SettingsUI.onCreatePreferences$lambda$6$3(sharedPreferences, settingsUI, mutableList2, ((Integer) obj2).intValue());
                }
            });
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$6$3(SharedPreferences sharedPreferences, SettingsUI settingsUI, List list, int i) {
        try {
            Intrinsics.checkNotNull(sharedPreferences);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString(settingsUI.getString(R.string.primary_color_key), (String) list.get(i));
            editorEdit.apply();
            FragmentActivity activity = settingsUI.getActivity();
            if (activity != null) {
                activity.recreate();
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$7(final SharedPreferences sharedPreferences, final SettingsUI settingsUI, Preference it) {
        ArrayList arrayListEmptyList;
        Intrinsics.checkNotNullParameter(it, "it");
        List listSorted = ArraysKt.sorted(SearchQuality.values());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSorted, 10));
        Iterator it2 = listSorted.iterator();
        while (it2.hasNext()) {
            arrayList.add(((SearchQuality) it2.next()).name());
        }
        ArrayList arrayList2 = arrayList;
        Set<String> stringSet = sharedPreferences.getStringSet(settingsUI.getString(R.string.pref_filter_search_quality_key), SetsKt.emptySet());
        if (stringSet == null) {
            arrayListEmptyList = CollectionsKt.emptyList();
        } else {
            Set<String> set = stringSet;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
            for (String str : set) {
                Intrinsics.checkNotNull(str);
                arrayList3.add(Integer.valueOf(Integer.parseInt(str)));
            }
            arrayListEmptyList = arrayList3;
        }
        List<Integer> list = arrayListEmptyList;
        FragmentActivity activity = settingsUI.getActivity();
        if (activity == null) {
            return true;
        }
        String string = settingsUI.getString(R.string.pref_filter_search_quality);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        SingleSelectionHelper.INSTANCE.showMultiDialog(activity, arrayList2, list, string, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsUI.onCreatePreferences$lambda$7$3(sharedPreferences, settingsUI, (List) obj);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$7$3(SharedPreferences sharedPreferences, SettingsUI settingsUI, List selectedList) {
        Intrinsics.checkNotNullParameter(selectedList, "selectedList");
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        String string = settingsUI.getString(R.string.pref_filter_search_quality_key);
        List list = selectedList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(((Number) it.next()).intValue()));
        }
        editorEdit.putStringSet(string, CollectionsKt.toMutableSet(arrayList));
        editorEdit.apply();
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$8(final SettingsUI settingsUI, final SharedPreferences sharedPreferences, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String[] stringArray = settingsUI.getResources().getStringArray(R.array.confirm_exit);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        final int[] intArray = settingsUI.getResources().getIntArray(R.array.confirm_exit_values);
        Intrinsics.checkNotNullExpressionValue(intArray, "getIntArray(...)");
        int i = sharedPreferences.getInt(settingsUI.getString(R.string.confirm_exit_key), -1);
        FragmentActivity activity = settingsUI.getActivity();
        if (activity == null) {
            return true;
        }
        List<String> list = ArraysKt.toList(stringArray);
        int iIndexOf = ArraysKt.indexOf(intArray, i);
        String string = settingsUI.getString(R.string.confirm_before_exiting_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        SingleSelectionHelper.INSTANCE.showBottomDialog(activity, list, iIndexOf, string, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUI$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsUI.onCreatePreferences$lambda$8$1(sharedPreferences, settingsUI, intArray, ((Integer) obj).intValue());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$8$1(SharedPreferences sharedPreferences, SettingsUI settingsUI, int[] iArr, int i) {
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt(settingsUI.getString(R.string.confirm_exit_key), iArr[i]);
        editorEdit.apply();
        return Unit.INSTANCE;
    }
}
