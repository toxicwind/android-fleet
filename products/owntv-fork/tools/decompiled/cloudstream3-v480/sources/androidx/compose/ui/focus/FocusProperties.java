package androidx.compose.ui.focus;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: FocusProperties.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR$\u0010\u0012\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR$\u0010\u0015\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR$\u0010\u0018\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000eR$\u0010\u001b\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\f\"\u0004\b\u001d\u0010\u000eR$\u0010\u001e\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010\f\"\u0004\b \u0010\u000eR$\u0010!\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\"\u0010\f\"\u0004\b#\u0010\u000eRB\u0010'\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\t0%2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\t0%8V@WX\u0097\u000e¢\u0006\u0012\u0012\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-RF\u00101\u001a\u0013\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020/0%¢\u0006\u0002\b02\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020/0%¢\u0006\u0002\b08V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b2\u0010+\"\u0004\b3\u0010-RB\u00104\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\t0%2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\t0%8V@WX\u0097\u000e¢\u0006\u0012\u0012\u0004\b5\u0010)\u001a\u0004\b6\u0010+\"\u0004\b7\u0010-RF\u00108\u001a\u0013\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020/0%¢\u0006\u0002\b02\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020/0%¢\u0006\u0002\b08V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b9\u0010+\"\u0004\b:\u0010-ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006;À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/focus/FocusProperties;", "", "canFocus", "", "getCanFocus", "()Z", "setCanFocus", "(Z)V", "_", "Landroidx/compose/ui/focus/FocusRequester;", "next", "getNext", "()Landroidx/compose/ui/focus/FocusRequester;", "setNext", "(Landroidx/compose/ui/focus/FocusRequester;)V", "previous", "getPrevious", "setPrevious", "up", "getUp", "setUp", "down", "getDown", "setDown", TtmlNode.LEFT, "getLeft", "setLeft", TtmlNode.RIGHT, "getRight", "setRight", "start", "getStart", "setStart", TtmlNode.END, "getEnd", "setEnd", ES6Iterator.VALUE_PROPERTY, "Lkotlin/Function1;", "Landroidx/compose/ui/focus/FocusDirection;", "enter", "getEnter$annotations", "()V", "getEnter", "()Lkotlin/jvm/functions/Function1;", "setEnter", "(Lkotlin/jvm/functions/Function1;)V", "Landroidx/compose/ui/focus/FocusEnterExitScope;", "", "Lkotlin/ExtensionFunctionType;", "onEnter", "getOnEnter", "setOnEnter", "exit", "getExit$annotations", "getExit", "setExit", "onExit", "getOnExit", "setOnExit", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface FocusProperties {
    boolean getCanFocus();

    FocusRequester getDown();

    FocusRequester getEnd();

    Function1<FocusDirection, FocusRequester> getEnter();

    Function1<FocusDirection, FocusRequester> getExit();

    FocusRequester getLeft();

    FocusRequester getNext();

    Function1<FocusEnterExitScope, Unit> getOnEnter();

    Function1<FocusEnterExitScope, Unit> getOnExit();

    FocusRequester getPrevious();

    FocusRequester getRight();

    FocusRequester getStart();

    FocusRequester getUp();

    void setCanFocus(boolean z);

    void setDown(FocusRequester focusRequester);

    void setEnd(FocusRequester focusRequester);

    @Deprecated(message = "Use onEnter instead", replaceWith = @ReplaceWith(expression = "onEnter", imports = {}))
    void setEnter(Function1<? super FocusDirection, FocusRequester> function1);

    @Deprecated(message = "Use onExit instead", replaceWith = @ReplaceWith(expression = "onExit", imports = {}))
    void setExit(Function1<? super FocusDirection, FocusRequester> function1);

    void setLeft(FocusRequester focusRequester);

    void setNext(FocusRequester focusRequester);

    void setOnEnter(Function1<? super FocusEnterExitScope, Unit> function1);

    void setOnExit(Function1<? super FocusEnterExitScope, Unit> function1);

    void setPrevious(FocusRequester focusRequester);

    void setRight(FocusRequester focusRequester);

    void setStart(FocusRequester focusRequester);

    void setUp(FocusRequester focusRequester);

    /* JADX INFO: renamed from: androidx.compose.ui.focus.FocusProperties$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: FocusProperties.kt */
    public final /* synthetic */ class CC {
        public static void $default$setDown(FocusProperties _this, FocusRequester focusRequester) {
        }

        public static void $default$setEnd(FocusProperties _this, FocusRequester focusRequester) {
        }

        public static void $default$setLeft(FocusProperties _this, FocusRequester focusRequester) {
        }

        public static void $default$setNext(FocusProperties _this, FocusRequester focusRequester) {
        }

        public static void $default$setOnEnter(FocusProperties _this, Function1 function1) {
        }

        public static void $default$setOnExit(FocusProperties _this, Function1 function1) {
        }

        public static void $default$setPrevious(FocusProperties _this, FocusRequester focusRequester) {
        }

        public static void $default$setRight(FocusProperties _this, FocusRequester focusRequester) {
        }

        public static void $default$setStart(FocusProperties _this, FocusRequester focusRequester) {
        }

        public static void $default$setUp(FocusProperties _this, FocusRequester focusRequester) {
        }

        public static /* synthetic */ void getEnter$annotations() {
        }

        public static /* synthetic */ void getExit$annotations() {
        }
    }
}
