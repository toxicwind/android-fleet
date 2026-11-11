package com.lagradost.cloudstream3.ui.account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.viewbinding.ViewBinding;
import coil3.request.ImageRequest;
import coil3.transform.RoundedCornersTransformation;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.AccountListItemAddBinding;
import com.lagradost.cloudstream3.databinding.AccountListItemBinding;
import com.lagradost.cloudstream3.databinding.AccountListItemEditBinding;
import com.lagradost.cloudstream3.ui.NoStateAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.ImageLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;

/* JADX INFO: compiled from: AccountAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001f2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001fBW\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0002H\u0016J&\u0010\u0015\u001a\u00020\u00052\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\fH\u0016J\u0016\u0010\u001a\u001a\u00020\u00052\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0016J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000e\"\u0004\b\u0011\u0010\u0012¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/ui/account/AccountAdapter;", "Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$Account;", "accountSelectCallback", "Lkotlin/Function1;", "", "accountCreateCallback", "accountEditCallback", "accountDeleteCallback", "<init>", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "footers", "", "getFooters", "()I", "viewType", "getViewType", "setViewType", "(I)V", "customContentViewType", "item", "onBindContent", "holder", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "position", "onBindFooter", "onCreateFooter", "parent", "Landroid/view/ViewGroup;", "onCreateContent", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AccountAdapter extends NoStateAdapter<DataStoreHelper.Account> {
    public static final int VIEW_TYPE_EDIT_ACCOUNT = 2;
    public static final int VIEW_TYPE_SELECT_ACCOUNT = 0;
    private final Function1<DataStoreHelper.Account, Unit> accountCreateCallback;
    private final Function1<DataStoreHelper.Account, Unit> accountDeleteCallback;
    private final Function1<DataStoreHelper.Account, Unit> accountEditCallback;
    private final Function1<DataStoreHelper.Account, Unit> accountSelectCallback;
    private final int footers;
    private int viewType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AccountAdapter(Function1<? super DataStoreHelper.Account, Unit> accountSelectCallback, Function1<? super DataStoreHelper.Account, Unit> accountCreateCallback, Function1<? super DataStoreHelper.Account, Unit> accountEditCallback, Function1<? super DataStoreHelper.Account, Unit> accountDeleteCallback) {
        super(null, 1, null);
        Intrinsics.checkNotNullParameter(accountSelectCallback, "accountSelectCallback");
        Intrinsics.checkNotNullParameter(accountCreateCallback, "accountCreateCallback");
        Intrinsics.checkNotNullParameter(accountEditCallback, "accountEditCallback");
        Intrinsics.checkNotNullParameter(accountDeleteCallback, "accountDeleteCallback");
        this.accountSelectCallback = accountSelectCallback;
        this.accountCreateCallback = accountCreateCallback;
        this.accountEditCallback = accountEditCallback;
        this.accountDeleteCallback = accountDeleteCallback;
        this.footers = 1;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public int getFooters() {
        return this.footers;
    }

    public final int getViewType() {
        return this.viewType;
    }

    public final void setViewType(int i) {
        this.viewType = i;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public int customContentViewType(DataStoreHelper.Account item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return this.viewType;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Object> holder, final DataStoreHelper.Account item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        if (view instanceof AccountListItemBinding) {
            final AccountListItemBinding accountListItemBinding = (AccountListItemBinding) view;
            boolean z = Globals.INSTANCE.isLayout(6) || !accountListItemBinding.getRoot().isInTouchMode();
            boolean z2 = item.getKeyIndex() == DataStoreHelper.INSTANCE.getSelectedKeyIndex();
            accountListItemBinding.accountName.setText(item.getName());
            ImageLoader imageLoader = ImageLoader.INSTANCE;
            ImageView accountImage = accountListItemBinding.accountImage;
            Intrinsics.checkNotNullExpressionValue(accountImage, "accountImage");
            ImageLoader.loadImage$default(imageLoader, accountImage, item.getImage(), (Function1) null, 2, (Object) null);
            ImageView lockIcon = accountListItemBinding.lockIcon;
            Intrinsics.checkNotNullExpressionValue(lockIcon, "lockIcon");
            lockIcon.setVisibility(item.getLockPin() != null ? 0 : 8);
            View outline = accountListItemBinding.outline;
            Intrinsics.checkNotNullExpressionValue(outline, "outline");
            outline.setVisibility(!z && z2 ? 0 : 8);
            if (z) {
                accountListItemBinding.getRoot().setFocusableInTouchMode(true);
                if (z2) {
                    accountListItemBinding.getRoot().requestFocus();
                }
                accountListItemBinding.getRoot().setForeground(ContextCompat.getDrawable(accountListItemBinding.getRoot().getContext(), R.drawable.outline_drawable));
            } else {
                accountListItemBinding.getRoot().setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountAdapter$$ExternalSyntheticLambda6
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view2) {
                        return AccountAdapter.onBindContent$lambda$0$0(accountListItemBinding, item, this, view2);
                    }
                });
            }
            accountListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountAdapter$$ExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AccountAdapter.onBindContent$lambda$0$1(this.f$0, item, view2);
                }
            });
            return;
        }
        if (view instanceof AccountListItemEditBinding) {
            final AccountListItemEditBinding accountListItemEditBinding = (AccountListItemEditBinding) view;
            boolean z3 = Globals.INSTANCE.isLayout(6) || !accountListItemEditBinding.getRoot().isInTouchMode();
            boolean z4 = item.getKeyIndex() == DataStoreHelper.INSTANCE.getSelectedKeyIndex();
            accountListItemEditBinding.accountName.setText(item.getName());
            ImageLoader imageLoader2 = ImageLoader.INSTANCE;
            ImageView accountImage2 = accountListItemEditBinding.accountImage;
            Intrinsics.checkNotNullExpressionValue(accountImage2, "accountImage");
            imageLoader2.loadImage(accountImage2, item.getImage(), new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountAdapter$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AccountAdapter.onBindContent$lambda$1$0((ImageRequest.Builder) obj);
                }
            });
            ImageView lockIcon2 = accountListItemEditBinding.lockIcon;
            Intrinsics.checkNotNullExpressionValue(lockIcon2, "lockIcon");
            lockIcon2.setVisibility(item.getLockPin() != null ? 0 : 8);
            View outline2 = accountListItemEditBinding.outline;
            Intrinsics.checkNotNullExpressionValue(outline2, "outline");
            outline2.setVisibility(!z3 && z4 ? 0 : 8);
            if (z3) {
                accountListItemEditBinding.getRoot().setFocusableInTouchMode(true);
                if (z4) {
                    accountListItemEditBinding.getRoot().requestFocus();
                }
                accountListItemEditBinding.getRoot().setForeground(ContextCompat.getDrawable(accountListItemEditBinding.getRoot().getContext(), R.drawable.outline_drawable));
            }
            accountListItemEditBinding.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountAdapter$$ExternalSyntheticLambda9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AccountAdapter.onBindContent$lambda$1$1(accountListItemEditBinding, item, this, view2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindContent$lambda$0$0(AccountListItemBinding accountListItemBinding, DataStoreHelper.Account account, final AccountAdapter accountAdapter, View view) {
        AccountHelper accountHelper = AccountHelper.INSTANCE;
        Context context = accountListItemBinding.getRoot().getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        accountHelper.showAccountEditDialog(context, account, false, new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountAdapter$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountAdapter.onBindContent$lambda$0$0$0(this.f$0, (DataStoreHelper.Account) obj);
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountAdapter$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountAdapter.onBindContent$lambda$0$0$1(this.f$0, (DataStoreHelper.Account) obj);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindContent$lambda$0$0$0(AccountAdapter accountAdapter, DataStoreHelper.Account account) {
        Intrinsics.checkNotNullParameter(account, "account");
        accountAdapter.accountEditCallback.invoke(account);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindContent$lambda$0$0$1(AccountAdapter accountAdapter, DataStoreHelper.Account account) {
        Intrinsics.checkNotNullParameter(account, "account");
        accountAdapter.accountDeleteCallback.invoke(account);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$lambda$0$1(AccountAdapter accountAdapter, DataStoreHelper.Account account, View view) {
        accountAdapter.accountSelectCallback.invoke(account);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindContent$lambda$1$0(ImageRequest.Builder loadImage) {
        Intrinsics.checkNotNullParameter(loadImage, "$this$loadImage");
        new RoundedCornersTransformation(10.0f);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$lambda$1$1(AccountListItemEditBinding accountListItemEditBinding, DataStoreHelper.Account account, final AccountAdapter accountAdapter, View view) {
        AccountHelper accountHelper = AccountHelper.INSTANCE;
        Context context = accountListItemEditBinding.getRoot().getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        accountHelper.showAccountEditDialog(context, account, false, new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountAdapter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountAdapter.onBindContent$lambda$1$1$0(this.f$0, (DataStoreHelper.Account) obj);
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountAdapter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountAdapter.onBindContent$lambda$1$1$1(this.f$0, (DataStoreHelper.Account) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindContent$lambda$1$1$0(AccountAdapter accountAdapter, DataStoreHelper.Account account) {
        Intrinsics.checkNotNullParameter(account, "account");
        accountAdapter.accountEditCallback.invoke(account);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindContent$lambda$1$1$1(AccountAdapter accountAdapter, DataStoreHelper.Account account) {
        Intrinsics.checkNotNullParameter(account, "account");
        accountAdapter.accountDeleteCallback.invoke(account);
        return Unit.INSTANCE;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindFooter(ViewHolderState<Object> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ViewBinding view = holder.getView();
        final AccountListItemAddBinding accountListItemAddBinding = view instanceof AccountListItemAddBinding ? (AccountListItemAddBinding) view : null;
        if (accountListItemAddBinding == null) {
            return;
        }
        accountListItemAddBinding.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountAdapter$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AccountAdapter.onBindFooter$lambda$0$0(this.f$0, accountListItemAddBinding, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindFooter$lambda$0$0(final AccountAdapter accountAdapter, AccountListItemAddBinding accountListItemAddBinding, View view) {
        Integer num;
        List<DataStoreHelper.Account> immutableCurrentList = accountAdapter.getImmutableCurrentList();
        Set set = ArraysKt.toSet(DataStoreHelper.INSTANCE.getProfileImages());
        List<DataStoreHelper.Account> list = immutableCurrentList;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((DataStoreHelper.Account) obj).getCustomImage() == null) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Integer num2 = (Integer) ArraysKt.getOrNull(DataStoreHelper.INSTANCE.getProfileImages(), ((DataStoreHelper.Account) it.next()).getDefaultImageIndex());
            if (num2 != null) {
                arrayList2.add(num2);
            }
        }
        Set setMinus = SetsKt.minus(set, (Iterable) CollectionsKt.toSet(arrayList2));
        Integer[] profileImages = DataStoreHelper.INSTANCE.getProfileImages();
        Integer num3 = (Integer) CollectionsKt.randomOrNull(setMinus, Random.INSTANCE);
        int iIndexOf = ArraysKt.indexOf(profileImages, Integer.valueOf(num3 != null ? num3.intValue() : ((Number) ArraysKt.random(DataStoreHelper.INSTANCE.getProfileImages(), Random.INSTANCE)).intValue()));
        Iterator<T> it2 = list.iterator();
        if (it2.hasNext()) {
            Integer numValueOf = Integer.valueOf(((DataStoreHelper.Account) it2.next()).getKeyIndex());
            while (it2.hasNext()) {
                Integer numValueOf2 = Integer.valueOf(((DataStoreHelper.Account) it2.next()).getKeyIndex());
                if (numValueOf.compareTo(numValueOf2) < 0) {
                    numValueOf = numValueOf2;
                }
            }
            num = numValueOf;
        } else {
            num = null;
        }
        Integer num4 = num;
        int iIntValue = (num4 != null ? num4.intValue() : 0) + 1;
        String string = accountListItemAddBinding.getRoot().getContext().getString(R.string.account);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        AccountHelper accountHelper = AccountHelper.INSTANCE;
        Context context = accountListItemAddBinding.getRoot().getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        accountHelper.showAccountEditDialog(context, new DataStoreHelper.Account(iIntValue, string + ' ' + iIntValue, null, iIndexOf, null, 16, null), true, new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountAdapter$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return AccountAdapter.onBindFooter$lambda$0$0$3(this.f$0, (DataStoreHelper.Account) obj2);
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountAdapter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return AccountAdapter.onBindFooter$lambda$0$0$4((DataStoreHelper.Account) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindFooter$lambda$0$0$3(AccountAdapter accountAdapter, DataStoreHelper.Account account) {
        Intrinsics.checkNotNullParameter(account, "account");
        accountAdapter.accountCreateCallback.invoke(account);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindFooter$lambda$0$0$4(DataStoreHelper.Account it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateFooter(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        AccountListItemAddBinding accountListItemAddBindingInflate = AccountListItemAddBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(accountListItemAddBindingInflate, "inflate(...)");
        return new ViewHolderState<>(accountListItemAddBindingInflate);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateContent(ViewGroup parent) {
        AccountListItemBinding accountListItemBinding;
        Intrinsics.checkNotNullParameter(parent, "parent");
        int i = this.viewType;
        if (i == 0) {
            AccountListItemBinding accountListItemBindingInflate = AccountListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            Intrinsics.checkNotNull(accountListItemBindingInflate);
            accountListItemBinding = accountListItemBindingInflate;
        } else if (i == 2) {
            AccountListItemEditBinding accountListItemEditBindingInflate = AccountListItemEditBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            Intrinsics.checkNotNull(accountListItemEditBindingInflate);
            accountListItemBinding = accountListItemEditBindingInflate;
        } else {
            throw new IllegalArgumentException("Invalid view type");
        }
        return new ViewHolderState<>(accountListItemBinding);
    }
}
