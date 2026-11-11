package com.lagradost.cloudstream3.utils;

import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.AnimeLoadResponse;
import com.lagradost.cloudstream3.DubStatus;
import com.lagradost.cloudstream3.Episode;
import com.lagradost.cloudstream3.HomePageList;
import com.lagradost.cloudstream3.HomePageResponse;
import com.lagradost.cloudstream3.LiveStreamLoadResponse;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainPageData;
import com.lagradost.cloudstream3.MainPageRequest;
import com.lagradost.cloudstream3.MovieLoadResponse;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.TvSeriesLoadResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.quicksearch.QuickSearchFragment;
import io.ktor.http.ContentType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.NotImplementedError;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: TestingUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0005)*+,-B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001a\u0010\f\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0002J\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@¢\u0006\u0002\u0010\u0014J,\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u00172\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@¢\u0006\u0002\u0010\u0018J&\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@¢\u0006\u0002\u0010\u001cJ(\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u001e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@¢\u0006\u0002\u0010\u001fJ;\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00110$2\u0018\u0010%\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\t0&¢\u0006\u0002\u0010(¨\u0006."}, d2 = {"Lcom/lagradost/cloudstream3/utils/TestingUtils;", "", "<init>", "()V", "fail", "", ContentType.Message.TYPE, "", "assertTrue", "", "condition", "", "assertNotNull", ES6Iterator.VALUE_PROPERTY, "testHomepage", "Lcom/lagradost/cloudstream3/utils/TestingUtils$TestResult;", "api", "Lcom/lagradost/cloudstream3/MainAPI;", "logger", "Lcom/lagradost/cloudstream3/utils/TestingUtils$Logger;", "(Lcom/lagradost/cloudstream3/MainAPI;Lcom/lagradost/cloudstream3/utils/TestingUtils$Logger;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "testSearch", "testQueries", "", "(Lcom/lagradost/cloudstream3/MainAPI;Ljava/util/List;Lcom/lagradost/cloudstream3/utils/TestingUtils$Logger;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "testLoad", "result", "Lcom/lagradost/cloudstream3/SearchResponse;", "(Lcom/lagradost/cloudstream3/MainAPI;Lcom/lagradost/cloudstream3/SearchResponse;Lcom/lagradost/cloudstream3/utils/TestingUtils$Logger;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "testLinkLoading", ImagesContract.URL, "(Lcom/lagradost/cloudstream3/MainAPI;Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/TestingUtils$Logger;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDeferredProviderTests", "scope", "Lkotlinx/coroutines/CoroutineScope;", QuickSearchFragment.PROVIDER_KEY, "", "callback", "Lkotlin/Function2;", "Lcom/lagradost/cloudstream3/utils/TestingUtils$TestResultProvider;", "(Lkotlinx/coroutines/CoroutineScope;[Lcom/lagradost/cloudstream3/MainAPI;Lkotlin/jvm/functions/Function2;)V", "TestResult", "Logger", "TestResultList", "TestResultLoad", "TestResultProvider", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TestingUtils {
    public static final TestingUtils INSTANCE = new TestingUtils();

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.TestingUtils$testHomepage$1, reason: invalid class name */
    /* JADX INFO: compiled from: TestingUtils.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.TestingUtils", f = "TestingUtils.kt", i = {0, 0, 0}, l = {75}, m = "testHomepage", n = {"api", "logger", "f"}, nl = {74}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TestingUtils.this.testHomepage(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.TestingUtils$testLinkLoading$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TestingUtils.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.TestingUtils", f = "TestingUtils.kt", i = {0, 0, 0, 0}, l = {240}, m = "testLinkLoading", n = {"api", ImagesContract.URL, "logger", "linksLoaded"}, nl = {248}, s = {"L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class C09531 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C09531(Continuation<? super C09531> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TestingUtils.this.testLinkLoading(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.TestingUtils$testLoad$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TestingUtils.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.TestingUtils", f = "TestingUtils.kt", i = {0, 0, 0}, l = {151}, m = "testLoad", n = {"api", "result", "logger"}, nl = {153}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class C09541 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C09541(Continuation<? super C09541> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TestingUtils.this.testLoad(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.TestingUtils$testSearch$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TestingUtils.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.TestingUtils", f = "TestingUtils.kt", i = {0, 0, 0, 0, 0}, l = {119}, m = "testSearch", n = {"api", "testQueries", "logger", SearchIntents.EXTRA_QUERY, "$i$a$-firstNotNullOfOrNull-TestingUtils$testSearch$searchResults$1"}, nl = {330}, s = {"L$0", "L$1", "L$2", "L$4", "I$0"}, v = 2)
    static final class C09551 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C09551(Continuation<? super C09551> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TestingUtils.this.testSearch(null, null, null, this);
        }
    }

    private TestingUtils() {
    }

    /* JADX INFO: compiled from: TestingUtils.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0016\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/lagradost/cloudstream3/utils/TestingUtils$TestResult;", "", "success", "", "<init>", "(Z)V", "getSuccess", "()Z", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static class TestResult {
        private final boolean success;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final TestResult Pass = new TestResult(true);
        private static final TestResult Fail = new TestResult(false);

        /* JADX INFO: compiled from: TestingUtils.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/utils/TestingUtils$TestResult$Companion;", "", "<init>", "()V", "Pass", "Lcom/lagradost/cloudstream3/utils/TestingUtils$TestResult;", "getPass", "()Lcom/lagradost/cloudstream3/utils/TestingUtils$TestResult;", "Fail", "getFail", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final TestResult getPass() {
                return TestResult.Pass;
            }

            public final TestResult getFail() {
                return TestResult.Fail;
            }
        }

        public TestResult(boolean z) {
            this.success = z;
        }

        public final boolean getSuccess() {
            return this.success;
        }
    }

    /* JADX INFO: compiled from: TestingUtils.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\u000f\u0010B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/utils/TestingUtils$Logger;", "", "<init>", "()V", "messageLog", "", "Lcom/lagradost/cloudstream3/utils/TestingUtils$Logger$Message;", "getRawLog", "", "log", "", ContentType.Message.TYPE, "", "warn", MediaRouteProviderProtocol.SERVICE_DATA_ERROR, "LogLevel", "Message", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Logger {
        private final List<Message> messageLog = new ArrayList();

        /* JADX INFO: compiled from: TestingUtils.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/TestingUtils$Logger$LogLevel;", "", "<init>", "(Ljava/lang/String;I)V", "Normal", "Warning", "Error", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public enum LogLevel {
            Normal,
            Warning,
            Error;

            private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

            public static EnumEntries<LogLevel> getEntries() {
                return $ENTRIES;
            }
        }

        /* JADX INFO: compiled from: TestingUtils.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u00020\u0005H\u0096\u0080\u0004J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/lagradost/cloudstream3/utils/TestingUtils$Logger$Message;", "", "level", "Lcom/lagradost/cloudstream3/utils/TestingUtils$Logger$LogLevel;", ContentType.Message.TYPE, "", "<init>", "(Lcom/lagradost/cloudstream3/utils/TestingUtils$Logger$LogLevel;Ljava/lang/String;)V", "getLevel", "()Lcom/lagradost/cloudstream3/utils/TestingUtils$Logger$LogLevel;", "getMessage", "()Ljava/lang/String;", "toString", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class Message {
            private final LogLevel level;
            private final String message;

            /* JADX INFO: compiled from: TestingUtils.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            public static final /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[LogLevel.values().length];
                    try {
                        iArr[LogLevel.Normal.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[LogLevel.Warning.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[LogLevel.Error.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            public static /* synthetic */ Message copy$default(Message message, LogLevel logLevel, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    logLevel = message.level;
                }
                if ((i & 2) != 0) {
                    str = message.message;
                }
                return message.copy(logLevel, str);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final LogLevel getLevel() {
                return this.level;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            public final Message copy(LogLevel level, String message) {
                Intrinsics.checkNotNullParameter(level, "level");
                Intrinsics.checkNotNullParameter(message, "message");
                return new Message(level, message);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Message)) {
                    return false;
                }
                Message message = (Message) other;
                return this.level == message.level && Intrinsics.areEqual(this.message, message.message);
            }

            public int hashCode() {
                return (this.level.hashCode() * 31) + this.message.hashCode();
            }

            public Message(LogLevel level, String message) {
                Intrinsics.checkNotNullParameter(level, "level");
                Intrinsics.checkNotNullParameter(message, "message");
                this.level = level;
                this.message = message;
            }

            public final LogLevel getLevel() {
                return this.level;
            }

            public final String getMessage() {
                return this.message;
            }

            public String toString() {
                String str;
                int i = WhenMappings.$EnumSwitchMapping$0[this.level.ordinal()];
                if (i == 1) {
                    str = "";
                } else if (i == 2) {
                    str = "Warning: ";
                } else {
                    if (i != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    str = "Error: ";
                }
                return str + this.message;
            }
        }

        public final List<Message> getRawLog() {
            return this.messageLog;
        }

        public final void log(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            this.messageLog.add(new Message(LogLevel.Normal, message));
        }

        public final void warn(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            this.messageLog.add(new Message(LogLevel.Warning, message));
        }

        public final void error(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            this.messageLog.add(new Message(LogLevel.Error, message));
        }
    }

    private final Void fail(String message) {
        throw new AssertionError(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void assertTrue(String message, boolean condition) {
        if (condition) {
            return;
        }
        fail(message);
        throw new KotlinNothingValueException();
    }

    private final void assertNotNull(String message, Object value) {
        if (value != null) {
            return;
        }
        fail(message);
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: compiled from: TestingUtils.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/lagradost/cloudstream3/utils/TestingUtils$TestResultList;", "Lcom/lagradost/cloudstream3/utils/TestingUtils$TestResult;", "results", "", "Lcom/lagradost/cloudstream3/SearchResponse;", "<init>", "(Ljava/util/List;)V", "getResults", "()Ljava/util/List;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class TestResultList extends TestResult {
        private final List<SearchResponse> results;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public TestResultList(List<? extends SearchResponse> results) {
            super(true);
            Intrinsics.checkNotNullParameter(results, "results");
            this.results = results;
        }

        public final List<SearchResponse> getResults() {
            return this.results;
        }
    }

    /* JADX INFO: compiled from: TestingUtils.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/lagradost/cloudstream3/utils/TestingUtils$TestResultLoad;", "Lcom/lagradost/cloudstream3/utils/TestingUtils$TestResult;", "extractorData", "", "shouldLoadLinks", "", "<init>", "(Ljava/lang/String;Z)V", "getExtractorData", "()Ljava/lang/String;", "getShouldLoadLinks", "()Z", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class TestResultLoad extends TestResult {
        private final String extractorData;
        private final boolean shouldLoadLinks;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TestResultLoad(String extractorData, boolean z) {
            super(true);
            Intrinsics.checkNotNullParameter(extractorData, "extractorData");
            this.extractorData = extractorData;
            this.shouldLoadLinks = z;
        }

        public final String getExtractorData() {
            return this.extractorData;
        }

        public final boolean getShouldLoadLinks() {
            return this.shouldLoadLinks;
        }
    }

    /* JADX INFO: compiled from: TestingUtils.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/lagradost/cloudstream3/utils/TestingUtils$TestResultProvider;", "Lcom/lagradost/cloudstream3/utils/TestingUtils$TestResult;", "success", "", "log", "", "Lcom/lagradost/cloudstream3/utils/TestingUtils$Logger$Message;", "exception", "", "<init>", "(ZLjava/util/List;Ljava/lang/Throwable;)V", "getLog", "()Ljava/util/List;", "getException", "()Ljava/lang/Throwable;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class TestResultProvider extends TestResult {
        private final Throwable exception;
        private final List<Logger.Message> log;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TestResultProvider(boolean z, List<Logger.Message> log, Throwable th) {
            super(z);
            Intrinsics.checkNotNullParameter(log, "log");
            this.log = log;
            this.exception = th;
        }

        public final List<Logger.Message> getLog() {
            return this.log;
        }

        public final Throwable getException() {
            return this.exception;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object testHomepage(MainAPI mainAPI, Logger logger, Continuation<? super TestResult> continuation) throws CancellationException, AssertionError {
        AnonymousClass1 anonymousClass1;
        ArrayList arrayListEmptyList;
        List<HomePageList> items;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object mainPage = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(mainPage);
                if (mainAPI.getHasMainPage()) {
                    MainPageData mainPageData = (MainPageData) CollectionsKt.first((List) mainAPI.getMainPage());
                    MainPageRequest mainPageRequest = new MainPageRequest(mainPageData.getName(), mainPageData.getData(), mainPageData.getHorizontalImages());
                    anonymousClass1.L$0 = mainAPI;
                    anonymousClass1.L$1 = logger;
                    anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(mainPageData);
                    anonymousClass1.label = 1;
                    mainPage = mainAPI.getMainPage(1, mainPageRequest, anonymousClass1);
                    if (mainPage == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return TestResult.INSTANCE.getPass();
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            logger = (Logger) anonymousClass1.L$1;
            mainAPI = (MainAPI) anonymousClass1.L$0;
            ResultKt.throwOnFailure(mainPage);
            HomePageResponse homePageResponse = (HomePageResponse) mainPage;
            if (homePageResponse == null) {
                logger.error("Provider " + mainAPI.getName() + " did not correctly load homepage!");
            } else if (homePageResponse.getItems().isEmpty()) {
                logger.warn("Provider " + mainAPI.getName() + " does not contain any homepage rows!");
            } else {
                List<HomePageList> items2 = homePageResponse.getItems();
                if (!(items2 instanceof Collection) || !items2.isEmpty()) {
                    Iterator<T> it = items2.iterator();
                    while (it.hasNext()) {
                        if (((HomePageList) it.next()).getList().isEmpty()) {
                            logger.warn("Provider " + mainAPI.getName() + " does not have any items in a homepage row!");
                            break;
                        }
                    }
                }
            }
            if (homePageResponse == null || (items = homePageResponse.getItems()) == null) {
                arrayListEmptyList = CollectionsKt.emptyList();
            } else {
                ArrayList arrayList = new ArrayList();
                Iterator<T> it2 = items.iterator();
                while (it2.hasNext()) {
                    CollectionsKt.addAll(arrayList, ((HomePageList) it2.next()).getList());
                }
                arrayListEmptyList = arrayList;
            }
            return new TestResultList(arrayListEmptyList);
        } catch (Throwable th) {
            if (th instanceof NotImplementedError) {
                fail("Provider marked as hasMainPage, while in reality is has not been implemented");
                throw new KotlinNothingValueException();
            }
            if (th instanceof CancellationException) {
                throw th;
            }
            String message = th.getMessage();
            if (message != null) {
                logger.warn("Exception thrown when loading homepage: \"" + message + '\"');
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:21:0x0061  */
    /* JADX WARN: Code duplicated, block: B:24:0x0098 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:0x0099  */
    /* JADX WARN: Code duplicated, block: B:28:0x00a1 A[Catch: all -> 0x0041, TryCatch #0 {all -> 0x0041, blocks: (B:12:0x003d, B:26:0x009d, B:28:0x00a1, B:30:0x00a7), top: B:56:0x003d }] */
    /* JADX WARN: Code duplicated, block: B:33:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:43:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:44:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0099 -> B:26:0x009d). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00c4 -> B:42:0x00cc). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object testSearch(com.lagradost.cloudstream3.MainAPI r9, java.util.List<java.lang.String> r10, com.lagradost.cloudstream3.utils.TestingUtils.Logger r11, kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.utils.TestingUtils.TestResult> r12) throws java.lang.AssertionError, java.util.concurrent.CancellationException {
        /*
            Method dump skipped, instruction units count: 276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.TestingUtils.testSearch(com.lagradost.cloudstream3.MainAPI, java.util.List, com.lagradost.cloudstream3.utils.TestingUtils$Logger, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x001c  */
    public final Object testLoad(MainAPI mainAPI, SearchResponse searchResponse, Logger logger, Continuation<? super TestResult> continuation) throws CancellationException, AssertionError {
        C09541 c09541;
        Episode episode;
        if (continuation instanceof C09541) {
            c09541 = (C09541) continuation;
            if ((c09541.label & Integer.MIN_VALUE) != 0) {
                c09541.label -= Integer.MIN_VALUE;
            } else {
                c09541 = new C09541(continuation);
            }
        } else {
            c09541 = new C09541(continuation);
        }
        Object objLoad = c09541.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c09541.label;
        boolean z = true;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(objLoad);
                if (!Intrinsics.areEqual(searchResponse.getApiName(), mainAPI.getName())) {
                    logger.warn("Wrong apiName on SearchResponse: " + mainAPI.getName() + " != " + searchResponse.getApiName());
                }
                String url = searchResponse.getUrl();
                c09541.L$0 = mainAPI;
                c09541.L$1 = searchResponse;
                c09541.L$2 = logger;
                c09541.label = 1;
                objLoad = mainAPI.load(url, c09541);
                if (objLoad == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                logger = (Logger) c09541.L$2;
                searchResponse = (SearchResponse) c09541.L$1;
                mainAPI = (MainAPI) c09541.L$0;
                ResultKt.throwOnFailure(objLoad);
            }
            LoadResponse loadResponse = (LoadResponse) objLoad;
            if (loadResponse == null) {
                logger.error("Returned null loadResponse on " + searchResponse.getUrl() + " on " + mainAPI.getName());
                return TestResult.INSTANCE.getFail();
            }
            if (!Intrinsics.areEqual(loadResponse.getApiName(), mainAPI.getName())) {
                logger.warn("Wrong apiName on LoadResponse: " + mainAPI.getName() + " != " + loadResponse.getApiName());
            }
            if (!mainAPI.getSupportedTypes().contains(loadResponse.getType())) {
                logger.warn("Api " + mainAPI.getName() + " on load does not contain any of the supportedTypes: " + loadResponse.getType());
            }
            String dataUrl = null;
            if (loadResponse instanceof AnimeLoadResponse) {
                if (!((AnimeLoadResponse) loadResponse).getEpisodes().keySet().isEmpty()) {
                    Set<DubStatus> setKeySet = ((AnimeLoadResponse) loadResponse).getEpisodes().keySet();
                    if (!(setKeySet instanceof Collection) || !setKeySet.isEmpty()) {
                        Iterator<T> it = setKeySet.iterator();
                        while (it.hasNext()) {
                            List<Episode> list = ((AnimeLoadResponse) loadResponse).getEpisodes().get((DubStatus) it.next());
                            if (list != null && !list.isEmpty()) {
                            }
                        }
                    }
                    List<Episode> list2 = ((AnimeLoadResponse) loadResponse).getEpisodes().get(CollectionsKt.firstOrNull(((AnimeLoadResponse) loadResponse).getEpisodes().keySet()));
                    if (list2 != null && (episode = (Episode) CollectionsKt.firstOrNull((List) list2)) != null) {
                        dataUrl = episode.getData();
                    }
                }
                logger.error("Api " + mainAPI.getName() + " got no episodes on " + ((AnimeLoadResponse) loadResponse).getUrl());
                return TestResult.INSTANCE.getFail();
            }
            if (loadResponse instanceof MovieLoadResponse) {
                if (StringsKt.isBlank(((MovieLoadResponse) loadResponse).getDataUrl())) {
                    logger.error("Api " + mainAPI.getName() + " got no movie on " + ((MovieLoadResponse) loadResponse).getUrl());
                    return TestResult.INSTANCE.getFail();
                }
                dataUrl = ((MovieLoadResponse) loadResponse).getDataUrl();
            } else if (loadResponse instanceof TvSeriesLoadResponse) {
                if (((TvSeriesLoadResponse) loadResponse).getEpisodes().isEmpty()) {
                    logger.error("Api " + mainAPI.getName() + " got no episodes on " + ((TvSeriesLoadResponse) loadResponse).getUrl());
                    return TestResult.INSTANCE.getFail();
                }
                Episode episode2 = (Episode) CollectionsKt.firstOrNull((List) ((TvSeriesLoadResponse) loadResponse).getEpisodes());
                if (episode2 != null) {
                    dataUrl = episode2.getData();
                }
            } else if (loadResponse instanceof LiveStreamLoadResponse) {
                dataUrl = ((LiveStreamLoadResponse) loadResponse).getDataUrl();
            } else {
                logger.error("Unknown load response: " + Reflection.getOrCreateKotlinClass(loadResponse.getClass()).getQualifiedName());
                return TestResult.INSTANCE.getFail();
            }
            if (dataUrl == null) {
                return TestResult.INSTANCE.getFail();
            }
            if (loadResponse.getType() == TvType.CustomMedia) {
                z = false;
            }
            return new TestResultLoad(dataUrl, z);
        } catch (Throwable th) {
            if (th instanceof NotImplementedError) {
                fail("Provider has not implemented load()");
                throw new KotlinNothingValueException();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:31:0x00a3 A[Catch: all -> 0x00e8, TryCatch #2 {all -> 0x00e8, blocks: (B:29:0x009b, B:31:0x00a3, B:35:0x00bc, B:37:0x00c0, B:38:0x00e7), top: B:55:0x009b }] */
    /* JADX WARN: Code duplicated, block: B:33:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:34:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:37:0x00c0 A[Catch: all -> 0x00e8, TryCatch #2 {all -> 0x00e8, blocks: (B:29:0x009b, B:31:0x00a3, B:35:0x00bc, B:37:0x00c0, B:38:0x00e7), top: B:55:0x009b }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Instruction removed from duplicated block: B:31:0x00a3, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:37:0x00c0, please report this as an issue */
    public final Object testLinkLoading(final MainAPI mainAPI, String str, final Logger logger, Continuation<? super TestResult> continuation) throws Throwable {
        C09531 c09531;
        final Ref.IntRef intRef;
        MainAPI mainAPI2;
        Throwable th;
        Object obj;
        MainAPI mainAPI3;
        if (continuation instanceof C09531) {
            c09531 = (C09531) continuation;
            if ((c09531.label & Integer.MIN_VALUE) != 0) {
                c09531.label -= Integer.MIN_VALUE;
            } else {
                c09531 = new C09531(continuation);
            }
        } else {
            c09531 = new C09531(continuation);
        }
        C09531 c09532 = c09531;
        Object obj2 = c09532.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c09532.label;
        boolean z = true;
        if (i == 0) {
            ResultKt.throwOnFailure(obj2);
            assertNotNull("Api " + mainAPI.getName() + " has invalid url on episode", str);
            if (str == null) {
                return TestResult.INSTANCE.getFail();
            }
            intRef = new Ref.IntRef();
            try {
                Function1<? super SubtitleFile, Unit> function1 = new Function1() { // from class: com.lagradost.cloudstream3.utils.TestingUtils$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return TestingUtils.testLinkLoading$lambda$0((SubtitleFile) obj3);
                    }
                };
                Function1<? super ExtractorLink, Unit> function2 = new Function1() { // from class: com.lagradost.cloudstream3.utils.TestingUtils$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return TestingUtils.testLinkLoading$lambda$1(logger, mainAPI, intRef, (ExtractorLink) obj3);
                    }
                };
                c09532.L$0 = mainAPI;
                c09532.L$1 = str;
                c09532.L$2 = logger;
                c09532.L$3 = intRef;
                c09532.label = 1;
                mainAPI2 = mainAPI;
                try {
                    Object objLoadLinks = mainAPI2.loadLinks(str, false, function1, function2, c09532);
                    if (objLoadLinks == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj = objLoadLinks;
                    mainAPI3 = mainAPI2;
                    str = str;
                    if (((Boolean) obj).booleanValue()) {
                        logger.log("Links loaded: " + intRef.element);
                        if (intRef.element > 0) {
                            z = false;
                        }
                        return new TestResult(z);
                    }
                    fail("Api " + mainAPI3.getName() + " returns false on loadLinks() with " + intRef.element + " links loaded");
                    throw new KotlinNothingValueException();
                } catch (Throwable th2) {
                    th = th2;
                    str = str;
                }
            } catch (Throwable th3) {
                mainAPI2 = mainAPI;
                th = th3;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Ref.IntRef intRef2 = (Ref.IntRef) c09532.L$3;
            logger = (Logger) c09532.L$2;
            str = (String) c09532.L$1;
            MainAPI mainAPI4 = (MainAPI) c09532.L$0;
            try {
                ResultKt.throwOnFailure(obj2);
                intRef = intRef2;
                mainAPI3 = mainAPI4;
                obj = obj2;
                try {
                    if (((Boolean) obj).booleanValue()) {
                        logger.log("Links loaded: " + intRef.element);
                        if (intRef.element > 0) {
                            z = false;
                        }
                        return new TestResult(z);
                    }
                    fail("Api " + mainAPI3.getName() + " returns false on loadLinks() with " + intRef.element + " links loaded");
                    throw new KotlinNothingValueException();
                } catch (Throwable th4) {
                    mainAPI2 = mainAPI3;
                    th = th4;
                }
            } catch (Throwable th5) {
                th = th5;
                mainAPI2 = mainAPI4;
            }
        }
        if (th instanceof NotImplementedError) {
            fail("Provider has not implemented loadLinks()");
            throw new KotlinNothingValueException();
        }
        logger.error("Failed link loading on " + mainAPI2.getName() + " using data: " + str);
        throw th;
    }

    static final Unit testLinkLoading$lambda$0(SubtitleFile it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    static final Unit testLinkLoading$lambda$1(Logger logger, MainAPI mainAPI, Ref.IntRef intRef, ExtractorLink link) {
        Intrinsics.checkNotNullParameter(link, "link");
        logger.log("Video loaded: " + link.getName());
        INSTANCE.assertTrue("Api " + mainAPI.getName() + " returns link with invalid url " + link.getUrl(), link.getUrl().length() > 4);
        intRef.element++;
        return Unit.INSTANCE;
    }

    public final void getDeferredProviderTests(CoroutineScope scope, MainAPI[] providers, Function2<? super MainAPI, ? super TestResultProvider, Unit> callback) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(providers, "providers");
        Intrinsics.checkNotNullParameter(callback, "callback");
        for (MainAPI mainAPI : providers) {
            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new TestingUtils$getDeferredProviderTests$1$1(mainAPI, callback, null), 3, null);
        }
    }
}
