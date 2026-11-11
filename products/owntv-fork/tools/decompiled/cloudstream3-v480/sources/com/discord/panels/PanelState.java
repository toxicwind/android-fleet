package com.discord.panels;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PanelState.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/discord/panels/PanelState;", "", "()V", "Closed", "Closing", "Opened", "Opening", "Lcom/discord/panels/PanelState$Opening;", "Lcom/discord/panels/PanelState$Opened;", "Lcom/discord/panels/PanelState$Closing;", "Lcom/discord/panels/PanelState$Closed;", "overlapping_panels_release"}, k = 1, mv = {1, 1, 16})
public abstract class PanelState {

    /* JADX INFO: compiled from: PanelState.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/discord/panels/PanelState$Opening;", "Lcom/discord/panels/PanelState;", "()V", "overlapping_panels_release"}, k = 1, mv = {1, 1, 16})
    public static final class Opening extends PanelState {
        public static final Opening INSTANCE = new Opening();

        private Opening() {
            super(null);
        }
    }

    private PanelState() {
    }

    public /* synthetic */ PanelState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: PanelState.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/discord/panels/PanelState$Opened;", "Lcom/discord/panels/PanelState;", "()V", "overlapping_panels_release"}, k = 1, mv = {1, 1, 16})
    public static final class Opened extends PanelState {
        public static final Opened INSTANCE = new Opened();

        private Opened() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: PanelState.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/discord/panels/PanelState$Closing;", "Lcom/discord/panels/PanelState;", "()V", "overlapping_panels_release"}, k = 1, mv = {1, 1, 16})
    public static final class Closing extends PanelState {
        public static final Closing INSTANCE = new Closing();

        private Closing() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: PanelState.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/discord/panels/PanelState$Closed;", "Lcom/discord/panels/PanelState;", "()V", "overlapping_panels_release"}, k = 1, mv = {1, 1, 16})
    public static final class Closed extends PanelState {
        public static final Closed INSTANCE = new Closed();

        private Closed() {
            super(null);
        }
    }
}
