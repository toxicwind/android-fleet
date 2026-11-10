package okhttp3.internal.http2;

import b.a.a.a.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.storage.network.NetworkRequest;
import d.C;
import d.g;
import d.i;
import d.j;
import d.t;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okhttp3.internal.Util;

/* JADX INFO: loaded from: classes2.dex */
public final class Hpack {
    public static final int PREFIX_4_BITS = 15;
    public static final int PREFIX_5_BITS = 31;
    public static final int PREFIX_6_BITS = 63;
    public static final int PREFIX_7_BITS = 127;
    public static final Header[] STATIC_HEADER_TABLE = {new Header(Header.TARGET_AUTHORITY, ""), new Header(Header.TARGET_METHOD, NetworkRequest.GET), new Header(Header.TARGET_METHOD, NetworkRequest.POST), new Header(Header.TARGET_PATH, "/"), new Header(Header.TARGET_PATH, "/index.html"), new Header(Header.TARGET_SCHEME, "http"), new Header(Header.TARGET_SCHEME, "https"), new Header(Header.RESPONSE_STATUS, "200"), new Header(Header.RESPONSE_STATUS, "204"), new Header(Header.RESPONSE_STATUS, "206"), new Header(Header.RESPONSE_STATUS, "304"), new Header(Header.RESPONSE_STATUS, "400"), new Header(Header.RESPONSE_STATUS, "404"), new Header(Header.RESPONSE_STATUS, "500"), new Header("accept-charset", ""), new Header("accept-encoding", "gzip, deflate"), new Header("accept-language", ""), new Header("accept-ranges", ""), new Header("accept", ""), new Header("access-control-allow-origin", ""), new Header("age", ""), new Header("allow", ""), new Header("authorization", ""), new Header("cache-control", ""), new Header("content-disposition", ""), new Header("content-encoding", ""), new Header("content-language", ""), new Header("content-length", ""), new Header("content-location", ""), new Header("content-range", ""), new Header("content-type", ""), new Header("cookie", ""), new Header("date", ""), new Header("etag", ""), new Header("expect", ""), new Header("expires", ""), new Header("from", ""), new Header(Http2Codec.HOST, ""), new Header("if-match", ""), new Header("if-modified-since", ""), new Header("if-none-match", ""), new Header("if-range", ""), new Header("if-unmodified-since", ""), new Header("last-modified", ""), new Header("link", ""), new Header(FirebaseAnalytics.Param.LOCATION, ""), new Header("max-forwards", ""), new Header("proxy-authenticate", ""), new Header("proxy-authorization", ""), new Header("range", ""), new Header("referer", ""), new Header("refresh", ""), new Header("retry-after", ""), new Header("server", ""), new Header("set-cookie", ""), new Header("strict-transport-security", ""), new Header(Http2Codec.TRANSFER_ENCODING, ""), new Header("user-agent", ""), new Header("vary", ""), new Header("via", ""), new Header("www-authenticate", "")};
    public static final Map<j, Integer> NAME_TO_FIRST_INDEX = nameToFirstIndex();

    static final class Reader {
        public Header[] dynamicTable;
        public int dynamicTableByteCount;
        public int headerCount;
        public final List<Header> headerList;
        public final int headerTableSizeSetting;
        public int maxDynamicTableByteCount;
        public int nextHeaderIndex;
        public final i source;

        public Reader(int i, C c2) {
            this(i, i, c2);
        }

        private void adjustDynamicTableByteCount() {
            int i = this.maxDynamicTableByteCount;
            int i2 = this.dynamicTableByteCount;
            if (i < i2) {
                if (i == 0) {
                    clearDynamicTable();
                } else {
                    evictToRecoverBytes(i2 - i);
                }
            }
        }

