package org.mozilla.javascript;

import java.math.BigInteger;
import java.util.Arrays;

/* JADX INFO: loaded from: classes8.dex */
final class NativeBigInt extends IdScriptableObject {
    private static final Object BIG_INT_TAG = "BigInt";
    private static final int ConstructorId_asIntN = -1;
    private static final int ConstructorId_asUintN = -2;
    private static final int Id_constructor = 1;
    private static final int Id_toLocaleString = 3;
    private static final int Id_toSource = 4;
    private static final int Id_toString = 2;
    private static final int Id_valueOf = 5;
    private static final int MAX_PROTOTYPE_ID = 6;
    private static final int SymbolId_toStringTag = 6;
    private static final long serialVersionUID = 1335609231306775449L;
    private BigInteger bigIntValue;

    static void init(Scriptable scriptable, boolean z) {
        new NativeBigInt(BigInteger.ZERO).exportAsJSClass(6, scriptable, z);
    }

    NativeBigInt(BigInteger bigInteger) {
        this.bigIntValue = bigInteger;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "BigInt";
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        Object obj = BIG_INT_TAG;
        addIdFunctionProperty(idFunctionObject, obj, -1, "asIntN", 2);
        addIdFunctionProperty(idFunctionObject, obj, -2, "asUintN", 2);
        super.fillConstructorProperties(idFunctionObject);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int i) {
        String str;
        if (i == 6) {
            initPrototypeValue(6, SymbolKey.TO_STRING_TAG, getClassName(), 3);
            return;
        }
        int i2 = 1;
        if (i != 1) {
            if (i == 2) {
                str = "toString";
            } else if (i == 3) {
                str = "toLocaleString";
            } else if (i == 4) {
                str = "toSource";
            } else if (i == 5) {
                str = "valueOf";
            } else {
                throw new IllegalArgumentException(String.valueOf(i));
            }
            i2 = 0;
        } else {
            str = "constructor";
        }
        initPrototypeMethod(BIG_INT_TAG, i, str, i2);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Object obj = BIG_INT_TAG;
        if (!idFunctionObject.hasTag(obj)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int iMethodId = idFunctionObject.methodId();
        if (iMethodId == 1) {
            if (scriptable2 != null) {
                return objArr.length >= 1 ? ScriptRuntime.toBigInt(objArr[0]) : BigInteger.ZERO;
            }
            throw ScriptRuntime.typeErrorById("msg.not.ctor", obj);
        }
        if (iMethodId < 1) {
            return execConstructorCall(iMethodId, objArr);
        }
        BigInteger bigInteger = ((NativeBigInt) ensureType(scriptable2, NativeBigInt.class, idFunctionObject)).bigIntValue;
        if (iMethodId == 2 || iMethodId == 3) {
            return ScriptRuntime.bigIntToString(bigInteger, (objArr.length == 0 || objArr[0] == Undefined.instance) ? 10 : ScriptRuntime.toInt32(objArr[0]));
        }
        if (iMethodId != 4) {
            if (iMethodId == 5) {
                return bigInteger;
            }
            throw new IllegalArgumentException(String.valueOf(iMethodId));
        }
        return "(new BigInt(" + ScriptRuntime.toString(bigInteger) + "))";
    }

    private static Object execConstructorCall(int i, Object[] objArr) {
        if (i == -2 || i == -1) {
            int index = ScriptRuntime.toIndex(objArr.length < 1 ? Undefined.instance : objArr[0]);
            BigInteger bigInt = ScriptRuntime.toBigInt(objArr.length < 2 ? Undefined.instance : objArr[1]);
            if (index == 0) {
                return BigInteger.ZERO;
            }
            byte[] byteArray = bigInt.toByteArray();
            int i2 = (index / 8) + 1;
            if (i2 > byteArray.length) {
                return bigInt;
            }
            byte[] bArrCopyOfRange = Arrays.copyOfRange(byteArray, byteArray.length - i2, byteArray.length);
            int i3 = index % 8;
            if (i == -2) {
                bArrCopyOfRange[0] = (byte) (bArrCopyOfRange[0] & ((1 << i3) - 1));
            } else if (i == -1) {
                if (i3 == 0) {
                    bArrCopyOfRange[0] = bArrCopyOfRange[1] >= 0 ? (byte) 0 : (byte) -1;
                } else {
                    byte b = bArrCopyOfRange[0];
                    if (((1 << (i3 - 1)) & b) != 0) {
                        bArrCopyOfRange[0] = (byte) (b | ((-1) << i3));
                    } else {
                        bArrCopyOfRange[0] = (byte) (b & ((1 << i3) - 1));
                    }
                }
            }
            return new BigInteger(bArrCopyOfRange);
        }
        throw new IllegalArgumentException(String.valueOf(i));
    }

    public String toString() {
        return ScriptRuntime.bigIntToString(this.bigIntValue, 10);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(Symbol symbol) {
        return SymbolKey.TO_STRING_TAG.equals(symbol) ? 6 : 0;
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        str.hashCode();
        switch (str) {
            case "toLocaleString":
                return 3;
            case "toSource":
                return 4;
            case "toString":
                return 2;
            case "constructor":
                return 1;
            case "valueOf":
                return 5;
            default:
                return 0;
        }
    }
}
