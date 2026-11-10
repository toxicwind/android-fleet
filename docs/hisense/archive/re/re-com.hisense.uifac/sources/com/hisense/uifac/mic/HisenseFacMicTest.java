package com.hisense.uifac.mic;

import android.content.Context;
import android.media.AudioManager;
import android.os.SystemProperties;
import android.util.Log;
import com.hisense.tvcm.FacFunction;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.uifac.R;
import com.hisense.uifac.util.IRootMenuUiListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

/* JADX INFO: loaded from: classes.dex */
public class HisenseFacMicTest {
    private static double MAX_MIC_VOLUME80 = 0.001d;
    private static double MIC_SUCCESSFULL_DATA = 12.0d;
    private static double MIN_MIC_VOLUME80 = -50.0d;
    private static HisenseFacMicTest mHisenseFacMicTest;
    private MicFactory mMicFac;
    MicFactory.MicTestResult mMicTestResult;
    private double mOneMicDb;
    private double mTwoMicDb;
    private Context mContext = null;
    private int mPreVolume = 80;
    private FacFunction mFacFunction = null;
    private boolean mIsMicTestRunning = false;
    private boolean isInit = false;

    public static HisenseFacMicTest getInstance() {
        if (mHisenseFacMicTest == null) {
            mHisenseFacMicTest = new HisenseFacMicTest();
        }
        return mHisenseFacMicTest;
    }

    private HisenseFacMicTest() {
        Log.i("HisenseFacMicTest", "HisenseFacMicTest create!");
    }

