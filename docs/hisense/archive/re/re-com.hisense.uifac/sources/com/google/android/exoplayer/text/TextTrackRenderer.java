package com.google.android.exoplayer.text;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.MediaFormatHolder;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.SampleSourceTrackRenderer;
import com.google.android.exoplayer.text.ssa.InternalSsaParser;
import com.google.android.exoplayer.text.sub.SubParser;
import com.google.android.exoplayer.text.vobsub.VobSubParser;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(16)
public final class TextTrackRenderer extends SampleSourceTrackRenderer implements Handler.Callback {
    private static final List<Class<? extends SubtitleParser>> DEFAULT_PARSER_CLASSES;
    private final MediaFormatHolder formatHolder;
    private boolean inputStreamEnded;
    private boolean isParseThirdSubtitle;
    private PlayableSubtitle nextSubtitle;
    private int nextSubtitleEventIndex;
    private long offsetUs;
    private SubtitleParserHelper parserHelper;
    private int parserIndex;
    private HandlerThread parserThread;
    private PlayableSubtitle subtitle;
    private final SubtitleParser[] subtitleParsers;
    private final TextRenderer textRenderer;
    private final Handler textRendererHandler;
    private PlayableSubtitle thirdSubtitle;

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected long getBufferedPositionUs() {
        return -3L;
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected boolean isReady() {
        return true;
    }

    static {
        ArrayList arrayList = new ArrayList();
        DEFAULT_PARSER_CLASSES = arrayList;
        try {
            arrayList.add(Class.forName("com.google.android.exoplayer.text.webvtt.WebvttParser").asSubclass(SubtitleParser.class));
        } catch (ClassNotFoundException unused) {
        }
        try {
            DEFAULT_PARSER_CLASSES.add(Class.forName("com.google.android.exoplayer.text.ttml.TtmlParser").asSubclass(SubtitleParser.class));
        } catch (ClassNotFoundException unused2) {
        }
        try {
            DEFAULT_PARSER_CLASSES.add(Class.forName("com.google.android.exoplayer.text.webvtt.Mp4WebvttParser").asSubclass(SubtitleParser.class));
        } catch (ClassNotFoundException unused3) {
        }
        try {
            DEFAULT_PARSER_CLASSES.add(Class.forName("com.google.android.exoplayer.text.subrip.SubripParser").asSubclass(SubtitleParser.class));
        } catch (ClassNotFoundException unused4) {
        }
        try {
            DEFAULT_PARSER_CLASSES.add(Class.forName("com.google.android.exoplayer.text.tx3g.Tx3gParser").asSubclass(SubtitleParser.class));
        } catch (ClassNotFoundException unused5) {
        }
        try {
            DEFAULT_PARSER_CLASSES.add(Class.forName("com.google.android.exoplayer.text.smi.SmiParser").asSubclass(SubtitleParser.class));
        } catch (ClassNotFoundException unused6) {
        }
    }

    public TextTrackRenderer(SampleSource sampleSource, TextRenderer textRenderer, Looper looper, SubtitleParser... subtitleParserArr) {
        this(new SampleSource[]{sampleSource}, textRenderer, looper, subtitleParserArr);
    }

    public TextTrackRenderer(SampleSource[] sampleSourceArr, TextRenderer textRenderer, Looper looper, SubtitleParser... subtitleParserArr) {
        super(sampleSourceArr);
        this.offsetUs = 0L;
        Assertions.checkNotNull(textRenderer);
        this.textRenderer = textRenderer;
        this.textRendererHandler = looper == null ? null : new Handler(looper, this);
        if (subtitleParserArr == null || subtitleParserArr.length == 0) {
            int size = DEFAULT_PARSER_CLASSES.size();
            subtitleParserArr = new SubtitleParser[size];
            for (int i = 0; i < size; i++) {
                try {
                    subtitleParserArr[i] = DEFAULT_PARSER_CLASSES.get(i).newInstance();
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Unexpected error creating default parser", e);
                } catch (InstantiationException e2) {
                    throw new IllegalStateException("Unexpected error creating default parser", e2);
                }
            }
        }
        this.subtitleParsers = subtitleParserArr;
        this.formatHolder = new MediaFormatHolder();
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer
    protected boolean handlesTrack(MediaFormat mediaFormat) {
        return getParserIndex(mediaFormat) != -1;
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onEnabled(int i, long j, boolean z) throws ExoPlaybackException {
        MediaFormat format;
        super.onEnabled(i, j, z);
        Log.d("TextTrackRenderer", "onEnabled,track==" + i);
        this.parserIndex = getParserIndex(getFormat(i));
        HandlerThread handlerThread = new HandlerThread("textParser");
        this.parserThread = handlerThread;
        handlerThread.start();
        if ((this.subtitleParsers[this.parserIndex] instanceof SubtitlePreInitParser) && (format = getFormat(i)) != null) {
            ((SubtitlePreInitParser) this.subtitleParsers[this.parserIndex]).init(format.getFrameworkMediaFormatV16());
        }
        Log.d("TextTrackRenderer", "onEnabled,subtitleParsers[parserIndex] == " + this.subtitleParsers[this.parserIndex]);
        if (this.subtitleParsers[this.parserIndex] instanceof InternalSsaParser) {
            this.isParseThirdSubtitle = true;
        } else {
            this.isParseThirdSubtitle = false;
        }
        Log.d("TextTrackRenderer", "onEnabled,isParseThirdSubtitle == " + this.isParseThirdSubtitle);
        if (this.subtitleParsers[this.parserIndex] instanceof VobSubParser) {
            Log.d("TextTrackRenderer", "VobSubParser");
            ((VobSubParser) this.subtitleParsers[this.parserIndex]).enableSubtitleIndex(i);
        }
        if (this.subtitleParsers[this.parserIndex] instanceof SubParser) {
            int i2 = getFormat(i).frameRate;
            Log.d("TextTrackRenderer", "SubParser, frameRate==" + i2);
            if (i2 != -1) {
                ((SubParser) this.subtitleParsers[this.parserIndex]).setFrameRate(i2);
            }
        }
        this.parserHelper = new SubtitleParserHelper(this.parserThread.getLooper(), this.subtitleParsers[this.parserIndex]);
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer
    protected void onDiscontinuity(long j) {
        this.inputStreamEnded = false;
        this.subtitle = null;
        this.nextSubtitle = null;
        this.thirdSubtitle = null;
        clearTextRenderer();
        SubtitleParserHelper subtitleParserHelper = this.parserHelper;
        if (subtitleParserHelper != null) {
            subtitleParserHelper.flush();
        }
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer
    protected void doSomeWork(long j, long j2, boolean z) throws ExoPlaybackException {
        boolean z2;
        long j3 = this.offsetUs;
        long j4 = j - j3 > 0 ? j - j3 : 0L;
        if (this.nextSubtitle == null) {
            try {
                this.nextSubtitle = this.parserHelper.getAndClearResult();
            } catch (IOException e) {
                throw new ExoPlaybackException(e);
            }
        } else if (this.thirdSubtitle == null && this.isParseThirdSubtitle) {
            try {
                this.thirdSubtitle = this.parserHelper.getAndClearResult();
            } catch (IOException e2) {
                throw new ExoPlaybackException(e2);
            }
        }
        if (getState() != 3) {
            return;
        }
        if (this.subtitle != null) {
            long nextEventTime = getNextEventTime();
            z2 = false;
            while (nextEventTime <= j4) {
                this.nextSubtitleEventIndex++;
                nextEventTime = getNextEventTime();
                z2 = true;
            }
        } else {
            z2 = false;
        }
        PlayableSubtitle playableSubtitle = this.nextSubtitle;
        if (playableSubtitle != null && playableSubtitle.startTimeUs <= j4 && (!this.isParseThirdSubtitle || this.thirdSubtitle != null)) {
            PlayableSubtitle playableSubtitle2 = this.nextSubtitle;
            this.subtitle = playableSubtitle2;
            playableSubtitle2.getCues(j4);
            if (!this.isParseThirdSubtitle) {
                this.nextSubtitle = null;
                this.thirdSubtitle = null;
            } else {
                long j5 = this.nextSubtitle.startTimeUs;
                PlayableSubtitle playableSubtitle3 = this.thirdSubtitle;
                if (j5 == playableSubtitle3.startTimeUs) {
                    if (playableSubtitle3.getCues(j4).size() > 0) {
                        String string = this.thirdSubtitle.getCues(j4).get(0).text.toString();
                        String string2 = this.nextSubtitle.getCues(j4).get(0).text.toString();
                        this.subtitle.getCues(j4).get(0).setText(string2 + " \n" + string);
                    }
                    this.nextSubtitle = null;
                    this.thirdSubtitle = null;
                } else {
                    this.nextSubtitle = playableSubtitle3;
                    this.thirdSubtitle = null;
                }
            }
            this.nextSubtitleEventIndex = this.subtitle.getNextEventTimeIndex(j4);
            z2 = true;
        }
        if (z2) {
            updateTextRenderer(this.subtitle.getCues(j4));
        }
        if (this.inputStreamEnded) {
            return;
        }
        if ((this.nextSubtitle == null || (this.thirdSubtitle == null && this.isParseThirdSubtitle)) && !this.parserHelper.isParsing()) {
            SampleHolder sampleHolder = this.parserHelper.getSampleHolder();
            sampleHolder.clearData();
            int source = readSource(j4, this.formatHolder, sampleHolder);
            Log.d("TextTrackRenderer", "result==" + source);
            if (source == -4) {
                this.parserHelper.setFormat(this.formatHolder.format);
            } else if (source == -3) {
                this.parserHelper.startParseOperation();
            } else if (source == -1) {
                this.inputStreamEnded = true;
            }
        }
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onDisabled(long j) throws ExoPlaybackException {
        this.subtitle = null;
        this.nextSubtitle = null;
        this.thirdSubtitle = null;
        this.parserThread.quit();
        this.parserThread = null;
        this.parserHelper = null;
        clearTextRenderer();
        super.onDisabled(j);
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected boolean isEnded() {
        return this.inputStreamEnded && (this.subtitle == null || getNextEventTime() == LongCompanionObject.MAX_VALUE);
    }

    private long getNextEventTime() {
        int i = this.nextSubtitleEventIndex;
        return (i == -1 || i >= this.subtitle.getEventTimeCount()) ? LongCompanionObject.MAX_VALUE : this.subtitle.getEventTime(this.nextSubtitleEventIndex);
    }

    private void updateTextRenderer(List<Cue> list) {
        Handler handler = this.textRendererHandler;
        if (handler != null) {
            handler.obtainMessage(0, list).sendToTarget();
        } else {
            invokeRendererInternalCues(list);
        }
    }

    private void clearTextRenderer() {
        updateTextRenderer(Collections.emptyList());
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 0) {
            return false;
        }
        invokeRendererInternalCues((List) message.obj);
        return true;
    }

    private void invokeRendererInternalCues(List<Cue> list) {
        this.textRenderer.onCues(list);
    }

    private int getParserIndex(MediaFormat mediaFormat) {
        int i = 0;
        while (true) {
            SubtitleParser[] subtitleParserArr = this.subtitleParsers;
            if (i >= subtitleParserArr.length) {
                return -1;
            }
            if (subtitleParserArr[i].canParse(mediaFormat.mimeType)) {
                return i;
            }
            i++;
        }
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer, com.google.android.exoplayer.ExoPlayer.ExoPlayerComponent
    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        MtkDebugLogUtil.log("TextTrackRenderer", "messageType = " + i + " message = " + obj, 16);
        if (i != 1003) {
            if (i == 1005) {
                this.offsetUs = ((Long) obj).longValue();
                return;
            } else {
                super.handleMessage(i, obj);
                return;
            }
        }
        SubtitleParser[] subtitleParserArr = this.subtitleParsers;
        int i2 = this.parserIndex;
        if (subtitleParserArr[i2] instanceof ExternalSubtitleCharsetEncoding) {
            ((ExternalSubtitleCharsetEncoding) subtitleParserArr[i2]).setCharsetEncoding((String) obj);
        }
    }
}
