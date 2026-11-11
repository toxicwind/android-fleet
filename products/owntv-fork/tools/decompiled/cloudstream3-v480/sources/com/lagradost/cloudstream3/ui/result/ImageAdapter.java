package com.lagradost.cloudstream3.ui.result;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.databinding.ResultMiniImageBinding;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseAdapterKt;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.NoStateAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.ImageLoader;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImageAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001aB7\u0012\u0016\b\u0002\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u0015\u001a\u00020\u00052\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016J&\u0010\u0017\u001a\u00020\u00052\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0002H\u0016R\u001f\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ImageAdapter;", "Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", "", "clickCallback", "Lkotlin/Function1;", "", "nextFocusUp", "nextFocusDown", "<init>", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getClickCallback", "()Lkotlin/jvm/functions/Function1;", "getNextFocusUp", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getNextFocusDown", "onCreateContent", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "parent", "Landroid/view/ViewGroup;", "onClearView", "holder", "onBindContent", "item", "position", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ImageAdapter extends NoStateAdapter<Integer> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Pair<WeakHashMap<Context, RecyclerView.RecycledViewPool>, Function1<RecyclerView.RecycledViewPool, Unit>> sharedPool = BaseAdapterKt.newSharedPool(new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ImageAdapter$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ImageAdapter.sharedPool$lambda$0((RecyclerView.RecycledViewPool) obj);
        }
    });
    private final Function1<Integer, Unit> clickCallback;
    private final Integer nextFocusDown;
    private final Integer nextFocusUp;

    public ImageAdapter() {
        this(null, null, null, 7, null);
    }

    public /* synthetic */ ImageAdapter(Function1 function1, Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : function1, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public /* bridge */ /* synthetic */ void onBindContent(ViewHolderState<Object> viewHolderState, Object obj, int i) {
        onBindContent(viewHolderState, ((Number) obj).intValue(), i);
    }

    public final Function1<Integer, Unit> getClickCallback() {
        return this.clickCallback;
    }

    public final Integer getNextFocusUp() {
        return this.nextFocusUp;
    }

    public final Integer getNextFocusDown() {
        return this.nextFocusDown;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ImageAdapter$1, reason: invalid class name */
    /* JADX INFO: compiled from: ImageAdapter.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function2<Integer, Object, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(2, Integer.TYPE, "equals", "equals(Ljava/lang/Object;)Z", 0);
        }

        public final Boolean invoke(int i, Object obj) {
            return Boolean.valueOf(Integer.valueOf(i).equals(obj));
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Boolean invoke(Integer num, Object obj) {
            return invoke(num.intValue(), obj);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ImageAdapter(Function1<? super Integer, Unit> function1, Integer num, Integer num2) {
        super(new BaseDiffCallback(AnonymousClass1.INSTANCE, AnonymousClass2.INSTANCE));
        this.clickCallback = function1;
        this.nextFocusUp = num;
        this.nextFocusDown = num2;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ImageAdapter$2, reason: invalid class name */
    /* JADX INFO: compiled from: ImageAdapter.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function2<Integer, Object, Boolean> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        AnonymousClass2() {
            super(2, Integer.TYPE, "equals", "equals(Ljava/lang/Object;)Z", 0);
        }

        public final Boolean invoke(int i, Object obj) {
            return Boolean.valueOf(Integer.valueOf(i).equals(obj));
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Boolean invoke(Integer num, Object obj) {
            return invoke(num.intValue(), obj);
        }
    }

    /* JADX INFO: compiled from: ImageAdapter.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R:\u0010\u0004\u001a+\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ImageAdapter$Companion;", "", "<init>", "()V", "sharedPool", "Lkotlin/Pair;", "Ljava/util/WeakHashMap;", "Landroid/content/Context;", "Landroidx/recyclerview/widget/RecyclerView$RecycledViewPool;", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "getSharedPool", "()Lkotlin/Pair;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Pair<WeakHashMap<Context, RecyclerView.RecycledViewPool>, Function1<RecyclerView.RecycledViewPool, Unit>> getSharedPool() {
            return ImageAdapter.sharedPool;
        }
    }

    static final Unit sharedPool$lambda$0(RecyclerView.RecycledViewPool newSharedPool) {
        Intrinsics.checkNotNullParameter(newSharedPool, "$this$newSharedPool");
        newSharedPool.setMaxRecycledViews(268435456, 10);
        return Unit.INSTANCE;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateContent(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ResultMiniImageBinding resultMiniImageBindingInflate = ResultMiniImageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(resultMiniImageBindingInflate, "inflate(...)");
        return new ViewHolderState<>(resultMiniImageBindingInflate);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onClearView(ViewHolderState<Object> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ViewBinding view = holder.getView();
        ResultMiniImageBinding resultMiniImageBinding = view instanceof ResultMiniImageBinding ? (ResultMiniImageBinding) view : null;
        if (resultMiniImageBinding == null) {
            return;
        }
        BaseAdapter.INSTANCE.clearImage(resultMiniImageBinding.getRoot());
    }

    public void onBindContent(ViewHolderState<Object> holder, int item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ViewBinding view = holder.getView();
        ResultMiniImageBinding resultMiniImageBinding = view instanceof ResultMiniImageBinding ? (ResultMiniImageBinding) view : null;
        if (resultMiniImageBinding == null) {
            return;
        }
        ImageView root = resultMiniImageBinding.getRoot();
        ImageLoader imageLoader = ImageLoader.INSTANCE;
        Intrinsics.checkNotNull(root);
        ImageLoader.loadImage$default(imageLoader, root, Integer.valueOf(item), (Function1) null, 2, (Object) null);
        Integer num = this.nextFocusDown;
        if (num != null) {
            root.setNextFocusDownId(num.intValue());
        }
        Integer num2 = this.nextFocusUp;
        if (num2 != null) {
            root.setNextFocusUpId(num2.intValue());
        }
        if (this.clickCallback != null) {
            if (Globals.INSTANCE.isLayout(2)) {
                root.setClickable(true);
                root.setLongClickable(true);
                root.setFocusable(true);
                root.setFocusableInTouchMode(true);
            }
            root.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ImageAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ImageAdapter.onBindContent$lambda$0$0(this.f$0, view2);
                }
            });
            root.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ImageAdapter$$ExternalSyntheticLambda1
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view2) {
                    return ImageAdapter.onBindContent$lambda$0$1(this.f$0, view2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$lambda$0$0(ImageAdapter imageAdapter, View view) {
        imageAdapter.clickCallback.invoke(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindContent$lambda$0$1(ImageAdapter imageAdapter, View view) {
        imageAdapter.clickCallback.invoke(1);
        return true;
    }
}
