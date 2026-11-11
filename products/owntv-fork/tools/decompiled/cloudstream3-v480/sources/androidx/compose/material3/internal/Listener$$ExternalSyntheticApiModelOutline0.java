package androidx.compose.material3.internal;

import android.os.LocaleList;
import android.view.accessibility.AccessibilityManager$AccessibilityServicesStateChangeListener;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillManager$AutofillCallback;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.DeleteGesture;
import android.view.inputmethod.DeleteRangeGesture;
import android.view.inputmethod.EditorBoundsInfo;
import android.view.inputmethod.HandwritingGesture;
import android.view.inputmethod.InsertGesture;
import android.view.inputmethod.JoinOrSplitGesture;
import android.view.inputmethod.RemoveSpaceGesture;
import android.view.inputmethod.SelectGesture;
import android.view.inputmethod.SelectRangeGesture;
import android.view.translation.ViewTranslationRequest;
import android.view.translation.ViewTranslationResponse;
import java.util.Locale;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Listener$$ExternalSyntheticApiModelOutline0 {
    public static /* synthetic */ LocaleList m(Locale[] localeArr) {
        return new LocaleList(localeArr);
    }

    public static /* bridge */ /* synthetic */ AccessibilityManager$AccessibilityServicesStateChangeListener m(Object obj) {
        return (AccessibilityManager$AccessibilityServicesStateChangeListener) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ AutofillManager$AutofillCallback m2624m(Object obj) {
        return (AutofillManager$AutofillCallback) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ AutofillManager m2625m(Object obj) {
        return (AutofillManager) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ AutofillValue m2626m(Object obj) {
        return (AutofillValue) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ DeleteGesture m2627m(Object obj) {
        return (DeleteGesture) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ DeleteRangeGesture m2628m(Object obj) {
        return (DeleteRangeGesture) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ EditorBoundsInfo.Builder m2629m() {
        return new EditorBoundsInfo.Builder();
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ HandwritingGesture m2630m(Object obj) {
        return (HandwritingGesture) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ InsertGesture m2631m(Object obj) {
        return (InsertGesture) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ JoinOrSplitGesture m2632m(Object obj) {
        return (JoinOrSplitGesture) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ RemoveSpaceGesture m2633m(Object obj) {
        return (RemoveSpaceGesture) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ SelectGesture m2634m(Object obj) {
        return (SelectGesture) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ SelectRangeGesture m2635m(Object obj) {
        return (SelectRangeGesture) obj;
    }

    public static /* synthetic */ ViewTranslationRequest.Builder m(AutofillId autofillId, long j) {
        return new ViewTranslationRequest.Builder(autofillId, j);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ViewTranslationResponse m2637m(Object obj) {
        return (ViewTranslationResponse) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m2639m() {
        return SelectGesture.class;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m2641m() {
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m2643m(Object obj) {
        return obj instanceof RemoveSpaceGesture;
    }

    /* JADX INFO: renamed from: m$1, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m2644m$1() {
        return SelectRangeGesture.class;
    }

    public static /* bridge */ /* synthetic */ boolean m$1(Object obj) {
        return obj instanceof JoinOrSplitGesture;
    }

    /* JADX INFO: renamed from: m$2, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m2645m$2() {
        return DeleteRangeGesture.class;
    }

    public static /* bridge */ /* synthetic */ boolean m$2(Object obj) {
        return obj instanceof DeleteGesture;
    }

    /* JADX INFO: renamed from: m$3, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m2646m$3() {
        return DeleteGesture.class;
    }

    public static /* bridge */ /* synthetic */ boolean m$3(Object obj) {
        return obj instanceof SelectGesture;
    }

    /* JADX INFO: renamed from: m$4, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m2647m$4() {
        return JoinOrSplitGesture.class;
    }

    public static /* bridge */ /* synthetic */ boolean m$4(Object obj) {
        return obj instanceof SelectRangeGesture;
    }

    /* JADX INFO: renamed from: m$5, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m2648m$5() {
        return InsertGesture.class;
    }

    public static /* bridge */ /* synthetic */ boolean m$5(Object obj) {
        return obj instanceof DeleteRangeGesture;
    }

    /* JADX INFO: renamed from: m$6, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m2649m$6() {
        return RemoveSpaceGesture.class;
    }

    public static /* bridge */ /* synthetic */ boolean m$6(Object obj) {
        return obj instanceof InsertGesture;
    }

    /* JADX INFO: renamed from: m$7, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m2650m$7() {
        return AutofillManager.class;
    }
}
