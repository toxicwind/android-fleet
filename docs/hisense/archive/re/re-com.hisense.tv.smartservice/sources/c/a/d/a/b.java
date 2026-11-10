package c.a.d.a;

import c.a.C0223ga;
import c.a.S;
import c.a.za;
import com.google.common.base.Preconditions;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile ExtensionRegistryLite f1767a = ExtensionRegistryLite.getEmptyRegistry();

    public static <T extends MessageLite> C0223ga.b<T> a(T t) {
        return new a(t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final class a<T extends MessageLite> implements C0223ga.d<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final ThreadLocal<Reference<byte[]>> f1768a = new ThreadLocal<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Parser<T> f1769b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final T f1770c;

        public a(T t) {
            this.f1770c = t;
            this.f1769b = (Parser<T>) t.getParserForType();
        }

        public InputStream a(Object obj) {
            return new c.a.d.a.a((MessageLite) obj, this.f1769b);
        }

        public Object a(InputStream inputStream) {
            byte[] bArr;
            if ((inputStream instanceof c.a.d.a.a) && ((c.a.d.a.a) inputStream).f1765b == this.f1769b) {
                try {
                    MessageLite messageLite = ((c.a.d.a.a) inputStream).f1764a;
                    if (messageLite != null) {
                        return messageLite;
                    }
                    throw new IllegalStateException("message not available");
                } catch (IllegalStateException unused) {
                }
            }
            CodedInputStream codedInputStreamNewInstance = null;
            try {
                if (inputStream instanceof S) {
                    int iAvailable = inputStream.available();
                    if (iAvailable > 0 && iAvailable <= 4194304) {
                        Reference<byte[]> reference = f1768a.get();
                        if (reference == null || (bArr = reference.get()) == null || bArr.length < iAvailable) {
                            bArr = new byte[iAvailable];
                            f1768a.set(new WeakReference(bArr));
                        }
                        int i = iAvailable;
                        while (i > 0) {
                            int i2 = inputStream.read(bArr, iAvailable - i, i);
                            if (i2 == -1) {
                                break;
                            }
                            i -= i2;
                        }
                        if (i == 0) {
                            codedInputStreamNewInstance = CodedInputStream.newInstance(bArr, 0, iAvailable);
                        } else {
                            throw new RuntimeException("size inaccurate: " + iAvailable + " != " + (iAvailable - i));
                        }
                    } else if (iAvailable == 0) {
                        return this.f1770c;
                    }
                }
                if (codedInputStreamNewInstance == null) {
                    codedInputStreamNewInstance = CodedInputStream.newInstance(inputStream);
                }
                codedInputStreamNewInstance.setSizeLimit(Integer.MAX_VALUE);
                try {
                    T from = this.f1769b.parseFrom(codedInputStreamNewInstance, b.f1767a);
                    try {
                        codedInputStreamNewInstance.checkLastTagWas(0);
                        return from;
                    } catch (InvalidProtocolBufferException e2) {
                        e2.setUnfinishedMessage(from);
                        throw e2;
                    }
                } catch (InvalidProtocolBufferException e3) {
                    throw za.k.b("Invalid protobuf byte sequence").b(e3).b();
                }
            } catch (IOException e4) {
                throw new RuntimeException(e4);
            }
        }
    }

    public static long a(InputStream inputStream, OutputStream outputStream) throws IOException {
        Preconditions.checkNotNull(inputStream, "inputStream cannot be null!");
        Preconditions.checkNotNull(outputStream, "outputStream cannot be null!");
        byte[] bArr = new byte[8192];
        long j = 0;
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return j;
            }
            outputStream.write(bArr, 0, i);
            j += (long) i;
        }
    }
}
