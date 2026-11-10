package com.google.firebase.firestore.remote;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import c.a.za;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.OnlineState;
import com.google.firebase.firestore.core.Transaction;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.model.mutation.MutationBatchResult;
import com.google.firebase.firestore.model.mutation.MutationResult;
import com.google.firebase.firestore.remote.ConnectivityMonitor;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.ByteString;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class RemoteStore implements WatchChangeAggregator.TargetMetadataProvider {
    public static final String LOG_TAG = "RemoteStore";
    public static final int MAX_PENDING_WRITES = 10;
    public final ConnectivityMonitor connectivityMonitor;
    public final Datastore datastore;
    public final LocalStore localStore;
    public final OnlineStateTracker onlineStateTracker;
    public final RemoteStoreCallback remoteStoreCallback;

    @Nullable
    public WatchChangeAggregator watchChangeAggregator;
    public final WatchStream watchStream;
    public final WriteStream writeStream;
    public boolean networkEnabled = false;
    public final Map<Integer, TargetData> listenTargets = new HashMap();
    public final Deque<MutationBatch> writePipeline = new ArrayDeque();

    public interface RemoteStoreCallback {
        ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int i);

        void handleOnlineStateChange(OnlineState onlineState);

        void handleRejectedListen(int i, za zaVar);

        void handleRejectedWrite(int i, za zaVar);

        void handleRemoteEvent(RemoteEvent remoteEvent);

        void handleSuccessfulWrite(MutationBatchResult mutationBatchResult);
    }

    public RemoteStore(final RemoteStoreCallback remoteStoreCallback, LocalStore localStore, Datastore datastore, final AsyncQueue asyncQueue, ConnectivityMonitor connectivityMonitor) {
        this.remoteStoreCallback = remoteStoreCallback;
        this.localStore = localStore;
        this.datastore = datastore;
        this.connectivityMonitor = connectivityMonitor;
        Objects.requireNonNull(remoteStoreCallback);
        this.onlineStateTracker = new OnlineStateTracker(asyncQueue, new OnlineStateTracker.OnlineStateCallback() { // from class: b.e.b.d.f.z
            @Override // com.google.firebase.firestore.remote.OnlineStateTracker.OnlineStateCallback
            public final void handleOnlineStateChange(OnlineState onlineState) {
                remoteStoreCallback.handleOnlineStateChange(onlineState);
            }
        });
        this.watchStream = datastore.createWatchStream(new WatchStream.Callback() { // from class: com.google.firebase.firestore.remote.RemoteStore.1
            @Override // com.google.firebase.firestore.remote.Stream.StreamCallback
            public void onClose(za zaVar) {
                RemoteStore.this.handleWatchStreamClose(zaVar);
            }

            @Override // com.google.firebase.firestore.remote.Stream.StreamCallback
            public void onOpen() {
                RemoteStore.this.handleWatchStreamOpen();
            }

            @Override // com.google.firebase.firestore.remote.WatchStream.Callback
            public void onWatchChange(SnapshotVersion snapshotVersion, WatchChange watchChange) {
                RemoteStore.this.handleWatchChange(snapshotVersion, watchChange);
            }
        });
        this.writeStream = datastore.createWriteStream(new WriteStream.Callback() { // from class: com.google.firebase.firestore.remote.RemoteStore.2
            @Override // com.google.firebase.firestore.remote.Stream.StreamCallback
            public void onClose(za zaVar) {
                RemoteStore.this.handleWriteStreamClose(zaVar);
            }

            @Override // com.google.firebase.firestore.remote.WriteStream.Callback
            public void onHandshakeComplete() {
                RemoteStore.this.handleWriteStreamHandshakeComplete();
            }

            @Override // com.google.firebase.firestore.remote.Stream.StreamCallback
            public void onOpen() {
                RemoteStore.this.writeStream.writeHandshake();
            }

            @Override // com.google.firebase.firestore.remote.WriteStream.Callback
            public void onWriteResponse(SnapshotVersion snapshotVersion, List<MutationResult> list) {
                RemoteStore.this.handleWriteStreamMutationResults(snapshotVersion, list);
            }
        });
        connectivityMonitor.addCallback(new Consumer() { // from class: b.e.b.d.f.x
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f529a.a(asyncQueue, (ConnectivityMonitor.NetworkStatus) obj);
            }
        });
    }

    private void addToWritePipeline(MutationBatch mutationBatch) {
        Assert.hardAssert(canAddToWritePipeline(), "addToWritePipeline called when pipeline is full", new Object[0]);
        this.writePipeline.add(mutationBatch);
        if (this.writeStream.isOpen() && this.writeStream.isHandshakeComplete()) {
            this.writeStream.writeMutations(mutationBatch.getMutations());
        }
    }

    private boolean canAddToWritePipeline() {
        return canUseNetwork() && this.writePipeline.size() < 10;
    }

    private void cleanUpWatchStreamState() {
        this.watchChangeAggregator = null;
    }

    private void disableNetworkInternal() {
        this.watchStream.stop();
        this.writeStream.stop();
        if (!this.writePipeline.isEmpty()) {
            Logger.debug(LOG_TAG, "Stopping write stream with %d pending writes", Integer.valueOf(this.writePipeline.size()));
            this.writePipeline.clear();
        }
        this.watchChangeAggregator = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWatchChange(SnapshotVersion snapshotVersion, WatchChange watchChange) {
        this.onlineStateTracker.updateState(OnlineState.ONLINE);
        Assert.hardAssert((this.watchStream == null || this.watchChangeAggregator == null) ? false : true, "WatchStream and WatchStreamAggregator should both be non-null", new Object[0]);
        boolean z = watchChange instanceof WatchChange.WatchTargetChange;
        WatchChange.WatchTargetChange watchTargetChange = z ? (WatchChange.WatchTargetChange) watchChange : null;
        if (watchTargetChange != null && watchTargetChange.getChangeType().equals(WatchChange.WatchTargetChangeType.Removed) && watchTargetChange.getCause() != null) {
            processTargetError(watchTargetChange);
            return;
        }
        if (watchChange instanceof WatchChange.DocumentChange) {
            this.watchChangeAggregator.handleDocumentChange((WatchChange.DocumentChange) watchChange);
        } else if (watchChange instanceof WatchChange.ExistenceFilterWatchChange) {
            this.watchChangeAggregator.handleExistenceFilter((WatchChange.ExistenceFilterWatchChange) watchChange);
        } else {
            Assert.hardAssert(z, "Expected watchChange to be an instance of WatchTargetChange", new Object[0]);
            this.watchChangeAggregator.handleTargetChange((WatchChange.WatchTargetChange) watchChange);
        }
        if (snapshotVersion.equals(SnapshotVersion.NONE) || snapshotVersion.compareTo(this.localStore.getLastRemoteSnapshotVersion()) < 0) {
            return;
        }
        raiseWatchSnapshot(snapshotVersion);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWatchStreamClose(za zaVar) {
        if (zaVar.c()) {
            Assert.hardAssert(!shouldStartWatchStream(), "Watch stream was stopped gracefully while still needed.", new Object[0]);
        }
        this.watchChangeAggregator = null;
        if (!shouldStartWatchStream()) {
            this.onlineStateTracker.updateState(OnlineState.UNKNOWN);
        } else {
            this.onlineStateTracker.handleWatchStreamFailure(zaVar);
            startWatchStream();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWatchStreamOpen() {
        Iterator<TargetData> it = this.listenTargets.values().iterator();
        while (it.hasNext()) {
            sendWatchRequest(it.next());
        }
    }

    private void handleWriteError(za zaVar) {
        Assert.hardAssert(!zaVar.c(), "Handling write error with status OK.", new Object[0]);
        if (Datastore.isPermanentWriteError(zaVar)) {
            MutationBatch mutationBatchPoll = this.writePipeline.poll();
            this.writeStream.inhibitBackoff();
            this.remoteStoreCallback.handleRejectedWrite(mutationBatchPoll.getBatchId(), zaVar);
            fillWritePipeline();
        }
    }

    private void handleWriteHandshakeError(za zaVar) {
        Assert.hardAssert(!zaVar.c(), "Handling write error with status OK.", new Object[0]);
        if (Datastore.isPermanentError(zaVar)) {
            Logger.debug(LOG_TAG, "RemoteStore error before completed handshake; resetting stream token %s: %s", Util.toDebugString(this.writeStream.getLastStreamToken()), zaVar);
            this.writeStream.setLastStreamToken(WriteStream.EMPTY_STREAM_TOKEN);
            this.localStore.setLastStreamToken(WriteStream.EMPTY_STREAM_TOKEN);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWriteStreamClose(za zaVar) {
        if (zaVar.c()) {
            Assert.hardAssert(!shouldStartWriteStream(), "Write stream was stopped gracefully while still needed.", new Object[0]);
        }
        if (!zaVar.c() && !this.writePipeline.isEmpty()) {
            if (this.writeStream.isHandshakeComplete()) {
                handleWriteError(zaVar);
            } else {
                handleWriteHandshakeError(zaVar);
            }
        }
        if (shouldStartWriteStream()) {
            startWriteStream();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWriteStreamHandshakeComplete() {
        this.localStore.setLastStreamToken(this.writeStream.getLastStreamToken());
        Iterator<MutationBatch> it = this.writePipeline.iterator();
        while (it.hasNext()) {
            this.writeStream.writeMutations(it.next().getMutations());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWriteStreamMutationResults(SnapshotVersion snapshotVersion, List<MutationResult> list) {
        this.remoteStoreCallback.handleSuccessfulWrite(MutationBatchResult.create(this.writePipeline.poll(), snapshotVersion, list, this.writeStream.getLastStreamToken()));
        fillWritePipeline();
    }

    private void processTargetError(WatchChange.WatchTargetChange watchTargetChange) {
        Assert.hardAssert(watchTargetChange.getCause() != null, "Processing target error without a cause", new Object[0]);
        for (Integer num : watchTargetChange.getTargetIds()) {
            if (this.listenTargets.containsKey(num)) {
                this.listenTargets.remove(num);
                this.watchChangeAggregator.removeTarget(num.intValue());
                this.remoteStoreCallback.handleRejectedListen(num.intValue(), watchTargetChange.getCause());
            }
        }
    }

    private void raiseWatchSnapshot(SnapshotVersion snapshotVersion) {
        Assert.hardAssert(!snapshotVersion.equals(SnapshotVersion.NONE), "Can't raise event for unknown SnapshotVersion", new Object[0]);
        RemoteEvent remoteEventCreateRemoteEvent = this.watchChangeAggregator.createRemoteEvent(snapshotVersion);
        for (Map.Entry<Integer, TargetChange> entry : remoteEventCreateRemoteEvent.getTargetChanges().entrySet()) {
            TargetChange value = entry.getValue();
            if (!value.getResumeToken().isEmpty()) {
                int iIntValue = entry.getKey().intValue();
                TargetData targetData = this.listenTargets.get(Integer.valueOf(iIntValue));
                if (targetData != null) {
                    this.listenTargets.put(Integer.valueOf(iIntValue), targetData.withResumeToken(value.getResumeToken(), snapshotVersion));
                }
            }
        }
        Iterator<Integer> it = remoteEventCreateRemoteEvent.getTargetMismatches().iterator();
        while (it.hasNext()) {
            int iIntValue2 = it.next().intValue();
            TargetData targetData2 = this.listenTargets.get(Integer.valueOf(iIntValue2));
            if (targetData2 != null) {
                this.listenTargets.put(Integer.valueOf(iIntValue2), targetData2.withResumeToken(ByteString.EMPTY, targetData2.getSnapshotVersion()));
                sendUnwatchRequest(iIntValue2);
                TargetData targetData3 = new TargetData(targetData2.getTarget(), iIntValue2, targetData2.getSequenceNumber(), QueryPurpose.EXISTENCE_FILTER_MISMATCH);
                this.watchChangeAggregator.recordPendingTargetRequest(targetData3.getTargetId());
                this.watchStream.watchQuery(targetData3);
            }
        }
        this.remoteStoreCallback.handleRemoteEvent(remoteEventCreateRemoteEvent);
    }

    private void restartNetwork() {
        this.networkEnabled = false;
        disableNetworkInternal();
        this.onlineStateTracker.updateState(OnlineState.UNKNOWN);
        this.writeStream.inhibitBackoff();
        this.watchStream.inhibitBackoff();
        enableNetwork();
    }

    private void sendUnwatchRequest(int i) {
        this.watchChangeAggregator.recordPendingTargetRequest(i);
        this.watchStream.unwatchTarget(i);
    }

    private void sendWatchRequest(TargetData targetData) {
        this.watchChangeAggregator.recordPendingTargetRequest(targetData.getTargetId());
        this.watchStream.watchQuery(targetData);
    }

    private boolean shouldStartWatchStream() {
        return (!canUseNetwork() || this.watchStream.isStarted() || this.listenTargets.isEmpty()) ? false : true;
    }

    private boolean shouldStartWriteStream() {
        return (!canUseNetwork() || this.writeStream.isStarted() || this.writePipeline.isEmpty()) ? false : true;
    }

    private void startWatchStream() {
        Assert.hardAssert(shouldStartWatchStream(), "startWatchStream() called when shouldStartWatchStream() is false.", new Object[0]);
        this.watchChangeAggregator = new WatchChangeAggregator(this);
        this.watchStream.start();
        this.onlineStateTracker.handleWatchStreamStart();
    }

    private void startWriteStream() {
        Assert.hardAssert(shouldStartWriteStream(), "startWriteStream() called when shouldStartWriteStream() is false.", new Object[0]);
        this.writeStream.start();
    }

    public /* synthetic */ void a(AsyncQueue asyncQueue, final ConnectivityMonitor.NetworkStatus networkStatus) {
        asyncQueue.enqueueAndForget(new Runnable() { // from class: b.e.b.d.f.y
            @Override // java.lang.Runnable
            public final void run() {
                this.f531a.a(networkStatus);
            }
        });
    }

    public boolean canUseNetwork() {
        return this.networkEnabled;
    }

    public Transaction createTransaction() {
        return new Transaction(this.datastore);
    }

    public void disableNetwork() {
        this.networkEnabled = false;
        disableNetworkInternal();
        this.onlineStateTracker.updateState(OnlineState.OFFLINE);
    }

    public void enableNetwork() {
        this.networkEnabled = true;
        if (canUseNetwork()) {
            this.writeStream.setLastStreamToken(this.localStore.getLastStreamToken());
            if (shouldStartWatchStream()) {
                startWatchStream();
            } else {
                this.onlineStateTracker.updateState(OnlineState.UNKNOWN);
            }
            fillWritePipeline();
        }
    }

    public void fillWritePipeline() {
        int batchId = this.writePipeline.isEmpty() ? -1 : this.writePipeline.getLast().getBatchId();
        while (canAddToWritePipeline()) {
            MutationBatch nextMutationBatch = this.localStore.getNextMutationBatch(batchId);
            if (nextMutationBatch == null) {
                if (this.writePipeline.size() != 0) {
                    break;
                }
                this.writeStream.markIdle();
                break;
            }
            addToWritePipeline(nextMutationBatch);
            batchId = nextMutationBatch.getBatchId();
        }
        if (shouldStartWriteStream()) {
            startWriteStream();
        }
    }

    @VisibleForTesting
    public void forceEnableNetwork() {
        enableNetwork();
        this.onlineStateTracker.updateState(OnlineState.ONLINE);
    }

    @Override // com.google.firebase.firestore.remote.WatchChangeAggregator.TargetMetadataProvider
    public ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int i) {
        return this.remoteStoreCallback.getRemoteKeysForTarget(i);
    }

    @Override // com.google.firebase.firestore.remote.WatchChangeAggregator.TargetMetadataProvider
    @Nullable
    public TargetData getTargetDataForTarget(int i) {
        return this.listenTargets.get(Integer.valueOf(i));
    }

    public void handleCredentialChange() {
        if (canUseNetwork()) {
            Logger.debug(LOG_TAG, "Restarting streams for new credential.", new Object[0]);
            restartNetwork();
        }
    }

    public void listen(TargetData targetData) {
        Integer numValueOf = Integer.valueOf(targetData.getTargetId());
        if (this.listenTargets.containsKey(numValueOf)) {
            return;
        }
        this.listenTargets.put(numValueOf, targetData);
        if (shouldStartWatchStream()) {
            startWatchStream();
        } else if (this.watchStream.isOpen()) {
            this.watchChangeAggregator.recordPendingTargetRequest(targetData.getTargetId());
            this.watchStream.watchQuery(targetData);
        }
    }

    public void shutdown() {
        Logger.debug(LOG_TAG, "Shutting down", new Object[0]);
        this.connectivityMonitor.shutdown();
        this.networkEnabled = false;
        disableNetworkInternal();
        this.datastore.shutdown();
        this.onlineStateTracker.updateState(OnlineState.UNKNOWN);
    }

    public void start() {
        enableNetwork();
    }

    public void stopListening(int i) {
        Assert.hardAssert(this.listenTargets.remove(Integer.valueOf(i)) != null, "stopListening called on target no currently watched: %d", Integer.valueOf(i));
        if (this.watchStream.isOpen()) {
            this.watchChangeAggregator.recordPendingTargetRequest(i);
            this.watchStream.unwatchTarget(i);
        }
        if (this.listenTargets.isEmpty()) {
            if (this.watchStream.isOpen()) {
                this.watchStream.markIdle();
            } else if (canUseNetwork()) {
                this.onlineStateTracker.updateState(OnlineState.UNKNOWN);
            }
        }
    }

    public /* synthetic */ void a(ConnectivityMonitor.NetworkStatus networkStatus) {
        if (networkStatus.equals(ConnectivityMonitor.NetworkStatus.REACHABLE) && this.onlineStateTracker.getState().equals(OnlineState.ONLINE)) {
            return;
        }
        if (!(networkStatus.equals(ConnectivityMonitor.NetworkStatus.UNREACHABLE) && this.onlineStateTracker.getState().equals(OnlineState.OFFLINE)) && canUseNetwork()) {
            Logger.debug(LOG_TAG, "Restarting streams for network reachability change.", new Object[0]);
            restartNetwork();
        }
    }
}
