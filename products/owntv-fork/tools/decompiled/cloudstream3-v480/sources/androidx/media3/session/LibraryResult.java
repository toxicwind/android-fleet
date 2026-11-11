package androidx.media3.session;

import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.core.app.BundleCompat;
import androidx.media3.common.BundleListRetriever;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Util;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class LibraryResult<V> {
    public static final int RESULT_ERROR_BAD_VALUE = -3;
    public static final int RESULT_ERROR_INVALID_STATE = -2;
    public static final int RESULT_ERROR_IO = -5;
    public static final int RESULT_ERROR_NOT_SUPPORTED = -6;
    public static final int RESULT_ERROR_PERMISSION_DENIED = -4;
    public static final int RESULT_ERROR_SESSION_AUTHENTICATION_EXPIRED = -102;
    public static final int RESULT_ERROR_SESSION_CONCURRENT_STREAM_LIMIT = -104;
    public static final int RESULT_ERROR_SESSION_DISCONNECTED = -100;
    public static final int RESULT_ERROR_SESSION_NOT_AVAILABLE_IN_REGION = -106;
    public static final int RESULT_ERROR_SESSION_PARENTAL_CONTROL_RESTRICTED = -105;
    public static final int RESULT_ERROR_SESSION_PREMIUM_ACCOUNT_REQUIRED = -103;
    public static final int RESULT_ERROR_SESSION_SETUP_REQUIRED = -108;
    public static final int RESULT_ERROR_SESSION_SKIP_LIMIT_REACHED = -107;
    public static final int RESULT_ERROR_UNKNOWN = -1;
    public static final int RESULT_INFO_SKIPPED = 1;
    public static final int RESULT_SUCCESS = 0;
    private static final int VALUE_TYPE_ERROR = 4;
    private static final int VALUE_TYPE_ITEM = 2;
    private static final int VALUE_TYPE_ITEM_LIST = 3;
    private static final int VALUE_TYPE_VOID = 1;
    public final long completionTimeMs;
    public final MediaLibraryService.LibraryParams params;
    public final int resultCode;
    public final SessionError sessionError;
    public final V value;
    private final int valueType;
    private static final String FIELD_RESULT_CODE = Util.intToStringMaxRadix(0);
    private static final String FIELD_COMPLETION_TIME_MS = Util.intToStringMaxRadix(1);
    private static final String FIELD_PARAMS = Util.intToStringMaxRadix(2);
    private static final String FIELD_VALUE = Util.intToStringMaxRadix(3);
    private static final String FIELD_VALUE_TYPE = Util.intToStringMaxRadix(4);
    private static final String FIELD_SESSION_ERROR = Util.intToStringMaxRadix(5);

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Code {
    }

    public static LibraryResult<Void> ofVoid() {
        return new LibraryResult<>(0, SystemClock.elapsedRealtime(), null, null, null, 1);
    }

    public static LibraryResult<Void> ofVoid(MediaLibraryService.LibraryParams libraryParams) {
        return new LibraryResult<>(0, SystemClock.elapsedRealtime(), libraryParams, null, null, 1);
    }

    public static LibraryResult<MediaItem> ofItem(MediaItem mediaItem, MediaLibraryService.LibraryParams libraryParams) {
        verifyMediaItem(mediaItem);
        return new LibraryResult<>(0, SystemClock.elapsedRealtime(), libraryParams, null, mediaItem, 2);
    }

    public static LibraryResult<ImmutableList<MediaItem>> ofItemList(List<MediaItem> list, MediaLibraryService.LibraryParams libraryParams) {
        Iterator<MediaItem> it = list.iterator();
        while (it.hasNext()) {
            verifyMediaItem(it.next());
        }
        return new LibraryResult<>(0, SystemClock.elapsedRealtime(), libraryParams, null, ImmutableList.copyOf((Collection) list), 3);
    }

    public static <V> LibraryResult<V> ofError(int i) {
        return ofError(new SessionError(i, "no error message provided", Bundle.EMPTY));
    }

    public static <V> LibraryResult<V> ofError(int i, MediaLibraryService.LibraryParams libraryParams) {
        return new LibraryResult<>(i, SystemClock.elapsedRealtime(), libraryParams, new SessionError(i, "no error message provided", Bundle.EMPTY), null, 4);
    }

    public static <V> LibraryResult<V> ofError(SessionError sessionError) {
        return new LibraryResult<>(sessionError.code, SystemClock.elapsedRealtime(), null, sessionError, null, 4);
    }

    public static <V> LibraryResult<V> ofError(SessionError sessionError, MediaLibraryService.LibraryParams libraryParams) {
        return new LibraryResult<>(sessionError.code, SystemClock.elapsedRealtime(), libraryParams, sessionError, null, 4);
    }

    private LibraryResult(int i, long j, MediaLibraryService.LibraryParams libraryParams, SessionError sessionError, V v, int i2) {
        this.resultCode = i;
        this.completionTimeMs = j;
        this.params = libraryParams;
        this.sessionError = sessionError;
        this.value = v;
        this.valueType = i2;
    }

    private static void verifyMediaItem(MediaItem mediaItem) {
        Preconditions.checkArgument(!TextUtils.isEmpty(mediaItem.mediaId), "mediaId must not be empty");
        Preconditions.checkArgument(mediaItem.mediaMetadata.isBrowsable != null, "mediaMetadata must specify isBrowsable");
        Preconditions.checkArgument(mediaItem.mediaMetadata.isPlayable != null, "mediaMetadata must specify isPlayable");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0045, code lost:
    
        if (r2 != 4) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.os.Bundle toBundle() {
        /*
            r4 = this;
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r1 = androidx.media3.session.LibraryResult.FIELD_RESULT_CODE
            int r2 = r4.resultCode
            r0.putInt(r1, r2)
            java.lang.String r1 = androidx.media3.session.LibraryResult.FIELD_COMPLETION_TIME_MS
            long r2 = r4.completionTimeMs
            r0.putLong(r1, r2)
            androidx.media3.session.MediaLibraryService$LibraryParams r1 = r4.params
            if (r1 == 0) goto L20
            java.lang.String r2 = androidx.media3.session.LibraryResult.FIELD_PARAMS
            android.os.Bundle r1 = r1.toBundle()
            r0.putBundle(r2, r1)
        L20:
            androidx.media3.session.SessionError r1 = r4.sessionError
            if (r1 == 0) goto L2d
            java.lang.String r2 = androidx.media3.session.LibraryResult.FIELD_SESSION_ERROR
            android.os.Bundle r1 = r1.toBundle()
            r0.putBundle(r2, r1)
        L2d:
            java.lang.String r1 = androidx.media3.session.LibraryResult.FIELD_VALUE_TYPE
            int r2 = r4.valueType
            r0.putInt(r1, r2)
            V r1 = r4.value
            if (r1 != 0) goto L39
            goto L47
        L39:
            int r2 = r4.valueType
            r3 = 1
            if (r2 == r3) goto L6c
            r3 = 2
            if (r2 == r3) goto L60
            r1 = 3
            if (r2 == r1) goto L48
            r4 = 4
            if (r2 == r4) goto L6c
        L47:
            return r0
        L48:
            java.lang.String r1 = androidx.media3.session.LibraryResult.FIELD_VALUE
            androidx.media3.common.BundleListRetriever r2 = new androidx.media3.common.BundleListRetriever
            V r4 = r4.value
            com.google.common.collect.ImmutableList r4 = (com.google.common.collect.ImmutableList) r4
            androidx.media3.session.LibraryResult$$ExternalSyntheticLambda0 r3 = new androidx.media3.session.LibraryResult$$ExternalSyntheticLambda0
            r3.<init>()
            com.google.common.collect.ImmutableList r4 = androidx.media3.common.util.BundleCollectionUtil.toBundleList(r4, r3)
            r2.<init>(r4)
            androidx.core.app.BundleCompat.putBinder(r0, r1, r2)
            return r0
        L60:
            java.lang.String r4 = androidx.media3.session.LibraryResult.FIELD_VALUE
            androidx.media3.common.MediaItem r1 = (androidx.media3.common.MediaItem) r1
            android.os.Bundle r1 = r1.toBundle()
            r0.putBundle(r4, r1)
            return r0
        L6c:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.LibraryResult.toBundle():android.os.Bundle");
    }

    public static LibraryResult<Void> fromVoidBundle(Bundle bundle) {
        return fromUnknownBundle(bundle);
    }

    public static LibraryResult<MediaItem> fromItemBundle(Bundle bundle) {
        return fromBundle(bundle, 2);
    }

    public static LibraryResult<ImmutableList<MediaItem>> fromItemListBundle(Bundle bundle) {
        return fromBundle(bundle, 3);
    }

    public static LibraryResult<?> fromUnknownBundle(Bundle bundle) {
        return fromBundle(bundle, null);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0043  */
    /* JADX WARN: Code duplicated, block: B:17:0x0046  */
    /* JADX WARN: Code duplicated, block: B:19:0x0049  */
    /* JADX WARN: Code duplicated, block: B:22:0x004d  */
    /* JADX WARN: Code duplicated, block: B:24:0x0053  */
    /* JADX WARN: Code duplicated, block: B:27:0x005b  */
    /* JADX WARN: Code duplicated, block: B:31:0x0068  */
    /* JADX WARN: Code duplicated, block: B:33:0x0077  */
    /* JADX WARN: Code duplicated, block: B:36:0x007f  */
    /* JADX WARN: Code duplicated, block: B:40:0x008c  */
    private static LibraryResult<?> fromBundle(Bundle bundle, Integer num) {
        SessionError sessionError;
        SessionError sessionError2;
        int i;
        Bundle bundle2;
        Object objFromBundle;
        IBinder binder;
        int i2 = bundle.getInt(FIELD_RESULT_CODE, 0);
        long j = bundle.getLong(FIELD_COMPLETION_TIME_MS, SystemClock.elapsedRealtime());
        Bundle bundle3 = bundle.getBundle(FIELD_PARAMS);
        Object obj = null;
        MediaLibraryService.LibraryParams libraryParamsFromBundle = bundle3 == null ? null : MediaLibraryService.LibraryParams.fromBundle(bundle3);
        Bundle bundle4 = bundle.getBundle(FIELD_SESSION_ERROR);
        if (bundle4 != null) {
            sessionError2 = SessionError.fromBundle(bundle4);
        } else {
            if (i2 != 0) {
                sessionError2 = new SessionError(i2, "no error message provided");
            } else {
                sessionError = null;
            }
            i = bundle.getInt(FIELD_VALUE_TYPE);
            if (i != 1) {
                if (i != 2) {
                    Preconditions.checkState(num != null || num.intValue() == 2);
                    bundle2 = bundle.getBundle(FIELD_VALUE);
                    if (bundle2 != null) {
                        objFromBundle = MediaItem.fromBundle(bundle2);
                        obj = objFromBundle;
                    }
                } else if (i != 3) {
                    Preconditions.checkState(num != null || num.intValue() == 3);
                    binder = BundleCompat.getBinder(bundle, FIELD_VALUE);
                    if (binder != null) {
                        objFromBundle = BundleCollectionUtil.fromBundleList(new LibraryResult$$ExternalSyntheticLambda1(), BundleListRetriever.getList(binder));
                        obj = objFromBundle;
                    }
                } else if (i != 4) {
                    throw new IllegalStateException();
                }
            }
            return new LibraryResult<>(i2, j, libraryParamsFromBundle, sessionError, obj, i);
        }
        sessionError = sessionError2;
        i = bundle.getInt(FIELD_VALUE_TYPE);
        if (i != 1) {
            if (i != 2) {
                Preconditions.checkState(num != null || num.intValue() == 2);
                bundle2 = bundle.getBundle(FIELD_VALUE);
                if (bundle2 != null) {
                    objFromBundle = MediaItem.fromBundle(bundle2);
                    obj = objFromBundle;
                }
            } else if (i != 3) {
                Preconditions.checkState(num != null || num.intValue() == 3);
                binder = BundleCompat.getBinder(bundle, FIELD_VALUE);
                if (binder != null) {
                    objFromBundle = BundleCollectionUtil.fromBundleList(new LibraryResult$$ExternalSyntheticLambda1(), BundleListRetriever.getList(binder));
                    obj = objFromBundle;
                }
            } else if (i != 4) {
                throw new IllegalStateException();
            }
        }
        return new LibraryResult<>(i2, j, libraryParamsFromBundle, sessionError, obj, i);
    }
}
