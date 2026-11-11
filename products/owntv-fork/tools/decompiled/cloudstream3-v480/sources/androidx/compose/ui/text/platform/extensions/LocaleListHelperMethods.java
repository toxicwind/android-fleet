package androidx.compose.ui.text.platform.extensions;

import androidx.compose.material3.internal.Listener$$ExternalSyntheticApiModelOutline0;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.platform.AndroidTextPaint;
import androidx.core.content.ContextCompat$$ExternalSyntheticApiModelOutline0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: LocaleExtensions.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/text/platform/extensions/LocaleListHelperMethods;", "", "<init>", "()V", "localeSpan", "localeList", "Landroidx/compose/ui/text/intl/LocaleList;", "setTextLocales", "", "textPaint", "Landroidx/compose/ui/text/platform/AndroidTextPaint;", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LocaleListHelperMethods {
    public static final int $stable = 0;
    public static final LocaleListHelperMethods INSTANCE = new LocaleListHelperMethods();

    private LocaleListHelperMethods() {
    }

    public final Object localeSpan(LocaleList localeList) {
        LocaleList localeList2 = localeList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(localeList2, 10));
        Iterator<Locale> it = localeList2.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getPlatformLocale());
        }
        java.util.Locale[] localeArr = (java.util.Locale[]) arrayList.toArray(new java.util.Locale[0]);
        return ContextCompat$$ExternalSyntheticApiModelOutline0.m7174m(Listener$$ExternalSyntheticApiModelOutline0.m((java.util.Locale[]) Arrays.copyOf(localeArr, localeArr.length)));
    }

    public final void setTextLocales(AndroidTextPaint textPaint, LocaleList localeList) {
        LocaleList localeList2 = localeList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(localeList2, 10));
        Iterator<Locale> it = localeList2.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getPlatformLocale());
        }
        java.util.Locale[] localeArr = (java.util.Locale[]) arrayList.toArray(new java.util.Locale[0]);
        textPaint.setTextLocales(Listener$$ExternalSyntheticApiModelOutline0.m((java.util.Locale[]) Arrays.copyOf(localeArr, localeArr.length)));
    }
}
