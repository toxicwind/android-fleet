package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSourceBitmapLoader;
import androidx.media3.session.legacy.MediaSessionManager;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Longs;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.errorprone.annotations.DoNotMock;
import j$.util.Objects;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* JADX INFO: loaded from: classes4.dex */
@DoNotMock
public class MediaSession {
    private final MediaSessionImpl impl;
    private static final Object STATIC_LOCK = new Object();
    private static final HashMap<String, MediaSession> SESSION_ID_TO_SESSION_MAP = new HashMap<>();

    interface ControllerCb {

        /* JADX INFO: renamed from: androidx.media3.session.MediaSession$ControllerCb$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onAudioAttributesChanged(ControllerCb _this, int i, AudioAttributes audioAttributes) throws RemoteException {
            }

            public static void $default$onAudioSessionIdChanged(ControllerCb _this, int i, int i2) throws RemoteException {
            }

            public static void $default$onAvailableCommandsChangedFromPlayer(ControllerCb _this, int i, Player.Commands commands) throws RemoteException {
            }

            public static void $default$onAvailableCommandsChangedFromSession(ControllerCb _this, int i, SessionCommands sessionCommands, Player.Commands commands) throws RemoteException {
            }

            public static void $default$onChildrenChanged(ControllerCb _this, int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            }

            public static void $default$onDeviceInfoChanged(ControllerCb _this, int i, DeviceInfo deviceInfo) throws RemoteException {
            }

            public static void $default$onDeviceVolumeChanged(ControllerCb _this, int i, int i2, boolean z) throws RemoteException {
            }

            public static void $default$onDisconnected(ControllerCb _this, int i) {
            }

            public static void $default$onError(ControllerCb _this, int i, SessionError sessionError) throws RemoteException {
            }

            public static void $default$onIsLoadingChanged(ControllerCb _this, int i, boolean z) throws RemoteException {
            }

            public static void $default$onIsPlayingChanged(ControllerCb _this, int i, boolean z) throws RemoteException {
            }

            public static void $default$onLibraryResult(ControllerCb _this, int i, LibraryResult libraryResult) throws RemoteException {
            }

            public static void $default$onMediaItemTransition(ControllerCb _this, int i, MediaItem mediaItem, int i2) throws RemoteException {
            }

            public static void $default$onMediaMetadataChanged(ControllerCb _this, int i, MediaMetadata mediaMetadata) throws RemoteException {
            }

            public static void $default$onPeriodicSessionPositionInfoChanged(ControllerCb _this, int i, SessionPositionInfo sessionPositionInfo, boolean z, boolean z2, int i2) throws RemoteException {
            }

            public static void $default$onPlayWhenReadyChanged(ControllerCb _this, int i, boolean z, int i2) throws RemoteException {
            }

            public static void $default$onPlaybackParametersChanged(ControllerCb _this, int i, PlaybackParameters playbackParameters) throws RemoteException {
            }

            public static void $default$onPlaybackStateChanged(ControllerCb _this, int i, int i2, PlaybackException playbackException) throws RemoteException {
            }

            public static void $default$onPlaybackSuppressionReasonChanged(ControllerCb _this, int i, int i2) throws RemoteException {
            }

            public static void $default$onPlayerChanged(ControllerCb _this, int i, PlayerWrapper playerWrapper, PlayerWrapper playerWrapper2) throws RemoteException {
            }

            public static void $default$onPlayerError(ControllerCb _this, int i, PlaybackException playbackException) throws RemoteException {
            }

            public static void $default$onPlayerInfoChanged(ControllerCb _this, int i, PlayerInfo playerInfo, Player.Commands commands, boolean z, boolean z2) throws RemoteException {
            }

            public static void $default$onPlaylistMetadataChanged(ControllerCb _this, int i, MediaMetadata mediaMetadata) throws RemoteException {
            }

            public static void $default$onPositionDiscontinuity(ControllerCb _this, int i, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i2) throws RemoteException {
            }

            public static void $default$onRenderedFirstFrame(ControllerCb _this, int i) throws RemoteException {
            }

            public static void $default$onRepeatModeChanged(ControllerCb _this, int i, int i2) throws RemoteException {
            }

            public static void $default$onSearchResultChanged(ControllerCb _this, int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            }

            public static void $default$onSeekBackIncrementChanged(ControllerCb _this, int i, long j) throws RemoteException {
            }

            public static void $default$onSeekForwardIncrementChanged(ControllerCb _this, int i, long j) throws RemoteException {
            }

            public static void $default$onSessionActivityChanged(ControllerCb _this, int i, PendingIntent pendingIntent) throws RemoteException {
            }

            public static void $default$onSessionExtrasChanged(ControllerCb _this, int i, Bundle bundle) throws RemoteException {
            }

            public static void $default$onSessionResult(ControllerCb _this, int i, SessionResult sessionResult) throws RemoteException {
            }

            public static void $default$onShuffleModeEnabledChanged(ControllerCb _this, int i, boolean z) throws RemoteException {
            }

            public static void $default$onSurfaceSizeChanged(ControllerCb _this, int i, int i2, int i3) throws RemoteException {
            }

            public static void $default$onTimelineChanged(ControllerCb _this, int i, Timeline timeline, int i2) throws RemoteException {
            }

            public static void $default$onTrackSelectionParametersChanged(ControllerCb _this, int i, TrackSelectionParameters trackSelectionParameters) throws RemoteException {
            }

            public static void $default$onTracksChanged(ControllerCb _this, int i, Tracks tracks) throws RemoteException {
            }

            public static void $default$onVideoSizeChanged(ControllerCb _this, int i, VideoSize videoSize) throws RemoteException {
            }

            public static void $default$onVolumeChanged(ControllerCb _this, int i, float f) throws RemoteException {
            }

            public static void $default$sendCustomCommand(ControllerCb _this, int i, SessionCommand sessionCommand, Bundle bundle) throws RemoteException {
            }

            public static void $default$sendCustomCommandProgressUpdate(ControllerCb _this, int i, SessionCommand sessionCommand, Bundle bundle, Bundle bundle2) throws RemoteException {
            }

            public static void $default$setCustomLayout(ControllerCb _this, int i, List list) throws RemoteException {
            }

            public static void $default$setMediaButtonPreferences(ControllerCb _this, int i, List list) throws RemoteException {
            }
        }

        void onAudioAttributesChanged(int i, AudioAttributes audioAttributes) throws RemoteException;

        void onAudioSessionIdChanged(int i, int i2) throws RemoteException;

        void onAvailableCommandsChangedFromPlayer(int i, Player.Commands commands) throws RemoteException;

        void onAvailableCommandsChangedFromSession(int i, SessionCommands sessionCommands, Player.Commands commands) throws RemoteException;

        void onChildrenChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException;

        void onDeviceInfoChanged(int i, DeviceInfo deviceInfo) throws RemoteException;

        void onDeviceVolumeChanged(int i, int i2, boolean z) throws RemoteException;

        void onDisconnected(int i);

        void onError(int i, SessionError sessionError) throws RemoteException;

        void onIsLoadingChanged(int i, boolean z) throws RemoteException;

        void onIsPlayingChanged(int i, boolean z) throws RemoteException;

        void onLibraryResult(int i, LibraryResult<?> libraryResult) throws RemoteException;

        void onMediaItemTransition(int i, MediaItem mediaItem, int i2) throws RemoteException;

        void onMediaMetadataChanged(int i, MediaMetadata mediaMetadata) throws RemoteException;

        void onPeriodicSessionPositionInfoChanged(int i, SessionPositionInfo sessionPositionInfo, boolean z, boolean z2, int i2) throws RemoteException;

        void onPlayWhenReadyChanged(int i, boolean z, int i2) throws RemoteException;

        void onPlaybackParametersChanged(int i, PlaybackParameters playbackParameters) throws RemoteException;

        void onPlaybackStateChanged(int i, int i2, PlaybackException playbackException) throws RemoteException;

        void onPlaybackSuppressionReasonChanged(int i, int i2) throws RemoteException;

        void onPlayerChanged(int i, PlayerWrapper playerWrapper, PlayerWrapper playerWrapper2) throws RemoteException;

        void onPlayerError(int i, PlaybackException playbackException) throws RemoteException;

        void onPlayerInfoChanged(int i, PlayerInfo playerInfo, Player.Commands commands, boolean z, boolean z2) throws RemoteException;

        void onPlaylistMetadataChanged(int i, MediaMetadata mediaMetadata) throws RemoteException;

        void onPositionDiscontinuity(int i, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i2) throws RemoteException;

        void onRenderedFirstFrame(int i) throws RemoteException;

        void onRepeatModeChanged(int i, int i2) throws RemoteException;

        void onSearchResultChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException;

        void onSeekBackIncrementChanged(int i, long j) throws RemoteException;

        void onSeekForwardIncrementChanged(int i, long j) throws RemoteException;

        void onSessionActivityChanged(int i, PendingIntent pendingIntent) throws RemoteException;

        void onSessionExtrasChanged(int i, Bundle bundle) throws RemoteException;

        void onSessionResult(int i, SessionResult sessionResult) throws RemoteException;

        void onShuffleModeEnabledChanged(int i, boolean z) throws RemoteException;

        void onSurfaceSizeChanged(int i, int i2, int i3) throws RemoteException;

        void onTimelineChanged(int i, Timeline timeline, int i2) throws RemoteException;

        void onTrackSelectionParametersChanged(int i, TrackSelectionParameters trackSelectionParameters) throws RemoteException;

        void onTracksChanged(int i, Tracks tracks) throws RemoteException;

        void onVideoSizeChanged(int i, VideoSize videoSize) throws RemoteException;

        void onVolumeChanged(int i, float f) throws RemoteException;

        void sendCustomCommand(int i, SessionCommand sessionCommand, Bundle bundle) throws RemoteException;

        void sendCustomCommandProgressUpdate(int i, SessionCommand sessionCommand, Bundle bundle, Bundle bundle2) throws RemoteException;

        void setCustomLayout(int i, List<CommandButton> list) throws RemoteException;

        void setMediaButtonPreferences(int i, List<CommandButton> list) throws RemoteException;
    }

    interface Listener {
        void onNotificationRefreshRequired(MediaSession mediaSession);

        boolean onPlayRequested(MediaSession mediaSession);
    }

    public interface ProgressReporter {
        void sendProgressUpdate(Bundle bundle);
    }

    public static final class Builder extends BuilderBase<MediaSession, Builder, Callback> {
        private boolean useLegacySurfaceHandling;

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public /* bridge */ /* synthetic */ BuilderBase setCommandButtonsForMediaItems(List list) {
            return setCommandButtonsForMediaItems((List<CommandButton>) list);
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public /* bridge */ /* synthetic */ BuilderBase setCustomLayout(List list) {
            return setCustomLayout((List<CommandButton>) list);
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public /* bridge */ /* synthetic */ BuilderBase setMediaButtonPreferences(List list) {
            return setMediaButtonPreferences((List<CommandButton>) list);
        }

        public Builder(Context context, Player player) {
            super(context, player, new Callback() { // from class: androidx.media3.session.MediaSession.Builder.1
                @Override // androidx.media3.session.MediaSession.Callback
                public /* synthetic */ ListenableFuture onAddMediaItems(MediaSession mediaSession, ControllerInfo controllerInfo, List list) {
                    return Callback.CC.$default$onAddMediaItems(this, mediaSession, controllerInfo, list);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public /* synthetic */ ConnectionResult onConnect(MediaSession mediaSession, ControllerInfo controllerInfo) {
                    return Callback.CC.$default$onConnect(this, mediaSession, controllerInfo);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public /* synthetic */ ListenableFuture onCustomCommand(MediaSession mediaSession, ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle) {
                    return Callback.CC.$default$onCustomCommand(this, mediaSession, controllerInfo, sessionCommand, bundle);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public /* synthetic */ ListenableFuture onCustomCommand(MediaSession mediaSession, ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle, ProgressReporter progressReporter) {
                    return Callback.CC.$default$onCustomCommand(this, mediaSession, controllerInfo, sessionCommand, bundle, progressReporter);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public /* synthetic */ void onDisconnected(MediaSession mediaSession, ControllerInfo controllerInfo) {
                    Callback.CC.$default$onDisconnected(this, mediaSession, controllerInfo);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public /* synthetic */ boolean onMediaButtonEvent(MediaSession mediaSession, ControllerInfo controllerInfo, Intent intent) {
                    return Callback.CC.$default$onMediaButtonEvent(this, mediaSession, controllerInfo, intent);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public /* synthetic */ ListenableFuture onPlaybackResumption(MediaSession mediaSession, ControllerInfo controllerInfo) {
                    return Callback.CC.$default$onPlaybackResumption(this, mediaSession, controllerInfo);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public /* synthetic */ ListenableFuture onPlaybackResumption(MediaSession mediaSession, ControllerInfo controllerInfo, boolean z) {
                    return Callback.CC.$default$onPlaybackResumption(this, mediaSession, controllerInfo, z);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public /* synthetic */ int onPlayerCommandRequest(MediaSession mediaSession, ControllerInfo controllerInfo, int i) {
                    return Callback.CC.$default$onPlayerCommandRequest(this, mediaSession, controllerInfo, i);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public /* synthetic */ void onPlayerInteractionFinished(MediaSession mediaSession, ControllerInfo controllerInfo, Player.Commands commands) {
                    Callback.CC.$default$onPlayerInteractionFinished(this, mediaSession, controllerInfo, commands);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public /* synthetic */ void onPostConnect(MediaSession mediaSession, ControllerInfo controllerInfo) {
                    Callback.CC.$default$onPostConnect(this, mediaSession, controllerInfo);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public /* synthetic */ ListenableFuture onSetMediaItems(MediaSession mediaSession, ControllerInfo controllerInfo, List list, int i, long j) {
                    return Callback.CC.$default$onSetMediaItems(this, mediaSession, controllerInfo, list, i, j);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public /* synthetic */ ListenableFuture onSetRating(MediaSession mediaSession, ControllerInfo controllerInfo, Rating rating) {
                    return Callback.CC.$default$onSetRating(this, mediaSession, controllerInfo, rating);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public /* synthetic */ ListenableFuture onSetRating(MediaSession mediaSession, ControllerInfo controllerInfo, String str, Rating rating) {
                    return Callback.CC.$default$onSetRating(this, mediaSession, controllerInfo, str, rating);
                }
            });
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public Builder setSessionActivity(PendingIntent pendingIntent) {
            return (Builder) super.setSessionActivity(pendingIntent);
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public Builder setId(String str) {
            return (Builder) super.setId(str);
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public Builder setCallback(Callback callback) {
            return (Builder) super.setCallback(callback);
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public Builder setExtras(Bundle bundle) {
            return (Builder) super.setExtras(bundle);
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public Builder setSessionExtras(Bundle bundle) {
            return (Builder) super.setSessionExtras(bundle);
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public Builder setBitmapLoader(androidx.media3.common.util.BitmapLoader bitmapLoader) {
            return (Builder) super.setBitmapLoader(bitmapLoader);
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public Builder setCustomLayout(List<CommandButton> list) {
            return (Builder) super.setCustomLayout(list);
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public Builder setMediaButtonPreferences(List<CommandButton> list) {
            return (Builder) super.setMediaButtonPreferences(list);
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public Builder setPeriodicPositionUpdateEnabled(boolean z) {
            return (Builder) super.setPeriodicPositionUpdateEnabled(z);
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public Builder setShowPlayButtonIfPlaybackIsSuppressed(boolean z) {
            return (Builder) super.setShowPlayButtonIfPlaybackIsSuppressed(z);
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public Builder setCommandButtonsForMediaItems(List<CommandButton> list) {
            return (Builder) super.setCommandButtonsForMediaItems(list);
        }

        public Builder setExperimentalSetUseLegacySurfaceHandling(boolean z) {
            this.useLegacySurfaceHandling = z;
            return this;
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public MediaSession build() {
            ensureBitmapLoaderIsSizeLimited();
            return new MediaSession(this.context, this.id, this.player, this.sessionActivity, this.customLayout, this.mediaButtonPreferences, this.commandButtonsForMediaItems, this.callback, this.tokenExtras, this.sessionExtras, this.bitmapLoader, this.playIfSuppressed, this.isPeriodicPositionUpdateEnabled, 0, this.useLegacySurfaceHandling);
        }
    }

    public static final class ControllerInfo {
        public static final int LEGACY_CONTROLLER_INTERFACE_VERSION = 0;
        public static final String LEGACY_CONTROLLER_PACKAGE_NAME = "android.media.session.MediaController";
        public static final int LEGACY_CONTROLLER_VERSION = 0;
        private final Bundle connectionHints;
        private final ControllerCb controllerCb;
        private final int interfaceVersion;
        private final boolean isPackageNameVerified;
        private final boolean isTrusted;
        private final int libraryVersion;
        private final int maxCommandsForMediaItems;
        private final MediaSessionManager.RemoteUserInfo remoteUserInfo;

        ControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo, int i, int i2, boolean z, ControllerCb controllerCb, Bundle bundle, int i3, boolean z2) {
            this.remoteUserInfo = remoteUserInfo;
            this.libraryVersion = i;
            this.interfaceVersion = i2;
            this.isTrusted = z;
            this.controllerCb = controllerCb;
            this.connectionHints = bundle;
            this.maxCommandsForMediaItems = i3;
            this.isPackageNameVerified = z2;
        }

        MediaSessionManager.RemoteUserInfo getRemoteUserInfo() {
            return this.remoteUserInfo;
        }

        public int getControllerVersion() {
            return this.libraryVersion;
        }

        public int getInterfaceVersion() {
            return this.interfaceVersion;
        }

        public String getPackageName() {
            return this.remoteUserInfo.getPackageName();
        }

        public int getUid() {
            return this.remoteUserInfo.getUid();
        }

        public Bundle getConnectionHints() {
            return new Bundle(this.connectionHints);
        }

        public int getMaxCommandsForMediaItems() {
            return this.maxCommandsForMediaItems;
        }

        public boolean isTrusted() {
            return this.isTrusted;
        }

        public boolean isPackageNameVerified() {
            return this.isPackageNameVerified;
        }

        public int hashCode() {
            return Objects.hash(this.controllerCb, this.remoteUserInfo);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ControllerInfo)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            ControllerInfo controllerInfo = (ControllerInfo) obj;
            ControllerCb controllerCb = this.controllerCb;
            if (controllerCb != null || controllerInfo.controllerCb != null) {
                return Objects.equals(controllerCb, controllerInfo.controllerCb);
            }
            return this.remoteUserInfo.equals(controllerInfo.remoteUserInfo);
        }

        public String toString() {
            return "ControllerInfo {pkg=" + this.remoteUserInfo.getPackageName() + ", uid=" + this.remoteUserInfo.getUid() + "}";
        }

        ControllerCb getControllerCb() {
            return this.controllerCb;
        }

        static ControllerInfo createLegacyControllerInfo() {
            return new ControllerInfo(new MediaSessionManager.RemoteUserInfo("android.media.session.MediaController", -1, -1), 0, 0, false, null, Bundle.EMPTY, 0, false);
        }

        public static ControllerInfo createTestOnlyControllerInfo(String str, int i, int i2, int i3, int i4, boolean z, Bundle bundle, boolean z2) {
            return new ControllerInfo(new MediaSessionManager.RemoteUserInfo(str, i, i2), i3, i4, z, null, bundle, 0, z2);
        }
    }

    MediaSession(Context context, String str, Player player, PendingIntent pendingIntent, ImmutableList<CommandButton> immutableList, ImmutableList<CommandButton> immutableList2, ImmutableList<CommandButton> immutableList3, Callback callback, Bundle bundle, Bundle bundle2, androidx.media3.common.util.BitmapLoader bitmapLoader, boolean z, boolean z2, int i, boolean z3) {
        synchronized (STATIC_LOCK) {
            HashMap<String, MediaSession> map = SESSION_ID_TO_SESSION_MAP;
            if (map.containsKey(str)) {
                throw new IllegalStateException("Session ID must be unique. ID=" + str);
            }
            map.put(str, this);
        }
        this.impl = createImpl(context, str, player, pendingIntent, immutableList, immutableList2, immutableList3, callback, bundle, bundle2, bitmapLoader, z, z2, i, z3);
    }

    MediaSessionImpl createImpl(Context context, String str, Player player, PendingIntent pendingIntent, ImmutableList<CommandButton> immutableList, ImmutableList<CommandButton> immutableList2, ImmutableList<CommandButton> immutableList3, Callback callback, Bundle bundle, Bundle bundle2, androidx.media3.common.util.BitmapLoader bitmapLoader, boolean z, boolean z2, int i, boolean z3) {
        return new MediaSessionImpl(this, context, str, player, pendingIntent, immutableList, immutableList2, immutableList3, callback, bundle, bundle2, bitmapLoader, z, z2, z3);
    }

    MediaSessionImpl getImpl() {
        return this.impl;
    }

    public static int getBitmapDimensionLimit(Context context) {
        return MediaSessionImpl.getBitmapDimensionLimit(context);
    }

    static MediaSession getSession(Uri uri) {
        synchronized (STATIC_LOCK) {
            for (MediaSession mediaSession : SESSION_ID_TO_SESSION_MAP.values()) {
                if (Objects.equals(mediaSession.getUri(), uri)) {
                    return mediaSession;
                }
            }
            return null;
        }
    }

    public final PendingIntent getSessionActivity() {
        return this.impl.getSessionActivity();
    }

    public final void setSessionActivity(PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 31 && pendingIntent != null) {
            Preconditions.checkArgument(Api31.isActivity(pendingIntent));
        }
        this.impl.setSessionActivity(pendingIntent);
    }

    public final void setSessionActivity(ControllerInfo controllerInfo, PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 31 && pendingIntent != null) {
            Preconditions.checkArgument(Api31.isActivity(pendingIntent));
        }
        this.impl.setSessionActivity(controllerInfo, pendingIntent);
    }

    public final void setPlayer(Player player) {
        Preconditions.checkNotNull(player);
        Preconditions.checkArgument(player.canAdvertiseSession());
        Preconditions.checkArgument(player.getApplicationLooper() == getPlayer().getApplicationLooper());
        Preconditions.checkState(player.getApplicationLooper() == Looper.myLooper());
        this.impl.setPlayer(player);
    }

    public final void release() {
        try {
            synchronized (STATIC_LOCK) {
                SESSION_ID_TO_SESSION_MAP.remove(this.impl.getId());
            }
            this.impl.release();
        } catch (Exception unused) {
        }
    }

    final boolean isReleased() {
        return this.impl.isReleased();
    }

    public final Player getPlayer() {
        return this.impl.getPlayerWrapper().getWrappedPlayer();
    }

    public final String getId() {
        return this.impl.getId();
    }

    public final SessionToken getToken() {
        return this.impl.getToken();
    }

    public final List<ControllerInfo> getConnectedControllers() {
        return this.impl.getConnectedControllers();
    }

    public final ControllerInfo getControllerForCurrentRequest() {
        return this.impl.getControllerForCurrentRequest();
    }

    public boolean isMediaNotificationController(ControllerInfo controllerInfo) {
        return this.impl.isMediaNotificationController(controllerInfo);
    }

    public ControllerInfo getMediaNotificationControllerInfo() {
        return this.impl.getMediaNotificationControllerInfo();
    }

    public final boolean isAutomotiveController(ControllerInfo controllerInfo) {
        return this.impl.isAutomotiveController(controllerInfo);
    }

    public final boolean isAutoCompanionController(ControllerInfo controllerInfo) {
        return this.impl.isAutoCompanionController(controllerInfo);
    }

    public final ListenableFuture<SessionResult> setCustomLayout(ControllerInfo controllerInfo, List<CommandButton> list) {
        Preconditions.checkNotNull(controllerInfo, "controller must not be null");
        Preconditions.checkNotNull(list, "layout must not be null");
        return this.impl.setCustomLayout(controllerInfo, ImmutableList.copyOf((Collection) list));
    }

    public final void setCustomLayout(List<CommandButton> list) {
        Preconditions.checkNotNull(list, "layout must not be null");
        this.impl.setCustomLayout(ImmutableList.copyOf((Collection) list));
    }

    public final ListenableFuture<SessionResult> setMediaButtonPreferences(ControllerInfo controllerInfo, List<CommandButton> list) {
        Preconditions.checkNotNull(controllerInfo, "controller must not be null");
        Preconditions.checkNotNull(list, "media button preferences must not be null");
        return this.impl.setMediaButtonPreferences(controllerInfo, ImmutableList.copyOf((Collection) list));
    }

    public final void setMediaButtonPreferences(List<CommandButton> list) {
        Preconditions.checkNotNull(list, "media button preferences must not be null");
        this.impl.setMediaButtonPreferences(ImmutableList.copyOf((Collection) list));
    }

    public final void setPlaybackException(ControllerInfo controllerInfo, PlaybackException playbackException) {
        this.impl.setPlaybackException(controllerInfo, playbackException);
    }

    public final void setPlaybackException(PlaybackException playbackException) {
        this.impl.setPlaybackException(playbackException);
    }

    public final void setAvailableCommands(ControllerInfo controllerInfo, SessionCommands sessionCommands, Player.Commands commands) {
        Preconditions.checkNotNull(controllerInfo, "controller must not be null");
        Preconditions.checkNotNull(sessionCommands, "sessionCommands must not be null");
        Preconditions.checkNotNull(commands, "playerCommands must not be null");
        this.impl.setAvailableCommands(controllerInfo, sessionCommands, commands);
    }

    public ImmutableList<CommandButton> getCustomLayout() {
        return this.impl.getCustomLayout();
    }

    public ImmutableList<CommandButton> getMediaButtonPreferences() {
        return this.impl.getMediaButtonPreferences();
    }

    public final void broadcastCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        Preconditions.checkNotNull(sessionCommand);
        Preconditions.checkNotNull(bundle);
        Preconditions.checkArgument(sessionCommand.commandCode == 0, "command must be a custom command");
        this.impl.broadcastCustomCommand(sessionCommand, bundle);
    }

    public Bundle getSessionExtras() {
        return this.impl.getSessionExtras();
    }

    public final void setSessionExtras(Bundle bundle) {
        this.impl.setSessionExtras(new Bundle(bundle));
    }

    public final void setSessionExtras(ControllerInfo controllerInfo, Bundle bundle) {
        Preconditions.checkNotNull(controllerInfo, "controller must not be null");
        this.impl.setSessionExtras(controllerInfo, new Bundle(bundle));
    }

    public final androidx.media3.common.util.BitmapLoader getBitmapLoader() {
        return this.impl.getBitmapLoader();
    }

    public final boolean getShowPlayButtonIfPlaybackIsSuppressed() {
        return this.impl.shouldPlayIfSuppressed();
    }

    public final ListenableFuture<SessionResult> sendCustomCommand(ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle) {
        Preconditions.checkNotNull(controllerInfo);
        Preconditions.checkNotNull(sessionCommand);
        Preconditions.checkNotNull(bundle);
        Preconditions.checkArgument(sessionCommand.commandCode == 0, "command must be a custom command");
        return this.impl.sendCustomCommand(controllerInfo, sessionCommand, bundle);
    }

    public final void sendError(ControllerInfo controllerInfo, SessionError sessionError) {
        this.impl.sendError(controllerInfo, sessionError);
    }

    public final void sendError(SessionError sessionError) {
        this.impl.sendError(sessionError);
    }

    public final android.media.session.MediaSession.Token getPlatformToken() {
        return this.impl.getPlatformToken();
    }

    final void setLegacyControllerConnectionTimeoutMs(long j) {
        this.impl.setLegacyControllerConnectionTimeoutMs(j);
    }

    final void handleControllerConnectionFromService(IMediaController iMediaController, ControllerInfo controllerInfo) {
        this.impl.connectFromService(iMediaController, controllerInfo);
    }

    final IBinder getLegacyBrowserServiceBinder() {
        return this.impl.getLegacyBrowserServiceBinder();
    }

    final void setSessionPositionUpdateDelayMs(long j) {
        this.impl.setSessionPositionUpdateDelayMsOnHandler(j);
    }

    final void setListener(Listener listener) {
        this.impl.setMediaSessionListener(listener);
    }

    final void clearListener() {
        this.impl.clearMediaSessionListener();
    }

    final Uri getUri() {
        return this.impl.getUri();
    }

    public interface Callback {
        ListenableFuture<List<MediaItem>> onAddMediaItems(MediaSession mediaSession, ControllerInfo controllerInfo, List<MediaItem> list);

        ConnectionResult onConnect(MediaSession mediaSession, ControllerInfo controllerInfo);

        ListenableFuture<SessionResult> onCustomCommand(MediaSession mediaSession, ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle);

        ListenableFuture<SessionResult> onCustomCommand(MediaSession mediaSession, ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle, ProgressReporter progressReporter);

        void onDisconnected(MediaSession mediaSession, ControllerInfo controllerInfo);

        boolean onMediaButtonEvent(MediaSession mediaSession, ControllerInfo controllerInfo, Intent intent);

        @Deprecated
        ListenableFuture<MediaItemsWithStartPosition> onPlaybackResumption(MediaSession mediaSession, ControllerInfo controllerInfo);

        ListenableFuture<MediaItemsWithStartPosition> onPlaybackResumption(MediaSession mediaSession, ControllerInfo controllerInfo, boolean z);

        @Deprecated
        int onPlayerCommandRequest(MediaSession mediaSession, ControllerInfo controllerInfo, int i);

        void onPlayerInteractionFinished(MediaSession mediaSession, ControllerInfo controllerInfo, Player.Commands commands);

        void onPostConnect(MediaSession mediaSession, ControllerInfo controllerInfo);

        ListenableFuture<MediaItemsWithStartPosition> onSetMediaItems(MediaSession mediaSession, ControllerInfo controllerInfo, List<MediaItem> list, int i, long j);

        ListenableFuture<SessionResult> onSetRating(MediaSession mediaSession, ControllerInfo controllerInfo, Rating rating);

        ListenableFuture<SessionResult> onSetRating(MediaSession mediaSession, ControllerInfo controllerInfo, String str, Rating rating);

        /* JADX INFO: renamed from: androidx.media3.session.MediaSession$Callback$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onDisconnected(Callback _this, MediaSession mediaSession, ControllerInfo controllerInfo) {
            }

            public static boolean $default$onMediaButtonEvent(Callback _this, MediaSession mediaSession, ControllerInfo controllerInfo, Intent intent) {
                return false;
            }

            @Deprecated
            public static int $default$onPlayerCommandRequest(Callback _this, MediaSession mediaSession, ControllerInfo controllerInfo, int i) {
                return 0;
            }

            public static void $default$onPlayerInteractionFinished(Callback _this, MediaSession mediaSession, ControllerInfo controllerInfo, Player.Commands commands) {
            }

            public static void $default$onPostConnect(Callback _this, MediaSession mediaSession, ControllerInfo controllerInfo) {
            }

            public static ConnectionResult $default$onConnect(Callback _this, MediaSession mediaSession, ControllerInfo controllerInfo) {
                return new ConnectionResult.AcceptedResultBuilder(mediaSession).build();
            }

            public static ListenableFuture $default$onSetRating(Callback _this, MediaSession mediaSession, ControllerInfo controllerInfo, String str, Rating rating) {
                return Futures.immediateFuture(new SessionResult(-6));
            }

            public static ListenableFuture $default$onSetRating(Callback _this, MediaSession mediaSession, ControllerInfo controllerInfo, Rating rating) {
                return Futures.immediateFuture(new SessionResult(-6));
            }

            public static ListenableFuture $default$onCustomCommand(Callback _this, MediaSession mediaSession, ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle) {
                return Futures.immediateFuture(new SessionResult(-6));
            }

            public static ListenableFuture $default$onCustomCommand(Callback _this, MediaSession mediaSession, ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle, ProgressReporter progressReporter) {
                return _this.onCustomCommand(mediaSession, controllerInfo, sessionCommand, bundle);
            }

            public static ListenableFuture $default$onAddMediaItems(Callback _this, MediaSession mediaSession, ControllerInfo controllerInfo, List list) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((MediaItem) it.next()).localConfiguration == null) {
                        return Futures.immediateFailedFuture(new UnsupportedOperationException());
                    }
                }
                return Futures.immediateFuture(list);
            }

            public static ListenableFuture $default$onSetMediaItems(Callback _this, MediaSession mediaSession, ControllerInfo controllerInfo, List list, final int i, final long j) {
                return Util.transformFutureAsync(_this.onAddMediaItems(mediaSession, controllerInfo, list), new AsyncFunction() { // from class: androidx.media3.session.MediaSession$Callback$$ExternalSyntheticLambda0
                    @Override // com.google.common.util.concurrent.AsyncFunction
                    public final ListenableFuture apply(Object obj) {
                        return Futures.immediateFuture(new MediaSession.MediaItemsWithStartPosition((List) obj, i, j));
                    }
                });
            }

            @Deprecated
            public static ListenableFuture $default$onPlaybackResumption(Callback _this, MediaSession mediaSession, ControllerInfo controllerInfo) {
                return Futures.immediateFailedFuture(new UnsupportedOperationException());
            }

            public static ListenableFuture $default$onPlaybackResumption(Callback _this, MediaSession mediaSession, ControllerInfo controllerInfo, boolean z) {
                return _this.onPlaybackResumption(mediaSession, controllerInfo);
            }
        }
    }

    public static final class MediaItemsWithStartPosition {
        public final ImmutableList<MediaItem> mediaItems;
        public final int startIndex;
        public final long startPositionMs;

        public MediaItemsWithStartPosition(List<MediaItem> list, int i, long j) {
            this.mediaItems = ImmutableList.copyOf((Collection) list);
            this.startIndex = i;
            this.startPositionMs = j;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaItemsWithStartPosition)) {
                return false;
            }
            MediaItemsWithStartPosition mediaItemsWithStartPosition = (MediaItemsWithStartPosition) obj;
            return this.mediaItems.equals(mediaItemsWithStartPosition.mediaItems) && this.startIndex == mediaItemsWithStartPosition.startIndex && this.startPositionMs == mediaItemsWithStartPosition.startPositionMs;
        }

        public int hashCode() {
            return (((this.mediaItems.hashCode() * 31) + this.startIndex) * 31) + Longs.hashCode(this.startPositionMs);
        }
    }

    public static final class ConnectionResult {
        public final Player.Commands availablePlayerCommands;
        public final SessionCommands availableSessionCommands;
        public final ImmutableList<CommandButton> customLayout;
        public final boolean isAccepted;
        public final ImmutableList<CommandButton> mediaButtonPreferences;
        public final PendingIntent sessionActivity;
        public final Bundle sessionExtras;
        public static final SessionCommands DEFAULT_SESSION_COMMANDS = new SessionCommands.Builder().addAllSessionCommands().build();
        public static final SessionCommands DEFAULT_SESSION_AND_LIBRARY_COMMANDS = new SessionCommands.Builder().addAllLibraryCommands().addAllSessionCommands().build();
        public static final Player.Commands DEFAULT_PLAYER_COMMANDS = new Player.Commands.Builder().addAllCommands().build();

        public static class AcceptedResultBuilder {
            private Player.Commands availablePlayerCommands = ConnectionResult.DEFAULT_PLAYER_COMMANDS;
            private SessionCommands availableSessionCommands;
            private ImmutableList<CommandButton> customLayout;
            private ImmutableList<CommandButton> mediaButtonPreferences;
            private PendingIntent sessionActivity;
            private Bundle sessionExtras;

            public AcceptedResultBuilder(MediaSession mediaSession) {
                SessionCommands sessionCommands;
                if (mediaSession instanceof MediaLibraryService.MediaLibrarySession) {
                    sessionCommands = ConnectionResult.DEFAULT_SESSION_AND_LIBRARY_COMMANDS;
                } else {
                    sessionCommands = ConnectionResult.DEFAULT_SESSION_COMMANDS;
                }
                this.availableSessionCommands = sessionCommands;
            }

            public AcceptedResultBuilder setAvailableSessionCommands(SessionCommands sessionCommands) {
                this.availableSessionCommands = (SessionCommands) Preconditions.checkNotNull(sessionCommands);
                return this;
            }

            public AcceptedResultBuilder setAvailablePlayerCommands(Player.Commands commands) {
                this.availablePlayerCommands = (Player.Commands) Preconditions.checkNotNull(commands);
                return this;
            }

            public AcceptedResultBuilder setCustomLayout(List<CommandButton> list) {
                this.customLayout = list == null ? null : ImmutableList.copyOf((Collection) list);
                return this;
            }

            public AcceptedResultBuilder setMediaButtonPreferences(List<CommandButton> list) {
                this.mediaButtonPreferences = list == null ? null : ImmutableList.copyOf((Collection) list);
                return this;
            }

            public AcceptedResultBuilder setSessionExtras(Bundle bundle) {
                this.sessionExtras = bundle;
                return this;
            }

            public AcceptedResultBuilder setSessionActivity(PendingIntent pendingIntent) {
                this.sessionActivity = pendingIntent;
                return this;
            }

            public ConnectionResult build() {
                return new ConnectionResult(true, this.availableSessionCommands, this.availablePlayerCommands, this.customLayout, this.mediaButtonPreferences, this.sessionExtras, this.sessionActivity);
            }
        }

        private ConnectionResult(boolean z, SessionCommands sessionCommands, Player.Commands commands, ImmutableList<CommandButton> immutableList, ImmutableList<CommandButton> immutableList2, Bundle bundle, PendingIntent pendingIntent) {
            this.isAccepted = z;
            this.availableSessionCommands = sessionCommands;
            this.availablePlayerCommands = commands;
            this.customLayout = immutableList;
            this.mediaButtonPreferences = immutableList2;
            this.sessionExtras = bundle;
            this.sessionActivity = pendingIntent;
        }

        public static ConnectionResult accept(SessionCommands sessionCommands, Player.Commands commands) {
            return new ConnectionResult(true, sessionCommands, commands, null, null, null, null);
        }

        public static ConnectionResult reject() {
            return new ConnectionResult(false, SessionCommands.EMPTY, Player.Commands.EMPTY, ImmutableList.of(), ImmutableList.of(), Bundle.EMPTY, null);
        }
    }

    static abstract class BuilderBase<SessionT extends MediaSession, BuilderT extends BuilderBase<SessionT, BuilderT, CallbackT>, CallbackT extends Callback> {
        androidx.media3.common.util.BitmapLoader bitmapLoader;
        CallbackT callback;
        ImmutableList<CommandButton> commandButtonsForMediaItems;
        final Context context;
        ImmutableList<CommandButton> customLayout;
        String id;
        boolean isPeriodicPositionUpdateEnabled;
        ImmutableList<CommandButton> mediaButtonPreferences;
        boolean playIfSuppressed;
        final Player player;
        PendingIntent sessionActivity;
        Bundle sessionExtras;
        Bundle tokenExtras;

        public abstract SessionT build();

        public BuilderBase(Context context, Player player, CallbackT callbackt) {
            this.context = (Context) Preconditions.checkNotNull(context);
            this.player = (Player) Preconditions.checkNotNull(player);
            Preconditions.checkArgument(player.canAdvertiseSession());
            this.id = "";
            this.callback = callbackt;
            this.tokenExtras = new Bundle();
            this.sessionExtras = new Bundle();
            this.customLayout = ImmutableList.of();
            this.mediaButtonPreferences = ImmutableList.of();
            this.playIfSuppressed = true;
            this.isPeriodicPositionUpdateEnabled = true;
            this.commandButtonsForMediaItems = ImmutableList.of();
        }

        public BuilderT setSessionActivity(PendingIntent pendingIntent) {
            if (Build.VERSION.SDK_INT >= 31) {
                Preconditions.checkArgument(Api31.isActivity(pendingIntent));
            }
            this.sessionActivity = (PendingIntent) Preconditions.checkNotNull(pendingIntent);
            return this;
        }

        public BuilderT setId(String str) {
            this.id = (String) Preconditions.checkNotNull(str);
            return this;
        }

        BuilderT setCallback(CallbackT callbackt) {
            this.callback = (CallbackT) Preconditions.checkNotNull(callbackt);
            return this;
        }

        public BuilderT setExtras(Bundle bundle) {
            this.tokenExtras = new Bundle((Bundle) Preconditions.checkNotNull(bundle));
            return this;
        }

        public BuilderT setSessionExtras(Bundle bundle) {
            this.sessionExtras = new Bundle((Bundle) Preconditions.checkNotNull(bundle));
            return this;
        }

        public BuilderT setBitmapLoader(androidx.media3.common.util.BitmapLoader bitmapLoader) {
            this.bitmapLoader = (androidx.media3.common.util.BitmapLoader) Preconditions.checkNotNull(bitmapLoader);
            return this;
        }

        public BuilderT setCustomLayout(List<CommandButton> list) {
            this.customLayout = ImmutableList.copyOf((Collection) list);
            return this;
        }

        public BuilderT setMediaButtonPreferences(List<CommandButton> list) {
            this.mediaButtonPreferences = ImmutableList.copyOf((Collection) list);
            return this;
        }

        public BuilderT setShowPlayButtonIfPlaybackIsSuppressed(boolean z) {
            this.playIfSuppressed = z;
            return this;
        }

        public BuilderT setCommandButtonsForMediaItems(List<CommandButton> list) {
            this.commandButtonsForMediaItems = ImmutableList.copyOf((Collection) list);
            return this;
        }

        public BuilderT setPeriodicPositionUpdateEnabled(boolean z) {
            this.isPeriodicPositionUpdateEnabled = z;
            return this;
        }

        @EnsuresNonNull({"bitmapLoader"})
        protected final void ensureBitmapLoaderIsSizeLimited() {
            int bitmapDimensionLimit = MediaSession.getBitmapDimensionLimit(this.context);
            if (this.bitmapLoader == null) {
                this.bitmapLoader = new CacheBitmapLoader(new DataSourceBitmapLoader.Builder(this.context).setMaximumOutputDimension(bitmapDimensionLimit).setMakeShared(true).build());
            } else {
                this.bitmapLoader = new SizeLimitedBitmapLoader(this.bitmapLoader, bitmapDimensionLimit, true);
            }
        }
    }

    private static final class Api31 {
        private Api31() {
        }

        public static boolean isActivity(PendingIntent pendingIntent) {
            return pendingIntent.isActivity();
        }
    }
}
