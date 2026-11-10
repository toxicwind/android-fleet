package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class SerializerFactoryConfig implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Serializers[] _additionalKeySerializers;
    protected final Serializers[] _additionalSerializers;
    protected final BeanSerializerModifier[] _modifiers;
    protected static final Serializers[] NO_SERIALIZERS = new Serializers[0];
    protected static final BeanSerializerModifier[] NO_MODIFIERS = new BeanSerializerModifier[0];

    public SerializerFactoryConfig() {
        this(null, null, null);
    }

    protected SerializerFactoryConfig(Serializers[] serializersArr, Serializers[] serializersArr2, BeanSerializerModifier[] beanSerializerModifierArr) {
        Serializers[] serializersArr3 = NO_SERIALIZERS;
        this._additionalSerializers = serializersArr == null ? serializersArr3 : serializersArr;
        this._additionalKeySerializers = serializersArr2 == null ? serializersArr3 : serializersArr2;
        this._modifiers = beanSerializerModifierArr == null ? NO_MODIFIERS : beanSerializerModifierArr;
    }

    public boolean hasKeySerializers() {
        return this._additionalKeySerializers.length > 0;
    }

    public boolean hasSerializerModifiers() {
        return this._modifiers.length > 0;
    }

    public Iterable<Serializers> serializers() {
        return new ArrayIterator(this._additionalSerializers);
    }

    public Iterable<Serializers> keySerializers() {
        return new ArrayIterator(this._additionalKeySerializers);
    }

    public Iterable<BeanSerializerModifier> serializerModifiers() {
        return new ArrayIterator(this._modifiers);
    }
}