        private void clearDynamicTable() {
            Arrays.fill(this.dynamicTable, (Object) null);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        private int dynamicTableIndex(int i) {
            return this.nextHeaderIndex + 1 + i;
        }

        private int evictToRecoverBytes(int i) {
            int i2 = 0;
            if (i > 0) {
                int length = this.dynamicTable.length;
                while (true) {
                    length--;
                    if (length < this.nextHeaderIndex || i <= 0) {
                        break;
                    }
                    Header[] headerArr = this.dynamicTable;
                    i -= headerArr[length].hpackSize;
                    this.dynamicTableByteCount -= headerArr[length].hpackSize;
                    this.headerCount--;
                    i2++;
                }
                Header[] headerArr2 = this.dynamicTable;
                int i3 = this.nextHeaderIndex;
                System.arraycopy(headerArr2, i3 + 1, headerArr2, i3 + 1 + i2, this.headerCount);
                this.nextHeaderIndex += i2;
            }
            return i2;
        }

        private j getName(int i) throws IOException {
            if (isStaticHeader(i)) {
                return Hpack.STATIC_HEADER_TABLE[i].name;
            }
            int iDynamicTableIndex = dynamicTableIndex(i - Hpack.STATIC_HEADER_TABLE.length);
            if (iDynamicTableIndex >= 0) {
                Header[] headerArr = this.dynamicTable;
                if (iDynamicTableIndex < headerArr.length) {
                    return headerArr[iDynamicTableIndex].name;
                }
            }
            StringBuilder sbA = a.a("Header index too large ");
            sbA.append(i + 1);
            throw new IOException(sbA.toString());
        }

        private void insertIntoDynamicTable(int i, Header header) {
            this.headerList.add(header);
            int i2 = header.hpackSize;
            if (i != -1) {
                i2 -= this.dynamicTable[(this.nextHeaderIndex + 1) + i].hpackSize;
            }
            int i3 = this.maxDynamicTableByteCount;
            if (i2 > i3) {
                clearDynamicTable();
                return;
            }
            int iEvictToRecoverBytes = evictToRecoverBytes((this.dynamicTableByteCount + i2) - i3);
            if (i == -1) {
                int i4 = this.headerCount + 1;
                Header[] headerArr = this.dynamicTable;
                if (i4 > headerArr.length) {
                    Header[] headerArr2 = new Header[headerArr.length * 2];
                    System.arraycopy(headerArr, 0, headerArr2, headerArr.length, headerArr.length);
                    this.nextHeaderIndex = this.dynamicTable.length - 1;
                    this.dynamicTable = headerArr2;
                }
                int i5 = this.nextHeaderIndex;
                this.nextHeaderIndex = i5 - 1;
                this.dynamicTable[i5] = header;
                this.headerCount++;
            } else {
                this.dynamicTable[this.nextHeaderIndex + 1 + i + iEvictToRecoverBytes + i] = header;
            }
            this.dynamicTableByteCount += i2;
        }

        private boolean isStaticHeader(int i) {
            return i >= 0 && i <= Hpack.STATIC_HEADER_TABLE.length - 1;
        }

        private int readByte() {
            return this.source.readByte() & 255;
        }

        private void readIndexedHeader(int i) throws IOException {
            if (isStaticHeader(i)) {
                this.headerList.add(Hpack.STATIC_HEADER_TABLE[i]);
                return;
            }
            int iDynamicTableIndex = dynamicTableIndex(i - Hpack.STATIC_HEADER_TABLE.length);
            if (iDynamicTableIndex >= 0) {
                Header[] headerArr = this.dynamicTable;
                if (iDynamicTableIndex < headerArr.length) {
                    this.headerList.add(headerArr[iDynamicTableIndex]);
                    return;
                }
            }
            StringBuilder sbA = a.a("Header index too large ");
            sbA.append(i + 1);
            throw new IOException(sbA.toString());
        }

        private void readLiteralHeaderWithIncrementalIndexingIndexedName(int i) {
            insertIntoDynamicTable(-1, new Header(getName(i), readByteString()));
        }

        private void readLiteralHeaderWithIncrementalIndexingNewName() throws IOException {
            j byteString = readByteString();
            Hpack.checkLowercase(byteString);
            insertIntoDynamicTable(-1, new Header(byteString, readByteString()));
        }

        private void readLiteralHeaderWithoutIndexingIndexedName(int i) throws IOException {
            this.headerList.add(new Header(getName(i), readByteString()));
        }

        private void readLiteralHeaderWithoutIndexingNewName() throws IOException {
            j byteString = readByteString();
            Hpack.checkLowercase(byteString);
            this.headerList.add(new Header(byteString, readByteString()));
        }

        public List<Header> getAndResetHeaderList() {
            ArrayList arrayList = new ArrayList(this.headerList);
            this.headerList.clear();
            return arrayList;
        }

        public int maxDynamicTableByteCount() {
            return this.maxDynamicTableByteCount;
        }

        public j readByteString() {
            int i = readByte();
            boolean z = (i & 128) == 128;
            int i2 = readInt(i, 127);
            return z ? j.a(Huffman.INSTANCE.decode(this.source.b(i2))) : this.source.d(i2);
        }

        public void readHeaders() throws IOException {
            while (!this.source.e()) {
                int i = this.source.readByte() & 255;
                if (i == 128) {
                    throw new IOException("index == 0");
                }
                if ((i & 128) == 128) {
                    readIndexedHeader(readInt(i, 127) - 1);
                } else if (i == 64) {
                    readLiteralHeaderWithIncrementalIndexingNewName();
                } else if ((i & 64) == 64) {
                    readLiteralHeaderWithIncrementalIndexingIndexedName(readInt(i, 63) - 1);
                } else if ((i & 32) == 32) {
                    this.maxDynamicTableByteCount = readInt(i, 31);
                    int i2 = this.maxDynamicTableByteCount;
                    if (i2 < 0 || i2 > this.headerTableSizeSetting) {
                        StringBuilder sbA = a.a("Invalid dynamic table size update ");
                        sbA.append(this.maxDynamicTableByteCount);
                        throw new IOException(sbA.toString());
                    }
                    adjustDynamicTableByteCount();
                } else if (i == 16 || i == 0) {
                    readLiteralHeaderWithoutIndexingNewName();
                } else {
                    readLiteralHeaderWithoutIndexingIndexedName(readInt(i, 15) - 1);
                }
            }
        }

        public int readInt(int i, int i2) {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            int i4 = 0;
            while (true) {
                int i5 = readByte();
                if ((i5 & 128) == 0) {
                    return i2 + (i5 << i4);
                }
                i2 += (i5 & 127) << i4;
                i4 += 7;
            }
        }

        public Reader(int i, int i2, C c2) {
            this.headerList = new ArrayList();
            this.dynamicTable = new Header[8];
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
            this.headerTableSizeSetting = i;
            this.maxDynamicTableByteCount = i2;
            this.source = t.a(c2);
        }
    }

