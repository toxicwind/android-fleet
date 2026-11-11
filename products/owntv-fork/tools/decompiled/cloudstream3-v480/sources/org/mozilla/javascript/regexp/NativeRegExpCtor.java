package org.mozilla.javascript.regexp;

import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.common.base.Ascii;
import kotlin.text.Typography;
import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.Undefined;

/* JADX INFO: loaded from: classes8.dex */
class NativeRegExpCtor extends BaseFunction {
    private static final int DOLLAR_ID_BASE = 12;
    private static final int Id_AMPERSAND = 6;
    private static final int Id_BACK_QUOTE = 10;
    private static final int Id_DOLLAR_1 = 13;
    private static final int Id_DOLLAR_2 = 14;
    private static final int Id_DOLLAR_3 = 15;
    private static final int Id_DOLLAR_4 = 16;
    private static final int Id_DOLLAR_5 = 17;
    private static final int Id_DOLLAR_6 = 18;
    private static final int Id_DOLLAR_7 = 19;
    private static final int Id_DOLLAR_8 = 20;
    private static final int Id_DOLLAR_9 = 21;
    private static final int Id_PLUS = 8;
    private static final int Id_QUOTE = 12;
    private static final int Id_STAR = 2;
    private static final int Id_UNDERSCORE = 4;
    private static final int Id_input = 3;
    private static final int Id_lastMatch = 5;
    private static final int Id_lastParen = 7;
    private static final int Id_leftContext = 9;
    private static final int Id_multiline = 1;
    private static final int Id_rightContext = 11;
    private static final int MAX_INSTANCE_ID = 21;
    private static final long serialVersionUID = -5733330028285400526L;
    private int multilineAttr = 4;
    private int starAttr = 4;
    private int inputAttr = 4;
    private int underscoreAttr = 4;

    @Override // org.mozilla.javascript.BaseFunction
    public int getArity() {
        return 2;
    }

    @Override // org.mozilla.javascript.BaseFunction
    public int getLength() {
        return 2;
    }

    NativeRegExpCtor() {
    }

    @Override // org.mozilla.javascript.BaseFunction
    public String getFunctionName() {
        return "RegExp";
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Callable
    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (objArr.length > 0 && (objArr[0] instanceof NativeRegExp) && (objArr.length == 1 || objArr[1] == Undefined.instance)) {
            return objArr[0];
        }
        return construct(context, scriptable, objArr);
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Constructable
    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        NativeRegExp nativeRegExpWithLanguageVersion = NativeRegExpInstantiator.withLanguageVersion(context.getLanguageVersion());
        nativeRegExpWithLanguageVersion.compile(context, scriptable, objArr);
        ScriptRuntime.setBuiltinProtoAndParent(nativeRegExpWithLanguageVersion, scriptable, TopLevel.Builtins.RegExp);
        return nativeRegExpWithLanguageVersion;
    }

