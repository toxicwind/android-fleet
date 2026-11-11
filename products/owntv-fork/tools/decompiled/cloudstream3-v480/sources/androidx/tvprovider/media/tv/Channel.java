package androidx.tvprovider.media.tv;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes5.dex */
public final class Channel {
    private static final long INVALID_CHANNEL_ID = -1;
    private static final int INVALID_INT_VALUE = -1;
    private static final int IS_BROWSABLE = 1;
    private static final int IS_LOCKED = 1;
    private static final int IS_SEARCHABLE = 1;
    private static final int IS_SYSTEM_APPROVED = 1;
    private static final int IS_TRANSIENT = 1;
    public static final String[] PROJECTION = getProjection();
    ContentValues mValues;

    Channel(Builder builder) {
        this.mValues = builder.mValues;
    }

    public long getId() {
        Long asLong = this.mValues.getAsLong("_id");
        if (asLong == null) {
            return -1L;
        }
        return asLong.longValue();
    }

    public String getPackageName() {
        return this.mValues.getAsString("package_name");
    }

    public String getInputId() {
        return this.mValues.getAsString("input_id");
    }

    public String getType() {
        return this.mValues.getAsString("type");
    }

    public String getDisplayNumber() {
        return this.mValues.getAsString(TvContractCompat.Channels.COLUMN_DISPLAY_NUMBER);
    }

    public String getDisplayName() {
        return this.mValues.getAsString("display_name");
    }

    public String getDescription() {
        return this.mValues.getAsString("description");
    }

    public String getVideoFormat() {
        return this.mValues.getAsString(TvContractCompat.Channels.COLUMN_VIDEO_FORMAT);
    }

