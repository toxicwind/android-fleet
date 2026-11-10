package com.google.android.exoplayer.extractor;

/* JADX INFO: loaded from: classes.dex */
public interface SeekMap {
    public static final SeekMap UNSEEKABLE = new SeekMap() { // from class: com.google.android.exoplayer.extractor.SeekMap.1
        @Override // com.google.android.exoplayer.extractor.SeekMap
        public long getPosition(long j) {
            return 0L;
        }

        @Override // com.google.android.exoplayer.extractor.SeekMap
        public boolean isSeekable() {
            return false;
        }
    };

    long getPosition(long j);

    boolean isSeekable();
}
