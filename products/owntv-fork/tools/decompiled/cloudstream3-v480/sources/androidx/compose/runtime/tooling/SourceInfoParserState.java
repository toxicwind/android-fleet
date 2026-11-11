package androidx.compose.runtime.tooling;

import androidx.media3.exoplayer.upstream.CmcdData;
import io.ktor.http.ContentType;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: SourceInformation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0003J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0003J\u000e\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0003J\u0006\u0010\u001a\u001a\u00020\u0003J\u000e\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0003J\u0010\u0010\u001c\u001a\u00020\u000f2\b\b\u0002\u0010\u001d\u001a\u00020\tJ\u0006\u0010\u001e\u001a\u00020\u0011J\u0006\u0010\u001f\u001a\u00020\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006 "}, d2 = {"Landroidx/compose/runtime/tooling/SourceInfoParserState;", "", "data", "", "<init>", "(Ljava/lang/String;)V", "getData", "()Ljava/lang/String;", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "", "getI", "()I", "setI", "(I)V", "expect", "", "char", "", "throwParseError", "", ContentType.Message.TYPE, "matches", "", "takeIntUntil", "separator", "takeUntil", "takeUntilEnd", "skipUntil", "advance", PeertubeParsingHelper.COUNT_KEY, "current", "atEnd", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class SourceInfoParserState {
    private final String data;
    private int i;

    public SourceInfoParserState(String str) {
        this.data = str;
    }

    public final String getData() {
        return this.data;
    }

    public final int getI() {
        return this.i;
    }

    public final void setI(int i) {
        this.i = i;
    }

    public final void expect(char c) throws ParseException {
        if (matches(c)) {
            return;
        }
        throwParseError("expected " + c);
        throw new KotlinNothingValueException();
    }

    public final Void throwParseError(String message) throws ParseException {
        int iMin = Math.min(this.i, this.data.length());
        StringBuilder sb = new StringBuilder("Error while parsing source information: ");
        sb.append(message);
        sb.append(" at ");
        String strSubstring = this.data.substring(0, iMin);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        sb.append(strSubstring);
        sb.append('|');
        String strSubstring2 = this.data.substring(iMin);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        sb.append(strSubstring2);
        throw new ParseException(sb.toString());
    }

    public final boolean matches(char c) {
        return this.i < this.data.length() && this.data.charAt(this.i) == c;
    }

    public final int takeIntUntil(String separator) throws ParseException {
        Integer intOrNull = StringsKt.toIntOrNull(takeUntil(separator));
        if (intOrNull != null) {
            return intOrNull.intValue();
        }
        throwParseError("expected int");
        throw new KotlinNothingValueException();
    }

    public final String takeUntil(String separator) {
        int i = this.i;
        skipUntil(separator);
        int i2 = this.i;
        if (i2 <= i) {
            return "";
        }
        String strSubstring = this.data.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public final String takeUntilEnd() {
        String str = this.data;
        String strSubstring = str.substring(this.i, str.length());
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public final void skipUntil(String separator) {
        while (this.i < this.data.length() && !StringsKt.contains$default((CharSequence) separator, this.data.charAt(this.i), false, 2, (Object) null)) {
            this.i++;
        }
    }

    public static /* synthetic */ void advance$default(SourceInfoParserState sourceInfoParserState, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 1;
        }
        sourceInfoParserState.advance(i);
    }

    public final void advance(int count) {
        this.i += count;
    }

    public final char current() {
        return this.data.charAt(this.i);
    }

    public final boolean atEnd() {
        return this.i >= this.data.length();
    }
}
