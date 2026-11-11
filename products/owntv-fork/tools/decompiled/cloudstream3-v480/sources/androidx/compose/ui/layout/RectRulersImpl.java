package androidx.compose.ui.layout;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: compiled from: RectRulers.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0018\u001a\u00020\u0003H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR\u001a\u0010\u0015\u001a\u00020\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011¨\u0006\u0019"}, d2 = {"Landroidx/compose/ui/layout/RectRulersImpl;", "Landroidx/compose/ui/layout/RectRulers;", "name", "", "<init>", "(Ljava/lang/String;)V", TtmlNode.LEFT, "Landroidx/compose/ui/layout/VerticalRuler;", "getLeft", "()Landroidx/compose/ui/layout/VerticalRuler;", "setLeft", "(Landroidx/compose/ui/layout/VerticalRuler;)V", "top", "Landroidx/compose/ui/layout/HorizontalRuler;", "getTop", "()Landroidx/compose/ui/layout/HorizontalRuler;", "setTop", "(Landroidx/compose/ui/layout/HorizontalRuler;)V", TtmlNode.RIGHT, "getRight", "setRight", "bottom", "getBottom", "setBottom", "toString", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class RectRulersImpl implements RectRulers {
    private final String name;
    private VerticalRuler left = new VerticalRuler();
    private HorizontalRuler top = new HorizontalRuler();
    private VerticalRuler right = new VerticalRuler();
    private HorizontalRuler bottom = new HorizontalRuler();

    public RectRulersImpl(String str) {
        this.name = str;
    }

    @Override // androidx.compose.ui.layout.RectRulers
    public VerticalRuler getLeft() {
        return this.left;
    }

    public void setLeft(VerticalRuler verticalRuler) {
        this.left = verticalRuler;
    }

    @Override // androidx.compose.ui.layout.RectRulers
    public HorizontalRuler getTop() {
        return this.top;
    }

    public void setTop(HorizontalRuler horizontalRuler) {
        this.top = horizontalRuler;
    }

    @Override // androidx.compose.ui.layout.RectRulers
    public VerticalRuler getRight() {
        return this.right;
    }

    public void setRight(VerticalRuler verticalRuler) {
        this.right = verticalRuler;
    }

    @Override // androidx.compose.ui.layout.RectRulers
    public HorizontalRuler getBottom() {
        return this.bottom;
    }

    public void setBottom(HorizontalRuler horizontalRuler) {
        this.bottom = horizontalRuler;
    }

    public String toString() {
        if (this.name == null) {
            return super.toString();
        }
        return "RectRulers(" + this.name + ')';
    }
}
