package androidx.compose.ui.layout;

import kotlin.Metadata;

/* JADX INFO: compiled from: WindowInsetsRulers.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000 \f2\u00020\u0001:\u0001\fJ\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005\u0082\u0001\u0002\r\u000eø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/layout/WindowInsetsRulers;", "", "current", "Landroidx/compose/ui/layout/RectRulers;", "getCurrent", "()Landroidx/compose/ui/layout/RectRulers;", "maximum", "getMaximum", "getAnimation", "Landroidx/compose/ui/layout/WindowInsetsAnimation;", "scope", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Companion", "Landroidx/compose/ui/layout/InnermostInsetsRulers;", "Landroidx/compose/ui/layout/WindowInsetsRulersImpl;", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface WindowInsetsRulers {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    WindowInsetsAnimation getAnimation(Placeable.PlacementScope scope);

    RectRulers getCurrent();

    RectRulers getMaximum();

    /* JADX INFO: compiled from: WindowInsetsRulers.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010 \u001a\u00020\u00052\u0012\u0010!\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\"\"\u00020\u0005¢\u0006\u0002\u0010#R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0007R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007¨\u0006$"}, d2 = {"Landroidx/compose/ui/layout/WindowInsetsRulers$Companion;", "", "<init>", "()V", "CaptionBar", "Landroidx/compose/ui/layout/WindowInsetsRulers;", "getCaptionBar", "()Landroidx/compose/ui/layout/WindowInsetsRulers;", "DisplayCutout", "getDisplayCutout", "Ime", "getIme", "MandatorySystemGestures", "getMandatorySystemGestures", "NavigationBars", "getNavigationBars", "StatusBars", "getStatusBars", "SystemBars", "getSystemBars", "SystemGestures", "getSystemGestures", "TappableElement", "getTappableElement", "Waterfall", "getWaterfall", "SafeDrawing", "getSafeDrawing", "SafeGestures", "getSafeGestures", "SafeContent", "getSafeContent", "innermostOf", "windowInsetsRulers", "", "([Landroidx/compose/ui/layout/WindowInsetsRulers;)Landroidx/compose/ui/layout/WindowInsetsRulers;", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final WindowInsetsRulers CaptionBar;
        private static final WindowInsetsRulers DisplayCutout;
        private static final WindowInsetsRulers Ime;
        private static final WindowInsetsRulers MandatorySystemGestures;
        private static final WindowInsetsRulers NavigationBars;
        private static final WindowInsetsRulers SafeContent;
        private static final WindowInsetsRulers SafeDrawing;
        private static final WindowInsetsRulers SafeGestures;
        private static final WindowInsetsRulers StatusBars;
        private static final WindowInsetsRulers SystemBars;
        private static final WindowInsetsRulers SystemGestures;
        private static final WindowInsetsRulers TappableElement;
        private static final WindowInsetsRulers Waterfall;

        private Companion() {
        }

        static {
            WindowInsetsRulersImpl windowInsetsRulersImpl = new WindowInsetsRulersImpl("caption bar");
            CaptionBar = windowInsetsRulersImpl;
            WindowInsetsRulersImpl windowInsetsRulersImpl2 = new WindowInsetsRulersImpl("display cutout");
            DisplayCutout = windowInsetsRulersImpl2;
            WindowInsetsRulersImpl windowInsetsRulersImpl3 = new WindowInsetsRulersImpl("ime");
            Ime = windowInsetsRulersImpl3;
            WindowInsetsRulersImpl windowInsetsRulersImpl4 = new WindowInsetsRulersImpl("mandatory system gestures");
            MandatorySystemGestures = windowInsetsRulersImpl4;
            WindowInsetsRulersImpl windowInsetsRulersImpl5 = new WindowInsetsRulersImpl("navigation bars");
            NavigationBars = windowInsetsRulersImpl5;
            WindowInsetsRulersImpl windowInsetsRulersImpl6 = new WindowInsetsRulersImpl("status bars");
            StatusBars = windowInsetsRulersImpl6;
            SystemBars = new InnermostInsetsRulers("system bars", new WindowInsetsRulers[]{windowInsetsRulersImpl6, windowInsetsRulersImpl5, windowInsetsRulersImpl});
            WindowInsetsRulersImpl windowInsetsRulersImpl7 = new WindowInsetsRulersImpl("system gestures");
            SystemGestures = windowInsetsRulersImpl7;
            WindowInsetsRulersImpl windowInsetsRulersImpl8 = new WindowInsetsRulersImpl("tappable element");
            TappableElement = windowInsetsRulersImpl8;
            WindowInsetsRulersImpl windowInsetsRulersImpl9 = new WindowInsetsRulersImpl("waterfall");
            Waterfall = windowInsetsRulersImpl9;
            SafeDrawing = new InnermostInsetsRulers("safe drawing", new WindowInsetsRulers[]{windowInsetsRulersImpl6, windowInsetsRulersImpl5, windowInsetsRulersImpl, windowInsetsRulersImpl2, windowInsetsRulersImpl3, windowInsetsRulersImpl8});
            SafeGestures = new InnermostInsetsRulers("safe gestures", new WindowInsetsRulers[]{windowInsetsRulersImpl4, windowInsetsRulersImpl7, windowInsetsRulersImpl8, windowInsetsRulersImpl9});
            SafeContent = new InnermostInsetsRulers("safe content", new WindowInsetsRulers[]{windowInsetsRulersImpl6, windowInsetsRulersImpl5, windowInsetsRulersImpl, windowInsetsRulersImpl3, windowInsetsRulersImpl7, windowInsetsRulersImpl4, windowInsetsRulersImpl8, windowInsetsRulersImpl2, windowInsetsRulersImpl9});
        }

        public final WindowInsetsRulers getCaptionBar() {
            return CaptionBar;
        }

        public final WindowInsetsRulers getDisplayCutout() {
            return DisplayCutout;
        }

        public final WindowInsetsRulers getIme() {
            return Ime;
        }

        public final WindowInsetsRulers getMandatorySystemGestures() {
            return MandatorySystemGestures;
        }

        public final WindowInsetsRulers getNavigationBars() {
            return NavigationBars;
        }

        public final WindowInsetsRulers getStatusBars() {
            return StatusBars;
        }

        public final WindowInsetsRulers getSystemBars() {
            return SystemBars;
        }

        public final WindowInsetsRulers getSystemGestures() {
            return SystemGestures;
        }

        public final WindowInsetsRulers getTappableElement() {
            return TappableElement;
        }

        public final WindowInsetsRulers getWaterfall() {
            return Waterfall;
        }

        public final WindowInsetsRulers getSafeDrawing() {
            return SafeDrawing;
        }

        public final WindowInsetsRulers getSafeGestures() {
            return SafeGestures;
        }

        public final WindowInsetsRulers getSafeContent() {
            return SafeContent;
        }

        public final WindowInsetsRulers innermostOf(WindowInsetsRulers... windowInsetsRulers) {
            return new InnermostInsetsRulers(null, windowInsetsRulers);
        }
    }
}
