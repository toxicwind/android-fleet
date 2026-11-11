package com.lagradost.cloudstream3.utils.downloader;

import android.content.Context;
import android.net.Uri;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.preference.PreferenceManager;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.safefile.MediaFileContentType;
import com.lagradost.safefile.SafeFile;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: DownloadFileManagement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nJ;\u0010\u000b\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0002\b\u0013J!\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0002\b\u0017J!\u0010\u0018\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00050\r*\u00020\u0010H\u0000¢\u0006\u0002\b\u0019J\u001d\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0000¢\u0006\u0002\b\u001dJ5\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u001e\u001a\u0004\u0018\u00010\u00052\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010 H\u0000¢\u0006\u0004\b\u001d\u0010\"J\u001b\u0010#\u001a\u0004\u0018\u00010\u0015*\u00020$2\u0006\u0010\u000f\u001a\u00020\u0010H\u0000¢\u0006\u0002\b%J\u001d\u0010\u000b\u001a\u00020\u00052\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0013J\u0010\u0010)\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadFileManagement;", "", "<init>", "()V", "RESERVED_CHARS", "", "sanitizeFilename", "name", "removeSpaces", "", "sanitizeFilename$app", "getFolder", "", "Lkotlin/Pair;", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "relativePath", "basePath", "getFolder$app", "basePathToFile", "Lcom/lagradost/safefile/SafeFile;", "path", "basePathToFile$app", "getBasePath", "getBasePath$app", "getFileName", TtmlNode.TAG_METADATA, "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeMetadata;", "getFileName$app", "epName", "episode", "", "season", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/String;", "toFile", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadedFileInfo;", "toFile$app", "currentType", "Lcom/lagradost/cloudstream3/TvType;", "titleName", "getDefaultDir", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DownloadFileManagement {
    public static final DownloadFileManagement INSTANCE = new DownloadFileManagement();
    private static final String RESERVED_CHARS = "|\\?*<\":>+[]/'";

    private DownloadFileManagement() {
    }

    public static /* synthetic */ String sanitizeFilename$app$default(DownloadFileManagement downloadFileManagement, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return downloadFileManagement.sanitizeFilename$app(str, z);
    }

    public final String sanitizeFilename$app(String name, boolean removeSpaces) {
        Intrinsics.checkNotNullParameter(name, "name");
        String strReplace$default = name;
        for (int i = 0; i < 13; i++) {
            strReplace$default = StringsKt.replace$default(strReplace$default, RESERVED_CHARS.charAt(i), ' ', false, 4, (Object) null);
        }
        if (removeSpaces) {
            strReplace$default = StringsKt.replace$default(strReplace$default, Stream.ID_UNKNOWN, "", false, 4, (Object) null);
        }
        return StringsKt.trim(StringsKt.replace$default(strReplace$default, "  ", Stream.ID_UNKNOWN, false, 4, (Object) null), ' ');
    }

    public final List<Pair<String, Uri>> getFolder$app(Context context, String relativePath, String basePath) {
        SafeFile safeFileGotoDirectory;
        List<SafeFile> listListFiles;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(relativePath, "relativePath");
        SafeFile safeFileBasePathToFile$app = basePathToFile$app(context, basePath);
        if (safeFileBasePathToFile$app == null || (safeFileGotoDirectory = safeFileBasePathToFile$app.gotoDirectory(relativePath, false)) == null || (listListFiles = safeFileGotoDirectory.listFiles()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (SafeFile safeFile : listListFiles) {
            String strName = safeFile.name();
            if (strName == null) {
                strName = "";
            }
            Uri uri = safeFile.uri();
            Pair pair = uri == null ? null : TuplesKt.to(strName, uri);
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        return arrayList;
    }

    public final SafeFile basePathToFile$app(Context context, String path) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = path;
        if (str == null || StringsKt.isBlank(str)) {
            return getDefaultDir(context);
        }
        if (StringsKt.startsWith$default(path, "content://", false, 2, (Object) null)) {
            return SafeFile.INSTANCE.fromUri(context, Uri.parse(path));
        }
        return SafeFile.INSTANCE.fromFilePath(context, path);
    }

    public final Pair<SafeFile, String> getBasePath$app(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.download_path_key), null);
        return TuplesKt.to(basePathToFile$app(context, string), string);
    }

    public final String getFileName$app(Context context, DownloadObjects.DownloadEpisodeMetadata metadata) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return getFileName$app(context, metadata.getName(), metadata.getEpisode(), metadata.getSeason());
    }

    public final String getFileName$app(Context context, String epName, Integer episode, Integer season) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (epName == null) {
            if (season != null) {
                epName = context.getString(R.string.season) + ' ' + season.intValue() + ' ' + context.getString(R.string.episode) + ' ' + episode;
            } else {
                epName = context.getString(R.string.episode) + ' ' + episode;
            }
        } else if (episode != null) {
            if (season != null) {
                epName = context.getString(R.string.season) + ' ' + season.intValue() + ' ' + context.getString(R.string.episode) + ' ' + episode.intValue() + " - " + epName;
            } else {
                epName = context.getString(R.string.episode) + ' ' + episode.intValue() + " - " + epName;
            }
        }
        return sanitizeFilename$app$default(this, epName, false, 2, null);
    }

    public final SafeFile toFile$app(DownloadObjects.DownloadedFileInfo downloadedFileInfo, Context context) {
        SafeFile safeFileGotoDirectory;
        Intrinsics.checkNotNullParameter(downloadedFileInfo, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        SafeFile safeFileBasePathToFile$app = basePathToFile$app(context, downloadedFileInfo.getBasePath());
        if (safeFileBasePathToFile$app == null || (safeFileGotoDirectory = safeFileBasePathToFile$app.gotoDirectory(downloadedFileInfo.getRelativePath(), false)) == null) {
            return null;
        }
        return SafeFile.DefaultImpls.findFile$default(safeFileGotoDirectory, downloadedFileInfo.getDisplayName(), false, 2, null);
    }

    public final String getFolder$app(TvType currentType, String titleName) {
        Intrinsics.checkNotNullParameter(currentType, "currentType");
        Intrinsics.checkNotNullParameter(titleName, "titleName");
        if (MainAPIKt.isEpisodeBased(currentType)) {
            return MainAPIKt.getFolderPrefix(currentType) + '/' + sanitizeFilename$app$default(this, titleName, false, 2, null);
        }
        return MainAPIKt.getFolderPrefix(currentType);
    }

    public final SafeFile getDefaultDir(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return SafeFile.Companion.fromMedia$default(SafeFile.INSTANCE, context, MediaFileContentType.Downloads, null, false, 12, null);
    }
}
