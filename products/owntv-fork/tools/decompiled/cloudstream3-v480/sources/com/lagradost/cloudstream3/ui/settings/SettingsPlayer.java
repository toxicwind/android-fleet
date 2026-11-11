package com.lagradost.cloudstream3.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.actions.VideoClickAction;
import com.lagradost.cloudstream3.actions.VideoClickActionHolder;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.BasePreferenceFragmentCompat;
import com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment;
import com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment;
import com.lagradost.cloudstream3.utils.AtomicList;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.Qualities;
import com.lagradost.cloudstream3.utils.SingleSelectionHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: SettingsPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u001c\u0010\n\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/SettingsPlayer;", "Lcom/lagradost/cloudstream3/ui/BasePreferenceFragmentCompat;", "<init>", "()V", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "onCreatePreferences", "rootKey", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SettingsPlayer extends BasePreferenceFragmentCompat {
    @Override // com.lagradost.cloudstream3.ui.BasePreferenceFragmentCompat, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        SettingsFragment.INSTANCE.setUpToolbar(this, R.string.category_player);
        SettingsPlayer settingsPlayer = this;
        SettingsFragment.INSTANCE.setPaddingBottom(settingsPlayer);
        SettingsFragment.INSTANCE.setToolBarScrollFlags((PreferenceFragmentCompat) settingsPlayer);
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        Context context;
        final File cacheDir;
        UIHelper.INSTANCE.hideKeyboard(this);
        setPreferencesFromResource(R.xml.settings_player, rootKey);
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        SettingsPlayer settingsPlayer = this;
        SettingsFragment.INSTANCE.hidePrefs(settingsPlayer, CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.string.pref_category_gestures_key), Integer.valueOf(R.string.rotate_video_key), Integer.valueOf(R.string.auto_rotate_video_key), Integer.valueOf(R.string.speedup_key)}), 6);
        Preference pref = SettingsFragment.INSTANCE.getPref(settingsPlayer, R.string.preview_seekbar_key);
        if (pref != null) {
            SettingsFragment.INSTANCE.hideOn(pref, 2);
        }
        Preference pref2 = SettingsFragment.INSTANCE.getPref(settingsPlayer, R.string.pref_category_android_tv_key);
        if (pref2 != null) {
            SettingsFragment.INSTANCE.hideOn(pref2, 1);
        }
        Preference pref3 = SettingsFragment.INSTANCE.getPref(settingsPlayer, R.string.video_buffer_length_key);
        if (pref3 != null) {
            pref3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda3
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsPlayer.onCreatePreferences$lambda$0(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
        Preference pref4 = SettingsFragment.INSTANCE.getPref(settingsPlayer, R.string.prefer_limit_title_key);
        if (pref4 != null) {
            pref4.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda7
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsPlayer.onCreatePreferences$lambda$1(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
        Preference pref5 = SettingsFragment.INSTANCE.getPref(settingsPlayer, R.string.software_decoding_key);
        if (pref5 != null) {
            pref5.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda8
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsPlayer.onCreatePreferences$lambda$2(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
        Preference pref6 = SettingsFragment.INSTANCE.getPref(settingsPlayer, R.string.prefer_limit_show_player_info);
        if (pref6 != null) {
            pref6.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda9
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsPlayer.onCreatePreferences$lambda$3(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
        Preference pref7 = SettingsFragment.INSTANCE.getPref(settingsPlayer, R.string.hide_player_control_names_key);
        if (pref7 != null) {
            SettingsFragment.INSTANCE.hideOn(pref7, 2);
        }
        Preference pref8 = SettingsFragment.INSTANCE.getPref(settingsPlayer, R.string.quality_pref_key);
        if (pref8 != null) {
            pref8.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda10
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsPlayer.onCreatePreferences$lambda$4(defaultSharedPreferences, this, preference);
                }
            });
        }
        Preference pref9 = SettingsFragment.INSTANCE.getPref(settingsPlayer, R.string.quality_pref_mobile_data_key);
        if (pref9 != null) {
            pref9.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda12
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsPlayer.onCreatePreferences$lambda$5(defaultSharedPreferences, this, preference);
                }
            });
        }
        Preference pref10 = SettingsFragment.INSTANCE.getPref(settingsPlayer, R.string.player_default_key);
        if (pref10 != null) {
            pref10.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda13
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsPlayer.onCreatePreferences$lambda$6(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
        Preference pref11 = SettingsFragment.INSTANCE.getPref(settingsPlayer, R.string.subtitle_settings_key);
        if (pref11 != null) {
            pref11.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda14
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsPlayer.onCreatePreferences$lambda$7(this.f$0, preference);
                }
            });
        }
        Preference pref12 = SettingsFragment.INSTANCE.getPref(settingsPlayer, R.string.subtitle_settings_chromecast_key);
        if (pref12 != null) {
            pref12.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda15
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsPlayer.onCreatePreferences$lambda$8(this.f$0, preference);
                }
            });
        }
        Preference pref13 = SettingsFragment.INSTANCE.getPref(settingsPlayer, R.string.player_source_priority_key);
        if (pref13 != null) {
            pref13.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda16
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsPlayer.onCreatePreferences$lambda$9(this.f$0, preference);
                }
            });
        }
        Preference pref14 = SettingsFragment.INSTANCE.getPref(settingsPlayer, R.string.video_buffer_disk_key);
        if (pref14 != null) {
            pref14.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda4
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsPlayer.onCreatePreferences$lambda$10(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
        Preference pref15 = SettingsFragment.INSTANCE.getPref(settingsPlayer, R.string.video_buffer_size_key);
        if (pref15 != null) {
            pref15.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda5
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsPlayer.onCreatePreferences$lambda$11(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
        final Preference pref16 = SettingsFragment.INSTANCE.getPref(settingsPlayer, R.string.video_buffer_clear_key);
        if (pref16 == null || (context = getContext()) == null || (cacheDir = context.getCacheDir()) == null) {
            return;
        }
        onCreatePreferences$lambda$12$updateSummary(pref16, cacheDir);
        pref16.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda6
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return SettingsPlayer.onCreatePreferences$lambda$12$0(cacheDir, pref16, preference);
            }
        });
    }

    static final boolean onCreatePreferences$lambda$0(final SettingsPlayer settingsPlayer, final SharedPreferences sharedPreferences, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String[] stringArray = settingsPlayer.getResources().getStringArray(R.array.video_buffer_length_names);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        final int[] intArray = settingsPlayer.getResources().getIntArray(R.array.video_buffer_length_values);
        Intrinsics.checkNotNullExpressionValue(intArray, "getIntArray(...)");
        int i = sharedPreferences.getInt(settingsPlayer.getString(R.string.video_buffer_length_key), 0);
        FragmentActivity activity = settingsPlayer.getActivity();
        if (activity == null) {
            return true;
        }
        List<String> list = ArraysKt.toList(stringArray);
        int iIndexOf = ArraysKt.indexOf(intArray, i);
        String string = settingsPlayer.getString(R.string.video_buffer_length_settings);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        SingleSelectionHelper.INSTANCE.showDialog(activity, list, iIndexOf, string, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsPlayer.onCreatePreferences$lambda$0$1(sharedPreferences, settingsPlayer, intArray, ((Integer) obj).intValue());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$0$1(SharedPreferences sharedPreferences, SettingsPlayer settingsPlayer, int[] iArr, int i) {
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt(settingsPlayer.getString(R.string.video_buffer_length_key), iArr[i]);
        editorEdit.apply();
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$1(final SettingsPlayer settingsPlayer, final SharedPreferences sharedPreferences, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String[] stringArray = settingsPlayer.getResources().getStringArray(R.array.limit_title_pref_names);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        final int[] intArray = settingsPlayer.getResources().getIntArray(R.array.limit_title_pref_values);
        Intrinsics.checkNotNullExpressionValue(intArray, "getIntArray(...)");
        int i = sharedPreferences.getInt(settingsPlayer.getString(R.string.prefer_limit_title_key), 0);
        FragmentActivity activity = settingsPlayer.getActivity();
        if (activity == null) {
            return true;
        }
        List<String> list = ArraysKt.toList(stringArray);
        int iIndexOf = ArraysKt.indexOf(intArray, i);
        String string = settingsPlayer.getString(R.string.limit_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        SingleSelectionHelper.INSTANCE.showBottomDialog(activity, list, iIndexOf, string, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsPlayer.onCreatePreferences$lambda$1$1(sharedPreferences, settingsPlayer, intArray, ((Integer) obj).intValue());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$1$1(SharedPreferences sharedPreferences, SettingsPlayer settingsPlayer, int[] iArr, int i) {
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt(settingsPlayer.getString(R.string.prefer_limit_title_key), iArr[i]);
        editorEdit.apply();
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$2(final SettingsPlayer settingsPlayer, final SharedPreferences sharedPreferences, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String[] stringArray = settingsPlayer.getResources().getStringArray(R.array.software_decoding_switch);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        final int[] intArray = settingsPlayer.getResources().getIntArray(R.array.software_decoding_switch_values);
        Intrinsics.checkNotNullExpressionValue(intArray, "getIntArray(...)");
        int i = sharedPreferences.getInt(settingsPlayer.getString(R.string.software_decoding_key), -1);
        FragmentActivity activity = settingsPlayer.getActivity();
        if (activity == null) {
            return true;
        }
        List<String> list = ArraysKt.toList(stringArray);
        int iIndexOf = ArraysKt.indexOf(intArray, i);
        String string = settingsPlayer.getString(R.string.software_decoding);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        SingleSelectionHelper.INSTANCE.showBottomDialog(activity, list, iIndexOf, string, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsPlayer.onCreatePreferences$lambda$2$1(sharedPreferences, settingsPlayer, intArray, ((Integer) obj).intValue());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$2$1(SharedPreferences sharedPreferences, SettingsPlayer settingsPlayer, int[] iArr, int i) {
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt(settingsPlayer.getString(R.string.software_decoding_key), iArr[i]);
        editorEdit.apply();
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$3(SettingsPlayer settingsPlayer, final SharedPreferences sharedPreferences, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Context context = settingsPlayer.getContext();
        int i = 0;
        if (context == null) {
            return false;
        }
        String[] stringArray = settingsPlayer.getResources().getStringArray(R.array.title_info_pref_names);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        final String[] stringArray2 = settingsPlayer.getResources().getStringArray(R.array.title_info_pref_values);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "getStringArray(...)");
        Map mapMapOf = MapsKt.mapOf(TuplesKt.to(context.getString(R.string.show_name_key), true), TuplesKt.to(context.getString(R.string.show_resolution_key), true), TuplesKt.to(context.getString(R.string.show_media_info_key), false));
        ArrayList arrayList = new ArrayList(stringArray2.length);
        for (String str : stringArray2) {
            Boolean bool = (Boolean) mapMapOf.get(str);
            arrayList.add(Boolean.valueOf(sharedPreferences.getBoolean(str, bool != null ? bool.booleanValue() : false)));
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
        FragmentActivity activity = settingsPlayer.getActivity();
        if (activity != null) {
            List<String> list = ArraysKt.toList(stringArray);
            String string = settingsPlayer.getString(R.string.limit_title_rez);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            SingleSelectionHelper.INSTANCE.showMultiDialog(activity, list, arrayList3, string, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda29
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda30
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return SettingsPlayer.onCreatePreferences$lambda$3$3(sharedPreferences, stringArray2, (List) obj2);
                }
            });
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$3$3(SharedPreferences sharedPreferences, String[] strArr, List selected) {
        Intrinsics.checkNotNullParameter(selected, "selected");
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            editorEdit.putBoolean(strArr[i], selected.contains(Integer.valueOf(i)));
        }
        editorEdit.apply();
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$4(final SharedPreferences sharedPreferences, final SettingsPlayer settingsPlayer, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        EnumEntries<Qualities> entries = Qualities.getEntries();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(entries, 10));
        Iterator<Qualities> it2 = entries.iterator();
        while (it2.hasNext()) {
            arrayList.add(Integer.valueOf(it2.next().getValue()));
        }
        final List mutableList = CollectionsKt.toMutableList((Collection) CollectionsKt.reversed(arrayList));
        mutableList.remove(Integer.valueOf(Qualities.Unknown.getValue()));
        List list = mutableList;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it3 = list.iterator();
        while (it3.hasNext()) {
            arrayList2.add(Qualities.INSTANCE.getStringByInt(Integer.valueOf(((Number) it3.next()).intValue())));
        }
        ArrayList arrayList3 = arrayList2;
        int i = sharedPreferences.getInt(settingsPlayer.getString(R.string.quality_pref_key), ((Qualities) CollectionsKt.last((List) Qualities.getEntries())).getValue());
        FragmentActivity activity = settingsPlayer.getActivity();
        if (activity == null) {
            return true;
        }
        List<String> list2 = CollectionsKt.toList(arrayList3);
        int iIndexOf = mutableList.indexOf(Integer.valueOf(i));
        String string = settingsPlayer.getString(R.string.watch_quality_pref);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        SingleSelectionHelper.INSTANCE.showBottomDialog(activity, list2, iIndexOf, string, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsPlayer.onCreatePreferences$lambda$4$3(sharedPreferences, settingsPlayer, mutableList, ((Integer) obj).intValue());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$4$3(SharedPreferences sharedPreferences, SettingsPlayer settingsPlayer, List list, int i) {
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt(settingsPlayer.getString(R.string.quality_pref_key), ((Number) list.get(i)).intValue());
        editorEdit.apply();
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$5(final SharedPreferences sharedPreferences, final SettingsPlayer settingsPlayer, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        EnumEntries<Qualities> entries = Qualities.getEntries();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(entries, 10));
        Iterator<Qualities> it2 = entries.iterator();
        while (it2.hasNext()) {
            arrayList.add(Integer.valueOf(it2.next().getValue()));
        }
        final List mutableList = CollectionsKt.toMutableList((Collection) CollectionsKt.reversed(arrayList));
        mutableList.remove(Integer.valueOf(Qualities.Unknown.getValue()));
        List list = mutableList;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it3 = list.iterator();
        while (it3.hasNext()) {
            arrayList2.add(Qualities.INSTANCE.getStringByInt(Integer.valueOf(((Number) it3.next()).intValue())));
        }
        ArrayList arrayList3 = arrayList2;
        int i = sharedPreferences.getInt(settingsPlayer.getString(R.string.quality_pref_mobile_data_key), ((Qualities) CollectionsKt.last((List) Qualities.getEntries())).getValue());
        FragmentActivity activity = settingsPlayer.getActivity();
        if (activity == null) {
            return true;
        }
        List<String> list2 = CollectionsKt.toList(arrayList3);
        int iIndexOf = mutableList.indexOf(Integer.valueOf(i));
        String string = settingsPlayer.getString(R.string.watch_quality_pref_data);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        SingleSelectionHelper.INSTANCE.showBottomDialog(activity, list2, iIndexOf, string, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda24
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsPlayer.onCreatePreferences$lambda$5$3(sharedPreferences, settingsPlayer, mutableList, ((Integer) obj).intValue());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$5$3(SharedPreferences sharedPreferences, SettingsPlayer settingsPlayer, List list, int i) {
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt(settingsPlayer.getString(R.string.quality_pref_mobile_data_key), ((Number) list.get(i)).intValue());
        editorEdit.apply();
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$6(final SettingsPlayer settingsPlayer, final SharedPreferences sharedPreferences, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        AtomicList<VideoClickAction> players = VideoClickActionHolder.INSTANCE.getPlayers(settingsPlayer.getActivity());
        ArrayList arrayList = new ArrayList();
        for (VideoClickAction videoClickAction : players) {
            String strAsStringNull = videoClickAction.getName().asStringNull(settingsPlayer.getActivity());
            if (strAsStringNull == null) {
                strAsStringNull = Reflection.getOrCreateKotlinClass(videoClickAction.getClass()).getSimpleName();
            }
            Pair pair = strAsStringNull != null ? TuplesKt.to(videoClickAction, strAsStringNull) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.add(settingsPlayer.getString(R.string.player_settings_play_in_app));
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            arrayList3.add((String) ((Pair) it2.next()).component2());
        }
        listCreateListBuilder.addAll(arrayList3);
        List listBuild = CollectionsKt.build(listCreateListBuilder);
        List listCreateListBuilder2 = CollectionsKt.createListBuilder();
        listCreateListBuilder2.add("");
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it3 = arrayList2.iterator();
        while (it3.hasNext()) {
            arrayList4.add(((VideoClickAction) ((Pair) it3.next()).component1()).uniqueId());
        }
        listCreateListBuilder2.addAll(arrayList4);
        final List listBuild2 = CollectionsKt.build(listCreateListBuilder2);
        String string = sharedPreferences.getString(settingsPlayer.getString(R.string.player_default_key), "");
        String str = string != null ? string : "";
        FragmentActivity activity = settingsPlayer.getActivity();
        if (activity == null) {
            return true;
        }
        List<String> list = CollectionsKt.toList(listBuild);
        int iIndexOf = listBuild2.indexOf(str);
        String string2 = settingsPlayer.getString(R.string.player_pref);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        SingleSelectionHelper.INSTANCE.showBottomDialog(activity, list, iIndexOf, string2, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda27
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsPlayer.onCreatePreferences$lambda$6$4(sharedPreferences, settingsPlayer, listBuild2, ((Integer) obj).intValue());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$6$4(SharedPreferences sharedPreferences, SettingsPlayer settingsPlayer, List list, int i) {
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString(settingsPlayer.getString(R.string.player_default_key), (String) list.get(i));
        editorEdit.apply();
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$7(SettingsPlayer settingsPlayer, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        SubtitlesFragment.INSTANCE.push(settingsPlayer.getActivity(), false);
        return true;
    }

    static final boolean onCreatePreferences$lambda$8(SettingsPlayer settingsPlayer, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ChromecastSubtitlesFragment.INSTANCE.push(settingsPlayer.getActivity(), false);
        return true;
    }

    static final boolean onCreatePreferences$lambda$9(SettingsPlayer settingsPlayer, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Coroutines.INSTANCE.ioSafe(settingsPlayer, new SettingsPlayer$onCreatePreferences$10$1(settingsPlayer, null));
        return true;
    }

    static final boolean onCreatePreferences$lambda$10(final SettingsPlayer settingsPlayer, final SharedPreferences sharedPreferences, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String[] stringArray = settingsPlayer.getResources().getStringArray(R.array.video_buffer_size_names);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        final int[] intArray = settingsPlayer.getResources().getIntArray(R.array.video_buffer_size_values);
        Intrinsics.checkNotNullExpressionValue(intArray, "getIntArray(...)");
        int i = sharedPreferences.getInt(settingsPlayer.getString(R.string.video_buffer_disk_key), 0);
        FragmentActivity activity = settingsPlayer.getActivity();
        if (activity == null) {
            return true;
        }
        List<String> list = ArraysKt.toList(stringArray);
        int iIndexOf = ArraysKt.indexOf(intArray, i);
        String string = settingsPlayer.getString(R.string.video_buffer_disk_settings);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        SingleSelectionHelper.INSTANCE.showDialog(activity, list, iIndexOf, string, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsPlayer.onCreatePreferences$lambda$10$1(sharedPreferences, settingsPlayer, intArray, ((Integer) obj).intValue());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$10$1(SharedPreferences sharedPreferences, SettingsPlayer settingsPlayer, int[] iArr, int i) {
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt(settingsPlayer.getString(R.string.video_buffer_disk_key), iArr[i]);
        editorEdit.apply();
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$11(final SettingsPlayer settingsPlayer, final SharedPreferences sharedPreferences, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String[] stringArray = settingsPlayer.getResources().getStringArray(R.array.video_buffer_size_names);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        final int[] intArray = settingsPlayer.getResources().getIntArray(R.array.video_buffer_size_values);
        Intrinsics.checkNotNullExpressionValue(intArray, "getIntArray(...)");
        int i = sharedPreferences.getInt(settingsPlayer.getString(R.string.video_buffer_size_key), 0);
        FragmentActivity activity = settingsPlayer.getActivity();
        if (activity == null) {
            return true;
        }
        List<String> list = ArraysKt.toList(stringArray);
        int iIndexOf = ArraysKt.indexOf(intArray, i);
        String string = settingsPlayer.getString(R.string.video_buffer_size_settings);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        SingleSelectionHelper.INSTANCE.showDialog(activity, list, iIndexOf, string, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsPlayer$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsPlayer.onCreatePreferences$lambda$11$1(sharedPreferences, settingsPlayer, intArray, ((Integer) obj).intValue());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$11$1(SharedPreferences sharedPreferences, SettingsPlayer settingsPlayer, int[] iArr, int i) {
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt(settingsPlayer.getString(R.string.video_buffer_size_key), iArr[i]);
        editorEdit.apply();
        return Unit.INSTANCE;
    }

    private static final void onCreatePreferences$lambda$12$updateSummary(Preference preference, File file) {
        try {
            preference.setSummary(Formatter.formatShortFileSize(preference.getContext(), SettingsFragment.INSTANCE.getFolderSize(file)));
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreatePreferences$lambda$12$0(File file, Preference preference, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        try {
            FilesKt.deleteRecursively(file);
            onCreatePreferences$lambda$12$updateSummary(preference, file);
            return true;
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
            return true;
        }
    }
}
