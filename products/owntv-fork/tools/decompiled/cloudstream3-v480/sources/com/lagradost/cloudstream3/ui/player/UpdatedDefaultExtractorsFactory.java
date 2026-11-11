package com.lagradost.cloudstream3.ui.player;

import android.net.Uri;
import androidx.media3.common.FileTypes;
import androidx.media3.common.Format;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.amr.AmrExtractor;
import androidx.media3.extractor.avi.AviExtractor;
import androidx.media3.extractor.avif.AvifExtractor;
import androidx.media3.extractor.bmp.BmpExtractor;
import androidx.media3.extractor.flac.FlacExtractor;
import androidx.media3.extractor.flv.FlvExtractor;
import androidx.media3.extractor.heif.HeifExtractor;
import androidx.media3.extractor.jpeg.JpegExtractor;
import androidx.media3.extractor.mkv.UpdatedMatroskaExtractor;
import androidx.media3.extractor.mp3.Mp3Extractor;
import androidx.media3.extractor.mp4.FragmentedMp4Extractor;
import androidx.media3.extractor.mp4.Mp4Extractor;
import androidx.media3.extractor.ogg.OggExtractor;
import androidx.media3.extractor.png.PngExtractor;
import androidx.media3.extractor.text.DefaultSubtitleParserFactory;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.ts.Ac3Extractor;
import androidx.media3.extractor.ts.Ac4Extractor;
import androidx.media3.extractor.ts.AdtsExtractor;
import androidx.media3.extractor.ts.DefaultTsPayloadReaderFactory;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.media3.extractor.wav.WavExtractor;
import androidx.media3.extractor.webp.WebpExtractor;
import com.google.common.collect.ImmutableList;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UpdatedDefaultExtractorsFactory.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\b\u0007\u0018\u0000 C2\u00020\u0001:\u0002BCB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\bJ\u000e\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\bJ\u000e\u0010 \u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\bJ\u000e\u0010!\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\bJ\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\bJ\u000e\u0010#\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\bJ\u000e\u0010$\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\bJ\u000e\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\bJ\u000e\u0010'\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\bJ\u0016\u0010(\u001a\u00020\u00002\u000e\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010*J\u000e\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\bJ\u0010\u0010-\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0005H\u0007J\u0010\u0010.\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0005H\u0017J\u0010\u0010/\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u00100\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\bH\u0016J\u000e\u00101\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\bJ\u000e\u00102\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\bJ\u0013\u00103\u001a\b\u0012\u0004\u0012\u00020504H\u0016¢\u0006\u0002\u00106J5\u00103\u001a\b\u0012\u0004\u0012\u000205042\u0006\u00107\u001a\u0002082\u0018\u00109\u001a\u0014\u0012\u0004\u0012\u00020;\u0012\n\u0012\b\u0012\u0004\u0012\u00020;0*0:H\u0016¢\u0006\u0002\u0010<J\u001e\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\b2\f\u0010@\u001a\b\u0012\u0004\u0012\u0002050AH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/UpdatedDefaultExtractorsFactory;", "Landroidx/media3/extractor/ExtractorsFactory;", "<init>", "()V", "constantBitrateSeekingEnabled", "", "constantBitrateSeekingAlwaysEnabled", "adtsFlags", "", "amrFlags", "flacFlags", "matroskaFlags", "mp4Flags", "fragmentedMp4Flags", "mp3Flags", "tsMode", "tsFlags", "tsSubtitleFormats", "Lcom/google/common/collect/ImmutableList;", "Landroidx/media3/common/Format;", "tsTimestampSearchBytes", "textTrackTranscodingEnabled", "subtitleParserFactory", "Landroidx/media3/extractor/text/SubtitleParser$Factory;", "codecsToParseWithinGopSampleDependencies", "jpegFlags", "heifFlags", "setConstantBitrateSeekingEnabled", "setConstantBitrateSeekingAlwaysEnabled", "setAdtsExtractorFlags", "flags", "setAmrExtractorFlags", "setFlacExtractorFlags", "setMatroskaExtractorFlags", "setMp4ExtractorFlags", "setFragmentedMp4ExtractorFlags", "setMp3ExtractorFlags", "setTsExtractorMode", "mode", "setTsExtractorFlags", "setTsSubtitleFormats", "subtitleFormats", "", "setTsExtractorTimestampSearchBytes", "timestampSearchBytes", "setTextTrackTranscodingEnabled", "experimentalSetTextTrackTranscodingEnabled", "setSubtitleParserFactory", "experimentalSetCodecsToParseWithinGopSampleDependencies", "setJpegExtractorFlags", "setHeifExtractorFlags", "createExtractors", "", "Landroidx/media3/extractor/Extractor;", "()[Landroidx/media3/extractor/Extractor;", "uri", "Landroid/net/Uri;", "responseHeaders", "", "", "(Landroid/net/Uri;Ljava/util/Map;)[Landroidx/media3/extractor/Extractor;", "addExtractorsForFileType", "", "fileType", "extractors", "", "ExtensionLoader", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class UpdatedDefaultExtractorsFactory implements ExtractorsFactory {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int[] DEFAULT_EXTRACTOR_ORDER = {5, 4, 12, 8, 3, 10, 9, 11, 6, 2, 0, 1, 7, 16, 15, 14, 17, 18, 19, 20, 21};
    private static final ExtensionLoader FLAC_EXTENSION_LOADER = new ExtensionLoader(new ExtensionLoader.ConstructorSupplier() { // from class: com.lagradost.cloudstream3.ui.player.UpdatedDefaultExtractorsFactory$Companion$FLAC_EXTENSION_LOADER$1
        @Override // com.lagradost.cloudstream3.ui.player.UpdatedDefaultExtractorsFactory.ExtensionLoader.ConstructorSupplier
        public Constructor<? extends Extractor> getConstructor() {
            return UpdatedDefaultExtractorsFactory.INSTANCE.getFlacExtractorConstructor();
        }
    });
    private static final ExtensionLoader MIDI_EXTENSION_LOADER = new ExtensionLoader(new ExtensionLoader.ConstructorSupplier() { // from class: com.lagradost.cloudstream3.ui.player.UpdatedDefaultExtractorsFactory$Companion$MIDI_EXTENSION_LOADER$1
        @Override // com.lagradost.cloudstream3.ui.player.UpdatedDefaultExtractorsFactory.ExtensionLoader.ConstructorSupplier
        public Constructor<? extends Extractor> getConstructor() {
            return UpdatedDefaultExtractorsFactory.INSTANCE.getMidiExtractorConstructor();
        }
    });
    private int adtsFlags;
    private int amrFlags;
    private boolean constantBitrateSeekingAlwaysEnabled;
    private boolean constantBitrateSeekingEnabled;
    private int flacFlags;
    private int fragmentedMp4Flags;
    private int heifFlags;
    private int jpegFlags;
    private int matroskaFlags;
    private int mp3Flags;
    private int mp4Flags;
    private int tsFlags;
    private ImmutableList<Format> tsSubtitleFormats;
    private int tsMode = 1;
    private int tsTimestampSearchBytes = TsExtractor.DEFAULT_TIMESTAMP_SEARCH_BYTES;
    private SubtitleParser.Factory subtitleParserFactory = new DefaultSubtitleParserFactory();
    private boolean textTrackTranscodingEnabled = true;
    private int codecsToParseWithinGopSampleDependencies = 3;

    public final synchronized UpdatedDefaultExtractorsFactory setConstantBitrateSeekingEnabled(boolean constantBitrateSeekingEnabled) {
        this.constantBitrateSeekingEnabled = constantBitrateSeekingEnabled;
        return this;
    }

    public final synchronized UpdatedDefaultExtractorsFactory setConstantBitrateSeekingAlwaysEnabled(boolean constantBitrateSeekingAlwaysEnabled) {
        this.constantBitrateSeekingAlwaysEnabled = constantBitrateSeekingAlwaysEnabled;
        return this;
    }

    public final synchronized UpdatedDefaultExtractorsFactory setAdtsExtractorFlags(int flags) {
        this.adtsFlags = flags;
        return this;
    }

    public final synchronized UpdatedDefaultExtractorsFactory setAmrExtractorFlags(int flags) {
        this.amrFlags = flags;
        return this;
    }

    public final synchronized UpdatedDefaultExtractorsFactory setFlacExtractorFlags(int flags) {
        this.flacFlags = flags;
        return this;
    }

    public final synchronized UpdatedDefaultExtractorsFactory setMatroskaExtractorFlags(int flags) {
        this.matroskaFlags = flags;
        return this;
    }

    public final synchronized UpdatedDefaultExtractorsFactory setMp4ExtractorFlags(int flags) {
        this.mp4Flags = flags;
        return this;
    }

    public final synchronized UpdatedDefaultExtractorsFactory setFragmentedMp4ExtractorFlags(int flags) {
        this.fragmentedMp4Flags = flags;
        return this;
    }

    public final synchronized UpdatedDefaultExtractorsFactory setMp3ExtractorFlags(int flags) {
        this.mp3Flags = flags;
        return this;
    }

    public final synchronized UpdatedDefaultExtractorsFactory setTsExtractorMode(int mode) {
        this.tsMode = mode;
        return this;
    }

    public final synchronized UpdatedDefaultExtractorsFactory setTsExtractorFlags(int flags) {
        this.tsFlags = flags;
        return this;
    }

    public final synchronized UpdatedDefaultExtractorsFactory setTsSubtitleFormats(List<Format> subtitleFormats) {
        ImmutableList<Format> immutableListCopyOf;
        if (subtitleFormats != null) {
            try {
                immutableListCopyOf = ImmutableList.copyOf((Collection) subtitleFormats);
            } catch (Throwable th) {
                throw th;
            }
        } else {
            immutableListCopyOf = null;
        }
        this.tsSubtitleFormats = immutableListCopyOf;
        return this;
    }

    public final synchronized UpdatedDefaultExtractorsFactory setTsExtractorTimestampSearchBytes(int timestampSearchBytes) {
        this.tsTimestampSearchBytes = timestampSearchBytes;
        return this;
    }

    @Deprecated(message = "This method (and all support for 'legacy' subtitle decoding during rendering) will\n        be removed in a future release.")
    public final synchronized UpdatedDefaultExtractorsFactory setTextTrackTranscodingEnabled(boolean textTrackTranscodingEnabled) {
        return experimentalSetTextTrackTranscodingEnabled(textTrackTranscodingEnabled);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    @Deprecated(message = "")
    public synchronized UpdatedDefaultExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean textTrackTranscodingEnabled) {
        this.textTrackTranscodingEnabled = textTrackTranscodingEnabled;
        return this;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public synchronized UpdatedDefaultExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory subtitleParserFactory) {
        Intrinsics.checkNotNullParameter(subtitleParserFactory, "subtitleParserFactory");
        this.subtitleParserFactory = subtitleParserFactory;
        return this;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public synchronized UpdatedDefaultExtractorsFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int codecsToParseWithinGopSampleDependencies) {
        this.codecsToParseWithinGopSampleDependencies = codecsToParseWithinGopSampleDependencies;
        return this;
    }

    public final synchronized UpdatedDefaultExtractorsFactory setJpegExtractorFlags(int flags) {
        this.jpegFlags = flags;
        return this;
    }

    public final synchronized UpdatedDefaultExtractorsFactory setHeifExtractorFlags(int flags) {
        this.heifFlags = flags;
        return this;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public synchronized Extractor[] createExtractors() {
        Uri EMPTY;
        EMPTY = Uri.EMPTY;
        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
        return createExtractors(EMPTY, new HashMap());
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public synchronized Extractor[] createExtractors(Uri uri, Map<String, ? extends List<String>> responseHeaders) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders");
        int[] iArr = DEFAULT_EXTRACTOR_ORDER;
        arrayList = new ArrayList(iArr.length);
        int iInferFileTypeFromResponseHeaders = FileTypes.inferFileTypeFromResponseHeaders(responseHeaders);
        if (iInferFileTypeFromResponseHeaders != -1) {
            addExtractorsForFileType(iInferFileTypeFromResponseHeaders, arrayList);
        }
        int iInferFileTypeFromUri = FileTypes.inferFileTypeFromUri(uri);
        if (iInferFileTypeFromUri != -1 && iInferFileTypeFromUri != iInferFileTypeFromResponseHeaders) {
            addExtractorsForFileType(iInferFileTypeFromUri, arrayList);
        }
        for (int i : iArr) {
            if (i != iInferFileTypeFromResponseHeaders && i != iInferFileTypeFromUri) {
                addExtractorsForFileType(i, arrayList);
            }
        }
        return (Extractor[]) arrayList.toArray(new Extractor[0]);
    }

    private final void addExtractorsForFileType(int fileType, List<Extractor> extractors) {
        switch (fileType) {
            case 0:
                extractors.add(new Ac3Extractor());
                break;
            case 1:
                extractors.add(new Ac4Extractor());
                break;
            case 2:
                extractors.add(new AdtsExtractor(((this.adtsFlags | (this.constantBitrateSeekingEnabled ? 1 : 0)) == true ? 1 : 0) | (this.constantBitrateSeekingAlwaysEnabled ? 2 : 0)));
                break;
            case 3:
                extractors.add(new AmrExtractor(this.amrFlags | (this.constantBitrateSeekingEnabled ? 1 : 0) | (this.constantBitrateSeekingAlwaysEnabled ? 2 : 0)));
                break;
            case 4:
                Extractor extractor = FLAC_EXTENSION_LOADER.getExtractor(Integer.valueOf(this.flacFlags));
                if (extractor != null) {
                    extractors.add(extractor);
                } else {
                    extractors.add(new FlacExtractor(this.flacFlags));
                }
                break;
            case 5:
                extractors.add(new FlvExtractor());
                break;
            case 6:
                extractors.add(new UpdatedMatroskaExtractor(this.subtitleParserFactory, this.matroskaFlags | (this.textTrackTranscodingEnabled ? 0 : 2)));
                break;
            case 7:
                extractors.add(new Mp3Extractor(this.mp3Flags | (this.constantBitrateSeekingEnabled ? 1 : 0) | (this.constantBitrateSeekingAlwaysEnabled ? 2 : 0)));
                break;
            case 8:
                extractors.add(new FragmentedMp4Extractor(this.subtitleParserFactory, this.fragmentedMp4Flags | FragmentedMp4Extractor.codecsToParseWithinGopSampleDependenciesAsFlags(this.codecsToParseWithinGopSampleDependencies) | (this.textTrackTranscodingEnabled ? 0 : 32)));
                extractors.add(new Mp4Extractor(this.subtitleParserFactory, this.mp4Flags | Mp4Extractor.codecsToParseWithinGopSampleDependenciesAsFlags(this.codecsToParseWithinGopSampleDependencies) | (this.textTrackTranscodingEnabled ? 0 : 16)));
                break;
            case 9:
                extractors.add(new OggExtractor());
                break;
            case 10:
                extractors.add(new PsExtractor());
                break;
            case 11:
                if (this.tsSubtitleFormats == null) {
                    this.tsSubtitleFormats = ImmutableList.of();
                }
                int i = this.tsMode;
                int i2 = !this.textTrackTranscodingEnabled ? 1 : 0;
                SubtitleParser.Factory factory = this.subtitleParserFactory;
                TimestampAdjuster timestampAdjuster = new TimestampAdjuster(0L);
                int i3 = this.tsFlags;
                ImmutableList<Format> immutableList = this.tsSubtitleFormats;
                Intrinsics.checkNotNull(immutableList);
                extractors.add(new TsExtractor(i, i2, factory, timestampAdjuster, new DefaultTsPayloadReaderFactory(i3, immutableList), this.tsTimestampSearchBytes));
                break;
            case 12:
                extractors.add(new WavExtractor());
                break;
            case 14:
                extractors.add(new JpegExtractor(this.jpegFlags));
                break;
            case 15:
                Extractor extractor2 = MIDI_EXTENSION_LOADER.getExtractor(new Object[0]);
                if (extractor2 != null) {
                    extractors.add(extractor2);
                }
                break;
            case 16:
                extractors.add(new AviExtractor(1 ^ (this.textTrackTranscodingEnabled ? 1 : 0), this.subtitleParserFactory));
                break;
            case 17:
                extractors.add(new PngExtractor());
                break;
            case 18:
                extractors.add(new WebpExtractor());
                break;
            case 19:
                extractors.add(new BmpExtractor());
                break;
            case 20:
                extractors.add(new HeifExtractor(this.heifFlags));
                break;
            case 21:
                extractors.add(new AvifExtractor());
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: UpdatedDefaultExtractorsFactory.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J%\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0016\u0010\f\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\r\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u000eJ\u0012\u0010\u000f\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\n\u0018\u00010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\n\u0018\u00010\t8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/UpdatedDefaultExtractorsFactory$ExtensionLoader;", "", "constructorSupplier", "Lcom/lagradost/cloudstream3/ui/player/UpdatedDefaultExtractorsFactory$ExtensionLoader$ConstructorSupplier;", "<init>", "(Lcom/lagradost/cloudstream3/ui/player/UpdatedDefaultExtractorsFactory$ExtensionLoader$ConstructorSupplier;)V", "extensionLoaded", "Ljava/util/concurrent/atomic/AtomicBoolean;", "extractorConstructor", "Ljava/lang/reflect/Constructor;", "Landroidx/media3/extractor/Extractor;", "getExtractor", "constructorParams", "", "([Ljava/lang/Object;)Landroidx/media3/extractor/Extractor;", "maybeLoadExtractorConstructor", "ConstructorSupplier", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    static final class ExtensionLoader {
        private final ConstructorSupplier constructorSupplier;
        private final AtomicBoolean extensionLoaded;
        private final Constructor<? extends Extractor> extractorConstructor;

        /* JADX INFO: compiled from: UpdatedDefaultExtractorsFactory.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u001e\u0010\u0002\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/UpdatedDefaultExtractorsFactory$ExtensionLoader$ConstructorSupplier;", "", "constructor", "Ljava/lang/reflect/Constructor;", "Landroidx/media3/extractor/Extractor;", "getConstructor", "()Ljava/lang/reflect/Constructor;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface ConstructorSupplier {
            Constructor<? extends Extractor> getConstructor() throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException;
        }

        public ExtensionLoader(ConstructorSupplier constructorSupplier) {
            Intrinsics.checkNotNullParameter(constructorSupplier, "constructorSupplier");
            this.constructorSupplier = constructorSupplier;
            this.extensionLoaded = new AtomicBoolean(false);
        }

        public final Extractor getExtractor(Object... constructorParams) {
            Intrinsics.checkNotNullParameter(constructorParams, "constructorParams");
            Constructor<? extends Extractor> constructorMaybeLoadExtractorConstructor = maybeLoadExtractorConstructor();
            if (constructorMaybeLoadExtractorConstructor == null) {
                return null;
            }
            try {
                return constructorMaybeLoadExtractorConstructor.newInstance(Arrays.copyOf(constructorParams, constructorParams.length));
            } catch (Exception e) {
                throw new IllegalStateException("Unexpected error creating extractor", e);
            }
        }

        public final Constructor<? extends Extractor> maybeLoadExtractorConstructor() {
            synchronized (this.extensionLoaded) {
                if (this.extensionLoaded.get()) {
                    return this.extractorConstructor;
                }
                try {
                    return this.constructorSupplier.getConstructor();
                } catch (ClassNotFoundException unused) {
                    this.extensionLoaded.set(true);
                    return this.extractorConstructor;
                } catch (Exception e) {
                    throw new RuntimeException("Error instantiating extension", e);
                }
            }
        }
    }

    /* JADX INFO: compiled from: UpdatedDefaultExtractorsFactory.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000b0\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR \u0010\u000e\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000b\u0018\u00010\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/UpdatedDefaultExtractorsFactory$Companion;", "", "<init>", "()V", "DEFAULT_EXTRACTOR_ORDER", "", "FLAC_EXTENSION_LOADER", "Lcom/lagradost/cloudstream3/ui/player/UpdatedDefaultExtractorsFactory$ExtensionLoader;", "MIDI_EXTENSION_LOADER", "midiExtractorConstructor", "Ljava/lang/reflect/Constructor;", "Landroidx/media3/extractor/Extractor;", "getMidiExtractorConstructor", "()Ljava/lang/reflect/Constructor;", "flacExtractorConstructor", "getFlacExtractorConstructor", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Constructor<? extends Extractor> getMidiExtractorConstructor() throws NoSuchMethodException, ClassNotFoundException {
            Constructor<? extends Extractor> constructor = Class.forName("androidx.media3.decoder.midi.MidiExtractor").asSubclass(Extractor.class).getConstructor(null);
            Intrinsics.checkNotNullExpressionValue(constructor, "getConstructor(...)");
            return constructor;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Constructor<? extends Extractor> getFlacExtractorConstructor() throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
            if (Intrinsics.areEqual((Object) true, Class.forName("androidx.media3.decoder.flac.FlacLibrary").getMethod("isAvailable", null).invoke(null, null))) {
                return Class.forName("androidx.media3.decoder.flac.FlacExtractor").asSubclass(Extractor.class).getConstructor(Integer.TYPE);
            }
            return null;
        }
    }
}
