package com.lagradost.cloudstream3.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.FragmentKt;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.lagradost.cloudstream3.AutoDownloadMode;
import com.lagradost.cloudstream3.BuildConfig;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.LogcatBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.network.RequestsHelperKt;
import com.lagradost.cloudstream3.services.BackupWorkManager;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BasePreferenceFragmentCompat;
import com.lagradost.cloudstream3.ui.settings.utils.DirectoryPickerKt;
import com.lagradost.cloudstream3.utils.BackupUtils;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.InAppUpdater;
import com.lagradost.cloudstream3.utils.SingleSelectionHelper;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import com.lagradost.safefile.SafeFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: SettingsUpdates.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u001c\u0010\r\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011H\u0002R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/SettingsUpdates;", "Lcom/lagradost/cloudstream3/ui/BasePreferenceFragmentCompat;", "<init>", "()V", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "pathPicker", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/net/Uri;", "onCreatePreferences", "rootKey", "", "getBackupDirsForDisplay", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SettingsUpdates extends BasePreferenceFragmentCompat {
    private final ActivityResultLauncher<Uri> pathPicker = DirectoryPickerKt.getChooseFolderLauncher(this, new Function2() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda16
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return SettingsUpdates.pathPicker$lambda$0(this.f$0, (Uri) obj, (String) obj2);
        }
    });

    @Override // com.lagradost.cloudstream3.ui.BasePreferenceFragmentCompat, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        SettingsFragment.INSTANCE.setUpToolbar(this, R.string.category_updates);
        SettingsUpdates settingsUpdates = this;
        SettingsFragment.INSTANCE.setPaddingBottom(settingsUpdates);
        SettingsFragment.INSTANCE.setToolBarScrollFlags((PreferenceFragmentCompat) settingsUpdates);
    }

    static final Unit pathPicker$lambda$0(SettingsUpdates settingsUpdates, Uri uri, String str) {
        if (uri == null) {
            return Unit.INSTANCE;
        }
        Context context = settingsUpdates.getContext();
        if (context == null && (context = CloudStreamApp.INSTANCE.getContext()) == null) {
            return Unit.INSTANCE;
        }
        if (str == null) {
            str = uri.toString();
            Intrinsics.checkNotNullExpressionValue(str, "toString(...)");
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "getDefaultSharedPreferences(...)");
        SharedPreferences.Editor editorEdit = defaultSharedPreferences.edit();
        editorEdit.putString(settingsUpdates.getString(R.string.backup_path_key), uri.toString());
        editorEdit.putString(settingsUpdates.getString(R.string.backup_dir_key), str);
        editorEdit.apply();
        return Unit.INSTANCE;
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        Preference preferenceHideOn;
        UIHelper.INSTANCE.hideKeyboard(this);
        setPreferencesFromResource(R.xml.settings_updates, rootKey);
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        SettingsUpdates settingsUpdates = this;
        Preference pref = SettingsFragment.INSTANCE.getPref(settingsUpdates, R.string.backup_key);
        if (pref != null) {
            pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda18
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsUpdates.onCreatePreferences$lambda$0(this.f$0, preference);
                }
            });
        }
        Preference pref2 = SettingsFragment.INSTANCE.getPref(settingsUpdates, R.string.automatic_backup_key);
        if (pref2 != null) {
            pref2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda20
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsUpdates.onCreatePreferences$lambda$1(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
        Preference pref3 = SettingsFragment.INSTANCE.getPref(settingsUpdates, R.string.redo_setup_key);
        if (pref3 != null) {
            pref3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda21
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsUpdates.onCreatePreferences$lambda$2(this.f$0, preference);
                }
            });
        }
        Preference pref4 = SettingsFragment.INSTANCE.getPref(settingsUpdates, R.string.restore_key);
        if (pref4 != null) {
            pref4.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda22
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsUpdates.onCreatePreferences$lambda$3(this.f$0, preference);
                }
            });
        }
        Preference pref5 = SettingsFragment.INSTANCE.getPref(settingsUpdates, R.string.backup_path_key);
        if (pref5 != null && (preferenceHideOn = SettingsFragment.INSTANCE.hideOn(pref5, 4)) != null) {
            preferenceHideOn.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda23
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsUpdates.onCreatePreferences$lambda$4(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
        Preference pref6 = SettingsFragment.INSTANCE.getPref(settingsUpdates, R.string.show_logcat_key);
        if (pref6 != null) {
            pref6.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda24
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsUpdates.onCreatePreferences$lambda$5(this.f$0, preference);
                }
            });
        }
        Preference pref7 = SettingsFragment.INSTANCE.getPref(settingsUpdates, R.string.apk_installer_key);
        if (pref7 != null) {
            pref7.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda1
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsUpdates.onCreatePreferences$lambda$6(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
        Preference pref8 = SettingsFragment.INSTANCE.getPref(settingsUpdates, R.string.manual_check_update_key);
        if (pref8 != null) {
            pref8.setSummary(BuildConfig.VERSION_NAME);
            pref8.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda2
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsUpdates.onCreatePreferences$lambda$7$0(this.f$0, preference);
                }
            });
        }
        Preference pref9 = SettingsFragment.INSTANCE.getPref(settingsUpdates, R.string.install_prerelease_key);
        if (pref9 != null) {
            pref9.setVisible(true);
            pref9.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda3
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsUpdates.onCreatePreferences$lambda$8$0(this.f$0, preference);
                }
            });
        }
        Preference pref10 = SettingsFragment.INSTANCE.getPref(settingsUpdates, R.string.auto_download_plugins_key);
        if (pref10 != null) {
            pref10.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda4
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsUpdates.onCreatePreferences$lambda$9(this.f$0, defaultSharedPreferences, preference);
                }
            });
        }
        Preference pref11 = SettingsFragment.INSTANCE.getPref(settingsUpdates, R.string.manual_update_plugins_key);
        if (pref11 != null) {
            pref11.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda19
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsUpdates.onCreatePreferences$lambda$10(this.f$0, preference);
                }
            });
        }
    }

    static final boolean onCreatePreferences$lambda$0(SettingsUpdates settingsUpdates, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        BackupUtils.INSTANCE.backup(settingsUpdates.getActivity());
        return true;
    }

    static final boolean onCreatePreferences$lambda$1(final SettingsUpdates settingsUpdates, final SharedPreferences sharedPreferences, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String[] stringArray = settingsUpdates.getResources().getStringArray(R.array.periodic_work_names);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        final int[] intArray = settingsUpdates.getResources().getIntArray(R.array.periodic_work_values);
        Intrinsics.checkNotNullExpressionValue(intArray, "getIntArray(...)");
        int i = sharedPreferences.getInt(settingsUpdates.getString(R.string.automatic_backup_key), 0);
        FragmentActivity activity = settingsUpdates.getActivity();
        if (activity == null) {
            return true;
        }
        List<String> list = ArraysKt.toList(stringArray);
        int iIndexOf = ArraysKt.indexOf(intArray, i);
        String string = settingsUpdates.getString(R.string.backup_frequency);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        SingleSelectionHelper.INSTANCE.showDialog(activity, list, iIndexOf, string, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsUpdates.onCreatePreferences$lambda$1$1(sharedPreferences, settingsUpdates, intArray, ((Integer) obj).intValue());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$1$1(SharedPreferences sharedPreferences, SettingsUpdates settingsUpdates, int[] iArr, int i) {
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt(settingsUpdates.getString(R.string.automatic_backup_key), iArr[i]);
        editorEdit.apply();
        BackupWorkManager.Companion companion = BackupWorkManager.INSTANCE;
        Context context = settingsUpdates.getContext();
        if (context == null) {
            context = CloudStreamApp.INSTANCE.getContext();
        }
        companion.enqueuePeriodicWork(context, iArr[i]);
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$2(SettingsUpdates settingsUpdates, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        FragmentKt.findNavController(settingsUpdates).navigate(R.id.navigation_setup_language);
        return true;
    }

    static final boolean onCreatePreferences$lambda$3(SettingsUpdates settingsUpdates, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        FragmentActivity activity = settingsUpdates.getActivity();
        if (activity == null) {
            return true;
        }
        BackupUtils.INSTANCE.restorePrompt(activity);
        return true;
    }

    static final boolean onCreatePreferences$lambda$4(final SettingsUpdates settingsUpdates, final SharedPreferences sharedPreferences, Preference it) {
        SafeFile defaultBackupDir;
        Intrinsics.checkNotNullParameter(it, "it");
        final List<String> backupDirsForDisplay = settingsUpdates.getBackupDirsForDisplay();
        String str = null;
        String string = sharedPreferences.getString(settingsUpdates.getString(R.string.backup_dir_key), null);
        if (string != null) {
            str = string;
        } else {
            Context context = settingsUpdates.getContext();
            if (context != null && (defaultBackupDir = BackupUtils.INSTANCE.getDefaultBackupDir(context)) != null) {
                string = defaultBackupDir.filePath();
                str = string;
            }
        }
        FragmentActivity activity = settingsUpdates.getActivity();
        if (activity == null) {
            return true;
        }
        List<String> listPlus = CollectionsKt.plus((Collection) backupDirsForDisplay, (Iterable) CollectionsKt.listOf(settingsUpdates.getString(R.string.custom)));
        int iIndexOf = CollectionsKt.indexOf((List<? extends String>) backupDirsForDisplay, str);
        String string2 = settingsUpdates.getString(R.string.backup_path_title);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        SingleSelectionHelper.INSTANCE.showBottomDialog(activity, listPlus, iIndexOf, string2, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsUpdates.onCreatePreferences$lambda$4$2(backupDirsForDisplay, settingsUpdates, sharedPreferences, ((Integer) obj).intValue());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$4$2(List list, SettingsUpdates settingsUpdates, SharedPreferences sharedPreferences, int i) {
        if (i == list.size()) {
            try {
                settingsUpdates.pathPicker.launch(Uri.EMPTY);
            } catch (Exception e) {
                ArchComponentExtKt.logError(e);
            }
        } else {
            Intrinsics.checkNotNull(sharedPreferences);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString(settingsUpdates.getString(R.string.backup_path_key), (String) list.get(i));
            editorEdit.putString(settingsUpdates.getString(R.string.backup_dir_key), (String) list.get(i));
            editorEdit.apply();
        }
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$5(final SettingsUpdates settingsUpdates, Preference pref) {
        Intrinsics.checkNotNullParameter(pref, "pref");
        AlertDialog.Builder builder = new AlertDialog.Builder(pref.getContext(), R.style.AlertDialogCustom);
        LogcatBinding logcatBindingInflate = LogcatBinding.inflate(settingsUpdates.getLayoutInflater(), null, false);
        Intrinsics.checkNotNullExpressionValue(logcatBindingInflate, "inflate(...)");
        builder.setView(logcatBindingInflate.getRoot());
        final AlertDialog alertDialogCreate = builder.create();
        Intrinsics.checkNotNullExpressionValue(alertDialogCreate, "create(...)");
        alertDialogCreate.show();
        final ArrayList arrayList = new ArrayList();
        try {
            Iterator<String> it = TextStreamsKt.lineSequence(new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("logcat -d").getInputStream()))).iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
        LogcatAdapter logcatAdapter = new LogcatAdapter();
        BaseAdapter.submitList$default(logcatAdapter, arrayList, null, 2, null);
        logcatBindingInflate.logcatRecyclerView.setLayoutManager(new LinearLayoutManager(pref.getContext()));
        logcatBindingInflate.logcatRecyclerView.setAdapter(logcatAdapter);
        logcatBindingInflate.copyBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsUpdates.onCreatePreferences$lambda$5$2(arrayList, alertDialogCreate, settingsUpdates, view);
            }
        });
        logcatBindingInflate.clearBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                SettingsUpdates.onCreatePreferences$lambda$5$3(alertDialogCreate, settingsUpdates, view);
            }
        });
        logcatBindingInflate.saveBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsUpdates.onCreatePreferences$lambda$5$4(alertDialogCreate, settingsUpdates, arrayList, view);
            }
        });
        logcatBindingInflate.closeBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsUpdates.onCreatePreferences$lambda$5$5(alertDialogCreate, settingsUpdates, view);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreatePreferences$lambda$5$2(List list, AlertDialog alertDialog, SettingsUpdates settingsUpdates, View view) {
        UIHelper.INSTANCE.clipboardHelper(TextUtilKt.txt("Logcat"), CollectionsKt.joinToString$default(list, "\n", null, null, 0, null, null, 62, null));
        UIHelper.INSTANCE.dismissSafe(alertDialog, settingsUpdates.getActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreatePreferences$lambda$5$3(AlertDialog alertDialog, SettingsUpdates settingsUpdates, View view) throws IOException {
        Runtime.getRuntime().exec("logcat -c");
        UIHelper.INSTANCE.dismissSafe(alertDialog, settingsUpdates.getActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreatePreferences$lambda$5$4(AlertDialog alertDialog, SettingsUpdates settingsUpdates, List list, View view) {
        String str = new SimpleDateFormat("yyyy_MM_dd_HH_mm", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
        try {
            VideoDownloadManager videoDownloadManager = VideoDownloadManager.INSTANCE;
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(videoDownloadManager.setupStream(context, "logcat_" + str, (String) null, "txt", false).openNew(), Charsets.UTF_8);
            try {
                outputStreamWriter.write(CollectionsKt.joinToString$default(list, "\n", null, null, 0, null, null, 62, null));
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(outputStreamWriter, null);
                UIHelper.INSTANCE.dismissSafe(alertDialog, settingsUpdates.getActivity());
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(outputStreamWriter, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            ArchComponentExtKt.logError(th3);
            CommonActivity.showToast$default(CommonActivity.INSTANCE, th3.getMessage(), (Integer) null, 2, (Object) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreatePreferences$lambda$5$5(AlertDialog alertDialog, SettingsUpdates settingsUpdates, View view) {
        UIHelper.INSTANCE.dismissSafe(alertDialog, settingsUpdates.getActivity());
    }

    static final boolean onCreatePreferences$lambda$6(final SettingsUpdates settingsUpdates, final SharedPreferences sharedPreferences, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String[] stringArray = settingsUpdates.getResources().getStringArray(R.array.apk_installer_pref);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        final int[] intArray = settingsUpdates.getResources().getIntArray(R.array.apk_installer_values);
        Intrinsics.checkNotNullExpressionValue(intArray, "getIntArray(...)");
        int i = sharedPreferences.getInt(settingsUpdates.getString(R.string.apk_installer_key), 1);
        FragmentActivity activity = settingsUpdates.getActivity();
        if (activity != null) {
            List<String> list = ArraysKt.toList(stringArray);
            int iIndexOf = ArraysKt.indexOf(intArray, i);
            String string = settingsUpdates.getString(R.string.apk_installer_settings);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            SingleSelectionHelper.INSTANCE.showBottomDialog(activity, list, iIndexOf, string, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SettingsUpdates.onCreatePreferences$lambda$6$1(sharedPreferences, settingsUpdates, intArray, ((Integer) obj).intValue());
                }
            });
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$6$1(SharedPreferences sharedPreferences, SettingsUpdates settingsUpdates, int[] iArr, int i) {
        try {
            Intrinsics.checkNotNull(sharedPreferences);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt(settingsUpdates.getString(R.string.apk_installer_key), iArr[i]);
            editorEdit.apply();
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreatePreferences$lambda$7$0(SettingsUpdates settingsUpdates, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Coroutines.INSTANCE.ioSafe(settingsUpdates, new SettingsUpdates$onCreatePreferences$8$1$1(settingsUpdates, null));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreatePreferences$lambda$8$0(SettingsUpdates settingsUpdates, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        FragmentActivity activity = settingsUpdates.getActivity();
        if (activity == null) {
            return true;
        }
        InAppUpdater.INSTANCE.installPreReleaseIfNeeded(activity);
        return true;
    }

    static final boolean onCreatePreferences$lambda$9(final SettingsUpdates settingsUpdates, final SharedPreferences sharedPreferences, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String[] stringArray = settingsUpdates.getResources().getStringArray(R.array.auto_download_plugin);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        List listSortedWith = ArraysKt.sortedWith(AutoDownloadMode.values(), new Comparator() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$onCreatePreferences$lambda$9$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((AutoDownloadMode) t).getValue()), Integer.valueOf(((AutoDownloadMode) t2).getValue()));
            }
        });
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSortedWith, 10));
        Iterator it2 = listSortedWith.iterator();
        while (it2.hasNext()) {
            arrayList.add(Integer.valueOf(((AutoDownloadMode) it2.next()).getValue()));
        }
        final ArrayList arrayList2 = arrayList;
        int i = sharedPreferences.getInt(settingsUpdates.getString(R.string.auto_download_plugins_key), 0);
        FragmentActivity activity = settingsUpdates.getActivity();
        if (activity == null) {
            return true;
        }
        List<String> list = ArraysKt.toList(stringArray);
        int iIndexOf = arrayList2.indexOf(Integer.valueOf(i));
        String string = settingsUpdates.getString(R.string.automatic_plugin_download_mode_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        SingleSelectionHelper.INSTANCE.showBottomDialog(activity, list, iIndexOf, string, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsUpdates.onCreatePreferences$lambda$9$3(sharedPreferences, settingsUpdates, arrayList2, ((Integer) obj).intValue());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreatePreferences$lambda$9$3(SharedPreferences sharedPreferences, SettingsUpdates settingsUpdates, List list, int i) {
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt(settingsUpdates.getString(R.string.auto_download_plugins_key), ((Number) list.get(i)).intValue());
        editorEdit.apply();
        Context context = settingsUpdates.getContext();
        if (context == null) {
            context = CloudStreamApp.INSTANCE.getContext();
        }
        if (context != null) {
            RequestsHelperKt.initClient(MainActivityKt.getApp(), context);
        }
        return Unit.INSTANCE;
    }

    static final boolean onCreatePreferences$lambda$10(SettingsUpdates settingsUpdates, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Coroutines.INSTANCE.ioSafe(settingsUpdates, new SettingsUpdates$onCreatePreferences$11$1(settingsUpdates, null));
        return true;
    }

    private final List<String> getBackupDirsForDisplay() {
        List<String> list = (List) ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SettingsUpdates.getBackupDirsForDisplay$lambda$0(this.f$0);
            }
        });
        return list == null ? CollectionsKt.emptyList() : list;
    }

    static final List getBackupDirsForDisplay$lambda$0(SettingsUpdates settingsUpdates) {
        Object objM8936constructorimpl;
        String second;
        Context context = settingsUpdates.getContext();
        if (context == null) {
            return null;
        }
        SafeFile defaultBackupDir = BackupUtils.INSTANCE.getDefaultBackupDir(context);
        List listListOf = CollectionsKt.listOf(defaultBackupDir != null ? defaultBackupDir.filePath() : null);
        try {
            Result.Companion companion = Result.INSTANCE;
            List list = listListOf;
            Pair<SafeFile, String> currentBackupDir = BackupUtils.INSTANCE.getCurrentBackupDir(context);
            SafeFile first = currentBackupDir.getFirst();
            if (first == null || (second = first.filePath()) == null) {
                second = currentBackupDir.getSecond();
            }
            objM8936constructorimpl = Result.m8936constructorimpl(CollectionsKt.plus((Collection<? extends String>) list, second));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        List list2 = (List) (Result.m8942isFailureimpl(objM8936constructorimpl) ? null : objM8936constructorimpl);
        if (list2 != null) {
            listListOf = list2;
        }
        return CollectionsKt.distinct(CollectionsKt.filterNotNull(listListOf));
    }
}
