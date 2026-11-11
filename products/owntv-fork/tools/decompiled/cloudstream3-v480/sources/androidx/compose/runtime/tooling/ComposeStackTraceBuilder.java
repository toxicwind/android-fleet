package androidx.compose.runtime.tooling;

import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.GroupSourceInformation;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ComposeStackTraceBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b!\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bJ\u001a\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0002J\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0011\u001a\u00020\u0001H\u0002J\f\u0010\u0012\u001a\u00020\u0013*\u00020\fH\u0002J\u001a\u0010\u0014\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001J\u0018\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0001H\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0019\u001a\u00020\u001aH&J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u001aH&R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/compose/runtime/tooling/ComposeStackTraceBuilder;", "", "<init>", "()V", "_trace", "", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "trace", "", "appendTraceFrame", "", "groupSourceInformation", "Landroidx/compose/runtime/GroupSourceInformation;", "child", "extractTraceFrame", "targetChild", "sourceInformationOf", "group", "isCall", "", "processEdge", "sourceInformation", "childData", "findInGroupSourceInformation", TypedValues.AttributesType.S_TARGET, LinkHeader.Parameters.Anchor, "Landroidx/compose/runtime/Anchor;", "groupKeyOf", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class ComposeStackTraceBuilder {
    public static final int $stable = 8;
    private final List<ComposeStackTraceFrame> _trace = new ArrayList();

    public abstract int groupKeyOf(Anchor anchor);

    public abstract GroupSourceInformation sourceInformationOf(Anchor anchor);

    public final List<ComposeStackTraceFrame> trace() {
        return this._trace;
    }

    private final void appendTraceFrame(GroupSourceInformation groupSourceInformation, Object child) {
        ComposeStackTraceFrame composeStackTraceFrameExtractTraceFrame = extractTraceFrame(groupSourceInformation, child);
        if (composeStackTraceFrameExtractTraceFrame != null) {
            this._trace.add(composeStackTraceFrameExtractTraceFrame);
        }
    }

    /* JADX WARN: Code duplicated, block: B:41:0x0087  */
    private final ComposeStackTraceFrame extractTraceFrame(GroupSourceInformation groupSourceInformation, Object targetChild) {
        ArrayList<Object> groups;
        String sourceInformation = groupSourceInformation.getSourceInformation();
        SourceInformation sourceInformation2 = sourceInformation != null ? SourceInformationKt.parseSourceInformation(sourceInformation) : null;
        if (sourceInformation2 == null) {
            return null;
        }
        if (targetChild == null) {
            return new ComposeStackTraceFrame(sourceInformation2, null);
        }
        ArrayList<Object> groups2 = groupSourceInformation.getGroups();
        int i = 0;
        if (groups2 != null) {
            int size = groups2.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                Object obj = groups2.get(i3);
                if (Intrinsics.areEqual(obj, targetChild)) {
                    break;
                }
                GroupSourceInformation groupSourceInformationSourceInformationOf = sourceInformationOf(obj);
                if (groupSourceInformationSourceInformationOf != null && (groupSourceInformationSourceInformationOf.getKey() == -127 || (groupSourceInformationSourceInformationOf.getKey() == 0 && (obj instanceof Anchor) && groupKeyOf((Anchor) obj) == -127))) {
                    if ((groupSourceInformationSourceInformationOf != null ? groupSourceInformationSourceInformationOf.getSourceInformation() : null) == null) {
                        if (groupSourceInformationSourceInformationOf != null && (groups = groupSourceInformationSourceInformationOf.getGroups()) != null) {
                            ArrayList<Object> arrayList = groups;
                            int size2 = arrayList.size();
                            for (int i4 = 0; i4 < size2; i4++) {
                                GroupSourceInformation groupSourceInformationSourceInformationOf2 = sourceInformationOf(arrayList.get(i4));
                                if (groupSourceInformationSourceInformationOf2 != null && isCall(groupSourceInformationSourceInformationOf2)) {
                                    i2++;
                                }
                            }
                        }
                    } else if (groupSourceInformationSourceInformationOf == null) {
                    }
                } else if (groupSourceInformationSourceInformationOf == null && isCall(groupSourceInformationSourceInformationOf)) {
                    i2++;
                }
            }
            i = i2;
        }
        return new ComposeStackTraceFrame(sourceInformation2, Integer.valueOf(i));
    }

    private final GroupSourceInformation sourceInformationOf(Object group) {
        if (group instanceof Anchor) {
            return sourceInformationOf((Anchor) group);
        }
        if (group instanceof GroupSourceInformation) {
            return (GroupSourceInformation) group;
        }
        throw new IllegalStateException(("Unexpected child source info " + group).toString());
    }

    private final boolean isCall(GroupSourceInformation groupSourceInformation) {
        String sourceInformation = groupSourceInformation.getSourceInformation();
        return sourceInformation != null && StringsKt.startsWith$default(sourceInformation, "C", false, 2, (Object) null);
    }

    public final void processEdge(GroupSourceInformation sourceInformation, Object childData) {
        if (sourceInformation != null) {
            if (childData == null) {
                appendTraceFrame(sourceInformation, null);
            } else {
                if (findInGroupSourceInformation(sourceInformation, childData) || sourceInformation.getClosed()) {
                    return;
                }
                appendTraceFrame(sourceInformation, null);
            }
        }
    }

    private final boolean findInGroupSourceInformation(GroupSourceInformation sourceInformation, Object target) {
        ArrayList<Object> groups = sourceInformation.getGroups();
        boolean z = false;
        if (groups == null) {
            if (!sourceInformation.getClosed()) {
                appendTraceFrame(sourceInformation, null);
                return true;
            }
            int dataStartOffset = sourceInformation.getDataStartOffset();
            int dataEndOffset = sourceInformation.getDataEndOffset();
            boolean z2 = target instanceof Integer;
            if (z2) {
                Number number = (Number) target;
                int iIntValue = number.intValue();
                if ((dataStartOffset <= iIntValue && iIntValue < dataEndOffset) || (dataStartOffset == dataEndOffset && z2 && dataStartOffset == number.intValue())) {
                    z = true;
                }
                if (z) {
                    appendTraceFrame(sourceInformation, null);
                }
            }
            return z;
        }
        ArrayList<Object> arrayList = groups;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Object obj = arrayList.get(i);
            if (obj instanceof Anchor) {
                if (Intrinsics.areEqual(obj, target)) {
                    appendTraceFrame(sourceInformation, obj);
                    return true;
                }
            } else if (obj instanceof GroupSourceInformation) {
                if (findInGroupSourceInformation((GroupSourceInformation) obj, target)) {
                    appendTraceFrame(sourceInformation, obj);
                    return true;
                }
            } else {
                throw new IllegalStateException(("Unexpected child source info " + obj).toString());
            }
        }
        return false;
    }
}
