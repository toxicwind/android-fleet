package com.lagradost.cloudstream3;

import com.fasterxml.jackson.annotation.JsonProperty;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/SettingsJson;", "", "enableAdult", "", "<init>", "(Z)V", "getEnableAdult", "()Z", "setEnableAdult", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SettingsJson {

    @JsonProperty("enableAdult")
    private boolean enableAdult;

    public SettingsJson() {
        this(false, 1, null);
    }

    public static /* synthetic */ SettingsJson copy$default(SettingsJson settingsJson, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = settingsJson.enableAdult;
        }
        return settingsJson.copy(z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getEnableAdult() {
        return this.enableAdult;
    }

    public final SettingsJson copy(@JsonProperty("enableAdult") boolean enableAdult) {
        return new SettingsJson(enableAdult);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SettingsJson) && this.enableAdult == ((SettingsJson) other).enableAdult;
    }

    public int hashCode() {
        return NextAiring$$ExternalSyntheticBackport0.m(this.enableAdult);
    }

    public String toString() {
        return "SettingsJson(enableAdult=" + this.enableAdult + ')';
    }

    public SettingsJson(@JsonProperty("enableAdult") boolean z) {
        this.enableAdult = z;
    }

    public /* synthetic */ SettingsJson(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public final boolean getEnableAdult() {
        return this.enableAdult;
    }

    public final void setEnableAdult(boolean z) {
        this.enableAdult = z;
    }
}