    static final class Writer {
        public static final int SETTINGS_HEADER_TABLE_SIZE = 4096;
        public static final int SETTINGS_HEADER_TABLE_SIZE_LIMIT = 16384;
        public Header[] dynamicTable;
        public int dynamicTableByteCount;
        public boolean emitDynamicTableSizeUpdate;
        public int headerCount;
        public int headerTableSizeSetting;
        public int maxDynamicTableByteCount;
        public int nextHeaderIndex;
        public final g out;
        public int smallestHeaderTableSizeSetting;
        public final boolean useCompression;

        public Writer(g gVar) {
            this(4096, true, gVar);
        }

        private void adjustDynamicTableByteCount() {
            int i = this.maxDynamicTableByteCount;
            int i2 = this.dynamicTableByteCount;
            if (i < i2) {
                if (i == 0) {
                    clearDynamicTable();
                } else {
                    evictToRecoverBytes(i2 - i);
                }
            }
        }

        private void clearDynamicTable() {
            Arrays.fill(this.dynamicTable, (Object) null);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        private int evictToRecoverBytes(int i) {
            int i2 = 0;
            if (i > 0) {
                int length = this.dynamicTable.length;
                while (true) {
                    length--;
                    if (length < this.nextHeaderIndex || i <= 0) {
                        break;
                    }
                    Header[] headerArr = this.dynamicTable;
                    i -= headerArr[length].hpackSize;
                    this.dynamicTableByteCount -= headerArr[length].hpackSize;
                    this.headerCount--;
                    i2++;
                }
                Header[] headerArr2 = this.dynamicTable;
                int i3 = this.nextHeaderIndex;
                System.arraycopy(headerArr2, i3 + 1, headerArr2, i3 + 1 + i2, this.headerCount);
                Header[] headerArr3 = this.dynamicTable;
                int i4 = this.nextHeaderIndex;
                Arrays.fill(headerArr3, i4 + 1, i4 + 1 + i2, (Object) null);
                this.nextHeaderIndex += i2;
            }
            return i2;
        }

        private void insertIntoDynamicTable(Header header) {
            int i = header.hpackSize;
            int i2 = this.maxDynamicTableByteCount;
            if (i > i2) {
                clearDynamicTable();
                return;
            }
            evictToRecoverBytes((this.dynamicTableByteCount + i) - i2);
            int i3 = this.headerCount + 1;
            Header[] headerArr = this.dynamicTable;
            if (i3 > headerArr.length) {
                Header[] headerArr2 = new Header[headerArr.length * 2];
                System.arraycopy(headerArr, 0, headerArr2, headerArr.length, headerArr.length);
                this.nextHeaderIndex = this.dynamicTable.length - 1;
                this.dynamicTable = headerArr2;
            }
            int i4 = this.nextHeaderIndex;
            this.nextHeaderIndex = i4 - 1;
            this.dynamicTable[i4] = header;
            this.headerCount++;
            this.dynamicTableByteCount += i;
        }

        public void setHeaderTableSizeSetting(int i) {
            this.headerTableSizeSetting = i;
            int iMin = Math.min(i, 16384);
            int i2 = this.maxDynamicTableByteCount;
            if (i2 == iMin) {
                return;
            }
            if (iMin < i2) {
                this.smallestHeaderTableSizeSetting = Math.min(this.smallestHeaderTableSizeSetting, iMin);
            }
            this.emitDynamicTableSizeUpdate = true;
            this.maxDynamicTableByteCount = iMin;
            adjustDynamicTableByteCount();
        }

        public void writeByteString(j jVar) {
            if (!this.useCompression || Huffman.INSTANCE.encodedLength(jVar) >= jVar.g()) {
                writeInt(jVar.g(), 127, 0);
                this.out.a(jVar);
                return;
            }
            g gVar = new g();
            Huffman.INSTANCE.encode(jVar, gVar);
            j jVarN = gVar.n();
            writeInt(jVarN.g(), 127, 128);
            this.out.a(jVarN);
        }

        /* JADX WARN: Code duplicated, block: B:22:0x006b  */
        /* JADX WARN: Code duplicated, block: B:26:0x0072  */
        /* JADX WARN: Code duplicated, block: B:28:0x007a  */
        /* JADX WARN: Code duplicated, block: B:30:0x0086  */
        /* JADX WARN: Code duplicated, block: B:33:0x009a A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:34:0x009c  */
        /* JADX WARN: Code duplicated, block: B:37:0x00a9  */
        /* JADX WARN: Code duplicated, block: B:38:0x00b1  */
        /* JADX WARN: Code duplicated, block: B:40:0x00b5  */
        /* JADX WARN: Code duplicated, block: B:41:0x00c4  */
        /* JADX WARN: Code duplicated, block: B:46:0x00dd  */
        /* JADX WARN: Code duplicated, block: B:55:0x0092 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:57:0x00a4 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:58:0x00a4 A[SYNTHETIC] */
        public void writeHeaders(List<Header> list) {
            int length;
            int length2;
            int i;
            int length3;
            if (this.emitDynamicTableSizeUpdate) {
                int i2 = this.smallestHeaderTableSizeSetting;
                if (i2 < this.maxDynamicTableByteCount) {
                    writeInt(i2, 31, 32);
                }
                this.emitDynamicTableSizeUpdate = false;
                this.smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
                writeInt(this.maxDynamicTableByteCount, 31, 32);
            }
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                Header header = list.get(i3);
                j jVarH = header.name.h();
                j jVar = header.value;
                Integer num = Hpack.NAME_TO_FIRST_INDEX.get(jVarH);
                if (num != null) {
                    length = num.intValue() + 1;
                    if (length <= 1 || length >= 8) {
                        length2 = length;
                        length = -1;
                    } else if (!Util.equal(Hpack.STATIC_HEADER_TABLE[length - 1].value, jVar)) {
                        if (Util.equal(Hpack.STATIC_HEADER_TABLE[length].value, jVar)) {
                            length2 = length;
                            length++;
                        } else {
                            length2 = length;
                            length = -1;
                        }
                    }
                    if (length == -1) {
                        length3 = this.dynamicTable.length;
                        for (i = this.nextHeaderIndex + 1; i < length3; i++) {
                            if (!Util.equal(this.dynamicTable[i].name, jVarH)) {
                                if (Util.equal(this.dynamicTable[i].value, jVar)) {
                                    length = Hpack.STATIC_HEADER_TABLE.length + (i - this.nextHeaderIndex);
                                    break;
                                } else if (length2 == -1) {
                                    length2 = (i - this.nextHeaderIndex) + Hpack.STATIC_HEADER_TABLE.length;
                                }
                            }
                        }
                    }
                    if (length != -1) {
                        writeInt(length, 127, 128);
                    } else if (length2 == -1) {
                        this.out.writeByte(64);
                        writeByteString(jVarH);
                        writeByteString(jVar);
                        insertIntoDynamicTable(header);
                    } else if (jVarH.a(Header.PSEUDO_PREFIX) || Header.TARGET_AUTHORITY.equals(jVarH)) {
                        writeInt(length2, 63, 64);
                        writeByteString(jVar);
                        insertIntoDynamicTable(header);
                    } else {
                        writeInt(length2, 15, 0);
                        writeByteString(jVar);
                    }
                } else {
                    length = -1;
                }
                length2 = length;
                if (length == -1) {
                    length3 = this.dynamicTable.length;
                    while (i < length3) {
                        if (!Util.equal(this.dynamicTable[i].name, jVarH)) {
                            if (Util.equal(this.dynamicTable[i].value, jVar)) {
                                length = Hpack.STATIC_HEADER_TABLE.length + (i - this.nextHeaderIndex);
                                break;
                            } else if (length2 == -1) {
                                length2 = (i - this.nextHeaderIndex) + Hpack.STATIC_HEADER_TABLE.length;
                            }
                        }
                    }
                }
                if (length != -1) {
                    writeInt(length, 127, 128);
                } else if (length2 == -1) {
                    this.out.writeByte(64);
                    writeByteString(jVarH);
                    writeByteString(jVar);
                    insertIntoDynamicTable(header);
                } else if (jVarH.a(Header.PSEUDO_PREFIX)) {
                    writeInt(length2, 63, 64);
                    writeByteString(jVar);
                    insertIntoDynamicTable(header);
                } else {
                    writeInt(length2, 63, 64);
                    writeByteString(jVar);
                    insertIntoDynamicTable(header);
                }
            }
        }

