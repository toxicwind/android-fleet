package com.lagradost.cloudstream3.ui.player;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: IGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016¢\u0006\u0002\u0010\u0011R\u0015\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/NoVideoGenerator;", "Lcom/lagradost/cloudstream3/ui/player/VideoGenerator;", "", "id", "", "<init>", "(Ljava/lang/Integer;)V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "hasCache", "", "getHasCache", "()Z", "canSkipLoading", "getCanSkipLoading", "index", "(I)Ljava/lang/Integer;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class NoVideoGenerator extends VideoGenerator {
    private final boolean canSkipLoading;
    private final boolean hasCache;
    private final Integer id;

    public NoVideoGenerator(Integer num) {
        super(CollectionsKt.emptyList());
        this.id = num;
    }

    public final Integer getId() {
        return this.id;
    }

    @Override // com.lagradost.cloudstream3.ui.player.VideoGenerator
    public boolean getHasCache() {
        return this.hasCache;
    }

    @Override // com.lagradost.cloudstream3.ui.player.VideoGenerator
    public boolean getCanSkipLoading() {
        return this.canSkipLoading;
    }

    @Override // com.lagradost.cloudstream3.ui.player.VideoGenerator
    public Integer getId(int index) {
        return this.id;
    }
}
