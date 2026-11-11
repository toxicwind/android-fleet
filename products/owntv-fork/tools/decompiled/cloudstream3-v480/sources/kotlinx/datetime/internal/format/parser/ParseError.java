package kotlinx.datetime.internal.format.parser;

import io.ktor.http.ContentType;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ParseResult.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lkotlinx/datetime/internal/format/parser/ParseError;", "", "position", "", ContentType.Message.TYPE, "Lkotlin/Function0;", "", "<init>", "(ILkotlin/jvm/functions/Function0;)V", "getPosition", "()I", "getMessage", "()Lkotlin/jvm/functions/Function0;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ParseError {
    private final Function0<String> message;
    private final int position;

    public ParseError(int i, Function0<String> message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.position = i;
        this.message = message;
    }

    public final Function0<String> getMessage() {
        return this.message;
    }

    public final int getPosition() {
        return this.position;
    }
}
