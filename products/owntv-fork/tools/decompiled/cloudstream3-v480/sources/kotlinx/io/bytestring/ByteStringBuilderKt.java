package kotlinx.io.bytestring;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByteStringBuilder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\u0080\u0004¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0086\u0080\u0004\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\u0010\t\u001a\u00020\n\"\u00020\u000bH\u0086\u0080\u0004\u001a0\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0011H\u0086\u0088\u0004ø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0012"}, d2 = {"append", "", "Lkotlinx/io/bytestring/ByteStringBuilder;", "byte", "Lkotlin/UByte;", "append-EK-6454", "(Lkotlinx/io/bytestring/ByteStringBuilder;B)V", "byteString", "Lkotlinx/io/bytestring/ByteString;", "bytes", "", "", "buildByteString", "capacity", "", "builderAction", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "kotlinx-io-bytestring"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ByteStringBuilderKt {
    /* JADX INFO: renamed from: append-EK-6454, reason: not valid java name */
    public static final void m10608appendEK6454(ByteStringBuilder append, byte b) {
        Intrinsics.checkNotNullParameter(append, "$this$append");
        append.append(b);
    }

    public static final void append(ByteStringBuilder byteStringBuilder, ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteStringBuilder, "<this>");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        ByteStringBuilder.append$default(byteStringBuilder, byteString.getData(), 0, 0, 6, null);
    }

    public static final void append(ByteStringBuilder byteStringBuilder, byte... bytes) {
        Intrinsics.checkNotNullParameter(byteStringBuilder, "<this>");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        ByteStringBuilder.append$default(byteStringBuilder, bytes, 0, 0, 6, null);
    }

    public static /* synthetic */ ByteString buildByteString$default(int i, Function1 builderAction, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        ByteStringBuilder byteStringBuilder = new ByteStringBuilder(i);
        builderAction.invoke(byteStringBuilder);
        return byteStringBuilder.toByteString();
    }

    public static final ByteString buildByteString(int i, Function1<? super ByteStringBuilder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        ByteStringBuilder byteStringBuilder = new ByteStringBuilder(i);
        builderAction.invoke(byteStringBuilder);
        return byteStringBuilder.toByteString();
    }
}
