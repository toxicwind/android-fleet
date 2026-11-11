package io.ktor.utils.io;

import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: IODispatcher.jvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\r\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0001\u0010\u0002¨\u0006\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineDispatcher;", "ioDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class IODispatcher_jvmKt {
    public static final CoroutineDispatcher ioDispatcher() {
        return Dispatchers.getIO();
    }
}
