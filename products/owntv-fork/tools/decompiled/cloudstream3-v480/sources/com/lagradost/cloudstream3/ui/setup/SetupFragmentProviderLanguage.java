package com.lagradost.cloudstream3.ui.setup;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.navigation.fragment.FragmentKt;
import androidx.preference.PreferenceManager;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.FragmentSetupProviderLanguagesBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.AtomicList;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SetupFragmentProviderLanguage.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0002H\u0016¨\u0006\u000b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/setup/SetupFragmentProviderLanguage;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentSetupProviderLanguagesBinding;", "<init>", "()V", "fixLayout", "", "view", "Landroid/view/View;", "onBindingCreated", "binding", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SetupFragmentProviderLanguage extends BaseFragment<FragmentSetupProviderLanguagesBinding> {

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.setup.SetupFragmentProviderLanguage$1, reason: invalid class name */
    /* JADX INFO: compiled from: SetupFragmentProviderLanguage.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, FragmentSetupProviderLanguagesBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, FragmentSetupProviderLanguagesBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/FragmentSetupProviderLanguagesBinding;", 0);
        }

        public final FragmentSetupProviderLanguagesBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentSetupProviderLanguagesBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ FragmentSetupProviderLanguagesBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public SetupFragmentProviderLanguage() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, false, false, false, false, false, TypedValues.PositionType.TYPE_POSITION_TYPE, null);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final FragmentSetupProviderLanguagesBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentProviderLanguage$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SetupFragmentProviderLanguage.onBindingCreated$lambda$0(this.f$0, binding);
            }
        });
    }

    static final Unit onBindingCreated$lambda$0(final SetupFragmentProviderLanguage setupFragmentProviderLanguage, final FragmentSetupProviderLanguagesBinding fragmentSetupProviderLanguagesBinding) {
        final Context context = setupFragmentProviderLanguage.getContext();
        if (context == null) {
            return Unit.INSTANCE;
        }
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.sort_bottom_single_choice);
        HashSet<String> apiProviderLangSettings = AppContextUtils.INSTANCE.getApiProviderLangSettings(context);
        final List list = (List) APIHolder.INSTANCE.getApis().withLock(new Function0() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentProviderLanguage$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SetupFragmentProviderLanguage.onBindingCreated$lambda$0$0(this.f$0);
            }
        });
        HashSet<String> hashSet = apiProviderLangSettings;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(hashSet, 10));
        Iterator<T> it = hashSet.iterator();
        while (true) {
            int i = -1;
            if (!it.hasNext()) {
                break;
            }
            String str = (String) it.next();
            Iterator it2 = list.iterator();
            int i2 = 0;
            while (it2.hasNext()) {
                if (Intrinsics.areEqual(((Pair) it2.next()).getFirst(), str)) {
                    i = i2;
                    break;
                }
                i2++;
            }
            arrayList.add(Integer.valueOf(i));
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (((Number) obj).intValue() > -1) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = arrayList2;
        List list2 = list;
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it3 = list2.iterator();
        while (it3.hasNext()) {
            arrayList4.add((String) ((Pair) it3.next()).getSecond());
        }
        arrayAdapter.addAll(arrayList4);
        fragmentSetupProviderLanguagesBinding.listview1.setAdapter((ListAdapter) arrayAdapter);
        fragmentSetupProviderLanguagesBinding.listview1.setChoiceMode(2);
        Iterator it4 = arrayList3.iterator();
        while (it4.hasNext()) {
            fragmentSetupProviderLanguagesBinding.listview1.setItemChecked(((Number) it4.next()).intValue(), true);
        }
        fragmentSetupProviderLanguagesBinding.listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentProviderLanguage$$ExternalSyntheticLambda2
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i3, long j) {
                SetupFragmentProviderLanguage.onBindingCreated$lambda$0$4$1(fragmentSetupProviderLanguagesBinding, defaultSharedPreferences, list, context, adapterView, view, i3, j);
            }
        });
        fragmentSetupProviderLanguagesBinding.nextBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentProviderLanguage$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetupFragmentProviderLanguage.onBindingCreated$lambda$0$4$2(this.f$0, view);
            }
        });
        fragmentSetupProviderLanguagesBinding.prevBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentProviderLanguage$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetupFragmentProviderLanguage.onBindingCreated$lambda$0$4$3(this.f$0, view);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List onBindingCreated$lambda$0$0(SetupFragmentProviderLanguage setupFragmentProviderLanguage) {
        List listListOf = CollectionsKt.listOf(new Pair(MainAPIKt.AllLanguagesName, setupFragmentProviderLanguage.getString(R.string.all_languages_preference)));
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
        return CollectionsKt.plus((Collection) listListOf, (Iterable) CollectionsKt.sortedWith(CollectionsKt.toSet(arrayList), new Comparator() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentProviderLanguage$onBindingCreated$lambda$0$0$$inlined$sortedBy$1
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
    public static final void onBindingCreated$lambda$0$4$1(FragmentSetupProviderLanguagesBinding fragmentSetupProviderLanguagesBinding, SharedPreferences sharedPreferences, List list, Context context, AdapterView adapterView, View view, int i, long j) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        SparseBooleanArray checkedItemPositions = fragmentSetupProviderLanguagesBinding.listview1.getCheckedItemPositions();
        if (checkedItemPositions != null) {
            int size = checkedItemPositions.size();
            for (int i2 = 0; i2 < size; i2++) {
                int iKeyAt = checkedItemPositions.keyAt(i2);
                if (checkedItemPositions.valueAt(i2)) {
                    linkedHashSet.add(((Pair) list.get(iKeyAt)).getFirst());
                }
            }
        }
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putStringSet(context.getString(R.string.provider_lang_key), CollectionsKt.toSet(linkedHashSet));
        editorEdit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$4$2(SetupFragmentProviderLanguage setupFragmentProviderLanguage, View view) {
        FragmentKt.findNavController(setupFragmentProviderLanguage).navigate(R.id.navigation_setup_provider_languages_to_navigation_setup_media);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$4$3(SetupFragmentProviderLanguage setupFragmentProviderLanguage, View view) {
        FragmentKt.findNavController(setupFragmentProviderLanguage).popBackStack();
    }
}
