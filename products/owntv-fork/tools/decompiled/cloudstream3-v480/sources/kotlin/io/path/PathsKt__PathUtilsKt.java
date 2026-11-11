package kotlin.io.path;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import j$.nio.file.CopyOption;
import j$.nio.file.FileStore;
import j$.nio.file.FileVisitOption;
import j$.nio.file.FileVisitor;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.Path;
import j$.nio.file.Paths;
import j$.nio.file.StandardCopyOption;
import j$.nio.file.attribute.BasicFileAttributes;
import j$.nio.file.attribute.FileAttribute;
import j$.nio.file.attribute.FileAttributeView;
import j$.nio.file.attribute.FileTime;
import j$.nio.file.attribute.PosixFilePermission;
import j$.nio.file.attribute.UserPrincipal;
import java.io.IOException;
import java.net.URI;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.IgnorableReturnValue;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;
import org.slf4j.Marker;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: PathUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\b\u0010\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\b\u0007\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\u001a\u0014\u0010\u0001\u001a\u00020\u0000*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0001\u0010\u0002\u001a\u0014\u0010\u0004\u001a\u00020\u0003*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001b\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a\u001b\u0010\t\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\t\u0010\b\u001a\u001d\u0010\n\u001a\u0004\u0018\u00010\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\n\u0010\b\u001a&\u0010\u000e\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\r\u001a\u00020\fH\u0087\b¢\u0006\u0004\b\u000e\u0010\u000f\u001a0\u0010\u000e\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u0010\"\u00020\u0011H\u0087\b¢\u0006\u0004\b\u000e\u0010\u0013\u001a(\u0010\u0015\u001a\u00020\f*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\b\u0015\u0010\u0016\u001a(\u0010\u0017\u001a\u00020\f*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\b\u0017\u0010\u0016\u001a(\u0010\u0018\u001a\u00020\f*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\b\u0018\u0010\u0016\u001a(\u0010\u0019\u001a\u00020\f*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\b\u0019\u0010\u0016\u001a\u0014\u0010\u001a\u001a\u00020\f*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u0014\u0010\u001c\u001a\u00020\f*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u001c\u0010\u001b\u001a\u0014\u0010\u001d\u001a\u00020\f*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u001d\u0010\u001b\u001a\u0014\u0010\u001e\u001a\u00020\f*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u001e\u0010\u001b\u001a\u0014\u0010\u001f\u001a\u00020\f*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u001f\u0010\u001b\u001a\u001c\u0010!\u001a\u00020\f*\u00020\u00002\u0006\u0010 \u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b!\u0010\"\u001a#\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00000$*\u00020\u00002\b\b\u0002\u0010#\u001a\u00020\u0003H\u0007¢\u0006\u0004\b%\u0010&\u001aA\u0010+\u001a\u00028\u0000\"\u0004\b\u0000\u0010'*\u00020\u00002\b\b\u0002\u0010#\u001a\u00020\u00032\u0018\u0010*\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00000)\u0012\u0004\u0012\u00028\u00000(H\u0087\bø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a5\u0010/\u001a\u00020-*\u00020\u00002\b\b\u0002\u0010#\u001a\u00020\u00032\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020-0(H\u0087\bø\u0001\u0000¢\u0006\u0004\b/\u00100\u001a\u0014\u00102\u001a\u000201*\u00020\u0000H\u0087\b¢\u0006\u0004\b2\u00103\u001a\u0014\u00104\u001a\u00020-*\u00020\u0000H\u0087\b¢\u0006\u0004\b4\u00105\u001a\u0014\u00106\u001a\u00020\f*\u00020\u0000H\u0087\b¢\u0006\u0004\b6\u0010\u001b\u001a0\u00109\u001a\u00020\u0000*\u00020\u00002\u001a\u00108\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u0003070\u0010\"\u0006\u0012\u0002\b\u000307H\u0087\b¢\u0006\u0004\b9\u0010:\u001a0\u0010;\u001a\u00020\u0000*\u00020\u00002\u001a\u00108\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u0003070\u0010\"\u0006\u0012\u0002\b\u000307H\u0087\b¢\u0006\u0004\b;\u0010:\u001a/\u0010<\u001a\u00020\u0000*\u00020\u00002\u001a\u00108\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u0003070\u0010\"\u0006\u0012\u0002\b\u000307H\u0007¢\u0006\u0004\b<\u0010:\u001a0\u0010=\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u0010\"\u00020\u0011H\u0087\b¢\u0006\u0004\b=\u0010\u0013\u001a&\u0010=\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\r\u001a\u00020\fH\u0087\b¢\u0006\u0004\b=\u0010\u000f\u001a\u0014\u0010?\u001a\u00020>*\u00020\u0000H\u0087\b¢\u0006\u0004\b?\u0010@\u001a2\u0010C\u001a\u0004\u0018\u00010B*\u00020\u00002\u0006\u0010A\u001a\u00020\u00032\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\bC\u0010D\u001a:\u0010F\u001a\u00020\u0000*\u00020\u00002\u0006\u0010A\u001a\u00020\u00032\b\u0010E\u001a\u0004\u0018\u00010B2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\bF\u0010G\u001a6\u0010J\u001a\u0004\u0018\u00018\u0000\"\n\b\u0000\u0010I\u0018\u0001*\u00020H*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\bJ\u0010K\u001a4\u0010L\u001a\u00028\u0000\"\n\b\u0000\u0010I\u0018\u0001*\u00020H*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\bL\u0010K\u001a#\u0010Q\u001a\u00020P2\u0006\u0010M\u001a\u00020\u00002\n\u0010O\u001a\u0006\u0012\u0002\b\u00030NH\u0001¢\u0006\u0004\bQ\u0010R\u001a4\u0010U\u001a\u00028\u0000\"\n\b\u0000\u0010T\u0018\u0001*\u00020S*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\bU\u0010V\u001a>\u0010U\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010B0W*\u00020\u00002\u0006\u00108\u001a\u00020\u00032\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\bU\u0010X\u001a(\u0010Z\u001a\u00020Y*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\bZ\u0010[\u001a\u001c\u0010\\\u001a\u00020\u0000*\u00020\u00002\u0006\u0010E\u001a\u00020YH\u0087\b¢\u0006\u0004\b\\\u0010]\u001a*\u0010_\u001a\u0004\u0018\u00010^*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\b_\u0010`\u001a\u001c\u0010a\u001a\u00020\u0000*\u00020\u00002\u0006\u0010E\u001a\u00020^H\u0087\b¢\u0006\u0004\ba\u0010b\u001a.\u0010e\u001a\b\u0012\u0004\u0012\u00020d0c*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\be\u0010f\u001a\"\u0010g\u001a\u00020\u0000*\u00020\u00002\f\u0010E\u001a\b\u0012\u0004\u0012\u00020d0cH\u0087\b¢\u0006\u0004\bg\u0010h\u001a\u001c\u0010i\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\bi\u0010\b\u001a8\u0010j\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00002\u001a\u00108\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u0003070\u0010\"\u0006\u0012\u0002\b\u000307H\u0087\b¢\u0006\u0004\bj\u0010k\u001a\u0014\u0010l\u001a\u00020\u0000*\u00020\u0000H\u0087\b¢\u0006\u0004\bl\u0010\u0002\u001a0\u0010m\u001a\u00020\u0000*\u00020\u00002\u001a\u00108\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u0003070\u0010\"\u0006\u0012\u0002\b\u000307H\u0087\b¢\u0006\u0004\bm\u0010:\u001aD\u0010p\u001a\u00020\u00002\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010o\u001a\u0004\u0018\u00010\u00032\u001a\u00108\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u0003070\u0010\"\u0006\u0012\u0002\b\u000307H\u0087\b¢\u0006\u0004\bp\u0010q\u001aM\u0010p\u001a\u00020\u00002\b\u0010r\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010o\u001a\u0004\u0018\u00010\u00032\u001a\u00108\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u0003070\u0010\"\u0006\u0012\u0002\b\u000307H\u0007¢\u0006\u0004\bp\u0010s\u001a8\u0010t\u001a\u00020\u00002\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u00032\u001a\u00108\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u0003070\u0010\"\u0006\u0012\u0002\b\u000307H\u0087\b¢\u0006\u0004\bt\u0010u\u001aA\u0010t\u001a\u00020\u00002\b\u0010r\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u00032\u001a\u00108\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u0003070\u0010\"\u0006\u0012\u0002\b\u000307H\u0007¢\u0006\u0004\bt\u0010v\u001a\u001c\u0010w\u001a\u00020\u0000*\u00020\u00002\u0006\u0010 \u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bw\u0010\b\u001a\u001c\u0010w\u001a\u00020\u0000*\u00020\u00002\u0006\u0010 \u001a\u00020\u0003H\u0087\n¢\u0006\u0004\bw\u0010x\u001a\u0018\u0010y\u001a\u00020\u00002\u0006\u0010M\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\by\u0010z\u001a,\u0010y\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00032\u0012\u0010{\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0010\"\u00020\u0003H\u0087\b¢\u0006\u0004\by\u0010|\u001a\u0014\u0010~\u001a\u00020\u0000*\u00020}H\u0087\b¢\u0006\u0004\b~\u0010\u007f\u001a2\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020\u00000)*\u00020\u00002\u0014\u0010\u0012\u001a\u000b\u0012\u0007\b\u0001\u0012\u00030\u0080\u00010\u0010\"\u00030\u0080\u0001H\u0007¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001\u001a=\u0010\u0088\u0001\u001a\u00020-*\u00020\u00002\u000e\u0010\u0084\u0001\u001a\t\u0012\u0004\u0012\u00020\u00000\u0083\u00012\n\b\u0002\u0010\u0086\u0001\u001a\u00030\u0085\u00012\t\b\u0002\u0010\u0087\u0001\u001a\u00020\fH\u0007¢\u0006\u0006\b\u0088\u0001\u0010\u0089\u0001\u001aV\u0010\u0088\u0001\u001a\u00020-*\u00020\u00002\n\b\u0002\u0010\u0086\u0001\u001a\u00030\u0085\u00012\t\b\u0002\u0010\u0087\u0001\u001a\u00020\f2\u001a\u0010\u008c\u0001\u001a\u0015\u0012\u0005\u0012\u00030\u008a\u0001\u0012\u0004\u0012\u00020-0(¢\u0006\u0003\b\u008b\u0001H\u0007\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001¢\u0006\u0006\b\u0088\u0001\u0010\u008d\u0001\u001aB\u0010\u008e\u0001\u001a\t\u0012\u0004\u0012\u00020\u00000\u0083\u00012\u001a\u0010\u008c\u0001\u001a\u0015\u0012\u0005\u0012\u00030\u008a\u0001\u0012\u0004\u0012\u00020-0(¢\u0006\u0003\b\u008b\u0001H\u0007\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001\"!\u0010\u0092\u0001\u001a\u00020\u0003*\u00020\u00008FX\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u0091\u0001\u00105\u001a\u0005\b\u0090\u0001\u0010\u0005\"!\u0010\u0095\u0001\u001a\u00020\u0003*\u00020\u00008FX\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u0094\u0001\u00105\u001a\u0005\b\u0093\u0001\u0010\u0005\"!\u0010\u0098\u0001\u001a\u00020\u0003*\u00020\u00008FX\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u0097\u0001\u00105\u001a\u0005\b\u0096\u0001\u0010\u0005\"\"\u0010\u009b\u0001\u001a\u00020\u0003*\u00020\u00008Æ\u0002X\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u009a\u0001\u00105\u001a\u0005\b\u0099\u0001\u0010\u0005\"!\u0010\u009e\u0001\u001a\u00020\u0003*\u00020\u00008FX\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u009d\u0001\u00105\u001a\u0005\b\u009c\u0001\u0010\u0005\"\"\u0010¡\u0001\u001a\u00020\u0003*\u00020\u00008Æ\u0002X\u0087\u0004¢\u0006\u000e\u0012\u0005\b \u0001\u00105\u001a\u0005\b\u009f\u0001\u0010\u0005\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006¢\u0001"}, d2 = {"j$/nio/file/Path", "absolute", "(Lj$/nio/file/Path;)Lj$/nio/file/Path;", "", "absolutePathString", "(Lj$/nio/file/Path;)Ljava/lang/String;", TtmlNode.RUBY_BASE, "relativeTo", "(Lj$/nio/file/Path;Lj$/nio/file/Path;)Lj$/nio/file/Path;", "relativeToOrSelf", "relativeToOrNull", TypedValues.AttributesType.S_TARGET, "", "overwrite", "copyTo", "(Lj$/nio/file/Path;Lj$/nio/file/Path;Z)Lj$/nio/file/Path;", "", "j$/nio/file/CopyOption", "options", "(Lj$/nio/file/Path;Lj$/nio/file/Path;[Lj$/nio/file/CopyOption;)Lj$/nio/file/Path;", "j$/nio/file/LinkOption", "exists", "(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Z", "notExists", "isRegularFile", "isDirectory", "isSymbolicLink", "(Lj$/nio/file/Path;)Z", "isExecutable", "isHidden", "isReadable", "isWritable", "other", "isSameFileAs", "(Lj$/nio/file/Path;Lj$/nio/file/Path;)Z", "glob", "", "listDirectoryEntries", "(Lj$/nio/file/Path;Ljava/lang/String;)Ljava/util/List;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/Function1;", "Lkotlin/sequences/Sequence;", "block", "useDirectoryEntries", "(Lj$/nio/file/Path;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "", "action", "forEachDirectoryEntry", "(Lj$/nio/file/Path;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "", "fileSize", "(Lj$/nio/file/Path;)J", "deleteExisting", "(Lj$/nio/file/Path;)V", "deleteIfExists", "j$/nio/file/attribute/FileAttribute", "attributes", "createDirectory", "(Lj$/nio/file/Path;[Lj$/nio/file/attribute/FileAttribute;)Lj$/nio/file/Path;", "createDirectories", "createParentDirectories", "moveTo", "j$/nio/file/FileStore", "fileStore", "(Lj$/nio/file/Path;)Lj$/nio/file/FileStore;", "attribute", "", "getAttribute", "(Lj$/nio/file/Path;Ljava/lang/String;[Lj$/nio/file/LinkOption;)Ljava/lang/Object;", ES6Iterator.VALUE_PROPERTY, "setAttribute", "(Lj$/nio/file/Path;Ljava/lang/String;Ljava/lang/Object;[Lj$/nio/file/LinkOption;)Lj$/nio/file/Path;", "j$/nio/file/attribute/FileAttributeView", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "fileAttributesViewOrNull", "(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Lj$/nio/file/attribute/FileAttributeView;", "fileAttributesView", "path", "Ljava/lang/Class;", "attributeViewClass", "", "fileAttributeViewNotAvailable", "(Lj$/nio/file/Path;Ljava/lang/Class;)Ljava/lang/Void;", "j$/nio/file/attribute/BasicFileAttributes", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "readAttributes", "(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Lj$/nio/file/attribute/BasicFileAttributes;", "", "(Lj$/nio/file/Path;Ljava/lang/String;[Lj$/nio/file/LinkOption;)Ljava/util/Map;", "j$/nio/file/attribute/FileTime", "getLastModifiedTime", "(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Lj$/nio/file/attribute/FileTime;", "setLastModifiedTime", "(Lj$/nio/file/Path;Lj$/nio/file/attribute/FileTime;)Lj$/nio/file/Path;", "j$/nio/file/attribute/UserPrincipal", "getOwner", "(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Lj$/nio/file/attribute/UserPrincipal;", "setOwner", "(Lj$/nio/file/Path;Lj$/nio/file/attribute/UserPrincipal;)Lj$/nio/file/Path;", "", "j$/nio/file/attribute/PosixFilePermission", "getPosixFilePermissions", "(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Ljava/util/Set;", "setPosixFilePermissions", "(Lj$/nio/file/Path;Ljava/util/Set;)Lj$/nio/file/Path;", "createLinkPointingTo", "createSymbolicLinkPointingTo", "(Lj$/nio/file/Path;Lj$/nio/file/Path;[Lj$/nio/file/attribute/FileAttribute;)Lj$/nio/file/Path;", "readSymbolicLink", "createFile", "prefix", "suffix", "createTempFile", "(Ljava/lang/String;Ljava/lang/String;[Lj$/nio/file/attribute/FileAttribute;)Lj$/nio/file/Path;", "directory", "(Lj$/nio/file/Path;Ljava/lang/String;Ljava/lang/String;[Lj$/nio/file/attribute/FileAttribute;)Lj$/nio/file/Path;", "createTempDirectory", "(Ljava/lang/String;[Lj$/nio/file/attribute/FileAttribute;)Lj$/nio/file/Path;", "(Lj$/nio/file/Path;Ljava/lang/String;[Lj$/nio/file/attribute/FileAttribute;)Lj$/nio/file/Path;", TtmlNode.TAG_DIV, "(Lj$/nio/file/Path;Ljava/lang/String;)Lj$/nio/file/Path;", "Path", "(Ljava/lang/String;)Lj$/nio/file/Path;", "subpaths", "(Ljava/lang/String;[Ljava/lang/String;)Lj$/nio/file/Path;", "Ljava/net/URI;", "toPath", "(Ljava/net/URI;)Lj$/nio/file/Path;", "Lkotlin/io/path/PathWalkOption;", "walk", "(Lj$/nio/file/Path;[Lkotlin/io/path/PathWalkOption;)Lkotlin/sequences/Sequence;", "j$/nio/file/FileVisitor", "visitor", "", "maxDepth", "followLinks", "visitFileTree", "(Lj$/nio/file/Path;Lj$/nio/file/FileVisitor;IZ)V", "Lkotlin/io/path/FileVisitorBuilder;", "Lkotlin/ExtensionFunctionType;", "builderAction", "(Lj$/nio/file/Path;IZLkotlin/jvm/functions/Function1;)V", "fileVisitor", "(Lkotlin/jvm/functions/Function1;)Lj$/nio/file/FileVisitor;", "getName", "getName$annotations", "name", "getNameWithoutExtension", "getNameWithoutExtension$annotations", "nameWithoutExtension", "getExtension", "getExtension$annotations", "extension", "getPathString", "getPathString$annotations", "pathString", "getInvariantSeparatorsPathString", "getInvariantSeparatorsPathString$annotations", "invariantSeparatorsPathString", "getInvariantSeparatorsPath", "getInvariantSeparatorsPath$annotations", "invariantSeparatorsPath", "kotlin-stdlib-jdk7"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/io/path/PathsKt")
public class PathsKt__PathUtilsKt extends PathsKt__PathRecursiveFunctionsKt {
    public static /* synthetic */ void getExtension$annotations(Path path) {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use invariantSeparatorsPathString property instead.", replaceWith = @ReplaceWith(expression = "invariantSeparatorsPathString", imports = {}))
    public static /* synthetic */ void getInvariantSeparatorsPath$annotations(Path path) {
    }

    public static /* synthetic */ void getInvariantSeparatorsPathString$annotations(Path path) {
    }

    public static /* synthetic */ void getName$annotations(Path path) {
    }

    public static /* synthetic */ void getNameWithoutExtension$annotations(Path path) {
    }

    public static /* synthetic */ void getPathString$annotations(Path path) {
    }

    public static final String getName(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Path fileName = path.getFileName();
        String string = fileName != null ? fileName.toString() : null;
        return string == null ? "" : string;
    }

    public static final String getNameWithoutExtension(Path path) {
        String string;
        String strSubstringBeforeLast$default;
        Intrinsics.checkNotNullParameter(path, "<this>");
        Path fileName = path.getFileName();
        return (fileName == null || (string = fileName.toString()) == null || (strSubstringBeforeLast$default = StringsKt.substringBeforeLast$default(string, ".", (String) null, 2, (Object) null)) == null) ? "" : strSubstringBeforeLast$default;
    }

    public static final String getExtension(Path path) {
        String string;
        String strSubstringAfterLast;
        Intrinsics.checkNotNullParameter(path, "<this>");
        Path fileName = path.getFileName();
        return (fileName == null || (string = fileName.toString()) == null || (strSubstringAfterLast = StringsKt.substringAfterLast(string, '.', "")) == null) ? "" : strSubstringAfterLast;
    }

    private static final String getPathString(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return path.toString();
    }

    public static final String getInvariantSeparatorsPathString(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        String separator = path.getFileSystem().getSeparator();
        if (Intrinsics.areEqual(separator, "/")) {
            return path.toString();
        }
        String string = path.toString();
        Intrinsics.checkNotNull(separator);
        return StringsKt.replace$default(string, separator, "/", false, 4, (Object) null);
    }

    private static final String getInvariantSeparatorsPath(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return PathsKt.getInvariantSeparatorsPathString(path);
    }

    private static final Path absolute(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Path absolutePath = path.toAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "toAbsolutePath(...)");
        return absolutePath;
    }

    private static final String absolutePathString(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return path.toAbsolutePath().toString();
    }

    public static final Path relativeTo(Path path, Path base) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(base, "base");
        try {
            return PathRelativizer.INSTANCE.tryRelativeTo(path, base);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage() + "\nthis path: " + path + "\nbase path: " + base, e);
        }
    }

    public static final Path relativeToOrSelf(Path path, Path base) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(base, "base");
        Path pathRelativeToOrNull = PathsKt.relativeToOrNull(path, base);
        return pathRelativeToOrNull == null ? path : pathRelativeToOrNull;
    }

    public static final Path relativeToOrNull(Path path, Path base) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(base, "base");
        try {
            return PathRelativizer.INSTANCE.tryRelativeTo(path, base);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    static /* synthetic */ Path copyTo$default(Path path, Path target, boolean z, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            z = false;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        CopyOption[] copyOptionArr = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path pathCopy = Files.copy(path, target, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(pathCopy, "copy(...)");
        return pathCopy;
    }

    @IgnorableReturnValue
    private static final Path copyTo(Path path, Path target, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        CopyOption[] copyOptionArr = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path pathCopy = Files.copy(path, target, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(pathCopy, "copy(...)");
        return pathCopy;
    }

    @IgnorableReturnValue
    private static final Path copyTo(Path path, Path target, CopyOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(options, "options");
        Path pathCopy = Files.copy(path, target, (CopyOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(pathCopy, "copy(...)");
        return pathCopy;
    }

    private static final boolean exists(Path path, LinkOption... options) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        return Files.exists(path, (LinkOption[]) Arrays.copyOf(options, options.length));
    }

    private static final boolean notExists(Path path, LinkOption... options) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        return Files.notExists(path, (LinkOption[]) Arrays.copyOf(options, options.length));
    }

    private static final boolean isRegularFile(Path path, LinkOption... options) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        return Files.isRegularFile(path, (LinkOption[]) Arrays.copyOf(options, options.length));
    }

    private static final boolean isDirectory(Path path, LinkOption... options) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        return Files.isDirectory(path, (LinkOption[]) Arrays.copyOf(options, options.length));
    }

    private static final boolean isSymbolicLink(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return Files.isSymbolicLink(path);
    }

    private static final boolean isExecutable(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return Files.isExecutable(path);
    }

    private static final boolean isHidden(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return Files.isHidden(path);
    }

    private static final boolean isReadable(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return Files.isReadable(path);
    }

    private static final boolean isWritable(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return Files.isWritable(path);
    }

    private static final boolean isSameFileAs(Path path, Path other) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return Files.isSameFile(path, other);
    }

    public static /* synthetic */ List listDirectoryEntries$default(Path path, String str, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            str = Marker.ANY_MARKER;
        }
        return PathsKt.listDirectoryEntries(path, str);
    }

    public static final List<Path> listDirectoryEntries(Path path, String glob) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(glob, "glob");
        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path, glob);
        try {
            DirectoryStream<Path> directoryStream = directoryStreamNewDirectoryStream;
            Intrinsics.checkNotNull(directoryStream);
            List<Path> list = CollectionsKt.toList(directoryStream);
            CloseableKt.closeFinally(directoryStreamNewDirectoryStream, null);
            return list;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(directoryStreamNewDirectoryStream, th);
                throw th2;
            }
        }
    }

    static /* synthetic */ Object useDirectoryEntries$default(Path path, String glob, Function1 block, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            glob = Marker.ANY_MARKER;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(glob, "glob");
        Intrinsics.checkNotNullParameter(block, "block");
        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path, glob);
        try {
            DirectoryStream<Path> directoryStream = directoryStreamNewDirectoryStream;
            Intrinsics.checkNotNull(directoryStream);
            Object objInvoke = block.invoke(CollectionsKt.asSequence(directoryStream));
            CloseableKt.closeFinally(directoryStreamNewDirectoryStream, null);
            return objInvoke;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(directoryStreamNewDirectoryStream, th);
                throw th2;
            }
        }
    }

    @IgnorableReturnValue
    private static final <T> T useDirectoryEntries(Path path, String glob, Function1<? super Sequence<? extends Path>, ? extends T> block) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(glob, "glob");
        Intrinsics.checkNotNullParameter(block, "block");
        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path, glob);
        try {
            DirectoryStream<Path> directoryStream = directoryStreamNewDirectoryStream;
            Intrinsics.checkNotNull(directoryStream);
            T tInvoke = block.invoke(CollectionsKt.asSequence(directoryStream));
            CloseableKt.closeFinally(directoryStreamNewDirectoryStream, null);
            return tInvoke;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(directoryStreamNewDirectoryStream, th);
                throw th2;
            }
        }
    }

    static /* synthetic */ void forEachDirectoryEntry$default(Path path, String glob, Function1 action, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            glob = Marker.ANY_MARKER;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(glob, "glob");
        Intrinsics.checkNotNullParameter(action, "action");
        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path, glob);
        try {
            DirectoryStream<Path> directoryStream = directoryStreamNewDirectoryStream;
            Intrinsics.checkNotNull(directoryStream);
            Iterator<Path> it = directoryStream.iterator();
            while (it.hasNext()) {
                action.invoke(it.next());
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(directoryStreamNewDirectoryStream, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(directoryStreamNewDirectoryStream, th);
                throw th2;
            }
        }
    }

    private static final void forEachDirectoryEntry(Path path, String glob, Function1<? super Path, Unit> action) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(glob, "glob");
        Intrinsics.checkNotNullParameter(action, "action");
        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path, glob);
        try {
            DirectoryStream<Path> directoryStream = directoryStreamNewDirectoryStream;
            Intrinsics.checkNotNull(directoryStream);
            Iterator<Path> it = directoryStream.iterator();
            while (it.hasNext()) {
                action.invoke(it.next());
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(directoryStreamNewDirectoryStream, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(directoryStreamNewDirectoryStream, th);
                throw th2;
            }
        }
    }

    private static final long fileSize(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return Files.size(path);
    }

    private static final void deleteExisting(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Files.delete(path);
    }

    @IgnorableReturnValue
    private static final boolean deleteIfExists(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return Files.deleteIfExists(path);
    }

    @IgnorableReturnValue
    private static final Path createDirectory(Path path, FileAttribute<?>... attributes) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Path pathCreateDirectory = Files.createDirectory(path, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateDirectory, "createDirectory(...)");
        return pathCreateDirectory;
    }

    @IgnorableReturnValue
    private static final Path createDirectories(Path path, FileAttribute<?>... attributes) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Path pathCreateDirectories = Files.createDirectories(path, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateDirectories, "createDirectories(...)");
        return pathCreateDirectories;
    }

    @IgnorableReturnValue
    public static final Path createParentDirectories(Path path, FileAttribute<?>... attributes) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Path parent = path.getParent();
        if (parent != null && !Files.isDirectory(parent, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0))) {
            try {
                FileAttribute[] fileAttributeArr = (FileAttribute[]) Arrays.copyOf(attributes, attributes.length);
                Intrinsics.checkNotNullExpressionValue(Files.createDirectories(parent, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length)), "createDirectories(...)");
                return path;
            } catch (FileAlreadyExistsException e) {
                if (!Files.isDirectory(parent, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0))) {
                    throw e;
                }
            }
        }
        return path;
    }

    @IgnorableReturnValue
    private static final Path moveTo(Path path, Path target, CopyOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(options, "options");
        Path pathMove = Files.move(path, target, (CopyOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(pathMove, "move(...)");
        return pathMove;
    }

    static /* synthetic */ Path moveTo$default(Path path, Path target, boolean z, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            z = false;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        CopyOption[] copyOptionArr = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path pathMove = Files.move(path, target, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(pathMove, "move(...)");
        return pathMove;
    }

    @IgnorableReturnValue
    private static final Path moveTo(Path path, Path target, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        CopyOption[] copyOptionArr = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path pathMove = Files.move(path, target, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(pathMove, "move(...)");
        return pathMove;
    }

    private static final FileStore fileStore(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        FileStore fileStore = Files.getFileStore(path);
        Intrinsics.checkNotNullExpressionValue(fileStore, "getFileStore(...)");
        return fileStore;
    }

    private static final Object getAttribute(Path path, String attribute, LinkOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        Intrinsics.checkNotNullParameter(options, "options");
        return Files.getAttribute(path, attribute, (LinkOption[]) Arrays.copyOf(options, options.length));
    }

    @IgnorableReturnValue
    private static final Path setAttribute(Path path, String attribute, Object obj, LinkOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        Intrinsics.checkNotNullParameter(options, "options");
        Path attribute2 = Files.setAttribute(path, attribute, obj, (LinkOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(attribute2, "setAttribute(...)");
        return attribute2;
    }

    private static final /* synthetic */ <V extends FileAttributeView> V fileAttributesViewOrNull(Path path, LinkOption... options) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
        return (V) Files.getFileAttributeView(path, FileAttributeView.class, (LinkOption[]) Arrays.copyOf(options, options.length));
    }

    private static final /* synthetic */ <V extends FileAttributeView> V fileAttributesView(Path path, LinkOption... options) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
        V v = (V) Files.getFileAttributeView(path, FileAttributeView.class, (LinkOption[]) Arrays.copyOf(options, options.length));
        if (v != null) {
            return v;
        }
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
        PathsKt.fileAttributeViewNotAvailable(path, FileAttributeView.class);
        throw new KotlinNothingValueException();
    }

    public static final Void fileAttributeViewNotAvailable(Path path, Class<?> attributeViewClass) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(attributeViewClass, "attributeViewClass");
        throw new UnsupportedOperationException("The desired attribute view type " + attributeViewClass + " is not available for the file " + path + '.');
    }

    private static final /* synthetic */ <A extends BasicFileAttributes> A readAttributes(Path path, LinkOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
        A a = (A) Files.readAttributes(path, BasicFileAttributes.class, (LinkOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(a, "readAttributes(...)");
        return a;
    }

    private static final Map<String, Object> readAttributes(Path path, String attributes, LinkOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Intrinsics.checkNotNullParameter(options, "options");
        Map<String, Object> attributes2 = Files.readAttributes(path, attributes, (LinkOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(attributes2, "readAttributes(...)");
        return attributes2;
    }

    private static final FileTime getLastModifiedTime(Path path, LinkOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        FileTime lastModifiedTime = Files.getLastModifiedTime(path, (LinkOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(lastModifiedTime, "getLastModifiedTime(...)");
        return lastModifiedTime;
    }

    private static final Path setLastModifiedTime(Path path, FileTime value) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        Path lastModifiedTime = Files.setLastModifiedTime(path, value);
        Intrinsics.checkNotNullExpressionValue(lastModifiedTime, "setLastModifiedTime(...)");
        return lastModifiedTime;
    }

    private static final UserPrincipal getOwner(Path path, LinkOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        return Files.getOwner(path, (LinkOption[]) Arrays.copyOf(options, options.length));
    }

    @IgnorableReturnValue
    private static final Path setOwner(Path path, UserPrincipal value) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        Path owner = Files.setOwner(path, value);
        Intrinsics.checkNotNullExpressionValue(owner, "setOwner(...)");
        return owner;
    }

    private static final Set<PosixFilePermission> getPosixFilePermissions(Path path, LinkOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        Set<PosixFilePermission> posixFilePermissions = Files.getPosixFilePermissions(path, (LinkOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(posixFilePermissions, "getPosixFilePermissions(...)");
        return posixFilePermissions;
    }

    private static final Path setPosixFilePermissions(Path path, Set<? extends PosixFilePermission> value) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        Path posixFilePermissions = Files.setPosixFilePermissions(path, value);
        Intrinsics.checkNotNullExpressionValue(posixFilePermissions, "setPosixFilePermissions(...)");
        return posixFilePermissions;
    }

    @IgnorableReturnValue
    private static final Path createLinkPointingTo(Path path, Path target) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        Path pathCreateLink = Files.createLink(path, target);
        Intrinsics.checkNotNullExpressionValue(pathCreateLink, "createLink(...)");
        return pathCreateLink;
    }

    @IgnorableReturnValue
    private static final Path createSymbolicLinkPointingTo(Path path, Path target, FileAttribute<?>... attributes) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Path pathCreateSymbolicLink = Files.createSymbolicLink(path, target, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateSymbolicLink, "createSymbolicLink(...)");
        return pathCreateSymbolicLink;
    }

    private static final Path readSymbolicLink(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Path symbolicLink = Files.readSymbolicLink(path);
        Intrinsics.checkNotNullExpressionValue(symbolicLink, "readSymbolicLink(...)");
        return symbolicLink;
    }

    @IgnorableReturnValue
    private static final Path createFile(Path path, FileAttribute<?>... attributes) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Path pathCreateFile = Files.createFile(path, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateFile, "createFile(...)");
        return pathCreateFile;
    }

    static /* synthetic */ Path createTempFile$default(String str, String str2, FileAttribute[] attributes, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Path pathCreateTempFile = Files.createTempFile(str, str2, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateTempFile, "createTempFile(...)");
        return pathCreateTempFile;
    }

    private static final Path createTempFile(String str, String str2, FileAttribute<?>... attributes) throws IOException {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Path pathCreateTempFile = Files.createTempFile(str, str2, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateTempFile, "createTempFile(...)");
        return pathCreateTempFile;
    }

    public static /* synthetic */ Path createTempFile$default(Path path, String str, String str2, FileAttribute[] fileAttributeArr, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        return PathsKt.createTempFile(path, str, str2, fileAttributeArr);
    }

    public static final Path createTempFile(Path path, String str, String str2, FileAttribute<?>... attributes) throws IOException {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        if (path != null) {
            Path pathCreateTempFile = Files.createTempFile(path, str, str2, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
            Intrinsics.checkNotNullExpressionValue(pathCreateTempFile, "createTempFile(...)");
            return pathCreateTempFile;
        }
        Path pathCreateTempFile2 = Files.createTempFile(str, str2, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateTempFile2, "createTempFile(...)");
        return pathCreateTempFile2;
    }

    static /* synthetic */ Path createTempDirectory$default(String str, FileAttribute[] attributes, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            str = null;
        }
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Path pathCreateTempDirectory = Files.createTempDirectory(str, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateTempDirectory, "createTempDirectory(...)");
        return pathCreateTempDirectory;
    }

    private static final Path createTempDirectory(String str, FileAttribute<?>... attributes) throws IOException {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Path pathCreateTempDirectory = Files.createTempDirectory(str, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateTempDirectory, "createTempDirectory(...)");
        return pathCreateTempDirectory;
    }

    public static /* synthetic */ Path createTempDirectory$default(Path path, String str, FileAttribute[] fileAttributeArr, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            str = null;
        }
        return PathsKt.createTempDirectory(path, str, fileAttributeArr);
    }

    public static final Path createTempDirectory(Path path, String str, FileAttribute<?>... attributes) throws IOException {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        if (path != null) {
            Path pathCreateTempDirectory = Files.createTempDirectory(path, str, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
            Intrinsics.checkNotNullExpressionValue(pathCreateTempDirectory, "createTempDirectory(...)");
            return pathCreateTempDirectory;
        }
        Path pathCreateTempDirectory2 = Files.createTempDirectory(str, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateTempDirectory2, "createTempDirectory(...)");
        return pathCreateTempDirectory2;
    }

    private static final Path div(Path path, Path other) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Path pathResolve = path.resolve(other);
        Intrinsics.checkNotNullExpressionValue(pathResolve, "resolve(...)");
        return pathResolve;
    }

    private static final Path div(Path path, String other) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Path pathResolve = path.resolve(other);
        Intrinsics.checkNotNullExpressionValue(pathResolve, "resolve(...)");
        return pathResolve;
    }

    private static final Path Path(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        Path path2 = Paths.get(path, new String[0]);
        Intrinsics.checkNotNullExpressionValue(path2, "get(...)");
        return path2;
    }

    private static final Path Path(String base, String... subpaths) {
        Intrinsics.checkNotNullParameter(base, "base");
        Intrinsics.checkNotNullParameter(subpaths, "subpaths");
        Path path = Paths.get(base, (String[]) Arrays.copyOf(subpaths, subpaths.length));
        Intrinsics.checkNotNullExpressionValue(path, "get(...)");
        return path;
    }

    private static final Path toPath(URI uri) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        Path path = Paths.get(uri);
        Intrinsics.checkNotNullExpressionValue(path, "get(...)");
        return path;
    }

    public static final Sequence<Path> walk(Path path, PathWalkOption... options) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        return new PathTreeWalk(path, options);
    }

    public static /* synthetic */ void visitFileTree$default(Path path, FileVisitor fileVisitor, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        PathsKt.visitFileTree(path, (FileVisitor<Path>) fileVisitor, i, z);
    }

    public static final void visitFileTree(Path path, FileVisitor<Path> visitor, int i, boolean z) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(visitor, "visitor");
        Files.walkFileTree(path, z ? SetsKt.setOf(FileVisitOption.FOLLOW_LINKS) : SetsKt.emptySet(), i, visitor);
    }

    public static /* synthetic */ void visitFileTree$default(Path path, int i, boolean z, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Integer.MAX_VALUE;
        }
        if ((i2 & 2) != 0) {
            z = false;
        }
        PathsKt.visitFileTree(path, i, z, (Function1<? super FileVisitorBuilder, Unit>) function1);
    }

    public static final void visitFileTree(Path path, int i, boolean z, Function1<? super FileVisitorBuilder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        PathsKt.visitFileTree(path, PathsKt.fileVisitor(builderAction), i, z);
    }

    public static final FileVisitor<Path> fileVisitor(Function1<? super FileVisitorBuilder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        FileVisitorBuilderImpl fileVisitorBuilderImpl = new FileVisitorBuilderImpl();
        builderAction.invoke(fileVisitorBuilderImpl);
        return fileVisitorBuilderImpl.build();
    }
}
