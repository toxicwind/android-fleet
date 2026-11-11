package okhttp3.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.sequences.SequencesKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Tags.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00028\u0000\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ/\u0010\u000b\u001a\u00020\u0003\"\b\b\u0001\u0010\f*\u00020\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\f0\u00052\b\u0010\u0006\u001a\u0004\u0018\u0001H\fH\u0016¢\u0006\u0002\u0010\rJ(\u0010\u000e\u001a\u0004\u0018\u0001H\f\"\b\b\u0001\u0010\f*\u00020\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\f0\u0005H\u0096\u0002¢\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lokhttp3/internal/LinkedTags;", "K", "", "Lokhttp3/internal/Tags;", "key", "Lkotlin/reflect/KClass;", ES6Iterator.VALUE_PROPERTY, "next", "<init>", "(Lkotlin/reflect/KClass;Ljava/lang/Object;Lokhttp3/internal/Tags;)V", "Ljava/lang/Object;", "plus", ExifInterface.GPS_DIRECTION_TRUE, "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Lokhttp3/internal/Tags;", "get", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "toString", "", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
final class LinkedTags<K> extends Tags {
    private final KClass<K> key;
    private final Tags next;
    private final K value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LinkedTags(KClass<K> key, K value, Tags next) {
        super(null);
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(next, "next");
        this.key = key;
        this.value = value;
        this.next = next;
    }

    @Override // okhttp3.internal.Tags
    public <T> Tags plus(KClass<T> key, T value) {
        Intrinsics.checkNotNullParameter(key, "key");
        boolean zAreEqual = Intrinsics.areEqual(key, this.key);
        LinkedTags<K> linkedTags = this.next;
        if (!zAreEqual) {
            Tags tagsPlus = linkedTags.plus(key, null);
            if (tagsPlus != this.next) {
                this = new LinkedTags<>(this.key, this.value, tagsPlus);
            }
            linkedTags = this;
        }
        return value != null ? new LinkedTags(key, value, linkedTags) : linkedTags;
    }

    @Override // okhttp3.internal.Tags
    public <T> T get(KClass<T> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return Intrinsics.areEqual(key, this.key) ? (T) JvmClassMappingKt.getJavaClass((KClass) key).cast(this.value) : (T) this.next.get(key);
    }

    static final LinkedTags toString$lambda$0(LinkedTags it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Tags tags = it.next;
        if (tags instanceof LinkedTags) {
            return (LinkedTags) tags;
        }
        return null;
    }

    public String toString() {
        return CollectionsKt.joinToString$default(CollectionsKt.reversed(SequencesKt.toList(SequencesKt.generateSequence(this, (Function1<? super LinkedTags<K>, ? extends LinkedTags<K>>) new Function1() { // from class: okhttp3.internal.LinkedTags$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LinkedTags.toString$lambda$0((LinkedTags) obj);
            }
        }))), null, "{", "}", 0, null, new Function1() { // from class: okhttp3.internal.LinkedTags$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LinkedTags.toString$lambda$1((LinkedTags) obj);
            }
        }, 25, null);
    }

    static final CharSequence toString$lambda$1(LinkedTags it) {
        Intrinsics.checkNotNullParameter(it, "it");
        StringBuilder sb = new StringBuilder();
        sb.append(it.key);
        sb.append('=');
        sb.append(it.value);
        return sb.toString();
    }
}
