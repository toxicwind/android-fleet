package com.hisense.uifac.menu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hisense.tvcm.FacFunction;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.tvcm.FacParamManager;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uiadapter.TVComponenetListener;
import com.hisense.uifac.ActuatorAdjust;
import com.hisense.uifac.AlarmAndFanSpeedActivity;
import com.hisense.uifac.ButtonTestActivity;
import com.hisense.uifac.CWActivity;
import com.hisense.uifac.FacOledScreenProtectActivity;
import com.hisense.uifac.FacRootMenu;
import com.hisense.uifac.GapAdjustActivity;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.adapter.SetConfigListViewAdapter;
import com.hisense.uifac.menu.commonview.ChangeValueBarView;
import com.hisense.uifac.menu.commonview.CustListView;
import com.hisense.uifac.menu.commonview.EffectOptionView;
import com.hisense.uifac.menu.commonview.MenuSetUpListView;
import com.hisense.uifac.menu.commonview.ProgressView;
import com.hisense.uifac.menu.commonview.RespondedKeyEvent;
import com.hisense.uifac.menu.commonview.TabMainMenuButtonBar;
import com.hisense.uifac.menu.commonview.TabMenuButtonBar;
import com.hisense.uifac.menu.commonview.UserCurrProgramInforDialog;
import com.hisense.uifac.menu.commonview.UserProgramTypeDialog;
import com.hisense.uifac.menu.util.MenuConfigManager;
import com.hisense.uifac.menu.util.MenuDataContainer;
import com.hisense.uifac.util.DemuraUtil;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MenuMain extends Activity implements View.OnKeyListener, View.OnFocusChangeListener, TVComponenetListener {
    private static String mBottomCountryInfo = "";
    private static int mCurrentMainFocusButtonID = 0;
    public static boolean mInterruptExit = false;
    private static MenuConfigManager mMenuConfigManager;
    private static RelativeLayout mMenuMainRelativeLayout;
    private static LinearLayout mRootRelativeLayout;
    private static LinearLayout mTabMainMenuButtonBarRootView;
    private RespondedKeyEvent childView;
    private LinearLayout infoLinear5G;
    private LinearLayout linearList;
    private Button mButtonCurrentMainTab;
    private ChangeValueBarView mChangeview;
    private Context mContext;
    private MenuDataContainer mDataContainer;
    private ParentData mFirstLevelData;
    private RelativeLayout mFrameLayout;
    private List<SetConfigListViewAdapter.DataItem> mGroup;
    private HideFacMainMenuReceiver mHideFacMainMenuReceiver;
    private ImageView mImageView;
    private SetConfigListViewAdapter mListViewAdpter;
    private SetConfigListViewAdapter.DataItem mListViewSelectedItemData;
    public MenuSetUpListView mMenuSetUpListView;
    private MyUpdata mMyUpData;
    private ParentData mSecondLevelData;
    SharedPreferences mShared;
    private TabMainMenuButtonBar mTabMainMenuButtonBar;
    private TabMenuButtonBar mTabMenuButtonBar;
    private LinearLayout mTabMenuButtonBarRootView;
    private ParentData mThirdLevelData;
    private TextView mVersionInfo_Bottom;
    private LinearLayout versionInfoLinear;
    private LinearLayout versionLayout1;
    private LinearLayout versionLayout2;
    private int selIdx = 0;
    private int mCurrentSelectedPosition = 0;
    private int mBeforeSelectedPosition = 0;
    private int mCurrentFouceButtonID = 0;
    private int mExitLevel = 0;
    private int mPageSize = 12;
    private ProgressDialog progressDialog = null;
    private int mWBPatternTimes = -1;
    private FacFunction mFacFunction_Adapter = null;
    private boolean mIsAlreadySet = false;
    private Handler mHandler = new Handler() { // from class: com.hisense.uifac.menu.MenuMain.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Log.i("Fac_MenuMain", "msg=" + message.what);
            int i = message.what;
            if (i == 100) {
                MenuMain.this.finish();
            } else if (i == 101) {
                if (MenuMain.this.mChangeview != null) {
                    MenuMain.this.mChangeview.setVisibility(8);
                    MenuMain.this.mListViewAdpter.notifyDataSetChanged();
                }
                if (MenuMain.mRootRelativeLayout != null) {
                    MenuMain.mRootRelativeLayout.setVisibility(0);
                }
                SetConfigListViewAdapter.DataItem dataItem = MenuMain.this.mListViewSelectedItemData.mParent;
                if (dataItem != null && dataItem.getmInitValue() != dataItem.userDefined) {
                    ((EffectOptionView) MenuMain.this.mMenuSetUpListView.getChildAt(dataItem.getPosition())).showValue(MenuMain.this.mDataContainer.getValueById(dataItem.getmItemID()));
                }
                MenuMain.this.mMenuSetUpListView.requestFocus();
                MenuMain menuMain = MenuMain.this;
                menuMain.mMenuSetUpListView.setSelection(menuMain.mCurrentSelectedPosition);
            } else if (i == 103) {
                MenuMain.this.finish();
            } else if (i == 115) {
                MenuMain.this.refreshFilterChangeTime();
            } else if (i == 135) {
                MenuMain.this.mListViewAdpter.notifyDataSetChanged();
            } else if (i == 142) {
                MenuMain.this.startScreenProtectActivity();
            } else if (i == 6000) {
                Intent intent = new Intent();
                intent.setClass(MenuMain.this, FacRootMenu.class);
                MenuMain.this.startActivity(intent);
                MenuMain.this.finish();
            } else if (i != 108) {
                if (i != 109) {
                    if (i != 111) {
                        if (i == 112 && MenuMain.this.progressDialog != null) {
                            MenuMain.this.progressDialog.dismiss();
                            Intent intent2 = new Intent();
                            intent2.setClass(MenuMain.this, FacRootMenu.class);
                            intent2.putExtra("fromMenu", true);
                            MenuMain.this.startActivity(intent2);
                            MenuMain.this.finish();
                        }
                    } else {
                        MenuMain menuMain2 = MenuMain.this;
                        menuMain2.progressDialog = menuMain2.getProgressDialog();
                        MenuMain.this.progressDialog.show();
                        SystemProperties.set("sys.factory.preset_channel", "1");
                    }
                } else if (MenuMain.this.mChangeview.getVisibility() == 0 && MenuMain.this.mCurrentSelectedPosition > 0) {
                    MenuMain menuMain3 = MenuMain.this;
                    menuMain3.selIdx = MenuMain.access$706(menuMain3);
                    MenuMain menuMain4 = MenuMain.this;
                    menuMain4.mBeforeSelectedPosition = menuMain4.mCurrentSelectedPosition;
                    MenuMain menuMain5 = MenuMain.this;
                    menuMain5.childView = (RespondedKeyEvent) menuMain5.mMenuSetUpListView.getSelectedView();
                    MenuMain menuMain6 = MenuMain.this;
                    menuMain6.mListViewSelectedItemData = (SetConfigListViewAdapter.DataItem) menuMain6.mMenuSetUpListView.getItemAtPosition(menuMain6.mCurrentSelectedPosition);
                    if (MenuMain.this.mListViewSelectedItemData.getDataType() == SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR) {
                        MenuMain.mRootRelativeLayout.setVisibility(4);
                        MenuMain.this.mChangeview.setVisibility(0);
                        MenuMain.this.mChangeview.requestFocus();
                        MenuMain.this.mChangeview.setHandler(MenuMain.this.mHandler);
                        MenuMain.this.mChangeview.setAdapter(MenuMain.this.mListViewSelectedItemData);
                        MenuMain.this.mChangeview.setVisibility(0);
                    } else {
                        sendEmptyMessage(101);
                    }
                }
            } else if (MenuMain.this.mChangeview.getVisibility() == 0) {
                MenuMain menuMain7 = MenuMain.this;
                menuMain7.mBeforeSelectedPosition = menuMain7.mCurrentSelectedPosition;
                MenuMain menuMain8 = MenuMain.this;
                menuMain8.childView = (RespondedKeyEvent) menuMain8.mMenuSetUpListView.getSelectedView();
                MenuMain menuMain9 = MenuMain.this;
                menuMain9.mListViewSelectedItemData = (SetConfigListViewAdapter.DataItem) menuMain9.mMenuSetUpListView.getItemAtPosition(menuMain9.mCurrentSelectedPosition);
                if (MenuMain.this.mListViewSelectedItemData.getDataType() == SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR) {
                    MenuMain.mRootRelativeLayout.setVisibility(4);
                    MenuMain.this.mChangeview.setVisibility(0);
                    MenuMain.this.mChangeview.requestFocus();
                    MenuMain.this.mChangeview.setHandler(MenuMain.this.mHandler);
                    MenuMain menuMain10 = MenuMain.this;
                    menuMain10.selIdx = MenuMain.access$704(menuMain10);
                    if (MenuMain.this.selIdx == MenuMain.this.mPageSize) {
                        MenuMain.this.selIdx = 0;
                    }
                    MenuMain.this.mChangeview.setAdapter(MenuMain.this.mListViewSelectedItemData);
                    MenuMain.this.mChangeview.setVisibility(0);
                } else {
                    sendEmptyMessage(101);
                }
            }
            super.handleMessage(message);
        }
    };

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean z) {
    }

    public MenuMain() {
        this.mFirstLevelData = new ParentData();
        this.mSecondLevelData = new ParentData();
        this.mThirdLevelData = new ParentData();
    }

    static /* synthetic */ int access$3208(MenuMain menuMain) {
        int i = menuMain.mExitLevel;
        menuMain.mExitLevel = i + 1;
        return i;
    }

    static /* synthetic */ int access$704(MenuMain menuMain) {
        int i = menuMain.mCurrentSelectedPosition + 1;
        menuMain.mCurrentSelectedPosition = i;
        return i;
    }

    static /* synthetic */ int access$706(MenuMain menuMain) {
        int i = menuMain.mCurrentSelectedPosition - 1;
        menuMain.mCurrentSelectedPosition = i;
        return i;
    }

    private class ParentData {
        private int mPageIndex;
        private List<SetConfigListViewAdapter.DataItem> mParentGroup;
        private int mSelectedPosition;

        private ParentData(MenuMain menuMain) {
        }
    }

    class MyUpdata implements CustListView.UpDateListView {
        MyUpdata() {
        }

        @Override // com.hisense.uifac.menu.commonview.CustListView.UpDateListView
        public void updata() {
            MenuMain menuMain = MenuMain.this;
            menuMain.mGroup = menuMain.mMenuSetUpListView.getCurrentList();
            MenuMain.this.mListViewAdpter.setmGroup(MenuMain.this.mGroup);
            MenuMain menuMain2 = MenuMain.this;
            menuMain2.mMenuSetUpListView.setAdapter((ListAdapter) menuMain2.mListViewAdpter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ProgressDialog getProgressDialog() {
        if (this.progressDialog == null) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            this.progressDialog = progressDialog;
            progressDialog.setMessage(getString(R.string.str_factorymenu_unprotectedclear_waiting));
            this.progressDialog.setIndeterminate(true);
            this.progressDialog.setCancelable(true);
        }
        return this.progressDialog;
    }

    private class HideFacMainMenuReceiver extends BroadcastReceiver {
        private HideFacMainMenuReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == null || !intent.getAction().equals("com.hisense.broadcom.HIDE_FAC_MAINMENU")) {
                return;
            }
            Log.i("Fac_MenuMain", "exit fac main menu");
            MenuMain.this.finish();
        }
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        switch (view.getId()) {
            case R.id.widget_buttonDesignMenu /* 2131296976 */:
                if (z) {
                    this.mDataContainer.loadHisDesignData();
                    if (FacPropertyDemo.HIFAC_MARKET_JP) {
                        int iConvertDipToPix = mMenuConfigManager.convertDipToPix(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_OFF);
                        int iConvertDipToPix2 = mMenuConfigManager.convertDipToPix(60);
                        Log.d("Fac_MenuMain", "==dolbyButton width = " + iConvertDipToPix);
                        Log.d("Fac_MenuMain", "==dolbyButton high = " + iConvertDipToPix2);
                        this.mTabMainMenuButtonBar.setLayoutParams(new LinearLayout.LayoutParams(iConvertDipToPix, iConvertDipToPix2));
                    }
                    this.mTabMenuButtonBarRootView.getChildAt(this.mCurrentFouceButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMenuButtonBar.mButtonDesignMenu.setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    setAdapter(this.mDataContainer.getHisDesignDataGroup());
                    this.mCurrentFouceButtonID = 1;
                    mCurrentMainFocusButtonID = 0;
                }
                break;
            case R.id.widget_buttonFactoryMenu /* 2131296977 */:
                if (z) {
                    mMenuConfigManager.hideHisToast();
                    this.mDataContainer.loadHisFactoryData();
                    Log.d("Fac_MenuMain", "==buttonFactoryMenu4");
                    if (FacPropertyDemo.HIFAC_MARKET_JP) {
                        int iConvertDipToPix3 = mMenuConfigManager.convertDipToPix(230);
                        int iConvertDipToPix4 = mMenuConfigManager.convertDipToPix(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_OFF);
                        Log.d("Fac_MenuMain", "==haibo width = " + iConvertDipToPix3);
                        Log.d("Fac_MenuMain", "==haibo high = " + iConvertDipToPix4);
                        this.mTabMainMenuButtonBar.setLayoutParams(new LinearLayout.LayoutParams(iConvertDipToPix4, iConvertDipToPix3));
                    }
                    this.mTabMenuButtonBarRootView.getChildAt(this.mCurrentFouceButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMenuButtonBar.mButtonFactoryMenu.setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    setAdapter(this.mDataContainer.getHisFactoryDataGroup());
                    this.mCurrentFouceButtonID = 0;
                    mCurrentMainFocusButtonID = 0;
                }
                break;
            case R.id.widget_buttonMainFactoryMenu_0 /* 2131296978 */:
                if (z) {
                    mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMainMenuButtonBar.mButtonMainMenu_Item[0].setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    if (this.mCurrentFouceButtonID == 0) {
                        mMenuConfigManager.hideHisToast();
                        enableLinearListVisable();
                        this.mDataContainer.loadHisFacData_WhiteBalance();
                        setAdapter(this.mDataContainer.getFacDataGroup_WhiteBalance());
                        if (FacPropertyDemo.HIFAC_IS_LASER4K) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[0].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_5);
                        } else if (FacPropertyDemo.HIFAC_MIC_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[0].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_4);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[0].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_3);
                        }
                        if (!mMenuConfigManager.isHaveTuner()) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[0].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_2);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[0].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_1);
                        }
                    } else if (FacPropertyDemo.HIFAC_MARKET_JP) {
                        this.mDataContainer.loadHisDesData_Dolby_IQ();
                        setAdapter(this.mDataContainer.getDesDataGroup_Dolby_IQ());
                    } else {
                        this.mDataContainer.loadHisDesData_ColorTuner();
                        setAdapter(this.mDataContainer.getDesDataGroup_ColorTuner());
                        if (this.mFacFunction_Adapter.IsDolbyIQSupport()) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[0].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_16);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[0].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_15);
                        }
                        this.mTabMainMenuButtonBar.mButtonMainMenu_Item[0].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_1);
                    }
                    mCurrentMainFocusButtonID = 0;
                    this.mExitLevel = 0;
                    this.selIdx = -1;
                }
                break;
            case R.id.widget_buttonMainFactoryMenu_1 /* 2131296979 */:
                if (z) {
                    mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMainMenuButtonBar.mButtonMainMenu_Item[1].setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    if (this.mCurrentFouceButtonID == 0) {
                        mMenuConfigManager.hideHisToast();
                        enableLinearListVisable();
                        if (FacPropertyDemo.HIFAC_MARKET_HK) {
                            this.mDataContainer.loadHisFacData_ChannelSetForHK();
                        } else {
                            this.mDataContainer.loadHisFacData_ChannelSet();
                        }
                        setAdapter(this.mDataContainer.getFacDataGroup_ChannelSet());
                    } else {
                        this.mDataContainer.loadHisDesData_PictureMode();
                        setAdapter(this.mDataContainer.getDesDataGroup_PictureMode());
                        if (!FacPropertyDemo.HIFAC_COLORTUNER_SUPPORT) {
                            if (FacPropertyDemo.HIFAC_BYPASS_SUPPORT) {
                                this.mTabMainMenuButtonBar.mButtonMainMenu_Item[1].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_12);
                            } else if (FacPropertyDemo.HIFAC_LIGHT_SENSOR_SUPPORT) {
                                this.mTabMainMenuButtonBar.mButtonMainMenu_Item[1].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_11);
                            } else if (FacPropertyDemo.HIFAC_WAVES_SUPPORT) {
                                this.mTabMainMenuButtonBar.mButtonMainMenu_Item[1].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_10);
                            } else {
                                this.mTabMainMenuButtonBar.mButtonMainMenu_Item[1].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_9);
                            }
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[1].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_0);
                        }
                        this.mTabMainMenuButtonBar.mButtonMainMenu_Item[1].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_2);
                    }
                    if (mInterruptExit) {
                        this.mDataContainer.loadHisFactoryData();
                        setAdapter(this.mDataContainer.getHisFactoryDataGroup());
                        this.mMenuSetUpListView.requestFocus();
                        this.mMenuSetUpListView.setSelection(this.mCurrentSelectedPosition);
                        this.mTabMenuButtonBar.mButtonFactoryMenu.setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                        mInterruptExit = false;
                        this.mExitLevel = 1;
                        this.selIdx = 0;
                    } else {
                        this.mExitLevel = 0;
                        this.selIdx = -1;
                    }
                    mCurrentMainFocusButtonID = 1;
                }
                break;
            case R.id.widget_buttonMainFactoryMenu_10 /* 2131296980 */:
                if (z) {
                    mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMainMenuButtonBar.mButtonMainMenu_Item[10].setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    if (this.mCurrentFouceButtonID == 1) {
                        if (FacPropertyDemo.HIFAC_LIGHT_SENSOR_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[10].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_11);
                        } else if (FacPropertyDemo.HIFAC_BYPASS_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[10].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_12);
                        } else if (FacPropertyDemo.HIFAC_COLORTUNER_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[10].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_0);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[10].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_1);
                        }
                        this.mDataContainer.loadHisDesData_Waves();
                        setAdapter(this.mDataContainer.getDesDataGroup_WAVES());
                        this.mExitLevel = 0;
                        this.selIdx = -1;
                        mCurrentMainFocusButtonID = 10;
                    }
                }
                break;
            case R.id.widget_buttonMainFactoryMenu_11 /* 2131296981 */:
                if (z) {
                    mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMainMenuButtonBar.mButtonMainMenu_Item[11].setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    if (this.mCurrentFouceButtonID == 1) {
                        this.mTabMainMenuButtonBar.mButtonMainMenu_Item[11].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_12);
                        if (FacPropertyDemo.HIFAC_WAVES_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[11].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_10);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[11].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_9);
                        }
                        if (FacPropertyDemo.HIFAC_BYPASS_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[11].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_12);
                        } else if (FacPropertyDemo.HIFAC_COLORTUNER_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[11].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_0);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[11].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_1);
                        }
                        this.mDataContainer.loadHisDesData_LightSensor();
                        setAdapter(this.mDataContainer.getDesDataGroup_LIGHT_SENSOR());
                        this.mExitLevel = 0;
                        this.selIdx = -1;
                        mCurrentMainFocusButtonID = 11;
                    }
                }
                break;
            case R.id.widget_buttonMainFactoryMenu_12 /* 2131296982 */:
                if (z) {
                    mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMainMenuButtonBar.mButtonMainMenu_Item[12].setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    if (this.mCurrentFouceButtonID == 1) {
                        this.mTabMainMenuButtonBar.mButtonMainMenu_Item[12].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_13);
                        if (FacPropertyDemo.HIFAC_LIGHT_SENSOR_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[12].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_11);
                        } else if (FacPropertyDemo.HIFAC_WAVES_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[12].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_10);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[12].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_9);
                        }
                        if (!FacPropertyDemo.HIFAC_COLORTUNER_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[12].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_1);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[12].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_13);
                        }
                        this.mDataContainer.loadHisDesData_Bypass();
                        setAdapter(this.mDataContainer.getDesDataGroup_Bypass());
                        this.mExitLevel = 0;
                        this.selIdx = -1;
                        mCurrentMainFocusButtonID = 12;
                    }
                }
                break;
            case R.id.widget_buttonMainFactoryMenu_13 /* 2131296983 */:
                if (z) {
                    mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMainMenuButtonBar.mButtonMainMenu_Item[13].setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    if (this.mCurrentFouceButtonID == 1) {
                        this.mTabMainMenuButtonBar.mButtonMainMenu_Item[13].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_0);
                        if (FacPropertyDemo.HIFAC_LIGHT_SENSOR_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[13].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_11);
                        } else if (FacPropertyDemo.HIFAC_WAVES_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[13].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_10);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[13].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_12);
                        }
                        this.mTabMainMenuButtonBar.mButtonMainMenu_Item[13].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_14);
                        this.mDataContainer.loadHisDesData_PEQ();
                        setAdapter(this.mDataContainer.getDesDataGroup_Peq());
                        this.mExitLevel = 0;
                        this.selIdx = -1;
                        mCurrentMainFocusButtonID = 13;
                    }
                }
                break;
            case R.id.widget_buttonMainFactoryMenu_14 /* 2131296984 */:
                if (z) {
                    mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMainMenuButtonBar.mButtonMainMenu_Item[14].setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    this.mTabMainMenuButtonBar.mButtonMainMenu_Item[14].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_13);
                    if (FacPropertyDemo.HIFAC_5G_CHANNEL_SUPPORT) {
                        this.mTabMainMenuButtonBar.mButtonMainMenu_Item[14].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_15);
                    } else if (this.mFacFunction_Adapter.IsDolbyIQSupport()) {
                        this.mTabMainMenuButtonBar.mButtonMainMenu_Item[14].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_16);
                    } else if (!FacPropertyDemo.HIFAC_COLORTUNER_SUPPORT) {
                        this.mTabMainMenuButtonBar.mButtonMainMenu_Item[14].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_1);
                    } else {
                        this.mTabMainMenuButtonBar.mButtonMainMenu_Item[14].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_0);
                    }
                    this.mDataContainer.loadHisDesData_OLED();
                    setAdapter(this.mDataContainer.getDesDataGroup_OLED());
                    this.mExitLevel = 0;
                    this.selIdx = -1;
                    mCurrentMainFocusButtonID = 14;
                }
                break;
            case R.id.widget_buttonMainFactoryMenu_15 /* 2131296985 */:
                if (z) {
                    mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMainMenuButtonBar.mButtonMainMenu_Item[15].setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    if (this.mCurrentFouceButtonID == 1) {
                        enableLinearListVisable();
                        this.infoLinear5G.setVisibility(0);
                        hideListView();
                        this.mTabMainMenuButtonBar.mButtonMainMenu_Item[15].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_14);
                        if (this.mFacFunction_Adapter.IsDolbyIQSupport()) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[15].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_16);
                        } else if (FacPropertyDemo.HIFAC_COLORTUNER_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[15].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_0);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[15].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_1);
                        }
                        this.mDataContainer.loadHisDesData_5G_Info();
                        setAdapter(this.mDataContainer.getDesDataGroup_5G_Info());
                        this.mExitLevel = 0;
                        this.selIdx = -1;
                        mCurrentMainFocusButtonID = 15;
                    }
                }
                break;
            case R.id.widget_buttonMainFactoryMenu_16 /* 2131296986 */:
                if (z) {
                    mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMainMenuButtonBar.mButtonMainMenu_Item[16].setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    if (this.mCurrentFouceButtonID == 1) {
                        if (FacPropertyDemo.HIFAC_5G_CHANNEL_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[16].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_15);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[16].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_14);
                        }
                        if (FacPropertyDemo.HIFAC_COLORTUNER_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[16].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_0);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[16].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_1);
                        }
                        this.mDataContainer.loadHisDesData_Dolby_IQ();
                        setAdapter(this.mDataContainer.getDesDataGroup_Dolby_IQ());
                        this.mExitLevel = 0;
                        this.selIdx = -1;
                        mCurrentMainFocusButtonID = 16;
                    }
                }
                break;
            case R.id.widget_buttonMainFactoryMenu_2 /* 2131296987 */:
                if (z) {
                    mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMainMenuButtonBar.mButtonMainMenu_Item[2].setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    if (this.mCurrentFouceButtonID == 0) {
                        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.MenuMain.2
                            @Override // java.lang.Runnable
                            public void run() {
                                MenuMain.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.menu.MenuMain.2.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MenuMain.this.mDataContainer.loadHisFacData_Function(false);
                                        MenuMain menuMain = MenuMain.this;
                                        menuMain.setAdapter(menuMain.mDataContainer.getFacDataGroup_Function());
                                    }
                                });
                            }
                        }).start();
                        if (!mMenuConfigManager.isHaveTuner()) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[2].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_0);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[2].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_1);
                        }
                    } else {
                        Log.i("Fac_MenuMain", "FacPropertyDemo.HIFAC_AUDIO_MORE_EQUALIZER:" + FacPropertyDemo.HIFAC_AUDIO_MORE_EQUALIZER);
                        if (FacPropertyDemo.HIFAC_AUDIO_MORE_EQUALIZER) {
                            this.mDataContainer.loadHisDesData_AudioMode_More_Equalizers();
                            setAdapter(this.mDataContainer.getDesDataGroup_SoundMode_More_Equalizers());
                        } else {
                            this.mDataContainer.loadHisDesData_AudioMode();
                            setAdapter(this.mDataContainer.getDesDataGroup_SoundMode());
                        }
                    }
                    mCurrentMainFocusButtonID = 2;
                    this.mExitLevel = 0;
                    this.selIdx = -1;
                }
                break;
            case R.id.widget_buttonMainFactoryMenu_3 /* 2131296988 */:
                if (z) {
                    mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMainMenuButtonBar.mButtonMainMenu_Item[3].setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    if (this.mCurrentFouceButtonID == 0) {
                        enableLinearListVisable();
                        this.versionInfoLinear.setVisibility(0);
                        hideListView();
                        if (FacPropertyDemo.HIFAC_MIC_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[3].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_4);
                        } else if (FacPropertyDemo.HIFAC_IS_LASER4K) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[3].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_5);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[3].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_0);
                        }
                    } else {
                        this.mDataContainer.loadHisDesData_PictureCurve();
                        setAdapter(this.mDataContainer.getDesDataGroup_PictureCurve());
                        this.mTabMainMenuButtonBar.mButtonMainMenu_Item[3].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_4);
                    }
                    this.mExitLevel = 0;
                    this.selIdx = -1;
                    mCurrentMainFocusButtonID = 3;
                }
                break;
            case R.id.widget_buttonMainFactoryMenu_4 /* 2131296989 */:
                if (z) {
                    mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMainMenuButtonBar.mButtonMainMenu_Item[4].setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    if (this.mCurrentFouceButtonID == 0) {
                        Log.i("Fac_MenuMain", "onFouceChanged mic start");
                        this.mDataContainer.loadHisFacData_DetectMic();
                        setAdapter(this.mDataContainer.getFacDataGroup_TestMic());
                        Log.i("Fac_MenuMain", "onFouceChanged mic end");
                        if (FacPropertyDemo.HIFAC_IS_LASER4K) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[4].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_5);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[4].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_0);
                        }
                        this.mTabMainMenuButtonBar.mButtonMainMenu_Item[4].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_3);
                    } else {
                        if (FacPropertyDemo.HIFAC_AUDIO_MORE_CURVE) {
                            this.mDataContainer.loadHisDesData_AudioCurveNew();
                            setAdapter(this.mDataContainer.getDesDataGroup_SoundCurveNew());
                        } else {
                            this.mDataContainer.loadHisDesData_AudioCurve();
                            setAdapter(this.mDataContainer.getDesDataGroup_SoundCurve());
                        }
                        this.mTabMainMenuButtonBar.mButtonMainMenu_Item[4].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_3);
                        this.mTabMainMenuButtonBar.mButtonMainMenu_Item[4].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_5);
                    }
                    this.mExitLevel = 0;
                    this.selIdx = -1;
                    mCurrentMainFocusButtonID = 4;
                }
                break;
            case R.id.widget_buttonMainFactoryMenu_5 /* 2131296990 */:
                if (z) {
                    mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMainMenuButtonBar.mButtonMainMenu_Item[5].setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    if (this.mCurrentFouceButtonID == 0 && FacPropertyDemo.HIFAC_IS_LASER4K) {
                        this.mDataContainer.loadHisFacData_Laser();
                        setAdapter(this.mDataContainer.getFacDataGroup_Laser());
                        if (FacPropertyDemo.HIFAC_MIC_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[5].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_4);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[5].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_3);
                        }
                        this.mTabMainMenuButtonBar.mButtonMainMenu_Item[5].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_0);
                        this.mExitLevel = 0;
                        this.selIdx = -1;
                        mCurrentMainFocusButtonID = 5;
                    } else if (this.mCurrentFouceButtonID == 1) {
                        this.mDataContainer.loadHisDesData_SavingMode();
                        setAdapter(this.mDataContainer.getDesDataGroup_SavingMode());
                        this.mTabMainMenuButtonBar.mButtonMainMenu_Item[5].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_4);
                        if (FacPropertyDemo.SourceEnum.DMP != mMenuConfigManager.getCurrentInputSourceEnum()) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[5].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_6);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[5].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_7);
                        }
                        this.mExitLevel = 0;
                        this.selIdx = -1;
                        mCurrentMainFocusButtonID = 5;
                    }
                }
                break;
            case R.id.widget_buttonMainFactoryMenu_6 /* 2131296991 */:
                if (z) {
                    mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMainMenuButtonBar.mButtonMainMenu_Item[6].setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    if (this.mCurrentFouceButtonID == 1) {
                        this.mDataContainer.loadHisDesData_OverScan();
                        setAdapter(this.mDataContainer.getDesDataGroup_OverScan());
                        if (mMenuConfigManager.isSSCSupported()) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[6].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_7);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[6].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_8);
                        }
                        this.mExitLevel = 0;
                        this.selIdx = -1;
                        mCurrentMainFocusButtonID = 6;
                    }
                }
                break;
            case R.id.widget_buttonMainFactoryMenu_7 /* 2131296992 */:
                if (z) {
                    mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMainMenuButtonBar.mButtonMainMenu_Item[7].setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    if (this.mCurrentFouceButtonID == 1) {
                        if (FacPropertyDemo.HIFAC_20_POINT_WB_BALANCE_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[7].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_8);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[7].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_9);
                        }
                        if (FacPropertyDemo.SourceEnum.DMP != mMenuConfigManager.getCurrentInputSourceEnum()) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[7].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_6);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[7].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_5);
                        }
                        this.mDataContainer.loadHisDesData_SSC();
                        setAdapter(this.mDataContainer.getDesDataGroup_SSC());
                        this.mExitLevel = 0;
                        this.selIdx = -1;
                        mCurrentMainFocusButtonID = 7;
                    }
                }
                break;
            case R.id.widget_buttonMainFactoryMenu_8 /* 2131296993 */:
                if (z) {
                    mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMainMenuButtonBar.mButtonMainMenu_Item[8].setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    if (this.mCurrentFouceButtonID == 1) {
                        this.mTabMainMenuButtonBar.mButtonMainMenu_Item[8].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_9);
                        this.mDataContainer.loadHisDesData_WB20_Point();
                        setAdapter(this.mDataContainer.getDesDataGroup_WB20Point());
                        if (mMenuConfigManager.isSSCSupported()) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[8].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_7);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[8].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_6);
                        }
                    }
                    this.mExitLevel = 0;
                    this.selIdx = -1;
                    mCurrentMainFocusButtonID = 8;
                }
                break;
            case R.id.widget_buttonMainFactoryMenu_9 /* 2131296994 */:
                if (z) {
                    mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
                    this.mTabMainMenuButtonBar.mButtonMainMenu_Item[9].setBackgroundResource(R.drawable.menu_tab_button_select_bg);
                    if (this.mCurrentFouceButtonID == 1) {
                        if (FacPropertyDemo.HIFAC_WAVES_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[9].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_10);
                        } else if (FacPropertyDemo.HIFAC_LIGHT_SENSOR_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[9].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_11);
                        } else if (FacPropertyDemo.HIFAC_BYPASS_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[9].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_12);
                        } else if (FacPropertyDemo.HIFAC_COLORTUNER_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[9].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_0);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[9].setNextFocusDownId(R.id.widget_buttonMainFactoryMenu_1);
                        }
                        if (FacPropertyDemo.HIFAC_20_POINT_WB_BALANCE_SUPPORT) {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[9].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_8);
                        } else {
                            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[9].setNextFocusUpId(R.id.widget_buttonMainFactoryMenu_7);
                        }
                        this.mDataContainer.loadHisDesData_DRC_AVC();
                        setAdapter(this.mDataContainer.getDesDataGroup_DRC_AVC());
                        this.mExitLevel = 0;
                        this.selIdx = -1;
                        mCurrentMainFocusButtonID = 9;
                    }
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableLinearListVisable() {
        this.linearList.setVisibility(0);
    }

    private void hideListView() {
        this.linearList.setVisibility(8);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:38:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:46:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:48:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:50:0x00e2  */
    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) throws Throwable {
        boolean zHandleHorizontalFocusWithinListItem;
        MenuSetUpListView menuSetUpListView = this.mMenuSetUpListView;
        if (menuSetUpListView == null || !menuSetUpListView.hasFocus() || this.mMenuSetUpListView.getAdapter() == null) {
            return false;
        }
        if (view.getId() == R.id.widget_buttonMainFactoryMenu_1 && this.mCurrentFouceButtonID == 0) {
            return false;
        }
        if (keyEvent.getAction() != 1) {
            this.mCurrentSelectedPosition = this.mMenuSetUpListView.getSelectedItemPosition();
            int count = this.mMenuSetUpListView.getCount();
            if (this.mCurrentSelectedPosition < 0) {
                this.mCurrentSelectedPosition = this.mBeforeSelectedPosition;
            }
            if (this.mCurrentSelectedPosition > count - 1) {
                this.mCurrentSelectedPosition = this.mBeforeSelectedPosition;
            }
            this.mBeforeSelectedPosition = this.mCurrentSelectedPosition;
            this.childView = (RespondedKeyEvent) this.mMenuSetUpListView.getSelectedView();
            this.mListViewSelectedItemData = (SetConfigListViewAdapter.DataItem) this.mMenuSetUpListView.getItemAtPosition(this.mCurrentSelectedPosition);
            if (this.childView == null) {
                this.childView = (RespondedKeyEvent) this.mMenuSetUpListView.getChildAt(this.mCurrentSelectedPosition);
            }
            if (i != 66) {
                switch (i) {
                    case 19:
                    case 20:
                        if (i == 20) {
                            int i2 = this.mCurrentSelectedPosition + 1;
                            this.selIdx = i2;
                            if (i2 == this.mPageSize) {
                                this.selIdx = 0;
                            }
                        } else {
                            this.selIdx = this.mCurrentSelectedPosition - 1;
                        }
                        zHandleHorizontalFocusWithinListItem = false;
                        break;
                    case 21:
                        zHandleHorizontalFocusWithinListItem = handleHorizontalFocusWithinListItem(21);
                        break;
                    case 22:
                        zHandleHorizontalFocusWithinListItem = handleHorizontalFocusWithinListItem(22);
                        break;
                    case 23:
                        boolean zHandleWithListItemKeyEnterEvent = handleWithListItemKeyEnterEvent();
                        if (!this.mListViewSelectedItemData.getmItemID().equals("FACTORY_OPTION_VCOM_VOLTAGE") || this.mListViewSelectedItemData.getmItemID().equals("FACTORY_OPTION_VCOM2_VOLTAGE")) {
                            if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                                setVcomType();
                                FacRootMenu.mIsVcomOK = true;
                                FacRootMenu.mIsAlreadySet = true;
                                finish();
                            }
                        } else if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_SLIDING_COVER_GAP_ADJUST")) {
                            zHandleHorizontalFocusWithinListItem = true;
                        }
                        zHandleHorizontalFocusWithinListItem = zHandleWithListItemKeyEnterEvent;
                        break;
                    default:
                        zHandleHorizontalFocusWithinListItem = false;
                        break;
                }
            } else {
                boolean zHandleWithListItemKeyEnterEvent2 = handleWithListItemKeyEnterEvent();
                if (!this.mListViewSelectedItemData.getmItemID().equals("FACTORY_OPTION_VCOM_VOLTAGE")) {
                    if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                        setVcomType();
                        FacRootMenu.mIsVcomOK = true;
                        FacRootMenu.mIsAlreadySet = true;
                        finish();
                    }
                    zHandleHorizontalFocusWithinListItem = zHandleWithListItemKeyEnterEvent2;
                } else {
                    if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                        setVcomType();
                        FacRootMenu.mIsVcomOK = true;
                        FacRootMenu.mIsAlreadySet = true;
                        finish();
                    }
                    zHandleHorizontalFocusWithinListItem = zHandleWithListItemKeyEnterEvent2;
                }
            }
        } else {
            zHandleHorizontalFocusWithinListItem = false;
        }
        return zHandleHorizontalFocusWithinListItem;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        Log.i("Fac_MenuMain", "menumain onkeyup");
        if (i == 4392 || i == 4393 || i == 4394 || i == 4395 || i == 4396 || i == 330) {
            Log.i("Fac_MenuMain", "TV key test");
            Intent intent = new Intent(this, (Class<?>) ButtonTestActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("KeyInitValue", i);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        return super.onKeyUp(i, keyEvent);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:158:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:160:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:169:0x021c  */
    /* JADX WARN: Code duplicated, block: B:171:0x024f  */
    /* JADX WARN: Code duplicated, block: B:174:0x0256  */
    /* JADX WARN: Code duplicated, block: B:176:0x025e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:177:0x025f  */
    /* JADX WARN: Code duplicated, block: B:186:0x029e  */
    /* JADX WARN: Code duplicated, block: B:188:0x02a2  */
    /* JADX WARN: Code duplicated, block: B:190:0x02aa  */
    /* JADX WARN: Code duplicated, block: B:199:0x02bd  */
    /* JADX WARN: Code duplicated, block: B:208:0x02d0  */
    /* JADX WARN: Code duplicated, block: B:210:0x02d8  */
    /* JADX WARN: Code duplicated, block: B:219:0x02eb  */
    /* JADX WARN: Code duplicated, block: B:228:0x02fe  */
    /* JADX WARN: Code duplicated, block: B:230:0x0306  */
    /* JADX WARN: Code duplicated, block: B:239:0x0319  */
    /* JADX WARN: Code duplicated, block: B:250:0x0330  */
    /* JADX WARN: Code duplicated, block: B:259:0x0343  */
    /* JADX WARN: Code duplicated, block: B:270:0x035a  */
    /* JADX WARN: Code duplicated, block: B:272:0x035e  */
    /* JADX WARN: Code duplicated, block: B:274:0x0366  */
    /* JADX WARN: Code duplicated, block: B:282:0x037a  */
    /* JADX WARN: Code duplicated, block: B:284:0x0380 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:285:0x0381  */
    /* JADX WARN: Code duplicated, block: B:292:0x0394  */
    /* JADX WARN: Code duplicated, block: B:300:0x03a8  */
    /* JADX WARN: Code duplicated, block: B:302:0x03ae A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:303:0x03af  */
    /* JADX WARN: Code duplicated, block: B:310:0x03c2  */
    /* JADX WARN: Code duplicated, block: B:312:0x03ca  */
    /* JADX WARN: Code duplicated, block: B:320:0x03de  */
    /* JADX WARN: Code duplicated, block: B:322:0x03e4 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:323:0x03e5  */
    /* JADX WARN: Code duplicated, block: B:330:0x03f8  */
    /* JADX WARN: Code duplicated, block: B:338:0x040c  */
    /* JADX WARN: Code duplicated, block: B:340:0x0412 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:341:0x0413  */
    /* JADX WARN: Code duplicated, block: B:348:0x0426  */
    /* JADX WARN: Code duplicated, block: B:350:0x042e  */
    /* JADX WARN: Code duplicated, block: B:358:0x0442  */
    /* JADX WARN: Code duplicated, block: B:360:0x0448 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:361:0x0449  */
    /* JADX WARN: Code duplicated, block: B:368:0x045c  */
    /* JADX WARN: Code duplicated, block: B:376:0x0470  */
    /* JADX WARN: Code duplicated, block: B:378:0x0476 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:379:0x0477  */
    /* JADX WARN: Code duplicated, block: B:388:0x0492  */
    /* JADX WARN: Code duplicated, block: B:390:0x049d  */
    /* JADX WARN: Instruction removed from duplicated block: B:160:0x01e9, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:169:0x021c, please report this as an issue */
    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        SetConfigListViewAdapter setConfigListViewAdapter;
        int i2;
        int i3;
        int vcomVoltage2;
        int i4;
        Log.i("Fac_MenuMain", "keyCode " + i + "is pressed");
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mHandler.removeMessages(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC);
            this.mHandler.sendEmptyMessageDelayed(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC, FacParamManager.FAC_SCREEN_PROTECT_TIME);
        }
        if ((mMenuConfigManager.isTestPattern() || mMenuConfigManager.isTestPattern_hardware()) && i != 22 && i != 21 && i != 23 && i != 66 && i != 4) {
            return true;
        }
        if (mMenuConfigManager.isInWBPatternTest() && i != 21 && i != 22) {
            return true;
        }
        if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0 && this.mIsAlreadySet && i != 4 && i != 21 && i != 22 && i != 7) {
            Log.i("Fac_MenuMain", "In vcom mode, not respond keydown event except BACK,LEFT,RIGHT,CENTER,KEYCODE_0");
            return true;
        }
        if (i == 4) {
            if (mMenuConfigManager.isTestPattern()) {
                if (MenuConfigManager.isAudioMute()) {
                    sendMuteBroadcast(false);
                }
                SystemProperties.set("sys.vt.factory.pattern_test", "false");
                mMenuConfigManager.setTestPatternModeOff();
                mMenuConfigManager.setTestPattern(0, 0, 0, 0);
                if (FacPropertyDemo.HIFAC_LOCALDIMMING_SUPPORT) {
                    mMenuConfigManager.setBackLightOn(false);
                    mMenuConfigManager.setLocalDimmingSwitch(true);
                }
                mMenuMainRelativeLayout.setBackgroundResource(R.drawable.translucent_background);
                mRootRelativeLayout.setVisibility(0);
                this.mButtonCurrentMainTab.requestFocus();
                return true;
            }
            if (mMenuConfigManager.isTestPattern_hardware()) {
                mMenuConfigManager.setTestPatternHardwareModeOff();
                mMenuMainRelativeLayout.setBackgroundResource(R.drawable.translucent_background);
                mRootRelativeLayout.setVisibility(0);
                this.mButtonCurrentMainTab.requestFocus();
                return true;
            }
            if (mMenuConfigManager.isDemuraPattern()) {
                if (MenuConfigManager.isAudioMute()) {
                    sendMuteBroadcast(false);
                }
                DemuraUtil.setDemuraPropStatus("false");
                mMenuConfigManager.setDemuraPatternModeOff();
                if (FacPropertyDemo.HIFAC_LOCALDIMMING_SUPPORT) {
                    mMenuConfigManager.setBackLightOn(false);
                    mMenuConfigManager.setLocalDimmingSwitch(true);
                }
                mMenuMainRelativeLayout.setBackgroundResource(R.drawable.translucent_background);
                mRootRelativeLayout.setVisibility(0);
                this.mButtonCurrentMainTab.requestFocus();
                return true;
            }
            finish();
            return true;
        }
        if (i != 66) {
            if (i == 82) {
                handleMenuKeyInFactoryMenu();
                return true;
            }
            if (i != 85 && i != 140) {
                if (i != 164) {
                    if (i == 4248) {
                        Log.i("Fac_MenuMain", "====LVDS SSC disable once!");
                        this.mFacFunction_Adapter.setLvdsSscDisableOnce(3);
                        return true;
                    }
                    if (i == 4357) {
                        Log.i("Fac_MenuMain", "====KEYCODE_EXIT");
                        finish();
                    } else if (i != 4390) {
                        if (i == 166) {
                            finish();
                            return true;
                        }
                        if (i != 167) {
                            switch (i) {
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                case 13:
                                case 14:
                                case 15:
                                case 16:
                                    return handleKeyboardInput(i);
                                default:
                                    switch (i) {
                                        case 21:
                                            if (this.mIsAlreadySet) {
                                                if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                                    int vcomType = mMenuConfigManager.getVcomType();
                                                    int vcomVoltage3 = mMenuConfigManager.getVcomVoltage2(vcomType) - FacPropertyDemo.HIFAC_STEP_VOM_VOLTAGE;
                                                    Log.i("Fac_MenuMain", "menumain double cell vcomCurrentValuen is " + vcomVoltage3);
                                                    int i5 = FacPropertyDemo.HIFAC_MIN_VCOM_SPAN;
                                                    if (vcomVoltage3 <= i5) {
                                                        vcomVoltage3 = i5;
                                                    }
                                                    mMenuConfigManager.setVcomVoltage2(vcomVoltage3, vcomType);
                                                } else {
                                                    int vcomVoltage = mMenuConfigManager.getVcomVoltage() - FacPropertyDemo.HIFAC_STEP_VOM_VOLTAGE;
                                                    Log.i("Fac_MenuMain", "menumain vcomCurrentValuen is " + vcomVoltage);
                                                    int i6 = FacPropertyDemo.HIFAC_MIN_VCOM_SPAN;
                                                    if (vcomVoltage <= i6) {
                                                        vcomVoltage = i6;
                                                    }
                                                    mMenuConfigManager.setVcomVoltage(vcomVoltage);
                                                }
                                                return true;
                                            }
                                            if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                                                if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                                    if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 9 && setTestPattern(i)) {
                                                        return true;
                                                    }
                                                } else if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 8 && setTestPattern(i)) {
                                                    return true;
                                                }
                                            } else if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 7 && setTestPattern(i)) {
                                                return true;
                                            }
                                            if (FacPropertyDemo.HIFAC_BYPASS_SUPPORT) {
                                                if (mCurrentMainFocusButtonID == 3 && this.mCurrentFouceButtonID == 1 && this.mCurrentSelectedPosition == 11 && setTestPattern_hardware(i)) {
                                                    return true;
                                                }
                                            } else if (mCurrentMainFocusButtonID == 3 && this.mCurrentFouceButtonID == 1 && this.mCurrentSelectedPosition == 4 && setTestPattern_hardware(i)) {
                                                return true;
                                            }
                                            if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                                                if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                                    if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 9) {
                                                        return true;
                                                    }
                                                } else if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 8) {
                                                    return true;
                                                }
                                            } else if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 7) {
                                                return true;
                                            }
                                            if (this.mIsAlreadySet) {
                                                if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                                    int vcomType2 = mMenuConfigManager.getVcomType();
                                                    vcomVoltage2 = mMenuConfigManager.getVcomVoltage2(vcomType2) + FacPropertyDemo.HIFAC_STEP_VOM_VOLTAGE;
                                                    Log.i("Fac_MenuMain", "menumain double cell vcomCurrentValuen is " + vcomVoltage2);
                                                    if (vcomType2 == 2 ? vcomVoltage2 > (i4 = FacPropertyDemo.HIFAC_MAX_VCOM_SPAN) : vcomVoltage2 > (i4 = FacPropertyDemo.HIFAC_MAX_VCOM_DOUBLE_CELL_FHD_SPAN)) {
                                                    }
                                                    mMenuConfigManager.setVcomVoltage2(vcomVoltage2, vcomType2);
                                                } else {
                                                    int vcomVoltage4 = mMenuConfigManager.getVcomVoltage();
                                                    Log.i("Fac_MenuMain", "menumain vcomPrevValuen is " + vcomVoltage4);
                                                    i2 = vcomVoltage4 + FacPropertyDemo.HIFAC_STEP_VOM_VOLTAGE;
                                                    Log.i("Fac_MenuMain", "menumain vcomCurrentValuen is " + i2);
                                                    i3 = FacPropertyDemo.HIFAC_MAX_VCOM_SPAN;
                                                    if (i2 > i3) {
                                                        i2 = i3;
                                                    }
                                                    mMenuConfigManager.setVcomVoltage(i2);
                                                }
                                                return true;
                                            }
                                            if (this.infoLinear5G.getVisibility() == 0) {
                                                return true;
                                            }
                                            if (mCurrentMainFocusButtonID != 1 && (setConfigListViewAdapter = this.mListViewAdpter) != null && setConfigListViewAdapter.getCount() == 0) {
                                                return true;
                                            }
                                            Log.i("Fac_MenuMain", "menumain double cell mCurrentFouceButtonID is " + this.mCurrentFouceButtonID);
                                            Log.i("Fac_MenuMain", "menumain double cell mCurrentSelectedPosition is " + this.mCurrentSelectedPosition);
                                            if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                                                if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                                    if (mMenuConfigManager.isHaveRegion()) {
                                                        if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 10 && setTestPattern(i)) {
                                                            return true;
                                                        }
                                                    } else if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 9 && setTestPattern(i)) {
                                                        return true;
                                                    }
                                                } else if (mMenuConfigManager.isHaveRegion()) {
                                                    if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 9 && setTestPattern(i)) {
                                                        return true;
                                                    }
                                                } else if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 8 && setTestPattern(i)) {
                                                    return true;
                                                }
                                            } else if (mMenuConfigManager.isHaveRegion()) {
                                                if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 8 && setTestPattern(i)) {
                                                    return true;
                                                }
                                            } else if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 7 && setTestPattern(i)) {
                                                return true;
                                            }
                                            if (FacPropertyDemo.HIFAC_BYPASS_SUPPORT) {
                                                if (mCurrentMainFocusButtonID == 3 && this.mCurrentFouceButtonID == 1 && this.mCurrentSelectedPosition == 11 && setTestPattern_hardware(i)) {
                                                    return true;
                                                }
                                            } else if (mCurrentMainFocusButtonID == 3 && this.mCurrentFouceButtonID == 1 && this.mCurrentSelectedPosition == 4 && setTestPattern_hardware(i)) {
                                                return true;
                                            }
                                            if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                                                if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                                    if (mMenuConfigManager.isHaveRegion()) {
                                                        if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 11) {
                                                            if (this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                                                                if (setWBPattern(i)) {
                                                                    return true;
                                                                }
                                                            } else if (this.mMenuSetUpListView.getCurrentPageNum() == 2 && FacPropertyDemo.HIFAC_DEMURA_SWITCH_SUPPORT && setDemuraPattern(i)) {
                                                                return true;
                                                            }
                                                        }
                                                    } else if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 10) {
                                                        if (this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                                                            if (setWBPattern(i)) {
                                                                return true;
                                                            }
                                                        } else if (this.mMenuSetUpListView.getCurrentPageNum() == 2 && FacPropertyDemo.HIFAC_DEMURA_SWITCH_SUPPORT && setDemuraPattern(i)) {
                                                            return true;
                                                        }
                                                    }
                                                } else if (mMenuConfigManager.isHaveRegion()) {
                                                    if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 10) {
                                                        if (this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                                                            if (setWBPattern(i)) {
                                                                return true;
                                                            }
                                                        } else if (this.mMenuSetUpListView.getCurrentPageNum() == 2 && FacPropertyDemo.HIFAC_DEMURA_SWITCH_SUPPORT && setDemuraPattern(i)) {
                                                            return true;
                                                        }
                                                    }
                                                } else if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 9) {
                                                    if (this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                                                        if (setWBPattern(i)) {
                                                            return true;
                                                        }
                                                    } else if (this.mMenuSetUpListView.getCurrentPageNum() == 2 && FacPropertyDemo.HIFAC_DEMURA_SWITCH_SUPPORT && setDemuraPattern(i)) {
                                                        return true;
                                                    }
                                                }
                                            } else if (mMenuConfigManager.isHaveRegion()) {
                                                if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 9) {
                                                    if (this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                                                        if (setWBPattern(i)) {
                                                            return true;
                                                        }
                                                    } else if (this.mMenuSetUpListView.getCurrentPageNum() == 2 && FacPropertyDemo.HIFAC_DEMURA_SWITCH_SUPPORT && setDemuraPattern(i)) {
                                                        return true;
                                                    }
                                                }
                                            } else if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 8) {
                                                if (this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                                                    if (setWBPattern(i)) {
                                                        return true;
                                                    }
                                                } else if (this.mMenuSetUpListView.getCurrentPageNum() == 2 && FacPropertyDemo.HIFAC_DEMURA_SWITCH_SUPPORT && setDemuraPattern(i)) {
                                                    return true;
                                                }
                                            }
                                            if (this.versionInfoLinear.getVisibility() == 0) {
                                                this.mTabMainMenuButtonBar.clearFocus();
                                                this.versionLayout1.requestFocus();
                                                return true;
                                            }
                                            if (this.mTabMainMenuButtonBar.hasFocus() && !this.mMenuSetUpListView.hasFocus()) {
                                                this.mTabMainMenuButtonBar.clearFocus();
                                                enableLinearListVisable();
                                                this.mMenuSetUpListView.requestFocus();
                                                this.mMenuSetUpListView.setSelection(0);
                                                this.mExitLevel++;
                                                this.selIdx = 0;
                                                return true;
                                            }
                                            break;
                                        case 22:
                                            if (this.mIsAlreadySet) {
                                                if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                                    int vcomType3 = mMenuConfigManager.getVcomType();
                                                    vcomVoltage2 = mMenuConfigManager.getVcomVoltage2(vcomType3) + FacPropertyDemo.HIFAC_STEP_VOM_VOLTAGE;
                                                    Log.i("Fac_MenuMain", "menumain double cell vcomCurrentValuen is " + vcomVoltage2);
                                                    vcomVoltage2 = vcomType3 == 2 ? i4 : i4;
                                                    mMenuConfigManager.setVcomVoltage2(vcomVoltage2, vcomType3);
                                                } else {
                                                    int vcomVoltage5 = mMenuConfigManager.getVcomVoltage();
                                                    Log.i("Fac_MenuMain", "menumain vcomPrevValuen is " + vcomVoltage5);
                                                    i2 = vcomVoltage5 + FacPropertyDemo.HIFAC_STEP_VOM_VOLTAGE;
                                                    Log.i("Fac_MenuMain", "menumain vcomCurrentValuen is " + i2);
                                                    i3 = FacPropertyDemo.HIFAC_MAX_VCOM_SPAN;
                                                    if (i2 > i3) {
                                                        i2 = i3;
                                                    }
                                                    mMenuConfigManager.setVcomVoltage(i2);
                                                }
                                                return true;
                                            }
                                            if (this.infoLinear5G.getVisibility() == 0) {
                                                return true;
                                            }
                                            if (mCurrentMainFocusButtonID != 1) {
                                            }
                                            Log.i("Fac_MenuMain", "menumain double cell mCurrentFouceButtonID is " + this.mCurrentFouceButtonID);
                                            Log.i("Fac_MenuMain", "menumain double cell mCurrentSelectedPosition is " + this.mCurrentSelectedPosition);
                                            if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                                                if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                                    if (mMenuConfigManager.isHaveRegion()) {
                                                        if (mCurrentMainFocusButtonID == 2) {
                                                            return true;
                                                        }
                                                    } else if (mCurrentMainFocusButtonID == 2) {
                                                        return true;
                                                    }
                                                } else if (mMenuConfigManager.isHaveRegion()) {
                                                    if (mCurrentMainFocusButtonID == 2) {
                                                        return true;
                                                    }
                                                } else if (mCurrentMainFocusButtonID == 2) {
                                                    return true;
                                                }
                                            } else if (mMenuConfigManager.isHaveRegion()) {
                                                if (mCurrentMainFocusButtonID == 2) {
                                                    return true;
                                                }
                                            } else if (mCurrentMainFocusButtonID == 2) {
                                                return true;
                                            }
                                            if (FacPropertyDemo.HIFAC_BYPASS_SUPPORT) {
                                                if (mCurrentMainFocusButtonID == 3) {
                                                    return true;
                                                }
                                            } else if (mCurrentMainFocusButtonID == 3) {
                                                return true;
                                            }
                                            if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                                                if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                                    if (mMenuConfigManager.isHaveRegion()) {
                                                        if (mCurrentMainFocusButtonID == 2) {
                                                            if (this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                                                                if (setWBPattern(i)) {
                                                                    return true;
                                                                }
                                                            } else if (this.mMenuSetUpListView.getCurrentPageNum() == 2) {
                                                                return true;
                                                            }
                                                        }
                                                    } else if (mCurrentMainFocusButtonID == 2) {
                                                        if (this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                                                            if (setWBPattern(i)) {
                                                                return true;
                                                            }
                                                        } else if (this.mMenuSetUpListView.getCurrentPageNum() == 2) {
                                                            return true;
                                                        }
                                                    }
                                                } else if (mMenuConfigManager.isHaveRegion()) {
                                                    if (mCurrentMainFocusButtonID == 2) {
                                                        if (this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                                                            if (setWBPattern(i)) {
                                                                return true;
                                                            }
                                                        } else if (this.mMenuSetUpListView.getCurrentPageNum() == 2) {
                                                            return true;
                                                        }
                                                    }
                                                } else if (mCurrentMainFocusButtonID == 2) {
                                                    if (this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                                                        if (setWBPattern(i)) {
                                                            return true;
                                                        }
                                                    } else if (this.mMenuSetUpListView.getCurrentPageNum() == 2) {
                                                        return true;
                                                    }
                                                }
                                            } else if (mMenuConfigManager.isHaveRegion()) {
                                                if (mCurrentMainFocusButtonID == 2) {
                                                    if (this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                                                        if (setWBPattern(i)) {
                                                            return true;
                                                        }
                                                    } else if (this.mMenuSetUpListView.getCurrentPageNum() == 2) {
                                                        return true;
                                                    }
                                                }
                                            } else if (mCurrentMainFocusButtonID == 2) {
                                                if (this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                                                    if (setWBPattern(i)) {
                                                        return true;
                                                    }
                                                } else if (this.mMenuSetUpListView.getCurrentPageNum() == 2) {
                                                    return true;
                                                }
                                            }
                                            if (this.versionInfoLinear.getVisibility() == 0) {
                                                this.mTabMainMenuButtonBar.clearFocus();
                                                this.versionLayout1.requestFocus();
                                                return true;
                                            }
                                            if (this.mTabMainMenuButtonBar.hasFocus()) {
                                                this.mTabMainMenuButtonBar.clearFocus();
                                                enableLinearListVisable();
                                                this.mMenuSetUpListView.requestFocus();
                                                this.mMenuSetUpListView.setSelection(0);
                                                this.mExitLevel++;
                                                this.selIdx = 0;
                                                return true;
                                            }
                                            break;
                                        case 24:
                                        case 25:
                                            int i7 = mMenuConfigManager.getDefault("HIFAC_VOLUME");
                                            int max = mMenuConfigManager.getMax("HIFAC_VOLUME");
                                            int min = mMenuConfigManager.getMin("HIFAC_VOLUME");
                                            int i8 = i7 + (i == 24 ? 1 : -1);
                                            if (i8 >= min && i8 <= max) {
                                                mMenuConfigManager.setValue("HIFAC_VOLUME", i8);
                                            } else if (i8 > max) {
                                                mMenuConfigManager.setValue("HIFAC_VOLUME", max);
                                            } else {
                                                mMenuConfigManager.setValue("HIFAC_VOLUME", min);
                                            }
                                            return true;
                                    }
                                    break;
                            }
                        } else {
                            finish();
                            return true;
                        }
                    }
                    return super.onKeyDown(i, keyEvent);
                }
                Log.i("Fac_MenuMain", "====mute");
                FacRootMenu.switchMute();
            }
            return true;
        }
        if (i == 23 || i == 66) {
            if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                if (mMenuConfigManager.isHaveRegion()) {
                    if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 9 && setTestPattern(i)) {
                        return true;
                    }
                } else if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 8 && setTestPattern(i)) {
                    return true;
                }
            } else if (mMenuConfigManager.isHaveRegion()) {
                if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 8 && setTestPattern(i)) {
                    return true;
                }
            } else if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 7 && setTestPattern(i)) {
                return true;
            }
            if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                if (mMenuConfigManager.isHaveRegion()) {
                    if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 10) {
                        if (this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                            if (setWBPattern(i)) {
                                return true;
                            }
                        } else if (this.mMenuSetUpListView.getCurrentPageNum() == 2 && FacPropertyDemo.HIFAC_DEMURA_SWITCH_SUPPORT && setDemuraPattern(i)) {
                            return true;
                        }
                    }
                } else if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 9) {
                    if (this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                        if (setWBPattern(i)) {
                            return true;
                        }
                    } else if (this.mMenuSetUpListView.getCurrentPageNum() == 2 && FacPropertyDemo.HIFAC_DEMURA_SWITCH_SUPPORT && setDemuraPattern(i)) {
                        return true;
                    }
                }
            } else if (mMenuConfigManager.isHaveRegion()) {
                if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 9) {
                    if (this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                        if (setWBPattern(i)) {
                            return true;
                        }
                    } else if (this.mMenuSetUpListView.getCurrentPageNum() == 2 && FacPropertyDemo.HIFAC_DEMURA_SWITCH_SUPPORT && setDemuraPattern(i)) {
                        return true;
                    }
                }
            } else if (mCurrentMainFocusButtonID == 2 && this.mCurrentFouceButtonID == 0 && this.mCurrentSelectedPosition == 8) {
                if (this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                    if (setWBPattern(i)) {
                        return true;
                    }
                } else if (this.mMenuSetUpListView.getCurrentPageNum() == 2 && FacPropertyDemo.HIFAC_DEMURA_SWITCH_SUPPORT && setDemuraPattern(i)) {
                    return true;
                }
            }
        }
        if (this.versionInfoLinear.getVisibility() == 0) {
            if (this.versionLayout1.hasFocus()) {
                this.versionLayout1.clearFocus();
                this.versionLayout2.requestFocus();
                return true;
            }
            if (this.versionLayout2.hasFocus()) {
                this.versionLayout2.clearFocus();
                this.versionLayout1.requestFocus();
                return true;
            }
            this.versionInfoLinear.setVisibility(8);
            enableLinearListVisable();
            mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).requestFocus();
        }
        if (this.infoLinear5G.getVisibility() == 0) {
            this.infoLinear5G.setVisibility(8);
            enableLinearListVisable();
            mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).requestFocus();
        }
        if (mMenuConfigManager.isTestPattern()) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("Fac_MenuMain", "MainMenu onCreate***");
        mMenuConfigManager = MenuConfigManager.getInstance(this);
        setContentView(R.layout.menu_main);
        getWindow().addFlags(128);
        mMenuConfigManager.addTVComponenetListener(this);
        this.mDataContainer = MenuDataContainer.getInstance(this);
        this.mFacFunction_Adapter = FacFunction_Adapter.getInstance(getApplicationContext());
        this.mContext = this;
        this.mMyUpData = new MyUpdata();
        if (bundle != null) {
            this.mCurrentFouceButtonID = bundle.getInt("mCurrentFouceButtonID");
            mCurrentMainFocusButtonID = bundle.getInt("mCurrentMainFocusButtonID");
            this.mCurrentSelectedPosition = bundle.getInt("mCurrentSelectedPosition");
            this.mExitLevel = bundle.getInt("mExitLevel");
            mInterruptExit = bundle.getBoolean("mInterruptExit");
        }
        initUI();
        if (SystemProperties.get("ro.designmenu.enable", "1").equals("1")) {
            this.mTabMenuButtonBar.mButtonDesignMenu.setVisibility(0);
            this.mTabMenuButtonBar.mButtonFactoryMenu.setNextFocusLeftId(R.id.widget_buttonDesignMenu);
            this.mTabMenuButtonBar.mButtonFactoryMenu.setNextFocusRightId(R.id.widget_buttonDesignMenu);
        } else {
            this.mTabMenuButtonBar.mButtonDesignMenu.setVisibility(4);
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getInt("fromwhere") == 1) {
            this.mTabMenuButtonBar.mButtonDesignMenu.setVisibility(4);
        }
        this.mHideFacMainMenuReceiver = new HideFacMainMenuReceiver();
        registerReceiver(this.mHideFacMainMenuReceiver, new IntentFilter("com.hisense.broadcom.HIDE_FAC_MAINMENU"));
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            Paint paint = new Paint();
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0.0f);
            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            getWindow().getDecorView().setLayerType(2, paint);
        }
        Log.i("Fac_MenuMain", "MainMenu onCreate***4");
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mHandler.sendEmptyMessageDelayed(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC, FacParamManager.FAC_SCREEN_PROTECT_TIME);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Fac_MenuMain", "MainMenu onDestroy");
        unregisterReceiver(this.mHideFacMainMenuReceiver);
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mHandler.removeMessages(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC);
        }
        mMenuConfigManager.removeTVComponenetListener(this);
        cleanMenuMainData();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        Log.i("Fac_MenuMain", "MainMenu onPause");
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mHandler.removeMessages(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC);
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        Log.i("Fac_MenuMain", "MainMenu onRestart");
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        Log.i("Fac_MenuMain", "MainMenu onStart");
        initBottomVersion();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Log.i("Fac_MenuMain", "MainMenu onResume");
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        if (true == mInterruptExit) {
            bundle.putBoolean("mInterruptExit", true);
            bundle.putInt("mCurrentSelectedPosition", 2);
            bundle.putInt("mCurrentMainFocusButtonID", 3);
            bundle.putInt("mCurrentFouceButtonID", 0);
            bundle.putInt("mExitLevel", 1);
        }
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
    }

    private boolean handleHorizontalFocusWithinListItem(int i) throws Throwable {
        int i2;
        boolean z;
        boolean z2;
        int i3;
        boolean z3;
        int i4;
        if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_TESTPATTERN")) {
            setTestPattern(i);
            return true;
        }
        if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_TEST_PATTERN")) {
            setTestPattern_hardware(i);
            return true;
        }
        if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_INSIDEPATTERN")) {
            this.mFacFunction_Adapter.getInputSourceType(this.mFacFunction_Adapter.getCurrentSource().getTilValue());
            setWBPattern(i);
            return true;
        }
        if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_DEMURA_PATTERN")) {
            setDemuraPattern(i);
            return true;
        }
        Log.i("Fac_MenuMain", "enter handleHorizontalFocusWithinListItem, direction is " + i);
        if (21 == i) {
            setColorTemperatureToStandard();
            if (this.mListViewSelectedItemData.getDataType() == SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD) {
                showNextLevelCofigPage();
                return true;
            }
            if (this.mListViewSelectedItemData.getDataType() != SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW) {
                SetConfigListViewAdapter.DataItem dataItem = this.mListViewSelectedItemData;
                if (dataItem.mParent == null && dataItem.getDataType() != SetConfigListViewAdapter.DataItem.DataType.SWICHOPTIONVIEW) {
                    if (this.mListViewSelectedItemData.getDataType() != SetConfigListViewAdapter.DataItem.DataType.TVSOURCEVIEW) {
                        if (this.mListViewSelectedItemData.getDataType() == SetConfigListViewAdapter.DataItem.DataType.PASSWORDVIEW && (i4 = this.mExitLevel) == 1) {
                            this.mExitLevel = i4 - 1;
                            this.mMenuSetUpListView.clearFocus();
                            this.mTabMenuButtonBarRootView.getChildAt(this.mCurrentFouceButtonID).requestFocus();
                            return true;
                        }
                        if (this.mListViewSelectedItemData.getDataType() == SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR) {
                            if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_OPTION_VCOM_VOLTAGE") || this.mListViewSelectedItemData.getmItemID().equals("FACTORY_OPTION_VCOM2_VOLTAGE")) {
                                z3 = true;
                                if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                                    setVcomType();
                                    FacRootMenu.mIsVcomOK = true;
                                    FacRootMenu.mIsAlreadySet = true;
                                    finish();
                                    return true;
                                }
                            } else {
                                if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_SLIDING_COVER_GAP_ADJUST")) {
                                    return true;
                                }
                                z3 = true;
                            }
                            RespondedKeyEvent respondedKeyEvent = this.childView;
                            if (respondedKeyEvent != null) {
                                respondedKeyEvent.onKeyLeft();
                            }
                            return z3;
                        }
                        RespondedKeyEvent respondedKeyEvent2 = this.childView;
                        if (respondedKeyEvent2 != null) {
                            respondedKeyEvent2.onKeyLeft();
                        }
                        return true;
                    }
                }
            }
            if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_AUDIOCURVE_MODE")) {
                return true;
            }
            RespondedKeyEvent respondedKeyEvent3 = this.childView;
            if (respondedKeyEvent3 != null) {
                respondedKeyEvent3.onKeyLeft();
            }
            SetConfigListViewAdapter.DataItem dataItem2 = this.mListViewSelectedItemData.mParent;
            if (dataItem2 != null && dataItem2.getmItemID().equals("HIFAC_COLOR_TEMP")) {
                return true;
            }
            if (dataItem2 != null && dataItem2.getmInitValue() != dataItem2.userDefined) {
                ((EffectOptionView) this.mMenuSetUpListView.getChildAt(dataItem2.getPosition())).showValue(this.mDataContainer.getValueById(dataItem2.getmItemID()));
                return true;
            }
            if (this.mListViewSelectedItemData.getDataType() == SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW) {
                if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_COLOR_TEMP")) {
                    Log.i("Fac_MenuMain", "effect view is WhiteBalance");
                    this.mDataContainer.loadHisFacData_WhiteBalance();
                    setAdapter(this.mDataContainer.getFacDataGroup_WhiteBalance());
                    return true;
                }
                List<SetConfigListViewAdapter.DataItem> list = this.mListViewSelectedItemData.getmEffectGroup();
                int[] effectGroupInitValues = this.mDataContainer.getEffectGroupInitValues(this.mListViewSelectedItemData);
                for (int i5 = 0; i5 < list.size(); i5++) {
                    SetConfigListViewAdapter.DataItem dataItem3 = list.get(i5);
                    RespondedKeyEvent respondedKeyEvent4 = (RespondedKeyEvent) this.mMenuSetUpListView.getChildAt(dataItem3.getPosition());
                    if (dataItem3.mItemID.equalsIgnoreCase("HIFAC_LIGHT_SENSOR_VALUE")) {
                        if (this.mListViewSelectedItemData.getmInitValue() < 4) {
                            dataItem3.setmEndValue(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR);
                        } else {
                            if (this.mListViewSelectedItemData.getmInitValue() > 3) {
                                i3 = 8;
                                if (this.mListViewSelectedItemData.getmInitValue() < 8) {
                                    dataItem3.setmEndValue(500);
                                }
                            } else {
                                i3 = 8;
                            }
                            if (this.mListViewSelectedItemData.getmInitValue() == i3) {
                                dataItem3.setmEndValue(1000);
                            }
                        }
                        ((ProgressView) this.mMenuSetUpListView.getChildAt(dataItem3.getPosition())).setAdapter(dataItem3);
                    }
                    respondedKeyEvent4.showValue(effectGroupInitValues[i5]);
                }
                if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_SETREGION")) {
                    Log.i("Fac_MenuMain", "effect view is region");
                    this.mDataContainer.loadHisFacData_Function(true);
                    setAdapter(this.mDataContainer.getFacDataGroup_Function());
                    return true;
                }
                if (!this.mListViewSelectedItemData.getmItemID().equals("HIFAC_SETREGION_SA")) {
                    return true;
                }
                Log.i("Fac_MenuMain", "effect view is region sa");
                this.mDataContainer.loadHisFacData_Function(true);
                setAdapter(this.mDataContainer.getFacDataGroup_Function());
                return true;
            }
            if (this.mListViewSelectedItemData.getDataType() != SetConfigListViewAdapter.DataItem.DataType.SWICHOPTIONVIEW) {
                return true;
            }
            this.mMenuSetUpListView.setAdapter((ListAdapter) this.mListViewAdpter);
            this.mMenuSetUpListView.setSelection(this.mCurrentSelectedPosition);
            return true;
        }
        if (22 != i) {
            return false;
        }
        setColorTemperatureToStandard();
        if (this.mListViewSelectedItemData.getDataType() == SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD) {
            if (isFocusOnChannelInitPage(this.mListViewSelectedItemData)) {
                this.mHandler.sendEmptyMessage(111);
                initFactoryChannel(this.mListViewSelectedItemData);
            }
            if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_CLEARPROTECTLY")) {
                this.mHandler.sendEmptyMessage(111);
                mMenuConfigManager.resetProtectly();
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_CLEARUNPROTECTLY") || this.mListViewSelectedItemData.getmItemID().equals("HIFAC_UNPROTECTEDCLEAR")) {
                this.mHandler.sendEmptyMessage(111);
                mMenuConfigManager.resetUnprotectly();
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_CLEAR_KEYS")) {
                mMenuConfigManager.clearKeys();
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_CLEAR_HDCP14_KEY")) {
                if (mMenuConfigManager.clearHdcp14Key()) {
                    mMenuConfigManager.displayHisToast(getResources().getString(R.string.str_clear_hdcp14_success));
                } else {
                    mMenuConfigManager.displayHisToast(getResources().getString(R.string.str_clear_hdcp14_failed));
                }
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_CLEAR_HDCP2X_KEY")) {
                if (mMenuConfigManager.clearHdcp22Key()) {
                    mMenuConfigManager.displayHisToast(getResources().getString(R.string.str_clear_hdcp2x_success));
                } else {
                    mMenuConfigManager.displayHisToast(getResources().getString(R.string.str_clear_hdcp2x_failed));
                }
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_CLEAR_WIDEVINE_KEY")) {
                if (mMenuConfigManager.clearWideVineKey()) {
                    mMenuConfigManager.displayHisToast(getResources().getString(R.string.str_clear_widevine_success));
                } else {
                    mMenuConfigManager.displayHisToast(getResources().getString(R.string.str_clear_widevine_failed));
                }
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_CLEAR_PLAYREADY_KEY")) {
                if (mMenuConfigManager.clearPlayReadyKey()) {
                    mMenuConfigManager.displayHisToast(getResources().getString(R.string.str_clear_playready_success));
                } else {
                    mMenuConfigManager.displayHisToast(getResources().getString(R.string.str_clear_playready_failed));
                }
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_DATAINIT")) {
                this.mHandler.sendEmptyMessage(111);
                mMenuConfigManager.resetDataInit(this.mHandler);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_SETMAC")) {
                mMenuConfigManager.setMacAddrFromOption(this.mContext);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_ZHENJING")) {
                mMenuConfigManager.setOpenMirror(true);
                Intent intent = new Intent();
                intent.setClass(this, ActuatorAdjust.class);
                startActivityForResult(intent, 8);
                finish();
            } else if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_FILTER_CHANGE_TIME")) {
                mMenuConfigManager.setFilterChangeTime(this.mHandler);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_SETCHINADRM")) {
                mMenuConfigManager.setChinaDrmKeyFromUDisk();
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_SETHDCP")) {
                mMenuConfigManager.setHDCPKey();
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_SET_PLAY_READY")) {
                mMenuConfigManager.setLogToTvcertificate("option set playready");
                mMenuConfigManager.isSetPlayReadyKey(this.mContext);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_SET_WIDEVINE")) {
                mMenuConfigManager.setLogToTvcertificate("option set widevine");
                mMenuConfigManager.isSetWindevineKey(this.mContext);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_SET_HDCP_1_4")) {
                mMenuConfigManager.setLogToTvcertificate("option set hdcp14");
                mMenuConfigManager.isSetHdcp14Key(this.mContext);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_SET_HDCP_2_X")) {
                mMenuConfigManager.setLogToTvcertificate("option set hdcp22");
                mMenuConfigManager.isSetHdcp2xKey(this.mContext);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_SET_NETFLIX_KEY")) {
                mMenuConfigManager.setLogToTvcertificate("option set mgk");
                mMenuConfigManager.isSetMGKKey(this.mContext);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_SET_ATTEST_KEY")) {
                mMenuConfigManager.setLogToTvcertificate("option set attestation");
                mMenuConfigManager.isSetAttenstationKey(this.mContext);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_FUN_SETQDA")) {
                mMenuConfigManager.setLogToTvcertificate("option set qda");
                mMenuConfigManager.isSetQDAKey(this.mContext);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_FUN_SETDTCPIP")) {
                mMenuConfigManager.setLogToTvcertificate("option set dtcpip");
                mMenuConfigManager.isSetDTCPIPKey(this.mContext);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_FUN_SETMARLIN")) {
                mMenuConfigManager.setLogToTvcertificate("option set marlin");
                mMenuConfigManager.isSetMarlinKey(this.mContext);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_FUN_SETPVR")) {
                mMenuConfigManager.setLogToTvcertificate("option set PVR");
                mMenuConfigManager.isSetPVRKey(this.mContext);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_FUN_SETSQV")) {
                mMenuConfigManager.setLogToTvcertificate("option set SQV");
                mMenuConfigManager.isSetSQVKey(this.mContext);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_FUN_SET_SERVICE_TAG")) {
                mMenuConfigManager.setLogToTvcertificate("option set ServiceTag");
                mMenuConfigManager.isSetServiceTag(this.mContext);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_OVER_CURRENT")) {
                mMenuConfigManager.setUsbOverCurrent();
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_USB_UPGRADE")) {
                mMenuConfigManager.doUsbUpgrade(this.mContext);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_DTV_NONSTANDA_USER_PROG_TYPE")) {
                new UserProgramTypeDialog(this).show();
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_DTV_NONSTANDA_USER_PROG_INFO")) {
                new UserCurrProgramInforDialog(this).show();
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_UPGRADE_FRC")) {
                mMenuConfigManager.upgradeFRC();
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_UPGRADE_BACKLIGHT_DRIVER")) {
                mMenuConfigManager.upgradeBacklightDriver();
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_UART_DEBUG")) {
                mMenuConfigManager.setUartDebugEnable();
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_RESET_GAMMA")) {
                if (mMenuConfigManager.resetGamma(1)) {
                    SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("FacShareP", 7);
                    this.mShared = sharedPreferences;
                    sharedPreferences.edit().putInt("GAMMAADJUSTRESULT", 0).commit();
                    mMenuConfigManager.displayHisToast(getResources().getString(R.string.str_reset_gamma_success));
                } else {
                    mMenuConfigManager.displayHisToast(getResources().getString(R.string.str_reset_gamma_failed));
                }
            } else if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_MIC_ONE_TEST")) {
                if ("ON".equals(mMenuConfigManager.getMicSwitchStatus())) {
                    mMenuConfigManager.startTestMic(0);
                } else {
                    mMenuConfigManager.displayHisToast("Open MIC Siwtch First!!");
                }
            } else if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_MIC_TWO_TEST")) {
                if ("ON".equals(mMenuConfigManager.getMicSwitchStatus())) {
                    z2 = true;
                    mMenuConfigManager.startTestMic(1);
                    return z2;
                }
                mMenuConfigManager.displayHisToast("Open MIC Siwtch First!!");
            } else if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_MIC_ONE_GET_STATUS")) {
                if ("ON".equals(mMenuConfigManager.getMicSwitchStatus())) {
                    mMenuConfigManager.startExternalMicTest(0);
                } else {
                    mMenuConfigManager.displayHisToast("Open MIC Siwtch First!!");
                }
            } else if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_MIC_TWO_GET_STATUS")) {
                if ("ON".equals(mMenuConfigManager.getMicSwitchStatus())) {
                    z2 = true;
                    mMenuConfigManager.startExternalMicTest(1);
                    return z2;
                }
                mMenuConfigManager.displayHisToast("Open MIC Siwtch First!!");
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_LUX")) {
                mMenuConfigManager.setLogToTvcertificate("display lux value");
                int sensorLuxValue = mMenuConfigManager.getSensorLuxValue();
                mMenuConfigManager.displayHisToast("Lux Value is :" + Integer.toString(sensorLuxValue));
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_Dolby_IQ_Uprade")) {
                mMenuConfigManager.setLogToTvcertificate("dolby IQ uprade");
                String dolbyIqUpdateFile = mMenuConfigManager.getDolbyIqUpdateFile();
                if (dolbyIqUpdateFile == null) {
                    mMenuConfigManager.displayHisToast("Can not find dolby upgrade file !!! \nThe user bin file should be named:" + FacParamManager.HIFAC_DOLBY_UPDATE_FILENAME);
                } else if (this.mFacFunction_Adapter.setDolbyIQUserBinUpdate(dolbyIqUpdateFile) == 0) {
                    mMenuConfigManager.displayHisToast("User file path transfer success ");
                } else {
                    mMenuConfigManager.displayHisToast("User file path transfer failed ");
                }
            } else if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_FUN_CLEAR_OM_RUNNING_TIME")) {
                mMenuConfigManager.clearOMRunningTime(this.mContext);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_SET_AGC")) {
                mMenuConfigManager.setAGC(this.mContext);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_CALIBRATION_OF_BACKGROUND_NOISE")) {
                mMenuConfigManager.calibrationOfBackgroundNoise(this.mContext);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_FUN_FANS_SPEED")) {
                startActivityForResult(new Intent(this, (Class<?>) AlarmAndFanSpeedActivity.class), 0);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_COLOR_WHEEL_ADJUST")) {
                startActivity(new Intent(this, (Class<?>) CWActivity.class));
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_FUN_MAJOR_MAINTENANCE")) {
                mMenuConfigManager.setMajorMaintenance();
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_FUN_MINOR_MAINTENANCE")) {
                mMenuConfigManager.setMinorMaintenance();
            }
            return true;
        }
        if (this.mListViewSelectedItemData.getDataType() != SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW) {
            SetConfigListViewAdapter.DataItem dataItem4 = this.mListViewSelectedItemData;
            if (dataItem4.mParent == null && dataItem4.getDataType() != SetConfigListViewAdapter.DataItem.DataType.SWICHOPTIONVIEW) {
                if (this.mListViewSelectedItemData.getDataType() == SetConfigListViewAdapter.DataItem.DataType.TVSOURCEVIEW) {
                    return false;
                }
                if (this.mListViewSelectedItemData.getDataType() == SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR) {
                    if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_OPTION_VCOM_VOLTAGE") || this.mListViewSelectedItemData.getmItemID().equals("FACTORY_OPTION_VCOM2_VOLTAGE")) {
                        z = true;
                        if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                            setVcomType();
                            FacRootMenu.mIsVcomOK = true;
                            FacRootMenu.mIsAlreadySet = true;
                            finish();
                            return true;
                        }
                    } else {
                        if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_SLIDING_COVER_GAP_ADJUST")) {
                            startActivity(new Intent(this, (Class<?>) GapAdjustActivity.class));
                            return true;
                        }
                        z = true;
                    }
                    RespondedKeyEvent respondedKeyEvent5 = this.childView;
                    if (respondedKeyEvent5 != null) {
                        respondedKeyEvent5.onKeyRight();
                    }
                    return z;
                }
                RespondedKeyEvent respondedKeyEvent6 = this.childView;
                if (respondedKeyEvent6 != null) {
                    respondedKeyEvent6.onKeyRight();
                }
                return true;
            }
        }
        if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_AUDIOCURVE_MODE")) {
            return true;
        }
        RespondedKeyEvent respondedKeyEvent7 = this.childView;
        if (respondedKeyEvent7 != null) {
            respondedKeyEvent7.onKeyRight();
        }
        SetConfigListViewAdapter.DataItem dataItem5 = this.mListViewSelectedItemData.mParent;
        if (dataItem5 != null && dataItem5.getmItemID().equals("HIFAC_COLOR_TEMP")) {
            return true;
        }
        if (dataItem5 != null && dataItem5.getmInitValue() != dataItem5.userDefined) {
            ((EffectOptionView) this.mMenuSetUpListView.getChildAt(dataItem5.getPosition())).showValue(this.mDataContainer.getValueById(dataItem5.getmItemID()));
            return true;
        }
        if (this.mListViewSelectedItemData.getDataType() == SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW) {
            if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_COLOR_TEMP")) {
                Log.i("Fac_MenuMain", "effect view is WhiteBalance");
                this.mDataContainer.loadHisFacData_WhiteBalance();
                setAdapter(this.mDataContainer.getFacDataGroup_WhiteBalance());
                return true;
            }
            List<SetConfigListViewAdapter.DataItem> list2 = this.mListViewSelectedItemData.getmEffectGroup();
            int[] effectGroupInitValues2 = this.mDataContainer.getEffectGroupInitValues(this.mListViewSelectedItemData);
            for (int i6 = 0; i6 < list2.size(); i6++) {
                SetConfigListViewAdapter.DataItem dataItem6 = list2.get(i6);
                RespondedKeyEvent respondedKeyEvent8 = (RespondedKeyEvent) this.mMenuSetUpListView.getChildAt(dataItem6.getPosition());
                if (dataItem6.mItemID.equalsIgnoreCase("HIFAC_LIGHT_SENSOR_VALUE")) {
                    if (this.mListViewSelectedItemData.getmInitValue() < 4) {
                        dataItem6.setmEndValue(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR);
                    } else {
                        if (this.mListViewSelectedItemData.getmInitValue() > 3) {
                            i2 = 8;
                            if (this.mListViewSelectedItemData.getmInitValue() < 8) {
                                dataItem6.setmEndValue(500);
                            }
                        } else {
                            i2 = 8;
                        }
                        if (this.mListViewSelectedItemData.getmInitValue() == i2) {
                            dataItem6.setmEndValue(1000);
                        }
                    }
                    ((ProgressView) this.mMenuSetUpListView.getChildAt(dataItem6.getPosition())).setAdapter(dataItem6);
                }
                respondedKeyEvent8.showValue(effectGroupInitValues2[i6]);
            }
            if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_SETREGION")) {
                Log.i("Fac_MenuMain", "effect view is region");
                this.mDataContainer.loadHisFacData_Function(true);
                setAdapter(this.mDataContainer.getFacDataGroup_Function());
                return true;
            }
            if (!this.mListViewSelectedItemData.getmItemID().equals("HIFAC_SETREGION_SA")) {
                return true;
            }
            Log.i("Fac_MenuMain", "effect view is region sa");
            this.mDataContainer.loadHisFacData_Function(true);
            setAdapter(this.mDataContainer.getFacDataGroup_Function());
            return true;
        }
        if (this.mListViewSelectedItemData.getDataType() != SetConfigListViewAdapter.DataItem.DataType.SWICHOPTIONVIEW) {
            return true;
        }
        this.mMenuSetUpListView.setAdapter((ListAdapter) this.mListViewAdpter);
        this.mMenuSetUpListView.setSelection(this.mCurrentSelectedPosition);
        return true;
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        Log.i("Fac_MenuMain", "the requestCode = " + i + "the resultCode = " + i2);
        if (8 == i) {
            if (i2 == 0) {
                this.mDataContainer.loadHisFacData_Function(false);
                setAdapter(this.mDataContainer.getFacDataGroup_Function());
            }
        } else if (i == 2021 && FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mHandler.sendEmptyMessageDelayed(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC, FacParamManager.FAC_SCREEN_PROTECT_TIME);
        }
        super.onActivityResult(i, i2, intent);
    }

    private boolean handleKeyboardInput(int i) {
        this.mHandler.removeMessages(103);
        this.mHandler.sendEmptyMessageDelayed(103, 9000000L);
        return false;
    }

    private void handleMenuKeyInFactoryMenu() {
        Log.d("Fac_MenuMain", "-----KEYCODE_MENU");
        if (mMenuConfigManager.isTestPattern()) {
            return;
        }
        if (this.mTabMenuButtonBar.hasFocus()) {
            Intent intent = new Intent();
            intent.setClass(this, FacRootMenu.class);
            intent.putExtra("fromMenu", true);
            startActivity(intent);
            finish();
            return;
        }
        if (this.versionInfoLinear.getVisibility() == 0 && (this.versionLayout1.hasFocus() || this.versionLayout2.hasFocus())) {
            this.versionLayout1.clearFocus();
            this.versionLayout2.clearFocus();
            mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).requestFocus();
            this.mExitLevel = 0;
            return;
        }
        if (!this.mMenuSetUpListView.hasFocus() && !this.mTabMenuButtonBar.hasFocus() && !this.mTabMainMenuButtonBar.hasFocus()) {
            enableLinearListVisable();
            this.mMenuSetUpListView.setFocusable(true);
            this.mMenuSetUpListView.requestFocus();
            return;
        }
        if (this.mChangeview.getVisibility() == 0) {
            this.mChangeview.setVisibility(8);
            enableLinearListVisable();
            this.mMenuSetUpListView.setFocusable(true);
            this.mMenuSetUpListView.requestFocus();
        }
        if (this.versionInfoLinear.getVisibility() == 0) {
            this.versionInfoLinear.setVisibility(8);
            this.mFrameLayout.setVisibility(4);
        }
        if (this.infoLinear5G.getVisibility() == 0) {
            this.infoLinear5G.setVisibility(8);
            this.mFrameLayout.setVisibility(4);
        }
        if (mRootRelativeLayout.getVisibility() != 0) {
            mRootRelativeLayout.setVisibility(0);
        }
        if (this.linearList.getVisibility() != 0) {
            enableLinearListVisable();
        }
        if (this.mTabMainMenuButtonBar.hasFocus()) {
            if (this.mMenuSetUpListView.hasFocus()) {
                this.mMenuSetUpListView.clearFocus();
                this.mCurrentSelectedPosition = 0;
                this.mExitLevel = 0;
            }
            this.mFrameLayout.setVisibility(4);
            this.mTabMainMenuButtonBar.clearFocus();
            mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).setBackgroundResource(R.drawable.menu_tab_button_bg_normal);
            this.mTabMenuButtonBarRootView.getChildAt(this.mCurrentFouceButtonID).requestFocus();
            return;
        }
        if (this.mMenuSetUpListView.hasFocus()) {
            this.mCurrentSelectedPosition = -1;
            this.mMenuSetUpListView.clearFocus();
            mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).requestFocus();
            this.mExitLevel = 0;
            return;
        }
        if (this.mListViewSelectedItemData.getDataType() == SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD) {
            int i = this.mExitLevel - 1;
            this.mExitLevel = i;
            if (i == 0) {
                this.mMenuSetUpListView.clearFocus();
                this.mTabMainMenuButtonBar.clearFocus();
                this.mTabMenuButtonBarRootView.getChildAt(this.mCurrentFouceButtonID).requestFocus();
                return;
            }
            reloadParentPageData(i);
        }
    }

    private boolean handleWithListItemKeyEnterEvent() {
        Log.i("Fac_MenuMain", "enter handleWithListItemKeyEnterEvent");
        if (this.mListViewSelectedItemData.getDataType() == SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR && !this.mListViewSelectedItemData.getmItemID().equals("FACTORY_OPTION_VCOM_VOLTAGE") && !this.mListViewSelectedItemData.getmItemID().equals("FACTORY_OPTION_VCOM2_VOLTAGE") && !this.mListViewSelectedItemData.getmItemID().equals("FACTORY_SLIDING_COVER_GAP_ADJUST")) {
            mRootRelativeLayout.setVisibility(4);
            this.mChangeview.setVisibility(0);
            this.mChangeview.requestFocus();
            this.mChangeview.setHandler(this.mHandler);
            this.mChangeview.setAdapter(this.mListViewSelectedItemData);
            this.mChangeview.setVisibility(0);
            return true;
        }
        if (this.mListViewSelectedItemData.getDataType() == SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD) {
            if (isFocusOnChannelInitPage(this.mListViewSelectedItemData)) {
                this.mHandler.sendEmptyMessage(111);
                initFactoryChannel(this.mListViewSelectedItemData);
                return true;
            }
            if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_CLEARUNPROTECTLY")) {
                mMenuConfigManager.resetUnprotectly();
                Intent intent = new Intent();
                intent.setClass(this, FacRootMenu.class);
                startActivity(intent);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_DATAINIT")) {
                this.mHandler.sendEmptyMessage(111);
                mMenuConfigManager.resetDataInit(this.mHandler);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_CLEAR_KEYS")) {
                mMenuConfigManager.clearKeys();
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_CLEAR_HDCP14_KEY")) {
                mMenuConfigManager.clearHdcp14Key();
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_CLEAR_HDCP2X_KEY")) {
                mMenuConfigManager.clearHdcp22Key();
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_CLEAR_WIDEVINE_KEY")) {
                mMenuConfigManager.clearWideVineKey();
            } else if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_CLEAR_PLAYREADY_KEY")) {
                mMenuConfigManager.clearPlayReadyKey();
            }
            return true;
        }
        if (this.mListViewSelectedItemData.getDataType() != SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW) {
            return false;
        }
        RespondedKeyEvent respondedKeyEvent = this.childView;
        if (respondedKeyEvent != null) {
            respondedKeyEvent.onKeyEnter();
        }
        return true;
    }

    private boolean isFocusOnChannelInitPage(SetConfigListViewAdapter.DataItem dataItem) {
        if (dataItem == null) {
            return false;
        }
        String str = dataItem.getmItemID();
        return str.equals("qingdao") || str.equals("huangdao") || str.equals("huangdao_em") || str.equals("huangdao_new") || str.equals("huangdao_sa") || str.equals("qingdao_sa") || str.equals("jiangmen_sa") || str.equals("guiyang") || str.equals("jiangmen") || str.equals("shunde") || str.equals("egypt") || str.equals("syria") || str.equals("south africa") || str.equals("algeria") || str.equals("malaysia") || str.equals("indonesia") || str.equals("iran") || str.equals("ruassia") || str.equals("nigeria") || str.equals("pakistan") || str.equals("egypt tsb") || str.equals("angola") || str.equals("brandt") || str.equals("polytron") || str.equals("huangdao_hK") || str.equals("qingdao_hk") || str.equals("guiyang_hk") || str.equals("jiangmen_hk");
    }

    private void initFactoryChannel(SetConfigListViewAdapter.DataItem dataItem) {
        if (dataItem == null) {
            return;
        }
        final String str = dataItem.getmItemID();
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.MenuMain.3
            @Override // java.lang.Runnable
            public void run() {
                if (str.equals("qingdao") || str.equals("huangdao") || str.equals("huangdao_em") || str.equals("huangdao_new") || str.equals("huangdao_sa") || str.equals("qingdao_sa") || str.equals("jiangmen_sa") || str.equals("guiyang") || str.equals("jiangmen") || str.equals("shunde") || str.equals("egypt") || str.equals("syria") || str.equals("south africa") || str.equals("algeria") || str.equals("malaysia") || str.equals("indonesia") || str.equals("iran") || str.equals("ruassia") || str.equals("nigeria") || str.equals("pakistan") || str.equals("egypt tsb") || str.equals("angola") || str.equals("brandt") || str.equals("polytron") || str.equals("huangdao_hK") || str.equals("qingdao_hk") || str.equals("guiyang_hk") || str.equals("jiangmen_hk")) {
                    Log.i("Fac_MenuMain", str);
                    MenuMain.mMenuConfigManager.presetChannel(str, MenuMain.this.mHandler);
                } else if (str.equals("jiangmen")) {
                    Log.i("Fac_MenuMain", str);
                    MenuMain.mMenuConfigManager.presetChannel("shunde", MenuMain.this.mHandler);
                }
            }
        }).start();
    }

    private void initUI() {
        this.mImageView = (ImageView) findViewById(R.id.picture);
        mMenuMainRelativeLayout = (RelativeLayout) findViewById(R.id.menumain_relativeLayout);
        this.mTabMenuButtonBar = (TabMenuButtonBar) findViewById(R.id.facmenu_tabmenubar);
        this.mTabMainMenuButtonBar = (TabMainMenuButtonBar) findViewById(R.id.facmenu_tabmainmenubar);
        this.mMenuSetUpListView = (MenuSetUpListView) findViewById(R.id.facmenu_sublistView);
        this.mFrameLayout = (RelativeLayout) findViewById(R.id.menumain_framLayout);
        mRootRelativeLayout = (LinearLayout) findViewById(R.id.layout_root);
        this.mTabMenuButtonBarRootView = (LinearLayout) this.mTabMenuButtonBar.getChildAt(0);
        mTabMainMenuButtonBarRootView = (LinearLayout) this.mTabMainMenuButtonBar.getChildAt(0);
        this.linearList = (LinearLayout) findViewById(R.id.menumain_contentlist_layout);
        this.versionInfoLinear = (LinearLayout) findViewById(R.id.menumain_version_layout);
        this.versionLayout1 = (LinearLayout) findViewById(R.id.ll_version1);
        this.versionLayout2 = (LinearLayout) findViewById(R.id.ll_version2);
        this.infoLinear5G = (LinearLayout) findViewById(R.id.menumain_5g_layout);
        this.mVersionInfo_Bottom = (TextView) findViewById(R.id.versioninfo_bottom);
        ChangeValueBarView changeValueBarView = (ChangeValueBarView) findViewById(R.id.changeValueViewBar);
        this.mChangeview = changeValueBarView;
        changeValueBarView.setVisibility(8);
        this.mMenuSetUpListView.setOnKeyListener(this);
        this.mListViewAdpter = new SetConfigListViewAdapter(this);
        setTabMenuButtonListener();
        if (!mInterruptExit) {
            if (this.mTabMenuButtonBarRootView.getChildAt(this.mCurrentFouceButtonID).hasFocus()) {
                return;
            }
            this.mTabMenuButtonBarRootView.getChildAt(this.mCurrentFouceButtonID).requestFocus();
        } else {
            if (mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).hasFocus()) {
                return;
            }
            mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID).requestFocus();
        }
    }

    private void initBottomVersion() {
        String str;
        Log.i("Fac_MenuMain", "initBottomVersion 1");
        String str2 = mMenuConfigManager.getCountryName() + " " + mMenuConfigManager.getLogoName() + " " + mMenuConfigManager.getlanguageName();
        String versionInfo = mMenuConfigManager.getVersionInfo();
        String dateInfo = mMenuConfigManager.getDateInfo(true);
        String str3 = str2 + "    " + mMenuConfigManager.updateStrVersion(versionInfo) + dateInfo;
        mBottomCountryInfo = str3;
        if (FacPropertyDemo.HIFAC_HDCP_KEY_SUPPORT) {
            str3 = str3 + "   " + getResources().getString(R.string.str_factorymenu_hdcp_key) + " " + mMenuConfigManager.getHdcpKey();
        }
        String str4 = "\n" + getResources().getString(R.string.str_factorymenu_macaddr) + "  " + mMenuConfigManager.getMacAddress();
        if (str4 != null && !str4.isEmpty() && str4.length() >= 17 && str4.substring(0, 17).equalsIgnoreCase(mMenuConfigManager.getDefaultMacAddress())) {
            str4 = "  " + getResources().getString(R.string.str_facmenu_macaddrnoset);
        }
        if (mMenuConfigManager.isBluSupport()) {
            str4 = str4 + "  " + getResources().getString(R.string.str_lcm_running_status) + " " + mMenuConfigManager.getLCMRunningStatus();
            if (mMenuConfigManager.isDualBluSupport()) {
                str4 = str4 + "  " + getResources().getString(R.string.str_lcm_running_status2) + " " + mMenuConfigManager.getSlaveLCMRunningStatus();
            }
        }
        if (FacPropertyDemo.HIFAC_MIC_SUPPORT) {
            str4 = (str4 + mMenuConfigManager.getZDMicTestResult()) + mMenuConfigManager.getMicTestResult();
        }
        String str5 = str4 + " PROJECT ID " + mMenuConfigManager.getProjectId();
        if (FacPropertyDemo.HIFAC_VCOM_SUPPORT == 2) {
            int[] vcomsValue = mMenuConfigManager.getVcomsValue();
            String str6 = "" + vcomsValue[0];
            String str7 = "" + vcomsValue[1];
            if (vcomsValue[1] == 0) {
                str7 = "null";
            }
            str5 = str5 + "    " + getResources().getString(R.string.str_factorymenu_vcom) + "  " + str6 + "    " + getResources().getString(R.string.str_factorymenu_vvcom) + "  " + str7;
        }
        String newFangChuanHuoString = mMenuConfigManager.getNewFangChuanHuoString();
        if (mMenuConfigManager.isSNSupport(newFangChuanHuoString) && !FacPropertyDemo.HIFAC_MARKET_SA) {
            str = str3 + str5 + ("\n" + getResources().getString(R.string.str_factorymenu_current_fangchuanghuo) + " " + newFangChuanHuoString);
        } else if (FacPropertyDemo.HIFAC_MARKET_SA) {
            str = str3 + str5 + "\nCUST: " + mMenuConfigManager.getClientType();
        } else {
            str = str3 + str5;
        }
        if (mMenuConfigManager.getLogoName().equalsIgnoreCase("Sunbrite") || mMenuConfigManager.getLogoName().equalsIgnoreCase("snb")) {
            str = str + ("\nService Tag " + mMenuConfigManager.getServiceTag());
        }
        if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT) {
            str = str + ("\n" + getResources().getString(R.string.str_factorymenu_wide_vine_key) + " " + mMenuConfigManager.getDeviceID());
        }
        if (FacPropertyDemo.HIFAC_CHINA_DRM_KEY_SUPPORT) {
            str = str + "\n" + getResources().getString(R.string.str_factorymenu_china_drm_key) + ("  " + mMenuConfigManager.getChinaDrmKey());
        }
        if (FacPropertyDemo.HIFAC_HDCP_1_4_KEY_SUPPORT) {
            str = str + "\n" + getResources().getString(R.string.str_factorymenu_hdcp14_key) + ("  " + mMenuConfigManager.getHdcp14Key());
        }
        if (FacPropertyDemo.HIFAC_HDCP_2_X_KEY_SUPPORT) {
            str = str + "\n" + getResources().getString(R.string.str_factorymenu_hdcp22_key) + ("  " + mMenuConfigManager.getHdcp2xKey());
        }
        if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
            str = str + "\n" + getResources().getString(R.string.str_factorymenu_netflix_key) + ("  " + mMenuConfigManager.getNetflixKey());
        }
        if (FacPropertyDemo.HIFAC_CI_PLUS_KEY_SUPPORT) {
            str = str + "\n" + getResources().getString(R.string.str_factorymenu_ci_plus_key) + ("  " + mMenuConfigManager.getCIPlusKey());
        }
        if (mMenuConfigManager.isSupportQDAKey()) {
            str = str + "\n" + getResources().getString(R.string.str_factorymenu_qda_key) + ("  " + mMenuConfigManager.getQDAKey());
        }
        if (FacPropertyDemo.HIFAC_IS_OLED) {
            String str8 = "  NG";
            if (mMenuConfigManager.checkTconTempVr() == 0) {
                str8 = "  " + mMenuConfigManager.getTconTempVersion() + "  OK";
            } else if (mMenuConfigManager.checkTconTempVr() == 1) {
                str8 = "  " + mMenuConfigManager.getTconTempVersion() + "  NG";
            }
            str = str + "\n" + getResources().getString(R.string.str_factorymenu_tcon_temp_vr) + str8;
        }
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            str = ((str + "\n" + getResources().getString(R.string.str_factorymenu_dtcpip_key) + ("  " + mMenuConfigManager.getDtcpIpKey())) + "\n" + getResources().getString(R.string.str_factorymenu_marlin_key) + ("  " + mMenuConfigManager.getMarlinKey())) + "\n" + getResources().getString(R.string.str_factorymenu_pvr_key) + ("  " + mMenuConfigManager.getPVRKey());
        }
        str.substring(mBottomCountryInfo.length());
        this.mVersionInfo_Bottom.setText(str);
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mVersionInfo_Bottom.setTextColor(-7829368);
        }
        Log.i("Fac_MenuMain", "initBottomVersion 2");
    }

    private void reloadParentPageData(int i) {
        if (i == 1) {
            setParentAdapter(this.mFirstLevelData);
            return;
        }
        if (i == 2) {
            setParentAdapter(this.mSecondLevelData);
        } else if (i == 3) {
            setParentAdapter(this.mThirdLevelData);
        } else {
            setAdapter(this.mDataContainer.getHisFactoryDataGroup());
            this.mMenuSetUpListView.setSelection(this.mBeforeSelectedPosition);
        }
    }

    private void saveParentPageData(int i) {
        ParentData parentData;
        if (i == 2) {
            parentData = this.mFirstLevelData;
        } else if (i == 3) {
            parentData = this.mSecondLevelData;
        } else {
            parentData = i != 4 ? null : this.mThirdLevelData;
        }
        setParentData(parentData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void setAdapter(List<?> list) {
        int currentPageNum;
        System.currentTimeMillis();
        if (this.mListViewSelectedItemData != null) {
            this.mPageSize = 12;
            currentPageNum = this.mMenuSetUpListView.getCurrentPageNum();
        } else {
            currentPageNum = 0;
        }
        if (list != null) {
            Log.i("Fac_MenuMain", "setAdapter adpter size = " + list.size());
        }
        this.mMenuSetUpListView.initData(list, this.mPageSize, this.mMyUpData, currentPageNum);
        List currentList = this.mMenuSetUpListView.getCurrentList();
        this.mGroup = currentList;
        this.mListViewAdpter.setmGroup(currentList);
        this.mMenuSetUpListView.setAdapter((ListAdapter) this.mListViewAdpter);
        if (this.mCurrentSelectedPosition > 0) {
            this.mMenuSetUpListView.requestFocus();
            this.mMenuSetUpListView.setSelection(this.mCurrentSelectedPosition);
        }
    }

    private void setAdapter(String[] strArr) {
        System.currentTimeMillis();
        this.mTabMainMenuButtonBar.initTabData(strArr);
    }

    private void setColorTemperatureToStandard() {
        Log.i("Fac_MenuMain", "set colortmp tp standard");
        if (this.mListViewSelectedItemData.getmItemID().equals("HIFAC_RDRV") || this.mListViewSelectedItemData.getmItemID().equals("HIFAC_GDRV") || this.mListViewSelectedItemData.getmItemID().equals("HIFAC_BDRV") || this.mListViewSelectedItemData.getmItemID().equals("HIFAC_RCUT") || this.mListViewSelectedItemData.getmItemID().equals("HIFAC_GCUT")) {
            return;
        }
        this.mListViewSelectedItemData.getmItemID().equals("HIFAC_BCUT");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void setParentAdapter(ParentData parentData) {
        if (parentData != null) {
            this.mMenuSetUpListView.initData(parentData.mParentGroup, this.mPageSize, this.mMyUpData, parentData.mPageIndex);
            List currentList = this.mMenuSetUpListView.getCurrentList();
            this.mGroup = currentList;
            this.mListViewAdpter.setmGroup(currentList);
            this.mMenuSetUpListView.setAdapter((ListAdapter) this.mListViewAdpter);
            this.mMenuSetUpListView.requestFocus();
            this.mMenuSetUpListView.setSelection(parentData.mSelectedPosition);
        }
    }

    private void setParentData(ParentData parentData) {
        Log.i("Fac_MenuMain", "setParentData");
        if (parentData != null) {
            parentData.mParentGroup = this.mListViewSelectedItemData.getmParentGroup();
            parentData.mPageIndex = this.mMenuSetUpListView.getCurrentPageNum();
            parentData.mSelectedPosition = this.mCurrentSelectedPosition;
        }
    }

    private void setTabMenuButtonListener() {
        this.mTabMenuButtonBar.mButtonFactoryMenu.setOnFocusChangeListener(this);
        this.mTabMenuButtonBar.mButtonDesignMenu.setOnFocusChangeListener(this);
        View.OnKeyListener onKeyListener = new View.OnKeyListener() { // from class: com.hisense.uifac.menu.MenuMain.4
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 0 || (i != 19 && i != 20 && i != 23 && i != 66)) {
                    return false;
                }
                if (FacPropertyDemo.HIFAC_COLORTUNER_SUPPORT || MenuMain.this.mCurrentFouceButtonID != 1) {
                    int unused = MenuMain.mCurrentMainFocusButtonID = 0;
                } else {
                    Log.i("Fac_MenuMain", "colorTuner not support,jumpt it");
                    int unused2 = MenuMain.mCurrentMainFocusButtonID = 1;
                }
                MenuMain.this.mTabMenuButtonBar.clearFocus();
                MenuMain.this.mFrameLayout.setVisibility(0);
                MenuMain.mTabMainMenuButtonBarRootView.getChildAt(MenuMain.mCurrentMainFocusButtonID).requestFocus();
                return true;
            }
        };
        this.mTabMenuButtonBar.mButtonFactoryMenu.setOnKeyListener(onKeyListener);
        this.mTabMenuButtonBar.mButtonDesignMenu.setOnKeyListener(onKeyListener);
        View.OnKeyListener onKeyListener2 = new View.OnKeyListener() { // from class: com.hisense.uifac.menu.MenuMain.5
            /* JADX WARN: Code duplicated, block: B:111:0x01d2  */
            /* JADX WARN: Code duplicated, block: B:113:0x01d8  */
            /* JADX WARN: Code duplicated, block: B:125:0x020e  */
            /* JADX WARN: Code duplicated, block: B:127:0x021b  */
            /* JADX WARN: Code duplicated, block: B:129:0x0221  */
            /* JADX WARN: Code duplicated, block: B:141:0x0257  */
            /* JADX WARN: Code duplicated, block: B:143:0x025d  */
            /* JADX WARN: Code duplicated, block: B:155:0x0292  */
            /* JADX WARN: Code duplicated, block: B:157:0x029b  */
            /* JADX WARN: Code duplicated, block: B:162:0x02ad  */
            /* JADX WARN: Code duplicated, block: B:167:0x02bf  */
            /* JADX WARN: Code duplicated, block: B:170:0x02cd  */
            /* JADX WARN: Code duplicated, block: B:172:0x02ea  */
            /* JADX WARN: Code duplicated, block: B:61:0x00fb  */
            /* JADX WARN: Code duplicated, block: B:63:0x0101  */
            /* JADX WARN: Code duplicated, block: B:65:0x0108  */
            /* JADX WARN: Code duplicated, block: B:67:0x0112  */
            /* JADX WARN: Code duplicated, block: B:69:0x0118  */
            /* JADX WARN: Code duplicated, block: B:81:0x0150  */
            /* JADX WARN: Code duplicated, block: B:83:0x0156  */
            /* JADX WARN: Code duplicated, block: B:95:0x018c  */
            /* JADX WARN: Code duplicated, block: B:97:0x0196  */
            /* JADX WARN: Code duplicated, block: B:99:0x019c  */
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == 0 && (i == 23 || i == 66)) {
                    if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                        Log.i("Fac_MenuMain", "vcom support");
                        if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                            if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                if (view.getId() == R.id.widget_buttonMainFactoryMenu_2 && MenuMain.this.mCurrentFouceButtonID == 0 && MenuMain.this.mCurrentSelectedPosition == 10) {
                                    MenuMain.this.setTestPattern(i);
                                } else if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                                    Log.i("Fac_MenuMain", "vcom support");
                                    if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                        if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_2 && MenuMain.this.mCurrentFouceButtonID == 0 && MenuMain.this.mCurrentSelectedPosition == 11) {
                                                if (MenuMain.this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                                                    MenuMain.this.setWBPattern(i);
                                                } else if (MenuMain.this.mMenuSetUpListView.getCurrentPageNum() == 2 && FacPropertyDemo.HIFAC_DEMURA_SWITCH_SUPPORT) {
                                                    MenuMain.this.setDemuraPattern(i);
                                                }
                                            } else {
                                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_3 && MenuMain.this.mCurrentFouceButtonID == 0) {
                                                    return true;
                                                }
                                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_15 && MenuMain.this.mCurrentFouceButtonID == 1) {
                                                    return true;
                                                }
                                                if (view.getId() == R.id.widget_buttonMainFactoryMenu_4 || MenuMain.this.mCurrentFouceButtonID != 0) {
                                                    MenuMain.this.enableLinearListVisable();
                                                    MenuMain.this.mMenuSetUpListView.requestFocus();
                                                    MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                    if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                        MenuMain.access$3208(MenuMain.this);
                                                        MenuMain.this.selIdx = 0;
                                                    }
                                                } else {
                                                    MenuMain.this.startMicTest();
                                                }
                                            }
                                        } else if (view.getId() != R.id.widget_buttonMainFactoryMenu_2 && MenuMain.this.mCurrentFouceButtonID == 0 && MenuMain.this.mCurrentSelectedPosition == 10) {
                                            if (MenuMain.this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                                                MenuMain.this.setWBPattern(i);
                                            } else if (MenuMain.this.mMenuSetUpListView.getCurrentPageNum() == 2 && FacPropertyDemo.HIFAC_DEMURA_SWITCH_SUPPORT) {
                                                MenuMain.this.setDemuraPattern(i);
                                            }
                                        } else {
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                            }
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                            }
                                            if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                                MenuMain.this.enableLinearListVisable();
                                                MenuMain.this.mMenuSetUpListView.requestFocus();
                                                MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                    MenuMain.access$3208(MenuMain.this);
                                                    MenuMain.this.selIdx = 0;
                                                }
                                            } else {
                                                MenuMain.this.enableLinearListVisable();
                                                MenuMain.this.mMenuSetUpListView.requestFocus();
                                                MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                    MenuMain.access$3208(MenuMain.this);
                                                    MenuMain.this.selIdx = 0;
                                                }
                                            }
                                        }
                                    } else if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_2 && MenuMain.this.mCurrentFouceButtonID == 0 && MenuMain.this.mCurrentSelectedPosition == 10) {
                                            if (MenuMain.this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                                                MenuMain.this.setWBPattern(i);
                                            } else if (MenuMain.this.mMenuSetUpListView.getCurrentPageNum() == 2 && FacPropertyDemo.HIFAC_DEMURA_SWITCH_SUPPORT) {
                                                MenuMain.this.setDemuraPattern(i);
                                            }
                                        } else {
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                            }
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                            }
                                            if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                                MenuMain.this.enableLinearListVisable();
                                                MenuMain.this.mMenuSetUpListView.requestFocus();
                                                MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                    MenuMain.access$3208(MenuMain.this);
                                                    MenuMain.this.selIdx = 0;
                                                }
                                            } else {
                                                MenuMain.this.enableLinearListVisable();
                                                MenuMain.this.mMenuSetUpListView.requestFocus();
                                                MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                    MenuMain.access$3208(MenuMain.this);
                                                    MenuMain.this.selIdx = 0;
                                                }
                                            }
                                        }
                                    } else if (view.getId() != R.id.widget_buttonMainFactoryMenu_2 && MenuMain.this.mCurrentFouceButtonID == 0 && MenuMain.this.mCurrentSelectedPosition == 9) {
                                        if (MenuMain.this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                                            MenuMain.this.setWBPattern(i);
                                        } else if (MenuMain.this.mMenuSetUpListView.getCurrentPageNum() == 2 && FacPropertyDemo.HIFAC_DEMURA_SWITCH_SUPPORT) {
                                            MenuMain.this.setDemuraPattern(i);
                                        }
                                    } else {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    }
                                } else {
                                    Log.i("Fac_MenuMain", "vcom not support");
                                    if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_2 && MenuMain.this.mCurrentFouceButtonID == 0 && MenuMain.this.mCurrentSelectedPosition == 9) {
                                            if (MenuMain.this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                                                MenuMain.this.setWBPattern(i);
                                            } else if (MenuMain.this.mMenuSetUpListView.getCurrentPageNum() == 2 && FacPropertyDemo.HIFAC_DEMURA_SWITCH_SUPPORT) {
                                                MenuMain.this.setDemuraPattern(i);
                                            }
                                        } else {
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                            }
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                            }
                                            if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                                MenuMain.this.enableLinearListVisable();
                                                MenuMain.this.mMenuSetUpListView.requestFocus();
                                                MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                    MenuMain.access$3208(MenuMain.this);
                                                    MenuMain.this.selIdx = 0;
                                                }
                                            } else {
                                                MenuMain.this.enableLinearListVisable();
                                                MenuMain.this.mMenuSetUpListView.requestFocus();
                                                MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                    MenuMain.access$3208(MenuMain.this);
                                                    MenuMain.this.selIdx = 0;
                                                }
                                            }
                                        }
                                    } else if (view.getId() != R.id.widget_buttonMainFactoryMenu_2 && MenuMain.this.mCurrentFouceButtonID == 0 && MenuMain.this.mCurrentSelectedPosition == 8) {
                                        if (MenuMain.this.mMenuSetUpListView.getCurrentPageNum() == 1) {
                                            MenuMain.this.setWBPattern(i);
                                        } else if (MenuMain.this.mMenuSetUpListView.getCurrentPageNum() == 2 && FacPropertyDemo.HIFAC_DEMURA_SWITCH_SUPPORT) {
                                            MenuMain.this.setDemuraPattern(i);
                                        }
                                    } else {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    }
                                }
                            } else if (view.getId() == R.id.widget_buttonMainFactoryMenu_2 && MenuMain.this.mCurrentFouceButtonID == 0 && MenuMain.this.mCurrentSelectedPosition == 9) {
                                MenuMain.this.setTestPattern(i);
                            } else if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                                Log.i("Fac_MenuMain", "vcom support");
                                if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                    if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                            }
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                            }
                                            if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                                MenuMain.this.enableLinearListVisable();
                                                MenuMain.this.mMenuSetUpListView.requestFocus();
                                                MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                    MenuMain.access$3208(MenuMain.this);
                                                    MenuMain.this.selIdx = 0;
                                                }
                                            } else {
                                                MenuMain.this.enableLinearListVisable();
                                                MenuMain.this.mMenuSetUpListView.requestFocus();
                                                MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                    MenuMain.access$3208(MenuMain.this);
                                                    MenuMain.this.selIdx = 0;
                                                }
                                            }
                                        } else {
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                            }
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                            }
                                            if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                                MenuMain.this.enableLinearListVisable();
                                                MenuMain.this.mMenuSetUpListView.requestFocus();
                                                MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                    MenuMain.access$3208(MenuMain.this);
                                                    MenuMain.this.selIdx = 0;
                                                }
                                            } else {
                                                MenuMain.this.enableLinearListVisable();
                                                MenuMain.this.mMenuSetUpListView.requestFocus();
                                                MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                    MenuMain.access$3208(MenuMain.this);
                                                    MenuMain.this.selIdx = 0;
                                                }
                                            }
                                        }
                                    } else if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    } else {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    }
                                } else if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    } else {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    }
                                } else if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                } else {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                }
                            } else {
                                Log.i("Fac_MenuMain", "vcom not support");
                                if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    } else {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    }
                                } else if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                } else {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                }
                            }
                        } else if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                            if (view.getId() == R.id.widget_buttonMainFactoryMenu_2 && MenuMain.this.mCurrentFouceButtonID == 0 && MenuMain.this.mCurrentSelectedPosition == 9) {
                                MenuMain.this.setTestPattern(i);
                            } else if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                                Log.i("Fac_MenuMain", "vcom support");
                                if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                    if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                            }
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                            }
                                            if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                                MenuMain.this.enableLinearListVisable();
                                                MenuMain.this.mMenuSetUpListView.requestFocus();
                                                MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                    MenuMain.access$3208(MenuMain.this);
                                                    MenuMain.this.selIdx = 0;
                                                }
                                            } else {
                                                MenuMain.this.enableLinearListVisable();
                                                MenuMain.this.mMenuSetUpListView.requestFocus();
                                                MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                    MenuMain.access$3208(MenuMain.this);
                                                    MenuMain.this.selIdx = 0;
                                                }
                                            }
                                        } else {
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                            }
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                            }
                                            if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                                MenuMain.this.enableLinearListVisable();
                                                MenuMain.this.mMenuSetUpListView.requestFocus();
                                                MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                    MenuMain.access$3208(MenuMain.this);
                                                    MenuMain.this.selIdx = 0;
                                                }
                                            } else {
                                                MenuMain.this.enableLinearListVisable();
                                                MenuMain.this.mMenuSetUpListView.requestFocus();
                                                MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                    MenuMain.access$3208(MenuMain.this);
                                                    MenuMain.this.selIdx = 0;
                                                }
                                            }
                                        }
                                    } else if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    } else {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    }
                                } else if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    } else {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    }
                                } else if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                } else {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                }
                            } else {
                                Log.i("Fac_MenuMain", "vcom not support");
                                if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    } else {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    }
                                } else if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                } else {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                }
                            }
                        } else if (view.getId() == R.id.widget_buttonMainFactoryMenu_2 && MenuMain.this.mCurrentFouceButtonID == 0 && MenuMain.this.mCurrentSelectedPosition == 8) {
                            MenuMain.this.setTestPattern(i);
                        } else if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                            Log.i("Fac_MenuMain", "vcom support");
                            if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    } else {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    }
                                } else if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                } else {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                }
                            } else if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                } else {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                }
                            } else if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                }
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                }
                                if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                    MenuMain.this.enableLinearListVisable();
                                    MenuMain.this.mMenuSetUpListView.requestFocus();
                                    MenuMain.this.mMenuSetUpListView.setSelection(0);
                                    if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                        MenuMain.access$3208(MenuMain.this);
                                        MenuMain.this.selIdx = 0;
                                    }
                                } else {
                                    MenuMain.this.enableLinearListVisable();
                                    MenuMain.this.mMenuSetUpListView.requestFocus();
                                    MenuMain.this.mMenuSetUpListView.setSelection(0);
                                    if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                        MenuMain.access$3208(MenuMain.this);
                                        MenuMain.this.selIdx = 0;
                                    }
                                }
                            } else {
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                }
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                }
                                if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                    MenuMain.this.enableLinearListVisable();
                                    MenuMain.this.mMenuSetUpListView.requestFocus();
                                    MenuMain.this.mMenuSetUpListView.setSelection(0);
                                    if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                        MenuMain.access$3208(MenuMain.this);
                                        MenuMain.this.selIdx = 0;
                                    }
                                } else {
                                    MenuMain.this.enableLinearListVisable();
                                    MenuMain.this.mMenuSetUpListView.requestFocus();
                                    MenuMain.this.mMenuSetUpListView.setSelection(0);
                                    if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                        MenuMain.access$3208(MenuMain.this);
                                        MenuMain.this.selIdx = 0;
                                    }
                                }
                            }
                        } else {
                            Log.i("Fac_MenuMain", "vcom not support");
                            if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                } else {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                }
                            } else if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                }
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                }
                                if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                    MenuMain.this.enableLinearListVisable();
                                    MenuMain.this.mMenuSetUpListView.requestFocus();
                                    MenuMain.this.mMenuSetUpListView.setSelection(0);
                                    if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                        MenuMain.access$3208(MenuMain.this);
                                        MenuMain.this.selIdx = 0;
                                    }
                                } else {
                                    MenuMain.this.enableLinearListVisable();
                                    MenuMain.this.mMenuSetUpListView.requestFocus();
                                    MenuMain.this.mMenuSetUpListView.setSelection(0);
                                    if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                        MenuMain.access$3208(MenuMain.this);
                                        MenuMain.this.selIdx = 0;
                                    }
                                }
                            } else {
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                }
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                }
                                if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                    MenuMain.this.enableLinearListVisable();
                                    MenuMain.this.mMenuSetUpListView.requestFocus();
                                    MenuMain.this.mMenuSetUpListView.setSelection(0);
                                    if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                        MenuMain.access$3208(MenuMain.this);
                                        MenuMain.this.selIdx = 0;
                                    }
                                } else {
                                    MenuMain.this.enableLinearListVisable();
                                    MenuMain.this.mMenuSetUpListView.requestFocus();
                                    MenuMain.this.mMenuSetUpListView.setSelection(0);
                                    if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                        MenuMain.access$3208(MenuMain.this);
                                        MenuMain.this.selIdx = 0;
                                    }
                                }
                            }
                        }
                    } else {
                        Log.i("Fac_MenuMain", "vcom not support");
                        if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                            if (view.getId() == R.id.widget_buttonMainFactoryMenu_2 && MenuMain.this.mCurrentFouceButtonID == 0 && MenuMain.this.mCurrentSelectedPosition == 8) {
                                MenuMain.this.setTestPattern(i);
                            } else if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                                Log.i("Fac_MenuMain", "vcom support");
                                if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                    if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                            }
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                            }
                                            if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                                MenuMain.this.enableLinearListVisable();
                                                MenuMain.this.mMenuSetUpListView.requestFocus();
                                                MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                    MenuMain.access$3208(MenuMain.this);
                                                    MenuMain.this.selIdx = 0;
                                                }
                                            } else {
                                                MenuMain.this.enableLinearListVisable();
                                                MenuMain.this.mMenuSetUpListView.requestFocus();
                                                MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                    MenuMain.access$3208(MenuMain.this);
                                                    MenuMain.this.selIdx = 0;
                                                }
                                            }
                                        } else {
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                            }
                                            if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                            }
                                            if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                                MenuMain.this.enableLinearListVisable();
                                                MenuMain.this.mMenuSetUpListView.requestFocus();
                                                MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                    MenuMain.access$3208(MenuMain.this);
                                                    MenuMain.this.selIdx = 0;
                                                }
                                            } else {
                                                MenuMain.this.enableLinearListVisable();
                                                MenuMain.this.mMenuSetUpListView.requestFocus();
                                                MenuMain.this.mMenuSetUpListView.setSelection(0);
                                                if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                    MenuMain.access$3208(MenuMain.this);
                                                    MenuMain.this.selIdx = 0;
                                                }
                                            }
                                        }
                                    } else if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    } else {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    }
                                } else if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    } else {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    }
                                } else if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                } else {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                }
                            } else {
                                Log.i("Fac_MenuMain", "vcom not support");
                                if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    } else {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    }
                                } else if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                } else {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                }
                            }
                        } else if (view.getId() == R.id.widget_buttonMainFactoryMenu_2 && MenuMain.this.mCurrentFouceButtonID == 0 && MenuMain.this.mCurrentSelectedPosition == 7) {
                            MenuMain.this.setTestPattern(i);
                        } else if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                            Log.i("Fac_MenuMain", "vcom support");
                            if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    } else {
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                        }
                                        if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                        }
                                        if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        } else {
                                            MenuMain.this.enableLinearListVisable();
                                            MenuMain.this.mMenuSetUpListView.requestFocus();
                                            MenuMain.this.mMenuSetUpListView.setSelection(0);
                                            if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                                MenuMain.access$3208(MenuMain.this);
                                                MenuMain.this.selIdx = 0;
                                            }
                                        }
                                    }
                                } else if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                } else {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                }
                            } else if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                } else {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                }
                            } else if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                }
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                }
                                if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                    MenuMain.this.enableLinearListVisable();
                                    MenuMain.this.mMenuSetUpListView.requestFocus();
                                    MenuMain.this.mMenuSetUpListView.setSelection(0);
                                    if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                        MenuMain.access$3208(MenuMain.this);
                                        MenuMain.this.selIdx = 0;
                                    }
                                } else {
                                    MenuMain.this.enableLinearListVisable();
                                    MenuMain.this.mMenuSetUpListView.requestFocus();
                                    MenuMain.this.mMenuSetUpListView.setSelection(0);
                                    if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                        MenuMain.access$3208(MenuMain.this);
                                        MenuMain.this.selIdx = 0;
                                    }
                                }
                            } else {
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                }
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                }
                                if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                    MenuMain.this.enableLinearListVisable();
                                    MenuMain.this.mMenuSetUpListView.requestFocus();
                                    MenuMain.this.mMenuSetUpListView.setSelection(0);
                                    if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                        MenuMain.access$3208(MenuMain.this);
                                        MenuMain.this.selIdx = 0;
                                    }
                                } else {
                                    MenuMain.this.enableLinearListVisable();
                                    MenuMain.this.mMenuSetUpListView.requestFocus();
                                    MenuMain.this.mMenuSetUpListView.setSelection(0);
                                    if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                        MenuMain.access$3208(MenuMain.this);
                                        MenuMain.this.selIdx = 0;
                                    }
                                }
                            }
                        } else {
                            Log.i("Fac_MenuMain", "vcom not support");
                            if (MenuMain.mMenuConfigManager.isHaveRegion()) {
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                } else {
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                    }
                                    if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                    }
                                    if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    } else {
                                        MenuMain.this.enableLinearListVisable();
                                        MenuMain.this.mMenuSetUpListView.requestFocus();
                                        MenuMain.this.mMenuSetUpListView.setSelection(0);
                                        if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                            MenuMain.access$3208(MenuMain.this);
                                            MenuMain.this.selIdx = 0;
                                        }
                                    }
                                }
                            } else if (view.getId() != R.id.widget_buttonMainFactoryMenu_2) {
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                }
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                }
                                if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                    MenuMain.this.enableLinearListVisable();
                                    MenuMain.this.mMenuSetUpListView.requestFocus();
                                    MenuMain.this.mMenuSetUpListView.setSelection(0);
                                    if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                        MenuMain.access$3208(MenuMain.this);
                                        MenuMain.this.selIdx = 0;
                                    }
                                } else {
                                    MenuMain.this.enableLinearListVisable();
                                    MenuMain.this.mMenuSetUpListView.requestFocus();
                                    MenuMain.this.mMenuSetUpListView.setSelection(0);
                                    if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                        MenuMain.access$3208(MenuMain.this);
                                        MenuMain.this.selIdx = 0;
                                    }
                                }
                            } else {
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_3) {
                                }
                                if (view.getId() != R.id.widget_buttonMainFactoryMenu_15) {
                                }
                                if (view.getId() == R.id.widget_buttonMainFactoryMenu_4) {
                                    MenuMain.this.enableLinearListVisable();
                                    MenuMain.this.mMenuSetUpListView.requestFocus();
                                    MenuMain.this.mMenuSetUpListView.setSelection(0);
                                    if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                        MenuMain.access$3208(MenuMain.this);
                                        MenuMain.this.selIdx = 0;
                                    }
                                } else {
                                    MenuMain.this.enableLinearListVisable();
                                    MenuMain.this.mMenuSetUpListView.requestFocus();
                                    MenuMain.this.mMenuSetUpListView.setSelection(0);
                                    if (MenuMain.this.mMenuSetUpListView.hasFocus()) {
                                        MenuMain.access$3208(MenuMain.this);
                                        MenuMain.this.selIdx = 0;
                                    }
                                }
                            }
                        }
                    }
                }
                return false;
            }
        };
        for (int i = 0; i < TabMainMenuButtonBar.BUTTONMAINMENU_COUNT; i++) {
            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[i].setOnFocusChangeListener(this);
            this.mTabMainMenuButtonBar.mButtonMainMenu_Item[i].setOnKeyListener(onKeyListener2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setWBPattern(int i) {
        Log.i("Fac_MenuMain", "mWBPatternTimes is " + this.mWBPatternTimes);
        this.mButtonCurrentMainTab = (Button) mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID);
        mRootRelativeLayout.setVisibility(8);
        if (this.mWBPatternTimes == -1) {
            SystemProperties.set("sys.vt.factory.wb_pattern_test", "true");
            mMenuConfigManager.setWBPattern(20);
        }
        if (i == 22 || i == 66 || i == 23) {
            int i2 = this.mWBPatternTimes + 1;
            this.mWBPatternTimes = i2;
            if (i2 > 13) {
                this.mWBPatternTimes = 21;
            }
        } else {
            int i3 = this.mWBPatternTimes - 1;
            this.mWBPatternTimes = i3;
            if (i3 < 0) {
                this.mWBPatternTimes = 21;
            }
        }
        mMenuConfigManager.setWBPattern(this.mWBPatternTimes);
        if (this.mWBPatternTimes == 21) {
            Log.i("Fac_MenuMain", "close mWBPattern");
            SystemProperties.set("sys.vt.factory.wb_pattern_test", "false");
            this.mWBPatternTimes = -1;
            mMenuMainRelativeLayout.setBackgroundResource(R.drawable.translucent_background);
            mRootRelativeLayout.setVisibility(0);
            this.mButtonCurrentMainTab.requestFocus();
            mMenuConfigManager.pauseTv(false);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setTestPattern(int i) {
        this.mButtonCurrentMainTab = (Button) mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID);
        mRootRelativeLayout.setVisibility(8);
        Log.i("Fac_MenuMain", "setTestPattern mMenuConfigManager.mTestPattern_Mode " + mMenuConfigManager.mTestPattern_Mode);
        if (mMenuConfigManager.mTestPattern_Mode == MenuConfigManager.TestPattern.CIRCLE && i == 21) {
            mMenuMainRelativeLayout.setBackgroundResource(R.drawable.translucent_background);
        }
        mMenuConfigManager.adjustTestPattern(i == 22 || i == 66 || i == 23);
        if (mMenuConfigManager.mTestPattern_Mode == MenuConfigManager.TestPattern.OFF) {
            if (MenuConfigManager.isAudioMute()) {
                sendMuteBroadcast(false);
            }
            SystemProperties.set("sys.vt.factory.pattern_test", "false");
        } else {
            if (MenuConfigManager.isAudioMute()) {
                sendMuteBroadcast(true);
            }
            SystemProperties.set("sys.vt.factory.pattern_test", "true");
        }
        Log.i("Fac_MenuMain", "setTestPattern mMenuConfigManager.mTestPattern_Mode2 " + mMenuConfigManager.mTestPattern_Mode);
        switch (AnonymousClass6.$SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern[mMenuConfigManager.mTestPattern_Mode.ordinal()]) {
            case 1:
                mMenuConfigManager.setTestPattern(1, 255, 0, 0);
                return true;
            case 2:
                mMenuConfigManager.setTestPattern(1, 0, 255, 0);
                return true;
            case 3:
                mMenuConfigManager.setTestPattern(1, 0, 0, 255);
                return true;
            case 4:
                mMenuConfigManager.setTestPattern(1, 255, 255, 255);
                return true;
            case 5:
                if (FacPropertyDemo.HIFAC_LOCALDIMMING_SUPPORT) {
                    mMenuConfigManager.setBackLightOn(true);
                    mMenuConfigManager.setLocalDimmingSwitch(false);
                }
                mMenuConfigManager.setTestPattern(1, 0, 0, 0);
                return true;
            case 6:
                mMenuConfigManager.setTestPattern(0, 0, 0, 0);
                mMenuMainRelativeLayout.setBackgroundResource(R.drawable.test_pattern);
                return true;
            default:
                mMenuConfigManager.setTestPattern(0, 0, 0, 0);
                mMenuMainRelativeLayout.setBackgroundResource(R.drawable.translucent_background);
                if (FacPropertyDemo.HIFAC_LOCALDIMMING_SUPPORT) {
                    mMenuConfigManager.setBackLightOn(false);
                    mMenuConfigManager.setLocalDimmingSwitch(true);
                }
                mRootRelativeLayout.setVisibility(0);
                this.mButtonCurrentMainTab.requestFocus();
                return true;
        }
    }

    private boolean setTestPattern_hardware(int i) {
        Log.i("Fac_MenuMain", "setTestPattern_hardware");
        this.mButtonCurrentMainTab = (Button) mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID);
        mRootRelativeLayout.setVisibility(8);
        mMenuConfigManager.adjustTestPattern_hardware(i == 22 || i == 66 || i == 23);
        if (mMenuConfigManager.mTestPattern_Mode_hardware == MenuConfigManager.TestPattern_hardware.OFF) {
            this.mImageView.setVisibility(8);
        } else {
            this.mImageView.setVisibility(0);
        }
        switch (AnonymousClass6.$SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern_hardware[mMenuConfigManager.mTestPattern_Mode_hardware.ordinal()]) {
            case 1:
                this.mImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.red_pq));
                return true;
            case 2:
                this.mImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.green_pq));
                return true;
            case 3:
                this.mImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.blue_pq));
                return true;
            case 4:
                this.mImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.white_pq));
                return true;
            case 5:
                this.mImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.black_pq));
                return true;
            case 6:
                this.mImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cyan_pq));
                return true;
            case 7:
                this.mImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.purple_pq));
                return true;
            case 8:
                this.mImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.yellow_pq));
                return true;
            default:
                mMenuMainRelativeLayout.setBackgroundResource(R.drawable.translucent_background);
                this.mImageView.setVisibility(8);
                mRootRelativeLayout.setVisibility(0);
                this.mButtonCurrentMainTab.requestFocus();
                return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setDemuraPattern(int i) {
        this.mButtonCurrentMainTab = (Button) mTabMainMenuButtonBarRootView.getChildAt(mCurrentMainFocusButtonID);
        mRootRelativeLayout.setVisibility(8);
        Log.i("Fac_MenuMain", "setDemuraPattern mMenuConfigManager.mDemuraPattern_Mode " + mMenuConfigManager.mDemuraPattern_Mode);
        if (mMenuConfigManager.mDemuraPattern_Mode == MenuConfigManager.DemuraPattern.GRAY255 && i == 21) {
            mMenuMainRelativeLayout.setBackgroundResource(R.drawable.translucent_background);
        }
        mMenuConfigManager.adjustDemuraPattern(i == 22 || i == 66 || i == 23);
        if (mMenuConfigManager.mDemuraPattern_Mode == MenuConfigManager.DemuraPattern.OFF) {
            if (MenuConfigManager.isAudioMute()) {
                sendMuteBroadcast(false);
            }
            DemuraUtil.setDemuraPropStatus("false");
        } else {
            if (MenuConfigManager.isAudioMute()) {
                sendMuteBroadcast(true);
            }
            DemuraUtil.setDemuraPropStatus("true");
        }
        Log.i("Fac_MenuMain", "setDemuraPattern mMenuConfigManager.mDemuraPattern_Mode2 " + mMenuConfigManager.mDemuraPattern_Mode);
        switch (AnonymousClass6.$SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$DemuraPattern[mMenuConfigManager.mDemuraPattern_Mode.ordinal()]) {
            case 1:
                mMenuMainRelativeLayout.setBackgroundResource(R.drawable.demuradotpattern);
                return true;
            case 2:
                mMenuConfigManager.setGamma(new int[]{1, 0, 0, 0});
                return true;
            case 3:
                mMenuConfigManager.setGamma(new int[]{1, 76, 76, 76});
                return true;
            case 4:
                mMenuConfigManager.setGamma(new int[]{1, 128, 128, 128});
                return true;
            case 5:
                mMenuConfigManager.setGamma(new int[]{1, 240, 240, 240});
                return true;
            case 6:
                mMenuConfigManager.setGamma(new int[]{1, 255, 255, 255});
                return true;
            default:
                mMenuConfigManager.closeGamma(new int[]{1, 0, 0, 0});
                mMenuMainRelativeLayout.setBackgroundResource(R.drawable.translucent_background);
                if (FacPropertyDemo.HIFAC_LOCALDIMMING_SUPPORT) {
                    mMenuConfigManager.setBackLightOn(false);
                    mMenuConfigManager.setLocalDimmingSwitch(true);
                }
                mRootRelativeLayout.setVisibility(0);
                this.mButtonCurrentMainTab.requestFocus();
                return true;
        }
    }

    /* JADX INFO: renamed from: com.hisense.uifac.menu.MenuMain$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$DemuraPattern;
        static final /* synthetic */ int[] $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern;
        static final /* synthetic */ int[] $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern_hardware;

        static {
            int[] iArr = new int[MenuConfigManager.DemuraPattern.values().length];
            $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$DemuraPattern = iArr;
            try {
                iArr[MenuConfigManager.DemuraPattern.DOT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$DemuraPattern[MenuConfigManager.DemuraPattern.GRAY0.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$DemuraPattern[MenuConfigManager.DemuraPattern.GRAY76.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$DemuraPattern[MenuConfigManager.DemuraPattern.GRAY128.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$DemuraPattern[MenuConfigManager.DemuraPattern.GRAY240.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$DemuraPattern[MenuConfigManager.DemuraPattern.GRAY255.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$DemuraPattern[MenuConfigManager.DemuraPattern.OFF.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[MenuConfigManager.TestPattern_hardware.values().length];
            $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern_hardware = iArr2;
            try {
                iArr2[MenuConfigManager.TestPattern_hardware.RED.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern_hardware[MenuConfigManager.TestPattern_hardware.GREEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern_hardware[MenuConfigManager.TestPattern_hardware.BLUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern_hardware[MenuConfigManager.TestPattern_hardware.WHITE.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern_hardware[MenuConfigManager.TestPattern_hardware.BLACK.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern_hardware[MenuConfigManager.TestPattern_hardware.CYAN.ordinal()] = 6;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern_hardware[MenuConfigManager.TestPattern_hardware.PURPLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern_hardware[MenuConfigManager.TestPattern_hardware.YELLOW.ordinal()] = 8;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern_hardware[MenuConfigManager.TestPattern_hardware.OFF.ordinal()] = 9;
            } catch (NoSuchFieldError unused16) {
            }
            int[] iArr3 = new int[MenuConfigManager.TestPattern.values().length];
            $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern = iArr3;
            try {
                iArr3[MenuConfigManager.TestPattern.RED.ordinal()] = 1;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern[MenuConfigManager.TestPattern.GREEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern[MenuConfigManager.TestPattern.BLUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern[MenuConfigManager.TestPattern.WHITE.ordinal()] = 4;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern[MenuConfigManager.TestPattern.BLACK.ordinal()] = 5;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern[MenuConfigManager.TestPattern.CIRCLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern[MenuConfigManager.TestPattern.OFF.ordinal()] = 7;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    public void refreshFilterChangeTime() {
        this.mDataContainer.loadHisFacData_Function(false);
        setAdapter(this.mDataContainer.getFacDataGroup_Function());
    }

    private void showNextLevelCofigPage() {
        this.mBeforeSelectedPosition = this.mCurrentSelectedPosition;
        this.mExitLevel++;
        this.selIdx = 0;
        if (this.mListViewSelectedItemData.getmSubChildGroup() != null && this.mListViewSelectedItemData.getmSubChildGroup().size() > 0) {
            saveParentPageData(this.mExitLevel);
            setAdapter(this.mListViewSelectedItemData.getmSubChildGroup());
        }
    }

    public static void setInterruptExit(boolean z) {
        mInterruptExit = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startMicTest() {
        Log.d("Fac_MenuMain", "start Mic Detect activity!");
    }

    private void sendMuteBroadcast(boolean z) {
        Log.i("Fac_MenuMain", "sendbroadcast hide mute icon");
        Intent intent = new Intent("com.hisense.intent.HIDE_MUTE_ICON");
        intent.putExtra("hide_mute_icon", z);
        intent.addFlags(268435456);
        sendBroadcast(intent);
    }

    @Override // com.hisense.uiadapter.TVComponenetListener
    public void onDataChanged(String str) {
        Log.i("Fac_MenuMain", "onDataChanged name = " + str);
        if (str != null) {
            if ("FACTORY_MIC".endsWith(str)) {
                this.mDataContainer.refreshMicData();
            } else {
                this.mDataContainer.refreshAudioCurveData(str);
            }
        }
        Message messageObtainMessage = this.mHandler.obtainMessage(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_DETECT);
        messageObtainMessage.obj = str;
        this.mHandler.sendMessage(messageObtainMessage);
    }

    private void setVcomType() {
        if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
            if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_OPTION_VCOM_VOLTAGE")) {
                mMenuConfigManager.setVcomType(1);
            } else if (this.mListViewSelectedItemData.getmItemID().equals("FACTORY_OPTION_VCOM2_VOLTAGE")) {
                mMenuConfigManager.setVcomType(2);
            } else {
                mMenuConfigManager.setVcomType(2);
            }
        }
    }

    private void cleanMenuMainData() {
        Log.i("Fac_MenuMain", "cleanMenuMainData");
        ParentData parentData = this.mFirstLevelData;
        if (parentData != null) {
            if (parentData.mParentGroup != null) {
                this.mFirstLevelData.mParentGroup.clear();
            }
            this.mFirstLevelData = null;
        }
        ParentData parentData2 = this.mSecondLevelData;
        if (parentData2 != null) {
            if (parentData2.mParentGroup != null) {
                this.mSecondLevelData.mParentGroup.clear();
            }
            this.mSecondLevelData = null;
        }
        ParentData parentData3 = this.mThirdLevelData;
        if (parentData3 != null) {
            if (parentData3.mParentGroup != null) {
                this.mThirdLevelData.mParentGroup.clear();
            }
            this.mThirdLevelData = null;
        }
        this.mListViewSelectedItemData = null;
        this.mMyUpData = null;
        List<SetConfigListViewAdapter.DataItem> list = this.mGroup;
        if (list != null) {
            list.clear();
            this.mGroup = null;
        }
        this.mListViewSelectedItemData = null;
        this.mMenuSetUpListView = null;
        this.mHandler = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startScreenProtectActivity() {
        startActivityForResult(new Intent(this, (Class<?>) FacOledScreenProtectActivity.class), 2021);
    }
}
