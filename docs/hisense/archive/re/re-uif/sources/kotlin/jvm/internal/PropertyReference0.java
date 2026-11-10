package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty0;

/* JADX INFO: loaded from: classes.dex */
public abstract class PropertyReference0 extends PropertyReference implements KProperty0 {
    /* JADX WARN: Failed to parse method signature: ()TR
    jadx.core.utils.exceptions.JadxRuntimeException: Can't parse type: ()TR at position 3 ('R'), unexpected: T
    	at jadx.core.dex.nodes.parser.SignatureParser.consumeType(SignatureParser.java:169)
    	at jadx.core.dex.visitors.SignatureProcessor.parseMethodSignature(SignatureProcessor.java:188)
    	at jadx.core.dex.visitors.SignatureProcessor.visit(SignatureProcessor.java:40)
     */
    public abstract /* synthetic */ Object get();

    public PropertyReference0() {
    }

    public PropertyReference0(Object obj) {
        super(obj);
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected KCallable computeReflected() {
        return Reflection.property0(this);
    }

    @Override // kotlin.jvm.functions.Function0
    public Object invoke() {
        return get();
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public KProperty0.Getter getGetter() {
        return ((KProperty0) getReflected()).getGetter();
    }

    @Override // kotlin.reflect.KProperty0
    public Object getDelegate() {
        return ((KProperty0) getReflected()).getDelegate();
    }
}
