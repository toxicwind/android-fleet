package com.lagradost.cloudstream3.ui.subtitles;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: SubtitlesFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\bY\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 }2\u00020\u0001:\u0002|}B½\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0001\u0010\u000b\u001a\u00020\u0003\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\b\b\u0003\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0003\u0010\u0011\u001a\u00020\u0010\u0012\b\b\u0003\u0010\u0012\u001a\u00020\u0010\u0012\b\b\u0003\u0010\u0013\u001a\u00020\u0010\u0012\b\b\u0003\u0010\u0014\u001a\u00020\u0010\u0012\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\r\u0012\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0017\u0010\u0018B¯\u0001\b\u0010\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0012\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0010\u0012\u0006\u0010\u0014\u001a\u00020\u0010\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b¢\u0006\u0004\b\u0017\u0010\u001cJ\t\u0010]\u001a\u00020\u0003HÆ\u0003J\t\u0010^\u001a\u00020\u0003HÆ\u0003J\t\u0010_\u001a\u00020\u0003HÆ\u0003J\t\u0010`\u001a\u00020\u0003HÆ\u0003J\t\u0010a\u001a\u00020\u0003HÆ\u0003J\u0010\u0010b\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u00101J\u000b\u0010c\u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u0010d\u001a\u00020\u0003HÆ\u0003J\u0010\u0010e\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010?J\u0010\u0010f\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010?J\t\u0010g\u001a\u00020\u0010HÆ\u0003J\t\u0010h\u001a\u00020\u0010HÆ\u0003J\t\u0010i\u001a\u00020\u0010HÆ\u0003J\t\u0010j\u001a\u00020\u0010HÆ\u0003J\t\u0010k\u001a\u00020\u0010HÆ\u0003J\u0010\u0010l\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010?J\u0010\u0010m\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u00101JÄ\u0001\u0010n\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00032\b\b\u0003\u0010\u0007\u001a\u00020\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0003\u0010\u000b\u001a\u00020\u00032\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0003\u0010\u000f\u001a\u00020\u00102\b\b\u0003\u0010\u0011\u001a\u00020\u00102\b\b\u0003\u0010\u0012\u001a\u00020\u00102\b\b\u0003\u0010\u0013\u001a\u00020\u00102\b\b\u0003\u0010\u0014\u001a\u00020\u00102\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\r2\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010oJ\u0014\u0010p\u001a\u00020\u00102\b\u0010q\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010r\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010s\u001a\u00020\nHÖ\u0081\u0004J%\u0010t\u001a\u00020u2\u0006\u0010v\u001a\u00020\u00002\u0006\u0010w\u001a\u00020x2\u0006\u0010y\u001a\u00020zH\u0001¢\u0006\u0002\b{R$\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R$\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b#\u0010\u001e\u001a\u0004\b$\u0010 \"\u0004\b%\u0010\"R$\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b&\u0010\u001e\u001a\u0004\b'\u0010 \"\u0004\b(\u0010\"R$\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b)\u0010\u001e\u001a\u0004\b*\u0010 \"\u0004\b+\u0010\"R$\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b,\u0010\u001e\u001a\u0004\b-\u0010 \"\u0004\b.\u0010\"R(\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u00104\u0012\u0004\b/\u0010\u001e\u001a\u0004\b0\u00101\"\u0004\b2\u00103R&\u0010\t\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b5\u0010\u001e\u001a\u0004\b6\u00107\"\u0004\b8\u00109R$\u0010\u000b\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b:\u0010\u001e\u001a\u0004\b;\u0010 \"\u0004\b<\u0010\"R(\u0010\f\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010B\u0012\u0004\b=\u0010\u001e\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR(\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010B\u0012\u0004\bC\u0010\u001e\u001a\u0004\bD\u0010?\"\u0004\bE\u0010AR$\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bF\u0010\u001e\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR$\u0010\u0011\u001a\u00020\u00108\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bK\u0010\u001e\u001a\u0004\bL\u0010H\"\u0004\bM\u0010JR$\u0010\u0012\u001a\u00020\u00108\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bN\u0010\u001e\u001a\u0004\bO\u0010H\"\u0004\bP\u0010JR$\u0010\u0013\u001a\u00020\u00108\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bQ\u0010\u001e\u001a\u0004\bR\u0010H\"\u0004\bS\u0010JR$\u0010\u0014\u001a\u00020\u00108\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bT\u0010\u001e\u001a\u0004\bU\u0010H\"\u0004\bV\u0010JR(\u0010\u0015\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010B\u0012\u0004\bW\u0010\u001e\u001a\u0004\bX\u0010?\"\u0004\bY\u0010AR(\u0010\u0016\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u00104\u0012\u0004\bZ\u0010\u001e\u001a\u0004\b[\u00101\"\u0004\b\\\u00103¨\u0006~"}, d2 = {"Lcom/lagradost/cloudstream3/ui/subtitles/SaveCaptionStyle;", "", "foregroundColor", "", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "windowColor", "edgeType", "edgeColor", "typeface", "typefaceFilePath", "", "elevation", "fixedTextSize", "", "edgeSize", "removeCaptions", "", "removeBloat", "upperCase", TtmlNode.BOLD, TtmlNode.ITALIC, "backgroundRadius", "alignment", "<init>", "(IIIIILjava/lang/Integer;Ljava/lang/String;ILjava/lang/Float;Ljava/lang/Float;ZZZZZLjava/lang/Float;Ljava/lang/Integer;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IIIIIILjava/lang/Integer;Ljava/lang/String;ILjava/lang/Float;Ljava/lang/Float;ZZZZZLjava/lang/Float;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getForegroundColor$annotations", "()V", "getForegroundColor", "()I", "setForegroundColor", "(I)V", "getBackgroundColor$annotations", "getBackgroundColor", "setBackgroundColor", "getWindowColor$annotations", "getWindowColor", "setWindowColor", "getEdgeType$annotations", "getEdgeType", "setEdgeType", "getEdgeColor$annotations", "getEdgeColor", "setEdgeColor", "getTypeface$annotations", "getTypeface", "()Ljava/lang/Integer;", "setTypeface", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getTypefaceFilePath$annotations", "getTypefaceFilePath", "()Ljava/lang/String;", "setTypefaceFilePath", "(Ljava/lang/String;)V", "getElevation$annotations", "getElevation", "setElevation", "getFixedTextSize$annotations", "getFixedTextSize", "()Ljava/lang/Float;", "setFixedTextSize", "(Ljava/lang/Float;)V", "Ljava/lang/Float;", "getEdgeSize$annotations", "getEdgeSize", "setEdgeSize", "getRemoveCaptions$annotations", "getRemoveCaptions", "()Z", "setRemoveCaptions", "(Z)V", "getRemoveBloat$annotations", "getRemoveBloat", "setRemoveBloat", "getUpperCase$annotations", "getUpperCase", "setUpperCase", "getBold$annotations", "getBold", "setBold", "getItalic$annotations", "getItalic", "setItalic", "getBackgroundRadius$annotations", "getBackgroundRadius", "setBackgroundRadius", "getAlignment$annotations", "getAlignment", "setAlignment", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "(IIIIILjava/lang/Integer;Ljava/lang/String;ILjava/lang/Float;Ljava/lang/Float;ZZZZZLjava/lang/Float;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/ui/subtitles/SaveCaptionStyle;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final /* data */ class SaveCaptionStyle {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JsonProperty("alignment")
    private Integer alignment;

    @JsonProperty(TtmlNode.ATTR_TTS_BACKGROUND_COLOR)
    private int backgroundColor;

    @JsonProperty("backgroundRadius")
    private Float backgroundRadius;

    @JsonProperty(TtmlNode.BOLD)
    private boolean bold;

    @JsonProperty("edgeColor")
    private int edgeColor;

    @JsonProperty("edgeSize")
    private Float edgeSize;

    @JsonProperty("edgeType")
    private int edgeType;

    @JsonProperty("elevation")
    private int elevation;

    @JsonProperty("fixedTextSize")
    private Float fixedTextSize;

    @JsonProperty("foregroundColor")
    private int foregroundColor;

    @JsonProperty(TtmlNode.ITALIC)
    private boolean italic;

    @JsonProperty("removeBloat")
    private boolean removeBloat;

    @JsonProperty("removeCaptions")
    private boolean removeCaptions;

    @JsonProperty("typeface")
    private Integer typeface;

    @JsonProperty("typefaceFilePath")
    private String typefaceFilePath;

    @JsonProperty("upperCase")
    private boolean upperCase;

    @JsonProperty("windowColor")
    private int windowColor;

    public static /* synthetic */ SaveCaptionStyle copy$default(SaveCaptionStyle saveCaptionStyle, int i, int i2, int i3, int i4, int i5, Integer num, String str, int i6, Float f, Float f2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, Float f3, Integer num2, int i7, Object obj) {
        Integer num3;
        Float f4;
        int i8 = (i7 & 1) != 0 ? saveCaptionStyle.foregroundColor : i;
        int i9 = (i7 & 2) != 0 ? saveCaptionStyle.backgroundColor : i2;
        int i10 = (i7 & 4) != 0 ? saveCaptionStyle.windowColor : i3;
        int i11 = (i7 & 8) != 0 ? saveCaptionStyle.edgeType : i4;
        int i12 = (i7 & 16) != 0 ? saveCaptionStyle.edgeColor : i5;
        Integer num4 = (i7 & 32) != 0 ? saveCaptionStyle.typeface : num;
        String str2 = (i7 & 64) != 0 ? saveCaptionStyle.typefaceFilePath : str;
        int i13 = (i7 & 128) != 0 ? saveCaptionStyle.elevation : i6;
        Float f5 = (i7 & 256) != 0 ? saveCaptionStyle.fixedTextSize : f;
        Float f6 = (i7 & 512) != 0 ? saveCaptionStyle.edgeSize : f2;
        boolean z6 = (i7 & 1024) != 0 ? saveCaptionStyle.removeCaptions : z;
        boolean z7 = (i7 & 2048) != 0 ? saveCaptionStyle.removeBloat : z2;
        boolean z8 = (i7 & 4096) != 0 ? saveCaptionStyle.upperCase : z3;
        boolean z9 = (i7 & 8192) != 0 ? saveCaptionStyle.bold : z4;
        int i14 = i8;
        boolean z10 = (i7 & 16384) != 0 ? saveCaptionStyle.italic : z5;
        Float f7 = (i7 & 32768) != 0 ? saveCaptionStyle.backgroundRadius : f3;
        if ((i7 & 65536) != 0) {
            f4 = f7;
            num3 = saveCaptionStyle.alignment;
        } else {
            num3 = num2;
            f4 = f7;
        }
        return saveCaptionStyle.copy(i14, i9, i10, i11, i12, num4, str2, i13, f5, f6, z6, z7, z8, z9, z10, f4, num3);
    }

    @SerialName("alignment")
    public static /* synthetic */ void getAlignment$annotations() {
    }

    @SerialName(TtmlNode.ATTR_TTS_BACKGROUND_COLOR)
    public static /* synthetic */ void getBackgroundColor$annotations() {
    }

    @SerialName("backgroundRadius")
    public static /* synthetic */ void getBackgroundRadius$annotations() {
    }

    @SerialName(TtmlNode.BOLD)
    public static /* synthetic */ void getBold$annotations() {
    }

    @SerialName("edgeColor")
    public static /* synthetic */ void getEdgeColor$annotations() {
    }

    @SerialName("edgeSize")
    public static /* synthetic */ void getEdgeSize$annotations() {
    }

    @SerialName("edgeType")
    public static /* synthetic */ void getEdgeType$annotations() {
    }

    @SerialName("elevation")
    public static /* synthetic */ void getElevation$annotations() {
    }

    @SerialName("fixedTextSize")
    public static /* synthetic */ void getFixedTextSize$annotations() {
    }

    @SerialName("foregroundColor")
    public static /* synthetic */ void getForegroundColor$annotations() {
    }

    @SerialName(TtmlNode.ITALIC)
    public static /* synthetic */ void getItalic$annotations() {
    }

    @SerialName("removeBloat")
    public static /* synthetic */ void getRemoveBloat$annotations() {
    }

    @SerialName("removeCaptions")
    public static /* synthetic */ void getRemoveCaptions$annotations() {
    }

    @SerialName("typeface")
    public static /* synthetic */ void getTypeface$annotations() {
    }

    @SerialName("typefaceFilePath")
    public static /* synthetic */ void getTypefaceFilePath$annotations() {
    }

    @SerialName("upperCase")
    public static /* synthetic */ void getUpperCase$annotations() {
    }

    @SerialName("windowColor")
    public static /* synthetic */ void getWindowColor$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getForegroundColor() {
        return this.foregroundColor;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Float getEdgeSize() {
        return this.edgeSize;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final boolean getRemoveCaptions() {
        return this.removeCaptions;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final boolean getRemoveBloat() {
        return this.removeBloat;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final boolean getUpperCase() {
        return this.upperCase;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final boolean getBold() {
        return this.bold;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final boolean getItalic() {
        return this.italic;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final Float getBackgroundRadius() {
        return this.backgroundRadius;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final Integer getAlignment() {
        return this.alignment;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getWindowColor() {
        return this.windowColor;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getEdgeType() {
        return this.edgeType;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getEdgeColor() {
        return this.edgeColor;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getTypeface() {
        return this.typeface;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getTypefaceFilePath() {
        return this.typefaceFilePath;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getElevation() {
        return this.elevation;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Float getFixedTextSize() {
        return this.fixedTextSize;
    }

    public final SaveCaptionStyle copy(@JsonProperty("foregroundColor") int foregroundColor, @JsonProperty(TtmlNode.ATTR_TTS_BACKGROUND_COLOR) int backgroundColor, @JsonProperty("windowColor") int windowColor, @JsonProperty("edgeType") int edgeType, @JsonProperty("edgeColor") int edgeColor, @JsonProperty("typeface") Integer typeface, @JsonProperty("typefaceFilePath") String typefaceFilePath, @JsonProperty("elevation") int elevation, @JsonProperty("fixedTextSize") Float fixedTextSize, @JsonProperty("edgeSize") Float edgeSize, @JsonProperty("removeCaptions") boolean removeCaptions, @JsonProperty("removeBloat") boolean removeBloat, @JsonProperty("upperCase") boolean upperCase, @JsonProperty(TtmlNode.BOLD) boolean bold, @JsonProperty(TtmlNode.ITALIC) boolean italic, @JsonProperty("backgroundRadius") Float backgroundRadius, @JsonProperty("alignment") Integer alignment) {
        return new SaveCaptionStyle(foregroundColor, backgroundColor, windowColor, edgeType, edgeColor, typeface, typefaceFilePath, elevation, fixedTextSize, edgeSize, removeCaptions, removeBloat, upperCase, bold, italic, backgroundRadius, alignment);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SaveCaptionStyle)) {
            return false;
        }
        SaveCaptionStyle saveCaptionStyle = (SaveCaptionStyle) other;
        return this.foregroundColor == saveCaptionStyle.foregroundColor && this.backgroundColor == saveCaptionStyle.backgroundColor && this.windowColor == saveCaptionStyle.windowColor && this.edgeType == saveCaptionStyle.edgeType && this.edgeColor == saveCaptionStyle.edgeColor && Intrinsics.areEqual(this.typeface, saveCaptionStyle.typeface) && Intrinsics.areEqual(this.typefaceFilePath, saveCaptionStyle.typefaceFilePath) && this.elevation == saveCaptionStyle.elevation && Intrinsics.areEqual((Object) this.fixedTextSize, (Object) saveCaptionStyle.fixedTextSize) && Intrinsics.areEqual((Object) this.edgeSize, (Object) saveCaptionStyle.edgeSize) && this.removeCaptions == saveCaptionStyle.removeCaptions && this.removeBloat == saveCaptionStyle.removeBloat && this.upperCase == saveCaptionStyle.upperCase && this.bold == saveCaptionStyle.bold && this.italic == saveCaptionStyle.italic && Intrinsics.areEqual((Object) this.backgroundRadius, (Object) saveCaptionStyle.backgroundRadius) && Intrinsics.areEqual(this.alignment, saveCaptionStyle.alignment);
    }

    public int hashCode() {
        int i = ((((((((this.foregroundColor * 31) + this.backgroundColor) * 31) + this.windowColor) * 31) + this.edgeType) * 31) + this.edgeColor) * 31;
        Integer num = this.typeface;
        int iHashCode = (i + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.typefaceFilePath;
        int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.elevation) * 31;
        Float f = this.fixedTextSize;
        int iHashCode3 = (iHashCode2 + (f == null ? 0 : f.hashCode())) * 31;
        Float f2 = this.edgeSize;
        int iHashCode4 = (((((((((((iHashCode3 + (f2 == null ? 0 : f2.hashCode())) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.removeCaptions)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.removeBloat)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.upperCase)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.bold)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.italic)) * 31;
        Float f3 = this.backgroundRadius;
        int iHashCode5 = (iHashCode4 + (f3 == null ? 0 : f3.hashCode())) * 31;
        Integer num2 = this.alignment;
        return iHashCode5 + (num2 != null ? num2.hashCode() : 0);
    }

    public String toString() {
        return "SaveCaptionStyle(foregroundColor=" + this.foregroundColor + ", backgroundColor=" + this.backgroundColor + ", windowColor=" + this.windowColor + ", edgeType=" + this.edgeType + ", edgeColor=" + this.edgeColor + ", typeface=" + this.typeface + ", typefaceFilePath=" + this.typefaceFilePath + ", elevation=" + this.elevation + ", fixedTextSize=" + this.fixedTextSize + ", edgeSize=" + this.edgeSize + ", removeCaptions=" + this.removeCaptions + ", removeBloat=" + this.removeBloat + ", upperCase=" + this.upperCase + ", bold=" + this.bold + ", italic=" + this.italic + ", backgroundRadius=" + this.backgroundRadius + ", alignment=" + this.alignment + ')';
    }

    /* JADX INFO: compiled from: SubtitlesFragment.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/ui/subtitles/SaveCaptionStyle$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/ui/subtitles/SaveCaptionStyle;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<SaveCaptionStyle> serializer() {
            return SaveCaptionStyle$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ SaveCaptionStyle(int i, int i2, int i3, int i4, int i5, int i6, Integer num, String str, int i7, Float f, Float f2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, Float f3, Integer num2, SerializationConstructorMarker serializationConstructorMarker) {
        if (511 != (i & 511)) {
            PluginExceptionsKt.throwMissingFieldException(i, 511, SaveCaptionStyle$$serializer.INSTANCE.getDescriptor());
        }
        this.foregroundColor = i2;
        this.backgroundColor = i3;
        this.windowColor = i4;
        this.edgeType = i5;
        this.edgeColor = i6;
        this.typeface = num;
        this.typefaceFilePath = str;
        this.elevation = i7;
        this.fixedTextSize = f;
        if ((i & 512) == 0) {
            this.edgeSize = null;
        } else {
            this.edgeSize = f2;
        }
        if ((i & 1024) == 0) {
            this.removeCaptions = false;
        } else {
            this.removeCaptions = z;
        }
        if ((i & 2048) == 0) {
            this.removeBloat = true;
        } else {
            this.removeBloat = z2;
        }
        if ((i & 4096) == 0) {
            this.upperCase = false;
        } else {
            this.upperCase = z3;
        }
        if ((i & 8192) == 0) {
            this.bold = false;
        } else {
            this.bold = z4;
        }
        if ((i & 16384) == 0) {
            this.italic = false;
        } else {
            this.italic = z5;
        }
        if ((32768 & i) == 0) {
            this.backgroundRadius = null;
        } else {
            this.backgroundRadius = f3;
        }
        if ((i & 65536) == 0) {
            this.alignment = null;
        } else {
            this.alignment = num2;
        }
    }

    public SaveCaptionStyle(@JsonProperty("foregroundColor") int i, @JsonProperty(TtmlNode.ATTR_TTS_BACKGROUND_COLOR) int i2, @JsonProperty("windowColor") int i3, @JsonProperty("edgeType") int i4, @JsonProperty("edgeColor") int i5, @JsonProperty("typeface") Integer num, @JsonProperty("typefaceFilePath") String str, @JsonProperty("elevation") int i6, @JsonProperty("fixedTextSize") Float f, @JsonProperty("edgeSize") Float f2, @JsonProperty("removeCaptions") boolean z, @JsonProperty("removeBloat") boolean z2, @JsonProperty("upperCase") boolean z3, @JsonProperty(TtmlNode.BOLD) boolean z4, @JsonProperty(TtmlNode.ITALIC) boolean z5, @JsonProperty("backgroundRadius") Float f3, @JsonProperty("alignment") Integer num2) {
        this.foregroundColor = i;
        this.backgroundColor = i2;
        this.windowColor = i3;
        this.edgeType = i4;
        this.edgeColor = i5;
        this.typeface = num;
        this.typefaceFilePath = str;
        this.elevation = i6;
        this.fixedTextSize = f;
        this.edgeSize = f2;
        this.removeCaptions = z;
        this.removeBloat = z2;
        this.upperCase = z3;
        this.bold = z4;
        this.italic = z5;
        this.backgroundRadius = f3;
        this.alignment = num2;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app(SaveCaptionStyle self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeIntElement(serialDesc, 0, self.foregroundColor);
        output.encodeIntElement(serialDesc, 1, self.backgroundColor);
        output.encodeIntElement(serialDesc, 2, self.windowColor);
        output.encodeIntElement(serialDesc, 3, self.edgeType);
        output.encodeIntElement(serialDesc, 4, self.edgeColor);
        output.encodeNullableSerializableElement(serialDesc, 5, IntSerializer.INSTANCE, self.typeface);
        output.encodeNullableSerializableElement(serialDesc, 6, StringSerializer.INSTANCE, self.typefaceFilePath);
        output.encodeIntElement(serialDesc, 7, self.elevation);
        output.encodeNullableSerializableElement(serialDesc, 8, FloatSerializer.INSTANCE, self.fixedTextSize);
        if (output.shouldEncodeElementDefault(serialDesc, 9) || self.edgeSize != null) {
            output.encodeNullableSerializableElement(serialDesc, 9, FloatSerializer.INSTANCE, self.edgeSize);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 10) || self.removeCaptions) {
            output.encodeBooleanElement(serialDesc, 10, self.removeCaptions);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 11) || !self.removeBloat) {
            output.encodeBooleanElement(serialDesc, 11, self.removeBloat);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 12) || self.upperCase) {
            output.encodeBooleanElement(serialDesc, 12, self.upperCase);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 13) || self.bold) {
            output.encodeBooleanElement(serialDesc, 13, self.bold);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 14) || self.italic) {
            output.encodeBooleanElement(serialDesc, 14, self.italic);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 15) || self.backgroundRadius != null) {
            output.encodeNullableSerializableElement(serialDesc, 15, FloatSerializer.INSTANCE, self.backgroundRadius);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 16) && self.alignment == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 16, IntSerializer.INSTANCE, self.alignment);
    }

    public /* synthetic */ SaveCaptionStyle(int i, int i2, int i3, int i4, int i5, Integer num, String str, int i6, Float f, Float f2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, Float f3, Integer num2, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, i4, i5, num, str, i6, f, (i7 & 512) != 0 ? null : f2, (i7 & 1024) != 0 ? false : z, (i7 & 2048) != 0 ? true : z2, (i7 & 4096) != 0 ? false : z3, (i7 & 8192) != 0 ? false : z4, (i7 & 16384) != 0 ? false : z5, (32768 & i7) != 0 ? null : f3, (i7 & 65536) != 0 ? null : num2);
    }

    public final int getForegroundColor() {
        return this.foregroundColor;
    }

    public final void setForegroundColor(int i) {
        this.foregroundColor = i;
    }

    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    public final void setBackgroundColor(int i) {
        this.backgroundColor = i;
    }

    public final int getWindowColor() {
        return this.windowColor;
    }

    public final void setWindowColor(int i) {
        this.windowColor = i;
    }

    public final int getEdgeType() {
        return this.edgeType;
    }

    public final void setEdgeType(int i) {
        this.edgeType = i;
    }

    public final int getEdgeColor() {
        return this.edgeColor;
    }

    public final void setEdgeColor(int i) {
        this.edgeColor = i;
    }

    public final Integer getTypeface() {
        return this.typeface;
    }

    public final void setTypeface(Integer num) {
        this.typeface = num;
    }

    public final String getTypefaceFilePath() {
        return this.typefaceFilePath;
    }

    public final void setTypefaceFilePath(String str) {
        this.typefaceFilePath = str;
    }

    public final int getElevation() {
        return this.elevation;
    }

    public final void setElevation(int i) {
        this.elevation = i;
    }

    public final Float getFixedTextSize() {
        return this.fixedTextSize;
    }

    public final void setFixedTextSize(Float f) {
        this.fixedTextSize = f;
    }

    public final Float getEdgeSize() {
        return this.edgeSize;
    }

    public final void setEdgeSize(Float f) {
        this.edgeSize = f;
    }

    public final boolean getRemoveCaptions() {
        return this.removeCaptions;
    }

    public final void setRemoveCaptions(boolean z) {
        this.removeCaptions = z;
    }

    public final boolean getRemoveBloat() {
        return this.removeBloat;
    }

    public final void setRemoveBloat(boolean z) {
        this.removeBloat = z;
    }

    public final boolean getUpperCase() {
        return this.upperCase;
    }

    public final void setUpperCase(boolean z) {
        this.upperCase = z;
    }

    public final boolean getBold() {
        return this.bold;
    }

    public final void setBold(boolean z) {
        this.bold = z;
    }

    public final boolean getItalic() {
        return this.italic;
    }

    public final void setItalic(boolean z) {
        this.italic = z;
    }

    public final Float getBackgroundRadius() {
        return this.backgroundRadius;
    }

    public final void setBackgroundRadius(Float f) {
        this.backgroundRadius = f;
    }

    public final Integer getAlignment() {
        return this.alignment;
    }

    public final void setAlignment(Integer num) {
        this.alignment = num;
    }
}
