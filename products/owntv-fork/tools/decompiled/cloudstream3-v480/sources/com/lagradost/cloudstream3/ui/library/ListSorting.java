package com.lagradost.cloudstream3.ui.library;

import com.lagradost.cloudstream3.R;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: LibraryViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0013\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/ui/library/ListSorting;", "", "stringRes", "", "<init>", "(Ljava/lang/String;II)V", "getStringRes", "()I", "Query", "RatingHigh", "RatingLow", "UpdatedNew", "UpdatedOld", "AlphabeticalA", "AlphabeticalZ", "ReleaseDateNew", "ReleaseDateOld", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public enum ListSorting {
    Query(R.string.none),
    RatingHigh(R.string.sort_rating_desc),
    RatingLow(R.string.sort_rating_asc),
    UpdatedNew(R.string.sort_updated_new),
    UpdatedOld(R.string.sort_updated_old),
    AlphabeticalA(R.string.sort_alphabetical_a),
    AlphabeticalZ(R.string.sort_alphabetical_z),
    ReleaseDateNew(R.string.sort_release_date_new),
    ReleaseDateOld(R.string.sort_release_date_old);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int stringRes;

    public static EnumEntries<ListSorting> getEntries() {
        return $ENTRIES;
    }

    ListSorting(int i) {
        this.stringRes = i;
    }

    public final int getStringRes() {
        return this.stringRes;
    }
}
