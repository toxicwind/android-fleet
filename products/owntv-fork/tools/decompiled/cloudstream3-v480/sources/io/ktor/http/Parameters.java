package io.ktor.http;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.ktor.util.StringValues;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Parameters.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003À\u0006\u0003"}, d2 = {"Lio/ktor/http/Parameters;", "Lio/ktor/util/StringValues;", "Companion", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface Parameters extends StringValues {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: renamed from: io.ktor.http.Parameters$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Parameters.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = Parameters.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: Parameters.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static boolean contains(Parameters parameters, String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return StringValues.CC.$default$contains(parameters, name);
        }

        @Deprecated
        public static boolean contains(Parameters parameters, String name, String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            return StringValues.CC.$default$contains(parameters, name, value);
        }

        @Deprecated
        public static void forEach(Parameters parameters, Function2<? super String, ? super List<String>, Unit> body) {
            Intrinsics.checkNotNullParameter(body, "body");
            StringValues.CC.$default$forEach(parameters, body);
        }

        @Deprecated
        public static String get(Parameters parameters, String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return StringValues.CC.$default$get(parameters, name);
        }
    }

    /* JADX INFO: compiled from: Parameters.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\n\u001a\u00020\t2\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\bø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0010"}, d2 = {"Lio/ktor/http/Parameters$Companion;", "", "<init>", "()V", "Lkotlin/Function1;", "Lio/ktor/http/ParametersBuilder;", "", "Lkotlin/ExtensionFunctionType;", "builder", "Lio/ktor/http/Parameters;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "(Lkotlin/jvm/functions/Function1;)Lio/ktor/http/Parameters;", "Empty", "Lio/ktor/http/Parameters;", "getEmpty", "()Lio/ktor/http/Parameters;", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Parameters Empty = EmptyParameters.INSTANCE;

        private Companion() {
        }

        public final Parameters getEmpty() {
            return Empty;
        }

        public final Parameters build(Function1<? super ParametersBuilder, Unit> builder) {
            Intrinsics.checkNotNullParameter(builder, "builder");
            ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
            builder.invoke(parametersBuilderParametersBuilder$default);
            return parametersBuilderParametersBuilder$default.build();
        }
    }
}
