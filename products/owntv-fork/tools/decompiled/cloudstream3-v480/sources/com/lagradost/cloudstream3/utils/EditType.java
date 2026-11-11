package com.lagradost.cloudstream3.utils;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: Levenshtein.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/lagradost/cloudstream3/utils/EditType;", "", "<init>", "(Ljava/lang/String;I)V", "DELETE", "EQUAL", "INSERT", "REPLACE", "KEEP", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
enum EditType {
    DELETE,
    EQUAL,
    INSERT,
    REPLACE,
    KEEP;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<EditType> getEntries() {
        return $ENTRIES;
    }
}
