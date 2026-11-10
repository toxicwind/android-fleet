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
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemProperties;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.tvcm.FactoryConstants;
import com.hisense.uifac.menu.adapter.SetConfigListViewAdapter;
import com.hisense.uifac.menu.commonview.MenuSetUpListView;
import com.hisense.uifac.menu.commonview.RespondedKeyEvent;
import com.hisense.uifac.menu.util.MenuConfigManager;
import com.hisense.uifac.menu.util.MenuDataContainer;
import com.hisense.uifac.nav.NavIntegration;
import com.mediatek.ExoMediaPlayer;
import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaPlayerActivity extends Activity implements SurfaceHolder.Callback {
    public static String VIDEO_FILE_ABS_PATH = "videoFileDirctory";
    public static String VIDEO_FILE_NAME = "videoFileName";
    private static boolean activityIsRunning = false;
    private AlertDialog errorDialog;
    private _3DSettingDialog m3DSettingDialog;
    private TextView mBalanceTip;
    private Bundle mBundle;
    MenuConfigManager mMenuConfigManager;
    private NavIntegration mNavIntegration;
    private PictureCurveDialog mPictureCurveDialog;
    FacPropertyDemo.SourceEnum mPrevSource;
    private Animation mTipEnterAnimation;
    private WhiteBalanceDialog mWhiteBalanceDialog;
    private String path;
    private Handler playerHandler;
    private ProgressDialog prepareDialog;
    MediaPlayer mPlayer = null;
    SurfaceView sv = null;
    private FacFunction_Adapter mFacFunction_Adapter = null;
    private String mVideoFileAbsPathName = null;
    private boolean mIs3DFromInside = false;
    private String mVideoPlayingStatus = null;
    private HandlerThread handlerThread = new HandlerThread("MediaPlayer");
    private final Handler uiHandler = new Handler() { // from class: com.hisense.uifac.MediaPlayerActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                MediaPlayerActivity.this.prepareDialog.show();
            } else if (i == 1) {
                MediaPlayerActivity.this.prepareDialog.dismiss();
            } else if (i != 2) {
                if (i == 3) {
                    MediaPlayerActivity.this.checkVideoPlayStatus();
                } else if (i == 4) {
                    MediaPlayerActivity.this.stopAndReleasyPlayer();
                }
            } else if (MediaPlayerActivity.activityIsRunning) {
                MediaPlayerActivity.this.errorDialog.show();
            }
            super.handleMessage(message);
        }
    };
    private BroadcastReceiver usbStatusReceiver = new BroadcastReceiver() { // from class: com.hisense.uifac.MediaPlayerActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("Fac_MediaPlayerActivity", "usbStatusReceiver reveice: " + action);
            String path = intent.getData() != null ? intent.getData().getPath() : "";
            Log.d("Fac_MediaPlayerActivity", "usbStatusReceiver path1: " + path);
            if (action != null && action.equalsIgnoreCase("android.intent.action.MEDIA_UNMOUNTED")) {
                if (!MediaPlayerActivity.this.path.startsWith(path) || MediaPlayerActivity.this.mPlayer == null) {
                    return;
                }
                Log.d("Fac_MediaPlayerActivity", "unmounted usb device, stop playing video");
                MediaPlayerActivity.this.stopAndReleasyPlayer();
                return;
            }
            if (action == null || !action.equalsIgnoreCase("android.os.storage.action.VOLUME_STATE_CHANGED")) {
                return;
            }
            int intExtra = intent.getIntExtra("android.os.storage.extra.VOLUME_STATE", -1);
            Log.d("Fac_MediaPlayerActivity", "usbStatusReceiver state: " + intExtra);
            if ((intExtra == 0 || intExtra == 8 || intExtra == 5) && MediaPlayerActivity.this.mPlayer != null) {
                Log.d("Fac_MediaPlayerActivity", "remove usb device, stop playing video");
                MediaPlayerActivity.this.stopAndReleasyPlayer();
            }
        }
    };

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkVideoPlayStatus() {
        this.mVideoPlayingStatus = SystemProperties.get("sys.media.playing", (String) null);
        Log.i("Fac_MediaPlayerActivity", "mVideoPlayingStatus is " + this.mVideoPlayingStatus);
        String str = this.mVideoPlayingStatus;
        if (str != null && str.equalsIgnoreCase("playing")) {
            String str2 = this.mVideoFileAbsPathName;
            if (str2 != null && str2.contains("3D") && this.mMenuConfigManager.is3DSupported()) {
                try {
                    Thread.sleep(1000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                FactoryConstants.canBluetooth3DBroadcastReceive = true;
                this.mFacFunction_Adapter.set3DMode(FacPropertyDemo.ThreeDimensionMode.SIDEBYSIDE);
                this.mIs3DFromInside = true;
                return;
            }
            return;
        }
        this.uiHandler.sendEmptyMessageDelayed(3, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void operateMediaPlayerBackground(final int i, final String str) {
        this.playerHandler.post(new Runnable() { // from class: com.hisense.uifac.MediaPlayerActivity.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (MediaPlayerActivity.this.mPlayer != null) {
                        int i2 = i;
                        if (i2 == 0) {
                            MediaPlayerActivity.this.mPlayer.start();
                            MediaPlayerActivity.this.checkVideoPlayStatus();
                            MediaPlayerActivity.this.uiHandler.sendEmptyMessage(1);
                        } else if (i2 == 1) {
                            MediaPlayerActivity.this.uiHandler.sendEmptyMessage(0);
                            MediaPlayerActivity.this.prepare(str);
                        } else if (i2 == 3) {
                            MediaPlayerActivity.this.handlerThread.quit();
                        } else if (i2 == 4 && MediaPlayerActivity.this.mPlayer != null) {
                            if (MediaPlayerActivity.this.mPlayer.isPlaying()) {
                                Log.d("Fac_MediaPlayerActivity", "#MediaPlayerActivity# player pause ");
                                MediaPlayerActivity.this.mPlayer.pause();
                            } else {
                                Log.d("Fac_MediaPlayerActivity", "#MediaPlayerActivity# player continue");
                                MediaPlayerActivity.this.mPlayer.start();
                            }
                        }
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e2) {
                    e2.printStackTrace();
                } catch (SecurityException e3) {
                    e3.printStackTrace();
                }
            }
        });
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Log.i("Fac_MediaPlayerActivity", "MediaPlayer onCreate");
        super.onCreate(bundle);
        activityIsRunning = true;
        this.mBundle = getIntent().getExtras();
        setContentView(R.layout.video_player_view);
        getWindow().addFlags(128);
        this.mFacFunction_Adapter = FacFunction_Adapter.getInstance(getApplicationContext());
        this.mMenuConfigManager = MenuConfigManager.getInstance(getApplicationContext());
        this.mNavIntegration = NavIntegration.getInstance(getApplicationContext());
        this.mMenuConfigManager.setLogToTvcertificate("play bmp");
        this.mIs3DFromInside = false;
        ProgressDialog progressDialog = new ProgressDialog(this);
        this.prepareDialog = progressDialog;
        progressDialog.setMessage(getResources().getString(R.string.str_dmp_prepare));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.str_dmp_cant_play));
        AlertDialog alertDialogCreate = builder.create();
        this.errorDialog = alertDialogCreate;
        alertDialogCreate.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.hisense.uifac.MediaPlayerActivity.4
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                MediaPlayerActivity.this.finish();
            }
        });
        this.errorDialog.setButton(-1, getResources().getString(R.string.alert_dialog_ok), new DialogInterface.OnClickListener() { // from class: com.hisense.uifac.MediaPlayerActivity.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MediaPlayerActivity.this.stopAndReleasyPlayer();
            }
        });
        this.handlerThread.start();
        this.playerHandler = new Handler(this.handlerThread.getLooper());
        IntentFilter intentFilter = new IntentFilter("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addDataScheme("file");
        registerReceiver(this.usbStatusReceiver, intentFilter);
        registerReceiver(this.usbStatusReceiver, new IntentFilter("android.os.storage.action.VOLUME_STATE_CHANGED"));
        this.mPlayer = new ExoMediaPlayer();
        this.sv = (SurfaceView) findViewById(R.id.sv);
        this.mBalanceTip = (TextView) findViewById(R.id.nav_tv_shortTip_model);
        this.mTipEnterAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_enter);
        this.sv.getHolder().addCallback(this);
        this.mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.hisense.uifac.MediaPlayerActivity.6
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                Log.i("Fac_MediaPlayerActivity", "onPrepared");
                MediaPlayerActivity.this.operateMediaPlayerBackground(0, null);
            }
        });
        this.mPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener(this) { // from class: com.hisense.uifac.MediaPlayerActivity.7
            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                Log.i("Fac_MediaPlayerActivity", "SeekComplete");
            }
        });
        this.mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.hisense.uifac.MediaPlayerActivity.8
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                Log.i("Fac_MediaPlayerActivity", "onCompletion");
                MediaPlayerActivity.this.mPlayer.reset();
                MediaPlayerActivity mediaPlayerActivity = MediaPlayerActivity.this;
                mediaPlayerActivity.operateMediaPlayerBackground(1, mediaPlayerActivity.path);
            }
        });
        this.mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.hisense.uifac.MediaPlayerActivity.9
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                Log.i("Fac_MediaPlayerActivity", "onError");
                MediaPlayerActivity.this.uiHandler.sendEmptyMessage(1);
                MediaPlayerActivity.this.uiHandler.sendEmptyMessage(2);
                if (i == 4000) {
                    MediaPlayerActivity.this.uiHandler.sendEmptyMessage(4);
                }
                return true;
            }
        });
        this.mPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() { // from class: com.hisense.uifac.MediaPlayerActivity.10
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                Log.i("Fac_MediaPlayerActivity", "onInfo--arg1 = " + i + "; onInfo--arg2 = " + i2);
                if (i != -5000 && i != -5008) {
                    return true;
                }
                MediaPlayerActivity.this.stopAndReleasyPlayer();
                return true;
            }
        });
        this.mPrevSource = this.mFacFunction_Adapter.getCurrentSource();
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            this.mFacFunction_Adapter.setTVSTvSourceStatus(true, 0);
        }
        Log.i("Fac_MediaPlayerActivity", "Source Service is available");
        operateMediaPlayerBackground(2, null);
        prepareVideoFile();
        this.mFacFunction_Adapter.setOttSrc(6);
        this.mFacFunction_Adapter.updateFacLogStatus(32);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopAndReleasyPlayer() {
        if (!activityIsRunning) {
            Log.i("Fac_MediaPlayerActivity", "player has released");
            return;
        }
        activityIsRunning = false;
        this.mPlayer.release();
        try {
            Thread.sleep(200L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mFacFunction_Adapter.switchSource(this.mPrevSource);
        this.mFacFunction_Adapter.setTvDisabled(false);
        this.mFacFunction_Adapter.setMtkSourceStatus(true);
        finish();
    }

    private void prepareVideoFile() {
        this.path = "/mnt/usbdisk/VIDEO_TEST.mp4";
        Bundle bundle = this.mBundle;
        if (bundle != null && bundle.getString(VIDEO_FILE_ABS_PATH) != null && this.mBundle.getString(VIDEO_FILE_NAME) != null) {
            String string = this.mBundle.getString(VIDEO_FILE_ABS_PATH);
            this.mVideoFileAbsPathName = this.mBundle.getString(VIDEO_FILE_NAME);
            Log.i("Fac_MediaPlayerActivity", "videoFileAbsPath = " + string);
            Log.i("Fac_MediaPlayerActivity", "mVideoFileAbsPathName = " + this.mVideoFileAbsPathName);
            this.path = string + File.separator + this.mVideoFileAbsPathName;
            String str = this.mVideoFileAbsPathName;
            if (str != null && str.contains("3D") && this.mMenuConfigManager.is3DSupported() && FacPropertyDemo.HIFAC_BLUETOOTH_SUPPORT) {
                controlBluetooth3DSync(true);
            }
        }
        if (new File(this.path).exists()) {
            operateMediaPlayerBackground(1, this.path);
        }
    }

    private void controlBluetooth3DSync(boolean z) {
        if (z) {
            sendBroadcast(new Intent("com.hisense.tv.bluetooth.bluetooth3D_mode_on"));
            Log.i("Fac_MediaPlayerActivity", "MediaPlayer send broadcast com.hisense.tv.bluetooth.bluetooth3D_mode_on");
        } else {
            sendBroadcast(new Intent("com.hisense.tv.bluetooth.bluetooth3D_mode_off"));
            Log.i("Fac_MediaPlayerActivity", "MediaPlayer send broadcast com.hisense.tv.bluetooth.bluetooth3D_mode_off");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepare(String str) {
        Log.d("Fac_MediaPlayerActivity", "mediaplayer perpare path = " + str);
        try {
            try {
                Method declaredMethod = this.mPlayer.getClass().getDeclaredMethod("setmmmPlayerType", Integer.TYPE);
                Log.d("Fac_MediaPlayerActivity", "mediaplayer set type 6");
                declaredMethod.invoke(this.mPlayer, 6);
            } catch (Exception e) {
                Log.e("Fac_MediaPlayerActivity", "reflect setmPlayerType exception" + e);
            }
            this.mPlayer.setDataSource(this, Uri.fromFile(new File(str)));
            this.mPlayer.prepareAsync();
        } catch (Exception e2) {
            e2.printStackTrace();
            this.uiHandler.sendEmptyMessage(1);
            this.uiHandler.sendEmptyMessage(2);
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        unregisterReceiver(this.usbStatusReceiver);
        Log.d("Fac_MediaPlayerActivity", "onStop ");
        this.uiHandler.removeMessages(3);
        operateMediaPlayerBackground(3, null);
        if (FacPropertyDemo.HIFAC_BLUETOOTH_SUPPORT) {
            controlBluetooth3DSync(false);
        }
        if (this.mMenuConfigManager.is3DSupported()) {
            Log.i("Fac_MediaPlayerActivity", "onStop 3dmode is " + this.mFacFunction_Adapter.get3DMode());
            FactoryConstants.canBluetooth3DBroadcastReceive = false;
            if (FacPropertyDemo.ThreeDimensionMode.OFF != this.mFacFunction_Adapter.get3DMode()) {
                this.mFacFunction_Adapter.set3DMode(FacPropertyDemo.ThreeDimensionMode.OFF);
            }
            this.mIs3DFromInside = false;
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Fac_MediaPlayerActivity", "onDestroy ");
        this.mPlayer = null;
        activityIsRunning = false;
        this.mFacFunction_Adapter.setOttSrc(7);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mPlayer.setDisplay(surfaceHolder);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        String str;
        String str2;
        Log.d("Fac_MediaPlayerActivity", "#MediaPlayerActivity# onKeyDown keycode =  " + i);
        Log.d("Fac_MediaPlayerActivity", "#MediaPlayerActivity# onKeyDown: " + keyEvent);
        if (i != 4224 && this.mBalanceTip.getVisibility() == 0) {
            Log.i("Fac_MediaPlayerActivity", "balance mode");
            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_650) {
                    this.mNavIntegration.preSetTvsTheeterWooferBalanceToCenter();
                } else if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_550) {
                    this.mNavIntegration.preSetTvsTheeterBalanceToCenter();
                } else if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_850) {
                    this.mNavIntegration.preSetTvsBalanceToCenterLike850();
                }
            } else if (this.mMenuConfigManager.getSubWooferTotal() == 1) {
                this.mNavIntegration.preSetDoubleAmplifierBalanceToCenter();
            } else if (this.mMenuConfigManager.getSubWooferTotal() == 2) {
                this.mNavIntegration.preSetMultiAmplifierBalanceToMIDDLE();
            } else {
                this.mNavIntegration.setBalanceToMiddle();
            }
            this.mBalanceTip.setVisibility(8);
        }
        if (i == 126) {
            operateMediaPlayerBackground(4, null);
            return true;
        }
        if (i == 4252) {
            if (this.mMenuConfigManager.is3DSupported() && this.mIs3DFromInside) {
                Log.d("Fac_MediaPlayerActivity", "#MediaPlayerActivity# show 3d setting dialog");
                show3DSetting();
            } else {
                Log.d("Fac_MediaPlayerActivity", "#MediaPlayerActivity# 3d is not supported, won't show 3d setting");
            }
            return true;
        }
        if (i == 4390) {
            return true;
        }
        if (i == 4357 || i == 4) {
            stopAndReleasyPlayer();
            return true;
        }
        if (i == 4221) {
            showWhiteBalanceDialog();
            return true;
        }
        if (i == 4222) {
            showPictureCurveDialog();
            return true;
        }
        if (i == 4224) {
            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_650) {
                    str2 = "HIFAC_TVS_BALANCE_LIKE_650";
                } else {
                    str2 = FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_850 ? "HIFAC_TVS_BALANCE_LIKE_850" : "HIFAC_TVS_BALANCE_LIKE_550";
                }
                if (this.mBalanceTip.getVisibility() == 0) {
                    this.mNavIntegration.iSetNextEffect(this.mBalanceTip, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.MediaPlayerActivity.11
                        @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                        public void effectChanged(View view, int i2) {
                            MediaPlayerActivity.this.mBalanceTip.setText(i2);
                        }
                    }, str2);
                } else {
                    if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_650) {
                        this.mNavIntegration.preSetTvsTheeterWooferBalanceToML();
                    } else if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_550) {
                        this.mNavIntegration.preSetTvsTheeterBalanceToML();
                    } else if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_850) {
                        this.mNavIntegration.preSetTvsBalanceToMLLike850();
                    }
                    this.mBalanceTip.setText(this.mNavIntegration.iGetEffect(str2));
                    this.mBalanceTip.startAnimation(this.mTipEnterAnimation);
                    this.mBalanceTip.setVisibility(0);
                }
                mSetShortTipData();
            } else {
                if (this.mMenuConfigManager.getSubWooferTotal() == 1) {
                    str = "HIFAC_BALANCE_DOUBLE_AMP";
                } else if (this.mMenuConfigManager.getSubWooferTotal() == 2) {
                    str = "HIFAC_BALANCE_MULTI_AMP";
                } else {
                    str = this.mMenuConfigManager.getSubWooferTotal() > 2 ? "HIFAC_BALANCE_UNIV_MULTI_AMP" : "HIFAC_BALANCE";
                }
                if (this.mBalanceTip.getVisibility() == 0) {
                    this.mNavIntegration.iSetNextEffect(this.mBalanceTip, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.MediaPlayerActivity.12
                        @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                        public void effectChanged(View view, int i2) {
                            MediaPlayerActivity.this.mBalanceTip.setText(i2);
                        }
                    }, str);
                } else {
                    if (this.mMenuConfigManager.getSubWooferTotal() == 1) {
                        this.mNavIntegration.preSetDoubleAmplifierBalanceToML();
                    } else if (this.mMenuConfigManager.getSubWooferTotal() == 2) {
                        this.mNavIntegration.preSetMultiAmplifierBalanceToML();
                    } else if (FacPropertyDemo.HIFAC_AMP_WOOFER_SUPPORT) {
                        this.mNavIntegration.preSetBalanceToWoofer();
                    } else {
                        this.mNavIntegration.preSetBalanceToLeft();
                    }
                    this.mBalanceTip.setText(this.mNavIntegration.iGetEffect(str));
                    this.mBalanceTip.startAnimation(this.mTipEnterAnimation);
                    this.mBalanceTip.setVisibility(0);
                }
                mSetShortTipData();
            }
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void mSetShortTipData() {
        this.mBalanceTip.setTextColor(getResources().getColor(R.color.white));
        this.mBalanceTip.setTextSize(getResources().getDimension(R.dimen.nav_textsize_normal));
        this.mBalanceTip.setBackgroundDrawable(getResources().getDrawable(R.drawable.nav_shorttip_bg));
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_650) {
                this.mBalanceTip.setText(this.mNavIntegration.iGetEffect("HIFAC_TVS_BALANCE_LIKE_650"));
                return;
            } else if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_550) {
                this.mBalanceTip.setText(this.mNavIntegration.iGetEffect("HIFAC_TVS_BALANCE_LIKE_550"));
                return;
            } else {
                if (FacPropertyDemo.HIFAC_TVS_BALANCE_LIKE_850) {
                    this.mBalanceTip.setText(this.mNavIntegration.iGetEffect("HIFAC_TVS_BALANCE_LIKE_850"));
                    return;
                }
                return;
            }
        }
        if (this.mMenuConfigManager.getSubWooferTotal() == 1) {
            this.mBalanceTip.setText(this.mNavIntegration.iGetEffect("HIFAC_BALANCE_DOUBLE_AMP"));
            return;
        }
        if (this.mMenuConfigManager.getSubWooferTotal() == 2) {
            this.mBalanceTip.setText(this.mNavIntegration.iGetEffect("HIFAC_BALANCE_MULTI_AMP"));
        } else if (this.mMenuConfigManager.getSubWooferTotal() > 2) {
            this.mBalanceTip.setText(this.mNavIntegration.iGetEffect("HIFAC_BALANCE_UNIV_MULTI_AMP"));
        } else {
            this.mBalanceTip.setText(this.mNavIntegration.iGetEffect("HIFAC_BALANCE"));
        }
    }

    private void show3DSetting() {
        if (this.m3DSettingDialog == null) {
            this.m3DSettingDialog = new _3DSettingDialog(this, MenuDataContainer.getInstance(getApplicationContext()));
        }
        this.m3DSettingDialog.show();
    }

    class _3DSettingDialog extends Dialog implements DialogInterface.OnKeyListener {
        private MenuDataContainer mDataContainer;
        private MenuSetUpListView mListView;
        private SetConfigListViewAdapter mListViewAdpter_3DSetting;
        private TextView mTxViewTitle;

        protected _3DSettingDialog(Context context, MenuDataContainer menuDataContainer) {
            super(context, android.R.style.Theme.DeviceDefault.Dialog.NoActionBar);
            this.mListViewAdpter_3DSetting = new SetConfigListViewAdapter(context);
            this.mDataContainer = menuDataContainer;
        }

        @Override // android.app.Dialog
        protected void onCreate(Bundle bundle) {
            setContentView(R.layout.video_player_3dsetting);
            getWindow().setGravity(49);
            TextView textView = (TextView) findViewById(R.id.title);
            this.mTxViewTitle = textView;
            textView.setText(MediaPlayerActivity.this.getResources().getString(R.string.str_facmenu_3dsetting));
            this.mListView = (MenuSetUpListView) findViewById(R.id.dmp_3dsettinglistView);
            load3DSetting();
            setOnKeyListener(this);
        }

        private void load3DSetting() {
            this.mDataContainer.load3DSetting();
            set3DSettingAdapter(this.mDataContainer.getFacDataGroup_3DSetting());
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void set3DSettingAdapter(List<?> list) {
            this.mListView.initData(list, 6, null, 0);
            this.mListViewAdpter_3DSetting.setmGroup(this.mListView.getCurrentList());
            this.mListView.setAdapter((ListAdapter) this.mListViewAdpter_3DSetting);
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() != 0) {
                return false;
            }
            RespondedKeyEvent respondedKeyEvent = (RespondedKeyEvent) this.mListView.getSelectedView();
            if (respondedKeyEvent == null) {
                Log.d("Fac_MediaPlayerActivity", "#MediaPlayerActivity# respondedKeyEvent is null");
                return false;
            }
            if (i == 21) {
                respondedKeyEvent.onKeyLeft();
                return true;
            }
            if (i != 22) {
                return false;
            }
            respondedKeyEvent.onKeyRight();
            return true;
        }
    }

    private void showWhiteBalanceDialog() {
        if (this.mWhiteBalanceDialog != null) {
            this.mWhiteBalanceDialog = null;
        }
        if (this.mWhiteBalanceDialog == null) {
            this.mWhiteBalanceDialog = new WhiteBalanceDialog(this, MenuDataContainer.getInstance(getApplicationContext()));
        }
        this.mWhiteBalanceDialog.show();
    }

    class WhiteBalanceDialog extends Dialog implements DialogInterface.OnKeyListener {
        private MenuDataContainer mDataContainer;
        private MenuSetUpListView mListView;
        private SetConfigListViewAdapter mListViewAdpter_WhiteBalance;
        private TextView mTxViewTitle;

        protected WhiteBalanceDialog(Context context, MenuDataContainer menuDataContainer) {
            super(context, android.R.style.Theme.DeviceDefault.Dialog.NoActionBar);
            this.mListViewAdpter_WhiteBalance = new SetConfigListViewAdapter(context);
            this.mDataContainer = menuDataContainer;
        }

        @Override // android.app.Dialog
        protected void onCreate(Bundle bundle) {
            setContentView(R.layout.video_player_3dsetting);
            getWindow().setGravity(49);
            TextView textView = (TextView) findViewById(R.id.title);
            this.mTxViewTitle = textView;
            textView.setText(MediaPlayerActivity.this.getResources().getString(R.string.str_factorymenu_whitebalance));
            this.mListView = (MenuSetUpListView) findViewById(R.id.dmp_3dsettinglistView);
            loadHisFacData_WhiteBalance();
            setOnKeyListener(this);
        }

        private void loadHisFacData_WhiteBalance() {
            this.mDataContainer.loadHisFacData_WhiteBalance();
            setWhiteBalanceAdapter(this.mDataContainer.getFacDataGroup_WhiteBalance());
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void setWhiteBalanceAdapter(List<?> list) {
            this.mListView.initData(list, 7, null, 0);
            this.mListViewAdpter_WhiteBalance.setmGroup(this.mListView.getCurrentList());
            this.mListView.setAdapter((ListAdapter) this.mListViewAdpter_WhiteBalance);
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() != 0) {
                return false;
            }
            RespondedKeyEvent respondedKeyEvent = (RespondedKeyEvent) this.mListView.getSelectedView();
            if (respondedKeyEvent == null) {
                Log.d("Fac_MediaPlayerActivity", "#MediaPlayerActivity# respondedKeyEvent is null");
                return false;
            }
            if (i == 21) {
                respondedKeyEvent.onKeyLeft();
                return true;
            }
            if (i != 22) {
                return false;
            }
            respondedKeyEvent.onKeyRight();
            return true;
        }
    }

    private void showPictureCurveDialog() {
        if (this.mPictureCurveDialog != null) {
            this.mPictureCurveDialog = null;
        }
        if (this.mPictureCurveDialog == null) {
            this.mPictureCurveDialog = new PictureCurveDialog(this, MenuDataContainer.getInstance(getApplicationContext()));
        }
        this.mPictureCurveDialog.show();
    }

    class PictureCurveDialog extends Dialog implements DialogInterface.OnKeyListener {
        private MenuDataContainer mDataContainer;
        private MenuSetUpListView mListView;
        private SetConfigListViewAdapter mListViewAdpter_PictureCurve;
        private TextView mTxViewTitle;

        protected PictureCurveDialog(Context context, MenuDataContainer menuDataContainer) {
            super(context, android.R.style.Theme.DeviceDefault.Dialog.NoActionBar);
            this.mListViewAdpter_PictureCurve = new SetConfigListViewAdapter(context);
            this.mDataContainer = menuDataContainer;
        }

        @Override // android.app.Dialog
        protected void onCreate(Bundle bundle) {
            setContentView(R.layout.video_player_3dsetting);
            getWindow().setGravity(49);
            TextView textView = (TextView) findViewById(R.id.title);
            this.mTxViewTitle = textView;
            textView.setText(MediaPlayerActivity.this.getResources().getString(R.string.str_factorymenu_picturecurve));
            this.mListView = (MenuSetUpListView) findViewById(R.id.dmp_3dsettinglistView);
            loadHisFacData_PictureCurve();
            setOnKeyListener(this);
        }

        private void loadHisFacData_PictureCurve() {
            this.mDataContainer.loadHisDesData_PictureCurve();
            setPictureCurveAdapter(this.mDataContainer.getDesDataGroup_PictureCurve());
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void setPictureCurveAdapter(List<?> list) {
            this.mListView.initData(list, 7, null, 0);
            this.mListViewAdpter_PictureCurve.setmGroup(this.mListView.getCurrentList());
            this.mListView.setAdapter((ListAdapter) this.mListViewAdpter_PictureCurve);
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() != 0) {
                return false;
            }
            RespondedKeyEvent respondedKeyEvent = (RespondedKeyEvent) this.mListView.getSelectedView();
            if (respondedKeyEvent == null) {
                Log.d("Fac_MediaPlayerActivity", "#MediaPlayerActivity# respondedKeyEvent is null");
                return false;
            }
            if (i == 21) {
                respondedKeyEvent.onKeyLeft();
                return true;
            }
            if (i != 22) {
                return false;
            }
            respondedKeyEvent.onKeyRight();
            return true;
        }
    }
}
