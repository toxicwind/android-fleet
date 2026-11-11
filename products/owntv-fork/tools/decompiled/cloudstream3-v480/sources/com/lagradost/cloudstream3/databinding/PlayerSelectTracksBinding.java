package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class PlayerSelectTracksBinding implements ViewBinding {
    public final MaterialButton applyBtt;
    public final LinearLayout applyBttHolder;
    public final LinearLayout audioTracksHolder;
    public final ListView autoTracksList;
    public final MaterialButton cancelBtt;
    private final LinearLayout rootView;
    public final LinearLayout subtitlesClickSettings;
    public final LinearLayout videoTracksHolder;
    public final ListView videoTracksList;

    private PlayerSelectTracksBinding(LinearLayout linearLayout, MaterialButton materialButton, LinearLayout linearLayout2, LinearLayout linearLayout3, ListView listView, MaterialButton materialButton2, LinearLayout linearLayout4, LinearLayout linearLayout5, ListView listView2) {
        this.rootView = linearLayout;
        this.applyBtt = materialButton;
        this.applyBttHolder = linearLayout2;
        this.audioTracksHolder = linearLayout3;
        this.autoTracksList = listView;
        this.cancelBtt = materialButton2;
        this.subtitlesClickSettings = linearLayout4;
        this.videoTracksHolder = linearLayout5;
        this.videoTracksList = listView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static PlayerSelectTracksBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PlayerSelectTracksBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.player_select_tracks, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PlayerSelectTracksBinding bind(View view) {
        int i = R.id.apply_btt;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.apply_btt_holder;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.audio_tracks_holder;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                if (linearLayout2 != null) {
                    i = R.id.auto_tracks_list;
                    ListView listView = (ListView) ViewBindings.findChildViewById(view, i);
                    if (listView != null) {
                        i = R.id.cancel_btt;
                        MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                        if (materialButton2 != null) {
                            i = R.id.subtitles_click_settings;
                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                            if (linearLayout3 != null) {
                                i = R.id.video_tracks_holder;
                                LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                if (linearLayout4 != null) {
                                    i = R.id.video_tracks_list;
                                    ListView listView2 = (ListView) ViewBindings.findChildViewById(view, i);
                                    if (listView2 != null) {
                                        return new PlayerSelectTracksBinding((LinearLayout) view, materialButton, linearLayout, linearLayout2, listView, materialButton2, linearLayout3, linearLayout4, listView2);
                                    }
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
