package j$.util;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes3.dex */
public final class OptionalLong {
    private static final OptionalLong EMPTY = new OptionalLong();
    private final boolean isPresent;
    private final long value;

    /* JADX INFO: renamed from: j$.util.OptionalLong$0, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass0 {
        public static /* synthetic */ int m(long j) {
            return (int) (j ^ (j >>> 32));
        }
    }

    private OptionalLong() {
        this.isPresent = false;
        this.value = 0L;
    }

    public static OptionalLong empty() {
        return EMPTY;
    }

    private OptionalLong(long j) {
        this.isPresent = true;
        this.value = j;
    }

    public static OptionalLong of(long j) {
        return new OptionalLong(j);
    }

    public long getAsLong() {
        if (!this.isPresent) {
            throw new NoSuchElementException("No value present");
        }
        return this.value;
    }

    public boolean isPresent() {
        return this.isPresent;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OptionalLong)) {
            return false;
        }
        OptionalLong optionalLong = (OptionalLong) obj;
        boolean z = this.isPresent;
        if (z && optionalLong.isPresent) {
            return this.value == optionalLong.value;
        }
        return z == optionalLong.isPresent;
    }

    public int hashCode() {
        if (this.isPresent) {
            return AnonymousClass0.m(this.value);
        }
        return 0;
    }

    public String toString() {
        return this.isPresent ? String.format("OptionalLong[%s]", Long.valueOf(this.value)) : "OptionalLong.empty";
    }
}
