package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class TvtypesChipsBinding implements ViewBinding {
    public final Chip homeSelectAnime;
    public final Chip homeSelectAsian;
    public final Chip homeSelectCartoons;
    public final Chip homeSelectDocumentaries;
    public final ChipGroup homeSelectGroup;
    public final Chip homeSelectLivestreams;
    public final Chip homeSelectMovies;
    public final Chip homeSelectNsfw;
    public final Chip homeSelectOthers;
    public final Chip homeSelectTorrents;
    public final Chip homeSelectTvSeries;
    private final ChipGroup rootView;

    private TvtypesChipsBinding(ChipGroup chipGroup, Chip chip, Chip chip2, Chip chip3, Chip chip4, ChipGroup chipGroup2, Chip chip5, Chip chip6, Chip chip7, Chip chip8, Chip chip9, Chip chip10) {
        this.rootView = chipGroup;
        this.homeSelectAnime = chip;
        this.homeSelectAsian = chip2;
        this.homeSelectCartoons = chip3;
        this.homeSelectDocumentaries = chip4;
        this.homeSelectGroup = chipGroup2;
        this.homeSelectLivestreams = chip5;
        this.homeSelectMovies = chip6;
        this.homeSelectNsfw = chip7;
        this.homeSelectOthers = chip8;
        this.homeSelectTorrents = chip9;
        this.homeSelectTvSeries = chip10;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ChipGroup getRoot() {
        return this.rootView;
    }

    public static TvtypesChipsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TvtypesChipsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.tvtypes_chips, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TvtypesChipsBinding bind(View view) {
        int i = R.id.home_select_anime;
        Chip chip = (Chip) ViewBindings.findChildViewById(view, i);
        if (chip != null) {
            i = R.id.home_select_asian;
            Chip chip2 = (Chip) ViewBindings.findChildViewById(view, i);
            if (chip2 != null) {
                i = R.id.home_select_cartoons;
                Chip chip3 = (Chip) ViewBindings.findChildViewById(view, i);
                if (chip3 != null) {
                    i = R.id.home_select_documentaries;
                    Chip chip4 = (Chip) ViewBindings.findChildViewById(view, i);
                    if (chip4 != null) {
                        ChipGroup chipGroup = (ChipGroup) view;
                        i = R.id.home_select_livestreams;
                        Chip chip5 = (Chip) ViewBindings.findChildViewById(view, i);
                        if (chip5 != null) {
                            i = R.id.home_select_movies;
                            Chip chip6 = (Chip) ViewBindings.findChildViewById(view, i);
                            if (chip6 != null) {
                                i = R.id.home_select_nsfw;
                                Chip chip7 = (Chip) ViewBindings.findChildViewById(view, i);
                                if (chip7 != null) {
                                    i = R.id.home_select_others;
                                    Chip chip8 = (Chip) ViewBindings.findChildViewById(view, i);
                                    if (chip8 != null) {
                                        i = R.id.home_select_torrents;
                                        Chip chip9 = (Chip) ViewBindings.findChildViewById(view, i);
                                        if (chip9 != null) {
                                            i = R.id.home_select_tv_series;
                                            Chip chip10 = (Chip) ViewBindings.findChildViewById(view, i);
                                            if (chip10 != null) {
                                                return new TvtypesChipsBinding(chipGroup, chip, chip2, chip3, chip4, chipGroup, chip5, chip6, chip7, chip8, chip9, chip10);
                                            }
                                        }
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
