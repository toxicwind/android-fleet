package org.mozilla.javascript.optimizer;

/* JADX INFO: loaded from: classes8.dex */
interface Signatures {
    public static final String MATH_ADD = "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;";
    public static final String MATH_COMPARE = "(Ljava/lang/Object;Ljava/lang/Object;)Z";
    public static final String MATH_EQ = "(Ljava/lang/Object;Ljava/lang/Object;)Z";
    public static final String MATH_SHALLOW_EQ = "(Ljava/lang/Object;Ljava/lang/Object;)Z";
    public static final String MATH_TO_BOOLEAN = "(Ljava/lang/Object;)Z";
    public static final String MATH_TO_INT32 = "(Ljava/lang/Object;)I";
    public static final String MATH_TO_NUMBER = "(Ljava/lang/Object;)D";
    public static final String MATH_TO_NUMERIC = "(Ljava/lang/Object;)Ljava/lang/Number;";
    public static final String MATH_TO_UINT32 = "(Ljava/lang/Object;)J";
    public static final String NAME_BIND = "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Scriptable;";
    public static final String NAME_GET = "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;";
    public static final String NAME_GET_THIS = "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Callable;";
    public static final String NAME_SET = "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
    public static final String NAME_SET_CONST = "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;";
    public static final String NAME_SET_STRICT = "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
    public static final String PROP_GET = "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
    public static final String PROP_GET_ELEMENT = "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
    public static final String PROP_GET_ELEMENT_SUPER = "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Ljava/lang/Object;";
    public static final String PROP_GET_INDEX = "(Ljava/lang/Object;DLorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
    public static final String PROP_GET_NOWARN = "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
    public static final String PROP_GET_SUPER = "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Z)Ljava/lang/Object;";
    public static final String PROP_GET_THIS = "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Callable;";
    public static final String PROP_SET = "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
    public static final String PROP_SET_ELEMENT = "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
    public static final String PROP_SET_ELEMENT_SUPER = "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Ljava/lang/Object;";
    public static final String PROP_SET_INDEX = "(Ljava/lang/Object;DLjava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
    public static final String PROP_SET_SUPER = "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Ljava/lang/Object;";
}
