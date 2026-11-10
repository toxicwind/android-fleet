package com.hisense.uifac;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.hisense.serialInfoTest.model.SerialFacService;
import com.hisense.tvcm.CamDisplayManager;
import com.hisense.tvcm.FacFunction;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.tvcm.FacParamManager;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.tvcm.ImageSurfaceView;
import com.hisense.tvcm.PropertyParseHandler;
import com.hisense.tvs.CESIConstants;
import com.hisense.tvs.FacTvsFunctionUtil;
import com.hisense.uifac.commonview.CustListView;
import com.hisense.uifac.menu.MenuMain;
import com.hisense.uifac.menu.adapter.SetConfigListViewAdapter;
import com.hisense.uifac.menu.commonview.EffectOptionView;
import com.hisense.uifac.menu.commonview.MenuSetUpListView;
import com.hisense.uifac.menu.commonview.RespondedKeyEvent;
import com.hisense.uifac.menu.util.MenuConfigManager;
import com.hisense.uifac.menu.util.MenuDataContainer;
import com.hisense.uifac.mic.HisenseFacMicTest;
import com.hisense.uifac.nav.NavIntegration;
import com.hisense.uifac.nav.adapter.SourceListAdapter;
import com.hisense.uifac.network.adapter.WifiBluetoochCombinedDialog;
import com.hisense.uifac.network.adapter.f7networkdialog;
import com.hisense.uifac.util.BluetoothPaireObserver;
import com.hisense.uifac.util.ChangeSourceUtil;
import com.hisense.uifac.util.DemuraUtil;
import com.hisense.uifac.util.FacCountryUtils;
import com.hisense.uifac.util.FacFileUtils;
import com.hisense.uifac.util.FacLanguageUtils;
import com.hisense.uifac.util.FacLogoUtils;
import com.hisense.uifac.util.IMtkFunCallback;
import com.hisense.uifac.util.IRootMenuUiListener;
import com.hisense.uifac.util.ISourceStatusChangeCallBack;
import com.mediatek.mtkaudiopatchmanager.MtkAudioPatchManager;
import com.mediatek.twoworlds.tv.MtkTvHighLevel;
import com.mediatek.twoworlds.tv.MtkTvInputSource;
import com.mediatek.twoworlds.tv.MtkTvInputSourceBase;
import com.mediatek.twoworlds.tv.MtkTvTVCallbackHandler;
import com.mediatek.twoworlds.tv.MtkTvUtilBase;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import com.mediatek.twoworlds.tv.model.MtkTvRectangle;
import com.regza.persistent.logd.lib.LogSaver;
import com.vt.tvplayer.TvPlayerClient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes.dex */
public class FacRootMenu extends Activity implements View.OnKeyListener, IRootMenuUiListener {
    public static Context facRootMenuContext = null;
    private static boolean isShowFileListInProgress = false;
    private static Bitmap mBitmap = null;
    private static Button mButton_FactoryMenuRoot = null;
    private static FacRootMenu mFacRootMenu = null;
    private static RelativeLayout mFactoryMenuRoot_Layout = null;
    public static boolean mInfoBarOpenFlag = false;
    public static boolean mIsAlreadySet = false;
    private static boolean mIsHaveVcomBitmap = false;
    private static MacKeyWriter mMacKeyWriter;
    private static int mMicDeviceType;
    private static View mModel_Volume_RL;
    private static ImageView mMuteImageView;
    private static NavIntegration mNavIntegration;
    private static ImageSurfaceView mSurfaceView;
    private static MenuConfigManager mcf;
    AlertDialog acasHddDialog;
    View acasHddDialogView;
    String acasHddResult;
    private InputStream bugConfigInputStream;
    private RespondedKeyEvent childView;
    int factoryfailedcode;
    IMtkFunCallback factoryokcallback;
    int factoryokretry;
    InputMethodManager imm;
    private boolean isFactoryokbusy;
    private boolean isShowResultAboutKey;
    AlertDialog keysDialog;
    View keysDialogView;
    TextView keysMessage;
    private int m3DKeyCount;
    private LinearLayout m3DlinearList;
    private final AgingForLaserTvControl mAgingLaserTv;
    private int mAtscF5Index;
    boolean mAttestationOk;
    BluetoothPaireObserver mBluetoothPaireObserver;
    private View mChannelDetailLayout;
    CheckKeyStatusReceiver mCheckKeyStatusReceiver;
    private View mCommonInfoTopRL;
    private DemuraUtil mDemuraUtil;
    private TextView mDetailsInfoBottomTV;
    boolean mDtcpIpOk;
    private StringBuffer mErrMsg;
    private View mFacBGColorView;
    private HisenseFacMicTest mFacMicTest;
    private ProgressBar mFactoryokProgressbar;
    private TextView mFactoryokprogressText;
    boolean mHdcp14Ok;
    boolean mHdcp22Ok;
    HisenseSystemkeyReceiver mHisenseSystemkeyReceiver;
    private ImageView mImageView;
    private boolean mInPicShowMode;
    private TextView mInfoBottomLeftTV;
    private TextView mInfoBottomRightTV;
    private TextView mInfoBottomTV;
    private TextView mInfoNameTV;
    private TextView mInfoNumTV;
    boolean mIsACASSTateOk;
    boolean mIsEMMCOk;
    private boolean mIsLightSensorEnable;
    private boolean mIsMacKeyOK;
    boolean mIsStOk;
    private AlertDialog mKeyAlertDialog;
    private View mLayout_Bottom_One_Colmun_LL;
    private View mLayout_Bottom_Two_Colmun_LL;
    private SetConfigListViewAdapter mListViewAdpter_3DSetting;
    private SetConfigListViewAdapter.DataItem mListViewSelectedItemData;
    boolean mMarlinOk;
    public MenuSetUpListView mMenu3DSettingListView;
    private MenuDataContainer mMenuDataContainer;
    private String mMicTestLog;
    private View mModel_Factoryok_progress;
    private View mModel_InfoBar_RL;
    private View mModel_ShortTip_LL;
    private TextView mModel_ShortTip_TV;
    private TextView mModel_ShortTip_Title;
    private View mModel_SourceList_RL;
    private MtkAudioPatchManager mMtkAudioPatchManager;
    private MtkTvHighLevel mMtkTvHighLevel;
    private MtkTvRectangle mMtkTvRectangle;
    private MtkTvUtilBase mMtkTvUtilBase;
    boolean mNetflixOk;
    boolean mPVROk;
    private ProgressBar mPbShow;
    boolean mPlayReadyOk;
    FacPropertyDemo.SourceEnum mPrevSourceEnum;
    private boolean mPrevWifiStatus;
    boolean mQDAOk;
    private TextView mSALogoName;
    private SharedPreferences mSharedPreferences;
    ShutDownReceiver mShutDownReceiver;
    private View mSimpleInfoTopRL;
    private final View.OnKeyListener mSourceItemKeyLsner;
    private SourceListAdapter mSourceListAdapter;
    private CustListView mSourceListView;
    private Drawable mSourceSelectedIcon;
    private Drawable mSourceUnSelectedIcon;
    private final CustListView.UpDateListView mSoureListUpDate;
    SystemkeyReceiver mSystemkeyReceiver;
    private Animation mTipEnterAnimation;
    private Toast mToastView;
    private SurfaceView mTvSurfaceView;
    private int mTvsF5Index;
    boolean mWideVineOk;
    private String mZDMicTestLog;
    private final Handler mhHandler;
    private boolean savingKeyIsPressed;
    private int selIdx;
    private String selectFangChuanHuoString;
    private short selectNum;
    private WindowManager wm;
    private WindowManager.LayoutParams wmParams;
    private static final int TVS_LASR_SOURCE_INPUT = CESIConstants.getValue("SourceType_e", "INPUT");
    private static int mcfNullCounter = 0;
    private static boolean is3DPressed = false;
    public static boolean mIsVcomOK = false;
    private final Intent exit_factory_intent = new Intent("com.hisense.broadcom.EXIT_FACTORY_MODE");
    private StringBuffer inputChannelNumStrBuffer = new StringBuffer();
    private StringBuffer inputFangChuanHuoNumberStrBuffer = new StringBuffer();
    private boolean inputNuming = false;
    private int mCurrentSelectedPosition = 0;
    private List<String> mSourceList = new ArrayList();
    private String videoFileDirctory = "";
    private String videoFileName = "";
    private String VIDEO_NAME_TO_PLAY = "VIDEO_TEST";
    private boolean isLongPressedDmp = false;
    private int LONG_PRESS_TIME = 600;
    private boolean isDmpPressedMessageSent = false;
    private boolean is3DPressedMsgSent = false;
    private boolean isLongPressed3D = false;
    private int mCurrentTwelveVoltsStatus = 1;
    private boolean bprepareexitaging = false;
    private int viewMode = 2;
    private int childType = -1;
    private Surface mSurface = null;
    private TvPlayerClient client = null;
    private ThreeDGlassesPairedReceiver mThreeDGlassesPairedReceiver = null;
    private ProgressDialog progressDialog = null;
    private ProgressDialog blueTooth3DGlassesProgressDialog = null;
    private ProgressDialog mToastProgressDialog = null;
    private boolean mAudioManagerState = false;
    private boolean mTvStoped = false;
    private final int[] mSecretKeys = {0, 0, 0, 0};
    private final int[] mAgingExitKeys = {0, 0, 0, 0};
    private final int[] mAgingExitKeysNew = {0, 0, 0, 0, 0};
    private final int[] mAgingExitKeys2 = {0, 0, 0, 0, 0};
    private final int[] mLaunchBugReportKeys = {0, 0, 0, 0, 0, 0};
    private final int[] mOnKeyDownSecretKeys = {0, 0, 0, 0, 0, 0};
    private SvctxNotifyCallbackHandler mSvctxNotifyCallbackHandler = null;
    private int mLocalDimmingValue = 0;
    private FangChuanHuoStatus mFangChuanHuo_Flag = FangChuanHuoStatus.FANGCHUANHUO_IDLE;
    private int FANGCHUANHUO_NUMBER_DISPLAY_COUNT = 15;
    private final Intent cancelMuteIntent = new Intent("com.hisense.action.CANCEL_MUTE");
    private int mCurrentValue = 0;
    private int keyRC = 0;
    private boolean serviceModeStatus = false;
    private int serviceModeMicCheck = 0;
    private boolean bLogoKeyPressed = false;
    private boolean mIsMKeyPressed = false;
    private boolean f3KeyIsPressed = false;
    private boolean f4KeyIsPressed = false;
    private boolean mIsKey1Pressed = false;
    private boolean mIsKey2Pressed = false;
    private boolean mIsKeyPannelTestPressed = false;
    private FacFunction mFacFunction_Adapter = null;
    private boolean mIsFirstShowM = false;
    private String mFirstPowerTag = null;
    private boolean mIsStartFromMenu = false;

    /* JADX INFO: renamed from: com.hisense.uifac.FacRootMenu$1, reason: invalid class name */
    class AnonymousClass1 implements View.OnKeyListener {
    }

    private enum FangChuanHuoStatus {
        FANGCHUANHUO_IDLE,
        FANGCHUANHUO_START,
        FANGCHUANHUO_Z,
        FANGCHUANHUO_INPUTNUM,
        FANGCHUANHUO_END,
        FANGCHUANHUO_FAIL
    }

    private enum SavingMode {
        BRIGHT,
        SOFT
    }

    private class ThreeDGlassesPairedReceiver extends BroadcastReceiver {
    }

    public interface WriteCallback {
        void writeEnd(boolean z, String str);
    }

    public FacRootMenu() {
        FacPropertyDemo.SourceEnum sourceEnum = FacPropertyDemo.SourceEnum.ATV;
        this.mSharedPreferences = null;
        this.mInPicShowMode = false;
        this.mIsMacKeyOK = false;
        this.savingKeyIsPressed = false;
        this.mIsLightSensorEnable = false;
        this.mToastView = null;
        this.mErrMsg = new StringBuffer();
        this.mWideVineOk = false;
        this.mPlayReadyOk = false;
        this.mHdcp14Ok = false;
        this.mHdcp22Ok = false;
        this.mNetflixOk = false;
        this.mAttestationOk = false;
        this.mMarlinOk = false;
        this.mDtcpIpOk = false;
        this.mPVROk = false;
        this.mQDAOk = false;
        this.mPrevWifiStatus = false;
        this.isShowResultAboutKey = false;
        this.mIsACASSTateOk = true;
        this.mIsEMMCOk = true;
        this.mIsStOk = false;
        this.mFacMicTest = null;
        this.mMicTestLog = "NG";
        this.mZDMicTestLog = "NG";
        this.mDemuraUtil = null;
        this.mKeyAlertDialog = null;
        this.m3DKeyCount = 0;
        this.mTvsF5Index = 0;
        this.mAtscF5Index = 0;
        this.acasHddResult = " ";
        this.mAgingLaserTv = new AgingForLaserTvControl();
        this.mSoureListUpDate = new CustListView.UpDateListView() { // from class: com.hisense.uifac.FacRootMenu.2
            @Override // com.hisense.uifac.commonview.CustListView.UpDateListView
            public void updata() {
                FacRootMenu.this.setSourceListData();
            }
        };
        this.mSourceItemKeyLsner = new AnonymousClass3();
        this.mhHandler = new Handler() { // from class: com.hisense.uifac.FacRootMenu.4
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (!Thread.currentThread().isInterrupted()) {
                    Log.i("Fac_FacRootMenu", "***msg=" + message.what);
                    int i = message.what;
                    if (i == 0) {
                        FacRootMenu.this.hideAll();
                        FacRootMenu.this.showBigMMode();
                    } else if (i == 103) {
                        FacRootMenu.this.showBigMMode();
                    } else if (i == 133) {
                        FacRootMenu.this.displayHisToast((String) message.obj);
                    } else if (i == 2) {
                        FacRootMenu.this.mModel_ShortTip_TV.setVisibility(8);
                        if (FacRootMenu.mcf.getDefault("HIFAC_STATUS") != 0) {
                            FacRootMenu.this.showBigMMode();
                        } else {
                            FacRootMenu.this.finish();
                        }
                    } else if (i == 3) {
                        FacRootMenu.mModel_Volume_RL.setVisibility(8);
                        if (FacRootMenu.mcf.getDefault("HIFAC_STATUS") != 0) {
                            FacRootMenu.this.showBigMMode();
                        } else {
                            FacRootMenu.this.finish();
                        }
                    } else if (i == 4) {
                        FacRootMenu.mInfoBarOpenFlag = false;
                        if (FacRootMenu.mcf.getDefault("HIFAC_STATUS") != 0) {
                            FacRootMenu.this.showBigMMode();
                        } else {
                            FacRootMenu.this.finish();
                        }
                    } else if (i == 5) {
                        FacRootMenu.this.mModel_ShortTip_LL.setVisibility(8);
                        Log.i("Fac_FacRootMenu", "savingKeyFlag = " + message.arg1);
                        if (message.arg1 == 1) {
                            FacRootMenu.this.savingKeyIsPressed = false;
                            FacRootMenu.this.inputNuming = false;
                            FacRootMenu.this.inputChannelNumStrBuffer = new StringBuffer();
                            Log.i("Fac_FacRootMenu", "to preset channel, ignore the number key");
                        } else {
                            if (19 != FacRootMenu.this.selectNum || !FacPropertyDemo.HIFAC_MARKET_SA) {
                                FacRootMenu.mNavIntegration.changeChannel(FacRootMenu.this.selectNum);
                            } else {
                                Log.i("Fac_FacRootMenu", "user input channel numbechangeChannelForIdr is 19");
                                FacRootMenu.this.mFacFunction_Adapter.changeChannelForId(5, 1);
                            }
                            try {
                                Thread.sleep(1500L);
                            } catch (Exception unused) {
                            }
                            FacRootMenu.this.showBasicInfoBar();
                            FacRootMenu.this.inputNuming = false;
                            FacRootMenu.this.inputChannelNumStrBuffer = new StringBuffer();
                        }
                    } else if (i == 6) {
                        FacRootMenu.this.showOKTipOnGreenScreen();
                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else if (i == 117) {
                        FacRootMenu facRootMenu = FacRootMenu.this;
                        facRootMenu.blueTooth3DGlassesProgressDialog = facRootMenu.get3DGlassesPairProgressDialog();
                        FacRootMenu.this.blueTooth3DGlassesProgressDialog.show();
                    } else if (i == 118) {
                        FacRootMenu.this.blueTooth3DGlassesProgressDialog.dismiss();
                        FacRootMenu.this.showBigMMode();
                    } else if (i != 142) {
                        if (i != 143) {
                            switch (i) {
                                case 8:
                                    FacRootMenu.this.mFangChuanHuo_Flag = FangChuanHuoStatus.FANGCHUANHUO_IDLE;
                                    break;
                                case 9:
                                    FacRootMenu.this.mFangChuanHuo_Flag = FangChuanHuoStatus.FANGCHUANHUO_FAIL;
                                    Log.i("Fac_FacRootMenu", "input string=" + FacRootMenu.this.selectFangChuanHuoString + " len=" + FacRootMenu.this.selectFangChuanHuoString.length());
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("fangchuanhuo code=");
                                    sb.append(FacRootMenu.this.selectFangChuanHuoString);
                                    Log.i("Fac_FacRootMenu", sb.toString());
                                    FacRootMenu.this.mModel_ShortTip_Title.setText(R.string.str_factorymenu_fail);
                                    FacRootMenu.this.showTipMode(false);
                                    FacRootMenu.this.canncelMessage(2);
                                    FacRootMenu.this.childType = 23;
                                    FacRootMenu facRootMenu2 = FacRootMenu.this;
                                    facRootMenu2.mSetShortTipData(facRootMenu2.childType);
                                    break;
                                case 10:
                                    FacRootMenu.this.mFangChuanHuo_Flag = FangChuanHuoStatus.FANGCHUANHUO_IDLE;
                                    break;
                                case 11:
                                    FacRootMenu.this.bLogoKeyPressed = false;
                                    break;
                                case 12:
                                    int i2 = message.arg1;
                                    if (i2 >= 0 && i2 <= 100) {
                                        FacRootMenu.this.mModel_Factoryok_progress.setVisibility(0);
                                        FacRootMenu.this.mFactoryokProgressbar.setProgress(message.arg1);
                                        FacRootMenu.this.mFactoryokprogressText.setText(message.arg1 + "%");
                                    }
                                    break;
                                case 13:
                                    FacRootMenu.this.mModel_ShortTip_TV.setTextColor(-65536);
                                    FacRootMenu.this.mModel_ShortTip_TV.setText("Failed");
                                    FacRootMenu.this.mModel_ShortTip_TV.setBackgroundColor(0);
                                    FacRootMenu.this.mModel_ShortTip_TV.setBackground(null);
                                    FacRootMenu.mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.drawable_transparent);
                                    FacRootMenu.this.mFacBGColorView.setBackgroundResource(R.drawable.drawable_transparent);
                                    FacRootMenu.this.mModel_ShortTip_TV.startAnimation(FacRootMenu.this.mTipEnterAnimation);
                                    FacRootMenu.this.mhHandler.removeMessages(4);
                                    break;
                                case 14:
                                    if (!FacRootMenu.this.isLongPressedDmp) {
                                        FacRootMenu.this.showFileList();
                                    } else {
                                        new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.4.1
                                            @Override // java.lang.Runnable
                                            public void run() {
                                                if (!FacRootMenu.this.doesVideoFileExistInUsb(false)) {
                                                    FacRootMenu.this.mhHandler.sendEmptyMessage(114);
                                                    return;
                                                }
                                                if (FacRootMenu.this.mSharedPreferences == null) {
                                                    FacRootMenu.this.initSharedPreference();
                                                }
                                                FacRootMenu.this.mSharedPreferences.edit().putBoolean(FacRootMenu.this.getResources().getString(R.string.str_test_info_dmp_play), true).commit();
                                                FacRootMenu facRootMenu3 = FacRootMenu.this;
                                                facRootMenu3.startInnerVideoPlayer(facRootMenu3.videoFileDirctory, FacRootMenu.this.videoFileName);
                                            }
                                        }).start();
                                    }
                                    break;
                                case 15:
                                    if (!FacRootMenu.this.isLongPressed3D) {
                                        Log.i("Fac_FacRootMenu", "3D button is short pressed!");
                                        FacRootMenu.this.is3DPressedMsgSent = false;
                                    } else {
                                        Log.i("Fac_FacRootMenu", "3D button is long pressed!");
                                        FacRootMenu.this.isLongPressed3D = false;
                                        FacRootMenu.this.is3DPressedMsgSent = false;
                                        if (FacPropertyDemo.HIFAC_MARKET_SA) {
                                            FacRootMenu.this.mFacFunction_Adapter.setVolume(FacRootMenu.this.getVolumeAfterChangeForSA());
                                        }
                                    }
                                    break;
                                case 16:
                                    if (message.arg1 == 1) {
                                        FacRootMenu.this.updateInfoNameTV(true);
                                    } else {
                                        FacRootMenu.this.updateInfoNameTV(false);
                                    }
                                    break;
                                default:
                                    switch (i) {
                                        case 18:
                                            FacRootMenu.this.mIsMKeyPressed = false;
                                            break;
                                        case 19:
                                            FacRootMenu.this.hideBigMIcon();
                                            break;
                                        case 20:
                                            FacRootMenu.this.setPatternAlreaySet();
                                            break;
                                        case 21:
                                            FacRootMenu.this.setKeysDialogMessage();
                                            break;
                                        case 22:
                                            Log.i("Fac_FacRootMenu", "delay 400ms to init factory curren source");
                                            if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacRootMenu.this.mPrevSourceEnum)) {
                                                int tilValue = FacRootMenu.this.mPrevSourceEnum.getTilValue();
                                                Log.i("Fac_FacRootMenu", "mtkSourceIndex is" + tilValue);
                                                int inputSourceTotalNumber = MtkTvInputSource.getInstance().getInputSourceTotalNumber();
                                                Log.i("Fac_FacRootMenu", "mtkSourceIndex total num" + inputSourceTotalNumber);
                                                if (tilValue >= 0 && tilValue < inputSourceTotalNumber - 1) {
                                                    try {
                                                        Thread.sleep(50L);
                                                    } catch (InterruptedException e2) {
                                                        e2.printStackTrace();
                                                    }
                                                    FacRootMenu.this.mFacFunction_Adapter.switchSource(FacRootMenu.this.mPrevSourceEnum);
                                                } else {
                                                    try {
                                                        Thread.sleep(50L);
                                                    } catch (InterruptedException e3) {
                                                        e3.printStackTrace();
                                                    }
                                                    FacRootMenu.this.mFacFunction_Adapter.switchSource(FacRootMenu.this.mPrevSourceEnum);
                                                }
                                                break;
                                            } else {
                                                try {
                                                    Thread.sleep(50L);
                                                } catch (InterruptedException e4) {
                                                    e4.printStackTrace();
                                                }
                                                if (FacRootMenu.mcf.isHaveTuner()) {
                                                    FacRootMenu.mcf.switchSource(FacPropertyDemo.SourceEnum.TV);
                                                } else {
                                                    FacRootMenu.mcf.switchSource(FacPropertyDemo.SourceEnum.HDMI1);
                                                }
                                                break;
                                            }
                                            FacRootMenu.mcf.setOttSrc(7);
                                            FacRootMenu.mcf.setMtkSourceStatus(true);
                                            Log.i("Fac_FacRootMenu", "TV started");
                                            FacRootMenu.this.mTvStoped = false;
                                        case 23:
                                            if (FacRootMenu.this.mMtkTvUtilBase == null) {
                                                FacRootMenu.this.mMtkTvUtilBase = new MtkTvUtilBase();
                                            }
                                            FacRootMenu.this.mMtkTvRectangle = new MtkTvRectangle(0.0f, 0.0f, 1.0f, 1.0f);
                                            Log.i("Fac_FacRootMenu", "setScreenOutputDispRect 0,0,1,1");
                                            FacRootMenu.this.mMtkTvUtilBase.setScreenOutputDispRect("main", FacRootMenu.this.mMtkTvRectangle);
                                            break;
                                        default:
                                            switch (i) {
                                                case 25:
                                                    FacRootMenu.this.setChannelInfo();
                                                    break;
                                                case 26:
                                                    FacRootMenu.this.mInfoNameTV.setText(FacRootMenu.mNavIntegration.getCurrentSourceVideoInfo());
                                                    break;
                                                case 27:
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                                                    builder.setTitle(R.string.str_facmenu_hisense_bug_report_success);
                                                    builder.setCancelable(true);
                                                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.FacRootMenu.4.2
                                                        @Override // android.content.DialogInterface.OnClickListener
                                                        public void onClick(DialogInterface dialogInterface, int i3) {
                                                        }
                                                    });
                                                    AlertDialog alertDialogCreate = builder.create();
                                                    alertDialogCreate.show();
                                                    alertDialogCreate.getButton(-2).setFocusable(true);
                                                    alertDialogCreate.getButton(-2).setFocusableInTouchMode(true);
                                                    alertDialogCreate.getButton(-2).requestFocus();
                                                    break;
                                                case 28:
                                                    FacRootMenu.this.updateAgingTime();
                                                    break;
                                                case 29:
                                                    FacRootMenu.this.f4KeyIsPressed = false;
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 31:
                                                            FacRootMenu.mNavIntegration.channelNext();
                                                            FacRootMenu.this.showSimpleInfoBar();
                                                            break;
                                                        case 32:
                                                            FacRootMenu.mNavIntegration.channelPre();
                                                            FacRootMenu.this.showSimpleInfoBar();
                                                            break;
                                                        case 33:
                                                            FacRootMenu.this.f3KeyIsPressed = false;
                                                            break;
                                                        case 34:
                                                            FacRootMenu.this.mIsKey1Pressed = false;
                                                            break;
                                                        case 35:
                                                            FacRootMenu.this.mIsKey2Pressed = false;
                                                            break;
                                                        case 36:
                                                            Log.i("Fac_FacRootMenu", "****F3_IS_PRESSED  mIsKey1Pressed = " + FacRootMenu.this.mIsKey1Pressed);
                                                            Log.i("Fac_FacRootMenu", "****F3_IS_PRESSED  mIsKey2Pressed = " + FacRootMenu.this.mIsKey2Pressed);
                                                            Log.i("Fac_FacRootMenu", "****F3_IS_PRESSED  mIsKeyPannelTestPressed = " + FacRootMenu.this.mIsKeyPannelTestPressed);
                                                            if (FacPropertyDemo.HIFAC_IS_LASER4K) {
                                                                if (FacRootMenu.this.mIsKey1Pressed) {
                                                                    FacRootMenu.this.mFacFunction_Adapter.setLaserSlidingCoverMode(0);
                                                                } else if (FacRootMenu.this.mIsKey2Pressed) {
                                                                    FacRootMenu.this.mFacFunction_Adapter.setLaserSlidingCoverMode(1);
                                                                } else if (!FacRootMenu.this.mIsKeyPannelTestPressed) {
                                                                    FacRootMenu.this.hideBigMIcon();
                                                                    FacRootMenu.this.startActivity(new Intent(FacRootMenu.this, (Class<?>) LuminanceTestActivity.class));
                                                                } else {
                                                                    FacRootMenu.this.startActivity(new Intent(FacRootMenu.this, (Class<?>) GapAdjustActivity.class));
                                                                }
                                                            } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.US) || FacPropertyDemo.HIFAC_MARKET_SA) {
                                                                FacRootMenu.mcf.adjustLocalDimmingStatus();
                                                                if (FacRootMenu.mcf.mLocalDimmingStatus.ordinal() != 0) {
                                                                    FacRootMenu.mFactoryMenuRoot_Layout.setBackgroundResource(R.color.white);
                                                                    FacRootMenu.this.mFacBGColorView.setBackgroundResource(R.color.white);
                                                                } else if (!FacPropertyDemo.HIFAC_MIC_SUPPORT || FacRootMenu.mcf.isZDMicTestOK()) {
                                                                    FacRootMenu.mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                                                                    FacRootMenu.this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                                                                } else {
                                                                    FacRootMenu.this.mhHandler.sendEmptyMessage(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_ON);
                                                                }
                                                                FacRootMenu.this.mFacFunction_Adapter.setLocalDimmingCheck(FacRootMenu.mcf.mLocalDimmingStatus.ordinal());
                                                            }
                                                            FacRootMenu.this.f3KeyIsPressed = false;
                                                            break;
                                                        case 37:
                                                            FacRootMenu.this.mIsKeyPannelTestPressed = false;
                                                            break;
                                                        default:
                                                            switch (i) {
                                                                case 111:
                                                                    FacRootMenu facRootMenu3 = FacRootMenu.this;
                                                                    facRootMenu3.progressDialog = facRootMenu3.getProgressDialog();
                                                                    if (FacRootMenu.this.progressDialog == null || !FacRootMenu.this.progressDialog.isShowing()) {
                                                                        FacRootMenu.this.progressDialog.show();
                                                                    }
                                                                    break;
                                                                case 112:
                                                                    FacRootMenu.this.progressDialog.dismiss();
                                                                    FacRootMenu.this.mModel_ShortTip_Title.setText("IP addr.: " + FacRootMenu.mcf.getIPAddr());
                                                                    FacRootMenu.this.mModel_ShortTip_Title.setGravity(19);
                                                                    FacRootMenu.this.mModel_ShortTip_TV.setGravity(19);
                                                                    FacRootMenu.this.mModel_ShortTip_TV.setTextSize((FacRootMenu.this.getResources().getDimension(R.dimen.nav_textsize_normal) * 2.0f) / 3.0f);
                                                                    FacRootMenu.this.mModel_ShortTip_TV.setText("MAC addr.: " + FacRootMenu.mcf.getMacAddress());
                                                                    FacRootMenu.this.showTipMode(false);
                                                                    Log.i("Fac_FacRootMenu", "eth mac=" + FacRootMenu.mcf.getMacAddress());
                                                                    break;
                                                                case 113:
                                                                    if (FacRootMenu.this.mAudioManagerState) {
                                                                        int unused2 = FacRootMenu.mMicDeviceType = FacRootMenu.mcf.getMicDevice();
                                                                        Log.d("Fac_FacRootMenu", "before getting in factory getMicDevice = " + FacRootMenu.mMicDeviceType);
                                                                        if (FacPropertyDemo.MicDevice.LOCALHOST.ordinal() != FacRootMenu.mMicDeviceType) {
                                                                            Log.i("Fac_FacRootMenu", "setMicDevice to LOCALHOST");
                                                                            FacRootMenu.mcf.setMicDevice(FacPropertyDemo.MicDevice.LOCALHOST.ordinal());
                                                                        }
                                                                    }
                                                                    removeMessages(113);
                                                                    break;
                                                                case 114:
                                                                    FacRootMenu.this.VIDEO_NAME_TO_PLAY = FacParamManager.FAC_DMP_FILE_NAME;
                                                                    if (FacRootMenu.this.VIDEO_NAME_TO_PLAY.equals("4K_VIDEO_TEST")) {
                                                                        FacRootMenu.mcf.displayHisToast(FacRootMenu.this.getResources().getString(R.string.str_4k_video_not_exit));
                                                                    } else if (FacRootMenu.this.VIDEO_NAME_TO_PLAY.equals("8K_VIDEO_TEST")) {
                                                                        FacRootMenu.mcf.displayHisToast(FacRootMenu.this.getResources().getString(R.string.str_8k_video_not_exit));
                                                                    } else {
                                                                        FacRootMenu.mcf.displayHisToast(FacRootMenu.this.getResources().getString(R.string.str_video_not_exit));
                                                                    }
                                                                    break;
                                                                default:
                                                                    switch (i) {
                                                                        case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_NOT_DETECT /* 136 */:
                                                                            Log.i("Fac_FacRootMenu", "msg init end");
                                                                            if (FacRootMenu.this.progressDialog != null) {
                                                                                Log.i("Fac_FacRootMenu", "msg init end  1");
                                                                                FacRootMenu.this.progressDialog.dismiss();
                                                                                FacRootMenu.this.progressDialog = null;
                                                                            }
                                                                            if (FacPropertyDemo.HIFAC_MARKET_JP && FacRootMenu.this.serviceModeMicCheck == 1) {
                                                                                Log.i("Fac_FacRootMenu", "startTestMic");
                                                                                FacRootMenu.this.startTestMic(false);
                                                                                Log.i("Fac_FacRootMenu", "endTestMic");
                                                                            }
                                                                            FacRootMenu.this.showBigMMode();
                                                                            break;
                                                                        case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_DETECT /* 137 */:
                                                                            FacRootMenu.this.getWarnProgressDialog((String) message.obj).show();
                                                                            break;
                                                                        case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_NOT_DETECT /* 138 */:
                                                                            if (!FacRootMenu.mcf.isZDMicTestOK()) {
                                                                                FacRootMenu.this.enterFactoryAging();
                                                                            } else {
                                                                                FacRootMenu.this.enterFactoryAgingNotHaveMic();
                                                                            }
                                                                            break;
                                                                        case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_ON /* 139 */:
                                                                            if (FacPropertyDemo.HIFAC_MIC_SUPPORT) {
                                                                                new Color();
                                                                                if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                                                                                    FacRootMenu.mFactoryMenuRoot_Layout.setBackgroundColor(Color.rgb(102, 102, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_OFF));
                                                                                    FacRootMenu.this.mFacBGColorView.setBackgroundColor(Color.rgb(102, 102, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_OFF));
                                                                                } else {
                                                                                    FacRootMenu.mFactoryMenuRoot_Layout.setBackgroundColor(Color.rgb(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR, 28, 230));
                                                                                    FacRootMenu.this.mFacBGColorView.setBackgroundColor(Color.rgb(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR, 28, 230));
                                                                                }
                                                                            }
                                                                            break;
                                                                        case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_OFF /* 140 */:
                                                                            FacRootMenu.mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                                                                            FacRootMenu.this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                                                                            break;
                                                                    }
                                                                    break;
                                                            }
                                                            break;
                                                    }
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                        } else {
                            TextView textView = (TextView) FacRootMenu.this.acasHddDialogView.findViewById(R.id.acas_hdd_dialog);
                            new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.4.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    FacRootMenu.this.acasHddResult = FacRootMenu.mcf.getF1Result();
                                }
                            }).start();
                            Log.i("Fac_FacRootMenu", "showTvsF1ResultDialog---------result = " + FacRootMenu.this.acasHddResult);
                            textView.setText(FacRootMenu.this.acasHddResult);
                            if (FacRootMenu.this.acasHddDialog.isShowing()) {
                                FacRootMenu.this.mhHandler.sendEmptyMessageDelayed(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_ON, 1500L);
                            } else {
                                FacRootMenu.this.mhHandler.removeMessages(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_ON);
                            }
                        }
                    } else if (!SystemProperties.get("sys.vt.factory.do_ok", "0").equalsIgnoreCase("1")) {
                        Log.i("Fac_FacRootMenu", "startScreenProtectActivity... ");
                        FacRootMenu.this.startScreenProtectActivity();
                    }
                }
                super.handleMessage(message);
            }
        };
        this.factoryokretry = 0;
        this.isFactoryokbusy = false;
        this.factoryfailedcode = -1;
        this.factoryokcallback = new IMtkFunCallback() { // from class: com.hisense.uifac.FacRootMenu.5
            @Override // com.hisense.uifac.util.IMtkFunCallback
            public void notifyProgress(int i) {
                if (i <= 100 && i >= 0) {
                    FacRootMenu.this.isFactoryokbusy = true;
                    Log.i("Fac_FacRootMenu", "factoryOk progress = " + i);
                } else if (i == 101) {
                    FacRootMenu.this.isFactoryokbusy = false;
                    FacRootMenu.this.sendMessageDelayed(6, 2000);
                    FacRootMenu.this.sendMessageDelayed(7, 3000);
                    FacRootMenu.this.getSharedPreferences("FactoryOk_failed", 0).edit().remove("FactoryOk_failed").commit();
                } else {
                    FacRootMenu.this.isFactoryokbusy = false;
                    FacRootMenu facRootMenu = FacRootMenu.this;
                    if (facRootMenu.factoryokretry < 0) {
                        Log.e("Fac_FacRootMenu", "Factory ok failed retry " + FacRootMenu.this.factoryokretry);
                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        FacRootMenu.this.factoryOk();
                        FacRootMenu.this.factoryokretry++;
                    } else {
                        facRootMenu.factoryokretry = 0;
                        facRootMenu.sendMessageDelayed(13, 3000);
                        FacRootMenu.this.getSharedPreferences("FactoryOk_failed", 0).edit().putInt("FactoryOk_failed", FacRootMenu.this.factoryfailedcode).commit();
                        try {
                            Runtime.getRuntime().exec("sync");
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                FacRootMenu.this.factoryfailedcode = i;
            }
        };
    }

    public class ShutDownReceiver extends BroadcastReceiver {
        public ShutDownReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.i("Fac_FacRootMenu", intent.getAction());
            if (intent.getAction().equals("com.hisense.action.POWER_OFF")) {
                if (SystemProperties.get("sys.factory.do_clear", "0").equals("0") && !FacRootMenu.mcf.isShutdownBroadcastFromFactory()) {
                    if (FacRootMenu.mcf.getDefault("HIFAC_AGING") != 1) {
                        FacRootMenu.mcf.setValue("HIFAC_STATUS", 0);
                        FacRootMenu.this.getWindow().clearFlags(128);
                        FacRootMenu.this.mFacFunction_Adapter.resetDBX();
                        FacRootMenu.this.mFacFunction_Adapter.setLedControl(3);
                        if (FacRootMenu.this.mMtkAudioPatchManager != null) {
                            Log.i("Fac_FacRootMenu", "onReceive releaseAudioPatch");
                            FacRootMenu.this.mMtkAudioPatchManager.releaseAudioPatch();
                            FacRootMenu.this.mMtkAudioPatchManager = null;
                        } else {
                            Log.i("Fac_FacRootMenu", "onReceive MtkAudioPatchManager is null");
                        }
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.i("Fac_FacRootMenu", "Exit factory menu by soft shut down");
                        Log.i("Fac_FacRootMenu", "exit factory to send broadcast = " + FacRootMenu.this.exit_factory_intent.getAction());
                        FacRootMenu facRootMenu = FacRootMenu.this;
                        facRootMenu.sendBroadcast(facRootMenu.exit_factory_intent);
                        FacRootMenu.this.finish();
                        return;
                    }
                    return;
                }
                Log.i("Fac_FacRootMenu", "Exit factory menu by soft shut down after doUnprotectedClear");
                Log.i("Fac_FacRootMenu", "exit factory with factory status" + FacRootMenu.mcf.getDefault("HIFAC_STATUS"));
            }
        }
    }

    public class SystemkeyReceiver extends BroadcastReceiver {
        public SystemkeyReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.i("Fac_FacRootMenu", intent.getAction());
            if (intent.hasExtra("android.intent.extra.KEY_EVENT")) {
                int intExtra = intent.getIntExtra("android.intent.extra.KEY_EVENT", 0);
                if (intent.getAction() == null || !intent.getAction().equals("tv.policy.SYSTEM_KEY")) {
                    return;
                }
                if (intExtra == 24 || intExtra == 25 || intExtra == 164) {
                    if (FacRootMenu.this.mModel_ShortTip_TV.getVisibility() == 0 && FacRootMenu.this.childType == 20) {
                        return;
                    }
                    if (FacRootMenu.mcf.getDefault("HIFAC_AGING") != 1) {
                        if (intExtra == 164) {
                            FacRootMenu.this.manualWriteProjectId();
                            return;
                        }
                        return;
                    } else {
                        abortBroadcast();
                        Log.i("Fac_FacRootMenu", "cancel systemkey " + intExtra);
                        return;
                    }
                }
                abortBroadcast();
            }
        }
    }

    class SvctxNotifyCallbackHandler extends MtkTvTVCallbackHandler {
        SvctxNotifyCallbackHandler() {
        }

        @Override // com.mediatek.twoworlds.tv.MtkTvTVCallbackHandler
        public int notifySvctxNotificationCode(int i) throws RemoteException {
            Log.i("Fac_FacRootMenu", "notifySvctxNotificationCode");
            if (i == 4 || i == 37) {
                Log.i("Fac_FacRootMenu", "code is 4 or 37");
                FacRootMenu.this.mhHandler.sendEmptyMessageDelayed(23, 300L);
                FacRootMenu.this.mhHandler.sendEmptyMessage(25);
                FacRootMenu.this.sendMessageDelayed(2000, 0);
            }
            return 0;
        }
    }

    private class CheckKeyStatusReceiver extends BroadcastReceiver {
        private CheckKeyStatusReceiver() {
        }

        /* synthetic */ CheckKeyStatusReceiver(FacRootMenu facRootMenu, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == null || !intent.getAction().equals("com.hisense.broadcom.CHECK_KEY_STATUS")) {
                return;
            }
            Log.i("Fac_FacRootMenu", "broadcast check key status");
            FacRootMenu.this.setKeysDialogMessage();
        }
    }

    private class HisenseSystemkeyReceiver extends BroadcastReceiver {
        private HisenseSystemkeyReceiver() {
        }

        /* synthetic */ HisenseSystemkeyReceiver(FacRootMenu facRootMenu, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.i("Fac_FacRootMenu", "intent.getAction()=" + intent.getAction());
            if (intent.getAction() == null || !intent.getAction().equals("com.jamdeo.tv.policy.SYSTEM_KEY")) {
                return;
            }
            int intExtra = intent.getIntExtra("android.intent.extra.KEY_EVENT", 0);
            if (intExtra == 4223 || intExtra == 4298 || intExtra == 4299) {
                if (!(FacRootMenu.this.mModel_ShortTip_TV.getVisibility() == 0 && FacRootMenu.this.childType == 20) && FacRootMenu.this.mIsMacKeyOK) {
                    if (FacRootMenu.mButton_FactoryMenuRoot.getVisibility() == 0 && FacRootMenu.this.hasWindowFocus() && intent.getBooleanExtra("aging_mode_enable", false)) {
                        if (FacRootMenu.mcf.getDefault("HIFAC_AGING") != 1) {
                            FacRootMenu.this.enterFactoryAging();
                        }
                    } else {
                        if (intent.getBooleanExtra("aging_mode_enable", false) || FacRootMenu.mcf.getDefault("HIFAC_AGING") != 1) {
                            return;
                        }
                        FacRootMenu.this.factoryAging(false);
                        FacRootMenu.this.showBigMMode();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSerialService() {
        Log.i("Fac_FacRootMenu", "will start serial service");
        Intent intent = new Intent(this, (Class<?>) SerialFacService.class);
        intent.setFlags(268435456);
        startService(intent);
    }

    public static void switchMute() {
        mModel_Volume_RL.setVisibility(8);
        if (MenuConfigManager.isAudioMute()) {
            MenuConfigManager.setAudioMute(false);
            mMuteImageView.setVisibility(8);
        } else {
            MenuConfigManager.setAudioMute(true);
            mMuteImageView.setVisibility(0);
        }
    }

    /* JADX INFO: renamed from: com.hisense.uifac.FacRootMenu$3, reason: invalid class name */
    class AnonymousClass3 implements View.OnKeyListener {
        AnonymousClass3() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            int selectedItemPosition = FacRootMenu.this.mSourceListView.getSelectedItemPosition();
            if (keyEvent.getAction() == 0 && ((i == 23 || i == 66) && selectedItemPosition != FacRootMenu.this.mSourceList.indexOf(FacRootMenu.mNavIntegration.getCurrentInputSource()))) {
                FacRootMenu.mNavIntegration.iSetTheSource(FacRootMenu.this.mSourceListView, new NavIntegration.iSourceChangeListener() { // from class: com.hisense.uifac.FacRootMenu.3.1
                    @Override // com.hisense.uifac.nav.NavIntegration.iSourceChangeListener
                    public void sourceChanged(View view2, final int i2) {
                        FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.3.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                FacRootMenu.this.checkInputSouce(i2);
                            }
                        });
                    }
                }, (String) FacRootMenu.this.mSourceList.get(selectedItemPosition));
            }
            FacRootMenu.this.selIdx = selectedItemPosition;
            return false;
        }
    }

    public class writeProductionKeyRunnable implements Runnable {
        public writeProductionKeyRunnable(FacRootMenu facRootMenu) {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.i("Fac_FacRootMenu", "check production key");
            if (FacRootMenu.mcf.getProducitonKey()) {
                return;
            }
            Log.i("Fac_FacRootMenu", "set production key");
            FacRootMenu.mcf.setProductionKey();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setKeysDialogMessage() {
        boolean z;
        Log.i("Fac_FacRootMenu", "!!!setKeysDialogMessage called");
        if (this.keysDialog == null) {
            return;
        }
        boolean z2 = true;
        if (!FacPropertyDemo.HIFAC_MARKET_JP || mcf.getEmmcType()) {
            z = true;
        } else {
            Log.i("Fac_FacRootMenu", "check emmc format error!!");
            z = false;
        }
        if (FacPropertyDemo.HIFAC_IS_OLED && mcf.checkTconTempVr() == 1) {
            Log.i("Fac_FacRootMenu", "check Tcon Temp Vr error!!");
            z2 = false;
        }
        if (!isMacAddressDefaultOrIllegal() && testWhetherKeyIsOK() && z && z2) {
            Log.d("Fac_FacRootMenu", "Every key is OK!!! dismiss red screen!!!");
            this.keysDialog.dismiss();
            mMacKeyWriter.onMacKeyOK();
            return;
        }
        this.mErrMsg.setLength(0);
        if (FacPropertyDemo.HIFAC_IS_OLED) {
            if (mcf.checkTconTempVr() == 0) {
                this.mErrMsg.append(getString(R.string.str_factorymenu_tcon_temp_vr) + " OK!\n");
            } else {
                this.mErrMsg.append(getString(R.string.str_factorymenu_tcon_temp_vr) + " NG!\n");
            }
        }
        if (isMacAddressDefaultOrIllegal()) {
            this.mErrMsg.append(getString(R.string.str_facmenu_setmacfail) + "\n");
        } else {
            this.mErrMsg.append(getString(R.string.str_facmenu_setmacsuccess) + "\n");
        }
        if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT) {
            if (isDeviceIDNull()) {
                this.mErrMsg.append(getString(R.string.str_wide_vine_key_fail) + "\n");
            } else {
                this.mErrMsg.append(getString(R.string.str_wide_vine_key_yes) + "\n");
            }
        }
        if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
            if (isNetflixNull()) {
                this.mErrMsg.append(getString(R.string.str_mgk_key_no) + "\n");
            } else {
                this.mErrMsg.append(getString(R.string.str_mgk_key_yes) + "\n");
            }
        }
        if (FacPropertyDemo.HIFAC_HDCP_1_4_KEY_SUPPORT) {
            if (isHdcp14KeyNull()) {
                this.mErrMsg.append(getString(R.string.str_hdcp14_key_fail) + "\n");
            } else {
                this.mErrMsg.append(getString(R.string.str_hdcp14_key_yes) + "\n");
            }
        }
        if (FacPropertyDemo.HIFAC_HDCP_2_X_KEY_SUPPORT) {
            if (isHdcp2xKeyNull()) {
                this.mErrMsg.append(getString(R.string.str_hdcp2x_key_fail) + "\n");
            } else {
                this.mErrMsg.append(getString(R.string.str_hdcp2x_key_yes) + "\n");
            }
        }
        if (mcf.isSupportQDAKey()) {
            if (isQDANull()) {
                this.mErrMsg.append(getString(R.string.str_qda_key_fail) + "\n");
            } else {
                this.mErrMsg.append(getString(R.string.str_qda_key_yes) + "\n");
            }
        }
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            if (isMarlinNull()) {
                this.mErrMsg.append(getString(R.string.str_marlin_key_fail) + "\n");
            } else {
                this.mErrMsg.append(getString(R.string.str_marlin_key_yes) + "\n");
            }
            if (isDTCPIPNull()) {
                this.mErrMsg.append(getString(R.string.str_dtcpip_key_fail) + "\n");
            } else {
                this.mErrMsg.append(getString(R.string.str_dtcpip_key_yes) + "\n");
            }
            if (isPVRNull()) {
                this.mErrMsg.append(getString(R.string.str_pvr_key_fail) + "\n");
            } else {
                this.mErrMsg.append(getString(R.string.str_pvr_key_yes) + "\n");
            }
            if (!mcf.getEmmcType()) {
                this.mErrMsg.append(getString(R.string.str_emmc_format_fail) + "\n");
            } else {
                this.mErrMsg.append(getString(R.string.str_emmc_format_yes) + "\n");
            }
        }
        this.keysMessage.setText(this.mErrMsg.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean doesVideoFileExistInUsb(boolean z) throws Throwable {
        Log.i("Fac_FacRootMenu", "doesVideoFileExistInUsb \t search began:");
        this.videoFileDirctory = "";
        this.videoFileName = "";
        List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(this);
        if (externalStoragePath != null && !externalStoragePath.isEmpty()) {
            Iterator<String> it = externalStoragePath.iterator();
            while (it.hasNext()) {
                File file = new File(it.next());
                Log.i("Fac_FacRootMenu", "doesVideoFileExistInUsb \t mountUsb:" + file.toString());
                if (file.isDirectory()) {
                    for (File file2 : file.listFiles()) {
                        String name = file2.getName();
                        String path = file2.getPath();
                        if (file2.isFile() && name.contains("VIDEO_TEST")) {
                            Log.i("Fac_FacRootMenu", "doesVideoFileExistInUsb \t contains const part." + this.videoFileDirctory + " \t" + this.videoFileName);
                            String[] strArrSplit = name.split("\\.");
                            if (z) {
                                if (strArrSplit[0].equals("3D_VIDEO_TEST")) {
                                    this.videoFileDirctory = path.substring(0, path.lastIndexOf("/"));
                                    this.videoFileName = name;
                                    Log.i("Fac_FacRootMenu", " doesVideoFileExistInUsb found 3d file.\t" + this.videoFileDirctory + " \t" + this.videoFileName);
                                    return true;
                                }
                            } else {
                                if (FacPropertyDemo.HIFAC_4K_VIDEO_SUPPORT) {
                                    this.VIDEO_NAME_TO_PLAY = "4K_VIDEO_TEST";
                                }
                                String str = FacParamManager.FAC_DMP_FILE_NAME;
                                this.VIDEO_NAME_TO_PLAY = str;
                                if (strArrSplit[0].equals(str)) {
                                    this.videoFileDirctory = path.substring(0, path.lastIndexOf("/"));
                                    this.videoFileName = name;
                                    Log.i("Fac_FacRootMenu", " doesVideoFileExistInUsb \tthe following line is the file to play");
                                    Log.i("Fac_FacRootMenu", " doesVideoFileExistInUsb found 2d file.\t" + this.videoFileDirctory + " \t" + this.videoFileName);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        Log.i("Fac_FacRootMenu", "There is no external storage!!!");
        return false;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("Fac_FacRootMenu", "**enter factory menu*****");
        setContentView(R.layout.nav_main_layout);
        getWindow().addFlags(128);
        findViews();
        this.mFacFunction_Adapter = FacFunction_Adapter.getInstance(getApplicationContext());
        mMacKeyWriter = new MacKeyWriter();
        initDrawable();
        initMuteImageView();
        mFacRootMenu = this;
        facRootMenuContext = this;
        if (this.mFacFunction_Adapter.isStoreMode()) {
            showStoreModeDialog();
        } else {
            this.mhHandler.sendEmptyMessageDelayed(111, 800L);
            this.mFirstPowerTag = readFile("/tvdatabase/factory/FirstPowerOn.txt");
            Log.i("Fac_FacRootMenu", "mFirstPowerTag is " + this.mFirstPowerTag);
            this.mIsFirstShowM = true;
            this.mInPicShowMode = false;
            parsePopertyXMLFile();
            FacPropertyDemo.SourceEnum.setWhichSourcesSupported();
            Intent intent = getIntent();
            this.keyRC = intent.getIntExtra("One", 0);
            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                this.serviceModeStatus = intent.getBooleanExtra("fromServiceMode", false);
                this.serviceModeMicCheck = intent.getIntExtra("startFunc", 0);
            }
            SystemProperties.set("sys.key_forward.enable", "0");
            FacPropertyDemo.initProperties();
            mcf = MenuConfigManager.getInstance(mFacRootMenu);
            initFactorySvc();
            this.imm = (InputMethodManager) getSystemService("input_method");
            showOriginView();
            Log.i("Fac_FacRootMenu", "onCreate****  view getCurrentFocus =" + getCurrentFocus());
            initReceiverAsync();
            initSharedPreference();
            initFactoryRootMenuParam();
        }
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            this.mTvSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() { // from class: com.hisense.uifac.FacRootMenu.7
                @Override // android.view.SurfaceHolder.Callback
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                }

                @Override // android.view.SurfaceHolder.Callback
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                }

                @Override // android.view.SurfaceHolder.Callback
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    Log.d("Fac_FacRootMenu", "surfaceCreated!!");
                    FacRootMenu.this.mSurface = surfaceHolder.getSurface();
                    FacRootMenu.this.client = new TvPlayerClient(FacRootMenu.mFacRootMenu);
                    FacRootMenu.this.client.setSurface(FacRootMenu.this.mSurface);
                    Log.d("Fac_FacRootMenu", "TvPlayerClient surface= " + FacRootMenu.this.mSurface);
                    FacRootMenu.this.mTvSurfaceView.setZOrderMediaOverlay(true);
                    surfaceHolder.setFormat(-3);
                    Log.i("Fac_FacRootMenu", "set surfaceview finish");
                }
            });
        } else {
            this.mTvSurfaceView.setVisibility(8);
        }
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            this.LONG_PRESS_TIME = 800;
            this.mBluetoothPaireObserver = new BluetoothPaireObserver(this, this.mhHandler);
            getContentResolver().registerContentObserver(Uri.parse("content://settings/global/bluetooth_remote_connected"), true, this.mBluetoothPaireObserver);
        }
        try {
            Thread.sleep(500L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        Log.i("Fac_FacRootMenu", "onSaveInstanceState");
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mIsStartFromMenu = intent.getBooleanExtra("fromMenu", false);
        Log.i("Fac_FacRootMenu", "root M is started from menu****");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isMacAddressDefaultOrIllegal() {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        String defaultMacAddress = mcf.getDefaultMacAddress();
        String macAddress = mcf.getMacAddress();
        Log.i("Fac_FacRootMenu", "current MAC address: " + macAddress + "\tdefault MAC address is : " + defaultMacAddress);
        if (macAddress == null || macAddress.isEmpty() || macAddress.equals(defaultMacAddress)) {
            return true;
        }
        if (macAddress.length() <= 17 || !macAddress.substring(0, 17).equalsIgnoreCase(defaultMacAddress)) {
            return macAddress.length() > 17 && macAddress.substring(0, 17).equalsIgnoreCase("FF:FF:FF:FF:FF:FF");
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDeviceIDNull() {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        String deviceID = mcf.getDeviceID();
        if (deviceID != null) {
            return "NG".equalsIgnoreCase(deviceID) || deviceID.length() <= 0 || !mcf.isWideVineNameIsRight();
        }
        Log.i("Fac_FacRootMenu", "deviceID is null");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPlayReadyNull() {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        if (FacPropertyDemo.HIFAC_PLAY_READY_40KEY_SUPPORT) {
            String playReady40Key = mcf.getPlayReady40Key();
            if ("NG".equalsIgnoreCase(playReady40Key) || playReady40Key.length() <= 0) {
                return true;
            }
        }
        if (FacPropertyDemo.HIFAC_PLAY_READY_30KEY_SUPPORT) {
            String playReadyKey = mcf.getPlayReadyKey();
            if ("NG".equalsIgnoreCase(playReadyKey) || playReadyKey.length() <= 0) {
                return true;
            }
        }
        if (!FacPropertyDemo.HIFAC_PLAY_READY_25KEY_SUPPORT) {
            return false;
        }
        String playReady25Key = mcf.getPlayReady25Key();
        return "NG".equalsIgnoreCase(playReady25Key) || playReady25Key.length() <= 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isNetflixNull() {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        String netflixKey = mcf.getNetflixKey();
        return "NG".equalsIgnoreCase(netflixKey) || netflixKey.length() <= 0 || !mcf.mgkNameIsRight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isQDANull() {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        if (!mcf.isSupportQDAKey()) {
            return false;
        }
        String qDAKey = mcf.getQDAKey();
        return "NG".equalsIgnoreCase(qDAKey) || qDAKey.length() <= 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAttestationNull() {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        String attestationKey = mcf.getAttestationKey();
        return "NG".equalsIgnoreCase(attestationKey) || attestationKey.length() <= 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isChinaDrmKeyNull() {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        String chinaDrmKey = mcf.getChinaDrmKey();
        if (chinaDrmKey != null) {
            return "NG".equalsIgnoreCase(chinaDrmKey) || "default".equalsIgnoreCase(chinaDrmKey) || chinaDrmKey.length() <= 0;
        }
        Log.i("Fac_FacRootMenu", "chinadarmkey is null");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHdcpKeyNull() {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        String hdcpKey = mcf.getHdcpKey();
        if (hdcpKey != null) {
            return "NG".equalsIgnoreCase(hdcpKey) || "default".equalsIgnoreCase(hdcpKey) || hdcpKey.length() <= 0;
        }
        Log.i("Fac_FacRootMenu", "hdcp key is null");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHdcp14KeyNull() {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        String hdcp14Key = mcf.getHdcp14Key();
        if (hdcp14Key != null) {
            return "NG".equalsIgnoreCase(hdcp14Key) || "default".equalsIgnoreCase(hdcp14Key) || hdcp14Key.length() <= 0;
        }
        Log.i("Fac_FacRootMenu", "hdcp1.4 key is null");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHdcp2xKeyNull() {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        String hdcp2xKey = mcf.getHdcp2xKey();
        if (hdcp2xKey != null) {
            return "NG".equalsIgnoreCase(hdcp2xKey) || "default".equalsIgnoreCase(hdcp2xKey) || hdcp2xKey.length() <= 0;
        }
        Log.i("Fac_FacRootMenu", "hdcp 2.x key is null");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDTCPIPNull() {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        String dtcpIpKey = mcf.getDtcpIpKey();
        if (dtcpIpKey != null) {
            return "NG".equalsIgnoreCase(dtcpIpKey) || "default".equalsIgnoreCase(dtcpIpKey) || dtcpIpKey.length() <= 0;
        }
        Log.i("Fac_FacRootMenu", "dtcp id key is null");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isMarlinNull() {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        String marlinKey = mcf.getMarlinKey();
        if (marlinKey != null) {
            return "NG".equalsIgnoreCase(marlinKey) || "default".equalsIgnoreCase(marlinKey) || marlinKey.length() <= 0;
        }
        Log.i("Fac_FacRootMenu", "marlin key is null");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPVRNull() {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        String pVRKey = mcf.getPVRKey();
        if (pVRKey != null) {
            return "NG".equalsIgnoreCase(pVRKey) || "default".equalsIgnoreCase(pVRKey) || pVRKey.length() <= 0;
        }
        Log.i("Fac_FacRootMenu", "PVR key is null");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ProgressDialog get3DGlassesPairProgressDialog() {
        if (this.blueTooth3DGlassesProgressDialog == null) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            this.blueTooth3DGlassesProgressDialog = progressDialog;
            progressDialog.setMessage(getString(R.string.str_facmenu_3dglasses_pairstart));
            this.blueTooth3DGlassesProgressDialog.setIndeterminate(true);
            this.blueTooth3DGlassesProgressDialog.setCancelable(true);
        }
        return this.blueTooth3DGlassesProgressDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ProgressDialog getProgressDialog() {
        if (this.progressDialog == null) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            this.progressDialog = progressDialog;
            progressDialog.setMessage(getString(R.string.str_factorymenu_waiting));
            this.progressDialog.setIndeterminate(true);
            this.progressDialog.setCancelable(true);
            this.progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener(this) { // from class: com.hisense.uifac.FacRootMenu.8
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    Log.i("Fac_FacRootMenu", "**progressDialog onkey*");
                    return true;
                }
            });
        }
        return this.progressDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ProgressDialog getWarnProgressDialog(String str) {
        if (this.mToastProgressDialog == null) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            this.mToastProgressDialog = progressDialog;
            progressDialog.setMessage(str);
            this.mToastProgressDialog.setIndeterminate(true);
            this.mToastProgressDialog.setCancelable(true);
        }
        return this.mToastProgressDialog;
    }

    @Override // android.app.Activity
    protected void onPause() {
        Log.i("Fac_FacRootMenu", "**onPause*****");
        super.onPause();
        hideBigMIcon();
        ProgressDialog progressDialog = this.progressDialog;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
        ProgressDialog progressDialog2 = this.blueTooth3DGlassesProgressDialog;
        if (progressDialog2 != null && progressDialog2.isShowing()) {
            this.blueTooth3DGlassesProgressDialog.dismiss();
        }
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mhHandler.removeMessages(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC);
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        AgingForLaserTvControl agingForLaserTvControl;
        Log.i("Fac_FacRootMenu", "**onDestroy*****");
        SystemProperties.set("sys.key_forward.enable", "1");
        try {
            if (FacPropertyDemo.HIFAC_MIC_DETECT_SUPPORT) {
                mcf.setMicDevice(mMicDeviceType);
            }
            if (FacPropertyDemo.HIFAC_BLUETOOTH_SUPPORT) {
                sendBroadcast(new Intent("com.hisense.tv.bluetooth.bluetooth3D_mode_on"));
            }
            if (FacPropertyDemo.HIFAC_LIGHT_SENSOR_SUPPORT && this.mIsLightSensorEnable) {
                Log.i("Fac_FacRootMenu", "open light sensor!");
                this.mFacFunction_Adapter.setSensorEnable(true);
            }
            this.wm.removeView(mMuteImageView);
            mNavIntegration.iExecWhenHisFacEnding();
            unregisterReceiver(this.mShutDownReceiver);
            unregisterReceiver(this.mSystemkeyReceiver);
            unregisterReceiver(this.mHisenseSystemkeyReceiver);
            unregisterReceiver(this.mCheckKeyStatusReceiver);
            if (this.mThreeDGlassesPairedReceiver != null) {
                unregisterReceiver(this.mThreeDGlassesPairedReceiver);
            }
            if (this.mPrevWifiStatus) {
                this.mFacFunction_Adapter.reconnectWifi();
            } else {
                this.mFacFunction_Adapter.closeWifi();
            }
            if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                this.mhHandler.removeMessages(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC);
            }
            if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
                this.mSurface = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDTSSwitch(0);
        if (this.mMtkAudioPatchManager != null) {
            Log.i("Fac_FacRootMenu", "onDestroy releaseAudioPatch");
            this.mMtkAudioPatchManager.releaseAudioPatch();
            this.mMtkAudioPatchManager = null;
        } else {
            Log.i("Fac_FacRootMenu", "onDestroy MtkAudioPatchManager is null");
        }
        if (this.mKeyAlertDialog != null) {
            this.mKeyAlertDialog = null;
        }
        if (!this.mTvStoped) {
            mcf.setMtkSourceStatus(false);
            Log.i("Fac_FacRootMenu", "TV stop");
            this.mTvStoped = true;
        }
        if ((FacPropertyDemo.HIFAC_IS_LASER4K || FacPropertyDemo.HIFAC_IS_OLED_TVS) && (agingForLaserTvControl = this.mAgingLaserTv) != null) {
            agingForLaserTvControl.shutdownAgingForLaserTv();
        }
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            getContentResolver().unregisterContentObserver(this.mBluetoothPaireObserver);
        }
        super.onDestroy();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:25:0x00bc  */
    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        boolean zHandleHorizontalFocusWithinListItem;
        Log.v("Fac_FacRootMenu", "onKey=" + i);
        canncelMessage(2);
        canncelMessage(4);
        MenuSetUpListView menuSetUpListView = this.mMenu3DSettingListView;
        if (menuSetUpListView == null || !menuSetUpListView.hasFocus()) {
            Log.v("Fac_FacRootMenu", "onkey return");
            return false;
        }
        if (this.mMenu3DSettingListView.getAdapter() == null) {
            Log.v("Fac_FacRootMenu", "onkey return2");
            return false;
        }
        int action = keyEvent.getAction();
        if (action != 1) {
            Log.v("Fac_FacRootMenu", "onkey action=" + action + "select=" + this.mCurrentSelectedPosition);
            this.mCurrentSelectedPosition = this.mMenu3DSettingListView.getSelectedItemPosition();
            this.childView = (RespondedKeyEvent) this.mMenu3DSettingListView.getSelectedView();
            this.mListViewSelectedItemData = (SetConfigListViewAdapter.DataItem) this.mMenu3DSettingListView.getItemAtPosition(this.mCurrentSelectedPosition);
            if (this.childView == null) {
                RespondedKeyEvent respondedKeyEvent = (RespondedKeyEvent) this.mMenu3DSettingListView.getChildAt(this.mCurrentSelectedPosition);
                this.childView = respondedKeyEvent;
                if (respondedKeyEvent == null) {
                    Log.w("Fac_FacRootMenu", "mMenu3DSettingListView has not been prepared");
                    return false;
                }
            }
            Log.v("Fac_FacRootMenu", "select=" + this.mCurrentSelectedPosition);
            switch (i) {
                case 19:
                case 20:
                    zHandleHorizontalFocusWithinListItem = false;
                    break;
                case 21:
                    zHandleHorizontalFocusWithinListItem = handleHorizontalFocusWithinListItem(21);
                    break;
                case 22:
                    zHandleHorizontalFocusWithinListItem = handleHorizontalFocusWithinListItem(22);
                    break;
                default:
                    zHandleHorizontalFocusWithinListItem = false;
                    break;
            }
        } else {
            zHandleHorizontalFocusWithinListItem = false;
        }
        return zHandleHorizontalFocusWithinListItem;
    }

    private void exitMStatusMethod() {
        cancelNumChangeChannel();
        if (FacPropertyDemo.HIFAC_MARKET_TW || this.mFacFunction_Adapter.getCountryName().equalsIgnoreCase(MtkTvConfigTypeBase.S3166_CFG_COUNT_MYS)) {
            this.mFacFunction_Adapter.writeBooleanSetting("hdmi_control_enabled", false);
        }
        hideAll();
        mcf.setValue("HIFAC_STATUS", 0);
        getWindow().clearFlags(128);
        this.mFacFunction_Adapter.resetDBX();
        if (FacPropertyDemo.HIFAC_IS_LASER4K && FacPropertyDemo.HIFAC_LASER_IS_L9) {
            this.mFacFunction_Adapter.setLedControl(23);
        } else {
            this.mFacFunction_Adapter.setLedControl(3);
        }
        if (this.mMtkAudioPatchManager != null) {
            Log.i("Fac_FacRootMenu", "exitMStatusMethod releaseAudioPatch");
            this.mMtkAudioPatchManager.releaseAudioPatch();
            this.mMtkAudioPatchManager = null;
        } else {
            Log.i("Fac_FacRootMenu", "exitMStatusMethod MtkAudioPatchManager is null");
        }
        Log.i("Fac_FacRootMenu", "exit factory to send broadcast = " + this.exit_factory_intent.getAction());
        sendBroadcast(this.exit_factory_intent);
        this.mhHandler.removeMessages(0);
        this.mhHandler.removeMessages(4);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:1102:0x19b1  */
    /* JADX WARN: Code duplicated, block: B:1104:0x19b9  */
    /* JADX WARN: Code duplicated, block: B:1105:0x19c2  */
    /* JADX WARN: Code duplicated, block: B:1106:0x19ca  */
    /* JADX WARN: Code duplicated, block: B:1112:0x19dc  */
    /* JADX WARN: Code duplicated, block: B:1113:0x1a0f  */
    /* JADX WARN: Code duplicated, block: B:1118:0x1a1f  */
    /* JADX WARN: Code duplicated, block: B:1120:0x1a27  */
    /* JADX WARN: Code duplicated, block: B:1121:0x1a54  */
    /* JADX WARN: Code duplicated, block: B:1123:0x1a60  */
    /* JADX WARN: Code duplicated, block: B:1124:0x1a67  */
    /* JADX WARN: Code duplicated, block: B:1127:0x1a72  */
    /* JADX WARN: Code duplicated, block: B:1128:0x1a79  */
    /* JADX WARN: Code duplicated, block: B:1131:0x1a7e  */
    /* JADX WARN: Code duplicated, block: B:1135:0x1a8a  */
    /* JADX WARN: Code duplicated, block: B:1138:0x1a8f  */
    /* JADX WARN: Code duplicated, block: B:1140:0x1a99  */
    /* JADX WARN: Code duplicated, block: B:1141:0x1a9b  */
    /* JADX WARN: Code duplicated, block: B:1144:0x1aa6  */
    /* JADX WARN: Code duplicated, block: B:1147:0x1ab7  */
    /* JADX WARN: Code duplicated, block: B:1151:0x1aca  */
    /* JADX WARN: Code duplicated, block: B:1153:0x1ad3  */
    /* JADX WARN: Code duplicated, block: B:1157:0x1add  */
    /* JADX WARN: Code duplicated, block: B:1160:0x1b46 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:1173:0x1b91  */
    /* JADX WARN: Code duplicated, block: B:1181:0x1bd3  */
    /* JADX WARN: Code duplicated, block: B:1184:0x1be6  */
    /* JADX WARN: Code duplicated, block: B:1185:0x1be9  */
    /* JADX WARN: Code duplicated, block: B:1236:0x1d28  */
    /* JADX WARN: Code duplicated, block: B:1238:0x1d2c  */
    /* JADX WARN: Code duplicated, block: B:1257:0x1d96  */
    /* JADX WARN: Code duplicated, block: B:1259:0x1d9e  */
    /* JADX WARN: Code duplicated, block: B:1261:0x1db0  */
    /* JADX WARN: Code duplicated, block: B:1262:0x1db5  */
    /* JADX WARN: Code duplicated, block: B:1265:0x1dba  */
    /* JADX WARN: Code duplicated, block: B:1268:0x1de1  */
    /* JADX WARN: Code duplicated, block: B:1269:0x1df0  */
    /* JADX WARN: Code duplicated, block: B:1272:0x1dfe  */
    /* JADX WARN: Code duplicated, block: B:1281:0x1e17  */
    /* JADX WARN: Code duplicated, block: B:1284:0x1e46  */
    /* JADX WARN: Code duplicated, block: B:1286:0x1e4a  */
    /* JADX WARN: Code duplicated, block: B:1288:0x1e54  */
    /* JADX WARN: Code duplicated, block: B:1291:0x1e64  */
    /* JADX WARN: Code duplicated, block: B:1292:0x1e71  */
    /* JADX WARN: Code duplicated, block: B:1294:0x1e75  */
    /* JADX WARN: Code duplicated, block: B:1301:0x1e99  */
    /* JADX WARN: Code duplicated, block: B:1303:0x1ea8  */
    /* JADX WARN: Code duplicated, block: B:1308:0x1ec3  */
    /* JADX WARN: Code duplicated, block: B:1309:0x1edb  */
    /* JADX WARN: Code duplicated, block: B:1314:0x1ef6  */
    /* JADX WARN: Code duplicated, block: B:1319:0x1f1c  */
    /* JADX WARN: Code duplicated, block: B:1320:0x1f1f  */
    /* JADX WARN: Code duplicated, block: B:1330:0x1f56  */
    /* JADX WARN: Code duplicated, block: B:1332:0x1f71  */
    /* JADX WARN: Code duplicated, block: B:1333:0x1f73  */
    /* JADX WARN: Code duplicated, block: B:239:0x03d5  */
    /* JADX WARN: Code duplicated, block: B:353:0x05dd  */
    /* JADX WARN: Code duplicated, block: B:355:0x05e1  */
    /* JADX WARN: Code duplicated, block: B:356:0x05e6  */
    /* JADX WARN: Code duplicated, block: B:358:0x0604  */
    /* JADX WARN: Code duplicated, block: B:386:0x0721  */
    /* JADX WARN: Code duplicated, block: B:536:0x0b1a  */
    /* JADX WARN: Code duplicated, block: B:538:0x0b1e  */
    /* JADX WARN: Instruction removed from duplicated block: B:1112:0x19dc, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v256 */
    /* JADX WARN: Type inference failed for: r1v257, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r1v259 */
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
    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) throws Throwable {
        String str;
        boolean z;
        boolean z2;
        String string;
        String str2;
        boolean zIsMacAddressDefaultOrIllegal;
        boolean zMgkNameIsRight;
        boolean zIsMicSwitchOn;
        boolean zIsHaveClientType;
        boolean z3;
        String serviceTag;
        boolean z4;
        int i2;
        ?? r1;
        int i3;
        int i4;
        String str3;
        Log.i("Fac_FacRootMenu", "onKeyDown keycode = " + i + " " + keyEvent.toString());
        StringBuilder sb = new StringBuilder();
        sb.append("onKeyDown  event.getDownTime() == ");
        sb.append(keyEvent.getDownTime());
        Log.i("Fac_FacRootMenu", sb.toString());
        Log.i("Fac_FacRootMenu", "onKeyDown  event.isLongPress() == " + keyEvent.isLongPress());
        Log.i("Fac_FacRootMenu", "onKeyDown  event.getRepeatCount() == " + keyEvent.getRepeatCount());
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mhHandler.removeMessages(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC);
            this.mhHandler.sendEmptyMessageDelayed(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC, FacParamManager.FAC_SCREEN_PROTECT_TIME);
        }
        int[] iArr = this.mOnKeyDownSecretKeys;
        int i5 = 0;
        iArr[0] = iArr[1];
        iArr[1] = iArr[2];
        iArr[2] = iArr[3];
        iArr[3] = iArr[4];
        iArr[4] = i;
        if (!this.mIsMacKeyOK) {
            if (i == 217 && !isMacAddressDefaultOrIllegal() && testWhetherKeyIsOK()) {
                Log.d("Fac_FacRootMenu", "In activity every key is OK!!! dismiss red screen!!!");
                this.mIsMacKeyOK = true;
                ImageView imageView = this.mImageView;
                if (imageView != null) {
                    imageView.setBackgroundDrawable(null);
                    this.mImageView.setVisibility(8);
                }
            }
            int[] iArr2 = this.mOnKeyDownSecretKeys;
            if (iArr2[0] == 4216 && iArr2[1] == 8 && iArr2[2] == 16 && iArr2[3] == 16 && iArr2[4] == 16) {
                this.mImageView.setBackgroundDrawable(null);
                this.mImageView.setVisibility(8);
                this.mIsMacKeyOK = true;
                if (FacPropertyDemo.HIFAC_MARKET_SA) {
                    showBigMMode();
                }
                return true;
            }
            if (i != 4214) {
                return true;
            }
        }
        preHandlerKeyDown();
        if (this.childType == 6 && i != 4224 && i != 112233) {
            Log.i("Fac_FacRootMenu", "balance mode");
            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_650) {
                    mNavIntegration.preSetTvsTheeterWooferBalanceToCenter();
                } else if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_550) {
                    mNavIntegration.preSetTvsTheeterBalanceToCenter();
                } else if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_850) {
                    mNavIntegration.preSetTvsBalanceToCenterLike850();
                }
            } else if (mcf.getSubWooferTotal() == 1) {
                mNavIntegration.preSetDoubleAmplifierBalanceToCenter();
            } else if (mcf.getSubWooferTotal() == 2) {
                mNavIntegration.preSetMultiAmplifierBalanceToMIDDLE();
            } else {
                mNavIntegration.setBalanceToMiddle();
            }
            this.mhHandler.sendEmptyMessage(2);
        }
        if (this.isFactoryokbusy) {
            Log.i("Fac_FacRootMenu", "In factory ok process, not respond keydown event.");
            return true;
        }
        if (this.mIsStartFromMenu) {
            this.mIsStartFromMenu = false;
        }
        if (i == 230 || i == 4293 || i == 209 || i == 4247) {
            if (keyEvent.getRepeatCount() == 1) {
                this.isLongPressedDmp = true;
            } else if (keyEvent.getRepeatCount() > 1) {
                return true;
            }
        }
        if (i == 4252) {
            if (keyEvent.getRepeatCount() == 0) {
                is3DPressed = false;
            }
            if (keyEvent.getEventTime() - keyEvent.getDownTime() >= 1500 && !is3DPressed) {
                this.isLongPressed3D = true;
                is3DPressed = true;
            }
        }
        MenuConfigManager menuConfigManager = mcf;
        if (menuConfigManager == null) {
            int i6 = mcfNullCounter + 1;
            mcfNullCounter = i6;
            if (i6 > 5) {
                Toast.makeText(this, "Mcf is null, maybe the service OK is delayed.", 1).show();
            }
            Log.i("Fac_FacRootMenu", "mcf is null, not respond keydown event.");
            return true;
        }
        mcfNullCounter = 0;
        if (menuConfigManager.getDefault("HIFAC_AGING") == 1) {
            if (isNeedExitAging(i)) {
                factoryAging(false);
                showBigMMode();
                cleanAgingExitKey();
                return true;
            }
            if (isNeedExitAging2(i)) {
                factoryAging(false);
                showBigMMode();
                cleanAgingExitKey2();
                return true;
            }
            if (i != 4223 && i != 4299) {
                return true;
            }
        }
        if (mcf.isTestPattern() && i != 4222 && i != 4 && i != 21 && i != 22) {
            Log.i("Fac_FacRootMenu", "In pattern testing, not respond keydown event except KEYCODE_HISIR_PANNEL_TEST.");
            return true;
        }
        if (mcf.isInWBPatternTest() && i != 21 && i != 22 && i != 4362 && i != 4363 && i != 4364 && i != 4365 && i != 4366 && i != 4367 && i != 4368 && i != 4369 && i != 4370 && i != 4371 && i != 4372 && i != 4373 && i != 4374 && i != 4375 && i != 4376 && i != 4377 && i != 4233) {
            return true;
        }
        if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0 && mIsAlreadySet && i != 4234 && i != 4 && i != 21 && i != 22 && i != 7) {
            Log.i("Fac_FacRootMenu", "In vcom mode, not respond keydown event except F4,BACK,LEFT,RIGHT,KEYCODE_0.");
            return true;
        }
        Log.i("Fac_FacRootMenu", "onKeyDown will enter switch keyCode");
        if (i == 166) {
            Log.i("Fac_FacRootMenu", "****KEYCODE_CHANNEL_UP");
            if (this.viewMode != 0 && !this.inputNuming && mNavIntegration.isCurrenSourceTV()) {
                Log.i("Fac_FacRootMenu", "*****ch up");
                Log.i("Fac_FacRootMenu", "*****have channel");
                this.mLayout_Bottom_One_Colmun_LL.setVisibility(8);
                hideBigMIcon();
                this.mModel_ShortTip_LL.setVisibility(8);
                sendMessageDelayed(31, 100);
            }
        } else if (i == 167) {
            Log.i("Fac_FacRootMenu", "****KEYCODE_CHANNEL_DOWN");
            if (this.viewMode != 0 && !this.inputNuming && mNavIntegration.isCurrenSourceTV()) {
                hideBigMIcon();
                this.mModel_ShortTip_LL.setVisibility(8);
                sendMessageDelayed(32, 100);
            }
        } else if (i == 645) {
            Log.i("Fac_FacRootMenu", "****KEYCODE_CHANNEL_UP");
            if (this.viewMode != 0) {
                Log.i("Fac_FacRootMenu", "*****ch up");
                Log.i("Fac_FacRootMenu", "*****have channel");
                this.mLayout_Bottom_One_Colmun_LL.setVisibility(8);
                hideBigMIcon();
                this.mModel_ShortTip_LL.setVisibility(8);
                sendMessageDelayed(31, 100);
            }
        } else if (i == 646) {
            Log.i("Fac_FacRootMenu", "****KEYCODE_CHANNEL_DOWN");
            if (this.viewMode != 0) {
                hideBigMIcon();
                this.mModel_ShortTip_LL.setVisibility(8);
                sendMessageDelayed(32, 100);
            }
        } else if (i == 4199) {
            if (FacPropertyDemo.HIFAC_MARKET_TW) {
                str = "HIFAC_PICTUREMODE_TW";
            } else {
                str = "HIFAC_PICTUREMODE";
            }
            Log.d("Fac_FacRootMenu", "gaomingyueFACTORY_PICTUREMODE" + str);
            if (this.viewMode != 0 && !this.inputNuming) {
                if (this.mModel_ShortTip_TV.getVisibility() != 0 && this.childType == 3) {
                    mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.40
                        @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                        public void effectChanged(View view, int i7) {
                            FacRootMenu.this.setPicModeString(i7);
                        }
                    }, str);
                } else {
                    setPicModeString(mNavIntegration.iGetEffect(str));
                    this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                }
                showTipMode(true);
                this.childType = 3;
                mSetShortTipData(3);
            }
        } else if (i != 4200) {
            switch (i) {
                case 4:
                    if (i != 4) {
                        if (FacPropertyDemo.HIFAC_MARKET_JP) {
                            this.mFacFunction_Adapter.setTVSSavingMode(FacTvsFunctionUtil.TVS_SAVING_MODE_STANDARD);
                        }
                        if (FacPropertyDemo.HIFAC_LIGHT_SENSOR_SUPPORT && this.mFacFunction_Adapter.getSensorEnableStatus()) {
                            Log.i("Fac_FacRootMenu", ".setSensorEnable(false)");
                            this.mFacFunction_Adapter.setSensorEnable(false);
                        }
                        if (FacPropertyDemo.HIFAC_VCOM_SUPPORT <= 0 && mIsAlreadySet) {
                            Log.i("Fac_FacRootMenu", "it's in vcom mode, save vcom value");
                            if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                if (1 == mcf.getVcomType()) {
                                    mcf.saveVcomVoltage2(mcf.getVcomVoltage2(1), 1);
                                } else {
                                    mcf.saveVcomVoltage2(mcf.getVcomVoltage2(2), 2);
                                }
                                mcf.setVcomType(0);
                            } else {
                                mcf.saveVcomVoltage(mcf.getVcomVoltage2(0));
                            }
                            setPatternNotSetYet();
                            showBigMMode();
                        } else {
                            if (mcf.isTestPattern()) {
                                SystemProperties.set("sys.vt.factory.pattern_test", "false");
                                mcf.setTestPatternModeOff();
                                if (MenuConfigManager.isAudioMute()) {
                                    r1 = 0;
                                    sendMuteBroadcast(false);
                                } else {
                                    r1 = 0;
                                }
                                if (FacPropertyDemo.HIFAC_LOCALDIMMING_SUPPORT) {
                                    mcf.setBackLightOn(r1);
                                    mcf.setLocalDimmingSwitch(true);
                                }
                                mcf.setTestPattern(r1, r1, r1, r1);
                                mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                                this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                                if (mcf.getDefault("HIFAC_STATUS") == 0) {
                                    this.mhHandler.removeMessages(r1);
                                    this.mhHandler.removeMessages(4);
                                    finish();
                                } else {
                                    showBigMMode();
                                }
                            }
                            if (mcf.getDefault("HIFAC_STATUS") != 1 && (this.mModel_ShortTip_TV.getVisibility() == 0 || (i2 = this.viewMode) == 0 || i2 == 9)) {
                                showBigMMode();
                            } else {
                                if (this.mInPicShowMode) {
                                    mButton_FactoryMenuRoot.setVisibility(0);
                                    mSurfaceView.setVisibility(8);
                                    mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                                    this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                                    this.mFacFunction_Adapter.switchSourceByName(this.mPrevSourceEnum.name());
                                }
                                if (!FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.US) || FacPropertyDemo.HIFAC_MARKET_SA) {
                                    this.mFacFunction_Adapter.setLocalDimmingCheck(0);
                                    if (!FacPropertyDemo.HIFAC_MIC_SUPPORT && !mcf.isZDMicTestOK()) {
                                        this.mhHandler.sendEmptyMessage(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_ON);
                                    } else {
                                        mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                                        this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                                    }
                                }
                                if (i == 4216) {
                                    this.mIsMKeyPressed = true;
                                    this.mhHandler.removeMessages(18);
                                    sendMessageDelayed(18, 3000);
                                }
                            }
                        }
                    } else if (i == 4216) {
                        this.mIsMKeyPressed = true;
                        this.mhHandler.removeMessages(18);
                        sendMessageDelayed(18, 3000);
                    }
                    break;
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                    inputFangChuanHuoNumber(i);
                    break;
                case 54:
                    FangChuanHuoStatus fangChuanHuoStatus = this.mFangChuanHuo_Flag;
                    if (fangChuanHuoStatus == FangChuanHuoStatus.FANGCHUANHUO_Z || fangChuanHuoStatus == FangChuanHuoStatus.FANGCHUANHUO_INPUTNUM) {
                        inputFangChuanHuoNumber(i);
                    } else {
                        canncelMessage(8);
                        if (this.mFangChuanHuo_Flag == FangChuanHuoStatus.FANGCHUANHUO_START) {
                            this.mFangChuanHuo_Flag = FangChuanHuoStatus.FANGCHUANHUO_Z;
                            this.inputFangChuanHuoNumberStrBuffer = new StringBuffer("");
                            sendMessageDelayed(10, 1500);
                        } else {
                            this.mFangChuanHuo_Flag = FangChuanHuoStatus.FANGCHUANHUO_IDLE;
                        }
                    }
                    break;
                case 66:
                    if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0 && mIsAlreadySet) {
                        Log.i("Fac_FacRootMenu", "it's in vcom mode, save vcom value");
                        mcf.getVcomVoltage();
                        setPatternNotSetYet();
                        return true;
                    }
                    break;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_CAM_CARD_MISSING /* 82 */:
                    if (mcf.getDefault("HIFAC_STATUS") == 0) {
                        Log.i("Fac_FacRootMenu", "****exit factory");
                        this.mhHandler.removeMessages(0);
                        this.mhHandler.removeMessages(4);
                        finish();
                    } else {
                        Log.i("Fac_FacRootMenu", "to main menu");
                        if (mButton_FactoryMenuRoot.getVisibility() == 0 && mButton_FactoryMenuRoot.requestFocus()) {
                            Log.i("Fac_FacRootMenu", "Start main menu.");
                            startActivityForResult(new Intent(this, (Class<?>) MenuMain.class), 0);
                            if (FacPropertyDemo.HIFAC_MARKET_SA) {
                                this.mFacFunction_Adapter.setSoftwareVersionLog();
                            }
                        } else {
                            Log.i("Fac_FacRootMenu", "Return root menu, rootmenu.visibility=" + mButton_FactoryMenuRoot.getVisibility() + " and rootmenu.focus=" + mButton_FactoryMenuRoot.requestFocus());
                            showBigMMode();
                        }
                    }
                    break;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_SERVICE_PIN_BLOCKED /* 85 */:
                    mcf.setChannelInitFileFromUdisk();
                    break;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_OFF /* 140 */:
                case 4390:
                    if (!this.inputNuming) {
                        int iIndexOf = this.mSourceList.indexOf(getDisplayName(mNavIntegration.getCurrentInputSource()));
                        int i7 = iIndexOf < 0 ? 0 : iIndexOf;
                        TextView textView = (TextView) this.mSourceListView.getChildAt(i7);
                        if (this.viewMode == 0) {
                            if (textView != null) {
                                textView.setCompoundDrawables(this.mSourceUnSelectedIcon, null, null, null);
                            }
                            mNavIntegration.iChangeNextSource(this.mSourceListView, new NavIntegration.iSourceChangeListener() { // from class: com.hisense.uifac.FacRootMenu.39
                                @Override // com.hisense.uifac.nav.NavIntegration.iSourceChangeListener
                                public void sourceChanged(View view, int i8) {
                                    FacRootMenu.this.checkInputSouce(i8);
                                }
                            });
                        } else {
                            this.mSourceListAdapter.notifyDataSetChanged();
                            this.mSourceListView.setSelection(i7);
                            if (textView != null) {
                                textView.setCompoundDrawables(this.mSourceSelectedIcon, null, null, null);
                            }
                            showSourceMode();
                        }
                        sendMessageDelayed(1);
                        return true;
                    }
                    break;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_AUDIO_STREAM_CTR_NOT_SUPPORT /* 209 */:
                case 230:
                case 4247:
                case 4293:
                    if (this.isDmpPressedMessageSent) {
                        return true;
                    }
                    this.isDmpPressedMessageSent = true;
                    sendMessageDelayed(14, this.LONG_PRESS_TIME);
                    break;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CONN_PMT_UPDATED /* 211 */:
                    if (FacPropertyDemo.HIFAC_MARKET_TW) {
                        str = "HIFAC_PICTUREMODE_TW";
                    } else {
                        str = "HIFAC_PICTUREMODE";
                    }
                    Log.d("Fac_FacRootMenu", "gaomingyueFACTORY_PICTUREMODE" + str);
                    if (this.viewMode != 0) {
                        if (this.mModel_ShortTip_TV.getVisibility() != 0) {
                            setPicModeString(mNavIntegration.iGetEffect(str));
                            this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                        } else {
                            setPicModeString(mNavIntegration.iGetEffect(str));
                            this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                        }
                        showTipMode(true);
                        this.childType = 3;
                        mSetShortTipData(3);
                    }
                    break;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_FILE_OPER_ERROR /* 213 */:
                    if (this.viewMode != 0 && !this.inputNuming) {
                        z = true;
                        if (mNavIntegration.iCurrentInputSourceHasSignal()) {
                            Log.e("Fac_FacRootMenu", "iCurrentInputSourceHasSignal true ------");
                            if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                                if (this.mModel_ShortTip_TV.getVisibility() != 0 && this.childType == 5) {
                                    mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.41
                                        @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                                        public void effectChanged(View view, int i8) {
                                            FacRootMenu.this.setZoomModeString(i8);
                                        }
                                    }, "HIFAC_ZOOMMODE_FOR_EM");
                                } else {
                                    Log.e("Fac_FacRootMenu", "iCurrentInputSourceHasSignal childType != CHILD_TYPE_TIP_ASPECT ------");
                                    setZoomModeString(mNavIntegration.iGetEffect("HIFAC_ZOOMMODE_FOR_EM"));
                                    this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                                }
                            } else if (this.mModel_ShortTip_TV.getVisibility() != 0 && this.childType == 5) {
                                mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.42
                                    @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                                    public void effectChanged(View view, int i8) {
                                        FacRootMenu.this.setZoomModeString(i8);
                                    }
                                }, "HIFAC_ZOOMMODE");
                            } else {
                                Log.e("Fac_FacRootMenu", "iCurrentInputSourceHasSignal childType != CHILD_TYPE_TIP_ASPECT ------");
                                setZoomModeString(mNavIntegration.iGetEffect("HIFAC_ZOOMMODE"));
                                this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                            }
                            z = true;
                        }
                        showTipMode(z);
                        this.childType = 5;
                        mSetShortTipData(5);
                    }
                    break;
                case 4248:
                    Log.i("Fac_FacRootMenu", "====LVDS SSC disable once!");
                    this.mFacFunction_Adapter.setLvdsSscDisableOnce(3);
                    return true;
                case 4249:
                    if (this.mOnKeyDownSecretKeys[3] == 4252) {
                        startTestMic(true);
                        return true;
                    }
                    break;
                case 4250:
                    if (this.viewMode != 0 && !this.inputNuming) {
                        mcf.setValue("HIFAC_SAVINGMODE", SavingMode.BRIGHT.ordinal());
                        setSavingModeString(mNavIntegration.iGetEffect("HIFAC_SAVINGMODE"));
                        this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                        showTipMode(true);
                        this.childType = 24;
                        mSetShortTipData(24);
                    }
                    break;
                case 4251:
                    Log.i("Fac_FacRootMenu", "OK key is pressed, view mode is " + this.viewMode + "inputNuming is " + this.inputNuming);
                    if (!this.mFacFunction_Adapter.isHaveFte()) {
                        if (!mcf.isStartInFacMode()) {
                            mcf.displayHisToast("Reboot first!!!!");
                        } else {
                            SystemProperties.set("ctl.start", "dirty_work_misc_reset");
                        }
                    } else {
                        Log.i("Fac_FacRootMenu", "OK key is pressed, FTE is OK ");
                    }
                    this.inputNuming = false;
                    this.viewMode = 2;
                    if (2 != 0 && 0 == 0) {
                        if (this.bLogoKeyPressed) {
                            Log.i("Fac_FacRootMenu", "fail progress is " + getSharedPreferences("FactoryOk_failed", 0).getInt("FactoryOk_failed", 0));
                            Intent intent = new Intent(this, (Class<?>) DebugInfoActivity.class);
                            intent.putExtra("fileName", "factoryokresult.txt");
                            startActivity(intent);
                        } else if (this.mModel_ShortTip_TV.getVisibility() == 0 || this.childType != 20) {
                            if (!this.mFacFunction_Adapter.factoryOk_checkAutoGamma()) {
                                this.mModel_ShortTip_TV.setText("Autogamma NG");
                                this.mModel_ShortTip_TV.setTextColor(-65536);
                                this.mModel_ShortTip_TV.setGravity(17);
                                this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                                showTipMode(true);
                                this.mhHandler.removeMessages(2);
                                this.mhHandler.removeMessages(0);
                            } else {
                                boolean zTestWhetherKeyIsOK = testWhetherKeyIsOK();
                                zIsMacAddressDefaultOrIllegal = isMacAddressDefaultOrIllegal();
                                if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
                                    zMgkNameIsRight = mcf.mgkNameIsRight();
                                } else {
                                    zMgkNameIsRight = true;
                                }
                                zIsMicSwitchOn = mcf.isMicSwitchOn();
                                if (FacPropertyDemo.HIFAC_MARKET_SA) {
                                    zIsHaveClientType = mcf.isHaveClientType();
                                } else {
                                    zIsHaveClientType = true;
                                }
                                if (FacPropertyDemo.HIFAC_IS_OLED || mcf.checkTconTempVr() != 1) {
                                    z3 = true;
                                } else {
                                    z3 = false;
                                }
                                if (FacPropertyDemo.HIFAC_MARKET_JP) {
                                    if (mcf.getACASState() == FacParamManager.FAC_TVS_ACAS_STATE_VALID) {
                                        z4 = true;
                                    } else {
                                        z4 = false;
                                    }
                                    this.mIsACASSTateOk = z4;
                                    if (!mcf.getEmmcType()) {
                                        this.mIsEMMCOk = false;
                                    }
                                }
                                if (!mcf.getLogoName().equalsIgnoreCase("Sunbrite") || mcf.getLogoName().equalsIgnoreCase("snb")) {
                                    serviceTag = mcf.getServiceTag();
                                    if (serviceTag != null || serviceTag.isEmpty()) {
                                        this.mIsStOk = false;
                                    } else {
                                        this.mIsStOk = true;
                                    }
                                } else {
                                    this.mIsStOk = true;
                                }
                                Log.i("Fac_FacRootMenu", "Execute OK isKeyOk " + zTestWhetherKeyIsOK);
                                Log.i("Fac_FacRootMenu", "Execute OK isMgkNameRight " + zMgkNameIsRight);
                                Log.i("Fac_FacRootMenu", "Execute OK isMacNotOk " + zIsMacAddressDefaultOrIllegal);
                                Log.i("Fac_FacRootMenu", "Execute OK isMicSwitchOn " + zIsMicSwitchOn);
                                Log.i("Fac_FacRootMenu", "Execute OK isTconTempVrOK " + z3);
                                if (!zIsMacAddressDefaultOrIllegal || !zTestWhetherKeyIsOK || !zMgkNameIsRight || !zIsMicSwitchOn || !zIsHaveClientType || !this.mIsACASSTateOk || !this.mIsEMMCOk || !this.mIsStOk || !z3) {
                                    showOkErrorMsg(zIsMicSwitchOn, zIsMacAddressDefaultOrIllegal, z3);
                                } else {
                                    mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                                    this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                                    this.mModel_ShortTip_TV.setText(R.string.str_factorymenu_waiting);
                                    this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                                    showTipMode(true);
                                    this.mhHandler.removeMessages(2);
                                    this.mhHandler.removeMessages(0);
                                    this.childType = 20;
                                    mSetShortTipData(20);
                                }
                            }
                        }
                    }
                    break;
                case 4252:
                    Log.i("Fac_FacRootMenu", "is3DPressedMsgSent = " + this.is3DPressedMsgSent);
                    if (!this.is3DPressedMsgSent) {
                        Log.i("Fac_FacRootMenu", "**is3DPressedMsgSent**");
                        sendMessageDelayed(15, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR);
                        this.is3DPressedMsgSent = true;
                    }
                    if (this.bLogoKeyPressed) {
                        this.mhHandler.removeMessages(11);
                        this.bLogoKeyPressed = false;
                        startTestMic(false);
                    }
                    break;
                case 4278:
                    if (this.viewMode != 0 && !this.inputNuming) {
                        mcf.setValue("HIFAC_SAVINGMODE", SavingMode.SOFT.ordinal());
                        setSavingModeString(mNavIntegration.iGetEffect("HIFAC_SAVINGMODE"));
                        this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                        showTipMode(true);
                        this.childType = 24;
                        mSetShortTipData(24);
                    }
                    break;
                case 4279:
                    if (this.viewMode != 0 && !this.inputNuming) {
                        if (this.mModel_ShortTip_TV.getVisibility() == 0 && this.childType == 8) {
                            mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.27
                                @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                                public void effectChanged(View view, int i8) {
                                    FacRootMenu.this.mModel_ShortTip_TV.setText(i8 + "");
                                }
                            }, "HIFAC_RDRV_PLUS");
                        } else {
                            handleWBAutoTest("HIFAC_RDRV_PLUS");
                            this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                        }
                        showTipMode(false);
                        this.childType = 8;
                        mSetShortTipData(8);
                    }
                    break;
                case 4280:
                    if (this.viewMode != 0 && !this.inputNuming) {
                        if (this.mModel_ShortTip_TV.getVisibility() == 0 && this.childType == 9) {
                            mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.28
                                @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                                public void effectChanged(View view, int i8) {
                                    FacRootMenu.this.mModel_ShortTip_TV.setText(i8 + "");
                                }
                            }, "HIFAC_RDRV_MINUS");
                        } else {
                            handleWBAutoTest("HIFAC_RDRV_MINUS");
                            this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                        }
                        showTipMode(false);
                        this.childType = 9;
                        mSetShortTipData(9);
                    }
                    break;
                case 4281:
                    if (this.viewMode != 0 && !this.inputNuming) {
                        if (this.mModel_ShortTip_TV.getVisibility() == 0 && this.childType == 10) {
                            mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.29
                                @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                                public void effectChanged(View view, int i8) {
                                    FacRootMenu.this.mModel_ShortTip_TV.setText(i8 + "");
                                }
                            }, "HIFAC_GDRV_PLUS");
                        } else {
                            handleWBAutoTest("HIFAC_GDRV_PLUS");
                            this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                        }
                        showTipMode(false);
                        this.childType = 10;
                        mSetShortTipData(10);
                    }
                    break;
                case 4282:
                    if (this.viewMode != 0 && !this.inputNuming) {
                        if (this.mModel_ShortTip_TV.getVisibility() == 0 && this.childType == 11) {
                            mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.30
                                @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                                public void effectChanged(View view, int i8) {
                                    FacRootMenu.this.mModel_ShortTip_TV.setText(i8 + "");
                                }
                            }, "HIFAC_GDRV_MINUS");
                        } else {
                            handleWBAutoTest("HIFAC_GDRV_MINUS");
                            this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                        }
                        showTipMode(false);
                        this.childType = 11;
                        mSetShortTipData(11);
                    }
                    break;
                case 4283:
                    if (this.viewMode != 0 && !this.inputNuming) {
                        if (this.mModel_ShortTip_TV.getVisibility() == 0 && this.childType == 12) {
                            mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.31
                                @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                                public void effectChanged(View view, int i8) {
                                    FacRootMenu.this.mModel_ShortTip_TV.setText(i8 + "");
                                }
                            }, "HIFAC_BDRV_PLUS");
                        } else {
                            handleWBAutoTest("HIFAC_BDRV_PLUS");
                            this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                        }
                        showTipMode(false);
                        this.childType = 12;
                        mSetShortTipData(12);
                    }
                    break;
                case 4284:
                    if (this.viewMode != 0 && !this.inputNuming) {
                        if (this.mModel_ShortTip_TV.getVisibility() == 0 && this.childType == 13) {
                            mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.32
                                @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                                public void effectChanged(View view, int i8) {
                                    FacRootMenu.this.mModel_ShortTip_TV.setText(i8 + "");
                                }
                            }, "HIFAC_BDRV_MINUS");
                        } else {
                            handleWBAutoTest("HIFAC_BDRV_MINUS");
                            this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                        }
                        showTipMode(false);
                        this.childType = 13;
                        mSetShortTipData(13);
                    }
                    break;
                case 4285:
                    if (this.viewMode != 0 && !this.inputNuming) {
                        if (this.mModel_ShortTip_TV.getVisibility() == 0 && this.childType == 14) {
                            mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.33
                                @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                                public void effectChanged(View view, int i8) {
                                    FacRootMenu.this.mModel_ShortTip_TV.setText(i8 + "");
                                }
                            }, "HIFAC_RCUT_PLUS");
                        } else {
                            handleWBAutoTest("HIFAC_RCUT_PLUS");
                            this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                        }
                        showTipMode(false);
                        this.childType = 14;
                        mSetShortTipData(14);
                    }
                    break;
                case 4286:
                    if (this.viewMode != 0 && !this.inputNuming) {
                        if (this.mModel_ShortTip_TV.getVisibility() == 0 && this.childType == 15) {
                            mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.34
                                @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                                public void effectChanged(View view, int i8) {
                                    FacRootMenu.this.mModel_ShortTip_TV.setText(i8 + "");
                                }
                            }, "HIFAC_RCUT_MINUS");
                        } else {
                            handleWBAutoTest("HIFAC_RCUT_MINUS");
                            this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                        }
                        showTipMode(false);
                        this.childType = 15;
                        mSetShortTipData(15);
                    }
                    break;
                case 4287:
                    if (this.viewMode != 0 && !this.inputNuming) {
                        if (this.mModel_ShortTip_TV.getVisibility() == 0 && this.childType == 16) {
                            mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.35
                                @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                                public void effectChanged(View view, int i8) {
                                    FacRootMenu.this.mModel_ShortTip_TV.setText(i8 + "");
                                }
                            }, "HIFAC_GCUT_PLUS");
                        } else {
                            handleWBAutoTest("HIFAC_GCUT_PLUS");
                            this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                        }
                        showTipMode(false);
                        this.childType = 16;
                        mSetShortTipData(16);
                    }
                    break;
                case 4288:
                    if (this.viewMode != 0 && !this.inputNuming) {
                        if (this.mModel_ShortTip_TV.getVisibility() == 0 && this.childType == 17) {
                            mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.36
                                @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                                public void effectChanged(View view, int i8) {
                                    FacRootMenu.this.mModel_ShortTip_TV.setText(i8 + "");
                                }
                            }, "HIFAC_GCUT_MINUS");
                        } else {
                            handleWBAutoTest("HIFAC_GCUT_MINUS");
                            this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                        }
                        showTipMode(false);
                        this.childType = 17;
                        mSetShortTipData(17);
                    }
                    break;
                case 4289:
                    if (this.viewMode != 0 && !this.inputNuming) {
                        if (this.mModel_ShortTip_TV.getVisibility() == 0 && this.childType == 18) {
                            mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.37
                                @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                                public void effectChanged(View view, int i8) {
                                    FacRootMenu.this.mModel_ShortTip_TV.setText(i8 + "");
                                }
                            }, "HIFAC_BCUT_PLUS");
                        } else {
                            handleWBAutoTest("HIFAC_BCUT_PLUS");
                            this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                        }
                        showTipMode(false);
                        this.childType = 18;
                        mSetShortTipData(18);
                    }
                    break;
                case 4290:
                    if (this.viewMode != 0 && !this.inputNuming) {
                        if (this.mModel_ShortTip_TV.getVisibility() == 0 && this.childType == 19) {
                            mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.38
                                @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                                public void effectChanged(View view, int i8) {
                                    FacRootMenu.this.mModel_ShortTip_TV.setText(i8 + "");
                                }
                            }, "HIFAC_BCUT_MINUS");
                        } else {
                            handleWBAutoTest("HIFAC_BCUT_MINUS");
                            this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                        }
                        showTipMode(false);
                        this.childType = 19;
                        mSetShortTipData(19);
                    }
                    break;
                case 4298:
                    z2 = true;
                    if (this.viewMode != 0) {
                        if (mcf.getDefault("HIFAC_AGING") != 1) {
                            enterFactoryAging();
                            this.bprepareexitaging = false;
                        } else {
                            this.bprepareexitaging = true;
                        }
                    }
                    return z2;
                case 4299:
                    Log.i("Fac_FacRootMenu", " press FacKeyMap.KEYCODE_HISENSE_TEST_BOARD_AGING_CLOSE ");
                    if (this.viewMode != 0) {
                        z2 = true;
                        if (mcf.getDefault("HIFAC_AGING") == 1) {
                            factoryAging(false);
                            showBigMMode();
                        }
                        return z2;
                    }
                    break;
                case 4300:
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(" ");
                    MenuConfigManager menuConfigManager2 = mcf;
                    sb2.append(menuConfigManager2.updateStrVersion(menuConfigManager2.getVersionInfo()));
                    sb2.append(mcf.getDateInfo(true));
                    String string2 = sb2.toString();
                    String str4 = " " + mcf.getDateInfo(false);
                    String str5 = " " + mcf.getNewFangChuanHuoString();
                    String str6 = " " + mcf.getOrignalFangChuanHuoString();
                    String str7 = mcf.isFrcSupport() ? " " + mcf.getFRCVersion() : null;
                    String str8 = " " + mcf.getIPAddr();
                    String str9 = " " + mcf.getMacAddress();
                    String str10 = " " + mcf.getCountryName();
                    String str11 = " " + mcf.getlanguageName();
                    String str12 = " " + mcf.getLogoName();
                    String str13 = " " + mcf.getBTMacAddress();
                    String str14 = (FacPropertyDemo.HIFAC_MARKET_JP && mcf.getACASState() == FacParamManager.FAC_TVS_ACAS_STATE_VALID) ? "Inserted" : " Extracted";
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(" ");
                    String str15 = str14;
                    sb3.append(getCountOfUsbOrSdCardMounted(true));
                    sb3.append("/");
                    sb3.append(FacPropertyDemo.HIFAC_USB_COUNT);
                    String string3 = sb3.toString();
                    if (FacPropertyDemo.HIFAC_TF_CARD_SUPPORT) {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(" ");
                        sb4.append(getCountOfUsbOrSdCardMounted(false) == 0 ? "Removed" : "Inserted");
                        string = sb4.toString();
                    } else {
                        string = null;
                    }
                    String str16 = FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT ? " " + mcf.getDeviceID() : null;
                    String str17 = FacPropertyDemo.HIFAC_HDCP_1_4_KEY_SUPPORT ? " " + mcf.getHdcp14Key() : null;
                    String str18 = FacPropertyDemo.HIFAC_HDCP_2_X_KEY_SUPPORT ? " " + mcf.getHdcp2xKey() : null;
                    String str19 = FacPropertyDemo.HIFAC_PLAY_READY_KEY_SUPPORT ? " " + mcf.getPlayReadyKey() : null;
                    if (FacPropertyDemo.HIFAC_PLAY_READY_40KEY_SUPPORT) {
                        str19 = " " + mcf.getPlayReady40Key();
                    }
                    String str20 = FacPropertyDemo.HIFAC_PLAY_READY_25KEY_SUPPORT ? " " + mcf.getPlayReady25Key() : null;
                    String str21 = FacPropertyDemo.HIFAC_CI_PLUS_KEY_SUPPORT ? " " + mcf.getCIPlusKey() : null;
                    String str22 = FacPropertyDemo.HIFAC_CHINA_DRM_KEY_SUPPORT ? " " + mcf.getChinaDrmKey() : null;
                    String str23 = FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT ? " " + mcf.getNetflixKey() : null;
                    String str24 = FacPropertyDemo.HIFAC_ATTEST_KEY_SUPPORT ? " " + mcf.getAttestationKey() : null;
                    Bundle bundle = new Bundle();
                    String str25 = str24;
                    String str26 = str23;
                    bundle.putString("type", "info");
                    bundle.putString("machineName", string2);
                    bundle.putString("strVersionDate", str4);
                    bundle.putString("sn", mcf.getNewFangChuanHuoString());
                    bundle.putString("machineNo", str5);
                    bundle.putString("oriMachineNo", str6);
                    if (mcf.isFrcSupport()) {
                        bundle.putString("frcVersion", str7);
                    }
                    if (mcf.isTconSupport()) {
                        bundle.putString("tconVersion", mcf.getTconVersion());
                    }
                    if (mcf.isBluSupport()) {
                        bundle.putString("bluVersion", mcf.getBacklightDriverVersion());
                    }
                    bundle.putString("strIpAddrString", str8);
                    bundle.putString("strMacAddress", str9);
                    bundle.putString("strCountry", str10);
                    bundle.putString("strLanguage", str11);
                    bundle.putString("strLogo", str12);
                    bundle.putString("strBlueMacAddr", str13);
                    if (FacPropertyDemo.HIFAC_CI_CARD_SUPPORT) {
                        bundle.putString("ciCardStatus", null);
                    }
                    bundle.putString("caCardStatus", str15);
                    bundle.putString("usbPortStatus", string3);
                    if (FacPropertyDemo.HIFAC_MARKET_JP && mcf.isHaveHDD()) {
                        bundle.putString("hddStatus", "Inserted");
                    }
                    if (FacPropertyDemo.HIFAC_TF_CARD_SUPPORT) {
                        bundle.putString("sdCardStatus", string);
                    }
                    if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT) {
                        bundle.putString("wideVineKey", str16);
                    }
                    if (FacPropertyDemo.HIFAC_HDCP_1_4_KEY_SUPPORT) {
                        bundle.putString("hdcp14key", str17);
                    }
                    if (FacPropertyDemo.HIFAC_HDCP_2_X_KEY_SUPPORT) {
                        bundle.putString("hdcp22key", str18);
                    }
                    if (FacPropertyDemo.HIFAC_PLAY_READY_KEY_SUPPORT) {
                        str2 = str19;
                        bundle.putString("playreadykey", str2);
                        bundle.putString("playready25key", str20);
                    } else {
                        str2 = str19;
                    }
                    if (FacPropertyDemo.HIFAC_PLAY_READY_40KEY_SUPPORT) {
                        bundle.putString("playreadykey", str2);
                    }
                    if (FacPropertyDemo.HIFAC_CI_PLUS_KEY_SUPPORT) {
                        bundle.putString("cipluskey", str21);
                    }
                    if (FacPropertyDemo.HIFAC_CHINA_DRM_KEY_SUPPORT) {
                        bundle.putString("chinaDrmKey", str22);
                    }
                    if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
                        bundle.putString("mgk", str26);
                    }
                    if (FacPropertyDemo.HIFAC_ATTEST_KEY_SUPPORT) {
                        bundle.putString("attest", str25);
                    }
                    if (mcf.isSupportQDAKey()) {
                        bundle.putString("QDAKey", mcf.getQDAKey());
                    }
                    if (FacPropertyDemo.HIFAC_MARKET_JP) {
                        bundle.putString("marlin", mcf.getMarlinKey());
                        bundle.putString("dtcpip", mcf.getDtcpIpKey());
                        bundle.putString("pvr", mcf.getPVRKey());
                    }
                    Intent intent2 = new Intent(this, (Class<?>) AllMachineInfo.class);
                    intent2.putExtras(bundle);
                    startActivityForResult(intent2, 0);
                    break;
                case 4301:
                    if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                        Log.i("Fac_FacRootMenu", "set autotest cable");
                        mNavIntegration.setTunerMode(1);
                        showTipMode(true);
                        this.childType = 29;
                        mSetShortTipData(29);
                        sendMessageDelayed(2);
                    } else if (FacPropertyDemo.HIFAC_MARKET_JP) {
                        mcf.setTvsTvTuner(FacTvsFunctionUtil.FAC_TVS_TUNE_TYPE_CS);
                    }
                    return true;
                case 4302:
                    if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                        Log.i("Fac_FacRootMenu", "set autotest air");
                        mNavIntegration.setTunerMode(0);
                        showTipMode(true);
                        this.childType = 29;
                        mSetShortTipData(29);
                        sendMessageDelayed(2);
                    }
                    return true;
                case 4303:
                    if (FacPropertyDemo.HIFAC_MARKET_JP) {
                        mcf.setTvsTvTuner(FacTvsFunctionUtil.FAC_TVS_TUNE_TYPE_BS);
                    }
                    return true;
                case 4304:
                    if (!mNavIntegration.isCurrenSourceDTV()) {
                        mcf.displayHisToast(getResources().getString(R.string.str_dtv_operate_warning));
                        return true;
                    }
                    this.mFacFunction_Adapter.switchDTVrouteToDtmb();
                    showTipMode(true);
                    showShortTipLL(R.string.str_factorymenu_dtmb);
                    sendMessageDelayed(2);
                    return true;
                case 4305:
                    this.mFacFunction_Adapter.setTunerMode(0);
                    showSourceInfoBar();
                    return true;
                case 4306:
                    if (!doesVideoFileExistInUsb(false)) {
                        String str27 = FacParamManager.FAC_DMP_FILE_NAME;
                        this.VIDEO_NAME_TO_PLAY = str27;
                        if (str27.equals("4K_VIDEO_TEST")) {
                            mcf.displayHisToast(getResources().getString(R.string.str_4k_video_not_exit));
                        } else if (this.VIDEO_NAME_TO_PLAY.equals("8K_VIDEO_TEST")) {
                            mcf.displayHisToast(getResources().getString(R.string.str_8k_video_not_exit));
                        } else {
                            mcf.displayHisToast(getResources().getString(R.string.str_video_not_exit));
                        }
                        this.isLongPressedDmp = false;
                        return true;
                    }
                    mcf.setMtkSourceStatus(false);
                    this.mTvStoped = true;
                    Log.i("Fac_FacRootMenu", "stop tv");
                    Intent intent3 = new Intent(this, (Class<?>) MediaPlayerActivity.class);
                    this.isLongPressedDmp = false;
                    intent3.addFlags(536870912);
                    intent3.addFlags(268435456);
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("videoFileDirctory", this.videoFileDirctory);
                    bundle2.putString("videoFileName", this.videoFileName);
                    intent3.putExtras(bundle2);
                    startActivityForResult(intent3, 9);
                    return true;
                case 4357:
                    showBigMMode();
                    return true;
                case 4362:
                    mcf.setWBPattern(0);
                    return true;
                case 4363:
                    mcf.setWBPattern(1);
                    return true;
                case 4364:
                    mcf.setWBPattern(2);
                    return true;
                case 4365:
                    mcf.setWBPattern(3);
                    return true;
                case 4366:
                    mcf.setWBPattern(4);
                    return true;
                case 4367:
                    mcf.setWBPattern(5);
                    return true;
                case 4368:
                    mcf.setWBPattern(6);
                    return true;
                case 4369:
                    mcf.setWBPattern(7);
                    return true;
                case 4370:
                    mcf.setWBPattern(8);
                    return true;
                case 4371:
                    mcf.setWBPattern(9);
                    return true;
                case 4372:
                    mcf.setWBPattern(10);
                    return true;
                case 4373:
                    mcf.setWBPattern(11);
                    return true;
                case 4374:
                    mcf.setWBPattern(12);
                    return true;
                case 4375:
                    mcf.setWBPattern(13);
                    return true;
                case 4376:
                    hideBigMMode();
                    SystemProperties.set("sys.vt.factory.wb_pattern_test", "true");
                    mcf.setWBPattern(20);
                    return true;
                case 4377:
                    mcf.setWBPattern(21);
                    SystemProperties.set("sys.vt.factory.wb_pattern_test", "false");
                    showBigMMode();
                    return true;
                case 4383:
                    if (!FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                        Log.i("Fac_FacRootMenu", "set autotest air");
                        FacPropertyDemo.SourceEnum currentInputSourceEnum = mcf.getCurrentInputSourceEnum();
                        FacPropertyDemo.SourceEnum sourceEnum = FacPropertyDemo.SourceEnum.TV;
                        if (currentInputSourceEnum != sourceEnum) {
                            mcf.switchSource(sourceEnum);
                            try {
                                Thread.sleep(200L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        mNavIntegration.setTunerMode(0);
                        showTipMode(true);
                        this.childType = 29;
                        mSetShortTipData(29);
                        sendMessageDelayed(2);
                        try {
                            Thread.sleep(500L);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        displayRightTopInfoBar();
                        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
                            String currentSourceVideoInfo = mNavIntegration.getCurrentSourceVideoInfo();
                            Log.i("Fac_FacRootMenu", "getCurrentSourceVideoInfo:" + currentSourceVideoInfo);
                            this.mInfoNameTV.setText("");
                            if (currentSourceVideoInfo != null && !currentSourceVideoInfo.isEmpty()) {
                                this.mInfoNameTV.setText(currentSourceVideoInfo);
                            } else {
                                this.mhHandler.removeMessages(26);
                                this.mhHandler.sendEmptyMessageDelayed(26, 1500L);
                            }
                        }
                        break;
                    }
                    return true;
                case 4384:
                    if (!FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                        Log.i("Fac_FacRootMenu", "set autotest cable");
                        FacPropertyDemo.SourceEnum currentInputSourceEnum2 = mcf.getCurrentInputSourceEnum();
                        FacPropertyDemo.SourceEnum sourceEnum2 = FacPropertyDemo.SourceEnum.TV;
                        if (currentInputSourceEnum2 != sourceEnum2) {
                            mcf.switchSource(sourceEnum2);
                            try {
                                Thread.sleep(200L);
                            } catch (InterruptedException e3) {
                                e3.printStackTrace();
                            }
                        }
                        mNavIntegration.setTunerMode(1);
                        showTipMode(true);
                        this.childType = 29;
                        mSetShortTipData(29);
                        sendMessageDelayed(2);
                        try {
                            Thread.sleep(500L);
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        displayRightTopInfoBar();
                        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
                            String currentSourceVideoInfo2 = mNavIntegration.getCurrentSourceVideoInfo();
                            Log.i("Fac_FacRootMenu", "getCurrentSourceVideoInfo:" + currentSourceVideoInfo2);
                            this.mInfoNameTV.setText("");
                            if (currentSourceVideoInfo2 != null && !currentSourceVideoInfo2.isEmpty()) {
                                this.mInfoNameTV.setText(currentSourceVideoInfo2);
                            } else {
                                this.mhHandler.removeMessages(26);
                                this.mhHandler.sendEmptyMessageDelayed(26, 1500L);
                            }
                        }
                        break;
                    }
                    return true;
                case 4385:
                    showTipMode(true);
                    this.childType = 3;
                    mSetShortTipData(3);
                    sendMessageDelayed(2);
                    return true;
                case 4387:
                    if (FacPropertyDemo.HIFAC_MARKET_JP) {
                        mcf.setTvsTvTuner(FacTvsFunctionUtil.FAC_TVS_TUNE_TYPE_TDJ15);
                    }
                    return true;
                case 4388:
                    if (FacPropertyDemo.HIFAC_MARKET_JP) {
                        mcf.setTvsTvTuner(FacTvsFunctionUtil.FAC_TVS_TUNE_TYPE_TDJ62);
                    }
                    return true;
                case 4391:
                    if (!mcf.isHaveTuner()) {
                        return true;
                    }
                    new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.9
                        @Override // java.lang.Runnable
                        public void run() {
                            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                                FacRootMenu.mcf.switchSource(FacPropertyDemo.SourceEnum.TV);
                                FacRootMenu.mcf.setTvsTvTuner(FacTvsFunctionUtil.FAC_TVS_TUNE_TYPE_TDJ62);
                            } else {
                                FacRootMenu.mNavIntegration.iSetSourcetoNextTV();
                            }
                            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.9.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    FacRootMenu.this.showSourceInfoBar();
                                }
                            });
                        }
                    }).start();
                    return true;
                case 4397:
                    if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
                        this.mFacFunction_Adapter.setTunerModeforATSC(0);
                        this.mFacFunction_Adapter.changePreciseChannelForATSC(35, 12);
                        showSourceInfoBar();
                    }
                    return true;
                case 5110:
                case 5112:
                    Log.i("Fac_FacRootMenu", "start show serial demura picture");
                    if (!this.mDemuraUtil.setDemuraPictureByKeyCode(i)) {
                        DemuraUtil.setDemuraPropStatus("false");
                        showBigMMode();
                    }
                    return true;
                case 112233:
                    if (FacPropertyDemo.HIFAC_MARKET_JP) {
                        if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_650) {
                            this.mModel_ShortTip_TV.setText(mNavIntegration.iGetEffect("HIFAC_TVS_BALANCE_LIKE_650"));
                        } else if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_550) {
                            this.mModel_ShortTip_TV.setText(mNavIntegration.iGetEffect("HIFAC_TVS_BALANCE_LIKE_550"));
                        } else if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_850) {
                            this.mModel_ShortTip_TV.setText(mNavIntegration.iGetEffect("HIFAC_TVS_BALANCE_LIKE_850"));
                        }
                    } else if (mcf.getSubWooferTotal() == 1) {
                        this.mModel_ShortTip_TV.setText(mNavIntegration.iGetEffect("HIFAC_BALANCE_DOUBLE_AMP"));
                    } else if (mcf.getSubWooferTotal() == 2) {
                        this.mModel_ShortTip_TV.setText(mNavIntegration.iGetEffect("HIFAC_BALANCE_MULTI_AMP"));
                    } else if (mcf.getSubWooferTotal() > 2) {
                        this.mModel_ShortTip_TV.setText(mNavIntegration.iGetEffect("HIFAC_BALANCE_UNIV_MULTI_AMP"));
                    } else {
                        this.mModel_ShortTip_TV.setText(mNavIntegration.iGetEffect("HIFAC_BALANCE"));
                    }
                    this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                    showTipModeForBalance(true);
                    this.childType = 6;
                    mSetShortTipData(6);
                    return true;
                case 112234:
                    showSourceInfoBar();
                    return true;
                case 112235:
                    Log.i("Fac_FacRootMenu", "setVcomType(1)");
                    if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                        mcf.setVcomType(1);
                        Log.i("Fac_FacRootMenu", "finish setVcomType(1)");
                        setPatternAlreaySet();
                    }
                    return true;
                case 112236:
                    Log.i("Fac_FacRootMenu", "setVcomType(2)");
                    if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                        mcf.setVcomType(2);
                        Log.i("Fac_FacRootMenu", "finish setVcomType(2)");
                        setPatternAlreaySet();
                    }
                    return true;
                case 112237:
                    hideAll();
                    return true;
                case 112238:
                    showBigMMode();
                    return true;
                default:
                    switch (i) {
                        case 7:
                            if (!mIsAlreadySet && FacPropertyDemo.HIFAC_ATSC30_SUPPORT && i == 7) {
                                this.mFacFunction_Adapter.setTunerModeforATSC(0);
                                this.mFacFunction_Adapter.changeChannelATSC(33, 0);
                                showSimpleInfoBar();
                            }
                            return true;
                        case 8:
                            Log.i("Fac_FacRootMenu", "****KEYCODE_1  f3KeyIsPressed" + this.f3KeyIsPressed);
                            if (this.f3KeyIsPressed) {
                                this.mIsKey1Pressed = true;
                                this.mhHandler.removeMessages(34);
                                sendMessageDelayed(34, 2000);
                            } else {
                                if (this.mIsMKeyPressed || !FacPropertyDemo.HIFAC_MARKET_JP) {
                                    i3 = 8;
                                } else {
                                    i3 = 8;
                                    if (i == 8) {
                                        mcf.setTvsTvTuner(FacTvsFunctionUtil.FAC_TVS_TUNE_TYPE_TDJ15);
                                    }
                                }
                                if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT && i == i3 && this.f4KeyIsPressed) {
                                    if (!mIsAlreadySet) {
                                        mcf.setVcomType(1);
                                        setPatternAlreaySet();
                                    }
                                }
                            }
                            return true;
                        case 9:
                            Log.i("Fac_FacRootMenu", "****KEYCODE_2  f3KeyIsPressed" + this.f3KeyIsPressed);
                            if (this.f3KeyIsPressed) {
                                this.mIsKey2Pressed = true;
                                this.mhHandler.removeMessages(35);
                                sendMessageDelayed(35, 2000);
                            } else if (!this.mIsMKeyPressed && FacPropertyDemo.HIFAC_MARKET_JP) {
                                mcf.setTvsTvTuner(FacTvsFunctionUtil.FAC_TVS_TUNE_TYPE_TDJ62);
                            } else if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT && i == 9 && this.f4KeyIsPressed) {
                                if (!mIsAlreadySet) {
                                    mcf.setVcomType(2);
                                    setPatternAlreaySet();
                                }
                            }
                            return true;
                        case 10:
                            if (!this.mIsMKeyPressed && FacPropertyDemo.HIFAC_MARKET_JP) {
                                mcf.setTvsTvTuner(FacTvsFunctionUtil.FAC_TVS_TUNE_TYPE_BS);
                            }
                            return true;
                        case 11:
                            if (!this.mIsMKeyPressed && FacPropertyDemo.HIFAC_MARKET_JP) {
                                mcf.setTvsTvTuner(FacTvsFunctionUtil.FAC_TVS_TUNE_TYPE_CS);
                            }
                            return true;
                        case 12:
                            if (!this.mIsMKeyPressed && FacPropertyDemo.HIFAC_MARKET_JP) {
                                mcf.setTvsTvTuner(FacTvsFunctionUtil.FAC_TVS_TUNE_TYPE_BS4K);
                            }
                            return true;
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                            if (!this.mIsMKeyPressed && (!FacPropertyDemo.HIFAC_MARKET_JP || (i != 14 && i != 16))) {
                                if (this.bLogoKeyPressed && FacPropertyDemo.HIFAC_MARKET_SA) {
                                    mcf.setLogoForLogoAndDigitalPressed(i - 7);
                                } else if (this.viewMode != 0 && !this.inputNuming) {
                                    if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0 && i == 7 && mIsAlreadySet) {
                                        Log.i("Fac_FacRootMenu", "it's in vcom mode, not save vcom value");
                                        setPatternNotSetYet();
                                    } else {
                                        this.inputNuming = true;
                                        if (!mNavIntegration.isCurrenSourceTV()) {
                                            this.inputNuming = false;
                                        } else {
                                            hideBigMIcon();
                                            this.mModel_ShortTip_LL.setVisibility(8);
                                            if (this.mLayout_Bottom_Two_Colmun_LL.getVisibility() == 0) {
                                                this.mLayout_Bottom_Two_Colmun_LL.setVisibility(8);
                                            }
                                            inputChannelNum(i);
                                        }
                                    }
                                } else if (this.viewMode != 0) {
                                    if (!mNavIntegration.isCurrenSourceTV()) {
                                        this.inputNuming = false;
                                    } else {
                                        this.inputNuming = true;
                                        hideBigMIcon();
                                        inputChannelNum(i);
                                    }
                                }
                            }
                            return true;
                        default:
                            switch (i) {
                                case 21:
                                    if (mcf.isTestPattern()) {
                                        adjustPatternMode(false);
                                    } else if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0 && mIsAlreadySet) {
                                        if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                            int vcomType = mcf.getVcomType();
                                            int vcomVoltage2 = mcf.getVcomVoltage2(vcomType) - FacPropertyDemo.HIFAC_STEP_VOM_VOLTAGE;
                                            Log.i("Fac_FacRootMenu", "facrootMenu vcomCurrentValue is " + vcomVoltage2);
                                            int i8 = FacPropertyDemo.HIFAC_MIN_VCOM_SPAN;
                                            if (vcomVoltage2 <= i8) {
                                                vcomVoltage2 = i8;
                                            }
                                            mcf.setVcomVoltage2(vcomVoltage2, vcomType);
                                        } else {
                                            int vcomVoltage = mcf.getVcomVoltage();
                                            Log.i("Fac_FacRootMenu", "vcom value is " + vcomVoltage);
                                            int i9 = vcomVoltage - FacPropertyDemo.HIFAC_STEP_VOM_VOLTAGE;
                                            Log.i("Fac_FacRootMenu", "facrootMenu vcomCurrentValue is " + i9);
                                            int i10 = FacPropertyDemo.HIFAC_MIN_VCOM_SPAN;
                                            if (i9 <= i10) {
                                                i9 = i10;
                                            }
                                            mcf.setVcomVoltage(i9);
                                        }
                                    } else {
                                        Intent intent4 = new Intent();
                                        intent4.setAction("tv.policy.SYSTEM_KEY");
                                        intent4.putExtra("android.intent.extra.KEY_EVENT", 25);
                                        intent4.addFlags(268435456);
                                        Log.i("Fac_FacRootMenu", "sendBroadcast " + intent4);
                                        sendOrderedBroadcast(intent4, null);
                                    }
                                    return true;
                                case 22:
                                    if (mcf.isTestPattern()) {
                                        adjustPatternMode(true);
                                    } else if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0 && mIsAlreadySet) {
                                        if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                            int vcomType2 = mcf.getVcomType();
                                            int vcomVoltage3 = mcf.getVcomVoltage2(vcomType2) + FacPropertyDemo.HIFAC_STEP_VOM_VOLTAGE;
                                            Log.i("Fac_FacRootMenu", "facrootMenu vcomCurrentValue is " + vcomVoltage3);
                                            if (vcomType2 != 2 ? vcomVoltage3 > (i4 = FacPropertyDemo.HIFAC_MAX_VCOM_SPAN) : vcomVoltage3 > (i4 = FacPropertyDemo.HIFAC_MAX_VCOM_DOUBLE_CELL_FHD_SPAN)) {
                                                vcomVoltage3 = i4;
                                            }
                                            mcf.setVcomVoltage2(vcomVoltage3, vcomType2);
                                        } else {
                                            int vcomVoltage4 = mcf.getVcomVoltage();
                                            Log.i("Fac_FacRootMenu", "vcom prev value is " + vcomVoltage4);
                                            int i11 = vcomVoltage4 + FacPropertyDemo.HIFAC_STEP_VOM_VOLTAGE;
                                            Log.i("Fac_FacRootMenu", "facrootMenu vcomCurrentValue is " + i11);
                                            int i12 = FacPropertyDemo.HIFAC_MAX_VCOM_SPAN;
                                            if (i11 > i12) {
                                                i11 = i12;
                                            }
                                            mcf.setVcomVoltage(i11);
                                        }
                                    } else {
                                        Intent intent5 = new Intent();
                                        intent5.setAction("tv.policy.SYSTEM_KEY");
                                        intent5.putExtra("android.intent.extra.KEY_EVENT", 24);
                                        intent5.addFlags(268435456);
                                        Log.i("Fac_FacRootMenu", "sendBroadcast " + intent5);
                                        sendOrderedBroadcast(intent5, null);
                                    }
                                    return true;
                                case 23:
                                    if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                                        Log.i("Fac_FacRootMenu", "it's in vcom mode, save vcom value");
                                        mcf.getVcomVoltage();
                                        setPatternNotSetYet();
                                        return true;
                                    }
                                    break;
                                case 24:
                                case 25:
                                    return false;
                                default:
                                    switch (i) {
                                        case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_OFF /* 144 */:
                                        case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STARTED /* 145 */:
                                        case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STOPPED /* 146 */:
                                        case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_PAUSED /* 147 */:
                                        case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_SOURCE_READY /* 148 */:
                                        case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_ES_LIST /* 149 */:
                                        case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_STOP /* 150 */:
                                        case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_PIN_CAP_CHANGE /* 151 */:
                                        case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_ES_AUTH_UPDATED /* 152 */:
                                        case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REACH_BOUND /* 153 */:
                                            inputFangChuanHuoNumber(i);
                                            break;
                                        default:
                                            switch (i) {
                                                case 4204:
                                                    this.mFangChuanHuo_Flag = FangChuanHuoStatus.FANGCHUANHUO_START;
                                                    if (!"hisi".equalsIgnoreCase(FacPropertyDemo.HIFAC_VENDOR)) {
                                                        try {
                                                            this.imm.setInputMethod(null, "com.android.inputmethod.latin/.LatinIME");
                                                            Log.i("Fac_FacRootMenu", "set InputMethod to com.android.inputmethod.latin/.LatinIME");
                                                        } catch (Exception e5) {
                                                            e5.printStackTrace();
                                                        }
                                                        break;
                                                    }
                                                    sendMessageDelayed(8, 3000);
                                                    return true;
                                                case 4205:
                                                    processSerialNumberResult();
                                                    return true;
                                                case 4206:
                                                    if (this.bLogoKeyPressed && FacPropertyDemo.HIFAC_MARKET_SA) {
                                                        this.mFacFunction_Adapter.setSAMarketFacCheckFlag();
                                                        mcf.displayHisToast("Start Main Board Test...");
                                                    } else {
                                                        if (FacPropertyDemo.HIFAC_MARKET_JP) {
                                                            showTvsF1ResultDialog();
                                                        }
                                                        if (FacPropertyDemo.HIFAC_CI_CARD_SUPPORT) {
                                                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                                            if (this.mFacFunction_Adapter.getCICardStatus()) {
                                                                builder.setMessage(((Object) getResources().getText(R.string.str_facmenu_ci_card_name)) + this.mFacFunction_Adapter.getCICardName());
                                                            } else {
                                                                builder.setMessage(getResources().getText(R.string.str_facmenu_ci_card_no_exist));
                                                            }
                                                            builder.setPositiveButton(R.string.str_facmenu_resetmac_yes, new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.FacRootMenu.26
                                                                @Override // android.content.DialogInterface.OnClickListener
                                                                public void onClick(DialogInterface dialogInterface, int i13) {
                                                                }
                                                            });
                                                            builder.create().show();
                                                        }
                                                    }
                                                    return true;
                                                case 4207:
                                                    if (FacPropertyDemo.HIFAC_IS_LASER4K) {
                                                        startActivityForResult(new Intent(this, (Class<?>) AlarmAndFanSpeedActivity.class), 0);
                                                    } else {
                                                        if (FacPropertyDemo.HIFAC_MARKET_JP) {
                                                            if (mcf.getLastSource() == TVS_LASR_SOURCE_INPUT) {
                                                                Toast.makeText(this, "unable to Tuner Check in non TV channel", 1).show();
                                                            } else {
                                                                new FacTVSTVSignalInfoDialog(this, 1, this.mhHandler).show();
                                                            }
                                                        }
                                                        if (mcf.getLogoName().equalsIgnoreCase("Sunbrite") || mcf.getLogoName().equalsIgnoreCase("snb")) {
                                                            mcf.isSetServiceTag(this);
                                                        }
                                                    }
                                                    return true;
                                                case 4208:
                                                    Log.i("Fac_FacRootMenu", "****KEYCODE_HISIR_F3  f3KeyIsPressed" + this.f3KeyIsPressed);
                                                    if (!this.f3KeyIsPressed) {
                                                        sendMessageDelayed(36, 1000);
                                                    }
                                                    this.f3KeyIsPressed = true;
                                                    this.mhHandler.removeMessages(33);
                                                    sendMessageDelayed(33, 2000);
                                                    return true;
                                                case 4209:
                                                    Log.i("Fac_FacRootMenu", "****KEYCODE_HISIR_F4");
                                                    Log.i("Fac_FacRootMenu", "****KEYCODE_HISIR_F4  bLogoKeyPressed = " + this.bLogoKeyPressed);
                                                    if (this.bLogoKeyPressed) {
                                                        this.mhHandler.removeMessages(11);
                                                        this.bLogoKeyPressed = false;
                                                        Intent intent6 = new Intent(this, (Class<?>) LogShowActivity.class);
                                                        if (FacPropertyDemo.HIFAC_MIC_SUPPORT) {
                                                            Bundle bundle3 = new Bundle();
                                                            bundle3.putString("mZDMicTestLog", this.mZDMicTestLog);
                                                            bundle3.putString("mMicTestLog", this.mMicTestLog);
                                                            intent6.putExtras(bundle3);
                                                        }
                                                        startActivity(intent6);
                                                    } else if (FacPropertyDemo.HIFAC_IS_LASER4K) {
                                                        if (!FacPropertyDemo.HIFAC_LASER_IS_L9) {
                                                            startActivity(new Intent(this, (Class<?>) CWActivity.class));
                                                        }
                                                    } else if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                                                        if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
                                                            this.f4KeyIsPressed = true;
                                                            this.mhHandler.removeMessages(29);
                                                            sendMessageDelayed(29, 2000);
                                                        } else if (!mIsAlreadySet) {
                                                            setPatternAlreaySet();
                                                        }
                                                    } else {
                                                        Log.i("Fac_FacRootMenu", "****KEYCODE_HISIR_F4  1111");
                                                        if (!mIsAlreadySet) {
                                                            setPatternAlreaySet();
                                                        }
                                                    }
                                                    return true;
                                                case 4210:
                                                case 4211:
                                                    if (this.bLogoKeyPressed && FacPropertyDemo.HIFAC_MARKET_SA) {
                                                        this.mhHandler.removeMessages(11);
                                                        this.bLogoKeyPressed = false;
                                                        boolean zTestWhetherKeyIsOK2 = testWhetherKeyIsOK();
                                                        boolean zIsMacAddressDefaultOrIllegal2 = isMacAddressDefaultOrIllegal();
                                                        Log.i("Fac_FacRootMenu", "isKeyOk = " + zTestWhetherKeyIsOK2 + " ;isMacNotOk = " + zIsMacAddressDefaultOrIllegal2);
                                                        if (!zIsMacAddressDefaultOrIllegal2 && zTestWhetherKeyIsOK2) {
                                                            this.mFacFunction_Adapter.setMacKeysStatusToLog();
                                                        }
                                                        startActivity(new Intent(this, (Class<?>) FacCheckLogShowActivity.class));
                                                    } else {
                                                        if (!mcf.isHaveTuner()) {
                                                            return true;
                                                        }
                                                        if (this.viewMode != 0 && !this.inputNuming) {
                                                            Log.i("Fac_FacRootMenu", "F5 set tuner mode");
                                                            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                                                                int i13 = (this.mTvsF5Index + 5) % 5;
                                                                Log.i("Fac_FacRootMenu", "F5 set tuner mode currentTune = " + i13);
                                                                mcf.setTvsTvTuner(FacTvsFunctionUtil.FAC_TVS_TUNE_TYPE_TDJ15 + i13);
                                                                this.mTvsF5Index = this.mTvsF5Index + 1;
                                                                displayLeftTopInfoBar();
                                                                try {
                                                                    Thread.sleep(500L);
                                                                } catch (Exception e6) {
                                                                    e6.printStackTrace();
                                                                }
                                                                displayRightTopInfoBar();
                                                            } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                                                                mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.22
                                                                    @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                                                                    public void effectChanged(View view, int i14) {
                                                                        FacRootMenu.this.setTunerModeString(i14);
                                                                    }
                                                                }, "HIFAC_TUNERMODE");
                                                                displayLeftTopInfoBar();
                                                                try {
                                                                    Thread.sleep(500L);
                                                                } catch (Exception e7) {
                                                                    e7.printStackTrace();
                                                                }
                                                                displayRightTopInfoBar();
                                                            } else if (!mcf.getCurrentSourceName().equalsIgnoreCase(MtkTvInputSourceBase.INPUT_TYPE_TV)) {
                                                                Toast.makeText(this, "Please press TV button first !!!!!!.", 1).show();
                                                            } else if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
                                                                Log.i("Fac_FacRootMenu", "mAtscF5Index = " + this.mAtscF5Index);
                                                                int i14 = (this.mAtscF5Index + 3) % 3;
                                                                if (i14 == 0) {
                                                                    this.mFacFunction_Adapter.setTunerModeforATSC(1);
                                                                    this.mFacFunction_Adapter.changeChannelATSC(39, 1);
                                                                    i5 = 1;
                                                                } else if (1 == i14) {
                                                                    this.mFacFunction_Adapter.setTunerModeforATSC(0);
                                                                    this.mFacFunction_Adapter.changePreciseChannelForATSC(5, 1);
                                                                } else if (2 == i14) {
                                                                    Log.i("Fac_FacRootMenu", "set surface for atsc3.0");
                                                                    TvPlayerClient tvPlayerClient = new TvPlayerClient(mFacRootMenu);
                                                                    this.client = tvPlayerClient;
                                                                    tvPlayerClient.setSurface(this.mSurface);
                                                                    this.mFacFunction_Adapter.setTunerModeforATSC(0);
                                                                    this.mFacFunction_Adapter.changePreciseChannelForATSC(35, 12);
                                                                }
                                                                setTunerModeString(i5);
                                                                displayLeftTopInfoBar();
                                                                try {
                                                                    Thread.sleep(500L);
                                                                } catch (Exception e8) {
                                                                    e8.printStackTrace();
                                                                }
                                                                displayRightTopInfoBar();
                                                                String currentSourceVideoInfo3 = mNavIntegration.getCurrentSourceVideoInfo();
                                                                Log.i("Fac_FacRootMenu", "getCurrentSourceVideoInfo:" + currentSourceVideoInfo3);
                                                                this.mInfoNameTV.setText("");
                                                                if (currentSourceVideoInfo3 != null && !currentSourceVideoInfo3.isEmpty()) {
                                                                    this.mInfoNameTV.setText(currentSourceVideoInfo3);
                                                                } else {
                                                                    this.mhHandler.removeMessages(26);
                                                                    this.mhHandler.sendEmptyMessageDelayed(26, 1500L);
                                                                }
                                                                this.mAtscF5Index++;
                                                            } else if (this.mModel_ShortTip_TV.getVisibility() == 0 && this.childType == 29) {
                                                                mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.23
                                                                    @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                                                                    public void effectChanged(View view, int i15) {
                                                                        FacRootMenu.this.setTunerModeString(i15);
                                                                    }
                                                                }, "HIFAC_TUNERMODE");
                                                                displayLeftTopInfoBar();
                                                                try {
                                                                    Thread.sleep(500L);
                                                                } catch (Exception e9) {
                                                                    e9.printStackTrace();
                                                                }
                                                                displayRightTopInfoBar();
                                                            } else {
                                                                setTunerModeString(mNavIntegration.iGetEffect("HIFAC_TUNERMODE"));
                                                                displayLeftTopInfoBar();
                                                                try {
                                                                    Thread.sleep(500L);
                                                                } catch (Exception e10) {
                                                                    e10.printStackTrace();
                                                                }
                                                                displayRightTopInfoBar();
                                                                String currentSourceVideoInfo4 = mNavIntegration.getCurrentSourceVideoInfo();
                                                                Log.i("Fac_FacRootMenu", "getCurrentSourceVideoInfo:" + currentSourceVideoInfo4);
                                                                this.mInfoNameTV.setText("");
                                                                if (currentSourceVideoInfo4 != null && !currentSourceVideoInfo4.isEmpty()) {
                                                                    this.mInfoNameTV.setText(currentSourceVideoInfo4);
                                                                } else {
                                                                    this.mhHandler.removeMessages(26);
                                                                    this.mhHandler.sendEmptyMessageDelayed(26, 1500L);
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    return true;
                                                case 4212:
                                                    Log.i("Fac_FacRootMenu", "**KEYCODE_HISIR_WIFI**");
                                                    if (mcf.isWIFISupported()) {
                                                        if (FacPropertyDemo.HIFAC_F7_MODEL == FacPropertyDemo.F7_Wifi_Bluetooth.F7_WIFI) {
                                                            Log.i("Fac_FacRootMenu", "**KEYCODE_HISIR_WIFI**");
                                                            showDialog(0);
                                                        } else if (FacPropertyDemo.HIFAC_F7_MODEL == FacPropertyDemo.F7_Wifi_Bluetooth.F7_WIFI_AND_BLUETOOTH) {
                                                            Log.i("Fac_FacRootMenu", "**KEYCODE_HISIR_WIFI_AND_BLUETOOTH**");
                                                            showDialog(1);
                                                        } else {
                                                            Log.i("Fac_FacRootMenu", "wifi button pressed");
                                                        }
                                                    }
                                                    return true;
                                                case 4213:
                                                    Log.i("Fac_FacRootMenu", "OK key is pressed, view mode is " + this.viewMode + "inputNuming is " + this.inputNuming);
                                                    if (!this.mFacFunction_Adapter.isHaveFte()) {
                                                        if (!mcf.isStartInFacMode()) {
                                                            mcf.displayHisToast("Reboot first!!!!");
                                                        } else {
                                                            SystemProperties.set("ctl.start", "dirty_work_misc_reset");
                                                        }
                                                    } else {
                                                        Log.i("Fac_FacRootMenu", "OK key is pressed, FTE is OK ");
                                                    }
                                                    this.inputNuming = false;
                                                    this.viewMode = 2;
                                                    if (2 != 0) {
                                                        if (this.bLogoKeyPressed) {
                                                            Log.i("Fac_FacRootMenu", "fail progress is " + getSharedPreferences("FactoryOk_failed", 0).getInt("FactoryOk_failed", 0));
                                                            Intent intent7 = new Intent(this, (Class<?>) DebugInfoActivity.class);
                                                            intent7.putExtra("fileName", "factoryokresult.txt");
                                                            startActivity(intent7);
                                                        } else if (this.mModel_ShortTip_TV.getVisibility() == 0) {
                                                            if (!this.mFacFunction_Adapter.factoryOk_checkAutoGamma()) {
                                                                this.mModel_ShortTip_TV.setText("Autogamma NG");
                                                                this.mModel_ShortTip_TV.setTextColor(-65536);
                                                                this.mModel_ShortTip_TV.setGravity(17);
                                                                this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                                                                showTipMode(true);
                                                                this.mhHandler.removeMessages(2);
                                                                this.mhHandler.removeMessages(0);
                                                            } else {
                                                                boolean zTestWhetherKeyIsOK3 = testWhetherKeyIsOK();
                                                                zIsMacAddressDefaultOrIllegal = isMacAddressDefaultOrIllegal();
                                                                if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
                                                                    zMgkNameIsRight = mcf.mgkNameIsRight();
                                                                } else {
                                                                    zMgkNameIsRight = true;
                                                                }
                                                                zIsMicSwitchOn = mcf.isMicSwitchOn();
                                                                if (FacPropertyDemo.HIFAC_MARKET_SA) {
                                                                    zIsHaveClientType = mcf.isHaveClientType();
                                                                } else {
                                                                    zIsHaveClientType = true;
                                                                }
                                                                if (FacPropertyDemo.HIFAC_IS_OLED) {
                                                                    z3 = true;
                                                                } else {
                                                                    z3 = true;
                                                                }
                                                                if (FacPropertyDemo.HIFAC_MARKET_JP) {
                                                                    if (mcf.getACASState() == FacParamManager.FAC_TVS_ACAS_STATE_VALID) {
                                                                        z4 = true;
                                                                    } else {
                                                                        z4 = false;
                                                                    }
                                                                    this.mIsACASSTateOk = z4;
                                                                    if (!mcf.getEmmcType()) {
                                                                        this.mIsEMMCOk = false;
                                                                    }
                                                                }
                                                                if (!mcf.getLogoName().equalsIgnoreCase("Sunbrite")) {
                                                                    serviceTag = mcf.getServiceTag();
                                                                    if (serviceTag != null) {
                                                                        this.mIsStOk = false;
                                                                    } else {
                                                                        this.mIsStOk = false;
                                                                    }
                                                                } else {
                                                                    serviceTag = mcf.getServiceTag();
                                                                    if (serviceTag != null) {
                                                                        this.mIsStOk = false;
                                                                    } else {
                                                                        this.mIsStOk = false;
                                                                    }
                                                                }
                                                                Log.i("Fac_FacRootMenu", "Execute OK isKeyOk " + zTestWhetherKeyIsOK3);
                                                                Log.i("Fac_FacRootMenu", "Execute OK isMgkNameRight " + zMgkNameIsRight);
                                                                Log.i("Fac_FacRootMenu", "Execute OK isMacNotOk " + zIsMacAddressDefaultOrIllegal);
                                                                Log.i("Fac_FacRootMenu", "Execute OK isMicSwitchOn " + zIsMicSwitchOn);
                                                                Log.i("Fac_FacRootMenu", "Execute OK isTconTempVrOK " + z3);
                                                                if (!zIsMacAddressDefaultOrIllegal) {
                                                                    showOkErrorMsg(zIsMicSwitchOn, zIsMacAddressDefaultOrIllegal, z3);
                                                                } else {
                                                                    showOkErrorMsg(zIsMicSwitchOn, zIsMacAddressDefaultOrIllegal, z3);
                                                                }
                                                            }
                                                        } else if (!this.mFacFunction_Adapter.factoryOk_checkAutoGamma()) {
                                                            this.mModel_ShortTip_TV.setText("Autogamma NG");
                                                            this.mModel_ShortTip_TV.setTextColor(-65536);
                                                            this.mModel_ShortTip_TV.setGravity(17);
                                                            this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                                                            showTipMode(true);
                                                            this.mhHandler.removeMessages(2);
                                                            this.mhHandler.removeMessages(0);
                                                        } else {
                                                            boolean zTestWhetherKeyIsOK4 = testWhetherKeyIsOK();
                                                            zIsMacAddressDefaultOrIllegal = isMacAddressDefaultOrIllegal();
                                                            if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
                                                                zMgkNameIsRight = mcf.mgkNameIsRight();
                                                            } else {
                                                                zMgkNameIsRight = true;
                                                            }
                                                            zIsMicSwitchOn = mcf.isMicSwitchOn();
                                                            if (FacPropertyDemo.HIFAC_MARKET_SA) {
                                                                zIsHaveClientType = mcf.isHaveClientType();
                                                            } else {
                                                                zIsHaveClientType = true;
                                                            }
                                                            if (FacPropertyDemo.HIFAC_IS_OLED) {
                                                                z3 = true;
                                                            } else {
                                                                z3 = true;
                                                            }
                                                            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                                                                if (mcf.getACASState() == FacParamManager.FAC_TVS_ACAS_STATE_VALID) {
                                                                    z4 = true;
                                                                } else {
                                                                    z4 = false;
                                                                }
                                                                this.mIsACASSTateOk = z4;
                                                                if (!mcf.getEmmcType()) {
                                                                    this.mIsEMMCOk = false;
                                                                }
                                                            }
                                                            if (!mcf.getLogoName().equalsIgnoreCase("Sunbrite")) {
                                                                serviceTag = mcf.getServiceTag();
                                                                if (serviceTag != null) {
                                                                    this.mIsStOk = false;
                                                                } else {
                                                                    this.mIsStOk = false;
                                                                }
                                                            } else {
                                                                serviceTag = mcf.getServiceTag();
                                                                if (serviceTag != null) {
                                                                    this.mIsStOk = false;
                                                                } else {
                                                                    this.mIsStOk = false;
                                                                }
                                                            }
                                                            Log.i("Fac_FacRootMenu", "Execute OK isKeyOk " + zTestWhetherKeyIsOK4);
                                                            Log.i("Fac_FacRootMenu", "Execute OK isMgkNameRight " + zMgkNameIsRight);
                                                            Log.i("Fac_FacRootMenu", "Execute OK isMacNotOk " + zIsMacAddressDefaultOrIllegal);
                                                            Log.i("Fac_FacRootMenu", "Execute OK isMicSwitchOn " + zIsMicSwitchOn);
                                                            Log.i("Fac_FacRootMenu", "Execute OK isTconTempVrOK " + z3);
                                                            if (!zIsMacAddressDefaultOrIllegal) {
                                                                showOkErrorMsg(zIsMicSwitchOn, zIsMacAddressDefaultOrIllegal, z3);
                                                            } else {
                                                                showOkErrorMsg(zIsMicSwitchOn, zIsMacAddressDefaultOrIllegal, z3);
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 4214:
                                                    mcf.setLogToTvcertificate("Mac is press");
                                                    List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(this);
                                                    if (externalStoragePath == null || externalStoragePath.isEmpty()) {
                                                        mcf.setLogToTvcertificate("Not have udisk, pass MacKeyPressed!!");
                                                        mcf.displayHisToast(getApplicationContext().getString(R.string.str_no_usb_mounted));
                                                    } else {
                                                        mMacKeyWriter.manualTryToWrite();
                                                    }
                                                    return true;
                                                case 4215:
                                                    String str28 = mcf.getIPAddr().equalsIgnoreCase("") ? "0.0.0.0" : " " + mcf.getIPAddr();
                                                    String str29 = " " + mcf.getMacAddress();
                                                    String bTMacAddress = mcf.getBTMacAddress();
                                                    Bundle bundle4 = new Bundle();
                                                    bundle4.putString("type", "ip");
                                                    bundle4.putString("ipAddr", str28);
                                                    bundle4.putString("macAddr", str29);
                                                    if (FacPropertyDemo.HIFAC_BLUETOOTH_SUPPORT) {
                                                        bundle4.putString("bluetoothMac", bTMacAddress);
                                                    }
                                                    if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT) {
                                                        bundle4.putString("wideVineKey", " " + mcf.getDeviceID());
                                                    }
                                                    if (FacPropertyDemo.HIFAC_HDCP_1_4_KEY_SUPPORT) {
                                                        bundle4.putString("hdcp14key", " " + mcf.getHdcp14Key());
                                                    }
                                                    if (FacPropertyDemo.HIFAC_HDCP_2_X_KEY_SUPPORT) {
                                                        bundle4.putString("hdcp22key", " " + mcf.getHdcp2xKey());
                                                    }
                                                    if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
                                                        bundle4.putString("mgk", " " + mcf.getNetflixKey());
                                                    }
                                                    Intent intent8 = new Intent(this, (Class<?>) AllMachineInfo.class);
                                                    intent8.putExtras(bundle4);
                                                    startActivityForResult(intent8, 0);
                                                    return true;
                                                case 4216:
                                                    if (i != 4) {
                                                        if (FacPropertyDemo.HIFAC_MARKET_JP) {
                                                            this.mFacFunction_Adapter.setTVSSavingMode(FacTvsFunctionUtil.TVS_SAVING_MODE_STANDARD);
                                                        }
                                                        if (FacPropertyDemo.HIFAC_LIGHT_SENSOR_SUPPORT) {
                                                            Log.i("Fac_FacRootMenu", ".setSensorEnable(false)");
                                                            this.mFacFunction_Adapter.setSensorEnable(false);
                                                        }
                                                        if (FacPropertyDemo.HIFAC_VCOM_SUPPORT <= 0) {
                                                            if (mcf.isTestPattern()) {
                                                                SystemProperties.set("sys.vt.factory.pattern_test", "false");
                                                                mcf.setTestPatternModeOff();
                                                                if (MenuConfigManager.isAudioMute()) {
                                                                    r1 = 0;
                                                                    sendMuteBroadcast(false);
                                                                } else {
                                                                    r1 = 0;
                                                                }
                                                                if (FacPropertyDemo.HIFAC_LOCALDIMMING_SUPPORT) {
                                                                    mcf.setBackLightOn(r1);
                                                                    mcf.setLocalDimmingSwitch(true);
                                                                }
                                                                mcf.setTestPattern(r1, r1, r1, r1);
                                                                mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                                                                this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                                                                if (mcf.getDefault("HIFAC_STATUS") == 0) {
                                                                    this.mhHandler.removeMessages(r1);
                                                                    this.mhHandler.removeMessages(4);
                                                                    finish();
                                                                } else {
                                                                    showBigMMode();
                                                                }
                                                            }
                                                            if (mcf.getDefault("HIFAC_STATUS") != 1) {
                                                            }
                                                            if (this.mInPicShowMode) {
                                                                mButton_FactoryMenuRoot.setVisibility(0);
                                                                mSurfaceView.setVisibility(8);
                                                                mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                                                                this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                                                                this.mFacFunction_Adapter.switchSourceByName(this.mPrevSourceEnum.name());
                                                            }
                                                            if (!FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.US)) {
                                                                this.mFacFunction_Adapter.setLocalDimmingCheck(0);
                                                                if (!FacPropertyDemo.HIFAC_MIC_SUPPORT) {
                                                                    mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                                                                    this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                                                                } else {
                                                                    mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                                                                    this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                                                                }
                                                            } else {
                                                                this.mFacFunction_Adapter.setLocalDimmingCheck(0);
                                                                if (!FacPropertyDemo.HIFAC_MIC_SUPPORT) {
                                                                    mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                                                                    this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                                                                } else {
                                                                    mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                                                                    this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                                                                }
                                                            }
                                                            if (i == 4216) {
                                                                this.mIsMKeyPressed = true;
                                                                this.mhHandler.removeMessages(18);
                                                                sendMessageDelayed(18, 3000);
                                                            }
                                                        } else {
                                                            if (mcf.isTestPattern()) {
                                                                SystemProperties.set("sys.vt.factory.pattern_test", "false");
                                                                mcf.setTestPatternModeOff();
                                                                if (MenuConfigManager.isAudioMute()) {
                                                                    r1 = 0;
                                                                    sendMuteBroadcast(false);
                                                                } else {
                                                                    r1 = 0;
                                                                }
                                                                if (FacPropertyDemo.HIFAC_LOCALDIMMING_SUPPORT) {
                                                                    mcf.setBackLightOn(r1);
                                                                    mcf.setLocalDimmingSwitch(true);
                                                                }
                                                                mcf.setTestPattern(r1, r1, r1, r1);
                                                                mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                                                                this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                                                                if (mcf.getDefault("HIFAC_STATUS") == 0) {
                                                                    this.mhHandler.removeMessages(r1);
                                                                    this.mhHandler.removeMessages(4);
                                                                    finish();
                                                                } else {
                                                                    showBigMMode();
                                                                }
                                                            }
                                                            if (mcf.getDefault("HIFAC_STATUS") != 1) {
                                                            }
                                                            if (this.mInPicShowMode) {
                                                                mButton_FactoryMenuRoot.setVisibility(0);
                                                                mSurfaceView.setVisibility(8);
                                                                mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                                                                this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                                                                this.mFacFunction_Adapter.switchSourceByName(this.mPrevSourceEnum.name());
                                                            }
                                                            if (!FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.US)) {
                                                                this.mFacFunction_Adapter.setLocalDimmingCheck(0);
                                                                if (!FacPropertyDemo.HIFAC_MIC_SUPPORT) {
                                                                    mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                                                                    this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                                                                } else {
                                                                    mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                                                                    this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                                                                }
                                                            } else {
                                                                this.mFacFunction_Adapter.setLocalDimmingCheck(0);
                                                                if (!FacPropertyDemo.HIFAC_MIC_SUPPORT) {
                                                                    mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                                                                    this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                                                                } else {
                                                                    mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                                                                    this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                                                                }
                                                            }
                                                            if (i == 4216) {
                                                                this.mIsMKeyPressed = true;
                                                                this.mhHandler.removeMessages(18);
                                                                sendMessageDelayed(18, 3000);
                                                            }
                                                        }
                                                    } else if (i == 4216) {
                                                        this.mIsMKeyPressed = true;
                                                        this.mhHandler.removeMessages(18);
                                                        sendMessageDelayed(18, 3000);
                                                    }
                                                    break;
                                                case 4217:
                                                    new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.10
                                                        @Override // java.lang.Runnable
                                                        public void run() {
                                                            FacRootMenu.mNavIntegration.iSetSourcetoNextAV();
                                                            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.10.1
                                                                @Override // java.lang.Runnable
                                                                public void run() {
                                                                    FacRootMenu.this.showSourceInfoBar();
                                                                }
                                                            });
                                                        }
                                                    }).start();
                                                    return true;
                                                case 4218:
                                                    if (FacPropertyDemo.HIFAC_LIGHT_SENSOR_SUPPORT) {
                                                        showLightSensorStatusDialog();
                                                    } else {
                                                        new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.11
                                                            @Override // java.lang.Runnable
                                                            public void run() {
                                                                FacRootMenu.mNavIntegration.iSetSourcetoVGA();
                                                                FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.11.1
                                                                    @Override // java.lang.Runnable
                                                                    public void run() {
                                                                        if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.VGA)) {
                                                                            FacRootMenu.this.showSourceInfoBar();
                                                                        }
                                                                    }
                                                                });
                                                            }
                                                        }).start();
                                                    }
                                                    return true;
                                                case 4219:
                                                    Log.i("Fac_FacRootMenu", "****KEYCODE_HISIR_HDMI  bLogoKeyPressed = " + this.bLogoKeyPressed);
                                                    if (this.bLogoKeyPressed) {
                                                        if (this.mFacFunction_Adapter.getEarcStatus() == 0) {
                                                            if (this.mFacFunction_Adapter.setEarcStatus(1)) {
                                                                mcf.displayHisToast("eARC：on");
                                                            }
                                                        } else if (this.mFacFunction_Adapter.setEarcStatus(0)) {
                                                            mcf.displayHisToast("eARC：off");
                                                        }
                                                        this.bLogoKeyPressed = false;
                                                    } else {
                                                        new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.12
                                                            @Override // java.lang.Runnable
                                                            public void run() {
                                                                FacRootMenu.mNavIntegration.iSetSourcetoNextHDMI();
                                                                FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.12.1
                                                                    @Override // java.lang.Runnable
                                                                    public void run() {
                                                                        FacRootMenu.this.showSourceInfoBar();
                                                                    }
                                                                });
                                                            }
                                                        }).start();
                                                    }
                                                    return true;
                                                default:
                                                    switch (i) {
                                                        case 4221:
                                                            if (FacPropertyDemo.HIFAC_MARKET_JP && !FacPropertyDemo.HIFAC_IS_OLED_TVS && this.viewMode != 0 && !this.inputNuming) {
                                                                int tVSSavingMode = mcf.setTVSSavingMode();
                                                                showTipMode(true);
                                                                setSavingModeString(tVSSavingMode - FacTvsFunctionUtil.TVS_SAVING_MODE_1);
                                                            }
                                                            return true;
                                                        case 4222:
                                                            if (this.bLogoKeyPressed) {
                                                                Log.i("Fac_FacRootMenu", "TEST  Logo+pannel pressd");
                                                                this.mhHandler.removeMessages(11);
                                                                this.bLogoKeyPressed = false;
                                                                if (FacPropertyDemo.HIFAC_LD_PATTERN_SUPPORT) {
                                                                    startActivity(new Intent(this, (Class<?>) LdPatternTestActivity.class));
                                                                }
                                                            } else if (this.f3KeyIsPressed) {
                                                                this.mIsKeyPannelTestPressed = true;
                                                                this.mhHandler.removeMessages(37);
                                                                sendMessageDelayed(37, 2000);
                                                            } else {
                                                                canncelMessage(2);
                                                                sendBroadcast(this.cancelMuteIntent);
                                                                setTestPatternMode();
                                                            }
                                                            return true;
                                                        case 4223:
                                                            if (this.viewMode != 0 && !this.inputNuming) {
                                                                if (keyEvent.getRepeatCount() == 0) {
                                                                    if (mcf.getDefault("HIFAC_AGING") != 1) {
                                                                        if (this.mSharedPreferences == null) {
                                                                            initSharedPreference();
                                                                        }
                                                                        enterFactoryAging();
                                                                        this.bprepareexitaging = false;
                                                                    } else {
                                                                        this.bprepareexitaging = true;
                                                                    }
                                                                } else if (keyEvent.getDownTime() >= 1000 && this.bprepareexitaging && mcf.getDefault("HIFAC_AGING") == 1) {
                                                                    factoryAging(false);
                                                                    showBigMMode();
                                                                }
                                                            }
                                                            return true;
                                                        case 4224:
                                                            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                                                                if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_650) {
                                                                    str3 = "HIFAC_TVS_BALANCE_LIKE_650";
                                                                } else {
                                                                    str3 = FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_850 ? "HIFAC_TVS_BALANCE_LIKE_850" : "HIFAC_TVS_BALANCE_LIKE_550";
                                                                }
                                                                if (this.viewMode != 0 && !this.inputNuming) {
                                                                    if (this.mModel_ShortTip_TV.getVisibility() == 0 && this.childType == 6) {
                                                                        mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.24
                                                                            @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                                                                            public void effectChanged(View view, int i15) {
                                                                                FacRootMenu.this.mModel_ShortTip_TV.setText(i15);
                                                                            }
                                                                        }, str3);
                                                                    } else {
                                                                        if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_650) {
                                                                            mNavIntegration.preSetTvsTheeterWooferBalanceToML();
                                                                        } else if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_550) {
                                                                            mNavIntegration.preSetTvsTheeterBalanceToML();
                                                                        } else if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_850) {
                                                                            mNavIntegration.preSetTvsBalanceToMLLike850();
                                                                        }
                                                                        this.mModel_ShortTip_TV.setText(mNavIntegration.iGetEffect(str3));
                                                                        this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                                                                        showTipModeForBalance(true);
                                                                        this.childType = 6;
                                                                    }
                                                                    mSetShortTipData(this.childType);
                                                                }
                                                            } else {
                                                                String str30 = mcf.getSubWooferTotal() == 1 ? "HIFAC_BALANCE_DOUBLE_AMP" : "HIFAC_BALANCE";
                                                                if (mcf.getSubWooferTotal() == 2) {
                                                                    str30 = "HIFAC_BALANCE_MULTI_AMP";
                                                                }
                                                                if (mcf.getSubWooferTotal() > 2) {
                                                                    str30 = "HIFAC_BALANCE_UNIV_MULTI_AMP";
                                                                }
                                                                if (this.viewMode != 0 && !this.inputNuming) {
                                                                    if (this.mModel_ShortTip_TV.getVisibility() == 0 && this.childType == 6) {
                                                                        mNavIntegration.iSetNextEffect(this.mModel_ShortTip_TV, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.FacRootMenu.25
                                                                            @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                                                                            public void effectChanged(View view, int i15) {
                                                                                FacRootMenu.this.mModel_ShortTip_TV.setText(i15);
                                                                            }
                                                                        }, str30);
                                                                    } else {
                                                                        if (mcf.getSubWooferTotal() == 1) {
                                                                            mNavIntegration.preSetDoubleAmplifierBalanceToML();
                                                                        } else if (mcf.getSubWooferTotal() == 2) {
                                                                            mNavIntegration.preSetMultiAmplifierBalanceToML();
                                                                        } else if (mcf.getSubWooferTotal() > 2) {
                                                                            mNavIntegration.preSetUnivMultiAmplifierBalanceToML();
                                                                        } else if (FacPropertyDemo.HIFAC_AMP_WOOFER_SUPPORT) {
                                                                            mNavIntegration.preSetBalanceToWoofer();
                                                                        } else {
                                                                            mNavIntegration.preSetBalanceToLeft();
                                                                        }
                                                                        this.mModel_ShortTip_TV.setText(mNavIntegration.iGetEffect(str30));
                                                                        this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                                                                        showTipModeForBalance(true);
                                                                        this.childType = 6;
                                                                    }
                                                                    mSetShortTipData(this.childType);
                                                                }
                                                            }
                                                            return true;
                                                        case 4225:
                                                            if (this.mOnKeyDownSecretKeys[3] == 4252) {
                                                                startTestMic(true);
                                                                return true;
                                                            }
                                                            break;
                                                        case 4226:
                                                            if (!this.bLogoKeyPressed) {
                                                                showTipMode(true);
                                                                this.childType = 30;
                                                                mSetShortTipData(30);
                                                                sendMessageDelayed(2);
                                                            }
                                                            this.bLogoKeyPressed = true;
                                                            this.mhHandler.removeMessages(11);
                                                            sendMessageDelayed(11, 2000);
                                                            return true;
                                                        case 4227:
                                                            if (!mcf.isHaveTuner()) {
                                                                return true;
                                                            }
                                                            mNavIntegration.iSetSourcetoATV();
                                                            showSourceInfoBar();
                                                            return true;
                                                        case 4228:
                                                            if (!mcf.isHaveTuner()) {
                                                                return true;
                                                            }
                                                            mNavIntegration.iSetSourcetoDTV();
                                                            showSourceInfoBar();
                                                            return true;
                                                        case 4229:
                                                            new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.16
                                                                @Override // java.lang.Runnable
                                                                public void run() {
                                                                    FacRootMenu.mNavIntegration.iSetSourcetoAV1();
                                                                    FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.16.1
                                                                        @Override // java.lang.Runnable
                                                                        public void run() {
                                                                            FacRootMenu.this.showSourceInfoBar();
                                                                        }
                                                                    });
                                                                }
                                                            }).start();
                                                            return true;
                                                        case 4230:
                                                            new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.17
                                                                @Override // java.lang.Runnable
                                                                public void run() {
                                                                    FacRootMenu.mNavIntegration.iSetSourcetoAV2();
                                                                    FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.17.1
                                                                        @Override // java.lang.Runnable
                                                                        public void run() {
                                                                            FacRootMenu.this.showSourceInfoBar();
                                                                        }
                                                                    });
                                                                }
                                                            }).start();
                                                            return true;
                                                        default:
                                                            switch (i) {
                                                                case 4232:
                                                                    showBigMMode();
                                                                    break;
                                                                case 4233:
                                                                    hideBigMMode();
                                                                    break;
                                                                case 4234:
                                                                    if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                                                                        setPatternNotSetYet();
                                                                    }
                                                                    break;
                                                                case 4235:
                                                                    if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                                                                        setPatternAlreaySet();
                                                                    }
                                                                    break;
                                                                case 4236:
                                                                    if ((mButton_FactoryMenuRoot.getVisibility() == 0 || mcf.getDefault("HIFAC_STATUS") == 0) && FacPropertyDemo.SourceEnum.DMP != mcf.getCurrentInputSourceEnum()) {
                                                                        exitMStatusMethod();
                                                                        finish();
                                                                    }
                                                                    break;
                                                                case 4237:
                                                                    if (FacPropertyDemo.HIFAC_MARKET_JP) {
                                                                        mcf.setTvsTvTuner(FacTvsFunctionUtil.FAC_TVS_TUNE_TYPE_BS4K);
                                                                    }
                                                                    break;
                                                                case 4238:
                                                                    new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.13
                                                                        @Override // java.lang.Runnable
                                                                        public void run() {
                                                                            FacRootMenu.mNavIntegration.iSetSourcetoYPBPR1();
                                                                            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.13.1
                                                                                @Override // java.lang.Runnable
                                                                                public void run() {
                                                                                    FacRootMenu.this.showSourceInfoBar();
                                                                                }
                                                                            });
                                                                        }
                                                                    }).start();
                                                                    break;
                                                                case 4239:
                                                                    new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.14
                                                                        @Override // java.lang.Runnable
                                                                        public void run() {
                                                                            FacRootMenu.mNavIntegration.iSetSourcetoYPBPR2();
                                                                            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.14.1
                                                                                @Override // java.lang.Runnable
                                                                                public void run() {
                                                                                    FacRootMenu.this.showSourceInfoBar();
                                                                                }
                                                                            });
                                                                        }
                                                                    }).start();
                                                                    break;
                                                                case 4240:
                                                                    new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.15
                                                                        @Override // java.lang.Runnable
                                                                        public void run() {
                                                                            FacRootMenu.mNavIntegration.iSetSourcetoYPBPR3();
                                                                            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.15.1
                                                                                @Override // java.lang.Runnable
                                                                                public void run() {
                                                                                    FacRootMenu.this.showSourceInfoBar();
                                                                                }
                                                                            });
                                                                        }
                                                                    }).start();
                                                                    break;
                                                                case 4241:
                                                                    if (FacPropertyDemo.HIFAC_LIGHT_SENSOR_SUPPORT) {
                                                                        showLightSensorStatusDialog();
                                                                    } else {
                                                                        new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.11
                                                                            @Override // java.lang.Runnable
                                                                            public void run() {
                                                                                FacRootMenu.mNavIntegration.iSetSourcetoVGA();
                                                                                FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.11.1
                                                                                    @Override // java.lang.Runnable
                                                                                    public void run() {
                                                                                        if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.VGA)) {
                                                                                            FacRootMenu.this.showSourceInfoBar();
                                                                                        }
                                                                                    }
                                                                                });
                                                                            }
                                                                        }).start();
                                                                    }
                                                                    break;
                                                                case 4242:
                                                                    new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.18
                                                                        @Override // java.lang.Runnable
                                                                        public void run() {
                                                                            FacRootMenu.mNavIntegration.iSetSourcetoHDMI1();
                                                                            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.18.1
                                                                                @Override // java.lang.Runnable
                                                                                public void run() {
                                                                                    FacRootMenu.this.showSourceInfoBar();
                                                                                }
                                                                            });
                                                                        }
                                                                    }).start();
                                                                    break;
                                                                case 4243:
                                                                    new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.19
                                                                        @Override // java.lang.Runnable
                                                                        public void run() {
                                                                            FacRootMenu.mNavIntegration.iSetSourcetoHDMI2();
                                                                            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.19.1
                                                                                @Override // java.lang.Runnable
                                                                                public void run() {
                                                                                    FacRootMenu.this.showSourceInfoBar();
                                                                                }
                                                                            });
                                                                        }
                                                                    }).start();
                                                                    break;
                                                                case 4244:
                                                                    new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.20
                                                                        @Override // java.lang.Runnable
                                                                        public void run() {
                                                                            FacRootMenu.mNavIntegration.iSetSourcetoHDMI3();
                                                                            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.20.1
                                                                                @Override // java.lang.Runnable
                                                                                public void run() {
                                                                                    FacRootMenu.this.showSourceInfoBar();
                                                                                }
                                                                            });
                                                                        }
                                                                    }).start();
                                                                    break;
                                                                case 4245:
                                                                    new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.21
                                                                        @Override // java.lang.Runnable
                                                                        public void run() {
                                                                            FacRootMenu.mNavIntegration.iSetSourcetoHDMI4();
                                                                            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.21.1
                                                                                @Override // java.lang.Runnable
                                                                                public void run() {
                                                                                    FacRootMenu.this.showSourceInfoBar();
                                                                                }
                                                                            });
                                                                        }
                                                                    }).start();
                                                                    break;
                                                            }
                                                            return true;
                                                    }
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
            }
        } else if (this.viewMode != 0) {
            z = true;
            if (mNavIntegration.iCurrentInputSourceHasSignal()) {
                Log.e("Fac_FacRootMenu", "iCurrentInputSourceHasSignal true ------");
                if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                    if (this.mModel_ShortTip_TV.getVisibility() != 0) {
                        Log.e("Fac_FacRootMenu", "iCurrentInputSourceHasSignal childType != CHILD_TYPE_TIP_ASPECT ------");
                        setZoomModeString(mNavIntegration.iGetEffect("HIFAC_ZOOMMODE_FOR_EM"));
                        this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                    } else {
                        Log.e("Fac_FacRootMenu", "iCurrentInputSourceHasSignal childType != CHILD_TYPE_TIP_ASPECT ------");
                        setZoomModeString(mNavIntegration.iGetEffect("HIFAC_ZOOMMODE_FOR_EM"));
                        this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                    }
                } else if (this.mModel_ShortTip_TV.getVisibility() != 0) {
                    Log.e("Fac_FacRootMenu", "iCurrentInputSourceHasSignal childType != CHILD_TYPE_TIP_ASPECT ------");
                    setZoomModeString(mNavIntegration.iGetEffect("HIFAC_ZOOMMODE"));
                    this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                } else {
                    Log.e("Fac_FacRootMenu", "iCurrentInputSourceHasSignal childType != CHILD_TYPE_TIP_ASPECT ------");
                    setZoomModeString(mNavIntegration.iGetEffect("HIFAC_ZOOMMODE"));
                    this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                }
                z = true;
            }
            showTipMode(z);
            this.childType = 5;
            mSetShortTipData(5);
        }
        return true;
    }

    public void startInnerVideoPlayer(String str, String str2) {
        if (this.mMtkAudioPatchManager != null) {
            Log.i("Fac_FacRootMenu", "startInnerVideoPlayer releaseAudioPatch");
            this.mMtkAudioPatchManager.releaseAudioPatch();
            this.mMtkAudioPatchManager = null;
        } else {
            Log.i("Fac_FacRootMenu", "startInnerVideoPlayer MtkAudioPatchManager is null");
        }
        mcf.setMtkSourceStatus(false);
        this.mTvStoped = true;
        Log.i("Fac_FacRootMenu", "stop tv");
        Intent intent = new Intent(this, (Class<?>) MediaPlayerActivity.class);
        intent.addFlags(536870912);
        intent.addFlags(268435456);
        Bundle bundle = new Bundle();
        bundle.putString("videoFileDirctory", str);
        bundle.putString("videoFileName", str2);
        intent.putExtras(bundle);
        startActivityForResult(intent, 9);
    }

    public void startPlayPicture(String str, String str2) {
        hideBigMIcon();
        Intent intent = new Intent(this, (Class<?>) PicturePlayerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("pictureFileDirctory", str);
        bundle.putString("pictureFileName", str2);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startPlayMusic(String str, String str2) {
        if (this.mMtkAudioPatchManager != null) {
            Log.i("Fac_FacRootMenu", "startPlayMusic releaseAudioPatch");
            this.mMtkAudioPatchManager.releaseAudioPatch();
            this.mMtkAudioPatchManager = null;
        } else {
            Log.i("Fac_FacRootMenu", "startPlayMusic MtkAudioPatchManager is null");
        }
        mcf.setMtkSourceStatus(false);
        this.mTvStoped = true;
        Log.i("Fac_FacRootMenu", "stop tv");
        Intent intent = new Intent(this, (Class<?>) MusicPlayerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("musicFileDirctory", str);
        bundle.putString("musicFileName", str2);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private int getCountOfUsbOrSdCardMounted(boolean z) throws Throwable {
        int usbCount;
        if (!z) {
            return Environment.getExternalStorageState().equals("mounted") ? 1 : 0;
        }
        List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(this);
        if (externalStoragePath == null) {
            return 0;
        }
        if (FacPropertyDemo.HIFAC_TF_CARD_SUPPORT && ExternalStorageHelper.isSDCardInsert(this)) {
            usbCount = externalStoragePath.size() - 1;
        } else {
            usbCount = mcf.getUsbCount();
        }
        return usbCount;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        Log.i("Fac_FacRootMenu", "onKeyUp keycode = " + i + " " + keyEvent.toString());
        StringBuilder sb = new StringBuilder();
        sb.append("onKeyUp  event.getDownTime() == ");
        sb.append(keyEvent.getDownTime());
        Log.i("Fac_FacRootMenu", sb.toString());
        Log.i("Fac_FacRootMenu", "onKeyUp  event.isLongPress() == " + keyEvent.isLongPress());
        Log.i("Fac_FacRootMenu", "onKeyUp  event.getRepeatCount() == " + keyEvent.getRepeatCount());
        Log.i("Fac_FacRootMenu", "onKeyUp  interval = " + (keyEvent.getEventTime() - keyEvent.getDownTime()));
        this.isDmpPressedMessageSent = false;
        this.isLongPressedDmp = false;
        checkLaunchHisenseBugReport(i, keyEvent);
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            doFactoryOkForTvs();
        }
        int[] iArr = this.mSecretKeys;
        iArr[0] = iArr[1];
        iArr[1] = iArr[2];
        iArr[2] = iArr[3];
        iArr[3] = i;
        if (i == 4221) {
            this.savingKeyIsPressed = true;
        }
        preHandlerKeyUp();
        if (this.mIsMKeyPressed) {
            int[] iArr2 = this.mSecretKeys;
            if (iArr2[0] == 8 && iArr2[1] == 16 && iArr2[2] == 13 && iArr2[3] == 16 && ((mButton_FactoryMenuRoot.getVisibility() == 0 || mcf.getDefault("HIFAC_STATUS") == 0) && FacPropertyDemo.SourceEnum.DMP != mcf.getCurrentInputSourceEnum())) {
                Log.i("Fac_FacRootMenu", "onKeyUp to exit M");
                exitMStatusMethod();
                if (!this.serviceModeStatus) {
                    syncSourceToClient();
                }
                finish();
            }
        }
        if (i == 4392 || i == 4393 || i == 4394 || i == 4395 || i == 4396 || i == 330) {
            Log.i("Fac_FacRootMenu", "TV key test");
            if (FacPropertyDemo.HIFAC_MARKET_SA) {
                this.mFacFunction_Adapter.setLocalKeypadLog();
            }
            Intent intent = new Intent(this, (Class<?>) ButtonTestActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("KeyInitValue", i);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Log.i("Fac_FacRootMenu", "^^^onActivityResult");
        if (i2 == 1) {
            return;
        }
        if (i == 1969 && i2 == 0 && FacPropertyDemo.HIFAC_MARKET_SA) {
            Log.i("Fac_FacRootMenu", "onActivityResult SA result");
        } else if (i == 2021 && FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mhHandler.sendEmptyMessageDelayed(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC, FacParamManager.FAC_SCREEN_PROTECT_TIME);
        }
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        if (i == 0) {
            return new f7networkdialog(this, i, this.mhHandler);
        }
        if (i != 1) {
            return null;
        }
        WifiBluetoochCombinedDialog wifiBluetoochCombinedDialog = new WifiBluetoochCombinedDialog(this, i, this.mhHandler);
        wifiBluetoochCombinedDialog.getWindow().setGravity(1);
        return wifiBluetoochCombinedDialog;
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Log.i("Fac_FacRootMenu", "^^^onresume");
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        if (this.mMtkTvHighLevel == null) {
            this.mMtkTvHighLevel = new MtkTvHighLevel();
        }
        if (mButton_FactoryMenuRoot.getVisibility() != 0 && mcf.getDefault("HIFAC_STATUS") == 1 && mcf.getDefault("HIFAC_AGING") == 0 && !this.isFactoryokbusy) {
            showBigMMode();
        }
        if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0 && mIsVcomOK) {
            sendMessageDelayed(20, 300);
            Log.i("Fac_FacRootMenu", "########################################## I am in VCOM");
            mIsVcomOK = false;
        }
        if (this.mIsMacKeyOK && FacPropertyDemo.HIFAC_MIC_SUPPORT && !mcf.isZDMicTestOK()) {
            this.mhHandler.sendEmptyMessage(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_ON);
        }
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS && this.mIsMacKeyOK) {
            Log.i("Fac_FacRootMenu", "^^^onresume OLED");
            this.mhHandler.removeMessages(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC);
            this.mhHandler.sendEmptyMessageDelayed(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC, FacParamManager.FAC_SCREEN_PROTECT_TIME);
        }
        Log.i("Fac_FacRootMenu", "^^^onresume end");
    }

    @Override // android.app.Activity
    protected void onStart() {
        Log.i("Fac_FacRootMenu", "**onStart*****");
        if (this.mSvctxNotifyCallbackHandler != null) {
            this.mSvctxNotifyCallbackHandler = null;
        }
        this.mSvctxNotifyCallbackHandler = new SvctxNotifyCallbackHandler();
        Log.i("Fac_FacRootMenu", "onStart,new SvctxNotifyCallbackHandler");
        CamDisplayManager.getInstance(this);
        if (this.mMtkAudioPatchManager == null) {
            Log.i("Fac_FacRootMenu", "createAudioPatch");
            MtkAudioPatchManager mtkAudioPatchManager = new MtkAudioPatchManager(getApplicationContext());
            this.mMtkAudioPatchManager = mtkAudioPatchManager;
            mtkAudioPatchManager.createAudioPatch();
        }
        if (this.mTvStoped) {
            Log.i("Fac_FacRootMenu", "set g_hisense__ott_src is 7");
            mcf.setOttSrc(7);
            mcf.setMtkSourceStatus(true);
            Log.i("Fac_FacRootMenu", "TV started");
            this.mTvStoped = false;
        }
        if (!FacPropertyDemo.HIFAC_MARKET_JP && !FacPropertyDemo.HIFAC_MARKET_TW && !FacPropertyDemo.HIFAC_MARKET_HK && "toshiba".equalsIgnoreCase(FacPropertyDemo.HIFAC_BRAND)) {
            mcf.setMuteColor(1);
            Log.i("Fac_FacRootMenu", "**onStart setMuteColor blue");
        }
        Log.i("Fac_FacRootMenu", "**onStart end");
        super.onStart();
    }

    private void stopAtsc3AndChangeSource() {
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            if (mcf.getCurrentSourceName().equals("TV")) {
                this.mFacFunction_Adapter.exitTvSource();
            }
            TvPlayerClient tvPlayerClient = this.client;
            if (tvPlayerClient != null) {
                tvPlayerClient.setSurface(null);
            }
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        Log.i("Fac_FacRootMenu", "^^^onStop");
        if (this.mSvctxNotifyCallbackHandler != null) {
            Log.i("Fac_FacRootMenu", "remove mSvctxNotifyCallbackHandler");
            this.mSvctxNotifyCallbackHandler.removeListener();
            this.mSvctxNotifyCallbackHandler = null;
        }
        if (!FacPropertyDemo.HIFAC_MARKET_JP && !FacPropertyDemo.HIFAC_MARKET_TW && !FacPropertyDemo.HIFAC_MARKET_HK && "toshiba".equalsIgnoreCase(FacPropertyDemo.HIFAC_BRAND)) {
            mcf.setMuteColor(0);
            Log.i("Fac_FacRootMenu", "**onStart setMuteColor black");
        }
        if (!this.mTvStoped) {
            mcf.setMtkSourceStatus(false);
            this.mTvStoped = true;
            Log.i("Fac_FacRootMenu", "TV stopped");
        }
        super.onStop();
    }

    private void cancelNumChangeChannel() {
        this.inputChannelNumStrBuffer = new StringBuffer();
        this.mhHandler.removeMessages(5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void canncelMessage(int i) {
        this.mhHandler.removeMessages(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkInputSouce(int i) {
        this.mSourceListView.setSelection(i);
        if (((TextView) this.mSourceListView.getChildAt(i)) != null) {
            ((TextView) this.mSourceListView.getChildAt(i)).setCompoundDrawables(this.mSourceSelectedIcon, null, null, null);
            showSourceInfoBar();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enterFactoryAging() {
        Log.i("Fac_FacRootMenu", "^^^enterFactoryAging");
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mhHandler.removeMessages(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC);
        }
        if (this.mFacFunction_Adapter.setAgingMode(true)) {
            sendBroadcast(this.cancelMuteIntent);
            this.mModel_ShortTip_TV.setText(R.string.str_factorymenu_m);
            this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
            showTipMode(true);
            this.childType = 21;
            this.mhHandler.removeCallbacksAndMessages(null);
            mSetShortTipData(this.childType);
        }
        startTestMic(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enterFactoryAgingNotHaveMic() {
        Log.i("Fac_FacRootMenu", "^^^enterFactoryAging");
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mhHandler.removeMessages(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC);
        }
        if (this.mFacFunction_Adapter.setAgingMode(true)) {
            sendBroadcast(this.cancelMuteIntent);
            this.mModel_ShortTip_TV.setText(R.string.str_factorymenu_m);
            this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
            showTipMode(true);
            this.childType = 21;
            this.mhHandler.removeCallbacksAndMessages(null);
            mSetShortTipData(this.childType);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void factoryAging(boolean z) {
        if (z) {
            mcf.setValue("HIFAC_AGING", 1);
            return;
        }
        if (this.mFacFunction_Adapter.setAgingMode(z)) {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.mModel_ShortTip_TV.setTextColor(getResources().getColor(R.color.white));
            this.mModel_ShortTip_TV.setTextSize(getResources().getDimension(R.dimen.nav_textsize_normal));
            this.mModel_ShortTip_TV.setBackgroundDrawable(getResources().getDrawable(R.drawable.nav_shorttip_bg));
            if (FacPropertyDemo.HIFAC_IS_LASER4K || FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                this.mAgingLaserTv.cancelAgingForLaserTv();
            }
            mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
            this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
            mcf.setValue("HIFAC_AGING", 0);
            if (!FacPropertyDemo.HIFAC_MIC_SUPPORT || mcf.isZDMicTestOK()) {
                return;
            }
            this.mhHandler.sendEmptyMessage(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_ON);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void factoryOk() {
        Log.i("Fac_FacRootMenu", "enter : resetFactoryOK");
        mMuteImageView.setVisibility(8);
        mcf.resetFactoryOK(this.factoryokcallback);
    }

    private void findViews() {
        this.mImageView = (ImageView) findViewById(R.id.vcom_picture);
        mButton_FactoryMenuRoot = (Button) findViewById(R.id.factorymenu_root);
        this.mTvSurfaceView = (SurfaceView) findViewById(R.id.tv_surfaceView);
        mFactoryMenuRoot_Layout = (RelativeLayout) findViewById(R.id.factoryrootmenu_relativelayout);
        this.mFacBGColorView = findViewById(R.id.fac_bg_color);
        this.mLayout_Bottom_One_Colmun_LL = findViewById(R.id.nav_bottom_one_column_layout);
        this.mLayout_Bottom_Two_Colmun_LL = findViewById(R.id.nav_bottom_two_column_layout);
        this.mModel_ShortTip_TV = (TextView) findViewById(R.id.nav_tv_shortTip_model);
        this.mModel_ShortTip_Title = (TextView) findViewById(R.id.nav_tv_shortTip_title);
        this.mModel_ShortTip_LL = findViewById(R.id.nav_ll_shortTip_model);
        this.mModel_SourceList_RL = findViewById(R.id.nav_ll_sourceList_model);
        mModel_Volume_RL = findViewById(R.id.nav_rl_volume_model);
        this.mModel_InfoBar_RL = findViewById(R.id.nav_rl_infobar_model);
        this.mModel_Factoryok_progress = findViewById(R.id.nav_factoryok_progress);
        this.mFactoryokProgressbar = (ProgressBar) findViewById(R.id.nav_factoryok_progress_pb_itemshow);
        this.mFactoryokprogressText = (TextView) findViewById(R.id.nav_factoryok_progress_text);
        this.mSourceListView = (CustListView) findViewById(R.id.nav_source_listview);
        this.mChannelDetailLayout = findViewById(R.id.nav_channel_details_layout);
        this.mCommonInfoTopRL = findViewById(R.id.nav_rl_top_common_info_banner);
        this.mDetailsInfoBottomTV = (TextView) findViewById(R.id.nav_tv_bottom_details_info_feild);
        this.mSimpleInfoTopRL = findViewById(R.id.nav_rl_top_right_simple_info_banner);
        this.mPbShow = (ProgressBar) findViewById(R.id.nav_volume_pb_itemshow);
        this.mInfoNumTV = (TextView) findViewById(R.id.nav_tv_top_right_infoNum_feild);
        this.mInfoNameTV = (TextView) findViewById(R.id.nav_tv_top_right_infoName_feild);
        this.mInfoBottomLeftTV = (TextView) findViewById(R.id.nav_tv_top_right_type_feild);
        this.mInfoBottomRightTV = (TextView) findViewById(R.id.nav_tv_top_right_time_feild);
        this.mInfoBottomTV = (TextView) findViewById(R.id.nav_tv_top_right_format_feild);
        this.mSALogoName = (TextView) findViewById(R.id.sa_logo_name);
        this.mMenu3DSettingListView = (MenuSetUpListView) findViewById(R.id.facmenu_3dsettinglistView);
        this.m3DlinearList = (LinearLayout) findViewById(R.id.facmenu_3dsettingcontent);
        this.mListViewAdpter_3DSetting = new SetConfigListViewAdapter(this);
        this.mMenu3DSettingListView.setOnKeyListener(this);
        ImageSurfaceView imageSurfaceView = new ImageSurfaceView(this);
        mSurfaceView = imageSurfaceView;
        mFactoryMenuRoot_Layout.addView(imageSurfaceView, new WindowManager.LayoutParams(-1, -1));
        mSurfaceView.setVisibility(8);
        mSurfaceView.getHolder();
    }

    private boolean handleHorizontalFocusWithinListItem(int i) {
        if (21 == i) {
            SetConfigListViewAdapter.DataItem dataItem = this.mListViewSelectedItemData;
            if (dataItem.mParent != null || dataItem.getDataType() == SetConfigListViewAdapter.DataItem.DataType.SWICHOPTIONVIEW) {
                Log.i("Fac_FacRootMenu", "****onkey left");
                this.childView.onKeyLeft();
                SetConfigListViewAdapter.DataItem dataItem2 = this.mListViewSelectedItemData.mParent;
                if (dataItem2 != null && dataItem2.getmInitValue() != dataItem2.userDefined) {
                    Log.i("Fac_FacRootMenu", "****onkey left show");
                    ((EffectOptionView) this.mMenu3DSettingListView.getChildAt(dataItem2.getPosition())).showValue(this.mMenuDataContainer.getValueById(dataItem2.getmItemID()));
                    return true;
                }
                Log.i("Fac_FacRootMenu", "****onkey left show" + this.mListViewSelectedItemData.getDataType().toString());
                if (this.mListViewSelectedItemData.getDataType() == SetConfigListViewAdapter.DataItem.DataType.SWICHOPTIONVIEW) {
                    this.mMenu3DSettingListView.setAdapter((ListAdapter) this.mListViewAdpter_3DSetting);
                    this.mMenu3DSettingListView.setSelection(this.mCurrentSelectedPosition);
                }
                return true;
            }
            this.childView.onKeyLeft();
            return true;
        }
        if (22 != i) {
            return false;
        }
        Log.i("Fac_FacRootMenu", "****right");
        SetConfigListViewAdapter.DataItem dataItem3 = this.mListViewSelectedItemData;
        if (dataItem3.mParent != null || dataItem3.getDataType() == SetConfigListViewAdapter.DataItem.DataType.SWICHOPTIONVIEW) {
            Log.i("Fac_FacRootMenu", "****right4");
            this.childView.onKeyRight();
            SetConfigListViewAdapter.DataItem dataItem4 = this.mListViewSelectedItemData.mParent;
            if (dataItem4 != null && dataItem4.getmInitValue() != dataItem4.userDefined) {
                Log.i("Fac_FacRootMenu", "****3d");
                ((EffectOptionView) this.mMenu3DSettingListView.getChildAt(dataItem4.getPosition())).showValue(this.mMenuDataContainer.getValueById(dataItem4.getmItemID()));
                return true;
            }
            if (this.mListViewSelectedItemData.getDataType() == SetConfigListViewAdapter.DataItem.DataType.SWICHOPTIONVIEW) {
                this.mMenu3DSettingListView.setAdapter((ListAdapter) this.mListViewAdpter_3DSetting);
                this.mMenu3DSettingListView.setSelection(this.mCurrentSelectedPosition);
            }
            return true;
        }
        Log.i("Fac_FacRootMenu", "****right2");
        this.childView.onKeyRight();
        Log.i("Fac_FacRootMenu", "****right3");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideAll() {
        Log.i("Fac_FacRootMenu", "enter hideAll ");
        this.viewMode = 2;
        this.childType = -1;
        hideBigMIcon();
        this.mSALogoName.setVisibility(8);
        this.mLayout_Bottom_One_Colmun_LL.setVisibility(8);
        this.mLayout_Bottom_Two_Colmun_LL.setVisibility(8);
        this.mModel_ShortTip_LL.setVisibility(8);
        this.mModel_ShortTip_TV.setVisibility(8);
        this.mModel_SourceList_RL.setVisibility(8);
        this.m3DlinearList.setVisibility(8);
        this.mChannelDetailLayout.setVisibility(8);
        mModel_Volume_RL.setVisibility(8);
        this.mModel_InfoBar_RL.setVisibility(8);
        this.mModel_Factoryok_progress.setVisibility(8);
        this.mhHandler.removeMessages(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initafterserviceok() throws Throwable {
        Log.i("Fac_FacRootMenu", "enter initafterserviceok *********** ");
        this.mhHandler.sendEmptyMessageDelayed(28, 3000L);
        if (FacPropertyDemo.HIFAC_SPORTS_MODE_SUPPORT && this.mFacFunction_Adapter.getSportMode()) {
            this.mFacFunction_Adapter.setSportMode(false);
        }
        this.mPrevSourceEnum = getPrevSource(false);
        if (this.mFacFunction_Adapter.isFirstPowerOn()) {
            Log.d("Fac_FacRootMenu", "isFirstTimePowerOn---true");
            setVolumAndPresetChannel();
            mcf.setLogToTvcertificate("FacRootMenu first power on");
            mcf.setFacLogStatus(0);
            this.mPrevSourceEnum = getPrevSource(true);
            mcf.setFirstTimePowerOn(0);
            if (FacPropertyDemo.HIFAC_MARKET_SA) {
                this.mFacFunction_Adapter.setFacCheckLogStatus(0);
            }
        } else if (mcf.getFactoryProtectFlag() == 1) {
            Log.d("Fac_FacRootMenu", "unprotectClearFlag is 1");
            setVolumAndPresetChannel();
            mcf.setLogToTvcertificate("FacRootMenu clear on start");
            this.mPrevSourceEnum = getPrevSource(true);
            if (FacPropertyDemo.HIFAC_MARKET_SA) {
                this.mFacFunction_Adapter.setFacCheckLogStatus(0);
            }
        } else if (mcf.getUpgradeFlag() == 1) {
            Log.d("Fac_FacRootMenu", "upgradeFlag is 1");
            setVolumAndPresetChannel();
            mcf.setFacLogStatus(0);
            mcf.setLogToTvcertificate("FacRootMenu upgrade start");
            this.mPrevSourceEnum = getPrevSource(true);
            if (FacPropertyDemo.HIFAC_MARKET_SA) {
                this.mFacFunction_Adapter.setFacCheckLogStatus(0);
            }
        } else if (mcf.isStartFromTiXiao()) {
            Log.d("Fac_FacRootMenu", "isStartFromTiXiao is 1");
            setVolumAndPresetChannel();
            mcf.setFacLogStatus(0);
            mcf.setLogToTvcertificate("FacRootMenu tixiao start");
            this.mPrevSourceEnum = getPrevSource(true);
            if (FacPropertyDemo.HIFAC_MARKET_SA) {
                this.mFacFunction_Adapter.setFacCheckLogStatus(0);
            }
        }
        mcf.setFactoryProtectFlag(0);
        mcf.setUpgradeFlag(0);
        SystemProperties.set("sys.vt.after_factory_reset", "0");
        this.mFacMicTest = HisenseFacMicTest.getInstance();
        if (mcf.getDefault("HIFAC_AGING") != 1) {
            mMacKeyWriter.autoTryToWrite();
        } else {
            this.mIsMacKeyOK = true;
            Log.d("Fac_FacRootMenu", "aging mode, set mIsMacKeyOK: true");
        }
        new Thread(new writeProductionKeyRunnable(this)).start();
        initViewDatas();
        registeListeners();
        Log.i("Fac_FacRootMenu", "prevSource is " + this.mPrevSourceEnum);
        this.mhHandler.sendEmptyMessageDelayed(22, 400L);
        mcf.setValue("HIFAC_STATUS", 1);
        if (mcf.getDefault("HIFAC_AGING") == 1) {
            this.mhHandler.sendEmptyMessageDelayed(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_NOT_DETECT, 410L);
            this.mhHandler.sendEmptyMessageDelayed(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_NOT_DETECT, 410L);
        }
        if (FacPropertyDemo.HIFAC_IS_LASER4K) {
            mcf.setModelSelection(1);
        }
        if (this.keyRC != 0) {
            mcf.setValue("HIFAC_STATUS", 0);
            onKeyDown(this.keyRC, null);
            this.keyRC = 0;
            Log.i("Fac_FacRootMenu", "*****in user menu press key=" + this.keyRC);
        }
        this.mPrevWifiStatus = this.mFacFunction_Adapter.getWifiStatus();
        Log.i("Fac_FacRootMenu", "prevWifiStatus is " + this.mPrevWifiStatus);
        if (this.mPrevWifiStatus) {
            this.mFacFunction_Adapter.disconnectWifi();
            this.mFacFunction_Adapter.startWifiScan();
        } else {
            this.mFacFunction_Adapter.openWifi();
        }
        if (FacPropertyDemo.HIFAC_IS_LASER4K && !this.mFacFunction_Adapter.getProjectId().equalsIgnoreCase("000") && !this.mFacFunction_Adapter.getProjectId().equalsIgnoreCase("") && this.mFacFunction_Adapter.getProjectId() != null) {
            this.mFacFunction_Adapter.initScreenType();
        }
        if (FacPropertyDemo.HIFAC_IS_LASER4K) {
            Log.d("Fac_FacRootMenu", "set setScreen as");
            FacFunction facFunction = this.mFacFunction_Adapter;
            facFunction.setScreen(facFunction.getScreen());
        }
        if (FacPropertyDemo.HIFAC_MARKET_TW || this.mFacFunction_Adapter.getCountryName().equalsIgnoreCase(MtkTvConfigTypeBase.S3166_CFG_COUNT_MYS)) {
            this.mFacFunction_Adapter.writeBooleanSetting("hdmi_control_enabled", true);
        }
        Log.i("Fac_FacRootMenu", "enter initafterserviceok *********** set str to 0");
    }

    private void initDrawable() {
        this.keysDialogView = View.inflate(this, R.layout.keys_dialog_layout, null);
        this.acasHddDialogView = View.inflate(this, R.layout.acas_hdd_dialog_layout, null);
        Drawable drawable = getResources().getDrawable(R.drawable.nav_source_list_select_icon);
        this.mSourceSelectedIcon = drawable;
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), this.mSourceSelectedIcon.getMinimumWidth());
        Drawable drawable2 = getResources().getDrawable(R.drawable.translucent_background);
        this.mSourceUnSelectedIcon = drawable2;
        drawable2.setBounds(0, 0, this.mSourceSelectedIcon.getMinimumWidth(), this.mSourceSelectedIcon.getMinimumWidth());
        this.mTipEnterAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_enter);
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile("/hisprod/config/factory/flicker.bmp");
        mBitmap = bitmapDecodeFile;
        if (bitmapDecodeFile != null) {
            mIsHaveVcomBitmap = true;
            if (bitmapDecodeFile.isRecycled()) {
                return;
            }
            mBitmap.recycle();
            mBitmap = null;
        }
    }

    private void initMuteImageView() {
        ImageView imageView = new ImageView(getApplicationContext());
        mMuteImageView = imageView;
        imageView.setImageResource(R.drawable.nav_mute);
        this.wm = (WindowManager) getApplicationContext().getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.wmParams = layoutParams;
        layoutParams.gravity = 51;
        layoutParams.x = 1706;
        layoutParams.y = 776;
        layoutParams.width = MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_NOT_SUPPORTED;
        layoutParams.height = MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PVR_PROG_BLOCKED;
        layoutParams.type = 2002;
        layoutParams.flags = MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_ES_AUTH_UPDATED;
        layoutParams.format = -3;
        if (mMuteImageView.getParent() != null) {
            this.wm.removeView(mMuteImageView);
        }
        mMuteImageView.setVisibility(8);
        this.wm.addView(mMuteImageView, this.wmParams);
    }

    private void initViewDatas() {
        List<String> listAsList = Arrays.asList(mNavIntegration.getInputSourceList());
        this.mSourceList = listAsList;
        if (listAsList == null) {
            Log.i("Fac_FacRootMenu", "mSourceList is null");
        } else {
            Log.i("Fac_FacRootMenu", "mSourceList size = " + this.mSourceList.size());
            CustListView custListView = this.mSourceListView;
            List<String> list = this.mSourceList;
            custListView.initData(list, list.size(), this.mSoureListUpDate);
            setSourceListData();
        }
        this.mPbShow.setMax(mcf.getMax("HIFAC_VOLUME"));
        this.mPbShow.setProgress(mcf.getDefault("HIFAC_VOLUME"));
    }

    private void inputChannelNum(int i) {
        String strSubstring;
        switch (i) {
            case 7:
                this.inputChannelNumStrBuffer.append("0");
                break;
            case 8:
                this.inputChannelNumStrBuffer.append("1");
                break;
            case 9:
                this.inputChannelNumStrBuffer.append("2");
                break;
            case 10:
                this.inputChannelNumStrBuffer.append(MtkTvRatingConvert2Goo.RATING_STR_3);
                break;
            case 11:
                this.inputChannelNumStrBuffer.append(MtkTvRatingConvert2Goo.RATING_STR_4);
                break;
            case 12:
                this.inputChannelNumStrBuffer.append(MtkTvRatingConvert2Goo.RATING_STR_5);
                break;
            case 13:
                this.inputChannelNumStrBuffer.append(MtkTvRatingConvert2Goo.RATING_STR_6);
                break;
            case 14:
                this.inputChannelNumStrBuffer.append(MtkTvRatingConvert2Goo.RATING_STR_7);
                break;
            case 15:
                this.inputChannelNumStrBuffer.append("8");
                break;
            case 16:
                this.inputChannelNumStrBuffer.append(MtkTvRatingConvert2Goo.RATING_STR_9);
                break;
        }
        if (this.inputChannelNumStrBuffer.length() <= 4) {
            strSubstring = this.inputChannelNumStrBuffer.toString();
        } else {
            StringBuffer stringBuffer = this.inputChannelNumStrBuffer;
            strSubstring = stringBuffer.substring(stringBuffer.length() - 4, this.inputChannelNumStrBuffer.length());
        }
        showInputNumberInfoBar(strSubstring);
        Log.d("Fac_FacRootMenu", "savingKeyIsPressed = " + this.savingKeyIsPressed);
        boolean z = this.savingKeyIsPressed;
        Message messageObtain = Message.obtain();
        messageObtain.what = 5;
        messageObtain.arg1 = z ? 1 : 0;
        this.mhHandler.sendMessageDelayed(messageObtain, 1000L);
    }

    private void showInputNumberInfoBar(String str) {
        hideAll();
        this.mCommonInfoTopRL.setBackgroundResource(R.drawable.nav_infobar_basic_bg);
        this.mSimpleInfoTopRL.setBackgroundResource(R.drawable.translucent_background);
        this.mModel_InfoBar_RL.setVisibility(0);
        this.mCommonInfoTopRL.setVisibility(0);
        this.mDetailsInfoBottomTV.setVisibility(4);
        this.mSimpleInfoTopRL.setVisibility(0);
        this.selectNum = Short.valueOf(str).shortValue();
        this.mInfoNumTV.setText("" + ((int) this.selectNum));
        this.mInfoNameTV.setText("");
        this.mInfoBottomLeftTV.setText("");
        this.mInfoBottomRightTV.setText("");
        this.mInfoNumTV.setVisibility(0);
        this.mInfoNameTV.setVisibility(0);
    }

    private void inputFangChuanHuoNumber(int i) {
        FangChuanHuoStatus fangChuanHuoStatus = this.mFangChuanHuo_Flag;
        if (fangChuanHuoStatus != FangChuanHuoStatus.FANGCHUANHUO_Z && fangChuanHuoStatus != FangChuanHuoStatus.FANGCHUANHUO_INPUTNUM) {
            this.mFangChuanHuo_Flag = FangChuanHuoStatus.FANGCHUANHUO_IDLE;
            return;
        }
        this.mFangChuanHuo_Flag = FangChuanHuoStatus.FANGCHUANHUO_INPUTNUM;
        switch (i) {
            case 29:
                this.inputFangChuanHuoNumberStrBuffer.append(MtkTvRatingConvert2Goo.SUB_RATING_STR_A);
                break;
            case 30:
                this.inputFangChuanHuoNumberStrBuffer.append("B");
                break;
            case 31:
                this.inputFangChuanHuoNumberStrBuffer.append("C");
                break;
            case 32:
                this.inputFangChuanHuoNumberStrBuffer.append(MtkTvRatingConvert2Goo.SUB_RATING_STR_D);
                break;
            case 33:
                this.inputFangChuanHuoNumberStrBuffer.append(MtkTvRatingConvert2Goo.STR_CA_TV_E);
                break;
            case 34:
                this.inputFangChuanHuoNumberStrBuffer.append(MtkTvRatingConvert2Goo.RATING_STR_F);
                break;
            case 35:
                this.inputFangChuanHuoNumberStrBuffer.append("G");
                break;
            case 36:
                this.inputFangChuanHuoNumberStrBuffer.append("H");
                break;
            case 37:
                this.inputFangChuanHuoNumberStrBuffer.append("I");
                break;
            case 38:
                this.inputFangChuanHuoNumberStrBuffer.append("J");
                break;
            case 39:
                this.inputFangChuanHuoNumberStrBuffer.append("K");
                break;
            case 40:
                this.inputFangChuanHuoNumberStrBuffer.append("L");
                break;
            case 41:
                this.inputFangChuanHuoNumberStrBuffer.append(MtkTvRatingConvert2Goo.RATING_STR_M);
                break;
            case 42:
                this.inputFangChuanHuoNumberStrBuffer.append("N");
                break;
            case 43:
                this.inputFangChuanHuoNumberStrBuffer.append("O");
                break;
            case 44:
                this.inputFangChuanHuoNumberStrBuffer.append(MtkTvRatingConvert2Goo.RATING_STR_P);
                break;
            case 45:
                this.inputFangChuanHuoNumberStrBuffer.append("Q");
                break;
            case 46:
                this.inputFangChuanHuoNumberStrBuffer.append("R");
                break;
            case 47:
                this.inputFangChuanHuoNumberStrBuffer.append(MtkTvRatingConvert2Goo.SUB_RATING_STR_S);
                break;
            case 48:
                this.inputFangChuanHuoNumberStrBuffer.append("T");
                break;
            case 49:
                this.inputFangChuanHuoNumberStrBuffer.append(MtkTvRatingConvert2Goo.RATING_STR_U);
                break;
            case 50:
                this.inputFangChuanHuoNumberStrBuffer.append(MtkTvRatingConvert2Goo.SUB_RATING_STR_V);
                break;
            case 51:
                this.inputFangChuanHuoNumberStrBuffer.append("W");
                break;
            case 52:
                this.inputFangChuanHuoNumberStrBuffer.append("X");
                break;
            case 53:
                this.inputFangChuanHuoNumberStrBuffer.append("Y");
                break;
            case 54:
                this.inputFangChuanHuoNumberStrBuffer.append("Z");
                break;
            default:
                switch (i) {
                    case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_OFF /* 144 */:
                        this.inputFangChuanHuoNumberStrBuffer.append("0");
                        break;
                    case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STARTED /* 145 */:
                        this.inputFangChuanHuoNumberStrBuffer.append("1");
                        break;
                    case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STOPPED /* 146 */:
                        this.inputFangChuanHuoNumberStrBuffer.append("2");
                        break;
                    case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_PAUSED /* 147 */:
                        this.inputFangChuanHuoNumberStrBuffer.append(MtkTvRatingConvert2Goo.RATING_STR_3);
                        break;
                    case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_SOURCE_READY /* 148 */:
                        this.inputFangChuanHuoNumberStrBuffer.append(MtkTvRatingConvert2Goo.RATING_STR_4);
                        break;
                    case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_ES_LIST /* 149 */:
                        this.inputFangChuanHuoNumberStrBuffer.append(MtkTvRatingConvert2Goo.RATING_STR_5);
                        break;
                    case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_STOP /* 150 */:
                        this.inputFangChuanHuoNumberStrBuffer.append(MtkTvRatingConvert2Goo.RATING_STR_6);
                        break;
                    case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_PIN_CAP_CHANGE /* 151 */:
                        this.inputFangChuanHuoNumberStrBuffer.append(MtkTvRatingConvert2Goo.RATING_STR_7);
                        break;
                    case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_ES_AUTH_UPDATED /* 152 */:
                        this.inputFangChuanHuoNumberStrBuffer.append("8");
                        break;
                    case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REACH_BOUND /* 153 */:
                        this.inputFangChuanHuoNumberStrBuffer.append(MtkTvRatingConvert2Goo.RATING_STR_9);
                        break;
                }
                break;
        }
        Log.i("Fac_FacRootMenu", "input fangchuanhuo=" + ((Object) this.inputFangChuanHuoNumberStrBuffer) + "len=" + this.inputFangChuanHuoNumberStrBuffer.length());
        this.selectFangChuanHuoString = this.inputFangChuanHuoNumberStrBuffer.toString();
        this.mModel_ShortTip_Title.setText(R.string.str_factorymenu_start);
        showTipMode(false);
        this.childType = 23;
        mSetShortTipData(23);
        canncelMessage(2);
        canncelMessage(4);
        canncelMessage(10);
        sendMessageDelayed(9, 6000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mSetShortTipData(int i) {
        Log.i("Fac_FacRootMenu", "short tip = " + i);
        this.mModel_ShortTip_Title.setGravity(17);
        this.mModel_ShortTip_TV.setGravity(17);
        if (i == 21) {
            Log.i("Fac_FacRootMenu", "show aging red M");
            this.mModel_ShortTip_TV.setTextColor(-65536);
            this.mModel_ShortTip_TV.setTextSize(84.0f);
            this.mModel_ShortTip_TV.setBackgroundResource(R.drawable.nav_empty_bg);
            this.mModel_ShortTip_TV.setGravity(17);
            if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                this.mModel_ShortTip_TV.setVisibility(8);
            }
            if (FacPropertyDemo.HIFAC_IS_LASER4K || FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                this.mAgingLaserTv.startAgingForLaserTv();
            } else {
                mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.color_white);
                this.mFacBGColorView.setBackgroundResource(R.drawable.color_white);
            }
        } else if (i == 20) {
            if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                this.mModel_ShortTip_TV.setTextColor(facRootMenuContext.getResources().getColor(R.color.red_oled));
            } else {
                this.mModel_ShortTip_TV.setTextColor(-65536);
            }
            this.mModel_ShortTip_TV.setTextSize(75.0f);
            this.mModel_ShortTip_TV.setGravity(17);
            this.mModel_ShortTip_TV.setBackgroundResource(R.drawable.nav_shorttip_bg);
        } else {
            this.mModel_ShortTip_TV.setTextColor(getResources().getColor(R.color.white));
            this.mModel_ShortTip_TV.setTextSize(getResources().getDimension(R.dimen.nav_textsize_normal));
            this.mModel_ShortTip_TV.setBackgroundDrawable(getResources().getDrawable(R.drawable.nav_shorttip_bg));
        }
        switch (i) {
            case 3:
                if (FacPropertyDemo.HIFAC_MARKET_TW) {
                    setPicModeString(mNavIntegration.iGetEffect("HIFAC_PICTUREMODE_TW"));
                } else {
                    setPicModeString(mNavIntegration.iGetEffect("HIFAC_PICTUREMODE"));
                }
                break;
            case 4:
                setAudioModeString(mNavIntegration.iGetEffect("HIFAC_SOUNDMODE"));
                break;
            case 5:
                if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                    setZoomModeString(mNavIntegration.iGetEffect("HIFAC_ZOOMMODE_FOR_EM"));
                } else {
                    setZoomModeString(mNavIntegration.iGetEffect("HIFAC_ZOOMMODE"));
                }
                break;
            case 6:
                if (FacPropertyDemo.HIFAC_MARKET_JP) {
                    if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_650) {
                        this.mModel_ShortTip_TV.setText(mNavIntegration.iGetEffect("HIFAC_TVS_BALANCE_LIKE_650"));
                    } else if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_550) {
                        this.mModel_ShortTip_TV.setText(mNavIntegration.iGetEffect("HIFAC_TVS_BALANCE_LIKE_550"));
                    } else if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_850) {
                        this.mModel_ShortTip_TV.setText(mNavIntegration.iGetEffect("HIFAC_TVS_BALANCE_LIKE_850"));
                    }
                } else if (mcf.getSubWooferTotal() == 1) {
                    this.mModel_ShortTip_TV.setText(mNavIntegration.iGetEffect("HIFAC_BALANCE_DOUBLE_AMP"));
                } else if (mcf.getSubWooferTotal() == 2) {
                    this.mModel_ShortTip_TV.setText(mNavIntegration.iGetEffect("HIFAC_BALANCE_MULTI_AMP"));
                } else if (mcf.getSubWooferTotal() > 2) {
                    this.mModel_ShortTip_TV.setText(mNavIntegration.iGetEffect("HIFAC_BALANCE_UNIV_MULTI_AMP"));
                } else {
                    this.mModel_ShortTip_TV.setText(mNavIntegration.iGetEffect("HIFAC_BALANCE"));
                }
                break;
            case 7:
                this.mModel_ShortTip_TV.setText(mNavIntegration.iGetEffect("HIFAC_F1_VOLUME") + "");
                break;
            case 8:
                setRGBGainAndOffsetModeString(0, mNavIntegration.iGetEffect("HIFAC_RDRV_PLUS"));
                break;
            case 9:
                setRGBGainAndOffsetModeString(6, mNavIntegration.iGetEffect("HIFAC_RDRV_MINUS"));
                break;
            case 10:
                setRGBGainAndOffsetModeString(1, mNavIntegration.iGetEffect("HIFAC_GDRV_PLUS"));
                break;
            case 11:
                setRGBGainAndOffsetModeString(7, mNavIntegration.iGetEffect("HIFAC_GDRV_MINUS"));
                break;
            case 12:
                setRGBGainAndOffsetModeString(2, mNavIntegration.iGetEffect("HIFAC_BDRV_PLUS"));
                break;
            case 13:
                setRGBGainAndOffsetModeString(8, mNavIntegration.iGetEffect("HIFAC_BDRV_MINUS"));
                break;
            case 14:
                setRGBGainAndOffsetModeString(3, mNavIntegration.iGetEffect("HIFAC_RCUT_PLUS"));
                break;
            case 15:
                setRGBGainAndOffsetModeString(9, mNavIntegration.iGetEffect("HIFAC_RCUT_MINUS"));
                break;
            case 16:
                setRGBGainAndOffsetModeString(4, mNavIntegration.iGetEffect("HIFAC_GCUT_PLUS"));
                break;
            case 17:
                setRGBGainAndOffsetModeString(10, mNavIntegration.iGetEffect("HIFAC_GCUT_MINUS"));
                break;
            case 18:
                setRGBGainAndOffsetModeString(5, mNavIntegration.iGetEffect("HIFAC_BCUT_PLUS"));
                break;
            case 19:
                setRGBGainAndOffsetModeString(11, mNavIntegration.iGetEffect("HIFAC_BCUT_MINUS"));
                break;
            case 20:
                Log.i("Fac_FacRootMenu", "enter : factoryOk()");
                factoryOk();
                break;
            case 21:
                factoryAging(true);
                break;
            case 22:
                set2DTo3DModeString(mNavIntegration.iGetEffect("HIFAC_2DTO3D"));
                break;
            case 23:
                this.mModel_ShortTip_TV.setText(this.selectFangChuanHuoString);
                break;
            case 24:
                setSavingModeString(mNavIntegration.iGetEffect("HIFAC_SAVINGMODE"));
                break;
            case 25:
                setSavingModeString(mNavIntegration.iGetEffect("HIFAC_AUTOTEST_SAVINGMODE"));
                break;
            case 26:
                setDTVTypeString(mNavIntegration.iGetEffect("FACTORY_DTVTYPE"));
                break;
            case 27:
                setSpeakerModeString(mNavIntegration.iGetEffect("HIFAC_SPEAKER"));
                break;
            case 28:
                setWBPanel(mNavIntegration.iGetEffect("HIFAC_PNL_SEL"));
                break;
            case 29:
                setTunerModeString(mNavIntegration.iGetEffect("HIFAC_TUNERMODE"));
                break;
            case 30:
                setLogoString();
                break;
        }
    }

    private void registeListeners() {
        this.mSourceListView.setOnKeyListener(this.mSourceItemKeyLsner);
    }

    private void sendMessageDelayed(int i) {
        Message message = new Message();
        message.what = i;
        this.mhHandler.removeMessages(i);
        this.mhHandler.sendMessageDelayed(message, 4000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMessageDelayed(int i, int i2) {
        Message message = new Message();
        message.what = i;
        this.mhHandler.removeMessages(i);
        this.mhHandler.sendMessageDelayed(message, i2);
    }

    private void set2DTo3DModeString(int i) {
        String[] stringArray = getResources().getStringArray(R.array.facmenu_3dmode_std);
        if (i < stringArray.length) {
            this.mModel_ShortTip_TV.setText(stringArray[i]);
        } else {
            this.mModel_ShortTip_TV.setText(stringArray[0]);
        }
    }

    private void setAudioModeString(int i) {
        String[] stringArray = getResources().getStringArray(R.array.designmenu_audiomode);
        if (i < stringArray.length && i > -1) {
            this.mModel_ShortTip_TV.setText(stringArray[i]);
        } else {
            this.mModel_ShortTip_TV.setText(stringArray[0]);
        }
    }

    String getDisplayName(String str) {
        for (FacPropertyDemo.SourceEnum sourceEnum : FacPropertyDemo.SourceEnum.values()) {
            if (sourceEnum.name().equalsIgnoreCase(str)) {
                return sourceEnum.getDisplayName();
            }
        }
        return "ATV";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setChannelInfo() {
        Log.i("Fac_FacRootMenu", "set channel info");
        String currentSourceVideoInfo = mNavIntegration.getCurrentSourceVideoInfo();
        Log.i("Fac_FacRootMenu", "getCurrentSourceVideoInfo:" + currentSourceVideoInfo);
        if (mNavIntegration.isCurrenSourceATV()) {
            Log.i("Fac_FacRootMenu", "is atv");
            this.mInfoNumTV.setText(getResources().getString(R.string.str_factorymenu_atv) + "\n" + mNavIntegration.getCurrentChannelNumberString() + "");
            this.mInfoNameTV.setText("");
            if (currentSourceVideoInfo != null && !currentSourceVideoInfo.isEmpty()) {
                this.mInfoNameTV.setText(currentSourceVideoInfo);
            } else {
                this.mhHandler.removeMessages(26);
                this.mhHandler.sendEmptyMessageDelayed(26, 1500L);
            }
        } else if (mNavIntegration.isCurrenSourceDTV()) {
            Log.i("Fac_FacRootMenu", "is dtv");
            this.mInfoNumTV.setText(getResources().getString(R.string.str_factorymenu_dtv) + "\n" + mNavIntegration.getCurrentChannelNumberString() + "");
            this.mInfoNameTV.setText("");
            if (currentSourceVideoInfo != null && !currentSourceVideoInfo.isEmpty()) {
                this.mInfoNameTV.setText(currentSourceVideoInfo);
            } else {
                this.mhHandler.removeMessages(26);
                this.mhHandler.sendEmptyMessageDelayed(26, 1500L);
            }
        } else if (mNavIntegration.isCurrenSourceTV()) {
            Log.i("Fac_FacRootMenu", "is tv");
            this.mInfoNumTV.setText(getResources().getString(R.string.str_factorymenu_tv) + "\n" + mNavIntegration.getCurrentChannelNumberString() + "");
            this.mInfoNameTV.setText("");
            if (currentSourceVideoInfo != null && !currentSourceVideoInfo.isEmpty()) {
                this.mInfoNameTV.setText(currentSourceVideoInfo);
            } else {
                this.mhHandler.removeMessages(26);
                this.mhHandler.sendEmptyMessageDelayed(26, 1500L);
            }
        } else {
            this.mInfoNumTV.setText(getDisplayName(mNavIntegration.getCurrentInputSource()));
            String displayName = getDisplayName(mNavIntegration.getCurrentInputSource());
            if (sourceStringEnglishToChineseMapper(displayName) == -1) {
                this.mInfoNumTV.setText(displayName);
            } else {
                this.mInfoNumTV.setText(sourceStringEnglishToChineseMapper(displayName));
            }
        }
        this.mInfoBottomTV.setVisibility(8);
        this.mInfoNumTV.setVisibility(0);
        this.mInfoNameTV.setVisibility(0);
        this.mInfoBottomLeftTV.setVisibility(0);
        this.mInfoBottomRightTV.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateInfoNameTV(boolean z) {
        runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.43
            @Override // java.lang.Runnable
            public void run() {
                Log.i("Fac_FacRootMenu", "enter updateInfoNameTV mIsFirstShowM is" + FacRootMenu.this.mIsFirstShowM);
                if (FacRootMenu.this.mIsFirstShowM) {
                    return;
                }
                FacRootMenu.this.showSimpleInfoBar();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPicModeString(int i) {
        String[] stringArray;
        if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            if (FacPropertyDemo.HIFAC_MARKET_TW) {
                stringArray = getResources().getStringArray(R.array.designmenu_picturemode_for_TW_Toshiba);
            } else {
                stringArray = getResources().getStringArray(R.array.designmenu_picturemode_for_em);
            }
        } else {
            stringArray = getResources().getStringArray(R.array.designmenu_picturemode);
        }
        if (i < stringArray.length && i > -1) {
            this.mModel_ShortTip_TV.setText(stringArray[i]);
        } else {
            this.mModel_ShortTip_TV.setText(stringArray[0]);
        }
    }

    private void setRGBGainAndOffsetModeString(int i, int i2) {
        this.mModel_ShortTip_Title.setText(getResources().getStringArray(R.array.facmenu_rgbgainandoffset_array)[i]);
        this.mModel_ShortTip_TV.setText(i2 + "");
    }

    private void setSavingModeString(int i) {
        Log.i("Fac_FacRootMenu", "setSavingModeString value = " + i);
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            String[] stringArray = getResources().getStringArray(R.array.facmenu_savingmode_tvs);
            if (i < stringArray.length && i > -1) {
                this.mModel_ShortTip_TV.setText(stringArray[i]);
                return;
            } else {
                this.mModel_ShortTip_TV.setText(stringArray[0]);
                return;
            }
        }
        String[] stringArray2 = getResources().getStringArray(R.array.facmenu_savingmode);
        if (i < stringArray2.length && i > -1) {
            this.mModel_ShortTip_TV.setText(stringArray2[i]);
        } else {
            this.mModel_ShortTip_TV.setText(stringArray2[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSourceListData() {
        this.mSourceList = this.mSourceListView.getCurrentList();
        if (FacPropertyDemo.HIFAC_DTV_TYPES_SUPPORT == FacPropertyDemo.DtvSupportType.DTV_NOT_SUPPORT) {
            for (int i = 0; i < this.mSourceList.size(); i++) {
                if (this.mSourceList.get(i).equals("DTV")) {
                    this.mSourceList.remove(i);
                    break;
                }
            }
        }
        SourceListAdapter sourceListAdapter = new SourceListAdapter(this, this.mSourceList, this.mSourceSelectedIcon, this.mSourceUnSelectedIcon);
        this.mSourceListAdapter = sourceListAdapter;
        this.mSourceListView.setAdapter((ListAdapter) sourceListAdapter);
    }

    private void setSpeakerModeString(int i) {
        String[] stringArray = getResources().getStringArray(R.array.facmenu_speakermode);
        if (i < stringArray.length && i > -1) {
            this.mModel_ShortTip_TV.setText(stringArray[i]);
        } else {
            this.mModel_ShortTip_TV.setText(stringArray[0]);
        }
    }

    private void setDTVTypeString(int i) {
        String[] stringArray = getResources().getStringArray(R.array.facmenu_dtvtype);
        if (i < stringArray.length && i > -1) {
            this.mModel_ShortTip_TV.setText(stringArray[i]);
        } else {
            this.mModel_ShortTip_TV.setText(stringArray[0]);
        }
    }

    private void setTestPatternMode() {
        hideAll();
        mcf.adjustTestPattern(true);
        setTestPattern();
    }

    private void adjustPatternMode(boolean z) {
        mcf.adjustTestPattern(z);
        setTestPattern();
    }

    private void setTestPattern() {
        if (mcf.mTestPattern_Mode == MenuConfigManager.TestPattern.OFF) {
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
        switch (AnonymousClass60.$SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern[mcf.mTestPattern_Mode.ordinal()]) {
            case 1:
                mcf.setTestPattern(1, 255, 0, 0);
                break;
            case 2:
                mcf.setTestPattern(1, 0, 255, 0);
                break;
            case 3:
                mcf.setTestPattern(1, 0, 0, 255);
                break;
            case 4:
                mcf.setTestPattern(1, 255, 255, 255);
                break;
            case 5:
                if (FacPropertyDemo.HIFAC_LOCALDIMMING_SUPPORT) {
                    Log.i("Fac_FacRootMenu", "black card to set local dimming false 1");
                    mcf.setBackLightOn(true);
                    this.mLocalDimmingValue = mcf.getLocalDimmingState();
                    mcf.setLocalDimmingSwitch(false);
                }
                mcf.setTestPattern(1, 0, 0, 0);
                break;
            case 6:
                mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.test_pattern);
                this.mFacBGColorView.setBackgroundResource(R.drawable.test_pattern);
                mcf.setTestPattern(0, 0, 0, 0);
                break;
            default:
                mcf.setTestPattern(0, 0, 0, 0);
                mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
                this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
                if (mcf.getDefault("HIFAC_STATUS") == 0) {
                    this.mhHandler.removeMessages(0);
                    this.mhHandler.removeMessages(4);
                    finish();
                } else {
                    Log.i("Fac_FacRootMenu", "local dimming flag = " + FacPropertyDemo.HIFAC_LOCALDIMMING_SUPPORT);
                    if (FacPropertyDemo.HIFAC_LOCALDIMMING_SUPPORT) {
                        mcf.setBackLightOn(false);
                        mcf.setLocalDimmingState(this.mLocalDimmingValue);
                    }
                    showBigMMode();
                }
                break;
        }
    }

    /* JADX INFO: renamed from: com.hisense.uifac.FacRootMenu$60, reason: invalid class name */
    static /* synthetic */ class AnonymousClass60 {
        static final /* synthetic */ int[] $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern;

        static {
            int[] iArr = new int[MenuConfigManager.TestPattern.values().length];
            $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern = iArr;
            try {
                iArr[MenuConfigManager.TestPattern.RED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern[MenuConfigManager.TestPattern.GREEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern[MenuConfigManager.TestPattern.BLUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern[MenuConfigManager.TestPattern.WHITE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern[MenuConfigManager.TestPattern.BLACK.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern[MenuConfigManager.TestPattern.CIRCLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern[MenuConfigManager.TestPattern.OFF.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private void setWBPanel(int i) {
        String[] stringArray = getResources().getStringArray(R.array.factorymenu_panelselect);
        if (i < stringArray.length) {
            this.mModel_ShortTip_TV.setText(stringArray[i]);
        } else {
            this.mModel_ShortTip_TV.setText(stringArray[0]);
        }
    }

    private void showLightSensorStatusDialog() {
        String tvsLightSensor;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            tvsLightSensor = mcf.getTvsLightSensor();
        } else {
            tvsLightSensor = "" + this.mFacFunction_Adapter.getLightSensorValue();
        }
        builder.setMessage(((Object) getResources().getText(R.string.str_light_sensor_status)) + ": " + tvsLightSensor);
        builder.setCancelable(true);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.FacRootMenu.44
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setZoomModeString(int i) {
        String[] stringArray;
        if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            stringArray = getResources().getStringArray(R.array.zoom_mode_array_for_em);
        } else {
            stringArray = getResources().getStringArray(R.array.zoom_mode_array);
        }
        Log.i("Fac_FacRootMenu", "setAspectString value ==  " + i);
        if (i < stringArray.length) {
            Log.i("Fac_FacRootMenu", "setAspectString  ");
            this.mModel_ShortTip_TV.setText(stringArray[i]);
        } else {
            Log.e("Fac_FacRootMenu", "aspectTmp error");
            this.mModel_ShortTip_TV.setText(stringArray[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBasicInfoBar() {
        hideAll();
        this.mCommonInfoTopRL.setBackgroundResource(R.drawable.nav_infobar_basic_bg);
        this.mSimpleInfoTopRL.setBackgroundResource(R.drawable.translucent_background);
        this.mModel_InfoBar_RL.setVisibility(0);
        this.mCommonInfoTopRL.setVisibility(0);
        this.mDetailsInfoBottomTV.setVisibility(4);
        this.mSimpleInfoTopRL.setVisibility(0);
        setChannelInfo();
        sendMessageDelayed(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBigMMode() {
        Log.i("Fac_FacRootMenu", "Enter showBigMMode");
        canncelMessage(2);
        canncelMessage(4);
        hideAll();
        if (!mButton_FactoryMenuRoot.hasFocus()) {
            mButton_FactoryMenuRoot.setVisibility(0);
            mButton_FactoryMenuRoot.requestFocus();
            if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                mButton_FactoryMenuRoot.setBackgroundColor(-7829368);
            }
        }
        if (this.mIsMacKeyOK) {
            showLogoAfterBigMForSA();
        }
    }

    private void hideBigMMode() {
        Log.i("Fac_FacRootMenu", "Enter hideBigMMode");
        hideAll();
        canncelMessage(2);
        canncelMessage(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSimpleInfoBar() {
        Log.i("Fac_FacRootMenu", "enter showSimpleInfoBar");
        hideAll();
        this.mSimpleInfoTopRL.setVisibility(0);
        this.mCommonInfoTopRL.setVisibility(0);
        this.mModel_InfoBar_RL.setVisibility(0);
        this.mCommonInfoTopRL.setBackgroundResource(R.drawable.translucent_background);
        this.mSimpleInfoTopRL.setBackgroundResource(R.drawable.nav_infobar_smaple_bg);
        this.mDetailsInfoBottomTV.setVisibility(4);
        if (FacPropertyDemo.SourceEnum.TV == mcf.getCurrentInputSourceEnum() || FacPropertyDemo.SourceEnum.ATV == mcf.getCurrentInputSourceEnum() || FacPropertyDemo.SourceEnum.DTV == mcf.getCurrentInputSourceEnum()) {
            setChannelInfo();
        } else {
            String currentSourceVideoInfo = mNavIntegration.getCurrentSourceVideoInfo();
            this.mInfoNumTV.setText(getDisplayName(mNavIntegration.getCurrentInputSource()));
            this.mInfoNameTV.setText("");
            if (currentSourceVideoInfo != null && !currentSourceVideoInfo.isEmpty()) {
                this.mInfoNameTV.setText(currentSourceVideoInfo);
            } else {
                this.mhHandler.removeMessages(26);
                this.mhHandler.sendEmptyMessageDelayed(26, 1500L);
            }
        }
        sendMessageDelayed(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSourceInfoBar() {
        hideAll();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        showSimpleInfoBar();
        this.mInfoBottomLeftTV.setVisibility(8);
        this.mInfoBottomRightTV.setVisibility(8);
        this.mInfoBottomTV.setVisibility(8);
    }

    private int sourceStringEnglishToChineseMapper(String str) {
        if (!getResources().getConfiguration().locale.toString().contains("zh") || str == null) {
            return -1;
        }
        if (str.equalsIgnoreCase("ATV")) {
            return R.string.str_factory_source_atv;
        }
        if (str.equalsIgnoreCase("DTV")) {
            return R.string.str_factory_source_dtv;
        }
        if (str.equalsIgnoreCase("AV1")) {
            return R.string.str_factory_source_av1;
        }
        if (str.equalsIgnoreCase("AV2")) {
            return R.string.str_factory_source_av2;
        }
        if (str.equalsIgnoreCase("COMPONENT")) {
            return R.string.str_factory_source_component;
        }
        if (str.equalsIgnoreCase(MtkTvRatingConvert2Goo.RATING_STR_AV)) {
            return R.string.str_factorymenu_av;
        }
        return -1;
    }

    private void showSourceMode() {
        hideAll();
        this.m3DlinearList.setVisibility(8);
        this.mLayout_Bottom_Two_Colmun_LL.setVisibility(0);
        this.mModel_SourceList_RL.setVisibility(0);
        this.mLayout_Bottom_Two_Colmun_LL.requestFocus();
        this.mModel_SourceList_RL.requestFocus();
        this.mSourceListView.requestFocus();
        this.viewMode = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTipMode(boolean z) {
        hideAll();
        this.mLayout_Bottom_Two_Colmun_LL.setVisibility(0);
        this.mModel_ShortTip_LL.setVisibility(0);
        this.mModel_ShortTip_TV.setVisibility(0);
        if (z) {
            this.mModel_ShortTip_Title.setVisibility(8);
        } else {
            this.mModel_ShortTip_Title.setVisibility(0);
        }
        this.viewMode = 3;
        sendMessageDelayed(2);
    }

    private void showTipModeForBalance(boolean z) {
        hideAll();
        this.mLayout_Bottom_Two_Colmun_LL.setVisibility(0);
        this.mModel_ShortTip_LL.setVisibility(0);
        this.mModel_ShortTip_TV.setVisibility(0);
        if (z) {
            this.mModel_ShortTip_Title.setVisibility(8);
        } else {
            this.mModel_ShortTip_Title.setVisibility(0);
        }
        this.viewMode = 3;
    }

    public void setPatternAlreaySet() {
        Log.i("Fac_FacRootMenu", "setPatternAlreaySet");
        if (FacPropertyDemo.HIFAC_VCOM_SUPPORT == 0) {
            mcf.displayHisToast("This machine not support vcom!!!");
            mIsAlreadySet = false;
            return;
        }
        hideAll();
        if (!mIsHaveVcomBitmap) {
            mcf.displayHisToast("There is no VCOM picture in specific path");
            mIsAlreadySet = false;
            return;
        }
        mIsAlreadySet = true;
        if (FacPropertyDemo.HIFAC_MIC_SUPPORT && !mcf.isZDMicTestOK()) {
            mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
            this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
        }
        if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
            MenuConfigManager menuConfigManager = mcf;
            menuConfigManager.setVcomPanelType(menuConfigManager.getVcomType());
        }
        this.mFacFunction_Adapter.setEnterVcomVideoParameter();
        sendMessageDelayed(19, 0);
        int i = mcf.getDefault("HIFAC_SAVINGMODE");
        this.mCurrentValue = i;
        if (i != SavingMode.BRIGHT.ordinal()) {
            mcf.setValue("HIFAC_SAVINGMODE", SavingMode.BRIGHT.ordinal());
        }
        mcf.setMtkSourceStatus(false);
        this.mTvStoped = true;
        if (!FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT || mcf.getVcomType() == 1) {
            mcf.setVcomCardParam("/hisprod/config/factory/flicker.bmp", 1);
        } else {
            mcf.setVcomCardParam("/hisprod/config/factory/2kflicker.bmp", 1);
        }
        new Thread(new Runnable(this) { // from class: com.hisense.uifac.FacRootMenu.45
            @Override // java.lang.Runnable
            public void run() {
            }
        }).start();
    }

    private void sendMuteBroadcast(boolean z) {
        Log.i("Fac_FacRootMenu", "sendbroadcast hide mute icon");
        Intent intent = new Intent("com.hisense.intent.HIDE_MUTE_ICON");
        intent.putExtra("hide_mute_icon", z);
        intent.addFlags(268435456);
        sendBroadcast(intent);
    }

    public void setPatternNotSetYet() {
        mButton_FactoryMenuRoot.setVisibility(0);
        mIsAlreadySet = false;
        this.mFacFunction_Adapter.setExitVcomVideoParameter();
        if (this.mCurrentValue != SavingMode.BRIGHT.ordinal()) {
            mcf.setValue("HIFAC_SAVINGMODE", this.mCurrentValue);
        }
        mcf.closeVcomCardParam();
        if (FacPropertyDemo.HIFAC_MIC_SUPPORT && !mcf.isZDMicTestOK()) {
            this.mhHandler.sendEmptyMessage(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_ON);
        }
        if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
            mcf.setVcomPanelType(0);
        }
        if (this.mTvStoped) {
            mcf.setMtkSourceStatus(true);
            Log.i("Fac_FacRootMenu", "TV started");
            this.mTvStoped = false;
        }
    }

    private void showShortTipLL(int i) {
        this.mModel_ShortTip_TV.setTextColor(getResources().getColor(R.color.white));
        this.mModel_ShortTip_TV.setTextSize(getResources().getDimension(R.dimen.nav_textsize_normal));
        this.mModel_ShortTip_TV.setBackgroundDrawable(getResources().getDrawable(R.drawable.nav_shorttip_bg));
        this.mModel_ShortTip_Title.setGravity(17);
        this.mModel_ShortTip_TV.setGravity(17);
        this.mModel_ShortTip_TV.setText(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFileList() {
        if (isShowFileListInProgress) {
            return;
        }
        new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.46
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                File[] fileArrListFiles;
                boolean unused = FacRootMenu.isShowFileListInProgress = true;
                final ArrayList arrayList = new ArrayList();
                List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(FacRootMenu.this);
                Log.i("Fac_FacRootMenu", "The mounted paths is : " + externalStoragePath);
                if (externalStoragePath != null && !externalStoragePath.isEmpty()) {
                    Iterator<String> it = externalStoragePath.iterator();
                    while (it.hasNext()) {
                        File file = new File(it.next());
                        if (file.exists() && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                            for (File file2 : fileArrListFiles) {
                                if (!file2.isDirectory()) {
                                    if (arrayList.size() >= 45) {
                                        break;
                                    } else {
                                        arrayList.add(file2);
                                    }
                                }
                            }
                        }
                    }
                }
                FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.46.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (arrayList.size() <= 0) {
                            FacRootMenu.mcf.displayHisToast(FacRootMenu.this.getApplicationContext().getString(R.string.str_no_usb_mounted));
                            boolean unused2 = FacRootMenu.isShowFileListInProgress = false;
                            return;
                        }
                        new FileListView(FacRootMenu.this).show(arrayList);
                        boolean unused3 = FacRootMenu.isShowFileListInProgress = false;
                        if (FacRootMenu.this.mSharedPreferences == null) {
                            FacRootMenu.this.initSharedPreference();
                        }
                        FacRootMenu.this.mSharedPreferences.edit().putBoolean(FacRootMenu.this.getResources().getString(R.string.str_test_info_dmp_file), true).commit();
                        if (FacPropertyDemo.HIFAC_MARKET_SA) {
                            FacRootMenu.this.mFacFunction_Adapter.setDMPStatusToLog();
                        }
                    }
                });
            }
        }).start();
    }

    public void parsePopertyXMLFile() {
        Log.i("Fac_FacRootMenu", "begin to parsePopertyXMLFile");
        InputStream hisBugConfigInputStream = getHisBugConfigInputStream();
        if (hisBugConfigInputStream == null) {
            return;
        }
        try {
            SAXParserFactory.newInstance().newSAXParser().parse(hisBugConfigInputStream, new PropertyParseHandler(this));
            hisBugConfigInputStream.close();
            Log.i("Fac_FacRootMenu", "end to parsePopertyXMLFile");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e2) {
            e2.printStackTrace();
        } catch (SAXException e3) {
            e3.printStackTrace();
        }
    }

    private void initReceiverAsync() {
        new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.47
            @Override // java.lang.Runnable
            public void run() {
                Log.i("Fac_FacRootMenu", "initReceiver begin");
                Intent intent = new Intent();
                intent.setAction("Enter_AGING_MODE_IN_FACTORY");
                Log.i("Fac_FacRootMenu", "sendBroadcast " + intent);
                FacRootMenu.this.sendBroadcast(intent);
                FacRootMenu.this.mShutDownReceiver = FacRootMenu.this.new ShutDownReceiver();
                FacRootMenu facRootMenu = FacRootMenu.this;
                facRootMenu.registerReceiver(facRootMenu.mShutDownReceiver, new IntentFilter("com.hisense.action.POWER_OFF"));
                FacRootMenu.this.mSystemkeyReceiver = FacRootMenu.this.new SystemkeyReceiver();
                IntentFilter intentFilter = new IntentFilter("tv.policy.SYSTEM_KEY");
                intentFilter.setPriority(1000);
                FacRootMenu facRootMenu2 = FacRootMenu.this;
                facRootMenu2.registerReceiver(facRootMenu2.mSystemkeyReceiver, intentFilter);
                AnonymousClass1 anonymousClass1 = null;
                FacRootMenu.this.mHisenseSystemkeyReceiver = new HisenseSystemkeyReceiver(FacRootMenu.this, anonymousClass1);
                IntentFilter intentFilter2 = new IntentFilter("com.jamdeo.tv.policy.SYSTEM_KEY");
                FacRootMenu facRootMenu3 = FacRootMenu.this;
                facRootMenu3.registerReceiver(facRootMenu3.mHisenseSystemkeyReceiver, intentFilter2);
                FacRootMenu.this.mCheckKeyStatusReceiver = new CheckKeyStatusReceiver(FacRootMenu.this, anonymousClass1);
                IntentFilter intentFilter3 = new IntentFilter("com.hisense.broadcom.CHECK_KEY_STATUS");
                FacRootMenu facRootMenu4 = FacRootMenu.this;
                facRootMenu4.registerReceiver(facRootMenu4.mCheckKeyStatusReceiver, intentFilter3);
                Log.i("Fac_FacRootMenu", "initReceiver end");
            }
        }).start();
    }

    public void setDTSSwitch(int i) {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        mcf.setDTSSwitch(i);
    }

    public InputStream getHisBugConfigInputStream() {
        try {
            File file = new File("/hisprod/config/factory/factory_init_property.xml");
            if (file.exists() && file.isFile()) {
                this.bugConfigInputStream = new FileInputStream(file);
                Log.i("Fac_FacRootMenu", "get source config file=" + file.getCanonicalPath());
                return this.bugConfigInputStream;
            }
            this.bugConfigInputStream = getResources().getAssets().open("init_property.xml");
            Log.i("Fac_FacRootMenu", "getHisBugConfigInputStream=" + this.bugConfigInputStream.toString());
            return this.bugConfigInputStream;
        } catch (IOException e) {
            Log.w("Fac_FacRootMenu", "ioexception " + e);
            this.bugConfigInputStream = null;
        }
    }

    String readFile(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            bufferedReader.close();
            return line;
        } catch (IOException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToWriteMacWithCB(WriteCallback writeCallback) {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        mcf.setMacAddrFromUDiskWithCB(writeCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToWriteDeviceIDWithCB(WriteCallback writeCallback) {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        mcf.setDeviceIDFromUDiskWithCB(writeCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToWriteChinaDRMWithCB(WriteCallback writeCallback) {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        mcf.setChinaDrmKeyFromUDiskWithCB(writeCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToWriteHDCPKeyWithCB(WriteCallback writeCallback) {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        mcf.setHDCPKeyFromUDiskWithCB(writeCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToWritePlayReadyKeyWithCB(WriteCallback writeCallback) {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        mcf.setPlayReadyFromUDiskWithCB(writeCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToWriteHDCP14KeyWithCB(WriteCallback writeCallback) {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        mcf.setHDCP14KeyFromUDiskWithCB(writeCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToWriteHDCP2xKeyWithCB(WriteCallback writeCallback) {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        mcf.setHDCP2xKeyFromUDiskWithCB(writeCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToWriteAttestationKeyWithCB(WriteCallback writeCallback) {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        mcf.setAttestationFromTV(writeCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToWriteMarlinKeyWithCB(WriteCallback writeCallback) {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        mcf.setMarlinKeyFromUDiskWithCB(writeCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToWriteDtcpipKeyWithCB(WriteCallback writeCallback) {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        mcf.setDTCPIPKeyFromUDiskWithCB(writeCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToWriteSQVKeyWithCB(WriteCallback writeCallback) {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        mcf.setSQVKeyFromUDiskWithCB(writeCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToWritePVRKeyWithCB(WriteCallback writeCallback) {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        mcf.setPVRKeyFromUDiskWithCB(writeCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToWriteMGKKeyWithCB(WriteCallback writeCallback) {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        mcf.setMGKKeyFromUDisk(writeCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToWriteCIPlusKeyWithCB(WriteCallback writeCallback) {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        mcf.setCIPlusKeyFromUDiskWithCB(writeCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToWriteQDAKeyWithCB(WriteCallback writeCallback) {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        mcf.setQDAKeyFromUDisk(writeCallback);
    }

    private boolean testWhetherKeyIsOK() {
        if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT) {
            this.mWideVineOk = !isDeviceIDNull();
        }
        if (FacPropertyDemo.HIFAC_PLAY_READY_KEY_SUPPORT) {
            this.mPlayReadyOk = !isPlayReadyNull();
        }
        if (FacPropertyDemo.HIFAC_HDCP_1_4_KEY_SUPPORT) {
            this.mHdcp14Ok = !isHdcp14KeyNull();
        }
        if (FacPropertyDemo.HIFAC_HDCP_2_X_KEY_SUPPORT) {
            this.mHdcp22Ok = !isHdcp2xKeyNull();
        }
        if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
            this.mNetflixOk = !isNetflixNull();
        } else {
            this.mNetflixOk = true;
        }
        if (FacPropertyDemo.HIFAC_ATTEST_KEY_SUPPORT) {
            this.mAttestationOk = !isAttestationNull();
        }
        if (mcf.isSupportQDAKey()) {
            this.mQDAOk = !isQDANull();
        } else {
            this.mQDAOk = true;
        }
        if (!FacPropertyDemo.HIFAC_MARKET_JP) {
            return this.mWideVineOk && this.mPlayReadyOk && this.mHdcp14Ok && this.mHdcp22Ok && this.mNetflixOk && this.mAttestationOk && this.mQDAOk;
        }
        this.mMarlinOk = !isMarlinNull();
        this.mDtcpIpOk = !isDTCPIPNull();
        boolean z = !isPVRNull();
        this.mPVROk = z;
        return this.mWideVineOk && this.mPlayReadyOk && this.mHdcp14Ok && this.mHdcp22Ok && this.mNetflixOk && this.mAttestationOk && this.mMarlinOk && this.mDtcpIpOk && z && this.mQDAOk;
    }

    class MacKeyWriter {
        private boolean mIsMacOK = true;
        private boolean mIsDeviceIDOK = true;
        private boolean mIsPlayReadyOK = true;
        private boolean mIsHdcp14OK = true;
        private boolean mIsHdcp2xOK = true;
        private boolean mIsAttestationOK = true;
        private boolean mIsMGKOK = true;
        private boolean mIsDtcpIpOK = true;
        private boolean mIsMarlinOK = true;
        private boolean mIsPVROK = true;
        private boolean mIsQDAOK = true;
        private boolean mIsEMMCOK = true;
        private boolean mIsTconTempVrOK = true;
        private StringBuffer mErrMsg = new StringBuffer();

        MacKeyWriter() {
        }

        public void autoTryToWrite() {
            if (FacRootMenu.mcf.FindFileOnUSB(FacRootMenu.this.mFacFunction_Adapter.getMacFileNameInUsbDisk()) == null && FacRootMenu.mcf.FindFileOnUSB(FacRootMenu.this.mFacFunction_Adapter.getProjectIdFileNameInUsbDisk()) != null) {
                String projectId = FacRootMenu.mcf.getProjectId();
                Log.i("Fac_FacRootMenu", "autoTryToWrite  currentProjectid = " + projectId);
                if (projectId == null || "000".equals(projectId) || "".equals(projectId)) {
                    FacRootMenu.this.autoTryToWriteProjectId();
                }
            }
            this.mIsMacOK = !FacRootMenu.this.isMacAddressDefaultOrIllegal();
            if (FacPropertyDemo.HIFAC_TEE_ENVIRON_SUPPORT) {
                if (!FacRootMenu.mcf.isKeyWriteProtecEnabled()) {
                    if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT) {
                        this.mIsDeviceIDOK = !FacRootMenu.this.isDeviceIDNull();
                    }
                    if (FacPropertyDemo.HIFAC_CHINA_DRM_KEY_SUPPORT) {
                        FacRootMenu.this.isChinaDrmKeyNull();
                    }
                    if (FacPropertyDemo.HIFAC_HDCP_KEY_SUPPORT) {
                        FacRootMenu.this.isHdcpKeyNull();
                    }
                }
            } else {
                if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT) {
                    this.mIsDeviceIDOK = !FacRootMenu.this.isDeviceIDNull();
                }
                if (FacPropertyDemo.HIFAC_CHINA_DRM_KEY_SUPPORT) {
                    FacRootMenu.this.isChinaDrmKeyNull();
                }
                if (FacPropertyDemo.HIFAC_HDCP_KEY_SUPPORT) {
                    FacRootMenu.this.isHdcpKeyNull();
                }
                this.mIsPlayReadyOK = !FacRootMenu.this.isPlayReadyNull();
                this.mIsHdcp14OK = !FacRootMenu.this.isHdcp14KeyNull();
                this.mIsHdcp2xOK = !FacRootMenu.this.isHdcp2xKeyNull();
                this.mIsAttestationOK = !FacRootMenu.this.isAttestationNull();
                this.mIsQDAOK = !FacRootMenu.this.isQDANull();
                if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
                    this.mIsMGKOK = !FacRootMenu.this.isNetflixNull();
                }
            }
            if (FacPropertyDemo.HIFAC_IS_OLED) {
                this.mIsTconTempVrOK = FacRootMenu.mcf.checkTconTempVr() != 1;
            }
            Log.i("Fac_FacRootMenu", "autoTryToWrite  mIsTconTempVrOK = " + this.mIsTconTempVrOK);
            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                this.mIsDtcpIpOK = !FacRootMenu.this.isDTCPIPNull();
                this.mIsMarlinOK = !FacRootMenu.this.isMarlinNull();
                this.mIsPVROK = !FacRootMenu.this.isPVRNull();
                boolean emmcType = FacRootMenu.mcf.getEmmcType();
                this.mIsEMMCOK = emmcType;
                if (!this.mIsMacOK || !this.mIsDeviceIDOK || !this.mIsPlayReadyOK || !this.mIsHdcp14OK || !this.mIsHdcp2xOK || !this.mIsMGKOK || !this.mIsAttestationOK || !this.mIsDtcpIpOK || !this.mIsMarlinOK || !this.mIsPVROK || !emmcType) {
                    FacRootMenu.mcf.setLogToTvcertificate("auto write key");
                    autoTryToWriteMAC();
                    return;
                }
                FacRootMenu.mcf.setLogToTvcertificate("JP all key ok!!");
                FacRootMenu.this.mIsMacKeyOK = true;
                FacRootMenu.this.mhHandler.sendEmptyMessage(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_NOT_DETECT);
                if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                    FacRootMenu.this.mhHandler.sendEmptyMessageDelayed(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC, FacParamManager.FAC_SCREEN_PROTECT_TIME);
                    return;
                }
                return;
            }
            if (!this.mIsMacOK || !this.mIsDeviceIDOK || !this.mIsPlayReadyOK || !this.mIsHdcp14OK || !this.mIsHdcp2xOK || !this.mIsMGKOK || !this.mIsAttestationOK || !this.mIsQDAOK || !this.mIsTconTempVrOK) {
                FacRootMenu.mcf.setLogToTvcertificate("auto write key");
                if (FacPropertyDemo.HIFAC_IS_OLED) {
                    String str = FacRootMenu.mcf.checkTconTempVr() == 0 ? " OK!" : " NG!";
                    StringBuffer stringBuffer = this.mErrMsg;
                    stringBuffer.append(FacRootMenu.this.getString(R.string.str_factorymenu_tcon_temp_vr));
                    stringBuffer.append(str);
                    stringBuffer.append("\n");
                }
                if (FacPropertyDemo.HIFAC_MARKET_SA) {
                    if (!this.mIsMacOK || !this.mIsDeviceIDOK || !this.mIsHdcp14OK || !this.mIsHdcp2xOK || !this.mIsMGKOK || !this.mIsQDAOK || !this.mIsTconTempVrOK) {
                        if (!this.mIsMacOK || !this.mIsDeviceIDOK || !this.mIsHdcp14OK || !this.mIsHdcp2xOK || !this.mIsMGKOK || !this.mIsQDAOK) {
                            FacRootMenu.mcf.generateEepKeyFile();
                            if (FacRootMenu.mcf.isHavePermKey()) {
                                FacRootMenu.mcf.setLogToTvcertificate("auto write perm key");
                                this.mErrMsg.append(FacRootMenu.mcf.setEepromKeyAfterEnterFac());
                                this.mIsMacOK = !FacRootMenu.this.isMacAddressDefaultOrIllegal();
                                this.mIsPlayReadyOK = !FacRootMenu.this.isPlayReadyNull();
                                this.mIsHdcp14OK = !FacRootMenu.this.isHdcp14KeyNull();
                                this.mIsHdcp2xOK = !FacRootMenu.this.isHdcp2xKeyNull();
                                this.mIsAttestationOK = !FacRootMenu.this.isAttestationNull();
                                this.mIsMGKOK = !FacRootMenu.this.isNetflixNull();
                                this.mIsDeviceIDOK = !FacRootMenu.this.isDeviceIDNull();
                                this.mIsQDAOK = !FacRootMenu.this.isQDANull();
                                showResultMsg();
                                return;
                            }
                            FacRootMenu.mcf.setLogToTvcertificate("no perm key,so auto write key");
                            autoTryToWriteMAC();
                            return;
                        }
                        showResultMsg();
                        return;
                    }
                    if (!this.mIsPlayReadyOK || !this.mIsAttestationOK) {
                        FacRootMenu.mcf.setAttestAndPlayreadyForEeprom();
                        this.mIsAttestationOK = !FacRootMenu.this.isAttestationNull();
                        this.mIsPlayReadyOK = !FacRootMenu.this.isPlayReadyNull();
                        FacRootMenu.this.mIsMacKeyOK = true;
                        FacRootMenu.this.mhHandler.sendEmptyMessage(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_NOT_DETECT);
                        return;
                    }
                    FacRootMenu.this.mIsMacKeyOK = true;
                    FacRootMenu.this.mhHandler.sendEmptyMessage(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_NOT_DETECT);
                    return;
                }
                autoTryToWriteMAC();
                return;
            }
            FacRootMenu.mcf.setLogToTvcertificate("all key ok!!");
            FacRootMenu.this.mIsMacKeyOK = true;
            FacRootMenu.this.mhHandler.sendEmptyMessage(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_NOT_DETECT);
            if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                FacRootMenu.this.mhHandler.sendEmptyMessageDelayed(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC, FacParamManager.FAC_SCREEN_PROTECT_TIME);
            }
        }

        public void manualTryToWrite() {
            manualTryToWriteMac();
        }

        private void manualTryToWriteMac() {
            if (FacRootMenu.this.isMacAddressDefaultOrIllegal()) {
                manualWriteMac();
            } else {
                showWarningDialog(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualWriteMac() {
            FacRootMenu.this.tryToWriteMacWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.1
                @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                public void writeEnd(boolean z, String str) {
                    MacKeyWriter.this.checkMacKey();
                    MacKeyWriter.this.showMacResultDialog(z, str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualTryToWriteDeviceId() {
            if (FacRootMenu.this.isDeviceIDNull()) {
                manualWriteDeviceId();
            } else {
                showWarningDialog(1);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualWriteDeviceId() {
            Log.i("Fac_FacRootMenu", "enter manualWriteDeviceId");
            FacRootMenu.this.tryToWriteDeviceIDWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.2
                @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                public void writeEnd(boolean z, String str) {
                    MacKeyWriter.this.checkMacKey();
                    MacKeyWriter.this.showDeviceIdResultDialog(z, str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualTryToWriteChinaDRM() {
            if (FacRootMenu.this.isChinaDrmKeyNull()) {
                manualWriteChinaDRM();
            } else {
                showDrmExistsDialog();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualTryToWriteHdcpKey() {
            if (FacRootMenu.this.isHdcpKeyNull()) {
                manualWriteHdcpKey();
            } else {
                showHdcpExistsDialog();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualTryToWriteHdcp14Key() {
            if (FacRootMenu.this.isHdcp14KeyNull()) {
                manualWriteHdcp14Key();
            } else {
                showWarningDialog(5);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualTryToWriteHdcp22Key() {
            if (FacRootMenu.this.isHdcp2xKeyNull()) {
                manualWriteHdcp22Key();
            } else {
                showWarningDialog(6);
            }
        }

        private void manualWriteChinaDRM() {
            FacRootMenu.this.tryToWriteChinaDRMWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.3
                @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                public void writeEnd(boolean z, String str) {
                    MacKeyWriter.this.checkMacKey();
                    MacKeyWriter.this.showDrmResultDialog(z, str);
                }
            });
        }

        private void manualWriteHdcpKey() {
            FacRootMenu.this.tryToWriteHDCPKeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.4
                @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                public void writeEnd(boolean z, String str) {
                    MacKeyWriter.this.checkMacKey();
                    MacKeyWriter.this.showHdcpResultDialog(z, str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualTryToWriteNeflixKey() {
            Log.i("Fac_FacRootMenu", "enter manualMGK");
            if (FacRootMenu.this.isNetflixNull()) {
                manualWriteNetflixKey();
            } else {
                showWarningDialog(8);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualWritePlayReadyKey() {
            FacRootMenu.this.tryToWritePlayReadyKeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.5
                @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                public void writeEnd(boolean z, String str) {
                    MacKeyWriter.this.checkMacKey();
                    MacKeyWriter.this.showPlayReadyResultDialog(z, str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualWriteHdcp14Key() {
            Log.i("Fac_FacRootMenu", "enter manualWriteHdcp14Key");
            FacRootMenu.this.tryToWriteHDCP14KeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.6
                @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                public void writeEnd(boolean z, String str) {
                    MacKeyWriter.this.checkMacKey();
                    MacKeyWriter.this.showHdcp14ResultDialog(z, str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualWriteHdcp22Key() {
            Log.i("Fac_FacRootMenu", "enter manualWriteHdcp2xKey");
            FacRootMenu.this.tryToWriteHDCP2xKeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.7
                @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                public void writeEnd(boolean z, String str) {
                    MacKeyWriter.this.checkMacKey();
                    MacKeyWriter.this.showHdcp2xResultDialog(z, str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualWriteCIPlusKey() {
            Log.i("Fac_FacRootMenu", "enter manualWriteCIPlusKey");
            FacRootMenu.this.tryToWriteCIPlusKeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.8
                @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                public void writeEnd(boolean z, String str) {
                    MacKeyWriter.this.checkMacKey();
                    MacKeyWriter.this.showCIPlusResultDialog(z, str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualWriteNetflixKey() {
            FacRootMenu.this.tryToWriteMGKKeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.9
                @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                public void writeEnd(boolean z, String str) {
                    MacKeyWriter.this.checkMacKey();
                    MacKeyWriter.this.showNetflixResultDialog(z, str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualWriteMarlinKey() {
            FacRootMenu.this.tryToWriteMarlinKeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.10
                @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                public void writeEnd(boolean z, String str) {
                    MacKeyWriter.this.checkMacKey();
                    MacKeyWriter.this.showMarlinResultDialog(z, str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualWriteDTCPIPKey() {
            FacRootMenu.this.tryToWriteDtcpipKeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.11
                @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                public void writeEnd(boolean z, String str) {
                    MacKeyWriter.this.checkMacKey();
                    MacKeyWriter.this.showDTCPIPResultDialog(z, str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualWriteSQVKey() {
            FacRootMenu.this.tryToWriteSQVKeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.12
                @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                public void writeEnd(boolean z, String str) {
                    MacKeyWriter.this.checkMacKey();
                    MacKeyWriter.this.showSQVResultDialog(z, str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualWritePVRKey() {
            FacRootMenu.this.tryToWritePVRKeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.13
                @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                public void writeEnd(boolean z, String str) {
                    MacKeyWriter.this.checkMacKey();
                    MacKeyWriter.this.showPVRResultDialog(z, str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualWriteQDAKey() {
            FacRootMenu.this.tryToWriteQDAKeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.14
                @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                public void writeEnd(boolean z, String str) {
                    MacKeyWriter.this.checkMacKey();
                    MacKeyWriter.this.showQDAResultDialog(z, str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualTryToWriteQDAKey() {
            Log.i("Fac_FacRootMenu", "enter manualQDA");
            if (FacRootMenu.this.isQDANull()) {
                manualWriteQDAKey();
            } else {
                showWarningDialog(17);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualTryToWriteMarlinKey() {
            Log.i("Fac_FacRootMenu", "enter manualMarlin");
            if (FacRootMenu.this.isMarlinNull()) {
                manualWriteMarlinKey();
            } else {
                showWarningDialog(13);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualTryToWriteDTCPIPKey() {
            Log.i("Fac_FacRootMenu", "enter manualDTCPIP");
            if (FacRootMenu.this.isDTCPIPNull()) {
                manualWriteDTCPIPKey();
            } else {
                showWarningDialog(12);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void manualTryToWritePVRKey() {
            Log.i("Fac_FacRootMenu", "enter manualPVR");
            if (FacRootMenu.this.isPVRNull()) {
                manualWritePVRKey();
            } else {
                showWarningDialog(15);
            }
        }

        private void showWarningDialog(final int i) {
            Log.i("Fac_FacRootMenu", "showWarningDialog type = " + i);
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.15
                @Override // java.lang.Runnable
                public void run() {
                    FacRootMenu.this.mKeyAlertDialog = null;
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                    int i2 = i;
                    int i3 = R.string.str_facmenu_hdcp22_key_ready_exist;
                    switch (i2) {
                        case 0:
                            i3 = R.string.str_facmenu_mac_exist;
                            break;
                        case 1:
                        case 3:
                        case 11:
                        case 16:
                        default:
                            i3 = R.string.str_facmenu_device_id_exist;
                            break;
                        case 2:
                            i3 = R.string.str_facmenu_china_drm_exist;
                            break;
                        case 4:
                            i3 = R.string.str_facmenu_play_ready_exist;
                            break;
                        case 5:
                        case 9:
                            i3 = R.string.str_facmenu_hdcp14_key_ready_exist;
                            break;
                        case 6:
                        case 10:
                            break;
                        case 7:
                            i3 = R.string.str_facmenu_ciplus_key_ready_exist;
                            break;
                        case 8:
                            i3 = R.string.str_facmenu_mgk_key_ready_exist;
                            break;
                        case 12:
                            i3 = R.string.str_facmenu_dtcpip_key_ready_exist;
                            break;
                        case 13:
                            i3 = R.string.str_facmenu_marlin_key_ready_exist;
                            break;
                        case 14:
                            i3 = R.string.str_facmenu_sqv_key_ready_exist;
                            break;
                        case 15:
                            i3 = R.string.str_facmenu_PVR_key_ready_exist;
                            break;
                        case 17:
                            i3 = R.string.str_facmenu_qda_key_ready_exist;
                            break;
                    }
                    builder.setTitle(i3);
                    builder.setCancelable(true);
                    builder.setNegativeButton(R.string.str_facmenu_resetmac_cancel, new DialogInterface.OnClickListener() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.15.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i4) {
                            AnonymousClass15 anonymousClass15 = AnonymousClass15.this;
                            int i5 = i;
                            if (i5 != 0) {
                                if (i5 == 1) {
                                    MacKeyWriter.this.manualTryToWriteHdcp22Key();
                                } else if (i5 != 2) {
                                    if (i5 == 5) {
                                        FacRootMenu.this.setAttestationAndMGk();
                                    } else if (i5 == 6) {
                                        MacKeyWriter.this.manualTryToWriteHdcp14Key();
                                    } else if (i5 != 8) {
                                        if (i5 == 17) {
                                            MacKeyWriter.this.manualTryToWriteNeflixKey();
                                        } else {
                                            switch (i5) {
                                                case 12:
                                                    if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
                                                        MacKeyWriter.this.manualTryToWriteNeflixKey();
                                                    } else if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT) {
                                                        MacKeyWriter.this.manualTryToWriteDeviceId();
                                                    }
                                                    break;
                                                case 13:
                                                    MacKeyWriter.this.manualTryToWriteDTCPIPKey();
                                                    break;
                                                case 14:
                                                    MacKeyWriter.this.manualTryToWriteNeflixKey();
                                                    break;
                                                case 15:
                                                    MacKeyWriter.this.manualTryToWriteMarlinKey();
                                                    break;
                                            }
                                        }
                                    } else if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT) {
                                        MacKeyWriter.this.manualTryToWriteDeviceId();
                                    }
                                } else if (FacPropertyDemo.HIFAC_HDCP_KEY_SUPPORT) {
                                    MacKeyWriter.this.manualTryToWriteHdcpKey();
                                }
                            } else if (FacPropertyDemo.HIFAC_TEE_ENVIRON_SUPPORT && FacRootMenu.mcf.isKeyWriteProtecEnabled()) {
                                FacRootMenu.mcf.displayHisToast(FacRootMenu.this.getResources().getString(R.string.str_key_write_protect_enable));
                            } else if (!FacPropertyDemo.HIFAC_MARKET_JP) {
                                if (FacRootMenu.mcf.isSupportQDAKey()) {
                                    MacKeyWriter.this.manualTryToWriteQDAKey();
                                } else if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
                                    MacKeyWriter.this.manualTryToWriteNeflixKey();
                                } else if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT) {
                                    MacKeyWriter.this.manualTryToWriteDeviceId();
                                }
                            } else {
                                MacKeyWriter.this.manualTryToWritePVRKey();
                            }
                            dialogInterface.cancel();
                        }
                    });
                    builder.setPositiveButton(R.string.str_facmenu_resetmac_yes, new DialogInterface.OnClickListener() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.15.2
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i4) {
                            AnonymousClass15 anonymousClass15 = AnonymousClass15.this;
                            int i5 = i;
                            if (i5 == 0) {
                                MacKeyWriter.this.manualWriteMac();
                                return;
                            }
                            if (i5 == 1) {
                                if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT) {
                                    MacKeyWriter.this.manualWriteDeviceId();
                                    return;
                                }
                                return;
                            }
                            if (i5 != 17) {
                                switch (i5) {
                                    case 4:
                                        MacKeyWriter.this.manualWritePlayReadyKey();
                                        break;
                                    case 5:
                                        MacKeyWriter.this.manualWriteHdcp14Key();
                                        break;
                                    case 6:
                                        MacKeyWriter.this.manualWriteHdcp22Key();
                                        break;
                                    case 7:
                                        MacKeyWriter.this.manualWriteCIPlusKey();
                                        break;
                                    case 8:
                                        MacKeyWriter.this.manualWriteNetflixKey();
                                        break;
                                    case 9:
                                        MacKeyWriter.this.manualWriteHdcp14Key();
                                        break;
                                    case 10:
                                        MacKeyWriter.this.manualWriteHdcp22Key();
                                        break;
                                    case 11:
                                        MacKeyWriter.this.manualWriteDeviceId();
                                        break;
                                    case 12:
                                        MacKeyWriter.this.manualWriteDTCPIPKey();
                                        break;
                                    case 13:
                                        MacKeyWriter.this.manualWriteMarlinKey();
                                        break;
                                    case 14:
                                        MacKeyWriter.this.manualWriteSQVKey();
                                        break;
                                    case 15:
                                        MacKeyWriter.this.manualWritePVRKey();
                                        break;
                                }
                                return;
                            }
                            MacKeyWriter.this.manualWriteQDAKey();
                        }
                    });
                    FacRootMenu.this.mKeyAlertDialog = builder.create();
                    FacRootMenu.this.mKeyAlertDialog.show();
                    FacRootMenu.this.mKeyAlertDialog.setCancelable(true);
                    FacRootMenu.this.mKeyAlertDialog.getButton(-2).setFocusable(true);
                    FacRootMenu.this.mKeyAlertDialog.getButton(-2).setFocusableInTouchMode(true);
                    FacRootMenu.this.mKeyAlertDialog.getButton(-2).requestFocus();
                    FacRootMenu.this.mKeyAlertDialog.getButton(-2).setOnFocusChangeListener(new View.OnFocusChangeListener(this) { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.15.3
                        @Override // android.view.View.OnFocusChangeListener
                        public void onFocusChange(View view, boolean z) {
                            Log.i("Fac_FacRootMenu", "enter onFocusChange arg1 = " + z);
                            if (z) {
                                view.requestFocus();
                            }
                        }
                    });
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showMacResultDialog(boolean z, final String str) {
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.16
                @Override // java.lang.Runnable
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                    builder.setMessage(str);
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.16.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (FacPropertyDemo.HIFAC_TEE_ENVIRON_SUPPORT) {
                                if (FacRootMenu.mcf.isKeyWriteProtecEnabled()) {
                                    FacRootMenu.mcf.displayHisToast(FacRootMenu.this.getResources().getString(R.string.str_key_write_protect_enable));
                                    return;
                                }
                                if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT) {
                                    MacKeyWriter.this.manualTryToWriteDeviceId();
                                    return;
                                } else if (FacPropertyDemo.HIFAC_CHINA_DRM_KEY_SUPPORT) {
                                    MacKeyWriter.this.manualTryToWriteChinaDRM();
                                    return;
                                } else {
                                    if (FacPropertyDemo.HIFAC_HDCP_KEY_SUPPORT) {
                                        MacKeyWriter.this.manualTryToWriteHdcp14Key();
                                        return;
                                    }
                                    return;
                                }
                            }
                            if (!FacPropertyDemo.HIFAC_MARKET_JP) {
                                if (FacRootMenu.mcf.isSupportQDAKey()) {
                                    MacKeyWriter.this.manualTryToWriteQDAKey();
                                    return;
                                } else if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
                                    MacKeyWriter.this.manualTryToWriteNeflixKey();
                                    return;
                                } else {
                                    if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT) {
                                        MacKeyWriter.this.manualTryToWriteDeviceId();
                                        return;
                                    }
                                    return;
                                }
                            }
                            MacKeyWriter.this.manualTryToWritePVRKey();
                        }
                    });
                    AlertDialog alertDialogCreate = builder.create();
                    alertDialogCreate.show();
                    alertDialogCreate.getButton(-2).setFocusable(true);
                    alertDialogCreate.getButton(-2).setFocusableInTouchMode(true);
                    alertDialogCreate.getButton(-2).requestFocus();
                    alertDialogCreate.getButton(-2).setOnFocusChangeListener(new View.OnFocusChangeListener(this) { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.16.2
                        @Override // android.view.View.OnFocusChangeListener
                        public void onFocusChange(View view, boolean z2) {
                            if (z2) {
                                view.requestFocus();
                            }
                        }
                    });
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showDeviceIdResultDialog(boolean z, final String str) {
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.17
                @Override // java.lang.Runnable
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                    builder.setMessage(str);
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.17.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MacKeyWriter.this.manualTryToWriteHdcp22Key();
                        }
                    });
                    AlertDialog alertDialogCreate = builder.create();
                    alertDialogCreate.show();
                    alertDialogCreate.getButton(-2).setFocusable(true);
                    alertDialogCreate.getButton(-2).setFocusableInTouchMode(true);
                    alertDialogCreate.getButton(-2).requestFocus();
                    alertDialogCreate.getButton(-2).setOnFocusChangeListener(new View.OnFocusChangeListener(this) { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.17.2
                        @Override // android.view.View.OnFocusChangeListener
                        public void onFocusChange(View view, boolean z2) {
                            if (z2) {
                                view.requestFocus();
                            }
                        }
                    });
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showNetflixResultDialog(boolean z, final String str) {
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.18
                @Override // java.lang.Runnable
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                    builder.setMessage(str);
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.18.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MacKeyWriter.this.manualTryToWriteDeviceId();
                        }
                    });
                    AlertDialog alertDialogCreate = builder.create();
                    alertDialogCreate.show();
                    alertDialogCreate.getButton(-2).setFocusable(true);
                    alertDialogCreate.getButton(-2).setFocusableInTouchMode(true);
                    alertDialogCreate.getButton(-2).requestFocus();
                    alertDialogCreate.getButton(-2).setOnFocusChangeListener(new View.OnFocusChangeListener(this) { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.18.2
                        @Override // android.view.View.OnFocusChangeListener
                        public void onFocusChange(View view, boolean z2) {
                            if (z2) {
                                view.requestFocus();
                            }
                        }
                    });
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showMarlinResultDialog(boolean z, final String str) {
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.19
                @Override // java.lang.Runnable
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                    builder.setMessage(str);
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.19.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MacKeyWriter.this.manualTryToWriteDTCPIPKey();
                        }
                    });
                    AlertDialog alertDialogCreate = builder.create();
                    alertDialogCreate.show();
                    alertDialogCreate.getButton(-2).setFocusable(true);
                    alertDialogCreate.getButton(-2).setFocusableInTouchMode(true);
                    alertDialogCreate.getButton(-2).requestFocus();
                    alertDialogCreate.getButton(-2).setOnFocusChangeListener(new View.OnFocusChangeListener(this) { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.19.2
                        @Override // android.view.View.OnFocusChangeListener
                        public void onFocusChange(View view, boolean z2) {
                            if (z2) {
                                view.requestFocus();
                            }
                        }
                    });
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showDTCPIPResultDialog(boolean z, final String str) {
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.20
                @Override // java.lang.Runnable
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                    builder.setMessage(str);
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.20.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
                                MacKeyWriter.this.manualTryToWriteNeflixKey();
                            } else {
                                MacKeyWriter.this.manualTryToWriteDeviceId();
                            }
                        }
                    });
                    AlertDialog alertDialogCreate = builder.create();
                    alertDialogCreate.show();
                    alertDialogCreate.getButton(-2).setFocusable(true);
                    alertDialogCreate.getButton(-2).setFocusableInTouchMode(true);
                    alertDialogCreate.getButton(-2).requestFocus();
                    alertDialogCreate.getButton(-2).setOnFocusChangeListener(new View.OnFocusChangeListener(this) { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.20.2
                        @Override // android.view.View.OnFocusChangeListener
                        public void onFocusChange(View view, boolean z2) {
                            if (z2) {
                                view.requestFocus();
                            }
                        }
                    });
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showSQVResultDialog(boolean z, final String str) {
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.21
                @Override // java.lang.Runnable
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                    builder.setMessage(str);
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.21.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MacKeyWriter.this.manualTryToWriteNeflixKey();
                        }
                    });
                    AlertDialog alertDialogCreate = builder.create();
                    alertDialogCreate.show();
                    alertDialogCreate.getButton(-2).setFocusable(true);
                    alertDialogCreate.getButton(-2).setFocusableInTouchMode(true);
                    alertDialogCreate.getButton(-2).requestFocus();
                    alertDialogCreate.getButton(-2).setOnFocusChangeListener(new View.OnFocusChangeListener(this) { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.21.2
                        @Override // android.view.View.OnFocusChangeListener
                        public void onFocusChange(View view, boolean z2) {
                            if (z2) {
                                view.requestFocus();
                            }
                        }
                    });
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showPVRResultDialog(boolean z, final String str) {
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.22
                @Override // java.lang.Runnable
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                    builder.setMessage(str);
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.22.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MacKeyWriter.this.manualTryToWriteMarlinKey();
                        }
                    });
                    AlertDialog alertDialogCreate = builder.create();
                    alertDialogCreate.show();
                    alertDialogCreate.getButton(-2).setFocusable(true);
                    alertDialogCreate.getButton(-2).setFocusableInTouchMode(true);
                    alertDialogCreate.getButton(-2).requestFocus();
                    alertDialogCreate.getButton(-2).setOnFocusChangeListener(new View.OnFocusChangeListener(this) { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.22.2
                        @Override // android.view.View.OnFocusChangeListener
                        public void onFocusChange(View view, boolean z2) {
                            if (z2) {
                                view.requestFocus();
                            }
                        }
                    });
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showDrmResultDialog(boolean z, final String str) {
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.23
                @Override // java.lang.Runnable
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                    builder.setMessage(str);
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.23.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (FacPropertyDemo.HIFAC_HDCP_KEY_SUPPORT) {
                                MacKeyWriter.this.manualTryToWriteHdcpKey();
                            }
                        }
                    });
                    builder.show();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showHdcpResultDialog(boolean z, final String str) {
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.24
                @Override // java.lang.Runnable
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                    builder.setMessage(str);
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.24.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.show();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showHdcp14ResultDialog(boolean z, final String str) {
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.25
                @Override // java.lang.Runnable
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                    builder.setMessage(str);
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.25.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FacRootMenu.this.setAttestationAndMGk();
                        }
                    });
                    AlertDialog alertDialogCreate = builder.create();
                    alertDialogCreate.show();
                    alertDialogCreate.getButton(-2).setFocusable(true);
                    alertDialogCreate.getButton(-2).setFocusableInTouchMode(true);
                    alertDialogCreate.getButton(-2).requestFocus();
                    alertDialogCreate.getButton(-2).setOnFocusChangeListener(new View.OnFocusChangeListener(this) { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.25.2
                        @Override // android.view.View.OnFocusChangeListener
                        public void onFocusChange(View view, boolean z2) {
                            if (z2) {
                                view.requestFocus();
                            }
                        }
                    });
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showHdcp2xResultDialog(boolean z, final String str) {
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.26
                @Override // java.lang.Runnable
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                    builder.setMessage(str);
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.26.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MacKeyWriter.this.manualTryToWriteHdcp14Key();
                        }
                    });
                    AlertDialog alertDialogCreate = builder.create();
                    alertDialogCreate.show();
                    alertDialogCreate.getButton(-2).setFocusable(true);
                    alertDialogCreate.getButton(-2).setFocusableInTouchMode(true);
                    alertDialogCreate.getButton(-2).requestFocus();
                    alertDialogCreate.getButton(-2).setOnFocusChangeListener(new View.OnFocusChangeListener(this) { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.26.2
                        @Override // android.view.View.OnFocusChangeListener
                        public void onFocusChange(View view, boolean z2) {
                            if (z2) {
                                view.requestFocus();
                            }
                        }
                    });
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showCIPlusResultDialog(boolean z, final String str) {
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.27
                @Override // java.lang.Runnable
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                    builder.setMessage(str);
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.27.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.show();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showPlayReadyResultDialog(boolean z, final String str) {
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.28
                @Override // java.lang.Runnable
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                    builder.setMessage(str);
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.28.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.show();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showQDAResultDialog(boolean z, final String str) {
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.29
                @Override // java.lang.Runnable
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                    builder.setMessage(str);
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.29.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MacKeyWriter.this.manualTryToWriteNeflixKey();
                        }
                    });
                    AlertDialog alertDialogCreate = builder.create();
                    alertDialogCreate.show();
                    alertDialogCreate.getButton(-2).setFocusable(true);
                    alertDialogCreate.getButton(-2).setFocusableInTouchMode(true);
                    alertDialogCreate.getButton(-2).requestFocus();
                    alertDialogCreate.getButton(-2).setOnFocusChangeListener(new View.OnFocusChangeListener(this) { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.29.2
                        @Override // android.view.View.OnFocusChangeListener
                        public void onFocusChange(View view, boolean z2) {
                            if (z2) {
                                view.requestFocus();
                            }
                        }
                    });
                }
            });
        }

        private void showDrmExistsDialog() {
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.30
                @Override // java.lang.Runnable
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                    builder.setMessage(FacRootMenu.this.getString(R.string.str_facmenu_china_drm_already_exists));
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.30.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (FacPropertyDemo.HIFAC_HDCP_KEY_SUPPORT) {
                                MacKeyWriter.this.manualTryToWriteHdcpKey();
                            }
                        }
                    });
                    builder.show();
                }
            });
        }

        private void showHdcpExistsDialog() {
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.32
                @Override // java.lang.Runnable
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                    builder.setMessage(FacRootMenu.this.getString(R.string.str_facmenu_hdcp_key_already_exists));
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.32.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.show();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showResultMsg() {
            Log.i("Fac_FacRootMenu", "showResultMsg");
            if (FacRootMenu.this.isShowResultAboutKey) {
                Log.i("Fac_FacRootMenu", "showResultMsg----isShowResultAboutKey--return");
            }
            FacRootMenu.this.isShowResultAboutKey = true;
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.35
                /* JADX WARN: Code duplicated, block: B:32:0x0090  */
                @Override // java.lang.Runnable
                public void run() {
                    Log.i("Fac_FacRootMenu", "showResultMsg----no param-----run");
                    if (FacPropertyDemo.HIFAC_MARKET_JP) {
                        if (!FacRootMenu.mcf.getEmmcType()) {
                            Log.i("Fac_FacRootMenu", "showResultMsg----no param-----run--emmc--error");
                            MacKeyWriter macKeyWriter = MacKeyWriter.this;
                            FacRootMenu.this.mIsEMMCOk = false;
                            macKeyWriter.mErrMsg.append("EMMC ERROR!!\n");
                        }
                        if (MacKeyWriter.this.mIsMacOK && MacKeyWriter.this.mIsDeviceIDOK && MacKeyWriter.this.mIsPlayReadyOK && MacKeyWriter.this.mIsHdcp14OK && MacKeyWriter.this.mIsHdcp2xOK && MacKeyWriter.this.mIsMGKOK && MacKeyWriter.this.mIsAttestationOK && MacKeyWriter.this.mIsDtcpIpOK && MacKeyWriter.this.mIsMarlinOK && MacKeyWriter.this.mIsPVROK && MacKeyWriter.this.mIsQDAOK) {
                            MacKeyWriter macKeyWriter2 = MacKeyWriter.this;
                            if (FacRootMenu.this.mIsEMMCOk) {
                                macKeyWriter2.onMacKeyOK();
                            } else {
                                Log.i("Fac_FacRootMenu", "showResultMsg----no param-----onMacKeyNG");
                                MacKeyWriter.this.onMacKeyNG();
                            }
                        } else {
                            Log.i("Fac_FacRootMenu", "showResultMsg----no param-----onMacKeyNG");
                            MacKeyWriter.this.onMacKeyNG();
                        }
                    } else if (MacKeyWriter.this.mIsMacOK && MacKeyWriter.this.mIsDeviceIDOK && MacKeyWriter.this.mIsPlayReadyOK && MacKeyWriter.this.mIsHdcp14OK && MacKeyWriter.this.mIsHdcp2xOK && MacKeyWriter.this.mIsMGKOK && MacKeyWriter.this.mIsAttestationOK && MacKeyWriter.this.mIsQDAOK && MacKeyWriter.this.mIsTconTempVrOK) {
                        Log.i("Fac_FacRootMenu", "showResultMsg----no param-----onMacKeyOK");
                        MacKeyWriter.this.onMacKeyOK();
                    } else {
                        Log.i("Fac_FacRootMenu", "showResultMsg----no param-----onMacKeyNG");
                        MacKeyWriter.this.onMacKeyNG();
                    }
                    FacRootMenu.this.mhHandler.sendEmptyMessage(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_NOT_DETECT);
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.35.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    FacRootMenu.this.keysDialog = builder.create();
                    FacRootMenu facRootMenu = FacRootMenu.this;
                    facRootMenu.keysDialog.setView(facRootMenu.keysDialogView);
                    FacRootMenu.this.keysDialog.setCancelable(true);
                    FacRootMenu facRootMenu2 = FacRootMenu.this;
                    facRootMenu2.keysMessage = (TextView) facRootMenu2.keysDialogView.findViewById(R.id.keys_dialog);
                    FacRootMenu.this.keysDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.35.2
                        @Override // android.content.DialogInterface.OnKeyListener
                        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                            Log.i("Fac_FacRootMenu", "showResultMsg---------onKey");
                            FacRootMenu.this.isShowResultAboutKey = false;
                            if (i == 217) {
                                Log.i("Fac_FacRootMenu", "check all keys status");
                                FacRootMenu.this.mhHandler.sendEmptyMessage(116);
                                return true;
                            }
                            if (i != 23) {
                                return true;
                            }
                            Log.i("Fac_FacRootMenu", "keysDialog dismiss");
                            ((ViewGroup) FacRootMenu.this.keysDialogView.getParent()).removeView(FacRootMenu.this.keysDialogView);
                            FacRootMenu.this.keysDialog.dismiss();
                            if (!FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                                return true;
                            }
                            FacRootMenu.this.mhHandler.sendEmptyMessageDelayed(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC, FacParamManager.FAC_SCREEN_PROTECT_TIME);
                            return true;
                        }
                    });
                    MacKeyWriter macKeyWriter3 = MacKeyWriter.this;
                    FacRootMenu.this.keysMessage.setText(macKeyWriter3.mErrMsg.toString());
                    FacRootMenu.this.keysDialog.show();
                }
            });
        }

        private void autoTryToWriteMAC() {
            Log.i("Fac_FacRootMenu", "autoTryToWriteMACK");
            if (!this.mIsMacOK) {
                if (FacRootMenu.mcf == null) {
                    MenuConfigManager unused = FacRootMenu.mcf = MenuConfigManager.getInstance(FacRootMenu.mFacRootMenu);
                }
                FacRootMenu.mcf.setMacContent(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.36
                    @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                    public void writeEnd(boolean z, String str) {
                        MacKeyWriter.this.mIsMacOK = z;
                        MacKeyWriter.this.mErrMsg.append(str + "\n");
                        MacKeyWriter.this.protectAutoTryToWriteQDA();
                    }
                });
            } else {
                this.mErrMsg.append(FacRootMenu.this.getString(R.string.str_facmenu_setmacsuccess) + "\n");
                protectAutoTryToWriteQDA();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void autoTryToWriteDeviceId() {
            Log.i("Fac_FacRootMenu", "autoTryToWriteWideVine");
            if (!this.mIsDeviceIDOK) {
                FacRootMenu.this.tryToWriteDeviceIDWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.37
                    @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                    public void writeEnd(boolean z, String str) {
                        MacKeyWriter.this.mIsDeviceIDOK = z;
                        MacKeyWriter.this.mErrMsg.append(str + "\n");
                        MacKeyWriter.this.autoTryToWritePlayReady();
                    }
                });
                return;
            }
            if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT) {
                this.mErrMsg.append(FacRootMenu.this.getString(R.string.str_wide_vine_key_yes) + "\n");
            }
            autoTryToWritePlayReady();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void autoTryToWritePlayReady() {
            Log.i("Fac_FacRootMenu", "autoTryToWritePlayReady");
            if (!this.mIsPlayReadyOK) {
                FacRootMenu.this.tryToWritePlayReadyKeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.39
                    @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                    public void writeEnd(boolean z, String str) {
                        MacKeyWriter.this.mIsPlayReadyOK = z;
                        MacKeyWriter.this.autoTryToWriteHDCP14();
                    }
                });
            } else {
                autoTryToWriteHDCP14();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void autoTryToWriteHDCP14() {
            Log.i("Fac_FacRootMenu", "autoTryToWriteHDCP14");
            if (!this.mIsHdcp14OK) {
                FacRootMenu.this.tryToWriteHDCP14KeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.41
                    @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                    public void writeEnd(boolean z, String str) {
                        MacKeyWriter.this.mIsHdcp14OK = z;
                        MacKeyWriter.this.mErrMsg.append(str + "\n");
                        MacKeyWriter.this.autoTryToWriteHDCP2x();
                    }
                });
                return;
            }
            if (FacPropertyDemo.HIFAC_HDCP_1_4_KEY_SUPPORT) {
                this.mErrMsg.append(FacRootMenu.this.getString(R.string.str_hdcp14_key_yes) + "\n");
            }
            autoTryToWriteHDCP2x();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void autoTryToWriteHDCP2x() {
            Log.i("Fac_FacRootMenu", "autoTryToWriteHDCP22");
            if (!this.mIsHdcp2xOK) {
                FacRootMenu.this.tryToWriteHDCP2xKeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.42
                    @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                    public void writeEnd(boolean z, String str) {
                        MacKeyWriter.this.mIsHdcp2xOK = z;
                        MacKeyWriter.this.mErrMsg.append(str + "\n");
                        MacKeyWriter.this.autoTryToWriteAttestation();
                    }
                });
                return;
            }
            if (FacPropertyDemo.HIFAC_HDCP_2_X_KEY_SUPPORT) {
                this.mErrMsg.append(FacRootMenu.this.getString(R.string.str_hdcp2x_key_yes) + "\n");
            }
            autoTryToWriteAttestation();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void autoTryToWriteAttestation() {
            Log.i("Fac_FacRootMenu", "autoTryToWriteAttest");
            if (!this.mIsAttestationOK) {
                FacRootMenu.this.tryToWriteAttestationKeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.43
                    @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                    public void writeEnd(boolean z, String str) {
                        MacKeyWriter.this.mIsAttestationOK = z;
                        if (FacPropertyDemo.HIFAC_MARKET_JP) {
                            MacKeyWriter.this.autoTryToWritePVR();
                        } else if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
                            MacKeyWriter.this.autoTryToWriteMGK();
                        } else {
                            MacKeyWriter.this.showResultMsg();
                        }
                    }
                });
                return;
            }
            boolean z = FacPropertyDemo.HIFAC_ATTEST_KEY_SUPPORT;
            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                autoTryToWritePVR();
            } else if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
                autoTryToWriteMGK();
            } else {
                showResultMsg();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void autoTryToWriteMarlin() {
            Log.i("Fac_FacRootMenu", "autoTryToWriteMarlin");
            if (!this.mIsMarlinOK) {
                FacRootMenu.this.tryToWriteMarlinKeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.44
                    @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                    public void writeEnd(boolean z, String str) {
                        MacKeyWriter.this.mIsMarlinOK = z;
                        MacKeyWriter.this.mErrMsg.append(str + "\n");
                        MacKeyWriter.this.autoTryToWriteDtcpip();
                    }
                });
                return;
            }
            this.mErrMsg.append(FacRootMenu.this.getString(R.string.str_marlin_key_yes) + "\n");
            autoTryToWriteDtcpip();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void autoTryToWriteDtcpip() {
            Log.i("Fac_FacRootMenu", "autoTryToWriteDtcpip");
            if (!this.mIsDtcpIpOK) {
                FacRootMenu.this.tryToWriteDtcpipKeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.45
                    @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                    public void writeEnd(boolean z, String str) {
                        MacKeyWriter.this.mIsDtcpIpOK = z;
                        MacKeyWriter.this.mErrMsg.append(str + "\n");
                        if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
                            MacKeyWriter.this.autoTryToWriteMGK();
                        } else {
                            MacKeyWriter.this.showResultMsg();
                        }
                    }
                });
                return;
            }
            this.mErrMsg.append(FacRootMenu.this.getString(R.string.str_dtcpip_key_yes) + "\n");
            if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
                autoTryToWriteMGK();
            } else {
                showResultMsg();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void autoTryToWritePVR() {
            Log.i("Fac_FacRootMenu", "autoTryToWritePVR");
            if (!this.mIsPVROK) {
                FacRootMenu.this.tryToWritePVRKeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.46
                    @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                    public void writeEnd(boolean z, String str) {
                        MacKeyWriter.this.mIsPVROK = z;
                        MacKeyWriter.this.mErrMsg.append(str + "\n");
                        MacKeyWriter.this.autoTryToWriteMarlin();
                    }
                });
                return;
            }
            this.mErrMsg.append(FacRootMenu.this.getString(R.string.str_pvr_key_yes) + "\n");
            autoTryToWriteMarlin();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void autoTryToWriteMGK() {
            Log.i("Fac_FacRootMenu", "autoTryToWriteMGK");
            if (!this.mIsMGKOK) {
                FacRootMenu.this.tryToWriteMGKKeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.47
                    @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                    public void writeEnd(boolean z, String str) {
                        MacKeyWriter.this.mIsMGKOK = z;
                        MacKeyWriter.this.mErrMsg.append(str + "\n");
                        MacKeyWriter.this.showResultMsg();
                    }
                });
                return;
            }
            if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
                this.mErrMsg.append(FacRootMenu.this.getString(R.string.str_mgk_key_yes) + "\n");
            }
            showResultMsg();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void protectAutoTryToWriteQDA() {
            if (FacPropertyDemo.HIFAC_TEE_ENVIRON_SUPPORT) {
                if (!FacRootMenu.mcf.isKeyWriteProtecEnabled()) {
                    autoTryToWriteQDA();
                    return;
                } else {
                    showResultMsg();
                    FacRootMenu.mcf.displayHisToast(FacRootMenu.this.getResources().getString(R.string.str_key_write_protect_enable));
                    return;
                }
            }
            autoTryToWriteQDA();
        }

        private void autoTryToWriteQDA() {
            Log.i("Fac_FacRootMenu", "autoTryToWriteQDA");
            boolean z = !FacRootMenu.this.isQDANull();
            this.mIsQDAOK = z;
            if (z || !FacRootMenu.mcf.isSupportQDAKey()) {
                if (FacRootMenu.mcf.isSupportQDAKey()) {
                    this.mErrMsg.append(FacRootMenu.this.getString(R.string.str_qda_key_yes) + "\n");
                }
                autoTryToWriteDeviceId();
                return;
            }
            FacRootMenu.this.tryToWriteQDAKeyWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.48
                @Override // com.hisense.uifac.FacRootMenu.WriteCallback
                public void writeEnd(boolean z2, String str) {
                    MacKeyWriter.this.mIsQDAOK = z2;
                    MacKeyWriter.this.mErrMsg.append(str + "\n");
                    MacKeyWriter.this.autoTryToWriteDeviceId();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void checkMacKey() {
            if (!FacPropertyDemo.HIFAC_MARKET_JP || (checkTVSKey() && FacRootMenu.mcf.getEmmcType())) {
                boolean z = !FacRootMenu.this.isMacAddressDefaultOrIllegal();
                boolean z2 = FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT ? !FacRootMenu.this.isDeviceIDNull() : true;
                boolean z3 = FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT ? !FacRootMenu.this.isNetflixNull() : true;
                boolean z4 = FacPropertyDemo.HIFAC_HDCP_1_4_KEY_SUPPORT ? !FacRootMenu.this.isHdcp14KeyNull() : true;
                boolean z5 = FacPropertyDemo.HIFAC_HDCP_2_X_KEY_SUPPORT ? !FacRootMenu.this.isHdcp2xKeyNull() : true;
                boolean zIsQDANull = true ^ FacRootMenu.this.isQDANull();
                if (z && z2 && z3 && z4 && z5 && zIsQDANull) {
                    onMacKeyOK();
                }
            }
        }

        private boolean checkTVSKey() {
            return (FacRootMenu.this.isMarlinNull() ^ true) && (FacRootMenu.this.isDTCPIPNull() ^ true) && (FacRootMenu.this.isPVRNull() ^ true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onMacKeyNG() {
            FacRootMenu.this.mIsMacKeyOK = false;
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.49
                @Override // java.lang.Runnable
                public void run() {
                    if (FacRootMenu.this.mImageView != null) {
                        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                            FacRootMenu.this.mImageView.setBackgroundResource(R.color.msg_red_oled);
                        } else {
                            FacRootMenu.this.mImageView.setBackgroundResource(R.color.read_no_transparent);
                        }
                        FacRootMenu.this.mImageView.setVisibility(0);
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onMacKeyOK() {
            FacRootMenu.this.mIsMacKeyOK = true;
            FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.MacKeyWriter.50
                @Override // java.lang.Runnable
                public void run() {
                    if (FacRootMenu.this.mImageView != null) {
                        FacRootMenu.this.mImageView.setBackgroundDrawable(null);
                        FacRootMenu.this.mImageView.setVisibility(8);
                    }
                }
            });
        }
    }

    private void showResultMsgForOK(final StringBuffer stringBuffer) {
        Log.i("Fac_FacRootMenu", "showResultMsg");
        runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.48
            @Override // java.lang.Runnable
            public void run() {
                Log.i("Fac_FacRootMenu", "showResultMsg---------run");
                FacRootMenu facRootMenu = FacRootMenu.this;
                if (!facRootMenu.mWideVineOk || !facRootMenu.mNetflixOk || !facRootMenu.mHdcp14Ok || !facRootMenu.mHdcp22Ok) {
                    Log.i("Fac_FacRootMenu", "showResultMsg---------onMacKeyNG");
                    FacRootMenu.this.mIsMacKeyOK = false;
                    FacRootMenu.this.runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.48.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (FacRootMenu.this.mImageView != null) {
                                if (FacPropertyDemo.HIFAC_MARKET_SA) {
                                    FacRootMenu.this.showBigMMode();
                                }
                                if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                                    FacRootMenu.this.mImageView.setBackgroundResource(R.color.msg_red_oled);
                                } else {
                                    FacRootMenu.this.mImageView.setBackgroundResource(R.color.read_no_transparent);
                                }
                                FacRootMenu.this.mImageView.setVisibility(0);
                            }
                        }
                    });
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                builder.setCancelable(false);
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.FacRootMenu.48.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                if (FacRootMenu.this.keysDialogView.getParent() != null) {
                    ((ViewGroup) FacRootMenu.this.keysDialogView.getParent()).removeView(FacRootMenu.this.keysDialogView);
                }
                FacRootMenu.this.keysDialog = builder.create();
                FacRootMenu facRootMenu2 = FacRootMenu.this;
                facRootMenu2.keysDialog.setView(facRootMenu2.keysDialogView);
                FacRootMenu.this.keysDialog.setCancelable(true);
                FacRootMenu facRootMenu3 = FacRootMenu.this;
                facRootMenu3.keysMessage = (TextView) facRootMenu3.keysDialogView.findViewById(R.id.keys_dialog);
                FacRootMenu.this.keysDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.hisense.uifac.FacRootMenu.48.3
                    @Override // android.content.DialogInterface.OnKeyListener
                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        Log.i("Fac_FacRootMenu", "showResultMsg---------onKey");
                        if (i == 217) {
                            Log.i("Fac_FacRootMenu", "check all keys status");
                            FacRootMenu.this.mhHandler.sendEmptyMessage(116);
                            return true;
                        }
                        if (i != 23) {
                            return true;
                        }
                        Log.i("Fac_FacRootMenu", "keysDialog dismiss");
                        ((ViewGroup) FacRootMenu.this.keysDialogView.getParent()).removeView(FacRootMenu.this.keysDialogView);
                        FacRootMenu.this.keysDialog.dismiss();
                        if (!FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                            return true;
                        }
                        FacRootMenu.this.mhHandler.sendEmptyMessageDelayed(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC, FacParamManager.FAC_SCREEN_PROTECT_TIME);
                        return true;
                    }
                });
                FacRootMenu.this.keysMessage.setText(stringBuffer.toString());
                FacRootMenu.this.keysDialog.show();
            }
        });
    }

    private void handleWBAutoTest(String str) {
        int i = (str.equalsIgnoreCase("HIFAC_RDRV_PLUS") || str.equalsIgnoreCase("HIFAC_GDRV_PLUS") || str.equalsIgnoreCase("HIFAC_BDRV_PLUS") || str.equalsIgnoreCase("HIFAC_RCUT_PLUS") || str.equalsIgnoreCase("HIFAC_GCUT_PLUS") || str.equalsIgnoreCase("HIFAC_BCUT_PLUS")) ? 1 : -1;
        if (str.equalsIgnoreCase("HIFAC_RDRV_PLUS") || str.equalsIgnoreCase("HIFAC_RDRV_MINUS")) {
            str = "HIFAC_RDRV";
        }
        if (str.equalsIgnoreCase("HIFAC_GDRV_PLUS") || str.equalsIgnoreCase("HIFAC_GDRV_MINUS")) {
            str = "HIFAC_GDRV";
        }
        if (str.equalsIgnoreCase("HIFAC_BDRV_PLUS") || str.equalsIgnoreCase("HIFAC_BDRV_MINUS")) {
            str = "HIFAC_BDRV";
        }
        if (str.equalsIgnoreCase("HIFAC_RCUT_PLUS") || str.equalsIgnoreCase("HIFAC_RCUT_MINUS")) {
            str = "HIFAC_RCUT";
        }
        if (str.equalsIgnoreCase("HIFAC_GCUT_PLUS") || str.equalsIgnoreCase("HIFAC_GCUT_MINUS")) {
            str = "HIFAC_GCUT";
        }
        if (str.equalsIgnoreCase("HIFAC_BCUT_PLUS") || str.equalsIgnoreCase("HIFAC_BCUT_MINUS")) {
            str = "HIFAC_BCUT";
        }
        int i2 = mcf.getDefault(str) + i;
        int max = mcf.getMax(str);
        int min = mcf.getMin(str);
        if (i2 > max) {
            i2 = min;
        } else if (i2 < min) {
            i2 = max;
        }
        mcf.setValue(str, i2);
        this.mModel_ShortTip_TV.setText(i2 + "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTunerModeString(int i) {
        String[] stringArray;
        if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            stringArray = getResources().getStringArray(R.array.facmenu_channeltype_array_for_em);
        } else {
            stringArray = getResources().getStringArray(R.array.facmenu_channeltype_array);
        }
        if (i < stringArray.length && i > -1) {
            this.mModel_ShortTip_TV.setText(stringArray[i]);
        } else {
            this.mModel_ShortTip_TV.setText(stringArray[0]);
        }
    }

    private void setLogoString() {
        String logoFullNameMon;
        if (FacPropertyDemo.HIFAC_MARKET_HK) {
            logoFullNameMon = mcf.getLogoFullName();
        } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            if (FacPropertyDemo.HIFAC_REGION5_SUPPORT) {
                logoFullNameMon = mcf.getLogoForEMSAEnum();
            } else {
                logoFullNameMon = mcf.getFullLogoName();
            }
        } else if (FacPropertyDemo.HIFAC_MARKET_SA) {
            logoFullNameMon = mcf.getLogoFullNameSA();
        } else if (FacPropertyDemo.HIFAC_MARKET_JP) {
            logoFullNameMon = mcf.getLogoFullNameJPN();
        } else if (FacPropertyDemo.HIFAC_MARKET_MONITOR || FacPropertyDemo.HIFAC_PRODUCT_OEM || FacPropertyDemo.HIFAC_MARKET_KO) {
            logoFullNameMon = mcf.getLogoFullNameMon();
        } else {
            logoFullNameMon = mcf.cover3CharacterLogToFullSpelling(mcf.getLogoEnum().getName());
        }
        this.mModel_ShortTip_TV.setText(logoFullNameMon);
    }

    private void displayRightTopInfoBar() {
        this.mSimpleInfoTopRL.setVisibility(0);
        this.mCommonInfoTopRL.setVisibility(0);
        this.mModel_InfoBar_RL.setVisibility(0);
        this.mCommonInfoTopRL.setBackgroundResource(R.drawable.translucent_background);
        this.mSimpleInfoTopRL.setBackgroundResource(R.drawable.nav_infobar_smaple_bg);
        this.mDetailsInfoBottomTV.setVisibility(4);
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            this.mInfoNumTV.setText(getResources().getString(R.string.str_factorymenu_tv) + "\n" + mcf.getTvsCurrentChannelNumber() + "\n" + mcf.getTvsCurrentChannelTuner() + "");
        } else {
            this.mInfoNumTV.setText(getResources().getString(R.string.str_factorymenu_tv) + "\n" + mNavIntegration.getCurrentChannelNumberString() + "");
        }
        this.mInfoNameTV.setText("");
    }

    private void displayLeftTopInfoBar() {
        this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
        showTipMode(true);
        this.childType = 29;
        mSetShortTipData(29);
        sendMessageDelayed(2);
    }

    private boolean isNeedExitAging(int i) {
        int[] iArr = this.mAgingExitKeys;
        iArr[0] = iArr[1];
        iArr[1] = iArr[2];
        iArr[2] = iArr[3];
        iArr[3] = i;
        int[] iArr2 = this.mAgingExitKeysNew;
        iArr2[0] = iArr2[1];
        iArr2[1] = iArr2[2];
        iArr2[2] = iArr2[3];
        iArr2[3] = iArr2[4];
        iArr2[4] = i;
        if (iArr[0] == 15 && iArr[1] == 7 && iArr[2] == 15 && iArr[3] == 14) {
            return true;
        }
        int[] iArr3 = this.mAgingExitKeysNew;
        return iArr3[0] == 19 && iArr3[1] == 19 && iArr3[2] == 20 && iArr3[3] == 20 && iArr3[3] == 4213;
    }

    private boolean isNeedExitAging2(int i) {
        int[] iArr = this.mAgingExitKeys2;
        iArr[0] = iArr[1];
        iArr[1] = iArr[2];
        iArr[2] = iArr[3];
        iArr[3] = iArr[4];
        iArr[4] = i;
        return iArr[0] == 19 && iArr[1] == 19 && iArr[2] == 20 && iArr[3] == 20 && iArr[4] == 23;
    }

    private void cleanAgingExitKey() {
        int[] iArr = this.mAgingExitKeys;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
    }

    private void cleanAgingExitKey2() {
        int[] iArr = this.mAgingExitKeys2;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
    }

    private void syncSourceToClient() {
        if (!this.mFacFunction_Adapter.isHaveFte()) {
            String currentSourceName = mcf.getCurrentSourceName();
            Log.i("Fac_FacRootMenu", "onDestroy currentSourceName = " + currentSourceName);
            stopAtsc3AndChangeSource();
            ChangeSourceUtil.changeSourceByName(currentSourceName, this);
            this.mTvStoped = true;
            return;
        }
        if (this.mTvStoped) {
            return;
        }
        mcf.setMtkSourceStatus(false);
        stopAtsc3AndChangeSource();
        Log.i("Fac_FacRootMenu", "syncSourceToClient stopTV");
        this.mTvStoped = true;
    }

    private void setVolumAndPresetChannel() {
        this.mFacFunction_Adapter.setVolume(80);
        mcf.setMtkSourceStatus(false);
        this.mTvStoped = true;
        if (mcf.isHaveTuner()) {
            if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                if (FacPropertyDemo.HIFAC_MARKET_HK) {
                    this.mFacFunction_Adapter.addTVPresetChannelForcityname("huangdao_hk");
                } else {
                    this.mFacFunction_Adapter.addTVPresetChannelForcityname("huangdao_em");
                }
            } else if (FacPropertyDemo.HIFAC_MARKET_SA) {
                this.mFacFunction_Adapter.addTVPresetChannelForcityname("huangdao_sa");
            } else {
                this.mFacFunction_Adapter.addTVPresetChannelForcityname("huangdao");
            }
        }
        mcf.updateSscDefaultValueForTemp();
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            mcf.setTvsTvTuner(FacTvsFunctionUtil.FAC_TVS_TUNE_TYPE_TDJ62);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayHisToast(String str) {
        Toast toast = this.mToastView;
        if (toast != null) {
            toast.cancel();
            this.mToastView.cancel();
            this.mToastView = null;
        }
        this.mToastView = new Toast(getApplicationContext());
        Log.i("Fac_FacRootMenu", "displayHisToast str = " + str);
        TextView textView = new TextView(getApplicationContext());
        textView.setBackgroundColor(-7829368);
        textView.setTextColor(-16776961);
        textView.setPadding(10, 10, 10, 10);
        textView.setText(str);
        textView.setTextSize(32.0f);
        this.mToastView.setView(textView);
        this.mToastView.setDuration(1);
        if (getApplicationContext().getResources().getString(R.string.remote_pair_success).equalsIgnoreCase(str)) {
            this.mToastView.setGravity(80, 0, 0);
        } else {
            this.mToastView.setGravity(17, 0, 0);
        }
        this.mToastView.show();
    }

    @Override // com.hisense.uifac.util.IRootMenuUiListener
    public void showToast(String str) {
        Message messageObtain = Message.obtain();
        messageObtain.what = MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PAUSE_STATUS;
        messageObtain.obj = str;
        this.mhHandler.sendMessage(messageObtain);
    }

    private void checkLaunchHisenseBugReport(int i, KeyEvent keyEvent) {
        Log.d("Fac_FacRootMenu", "checkLaunchHisenseBugReport = " + i);
        switch (i) {
            case 21:
            case 22:
            case 23:
                int[] iArr = this.mLaunchBugReportKeys;
                iArr[0] = iArr[1];
                iArr[1] = iArr[2];
                iArr[2] = iArr[3];
                iArr[3] = iArr[4];
                iArr[4] = iArr[5];
                iArr[5] = i;
                if (iArr[1] == 21 && iArr[2] == 23 && iArr[3] == 22 && iArr[4] == 23 && iArr[5] == 21) {
                    if (getCountOfUsbOrSdCardMounted(true) >= 1) {
                        Log.i("Fac_FacRootMenu", "secret keys correct, start hisense bug report ");
                        SystemProperties.set("ctl.start", "user_hi_bugreport");
                        if (FacPropertyDemo.HIFAC_MARKET_JP) {
                            mcf.setTVSLog();
                            if (LogSaver.saveLog(facRootMenuContext)) {
                                Toast.makeText(facRootMenuContext, "tvs log saving is successful.", 1).show();
                            } else {
                                Toast.makeText(facRootMenuContext, "tvs log saving fails.", 1).show();
                            }
                        }
                        this.mhHandler.removeMessages(27);
                        this.mhHandler.sendEmptyMessageDelayed(27, 30000L);
                        Toast.makeText(this, "Please do not remove the USB flash disk while the log is beging uploading.", 1).show();
                    } else {
                        mcf.displayHisToast(getResources().getString(R.string.str_facmenu_start_bugreport_no_udisk));
                    }
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFactoryEnterParamAsync() {
        new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.49
            @Override // java.lang.Runnable
            public void run() {
                Log.i("Fac_FacRootMenu", "setFactoryEnterParamAsync begin");
                if (FacPropertyDemo.HIFAC_LASER_IS_L9 || FacRootMenu.this.mFacFunction_Adapter.getLaserAlarmEnable() != 1) {
                    FacRootMenu.this.mFacFunction_Adapter.setLedControl(1);
                }
                FacRootMenu.this.mFacFunction_Adapter.closeDBX();
                FacRootMenu.mcf.setOttSrc(7);
                Log.i("Fac_FacRootMenu", "setFactoryEnterParamAsync end");
                FacRootMenu.this.setDTSSwitch(1);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSourceChangedHandle() {
        this.mFacFunction_Adapter.addSourceChangedStatus(new ISourceStatusChangeCallBack(this) { // from class: com.hisense.uifac.FacRootMenu.50
        });
    }

    private void initFactoryRootMenuParam() {
        if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            this.FANGCHUANHUO_NUMBER_DISPLAY_COUNT = FacParamManager.FAC_SN_EM_COUNT;
        } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.JP)) {
            this.FANGCHUANHUO_NUMBER_DISPLAY_COUNT = FacParamManager.FAC_SN_JP_COUNT;
        } else {
            this.FANGCHUANHUO_NUMBER_DISPLAY_COUNT = FacParamManager.FAC_SN_US_COUNT;
        }
        DemuraUtil demuraUtil = new DemuraUtil();
        this.mDemuraUtil = demuraUtil;
        demuraUtil.setLayout(this.mFacBGColorView);
    }

    private void processSerialNumberResult() {
        if (this.mFangChuanHuo_Flag == FangChuanHuoStatus.FANGCHUANHUO_INPUTNUM) {
            String str = this.selectFangChuanHuoString;
            int length = (str == null || str.isEmpty()) ? 0 : this.selectFangChuanHuoString.length();
            Log.i("Fac_FacRootMenu", "the fangchuanhuo FANGCHUANHUO_NUMBER_DISPLAY_COUNT :" + this.FANGCHUANHUO_NUMBER_DISPLAY_COUNT);
            if (length != this.FANGCHUANHUO_NUMBER_DISPLAY_COUNT) {
                this.mModel_ShortTip_Title.setText(R.string.str_factorymenu_ng);
                this.selectFangChuanHuoString = "NG";
            } else {
                this.mModel_ShortTip_Title.setText(R.string.str_factorymenu_finish);
            }
            this.mFangChuanHuo_Flag = FangChuanHuoStatus.FANGCHUANHUO_IDLE;
            Log.i("Fac_FacRootMenu", "input string=" + this.selectFangChuanHuoString + " len = " + length);
            canncelMessage(2);
            canncelMessage(9);
            showTipMode(false);
            this.childType = 23;
            mSetShortTipData(23);
            if (length == this.FANGCHUANHUO_NUMBER_DISPLAY_COUNT) {
                mcf.setNewFangChuanHuoValue(this.selectFangChuanHuoString);
                return;
            }
            return;
        }
        this.mFangChuanHuo_Flag = FangChuanHuoStatus.FANGCHUANHUO_IDLE;
    }

    @Override // com.hisense.uifac.util.IRootMenuUiListener
    public void setMicTestResult(String str) {
        Message messageObtainMessage = this.mhHandler.obtainMessage(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PAUSE_STATUS);
        messageObtainMessage.obj = str;
        this.mhHandler.sendMessage(messageObtainMessage);
    }

    @Override // com.hisense.uifac.util.IRootMenuUiListener
    public void setMicTestResultToLog(String str, boolean z) {
        if (z) {
            this.mZDMicTestLog = str;
        } else {
            this.mMicTestLog = str;
        }
    }

    public void initFactorySvc() {
        new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.51
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                FacRootMenu.this.mFacFunction_Adapter.initMtkManagers(FacRootMenu.this.getApplicationContext());
                NavIntegration unused = FacRootMenu.mNavIntegration = NavIntegration.getInstance(FacRootMenu.mFacRootMenu);
                FacRootMenu.this.mMenuDataContainer = MenuDataContainer.getInstance(FacRootMenu.mFacRootMenu);
                FacRootMenu.this.mhHandler.sendEmptyMessageDelayed(111, 1000L);
                boolean z = false;
                do {
                    if (FacRootMenu.mcf.isFileExit("mnt/vendor/tmp/dtv_svc_is_ready")) {
                        Log.i("Fac_FacRootMenu", "DTV SVC IS OK");
                        z = true;
                    } else {
                        try {
                            Log.i("Fac_FacRootMenu", "DTV SVC IS NOT OK");
                            Thread.sleep(200L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } while (!z);
                FacRootMenu.this.waitForUsbMount();
                try {
                    Log.i("Fac_FacRootMenu", "USB is OK,wait 1s");
                    Thread.sleep(1000L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                FacRootMenu.mcf.setValue("HIFAC_STATUS", 1);
                FacRootMenu.this.startSerialService();
                FacRootMenu.mcf.readDriverInfo();
                FacRootMenu.mcf.setLogToTvcertificate("FacRootMenu enter");
                FacRootMenu.mcf.updateOSDC();
                FacRootMenu.mcf.setRootMenuListener(FacRootMenu.mFacRootMenu);
                if (FacRootMenu.this.mMtkTvHighLevel == null) {
                    FacRootMenu.this.mMtkTvHighLevel = new MtkTvHighLevel();
                }
                FacRootMenu.this.setFactoryEnterParamAsync();
                FacCountryUtils.setContext(FacRootMenu.mFacRootMenu);
                FacCountryUtils.loadCountry();
                FacLanguageUtils.initLanguage();
                FacLanguageUtils.initLanguageArray(FacRootMenu.mFacRootMenu);
                FacLogoUtils.initLogoArray(FacRootMenu.mFacRootMenu);
                FacRootMenu.this.initafterserviceok();
                FacRootMenu.this.setSourceChangedHandle();
                if (FacRootMenu.this.mTvStoped) {
                    Log.i("Fac_FacRootMenu", "set g_hisense__ott_src is 7");
                    FacRootMenu.mcf.setOttSrc(7);
                    FacRootMenu.mcf.setMtkSourceStatus(true);
                    Log.i("Fac_FacRootMenu", "TV started");
                    FacRootMenu.this.mTvStoped = false;
                }
                if (FacPropertyDemo.HIFAC_MIC_SUPPORT) {
                    if (!FacRootMenu.mcf.isZDMicTestOK()) {
                        FacRootMenu.this.mhHandler.sendEmptyMessage(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_ON);
                    }
                    FacRootMenu.this.startMicSwitchCheckThread();
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAgingTime() {
        if (SystemProperties.getInt("sys.fac_has_aged", 0) == 0) {
            this.mFacFunction_Adapter.cleanAgingTime();
        } else {
            this.mFacFunction_Adapter.restoreAgingTime();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitForUsbMount() {
        boolean z;
        try {
            Log.i("Fac_FacRootMenu", "wait for data unlock");
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean zIsInsertUsb = isInsertUsb();
        Log.i("Fac_FacRootMenu", "isHaveUsb = " + zIsInsertUsb);
        if (zIsInsertUsb) {
            try {
                Log.i("Fac_FacRootMenu", "wait for usb");
                Thread.sleep(5000L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            int usbCount = mcf.getUsbCount();
            String[] fileNameList = FacFileUtils.getFileNameList("/mnt/media_rw/");
            if (fileNameList != null) {
                z = false;
                for (int i = 0; i < fileNameList.length; i++) {
                    Log.i("Fac_FacRootMenu", "list [" + i + "] is = " + fileNameList[i]);
                    if (!fileNameList[i].contains("sdcard")) {
                        z = true;
                    }
                }
            } else {
                z = false;
            }
            if (!zIsInsertUsb || (usbCount > 0 && z)) {
                try {
                    Log.i("Fac_FacRootMenu", "media_rw is OK ,wait 1s");
                    Thread.sleep(1000L);
                    return;
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                    return;
                }
            }
            int i2 = 0;
            do {
                i2++;
                try {
                    Log.i("Fac_FacRootMenu", "wait for usb");
                    Thread.sleep(500L);
                } catch (InterruptedException e4) {
                    e4.printStackTrace();
                }
                String[] fileNameList2 = FacFileUtils.getFileNameList("/mnt/media_rw/");
                if (fileNameList2 != null) {
                    for (int i3 = 0; i3 < fileNameList2.length; i3++) {
                        Log.i("Fac_FacRootMenu", "list [" + i3 + "] is = " + fileNameList2[i3]);
                        if (!fileNameList2[i3].contains("sdcard")) {
                            z = true;
                        }
                    }
                }
                if (i2 >= 10) {
                    return;
                }
            } while (!z);
        }
    }

    private boolean isInsertUsb() {
        String[] fileNameList = FacFileUtils.getFileNameList("dev/block/platform/soc/");
        if (fileNameList != null && fileNameList.length > 0) {
            for (String str : fileNameList) {
                Log.i("Fac_FacRootMenu", "isInsertUsb fileName = " + str);
                if (str.contains("USB") || str.contains("usb")) {
                    return true;
                }
            }
        }
        return false;
    }

    private FacPropertyDemo.SourceEnum getPrevSource(boolean z) {
        if (z) {
            if (!mcf.isHaveTuner()) {
                return FacPropertyDemo.SourceEnum.HDMI1;
            }
            return FacPropertyDemo.SourceEnum.TV;
        }
        return mcf.getCurrentInputSourceEnum();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startTestMic(boolean z) {
        if (FacPropertyDemo.HIFAC_MIC_SUPPORT) {
            if ("ON".equals(mcf.getMicSwitchStatus())) {
                this.mFacMicTest.initMicParam(this);
                this.mFacMicTest.startTestMic(this, z);
            } else {
                showToast("Open Mic Swtich First!!!!");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startMicSwitchCheckThread() {
        new Thread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.52
            /* JADX INFO: Infinite loop detected, blocks: 14, insns: 0 */
            @Override // java.lang.Runnable
            public void run() {
                String micSwitchStatus = FacRootMenu.mcf.getMicSwitchStatus();
                while (true) {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String micSwitchStatus2 = FacRootMenu.mcf.getMicSwitchStatus();
                    if (!micSwitchStatus.equals(micSwitchStatus2)) {
                        FacRootMenu.this.showToast("MIC SWITCH: " + micSwitchStatus2);
                        micSwitchStatus = micSwitchStatus2;
                    }
                }
            }
        }).start();
    }

    private void showOkErrorMsg(boolean z, boolean z2, boolean z3) {
        this.mErrMsg = new StringBuffer();
        Log.i("Fac_FacRootMenu", "mac or key not write");
        if (FacPropertyDemo.HIFAC_IS_OLED && !z3) {
            this.mErrMsg.append(getString(R.string.str_factorymenu_tcon_temp_vr) + " NG!\n");
        }
        if (!z) {
            this.mErrMsg.append(facRootMenuContext.getResources().getString(R.string.str_factory_mic_switch_status_not_right) + "\n");
        }
        if (z2) {
            this.mErrMsg.append(facRootMenuContext.getResources().getString(R.string.str_facmenu_mac_not_right) + "\n");
        }
        if (!this.mWideVineOk) {
            if (mcf.isHaveMGK() && !mcf.isWideVineNameIsRight()) {
                this.mErrMsg.append(facRootMenuContext.getResources().getString(R.string.str_facmenu_widevine_is_not_right) + "\n");
            } else {
                this.mErrMsg.append(facRootMenuContext.getResources().getString(R.string.str_facmenu_widevine_cannot_found) + "\n");
            }
        } else if (!mcf.isWideVineNameIsRight()) {
            this.mErrMsg.append(facRootMenuContext.getResources().getString(R.string.str_facmenu_widevine_is_not_right) + "\n");
        }
        if (!this.mPlayReadyOk) {
            this.mErrMsg.append(facRootMenuContext.getResources().getString(R.string.str_facmenu_playready_cannot_found) + "\n");
        }
        if (!this.mHdcp14Ok) {
            this.mErrMsg.append(facRootMenuContext.getResources().getString(R.string.str_facmenu_hdcp14_cannot_found) + "\n");
        }
        if (!this.mHdcp22Ok) {
            this.mErrMsg.append(facRootMenuContext.getResources().getString(R.string.str_facmenu_hdcp2X_cannot_found) + "\n");
        }
        if (!this.mNetflixOk && FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
            if (mcf.isHaveMGK() && !mcf.mgkNameIsRight()) {
                this.mErrMsg.append(facRootMenuContext.getResources().getString(R.string.str_facmenu_mgk_is_not_right) + "\n");
            } else {
                this.mErrMsg.append(facRootMenuContext.getResources().getString(R.string.str_facmenu_netflix_cannot_found) + "\n");
            }
        }
        if (!this.mAttestationOk) {
            this.mErrMsg.append(facRootMenuContext.getResources().getString(R.string.str_facmenu_attestation_cannot_found) + "\n");
        }
        if (!this.mQDAOk) {
            this.mErrMsg.append(facRootMenuContext.getResources().getString(R.string.str_facmenu_qda_cannot_found) + "\n");
        }
        if (FacPropertyDemo.HIFAC_MARKET_SA) {
            String clientType = mcf.getClientType();
            if (clientType == null || clientType.isEmpty()) {
                this.mErrMsg.append(facRootMenuContext.getResources().getString(R.string.str_facmenu_client_type_null) + "\n");
            }
        } else if (FacPropertyDemo.HIFAC_MARKET_JP) {
            if (!this.mMarlinOk) {
                this.mErrMsg.append(facRootMenuContext.getResources().getString(R.string.str_facmenu_marlin_cannot_found) + "\n");
            }
            if (!this.mDtcpIpOk) {
                this.mErrMsg.append(facRootMenuContext.getResources().getString(R.string.str_facmenu_dtcpip_cannot_found) + "\n");
            }
            if (!this.mPVROk) {
                this.mErrMsg.append(facRootMenuContext.getResources().getString(R.string.str_facmenu_pvr_cannot_found) + "\n");
            }
            if (!this.mIsACASSTateOk) {
                this.mErrMsg.append(facRootMenuContext.getResources().getString(R.string.str_facmenu_acas_cannot_found) + "\n");
            }
            if (!this.mIsEMMCOk) {
                this.mErrMsg.append("EMMC ERROR!!");
            }
        }
        if ((mcf.getLogoName().equalsIgnoreCase("Sunbrite") || mcf.getLogoName().equalsIgnoreCase("snb")) && !this.mIsStOk) {
            this.mErrMsg.append("Service Tag does not exist!");
        }
        showResultMsgForOK(this.mErrMsg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void manualWriteProjectId() {
        Log.i("Fac_FacRootMenu", "set manualWriteProjectId mIsAlreadySet = " + mIsAlreadySet);
        if (mIsAlreadySet) {
            return;
        }
        tryToWriteProjectIdWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.53
            @Override // com.hisense.uifac.FacRootMenu.WriteCallback
            public void writeEnd(boolean z, String str) {
                FacRootMenu.this.showProjectIdDialog(str);
            }
        });
    }

    private void tryToWriteProjectIdWithCB(WriteCallback writeCallback) {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        mcf.setProjectIdFromUDiskWithCB(writeCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showProjectIdDialog(final String str) {
        runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.54
            @Override // java.lang.Runnable
            public void run() {
                AlertDialog.Builder builder;
                if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                    builder = new AlertDialog.Builder(FacRootMenu.this, R.style.OLEDAlertDialog);
                } else {
                    builder = new AlertDialog.Builder(FacRootMenu.this);
                }
                builder.setMessage(str);
                builder.setCancelable(false);
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.FacRootMenu.54.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog alertDialogCreate = builder.create();
                alertDialogCreate.show();
                alertDialogCreate.getButton(-2).setFocusable(true);
                alertDialogCreate.getButton(-2).setFocusableInTouchMode(true);
                alertDialogCreate.getButton(-2).requestFocus();
            }
        });
    }

    @Override // com.hisense.uifac.util.IRootMenuUiListener
    public void notifyZDMicTestResult(boolean z) {
        if (z) {
            this.mhHandler.sendEmptyMessage(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_OFF);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getVolumeAfterChangeForSA() {
        int i = this.m3DKeyCount;
        int i2 = 20;
        if (i != 0) {
            if (i == 1) {
                i2 = 50;
            } else if (i == 2) {
                i2 = 60;
            } else if (i == 3) {
                i2 = 100;
            }
        }
        int i3 = this.m3DKeyCount + 1;
        this.m3DKeyCount = i3;
        if (i3 > 3) {
            this.m3DKeyCount = 0;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAttestationAndMGk() {
        this.mhHandler.sendMessage(this.mhHandler.obtainMessage(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_DETECT, getString(R.string.str_factory_set_internal_key)));
        mcf.setPlayReadyAndAttestationFromTV(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.55
            @Override // com.hisense.uifac.FacRootMenu.WriteCallback
            public void writeEnd(boolean z, String str) {
                if (FacRootMenu.this.mToastProgressDialog != null) {
                    FacRootMenu.this.mToastProgressDialog.dismiss();
                    FacRootMenu.this.mToastProgressDialog = null;
                }
            }
        });
    }

    private void showOriginView() {
        mButton_FactoryMenuRoot.setFocusable(true);
        mButton_FactoryMenuRoot.setFocusableInTouchMode(true);
        mButton_FactoryMenuRoot.requestFocus();
    }

    private void showLogoAfterBigMForSA() {
        Log.d("Fac_FacRootMenu", "showLogoAfterBigMForSA");
        if (FacPropertyDemo.HIFAC_MARKET_SA) {
            this.mSALogoName.setVisibility(0);
            this.mSALogoName.setText("LOGO   : " + mcf.getLogoFullNameSA());
            this.mLayout_Bottom_One_Colmun_LL.setVisibility(8);
            this.mLayout_Bottom_Two_Colmun_LL.setVisibility(8);
            this.mModel_ShortTip_LL.setVisibility(8);
            this.mModel_ShortTip_TV.setVisibility(8);
            this.mModel_SourceList_RL.setVisibility(8);
            this.m3DlinearList.setVisibility(8);
            this.mChannelDetailLayout.setVisibility(8);
            mModel_Volume_RL.setVisibility(8);
            this.mModel_InfoBar_RL.setVisibility(8);
            this.mModel_Factoryok_progress.setVisibility(8);
            this.mCommonInfoTopRL.setBackgroundResource(R.drawable.nav_infobar_basic_bg);
            this.mSimpleInfoTopRL.setBackgroundResource(R.drawable.translucent_background);
            this.mModel_InfoBar_RL.setVisibility(0);
            this.mCommonInfoTopRL.setVisibility(0);
            this.mDetailsInfoBottomTV.setVisibility(4);
            this.mSimpleInfoTopRL.setVisibility(0);
            setChannelInfo();
        }
    }

    private void preHandlerKeyDown() {
        int[] iArr = this.mOnKeyDownSecretKeys;
        if (iArr[3] == 4225) {
            switch (iArr[4]) {
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                    if (FacPropertyDemo.HIFAC_MARKET_SA) {
                        handlePictrueWithAudioForSA(iArr[4]);
                        return;
                    }
                    break;
            }
        }
        int[] iArr2 = this.mOnKeyDownSecretKeys;
        if (iArr2[3] == 4206) {
            int i = iArr2[4];
            if (i == 8) {
                if (this.mFacFunction_Adapter.getFactoryTconStatus() == 1) {
                    setTwelveVoltsStatus();
                }
            } else if (i == 9 && this.mFacFunction_Adapter.getFactoryTconStatus() == 1) {
                initInnertconBoard();
            }
        }
    }

    private void handlePictrueWithAudioForSA(int i) {
        if (!this.mTvStoped) {
            mcf.setMtkSourceStatus(false);
            Log.i("Fac_FacRootMenu", "create FacPictureWithAudio,so stopTV");
            this.mTvStoped = true;
        }
        Intent intent = new Intent(this, (Class<?>) FacPictureWithAudio.class);
        Bundle bundle = new Bundle();
        bundle.putInt("FAC_PICTURE_WITH_AUDIO_SA", i);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1969);
        getPrevSource(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideBigMIcon() {
        mButton_FactoryMenuRoot.setVisibility(8);
        if (FacPropertyDemo.HIFAC_MARKET_SA) {
            this.mSALogoName.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void autoTryToWriteProjectId() {
        Log.i("Fac_FacRootMenu", "autoTryToWriteProjectId");
        tryToWriteProjectIdWithCB(new WriteCallback() { // from class: com.hisense.uifac.FacRootMenu.56
            @Override // com.hisense.uifac.FacRootMenu.WriteCallback
            public void writeEnd(boolean z, String str) {
                FacRootMenu.this.showProjectIdDialog(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initSharedPreference() {
        try {
            this.mSharedPreferences = getSharedPreferences("FacShareP", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showStoreModeDialog() {
        Log.i("Fac_FacRootMenu", "showStoreModeDialog");
        runOnUiThread(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.57
            @Override // java.lang.Runnable
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(FacRootMenu.this);
                builder.setCancelable(false);
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.FacRootMenu.57.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                FacRootMenu.this.keysDialog = builder.create();
                FacRootMenu facRootMenu = FacRootMenu.this;
                facRootMenu.keysDialog.setView(facRootMenu.keysDialogView);
                FacRootMenu.this.keysDialog.setCancelable(true);
                FacRootMenu facRootMenu2 = FacRootMenu.this;
                facRootMenu2.keysMessage = (TextView) facRootMenu2.keysDialogView.findViewById(R.id.keys_dialog);
                FacRootMenu.this.keysDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.hisense.uifac.FacRootMenu.57.2
                    @Override // android.content.DialogInterface.OnKeyListener
                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        Log.i("Fac_FacRootMenu", "showStoreModeDialog---------onKey");
                        FacRootMenu.this.finish();
                        return true;
                    }
                });
                FacRootMenu.this.keysMessage.setText("Please exit store mode first!!!!");
                FacRootMenu.this.keysDialog.show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showOKTipOnGreenScreen() {
        hideBigMMode();
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mModel_ShortTip_TV.setTextColor(-16777216);
            this.mModel_ShortTip_TV.setBackgroundColor(facRootMenuContext.getResources().getColor(R.color.green_oled));
        } else {
            this.mModel_ShortTip_TV.setTextColor(-1);
            this.mModel_ShortTip_TV.setBackgroundColor(-16711936);
        }
        this.mModel_ShortTip_TV.setBackground(null);
        this.mModel_ShortTip_TV.setText(R.string.str_factorymenu_ok);
        this.mLayout_Bottom_Two_Colmun_LL.setVisibility(0);
        this.mModel_ShortTip_LL.setVisibility(0);
        this.mModel_ShortTip_TV.setVisibility(0);
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            mFactoryMenuRoot_Layout.setBackgroundResource(R.color.green_oled);
            this.mFacBGColorView.setBackgroundResource(R.color.green_oled);
        } else {
            mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.color_green);
            this.mFacBGColorView.setBackgroundResource(R.drawable.color_green);
        }
        this.mhHandler.removeMessages(4);
        this.mModel_Factoryok_progress.setVisibility(8);
    }

    private void showTvsF1ResultDialog() {
        AlertDialog.Builder builder;
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            builder = new AlertDialog.Builder(this, R.style.OLEDAlertDialog);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setCancelable(false);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.FacRootMenu.58
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        this.acasHddDialog = alertDialogCreate;
        alertDialogCreate.setView(this.acasHddDialogView);
        this.acasHddDialog.setCancelable(true);
        this.acasHddDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.hisense.uifac.FacRootMenu.59
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                Log.i("Fac_FacRootMenu", "showResultMsg---------onKey");
                if (i != 23) {
                    return true;
                }
                Log.i("Fac_FacRootMenu", "acasHddDialog dismiss");
                ((ViewGroup) FacRootMenu.this.acasHddDialogView.getParent()).removeView(FacRootMenu.this.acasHddDialogView);
                FacRootMenu.this.acasHddDialog.dismiss();
                return true;
            }
        });
        this.mhHandler.sendEmptyMessage(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_ON);
        this.acasHddDialog.show();
    }

    class AgingForLaserTvControl {
        private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        private MenuConfigManager.TestPattern pictureCount = MenuConfigManager.TestPattern.RED;
        private ScheduledFuture updateHandle = null;

        AgingForLaserTvControl() {
        }

        public void startAgingForLaserTv() {
            this.updateHandle = this.scheduler.scheduleWithFixedDelay(new Runnable() { // from class: com.hisense.uifac.FacRootMenu.AgingForLaserTvControl.1
                @Override // java.lang.Runnable
                public void run() {
                    Log.i("Fac_FacRootMenu", "update aging color for laser tv.");
                    int i = AnonymousClass60.$SwitchMap$com$hisense$uifac$menu$util$MenuConfigManager$TestPattern[AgingForLaserTvControl.this.pictureCount.ordinal()];
                    if (i == 1) {
                        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                            FacRootMenu.mFactoryMenuRoot_Layout.setBackgroundResource(R.color.red_oled);
                            FacRootMenu.this.mFacBGColorView.setBackgroundResource(R.color.red_oled);
                        } else {
                            FacRootMenu.mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.color_red);
                            FacRootMenu.this.mFacBGColorView.setBackgroundResource(R.drawable.color_red);
                        }
                        AgingForLaserTvControl.this.pictureCount = MenuConfigManager.TestPattern.GREEN;
                        return;
                    }
                    if (i == 2) {
                        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                            FacRootMenu.mFactoryMenuRoot_Layout.setBackgroundResource(R.color.green_oled);
                            FacRootMenu.this.mFacBGColorView.setBackgroundResource(R.color.green_oled);
                        } else {
                            FacRootMenu.mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.color_green);
                            FacRootMenu.this.mFacBGColorView.setBackgroundResource(R.drawable.color_green);
                        }
                        AgingForLaserTvControl.this.pictureCount = MenuConfigManager.TestPattern.BLUE;
                        return;
                    }
                    if (i == 3) {
                        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                            FacRootMenu.mFactoryMenuRoot_Layout.setBackgroundResource(R.color.blue_oled);
                            FacRootMenu.this.mFacBGColorView.setBackgroundResource(R.color.blue_oled);
                        } else {
                            FacRootMenu.mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.color_blue);
                            FacRootMenu.this.mFacBGColorView.setBackgroundResource(R.drawable.color_blue);
                        }
                        AgingForLaserTvControl.this.pictureCount = MenuConfigManager.TestPattern.WHITE;
                        return;
                    }
                    if (i == 4) {
                        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                            FacRootMenu.mFactoryMenuRoot_Layout.setBackgroundResource(R.color.white_oled);
                            FacRootMenu.this.mFacBGColorView.setBackgroundResource(R.color.white_oled);
                        } else {
                            FacRootMenu.mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.color_white);
                            FacRootMenu.this.mFacBGColorView.setBackgroundResource(R.drawable.color_white);
                        }
                        AgingForLaserTvControl.this.pictureCount = MenuConfigManager.TestPattern.RED;
                        return;
                    }
                    AgingForLaserTvControl.this.pictureCount = MenuConfigManager.TestPattern.RED;
                }
            }, 0L, 3L, TimeUnit.SECONDS);
        }

        public void cancelAgingForLaserTv() {
            ScheduledFuture scheduledFuture = this.updateHandle;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
            }
        }

        public void shutdownAgingForLaserTv() {
            this.scheduler.shutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startScreenProtectActivity() {
        Log.i("Fac_FacRootMenu", "startScreenProtectActivity");
        startActivityForResult(new Intent(this, (Class<?>) FacOledScreenProtectActivity.class), 2021);
    }

    private void doFactoryOkForTvs() {
        int[] iArr = this.mLaunchBugReportKeys;
        if (iArr[0] == 14 && iArr[1] == 14 && iArr[2] == 16 && iArr[3] == 16 && iArr[4] == 14 && iArr[5] == 14) {
            this.inputNuming = false;
            this.viewMode = 2;
            if (2 == 0 || 0 != 0) {
                return;
            }
            if (this.mModel_ShortTip_TV.getVisibility() == 0 && this.childType == 20) {
                return;
            }
            if (!this.mFacFunction_Adapter.factoryOk_checkAutoGamma()) {
                this.mModel_ShortTip_TV.setText("Autogamma NG");
                this.mModel_ShortTip_TV.setTextColor(-65536);
                this.mModel_ShortTip_TV.setGravity(17);
                this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
                showTipMode(true);
                this.mhHandler.removeMessages(2);
                this.mhHandler.removeMessages(0);
                return;
            }
            boolean zTestWhetherKeyIsOK = testWhetherKeyIsOK();
            boolean zIsMacAddressDefaultOrIllegal = isMacAddressDefaultOrIllegal();
            boolean zMgkNameIsRight = FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT ? mcf.mgkNameIsRight() : true;
            boolean zIsMicSwitchOn = mcf.isMicSwitchOn();
            boolean zIsHaveClientType = FacPropertyDemo.HIFAC_MARKET_SA ? mcf.isHaveClientType() : true;
            boolean z = (FacPropertyDemo.HIFAC_IS_OLED && mcf.checkTconTempVr() == 1) ? false : true;
            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                this.mIsACASSTateOk = true;
                this.mIsACASSTateOk = mcf.getACASState() == FacParamManager.FAC_TVS_ACAS_STATE_VALID;
                if (!mcf.getEmmcType()) {
                    this.mIsEMMCOk = false;
                }
            }
            Log.i("Fac_FacRootMenu", "Execute OK isKeyOk " + zTestWhetherKeyIsOK);
            Log.i("Fac_FacRootMenu", "Execute OK isMgkNameRight " + zMgkNameIsRight);
            Log.i("Fac_FacRootMenu", "Execute OK isMacNotOk " + zIsMacAddressDefaultOrIllegal);
            Log.i("Fac_FacRootMenu", "Execute OK isMicSwitchOn " + zIsMicSwitchOn);
            Log.i("Fac_FacRootMenu", "Execute OK isTconTempVrOK " + z);
            if (zIsMacAddressDefaultOrIllegal || !zTestWhetherKeyIsOK || !zMgkNameIsRight || !zIsHaveClientType || !this.mIsACASSTateOk || !this.mIsEMMCOk || !z) {
                showOkErrorMsg(true, zIsMacAddressDefaultOrIllegal, z);
                return;
            }
            mFactoryMenuRoot_Layout.setBackgroundResource(R.drawable.translucent_background);
            this.mFacBGColorView.setBackgroundResource(R.drawable.translucent_background);
            this.mModel_ShortTip_TV.setText(R.string.str_factorymenu_waiting);
            this.mModel_ShortTip_TV.startAnimation(this.mTipEnterAnimation);
            showTipMode(true);
            this.mhHandler.removeMessages(2);
            this.mhHandler.removeMessages(0);
            this.childType = 20;
            mSetShortTipData(20);
        }
    }

    private void setTwelveVoltsStatus() {
        int twelveVoltsStatus;
        String str;
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        if (this.mCurrentTwelveVoltsStatus == 0) {
            twelveVoltsStatus = mcf.setTwelveVoltsStatus(1);
            str = "ON";
        } else {
            twelveVoltsStatus = mcf.setTwelveVoltsStatus(0);
            str = "OFF";
        }
        if (twelveVoltsStatus == 0) {
            this.mCurrentTwelveVoltsStatus = this.mCurrentTwelveVoltsStatus != 1 ? 1 : 0;
            Toast.makeText(this, facRootMenuContext.getResources().getString(R.string.str_factorymenu_twelve_volts_status) + str, 1).show();
        }
    }

    private void initInnertconBoard() {
        if (mcf == null) {
            mcf = MenuConfigManager.getInstance(this);
        }
        int iInitInnertconBoard = mcf.initInnertconBoard();
        if (iInitInnertconBoard == 1) {
            Toast.makeText(this, facRootMenuContext.getResources().getString(R.string.str_factorymenu_initok), 1).show();
        } else if (iInitInnertconBoard == 0) {
            Toast.makeText(this, facRootMenuContext.getResources().getString(R.string.str_factorymenu_completed), 1).show();
        } else {
            Toast.makeText(this, facRootMenuContext.getResources().getString(R.string.str_factorymenu_initng), 1).show();
        }
    }

    private void preHandlerKeyUp() {
        String string;
        if (FacPropertyDemo.HIFAC_MARKET_SA) {
            int[] iArr = this.mSecretKeys;
            if (iArr[1] == 4221) {
                if (iArr[3] == 23) {
                    FacPropertyDemo.SourceEnum currentSource = this.mFacFunction_Adapter.getCurrentSource();
                    boolean z = (currentSource == FacPropertyDemo.SourceEnum.TV || currentSource == FacPropertyDemo.SourceEnum.ATV || currentSource == FacPropertyDemo.SourceEnum.DTV) ? false : true;
                    switch (this.mSecretKeys[2]) {
                        case 8:
                            Log.i("Fac_FacRootMenu", "addTVPresetChannelForSA NEWSAN");
                            mcf.addTVPresetChannelForSA(27);
                            string = getResources().getString(R.string.str_factorymenu_newsan_present_channel_result);
                            break;
                        case 9:
                            Log.i("Fac_FacRootMenu", "addTVPresetChannelForSA BGH");
                            mcf.addTVPresetChannelForSA(28);
                            string = getResources().getString(R.string.str_factorymenu_bgh_present_channel_result);
                            break;
                        case 10:
                            Log.i("Fac_FacRootMenu", "addTVPresetChannelForSA RIVIERA");
                            mcf.addTVPresetChannelForSA(29);
                            string = getResources().getString(R.string.str_factorymenu_riviera_present_channel_result);
                            break;
                        case 11:
                            Log.i("Fac_FacRootMenu", "addTVPresetChannelForSA FRAVEGA");
                            mcf.addTVPresetChannelForSA(30);
                            string = getResources().getString(R.string.str_factorymenu_fravega_present_channel_result);
                            break;
                        case 12:
                            Log.i("Fac_FacRootMenu", "addTVPresetChannelForSA BRAZIL");
                            mcf.addTVPresetChannelForSA(35);
                            string = getResources().getString(R.string.str_factorymenu_brazil_present_channel_result);
                            break;
                        default:
                            string = "";
                            break;
                    }
                    showToast(string);
                    if (z) {
                        showSourceInfoBar();
                    } else {
                        displayLeftTopInfoBar();
                        try {
                            Thread.sleep(500L);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        displayRightTopInfoBar();
                    }
                }
                if (this.savingKeyIsPressed) {
                    this.savingKeyIsPressed = false;
                }
            }
        }
    }
}
