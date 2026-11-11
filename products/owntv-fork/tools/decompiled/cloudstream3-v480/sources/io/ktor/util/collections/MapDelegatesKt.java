package io.ktor.util.collections;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: MapDelegates.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a*\u0010\u0005\u001a\u0004\u0018\u00010\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0087\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a2\u0010\t\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u0000H\u0087\u0002¢\u0006\u0004\b\t\u0010\n\u001a6\u0010\u0005\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u00028\u00000\f2\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0087\u0002¢\u0006\u0004\b\u0005\u0010\r\u001a>\u0010\t\u001a\u00020\b\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u00028\u00000\f2\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00032\b\u0010\u0007\u001a\u0004\u0018\u00018\u0000H\u0087\u0002¢\u0006\u0004\b\t\u0010\u000e\u001a\u0019\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\f*\u00020\u0000H\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u001b\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\f*\u00020\u0000H\u0007¢\u0006\u0004\b\u0012\u0010\u0011¨\u0006\u0013"}, d2 = {"", "Lio/ktor/util/collections/StringMap;", "thisRef", "Lkotlin/reflect/KProperty;", "property", "getValue", "(Ljava/lang/String;Lio/ktor/util/collections/StringMap;Lkotlin/reflect/KProperty;)Ljava/lang/String;", ES6Iterator.VALUE_PROPERTY, "", "setValue", "(Ljava/lang/String;Lio/ktor/util/collections/StringMap;Lkotlin/reflect/KProperty;Ljava/lang/String;)V", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/util/collections/SerializedMapValue;", "(Lio/ktor/util/collections/SerializedMapValue;Lio/ktor/util/collections/StringMap;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "(Lio/ktor/util/collections/SerializedMapValue;Lio/ktor/util/collections/StringMap;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "", "asBoolean", "(Ljava/lang/String;)Lio/ktor/util/collections/SerializedMapValue;", "asPresenceBoolean", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MapDelegatesKt {
    public static final String getValue(String str, StringMap thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(thisRef, "thisRef");
        Intrinsics.checkNotNullParameter(property, "property");
        return thisRef.get(str);
    }

    public static final void setValue(String str, StringMap thisRef, KProperty<?> property, String str2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(thisRef, "thisRef");
        Intrinsics.checkNotNullParameter(property, "property");
        if (str2 == null) {
            thisRef.remove(str);
        } else {
            thisRef.set(str, str2);
        }
    }

    public static final <T> T getValue(SerializedMapValue<T> serializedMapValue, StringMap thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(serializedMapValue, "<this>");
        Intrinsics.checkNotNullParameter(thisRef, "thisRef");
        Intrinsics.checkNotNullParameter(property, "property");
        String str = thisRef.get(serializedMapValue.getKey());
        if (str != null) {
            return serializedMapValue.getDeserialize$ktor_utils().invoke(str);
        }
        return null;
    }

    public static final <T> void setValue(SerializedMapValue<T> serializedMapValue, StringMap thisRef, KProperty<?> property, T t) {
        Intrinsics.checkNotNullParameter(serializedMapValue, "<this>");
        Intrinsics.checkNotNullParameter(thisRef, "thisRef");
        Intrinsics.checkNotNullParameter(property, "property");
        String strInvoke = t != null ? serializedMapValue.getSerialize$ktor_utils().invoke(t) : null;
        if (strInvoke == null) {
            thisRef.remove(serializedMapValue.getKey());
        } else {
            thisRef.set(serializedMapValue.getKey(), strInvoke);
        }
    }

    /* JADX INFO: renamed from: io.ktor.util.collections.MapDelegatesKt$asBoolean$1, reason: invalid class name */
    /* JADX INFO: compiled from: MapDelegates.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Boolean, String> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1, Boolean.TYPE, "toString", "toString()Ljava/lang/String;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ String invoke(Boolean bool) {
            return invoke(bool.booleanValue());
        }

        public final String invoke(boolean z) {
            return String.valueOf(z);
        }
    }

    /* JADX INFO: renamed from: io.ktor.util.collections.MapDelegatesKt$asBoolean$2, reason: invalid class name */
    /* JADX INFO: compiled from: MapDelegates.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<String, Boolean> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        AnonymousClass2() {
            super(1, StringsKt.class, "toBoolean", "toBoolean(Ljava/lang/String;)Z", 1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(String p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return Boolean.valueOf(Boolean.parseBoolean(p0));
        }
    }

    public static final SerializedMapValue<Boolean> asBoolean(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return new SerializedMapValue<>(str, AnonymousClass1.INSTANCE, AnonymousClass2.INSTANCE);
    }

    public static final SerializedMapValue<Boolean> asPresenceBoolean(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return new SerializedMapValue<>(str, new Function1() { // from class: io.ktor.util.collections.MapDelegatesKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MapDelegatesKt.asPresenceBoolean$lambda$0((Boolean) obj);
            }
        }, new Function1() { // from class: io.ktor.util.collections.MapDelegatesKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MapDelegatesKt.asPresenceBoolean$lambda$1((String) obj);
            }
        });
    }

    static final String asPresenceBoolean$lambda$0(Boolean bool) {
        if (Intrinsics.areEqual((Object) bool, (Object) false)) {
            return null;
        }
        return "";
    }

    static final Boolean asPresenceBoolean$lambda$1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return true;
    }
}
