package com.lagradost.cloudstream3.utils;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import kotlin.Metadata;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: GitInfo.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/GitInfo;", "", "<init>", "()V", "currentCommitHash", "", "Landroid/content/Context;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class GitInfo {
    public static final GitInfo INSTANCE = new GitInfo();

    private GitInfo() {
    }

    public final String currentCommitHash(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            InputStream inputStreamOpen = context.getAssets().open("git-hash.txt");
            Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "open(...)");
            Reader inputStreamReader = new InputStreamReader(inputStreamOpen, Charsets.UTF_8);
            return StringsKt.trim((CharSequence) TextStreamsKt.readText(inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192))).toString();
        } catch (Exception unused) {
            return "";
        }
    }
}
