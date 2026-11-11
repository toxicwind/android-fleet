package com.lagradost.cloudstream3.ui.settings;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.OnBackPressedDispatcher;
import androidx.core.view.ViewGroupKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.recyclerview.widget.RecyclerView;
import coil3.Image;
import coil3.request.ImageRequest;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.lagradost.cloudstream3.BuildConfig;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.MainSettingsBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.syncproviders.AccountManager;
import com.lagradost.cloudstream3.syncproviders.AuthRepo;
import com.lagradost.cloudstream3.syncproviders.AuthUser;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.ui.home.HomeFragment;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.GitInfo;
import com.lagradost.cloudstream3.utils.ImageLoader;
import com.lagradost.cloudstream3.utils.ImageUtilKt;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import j$.util.DesugarTimeZone;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SettingsFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000bB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0002H\u0016¨\u0006\f"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/SettingsFragment;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/MainSettingsBinding;", "<init>", "()V", "fixLayout", "", "view", "Landroid/view/View;", "onBindingCreated", "binding", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SettingsFragment extends BaseFragment<MainSettingsBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.settings.SettingsFragment$1, reason: invalid class name */
    /* JADX INFO: compiled from: SettingsFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, MainSettingsBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, MainSettingsBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/MainSettingsBinding;", 0);
        }

        public final MainSettingsBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return MainSettingsBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ MainSettingsBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public SettingsFragment() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
    }

    /* JADX INFO: compiled from: SettingsFragment.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bJ\"\u0010\t\u001a\u00020\n*\u0004\u0018\u00010\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\r\u001a\u00020\bJ\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u0005*\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\bJ\n\u0010\u000f\u001a\u00020\n*\u00020\u0006J\n\u0010\u0010\u001a\u00020\n*\u00020\u0006J\f\u0010\u0010\u001a\u00020\n*\u0004\u0018\u00010\u0011J\u0014\u0010\u0012\u001a\u00020\n*\u0004\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010\u0012\u001a\u00020\n*\u0004\u0018\u00010\u00112\b\b\u0001\u0010\u0013\u001a\u00020\bJ\n\u0010\u0015\u001a\u00020\n*\u00020\u0011J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019¨\u0006\u001a"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/SettingsFragment$Companion;", "", "<init>", "()V", "getPref", "Landroidx/preference/Preference;", "Landroidx/preference/PreferenceFragmentCompat;", "id", "", "hidePrefs", "", "ids", "", "layoutFlags", "hideOn", "setPaddingBottom", "setToolBarScrollFlags", "Landroidx/fragment/app/Fragment;", "setUpToolbar", "title", "", "setSystemBarsPadding", "getFolderSize", "", "dir", "Ljava/io/File;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Preference getPref(PreferenceFragmentCompat preferenceFragmentCompat, int i) {
            if (preferenceFragmentCompat == null) {
                return null;
            }
            try {
                return preferenceFragmentCompat.findPreference(preferenceFragmentCompat.getString(i));
            } catch (Exception e) {
                ArchComponentExtKt.logError(e);
                return null;
            }
        }

        public final void hidePrefs(PreferenceFragmentCompat preferenceFragmentCompat, List<Integer> ids, int i) {
            Intrinsics.checkNotNullParameter(ids, "ids");
            if (preferenceFragmentCompat == null) {
                return;
            }
            try {
                Iterator<T> it = ids.iterator();
                while (it.hasNext()) {
                    Preference pref = SettingsFragment.INSTANCE.getPref(preferenceFragmentCompat, ((Number) it.next()).intValue());
                    if (pref != null) {
                        pref.setVisible(!Globals.INSTANCE.isLayout(i));
                    }
                }
            } catch (Exception e) {
                ArchComponentExtKt.logError(e);
            }
        }

        public final Preference hideOn(Preference preference, int i) {
            if (preference == null) {
                return null;
            }
            preference.setVisible(!Globals.INSTANCE.isLayout(i));
            if (preference.isVisible()) {
                return preference;
            }
            return null;
        }

        public final void setPaddingBottom(PreferenceFragmentCompat preferenceFragmentCompat) {
            RecyclerView listView;
            Intrinsics.checkNotNullParameter(preferenceFragmentCompat, "<this>");
            if (!Globals.INSTANCE.isLayout(6) || (listView = preferenceFragmentCompat.getListView()) == null) {
                return;
            }
            listView.setPadding(0, 0, 0, UIHelper.INSTANCE.getToPx(100));
        }

        public final void setToolBarScrollFlags(PreferenceFragmentCompat preferenceFragmentCompat) {
            Intrinsics.checkNotNullParameter(preferenceFragmentCompat, "<this>");
            if (Globals.INSTANCE.isLayout(6)) {
                View view = preferenceFragmentCompat.getView();
                MaterialToolbar materialToolbar = view != null ? (MaterialToolbar) view.findViewById(R.id.settings_toolbar) : null;
                if (materialToolbar != null) {
                    MaterialToolbar materialToolbar2 = materialToolbar;
                    ViewGroup.LayoutParams layoutParams = materialToolbar2.getLayoutParams();
                    if (layoutParams != null) {
                        AppBarLayout.LayoutParams layoutParams2 = (AppBarLayout.LayoutParams) layoutParams;
                        layoutParams2.setScrollFlags(0);
                        materialToolbar2.setLayoutParams(layoutParams2);
                        return;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type com.google.android.material.appbar.AppBarLayout.LayoutParams");
                }
            }
        }

        public final void setToolBarScrollFlags(Fragment fragment) {
            View view;
            if (Globals.INSTANCE.isLayout(6)) {
                MaterialToolbar materialToolbar = (fragment == null || (view = fragment.getView()) == null) ? null : (MaterialToolbar) view.findViewById(R.id.settings_toolbar);
                if (materialToolbar != null) {
                    MaterialToolbar materialToolbar2 = materialToolbar;
                    ViewGroup.LayoutParams layoutParams = materialToolbar2.getLayoutParams();
                    if (layoutParams != null) {
                        AppBarLayout.LayoutParams layoutParams2 = (AppBarLayout.LayoutParams) layoutParams;
                        layoutParams2.setScrollFlags(0);
                        materialToolbar2.setLayoutParams(layoutParams2);
                        return;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type com.google.android.material.appbar.AppBarLayout.LayoutParams");
                }
            }
        }

        public final void setUpToolbar(final Fragment fragment, String title) {
            View view;
            MaterialToolbar materialToolbar;
            Intrinsics.checkNotNullParameter(title, "title");
            if (fragment == null || (view = fragment.getView()) == null || (materialToolbar = (MaterialToolbar) view.findViewById(R.id.settings_toolbar)) == null) {
                return;
            }
            materialToolbar.setTitle(title);
            if (Globals.INSTANCE.isLayout(5)) {
                materialToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
                materialToolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsFragment$Companion$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        SettingsFragment.Companion.setUpToolbar$lambda$0$0(fragment, view2);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void setUpToolbar$lambda$0$0(Fragment fragment, View view) {
            OnBackPressedDispatcher onBackPressedDispatcher;
            FragmentActivity activity = fragment.getActivity();
            if (activity == null || (onBackPressedDispatcher = activity.getOnBackPressedDispatcher()) == null) {
                return;
            }
            onBackPressedDispatcher.onBackPressed();
        }

        public final void setUpToolbar(final Fragment fragment, int i) {
            View view;
            MaterialToolbar materialToolbar;
            View next;
            if (fragment == null || (view = fragment.getView()) == null || (materialToolbar = (MaterialToolbar) view.findViewById(R.id.settings_toolbar)) == null) {
                return;
            }
            materialToolbar.setTitle(i);
            if (Globals.INSTANCE.isLayout(5)) {
                materialToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
                Iterator<View> it = ViewGroupKt.getChildren(materialToolbar).iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!(next instanceof ImageView));
                View view2 = next;
                if (view2 != null) {
                    view2.setTag(fragment.getString(R.string.tv_no_focus_tag));
                }
                materialToolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsFragment$Companion$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view3) {
                        SettingsFragment.Companion.setUpToolbar$lambda$1$1(fragment, view3);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void setUpToolbar$lambda$1$1(final Fragment fragment, View view) {
            ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsFragment$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SettingsFragment.Companion.setUpToolbar$lambda$1$1$0(fragment);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit setUpToolbar$lambda$1$1$0(Fragment fragment) {
            OnBackPressedDispatcher onBackPressedDispatcher;
            FragmentActivity activity = fragment.getActivity();
            if (activity == null || (onBackPressedDispatcher = activity.getOnBackPressedDispatcher()) == null) {
                return null;
            }
            onBackPressedDispatcher.onBackPressed();
            return Unit.INSTANCE;
        }

        public final void setSystemBarsPadding(Fragment fragment) {
            Intrinsics.checkNotNullParameter(fragment, "<this>");
            View view = fragment.getView();
            if (view != null) {
                UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, Globals.INSTANCE.isLandscape(), Globals.INSTANCE.isLayout(6), false, false, false, 462, null);
            }
        }

        public final long getFolderSize(File dir) {
            long folderSize;
            Intrinsics.checkNotNullParameter(dir, "dir");
            File[] fileArrListFiles = dir.listFiles();
            long j = 0;
            if (fileArrListFiles != null) {
                for (File file : fileArrListFiles) {
                    if (file.isFile()) {
                        folderSize = file.length();
                    } else {
                        Companion companion = SettingsFragment.INSTANCE;
                        Intrinsics.checkNotNull(file);
                        folderSize = companion.getFolderSize(file);
                    }
                    j += folderSize;
                }
            }
            return j;
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, Globals.INSTANCE.isLandscape(), Globals.INSTANCE.isLayout(6), false, false, false, 462, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$navigate(SettingsFragment settingsFragment, int i) {
        FragmentActivity activity = settingsFragment.getActivity();
        if (activity != null) {
            UIHelper.navigate$default(UIHelper.INSTANCE, activity, i, new Bundle(), null, 4, null);
        }
    }

    private static final boolean onBindingCreated$hasProfilePictureFromAccountManagers(MainSettingsBinding mainSettingsBinding, final SettingsFragment settingsFragment, AuthRepo[] authRepoArr) {
        String profilePicture;
        for (AuthRepo authRepo : authRepoArr) {
            AuthUser authUser = authRepo.authUser();
            if (authUser != null && (profilePicture = authUser.getProfilePicture()) != null) {
                ImageView imageView = mainSettingsBinding.settingsProfilePic;
                ImageLoader imageLoader = ImageLoader.INSTANCE;
                Intrinsics.checkNotNull(imageView);
                ImageLoader.loadImage$default(imageLoader, imageView, profilePicture, (Map) null, new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsFragment$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsFragment.onBindingCreated$hasProfilePictureFromAccountManagers$lambda$0$0(this.f$0, (ImageRequest.Builder) obj);
                    }
                }, 2, (Object) null);
                mainSettingsBinding.settingsProfileText.setText(authUser.getName());
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$hasProfilePictureFromAccountManagers$lambda$0$0(final SettingsFragment settingsFragment, ImageRequest.Builder loadImage) {
        Intrinsics.checkNotNullParameter(loadImage, "$this$loadImage");
        loadImage.error(new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsFragment.onBindingCreated$hasProfilePictureFromAccountManagers$lambda$0$0$0(this.f$0, (ImageRequest) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Image onBindingCreated$hasProfilePictureFromAccountManagers$lambda$0$0$0(SettingsFragment settingsFragment, ImageRequest it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Context context = settingsFragment.getContext();
        if (context == null) {
            return null;
        }
        return ImageUtilKt.getImageFromDrawable(context, HomeFragment.INSTANCE.getErrorProfilePic());
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(MainSettingsBinding binding) {
        final String strCurrentCommitHash;
        DataStoreHelper.Account defaultAccount;
        Intrinsics.checkNotNullParameter(binding, "binding");
        if (!onBindingCreated$hasProfilePictureFromAccountManagers(binding, this, AccountManager.INSTANCE.getAllApis())) {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                return;
            }
            try {
                DataStoreHelper.Account[] accounts = DataStoreHelper.INSTANCE.getAccounts();
                int length = accounts.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        defaultAccount = null;
                        break;
                    }
                    defaultAccount = accounts[i];
                    if (defaultAccount.getKeyIndex() == DataStoreHelper.INSTANCE.getSelectedKeyIndex()) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (defaultAccount == null) {
                    defaultAccount = DataStoreHelper.INSTANCE.getDefaultAccount(activity);
                }
            } catch (IllegalStateException e) {
                Log.e("AccountManager", "Activity not found", e);
                defaultAccount = null;
            }
            ImageLoader imageLoader = ImageLoader.INSTANCE;
            ImageView settingsProfilePic = binding.settingsProfilePic;
            Intrinsics.checkNotNullExpressionValue(settingsProfilePic, "settingsProfilePic");
            ImageLoader.loadImage$default(imageLoader, settingsProfilePic, defaultAccount != null ? defaultAccount.getImage() : null, (Function1) null, 2, (Object) null);
            binding.settingsProfileText.setText(defaultAccount != null ? defaultAccount.getName() : null);
        }
        for (Pair pair : CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(binding.settingsGeneral, Integer.valueOf(R.id.action_navigation_global_to_navigation_settings_general)), TuplesKt.to(binding.settingsPlayer, Integer.valueOf(R.id.action_navigation_global_to_navigation_settings_player)), TuplesKt.to(binding.settingsCredits, Integer.valueOf(R.id.action_navigation_global_to_navigation_settings_account)), TuplesKt.to(binding.settingsUi, Integer.valueOf(R.id.action_navigation_global_to_navigation_settings_ui)), TuplesKt.to(binding.settingsProviders, Integer.valueOf(R.id.action_navigation_global_to_navigation_settings_providers)), TuplesKt.to(binding.settingsUpdates, Integer.valueOf(R.id.action_navigation_global_to_navigation_settings_updates)), TuplesKt.to(binding.settingsExtensions, Integer.valueOf(R.id.action_navigation_global_to_navigation_settings_extensions))})) {
            Object objComponent1 = pair.component1();
            Intrinsics.checkNotNullExpressionValue(objComponent1, "component1(...)");
            TextView textView = (TextView) objComponent1;
            final int iIntValue = ((Number) pair.component2()).intValue();
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SettingsFragment.onBindingCreated$navigate(this, iIntValue);
                }
            });
            if (Globals.INSTANCE.isLayout(2)) {
                textView.setFocusable(true);
                textView.setFocusableInTouchMode(true);
            }
        }
        if (Globals.INSTANCE.isLayout(2)) {
            binding.settingsGeneral.requestFocus();
        }
        FragmentActivity activity2 = getActivity();
        if (activity2 == null || (strCurrentCommitHash = GitInfo.INSTANCE.currentCommitHash(activity2)) == null) {
            strCurrentCommitHash = "";
        }
        DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance(1, 2, Locale.getDefault());
        dateTimeInstance.setTimeZone(DesugarTimeZone.getTimeZone("UTC"));
        String str = dateTimeInstance.format(new Date(BuildConfig.BUILD_DATE));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        final String strReplace$default = StringsKt.replace$default(str, "UTC", "", false, 4, (Object) null);
        TextView textView2 = binding.appVersion;
        final String str2 = BuildConfig.VERSION_NAME;
        textView2.setText(BuildConfig.VERSION_NAME);
        binding.buildDate.setText(strReplace$default);
        binding.commitHash.setText(strCurrentCommitHash);
        binding.appVersionInfo.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return SettingsFragment.onBindingCreated$lambda$5(str2, strCurrentCommitHash, strReplace$default, view);
            }
        });
    }

    static final boolean onBindingCreated$lambda$5(String str, String str2, String str3, View view) {
        UIHelper.INSTANCE.clipboardHelper(TextUtilKt.txt(R.string.extension_version, new Object[0]), str + ' ' + str2 + ' ' + str3);
        return true;
    }
}
