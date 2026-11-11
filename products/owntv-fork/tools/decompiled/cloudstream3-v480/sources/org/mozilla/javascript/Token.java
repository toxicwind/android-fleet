package org.mozilla.javascript;

import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import coil3.network.internal.UtilsKt;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.android.gms.cast.MediaError;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes8.dex */
public class Token {
    public static final int ADD = 21;
    public static final int AND = 118;
    public static final int ARRAYCOMP = 171;
    public static final int ARRAYLIT = 71;
    public static final int ARROW = 178;
    public static final int ASSIGN = 99;
    public static final int ASSIGN_ADD = 108;
    public static final int ASSIGN_BITAND = 103;
    public static final int ASSIGN_BITOR = 100;
    public static final int ASSIGN_BITXOR = 102;
    public static final int ASSIGN_DIV = 111;
    public static final int ASSIGN_EXP = 113;
    public static final int ASSIGN_LOGICAL_AND = 104;
    public static final int ASSIGN_LOGICAL_OR = 101;
    public static final int ASSIGN_LSH = 105;
    public static final int ASSIGN_MOD = 112;
    public static final int ASSIGN_MUL = 110;
    public static final int ASSIGN_NULLISH = 114;
    public static final int ASSIGN_RSH = 106;
    public static final int ASSIGN_SUB = 109;
    public static final int ASSIGN_URSH = 107;
    public static final int BIGINT = 89;
    public static final int BINDNAME = 54;
    public static final int BITAND = 11;
    public static final int BITNOT = 27;
    public static final int BITOR = 9;
    public static final int BITXOR = 10;
    public static final int BLOCK = 143;
    public static final int BREAK = 133;
    public static final int CALL = 43;
    public static final int CASE = 128;
    public static final int CATCH = 137;
    public static final int CATCH_SCOPE = 62;
    public static final int COLON = 116;
    public static final int COLONCOLON = 158;
    public static final int COMMA = 98;
    public static final int COMMENT = 175;
    public static final int COMPUTED_PROPERTY = 142;
    public static final int CONST = 168;
    public static final int CONTINUE = 134;
    public static final int DEBUGGER = 174;
    public static final int DEC = 120;
    public static final int DEFAULT = 129;
    public static final int DEFAULTNAMESPACE = 82;
    public static final int DELPROP = 31;
    public static final int DEL_REF = 75;
    public static final int DIV = 24;
    public static final int DO = 131;
    public static final int DOT = 121;
    public static final int DOTDOT = 157;
    public static final int DOTDOTDOT = 184;
    public static final int DOTQUERY = 160;
    public static final int ELSE = 126;
    public static final int EMPTY = 141;
    public static final int ENTERWITH = 2;
    public static final int ENUM_ID = 68;
    public static final int ENUM_INIT_ARRAY = 65;
    public static final int ENUM_INIT_KEYS = 63;
    public static final int ENUM_INIT_VALUES = 64;
    public static final int ENUM_INIT_VALUES_IN_ORDER = 66;
    public static final int ENUM_NEXT = 67;
    public static final int EOF = 0;
    public static final int EOL = 1;
    public static final int EQ = 12;
    public static final int ERROR = -1;
    public static final int ESCXMLATTR = 83;
    public static final int ESCXMLTEXT = 84;
    public static final int EXP = 81;
    public static final int EXPORT = 123;
    public static final int EXPR_RESULT = 148;
    public static final int EXPR_VOID = 147;
    public static final int FALSE = 49;
    public static final int FINALLY = 138;
    public static final int FIRST_ASSIGN = 99;
    public static final int FIRST_BYTECODE_TOKEN = 2;
    public static final int FIRST_TOKEN = -1;
    public static final int FOR = 132;
    public static final int FUNCTION = 122;
    public static final int GE = 17;
    public static final int GENEXPR = 176;
    public static final int GET = 165;
    public static final int GETELEM = 39;
    public static final int GETELEM_SUPER = 40;
    public static final int GETPROP = 33;
    public static final int GETPROPNOWARN = 34;
    public static final int GETPROPNOWARN_SUPER = 36;
    public static final int GETPROP_SUPER = 35;
    public static final int GETVAR = 60;
    public static final int GET_REF = 73;
    public static final int GOTO = 5;
    public static final int GT = 16;
    public static final int HOOK = 115;
    public static final int IF = 125;
    public static final int IFEQ = 6;
    public static final int IFNE = 7;
    public static final int IMPORT = 124;
    public static final int IN = 57;
    public static final int INC = 119;
    public static final int INSTANCEOF = 58;
    public static final int JSR = 149;
    public static final int LABEL = 144;
    public static final int LAST_ASSIGN = 114;
    public static final int LAST_BYTECODE_TOKEN = 89;
    public static final int LAST_TOKEN = 187;
    public static final int LB = 92;
    public static final int LC = 94;
    public static final int LE = 15;
    public static final int LEAVEWITH = 3;
    public static final int LET = 167;
    public static final int LETEXPR = 172;
    public static final int LOCAL_BLOCK = 155;
    public static final int LOCAL_LOAD = 59;
    public static final int LOOP = 146;
    public static final int LP = 96;
    public static final int LSH = 18;
    public static final int LT = 14;
    public static final int METHOD = 177;
    public static final int MOD = 25;
    public static final int MUL = 23;
    public static final int NAME = 44;
    public static final int NE = 13;
    public static final int NEG = 29;
    public static final int NEW = 30;
    public static final int NOT = 26;
    public static final int NULL = 47;
    public static final int NULLISH_COALESCING = 185;
    public static final int NUMBER = 45;
    public static final int OBJECTLIT = 72;
    public static final int OR = 117;
    public static final int POS = 28;
    public static final int QUESTION_DOT = 186;
    public static final int RB = 93;
    public static final int RC = 95;
    public static final int REF_CALL = 76;
    public static final int REF_MEMBER = 85;
    public static final int REF_NAME = 87;
    public static final int REF_NS_MEMBER = 86;
    public static final int REF_NS_NAME = 88;
    public static final int REF_SPECIAL = 77;
    public static final int REGEXP = 53;
    public static final int RESERVED = 140;
    public static final int RETHROW = 56;
    public static final int RETURN = 4;
    public static final int RETURN_RESULT = 70;
    public static final int RP = 97;
    public static final int RSH = 19;
    public static final int SCRIPT = 150;
    public static final int SEMI = 91;
    public static final int SET = 166;
    public static final int SETCONST = 169;
    public static final int SETCONSTVAR = 170;
    public static final int SETELEM = 41;
    public static final int SETELEM_OP = 154;
    public static final int SETELEM_SUPER = 42;
    public static final int SETNAME = 8;
    public static final int SETPROP = 37;
    public static final int SETPROP_OP = 153;
    public static final int SETPROP_SUPER = 38;
    public static final int SETVAR = 61;
    public static final int SET_REF = 74;
    public static final int SET_REF_OP = 156;
    public static final int SHEQ = 51;
    public static final int SHNE = 52;
    public static final int STRICT_SETNAME = 80;
    public static final int STRING = 46;
    public static final int SUB = 22;
    public static final int SUPER = 79;
    public static final int SWITCH = 127;
    public static final int TAGGED_TEMPLATE_LITERAL = 183;
    public static final int TARGET = 145;
    public static final int TEMPLATE_CHARS = 181;
    public static final int TEMPLATE_LITERAL = 180;
    public static final int TEMPLATE_LITERAL_SUBST = 182;
    public static final int THIS = 48;
    public static final int THISFN = 69;
    public static final int THROW = 55;
    public static final int TO_DOUBLE = 164;
    public static final int TO_OBJECT = 163;
    public static final int TRUE = 50;
    public static final int TRY = 90;
    public static final int TYPEOF = 32;
    public static final int TYPEOFNAME = 151;
    public static final int URSH = 20;
    public static final int USE_STACK = 152;
    public static final int VAR = 135;
    public static final int VOID = 139;
    public static final int WHILE = 130;
    public static final int WITH = 136;
    public static final int WITHEXPR = 173;
    public static final int XML = 159;
    public static final int XMLATTR = 161;
    public static final int XMLEND = 162;
    public static final int YIELD = 78;
    public static final int YIELD_STAR = 179;
    static final boolean printICode = false;
    static final boolean printNames = false;
    public static final boolean printTrees = false;

