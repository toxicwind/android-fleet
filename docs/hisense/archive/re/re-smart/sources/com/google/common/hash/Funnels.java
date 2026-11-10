package com.google.common.hash;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@Beta
public final class Funnels {

    private enum ByteArrayFunnel implements Funnel<byte[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Funnels.byteArrayFunnel()";
        }

        @Override // com.google.common.hash.Funnel
        public void funnel(byte[] bArr, PrimitiveSink primitiveSink) {
            primitiveSink.putBytes(bArr);
        }
    }

    private enum IntegerFunnel implements Funnel<Integer> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Funnels.integerFunnel()";
        }

        @Override // com.google.common.hash.Funnel
        public void funnel(Integer num, PrimitiveSink primitiveSink) {
            primitiveSink.putInt(num.intValue());
        }
    }

    private enum LongFunnel implements Funnel<Long> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Funnels.longFunnel()";
        }

        @Override // com.google.common.hash.Funnel
        public void funnel(Long l, PrimitiveSink primitiveSink) {
            primitiveSink.putLong(l.longValue());
        }
    }

    private static class SequentialFunnel<E> implements Funnel<Iterable<? extends E>>, Serializable {
        public final Funnel<E> elementFunnel;

        public SequentialFunnel(Funnel<E> funnel) {
            if (funnel == null) {
                throw new NullPointerException();
            }
            this.elementFunnel = funnel;
        }

        public boolean equals(Object obj) {
            if (obj instanceof SequentialFunnel) {
                return this.elementFunnel.equals(((SequentialFunnel) obj).elementFunnel);
            }
            return false;
        }

        public int hashCode() {
            return SequentialFunnel.class.hashCode() ^ this.elementFunnel.hashCode();
        }

        public String toString() {
            String strValueOf = String.valueOf(this.elementFunnel);
            return a.a(strValueOf.length() + 26, "Funnels.sequentialFunnel(", strValueOf, ")");
        }

        @Override // com.google.common.hash.Funnel
        public void funnel(Iterable<? extends E> iterable, PrimitiveSink primitiveSink) {
            Iterator<? extends E> it = iterable.iterator();
            while (it.hasNext()) {
                this.elementFunnel.funnel(it.next(), primitiveSink);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static class SinkAsStream extends OutputStream {
        public final PrimitiveSink sink;

        public SinkAsStream(PrimitiveSink primitiveSink) {
            if (primitiveSink == null) {
                throw new NullPointerException();
            }
            this.sink = primitiveSink;
        }

        public String toString() {
            String strValueOf = String.valueOf(this.sink);
            return a.a(strValueOf.length() + 24, "Funnels.asOutputStream(", strValueOf, ")");
        }

        @Override // java.io.OutputStream
        public void write(int i) {
            this.sink.putByte((byte) i);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) {
            this.sink.putBytes(bArr);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) {
            this.sink.putBytes(bArr, i, i2);
        }
    }

    private static class StringCharsetFunnel implements Funnel<CharSequence>, Serializable {
        public final Charset charset;

        private static class SerializedForm implements Serializable {
            public static final long serialVersionUID = 0;
            public final String charsetCanonicalName;

            public SerializedForm(Charset charset) {
                this.charsetCanonicalName = charset.name();
            }

            private Object readResolve() {
                return new StringCharsetFunnel(Charset.forName(this.charsetCanonicalName));
            }
        }

        public StringCharsetFunnel(Charset charset) {
            if (charset == null) {
                throw new NullPointerException();
            }
            this.charset = charset;
        }

        public boolean equals(Object obj) {
            if (obj instanceof StringCharsetFunnel) {
                return this.charset.equals(((StringCharsetFunnel) obj).charset);
            }
            return false;
        }

        public int hashCode() {
            return StringCharsetFunnel.class.hashCode() ^ this.charset.hashCode();
        }

        public String toString() {
            String strName = this.charset.name();
            return a.a(a.a((Object) strName, 22), "Funnels.stringFunnel(", strName, ")");
        }

        public Object writeReplace() {
            return new SerializedForm(this.charset);
        }

        @Override // com.google.common.hash.Funnel
        public void funnel(CharSequence charSequence, PrimitiveSink primitiveSink) {
            primitiveSink.putString(charSequence, this.charset);
        }
    }

    private enum UnencodedCharsFunnel implements Funnel<CharSequence> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Funnels.unencodedCharsFunnel()";
        }

        @Override // com.google.common.hash.Funnel
        public void funnel(CharSequence charSequence, PrimitiveSink primitiveSink) {
            primitiveSink.putUnencodedChars(charSequence);
        }
    }

    public static OutputStream asOutputStream(PrimitiveSink primitiveSink) {
        return new SinkAsStream(primitiveSink);
    }

    public static Funnel<byte[]> byteArrayFunnel() {
        return ByteArrayFunnel.INSTANCE;
    }

    public static Funnel<Integer> integerFunnel() {
        return IntegerFunnel.INSTANCE;
    }

    public static Funnel<Long> longFunnel() {
        return LongFunnel.INSTANCE;
    }

    public static <E> Funnel<Iterable<? extends E>> sequentialFunnel(Funnel<E> funnel) {
        return new SequentialFunnel(funnel);
    }

    public static Funnel<CharSequence> stringFunnel(Charset charset) {
        return new StringCharsetFunnel(charset);
    }

    public static Funnel<CharSequence> unencodedCharsFunnel() {
        return UnencodedCharsFunnel.INSTANCE;
    }
}
