package app.cash.zipline;

import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: QuickJsException.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u0000 \b2\u00060\u0001j\u0002`\u0002:\u0001\bB\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lapp/cash/zipline/QuickJsException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "detailMessage", "", "jsStackTrace", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "Companion", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class QuickJsException extends RuntimeException {
    private static final String STACK_TRACE_CLASS_NAME = "JavaScript";
    private static final Companion Companion = new Companion(null);
    private static final Pattern STACK_TRACE_PATTERN = Pattern.compile("\\s*at ([^\\s]+) \\(([^\\s]+(?<!cpp))[:(\\d+)]?\\).*$");

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public QuickJsException(String detailMessage) {
        this(detailMessage, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(detailMessage, "detailMessage");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QuickJsException(String detailMessage, String str) {
        super(detailMessage);
        Intrinsics.checkNotNullParameter(detailMessage, "detailMessage");
        if (str != null) {
            Companion.addJavaScriptStack(this, str);
        }
    }

    public /* synthetic */ QuickJsException(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2);
    }

    /* JADX INFO: compiled from: QuickJsException.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\bH\u0007J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0012\u001a\u00020\bH\u0002R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u0018\u0010\r\u001a\u00020\u000e*\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0010¨\u0006\u0013"}, d2 = {"Lapp/cash/zipline/QuickJsException$Companion;", "", "<init>", "()V", "STACK_TRACE_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "STACK_TRACE_CLASS_NAME", "", "addJavaScriptStack", "", "", "detailMessage", "isZipline", "", "Ljava/lang/StackTraceElement;", "(Ljava/lang/StackTraceElement;)Z", "toStackTraceElement", CmcdData.STREAMING_FORMAT_SS, "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final /* synthetic */ void addJavaScriptStack(Throwable th, String detailMessage) {
            List listEmptyList;
            Intrinsics.checkNotNullParameter(th, "<this>");
            Intrinsics.checkNotNullParameter(detailMessage, "detailMessage");
            List listSplit$default = StringsKt.split$default((CharSequence) detailMessage, new char[]{'\n'}, false, 0, 6, (Object) null);
            if (!listSplit$default.isEmpty()) {
                ListIterator listIterator = listSplit$default.listIterator(listSplit$default.size());
                while (true) {
                    if (listIterator.hasPrevious()) {
                        if (((String) listIterator.previous()).length() != 0) {
                            listEmptyList = CollectionsKt.take(listSplit$default, listIterator.nextIndex() + 1);
                            break;
                        }
                    } else {
                        listEmptyList = CollectionsKt.emptyList();
                        break;
                    }
                }
            } else {
                listEmptyList = CollectionsKt.emptyList();
                break;
            }
            if (listEmptyList.isEmpty()) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            StackTraceElement[] stackTrace = th.getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "getStackTrace(...)");
            boolean z = false;
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (!z && stackTraceElement.isNativeMethod()) {
                    Intrinsics.checkNotNull(stackTraceElement);
                    if (isZipline(stackTraceElement)) {
                        Iterator it = listEmptyList.iterator();
                        while (it.hasNext()) {
                            StackTraceElement stackTraceElement2 = toStackTraceElement((String) it.next());
                            if (stackTraceElement2 != null) {
                                arrayList.add(stackTraceElement2);
                            }
                        }
                        z = true;
                    }
                }
                arrayList.add(stackTraceElement);
            }
            th.setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]));
        }

        private final boolean isZipline(StackTraceElement stackTraceElement) {
            return Intrinsics.areEqual(stackTraceElement.getClassName(), QuickJs.class.getName()) || Intrinsics.areEqual(stackTraceElement.getClassName(), JniCallChannel.class.getName());
        }

        private final StackTraceElement toStackTraceElement(String s) {
            int i;
            Matcher matcher = QuickJsException.STACK_TRACE_PATTERN.matcher(s);
            if (!matcher.matches()) {
                return null;
            }
            String strGroup = matcher.group(1);
            String strGroup2 = matcher.group(2);
            if (matcher.groupCount() > 3) {
                String strGroup3 = matcher.group(3);
                Intrinsics.checkNotNull(strGroup3);
                i = Integer.parseInt(strGroup3);
            } else {
                i = -1;
            }
            return new StackTraceElement(QuickJsException.STACK_TRACE_CLASS_NAME, strGroup, strGroup2, i);
        }
    }
}
