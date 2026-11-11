package kotlinx.serialization.json.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.tvprovider.media.tv.TvContractCompat;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonDecodingException;
import kotlinx.serialization.json.JsonEncodingException;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: JsonExceptions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0004\n\u0002\b\u0007\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0080\u0080\u0004\u001a?\u0010\u0000\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0080\u0088\u0004ø\u0001\u0000\u001a8\u0010\u0000\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0007\u001a\u00020\tH\u0080\u0080\u0004\u001a\u001e\u0010\r\u001a\u0004\u0018\u00010\u0003*\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u0082\u0088\u0004\u001a\u001c\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u0080\u0080\u0004\u001a8\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0082\u0080\u0004\u001a\u0018\u0010\u0012\u001a\u00020\u0013*\u00020\n2\b\b\u0002\u0010\u0014\u001a\u00020\u0003H\u0080\u0080\u0004\u001a\u0012\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0080\u0080\u0004\u001a\u0016\u0010\u0019\u001a\u00020\u0013*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001bH\u0080\u0080\u0004\u001a\u001e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0003H\u0080\u0080\u0004\u001a/\u0010\u001f\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0080\u0088\u0004ø\u0001\u0000\u001a\u001c\u0010 \u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0003H\u0082\u0080\u0004\u001a\u0018\u0010!\u001a\u00020\t*\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0080\u0080\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\""}, d2 = {"decodingExceptionOf", "Lkotlinx/serialization/json/JsonDecodingException;", "shortMessage", "", "Lkotlinx/serialization/json/JsonDecoder;", "path", "hint", TvContractCompat.PARAM_INPUT, "Lkotlin/Function0;", "", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", TypedValues.CycleType.S_WAVE_OFFSET, "", "ifDebugInput", "Lkotlinx/serialization/json/JsonConfiguration;", "block", "formatEncodingException", "formatDecodingException", "invalidTrailingComma", "", "entity", "InvalidKeyKindException", "Lkotlinx/serialization/json/JsonEncodingException;", "keyDescriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "throwInvalidFloatingPointDecoded", "result", "", "InvalidFloatingPointEncoded", ES6Iterator.VALUE_PROPERTY, "key", "InvalidFloatingPointDecoded", "nonFiniteFpMessage", "minify", "kotlinx-serialization-json"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class JsonExceptionsKt {
    public static final JsonDecodingException decodingExceptionOf(String shortMessage) {
        Intrinsics.checkNotNullParameter(shortMessage, "shortMessage");
        return new JsonDecodingException(formatDecodingException(-1, shortMessage, null, null, null), shortMessage, -1, null, null, null);
    }

    public static /* synthetic */ JsonDecodingException decodingExceptionOf$default(JsonDecoder jsonDecoder, String shortMessage, String str, String str2, Function0 input, int i, Object obj) {
        String str3 = (i & 2) != 0 ? null : str;
        String str4 = (i & 4) != 0 ? null : str2;
        Intrinsics.checkNotNullParameter(jsonDecoder, "<this>");
        Intrinsics.checkNotNullParameter(shortMessage, "shortMessage");
        Intrinsics.checkNotNullParameter(input, "input");
        String string = jsonDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? minify$default((CharSequence) input.invoke(), 0, 1, null).toString() : null;
        return new JsonDecodingException(formatDecodingException(-1, shortMessage, str3, str4, string), shortMessage, -1, str3, string, str4);
    }

    public static final JsonDecodingException decodingExceptionOf(JsonDecoder jsonDecoder, String shortMessage, String str, String str2, Function0<? extends CharSequence> input) {
        Intrinsics.checkNotNullParameter(jsonDecoder, "<this>");
        Intrinsics.checkNotNullParameter(shortMessage, "shortMessage");
        Intrinsics.checkNotNullParameter(input, "input");
        String string = jsonDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? minify$default(input.invoke(), 0, 1, null).toString() : null;
        return new JsonDecodingException(formatDecodingException(-1, shortMessage, str, str2, string), shortMessage, -1, str, string, str2);
    }

    public static final JsonDecodingException decodingExceptionOf(AbstractJsonLexer abstractJsonLexer, String shortMessage, int i, String path, String str, CharSequence input) {
        Intrinsics.checkNotNullParameter(abstractJsonLexer, "<this>");
        Intrinsics.checkNotNullParameter(shortMessage, "shortMessage");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(input, "input");
        String string = abstractJsonLexer.getConfiguration().getExceptionsWithDebugInfo() ? minify(input, i).toString() : null;
        return new JsonDecodingException(formatDecodingException(i, shortMessage, path, str, string), shortMessage, i, path, string, str);
    }

    private static final String ifDebugInput(JsonConfiguration jsonConfiguration, Function0<String> function0) {
        if (jsonConfiguration.getExceptionsWithDebugInfo()) {
            return function0.invoke();
        }
        return null;
    }

    public static final String formatEncodingException(String shortMessage, String str) {
        String str2;
        Intrinsics.checkNotNullParameter(shortMessage, "shortMessage");
        StringBuilder sb = new StringBuilder();
        sb.append(shortMessage);
        String str3 = str;
        if (str3 == null || StringsKt.isBlank(str3)) {
            str2 = "";
        } else {
            str2 = "\n" + str;
        }
        sb.append(str2);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String formatDecodingException(int i, String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder();
        if (i >= 0) {
            sb.append("Unexpected JSON token at offset " + i + ": ");
        }
        sb.append(str);
        String str5 = str2;
        if (str5 != null && !StringsKt.isBlank(str5)) {
            sb.append(" at path: ");
            sb.append(str2);
        }
        String str6 = str3;
        if (str6 != null && !StringsKt.isBlank(str6)) {
            sb.append("\n" + str3);
        }
        if (str4 != null) {
            sb.append("\nJSON input: ");
            sb.append(str4);
        }
        return sb.toString();
    }

    public static /* synthetic */ Void invalidTrailingComma$default(AbstractJsonLexer abstractJsonLexer, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "object";
        }
        return invalidTrailingComma(abstractJsonLexer, str);
    }

    public static final Void invalidTrailingComma(AbstractJsonLexer abstractJsonLexer, String entity) {
        Intrinsics.checkNotNullParameter(abstractJsonLexer, "<this>");
        Intrinsics.checkNotNullParameter(entity, "entity");
        abstractJsonLexer.fail("Trailing comma before the end of JSON " + entity, abstractJsonLexer.currentPosition - 1, "Trailing commas are non-complaint JSON and not allowed by default. Use 'allowTrailingComma = true' in 'Json {}' builder to support them.");
        throw new KotlinNothingValueException();
    }

    public static final JsonEncodingException InvalidKeyKindException(SerialDescriptor keyDescriptor) {
        Intrinsics.checkNotNullParameter(keyDescriptor, "keyDescriptor");
        return new JsonEncodingException("Value of type '" + keyDescriptor.getSerialName() + "' can't be used in JSON as a key in the map. It should have either primitive or enum kind, but its kind is '" + keyDescriptor.getKind() + '\'', keyDescriptor.getSerialName(), AbstractJsonLexerKt.allowStructuredMapKeysHint);
    }

    public static final Void throwInvalidFloatingPointDecoded(AbstractJsonLexer abstractJsonLexer, Number result) {
        Intrinsics.checkNotNullParameter(abstractJsonLexer, "<this>");
        Intrinsics.checkNotNullParameter(result, "result");
        AbstractJsonLexer.fail$default(abstractJsonLexer, nonFiniteFpMessage(result, null), 0, AbstractJsonLexerKt.specialFlowingValuesHint, 2, null);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ JsonEncodingException InvalidFloatingPointEncoded$default(Number number, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return InvalidFloatingPointEncoded(number, str);
    }

    public static final JsonEncodingException InvalidFloatingPointEncoded(Number value, String str) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new JsonEncodingException(nonFiniteFpMessage(value, str), null, AbstractJsonLexerKt.specialFlowingValuesHint, 2, null);
    }

    public static final JsonDecodingException InvalidFloatingPointDecoded(JsonDecoder jsonDecoder, Number value, String key, Function0<? extends CharSequence> input) {
        Intrinsics.checkNotNullParameter(jsonDecoder, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(input, "input");
        String strNonFiniteFpMessage = nonFiniteFpMessage(value, key);
        String string = jsonDecoder.getJson().getConfiguration().getExceptionsWithDebugInfo() ? minify$default(input.invoke(), 0, 1, null).toString() : null;
        return new JsonDecodingException(formatDecodingException(-1, strNonFiniteFpMessage, null, AbstractJsonLexerKt.specialFlowingValuesHint, string), strNonFiniteFpMessage, -1, null, string, AbstractJsonLexerKt.specialFlowingValuesHint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String nonFiniteFpMessage(Number number, String str) {
        StringBuilder sb = new StringBuilder("Unexpected special floating-point value ");
        sb.append(number);
        String str2 = ". ";
        if (str != null) {
            str2 = " with key " + str + ". ";
        }
        sb.append(str2);
        sb.append("By default, non-finite floating point values are prohibited because they do not conform JSON specification.");
        return sb.toString();
    }

    public static /* synthetic */ CharSequence minify$default(CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = -1;
        }
        return minify(charSequence, i);
    }

    public static final CharSequence minify(CharSequence charSequence, int i) {
        String str;
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (charSequence.length() >= 200) {
            String str2 = ".....";
            if (i == -1) {
                int length = charSequence.length() - 60;
                if (length > 0) {
                    return "....." + charSequence.subSequence(length, charSequence.length()).toString();
                }
            } else {
                int i2 = i - 30;
                int i3 = i + 30;
                if (i2 > 0) {
                    str = ".....";
                } else {
                    str = "";
                }
                if (i3 >= charSequence.length()) {
                    str2 = "";
                }
                return str + charSequence.subSequence(RangesKt.coerceAtLeast(i2, 0), RangesKt.coerceAtMost(i3, charSequence.length())).toString() + str2;
            }
        }
        return charSequence;
    }
}
