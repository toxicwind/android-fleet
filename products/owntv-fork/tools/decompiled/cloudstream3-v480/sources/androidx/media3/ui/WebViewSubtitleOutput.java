package androidx.media3.ui;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.common.base.Preconditions;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
final class WebViewSubtitleOutput extends FrameLayout implements SubtitleView.Output {
    private static final float CSS_LINE_HEIGHT = 1.2f;
    private static final String DEFAULT_BACKGROUND_CSS_CLASS = "default_bg";
    private float bottomPaddingFraction;
    private final CanvasSubtitleOutput canvasSubtitleOutput;
    private float defaultTextSize;
    private int defaultTextSizeType;
    private CaptionStyleCompat style;
    private List<Cue> textCues;
    private final WebView webView;

    private static int anchorTypeToTranslatePercent(int i) {
        if (i != 1) {
            return i != 2 ? 0 : -100;
        }
        return -50;
    }

    public WebViewSubtitleOutput(Context context) {
        this(context, null);
    }

    public WebViewSubtitleOutput(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.textCues = Collections.EMPTY_LIST;
        this.style = CaptionStyleCompat.DEFAULT;
        this.defaultTextSize = 0.0533f;
        this.defaultTextSizeType = 0;
        this.bottomPaddingFraction = 0.08f;
        CanvasSubtitleOutput canvasSubtitleOutput = new CanvasSubtitleOutput(context, attributeSet);
        this.canvasSubtitleOutput = canvasSubtitleOutput;
        WebView webView = new WebView(context, attributeSet) { // from class: androidx.media3.ui.WebViewSubtitleOutput.1
            @Override // android.webkit.WebView, android.view.View
            public boolean onTouchEvent(MotionEvent motionEvent) {
                super.onTouchEvent(motionEvent);
                return false;
            }

            @Override // android.view.View
            public boolean performClick() {
                super.performClick();
                return false;
            }
        };
        this.webView = webView;
        webView.setBackgroundColor(0);
        webView.getSettings().setAllowContentAccess(false);
        addView(canvasSubtitleOutput);
        addView(webView);
    }

