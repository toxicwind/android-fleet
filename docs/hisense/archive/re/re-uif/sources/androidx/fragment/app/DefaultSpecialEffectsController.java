package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class DefaultSpecialEffectsController extends SpecialEffectsController {
    private final HashMap<SpecialEffectsController.Operation, HashSet<CancellationSignal>> mRunningOperations;

    DefaultSpecialEffectsController(ViewGroup viewGroup) {
        super(viewGroup);
        this.mRunningOperations = new HashMap<>();
    }

    private void addCancellationSignal(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal) {
        if (this.mRunningOperations.get(operation) == null) {
            this.mRunningOperations.put(operation, new HashSet<>());
        }
        this.mRunningOperations.get(operation).add(cancellationSignal);
    }

    void removeCancellationSignal(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal) {
        HashSet<CancellationSignal> hashSet = this.mRunningOperations.get(operation);
        if (hashSet != null && hashSet.remove(cancellationSignal) && hashSet.isEmpty()) {
            this.mRunningOperations.remove(operation);
            operation.complete();
        }
    }

    void cancelAllSpecialEffects(SpecialEffectsController.Operation operation) {
        HashSet<CancellationSignal> hashSetRemove = this.mRunningOperations.remove(operation);
        if (hashSetRemove != null) {
            Iterator<CancellationSignal> it = hashSetRemove.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.DefaultSpecialEffectsController$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$Type;

        static {
            int[] iArr = new int[SpecialEffectsController.Operation.Type.values().length];
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$Type = iArr;
            try {
                iArr[SpecialEffectsController.Operation.Type.HIDE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$Type[SpecialEffectsController.Operation.Type.REMOVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$Type[SpecialEffectsController.Operation.Type.SHOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$Type[SpecialEffectsController.Operation.Type.ADD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0072  */
    @Override // androidx.fragment.app.SpecialEffectsController
    void executeOperations(List<SpecialEffectsController.Operation> list, boolean z) {
        SpecialEffectsController.Operation operation = null;
        SpecialEffectsController.Operation operation2 = null;
        for (SpecialEffectsController.Operation operation3 : list) {
            int i = AnonymousClass8.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$Type[operation3.getType().ordinal()];
            if (i == 1 || i == 2) {
                if (operation == null) {
                    operation = operation3;
                }
            } else if (i == 3 || i == 4) {
                operation2 = operation3;
            }
        }
        ArrayList<AnimationInfo> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList(list);
        for (final SpecialEffectsController.Operation operation4 : list) {
            CancellationSignal cancellationSignal = new CancellationSignal();
            addCancellationSignal(operation4, cancellationSignal);
            arrayList.add(new AnimationInfo(operation4, cancellationSignal));
            CancellationSignal cancellationSignal2 = new CancellationSignal();
            addCancellationSignal(operation4, cancellationSignal2);
            boolean z2 = false;
            if (z) {
                if (operation4 == operation) {
                    z2 = true;
                }
            } else if (operation4 == operation2) {
                z2 = true;
            }
            arrayList2.add(new TransitionInfo(operation4, cancellationSignal2, z, z2));
            operation4.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.1
                @Override // java.lang.Runnable
                public void run() {
                    if (arrayList3.contains(operation4)) {
                        arrayList3.remove(operation4);
                        DefaultSpecialEffectsController.this.applyContainerChanges(operation4);
                    }
                }
            });
            operation4.getCancellationSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.2
                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                public void onCancel() {
                    DefaultSpecialEffectsController.this.cancelAllSpecialEffects(operation4);
                }
            });
        }
        startTransitions(arrayList2, z, operation, operation2);
        for (AnimationInfo animationInfo : arrayList) {
            startAnimation(animationInfo.getOperation(), animationInfo.getSignal());
        }
        Iterator it = arrayList3.iterator();
        while (it.hasNext()) {
            applyContainerChanges((SpecialEffectsController.Operation) it.next());
        }
        arrayList3.clear();
    }

    private void startAnimation(final SpecialEffectsController.Operation operation, final CancellationSignal cancellationSignal) {
        Animation enterViewTransitionAnimation;
        final ViewGroup container = getContainer();
        Context context = container.getContext();
        Fragment fragment = operation.getFragment();
        final View view = fragment.mView;
        FragmentAnim.AnimationOrAnimator animationOrAnimatorLoadAnimation = FragmentAnim.loadAnimation(context, fragment, operation.getType() == SpecialEffectsController.Operation.Type.ADD || operation.getType() == SpecialEffectsController.Operation.Type.SHOW);
        if (animationOrAnimatorLoadAnimation == null) {
            removeCancellationSignal(operation, cancellationSignal);
            return;
        }
        container.startViewTransition(view);
        if (animationOrAnimatorLoadAnimation.animation != null) {
            if (operation.getType() == SpecialEffectsController.Operation.Type.ADD || operation.getType() == SpecialEffectsController.Operation.Type.SHOW) {
                enterViewTransitionAnimation = new FragmentAnim.EnterViewTransitionAnimation(animationOrAnimatorLoadAnimation.animation);
            } else {
                enterViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animationOrAnimatorLoadAnimation.animation, container, view);
            }
            Animation animation = enterViewTransitionAnimation;
            animation.setAnimationListener(new Animation.AnimationListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.3
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation2) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation2) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation2) {
                    container.post(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                            container.endViewTransition(view);
                            AnonymousClass3 anonymousClass4 = AnonymousClass3.this;
                            DefaultSpecialEffectsController.this.removeCancellationSignal(operation, cancellationSignal);
                        }
                    });
                }
            });
            view.startAnimation(animation);
        } else {
            animationOrAnimatorLoadAnimation.animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.4
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    container.endViewTransition(view);
                    DefaultSpecialEffectsController.this.removeCancellationSignal(operation, cancellationSignal);
                }
            });
            animationOrAnimatorLoadAnimation.animator.setTarget(view);
            animationOrAnimatorLoadAnimation.animator.start();
        }
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener(this) { // from class: androidx.fragment.app.DefaultSpecialEffectsController.5
            @Override // androidx.core.os.CancellationSignal.OnCancelListener
            public void onCancel() {
                view.clearAnimation();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void startTransitions(List<TransitionInfo> list, boolean z, SpecialEffectsController.Operation operation, SpecialEffectsController.Operation operation2) {
        Object obj;
        View view;
        Iterator<TransitionInfo> it;
        ArrayList<String> sharedElementTargetNames;
        ArrayList<String> sharedElementSourceNames;
        View view2;
        final View view3;
        SpecialEffectsController.Operation operation3 = operation2;
        final FragmentTransitionImpl fragmentTransitionImpl = null;
        for (TransitionInfo transitionInfo : list) {
            FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl();
            if (fragmentTransitionImpl == null) {
                fragmentTransitionImpl = handlingImpl;
            } else if (handlingImpl != null && fragmentTransitionImpl != handlingImpl) {
                throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.getOperation().getFragment() + " returned Transition " + transitionInfo.getTransition() + " which uses a different Transition  type than other Fragments.");
            }
        }
        if (fragmentTransitionImpl == null) {
            for (TransitionInfo transitionInfo2 : list) {
                removeCancellationSignal(transitionInfo2.getOperation(), transitionInfo2.getSignal());
            }
            return;
        }
        View view4 = new View(getContainer().getContext());
        final Rect rect = new Rect();
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        ArrayMap arrayMap = new ArrayMap();
        Iterator<TransitionInfo> it2 = list.iterator();
        View view5 = null;
        boolean z2 = false;
        Object objWrapTransitionInSet = null;
        while (it2.hasNext()) {
            TransitionInfo next = it2.next();
            if (!next.hasSharedElementTransition() || operation == null || operation3 == null) {
                it = it2;
                view5 = view5;
            } else {
                objWrapTransitionInSet = fragmentTransitionImpl.wrapTransitionInSet(fragmentTransitionImpl.cloneTransition(next.getSharedElementTransition()));
                Fragment fragment = next.getOperation().getFragment();
                if (!z) {
                    sharedElementTargetNames = fragment.getSharedElementSourceNames();
                    sharedElementSourceNames = fragment.getSharedElementTargetNames();
                } else {
                    sharedElementTargetNames = fragment.getSharedElementTargetNames();
                    sharedElementSourceNames = fragment.getSharedElementSourceNames();
                }
                int size = sharedElementTargetNames.size();
                int i = 0;
                while (i < size) {
                    int i2 = size;
                    ArrayList<String> arrayList3 = sharedElementTargetNames;
                    arrayMap.put(arrayList3.get(i), sharedElementSourceNames.get(i));
                    i++;
                    sharedElementTargetNames = arrayList3;
                    size = i2;
                    it2 = it2;
                    view5 = view5;
                }
                it = it2;
                View view6 = view5;
                ArrayList<String> arrayList4 = sharedElementTargetNames;
                ArrayMap arrayMap2 = new ArrayMap();
                findNamedViews(arrayMap2, operation.getFragment().mView);
                arrayMap2.retainAll(arrayList4);
                arrayMap.retainAll(arrayMap2.keySet());
                ArrayMap arrayMap3 = new ArrayMap();
                findNamedViews(arrayMap3, operation2.getFragment().mView);
                arrayMap3.retainAll(sharedElementSourceNames);
                FragmentTransition.retainValues(arrayMap, arrayMap3);
                arrayMap2.retainAll(arrayMap.keySet());
                arrayMap3.retainAll(arrayMap.values());
                if (arrayMap.isEmpty()) {
                    arrayList.clear();
                    arrayList2.clear();
                    view5 = view6;
                    objWrapTransitionInSet = null;
                } else {
                    for (Iterator it3 = arrayMap2.values().iterator(); it3.hasNext(); it3 = it3) {
                        captureTransitioningViews(arrayList, (View) it3.next());
                    }
                    if (arrayList4.isEmpty()) {
                        view2 = view6;
                    } else {
                        view2 = (View) arrayMap2.get(arrayList4.get(0));
                        fragmentTransitionImpl.setEpicenter(objWrapTransitionInSet, view2);
                    }
                    Iterator it4 = arrayMap3.values().iterator();
                    while (it4.hasNext()) {
                        captureTransitioningViews(arrayList2, (View) it4.next());
                    }
                    if (!sharedElementSourceNames.isEmpty() && (view3 = (View) arrayMap3.get(sharedElementSourceNames.get(0))) != null) {
                        OneShotPreDrawListener.add(getContainer(), new Runnable(this) { // from class: androidx.fragment.app.DefaultSpecialEffectsController.6
                            @Override // java.lang.Runnable
                            public void run() {
                                fragmentTransitionImpl.getBoundsOnScreen(view3, rect);
                            }
                        });
                        z2 = true;
                    }
                    fragmentTransitionImpl.addTargets(objWrapTransitionInSet, arrayList);
                    view5 = view2;
                }
            }
            it2 = it;
        }
        View view7 = view5;
        ArrayList arrayList5 = new ArrayList();
        Iterator<TransitionInfo> it5 = list.iterator();
        Object objMergeTransitionsTogether = null;
        Object objMergeTransitionsTogether2 = null;
        while (it5.hasNext()) {
            TransitionInfo next2 = it5.next();
            Iterator<TransitionInfo> it6 = it5;
            Object objCloneTransition = fragmentTransitionImpl.cloneTransition(next2.getTransition());
            ArrayMap arrayMap4 = arrayMap;
            SpecialEffectsController.Operation operation4 = next2.getOperation();
            boolean z3 = objWrapTransitionInSet != null && (operation4 == operation || operation4 == operation3);
            if (objCloneTransition == null) {
                if (!z3) {
                    removeCancellationSignal(next2.getOperation(), next2.getSignal());
                }
                obj = objWrapTransitionInSet;
                view = view7;
            } else {
                ArrayList<View> arrayList6 = new ArrayList<>();
                obj = objWrapTransitionInSet;
                captureTransitioningViews(arrayList6, next2.getOperation().getFragment().mView);
                if (z3) {
                    if (operation4 == operation) {
                        arrayList6.removeAll(arrayList);
                    } else {
                        arrayList6.removeAll(arrayList2);
                    }
                }
                if (arrayList6.isEmpty()) {
                    fragmentTransitionImpl.addTarget(objCloneTransition, view4);
                } else {
                    fragmentTransitionImpl.addTargets(objCloneTransition, arrayList6);
                }
                if (next2.getOperation().getType().equals(SpecialEffectsController.Operation.Type.ADD)) {
                    arrayList5.addAll(arrayList6);
                    if (z2) {
                        fragmentTransitionImpl.setEpicenter(objCloneTransition, rect);
                    }
                    view = view7;
                } else {
                    view = view7;
                    fragmentTransitionImpl.setEpicenter(objCloneTransition, view);
                }
                if (next2.isOverlapAllowed()) {
                    objMergeTransitionsTogether = fragmentTransitionImpl.mergeTransitionsTogether(objMergeTransitionsTogether, objCloneTransition, null);
                } else {
                    objMergeTransitionsTogether2 = fragmentTransitionImpl.mergeTransitionsTogether(objMergeTransitionsTogether2, objCloneTransition, null);
                }
            }
            it5 = it6;
            view7 = view;
            arrayMap = arrayMap4;
            objWrapTransitionInSet = obj;
            operation3 = operation2;
        }
        ArrayMap arrayMap5 = arrayMap;
        Object objMergeTransitionsInSequence = fragmentTransitionImpl.mergeTransitionsInSequence(objMergeTransitionsTogether, objMergeTransitionsTogether2, objWrapTransitionInSet);
        for (final TransitionInfo transitionInfo3 : list) {
            if (transitionInfo3.getTransition() != null) {
                fragmentTransitionImpl.setListenerForTransitionEnd(transitionInfo3.getOperation().getFragment(), objMergeTransitionsInSequence, transitionInfo3.getSignal(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.7
                    @Override // java.lang.Runnable
                    public void run() {
                        DefaultSpecialEffectsController.this.removeCancellationSignal(transitionInfo3.getOperation(), transitionInfo3.getSignal());
                    }
                });
            }
        }
        FragmentTransition.setViewVisibility(arrayList5, 4);
        ArrayList<String> arrayListPrepareSetNameOverridesReordered = fragmentTransitionImpl.prepareSetNameOverridesReordered(arrayList2);
        fragmentTransitionImpl.beginDelayedTransition(getContainer(), objMergeTransitionsInSequence);
        fragmentTransitionImpl.setNameOverridesReordered(getContainer(), arrayList, arrayList2, arrayListPrepareSetNameOverridesReordered, arrayMap5);
        FragmentTransition.setViewVisibility(arrayList5, 0);
        fragmentTransitionImpl.swapSharedElementTargets(objWrapTransitionInSet, arrayList, arrayList2);
    }

    void captureTransitioningViews(ArrayList<View> arrayList, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (ViewGroupCompat.isTransitionGroup(viewGroup)) {
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    captureTransitioningViews(arrayList, childAt);
                }
            }
            return;
        }
        arrayList.add(view);
    }

    void findNamedViews(Map<String, View> map, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    findNamedViews(map, childAt);
                }
            }
        }
    }

    void applyContainerChanges(SpecialEffectsController.Operation operation) {
        View view = operation.getFragment().mView;
        int i = AnonymousClass8.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$Type[operation.getType().ordinal()];
        if (i == 1) {
            view.setVisibility(8);
            return;
        }
        if (i == 2) {
            getContainer().removeView(view);
        } else if (i == 3 || i == 4) {
            view.setVisibility(0);
        }
    }

    private static class AnimationInfo {
        private final SpecialEffectsController.Operation mOperation;
        private final CancellationSignal mSignal;

        AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal) {
            this.mOperation = operation;
            this.mSignal = cancellationSignal;
        }

        SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        CancellationSignal getSignal() {
            return this.mSignal;
        }
    }

    private static class TransitionInfo {
        private final SpecialEffectsController.Operation mOperation;
        private final boolean mOverlapAllowed;
        private final Object mSharedElementTransition;
        private final CancellationSignal mSignal;
        private final Object mTransition;

        TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z, boolean z2) {
            Object enterTransition;
            boolean allowReturnTransitionOverlap;
            Object exitTransition;
            this.mOperation = operation;
            this.mSignal = cancellationSignal;
            if (operation.getType() == SpecialEffectsController.Operation.Type.ADD || operation.getType() == SpecialEffectsController.Operation.Type.SHOW) {
                if (z) {
                    enterTransition = operation.getFragment().getReenterTransition();
                } else {
                    enterTransition = operation.getFragment().getEnterTransition();
                }
                this.mTransition = enterTransition;
                if (z) {
                    allowReturnTransitionOverlap = operation.getFragment().getAllowEnterTransitionOverlap();
                } else {
                    allowReturnTransitionOverlap = operation.getFragment().getAllowReturnTransitionOverlap();
                }
                this.mOverlapAllowed = allowReturnTransitionOverlap;
            } else {
                if (z) {
                    exitTransition = operation.getFragment().getReturnTransition();
                } else {
                    exitTransition = operation.getFragment().getExitTransition();
                }
                this.mTransition = exitTransition;
                this.mOverlapAllowed = true;
            }
            if (!z2) {
                this.mSharedElementTransition = null;
            } else if (z) {
                this.mSharedElementTransition = operation.getFragment().getSharedElementReturnTransition();
            } else {
                this.mSharedElementTransition = operation.getFragment().getSharedElementEnterTransition();
            }
        }

        SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        CancellationSignal getSignal() {
            return this.mSignal;
        }

        Object getTransition() {
            return this.mTransition;
        }

        boolean isOverlapAllowed() {
            return this.mOverlapAllowed;
        }

        public boolean hasSharedElementTransition() {
            return this.mSharedElementTransition != null;
        }

        public Object getSharedElementTransition() {
            return this.mSharedElementTransition;
        }

        FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.mTransition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.mSharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl != null ? handlingImpl : handlingImpl2;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + this.mOperation.getFragment() + " returned Transition " + this.mTransition + " which uses a different Transition  type than its shared element transition " + this.mSharedElementTransition);
        }

        private FragmentTransitionImpl getHandlingImpl(Object obj) {
            if (obj == null) {
                return null;
            }
            FragmentTransitionImpl fragmentTransitionImpl = FragmentTransition.PLATFORM_IMPL;
            if (fragmentTransitionImpl != null && fragmentTransitionImpl.canHandle(obj)) {
                return FragmentTransition.PLATFORM_IMPL;
            }
            FragmentTransitionImpl fragmentTransitionImpl2 = FragmentTransition.SUPPORT_IMPL;
            if (fragmentTransitionImpl2 != null && fragmentTransitionImpl2.canHandle(obj)) {
                return FragmentTransition.SUPPORT_IMPL;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + this.mOperation.getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
