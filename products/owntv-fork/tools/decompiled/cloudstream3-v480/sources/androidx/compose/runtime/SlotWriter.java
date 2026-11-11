package androidx.compose.runtime;

import androidx.collection.IntSetKt;
import androidx.collection.MutableIntList;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.collection.MutableObjectList;
import androidx.compose.ui.spatial.RectListKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.common.C;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0001\u0018\u0000 Ö\u00012\u00020\u0001:\u0002Ö\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u00103\u001a\u00020/2\u0006\u00108\u001a\u00020\u0019J\u000e\u0010!\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019J\u000e\u00109\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019J\u0010\u0010:\u001a\u0004\u0018\u00010\u00012\u0006\u00108\u001a\u00020\u0019J\u000e\u0010;\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019J\u0010\u0010<\u001a\u0004\u0018\u00010\u00012\u0006\u00108\u001a\u00020\u0019J\u000e\u0010=\u001a\u00020/2\u0006\u00108\u001a\u00020\u0019J\u000e\u0010>\u001a\u00020/2\u0006\u00108\u001a\u00020\u0019J\u0016\u0010?\u001a\u00020/2\u0006\u00108\u001a\u00020\u00192\u0006\u0010@\u001a\u00020\u0019J\u0010\u0010A\u001a\u0004\u0018\u00010\u00012\u0006\u00108\u001a\u00020\u0019J\u0010\u0010A\u001a\u0004\u0018\u00010\u00012\u0006\u0010B\u001a\u00020\u000fJ\u000e\u0010C\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019J\u000e\u0010C\u001a\u00020\u00192\u0006\u0010B\u001a\u00020\u000fJ\u000e\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020/J\u0006\u0010J\u001a\u00020HJ\u0012\u0010K\u001a\u0004\u0018\u00010\u00012\b\u0010(\u001a\u0004\u0018\u00010\u0001J\u0014\u0010L\u001a\u0004\u0018\u00010\u00012\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u0002J\u0018\u0010M\u001a\u00020H2\u0006\u0010B\u001a\u00020\u000f2\b\u0010(\u001a\u0004\u0018\u00010\u0001J\u000e\u0010N\u001a\u00020H2\u0006\u0010O\u001a\u00020\u0019J\u0010\u0010P\u001a\u00020H2\b\u0010(\u001a\u0004\u0018\u00010\u0001J\u0010\u0010Q\u001a\u00020H2\b\u0010(\u001a\u0004\u0018\u00010\u0001J\u0006\u0010R\u001a\u00020HJ\u000e\u0010S\u001a\u00020H2\u0006\u0010T\u001a\u00020UJ\u0016\u0010V\u001a\u00020H2\u0006\u0010W\u001a\u00020\u00192\u0006\u0010(\u001a\u00020UJ\u0006\u0010X\u001a\u00020HJ\u001c\u0010Y\u001a\u0004\u0018\u00010\u00132\u0006\u0010C\u001a\u00020\u00192\b\u0010T\u001a\u0004\u0018\u00010UH\u0002J\u0010\u0010Z\u001a\u00020H2\b\u0010(\u001a\u0004\u0018\u00010\u0001J\u0018\u0010Z\u001a\u00020H2\u0006\u0010B\u001a\u00020\u000f2\b\u0010(\u001a\u0004\u0018\u00010\u0001J\u0010\u0010[\u001a\u00020H2\b\u0010(\u001a\u0004\u0018\u00010\u0001J\u0010\u0010\\\u001a\u00020H2\b\u0010(\u001a\u0004\u0018\u00010\u0001J\u001d\u0010\\\u001a\u0004\u0018\u00010\u00012\u0006\u00108\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u0086\bJ\u0016\u0010]\u001a\u00020\u00192\u0006\u0010@\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019J\"\u0010\\\u001a\u0004\u0018\u00010\u00012\u0006\u0010@\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010\u0001J\u0010\u0010^\u001a\u0004\u0018\u00010\u00012\u0006\u0010_\u001a\u00020\u0019J\b\u0010`\u001a\u0004\u0018\u00010\u0001J\u0018\u0010a\u001a\u0004\u0018\u00010\u00012\u0006\u0010B\u001a\u00020\u000f2\u0006\u00108\u001a\u00020\u0019J\u0018\u0010a\u001a\u0004\u0018\u00010\u00012\u0006\u0010b\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019J5\u0010c\u001a\u00020H2\u0006\u0010b\u001a\u00020\u00192\u0006\u0010O\u001a\u00020\u00192\u001a\u0010d\u001a\u0016\u0012\u0004\u0012\u00020\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020H0eH\u0086\bJ\u0015\u0010f\u001a\u00020\u00192\u0006\u0010b\u001a\u00020\u0019H\u0000¢\u0006\u0002\bgJ\u0015\u0010h\u001a\u00020\u00192\u0006\u0010b\u001a\u00020\u0019H\u0000¢\u0006\u0002\biJ\u0015\u0010j\u001a\u00020\u00192\u0006\u0010b\u001a\u00020\u0019H\u0000¢\u0006\u0002\bkJ\u000e\u0010n\u001a\u00020\u00192\u0006\u0010@\u001a\u00020\u0019J\u000e\u0010o\u001a\u00020H2\u0006\u0010p\u001a\u00020\u0019J\u000e\u0010q\u001a\u00020H2\u0006\u0010B\u001a\u00020\u000fJ\u0006\u0010r\u001a\u00020HJ\u0006\u0010s\u001a\u00020HJ\u0006\u0010t\u001a\u00020HJ\u0006\u0010u\u001a\u00020HJ\u000e\u0010u\u001a\u00020H2\u0006\u0010W\u001a\u00020\u0019J\u0018\u0010u\u001a\u00020H2\u0006\u0010W\u001a\u00020\u00192\b\u0010v\u001a\u0004\u0018\u00010\u0001J\u0018\u0010w\u001a\u00020H2\u0006\u0010W\u001a\u00020\u00192\b\u0010x\u001a\u0004\u0018\u00010\u0001J\"\u0010w\u001a\u00020H2\u0006\u0010W\u001a\u00020\u00192\b\u0010x\u001a\u0004\u0018\u00010\u00012\b\u0010A\u001a\u0004\u0018\u00010\u0001J\"\u0010y\u001a\u00020H2\u0006\u0010W\u001a\u00020\u00192\b\u0010x\u001a\u0004\u0018\u00010\u00012\b\u0010z\u001a\u0004\u0018\u00010\u0001J\u0018\u0010y\u001a\u00020H2\u0006\u0010W\u001a\u00020\u00192\b\u0010z\u001a\u0004\u0018\u00010\u0001J,\u0010u\u001a\u00020H2\u0006\u0010W\u001a\u00020\u00192\b\u0010x\u001a\u0004\u0018\u00010\u00012\u0006\u00103\u001a\u00020/2\b\u0010z\u001a\u0004\u0018\u00010\u0001H\u0002J\u0006\u0010{\u001a\u00020\u0019J\u000e\u0010|\u001a\u00020H2\u0006\u00108\u001a\u00020\u0019J\u000e\u0010|\u001a\u00020H2\u0006\u0010B\u001a\u00020\u000fJ\u0006\u0010}\u001a\u00020\u0019J\u0006\u0010~\u001a\u00020/J\u000f\u0010\u007f\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0080\u0001JQ\u0010\u0081\u0001\u001a\u00020H2\u0006\u0010@\u001a\u00020\u00192=\u0010d\u001a9\u0012\u0015\u0012\u00130\u0019¢\u0006\u000e\b\u0082\u0001\u0012\t\b\u0083\u0001\u0012\u0004\b\b(8\u0012\u0018\u0012\u0016\u0018\u00010\u0001¢\u0006\u000f\b\u0082\u0001\u0012\n\b\u0083\u0001\u0012\u0005\b\b(\u0084\u0001\u0012\u0004\u0012\u00020H0eH\u0086\bJb\u0010\u0085\u0001\u001a\u00020H2\u0006\u0010@\u001a\u00020\u00192&\u0010\u0086\u0001\u001a!\u0012\u0016\u0012\u00140\u0019¢\u0006\u000f\b\u0082\u0001\u0012\n\b\u0083\u0001\u0012\u0005\b\b(\u0088\u0001\u0012\u0004\u0012\u00020H0\u0087\u00012&\u0010\u0089\u0001\u001a!\u0012\u0016\u0012\u00140\u0019¢\u0006\u000f\b\u0082\u0001\u0012\n\b\u0083\u0001\u0012\u0005\b\b(\u0088\u0001\u0012\u0004\u0012\u00020H0\u0087\u0001H\u0086\bJN\u0010\u008a\u0001\u001a\u00020H2\u0006\u0010@\u001a\u00020\u00192=\u0010d\u001a9\u0012\u0015\u0012\u00130\u0019¢\u0006\u000e\b\u0082\u0001\u0012\t\b\u0083\u0001\u0012\u0004\b\b(8\u0012\u0018\u0012\u0016\u0018\u00010\u0001¢\u0006\u000f\b\u0082\u0001\u0012\n\b\u0083\u0001\u0012\u0005\b\b(\u0084\u0001\u0012\u0004\u0012\u00020H0eJ\u0010\u0010\u008b\u0001\u001a\u00020H2\u0007\u0010\u008c\u0001\u001a\u00020\u0019J(\u0010\u008d\u0001\u001a\t\u0012\u0004\u0012\u00020\u000f0\u008e\u00012\u0006\u0010B\u001a\u00020\u000f2\u0007\u0010\u008c\u0001\u001a\u00020\u00192\u0007\u0010\u008f\u0001\u001a\u00020\u0000J)\u0010\u0090\u0001\u001a\t\u0012\u0004\u0012\u00020\u000f0\u008e\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00108\u001a\u00020\u00192\t\b\u0002\u0010\u0091\u0001\u001a\u00020/J\u0007\u0010\u0092\u0001\u001a\u00020HJ'\u0010\u0093\u0001\u001a\t\u0012\u0004\u0012\u00020\u000f0\u008e\u00012\u0007\u0010\u008c\u0001\u001a\u00020\u00192\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00108\u001a\u00020\u0019J\u0010\u0010B\u001a\u00020\u000f2\b\b\u0002\u00108\u001a\u00020\u0019J\u0011\u0010\u0094\u0001\u001a\u00020H2\b\b\u0002\u0010@\u001a\u00020\u0019J\u0011\u0010\u0095\u0001\u001a\u00020/2\u0006\u0010@\u001a\u00020\u0019H\u0002J\u0011\u0010\u0096\u0001\u001a\u00020/2\u0006\u0010@\u001a\u00020\u0019H\u0002J\t\u0010\u009a\u0001\u001a\u00020HH\u0002J\u0011\u0010\u009b\u0001\u001a\u00020H2\u0006\u0010@\u001a\u00020\u0019H\u0002J#\u0010\u009c\u0001\u001a\u00020H2\u0006\u0010@\u001a\u00020\u00192\u0007\u0010\\\u001a\u00030\u0098\u0001H\u0002¢\u0006\u0006\b\u009d\u0001\u0010\u009e\u0001J\u0011\u0010\u009f\u0001\u001a\u00020/2\u0006\u0010@\u001a\u00020\u0019H\u0002J\u000f\u0010 \u0001\u001a\u00020\u00192\u0006\u0010B\u001a\u00020\u000fJ\t\u0010¡\u0001\u001a\u00020UH\u0016J\t\u0010¢\u0001\u001a\u00020HH\u0002J\t\u0010£\u0001\u001a\u00020\u0019H\u0002J\"\u0010¤\u0001\u001a\u00020H2\u0006\u0010C\u001a\u00020\u00192\u0006\u0010{\u001a\u00020\u00192\u0007\u0010¥\u0001\u001a\u00020\u0019H\u0002J\u0011\u0010¦\u0001\u001a\u00020H2\u0006\u00108\u001a\u00020\u0019H\u0002J\u0019\u0010§\u0001\u001a\u00020H2\u0006\u00108\u001a\u00020\u00192\u0006\u0010@\u001a\u00020\u0019H\u0002J\t\u0010¨\u0001\u001a\u00020HH\u0002J\u0012\u0010©\u0001\u001a\u00020H2\u0007\u0010ª\u0001\u001a\u00020\u0019H\u0002J\u001a\u0010«\u0001\u001a\u00020H2\u0007\u0010ª\u0001\u001a\u00020\u00192\u0006\u0010@\u001a\u00020\u0019H\u0002J\u001b\u0010¬\u0001\u001a\u00020/2\u0007\u0010\u00ad\u0001\u001a\u00020\u00192\u0007\u0010®\u0001\u001a\u00020\u0019H\u0002J\u0019\u0010¯\u0001\u001a\u0004\u0018\u00010\u00132\u0006\u0010@\u001a\u00020\u0019H\u0000¢\u0006\u0003\b°\u0001J\u0019\u0010±\u0001\u001a\u0004\u0018\u00010\u000f2\u0006\u0010@\u001a\u00020\u0019H\u0000¢\u0006\u0003\b²\u0001J#\u0010³\u0001\u001a\u00020H2\u0007\u0010\u00ad\u0001\u001a\u00020\u00192\u0007\u0010®\u0001\u001a\u00020\u00192\u0006\u0010@\u001a\u00020\u0019H\u0002J\u001b\u0010´\u0001\u001a\u00020H2\u0006\u00108\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u0002J\u001b\u0010µ\u0001\u001a\u00020H2\u0007\u0010¶\u0001\u001a\u00020\u00192\u0007\u0010·\u0001\u001a\u00020\u0019H\u0002JC\u0010¸\u0001\u001a\u00020/2\u0007\u0010¹\u0001\u001a\u00020\u00192\u0007\u0010ª\u0001\u001a\u00020\u00192&\u0010\u0011\u001a\"\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012j\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0013\u0018\u0001`\u0014H\u0002J$\u0010º\u0001\u001a\u00020H2\u0007\u0010»\u0001\u001a\u00020\u00192\u0007\u0010¼\u0001\u001a\u00020\u00192\u0007\u0010ª\u0001\u001a\u00020\u0019H\u0002J\u0007\u0010½\u0001\u001a\u00020UJ\u001b\u0010¾\u0001\u001a\u00020H*\b0¿\u0001j\u0003`À\u00012\u0006\u00108\u001a\u00020\u0019H\u0002J\u000f\u0010Á\u0001\u001a\u00020HH\u0000¢\u0006\u0003\bÂ\u0001J\u000f\u0010Ã\u0001\u001a\u00020HH\u0000¢\u0006\u0003\bÄ\u0001J\u0011\u0010È\u0001\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019H\u0002J\u0012\u0010É\u0001\u001a\u00020\u00192\u0007\u0010Ê\u0001\u001a\u00020\u0019H\u0002J\u0014\u0010C\u001a\u00020\u0019*\u00020\t2\u0006\u00108\u001a\u00020\u0019H\u0002J\u0011\u0010Ê\u0001\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019H\u0002J\u0016\u0010Ê\u0001\u001a\u00020\u0019*\u00020\t2\u0007\u0010Ë\u0001\u001a\u00020\u0019H\u0002J\u0015\u0010_\u001a\u00020\u0019*\u00020\t2\u0007\u0010Ë\u0001\u001a\u00020\u0019H\u0002J\u001f\u0010Ì\u0001\u001a\u00020H*\u00020\t2\u0007\u0010Ë\u0001\u001a\u00020\u00192\u0007\u0010Ê\u0001\u001a\u00020\u0019H\u0002J\u0016\u0010Í\u0001\u001a\u00020\u0019*\u00020\t2\u0007\u0010Ë\u0001\u001a\u00020\u0019H\u0002J\u0016\u0010Î\u0001\u001a\u00020\u0019*\u00020\t2\u0007\u0010Ë\u0001\u001a\u00020\u0019H\u0002J\u0014\u0010Ï\u0001\u001a\t\u0012\u0004\u0012\u00020\u00190\u008e\u0001*\u00020\tH\u0002J\u0010\u0010Ð\u0001\u001a\t\u0012\u0004\u0012\u00020\u00190\u008e\u0001H\u0002J,\u0010Ñ\u0001\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u00192\u0007\u0010¹\u0001\u001a\u00020\u00192\u0007\u0010Ò\u0001\u001a\u00020\u00192\u0007\u0010Æ\u0001\u001a\u00020\u0019H\u0002J#\u0010Ó\u0001\u001a\u00020\u00192\u0006\u0010B\u001a\u00020\u00192\u0007\u0010Ò\u0001\u001a\u00020\u00192\u0007\u0010Æ\u0001\u001a\u00020\u0019H\u0002J\u001a\u0010Ô\u0001\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u00192\u0007\u0010¹\u0001\u001a\u00020\u0019H\u0002J\u0011\u0010Õ\u0001\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019H\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010\u0011\u001a\"\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012j\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0013\u0018\u0001`\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010&\u001a\u0012\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010'\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010)\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u0019@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u001e\u0010,\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u0019@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b-\u0010+R\u0011\u0010.\u001a\u00020/8F¢\u0006\u0006\u001a\u0004\b.\u00100R\u0011\u00101\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b2\u0010+R\u0011\u00103\u001a\u00020/8F¢\u0006\u0006\u001a\u0004\b3\u00100R\u0011\u00104\u001a\u00020/8F¢\u0006\u0006\u001a\u0004\b5\u00100R\u0011\u00106\u001a\u00020/8F¢\u0006\u0006\u001a\u0004\b7\u00100R\u001e\u0010C\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u0019@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bD\u0010+R\u001e\u0010E\u001a\u00020/2\u0006\u0010(\u001a\u00020/@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bF\u00100R\u0014\u0010l\u001a\u00020\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bm\u0010+R\u0015\u0010\u0097\u0001\u001a\u0005\u0018\u00010\u0098\u0001X\u0082\u000e¢\u0006\u0005\n\u0003\u0010\u0099\u0001R\u0016\u0010ª\u0001\u001a\u00020\u00198@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bÅ\u0001\u0010+R\u0016\u0010Æ\u0001\u001a\u00020\u00198BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bÇ\u0001\u0010+¨\u0006×\u0001"}, d2 = {"Landroidx/compose/runtime/SlotWriter;", "", "table", "Landroidx/compose/runtime/SlotTable;", "<init>", "(Landroidx/compose/runtime/SlotTable;)V", "getTable$runtime", "()Landroidx/compose/runtime/SlotTable;", "groups", "", "slots", "", "[Ljava/lang/Object;", "anchors", "Ljava/util/ArrayList;", "Landroidx/compose/runtime/Anchor;", "Lkotlin/collections/ArrayList;", "sourceInformationMap", "Ljava/util/HashMap;", "Landroidx/compose/runtime/GroupSourceInformation;", "Lkotlin/collections/HashMap;", "calledByMap", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/collection/MutableIntSet;", "groupGapStart", "", "groupGapLen", "currentSlot", "currentSlotEnd", "slotsGapStart", "slotsGapLen", "slotsGapOwner", "insertCount", "nodeCount", "startStack", "Landroidx/compose/runtime/IntStack;", "endStack", "nodeCountStack", "deferredSlotWrites", "Landroidx/collection/MutableObjectList;", ES6Iterator.VALUE_PROPERTY, "currentGroup", "getCurrentGroup", "()I", "currentGroupEnd", "getCurrentGroupEnd", "isGroupEnd", "", "()Z", "slotsSize", "getSlotsSize", "isNode", "collectingSourceInformation", "getCollectingSourceInformation", "collectingCalledInformation", "getCollectingCalledInformation", "index", "groupKey", "groupObjectKey", "groupSize", "groupAux", "indexInParent", "indexInCurrentGroup", "indexInGroup", "group", "node", LinkHeader.Parameters.Anchor, "parent", "getParent", "closed", "getClosed", "close", "", "normalClose", "reset", "update", "rawUpdate", "appendSlot", "trimTailSlots", PeertubeParsingHelper.COUNT_KEY, "updateAux", "insertAux", "updateToTableMaps", "recordGroupSourceInformation", "sourceInformation", "", "recordGrouplessCallSourceInformationStart", "key", "recordGrouplessCallSourceInformationEnd", "groupSourceInformationFor", "updateNode", "updateParentNode", "set", "slotIndexOfGroupSlotIndex", "clear", "slotIndex", "skip", "slot", "groupIndex", "forEachTailSlot", "block", "Lkotlin/Function2;", "slotsStartIndex", "slotsStartIndex$runtime", "slotsEndIndex", "slotsEndIndex$runtime", "slotsEndAllIndex", "slotsEndAllIndex$runtime", "currentGroupSlotIndex", "getCurrentGroupSlotIndex", "groupSlotIndex", "advanceBy", "amount", "seek", "skipToGroupEnd", "beginInsert", "endInsert", "startGroup", "dataKey", "startNode", "objectKey", "startData", "aux", "endGroup", "ensureStarted", "skipGroup", "removeGroup", "groupSlots", "", "forAllData", "Lkotlin/ParameterName;", "name", "data", "traverseGroupAndChildren", "enter", "Lkotlin/Function1;", "child", "exit", "forAllDataInRememberOrder", "moveGroup", TypedValues.CycleType.S_WAVE_OFFSET, "moveTo", "", "writer", "moveFrom", "removeSourceGroup", "bashCurrentGroup", "moveIntoGroupFrom", "markGroup", "containsGroupMark", "containsAnyGroupMarks", "pendingRecalculateMarks", "Landroidx/compose/runtime/PrioritySet;", "Landroidx/collection/MutableIntList;", "recalculateMarks", "updateContainsMark", "updateContainsMarkNow", "updateContainsMarkNow-XpTMRCE", "(ILandroidx/collection/MutableIntList;)V", "childContainsAnyMarks", "anchorIndex", "toString", "saveCurrentGroupEnd", "restoreCurrentGroupEnd", "fixParentAnchorsFor", "firstChild", "moveGroupGapTo", "moveSlotGapTo", "clearSlotGap", "insertGroups", ContentDisposition.Parameters.Size, "insertSlots", "removeGroups", "start", "len", "sourceInformationOf", "sourceInformationOf$runtime", "tryAnchor", "tryAnchor$runtime", "removeSlots", "updateNodeOfGroup", "updateAnchors", "previousGapStart", "newGapStart", "removeAnchors", "gapStart", "moveAnchors", "originalLocation", "newLocation", "toDebugString", "groupAsString", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "verifyDataAnchors", "verifyDataAnchors$runtime", "verifyParentAnchors", "verifyParentAnchors$runtime", "getSize$runtime", "capacity", "getCapacity", "groupIndexToAddress", "dataIndexToDataAddress", "dataIndex", "address", "updateDataIndex", "nodeIndex", "auxIndex", "dataIndexes", "keys", "dataIndexToDataAnchor", "gapLen", "dataAnchorToDataIndex", "parentIndexToAnchor", "parentAnchorToIndex", "Companion", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SlotWriter {
    private ArrayList<Anchor> anchors;
    private MutableIntObjectMap<MutableIntSet> calledByMap;
    private boolean closed;
    private int currentGroup;
    private int currentGroupEnd;
    private int currentSlot;
    private int currentSlotEnd;
    private MutableIntObjectMap<MutableObjectList<Object>> deferredSlotWrites;
    private int groupGapLen;
    private int groupGapStart;
    private int[] groups;
    private int insertCount;
    private int nodeCount;
    private MutableIntList pendingRecalculateMarks;
    private Object[] slots;
    private int slotsGapLen;
    private int slotsGapOwner;
    private int slotsGapStart;
    private HashMap<Anchor, GroupSourceInformation> sourceInformationMap;
    private final SlotTable table;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private final IntStack startStack = new IntStack();
    private final IntStack endStack = new IntStack();
    private final IntStack nodeCountStack = new IntStack();
    private int parent = -1;

    private final int dataAnchorToDataIndex(int anchor, int gapLen, int capacity) {
        return anchor < 0 ? (capacity - gapLen) + anchor + 1 : anchor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndexToDataAnchor(int index, int gapStart, int gapLen, int capacity) {
        return index > gapStart ? -(((capacity - gapLen) - index) + 1) : index;
    }

    public SlotWriter(SlotTable slotTable) {
        this.table = slotTable;
        this.groups = slotTable.getGroups();
        this.slots = slotTable.getSlots();
        this.anchors = slotTable.getAnchors$runtime();
        this.sourceInformationMap = slotTable.getSourceInformationMap$runtime();
        this.calledByMap = slotTable.getCalledByMap$runtime();
        this.groupGapStart = slotTable.getGroupsSize();
        this.groupGapLen = (this.groups.length / 5) - slotTable.getGroupsSize();
        this.slotsGapStart = slotTable.getSlotsSize();
        this.slotsGapLen = this.slots.length - slotTable.getSlotsSize();
        this.slotsGapOwner = slotTable.getGroupsSize();
        this.currentGroupEnd = slotTable.getGroupsSize();
    }

    /* JADX INFO: renamed from: getTable$runtime, reason: from getter */
    public final SlotTable getTable() {
        return this.table;
    }

    public final int getCurrentGroup() {
        return this.currentGroup;
    }

    public final int getCurrentGroupEnd() {
        return this.currentGroupEnd;
    }

    public final boolean isGroupEnd() {
        return this.currentGroup == this.currentGroupEnd;
    }

    public final int getSlotsSize() {
        return this.slots.length - this.slotsGapLen;
    }

    public final boolean isNode() {
        int i = this.currentGroup;
        return i < this.currentGroupEnd && (this.groups[(groupIndexToAddress(i) * 5) + 1] & 1073741824) != 0;
    }

    public final boolean getCollectingSourceInformation() {
        return this.sourceInformationMap != null;
    }

    public final boolean getCollectingCalledInformation() {
        return this.calledByMap != null;
    }

    public final boolean isNode(int index) {
        return (this.groups[(groupIndexToAddress(index) * 5) + 1] & 1073741824) != 0;
    }

    public final int nodeCount(int index) {
        return this.groups[(groupIndexToAddress(index) * 5) + 1] & RectListKt.Lower26Bits;
    }

    public final int groupKey(int index) {
        return this.groups[groupIndexToAddress(index) * 5];
    }

    public final Object groupObjectKey(int index) {
        int iGroupIndexToAddress = groupIndexToAddress(index);
        int[] iArr = this.groups;
        if ((iArr[(iGroupIndexToAddress * 5) + 1] & 536870912) != 0) {
            return this.slots[SlotTableKt.objectKeyIndex(iArr, iGroupIndexToAddress)];
        }
        return null;
    }

    public final int groupSize(int index) {
        return SlotTableKt.groupSize(this.groups, groupIndexToAddress(index));
    }

    public final Object groupAux(int index) {
        int iGroupIndexToAddress = groupIndexToAddress(index);
        int[] iArr = this.groups;
        return (iArr[(iGroupIndexToAddress * 5) + 1] & 268435456) != 0 ? this.slots[auxIndex(iArr, iGroupIndexToAddress)] : Composer.INSTANCE.getEmpty();
    }

    public final boolean indexInParent(int index) {
        int i = this.parent;
        if (index <= i || index >= this.currentGroupEnd) {
            return i == 0 && index == 0;
        }
        return true;
    }

    public final boolean indexInCurrentGroup(int index) {
        return indexInGroup(index, this.currentGroup);
    }

    public final boolean indexInGroup(int index, int group) {
        int iIndexOf;
        int capacity;
        if (group == this.parent) {
            capacity = this.currentGroupEnd;
        } else if (group > this.startStack.peekOr(0) || (iIndexOf = this.startStack.indexOf(group)) < 0) {
            int iGroupSize = groupSize(group);
            capacity = iGroupSize + group;
        } else {
            capacity = (getCapacity() - this.groupGapLen) - this.endStack.peek(iIndexOf);
        }
        return index > group && index < capacity;
    }

    public final Object node(int index) {
        int iGroupIndexToAddress = groupIndexToAddress(index);
        int[] iArr = this.groups;
        if ((iArr[(iGroupIndexToAddress * 5) + 1] & 1073741824) != 0) {
            return this.slots[dataIndexToDataAddress(nodeIndex(iArr, iGroupIndexToAddress))];
        }
        return null;
    }

    public final Object node(Anchor anchor) {
        return node(anchor.toIndexFor(this));
    }

    public final int getParent() {
        return this.parent;
    }

    public final int parent(int index) {
        return parent(this.groups, index);
    }

    public final int parent(Anchor anchor) {
        if (anchor.getValid()) {
            return parent(this.groups, anchorIndex(anchor));
        }
        return -1;
    }

    public final boolean getClosed() {
        return this.closed;
    }

    public final void close(boolean normalClose) {
        this.closed = true;
        if (normalClose && this.startStack.tos == 0) {
            moveGroupGapTo(getSize$runtime());
            moveSlotGapTo(this.slots.length - this.slotsGapLen, this.groupGapStart);
            clearSlotGap();
            recalculateMarks();
        }
        this.table.close$runtime(this, this.groups, this.groupGapStart, this.slots, this.slotsGapStart, this.anchors, this.sourceInformationMap, this.calledByMap);
    }

    public final void reset() {
        if (!(this.insertCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot reset when inserting");
        }
        recalculateMarks();
        this.currentGroup = 0;
        this.currentGroupEnd = getCapacity() - this.groupGapLen;
        this.currentSlot = 0;
        this.currentSlotEnd = 0;
        this.nodeCount = 0;
    }

    public final Object update(Object value) {
        if (this.insertCount > 0 && this.currentSlot != this.slotsGapStart) {
            MutableIntObjectMap<MutableObjectList<Object>> mutableIntObjectMap = this.deferredSlotWrites;
            DefaultConstructorMarker defaultConstructorMarker = null;
            int i = 1;
            int i2 = 0;
            if (mutableIntObjectMap == null) {
                mutableIntObjectMap = new MutableIntObjectMap<>(i2, i, defaultConstructorMarker);
            }
            this.deferredSlotWrites = mutableIntObjectMap;
            int i3 = this.parent;
            MutableObjectList<Object> mutableObjectList = mutableIntObjectMap.get(i3);
            if (mutableObjectList == null) {
                mutableObjectList = new MutableObjectList<>(i2, i, defaultConstructorMarker);
                mutableIntObjectMap.set(i3, mutableObjectList);
            }
            mutableObjectList.add(value);
            return Composer.INSTANCE.getEmpty();
        }
        return rawUpdate(value);
    }

    private final Object rawUpdate(Object value) {
        Object objSkip = skip();
        set(value);
        return objSkip;
    }

    public final void appendSlot(Anchor anchor, Object value) {
        if (!(this.insertCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Can only append a slot if not current inserting");
        }
        int i = this.currentSlot;
        int i2 = this.currentSlotEnd;
        int iAnchorIndex = anchorIndex(anchor);
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(iAnchorIndex + 1));
        this.currentSlot = iDataIndex;
        this.currentSlotEnd = iDataIndex;
        insertSlots(1, iAnchorIndex);
        if (i >= iDataIndex) {
            i++;
            i2++;
        }
        this.slots[iDataIndex] = value;
        this.currentSlot = i;
        this.currentSlotEnd = i2;
    }

    public final void updateAux(Object value) {
        int iGroupIndexToAddress = groupIndexToAddress(this.currentGroup);
        if (!((this.groups[(iGroupIndexToAddress * 5) + 1] & 268435456) != 0)) {
            ComposerKt.composeImmediateRuntimeError("Updating the data of a group that was not created with a data slot");
        }
        this.slots[dataIndexToDataAddress(auxIndex(this.groups, iGroupIndexToAddress))] = value;
    }

    public final void insertAux(Object value) {
        if (!(this.insertCount >= 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot insert auxiliary data when not inserting");
        }
        int i = this.parent;
        int iGroupIndexToAddress = groupIndexToAddress(i);
        if ((this.groups[(iGroupIndexToAddress * 5) + 1] & 268435456) != 0) {
            ComposerKt.composeImmediateRuntimeError("Group already has auxiliary data");
        }
        insertSlots(1, i);
        int iAuxIndex = auxIndex(this.groups, iGroupIndexToAddress);
        int iDataIndexToDataAddress = dataIndexToDataAddress(iAuxIndex);
        int i2 = this.currentSlot;
        if (i2 > iAuxIndex) {
            int i3 = i2 - iAuxIndex;
            if (!(i3 < 3)) {
                PreconditionsKt.throwIllegalStateException("Moving more than two slot not supported");
            }
            if (i3 > 1) {
                Object[] objArr = this.slots;
                objArr[iDataIndexToDataAddress + 2] = objArr[iDataIndexToDataAddress + 1];
            }
            Object[] objArr2 = this.slots;
            objArr2[iDataIndexToDataAddress + 1] = objArr2[iDataIndexToDataAddress];
        }
        SlotTableKt.addAux(this.groups, iGroupIndexToAddress);
        this.slots[iDataIndexToDataAddress] = value;
        this.currentSlot++;
    }

    public final void updateToTableMaps() {
        this.sourceInformationMap = this.table.getSourceInformationMap$runtime();
        this.calledByMap = this.table.getCalledByMap$runtime();
    }

    public final void recordGroupSourceInformation(String sourceInformation) {
        if (this.insertCount > 0) {
            groupSourceInformationFor(this.parent, sourceInformation);
        }
    }

    public final void recordGrouplessCallSourceInformationStart(int key, String value) {
        if (this.insertCount > 0) {
            MutableIntObjectMap<MutableIntSet> mutableIntObjectMap = this.calledByMap;
            if (mutableIntObjectMap != null) {
                SlotTableKt.add(mutableIntObjectMap, key, groupKey(this.parent));
            }
            GroupSourceInformation groupSourceInformationGroupSourceInformationFor = groupSourceInformationFor(this.parent, null);
            if (groupSourceInformationGroupSourceInformationFor != null) {
                groupSourceInformationGroupSourceInformationFor.startGrouplessCall(key, value, getCurrentGroupSlotIndex());
            }
        }
    }

    public final void recordGrouplessCallSourceInformationEnd() {
        GroupSourceInformation groupSourceInformationGroupSourceInformationFor;
        if (this.insertCount <= 0 || (groupSourceInformationGroupSourceInformationFor = groupSourceInformationFor(this.parent, null)) == null) {
            return;
        }
        groupSourceInformationGroupSourceInformationFor.endGrouplessCall(getCurrentGroupSlotIndex());
    }

    private final GroupSourceInformation groupSourceInformationFor(int parent, String sourceInformation) {
        HashMap<Anchor, GroupSourceInformation> map = this.sourceInformationMap;
        if (map == null) {
            return null;
        }
        HashMap<Anchor, GroupSourceInformation> map2 = map;
        Anchor anchor = anchor(parent);
        GroupSourceInformation groupSourceInformation = map2.get(anchor);
        if (groupSourceInformation == null) {
            groupSourceInformation = new GroupSourceInformation(0, sourceInformation, 0);
            if (sourceInformation == null) {
                int iGroupSize = parent + 1;
                int i = this.currentGroup;
                while (iGroupSize < i) {
                    groupSourceInformation.reportGroup(this, iGroupSize);
                    iGroupSize += SlotTableKt.groupSize(this.groups, iGroupSize);
                }
            }
            map2.put(anchor, groupSourceInformation);
        }
        return groupSourceInformation;
    }

    public final void updateNode(Object value) {
        updateNodeOfGroup(this.currentGroup, value);
    }

    public final void updateNode(Anchor anchor, Object value) {
        updateNodeOfGroup(anchor.toIndexFor(this), value);
    }

    public final void updateParentNode(Object value) {
        updateNodeOfGroup(this.parent, value);
    }

    public final void set(Object value) {
        if (!(this.currentSlot <= this.currentSlotEnd)) {
            ComposerKt.composeImmediateRuntimeError("Writing to an invalid slot");
        }
        this.slots[dataIndexToDataAddress(this.currentSlot - 1)] = value;
    }

    public final Object set(int index, Object value) {
        return set(getCurrentGroup(), index, value);
    }

    public final int slotIndexOfGroupSlotIndex(int group, int index) {
        int iSlotIndex = slotIndex(this.groups, groupIndexToAddress(group));
        int i = iSlotIndex + index;
        if (!(i >= iSlotIndex && i < dataIndex(this.groups, groupIndexToAddress(group + 1)))) {
            ComposerKt.composeImmediateRuntimeError("Write to an invalid slot index " + index + " for group " + group);
        }
        return i;
    }

    public final Object set(int group, int index, Object value) {
        int iDataIndexToDataAddress = dataIndexToDataAddress(slotIndexOfGroupSlotIndex(group, index));
        Object[] objArr = this.slots;
        Object obj = objArr[iDataIndexToDataAddress];
        objArr[iDataIndexToDataAddress] = value;
        return obj;
    }

    public final Object clear(int slotIndex) {
        int iDataIndexToDataAddress = dataIndexToDataAddress(slotIndex);
        Object[] objArr = this.slots;
        Object obj = objArr[iDataIndexToDataAddress];
        objArr[iDataIndexToDataAddress] = Composer.INSTANCE.getEmpty();
        return obj;
    }

    public final Object skip() {
        if (this.insertCount > 0) {
            insertSlots(1, this.parent);
        }
        Object[] objArr = this.slots;
        int i = this.currentSlot;
        this.currentSlot = i + 1;
        return objArr[dataIndexToDataAddress(i)];
    }

    public final Object slot(Anchor anchor, int index) {
        return slot(anchorIndex(anchor), index);
    }

    public final Object slot(int groupIndex, int index) {
        int iSlotIndex = slotIndex(this.groups, groupIndexToAddress(groupIndex));
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(groupIndex + 1));
        int i = index + iSlotIndex;
        if (iSlotIndex > i || i >= iDataIndex) {
            return Composer.INSTANCE.getEmpty();
        }
        return this.slots[dataIndexToDataAddress(i)];
    }

    public final void forEachTailSlot(int groupIndex, int count, Function2<? super Integer, Object, Unit> block) {
        int iSlotsStartIndex$runtime = slotsStartIndex$runtime(groupIndex);
        int iSlotsEndIndex$runtime = slotsEndIndex$runtime(groupIndex);
        for (int iMax = Math.max(iSlotsStartIndex$runtime, iSlotsEndIndex$runtime - count); iMax < iSlotsEndIndex$runtime; iMax++) {
            block.invoke(Integer.valueOf(iMax), this.slots[dataIndexToDataAddress(iMax)]);
        }
    }

    public final int slotsStartIndex$runtime(int groupIndex) {
        return slotIndex(this.groups, groupIndexToAddress(groupIndex));
    }

    public final int slotsEndIndex$runtime(int groupIndex) {
        return dataIndex(this.groups, groupIndexToAddress(groupIndex + 1));
    }

    public final int slotsEndAllIndex$runtime(int groupIndex) {
        return dataIndex(this.groups, groupIndexToAddress(groupIndex + groupSize(groupIndex)));
    }

    private final int getCurrentGroupSlotIndex() {
        return groupSlotIndex(this.parent);
    }

    public final int groupSlotIndex(int group) {
        MutableObjectList<Object> mutableObjectList;
        int iSlotsStartIndex$runtime = this.currentSlot - slotsStartIndex$runtime(group);
        MutableIntObjectMap<MutableObjectList<Object>> mutableIntObjectMap = this.deferredSlotWrites;
        return iSlotsStartIndex$runtime + ((mutableIntObjectMap == null || (mutableObjectList = mutableIntObjectMap.get(group)) == null) ? 0 : mutableObjectList.getSize());
    }

    public final void advanceBy(int amount) {
        if (!(amount >= 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot seek backwards");
        }
        if (!(this.insertCount <= 0)) {
            PreconditionsKt.throwIllegalStateException("Cannot call seek() while inserting");
        }
        if (amount == 0) {
            return;
        }
        int i = this.currentGroup + amount;
        if (!(i >= this.parent && i <= this.currentGroupEnd)) {
            ComposerKt.composeImmediateRuntimeError("Cannot seek outside the current group (" + this.parent + '-' + this.currentGroupEnd + ')');
        }
        this.currentGroup = i;
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(i));
        this.currentSlot = iDataIndex;
        this.currentSlotEnd = iDataIndex;
    }

    public final void seek(Anchor anchor) {
        advanceBy(anchor.toIndexFor(this) - this.currentGroup);
    }

    public final void skipToGroupEnd() {
        int i = this.currentGroupEnd;
        this.currentGroup = i;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(i));
    }

    public final void beginInsert() {
        int i = this.insertCount;
        this.insertCount = i + 1;
        if (i == 0) {
            saveCurrentGroupEnd();
        }
    }

    public final void endInsert() {
        if (!(this.insertCount > 0)) {
            PreconditionsKt.throwIllegalStateException("Unbalanced begin/end insert");
        }
        int i = this.insertCount - 1;
        this.insertCount = i;
        if (i == 0) {
            if (!(this.nodeCountStack.tos == this.startStack.tos)) {
                ComposerKt.composeImmediateRuntimeError("startGroup/endGroup mismatch while inserting");
            }
            restoreCurrentGroupEnd();
        }
    }

    public final void startGroup() {
        if (!(this.insertCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Key must be supplied when inserting");
        }
        startGroup(0, Composer.INSTANCE.getEmpty(), false, Composer.INSTANCE.getEmpty());
    }

    public final void startGroup(int key) {
        startGroup(key, Composer.INSTANCE.getEmpty(), false, Composer.INSTANCE.getEmpty());
    }

    public final void startGroup(int key, Object dataKey) {
        startGroup(key, dataKey, false, Composer.INSTANCE.getEmpty());
    }

    public final void startNode(int key, Object objectKey) {
        startGroup(key, objectKey, true, Composer.INSTANCE.getEmpty());
    }

    public final void startNode(int key, Object objectKey, Object node) {
        startGroup(key, objectKey, true, node);
    }

    public final void startData(int key, Object objectKey, Object aux) {
        startGroup(key, objectKey, false, aux);
    }

    public final void startData(int key, Object aux) {
        startGroup(key, Composer.INSTANCE.getEmpty(), false, aux);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r13v2 */
    private final void startGroup(int key, Object objectKey, boolean isNode, Object aux) {
        int iGroupSize;
        GroupSourceInformation groupSourceInformationSourceInformationOf$runtime;
        int i = this.parent;
        Object[] objArr = this.insertCount > 0;
        this.nodeCountStack.push(this.nodeCount);
        if (objArr != false) {
            int i2 = this.currentGroup;
            int iDataIndex = dataIndex(this.groups, groupIndexToAddress(i2));
            insertGroups(1);
            this.currentSlot = iDataIndex;
            this.currentSlotEnd = iDataIndex;
            int iGroupIndexToAddress = groupIndexToAddress(i2);
            ?? r12 = objectKey != Composer.INSTANCE.getEmpty() ? 1 : 0;
            ?? r13 = (isNode || aux == Composer.INSTANCE.getEmpty()) ? 0 : 1;
            int iDataIndexToDataAnchor = dataIndexToDataAnchor(iDataIndex, this.slotsGapStart, this.slotsGapLen, this.slots.length);
            if (iDataIndexToDataAnchor >= 0 && this.slotsGapOwner < i2) {
                iDataIndexToDataAnchor = -(((this.slots.length - this.slotsGapLen) - iDataIndexToDataAnchor) + 1);
            }
            SlotTableKt.initGroup(this.groups, iGroupIndexToAddress, key, isNode, r12, r13, this.parent, iDataIndexToDataAnchor);
            int i3 = (isNode ? 1 : 0) + r12 + r13;
            if (i3 > 0) {
                insertSlots(i3, i2);
                Object[] objArr2 = this.slots;
                int i4 = this.currentSlot;
                if (isNode) {
                    objArr2[i4] = aux;
                    i4++;
                }
                if (r12 != 0) {
                    objArr2[i4] = objectKey;
                    i4++;
                }
                if (r13 != 0) {
                    objArr2[i4] = aux;
                    i4++;
                }
                this.currentSlot = i4;
            }
            this.nodeCount = 0;
            iGroupSize = i2 + 1;
            this.parent = i2;
            this.currentGroup = iGroupSize;
            if (i >= 0 && (groupSourceInformationSourceInformationOf$runtime = sourceInformationOf$runtime(i)) != null) {
                groupSourceInformationSourceInformationOf$runtime.reportGroup(this, i2);
            }
        } else {
            this.startStack.push(i);
            saveCurrentGroupEnd();
            int i5 = this.currentGroup;
            int iGroupIndexToAddress2 = groupIndexToAddress(i5);
            if (!Intrinsics.areEqual(aux, Composer.INSTANCE.getEmpty())) {
                if (isNode) {
                    updateNode(aux);
                } else {
                    updateAux(aux);
                }
            }
            this.currentSlot = slotIndex(this.groups, iGroupIndexToAddress2);
            this.currentSlotEnd = dataIndex(this.groups, groupIndexToAddress(this.currentGroup + 1));
            int[] iArr = this.groups;
            this.nodeCount = iArr[(iGroupIndexToAddress2 * 5) + 1] & RectListKt.Lower26Bits;
            this.parent = i5;
            this.currentGroup = i5 + 1;
            iGroupSize = i5 + SlotTableKt.groupSize(iArr, iGroupIndexToAddress2);
        }
        this.currentGroupEnd = iGroupSize;
    }

    public final int endGroup() {
        MutableObjectList<Object> mutableObjectList;
        boolean z = this.insertCount > 0;
        int i = this.currentGroup;
        int i2 = this.currentGroupEnd;
        int i3 = this.parent;
        int iGroupIndexToAddress = groupIndexToAddress(i3);
        int i4 = this.nodeCount;
        int i5 = i - i3;
        int i6 = (iGroupIndexToAddress * 5) + 1;
        boolean z2 = (this.groups[i6] & 1073741824) != 0;
        if (!z) {
            if (!(i == i2)) {
                ComposerKt.composeImmediateRuntimeError("Expected to be at the end of a group");
            }
            int iGroupSize = SlotTableKt.groupSize(this.groups, iGroupIndexToAddress);
            int[] iArr = this.groups;
            int i7 = iArr[i6] & RectListKt.Lower26Bits;
            SlotTableKt.updateGroupSize(iArr, iGroupIndexToAddress, i5);
            SlotTableKt.updateNodeCount(this.groups, iGroupIndexToAddress, i4);
            int iPop = this.startStack.pop();
            restoreCurrentGroupEnd();
            this.parent = iPop;
            int iParent = parent(this.groups, i3);
            int iPop2 = this.nodeCountStack.pop();
            this.nodeCount = iPop2;
            if (iParent == iPop) {
                this.nodeCount = iPop2 + (z2 ? 0 : i4 - i7);
                return i4;
            }
            int i8 = i5 - iGroupSize;
            int i9 = z2 ? 0 : i4 - i7;
            if (i8 != 0 || i9 != 0) {
                while (iParent != 0 && iParent != iPop && (i9 != 0 || i8 != 0)) {
                    int iGroupIndexToAddress2 = groupIndexToAddress(iParent);
                    if (i8 != 0) {
                        SlotTableKt.updateGroupSize(this.groups, iGroupIndexToAddress2, SlotTableKt.groupSize(this.groups, iGroupIndexToAddress2) + i8);
                    }
                    if (i9 != 0) {
                        int[] iArr2 = this.groups;
                        SlotTableKt.updateNodeCount(iArr2, iGroupIndexToAddress2, (iArr2[(iGroupIndexToAddress2 * 5) + 1] & RectListKt.Lower26Bits) + i9);
                    }
                    int[] iArr3 = this.groups;
                    if ((iArr3[(iGroupIndexToAddress2 * 5) + 1] & 1073741824) != 0) {
                        i9 = 0;
                    }
                    iParent = parent(iArr3, iParent);
                }
            }
            this.nodeCount += i9;
            return i4;
        }
        MutableIntObjectMap<MutableObjectList<Object>> mutableIntObjectMap = this.deferredSlotWrites;
        if (mutableIntObjectMap != null && (mutableObjectList = mutableIntObjectMap.get(i3)) != null) {
            MutableObjectList<Object> mutableObjectList2 = mutableObjectList;
            Object[] objArr = mutableObjectList2.content;
            int i10 = mutableObjectList2._size;
            for (int i11 = 0; i11 < i10; i11++) {
                rawUpdate(objArr[i11]);
            }
            mutableIntObjectMap.remove(i3);
        }
        SlotTableKt.updateGroupSize(this.groups, iGroupIndexToAddress, i5);
        SlotTableKt.updateNodeCount(this.groups, iGroupIndexToAddress, i4);
        this.nodeCount = this.nodeCountStack.pop() + (z2 ? 1 : i4);
        int iParent2 = parent(this.groups, i3);
        this.parent = iParent2;
        int size$runtime = iParent2 < 0 ? getSize$runtime() : groupIndexToAddress(iParent2 + 1);
        int iDataIndex = size$runtime >= 0 ? dataIndex(this.groups, size$runtime) : 0;
        this.currentSlot = iDataIndex;
        this.currentSlotEnd = iDataIndex;
        return i4;
    }

    public final void ensureStarted(int index) {
        if (!(this.insertCount <= 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot call ensureStarted() while inserting");
        }
        int i = this.parent;
        if (i != index) {
            if (!(index >= i && index < this.currentGroupEnd)) {
                ComposerKt.composeImmediateRuntimeError("Started group at " + index + " must be a subgroup of the group at " + i);
            }
            int i2 = this.currentGroup;
            int i3 = this.currentSlot;
            int i4 = this.currentSlotEnd;
            this.currentGroup = index;
            startGroup();
            this.currentGroup = i2;
            this.currentSlot = i3;
            this.currentSlotEnd = i4;
        }
    }

    public final void ensureStarted(Anchor anchor) {
        ensureStarted(anchor.toIndexFor(this));
    }

    public final int skipGroup() {
        int iGroupIndexToAddress = groupIndexToAddress(this.currentGroup);
        int iGroupSize = this.currentGroup + SlotTableKt.groupSize(this.groups, iGroupIndexToAddress);
        this.currentGroup = iGroupSize;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(iGroupSize));
        int i = this.groups[(iGroupIndexToAddress * 5) + 1];
        if ((1073741824 & i) != 0) {
            return 1;
        }
        return i & RectListKt.Lower26Bits;
    }

    public final boolean removeGroup() {
        Anchor anchorTryAnchor$runtime;
        if (!(this.insertCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot remove group while inserting");
        }
        int i = this.currentGroup;
        int i2 = this.currentSlot;
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(i));
        int iSkipGroup = skipGroup();
        GroupSourceInformation groupSourceInformationSourceInformationOf$runtime = sourceInformationOf$runtime(this.parent);
        if (groupSourceInformationSourceInformationOf$runtime != null && (anchorTryAnchor$runtime = tryAnchor$runtime(i)) != null) {
            groupSourceInformationSourceInformationOf$runtime.removeAnchor(anchorTryAnchor$runtime);
        }
        MutableIntList mutableIntList = this.pendingRecalculateMarks;
        if (mutableIntList != null) {
            while (PrioritySet.m3421isNotEmptyimpl(mutableIntList) && PrioritySet.m3422peekimpl(mutableIntList) >= i) {
                PrioritySet.m3423takeMaximpl(mutableIntList);
            }
        }
        boolean zRemoveGroups = removeGroups(i, this.currentGroup - i);
        removeSlots(iDataIndex, this.currentSlot - iDataIndex, i - 1);
        this.currentGroup = i;
        this.currentSlot = i2;
        this.nodeCount -= iSkipGroup;
        return zRemoveGroups;
    }

    public final Iterator<Object> groupSlots() {
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(this.currentGroup));
        int[] iArr = this.groups;
        int i = this.currentGroup;
        return new AnonymousClass1(iDataIndex, dataIndex(iArr, groupIndexToAddress(i + groupSize(i))), this);
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.SlotWriter$groupSlots$1, reason: invalid class name */
    /* JADX INFO: compiled from: SlotTable.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0010(\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\t\u0010\t\u001a\u00020\nH\u0096\u0002J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u0096\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"androidx/compose/runtime/SlotWriter$groupSlots$1", "", "", "current", "", "getCurrent", "()I", "setCurrent", "(I)V", "hasNext", "", "next", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AnonymousClass1 implements Iterator<Object>, KMappedMarker {
        final /* synthetic */ int $end;
        private int current;
        final /* synthetic */ SlotWriter this$0;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        AnonymousClass1(int i, int i2, SlotWriter slotWriter) {
            this.$end = i2;
            this.this$0 = slotWriter;
            this.current = i;
        }

        public final int getCurrent() {
            return this.current;
        }

        public final void setCurrent(int i) {
            this.current = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.current < this.$end;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (!hasNext()) {
                return null;
            }
            Object[] objArr = this.this$0.slots;
            SlotWriter slotWriter = this.this$0;
            int i = this.current;
            this.current = i + 1;
            return objArr[slotWriter.dataIndexToDataAddress(i)];
        }
    }

    public final void forAllData(int group, Function2<? super Integer, Object, Unit> block) {
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(getCurrentGroup() + groupSize(getCurrentGroup())));
        for (int iDataIndex2 = dataIndex(this.groups, groupIndexToAddress(group)); iDataIndex2 < iDataIndex; iDataIndex2++) {
            block.invoke(Integer.valueOf(iDataIndex2), this.slots[dataIndexToDataAddress(iDataIndex2)]);
        }
    }

    public final void traverseGroupAndChildren(int group, Function1<? super Integer, Unit> enter, Function1<? super Integer, Unit> exit) {
        int iParent = parent(group);
        int size$runtime = getSize$runtime();
        int iGroupSize = groupSize(group) + group;
        int i = group;
        while (i < iGroupSize) {
            enter.invoke(Integer.valueOf(i));
            int i2 = i + 1;
            int iParent2 = i2 < size$runtime ? parent(i2) : -1;
            if (iParent2 != i) {
                while (true) {
                    exit.invoke(Integer.valueOf(i));
                    if (i == group || iParent == iParent2) {
                        break;
                    }
                    i = iParent;
                    iParent = parent(iParent);
                }
            }
            i = i2;
            iParent = iParent2;
        }
    }

    public final void moveGroup(int offset) {
        int[] iArr;
        boolean z = true;
        if (!(this.insertCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot move a group while inserting");
        }
        if (!(offset >= 0)) {
            ComposerKt.composeImmediateRuntimeError("Parameter offset is out of bounds");
        }
        if (offset == 0) {
            return;
        }
        int i = this.currentGroup;
        int i2 = this.parent;
        int i3 = this.currentGroupEnd;
        int i4 = offset;
        int iGroupSize = i;
        while (true) {
            iArr = this.groups;
            if (i4 <= 0) {
                break;
            }
            iGroupSize += SlotTableKt.groupSize(iArr, groupIndexToAddress(iGroupSize));
            if (!(iGroupSize <= i3)) {
                ComposerKt.composeImmediateRuntimeError("Parameter offset is out of bounds");
            }
            i4--;
        }
        int iGroupSize2 = SlotTableKt.groupSize(iArr, groupIndexToAddress(iGroupSize));
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(this.currentGroup));
        int iDataIndex2 = dataIndex(this.groups, groupIndexToAddress(iGroupSize));
        int i5 = iGroupSize + iGroupSize2;
        int iDataIndex3 = dataIndex(this.groups, groupIndexToAddress(i5));
        int i6 = iDataIndex3 - iDataIndex2;
        insertSlots(i6, Math.max(this.currentGroup - 1, 0));
        insertGroups(iGroupSize2);
        int[] iArr2 = this.groups;
        int iGroupIndexToAddress = groupIndexToAddress(i5) * 5;
        ArraysKt.copyInto(iArr2, iArr2, groupIndexToAddress(i) * 5, iGroupIndexToAddress, (iGroupSize2 * 5) + iGroupIndexToAddress);
        if (i6 > 0) {
            Object[] objArr = this.slots;
            int iDataIndexToDataAddress = dataIndexToDataAddress(iDataIndex2 + i6);
            System.arraycopy(objArr, iDataIndexToDataAddress, objArr, iDataIndex, dataIndexToDataAddress(iDataIndex3 + i6) - iDataIndexToDataAddress);
        }
        int i7 = iDataIndex2 + i6;
        int i8 = i7 - iDataIndex;
        int i9 = this.slotsGapStart;
        int i10 = this.slotsGapLen;
        int length = this.slots.length;
        int i11 = this.slotsGapOwner;
        int i12 = i + iGroupSize2;
        int i13 = i;
        while (i13 < i12) {
            boolean z2 = z;
            int iGroupIndexToAddress2 = groupIndexToAddress(i13);
            int i14 = i13;
            int i15 = i8;
            updateDataIndex(iArr2, iGroupIndexToAddress2, dataIndexToDataAnchor(dataIndex(iArr2, iGroupIndexToAddress2) - i8, i11 < iGroupIndexToAddress2 ? 0 : i9, i10, length));
            i13 = i14 + 1;
            z = z2;
            i8 = i15;
        }
        moveAnchors(i5, i, iGroupSize2);
        if (removeGroups(i5, iGroupSize2)) {
            ComposerKt.composeImmediateRuntimeError("Unexpectedly removed anchors");
        }
        fixParentAnchorsFor(i2, this.currentGroupEnd, i);
        if (i6 > 0) {
            removeSlots(i7, i6, i5 - 1);
        }
    }

    /* JADX INFO: compiled from: SlotTable.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J@\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\rH\u0002¨\u0006\u0010"}, d2 = {"Landroidx/compose/runtime/SlotWriter$Companion;", "", "<init>", "()V", "moveGroup", "", "Landroidx/compose/runtime/Anchor;", "fromWriter", "Landroidx/compose/runtime/SlotWriter;", "fromIndex", "", "toWriter", "updateFromCursor", "", "updateToCursor", "removeSourceGroup", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        static /* synthetic */ List moveGroup$default(Companion companion, SlotWriter slotWriter, int i, SlotWriter slotWriter2, boolean z, boolean z2, boolean z3, int i2, Object obj) {
            if ((i2 & 32) != 0) {
                z3 = true;
            }
            return companion.moveGroup(slotWriter, i, slotWriter2, z, z2, z3);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public final List<Anchor> moveGroup(SlotWriter fromWriter, int fromIndex, SlotWriter toWriter, boolean updateFromCursor, boolean updateToCursor, boolean removeSourceGroup) {
            boolean zRemoveGroups;
            ArrayList arrayListEmptyList;
            int iGroupSize = fromWriter.groupSize(fromIndex);
            int i = fromIndex + iGroupSize;
            int iDataIndex = fromWriter.dataIndex(fromIndex);
            int iDataIndex2 = fromWriter.dataIndex(i);
            int i2 = iDataIndex2 - iDataIndex;
            boolean zContainsAnyGroupMarks = fromWriter.containsAnyGroupMarks(fromIndex);
            toWriter.insertGroups(iGroupSize);
            toWriter.insertSlots(i2, toWriter.getCurrentGroup());
            if (fromWriter.groupGapStart < i) {
                fromWriter.moveGroupGapTo(i);
            }
            if (fromWriter.slotsGapStart < iDataIndex2) {
                fromWriter.moveSlotGapTo(iDataIndex2, i);
            }
            int[] iArr = toWriter.groups;
            int currentGroup = toWriter.getCurrentGroup();
            int i3 = currentGroup * 5;
            ArraysKt.copyInto(fromWriter.groups, iArr, i3, fromIndex * 5, i * 5);
            Object[] objArr = toWriter.slots;
            int i4 = toWriter.currentSlot;
            System.arraycopy(fromWriter.slots, iDataIndex, objArr, i4, i2);
            int parent = toWriter.getParent();
            iArr[i3 + 2] = parent;
            int i5 = currentGroup - fromIndex;
            int i6 = currentGroup + iGroupSize;
            int iDataIndex3 = i4 - toWriter.dataIndex(iArr, currentGroup);
            int i7 = toWriter.slotsGapOwner;
            int i8 = toWriter.slotsGapLen;
            int length = objArr.length;
            int i9 = i7;
            int i10 = currentGroup;
            while (true) {
                zRemoveGroups = false;
                if (i10 >= i6) {
                    break;
                }
                if (i10 != currentGroup) {
                    int i11 = (i10 * 5) + 2;
                    iArr[i11] = iArr[i11] + i5;
                }
                int[] iArr2 = iArr;
                int i12 = currentGroup;
                iArr2[(i10 * 5) + 4] = toWriter.dataIndexToDataAnchor(toWriter.dataIndex(iArr, i10) + iDataIndex3, i9 >= i10 ? toWriter.slotsGapStart : 0, i8, length);
                if (i10 == i9) {
                    i9++;
                }
                i10++;
                currentGroup = i12;
                iArr = iArr2;
            }
            int[] iArr3 = iArr;
            toWriter.slotsGapOwner = i9;
            int iLocationOf = SlotTableKt.locationOf(fromWriter.anchors, fromIndex, fromWriter.getSize$runtime());
            int iLocationOf2 = SlotTableKt.locationOf(fromWriter.anchors, i, fromWriter.getSize$runtime());
            if (iLocationOf < iLocationOf2) {
                ArrayList arrayList = fromWriter.anchors;
                ArrayList arrayList2 = new ArrayList(iLocationOf2 - iLocationOf);
                for (int i13 = iLocationOf; i13 < iLocationOf2; i13++) {
                    Anchor anchor = (Anchor) arrayList.get(i13);
                    anchor.setLocation$runtime(anchor.getLocation() + i5);
                    arrayList2.add(anchor);
                }
                toWriter.anchors.addAll(SlotTableKt.locationOf(toWriter.anchors, toWriter.getCurrentGroup(), toWriter.getSize$runtime()), arrayList2);
                arrayList.subList(iLocationOf, iLocationOf2).clear();
                arrayListEmptyList = arrayList2;
            } else {
                arrayListEmptyList = CollectionsKt.emptyList();
            }
            List<Anchor> list = arrayListEmptyList;
            if (!list.isEmpty()) {
                HashMap map = fromWriter.sourceInformationMap;
                HashMap map2 = toWriter.sourceInformationMap;
                if (map != null && map2 != null) {
                    int size = list.size();
                    for (int i14 = 0; i14 < size; i14++) {
                        Anchor anchor2 = arrayListEmptyList.get(i14);
                        GroupSourceInformation groupSourceInformation = (GroupSourceInformation) map.get(anchor2);
                        if (groupSourceInformation != null) {
                            map.remove(anchor2);
                            map2.put(anchor2, groupSourceInformation);
                        }
                    }
                }
            }
            int parent2 = toWriter.getParent();
            GroupSourceInformation groupSourceInformationSourceInformationOf$runtime = toWriter.sourceInformationOf$runtime(parent);
            if (groupSourceInformationSourceInformationOf$runtime != null) {
                int iGroupSize2 = parent2 + 1;
                int currentGroup2 = toWriter.getCurrentGroup();
                int i15 = -1;
                while (iGroupSize2 < currentGroup2) {
                    i15 = iGroupSize2;
                    iGroupSize2 = SlotTableKt.groupSize(toWriter.groups, iGroupSize2) + iGroupSize2;
                }
                groupSourceInformationSourceInformationOf$runtime.addGroupAfter(toWriter, i15, currentGroup2);
            }
            int iParent = fromWriter.parent(fromIndex);
            if (removeSourceGroup) {
                if (!updateFromCursor) {
                    zRemoveGroups = fromWriter.removeGroups(fromIndex, iGroupSize);
                    fromWriter.removeSlots(iDataIndex, i2, fromIndex - 1);
                } else {
                    Object[] objArr2 = iParent >= 0 ? 1 : 0;
                    if (objArr2 != 0) {
                        fromWriter.startGroup();
                        fromWriter.advanceBy(iParent - fromWriter.getCurrentGroup());
                        fromWriter.startGroup();
                    }
                    fromWriter.advanceBy(fromIndex - fromWriter.getCurrentGroup());
                    boolean zRemoveGroup = fromWriter.removeGroup();
                    if (objArr2 != 0) {
                        fromWriter.skipToGroupEnd();
                        fromWriter.endGroup();
                        fromWriter.skipToGroupEnd();
                        fromWriter.endGroup();
                    }
                    zRemoveGroups = zRemoveGroup;
                }
            }
            if (zRemoveGroups) {
                ComposerKt.composeImmediateRuntimeError("Unexpectedly removed anchors");
            }
            int i16 = toWriter.nodeCount;
            int i17 = iArr3[i3 + 1];
            toWriter.nodeCount = i16 + ((1073741824 & i17) == 0 ? i17 & RectListKt.Lower26Bits : 1);
            if (updateToCursor) {
                toWriter.currentGroup = i6;
                toWriter.currentSlot = i4 + i2;
            }
            if (zContainsAnyGroupMarks) {
                toWriter.updateContainsMark(parent);
            }
            return arrayListEmptyList;
        }
    }

    public final List<Anchor> moveTo(Anchor anchor, int offset, SlotWriter writer) {
        if (!(writer.insertCount > 0)) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        if (!(this.insertCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        if (!anchor.getValid()) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        int iAnchorIndex = anchorIndex(anchor) + offset;
        int i = this.currentGroup;
        if (!(i <= iAnchorIndex && iAnchorIndex < this.currentGroupEnd)) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        int iParent = parent(iAnchorIndex);
        int iGroupSize = groupSize(iAnchorIndex);
        int iNodeCount = isNode(iAnchorIndex) ? 1 : nodeCount(iAnchorIndex);
        List<Anchor> listMoveGroup$default = Companion.moveGroup$default(INSTANCE, this, iAnchorIndex, writer, false, false, false, 32, null);
        updateContainsMark(iParent);
        boolean z = iNodeCount > 0;
        while (iParent >= i) {
            int iGroupIndexToAddress = groupIndexToAddress(iParent);
            int[] iArr = this.groups;
            SlotTableKt.updateGroupSize(iArr, iGroupIndexToAddress, SlotTableKt.groupSize(iArr, iGroupIndexToAddress) - iGroupSize);
            if (z) {
                int[] iArr2 = this.groups;
                int i2 = iArr2[(iGroupIndexToAddress * 5) + 1];
                if ((1073741824 & i2) != 0) {
                    z = false;
                } else {
                    SlotTableKt.updateNodeCount(iArr2, iGroupIndexToAddress, (i2 & RectListKt.Lower26Bits) - iNodeCount);
                }
            }
            iParent = parent(iParent);
        }
        if (z) {
            if (!(this.nodeCount >= iNodeCount)) {
                ComposerKt.composeImmediateRuntimeError("Check failed");
            }
            this.nodeCount -= iNodeCount;
        }
        return listMoveGroup$default;
    }

    public static /* synthetic */ List moveFrom$default(SlotWriter slotWriter, SlotTable slotTable, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        return slotWriter.moveFrom(slotTable, i, z);
    }

    public final List<Anchor> moveFrom(SlotTable table, int index, boolean removeSourceGroup) {
        boolean z = false;
        if (!(this.insertCount > 0 ? true : z)) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        if (index == 0 && this.currentGroup == 0 && this.table.getGroupsSize() == 0 && SlotTableKt.groupSize(table.getGroups(), index) == table.getGroupsSize()) {
            int[] iArr = this.groups;
            Object[] objArr = this.slots;
            ArrayList<Anchor> arrayList = this.anchors;
            HashMap<Anchor, GroupSourceInformation> map = this.sourceInformationMap;
            MutableIntObjectMap<MutableIntSet> mutableIntObjectMap = this.calledByMap;
            int[] groups = table.getGroups();
            int groupsSize = table.getGroupsSize();
            Object[] slots = table.getSlots();
            int slotsSize = table.getSlotsSize();
            HashMap<Anchor, GroupSourceInformation> sourceInformationMap$runtime = table.getSourceInformationMap$runtime();
            MutableIntObjectMap<MutableIntSet> calledByMap$runtime = table.getCalledByMap$runtime();
            this.groups = groups;
            this.slots = slots;
            this.anchors = table.getAnchors$runtime();
            this.groupGapStart = groupsSize;
            this.groupGapLen = (groups.length / 5) - groupsSize;
            this.slotsGapStart = slotsSize;
            this.slotsGapLen = slots.length - slotsSize;
            this.slotsGapOwner = groupsSize;
            this.sourceInformationMap = sourceInformationMap$runtime;
            this.calledByMap = calledByMap$runtime;
            table.setTo$runtime(iArr, 0, objArr, 0, arrayList, map, mutableIntObjectMap);
            return this.anchors;
        }
        SlotWriter slotWriterOpenWriter = table.openWriter();
        try {
            return INSTANCE.moveGroup(slotWriterOpenWriter, index, this, true, true, removeSourceGroup);
        } finally {
            slotWriterOpenWriter.close(z);
        }
    }

    public final void bashCurrentGroup() {
        SlotTableKt.updateGroupKey(this.groups, this.currentGroup, -3);
    }

    public final List<Anchor> moveIntoGroupFrom(int offset, SlotTable table, int index) {
        if (!(this.insertCount <= 0 && groupSize(this.currentGroup + offset) == 1)) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        int i = this.currentGroup;
        int i2 = this.currentSlot;
        int i3 = this.currentSlotEnd;
        advanceBy(offset);
        startGroup();
        beginInsert();
        SlotWriter slotWriterOpenWriter = table.openWriter();
        try {
            List<Anchor> listMoveGroup$default = Companion.moveGroup$default(INSTANCE, slotWriterOpenWriter, index, this, false, true, false, 32, null);
            slotWriterOpenWriter.close(true);
            endInsert();
            endGroup();
            this.currentGroup = i;
            this.currentSlot = i2;
            this.currentSlotEnd = i3;
            return listMoveGroup$default;
        } catch (Throwable th) {
            slotWriterOpenWriter.close(false);
            throw th;
        }
    }

    public static /* synthetic */ Anchor anchor$default(SlotWriter slotWriter, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = slotWriter.currentGroup;
        }
        return slotWriter.anchor(i);
    }

    public final Anchor anchor(int index) {
        ArrayList<Anchor> arrayList = this.anchors;
        int iSearch = SlotTableKt.search(arrayList, index, getSize$runtime());
        if (iSearch < 0) {
            if (index > this.groupGapStart) {
                index = -(getSize$runtime() - index);
            }
            Anchor anchor = new Anchor(index);
            arrayList.add(-(iSearch + 1), anchor);
            return anchor;
        }
        return arrayList.get(iSearch);
    }

    public static /* synthetic */ void markGroup$default(SlotWriter slotWriter, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = slotWriter.parent;
        }
        slotWriter.markGroup(i);
    }

    public final void markGroup(int group) {
        int iGroupIndexToAddress = groupIndexToAddress(group);
        int[] iArr = this.groups;
        int i = (iGroupIndexToAddress * 5) + 1;
        if ((iArr[i] & C.BUFFER_FLAG_FIRST_SAMPLE) != 0) {
            return;
        }
        SlotTableKt.updateMark(iArr, iGroupIndexToAddress, true);
        if ((this.groups[i] & 67108864) != 0) {
            return;
        }
        updateContainsMark(parent(group));
    }

    private final boolean containsGroupMark(int group) {
        return group >= 0 && (this.groups[(groupIndexToAddress(group) * 5) + 1] & 67108864) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean containsAnyGroupMarks(int group) {
        return group >= 0 && (this.groups[(groupIndexToAddress(group) * 5) + 1] & 201326592) != 0;
    }

    private final void recalculateMarks() {
        MutableIntList mutableIntList = this.pendingRecalculateMarks;
        if (mutableIntList != null) {
            while (PrioritySet.m3421isNotEmptyimpl(mutableIntList)) {
                m3435updateContainsMarkNowXpTMRCE(PrioritySet.m3423takeMaximpl(mutableIntList), mutableIntList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateContainsMark(int group) {
        if (group >= 0) {
            MutableIntList mutableIntListM3416constructorimpl$default = this.pendingRecalculateMarks;
            if (mutableIntListM3416constructorimpl$default == null) {
                mutableIntListM3416constructorimpl$default = PrioritySet.m3416constructorimpl$default(null, 1, null);
                this.pendingRecalculateMarks = mutableIntListM3416constructorimpl$default;
            }
            PrioritySet.m3413addimpl(mutableIntListM3416constructorimpl$default, group);
        }
    }

    /* JADX INFO: renamed from: updateContainsMarkNow-XpTMRCE, reason: not valid java name */
    private final void m3435updateContainsMarkNowXpTMRCE(int group, MutableIntList set) {
        int iGroupIndexToAddress = groupIndexToAddress(group);
        boolean zChildContainsAnyMarks = childContainsAnyMarks(group);
        int[] iArr = this.groups;
        if (((iArr[(iGroupIndexToAddress * 5) + 1] & 67108864) != 0) != zChildContainsAnyMarks) {
            SlotTableKt.updateContainsMark(iArr, iGroupIndexToAddress, zChildContainsAnyMarks);
            int iParent = parent(group);
            if (iParent >= 0) {
                PrioritySet.m3413addimpl(set, iParent);
            }
        }
    }

    private final boolean childContainsAnyMarks(int group) {
        int iGroupSize = group + 1;
        int iGroupSize2 = group + groupSize(group);
        while (iGroupSize < iGroupSize2) {
            if ((this.groups[(groupIndexToAddress(iGroupSize) * 5) + 1] & 201326592) != 0) {
                return true;
            }
            iGroupSize += groupSize(iGroupSize);
        }
        return false;
    }

    public final int anchorIndex(Anchor anchor) {
        int location$runtime = anchor.getLocation();
        return location$runtime < 0 ? getSize$runtime() + location$runtime : location$runtime;
    }

    public String toString() {
        return "SlotWriter(current = " + this.currentGroup + " end=" + this.currentGroupEnd + " size = " + getSize$runtime() + " gap=" + this.groupGapStart + '-' + (this.groupGapStart + this.groupGapLen) + ')';
    }

    private final void saveCurrentGroupEnd() {
        this.endStack.push((getCapacity() - this.groupGapLen) - this.currentGroupEnd);
    }

    private final int restoreCurrentGroupEnd() {
        int capacity = (getCapacity() - this.groupGapLen) - this.endStack.pop();
        this.currentGroupEnd = capacity;
        return capacity;
    }

    private final void fixParentAnchorsFor(int parent, int endGroup, int firstChild) {
        int iParentIndexToAnchor = parentIndexToAnchor(parent, this.groupGapStart);
        while (firstChild < endGroup) {
            this.groups[(groupIndexToAddress(firstChild) * 5) + 2] = iParentIndexToAnchor;
            int iGroupSize = SlotTableKt.groupSize(this.groups, groupIndexToAddress(firstChild)) + firstChild;
            fixParentAnchorsFor(firstChild, iGroupSize, firstChild + 1);
            firstChild = iGroupSize;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveGroupGapTo(int index) {
        int i = this.groupGapLen;
        int i2 = this.groupGapStart;
        if (i2 != index) {
            if (!this.anchors.isEmpty()) {
                updateAnchors(i2, index);
            }
            if (i > 0) {
                int[] iArr = this.groups;
                int i3 = index * 5;
                int i4 = i * 5;
                int i5 = i2 * 5;
                if (index < i2) {
                    ArraysKt.copyInto(iArr, iArr, i4 + i3, i3, i5);
                } else {
                    ArraysKt.copyInto(iArr, iArr, i5, i5 + i4, i3 + i4);
                }
            }
            if (index < i2) {
                i2 = index + i;
            }
            int capacity = getCapacity();
            if (!(i2 < capacity)) {
                ComposerKt.composeImmediateRuntimeError("Check failed");
            }
            while (i2 < capacity) {
                int i6 = (i2 * 5) + 2;
                int i7 = this.groups[i6];
                int iParentIndexToAnchor = parentIndexToAnchor(parentAnchorToIndex(i7), index);
                if (iParentIndexToAnchor != i7) {
                    this.groups[i6] = iParentIndexToAnchor;
                }
                i2++;
                if (i2 == index) {
                    i2 += i;
                }
            }
        }
        this.groupGapStart = index;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveSlotGapTo(int index, int group) {
        int i = this.slotsGapLen;
        int i2 = this.slotsGapStart;
        int i3 = this.slotsGapOwner;
        if (i2 != index) {
            Object[] objArr = this.slots;
            if (index < i2) {
                System.arraycopy(objArr, index, objArr, index + i, i2 - index);
            } else {
                int i4 = i2 + i;
                System.arraycopy(objArr, i4, objArr, i2, (index + i) - i4);
            }
        }
        int iMin = Math.min(group + 1, getSize$runtime());
        if (i3 != iMin) {
            int length = this.slots.length - i;
            if (iMin < i3) {
                int iGroupIndexToAddress = groupIndexToAddress(iMin);
                int iGroupIndexToAddress2 = groupIndexToAddress(i3);
                int i5 = this.groupGapStart;
                while (iGroupIndexToAddress < iGroupIndexToAddress2) {
                    int i6 = (iGroupIndexToAddress * 5) + 4;
                    int i7 = this.groups[i6];
                    if (!(i7 >= 0)) {
                        ComposerKt.composeImmediateRuntimeError("Unexpected anchor value, expected a positive anchor");
                    }
                    this.groups[i6] = -((length - i7) + 1);
                    iGroupIndexToAddress++;
                    if (iGroupIndexToAddress == i5) {
                        iGroupIndexToAddress += this.groupGapLen;
                    }
                }
            } else {
                int iGroupIndexToAddress3 = groupIndexToAddress(i3);
                int iGroupIndexToAddress4 = groupIndexToAddress(iMin);
                while (iGroupIndexToAddress3 < iGroupIndexToAddress4) {
                    int i8 = (iGroupIndexToAddress3 * 5) + 4;
                    int i9 = this.groups[i8];
                    if (!(i9 < 0)) {
                        ComposerKt.composeImmediateRuntimeError("Unexpected anchor value, expected a negative anchor");
                    }
                    this.groups[i8] = i9 + length + 1;
                    iGroupIndexToAddress3++;
                    if (iGroupIndexToAddress3 == this.groupGapStart) {
                        iGroupIndexToAddress3 += this.groupGapLen;
                    }
                }
            }
            this.slotsGapOwner = iMin;
        }
        this.slotsGapStart = index;
    }

    private final void clearSlotGap() {
        int i = this.slotsGapStart;
        ArraysKt.fill(this.slots, (Object) null, i, this.slotsGapLen + i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertGroups(int size) {
        if (size > 0) {
            int i = this.currentGroup;
            moveGroupGapTo(i);
            int i2 = this.groupGapStart;
            int i3 = this.groupGapLen;
            int[] iArr = this.groups;
            int length = iArr.length / 5;
            int i4 = length - i3;
            if (i3 < size) {
                int iMax = Math.max(Math.max(length * 2, i4 + size), 32);
                int[] iArr2 = new int[iMax * 5];
                int i5 = iMax - i4;
                ArraysKt.copyInto(iArr, iArr2, 0, 0, i2 * 5);
                ArraysKt.copyInto(iArr, iArr2, (i2 + i5) * 5, (i3 + i2) * 5, length * 5);
                this.groups = iArr2;
                i3 = i5;
            }
            int i6 = this.currentGroupEnd;
            if (i6 >= i2) {
                this.currentGroupEnd = i6 + size;
            }
            int i7 = i2 + size;
            this.groupGapStart = i7;
            this.groupGapLen = i3 - size;
            int iDataIndexToDataAnchor = dataIndexToDataAnchor(i4 > 0 ? dataIndex(i + size) : 0, this.slotsGapOwner >= i2 ? this.slotsGapStart : 0, this.slotsGapLen, this.slots.length);
            for (int i8 = i2; i8 < i7; i8++) {
                this.groups[(i8 * 5) + 4] = iDataIndexToDataAnchor;
            }
            int i9 = this.slotsGapOwner;
            if (i9 >= i2) {
                this.slotsGapOwner = i9 + size;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertSlots(int size, int group) {
        if (size > 0) {
            moveSlotGapTo(this.currentSlot, group);
            int i = this.slotsGapStart;
            int i2 = this.slotsGapLen;
            if (i2 < size) {
                Object[] objArr = this.slots;
                int length = objArr.length;
                int i3 = length - i2;
                int iMax = Math.max(Math.max(length * 2, i3 + size), 32);
                Object[] objArr2 = new Object[iMax];
                for (int i4 = 0; i4 < iMax; i4++) {
                    objArr2[i4] = null;
                }
                int i5 = iMax - i3;
                int i6 = i2 + i;
                System.arraycopy(objArr, 0, objArr2, 0, i);
                System.arraycopy(objArr, i6, objArr2, i + i5, length - i6);
                this.slots = objArr2;
                i2 = i5;
            }
            int i7 = this.currentSlotEnd;
            if (i7 >= i) {
                this.currentSlotEnd = i7 + size;
            }
            this.slotsGapStart = i + size;
            this.slotsGapLen = i2 - size;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean removeGroups(int start, int len) {
        boolean zRemoveAnchors = false;
        if (len > 0) {
            ArrayList<Anchor> arrayList = this.anchors;
            moveGroupGapTo(start);
            zRemoveAnchors = arrayList.isEmpty() ? false : removeAnchors(start, len, this.sourceInformationMap);
            this.groupGapStart = start;
            this.groupGapLen += len;
            int i = this.slotsGapOwner;
            if (i > start) {
                this.slotsGapOwner = Math.max(start, i - len);
            }
            int i2 = this.currentGroupEnd;
            if (i2 >= this.groupGapStart) {
                this.currentGroupEnd = i2 - len;
            }
            int i3 = this.parent;
            if (containsGroupMark(i3)) {
                updateContainsMark(i3);
            }
        }
        return zRemoveAnchors;
    }

    public final GroupSourceInformation sourceInformationOf$runtime(int group) {
        Anchor anchorTryAnchor$runtime;
        HashMap<Anchor, GroupSourceInformation> map = this.sourceInformationMap;
        if (map == null || (anchorTryAnchor$runtime = tryAnchor$runtime(group)) == null) {
            return null;
        }
        return map.get(anchorTryAnchor$runtime);
    }

    public final Anchor tryAnchor$runtime(int group) {
        if (group < 0 || group >= getSize$runtime()) {
            return null;
        }
        return SlotTableKt.find(this.anchors, group, getSize$runtime());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeSlots(int start, int len, int group) {
        if (len > 0) {
            int i = this.slotsGapLen;
            int i2 = start + len;
            moveSlotGapTo(i2, group);
            this.slotsGapStart = start;
            this.slotsGapLen = i + len;
            ArraysKt.fill(this.slots, (Object) null, start, i2);
            int i3 = this.currentSlotEnd;
            if (i3 >= start) {
                this.currentSlotEnd = i3 - len;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    private final void updateNodeOfGroup(int index, Object value) {
        boolean z;
        int iGroupIndexToAddress = groupIndexToAddress(index);
        int[] iArr = this.groups;
        if (iGroupIndexToAddress < iArr.length) {
            z = (iArr[(iGroupIndexToAddress * 5) + 1] & 1073741824) != 0;
        }
        if (!z) {
            ComposerKt.composeImmediateRuntimeError("Updating the node of a group at " + index + " that was not created with as a node group");
        }
        this.slots[dataIndexToDataAddress(nodeIndex(this.groups, iGroupIndexToAddress))] = value;
    }

    private final void updateAnchors(int previousGapStart, int newGapStart) {
        Anchor anchor;
        int location$runtime;
        Anchor anchor2;
        int location$runtime2;
        int i;
        int capacity = getCapacity() - this.groupGapLen;
        ArrayList<Anchor> arrayList = this.anchors;
        if (previousGapStart < newGapStart) {
            for (int iLocationOf = SlotTableKt.locationOf(arrayList, previousGapStart, capacity); iLocationOf < this.anchors.size() && (location$runtime2 = (anchor2 = this.anchors.get(iLocationOf)).getLocation()) < 0 && (i = location$runtime2 + capacity) < newGapStart; iLocationOf++) {
                anchor2.setLocation$runtime(i);
            }
            return;
        }
        for (int iLocationOf2 = SlotTableKt.locationOf(arrayList, newGapStart, capacity); iLocationOf2 < this.anchors.size() && (location$runtime = (anchor = this.anchors.get(iLocationOf2)).getLocation()) >= 0; iLocationOf2++) {
            anchor.setLocation$runtime(-(capacity - location$runtime));
        }
    }

    private final boolean removeAnchors(int gapStart, int size, HashMap<Anchor, GroupSourceInformation> sourceInformationMap) {
        int i = size + gapStart;
        int iLocationOf = SlotTableKt.locationOf(this.anchors, i, getCapacity() - this.groupGapLen);
        if (iLocationOf >= this.anchors.size()) {
            iLocationOf--;
        }
        int i2 = iLocationOf + 1;
        int i3 = 0;
        while (iLocationOf >= 0) {
            Anchor anchor = this.anchors.get(iLocationOf);
            int iAnchorIndex = anchorIndex(anchor);
            if (iAnchorIndex < gapStart) {
                break;
            }
            if (iAnchorIndex < i) {
                anchor.setLocation$runtime(Integer.MIN_VALUE);
                if (sourceInformationMap != null) {
                    sourceInformationMap.remove(anchor);
                }
                if (i3 == 0) {
                    i3 = iLocationOf + 1;
                }
                i2 = iLocationOf;
            }
            iLocationOf--;
        }
        boolean z = i2 < i3;
        if (z) {
            this.anchors.subList(i2, i3).clear();
        }
        return z;
    }

    private final void moveAnchors(int originalLocation, int newLocation, int size) {
        Anchor anchor;
        int iAnchorIndex;
        int i = size + originalLocation;
        int size$runtime = getSize$runtime();
        int iLocationOf = SlotTableKt.locationOf(this.anchors, originalLocation, size$runtime);
        ArrayList arrayList = new ArrayList();
        if (iLocationOf >= 0) {
            while (iLocationOf < this.anchors.size() && (iAnchorIndex = anchorIndex((anchor = this.anchors.get(iLocationOf)))) >= originalLocation && iAnchorIndex < i) {
                arrayList.add(anchor);
                this.anchors.remove(iLocationOf);
            }
        }
        int i2 = newLocation - originalLocation;
        int size2 = arrayList.size();
        for (int i3 = 0; i3 < size2; i3++) {
            Anchor anchor2 = (Anchor) arrayList.get(i3);
            int iAnchorIndex2 = anchorIndex(anchor2) + i2;
            if (iAnchorIndex2 >= this.groupGapStart) {
                anchor2.setLocation$runtime(-(size$runtime - iAnchorIndex2));
            } else {
                anchor2.setLocation$runtime(iAnchorIndex2);
            }
            this.anchors.add(SlotTableKt.locationOf(this.anchors, iAnchorIndex2, size$runtime), anchor2);
        }
    }

    public final String toDebugString() {
        StringBuilder sb = new StringBuilder();
        sb.append(toString());
        Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
        sb.append('\n');
        Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
        sb.append("  parent:    " + this.parent);
        Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
        sb.append('\n');
        Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
        sb.append("  current:   " + this.currentGroup);
        Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
        sb.append('\n');
        Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
        sb.append("  group gap: " + this.groupGapStart + '-' + (this.groupGapStart + this.groupGapLen) + '(' + this.groupGapLen + ')');
        Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
        sb.append('\n');
        Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
        sb.append("  slots gap: " + this.slotsGapStart + '-' + (this.slotsGapStart + this.slotsGapLen) + '(' + this.slotsGapLen + ')');
        Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
        sb.append('\n');
        Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
        StringBuilder sb2 = new StringBuilder("  gap owner: ");
        sb2.append(this.slotsGapOwner);
        sb.append(sb2.toString());
        Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
        sb.append('\n');
        Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
        int size$runtime = getSize$runtime();
        for (int i = 0; i < size$runtime; i++) {
            groupAsString(sb, i);
            sb.append('\n');
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    private final void groupAsString(StringBuilder sb, int i) {
        int iGroupIndexToAddress = groupIndexToAddress(i);
        sb.append("Group(");
        if (i < 10) {
            sb.append(' ');
        }
        if (i < 100) {
            sb.append(' ');
        }
        if (i < 1000) {
            sb.append(' ');
        }
        sb.append(i);
        if (iGroupIndexToAddress != i) {
            sb.append("(");
            sb.append(iGroupIndexToAddress);
            sb.append(")");
        }
        sb.append('#');
        sb.append(SlotTableKt.groupSize(this.groups, iGroupIndexToAddress));
        sb.append('^');
        int i2 = iGroupIndexToAddress * 5;
        int i3 = i2 + 2;
        sb.append(parentAnchorToIndex(this.groups[i3]));
        sb.append(": key=");
        sb.append(this.groups[i2]);
        sb.append(", nodes=");
        int i4 = i2 + 1;
        sb.append(this.groups[i4] & RectListKt.Lower26Bits);
        sb.append(", dataAnchor=");
        sb.append(this.groups[i2 + 4]);
        sb.append(", parentAnchor=");
        sb.append(this.groups[i3]);
        if ((this.groups[i4] & 1073741824) != 0) {
            sb.append(", node=" + SlotTableKt.summarize(String.valueOf(this.slots[dataIndexToDataAddress(nodeIndex(this.groups, iGroupIndexToAddress))]), 10));
        }
        int iSlotIndex = slotIndex(this.groups, iGroupIndexToAddress);
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(i + 1));
        if (iDataIndex > iSlotIndex) {
            sb.append(", [");
            for (int i5 = iSlotIndex; i5 < iDataIndex; i5++) {
                if (i5 != iSlotIndex) {
                    sb.append(", ");
                }
                sb.append(SlotTableKt.summarize(String.valueOf(this.slots[dataIndexToDataAddress(i5)]), 10));
            }
            sb.append(AbstractJsonLexerKt.END_LIST);
        }
        sb.append(")");
    }

    public final void verifyDataAnchors$runtime() {
        int i = this.slotsGapOwner;
        int length = this.slots.length - this.slotsGapLen;
        int size$runtime = getSize$runtime();
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        while (i2 < size$runtime) {
            int iGroupIndexToAddress = groupIndexToAddress(i2);
            int[] iArr = this.groups;
            int i4 = iArr[(iGroupIndexToAddress * 5) + 4];
            int iDataIndex = dataIndex(iArr, iGroupIndexToAddress);
            if (!(iDataIndex >= i3)) {
                PreconditionsKt.throwIllegalStateException("Data index out of order at " + i2 + ", previous = " + i3 + ", current = " + iDataIndex);
            }
            if (!(iDataIndex <= length)) {
                PreconditionsKt.throwIllegalStateException("Data index, " + iDataIndex + ", out of bound at " + i2);
            }
            if (i4 < 0 && !z) {
                if (!(i == i2)) {
                    PreconditionsKt.throwIllegalStateException("Expected the slot gap owner to be " + i + " found gap at " + i2);
                }
                z = true;
            }
            i2++;
            i3 = iDataIndex;
        }
    }

    public final void verifyParentAnchors$runtime() {
        int i = this.groupGapStart;
        int i2 = this.groupGapLen;
        int capacity = getCapacity();
        int i3 = 0;
        while (true) {
            if (i3 >= i) {
                break;
            }
            if (!(this.groups[(i3 * 5) + 2] > -2)) {
                PreconditionsKt.throwIllegalStateException("Expected a start relative anchor at " + i3);
            }
            i3++;
        }
        for (int i4 = i2 + i; i4 < capacity; i4++) {
            int i5 = this.groups[(i4 * 5) + 2];
            if (parentAnchorToIndex(i5) < i) {
                if (!(i5 > -2)) {
                    PreconditionsKt.throwIllegalStateException("Expected a start relative anchor at " + i4);
                }
            } else if (!(i5 <= -2)) {
                PreconditionsKt.throwIllegalStateException("Expected an end relative anchor at " + i4);
            }
        }
    }

    public final int getSize$runtime() {
        return getCapacity() - this.groupGapLen;
    }

    private final int getCapacity() {
        return this.groups.length / 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int groupIndexToAddress(int index) {
        return index + (this.groupGapLen * (index < this.groupGapStart ? 0 : 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndexToDataAddress(int dataIndex) {
        return dataIndex + (this.slotsGapLen * (dataIndex < this.slotsGapStart ? 0 : 1));
    }

    private final int parent(int[] iArr, int i) {
        return parentAnchorToIndex(iArr[(groupIndexToAddress(i) * 5) + 2]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndex(int index) {
        return dataIndex(this.groups, groupIndexToAddress(index));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndex(int[] iArr, int i) {
        return i >= getCapacity() ? this.slots.length - this.slotsGapLen : dataAnchorToDataIndex(iArr[(i * 5) + 4], this.slotsGapLen, this.slots.length);
    }

    private final int slotIndex(int[] iArr, int i) {
        return i >= getCapacity() ? this.slots.length - this.slotsGapLen : dataAnchorToDataIndex(SlotTableKt.slotAnchor(iArr, i), this.slotsGapLen, this.slots.length);
    }

    private final void updateDataIndex(int[] iArr, int i, int i2) {
        iArr[(i * 5) + 4] = dataIndexToDataAnchor(i2, this.slotsGapStart, this.slotsGapLen, this.slots.length);
    }

    private final int nodeIndex(int[] iArr, int i) {
        return dataIndex(iArr, i);
    }

    private final int auxIndex(int[] iArr, int i) {
        return dataIndex(iArr, i) + Integer.bitCount(iArr[(i * 5) + 1] >> 29);
    }

    private final List<Integer> dataIndexes(int[] iArr) {
        List listDataAnchors$default = SlotTableKt.dataAnchors$default(this.groups, 0, 1, null);
        List listPlus = CollectionsKt.plus((Collection) CollectionsKt.slice(listDataAnchors$default, RangesKt.until(0, this.groupGapStart)), (Iterable) CollectionsKt.slice(listDataAnchors$default, RangesKt.until(this.groupGapStart + this.groupGapLen, iArr.length / 5)));
        ArrayList arrayList = new ArrayList(listPlus.size());
        int size = listPlus.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(Integer.valueOf(dataAnchorToDataIndex(((Number) listPlus.get(i)).intValue(), this.slotsGapLen, this.slots.length)));
        }
        return arrayList;
    }

    private final List<Integer> keys() {
        List listKeys$default = SlotTableKt.keys$default(this.groups, 0, 1, null);
        ArrayList arrayList = new ArrayList(listKeys$default.size());
        int size = listKeys$default.size();
        for (int i = 0; i < size; i++) {
            Object obj = listKeys$default.get(i);
            ((Number) obj).intValue();
            int i2 = this.groupGapStart;
            if (i < i2 || i >= i2 + this.groupGapLen) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private final int parentIndexToAnchor(int index, int gapStart) {
        return index < gapStart ? index : -((getSize$runtime() - index) + 2);
    }

    private final int parentAnchorToIndex(int index) {
        return index > -2 ? index : (getSize$runtime() + index) - (-2);
    }

    public final void trimTailSlots(int count) {
        if (!(count > 0)) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        int i = this.parent;
        int iSlotIndex = slotIndex(this.groups, groupIndexToAddress(i));
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(i + 1)) - count;
        if (!(iDataIndex >= iSlotIndex)) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        removeSlots(iDataIndex, count, i);
        int i2 = this.currentSlot;
        if (i2 >= iSlotIndex) {
            this.currentSlot = i2 - count;
        }
    }

    public final void forAllDataInRememberOrder(int group, Function2<? super Integer, Object, Unit> block) {
        int i;
        int i2;
        Anchor after;
        int iParent = parent(group);
        int size$runtime = getSize$runtime();
        int iGroupSize = groupSize(group) + group;
        DefaultConstructorMarker defaultConstructorMarker = null;
        int i3 = group;
        MutableIntSet mutableIntSetMutableIntSetOf = null;
        MutableIntList mutableIntList = null;
        while (i3 < iGroupSize) {
            int iDataIndex = dataIndex(i3);
            int i4 = i3 + 1;
            int iDataIndex2 = dataIndex(i4);
            while (true) {
                i = 0;
                if (iDataIndex >= iDataIndex2) {
                    break;
                }
                Object obj = this.slots[dataIndexToDataAddress(iDataIndex)];
                if ((obj instanceof RememberObserverHolder) && (after = ((RememberObserverHolder) obj).getAfter()) != null && after.getValid()) {
                    int iAnchorIndex = anchorIndex(after);
                    if (mutableIntSetMutableIntSetOf == null) {
                        mutableIntSetMutableIntSetOf = IntSetKt.mutableIntSetOf();
                    }
                    if (mutableIntList == null) {
                        mutableIntList = new MutableIntList(i, 1, defaultConstructorMarker);
                    }
                    mutableIntSetMutableIntSetOf.add(iAnchorIndex);
                    mutableIntList.add(iAnchorIndex);
                    mutableIntList.add(iDataIndex);
                } else {
                    block.invoke(Integer.valueOf(iDataIndex), obj);
                }
                iDataIndex++;
            }
            int iParent2 = i4 < size$runtime ? parent(i4) : -1;
            if (iParent2 != i3) {
                while (true) {
                    if (mutableIntList == null || mutableIntSetMutableIntSetOf == null || !mutableIntSetMutableIntSetOf.remove(i3)) {
                        i2 = size$runtime;
                    } else {
                        int i5 = mutableIntList._size;
                        int i6 = i5 / 2;
                        int i7 = i;
                        int i8 = i7;
                        while (i8 < i6) {
                            int i9 = i8 * 2;
                            int i10 = size$runtime;
                            int i11 = mutableIntList.get(i9);
                            if (i11 == i3) {
                                int i12 = mutableIntList.get(i9 + 1);
                                block.invoke(Integer.valueOf(i12), this.slots[dataIndexToDataAddress(i12)]);
                            } else if (i9 != i7) {
                                int i13 = i7 + 1;
                                mutableIntList.set(i7, i11);
                                i7 += 2;
                                mutableIntList.set(i13, mutableIntList.get(i9 + 1));
                            } else {
                                i7 += 2;
                            }
                            i8++;
                            block = block;
                            size$runtime = i10;
                        }
                        i2 = size$runtime;
                        if (i7 != i5) {
                            mutableIntList.removeRange(i7, i5);
                        }
                    }
                    if (i3 == group || iParent == iParent2) {
                        break;
                    }
                    i3 = iParent;
                    size$runtime = i2;
                    i = 0;
                    iParent = parent(iParent);
                    block = block;
                }
            } else {
                i2 = size$runtime;
            }
            iParent = iParent2;
            i3 = i4;
            size$runtime = i2;
            defaultConstructorMarker = null;
        }
    }
}
