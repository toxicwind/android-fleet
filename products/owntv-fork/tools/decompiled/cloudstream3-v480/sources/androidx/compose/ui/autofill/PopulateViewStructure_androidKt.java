package androidx.compose.ui.autofill;

import android.os.Build;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import androidx.collection.MutableScatterMap;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;
import androidx.compose.ui.platform.SemanticsUtils_androidKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsInfo;
import androidx.compose.ui.semantics.SemanticsInfoKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.spatial.RectManager;
import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.text.AnnotatedString;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PopulateViewStructure.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a.\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0001¨\u0006\u000b"}, d2 = {"populate", "", "Landroid/view/ViewStructure;", "semanticsInfo", "Landroidx/compose/ui/semantics/SemanticsInfo;", "rootAutofillId", "Landroid/view/autofill/AutofillId;", "packageName", "", "rectManager", "Landroidx/compose/ui/spatial/RectManager;", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PopulateViewStructure_androidKt {
    /* JADX WARN: Code duplicated, block: B:174:0x01ae A[EDGE_INSN: B:174:0x01ae->B:66:0x01ae BREAK  A[LOOP:0: B:9:0x0043->B:64:0x018d], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:63:0x018b A[DONT_INVERT, PHI: r21 r22 r23 r24 r25 r26 r27 r28 r29
      0x018b: PHI (r21v6 androidx.compose.ui.autofill.ContentDataType) = (r21v5 androidx.compose.ui.autofill.ContentDataType), (r21v7 androidx.compose.ui.autofill.ContentDataType) binds: [B:10:0x004f, B:62:0x0189] A[DONT_GENERATE, DONT_INLINE]
      0x018b: PHI (r22v6 boolean) = (r22v5 boolean), (r22v7 boolean) binds: [B:10:0x004f, B:62:0x0189] A[DONT_GENERATE, DONT_INLINE]
      0x018b: PHI (r23v10 androidx.compose.ui.state.ToggleableState) = (r23v9 androidx.compose.ui.state.ToggleableState), (r23v11 androidx.compose.ui.state.ToggleableState) binds: [B:10:0x004f, B:62:0x0189] A[DONT_GENERATE, DONT_INLINE]
      0x018b: PHI (r24v6 androidx.compose.ui.autofill.ContentType) = (r24v5 androidx.compose.ui.autofill.ContentType), (r24v7 androidx.compose.ui.autofill.ContentType) binds: [B:10:0x004f, B:62:0x0189] A[DONT_GENERATE, DONT_INLINE]
      0x018b: PHI (r25v6 java.lang.Boolean) = (r25v5 java.lang.Boolean), (r25v7 java.lang.Boolean) binds: [B:10:0x004f, B:62:0x0189] A[DONT_GENERATE, DONT_INLINE]
      0x018b: PHI (r26v6 androidx.compose.ui.semantics.Role) = (r26v5 androidx.compose.ui.semantics.Role), (r26v7 androidx.compose.ui.semantics.Role) binds: [B:10:0x004f, B:62:0x0189] A[DONT_GENERATE, DONT_INLINE]
      0x018b: PHI (r27v6 boolean) = (r27v5 boolean), (r27v7 boolean) binds: [B:10:0x004f, B:62:0x0189] A[DONT_GENERATE, DONT_INLINE]
      0x018b: PHI (r28v7 java.lang.Integer) = (r28v6 java.lang.Integer), (r28v8 java.lang.Integer) binds: [B:10:0x004f, B:62:0x0189] A[DONT_GENERATE, DONT_INLINE]
      0x018b: PHI (r29v6 androidx.compose.ui.text.AnnotatedString) = (r29v5 androidx.compose.ui.text.AnnotatedString), (r29v7 androidx.compose.ui.text.AnnotatedString) binds: [B:10:0x004f, B:62:0x0189] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:64:0x018d A[LOOP:0: B:9:0x0043->B:64:0x018d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:95:0x024f  */
    public static final void populate(final ViewStructure viewStructure, SemanticsInfo semanticsInfo, AutofillId autofillId, String str, RectManager rectManager) {
        long j;
        char c;
        long j2;
        ToggleableState toggleableState;
        ContentDataType contentDataType;
        boolean z;
        ContentType contentType;
        Boolean bool;
        Role role;
        boolean z2;
        Integer num;
        AnnotatedString annotatedString;
        List list;
        Integer numValueOf;
        String strM6089toLegacyClassNameV4PA4sw;
        String[] contentHints;
        String[] contentHints2;
        MutableScatterMap<SemanticsPropertyKey<?>, Object> props$ui_release;
        SemanticsProperties semanticsProperties;
        SemanticsProperties semanticsProperties2;
        MutableScatterMap<SemanticsPropertyKey<?>, Object> props$ui_release2;
        ToggleableState toggleableState2;
        int i;
        final AutofillApi26Helper autofillApi26Helper = AutofillApi26Helper.INSTANCE;
        SemanticsProperties semanticsProperties3 = SemanticsProperties.INSTANCE;
        SemanticsActions semanticsActions = SemanticsActions.INSTANCE;
        SemanticsConfiguration semanticsConfiguration = semanticsInfo.getSemanticsConfiguration();
        int i2 = 8;
        if (semanticsConfiguration == null || (props$ui_release2 = semanticsConfiguration.getProps$ui_release()) == null) {
            j = 255;
            c = 7;
            j2 = -9187201950435737472L;
            toggleableState = null;
            contentDataType = null;
            z = false;
            contentType = null;
            bool = null;
            role = null;
            z2 = false;
            num = null;
            annotatedString = null;
        } else {
            MutableScatterMap<SemanticsPropertyKey<?>, Object> mutableScatterMap = props$ui_release2;
            Object[] objArr = mutableScatterMap.keys;
            j = 255;
            Object[] objArr2 = mutableScatterMap.values;
            long[] jArr = mutableScatterMap.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i3 = 0;
                c = 7;
                contentDataType = null;
                z = false;
                toggleableState2 = null;
                contentType = null;
                bool = null;
                role = null;
                z2 = false;
                num = null;
                annotatedString = null;
                j2 = -9187201950435737472L;
                while (true) {
                    long j3 = jArr[i3];
                    Object[] objArr3 = objArr2;
                    if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i4 = 8 - ((~(i3 - length)) >>> 31);
                        int i5 = 0;
                        while (i5 < i4) {
                            if ((j3 & 255) < 128) {
                                int i6 = (i3 << 3) + i5;
                                Object obj = objArr[i6];
                                Object obj2 = objArr3[i6];
                                SemanticsPropertyKey semanticsPropertyKey = (SemanticsPropertyKey) obj;
                                i = i2;
                                if (Intrinsics.areEqual(semanticsPropertyKey, semanticsProperties3.getContentDataType())) {
                                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.compose.ui.autofill.ContentDataType");
                                    contentDataType = (ContentDataType) obj2;
                                } else if (Intrinsics.areEqual(semanticsPropertyKey, semanticsProperties3.getContentDescription())) {
                                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.collections.List<kotlin.String>");
                                    String str2 = (String) CollectionsKt.firstOrNull((List) obj2);
                                    if (str2 != null) {
                                        autofillApi26Helper.setContentDescription(viewStructure, str2);
                                    }
                                } else if (Intrinsics.areEqual(semanticsPropertyKey, semanticsProperties3.getContentType())) {
                                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.compose.ui.autofill.ContentType");
                                    contentType = (ContentType) obj2;
                                } else if (Intrinsics.areEqual(semanticsPropertyKey, semanticsProperties3.getEditableText())) {
                                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.compose.ui.text.AnnotatedString");
                                    annotatedString = (AnnotatedString) obj2;
                                } else if (Intrinsics.areEqual(semanticsPropertyKey, semanticsProperties3.getFocused())) {
                                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Boolean");
                                    autofillApi26Helper.setFocused(viewStructure, ((Boolean) obj2).booleanValue());
                                } else if (Intrinsics.areEqual(semanticsPropertyKey, semanticsProperties3.getMaxTextLength())) {
                                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Int");
                                    num = (Integer) obj2;
                                } else if (Intrinsics.areEqual(semanticsPropertyKey, semanticsProperties3.getPassword())) {
                                    z2 = true;
                                } else if (Intrinsics.areEqual(semanticsPropertyKey, semanticsProperties3.getRole())) {
                                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.compose.ui.semantics.Role");
                                    role = (Role) obj2;
                                } else if (Intrinsics.areEqual(semanticsPropertyKey, semanticsProperties3.getSelected())) {
                                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Boolean");
                                    bool = (Boolean) obj2;
                                } else if (Intrinsics.areEqual(semanticsPropertyKey, semanticsProperties3.getToggleableState())) {
                                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.compose.ui.state.ToggleableState");
                                    toggleableState2 = (ToggleableState) obj2;
                                } else if (Intrinsics.areEqual(semanticsPropertyKey, semanticsActions.getOnClick())) {
                                    autofillApi26Helper.setClickable(viewStructure, true);
                                } else if (Intrinsics.areEqual(semanticsPropertyKey, semanticsActions.getOnLongClick())) {
                                    autofillApi26Helper.setLongClickable(viewStructure, true);
                                } else if (Intrinsics.areEqual(semanticsPropertyKey, semanticsActions.getRequestFocus())) {
                                    autofillApi26Helper.setFocusable(viewStructure, true);
                                } else if (Intrinsics.areEqual(semanticsPropertyKey, semanticsActions.getSetText())) {
                                    z = true;
                                }
                            } else {
                                i = i2;
                            }
                            j3 >>= i;
                            i5++;
                            i2 = i;
                        }
                        if (i4 != i2) {
                            break;
                        }
                        if (i3 != length) {
                            break;
                        }
                        i3++;
                        objArr2 = objArr3;
                        i2 = 8;
                    } else if (i3 != length) {
                        break;
                        break;
                    } else {
                        i3++;
                        objArr2 = objArr3;
                        i2 = 8;
                    }
                }
            } else {
                c = 7;
                j2 = -9187201950435737472L;
                contentDataType = null;
                z = false;
                toggleableState2 = null;
                contentType = null;
                bool = null;
                role = null;
                z2 = false;
                num = null;
                annotatedString = null;
            }
            toggleableState = toggleableState2;
        }
        SemanticsConfiguration semanticsConfigurationMergedSemanticsConfiguration = SemanticsInfoKt.mergedSemanticsConfiguration(semanticsInfo);
        if (semanticsConfigurationMergedSemanticsConfiguration == null || (props$ui_release = semanticsConfigurationMergedSemanticsConfiguration.getProps$ui_release()) == null) {
            list = null;
        } else {
            MutableScatterMap<SemanticsPropertyKey<?>, Object> mutableScatterMap2 = props$ui_release;
            Object[] objArr4 = mutableScatterMap2.keys;
            Object[] objArr5 = mutableScatterMap2.values;
            long[] jArr2 = mutableScatterMap2.metadata;
            int length2 = jArr2.length - 2;
            if (length2 >= 0) {
                int i7 = 0;
                list = null;
                while (true) {
                    long j4 = jArr2[i7];
                    if ((((~j4) << c) & j4 & j2) != j2) {
                        int i8 = 8 - ((~(i7 - length2)) >>> 31);
                        int i9 = 0;
                        while (i9 < i8) {
                            if ((j4 & j) < 128) {
                                int i10 = (i7 << 3) + i9;
                                Object obj3 = objArr4[i10];
                                Object obj4 = objArr5[i10];
                                SemanticsPropertyKey semanticsPropertyKey2 = (SemanticsPropertyKey) obj3;
                                semanticsProperties2 = semanticsProperties3;
                                if (Intrinsics.areEqual(semanticsPropertyKey2, semanticsProperties2.getDisabled())) {
                                    autofillApi26Helper.setEnabled(viewStructure, false);
                                } else if (Intrinsics.areEqual(semanticsPropertyKey2, semanticsProperties2.getText())) {
                                    Intrinsics.checkNotNull(obj4, "null cannot be cast to non-null type kotlin.collections.List<androidx.compose.ui.text.AnnotatedString>");
                                    list = (List) obj4;
                                }
                            } else {
                                semanticsProperties2 = semanticsProperties3;
                            }
                            j4 >>= 8;
                            i9++;
                            semanticsProperties3 = semanticsProperties2;
                        }
                        semanticsProperties = semanticsProperties3;
                        if (i8 != 8) {
                            break;
                        }
                    } else {
                        semanticsProperties = semanticsProperties3;
                    }
                    if (i7 == length2) {
                        break;
                    }
                    i7++;
                    semanticsProperties3 = semanticsProperties;
                }
            } else {
                list = null;
            }
        }
        Integer numValueOf2 = Integer.valueOf(semanticsInfo.getSemanticsId());
        numValueOf2.intValue();
        if (semanticsInfo.getParentInfo() == null) {
            numValueOf2 = null;
        }
        int iIntValue = numValueOf2 != null ? numValueOf2.intValue() : -1;
        autofillApi26Helper.setAutofillId(viewStructure, autofillId, iIntValue);
        autofillApi26Helper.setId(viewStructure, iIntValue, str, null, null);
        if (contentDataType != null) {
            numValueOf = Integer.valueOf(ContentDataType_androidKt.getDataType(contentDataType));
        } else if (z) {
            numValueOf = 1;
        } else {
            numValueOf = toggleableState != null ? 2 : null;
        }
        if (numValueOf != null) {
            autofillApi26Helper.setAutofillType(viewStructure, numValueOf.intValue());
        }
        if (contentType != null && (contentHints2 = ContentType_androidKt.getContentHints(contentType)) != null) {
            autofillApi26Helper.setAutofillHints(viewStructure, contentHints2);
        }
        rectManager.getRects().withRect(semanticsInfo.getSemanticsId(), new Function4<Integer, Integer, Integer, Integer, Unit>() { // from class: androidx.compose.ui.autofill.PopulateViewStructure_androidKt.populate.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(Integer num2, Integer num3, Integer num4, Integer num5) {
                invoke(num2.intValue(), num3.intValue(), num4.intValue(), num5.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i11, int i12, int i13, int i14) {
                autofillApi26Helper.setDimens(viewStructure, i11, i12, 0, 0, i13 - i11, i14 - i12);
            }
        });
        if (bool != null) {
            autofillApi26Helper.setSelected(viewStructure, bool.booleanValue());
        }
        if (toggleableState != null) {
            autofillApi26Helper.setCheckable(viewStructure, true);
            autofillApi26Helper.setChecked(viewStructure, toggleableState == ToggleableState.On);
        } else if (bool != null) {
            if (!(role == null ? false : Role.m6111equalsimpl0(role.getValue(), Role.INSTANCE.m6122getTabo7Vup1c()))) {
                autofillApi26Helper.setCheckable(viewStructure, true);
                autofillApi26Helper.setChecked(viewStructure, bool.booleanValue());
            }
        }
        boolean z3 = z2 || (contentType != null && (contentHints = ContentType_androidKt.getContentHints(contentType)) != null && ArraysKt.contains(contentHints, (String) ArraysKt.first(ContentType_androidKt.getContentHints(ContentType.INSTANCE.getPassword()))));
        if (z3) {
            autofillApi26Helper.setDataIsSensitive(viewStructure, true);
        }
        autofillApi26Helper.setVisibility(viewStructure, semanticsInfo.isTransparent() ? 4 : 0);
        if (list != null) {
            int size = list.size();
            String str3 = "";
            for (int i11 = 0; i11 < size; i11++) {
                str3 = str3 + ((AnnotatedString) list.get(i11)).getText() + '\n';
            }
            autofillApi26Helper.setText(viewStructure, str3);
            autofillApi26Helper.setClassName(viewStructure, AndroidComposeViewAccessibilityDelegateCompat.TextClassName);
        }
        if (semanticsInfo.getChildrenInfo().isEmpty() && role != null && (strM6089toLegacyClassNameV4PA4sw = SemanticsUtils_androidKt.m6089toLegacyClassNameV4PA4sw(role.getValue())) != null) {
            autofillApi26Helper.setClassName(viewStructure, strM6089toLegacyClassNameV4PA4sw);
        }
        if (z) {
            autofillApi26Helper.setClassName(viewStructure, AndroidComposeViewAccessibilityDelegateCompat.TextFieldClassName);
            if (Build.VERSION.SDK_INT >= 28 && num != null) {
                AutofillApi28Helper.INSTANCE.setMaxTextLength(viewStructure, num.intValue());
            }
            if (annotatedString != null) {
                autofillApi26Helper.setAutofillValue(viewStructure, autofillApi26Helper.getAutofillTextValue(annotatedString.getText()));
            }
            if (z3) {
                autofillApi26Helper.setInputType(viewStructure, 129);
            }
        }
    }
}
