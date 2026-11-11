package com.lagradost.cloudstream3.ui.subtitles;

import androidx.core.view.ViewCompat;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fasterxml.jackson.annotation.JsonProperty;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: ChromecastSubtitlesFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b.\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 M2\u00020\u0001:\u0002LMB[\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0003\u0010\b\u001a\u00020\u0005\u0012\b\b\u0003\u0010\t\u001a\u00020\u0005\u0012\b\b\u0003\u0010\n\u001a\u00020\u000b\u0012\b\b\u0003\u0010\f\u001a\u00020\u0005¢\u0006\u0004\b\r\u0010\u000eB_\b\u0010\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\r\u0010\u0012J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00106\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001bJ\t\u00107\u001a\u00020\u0005HÆ\u0003J\t\u00108\u001a\u00020\u0005HÆ\u0003J\t\u00109\u001a\u00020\u0005HÆ\u0003J\t\u0010:\u001a\u00020\u0005HÆ\u0003J\t\u0010;\u001a\u00020\u000bHÆ\u0003J\t\u0010<\u001a\u00020\u0005HÆ\u0003Jb\u0010=\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00052\b\b\u0003\u0010\b\u001a\u00020\u00052\b\b\u0003\u0010\t\u001a\u00020\u00052\b\b\u0003\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\f\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010>J\u0014\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010B\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010C\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020\u00002\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020JH\u0001¢\u0006\u0002\bKR&\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R(\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u001e\u0012\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR$\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001f\u0010\u0014\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R$\u0010\u0007\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b$\u0010\u0014\u001a\u0004\b%\u0010!\"\u0004\b&\u0010#R$\u0010\b\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b'\u0010\u0014\u001a\u0004\b(\u0010!\"\u0004\b)\u0010#R$\u0010\t\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b*\u0010\u0014\u001a\u0004\b+\u0010!\"\u0004\b,\u0010#R$\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b-\u0010\u0014\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R$\u0010\f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b2\u0010\u0014\u001a\u0004\b3\u0010!\"\u0004\b4\u0010#¨\u0006N"}, d2 = {"Lcom/lagradost/cloudstream3/ui/subtitles/SaveChromeCaptionStyle;", "", TtmlNode.ATTR_TTS_FONT_FAMILY, "", "fontGenericFamily", "", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "edgeColor", "edgeType", "foregroundColor", "fontScale", "", "windowColor", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;IIIIFI)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/Integer;IIIIFILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getFontFamily$annotations", "()V", "getFontFamily", "()Ljava/lang/String;", "setFontFamily", "(Ljava/lang/String;)V", "getFontGenericFamily$annotations", "getFontGenericFamily", "()Ljava/lang/Integer;", "setFontGenericFamily", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getBackgroundColor$annotations", "getBackgroundColor", "()I", "setBackgroundColor", "(I)V", "getEdgeColor$annotations", "getEdgeColor", "setEdgeColor", "getEdgeType$annotations", "getEdgeType", "setEdgeType", "getForegroundColor$annotations", "getForegroundColor", "setForegroundColor", "getFontScale$annotations", "getFontScale", "()F", "setFontScale", "(F)V", "getWindowColor$annotations", "getWindowColor", "setWindowColor", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/Integer;IIIIFI)Lcom/lagradost/cloudstream3/ui/subtitles/SaveChromeCaptionStyle;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final /* data */ class SaveChromeCaptionStyle {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JsonProperty(TtmlNode.ATTR_TTS_BACKGROUND_COLOR)
    private int backgroundColor;

    @JsonProperty("edgeColor")
    private int edgeColor;

    @JsonProperty("edgeType")
    private int edgeType;

    @JsonProperty(TtmlNode.ATTR_TTS_FONT_FAMILY)
    private String fontFamily;

    @JsonProperty("fontGenericFamily")
    private Integer fontGenericFamily;

    @JsonProperty("fontScale")
    private float fontScale;

    @JsonProperty("foregroundColor")
    private int foregroundColor;

    @JsonProperty("windowColor")
    private int windowColor;

    public SaveChromeCaptionStyle() {
        this((String) null, (Integer) null, 0, 0, 0, 0, 0.0f, 0, 255, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ SaveChromeCaptionStyle copy$default(SaveChromeCaptionStyle saveChromeCaptionStyle, String str, Integer num, int i, int i2, int i3, int i4, float f, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            str = saveChromeCaptionStyle.fontFamily;
        }
        if ((i6 & 2) != 0) {
            num = saveChromeCaptionStyle.fontGenericFamily;
        }
        if ((i6 & 4) != 0) {
            i = saveChromeCaptionStyle.backgroundColor;
        }
        if ((i6 & 8) != 0) {
            i2 = saveChromeCaptionStyle.edgeColor;
        }
        if ((i6 & 16) != 0) {
            i3 = saveChromeCaptionStyle.edgeType;
        }
        if ((i6 & 32) != 0) {
            i4 = saveChromeCaptionStyle.foregroundColor;
        }
        if ((i6 & 64) != 0) {
            f = saveChromeCaptionStyle.fontScale;
        }
        if ((i6 & 128) != 0) {
            i5 = saveChromeCaptionStyle.windowColor;
        }
        float f2 = f;
        int i7 = i5;
        int i8 = i3;
        int i9 = i4;
        return saveChromeCaptionStyle.copy(str, num, i, i2, i8, i9, f2, i7);
    }

    @SerialName(TtmlNode.ATTR_TTS_BACKGROUND_COLOR)
    public static /* synthetic */ void getBackgroundColor$annotations() {
    }

    @SerialName("edgeColor")
    public static /* synthetic */ void getEdgeColor$annotations() {
    }

    @SerialName("edgeType")
    public static /* synthetic */ void getEdgeType$annotations() {
    }

    @SerialName(TtmlNode.ATTR_TTS_FONT_FAMILY)
    public static /* synthetic */ void getFontFamily$annotations() {
    }

    @SerialName("fontGenericFamily")
    public static /* synthetic */ void getFontGenericFamily$annotations() {
    }

    @SerialName("fontScale")
    public static /* synthetic */ void getFontScale$annotations() {
    }

    @SerialName("foregroundColor")
    public static /* synthetic */ void getForegroundColor$annotations() {
    }

    @SerialName("windowColor")
    public static /* synthetic */ void getWindowColor$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getFontFamily() {
        return this.fontFamily;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getFontGenericFamily() {
        return this.fontGenericFamily;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getEdgeColor() {
        return this.edgeColor;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getEdgeType() {
        return this.edgeType;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getForegroundColor() {
        return this.foregroundColor;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final float getFontScale() {
        return this.fontScale;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getWindowColor() {
        return this.windowColor;
    }

    public final SaveChromeCaptionStyle copy(@JsonProperty(TtmlNode.ATTR_TTS_FONT_FAMILY) String fontFamily, @JsonProperty("fontGenericFamily") Integer fontGenericFamily, @JsonProperty(TtmlNode.ATTR_TTS_BACKGROUND_COLOR) int backgroundColor, @JsonProperty("edgeColor") int edgeColor, @JsonProperty("edgeType") int edgeType, @JsonProperty("foregroundColor") int foregroundColor, @JsonProperty("fontScale") float fontScale, @JsonProperty("windowColor") int windowColor) {
        return new SaveChromeCaptionStyle(fontFamily, fontGenericFamily, backgroundColor, edgeColor, edgeType, foregroundColor, fontScale, windowColor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SaveChromeCaptionStyle)) {
            return false;
        }
        SaveChromeCaptionStyle saveChromeCaptionStyle = (SaveChromeCaptionStyle) other;
        return Intrinsics.areEqual(this.fontFamily, saveChromeCaptionStyle.fontFamily) && Intrinsics.areEqual(this.fontGenericFamily, saveChromeCaptionStyle.fontGenericFamily) && this.backgroundColor == saveChromeCaptionStyle.backgroundColor && this.edgeColor == saveChromeCaptionStyle.edgeColor && this.edgeType == saveChromeCaptionStyle.edgeType && this.foregroundColor == saveChromeCaptionStyle.foregroundColor && Float.compare(this.fontScale, saveChromeCaptionStyle.fontScale) == 0 && this.windowColor == saveChromeCaptionStyle.windowColor;
    }

    public int hashCode() {
        String str = this.fontFamily;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.fontGenericFamily;
        return ((((((((((((iHashCode + (num != null ? num.hashCode() : 0)) * 31) + this.backgroundColor) * 31) + this.edgeColor) * 31) + this.edgeType) * 31) + this.foregroundColor) * 31) + Float.floatToIntBits(this.fontScale)) * 31) + this.windowColor;
    }

    public String toString() {
        return "SaveChromeCaptionStyle(fontFamily=" + this.fontFamily + ", fontGenericFamily=" + this.fontGenericFamily + ", backgroundColor=" + this.backgroundColor + ", edgeColor=" + this.edgeColor + ", edgeType=" + this.edgeType + ", foregroundColor=" + this.foregroundColor + ", fontScale=" + this.fontScale + ", windowColor=" + this.windowColor + ')';
    }

    /* JADX INFO: compiled from: ChromecastSubtitlesFragment.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/ui/subtitles/SaveChromeCaptionStyle$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/ui/subtitles/SaveChromeCaptionStyle;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<SaveChromeCaptionStyle> serializer() {
            return SaveChromeCaptionStyle$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ SaveChromeCaptionStyle(int i, String str, Integer num, int i2, int i3, int i4, int i5, float f, int i6, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) == 0) {
            this.fontFamily = null;
        } else {
            this.fontFamily = str;
        }
        if ((i & 2) == 0) {
            this.fontGenericFamily = null;
        } else {
            this.fontGenericFamily = num;
        }
        if ((i & 4) == 0) {
            this.backgroundColor = ViewCompat.MEASURED_SIZE_MASK;
        } else {
            this.backgroundColor = i2;
        }
        if ((i & 8) == 0) {
            this.edgeColor = -16777216;
        } else {
            this.edgeColor = i3;
        }
        if ((i & 16) == 0) {
            this.edgeType = 1;
        } else {
            this.edgeType = i4;
        }
        if ((i & 32) == 0) {
            this.foregroundColor = -1;
        } else {
            this.foregroundColor = i5;
        }
        if ((i & 64) == 0) {
            this.fontScale = 1.05f;
        } else {
            this.fontScale = f;
        }
        if ((i & 128) == 0) {
            this.windowColor = 0;
        } else {
            this.windowColor = i6;
        }
    }

    public SaveChromeCaptionStyle(@JsonProperty(TtmlNode.ATTR_TTS_FONT_FAMILY) String str, @JsonProperty("fontGenericFamily") Integer num, @JsonProperty(TtmlNode.ATTR_TTS_BACKGROUND_COLOR) int i, @JsonProperty("edgeColor") int i2, @JsonProperty("edgeType") int i3, @JsonProperty("foregroundColor") int i4, @JsonProperty("fontScale") float f, @JsonProperty("windowColor") int i5) {
        this.fontFamily = str;
        this.fontGenericFamily = num;
        this.backgroundColor = i;
        this.edgeColor = i2;
        this.edgeType = i3;
        this.foregroundColor = i4;
        this.fontScale = f;
        this.windowColor = i5;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app(SaveChromeCaptionStyle self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.fontFamily != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.fontFamily);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.fontGenericFamily != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.fontGenericFamily);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.backgroundColor != 16777215) {
            output.encodeIntElement(serialDesc, 2, self.backgroundColor);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.edgeColor != -16777216) {
            output.encodeIntElement(serialDesc, 3, self.edgeColor);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.edgeType != 1) {
            output.encodeIntElement(serialDesc, 4, self.edgeType);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.foregroundColor != -1) {
            output.encodeIntElement(serialDesc, 5, self.foregroundColor);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || Float.compare(self.fontScale, 1.05f) != 0) {
            output.encodeFloatElement(serialDesc, 6, self.fontScale);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 7) && self.windowColor == 0) {
            return;
        }
        output.encodeIntElement(serialDesc, 7, self.windowColor);
    }

    public /* synthetic */ SaveChromeCaptionStyle(String str, Integer num, int i, int i2, int i3, int i4, float f, int i5, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this((i6 & 1) != 0 ? null : str, (i6 & 2) != 0 ? null : num, (i6 & 4) != 0 ? ViewCompat.MEASURED_SIZE_MASK : i, (i6 & 8) != 0 ? -16777216 : i2, (i6 & 16) != 0 ? 1 : i3, (i6 & 32) != 0 ? -1 : i4, (i6 & 64) != 0 ? 1.05f : f, (i6 & 128) != 0 ? 0 : i5);
    }

    public final String getFontFamily() {
        return this.fontFamily;
    }

    public final void setFontFamily(String str) {
        this.fontFamily = str;
    }

    public final Integer getFontGenericFamily() {
        return this.fontGenericFamily;
    }

    public final void setFontGenericFamily(Integer num) {
        this.fontGenericFamily = num;
    }

    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    public final void setBackgroundColor(int i) {
        this.backgroundColor = i;
    }

    public final int getEdgeColor() {
        return this.edgeColor;
    }

    public final void setEdgeColor(int i) {
        this.edgeColor = i;
    }

    public final int getEdgeType() {
        return this.edgeType;
    }

    public final void setEdgeType(int i) {
        this.edgeType = i;
    }

    public final int getForegroundColor() {
        return this.foregroundColor;
    }

    public final void setForegroundColor(int i) {
        this.foregroundColor = i;
    }

    public final float getFontScale() {
        return this.fontScale;
    }

    public final void setFontScale(float f) {
        this.fontScale = f;
    }

    public final int getWindowColor() {
        return this.windowColor;
    }

    public final void setWindowColor(int i) {
        this.windowColor = i;
    }
}
