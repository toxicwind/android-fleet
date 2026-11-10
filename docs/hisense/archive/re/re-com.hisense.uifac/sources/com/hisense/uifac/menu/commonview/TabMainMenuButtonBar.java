package com.hisense.uifac.menu.commonview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.R;

/* JADX INFO: loaded from: classes.dex */
public class TabMainMenuButtonBar extends LinearLayout {
    public static int BUTTONMAINMENU_COUNT = 17;
    public Button[] mButtonMainMenu_Item;

    public TabMainMenuButtonBar(Context context) {
        super(context);
        this.mButtonMainMenu_Item = new Button[BUTTONMAINMENU_COUNT];
    }

    public TabMainMenuButtonBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mButtonMainMenu_Item = new Button[BUTTONMAINMENU_COUNT];
    }

    public void initTabData(String[] strArr) {
        int i = 0;
        while (i < strArr.length) {
            this.mButtonMainMenu_Item[i].setText(strArr[i]);
            this.mButtonMainMenu_Item[i].setVisibility(0);
            if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                this.mButtonMainMenu_Item[i].setTextColor(-7829368);
            }
            i++;
        }
        int i2 = i - 1;
        this.mButtonMainMenu_Item[i2].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_0);
        Button[] buttonArr = this.mButtonMainMenu_Item;
        buttonArr[0].setNextFocusUpId(buttonArr[i2].getId());
        while (i < BUTTONMAINMENU_COUNT) {
            this.mButtonMainMenu_Item[i].setVisibility(8);
            i++;
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        ((Activity) getContext()).getLayoutInflater().inflate(R.layout.menu_tab_mainmenu_button, this);
        this.mButtonMainMenu_Item[0] = (Button) findViewById(R.id.widget_buttonMainFactoryMenu_0);
        this.mButtonMainMenu_Item[1] = (Button) findViewById(R.id.widget_buttonMainFactoryMenu_1);
        this.mButtonMainMenu_Item[2] = (Button) findViewById(R.id.widget_buttonMainFactoryMenu_2);
        this.mButtonMainMenu_Item[3] = (Button) findViewById(R.id.widget_buttonMainFactoryMenu_3);
        this.mButtonMainMenu_Item[4] = (Button) findViewById(R.id.widget_buttonMainFactoryMenu_4);
        this.mButtonMainMenu_Item[5] = (Button) findViewById(R.id.widget_buttonMainFactoryMenu_5);
        this.mButtonMainMenu_Item[6] = (Button) findViewById(R.id.widget_buttonMainFactoryMenu_6);
        this.mButtonMainMenu_Item[7] = (Button) findViewById(R.id.widget_buttonMainFactoryMenu_7);
        this.mButtonMainMenu_Item[8] = (Button) findViewById(R.id.widget_buttonMainFactoryMenu_8);
        this.mButtonMainMenu_Item[9] = (Button) findViewById(R.id.widget_buttonMainFactoryMenu_9);
        this.mButtonMainMenu_Item[10] = (Button) findViewById(R.id.widget_buttonMainFactoryMenu_10);
        this.mButtonMainMenu_Item[11] = (Button) findViewById(R.id.widget_buttonMainFactoryMenu_11);
        this.mButtonMainMenu_Item[12] = (Button) findViewById(R.id.widget_buttonMainFactoryMenu_12);
        this.mButtonMainMenu_Item[13] = (Button) findViewById(R.id.widget_buttonMainFactoryMenu_13);
        this.mButtonMainMenu_Item[14] = (Button) findViewById(R.id.widget_buttonMainFactoryMenu_14);
        this.mButtonMainMenu_Item[15] = (Button) findViewById(R.id.widget_buttonMainFactoryMenu_15);
        this.mButtonMainMenu_Item[16] = (Button) findViewById(R.id.widget_buttonMainFactoryMenu_16);
    }
}
