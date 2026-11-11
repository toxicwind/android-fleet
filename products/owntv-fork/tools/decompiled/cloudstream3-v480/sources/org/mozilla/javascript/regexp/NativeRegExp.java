package org.mozilla.javascript.regexp;

import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.common.base.Ascii;
import io.ktor.util.date.GMTDateParser;
import org.mozilla.javascript.AbstractEcmaObjectOperations;
import org.mozilla.javascript.Constructable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.ScriptRuntimeES6;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Symbol;
import org.mozilla.javascript.SymbolKey;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.Undefined;

/* JADX INFO: loaded from: classes8.dex */
public class NativeRegExp extends IdScriptableObject {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ANCHOR_BOL = -2;
    private static final int INDEX_LEN = 2;
    private static final int Id_compile = 1;
    private static final int Id_dotAll = 7;
    private static final int Id_exec = 4;
    private static final int Id_flags = 3;
    private static final int Id_global = 4;
    private static final int Id_ignoreCase = 5;
    private static final int Id_lastIndex = 1;
    private static final int Id_multiline = 6;
    private static final int Id_prefix = 6;
    private static final int Id_source = 2;
    private static final int Id_sticky = 8;
    private static final int Id_test = 5;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    public static final int JSREG_DOTALL = 8;
    public static final int JSREG_FOLD = 2;
    public static final int JSREG_GLOB = 1;
    public static final int JSREG_MULTILINE = 4;
    public static final int JSREG_STICKY = 16;
    public static final int MATCH = 1;
    private static final int MAX_INSTANCE_ID = 8;
    private static final int MAX_PROTOTYPE_ID = 9;
    public static final int PREFIX = 2;
    private static final Object REGEXP_TAG = new Object();
    private static final byte REOP_ALNUM = 9;
    private static final byte REOP_ALT = 31;
    private static final byte REOP_ALTPREREQ = 53;
    private static final byte REOP_ALTPREREQ2 = 55;
    private static final byte REOP_ALTPREREQi = 54;
    private static final byte REOP_ASSERT = 41;
    private static final byte REOP_ASSERTNOTTEST = 44;
    private static final byte REOP_ASSERTTEST = 43;
    private static final byte REOP_ASSERT_NOT = 42;
    private static final byte REOP_BACKREF = 13;
    private static final byte REOP_BOL = 2;
    private static final byte REOP_CLASS = 22;
    private static final byte REOP_DIGIT = 7;
    private static final byte REOP_DOT = 6;
    private static final byte REOP_EMPTY = 1;
    private static final byte REOP_END = 57;
    private static final byte REOP_ENDCHILD = 49;
    private static final byte REOP_EOL = 3;
    private static final byte REOP_FLAT = 14;
    private static final byte REOP_FLAT1 = 15;
    private static final byte REOP_FLAT1i = 17;
    private static final byte REOP_FLATi = 16;
    private static final byte REOP_JUMP = 32;
    private static final byte REOP_LPAREN = 29;
    private static final byte REOP_MINIMALOPT = 47;
    private static final byte REOP_MINIMALPLUS = 46;
    private static final byte REOP_MINIMALQUANT = 48;
    private static final byte REOP_MINIMALREPEAT = 52;
    private static final byte REOP_MINIMALSTAR = 45;
    private static final byte REOP_NCLASS = 23;
    private static final byte REOP_NONALNUM = 10;
    private static final byte REOP_NONDIGIT = 8;
    private static final byte REOP_NONSPACE = 12;
    private static final byte REOP_OPT = 28;
    private static final byte REOP_PLUS = 27;
    private static final byte REOP_QUANT = 25;
    private static final byte REOP_REPEAT = 51;
    private static final byte REOP_RPAREN = 30;
    private static final byte REOP_SIMPLE_END = 23;
    private static final byte REOP_SIMPLE_START = 1;
    private static final byte REOP_SPACE = 11;
    private static final byte REOP_STAR = 26;
    private static final byte REOP_UCFLAT1 = 18;
    private static final byte REOP_UCFLAT1i = 19;
    private static final byte REOP_WBDRY = 4;
    private static final byte REOP_WNONBDRY = 5;
    private static final int SymbolId_match = 7;
    private static final int SymbolId_matchAll = 8;
    private static final int SymbolId_search = 9;
    public static final int TEST = 0;
    private static final boolean debug = false;
    private static final long serialVersionUID = 4965263491464903264L;
    Object lastIndex = ScriptRuntime.zeroObj;
    private int lastIndexAttr = 6;
    private RECompiled re;

    private static boolean isControlLetter(char c) {
        if ('a' > c || c > 'z') {
            return 'A' <= c && c <= 'Z';
        }
        return true;
    }

    static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    private static boolean reopIsSimple(int i) {
        return i >= 1 && i <= 23;
    }

