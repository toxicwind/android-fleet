package com.fleeksoft.io;

import androidx.exifinterface.media.ExifInterface;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DebugKt;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: StreamCoreExt.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0016\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00020\u0003H\u0086\b¢\u0006\u0002\u0010\u0004\u001a\u0016\u0010\u0005\u001a\u00060\u0001j\u0002`\u0002*\u00020\u0006H\u0086\b¢\u0006\u0002\u0010\u0007\u001a&\u0010\u0005\u001a\u00060\u0001j\u0002`\u0002*\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0086\b¢\u0006\u0002\u0010\u000b\u001a\u0016\u0010\f\u001a\u00060\rj\u0002`\u000e*\u00020\u0003H\u0086\b¢\u0006\u0002\u0010\u000f\u001a$\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012*\u00060\u0013j\u0002`\u00142\b\b\u0002\u0010\u0015\u001a\u00020\tH\u0086\b¢\u0006\u0002\u0010\u0016\u001a\u001b\u0010\u0017\u001a\u00020\u0003*\u00060\u0013j\u0002`\u00142\u0006\u0010\u0018\u001a\u00020\t¢\u0006\u0002\u0010\u0019\u001a'\u0010\u001a\u001a\u00020\u001b*\u00060\u0013j\u0002`\u00142\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001b0\u001d¢\u0006\u0002\u0010\u001e\u001a\u0019\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030 *\u00060\u0013j\u0002`\u0014¢\u0006\u0002\u0010!\u001aF\u0010\"\u001a\u0002H#\"\u0004\b\u0000\u0010#*\u00060\u0013j\u0002`\u00142\u0018\u0010$\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030%\u0012\u0004\u0012\u0002H#0\u001dH\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010&\u001a\u0019\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030%*\u00060\u0011j\u0002`\u0012¢\u0006\u0002\u0010(\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006)"}, d2 = {"byteInputStream", "Ljava/io/ByteArrayInputStream;", "Lcom/fleeksoft/io/ByteArrayInputStream;", "", "(Ljava/lang/String;)Ljava/io/ByteArrayInputStream;", "inputStream", "", "([B)Ljava/io/ByteArrayInputStream;", DebugKt.DEBUG_PROPERTY_VALUE_OFF, "", "len", "([BII)Ljava/io/ByteArrayInputStream;", "reader", "Ljava/io/StringReader;", "Lcom/fleeksoft/io/StringReader;", "(Ljava/lang/String;)Ljava/io/StringReader;", "buffered", "Ljava/io/BufferedReader;", "Lcom/fleeksoft/io/BufferedReader;", "Ljava/io/Reader;", "Lcom/fleeksoft/io/Reader;", "bufferSize", "(Ljava/io/Reader;I)Ljava/io/BufferedReader;", "readString", PeertubeParsingHelper.COUNT_KEY, "(Ljava/io/Reader;I)Ljava/lang/String;", "forEachLine", "", "action", "Lkotlin/Function1;", "(Ljava/io/Reader;Lkotlin/jvm/functions/Function1;)V", "readLines", "", "(Ljava/io/Reader;)Ljava/util/List;", "useLines", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/sequences/Sequence;", "(Ljava/io/Reader;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "lineSequence", "(Ljava/io/BufferedReader;)Lkotlin/sequences/Sequence;", "io-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class StreamCoreExtKt {
    public static final ByteArrayInputStream byteInputStream(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return new ByteArrayInputStream(StringsKt.encodeToByteArray(str));
    }

    public static final ByteArrayInputStream inputStream(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return new ByteArrayInputStream(bArr);
    }

    public static final ByteArrayInputStream inputStream(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return new ByteArrayInputStream(bArr, i, i2);
    }

    public static final StringReader reader(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return new StringReader(str);
    }

    public static /* synthetic */ BufferedReader buffered$default(Reader reader, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        Intrinsics.checkNotNullParameter(reader, "<this>");
        BufferedReader bufferedReader = reader instanceof BufferedReader ? (BufferedReader) reader : null;
        return bufferedReader == null ? new BufferedReader(reader, i) : bufferedReader;
    }

    public static final BufferedReader buffered(Reader reader, int i) {
        Intrinsics.checkNotNullParameter(reader, "<this>");
        BufferedReader bufferedReader = reader instanceof BufferedReader ? (BufferedReader) reader : null;
        return bufferedReader == null ? new BufferedReader(reader, i) : bufferedReader;
    }

    public static final String readString(Reader reader, int i) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "<this>");
        char[] cArr = new char[i];
        int i2 = reader.read(cArr, 0, i);
        return i2 > 0 ? StringsKt.concatToString(cArr, 0, i2) : "";
    }

    public static final List<String> readLines(Reader reader) {
        Intrinsics.checkNotNullParameter(reader, "<this>");
        final ArrayList arrayList = new ArrayList();
        forEachLine(reader, new Function1() { // from class: com.fleeksoft.io.StreamCoreExtKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return StreamCoreExtKt.readLines$lambda$0(arrayList, (String) obj);
            }
        });
        return arrayList;
    }

    static final Unit readLines$lambda$0(ArrayList arrayList, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        arrayList.add(it);
        return Unit.INSTANCE;
    }

    public static final Sequence<String> lineSequence(BufferedReader bufferedReader) {
        Intrinsics.checkNotNullParameter(bufferedReader, "<this>");
        return SequencesKt.constrainOnce(new LinesSequence(bufferedReader));
    }

    public static final void forEachLine(Reader reader, Function1<? super String, Unit> action) {
        Intrinsics.checkNotNullParameter(reader, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        BufferedReader bufferedReader = reader instanceof BufferedReader ? (BufferedReader) reader : null;
        if (bufferedReader == null) {
            bufferedReader = new BufferedReader(reader, 8192);
        }
        BufferedReader bufferedReader2 = bufferedReader;
        try {
            Iterator<String> it = lineSequence(bufferedReader2).iterator();
            while (it.hasNext()) {
                action.invoke(it.next());
            }
            Unit unit = Unit.INSTANCE;
            AutoCloseableKt.closeFinally(bufferedReader2, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(bufferedReader2, th);
                throw th2;
            }
        }
    }

    public static final <T> T useLines(Reader reader, Function1<? super Sequence<String>, ? extends T> block) {
        Intrinsics.checkNotNullParameter(reader, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        BufferedReader bufferedReader = reader instanceof BufferedReader ? (BufferedReader) reader : null;
        if (bufferedReader == null) {
            bufferedReader = new BufferedReader(reader, 8192);
        }
        BufferedReader bufferedReader2 = bufferedReader;
        try {
            T tInvoke = block.invoke(lineSequence(bufferedReader2));
            AutoCloseableKt.closeFinally(bufferedReader2, null);
            return tInvoke;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(bufferedReader2, th);
                throw th2;
            }
        }
    }
}
