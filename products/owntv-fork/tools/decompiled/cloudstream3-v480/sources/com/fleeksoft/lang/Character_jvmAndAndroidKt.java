package com.fleeksoft.lang;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Character.jvmAndAndroid.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"isSpaceChar", "", "Lcom/fleeksoft/lang/Character;", "codePoint", "", "getType", "io-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class Character_jvmAndAndroidKt {
    public static final boolean isSpaceChar(Character character, int i) {
        Intrinsics.checkNotNullParameter(character, "<this>");
        return java.lang.Character.isSpaceChar(i);
    }

    public static final int getType(Character character, int i) {
        Intrinsics.checkNotNullParameter(character, "<this>");
        return java.lang.Character.getType(i);
    }
}
