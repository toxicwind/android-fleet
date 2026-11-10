package com.hisense.uifac;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.tvcm.FacPropertyDemo;

/* JADX INFO: loaded from: classes.dex */
public class FacPictureWithAudio extends Activity {
    private Bundle mBundle;
    private ImageView mImageView;
    private Handler mPlayerHandler;
    private FacPropertyDemo.SourceEnum mPrevSource;
    private MediaPlayer mPlayer = null;
    private int mType = 0;
    private FacFunction_Adapter mFacFunction_Adapter = null;
    private HandlerThread mHandlerThread = new HandlerThread("FacPictureWithAudioHandlerThread");
    private final int[] mOnKeyDown = {0, 0};
    private final int[] mPictureResource = {R.drawable.picture_audio_halfcolor, R.drawable.picture_audio_50_balack, R.drawable.picture_audio_4k_test_fhd, R.drawable.picture_audio_red, R.drawable.picture_audio_green, R.drawable.picture_audio_blue, R.drawable.picture_audio_white, R.drawable.picture_audio_black};

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("Fac_FacPictureWithAudio", " onCreate");
        Bundle extras = getIntent().getExtras();
        this.mBundle = extras;
        if (extras == null) {
            this.mType = 0;
        } else {
            this.mType = extras.getInt("FAC_PICTURE_WITH_AUDIO_SA");
        }
        Log.i("Fac_FacPictureWithAudio", " mType = " + this.mType);
        setContentView(R.layout.sa_picture_with_audio);
        this.mImageView = (ImageView) findViewById(R.id.sa_picture_with_audio);
        getWindow().addFlags(128);
        changeImage(this.mType);
        FacFunction_Adapter facFunction_Adapter = FacFunction_Adapter.getInstance(getApplicationContext());
        this.mFacFunction_Adapter = facFunction_Adapter;
        this.mPrevSource = facFunction_Adapter.getCurrentSource();
        this.mPlayer = new MediaPlayer();
        initPlayre();
        this.mHandlerThread.start();
        this.mPlayerHandler = new Handler(this.mHandlerThread.getLooper());
        operateMediaPlayerBackground(2);
        this.mFacFunction_Adapter.setOttSrc(6);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        operateMediaPlayerBackground(1);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        int[] iArr = this.mOnKeyDown;
        iArr[0] = iArr[1];
        iArr[1] = i;
        if (i == 4 || i == 4357) {
            exitToFacRootMenu();
        } else {
            switch (i) {
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                    if (iArr[0] == 4225) {
                        changeImage(i);
                        break;
                    }
                default:
                    return true;
            }
        }
        return true;
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        Intent intent = new Intent();
        intent.putExtra("FacPictureWithAudio", "testok");
        setResult(1, intent);
    }

    private void initPlayre() {
        this.mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.hisense.uifac.FacPictureWithAudio.1
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                Log.i("Fac_FacPictureWithAudio", "onPrepared");
                FacPictureWithAudio.this.operateMediaPlayerBackground(0);
            }
        });
        this.mPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener(this) { // from class: com.hisense.uifac.FacPictureWithAudio.2
            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                Log.i("Fac_FacPictureWithAudio", "SeekComplete");
            }
        });
        this.mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.hisense.uifac.FacPictureWithAudio.3
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                Log.i("Fac_FacPictureWithAudio", "onCompletion");
                FacPictureWithAudio.this.operateMediaPlayerBackground(1);
            }
        });
        this.mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener(this) { // from class: com.hisense.uifac.FacPictureWithAudio.4
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                Log.i("Fac_FacPictureWithAudio", "onError--arg1 = " + i);
                Log.i("Fac_FacPictureWithAudio", "onError--arg2 = " + i2);
                return true;
            }
        });
        this.mPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener(this) { // from class: com.hisense.uifac.FacPictureWithAudio.5
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                Log.i("Fac_FacPictureWithAudio", "onInfo--arg1 = " + i);
                Log.i("Fac_FacPictureWithAudio", "onInfo--arg2 = " + i2);
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void operateMediaPlayerBackground(final int i) {
        this.mPlayerHandler.post(new Runnable() { // from class: com.hisense.uifac.FacPictureWithAudio.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (FacPictureWithAudio.this.mPlayer != null) {
                        int i2 = i;
                        if (i2 == 0) {
                            FacPictureWithAudio.this.mPlayer.start();
                        } else if (i2 == 1) {
                            FacPictureWithAudio.this.prepare();
                        } else if (i2 == 2) {
                            FacPictureWithAudio.this.mFacFunction_Adapter.switchSource(FacPropertyDemo.SourceEnum.DMP);
                        } else if (i2 != 3) {
                            if (i2 == 4 && FacPictureWithAudio.this.mPlayer != null) {
                                if (FacPictureWithAudio.this.mPlayer.isPlaying()) {
                                    Log.d("Fac_FacPictureWithAudio", "player pause ");
                                    FacPictureWithAudio.this.mPlayer.pause();
                                } else {
                                    Log.d("Fac_FacPictureWithAudio", "player continue");
                                    FacPictureWithAudio.this.mPlayer.start();
                                }
                            }
                        } else {
                            FacPictureWithAudio.this.mHandlerThread.quit();
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

    /* JADX INFO: Access modifiers changed from: private */
    public void prepare() {
        Log.d("Fac_FacPictureWithAudio", " perpare");
        try {
            this.mPlayer.reset();
            this.mPlayer.setLooping(true);
            AssetFileDescriptor assetFileDescriptorOpenFd = getAssets().openFd("sa_picture_with_audio_400and1000hz.mp3");
            this.mPlayer.setDataSource(assetFileDescriptorOpenFd.getFileDescriptor(), assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getLength());
            this.mPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exitToFacRootMenu() {
        try {
            Log.d("Fac_FacPictureWithAudio", " exitToFacRootMenu isPlaying = " + this.mPlayer.isPlaying());
            for (MediaPlayer.TrackInfo trackInfo : this.mPlayer.getTrackInfo()) {
                Log.d("Fac_FacPictureWithAudio", " exitToFacRootMenu infos = " + trackInfo.toString());
            }
            this.mPlayer.stop();
            Thread.sleep(100L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.mPlayer.release();
            Thread.sleep(100L);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.mFacFunction_Adapter.switchSource(this.mPrevSource);
        try {
            Thread.sleep(100L);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        finish();
    }

    private void changeImage(int i) {
        int i2 = i - 8;
        if (i2 > 7 || i2 < 0) {
            return;
        }
        this.mImageView.setImageResource(this.mPictureResource[i2]);
    }
}
