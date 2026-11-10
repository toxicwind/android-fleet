package com.google.android.exoplayer;

/* JADX INFO: loaded from: classes.dex */
public interface MediaCodecSelector {
    public static final MediaCodecSelector DEFAULT = new MediaCodecSelector() { // from class: com.google.android.exoplayer.MediaCodecSelector.1
        @Override // com.google.android.exoplayer.MediaCodecSelector
        public DecoderInfo getDecoderInfo(String str, boolean z) throws MediaCodecUtil.DecoderQueryException {
            return MediaCodecUtil.getDecoderInfo(str, z);
        }

        @Override // com.google.android.exoplayer.MediaCodecSelector
        public DecoderInfo getPassthroughDecoderInfo() throws MediaCodecUtil.DecoderQueryException {
            return MediaCodecUtil.getPassthroughDecoderInfo();
        }

        @Override // com.google.android.exoplayer.MediaCodecSelector
        public DecoderInfo getDecoderInfo(String str, boolean z, boolean z2) throws MediaCodecUtil.DecoderQueryException {
            return MediaCodecUtil.getDecoderInfo(str, z, z2);
        }
    };

    DecoderInfo getDecoderInfo(String str, boolean z) throws MediaCodecUtil.DecoderQueryException;

    DecoderInfo getDecoderInfo(String str, boolean z, boolean z2) throws MediaCodecUtil.DecoderQueryException;

    DecoderInfo getPassthroughDecoderInfo() throws MediaCodecUtil.DecoderQueryException;
}