    public enum CommentType {
        LINE,
        BLOCK_COMMENT,
        JSDOC,
        HTML
    }

    public static boolean isValidToken(int i) {
        return i >= -1 && i <= 187;
    }

    public static String name(int i) {
        return String.valueOf(i);
    }

    public static String typeToName(int i) {
        switch (i) {
            case -1:
                return MediaError.ERROR_TYPE_ERROR;
            case 0:
                return "EOF";
            case 1:
                return "EOL";
            case 2:
                return "ENTERWITH";
            case 3:
                return "LEAVEWITH";
            case 4:
                return "RETURN";
            case 5:
                return "GOTO";
            case 6:
                return "IFEQ";
            case 7:
                return "IFNE";
            case 8:
                return "SETNAME";
            case 9:
                return "BITOR";
            case 10:
                return "BITXOR";
            case 11:
                return "BITAND";
            case 12:
                return "EQ";
            case 13:
                return "NE";
            case 14:
                return "LT";
            case 15:
                return "LE";
            case 16:
                return "GT";
            case 17:
                return "GE";
            case 18:
                return "LSH";
            case 19:
                return "RSH";
            case 20:
                return "URSH";
            case 21:
                return "ADD";
            case 22:
                return "SUB";
            case 23:
                return "MUL";
            case 24:
                return "DIV";
            case 25:
                return "MOD";
            case 26:
                return "NOT";
            case 27:
                return "BITNOT";
            case 28:
                return "POS";
            case 29:
                return "NEG";
            case 30:
                return "NEW";
            case 31:
                return "DELPROP";
            case 32:
                return "TYPEOF";
            case 33:
                return "GETPROP";
            case 34:
                return "GETPROPNOWARN";
            case 35:
                return "GETPROP_SUPER";
            case 36:
                return "GETPROPNOWARN_SUPER";
            case 37:
                return "SETPROP";
            case 38:
                return "SETPROP_SUPER";
            case 39:
                return "GETELEM";
            case 40:
                return "GETELEM_SUPER";
            case 41:
                return "SETELEM";
            case 42:
                return "SETELEM_SUPER";
            case 43:
                return "CALL";
            case 44:
                return "NAME";
            case 45:
                return "NUMBER";
            case 46:
                return "STRING";
            case 47:
                return "NULL";
            case 48:
                return "THIS";
            case 49:
                return "FALSE";
            case 50:
                return "TRUE";
            case 51:
                return "SHEQ";
            case 52:
                return "SHNE";
            case 53:
                return "REGEXP";
            case 54:
                return "BINDNAME";
            case 55:
                return "THROW";
            case 56:
                return "RETHROW";
            case 57:
                return HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN;
            case 58:
                return "INSTANCEOF";
            case 59:
                return "LOCAL_LOAD";
            case 60:
                return "GETVAR";
            case 61:
                return "SETVAR";
            case 62:
                return "CATCH_SCOPE";
            case 63:
                return "ENUM_INIT_KEYS";
            case 64:
                return "ENUM_INIT_VALUES";
            case 65:
                return "ENUM_INIT_ARRAY";
            case 66:
                return "ENUM_INIT_VALUES_IN_ORDER";
            case 67:
                return "ENUM_NEXT";
            case 68:
                return "ENUM_ID";
            case 69:
                return "THISFN";
            case 70:
                return "RETURN_RESULT";
            case 71:
                return "ARRAYLIT";
            case 72:
                return "OBJECTLIT";
            case 73:
                return "GET_REF";
            case 74:
                return "SET_REF";
            case 75:
                return "DEL_REF";
            case 76:
                return "REF_CALL";
            case 77:
                return "REF_SPECIAL";
            case 78:
                return "YIELD";
            case 79:
                return "SUPER";
            case 80:
                return "STRICT_SETNAME";
            case 81:
                return "EXP";
            case 82:
                return "DEFAULTNAMESPACE";
            case 83:
                return "ESCXMLATTR";
            case 84:
                return "ESCXMLTEXT";
            case 85:
                return "REF_MEMBER";
            case 86:
                return "REF_NS_MEMBER";
            case 87:
                return "REF_NAME";
            case 88:
                return "REF_NS_NAME";
            case 89:
                return "BIGINT";
            case 90:
                return "TRY";
            case 91:
                return "SEMI";
            case 92:
                return "LB";
            case 93:
                return "RB";
            case 94:
                return "LC";
            case 95:
                return "RC";
            case 96:
                return "LP";
            case 97:
                return "RP";
            case 98:
                return "COMMA";
            case 99:
                return "ASSIGN";
            case 100:
                return "ASSIGN_BITOR";
            case 101:
                return "ASSIGN_LOGICAL_OR";
            case 102:
                return "ASSIGN_BITXOR";
            case 103:
                return "ASSIGN_BITAND";
            case 104:
                return "ASSIGN_LOGICAL_AND";
            case 105:
                return "ASSIGN_LSH";
            case 106:
                return "ASSIGN_RSH";
            case 107:
                return "ASSIGN_URSH";
            case 108:
                return "ASSIGN_ADD";
            case 109:
                return "ASSIGN_SUB";
            case 110:
                return "ASSIGN_MUL";
            case 111:
                return "ASSIGN_DIV";
            case 112:
                return "ASSIGN_MOD";
            case 113:
                return "ASSIGN_EXP";
            case 114:
                return "ASSIGN_NULLISH";
            case 115:
                return "HOOK";
            case 116:
                return "COLON";
            case 117:
                return "OR";
            case 118:
                return "AND";
            case 119:
                return "INC";
            case 120:
                return "DEC";
            case 121:
                return "DOT";
            case 122:
                return "FUNCTION";
            case 123:
                return "EXPORT";
            case 124:
                return "IMPORT";
            case 125:
                return "IF";
            case 126:
                return "ELSE";
            case 127:
                return "SWITCH";
            case 128:
                return "CASE";
            case 129:
                return "DEFAULT";
            case 130:
                return "WHILE";
            case 131:
                return "DO";
            case 132:
                return "FOR";
            case 133:
                return "BREAK";
            case 134:
                return "CONTINUE";
            case 135:
                return "VAR";
            case 136:
                return "WITH";
            case 137:
                return "CATCH";
            case 138:
                return "FINALLY";
            case 139:
                return "VOID";
            case 140:
                return "RESERVED";
            case 141:
                return "EMPTY";
            case 142:
                return "COMPUTED_PROPERTY";
            case 143:
                return "BLOCK";
            case 144:
                return "LABEL";
            case 145:
                return "TARGET";
            case 146:
                return "LOOP";
            case 147:
                return "EXPR_VOID";
            case 148:
                return "EXPR_RESULT";
            case 149:
                return "JSR";
            case 150:
                return "SCRIPT";
            case 151:
                return "TYPEOFNAME";
            case 152:
                return "USE_STACK";
            case 153:
                return "SETPROP_OP";
            case 154:
                return "SETELEM_OP";
            case 155:
                return "LOCAL_BLOCK";
            case 156:
                return "SET_REF_OP";
            case 157:
                return "DOTDOT";
            case 158:
                return "COLONCOLON";
            case 159:
                return "XML";
            case 160:
                return "DOTQUERY";
            case 161:
                return "XMLATTR";
            case 162:
                return "XMLEND";
            case 163:
                return "TO_OBJECT";
            case 164:
                return "TO_DOUBLE";
            case 165:
                return UtilsKt.HTTP_METHOD_GET;
            case 166:
                return "SET";
            case 167:
                return "LET";
            case 168:
                return "CONST";
            case 169:
                return "SETCONST";
            case 170:
                return "SETCONSTVAR";
            case 171:
                return "ARRAYCOMP";
            case 172:
                return "LETEXPR";
            case 173:
                return "WITHEXPR";
            case 174:
                return "DEBUGGER";
            case 175:
                return "COMMENT";
            case 176:
                return "GENEXPR";
            case 177:
                return "METHOD";
            case 178:
                return "ARROW";
            case 179:
                return "YIELD_STAR";
            case 180:
                return "TEMPLATE_LITERAL";
            case 181:
                return "TEMPLATE_CHARS";
            case 182:
                return "TEMPLATE_LITERAL_SUBST";
            case 183:
                return "TAGGED_TEMPLATE_LITERAL";
            case 184:
                return "DOTDOTDOT";
            case 185:
                return "NULLISH_COALESCING";
            case 186:
                return "QUESTION_DOT";
            default:
                throw new IllegalStateException(String.valueOf(i));
        }
    }

