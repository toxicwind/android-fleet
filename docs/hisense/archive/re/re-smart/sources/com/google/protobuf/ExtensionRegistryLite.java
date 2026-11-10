package com.google.protobuf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ExtensionRegistryLite {
    public static final ExtensionRegistryLite EMPTY_REGISTRY_LITE = new ExtensionRegistryLite(true);
    public static final String EXTENSION_CLASS_NAME = "com.google.protobuf.Extension";
    public static boolean doFullRuntimeInheritanceCheck = true;
    public static volatile boolean eagerlyParseMessageSets = false;
    public static volatile ExtensionRegistryLite emptyRegistry;
    public final Map<ObjectIntPair, GeneratedMessageLite.GeneratedExtension<?, ?>> extensionsByNumber;

    private static class ExtensionClassHolder {
        public static final Class<?> INSTANCE = resolveExtensionClass();

        public static Class<?> resolveExtensionClass() {
            try {
                return Class.forName(ExtensionRegistryLite.EXTENSION_CLASS_NAME);
            } catch (ClassNotFoundException unused) {
                return null;
            }
        }
    }

    private static final class ObjectIntPair {
        public final int number;
        public final Object object;

        public ObjectIntPair(Object obj, int i) {
            this.object = obj;
            this.number = i;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ObjectIntPair)) {
                return false;
            }
            ObjectIntPair objectIntPair = (ObjectIntPair) obj;
            return this.object == objectIntPair.object && this.number == objectIntPair.number;
        }

        public int hashCode() {
            return (System.identityHashCode(this.object) * 65535) + this.number;
        }
    }

    public ExtensionRegistryLite() {
        this.extensionsByNumber = new HashMap();
    }

    public static ExtensionRegistryLite getEmptyRegistry() {
        ExtensionRegistryLite extensionRegistryLiteCreateEmpty = emptyRegistry;
        if (extensionRegistryLiteCreateEmpty == null) {
            synchronized (ExtensionRegistryLite.class) {
                extensionRegistryLiteCreateEmpty = emptyRegistry;
                if (extensionRegistryLiteCreateEmpty == null) {
                    extensionRegistryLiteCreateEmpty = doFullRuntimeInheritanceCheck ? ExtensionRegistryFactory.createEmpty() : EMPTY_REGISTRY_LITE;
                    emptyRegistry = extensionRegistryLiteCreateEmpty;
                }
            }
        }
        return extensionRegistryLiteCreateEmpty;
    }

    public static boolean isEagerlyParseMessageSets() {
        return eagerlyParseMessageSets;
    }

    public static ExtensionRegistryLite newInstance() {
        return doFullRuntimeInheritanceCheck ? ExtensionRegistryFactory.create() : new ExtensionRegistryLite();
    }

    public static void setEagerlyParseMessageSets(boolean z) {
        eagerlyParseMessageSets = z;
    }

    public final void add(GeneratedMessageLite.GeneratedExtension<?, ?> generatedExtension) {
        this.extensionsByNumber.put(new ObjectIntPair(generatedExtension.getContainingTypeDefaultInstance(), generatedExtension.getNumber()), generatedExtension);
    }

    public <ContainingType extends MessageLite> GeneratedMessageLite.GeneratedExtension<ContainingType, ?> findLiteExtensionByNumber(ContainingType containingtype, int i) {
        return (GeneratedMessageLite.GeneratedExtension) this.extensionsByNumber.get(new ObjectIntPair(containingtype, i));
    }

    public ExtensionRegistryLite getUnmodifiable() {
        return new ExtensionRegistryLite(this);
    }

    public ExtensionRegistryLite(ExtensionRegistryLite extensionRegistryLite) {
        if (extensionRegistryLite == EMPTY_REGISTRY_LITE) {
            this.extensionsByNumber = Collections.emptyMap();
        } else {
            this.extensionsByNumber = Collections.unmodifiableMap(extensionRegistryLite.extensionsByNumber);
        }
    }

    public final void add(ExtensionLite<?, ?> extensionLite) {
        if (GeneratedMessageLite.GeneratedExtension.class.isAssignableFrom(extensionLite.getClass())) {
            add((GeneratedMessageLite.GeneratedExtension<?, ?>) extensionLite);
        }
        if (doFullRuntimeInheritanceCheck && ExtensionRegistryFactory.isFullRegistry(this)) {
            try {
                getClass().getMethod("add", ExtensionClassHolder.INSTANCE).invoke(this, extensionLite);
            } catch (Exception e2) {
                throw new IllegalArgumentException(String.format("Could not invoke ExtensionRegistry#add for %s", extensionLite), e2);
            }
        }
    }

    public ExtensionRegistryLite(boolean z) {
        this.extensionsByNumber = Collections.emptyMap();
    }
}
