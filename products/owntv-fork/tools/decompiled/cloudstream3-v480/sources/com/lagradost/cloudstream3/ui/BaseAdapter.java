package com.lagradost.cloudstream3.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.view.ViewGroupKt;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import coil3.util.CoilUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: BaseAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000g\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u000e\n\u0002\u0010!\n\u0002\b\u0002*\u0001>\b&\u0018\u0000 M*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00050\u0004:\u0001MB!\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00020\u0007¢\u0006\u0002\u0010\u0018J\u0015\u0010\u0019\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0017\u001a\u00020\u0007¢\u0006\u0002\u0010\u0018J$\u0010\u001c\u001a\u00020\u001d2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00132\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J$\u0010!\u001a\u00020\u001d2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\"2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010#\u001a\u00020\u0007H\u0016J+\u0010$\u001a\u00020\u001d2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010&\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00020\u0007H\u0016¢\u0006\u0002\u0010'J+\u0010(\u001a\u00020\u001d2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010&\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00020\u0007H\u0016¢\u0006\u0002\u0010'J\u0016\u0010)\u001a\u00020\u001d2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005H\u0016J\u0016\u0010*\u001a\u00020\u001d2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005H\u0016J\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010,\u001a\u00020-H\u0016J\u001e\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010,\u001a\u00020-2\u0006\u0010/\u001a\u00020\u0007H\u0016J\u0016\u00100\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010,\u001a\u00020-H\u0016J\u001e\u00101\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010,\u001a\u00020-2\u0006\u0010/\u001a\u00020\u0007H\u0016J\u0016\u00102\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010,\u001a\u00020-H\u0016J\u001e\u00103\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010,\u001a\u00020-2\u0006\u0010/\u001a\u00020\u0007H\u0016J\u0016\u00104\u001a\u00020\u001d2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005H\u0016J\u0016\u00105\u001a\u00020\u001d2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005H\u0016J\u000e\u00106\u001a\u00020\u001d2\u0006\u00107\u001a\u000208J\u0006\u00109\u001a\u00020\u001dJ\u001d\u0010:\u001a\u0004\u0018\u00018\u00012\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005H\u0002¢\u0006\u0002\u0010;J\u0016\u0010<\u001a\u00020\u001d2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005H\u0002J\u000e\u0010@\u001a\u00020\u001d2\u0006\u00107\u001a\u000208J\u000e\u0010A\u001a\u00020\u001d2\u0006\u00107\u001a\u000208J\u0015\u0010B\u001a\u00020\u00072\u0006\u0010&\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010CJ\b\u0010D\u001a\u00020\u0007H\u0016J\b\u0010E\u001a\u00020\u0007H\u0016J\u000e\u0010F\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0007J\u0014\u0010G\u001a\u00020\u001d2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005J\u0016\u0010H\u001a\u00020\u001d2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005H\u0016J\u001c\u0010I\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010,\u001a\u00020-2\u0006\u0010/\u001a\u00020\u0007J,\u0010J\u001a\u00020\u001d2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010\u0017\u001a\u00020\u00072\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00020LH\u0016J\u001c\u0010J\u001a\u00020\u001d2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010\u0017\u001a\u00020\u0007R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u0007X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u0007X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010=\u001a\u00020>X\u0082\u0004¢\u0006\u0004\n\u0002\u0010?¨\u0006N"}, d2 = {"Lcom/lagradost/cloudstream3/ui/BaseAdapter;", ExifInterface.GPS_DIRECTION_TRUE, "", ExifInterface.LATITUDE_SOUTH, "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "id", "", "diffCallback", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "<init>", "(ILandroidx/recyclerview/widget/DiffUtil$ItemCallback;)V", "getId", "()I", "footers", "getFooters", "headers", "getHeaders", "immutableCurrentList", "", "getImmutableCurrentList", "()Ljava/util/List;", "getItem", "position", "(I)Ljava/lang/Object;", "getItemOrNull", "mDiffer", "Landroidx/recyclerview/widget/AsyncListDiffer;", "submitIncomparableList", "", "list", "commitCallback", "Ljava/lang/Runnable;", "submitList", "", "getItemCount", "onUpdateContent", "holder", "item", "(Lcom/lagradost/cloudstream3/ui/ViewHolderState;Ljava/lang/Object;I)V", "onBindContent", "onBindFooter", "onBindHeader", "onCreateContent", "parent", "Landroid/view/ViewGroup;", "onCreateCustomContent", "viewType", "onCreateFooter", "onCreateCustomFooter", "onCreateHeader", "onCreateCustomHeader", "onViewAttachedToWindow", "onViewDetachedFromWindow", "save", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "clearState", "getState", "(Lcom/lagradost/cloudstream3/ui/ViewHolderState;)Ljava/lang/Object;", "setState", "attachListener", "com/lagradost/cloudstream3/ui/BaseAdapter$attachListener$1", "Lcom/lagradost/cloudstream3/ui/BaseAdapter$attachListener$1;", "onAttachedToRecyclerView", "onDetachedFromRecyclerView", "customContentViewType", Signatures.MATH_TO_INT32, "customFooterViewType", "customHeaderViewType", "getItemViewType", "onViewRecycled", "onClearView", "onCreateViewHolder", "onBindViewHolder", "payloads", "", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class BaseAdapter<T, S> extends RecyclerView.Adapter<ViewHolderState<S>> {
    public static final int CONTENT = 268435456;
    private static final int CUSTOM_MASK = 268435455;
    public static final int FOOTER = 536870912;
    public static final int HEADER = 805306368;
    private static final int MASK_SIZE = 28;
    private static final int TYPE_MASK = -268435456;
    private final BaseAdapter$attachListener$1 attachListener;
    private final int footers;
    private final int headers;
    private final int id;
    private final AsyncListDiffer<T> mDiffer;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final HashMap<Integer, HashMap<Integer, Object>> layoutManagerStates = new HashMap<>();

    /* JADX WARN: Multi-variable type inference failed */
    public BaseAdapter() {
        this(0, null, 3, 0 == true ? 1 : 0);
    }

    public int customContentViewType(T item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return 0;
    }

    public int customFooterViewType() {
        return 0;
    }

    public int customHeaderViewType() {
        return 0;
    }

    public void onBindContent(ViewHolderState<S> holder, T item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
    }

    public void onBindFooter(ViewHolderState<S> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
    }

    public void onBindHeader(ViewHolderState<S> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
    }

    public void onClearView(ViewHolderState<S> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(ViewHolderState<S> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(ViewHolderState<S> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [com.lagradost.cloudstream3.ui.BaseAdapter$attachListener$1] */
    public BaseAdapter(int i, DiffUtil.ItemCallback<T> diffCallback) {
        Intrinsics.checkNotNullParameter(diffCallback, "diffCallback");
        this.id = i;
        this.mDiffer = new AsyncListDiffer<>(new NonFinalAdapterListUpdateCallback(this) { // from class: com.lagradost.cloudstream3.ui.BaseAdapter$mDiffer$1
            final /* synthetic */ BaseAdapter<T, S> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this);
                this.this$0 = this;
            }

            @Override // com.lagradost.cloudstream3.ui.NonFinalAdapterListUpdateCallback, androidx.recyclerview.widget.ListUpdateCallback
            public void onMoved(int fromPosition, int toPosition) {
                super.onMoved(fromPosition + this.this$0.getHeaders(), toPosition + this.this$0.getHeaders());
            }

            @Override // com.lagradost.cloudstream3.ui.NonFinalAdapterListUpdateCallback, androidx.recyclerview.widget.ListUpdateCallback
            public void onRemoved(int position, int count) {
                super.onRemoved(position + this.this$0.getHeaders(), count);
            }

            @Override // com.lagradost.cloudstream3.ui.NonFinalAdapterListUpdateCallback, androidx.recyclerview.widget.ListUpdateCallback
            public void onChanged(int position, int count, Object payload) {
                super.onChanged(position + this.this$0.getHeaders(), count, payload);
            }

            @Override // com.lagradost.cloudstream3.ui.NonFinalAdapterListUpdateCallback, androidx.recyclerview.widget.ListUpdateCallback
            public void onInserted(int position, int count) {
                super.onInserted(position + this.this$0.getHeaders(), count);
            }
        }, new AsyncDifferConfig.Builder(diffCallback).build());
        this.attachListener = new View.OnAttachStateChangeListener(this) { // from class: com.lagradost.cloudstream3.ui.BaseAdapter$attachListener$1
            final /* synthetic */ BaseAdapter<T, S> this$0;

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View v) {
                Intrinsics.checkNotNullParameter(v, "v");
            }

            {
                this.this$0 = this;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View v) {
                Intrinsics.checkNotNullParameter(v, "v");
                if (v instanceof RecyclerView) {
                    this.this$0.save((RecyclerView) v);
                }
            }
        };
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        onBindViewHolder((ViewHolderState) viewHolder, i, (List<Object>) list);
    }

    public final int getId() {
        return this.id;
    }

    public /* synthetic */ BaseAdapter(int i, BaseDiffCallback baseDiffCallback, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? new BaseDiffCallback(null, null, 3, null) : baseDiffCallback);
    }

    public int getFooters() {
        return this.footers;
    }

    public int getHeaders() {
        return this.headers;
    }

    public final List<T> getImmutableCurrentList() {
        List<T> currentList = this.mDiffer.getCurrentList();
        Intrinsics.checkNotNullExpressionValue(currentList, "getCurrentList(...)");
        return currentList;
    }

    public final T getItem(int position) {
        T t = this.mDiffer.getCurrentList().get(position);
        Intrinsics.checkNotNullExpressionValue(t, "get(...)");
        return t;
    }

    public final T getItemOrNull(int position) {
        List<T> currentList = this.mDiffer.getCurrentList();
        Intrinsics.checkNotNullExpressionValue(currentList, "getCurrentList(...)");
        return (T) CollectionsKt.getOrNull(currentList, position);
    }

    public static /* synthetic */ void submitIncomparableList$default(BaseAdapter baseAdapter, List list, Runnable runnable, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: submitIncomparableList");
        }
        if ((i & 2) != 0) {
            runnable = null;
        }
        baseAdapter.submitIncomparableList(list, runnable);
    }

    public void submitIncomparableList(List<? extends T> list, Runnable commitCallback) {
        submitList$default(this, null, null, 2, null);
        submitList(list, commitCallback);
    }

    public static /* synthetic */ void submitList$default(BaseAdapter baseAdapter, Collection collection, Runnable runnable, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: submitList");
        }
        if ((i & 2) != 0) {
            runnable = null;
        }
        baseAdapter.submitList(collection, runnable);
    }

    public void submitList(Collection<? extends T> list, Runnable commitCallback) {
        if (list == null || list.isEmpty()) {
            this.mDiffer.submitList(null, commitCallback);
        } else {
            this.mDiffer.submitList(new CopyOnWriteArrayList(list), commitCallback);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mDiffer.getCurrentList().size() + getFooters() + getHeaders();
    }

    public void onUpdateContent(ViewHolderState<S> holder, T item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        onBindContent(holder, item, position);
    }

    public ViewHolderState<S> onCreateContent(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        throw new NotImplementedError(null, 1, null);
    }

    public ViewHolderState<S> onCreateCustomContent(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return onCreateContent(parent);
    }

    public ViewHolderState<S> onCreateFooter(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        throw new NotImplementedError(null, 1, null);
    }

    public ViewHolderState<S> onCreateCustomFooter(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return onCreateFooter(parent);
    }

    public ViewHolderState<S> onCreateHeader(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        throw new NotImplementedError(null, 1, null);
    }

    public ViewHolderState<S> onCreateCustomHeader(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return onCreateHeader(parent);
    }

    public final void save(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Iterator<View> it = ViewGroupKt.getChildren(recyclerView).iterator();
        while (it.hasNext()) {
            RecyclerView.ViewHolder viewHolderFindContainingViewHolder = recyclerView.findContainingViewHolder(it.next());
            ViewHolderState<S> viewHolderState = viewHolderFindContainingViewHolder instanceof ViewHolderState ? (ViewHolderState) viewHolderFindContainingViewHolder : null;
            if (viewHolderState != null) {
                setState(viewHolderState);
            }
        }
    }

    public final void clearState() {
        HashMap<Integer, Object> map = layoutManagerStates.get(Integer.valueOf(this.id));
        if (map != null) {
            map.clear();
        }
    }

    private final S getState(ViewHolderState<S> holder) {
        HashMap<Integer, Object> map = layoutManagerStates.get(Integer.valueOf(this.id));
        S s = map != null ? (S) map.get(Integer.valueOf(holder.getAbsoluteAdapterPosition())) : null;
        if (s == null) {
            return null;
        }
        return s;
    }

    private final void setState(ViewHolderState<S> holder) {
        int i = this.id;
        if (i == 0) {
            return;
        }
        HashMap<Integer, HashMap<Integer, Object>> map = layoutManagerStates;
        if (!map.containsKey(Integer.valueOf(i))) {
            map.put(Integer.valueOf(this.id), new HashMap<>());
        }
        HashMap<Integer, Object> map2 = map.get(Integer.valueOf(this.id));
        if (map2 != null) {
            map2.put(Integer.valueOf(holder.getAbsoluteAdapterPosition()), holder.save());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        recyclerView.addOnAttachStateChangeListener(this.attachListener);
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        recyclerView.removeOnAttachStateChangeListener(this.attachListener);
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int position) {
        int iCustomContentViewType;
        int i;
        if (position < getHeaders()) {
            i = HEADER;
            iCustomContentViewType = customHeaderViewType();
        } else {
            int headers = position - getHeaders();
            if (headers >= this.mDiffer.getCurrentList().size()) {
                i = 536870912;
                iCustomContentViewType = customFooterViewType();
            } else {
                iCustomContentViewType = customContentViewType(getItem(headers));
                i = 268435456;
            }
        }
        return iCustomContentViewType | i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onViewRecycled(ViewHolderState<S> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        setState(holder);
        onClearView(holder);
        super.onViewRecycled(holder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final ViewHolderState<S> onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        int i = TYPE_MASK & viewType;
        if (i == 268435456) {
            return onCreateCustomContent(parent, viewType & CUSTOM_MASK);
        }
        if (i == 536870912) {
            return onCreateCustomFooter(parent, viewType & CUSTOM_MASK);
        }
        if (i == 805306368) {
            return onCreateCustomHeader(parent, viewType & CUSTOM_MASK);
        }
        throw new NotImplementedError(null, 1, null);
    }

    public void onBindViewHolder(ViewHolderState<S> holder, int position, List<Object> payloads) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
            return;
        }
        int itemViewType = getItemViewType(position) & TYPE_MASK;
        if (itemViewType == 268435456) {
            int headers = position - getHeaders();
            onUpdateContent(holder, getItem(headers), headers);
        } else if (itemViewType == 536870912) {
            onBindFooter(holder);
        } else {
            if (itemViewType != 805306368) {
                return;
            }
            onBindHeader(holder);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(ViewHolderState<S> holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        int itemViewType = getItemViewType(position) & TYPE_MASK;
        if (itemViewType == 268435456) {
            int headers = position - getHeaders();
            onBindContent(holder, getItem(headers), headers);
        } else if (itemViewType == 536870912) {
            onBindFooter(holder);
        } else if (itemViewType == 805306368) {
            onBindHeader(holder);
        }
        S state = getState(holder);
        if (state != null) {
            holder.restore(state);
        }
    }

    /* JADX INFO: compiled from: BaseAdapter.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rRm\u0010\u0004\u001a^\u0012\u0004\u0012\u00020\u0006\u0012$\u0012\"\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00070\u0005j.\u0012\u0004\u0012\u00020\u0006\u0012$\u0012\"\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u0007`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u000e\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/lagradost/cloudstream3/ui/BaseAdapter$Companion;", "", "<init>", "()V", "layoutManagerStates", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getLayoutManagerStates", "()Ljava/util/HashMap;", "clearImage", "", "image", "Landroid/widget/ImageView;", "MASK_SIZE", "CUSTOM_MASK", "TYPE_MASK", "HEADER", "FOOTER", "CONTENT", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HashMap<Integer, HashMap<Integer, Object>> getLayoutManagerStates() {
            return BaseAdapter.layoutManagerStates;
        }

        public final void clearImage(ImageView image) {
            if (image != null) {
                CoilUtils.dispose(image);
            }
        }
    }
}
