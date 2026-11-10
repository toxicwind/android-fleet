package com.google.android.exoplayer.text;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
final class SubtitleParserHelper implements Handler.Callback {
    private IOException error;
    private final Handler handler;
    private final SubtitleParser parser;
    private boolean parsing;
    private PlayableSubtitle result;
    private RuntimeException runtimeError;
    private SampleHolder sampleHolder;
    private long subtitleOffsetUs;
    private boolean subtitlesAreRelative;

    public SubtitleParserHelper(Looper looper, SubtitleParser subtitleParser) {
        this.handler = new Handler(looper, this);
        this.parser = subtitleParser;
        flush();
    }

    public synchronized void flush() {
        this.sampleHolder = new SampleHolder(1);
        this.parsing = false;
        this.result = null;
        this.error = null;
        this.runtimeError = null;
    }

    public synchronized boolean isParsing() {
        return this.parsing;
    }

    public synchronized SampleHolder getSampleHolder() {
        return this.sampleHolder;
    }

    public void setFormat(MediaFormat mediaFormat) {
        this.handler.obtainMessage(0, mediaFormat).sendToTarget();
    }

    public synchronized void startParseOperation() {
        Assertions.checkState(!this.parsing);
        this.parsing = true;
        this.result = null;
        this.error = null;
        this.runtimeError = null;
        this.handler.obtainMessage(1, Util.getTopInt(this.sampleHolder.timeUs), Util.getBottomInt(this.sampleHolder.timeUs), this.sampleHolder).sendToTarget();
    }

    public synchronized PlayableSubtitle getAndClearResult() throws IOException {
        PlayableSubtitle playableSubtitle;
        try {
            if (this.error != null) {
                throw this.error;
            }
            if (this.runtimeError != null) {
                throw this.runtimeError;
            }
            playableSubtitle = this.result;
            this.result = null;
            this.error = null;
            this.runtimeError = null;
        } catch (Throwable th) {
            this.result = null;
            this.error = null;
            this.runtimeError = null;
            throw th;
        }
        return playableSubtitle;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) throws ParserException {
        int i = message.what;
        if (i == 0) {
            handleFormat((MediaFormat) message.obj);
        } else if (i == 1) {
            handleSample(Util.getLong(message.arg1, message.arg2), (SampleHolder) message.obj);
        }
        return true;
    }

    private void handleFormat(MediaFormat mediaFormat) {
        boolean z = mediaFormat.subsampleOffsetUs == LongCompanionObject.MAX_VALUE;
        this.subtitlesAreRelative = z;
        this.subtitleOffsetUs = z ? 0L : mediaFormat.subsampleOffsetUs;
    }

    private void handleSample(long j, SampleHolder sampleHolder) throws ParserException {
        Subtitle subtitle;
        ParserException parserException = null;
        try {
            subtitle = this.parser.parse(sampleHolder.data.array(), 0, sampleHolder.size);
            e = null;
        } catch (ParserException e) {
            subtitle = null;
            parserException = e;
            e = null;
        } catch (RuntimeException e2) {
            e = e2;
            subtitle = null;
        }
        synchronized (this) {
            if (this.sampleHolder == sampleHolder) {
                this.result = new PlayableSubtitle(subtitle, this.subtitlesAreRelative, j, this.subtitleOffsetUs);
                this.error = parserException;
                this.runtimeError = e;
                this.parsing = false;
            }
        }
    }
}
