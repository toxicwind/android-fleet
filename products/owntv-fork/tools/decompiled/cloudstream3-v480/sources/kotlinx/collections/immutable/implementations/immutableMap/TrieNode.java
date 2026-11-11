package kotlinx.collections.immutable.implementations.immutableMap;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlinx.collections.immutable.internal.CommonFunctionsKt;
import kotlinx.collections.immutable.internal.DeltaCounter;
import kotlinx.collections.immutable.internal.MutabilityOwnership;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: TrieNode.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 \u0085\u0001*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003:\u0004\u0084\u0001\u0085\u0001B1\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fB)\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b¢\u0006\u0004\b\u000b\u0010\rJ\u0014\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000fH\u0002J\u0014\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000fH\u0002J\r\u0010\u0015\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0016J\u0015\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u001aJ\u0010\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\u0015\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u001dJ\u0015\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u001fJ\u0015\u0010 \u001a\u00028\u00002\u0006\u0010!\u001a\u00020\u0005H\u0002¢\u0006\u0002\u0010\"J\u0015\u0010#\u001a\u00028\u00012\u0006\u0010!\u001a\u00020\u0005H\u0002¢\u0006\u0002\u0010\"J!\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u0005H\u0000¢\u0006\u0002\b%J1\u0010&\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010(J9\u0010)\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0006\u0010*\u001a\u00020\nH\u0002¢\u0006\u0002\u0010+J)\u0010,\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010-J=\u0010.\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00028\u00012\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002¢\u0006\u0002\u00101JD\u00102\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\nH\u0002J&\u00104\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J.\u00105\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\nH\u0002JO\u00106\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u00107\u001a\u00020\u00052\u0006\u00108\u001a\u00028\u00002\u0006\u00109\u001a\u00028\u00012\u0006\u0010:\u001a\u00020\u00052\b\u0010*\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0002\u0010;JI\u0010<\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u00107\u001a\u00020\u00052\u0006\u00108\u001a\u00028\u00002\u0006\u00109\u001a\u00028\u00012\u0006\u0010:\u001a\u00020\u0005H\u0002¢\u0006\u0002\u0010=JQ\u0010>\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u00107\u001a\u00020\u00052\u0006\u00108\u001a\u00028\u00002\u0006\u00109\u001a\u00028\u00012\u0006\u0010:\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\nH\u0002¢\u0006\u0002\u0010?J[\u0010@\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010A\u001a\u00020\u00052\u0006\u0010B\u001a\u00028\u00002\u0006\u0010C\u001a\u00028\u00012\u0006\u0010D\u001a\u00020\u00052\u0006\u0010E\u001a\u00028\u00002\u0006\u0010F\u001a\u00028\u00012\u0006\u0010:\u001a\u00020\u00052\b\u0010*\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0002\u0010GJ&\u0010H\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J:\u0010I\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002J\u001e\u0010J\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010K\u001a\u00020\u0005H\u0002J2\u0010L\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010K\u001a\u00020\u00052\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002J\u0012\u0010M\u001a\u00020\u00052\b\u0010'\u001a\u0004\u0018\u00010\u0003H\u0002J\u0015\u0010N\u001a\u00020\u00182\u0006\u0010'\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010OJ\u0017\u0010P\u001a\u0004\u0018\u00018\u00012\u0006\u0010'\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010QJ+\u0010R\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000f2\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010SJ=\u0010T\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002¢\u0006\u0002\u0010UJ#\u0010V\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010'\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010WJ7\u0010X\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010'\u001a\u00028\u00002\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002¢\u0006\u0002\u0010YJ+\u0010V\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010ZJ?\u0010X\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002¢\u0006\u0002\u0010UJ8\u0010[\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0012\u0010\\\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010]\u001a\u00020^2\u0006\u0010*\u001a\u00020\nH\u0002JT\u0010_\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0012\u0010\\\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010:\u001a\u00020\u00052\u0006\u0010]\u001a\u00020^2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002J\b\u0010`\u001a\u00020\u0005H\u0002J\u001c\u0010a\u001a\u00020\u00182\u0012\u0010\\\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000H\u0002J#\u0010b\u001a\u00020\u00182\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010:\u001a\u00020\u0005¢\u0006\u0002\u0010dJ%\u0010e\u001a\u0004\u0018\u00018\u00012\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010:\u001a\u00020\u0005¢\u0006\u0002\u0010fJJ\u0010g\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0012\u0010\\\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010:\u001a\u00020\u00052\u0006\u0010]\u001a\u00020^2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100J9\u0010h\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000f2\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0006\u0010:\u001a\u00020\u0005¢\u0006\u0002\u0010iJK\u0010j\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0006\u0010:\u001a\u00020\u00052\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100¢\u0006\u0002\u0010kJ1\u0010l\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010:\u001a\u00020\u0005¢\u0006\u0002\u0010mJP\u0010n\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0012\u0010o\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0014\u00103\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0002JE\u0010p\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010:\u001a\u00020\u00052\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100¢\u0006\u0002\u0010qJD\u0010r\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0014\u00103\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\nH\u0002J9\u0010l\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0006\u0010:\u001a\u00020\u0005¢\u0006\u0002\u0010sJM\u0010p\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0006\u0010:\u001a\u00020\u00052\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100¢\u0006\u0002\u0010kJG\u0010t\u001a\u00020\u0018\"\u0004\b\u0002\u0010u\"\u0004\b\u0003\u0010v2\u0012\u0010w\u001a\u000e\u0012\u0004\u0012\u0002Hu\u0012\u0004\u0012\u0002Hv0\u00002\u0018\u0010x\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002Hv\u0012\u0004\u0012\u00020\u00180yH\u0000¢\u0006\u0002\bzJ\u009a\u0001\u0010{\u001a\u00020|2\u0089\u0001\u0010}\u001a\u0084\u0001\u0012!\u0012\u001f\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000¢\u0006\u000e\b\u007f\u0012\n\b\u0080\u0001\u0012\u0005\b\b(\u0081\u0001\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b\u007f\u0012\t\b\u0080\u0001\u0012\u0004\b\b(:\u0012\u0015\u0012\u00130\u0005¢\u0006\u000e\b\u007f\u0012\n\b\u0080\u0001\u0012\u0005\b\b(\u0082\u0001\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b\u007f\u0012\t\b\u0080\u0001\u0012\u0004\b\b(\u0004\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b\u007f\u0012\t\b\u0080\u0001\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020|0~H\u0000¢\u0006\u0003\b\u0083\u0001J¥\u0001\u0010{\u001a\u00020|2\u0089\u0001\u0010}\u001a\u0084\u0001\u0012!\u0012\u001f\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000¢\u0006\u000e\b\u007f\u0012\n\b\u0080\u0001\u0012\u0005\b\b(\u0081\u0001\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b\u007f\u0012\t\b\u0080\u0001\u0012\u0004\b\b(:\u0012\u0015\u0012\u00130\u0005¢\u0006\u000e\b\u007f\u0012\n\b\u0080\u0001\u0012\u0005\b\b(\u0082\u0001\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b\u007f\u0012\t\b\u0080\u0001\u0012\u0004\b\b(\u0004\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b\u007f\u0012\t\b\u0080\u0001\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020|0~2\u0007\u0010\u0082\u0001\u001a\u00020\u00052\u0006\u0010:\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R0\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b2\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b@BX\u0080\u000e¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0086\u0001"}, d2 = {"Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", "dataMap", "", "nodeMap", "buffer", "", "ownedBy", "Lkotlinx/collections/immutable/internal/MutabilityOwnership;", "<init>", "(II[Ljava/lang/Object;Lkotlinx/collections/immutable/internal/MutabilityOwnership;)V", "(II[Ljava/lang/Object;)V", "asInsertResult", "Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode$ModificationResult;", "asUpdateResult", ES6Iterator.VALUE_PROPERTY, "getBuffer$kotlinx_collections_immutable", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "entryCount", "entryCount$kotlinx_collections_immutable", "hasEntryAt", "", "positionMask", "hasEntryAt$kotlinx_collections_immutable", "hasNodeAt", "entryKeyIndex", "entryKeyIndex$kotlinx_collections_immutable", "nodeIndex", "nodeIndex$kotlinx_collections_immutable", "keyAtIndex", "keyIndex", "(I)Ljava/lang/Object;", "valueAtKeyIndex", "nodeAtIndex", "nodeAtIndex$kotlinx_collections_immutable", "insertEntryAt", "key", "(ILjava/lang/Object;Ljava/lang/Object;)Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "mutableInsertEntryAt", "owner", "(ILjava/lang/Object;Ljava/lang/Object;Lkotlinx/collections/immutable/internal/MutabilityOwnership;)Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "updateValueAtIndex", "(ILjava/lang/Object;)Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "mutableUpdateValueAtIndex", "mutator", "Lkotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;", "(ILjava/lang/Object;Lkotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;)Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "updateNodeAtIndex", "newNode", "removeNodeAtIndex", "mutableRemoveNodeAtIndex", "bufferMoveEntryToNode", "newKeyHash", "newKey", "newValue", "shift", "(IIILjava/lang/Object;Ljava/lang/Object;ILkotlinx/collections/immutable/internal/MutabilityOwnership;)[Ljava/lang/Object;", "moveEntryToNode", "(IIILjava/lang/Object;Ljava/lang/Object;I)Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "mutableMoveEntryToNode", "(IIILjava/lang/Object;Ljava/lang/Object;ILkotlinx/collections/immutable/internal/MutabilityOwnership;)Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "makeNode", "keyHash1", "key1", "value1", "keyHash2", "key2", "value2", "(ILjava/lang/Object;Ljava/lang/Object;ILjava/lang/Object;Ljava/lang/Object;ILkotlinx/collections/immutable/internal/MutabilityOwnership;)Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "removeEntryAtIndex", "mutableRemoveEntryAtIndex", "collisionRemoveEntryAtIndex", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "mutableCollisionRemoveEntryAtIndex", "collisionKeyIndex", "collisionContainsKey", Signatures.MATH_TO_BOOLEAN, "collisionGet", "(Ljava/lang/Object;)Ljava/lang/Object;", "collisionPut", "(Ljava/lang/Object;Ljava/lang/Object;)Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode$ModificationResult;", "mutableCollisionPut", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;)Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "collisionRemove", "(Ljava/lang/Object;)Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "mutableCollisionRemove", "(Ljava/lang/Object;Lkotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;)Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "(Ljava/lang/Object;Ljava/lang/Object;)Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "mutableCollisionPutAll", "otherNode", "intersectionCounter", "Lkotlinx/collections/immutable/internal/DeltaCounter;", "mutablePutAllFromOtherNodeCell", "calculateSize", "elementsIdentityEquals", "containsKey", "keyHash", "(ILjava/lang/Object;I)Z", "get", "(ILjava/lang/Object;I)Ljava/lang/Object;", "mutablePutAll", "put", "(ILjava/lang/Object;Ljava/lang/Object;I)Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode$ModificationResult;", "mutablePut", "(ILjava/lang/Object;Ljava/lang/Object;ILkotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;)Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "remove", "(ILjava/lang/Object;I)Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "replaceNode", "targetNode", "mutableRemove", "(ILjava/lang/Object;ILkotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;)Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "mutableReplaceNode", "(ILjava/lang/Object;Ljava/lang/Object;I)Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "equalsWith", "K1", "V1", "that", "equalityComparator", "Lkotlin/Function2;", "equalsWith$kotlinx_collections_immutable", "accept", "", "visitor", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "node", "hash", "accept$kotlinx_collections_immutable", "ModificationResult", "Companion", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TrieNode<K, V> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final TrieNode EMPTY = new TrieNode(0, 0, new Object[0]);
    private Object[] buffer;
    private int dataMap;
    private int nodeMap;
    private final MutabilityOwnership ownedBy;

    public TrieNode(int i, int i2, Object[] buffer, MutabilityOwnership mutabilityOwnership) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.dataMap = i;
        this.nodeMap = i2;
        this.ownedBy = mutabilityOwnership;
        this.buffer = buffer;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TrieNode(int i, int i2, Object[] buffer) {
        this(i, i2, buffer, null);
        Intrinsics.checkNotNullParameter(buffer, "buffer");
    }

    /* JADX INFO: compiled from: TrieNode.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003B#\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJD\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00002*\u0010\u0011\u001a&\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00050\u0012H\u0086\bø\u0001\u0000R&\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0013"}, d2 = {"Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode$ModificationResult;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", "node", "Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "sizeDelta", "", "<init>", "(Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;I)V", "getNode", "()Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "setNode", "(Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;)V", "getSizeDelta", "()I", "replaceNode", "operation", "Lkotlin/Function1;", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ModificationResult<K, V> {
        private TrieNode<K, V> node;
        private final int sizeDelta;

        public ModificationResult(TrieNode<K, V> node, int i) {
            Intrinsics.checkNotNullParameter(node, "node");
            this.node = node;
            this.sizeDelta = i;
        }

        public final TrieNode<K, V> getNode() {
            return this.node;
        }

        public final int getSizeDelta() {
            return this.sizeDelta;
        }

        public final void setNode(TrieNode<K, V> trieNode) {
            Intrinsics.checkNotNullParameter(trieNode, "<set-?>");
            this.node = trieNode;
        }

        public final ModificationResult<K, V> replaceNode(Function1<? super TrieNode<K, V>, TrieNode<K, V>> operation) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            setNode(operation.invoke(getNode()));
            return this;
        }
    }

    private final ModificationResult<K, V> asInsertResult() {
        return new ModificationResult<>(this, 1);
    }

    private final ModificationResult<K, V> asUpdateResult() {
        return new ModificationResult<>(this, 0);
    }

    /* JADX INFO: renamed from: getBuffer$kotlinx_collections_immutable, reason: from getter */
    public final Object[] getBuffer() {
        return this.buffer;
    }

    public final int entryCount$kotlinx_collections_immutable() {
        return Integer.bitCount(this.dataMap);
    }

    public final boolean hasEntryAt$kotlinx_collections_immutable(int positionMask) {
        return (this.dataMap & positionMask) != 0;
    }

    private final boolean hasNodeAt(int positionMask) {
        return (this.nodeMap & positionMask) != 0;
    }

    public final int entryKeyIndex$kotlinx_collections_immutable(int positionMask) {
        return Integer.bitCount(this.dataMap & (positionMask - 1)) * 2;
    }

    public final int nodeIndex$kotlinx_collections_immutable(int positionMask) {
        return (this.buffer.length - 1) - Integer.bitCount(this.nodeMap & (positionMask - 1));
    }

    private final K keyAtIndex(int keyIndex) {
        return (K) this.buffer[keyIndex];
    }

    private final V valueAtKeyIndex(int keyIndex) {
        return (V) this.buffer[keyIndex + 1];
    }

    public final TrieNode<K, V> nodeAtIndex$kotlinx_collections_immutable(int nodeIndex) {
        Object obj = this.buffer[nodeIndex];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of kotlinx.collections.immutable.implementations.immutableMap.TrieNode, V of kotlinx.collections.immutable.implementations.immutableMap.TrieNode>");
        return (TrieNode) obj;
    }

    private final TrieNode<K, V> insertEntryAt(int positionMask, K key, V value) {
        return new TrieNode<>(positionMask | this.dataMap, this.nodeMap, TrieNodeKt.insertEntryAtIndex(this.buffer, entryKeyIndex$kotlinx_collections_immutable(positionMask), key, value));
    }

    private final TrieNode<K, V> mutableInsertEntryAt(int positionMask, K key, V value, MutabilityOwnership owner) {
        int iEntryKeyIndex$kotlinx_collections_immutable = entryKeyIndex$kotlinx_collections_immutable(positionMask);
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == owner) {
            this.buffer = TrieNodeKt.insertEntryAtIndex(objArr, iEntryKeyIndex$kotlinx_collections_immutable, key, value);
            this.dataMap = positionMask | this.dataMap;
            return this;
        }
        return new TrieNode<>(positionMask | this.dataMap, this.nodeMap, TrieNodeKt.insertEntryAtIndex(objArr, iEntryKeyIndex$kotlinx_collections_immutable, key, value), owner);
    }

    private final TrieNode<K, V> updateValueAtIndex(int keyIndex, V value) {
        Object[] objArr = this.buffer;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        objArrCopyOf[keyIndex + 1] = value;
        return new TrieNode<>(this.dataMap, this.nodeMap, objArrCopyOf);
    }

    private final TrieNode<K, V> mutableUpdateValueAtIndex(int keyIndex, V value, PersistentHashMapBuilder<K, V> mutator) {
        if (this.ownedBy == mutator.getOwnership()) {
            this.buffer[keyIndex + 1] = value;
            return this;
        }
        mutator.setModCount$kotlinx_collections_immutable(mutator.getModCount() + 1);
        Object[] objArr = this.buffer;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        objArrCopyOf[keyIndex + 1] = value;
        return new TrieNode<>(this.dataMap, this.nodeMap, objArrCopyOf, mutator.getOwnership());
    }

    static /* synthetic */ TrieNode updateNodeAtIndex$default(TrieNode trieNode, int i, int i2, TrieNode trieNode2, MutabilityOwnership mutabilityOwnership, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            mutabilityOwnership = null;
        }
        return trieNode.updateNodeAtIndex(i, i2, trieNode2, mutabilityOwnership);
    }

    private final TrieNode<K, V> updateNodeAtIndex(int nodeIndex, int positionMask, TrieNode<K, V> newNode, MutabilityOwnership owner) {
        Object[] objArr = newNode.buffer;
        if (objArr.length == 2 && newNode.nodeMap == 0) {
            if (this.buffer.length == 1) {
                newNode.dataMap = this.nodeMap;
                return newNode;
            }
            return new TrieNode<>(this.dataMap ^ positionMask, this.nodeMap ^ positionMask, TrieNodeKt.replaceNodeWithEntry(this.buffer, nodeIndex, entryKeyIndex$kotlinx_collections_immutable(positionMask), objArr[0], objArr[1]), owner);
        }
        if (owner != null && this.ownedBy == owner) {
            this.buffer[nodeIndex] = newNode;
            return this;
        }
        Object[] objArr2 = this.buffer;
        Object[] objArrCopyOf = Arrays.copyOf(objArr2, objArr2.length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        objArrCopyOf[nodeIndex] = newNode;
        return new TrieNode<>(this.dataMap, this.nodeMap, objArrCopyOf, owner);
    }

    private final TrieNode<K, V> removeNodeAtIndex(int nodeIndex, int positionMask) {
        Object[] objArr = this.buffer;
        if (objArr.length == 1) {
            return null;
        }
        return new TrieNode<>(this.dataMap, this.nodeMap ^ positionMask, TrieNodeKt.removeNodeAtIndex(objArr, nodeIndex));
    }

    private final TrieNode<K, V> mutableRemoveNodeAtIndex(int nodeIndex, int positionMask, MutabilityOwnership owner) {
        Object[] objArr = this.buffer;
        if (objArr.length == 1) {
            return null;
        }
        if (this.ownedBy == owner) {
            this.buffer = TrieNodeKt.removeNodeAtIndex(objArr, nodeIndex);
            this.nodeMap ^= positionMask;
            return this;
        }
        return new TrieNode<>(this.dataMap, this.nodeMap ^ positionMask, TrieNodeKt.removeNodeAtIndex(objArr, nodeIndex), owner);
    }

    private final Object[] bufferMoveEntryToNode(int keyIndex, int positionMask, int newKeyHash, K newKey, V newValue, int shift, MutabilityOwnership owner) {
        K kKeyAtIndex = keyAtIndex(keyIndex);
        return TrieNodeKt.replaceEntryWithNode(this.buffer, keyIndex, nodeIndex$kotlinx_collections_immutable(positionMask) + 1, makeNode(kKeyAtIndex != null ? kKeyAtIndex.hashCode() : 0, kKeyAtIndex, valueAtKeyIndex(keyIndex), newKeyHash, newKey, newValue, shift + 5, owner));
    }

    private final TrieNode<K, V> moveEntryToNode(int keyIndex, int positionMask, int newKeyHash, K newKey, V newValue, int shift) {
        return new TrieNode<>(this.dataMap ^ positionMask, this.nodeMap | positionMask, bufferMoveEntryToNode(keyIndex, positionMask, newKeyHash, newKey, newValue, shift, null));
    }

    private final TrieNode<K, V> mutableMoveEntryToNode(int keyIndex, int positionMask, int newKeyHash, K newKey, V newValue, int shift, MutabilityOwnership owner) {
        if (this.ownedBy == owner) {
            this.buffer = bufferMoveEntryToNode(keyIndex, positionMask, newKeyHash, newKey, newValue, shift, owner);
            this.dataMap ^= positionMask;
            this.nodeMap |= positionMask;
            return this;
        }
        return new TrieNode<>(this.dataMap ^ positionMask, this.nodeMap | positionMask, bufferMoveEntryToNode(keyIndex, positionMask, newKeyHash, newKey, newValue, shift, owner), owner);
    }

    private final TrieNode<K, V> makeNode(int keyHash1, K key1, V value1, int keyHash2, K key2, V value2, int shift, MutabilityOwnership owner) {
        Object[] objArr;
        if (shift > 30) {
            return new TrieNode<>(0, 0, new Object[]{key1, value1, key2, value2}, owner);
        }
        int iIndexSegment = TrieNodeKt.indexSegment(keyHash1, shift);
        int iIndexSegment2 = TrieNodeKt.indexSegment(keyHash2, shift);
        if (iIndexSegment != iIndexSegment2) {
            if (iIndexSegment < iIndexSegment2) {
                objArr = new Object[]{key1, value1, key2, value2};
            } else {
                objArr = new Object[]{key2, value2, key1, value1};
            }
            return new TrieNode<>((1 << iIndexSegment) | (1 << iIndexSegment2), 0, objArr, owner);
        }
        return new TrieNode<>(0, 1 << iIndexSegment, new Object[]{makeNode(keyHash1, key1, value1, keyHash2, key2, value2, shift + 5, owner)}, owner);
    }

    private final TrieNode<K, V> removeEntryAtIndex(int keyIndex, int positionMask) {
        Object[] objArr = this.buffer;
        if (objArr.length == 2) {
            return null;
        }
        return new TrieNode<>(positionMask ^ this.dataMap, this.nodeMap, TrieNodeKt.removeEntryAtIndex(objArr, keyIndex));
    }

    private final TrieNode<K, V> mutableRemoveEntryAtIndex(int keyIndex, int positionMask, PersistentHashMapBuilder<K, V> mutator) {
        mutator.setSize(mutator.size() - 1);
        mutator.setOperationResult$kotlinx_collections_immutable(valueAtKeyIndex(keyIndex));
        if (this.buffer.length == 2) {
            return null;
        }
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        MutabilityOwnership ownership$kotlinx_collections_immutable = mutator.getOwnership();
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == ownership$kotlinx_collections_immutable) {
            this.buffer = TrieNodeKt.removeEntryAtIndex(objArr, keyIndex);
            this.dataMap ^= positionMask;
            return this;
        }
        return new TrieNode<>(positionMask ^ this.dataMap, this.nodeMap, TrieNodeKt.removeEntryAtIndex(objArr, keyIndex), mutator.getOwnership());
    }

    private final TrieNode<K, V> collisionRemoveEntryAtIndex(int i) {
        Object[] objArr = this.buffer;
        if (objArr.length == 2) {
            return null;
        }
        return new TrieNode<>(0, 0, TrieNodeKt.removeEntryAtIndex(objArr, i));
    }

    private final TrieNode<K, V> mutableCollisionRemoveEntryAtIndex(int i, PersistentHashMapBuilder<K, V> mutator) {
        mutator.setSize(mutator.size() - 1);
        mutator.setOperationResult$kotlinx_collections_immutable(valueAtKeyIndex(i));
        if (this.buffer.length == 2) {
            return null;
        }
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        MutabilityOwnership ownership$kotlinx_collections_immutable = mutator.getOwnership();
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == ownership$kotlinx_collections_immutable) {
            this.buffer = TrieNodeKt.removeEntryAtIndex(objArr, i);
            return this;
        }
        return new TrieNode<>(0, 0, TrieNodeKt.removeEntryAtIndex(objArr, i), mutator.getOwnership());
    }

    private final int collisionKeyIndex(Object key) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step <= 0 || first > last) && (step >= 0 || last > first)) {
            return -1;
        }
        while (!Intrinsics.areEqual(key, keyAtIndex(first))) {
            if (first == last) {
                return -1;
            }
            first += step;
        }
        return first;
    }

    private final boolean collisionContainsKey(K key) {
        return collisionKeyIndex(key) != -1;
    }

    private final V collisionGet(K key) {
        int iCollisionKeyIndex = collisionKeyIndex(key);
        if (iCollisionKeyIndex != -1) {
            return valueAtKeyIndex(iCollisionKeyIndex);
        }
        return null;
    }

    private final ModificationResult<K, V> collisionPut(K key, V value) {
        int iCollisionKeyIndex = collisionKeyIndex(key);
        if (iCollisionKeyIndex != -1) {
            if (value == valueAtKeyIndex(iCollisionKeyIndex)) {
                return null;
            }
            Object[] objArr = this.buffer;
            Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
            objArrCopyOf[iCollisionKeyIndex + 1] = value;
            return new TrieNode(0, 0, objArrCopyOf).asUpdateResult();
        }
        return new TrieNode(0, 0, TrieNodeKt.insertEntryAtIndex(this.buffer, 0, key, value)).asInsertResult();
    }

    private final TrieNode<K, V> mutableCollisionPut(K key, V value, PersistentHashMapBuilder<K, V> mutator) {
        int iCollisionKeyIndex = collisionKeyIndex(key);
        if (iCollisionKeyIndex != -1) {
            mutator.setOperationResult$kotlinx_collections_immutable(valueAtKeyIndex(iCollisionKeyIndex));
            if (this.ownedBy == mutator.getOwnership()) {
                this.buffer[iCollisionKeyIndex + 1] = value;
                return this;
            }
            mutator.setModCount$kotlinx_collections_immutable(mutator.getModCount() + 1);
            Object[] objArr = this.buffer;
            Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
            objArrCopyOf[iCollisionKeyIndex + 1] = value;
            return new TrieNode<>(0, 0, objArrCopyOf, mutator.getOwnership());
        }
        mutator.setSize(mutator.size() + 1);
        return new TrieNode<>(0, 0, TrieNodeKt.insertEntryAtIndex(this.buffer, 0, key, value), mutator.getOwnership());
    }

    private final TrieNode<K, V> collisionRemove(K key) {
        int iCollisionKeyIndex = collisionKeyIndex(key);
        return iCollisionKeyIndex != -1 ? collisionRemoveEntryAtIndex(iCollisionKeyIndex) : this;
    }

    private final TrieNode<K, V> mutableCollisionRemove(K key, PersistentHashMapBuilder<K, V> mutator) {
        int iCollisionKeyIndex = collisionKeyIndex(key);
        return iCollisionKeyIndex != -1 ? mutableCollisionRemoveEntryAtIndex(iCollisionKeyIndex, mutator) : this;
    }

    private final TrieNode<K, V> collisionRemove(K key, V value) {
        int iCollisionKeyIndex = collisionKeyIndex(key);
        return (iCollisionKeyIndex == -1 || !Intrinsics.areEqual(value, valueAtKeyIndex(iCollisionKeyIndex))) ? this : collisionRemoveEntryAtIndex(iCollisionKeyIndex);
    }

    private final TrieNode<K, V> mutableCollisionRemove(K key, V value, PersistentHashMapBuilder<K, V> mutator) {
        int iCollisionKeyIndex = collisionKeyIndex(key);
        return (iCollisionKeyIndex == -1 || !Intrinsics.areEqual(value, valueAtKeyIndex(iCollisionKeyIndex))) ? this : mutableCollisionRemoveEntryAtIndex(iCollisionKeyIndex, mutator);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final TrieNode<K, V> mutableCollisionPutAll(TrieNode<K, V> otherNode, DeltaCounter intersectionCounter, MutabilityOwnership owner) {
        CommonFunctionsKt.m10467assert(this.nodeMap == 0);
        CommonFunctionsKt.m10467assert(this.dataMap == 0);
        CommonFunctionsKt.m10467assert(otherNode.nodeMap == 0);
        CommonFunctionsKt.m10467assert(otherNode.dataMap == 0);
        Object[] objArr = this.buffer;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length + otherNode.buffer.length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        int length = this.buffer.length;
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, otherNode.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (true) {
                if (!collisionContainsKey(otherNode.buffer[first])) {
                    Object[] objArr2 = otherNode.buffer;
                    objArrCopyOf[length] = objArr2[first];
                    objArrCopyOf[length + 1] = objArr2[first + 1];
                    length += 2;
                } else {
                    intersectionCounter.setCount(intersectionCounter.getCount() + 1);
                }
                if (first == last) {
                    break;
                }
                first += step;
            }
        }
        if (length == this.buffer.length) {
            return this;
        }
        if (length == otherNode.buffer.length) {
            return otherNode;
        }
        if (length == objArrCopyOf.length) {
            return new TrieNode<>(0, 0, objArrCopyOf, owner);
        }
        Object[] objArrCopyOf2 = Arrays.copyOf(objArrCopyOf, length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf2, "copyOf(...)");
        return new TrieNode<>(0, 0, objArrCopyOf2, owner);
    }

    private final TrieNode<K, V> mutablePutAllFromOtherNodeCell(TrieNode<K, V> otherNode, int positionMask, int shift, DeltaCounter intersectionCounter, PersistentHashMapBuilder<K, V> mutator) {
        if (hasNodeAt(positionMask)) {
            TrieNode<K, V> trieNodeNodeAtIndex$kotlinx_collections_immutable = nodeAtIndex$kotlinx_collections_immutable(nodeIndex$kotlinx_collections_immutable(positionMask));
            if (otherNode.hasNodeAt(positionMask)) {
                return trieNodeNodeAtIndex$kotlinx_collections_immutable.mutablePutAll(otherNode.nodeAtIndex$kotlinx_collections_immutable(otherNode.nodeIndex$kotlinx_collections_immutable(positionMask)), shift + 5, intersectionCounter, mutator);
            }
            if (!otherNode.hasEntryAt$kotlinx_collections_immutable(positionMask)) {
                return trieNodeNodeAtIndex$kotlinx_collections_immutable;
            }
            int iEntryKeyIndex$kotlinx_collections_immutable = otherNode.entryKeyIndex$kotlinx_collections_immutable(positionMask);
            K kKeyAtIndex = otherNode.keyAtIndex(iEntryKeyIndex$kotlinx_collections_immutable);
            V vValueAtKeyIndex = otherNode.valueAtKeyIndex(iEntryKeyIndex$kotlinx_collections_immutable);
            int size = mutator.size();
            TrieNode<K, V> trieNodeMutablePut = trieNodeNodeAtIndex$kotlinx_collections_immutable.mutablePut(kKeyAtIndex != null ? kKeyAtIndex.hashCode() : 0, kKeyAtIndex, vValueAtKeyIndex, shift + 5, mutator);
            if (mutator.size() == size) {
                intersectionCounter.setCount(intersectionCounter.getCount() + 1);
            }
            return trieNodeMutablePut;
        }
        if (otherNode.hasNodeAt(positionMask)) {
            TrieNode<K, V> trieNodeNodeAtIndex$kotlinx_collections_immutable2 = otherNode.nodeAtIndex$kotlinx_collections_immutable(otherNode.nodeIndex$kotlinx_collections_immutable(positionMask));
            if (!hasEntryAt$kotlinx_collections_immutable(positionMask)) {
                return trieNodeNodeAtIndex$kotlinx_collections_immutable2;
            }
            int iEntryKeyIndex$kotlinx_collections_immutable2 = entryKeyIndex$kotlinx_collections_immutable(positionMask);
            K kKeyAtIndex2 = keyAtIndex(iEntryKeyIndex$kotlinx_collections_immutable2);
            int i = shift + 5;
            if (!trieNodeNodeAtIndex$kotlinx_collections_immutable2.containsKey(kKeyAtIndex2 != null ? kKeyAtIndex2.hashCode() : 0, kKeyAtIndex2, i)) {
                return trieNodeNodeAtIndex$kotlinx_collections_immutable2.mutablePut(kKeyAtIndex2 != null ? kKeyAtIndex2.hashCode() : 0, kKeyAtIndex2, valueAtKeyIndex(iEntryKeyIndex$kotlinx_collections_immutable2), i, mutator);
            }
            intersectionCounter.setCount(intersectionCounter.getCount() + 1);
            return trieNodeNodeAtIndex$kotlinx_collections_immutable2;
        }
        int iEntryKeyIndex$kotlinx_collections_immutable3 = entryKeyIndex$kotlinx_collections_immutable(positionMask);
        K kKeyAtIndex3 = keyAtIndex(iEntryKeyIndex$kotlinx_collections_immutable3);
        V vValueAtKeyIndex2 = valueAtKeyIndex(iEntryKeyIndex$kotlinx_collections_immutable3);
        int iEntryKeyIndex$kotlinx_collections_immutable4 = otherNode.entryKeyIndex$kotlinx_collections_immutable(positionMask);
        K kKeyAtIndex4 = otherNode.keyAtIndex(iEntryKeyIndex$kotlinx_collections_immutable4);
        return makeNode(kKeyAtIndex3 != null ? kKeyAtIndex3.hashCode() : 0, kKeyAtIndex3, vValueAtKeyIndex2, kKeyAtIndex4 != null ? kKeyAtIndex4.hashCode() : 0, kKeyAtIndex4, otherNode.valueAtKeyIndex(iEntryKeyIndex$kotlinx_collections_immutable4), shift + 5, mutator.getOwnership());
    }

    private final int calculateSize() {
        if (this.nodeMap == 0) {
            return this.buffer.length / 2;
        }
        int iBitCount = Integer.bitCount(this.dataMap);
        int length = this.buffer.length;
        for (int i = iBitCount * 2; i < length; i++) {
            iBitCount += nodeAtIndex$kotlinx_collections_immutable(i).calculateSize();
        }
        return iBitCount;
    }

    private final boolean elementsIdentityEquals(TrieNode<K, V> otherNode) {
        if (this == otherNode) {
            return true;
        }
        if (this.nodeMap != otherNode.nodeMap || this.dataMap != otherNode.dataMap) {
            return false;
        }
        int length = this.buffer.length;
        for (int i = 0; i < length; i++) {
            if (this.buffer[i] != otherNode.buffer[i]) {
                return false;
            }
        }
        return true;
    }

    public final boolean containsKey(int keyHash, K key, int shift) {
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$kotlinx_collections_immutable(iIndexSegment)) {
            return Intrinsics.areEqual(key, keyAtIndex(entryKeyIndex$kotlinx_collections_immutable(iIndexSegment)));
        }
        if (!hasNodeAt(iIndexSegment)) {
            return false;
        }
        TrieNode<K, V> trieNodeNodeAtIndex$kotlinx_collections_immutable = nodeAtIndex$kotlinx_collections_immutable(nodeIndex$kotlinx_collections_immutable(iIndexSegment));
        if (shift == 30) {
            return trieNodeNodeAtIndex$kotlinx_collections_immutable.collisionContainsKey(key);
        }
        return trieNodeNodeAtIndex$kotlinx_collections_immutable.containsKey(keyHash, key, shift + 5);
    }

    public final V get(int keyHash, K key, int shift) {
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$kotlinx_collections_immutable(iIndexSegment)) {
            int iEntryKeyIndex$kotlinx_collections_immutable = entryKeyIndex$kotlinx_collections_immutable(iIndexSegment);
            if (Intrinsics.areEqual(key, keyAtIndex(iEntryKeyIndex$kotlinx_collections_immutable))) {
                return valueAtKeyIndex(iEntryKeyIndex$kotlinx_collections_immutable);
            }
            return null;
        }
        if (!hasNodeAt(iIndexSegment)) {
            return null;
        }
        TrieNode<K, V> trieNodeNodeAtIndex$kotlinx_collections_immutable = nodeAtIndex$kotlinx_collections_immutable(nodeIndex$kotlinx_collections_immutable(iIndexSegment));
        if (shift == 30) {
            return trieNodeNodeAtIndex$kotlinx_collections_immutable.collisionGet(key);
        }
        return trieNodeNodeAtIndex$kotlinx_collections_immutable.get(keyHash, key, shift + 5);
    }

    public final TrieNode<K, V> mutablePutAll(TrieNode<K, V> otherNode, int shift, DeltaCounter intersectionCounter, PersistentHashMapBuilder<K, V> mutator) {
        Intrinsics.checkNotNullParameter(otherNode, "otherNode");
        Intrinsics.checkNotNullParameter(intersectionCounter, "intersectionCounter");
        PersistentHashMapBuilder<K, V> mutator2 = mutator;
        Intrinsics.checkNotNullParameter(mutator2, "mutator");
        if (this == otherNode) {
            intersectionCounter.plusAssign(calculateSize());
            return this;
        }
        int i = shift;
        if (i > 30) {
            return mutableCollisionPutAll(otherNode, intersectionCounter, mutator2.getOwnership());
        }
        int i2 = this.nodeMap | otherNode.nodeMap;
        int i3 = this.dataMap;
        int i4 = otherNode.dataMap;
        int i5 = (i3 ^ i4) & (~i2);
        int i6 = i3 & i4;
        while (i6 != 0) {
            int iLowestOneBit = Integer.lowestOneBit(i6);
            if (Intrinsics.areEqual(keyAtIndex(entryKeyIndex$kotlinx_collections_immutable(iLowestOneBit)), otherNode.keyAtIndex(otherNode.entryKeyIndex$kotlinx_collections_immutable(iLowestOneBit)))) {
                i5 |= iLowestOneBit;
            } else {
                i2 |= iLowestOneBit;
            }
            i6 ^= iLowestOneBit;
        }
        if ((i2 & i5) != 0) {
            throw new IllegalStateException("Check failed.");
        }
        TrieNode<K, V> trieNode = (Intrinsics.areEqual(this.ownedBy, mutator2.getOwnership()) && this.dataMap == i5 && this.nodeMap == i2) ? this : new TrieNode<>(i5, i2, new Object[(Integer.bitCount(i5) * 2) + Integer.bitCount(i2)]);
        int i7 = 0;
        int i8 = i2;
        int i9 = 0;
        while (i8 != 0) {
            int iLowestOneBit2 = Integer.lowestOneBit(i8);
            Object[] objArr = trieNode.buffer;
            objArr[(objArr.length - 1) - i9] = mutablePutAllFromOtherNodeCell(otherNode, iLowestOneBit2, i, intersectionCounter, mutator2);
            i9++;
            i8 ^= iLowestOneBit2;
            i = shift;
            mutator2 = mutator;
        }
        while (i5 != 0) {
            int iLowestOneBit3 = Integer.lowestOneBit(i5);
            int i10 = i7 * 2;
            if (!otherNode.hasEntryAt$kotlinx_collections_immutable(iLowestOneBit3)) {
                int iEntryKeyIndex$kotlinx_collections_immutable = entryKeyIndex$kotlinx_collections_immutable(iLowestOneBit3);
                trieNode.buffer[i10] = keyAtIndex(iEntryKeyIndex$kotlinx_collections_immutable);
                trieNode.buffer[i10 + 1] = valueAtKeyIndex(iEntryKeyIndex$kotlinx_collections_immutable);
            } else {
                int iEntryKeyIndex$kotlinx_collections_immutable2 = otherNode.entryKeyIndex$kotlinx_collections_immutable(iLowestOneBit3);
                trieNode.buffer[i10] = otherNode.keyAtIndex(iEntryKeyIndex$kotlinx_collections_immutable2);
                trieNode.buffer[i10 + 1] = otherNode.valueAtKeyIndex(iEntryKeyIndex$kotlinx_collections_immutable2);
                if (hasEntryAt$kotlinx_collections_immutable(iLowestOneBit3)) {
                    intersectionCounter.setCount(intersectionCounter.getCount() + 1);
                }
            }
            i7++;
            i5 ^= iLowestOneBit3;
        }
        if (elementsIdentityEquals(trieNode)) {
            return this;
        }
        return otherNode.elementsIdentityEquals(trieNode) ? otherNode : trieNode;
    }

    public final ModificationResult<K, V> put(int keyHash, K key, V value, int shift) {
        ModificationResult<K, V> modificationResultPut;
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$kotlinx_collections_immutable(iIndexSegment)) {
            int iEntryKeyIndex$kotlinx_collections_immutable = entryKeyIndex$kotlinx_collections_immutable(iIndexSegment);
            if (Intrinsics.areEqual(key, keyAtIndex(iEntryKeyIndex$kotlinx_collections_immutable))) {
                if (valueAtKeyIndex(iEntryKeyIndex$kotlinx_collections_immutable) == value) {
                    return null;
                }
                return updateValueAtIndex(iEntryKeyIndex$kotlinx_collections_immutable, value).asUpdateResult();
            }
            return moveEntryToNode(iEntryKeyIndex$kotlinx_collections_immutable, iIndexSegment, keyHash, key, value, shift).asInsertResult();
        }
        if (hasNodeAt(iIndexSegment)) {
            int iNodeIndex$kotlinx_collections_immutable = nodeIndex$kotlinx_collections_immutable(iIndexSegment);
            TrieNode<K, V> trieNodeNodeAtIndex$kotlinx_collections_immutable = nodeAtIndex$kotlinx_collections_immutable(iNodeIndex$kotlinx_collections_immutable);
            if (shift == 30) {
                modificationResultPut = trieNodeNodeAtIndex$kotlinx_collections_immutable.collisionPut(key, value);
                if (modificationResultPut == null) {
                    return null;
                }
            } else {
                modificationResultPut = trieNodeNodeAtIndex$kotlinx_collections_immutable.put(keyHash, key, value, shift + 5);
                if (modificationResultPut == null) {
                    return null;
                }
            }
            modificationResultPut.setNode(updateNodeAtIndex$default(this, iNodeIndex$kotlinx_collections_immutable, iIndexSegment, modificationResultPut.getNode(), null, 8, null));
            return modificationResultPut;
        }
        return insertEntryAt(iIndexSegment, key, value).asInsertResult();
    }

    public final TrieNode<K, V> mutablePut(int keyHash, K key, V value, int shift, PersistentHashMapBuilder<K, V> mutator) {
        TrieNode<K, V> trieNodeMutablePut;
        Intrinsics.checkNotNullParameter(mutator, "mutator");
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$kotlinx_collections_immutable(iIndexSegment)) {
            int iEntryKeyIndex$kotlinx_collections_immutable = entryKeyIndex$kotlinx_collections_immutable(iIndexSegment);
            if (Intrinsics.areEqual(key, keyAtIndex(iEntryKeyIndex$kotlinx_collections_immutable))) {
                mutator.setOperationResult$kotlinx_collections_immutable(valueAtKeyIndex(iEntryKeyIndex$kotlinx_collections_immutable));
                return valueAtKeyIndex(iEntryKeyIndex$kotlinx_collections_immutable) == value ? this : mutableUpdateValueAtIndex(iEntryKeyIndex$kotlinx_collections_immutable, value, mutator);
            }
            mutator.setSize(mutator.size() + 1);
            return mutableMoveEntryToNode(iEntryKeyIndex$kotlinx_collections_immutable, iIndexSegment, keyHash, key, value, shift, mutator.getOwnership());
        }
        if (hasNodeAt(iIndexSegment)) {
            int iNodeIndex$kotlinx_collections_immutable = nodeIndex$kotlinx_collections_immutable(iIndexSegment);
            TrieNode<K, V> trieNodeNodeAtIndex$kotlinx_collections_immutable = nodeAtIndex$kotlinx_collections_immutable(iNodeIndex$kotlinx_collections_immutable);
            if (shift == 30) {
                trieNodeMutablePut = trieNodeNodeAtIndex$kotlinx_collections_immutable.mutableCollisionPut(key, value, mutator);
            } else {
                trieNodeMutablePut = trieNodeNodeAtIndex$kotlinx_collections_immutable.mutablePut(keyHash, key, value, shift + 5, mutator);
            }
            return trieNodeNodeAtIndex$kotlinx_collections_immutable == trieNodeMutablePut ? this : updateNodeAtIndex(iNodeIndex$kotlinx_collections_immutable, iIndexSegment, trieNodeMutablePut, mutator.getOwnership());
        }
        mutator.setSize(mutator.size() + 1);
        return mutableInsertEntryAt(iIndexSegment, key, value, mutator.getOwnership());
    }

    public final TrieNode<K, V> remove(int keyHash, K key, int shift) {
        TrieNode<K, V> trieNodeRemove;
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$kotlinx_collections_immutable(iIndexSegment)) {
            int iEntryKeyIndex$kotlinx_collections_immutable = entryKeyIndex$kotlinx_collections_immutable(iIndexSegment);
            return Intrinsics.areEqual(key, keyAtIndex(iEntryKeyIndex$kotlinx_collections_immutable)) ? removeEntryAtIndex(iEntryKeyIndex$kotlinx_collections_immutable, iIndexSegment) : this;
        }
        if (!hasNodeAt(iIndexSegment)) {
            return this;
        }
        int iNodeIndex$kotlinx_collections_immutable = nodeIndex$kotlinx_collections_immutable(iIndexSegment);
        TrieNode<K, V> trieNodeNodeAtIndex$kotlinx_collections_immutable = nodeAtIndex$kotlinx_collections_immutable(iNodeIndex$kotlinx_collections_immutable);
        if (shift == 30) {
            trieNodeRemove = trieNodeNodeAtIndex$kotlinx_collections_immutable.collisionRemove(key);
        } else {
            trieNodeRemove = trieNodeNodeAtIndex$kotlinx_collections_immutable.remove(keyHash, key, shift + 5);
        }
        return replaceNode(trieNodeNodeAtIndex$kotlinx_collections_immutable, trieNodeRemove, iNodeIndex$kotlinx_collections_immutable, iIndexSegment);
    }

    private final TrieNode<K, V> replaceNode(TrieNode<K, V> targetNode, TrieNode<K, V> newNode, int nodeIndex, int positionMask) {
        if (newNode == null) {
            return removeNodeAtIndex(nodeIndex, positionMask);
        }
        return targetNode != newNode ? updateNodeAtIndex$default(this, nodeIndex, positionMask, newNode, null, 8, null) : this;
    }

    public final TrieNode<K, V> mutableRemove(int keyHash, K key, int shift, PersistentHashMapBuilder<K, V> mutator) {
        TrieNode<K, V> trieNodeMutableRemove;
        Intrinsics.checkNotNullParameter(mutator, "mutator");
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$kotlinx_collections_immutable(iIndexSegment)) {
            int iEntryKeyIndex$kotlinx_collections_immutable = entryKeyIndex$kotlinx_collections_immutable(iIndexSegment);
            return Intrinsics.areEqual(key, keyAtIndex(iEntryKeyIndex$kotlinx_collections_immutable)) ? mutableRemoveEntryAtIndex(iEntryKeyIndex$kotlinx_collections_immutable, iIndexSegment, mutator) : this;
        }
        if (!hasNodeAt(iIndexSegment)) {
            return this;
        }
        int iNodeIndex$kotlinx_collections_immutable = nodeIndex$kotlinx_collections_immutable(iIndexSegment);
        TrieNode<K, V> trieNodeNodeAtIndex$kotlinx_collections_immutable = nodeAtIndex$kotlinx_collections_immutable(iNodeIndex$kotlinx_collections_immutable);
        if (shift == 30) {
            trieNodeMutableRemove = trieNodeNodeAtIndex$kotlinx_collections_immutable.mutableCollisionRemove(key, mutator);
        } else {
            trieNodeMutableRemove = trieNodeNodeAtIndex$kotlinx_collections_immutable.mutableRemove(keyHash, key, shift + 5, mutator);
        }
        return mutableReplaceNode(trieNodeMutableRemove, iNodeIndex$kotlinx_collections_immutable, iIndexSegment, mutator.getOwnership());
    }

    private final TrieNode<K, V> mutableReplaceNode(TrieNode<K, V> newNode, int nodeIndex, int positionMask, MutabilityOwnership owner) {
        if (newNode == null) {
            return mutableRemoveNodeAtIndex(nodeIndex, positionMask, owner);
        }
        return updateNodeAtIndex(nodeIndex, positionMask, newNode, owner);
    }

    public final TrieNode<K, V> remove(int keyHash, K key, V value, int shift) {
        TrieNode<K, V> trieNodeRemove;
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$kotlinx_collections_immutable(iIndexSegment)) {
            int iEntryKeyIndex$kotlinx_collections_immutable = entryKeyIndex$kotlinx_collections_immutable(iIndexSegment);
            return (Intrinsics.areEqual(key, keyAtIndex(iEntryKeyIndex$kotlinx_collections_immutable)) && Intrinsics.areEqual(value, valueAtKeyIndex(iEntryKeyIndex$kotlinx_collections_immutable))) ? removeEntryAtIndex(iEntryKeyIndex$kotlinx_collections_immutable, iIndexSegment) : this;
        }
        if (!hasNodeAt(iIndexSegment)) {
            return this;
        }
        int iNodeIndex$kotlinx_collections_immutable = nodeIndex$kotlinx_collections_immutable(iIndexSegment);
        TrieNode<K, V> trieNodeNodeAtIndex$kotlinx_collections_immutable = nodeAtIndex$kotlinx_collections_immutable(iNodeIndex$kotlinx_collections_immutable);
        if (shift == 30) {
            trieNodeRemove = trieNodeNodeAtIndex$kotlinx_collections_immutable.collisionRemove(key, value);
        } else {
            trieNodeRemove = trieNodeNodeAtIndex$kotlinx_collections_immutable.remove(keyHash, key, value, shift + 5);
        }
        return replaceNode(trieNodeNodeAtIndex$kotlinx_collections_immutable, trieNodeRemove, iNodeIndex$kotlinx_collections_immutable, iIndexSegment);
    }

    public final TrieNode<K, V> mutableRemove(int keyHash, K key, V value, int shift, PersistentHashMapBuilder<K, V> mutator) {
        PersistentHashMapBuilder<K, V> persistentHashMapBuilder;
        TrieNode<K, V> trieNodeMutableRemove;
        Intrinsics.checkNotNullParameter(mutator, "mutator");
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$kotlinx_collections_immutable(iIndexSegment)) {
            int iEntryKeyIndex$kotlinx_collections_immutable = entryKeyIndex$kotlinx_collections_immutable(iIndexSegment);
            return (Intrinsics.areEqual(key, keyAtIndex(iEntryKeyIndex$kotlinx_collections_immutable)) && Intrinsics.areEqual(value, valueAtKeyIndex(iEntryKeyIndex$kotlinx_collections_immutable))) ? mutableRemoveEntryAtIndex(iEntryKeyIndex$kotlinx_collections_immutable, iIndexSegment, mutator) : this;
        }
        if (!hasNodeAt(iIndexSegment)) {
            return this;
        }
        int iNodeIndex$kotlinx_collections_immutable = nodeIndex$kotlinx_collections_immutable(iIndexSegment);
        TrieNode<K, V> trieNodeNodeAtIndex$kotlinx_collections_immutable = nodeAtIndex$kotlinx_collections_immutable(iNodeIndex$kotlinx_collections_immutable);
        if (shift == 30) {
            trieNodeMutableRemove = trieNodeNodeAtIndex$kotlinx_collections_immutable.mutableCollisionRemove(key, value, mutator);
            persistentHashMapBuilder = mutator;
        } else {
            persistentHashMapBuilder = mutator;
            trieNodeMutableRemove = trieNodeNodeAtIndex$kotlinx_collections_immutable.mutableRemove(keyHash, key, value, shift + 5, persistentHashMapBuilder);
        }
        return mutableReplaceNode(trieNodeMutableRemove, iNodeIndex$kotlinx_collections_immutable, iIndexSegment, persistentHashMapBuilder.getOwnership());
    }

    public final <K1, V1> boolean equalsWith$kotlinx_collections_immutable(TrieNode<K1, V1> that, Function2<? super V, ? super V1, Boolean> equalityComparator) {
        int i;
        Intrinsics.checkNotNullParameter(that, "that");
        Intrinsics.checkNotNullParameter(equalityComparator, "equalityComparator");
        if (this == that) {
            return true;
        }
        int i2 = this.dataMap;
        if (i2 != that.dataMap || (i = this.nodeMap) != that.nodeMap) {
            return false;
        }
        if (i2 == 0 && i == 0) {
            Object[] objArr = this.buffer;
            if (objArr.length != that.buffer.length) {
                return false;
            }
            Iterable iterableStep = RangesKt.step(RangesKt.until(0, objArr.length), 2);
            if ((iterableStep instanceof Collection) && ((Collection) iterableStep).isEmpty()) {
                return true;
            }
            Iterator it = iterableStep.iterator();
            while (it.hasNext()) {
                int iNextInt = ((IntIterator) it).nextInt();
                K1 k1KeyAtIndex = that.keyAtIndex(iNextInt);
                V1 v1ValueAtKeyIndex = that.valueAtKeyIndex(iNextInt);
                int iCollisionKeyIndex = collisionKeyIndex(k1KeyAtIndex);
                if (!(iCollisionKeyIndex != -1 ? equalityComparator.invoke(valueAtKeyIndex(iCollisionKeyIndex), v1ValueAtKeyIndex).booleanValue() : false)) {
                    return false;
                }
            }
            return true;
        }
        int iBitCount = Integer.bitCount(i2) * 2;
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, iBitCount), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (Intrinsics.areEqual(keyAtIndex(first), that.keyAtIndex(first)) && equalityComparator.invoke(valueAtKeyIndex(first), that.valueAtKeyIndex(first)).booleanValue()) {
                if (first != last) {
                    first += step;
                }
            }
            return false;
        }
        int length = this.buffer.length;
        while (iBitCount < length) {
            if (!nodeAtIndex$kotlinx_collections_immutable(iBitCount).equalsWith$kotlinx_collections_immutable(that.nodeAtIndex$kotlinx_collections_immutable(iBitCount), equalityComparator)) {
                return false;
            }
            iBitCount++;
        }
        return true;
    }

    public final void accept$kotlinx_collections_immutable(Function5<? super TrieNode<K, V>, ? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> visitor) {
        Intrinsics.checkNotNullParameter(visitor, "visitor");
        accept(visitor, 0, 0);
    }

    private final void accept(Function5<? super TrieNode<K, V>, ? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> visitor, int hash, int shift) {
        visitor.invoke(this, Integer.valueOf(shift), Integer.valueOf(hash), Integer.valueOf(this.dataMap), Integer.valueOf(this.nodeMap));
        int i = this.nodeMap;
        while (i != 0) {
            int iLowestOneBit = Integer.lowestOneBit(i);
            nodeAtIndex$kotlinx_collections_immutable(nodeIndex$kotlinx_collections_immutable(iLowestOneBit)).accept(visitor, (Integer.numberOfTrailingZeros(iLowestOneBit) << shift) + hash, shift + 5);
            i -= iLowestOneBit;
        }
    }

    /* JADX INFO: compiled from: TrieNode.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode$Companion;", "", "<init>", "()V", "EMPTY", "Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "", "getEMPTY$kotlinx_collections_immutable", "()Lkotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TrieNode getEMPTY$kotlinx_collections_immutable() {
            return TrieNode.EMPTY;
        }
    }
}
