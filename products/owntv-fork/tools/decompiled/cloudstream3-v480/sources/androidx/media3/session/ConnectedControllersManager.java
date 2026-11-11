package androidx.media3.session;

import androidx.collection.ArrayMap;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.util.Util;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes4.dex */
final class ConnectedControllersManager<T> {
    private final ArrayMap<T, MediaSession.ControllerInfo> controllerInfoMap = new ArrayMap<>();
    private final ArrayMap<MediaSession.ControllerInfo, ConnectedControllerRecord<T>> controllerRecords = new ArrayMap<>();
    private final Object lock = new Object();
    private final WeakReference<MediaSessionImpl> sessionImpl;

    public interface AsyncCommand {
        ListenableFuture<Void> run();
    }

    public ConnectedControllersManager(MediaSessionImpl mediaSessionImpl) {
        this.sessionImpl = new WeakReference<>(mediaSessionImpl);
    }

    public void addController(T t, MediaSession.ControllerInfo controllerInfo, SessionCommands sessionCommands, Player.Commands commands) {
        synchronized (this.lock) {
            MediaSession.ControllerInfo controller = getController(t);
            if (controller == null) {
                this.controllerInfoMap.put(t, controllerInfo);
                this.controllerRecords.put(controllerInfo, new ConnectedControllerRecord<>(t, new SequencedFutureManager(), sessionCommands, commands));
            } else {
                ConnectedControllerRecord connectedControllerRecord = (ConnectedControllerRecord) Preconditions.checkNotNull(this.controllerRecords.get(controller));
                connectedControllerRecord.sessionCommands = sessionCommands;
                connectedControllerRecord.playerCommands = commands;
            }
        }
    }

    public void updateCommandsFromSession(MediaSession.ControllerInfo controllerInfo, SessionCommands sessionCommands, Player.Commands commands) {
        synchronized (this.lock) {
            ConnectedControllerRecord<T> connectedControllerRecord = this.controllerRecords.get(controllerInfo);
            if (connectedControllerRecord != null) {
                connectedControllerRecord.sessionCommands = sessionCommands;
                if (connectedControllerRecord.playerCommandsBeforePlaybackException != null) {
                    connectedControllerRecord.playerCommandsBeforePlaybackException = commands;
                } else {
                    connectedControllerRecord.playerCommands = commands;
                }
            }
        }
    }

    public Player.Commands getAvailablePlayerCommands(MediaSession.ControllerInfo controllerInfo) {
        synchronized (this.lock) {
            ConnectedControllerRecord<T> connectedControllerRecord = this.controllerRecords.get(controllerInfo);
            if (connectedControllerRecord == null) {
                return null;
            }
            return connectedControllerRecord.playerCommands;
        }
    }

    public SessionCommands getAvailableSessionCommands(MediaSession.ControllerInfo controllerInfo) {
        synchronized (this.lock) {
            ConnectedControllerRecord<T> connectedControllerRecord = this.controllerRecords.get(controllerInfo);
            if (connectedControllerRecord == null) {
                return null;
            }
            return connectedControllerRecord.sessionCommands;
        }
    }

    public void setPlaybackException(MediaSession.ControllerInfo controllerInfo, PlaybackException playbackException, Player.Commands commands) {
        synchronized (this.lock) {
            ConnectedControllerRecord<T> connectedControllerRecord = this.controllerRecords.get(controllerInfo);
            if (connectedControllerRecord != null) {
                connectedControllerRecord.playbackException = playbackException;
                connectedControllerRecord.playerCommandsBeforePlaybackException = commands;
                connectedControllerRecord.playerInfoForPlaybackException = null;
            }
        }
    }

    public void resetPlaybackException(MediaSession.ControllerInfo controllerInfo) {
        synchronized (this.lock) {
            ConnectedControllerRecord<T> connectedControllerRecord = this.controllerRecords.get(controllerInfo);
            if (connectedControllerRecord != null) {
                connectedControllerRecord.playbackException = null;
                connectedControllerRecord.playerCommandsBeforePlaybackException = null;
                connectedControllerRecord.playerInfoForPlaybackException = null;
            }
        }
    }

    public void setPlayerInfoForPlaybackException(MediaSession.ControllerInfo controllerInfo, PlayerInfo playerInfo) {
        synchronized (this.lock) {
            ConnectedControllerRecord<T> connectedControllerRecord = this.controllerRecords.get(controllerInfo);
            if (connectedControllerRecord != null) {
                Preconditions.checkNotNull(connectedControllerRecord.playbackException);
                connectedControllerRecord.playerInfoForPlaybackException = playerInfo;
            }
        }
    }

    public PlaybackException getPlaybackException(MediaSession.ControllerInfo controllerInfo) {
        synchronized (this.lock) {
            ConnectedControllerRecord<T> connectedControllerRecord = this.controllerRecords.get(controllerInfo);
            if (connectedControllerRecord == null) {
                return null;
            }
            return connectedControllerRecord.playbackException;
        }
    }

