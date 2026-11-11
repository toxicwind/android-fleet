package com.hippo.unifile;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import kotlin.UByte$$ExternalSyntheticBackport0;

/* JADX INFO: loaded from: classes6.dex */
final class DocumentsContractApi21 {
    private static final String PATH_DOCUMENT = "document";
    private static final String PATH_TREE = "tree";
    private static final String TAG = "DocumentsContractApi21";

    private DocumentsContractApi21() {
    }

    public static boolean isTreeDocumentUri(Context context, Uri uri) {
        if (DocumentsContractApi19.isContentUri(uri) && DocumentsContractApi19.isDocumentsProvider(context, uri.getAuthority())) {
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments.size() == 2) {
                return PATH_TREE.equals(pathSegments.get(0));
            }
            if (pathSegments.size() == 4 && PATH_TREE.equals(pathSegments.get(0)) && PATH_DOCUMENT.equals(pathSegments.get(2))) {
                return true;
            }
        }
        return false;
    }

    public static Uri createFile(Context context, Uri uri, String str, String str2) {
        try {
            return DocumentsContract.createDocument(context.getContentResolver(), uri, str, str2);
        } catch (Exception e) {
            Log.e(TAG, "Failed to createFile", e);
            return null;
        }
    }

    public static Uri createDirectory(Context context, Uri uri, String str) {
        return createFile(context, uri, "vnd.android.document/directory", str);
    }

    public static Uri prepareTreeUri(Uri uri) {
        String treeDocumentId;
        try {
            treeDocumentId = DocumentsContract.getDocumentId(uri);
            if (treeDocumentId == null) {
                throw new IllegalArgumentException();
            }
        } catch (Exception unused) {
            treeDocumentId = DocumentsContract.getTreeDocumentId(uri);
        }
        return DocumentsContract.buildDocumentUriUsingTree(uri, treeDocumentId);
    }

    public static Uri[] listFiles(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri uriBuildChildDocumentsUriUsingTree = DocumentsContract.buildChildDocumentsUriUsingTree(uri, DocumentsContract.getDocumentId(uri));
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            cursorQuery = contentResolver.query(uriBuildChildDocumentsUriUsingTree, new String[]{"document_id"}, null, null, null);
            if (cursorQuery != null) {
                while (cursorQuery.moveToNext()) {
                    arrayList.add(DocumentsContract.buildDocumentUriUsingTree(uri, cursorQuery.getString(0)));
                }
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            closeQuietly(cursorQuery);
            throw th;
        }
        closeQuietly(cursorQuery);
        return (Uri[]) arrayList.toArray(new Uri[arrayList.size()]);
    }

    public static NamedUri[] listFilesNamed(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri uriBuildChildDocumentsUriUsingTree = DocumentsContract.buildChildDocumentsUriUsingTree(uri, DocumentsContract.getDocumentId(uri));
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            cursorQuery = contentResolver.query(uriBuildChildDocumentsUriUsingTree, new String[]{"document_id", "_display_name"}, null, null, null);
            if (cursorQuery != null) {
                while (cursorQuery.moveToNext()) {
                    String string = cursorQuery.getString(0);
                    arrayList.add(new NamedUri(DocumentsContract.buildDocumentUriUsingTree(uri, string), cursorQuery.getString(1)));
                }
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            closeQuietly(cursorQuery);
            throw th;
        }
        closeQuietly(cursorQuery);
        return (NamedUri[]) arrayList.toArray(new NamedUri[arrayList.size()]);
    }

    public static Uri renameTo(Context context, Uri uri, String str) {
        try {
            return DocumentsContract.renameDocument(context.getContentResolver(), uri, str);
        } catch (Exception e) {
            Log.e(TAG, "Failed to renameTo", e);
            return null;
        }
    }

    private static void closeQuietly(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                UByte$$ExternalSyntheticBackport0.m9014m((Object) autoCloseable);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }
}
