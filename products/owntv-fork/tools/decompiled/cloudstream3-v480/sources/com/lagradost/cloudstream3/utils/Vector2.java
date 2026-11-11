package com.lagradost.cloudstream3.utils;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Vector2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000H\u0086\u0002J\u0011\u0010\f\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000H\u0086\u0002J\u0011\u0010\r\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u000eH\u0086\u0002J\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004J\u000e\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0000J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u000eHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u001b\u0010\u0012\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0013\u0010\bR\u001b\u0010\u0016\u001a\u00020\u00038FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0015\u001a\u0004\b\u0017\u0010\b¨\u0006\u001f"}, d2 = {"Lcom/lagradost/cloudstream3/utils/Vector2;", "", "x", "", "y", "<init>", "(FF)V", "getX", "()F", "getY", "minus", "other", "plus", "times", "", "toString", "", "distanceTo", "lengthSquared", "getLengthSquared", "lengthSquared$delegate", "Lkotlin/Lazy;", "length", "getLength", "length$delegate", "component1", "component2", "copy", "equals", "", "hashCode", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Vector2 {
    private final float x;
    private final float y;

    /* JADX INFO: renamed from: lengthSquared$delegate, reason: from kotlin metadata */
    private final Lazy lengthSquared = LazyKt.lazy(new Function0() { // from class: com.lagradost.cloudstream3.utils.Vector2$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Float.valueOf(Vector2.lengthSquared_delegate$lambda$0(this.f$0));
        }
    });

    /* JADX INFO: renamed from: length$delegate, reason: from kotlin metadata */
    private final Lazy length = LazyKt.lazy(new Function0() { // from class: com.lagradost.cloudstream3.utils.Vector2$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Float.valueOf(Vector2.length_delegate$lambda$0(this.f$0));
        }
    });

    public static /* synthetic */ Vector2 copy$default(Vector2 vector2, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = vector2.x;
        }
        if ((i & 2) != 0) {
            f2 = vector2.y;
        }
        return vector2.copy(f, f2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getX() {
        return this.x;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getY() {
        return this.y;
    }

    public final Vector2 copy(float x, float y) {
        return new Vector2(x, y);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vector2)) {
            return false;
        }
        Vector2 vector2 = (Vector2) other;
        return Float.compare(this.x, vector2.x) == 0 && Float.compare(this.y, vector2.y) == 0;
    }

    public int hashCode() {
        return (Float.floatToIntBits(this.x) * 31) + Float.floatToIntBits(this.y);
    }

    public Vector2(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public final Vector2 minus(Vector2 other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    public final Vector2 plus(Vector2 other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    public final Vector2 times(int other) {
        float f = other;
        return new Vector2(this.x * f, this.y * f);
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ')';
    }

    public final float distanceTo(Vector2 other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return minus(other).getLength();
    }

    private final float getLengthSquared() {
        return ((Number) this.lengthSquared.getValue()).floatValue();
    }

    static final float lengthSquared_delegate$lambda$0(Vector2 vector2) {
        float f = vector2.x;
        float f2 = vector2.y;
        return (f * f) + (f2 * f2);
    }

    static final float length_delegate$lambda$0(Vector2 vector2) {
        return (float) Math.sqrt(vector2.getLengthSquared());
    }

    public final float getLength() {
        return ((Number) this.length.getValue()).floatValue();
    }
}
