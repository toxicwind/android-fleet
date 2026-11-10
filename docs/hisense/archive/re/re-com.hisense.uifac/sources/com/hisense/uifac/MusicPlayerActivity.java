package com.hisense.uifac;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.MediaController;
import android.widget.TextView;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.menu.adapter.SetConfigListViewAdapter;
import com.hisense.uifac.menu.commonview.MenuSetUpListView;
import com.hisense.uifac.menu.commonview.RespondedKeyEvent;
import com.hisense.uifac.menu.util.MenuConfigManager;
import com.hisense.uifac.menu.util.MenuDataContainer;
import com.hisense.uifac.nav.NavIntegration;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MusicPlayerActivity extends Activity {
    MusicService.MusicBinder binder;
    private String fileName;
    private String filePath;
    private ImageView image;
    private TextView mBalanceTip;
    private MediaController mController;
    MenuConfigManager mMenuConfigManager;
    private NavIntegration mNavIntegration;
    FacPropertyDemo.SourceEnum mPrevSource;
    private SoundCurveDialog mSoundCurveDialog;
    private Animation mTipEnterAnimation;
    MusicService musicService;
    private FacFunction_Adapter mFacFunction_Adapter = null;
    private Intent intent = null;
    ServiceConnection conn = new ServiceConnection() { // from class: com.hisense.uifac.MusicPlayerActivity.1
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("Fac_MusicPlayerActivity", "!!!!~MusicActivity，onServiceDisconnected~,goodbye!!!!");
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicPlayerActivity musicPlayerActivity = MusicPlayerActivity.this;
            MusicService.MusicBinder musicBinder = (MusicService.MusicBinder) iBinder;
            musicPlayerActivity.binder = musicBinder;
            musicPlayerActivity.musicService = musicBinder.getService();
            MusicPlayerActivity.this.musicService.setMusicPath(MusicPlayerActivity.this.filePath + "/" + MusicPlayerActivity.this.fileName);
            MusicPlayerActivity.this.mController.setMediaPlayer(MusicPlayerActivity.this.mpc);
            MusicPlayerActivity.this.mController.setEnabled(true);
            Log.i("Fac_MusicPlayerActivity", "!!!!onServiceConnected!!!! " + MusicPlayerActivity.this.musicService);
        }
    };
    MediaController.MediaPlayerControl mpc = new MediaController.MediaPlayerControl() { // from class: com.hisense.uifac.MusicPlayerActivity.4
        @Override // android.widget.MediaController.MediaPlayerControl
        public boolean canPause() {
            return true;
        }

        @Override // android.widget.MediaController.MediaPlayerControl
        public boolean canSeekBackward() {
            return true;
        }

        @Override // android.widget.MediaController.MediaPlayerControl
        public boolean canSeekForward() {
            return true;
        }

        @Override // android.widget.MediaController.MediaPlayerControl
        public int getBufferPercentage() {
            return 0;
        }

        @Override // android.widget.MediaController.MediaPlayerControl
        public void start() {
            MusicPlayerActivity.this.musicService.mp.start();
        }

        @Override // android.widget.MediaController.MediaPlayerControl
        public void seekTo(int i) {
            MusicPlayerActivity.this.musicService.mp.seekTo(i);
        }

        @Override // android.widget.MediaController.MediaPlayerControl
        public void pause() {
            MusicPlayerActivity.this.musicService.mp.pause();
        }

        @Override // android.widget.MediaController.MediaPlayerControl
        public boolean isPlaying() {
            return MusicPlayerActivity.this.musicService.mp.isPlaying();
        }

        @Override // android.widget.MediaController.MediaPlayerControl
        public int getDuration() {
            return MusicPlayerActivity.this.musicService.mp.getDuration();
        }

        @Override // android.widget.MediaController.MediaPlayerControl
        public int getCurrentPosition() {
            return MusicPlayerActivity.this.musicService.mp.getCurrentPosition();
        }

        @Override // android.widget.MediaController.MediaPlayerControl
        public int getAudioSessionId() {
            return MusicPlayerActivity.this.musicService.mp.getAudioSessionId();
        }
    };

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.i("Fac_MusicPlayerActivity", "Call out mController success");
        this.mController.show();
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("Fac_MusicPlayerActivity", "****MusicActivity,onCreate****");
        setContentView(R.layout.music);
        getWindow().addFlags(128);
        this.mFacFunction_Adapter = FacFunction_Adapter.getInstance(getApplicationContext());
        this.mMenuConfigManager = MenuConfigManager.getInstance(getApplicationContext());
        this.mNavIntegration = NavIntegration.getInstance(getApplicationContext());
        this.mPrevSource = this.mFacFunction_Adapter.getCurrentSource();
        Log.i("Fac_MusicPlayerActivity", "MusicPlayerActivity mPrevSource = " + this.mPrevSource.toString());
        this.image = (ImageView) findViewById(R.id.image);
        this.mBalanceTip = (TextView) findViewById(R.id.nav_tv_shortTip_model);
        this.mTipEnterAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_enter);
        this.image.setImageResource(R.drawable.music_icon);
        this.filePath = getIntent().getStringExtra("musicFileDirctory");
        this.fileName = getIntent().getStringExtra("musicFileName");
        Intent intent = new Intent(this, (Class<?>) MusicService.class);
        this.intent = intent;
        bindService(intent, this.conn, 1);
        this.mFacFunction_Adapter.setOttSrc(6);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Log.i("Fac_MusicPlayerActivity", "****MusicActivity,onResume****");
        MediaController mediaController = new MediaController(this);
        this.mController = mediaController;
        mediaController.setAnchorView(findViewById(R.id.root));
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        String str;
        String str2;
        if (i != 4224 && this.mBalanceTip.getVisibility() == 0) {
            Log.i("Fac_MusicPlayerActivity", "balance mode");
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
        if (i == 23) {
            this.mController.show();
            return true;
        }
        if (i == 4221) {
            showSoundCurveDialog();
            return true;
        }
        if (i == 4357 || i == 4) {
            MusicService musicService = this.musicService;
            if (musicService != null) {
                musicService.stopMusic();
            }
            try {
                Thread.sleep(200L);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.mFacFunction_Adapter.switchSource(this.mPrevSource);
            try {
                Thread.sleep(200L);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            finish();
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
                    this.mNavIntegration.iSetNextEffect(this.mBalanceTip, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.MusicPlayerActivity.2
                        @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                        public void effectChanged(View view, int i2) {
                            MusicPlayerActivity.this.mBalanceTip.setText(i2);
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
                    this.mNavIntegration.iSetNextEffect(this.mBalanceTip, new NavIntegration.iEffectChangeListener() { // from class: com.hisense.uifac.MusicPlayerActivity.3
                        @Override // com.hisense.uifac.nav.NavIntegration.iEffectChangeListener
                        public void effectChanged(View view, int i2) {
                            MusicPlayerActivity.this.mBalanceTip.setText(i2);
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

    private void showSoundCurveDialog() {
        if (this.mSoundCurveDialog != null) {
            this.mSoundCurveDialog = null;
        }
        if (this.mSoundCurveDialog == null) {
            this.mSoundCurveDialog = new SoundCurveDialog(this, MenuDataContainer.getInstance(getApplicationContext()));
        }
        this.mSoundCurveDialog.show();
    }

    class SoundCurveDialog extends Dialog implements DialogInterface.OnKeyListener {
        private MenuDataContainer mDataContainer;
        private MenuSetUpListView mListView;
        private SetConfigListViewAdapter mListViewAdpter_SoundCurve;
        private TextView mTxViewTitle;

        protected SoundCurveDialog(Context context, MenuDataContainer menuDataContainer) {
            super(context, android.R.style.Theme.DeviceDefault.Dialog.NoActionBar);
            this.mListViewAdpter_SoundCurve = new SetConfigListViewAdapter(context);
            this.mDataContainer = menuDataContainer;
        }

        @Override // android.app.Dialog
        protected void onCreate(Bundle bundle) {
            setContentView(R.layout.video_player_3dsetting);
            getWindow().setGravity(49);
            TextView textView = (TextView) findViewById(R.id.title);
            this.mTxViewTitle = textView;
            textView.setText(MusicPlayerActivity.this.getResources().getString(R.string.str_factorymenu_soundcurve));
            this.mListView = (MenuSetUpListView) findViewById(R.id.dmp_3dsettinglistView);
            if (FacPropertyDemo.HIFAC_AUDIO_MORE_CURVE) {
                loadHisFacData_SoundCurveNew();
            } else {
                loadHisFacData_SoundCurve();
            }
            setOnKeyListener(this);
        }

        private void loadHisFacData_SoundCurve() {
            this.mDataContainer.loadHisDesData_AudioCurve();
            setSoundCurveAdapter(this.mDataContainer.getDesDataGroup_SoundCurve());
        }

        private void loadHisFacData_SoundCurveNew() {
            this.mDataContainer.loadHisDesData_AudioCurveNew();
            setSoundCurveAdapter(this.mDataContainer.getDesDataGroup_SoundCurveNew());
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void setSoundCurveAdapter(List<?> list) {
            this.mListView.initData(list, 7, null, 0);
            this.mListViewAdpter_SoundCurve.setmGroup(this.mListView.getCurrentList());
            this.mListView.setAdapter((ListAdapter) this.mListViewAdpter_SoundCurve);
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() != 0) {
                return false;
            }
            RespondedKeyEvent respondedKeyEvent = (RespondedKeyEvent) this.mListView.getSelectedView();
            if (respondedKeyEvent == null) {
                Log.d("Fac_MusicPlayerActivity", "#MusicPlayerActivity# respondedKeyEvent is null");
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

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onStop() {
        unbindService(this.conn);
        Log.d("Fac_MusicPlayerActivity", "#MusicPlayerActivity# onStop");
        super.onStop();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mFacFunction_Adapter.setOttSrc(7);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
    }
}
