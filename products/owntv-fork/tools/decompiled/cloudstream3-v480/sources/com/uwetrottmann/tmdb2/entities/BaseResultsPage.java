package com.uwetrottmann.tmdb2.entities;

/* JADX INFO: loaded from: classes7.dex */
public abstract class BaseResultsPage<T> {
    public Integer id;
    public Integer page;
    public java.util.List<T> results;
    public Integer total_pages;
    public Integer total_results;
}
