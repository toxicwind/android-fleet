package com.google.protobuf;

/* JADX INFO: loaded from: classes2.dex */
public enum Syntax implements Internal.EnumLite {
    SYNTAX_PROTO2(0),
    SYNTAX_PROTO3(1),
    UNRECOGNIZED(-1);

    public static final int SYNTAX_PROTO2_VALUE = 0;
    public static final int SYNTAX_PROTO3_VALUE = 1;
    public static final Internal.EnumLiteMap<Syntax> internalValueMap = new Internal.EnumLiteMap<Syntax>() { // from class: com.google.protobuf.Syntax.1
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public Syntax findValueByNumber(int i) {
            return Syntax.forNumber(i);
        }
    };
    public final int value;

    private static final class SyntaxVerifier implements Internal.EnumVerifier {
        public static final Internal.EnumVerifier INSTANCE = new SyntaxVerifier();

        @Override // com.google.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i) {
            return Syntax.forNumber(i) != null;
        }
    }

    Syntax(int i) {
        this.value = i;
    }

    public static Syntax forNumber(int i) {
        if (i == 0) {
            return SYNTAX_PROTO2;
        }
        if (i != 1) {
            return null;
        }
        return SYNTAX_PROTO3;
    }

    public static Internal.EnumLiteMap<Syntax> internalGetValueMap() {
        return internalValueMap;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return SyntaxVerifier.INSTANCE;
    }

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    @Deprecated
    public static Syntax valueOf(int i) {
        return forNumber(i);
    }
}
