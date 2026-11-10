package com.google.android.exoplayer;

import android.os.Handler;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.upstream.NetworkLock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultLoadControl implements LoadControl {
    private final Allocator allocator;
    private int bufferState;
    private final Handler eventHandler;
    private final EventListener eventListener;
    private boolean fillingBuffers;
    private final float highBufferLoad;
    private final long highWatermarkUs;
    private final HashMap<Object, LoaderState> loaderStates;
    private final List<Object> loaders;
    private final float lowBufferLoad;
    private final long lowWatermarkUs;
    private long maxLoadStartPositionUs;
    private boolean streamingPrioritySet;
    private int targetBufferSize;

    public interface EventListener {
        void onLoadingChanged(boolean z);
    }

    public DefaultLoadControl(Allocator allocator) {
        this(allocator, null, null);
    }

    public DefaultLoadControl(Allocator allocator, Handler handler, EventListener eventListener) {
        this(allocator, handler, eventListener, 15000, 30000, 0.2f, 0.8f);
    }

    public DefaultLoadControl(Allocator allocator, Handler handler, EventListener eventListener, int i, int i2, float f, float f2) {
        this.allocator = allocator;
        this.eventHandler = handler;
        this.eventListener = eventListener;
        this.loaders = new ArrayList();
        this.loaderStates = new HashMap<>();
        this.lowWatermarkUs = ((long) i) * 1000;
        this.highWatermarkUs = ((long) i2) * 1000;
        this.lowBufferLoad = f;
        this.highBufferLoad = f2;
    }

    @Override // com.google.android.exoplayer.LoadControl
    public void register(Object obj, int i) {
        this.loaders.add(obj);
        this.loaderStates.put(obj, new LoaderState(i));
        this.targetBufferSize += i;
    }

    @Override // com.google.android.exoplayer.LoadControl
    public void unregister(Object obj) {
        this.loaders.remove(obj);
        this.targetBufferSize -= this.loaderStates.remove(obj).bufferSizeContribution;
        updateControlState();
    }

    @Override // com.google.android.exoplayer.LoadControl
    public void trimAllocator() {
        this.allocator.trim(this.targetBufferSize);
    }

    @Override // com.google.android.exoplayer.LoadControl
    public Allocator getAllocator() {
        return this.allocator;
    }

    @Override // com.google.android.exoplayer.LoadControl
    public boolean update(Object obj, long j, long j2, boolean z) {
        int loaderBufferState = getLoaderBufferState(j, j2);
        LoaderState loaderState = this.loaderStates.get(obj);
        boolean z2 = (loaderState.bufferState == loaderBufferState && loaderState.nextLoadPositionUs == j2 && loaderState.loading == z) ? false : true;
        if (z2) {
            loaderState.bufferState = loaderBufferState;
            loaderState.nextLoadPositionUs = j2;
            loaderState.loading = z;
        }
        int totalBytesAllocated = this.allocator.getTotalBytesAllocated();
        int bufferState = getBufferState(totalBytesAllocated);
        boolean z3 = this.bufferState != bufferState;
        if (z3) {
            this.bufferState = bufferState;
        }
        if (z2 || z3) {
            updateControlState();
        }
        return (totalBytesAllocated < this.targetBufferSize || loaderBufferState == 2) && j2 != -1 && j2 <= this.maxLoadStartPositionUs;
    }

    private int getLoaderBufferState(long j, long j2) {
        if (j2 == -1) {
            return 0;
        }
        long j3 = j2 - j;
        if (j3 > this.highWatermarkUs) {
            return 0;
        }
        return j3 < this.lowWatermarkUs ? 2 : 1;
    }

    private int getBufferState(int i) {
        float f = i / this.targetBufferSize;
        if (f > this.highBufferLoad) {
            return 0;
        }
        return f < this.lowBufferLoad ? 2 : 1;
    }

    private void updateControlState() {
        int iMax = this.bufferState;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        while (true) {
            boolean z3 = true;
            if (i >= this.loaders.size()) {
                break;
            }
            LoaderState loaderState = this.loaderStates.get(this.loaders.get(i));
            z |= loaderState.loading;
            if (loaderState.nextLoadPositionUs == -1) {
                z3 = false;
            }
            z2 |= z3;
            iMax = Math.max(iMax, loaderState.bufferState);
            i++;
        }
        boolean z4 = !this.loaders.isEmpty() && (z || z2) && (iMax == 2 || (iMax == 1 && this.fillingBuffers));
        this.fillingBuffers = z4;
        if (z4 && !this.streamingPrioritySet) {
            NetworkLock.instance.add(0);
            this.streamingPrioritySet = true;
            notifyLoadingChanged(true);
        } else if (!this.fillingBuffers && this.streamingPrioritySet && !z) {
            NetworkLock.instance.remove(0);
            this.streamingPrioritySet = false;
            notifyLoadingChanged(false);
        }
        this.maxLoadStartPositionUs = -1L;
        if (this.fillingBuffers) {
            for (int i2 = 0; i2 < this.loaders.size(); i2++) {
                long j = this.loaderStates.get(this.loaders.get(i2)).nextLoadPositionUs;
                if (j != -1) {
                    long j2 = this.maxLoadStartPositionUs;
                    if (j2 == -1 || j < j2) {
                        this.maxLoadStartPositionUs = j;
                    }
                }
            }
        }
    }

    private void notifyLoadingChanged(final boolean z) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.DefaultLoadControl.1
            @Override // java.lang.Runnable
            public void run() {
                DefaultLoadControl.this.eventListener.onLoadingChanged(z);
            }
        });
    }

    private static class LoaderState {
        public final int bufferSizeContribution;
        public int bufferState = 0;
        public boolean loading = false;
        public long nextLoadPositionUs = -1;

        public LoaderState(int i) {
            this.bufferSizeContribution = i;
        }
    }
}
