package androidx.compose.ui.text.android;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextLayout.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\tJ\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\tJ\u000e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\tJ\u000e\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\tJ(\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/compose/ui/text/android/HorizontalPositionCache;", "", TtmlNode.TAG_LAYOUT, "Landroidx/compose/ui/text/android/TextLayout;", "<init>", "(Landroidx/compose/ui/text/android/TextLayout;)V", "getLayout", "()Landroidx/compose/ui/text/android/TextLayout;", "cachedKey", "", "cachedValue", "", "getPrimaryDownstream", TypedValues.CycleType.S_WAVE_OFFSET, "getPrimaryUpstream", "getSecondaryDownstream", "getSecondaryUpstream", "get", "upstream", "", "cache", "primary", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class HorizontalPositionCache {
    private int cachedKey = -1;
    private float cachedValue;
    private final TextLayout layout;

    public HorizontalPositionCache(TextLayout textLayout) {
        this.layout = textLayout;
    }

    public final TextLayout getLayout() {
        return this.layout;
    }

    public final float getPrimaryDownstream(int offset) {
        return get(offset, false, false, true);
    }

    public final float getPrimaryUpstream(int offset) {
        return get(offset, true, true, true);
    }

    public final float getSecondaryDownstream(int offset) {
        return get(offset, false, false, false);
    }

    public final float getSecondaryUpstream(int offset) {
        return get(offset, true, true, false);
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0020  */
    private final float get(int offset, boolean upstream, boolean cache, boolean primary) {
        boolean z;
        float secondaryHorizontal;
        int i = 1;
        if (upstream) {
            int lineForOffset = LayoutCompat_androidKt.getLineForOffset(this.layout.getLayout(), offset, upstream);
            int lineStart = this.layout.getLineStart(lineForOffset);
            int lineEnd = this.layout.getLineEnd(lineForOffset);
            if (offset == lineStart || offset == lineEnd) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        int i2 = offset * 4;
        if (!primary) {
            i = z ? 2 : 3;
        } else if (z) {
            i = 0;
        }
        int i3 = i2 + i;
        if (this.cachedKey == i3) {
            return this.cachedValue;
        }
        TextLayout textLayout = this.layout;
        if (primary) {
            secondaryHorizontal = textLayout.getPrimaryHorizontal(offset, upstream);
        } else {
            secondaryHorizontal = textLayout.getSecondaryHorizontal(offset, upstream);
        }
        if (cache) {
            this.cachedKey = i3;
            this.cachedValue = secondaryHorizontal;
        }
        return secondaryHorizontal;
    }
}
