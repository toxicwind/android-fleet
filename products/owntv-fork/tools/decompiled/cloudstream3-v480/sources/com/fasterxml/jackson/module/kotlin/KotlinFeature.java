package com.fasterxml.jackson.module.kotlin;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: KotlinFeature.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinFeature;", "", "enabledByDefault", "", "(Ljava/lang/String;IZ)V", "bitSet", "Ljava/util/BitSet;", "getBitSet$jackson_module_kotlin", "()Ljava/util/BitSet;", "NullToEmptyCollection", "NullToEmptyMap", "NullIsSameAsDefault", "SingletonSupport", "StrictNullChecks", "Companion", "jackson-module-kotlin"}, k = 1, mv = {1, 5, 1}, xi = 48)
public enum KotlinFeature {
    NullToEmptyCollection(false),
    NullToEmptyMap(false),
    NullIsSameAsDefault(false),
    SingletonSupport(false),
    StrictNullChecks(false);


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BitSet bitSet = ExtensionsKt.toBitSet((int) Math.pow(2.0d, ordinal()));
    private final boolean enabledByDefault;

    KotlinFeature(boolean z) {
        this.enabledByDefault = z;
    }

    /* JADX INFO: renamed from: getBitSet$jackson_module_kotlin, reason: from getter */
    public final BitSet getBitSet() {
        return this.bitSet;
    }

    /* JADX INFO: compiled from: KotlinFeature.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinFeature$Companion;", "", "()V", "defaults", "Ljava/util/BitSet;", "getDefaults$jackson_module_kotlin", "()Ljava/util/BitSet;", "jackson-module-kotlin"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BitSet getDefaults$jackson_module_kotlin() {
            BitSet bitSet = ExtensionsKt.toBitSet(0);
            KotlinFeature[] kotlinFeatureArrValues = KotlinFeature.values();
            ArrayList arrayList = new ArrayList();
            for (KotlinFeature kotlinFeature : kotlinFeatureArrValues) {
                if (kotlinFeature.enabledByDefault) {
                    arrayList.add(kotlinFeature);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                bitSet.or(((KotlinFeature) it.next()).getBitSet());
            }
            return bitSet;
        }
    }
}
