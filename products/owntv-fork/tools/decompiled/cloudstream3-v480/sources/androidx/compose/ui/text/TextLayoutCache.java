package androidx.compose.ui.text;

import androidx.collection.LruCache;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: TextMeasurer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\tR\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/text/TextLayoutCache;", "", "capacity", "", "<init>", "(I)V", "cache", "Landroidx/collection/LruCache;", "Landroidx/compose/ui/text/CacheTextLayoutInput;", "Landroidx/compose/ui/text/TextLayoutResult;", "singleSizeCacheInput", "singleSizeCacheResult", "get", "key", "Landroidx/compose/ui/text/TextLayoutInput;", "put", "", ES6Iterator.VALUE_PROPERTY, "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TextLayoutCache {
    public static final int $stable = 8;
    private final LruCache<CacheTextLayoutInput, TextLayoutResult> cache;
    private CacheTextLayoutInput singleSizeCacheInput;
    private TextLayoutResult singleSizeCacheResult;

    public TextLayoutCache() {
        this(0, 1, null);
    }

    public TextLayoutCache(int i) {
        this.cache = i != 1 ? new LruCache<>(i) : null;
    }

    public /* synthetic */ TextLayoutCache(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 8 : i);
    }

    public final TextLayoutResult get(TextLayoutInput key) {
        TextLayoutResult textLayoutResult;
        CacheTextLayoutInput cacheTextLayoutInput = new CacheTextLayoutInput(key);
        LruCache<CacheTextLayoutInput, TextLayoutResult> lruCache = this.cache;
        if (lruCache != null) {
            textLayoutResult = lruCache.get(cacheTextLayoutInput);
        } else {
            if (!Intrinsics.areEqual(this.singleSizeCacheInput, cacheTextLayoutInput)) {
                return null;
            }
            textLayoutResult = this.singleSizeCacheResult;
        }
        if (textLayoutResult == null || textLayoutResult.getMultiParagraph().getIntrinsics().getHasStaleResolvedFonts()) {
            return null;
        }
        return textLayoutResult;
    }

    public final void put(TextLayoutInput key, TextLayoutResult value) {
        LruCache<CacheTextLayoutInput, TextLayoutResult> lruCache = this.cache;
        if (lruCache != null) {
            lruCache.put(new CacheTextLayoutInput(key), value);
        } else {
            this.singleSizeCacheInput = new CacheTextLayoutInput(key);
            this.singleSizeCacheResult = value;
        }
    }
}
