package com.google.android.mediahome.video;

import android.content.ContentProviderClient;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import kotlin.UByte$$ExternalSyntheticBackport0;

/* JADX INFO: compiled from: com.google.android.mediahome:video@@1.0.0 */
/* JADX INFO: loaded from: classes6.dex */
public class PreviewChannelHelper {
    private static final Uri zza = Uri.parse("content://com.google.android.mediahome.video");
    private final Context zzb;

    public PreviewChannelHelper(Context context) {
        this.zzb = context.getApplicationContext();
    }

    public void deleteAllPreviewProgramsByChannelId(long j) {
        this.zzb.getContentResolver().delete(VideoContract.buildPreviewProgramsUriForChannel(j), null, null);
    }

    public void deletePreviewChannel(long j) {
        this.zzb.getContentResolver().delete(VideoContract.buildChannelUri(j), null, null);
    }

    public void deletePreviewProgram(long j) {
        this.zzb.getContentResolver().delete(VideoContract.buildPreviewProgramUri(j), null, null);
    }

    public void deleteWatchNextProgram(long j) {
        this.zzb.getContentResolver().delete(VideoContract.buildWatchNextProgramUri(j), null, null);
    }

    public void deleteWatchNextPrograms() {
        this.zzb.getContentResolver().delete(VideoContract.WatchNextPrograms.CONTENT_URI, null, null);
    }

    public List<PreviewChannel> getAllChannels() {
        Cursor cursorQuery = this.zzb.getContentResolver().query(VideoContract.PreviewChannels.CONTENT_URI, PreviewChannel.Columns.PROJECTION, null, null, null);
        ArrayList arrayList = new ArrayList();
        if (cursorQuery != null) {
            if (cursorQuery.moveToFirst()) {
                do {
                    arrayList.add(PreviewChannel.fromCursor(cursorQuery));
                } while (cursorQuery.moveToNext());
            }
            cursorQuery.close();
        }
        return arrayList;
    }

    public PreviewChannel getPreviewChannel(long j) {
        Cursor cursorQuery = this.zzb.getContentResolver().query(VideoContract.buildChannelUri(j), PreviewChannel.Columns.PROJECTION, null, null, null);
        PreviewChannel previewChannelFromCursor = null;
        if (cursorQuery != null) {
            previewChannelFromCursor = cursorQuery.moveToFirst() ? PreviewChannel.fromCursor(cursorQuery) : null;
            cursorQuery.close();
        }
        return previewChannelFromCursor;
    }

    public PreviewProgram getPreviewProgram(long j) {
        Cursor cursorQuery = this.zzb.getContentResolver().query(VideoContract.buildPreviewProgramUri(j), null, null, null, null);
        PreviewProgram previewProgramFromCursor = null;
        if (cursorQuery != null) {
            previewProgramFromCursor = cursorQuery.moveToFirst() ? PreviewProgram.fromCursor(cursorQuery) : null;
            cursorQuery.close();
        }
        return previewProgramFromCursor;
    }

    public WatchNextProgram getWatchNextProgram(long j) {
        Cursor cursorQuery = this.zzb.getContentResolver().query(VideoContract.buildWatchNextProgramUri(j), null, null, null, null);
        WatchNextProgram watchNextProgramFromCursor = null;
        if (cursorQuery != null) {
            watchNextProgramFromCursor = cursorQuery.moveToFirst() ? WatchNextProgram.fromCursor(cursorQuery) : null;
            cursorQuery.close();
        }
        return watchNextProgramFromCursor;
    }

    public boolean isAvailable() throws Exception {
        ContentProviderClient contentProviderClientAcquireContentProviderClient = this.zzb.getContentResolver().acquireContentProviderClient(zza);
        if (contentProviderClientAcquireContentProviderClient == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT <= 23) {
            contentProviderClientAcquireContentProviderClient.release();
            return true;
        }
        UByte$$ExternalSyntheticBackport0.m9014m((Object) contentProviderClientAcquireContentProviderClient);
        return true;
    }

    public long publishChannel(PreviewChannel previewChannel) {
        try {
            Uri uriInsert = this.zzb.getContentResolver().insert(VideoContract.PreviewChannels.CONTENT_URI, previewChannel.toContentValues());
            if (uriInsert == null || uriInsert.equals(Uri.EMPTY)) {
                throw new NullPointerException("Channel insertion failed");
            }
            return ContentUris.parseId(uriInsert);
        } catch (SecurityException e) {
            Log.e("PreviewChannelHelper", "Your app's ability to insert data into the Provider may have been revoked.", e);
            return -1L;
        }
    }

    public long publishPreviewProgram(PreviewProgram previewProgram) {
        try {
            Uri uriInsert = this.zzb.getContentResolver().insert(VideoContract.PreviewPrograms.CONTENT_URI, previewProgram.toContentValues());
            if (uriInsert == null || uriInsert.equals(Uri.EMPTY)) {
                throw new NullPointerException("Program insertion failed");
            }
            return ContentUris.parseId(uriInsert);
        } catch (SecurityException e) {
            Log.e("PreviewChannelHelper", "Your app's ability to insert data into the Provider may have been revoked.", e);
            return -1L;
        }
    }

    public long publishWatchNextProgram(WatchNextProgram watchNextProgram) {
        try {
            Uri uriInsert = this.zzb.getContentResolver().insert(VideoContract.WatchNextPrograms.CONTENT_URI, watchNextProgram.toContentValues());
            if (uriInsert == null || uriInsert.equals(Uri.EMPTY)) {
                throw new NullPointerException("Program insertion failed");
            }
            return ContentUris.parseId(uriInsert);
        } catch (SecurityException e) {
            Log.e("PreviewChannelHelper", "Your app's ability to insert data into the Provider may have been revoked.", e);
            return -1L;
        }
    }

    public void updatePreviewChannel(long j, PreviewChannel previewChannel) {
        PreviewChannel previewChannel2 = getPreviewChannel(j);
        if (previewChannel2 == null || !previewChannel2.hasAnyUpdatedValues(previewChannel)) {
            return;
        }
        this.zzb.getContentResolver().update(VideoContract.buildChannelUri(j), previewChannel.toContentValues(), null, null);
    }

    public void updatePreviewProgram(long j, PreviewProgram previewProgram) {
        PreviewProgram previewProgram2 = getPreviewProgram(j);
        if (previewProgram2 == null || !previewProgram2.hasAnyUpdatedValues(previewProgram)) {
            return;
        }
        this.zzb.getContentResolver().update(VideoContract.buildPreviewProgramUri(j), previewProgram.toContentValues(), null, null);
    }

    public void updateWatchNextProgram(long j, WatchNextProgram watchNextProgram) {
        WatchNextProgram watchNextProgram2 = getWatchNextProgram(j);
        if (watchNextProgram2 == null || !watchNextProgram2.hasAnyUpdatedValues(watchNextProgram)) {
            return;
        }
        this.zzb.getContentResolver().update(VideoContract.buildWatchNextProgramUri(j), watchNextProgram.toContentValues(), null, null);
    }
}
