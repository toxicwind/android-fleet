package okhttp3.dnsoverhttps;

import java.io.EOFException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okio.Buffer;
import okio.ByteString;
import okio.Utf8;

/* JADX INFO: compiled from: DnsRecordCodec.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005J\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\rJ\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lokhttp3/dnsoverhttps/DnsRecordCodec;", "", "<init>", "()V", "SERVFAIL", "", "NXDOMAIN", "TYPE_A", "TYPE_AAAA", "TYPE_PTR", "ASCII", "Ljava/nio/charset/Charset;", "encodeQuery", "Lokio/ByteString;", "host", "", "type", "decodeAnswers", "", "Ljava/net/InetAddress;", "hostname", "byteString", "skipName", "", "source", "Lokio/Buffer;", "okhttp-dnsoverhttps"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DnsRecordCodec {
    private static final int NXDOMAIN = 3;
    private static final int SERVFAIL = 2;
    public static final int TYPE_A = 1;
    public static final int TYPE_AAAA = 28;
    private static final int TYPE_PTR = 12;
    public static final DnsRecordCodec INSTANCE = new DnsRecordCodec();
    private static final Charset ASCII = Charsets.US_ASCII;

    private DnsRecordCodec() {
    }

    public final ByteString encodeQuery(String host, int type) {
        List<String> listEmptyList;
        Intrinsics.checkNotNullParameter(host, "host");
        Buffer buffer = new Buffer();
        buffer.writeShort(0);
        buffer.writeShort(256);
        buffer.writeShort(1);
        buffer.writeShort(0);
        buffer.writeShort(0);
        buffer.writeShort(0);
        Buffer buffer2 = new Buffer();
        List listSplit$default = StringsKt.split$default((CharSequence) host, new char[]{'.'}, false, 0, 6, (Object) null);
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
        for (String str : listEmptyList) {
            long jSize$default = Utf8.size$default(str, 0, 0, 3, null);
            if (jSize$default != str.length()) {
                throw new IllegalArgumentException(("non-ascii hostname: " + host).toString());
            }
            buffer2.writeByte((int) jSize$default);
            buffer2.writeUtf8(str);
        }
        buffer2.writeByte(0);
        buffer2.copyTo(buffer, 0L, buffer2.size());
        buffer.writeShort(type);
        buffer.writeShort(1);
        return buffer.readByteString();
    }

    public final List<InetAddress> decodeAnswers(String hostname, ByteString byteString) throws Exception {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        ArrayList arrayList = new ArrayList();
        Buffer buffer = new Buffer();
        buffer.write(byteString);
        buffer.readShort();
        short s = buffer.readShort();
        if (((s & UShort.MAX_VALUE) >> 15) == 0) {
            throw new IllegalArgumentException("not a response".toString());
        }
        int i = s & 15;
        if (i == 2) {
            throw new UnknownHostException(hostname + ": SERVFAIL");
        }
        if (i == 3) {
            throw new UnknownHostException(hostname + ": NXDOMAIN");
        }
        int i2 = buffer.readShort() & UShort.MAX_VALUE;
        int i3 = buffer.readShort() & UShort.MAX_VALUE;
        buffer.readShort();
        buffer.readShort();
        for (int i4 = 0; i4 < i2; i4++) {
            skipName(buffer);
            buffer.readShort();
            buffer.readShort();
        }
        for (int i5 = 0; i5 < i3; i5++) {
            skipName(buffer);
            int i6 = buffer.readShort() & UShort.MAX_VALUE;
            buffer.readShort();
            buffer.readInt();
            int i7 = buffer.readShort() & UShort.MAX_VALUE;
            if (i6 == 1 || i6 == 28) {
                byte[] bArr = new byte[i7];
                buffer.read(bArr);
                InetAddress byAddress = InetAddress.getByAddress(bArr);
                Intrinsics.checkNotNullExpressionValue(byAddress, "getByAddress(...)");
                arrayList.add(byAddress);
            } else {
                buffer.skip(i7);
            }
        }
        return arrayList;
    }

    private final void skipName(Buffer source) throws EOFException {
        byte b = source.readByte();
        if (b < 0) {
            source.skip(1L);
            return;
        }
        while (b > 0) {
            source.skip(b);
            b = source.readByte();
        }
    }
}
