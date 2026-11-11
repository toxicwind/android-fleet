package io.ktor.http;

import java.util.Arrays;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: HttpHeaders.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0003\bÏ\u0001\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000e\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0004¢\u0006\u0004\b\u000e\u0010\fJ\u000f\u0010\u000f\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0011\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0012\u0010\u0010J\u000f\u0010\u0013\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0013\u0010\u0010J\u000f\u0010\u0014\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0014\u0010\u0010J\u000f\u0010\u0015\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0015\u0010\u0010J\u000f\u0010\u0016\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0016\u0010\u0010J\u000f\u0010\u0017\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0017\u0010\u0010J\u000f\u0010\u0018\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0018\u0010\u0010J\u000f\u0010\u0019\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0019\u0010\u0010J\u000f\u0010\u001a\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u001a\u0010\u0010J\u000f\u0010\u001b\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u001b\u0010\u0010J\u000f\u0010\u001c\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u001c\u0010\u0010J\u000f\u0010\u001d\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u001d\u0010\u0010J\u000f\u0010\u001e\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u001e\u0010\u0010J\u000f\u0010\u001f\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u001f\u0010\u0010J\u000f\u0010 \u001a\u00020\u0004H\u0007¢\u0006\u0004\b \u0010\u0010J\u000f\u0010!\u001a\u00020\u0004H\u0007¢\u0006\u0004\b!\u0010\u0010J\u000f\u0010\"\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\"\u0010\u0010J\u000f\u0010#\u001a\u00020\u0004H\u0007¢\u0006\u0004\b#\u0010\u0010J\u000f\u0010$\u001a\u00020\u0004H\u0007¢\u0006\u0004\b$\u0010\u0010J\u000f\u0010%\u001a\u00020\u0004H\u0007¢\u0006\u0004\b%\u0010\u0010J\u000f\u0010&\u001a\u00020\u0004H\u0007¢\u0006\u0004\b&\u0010\u0010J\u000f\u0010'\u001a\u00020\u0004H\u0007¢\u0006\u0004\b'\u0010\u0010J\u000f\u0010(\u001a\u00020\u0004H\u0007¢\u0006\u0004\b(\u0010\u0010J\u000f\u0010)\u001a\u00020\u0004H\u0007¢\u0006\u0004\b)\u0010\u0010J\u000f\u0010*\u001a\u00020\u0004H\u0007¢\u0006\u0004\b*\u0010\u0010J\u000f\u0010+\u001a\u00020\u0004H\u0007¢\u0006\u0004\b+\u0010\u0010J\u000f\u0010,\u001a\u00020\u0004H\u0007¢\u0006\u0004\b,\u0010\u0010J\u000f\u0010-\u001a\u00020\u0004H\u0007¢\u0006\u0004\b-\u0010\u0010J\u000f\u0010.\u001a\u00020\u0004H\u0007¢\u0006\u0004\b.\u0010\u0010J\u000f\u0010/\u001a\u00020\u0004H\u0007¢\u0006\u0004\b/\u0010\u0010J\u000f\u00100\u001a\u00020\u0004H\u0007¢\u0006\u0004\b0\u0010\u0010J\u000f\u00101\u001a\u00020\u0004H\u0007¢\u0006\u0004\b1\u0010\u0010J\u000f\u00102\u001a\u00020\u0004H\u0007¢\u0006\u0004\b2\u0010\u0010J\u000f\u00103\u001a\u00020\u0004H\u0007¢\u0006\u0004\b3\u0010\u0010J\u000f\u00104\u001a\u00020\u0004H\u0007¢\u0006\u0004\b4\u0010\u0010J\u000f\u00105\u001a\u00020\u0004H\u0007¢\u0006\u0004\b5\u0010\u0010J\u000f\u00106\u001a\u00020\u0004H\u0007¢\u0006\u0004\b6\u0010\u0010J\u000f\u00107\u001a\u00020\u0004H\u0007¢\u0006\u0004\b7\u0010\u0010J\u000f\u00108\u001a\u00020\u0004H\u0007¢\u0006\u0004\b8\u0010\u0010J\u000f\u00109\u001a\u00020\u0004H\u0007¢\u0006\u0004\b9\u0010\u0010J\u000f\u0010:\u001a\u00020\u0004H\u0007¢\u0006\u0004\b:\u0010\u0010J\u000f\u0010;\u001a\u00020\u0004H\u0007¢\u0006\u0004\b;\u0010\u0010J\u000f\u0010<\u001a\u00020\u0004H\u0007¢\u0006\u0004\b<\u0010\u0010J\u000f\u0010=\u001a\u00020\u0004H\u0007¢\u0006\u0004\b=\u0010\u0010J\u000f\u0010>\u001a\u00020\u0004H\u0007¢\u0006\u0004\b>\u0010\u0010J\u000f\u0010?\u001a\u00020\u0004H\u0007¢\u0006\u0004\b?\u0010\u0010J\u000f\u0010@\u001a\u00020\u0004H\u0007¢\u0006\u0004\b@\u0010\u0010J\u000f\u0010A\u001a\u00020\u0004H\u0007¢\u0006\u0004\bA\u0010\u0010J\u000f\u0010B\u001a\u00020\u0004H\u0007¢\u0006\u0004\bB\u0010\u0010J\u000f\u0010C\u001a\u00020\u0004H\u0007¢\u0006\u0004\bC\u0010\u0010J\u000f\u0010D\u001a\u00020\u0004H\u0007¢\u0006\u0004\bD\u0010\u0010J\u000f\u0010E\u001a\u00020\u0004H\u0007¢\u0006\u0004\bE\u0010\u0010J\u000f\u0010F\u001a\u00020\u0004H\u0007¢\u0006\u0004\bF\u0010\u0010J\u000f\u0010G\u001a\u00020\u0004H\u0007¢\u0006\u0004\bG\u0010\u0010J\u000f\u0010H\u001a\u00020\u0004H\u0007¢\u0006\u0004\bH\u0010\u0010J\u000f\u0010I\u001a\u00020\u0004H\u0007¢\u0006\u0004\bI\u0010\u0010J\u000f\u0010J\u001a\u00020\u0004H\u0007¢\u0006\u0004\bJ\u0010\u0010J\u000f\u0010K\u001a\u00020\u0004H\u0007¢\u0006\u0004\bK\u0010\u0010J\u000f\u0010L\u001a\u00020\u0004H\u0007¢\u0006\u0004\bL\u0010\u0010J\u000f\u0010M\u001a\u00020\u0004H\u0007¢\u0006\u0004\bM\u0010\u0010J\u000f\u0010N\u001a\u00020\u0004H\u0007¢\u0006\u0004\bN\u0010\u0010J\u000f\u0010O\u001a\u00020\u0004H\u0007¢\u0006\u0004\bO\u0010\u0010J\u000f\u0010P\u001a\u00020\u0004H\u0007¢\u0006\u0004\bP\u0010\u0010J\u000f\u0010Q\u001a\u00020\u0004H\u0007¢\u0006\u0004\bQ\u0010\u0010J\u000f\u0010R\u001a\u00020\u0004H\u0007¢\u0006\u0004\bR\u0010\u0010J\u000f\u0010S\u001a\u00020\u0004H\u0007¢\u0006\u0004\bS\u0010\u0010J\u000f\u0010T\u001a\u00020\u0004H\u0007¢\u0006\u0004\bT\u0010\u0010J\u000f\u0010U\u001a\u00020\u0004H\u0007¢\u0006\u0004\bU\u0010\u0010J\u000f\u0010V\u001a\u00020\u0004H\u0007¢\u0006\u0004\bV\u0010\u0010J\u000f\u0010W\u001a\u00020\u0004H\u0007¢\u0006\u0004\bW\u0010\u0010J\u000f\u0010X\u001a\u00020\u0004H\u0007¢\u0006\u0004\bX\u0010\u0010J\u000f\u0010Y\u001a\u00020\u0004H\u0007¢\u0006\u0004\bY\u0010\u0010J\u000f\u0010Z\u001a\u00020\u0004H\u0007¢\u0006\u0004\bZ\u0010\u0010J\u000f\u0010[\u001a\u00020\u0004H\u0007¢\u0006\u0004\b[\u0010\u0010J\u000f\u0010\\\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\\\u0010\u0010J\u000f\u0010]\u001a\u00020\u0004H\u0007¢\u0006\u0004\b]\u0010\u0010J\u000f\u0010^\u001a\u00020\u0004H\u0007¢\u0006\u0004\b^\u0010\u0010J\u000f\u0010_\u001a\u00020\u0004H\u0007¢\u0006\u0004\b_\u0010\u0010J\u000f\u0010`\u001a\u00020\u0004H\u0007¢\u0006\u0004\b`\u0010\u0010J\u000f\u0010a\u001a\u00020\u0004H\u0007¢\u0006\u0004\ba\u0010\u0010J\u000f\u0010b\u001a\u00020\u0004H\u0007¢\u0006\u0004\bb\u0010\u0010J\u000f\u0010c\u001a\u00020\u0004H\u0007¢\u0006\u0004\bc\u0010\u0010J\u000f\u0010d\u001a\u00020\u0004H\u0007¢\u0006\u0004\bd\u0010\u0010J\u000f\u0010e\u001a\u00020\u0004H\u0007¢\u0006\u0004\be\u0010\u0010J\u000f\u0010f\u001a\u00020\u0004H\u0007¢\u0006\u0004\bf\u0010\u0010J\u000f\u0010g\u001a\u00020\u0004H\u0007¢\u0006\u0004\bg\u0010\u0010J\u000f\u0010h\u001a\u00020\u0004H\u0007¢\u0006\u0004\bh\u0010\u0010J\u000f\u0010i\u001a\u00020\u0004H\u0007¢\u0006\u0004\bi\u0010\u0010J\u000f\u0010j\u001a\u00020\u0004H\u0007¢\u0006\u0004\bj\u0010\u0010J\u000f\u0010k\u001a\u00020\u0004H\u0007¢\u0006\u0004\bk\u0010\u0010J\u000f\u0010l\u001a\u00020\u0004H\u0007¢\u0006\u0004\bl\u0010\u0010J\u000f\u0010m\u001a\u00020\u0004H\u0007¢\u0006\u0004\bm\u0010\u0010J\u000f\u0010n\u001a\u00020\u0004H\u0007¢\u0006\u0004\bn\u0010\u0010J\u000f\u0010o\u001a\u00020\u0004H\u0007¢\u0006\u0004\bo\u0010\u0010J\u000f\u0010p\u001a\u00020\u0004H\u0007¢\u0006\u0004\bp\u0010\u0010J\u000f\u0010q\u001a\u00020\u0004H\u0007¢\u0006\u0004\bq\u0010\u0010J\u000f\u0010r\u001a\u00020\u0004H\u0007¢\u0006\u0004\br\u0010\u0010R\u0014\u0010s\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\bs\u0010tR\u001a\u0010u\u001a\u00020\u00048\u0006X\u0087T¢\u0006\f\n\u0004\bu\u0010t\u0012\u0004\bv\u0010\u0003R\u0014\u0010w\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\bw\u0010tR\u0014\u0010x\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\bx\u0010tR\u0014\u0010y\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\by\u0010tR\u0014\u0010z\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\bz\u0010tR\u0014\u0010{\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b{\u0010tR\u0014\u0010|\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b|\u0010tR\u0014\u0010}\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b}\u0010tR\u0014\u0010~\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b~\u0010tR\u0014\u0010\u007f\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u007f\u0010tR\u0016\u0010\u0080\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0080\u0001\u0010tR\u0016\u0010\u0081\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0081\u0001\u0010tR\u0016\u0010\u0082\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0082\u0001\u0010tR\u0016\u0010\u0083\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0083\u0001\u0010tR\u0016\u0010\u0084\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0084\u0001\u0010tR\u0016\u0010\u0085\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0085\u0001\u0010tR\u0016\u0010\u0086\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0086\u0001\u0010tR\u0016\u0010\u0087\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0087\u0001\u0010tR\u0016\u0010\u0088\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0088\u0001\u0010tR\u0016\u0010\u0089\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0089\u0001\u0010tR\u0016\u0010\u008a\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u008a\u0001\u0010tR\u0016\u0010\u008b\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u008b\u0001\u0010tR\u0016\u0010\u008c\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u008c\u0001\u0010tR\u0016\u0010\u008d\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u008d\u0001\u0010tR\u0016\u0010\u008e\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u008e\u0001\u0010tR\u0016\u0010\u008f\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u008f\u0001\u0010tR\u0016\u0010\u0090\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0090\u0001\u0010tR\u0016\u0010\u0091\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0091\u0001\u0010tR\u0016\u0010\u0092\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0092\u0001\u0010tR\u0016\u0010\u0093\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0093\u0001\u0010tR\u0016\u0010\u0094\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0094\u0001\u0010tR\u0016\u0010\u0095\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0095\u0001\u0010tR\u0016\u0010\u0096\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0096\u0001\u0010tR\u0016\u0010\u0097\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0097\u0001\u0010tR\u0016\u0010\u0098\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0098\u0001\u0010tR\u0016\u0010\u0099\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u0099\u0001\u0010tR\u0016\u0010\u009a\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u009a\u0001\u0010tR\u0016\u0010\u009b\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u009b\u0001\u0010tR\u0016\u0010\u009c\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u009c\u0001\u0010tR\u0016\u0010\u009d\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u009d\u0001\u0010tR\u0016\u0010\u009e\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u009e\u0001\u0010tR\u0016\u0010\u009f\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u009f\u0001\u0010tR\u0016\u0010 \u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b \u0001\u0010tR\u0016\u0010¡\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b¡\u0001\u0010tR\u0016\u0010¢\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b¢\u0001\u0010tR\u0016\u0010£\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b£\u0001\u0010tR\u0016\u0010¤\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b¤\u0001\u0010tR\u0016\u0010¥\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b¥\u0001\u0010tR\u0016\u0010¦\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b¦\u0001\u0010tR\u0016\u0010§\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b§\u0001\u0010tR\u0016\u0010¨\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b¨\u0001\u0010tR\u0016\u0010©\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b©\u0001\u0010tR\u0016\u0010ª\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bª\u0001\u0010tR\u0016\u0010«\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b«\u0001\u0010tR\u0016\u0010¬\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b¬\u0001\u0010tR\u0016\u0010\u00ad\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b\u00ad\u0001\u0010tR\u0016\u0010®\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b®\u0001\u0010tR\u0016\u0010¯\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b¯\u0001\u0010tR\u0016\u0010°\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b°\u0001\u0010tR\u0016\u0010±\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b±\u0001\u0010tR\u0016\u0010²\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b²\u0001\u0010tR\u0016\u0010³\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b³\u0001\u0010tR\u0016\u0010´\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b´\u0001\u0010tR\u0016\u0010µ\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bµ\u0001\u0010tR\u0016\u0010¶\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b¶\u0001\u0010tR\u0016\u0010·\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b·\u0001\u0010tR\u0016\u0010¸\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b¸\u0001\u0010tR\u0016\u0010¹\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b¹\u0001\u0010tR\u0016\u0010º\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bº\u0001\u0010tR\u0016\u0010»\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b»\u0001\u0010tR\u0016\u0010¼\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b¼\u0001\u0010tR\u0016\u0010½\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b½\u0001\u0010tR\u0016\u0010¾\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b¾\u0001\u0010tR\u0016\u0010¿\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b¿\u0001\u0010tR\u0016\u0010À\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÀ\u0001\u0010tR\u0016\u0010Á\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÁ\u0001\u0010tR\u0016\u0010Â\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÂ\u0001\u0010tR\u0016\u0010Ã\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÃ\u0001\u0010tR\u0016\u0010Ä\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÄ\u0001\u0010tR\u0016\u0010Å\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÅ\u0001\u0010tR\u0016\u0010Æ\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÆ\u0001\u0010tR\u0016\u0010Ç\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÇ\u0001\u0010tR\u0016\u0010È\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÈ\u0001\u0010tR\u0016\u0010É\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÉ\u0001\u0010tR\u0016\u0010Ê\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÊ\u0001\u0010tR\u0016\u0010Ë\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bË\u0001\u0010tR\u0016\u0010Ì\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÌ\u0001\u0010tR\u0016\u0010Í\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÍ\u0001\u0010tR\u0016\u0010Î\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÎ\u0001\u0010tR\u0016\u0010Ï\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÏ\u0001\u0010tR\u0016\u0010Ð\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÐ\u0001\u0010tR\u0016\u0010Ñ\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÑ\u0001\u0010tR\u0016\u0010Ò\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÒ\u0001\u0010tR\u0016\u0010Ó\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÓ\u0001\u0010tR\u0016\u0010Ô\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÔ\u0001\u0010tR\u0016\u0010Õ\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÕ\u0001\u0010tR\u0016\u0010Ö\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÖ\u0001\u0010tR\u0016\u0010×\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\b×\u0001\u0010tR\u0016\u0010Ø\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bØ\u0001\u0010tR\u0016\u0010Ù\u0001\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0007\n\u0005\bÙ\u0001\u0010tR\u001e\u0010Û\u0001\u001a\t\u0012\u0004\u0012\u00020\u00040Ú\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÛ\u0001\u0010Ü\u0001R%\u0010à\u0001\u001a\t\u0012\u0004\u0012\u00020\u00040Ú\u00018FX\u0087\u0004¢\u0006\u000f\u0012\u0005\bß\u0001\u0010\u0003\u001a\u0006\bÝ\u0001\u0010Þ\u0001R#\u0010â\u0001\u001a\t\u0012\u0004\u0012\u00020\u00040á\u00018\u0006¢\u0006\u0010\n\u0006\bâ\u0001\u0010ã\u0001\u001a\u0006\bä\u0001\u0010å\u0001¨\u0006æ\u0001"}, d2 = {"Lio/ktor/http/HttpHeaders;", "", "<init>", "()V", "", "header", "", "isUnsafe", "(Ljava/lang/String;)Z", "name", "", "checkHeaderName", "(Ljava/lang/String;)V", ES6Iterator.VALUE_PROPERTY, "checkHeaderValue", "getAccept", "()Ljava/lang/String;", "getAcceptCharset", "getAcceptEncoding", "getAcceptLanguage", "getAcceptRanges", "getAge", "getAllow", "getALPN", "getAuthenticationInfo", "getAuthorization", "getCacheControl", "getConnection", "getContentDisposition", "getContentEncoding", "getContentLanguage", "getContentLength", "getContentLocation", "getContentRange", "getContentType", "getCookie", "getDASL", "getDate", "getDAV", "getDepth", "getDestination", "getETag", "getExpect", "getExpires", "getFrom", "getForwarded", "getHost", "getHTTP2Settings", "getIf", "getIfMatch", "getIfModifiedSince", "getIfNoneMatch", "getIfRange", "getIfScheduleTagMatch", "getIfUnmodifiedSince", "getLastModified", "getLocation", "getLockToken", "getLink", "getMaxForwards", "getMIMEVersion", "getOrderingType", "getOrigin", "getOverwrite", "getPosition", "getPragma", "getPrefer", "getPreferenceApplied", "getProxyAuthenticate", "getProxyAuthenticationInfo", "getProxyAuthorization", "getPublicKeyPins", "getPublicKeyPinsReportOnly", "getRange", "getReferrer", "getRetryAfter", "getScheduleReply", "getScheduleTag", "getSecWebSocketAccept", "getSecWebSocketExtensions", "getSecWebSocketKey", "getSecWebSocketProtocol", "getSecWebSocketVersion", "getServer", "getSetCookie", "getSLUG", "getStrictTransportSecurity", "getTE", "getTimeout", "getTrailer", "getTransferEncoding", "getUpgrade", "getUserAgent", "getVary", "getVia", "getWarning", "getWWWAuthenticate", "getAccessControlAllowOrigin", "getAccessControlAllowMethods", "getAccessControlAllowCredentials", "getAccessControlAllowHeaders", "getAccessControlRequestMethod", "getAccessControlRequestHeaders", "getAccessControlExposeHeaders", "getAccessControlMaxAge", "getXHttpMethodOverride", "getXForwardedHost", "getXForwardedServer", "getXForwardedProto", "getXForwardedFor", "getXForwardedPort", "getXRequestId", "getXCorrelationId", "getXTotalCount", "getLastEventID", "Accept", "Ljava/lang/String;", "AcceptCharset", "getAcceptCharset$annotations", "AcceptEncoding", "AcceptLanguage", "AcceptRanges", "Age", "Allow", HttpHeaders.ALPN, "AuthenticationInfo", "Authorization", "CacheControl", "Connection", "ContentDisposition", "ContentEncoding", "ContentLanguage", "ContentLength", "ContentLocation", "ContentRange", "ContentType", "Cookie", HttpHeaders.DASL, "Date", HttpHeaders.DAV, HttpHeaders.Depth, HttpHeaders.Destination, "ETag", "Expect", "Expires", "From", "Forwarded", "Host", "HTTP2Settings", HttpHeaders.If, "IfMatch", "IfModifiedSince", "IfNoneMatch", "IfRange", "IfScheduleTagMatch", "IfUnmodifiedSince", "LastModified", "Location", "LockToken", "Link", "MaxForwards", "MIMEVersion", "OrderingType", "Origin", HttpHeaders.Overwrite, HttpHeaders.Position, "Pragma", HttpHeaders.Prefer, "PreferenceApplied", "ProxyAuthenticate", "ProxyAuthenticationInfo", "ProxyAuthorization", "PublicKeyPins", "PublicKeyPinsReportOnly", "Range", "Referrer", "RetryAfter", "ScheduleReply", "ScheduleTag", "SecWebSocketAccept", "SecWebSocketExtensions", "SecWebSocketKey", "SecWebSocketProtocol", "SecWebSocketVersion", "Server", "SetCookie", HttpHeaders.SLUG, "StrictTransportSecurity", "TE", HttpHeaders.Timeout, "Trailer", "TransferEncoding", "Upgrade", "UserAgent", "Vary", "Via", "Warning", "WWWAuthenticate", "AccessControlAllowOrigin", "AccessControlAllowMethods", "AccessControlAllowCredentials", "AccessControlAllowHeaders", "AccessControlRequestMethod", "AccessControlRequestHeaders", "AccessControlExposeHeaders", "AccessControlMaxAge", "XHttpMethodOverride", "XForwardedHost", "XForwardedServer", "XForwardedProto", "XForwardedFor", "XForwardedPort", "XRequestId", "XCorrelationId", "XTotalCount", "LastEventID", "TDMReservation", "TDMPolicy", "", "UnsafeHeadersArray", "[Ljava/lang/String;", "getUnsafeHeaders", "()[Ljava/lang/String;", "getUnsafeHeaders$annotations", "UnsafeHeaders", "", "UnsafeHeadersList", "Ljava/util/List;", "getUnsafeHeadersList", "()Ljava/util/List;", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HttpHeaders {
    public static final String ALPN = "ALPN";
    public static final String Accept = "Accept";
    public static final String AcceptCharset = "Accept-Charset";
    public static final String AcceptEncoding = "Accept-Encoding";
    public static final String AcceptLanguage = "Accept-Language";
    public static final String AcceptRanges = "Accept-Ranges";
    public static final String AccessControlAllowCredentials = "Access-Control-Allow-Credentials";
    public static final String AccessControlAllowHeaders = "Access-Control-Allow-Headers";
    public static final String AccessControlAllowMethods = "Access-Control-Allow-Methods";
    public static final String AccessControlAllowOrigin = "Access-Control-Allow-Origin";
    public static final String AccessControlExposeHeaders = "Access-Control-Expose-Headers";
    public static final String AccessControlMaxAge = "Access-Control-Max-Age";
    public static final String AccessControlRequestHeaders = "Access-Control-Request-Headers";
    public static final String AccessControlRequestMethod = "Access-Control-Request-Method";
    public static final String Age = "Age";
    public static final String Allow = "Allow";
    public static final String AuthenticationInfo = "Authentication-Info";
    public static final String Authorization = "Authorization";
    public static final String CacheControl = "Cache-Control";
    public static final String Connection = "Connection";
    public static final String ContentDisposition = "Content-Disposition";
    public static final String ContentEncoding = "Content-Encoding";
    public static final String ContentLanguage = "Content-Language";
    public static final String ContentLength = "Content-Length";
    public static final String ContentLocation = "Content-Location";
    public static final String ContentRange = "Content-Range";
    public static final String ContentType = "Content-Type";
    public static final String Cookie = "Cookie";
    public static final String DASL = "DASL";
    public static final String DAV = "DAV";
    public static final String Date = "Date";
    public static final String Depth = "Depth";
    public static final String Destination = "Destination";
    public static final String ETag = "ETag";
    public static final String Expect = "Expect";
    public static final String Expires = "Expires";
    public static final String Forwarded = "Forwarded";
    public static final String From = "From";
    public static final String HTTP2Settings = "HTTP2-Settings";
    public static final String Host = "Host";
    public static final HttpHeaders INSTANCE = new HttpHeaders();
    public static final String If = "If";
    public static final String IfMatch = "If-Match";
    public static final String IfModifiedSince = "If-Modified-Since";
    public static final String IfNoneMatch = "If-None-Match";
    public static final String IfRange = "If-Range";
    public static final String IfScheduleTagMatch = "If-Schedule-Tag-Match";
    public static final String IfUnmodifiedSince = "If-Unmodified-Since";
    public static final String LastEventID = "Last-Event-ID";
    public static final String LastModified = "Last-Modified";
    public static final String Link = "Link";
    public static final String Location = "Location";
    public static final String LockToken = "Lock-Token";
    public static final String MIMEVersion = "MIME-Version";
    public static final String MaxForwards = "Max-Forwards";
    public static final String OrderingType = "Ordering-Type";
    public static final String Origin = "Origin";
    public static final String Overwrite = "Overwrite";
    public static final String Position = "Position";
    public static final String Pragma = "Pragma";
    public static final String Prefer = "Prefer";
    public static final String PreferenceApplied = "Preference-Applied";
    public static final String ProxyAuthenticate = "Proxy-Authenticate";
    public static final String ProxyAuthenticationInfo = "Proxy-Authentication-Info";
    public static final String ProxyAuthorization = "Proxy-Authorization";
    public static final String PublicKeyPins = "Public-Key-Pins";
    public static final String PublicKeyPinsReportOnly = "Public-Key-Pins-Report-Only";
    public static final String Range = "Range";
    public static final String Referrer = "Referer";
    public static final String RetryAfter = "Retry-After";
    public static final String SLUG = "SLUG";
    public static final String ScheduleReply = "Schedule-Reply";
    public static final String ScheduleTag = "Schedule-Tag";
    public static final String SecWebSocketAccept = "Sec-WebSocket-Accept";
    public static final String SecWebSocketExtensions = "Sec-WebSocket-Extensions";
    public static final String SecWebSocketKey = "Sec-WebSocket-Key";
    public static final String SecWebSocketProtocol = "Sec-WebSocket-Protocol";
    public static final String SecWebSocketVersion = "Sec-WebSocket-Version";
    public static final String Server = "Server";
    public static final String SetCookie = "Set-Cookie";
    public static final String StrictTransportSecurity = "Strict-Transport-Security";
    public static final String TDMPolicy = "TDM-Policy";
    public static final String TDMReservation = "TDM-Reservation";
    public static final String TE = "TE";
    public static final String Timeout = "Timeout";
    public static final String Trailer = "Trailer";
    public static final String TransferEncoding = "Transfer-Encoding";
    private static final String[] UnsafeHeadersArray;
    private static final List<String> UnsafeHeadersList;
    public static final String Upgrade = "Upgrade";
    public static final String UserAgent = "User-Agent";
    public static final String Vary = "Vary";
    public static final String Via = "Via";
    public static final String WWWAuthenticate = "WWW-Authenticate";
    public static final String Warning = "Warning";
    public static final String XCorrelationId = "X-Correlation-ID";
    public static final String XForwardedFor = "X-Forwarded-For";
    public static final String XForwardedHost = "X-Forwarded-Host";
    public static final String XForwardedPort = "X-Forwarded-Port";
    public static final String XForwardedProto = "X-Forwarded-Proto";
    public static final String XForwardedServer = "X-Forwarded-Server";
    public static final String XHttpMethodOverride = "X-Http-Method-Override";
    public static final String XRequestId = "X-Request-ID";
    public static final String XTotalCount = "X-Total-Count";

    @Deprecated(message = "The Accept-Charset request header has been deprecated in RFC 9110. UTF-8 should be used by default in modern applications.")
    public static /* synthetic */ void getAcceptCharset$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use UnsafeHeadersList instead.", replaceWith = @ReplaceWith(expression = "HttpHeaders.UnsafeHeadersList", imports = {}))
    public static /* synthetic */ void getUnsafeHeaders$annotations() {
    }

    private HttpHeaders() {
    }

    public final boolean isUnsafe(String header) {
        Intrinsics.checkNotNullParameter(header, "header");
        for (String str : UnsafeHeadersArray) {
            if (StringsKt.equals(str, header, true)) {
                return true;
            }
        }
        return false;
    }

    static {
        String[] strArr = {"Transfer-Encoding", "Upgrade"};
        UnsafeHeadersArray = strArr;
        UnsafeHeadersList = ArraysKt.asList(strArr);
    }

    public final String[] getUnsafeHeaders() {
        String[] strArr = UnsafeHeadersArray;
        Object[] objArrCopyOf = Arrays.copyOf(strArr, strArr.length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        return (String[]) objArrCopyOf;
    }

    public final List<String> getUnsafeHeadersList() {
        return UnsafeHeadersList;
    }

    public final void checkHeaderName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        String str = name;
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            int i3 = i2 + 1;
            if (Intrinsics.compare((int) cCharAt, 32) <= 0 || HttpHeadersKt.isDelimiter(cCharAt)) {
                throw new IllegalHeaderNameException(name, i2);
            }
            i++;
            i2 = i3;
        }
    }

    public final void checkHeaderValue(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        String str = value;
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            int i3 = i2 + 1;
            if (Intrinsics.compare((int) cCharAt, 32) < 0 && cCharAt != '\t') {
                throw new IllegalHeaderValueException(value, i2);
            }
            i++;
            i2 = i3;
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Accept constant instead.", replaceWith = @ReplaceWith(expression = "Accept", imports = {}))
    public final String getAccept() {
        return "Accept";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use AcceptCharset constant instead.", replaceWith = @ReplaceWith(expression = "AcceptCharset", imports = {}))
    public final String getAcceptCharset() {
        return "Accept-Charset";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use AcceptEncoding constant instead.", replaceWith = @ReplaceWith(expression = "AcceptEncoding", imports = {}))
    public final String getAcceptEncoding() {
        return "Accept-Encoding";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use AcceptLanguage constant instead.", replaceWith = @ReplaceWith(expression = "AcceptLanguage", imports = {}))
    public final String getAcceptLanguage() {
        return "Accept-Language";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use AcceptRanges constant instead.", replaceWith = @ReplaceWith(expression = "AcceptRanges", imports = {}))
    public final String getAcceptRanges() {
        return "Accept-Ranges";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Age constant instead.", replaceWith = @ReplaceWith(expression = "Age", imports = {}))
    public final String getAge() {
        return "Age";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Allow constant instead.", replaceWith = @ReplaceWith(expression = "Allow", imports = {}))
    public final String getAllow() {
        return "Allow";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use ALPN constant instead.", replaceWith = @ReplaceWith(expression = ALPN, imports = {}))
    public final String getALPN() {
        return ALPN;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use AuthenticationInfo constant instead.", replaceWith = @ReplaceWith(expression = "AuthenticationInfo", imports = {}))
    public final String getAuthenticationInfo() {
        return AuthenticationInfo;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Authorization constant instead.", replaceWith = @ReplaceWith(expression = "Authorization", imports = {}))
    public final String getAuthorization() {
        return "Authorization";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use CacheControl constant instead.", replaceWith = @ReplaceWith(expression = "CacheControl", imports = {}))
    public final String getCacheControl() {
        return "Cache-Control";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Connection constant instead.", replaceWith = @ReplaceWith(expression = "Connection", imports = {}))
    public final String getConnection() {
        return "Connection";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use ContentDisposition constant instead.", replaceWith = @ReplaceWith(expression = "ContentDisposition", imports = {}))
    public final String getContentDisposition() {
        return "Content-Disposition";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use ContentEncoding constant instead.", replaceWith = @ReplaceWith(expression = "ContentEncoding", imports = {}))
    public final String getContentEncoding() {
        return "Content-Encoding";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use ContentLanguage constant instead.", replaceWith = @ReplaceWith(expression = "ContentLanguage", imports = {}))
    public final String getContentLanguage() {
        return "Content-Language";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use ContentLength constant instead.", replaceWith = @ReplaceWith(expression = "ContentLength", imports = {}))
    public final String getContentLength() {
        return "Content-Length";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use ContentLocation constant instead.", replaceWith = @ReplaceWith(expression = "ContentLocation", imports = {}))
    public final String getContentLocation() {
        return "Content-Location";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use ContentRange constant instead.", replaceWith = @ReplaceWith(expression = "ContentRange", imports = {}))
    public final String getContentRange() {
        return "Content-Range";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use ContentType constant instead.", replaceWith = @ReplaceWith(expression = "ContentType", imports = {}))
    public final String getContentType() {
        return "Content-Type";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Cookie constant instead.", replaceWith = @ReplaceWith(expression = "Cookie", imports = {}))
    public final String getCookie() {
        return "Cookie";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use DASL constant instead.", replaceWith = @ReplaceWith(expression = DASL, imports = {}))
    public final String getDASL() {
        return DASL;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Date constant instead.", replaceWith = @ReplaceWith(expression = "Date", imports = {}))
    public final String getDate() {
        return "Date";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use DAV constant instead.", replaceWith = @ReplaceWith(expression = DAV, imports = {}))
    public final String getDAV() {
        return DAV;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Depth constant instead.", replaceWith = @ReplaceWith(expression = Depth, imports = {}))
    public final String getDepth() {
        return Depth;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Destination constant instead.", replaceWith = @ReplaceWith(expression = Destination, imports = {}))
    public final String getDestination() {
        return Destination;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use ETag constant instead.", replaceWith = @ReplaceWith(expression = "ETag", imports = {}))
    public final String getETag() {
        return "ETag";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Expect constant instead.", replaceWith = @ReplaceWith(expression = "Expect", imports = {}))
    public final String getExpect() {
        return "Expect";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Expires constant instead.", replaceWith = @ReplaceWith(expression = "Expires", imports = {}))
    public final String getExpires() {
        return "Expires";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use From constant instead.", replaceWith = @ReplaceWith(expression = "From", imports = {}))
    public final String getFrom() {
        return "From";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Forwarded constant instead.", replaceWith = @ReplaceWith(expression = "Forwarded", imports = {}))
    public final String getForwarded() {
        return "Forwarded";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Host constant instead.", replaceWith = @ReplaceWith(expression = "Host", imports = {}))
    public final String getHost() {
        return "Host";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use HTTP2Settings constant instead.", replaceWith = @ReplaceWith(expression = "HTTP2Settings", imports = {}))
    public final String getHTTP2Settings() {
        return "HTTP2-Settings";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use If constant instead.", replaceWith = @ReplaceWith(expression = If, imports = {}))
    public final String getIf() {
        return If;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use IfMatch constant instead.", replaceWith = @ReplaceWith(expression = "IfMatch", imports = {}))
    public final String getIfMatch() {
        return "If-Match";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use IfModifiedSince constant instead.", replaceWith = @ReplaceWith(expression = "IfModifiedSince", imports = {}))
    public final String getIfModifiedSince() {
        return "If-Modified-Since";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use IfNoneMatch constant instead.", replaceWith = @ReplaceWith(expression = "IfNoneMatch", imports = {}))
    public final String getIfNoneMatch() {
        return "If-None-Match";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use IfRange constant instead.", replaceWith = @ReplaceWith(expression = "IfRange", imports = {}))
    public final String getIfRange() {
        return "If-Range";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use IfScheduleTagMatch constant instead.", replaceWith = @ReplaceWith(expression = "IfScheduleTagMatch", imports = {}))
    public final String getIfScheduleTagMatch() {
        return IfScheduleTagMatch;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use IfUnmodifiedSince constant instead.", replaceWith = @ReplaceWith(expression = "IfUnmodifiedSince", imports = {}))
    public final String getIfUnmodifiedSince() {
        return "If-Unmodified-Since";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use LastModified constant instead.", replaceWith = @ReplaceWith(expression = "LastModified", imports = {}))
    public final String getLastModified() {
        return "Last-Modified";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Location constant instead.", replaceWith = @ReplaceWith(expression = "Location", imports = {}))
    public final String getLocation() {
        return "Location";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use LockToken constant instead.", replaceWith = @ReplaceWith(expression = "LockToken", imports = {}))
    public final String getLockToken() {
        return LockToken;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Link constant instead.", replaceWith = @ReplaceWith(expression = "Link", imports = {}))
    public final String getLink() {
        return "Link";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use MaxForwards constant instead.", replaceWith = @ReplaceWith(expression = "MaxForwards", imports = {}))
    public final String getMaxForwards() {
        return "Max-Forwards";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use MIMEVersion constant instead.", replaceWith = @ReplaceWith(expression = "MIMEVersion", imports = {}))
    public final String getMIMEVersion() {
        return MIMEVersion;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use OrderingType constant instead.", replaceWith = @ReplaceWith(expression = "OrderingType", imports = {}))
    public final String getOrderingType() {
        return OrderingType;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Origin constant instead.", replaceWith = @ReplaceWith(expression = "Origin", imports = {}))
    public final String getOrigin() {
        return "Origin";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Overwrite constant instead.", replaceWith = @ReplaceWith(expression = Overwrite, imports = {}))
    public final String getOverwrite() {
        return Overwrite;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Position constant instead.", replaceWith = @ReplaceWith(expression = Position, imports = {}))
    public final String getPosition() {
        return Position;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Pragma constant instead.", replaceWith = @ReplaceWith(expression = "Pragma", imports = {}))
    public final String getPragma() {
        return "Pragma";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Prefer constant instead.", replaceWith = @ReplaceWith(expression = Prefer, imports = {}))
    public final String getPrefer() {
        return Prefer;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use PreferenceApplied constant instead.", replaceWith = @ReplaceWith(expression = "PreferenceApplied", imports = {}))
    public final String getPreferenceApplied() {
        return PreferenceApplied;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use ProxyAuthenticate constant instead.", replaceWith = @ReplaceWith(expression = "ProxyAuthenticate", imports = {}))
    public final String getProxyAuthenticate() {
        return "Proxy-Authenticate";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use ProxyAuthenticationInfo constant instead.", replaceWith = @ReplaceWith(expression = "ProxyAuthenticationInfo", imports = {}))
    public final String getProxyAuthenticationInfo() {
        return ProxyAuthenticationInfo;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use ProxyAuthorization constant instead.", replaceWith = @ReplaceWith(expression = "ProxyAuthorization", imports = {}))
    public final String getProxyAuthorization() {
        return "Proxy-Authorization";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use PublicKeyPins constant instead.", replaceWith = @ReplaceWith(expression = "PublicKeyPins", imports = {}))
    public final String getPublicKeyPins() {
        return "Public-Key-Pins";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use PublicKeyPinsReportOnly constant instead.", replaceWith = @ReplaceWith(expression = "PublicKeyPinsReportOnly", imports = {}))
    public final String getPublicKeyPinsReportOnly() {
        return "Public-Key-Pins-Report-Only";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Range constant instead.", replaceWith = @ReplaceWith(expression = "Range", imports = {}))
    public final String getRange() {
        return "Range";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Referrer constant instead.", replaceWith = @ReplaceWith(expression = "Referrer", imports = {}))
    public final String getReferrer() {
        return "Referer";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use RetryAfter constant instead.", replaceWith = @ReplaceWith(expression = "RetryAfter", imports = {}))
    public final String getRetryAfter() {
        return "Retry-After";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use ScheduleReply constant instead.", replaceWith = @ReplaceWith(expression = "ScheduleReply", imports = {}))
    public final String getScheduleReply() {
        return ScheduleReply;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use ScheduleTag constant instead.", replaceWith = @ReplaceWith(expression = "ScheduleTag", imports = {}))
    public final String getScheduleTag() {
        return ScheduleTag;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use SecWebSocketAccept constant instead.", replaceWith = @ReplaceWith(expression = "SecWebSocketAccept", imports = {}))
    public final String getSecWebSocketAccept() {
        return "Sec-WebSocket-Accept";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use SecWebSocketExtensions constant instead.", replaceWith = @ReplaceWith(expression = "SecWebSocketExtensions", imports = {}))
    public final String getSecWebSocketExtensions() {
        return "Sec-WebSocket-Extensions";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use SecWebSocketKey constant instead.", replaceWith = @ReplaceWith(expression = "SecWebSocketKey", imports = {}))
    public final String getSecWebSocketKey() {
        return "Sec-WebSocket-Key";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use SecWebSocketProtocol constant instead.", replaceWith = @ReplaceWith(expression = "SecWebSocketProtocol", imports = {}))
    public final String getSecWebSocketProtocol() {
        return "Sec-WebSocket-Protocol";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use SecWebSocketVersion constant instead.", replaceWith = @ReplaceWith(expression = "SecWebSocketVersion", imports = {}))
    public final String getSecWebSocketVersion() {
        return "Sec-WebSocket-Version";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Server constant instead.", replaceWith = @ReplaceWith(expression = "Server", imports = {}))
    public final String getServer() {
        return "Server";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use SetCookie constant instead.", replaceWith = @ReplaceWith(expression = "SetCookie", imports = {}))
    public final String getSetCookie() {
        return "Set-Cookie";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use SLUG constant instead.", replaceWith = @ReplaceWith(expression = SLUG, imports = {}))
    public final String getSLUG() {
        return SLUG;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use StrictTransportSecurity constant instead.", replaceWith = @ReplaceWith(expression = "StrictTransportSecurity", imports = {}))
    public final String getStrictTransportSecurity() {
        return "Strict-Transport-Security";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use TE constant instead.", replaceWith = @ReplaceWith(expression = "TE", imports = {}))
    public final String getTE() {
        return "TE";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Timeout constant instead.", replaceWith = @ReplaceWith(expression = Timeout, imports = {}))
    public final String getTimeout() {
        return Timeout;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Trailer constant instead.", replaceWith = @ReplaceWith(expression = "Trailer", imports = {}))
    public final String getTrailer() {
        return "Trailer";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use TransferEncoding constant instead.", replaceWith = @ReplaceWith(expression = "TransferEncoding", imports = {}))
    public final String getTransferEncoding() {
        return "Transfer-Encoding";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Upgrade constant instead.", replaceWith = @ReplaceWith(expression = "Upgrade", imports = {}))
    public final String getUpgrade() {
        return "Upgrade";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use UserAgent constant instead.", replaceWith = @ReplaceWith(expression = "UserAgent", imports = {}))
    public final String getUserAgent() {
        return "User-Agent";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Vary constant instead.", replaceWith = @ReplaceWith(expression = "Vary", imports = {}))
    public final String getVary() {
        return "Vary";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Via constant instead.", replaceWith = @ReplaceWith(expression = "Via", imports = {}))
    public final String getVia() {
        return "Via";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use Warning constant instead.", replaceWith = @ReplaceWith(expression = "Warning", imports = {}))
    public final String getWarning() {
        return "Warning";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use WWWAuthenticate constant instead.", replaceWith = @ReplaceWith(expression = "WWWAuthenticate", imports = {}))
    public final String getWWWAuthenticate() {
        return "WWW-Authenticate";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use AccessControlAllowOrigin constant instead.", replaceWith = @ReplaceWith(expression = "AccessControlAllowOrigin", imports = {}))
    public final String getAccessControlAllowOrigin() {
        return "Access-Control-Allow-Origin";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use AccessControlAllowMethods constant instead.", replaceWith = @ReplaceWith(expression = "AccessControlAllowMethods", imports = {}))
    public final String getAccessControlAllowMethods() {
        return "Access-Control-Allow-Methods";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use AccessControlAllowCredentials constant instead.", replaceWith = @ReplaceWith(expression = "AccessControlAllowCredentials", imports = {}))
    public final String getAccessControlAllowCredentials() {
        return "Access-Control-Allow-Credentials";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use AccessControlAllowHeaders constant instead.", replaceWith = @ReplaceWith(expression = "AccessControlAllowHeaders", imports = {}))
    public final String getAccessControlAllowHeaders() {
        return "Access-Control-Allow-Headers";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use AccessControlRequestMethod constant instead.", replaceWith = @ReplaceWith(expression = "AccessControlRequestMethod", imports = {}))
    public final String getAccessControlRequestMethod() {
        return "Access-Control-Request-Method";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use AccessControlRequestHeaders constant instead.", replaceWith = @ReplaceWith(expression = "AccessControlRequestHeaders", imports = {}))
    public final String getAccessControlRequestHeaders() {
        return "Access-Control-Request-Headers";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use AccessControlExposeHeaders constant instead.", replaceWith = @ReplaceWith(expression = "AccessControlExposeHeaders", imports = {}))
    public final String getAccessControlExposeHeaders() {
        return "Access-Control-Expose-Headers";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use AccessControlMaxAge constant instead.", replaceWith = @ReplaceWith(expression = "AccessControlMaxAge", imports = {}))
    public final String getAccessControlMaxAge() {
        return "Access-Control-Max-Age";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use XHttpMethodOverride constant instead.", replaceWith = @ReplaceWith(expression = "XHttpMethodOverride", imports = {}))
    public final String getXHttpMethodOverride() {
        return XHttpMethodOverride;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use XForwardedHost constant instead.", replaceWith = @ReplaceWith(expression = "XForwardedHost", imports = {}))
    public final String getXForwardedHost() {
        return "X-Forwarded-Host";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use XForwardedServer constant instead.", replaceWith = @ReplaceWith(expression = "XForwardedServer", imports = {}))
    public final String getXForwardedServer() {
        return XForwardedServer;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use XForwardedProto constant instead.", replaceWith = @ReplaceWith(expression = "XForwardedProto", imports = {}))
    public final String getXForwardedProto() {
        return "X-Forwarded-Proto";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use XForwardedFor constant instead.", replaceWith = @ReplaceWith(expression = "XForwardedFor", imports = {}))
    public final String getXForwardedFor() {
        return "X-Forwarded-For";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use XForwardedPort constant instead.", replaceWith = @ReplaceWith(expression = "XForwardedPort", imports = {}))
    public final String getXForwardedPort() {
        return "X-Forwarded-Port";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use XRequestId constant instead.", replaceWith = @ReplaceWith(expression = "XRequestId", imports = {}))
    public final String getXRequestId() {
        return "X-Request-ID";
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use XCorrelationId constant instead.", replaceWith = @ReplaceWith(expression = "XCorrelationId", imports = {}))
    public final String getXCorrelationId() {
        return XCorrelationId;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use XTotalCount constant instead.", replaceWith = @ReplaceWith(expression = "XTotalCount", imports = {}))
    public final String getXTotalCount() {
        return XTotalCount;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use LastEventID constant instead.", replaceWith = @ReplaceWith(expression = "LastEventID", imports = {}))
    public final String getLastEventID() {
        return "Last-Event-ID";
    }
}
