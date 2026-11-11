package androidx.media3.cast;

import android.content.Context;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.ForwardingPlayer;
import androidx.media3.common.Player;
import androidx.media3.common.PlayerTransferState;
import androidx.media3.exoplayer.ExoPlayer;
import com.google.android.gms.cast.framework.CastContext;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class CastPlayer extends ForwardingPlayer {
    public static final DeviceInfo DEVICE_INFO_REMOTE_EMPTY = RemoteCastPlayer.DEVICE_INFO_REMOTE_EMPTY;
    private final RemoteCastPlayer remoteCastPlayer;

    public interface TransferCallback {
        public static final TransferCallback DEFAULT = new TransferCallback() { // from class: androidx.media3.cast.CastPlayer$TransferCallback$$ExternalSyntheticLambda0
            @Override // androidx.media3.cast.CastPlayer.TransferCallback
            public final void transferState(Player player, Player player2) {
                PlayerTransferState.fromPlayer(player).setToPlayer(player2);
            }
        };

        void transferState(Player player, Player player2);

        /* JADX INFO: renamed from: androidx.media3.cast.CastPlayer$TransferCallback$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            static {
                TransferCallback transferCallback = TransferCallback.DEFAULT;
            }
        }
    }

    public static final class Builder {
        private boolean buildCalled;
        private final Context context;
        private Player localPlayer;
        private RemoteCastPlayer remotePlayer;
        private TransferCallback transferCallback = TransferCallback.DEFAULT;

        public Builder(Context context) {
            this.context = (Context) Preconditions.checkNotNull(context);
        }

        public Builder setTransferCallback(TransferCallback transferCallback) {
            Preconditions.checkState(!this.buildCalled);
            this.transferCallback = (TransferCallback) Preconditions.checkNotNull(transferCallback);
            return this;
        }

        public Builder setLocalPlayer(Player player) {
            Preconditions.checkState(!this.buildCalled);
            this.localPlayer = (Player) Preconditions.checkNotNull(player);
            return this;
        }

        public Builder setRemotePlayer(RemoteCastPlayer remoteCastPlayer) {
            Preconditions.checkState(!this.buildCalled);
            this.remotePlayer = (RemoteCastPlayer) Preconditions.checkNotNull(remoteCastPlayer);
            return this;
        }

        public CastPlayer build() {
            Preconditions.checkState(!this.buildCalled);
            this.buildCalled = true;
            if (this.localPlayer == null) {
                this.localPlayer = new ExoPlayer.Builder(this.context).build();
            }
            if (this.remotePlayer == null) {
                this.remotePlayer = new RemoteCastPlayer.Builder(this.context).build();
            }
            return new CastPlayer(new CastPlayerImpl(this.localPlayer, this.remotePlayer, this.remotePlayer.isCastSessionAvailable() ? this.remotePlayer : this.localPlayer, this.transferCallback), this.remotePlayer);
        }
    }

    @Deprecated
    public CastPlayer(CastContext castContext) {
        this(castContext, new DefaultMediaItemConverter());
    }

    @Deprecated
    public CastPlayer(CastContext castContext, MediaItemConverter mediaItemConverter) {
        this(castContext, mediaItemConverter, 5000L, 15000L);
    }

    @Deprecated
    public CastPlayer(CastContext castContext, MediaItemConverter mediaItemConverter, long j, long j2) {
        this(null, castContext, mediaItemConverter, j, j2, 3000L);
    }

    @Deprecated
    public CastPlayer(Context context, CastContext castContext, MediaItemConverter mediaItemConverter, long j, long j2, long j3) {
        this(new RemoteCastPlayer(context, castContext, mediaItemConverter, j, j2, j3));
    }

    private CastPlayer(RemoteCastPlayer remoteCastPlayer) {
        super(remoteCastPlayer);
        this.remoteCastPlayer = remoteCastPlayer;
    }

    private CastPlayer(CastPlayerImpl castPlayerImpl, RemoteCastPlayer remoteCastPlayer) {
        super(castPlayerImpl);
        this.remoteCastPlayer = remoteCastPlayer;
    }

    @Deprecated
    public boolean isCastSessionAvailable() {
        return this.remoteCastPlayer.isCastSessionAvailable();
    }

    @Deprecated
    public void setSessionAvailabilityListener(SessionAvailabilityListener sessionAvailabilityListener) {
        this.remoteCastPlayer.setSessionAvailabilityListener(sessionAvailabilityListener);
    }
}
