package com.lagradost.cloudstream3.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.FragmentEasterEggMonkeBinding;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: EasterEggMonkeFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0002H\u0016J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0002H\u0002J\u0018\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0002H\u0002J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u0002H\u0002J\u0018\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0002H\u0002J\u0018\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0002H\u0002J\b\u0010\u001c\u001a\u00020\u000eH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/ui/EasterEggMonkeFragment;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/FragmentEasterEggMonkeBinding;", "<init>", "()V", "monkeys", "", "", "activeMonkeys", "", "Landroid/widget/ImageView;", "spawningJob", "Lkotlinx/coroutines/Job;", "fixLayout", "", "view", "Landroid/view/View;", "onBindingCreated", "binding", "spawnMonkey", "startFloatingAnimation", "monkey", "handleTouch", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "isTouchingEdge", "removeMonkey", "onDestroyView", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class EasterEggMonkeFragment extends BaseFragment<FragmentEasterEggMonkeBinding> {
    private final List<ImageView> activeMonkeys;
    private final List<Integer> monkeys;
    private Job spawningJob;

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.EasterEggMonkeFragment$1, reason: invalid class name */
    /* JADX INFO: compiled from: EasterEggMonkeFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, FragmentEasterEggMonkeBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, FragmentEasterEggMonkeBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/FragmentEasterEggMonkeBinding;", 0);
        }

        public final FragmentEasterEggMonkeBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return FragmentEasterEggMonkeBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ FragmentEasterEggMonkeBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public EasterEggMonkeFragment() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
        this.monkeys = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.drawable.monke_benene), Integer.valueOf(R.drawable.monke_burrito), Integer.valueOf(R.drawable.monke_coco), Integer.valueOf(R.drawable.monke_cookie), Integer.valueOf(R.drawable.monke_flusdered), Integer.valueOf(R.drawable.monke_funny), Integer.valueOf(R.drawable.monke_like), Integer.valueOf(R.drawable.monke_party), Integer.valueOf(R.drawable.monke_sob), Integer.valueOf(R.drawable.monke_drink), Integer.valueOf(R.drawable.benene), Integer.valueOf(R.drawable.ic_launcher_foreground), Integer.valueOf(R.drawable.quick_novel_icon)});
        this.activeMonkeys = new ArrayList();
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.EasterEggMonkeFragment$onBindingCreated$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: EasterEggMonkeFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.EasterEggMonkeFragment$onBindingCreated$1", f = "EasterEggMonkeFragment.kt", i = {0, 1}, l = {54, 57}, m = "invokeSuspend", n = {"$this$launch", "$this$launch"}, nl = {55, 59}, s = {"L$0", "L$0"}, v = 2)
    static final class C08271 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ FragmentEasterEggMonkeBinding $binding;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08271(FragmentEasterEggMonkeBinding fragmentEasterEggMonkeBinding, Continuation<? super C08271> continuation) {
            super(2, continuation);
            this.$binding = fragmentEasterEggMonkeBinding;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C08271 c08271 = EasterEggMonkeFragment.this.new C08271(this.$binding, continuation);
            c08271.L$0 = obj;
            return c08271;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08271) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.L$0 = coroutineScope;
                this.label = 1;
                if (DelayKt.delay(1000L, this) != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i != 1 && i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            while (CoroutineScopeKt.isActive(coroutineScope)) {
                EasterEggMonkeFragment.this.spawnMonkey(this.$binding);
                this.L$0 = coroutineScope;
                this.label = 2;
                if (DelayKt.delay(500L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(FragmentEasterEggMonkeBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            UIHelper.INSTANCE.hideSystemUI(activity);
        }
        this.spawningJob = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C08271(binding, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void spawnMonkey(final FragmentEasterEggMonkeBinding binding) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(((Number) CollectionsKt.random(this.monkeys, Random.INSTANCE)).intValue());
        ImageView imageView2 = imageView;
        imageView2.setVisibility(0);
        float fNextFloat = (Random.INSTANCE.nextFloat() * 1.5f) + 0.5f;
        imageView.setScaleX(fNextFloat);
        imageView.setScaleY(fNextFloat);
        imageView.measure(0, 0);
        float measuredWidth = imageView.getMeasuredWidth() * fNextFloat;
        float measuredHeight = imageView.getMeasuredHeight() * fNextFloat;
        imageView.setX(Random.INSTANCE.nextFloat() * (binding.frame.getWidth() - measuredWidth));
        imageView.setY(Random.INSTANCE.nextFloat() * (binding.frame.getHeight() - measuredHeight));
        binding.frame.addView(imageView2, new FrameLayout.LayoutParams(-2, -2));
        this.activeMonkeys.add(imageView);
        imageView.setAlpha(0.0f);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(imageView, (Property<ImageView, Float>) View.ALPHA, 0.0f, 1.0f);
        objectAnimatorOfFloat.setDuration(Random.INSTANCE.nextLong(1000L, 2500L));
        objectAnimatorOfFloat.setInterpolator(new AccelerateInterpolator());
        objectAnimatorOfFloat.start();
        imageView.setOnTouchListener(new View.OnTouchListener() { // from class: com.lagradost.cloudstream3.ui.EasterEggMonkeFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return EasterEggMonkeFragment.spawnMonkey$lambda$2(this.f$0, binding, view, motionEvent);
            }
        });
        startFloatingAnimation(imageView, binding);
    }

    static final boolean spawnMonkey$lambda$2(EasterEggMonkeFragment easterEggMonkeFragment, FragmentEasterEggMonkeBinding fragmentEasterEggMonkeBinding, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNull(view);
        Intrinsics.checkNotNull(motionEvent);
        return easterEggMonkeFragment.handleTouch(view, motionEvent, fragmentEasterEggMonkeBinding);
    }

    private final void startFloatingAnimation(final ImageView monkey, final FragmentEasterEggMonkeBinding binding) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(monkey, (Property<ImageView, Float>) View.TRANSLATION_Y, monkey.getY(), -monkey.getHeight());
        objectAnimatorOfFloat.setDuration(Random.INSTANCE.nextLong(8000L, 15000L));
        objectAnimatorOfFloat.setInterpolator(new LinearInterpolator());
        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.lagradost.cloudstream3.ui.EasterEggMonkeFragment.startFloatingAnimation.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                binding.frame.removeView(monkey);
                this.activeMonkeys.remove(monkey);
            }
        });
        objectAnimatorOfFloat.start();
        monkey.setTag(objectAnimatorOfFloat);
    }

    private final boolean handleTouch(View view, MotionEvent event, FragmentEasterEggMonkeBinding binding) {
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.ImageView");
        ImageView imageView = (ImageView) view;
        int action = event.getAction();
        if (action == 0) {
            Object tag = imageView.getTag();
            ObjectAnimator objectAnimator = tag instanceof ObjectAnimator ? (ObjectAnimator) tag : null;
            if (objectAnimator != null) {
                objectAnimator.pause();
            }
            return true;
        }
        if (action != 1) {
            if (action == 2) {
                imageView.setX(event.getRawX() - (imageView.getWidth() / 2));
                imageView.setY(event.getRawY() - (imageView.getHeight() / 2));
                if (isTouchingEdge(imageView, binding)) {
                    removeMonkey(imageView, binding);
                }
                return true;
            }
            if (action != 3) {
                return false;
            }
        }
        if (isTouchingEdge(imageView, binding)) {
            removeMonkey(imageView, binding);
        } else {
            startFloatingAnimation(imageView, binding);
        }
        return true;
    }

    private final boolean isTouchingEdge(ImageView monkey, FragmentEasterEggMonkeBinding binding) {
        return monkey.getX() <= 0.0f || monkey.getX() + ((float) monkey.getWidth()) >= ((float) binding.frame.getWidth()) || monkey.getY() <= 0.0f || monkey.getY() + ((float) monkey.getHeight()) >= ((float) binding.frame.getHeight());
    }

    private final void removeMonkey(final ImageView monkey, final FragmentEasterEggMonkeBinding binding) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(monkey, (Property<ImageView, Float>) View.ALPHA, 1.0f, 0.0f);
        objectAnimatorOfFloat.setDuration(300L);
        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.lagradost.cloudstream3.ui.EasterEggMonkeFragment$removeMonkey$1$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                binding.frame.removeView(monkey);
                this.activeMonkeys.remove(monkey);
            }
        });
        objectAnimatorOfFloat.start();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            UIHelper.INSTANCE.showSystemUI(activity);
        }
        Job job = this.spawningJob;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }
}
