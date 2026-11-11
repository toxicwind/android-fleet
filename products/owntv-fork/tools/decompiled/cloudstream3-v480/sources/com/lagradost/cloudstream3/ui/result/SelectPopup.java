package com.lagradost.cloudstream3.ui.result;

import com.lagradost.cloudstream3.utils.UiText;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/SelectPopup;", "", "<init>", "()V", "SelectText", "SelectArray", "Lcom/lagradost/cloudstream3/ui/result/SelectPopup$SelectArray;", "Lcom/lagradost/cloudstream3/ui/result/SelectPopup$SelectText;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class SelectPopup {
    public /* synthetic */ SelectPopup(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u0017\u0010\u0014\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t0\u0007HÆ\u0003J;\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t0\u0007HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001f\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/SelectPopup$SelectText;", "Lcom/lagradost/cloudstream3/ui/result/SelectPopup;", "text", "Lcom/lagradost/cloudstream3/utils/UiText;", "options", "", "callback", "Lkotlin/Function1;", "", "", "<init>", "(Lcom/lagradost/cloudstream3/utils/UiText;Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "getText", "()Lcom/lagradost/cloudstream3/utils/UiText;", "getOptions", "()Ljava/util/List;", "getCallback", "()Lkotlin/jvm/functions/Function1;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SelectText extends SelectPopup {
        private final Function1<Integer, Unit> callback;
        private final List<UiText> options;
        private final UiText text;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SelectText copy$default(SelectText selectText, UiText uiText, List list, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                uiText = selectText.text;
            }
            if ((i & 2) != 0) {
                list = selectText.options;
            }
            if ((i & 4) != 0) {
                function1 = selectText.callback;
            }
            return selectText.copy(uiText, list, function1);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final UiText getText() {
            return this.text;
        }

        public final List<UiText> component2() {
            return this.options;
        }

        public final Function1<Integer, Unit> component3() {
            return this.callback;
        }

        public final SelectText copy(UiText text, List<? extends UiText> options, Function1<? super Integer, Unit> callback) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(callback, "callback");
            return new SelectText(text, options, callback);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SelectText)) {
                return false;
            }
            SelectText selectText = (SelectText) other;
            return Intrinsics.areEqual(this.text, selectText.text) && Intrinsics.areEqual(this.options, selectText.options) && Intrinsics.areEqual(this.callback, selectText.callback);
        }

        public int hashCode() {
            return (((this.text.hashCode() * 31) + this.options.hashCode()) * 31) + this.callback.hashCode();
        }

        public String toString() {
            return "SelectText(text=" + this.text + ", options=" + this.options + ", callback=" + this.callback + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public SelectText(UiText text, List<? extends UiText> options, Function1<? super Integer, Unit> callback) {
            super(null);
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.text = text;
            this.options = options;
            this.callback = callback;
        }

        public final UiText getText() {
            return this.text;
        }

        public final List<UiText> getOptions() {
            return this.options;
        }

        public final Function1<Integer, Unit> getCallback() {
            return this.callback;
        }
    }

    private SelectPopup() {
    }

    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u00060\u0005\u0012\u0014\u0010\b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u001b\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u00060\u0005HÆ\u0003J\u0017\u0010\u0015\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\n0\tHÆ\u0003JG\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u001a\b\u0002\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u00060\u00052\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR#\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001f\u0010\b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/SelectPopup$SelectArray;", "Lcom/lagradost/cloudstream3/ui/result/SelectPopup;", "text", "Lcom/lagradost/cloudstream3/utils/UiText;", "options", "", "Lkotlin/Pair;", "", "callback", "Lkotlin/Function1;", "", "<init>", "(Lcom/lagradost/cloudstream3/utils/UiText;Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "getText", "()Lcom/lagradost/cloudstream3/utils/UiText;", "getOptions", "()Ljava/util/List;", "getCallback", "()Lkotlin/jvm/functions/Function1;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SelectArray extends SelectPopup {
        private final Function1<Integer, Unit> callback;
        private final List<Pair<UiText, Integer>> options;
        private final UiText text;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SelectArray copy$default(SelectArray selectArray, UiText uiText, List list, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                uiText = selectArray.text;
            }
            if ((i & 2) != 0) {
                list = selectArray.options;
            }
            if ((i & 4) != 0) {
                function1 = selectArray.callback;
            }
            return selectArray.copy(uiText, list, function1);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final UiText getText() {
            return this.text;
        }

        public final List<Pair<UiText, Integer>> component2() {
            return this.options;
        }

        public final Function1<Integer, Unit> component3() {
            return this.callback;
        }

        public final SelectArray copy(UiText text, List<? extends Pair<? extends UiText, Integer>> options, Function1<? super Integer, Unit> callback) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(callback, "callback");
            return new SelectArray(text, options, callback);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SelectArray)) {
                return false;
            }
            SelectArray selectArray = (SelectArray) other;
            return Intrinsics.areEqual(this.text, selectArray.text) && Intrinsics.areEqual(this.options, selectArray.options) && Intrinsics.areEqual(this.callback, selectArray.callback);
        }

        public int hashCode() {
            return (((this.text.hashCode() * 31) + this.options.hashCode()) * 31) + this.callback.hashCode();
        }

        public String toString() {
            return "SelectArray(text=" + this.text + ", options=" + this.options + ", callback=" + this.callback + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public SelectArray(UiText text, List<? extends Pair<? extends UiText, Integer>> options, Function1<? super Integer, Unit> callback) {
            super(null);
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.text = text;
            this.options = options;
            this.callback = callback;
        }

        public final UiText getText() {
            return this.text;
        }

        public final List<Pair<UiText, Integer>> getOptions() {
            return this.options;
        }

        public final Function1<Integer, Unit> getCallback() {
            return this.callback;
        }
    }
}
