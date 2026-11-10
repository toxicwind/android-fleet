package com.google.android.exoplayer.text.eia608;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.MediaFormatHolder;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.SampleSourceTrackRenderer;
import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.TextRenderer;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.util.Collections;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public final class Eia608TrackRenderer extends SampleSourceTrackRenderer implements Handler.Callback {
    private String caption;
    private int captionMode;
    private int captionRowCount;
    private final StringBuilder captionStringBuilder;
    private final Eia608Parser eia608Parser;
    private final MediaFormatHolder formatHolder;
    private boolean inputStreamEnded;
    private String lastRenderedCaption;
    private final TreeSet<ClosedCaptionList> pendingCaptionLists;
    private ClosedCaptionCtrl repeatableControl;
    private final SampleHolder sampleHolder;
    private final TextRenderer textRenderer;
    private final Handler textRendererHandler;

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected long getBufferedPositionUs() {
        return -3L;
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected boolean isReady() {
        return true;
    }

    public Eia608TrackRenderer(SampleSource sampleSource, TextRenderer textRenderer, Looper looper) {
        super(sampleSource);
        Assertions.checkNotNull(textRenderer);
        this.textRenderer = textRenderer;
        this.textRendererHandler = looper == null ? null : new Handler(looper, this);
        this.eia608Parser = new Eia608Parser();
        this.formatHolder = new MediaFormatHolder();
        this.sampleHolder = new SampleHolder(1);
        this.captionStringBuilder = new StringBuilder();
        this.pendingCaptionLists = new TreeSet<>();
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer
    protected boolean handlesTrack(MediaFormat mediaFormat) {
        return this.eia608Parser.canParse(mediaFormat.mimeType);
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onEnabled(int i, long j, boolean z) throws ExoPlaybackException {
        super.onEnabled(i, j, z);
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer
    protected void onDiscontinuity(long j) {
        this.inputStreamEnded = false;
        this.repeatableControl = null;
        this.pendingCaptionLists.clear();
        clearPendingSample();
        this.captionRowCount = 4;
        setCaptionMode(0);
        invokeRenderer(null);
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer
    protected void doSomeWork(long j, long j2, boolean z) throws ExoPlaybackException {
        if (isSamplePending()) {
            maybeParsePendingSample(j);
        }
        int source = this.inputStreamEnded ? -1 : -3;
        while (!isSamplePending() && source == -3) {
            source = readSource(j, this.formatHolder, this.sampleHolder);
            if (source == -3) {
                maybeParsePendingSample(j);
            } else if (source == -1) {
                this.inputStreamEnded = true;
            }
        }
        while (!this.pendingCaptionLists.isEmpty() && this.pendingCaptionLists.first().timeUs <= j) {
            ClosedCaptionList closedCaptionListPollFirst = this.pendingCaptionLists.pollFirst();
            consumeCaptionList(closedCaptionListPollFirst);
            if (!closedCaptionListPollFirst.decodeOnly) {
                invokeRenderer(this.caption);
            }
        }
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected boolean isEnded() {
        return this.inputStreamEnded;
    }

    private void invokeRenderer(String str) {
        if (Util.areEqual(this.lastRenderedCaption, str)) {
            return;
        }
        this.lastRenderedCaption = str;
        Handler handler = this.textRendererHandler;
        if (handler != null) {
            handler.obtainMessage(0, str).sendToTarget();
        } else {
            invokeRendererInternal(str);
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 0) {
            return false;
        }
        invokeRendererInternal((String) message.obj);
        return true;
    }

    private void invokeRendererInternal(String str) {
        if (str == null) {
            this.textRenderer.onCues(Collections.emptyList());
        } else {
            this.textRenderer.onCues(Collections.singletonList(new Cue(str)));
        }
    }

    private void maybeParsePendingSample(long j) {
        SampleHolder sampleHolder = this.sampleHolder;
        if (sampleHolder.timeUs > j + 5000000) {
            return;
        }
        ClosedCaptionList closedCaptionList = this.eia608Parser.parse(sampleHolder);
        clearPendingSample();
        if (closedCaptionList != null) {
            this.pendingCaptionLists.add(closedCaptionList);
        }
    }

    private void consumeCaptionList(ClosedCaptionList closedCaptionList) {
        ClosedCaptionCtrl closedCaptionCtrl;
        int length = closedCaptionList.captions.length;
        if (length == 0) {
            return;
        }
        boolean z = false;
        for (int i = 0; i < length; i++) {
            ClosedCaption closedCaption = closedCaptionList.captions[i];
            if (closedCaption.type == 0) {
                ClosedCaptionCtrl closedCaptionCtrl2 = (ClosedCaptionCtrl) closedCaption;
                z = length == 1 && closedCaptionCtrl2.isRepeatable();
                if (z && (closedCaptionCtrl = this.repeatableControl) != null && closedCaptionCtrl.cc1 == closedCaptionCtrl2.cc1 && closedCaptionCtrl.cc2 == closedCaptionCtrl2.cc2) {
                    this.repeatableControl = null;
                } else {
                    if (z) {
                        this.repeatableControl = closedCaptionCtrl2;
                    }
                    if (closedCaptionCtrl2.isMiscCode()) {
                        handleMiscCode(closedCaptionCtrl2);
                    } else if (closedCaptionCtrl2.isPreambleAddressCode()) {
                        handlePreambleAddressCode();
                    }
                }
            } else {
                handleText((ClosedCaptionText) closedCaption);
            }
        }
        if (!z) {
            this.repeatableControl = null;
        }
        int i2 = this.captionMode;
        if (i2 == 1 || i2 == 3) {
            this.caption = getDisplayCaption();
        }
    }

    private void handleText(ClosedCaptionText closedCaptionText) {
        if (this.captionMode != 0) {
            this.captionStringBuilder.append(closedCaptionText.text);
        }
    }

    private void handleMiscCode(ClosedCaptionCtrl closedCaptionCtrl) {
        byte b = closedCaptionCtrl.cc2;
        if (b == 32) {
            setCaptionMode(2);
            return;
        }
        if (b != 41) {
            switch (b) {
                case 37:
                    this.captionRowCount = 2;
                    setCaptionMode(1);
                    break;
                case 38:
                    this.captionRowCount = 3;
                    setCaptionMode(1);
                    break;
                case 39:
                    this.captionRowCount = 4;
                    setCaptionMode(1);
                    break;
                default:
                    int i = this.captionMode;
                    if (i != 0) {
                        if (b != 33) {
                            switch (b) {
                                case 44:
                                    this.caption = null;
                                    if (i == 1 || i == 3) {
                                        this.captionStringBuilder.setLength(0);
                                    }
                                    break;
                                case 45:
                                    maybeAppendNewline();
                                    break;
                                case 46:
                                    this.captionStringBuilder.setLength(0);
                                    break;
                                case 47:
                                    this.caption = getDisplayCaption();
                                    this.captionStringBuilder.setLength(0);
                                    break;
                            }
                        } else {
                            if (this.captionStringBuilder.length() > 0) {
                                StringBuilder sb = this.captionStringBuilder;
                                sb.setLength(sb.length() - 1);
                            }
                            break;
                        }
                    }
                    break;
            }
            return;
        }
        setCaptionMode(3);
    }

    private void handlePreambleAddressCode() {
        maybeAppendNewline();
    }

    private void setCaptionMode(int i) {
        if (this.captionMode == i) {
            return;
        }
        this.captionMode = i;
        this.captionStringBuilder.setLength(0);
        if (i == 1 || i == 0) {
            this.caption = null;
        }
    }

    private void maybeAppendNewline() {
        int length = this.captionStringBuilder.length();
        if (length <= 0 || this.captionStringBuilder.charAt(length - 1) == '\n') {
            return;
        }
        this.captionStringBuilder.append('\n');
    }

    private String getDisplayCaption() {
        int length = this.captionStringBuilder.length();
        if (length == 0) {
            return null;
        }
        int i = length - 1;
        boolean z = this.captionStringBuilder.charAt(i) == '\n';
        if (length == 1 && z) {
            return null;
        }
        if (z) {
            length = i;
        }
        if (this.captionMode != 1) {
            return this.captionStringBuilder.substring(0, length);
        }
        int iLastIndexOf = length;
        for (int i2 = 0; i2 < this.captionRowCount && iLastIndexOf != -1; i2++) {
            iLastIndexOf = this.captionStringBuilder.lastIndexOf("\n", iLastIndexOf - 1);
        }
        int i3 = iLastIndexOf != -1 ? iLastIndexOf + 1 : 0;
        this.captionStringBuilder.delete(0, i3);
        return this.captionStringBuilder.substring(0, length - i3);
    }

    private void clearPendingSample() {
        SampleHolder sampleHolder = this.sampleHolder;
        sampleHolder.timeUs = -1L;
        sampleHolder.clearData();
    }

    private boolean isSamplePending() {
        return this.sampleHolder.timeUs != -1;
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onDisabled(long j) throws ExoPlaybackException {
        super.onDisabled(j);
        invokeRenderer(null);
    }
}
