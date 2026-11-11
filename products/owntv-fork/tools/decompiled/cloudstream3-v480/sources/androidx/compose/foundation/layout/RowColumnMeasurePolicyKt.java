package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: RowColumnMeasurePolicy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\u001a\u0085\u0001\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u000e\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0004H\u0000¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/foundation/layout/RowColumnMeasurePolicy;", "mainAxisMin", "", "crossAxisMin", "mainAxisMax", "crossAxisMax", "arrangementSpacingInt", "measureScope", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "placeables", "", "Landroidx/compose/ui/layout/Placeable;", "startIndex", "endIndex", "crossAxisOffset", "", "currentLineIndex", "(Landroidx/compose/foundation/layout/RowColumnMeasurePolicy;IIIIILandroidx/compose/ui/layout/MeasureScope;Ljava/util/List;[Landroidx/compose/ui/layout/Placeable;II[II)Landroidx/compose/ui/layout/MeasureResult;", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RowColumnMeasurePolicyKt {
    /* JADX WARN: Code duplicated, block: B:127:0x0298 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x0286 A[Catch: IllegalArgumentException -> 0x0322, TRY_LEAVE, TryCatch #0 {IllegalArgumentException -> 0x0322, blocks: (B:65:0x0273, B:73:0x0286), top: B:121:0x0273 }] */
    /* JADX WARN: Code duplicated, block: B:75:0x0292  */
    /* JADX WARN: Code duplicated, block: B:81:0x02c2  */
    public static final MeasureResult measure(RowColumnMeasurePolicy rowColumnMeasurePolicy, int i, int i2, int i3, int i4, int i5, MeasureScope measureScope, List<? extends Measurable> list, Placeable[] placeableArr, int i6, int i7, int[] iArr, int i8) {
        int i9;
        int i10;
        int i11;
        float f;
        String str;
        String str2;
        String str3;
        long j;
        String str4;
        String str5;
        long j2;
        String str6;
        int i12;
        int iCoerceIn;
        int i13;
        int i14;
        String str7;
        int i15;
        String str8;
        int i16;
        String str9;
        int i17;
        String str10;
        String str11;
        FlowLayoutData flowLayoutData;
        String str12;
        int i18;
        String str13;
        int i19;
        String str14;
        long j3;
        String str15;
        long j4;
        char c;
        String str16;
        int iIntValue;
        int iIntValue2;
        int i20;
        int i21;
        int i22;
        RowColumnMeasurePolicy rowColumnMeasurePolicy2;
        FlowLayoutData flowLayoutData2;
        List<? extends Measurable> list2 = list;
        int i23 = i7;
        long j5 = i5;
        int i24 = i23 - i6;
        int[] iArr2 = new int[i24];
        int i25 = 0;
        int i26 = i6;
        int i27 = 0;
        int i28 = 0;
        int i29 = 0;
        int i30 = 0;
        int i31 = 0;
        float f2 = 0.0f;
        while (true) {
            Integer numValueOf = null;
            i9 = i28;
            if (i26 >= i23) {
                break;
            }
            float f3 = f2;
            Measurable measurable = list2.get(i26);
            RowColumnParentData rowColumnParentData = RowColumnImplKt.getRowColumnParentData(measurable);
            float weight = RowColumnImplKt.getWeight(rowColumnParentData);
            int i32 = (i29 != 0 || RowColumnImplKt.isRelative(rowColumnParentData)) ? 1 : i25;
            if (weight > 0.0f) {
                f2 = f3 + weight;
                i27++;
                i24 = i24;
                i26 = i26;
                i28 = i9;
            } else {
                if (i4 != 2147483647 && rowColumnParentData != null && (flowLayoutData2 = rowColumnParentData.getFlowLayoutData()) != null) {
                    numValueOf = Integer.valueOf(Math.round(flowLayoutData2.getFillCrossAxisFraction() * i4));
                }
                int i33 = i3 - i9;
                Placeable placeableMo5581measureBRTryo0 = placeableArr[i26];
                if (placeableMo5581measureBRTryo0 == null) {
                    rowColumnMeasurePolicy2 = rowColumnMeasurePolicy;
                    placeableMo5581measureBRTryo0 = measurable.mo5581measureBRTryo0(RowColumnMeasurePolicy.CC.m745createConstraintsxF2OJ5Q$default(rowColumnMeasurePolicy2, 0, numValueOf != null ? numValueOf.intValue() : i25, i3 != Integer.MAX_VALUE ? RangesKt.coerceAtLeast(i33, i25) : Integer.MAX_VALUE, numValueOf != null ? numValueOf.intValue() : i4, false, 16, null));
                } else {
                    rowColumnMeasurePolicy2 = rowColumnMeasurePolicy;
                }
                int iMainAxisSize = rowColumnMeasurePolicy2.mainAxisSize(placeableMo5581measureBRTryo0);
                int iCrossAxisSize = rowColumnMeasurePolicy2.crossAxisSize(placeableMo5581measureBRTryo0);
                iArr2[i26 - i6] = iMainAxisSize;
                int iMin = Math.min(i5, RangesKt.coerceAtLeast(i33 - iMainAxisSize, 0));
                i28 = i9 + iMainAxisSize + iMin;
                int iMax = Math.max(i31, iCrossAxisSize);
                placeableArr[i26] = placeableMo5581measureBRTryo0;
                i31 = iMax;
                i30 = iMin;
                i27 = i27;
                f2 = f3;
            }
            i26++;
            i29 = i32;
            i24 = i24;
            i25 = 0;
        }
        int i34 = i24;
        float f4 = f2;
        int i35 = i31;
        int i36 = i9;
        int i37 = i27;
        if (i37 == 0) {
            i13 = i36 - i30;
            i12 = i35;
            i10 = i29;
            iCoerceIn = 0;
        } else {
            int i38 = i3 != Integer.MAX_VALUE ? i3 : i;
            long j6 = ((long) (i37 - 1)) * j5;
            int i39 = i35;
            long jCoerceAtLeast = RangesKt.coerceAtLeast(((long) (i38 - i36)) - j6, 0L);
            float f5 = jCoerceAtLeast / f4;
            int i40 = i6;
            long jRound = jCoerceAtLeast;
            i10 = i29;
            while (true) {
                i11 = i39;
                f = f5;
                str = "weightUnitSpace ";
                str2 = "arrangementSpacingTotal ";
                str3 = "fixedSpace ";
                j = jCoerceAtLeast;
                str4 = "remainingToTarget ";
                str5 = "targetSpace ";
                j2 = j6;
                str6 = "mainAxisMin ";
                if (i40 >= i23) {
                    break;
                }
                float weight2 = RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(list2.get(i40)));
                float f6 = f * weight2;
                try {
                    jRound -= (long) Math.round(f6);
                    i40++;
                    list2 = list;
                    i39 = i11;
                    f5 = f;
                    jCoerceAtLeast = j;
                    j6 = j2;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("This log indicates a hard-to-reproduce Compose issue, modified with additional debugging details. Please help us by adding your experiences to the bug link provided. Thank you for helping us improve Compose. https://issuetracker.google.com/issues/297974033 mainAxisMax " + i3 + "mainAxisMin " + i + "targetSpace " + i38 + "arrangementSpacingPx " + j5 + "weightChildrenCount " + i37 + "fixedSpace " + i36 + "arrangementSpacingTotal " + j2 + str4 + j + "totalWeight " + f4 + str + f + "itemWeight " + weight2 + "weightedSize " + f6).initCause(e);
                }
            }
            int i41 = i37;
            String str17 = "weightedSize ";
            String str18 = "weightChildrenCount ";
            int iMax2 = i11;
            int i42 = 0;
            int i43 = i6;
            while (i43 < i23) {
                if (placeableArr[i43] == null) {
                    i16 = i43;
                    Measurable measurable2 = list.get(i43);
                    RowColumnParentData rowColumnParentData2 = RowColumnImplKt.getRowColumnParentData(measurable2);
                    float weight3 = RowColumnImplKt.getWeight(rowColumnParentData2);
                    int i44 = i42;
                    Integer numValueOf2 = (i4 == Integer.MAX_VALUE || rowColumnParentData2 == null || (flowLayoutData = rowColumnParentData2.getFlowLayoutData()) == null) ? null : Integer.valueOf(Math.round(i4 * flowLayoutData.getFillCrossAxisFraction()));
                    if (weight3 <= 0.0f) {
                        throw new IllegalStateException("All weights <= 0 should have placeables".toString());
                    }
                    Integer num = numValueOf2;
                    int sign = MathKt.getSign(jRound);
                    String str19 = str5;
                    jRound -= (long) sign;
                    float f7 = f * weight3;
                    String str20 = str6;
                    int iMax3 = Math.max(0, Math.round(f7) + sign);
                    try {
                        try {
                            if (RowColumnImplKt.getFill(rowColumnParentData2)) {
                                c = 65535;
                                int i45 = iMax3 != Integer.MAX_VALUE ? iMax3 : 0;
                                if (num != null) {
                                    iIntValue = num.intValue();
                                    str16 = str17;
                                } else {
                                    str16 = str17;
                                    iIntValue = 0;
                                }
                                if (num != null) {
                                    try {
                                        iIntValue2 = num.intValue();
                                    } catch (IllegalArgumentException e2) {
                                        e = e2;
                                        String str21 = str18;
                                        i14 = i36;
                                        str7 = str21;
                                        str8 = str2;
                                        i18 = sign;
                                        i19 = i41;
                                        str12 = str16;
                                        str14 = str;
                                        j3 = j;
                                        str15 = str4;
                                        j4 = j2;
                                        str11 = str20;
                                        str13 = str3;
                                        str10 = str19;
                                        throw new IllegalArgumentException("This log indicates a hard-to-reproduce Compose issue, modified with additional debugging details. Please help us by adding your experiences to the bug link provided. Thank you for helping us improve Compose. https://issuetracker.google.com/issues/300280216 mainAxisMax " + i3 + str11 + i + str10 + i38 + "arrangementSpacingPx " + j5 + str7 + i19 + str13 + i14 + str8 + j4 + str15 + j3 + "totalWeight " + f4 + str14 + f + "weight " + weight3 + str12 + f7 + "crossAxisDesiredSize " + num + "remainderUnit " + i18 + "childMainAxisSize " + iMax3).initCause(e);
                                    }
                                } else {
                                    iIntValue2 = i4;
                                }
                                String str22 = str18;
                                i14 = i36;
                                str7 = str22;
                                str8 = str2;
                                str12 = str16;
                                int i46 = iIntValue2;
                                str14 = str;
                                j3 = j;
                                str15 = str4;
                                j4 = j2;
                                i18 = sign;
                                str13 = str3;
                                i19 = i41;
                                str10 = str19;
                                str11 = str20;
                                Placeable placeableMo5581measureBRTryo1 = measurable2.mo5581measureBRTryo0(rowColumnMeasurePolicy.mo634createConstraintsxF2OJ5Q(i45, iIntValue, iMax3, i46, true));
                                int iMainAxisSize2 = rowColumnMeasurePolicy.mainAxisSize(placeableMo5581measureBRTryo1);
                                int iCrossAxisSize2 = rowColumnMeasurePolicy.crossAxisSize(placeableMo5581measureBRTryo1);
                                iArr2[i16 - i6] = iMainAxisSize2;
                                iMax2 = Math.max(iMax2, iCrossAxisSize2);
                                placeableArr[i16] = placeableMo5581measureBRTryo1;
                                i15 = i44 + iMainAxisSize2;
                                str9 = str13;
                                i17 = i19;
                                j2 = j4;
                                j = j3;
                                str17 = str12;
                                str = str14;
                                str4 = str15;
                            } else {
                                c = 65535;
                            }
                            Placeable placeableMo5581measureBRTryo2 = measurable2.mo5581measureBRTryo0(rowColumnMeasurePolicy.mo634createConstraintsxF2OJ5Q(i45, iIntValue, iMax3, i46, true));
                            int iMainAxisSize3 = rowColumnMeasurePolicy.mainAxisSize(placeableMo5581measureBRTryo2);
                            int iCrossAxisSize3 = rowColumnMeasurePolicy.crossAxisSize(placeableMo5581measureBRTryo2);
                            iArr2[i16 - i6] = iMainAxisSize3;
                            iMax2 = Math.max(iMax2, iCrossAxisSize3);
                            placeableArr[i16] = placeableMo5581measureBRTryo2;
                            i15 = i44 + iMainAxisSize3;
                            str9 = str13;
                            i17 = i19;
                            j2 = j4;
                            j = j3;
                            str17 = str12;
                            str = str14;
                            str4 = str15;
                        } catch (IllegalArgumentException e3) {
                            e = e3;
                            throw new IllegalArgumentException("This log indicates a hard-to-reproduce Compose issue, modified with additional debugging details. Please help us by adding your experiences to the bug link provided. Thank you for helping us improve Compose. https://issuetracker.google.com/issues/300280216 mainAxisMax " + i3 + str11 + i + str10 + i38 + "arrangementSpacingPx " + j5 + str7 + i19 + str13 + i14 + str8 + j4 + str15 + j3 + "totalWeight " + f4 + str14 + f + "weight " + weight3 + str12 + f7 + "crossAxisDesiredSize " + num + "remainderUnit " + i18 + "childMainAxisSize " + iMax3).initCause(e);
                        }
                        if (num != null) {
                            iIntValue = num.intValue();
                            str16 = str17;
                        } else {
                            str16 = str17;
                            iIntValue = 0;
                        }
                        if (num != null) {
                            iIntValue2 = num.intValue();
                        } else {
                            iIntValue2 = i4;
                        }
                        String str23 = str18;
                        i14 = i36;
                        str7 = str23;
                        str8 = str2;
                        str12 = str16;
                        int i47 = iIntValue2;
                        str14 = str;
                        j3 = j;
                        str15 = str4;
                        j4 = j2;
                        i18 = sign;
                        str13 = str3;
                        i19 = i41;
                        str10 = str19;
                        str11 = str20;
                    } catch (IllegalArgumentException e4) {
                        e = e4;
                        String str24 = str18;
                        i14 = i36;
                        str7 = str24;
                        str12 = str17;
                        str8 = str2;
                        i18 = sign;
                        str13 = str3;
                        i19 = i41;
                        str14 = str;
                        j3 = j;
                        str15 = str4;
                        j4 = j2;
                        str10 = str19;
                        str11 = str20;
                        iMax3 = iMax3;
                    }
                } else {
                    String str25 = str18;
                    i14 = i36;
                    str7 = str25;
                    i15 = i42;
                    str8 = str2;
                    i16 = i43;
                    str9 = str3;
                    i17 = i41;
                    str10 = str5;
                    str11 = str6;
                }
                int i48 = i14;
                str18 = str7;
                i36 = i48;
                str6 = str11;
                i42 = i15;
                str2 = str8;
                i41 = i17;
                i43 = i16 + 1;
                str5 = str10;
                str3 = str9;
                i23 = i7;
            }
            int i49 = i36;
            i12 = iMax2;
            iCoerceIn = RangesKt.coerceIn((int) (((long) i42) + j2), 0, i3 - i49);
            i13 = i49;
        }
        if (i10 != 0) {
            i20 = i7;
            int iMax4 = 0;
            int iMax5 = 0;
            for (int i50 = i6; i50 < i20; i50++) {
                Placeable placeable = placeableArr[i50];
                Intrinsics.checkNotNull(placeable);
                CrossAxisAlignment crossAxisAlignment = RowColumnImplKt.getCrossAxisAlignment(RowColumnImplKt.getRowColumnParentData(placeable));
                Integer numCalculateAlignmentLinePosition$foundation_layout_release = crossAxisAlignment != null ? crossAxisAlignment.calculateAlignmentLinePosition$foundation_layout_release(placeable) : null;
                if (numCalculateAlignmentLinePosition$foundation_layout_release != null) {
                    int iIntValue3 = numCalculateAlignmentLinePosition$foundation_layout_release.intValue();
                    int iCrossAxisSize4 = rowColumnMeasurePolicy.crossAxisSize(placeable);
                    iMax4 = Math.max(iMax4, iIntValue3 != Integer.MIN_VALUE ? numCalculateAlignmentLinePosition$foundation_layout_release.intValue() : 0);
                    if (iIntValue3 == Integer.MIN_VALUE) {
                        iIntValue3 = iCrossAxisSize4;
                    }
                    iMax5 = Math.max(iMax5, iCrossAxisSize4 - iIntValue3);
                }
            }
            int i51 = iMax5;
            i22 = iMax4;
            i21 = i51;
        } else {
            i20 = i7;
            i21 = 0;
            i22 = 0;
        }
        int iMax6 = Math.max(RangesKt.coerceAtLeast(i13 + iCoerceIn, 0), i);
        int iMax7 = Math.max(i12, Math.max(i2, i21 + i22));
        int[] iArr3 = new int[i34];
        for (int i52 = 0; i52 < i34; i52++) {
            iArr3[i52] = 0;
        }
        rowColumnMeasurePolicy.populateMainAxisPositions(iMax6, iArr2, iArr3, measureScope);
        return rowColumnMeasurePolicy.placeHelper(placeableArr, measureScope, i22, iArr3, iMax6, iMax7, iArr, i8, i6, i20);
    }
}