    public synchronized boolean initMicParam(Context context) {
        Log.i("HisenseFacMicTest", "startTestMic  initMicParam");
        boolean z = false;
        if (context != null && !this.isInit) {
            this.isInit = true;
            this.mContext = context;
            FacFunction_Adapter facFunction_Adapter = FacFunction_Adapter.getInstance(context);
            this.mFacFunction = facFunction_Adapter;
            int currentTvInch = facFunction_Adapter.getCurrentTvInch();
            if (currentTvInch >= 75) {
                Log.i("HisenseFacMicTest", "startTestMic  initMicParam tvInch = " + currentTvInch);
                MIC_SUCCESSFULL_DATA = 12.0d;
                MIN_MIC_VOLUME80 = -50.0d;
            }
            Log.i("HisenseFacMicTest", "initMicParam MIC_SUCCESSFULL_DATA2 = " + MIC_SUCCESSFULL_DATA);
            InputStream inputStreamOpenRawResource = this.mContext.getResources().openRawResource(R.raw.onek_tone_48khz_16bit_10s);
            try {
                byte[] bArr = new byte[inputStreamOpenRawResource.available()];
                inputStreamOpenRawResource.read(bArr);
                inputStreamOpenRawResource.close();
                MicFactory micFactory = new MicFactory(toShortArray(bArr));
                this.mMicFac = micFactory;
                micFactory.setContext(context);
                z = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return z;
        }
        return false;
    }

    public void startTestMic(final IRootMenuUiListener iRootMenuUiListener, final boolean z) {
        Log.i("HisenseFacMicTest", "startTestMic  mIsMicTestRunning =" + this.mIsMicTestRunning);
        if (this.mIsMicTestRunning) {
            return;
        }
        this.mFacFunction.updateFacLogStatus(z ? 8388608 : 4194304);
        this.mIsMicTestRunning = true;
        Log.i("HisenseFacMicTest", "startTestMic 1");
        this.mPreVolume = this.mFacFunction.getVolume();
        int i = SystemProperties.getInt("sys.vt.his.uifac.mic_volume", 0);
        if (i == 0) {
            this.mFacFunction.setVolume(80);
            setVolume(80);
        } else {
            this.mFacFunction.setVolume(i);
            setVolume(i);
        }
        Log.i("HisenseFacMicTest", "startTestMic 2");
        new Thread(new Runnable() { // from class: com.hisense.uifac.mic.HisenseFacMicTest.1
            @Override // java.lang.Runnable
            public void run() {
                String str;
                String str2;
                String str3;
                int i2 = 1;
                if (HisenseFacMicTest.this.mFacFunction.getMicSwitchStatus() != 1) {
                    iRootMenuUiListener.setMicTestResult("MIC SWITCH OFF!");
                    return;
                }
                iRootMenuUiListener.setMicTestResult("MIC TEST START!");
                Log.i("HisenseFacMicTest", "startTestMic 21");
                int micDb = HisenseFacMicTest.this.mFacFunction.getMicDb();
                Log.i("HisenseFacMicTest", "startTestMic type = " + micDb);
                if (micDb == 0) {
                    double unused = HisenseFacMicTest.MIC_SUCCESSFULL_DATA = 12.0d;
                } else if (1 == micDb) {
                    double unused2 = HisenseFacMicTest.MIC_SUCCESSFULL_DATA = 10.0d;
                } else {
                    double unused3 = HisenseFacMicTest.MIC_SUCCESSFULL_DATA = 6.0d;
                }
                HisenseFacMicTest.this.mMicFac.setRecordTime(HisenseFacMicTest.this.mFacFunction.getMicRecordTime());
                Log.i("HisenseFacMicTest", "startTestMic MIC_SUCCESSFULL_DATA = " + HisenseFacMicTest.MIC_SUCCESSFULL_DATA);
                String str4 = "";
                int i3 = 0;
                while (true) {
                    i3 += i2;
                    HisenseFacMicTest.this.testMicForFactory();
                    if (HisenseFacMicTest.this.isAllMicTestResultOK() && HisenseFacMicTest.this.isAllMicDBOK()) {
                        String str5 = str4;
                        double dAbs = Math.abs(HisenseFacMicTest.this.mOneMicDb - HisenseFacMicTest.this.mTwoMicDb);
                        StringBuilder sb = new StringBuilder();
                        str = "MIC2: OK";
                        sb.append("startTestMic testDb = ");
                        sb.append(dAbs);
                        Log.i("HisenseFacMicTest", sb.toString());
                        if (HisenseFacMicTest.MAX_MIC_VOLUME80 < dAbs && dAbs <= HisenseFacMicTest.MIC_SUCCESSFULL_DATA) {
                            String str6 = str5 + "MIC RESULT: OK \n";
                            str3 = HisenseFacMicTest.this.mMicTestResult.mic1DB <= HisenseFacMicTest.MIN_MIC_VOLUME80 ? "MIC1: NG" : "MIC1: OK";
                            String str7 = HisenseFacMicTest.this.mMicTestResult.mic2DB > HisenseFacMicTest.MIN_MIC_VOLUME80 ? str : "MIC2: NG";
                            String str8 = str6 + str3 + "        " + str7 + "\n";
                            String strReplace = str3 + "        " + str7;
                            IRootMenuUiListener iRootMenuUiListener2 = iRootMenuUiListener;
                            if (z) {
                                strReplace = strReplace.replace("MIC", "ZDMIC");
                            }
                            iRootMenuUiListener2.setMicTestResultToLog(strReplace, z);
                            String str9 = str8 + "MIC1 : " + HisenseFacMicTest.this.mOneMicDb + "\nMIC2 : " + HisenseFacMicTest.this.mTwoMicDb + "\n";
                            if (z) {
                                HisenseFacMicTest.this.mFacFunction.setLogToFile("ZDMIC Result: \n" + str9);
                                HisenseFacMicTest.this.mFacFunction.setZDMicResult(1);
                                iRootMenuUiListener.notifyZDMicTestResult(true);
                            } else {
                                HisenseFacMicTest.this.mFacFunction.setLogToFile("MIC Result: \n" + str9);
                                HisenseFacMicTest.this.mFacFunction.setMicTestResult(1);
                            }
                            iRootMenuUiListener.setMicTestResult(str9);
                            HisenseFacMicTest.this.mIsMicTestRunning = false;
                            HisenseFacMicTest.this.mFacFunction.setVolume(HisenseFacMicTest.this.mPreVolume);
                            HisenseFacMicTest hisenseFacMicTest = HisenseFacMicTest.this;
                            hisenseFacMicTest.setVolume(hisenseFacMicTest.mPreVolume);
                            return;
                        }
                        str2 = str5;
                    } else {
                        str = "MIC2: OK";
                        str2 = str4;
                    }
                    HisenseFacMicTest.this.sleep(500L);
                    if (3 <= i3) {
                        String str10 = str2 + "MIC RESULT: NG \n";
                        str3 = HisenseFacMicTest.this.mMicTestResult.mic1DB <= HisenseFacMicTest.MIN_MIC_VOLUME80 ? "MIC1: NG" : "MIC1: OK";
                        String str11 = HisenseFacMicTest.this.mMicTestResult.mic2DB > HisenseFacMicTest.MIN_MIC_VOLUME80 ? str : "MIC2: NG";
                        String str12 = str10 + str3 + "        " + str11 + "\n";
                        String strReplace2 = str3 + "        " + str11;
                        IRootMenuUiListener iRootMenuUiListener3 = iRootMenuUiListener;
                        if (z) {
                            strReplace2 = strReplace2.replace("MIC", "ZDMIC");
                        }
                        iRootMenuUiListener3.setMicTestResultToLog(strReplace2, z);
                        String str13 = str12 + "MIC1 DB :" + HisenseFacMicTest.this.mOneMicDb + "\nMIC2 DB : " + HisenseFacMicTest.this.mTwoMicDb + "\n";
                        if (z) {
                            HisenseFacMicTest.this.mFacFunction.setLogToFile("ZDMIC Result: \n" + str13);
                            HisenseFacMicTest.this.mFacFunction.setZDMicResult(44);
                        } else {
                            HisenseFacMicTest.this.mFacFunction.setLogToFile("MIC Result: \n" + str13);
                            HisenseFacMicTest.this.mFacFunction.setMicTestResult(44);
                        }
                        iRootMenuUiListener.setMicTestResult(str13);
                        HisenseFacMicTest.this.mIsMicTestRunning = false;
                        HisenseFacMicTest.this.mFacFunction.setVolume(HisenseFacMicTest.this.mPreVolume);
                        HisenseFacMicTest hisenseFacMicTest2 = HisenseFacMicTest.this;
                        hisenseFacMicTest2.setVolume(hisenseFacMicTest2.mPreVolume);
                        return;
                    }
                    str4 = str2;
                    i2 = 1;
                }
            }
        }).start();
        Log.i("HisenseFacMicTest", "startTestMic 3");
    }

    public double startMicTightnessTest(int i) {
        double d;
        Log.i("HisenseFacMicTest", "startMicTightnessTest  mIsMicTestRunning = " + this.mIsMicTestRunning);
        Log.i("HisenseFacMicTest", "startMicTightnessTest  index = " + i);
        if (this.mIsMicTestRunning || this.mFacFunction.getMicSwitchStatus() != 1) {
            return 0.0d;
        }
        this.mIsMicTestRunning = true;
        Log.i("HisenseFacMicTest", "startMicTightnessTest 2");
        testMicByIndex(i == 1 ? 1 : 0);
        if (i == 1) {
            d = this.mTwoMicDb;
        } else {
            d = this.mOneMicDb;
        }
        Log.i("HisenseFacMicTest", "startMicTightnessTest result = " + d);
        this.mIsMicTestRunning = false;
        return d;
    }

    public double getMicStatus(int i) {
        double d;
        if (this.mIsMicTestRunning || this.mFacFunction.getMicSwitchStatus() != 1) {
            return 0.0d;
        }
        try {
            if (1 != this.mMicFac.getMicInputStatus(i, this.mContext.openFileOutput(String.format("hisense_getMicStatus_" + i + ".pcm", new Object[0]), 0).getChannel())) {
                return 0.0d;
            }
            updateMicTestResult(i);
            if (i == 1) {
                d = this.mTwoMicDb;
            } else {
                d = this.mOneMicDb;
            }
            return d;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    public double getMicOneResult() {
        return this.mOneMicDb;
    }

    public double getMicTwoResult() {
        return this.mTwoMicDb;
    }

    public void setMicDb(int i) {
        MIC_SUCCESSFULL_DATA = Double.valueOf(i).doubleValue();
        Log.i("HisenseFacMicTest", "setMicDb db = " + MIC_SUCCESSFULL_DATA);
    }

    public double getMicDb() {
        return MIC_SUCCESSFULL_DATA;
    }

    private static short[] toShortArray(byte[] bArr) {
        ShortBuffer shortBufferAsShortBuffer = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer();
        short[] sArr = new short[shortBufferAsShortBuffer.limit()];
        shortBufferAsShortBuffer.get(sArr);
        return sArr;
    }

    private boolean testMicByIndex(int i) {
        try {
            int micStatus = this.mMicFac.getMicStatus(i, this.mContext.openFileOutput("getMicStatus01ch_16K_16bit" + i + ".pcm", 0).getChannel());
            updateMicTestResult(i);
            return micStatus == 0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sleep(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVolume(int i) {
        ((AudioManager) this.mContext.getSystemService("audio")).setStreamVolume(3, i, 4);
    }

    private void updateMicTestResult(int i) {
        Log.i("HisenseFacMicTest", "updateMicTestResult index = " + i);
        Log.i("HisenseFacMicTest", "updateMicTestResult freq = " + this.mMicFac.getFreq());
        double db = this.mMicFac.getDB();
        Log.i("HisenseFacMicTest", "updateMicTestResult db = " + db);
        if (i == 0) {
            this.mOneMicDb = db;
        }
        if (i == 1) {
            this.mTwoMicDb = db;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean testMicForFactory() {
        try {
            MicFactory.MicTestResult micStatus = this.mMicFac.getMicStatus(this.mContext.openFileOutput("getMicStatus01ch_16K_16bit.pcm", 0).getChannel());
            this.mFacFunction.sendCmd("sync");
            updateMicTestResult(micStatus);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void updateMicTestResult(MicFactory.MicTestResult micTestResult) {
        this.mMicTestResult = micTestResult;
        double d = micTestResult.mic1Freq;
        this.mOneMicDb = micTestResult.mic1DB;
        double d2 = micTestResult.mic2Freq;
        this.mTwoMicDb = micTestResult.mic2DB;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAllMicTestResultOK() {
        MicFactory.MicTestResult micTestResult = this.mMicTestResult;
        return micTestResult.mic1Result == 0 && micTestResult.mic2Result == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAllMicDBOK() {
        double d = this.mMicTestResult.mic1DB;
        boolean z = 0.0d > d && d > MIN_MIC_VOLUME80;
        double d2 = this.mMicTestResult.mic2DB;
        return z && ((0.0d > d2 ? 1 : (0.0d == d2 ? 0 : -1)) > 0 && (d2 > MIN_MIC_VOLUME80 ? 1 : (d2 == MIN_MIC_VOLUME80 ? 0 : -1)) > 0);
    }
}
