package org.mozilla.javascript;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.work.WorkInfo;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.google.common.base.Ascii;
import io.ktor.http.ContentType;
import java.io.Serializable;
import org.mozilla.javascript.xml.XMLLib;

/* JADX INFO: loaded from: classes8.dex */
public class NativeGlobal implements Serializable, IdFunctionCall {
    private static final Object FTAG = "Global";
    private static final int INVALID_UTF8 = Integer.MAX_VALUE;
    private static final int Id_decodeURI = 1;
    private static final int Id_decodeURIComponent = 2;
    private static final int Id_encodeURI = 3;
    private static final int Id_encodeURIComponent = 4;
    private static final int Id_escape = 5;
    private static final int Id_eval = 6;
    private static final int Id_isFinite = 7;
    private static final int Id_isNaN = 8;
    private static final int Id_isXMLName = 9;
    private static final int Id_new_AggregateError = 15;
    private static final int Id_new_CommonError = 14;
    private static final int Id_parseFloat = 10;
    private static final int Id_parseInt = 11;
    private static final int Id_unescape = 12;
    private static final int Id_uneval = 13;
    private static final int LAST_SCOPE_FUNCTION_ID = 13;
    private static final String URI_DECODE_RESERVED = ";/?:@&=+$,#";
    static final long serialVersionUID = 6080442165748707530L;

