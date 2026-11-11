package androidx.compose.ui.platform;

import android.os.Parcel;
import android.util.Base64;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.AndroidColor_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.unit.TextUnitType;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: AndroidClipboardManager.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\r\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\rJ\u0006\u0010\u0011\u001a\u00020\u0012J\r\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0018¢\u0006\u0004\b\u0019\u0010\u0016J\u000f\u0010\u001a\u001a\u00020\u001bH\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020'H\u0002J\u000f\u0010(\u001a\u00020)H\u0002¢\u0006\u0004\b*\u0010\rJ\b\u0010+\u001a\u00020,H\u0002J\n\u0010-\u001a\u0004\u0018\u00010\u0003H\u0002J\b\u0010.\u001a\u00020'H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Landroidx/compose/ui/platform/DecodeHelper;", "", TypedValues.Custom.S_STRING, "", "<init>", "(Ljava/lang/String;)V", "parcel", "Landroid/os/Parcel;", "decodeSpanStyle", "Landroidx/compose/ui/text/SpanStyle;", "decodeColor", "Landroidx/compose/ui/graphics/Color;", "decodeColor-0d7_KjU", "()J", "decodeTextUnit", "Landroidx/compose/ui/unit/TextUnit;", "decodeTextUnit-XSAIIZE", "decodeFontWeight", "Landroidx/compose/ui/text/font/FontWeight;", "decodeFontStyle", "Landroidx/compose/ui/text/font/FontStyle;", "decodeFontStyle-_-LCdwA", "()I", "decodeFontSynthesis", "Landroidx/compose/ui/text/font/FontSynthesis;", "decodeFontSynthesis-GVVA2EU", "decodeBaselineShift", "Landroidx/compose/ui/text/style/BaselineShift;", "decodeBaselineShift-y9eOQZs", "()F", "decodeTextGeometricTransform", "Landroidx/compose/ui/text/style/TextGeometricTransform;", "decodeTextDecoration", "Landroidx/compose/ui/text/style/TextDecoration;", "decodeShadow", "Landroidx/compose/ui/graphics/Shadow;", "decodeByte", "", "decodeInt", "", "decodeULong", "Lkotlin/ULong;", "decodeULong-s-VKNKU", "decodeFloat", "", "decodeString", "dataAvailable", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DecodeHelper {
    public static final int $stable = 8;
    private final Parcel parcel;

    public DecodeHelper(String str) {
        Parcel parcelObtain = Parcel.obtain();
        this.parcel = parcelObtain;
        byte[] bArrDecode = Base64.decode(str, 0);
        parcelObtain.unmarshall(bArrDecode, 0, bArrDecode.length);
        parcelObtain.setDataPosition(0);
    }

    public final SpanStyle decodeSpanStyle() {
        MutableSpanStyle mutableSpanStyle = new MutableSpanStyle(0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, 16383, null);
        while (this.parcel.dataAvail() > 1) {
            byte bDecodeByte = decodeByte();
            if (bDecodeByte != 1) {
                if (bDecodeByte != 2) {
                    if (bDecodeByte != 3) {
                        if (bDecodeByte != 4) {
                            if (bDecodeByte != 5) {
                                if (bDecodeByte != 6) {
                                    if (bDecodeByte != 7) {
                                        if (bDecodeByte != 8) {
                                            if (bDecodeByte != 9) {
                                                if (bDecodeByte != 10) {
                                                    if (bDecodeByte != 11) {
                                                        if (bDecodeByte == 12) {
                                                            if (dataAvailable() < 20) {
                                                                break;
                                                            }
                                                            mutableSpanStyle.setShadow(decodeShadow());
                                                        } else {
                                                            continue;
                                                        }
                                                    } else {
                                                        if (dataAvailable() < 4) {
                                                            break;
                                                        }
                                                        mutableSpanStyle.setTextDecoration(decodeTextDecoration());
                                                    }
                                                } else {
                                                    if (dataAvailable() < 8) {
                                                        break;
                                                    }
                                                    mutableSpanStyle.m6070setBackground8_81llA(m6009decodeColor0d7_KjU());
                                                }
                                            } else {
                                                if (dataAvailable() < 8) {
                                                    break;
                                                }
                                                mutableSpanStyle.setTextGeometricTransform(decodeTextGeometricTransform());
                                            }
                                        } else {
                                            if (dataAvailable() < 4) {
                                                break;
                                            }
                                            mutableSpanStyle.m6071setBaselineShift_isdbwI(BaselineShift.m6636boximpl(m6007decodeBaselineShifty9eOQZs()));
                                        }
                                    } else {
                                        if (dataAvailable() < 5) {
                                            break;
                                        }
                                        mutableSpanStyle.m6076setLetterSpacingR2X_6o(m6012decodeTextUnitXSAIIZE());
                                    }
                                } else {
                                    mutableSpanStyle.setFontFeatureSettings(decodeString());
                                }
                            } else {
                                if (dataAvailable() < 1) {
                                    break;
                                }
                                mutableSpanStyle.m6075setFontSynthesistDdu0R4(FontSynthesis.m6466boximpl(m6011decodeFontSynthesisGVVA2EU()));
                            }
                        } else {
                            if (dataAvailable() < 1) {
                                break;
                            }
                            mutableSpanStyle.m6074setFontStylemLjRB2g(FontStyle.m6455boximpl(m6010decodeFontStyle_LCdwA()));
                        }
                    } else {
                        if (dataAvailable() < 4) {
                            break;
                        }
                        mutableSpanStyle.setFontWeight(decodeFontWeight());
                    }
                } else {
                    if (dataAvailable() < 5) {
                        break;
                    }
                    mutableSpanStyle.m6073setFontSizeR2X_6o(m6012decodeTextUnitXSAIIZE());
                }
            } else {
                if (dataAvailable() < 8) {
                    break;
                }
                mutableSpanStyle.m6072setColor8_81llA(m6009decodeColor0d7_KjU());
            }
        }
        return mutableSpanStyle.toSpanStyle();
    }

    /* JADX INFO: renamed from: decodeColor-0d7_KjU, reason: not valid java name */
    public final long m6009decodeColor0d7_KjU() {
        return AndroidColor_androidKt.fromColorLong(Color.INSTANCE, this.parcel.readLong());
    }

    /* JADX INFO: renamed from: decodeTextUnit-XSAIIZE, reason: not valid java name */
    public final long m6012decodeTextUnitXSAIIZE() {
        long jM7122getUnspecifiedUIouoOA;
        byte bDecodeByte = decodeByte();
        if (bDecodeByte == 1) {
            jM7122getUnspecifiedUIouoOA = TextUnitType.INSTANCE.m7121getSpUIouoOA();
        } else if (bDecodeByte == 2) {
            jM7122getUnspecifiedUIouoOA = TextUnitType.INSTANCE.m7120getEmUIouoOA();
        } else {
            jM7122getUnspecifiedUIouoOA = TextUnitType.INSTANCE.m7122getUnspecifiedUIouoOA();
        }
        if (TextUnitType.m7116equalsimpl0(jM7122getUnspecifiedUIouoOA, TextUnitType.INSTANCE.m7122getUnspecifiedUIouoOA())) {
            return TextUnit.INSTANCE.m7099getUnspecifiedXSAIIZE();
        }
        return TextUnitKt.m7100TextUnitanM5pPY(decodeFloat(), jM7122getUnspecifiedUIouoOA);
    }

    public final FontWeight decodeFontWeight() {
        return new FontWeight(decodeInt());
    }

    /* JADX INFO: renamed from: decodeFontStyle-_-LCdwA, reason: not valid java name */
    public final int m6010decodeFontStyle_LCdwA() {
        byte bDecodeByte = decodeByte();
        if (bDecodeByte == 0) {
            return FontStyle.INSTANCE.m6465getNormal_LCdwA();
        }
        if (bDecodeByte == 1) {
            return FontStyle.INSTANCE.m6464getItalic_LCdwA();
        }
        return FontStyle.INSTANCE.m6465getNormal_LCdwA();
    }

    /* JADX INFO: renamed from: decodeFontSynthesis-GVVA2EU, reason: not valid java name */
    public final int m6011decodeFontSynthesisGVVA2EU() {
        byte bDecodeByte = decodeByte();
        if (bDecodeByte == 0) {
            return FontSynthesis.INSTANCE.m6476getNoneGVVA2EU();
        }
        if (bDecodeByte == 1) {
            return FontSynthesis.INSTANCE.m6475getAllGVVA2EU();
        }
        if (bDecodeByte == 3) {
            return FontSynthesis.INSTANCE.m6477getStyleGVVA2EU();
        }
        if (bDecodeByte == 2) {
            return FontSynthesis.INSTANCE.m6478getWeightGVVA2EU();
        }
        return FontSynthesis.INSTANCE.m6476getNoneGVVA2EU();
    }

    /* JADX INFO: renamed from: decodeBaselineShift-y9eOQZs, reason: not valid java name */
    private final float m6007decodeBaselineShifty9eOQZs() {
        return BaselineShift.m6637constructorimpl(decodeFloat());
    }

    private final TextGeometricTransform decodeTextGeometricTransform() {
        return new TextGeometricTransform(decodeFloat(), decodeFloat());
    }

    private final TextDecoration decodeTextDecoration() {
        int iDecodeInt = decodeInt();
        boolean z = (TextDecoration.INSTANCE.getLineThrough().getMask() & iDecodeInt) != 0;
        boolean z2 = (iDecodeInt & TextDecoration.INSTANCE.getUnderline().getMask()) != 0;
        if (z && z2) {
            return TextDecoration.INSTANCE.combine(CollectionsKt.listOf((Object[]) new TextDecoration[]{TextDecoration.INSTANCE.getLineThrough(), TextDecoration.INSTANCE.getUnderline()}));
        }
        if (z) {
            return TextDecoration.INSTANCE.getLineThrough();
        }
        if (z2) {
            return TextDecoration.INSTANCE.getUnderline();
        }
        return TextDecoration.INSTANCE.getNone();
    }

    private final Shadow decodeShadow() {
        long jM6009decodeColor0d7_KjU = m6009decodeColor0d7_KjU();
        float fDecodeFloat = decodeFloat();
        return new Shadow(jM6009decodeColor0d7_KjU, Offset.m3862constructorimpl((((long) Float.floatToRawIntBits(decodeFloat())) & 4294967295L) | (Float.floatToRawIntBits(fDecodeFloat) << 32)), decodeFloat(), null);
    }

    private final byte decodeByte() {
        return this.parcel.readByte();
    }

    private final int decodeInt() {
        return this.parcel.readInt();
    }

    /* JADX INFO: renamed from: decodeULong-s-VKNKU, reason: not valid java name */
    private final long m6008decodeULongsVKNKU() {
        return ULong.m9121constructorimpl(this.parcel.readLong());
    }

    private final float decodeFloat() {
        return this.parcel.readFloat();
    }

    private final String decodeString() {
        return this.parcel.readString();
    }

    private final int dataAvailable() {
        return this.parcel.dataAvail();
    }
}
