package com.lagradost.cloudstream3.extractors;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: M3u8Manifest.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J6\u0010\u0004\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0005j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006`\b2\u0006\u0010\t\u001a\u00020\u0007¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/M3u8Manifest;", "", "<init>", "()V", "extractLinks", "Ljava/util/ArrayList;", "Lkotlin/Pair;", "", "Lkotlin/collections/ArrayList;", "m3u8Data", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class M3u8Manifest {
    public static final M3u8Manifest INSTANCE = new M3u8Manifest();

    private M3u8Manifest() {
    }

    public final ArrayList<Pair<String, String>> extractLinks(String m3u8Data) {
        Intrinsics.checkNotNullParameter(m3u8Data, "m3u8Data");
        ArrayList<Pair<String, String>> arrayList = new ArrayList<>();
        for (MatchResult matchResult : Regex.findAll$default(new Regex("\"(.*?)\":\"(.*?)\""), m3u8Data, 0, 2, null)) {
            String strReplace$default = StringsKt.replace$default(matchResult.getGroupValues().get(1), "auto", "Auto", false, 4, (Object) null);
            if (!Intrinsics.areEqual(strReplace$default, "Auto") && !StringsKt.endsWith$default((CharSequence) strReplace$default, 'p', false, 2, (Object) null)) {
                strReplace$default = strReplace$default + 'p';
            }
            arrayList.add(new Pair<>(matchResult.getGroupValues().get(2), strReplace$default));
        }
        return arrayList;
    }
}
