package com.hisense.uifac.mic;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.util.Log;
import com.hisense.uifac.menu.util.MenuConfigManager;
import com.hisense.uifac.util.USBUtils;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public class MicFactory {
    private short[] mRefData;
    private final int bufferSize = AudioRecord.getMinBufferSize(16000, 12, 2) * 2;
    private int HIFAC_MIC_RECORD_TIME = 6;
    private boolean isRunning = false;
    private double mFreq = 0.0d;
    private double mVolumeDB = 0.0d;
    private AudioRecord mAudioRecord = null;
    private short[] mRecordResult = null;
    private Context mContext = null;

    public static class MicTestResult {
        public double mic1DB;
        public double mic1Freq;
        public int mic1Result;
        public double mic2DB;
        public double mic2Freq;
        public int mic2Result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static short[] toShortArray(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        ShortBuffer shortBufferAsShortBuffer = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer();
        short[] sArr = new short[shortBufferAsShortBuffer.limit()];
        shortBufferAsShortBuffer.get(sArr);
        return sArr;
    }

    private static void dumpPCM(short[] sArr, FileChannel fileChannel) throws IOException {
        if (sArr == null || fileChannel == null) {
            return;
        }
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(sArr.length * 2).order(ByteOrder.LITTLE_ENDIAN);
        byteBufferOrder.asShortBuffer().put(sArr);
        fileChannel.write(byteBufferOrder);
        fileChannel.close();
    }

    private void dumpPCMToUsb(short[] sArr, int i) throws IOException {
        if (sArr == null) {
            return;
        }
        List<USBUtils.UsbPathAndLabel> usbPath = USBUtils.getUsbPath(this.mContext);
        if (usbPath.size() < 1) {
            Log.e("HisMicFactory", "no usb detected");
            return;
        }
        writeToUSB(sArr, usbPath.get(0).getInternalPath() + "/" + MenuConfigManager.getInstance(this.mContext).getWidevineLastName() + ".pcm");
    }

    private void writeToUSB(short[] sArr, String str) {
        Log.e("HisMicFactory", "write to usb path: " + str);
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            FileChannel channel = fileOutputStream.getChannel();
            ByteBuffer byteBufferOrder = ByteBuffer.allocate(sArr.length * 2).order(ByteOrder.LITTLE_ENDIAN);
            byteBufferOrder.asShortBuffer().put(sArr);
            channel.write(byteBufferOrder);
            channel.close();
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            Log.e("HisMicFactory", "Write to usb exception:" + e);
        }
    }

    private class MicCaptureThread extends Thread {
        private int mMicIndex;
        private int mRecordSeconds;

        public MicCaptureThread(int i) {
            this.mRecordSeconds = 0;
            this.mRecordSeconds = i;
            this.mMicIndex = -1;
        }

        private class HighPassFilter16 {
            private int mChannel;
            private int mCoef;
            private int mProcChannel;
            private int mStage;
            private int[][] preXX = (int[][]) Array.newInstance((Class<?>) int.class, 8, 2);
            private int[][] preYY = (int[][]) Array.newInstance((Class<?>) int.class, 8, 2);

            public HighPassFilter16(MicCaptureThread micCaptureThread, int i, int i2, int i3) {
                this.mChannel = i;
                this.mCoef = i2;
                this.mStage = i3;
                this.mProcChannel = i;
            }

            public boolean operate(short[] sArr) {
                int i;
                if (sArr == null) {
                    Log.e("HisMicFactory", "[HighPassFilter16] data is null pointer!!");
                    return false;
                }
                int i2 = this.mChannel;
                if (i2 > 8 || (i = this.mCoef) > 9 || i <= -3 || this.mStage > 2) {
                    Log.e("HisMicFactory", "[HighPassFilter16] param not support!!");
                    return false;
                }
                int length = sArr.length / i2;
                Log.i("HisMicFactory", String.format("[HighPassFilter16] mChannel = %d mCoef = %d mStage = %d frame = %d", Integer.valueOf(i2), Integer.valueOf(this.mCoef), Integer.valueOf(this.mStage), Integer.valueOf(length)));
                for (int i3 = 0; i3 < length; i3++) {
                    int i4 = 0;
                    while (true) {
                        int i5 = this.mChannel;
                        if (i4 < i5) {
                            if (i4 < this.mProcChannel) {
                                int i6 = sArr[(i5 * i3) + i4] << 14;
                                int i7 = 0;
                                while (i7 < this.mStage) {
                                    int[][] iArr = this.preXX;
                                    int i8 = i6 - iArr[i4][i7];
                                    int[][] iArr2 = this.preYY;
                                    int i9 = (i8 + iArr2[i4][i7]) - (iArr2[i4][i7] >> (this.mCoef + 3));
                                    iArr[i4][i7] = i6;
                                    iArr2[i4][i7] = i9;
                                    i7++;
                                    i6 = i9;
                                }
                                int i10 = i6 >> 14;
                                if (i10 > 32767) {
                                    i10 = 32767;
                                } else if (i10 < -32768) {
                                    i10 = -32768;
                                }
                                sArr[(this.mChannel * i3) + i4] = (short) i10;
                            }
                            i4++;
                        }
                    }
                }
                return true;
            }
        }

        public MicCaptureThread(int i, int i2) {
            this.mRecordSeconds = 0;
            this.mRecordSeconds = i;
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 > 1) {
                i2 = 1;
            }
            this.mMicIndex = i2;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int i = this.mRecordSeconds * 2 * 16000 * 2;
            byte[] bArr = new byte[256000 + i];
            if (MicFactory.this.mAudioRecord == null) {
                return;
            }
            MicFactory.this.mAudioRecord.startRecording();
            new Timer(true).schedule(new TimerTask() { // from class: com.hisense.uifac.mic.MicFactory.MicCaptureThread.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    MicFactory.this.isRunning = false;
                }
            }, this.mRecordSeconds * 1000);
            Log.d("HisMicFactory", "Start Recording : " + i);
            int i2 = 0;
            while (MicFactory.this.isRunning) {
                i2 += MicFactory.this.mAudioRecord.read(bArr, i2, MicFactory.this.bufferSize);
            }
            Log.i("HisMicFactory", "offset : " + i2);
            MicFactory.this.mAudioRecord.stop();
            MicFactory.this.mAudioRecord.release();
            MicFactory.this.mAudioRecord = null;
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i2 > 65536 ? i2 - MtkTvScanDvbsBase.SB_DVBS_CONFIG_BGM_SCAN : 0, i2);
            Log.d("HisMicFactory", "MicIndex dump len : " + bArrCopyOfRange.length);
            short[] shortArray = MicFactory.toShortArray(bArrCopyOfRange);
            Log.d("HisMicFactory", "MicIndex dumpShortArr len : " + shortArray.length);
            new HighPassFilter16(this, 2, 5, 2).operate(shortArray);
            Log.d("HisMicFactory", "MicIndex : " + this.mMicIndex);
            int i3 = this.mMicIndex;
            if (i3 == 0 || i3 == 1) {
                MicFactory.this.mRecordResult = new short[shortArray.length / 2];
                Log.i("HisMicFactory", "mRecordResult.length : " + MicFactory.this.mRecordResult.length);
                for (int i4 = 0; i4 < MicFactory.this.mRecordResult.length; i4++) {
                    MicFactory.this.mRecordResult[i4] = shortArray[(i4 * 2) + this.mMicIndex];
                }
                return;
            }
            MicFactory.this.mRecordResult = shortArray;
        }
    }

    private class TonePlayThread extends Thread {
        private TonePlayThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (MicFactory.this.mRefData == null) {
                Log.e("HisMicFactory", "mRefData is null!!! ");
                return;
            }
            AudioTrack audioTrackBuild = new AudioTrack.Builder().setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(2).build()).setAudioFormat(new AudioFormat.Builder().setEncoding(2).setSampleRate(48000).setChannelMask(12).build()).setBufferSizeInBytes(MicFactory.this.bufferSize).build();
            audioTrackBuild.play();
            Log.i("HisMicFactory", "startPlay1KTone");
            int length = MicFactory.this.mRefData.length;
            int i = 0;
            while (length > 64) {
                int iWrite = audioTrackBuild.write(MicFactory.this.mRefData, i, 64);
                i += iWrite;
                length -= iWrite;
            }
            MicFactory.this.isRunning = false;
            audioTrackBuild.stop();
            audioTrackBuild.release();
            Log.i("HisMicFactory", "startPlay1KTone end...");
        }
    }

    public MicFactory(short[] sArr) {
        this.mRefData = sArr;
    }

    public double getFreq() {
        return this.mFreq;
    }

    public double getDB() {
        return this.mVolumeDB;
    }

    public int getMicInputStatus(int i) {
        startMic();
        if (this.mAudioRecord == null) {
            Log.e("HisMicFactory", String.format("Open MIC %d Fail!", Integer.valueOf(i)));
            return 0;
        }
        MicCaptureThread micCaptureThread = new MicCaptureThread(2, i);
        this.isRunning = true;
        micCaptureThread.start();
        try {
            micCaptureThread.join();
            if (this.mRecordResult != null) {
                SimpleFFT simpleFFT = new SimpleFFT(this.mRecordResult, this.mRefData, 16000, 1);
                this.mFreq = simpleFFT.getFrequency();
                this.mVolumeDB = simpleFFT.getDB();
                for (short s : this.mRecordResult) {
                    if (s != 0) {
                        Log.d("HisMicFactory", String.format("MIC %d OK!", Integer.valueOf(i)));
                        return 1;
                    }
                }
                Log.e("HisMicFactory", String.format("MIC %d Fail!", Integer.valueOf(i)));
                return 0;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("HisMicFactory", String.format("MIC %d Fail!", Integer.valueOf(i)));
        return 0;
    }

    public int getMicInputStatus(int i, FileChannel fileChannel) {
        Log.w("HisMicFactory", "getMicInputStatus micIndex = " + i);
        int micInputStatus = getMicInputStatus(i);
        try {
            dumpPCM(this.mRecordResult, fileChannel);
        } catch (IOException unused) {
            Log.w("HisMicFactory", "dump fail!!");
        }
        return micInputStatus;
    }

    public void startMic() {
        if (this.mAudioRecord == null) {
            this.mAudioRecord = new AudioRecord(1, 16000, 12, 2, this.bufferSize);
        }
    }

    public int getMicStatus(int i) {
        startMic();
        if (this.mAudioRecord == null) {
            Log.e("HisMicFactory", String.format("Open MIC %d Fail!", Integer.valueOf(i)));
            return 2;
        }
        MicCaptureThread micCaptureThread = new MicCaptureThread(2, i);
        TonePlayThread tonePlayThread = new TonePlayThread();
        this.isRunning = true;
        tonePlayThread.start();
        micCaptureThread.start();
        try {
            tonePlayThread.join();
            micCaptureThread.join();
            SimpleFFT simpleFFT = new SimpleFFT(this.mRecordResult, this.mRefData, 16000, 1);
            this.mFreq = simpleFFT.getFrequency();
            this.mVolumeDB = simpleFFT.getDB();
            return 0;
        } catch (InterruptedException unused) {
            return 2;
        }
    }

    public int getMicStatus(int i, FileChannel fileChannel) {
        int micStatus = getMicStatus(i);
        try {
            dumpPCM(this.mRecordResult, fileChannel);
            dumpPCMToUsb(this.mRecordResult, i);
        } catch (IOException unused) {
            Log.w("HisMicFactory", "dump fail!!");
        }
        return micStatus;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public MicTestResult getMicStatus() {
        MicTestResult micTestResult = new MicTestResult();
        startMic();
        if (this.mAudioRecord == null) {
            Log.e("HisMicFactory", String.format("Open MIC Fail!", new Object[0]));
            micTestResult.mic1Result = 2;
            micTestResult.mic2Result = 2;
            return micTestResult;
        }
        MicCaptureThread micCaptureThread = new MicCaptureThread(this.HIFAC_MIC_RECORD_TIME);
        TonePlayThread tonePlayThread = new TonePlayThread();
        int i = 1;
        this.isRunning = true;
        tonePlayThread.start();
        micCaptureThread.start();
        try {
            tonePlayThread.join();
            micCaptureThread.join();
            int length = this.mRecordResult.length / 2;
            short[] sArr = new short[length];
            for (int i2 = 0; i2 < length; i2++) {
                sArr[i2] = this.mRecordResult[i2 * 2];
            }
            SimpleFFT simpleFFT = new SimpleFFT(sArr, this.mRefData, 16000, 1);
            micTestResult.mic1Freq = simpleFFT.getFrequency();
            micTestResult.mic1DB = simpleFFT.getOneKiloHzDB();
            micTestResult.mic1Result = (micTestResult.mic1Freq < 950.0d || micTestResult.mic1Freq > 1050.0d) ? 1 : 0;
            Log.e("HisMicFactory", "getMicStatus mic1Freq " + micTestResult.mic1Freq);
            micTestResult.mic1Result = 0;
            int length2 = this.mRecordResult.length / 2;
            short[] sArr2 = new short[length2];
            for (int i3 = 0; i3 < length2; i3++) {
                sArr2[i3] = this.mRecordResult[(i3 * 2) + 1];
            }
            SimpleFFT simpleFFT2 = new SimpleFFT(sArr2, this.mRefData, 16000, 1);
            micTestResult.mic2Freq = simpleFFT2.getFrequency();
            micTestResult.mic2DB = simpleFFT2.getOneKiloHzDB();
            Log.e("HisMicFactory", "getMicStatus mic2Freq " + micTestResult.mic1Freq);
            if (micTestResult.mic2Freq >= 950.0d && micTestResult.mic2Freq <= 1050.0d) {
                i = 0;
            }
            micTestResult.mic2Result = i;
            micTestResult.mic2Result = 0;
        } catch (InterruptedException unused) {
            micTestResult.mic1Result = 2;
            micTestResult.mic2Result = 2;
        }
        return micTestResult;
    }

    public MicTestResult getMicStatus(FileChannel fileChannel) {
        MicTestResult micStatus = getMicStatus();
        try {
            dumpPCM(this.mRecordResult, fileChannel);
            dumpPCMToUsb(this.mRecordResult, 6);
        } catch (IOException unused) {
            Log.w("HisMicFactory", "dump fail!!");
        }
        return micStatus;
    }

    public void setRecordTime(int i) {
        Log.i("HisMicFactory", "setRecordTime time = " + i);
        if (i == 0) {
            this.HIFAC_MIC_RECORD_TIME = 4;
        }
        if (1 == i) {
            this.HIFAC_MIC_RECORD_TIME = 6;
        }
        if (2 == i) {
            this.HIFAC_MIC_RECORD_TIME = 8;
        }
    }
}
