package com.google.android.gms.cast.framework.media.widget;

import android.widget.ImageView;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public interface ControlButtonsContainer {
    ImageView getButtonImageViewAt(int i) throws IndexOutOfBoundsException;

    int getButtonSlotCount();

    int getButtonTypeAt(int i) throws IndexOutOfBoundsException;

    UIMediaController getUIMediaController();
}
