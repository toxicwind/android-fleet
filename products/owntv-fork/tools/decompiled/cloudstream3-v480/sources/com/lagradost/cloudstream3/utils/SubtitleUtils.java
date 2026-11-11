package com.lagradost.cloudstream3.utils;

import android.content.Context;
import com.lagradost.api.Log;
import com.lagradost.cloudstream3.utils.downloader.DownloadFileManagement;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.safefile.SafeFile;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SubtitleUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u001e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006J\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/utils/SubtitleUtils;", "", "<init>", "()V", "allowedExtensions", "", "", "deleteMatchingSubtitles", "", "context", "Landroid/content/Context;", "info", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadedFileInfo;", "isMatchingSubtitle", "", "name", "display", "cleanDisplay", "cleanDisplayName", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SubtitleUtils {
    public static final SubtitleUtils INSTANCE = new SubtitleUtils();
    private static final List<String> allowedExtensions = CollectionsKt.listOf((Object[]) new String[]{".vtt", ".srt", ".txt", ".ass", ".ttml", ".sbv", ".dfxp"});

    private SubtitleUtils() {
    }

    public final void deleteMatchingSubtitles(Context context, DownloadObjects.DownloadedFileInfo info) {
        SafeFile safeFileGotoDirectory;
        List<SafeFile> listListFiles;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(info, "info");
        String strCleanDisplayName = cleanDisplayName(info.getDisplayName());
        SafeFile safeFileBasePathToFile$app = DownloadFileManagement.INSTANCE.basePathToFile$app(context, info.getBasePath());
        if (safeFileBasePathToFile$app == null || (safeFileGotoDirectory = safeFileBasePathToFile$app.gotoDirectory(info.getRelativePath(), false)) == null || (listListFiles = safeFileGotoDirectory.listFiles()) == null) {
            return;
        }
        for (SafeFile safeFile : listListFiles) {
            String strName = safeFile.name();
            if (strName != null && isMatchingSubtitle(strName, info.getDisplayName(), strCleanDisplayName) && !Intrinsics.areEqual((Object) safeFile.delete(), (Object) true)) {
                Log.INSTANCE.e("SubtitleDeletion", "Failed to delete subtitle file: " + strName);
            }
        }
    }

    public final boolean isMatchingSubtitle(String name, String display, String cleanDisplay) {
        boolean z;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(display, "display");
        Intrinsics.checkNotNullParameter(cleanDisplay, "cleanDisplay");
        List<String> list = allowedExtensions;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                if (StringsKt.endsWith(name, (String) it.next(), true)) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
            break;
        }
        return z && !StringsKt.equals(name, display, true) && StringsKt.startsWith(cleanDisplayName(name), cleanDisplay, true);
    }

    public final String cleanDisplayName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return StringsKt.trim((CharSequence) StringsKt.substringBeforeLast$default(name, '.', (String) null, 2, (Object) null)).toString();
    }
}
