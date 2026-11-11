package io.github.anilbeesetti.nextlib.mediainfo;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import coil3.util.UtilsKt;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PathUtil.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ=\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\rH\u0002¢\u0006\u0002\u0010\u000eR\u0015\u0010\u000f\u001a\u00020\u0010*\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u0015\u0010\u0012\u001a\u00020\u0010*\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0011R\u0015\u0010\u0013\u001a\u00020\u0010*\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0015\u0010\u0014\u001a\u00020\u0010*\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0011¨\u0006\u0015"}, d2 = {"Lio/github/anilbeesetti/nextlib/mediainfo/PathUtil;", "", "<init>", "()V", "getPath", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "getDataColumn", "selection", "selectionArgs", "", "(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/String;", "isExternalStorageDocument", "", "(Landroid/net/Uri;)Z", "isDownloadsDocument", "isMediaDocument", "isGooglePhotosUri", "mediainfo"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PathUtil {
    public static final PathUtil INSTANCE = new PathUtil();

    private PathUtil() {
    }

    /* JADX WARN: Code duplicated, block: B:55:0x0144  */
    public final String getPath(Context context, Uri uri) {
        List listEmptyList;
        Uri uri2;
        List listEmptyList2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                Intrinsics.checkNotNull(documentId);
                List<String> listSplit = new Regex(":").split(documentId, 0);
                if (!listSplit.isEmpty()) {
                    ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
                    while (true) {
                        if (listIterator.hasPrevious()) {
                            if (listIterator.previous().length() != 0) {
                                listEmptyList2 = CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                                break;
                            }
                        } else {
                            listEmptyList2 = CollectionsKt.emptyList();
                            break;
                        }
                    }
                } else {
                    listEmptyList2 = CollectionsKt.emptyList();
                    break;
                }
                String[] strArr = (String[]) listEmptyList2.toArray(new String[0]);
                if (StringsKt.equals("primary", strArr[0], true)) {
                    return Environment.getExternalStorageDirectory().getPath() + "/" + strArr[1];
                }
            } else {
                if (isDownloadsDocument(uri)) {
                    String documentId2 = DocumentsContract.getDocumentId(uri);
                    try {
                        Uri uri3 = Uri.parse("content://downloads/public_downloads");
                        Intrinsics.checkNotNull(documentId2);
                        Uri uriWithAppendedId = ContentUris.withAppendedId(uri3, Long.parseLong(documentId2));
                        Intrinsics.checkNotNullExpressionValue(uriWithAppendedId, "withAppendedId(...)");
                        return getDataColumn(context, uriWithAppendedId, null, null);
                    } catch (Exception unused) {
                        return null;
                    }
                }
                if (isMediaDocument(uri)) {
                    String documentId3 = DocumentsContract.getDocumentId(uri);
                    Intrinsics.checkNotNull(documentId3);
                    List<String> listSplit2 = new Regex(":").split(documentId3, 0);
                    if (!listSplit2.isEmpty()) {
                        ListIterator<String> listIterator2 = listSplit2.listIterator(listSplit2.size());
                        while (true) {
                            if (listIterator2.hasPrevious()) {
                                if (listIterator2.previous().length() != 0) {
                                    listEmptyList = CollectionsKt.take(listSplit2, listIterator2.nextIndex() + 1);
                                    break;
                                }
                            } else {
                                listEmptyList = CollectionsKt.emptyList();
                                break;
                            }
                        }
                    } else {
                        listEmptyList = CollectionsKt.emptyList();
                        break;
                    }
                    String[] strArr2 = (String[]) listEmptyList.toArray(new String[0]);
                    String str = strArr2[0];
                    int iHashCode = str.hashCode();
                    if (iHashCode != 93166550) {
                        if (iHashCode != 100313435) {
                            if (iHashCode == 112202875 && str.equals("video")) {
                                uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                            } else {
                                uri2 = null;
                            }
                        } else if (str.equals("image")) {
                            uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        } else {
                            uri2 = null;
                        }
                    } else if (str.equals("audio")) {
                        uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    } else {
                        uri2 = null;
                    }
                    String[] strArr3 = {strArr2[1]};
                    if (uri2 != null) {
                        return INSTANCE.getDataColumn(context, uri2, "_id=?", strArr3);
                    }
                    return null;
                }
            }
        } else {
            if (StringsKt.equals("content", uri.getScheme(), true)) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(context, uri, null, null);
            }
            if (StringsKt.equals(UtilsKt.SCHEME_FILE, uri.getScheme(), true)) {
                return uri.getPath();
            }
        }
        return null;
    }

    static /* synthetic */ String getDataColumn$default(PathUtil pathUtil, Context context, Uri uri, String str, String[] strArr, int i, Object obj) {
        if ((i & 4) != 0) {
            str = null;
        }
        if ((i & 8) != 0) {
            strArr = null;
        }
        return pathUtil.getDataColumn(context, uri, str, strArr);
    }

    private final String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        try {
            Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{"_data"}, selection, selectionArgs, null);
            if (cursorQuery != null) {
                Cursor cursor = cursorQuery;
                try {
                    Cursor cursor2 = cursor;
                    if (cursor2.moveToFirst()) {
                        String string = cursor2.getString(cursor2.getColumnIndexOrThrow("_data"));
                        CloseableKt.closeFinally(cursor, null);
                        return string;
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor, null);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(cursor, th);
                        throw th2;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public final boolean isExternalStorageDocument(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        return Intrinsics.areEqual("com.android.externalstorage.documents", uri.getAuthority());
    }

    public final boolean isDownloadsDocument(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        return Intrinsics.areEqual("com.android.providers.downloads.documents", uri.getAuthority());
    }

    public final boolean isMediaDocument(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        return Intrinsics.areEqual("com.android.providers.media.documents", uri.getAuthority());
    }

    public final boolean isGooglePhotosUri(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        return Intrinsics.areEqual("com.google.android.apps.photos.content", uri.getAuthority());
    }
}
