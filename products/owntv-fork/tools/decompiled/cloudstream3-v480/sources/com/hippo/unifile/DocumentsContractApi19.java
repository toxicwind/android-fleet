package com.hippo.unifile;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
final class DocumentsContractApi19 {
    private static final String AUTHORITY_DOCUMENT_DOWNLOAD = "com.android.providers.downloads.documents";
    private static final String AUTHORITY_DOCUMENT_EXTERNAL_STORAGE = "com.android.externalstorage.documents";
    private static final String AUTHORITY_DOCUMENT_MEDIA = "com.android.providers.media.documents";
    private static final String PATH_DOCUMENT = "document";
    private static final String PATH_TREE = "tree";
    private static final String PROVIDER_INTERFACE = "android.content.action.DOCUMENTS_PROVIDER";
    private static final String TAG = "DocumentsContractApi19";

    private DocumentsContractApi19() {
    }

    public static boolean isContentUri(Uri uri) {
        return uri != null && "content".equals(uri.getScheme());
    }

    public static boolean isDocumentsProvider(Context context, String str) {
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentContentProviders(new Intent(PROVIDER_INTERFACE), 0).iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().providerInfo.authority)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDocumentUri(Context context, Uri uri) {
        if (isContentUri(uri) && isDocumentsProvider(context, uri.getAuthority())) {
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments.size() == 2) {
                return PATH_DOCUMENT.equals(pathSegments.get(0)) || PATH_TREE.equals(pathSegments.get(0));
            }
            if (pathSegments.size() == 4 && PATH_TREE.equals(pathSegments.get(0)) && PATH_DOCUMENT.equals(pathSegments.get(2))) {
                return true;
            }
        }
        return false;
    }

    public static String getName(Context context, Uri uri) {
        return Contracts.queryForString(context, uri, "_display_name", null);
    }

    private static String getRawType(Context context, Uri uri) {
        return Contracts.queryForString(context, uri, "mime_type", null);
    }

    public static String getType(Context context, Uri uri) {
        String rawType = getRawType(context, uri);
        if ("vnd.android.document/directory".equals(rawType)) {
            return null;
        }
        return rawType;
    }

    public static String getFilePath(Context context, Uri uri) {
        Uri uri2;
        if (uri == null) {
            return null;
        }
        try {
            String authority = uri.getAuthority();
            if (AUTHORITY_DOCUMENT_EXTERNAL_STORAGE.equals(authority)) {
                String[] strArrSplit = DocumentsContract.getDocumentId(uri).split(":");
                String str = strArrSplit[0];
                String str2 = strArrSplit[1];
                if ("primary".equalsIgnoreCase(str)) {
                    return Environment.getExternalStorageDirectory() + "/" + str2;
                }
                String strSubstring = null;
                for (File file : context.getExternalCacheDirs()) {
                    String path = file.getPath();
                    int iIndexOf = path.indexOf(str);
                    if (iIndexOf >= 0) {
                        strSubstring = path.substring(0, iIndexOf + str.length());
                    }
                }
                if (strSubstring == null) {
                    return null;
                }
                return strSubstring + "/" + str2;
            }
            if (AUTHORITY_DOCUMENT_DOWNLOAD.equals(authority)) {
                return Contracts.queryForString(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), "_data", null);
            }
            if (AUTHORITY_DOCUMENT_MEDIA.equals(authority)) {
                String[] strArrSplit2 = DocumentsContract.getDocumentId(uri).split(":");
                String str3 = strArrSplit2[0];
                String str4 = strArrSplit2[1];
                if ("image".equals(str3)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str3)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(str3)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                } else {
                    Log.d(TAG, "Unknown type in com.android.providers.media.documents: " + str3);
                }
                return Contracts.queryForString(context, ContentUris.withAppendedId(uri2, Long.valueOf(str4).longValue()), "_data", null);
            }
            return null;
        } catch (Exception unused) {
        }
    }

    public static boolean isDirectory(Context context, Uri uri) {
        return "vnd.android.document/directory".equals(getRawType(context, uri));
    }

    public static boolean isFile(Context context, Uri uri) {
        String rawType = getRawType(context, uri);
        return ("vnd.android.document/directory".equals(rawType) || TextUtils.isEmpty(rawType)) ? false : true;
    }

    public static long lastModified(Context context, Uri uri) {
        return Contracts.queryForLong(context, uri, "last_modified", -1L);
    }

    public static long length(Context context, Uri uri) {
        return Contracts.queryForLong(context, uri, "_size", -1L);
    }

    public static boolean canRead(Context context, Uri uri) {
        if (context.checkCallingOrSelfUriPermission(uri, 1) != 0) {
            return false;
        }
        return !TextUtils.isEmpty(getRawType(context, uri));
    }

    public static boolean canWrite(Context context, Uri uri) {
        if (context.checkCallingOrSelfUriPermission(uri, 2) != 0) {
            return false;
        }
        String rawType = getRawType(context, uri);
        int iQueryForInt = Contracts.queryForInt(context, uri, "flags", 0);
        if (TextUtils.isEmpty(rawType)) {
            return false;
        }
        if ((iQueryForInt & 4) != 0) {
            return true;
        }
        if (!"vnd.android.document/directory".equals(rawType) || (iQueryForInt & 8) == 0) {
            return (TextUtils.isEmpty(rawType) || (iQueryForInt & 2) == 0) ? false : true;
        }
        return true;
    }

    public static boolean delete(Context context, Uri uri) {
        try {
            return DocumentsContract.deleteDocument(context.getContentResolver(), uri);
        } catch (Exception e) {
            Log.e(TAG, "Failed to renameTo", e);
            return false;
        }
    }

    public static boolean exists(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        boolean z = false;
        Cursor cursorQuery = null;
        try {
            cursorQuery = contentResolver.query(uri, new String[]{"document_id"}, null, null, null);
            if (cursorQuery != null && cursorQuery.getCount() > 0) {
                z = true;
            }
            return z;
        } catch (Exception unused) {
            return false;
        } finally {
            Contracts.closeQuietly(cursorQuery);
        }
    }
}
