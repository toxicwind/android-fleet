package com.google.android.exoplayer;

import android.os.SystemClock;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
final class StandaloneMediaClock implements MediaClock {
    private long deltaUs;
    private long positionUs;
    private double speed;
    private boolean speeding;
    private boolean started;
    private long startingSpeedingDeltaUs;
    private long startingSpeedingPositionUs;

    StandaloneMediaClock() {
    }

    public void start() {
        if (this.started) {
            return;
        }
        this.started = true;
        this.deltaUs = elapsedRealtimeMinus(this.positionUs);
    }

    public void stop() {
        if (this.started) {
            this.positionUs = elapsedRealtimeMinus(this.deltaUs);
            this.started = false;
        }
    }

    public void setPositionUs(long j) {
        this.positionUs = j;
        this.deltaUs = elapsedRealtimeMinus(j);
    }

    @Override // com.google.android.exoplayer.MediaClock
    public long getPositionUs() {
        if (this.started) {
            if (this.speeding) {
                long jElapsedRealtimeMinus = ((long) (elapsedRealtimeMinus(this.startingSpeedingDeltaUs) * this.speed)) + this.startingSpeedingPositionUs;
                setPositionUs(jElapsedRealtimeMinus);
                return jElapsedRealtimeMinus;
            }
            return elapsedRealtimeMinus(this.deltaUs);
        }
        return this.positionUs;
    }

    private long elapsedRealtimeMinus(long j) {
        return (SystemClock.elapsedRealtime() * 1000) - j;
    }

    public void setSpeed(double d) {
        this.speed = d;
    }

    public void startSpeeding() {
        if (this.speeding) {
            return;
        }
        this.speeding = true;
        this.startingSpeedingPositionUs = this.positionUs;
        this.startingSpeedingDeltaUs = elapsedRealtimeMinus(0L);
        Log.d("StandaloneMediaClock", "startingSpeedingPositionUs = " + this.startingSpeedingPositionUs + ", startingSpeedingDeltaUs = " + this.startingSpeedingDeltaUs);
    }

    public void stopSpeeding() {
        if (this.speeding) {
            this.speeding = false;
            if (this.speed != 1.0d) {
                Log.d("StandaloneMediaClock", "positionUs = " + this.positionUs);
                setPositionUs((long) ((((double) elapsedRealtimeMinus(this.startingSpeedingDeltaUs)) * this.speed) + ((double) this.startingSpeedingPositionUs)));
                Log.d("StandaloneMediaClock", "positionUs = " + this.positionUs);
            }
        }
    }
}
