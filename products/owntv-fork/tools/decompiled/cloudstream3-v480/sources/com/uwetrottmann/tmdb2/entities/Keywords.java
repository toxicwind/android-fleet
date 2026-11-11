package com.uwetrottmann.tmdb2.entities;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes7.dex */
public class Keywords {
    public Integer id;

    @SerializedName(alternate = {"results"}, value = "keywords")
    public java.util.List<BaseKeyword> keywords;
}
