package com.lagradost.cloudstream3.ui.settings.testing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.FragmentTestingBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.LifecycleKt;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.ui.settings.SettingsFragment;
import java.util.Comparator;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: TestFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/testing/TestFragment;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentTestingBinding;", "<init>", "()V", "testViewModel", "Lcom/lagradost/cloudstream3/ui/settings/testing/TestViewModel;", "getTestViewModel", "()Lcom/lagradost/cloudstream3/ui/settings/testing/TestViewModel;", "testViewModel$delegate", "Lkotlin/Lazy;", "fixLayout", "", "view", "Landroid/view/View;", "onBindingCreated", "binding", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TestFragment extends BaseFragment<FragmentTestingBinding> {

    /* JADX INFO: renamed from: testViewModel$delegate, reason: from kotlin metadata */
    private final Lazy testViewModel;

    /* JADX INFO: compiled from: TestFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TestView.TestState.values().length];
            try {
                iArr[TestView.TestState.Stopped.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TestView.TestState.Running.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TestView.TestState.None.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.settings.testing.TestFragment$1, reason: invalid class name */
    /* JADX INFO: compiled from: TestFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, FragmentTestingBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, FragmentTestingBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/FragmentTestingBinding;", 0);
        }

        public final FragmentTestingBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentTestingBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ FragmentTestingBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public TestFragment() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
        final TestFragment testFragment = this;
        final Function0 function0 = null;
        this.testViewModel = FragmentViewModelLazyKt.createViewModelLazy(testFragment, Reflection.getOrCreateKotlinClass(TestViewModel.class), new Function0<ViewModelStore>() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return testFragment.requireActivity().getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function1 = function0;
                return (function1 == null || (creationExtras = (CreationExtras) function1.invoke()) == null) ? testFragment.requireActivity().getDefaultViewModelCreationExtras() : creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return testFragment.requireActivity().getDefaultViewModelProviderFactory();
            }
        });
    }

    private final TestViewModel getTestViewModel() {
        return (TestViewModel) this.testViewModel.getValue();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        SettingsFragment.INSTANCE.setSystemBarsPadding(this);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final FragmentTestingBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        TestFragment testFragment = this;
        SettingsFragment.INSTANCE.setUpToolbar(testFragment, R.string.category_provider_test);
        SettingsFragment.INSTANCE.setToolBarScrollFlags(testFragment);
        binding.providerTestRecyclerView.setAdapter(new TestResultAdapter());
        getTestViewModel().init();
        if (getTestViewModel().isRunningTest()) {
            binding.providerTest.setState(TestView.TestState.Running);
        }
        TestFragment testFragment2 = this;
        LifecycleKt.observe(testFragment2, getTestViewModel().getProviderProgress(), new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TestFragment.onBindingCreated$lambda$0$0(binding, (TestViewModel.TestProgress) obj);
            }
        });
        LifecycleKt.observe(testFragment2, getTestViewModel().getProviderResults(), new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TestFragment.onBindingCreated$lambda$0$1(binding, (List) obj);
            }
        });
        binding.providerTest.setOnPlayButtonListener(new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestFragment$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TestFragment.onBindingCreated$lambda$0$2(this.f$0, (TestView.TestState) obj);
            }
        });
        if (Globals.INSTANCE.isLayout(2)) {
            MaterialButton playPauseButton = binding.providerTest.getPlayPauseButton();
            if (playPauseButton != null) {
                playPauseButton.setFocusableInTouchMode(true);
            }
            MaterialButton playPauseButton2 = binding.providerTest.getPlayPauseButton();
            if (playPauseButton2 != null) {
                playPauseButton2.requestFocus();
            }
        }
        MaterialButton playPauseButton3 = binding.providerTest.getPlayPauseButton();
        if (playPauseButton3 != null) {
            playPauseButton3.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestFragment$$ExternalSyntheticLambda4
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view, boolean z) {
                    TestFragment.onBindingCreated$lambda$0$3(binding, view, z);
                }
            });
        }
        binding.providerTest.setOnMainClick(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TestFragment.onBindingCreated$lambda$0$4(this.f$0, binding, view);
            }
        });
        binding.providerTest.setOnFailedClick(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TestFragment.onBindingCreated$lambda$0$5(this.f$0, binding, view);
            }
        });
        binding.providerTest.setOnPassedClick(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TestFragment.onBindingCreated$lambda$0$6(this.f$0, binding, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$0$0(FragmentTestingBinding fragmentTestingBinding, TestViewModel.TestProgress testProgress) {
        Intrinsics.checkNotNullParameter(testProgress, "<destruct>");
        fragmentTestingBinding.providerTest.setProgress(testProgress.getPassed(), testProgress.getFailed(), Integer.valueOf(testProgress.getTotal()));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$0$1(final FragmentTestingBinding fragmentTestingBinding, final List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TestFragment.onBindingCreated$lambda$0$1$0(it, fragmentTestingBinding);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$0$1$0(List list, FragmentTestingBinding fragmentTestingBinding) {
        List listSortedWith = CollectionsKt.sortedWith(list, new Comparator() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestFragment$onBindingCreated$lambda$0$1$0$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((MainAPI) ((Pair) t).getFirst()).getName(), ((MainAPI) ((Pair) t2).getFirst()).getName());
            }
        });
        RecyclerView.Adapter adapter = fragmentTestingBinding.providerTestRecyclerView.getAdapter();
        TestResultAdapter testResultAdapter = adapter instanceof TestResultAdapter ? (TestResultAdapter) adapter : null;
        if (testResultAdapter == null) {
            return null;
        }
        BaseAdapter.submitList$default(testResultAdapter, listSortedWith, null, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$0$2(TestFragment testFragment, TestView.TestState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        int i = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
        if (i == 1) {
            testFragment.getTestViewModel().stopTest();
        } else {
            if (i != 2 && i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            testFragment.getTestViewModel().startTest();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$3(FragmentTestingBinding fragmentTestingBinding, View view, boolean z) {
        if (z) {
            fragmentTestingBinding.providerTestAppbar.setExpanded(true, true);
        }
    }

    private static final void onBindingCreated$lambda$0$focusRecyclerView(FragmentTestingBinding fragmentTestingBinding) {
        if (Globals.INSTANCE.isLayout(2)) {
            fragmentTestingBinding.providerTestRecyclerView.requestFocus();
            fragmentTestingBinding.providerTestAppbar.setExpanded(false, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$4(TestFragment testFragment, FragmentTestingBinding fragmentTestingBinding, View view) {
        testFragment.getTestViewModel().setFilterMethod(TestViewModel.ProviderFilter.All);
        onBindingCreated$lambda$0$focusRecyclerView(fragmentTestingBinding);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$5(TestFragment testFragment, FragmentTestingBinding fragmentTestingBinding, View view) {
        testFragment.getTestViewModel().setFilterMethod(TestViewModel.ProviderFilter.Failed);
        onBindingCreated$lambda$0$focusRecyclerView(fragmentTestingBinding);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$6(TestFragment testFragment, FragmentTestingBinding fragmentTestingBinding, View view) {
        testFragment.getTestViewModel().setFilterMethod(TestViewModel.ProviderFilter.Passed);
        onBindingCreated$lambda$0$focusRecyclerView(fragmentTestingBinding);
    }
}
