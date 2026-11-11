package androidx.compose.runtime.tooling;

import app.cash.zipline.ZiplineApiMismatchException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: ComposeStackTrace.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004H\u0000\u001a \u0010\u0007\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004H\u0000\u001a\u001e\u0010\b\u001a\u00020\t*\u00060\nj\u0002`\u000b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000\"\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"tryAttachComposeStackTrace", "", "", "trace", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "attachComposeStackTrace", "appendStackTrace", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "RuntimePackageHash", "", "IncludeDebugInfo", "runtime"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ComposeStackTraceKt {
    private static final boolean IncludeDebugInfo = false;
    private static final String RuntimePackageHash = "9igjgp";

    public static final boolean tryAttachComposeStackTrace(Throwable th, Function0<? extends List<ComposeStackTraceFrame>> function0) {
        DiagnosticComposeException diagnosticComposeException;
        List<Throwable> suppressedExceptions = ExceptionsKt.getSuppressedExceptions(th);
        boolean z = false;
        if (!(suppressedExceptions instanceof Collection) || !suppressedExceptions.isEmpty()) {
            Iterator<T> it = suppressedExceptions.iterator();
            while (it.hasNext()) {
                if (((Throwable) it.next()) instanceof DiagnosticComposeException) {
                    return false;
                }
            }
        }
        try {
            List<ComposeStackTraceFrame> listInvoke = function0.invoke();
            boolean zIsEmpty = listInvoke.isEmpty();
            z = !zIsEmpty;
            diagnosticComposeException = !zIsEmpty ? new DiagnosticComposeException(listInvoke) : null;
        } catch (Throwable th2) {
            diagnosticComposeException = th2;
        }
        if (diagnosticComposeException != null) {
            ExceptionsKt.addSuppressed(th, diagnosticComposeException);
        }
        return z;
    }

    public static final Throwable attachComposeStackTrace(Throwable th, Function0<? extends List<ComposeStackTraceFrame>> function0) {
        tryAttachComposeStackTrace(th, function0);
        return th;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0037 A[PHI: r9
      0x0037: PHI (r9v1 java.lang.String) = (r9v0 java.lang.String), (r9v14 java.lang.String) binds: [B:5:0x0024, B:10:0x0030] A[DONT_GENERATE, DONT_INLINE]] */
    public static final void appendStackTrace(StringBuilder sb, List<ComposeStackTraceFrame> list) {
        String strValueOf;
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        List listAsReversed = CollectionsKt.asReversed(list);
        int size = listAsReversed.size();
        String str = null;
        String str2 = null;
        for (int i = 0; i < size; i++) {
            ComposeStackTraceFrame composeStackTraceFrame = (ComposeStackTraceFrame) listAsReversed.get(i);
            SourceInformation sourceInfo = composeStackTraceFrame.getSourceInfo();
            String functionName = sourceInfo.getFunctionName();
            if (functionName != null) {
                str = functionName;
            } else {
                functionName = sourceInfo.getIsCall() ? "<lambda>" : null;
                if (functionName != null) {
                    str = functionName;
                } else if (str == null) {
                    str = ZiplineApiMismatchException.UNKNOWN_FUNCTION;
                }
            }
            String sourceFile = sourceInfo.getSourceFile();
            if (sourceFile != null) {
                str2 = sourceFile;
            } else if (str2 == null) {
                str2 = "<unknown file>";
            }
            List<LocationSourceInformation> locations = sourceInfo.getLocations();
            if (composeStackTraceFrame.getGroupOffset() != null && composeStackTraceFrame.getGroupOffset().intValue() < locations.size()) {
                strValueOf = String.valueOf(locations.get(composeStackTraceFrame.getGroupOffset().intValue()).getLineNumber());
            } else {
                strValueOf = "<unknown line>";
            }
            String str3 = str + '(' + str2 + AbstractJsonLexerKt.COLON + strValueOf + ')';
            Intrinsics.checkNotNullExpressionValue(str3, "toString(...)");
            if (!sourceInfo.getIsCall()) {
            }
            if (!Intrinsics.areEqual(sourceInfo.getFunctionName(), "rememberCompositionContext") || !Intrinsics.areEqual(sourceInfo.getPackageHash(), RuntimePackageHash)) {
                listCreateListBuilder.add(str3);
            }
        }
        List listAsReversed2 = CollectionsKt.asReversed(CollectionsKt.build(listCreateListBuilder));
        int size2 = listAsReversed2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            sb.append("\tat " + ((String) listAsReversed2.get(i2)));
            Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
            sb.append('\n');
            Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
        }
    }
}