    private static int toASCIIHexDigit(int i) {
        if (i < 48) {
            return -1;
        }
        if (i <= 57) {
            return i - 48;
        }
        int i2 = i | 32;
        if (97 > i2 || i2 > 102) {
            return -1;
        }
        return i2 - 87;
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int getMaxInstanceId() {
        return 8;
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        NativeRegExp nativeRegExpWithLanguageVersion = NativeRegExpInstantiator.withLanguageVersion(context.getLanguageVersion());
        nativeRegExpWithLanguageVersion.re = compileRE(context, "", null, false);
        nativeRegExpWithLanguageVersion.activatePrototypeMap(9);
        nativeRegExpWithLanguageVersion.setParentScope(scriptable);
        nativeRegExpWithLanguageVersion.setPrototype(getObjectPrototype(scriptable));
        NativeRegExpCtor nativeRegExpCtor = new NativeRegExpCtor();
        nativeRegExpWithLanguageVersion.defineProperty("constructor", nativeRegExpCtor, 2);
        ScriptRuntime.setFunctionProtoAndParent(nativeRegExpCtor, context, scriptable);
        nativeRegExpCtor.setImmunePrototypeProperty(nativeRegExpWithLanguageVersion);
        if (z) {
            nativeRegExpWithLanguageVersion.sealObject();
            nativeRegExpCtor.sealObject();
        }
        defineProperty(scriptable, "RegExp", nativeRegExpCtor, 2);
        ScriptRuntimeES6.addSymbolSpecies(context, scriptable, nativeRegExpCtor);
    }

    NativeRegExp(Scriptable scriptable, RECompiled rECompiled) {
        this.re = rECompiled;
        setLastIndex(ScriptRuntime.zeroObj);
        ScriptRuntime.setBuiltinProtoAndParent(this, scriptable, TopLevel.Builtins.RegExp);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "RegExp";
    }

    @Override // org.mozilla.javascript.ScriptableObject
    public String getTypeOf() {
        return "object";
    }

    Scriptable compile(Context context, Scriptable scriptable, Object[] objArr) {
        String strEscapeRegExp;
        if (objArr.length >= 1 && (objArr[0] instanceof NativeRegExp) && (objArr.length == 1 || objArr[1] == Undefined.instance)) {
            this.re = ((NativeRegExp) objArr[0]).re;
        } else {
            if (objArr.length == 0 || objArr[0] == Undefined.instance) {
                strEscapeRegExp = "";
            } else {
                Object obj = objArr[0];
                if (obj instanceof NativeRegExp) {
                    strEscapeRegExp = new String(((NativeRegExp) objArr[0]).re.source);
                } else {
                    strEscapeRegExp = escapeRegExp(obj);
                }
            }
            String string = (objArr.length <= 1 || objArr[1] == Undefined.instance) ? null : ScriptRuntime.toString(objArr[1]);
            if (objArr.length > 0 && (objArr[0] instanceof NativeRegExp) && string != null && context.getLanguageVersion() < 200) {
                throw ScriptRuntime.typeErrorById("msg.bad.regexp.compile", new Object[0]);
            }
            this.re = compileRE(context, strEscapeRegExp, string, false);
        }
        setLastIndex(ScriptRuntime.zeroObj);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('/');
        if (this.re.source.length != 0) {
            sb.append(this.re.source);
        } else {
            sb.append("(?:)");
        }
        sb.append('/');
        appendFlags(sb);
        return sb.toString();
    }

    private void appendFlags(StringBuilder sb) {
        if ((this.re.flags & 1) != 0) {
            sb.append('g');
        }
        if ((this.re.flags & 2) != 0) {
            sb.append('i');
        }
        if ((this.re.flags & 4) != 0) {
            sb.append(GMTDateParser.MINUTES);
        }
        if ((this.re.flags & 8) != 0) {
            sb.append(GMTDateParser.SECONDS);
        }
        if ((this.re.flags & 16) != 0) {
            sb.append('y');
        }
    }

    NativeRegExp() {
    }

    private static RegExpImpl getImpl(Context context) {
        return (RegExpImpl) ScriptRuntime.getRegExpProxy(context);
    }

    private static String escapeRegExp(Object obj) {
        String string = ScriptRuntime.toString(obj);
        StringBuilder sb = null;
        int i = 0;
        for (int iIndexOf = string.indexOf(47); iIndexOf > -1; iIndexOf = string.indexOf(47, iIndexOf + 1)) {
            if (iIndexOf == i || string.charAt(iIndexOf - 1) != '\\') {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append((CharSequence) string, i, iIndexOf);
                sb.append("\\/");
                i = iIndexOf + 1;
            }
        }
        if (sb == null) {
            return string;
        }
        sb.append((CharSequence) string, i, string.length());
        return sb.toString();
    }

    Object execSub(Context context, Scriptable scriptable, Object[] objArr, int i) {
        String string;
        RegExpImpl impl = getImpl(context);
        if (objArr.length == 0) {
            string = impl.input;
            if (string == null) {
                string = ScriptRuntime.toString(Undefined.instance);
            }
        } else {
            string = ScriptRuntime.toString(objArr[0]);
        }
        String str = string;
        boolean z = ((this.re.flags & 1) == 0 && (this.re.flags & 16) == 0) ? false : true;
        double d = 0.0d;
        if (z) {
            double integer = ScriptRuntime.toInteger(this.lastIndex);
            if (integer < 0.0d || str.length() < integer) {
                setLastIndex(ScriptRuntime.zeroObj);
                return null;
            }
            d = integer;
        }
        int[] iArr = {(int) d};
        Object objExecuteRegExp = executeRegExp(context, scriptable, impl, str, iArr, i);
        if (z) {
            if (objExecuteRegExp == null || objExecuteRegExp == Undefined.instance) {
                setLastIndex(ScriptRuntime.zeroObj);
            } else {
                setLastIndex(Double.valueOf(iArr[0]));
                return objExecuteRegExp;
            }
        }
        return objExecuteRegExp;
    }

    static RECompiled compileRE(Context context, String str, String str2, boolean z) {
        int i;
        int i2;
        RECompiled rECompiled = new RECompiled(str);
        int length = str.length();
        if (str2 != null) {
            i = 0;
            for (int i3 = 0; i3 < str2.length(); i3++) {
                char cCharAt = str2.charAt(i3);
                if (cCharAt == 'g') {
                    i2 = 1;
                } else if (cCharAt == 'i') {
                    i2 = 2;
                } else if (cCharAt == 'm') {
                    i2 = 4;
                } else if (cCharAt == 's') {
                    i2 = 8;
                } else if (cCharAt == 'y') {
                    i2 = 16;
                } else {
                    reportError("msg.invalid.re.flag", String.valueOf(cCharAt));
                    i2 = 0;
                }
                if ((i & i2) != 0) {
                    reportError("msg.invalid.re.flag", String.valueOf(cCharAt));
                }
                i |= i2;
            }
        } else {
            i = 0;
        }
        rECompiled.flags = i;
        CompilerState compilerState = new CompilerState(context, rECompiled.source, length, i);
        if (z && length > 0) {
            compilerState.result = new RENode((byte) 14);
            compilerState.result.chr = compilerState.cpbegin[0];
            compilerState.result.length = length;
            compilerState.result.flatIndex = 0;
            compilerState.progLength += 5;
        } else {
            if (!parseDisjunction(compilerState)) {
                return null;
            }
            if (compilerState.maxBackReference > compilerState.parenCount) {
                compilerState = new CompilerState(context, rECompiled.source, length, i);
                compilerState.backReferenceLimit = compilerState.parenCount;
                if (!parseDisjunction(compilerState)) {
                    return null;
                }
            }
        }
        rECompiled.program = new byte[compilerState.progLength + 1];
        if (compilerState.classCount != 0) {
            rECompiled.classList = new RECharSet[compilerState.classCount];
            rECompiled.classCount = compilerState.classCount;
        }
        rECompiled.program[emitREBytecode(compilerState, rECompiled, 0, compilerState.result)] = REOP_END;
        rECompiled.parenCount = compilerState.parenCount;
        byte b = rECompiled.program[0];
        if (b == 2) {
            rECompiled.anchorCh = -2;
            return rECompiled;
        }
        if (b != 31) {
            switch (b) {
                case 14:
                case 16:
                    rECompiled.anchorCh = rECompiled.source[getIndex(rECompiled.program, 1)];
                    return rECompiled;
                case 15:
                case 17:
                    rECompiled.anchorCh = (char) (rECompiled.program[1] & 255);
                    return rECompiled;
                case 18:
                case 19:
                    rECompiled.anchorCh = (char) getIndex(rECompiled.program, 1);
                    return rECompiled;
            }
        }
        RENode rENode = compilerState.result;
        if (rENode.kid.op == 2 && rENode.kid2.op == 2) {
            rECompiled.anchorCh = -2;
        }
        return rECompiled;
    }

    private static boolean isWord(char c) {
        if ('a' > c || c > 'z') {
            return ('A' <= c && c <= 'Z') || isDigit(c) || c == '_';
        }
        return true;
    }

    private static boolean isLineTerm(char c) {
        return ScriptRuntime.isJSLineTerminator(c);
    }

    private static boolean isREWhiteSpace(int i) {
        return ScriptRuntime.isJSWhitespaceOrLineTerminator(i);
    }

    private static char upcase(char c) {
        if (c >= 128) {
            char upperCase = Character.toUpperCase(c);
            if (upperCase >= 128) {
                return upperCase;
            }
        } else if ('a' <= c && c <= 'z') {
            return (char) (c - ' ');
        }
        return c;
    }

    private static char downcase(char c) {
        if (c >= 128) {
            char lowerCase = Character.toLowerCase(c);
            if (lowerCase >= 128) {
                return lowerCase;
            }
        } else if ('A' <= c && c <= 'Z') {
            return (char) (c + ' ');
        }
        return c;
    }

    private static boolean parseDisjunction(CompilerState compilerState) {
        if (!parseAlternative(compilerState)) {
            return false;
        }
        char[] cArr = compilerState.cpbegin;
        int i = compilerState.cp;
        if (i != cArr.length && cArr[i] == '|') {
            compilerState.cp++;
            RENode rENode = new RENode((byte) 31);
            rENode.kid = compilerState.result;
            if (!parseDisjunction(compilerState)) {
                return false;
            }
            rENode.kid2 = compilerState.result;
            compilerState.result = rENode;
            if (rENode.kid.op == 14 && rENode.kid2.op == 14) {
                rENode.op = (compilerState.flags & 2) == 0 ? REOP_ALTPREREQ : REOP_ALTPREREQi;
                rENode.chr = rENode.kid.chr;
                rENode.index = rENode.kid2.chr;
                compilerState.progLength += 13;
            } else if (rENode.kid.op == 22 && rENode.kid.index < 256 && rENode.kid2.op == 14 && (compilerState.flags & 2) == 0) {
                rENode.op = REOP_ALTPREREQ2;
                rENode.chr = rENode.kid2.chr;
                rENode.index = rENode.kid.index;
                compilerState.progLength += 13;
            } else if (rENode.kid.op == 14 && rENode.kid2.op == 22 && rENode.kid2.index < 256 && (compilerState.flags & 2) == 0) {
                rENode.op = REOP_ALTPREREQ2;
                rENode.chr = rENode.kid.chr;
                rENode.index = rENode.kid2.index;
                compilerState.progLength += 13;
            } else {
                compilerState.progLength += 9;
            }
        }
        return true;
    }

    private static boolean parseAlternative(CompilerState compilerState) {
        char[] cArr = compilerState.cpbegin;
        RENode rENode = null;
        RENode rENode2 = null;
        while (compilerState.cp != compilerState.cpend && cArr[compilerState.cp] != '|' && (compilerState.parenNesting == 0 || cArr[compilerState.cp] != ')')) {
            if (!parseTerm(compilerState)) {
                return false;
            }
            if (rENode == null) {
                rENode = compilerState.result;
                rENode2 = rENode;
            } else {
                rENode2.next = compilerState.result;
            }
            while (rENode2.next != null) {
                rENode2 = rENode2.next;
            }
        }
        if (rENode == null) {
            compilerState.result = new RENode((byte) 1);
        } else {
            compilerState.result = rENode;
        }
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:30:0x004f
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:272)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:237)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:80)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:127)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:75)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:127)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:75)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:127)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:75)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:162)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:49)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    private static boolean calculateBitmapSize(org.mozilla.javascript.regexp.CompilerState r11, org.mozilla.javascript.regexp.RENode r12, char[] r13, int r14, int r15) {
        /*
            Method dump skipped, instruction units count: 296
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.calculateBitmapSize(org.mozilla.javascript.regexp.CompilerState, org.mozilla.javascript.regexp.RENode, char[], int, int):boolean");
    }

    private static void doFlat(CompilerState compilerState, char c) {
        compilerState.result = new RENode((byte) 14);
        compilerState.result.chr = c;
        compilerState.result.length = 1;
        compilerState.result.flatIndex = -1;
        compilerState.progLength += 3;
    }

    private static int getDecimalValue(char c, CompilerState compilerState, String str) {
        int i = compilerState.cp;
        char[] cArr = compilerState.cpbegin;
        int i2 = c - '0';
        boolean z = false;
        while (compilerState.cp != compilerState.cpend) {
            char c2 = cArr[compilerState.cp];
            if (!isDigit(c2)) {
                break;
            }
            if (!z && (i2 = (i2 * 10) + (c2 - '0')) >= 65535) {
                i2 = 65535;
                z = true;
            }
            compilerState.cp++;
        }
        if (z) {
            reportError(str, String.valueOf(cArr, i, compilerState.cp - i));
        }
        return i2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r18v0 */
    /* JADX WARN: Type inference failed for: r18v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r18v10 */
    /* JADX WARN: Type inference failed for: r18v11 */
    /* JADX WARN: Type inference failed for: r18v12 */
    /* JADX WARN: Type inference failed for: r18v13 */
    /* JADX WARN: Type inference failed for: r18v15 */
    /* JADX WARN: Type inference failed for: r18v16 */
    /* JADX WARN: Type inference failed for: r18v17 */
    /* JADX WARN: Type inference failed for: r18v18 */
    /* JADX WARN: Type inference failed for: r18v19 */
    /* JADX WARN: Type inference failed for: r18v20 */
    /* JADX WARN: Type inference failed for: r18v6 */
    /* JADX WARN: Type inference failed for: r18v8 */
    /* JADX WARN: Type inference failed for: r18v9 */
    /* JADX WARN: Type inference failed for: r4v62 */
    /* JADX WARN: Type inference failed for: r4v63, types: [int] */
    /* JADX WARN: Type inference failed for: r4v64, types: [int] */
    /* JADX WARN: Type inference failed for: r4v69, types: [char] */
    /* JADX WARN: Type inference failed for: r4v99 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:75:0x0169
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:272)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:237)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:80)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:127)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:75)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:127)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:75)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:127)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:75)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:49)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    private static boolean parseTerm(org.mozilla.javascript.regexp.CompilerState r19) {
        /*
            Method dump skipped, instruction units count: 1286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.parseTerm(org.mozilla.javascript.regexp.CompilerState):boolean");
    }

    private static void resolveForwardJump(byte[] bArr, int i, int i2) {
        if (i > i2) {
            throw Kit.codeBug();
        }
        addIndex(bArr, i, i2 - i);
    }

    private static int getOffset(byte[] bArr, int i) {
        return getIndex(bArr, i);
    }

    private static int addIndex(byte[] bArr, int i, int i2) {
        if (i2 < 0) {
            throw Kit.codeBug();
        }
        if (i2 > 65535) {
            throw Context.reportRuntimeError("Too complex regexp");
        }
        bArr[i] = (byte) (i2 >> 8);
        bArr[i + 1] = (byte) i2;
        return i + 2;
    }

    private static int getIndex(byte[] bArr, int i) {
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }

    /* JADX WARN: Switch 'out' block B:69:0x010a for B:22:0x0030 already processed. Defaulting to fallback option. */
    private static int emitREBytecode(CompilerState compilerState, RECompiled rECompiled, int i, RENode rENode) {
        int i2;
        byte[] bArr = rECompiled.program;
        while (rENode != null) {
            int iAddIndex = i + 1;
            bArr[i] = rENode.op;
            byte b = rENode.op;
            if (b != 1) {
                if (b != 22) {
                    if (b == 25) {
                        if (rENode.min == 0 && rENode.max == -1) {
                            bArr[i] = rENode.greedy ? (byte) 26 : REOP_MINIMALSTAR;
                        } else if (rENode.min == 0 && rENode.max == 1) {
                            bArr[i] = rENode.greedy ? (byte) 28 : REOP_MINIMALOPT;
                        } else if (rENode.min == 1 && rENode.max == -1) {
                            bArr[i] = rENode.greedy ? (byte) 27 : REOP_MINIMALPLUS;
                        } else {
                            if (!rENode.greedy) {
                                bArr[i] = 48;
                            }
                            iAddIndex = addIndex(bArr, addIndex(bArr, iAddIndex, rENode.min), rENode.max + 1);
                        }
                        int iAddIndex2 = addIndex(bArr, addIndex(bArr, iAddIndex, rENode.parenCount), rENode.parenIndex);
                        int iEmitREBytecode = emitREBytecode(compilerState, rECompiled, iAddIndex2 + 2, rENode.kid);
                        i2 = iEmitREBytecode + 1;
                        bArr[iEmitREBytecode] = REOP_ENDCHILD;
                        resolveForwardJump(bArr, iAddIndex2, i2);
                    } else if (b == 29) {
                        int iEmitREBytecode2 = emitREBytecode(compilerState, rECompiled, addIndex(bArr, iAddIndex, rENode.parenIndex), rENode.kid);
                        bArr[iEmitREBytecode2] = 30;
                        i = addIndex(bArr, iEmitREBytecode2 + 1, rENode.parenIndex);
                    } else if (b == 31) {
                        RENode rENode2 = rENode.kid2;
                        int iEmitREBytecode3 = emitREBytecode(compilerState, rECompiled, iAddIndex + 2, rENode.kid);
                        int i3 = iEmitREBytecode3 + 1;
                        bArr[iEmitREBytecode3] = 32;
                        int i4 = iEmitREBytecode3 + 3;
                        resolveForwardJump(bArr, iAddIndex, i4);
                        int iEmitREBytecode4 = emitREBytecode(compilerState, rECompiled, i4, rENode2);
                        int i5 = iEmitREBytecode4 + 1;
                        bArr[iEmitREBytecode4] = 32;
                        i = iEmitREBytecode4 + 3;
                        resolveForwardJump(bArr, i3, i);
                        resolveForwardJump(bArr, i5, i);
                    } else if (b == 13) {
                        i = addIndex(bArr, iAddIndex, rENode.parenIndex);
                    } else if (b == 14) {
                        if (rENode.flatIndex != -1) {
                            while (rENode.next != null && rENode.next.op == 14 && rENode.flatIndex + rENode.length == rENode.next.flatIndex) {
                                rENode.length += rENode.next.length;
                                rENode.next = rENode.next.next;
                            }
                        }
                        if (rENode.flatIndex != -1 && rENode.length > 1) {
                            if ((compilerState.flags & 2) != 0) {
                                bArr[i] = 16;
                            } else {
                                bArr[i] = 14;
                            }
                            i = addIndex(bArr, addIndex(bArr, iAddIndex, rENode.flatIndex), rENode.length);
                        } else if (rENode.chr < 256) {
                            if ((compilerState.flags & 2) != 0) {
                                bArr[i] = 17;
                            } else {
                                bArr[i] = 15;
                            }
                            i += 2;
                            bArr[iAddIndex] = (byte) rENode.chr;
                        } else {
                            if ((compilerState.flags & 2) != 0) {
                                bArr[i] = 19;
                            } else {
                                bArr[i] = 18;
                            }
                            i = addIndex(bArr, iAddIndex, rENode.chr);
                        }
                    } else if (b == 41) {
                        int iEmitREBytecode5 = emitREBytecode(compilerState, rECompiled, i + 3, rENode.kid);
                        i2 = iEmitREBytecode5 + 1;
                        bArr[iEmitREBytecode5] = REOP_ASSERTTEST;
                        resolveForwardJump(bArr, iAddIndex, i2);
                    } else if (b != 42) {
                        switch (b) {
                            case 53:
                            case 54:
                            case 55:
                                boolean z = rENode.op == 54;
                                char cUpcase = rENode.chr;
                                if (z) {
                                    cUpcase = upcase(cUpcase);
                                }
                                addIndex(bArr, iAddIndex, cUpcase);
                                int i6 = i + 3;
                                int iUpcase = rENode.index;
                                if (z) {
                                    iUpcase = upcase((char) iUpcase);
                                }
                                addIndex(bArr, i6, iUpcase);
                                iAddIndex = i + 5;
                                RENode rENode3 = rENode.kid2;
                                int iEmitREBytecode6 = emitREBytecode(compilerState, rECompiled, iAddIndex + 2, rENode.kid);
                                int i7 = iEmitREBytecode6 + 1;
                                bArr[iEmitREBytecode6] = 32;
                                int i8 = iEmitREBytecode6 + 3;
                                resolveForwardJump(bArr, iAddIndex, i8);
                                int iEmitREBytecode7 = emitREBytecode(compilerState, rECompiled, i8, rENode3);
                                int i9 = iEmitREBytecode7 + 1;
                                bArr[iEmitREBytecode7] = 32;
                                i = iEmitREBytecode7 + 3;
                                resolveForwardJump(bArr, i7, i);
                                resolveForwardJump(bArr, i9, i);
                                break;
                            default:
                                i = iAddIndex;
                                continue;
                        }
                    } else {
                        int iEmitREBytecode8 = emitREBytecode(compilerState, rECompiled, i + 3, rENode.kid);
                        i2 = iEmitREBytecode8 + 1;
                        bArr[iEmitREBytecode8] = REOP_ASSERTNOTTEST;
                        resolveForwardJump(bArr, iAddIndex, i2);
                    }
                    i = i2;
                } else {
                    if (!rENode.sense) {
                        bArr[i] = Ascii.ETB;
                    }
                    i = addIndex(bArr, iAddIndex, rENode.index);
                    rECompiled.classList[rENode.index] = new RECharSet(rENode.bmsize, rENode.startIndex, rENode.kidlen, rENode.sense);
                }
            }
            rENode = rENode.next;
        }
        return i;
    }

    private static void pushProgState(REGlobalData rEGlobalData, int i, int i2, int i3, REBackTrackData rEBackTrackData, int i4, int i5) {
        rEGlobalData.stateStackTop = new REProgState(rEGlobalData.stateStackTop, i, i2, i3, rEBackTrackData, i4, i5);
    }

    private static REProgState popProgState(REGlobalData rEGlobalData) {
        REProgState rEProgState = rEGlobalData.stateStackTop;
        rEGlobalData.stateStackTop = rEProgState.previous;
        return rEProgState;
    }

    private static void pushBackTrackState(REGlobalData rEGlobalData, byte b, int i) {
        REProgState rEProgState = rEGlobalData.stateStackTop;
        rEGlobalData.backTrackStackTop = new REBackTrackData(rEGlobalData, b, i, rEGlobalData.cp, rEProgState.continuationOp, rEProgState.continuationPc);
    }

    private static void pushBackTrackState(REGlobalData rEGlobalData, byte b, int i, int i2, int i3, int i4) {
        rEGlobalData.backTrackStackTop = new REBackTrackData(rEGlobalData, b, i, i2, i3, i4);
    }

    private static boolean flatNMatcher(REGlobalData rEGlobalData, int i, int i2, String str, int i3) {
        if (rEGlobalData.cp + i2 > i3) {
            return false;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            if (rEGlobalData.regexp.source[i + i4] != str.charAt(rEGlobalData.cp + i4)) {
                return false;
            }
        }
        rEGlobalData.cp += i2;
        return true;
    }

    private static boolean flatNIMatcher(REGlobalData rEGlobalData, int i, int i2, String str, int i3) {
        if (rEGlobalData.cp + i2 > i3) {
            return false;
        }
        char[] cArr = rEGlobalData.regexp.source;
        for (int i4 = 0; i4 < i2; i4++) {
            char c = cArr[i + i4];
            char cCharAt = str.charAt(rEGlobalData.cp + i4);
            if (c != cCharAt && upcase(c) != upcase(cCharAt)) {
                return false;
            }
        }
        rEGlobalData.cp += i2;
        return true;
    }

    private static boolean backrefMatcher(REGlobalData rEGlobalData, int i, String str, int i2) {
        if (rEGlobalData.parens == null || i >= rEGlobalData.parens.length) {
            return false;
        }
        int iParensIndex = rEGlobalData.parensIndex(i);
        if (iParensIndex == -1) {
            return true;
        }
        int iParensLength = rEGlobalData.parensLength(i);
        if (rEGlobalData.cp + iParensLength > i2) {
            return false;
        }
        if ((rEGlobalData.regexp.flags & 2) != 0) {
            for (int i3 = 0; i3 < iParensLength; i3++) {
                char cCharAt = str.charAt(iParensIndex + i3);
                char cCharAt2 = str.charAt(rEGlobalData.cp + i3);
                if (cCharAt != cCharAt2 && upcase(cCharAt) != upcase(cCharAt2)) {
                    return false;
                }
            }
        } else if (!str.regionMatches(iParensIndex, str, rEGlobalData.cp, iParensLength)) {
            return false;
        }
        rEGlobalData.cp += iParensLength;
        return true;
    }

    private static void addCharacterToCharSet(RECharSet rECharSet, char c) {
        int i = c / '\b';
        if (c >= rECharSet.length) {
            throw ScriptRuntime.constructError("SyntaxError", "invalid range in character class");
        }
        byte[] bArr = rECharSet.bits;
        bArr[i] = (byte) (((byte) (1 << (c & 7))) | bArr[i]);
    }

    private static void addCharacterRangeToCharSet(RECharSet rECharSet, char c, char c2) {
        int i = c / '\b';
        int i2 = c2 / '\b';
        if (c2 >= rECharSet.length || c > c2) {
            throw ScriptRuntime.constructError("SyntaxError", "invalid range in character class");
        }
        char c3 = (char) (c & 7);
        char c4 = (char) (c2 & 7);
        if (i == i2) {
            byte[] bArr = rECharSet.bits;
            bArr[i] = (byte) (((byte) ((255 >> (7 - (c4 - c3))) << c3)) | bArr[i]);
            return;
        }
        byte[] bArr2 = rECharSet.bits;
        bArr2[i] = (byte) (((byte) (255 << c3)) | bArr2[i]);
        while (true) {
            i++;
            if (i < i2) {
                rECharSet.bits[i] = -1;
            } else {
                byte[] bArr3 = rECharSet.bits;
                bArr3[i2] = (byte) (bArr3[i2] | ((byte) (255 >> (7 - c4))));
                return;
            }
        }
    }

    private static void processCharSet(REGlobalData rEGlobalData, RECharSet rECharSet) {
        synchronized (rECharSet) {
            if (!rECharSet.converted) {
                processCharSetImpl(rEGlobalData, rECharSet);
                rECharSet.converted = true;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:23:0x0057. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:24:0x005a. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x005d. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v21 */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v27 */
    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v29 */
    /* JADX WARN: Type inference failed for: r4v30 */
    /* JADX WARN: Type inference failed for: r4v31 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:40:0x0084
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:272)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:237)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:80)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:127)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:75)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:127)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:75)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:127)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:75)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:162)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:49)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    private static void processCharSetImpl(org.mozilla.javascript.regexp.REGlobalData r18, org.mozilla.javascript.regexp.RECharSet r19) {
        /*
            Method dump skipped, instruction units count: 522
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.processCharSetImpl(org.mozilla.javascript.regexp.REGlobalData, org.mozilla.javascript.regexp.RECharSet):void");
    }

    private static boolean classMatcher(REGlobalData rEGlobalData, RECharSet rECharSet, char c) {
        if (!rECharSet.converted) {
            processCharSet(rEGlobalData, rECharSet);
        }
        int i = c >> 3;
        boolean z = true;
        if (rECharSet.length != 0 && c < rECharSet.length && (rECharSet.bits[i] & (1 << (c & 7))) != 0) {
            z = false;
        }
        return rECharSet.sense ^ z;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:103:0x01d6 A[PHI: r5
      0x01d6: PHI (r5v16 boolean) = (r5v10 boolean), (r5v20 boolean), (r5v20 boolean) binds: [B:101:0x01d3, B:87:0x01a1, B:89:0x01ad] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:118:0x0203 A[PHI: r7
      0x0203: PHI (r7v13 int) = 
      (r7v0 int)
      (r7v0 int)
      (r7v0 int)
      (r7v0 int)
      (r7v0 int)
      (r7v0 int)
      (r7v0 int)
      (r7v0 int)
      (r7v0 int)
      (r7v0 int)
      (r7v0 int)
      (r7v0 int)
      (r7v0 int)
      (r7v0 int)
      (r7v0 int)
      (r7v0 int)
      (r7v0 int)
      (r7v0 int)
      (r7v7 int)
      (r7v11 int)
      (r7v11 int)
      (r7v12 int)
      (r7v12 int)
      (r7v14 int)
      (r7v14 int)
     binds: [B:115:0x01f4, B:117:0x0201, B:108:0x01df, B:110:0x01eb, B:73:0x016d, B:77:0x0181, B:68:0x0156, B:70:0x0162, B:63:0x013f, B:65:0x014b, B:58:0x0128, B:60:0x0134, B:53:0x0111, B:55:0x011d, B:48:0x00fa, B:50:0x0106, B:43:0x00e3, B:45:0x00ef, B:39:0x00c0, B:19:0x005c, B:21:0x0064, B:12:0x0038, B:16:0x004a, B:7:0x0014, B:9:0x0026] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:120:0x0206 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:121:0x0208  */
    /* JADX WARN: Code duplicated, block: B:123:0x020b  */
    private static int simpleMatch(REGlobalData rEGlobalData, String str, int i, byte[] bArr, int i2, int i3, boolean z) {
        boolean z2;
        int i4;
        char cCharAt;
        int i5 = rEGlobalData.cp;
        boolean zBackrefMatcher = true;
        switch (i) {
            case 1:
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 2:
                if (rEGlobalData.cp != 0 && (!rEGlobalData.multiline || !isLineTerm(str.charAt(rEGlobalData.cp - 1)))) {
                    zBackrefMatcher = false;
                }
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 3:
                if (rEGlobalData.cp != i3 && (!rEGlobalData.multiline || !isLineTerm(str.charAt(rEGlobalData.cp)))) {
                    zBackrefMatcher = false;
                }
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 4:
                z2 = rEGlobalData.cp == 0 || !isWord(str.charAt(rEGlobalData.cp - 1));
                if (rEGlobalData.cp < i3 && isWord(str.charAt(rEGlobalData.cp))) {
                    zBackrefMatcher = false;
                }
                zBackrefMatcher ^= z2;
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 5:
                z2 = rEGlobalData.cp == 0 || !isWord(str.charAt(rEGlobalData.cp - 1));
                if (rEGlobalData.cp >= i3 || !isWord(str.charAt(rEGlobalData.cp))) {
                    zBackrefMatcher = false;
                }
                zBackrefMatcher ^= z2;
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 6:
                if (rEGlobalData.cp == i3 || ((rEGlobalData.regexp.flags & 8) == 0 && isLineTerm(str.charAt(rEGlobalData.cp)))) {
                    zBackrefMatcher = false;
                } else {
                    rEGlobalData.cp++;
                }
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 7:
                if (rEGlobalData.cp == i3 || !isDigit(str.charAt(rEGlobalData.cp))) {
                    zBackrefMatcher = false;
                } else {
                    rEGlobalData.cp++;
                }
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 8:
                if (rEGlobalData.cp == i3 || isDigit(str.charAt(rEGlobalData.cp))) {
                    zBackrefMatcher = false;
                } else {
                    rEGlobalData.cp++;
                }
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 9:
                if (rEGlobalData.cp == i3 || !isWord(str.charAt(rEGlobalData.cp))) {
                    zBackrefMatcher = false;
                } else {
                    rEGlobalData.cp++;
                }
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 10:
                if (rEGlobalData.cp == i3 || isWord(str.charAt(rEGlobalData.cp))) {
                    zBackrefMatcher = false;
                } else {
                    rEGlobalData.cp++;
                }
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 11:
                if (rEGlobalData.cp == i3 || !isREWhiteSpace(str.charAt(rEGlobalData.cp))) {
                    zBackrefMatcher = false;
                } else {
                    rEGlobalData.cp++;
                }
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 12:
                if (rEGlobalData.cp == i3 || isREWhiteSpace(str.charAt(rEGlobalData.cp))) {
                    zBackrefMatcher = false;
                } else {
                    rEGlobalData.cp++;
                }
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 13:
                int index = getIndex(bArr, i2);
                i2 += 2;
                zBackrefMatcher = backrefMatcher(rEGlobalData, index, str, i3);
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 14:
                int index2 = getIndex(bArr, i2);
                int index3 = getIndex(bArr, i2 + 2);
                i2 += 4;
                zBackrefMatcher = flatNMatcher(rEGlobalData, index2, index3, str, i3);
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 15:
                i4 = i2 + 1;
                char c = (char) (bArr[i2] & 255);
                if (rEGlobalData.cp != i3 && str.charAt(rEGlobalData.cp) == c) {
                    rEGlobalData.cp++;
                    i2 = i4;
                    if (zBackrefMatcher) {
                        rEGlobalData.cp = i5;
                        return -1;
                    }
                    if (!z) {
                        rEGlobalData.cp = i5;
                    }
                    return i2;
                }
                i2 = i4;
                zBackrefMatcher = false;
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 16:
                int index4 = getIndex(bArr, i2);
                int index5 = getIndex(bArr, i2 + 2);
                i2 += 4;
                zBackrefMatcher = flatNIMatcher(rEGlobalData, index4, index5, str, i3);
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 17:
                i4 = i2 + 1;
                char c2 = (char) (bArr[i2] & 255);
                if (rEGlobalData.cp != i3) {
                    char cCharAt2 = str.charAt(rEGlobalData.cp);
                    if (c2 == cCharAt2 || upcase(c2) == upcase(cCharAt2)) {
                        rEGlobalData.cp++;
                    } else {
                        zBackrefMatcher = false;
                    }
                    i2 = i4;
                    if (zBackrefMatcher) {
                        rEGlobalData.cp = i5;
                        return -1;
                    }
                    if (!z) {
                        rEGlobalData.cp = i5;
                    }
                    return i2;
                }
                i2 = i4;
                zBackrefMatcher = false;
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 18:
                char index6 = (char) getIndex(bArr, i2);
                i2 += 2;
                if (rEGlobalData.cp == i3 || str.charAt(rEGlobalData.cp) != index6) {
                    zBackrefMatcher = false;
                } else {
                    rEGlobalData.cp++;
                }
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 19:
                char index7 = (char) getIndex(bArr, i2);
                i2 += 2;
                if (rEGlobalData.cp == i3 || !(index7 == (cCharAt = str.charAt(rEGlobalData.cp)) || upcase(index7) == upcase(cCharAt))) {
                    zBackrefMatcher = false;
                } else {
                    rEGlobalData.cp++;
                }
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
            case 20:
            case 21:
            default:
                throw Kit.codeBug();
            case 22:
            case 23:
                int index8 = getIndex(bArr, i2);
                i2 += 2;
                if (rEGlobalData.cp == i3 || !classMatcher(rEGlobalData, rEGlobalData.regexp.classList[index8], str.charAt(rEGlobalData.cp))) {
                    zBackrefMatcher = false;
                } else {
                    rEGlobalData.cp++;
                }
                if (zBackrefMatcher) {
                    rEGlobalData.cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.cp = i5;
                }
                return i2;
        }
    }

    /*  JADX ERROR: Types fix failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 11821. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:99)
        */
    private static boolean executeREBytecode(org.mozilla.javascript.Context r19, org.mozilla.javascript.regexp.REGlobalData r20, java.lang.String r21, int r22) {
        /*
            Method dump skipped, instruction units count: 1182
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.executeREBytecode(org.mozilla.javascript.Context, org.mozilla.javascript.regexp.REGlobalData, java.lang.String, int):boolean");
    }

    private static boolean matchRegExp(Context context, REGlobalData rEGlobalData, RECompiled rECompiled, String str, int i, int i2, boolean z) {
        if (rECompiled.parenCount != 0) {
            rEGlobalData.parens = new long[rECompiled.parenCount];
        } else {
            rEGlobalData.parens = null;
        }
        rEGlobalData.backTrackStackTop = null;
        rEGlobalData.stateStackTop = null;
        rEGlobalData.multiline = z || (rECompiled.flags & 4) != 0;
        rEGlobalData.regexp = rECompiled;
        int i3 = rEGlobalData.regexp.anchorCh;
        int i4 = i;
        while (i4 <= i2) {
            if (i3 >= 0) {
                while (i4 != i2) {
                    char cCharAt = str.charAt(i4);
                    if (cCharAt != i3 && ((rEGlobalData.regexp.flags & 2) == 0 || upcase(cCharAt) != upcase((char) i3))) {
                        if ((rEGlobalData.regexp.flags & 16) != 0) {
                            return false;
                        }
                        i4++;
                    }
                }
                return false;
            }
            rEGlobalData.cp = i4;
            rEGlobalData.skipped = i4 - i;
            for (int i5 = 0; i5 < rECompiled.parenCount; i5++) {
                rEGlobalData.parens[i5] = -1;
            }
            boolean zExecuteREBytecode = executeREBytecode(context, rEGlobalData, str, i2);
            rEGlobalData.backTrackStackTop = null;
            rEGlobalData.stateStackTop = null;
            if (zExecuteREBytecode) {
                return true;
            }
            if (i3 == -2 && !rEGlobalData.multiline) {
                rEGlobalData.skipped = i2;
                return false;
            }
            if ((rEGlobalData.regexp.flags & 16) != 0) {
                return false;
            }
            i4 = rEGlobalData.skipped + i + 1;
        }
        return false;
    }

    Object executeRegExp(Context context, Scriptable scriptable, RegExpImpl regExpImpl, String str, int[] iArr, int i) {
        Context context2;
        Scriptable scriptable2;
        Object obj;
        NativeRegExp nativeRegExp = this;
        REGlobalData rEGlobalData = new REGlobalData();
        int i2 = iArr[0];
        int length = str.length();
        int i3 = i2 > length ? length : i2;
        SubString subString = null;
        if (!matchRegExp(context, rEGlobalData, nativeRegExp.re, str, i3, length, regExpImpl.multiline)) {
            if (i != 2) {
                return null;
            }
            return Undefined.instance;
        }
        int i4 = rEGlobalData.cp;
        iArr[0] = i4;
        int i5 = i4 - (rEGlobalData.skipped + i3);
        int i6 = i4 - i5;
        if (i == 0) {
            scriptable2 = null;
            obj = Boolean.TRUE;
            context2 = context;
        } else {
            context2 = context;
            Scriptable scriptableNewArray = context2.newArray(scriptable, 0);
            scriptableNewArray.put(0, scriptableNewArray, str.substring(i6, i6 + i5));
            scriptable2 = scriptableNewArray;
            obj = scriptableNewArray;
        }
        if (nativeRegExp.re.parenCount == 0) {
            regExpImpl.parens = null;
            regExpImpl.lastParen = new SubString();
        } else {
            regExpImpl.parens = new SubString[nativeRegExp.re.parenCount];
            int i7 = 0;
            while (i7 < nativeRegExp.re.parenCount) {
                int iParensIndex = rEGlobalData.parensIndex(i7);
                if (iParensIndex != -1) {
                    subString = new SubString(str, iParensIndex, rEGlobalData.parensLength(i7));
                    regExpImpl.parens[i7] = subString;
                    if (i != 0) {
                        scriptable2.put(i7 + 1, scriptable2, subString.toString());
                    }
                } else if (i != 0) {
                    scriptable2.put(i7 + 1, scriptable2, Undefined.instance);
                }
                i7++;
                nativeRegExp = this;
            }
            regExpImpl.lastParen = subString;
        }
        if (i != 0) {
            scriptable2.put("index", scriptable2, Integer.valueOf(rEGlobalData.skipped + i3));
            scriptable2.put(TvContractCompat.PARAM_INPUT, scriptable2, str);
        }
        if (regExpImpl.lastMatch == null) {
            regExpImpl.lastMatch = new SubString();
            regExpImpl.leftContext = new SubString();
            regExpImpl.rightContext = new SubString();
        }
        regExpImpl.lastMatch.str = str;
        regExpImpl.lastMatch.index = i6;
        regExpImpl.lastMatch.length = i5;
        regExpImpl.leftContext.str = str;
        if (context2.getLanguageVersion() == 120) {
            regExpImpl.leftContext.index = i3;
            regExpImpl.leftContext.length = rEGlobalData.skipped;
        } else {
            regExpImpl.leftContext.index = 0;
            regExpImpl.leftContext.length = i3 + rEGlobalData.skipped;
        }
        regExpImpl.rightContext.str = str;
        regExpImpl.rightContext.index = i4;
        regExpImpl.rightContext.length = length - i4;
        return obj;
    }

    int getFlags() {
        return this.re.flags;
    }

    private static void reportWarning(Context context, String str, String str2) {
        if (context.hasFeature(11)) {
            Context.reportWarning(ScriptRuntime.getMessageById(str, str2));
        }
    }

    private static void reportError(String str, String str2) {
        throw ScriptRuntime.constructError("SyntaxError", ScriptRuntime.getMessageById(str, str2));
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findInstanceIdInfo(String str) {
        str.hashCode();
        int i = 6;
        int i2 = 7;
        switch (str) {
            case "dotAll":
                i = 7;
                break;
            case "global":
                i = 4;
                break;
            case "multiline":
                break;
            case "source":
                i = 2;
                break;
            case "sticky":
                i = 8;
                break;
            case "flags":
                i = 3;
                break;
            case "ignoreCase":
                i = 5;
                break;
            case "lastIndex":
                i = 1;
                break;
            default:
                i = 0;
                break;
        }
        if (i == 0) {
            return super.findInstanceIdInfo(str);
        }
        switch (i) {
            case 1:
                i2 = this.lastIndexAttr;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                break;
            default:
                throw new IllegalStateException();
        }
        return instanceIdInfo(i2, i);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected String getInstanceIdName(int i) {
        switch (i) {
            case 1:
                return "lastIndex";
            case 2:
                return "source";
            case 3:
                return "flags";
            case 4:
                return "global";
            case 5:
                return "ignoreCase";
            case 6:
                return "multiline";
            case 7:
                return "dotAll";
            case 8:
                return "sticky";
            default:
                return super.getInstanceIdName(i);
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected Object getInstanceIdValue(int i) {
        switch (i) {
            case 1:
                return this.lastIndex;
            case 2:
                return new String(this.re.source);
            case 3:
                StringBuilder sb = new StringBuilder();
                appendFlags(sb);
                return sb.toString();
            case 4:
                return ScriptRuntime.wrapBoolean((this.re.flags & 1) != 0);
            case 5:
                return ScriptRuntime.wrapBoolean((this.re.flags & 2) != 0);
            case 6:
                return ScriptRuntime.wrapBoolean((this.re.flags & 4) != 0);
            case 7:
                return ScriptRuntime.wrapBoolean((this.re.flags & 8) != 0);
            case 8:
                return ScriptRuntime.wrapBoolean((this.re.flags & 16) != 0);
            default:
                return super.getInstanceIdValue(i);
        }
    }

    private void setLastIndex(Object obj) {
        if ((this.lastIndexAttr & 1) != 0) {
            throw ScriptRuntime.typeErrorById("msg.modify.readonly", "lastIndex");
        }
        this.lastIndex = obj;
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void setInstanceIdValue(int i, Object obj) {
        switch (i) {
            case 1:
                setLastIndex(obj);
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                break;
            default:
                super.setInstanceIdValue(i, obj);
                break;
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void setInstanceIdAttributes(int i, int i2) {
        if (i == 1) {
            this.lastIndexAttr = i2;
        } else {
            super.setInstanceIdAttributes(i, i2);
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int i) {
        String str;
        String str2;
        if (i == 7) {
            initPrototypeMethod(REGEXP_TAG, i, SymbolKey.MATCH, "[Symbol.match]", 1);
            return;
        }
        if (i == 8) {
            initPrototypeMethod(REGEXP_TAG, i, SymbolKey.MATCH_ALL, "[Symbol.matchAll]", 1);
            return;
        }
        if (i == 9) {
            initPrototypeMethod(REGEXP_TAG, i, SymbolKey.SEARCH, "[Symbol.search]", 1);
            return;
        }
        int i2 = 0;
        int i3 = 1;
        switch (i) {
            case 1:
                i2 = 2;
                str = "compile";
                String str3 = str;
                i3 = i2;
                str2 = str3;
                initPrototypeMethod(REGEXP_TAG, i, str2, i3);
                return;
            case 2:
                str = "toString";
                String str4 = str;
                i3 = i2;
                str2 = str4;
                initPrototypeMethod(REGEXP_TAG, i, str2, i3);
                return;
            case 3:
                str = "toSource";
                String str5 = str;
                i3 = i2;
                str2 = str5;
                initPrototypeMethod(REGEXP_TAG, i, str2, i3);
                return;
            case 4:
                str2 = "exec";
                initPrototypeMethod(REGEXP_TAG, i, str2, i3);
                return;
            case 5:
                str2 = "test";
                initPrototypeMethod(REGEXP_TAG, i, str2, i3);
                return;
            case 6:
                str2 = "prefix";
                initPrototypeMethod(REGEXP_TAG, i, str2, i3);
                return;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(REGEXP_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int iMethodId = idFunctionObject.methodId();
        switch (iMethodId) {
            case 1:
                return realThis(scriptable2, idFunctionObject).compile(context, scriptable, objArr);
            case 2:
                if (scriptable2 != scriptable && (scriptable2 instanceof NativeObject)) {
                    Object obj = scriptable2.get("source", scriptable2);
                    String strEscapeRegExp = obj.equals(NOT_FOUND) ? "undefined" : escapeRegExp(obj);
                    Object obj2 = scriptable2.get("flags", scriptable2);
                    return "/" + strEscapeRegExp + "/" + (obj2.equals(NOT_FOUND) ? "undefined" : obj2.toString());
                }
                return realThis(scriptable2, idFunctionObject).toString();
            case 3:
                return realThis(scriptable2, idFunctionObject).toString();
            case 4:
                return js_exec(context, scriptable, scriptable2, objArr);
            case 5:
                return Boolean.TRUE.equals(realThis(scriptable2, idFunctionObject).execSub(context, scriptable, objArr, 0)) ? Boolean.TRUE : Boolean.FALSE;
            case 6:
                return realThis(scriptable2, idFunctionObject).execSub(context, scriptable, objArr, 2);
            case 7:
                return realThis(scriptable2, idFunctionObject).execSub(context, scriptable, objArr, 1);
            case 8:
                return js_SymbolMatchAll(context, scriptable, scriptable2, objArr);
            case 9:
                Scriptable scriptable3 = (Scriptable) realThis(scriptable2, idFunctionObject).execSub(context, scriptable, objArr, 1);
                if (scriptable3 == null) {
                    return -1;
                }
                return scriptable3.get("index", scriptable3);
            default:
                throw new IllegalArgumentException(String.valueOf(iMethodId));
        }
    }

    static Object js_exec(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return realThis(scriptable2, "exec").execSub(context, scriptable, objArr, 1);
    }

    private Object js_SymbolMatchAll(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!ScriptRuntime.isObject(scriptable2)) {
            throw ScriptRuntime.typeErrorById("msg.arg.not.object", ScriptRuntime.typeof(scriptable2));
        }
        String string = ScriptRuntime.toString(objArr.length > 0 ? objArr[0] : Undefined.instance);
        Constructable constructableSpeciesConstructor = AbstractEcmaObjectOperations.speciesConstructor(context, scriptable2, ScriptRuntime.getExistingCtor(context, ScriptableObject.getTopLevelScope(scriptable), getClassName()));
        String string2 = ScriptRuntime.toString(ScriptRuntime.getObjectProp(scriptable2, "flags", context));
        Scriptable scriptableConstruct = constructableSpeciesConstructor.construct(context, scriptable, new Object[]{scriptable2, string2});
        ScriptRuntime.setObjectProp(scriptableConstruct, "lastIndex", (Object) Long.valueOf(ScriptRuntime.toLength(ScriptRuntime.getObjectProp(scriptable2, "lastIndex", context))), context);
        return new NativeRegExpStringIterator(scriptable, scriptableConstruct, string, string2.indexOf(103) != -1, (string2.indexOf(117) == -1 && string2.indexOf(118) == -1) ? false : true);
    }

    private static NativeRegExp realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        return realThis(scriptable, idFunctionObject.getFunctionName());
    }

    private static NativeRegExp realThis(Scriptable scriptable, String str) {
        return (NativeRegExp) ensureType(scriptable, NativeRegExp.class, str);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(Symbol symbol) {
        if (SymbolKey.MATCH.equals(symbol)) {
            return 7;
        }
        if (SymbolKey.MATCH_ALL.equals(symbol)) {
            return 8;
        }
        return SymbolKey.SEARCH.equals(symbol) ? 9 : 0;
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        str.hashCode();
        switch (str) {
            case "toSource":
                return 3;
            case "toString":
                return 2;
            case "prefix":
                return 6;
            case "exec":
                return 4;
            case "test":
                return 5;
            case "compile":
                return 1;
            default:
                return 0;
        }
    }
}
