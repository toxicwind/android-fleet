package com.google.firebase.firestore.bundle;

import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.storage.StorageMetadata;
import e.b.d;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public class BundleReader {
    public static final int BUFFER_CAPACITY = 1024;
    public static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    public ByteBuffer buffer = ByteBuffer.allocate(1024);
    public final InputStream bundleInputStream;
    public long bytesRead;
    public final InputStreamReader dataReader;

    @Nullable
    public BundleMetadata metadata;
    public final BundleSerializer serializer;

    public BundleReader(BundleSerializer bundleSerializer, InputStream inputStream) {
        this.serializer = bundleSerializer;
        this.bundleInputStream = inputStream;
        this.dataReader = new InputStreamReader(this.bundleInputStream);
        this.buffer.flip();
    }

    private IllegalArgumentException abort(String str) throws IOException {
        close();
        throw new IllegalArgumentException(a.a("Invalid bundle: ", str));
    }

    private BundleElement decodeBundleElement(String str) throws IOException {
        d dVar = new d(str);
        if (dVar.f2163b.containsKey(StorageMetadata.CUSTOM_METADATA_KEY)) {
            BundleMetadata bundleMetadataDecodeBundleMetadata = this.serializer.decodeBundleMetadata(dVar.f(StorageMetadata.CUSTOM_METADATA_KEY));
            Logger.debug("BundleElement", "BundleMetadata element loaded", new Object[0]);
            return bundleMetadataDecodeBundleMetadata;
        }
        if (dVar.f2163b.containsKey("namedQuery")) {
            NamedQuery namedQueryDecodeNamedQuery = this.serializer.decodeNamedQuery(dVar.f("namedQuery"));
            StringBuilder sbA = a.a("Query loaded: ");
            sbA.append(namedQueryDecodeNamedQuery.getName());
            Logger.debug("BundleElement", sbA.toString(), new Object[0]);
            return namedQueryDecodeNamedQuery;
        }
        if (dVar.f2163b.containsKey("documentMetadata")) {
            BundledDocumentMetadata bundledDocumentMetadataDecodeBundledDocumentMetadata = this.serializer.decodeBundledDocumentMetadata(dVar.f("documentMetadata"));
            StringBuilder sbA2 = a.a("Document metadata loaded: ");
            sbA2.append(bundledDocumentMetadataDecodeBundledDocumentMetadata.getKey());
            Logger.debug("BundleElement", sbA2.toString(), new Object[0]);
            return bundledDocumentMetadataDecodeBundledDocumentMetadata;
        }
        if (!dVar.f2163b.containsKey("document")) {
            abort(a.a("Cannot decode unknown Bundle element: ", str));
            throw null;
        }
        BundleDocument bundleDocumentDecodeDocument = this.serializer.decodeDocument(dVar.f("document"));
        StringBuilder sbA3 = a.a("Document loaded: ");
        sbA3.append(bundleDocumentDecodeDocument.getKey());
        Logger.debug("BundleElement", sbA3.toString(), new Object[0]);
        return bundleDocumentDecodeDocument;
    }

    private int indexOfOpenBracket() {
        this.buffer.mark();
        int i = 0;
        while (i < this.buffer.remaining()) {
            try {
                if (this.buffer.get() == 123) {
                    return i;
                }
                i++;
            } finally {
                this.buffer.reset();
            }
        }
        i = -1;
        return i;
    }

    private boolean pullMoreData() throws IOException {
        this.buffer.compact();
        int i = this.bundleInputStream.read(this.buffer.array(), this.buffer.position() + this.buffer.arrayOffset(), this.buffer.remaining());
        boolean z = i > 0;
        if (z) {
            ByteBuffer byteBuffer = this.buffer;
            byteBuffer.position(byteBuffer.position() + i);
        }
        this.buffer.flip();
        return z;
    }

    private String readJsonString(int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (i > 0) {
            if (this.buffer.remaining() == 0 && !pullMoreData()) {
                abort("Reached the end of bundle when more data was expected.");
                throw null;
            }
            int iMin = Math.min(i, this.buffer.remaining());
            byteArrayOutputStream.write(this.buffer.array(), this.buffer.position() + this.buffer.arrayOffset(), iMin);
            ByteBuffer byteBuffer = this.buffer;
            byteBuffer.position(byteBuffer.position() + iMin);
            i -= iMin;
        }
        return byteArrayOutputStream.toString(UTF8_CHARSET.name());
    }

    @Nullable
    private String readLengthPrefix() throws IOException {
        int iIndexOfOpenBracket;
        do {
            iIndexOfOpenBracket = indexOfOpenBracket();
            if (iIndexOfOpenBracket != -1) {
                break;
            }
        } while (pullMoreData());
        if (this.buffer.remaining() == 0) {
            return null;
        }
        if (iIndexOfOpenBracket == -1) {
            abort("Reached the end of bundle when a length string is expected.");
            throw null;
        }
        byte[] bArr = new byte[iIndexOfOpenBracket];
        this.buffer.get(bArr);
        return UTF8_CHARSET.decode(ByteBuffer.wrap(bArr)).toString();
    }

    @Nullable
    private BundleElement readNextElement() throws IOException {
        String lengthPrefix = readLengthPrefix();
        if (lengthPrefix == null) {
            return null;
        }
        int i = Integer.parseInt(lengthPrefix);
        String jsonString = readJsonString(i);
        this.bytesRead += (long) (lengthPrefix.getBytes(UTF8_CHARSET).length + i);
        return decodeBundleElement(jsonString);
    }

    public void close() throws IOException {
        this.bundleInputStream.close();
    }

    public BundleMetadata getBundleMetadata() throws IOException {
        BundleMetadata bundleMetadata = this.metadata;
        if (bundleMetadata != null) {
            return bundleMetadata;
        }
        BundleElement nextElement = readNextElement();
        if (!(nextElement instanceof BundleMetadata)) {
            abort("Expected first element in bundle to be a metadata object");
            throw null;
        }
        this.metadata = (BundleMetadata) nextElement;
        this.bytesRead = 0L;
        return this.metadata;
    }

    public long getBytesRead() {
        return this.bytesRead;
    }

    public BundleElement getNextElement() throws IOException {
        getBundleMetadata();
        return readNextElement();
    }
}
