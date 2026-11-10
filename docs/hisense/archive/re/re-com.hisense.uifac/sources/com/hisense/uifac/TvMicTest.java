package com.hisense.uifac;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uiadapter.TVComponent;
import com.hisense.uifac.menu.util.MenuConfigManager;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class TvMicTest extends Activity {
    private static FacFunction_Adapter mFacFunctionAdapter = null;
    private static String mRecAudioFileName = "/MicTest/Hisense_MicTest.3gp";
    private Handler MicTypeHandler;
    private int curvolume;
    private Context mMicTestContext;
    private MenuConfigManager mMicTestMenuConfigManager;
    FacPropertyDemo.SourceEnum mPrevSource;
    MicShutDownReceiver mShutDownReceiver;
    private TextView showRecordTime = null;
    private MediaRecorder mRecorder = null;
    private MediaPlayer mPlayer = null;
    private File myRecAudioFile = null;
    private FileInputStream playMedia = null;
    private TimeCount TvMicTestTimer = null;
    private SharedPreferences mSharedPreferences = null;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.mic_test);
        this.mMicTestContext = this;
        this.mMicTestMenuConfigManager = MenuConfigManager.getInstance(this);
        this.showRecordTime = (TextView) findViewById(R.id.showRecordTime);
        this.TvMicTestTimer = new TimeCount(5999L, 1000L);
        this.mShutDownReceiver = new MicShutDownReceiver();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.ACTION_SHUTDOWN");
        IntentFilter intentFilter2 = new IntentFilter("android.intent.action.SCREEN_OFF");
        intentFilter.setPriority(1000);
        registerReceiver(this.mShutDownReceiver, intentFilter);
        registerReceiver(this.mShutDownReceiver, intentFilter2);
        TVComponent.getInstance(this.mMicTestContext);
        FacFunction_Adapter facFunction_Adapter = FacFunction_Adapter.getInstance(this.mMicTestContext);
        mFacFunctionAdapter = facFunction_Adapter;
        this.curvolume = facFunction_Adapter.getVolume();
        Log.i("Fac_TvMicTest", "{dndebug}:OnCreate: curvolume=" + this.curvolume);
        this.mPrevSource = FacFunction_Adapter.getInstance(getApplicationContext()).getCurrentSource();
        mFacFunctionAdapter.pauseTv(true);
        mFacFunctionAdapter.setVolume(100);
        mHandler mhandler = new mHandler();
        this.MicTypeHandler = mhandler;
        mhandler.sendEmptyMessageDelayed(2, 200L);
        this.mSharedPreferences = getSharedPreferences("FacShareP", 0);
    }

    class mHandler extends Handler {
        mHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 2) {
                return;
            }
            Log.d("Fac_TvMicTest", "***{dndebug}handler:START_MIC_TEST");
            TvMicTest.this.startRecording();
            removeMessages(2);
        }
    }

    public class MicShutDownReceiver extends BroadcastReceiver {
        public MicShutDownReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.i("Fac_TvMicTest", "intent.getAction()=" + intent.getAction());
            if (intent.getAction().equals("android.intent.action.ACTION_SHUTDOWN") || intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("Fac_TvMicTest", "{dndebug}:Exit MicTestActivity by soft shut down");
                TvMicTest.this.finish();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exitMicTest() {
        Log.d("Fac_TvMicTest", "***{dndebug}return menumain activity!");
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRecording() {
        stopRecording();
        stopPlaying();
        File file = new File(getFilesDir() + mRecAudioFileName);
        this.myRecAudioFile = file;
        if (!file.getParentFile().exists()) {
            this.myRecAudioFile.getParentFile().mkdirs();
        }
        Log.d("Fac_TvMicTest", "****{dndebug}Get file directory!" + this.myRecAudioFile);
        try {
            MediaRecorder mediaRecorder = new MediaRecorder();
            this.mRecorder = mediaRecorder;
            mediaRecorder.reset();
            this.mRecorder.setAudioSource(1);
            this.mRecorder.setOutputFormat(1);
            this.mRecorder.setAudioEncoder(3);
            this.mRecorder.setOutputFile(this.myRecAudioFile.getAbsolutePath());
            this.mRecorder.prepare();
            this.mRecorder.start();
            this.TvMicTestTimer.start();
            Log.d("Fac_TvMicTest", "****{dndebug}star to record!");
        } catch (Exception e) {
            Log.e("Fac_TvMicTest", "****{dndebug}Record listener is failed!");
            this.mMicTestMenuConfigManager.displayHisToast(this.mMicTestContext.getResources().getString(R.string.str_factorymenu_mictestfail));
            MediaRecorder mediaRecorder2 = this.mRecorder;
            if (mediaRecorder2 != null) {
                mediaRecorder2.reset();
                this.mRecorder.release();
                this.mRecorder = null;
            }
            exitMicTest();
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopRecording() {
        MediaRecorder mediaRecorder = this.mRecorder;
        if (mediaRecorder == null) {
            return;
        }
        mediaRecorder.stop();
        this.mRecorder.release();
        this.mRecorder = null;
        Log.d("Fac_TvMicTest", "****{dndebug}stop to record!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPlaying() {
        stopRecording();
        stopPlaying();
        try {
            try {
                if (this.myRecAudioFile != null) {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(this.myRecAudioFile);
                        this.playMedia = fileInputStream;
                        FileDescriptor fd = fileInputStream.getFD();
                        try {
                            MediaPlayer mediaPlayer = new MediaPlayer();
                            this.mPlayer = mediaPlayer;
                            mediaPlayer.setDataSource(fd);
                            this.mPlayer.prepare();
                            this.mPlayer.start();
                            String string = this.mMicTestContext.getResources().getString(R.string.str_factorymenu_mictestPlaying);
                            this.showRecordTime.setBackgroundColor(-7829368);
                            this.showRecordTime.setTextColor(-16776961);
                            this.showRecordTime.setText(string);
                            Log.d("Fac_TvMicTest", "****{dndebug}start to play the recording file!");
                            this.mSharedPreferences.edit().putBoolean(getResources().getString(R.string.str_test_info_mic_test), true).commit();
                        } catch (Exception e) {
                            this.mMicTestMenuConfigManager.displayHisToast(this.mMicTestContext.getResources().getString(R.string.str_factorymenu_mictestfail));
                            Log.e("Fac_TvMicTest", "****{dndebug}Failed to play the recording file!");
                            stopPlaying();
                            exitMicTest();
                            e.printStackTrace();
                        }
                        FileInputStream fileInputStream2 = this.playMedia;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                    } catch (IOException e2) {
                        Log.e("Fac_TvMicTest", "****{dndebug}Not find the recording file!");
                        exitMicTest();
                        e2.printStackTrace();
                        FileInputStream fileInputStream3 = this.playMedia;
                        if (fileInputStream3 == null) {
                        } else {
                            fileInputStream3.close();
                        }
                    }
                } else {
                    exitMicTest();
                }
            } catch (Throwable th) {
                FileInputStream fileInputStream4 = this.playMedia;
                if (fileInputStream4 != null) {
                    try {
                        fileInputStream4.close();
                    } catch (IOException e3) {
                        Log.e("Fac_TvMicTest", "****{dndebug}Failed to close the recording file!");
                        exitMicTest();
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e4) {
            Log.e("Fac_TvMicTest", "****{dndebug}Failed to close the recording file!");
            exitMicTest();
            e4.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopPlaying() {
        MediaPlayer mediaPlayer = this.mPlayer;
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.stop();
        this.mPlayer.release();
        this.mPlayer = null;
        Log.d("Fac_TvMicTest", "****{dndebug}stop playing media!");
    }

    private class PlayerCompletionListener implements MediaPlayer.OnCompletionListener {
        private PlayerCompletionListener() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            TvMicTest.this.stopRecording();
            TvMicTest.this.stopPlaying();
            TvMicTest.this.exitMicTest();
            Log.d("Fac_TvMicTest", "****{dndebug}Finish the TvMicTest,and restart menumain!");
        }
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            String string = TvMicTest.this.mMicTestContext.getResources().getString(R.string.str_factorymenu_mictestRecording);
            String string2 = TvMicTest.this.mMicTestContext.getResources().getString(R.string.str_factorymenu_mictestRecordTimer);
            TvMicTest.this.showRecordTime.setBackgroundColor(-7829368);
            TvMicTest.this.showRecordTime.setTextColor(-16776961);
            TvMicTest.this.showRecordTime.setText(string + (j / 1000) + string2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            Log.d("Fac_TvMicTest", "****{dndebug}TvRecordCount Timer is off!");
            TvMicTest.this.startPlaying();
            TvMicTest.this.mPlayer.setOnCompletionListener(new PlayerCompletionListener());
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        MediaRecorder mediaRecorder = this.mRecorder;
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            this.mRecorder.release();
            this.mRecorder = null;
        }
        MediaPlayer mediaPlayer = this.mPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mPlayer = null;
        }
        this.TvMicTestTimer.cancel();
        Log.d("Fac_TvMicTest", "****{dndebug}Destroy the MIC test activity!");
        this.MicTypeHandler.removeMessages(2);
        mFacFunctionAdapter.setVolume(this.curvolume);
        FacFunction_Adapter.getInstance(getApplicationContext()).switchSourceByName(this.mPrevSource.name());
        unregisterReceiver(this.mShutDownReceiver);
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 82) {
            return true;
        }
        finish();
        return true;
    }
}