    private static int unHex(char c) {
        if ('A' <= c && c <= 'F') {
            return c - '7';
        }
        if ('a' <= c && c <= 'f') {
            return c - 'W';
        }
        if ('0' > c || c > '9') {
            return -1;
        }
        return c - '0';
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0049  */
    /* JADX WARN: Code duplicated, block: B:44:0x004c A[SYNTHETIC] */
    public static void init(Context context, Scriptable scriptable, boolean z) {
        IdFunctionObject idFunctionObject;
        String str;
        String str2;
        int i;
        IdFunctionObject idFunctionObject2;
        NativeGlobal nativeGlobal = new NativeGlobal();
        for (int i2 = 1; i2 <= 13; i2++) {
            switch (i2) {
                case 1:
                    str = "decodeURI";
                    str2 = str;
                    i = 1;
                    idFunctionObject2 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                        idFunctionObject2.sealObject();
                    }
                    idFunctionObject2.exportAsScopeProperty();
                    break;
                case 2:
                    str = "decodeURIComponent";
                    str2 = str;
                    i = 1;
                    idFunctionObject2 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                        idFunctionObject2.sealObject();
                    }
                    idFunctionObject2.exportAsScopeProperty();
                    break;
                case 3:
                    str = "encodeURI";
                    str2 = str;
                    i = 1;
                    idFunctionObject2 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                        idFunctionObject2.sealObject();
                    }
                    idFunctionObject2.exportAsScopeProperty();
                    break;
                case 4:
                    str = "encodeURIComponent";
                    str2 = str;
                    i = 1;
                    idFunctionObject2 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                        idFunctionObject2.sealObject();
                    }
                    idFunctionObject2.exportAsScopeProperty();
                    break;
                case 5:
                    str = "escape";
                    str2 = str;
                    i = 1;
                    idFunctionObject2 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                        idFunctionObject2.sealObject();
                    }
                    idFunctionObject2.exportAsScopeProperty();
                    break;
                case 6:
                    str = "eval";
                    str2 = str;
                    i = 1;
                    idFunctionObject2 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                        idFunctionObject2.sealObject();
                    }
                    idFunctionObject2.exportAsScopeProperty();
                    break;
                case 7:
                    str = "isFinite";
                    str2 = str;
                    i = 1;
                    idFunctionObject2 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                        idFunctionObject2.sealObject();
                    }
                    idFunctionObject2.exportAsScopeProperty();
                    break;
                case 8:
                    str = "isNaN";
                    str2 = str;
                    i = 1;
                    idFunctionObject2 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                        idFunctionObject2.sealObject();
                    }
                    idFunctionObject2.exportAsScopeProperty();
                    break;
                case 9:
                    str = "isXMLName";
                    str2 = str;
                    i = 1;
                    idFunctionObject2 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                        idFunctionObject2.sealObject();
                    }
                    idFunctionObject2.exportAsScopeProperty();
                    break;
                case 10:
                    str = "parseFloat";
                    str2 = str;
                    i = 1;
                    idFunctionObject2 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                        idFunctionObject2.sealObject();
                    }
                    idFunctionObject2.exportAsScopeProperty();
                    break;
                case 11:
                    str2 = "parseInt";
                    i = 2;
                    idFunctionObject2 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                        idFunctionObject2.sealObject();
                    }
                    idFunctionObject2.exportAsScopeProperty();
                    break;
                case 12:
                    str = "unescape";
                    str2 = str;
                    i = 1;
                    idFunctionObject2 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                        idFunctionObject2.sealObject();
                    }
                    idFunctionObject2.exportAsScopeProperty();
                    break;
                case 13:
                    str = "uneval";
                    str2 = str;
                    i = 1;
                    idFunctionObject2 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                        idFunctionObject2.sealObject();
                    }
                    idFunctionObject2.exportAsScopeProperty();
                    break;
                default:
                    throw Kit.codeBug();
            }
        }
        Scriptable scriptable2 = scriptable;
        ScriptableObject.defineProperty(scriptable2, "NaN", ScriptRuntime.NaNobj, 7);
        ScriptableObject.defineProperty(scriptable2, "Infinity", ScriptRuntime.wrapNumber(Double.POSITIVE_INFINITY), 7);
        ScriptableObject.defineProperty(scriptable2, "undefined", Undefined.instance, 7);
        ScriptableObject.defineProperty(scriptable2, "globalThis", scriptable2, 2);
        Scriptable scriptableEnsureScriptable = ScriptableObject.ensureScriptable(ScriptableObject.getProperty(scriptable2, "Error"));
        Scriptable scriptableEnsureScriptable2 = ScriptableObject.ensureScriptable(ScriptableObject.getProperty(scriptableEnsureScriptable, "prototype"));
        TopLevel.NativeErrors[] nativeErrorsArrValues = TopLevel.NativeErrors.values();
        int length = nativeErrorsArrValues.length;
        int i3 = 0;
        while (i3 < length) {
            TopLevel.NativeErrors nativeErrors = nativeErrorsArrValues[i3];
            if (nativeErrors != TopLevel.NativeErrors.Error) {
                String strName = nativeErrors.name();
                Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable2);
                NativeError nativeErrorMakeProto = NativeError.makeProto(topLevelScope, (IdFunctionObject) TopLevel.getBuiltinCtor(context, topLevelScope, TopLevel.Builtins.Error));
                nativeErrorMakeProto.defineProperty("name", strName, 2);
                nativeErrorMakeProto.defineProperty(ContentType.Message.TYPE, "", 2);
                if (nativeErrors == TopLevel.NativeErrors.AggregateError) {
                    idFunctionObject = new IdFunctionObject(nativeGlobal, FTAG, 15, strName, 2, scriptable2);
                } else {
                    idFunctionObject = new IdFunctionObject(nativeGlobal, FTAG, 14, strName, 1, scriptable);
                }
                idFunctionObject.markAsConstructor(nativeErrorMakeProto);
                idFunctionObject.setPrototype(scriptableEnsureScriptable);
                nativeErrorMakeProto.put("constructor", nativeErrorMakeProto, idFunctionObject);
                nativeErrorMakeProto.setAttributes("constructor", 2);
                nativeErrorMakeProto.setPrototype(scriptableEnsureScriptable2);
                if (z) {
                    nativeErrorMakeProto.sealObject();
                    idFunctionObject.sealObject();
                }
                idFunctionObject.setAttributes("name", 3);
                idFunctionObject.setAttributes("length", 3);
                idFunctionObject.exportAsScopeProperty();
            }
            i3++;
            scriptable2 = scriptable;
        }
    }

    @Override // org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (idFunctionObject.hasTag(FTAG)) {
            int iMethodId = idFunctionObject.methodId();
            switch (iMethodId) {
                case 1:
                case 2:
                    return decode(ScriptRuntime.toString(objArr, 0), iMethodId == 1);
                case 3:
                case 4:
                    return encode(ScriptRuntime.toString(objArr, 0), iMethodId == 3);
                case 5:
                    return js_escape(objArr);
                case 6:
                    return js_eval(context, scriptable, objArr);
                case 7:
                    if (objArr.length < 1) {
                        return Boolean.FALSE;
                    }
                    return NativeNumber.isFinite(objArr[0]);
                case 8:
                    return ScriptRuntime.wrapBoolean(objArr.length >= 1 ? Double.isNaN(ScriptRuntime.toNumber(objArr[0])) : true);
                case 9:
                    return ScriptRuntime.wrapBoolean(XMLLib.extractFromScope(scriptable).isXMLName(context, objArr.length == 0 ? Undefined.instance : objArr[0]));
                case 10:
                    return js_parseFloat(objArr);
                case 11:
                    return js_parseInt(context, objArr);
                case 12:
                    return js_unescape(objArr);
                case 13:
                    return ScriptRuntime.uneval(context, scriptable, objArr.length != 0 ? objArr[0] : Undefined.instance);
                case 14:
                    return NativeError.make(context, scriptable, idFunctionObject, objArr);
                case 15:
                    return NativeError.makeAggregate(context, scriptable, idFunctionObject, objArr);
            }
        }
        throw idFunctionObject.unknown();
    }

    /* JADX WARN: Code duplicated, block: B:18:0x002e A[PHI: r0
      0x002e: PHI (r0v1 boolean) = (r0v0 boolean), (r0v3 boolean) binds: [B:13:0x0025, B:17:0x002c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:53:0x008a  */
    static Object js_parseInt(Context context, Object[] objArr) {
        char cCharAt;
        char cCharAt2;
        boolean z = false;
        String string = ScriptRuntime.toString(objArr, 0);
        int int32 = ScriptRuntime.toInt32(objArr, 1);
        int length = string.length();
        if (length == 0) {
            return ScriptRuntime.NaNobj;
        }
        int i = 0;
        do {
            cCharAt = string.charAt(i);
            if (!ScriptRuntime.isStrWhiteSpaceChar(cCharAt)) {
                break;
            }
            i++;
        } while (i < length);
        if (cCharAt == '+') {
            i++;
        } else {
            z = cCharAt == '-';
            if (z) {
                i++;
            }
        }
        int i2 = 16;
        if (int32 == 0) {
            int32 = -1;
        } else {
            if (int32 < 2 || int32 > 36) {
                return ScriptRuntime.NaNobj;
            }
            if (int32 == 16 && length - i > 1 && string.charAt(i) == '0' && ((cCharAt2 = string.charAt(i + 1)) == 'x' || cCharAt2 == 'X')) {
                i += 2;
            }
        }
        if (int32 != -1) {
            i2 = int32;
        } else if (length - i <= 1 || string.charAt(i) != '0') {
            i2 = 10;
        } else {
            int i3 = i + 1;
            char cCharAt3 = string.charAt(i3);
            if (cCharAt3 == 'x' || cCharAt3 == 'X') {
                i += 2;
            } else if ('0' > cCharAt3 || cCharAt3 > '9' || (context != null && context.getLanguageVersion() >= 150)) {
                i2 = 10;
            } else {
                i2 = 8;
                i = i3;
            }
        }
        double dStringPrefixToNumber = ScriptRuntime.stringPrefixToNumber(string, i, i2);
        if (z) {
            dStringPrefixToNumber = -dStringPrefixToNumber;
        }
        return ScriptRuntime.wrapNumber(dStringPrefixToNumber);
    }

    /* JADX WARN: Code duplicated, block: B:68:0x00a1 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:70:0x00a4  */
    static Object js_parseFloat(Object[] objArr) {
        int i;
        if (objArr.length < 1) {
            return ScriptRuntime.NaNobj;
        }
        boolean z = false;
        String string = ScriptRuntime.toString(objArr[0]);
        int length = string.length();
        for (int i2 = 0; i2 != length; i2++) {
            char cCharAt = string.charAt(i2);
            if (!ScriptRuntime.isStrWhiteSpaceChar(cCharAt)) {
                if (cCharAt == '+' || cCharAt == '-') {
                    int i3 = i2 + 1;
                    if (i3 == length) {
                        return ScriptRuntime.NaNobj;
                    }
                    i = i3;
                    cCharAt = string.charAt(i3);
                } else {
                    i = i2;
                }
                if (cCharAt == 'I') {
                    if (i + 8 <= length && string.regionMatches(i, "Infinity", 0, 8)) {
                        return ScriptRuntime.wrapNumber(string.charAt(i2) == '-' ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
                    }
                    return ScriptRuntime.NaNobj;
                }
                int i4 = -1;
                int i5 = -1;
                try {
                    while (i < length) {
                        char cCharAt2 = string.charAt(i);
                        if (cCharAt2 != '+') {
                            if (cCharAt2 == 'E' || cCharAt2 == 'e') {
                                if (i4 != -1 || i == length - 1) {
                                    if (i4 != -1 || z) {
                                        i4 = i;
                                    }
                                    return Double.valueOf(string.substring(i2, i4));
                                }
                                i4 = i;
                                i++;
                            } else if (cCharAt2 != '-') {
                                if (cCharAt2 != '.') {
                                    switch (cCharAt2) {
                                        case '0':
                                        case '1':
                                        case '2':
                                        case '3':
                                        case '4':
                                        case '5':
                                        case '6':
                                        case '7':
                                        case '8':
                                        case '9':
                                            if (i4 != -1) {
                                                z = true;
                                            }
                                            break;
                                        default:
                                            if (i4 != -1) {
                                                i4 = i;
                                            } else {
                                                i4 = i;
                                            }
                                            return Double.valueOf(string.substring(i2, i4));
                                    }
                                    i++;
                                } else {
                                    if (i5 != -1) {
                                        if (i4 != -1) {
                                            i4 = i;
                                        } else {
                                            i4 = i;
                                        }
                                        return Double.valueOf(string.substring(i2, i4));
                                    }
                                    i5 = i;
                                    i++;
                                }
                            }
                        }
                        if (i4 == i - 1) {
                            if (i == length - 1) {
                                i--;
                            } else {
                                i++;
                            }
                        }
                        if (i4 != -1) {
                            i4 = i;
                        } else {
                            i4 = i;
                        }
                        return Double.valueOf(string.substring(i2, i4));
                    }
                    return Double.valueOf(string.substring(i2, i4));
                } catch (NumberFormatException unused) {
                    return ScriptRuntime.NaNobj;
                }
                if (i4 != -1) {
                    i4 = i;
                } else {
                    i4 = i;
                }
            }
        }
        return ScriptRuntime.NaNobj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001d, code lost:
    
        if ((r11 & (-8)) == 0) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.Object js_escape(java.lang.Object[] r11) {
        /*
            r0 = 0
            java.lang.String r1 = org.mozilla.javascript.ScriptRuntime.toString(r11, r0)
            int r2 = r11.length
            r3 = 1
            if (r2 <= r3) goto L29
            r11 = r11[r3]
            double r4 = org.mozilla.javascript.ScriptRuntime.toNumber(r11)
            boolean r11 = java.lang.Double.isNaN(r4)
            if (r11 != 0) goto L20
            int r11 = (int) r4
            double r6 = (double) r11
            int r2 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r2 != 0) goto L20
            r2 = r11 & (-8)
            if (r2 != 0) goto L20
            goto L2a
        L20:
            java.lang.String r11 = "msg.bad.esc.mask"
            java.lang.Object[] r0 = new java.lang.Object[r0]
            org.mozilla.javascript.EvaluatorException r11 = org.mozilla.javascript.Context.reportRuntimeErrorById(r11, r0)
            throw r11
        L29:
            r11 = 7
        L2a:
            int r2 = r1.length()
            r4 = 0
        L2f:
            if (r0 == r2) goto Lc0
            char r5 = r1.charAt(r0)
            r6 = 43
            if (r11 == 0) goto L76
            r7 = 48
            if (r5 < r7) goto L41
            r7 = 57
            if (r5 <= r7) goto L6f
        L41:
            r7 = 65
            if (r5 < r7) goto L49
            r7 = 90
            if (r5 <= r7) goto L6f
        L49:
            r7 = 97
            if (r5 < r7) goto L51
            r7 = 122(0x7a, float:1.71E-43)
            if (r5 <= r7) goto L6f
        L51:
            r7 = 64
            if (r5 == r7) goto L6f
            r7 = 42
            if (r5 == r7) goto L6f
            r7 = 95
            if (r5 == r7) goto L6f
            r7 = 45
            if (r5 == r7) goto L6f
            r7 = 46
            if (r5 == r7) goto L6f
            r7 = r11 & 4
            if (r7 == 0) goto L76
            r7 = 47
            if (r5 == r7) goto L6f
            if (r5 != r6) goto L76
        L6f:
            if (r4 == 0) goto Lbc
            char r5 = (char) r5
            r4.append(r5)
            goto Lbc
        L76:
            if (r4 != 0) goto L85
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r7 = r2 + 3
            r4.<init>(r7)
            r4.append(r1)
            r4.setLength(r0)
        L85:
            r7 = 256(0x100, float:3.59E-43)
            r8 = 37
            r9 = 4
            if (r5 >= r7) goto L9b
            r7 = 32
            r10 = 2
            if (r5 != r7) goto L97
            if (r11 != r10) goto L97
            r4.append(r6)
            goto Lbc
        L97:
            r4.append(r8)
            goto La4
        L9b:
            r4.append(r8)
            r6 = 117(0x75, float:1.64E-43)
            r4.append(r6)
            r10 = r9
        La4:
            int r10 = r10 - r3
            int r10 = r10 * r9
        La6:
            if (r10 < 0) goto Lbc
            int r6 = r5 >> r10
            r6 = r6 & 15
            r7 = 10
            if (r6 >= r7) goto Lb3
            int r6 = r6 + 48
            goto Lb5
        Lb3:
            int r6 = r6 + 55
        Lb5:
            char r6 = (char) r6
            r4.append(r6)
            int r10 = r10 + (-4)
            goto La6
        Lbc:
            int r0 = r0 + 1
            goto L2f
        Lc0:
            if (r4 != 0) goto Lc3
            return r1
        Lc3:
            java.lang.String r11 = r4.toString()
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeGlobal.js_escape(java.lang.Object[]):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0040  */
    private static Object js_unescape(Object[] objArr) {
        int i;
        String string = ScriptRuntime.toString(objArr, 0);
        int iIndexOf = string.indexOf(37);
        if (iIndexOf < 0) {
            return string;
        }
        int length = string.length();
        char[] charArray = string.toCharArray();
        int i2 = iIndexOf;
        while (iIndexOf != length) {
            char c = charArray[iIndexOf];
            int i3 = iIndexOf + 1;
            if (c != '%' || i3 == length) {
                iIndexOf = i3;
            } else {
                if (charArray[i3] == 'u') {
                    i = iIndexOf + 2;
                    iIndexOf += 6;
                } else {
                    iIndexOf += 3;
                    i = i3;
                }
                if (iIndexOf <= length) {
                    int iXDigitToInt = 0;
                    while (i != iIndexOf) {
                        iXDigitToInt = Kit.xDigitToInt(charArray[i], iXDigitToInt);
                        i++;
                    }
                    if (iXDigitToInt >= 0) {
                        c = (char) iXDigitToInt;
                    } else {
                        iIndexOf = i3;
                    }
                } else {
                    iIndexOf = i3;
                }
            }
            charArray[i2] = c;
            i2++;
        }
        return new String(charArray, 0, i2);
    }

    private static Object js_eval(Context context, Scriptable scriptable, Object[] objArr) {
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        return ScriptRuntime.evalSpecial(context, topLevelScope, topLevelScope, objArr, "eval code", 1);
    }

    static boolean isEvalFunction(Object obj) {
        if (!(obj instanceof IdFunctionObject)) {
            return false;
        }
        IdFunctionObject idFunctionObject = (IdFunctionObject) obj;
        return idFunctionObject.hasTag(FTAG) && idFunctionObject.methodId() == 6;
    }

    @Deprecated
    public static EcmaError constructError(Context context, String str, String str2, Scriptable scriptable) {
        return ScriptRuntime.constructError(str, str2);
    }

    @Deprecated
    public static EcmaError constructError(Context context, String str, String str2, Scriptable scriptable, String str3, int i, int i2, String str4) {
        return ScriptRuntime.constructError(str, str2, str3, i, str4, i2);
    }

    private static String encode(String str, boolean z) {
        int length = str.length();
        StringBuilder sb = null;
        byte[] bArr = null;
        int i = 0;
        while (i != length) {
            char cCharAt = str.charAt(i);
            if (!encodeUnescaped(cCharAt, z)) {
                if (sb == null) {
                    StringBuilder sb2 = new StringBuilder(length + 3);
                    sb2.append(str);
                    sb2.setLength(i);
                    bArr = new byte[6];
                    sb = sb2;
                }
                if (56320 <= cCharAt && cCharAt <= 57343) {
                    throw uriError();
                }
                int i2 = cCharAt;
                if (cCharAt >= 55296 && 56319 >= cCharAt) {
                    i++;
                    if (i == length) {
                        i2 = cCharAt;
                        throw uriError();
                    }
                    char cCharAt2 = str.charAt(i);
                    if (56320 > cCharAt2) {
                        i2 = cCharAt;
                    } else if (cCharAt2 <= 57343) {
                        i2 = ((cCharAt - GeneratorBase.SURR1_FIRST) << 10) + (cCharAt2 - 56320) + 65536;
                    }
                    throw uriError();
                }
                i2 = cCharAt;
                int iOneUcs4ToUtf8Char = oneUcs4ToUtf8Char(bArr, i2);
                for (int i3 = 0; i3 < iOneUcs4ToUtf8Char; i3++) {
                    byte b = bArr[i3];
                    sb.append('%');
                    sb.append(toHexChar((b & 255) >>> 4));
                    sb.append(toHexChar(b & Ascii.SI));
                }
            } else if (sb != null) {
                sb.append(cCharAt);
            }
            i++;
            sb = sb;
        }
        return sb == null ? str : sb.toString();
    }

    private static char toHexChar(int i) {
        if ((i >> 4) != 0) {
            Kit.codeBug();
        }
        return (char) (i < 10 ? i + 48 : i + 55);
    }

    private static int unHex(char c, char c2) {
        int iUnHex = unHex(c);
        int iUnHex2 = unHex(c2);
        if (iUnHex < 0 || iUnHex2 < 0) {
            return -1;
        }
        return (iUnHex << 4) | iUnHex2;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0040 A[PHI: r6 r8
      0x0040: PHI (r6v5 int) = (r6v1 int), (r6v2 int) binds: [B:16:0x003e, B:62:0x00d0] A[DONT_GENERATE, DONT_INLINE]
      0x0040: PHI (r8v22 int) = (r8v2 int), (r8v10 int) binds: [B:16:0x003e, B:62:0x00d0] A[DONT_GENERATE, DONT_INLINE]] */
    private static String decode(String str, boolean z) {
        int i;
        int i2;
        char c;
        int length = str.length();
        char[] cArr = null;
        int i3 = 0;
        int i4 = 0;
        while (i3 != length) {
            char cCharAt = str.charAt(i3);
            if (cCharAt != '%') {
                if (cArr != null) {
                    cArr[i4] = cCharAt;
                    i4++;
                }
                i3++;
            } else {
                if (cArr == null) {
                    cArr = new char[length];
                    str.getChars(0, i3, cArr, 0);
                    i4 = i3;
                }
                int i5 = i3 + 3;
                if (i5 > length) {
                    throw uriError();
                }
                int iUnHex = unHex(str.charAt(i3 + 1), str.charAt(i3 + 2));
                if (iUnHex < 0) {
                    throw uriError();
                }
                if ((iUnHex & 128) == 0) {
                    c = (char) iUnHex;
                } else {
                    if ((iUnHex & 192) == 128) {
                        throw uriError();
                    }
                    if ((iUnHex & 32) == 0) {
                        iUnHex &= 31;
                        i = 1;
                        i2 = 128;
                    } else if ((iUnHex & 16) == 0) {
                        iUnHex &= 15;
                        i = 2;
                        i2 = 2048;
                    } else if ((iUnHex & 8) == 0) {
                        iUnHex &= 7;
                        i = 3;
                        i2 = 65536;
                    } else if ((iUnHex & 4) == 0) {
                        iUnHex &= 3;
                        i = 4;
                        i2 = 2097152;
                    } else {
                        if ((iUnHex & 2) != 0) {
                            throw uriError();
                        }
                        iUnHex &= 1;
                        i = 5;
                        i2 = 67108864;
                    }
                    if ((i * 3) + i5 > length) {
                        throw uriError();
                    }
                    for (int i6 = 0; i6 != i; i6++) {
                        if (str.charAt(i5) != '%') {
                            throw uriError();
                        }
                        int iUnHex2 = unHex(str.charAt(i5 + 1), str.charAt(i5 + 2));
                        if (iUnHex2 < 0 || (iUnHex2 & 192) != 128) {
                            throw uriError();
                        }
                        iUnHex = (iUnHex << 6) | (iUnHex2 & 63);
                        i5 += 3;
                    }
                    if (iUnHex < i2 || (iUnHex >= 55296 && iUnHex <= 57343)) {
                        iUnHex = Integer.MAX_VALUE;
                    } else if (iUnHex == 65534 || iUnHex == 65535) {
                        iUnHex = 65533;
                    }
                    if (iUnHex >= 65536) {
                        int i7 = iUnHex - 65536;
                        if (i7 > 1048575) {
                            throw uriError();
                        }
                        char c2 = (char) ((i7 >>> 10) + GeneratorBase.SURR1_FIRST);
                        char c3 = (char) ((i7 & AnalyticsListener.EVENT_DRM_KEYS_LOADED) + 56320);
                        cArr[i4] = c2;
                        c = c3;
                        i4++;
                    } else {
                        c = (char) iUnHex;
                    }
                }
                if (!z || URI_DECODE_RESERVED.indexOf(c) < 0) {
                    cArr[i4] = c;
                    i4++;
                } else {
                    while (i3 != i5) {
                        cArr[i4] = str.charAt(i3);
                        i3++;
                        i4++;
                    }
                }
                i3 = i5;
            }
        }
        return cArr == null ? str : new String(cArr, 0, i4);
    }

    private static boolean encodeUnescaped(char c, boolean z) {
        if (('A' > c || c > 'Z') && (('a' > c || c > 'z') && (('0' > c || c > '9') && "-_.!~*'()".indexOf(c) < 0))) {
            return z && URI_DECODE_RESERVED.indexOf(c) >= 0;
        }
        return true;
    }

    private static EcmaError uriError() {
        return ScriptRuntime.constructError("URIError", ScriptRuntime.getMessageById("msg.bad.uri", new Object[0]));
    }

    private static int oneUcs4ToUtf8Char(byte[] bArr, int i) {
        if ((i & WorkInfo.STOP_REASON_FOREGROUND_SERVICE_TIMEOUT) == 0) {
            bArr[0] = (byte) i;
            return 1;
        }
        int i2 = i >>> 11;
        int i3 = 2;
        while (i2 != 0) {
            i2 >>>= 5;
            i3++;
        }
        int i4 = i3;
        while (true) {
            i4--;
            if (i4 > 0) {
                bArr[i4] = (byte) ((i & 63) | 128);
                i >>>= 6;
            } else {
                bArr[0] = (byte) ((256 - (1 << (8 - i3))) + i);
                return i3;
            }
        }
    }
}
