package kotlin.jvm.internal;

import java.util.Collection;
import kotlin.reflect.KCallable;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ClassBasedDeclarationContainer.kt */
/* JADX INFO: loaded from: classes.dex */
public interface ClassBasedDeclarationContainer extends KDeclarationContainer {
    @NotNull
    Class<?> getJClass();

    @NotNull
    /* synthetic */ Collection<KCallable<?>> getMembers();
}
