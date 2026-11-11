package androidx.media3.cast;

import android.net.Uri;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Log;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.common.images.WebImage;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class DefaultMediaItemConverter implements MediaItemConverter {
    private static final String KEY_DRM_CONFIGURATION = "drmConfiguration";
    private static final String KEY_LICENSE_URI = "licenseUri";
    private static final String KEY_MEDIA_ID = "mediaId";
    private static final String KEY_MEDIA_ITEM = "mediaItem";
    private static final String KEY_MIME_TYPE = "mimeType";
    private static final String KEY_PLAYER_CONFIG = "exoPlayerConfig";
    private static final String KEY_REQUEST_HEADERS = "requestHeaders";
    private static final String KEY_TITLE = "title";
    private static final String KEY_URI = "uri";
    private static final String KEY_UUID = "uuid";
    private static final String TAG = "DefltMediaItemConverter";

    @Override // androidx.media3.cast.MediaItemConverter
    public MediaItem toMediaItem(MediaQueueItem mediaQueueItem) {
        MediaInfo media = mediaQueueItem.getMedia();
        Preconditions.checkNotNull(media);
        MediaMetadata.Builder builder = new MediaMetadata.Builder();
        com.google.android.gms.cast.MediaMetadata metadata = media.getMetadata();
        if (metadata != null) {
            if (metadata.containsKey(com.google.android.gms.cast.MediaMetadata.KEY_TITLE)) {
                builder.setTitle(metadata.getString(com.google.android.gms.cast.MediaMetadata.KEY_TITLE));
            }
            if (metadata.containsKey(com.google.android.gms.cast.MediaMetadata.KEY_SUBTITLE)) {
                builder.setSubtitle(metadata.getString(com.google.android.gms.cast.MediaMetadata.KEY_SUBTITLE));
            }
            if (metadata.containsKey(com.google.android.gms.cast.MediaMetadata.KEY_ARTIST)) {
                builder.setArtist(metadata.getString(com.google.android.gms.cast.MediaMetadata.KEY_ARTIST));
            }
            if (metadata.containsKey(com.google.android.gms.cast.MediaMetadata.KEY_ALBUM_ARTIST)) {
                builder.setAlbumArtist(metadata.getString(com.google.android.gms.cast.MediaMetadata.KEY_ALBUM_ARTIST));
            }
            if (metadata.containsKey(com.google.android.gms.cast.MediaMetadata.KEY_ALBUM_TITLE)) {
                builder.setAlbumTitle(metadata.getString(com.google.android.gms.cast.MediaMetadata.KEY_ALBUM_TITLE));
            }
            if (!metadata.getImages().isEmpty()) {
                builder.setArtworkUri(metadata.getImages().get(0).getUrl());
            }
            if (metadata.containsKey(com.google.android.gms.cast.MediaMetadata.KEY_COMPOSER)) {
                builder.setComposer(metadata.getString(com.google.android.gms.cast.MediaMetadata.KEY_COMPOSER));
            }
            if (metadata.containsKey(com.google.android.gms.cast.MediaMetadata.KEY_DISC_NUMBER)) {
                builder.setDiscNumber(Integer.valueOf(metadata.getInt(com.google.android.gms.cast.MediaMetadata.KEY_DISC_NUMBER)));
            }
            if (metadata.containsKey(com.google.android.gms.cast.MediaMetadata.KEY_TRACK_NUMBER)) {
                builder.setTrackNumber(Integer.valueOf(metadata.getInt(com.google.android.gms.cast.MediaMetadata.KEY_TRACK_NUMBER)));
            }
        }
        return getMediaItem((JSONObject) Preconditions.checkNotNull(media.getCustomData()), builder.build());
    }

    @Override // androidx.media3.cast.MediaItemConverter
    public MediaQueueItem toMediaQueueItem(MediaItem mediaItem) {
        Preconditions.checkNotNull(mediaItem.localConfiguration);
        com.google.android.gms.cast.MediaMetadata mediaMetadata = new com.google.android.gms.cast.MediaMetadata(getMediaType(mediaItem));
        if (mediaItem.mediaMetadata.title != null) {
            mediaMetadata.putString(com.google.android.gms.cast.MediaMetadata.KEY_TITLE, mediaItem.mediaMetadata.title.toString());
        }
        if (mediaItem.mediaMetadata.subtitle != null) {
            mediaMetadata.putString(com.google.android.gms.cast.MediaMetadata.KEY_SUBTITLE, mediaItem.mediaMetadata.subtitle.toString());
        }
        if (mediaItem.mediaMetadata.artist != null) {
            mediaMetadata.putString(com.google.android.gms.cast.MediaMetadata.KEY_ARTIST, mediaItem.mediaMetadata.artist.toString());
        }
        if (mediaItem.mediaMetadata.albumArtist != null) {
            mediaMetadata.putString(com.google.android.gms.cast.MediaMetadata.KEY_ALBUM_ARTIST, mediaItem.mediaMetadata.albumArtist.toString());
        }
        if (mediaItem.mediaMetadata.albumTitle != null) {
            mediaMetadata.putString(com.google.android.gms.cast.MediaMetadata.KEY_ALBUM_TITLE, mediaItem.mediaMetadata.albumTitle.toString());
        }
        if (mediaItem.mediaMetadata.artworkUri != null) {
            mediaMetadata.addImage(new WebImage(mediaItem.mediaMetadata.artworkUri));
        }
        if (mediaItem.mediaMetadata.composer != null) {
            mediaMetadata.putString(com.google.android.gms.cast.MediaMetadata.KEY_COMPOSER, mediaItem.mediaMetadata.composer.toString());
        }
        if (mediaItem.mediaMetadata.discNumber != null) {
            mediaMetadata.putInt(com.google.android.gms.cast.MediaMetadata.KEY_DISC_NUMBER, mediaItem.mediaMetadata.discNumber.intValue());
        }
        if (mediaItem.mediaMetadata.trackNumber != null) {
            mediaMetadata.putInt(com.google.android.gms.cast.MediaMetadata.KEY_TRACK_NUMBER, mediaItem.mediaMetadata.trackNumber.intValue());
        }
        String string = mediaItem.localConfiguration.uri.toString();
        return new MediaQueueItem.Builder(new MediaInfo.Builder(mediaItem.mediaId.equals("") ? string : mediaItem.mediaId).setStreamType(1).setContentType(mediaItem.localConfiguration.mimeType).setContentUrl(string).setMetadata(mediaMetadata).setCustomData(getCustomData(mediaItem)).build()).build();
    }

    private static int getMediaType(MediaItem mediaItem) {
        Integer num = mediaItem.mediaMetadata.mediaType;
        if (num != null) {
            int iIntValue = num.intValue();
            if (iIntValue != 1) {
                if (iIntValue == 2) {
                    return 5;
                }
                if (iIntValue != 4) {
                    if (iIntValue == 7 || iIntValue == 8) {
                        return 1;
                    }
                    if (iIntValue == 9) {
                        return 2;
                    }
                }
            }
            return 3;
        }
        String str = mediaItem.localConfiguration != null ? mediaItem.localConfiguration.mimeType : null;
        if (str == null) {
            Log.w(TAG, "Converting MediaItem with null MIME type and no media type. Assuming MEDIA_TYPE_MOVIE. Song metadata may not be rendered correctly by the default receiver.");
        }
        return MimeTypes.isAudio(str) ? 3 : 1;
    }

    private static MediaItem getMediaItem(JSONObject jSONObject, MediaMetadata mediaMetadata) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(KEY_MEDIA_ITEM);
            MediaItem.Builder mediaMetadata2 = new MediaItem.Builder().setUri(Uri.parse(jSONObject2.getString(KEY_URI))).setMediaId(jSONObject2.getString(KEY_MEDIA_ID)).setMediaMetadata(mediaMetadata);
            if (jSONObject2.has(KEY_MIME_TYPE)) {
                mediaMetadata2.setMimeType(jSONObject2.getString(KEY_MIME_TYPE));
            }
            if (jSONObject2.has(KEY_DRM_CONFIGURATION)) {
                populateDrmConfiguration(jSONObject2.getJSONObject(KEY_DRM_CONFIGURATION), mediaMetadata2);
            }
            return mediaMetadata2.build();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static void populateDrmConfiguration(JSONObject jSONObject, MediaItem.Builder builder) throws JSONException {
        MediaItem.DrmConfiguration.Builder licenseUri = new MediaItem.DrmConfiguration.Builder(UUID.fromString(jSONObject.getString(KEY_UUID))).setLicenseUri(jSONObject.getString(KEY_LICENSE_URI));
        JSONObject jSONObject2 = jSONObject.getJSONObject(KEY_REQUEST_HEADERS);
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject2.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            map.put(next, jSONObject2.getString(next));
        }
        licenseUri.setLicenseRequestHeaders(map);
        builder.setDrmConfiguration(licenseUri.build());
    }

    private static JSONObject getCustomData(MediaItem mediaItem) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_MEDIA_ITEM, getMediaItemJson(mediaItem));
            JSONObject playerConfigJson = getPlayerConfigJson(mediaItem);
            if (playerConfigJson != null) {
                jSONObject.put(KEY_PLAYER_CONFIG, playerConfigJson);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static JSONObject getMediaItemJson(MediaItem mediaItem) throws JSONException {
        Preconditions.checkNotNull(mediaItem.localConfiguration);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(KEY_MEDIA_ID, mediaItem.mediaId);
        jSONObject.put("title", mediaItem.mediaMetadata.title);
        jSONObject.put(KEY_URI, mediaItem.localConfiguration.uri.toString());
        jSONObject.put(KEY_MIME_TYPE, mediaItem.localConfiguration.mimeType);
        if (mediaItem.localConfiguration.drmConfiguration != null) {
            jSONObject.put(KEY_DRM_CONFIGURATION, getDrmConfigurationJson(mediaItem.localConfiguration.drmConfiguration));
        }
        return jSONObject;
    }

    private static JSONObject getDrmConfigurationJson(MediaItem.DrmConfiguration drmConfiguration) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(KEY_UUID, drmConfiguration.scheme);
        jSONObject.put(KEY_LICENSE_URI, drmConfiguration.licenseUri);
        jSONObject.put(KEY_REQUEST_HEADERS, new JSONObject((Map<?, ?>) drmConfiguration.licenseRequestHeaders));
        return jSONObject;
    }

    private static JSONObject getPlayerConfigJson(MediaItem mediaItem) throws JSONException {
        String str;
        JSONObject jSONObject = null;
        if (mediaItem.localConfiguration != null && mediaItem.localConfiguration.drmConfiguration != null) {
            MediaItem.DrmConfiguration drmConfiguration = mediaItem.localConfiguration.drmConfiguration;
            if (C.WIDEVINE_UUID.equals(drmConfiguration.scheme)) {
                str = "widevine";
            } else if (C.PLAYREADY_UUID.equals(drmConfiguration.scheme)) {
                str = "playready";
            }
            jSONObject = new JSONObject();
            jSONObject.put("withCredentials", false);
            jSONObject.put("protectionSystem", str);
            if (drmConfiguration.licenseUri != null) {
                jSONObject.put("licenseUrl", drmConfiguration.licenseUri);
            }
            if (!drmConfiguration.licenseRequestHeaders.isEmpty()) {
                jSONObject.put("headers", new JSONObject((Map<?, ?>) drmConfiguration.licenseRequestHeaders));
            }
        }
        return jSONObject;
    }
}
