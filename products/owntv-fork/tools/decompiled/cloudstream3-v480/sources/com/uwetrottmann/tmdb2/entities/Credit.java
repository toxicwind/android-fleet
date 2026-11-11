package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.enumerations.CreditType;
import com.uwetrottmann.tmdb2.enumerations.MediaType;

/* JADX INFO: loaded from: classes7.dex */
public class Credit {
    public CreditType credit_type;
    public String department;
    public String id;
    public String job;
    public CreditMedia media;
    public MediaType media_type;
    public BasePerson person;
}
