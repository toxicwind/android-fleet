package c.a.d.a;

import c.a.A;
import c.a.S;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends InputStream implements A, S {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public MessageLite f1764a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Parser<?> f1765b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ByteArrayInputStream f1766c;

    public a(MessageLite messageLite, Parser<?> parser) {
        this.f1764a = messageLite;
        this.f1765b = parser;
    }

    @Override // java.io.InputStream
    public int available() {
        MessageLite messageLite = this.f1764a;
        if (messageLite != null) {
            return messageLite.getSerializedSize();
        }
        ByteArrayInputStream byteArrayInputStream = this.f1766c;
        if (byteArrayInputStream != null) {
            return byteArrayInputStream.available();
        }
        return 0;
    }

    @Override // java.io.InputStream
    public int read() {
        MessageLite messageLite = this.f1764a;
        if (messageLite != null) {
            this.f1766c = new ByteArrayInputStream(messageLite.toByteArray());
            this.f1764a = null;
        }
        ByteArrayInputStream byteArrayInputStream = this.f1766c;
        if (byteArrayInputStream != null) {
            return byteArrayInputStream.read();
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        MessageLite messageLite = this.f1764a;
        if (messageLite != null) {
            int serializedSize = messageLite.getSerializedSize();
            if (serializedSize == 0) {
                this.f1764a = null;
                this.f1766c = null;
                return -1;
            }
            if (i2 >= serializedSize) {
                CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(bArr, i, serializedSize);
                this.f1764a.writeTo(codedOutputStreamNewInstance);
                codedOutputStreamNewInstance.flush();
                codedOutputStreamNewInstance.checkNoSpaceLeft();
                this.f1764a = null;
                this.f1766c = null;
                return serializedSize;
            }
            this.f1766c = new ByteArrayInputStream(this.f1764a.toByteArray());
            this.f1764a = null;
        }
        ByteArrayInputStream byteArrayInputStream = this.f1766c;
        if (byteArrayInputStream != null) {
            return byteArrayInputStream.read(bArr, i, i2);
        }
        return -1;
    }
}
