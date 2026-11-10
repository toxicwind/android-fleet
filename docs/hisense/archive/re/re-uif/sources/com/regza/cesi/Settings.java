package com.regza.cesi;

import com.fasterxml.jackson.databind.JsonNode;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: CesiConnection.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Settings {

    @Nullable
    private JsonNode constants;
    private long parallelisum;
    private boolean verbose;

    public Settings() {
        this(null, 0L, false, 7, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Settings)) {
            return false;
        }
        Settings settings = (Settings) obj;
        return Intrinsics.areEqual(this.constants, settings.constants) && this.parallelisum == settings.parallelisum && this.verbose == settings.verbose;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v6, types: [int] */
    /* JADX WARN: Type inference failed for: r5v2, types: [int] */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    public int hashCode() {
        JsonNode jsonNode = this.constants;
        int iHashCode = jsonNode != null ? jsonNode.hashCode() : 0;
        long j = this.parallelisum;
        int i = ((iHashCode * 31) + ((int) (j ^ (j >>> 32)))) * 31;
        boolean z = this.verbose;
        ?? r5 = z;
        if (z) {
            r5 = 1;
        }
        return i + r5;
    }

    @NotNull
    public String toString() {
        return "Settings(constants=" + this.constants + ", parallelisum=" + this.parallelisum + ", verbose=" + this.verbose + ")";
    }

    public Settings(@Nullable JsonNode jsonNode, long j, boolean z) {
        this.constants = jsonNode;
        this.parallelisum = j;
        this.verbose = z;
    }

    public /* synthetic */ Settings(JsonNode jsonNode, long j, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : jsonNode, (i & 2) != 0 ? 10L : j, (i & 4) != 0 ? false : z);
    }

    @Nullable
    public final JsonNode getConstants() {
        return this.constants;
    }

    public final long getParallelisum() {
        return this.parallelisum;
    }

    public final boolean getVerbose() {
        return this.verbose;
    }
}
