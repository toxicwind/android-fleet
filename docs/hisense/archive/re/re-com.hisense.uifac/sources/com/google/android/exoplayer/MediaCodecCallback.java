package com.google.android.exoplayer;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.PriorityHandlerThread;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(21)
public class MediaCodecCallback extends MediaCodec.Callback implements Handler.Callback {
    private final Handler bufferHandler;
    private final HandlerThread bufferThread;
    private Lock callbackBufferLock;
    private Lock feedBufferLock;
    private final Handler internalBufferHandler;
    private final HandlerThread internalBufferThread;
    private final boolean isAudio;
    private volatile boolean needLoop;
    private final MediaCodecTrackRenderer trackRenderer;

    public MediaCodecCallback(MediaCodecTrackRenderer mediaCodecTrackRenderer) {
        this.trackRenderer = mediaCodecTrackRenderer;
        this.isAudio = mediaCodecTrackRenderer instanceof MediaCodecAudioTrackRenderer;
        StringBuilder sb = new StringBuilder();
        sb.append(MediaCodecCallback.class.getSimpleName());
        sb.append(this.isAudio ? "[Audio]" : "[Video]");
        sb.append(":Handler");
        PriorityHandlerThread priorityHandlerThread = new PriorityHandlerThread(sb.toString(), -16);
        this.bufferThread = priorityHandlerThread;
        priorityHandlerThread.start();
        Log.i("MediaCodecCallback", "MediaCodecCallback[" + this.isAudio + "] thread start");
        this.bufferHandler = new Handler(this.bufferThread.getLooper(), this);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(MediaCodecCallback.class.getSimpleName());
        sb2.append(this.isAudio ? "[Audio]" : "[Video]");
        sb2.append(":internalHandler");
        PriorityHandlerThread priorityHandlerThread2 = new PriorityHandlerThread(sb2.toString(), -16);
        this.internalBufferThread = priorityHandlerThread2;
        priorityHandlerThread2.start();
        Log.i("MediaCodecCallback", "MediaCodecCallback[" + this.isAudio + "] internal thread start");
        this.internalBufferHandler = new Handler(this.internalBufferThread.getLooper(), this);
        this.callbackBufferLock = new ReentrantLock();
        this.feedBufferLock = new ReentrantLock();
    }

    @Override // android.media.MediaCodec.Callback
    public void onInputBufferAvailable(MediaCodec mediaCodec, int i) {
        MtkDebugLogUtil.log("MediaCodecCallback", "onInputBufferAvailable, isAudio[" + this.isAudio + "], inputBufferId:" + i, 1);
        if (this.callbackBufferLock.tryLock()) {
            this.internalBufferHandler.sendMessage(this.internalBufferHandler.obtainMessage(1, i, 0));
            this.callbackBufferLock.unlock();
            return;
        }
        Log.i("MediaCodecCallback", "ignore this input buffer when flush/release");
    }

    @Override // android.media.MediaCodec.Callback
    public void onOutputBufferAvailable(MediaCodec mediaCodec, int i, MediaCodec.BufferInfo bufferInfo) {
        MtkDebugLogUtil.log("MediaCodecCallback", "onOutputBufferAvailable, isAudio[" + this.isAudio + "], outputBufferId:" + i + ", PTS:" + bufferInfo.presentationTimeUs + ", Size:" + bufferInfo.size + ", flags:" + bufferInfo.flags, 1);
        if (this.callbackBufferLock.tryLock()) {
            this.trackRenderer.onOutputBufferAvailable(i, bufferInfo);
            this.callbackBufferLock.unlock();
        } else {
            Log.i("MediaCodecCallback", "ignore this out buffer when flush/release");
        }
    }

    @Override // android.media.MediaCodec.Callback
    public void onError(MediaCodec mediaCodec, MediaCodec.CodecException codecException) {
        Log.e("MediaCodecCallback", "onError, isAudio[" + this.isAudio + "], CodecException:" + codecException.getMessage() + ", errorCode=" + codecException.getErrorCode());
        this.trackRenderer.onError(codecException, this.isAudio);
    }

    @Override // android.media.MediaCodec.Callback
    public void onOutputFormatChanged(MediaCodec mediaCodec, android.media.MediaFormat mediaFormat) {
        MtkDebugLogUtil.log("MediaCodecCallback", "onOutputFormatChanged, isAudio[" + this.isAudio + "], format:" + mediaFormat.getString("mime"), 1);
        this.trackRenderer.onOutputFormatChangedFromCallback(mediaFormat);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 1) {
            return false;
        }
        this.needLoop = true;
        while (this.needLoop) {
            if (this.feedBufferLock.tryLock()) {
                if (this.trackRenderer.onInputBufferAvailable(message.arg1)) {
                    this.needLoop = false;
                    this.feedBufferLock.unlock();
                } else {
                    this.feedBufferLock.unlock();
                    try {
                        MtkDebugLogUtil.log("MediaCodecCallback", "isAudio[" + this.isAudio + "], do feedInputBuffer(" + message.arg1 + ") return false, sleep(5)", 1);
                        Thread.sleep(5L);
                    } catch (InterruptedException unused) {
                        Log.e("MediaCodecCallback", "MSG_RETRY_FEED_INPUT_BUFFER, catch InterruptedException");
                    }
                }
            } else {
                this.needLoop = false;
            }
        }
        return true;
    }

    public Handler getHandler() {
        return this.bufferHandler;
    }

    public void flush() {
        Log.i("MediaCodecCallback", "MediaCodecCallback, flush, isAudio[" + this.isAudio + "]");
        this.callbackBufferLock.lock();
        this.feedBufferLock.lock();
        this.needLoop = false;
        this.internalBufferHandler.removeCallbacksAndMessages(null);
    }

    public void flushDone() {
        Log.i("MediaCodecCallback", "MediaCodecCallback, flushDone, isAudio[" + this.isAudio + "]");
        this.callbackBufferLock.unlock();
        this.feedBufferLock.unlock();
    }

    public void release() {
        Log.i("MediaCodecCallback", "MediaCodecCallback, isAudio[" + this.isAudio + "] thread release");
        this.callbackBufferLock.lock();
        this.feedBufferLock.lock();
        this.needLoop = false;
        Log.i("MediaCodecCallback", "MediaCodecCallback, isAudio[" + this.isAudio + "], got release lock");
        this.bufferThread.quit();
        this.internalBufferHandler.removeCallbacksAndMessages(null);
        this.internalBufferThread.quit();
    }
}
