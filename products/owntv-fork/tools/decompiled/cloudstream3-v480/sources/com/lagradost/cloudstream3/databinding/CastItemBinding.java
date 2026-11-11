package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class CastItemBinding implements ViewBinding {
    public final TextView actorExtra;
    public final ImageView actorImage;
    public final TextView actorName;
    public final CardView episodeHolder;
    private final CardView rootView;
    public final ImageView voiceActorImage;
    public final CardView voiceActorImageHolder;
    public final CardView voiceActorImageHolder2;
    public final TextView voiceActorName;

    private CastItemBinding(CardView cardView, TextView textView, ImageView imageView, TextView textView2, CardView cardView2, ImageView imageView2, CardView cardView3, CardView cardView4, TextView textView3) {
        this.rootView = cardView;
        this.actorExtra = textView;
        this.actorImage = imageView;
        this.actorName = textView2;
        this.episodeHolder = cardView2;
        this.voiceActorImage = imageView2;
        this.voiceActorImageHolder = cardView3;
        this.voiceActorImageHolder2 = cardView4;
        this.voiceActorName = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static CastItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static CastItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.cast_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static CastItemBinding bind(View view) {
        int i = R.id.actor_extra;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.actor_image;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R.id.actor_name;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    CardView cardView = (CardView) view;
                    i = R.id.voice_actor_image;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView2 != null) {
                        i = R.id.voice_actor_image_holder;
                        CardView cardView2 = (CardView) ViewBindings.findChildViewById(view, i);
                        if (cardView2 != null) {
                            i = R.id.voice_actor_image_holder2;
                            CardView cardView3 = (CardView) ViewBindings.findChildViewById(view, i);
                            if (cardView3 != null) {
                                i = R.id.voice_actor_name;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView3 != null) {
                                    return new CastItemBinding(cardView, textView, imageView, textView2, cardView, imageView2, cardView2, cardView3, textView3);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
