package kotlinx.serialization.json.internal;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentType;
import java.util.ArrayList;
import java.util.List;
import kotlin.IgnorableReturnValue;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.JsonConfiguration;

/* JADX INFO: compiled from: AbstractJsonLexer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u000b\b \u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0010\u001a\u00020\u0011*\u00020\u0012H\u0084\u0088\u0004J\n\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004J\n\u0010\u0015\u001a\u00020\u0011H\u0086\u0080\u0004J\u0012\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\rH¦\u0080\u0004J\n\u0010\u0018\u001a\u00020\u0011H¦\u0080\u0004J\n\u0010\u0019\u001a\u00020\u001aH¦\u0080\u0004J\n\u0010\u001b\u001a\u00020\u0011H\u0086\u0080\u0004J\u0012\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0012H\u0084\u0080\u0004J\n\u0010\u001e\u001a\u00020\u0014H\u0086\u0080\u0004J\u0012\u0010\u0019\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\u001aH\u0087\u0080\bJ\u0012\u0010\u0019\u001a\u00020\u00142\u0006\u0010(\u001a\u00020\u0012H¦\u0080\u0004J\u0012\u0010)\u001a\u00020\u00142\u0006\u0010(\u001a\u00020\u0012H\u0084\u0080\u0004J^\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u001a2\b\b\u0002\u0010-\u001a\u00020\u001128\b\u0002\u0010.\u001a2\u0012\u0013\u0012\u00110 ¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b((\u0012\u0013\u0012\u00110 ¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020 0/H\u0080\u0088\u0004ø\u0001\u0000¢\u0006\u0002\b2J\n\u00103\u001a\u00020\u001aH\u0096\u0080\u0004J\u0014\u00104\u001a\u00020\u00112\b\b\u0002\u00105\u001a\u00020\u0011H\u0086\u0080\u0004J\n\u00106\u001a\u00020\rH¦\u0080\u0004J\u001c\u00107\u001a\u0004\u0018\u00010 2\u0006\u00108\u001a\u00020 2\u0006\u00109\u001a\u00020\u0011H¦\u0080\u0004J\u0014\u0010:\u001a\u0004\u0018\u00010 2\u0006\u00109\u001a\u00020\u0011H\u0086\u0080\u0004J\n\u0010;\u001a\u00020\u0014H\u0086\u0080\u0004J\u001a\u0010<\u001a\u00020\r2\u0006\u0010=\u001a\u00020\u00122\u0006\u0010>\u001a\u00020\rH\u0096\u0080\u0004J\u001a\u0010?\u001a\u00020 2\u0006\u0010>\u001a\u00020\r2\u0006\u0010@\u001a\u00020\rH\u0096\u0080\u0004J\n\u0010A\u001a\u00020 H¦\u0080\u0004J\u001a\u0010B\u001a\u00020\u00112\u0006\u00109\u001a\u00020\u00112\u0006\u0010=\u001a\u00020\u0012H\u0082\u0080\u0004J5\u0010C\u001a\u00020\u00142\u0006\u00109\u001a\u00020\u00112!\u0010D\u001a\u001d\u0012\u0013\u0012\u00110 ¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u00140EH\u0096\u0080\u0004JE\u0010G\u001a\u00020\u00142\u0006\u0010H\u001a\u00020\r2\u0006\u0010I\u001a\u00020\r2\u0006\u0010J\u001a\u00020\u00112!\u0010D\u001a\u001d\u0012\u0013\u0012\u00110 ¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u00140EH\u0082\u0080\u0004J\n\u0010K\u001a\u00020 H\u0086\u0080\u0004J\"\u0010K\u001a\u00020 2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010L\u001a\u00020\r2\u0006\u0010M\u001a\u00020\rH\u0085\u0080\u0004J\u001a\u0010N\u001a\u00020\r2\u0006\u0010O\u001a\u00020\r2\u0006\u0010M\u001a\u00020\rH\u0082\u0080\u0004J\u001a\u0010P\u001a\u00020 2\u0006\u0010O\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0082\u0080\u0004J\n\u0010Q\u001a\u00020 H\u0082\u0080\u0004J\n\u0010R\u001a\u00020 H\u0086\u0080\u0004J\n\u0010S\u001a\u00020\u0011H\u0082\u0080\u0004J\n\u0010T\u001a\u00020 H\u0086\u0080\u0004J\u001a\u0010U\u001a\u00020\u00142\u0006\u0010H\u001a\u00020\r2\u0006\u0010I\u001a\u00020\rH\u0094\u0080\u0004J\u0012\u0010V\u001a\u00020\r2\u0006\u0010L\u001a\u00020\rH\u0082\u0080\u0004J\u001a\u0010W\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010>\u001a\u00020\rH\u0082\u0080\u0004J2\u0010X\u001a\u00020\u00142\u0006\u0010Y\u001a\u00020\u00112\b\b\u0002\u0010\u0017\u001a\u00020\r2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020 0ZH\u0080\u0088\u0004ø\u0001\u0000¢\u0006\u0002\b[J\u001a\u0010\\\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0082\u0080\u0004J\u0012\u0010]\u001a\u00020\u00142\u0006\u0010^\u001a\u00020\u0011H\u0086\u0080\u0004J\n\u0010_\u001a\u00020 H\u0096\u0080\u0004J\u0012\u0010`\u001a\u00020\u00142\u0006\u0010a\u001a\u00020 H\u0086\u0080\u0004J(\u0010*\u001a\u00020+2\u0006\u0010.\u001a\u00020 2\b\b\u0002\u0010\u0017\u001a\u00020\r2\n\b\u0002\u0010b\u001a\u0004\u0018\u00010 H\u0086\u0080\u0004J\n\u0010c\u001a\u00020dH\u0086\u0080\u0004J\n\u0010e\u001a\u00020dH\u0086\u0080\u0004J\n\u0010f\u001a\u00020\u0011H\u0086\u0080\u0004J\n\u0010g\u001a\u00020\u0011H\u0086\u0080\u0004J\u0012\u0010f\u001a\u00020\u00112\u0006\u0010h\u001a\u00020\rH\u0083\u0080\u0004J\u001a\u0010i\u001a\u00020\u00142\u0006\u0010j\u001a\u00020 2\u0006\u0010M\u001a\u00020\rH\u0082\u0080\u0004J#\u0010k\u001a\u0002Hl\"\u0004\b\u0000\u0010l2\f\u0010m\u001a\b\u0012\u0004\u0012\u0002Hl0ZH\u0082\u0088\u0004¢\u0006\u0002\u0010nR\u0015\u0010\u0002\u001a\u00020\u0003X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u00020\tX¤\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\f\u001a\u00020\r8\u0000@\u0000X\u0081\u008e\b¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u001f\u0010!\u001a\u00060\"j\u0002`#X\u0084\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006o"}, d2 = {"Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "", "configuration", "Lkotlinx/serialization/json/JsonConfiguration;", "<init>", "(Lkotlinx/serialization/json/JsonConfiguration;)V", "getConfiguration$kotlinx_serialization_json", "()Lkotlinx/serialization/json/JsonConfiguration;", "source", "", "getSource", "()Ljava/lang/CharSequence;", "currentPosition", "", "path", "Lkotlinx/serialization/json/internal/JsonPath;", "isWs", "", "", "ensureHaveChars", "", "isNotEof", "prefetchOrEof", "position", "canConsumeValue", "consumeNextToken", "", "tryConsumeComma", "isValidValueStart", "c", "expectEof", "peekedString", "", "escapedString", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getEscapedString", "()Ljava/lang/StringBuilder;", "setEscapedString", "(Ljava/lang/StringBuilder;)V", "expected", "unexpectedToken", "fail", "", "expectedToken", "wasConsumed", ContentType.Message.TYPE, "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "fail$kotlinx_serialization_json", "peekNextToken", "tryConsumeNull", "doConsume", "skipWhitespaces", "peekLeadingMatchingValue", "keyToMatch", "isLenient", "peekString", "discardPeeked", "indexOf", "char", "startPos", "substring", "endPos", "consumeKeyString", "insideString", "consumeStringChunked", "consumeChunk", "Lkotlin/Function1;", "stringChunk", "writeRange", "fromIndex", "toIndex", "currentChunkHasEscape", "consumeString", "startPosition", "current", "appendEscape", "lastPosition", "decodedString", "takePeeked", "consumeStringLenientNotNull", "wasUnquotedString", "consumeStringLenient", "appendRange", "appendEsc", "appendHex", "require", "condition", "Lkotlin/Function0;", "require$kotlinx_serialization_json", "fromHexChar", "skipElement", "allowLenientStrings", "toString", "failOnUnknownKey", "key", "hint", "consumeNumericLiteral", "", "consumeNumericLiteralFully", "consumeBoolean", "consumeBooleanLenient", "start", "consumeBooleanLiteral", "literalSuffix", "withPositionRollback", ExifInterface.GPS_DIRECTION_TRUE, "action", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class AbstractJsonLexer {
    private final JsonConfiguration configuration;
    public int currentPosition;
    private StringBuilder escapedString;
    public final JsonPath path;
    private String peekedString;

    public abstract boolean canConsumeValue();

    public abstract String consumeKeyString();

    public abstract byte consumeNextToken();

    public abstract void consumeNextToken(char expected);

    public void ensureHaveChars() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract CharSequence getSource();

    protected final boolean isValidValueStart(char c) {
        return (c == ',' || c == ':' || c == ']' || c == '}') ? false : true;
    }

    protected final boolean isWs(char c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t';
    }

    public abstract String peekLeadingMatchingValue(String keyToMatch, boolean isLenient);

    public abstract int prefetchOrEof(int position);

    public abstract int skipWhitespaces();

    public AbstractJsonLexer(JsonConfiguration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        this.configuration = configuration;
        this.path = new JsonPath(configuration);
        this.escapedString = new StringBuilder();
    }

    /* JADX INFO: renamed from: getConfiguration$kotlinx_serialization_json, reason: from getter */
    public final JsonConfiguration getConfiguration() {
        return this.configuration;
    }

    public final boolean isNotEof() {
        return peekNextToken() != 10;
    }

    public final boolean tryConsumeComma() {
        int iSkipWhitespaces = skipWhitespaces();
        CharSequence source = getSource();
        if (iSkipWhitespaces >= source.length() || iSkipWhitespaces == -1 || source.charAt(iSkipWhitespaces) != ',') {
            return false;
        }
        this.currentPosition++;
        return true;
    }

    public final void expectEof() {
        if (consumeNextToken() == 10) {
            return;
        }
        fail$default(this, "Expected EOF after parsing, but had " + getSource().charAt(this.currentPosition - 1) + " instead", 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    protected final StringBuilder getEscapedString() {
        return this.escapedString;
    }

    protected final void setEscapedString(StringBuilder sb) {
        Intrinsics.checkNotNullParameter(sb, "<set-?>");
        this.escapedString = sb;
    }

    @IgnorableReturnValue
    public final byte consumeNextToken(byte expected) {
        byte bConsumeNextToken = consumeNextToken();
        if (bConsumeNextToken == expected) {
            return bConsumeNextToken;
        }
        String str = AbstractJsonLexerKt.tokenDescription(expected);
        int i = this.currentPosition;
        int i2 = i > 0 ? i - 1 : i;
        fail$default(this, "Expected " + str + ", but had '" + ((i == getSource().length() || i2 < 0) ? "EOF" : String.valueOf(getSource().charAt(i2))) + "' instead", i2, null, 4, null);
        throw new KotlinNothingValueException();
    }

    protected final void unexpectedToken(char expected) {
        int i = this.currentPosition;
        if (i > 0 && expected == '\"') {
            try {
                this.currentPosition = i - 1;
                String strConsumeStringLenient = consumeStringLenient();
                this.currentPosition = i;
                if (Intrinsics.areEqual(strConsumeStringLenient, AbstractJsonLexerKt.NULL)) {
                    fail("Expected string literal but 'null' literal was found", this.currentPosition - 1, AbstractJsonLexerKt.coerceInputValuesHint);
                    throw new KotlinNothingValueException();
                }
            } catch (Throwable th) {
                this.currentPosition = i;
                throw th;
            }
        }
        String str = AbstractJsonLexerKt.tokenDescription(AbstractJsonLexerKt.charToTokenClass(expected));
        int i2 = this.currentPosition;
        int i3 = i2 > 0 ? i2 - 1 : i2;
        fail$default(this, "Expected " + str + ", but had '" + ((i2 == getSource().length() || i3 < 0) ? "EOF" : String.valueOf(getSource().charAt(i3))) + "' instead", i3, null, 4, null);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ Void fail$kotlinx_serialization_json$default(AbstractJsonLexer abstractJsonLexer, byte b, boolean z, Function2 message, int i, Object obj) {
        int i2;
        if (obj == null) {
            if ((i & 2) != 0) {
                z = true;
            }
            if ((i & 4) != 0) {
                message = new Function2<String, String, String>() { // from class: kotlinx.serialization.json.internal.AbstractJsonLexer.fail.1
                    @Override // kotlin.jvm.functions.Function2
                    public final String invoke(String expected, String source) {
                        Intrinsics.checkNotNullParameter(expected, "expected");
                        Intrinsics.checkNotNullParameter(source, "source");
                        return "Expected " + expected + ", but had '" + source + "' instead";
                    }
                };
            }
            Intrinsics.checkNotNullParameter(message, "message");
            String str = AbstractJsonLexerKt.tokenDescription(b);
            int i3 = (!z || (i2 = abstractJsonLexer.currentPosition) <= 0) ? abstractJsonLexer.currentPosition : i2 - 1;
            fail$default(abstractJsonLexer, (String) message.invoke(str, (abstractJsonLexer.currentPosition == abstractJsonLexer.getSource().length() || i3 < 0) ? "EOF" : String.valueOf(abstractJsonLexer.getSource().charAt(i3))), i3, null, 4, null);
            throw new KotlinNothingValueException();
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fail");
    }

    public final Void fail$kotlinx_serialization_json(byte expectedToken, boolean wasConsumed, Function2<? super String, ? super String, String> message) {
        int i;
        Intrinsics.checkNotNullParameter(message, "message");
        String str = AbstractJsonLexerKt.tokenDescription(expectedToken);
        int i2 = (!wasConsumed || (i = this.currentPosition) <= 0) ? this.currentPosition : i - 1;
        fail$default(this, message.invoke(str, (this.currentPosition == getSource().length() || i2 < 0) ? "EOF" : String.valueOf(getSource().charAt(i2))), i2, null, 4, null);
        throw new KotlinNothingValueException();
    }

    public byte peekNextToken() {
        CharSequence source = getSource();
        int i = this.currentPosition;
        while (true) {
            int iPrefetchOrEof = prefetchOrEof(i);
            if (iPrefetchOrEof != -1) {
                char cCharAt = source.charAt(iPrefetchOrEof);
                if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != ' ') {
                    this.currentPosition = iPrefetchOrEof;
                    return AbstractJsonLexerKt.charToTokenClass(cCharAt);
                }
                i = iPrefetchOrEof + 1;
            } else {
                this.currentPosition = iPrefetchOrEof;
                return (byte) 10;
            }
        }
    }

    public static /* synthetic */ boolean tryConsumeNull$default(AbstractJsonLexer abstractJsonLexer, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tryConsumeNull");
        }
        if ((i & 1) != 0) {
            z = true;
        }
        return abstractJsonLexer.tryConsumeNull(z);
    }

    public final boolean tryConsumeNull(boolean doConsume) {
        int iPrefetchOrEof = prefetchOrEof(skipWhitespaces());
        int length = getSource().length() - iPrefetchOrEof;
        if (length < 4 || iPrefetchOrEof == -1) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (AbstractJsonLexerKt.NULL.charAt(i) != getSource().charAt(iPrefetchOrEof + i)) {
                return false;
            }
        }
        if (length > 4 && AbstractJsonLexerKt.charToTokenClass(getSource().charAt(iPrefetchOrEof + 4)) == 0) {
            return false;
        }
        if (!doConsume) {
            return true;
        }
        this.currentPosition = iPrefetchOrEof + 4;
        return true;
    }

    public final String peekString(boolean isLenient) {
        String strConsumeString;
        byte bPeekNextToken = peekNextToken();
        if (isLenient) {
            if (bPeekNextToken != 1 && bPeekNextToken != 0) {
                return null;
            }
            strConsumeString = consumeStringLenient();
        } else {
            if (bPeekNextToken != 1) {
                return null;
            }
            strConsumeString = consumeString();
        }
        this.peekedString = strConsumeString;
        return strConsumeString;
    }

    public final void discardPeeked() {
        this.peekedString = null;
    }

    public int indexOf(char c, int startPos) {
        return StringsKt.indexOf$default(getSource(), c, startPos, false, 4, (Object) null);
    }

    public String substring(int startPos, int endPos) {
        return getSource().subSequence(startPos, endPos).toString();
    }

    private final boolean insideString(boolean isLenient, char c) {
        if (isLenient) {
            return AbstractJsonLexerKt.charToTokenClass(c) == 0;
        }
        return c != '\"';
    }

    public void consumeStringChunked(boolean isLenient, Function1<? super String, Unit> consumeChunk) {
        int i;
        int iPrefetchOrEof;
        Intrinsics.checkNotNullParameter(consumeChunk, "consumeChunk");
        byte bPeekNextToken = peekNextToken();
        if (!isLenient || bPeekNextToken == 0) {
            if (!isLenient) {
                consumeNextToken('\"');
            }
            int i2 = this.currentPosition;
            char cCharAt = getSource().charAt(i2);
            boolean z = false;
            int i3 = i2;
            while (this.insideString(isLenient, cCharAt)) {
                if (isLenient || cCharAt != '\\') {
                    int i4 = i3 + 1;
                    i = i2;
                    iPrefetchOrEof = i4;
                } else {
                    iPrefetchOrEof = this.prefetchOrEof(this.appendEscape(i2, i3));
                    z = true;
                    i = iPrefetchOrEof;
                }
                if (iPrefetchOrEof >= this.getSource().length()) {
                    this.writeRange(i, iPrefetchOrEof, z, consumeChunk);
                    int iPrefetchOrEof2 = this.prefetchOrEof(iPrefetchOrEof);
                    if (iPrefetchOrEof2 == -1) {
                        fail$default(this, "EOF", iPrefetchOrEof2, null, 4, null);
                        throw new KotlinNothingValueException();
                    }
                    z = false;
                    i2 = iPrefetchOrEof2;
                    i3 = i2;
                } else {
                    int i5 = i;
                    i3 = iPrefetchOrEof;
                    i2 = i5;
                }
                AbstractJsonLexer abstractJsonLexer = this;
                cCharAt = abstractJsonLexer.getSource().charAt(i3);
                this = abstractJsonLexer;
            }
            AbstractJsonLexer abstractJsonLexer2 = this;
            abstractJsonLexer2.writeRange(i2, i3, z, consumeChunk);
            abstractJsonLexer2.currentPosition = i3;
            if (isLenient) {
                return;
            }
            abstractJsonLexer2.consumeNextToken('\"');
        }
    }

    private final void writeRange(int fromIndex, int toIndex, boolean currentChunkHasEscape, Function1<? super String, Unit> consumeChunk) {
        if (currentChunkHasEscape) {
            consumeChunk.invoke(decodedString(fromIndex, toIndex));
        } else {
            consumeChunk.invoke(substring(fromIndex, toIndex));
        }
    }

    public final String consumeString() {
        if (this.peekedString != null) {
            return takePeeked();
        }
        return consumeKeyString();
    }

    protected final String consumeString(CharSequence source, int startPosition, int current) {
        String strDecodedString;
        AbstractJsonLexer abstractJsonLexer;
        Intrinsics.checkNotNullParameter(source, "source");
        char cCharAt = source.charAt(current);
        boolean z = false;
        while (cCharAt != '\"') {
            if (cCharAt == '\\') {
                int iPrefetchOrEof = this.prefetchOrEof(this.appendEscape(startPosition, current));
                if (iPrefetchOrEof == -1) {
                    fail$default(this, "Unexpected EOF", iPrefetchOrEof, null, 4, null);
                    throw new KotlinNothingValueException();
                }
                abstractJsonLexer = this;
                z = true;
                startPosition = iPrefetchOrEof;
                current = startPosition;
            } else {
                abstractJsonLexer = this;
                current++;
                if (current >= source.length()) {
                    abstractJsonLexer.appendRange(startPosition, current);
                    int iPrefetchOrEof2 = abstractJsonLexer.prefetchOrEof(current);
                    if (iPrefetchOrEof2 == -1) {
                        fail$default(abstractJsonLexer, "Unexpected EOF", iPrefetchOrEof2, null, 4, null);
                        throw new KotlinNothingValueException();
                    }
                    startPosition = iPrefetchOrEof2;
                    current = startPosition;
                    z = true;
                } else {
                    continue;
                }
            }
            AbstractJsonLexer abstractJsonLexer2 = abstractJsonLexer;
            cCharAt = source.charAt(current);
            this = abstractJsonLexer2;
        }
        AbstractJsonLexer abstractJsonLexer3 = this;
        if (!z) {
            strDecodedString = abstractJsonLexer3.substring(startPosition, current);
        } else {
            strDecodedString = abstractJsonLexer3.decodedString(startPosition, current);
        }
        abstractJsonLexer3.currentPosition = current + 1;
        return strDecodedString;
    }

    private final int appendEscape(int lastPosition, int current) {
        appendRange(lastPosition, current);
        return appendEsc(current + 1);
    }

    private final String decodedString(int lastPosition, int currentPosition) {
        appendRange(lastPosition, currentPosition);
        String string = this.escapedString.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        this.escapedString.setLength(0);
        return string;
    }

    private final String takePeeked() {
        String str = this.peekedString;
        Intrinsics.checkNotNull(str);
        this.peekedString = null;
        return str;
    }

    public final String consumeStringLenientNotNull() {
        String strConsumeStringLenient = consumeStringLenient();
        if (!Intrinsics.areEqual(strConsumeStringLenient, AbstractJsonLexerKt.NULL) || !wasUnquotedString()) {
            return strConsumeStringLenient;
        }
        fail$default(this, "Unexpected 'null' value instead of string literal", 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    private final boolean wasUnquotedString() {
        return getSource().charAt(this.currentPosition - 1) != '\"';
    }

    public final String consumeStringLenient() {
        String strDecodedString;
        if (this.peekedString != null) {
            return takePeeked();
        }
        int iSkipWhitespaces = skipWhitespaces();
        if (iSkipWhitespaces >= getSource().length() || iSkipWhitespaces == -1) {
            fail$default(this, "EOF", iSkipWhitespaces, null, 4, null);
            throw new KotlinNothingValueException();
        }
        byte bCharToTokenClass = AbstractJsonLexerKt.charToTokenClass(getSource().charAt(iSkipWhitespaces));
        if (bCharToTokenClass == 1) {
            return consumeString();
        }
        if (bCharToTokenClass != 0) {
            fail$default(this, "Expected beginning of the string, but got " + getSource().charAt(iSkipWhitespaces), 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        boolean z = false;
        while (AbstractJsonLexerKt.charToTokenClass(getSource().charAt(iSkipWhitespaces)) == 0) {
            iSkipWhitespaces++;
            if (iSkipWhitespaces >= getSource().length()) {
                appendRange(this.currentPosition, iSkipWhitespaces);
                int iPrefetchOrEof = prefetchOrEof(iSkipWhitespaces);
                if (iPrefetchOrEof == -1) {
                    this.currentPosition = iSkipWhitespaces;
                    return decodedString(0, 0);
                }
                iSkipWhitespaces = iPrefetchOrEof;
                z = true;
            }
        }
        int i = this.currentPosition;
        if (!z) {
            strDecodedString = substring(i, iSkipWhitespaces);
        } else {
            strDecodedString = decodedString(i, iSkipWhitespaces);
        }
        this.currentPosition = iSkipWhitespaces;
        return strDecodedString;
    }

    protected void appendRange(int fromIndex, int toIndex) {
        this.escapedString.append(getSource(), fromIndex, toIndex);
    }

    private final int appendEsc(int startPosition) {
        int iPrefetchOrEof = prefetchOrEof(startPosition);
        if (iPrefetchOrEof == -1) {
            fail$default(this, "Expected escape sequence to continue, got EOF", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        int i = iPrefetchOrEof + 1;
        char cCharAt = getSource().charAt(iPrefetchOrEof);
        if (cCharAt == 'u') {
            return appendHex(getSource(), i);
        }
        char cEscapeToChar = AbstractJsonLexerKt.escapeToChar(cCharAt);
        if (cEscapeToChar == 0) {
            fail$default(this, "Invalid escaped char '" + cCharAt + '\'', 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        this.escapedString.append(cEscapeToChar);
        return i;
    }

    private final int appendHex(CharSequence source, int startPos) {
        int i = startPos + 4;
        if (i >= source.length()) {
            this.currentPosition = startPos;
            ensureHaveChars();
            if (this.currentPosition + 4 >= source.length()) {
                fail$default(this, "Unexpected EOF during unicode escape", 0, null, 6, null);
                throw new KotlinNothingValueException();
            }
            return appendHex(source, this.currentPosition);
        }
        this.escapedString.append((char) ((fromHexChar(source, startPos) << 12) + (fromHexChar(source, startPos + 1) << 8) + (fromHexChar(source, startPos + 2) << 4) + fromHexChar(source, startPos + 3)));
        return i;
    }

    public static /* synthetic */ void require$kotlinx_serialization_json$default(AbstractJsonLexer abstractJsonLexer, boolean z, int i, Function0 message, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: require");
        }
        if ((i2 & 2) != 0) {
            i = abstractJsonLexer.currentPosition;
        }
        int i3 = i;
        Intrinsics.checkNotNullParameter(message, "message");
        if (z) {
            return;
        }
        fail$default(abstractJsonLexer, (String) message.invoke(), i3, null, 4, null);
        throw new KotlinNothingValueException();
    }

    public final void require$kotlinx_serialization_json(boolean condition, int position, Function0<String> message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (condition) {
            return;
        }
        fail$default(this, message.invoke(), position, null, 4, null);
        throw new KotlinNothingValueException();
    }

    private final int fromHexChar(CharSequence source, int currentPosition) {
        char cCharAt = source.charAt(currentPosition);
        if ('0' <= cCharAt && cCharAt < ':') {
            return cCharAt - '0';
        }
        if ('a' <= cCharAt && cCharAt < 'g') {
            return cCharAt - 'W';
        }
        if ('A' <= cCharAt && cCharAt < 'G') {
            return cCharAt - '7';
        }
        fail$default(this, "Invalid toHexChar char '" + cCharAt + "' in unicode escape", 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    public final void skipElement(boolean allowLenientStrings) {
        AbstractJsonLexer abstractJsonLexer;
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList();
        byte bPeekNextToken = peekNextToken();
        if (bPeekNextToken != 8 && bPeekNextToken != 6) {
            consumeStringLenient();
            return;
        }
        while (true) {
            byte bPeekNextToken2 = this.peekNextToken();
            if (bPeekNextToken2 != 1) {
                if (bPeekNextToken2 == 8 || bPeekNextToken2 == 6) {
                    ArrayList arrayList3 = arrayList2;
                    abstractJsonLexer = this;
                    arrayList = arrayList3;
                    arrayList.add(Byte.valueOf(bPeekNextToken2));
                } else if (bPeekNextToken2 != 9) {
                    AbstractJsonLexer abstractJsonLexer2 = this;
                    if (bPeekNextToken2 != 7) {
                        arrayList = arrayList2;
                        abstractJsonLexer = abstractJsonLexer2;
                        if (bPeekNextToken2 == 10) {
                            fail$default(abstractJsonLexer, "Unexpected end of input due to malformed JSON during ignoring unknown keys", 0, null, 6, null);
                            throw new KotlinNothingValueException();
                        }
                    } else {
                        if (((Number) CollectionsKt.last((List) arrayList2)).byteValue() != 6) {
                            fail$default(abstractJsonLexer2, "found } instead of ]", 0, null, 6, null);
                            throw new KotlinNothingValueException();
                        }
                        CollectionsKt.removeLast(arrayList2);
                        arrayList = arrayList2;
                        abstractJsonLexer = abstractJsonLexer2;
                    }
                } else {
                    if (((Number) CollectionsKt.last((List) arrayList2)).byteValue() != 8) {
                        fail$default(this, "found ] instead of }", 0, null, 6, null);
                        throw new KotlinNothingValueException();
                    }
                    CollectionsKt.removeLast(arrayList2);
                    ArrayList arrayList4 = arrayList2;
                    abstractJsonLexer = this;
                    arrayList = arrayList4;
                }
                abstractJsonLexer.consumeNextToken();
                if (arrayList.size() == 0) {
                    return;
                }
                AbstractJsonLexer abstractJsonLexer3 = abstractJsonLexer;
                arrayList2 = arrayList;
                this = abstractJsonLexer3;
            } else if (allowLenientStrings) {
                this.consumeStringLenient();
            } else {
                this.consumeKeyString();
            }
        }
    }

    public String toString() {
        return "JsonReader(source='" + ((Object) getSource()) + "', currentPosition=" + this.currentPosition + ')';
    }

    public final void failOnUnknownKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        fail("Encountered an unknown key '" + key + '\'', StringsKt.lastIndexOf$default((CharSequence) substring(0, this.currentPosition), key, 0, false, 6, (Object) null), AbstractJsonLexerKt.ignoreUnknownKeysHint);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ Void fail$default(AbstractJsonLexer abstractJsonLexer, String str, int i, String str2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fail");
        }
        if ((i2 & 2) != 0) {
            i = abstractJsonLexer.currentPosition;
        }
        if ((i2 & 4) != 0) {
            str2 = null;
        }
        return abstractJsonLexer.fail(str, i, str2);
    }

    public final Void fail(String message, int position, String hint) {
        Intrinsics.checkNotNullParameter(message, "message");
        throw JsonExceptionsKt.decodingExceptionOf(this, message, position, this.path.getPath(), hint, getSource());
    }

    public final long consumeNumericLiteral() {
        boolean z;
        boolean z2;
        int iPrefetchOrEof = prefetchOrEof(skipWhitespaces());
        if (iPrefetchOrEof >= getSource().length() || iPrefetchOrEof == -1) {
            fail$default(this, "EOF", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        if (getSource().charAt(iPrefetchOrEof) == '\"') {
            iPrefetchOrEof++;
            if (iPrefetchOrEof == getSource().length()) {
                fail$default(this, "EOF", 0, null, 6, null);
                throw new KotlinNothingValueException();
            }
            z = true;
        } else {
            z = false;
        }
        int i = iPrefetchOrEof;
        boolean z3 = false;
        boolean z4 = false;
        long j = 0;
        long j2 = 0;
        loop0: while (true) {
            z2 = false;
            while (true) {
                if (i == getSource().length()) {
                    break loop0;
                }
                char cCharAt = getSource().charAt(i);
                if ((cCharAt == 'e' || cCharAt == 'E') && !z3) {
                    if (i == iPrefetchOrEof) {
                        fail$default(this, "Unexpected symbol '" + cCharAt + "' in numeric literal", i, null, 4, null);
                        throw new KotlinNothingValueException();
                    }
                    i++;
                    z3 = true;
                } else if (cCharAt != '-' || !z3) {
                    if (cCharAt != '+' || !z3) {
                        if (cCharAt != '-') {
                            if (AbstractJsonLexerKt.charToTokenClass(cCharAt) != 0) {
                                break loop0;
                            }
                            int i2 = i + 1;
                            int i3 = cCharAt - '0';
                            if (i3 < 0 || i3 >= 10) {
                                fail$default(this, "Unexpected symbol '" + cCharAt + "' in numeric literal", i, null, 4, null);
                                throw new KotlinNothingValueException();
                            }
                            if (z3) {
                                j2 = (j2 * 10) + ((long) i3);
                            } else {
                                j = (j * 10) - ((long) i3);
                                if (j > 0) {
                                    fail$default(this, "Numeric value overflow", 0, null, 6, null);
                                    throw new KotlinNothingValueException();
                                }
                            }
                            i = i2;
                        } else {
                            if (i != iPrefetchOrEof) {
                                fail$default(this, "Unexpected symbol '-' in numeric literal", i, null, 4, null);
                                throw new KotlinNothingValueException();
                            }
                            i++;
                            z4 = true;
                        }
                    } else {
                        if (i == iPrefetchOrEof) {
                            fail$default(this, "Unexpected symbol '+' in numeric literal", i, null, 4, null);
                            throw new KotlinNothingValueException();
                        }
                        i++;
                    }
                }
                z2 = true;
            }
            if (i == iPrefetchOrEof) {
                fail$default(this, "Unexpected symbol '-' in numeric literal", i, null, 4, null);
                throw new KotlinNothingValueException();
            }
            i++;
        }
        boolean z5 = i != iPrefetchOrEof;
        if (iPrefetchOrEof == i || (z4 && iPrefetchOrEof == i - 1)) {
            fail$default(this, "Expected numeric literal", i, null, 4, null);
            throw new KotlinNothingValueException();
        }
        if (z) {
            if (!z5) {
                fail$default(this, "EOF", 0, null, 6, null);
                throw new KotlinNothingValueException();
            }
            if (getSource().charAt(i) != '\"') {
                fail$default(this, "Expected closing quotation mark", i, null, 4, null);
                throw new KotlinNothingValueException();
            }
            i++;
        }
        this.currentPosition = i;
        if (z3) {
            double dConsumeNumericLiteral$calculateExponent = j * consumeNumericLiteral$calculateExponent(j2, z2);
            if (dConsumeNumericLiteral$calculateExponent > 9.223372036854776E18d || dConsumeNumericLiteral$calculateExponent < -9.223372036854776E18d) {
                fail$default(this, "Numeric value overflow", 0, null, 6, null);
                throw new KotlinNothingValueException();
            }
            if (Math.floor(dConsumeNumericLiteral$calculateExponent) != dConsumeNumericLiteral$calculateExponent) {
                fail$default(this, "Can't convert " + dConsumeNumericLiteral$calculateExponent + " to Long", 0, null, 6, null);
                throw new KotlinNothingValueException();
            }
            j = (long) dConsumeNumericLiteral$calculateExponent;
        }
        if (z4) {
            return j;
        }
        if (j != Long.MIN_VALUE) {
            return -j;
        }
        fail$default(this, "Numeric value overflow", 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    private static final double consumeNumericLiteral$calculateExponent(long j, boolean z) {
        if (!z) {
            return Math.pow(10.0d, -j);
        }
        if (!z) {
            throw new NoWhenBranchMatchedException();
        }
        return Math.pow(10.0d, j);
    }

    public final long consumeNumericLiteralFully() {
        long jConsumeNumericLiteral = consumeNumericLiteral();
        if (consumeNextToken() == 10) {
            return jConsumeNumericLiteral;
        }
        AbstractJsonLexerKt.tokenDescription((byte) 10);
        int i = this.currentPosition;
        int i2 = i > 0 ? i - 1 : i;
        fail$default(this, "Expected input to contain a single valid number, but got '" + ((i == getSource().length() || i2 < 0) ? "EOF" : String.valueOf(getSource().charAt(i2))) + "' after it", i2, null, 4, null);
        throw new KotlinNothingValueException();
    }

    public final boolean consumeBoolean() {
        return consumeBoolean(skipWhitespaces());
    }

    public final boolean consumeBooleanLenient() {
        boolean z;
        int iSkipWhitespaces = skipWhitespaces();
        if (iSkipWhitespaces == getSource().length()) {
            fail$default(this, "EOF", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        if (getSource().charAt(iSkipWhitespaces) == '\"') {
            iSkipWhitespaces++;
            z = true;
        } else {
            z = false;
        }
        boolean zConsumeBoolean = consumeBoolean(iSkipWhitespaces);
        if (!z) {
            return zConsumeBoolean;
        }
        if (this.currentPosition == getSource().length()) {
            fail$default(this, "EOF", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        if (getSource().charAt(this.currentPosition) != '\"') {
            fail$default(this, "Expected closing quotation mark", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        this.currentPosition++;
        return zConsumeBoolean;
    }

    private final boolean consumeBoolean(int start) {
        int iPrefetchOrEof = prefetchOrEof(start);
        if (iPrefetchOrEof >= getSource().length() || iPrefetchOrEof == -1) {
            fail$default(this, "EOF", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        int i = iPrefetchOrEof + 1;
        int iCharAt = getSource().charAt(iPrefetchOrEof) | ' ';
        if (iCharAt == 102) {
            consumeBooleanLiteral("alse", i);
            return false;
        }
        if (iCharAt == 116) {
            consumeBooleanLiteral("rue", i);
            return true;
        }
        fail$default(this, "Expected valid boolean literal prefix, but had '" + consumeStringLenient() + '\'', 0, null, 6, null);
        throw new KotlinNothingValueException();
    }

    private final void consumeBooleanLiteral(String literalSuffix, int current) {
        if (getSource().length() - current < literalSuffix.length()) {
            fail$default(this, "Unexpected end of boolean literal", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        int length = literalSuffix.length();
        for (int i = 0; i < length; i++) {
            if (literalSuffix.charAt(i) != (getSource().charAt(current + i) | ' ')) {
                fail$default(this, "Expected valid boolean literal prefix, but had '" + consumeStringLenient() + '\'', 0, null, 6, null);
                throw new KotlinNothingValueException();
            }
        }
        this.currentPosition = current + literalSuffix.length();
    }

    private final <T> T withPositionRollback(Function0<? extends T> action) {
        int i = this.currentPosition;
        try {
            return action.invoke();
        } finally {
            this.currentPosition = i;
        }
    }
}
