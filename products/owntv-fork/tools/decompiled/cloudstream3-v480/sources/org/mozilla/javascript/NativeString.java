package org.mozilla.javascript;

import androidx.collection.SieveCacheKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.common.base.Ascii;
import dev.whyoleg.cryptography.serialization.asn1.internal.DerTagKt;
import io.ktor.http.LinkHeader;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes8.dex */
final class NativeString extends IdScriptableObject {
    private static final int ConstructorId_charAt = -5;
    private static final int ConstructorId_charCodeAt = -6;
    private static final int ConstructorId_concat = -14;
    private static final int ConstructorId_equalsIgnoreCase = -30;
    private static final int ConstructorId_fromCharCode = -1;
    private static final int ConstructorId_fromCodePoint = -2;
    private static final int ConstructorId_indexOf = -7;
    private static final int ConstructorId_lastIndexOf = -8;
    private static final int ConstructorId_localeCompare = -35;
    private static final int ConstructorId_match = -31;
    private static final int ConstructorId_raw = -3;
    private static final int ConstructorId_replace = -33;
    private static final int ConstructorId_replaceAll = -34;
    private static final int ConstructorId_search = -32;
    private static final int ConstructorId_slice = -15;
    private static final int ConstructorId_split = -9;
    private static final int ConstructorId_substr = -13;
    private static final int ConstructorId_substring = -10;
    private static final int ConstructorId_toLocaleLowerCase = -36;
    private static final int ConstructorId_toLowerCase = -11;
    private static final int ConstructorId_toUpperCase = -12;
    private static final int Id_anchor = 28;
    private static final int Id_at = 52;
    private static final int Id_big = 21;
    private static final int Id_blink = 22;
    private static final int Id_bold = 16;
    private static final int Id_charAt = 5;
    private static final int Id_charCodeAt = 6;
    private static final int Id_codePointAt = 46;
    private static final int Id_concat = 14;
    private static final int Id_constructor = 1;
    private static final int Id_endsWith = 43;
    private static final int Id_equals = 29;
    private static final int Id_equalsIgnoreCase = 30;
    private static final int Id_fixed = 18;
    private static final int Id_fontcolor = 26;
    private static final int Id_fontsize = 25;
    private static final int Id_includes = 41;
    private static final int Id_indexOf = 7;
    private static final int Id_isWellFormed = 53;
    private static final int Id_italics = 17;
    private static final int Id_lastIndexOf = 8;
    private static final int Id_length = 1;
    private static final int Id_link = 27;
    private static final int Id_localeCompare = 35;
    private static final int Id_match = 31;
    private static final int Id_matchAll = 55;
    private static final int Id_normalize = 44;
    private static final int Id_padEnd = 48;
    private static final int Id_padStart = 47;
    private static final int Id_repeat = 45;
    private static final int Id_replace = 33;
    private static final int Id_replaceAll = 34;
    private static final int Id_search = 32;
    private static final int Id_slice = 15;
    private static final int Id_small = 20;
    private static final int Id_split = 9;
    private static final int Id_startsWith = 42;
    private static final int Id_strike = 19;
    private static final int Id_sub = 24;
    private static final int Id_substr = 13;
    private static final int Id_substring = 10;
    private static final int Id_sup = 23;
    private static final int Id_toLocaleLowerCase = 36;
    private static final int Id_toLocaleUpperCase = 37;
    private static final int Id_toLowerCase = 11;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    private static final int Id_toUpperCase = 12;
    private static final int Id_toWellFormed = 54;
    private static final int Id_trim = 38;
    private static final int Id_trimEnd = 51;
    private static final int Id_trimLeft = 39;
    private static final int Id_trimRight = 40;
    private static final int Id_trimStart = 50;
    private static final int Id_valueOf = 4;
    private static final int MAX_INSTANCE_ID = 1;
    private static final int MAX_PROTOTYPE_ID = 55;
    private static final Object STRING_TAG = "String";
    private static final int SymbolId_iterator = 49;
    private static final long serialVersionUID = 920268368584188687L;
    private CharSequence string;

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int getMaxInstanceId() {
        return 1;
    }

    static void init(Scriptable scriptable, boolean z) {
        new NativeString("").exportAsJSClass(55, scriptable, z);
    }

