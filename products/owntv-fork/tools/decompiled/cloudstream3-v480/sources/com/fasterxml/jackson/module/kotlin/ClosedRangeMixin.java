package com.fasterxml.jackson.module.kotlin;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kotlin.Metadata;

/* JADX INFO: compiled from: KotlinMixins.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0017\b\u0007\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J\r\u0010\n\u001a\u00028\u0000H'¢\u0006\u0002\u0010\u0007J\r\u0010\u000b\u001a\u00028\u0000H'¢\u0006\u0002\u0010\u0007J\r\u0010\f\u001a\u00028\u0000H'¢\u0006\u0002\u0010\u0007J\r\u0010\r\u001a\u00028\u0000H'¢\u0006\u0002\u0010\u0007J\r\u0010\u000e\u001a\u00028\u0000H'¢\u0006\u0002\u0010\u0007J\b\u0010\u000f\u001a\u00020\u0010H'R\u0015\u0010\u0004\u001a\u00028\u00008\u0007¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\t\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ClosedRangeMixin;", ExifInterface.GPS_DIRECTION_TRUE, "", "start", "endInclusive", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getEndInclusive", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getStart", "getEnd", "getFirst", "getIncrement", "getLast", "getStep", "isEmpty", "", "jackson-module-kotlin"}, k = 1, mv = {1, 5, 1}, xi = 48)
public abstract class ClosedRangeMixin<T> {
    private final T endInclusive;
    private final T start;

    @JsonIgnore
    public abstract T getEnd();

    @JsonIgnore
    public abstract T getFirst();

    @JsonIgnore
    public abstract T getIncrement();

    @JsonIgnore
    public abstract T getLast();

    @JsonIgnore
    public abstract T getStep();

    @JsonIgnore
    public abstract boolean isEmpty();

    @JsonCreator
    public ClosedRangeMixin(T t, T t2) {
        this.start = t;
        this.endInclusive = t2;
    }

    @JsonProperty(TtmlNode.END)
    public final T getEndInclusive() {
        return this.endInclusive;
    }

    public final T getStart() {
        return this.start;
    }
}
