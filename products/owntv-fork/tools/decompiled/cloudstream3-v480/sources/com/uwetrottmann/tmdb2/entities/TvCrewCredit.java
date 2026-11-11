package com.uwetrottmann.tmdb2.entities;

/* JADX INFO: loaded from: classes7.dex */
public class TvCrewCredit extends BaseTvCredit {
    public String department;
    public java.util.List<Job> jobs;

    public static class Job {
        public String credit_id;
        public Integer episode_count;
        public String job;
    }
}
