package io.ktor.util;

import kotlin.Metadata;

/* JADX INFO: compiled from: ContentEncoder.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\t\u001a\u00020\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lio/ktor/util/ContentEncoder;", "Lio/ktor/util/Encoder;", "", "contentLength", "predictCompressedLength", "(J)Ljava/lang/Long;", "", "getName", "()Ljava/lang/String;", "name", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface ContentEncoder extends Encoder {
    String getName();

    Long predictCompressedLength(long contentLength);

    /* JADX INFO: renamed from: io.ktor.util.ContentEncoder$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ContentEncoder.kt */
    public final /* synthetic */ class CC {
        public static Long $default$predictCompressedLength(ContentEncoder _this, long j) {
            return null;
        }
    }

    /* JADX INFO: compiled from: ContentEncoder.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static Long predictCompressedLength(ContentEncoder contentEncoder, long j) {
            return CC.$default$predictCompressedLength(contentEncoder, j);
        }
    }
}
