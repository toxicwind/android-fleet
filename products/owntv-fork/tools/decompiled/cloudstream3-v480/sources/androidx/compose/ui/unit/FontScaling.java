package androidx.compose.ui.unit;

import androidx.compose.ui.unit.fontscaling.FontScaleConverter;
import androidx.compose.ui.unit.fontscaling.FontScaleConverterFactory;
import kotlin.Metadata;

/* JADX INFO: compiled from: FontScaling.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0013\u0010\b\u001a\u00020\t*\u00020\nH\u0017¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\n*\u00020\tH\u0017¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/unit/FontScaling;", "", "fontScale", "", "getFontScale$annotations", "()V", "getFontScale", "()F", "toSp", "Landroidx/compose/ui/unit/TextUnit;", "Landroidx/compose/ui/unit/Dp;", "toSp-0xMU5do", "(F)J", "toDp", "toDp-GaN1DYA", "(J)F", "ui-unit"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface FontScaling {
    float getFontScale();

    /* JADX INFO: renamed from: toDp-GaN1DYA */
    float mo401toDpGaN1DYA(long j);

    /* JADX INFO: renamed from: toSp-0xMU5do */
    long mo408toSp0xMU5do(float f);

    /* JADX INFO: renamed from: androidx.compose.ui.unit.FontScaling$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: FontScaling.android.kt */
    public final /* synthetic */ class CC {
        /* JADX INFO: renamed from: $default$toSp-0xMU5do, reason: not valid java name */
        public static long m7000$default$toSp0xMU5do(FontScaling _this, float f) {
            if (!FontScaleConverterFactory.INSTANCE.isNonLinearFontScalingActive(_this.getFontScale())) {
                return TextUnitKt.getSp(f / _this.getFontScale());
            }
            FontScaleConverter fontScaleConverterForScale = FontScaleConverterFactory.INSTANCE.forScale(_this.getFontScale());
            return TextUnitKt.getSp(fontScaleConverterForScale != null ? fontScaleConverterForScale.convertDpToSp(f) : f / _this.getFontScale());
        }

        /* JADX INFO: renamed from: $default$toDp-GaN1DYA, reason: not valid java name */
        public static float m6999$default$toDpGaN1DYA(FontScaling _this, long j) {
            if (!TextUnitType.m7116equalsimpl0(TextUnit.m7087getTypeUIouoOA(j), TextUnitType.INSTANCE.m7121getSpUIouoOA())) {
                InlineClassHelperKt.throwIllegalStateException("Only Sp can convert to Px");
            }
            if (!FontScaleConverterFactory.INSTANCE.isNonLinearFontScalingActive(_this.getFontScale())) {
                return Dp.m6890constructorimpl(TextUnit.m7088getValueimpl(j) * _this.getFontScale());
            }
            FontScaleConverter fontScaleConverterForScale = FontScaleConverterFactory.INSTANCE.forScale(_this.getFontScale());
            return fontScaleConverterForScale == null ? Dp.m6890constructorimpl(TextUnit.m7088getValueimpl(j) * _this.getFontScale()) : Dp.m6890constructorimpl(fontScaleConverterForScale.convertSpToDp(TextUnit.m7088getValueimpl(j)));
        }
    }

    /* JADX INFO: compiled from: FontScaling.android.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void getFontScale$annotations() {
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m7004toSp0xMU5do(FontScaling fontScaling, float f) {
            return CC.m7000$default$toSp0xMU5do(fontScaling, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m7003toDpGaN1DYA(FontScaling fontScaling, long j) {
            return CC.m6999$default$toDpGaN1DYA(fontScaling, j);
        }
    }
}
