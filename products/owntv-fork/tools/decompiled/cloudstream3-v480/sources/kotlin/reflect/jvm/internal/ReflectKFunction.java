package kotlin.reflect.jvm.internal;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.KotlinGenericDeclaration;
import kotlin.reflect.KFunction;

/* JADX INFO: compiled from: ReflectKFunction.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00032\u00020\u0004J\f\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0080\u0004R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00000\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000f¨\u0006\u0012À\u0006\u0003"}, d2 = {"Lkotlin/reflect/jvm/internal/ReflectKFunction;", "Lkotlin/reflect/jvm/internal/ReflectKCallable;", "", "Lkotlin/reflect/KFunction;", "Lkotlin/jvm/internal/KotlinGenericDeclaration;", "signature", "", "getSignature", "()Ljava/lang/String;", "overridden", "", "getOverridden", "()Ljava/util/Collection;", "isPrimaryConstructor", "", "()Z", "findJavaDeclaration", "Ljava/lang/reflect/GenericDeclaration;", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface ReflectKFunction extends KotlinGenericDeclaration, KFunction<Object>, ReflectKCallable<Object> {
    Collection<ReflectKFunction> getOverridden();

    String getSignature();

    boolean isPrimaryConstructor();
}
