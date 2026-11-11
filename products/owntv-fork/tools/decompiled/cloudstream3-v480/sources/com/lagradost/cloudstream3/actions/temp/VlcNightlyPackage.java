package com.lagradost.cloudstream3.actions.temp;

import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UiText;
import kotlin.Metadata;

/* JADX INFO: compiled from: VlcPackage.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/VlcNightlyPackage;", "Lcom/lagradost/cloudstream3/actions/temp/VlcPackage;", "<init>", "()V", "packageName", "", "getPackageName", "()Ljava/lang/String;", "appName", "Lcom/lagradost/cloudstream3/utils/UiText;", "getAppName", "()Lcom/lagradost/cloudstream3/utils/UiText;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class VlcNightlyPackage extends VlcPackage {
    private final String packageName = "org.videolan.vlc.debug";
    private final UiText appName = TextUtilKt.txt("VLC Nightly");

    @Override // com.lagradost.cloudstream3.actions.OpenInAppAction
    public String getPackageName() {
        return this.packageName;
    }

    @Override // com.lagradost.cloudstream3.actions.OpenInAppAction
    public UiText getAppName() {
        return this.appName;
    }
}