    NativeString(CharSequence charSequence) {
        this.string = charSequence;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "String";
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findInstanceIdInfo(String str) {
        if (str.equals("length")) {
            return instanceIdInfo(7, 1);
        }
        return super.findInstanceIdInfo(str);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected String getInstanceIdName(int i) {
        if (i == 1) {
            return "length";
        }
        return super.getInstanceIdName(i);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected Object getInstanceIdValue(int i) {
        if (i == 1) {
            return ScriptRuntime.wrapInt(this.string.length());
        }
        return super.getInstanceIdValue(i);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        Object obj = STRING_TAG;
        addIdFunctionProperty(idFunctionObject, obj, -1, "fromCharCode", 1);
        addIdFunctionProperty(idFunctionObject, obj, -2, "fromCodePoint", 1);
        addIdFunctionProperty(idFunctionObject, obj, -3, "raw", 1);
        addIdFunctionProperty(idFunctionObject, obj, -5, "charAt", 2);
        addIdFunctionProperty(idFunctionObject, obj, -6, "charCodeAt", 2);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_indexOf, "indexOf", 2);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_lastIndexOf, "lastIndexOf", 2);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_split, "split", 3);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_substring, "substring", 3);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_toLowerCase, "toLowerCase", 1);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_toUpperCase, "toUpperCase", 1);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_substr, "substr", 3);
        addIdFunctionProperty(idFunctionObject, obj, -14, "concat", 2);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_slice, "slice", 3);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_equalsIgnoreCase, "equalsIgnoreCase", 2);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_match, "match", 2);
        addIdFunctionProperty(idFunctionObject, obj, -32, "search", 2);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_replace, "replace", 2);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_replaceAll, "replaceAll", 2);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_localeCompare, "localeCompare", 2);
        addIdFunctionProperty(idFunctionObject, obj, ConstructorId_toLocaleLowerCase, "toLocaleLowerCase", 1);
        super.fillConstructorProperties(idFunctionObject);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int i) {
        String str;
        String str2;
        String str3;
        int i2;
        String str4;
        if (i == 49) {
            initPrototypeMethod(STRING_TAG, i, SymbolKey.ITERATOR, "[Symbol.iterator]", 0);
            return;
        }
        switch (i) {
            case 1:
                str = "constructor";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 2:
                str2 = "toString";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 3:
                str2 = "toSource";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 4:
                str2 = "valueOf";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 5:
                str = "charAt";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 6:
                str = "charCodeAt";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 7:
                str = "indexOf";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 8:
                str = "lastIndexOf";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 9:
                str3 = "split";
                i2 = 2;
                str4 = str3;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 10:
                str3 = "substring";
                i2 = 2;
                str4 = str3;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 11:
                str2 = "toLowerCase";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 12:
                str2 = "toUpperCase";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 13:
                str3 = "substr";
                i2 = 2;
                str4 = str3;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 14:
                str = "concat";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 15:
                str3 = "slice";
                i2 = 2;
                str4 = str3;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 16:
                str2 = TtmlNode.BOLD;
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 17:
                str2 = "italics";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 18:
                str2 = "fixed";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 19:
                str2 = "strike";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 20:
                str2 = "small";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 21:
                str2 = "big";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 22:
                str2 = "blink";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 23:
                str2 = "sup";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 24:
                str2 = "sub";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 25:
                str2 = "fontsize";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 26:
                str2 = "fontcolor";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 27:
                str2 = "link";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 28:
                str2 = LinkHeader.Parameters.Anchor;
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 29:
                str = "equals";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 30:
                str = "equalsIgnoreCase";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 31:
                str = "match";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 32:
                str = "search";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 33:
                str3 = "replace";
                i2 = 2;
                str4 = str3;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 34:
                str3 = "replaceAll";
                i2 = 2;
                str4 = str3;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 35:
                str = "localeCompare";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 36:
                str2 = "toLocaleLowerCase";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 37:
                str2 = "toLocaleUpperCase";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 38:
                str2 = "trim";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 39:
                str2 = "trimLeft";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 40:
                str2 = "trimRight";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 41:
                str = "includes";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 42:
                str = "startsWith";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 43:
                str = "endsWith";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 44:
                str2 = "normalize";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 45:
                str = "repeat";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 46:
                str = "codePointAt";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 47:
                str = "padStart";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 48:
                str = "padEnd";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 49:
            default:
                throw new IllegalArgumentException(String.valueOf(i));
            case 50:
                str2 = "trimStart";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 51:
                str2 = "trimEnd";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 52:
                str = "at";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 53:
                str2 = "isWellFormed";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 54:
                str2 = "toWellFormed";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 55:
                str = "matchAll";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:101:0x01b9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:107:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:109:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:110:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:114:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:116:0x01e9 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:11:0x0020  */
    /* JADX WARN: Code duplicated, block: B:122:0x01f6  */
    /* JADX WARN: Code duplicated, block: B:124:0x0200  */
    /* JADX WARN: Code duplicated, block: B:126:0x0210  */
    /* JADX WARN: Code duplicated, block: B:133:0x0227  */
    /* JADX WARN: Code duplicated, block: B:135:0x022c  */
    /* JADX WARN: Code duplicated, block: B:137:0x022f  */
    /* JADX WARN: Code duplicated, block: B:13:0x0023  */
    /* JADX WARN: Code duplicated, block: B:14:0x0026  */
    /* JADX WARN: Code duplicated, block: B:158:0x029a  */
    /* JADX WARN: Code duplicated, block: B:160:0x02a5  */
    /* JADX WARN: Code duplicated, block: B:164:0x02b1  */
    /* JADX WARN: Code duplicated, block: B:166:0x02b9  */
    /* JADX WARN: Code duplicated, block: B:173:0x02e3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:175:0x02e6  */
    /* JADX WARN: Code duplicated, block: B:178:0x02ec  */
    /* JADX WARN: Code duplicated, block: B:17:0x0032  */
    /* JADX WARN: Code duplicated, block: B:180:0x02f0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:182:0x02f3  */
    /* JADX WARN: Code duplicated, block: B:185:0x02f9  */
    /* JADX WARN: Code duplicated, block: B:187:0x02fd A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:189:0x0300  */
    /* JADX WARN: Code duplicated, block: B:195:0x030b  */
    /* JADX WARN: Code duplicated, block: B:198:0x0311  */
    /* JADX WARN: Code duplicated, block: B:19:0x004a  */
    /* JADX WARN: Code duplicated, block: B:200:0x0320  */
    /* JADX WARN: Code duplicated, block: B:205:0x0332  */
    /* JADX WARN: Code duplicated, block: B:208:0x0341  */
    /* JADX WARN: Code duplicated, block: B:213:0x0352  */
    /* JADX WARN: Code duplicated, block: B:216:0x0361  */
    /* JADX WARN: Code duplicated, block: B:21:0x0051  */
    /* JADX WARN: Code duplicated, block: B:221:0x036f  */
    /* JADX WARN: Code duplicated, block: B:226:0x0381  */
    /* JADX WARN: Code duplicated, block: B:228:0x0390  */
    /* JADX WARN: Code duplicated, block: B:22:0x0054  */
    /* JADX WARN: Code duplicated, block: B:233:0x03a7  */
    /* JADX WARN: Code duplicated, block: B:235:0x03b6  */
    /* JADX WARN: Code duplicated, block: B:240:0x03cd  */
    /* JADX WARN: Code duplicated, block: B:242:0x03f1  */
    /* JADX WARN: Code duplicated, block: B:244:0x03f5 A[PHI: r13
      0x03f5: PHI (r13v6 int) = (r13v1 int), (r13v4 int) binds: [B:243:0x03f3, B:247:0x03fb] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:245:0x03f7  */
    /* JADX WARN: Code duplicated, block: B:247:0x03fb  */
    /* JADX WARN: Code duplicated, block: B:248:0x03fd  */
    /* JADX WARN: Code duplicated, block: B:250:0x0401  */
    /* JADX WARN: Code duplicated, block: B:251:0x0403  */
    /* JADX WARN: Code duplicated, block: B:254:0x0413  */
    /* JADX WARN: Code duplicated, block: B:256:0x041f  */
    /* JADX WARN: Code duplicated, block: B:257:0x0424  */
    /* JADX WARN: Code duplicated, block: B:25:0x005e  */
    /* JADX WARN: Code duplicated, block: B:260:0x042d  */
    /* JADX WARN: Code duplicated, block: B:262:0x0436  */
    /* JADX WARN: Code duplicated, block: B:264:0x043f  */
    /* JADX WARN: Code duplicated, block: B:266:0x0448  */
    /* JADX WARN: Code duplicated, block: B:268:0x0451  */
    /* JADX WARN: Code duplicated, block: B:270:0x045a  */
    /* JADX WARN: Code duplicated, block: B:272:0x0463  */
    /* JADX WARN: Code duplicated, block: B:274:0x046c  */
    /* JADX WARN: Code duplicated, block: B:276:0x0475  */
    /* JADX WARN: Code duplicated, block: B:278:0x047e  */
    /* JADX WARN: Code duplicated, block: B:280:0x0487  */
    /* JADX WARN: Code duplicated, block: B:282:0x0490  */
    /* JADX WARN: Code duplicated, block: B:284:0x0499  */
    /* JADX WARN: Code duplicated, block: B:286:0x04a2  */
    /* JADX WARN: Code duplicated, block: B:288:0x04af  */
    /* JADX WARN: Code duplicated, block: B:290:0x04bc  */
    /* JADX WARN: Code duplicated, block: B:292:0x04c9  */
    /* JADX WARN: Code duplicated, block: B:294:0x04d8  */
    /* JADX WARN: Code duplicated, block: B:296:0x04e7  */
    /* JADX WARN: Code duplicated, block: B:298:0x04f4  */
    /* JADX WARN: Code duplicated, block: B:29:0x0068  */
    /* JADX WARN: Code duplicated, block: B:300:0x0505  */
    /* JADX WARN: Code duplicated, block: B:302:0x0516  */
    /* JADX WARN: Code duplicated, block: B:304:0x0528  */
    /* JADX WARN: Code duplicated, block: B:306:0x0539  */
    /* JADX WARN: Code duplicated, block: B:316:0x0556 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:317:0x0557  */
    /* JADX WARN: Code duplicated, block: B:319:0x055a  */
    /* JADX WARN: Code duplicated, block: B:321:0x057c  */
    /* JADX WARN: Code duplicated, block: B:323:0x0586 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:324:0x0587  */
    /* JADX WARN: Code duplicated, block: B:326:0x058c  */
    /* JADX WARN: Code duplicated, block: B:329:0x0590  */
    /* JADX WARN: Code duplicated, block: B:331:0x0598 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:333:0x05a1  */
    /* JADX WARN: Code duplicated, block: B:335:0x05a9  */
    /* JADX WARN: Code duplicated, block: B:337:0x05af  */
    /* JADX WARN: Code duplicated, block: B:339:0x05b3 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:340:0x05b4  */
    /* JADX WARN: Code duplicated, block: B:34:0x0085  */
    /* JADX WARN: Code duplicated, block: B:374:0x0029 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:381:0x0144 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:385:0x01b2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:386:0x01bb A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:388:0x01a3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:38:0x0096  */
    /* JADX WARN: Code duplicated, block: B:391:0x01c0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:42:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:44:0x00af  */
    /* JADX WARN: Code duplicated, block: B:49:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:52:0x00db  */
    /* JADX WARN: Code duplicated, block: B:54:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:56:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:58:0x0107  */
    /* JADX WARN: Code duplicated, block: B:60:0x0111  */
    /* JADX WARN: Code duplicated, block: B:63:0x012c  */
    /* JADX WARN: Code duplicated, block: B:65:0x0132  */
    /* JADX WARN: Code duplicated, block: B:67:0x0138  */
    /* JADX WARN: Code duplicated, block: B:69:0x0143  */
    /* JADX WARN: Code duplicated, block: B:73:0x014e  */
    /* JADX WARN: Code duplicated, block: B:75:0x0153  */
    /* JADX WARN: Code duplicated, block: B:77:0x015e  */
    /* JADX WARN: Code duplicated, block: B:79:0x016e  */
    /* JADX WARN: Code duplicated, block: B:83:0x017c  */
    /* JADX WARN: Code duplicated, block: B:87:0x0187  */
    /* JADX WARN: Code duplicated, block: B:89:0x0197  */
    /* JADX WARN: Code duplicated, block: B:91:0x01a1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:94:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:95:0x01aa  */
    /* JADX WARN: Code duplicated, block: B:97:0x01b0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:9:0x0019  */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public java.lang.Object execIdCall(org.mozilla.javascript.IdFunctionObject r10, org.mozilla.javascript.Context r11, org.mozilla.javascript.Scriptable r12, org.mozilla.javascript.Scriptable r13, java.lang.Object[] r14) {
        /*
            Method dump skipped, instruction units count: 1756
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeString.execIdCall(org.mozilla.javascript.IdFunctionObject, org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, org.mozilla.javascript.Scriptable, java.lang.Object[]):java.lang.Object");
    }

    private static NativeString realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        return (NativeString) ensureType(scriptable, NativeString.class, idFunctionObject);
    }

    private static String tagify(Context context, Scriptable scriptable, IdFunctionObject idFunctionObject, String str, String str2, Object[] objArr) {
        String string = ScriptRuntime.toString(ScriptRuntimeES6.requireObjectCoercible(context, scriptable, idFunctionObject));
        StringBuilder sb = new StringBuilder("<");
        sb.append(str);
        if (str2 != null && str2.length() > 0) {
            String strReplace = ScriptRuntime.toString(objArr, 0).replace("\"", "&quot;");
            sb.append(' ');
            sb.append(str2);
            sb.append("=\"");
            sb.append(strReplace);
            sb.append('\"');
        }
        sb.append(Typography.greater);
        sb.append(string);
        sb.append("</");
        sb.append(str);
        sb.append(Typography.greater);
        return sb.toString();
    }

    public CharSequence toCharSequence() {
        return this.string;
    }

    public String toString() {
        CharSequence charSequence = this.string;
        return charSequence instanceof String ? (String) charSequence : charSequence.toString();
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object get(int i, Scriptable scriptable) {
        if (i >= 0 && i < this.string.length()) {
            return String.valueOf(this.string.charAt(i));
        }
        return super.get(i, scriptable);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(int i, Scriptable scriptable, Object obj) {
        if (i < 0 || i >= this.string.length()) {
            super.put(i, scriptable, obj);
        }
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean has(int i, Scriptable scriptable) {
        if (i < 0 || i >= this.string.length()) {
            return super.has(i, scriptable);
        }
        return true;
    }

    @Override // org.mozilla.javascript.ScriptableObject
    public int getAttributes(int i) {
        if (i < 0 || i >= this.string.length()) {
            return super.getAttributes(i);
        }
        return Context.getContext().getLanguageVersion() < 200 ? 7 : 5;
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject
    protected Object[] getIds(boolean z, boolean z2) {
        Context currentContext = Context.getCurrentContext();
        if (currentContext != null && currentContext.getLanguageVersion() >= 200) {
            Object[] ids = super.getIds(z, z2);
            Object[] objArr = new Object[ids.length + this.string.length()];
            int i = 0;
            while (i < this.string.length()) {
                objArr[i] = Integer.valueOf(i);
                i++;
            }
            System.arraycopy(ids, 0, objArr, i, ids.length);
            return objArr;
        }
        return super.getIds(z, z2);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject
    protected ScriptableObject getOwnPropertyDescriptor(Context context, Object obj) {
        if (!(obj instanceof Symbol) && context != null && context.getLanguageVersion() >= 200) {
            ScriptRuntime.StringIdOrIndex stringIdOrIndex = ScriptRuntime.toStringIdOrIndex(obj);
            if (stringIdOrIndex.stringId == null && stringIdOrIndex.index >= 0 && stringIdOrIndex.index < this.string.length()) {
                return defaultIndexPropertyDescriptor(String.valueOf(this.string.charAt(stringIdOrIndex.index)));
            }
        }
        return super.getOwnPropertyDescriptor(context, obj);
    }

    private ScriptableObject defaultIndexPropertyDescriptor(Object obj) {
        Scriptable parentScope = getParentScope();
        if (parentScope != null) {
            this = parentScope;
        }
        NativeObject nativeObject = new NativeObject();
        ScriptRuntime.setBuiltinProtoAndParent(nativeObject, this, TopLevel.Builtins.Object);
        nativeObject.defineProperty(ES6Iterator.VALUE_PROPERTY, obj, 0);
        nativeObject.defineProperty("writable", Boolean.FALSE, 0);
        nativeObject.defineProperty("enumerable", Boolean.TRUE, 0);
        nativeObject.defineProperty("configurable", Boolean.FALSE, 0);
        return nativeObject;
    }

    private static int js_indexOf(int i, String str, Object[] objArr) {
        int length;
        String string = ScriptRuntime.toString(objArr, 0);
        double integer = ScriptRuntime.toInteger(objArr, 1);
        if (i != 42 && i != 43 && string.length() == 0) {
            return integer > ((double) str.length()) ? str.length() : (int) integer;
        }
        if (i != 42 && i != 43 && integer > str.length()) {
            return -1;
        }
        if (integer < 0.0d) {
            integer = 0.0d;
        } else {
            if (integer > str.length()) {
                length = str.length();
            } else if (i == 43 && (Double.isNaN(integer) || integer > str.length())) {
                length = str.length();
            }
            integer = length;
        }
        if (43 != i) {
            if (i == 42) {
                return str.startsWith(string, (int) integer) ? 0 : -1;
            }
            return str.indexOf(string, (int) integer);
        }
        if (objArr.length == 0 || objArr.length == 1 || (objArr.length == 2 && objArr[1] == Undefined.instance)) {
            integer = str.length();
        }
        return str.substring(0, (int) integer).endsWith(string) ? 0 : -1;
    }

    private static int js_lastIndexOf(String str, Object[] objArr) {
        String string = ScriptRuntime.toString(objArr, 0);
        double number = ScriptRuntime.toNumber(objArr, 1);
        if (Double.isNaN(number) || number > str.length()) {
            number = str.length();
        } else if (number < 0.0d) {
            number = 0.0d;
        }
        return str.lastIndexOf(string, (int) number);
    }

    private static CharSequence js_substring(Context context, CharSequence charSequence, Object[] objArr) {
        int length = charSequence.length();
        double integer = ScriptRuntime.toInteger(objArr, 0);
        double d = 0.0d;
        if (integer < 0.0d) {
            integer = 0.0d;
        } else {
            double d2 = length;
            if (integer > d2) {
                integer = d2;
            }
        }
        if (objArr.length <= 1 || objArr[1] == Undefined.instance) {
            d = length;
        } else {
            double integer2 = ScriptRuntime.toInteger(objArr[1]);
            if (integer2 >= 0.0d) {
                d = length;
                if (integer2 <= d) {
                    d = integer2;
                }
            }
            if (d < integer) {
                if (context.getLanguageVersion() != 120) {
                    double d3 = d;
                    d = integer;
                    integer = d3;
                } else {
                    d = integer;
                }
            }
        }
        return charSequence.subSequence((int) integer, (int) d);
    }

    int getLength() {
        return this.string.length();
    }

    private static CharSequence js_substr(CharSequence charSequence, Object[] objArr) {
        if (objArr.length < 1) {
            return charSequence;
        }
        double integer = ScriptRuntime.toInteger(objArr[0]);
        int length = charSequence.length();
        if (integer < 0.0d) {
            integer += (double) length;
            if (integer < 0.0d) {
                integer = 0.0d;
            }
        } else {
            double d = length;
            if (integer > d) {
                integer = d;
            }
        }
        double d2 = length;
        if (objArr.length > 1) {
            Object obj = objArr[1];
            if (!Undefined.isUndefined(obj)) {
                double integer2 = ScriptRuntime.toInteger(obj);
                double d3 = (integer2 >= 0.0d ? integer2 : 0.0d) + integer;
                if (d3 <= d2) {
                    d2 = d3;
                }
            }
        }
        return charSequence.subSequence((int) integer, (int) d2);
    }

    private static String js_concat(String str, Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return str;
        }
        if (length == 1) {
            return str.concat(ScriptRuntime.toString(objArr[0]));
        }
        int length2 = str.length();
        String[] strArr = new String[length];
        for (int i = 0; i != length; i++) {
            String string = ScriptRuntime.toString(objArr[i]);
            strArr[i] = string;
            length2 += string.length();
        }
        StringBuilder sb = new StringBuilder(length2);
        sb.append(str);
        for (int i2 = 0; i2 != length; i2++) {
            sb.append(strArr[i2]);
        }
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0047 A[PHI: r6
      0x0047: PHI (r6v5 double) = (r6v4 double), (r6v6 double) binds: [B:25:0x0044, B:22:0x003e] A[DONT_GENERATE, DONT_INLINE]] */
    private static CharSequence js_slice(CharSequence charSequence, Object[] objArr) {
        double d = 0.0d;
        double integer = objArr.length < 1 ? 0.0d : ScriptRuntime.toInteger(objArr[0]);
        int length = charSequence.length();
        if (integer < 0.0d) {
            integer += (double) length;
            if (integer < 0.0d) {
                integer = 0.0d;
            }
        } else {
            double d2 = length;
            if (integer > d2) {
                integer = d2;
            }
        }
        if (objArr.length < 2 || objArr[1] == Undefined.instance) {
            d = length;
        } else {
            double integer2 = ScriptRuntime.toInteger(objArr[1]);
            if (integer2 < 0.0d) {
                integer2 += (double) length;
                if (integer2 >= 0.0d) {
                    d = integer2;
                }
            } else {
                d = length;
                if (integer2 <= d) {
                    d = integer2;
                }
            }
            if (d < integer) {
                d = integer;
            }
        }
        return charSequence.subSequence((int) integer, (int) d);
    }

    private static String js_repeat(Context context, Scriptable scriptable, IdFunctionObject idFunctionObject, Object[] objArr) {
        String string = ScriptRuntime.toString(ScriptRuntimeES6.requireObjectCoercible(context, scriptable, idFunctionObject));
        double integer = ScriptRuntime.toInteger(objArr, 0);
        if (integer < 0.0d || integer == Double.POSITIVE_INFINITY) {
            throw ScriptRuntime.rangeError("Invalid count value");
        }
        if (integer == 0.0d || string.length() == 0) {
            return "";
        }
        long length = ((long) string.length()) * ((long) integer);
        if (integer > 2.147483647E9d || length > SieveCacheKt.NodeLinkMask) {
            throw ScriptRuntime.rangeError("Invalid size or count value");
        }
        StringBuilder sb = new StringBuilder((int) length);
        sb.append(string);
        int i = (int) integer;
        int i2 = 1;
        while (i2 <= i / 2) {
            sb.append((CharSequence) sb);
            i2 *= 2;
        }
        if (i2 < i) {
            sb.append(sb.substring(0, string.length() * (i - i2)));
        }
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002e, code lost:
    
        if (r7.length() < 1) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String js_pad(org.mozilla.javascript.Context r4, org.mozilla.javascript.Scriptable r5, org.mozilla.javascript.IdFunctionObject r6, java.lang.Object[] r7, boolean r8) {
        /*
            java.lang.Object r4 = org.mozilla.javascript.ScriptRuntimeES6.requireObjectCoercible(r4, r5, r6)
            java.lang.String r4 = org.mozilla.javascript.ScriptRuntime.toString(r4)
            r5 = 0
            long r0 = org.mozilla.javascript.ScriptRuntime.toLength(r7, r5)
            int r6 = r4.length()
            long r2 = (long) r6
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 > 0) goto L17
            goto L30
        L17:
            int r6 = r7.length
            r2 = 2
            if (r6 < r2) goto L31
            r6 = 1
            r2 = r7[r6]
            boolean r2 = org.mozilla.javascript.Undefined.isUndefined(r2)
            if (r2 != 0) goto L31
            r7 = r7[r6]
            java.lang.String r7 = org.mozilla.javascript.ScriptRuntime.toString(r7)
            int r2 = r7.length()
            if (r2 >= r6) goto L33
        L30:
            return r4
        L31:
            java.lang.String r7 = " "
        L33:
            int r6 = r4.length()
            long r2 = (long) r6
            long r0 = r0 - r2
            int r6 = (int) r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
        L3f:
            r0.append(r7)
            int r1 = r0.length()
            if (r1 < r6) goto L3f
            r0.setLength(r6)
            if (r8 == 0) goto L55
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            return r4
        L55:
            java.lang.StringBuilder r4 = r0.insert(r5, r4)
            java.lang.String r4 = r4.toString()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeString.js_pad(org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, org.mozilla.javascript.IdFunctionObject, java.lang.Object[], boolean):java.lang.String");
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(Symbol symbol) {
        return SymbolKey.ITERATOR.equals(symbol) ? 49 : 0;
    }

    private static CharSequence js_raw(Context context, Scriptable scriptable, Object[] objArr) {
        int i = 0;
        Scriptable object = ScriptRuntime.toObject(context, scriptable, ScriptRuntime.getObjectProp(ScriptRuntime.toObject(context, scriptable, objArr.length > 0 ? objArr[0] : Undefined.instance), "raw", context));
        long lengthProperty = NativeArray.getLengthProperty(context, object);
        if (lengthProperty > SieveCacheKt.NodeLinkMask) {
            throw ScriptRuntime.rangeError("raw.length > 2147483647");
        }
        int i2 = (int) lengthProperty;
        if (i2 <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            sb.append(ScriptRuntime.toString(ScriptRuntime.getObjectIndex(object, i, context)));
            i++;
            if (i == i2) {
                return sb;
            }
            if (objArr.length > i) {
                sb.append(ScriptRuntime.toString(objArr[i]));
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -1781441930:
                if (str.equals("toSource")) {
                    b = 0;
                }
                break;
            case -1776922004:
                if (str.equals("toString")) {
                    b = 1;
                }
                break;
            case -1588406278:
                if (str.equals("constructor")) {
                    b = 2;
                }
                break;
            case -1555538761:
                if (str.equals("startsWith")) {
                    b = 3;
                }
                break;
            case -1536328588:
                if (str.equals("fontcolor")) {
                    b = 4;
                }
                break;
            case -1464939364:
                if (str.equals("toLocaleLowerCase")) {
                    b = 5;
                }
                break;
            case -1413299531:
                if (str.equals(LinkHeader.Parameters.Anchor)) {
                    b = 6;
                }
                break;
            case -1361633751:
                if (str.equals("charAt")) {
                    b = 7;
                }
                break;
            case -1354795244:
                if (str.equals("concat")) {
                    b = 8;
                }
                break;
            case -1305276618:
                if (str.equals("codePointAt")) {
                    b = 9;
                }
                break;
            case -1295482945:
                if (str.equals("equals")) {
                    b = 10;
                }
                break;
            case -1233067443:
                if (str.equals("replaceAll")) {
                    b = 11;
                }
                break;
            case -1137582698:
                if (str.equals("toLowerCase")) {
                    b = 12;
                }
                break;
            case -1059745447:
                if (str.equals("trimEnd")) {
                    b = 13;
                }
                break;
            case -995871928:
                if (str.equals("padEnd")) {
                    b = 14;
                }
                break;
            case -934531685:
                if (str.equals("repeat")) {
                    b = 15;
                }
                break;
            case -906336856:
                if (str.equals("search")) {
                    b = 16;
                }
                break;
            case -891985998:
                if (str.equals("strike")) {
                    b = 17;
                }
                break;
            case -891529231:
                if (str.equals("substr")) {
                    b = 18;
                }
                break;
            case -726908483:
                if (str.equals("toLocaleUpperCase")) {
                    b = 19;
                }
                break;
            case -496262374:
                if (str.equals("trimRight")) {
                    b = 20;
                }
                break;
            case -495016608:
                if (str.equals("trimStart")) {
                    b = 21;
                }
                break;
            case -467511597:
                if (str.equals("lastIndexOf")) {
                    b = Ascii.SYN;
                }
                break;
            case -399551817:
                if (str.equals("toUpperCase")) {
                    b = Ascii.ETB;
                }
                break;
            case 3123:
                if (str.equals("at")) {
                    b = Ascii.CAN;
                }
                break;
            case 97536:
                if (str.equals("big")) {
                    b = Ascii.EM;
                }
                break;
            case 114240:
                if (str.equals("sub")) {
                    b = Ascii.SUB;
                }
                break;
            case 114254:
                if (str.equals("sup")) {
                    b = Ascii.ESC;
                }
                break;
            case 3029637:
                if (str.equals(TtmlNode.BOLD)) {
                    b = Ascii.FS;
                }
                break;
            case 3321850:
                if (str.equals("link")) {
                    b = Ascii.GS;
                }
                break;
            case 3568674:
                if (str.equals("trim")) {
                    b = Ascii.RS;
                }
                break;
            case 90259659:
                if (str.equals("includes")) {
                    b = Ascii.US;
                }
                break;
            case 93826908:
                if (str.equals("blink")) {
                    b = 32;
                }
                break;
            case 97445748:
                if (str.equals("fixed")) {
                    b = 33;
                }
                break;
            case 103668165:
                if (str.equals("match")) {
                    b = 34;
                }
                break;
            case 109526418:
                if (str.equals("slice")) {
                    b = 35;
                }
                break;
            case 109548807:
                if (str.equals("small")) {
                    b = 36;
                }
                break;
            case 109648666:
                if (str.equals("split")) {
                    b = 37;
                }
                break;
            case 231605032:
                if (str.equals("valueOf")) {
                    b = 38;
                }
                break;
            case 236609293:
                if (str.equals("normalize")) {
                    b = 39;
                }
                break;
            case 257797441:
                if (str.equals("equalsIgnoreCase")) {
                    b = 40;
                }
                break;
            case 296883612:
                if (str.equals("matchAll")) {
                    b = 41;
                }
                break;
            case 366554320:
                if (str.equals("fontsize")) {
                    b = 42;
                }
                break;
            case 397153782:
                if (str.equals("charCodeAt")) {
                    b = 43;
                }
                break;
            case 530542161:
                if (str.equals("substring")) {
                    b = 44;
                }
                break;
            case 757893007:
                if (str.equals("padStart")) {
                    b = 45;
                }
                break;
            case 812855195:
                if (str.equals("isWellFormed")) {
                    b = 46;
                }
                break;
            case 830635948:
                if (str.equals("toWellFormed")) {
                    b = 47;
                }
                break;
            case 1042795819:
                if (str.equals("localeCompare")) {
                    b = DerTagKt.DerTag_SEQUENCE;
                }
                break;
            case 1094496948:
                if (str.equals("replace")) {
                    b = 49;
                }
                break;
            case 1507829577:
                if (str.equals("trimLeft")) {
                    b = 50;
                }
                break;
            case 1743158238:
                if (str.equals("endsWith")) {
                    b = 51;
                }
                break;
            case 1943291465:
                if (str.equals("indexOf")) {
                    b = 52;
                }
                break;
            case 2112490563:
                if (str.equals("italics")) {
                    b = 53;
                }
                break;
        }
        switch (b) {
            case 0:
                return 3;
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 42;
            case 4:
                return 26;
            case 5:
                return 36;
            case 6:
                return 28;
            case 7:
                return 5;
            case 8:
                return 14;
            case 9:
                return 46;
            case 10:
                return 29;
            case 11:
                return 34;
            case 12:
                return 11;
            case 13:
                return 51;
            case 14:
                return 48;
            case 15:
                return 45;
            case 16:
                return 32;
            case 17:
                return 19;
            case 18:
                return 13;
            case 19:
                return 37;
            case 20:
                return 40;
            case 21:
                return 50;
            case 22:
                return 8;
            case 23:
                return 12;
            case 24:
                return 52;
            case 25:
                return 21;
            case 26:
                return 24;
            case 27:
                return 23;
            case 28:
                return 16;
            case 29:
                return 27;
            case 30:
                return 38;
            case 31:
                return 41;
            case 32:
                return 22;
            case 33:
                return 18;
            case 34:
                return 31;
            case 35:
                return 15;
            case 36:
                return 20;
            case 37:
                return 9;
            case 38:
                return 4;
            case 39:
                return 44;
            case 40:
                return 30;
            case 41:
                return 55;
            case 42:
                return 25;
            case 43:
                return 6;
            case 44:
                return 10;
            case 45:
                return 47;
            case 46:
                return 53;
            case 47:
                return 54;
            case 48:
                return 35;
            case 49:
                return 33;
            case 50:
                return 39;
            case 51:
                return 43;
            case 52:
                return 7;
            case 53:
                return 17;
            default:
                return 0;
        }
    }
}