    @Override // androidx.media3.ui.SubtitleView.Output
    public void update(List<Cue> list, CaptionStyleCompat captionStyleCompat, float f, int i, float f2) {
        this.style = captionStyleCompat;
        this.defaultTextSize = f;
        this.defaultTextSizeType = i;
        this.bottomPaddingFraction = f2;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            Cue cue = list.get(i2);
            if (cue.bitmap != null) {
                arrayList.add(cue);
            } else {
                arrayList2.add(cue);
            }
        }
        if (!this.textCues.isEmpty() || !arrayList2.isEmpty()) {
            this.textCues = arrayList2;
            updateWebView();
        }
        this.canvasSubtitleOutput.update(arrayList, captionStyleCompat, f, i, f2);
        invalidate();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (!z || this.textCues.isEmpty()) {
            return;
        }
        updateWebView();
    }

    public void destroy() {
        this.webView.destroy();
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0118  */
    /* JADX WARN: Code duplicated, block: B:27:0x012b  */
    /* JADX WARN: Code duplicated, block: B:30:0x0147  */
    /* JADX WARN: Code duplicated, block: B:31:0x014a  */
    /* JADX WARN: Code duplicated, block: B:34:0x0161  */
    /* JADX WARN: Code duplicated, block: B:36:0x0164 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:37:0x0166  */
    /* JADX WARN: Code duplicated, block: B:38:0x0169 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:40:0x016c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:41:0x016e  */
    /* JADX WARN: Code duplicated, block: B:49:0x0182  */
    /* JADX WARN: Code duplicated, block: B:53:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:59:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:63:0x0244  */
    /* JADX WARN: Code duplicated, block: B:64:0x0262  */
    /* JADX WARN: Multi-variable type inference failed */
    private void updateWebView() {
        char c;
        String invariant;
        int iAnchorTypeToTranslatePercent;
        int i;
        int i2;
        String invariant2;
        int i3;
        int i4;
        String str;
        String str2;
        String str3;
        String str4;
        SpannedToHtmlConverter.HtmlAndCss htmlAndCssConvert;
        String str5;
        boolean z;
        StringBuilder sb = new StringBuilder();
        String cssRgba = HtmlUtils.toCssRgba(this.style.foregroundColor);
        String strConvertTextSizeToCss = convertTextSizeToCss(this.defaultTextSizeType, this.defaultTextSize);
        float f = CSS_LINE_HEIGHT;
        char c2 = 4;
        int i5 = 0;
        int i6 = 1;
        char c3 = 3;
        sb.append(Util.formatInvariant("<body><div style='-webkit-user-select:none;position:fixed;top:0;bottom:0;left:0;right:0;color:%s;font-size:%s;line-height:%.2f;text-shadow:%s;'>", cssRgba, strConvertTextSizeToCss, Float.valueOf(CSS_LINE_HEIGHT), convertCaptionStyleToCssTextShadow(this.style)));
        HashMap map = new HashMap();
        map.put(HtmlUtils.cssAllClassDescendantsSelector(DEFAULT_BACKGROUND_CSS_CLASS), Util.formatInvariant("background-color:%s;", HtmlUtils.toCssRgba(this.style.backgroundColor)));
        int i7 = 0;
        while (i7 < this.textCues.size()) {
            Cue cue = this.textCues.get(i7);
            float f2 = -3.4028235E38f;
            float f3 = cue.position != -3.4028235E38f ? cue.position * 100.0f : 50.0f;
            int iAnchorTypeToTranslatePercent2 = anchorTypeToTranslatePercent(cue.positionAnchor);
            float f4 = f;
            char c4 = c3;
            if (cue.line != -3.4028235E38f) {
                if (cue.lineType != i6) {
                    c = c2;
                    Object[] objArr = new Object[i6];
                    objArr[i5] = Float.valueOf(cue.line * 100.0f);
                    invariant = Util.formatInvariant("%.2f%%", objArr);
                    if (cue.verticalType == i6) {
                        iAnchorTypeToTranslatePercent = -anchorTypeToTranslatePercent(cue.lineAnchor);
                    } else {
                        iAnchorTypeToTranslatePercent = anchorTypeToTranslatePercent(cue.lineAnchor);
                    }
                    f2 = -3.4028235E38f;
                    i = i5;
                } else {
                    c = c2;
                    if (cue.line >= 0.0f) {
                        Object[] objArr2 = new Object[i6];
                        objArr2[i5] = Float.valueOf(cue.line * f4);
                        invariant = Util.formatInvariant("%.2fem", objArr2);
                        iAnchorTypeToTranslatePercent = i5;
                        i = iAnchorTypeToTranslatePercent;
                    } else {
                        Object[] objArr3 = new Object[i6];
                        objArr3[i5] = Float.valueOf(((-cue.line) - 1.0f) * f4);
                        invariant = Util.formatInvariant("%.2fem", objArr3);
                        i = i6;
                        iAnchorTypeToTranslatePercent = i5;
                    }
                }
                if (cue.size != f2) {
                    i2 = i5;
                    Object[] objArr4 = new Object[i6];
                    objArr4[i2] = Float.valueOf(cue.size * 100.0f);
                    invariant2 = Util.formatInvariant("%.2f%%", objArr4);
                } else {
                    i2 = i5;
                    invariant2 = "fit-content";
                }
                String strConvertAlignmentToCss = convertAlignmentToCss(cue.textAlignment);
                String strConvertVerticalTypeToCss = convertVerticalTypeToCss(cue.verticalType);
                String strConvertTextSizeToCss2 = convertTextSizeToCss(cue.textSizeType, cue.textSize);
                if (cue.windowColorSet) {
                    i3 = cue.windowColor;
                } else {
                    i3 = this.style.windowColor;
                }
                String cssRgba2 = HtmlUtils.toCssRgba(i3);
                i4 = cue.verticalType;
                str = TtmlNode.RIGHT;
                str2 = TtmlNode.LEFT;
                str3 = "top";
                if (i4 != 1) {
                    if (i != 0) {
                        str = TtmlNode.LEFT;
                    }
                    str2 = "top";
                    str3 = str;
                } else if (i4 != 2) {
                    if (i == 0) {
                        str = TtmlNode.LEFT;
                    }
                    str2 = "top";
                    str3 = str;
                } else if (i != 0) {
                    str3 = "bottom";
                }
                if (cue.verticalType != 2 || cue.verticalType == 1) {
                    str4 = "height";
                    iAnchorTypeToTranslatePercent2 = iAnchorTypeToTranslatePercent;
                    iAnchorTypeToTranslatePercent = iAnchorTypeToTranslatePercent2;
                } else {
                    str4 = "width";
                }
                htmlAndCssConvert = SpannedToHtmlConverter.convert(cue.text, getContext().getResources().getDisplayMetrics().density);
                for (String str6 : map.keySet()) {
                    String str7 = str4;
                    String str8 = invariant;
                    str5 = (String) map.put(str6, (String) map.get(str6));
                    if (str5 != null || str5.equals(map.get(str6))) {
                        z = 1;
                    } else {
                        z = i2;
                    }
                    Preconditions.checkState(z);
                    str4 = str7;
                    invariant = str8;
                }
                String str9 = str4;
                String str10 = invariant;
                Integer numValueOf = Integer.valueOf(i7);
                Float fValueOf = Float.valueOf(f3);
                Integer numValueOf2 = Integer.valueOf(iAnchorTypeToTranslatePercent2);
                Integer numValueOf3 = Integer.valueOf(iAnchorTypeToTranslatePercent);
                String blockShearTransformFunction = getBlockShearTransformFunction(cue);
                Object[] objArr5 = new Object[14];
                objArr5[i2] = numValueOf;
                objArr5[1] = str2;
                objArr5[2] = fValueOf;
                objArr5[c4] = str3;
                objArr5[c] = str10;
                objArr5[5] = str9;
                objArr5[6] = invariant2;
                objArr5[7] = strConvertAlignmentToCss;
                objArr5[8] = strConvertVerticalTypeToCss;
                objArr5[9] = strConvertTextSizeToCss2;
                objArr5[10] = cssRgba2;
                objArr5[11] = numValueOf2;
                objArr5[12] = numValueOf3;
                objArr5[13] = blockShearTransformFunction;
                sb.append(Util.formatInvariant("<div style='position:absolute;z-index:%s;%s:%.2f%%;%s:%s;%s:%s;text-align:%s;writing-mode:%s;font-size:%s;background-color:%s;transform:translate(%s%%,%s%%)%s;'>", objArr5));
                Object[] objArr6 = new Object[1];
                objArr6[i2] = DEFAULT_BACKGROUND_CSS_CLASS;
                sb.append(Util.formatInvariant("<span class='%s'>", objArr6));
                if (cue.multiRowAlignment != null) {
                    Object[] objArr7 = new Object[1];
                    objArr7[i2] = convertAlignmentToCss(cue.multiRowAlignment);
                    sb.append(Util.formatInvariant("<span style='display:inline-block; text-align:%s;'>", objArr7));
                    sb.append(htmlAndCssConvert.html);
                    sb.append("</span>");
                } else {
                    sb.append(htmlAndCssConvert.html);
                }
                sb.append("</span></div>");
                i7++;
                f = f4;
                i5 = i2;
                c3 = c4;
                c2 = c;
                i6 = 1;
            } else {
                c = c2;
                Object[] objArr8 = new Object[i6];
                objArr8[i5] = Float.valueOf((1.0f - this.bottomPaddingFraction) * 100.0f);
                invariant = Util.formatInvariant("%.2f%%", objArr8);
                iAnchorTypeToTranslatePercent = -100;
                i = i5;
            }
            if (cue.size != f2) {
                i2 = i5;
                Object[] objArr9 = new Object[i6];
                objArr9[i2] = Float.valueOf(cue.size * 100.0f);
                invariant2 = Util.formatInvariant("%.2f%%", objArr9);
            } else {
                i2 = i5;
                invariant2 = "fit-content";
            }
            String strConvertAlignmentToCss2 = convertAlignmentToCss(cue.textAlignment);
            String strConvertVerticalTypeToCss2 = convertVerticalTypeToCss(cue.verticalType);
            String strConvertTextSizeToCss3 = convertTextSizeToCss(cue.textSizeType, cue.textSize);
            if (cue.windowColorSet) {
                i3 = cue.windowColor;
            } else {
                i3 = this.style.windowColor;
            }
            String cssRgba3 = HtmlUtils.toCssRgba(i3);
            i4 = cue.verticalType;
            str = TtmlNode.RIGHT;
            str2 = TtmlNode.LEFT;
            str3 = "top";
            if (i4 != 1) {
                if (i != 0) {
                    str = TtmlNode.LEFT;
                }
                str2 = "top";
                str3 = str;
            } else if (i4 != 2) {
                if (i == 0) {
                    str = TtmlNode.LEFT;
                }
                str2 = "top";
                str3 = str;
            } else if (i != 0) {
                str3 = "bottom";
            }
            if (cue.verticalType != 2) {
                str4 = "height";
                iAnchorTypeToTranslatePercent2 = iAnchorTypeToTranslatePercent;
                iAnchorTypeToTranslatePercent = iAnchorTypeToTranslatePercent2;
            } else {
                str4 = "height";
                iAnchorTypeToTranslatePercent2 = iAnchorTypeToTranslatePercent;
                iAnchorTypeToTranslatePercent = iAnchorTypeToTranslatePercent2;
            }
            htmlAndCssConvert = SpannedToHtmlConverter.convert(cue.text, getContext().getResources().getDisplayMetrics().density);
            while (r13.hasNext()) {
                String str11 = str4;
                String str12 = invariant;
                str5 = (String) map.put(str6, (String) map.get(str6));
                if (str5 != null) {
                    z = 1;
                } else {
                    z = 1;
                }
                Preconditions.checkState(z);
                str4 = str11;
                invariant = str12;
            }
            String str13 = str4;
            String str14 = invariant;
            Integer numValueOf4 = Integer.valueOf(i7);
            Float fValueOf2 = Float.valueOf(f3);
            Integer numValueOf5 = Integer.valueOf(iAnchorTypeToTranslatePercent2);
            Integer numValueOf6 = Integer.valueOf(iAnchorTypeToTranslatePercent);
            String blockShearTransformFunction2 = getBlockShearTransformFunction(cue);
            Object[] objArr10 = new Object[14];
            objArr10[i2] = numValueOf4;
            objArr10[1] = str2;
            objArr10[2] = fValueOf2;
            objArr10[c4] = str3;
            objArr10[c] = str14;
            objArr10[5] = str13;
            objArr10[6] = invariant2;
            objArr10[7] = strConvertAlignmentToCss2;
            objArr10[8] = strConvertVerticalTypeToCss2;
            objArr10[9] = strConvertTextSizeToCss3;
            objArr10[10] = cssRgba3;
            objArr10[11] = numValueOf5;
            objArr10[12] = numValueOf6;
            objArr10[13] = blockShearTransformFunction2;
            sb.append(Util.formatInvariant("<div style='position:absolute;z-index:%s;%s:%.2f%%;%s:%s;%s:%s;text-align:%s;writing-mode:%s;font-size:%s;background-color:%s;transform:translate(%s%%,%s%%)%s;'>", objArr10));
            Object[] objArr11 = new Object[1];
            objArr11[i2] = DEFAULT_BACKGROUND_CSS_CLASS;
            sb.append(Util.formatInvariant("<span class='%s'>", objArr11));
            if (cue.multiRowAlignment != null) {
                Object[] objArr12 = new Object[1];
                objArr12[i2] = convertAlignmentToCss(cue.multiRowAlignment);
                sb.append(Util.formatInvariant("<span style='display:inline-block; text-align:%s;'>", objArr12));
                sb.append(htmlAndCssConvert.html);
                sb.append("</span>");
            } else {
                sb.append(htmlAndCssConvert.html);
            }
            sb.append("</span></div>");
            i7++;
            f = f4;
            i5 = i2;
            c3 = c4;
            c2 = c;
            i6 = 1;
        }
        int i8 = i5;
        sb.append("</div></body></html>");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("<html><head><style>");
        for (String str15 : map.keySet()) {
            sb2.append(str15);
            sb2.append("{");
            sb2.append((String) map.get(str15));
            sb2.append("}");
        }
        sb2.append("</style></head>");
        sb.insert(i8, (CharSequence) sb2);
        this.webView.loadData(Base64.encodeToString(sb.toString().getBytes(StandardCharsets.UTF_8), 1), "text/html", "base64");
    }

    private static String getBlockShearTransformFunction(Cue cue) {
        String str;
        if (cue.shearDegrees != 0.0f) {
            if (cue.verticalType == 2 || cue.verticalType == 1) {
                str = "skewY";
            } else {
                str = "skewX";
            }
            return Util.formatInvariant("%s(%.2fdeg)", str, Float.valueOf(cue.shearDegrees));
        }
        return "";
    }

    private String convertTextSizeToCss(int i, float f) {
        float fResolveTextSize = SubtitleViewUtils.resolveTextSize(i, f, getHeight(), (getHeight() - getPaddingTop()) - getPaddingBottom());
        return fResolveTextSize == -3.4028235E38f ? "unset" : Util.formatInvariant("%.2fpx", Float.valueOf(fResolveTextSize / getContext().getResources().getDisplayMetrics().density));
    }

    private static String convertCaptionStyleToCssTextShadow(CaptionStyleCompat captionStyleCompat) {
        int i = captionStyleCompat.edgeType;
        if (i == 1) {
            return Util.formatInvariant("1px 1px 0 %1$s, 1px -1px 0 %1$s, -1px 1px 0 %1$s, -1px -1px 0 %1$s", HtmlUtils.toCssRgba(captionStyleCompat.edgeColor));
        }
        if (i == 2) {
            return Util.formatInvariant("0.1em 0.12em 0.15em %s", HtmlUtils.toCssRgba(captionStyleCompat.edgeColor));
        }
        if (i == 3) {
            return Util.formatInvariant("0.06em 0.08em 0.15em %s", HtmlUtils.toCssRgba(captionStyleCompat.edgeColor));
        }
        if (i == 4) {
            return Util.formatInvariant("-0.05em -0.05em 0.15em %s", HtmlUtils.toCssRgba(captionStyleCompat.edgeColor));
        }
        return "unset";
    }

    private static String convertVerticalTypeToCss(int i) {
        if (i == 1) {
            return "vertical-rl";
        }
        if (i == 2) {
            return "vertical-lr";
        }
        return "horizontal-tb";
    }

    private static String convertAlignmentToCss(Layout.Alignment alignment) {
        if (alignment == null) {
            return TtmlNode.CENTER;
        }
        int i = AnonymousClass2.$SwitchMap$android$text$Layout$Alignment[alignment.ordinal()];
        if (i == 1) {
            return "start";
        }
        if (i != 2) {
            return TtmlNode.CENTER;
        }
        return TtmlNode.END;
    }

    /* JADX INFO: renamed from: androidx.media3.ui.WebViewSubtitleOutput$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$text$Layout$Alignment;

        static {
            int[] iArr = new int[Layout.Alignment.values().length];
            $SwitchMap$android$text$Layout$Alignment = iArr;
            try {
                iArr[Layout.Alignment.ALIGN_NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$text$Layout$Alignment[Layout.Alignment.ALIGN_OPPOSITE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$text$Layout$Alignment[Layout.Alignment.ALIGN_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
