package com.hisense.uifac.menu.commonview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.R;

/* JADX INFO: loaded from: classes.dex */
public class TabMenuButtonBar extends LinearLayout {
    public Button mButtonDesignMenu;
    public Button mButtonFactoryMenu;

    public TabMenuButtonBar(Context context) {
        super(context);
    }

    public TabMenuButtonBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        ((Activity) getContext()).getLayoutInflater().inflate(R.layout.menu_tab_menu_button, this);
        this.mButtonFactoryMenu = (Button) findViewById(R.id.widget_buttonFactoryMenu);
        this.mButtonDesignMenu = (Button) findViewById(R.id.widget_buttonDesignMenu);
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mButtonFactoryMenu.setTextColor(-7829368);
            this.mButtonDesignMenu.setTextColor(-7829368);
        }
    }
}
