package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@Beta
public final class ImmutableTypeToInstanceMap<B> extends ForwardingMap<TypeToken<? extends B>, B> implements TypeToInstanceMap<B> {
    public final ImmutableMap<TypeToken<? extends B>, B> delegate;

    @Beta
    public static final class Builder<B> {
        public final ImmutableMap.Builder<TypeToken<? extends B>, B> mapBuilder;

        public ImmutableTypeToInstanceMap<B> build() {
            return new ImmutableTypeToInstanceMap<>(this.mapBuilder.build(), null);
        }

        @CanIgnoreReturnValue
        public <T extends B> Builder<B> put(Class<T> cls, T t) {
            this.mapBuilder.put(new TypeToken.SimpleTypeToken(cls), t);
            return this;
        }

        public Builder() {
            this.mapBuilder = ImmutableMap.builder();
        }

        @CanIgnoreReturnValue
        public <T extends B> Builder<B> put(TypeToken<T> typeToken, T t) {
            this.mapBuilder.put(typeToken.rejectTypeVariables(), t);
            return this;
        }
    }

    public ImmutableTypeToInstanceMap(ImmutableMap<TypeToken<? extends B>, B> immutableMap) {
        this.delegate = immutableMap;
    }

    public static <B> Builder<B> builder() {
        return new Builder<>();
    }

    public static <B> ImmutableTypeToInstanceMap<B> of() {
        return new ImmutableTypeToInstanceMap<>(ImmutableMap.of());
    }

    private <T extends B> T trustedGet(TypeToken<T> typeToken) {
        return this.delegate.get(typeToken);
    }

    @Override // com.google.common.reflect.TypeToInstanceMap
    public <T extends B> T getInstance(TypeToken<T> typeToken) {
        return (T) trustedGet(typeToken.rejectTypeVariables());
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map, com.google.common.collect.BiMap
    @Deprecated
    public void putAll(Map<? extends TypeToken<? extends B>, ? extends B> map) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.reflect.TypeToInstanceMap
    @CanIgnoreReturnValue
    @Deprecated
    public <T extends B> T putInstance(TypeToken<T> typeToken, T t) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
    public Map<TypeToken<? extends B>, B> delegate() {
        return this.delegate;
    }

    @Override // com.google.common.reflect.TypeToInstanceMap
    public <T extends B> T getInstance(Class<T> cls) {
        return this.delegate.get(new TypeToken.SimpleTypeToken(cls));
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map, com.google.common.collect.BiMap
    @CanIgnoreReturnValue
    @Deprecated
    public B put(TypeToken<? extends B> typeToken, B b2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.reflect.TypeToInstanceMap
    @CanIgnoreReturnValue
    @Deprecated
    public <T extends B> T putInstance(Class<T> cls, T t) {
        throw new UnsupportedOperationException();
    }

    public /* synthetic */ ImmutableTypeToInstanceMap(ImmutableMap immutableMap, AnonymousClass1 anonymousClass1) {
        this.delegate = immutableMap;
    }
}
