package com.lagradost.cloudstream3.ui.result;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.google.android.gms.actions.SearchIntents;
import com.lagradost.cloudstream3.Actor;
import com.lagradost.cloudstream3.ActorData;
import com.lagradost.cloudstream3.ActorRole;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.CastItemBinding;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseAdapterKt;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.NoStateAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.ImageLoader;
import j$.util.Map;
import java.util.HashMap;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ActorAdaptor.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u001d2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001dB+\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0016\u0010\u0017\u001a\u00020\b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0016J&\u0010\u0019\u001a\u00020\b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0004H\u0016J&\u0010\u001c\u001a\u00020\b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0004H\u0016R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u001c\u0010\u0005\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R-\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\rj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e`\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ActorAdaptor;", "Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", "Lcom/lagradost/cloudstream3/ActorData;", "nextFocusUpId", "", "focusCallback", "Lkotlin/Function1;", "Landroid/view/View;", "", "<init>", "(Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)V", "Ljava/lang/Integer;", "inverted", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getInverted", "()Ljava/util/HashMap;", "onCreateContent", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "parent", "Landroid/view/ViewGroup;", "onClearView", "holder", "onUpdateContent", "item", "position", "onBindContent", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ActorAdaptor extends NoStateAdapter<ActorData> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Pair<WeakHashMap<Context, RecyclerView.RecycledViewPool>, Function1<RecyclerView.RecycledViewPool, Unit>> sharedPool = BaseAdapterKt.newSharedPool(new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ActorAdaptor$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ActorAdaptor.sharedPool$lambda$0((RecyclerView.RecycledViewPool) obj);
        }
    });
    private final Function1<View, Unit> focusCallback;
    private final HashMap<ActorData, Boolean> inverted;
    private Integer nextFocusUpId;

    /* JADX INFO: compiled from: ActorAdaptor.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ActorRole.values().length];
            try {
                iArr[ActorRole.Main.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ActorRole.Supporting.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ActorRole.Background.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ActorAdaptor() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public /* synthetic */ ActorAdaptor(Integer num, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ActorAdaptor$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        } : function1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ActorAdaptor(Integer num, Function1<? super View, Unit> focusCallback) {
        super(new BaseDiffCallback(new Function2() { // from class: com.lagradost.cloudstream3.ui.result.ActorAdaptor$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ActorAdaptor._init_$lambda$1((ActorData) obj, (ActorData) obj2));
            }
        }, null, 2, null));
        Intrinsics.checkNotNullParameter(focusCallback, "focusCallback");
        this.nextFocusUpId = num;
        this.focusCallback = focusCallback;
        this.inverted = new HashMap<>();
    }

    static final boolean _init_$lambda$1(ActorData a, ActorData b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return Intrinsics.areEqual(a.getActor().getName(), b.getActor().getName());
    }

    /* JADX INFO: compiled from: ActorAdaptor.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R:\u0010\u0004\u001a+\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ActorAdaptor$Companion;", "", "<init>", "()V", "sharedPool", "Lkotlin/Pair;", "Ljava/util/WeakHashMap;", "Landroid/content/Context;", "Landroidx/recyclerview/widget/RecyclerView$RecycledViewPool;", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "getSharedPool", "()Lkotlin/Pair;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Pair<WeakHashMap<Context, RecyclerView.RecycledViewPool>, Function1<RecyclerView.RecycledViewPool, Unit>> getSharedPool() {
            return ActorAdaptor.sharedPool;
        }
    }

    static final Unit sharedPool$lambda$0(RecyclerView.RecycledViewPool newSharedPool) {
        Intrinsics.checkNotNullParameter(newSharedPool, "$this$newSharedPool");
        newSharedPool.setMaxRecycledViews(268435456, 10);
        return Unit.INSTANCE;
    }

    public final HashMap<ActorData, Boolean> getInverted() {
        return this.inverted;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateContent(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        CastItemBinding castItemBindingInflate = CastItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(castItemBindingInflate, "inflate(...)");
        return new ViewHolderState<>(castItemBindingInflate);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onClearView(ViewHolderState<Object> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ViewBinding view = holder.getView();
        if (view instanceof CastItemBinding) {
            BaseAdapter.INSTANCE.clearImage(((CastItemBinding) view).actorImage);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onUpdateContent(ViewHolderState<Object> holder, ActorData item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        if (view instanceof CastItemBinding) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 0.8f, 1.0f, 1, 0.5f, 1, 0.5f);
            scaleAnimation.setFillAfter(true);
            scaleAnimation.setDuration(200L);
            scaleAnimation.setInterpolator(new OvershootInterpolator());
            ((CastItemBinding) view).voiceActorImageHolder2.startAnimation(scaleAnimation);
        }
        super.onUpdateContent((ViewHolderState) holder, item, position);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x006e  */
    /* JADX WARN: Code duplicated, block: B:22:0x007e  */
    /* JADX WARN: Code duplicated, block: B:23:0x0083  */
    /* JADX WARN: Code duplicated, block: B:52:0x013d  */
    /* JADX WARN: Code duplicated, block: B:54:0x0140  */
    /* JADX WARN: Code duplicated, block: B:56:0x0146  */
    /* JADX WARN: Code duplicated, block: B:57:0x0158  */
    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(final ViewHolderState<Object> holder, final ActorData item, final int position) {
        Pair pair;
        Actor voiceActor;
        String image;
        String roleString;
        Unit unit;
        int i;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        if (view instanceof CastItemBinding) {
            CastItemBinding castItemBinding = (CastItemBinding) view;
            final CardView root = castItemBinding.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
            Object orDefault = Map.EL.getOrDefault(this.inverted, item, false);
            Intrinsics.checkNotNullExpressionValue(orDefault, "getOrDefault(...)");
            final boolean zBooleanValue = ((Boolean) orDefault).booleanValue();
            if (zBooleanValue) {
                Actor voiceActor2 = item.getVoiceActor();
                String image2 = voiceActor2 != null ? voiceActor2.getImage() : null;
                if (image2 == null || StringsKt.isBlank(image2)) {
                    String image3 = item.getActor().getImage();
                    voiceActor = item.getVoiceActor();
                    if (voiceActor != null) {
                        image = voiceActor.getImage();
                    } else {
                        image = null;
                    }
                    pair = new Pair(image3, image);
                } else {
                    Actor voiceActor3 = item.getVoiceActor();
                    pair = new Pair(voiceActor3 != null ? voiceActor3.getImage() : null, item.getActor().getImage());
                }
            } else {
                String image4 = item.getActor().getImage();
                voiceActor = item.getVoiceActor();
                if (voiceActor != null) {
                    image = voiceActor.getImage();
                } else {
                    image = null;
                }
                pair = new Pair(image4, image);
            }
            String str = (String) pair.component1();
            String str2 = (String) pair.component2();
            if (position == 0) {
                root.setNextFocusLeftId(R.id.result_cast_items);
            } else if (position - 1 == getItemCount()) {
                root.setNextFocusRightId(R.id.result_cast_items);
            }
            Integer num = this.nextFocusUpId;
            if (num != null) {
                root.setNextFocusUpId(num.intValue());
            }
            root.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.lagradost.cloudstream3.ui.result.ActorAdaptor$$ExternalSyntheticLambda2
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z) {
                    ActorAdaptor.onBindContent$lambda$1(this.f$0, view2, z);
                }
            });
            root.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ActorAdaptor$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ActorAdaptor.onBindContent$lambda$2(this.f$0, item, zBooleanValue, holder, position, view2);
                }
            });
            root.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ActorAdaptor$$ExternalSyntheticLambda4
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view2) {
                    return ActorAdaptor.onBindContent$lambda$3(item, root, view2);
                }
            });
            ImageLoader imageLoader = ImageLoader.INSTANCE;
            ImageView actorImage = castItemBinding.actorImage;
            Intrinsics.checkNotNullExpressionValue(actorImage, "actorImage");
            ImageLoader.loadImage$default(imageLoader, actorImage, str, (java.util.Map) null, (Function1) null, 6, (Object) null);
            castItemBinding.actorName.setText(item.getActor().getName());
            ActorRole role = item.getRole();
            if (role != null) {
                Context context = castItemBinding.actorExtra.getContext();
                if (context == null) {
                    unit = null;
                } else {
                    int i2 = WhenMappings.$EnumSwitchMapping$0[role.ordinal()];
                    if (i2 == 1) {
                        i = R.string.actor_main;
                    } else if (i2 == 2) {
                        i = R.string.actor_supporting;
                    } else {
                        if (i2 != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        i = R.string.actor_background;
                    }
                    String string = context.getString(i);
                    if (string != null) {
                        TextView actorExtra = castItemBinding.actorExtra;
                        Intrinsics.checkNotNullExpressionValue(actorExtra, "actorExtra");
                        actorExtra.setVisibility(0);
                        castItemBinding.actorExtra.setText(string);
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                }
                if (unit == null) {
                    roleString = item.getRoleString();
                    if (roleString != null) {
                        TextView actorExtra2 = castItemBinding.actorExtra;
                        Intrinsics.checkNotNullExpressionValue(actorExtra2, "actorExtra");
                        actorExtra2.setVisibility(0);
                        castItemBinding.actorExtra.setText(roleString);
                    } else {
                        TextView actorExtra3 = castItemBinding.actorExtra;
                        Intrinsics.checkNotNullExpressionValue(actorExtra3, "actorExtra");
                        actorExtra3.setVisibility(8);
                    }
                }
            } else {
                roleString = item.getRoleString();
                if (roleString != null) {
                    TextView actorExtra4 = castItemBinding.actorExtra;
                    Intrinsics.checkNotNullExpressionValue(actorExtra4, "actorExtra");
                    actorExtra4.setVisibility(0);
                    castItemBinding.actorExtra.setText(roleString);
                } else {
                    TextView actorExtra5 = castItemBinding.actorExtra;
                    Intrinsics.checkNotNullExpressionValue(actorExtra5, "actorExtra");
                    actorExtra5.setVisibility(8);
                }
            }
            if (item.getVoiceActor() == null) {
                CardView voiceActorImageHolder = castItemBinding.voiceActorImageHolder;
                Intrinsics.checkNotNullExpressionValue(voiceActorImageHolder, "voiceActorImageHolder");
                voiceActorImageHolder.setVisibility(8);
                TextView voiceActorName = castItemBinding.voiceActorName;
                Intrinsics.checkNotNullExpressionValue(voiceActorName, "voiceActorName");
                voiceActorName.setVisibility(8);
                return;
            }
            TextView textView = castItemBinding.voiceActorName;
            Actor voiceActor4 = item.getVoiceActor();
            textView.setText(voiceActor4 != null ? voiceActor4.getName() : null);
            String str3 = str2;
            if (str3 != null && str3.length() != 0) {
                CardView voiceActorImageHolder2 = castItemBinding.voiceActorImageHolder;
                Intrinsics.checkNotNullExpressionValue(voiceActorImageHolder2, "voiceActorImageHolder");
                voiceActorImageHolder2.setVisibility(0);
            }
            ImageLoader imageLoader2 = ImageLoader.INSTANCE;
            ImageView voiceActorImage = castItemBinding.voiceActorImage;
            Intrinsics.checkNotNullExpressionValue(voiceActorImage, "voiceActorImage");
            ImageLoader.loadImage$default(imageLoader2, voiceActorImage, str2, (java.util.Map) null, (Function1) null, 6, (Object) null);
        }
    }

    static final void onBindContent$lambda$1(ActorAdaptor actorAdaptor, View view, boolean z) {
        if (z) {
            actorAdaptor.focusCallback.invoke(view);
        }
    }

    static final void onBindContent$lambda$2(ActorAdaptor actorAdaptor, ActorData actorData, boolean z, ViewHolderState viewHolderState, int i, View view) {
        actorAdaptor.inverted.put(actorData, Boolean.valueOf(!z));
        actorAdaptor.onUpdateContent((ViewHolderState<Object>) viewHolderState, actorAdaptor.getItem(i), i);
    }

    static final boolean onBindContent$lambda$3(ActorData actorData, CardView cardView, View view) {
        if (Globals.INSTANCE.isLayout(1)) {
            Intent intent = new Intent("android.intent.action.WEB_SEARCH");
            intent.putExtra(SearchIntents.EXTRA_QUERY, actorData.getActor().getName());
            PackageManager packageManager = cardView.getContext().getPackageManager();
            if (packageManager != null && intent.resolveActivity(packageManager) != null) {
                cardView.getContext().startActivity(intent);
            }
        }
        return true;
    }
}
