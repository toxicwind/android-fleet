package com.uwetrottmann.tmdb2.entities;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes7.dex */
public class AlternativeTitles {
    public Integer id;

    @SerializedName(alternate = {"results"}, value = "titles")
    public java.util.List<AlternativeTitle> titles;
}
