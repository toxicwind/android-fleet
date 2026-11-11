package io.ktor.util;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: PlatformUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0004\u0005\u0006\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\t\n\u000b\f¨\u0006\r"}, d2 = {"Lio/ktor/util/Platform;", "", "<init>", "()V", "Jvm", "Native", "Js", "WasmJs", "JsPlatform", "Lio/ktor/util/Platform$Js;", "Lio/ktor/util/Platform$Jvm;", "Lio/ktor/util/Platform$Native;", "Lio/ktor/util/Platform$WasmJs;", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class Platform {

    /* JADX INFO: compiled from: PlatformUtils.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lio/ktor/util/Platform$JsPlatform;", "", "<init>", "(Ljava/lang/String;I)V", "Browser", "Node", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum JsPlatform {
        Browser,
        Node;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<JsPlatform> getEntries() {
            return $ENTRIES;
        }
    }

    public /* synthetic */ Platform(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: PlatformUtils.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÖ\u0083\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\n\u001a\u00020\tHÖ\u0081\u0004¢\u0006\u0004\b\n\u0010\u000bJ\u0011\u0010\r\u001a\u00020\fHÖ\u0081\u0004¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/util/Platform$Jvm;", "Lio/ktor/util/Platform;", "<init>", "()V", "", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Jvm extends Platform {
        public static final Jvm INSTANCE = new Jvm();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Jvm)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1051825272;
        }

        public String toString() {
            return "Jvm";
        }

        private Jvm() {
            super(null);
        }
    }

    private Platform() {
    }

    /* JADX INFO: compiled from: PlatformUtils.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÖ\u0083\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\n\u001a\u00020\tHÖ\u0081\u0004¢\u0006\u0004\b\n\u0010\u000bJ\u0011\u0010\r\u001a\u00020\fHÖ\u0081\u0004¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/util/Platform$Native;", "Lio/ktor/util/Platform;", "<init>", "()V", "", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Native extends Platform {
        public static final Native INSTANCE = new Native();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Native)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1059277600;
        }

        public String toString() {
            return "Native";
        }

        private Native() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: PlatformUtils.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\nHÖ\u0083\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u0011\u0010\u0010\u001a\u00020\u000fHÖ\u0081\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012HÖ\u0081\u0004¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0007¨\u0006\u0017"}, d2 = {"Lio/ktor/util/Platform$Js;", "Lio/ktor/util/Platform;", "Lio/ktor/util/Platform$JsPlatform;", "jsPlatform", "<init>", "(Lio/ktor/util/Platform$JsPlatform;)V", "component1", "()Lio/ktor/util/Platform$JsPlatform;", "copy", "(Lio/ktor/util/Platform$JsPlatform;)Lio/ktor/util/Platform$Js;", "", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lio/ktor/util/Platform$JsPlatform;", "getJsPlatform", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Js extends Platform {
        private final JsPlatform jsPlatform;

        public static /* synthetic */ Js copy$default(Js js, JsPlatform jsPlatform, int i, Object obj) {
            if ((i & 1) != 0) {
                jsPlatform = js.jsPlatform;
            }
            return js.copy(jsPlatform);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final JsPlatform getJsPlatform() {
            return this.jsPlatform;
        }

        public final Js copy(JsPlatform jsPlatform) {
            Intrinsics.checkNotNullParameter(jsPlatform, "jsPlatform");
            return new Js(jsPlatform);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Js) && this.jsPlatform == ((Js) other).jsPlatform;
        }

        public int hashCode() {
            return this.jsPlatform.hashCode();
        }

        public String toString() {
            return "Js(jsPlatform=" + this.jsPlatform + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Js(JsPlatform jsPlatform) {
            super(null);
            Intrinsics.checkNotNullParameter(jsPlatform, "jsPlatform");
            this.jsPlatform = jsPlatform;
        }

        public final JsPlatform getJsPlatform() {
            return this.jsPlatform;
        }
    }

    /* JADX INFO: compiled from: PlatformUtils.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\nHÖ\u0083\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u0011\u0010\u0010\u001a\u00020\u000fHÖ\u0081\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012HÖ\u0081\u0004¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0007¨\u0006\u0017"}, d2 = {"Lio/ktor/util/Platform$WasmJs;", "Lio/ktor/util/Platform;", "Lio/ktor/util/Platform$JsPlatform;", "jsPlatform", "<init>", "(Lio/ktor/util/Platform$JsPlatform;)V", "component1", "()Lio/ktor/util/Platform$JsPlatform;", "copy", "(Lio/ktor/util/Platform$JsPlatform;)Lio/ktor/util/Platform$WasmJs;", "", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lio/ktor/util/Platform$JsPlatform;", "getJsPlatform", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class WasmJs extends Platform {
        private final JsPlatform jsPlatform;

        public static /* synthetic */ WasmJs copy$default(WasmJs wasmJs, JsPlatform jsPlatform, int i, Object obj) {
            if ((i & 1) != 0) {
                jsPlatform = wasmJs.jsPlatform;
            }
            return wasmJs.copy(jsPlatform);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final JsPlatform getJsPlatform() {
            return this.jsPlatform;
        }

        public final WasmJs copy(JsPlatform jsPlatform) {
            Intrinsics.checkNotNullParameter(jsPlatform, "jsPlatform");
            return new WasmJs(jsPlatform);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof WasmJs) && this.jsPlatform == ((WasmJs) other).jsPlatform;
        }

        public int hashCode() {
            return this.jsPlatform.hashCode();
        }

        public String toString() {
            return "WasmJs(jsPlatform=" + this.jsPlatform + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WasmJs(JsPlatform jsPlatform) {
            super(null);
            Intrinsics.checkNotNullParameter(jsPlatform, "jsPlatform");
            this.jsPlatform = jsPlatform;
        }

        public final JsPlatform getJsPlatform() {
            return this.jsPlatform;
        }
    }
}
