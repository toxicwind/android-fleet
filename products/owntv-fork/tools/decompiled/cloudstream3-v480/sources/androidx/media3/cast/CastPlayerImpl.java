package androidx.media3.cast;

import androidx.media3.common.ForwardingSimpleBasePlayer;
import androidx.media3.common.Player;
import androidx.media3.common.SimpleBasePlayer;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/* JADX INFO: loaded from: classes4.dex */
final class CastPlayerImpl extends ForwardingSimpleBasePlayer {
    private final Player localPlayer;
    private final RemoteCastPlayer remotePlayer;
    private final SessionAvailabilityListener sessionAvailabilityListener;
    private final CastPlayer.TransferCallback transferCallback;

    public CastPlayerImpl(Player player, RemoteCastPlayer remoteCastPlayer, Player player2, CastPlayer.TransferCallback transferCallback) {
        super(player2);
        this.localPlayer = player;
        this.remotePlayer = remoteCastPlayer;
        this.transferCallback = transferCallback;
        SessionAvailabilityListenerImpl sessionAvailabilityListenerImpl = new SessionAvailabilityListenerImpl();
        this.sessionAvailabilityListener = sessionAvailabilityListenerImpl;
        remoteCastPlayer.setInternalSessionAvailabilityListener(sessionAvailabilityListenerImpl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateActivePlayer() {
        Player player = getPlayer();
        Player player2 = this.remotePlayer.isCastSessionAvailable() ? this.remotePlayer : this.localPlayer;
        if (player == player2) {
            return;
        }
        this.transferCallback.transferState(player, player2);
        if (player.getPlaybackState() != 1) {
            player2.prepare();
        }
        player.stop();
        setPlayer(player2);
    }

    @Override // androidx.media3.common.ForwardingSimpleBasePlayer, androidx.media3.common.SimpleBasePlayer
    protected SimpleBasePlayer.State getState() {
        SimpleBasePlayer.State state = super.getState();
        Player.Commands commands = state.availableCommands;
        if (commands.contains(32)) {
            return state;
        }
        return state.buildUpon().setAvailableCommands(commands.buildUpon().add(32).build()).build();
    }

    @Override // androidx.media3.common.ForwardingSimpleBasePlayer, androidx.media3.common.SimpleBasePlayer
    protected ListenableFuture<?> handleRelease() {
        this.remotePlayer.release();
        this.remotePlayer.setInternalSessionAvailabilityListener(null);
        if (this.localPlayer.isCommandAvailable(32)) {
            this.localPlayer.release();
        }
        return Futures.immediateVoidFuture();
    }

    private class SessionAvailabilityListenerImpl implements SessionAvailabilityListener {
        private SessionAvailabilityListenerImpl() {
        }

        @Override // androidx.media3.cast.SessionAvailabilityListener
        public void onCastSessionAvailable() {
            CastPlayerImpl.this.updateActivePlayer();
        }

        @Override // androidx.media3.cast.SessionAvailabilityListener
        public void onCastSessionUnavailable() {
            CastPlayerImpl.this.updateActivePlayer();
        }
    }
}
