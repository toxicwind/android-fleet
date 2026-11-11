package com.lagradost.cloudstream3.ui.library;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.lagradost.cloudstream3.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: LibraryScrollTransformer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/ui/library/LibraryScrollTransformer;", "Landroidx/viewpager2/widget/ViewPager2$PageTransformer;", "<init>", "()V", "transformPage", "", "page", "Landroid/view/View;", "position", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LibraryScrollTransformer implements ViewPager2.PageTransformer {
    @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
    public void transformPage(View page, float position) {
        Intrinsics.checkNotNullParameter(page, "page");
        int iRoundToInt = MathKt.roundToInt((-position) * page.getWidth());
        page.findViewById(R.id.page_recyclerview).setPadding(iRoundToInt, 0, -iRoundToInt, 0);
    }
}
