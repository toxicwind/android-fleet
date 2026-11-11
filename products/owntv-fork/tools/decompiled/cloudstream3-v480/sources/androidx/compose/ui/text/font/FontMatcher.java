package androidx.compose.ui.text.font;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FontMatcher.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJF\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\tH\u0080\b¢\u0006\u0002\b\u0013J+\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\u0016J+\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0014\u001a\u00020\u00172\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\u0018¨\u0006\u0019"}, d2 = {"Landroidx/compose/ui/text/font/FontMatcher;", "", "<init>", "()V", "matchFont", "", "Landroidx/compose/ui/text/font/Font;", "fontList", TtmlNode.ATTR_TTS_FONT_WEIGHT, "Landroidx/compose/ui/text/font/FontWeight;", TtmlNode.ATTR_TTS_FONT_STYLE, "Landroidx/compose/ui/text/font/FontStyle;", "matchFont-RetOiIg", "(Ljava/util/List;Landroidx/compose/ui/text/font/FontWeight;I)Ljava/util/List;", "filterByClosestWeight", "preferBelow", "", "minSearchRange", "maxSearchRange", "filterByClosestWeight$ui_text", TtmlNode.ATTR_TTS_FONT_FAMILY, "Landroidx/compose/ui/text/font/FontFamily;", "(Landroidx/compose/ui/text/font/FontFamily;Landroidx/compose/ui/text/font/FontWeight;I)Ljava/util/List;", "Landroidx/compose/ui/text/font/FontListFontFamily;", "(Landroidx/compose/ui/text/font/FontListFontFamily;Landroidx/compose/ui/text/font/FontWeight;I)Ljava/util/List;", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class FontMatcher {
    public static final int $stable = 0;

    public static /* synthetic */ List filterByClosestWeight$ui_text$default(FontMatcher fontMatcher, List list, FontWeight fontWeight, boolean z, FontWeight fontWeight2, FontWeight fontWeight3, int i, Object obj) {
        FontWeight fontWeight4 = null;
        if ((i & 4) != 0) {
            fontWeight2 = null;
        }
        if ((i & 8) != 0) {
            fontWeight3 = null;
        }
        List list2 = list;
        int size = list2.size();
        FontWeight fontWeight5 = null;
        for (int i2 = 0; i2 < size; i2++) {
            FontWeight weight = ((Font) list.get(i2)).getWeight();
            if ((fontWeight2 == null || weight.compareTo(fontWeight2) >= 0) && (fontWeight3 == null || weight.compareTo(fontWeight3) <= 0)) {
                if (weight.compareTo(fontWeight) < 0) {
                    if (fontWeight4 == null || weight.compareTo(fontWeight4) > 0) {
                        fontWeight4 = weight;
                    }
                } else {
                    if (weight.compareTo(fontWeight) <= 0) {
                        fontWeight4 = weight;
                        fontWeight5 = fontWeight4;
                        break;
                    }
                    if (fontWeight5 == null || weight.compareTo(fontWeight5) < 0) {
                        fontWeight5 = weight;
                    }
                }
            }
        }
        if (!z ? fontWeight5 != null : fontWeight4 == null) {
            fontWeight4 = fontWeight5;
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size2 = list2.size();
        for (int i3 = 0; i3 < size2; i3++) {
            Object obj2 = list.get(i3);
            if (Intrinsics.areEqual(((Font) obj2).getWeight(), fontWeight4)) {
                arrayList.add(obj2);
            }
        }
        return arrayList;
    }

    public final List<Font> filterByClosestWeight$ui_text(List<? extends Font> list, FontWeight fontWeight, boolean z, FontWeight fontWeight2, FontWeight fontWeight3) {
        List<? extends Font> list2 = list;
        int size = list2.size();
        FontWeight fontWeight4 = null;
        FontWeight fontWeight5 = null;
        for (int i = 0; i < size; i++) {
            FontWeight weight = list.get(i).getWeight();
            if ((fontWeight2 == null || weight.compareTo(fontWeight2) >= 0) && (fontWeight3 == null || weight.compareTo(fontWeight3) <= 0)) {
                if (weight.compareTo(fontWeight) < 0) {
                    if (fontWeight4 == null || weight.compareTo(fontWeight4) > 0) {
                        fontWeight4 = weight;
                    }
                } else {
                    if (weight.compareTo(fontWeight) <= 0) {
                        fontWeight4 = weight;
                        fontWeight5 = fontWeight4;
                        break;
                    }
                    if (fontWeight5 == null || weight.compareTo(fontWeight5) < 0) {
                        fontWeight5 = weight;
                    }
                }
            }
        }
        if (!z ? fontWeight5 != null : fontWeight4 == null) {
            fontWeight4 = fontWeight5;
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size2 = list2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            Font font = list.get(i2);
            if (Intrinsics.areEqual(font.getWeight(), fontWeight4)) {
                arrayList.add(font);
            }
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: matchFont-RetOiIg, reason: not valid java name */
    public final List<Font> m6452matchFontRetOiIg(FontFamily fontFamily, FontWeight fontWeight, int fontStyle) {
        if (!(fontFamily instanceof FontListFontFamily)) {
            throw new IllegalArgumentException("Only FontFamily instances that presents a list of Fonts can be used");
        }
        return m6453matchFontRetOiIg((FontListFontFamily) fontFamily, fontWeight, fontStyle);
    }

    /* JADX INFO: renamed from: matchFont-RetOiIg, reason: not valid java name */
    public final List<Font> m6453matchFontRetOiIg(FontListFontFamily fontFamily, FontWeight fontWeight, int fontStyle) {
        return m6454matchFontRetOiIg(fontFamily.getFonts(), fontWeight, fontStyle);
    }

    /* JADX INFO: renamed from: matchFont-RetOiIg, reason: not valid java name */
    public final List<Font> m6454matchFontRetOiIg(List<? extends Font> fontList, FontWeight fontWeight, int fontStyle) {
        ArrayList arrayList = new ArrayList(fontList.size());
        List<? extends Font> list = fontList;
        int size = list.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Font font = fontList.get(i2);
            Font font2 = font;
            if (Intrinsics.areEqual(font2.getWeight(), fontWeight) && FontStyle.m6458equalsimpl0(font2.getStyle(), fontStyle)) {
                arrayList.add(font);
            }
        }
        ArrayList arrayList2 = arrayList;
        if (!arrayList2.isEmpty()) {
            return arrayList2;
        }
        ArrayList arrayList3 = new ArrayList(fontList.size());
        int size2 = list.size();
        for (int i3 = 0; i3 < size2; i3++) {
            Font font3 = fontList.get(i3);
            if (FontStyle.m6458equalsimpl0(font3.getStyle(), fontStyle)) {
                arrayList3.add(font3);
            }
        }
        ArrayList arrayList4 = arrayList3;
        if (!arrayList4.isEmpty()) {
            fontList = arrayList4;
        }
        List<? extends Font> list2 = fontList;
        FontWeight fontWeight2 = null;
        if (fontWeight.compareTo(FontWeight.INSTANCE.getW400()) >= 0) {
            if (fontWeight.compareTo(FontWeight.INSTANCE.getW500()) <= 0) {
                FontWeight w500 = FontWeight.INSTANCE.getW500();
                List<? extends Font> list3 = list2;
                int size3 = list3.size();
                FontWeight fontWeight3 = null;
                FontWeight fontWeight4 = null;
                for (int i4 = 0; i4 < size3; i4++) {
                    FontWeight weight = ((Font) list2.get(i4)).getWeight();
                    if (w500 == null || weight.compareTo(w500) <= 0) {
                        if (weight.compareTo(fontWeight) < 0) {
                            if (fontWeight3 == null || weight.compareTo(fontWeight3) > 0) {
                                fontWeight3 = weight;
                            }
                        } else {
                            if (weight.compareTo(fontWeight) <= 0) {
                                fontWeight3 = weight;
                                fontWeight4 = fontWeight3;
                                break;
                            }
                            if (fontWeight4 == null || weight.compareTo(fontWeight4) < 0) {
                                fontWeight4 = weight;
                            }
                        }
                    }
                }
                if (fontWeight4 != null) {
                    fontWeight3 = fontWeight4;
                }
                ArrayList arrayList5 = new ArrayList(list2.size());
                int size4 = list3.size();
                for (int i5 = 0; i5 < size4; i5++) {
                    Object obj = list2.get(i5);
                    if (Intrinsics.areEqual(((Font) obj).getWeight(), fontWeight3)) {
                        arrayList5.add(obj);
                    }
                }
                ArrayList arrayList6 = arrayList5;
                if (arrayList6.isEmpty()) {
                    FontWeight w501 = FontWeight.INSTANCE.getW500();
                    int size5 = list3.size();
                    FontWeight fontWeight5 = null;
                    for (int i6 = 0; i6 < size5; i6++) {
                        FontWeight weight2 = ((Font) list2.get(i6)).getWeight();
                        if (w501 == null || weight2.compareTo(w501) >= 0) {
                            if (weight2.compareTo(fontWeight) < 0) {
                                if (fontWeight2 == null || weight2.compareTo(fontWeight2) > 0) {
                                    fontWeight2 = weight2;
                                }
                            } else {
                                if (weight2.compareTo(fontWeight) <= 0) {
                                    fontWeight2 = weight2;
                                    fontWeight5 = fontWeight2;
                                    break;
                                }
                                if (fontWeight5 == null || weight2.compareTo(fontWeight5) < 0) {
                                    fontWeight5 = weight2;
                                }
                            }
                        }
                    }
                    if (fontWeight5 != null) {
                        fontWeight2 = fontWeight5;
                    }
                    ArrayList arrayList7 = new ArrayList(list2.size());
                    int size6 = list3.size();
                    while (i < size6) {
                        Object obj2 = list2.get(i);
                        if (Intrinsics.areEqual(((Font) obj2).getWeight(), fontWeight2)) {
                            arrayList7.add(obj2);
                        }
                        i++;
                    }
                    arrayList6 = arrayList7;
                }
                return arrayList6;
            }
            List<? extends Font> list4 = list2;
            int size7 = list4.size();
            FontWeight fontWeight6 = null;
            for (int i7 = 0; i7 < size7; i7++) {
                FontWeight weight3 = ((Font) list2.get(i7)).getWeight();
                if (weight3.compareTo(fontWeight) < 0) {
                    if (fontWeight2 == null || weight3.compareTo(fontWeight2) > 0) {
                        fontWeight2 = weight3;
                    }
                } else {
                    if (weight3.compareTo(fontWeight) <= 0) {
                        fontWeight2 = weight3;
                        fontWeight6 = fontWeight2;
                        break;
                    }
                    if (fontWeight6 == null || weight3.compareTo(fontWeight6) < 0) {
                        fontWeight6 = weight3;
                    }
                }
            }
            if (fontWeight6 != null) {
                fontWeight2 = fontWeight6;
            }
            ArrayList arrayList8 = new ArrayList(list2.size());
            int size8 = list4.size();
            while (i < size8) {
                Object obj3 = list2.get(i);
                if (Intrinsics.areEqual(((Font) obj3).getWeight(), fontWeight2)) {
                    arrayList8.add(obj3);
                }
                i++;
            }
            return arrayList8;
        }
        List<? extends Font> list5 = list2;
        int size9 = list5.size();
        FontWeight fontWeight7 = null;
        for (int i8 = 0; i8 < size9; i8++) {
            FontWeight weight4 = ((Font) list2.get(i8)).getWeight();
            if (weight4.compareTo(fontWeight) < 0) {
                if (fontWeight2 == null || weight4.compareTo(fontWeight2) > 0) {
                    fontWeight2 = weight4;
                }
            } else {
                if (weight4.compareTo(fontWeight) <= 0) {
                    fontWeight2 = weight4;
                    fontWeight7 = fontWeight2;
                    break;
                }
                if (fontWeight7 == null || weight4.compareTo(fontWeight7) < 0) {
                    fontWeight7 = weight4;
                }
            }
        }
        if (fontWeight2 == null) {
            fontWeight2 = fontWeight7;
        }
        ArrayList arrayList9 = new ArrayList(list2.size());
        int size10 = list5.size();
        while (i < size10) {
            Object obj4 = list2.get(i);
            if (Intrinsics.areEqual(((Font) obj4).getWeight(), fontWeight2)) {
                arrayList9.add(obj4);
            }
            i++;
        }
        return arrayList9;
    }
}
