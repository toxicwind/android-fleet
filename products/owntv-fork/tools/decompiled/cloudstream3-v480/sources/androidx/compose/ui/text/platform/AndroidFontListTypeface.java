package androidx.compose.ui.text.platform;

import android.content.Context;
import android.graphics.Typeface;
import androidx.collection.MutableScatterSet;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontListFontFamily;
import androidx.compose.ui.text.font.FontLoadingStrategy;
import androidx.compose.ui.text.font.FontMatcher;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis_androidKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.internal.InlineClassHelperKt;
import androidx.compose.ui.util.ListUtilsKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidFontListTypeface.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Deprecated(message = "This is not supported after downloadable fonts.")
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000  2\u00020\u0001:\u0001 B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u001c\b\u0002\u0010\u0006\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ'\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016¢\u0006\u0004\b\u001e\u0010\u001fR\u0013\u0010\u000b\u001a\u00020\f¢\u0006\n\n\u0002\b\u0011\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0016X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006!"}, d2 = {"Landroidx/compose/ui/text/platform/AndroidFontListTypeface;", "Landroidx/compose/ui/text/platform/AndroidTypeface;", TtmlNode.ATTR_TTS_FONT_FAMILY, "Landroidx/compose/ui/text/font/FontListFontFamily;", "context", "Landroid/content/Context;", "necessaryStyles", "", "Lkotlin/Pair;", "Landroidx/compose/ui/text/font/FontWeight;", "Landroidx/compose/ui/text/font/FontStyle;", "fontMatcher", "Landroidx/compose/ui/text/font/FontMatcher;", "<init>", "(Landroidx/compose/ui/text/font/FontListFontFamily;Landroid/content/Context;Ljava/util/List;Landroidx/compose/ui/text/font/FontMatcher;)V", "getFontMatcher", "()Landroidx/compose/ui/text/font/FontMatcher;", "fontMatcher$1", "loadedTypefaces", "", "Landroidx/compose/ui/text/font/Font;", "Landroid/graphics/Typeface;", "Landroidx/compose/ui/text/font/FontFamily;", "getFontFamily", "()Landroidx/compose/ui/text/font/FontFamily;", "getNativeTypeface", TtmlNode.ATTR_TTS_FONT_WEIGHT, TtmlNode.ATTR_TTS_FONT_STYLE, "synthesis", "Landroidx/compose/ui/text/font/FontSynthesis;", "getNativeTypeface-PYhJU0U", "(Landroidx/compose/ui/text/font/FontWeight;II)Landroid/graphics/Typeface;", "Companion", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AndroidFontListTypeface implements AndroidTypeface {
    private final FontFamily fontFamily;

    /* JADX INFO: renamed from: fontMatcher$1, reason: from kotlin metadata */
    private final FontMatcher fontMatcher;
    private final Map<Font, Typeface> loadedTypefaces;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final FontMatcher fontMatcher = new FontMatcher();

    /* JADX WARN: Code duplicated, block: B:22:0x00bc  */
    public AndroidFontListTypeface(FontListFontFamily fontListFontFamily, Context context, List<Pair<FontWeight, FontStyle>> list, FontMatcher fontMatcher2) {
        ArrayList arrayList;
        this.fontMatcher = fontMatcher2;
        List<Font> fonts = fontListFontFamily.getFonts();
        ArrayList arrayList2 = new ArrayList(fonts.size());
        int size = fonts.size();
        for (int i = 0; i < size; i++) {
            Font font = fonts.get(i);
            if (FontLoadingStrategy.m6445equalsimpl0(font.getLoadingStrategy(), FontLoadingStrategy.INSTANCE.m6450getBlockingPKNRLFQ())) {
                arrayList2.add(font);
            }
        }
        ArrayList arrayList3 = arrayList2;
        if (list != null) {
            ArrayList arrayList4 = new ArrayList(list.size());
            int size2 = list.size();
            for (int i2 = 0; i2 < size2; i2++) {
                Pair<FontWeight, FontStyle> pair = list.get(i2);
                arrayList4.add((Font) CollectionsKt.firstOrNull((List) this.fontMatcher.m6454matchFontRetOiIg(arrayList3, pair.component1(), pair.component2().m6461unboximpl())));
            }
            List listFastFilterNotNull = ListUtilsKt.fastFilterNotNull(arrayList4);
            if (listFastFilterNotNull != null) {
                MutableScatterSet mutableScatterSet = new MutableScatterSet(listFastFilterNotNull.size());
                ArrayList arrayList5 = new ArrayList(listFastFilterNotNull.size());
                int size3 = listFastFilterNotNull.size();
                for (int i3 = 0; i3 < size3; i3++) {
                    Object obj = listFastFilterNotNull.get(i3);
                    if (mutableScatterSet.add((Font) obj)) {
                        arrayList5.add(obj);
                    }
                }
                arrayList = arrayList5;
            } else {
                arrayList = null;
            }
        } else {
            arrayList = null;
        }
        arrayList3 = arrayList != null ? arrayList : arrayList3;
        ArrayList arrayList6 = arrayList3;
        if (arrayList6.isEmpty()) {
            InlineClassHelperKt.throwIllegalStateException("Could not match font");
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int size4 = arrayList6.size();
        for (int i4 = 0; i4 < size4; i4++) {
            Font font2 = (Font) arrayList3.get(i4);
            try {
                linkedHashMap.put(font2, AndroidTypefaceCache.INSTANCE.getOrCreate(context, font2));
            } catch (Exception unused) {
                InlineClassHelperKt.throwIllegalStateException("Cannot create Typeface from " + font2);
            }
        }
        this.loadedTypefaces = linkedHashMap;
        this.fontFamily = fontListFontFamily;
    }

    public /* synthetic */ AndroidFontListTypeface(FontListFontFamily fontListFontFamily, Context context, List list, FontMatcher fontMatcher2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fontListFontFamily, context, (i & 4) != 0 ? null : list, (i & 8) != 0 ? fontMatcher : fontMatcher2);
    }

    public final FontMatcher getFontMatcher() {
        return this.fontMatcher;
    }

    /* JADX INFO: compiled from: AndroidFontListTypeface.android.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/text/platform/AndroidFontListTypeface$Companion;", "", "<init>", "()V", "fontMatcher", "Landroidx/compose/ui/text/font/FontMatcher;", "getFontMatcher", "()Landroidx/compose/ui/text/font/FontMatcher;", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FontMatcher getFontMatcher() {
            return AndroidFontListTypeface.fontMatcher;
        }
    }

    @Override // androidx.compose.ui.text.font.Typeface
    public FontFamily getFontFamily() {
        return this.fontFamily;
    }

    @Override // androidx.compose.ui.text.platform.AndroidTypeface
    /* JADX INFO: renamed from: getNativeTypeface-PYhJU0U */
    public Typeface mo6598getNativeTypefacePYhJU0U(FontWeight fontWeight, int fontStyle, int synthesis) {
        Font font = (Font) CollectionsKt.firstOrNull((List) this.fontMatcher.m6454matchFontRetOiIg(new ArrayList(this.loadedTypefaces.keySet()), fontWeight, fontStyle));
        if (font != null) {
            Typeface typeface = this.loadedTypefaces.get(font);
            if (typeface != null) {
                Object objM6479synthesizeTypefaceFxwP2eA = FontSynthesis_androidKt.m6479synthesizeTypefaceFxwP2eA(synthesis, typeface, font, fontWeight, fontStyle);
                Intrinsics.checkNotNull(objM6479synthesizeTypefaceFxwP2eA, "null cannot be cast to non-null type android.graphics.Typeface");
                return (Typeface) objM6479synthesizeTypefaceFxwP2eA;
            }
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Could not load typeface");
            throw new KotlinNothingValueException();
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Could not load font");
        throw new KotlinNothingValueException();
    }
}
