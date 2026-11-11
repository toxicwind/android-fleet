package io.ktor.http.content;

import io.ktor.util.ContentEncoder;
import io.ktor.utils.io.ByteChannelCtorKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CompressedContent.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a%\u0010\u0005\u001a\u0004\u0018\u00010\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/http/content/OutgoingContent;", "Lio/ktor/util/ContentEncoder;", "contentEncoder", "Lkotlin/coroutines/CoroutineContext;", "coroutineContext", "compressed", "(Lio/ktor/http/content/OutgoingContent;Lio/ktor/util/ContentEncoder;Lkotlin/coroutines/CoroutineContext;)Lio/ktor/http/content/OutgoingContent;", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CompressedContentKt {
    public static /* synthetic */ OutgoingContent compressed$default(OutgoingContent outgoingContent, ContentEncoder contentEncoder, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 2) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return compressed(outgoingContent, contentEncoder, coroutineContext);
    }

    public static final OutgoingContent compressed(final OutgoingContent outgoingContent, ContentEncoder contentEncoder, CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(outgoingContent, "<this>");
        Intrinsics.checkNotNullParameter(contentEncoder, "contentEncoder");
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        if (outgoingContent instanceof OutgoingContent.ReadChannelContent) {
            return new CompressedReadChannelResponse(outgoingContent, new Function0() { // from class: io.ktor.http.content.CompressedContentKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ((OutgoingContent.ReadChannelContent) outgoingContent).readFrom();
                }
            }, contentEncoder, coroutineContext);
        }
        if (outgoingContent instanceof OutgoingContent.WriteChannelContent) {
            return new CompressedWriteChannelResponse((OutgoingContent.WriteChannelContent) outgoingContent, contentEncoder, coroutineContext);
        }
        if (outgoingContent instanceof OutgoingContent.ByteArrayContent) {
            return new CompressedReadChannelResponse(outgoingContent, new Function0() { // from class: io.ktor.http.content.CompressedContentKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ByteChannelCtorKt.ByteReadChannel$default(((OutgoingContent.ByteArrayContent) outgoingContent).getBytes(), 0, 0, 6, null);
                }
            }, contentEncoder, coroutineContext);
        }
        if ((outgoingContent instanceof OutgoingContent.NoContent) || (outgoingContent instanceof OutgoingContent.ProtocolUpgrade)) {
            return null;
        }
        if (outgoingContent instanceof OutgoingContent.ContentWrapper) {
            return compressed(((OutgoingContent.ContentWrapper) outgoingContent).getDelegate(), contentEncoder, coroutineContext);
        }
        throw new NoWhenBranchMatchedException();
    }
}