    public PlayerInfo getPlayerInfoForPlaybackException(MediaSession.ControllerInfo controllerInfo) {
        synchronized (this.lock) {
            ConnectedControllerRecord<T> connectedControllerRecord = this.controllerRecords.get(controllerInfo);
            if (connectedControllerRecord == null) {
                return null;
            }
            return connectedControllerRecord.playerInfoForPlaybackException;
        }
    }

    public Player.Commands getPlayerCommandsBeforePlaybackException(MediaSession.ControllerInfo controllerInfo) {
        synchronized (this.lock) {
            ConnectedControllerRecord<T> connectedControllerRecord = this.controllerRecords.get(controllerInfo);
            if (connectedControllerRecord == null) {
                return null;
            }
            return connectedControllerRecord.playerCommandsBeforePlaybackException;
        }
    }

    public void removeController(T t) {
        MediaSession.ControllerInfo controller = getController(t);
        if (controller != null) {
            removeController(controller);
        }
    }

    public void removeController(final MediaSession.ControllerInfo controllerInfo) {
        synchronized (this.lock) {
            ConnectedControllerRecord<T> connectedControllerRecordRemove = this.controllerRecords.remove(controllerInfo);
            if (connectedControllerRecordRemove == null) {
                return;
            }
            this.controllerInfoMap.remove(connectedControllerRecordRemove.controllerKey);
            connectedControllerRecordRemove.sequencedFutureManager.release();
            final MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
            if (mediaSessionImpl == null || mediaSessionImpl.isReleased()) {
                return;
            }
            Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.ConnectedControllersManager$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    ConnectedControllersManager.lambda$removeController$0(mediaSessionImpl, controllerInfo);
                }
            });
        }
    }

    static /* synthetic */ void lambda$removeController$0(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo) {
        if (mediaSessionImpl.isReleased()) {
            return;
        }
        mediaSessionImpl.onDisconnectedOnHandler(controllerInfo);
    }

    public ImmutableList<MediaSession.ControllerInfo> getConnectedControllers() {
        ImmutableList<MediaSession.ControllerInfo> immutableListCopyOf;
        synchronized (this.lock) {
            immutableListCopyOf = ImmutableList.copyOf((Collection) this.controllerInfoMap.values());
        }
        return immutableListCopyOf;
    }

    public boolean isConnected(MediaSession.ControllerInfo controllerInfo) {
        boolean z;
        synchronized (this.lock) {
            z = this.controllerRecords.get(controllerInfo) != null;
        }
        return z;
    }

    public SequencedFutureManager getSequencedFutureManager(MediaSession.ControllerInfo controllerInfo) {
        ConnectedControllerRecord<T> connectedControllerRecord;
        synchronized (this.lock) {
            connectedControllerRecord = this.controllerRecords.get(controllerInfo);
        }
        if (connectedControllerRecord != null) {
            return connectedControllerRecord.sequencedFutureManager;
        }
        return null;
    }

    public SequencedFutureManager getSequencedFutureManager(T t) {
        ConnectedControllerRecord<T> connectedControllerRecord;
        synchronized (this.lock) {
            MediaSession.ControllerInfo controller = getController(t);
            connectedControllerRecord = controller != null ? this.controllerRecords.get(controller) : null;
        }
        if (connectedControllerRecord != null) {
            return connectedControllerRecord.sequencedFutureManager;
        }
        return null;
    }

    public boolean isSessionCommandAvailable(MediaSession.ControllerInfo controllerInfo, SessionCommand sessionCommand) {
        ConnectedControllerRecord<T> connectedControllerRecord;
        synchronized (this.lock) {
            connectedControllerRecord = this.controllerRecords.get(controllerInfo);
        }
        if (connectedControllerRecord != null) {
            return connectedControllerRecord.sessionCommands.contains(sessionCommand) || CommandButton.isPredefinedCustomCommandButtonCode(sessionCommand.customAction);
        }
        return false;
    }

    public boolean isSessionCommandAvailable(MediaSession.ControllerInfo controllerInfo, int i) {
        ConnectedControllerRecord<T> connectedControllerRecord;
        synchronized (this.lock) {
            connectedControllerRecord = this.controllerRecords.get(controllerInfo);
        }
        return connectedControllerRecord != null && connectedControllerRecord.sessionCommands.contains(i);
    }

    public boolean isPlayerCommandAvailable(MediaSession.ControllerInfo controllerInfo, int i) {
        ConnectedControllerRecord<T> connectedControllerRecord;
        synchronized (this.lock) {
            connectedControllerRecord = this.controllerRecords.get(controllerInfo);
        }
        MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
        return connectedControllerRecord != null && connectedControllerRecord.playerCommands.contains(i) && mediaSessionImpl != null && mediaSessionImpl.getPlayerWrapper().getAvailableCommands().contains(i);
    }

    public MediaSession.ControllerInfo getController(T t) {
        MediaSession.ControllerInfo controllerInfo;
        synchronized (this.lock) {
            controllerInfo = this.controllerInfoMap.get(t);
        }
        return controllerInfo;
    }

    public void addToCommandQueue(MediaSession.ControllerInfo controllerInfo, int i, AsyncCommand asyncCommand) {
        synchronized (this.lock) {
            ConnectedControllerRecord<T> connectedControllerRecord = this.controllerRecords.get(controllerInfo);
            if (connectedControllerRecord != null) {
                connectedControllerRecord.commandQueuePlayerCommands = connectedControllerRecord.commandQueuePlayerCommands.buildUpon().add(i).build();
                connectedControllerRecord.commandQueue.add(asyncCommand);
            }
        }
    }

    public void flushCommandQueue(final MediaSession.ControllerInfo controllerInfo) {
        synchronized (this.lock) {
            ConnectedControllerRecord<T> connectedControllerRecord = this.controllerRecords.get(controllerInfo);
            if (connectedControllerRecord == null) {
                return;
            }
            final Player.Commands commands = connectedControllerRecord.commandQueuePlayerCommands;
            connectedControllerRecord.commandQueuePlayerCommands = Player.Commands.EMPTY;
            connectedControllerRecord.commandQueue.add(new AsyncCommand() { // from class: androidx.media3.session.ConnectedControllersManager$$ExternalSyntheticLambda1
                @Override // androidx.media3.session.ConnectedControllersManager.AsyncCommand
                public final ListenableFuture run() {
                    return this.f$0.m7636x2298a984(controllerInfo, commands);
                }
            });
            if (connectedControllerRecord.commandQueueIsFlushing) {
                return;
            }
            connectedControllerRecord.commandQueueIsFlushing = true;
            flushCommandQueue(connectedControllerRecord);
        }
    }

    /* JADX INFO: renamed from: lambda$flushCommandQueue$1$androidx-media3-session-ConnectedControllersManager, reason: not valid java name */
    /* synthetic */ ListenableFuture m7636x2298a984(MediaSession.ControllerInfo controllerInfo, Player.Commands commands) {
        MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
        if (mediaSessionImpl != null) {
            mediaSessionImpl.onPlayerInteractionFinishedOnHandler(controllerInfo, commands);
        }
        return Futures.immediateVoidFuture();
    }

    private void flushCommandQueue(final ConnectedControllerRecord<T> connectedControllerRecord) {
        MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
        if (mediaSessionImpl == null) {
            return;
        }
        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        while (atomicBoolean.get()) {
            atomicBoolean.set(false);
            final AsyncCommand asyncCommandPoll = connectedControllerRecord.commandQueue.poll();
            if (asyncCommandPoll == null) {
                connectedControllerRecord.commandQueueIsFlushing = false;
                return;
            }
            final AtomicBoolean atomicBoolean2 = new AtomicBoolean(true);
            final ConnectedControllersManager<T> connectedControllersManager = this;
            Util.postOrRun(mediaSessionImpl.getApplicationHandler(), mediaSessionImpl.callWithControllerForCurrentRequestSet(this.getController(connectedControllerRecord.controllerKey), new Runnable() { // from class: androidx.media3.session.ConnectedControllersManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m7638x3d0e0c86(asyncCommandPoll, atomicBoolean2, connectedControllerRecord, atomicBoolean);
                }
            }));
            atomicBoolean2.set(false);
            this = connectedControllersManager;
        }
    }

    /* JADX INFO: renamed from: lambda$flushCommandQueue$3$androidx-media3-session-ConnectedControllersManager, reason: not valid java name */
    /* synthetic */ void m7638x3d0e0c86(AsyncCommand asyncCommand, final AtomicBoolean atomicBoolean, final ConnectedControllerRecord connectedControllerRecord, final AtomicBoolean atomicBoolean2) {
        asyncCommand.run().addListener(new Runnable() { // from class: androidx.media3.session.ConnectedControllersManager$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7637xafd35b05(atomicBoolean, connectedControllerRecord, atomicBoolean2);
            }
        }, MoreExecutors.directExecutor());
    }

    /* JADX INFO: renamed from: lambda$flushCommandQueue$2$androidx-media3-session-ConnectedControllersManager, reason: not valid java name */
    /* synthetic */ void m7637xafd35b05(AtomicBoolean atomicBoolean, ConnectedControllerRecord connectedControllerRecord, AtomicBoolean atomicBoolean2) {
        synchronized (this.lock) {
            if (!atomicBoolean.get()) {
                flushCommandQueue(connectedControllerRecord);
            } else {
                atomicBoolean2.set(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class ConnectedControllerRecord<T> {
        public boolean commandQueueIsFlushing;
        public final T controllerKey;
        public PlaybackException playbackException;
        public Player.Commands playerCommands;
        public Player.Commands playerCommandsBeforePlaybackException;
        public PlayerInfo playerInfoForPlaybackException;
        public final SequencedFutureManager sequencedFutureManager;
        public SessionCommands sessionCommands;
        public final Deque<AsyncCommand> commandQueue = new ArrayDeque();
        public Player.Commands commandQueuePlayerCommands = Player.Commands.EMPTY;

        public ConnectedControllerRecord(T t, SequencedFutureManager sequencedFutureManager, SessionCommands sessionCommands, Player.Commands commands) {
            this.controllerKey = t;
            this.sequencedFutureManager = sequencedFutureManager;
            this.sessionCommands = sessionCommands;
            this.playerCommands = commands;
        }
    }
}
