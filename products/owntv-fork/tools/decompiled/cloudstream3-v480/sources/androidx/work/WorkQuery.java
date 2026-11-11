package androidx.work;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorkQuery.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u00122\u00020\u0001:\u0002\u0011\u0012BI\b\u0000\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u0013"}, d2 = {"Landroidx/work/WorkQuery;", "", "ids", "", "Ljava/util/UUID;", "uniqueWorkNames", "", "tags", "states", "Landroidx/work/WorkInfo$State;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getIds", "()Ljava/util/List;", "getUniqueWorkNames", "getTags", "getStates", "Builder", "Companion", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class WorkQuery {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<UUID> ids;
    private final List<WorkInfo.State> states;
    private final List<String> tags;
    private final List<String> uniqueWorkNames;

    public WorkQuery() {
        this(null, null, null, null, 15, null);
    }

    @JvmStatic
    public static final WorkQuery fromIds(List<UUID> list) {
        return INSTANCE.fromIds(list);
    }

    @JvmStatic
    public static final WorkQuery fromIds(UUID... uuidArr) {
        return INSTANCE.fromIds(uuidArr);
    }

    @JvmStatic
    public static final WorkQuery fromStates(List<? extends WorkInfo.State> list) {
        return INSTANCE.fromStates(list);
    }

    @JvmStatic
    public static final WorkQuery fromStates(WorkInfo.State... stateArr) {
        return INSTANCE.fromStates(stateArr);
    }

    @JvmStatic
    public static final WorkQuery fromTags(List<String> list) {
        return INSTANCE.fromTags(list);
    }

    @JvmStatic
    public static final WorkQuery fromTags(String... strArr) {
        return INSTANCE.fromTags(strArr);
    }

    @JvmStatic
    public static final WorkQuery fromUniqueWorkNames(List<String> list) {
        return INSTANCE.fromUniqueWorkNames(list);
    }

    @JvmStatic
    public static final WorkQuery fromUniqueWorkNames(String... strArr) {
        return INSTANCE.fromUniqueWorkNames(strArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public WorkQuery(List<UUID> ids, List<String> uniqueWorkNames, List<String> tags, List<? extends WorkInfo.State> states) {
        Intrinsics.checkNotNullParameter(ids, "ids");
        Intrinsics.checkNotNullParameter(uniqueWorkNames, "uniqueWorkNames");
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(states, "states");
        this.ids = ids;
        this.uniqueWorkNames = uniqueWorkNames;
        this.tags = tags;
        this.states = states;
    }

    public /* synthetic */ WorkQuery(List list, List list2, List list3, List list4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? CollectionsKt.emptyList() : list2, (i & 4) != 0 ? CollectionsKt.emptyList() : list3, (i & 8) != 0 ? CollectionsKt.emptyList() : list4);
    }

    public final List<UUID> getIds() {
        return this.ids;
    }

    public final List<String> getUniqueWorkNames() {
        return this.uniqueWorkNames;
    }

    public final List<String> getTags() {
        return this.tags;
    }

    public final List<WorkInfo.State> getStates() {
        return this.states;
    }

    /* JADX INFO: compiled from: WorkQuery.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\f\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\rJ\u0014\u0010\u000e\u001a\u00020\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\rJ\u0014\u0010\u000f\u001a\u00020\u00002\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\rJ\u0014\u0010\u0010\u001a\u00020\u00002\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\rJ\u0006\u0010\u0011\u001a\u00020\u0012R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/work/WorkQuery$Builder;", "", "<init>", "()V", "ids", "", "Ljava/util/UUID;", "uniqueWorkNames", "", "tags", "states", "Landroidx/work/WorkInfo$State;", "addIds", "", "addUniqueWorkNames", "addTags", "addStates", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Landroidx/work/WorkQuery;", "Companion", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Builder {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final List<UUID> ids;
        private final List<WorkInfo.State> states;
        private final List<String> tags;
        private final List<String> uniqueWorkNames;

        public /* synthetic */ Builder(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static final Builder fromIds(List<UUID> list) {
            return INSTANCE.fromIds(list);
        }

        @JvmStatic
        public static final Builder fromStates(List<? extends WorkInfo.State> list) {
            return INSTANCE.fromStates(list);
        }

        @JvmStatic
        public static final Builder fromTags(List<String> list) {
            return INSTANCE.fromTags(list);
        }

        @JvmStatic
        public static final Builder fromUniqueWorkNames(List<String> list) {
            return INSTANCE.fromUniqueWorkNames(list);
        }

        private Builder() {
            this.ids = new ArrayList();
            this.uniqueWorkNames = new ArrayList();
            this.tags = new ArrayList();
            this.states = new ArrayList();
        }

        public final Builder addIds(List<UUID> ids) {
            Intrinsics.checkNotNullParameter(ids, "ids");
            CollectionsKt.addAll(this.ids, ids);
            return this;
        }

        public final Builder addUniqueWorkNames(List<String> uniqueWorkNames) {
            Intrinsics.checkNotNullParameter(uniqueWorkNames, "uniqueWorkNames");
            CollectionsKt.addAll(this.uniqueWorkNames, uniqueWorkNames);
            return this;
        }

        public final Builder addTags(List<String> tags) {
            Intrinsics.checkNotNullParameter(tags, "tags");
            CollectionsKt.addAll(this.tags, tags);
            return this;
        }

        public final Builder addStates(List<? extends WorkInfo.State> states) {
            Intrinsics.checkNotNullParameter(states, "states");
            CollectionsKt.addAll(this.states, states);
            return this;
        }

        public final WorkQuery build() {
            if (this.ids.isEmpty() && this.uniqueWorkNames.isEmpty() && this.tags.isEmpty() && this.states.isEmpty()) {
                throw new IllegalArgumentException("Must specify ids, uniqueNames, tags or states when building a WorkQuery");
            }
            return new WorkQuery(this.ids, this.uniqueWorkNames, this.tags, this.states);
        }

        /* JADX INFO: compiled from: WorkQuery.kt */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007J\u0016\u0010\t\u001a\u00020\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0007H\u0007J\u0016\u0010\f\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0007H\u0007J\u0016\u0010\u000e\u001a\u00020\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0007H\u0007¨\u0006\u0011"}, d2 = {"Landroidx/work/WorkQuery$Builder$Companion;", "", "<init>", "()V", "fromIds", "Landroidx/work/WorkQuery$Builder;", "ids", "", "Ljava/util/UUID;", "fromUniqueWorkNames", "uniqueWorkNames", "", "fromTags", "tags", "fromStates", "states", "Landroidx/work/WorkInfo$State;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Builder fromIds(List<UUID> ids) {
                Intrinsics.checkNotNullParameter(ids, "ids");
                Builder builder = new Builder(null);
                builder.addIds(ids);
                return builder;
            }

            @JvmStatic
            public final Builder fromUniqueWorkNames(List<String> uniqueWorkNames) {
                Intrinsics.checkNotNullParameter(uniqueWorkNames, "uniqueWorkNames");
                Builder builder = new Builder(null);
                builder.addUniqueWorkNames(uniqueWorkNames);
                return builder;
            }

            @JvmStatic
            public final Builder fromTags(List<String> tags) {
                Intrinsics.checkNotNullParameter(tags, "tags");
                Builder builder = new Builder(null);
                builder.addTags(tags);
                return builder;
            }

            @JvmStatic
            public final Builder fromStates(List<? extends WorkInfo.State> states) {
                Intrinsics.checkNotNullParameter(states, "states");
                Builder builder = new Builder(null);
                builder.addStates(states);
                return builder;
            }
        }
    }

    /* JADX INFO: compiled from: WorkQuery.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007J!\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\t\"\u00020\bH\u0007¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0007H\u0007J!\u0010\u000b\u001a\u00020\u00052\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\t\"\u00020\rH\u0007¢\u0006\u0002\u0010\u000eJ!\u0010\u000f\u001a\u00020\u00052\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\t\"\u00020\rH\u0007¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0007H\u0007J\u0016\u0010\u0011\u001a\u00020\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0007H\u0007J!\u0010\u0011\u001a\u00020\u00052\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130\t\"\u00020\u0013H\u0007¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/work/WorkQuery$Companion;", "", "<init>", "()V", "fromIds", "Landroidx/work/WorkQuery;", "ids", "", "Ljava/util/UUID;", "", "([Ljava/util/UUID;)Landroidx/work/WorkQuery;", "fromTags", "tags", "", "([Ljava/lang/String;)Landroidx/work/WorkQuery;", "fromUniqueWorkNames", "uniqueWorkNames", "fromStates", "states", "Landroidx/work/WorkInfo$State;", "([Landroidx/work/WorkInfo$State;)Landroidx/work/WorkQuery;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final WorkQuery fromIds(List<UUID> ids) {
            Intrinsics.checkNotNullParameter(ids, "ids");
            return new WorkQuery(ids, null, null, null, 14, null);
        }

        @JvmStatic
        public final WorkQuery fromIds(UUID... ids) {
            Intrinsics.checkNotNullParameter(ids, "ids");
            return new WorkQuery(ArraysKt.toList(ids), null, null, null, 14, null);
        }

        @JvmStatic
        public final WorkQuery fromTags(List<String> tags) {
            Intrinsics.checkNotNullParameter(tags, "tags");
            return new WorkQuery(null, null, tags, null, 11, null);
        }

        @JvmStatic
        public final WorkQuery fromTags(String... tags) {
            Intrinsics.checkNotNullParameter(tags, "tags");
            return new WorkQuery(null, null, ArraysKt.toList(tags), null, 11, null);
        }

        @JvmStatic
        public final WorkQuery fromUniqueWorkNames(String... uniqueWorkNames) {
            Intrinsics.checkNotNullParameter(uniqueWorkNames, "uniqueWorkNames");
            return new WorkQuery(null, ArraysKt.toList(uniqueWorkNames), null, null, 13, null);
        }

        @JvmStatic
        public final WorkQuery fromUniqueWorkNames(List<String> uniqueWorkNames) {
            Intrinsics.checkNotNullParameter(uniqueWorkNames, "uniqueWorkNames");
            return new WorkQuery(null, uniqueWorkNames, null, null, 13, null);
        }

        @JvmStatic
        public final WorkQuery fromStates(List<? extends WorkInfo.State> states) {
            Intrinsics.checkNotNullParameter(states, "states");
            return new WorkQuery(null, null, null, states, 7, null);
        }

        @JvmStatic
        public final WorkQuery fromStates(WorkInfo.State... states) {
            Intrinsics.checkNotNullParameter(states, "states");
            return new WorkQuery(null, null, null, ArraysKt.toList(states), 7, null);
        }
    }
}