    private static RegExpImpl getImpl() {
        return (RegExpImpl) ScriptRuntime.getRegExpProxy(Context.getCurrentContext());
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected int getMaxInstanceId() {
        return super.getMaxInstanceId() + 21;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected int findInstanceIdInfo(String str) {
        int i;
        int i2;
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -1304775693:
                if (str.equals("rightContext")) {
                    b = 0;
                }
                break;
            case -1206239059:
                if (str.equals("multiline")) {
                    b = 1;
                }
                break;
            case -223254616:
                if (str.equals("leftContext")) {
                    b = 2;
                }
                break;
            case 1154:
                if (str.equals("$&")) {
                    b = 3;
                }
                break;
            case 1155:
                if (str.equals("$'")) {
                    b = 4;
                }
                break;
            case 1158:
                if (str.equals("$*")) {
                    b = 5;
                }
                break;
            case 1159:
                if (str.equals("$+")) {
                    b = 6;
                }
                break;
            case 1165:
                if (str.equals("$1")) {
                    b = 7;
                }
                break;
            case 1166:
                if (str.equals("$2")) {
                    b = 8;
                }
                break;
            case 1167:
                if (str.equals("$3")) {
                    b = 9;
                }
                break;
            case 1168:
                if (str.equals("$4")) {
                    b = 10;
                }
                break;
            case 1169:
                if (str.equals("$5")) {
                    b = 11;
                }
                break;
            case 1170:
                if (str.equals("$6")) {
                    b = 12;
                }
                break;
            case 1171:
                if (str.equals("$7")) {
                    b = 13;
                }
                break;
            case 1172:
                if (str.equals("$8")) {
                    b = 14;
                }
                break;
            case 1173:
                if (str.equals("$9")) {
                    b = 15;
                }
                break;
            case 1211:
                if (str.equals("$_")) {
                    b = 16;
                }
                break;
            case 1212:
                if (str.equals("$`")) {
                    b = 17;
                }
                break;
            case 100358090:
                if (str.equals(TvContractCompat.PARAM_INPUT)) {
                    b = Ascii.DC2;
                }
                break;
            case 1996129487:
                if (str.equals("lastMatch")) {
                    b = 19;
                }
                break;
            case 1998898196:
                if (str.equals("lastParen")) {
                    b = Ascii.DC4;
                }
                break;
        }
        switch (b) {
            case 0:
                i = 11;
                break;
            case 1:
                i = 1;
                break;
            case 2:
                i = 9;
                break;
            case 3:
                i = 6;
                break;
            case 4:
                i = 12;
                break;
            case 5:
                i = 2;
                break;
            case 6:
                i = 8;
                break;
            case 7:
                i = 13;
                break;
            case 8:
                i = 14;
                break;
            case 9:
                i = 15;
                break;
            case 10:
                i = 16;
                break;
            case 11:
                i = 17;
                break;
            case 12:
                i = 18;
                break;
            case 13:
                i = 19;
                break;
            case 14:
                i = 20;
                break;
            case 15:
                i = 21;
                break;
            case 16:
                i = 4;
                break;
            case 17:
                i = 10;
                break;
            case 18:
                i = 3;
                break;
            case 19:
                i = 5;
                break;
            case 20:
                i = 7;
                break;
            default:
                i = 0;
                break;
        }
        if (i == 0) {
            return super.findInstanceIdInfo(str);
        }
        if (i == 1) {
            i2 = this.multilineAttr;
        } else if (i == 2) {
            i2 = this.starAttr;
        } else if (i == 3) {
            i2 = this.inputAttr;
        } else {
            i2 = i != 4 ? 5 : this.underscoreAttr;
        }
        return instanceIdInfo(i2, super.getMaxInstanceId() + i);
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected String getInstanceIdName(int i) {
        int maxInstanceId = i - super.getMaxInstanceId();
        if (1 <= maxInstanceId && maxInstanceId <= 21) {
            switch (maxInstanceId) {
                case 1:
                    return "multiline";
                case 2:
                    return "$*";
                case 3:
                    return TvContractCompat.PARAM_INPUT;
                case 4:
                    return "$_";
                case 5:
                    return "lastMatch";
                case 6:
                    return "$&";
                case 7:
                    return "lastParen";
                case 8:
                    return "$+";
                case 9:
                    return "leftContext";
                case 10:
                    return "$`";
                case 11:
                    return "rightContext";
                case 12:
                    return "$'";
                default:
                    return new String(new char[]{Typography.dollar, (char) (maxInstanceId + 36)});
            }
        }
        return super.getInstanceIdName(i);
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected Object getInstanceIdValue(int i) {
        Object parenSubString;
        int maxInstanceId = i - super.getMaxInstanceId();
        if (1 <= maxInstanceId && maxInstanceId <= 21) {
            RegExpImpl impl = getImpl();
            switch (maxInstanceId) {
                case 1:
                case 2:
                    return ScriptRuntime.wrapBoolean(impl.multiline);
                case 3:
                case 4:
                    parenSubString = impl.input;
                    break;
                case 5:
                case 6:
                    parenSubString = impl.lastMatch;
                    break;
                case 7:
                case 8:
                    parenSubString = impl.lastParen;
                    break;
                case 9:
                case 10:
                    parenSubString = impl.leftContext;
                    break;
                case 11:
                case 12:
                    parenSubString = impl.rightContext;
                    break;
                default:
                    parenSubString = impl.getParenSubString(maxInstanceId - 13);
                    break;
            }
            return parenSubString == null ? "" : parenSubString.toString();
        }
        return super.getInstanceIdValue(i);
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected void setInstanceIdValue(int i, Object obj) {
        int maxInstanceId = i - super.getMaxInstanceId();
        switch (maxInstanceId) {
            case 1:
            case 2:
                getImpl().multiline = ScriptRuntime.toBoolean(obj);
                break;
            case 3:
            case 4:
                getImpl().input = ScriptRuntime.toString(obj);
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                break;
            default:
                int i2 = maxInstanceId - 13;
                if (i2 < 0 || i2 > 8) {
                    super.setInstanceIdValue(i, obj);
                }
                break;
        }
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected void setInstanceIdAttributes(int i, int i2) {
        int maxInstanceId = i - super.getMaxInstanceId();
        switch (maxInstanceId) {
            case 1:
                this.multilineAttr = i2;
                break;
            case 2:
                this.starAttr = i2;
                break;
            case 3:
                this.inputAttr = i2;
                break;
            case 4:
                this.underscoreAttr = i2;
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                break;
            default:
                int i3 = maxInstanceId - 13;
                if (i3 < 0 || i3 > 8) {
                    super.setInstanceIdAttributes(i, i2);
                }
                break;
        }
    }
}
