package com.fleeksoft.ksoup.ported;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Consumer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bæ\u0080\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006J \u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00002\u000e\u0010\b\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u0000H\u0016¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/fleeksoft/ksoup/ported/Consumer;", ExifInterface.GPS_DIRECTION_TRUE, "", "accept", "", "t", "(Ljava/lang/Object;)V", "andThen", TtmlNode.ANNOTATION_POSITION_AFTER, "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface Consumer<T> {
    void accept(T t);

    Consumer<T> andThen(Consumer<? super T> after);

    /* JADX INFO: renamed from: com.fleeksoft.ksoup.ported.Consumer$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Consumer.kt */
    public final /* synthetic */ class CC {
        public static Consumer $default$andThen(final Consumer _this, final Consumer after) {
            Intrinsics.checkNotNullParameter(after, "after");
            return new Consumer() { // from class: com.fleeksoft.ksoup.ported.Consumer$$ExternalSyntheticLambda0
                @Override // com.fleeksoft.ksoup.ported.Consumer
                public final void accept(Object obj) {
                    Consumer.CC.andThen$lambda$0(_this, after, obj);
                }

                @Override // com.fleeksoft.ksoup.ported.Consumer
                public /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer.CC.$default$andThen(this, consumer);
                }
            };
        }

        public static void andThen$lambda$0(Consumer consumer, Consumer consumer2, Object obj) {
            consumer.accept(obj);
            consumer2.accept(obj);
        }
    }

    /* JADX INFO: compiled from: Consumer.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static <T> Consumer<T> andThen(Consumer<T> consumer, Consumer<? super T> after) {
            Intrinsics.checkNotNullParameter(after, "after");
            return CC.$default$andThen(consumer, after);
        }
    }
}
