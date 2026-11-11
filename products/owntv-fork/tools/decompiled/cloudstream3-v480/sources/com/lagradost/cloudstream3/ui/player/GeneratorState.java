package com.lagradost.cloudstream3.ui.player;

import com.lagradost.cloudstream3.LoadResponse;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayerGeneratorViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001\u0012\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000b\u0010\fJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0017JX\u0010\u001f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010 J\u0014\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010$\u001a\u00020\tHÖ\u0081\u0004J\n\u0010%\u001a\u00020&HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0017\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/GeneratorState;", "", "meta", "nextMeta", "allMeta", "", "response", "Lcom/lagradost/cloudstream3/LoadResponse;", "index", "", "id", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/List;Lcom/lagradost/cloudstream3/LoadResponse;ILjava/lang/Integer;)V", "getMeta", "()Ljava/lang/Object;", "getNextMeta", "getAllMeta", "()Ljava/util/List;", "getResponse", "()Lcom/lagradost/cloudstream3/LoadResponse;", "getIndex", "()I", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/List;Lcom/lagradost/cloudstream3/LoadResponse;ILjava/lang/Integer;)Lcom/lagradost/cloudstream3/ui/player/GeneratorState;", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class GeneratorState {
    private final List<?> allMeta;
    private final Integer id;
    private final int index;
    private final Object meta;
    private final Object nextMeta;
    private final LoadResponse response;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ GeneratorState copy$default(GeneratorState generatorState, Object obj, Object obj2, List list, LoadResponse loadResponse, int i, Integer num, int i2, Object obj3) {
        if ((i2 & 1) != 0) {
            obj = generatorState.meta;
        }
        if ((i2 & 2) != 0) {
            obj2 = generatorState.nextMeta;
        }
        if ((i2 & 4) != 0) {
            list = generatorState.allMeta;
        }
        if ((i2 & 8) != 0) {
            loadResponse = generatorState.response;
        }
        if ((i2 & 16) != 0) {
            i = generatorState.index;
        }
        if ((i2 & 32) != 0) {
            num = generatorState.id;
        }
        int i3 = i;
        Integer num2 = num;
        return generatorState.copy(obj, obj2, list, loadResponse, i3, num2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Object getMeta() {
        return this.meta;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Object getNextMeta() {
        return this.nextMeta;
    }

    public final List<?> component3() {
        return this.allMeta;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final LoadResponse getResponse() {
        return this.response;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    public final GeneratorState copy(Object meta, Object nextMeta, List<?> allMeta, LoadResponse response, int index, Integer id) {
        return new GeneratorState(meta, nextMeta, allMeta, response, index, id);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GeneratorState)) {
            return false;
        }
        GeneratorState generatorState = (GeneratorState) other;
        return Intrinsics.areEqual(this.meta, generatorState.meta) && Intrinsics.areEqual(this.nextMeta, generatorState.nextMeta) && Intrinsics.areEqual(this.allMeta, generatorState.allMeta) && Intrinsics.areEqual(this.response, generatorState.response) && this.index == generatorState.index && Intrinsics.areEqual(this.id, generatorState.id);
    }

    public int hashCode() {
        Object obj = this.meta;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.nextMeta;
        int iHashCode2 = (iHashCode + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        List<?> list = this.allMeta;
        int iHashCode3 = (iHashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        LoadResponse loadResponse = this.response;
        int iHashCode4 = (((iHashCode3 + (loadResponse == null ? 0 : loadResponse.hashCode())) * 31) + this.index) * 31;
        Integer num = this.id;
        return iHashCode4 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "GeneratorState(meta=" + this.meta + ", nextMeta=" + this.nextMeta + ", allMeta=" + this.allMeta + ", response=" + this.response + ", index=" + this.index + ", id=" + this.id + ')';
    }

    public GeneratorState(Object obj, Object obj2, List<?> list, LoadResponse loadResponse, int i, Integer num) {
        this.meta = obj;
        this.nextMeta = obj2;
        this.allMeta = list;
        this.response = loadResponse;
        this.index = i;
        this.id = num;
    }

    public final Object getMeta() {
        return this.meta;
    }

    public final Object getNextMeta() {
        return this.nextMeta;
    }

    public final List<?> getAllMeta() {
        return this.allMeta;
    }

    public final LoadResponse getResponse() {
        return this.response;
    }

    public final int getIndex() {
        return this.index;
    }

    public final Integer getId() {
        return this.id;
    }
}