    public static String keywordToName(int i) {
        if (i == 4) {
            return "return";
        }
        if (i == 55) {
            return "throw";
        }
        if (i == 90) {
            return "try";
        }
        if (i == 122) {
            return "function";
        }
        if (i == 174) {
            return "debugger";
        }
        if (i == 57) {
            return "in";
        }
        if (i == 58) {
            return "instanceof";
        }
        if (i == 78) {
            return "yield";
        }
        if (i == 79) {
            return "super";
        }
        if (i == 167) {
            return "let";
        }
        if (i != 168) {
            switch (i) {
                case 30:
                    return "new";
                case 31:
                    return "delete";
                case 32:
                    return "typeof";
                default:
                    switch (i) {
                        case 47:
                            return AbstractJsonLexerKt.NULL;
                        case 48:
                            return "this";
                        case 49:
                            return "false";
                        case 50:
                            return "true";
                        default:
                            switch (i) {
                                case 125:
                                    return "if";
                                case 126:
                                    return "else";
                                case 127:
                                    return "switch";
                                case 128:
                                    return "case";
                                case 129:
                                    return "default";
                                case 130:
                                    return "while";
                                case 131:
                                    return "do";
                                case 132:
                                    return "for";
                                case 133:
                                    return "break";
                                case 134:
                                    return "continue";
                                case 135:
                                    return "var";
                                case 136:
                                    return JsonPOJOBuilder.DEFAULT_WITH_PREFIX;
                                case 137:
                                    return "catch";
                                case 138:
                                    return "finally";
                                case 139:
                                    return "void";
                                default:
                                    return null;
                            }
                    }
            }
        }
        return "const";
    }
}
