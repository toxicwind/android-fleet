package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public class ThrowableDeserializer extends BeanDeserializer {
    protected static final String PROP_NAME_MESSAGE = "message";
    protected static final String PROP_NAME_SUPPRESSED = "suppressed";
    private static final long serialVersionUID = 1;

    public ThrowableDeserializer(BeanDeserializer beanDeserializer) {
        super(beanDeserializer);
        this._vanillaProcessing = false;
    }

    protected ThrowableDeserializer(BeanDeserializer beanDeserializer, NameTransformer nameTransformer) {
        super(beanDeserializer, nameTransformer);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializer, com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer nameTransformer) {
        return getClass() != ThrowableDeserializer.class ? this : new ThrowableDeserializer(this, nameTransformer);
    }

    /* JADX WARN: Code duplicated, block: B:36:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:41:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:43:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:44:0x00d7  */
    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializer, com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingPropertyBased(jsonParser, deserializationContext);
        }
        if (this._delegateDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(deserializationContext, this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
        }
        if (this._beanType.isAbstract()) {
            return deserializationContext.handleMissingInstantiator(handledType(), getValueInstantiator(), jsonParser, "abstract type (need to add/enable type information?)", new Object[0]);
        }
        boolean zCanCreateFromString = this._valueInstantiator.canCreateFromString();
        boolean zCanCreateUsingDefault = this._valueInstantiator.canCreateUsingDefault();
        if (!zCanCreateFromString && !zCanCreateUsingDefault) {
            return deserializationContext.handleMissingInstantiator(handledType(), getValueInstantiator(), jsonParser, "Throwable needs a default constructor, a single-String-arg constructor; or explicit @JsonCreator", new Object[0]);
        }
        int i = 0;
        Throwable th = null;
        Object[] objArr = null;
        Throwable[] thArr = null;
        while (!jsonParser.hasToken(JsonToken.END_OBJECT)) {
            String strCurrentName = jsonParser.currentName();
            SettableBeanProperty settableBeanPropertyFind = this._beanProperties.find(strCurrentName);
            jsonParser.nextToken();
            if (settableBeanPropertyFind != null) {
                if (th != null) {
                    settableBeanPropertyFind.deserializeAndSet(jsonParser, deserializationContext, th);
                } else {
                    if (objArr == null) {
                        int size = this._beanProperties.size();
                        objArr = new Object[size + size];
                    }
                    int i2 = i + 1;
                    objArr[i] = settableBeanPropertyFind;
                    i += 2;
                    objArr[i2] = settableBeanPropertyFind.deserialize(jsonParser, deserializationContext);
                }
            } else if ("message".equals(strCurrentName)) {
                if (zCanCreateFromString) {
                    th = (Throwable) this._valueInstantiator.createFromString(deserializationContext, jsonParser.getValueAsString());
                } else if (this._ignorableProps == null && this._ignorableProps.contains(strCurrentName)) {
                    jsonParser.skipChildren();
                } else if (this._anySetter != null) {
                    this._anySetter.deserializeAndSet(jsonParser, deserializationContext, th, strCurrentName);
                } else {
                    handleUnknownProperty(jsonParser, deserializationContext, th, strCurrentName);
                }
            } else if (PROP_NAME_SUPPRESSED.equals(strCurrentName)) {
                thArr = (Throwable[]) deserializationContext.readValue(jsonParser, Throwable[].class);
            } else if (this._ignorableProps == null) {
                if (this._anySetter != null) {
                    this._anySetter.deserializeAndSet(jsonParser, deserializationContext, th, strCurrentName);
                } else {
                    handleUnknownProperty(jsonParser, deserializationContext, th, strCurrentName);
                }
            } else if (this._anySetter != null) {
                this._anySetter.deserializeAndSet(jsonParser, deserializationContext, th, strCurrentName);
            } else {
                handleUnknownProperty(jsonParser, deserializationContext, th, strCurrentName);
            }
            jsonParser.nextToken();
        }
        if (th == null) {
            if (zCanCreateFromString) {
                th = (Throwable) this._valueInstantiator.createFromString(deserializationContext, null);
            } else {
                th = (Throwable) this._valueInstantiator.createUsingDefault(deserializationContext);
            }
        }
        if (objArr != null) {
            for (int i3 = 0; i3 < i; i3 += 2) {
                ((SettableBeanProperty) objArr[i3]).set(th, objArr[i3 + 1]);
            }
        }
        if (thArr != null) {
            for (Throwable th2 : thArr) {
                th.addSuppressed(th2);
            }
        }
        return th;
    }
}
