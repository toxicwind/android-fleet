package com.lagradost.cloudstream3.ui.settings;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.databinding.ItemLogcatBinding;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.NoStateAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LogcatAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J&\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/LogcatAdapter;", "Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", "", "<init>", "()V", "onCreateContent", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "parent", "Landroid/view/ViewGroup;", "onBindContent", "", "holder", "item", "position", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LogcatAdapter extends NoStateAdapter<String> {

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.settings.LogcatAdapter$1, reason: invalid class name */
    /* JADX INFO: compiled from: LogcatAdapter.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function2<String, Object, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(2, String.class, "equals", "equals(Ljava/lang/Object;)Z", 0);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Boolean invoke(String p0, Object obj) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return Boolean.valueOf(p0.equals(obj));
        }
    }

    public LogcatAdapter() {
        super(new BaseDiffCallback(AnonymousClass1.INSTANCE, AnonymousClass2.INSTANCE));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.settings.LogcatAdapter$2, reason: invalid class name */
    /* JADX INFO: compiled from: LogcatAdapter.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function2<String, Object, Boolean> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        AnonymousClass2() {
            super(2, String.class, "equals", "equals(Ljava/lang/Object;)Z", 0);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Boolean invoke(String p0, Object obj) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return Boolean.valueOf(p0.equals(obj));
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateContent(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemLogcatBinding itemLogcatBindingInflate = ItemLogcatBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(itemLogcatBindingInflate, "inflate(...)");
        return new ViewHolderState<>(itemLogcatBindingInflate);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Object> holder, String item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        ItemLogcatBinding itemLogcatBinding = view instanceof ItemLogcatBinding ? (ItemLogcatBinding) view : null;
        if (itemLogcatBinding != null) {
            itemLogcatBinding.logText.setText(item);
        }
    }
}
