package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KProperty1;

/* JADX INFO: loaded from: classes.dex */
public abstract class MutablePropertyReference1 extends MutablePropertyReference implements KMutableProperty1 {
    /* JADX WARN: Failed to parse method signature: (TT)TR
    jadx.core.utils.exceptions.JadxRuntimeException: Can't parse type: (TT)TR at position 2 ('T'), unexpected: T
    	at jadx.core.dex.nodes.parser.SignatureParser.consumeType(SignatureParser.java:169)
    	at jadx.core.dex.nodes.parser.SignatureParser.consumeMethodArgs(SignatureParser.java:336)
    	at jadx.core.dex.visitors.SignatureProcessor.parseMethodSignature(SignatureProcessor.java:187)
    	at jadx.core.dex.visitors.SignatureProcessor.visit(SignatureProcessor.java:40)
     */
    public abstract /* synthetic */ Object get(Object obj);

    /* JADX WARN: Failed to parse method signature: (TTTR)V
    jadx.core.utils.exceptions.JadxRuntimeException: Can't parse type: (TTTR)V at position 2 ('T'), unexpected: T
    	at jadx.core.dex.nodes.parser.SignatureParser.consumeType(SignatureParser.java:169)
    	at jadx.core.dex.nodes.parser.SignatureParser.consumeMethodArgs(SignatureParser.java:336)
    	at jadx.core.dex.visitors.SignatureProcessor.parseMethodSignature(SignatureProcessor.java:187)
    	at jadx.core.dex.visitors.SignatureProcessor.visit(SignatureProcessor.java:40)
     */
    public abstract /* synthetic */ void set(Object obj, Object obj2);

    public MutablePropertyReference1() {
    }

    public MutablePropertyReference1(Object obj) {
        super(obj);
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected KCallable computeReflected() {
        return Reflection.mutableProperty1(this);
    }

    @Override // kotlin.jvm.functions.Function1
    public Object invoke(Object obj) {
        return get(obj);
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public KProperty1.Getter getGetter() {
        return ((KMutableProperty1) getReflected()).getGetter();
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference
    public KMutableProperty1.Setter getSetter() {
        return ((KMutableProperty1) getReflected()).getSetter();
    }

    @Override // kotlin.reflect.KProperty1
    public Object getDelegate(Object obj) {
        return ((KMutableProperty1) getReflected()).getDelegate(obj);
    }
}
