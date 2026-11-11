package com.lagradost.cloudstream3.ui.account;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import j$.util.function.Predicate$CC;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: AccountViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000eJ\u0006\u0010\u001a\u001a\u00020\u0018J\u0016\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001eJ\u0016\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001eJ*\u0010 \u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010!\u001a\u00020\u000e2\b\b\u0002\u0010\"\u001a\u00020\u000eR\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u000e0\u000e0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u001c\u0010\u0011\u001a\u0010\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u000e0\u000e0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u001c\u0010\u0013\u001a\u0010\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u00140\u00140\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\f¨\u0006#"}, d2 = {"Lcom/lagradost/cloudstream3/ui/account/AccountViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "getAllAccounts", "", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$Account;", "_accounts", "Landroidx/lifecycle/MutableLiveData;", "accounts", "Landroidx/lifecycle/LiveData;", "getAccounts", "()Landroidx/lifecycle/LiveData;", "_isEditing", "", "kotlin.jvm.PlatformType", "isEditing", "_isAllowedLogin", "isAllowedLogin", "_selectedKeyIndex", "", "selectedKeyIndex", "getSelectedKeyIndex", "setIsEditing", "", ES6Iterator.VALUE_PROPERTY, "toggleIsEditing", "handleAccountUpdate", "account", "context", "Landroid/content/Context;", "handleAccountDelete", "handleAccountSelect", "forStartup", "reloadForActivity", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AccountViewModel extends ViewModel {
    private final MutableLiveData<List<DataStoreHelper.Account>> _accounts;
    private final MutableLiveData<Boolean> _isAllowedLogin;
    private final MutableLiveData<Boolean> _isEditing;
    private final MutableLiveData<Integer> _selectedKeyIndex;
    private final LiveData<List<DataStoreHelper.Account>> accounts;
    private final LiveData<Boolean> isAllowedLogin;
    private final LiveData<Boolean> isEditing;
    private final LiveData<Integer> selectedKeyIndex;

    public AccountViewModel() {
        MutableLiveData<List<DataStoreHelper.Account>> mutableLiveData = new MutableLiveData<>(getAllAccounts());
        this._accounts = mutableLiveData;
        this.accounts = mutableLiveData;
        int i = 0;
        MutableLiveData<Boolean> mutableLiveData2 = new MutableLiveData<>(false);
        this._isEditing = mutableLiveData2;
        this.isEditing = mutableLiveData2;
        MutableLiveData<Boolean> mutableLiveData3 = new MutableLiveData<>(false);
        this._isAllowedLogin = mutableLiveData3;
        this.isAllowedLogin = mutableLiveData3;
        Iterator<DataStoreHelper.Account> it = getAllAccounts().iterator();
        while (it.hasNext()) {
            if (it.next().getKeyIndex() == DataStoreHelper.INSTANCE.getSelectedKeyIndex()) {
                MutableLiveData<Integer> mutableLiveData4 = new MutableLiveData<>(Integer.valueOf(i));
                this._selectedKeyIndex = mutableLiveData4;
                this.selectedKeyIndex = mutableLiveData4;
            }
            i++;
        }
        i = -1;
        MutableLiveData<Integer> mutableLiveData5 = new MutableLiveData<>(Integer.valueOf(i));
        this._selectedKeyIndex = mutableLiveData5;
        this.selectedKeyIndex = mutableLiveData5;
    }

    private final List<DataStoreHelper.Account> getAllAccounts() {
        List<DataStoreHelper.Account> accounts;
        Context context = CloudStreamApp.INSTANCE.getContext();
        return (context == null || (accounts = DataStoreHelper.INSTANCE.getAccounts(context)) == null) ? ArraysKt.toList(DataStoreHelper.INSTANCE.getAccounts()) : accounts;
    }

    public final LiveData<List<DataStoreHelper.Account>> getAccounts() {
        return this.accounts;
    }

    public final LiveData<Boolean> isEditing() {
        return this.isEditing;
    }

    public final LiveData<Boolean> isAllowedLogin() {
        return this.isAllowedLogin;
    }

    public final LiveData<Integer> getSelectedKeyIndex() {
        return this.selectedKeyIndex;
    }

    public final void setIsEditing(boolean value) {
        this._isEditing.postValue(Boolean.valueOf(value));
    }

    public final void toggleIsEditing() {
        MutableLiveData<Boolean> mutableLiveData = this._isEditing;
        Boolean value = mutableLiveData.getValue();
        mutableLiveData.postValue(Boolean.valueOf(!(value != null ? value.booleanValue() : false)));
    }

    public final void handleAccountUpdate(DataStoreHelper.Account account, Context context) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(context, "context");
        List mutableList = CollectionsKt.toMutableList((Collection) DataStoreHelper.INSTANCE.getAccounts(context));
        Iterator it = mutableList.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            } else if (((DataStoreHelper.Account) it.next()).getKeyIndex() == account.getKeyIndex()) {
                break;
            } else {
                i++;
            }
        }
        if (i != -1) {
            mutableList.set(i, account);
        } else {
            mutableList.add(account);
        }
        String currentHomePage = DataStoreHelper.INSTANCE.getCurrentHomePage();
        DataStoreHelper.INSTANCE.setAccount(account);
        DataStoreHelper.INSTANCE.setCurrentHomePage(currentHomePage);
        DataStoreHelper.INSTANCE.setAccounts((DataStoreHelper.Account[]) mutableList.toArray(new DataStoreHelper.Account[0]));
        this._accounts.postValue(DataStoreHelper.INSTANCE.getAccounts(context));
        this._selectedKeyIndex.postValue(Integer.valueOf(DataStoreHelper.INSTANCE.getAccounts(context).indexOf(account)));
        MainActivity.INSTANCE.getReloadAccountEvent().invoke(true);
    }

    public final void handleAccountDelete(final DataStoreHelper.Account account, Context context) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(context, "context");
        CloudStreamApp.INSTANCE.removeKeys(String.valueOf(account.getKeyIndex()));
        List mutableList = CollectionsKt.toMutableList((Collection) DataStoreHelper.INSTANCE.getAccounts(context));
        final Function1 function1 = new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountViewModel$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(AccountViewModel.handleAccountDelete$lambda$0(account, (DataStoreHelper.Account) obj));
            }
        };
        j$.util.Collection.EL.removeIf(mutableList, new Predicate() { // from class: com.lagradost.cloudstream3.ui.account.AccountViewModel$$ExternalSyntheticLambda2
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Boolean) function1.invoke(obj)).booleanValue();
            }
        });
        int i = 0;
        DataStoreHelper.INSTANCE.setAccounts((DataStoreHelper.Account[]) mutableList.toArray(new DataStoreHelper.Account[0]));
        if (account.getKeyIndex() == DataStoreHelper.INSTANCE.getSelectedKeyIndex()) {
            DataStoreHelper.INSTANCE.setAccount(DataStoreHelper.INSTANCE.getDefaultAccount(context));
        }
        this._accounts.postValue(DataStoreHelper.INSTANCE.getAccounts(context));
        MutableLiveData<Integer> mutableLiveData = this._selectedKeyIndex;
        Iterator<DataStoreHelper.Account> it = getAllAccounts().iterator();
        while (it.hasNext()) {
            if (it.next().getKeyIndex() == DataStoreHelper.INSTANCE.getSelectedKeyIndex()) {
                mutableLiveData.postValue(Integer.valueOf(i));
                MainActivity.INSTANCE.getReloadAccountEvent().invoke(true);
            }
            i++;
        }
        i = -1;
        mutableLiveData.postValue(Integer.valueOf(i));
        MainActivity.INSTANCE.getReloadAccountEvent().invoke(true);
    }

    static final boolean handleAccountDelete$lambda$0(DataStoreHelper.Account account, DataStoreHelper.Account it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getKeyIndex() == account.getKeyIndex();
    }

    public static /* synthetic */ void handleAccountSelect$default(AccountViewModel accountViewModel, DataStoreHelper.Account account, Context context, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        accountViewModel.handleAccountSelect(account, context, z, z2);
    }

    public final void handleAccountSelect(final DataStoreHelper.Account account, final Context context, boolean forStartup, boolean reloadForActivity) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(context, "context");
        if (reloadForActivity) {
            this._accounts.postValue(DataStoreHelper.INSTANCE.getAccounts(context));
            this._selectedKeyIndex.postValue(Integer.valueOf(DataStoreHelper.INSTANCE.getAccounts(context).indexOf(account)));
            MainActivity.INSTANCE.getReloadAccountEvent().invoke(true);
        } else {
            if (account.getLockPin() == null) {
                this._isAllowedLogin.postValue(true);
                this._selectedKeyIndex.postValue(Integer.valueOf(DataStoreHelper.INSTANCE.getAccounts(context).indexOf(account)));
                DataStoreHelper.INSTANCE.setAccount(account);
                MainActivity.INSTANCE.getReloadAccountEvent().invoke(true);
                return;
            }
            AccountHelper.showPinInputDialog$default(AccountHelper.INSTANCE, context, account.getLockPin(), false, forStartup, null, new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountViewModel$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AccountViewModel.handleAccountSelect$lambda$0(this.f$0, context, account, (String) obj);
                }
            }, 16, null);
        }
    }

    static final Unit handleAccountSelect$lambda$0(AccountViewModel accountViewModel, Context context, DataStoreHelper.Account account, String str) {
        if (str == null) {
            return Unit.INSTANCE;
        }
        accountViewModel._isAllowedLogin.postValue(true);
        accountViewModel._selectedKeyIndex.postValue(Integer.valueOf(DataStoreHelper.INSTANCE.getAccounts(context).indexOf(account)));
        DataStoreHelper.INSTANCE.setAccount(account);
        MainActivity.INSTANCE.getReloadAccountEvent().invoke(true);
        return Unit.INSTANCE;
    }
}
