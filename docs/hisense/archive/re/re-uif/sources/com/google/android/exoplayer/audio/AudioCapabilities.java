package com.google.android.exoplayer.audio;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.util.Log;
import com.google.android.exoplayer.C;
import com.google.android.exoplayer.MediaFormat;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(21)
public final class AudioCapabilities {
    private final int maxChannelCount;
    private final int[] supportedEncodings;
    public static final AudioCapabilities DEFAULT_AUDIO_CAPABILITIES = new AudioCapabilities(new int[]{2}, 2);
    private static boolean enablePassThroughMode = true;
    private static boolean enableDTSPassThroughMode = true;
    private static boolean enableAACPassThroughMode = true;
    private static boolean isEnablePassThrough = true;

    public static AudioCapabilities getCapabilities(Context context) {
        if (isEnablePassThrough && (enablePassThroughMode || enableDTSPassThroughMode || enableAACPassThroughMode)) {
            ArrayList arrayList = new ArrayList();
            if (enablePassThroughMode) {
                arrayList.add(6);
                arrayList.add(5);
                arrayList.add(2);
            }
            if (enableDTSPassThroughMode) {
                arrayList.add(7);
                arrayList.add(8);
            }
            arrayList.add(Integer.valueOf(C.ENCODING_AC4));
            if (enableAACPassThroughMode) {
                arrayList.add(10);
                arrayList.add(11);
                arrayList.add(12);
            }
            arrayList.add(18);
            arrayList.add(19);
            Integer[] numArr = (Integer[]) arrayList.toArray(new Integer[arrayList.size()]);
            int length = numArr.length;
            int[] iArr = new int[length];
            for (int i = 0; i < numArr.length; i++) {
                iArr[i] = numArr[i].intValue();
            }
            return new AudioCapabilities(iArr, length);
        }
        return getCapabilities(context.registerReceiver(null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG")));
    }

    @SuppressLint({"InlinedApi"})
    static AudioCapabilities getCapabilities(Intent intent) {
        if (intent == null || intent.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0) == 0) {
            return DEFAULT_AUDIO_CAPABILITIES;
        }
        return new AudioCapabilities(intent.getIntArrayExtra("android.media.extra.ENCODINGS"), intent.getIntExtra("android.media.extra.MAX_CHANNEL_COUNT", 0));
    }

    AudioCapabilities(int[] iArr, int i) {
        if (iArr != null) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
            this.supportedEncodings = iArrCopyOf;
            Arrays.sort(iArrCopyOf);
        } else {
            this.supportedEncodings = new int[0];
        }
        this.maxChannelCount = i;
    }

    public boolean supportsEncoding(int i) {
        return Arrays.binarySearch(this.supportedEncodings, i) >= 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioCapabilities)) {
            return false;
        }
        AudioCapabilities audioCapabilities = (AudioCapabilities) obj;
        return Arrays.equals(this.supportedEncodings, audioCapabilities.supportedEncodings) && this.maxChannelCount == audioCapabilities.maxChannelCount;
    }

    public int hashCode() {
        return this.maxChannelCount + (Arrays.hashCode(this.supportedEncodings) * 31);
    }

    public String toString() {
        return "AudioCapabilities[maxChannelCount=" + this.maxChannelCount + ", supportedEncodings=" + Arrays.toString(this.supportedEncodings) + "]";
    }

    public static boolean isDirectPlaybackSupported(MediaFormat mediaFormat) {
        int i;
        int iMapChannelCountToMask;
        if (mediaFormat == null) {
            return false;
        }
        int encodingForMimeType = -1;
        try {
            iMapChannelCountToMask = AudioTrack.mapChannelCountToMask(mediaFormat.channelCount, mediaFormat.mimeType);
            try {
                encodingForMimeType = AudioTrack.getEncodingForMimeType(mediaFormat.mimeType, -1);
                android.media.AudioTrack audioTrackBuild = new android.media.AudioTrack.Builder().setAudioFormat(new AudioFormat.Builder().setChannelMask(iMapChannelCountToMask).setEncoding(encodingForMimeType).setSampleRate(mediaFormat.sampleRate).build()).setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(3).build()).setTransferMode(1).setBufferSizeInBytes(32768).build();
                if (audioTrackBuild != null) {
                    audioTrackBuild.release();
                    return true;
                }
            } catch (Exception e) {
                e = e;
                i = encodingForMimeType;
                encodingForMimeType = iMapChannelCountToMask;
                Log.d("AudioCapabilities", e.toString());
                int i2 = i;
                iMapChannelCountToMask = encodingForMimeType;
                encodingForMimeType = i2;
            }
        } catch (Exception e2) {
            e = e2;
            i = -1;
        }
        Log.d("AudioCapabilities", "Direct playback not supported, config(" + encodingForMimeType + ", " + iMapChannelCountToMask + ", " + mediaFormat.sampleRate + ")");
        return false;
    }

    public static void enablePassThrough() {
        isEnablePassThrough = true;
    }
}
