package com.lagradost.cloudstream3.ui.setup;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.ContextCompat;
import androidx.navigation.fragment.FragmentKt;
import androidx.preference.PreferenceManager;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.FragmentSetupLanguageBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.plugins.PluginManager;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.ui.settings.SettingsGeneralKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SetupFragmentLanguage.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0002H\u0016¨\u0006\u000b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/setup/SetupFragmentLanguage;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentSetupLanguageBinding;", "<init>", "()V", "fixLayout", "", "view", "Landroid/view/View;", "onBindingCreated", "binding", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SetupFragmentLanguage extends BaseFragment<FragmentSetupLanguageBinding> {

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.setup.SetupFragmentLanguage$1, reason: invalid class name */
    /* JADX INFO: compiled from: SetupFragmentLanguage.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, FragmentSetupLanguageBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, FragmentSetupLanguageBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/FragmentSetupLanguageBinding;", 0);
        }

        public final FragmentSetupLanguageBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentSetupLanguageBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ FragmentSetupLanguageBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public SetupFragmentLanguage() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, false, false, false, false, false, TypedValues.PositionType.TYPE_POSITION_TYPE, null);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final FragmentSetupLanguageBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentLanguage$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SetupFragmentLanguage.onBindingCreated$lambda$0(this.f$0, binding);
            }
        });
    }

    static final Unit onBindingCreated$lambda$0(final SetupFragmentLanguage setupFragmentLanguage, final FragmentSetupLanguageBinding fragmentSetupLanguageBinding) {
        final Context context = setupFragmentLanguage.getContext();
        if (context == null) {
            return Unit.INSTANCE;
        }
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.sort_bottom_single_choice);
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentLanguage$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SetupFragmentLanguage.onBindingCreated$lambda$0$0$0(fragmentSetupLanguageBinding, context);
            }
        });
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
        int iIndexOf = arrayList2.indexOf(currentLocale);
        arrayAdapter.addAll(arrayList3);
        fragmentSetupLanguageBinding.listview1.setAdapter((ListAdapter) arrayAdapter);
        fragmentSetupLanguageBinding.listview1.setChoiceMode(1);
        fragmentSetupLanguageBinding.listview1.setItemChecked(iIndexOf, true);
        fragmentSetupLanguageBinding.listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentLanguage$$ExternalSyntheticLambda1
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                SetupFragmentLanguage.onBindingCreated$lambda$0$0$3(arrayList2, setupFragmentLanguage, defaultSharedPreferences, adapterView, view, i, j);
            }
        });
        fragmentSetupLanguageBinding.nextBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentLanguage$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetupFragmentLanguage.onBindingCreated$lambda$0$0$4(this.f$0, view);
            }
        });
        fragmentSetupLanguageBinding.skipBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentLanguage$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetupFragmentLanguage.onBindingCreated$lambda$0$0$5(this.f$0, view);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$0$0$0(FragmentSetupLanguageBinding fragmentSetupLanguageBinding, Context context) {
        fragmentSetupLanguageBinding.appIconImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.cloud_2_gradient));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$0$3(List list, SetupFragmentLanguage setupFragmentLanguage, SharedPreferences sharedPreferences, AdapterView adapterView, View view, int i, long j) {
        String str = (String) list.get(i);
        CommonActivity.INSTANCE.setLocale(setupFragmentLanguage.getActivity(), str);
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString(setupFragmentLanguage.getString(R.string.locale_key), str);
        editorEdit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$0$4(SetupFragmentLanguage setupFragmentLanguage, View view) {
        int i;
        if (PluginManager.INSTANCE.getPluginsOnline().length == 0 && PluginManager.INSTANCE.getPluginsLocal().length == 0) {
            i = R.id.action_navigation_global_to_navigation_setup_extensions;
        } else {
            i = R.id.action_navigation_setup_language_to_navigation_setup_provider_languages;
        }
        FragmentKt.findNavController(setupFragmentLanguage).navigate(i, SetupFragmentExtensions.INSTANCE.newInstance(true));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$0$5(SetupFragmentLanguage setupFragmentLanguage, View view) {
        CloudStreamApp.INSTANCE.setKey(SetupFragmentLanguageKt.HAS_DONE_SETUP_KEY, true);
        FragmentKt.findNavController(setupFragmentLanguage).navigate(R.id.navigation_home);
    }
}