        public void writeInt(int i, int i2, int i3) {
            if (i < i2) {
                this.out.writeByte(i | i3);
                return;
            }
            this.out.writeByte(i3 | i2);
            int i4 = i - i2;
            while (i4 >= 128) {
                this.out.writeByte(128 | (i4 & 127));
                i4 >>>= 7;
            }
            this.out.writeByte(i4);
        }

        public Writer(int i, boolean z, g gVar) {
            this.smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
            this.dynamicTable = new Header[8];
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
            this.headerTableSizeSetting = i;
            this.maxDynamicTableByteCount = i;
            this.useCompression = z;
            this.out = gVar;
        }
    }

    public static j checkLowercase(j jVar) throws IOException {
        int iG = jVar.g();
        for (int i = 0; i < iG; i++) {
            byte bA = jVar.a(i);
            if (bA >= 65 && bA <= 90) {
                StringBuilder sbA = a.a("PROTOCOL_ERROR response malformed: mixed case name: ");
                sbA.append(jVar.j());
                throw new IOException(sbA.toString());
            }
        }
        return jVar;
    }

    public static Map<j, Integer> nameToFirstIndex() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(STATIC_HEADER_TABLE.length);
        int i = 0;
        while (true) {
            Header[] headerArr = STATIC_HEADER_TABLE;
            if (i >= headerArr.length) {
                return Collections.unmodifiableMap(linkedHashMap);
            }
            if (!linkedHashMap.containsKey(headerArr[i].name)) {
                linkedHashMap.put(STATIC_HEADER_TABLE[i].name, Integer.valueOf(i));
            }
            i++;
        }
    }
}
