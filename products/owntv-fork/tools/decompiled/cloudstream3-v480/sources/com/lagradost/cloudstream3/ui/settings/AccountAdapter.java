package com.lagradost.cloudstream3.ui.settings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.AccountSingleBinding;
import com.lagradost.cloudstream3.syncproviders.AuthData;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.NoStateAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.utils.ImageLoader;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AccountAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0016\u0010\u000e\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J&\u0010\u0010\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/AccountAdapter;", "Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", "Lcom/lagradost/cloudstream3/syncproviders/AuthData;", "clickCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/ui/settings/AccountClickCallback;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "onCreateContent", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "parent", "Landroid/view/ViewGroup;", "onClearView", "holder", "onBindContent", "item", "position", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AccountAdapter extends NoStateAdapter<AuthData> {
    private final Function1<AccountClickCallback, Unit> clickCallback;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AccountAdapter(Function1<? super AccountClickCallback, Unit> clickCallback) {
        super(new BaseDiffCallback(new Function2() { // from class: com.lagradost.cloudstream3.ui.settings.AccountAdapter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(AccountAdapter._init_$lambda$0((AuthData) obj, (AuthData) obj2));
            }
        }, null, 2, null));
        Intrinsics.checkNotNullParameter(clickCallback, "clickCallback");
        this.clickCallback = clickCallback;
    }

    static final boolean _init_$lambda$0(AuthData a, AuthData b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return a.getUser().getId() == b.getUser().getId();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateContent(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        AccountSingleBinding accountSingleBindingInflate = AccountSingleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(accountSingleBindingInflate, "inflate(...)");
        return new ViewHolderState<>(accountSingleBindingInflate);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onClearView(ViewHolderState<Object> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ViewBinding view = holder.getView();
        AccountSingleBinding accountSingleBinding = view instanceof AccountSingleBinding ? (AccountSingleBinding) view : null;
        if (accountSingleBinding == null) {
            return;
        }
        BaseAdapter.INSTANCE.clearImage(accountSingleBinding.accountProfilePicture);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Object> holder, final AuthData item, int position) {
        String str;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        final AccountSingleBinding accountSingleBinding = view instanceof AccountSingleBinding ? (AccountSingleBinding) view : null;
        if (accountSingleBinding == null) {
            return;
        }
        TextView textView = accountSingleBinding.accountName;
        String name = item.getUser().getName();
        if (name != null) {
            str = name;
        } else {
            str = accountSingleBinding.accountName.getContext().getString(R.string.account) + ' ' + (position + 1);
        }
        textView.setText(str);
        ImageView accountProfilePicture = accountSingleBinding.accountProfilePicture;
        Intrinsics.checkNotNullExpressionValue(accountProfilePicture, "accountProfilePicture");
        accountProfilePicture.setVisibility(0);
        ImageLoader imageLoader = ImageLoader.INSTANCE;
        ImageView accountProfilePicture2 = accountSingleBinding.accountProfilePicture;
        Intrinsics.checkNotNullExpressionValue(accountProfilePicture2, "accountProfilePicture");
        ImageLoader.loadImage$default(imageLoader, accountProfilePicture2, item.getUser().getProfilePicture(), item.getUser().getProfilePictureHeaders(), (Function1) null, 4, (Object) null);
        accountSingleBinding.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.AccountAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AccountAdapter.onBindContent$lambda$0$0(this.f$0, accountSingleBinding, item, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$lambda$0$0(AccountAdapter accountAdapter, AccountSingleBinding accountSingleBinding, AuthData authData, View view) {
        Function1<AccountClickCallback, Unit> function1 = accountAdapter.clickCallback;
        LinearLayout root = accountSingleBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        function1.invoke(new AccountClickCallback(0, root, authData));
    }
}