    public int getOriginalNetworkId() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.Channels.COLUMN_ORIGINAL_NETWORK_ID);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public int getTransportStreamId() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.Channels.COLUMN_TRANSPORT_STREAM_ID);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public int getServiceId() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.Channels.COLUMN_SERVICE_ID);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public String getAppLinkText() {
        return this.mValues.getAsString("app_link_text");
    }

    public int getAppLinkColor() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.Channels.COLUMN_APP_LINK_COLOR);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public Uri getAppLinkIconUri() {
        String asString = this.mValues.getAsString("app_link_icon_uri");
        if (asString == null) {
            return null;
        }
        return Uri.parse(asString);
    }

    public Uri getAppLinkPosterArtUri() {
        String asString = this.mValues.getAsString(TvContractCompat.Channels.COLUMN_APP_LINK_POSTER_ART_URI);
        if (asString == null) {
            return null;
        }
        return Uri.parse(asString);
    }

    public Uri getAppLinkIntentUri() {
        String asString = this.mValues.getAsString("app_link_intent_uri");
        if (asString == null) {
            return null;
        }
        return Uri.parse(asString);
    }

    public Intent getAppLinkIntent() throws URISyntaxException {
        String asString = this.mValues.getAsString("app_link_intent_uri");
        if (asString == null) {
            return null;
        }
        return Intent.parseUri(asString.toString(), 1);
    }

    public String getNetworkAffiliation() {
        return this.mValues.getAsString(TvContractCompat.Channels.COLUMN_NETWORK_AFFILIATION);
    }

    public boolean isSearchable() {
        Integer asInteger = this.mValues.getAsInteger("searchable");
        return asInteger == null || asInteger.intValue() == 1;
    }

    public byte[] getInternalProviderDataByteArray() {
        return this.mValues.getAsByteArray("internal_provider_data");
    }

    public String getServiceType() {
        return this.mValues.getAsString(TvContractCompat.Channels.COLUMN_SERVICE_TYPE);
    }

    public Long getInternalProviderFlag1() {
        return this.mValues.getAsLong("internal_provider_flag1");
    }

    public Long getInternalProviderFlag2() {
        return this.mValues.getAsLong("internal_provider_flag2");
    }

    public Long getInternalProviderFlag3() {
        return this.mValues.getAsLong("internal_provider_flag3");
    }

    public Long getInternalProviderFlag4() {
        return this.mValues.getAsLong("internal_provider_flag4");
    }

    public String getInternalProviderId() {
        return this.mValues.getAsString("internal_provider_id");
    }

    public boolean isTransient() {
        Integer asInteger = this.mValues.getAsInteger("transient");
        return asInteger != null && asInteger.intValue() == 1;
    }

    public boolean isBrowsable() {
        Integer asInteger = this.mValues.getAsInteger("browsable");
        return asInteger != null && asInteger.intValue() == 1;
    }

    public boolean isSystemApproved() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.Channels.COLUMN_SYSTEM_APPROVED);
        return asInteger != null && asInteger.intValue() == 1;
    }

    public int getConfigurationDisplayOrder() {
        return this.mValues.getAsInteger(TvContractCompat.Channels.COLUMN_CONFIGURATION_DISPLAY_ORDER).intValue();
    }

    public String getSystemChannelKey() {
        return this.mValues.getAsString(TvContractCompat.Channels.COLUMN_SYSTEM_CHANNEL_KEY);
    }

    public String getGlobalContentId() {
        return this.mValues.getAsString("global_content_id");
    }

    public boolean isLocked() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.Channels.COLUMN_LOCKED);
        return asInteger != null && asInteger.intValue() == 1;
    }

    public int hashCode() {
        return this.mValues.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Channel) {
            return this.mValues.equals(((Channel) obj).mValues);
        }
        return false;
    }

    public String toString() {
        return "Channel{" + this.mValues.toString() + "}";
    }

    public ContentValues toContentValues() {
        return toContentValues(false);
    }

    public ContentValues toContentValues(boolean z) {
        ContentValues contentValues = new ContentValues(this.mValues);
        if (Build.VERSION.SDK_INT < 26) {
            contentValues.remove("internal_provider_id");
            contentValues.remove("transient");
            contentValues.remove(TvContractCompat.Channels.COLUMN_CONFIGURATION_DISPLAY_ORDER);
            contentValues.remove(TvContractCompat.Channels.COLUMN_SYSTEM_CHANNEL_KEY);
        }
        if (Build.VERSION.SDK_INT < 30) {
            contentValues.remove("global_content_id");
        }
        if (!z) {
            contentValues.remove("browsable");
            contentValues.remove(TvContractCompat.Channels.COLUMN_LOCKED);
        }
        if (Build.VERSION.SDK_INT >= 26 && z) {
            return contentValues;
        }
        contentValues.remove(TvContractCompat.Channels.COLUMN_SYSTEM_APPROVED);
        return contentValues;
    }

    public static Channel fromCursor(Cursor cursor) {
        int columnIndex;
        Builder builder = new Builder();
        int columnIndex2 = cursor.getColumnIndex("_id");
        if (columnIndex2 >= 0 && !cursor.isNull(columnIndex2)) {
            builder.setId(cursor.getLong(columnIndex2));
        }
        int columnIndex3 = cursor.getColumnIndex("description");
        if (columnIndex3 >= 0 && !cursor.isNull(columnIndex3)) {
            builder.setDescription(cursor.getString(columnIndex3));
        }
        int columnIndex4 = cursor.getColumnIndex("display_name");
        if (columnIndex4 >= 0 && !cursor.isNull(columnIndex4)) {
            builder.setDisplayName(cursor.getString(columnIndex4));
        }
        int columnIndex5 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_DISPLAY_NUMBER);
        if (columnIndex5 >= 0 && !cursor.isNull(columnIndex5)) {
            builder.setDisplayNumber(cursor.getString(columnIndex5));
        }
        int columnIndex6 = cursor.getColumnIndex("input_id");
        if (columnIndex6 >= 0 && !cursor.isNull(columnIndex6)) {
            builder.setInputId(cursor.getString(columnIndex6));
        }
        int columnIndex7 = cursor.getColumnIndex("internal_provider_data");
        if (columnIndex7 >= 0 && !cursor.isNull(columnIndex7)) {
            builder.setInternalProviderData(cursor.getBlob(columnIndex7));
        }
        int columnIndex8 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_NETWORK_AFFILIATION);
        if (columnIndex8 >= 0 && !cursor.isNull(columnIndex8)) {
            builder.setNetworkAffiliation(cursor.getString(columnIndex8));
        }
        int columnIndex9 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_ORIGINAL_NETWORK_ID);
        if (columnIndex9 >= 0 && !cursor.isNull(columnIndex9)) {
            builder.setOriginalNetworkId(cursor.getInt(columnIndex9));
        }
        int columnIndex10 = cursor.getColumnIndex("package_name");
        if (columnIndex10 >= 0 && !cursor.isNull(columnIndex10)) {
            builder.setPackageName(cursor.getString(columnIndex10));
        }
        int columnIndex11 = cursor.getColumnIndex("searchable");
        if (columnIndex11 >= 0 && !cursor.isNull(columnIndex11)) {
            builder.setSearchable(cursor.getInt(columnIndex11) == 1);
        }
        int columnIndex12 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_SERVICE_ID);
        if (columnIndex12 >= 0 && !cursor.isNull(columnIndex12)) {
            builder.setServiceId(cursor.getInt(columnIndex12));
        }
        int columnIndex13 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_SERVICE_TYPE);
        if (columnIndex13 >= 0 && !cursor.isNull(columnIndex13)) {
            builder.setServiceType(cursor.getString(columnIndex13));
        }
        int columnIndex14 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_TRANSPORT_STREAM_ID);
        if (columnIndex14 >= 0 && !cursor.isNull(columnIndex14)) {
            builder.setTransportStreamId(cursor.getInt(columnIndex14));
        }
        int columnIndex15 = cursor.getColumnIndex("type");
        if (columnIndex15 >= 0 && !cursor.isNull(columnIndex15)) {
            builder.setType(cursor.getString(columnIndex15));
        }
        int columnIndex16 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_VIDEO_FORMAT);
        if (columnIndex16 >= 0 && !cursor.isNull(columnIndex16)) {
            builder.setVideoFormat(cursor.getString(columnIndex16));
        }
        int columnIndex17 = cursor.getColumnIndex("browsable");
        if (columnIndex17 >= 0 && !cursor.isNull(columnIndex17)) {
            builder.setBrowsable(cursor.getInt(columnIndex17) == 1);
        }
        int columnIndex18 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_LOCKED);
        if (columnIndex18 >= 0 && !cursor.isNull(columnIndex18)) {
            builder.setLocked(cursor.getInt(columnIndex18) == 1);
        }
        int columnIndex19 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_APP_LINK_COLOR);
        if (columnIndex19 >= 0 && !cursor.isNull(columnIndex19)) {
            builder.setAppLinkColor(cursor.getInt(columnIndex19));
        }
        int columnIndex20 = cursor.getColumnIndex("app_link_icon_uri");
        if (columnIndex20 >= 0 && !cursor.isNull(columnIndex20)) {
            builder.setAppLinkIconUri(Uri.parse(cursor.getString(columnIndex20)));
        }
        int columnIndex21 = cursor.getColumnIndex("app_link_intent_uri");
        if (columnIndex21 >= 0 && !cursor.isNull(columnIndex21)) {
            builder.setAppLinkIntentUri(Uri.parse(cursor.getString(columnIndex21)));
        }
        int columnIndex22 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_APP_LINK_POSTER_ART_URI);
        if (columnIndex22 >= 0 && !cursor.isNull(columnIndex22)) {
            builder.setAppLinkPosterArtUri(Uri.parse(cursor.getString(columnIndex22)));
        }
        int columnIndex23 = cursor.getColumnIndex("app_link_text");
        if (columnIndex23 >= 0 && !cursor.isNull(columnIndex23)) {
            builder.setAppLinkText(cursor.getString(columnIndex23));
        }
        int columnIndex24 = cursor.getColumnIndex("internal_provider_flag1");
        if (columnIndex24 >= 0 && !cursor.isNull(columnIndex24)) {
            builder.setInternalProviderFlag1(cursor.getLong(columnIndex24));
        }
        int columnIndex25 = cursor.getColumnIndex("internal_provider_flag2");
        if (columnIndex25 >= 0 && !cursor.isNull(columnIndex25)) {
            builder.setInternalProviderFlag2(cursor.getLong(columnIndex25));
        }
        int columnIndex26 = cursor.getColumnIndex("internal_provider_flag3");
        if (columnIndex26 >= 0 && !cursor.isNull(columnIndex26)) {
            builder.setInternalProviderFlag3(cursor.getLong(columnIndex26));
        }
        int columnIndex27 = cursor.getColumnIndex("internal_provider_flag4");
        if (columnIndex27 >= 0 && !cursor.isNull(columnIndex27)) {
            builder.setInternalProviderFlag4(cursor.getLong(columnIndex27));
        }
        if (Build.VERSION.SDK_INT >= 26) {
            int columnIndex28 = cursor.getColumnIndex("internal_provider_id");
            if (columnIndex28 >= 0 && !cursor.isNull(columnIndex28)) {
                builder.setInternalProviderId(cursor.getString(columnIndex28));
            }
            int columnIndex29 = cursor.getColumnIndex("transient");
            if (columnIndex29 >= 0 && !cursor.isNull(columnIndex29)) {
                builder.setTransient(cursor.getInt(columnIndex29) == 1);
            }
            int columnIndex30 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_SYSTEM_APPROVED);
            if (columnIndex30 >= 0 && !cursor.isNull(columnIndex30)) {
                builder.setSystemApproved(cursor.getInt(columnIndex30) == 1);
            }
            int columnIndex31 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_CONFIGURATION_DISPLAY_ORDER);
            if (columnIndex31 >= 0 && !cursor.isNull(columnIndex31)) {
                builder.setConfigurationDisplayOrder(cursor.getInt(columnIndex31));
            }
            int columnIndex32 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_SYSTEM_CHANNEL_KEY);
            if (columnIndex32 >= 0 && !cursor.isNull(columnIndex32)) {
                builder.setSystemChannelKey(cursor.getString(columnIndex32));
            }
        }
        if (Build.VERSION.SDK_INT >= 30 && (columnIndex = cursor.getColumnIndex("global_content_id")) >= 0 && !cursor.isNull(columnIndex)) {
            builder.setGlobalContentId(cursor.getString(columnIndex));
        }
        return builder.build();
    }

    private static String[] getProjection() {
        String[] strArr = {"_id", "description", "display_name", TvContractCompat.Channels.COLUMN_DISPLAY_NUMBER, "input_id", "internal_provider_data", TvContractCompat.Channels.COLUMN_NETWORK_AFFILIATION, TvContractCompat.Channels.COLUMN_ORIGINAL_NETWORK_ID, "package_name", "searchable", TvContractCompat.Channels.COLUMN_SERVICE_ID, TvContractCompat.Channels.COLUMN_SERVICE_TYPE, TvContractCompat.Channels.COLUMN_TRANSPORT_STREAM_ID, "type", TvContractCompat.Channels.COLUMN_VIDEO_FORMAT, "browsable", TvContractCompat.Channels.COLUMN_LOCKED};
        String[] strArr2 = {TvContractCompat.Channels.COLUMN_APP_LINK_COLOR, "app_link_icon_uri", "app_link_intent_uri", TvContractCompat.Channels.COLUMN_APP_LINK_POSTER_ART_URI, "app_link_text", "internal_provider_flag1", "internal_provider_flag2", "internal_provider_flag3", "internal_provider_flag4"};
        String[] strArr3 = {"internal_provider_id", "transient", TvContractCompat.Channels.COLUMN_SYSTEM_APPROVED, TvContractCompat.Channels.COLUMN_CONFIGURATION_DISPLAY_ORDER, TvContractCompat.Channels.COLUMN_SYSTEM_CHANNEL_KEY};
        if (Build.VERSION.SDK_INT >= 30) {
            return (String[]) CollectionUtils.concatAll(strArr, strArr2, strArr3, new String[]{"global_content_id"});
        }
        return Build.VERSION.SDK_INT >= 26 ? (String[]) CollectionUtils.concatAll(strArr, strArr2, strArr3) : (String[]) CollectionUtils.concatAll(strArr, strArr2);
    }

    public static final class Builder {
        ContentValues mValues;

        public Builder() {
            this.mValues = new ContentValues();
        }

        public Builder(Channel channel) {
            this.mValues = new ContentValues(channel.mValues);
        }

        Builder setId(long j) {
            this.mValues.put("_id", Long.valueOf(j));
            return this;
        }

        Builder setPackageName(String str) {
            this.mValues.put("package_name", str);
            return this;
        }

        public Builder setInputId(String str) {
            this.mValues.put("input_id", str);
            return this;
        }

        public Builder setType(String str) {
            this.mValues.put("type", str);
            return this;
        }

        public Builder setDisplayNumber(String str) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_DISPLAY_NUMBER, str);
            return this;
        }

        public Builder setDisplayName(String str) {
            this.mValues.put("display_name", str);
            return this;
        }

        public Builder setDescription(String str) {
            this.mValues.put("description", str);
            return this;
        }

        public Builder setVideoFormat(String str) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_VIDEO_FORMAT, str);
            return this;
        }

        public Builder setOriginalNetworkId(int i) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_ORIGINAL_NETWORK_ID, Integer.valueOf(i));
            return this;
        }

        public Builder setTransportStreamId(int i) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_TRANSPORT_STREAM_ID, Integer.valueOf(i));
            return this;
        }

        public Builder setServiceId(int i) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_SERVICE_ID, Integer.valueOf(i));
            return this;
        }

        public Builder setInternalProviderData(byte[] bArr) {
            this.mValues.put("internal_provider_data", bArr);
            return this;
        }

        public Builder setInternalProviderData(String str) {
            this.mValues.put("internal_provider_data", str.getBytes(Charset.defaultCharset()));
            return this;
        }

        public Builder setAppLinkText(String str) {
            this.mValues.put("app_link_text", str);
            return this;
        }

        public Builder setAppLinkColor(int i) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_APP_LINK_COLOR, Integer.valueOf(i));
            return this;
        }

        public Builder setAppLinkIconUri(Uri uri) {
            this.mValues.put("app_link_icon_uri", uri == null ? null : uri.toString());
            return this;
        }

        public Builder setAppLinkPosterArtUri(Uri uri) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_APP_LINK_POSTER_ART_URI, uri == null ? null : uri.toString());
            return this;
        }

        public Builder setAppLinkIntent(Intent intent) {
            return setAppLinkIntentUri(Uri.parse(intent.toUri(1)));
        }

        public Builder setAppLinkIntentUri(Uri uri) {
            this.mValues.put("app_link_intent_uri", uri == null ? null : uri.toString());
            return this;
        }

        public Builder setNetworkAffiliation(String str) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_NETWORK_AFFILIATION, str);
            return this;
        }

        public Builder setSearchable(boolean z) {
            this.mValues.put("searchable", Integer.valueOf(z ? 1 : 0));
            return this;
        }

        public Builder setServiceType(String str) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_SERVICE_TYPE, str);
            return this;
        }

        public Builder setInternalProviderFlag1(long j) {
            this.mValues.put("internal_provider_flag1", Long.valueOf(j));
            return this;
        }

        public Builder setInternalProviderFlag2(long j) {
            this.mValues.put("internal_provider_flag2", Long.valueOf(j));
            return this;
        }

        public Builder setInternalProviderFlag3(long j) {
            this.mValues.put("internal_provider_flag3", Long.valueOf(j));
            return this;
        }

        public Builder setInternalProviderFlag4(long j) {
            this.mValues.put("internal_provider_flag4", Long.valueOf(j));
            return this;
        }

        public Builder setInternalProviderId(String str) {
            this.mValues.put("internal_provider_id", str);
            return this;
        }

        public Builder setTransient(boolean z) {
            this.mValues.put("transient", Integer.valueOf(z ? 1 : 0));
            return this;
        }

        public Builder setBrowsable(boolean z) {
            this.mValues.put("browsable", Integer.valueOf(z ? 1 : 0));
            return this;
        }

        public Builder setSystemApproved(boolean z) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_SYSTEM_APPROVED, Integer.valueOf(z ? 1 : 0));
            return this;
        }

        public Builder setConfigurationDisplayOrder(int i) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_CONFIGURATION_DISPLAY_ORDER, Integer.valueOf(i));
            return this;
        }

        public Builder setSystemChannelKey(String str) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_SYSTEM_CHANNEL_KEY, str);
            return this;
        }

        public Builder setGlobalContentId(String str) {
            this.mValues.put("global_content_id", str);
            return this;
        }

        public Builder setLocked(boolean z) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_LOCKED, Integer.valueOf(z ? 1 : 0));
            return this;
        }

        public Channel build() {
            return new Channel(this);
        }
    }
}
