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
import android.widget.ListView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.navigation.fragment.FragmentKt;
import androidx.preference.PreferenceManager;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.databinding.FragmentSetupMediaBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SetupFragmentMedia.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0002H\u0016¨\u0006\u000b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/setup/SetupFragmentMedia;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentSetupMediaBinding;", "<init>", "()V", "fixLayout", "", "view", "Landroid/view/View;", "onBindingCreated", "binding", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SetupFragmentMedia extends BaseFragment<FragmentSetupMediaBinding> {

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.setup.SetupFragmentMedia$1, reason: invalid class name */
    /* JADX INFO: compiled from: SetupFragmentMedia.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, FragmentSetupMediaBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, FragmentSetupMediaBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/FragmentSetupMediaBinding;", 0);
        }

        public final FragmentSetupMediaBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentSetupMediaBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ FragmentSetupMediaBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public SetupFragmentMedia() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, false, false, false, false, false, TypedValues.PositionType.TYPE_POSITION_TYPE, null);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final FragmentSetupMediaBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentMedia$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SetupFragmentMedia.onBindingCreated$lambda$0(this.f$0, binding);
            }
        });
    }

    static final Unit onBindingCreated$lambda$0(final SetupFragmentMedia setupFragmentMedia, FragmentSetupMediaBinding fragmentSetupMediaBinding) {
        Context context = setupFragmentMedia.getContext();
        if (context == null) {
            return Unit.INSTANCE;
        }
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.sort_bottom_single_choice);
        List listSorted = ArraysKt.sorted(TvType.values());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSorted, 10));
        Iterator it = listSorted.iterator();
        while (it.hasNext()) {
            arrayList.add(((TvType) it.next()).name());
        }
        final ArrayList arrayList2 = new ArrayList();
        arrayAdapter.addAll(arrayList);
        final ListView listView = fragmentSetupMediaBinding.listview1;
        listView.setAdapter((ListAdapter) arrayAdapter);
        listView.setChoiceMode(2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentMedia$$ExternalSyntheticLambda1
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                SetupFragmentMedia.onBindingCreated$lambda$0$1$0$0(listView, arrayList2, defaultSharedPreferences, setupFragmentMedia, adapterView, view, i, j);
            }
        });
        fragmentSetupMediaBinding.nextBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentMedia$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetupFragmentMedia.onBindingCreated$lambda$0$1$1(this.f$0, view);
            }
        });
        fragmentSetupMediaBinding.prevBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentMedia$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetupFragmentMedia.onBindingCreated$lambda$0$1$2(this.f$0, view);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$1$0$0(ListView listView, List list, SharedPreferences sharedPreferences, SetupFragmentMedia setupFragmentMedia, AdapterView adapterView, View view, int i, long j) {
        String string;
        SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
        if (checkedItemPositions != null) {
            int size = checkedItemPositions.size();
            for (int i2 = 0; i2 < size; i2++) {
                int iKeyAt = checkedItemPositions.keyAt(i2);
                if (checkedItemPositions.valueAt(i2)) {
                    list.add(Integer.valueOf(iKeyAt));
                } else {
                    list.remove(Integer.valueOf(iKeyAt));
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (true) {
            String strValueOf = null;
            if (it.hasNext()) {
                Object itemAtPosition = listView.getItemAtPosition(((Number) it.next()).intValue());
                if (itemAtPosition != null && (string = itemAtPosition.toString()) != null) {
                    strValueOf = String.valueOf(TvType.valueOf(string).ordinal());
                }
                if (strValueOf != null) {
                    arrayList.add(strValueOf);
                }
            } else {
                Set<String> set = CollectionsKt.toSet(arrayList);
                Intrinsics.checkNotNull(sharedPreferences);
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putStringSet(setupFragmentMedia.getString(R.string.prefer_media_type_key), set);
                editorEdit.apply();
                DataStoreHelper.INSTANCE.setCurrentHomePage(null);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$1$1(SetupFragmentMedia setupFragmentMedia, View view) {
        FragmentKt.findNavController(setupFragmentMedia).navigate(R.id.navigation_setup_media_to_navigation_setup_layout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$1$2(SetupFragmentMedia setupFragmentMedia, View view) {
        FragmentKt.findNavController(setupFragmentMedia).popBackStack();
    }
}
