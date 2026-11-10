package com.hisense.tv.ota.ui;

import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import com.hisense.tv.ota.R;

/* JADX INFO: loaded from: classes.dex */
class u implements CompoundButton.OnCheckedChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ w f65a;

    u(w wVar) {
        this.f65a = wVar;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Log.i("NewVersionDialog", "checkbox onCheckedChanged, checked: " + z);
        this.f65a.c.setEnabled(z);
        ((Button) this.f65a.findViewById(R.id.id_version_dialog_btn_ok)).setTextColor(z ? -1 : -7829368);
    }
}
