package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.KProperty2;

/* JADX INFO: loaded from: classes.dex */
public abstract class MutablePropertyReference2 extends MutablePropertyReference implements KMutableProperty2 {
    /* JADX WARN: Failed to parse method signature: (TDTE)TR
    jadx.core.utils.exceptions.JadxRuntimeException: Can't parse type: (TDTE)TR at position 2 ('D'), unexpected: T
    	at jadx.core.dex.nodes.parser.SignatureParser.consumeType(SignatureParser.java:169)
    	at jadx.core.dex.nodes.parser.SignatureParser.consumeMethodArgs(SignatureParser.java:336)
    	at jadx.core.dex.visitors.SignatureProcessor.parseMethodSignature(SignatureProcessor.java:187)
    	at jadx.core.dex.visitors.SignatureProcessor.visit(SignatureProcessor.java:40)
     */
    public abstract /* synthetic */ Object get(Object obj, Object obj2);

    /* JADX WARN: Failed to parse method signature: (TDTETR)V
    jadx.core.utils.exceptions.JadxRuntimeException: Can't parse type: (TDTETR)V at position 2 ('D'), unexpected: T
    	at jadx.core.dex.nodes.parser.SignatureParser.consumeType(SignatureParser.java:169)
    	at jadx.core.dex.nodes.parser.SignatureParser.consumeMethodArgs(SignatureParser.java:336)
    	at jadx.core.dex.visitors.SignatureProcessor.parseMethodSignature(SignatureProcessor.java:187)
    	at jadx.core.dex.visitors.SignatureProcessor.visit(SignatureProcessor.java:40)
     */
    public abstract /* synthetic */ void set(Object obj, Object obj2, Object obj3);

    @Override // kotlin.jvm.internal.CallableReference
    protected KCallable computeReflected() {
        return Reflection.mutableProperty2(this);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Object obj, Object obj2) {
        return get(obj, obj2);
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public KProperty2.Getter getGetter() {
        return ((KMutableProperty2) getReflected()).getGetter();
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference
    public KMutableProperty2.Setter getSetter() {
        return ((KMutableProperty2) getReflected()).getSetter();
    }

    @Override // kotlin.reflect.KProperty2
    public Object getDelegate(Object obj, Object obj2) {
        return ((KMutableProperty2) getReflected()).getDelegate(obj, obj2);
    }
}
