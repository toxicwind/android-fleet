package com.fleeksoft.ksoup.nodes;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fleeksoft.ksoup.exception.PatternSyntaxException;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.internal.Normalizer;
import com.fleeksoft.ksoup.internal.QuietAppendable;
import com.fleeksoft.ksoup.internal.StringUtil;
import com.fleeksoft.ksoup.internal.WeakReference;
import com.fleeksoft.ksoup.parser.ParseSettings;
import com.fleeksoft.ksoup.parser.Parser;
import com.fleeksoft.ksoup.parser.Tag;
import com.fleeksoft.ksoup.parser.TokenQueue;
import com.fleeksoft.ksoup.ported.AtomicBoolean;
import com.fleeksoft.ksoup.ported.Consumer;
import com.fleeksoft.ksoup.ported.CoreFunctionsKt;
import com.fleeksoft.ksoup.select.Collector;
import com.fleeksoft.ksoup.select.Elements;
import com.fleeksoft.ksoup.select.Evaluator;
import com.fleeksoft.ksoup.select.NodeFilter;
import com.fleeksoft.ksoup.select.NodeVisitor;
import com.fleeksoft.ksoup.select.Nodes;
import com.fleeksoft.ksoup.select.Selector;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.reflect.KClass;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: Element.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000¢\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0017\u0018\u0000 ×\u00012\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0004Ö\u0001×\u0001B\u0019\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\bB%\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0006\u0010\rB\u001b\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u000eJ\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010%H\u0016J\b\u0010&\u001a\u00020#H\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016J\u0012\u0010'\u001a\u00020(2\b\u0010\n\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020\u0004H\u0016J\u0006\u0010,\u001a\u00020\u0004J\b\u0010-\u001a\u00020\u0004H\u0016J\u001a\u0010.\u001a\u00020#2\b\u0010-\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u001a\u0010,\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0007J\u0006\u0010\u0003\u001a\u00020\tJ\u0010\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\tH\u0007J\u0006\u0010/\u001a\u00020#J\u0006\u00100\u001a\u00020\u0004J\u0010\u00100\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u0004H\u0007J\u001a\u00101\u001a\u00020\u00002\u0006\u00102\u001a\u00020\u00042\b\u00103\u001a\u0004\u0018\u00010\u0004H\u0016J\u0018\u00101\u001a\u00020\u00002\u0006\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00020#H\u0007J\u0012\u00104\u001a\u0004\u0018\u0001052\b\u00106\u001a\u0004\u0018\u00010\u0004J\u0006\u00107\u001a\u000208J\n\u00109\u001a\u0004\u0018\u00010\u0000H\u0016J\u0006\u0010:\u001a\u00020;J\u000e\u0010<\u001a\u00020\u00002\u0006\u0010=\u001a\u00020*J\u0006\u0010>\u001a\u00020*J\u0006\u0010?\u001a\u00020;J\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00000AJ\u000e\u0010B\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010AJ\u0016\u0010C\u001a\u00020(2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00000AH\u0002J\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00000FJ)\u0010G\u001a\b\u0012\u0004\u0012\u0002HH0A\"\n\b\u0000\u0010H\u0018\u0001*\u00020\u00102\f\u0010I\u001a\b\u0012\u0004\u0012\u0002HH0JH\u0082\bJ\f\u0010K\u001a\b\u0012\u0004\u0012\u00020L0AJ\f\u0010M\u001a\b\u0012\u0004\u0012\u00020N0AJ\u000e\u0010O\u001a\u00020;2\u0006\u0010P\u001a\u00020\u0004J\u0010\u0010O\u001a\u00020;2\u0006\u0010Q\u001a\u00020RH\u0007J\u0014\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00000F2\u0006\u0010P\u001a\u00020\u0004J\u0010\u0010T\u001a\u0004\u0018\u00010\u00002\u0006\u0010P\u001a\u00020\u0004J\u0012\u0010T\u001a\u0004\u0018\u00010\u00002\u0006\u0010Q\u001a\u00020RH\u0007J\u000e\u0010U\u001a\u00020\u00002\u0006\u0010P\u001a\u00020\u0004J\u0016\u0010V\u001a\b\u0012\u0004\u0012\u00020\u00010W2\u0006\u0010Q\u001a\u00020RH\u0007J\u0014\u0010V\u001a\b\u0012\u0004\u0012\u00020\u00010W2\u0006\u0010P\u001a\u00020\u0004J.\u0010V\u001a\b\u0012\u0004\u0012\u0002HH0W\"\b\b\u0000\u0010H*\u00020\u00012\u0006\u0010Q\u001a\u00020R2\f\u0010X\u001a\b\u0012\u0004\u0012\u0002HH0JH\u0007J.\u0010V\u001a\b\u0012\u0004\u0012\u0002HH0W\"\b\b\u0000\u0010H*\u00020\u00012\u0006\u0010P\u001a\u00020\u00042\f\u0010X\u001a\b\u0012\u0004\u0012\u0002HH0JH\u0007J-\u0010Y\u001a\u0004\u0018\u0001HH\"\b\b\u0000\u0010H*\u00020\u00012\u0006\u0010P\u001a\u00020\u00042\f\u0010X\u001a\b\u0012\u0004\u0012\u0002HH0J¢\u0006\u0002\u0010ZJ/\u0010Y\u001a\u0004\u0018\u0001HH\"\b\b\u0000\u0010H*\u00020\u00012\u0006\u0010Q\u001a\u00020R2\f\u0010X\u001a\b\u0012\u0004\u0012\u0002HH0JH\u0007¢\u0006\u0002\u0010[J+\u0010\\\u001a\u0002HH\"\b\b\u0000\u0010H*\u00020\u00012\u0006\u0010P\u001a\u00020\u00042\f\u0010X\u001a\b\u0012\u0004\u0012\u0002HH0J¢\u0006\u0002\u0010ZJ\u000e\u0010]\u001a\u00020#2\u0006\u0010P\u001a\u00020\u0004J\u0012\u0010]\u001a\u00020#2\b\u0010Q\u001a\u0004\u0018\u00010RH\u0007J\u0010\u0010^\u001a\u0004\u0018\u00010\u00002\u0006\u0010P\u001a\u00020\u0004J\u0012\u0010^\u001a\u0004\u0018\u00010\u00002\u0006\u0010Q\u001a\u00020RH\u0007J\u000e\u0010_\u001a\u00020\u00002\u0006\u0010<\u001a\u00020\u0001J\u0014\u0010`\u001a\u00020\u00002\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00010aJ\u000e\u0010b\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u0000J\u000e\u0010c\u001a\u00020\u00002\u0006\u0010<\u001a\u00020\u0001J\u0014\u0010d\u001a\u00020\u00002\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00010aJ\u001c\u0010e\u001a\u00020\u00002\u0006\u0010=\u001a\u00020*2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00010aJ)\u0010e\u001a\u00020\u00002\u0006\u0010=\u001a\u00020*2\u0012\u0010?\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010f\"\u00020\u0001H\u0007¢\u0006\u0002\u0010gJ\u0018\u0010h\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004J\u001a\u0010i\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0007J\u000e\u0010j\u001a\u00020\u00002\u0006\u0010k\u001a\u00020\u0004J\u000e\u0010l\u001a\u00020\u00002\u0006\u0010k\u001a\u00020\u0004J\u000e\u0010m\u001a\u00020\u00002\u0006\u0010n\u001a\u00020\u0004J\u000e\u0010o\u001a\u00020\u00002\u0006\u0010n\u001a\u00020\u0004J\u0010\u0010p\u001a\u00020\u00002\u0006\u0010n\u001a\u00020\u0004H\u0016J\u0010\u0010p\u001a\u00020\u00002\u0006\u0010q\u001a\u00020\u0001H\u0016J\u0010\u0010r\u001a\u00020\u00002\u0006\u0010n\u001a\u00020\u0004H\u0016J\u0010\u0010r\u001a\u00020\u00002\u0006\u0010q\u001a\u00020\u0001H\u0016J\b\u0010s\u001a\u00020\u0000H\u0016J\u0010\u0010t\u001a\u00020\u00002\u0006\u0010n\u001a\u00020\u0004H\u0016J\u0012\u0010u\u001a\u00020\u00042\b\u0010v\u001a\u0004\u0018\u00010wH\u0002J\u0006\u0010x\u001a\u00020\u0004J\b\u0010y\u001a\u00020\u0004H\u0002J\u0006\u0010z\u001a\u00020;J\u0006\u0010{\u001a\u00020;J\u0006\u0010|\u001a\u00020;J\u0010\u0010{\u001a\u00020;2\u0006\u0010}\u001a\u00020#H\u0002J\b\u0010~\u001a\u0004\u0018\u00010\u0000J\u0006\u0010\u007f\u001a\u00020*J\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0000J\t\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u0000J\t\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u0000J\u0011\u0010\u0083\u0001\u001a\u00020;2\b\u0010,\u001a\u0004\u0018\u00010\u0004J\u0011\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u00002\u0006\u00100\u001a\u00020\u0004J\u0010\u0010\u0085\u0001\u001a\u00020;2\u0007\u0010\u0086\u0001\u001a\u00020\u0004J\u000f\u0010\u0087\u0001\u001a\u00020;2\u0006\u00106\u001a\u00020\u0004J\u0010\u0010\u0088\u0001\u001a\u00020;2\u0007\u0010\u0089\u0001\u001a\u00020\u0004J\u0017\u0010\u008a\u0001\u001a\u00020;2\u0006\u00106\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004J\u0017\u0010\u008b\u0001\u001a\u00020;2\u0006\u00106\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004J\u0018\u0010\u008c\u0001\u001a\u00020;2\u0006\u00106\u001a\u00020\u00042\u0007\u0010\u008d\u0001\u001a\u00020\u0004J\u0018\u0010\u008e\u0001\u001a\u00020;2\u0006\u00106\u001a\u00020\u00042\u0007\u0010\u008f\u0001\u001a\u00020\u0004J\u0018\u0010\u0090\u0001\u001a\u00020;2\u0006\u00106\u001a\u00020\u00042\u0007\u0010\u0091\u0001\u001a\u00020\u0004J\u0019\u0010\u0092\u0001\u001a\u00020;2\u0006\u00106\u001a\u00020\u00042\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001J\u001a\u0010\u0092\u0001\u001a\u00020;2\u0006\u00106\u001a\u00020\u00042\u0007\u0010\u0093\u0001\u001a\u00020\u0004H\u0007J\u000f\u0010\u0095\u0001\u001a\u00020;2\u0006\u0010=\u001a\u00020*J\u000f\u0010\u0096\u0001\u001a\u00020;2\u0006\u0010=\u001a\u00020*J\u000f\u0010\u0097\u0001\u001a\u00020;2\u0006\u0010=\u001a\u00020*J\u0010\u0010\u0098\u0001\u001a\u00020;2\u0007\u0010\u0099\u0001\u001a\u00020\u0004J\u0012\u0010\u009a\u0001\u001a\u00020;2\u0007\u0010\u0099\u0001\u001a\u00020\u0004H\u0007J\u0011\u0010\u009b\u0001\u001a\u00020;2\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001J\u0012\u0010\u009b\u0001\u001a\u00020;2\u0007\u0010\u0093\u0001\u001a\u00020\u0004H\u0007J\u0011\u0010\u009c\u0001\u001a\u00020;2\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001J\u0012\u0010\u009c\u0001\u001a\u00020;2\u0007\u0010\u0093\u0001\u001a\u00020\u0004H\u0007J\u0007\u0010\u009d\u0001\u001a\u00020;J\u0006\u0010k\u001a\u00020\u0004J\u0007\u0010\u009e\u0001\u001a\u00020\u0004J\t\u0010\u009f\u0001\u001a\u00020\u0004H\u0016J\u0007\u0010 \u0001\u001a\u00020\u0004J\u0007\u0010¡\u0001\u001a\u00020\u0004J\u0018\u0010¡\u0001\u001a\u00020(2\r\u0010¢\u0001\u001a\b0£\u0001j\u0003`¤\u0001H\u0002J\u0010\u0010k\u001a\u00020\u00002\u0006\u0010k\u001a\u00020\u0004H\u0017J\u0007\u0010¥\u0001\u001a\u00020#J\u0007\u0010¦\u0001\u001a\u00020\u0004J\u0007\u0010\u0086\u0001\u001a\u00020\u0004J\u000e\u0010§\u0001\u001a\t\u0012\u0004\u0012\u00020\u00040¨\u0001J\u0019\u0010§\u0001\u001a\u00020\u00002\u000e\u0010§\u0001\u001a\t\u0012\u0004\u0012\u00020\u00040©\u0001H\u0007J\u0010\u0010ª\u0001\u001a\u00020#2\u0007\u0010\u0086\u0001\u001a\u00020\u0004J\u0010\u0010«\u0001\u001a\u00020\u00002\u0007\u0010\u0086\u0001\u001a\u00020\u0004J\u0010\u0010¬\u0001\u001a\u00020\u00002\u0007\u0010\u0086\u0001\u001a\u00020\u0004J\u0010\u0010\u00ad\u0001\u001a\u00020\u00002\u0007\u0010\u0086\u0001\u001a\u00020\u0004J\u0006\u0010\u0012\u001a\u00020\u0004J\u0010\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0004H\u0007J\b\u0010®\u0001\u001a\u00030¯\u0001J#\u0010°\u0001\u001a\u00020(2\b\u0010¢\u0001\u001a\u00030±\u00012\b\u0010²\u0001\u001a\u00030³\u0001H\u0010¢\u0006\u0003\b´\u0001J#\u0010µ\u0001\u001a\u00020(2\b\u0010¢\u0001\u001a\u00030±\u00012\b\u0010²\u0001\u001a\u00030³\u0001H\u0010¢\u0006\u0003\b¶\u0001J\u0015\u0010·\u0001\u001a\u0004\u0018\u00010\u00042\b\u0010¸\u0001\u001a\u00030¹\u0001H\u0002J\u0006\u0010n\u001a\u00020\u0004J'\u0010n\u001a\u0002HH\"\u000e\b\u0000\u0010H*\b0º\u0001j\u0003`»\u00012\u0007\u0010¼\u0001\u001a\u0002HHH\u0016¢\u0006\u0003\u0010½\u0001J\u0010\u0010n\u001a\u00020\u00002\u0006\u0010n\u001a\u00020\u0004H\u0007J\u000f\u0010¾\u0001\u001a\u00020\u0001H\u0010¢\u0006\u0003\b¿\u0001J\t\u0010À\u0001\u001a\u00020\u0000H\u0016J\u0013\u0010Á\u0001\u001a\u00020\u00002\b\u00109\u001a\u0004\u0018\u00010\u0001H\u0014J\t\u0010Â\u0001\u001a\u00020\u0000H\u0016J\t\u0010Ã\u0001\u001a\u00020\u0000H\u0016J\u0011\u0010Ä\u0001\u001a\u00020\u00002\u0006\u00102\u001a\u00020\u0004H\u0016J\t\u0010Å\u0001\u001a\u00020\u0000H\u0016J\u0013\u0010Æ\u0001\u001a\u00020\u00002\b\u0010Ç\u0001\u001a\u00030È\u0001H\u0016J\u001b\u0010É\u0001\u001a\u00020\u00002\u0010\u0010Ê\u0001\u001a\u000b\u0012\u0006\b\u0000\u0012\u00020\u00010Ë\u0001H\u0016J\u001d\u0010Ì\u0001\u001a\u00020(2\u0014\u0010Ê\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020(0Í\u0001J\u0012\u0010Î\u0001\u001a\t\u0012\u0004\u0012\u00020\u00000Ï\u0001H\u0096\u0082\u0004J\u0013\u0010Ð\u0001\u001a\u00020\u00002\b\u0010Ñ\u0001\u001a\u00030Ò\u0001H\u0016J\u0007\u0010Ó\u0001\u001a\u00020(J\u0007\u0010Ô\u0001\u001a\u00020(J\u0007\u0010Õ\u0001\u001a\u00020#R\u0012\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\t8\u0006@DX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00188\u0006@DX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006Ø\u0001"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Element;", "Lcom/fleeksoft/ksoup/nodes/Node;", "", "tag", "", "namespace", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "(Ljava/lang/String;)V", "Lcom/fleeksoft/ksoup/parser/Tag;", "baseUri", "attributes", "Lcom/fleeksoft/ksoup/nodes/Attributes;", "(Lcom/fleeksoft/ksoup/parser/Tag;Ljava/lang/String;Lcom/fleeksoft/ksoup/nodes/Attributes;)V", "(Lcom/fleeksoft/ksoup/parser/Tag;Ljava/lang/String;)V", "lock", "", "Lco/touchlab/stately/concurrency/Synchronizable;", ES6Iterator.VALUE_PROPERTY, "getTag", "()Lcom/fleeksoft/ksoup/parser/Tag;", "setTag", "(Lcom/fleeksoft/ksoup/parser/Tag;)V", "_baseUri", "Lcom/fleeksoft/ksoup/nodes/Element$Companion$NodeList;", "childNodes", "getChildNodes", "()Lcom/fleeksoft/ksoup/nodes/Element$Companion$NodeList;", "setChildNodes", "(Lcom/fleeksoft/ksoup/nodes/Element$Companion$NodeList;)V", "getAttributes$ksoup_release", "()Lcom/fleeksoft/ksoup/nodes/Attributes;", "setAttributes$ksoup_release", "(Lcom/fleeksoft/ksoup/nodes/Attributes;)V", "hasChildNodes", "", "ensureChildNodes", "", "hasAttributes", "doSetBaseUri", "", "childNodeSize", "", "nodeName", "tagName", "normalName", "elementIs", "isBlock", "id", "attr", "attributeKey", "attributeValue", "attribute", "Lcom/fleeksoft/ksoup/nodes/Attribute;", "key", "dataset", "Lcom/fleeksoft/ksoup/nodes/Attributes$Dataset;", "parent", "parents", "Lcom/fleeksoft/ksoup/select/Elements;", "child", "index", "childrenSize", "children", "childElementsList", "", "cachedChildren", "stashChildren", "els", "stream", "Lkotlin/sequences/Sequence;", "filterNodes", ExifInterface.GPS_DIRECTION_TRUE, "clazz", "Lkotlin/reflect/KClass;", "textNodes", "Lcom/fleeksoft/ksoup/nodes/TextNode;", "dataNodes", "Lcom/fleeksoft/ksoup/nodes/DataNode;", "select", "cssQuery", "evaluator", "Lcom/fleeksoft/ksoup/select/Evaluator;", "selectStream", "selectFirst", "expectFirst", "selectNodes", "Lcom/fleeksoft/ksoup/select/Nodes;", "type", "selectFirstNode", "(Ljava/lang/String;Lkotlin/reflect/KClass;)Lcom/fleeksoft/ksoup/nodes/Node;", "(Lcom/fleeksoft/ksoup/select/Evaluator;Lkotlin/reflect/KClass;)Lcom/fleeksoft/ksoup/nodes/Node;", "expectFirstNode", "is", "closest", "appendChild", "appendChildren", "", "appendTo", "prependChild", "prependChildren", "insertChildren", "", "(I[Lcom/fleeksoft/ksoup/nodes/Node;)Lcom/fleeksoft/ksoup/nodes/Element;", "appendElement", "prependElement", "appendText", "text", "prependText", "append", "html", "prepend", TtmlNode.ANNOTATION_POSITION_BEFORE, "node", TtmlNode.ANNOTATION_POSITION_AFTER, "empty", "wrap", "uniqueIdSelector", "ownerDoc", "Lcom/fleeksoft/ksoup/nodes/Document;", "cssSelector", "cssSelectorComponent", "siblingElements", "nextElementSiblings", "previousElementSiblings", "next", "firstElementSibling", "elementSiblingIndex", "lastElementSibling", "firstElementChild", "lastElementChild", "getElementsByTag", "getElementById", "getElementsByClass", "className", "getElementsByAttribute", "getElementsByAttributeStarting", "keyPrefix", "getElementsByAttributeValue", "getElementsByAttributeValueNot", "getElementsByAttributeValueStarting", "valuePrefix", "getElementsByAttributeValueEnding", "valueSuffix", "getElementsByAttributeValueContaining", "match", "getElementsByAttributeValueMatching", "regex", "Lkotlin/text/Regex;", "getElementsByIndexLessThan", "getElementsByIndexGreaterThan", "getElementsByIndexEquals", "getElementsContainingText", "searchText", "getElementsContainingOwnText", "getElementsMatchingText", "getElementsMatchingOwnText", "getAllElements", "wholeText", "nodeValue", "wholeOwnText", "ownText", "accum", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "hasText", "data", "classNames", "", "", "hasClass", "addClass", "removeClass", "toggleClass", "endSourceRange", "Lcom/fleeksoft/ksoup/nodes/Range;", "outerHtmlHead", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "out", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "outerHtmlHead$ksoup_release", "outerHtmlTail", "outerHtmlTail$ksoup_release", "safeTagName", "syntax", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings$Syntax;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "appendable", "(Ljava/lang/Appendable;)Ljava/lang/Appendable;", "createClone", "createClone$ksoup_release", "clone", "doClone", "shallowClone", "clearAttributes", "removeAttr", "root", "traverse", "nodeVisitor", "Lcom/fleeksoft/ksoup/select/NodeVisitor;", "forEachNode", "action", "Lcom/fleeksoft/ksoup/ported/Consumer;", "forEach", "Lkotlin/Function1;", "iterator", "", "filter", "nodeFilter", "Lcom/fleeksoft/ksoup/select/NodeFilter;", "reindexChildren", "invalidateChildren", "hasValidChildren", "TextAccumulator", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Element extends Node implements Iterable<Element>, KMappedMarker {
    private static final String childElsKey = "ksoup.childEls";
    private static final String childElsMod = "ksoup.childElsMod";
    private String _baseUri;
    private Attributes attributes;
    private Companion.NodeList childNodes;
    private final Object lock;
    private Tag tag;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<Element> EmptyChildren = CollectionsKt.emptyList();
    private static final Companion.NodeList EmptyNodeList = new Companion.NodeList(0);
    private static final Regex ClassSplit = new Regex("\\s+");
    private static final String BaseUriKey = Attributes.INSTANCE.internalKey("baseUri");

    public final Element prependElement(String tagName) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        return prependElement$default(this, tagName, null, 2, null);
    }

    public final Element tagName(String tagName) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        return tagName$default(this, tagName, null, 2, null);
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public /* bridge */ /* synthetic */ Node forEachNode(Consumer consumer) {
        return forEachNode((Consumer<? super Node>) consumer);
    }

    public final Tag getTag() {
        return this.tag;
    }

    protected final void setTag(Tag tag) {
        Intrinsics.checkNotNullParameter(tag, "<set-?>");
        this.tag = tag;
    }

    public final Companion.NodeList getChildNodes() {
        return this.childNodes;
    }

    protected final void setChildNodes(Companion.NodeList nodeList) {
        Intrinsics.checkNotNullParameter(nodeList, "<set-?>");
        this.childNodes = nodeList;
    }

    /* JADX INFO: renamed from: getAttributes$ksoup_release, reason: from getter */
    public final Attributes getAttributes() {
        return this.attributes;
    }

    public final void setAttributes$ksoup_release(Attributes attributes) {
        this.attributes = attributes;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Element(String tag, String namespace) {
        this(Tag.INSTANCE.valueOf(tag, namespace, ParseSettings.INSTANCE.getPreserveCase()), (String) null);
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Element(String tag) {
        this(tag, "http://www.w3.org/1999/xhtml");
        Intrinsics.checkNotNullParameter(tag, "tag");
    }

    public Element(Tag tag, String str, Attributes attributes) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        this.lock = new Object();
        this.childNodes = EmptyNodeList;
        this.attributes = attributes;
        this.tag = tag;
        this._baseUri = str;
        if (StringUtil.INSTANCE.isBlank(str)) {
            return;
        }
        Intrinsics.checkNotNull(str);
        setBaseUri(str);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Element(Tag tag, String str) {
        this(tag, str, null);
        Intrinsics.checkNotNullParameter(tag, "tag");
    }

    public final boolean hasChildNodes() {
        return !Intrinsics.areEqual(this.childNodes, EmptyNodeList);
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public List<Node> ensureChildNodes() {
        if (Intrinsics.areEqual(this.childNodes, EmptyNodeList)) {
            this.childNodes = new Companion.NodeList(4);
        }
        return this.childNodes;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public boolean hasAttributes() {
        return this.attributes != null;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Attributes attributes() {
        if (this.attributes == null) {
            this.attributes = new Attributes();
        }
        Attributes attributes = this.attributes;
        Intrinsics.checkNotNull(attributes);
        return attributes;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public String baseUri() {
        String strSearchUpForAttribute = INSTANCE.searchUpForAttribute(this, BaseUriKey);
        return strSearchUpForAttribute == null ? "" : strSearchUpForAttribute;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public void doSetBaseUri(String baseUri) {
        attributes().put(BaseUriKey, baseUri);
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public int childNodeSize() {
        return this.childNodes.size();
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public String nodeName() {
        return this.tag.getTagName();
    }

    public final String tagName() {
        return this.tag.getTagName();
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public String normalName() {
        return this.tag.normalName();
    }

    public final boolean elementIs(String normalName, String namespace) {
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        return Intrinsics.areEqual(this.tag.normalName(), normalName) && Intrinsics.areEqual(this.tag.namespace(), namespace);
    }

    public static /* synthetic */ Element tagName$default(Element element, String str, String str2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tagName");
        }
        if ((i & 2) != 0) {
            str2 = element.tag.namespace();
        }
        return element.tagName(str, str2);
    }

    public final Element tagName(String tagName, String namespace) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        Validate.INSTANCE.notEmptyParam(tagName, "tagName");
        Validate.INSTANCE.notEmptyParam(namespace, "namespace");
        Parser rVar = NodeUtils.INSTANCE.parser(this);
        this.tag = rVar.tagSet().valueOf(tagName, namespace, rVar.getSettings());
        return this;
    }

    public final Tag tag() {
        return this.tag;
    }

    public final Element tag(Tag tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        this.tag = tag;
        return this;
    }

    public final boolean isBlock() {
        return this.tag.isBlock();
    }

    public final String id() {
        Attributes attributes = this.attributes;
        if (attributes == null) {
            return "";
        }
        Intrinsics.checkNotNull(attributes);
        return attributes.getIgnoreCase("id");
    }

    public final Element id(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        attr("id", id);
        return this;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Element attr(String attributeKey, String attributeValue) {
        Intrinsics.checkNotNullParameter(attributeKey, "attributeKey");
        super.attr(attributeKey, attributeValue);
        return this;
    }

    public final Element attr(String attributeKey, boolean attributeValue) {
        Intrinsics.checkNotNullParameter(attributeKey, "attributeKey");
        attributes().put(attributeKey, attributeValue);
        return this;
    }

    public final Attribute attribute(String key) {
        if (hasAttributes()) {
            return attributes().attribute(key);
        }
        return null;
    }

    public final Attributes.Dataset dataset() {
        return attributes().dataset();
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Element parent() {
        Element element = get_parentNode();
        if (element instanceof Element) {
            return element;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Elements parents() {
        Elements elements = new Elements(null, 1, 0 == true ? 1 : 0);
        for (Element elementParent = parent(); elementParent != null && !elementParent.nameIs("#root"); elementParent = elementParent.parent()) {
            elements.add(elementParent);
        }
        return elements;
    }

    public final Element child(int index) {
        Validate.INSTANCE.isTrue(index >= 0, "Index must be >= 0");
        List<Element> listCachedChildren = cachedChildren();
        if (listCachedChildren != null) {
            return listCachedChildren.get(index);
        }
        int size = this.childNodes.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Node node = this.childNodes.get(i2);
            if (node instanceof Element) {
                int i3 = i + 1;
                if (i == index) {
                    return (Element) node;
                }
                i = i3;
            }
        }
        throw new IndexOutOfBoundsException("No child at index: " + index);
    }

    public final int childrenSize() {
        if (childNodeSize() == 0) {
            return 0;
        }
        return childElementsList().size();
    }

    public final Elements children() {
        return new Elements(childElementsList());
    }

    public final List<Element> childElementsList() {
        Object objInvoke;
        if (childNodeSize() == 0) {
            return EmptyChildren;
        }
        Object obj = this.lock;
        Function0 function0 = new Function0() { // from class: com.fleeksoft.ksoup.nodes.Element$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Element.childElementsList$lambda$0(this.f$0);
            }
        };
        synchronized (obj) {
            objInvoke = function0.invoke();
        }
        return (List) objInvoke;
    }

    static final List childElementsList$lambda$0(Element element) {
        List<Element> listCachedChildren = element.cachedChildren();
        if (listCachedChildren != null) {
            return listCachedChildren;
        }
        Companion.NodeList nodeList = element.childNodes;
        ArrayList arrayList = new ArrayList();
        for (Node node : nodeList) {
            if (node instanceof Element) {
                arrayList.add(node);
            }
        }
        ArrayList arrayList2 = arrayList;
        element.stashChildren(arrayList2);
        return arrayList2;
    }

    public final List<Element> cachedChildren() {
        Attributes attributes = this.attributes;
        if (attributes != null) {
            Intrinsics.checkNotNull(attributes);
            if (attributes.hasUserData()) {
                Attributes attributes2 = this.attributes;
                Intrinsics.checkNotNull(attributes2);
                Map<String, Object> mapUserData = attributes2.userData();
                Object obj = mapUserData.get(childElsKey);
                WeakReference weakReference = obj instanceof WeakReference ? (WeakReference) obj : null;
                List<Element> list = weakReference != null ? (List) weakReference.get() : null;
                if (list != null) {
                    Object obj2 = mapUserData.get(childElsMod);
                    Integer num = obj2 instanceof Integer ? (Integer) obj2 : null;
                    if (num != null) {
                        if (num.intValue() == this.childNodes.getModCount()) {
                            return list;
                        }
                    }
                }
            }
        }
        return null;
    }

    private final void stashChildren(List<? extends Element> els) {
        Map<String, Object> mapUserData = attributes().userData();
        mapUserData.put(childElsKey, new WeakReference(els));
        mapUserData.put(childElsMod, Integer.valueOf(this.childNodes.getModCount()));
    }

    public final Sequence<Element> stream() {
        return NodeUtils.INSTANCE.stream(this, Reflection.getOrCreateKotlinClass(Element.class));
    }

    private final /* synthetic */ <T> List<T> filterNodes(KClass<T> clazz) {
        Companion.NodeList nodeList = this.childNodes;
        ArrayList arrayList = new ArrayList();
        for (Node node : nodeList) {
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (node instanceof Object) {
                arrayList.add(node);
            }
        }
        return arrayList;
    }

    public final List<TextNode> textNodes() {
        Companion.NodeList nodeList = this.childNodes;
        ArrayList arrayList = new ArrayList();
        for (Node node : nodeList) {
            if (node instanceof TextNode) {
                arrayList.add(node);
            }
        }
        return arrayList;
    }

    public final List<DataNode> dataNodes() {
        Companion.NodeList nodeList = this.childNodes;
        ArrayList arrayList = new ArrayList();
        for (Node node : nodeList) {
            if (node instanceof DataNode) {
                arrayList.add(node);
            }
        }
        return arrayList;
    }

    public final Elements select(String cssQuery) {
        Intrinsics.checkNotNullParameter(cssQuery, "cssQuery");
        return Selector.INSTANCE.select(cssQuery, this);
    }

    public final Elements select(Evaluator evaluator) {
        Intrinsics.checkNotNullParameter(evaluator, "evaluator");
        return Selector.INSTANCE.select(evaluator, this);
    }

    public final Sequence<Element> selectStream(String cssQuery) {
        Intrinsics.checkNotNullParameter(cssQuery, "cssQuery");
        return Selector.INSTANCE.selectStream(cssQuery, this);
    }

    public final Element selectFirst(String cssQuery) {
        Intrinsics.checkNotNullParameter(cssQuery, "cssQuery");
        return Selector.INSTANCE.selectFirst(cssQuery, this);
    }

    public final Element selectFirst(Evaluator evaluator) {
        Intrinsics.checkNotNullParameter(evaluator, "evaluator");
        return Collector.INSTANCE.findFirst(evaluator, this);
    }

    public final Element expectFirst(String cssQuery) {
        String str;
        Intrinsics.checkNotNullParameter(cssQuery, "cssQuery");
        Validate validate = Validate.INSTANCE;
        Element elementSelectFirst = Selector.INSTANCE.selectFirst(cssQuery, this);
        if (parent() != null) {
            str = "No elements matched the query '" + cssQuery + "' on element '" + tagName() + "'.";
        } else {
            str = "No elements matched the query '" + cssQuery + "' in the document.";
        }
        return (Element) validate.expectNotNull(elementSelectFirst, str);
    }

    public final Nodes<Node> selectNodes(Evaluator evaluator) {
        Intrinsics.checkNotNullParameter(evaluator, "evaluator");
        return selectNodes(evaluator, Reflection.getOrCreateKotlinClass(Node.class));
    }

    public final Nodes<Node> selectNodes(String cssQuery) {
        Intrinsics.checkNotNullParameter(cssQuery, "cssQuery");
        return selectNodes(cssQuery, Reflection.getOrCreateKotlinClass(Node.class));
    }

    public final <T extends Node> Nodes<T> selectNodes(Evaluator evaluator, KClass<T> type) {
        Intrinsics.checkNotNullParameter(evaluator, "evaluator");
        Intrinsics.checkNotNullParameter(type, "type");
        return Collector.INSTANCE.collectNodes(evaluator, this, type);
    }

    public final <T extends Node> Nodes<T> selectNodes(String cssQuery, KClass<T> type) {
        Intrinsics.checkNotNullParameter(cssQuery, "cssQuery");
        Intrinsics.checkNotNullParameter(type, "type");
        Validate.INSTANCE.notEmpty(cssQuery);
        return selectNodes(Selector.INSTANCE.evaluatorOf(cssQuery), type);
    }

    public final <T extends Node> T selectFirstNode(String cssQuery, KClass<T> type) {
        Intrinsics.checkNotNullParameter(cssQuery, "cssQuery");
        Intrinsics.checkNotNullParameter(type, "type");
        return (T) selectFirstNode(Selector.INSTANCE.evaluatorOf(cssQuery), type);
    }

    public final <T extends Node> T selectFirstNode(Evaluator evaluator, KClass<T> type) {
        Intrinsics.checkNotNullParameter(evaluator, "evaluator");
        Intrinsics.checkNotNullParameter(type, "type");
        return (T) Collector.INSTANCE.findFirstNode(evaluator, this, type);
    }

    public final <T extends Node> T expectFirstNode(String cssQuery, KClass<T> type) {
        String str;
        Intrinsics.checkNotNullParameter(cssQuery, "cssQuery");
        Intrinsics.checkNotNullParameter(type, "type");
        Validate validate = Validate.INSTANCE;
        Node nodeSelectFirstNode = selectFirstNode(cssQuery, type);
        if (parent() != null) {
            str = "No nodes matched the query '" + cssQuery + "' on element '" + tagName() + "'.";
        } else {
            str = "No nodes matched the query '" + cssQuery + "' in the document.";
        }
        return (T) validate.expectNotNull(nodeSelectFirstNode, str);
    }

    public final boolean is(String cssQuery) {
        Intrinsics.checkNotNullParameter(cssQuery, "cssQuery");
        return is(Selector.INSTANCE.evaluatorOf(cssQuery));
    }

    public final boolean is(Evaluator evaluator) {
        Intrinsics.checkNotNull(evaluator);
        return evaluator.matches(root(), this);
    }

    public final Element closest(String cssQuery) {
        Intrinsics.checkNotNullParameter(cssQuery, "cssQuery");
        return closest(Selector.INSTANCE.evaluatorOf(cssQuery));
    }

    public final Element closest(Evaluator evaluator) {
        Intrinsics.checkNotNullParameter(evaluator, "evaluator");
        Element elementRoot = root();
        do {
            Intrinsics.checkNotNull(this);
            if (evaluator.matches(elementRoot, this)) {
                return this;
            }
            this = this.parent();
        } while (this != null);
        return null;
    }

    public final Element appendChild(Node child) {
        Intrinsics.checkNotNullParameter(child, "child");
        reparentChild(child);
        ensureChildNodes();
        this.childNodes.add(child);
        child.set_siblingIndex(this.childNodes.size() - 1);
        return this;
    }

    public final Element appendChildren(Collection<? extends Node> children) {
        Intrinsics.checkNotNullParameter(children, "children");
        insertChildren(-1, children);
        return this;
    }

    public final Element appendTo(Element parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        parent.appendChild(this);
        return this;
    }

    public final Element prependChild(Node child) {
        Intrinsics.checkNotNullParameter(child, "child");
        addChildren(0, child);
        return this;
    }

    public final Element prependChildren(Collection<? extends Node> children) {
        Intrinsics.checkNotNullParameter(children, "children");
        insertChildren(0, children);
        return this;
    }

    public final Element insertChildren(int index, Collection<? extends Node> children) {
        Intrinsics.checkNotNullParameter(children, "children");
        int iChildNodeSize = childNodeSize();
        if (index < 0) {
            index += iChildNodeSize + 1;
        }
        Validate.INSTANCE.isTrue(index >= 0 && index <= iChildNodeSize, "Insert position out of bounds.");
        Node[] nodeArr = (Node[]) children.toArray(new Node[0]);
        addChildren(index, (Node[]) Arrays.copyOf(nodeArr, nodeArr.length));
        return this;
    }

    public final Element insertChildren(int index, Node... children) {
        Intrinsics.checkNotNullParameter(children, "children");
        int iChildNodeSize = childNodeSize();
        if (index < 0) {
            index += iChildNodeSize + 1;
        }
        Validate validate = Validate.INSTANCE;
        boolean z = false;
        if (index >= 0 && index <= iChildNodeSize) {
            z = true;
        }
        validate.isTrue(z, "Insert position out of bounds.");
        addChildren(index, (Node[]) Arrays.copyOf(children, children.length));
        return this;
    }

    public static /* synthetic */ Element appendElement$default(Element element, String str, String str2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: appendElement");
        }
        if ((i & 2) != 0) {
            str2 = element.tag.namespace();
        }
        return element.appendElement(str, str2);
    }

    public final Element appendElement(String tagName, String namespace) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        Parser rVar = NodeUtils.INSTANCE.parser(this);
        Element element = new Element(rVar.tagSet().valueOf(tagName, namespace, rVar.getSettings()), baseUri());
        appendChild(element);
        return element;
    }

    public static /* synthetic */ Element prependElement$default(Element element, String str, String str2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: prependElement");
        }
        if ((i & 2) != 0) {
            str2 = element.tag.namespace();
        }
        return element.prependElement(str, str2);
    }

    public final Element prependElement(String tagName, String namespace) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        Parser rVar = NodeUtils.INSTANCE.parser(this);
        Element element = new Element(rVar.tagSet().valueOf(tagName, namespace, rVar.getSettings()), baseUri());
        prependChild(element);
        return element;
    }

    public final Element appendText(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        appendChild(new TextNode(text));
        return this;
    }

    public final Element prependText(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        prependChild(new TextNode(text));
        return this;
    }

    public final Element append(String html) {
        Intrinsics.checkNotNullParameter(html, "html");
        Node[] nodeArr = (Node[]) NodeUtils.INSTANCE.parser(this).parseFragmentInput(html, this, baseUri()).toArray(new Node[0]);
        addChildren((Node[]) Arrays.copyOf(nodeArr, nodeArr.length));
        return this;
    }

    public final Element prepend(String html) {
        Intrinsics.checkNotNullParameter(html, "html");
        Node[] nodeArr = (Node[]) NodeUtils.INSTANCE.parser(this).parseFragmentInput(html, this, baseUri()).toArray(new Node[0]);
        addChildren(0, (Node[]) Arrays.copyOf(nodeArr, nodeArr.length));
        return this;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Element before(String html) {
        Intrinsics.checkNotNullParameter(html, "html");
        Node nodeBefore = super.before(html);
        Intrinsics.checkNotNull(nodeBefore, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
        return (Element) nodeBefore;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Element before(Node node) {
        Intrinsics.checkNotNullParameter(node, "node");
        Node nodeBefore = super.before(node);
        Intrinsics.checkNotNull(nodeBefore, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
        return (Element) nodeBefore;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Element after(String html) {
        Intrinsics.checkNotNullParameter(html, "html");
        Node nodeAfter = super.after(html);
        Intrinsics.checkNotNull(nodeAfter, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
        return (Element) nodeAfter;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Element after(Node node) {
        Intrinsics.checkNotNullParameter(node, "node");
        Node nodeAfter = super.after(node);
        Intrinsics.checkNotNull(nodeAfter, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
        return (Element) nodeAfter;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Element empty() {
        int size = this.childNodes.size();
        int i = 0;
        while (true) {
            Companion.NodeList nodeList = this.childNodes;
            if (i < size) {
                nodeList.get(i).set_parentNode(null);
                i++;
            } else {
                nodeList.clear();
                return this;
            }
        }
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Element wrap(String html) {
        Intrinsics.checkNotNullParameter(html, "html");
        Node nodeWrap = super.wrap(html);
        Intrinsics.checkNotNull(nodeWrap, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
        return (Element) nodeWrap;
    }

    private final String uniqueIdSelector(Document ownerDoc) {
        String strId = id();
        if (strId.length() != 0) {
            String str = "#" + TokenQueue.INSTANCE.escapeCssIdentifier(strId);
            if (ownerDoc != null) {
                Elements elementsSelect = ownerDoc.select(str);
                if (elementsSelect.size() != 1 || elementsSelect.get(0) != this) {
                    return "";
                }
            }
            return str;
        }
        return "";
    }

    public final String cssSelector() {
        Document documentOwnerDocument = ownerDocument();
        String strUniqueIdSelector = uniqueIdSelector(documentOwnerDocument);
        if (strUniqueIdSelector.length() != 0) {
            return strUniqueIdSelector;
        }
        StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
        while (this != null && !(this instanceof Document)) {
            String strUniqueIdSelector2 = this.uniqueIdSelector(documentOwnerDocument);
            if (strUniqueIdSelector2.length() != 0) {
                sbBorrowBuilder.insert(0, strUniqueIdSelector2);
                break;
            }
            sbBorrowBuilder.insert(0, this.cssSelectorComponent());
            this = this.parent();
        }
        return StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder);
    }

    private final String cssSelectorComponent() {
        String strReplace$default = StringsKt.replace$default(TokenQueue.INSTANCE.escapeCssIdentifier(tagName()), "\\:", "|", false, 4, (Object) null);
        StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
        sbBorrowBuilder.append(strReplace$default);
        Intrinsics.checkNotNullExpressionValue(sbBorrowBuilder, "append(...)");
        StringUtil.StringJoiner stringJoiner = new StringUtil.StringJoiner(".");
        Iterator<String> it = classNames().iterator();
        while (it.hasNext()) {
            stringJoiner.add(TokenQueue.INSTANCE.escapeCssIdentifier(it.next()));
        }
        String strComplete = stringJoiner.complete();
        if (strComplete.length() > 0) {
            sbBorrowBuilder.append('.');
            sbBorrowBuilder.append(strComplete);
        }
        Element elementParent = parent();
        if (elementParent == null || (elementParent instanceof Document)) {
            return StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder);
        }
        sbBorrowBuilder.insert(0, " > ");
        String string = sbBorrowBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        if (elementParent.select(string).size() > 1) {
            sbBorrowBuilder.append(":nth-child(" + (elementSiblingIndex() + 1) + ")");
        }
        return StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Elements siblingElements() {
        int i = 1;
        ArrayList arrayList = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object[] objArr3 = 0;
        if (get_parentNode() == null) {
            return new Elements(arrayList, i, objArr3 == true ? 1 : 0);
        }
        Element element = get_parentNode();
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
        List<Element> listChildElementsList = element.childElementsList();
        Elements elements = new Elements(objArr2 == true ? 1 : 0, i, objArr == true ? 1 : 0);
        for (Element element2 : listChildElementsList) {
            if (!Intrinsics.areEqual(element2, this)) {
                elements.add(element2);
            }
        }
        return elements;
    }

    public final Elements nextElementSiblings() {
        return nextElementSiblings(true);
    }

    public final Elements previousElementSiblings() {
        return nextElementSiblings(false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Elements nextElementSiblings(boolean next) {
        Elements elements = new Elements(null, 1, 0 == true ? 1 : 0);
        if (get_parentNode() == null) {
            return elements;
        }
        elements.add(this);
        return next ? elements.nextAll() : elements.prevAll();
    }

    public final Element firstElementSibling() {
        Element elementParent = parent();
        return elementParent != null ? elementParent.firstElementChild() : this;
    }

    public final int elementSiblingIndex() {
        Element elementParent = parent();
        if (elementParent == null) {
            return 0;
        }
        return INSTANCE.indexInList(this, elementParent.childElementsList());
    }

    public final Element lastElementSibling() {
        Element elementParent = parent();
        return elementParent != null ? elementParent.lastElementChild() : this;
    }

    public final Element firstElementChild() {
        int size = this.childNodes.size();
        for (int i = 0; i < size; i++) {
            Node node = this.childNodes.get(i);
            if (node instanceof Element) {
                return (Element) node;
            }
        }
        return null;
    }

    public final Element lastElementChild() {
        int size = this.childNodes.size() - 1;
        if (size < 0) {
            return null;
        }
        while (true) {
            int i = size - 1;
            Node node = this.childNodes.get(size);
            if (node instanceof Element) {
                return (Element) node;
            }
            if (i < 0) {
                return null;
            }
            size = i;
        }
    }

    public final Elements getElementsByTag(String tagName) {
        Validate.INSTANCE.notEmpty(tagName);
        return Collector.INSTANCE.collect(new Evaluator.Tag(Normalizer.INSTANCE.normalize(tagName)), this);
    }

    public final Element getElementById(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        Validate.INSTANCE.notEmpty(id);
        return Collector.INSTANCE.findFirst(new Evaluator.Id(id), this);
    }

    public final Elements getElementsByClass(String className) {
        Intrinsics.checkNotNullParameter(className, "className");
        Validate.INSTANCE.notEmpty(className);
        return Collector.INSTANCE.collect(new Evaluator.Class(className), this);
    }

    public final Elements getElementsByAttribute(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        Validate.INSTANCE.notEmpty(key);
        String str = key;
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        return Collector.INSTANCE.collect(new Evaluator.Attribute(str.subSequence(i, length + 1).toString()), this);
    }

    public final Elements getElementsByAttributeStarting(String keyPrefix) {
        Intrinsics.checkNotNullParameter(keyPrefix, "keyPrefix");
        Validate.INSTANCE.notEmpty(keyPrefix);
        String str = keyPrefix;
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        return Collector.INSTANCE.collect(new Evaluator.AttributeStarting(str.subSequence(i, length + 1).toString()), this);
    }

    public final Elements getElementsByAttributeValue(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        return Collector.INSTANCE.collect(new Evaluator.AttributeWithValue(key, value), this);
    }

    public final Elements getElementsByAttributeValueNot(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        return Collector.INSTANCE.collect(new Evaluator.AttributeWithValueNot(key, value), this);
    }

    public final Elements getElementsByAttributeValueStarting(String key, String valuePrefix) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(valuePrefix, "valuePrefix");
        return Collector.INSTANCE.collect(new Evaluator.AttributeWithValueStarting(key, valuePrefix), this);
    }

    public final Elements getElementsByAttributeValueEnding(String key, String valueSuffix) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(valueSuffix, "valueSuffix");
        return Collector.INSTANCE.collect(new Evaluator.AttributeWithValueEnding(key, valueSuffix), this);
    }

    public final Elements getElementsByAttributeValueContaining(String key, String match) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(match, "match");
        return Collector.INSTANCE.collect(new Evaluator.AttributeWithValueContaining(key, match), this);
    }

    public final Elements getElementsByAttributeValueMatching(String key, Regex regex) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(regex, "regex");
        return Collector.INSTANCE.collect(new Evaluator.AttributeWithValueMatching(key, regex), this);
    }

    public final Elements getElementsByAttributeValueMatching(String key, String regex) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(regex, "regex");
        try {
            return Collector.INSTANCE.collect(new Evaluator.AttributeWithValueMatching(key, CoreFunctionsKt.jsSupportedRegex(regex)), this);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Pattern syntax error: " + regex, e);
        }
    }

    public final Elements getElementsByIndexLessThan(int index) {
        return Collector.INSTANCE.collect(new Evaluator.IndexLessThan(index), this);
    }

    public final Elements getElementsByIndexGreaterThan(int index) {
        return Collector.INSTANCE.collect(new Evaluator.IndexGreaterThan(index), this);
    }

    public final Elements getElementsByIndexEquals(int index) {
        return Collector.INSTANCE.collect(new Evaluator.IndexEquals(index), this);
    }

    public final Elements getElementsContainingText(String searchText) {
        Intrinsics.checkNotNullParameter(searchText, "searchText");
        return Collector.INSTANCE.collect(new Evaluator.ContainsText(searchText), this);
    }

    public final Elements getElementsContainingOwnText(String searchText) {
        Intrinsics.checkNotNullParameter(searchText, "searchText");
        return Collector.INSTANCE.collect(new Evaluator.ContainsOwnText(searchText), this);
    }

    public final Elements getElementsMatchingText(Regex regex) {
        Intrinsics.checkNotNullParameter(regex, "regex");
        return Collector.INSTANCE.collect(new Evaluator.Matches(regex), this);
    }

    public final Elements getElementsMatchingText(String regex) {
        Intrinsics.checkNotNullParameter(regex, "regex");
        try {
            return Collector.INSTANCE.collect(new Evaluator.Matches(CoreFunctionsKt.jsSupportedRegex(regex)), this);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Pattern syntax error: " + regex, e);
        }
    }

    public final Elements getElementsMatchingOwnText(Regex regex) {
        Intrinsics.checkNotNullParameter(regex, "regex");
        return Collector.INSTANCE.collect(new Evaluator.MatchesOwn(regex), this);
    }

    public final Elements getElementsMatchingOwnText(String regex) {
        Intrinsics.checkNotNullParameter(regex, "regex");
        try {
            return Collector.INSTANCE.collect(new Evaluator.MatchesOwn(CoreFunctionsKt.jsSupportedRegex(regex)), this);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Pattern syntax error: " + regex, e);
        }
    }

    public final Elements getAllElements() {
        return Collector.INSTANCE.collect(new Evaluator.AllElements(), this);
    }

    public final String text() {
        StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
        new TextAccumulator(sbBorrowBuilder).traverse(this);
        return StringsKt.trim((CharSequence) StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder)).toString();
    }

    /* JADX INFO: compiled from: Element.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0012\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Element$TextAccumulator;", "Lcom/fleeksoft/ksoup/select/NodeVisitor;", "accum", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "<init>", "(Ljava/lang/StringBuilder;)V", TtmlNode.TAG_HEAD, "", "node", "Lcom/fleeksoft/ksoup/nodes/Node;", "depth", "", "tail", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class TextAccumulator implements NodeVisitor {
        private final StringBuilder accum;

        public TextAccumulator(StringBuilder accum) {
            Intrinsics.checkNotNullParameter(accum, "accum");
            this.accum = accum;
        }

        @Override // com.fleeksoft.ksoup.select.NodeVisitor
        public /* bridge */ void traverse(Node node) {
            NodeVisitor.CC.$default$traverse(this, node);
        }

        @Override // com.fleeksoft.ksoup.select.NodeVisitor
        public void head(Node node, int depth) {
            Intrinsics.checkNotNullParameter(node, "node");
            if (!(node instanceof TextNode)) {
                if (!(node instanceof Element) || this.accum.length() <= 0) {
                    return;
                }
                if ((((Element) node).isBlock() || node.nameIs("br")) && !TextNode.INSTANCE.lastCharIsWhitespace$ksoup_release(this.accum)) {
                    this.accum.append(' ');
                    return;
                }
                return;
            }
            Element.INSTANCE.appendNormalisedText(this.accum, (TextNode) node);
        }

        @Override // com.fleeksoft.ksoup.select.NodeVisitor
        public void tail(Node node, int depth) {
            Intrinsics.checkNotNullParameter(node, "node");
            if (node instanceof Element) {
                Node nodeNextSibling = node.nextSibling();
                if (((Element) node).getTag().isInline()) {
                    return;
                }
                if (((nodeNextSibling instanceof TextNode) || ((nodeNextSibling instanceof Element) && ((Element) nodeNextSibling).getTag().isInline())) && !TextNode.INSTANCE.lastCharIsWhitespace$ksoup_release(this.accum)) {
                    this.accum.append(' ');
                }
            }
        }
    }

    public final String wholeText() {
        return INSTANCE.wholeTextOf(nodeStream());
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public String nodeValue() {
        return wholeOwnText();
    }

    public final String wholeOwnText() {
        return INSTANCE.wholeTextOf(CollectionsKt.asSequence(childNodes()));
    }

    public final String ownText() {
        StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
        ownText(sbBorrowBuilder);
        return StringsKt.trim((CharSequence) StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder)).toString();
    }

    private final void ownText(StringBuilder accum) {
        int iChildNodeSize = childNodeSize();
        for (int i = 0; i < iChildNodeSize; i++) {
            Node node = this.childNodes.get(i);
            if (!(node instanceof TextNode)) {
                if (node.nameIs("br") && !TextNode.INSTANCE.lastCharIsWhitespace$ksoup_release(accum)) {
                    accum.append(Stream.ID_UNKNOWN);
                }
            } else {
                INSTANCE.appendNormalisedText(accum, (TextNode) node);
            }
        }
    }

    public Element text(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        empty();
        if (tag().is(256)) {
            appendChild(new DataNode(text));
            return this;
        }
        appendChild(new TextNode(text));
        return this;
    }

    public final boolean hasText() {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        filter(new NodeFilter() { // from class: com.fleeksoft.ksoup.nodes.Element.hasText.1
            @Override // com.fleeksoft.ksoup.select.NodeFilter
            public /* bridge */ NodeFilter.FilterResult tail(Node node, int i) {
                return NodeFilter.CC.$default$tail(this, node, i);
            }

            @Override // com.fleeksoft.ksoup.select.NodeFilter
            public /* bridge */ void traverse(Node node) {
                NodeFilter.CC.$default$traverse(this, node);
            }

            @Override // com.fleeksoft.ksoup.select.NodeFilter
            public NodeFilter.FilterResult head(Node node, int depth) {
                Intrinsics.checkNotNullParameter(node, "node");
                if ((node instanceof TextNode) && !((TextNode) node).isBlank()) {
                    atomicBoolean.set(true);
                    return NodeFilter.FilterResult.STOP;
                }
                return NodeFilter.FilterResult.CONTINUE;
            }
        });
        return atomicBoolean.getValue();
    }

    public final String data() {
        final StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
        traverse(new NodeVisitor() { // from class: com.fleeksoft.ksoup.nodes.Element$$ExternalSyntheticLambda1
            @Override // com.fleeksoft.ksoup.select.NodeVisitor
            public final void head(Node node, int i) {
                Element.data$lambda$0(sbBorrowBuilder, node, i);
            }

            @Override // com.fleeksoft.ksoup.select.NodeVisitor
            public /* synthetic */ void tail(Node node, int i) {
                Intrinsics.checkNotNullParameter(node, "node");
            }

            @Override // com.fleeksoft.ksoup.select.NodeVisitor
            public /* synthetic */ void traverse(Node node) {
                NodeVisitor.CC.$default$traverse(this, node);
            }
        });
        return StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder);
    }

    static final void data$lambda$0(StringBuilder sb, Node childNode, int i) {
        Intrinsics.checkNotNullParameter(childNode, "childNode");
        if (childNode instanceof DataNode) {
            sb.append(((DataNode) childNode).getWholeData());
        } else if (childNode instanceof Comment) {
            sb.append(((Comment) childNode).getData());
        } else if (childNode instanceof CDataNode) {
            sb.append(((CDataNode) childNode).getWholeText());
        }
    }

    public final String className() {
        return StringsKt.trim((CharSequence) attr("class")).toString();
    }

    public final Set<String> classNames() {
        LinkedHashSet linkedHashSet = new LinkedHashSet(Regex.split$default(ClassSplit, className(), 0, 2, null));
        linkedHashSet.remove("");
        return linkedHashSet;
    }

    public final Element classNames(Set<String> classNames) {
        Intrinsics.checkNotNullParameter(classNames, "classNames");
        if (classNames.isEmpty()) {
            attributes().remove("class");
            return this;
        }
        attributes().put("class", StringUtil.INSTANCE.join(classNames, Stream.ID_UNKNOWN));
        return this;
    }

    public final boolean hasClass(String className) {
        String str;
        Intrinsics.checkNotNullParameter(className, "className");
        Attributes attributes = this.attributes;
        if (attributes == null) {
            return false;
        }
        Intrinsics.checkNotNull(attributes);
        String ignoreCase = attributes.getIgnoreCase("class");
        int length = ignoreCase.length();
        int length2 = className.length();
        if (length != 0 && length >= length2) {
            if (length == length2) {
                return StringsKt.equals(className, ignoreCase, true);
            }
            boolean z = false;
            int i = 0;
            int i2 = 0;
            while (i2 < length) {
                if (!CharsKt.isWhitespace(ignoreCase.charAt(i2))) {
                    ignoreCase = ignoreCase;
                    str = className;
                    if (!z) {
                        z = true;
                        i = i2;
                    }
                } else if (z) {
                    if (i2 - i == length2) {
                        int i3 = i;
                        String str2 = className;
                        boolean zRegionMatches = StringsKt.regionMatches(ignoreCase, i3, str2, 0, length2, true);
                        str = str2;
                        i = i3;
                        if (zRegionMatches) {
                            return true;
                        }
                    } else {
                        str = className;
                    }
                    z = false;
                } else {
                    ignoreCase = ignoreCase;
                    str = className;
                }
                i2++;
                className = str;
                ignoreCase = ignoreCase;
            }
            String str3 = ignoreCase;
            String str4 = className;
            if (z && length - i == length2) {
                return StringsKt.regionMatches(str3, i, str4, 0, length2, true);
            }
        }
        return false;
    }

    public final Element addClass(String className) {
        Intrinsics.checkNotNullParameter(className, "className");
        Set<String> setClassNames = classNames();
        setClassNames.add(className);
        classNames(setClassNames);
        return this;
    }

    public final Element removeClass(String className) {
        Intrinsics.checkNotNullParameter(className, "className");
        Set<String> setClassNames = classNames();
        setClassNames.remove(className);
        classNames(setClassNames);
        return this;
    }

    public final Element toggleClass(String className) {
        Intrinsics.checkNotNullParameter(className, "className");
        Set<String> setClassNames = classNames();
        if (setClassNames.contains(className)) {
            setClassNames.remove(className);
        } else {
            setClassNames.add(className);
        }
        classNames(setClassNames);
        return this;
    }

    public final String value() {
        return elementIs("textarea", "http://www.w3.org/1999/xhtml") ? text() : attr(ES6Iterator.VALUE_PROPERTY);
    }

    public final Element value(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (elementIs("textarea", "http://www.w3.org/1999/xhtml")) {
            text(value);
            return this;
        }
        attr(ES6Iterator.VALUE_PROPERTY, value);
        return this;
    }

    public final Range endSourceRange() {
        return Range.INSTANCE.of(this, false);
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public void outerHtmlHead$ksoup_release(QuietAppendable accum, Document.OutputSettings out) {
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(out, "out");
        String strSafeTagName = safeTagName(out.syntax());
        accum.append(Typography.less).append(strSafeTagName);
        Attributes attributes = this.attributes;
        if (attributes != null) {
            attributes.html(accum, out);
        }
        if (this.childNodes.isEmpty()) {
            boolean z = out.syntax() == Document.OutputSettings.Syntax.xml || !Intrinsics.areEqual(this.tag.namespace(), "http://www.w3.org/1999/xhtml");
            if (z && (this.tag.is(32) || (this.tag.isKnownTag() && (this.tag.isEmpty() || this.tag.isSelfClosing())))) {
                accum.append(" />");
                return;
            } else if (!z && this.tag.isEmpty()) {
                accum.append(Typography.greater);
                return;
            } else {
                accum.append("></").append(strSafeTagName).append(Typography.greater);
                return;
            }
        }
        accum.append(Typography.greater);
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public void outerHtmlTail$ksoup_release(QuietAppendable accum, Document.OutputSettings out) {
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(out, "out");
        if (this.childNodes.isEmpty()) {
            return;
        }
        accum.append("</").append(safeTagName(out.syntax())).append(Typography.greater);
    }

    private final String safeTagName(Document.OutputSettings.Syntax syntax) {
        return syntax == Document.OutputSettings.Syntax.xml ? Normalizer.INSTANCE.xmlSafeTagName(tagName()) : tagName();
    }

    public final String html() {
        StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
        html(sbBorrowBuilder);
        String strReleaseBuilder = StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder);
        if (!NodeUtils.INSTANCE.outputSettings(this).prettyPrint()) {
            return strReleaseBuilder;
        }
        String str = strReleaseBuilder;
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        return str.subSequence(i, length + 1).toString();
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public <T extends Appendable> T html(T appendable) {
        Intrinsics.checkNotNullParameter(appendable, "appendable");
        Node nodeFirstChild = firstChild();
        if (nodeFirstChild != null) {
            Printer printerPrinterFor = Printer.INSTANCE.printerFor(nodeFirstChild, QuietAppendable.INSTANCE.wrap(appendable));
            while (nodeFirstChild != null) {
                printerPrinterFor.traverse(nodeFirstChild);
                nodeFirstChild = nodeFirstChild.nextSibling();
            }
        }
        return appendable;
    }

    public final Element html(String html) {
        Intrinsics.checkNotNullParameter(html, "html");
        empty();
        append(html);
        return this;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Node createClone$ksoup_release() {
        Element element = new Element(this.tag, this._baseUri);
        element.attributes = this.attributes;
        element.childNodes = this.childNodes;
        return element;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fleeksoft.ksoup.nodes.Node, com.fleeksoft.ksoup.ported.KCloneable
    /* JADX INFO: renamed from: clone */
    public Node clone2() {
        Node nodeClone = super.clone2();
        Intrinsics.checkNotNull(nodeClone, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
        return (Element) nodeClone;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fleeksoft.ksoup.nodes.Node
    public Element doClone(Node parent) {
        Node nodeDoClone = super.doClone(parent);
        Intrinsics.checkNotNull(nodeDoClone, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
        Element element = (Element) nodeDoClone;
        Companion.NodeList nodeList = new Companion.NodeList(this.childNodes.size());
        element.childNodes = nodeList;
        nodeList.addAll(this.childNodes);
        Attributes attributes = this.attributes;
        if (attributes != null) {
            Attributes attributesClone = attributes != null ? attributes.clone2() : null;
            element.attributes = attributesClone;
            Intrinsics.checkNotNull(attributesClone);
            attributesClone.userData(childElsKey, null);
        }
        return element;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Element shallowClone() {
        String strBaseUri = baseUri();
        if (strBaseUri.length() == 0) {
            strBaseUri = null;
        }
        String str = strBaseUri;
        Tag tag = this.tag;
        Attributes attributes = this.attributes;
        return new Element(tag, str, attributes != null ? attributes.clone2() : null);
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Element clearAttributes() {
        if (this.attributes != null) {
            super.clearAttributes();
            Attributes attributes = this.attributes;
            Intrinsics.checkNotNull(attributes);
            if (attributes.getSize() == 0) {
                this.attributes = null;
            }
        }
        return this;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Element removeAttr(String attributeKey) {
        Intrinsics.checkNotNullParameter(attributeKey, "attributeKey");
        Node nodeRemoveAttr = super.removeAttr(attributeKey);
        Intrinsics.checkNotNull(nodeRemoveAttr, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
        return (Element) nodeRemoveAttr;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Element root() {
        Node nodeRoot = super.root();
        Intrinsics.checkNotNull(nodeRoot, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
        return (Element) nodeRoot;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Element traverse(NodeVisitor nodeVisitor) {
        Intrinsics.checkNotNullParameter(nodeVisitor, "nodeVisitor");
        Node nodeTraverse = super.traverse(nodeVisitor);
        Intrinsics.checkNotNull(nodeTraverse, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
        return (Element) nodeTraverse;
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Element forEachNode(Consumer<? super Node> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        Node nodeForEachNode = super.forEachNode(action);
        Intrinsics.checkNotNull(nodeForEachNode, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
        return (Element) nodeForEachNode;
    }

    public final void forEach(Function1<? super Element, Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        Iterator<Element> it = stream().iterator();
        while (it.hasNext()) {
            action.invoke(it.next());
        }
    }

    @Override // java.lang.Iterable
    public Iterator<Element> iterator() {
        return new NodeIterator(this, Reflection.getOrCreateKotlinClass(Element.class));
    }

    @Override // com.fleeksoft.ksoup.nodes.Node
    public Element filter(NodeFilter nodeFilter) {
        Intrinsics.checkNotNullParameter(nodeFilter, "nodeFilter");
        Node nodeFilter2 = super.filter(nodeFilter);
        Intrinsics.checkNotNull(nodeFilter2, "null cannot be cast to non-null type com.fleeksoft.ksoup.nodes.Element");
        return (Element) nodeFilter2;
    }

    public final void reindexChildren() {
        int size = this.childNodes.size();
        int i = 0;
        while (true) {
            Companion.NodeList nodeList = this.childNodes;
            if (i < size) {
                nodeList.get(i).set_siblingIndex(i);
                i++;
            } else {
                nodeList.setValidChildren(true);
                return;
            }
        }
    }

    public final void invalidateChildren() {
        this.childNodes.setValidChildren(false);
    }

    public final boolean hasValidChildren() {
        return this.childNodes.getValidChildren();
    }

    /* JADX INFO: compiled from: Element.kt */
    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001'B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0005J*\u0010\u0014\u001a\u00020\u0015\"\n\b\u0000\u0010\u0016*\u0004\u0018\u00010\t2\u0006\u0010\u0017\u001a\u00020\t2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00160\bH\u0002J\u0016\u0010\u0019\u001a\u00020\u00052\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002J\u001c\u0010\u001d\u001a\u00020\u001e2\n\u0010\u001f\u001a\u00060 j\u0002`!2\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u001cR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006("}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Element$Companion;", "", "<init>", "()V", "childElsKey", "", "childElsMod", "EmptyChildren", "", "Lcom/fleeksoft/ksoup/nodes/Element;", "EmptyNodeList", "Lcom/fleeksoft/ksoup/nodes/Element$Companion$NodeList;", "ClassSplit", "Lkotlin/text/Regex;", "BaseUriKey", "getBaseUriKey", "()Ljava/lang/String;", "searchUpForAttribute", "start", "key", "indexInList", "", ExifInterface.LONGITUDE_EAST, "search", "elements", "wholeTextOf", "nodeStream", "Lkotlin/sequences/Sequence;", "Lcom/fleeksoft/ksoup/nodes/Node;", "appendNormalisedText", "", "accum", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "textNode", "Lcom/fleeksoft/ksoup/nodes/TextNode;", "preserveWhitespace", "", "node", "NodeList", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getBaseUriKey() {
            return Element.BaseUriKey;
        }

        /* JADX INFO: compiled from: Element.kt */
        @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010)\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010+\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012H\u0096\u0082\u0004J\u0012\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0002H\u0096\u0080\bJ\u0012\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0002H\u0096\u0080\bJ\u0018\u0010\u0017\u001a\u00020\b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u0019H\u0096\u0080\bJ \u0010\u0017\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u00042\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u0019H\u0096\u0080\bJ\u0018\u0010\u001b\u001a\u00020\b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u0019H\u0096\u0080\bJ\u0018\u0010\u001c\u001a\u00020\b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u0019H\u0096\u0080\bJ\n\u0010 \u001a\u00020\bH\u0096\u0080\u0004J\u0012\u0010!\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0002H\u0096\u0082\u0004J\u0018\u0010\"\u001a\u00020\b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u0019H\u0096\u0080\u0004J\u0012\u0010#\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0004H\u0096\u0082\u0004J\u0012\u0010$\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0002H\u0096\u0080\u0004J\u0012\u0010%\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0002H\u0096\u0080\u0004J\u0006\u0010\u0010\u001a\u00020\u0004J\u0006\u0010&\u001a\u00020'J\n\u0010(\u001a\u00020'H\u0096\u0080\u0004J\u001a\u0010)\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0002H\u0096\u0082\bJ\u001a\u0010\u0013\u001a\u00020'2\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0002H\u0096\u0080\u0004J\u0012\u0010*\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0004H\u0096\u0080\bJ\u0010\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00020,H\u0096\u0080\u0004J\u0018\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00020,2\u0006\u0010\u001a\u001a\u00020\u0004H\u0096\u0080\u0004J \u0010-\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010.\u001a\u00020\u00042\u0006\u0010/\u001a\u00020\u0004H\u0096\u0080\u0004R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u000ej\b\u0012\u0004\u0012\u00020\u0002`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0015\u0010\u001d\u001a\u00020\u00048VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u00060"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Element$Companion$NodeList;", "", "Lcom/fleeksoft/ksoup/nodes/Node;", "initialCapacity", "", "<init>", "(I)V", "validChildren", "", "getValidChildren", "()Z", "setValidChildren", "(Z)V", "list", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "modCount", "iterator", "", "add", "node", "remove", "element", "addAll", "elements", "", "index", "removeAll", "retainAll", ContentDisposition.Parameters.Size, "getSize", "()I", "isEmpty", "contains", "containsAll", "get", "indexOf", "lastIndexOf", "incrementMod", "", "clear", "set", "removeAt", "listIterator", "", "subList", "fromIndex", "toIndex", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class NodeList implements List<Node>, KMutableList {
            private final ArrayList<Node> list;
            private int modCount;
            private boolean validChildren = true;

            @Override // java.util.List, java.util.Collection
            public Object[] toArray() {
                return CollectionToArray.toArray(this);
            }

            @Override // java.util.List, java.util.Collection
            public <T> T[] toArray(T[] array) {
                Intrinsics.checkNotNullParameter(array, "array");
                return (T[]) CollectionToArray.toArray(this, array);
            }

            public NodeList(int i) {
                this.list = new ArrayList<>(i);
            }

            @Override // java.util.List, java.util.Collection
            public final /* bridge */ boolean contains(Object obj) {
                if (obj instanceof Node) {
                    return contains((Node) obj);
                }
                return false;
            }

            @Override // java.util.List
            public final /* bridge */ int indexOf(Object obj) {
                if (obj instanceof Node) {
                    return indexOf((Node) obj);
                }
                return -1;
            }

            @Override // java.util.List
            public final /* bridge */ int lastIndexOf(Object obj) {
                if (obj instanceof Node) {
                    return lastIndexOf((Node) obj);
                }
                return -1;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.List
            public final /* bridge */ Node remove(int i) {
                return removeAt(i);
            }

            @Override // java.util.List, java.util.Collection
            public final /* bridge */ boolean remove(Object obj) {
                if (obj instanceof Node) {
                    return remove((Node) obj);
                }
                return false;
            }

            @Override // java.util.List, java.util.Collection
            public final /* bridge */ int size() {
                return getSize();
            }

            public final boolean getValidChildren() {
                return this.validChildren;
            }

            public final void setValidChildren(boolean z) {
                this.validChildren = z;
            }

            @Override // java.util.List, java.util.Collection, java.lang.Iterable
            public Iterator<Node> iterator() {
                return CollectionsKt.toMutableList((Collection) this.list).iterator();
            }

            @Override // java.util.List, java.util.Collection
            public boolean add(Node node) {
                Intrinsics.checkNotNullParameter(node, "node");
                this.modCount++;
                return this.list.add(node);
            }

            public boolean remove(Node element) {
                Intrinsics.checkNotNullParameter(element, "element");
                boolean zRemove = this.list.remove(element);
                if (zRemove) {
                    this.modCount++;
                }
                return zRemove;
            }

            @Override // java.util.List, java.util.Collection
            public boolean addAll(Collection<? extends Node> elements) {
                Intrinsics.checkNotNullParameter(elements, "elements");
                boolean zAddAll = this.list.addAll(elements);
                if (zAddAll) {
                    this.modCount++;
                }
                return zAddAll;
            }

            @Override // java.util.List
            public boolean addAll(int index, Collection<? extends Node> elements) {
                Intrinsics.checkNotNullParameter(elements, "elements");
                boolean zAddAll = this.list.addAll(index, elements);
                if (zAddAll) {
                    this.modCount++;
                }
                return zAddAll;
            }

            @Override // java.util.List, java.util.Collection
            public boolean removeAll(Collection<?> elements) {
                Intrinsics.checkNotNullParameter(elements, "elements");
                boolean zRemoveAll = this.list.removeAll(elements);
                if (zRemoveAll) {
                    this.modCount++;
                }
                return zRemoveAll;
            }

            @Override // java.util.List, java.util.Collection
            public boolean retainAll(Collection<?> elements) {
                Intrinsics.checkNotNullParameter(elements, "elements");
                boolean zRetainAll = this.list.retainAll(elements);
                if (zRetainAll) {
                    this.modCount++;
                }
                return zRetainAll;
            }

            public int getSize() {
                return this.list.size();
            }

            @Override // java.util.List, java.util.Collection
            public boolean isEmpty() {
                return this.list.isEmpty();
            }

            public boolean contains(Node element) {
                Intrinsics.checkNotNullParameter(element, "element");
                return this.list.contains(element);
            }

            @Override // java.util.List, java.util.Collection
            public boolean containsAll(Collection<?> elements) {
                Intrinsics.checkNotNullParameter(elements, "elements");
                return this.list.containsAll(elements);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.List
            public Node get(int index) {
                Node node = this.list.get(index);
                Intrinsics.checkNotNullExpressionValue(node, "get(...)");
                return node;
            }

            public int indexOf(Node element) {
                Intrinsics.checkNotNullParameter(element, "element");
                return this.list.indexOf(element);
            }

            public int lastIndexOf(Node element) {
                Intrinsics.checkNotNullParameter(element, "element");
                return this.list.lastIndexOf(element);
            }

            /* JADX INFO: renamed from: modCount, reason: from getter */
            public final int getModCount() {
                return this.modCount;
            }

            public final void incrementMod() {
                this.modCount++;
            }

            @Override // java.util.List, java.util.Collection
            public void clear() {
                this.modCount++;
                this.list.clear();
            }

            @Override // java.util.List
            public Node set(int index, Node element) {
                Intrinsics.checkNotNullParameter(element, "element");
                this.modCount++;
                Node node = this.list.set(index, element);
                Intrinsics.checkNotNullExpressionValue(node, "set(...)");
                return node;
            }

            @Override // java.util.List
            public void add(int index, Node element) {
                Intrinsics.checkNotNullParameter(element, "element");
                this.modCount++;
                this.list.add(index, element);
            }

            public Node removeAt(int index) {
                this.modCount++;
                Node nodeRemove = this.list.remove(index);
                Intrinsics.checkNotNullExpressionValue(nodeRemove, "removeAt(...)");
                return nodeRemove;
            }

            @Override // java.util.List
            public ListIterator<Node> listIterator() {
                ListIterator<Node> listIterator = this.list.listIterator();
                Intrinsics.checkNotNullExpressionValue(listIterator, "listIterator(...)");
                return listIterator;
            }

            @Override // java.util.List
            public ListIterator<Node> listIterator(int index) {
                ListIterator<Node> listIterator = this.list.listIterator(index);
                Intrinsics.checkNotNullExpressionValue(listIterator, "listIterator(...)");
                return listIterator;
            }

            @Override // java.util.List
            public List<Node> subList(int fromIndex, int toIndex) {
                List<Node> listSubList = this.list.subList(fromIndex, toIndex);
                Intrinsics.checkNotNullExpressionValue(listSubList, "subList(...)");
                return listSubList;
            }
        }

        public final String searchUpForAttribute(Element start, String key) {
            Intrinsics.checkNotNullParameter(start, "start");
            Intrinsics.checkNotNullParameter(key, "key");
            while (start != null) {
                Attributes attributes = start.getAttributes();
                if (attributes != null && attributes.hasKey(key)) {
                    Attributes attributes2 = start.getAttributes();
                    Intrinsics.checkNotNull(attributes2);
                    return attributes2.get(key);
                }
                start = start.parent();
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final <E extends Element> int indexInList(Element search, List<? extends E> elements) {
            int size = elements.size();
            for (int i = 0; i < size; i++) {
                if (elements.get(i) == search) {
                    return i;
                }
            }
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String wholeTextOf(Sequence<? extends Node> nodeStream) {
            return SequencesKt.joinToString$default(SequencesKt.map(nodeStream, new Function1() { // from class: com.fleeksoft.ksoup.nodes.Element$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Element.Companion.wholeTextOf$lambda$0((Node) obj);
                }
            }), "", null, null, 0, null, null, 62, null);
        }

        static final String wholeTextOf$lambda$0(Node node) {
            Intrinsics.checkNotNullParameter(node, "node");
            if (node instanceof TextNode) {
                return ((TextNode) node).getWholeText();
            }
            return node.nameIs("br") ? "\n" : "";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void appendNormalisedText(StringBuilder accum, TextNode textNode) {
            String wholeText = textNode.getWholeText();
            if (preserveWhitespace(textNode.get_parentNode()) || (textNode instanceof CDataNode)) {
                accum.append(wholeText);
            } else {
                StringUtil.INSTANCE.appendNormalisedWhitespace(accum, wholeText, TextNode.INSTANCE.lastCharIsWhitespace$ksoup_release(accum));
            }
        }

        public final boolean preserveWhitespace(Node node) {
            if (node instanceof Element) {
                Element elementParent = (Element) node;
                int i = 0;
                while (!elementParent.getTag().preserveWhitespace()) {
                    elementParent = elementParent.parent();
                    i++;
                    if (i >= 6 || elementParent == null) {
                    }
                }
                return true;
            }
            return false;
        }
    }
}
