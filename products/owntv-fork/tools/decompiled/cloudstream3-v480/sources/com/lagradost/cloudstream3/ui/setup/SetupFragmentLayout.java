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
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.FragmentKt;
import androidx.preference.PreferenceManager;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.FragmentSetupLayoutBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.utils.UIHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SetupFragmentLayout.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0002H\u0016¨\u0006\u000b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/setup/SetupFragmentLayout;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentSetupLayoutBinding;", "<init>", "()V", "fixLayout", "", "view", "Landroid/view/View;", "onBindingCreated", "binding", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SetupFragmentLayout extends BaseFragment<FragmentSetupLayoutBinding> {

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.setup.SetupFragmentLayout$1, reason: invalid class name */
    /* JADX INFO: compiled from: SetupFragmentLayout.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, FragmentSetupLayoutBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, FragmentSetupLayoutBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/FragmentSetupLayoutBinding;", 0);
        }

        public final FragmentSetupLayoutBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentSetupLayoutBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ FragmentSetupLayoutBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public SetupFragmentLayout() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, false, false, false, false, false, TypedValues.PositionType.TYPE_POSITION_TYPE, null);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final FragmentSetupLayoutBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentLayout$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SetupFragmentLayout.onBindingCreated$lambda$0(this.f$0, binding);
            }
        });
    }

    static final Unit onBindingCreated$lambda$0(final SetupFragmentLayout setupFragmentLayout, FragmentSetupLayoutBinding fragmentSetupLayoutBinding) {
        Context context = setupFragmentLayout.getContext();
        if (context == null) {
            return Unit.INSTANCE;
        }
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String[] stringArray = setupFragmentLayout.getResources().getStringArray(R.array.app_layout);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        final int[] intArray = setupFragmentLayout.getResources().getIntArray(R.array.app_layout_values);
        Intrinsics.checkNotNullExpressionValue(intArray, "getIntArray(...)");
        int i = defaultSharedPreferences.getInt(setupFragmentLayout.getString(R.string.app_layout_key), -1);
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.sort_bottom_single_choice);
        arrayAdapter.addAll(ArraysKt.toList(stringArray));
        fragmentSetupLayoutBinding.listview1.setAdapter((ListAdapter) arrayAdapter);
        fragmentSetupLayoutBinding.listview1.setChoiceMode(1);
        fragmentSetupLayoutBinding.listview1.setItemChecked(ArraysKt.indexOf(intArray, i), true);
        fragmentSetupLayoutBinding.listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentLayout$$ExternalSyntheticLambda0
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i2, long j) {
                SetupFragmentLayout.onBindingCreated$lambda$0$0$0(defaultSharedPreferences, setupFragmentLayout, intArray, adapterView, view, i2, j);
            }
        });
        fragmentSetupLayoutBinding.nextBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentLayout$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetupFragmentLayout.onBindingCreated$lambda$0$0$1(this.f$0, view);
            }
        });
        fragmentSetupLayoutBinding.prevBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentLayout$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetupFragmentLayout.onBindingCreated$lambda$0$0$2(this.f$0, view);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$0$0(SharedPreferences sharedPreferences, SetupFragmentLayout setupFragmentLayout, int[] iArr, AdapterView adapterView, View view, int i, long j) {
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt(setupFragmentLayout.getString(R.string.app_layout_key), iArr[i]);
        editorEdit.apply();
        FragmentActivity activity = setupFragmentLayout.getActivity();
        if (activity != null) {
            activity.recreate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$0$1(SetupFragmentLayout setupFragmentLayout, View view) {
        CloudStreamApp.INSTANCE.setKey(SetupFragmentLanguageKt.HAS_DONE_SETUP_KEY, true);
        FragmentKt.findNavController(setupFragmentLayout).navigate(R.id.navigation_home);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$0$2(SetupFragmentLayout setupFragmentLayout, View view) {
        FragmentKt.findNavController(setupFragmentLayout).popBackStack();
    }
}
