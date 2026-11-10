package com.android.settingslib.drawer;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public abstract class SwitchesProvider extends ContentProvider {
    private String mAuthority;
    private final Map<String, SwitchController> mControllerMap = new LinkedHashMap();
    private final List<Bundle> mSwitchDataList = new ArrayList();

    protected abstract List<SwitchController> createSwitchControllers();

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        String str = providerInfo.authority;
        this.mAuthority = str;
        Log.i("SwitchesProvider", str);
        super.attachInfo(context, providerInfo);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        List<SwitchController> listCreateSwitchControllers = createSwitchControllers();
        if (listCreateSwitchControllers == null || listCreateSwitchControllers.isEmpty()) {
            throw new IllegalArgumentException();
        }
        listCreateSwitchControllers.forEach(new Consumer() { // from class: com.android.settingslib.drawer.-$$Lambda$SwitchesProvider$Todk-P6pI0mvKmdxKm2pz5ruW68
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$onCreate$0$SwitchesProvider((SwitchController) obj);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreate$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreate$0$SwitchesProvider(SwitchController switchController) {
        String switchKey = switchController.getSwitchKey();
        if (TextUtils.isEmpty(switchKey)) {
            throw new NullPointerException("Switch key cannot be null: " + switchController.getClass().getSimpleName());
        }
        if (this.mControllerMap.containsKey(switchKey)) {
            throw new IllegalArgumentException("Switch key " + switchKey + " is duplicated by: " + switchController.getClass().getSimpleName());
        }
        switchController.setAuthority(this.mAuthority);
        this.mControllerMap.put(switchKey, switchController);
        this.mSwitchDataList.add(switchController.getBundle());
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        String string = bundle != null ? bundle.getString("com.android.settings.keyhint") : null;
        if (TextUtils.isEmpty(string)) {
            if (!"getSwitchData".equals(str)) {
                return null;
            }
            bundle2.putParcelableList("switch_data", this.mSwitchDataList);
            return bundle2;
        }
        SwitchController switchController = this.mControllerMap.get(string);
        if (switchController == 0) {
            return null;
        }
        byte b = -1;
        switch (str.hashCode()) {
            case -2097433649:
                if (str.equals("getDynamicTitle")) {
                    b = 2;
                }
                break;
            case -1844463779:
                if (str.equals("getDynamicSummary")) {
                    b = 3;
                }
                break;
            case -952092468:
                if (str.equals("onCheckedChanged")) {
                    b = 5;
                }
                break;
            case -645523212:
                if (str.equals("getSwitchData")) {
                    b = 0;
                }
                break;
            case 162535197:
                if (str.equals("isChecked")) {
                    b = 4;
                }
                break;
            case 1207101568:
                if (str.equals("getProviderIcon")) {
                    b = 1;
                }
                break;
        }
        if (b == 0) {
            return switchController.getBundle();
        }
        if (b != 1) {
            if (b != 2) {
                if (b != 3) {
                    if (b == 4) {
                        bundle2.putBoolean("checked_state", switchController.isChecked());
                        return bundle2;
                    }
                    if (b == 5) {
                        return onCheckedChanged(bundle.getBoolean("checked_state"), switchController);
                    }
                } else if (switchController instanceof DynamicSummary) {
                    bundle2.putString("com.android.settings.summary", ((DynamicSummary) switchController).getDynamicSummary());
                    return bundle2;
                }
            } else if (switchController instanceof DynamicTitle) {
                bundle2.putString("com.android.settings.title", ((DynamicTitle) switchController).getDynamicTitle());
                return bundle2;
            }
        } else if (switchController instanceof ProviderIcon) {
            return ((ProviderIcon) switchController).getProviderIcon();
        }
        return null;
    }

    private Bundle onCheckedChanged(boolean z, SwitchController switchController) {
        boolean zOnCheckedChanged = switchController.onCheckedChanged(z);
        Bundle bundle = new Bundle();
        bundle.putBoolean("set_checked_error", !zOnCheckedChanged);
        if (zOnCheckedChanged) {
            if (switchController instanceof DynamicSummary) {
                switchController.notifySummaryChanged(getContext());
            }
        } else {
            bundle.putString("set_checked_error_message", switchController.getErrorMessage(z));
        }
        return bundle;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }
}
