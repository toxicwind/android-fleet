package com.hisense.uifac;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import com.hisense.tvcm.FacFunction_Adapter;
import java.io.IOException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class MusicService extends Service {
    private String filePath;
    public MediaPlayer mp;
    private FacFunction_Adapter mFacFunction_Adapter = null;
    private MediaPlayer.OnCompletionListener listener = new MediaPlayer.OnCompletionListener() { // from class: com.hisense.uifac.MusicService.1
        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            MusicService.this.startPlay();
        }
    };
    private MusicBinder binder = new MusicBinder();

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    public void setMusicPath(String str) {
        FacFunction_Adapter facFunction_Adapter = FacFunction_Adapter.getInstance(getApplicationContext());
        this.mFacFunction_Adapter = facFunction_Adapter;
        facFunction_Adapter.getCurrentSource();
        MediaPlayer mediaPlayer = this.mp;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        } else {
            this.mp = new MediaPlayer();
        }
        this.mp.setOnCompletionListener(this.listener);
        this.filePath = str;
        prepareMusic();
    }

    private void prepareMusic() {
        startPlay();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPlay() {
        try {
            Method declaredMethod = this.mp.getClass().getDeclaredMethod("setPlayerType", Integer.TYPE);
            Log.d("Fac_MusicService", "mediaplayer set type 6");
            declaredMethod.invoke(this.mp, 6);
        } catch (Exception e) {
            Log.e("Fac_MusicService", "reflect setPlayerType exception" + e);
        }
        this.mp.reset();
        try {
            this.mp.setDataSource(this.filePath);
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
        } catch (IllegalStateException e4) {
            e4.printStackTrace();
        } catch (SecurityException e5) {
            e5.printStackTrace();
        }
        try {
            this.mp.prepare();
        } catch (IOException e6) {
            e6.printStackTrace();
        } catch (IllegalStateException e7) {
            e7.printStackTrace();
        }
        this.mp.start();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.binder;
    }

    public class MusicBinder extends Binder {
        public MusicBinder() {
        }

        public MusicService getService() {
            return MusicService.this;
        }
    }

    public void stopMusic() {
        Log.e("Fac_MusicService", "MusicService stopMusic");
        MediaPlayer mediaPlayer = this.mp;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mp.release();
            this.mp = null;
            Log.e("Fac_MusicService", "MusicService stopMusic end");
        }
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Log.e("Fac_MusicService", "MusicService onUnbind");
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }
}
