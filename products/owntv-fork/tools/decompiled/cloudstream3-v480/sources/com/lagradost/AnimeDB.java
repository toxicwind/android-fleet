package com.lagradost;

import java.io.InputStream;
import kotlin.Metadata;

/* JADX INFO: compiled from: Main.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¨\u0006\u0006"}, d2 = {"Lcom/lagradost/AnimeDB;", "", "<init>", "()V", "getDatabaseStream", "Ljava/io/InputStream;", "anime-db"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AnimeDB {
    public static final AnimeDB INSTANCE = new AnimeDB();

    private AnimeDB() {
    }

    public final InputStream getDatabaseStream() {
        ClassLoader classLoader = getClass().getClassLoader();
        if (classLoader != null) {
            return classLoader.getResourceAsStream("animedb.json");
        }
        return null;
    }
}
