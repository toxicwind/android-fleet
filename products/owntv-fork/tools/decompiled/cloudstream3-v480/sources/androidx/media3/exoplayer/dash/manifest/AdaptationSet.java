package androidx.media3.exoplayer.dash.manifest;

import j$.util.DesugarCollections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class AdaptationSet {
    public static final long ID_UNSET = -1;
    public final List<Descriptor> accessibilityDescriptors;
    public final List<Descriptor> essentialProperties;
    public final long id;
    public final List<Representation> representations;
    public final List<Descriptor> supplementalProperties;
    public final int type;

    public AdaptationSet(long j, int i, List<Representation> list, List<Descriptor> list2, List<Descriptor> list3, List<Descriptor> list4) {
        this.id = j;
        this.type = i;
        this.representations = DesugarCollections.unmodifiableList(list);
        this.accessibilityDescriptors = DesugarCollections.unmodifiableList(list2);
        this.essentialProperties = DesugarCollections.unmodifiableList(list3);
        this.supplementalProperties = DesugarCollections.unmodifiableList(list4);
    }
}
