package androidx.compose.ui.text;

import kotlin.Metadata;

/* JADX INFO: compiled from: ParagraphIntrinsics.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/text/ParagraphIntrinsics;", "", "minIntrinsicWidth", "", "getMinIntrinsicWidth", "()F", "maxIntrinsicWidth", "getMaxIntrinsicWidth", "hasStaleResolvedFonts", "", "getHasStaleResolvedFonts", "()Z", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface ParagraphIntrinsics {

    /* JADX INFO: renamed from: androidx.compose.ui.text.ParagraphIntrinsics$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ParagraphIntrinsics.kt */
    public final /* synthetic */ class CC {
        public static boolean $default$getHasStaleResolvedFonts(ParagraphIntrinsics _this) {
            return false;
        }
    }

    boolean getHasStaleResolvedFonts();

    float getMaxIntrinsicWidth();

    float getMinIntrinsicWidth();
}
