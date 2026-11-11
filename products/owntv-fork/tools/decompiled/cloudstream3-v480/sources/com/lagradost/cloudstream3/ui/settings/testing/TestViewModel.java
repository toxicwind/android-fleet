package com.lagradost.cloudstream3.ui.settings.testing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment;
import com.lagradost.cloudstream3.utils.AtomicList;
import com.lagradost.cloudstream3.utils.AtomicMutableList;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.TestingUtils;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: TestViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0002)*B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020 H\u0002J\u000e\u0010\"\u001a\u00020 2\u0006\u0010\u0017\u001a\u00020\u0018J\u0018\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u000fH\u0002J\u0006\u0010&\u001a\u00020 J\u0006\u0010'\u001a\u00020 J\u0006\u0010(\u001a\u00020 R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR&\u0010\u000b\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r0\f0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u0010\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r0\f0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\nR\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/testing/TestViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "_providerProgress", "Landroidx/lifecycle/MutableLiveData;", "Lcom/lagradost/cloudstream3/ui/settings/testing/TestViewModel$TestProgress;", "providerProgress", "Landroidx/lifecycle/LiveData;", "getProviderProgress", "()Landroidx/lifecycle/LiveData;", "_providerResults", "", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/MainAPI;", "Lcom/lagradost/cloudstream3/utils/TestingUtils$TestResultProvider;", "providerResults", "getProviderResults", "scope", "Lkotlinx/coroutines/CoroutineScope;", "isRunningTest", "", "()Z", "filter", "Lcom/lagradost/cloudstream3/ui/settings/testing/TestViewModel$ProviderFilter;", QuickSearchFragment.PROVIDER_KEY, "Lcom/lagradost/cloudstream3/utils/AtomicMutableList;", "passed", "", "failed", "total", "updateProgress", "", "postProviders", "setFilterMethod", "addProvider", "api", "results", "init", "startTest", "stopTest", "TestProgress", "ProviderFilter", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TestViewModel extends ViewModel {
    private final MutableLiveData<TestProgress> _providerProgress;
    private final MutableLiveData<List<Pair<MainAPI, TestingUtils.TestResultProvider>>> _providerResults;
    private int failed;
    private ProviderFilter filter;
    private int passed;
    private final LiveData<TestProgress> providerProgress;
    private final LiveData<List<Pair<MainAPI, TestingUtils.TestResultProvider>>> providerResults;
    private final AtomicMutableList<Pair<MainAPI, TestingUtils.TestResultProvider>> providers;
    private CoroutineScope scope;
    private int total;

    /* JADX INFO: compiled from: TestViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/testing/TestViewModel$ProviderFilter;", "", "<init>", "(Ljava/lang/String;I)V", "All", "Passed", "Failed", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum ProviderFilter {
        All,
        Passed,
        Failed;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<ProviderFilter> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: TestViewModel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ProviderFilter.values().length];
            try {
                iArr[ProviderFilter.All.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ProviderFilter.Passed.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ProviderFilter.Failed.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: compiled from: TestViewModel.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/testing/TestViewModel$TestProgress;", "", "passed", "", "failed", "total", "<init>", "(III)V", "getPassed", "()I", "getFailed", "getTotal", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TestProgress {
        private final int failed;
        private final int passed;
        private final int total;

        public static /* synthetic */ TestProgress copy$default(TestProgress testProgress, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = testProgress.passed;
            }
            if ((i4 & 2) != 0) {
                i2 = testProgress.failed;
            }
            if ((i4 & 4) != 0) {
                i3 = testProgress.total;
            }
            return testProgress.copy(i, i2, i3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getPassed() {
            return this.passed;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getFailed() {
            return this.failed;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getTotal() {
            return this.total;
        }

        public final TestProgress copy(int passed, int failed, int total) {
            return new TestProgress(passed, failed, total);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TestProgress)) {
                return false;
            }
            TestProgress testProgress = (TestProgress) other;
            return this.passed == testProgress.passed && this.failed == testProgress.failed && this.total == testProgress.total;
        }

        public int hashCode() {
            return (((this.passed * 31) + this.failed) * 31) + this.total;
        }

        public String toString() {
            return "TestProgress(passed=" + this.passed + ", failed=" + this.failed + ", total=" + this.total + ')';
        }

        public TestProgress(int i, int i2, int i3) {
            this.passed = i;
            this.failed = i2;
            this.total = i3;
        }

        public final int getPassed() {
            return this.passed;
        }

        public final int getFailed() {
            return this.failed;
        }

        public final int getTotal() {
            return this.total;
        }
    }

    public TestViewModel() {
        MutableLiveData<TestProgress> mutableLiveData = new MutableLiveData<>(null);
        this._providerProgress = mutableLiveData;
        this.providerProgress = mutableLiveData;
        MutableLiveData<List<Pair<MainAPI, TestingUtils.TestResultProvider>>> mutableLiveData2 = new MutableLiveData<>(CollectionsKt.emptyList());
        this._providerResults = mutableLiveData2;
        this.providerResults = mutableLiveData2;
        this.filter = ProviderFilter.All;
        this.providers = Coroutines.INSTANCE.atomicListOf(new Pair[0]);
    }

    public final LiveData<TestProgress> getProviderProgress() {
        return this.providerProgress;
    }

    public final LiveData<List<Pair<MainAPI, TestingUtils.TestResultProvider>>> getProviderResults() {
        return this.providerResults;
    }

    public final boolean isRunningTest() {
        return this.scope != null;
    }

    private final void updateProgress() {
        this._providerProgress.postValue(new TestProgress(this.passed, this.failed, this.total));
        postProviders();
    }

    private final void postProviders() {
        this.providers.withLock(new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestViewModel$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TestViewModel.postProviders$lambda$0(this.f$0);
            }
        });
    }

    static final Unit postProviders$lambda$0(TestViewModel testViewModel) {
        AtomicList<Pair<MainAPI, TestingUtils.TestResultProvider>> list;
        int i = WhenMappings.$EnumSwitchMapping$0[testViewModel.filter.ordinal()];
        if (i == 1) {
            list = CollectionsKt.toList(testViewModel.providers);
        } else if (i == 2) {
            list = testViewModel.providers.filter(new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestViewModel$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(TestViewModel.postProviders$lambda$0$0((Pair) obj));
                }
            });
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            list = testViewModel.providers.filter(new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestViewModel$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(TestViewModel.postProviders$lambda$0$1((Pair) obj));
                }
            });
        }
        testViewModel._providerResults.postValue(list);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean postProviders$lambda$0$0(Pair it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return ((TestingUtils.TestResultProvider) it.getSecond()).getSuccess();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean postProviders$lambda$0$1(Pair it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return !((TestingUtils.TestResultProvider) it.getSecond()).getSuccess();
    }

    public final void setFilterMethod(ProviderFilter filter) {
        Intrinsics.checkNotNullParameter(filter, "filter");
        if (this.filter == filter) {
            return;
        }
        this.filter = filter;
        postProviders();
    }

    private final void addProvider(final MainAPI api, final TestingUtils.TestResultProvider results) {
        this.providers.withLock(new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestViewModel$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TestViewModel.addProvider$lambda$0(this.f$0, api, results);
            }
        });
    }

    static final Unit addProvider$lambda$0(TestViewModel testViewModel, MainAPI mainAPI, TestingUtils.TestResultProvider testResultProvider) {
        Iterator<Pair<MainAPI, TestingUtils.TestResultProvider>> it = testViewModel.providers.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (Intrinsics.areEqual(it.next().getFirst(), mainAPI)) {
                break;
            }
            i++;
        }
        if (i == -1) {
            testViewModel.providers.add(TuplesKt.to(mainAPI, testResultProvider));
            if (testResultProvider.getSuccess()) {
                testViewModel.passed++;
            } else {
                testViewModel.failed++;
            }
        } else {
            testViewModel.providers.set(i, TuplesKt.to(mainAPI, testResultProvider));
        }
        testViewModel.updateProgress();
        return Unit.INSTANCE;
    }

    public final void init() {
        this.total = ((Number) APIHolder.INSTANCE.getAllProviders().withLock(new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestViewModel$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Integer.valueOf(APIHolder.INSTANCE.getAllProviders().size());
            }
        })).intValue();
        updateProgress();
    }

    public final void startTest() {
        this.scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        MainAPI[] mainAPIArr = (MainAPI[]) APIHolder.INSTANCE.getAllProviders().withLock(new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestViewModel$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TestViewModel.startTest$lambda$0();
            }
        });
        this.total = mainAPIArr.length;
        this.failed = 0;
        this.passed = 0;
        this.providers.clear();
        updateProgress();
        TestingUtils testingUtils = TestingUtils.INSTANCE;
        CoroutineScope coroutineScope = this.scope;
        if (coroutineScope == null) {
            return;
        }
        testingUtils.getDeferredProviderTests(coroutineScope, mainAPIArr, new Function2() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestViewModel$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return TestViewModel.startTest$lambda$1(this.f$0, (MainAPI) obj, (TestingUtils.TestResultProvider) obj2);
            }
        });
    }

    static final MainAPI[] startTest$lambda$0() {
        return (MainAPI[]) APIHolder.INSTANCE.getAllProviders().toArray(new MainAPI[0]);
    }

    static final Unit startTest$lambda$1(TestViewModel testViewModel, MainAPI api, TestingUtils.TestResultProvider result) {
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(result, "result");
        testViewModel.addProvider(api, result);
        return Unit.INSTANCE;
    }

    public final void stopTest() {
        CoroutineScope coroutineScope = this.scope;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
        this.scope = null;
    }
}
