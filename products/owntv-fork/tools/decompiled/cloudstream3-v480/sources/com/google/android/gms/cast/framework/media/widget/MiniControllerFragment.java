package com.google.android.gms.cast.framework.media.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzpb;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public class MiniControllerFragment extends Fragment implements ControlButtonsContainer {
    private Logger zza;
    private boolean zzb;
    private int zzc;
    private int zzd;
    private TextView zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int[] zzj;
    private final ImageView[] zzk = new ImageView[3];
    private int zzl;
    private int zzm;
    private int zzn;
    private int zzo;
    private int zzp;
    private int zzq;
    private int zzr;
    private int zzs;
    private int zzt;
    private int zzu;
    private int zzv;
    private int zzw;
    private int zzx;
    private UIMediaController zzy;

    private final void zza(UIMediaController uIMediaController, RelativeLayout relativeLayout, int i, int i2) {
        ImageView imageView = (ImageView) relativeLayout.findViewById(i);
        int i3 = this.zzj[i2];
        if (i3 == R.id.cast_button_type_empty) {
            imageView.setVisibility(4);
            return;
        }
        if (i3 == R.id.cast_button_type_custom) {
            return;
        }
        if (i3 == R.id.cast_button_type_play_pause_toggle) {
            int i4 = this.zzm;
            int i5 = this.zzn;
            int i6 = this.zzo;
            if (this.zzl == 1) {
                i4 = this.zzp;
                i5 = this.zzq;
                i6 = this.zzr;
            }
            Drawable drawableZzc = zzq.zzc(getContext(), this.zzi, i4);
            Drawable drawableZzc2 = zzq.zzc(getContext(), this.zzi, i5);
            Drawable drawableZzc3 = zzq.zzc(getContext(), this.zzi, i6);
            imageView.setImageDrawable(drawableZzc2);
            ProgressBar progressBar = new ProgressBar(getContext());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(8, i);
            layoutParams.addRule(6, i);
            layoutParams.addRule(5, i);
            layoutParams.addRule(7, i);
            layoutParams.addRule(15);
            progressBar.setLayoutParams(layoutParams);
            progressBar.setVisibility(8);
            Drawable indeterminateDrawable = progressBar.getIndeterminateDrawable();
            int i7 = this.zzh;
            if (i7 != 0 && indeterminateDrawable != null) {
                indeterminateDrawable.setColorFilter(i7, PorterDuff.Mode.SRC_IN);
            }
            relativeLayout.addView(progressBar);
            uIMediaController.bindImageViewToPlayPauseToggle(imageView, drawableZzc, drawableZzc2, drawableZzc3, progressBar, true);
            return;
        }
        if (i3 == R.id.cast_button_type_skip_previous) {
            imageView.setImageDrawable(zzq.zzc(getContext(), this.zzi, this.zzs));
            imageView.setContentDescription(getResources().getString(R.string.cast_skip_prev));
            uIMediaController.bindViewToSkipPrev(imageView, 0);
            return;
        }
        if (i3 == R.id.cast_button_type_skip_next) {
            imageView.setImageDrawable(zzq.zzc(getContext(), this.zzi, this.zzt));
            imageView.setContentDescription(getResources().getString(R.string.cast_skip_next));
            uIMediaController.bindViewToSkipNext(imageView, 0);
            return;
        }
        if (i3 == R.id.cast_button_type_rewind_30_seconds) {
            imageView.setImageDrawable(zzq.zzc(getContext(), this.zzi, this.zzu));
            imageView.setContentDescription(getResources().getString(R.string.cast_rewind_30));
            uIMediaController.bindViewToRewind(imageView, 30000L);
        } else if (i3 == R.id.cast_button_type_forward_30_seconds) {
            imageView.setImageDrawable(zzq.zzc(getContext(), this.zzi, this.zzv));
            imageView.setContentDescription(getResources().getString(R.string.cast_forward_30));
            uIMediaController.bindViewToForward(imageView, 30000L);
        } else if (i3 == R.id.cast_button_type_mute_toggle) {
            imageView.setImageDrawable(zzq.zzc(getContext(), this.zzi, this.zzw));
            uIMediaController.bindImageViewToMuteToggle(imageView);
        } else if (i3 == R.id.cast_button_type_closed_caption) {
            imageView.setImageDrawable(zzq.zzc(getContext(), this.zzi, this.zzx));
            uIMediaController.bindViewToClosedCaption(imageView);
        }
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final ImageView getButtonImageViewAt(int i) throws IndexOutOfBoundsException {
        return this.zzk[i];
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final int getButtonSlotCount() {
        return 3;
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final int getButtonTypeAt(int i) throws IndexOutOfBoundsException {
        return this.zzj[i];
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public UIMediaController getUIMediaController() {
        return this.zzy;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.zza = new Logger("MiniControllerFragment");
        UIMediaController uIMediaController = new UIMediaController(getActivity());
        this.zzy = uIMediaController;
        View viewInflate = layoutInflater.inflate(R.layout.cast_mini_controller, viewGroup, false);
        viewInflate.setVisibility(8);
        uIMediaController.bindViewVisibilityToMediaSession(viewInflate, 8);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.container_current);
        int i = this.zzf;
        if (i != 0) {
            relativeLayout.setBackgroundResource(i);
        }
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.icon_view);
        TextView textView = (TextView) viewInflate.findViewById(R.id.title_view);
        if (this.zzc != 0) {
            textView.setTextAppearance(getActivity(), this.zzc);
        }
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.subtitle_view);
        this.zze = textView2;
        if (this.zzd != 0) {
            textView2.setTextAppearance(getActivity(), this.zzd);
        }
        ProgressBar progressBar = (ProgressBar) viewInflate.findViewById(R.id.progressBar);
        if (this.zzg != 0) {
            ((LayerDrawable) progressBar.getProgressDrawable()).setColorFilter(this.zzg, PorterDuff.Mode.SRC_IN);
        }
        uIMediaController.bindTextViewToMetadataOfCurrentItem(textView, MediaMetadata.KEY_TITLE);
        uIMediaController.bindTextViewToSmartSubtitle(this.zze);
        uIMediaController.bindProgressBar(progressBar);
        uIMediaController.bindViewToLaunchExpandedController(relativeLayout);
        if (this.zzb) {
            uIMediaController.bindImageViewToImageOfCurrentItem(imageView, new ImageHints(2, getResources().getDimensionPixelSize(R.dimen.cast_mini_controller_icon_width), getResources().getDimensionPixelSize(R.dimen.cast_mini_controller_icon_height)), R.drawable.cast_album_art_placeholder);
        } else {
            imageView.setVisibility(8);
        }
        ImageView[] imageViewArr = this.zzk;
        imageViewArr[0] = (ImageView) relativeLayout.findViewById(R.id.button_0);
        imageViewArr[1] = (ImageView) relativeLayout.findViewById(R.id.button_1);
        imageViewArr[2] = (ImageView) relativeLayout.findViewById(R.id.button_2);
        zza(uIMediaController, relativeLayout, R.id.button_0, 0);
        zza(uIMediaController, relativeLayout, R.id.button_1, 1);
        zza(uIMediaController, relativeLayout, R.id.button_2, 2);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        UIMediaController uIMediaController = this.zzy;
        if (uIMediaController != null) {
            uIMediaController.dispose();
            this.zzy = null;
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(context, attributeSet, bundle);
        if (this.zzj == null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CastMiniController, R.attr.castMiniControllerStyle, R.style.CastMiniController);
            this.zzb = typedArrayObtainStyledAttributes.getBoolean(R.styleable.CastMiniController_castShowImageThumbnail, true);
            this.zzc = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castTitleTextAppearance, 0);
            this.zzd = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castSubtitleTextAppearance, 0);
            this.zzf = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castBackground, 0);
            this.zzg = typedArrayObtainStyledAttributes.getColor(R.styleable.CastMiniController_castProgressBarColor, 0);
            this.zzh = typedArrayObtainStyledAttributes.getColor(R.styleable.CastMiniController_castMiniControllerLoadingIndicatorColor, this.zzg);
            this.zzi = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castButtonColor, 0);
            this.zzm = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castPlayButtonDrawable, 0);
            this.zzn = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castPauseButtonDrawable, 0);
            this.zzo = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castStopButtonDrawable, 0);
            this.zzp = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castPlayButtonDrawable, 0);
            this.zzq = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castPauseButtonDrawable, 0);
            this.zzr = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castStopButtonDrawable, 0);
            this.zzs = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castSkipPreviousButtonDrawable, 0);
            this.zzt = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castSkipNextButtonDrawable, 0);
            this.zzu = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castRewind30ButtonDrawable, 0);
            this.zzv = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castForward30ButtonDrawable, 0);
            this.zzw = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castMuteToggleButtonDrawable, 0);
            this.zzx = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castClosedCaptionsButtonDrawable, 0);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castControlButtons, 0);
            if (resourceId != 0) {
                TypedArray typedArrayObtainTypedArray = context.getResources().obtainTypedArray(resourceId);
                Preconditions.checkArgument(typedArrayObtainTypedArray.length() == 3);
                this.zzj = new int[typedArrayObtainTypedArray.length()];
                for (int i = 0; i < typedArrayObtainTypedArray.length(); i++) {
                    this.zzj[i] = typedArrayObtainTypedArray.getResourceId(i, 0);
                }
                typedArrayObtainTypedArray.recycle();
                if (this.zzb) {
                    this.zzj[0] = R.id.cast_button_type_empty;
                }
                this.zzl = 0;
                for (int i2 : this.zzj) {
                    if (i2 != R.id.cast_button_type_empty) {
                        this.zzl++;
                    }
                }
            } else {
                Logger logger = this.zza;
                if (logger != null) {
                    logger.w("Unable to read attribute castControlButtons.", new Object[0]);
                }
                this.zzj = new int[]{R.id.cast_button_type_empty, R.id.cast_button_type_empty, R.id.cast_button_type_empty};
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        com.google.android.gms.internal.cast.zzp.zzd(zzpb.CAF_MINI_CONTROLLER);
    }
}
