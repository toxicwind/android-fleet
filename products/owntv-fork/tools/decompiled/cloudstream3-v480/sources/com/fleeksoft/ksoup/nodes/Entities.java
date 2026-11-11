package com.fleeksoft.ksoup.nodes;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
import coil3.util.UtilsKt;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fleeksoft.charset.Charsets;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.internal.QuietAppendable;
import com.fleeksoft.ksoup.internal.StringUtil;
import com.fleeksoft.ksoup.parser.CharacterReader;
import com.fleeksoft.ksoup.parser.Parser;
import com.fleeksoft.ksoup.ported.Character;
import com.fleeksoft.ksoup.ported.CharacterKt;
import com.fleeksoft.ksoup.ported.CodePoint;
import com.fleeksoft.ksoup.ported.ThreadLocal;
import io.ktor.http.ContentDisposition;
import io.ktor.http.auth.HttpAuthHeader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.HexExtensionsKt;
import kotlin.text.HexFormat;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: Entities.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\f\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002KLB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u001b\u001a\u00060\u0019j\u0002`\u001a2\n\u0010\u001c\u001a\u00060\u001dj\u0002`\u001eH\u0002J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\fJ\u000e\u0010\"\u001a\u00020 2\u0006\u0010!\u001a\u00020\fJ\u000e\u0010#\u001a\u00020\f2\u0006\u0010!\u001a\u00020\fJ\u0016\u0010$\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\f2\u0006\u0010%\u001a\u00020&J\u000e\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\fJ\u0018\u0010)\u001a\u00020\f2\b\u0010*\u001a\u0004\u0018\u00010\f2\u0006\u0010+\u001a\u00020,J\u0010\u0010)\u001a\u00020\f2\b\u0010*\u001a\u0004\u0018\u00010\fJ$\u0010-\u001a\u00020\f2\b\u0010*\u001a\u0004\u0018\u00010\f2\u0006\u0010.\u001a\u00020/2\n\u0010\u001c\u001a\u00060\u001dj\u0002`\u001eJ&\u0010)\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020,2\u0006\u00103\u001a\u00020\u0005J4\u00104\u001a\u0002002\u0006\u0010*\u001a\u00020\f2\u0006\u00101\u001a\u0002022\u0006\u00105\u001a\u00020/2\n\u0010\u001c\u001a\u00060\u001dj\u0002`\u001e2\u0006\u00103\u001a\u00020\u0005H\u0002J<\u00106\u001a\u0002002\u0006\u00107\u001a\u0002082\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u00052\u0006\u0010.\u001a\u00020/2\u0006\u00109\u001a\u00020:2\n\u0010;\u001a\u00060\u0019j\u0002`\u001aH\u0002J\u0018\u0010=\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u0010.\u001a\u00020/H\u0002J \u0010>\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u00052\u0006\u0010.\u001a\u00020/H\u0002J \u0010?\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u0010.\u001a\u00020/2\u0006\u00107\u001a\u00020\u0005H\u0002J\u000e\u0010@\u001a\u00020\f2\u0006\u0010A\u001a\u00020\fJ\u0016\u0010@\u001a\u00020\f2\u0006\u0010A\u001a\u00020\f2\u0006\u0010B\u001a\u00020 J$\u0010C\u001a\u00020 2\u0006\u0010\u001c\u001a\u00020:2\u0006\u0010D\u001a\u00020E2\n\u0010;\u001a\u00060\u0019j\u0002`\u001aH\u0002J\u0010\u0010F\u001a\u00020 2\u0006\u00107\u001a\u00020\u0005H\u0002J \u0010G\u001a\u0002002\u0006\u0010H\u001a\u00020/2\u0006\u0010I\u001a\u00020\f2\u0006\u0010J\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0011j\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f`\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\f0\u0015j\b\u0012\u0004\u0012\u00020\f`\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0010\u0012\f\u0012\n\u0018\u00010\u0019j\u0004\u0018\u0001`\u001a0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010<\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Entities;", "", "<init>", "()V", "ForText", "", "ForAttribute", "Normalise", "TrimLeading", "TrimTrailing", "empty", "emptyName", "", "codepointRadix", "codeDelims", "", "multipoints", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "BaseCount", "baseSorted", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "LocalEncoder", "Lcom/fleeksoft/ksoup/ported/ThreadLocal;", "Ljava/nio/charset/CharsetEncoder;", "Lcom/fleeksoft/charset/CharsetEncoder;", "encoderFor", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lcom/fleeksoft/charset/Charset;", "isNamedEntity", "", "name", "isBaseNamedEntity", "getByName", "codepointsForName", "codepoints", "", "findPrefix", TvContractCompat.PARAM_INPUT, "escape", "data", "out", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "escapeString", "escapeMode", "Lcom/fleeksoft/ksoup/nodes/Entities$EscapeMode;", "", "accum", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "options", "doEscape", "mode", "appendEscaped", "codePoint", "Lcom/fleeksoft/ksoup/ported/CodePoint;", "coreCharset", "Lcom/fleeksoft/ksoup/nodes/Entities$CoreCharset;", "fallback", "charBuf", "appendNbsp", "appendApos", "appendEncoded", "unescape", TypedValues.Custom.S_STRING, "strict", "canEncode", "c", "", "isValidXmlChar", "load", "e", "pointsData", ContentDisposition.Parameters.Size, "EscapeMode", "CoreCharset", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Entities {
    public static final int ForAttribute = 2;
    public static final int ForText = 1;
    public static final int Normalise = 4;
    public static final int TrimLeading = 8;
    public static final int TrimTrailing = 16;
    private static final int codepointRadix = 36;
    private static final int empty = -1;
    private static final String emptyName = "";
    public static final Entities INSTANCE = new Entities();
    private static final char[] codeDelims = {AbstractJsonLexerKt.COMMA, ';'};
    private static final HashMap<String, String> multipoints = new HashMap<>();
    private static final int BaseCount = 106;
    private static final ArrayList<String> baseSorted = new ArrayList<>(106);
    private static final ThreadLocal<CharsetEncoder> LocalEncoder = new ThreadLocal<>(new Function0() { // from class: com.fleeksoft.ksoup.nodes.Entities$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Entities.LocalEncoder$lambda$0();
        }
    });
    private static final ThreadLocal<char[]> charBuf = new ThreadLocal<>(new Function0() { // from class: com.fleeksoft.ksoup.nodes.Entities$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Entities.charBuf$lambda$0();
        }
    });

    /* JADX INFO: compiled from: Entities.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CoreCharset.values().length];
            try {
                iArr[CoreCharset.ascii.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CoreCharset.utf.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final CharsetEncoder LocalEncoder$lambda$0() {
        return null;
    }

    private final boolean isValidXmlChar(int codePoint) {
        if (codePoint == 9 || codePoint == 10 || codePoint == 13) {
            return true;
        }
        if (32 <= codePoint && codePoint < 55296) {
            return true;
        }
        if (57344 > codePoint || codePoint >= 65534) {
            return 65536 <= codePoint && codePoint < 1114112;
        }
        return true;
    }

    private Entities() {
    }

    private final CharsetEncoder encoderFor(Charset charset) {
        ThreadLocal<CharsetEncoder> threadLocal = LocalEncoder;
        CharsetEncoder charsetEncoderNewEncoder = threadLocal.get();
        if (charsetEncoderNewEncoder == null || !Intrinsics.areEqual(charsetEncoderNewEncoder.charset(), charset)) {
            charsetEncoderNewEncoder = charset.newEncoder();
            threadLocal.setValue(charsetEncoderNewEncoder);
        }
        Intrinsics.checkNotNull(charsetEncoderNewEncoder);
        return charsetEncoderNewEncoder;
    }

    public final boolean isNamedEntity(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return EscapeMode.extended.codepointForName(name) != -1;
    }

    public final boolean isBaseNamedEntity(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return EscapeMode.base.codepointForName(name) != -1;
    }

    public final String getByName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        String str = multipoints.get(name);
        if (str != null) {
            return str;
        }
        int iCodepointForName = EscapeMode.extended.codepointForName(name);
        if (iCodepointForName != -1) {
            return String.valueOf((char) iCodepointForName);
        }
        return "";
    }

    public final int codepointsForName(String name, int[] codepoints) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(codepoints, "codepoints");
        String str = multipoints.get(name);
        if (str != null) {
            String str2 = str;
            codepoints[0] = CharacterKt.codePointValueAt(str2, 0);
            codepoints[1] = CharacterKt.codePointValueAt(str2, 1);
            return 2;
        }
        int iCodepointForName = EscapeMode.extended.codepointForName(name);
        if (iCodepointForName == -1) {
            return 0;
        }
        codepoints[0] = iCodepointForName;
        return 1;
    }

    public final String findPrefix(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        Iterator<String> it = baseSorted.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            String next = it.next();
            if (StringsKt.startsWith$default(input, next, false, 2, (Object) null)) {
                return next;
            }
        }
        return "";
    }

    public final String escape(String data, Document.OutputSettings out) {
        Intrinsics.checkNotNullParameter(out, "out");
        return escapeString(data, out.escapeMode(), out.charset());
    }

    public final String escape(String data) {
        return escapeString(data, EscapeMode.base, Charsets.INSTANCE.getUTF8());
    }

    public final String escapeString(String data, EscapeMode escapeMode, Charset charset) {
        Intrinsics.checkNotNullParameter(escapeMode, "escapeMode");
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (data == null) {
            return "";
        }
        StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
        doEscape(data, QuietAppendable.INSTANCE.wrap(sbBorrowBuilder), escapeMode, charset, 3);
        return StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder);
    }

    public final void escape(QuietAppendable accum, String data, Document.OutputSettings out, int options) {
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(out, "out");
        doEscape(data, accum, out.escapeMode(), out.charset(), options);
    }

    private final void doEscape(String data, QuietAppendable accum, EscapeMode mode, Charset charset, int options) {
        boolean z;
        boolean z2;
        boolean z3;
        int charCount;
        CoreCharset.Companion companion = CoreCharset.INSTANCE;
        String strName = charset.name();
        Intrinsics.checkNotNullExpressionValue(strName, "name(...)");
        CoreCharset coreCharsetByName = companion.byName(strName);
        CharsetEncoder charsetEncoderEncoderFor = encoderFor(charset);
        int length = data.length();
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        int charCount2 = 0;
        while (charCount2 < length) {
            CodePoint codePointCodePointAt = CharacterKt.codePointAt(data, charCount2);
            if ((options & 4) != 0) {
                z2 = true;
                if (StringUtil.INSTANCE.isWhitespace(codePointCodePointAt.getValue())) {
                    if ((options & 8) != 0 && !z5) {
                        charCount = codePointCodePointAt.getCharCount();
                    } else if (z6) {
                        charCount = codePointCodePointAt.getCharCount();
                    } else if ((options & 16) != 0) {
                        charCount2 += codePointCodePointAt.getCharCount();
                        z4 = true;
                    } else {
                        accum.append(' ');
                        charCount2 += codePointCodePointAt.getCharCount();
                        z6 = true;
                    }
                    charCount2 += charCount;
                } else if (z4) {
                    accum.append(' ');
                    z = false;
                    z3 = false;
                } else {
                    z = z4;
                    z3 = false;
                }
            } else {
                accum = accum;
                z = z4;
                z2 = z5;
                z3 = z6;
                codePointCodePointAt = codePointCodePointAt;
                this = this;
                options = options;
            }
            this.appendEscaped(codePointCodePointAt, accum, options, mode, coreCharsetByName, charsetEncoderEncoderFor);
            charCount2 += codePointCodePointAt.getCharCount();
            z4 = z;
            z6 = z3;
            z5 = z2;
        }
    }

    private final void appendEscaped(CodePoint codePoint, QuietAppendable accum, int options, EscapeMode escapeMode, CoreCharset coreCharset, CharsetEncoder fallback) {
        if (EscapeMode.xhtml != escapeMode || isValidXmlChar(codePoint.getValue())) {
            char value = (char) codePoint.getValue();
            if (codePoint.getValue() >= Character.INSTANCE.getMIN_SUPPLEMENTARY_CODE_POINT()) {
                if (canEncode(coreCharset, value, fallback)) {
                    char[] cArr = charBuf.get();
                    accum.append(cArr, 0, codePoint.toChars(cArr, 0));
                    return;
                } else {
                    appendEncoded(accum, escapeMode, codePoint.getValue());
                    Unit unit = Unit.INSTANCE;
                    return;
                }
            }
            if (value == '&') {
                accum.append("&amp;");
                return;
            }
            if (value == 160) {
                appendNbsp(accum, escapeMode);
                Unit unit2 = Unit.INSTANCE;
                return;
            }
            if (value == '<') {
                accum.append("&lt;");
                return;
            }
            if (value == '>') {
                accum.append("&gt;");
                return;
            }
            if (value == '\"') {
                if ((options & 2) != 0) {
                    accum.append("&quot;");
                    return;
                } else {
                    accum.append(value);
                    return;
                }
            }
            if (value == '\'') {
                appendApos(accum, options, escapeMode);
                Unit unit3 = Unit.INSTANCE;
                return;
            }
            if (value == '\t' || value == '\n' || value == '\r') {
                accum.append(value);
            } else if (value < ' ' || !canEncode(coreCharset, value, fallback)) {
                appendEncoded(accum, escapeMode, codePoint.getValue());
                Unit unit4 = Unit.INSTANCE;
            } else {
                accum.append(value);
            }
        }
    }

    static final char[] charBuf$lambda$0() {
        return new char[2];
    }

    private final void appendNbsp(QuietAppendable accum, EscapeMode escapeMode) {
        if (escapeMode != EscapeMode.xhtml) {
            accum.append("&nbsp;");
        } else {
            accum.append("&#xa0;");
        }
    }

    private final void appendApos(QuietAppendable accum, int options, EscapeMode escapeMode) {
        if ((options & 2) != 0 && (options & 1) != 0) {
            if (escapeMode == EscapeMode.xhtml) {
                accum.append("&#x27;");
                return;
            } else {
                accum.append("&apos;");
                return;
            }
        }
        accum.append('\'');
    }

    private final void appendEncoded(QuietAppendable accum, EscapeMode escapeMode, int codePoint) {
        String strNameForCodepoint = escapeMode.nameForCodepoint(codePoint);
        if (!Intrinsics.areEqual("", strNameForCodepoint)) {
            accum.append(Typography.amp).append(strNameForCodepoint).append(';');
            return;
        }
        QuietAppendable quietAppendableAppend = accum.append("&#x");
        HexFormat.Builder builder = new HexFormat.Builder();
        builder.getNumber().setRemoveLeadingZeros(true);
        Unit unit = Unit.INSTANCE;
        quietAppendableAppend.append(HexExtensionsKt.toHexString(codePoint, builder.build())).append(';');
    }

    public final String unescape(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        return unescape(string, false);
    }

    public final String unescape(String string, boolean strict) {
        Intrinsics.checkNotNullParameter(string, "string");
        return Parser.INSTANCE.unescapeEntities(string, strict);
    }

    private final boolean canEncode(CoreCharset charset, char c, CharsetEncoder fallback) {
        int i = WhenMappings.$EnumSwitchMapping$0[charset.ordinal()];
        if (i == 1) {
            return c <= 128;
        }
        if (i != 2) {
            return fallback.canEncode(c);
        }
        return Intrinsics.compare((int) c, GeneratorBase.SURR1_FIRST) < 0 || c >= Character.INSTANCE.getMAX_SURROGATE() + 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void load(EscapeMode e, String pointsData, int size) {
        int i;
        e.setNameKeys$ksoup_release(new String[size]);
        e.setCodeVals$ksoup_release(new int[size]);
        e.setCodeKeys$ksoup_release(new int[size]);
        e.setNameVals$ksoup_release(new String[size]);
        CharacterReader characterReader = new CharacterReader(pointsData);
        try {
            CharacterReader characterReader2 = characterReader;
            int i2 = 0;
            while (!characterReader2.isEmpty()) {
                String strConsumeTo = characterReader2.consumeTo('=');
                characterReader2.advance();
                char[] cArr = codeDelims;
                int i3 = Integer.parseInt(characterReader2.consumeToAny(Arrays.copyOf(cArr, cArr.length)), CharsKt.checkRadix(36));
                char cCurrent = characterReader2.current();
                characterReader2.advance();
                if (cCurrent == ',') {
                    i = Integer.parseInt(characterReader2.consumeTo(';'), CharsKt.checkRadix(36));
                    characterReader2.advance();
                } else {
                    i = -1;
                }
                int i4 = Integer.parseInt(characterReader2.consumeTo(Typography.amp), CharsKt.checkRadix(36));
                characterReader2.advance();
                e.getNameKeys$ksoup_release()[i2] = strConsumeTo;
                e.getCodeVals$ksoup_release()[i2] = i3;
                e.getCodeKeys$ksoup_release()[i4] = i3;
                e.getNameVals$ksoup_release()[i4] = strConsumeTo;
                if (i != -1) {
                    multipoints.put(strConsumeTo, StringsKt.concatToString(new char[]{(char) i3, (char) i}));
                }
                i2++;
            }
            Validate.INSTANCE.isTrue(i2 == size, "Unexpected count of entities loaded");
            Unit unit = Unit.INSTANCE;
            AutoCloseableKt.closeFinally(characterReader, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(characterReader, th);
                throw th2;
            }
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.fleeksoft.ksoup.nodes.Entities$EscapeMode, still in use, count: 1, list:
      (r0v1 com.fleeksoft.ksoup.nodes.Entities$EscapeMode) from 0x004f: INVOKE (r0v2 java.lang.String[]) = (r0v1 com.fleeksoft.ksoup.nodes.Entities$EscapeMode) VIRTUAL call: com.fleeksoft.ksoup.nodes.Entities.EscapeMode.getNameKeys$ksoup_release():java.lang.String[] A[MD:():java.lang.String[] (m)]
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
    	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
    	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: compiled from: Entities.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0010\b\u0086\u0081\u0002\u0018\u0000 \"2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\"B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u0003J\u000e\u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u0005R$\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\fX\u0080.¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u0013X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0013X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R$\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\fX\u0080.¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\u001c\u0010\u000e\"\u0004\b\u001d\u0010\u0010j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006#"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Entities$EscapeMode;", "", UtilsKt.SCHEME_FILE, "", ContentDisposition.Parameters.Size, "", "<init>", "(Ljava/lang/String;ILjava/lang/String;I)V", "xhtml", TtmlNode.RUBY_BASE, "extended", "nameKeys", "", "getNameKeys$ksoup_release", "()[Ljava/lang/String;", "setNameKeys$ksoup_release", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "codeVals", "", "getCodeVals$ksoup_release", "()[I", "setCodeVals$ksoup_release", "([I)V", "codeKeys", "getCodeKeys$ksoup_release", "setCodeKeys$ksoup_release", "nameVals", "getNameVals$ksoup_release", "setNameVals$ksoup_release", "codepointForName", "name", "nameForCodepoint", "codepoint", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class EscapeMode {
        xhtml(EntitiesData.INSTANCE.getXmlPoints(), 4),
        base(EntitiesData.INSTANCE.getBasePoints(), 106),
        extended(EntitiesData.INSTANCE.getFullPoints(), 2125);

        public int[] codeKeys;
        public int[] codeVals;
        public String[] nameKeys;
        public String[] nameVals;
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<EscapeMode> getEntries() {
            return $ENTRIES;
        }

        public static EscapeMode valueOf(String str) {
            return (EscapeMode) Enum.valueOf(EscapeMode.class, str);
        }

        public static EscapeMode[] values() {
            return (EscapeMode[]) $VALUES.clone();
        }

        private EscapeMode(String str, int i) {
            super(str, i);
            Entities.INSTANCE.load(this, str, i);
        }

        static {
            ArrayList arrayList = Entities.baseSorted;
            String[] nameKeys$ksoup_release = new EscapeMode(EntitiesData.INSTANCE.getBasePoints(), 106).getNameKeys$ksoup_release();
            ArrayList arrayList2 = new ArrayList();
            for (String str : nameKeys$ksoup_release) {
                if (str != null) {
                    arrayList2.add(str);
                }
            }
            arrayList.addAll(arrayList2);
            ArrayList arrayList3 = Entities.baseSorted;
            final Function2 function2 = new Function2() { // from class: com.fleeksoft.ksoup.nodes.Entities$EscapeMode$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return Integer.valueOf(Entities.EscapeMode._init_$lambda$1((String) obj, (String) obj2));
                }
            };
            CollectionsKt.sortWith(arrayList3, new Comparator() { // from class: com.fleeksoft.ksoup.nodes.Entities$EscapeMode$$ExternalSyntheticLambda1
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return ((Number) function2.invoke(obj, obj2)).intValue();
                }
            });
        }

        public final String[] getNameKeys$ksoup_release() {
            String[] strArr = this.nameKeys;
            if (strArr != null) {
                return strArr;
            }
            Intrinsics.throwUninitializedPropertyAccessException("nameKeys");
            return null;
        }

        public final void setNameKeys$ksoup_release(String[] strArr) {
            Intrinsics.checkNotNullParameter(strArr, "<set-?>");
            this.nameKeys = strArr;
        }

        public final int[] getCodeVals$ksoup_release() {
            int[] iArr = this.codeVals;
            if (iArr != null) {
                return iArr;
            }
            Intrinsics.throwUninitializedPropertyAccessException("codeVals");
            return null;
        }

        public final void setCodeVals$ksoup_release(int[] iArr) {
            Intrinsics.checkNotNullParameter(iArr, "<set-?>");
            this.codeVals = iArr;
        }

        public final int[] getCodeKeys$ksoup_release() {
            int[] iArr = this.codeKeys;
            if (iArr != null) {
                return iArr;
            }
            Intrinsics.throwUninitializedPropertyAccessException("codeKeys");
            return null;
        }

        public final void setCodeKeys$ksoup_release(int[] iArr) {
            Intrinsics.checkNotNullParameter(iArr, "<set-?>");
            this.codeKeys = iArr;
        }

        public final String[] getNameVals$ksoup_release() {
            String[] strArr = this.nameVals;
            if (strArr != null) {
                return strArr;
            }
            Intrinsics.throwUninitializedPropertyAccessException("nameVals");
            return null;
        }

        public final void setNameVals$ksoup_release(String[] strArr) {
            Intrinsics.checkNotNullParameter(strArr, "<set-?>");
            this.nameVals = strArr;
        }

        /* JADX WARN: Code duplicated, block: B:11:0x002c  */
        /* JADX WARN: Code duplicated, block: B:13:0x0033 A[RETURN] */
        public final int codepointForName(String name) {
            int i;
            Intrinsics.checkNotNullParameter(name, "name");
            String[] nameKeys$ksoup_release = getNameKeys$ksoup_release();
            String str = name;
            int length = nameKeys$ksoup_release.length - 1;
            int i2 = 0;
            while (i2 <= length) {
                i = (i2 + length) >>> 1;
                int iCompareValues = ComparisonsKt.compareValues(nameKeys$ksoup_release[i], str);
                if (iCompareValues < 0) {
                    i2 = i + 1;
                } else {
                    if (iCompareValues <= 0) {
                        if (i >= 0) {
                            return getCodeVals$ksoup_release()[i];
                        }
                        return -1;
                    }
                    length = i - 1;
                }
            }
            i = -(i2 + 1);
            if (i >= 0) {
                return getCodeVals$ksoup_release()[i];
            }
            return -1;
        }

        /* JADX WARN: Code duplicated, block: B:18:0x003c  */
        public final String nameForCodepoint(int codepoint) {
            int i;
            String str;
            int[] codeKeys$ksoup_release = getCodeKeys$ksoup_release();
            int length = codeKeys$ksoup_release.length - 1;
            int i2 = 0;
            while (true) {
                if (i2 <= length) {
                    i = (i2 + length) >>> 1;
                    int i3 = codeKeys$ksoup_release[i];
                    if (i3 >= codepoint) {
                        if (i3 <= codepoint) {
                            break;
                        }
                        length = i - 1;
                    } else {
                        i2 = i + 1;
                    }
                } else {
                    i = -(i2 + 1);
                    break;
                }
            }
            if (i >= 0) {
                if (i < getNameVals$ksoup_release().length - 1) {
                    int i4 = i + 1;
                    if (getCodeKeys$ksoup_release()[i4] == codepoint) {
                        str = getNameVals$ksoup_release()[i4];
                    } else {
                        str = getNameVals$ksoup_release()[i];
                    }
                } else {
                    str = getNameVals$ksoup_release()[i];
                }
                Intrinsics.checkNotNull(str);
                return str;
            }
            return "";
        }

        static final int _init_$lambda$1(String str, String str2) {
            return str2.length() - str.length();
        }
    }

    /* JADX INFO: compiled from: Entities.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0007B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\b"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Entities$CoreCharset;", "", "<init>", "(Ljava/lang/String;I)V", "ascii", "utf", "fallback", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum CoreCharset {
        ascii,
        utf,
        fallback;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        public static EnumEntries<CoreCharset> getEntries() {
            return $ENTRIES;
        }

        /* JADX INFO: compiled from: Entities.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Entities$CoreCharset$Companion;", "", "<init>", "()V", "byName", "Lcom/fleeksoft/ksoup/nodes/Entities$CoreCharset;", "name", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final CoreCharset byName(String name) {
                Intrinsics.checkNotNullParameter(name, "name");
                String upperCase = name.toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                if (Intrinsics.areEqual(upperCase, "US-ASCII")) {
                    return CoreCharset.ascii;
                }
                return StringsKt.startsWith$default(name, "UTF-", false, 2, (Object) null) ? CoreCharset.utf : CoreCharset.fallback;
            }
        }
    }
}
