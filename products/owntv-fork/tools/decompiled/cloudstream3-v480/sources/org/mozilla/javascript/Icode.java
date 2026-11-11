package org.mozilla.javascript;

/* JADX INFO: loaded from: classes8.dex */
abstract class Icode {
    static final int ICode_FN_STORE_HOME_OBJECT = -37;
    static final int Icode_CALLSPECIAL = -25;
    static final int Icode_CALLSPECIAL_OPTIONAL = -26;
    static final int Icode_CALL_ON_SUPER = -85;
    static final int Icode_CLOSURE_EXPR = -23;
    static final int Icode_CLOSURE_STMT = -24;
    static final int Icode_DEBUGGER = -71;
    static final int Icode_DELNAME = 0;
    static final int Icode_DELPROP_SUPER = -86;
    static final int Icode_DUP = -1;
    static final int Icode_DUP2 = -2;
    static final int Icode_ELEM_AND_THIS = -17;
    static final int Icode_ELEM_AND_THIS_OPTIONAL = -21;
    static final int Icode_ELEM_INC_DEC = -10;
    static final int Icode_ENTERDQ = -60;
    static final int Icode_GENERATOR = -69;
    static final int Icode_GENERATOR_END = -70;
    static final int Icode_GENERATOR_RETURN = -72;
    static final int Icode_GETVAR1 = -55;
    static final int Icode_GOSUB = -28;
    static final int Icode_IFEQ_POP = -6;
    static final int Icode_IF_NOT_NULL_UNDEF = -84;
    static final int Icode_IF_NULL_UNDEF = -83;
    static final int Icode_INTNUMBER = -33;
    static final int Icode_LEAVEDQ = -61;
    static final int Icode_LINE = -31;
    static final int Icode_LITERAL_GETTER = -64;
    static final int Icode_LITERAL_KEY_SET = -82;
    static final int Icode_LITERAL_NEW_ARRAY = -35;
    static final int Icode_LITERAL_NEW_OBJECT = -34;
    static final int Icode_LITERAL_SET = -36;
    static final int Icode_LITERAL_SETTER = -65;
    static final int Icode_LOCAL_CLEAR = -63;
    static final int Icode_NAME_AND_THIS = -15;
    static final int Icode_NAME_AND_THIS_OPTIONAL = -19;
    static final int Icode_NAME_INC_DEC = -8;
    static final int Icode_ONE = -59;
    static final int Icode_POP = -4;
    static final int Icode_POP_RESULT = -5;
    static final int Icode_PROP_AND_THIS = -16;
    static final int Icode_PROP_AND_THIS_OPTIONAL = -20;
    static final int Icode_PROP_INC_DEC = -9;
    static final int Icode_REF_INC_DEC = -11;
    static final int Icode_REG_BIGINT1 = -78;
    static final int Icode_REG_BIGINT2 = -79;
    static final int Icode_REG_BIGINT4 = -80;
    static final int Icode_REG_BIGINT_C0 = -74;
    static final int Icode_REG_BIGINT_C1 = -75;
    static final int Icode_REG_BIGINT_C2 = -76;
    static final int Icode_REG_BIGINT_C3 = -77;
    static final int Icode_REG_IND1 = -45;
    static final int Icode_REG_IND2 = -46;
    static final int Icode_REG_IND4 = -47;
    static final int Icode_REG_IND_C0 = -39;
    static final int Icode_REG_IND_C1 = -40;
    static final int Icode_REG_IND_C2 = -41;
    static final int Icode_REG_IND_C3 = -42;
    static final int Icode_REG_IND_C4 = -43;
    static final int Icode_REG_IND_C5 = -44;
    static final int Icode_REG_STR1 = -52;
    static final int Icode_REG_STR2 = -53;
    static final int Icode_REG_STR4 = -54;
    static final int Icode_REG_STR_C0 = -48;
    static final int Icode_REG_STR_C1 = -49;
    static final int Icode_REG_STR_C2 = -50;
    static final int Icode_REG_STR_C3 = -51;
    static final int Icode_RETSUB = -30;
    static final int Icode_RETUNDEF = -27;
    static final int Icode_SCOPE_LOAD = -12;
    static final int Icode_SCOPE_SAVE = -13;
    static final int Icode_SETCONST = -66;
    static final int Icode_SETCONSTVAR = -67;
    static final int Icode_SETCONSTVAR1 = -68;
    static final int Icode_SETVAR1 = -56;
    static final int Icode_SHORTNUMBER = -32;
    static final int Icode_SPARE_ARRAYLIT = -38;
    static final int Icode_STARTSUB = -29;
    static final int Icode_SWAP = -3;
    static final int Icode_TAIL_CALL = -62;
    static final int Icode_TEMPLATE_LITERAL_CALLSITE = -81;
    static final int Icode_TYPEOFNAME = -14;
    static final int Icode_UNDEF = -57;
    static final int Icode_VALUE_AND_THIS = -18;
    static final int Icode_VALUE_AND_THIS_OPTIONAL = -22;
    static final int Icode_VAR_INC_DEC = -7;
    static final int Icode_YIELD_STAR = -73;
    static final int Icode_ZERO = -58;
    static final int MIN_ICODE = -86;

    static boolean validIcode(int i) {
        return -86 <= i && i <= 0;
    }

    static boolean validTokenCode(int i) {
        return 2 <= i && i <= 89;
    }

    Icode() {
    }

    static String bytecodeName(int i) {
        if (!validBytecode(i)) {
            throw new IllegalArgumentException(String.valueOf(i));
        }
        return String.valueOf(i);
    }

    static boolean validBytecode(int i) {
        return validIcode(i) || validTokenCode(i);
    }
}
