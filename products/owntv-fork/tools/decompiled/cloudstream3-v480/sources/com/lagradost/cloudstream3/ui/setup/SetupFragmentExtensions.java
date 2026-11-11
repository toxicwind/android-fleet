package com.lagradost.cloudstream3.ui.setup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.navigation.fragment.FragmentKt;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.FragmentSetupExtensionsBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.plugins.RepositoryManager;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel;
import com.lagradost.cloudstream3.ui.settings.extensions.RepoAdapter;
import com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.UIHelper;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SetupFragmentExtensions.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0010B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0002H\u0016¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/ui/setup/SetupFragmentExtensions;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentSetupExtensionsBinding;", "<init>", "()V", "onResume", "", "onStop", "fixLayout", "view", "Landroid/view/View;", "setRepositories", "success", "", "onBindingCreated", "binding", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SetupFragmentExtensions extends BaseFragment<FragmentSetupExtensionsBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String SETUP_EXTENSION_BUNDLE_IS_SETUP = "isSetup";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.setup.SetupFragmentExtensions$1, reason: invalid class name */
    /* JADX INFO: compiled from: SetupFragmentExtensions.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, FragmentSetupExtensionsBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, FragmentSetupExtensionsBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/FragmentSetupExtensionsBinding;", 0);
        }

        public final FragmentSetupExtensionsBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentSetupExtensionsBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ FragmentSetupExtensionsBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public SetupFragmentExtensions() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
    }

    /* JADX INFO: compiled from: SetupFragmentExtensions.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/ui/setup/SetupFragmentExtensions$Companion;", "", "<init>", "()V", "SETUP_EXTENSION_BUNDLE_IS_SETUP", "", "newInstance", "Landroid/os/Bundle;", SetupFragmentExtensions.SETUP_EXTENSION_BUNDLE_IS_SETUP, "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Bundle newInstance(boolean isSetup) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(SetupFragmentExtensions.SETUP_EXTENSION_BUNDLE_IS_SETUP, isSetup);
            return bundle;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.setup.SetupFragmentExtensions$onResume$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SetupFragmentExtensions.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C09121 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        C09121(Object obj) {
            super(1, obj, SetupFragmentExtensions.class, "setRepositories", "setRepositories(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((SetupFragmentExtensions) this.receiver).setRepositories(z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        MainActivity.INSTANCE.getAfterRepositoryLoadedEvent().plusAssign(new C09121(this));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.setup.SetupFragmentExtensions$onStop$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SetupFragmentExtensions.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C09131 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        C09131(Object obj) {
            super(1, obj, SetupFragmentExtensions.class, "setRepositories", "setRepositories(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((SetupFragmentExtensions) this.receiver).setRepositories(z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        MainActivity.INSTANCE.getAfterRepositoryLoadedEvent().minusAssign(new C09131(this));
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, false, false, false, false, false, TypedValues.PositionType.TYPE_POSITION_TYPE, null);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.setup.SetupFragmentExtensions$setRepositories$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SetupFragmentExtensions.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/lagradost/cloudstream3/ui/setup/SetupFragmentExtensions;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.setup.SetupFragmentExtensions$setRepositories$1", f = "SetupFragmentExtensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C09141 extends SuspendLambda implements Function2<SetupFragmentExtensions, Continuation<? super Unit>, Object> {
        int label;

        C09141(Continuation<? super C09141> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SetupFragmentExtensions.this.new C09141(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SetupFragmentExtensions setupFragmentExtensions, Continuation<? super Unit> continuation) {
            return ((C09141) create(setupFragmentExtensions, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            FragmentSetupExtensionsBinding binding;
            RecyclerView recyclerView;
            LinearLayout linearLayout;
            RecyclerView recyclerView2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            RepositoryData[] repositoryDataArr = (RepositoryData[]) ArraysKt.plus((Object[]) RepositoryManager.INSTANCE.getRepositories(), (Object[]) RepositoryManager.INSTANCE.getPREBUILT_REPOSITORIES());
            boolean z = repositoryDataArr.length == 0;
            FragmentSetupExtensionsBinding binding2 = SetupFragmentExtensions.this.getBinding();
            if (binding2 != null && (recyclerView2 = binding2.repoRecyclerView) != null) {
                recyclerView2.setVisibility(!z ? 0 : 8);
            }
            FragmentSetupExtensionsBinding binding3 = SetupFragmentExtensions.this.getBinding();
            if (binding3 != null && (linearLayout = binding3.blankRepoScreen) != null) {
                linearLayout.setVisibility(z ? 0 : 8);
            }
            if (!z && (binding = SetupFragmentExtensions.this.getBinding()) != null && (recyclerView = binding.repoRecyclerView) != null) {
                Function2 function2 = new Function2() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentExtensions$setRepositories$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return Unit.INSTANCE;
                    }
                };
                final SetupFragmentExtensions setupFragmentExtensions = SetupFragmentExtensions.this;
                RepoAdapter repoAdapter = new RepoAdapter(true, function2, new Function2() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentExtensions$setRepositories$1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return SetupFragmentExtensions.C09141.invokeSuspend$lambda$1(setupFragmentExtensions, (RepoAdapter) obj2, (RepositoryData) obj3);
                    }
                });
                BaseAdapter.submitList$default(repoAdapter, ArraysKt.toList(repositoryDataArr), null, 2, null);
                recyclerView.setAdapter(repoAdapter);
            }
            return Unit.INSTANCE;
        }

        static final Unit invokeSuspend$lambda$1(SetupFragmentExtensions setupFragmentExtensions, RepoAdapter repoAdapter, RepositoryData repositoryData) {
            PluginsViewModel.INSTANCE.downloadAll(setupFragmentExtensions.getActivity(), repositoryData, null);
            return Unit.INSTANCE;
        }
    }

    static /* synthetic */ void setRepositories$default(SetupFragmentExtensions setupFragmentExtensions, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        setupFragmentExtensions.setRepositories(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRepositories(boolean success) {
        Coroutines.INSTANCE.main(this, new C09141(null));
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final FragmentSetupExtensionsBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        Bundle arguments = getArguments();
        final boolean z = arguments != null ? arguments.getBoolean(SETUP_EXTENSION_BUNDLE_IS_SETUP) : false;
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentExtensions$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SetupFragmentExtensions.onBindingCreated$lambda$0(this.f$0, binding, z);
            }
        });
    }

    static final FragmentSetupExtensionsBinding onBindingCreated$lambda$0(final SetupFragmentExtensions setupFragmentExtensions, FragmentSetupExtensionsBinding fragmentSetupExtensionsBinding, final boolean z) {
        setRepositories$default(setupFragmentExtensions, false, 1, null);
        if (!z) {
            fragmentSetupExtensionsBinding.nextBtt.setText(R.string.setup_done);
        }
        MaterialButton prevBtt = fragmentSetupExtensionsBinding.prevBtt;
        Intrinsics.checkNotNullExpressionValue(prevBtt, "prevBtt");
        prevBtt.setVisibility(z ? 0 : 8);
        fragmentSetupExtensionsBinding.nextBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentExtensions$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetupFragmentExtensions.onBindingCreated$lambda$0$0$0(z, setupFragmentExtensions, view);
            }
        });
        fragmentSetupExtensionsBinding.prevBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentExtensions$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetupFragmentExtensions.onBindingCreated$lambda$0$0$1(this.f$0, view);
            }
        });
        return fragmentSetupExtensionsBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$0$0(boolean z, SetupFragmentExtensions setupFragmentExtensions, View view) {
        if (z) {
            if (APIHolder.INSTANCE.getApis().distinctBy(new Function1() { // from class: com.lagradost.cloudstream3.ui.setup.SetupFragmentExtensions$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SetupFragmentExtensions.onBindingCreated$lambda$0$0$0$0((MainAPI) obj);
                }
            }).size() > 1) {
                FragmentKt.findNavController(setupFragmentExtensions).navigate(R.id.action_navigation_setup_extensions_to_navigation_setup_provider_languages);
                return;
            } else {
                FragmentKt.findNavController(setupFragmentExtensions).navigate(R.id.action_navigation_setup_extensions_to_navigation_setup_media);
                return;
            }
        }
        FragmentKt.findNavController(setupFragmentExtensions).navigate(R.id.navigation_home);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object onBindingCreated$lambda$0$0$0$0(MainAPI it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getLang();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$0$0$1(SetupFragmentExtensions setupFragmentExtensions, View view) {
        FragmentKt.findNavController(setupFragmentExtensions).navigate(R.id.navigation_setup_language);
    }
}
