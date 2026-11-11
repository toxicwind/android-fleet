package io.ktor.http;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Parameters.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0017\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\r\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001d\u0010\u0006\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u0006\u0010\u000b\u001a#\u0010\u0006\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\f¢\u0006\u0004\b\u0006\u0010\u000e\u001a'\u0010\u0006\u001a\u00020\u00052\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000f¢\u0006\u0004\b\u0006\u0010\u0011\u001aE\u0010\u0006\u001a\u00020\u000526\u0010\u0014\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u00130\u0012\"\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u0013¢\u0006\u0004\b\u0006\u0010\u0015\u001a&\u0010\u001a\u001a\u00020\u00052\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\u0002\b\u0018¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u001c\u0010\u001d\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0005H\u0086\u0002¢\u0006\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"", ContentDisposition.Parameters.Size, "Lio/ktor/http/ParametersBuilder;", "ParametersBuilder", "(I)Lio/ktor/http/ParametersBuilder;", "Lio/ktor/http/Parameters;", "parametersOf", "()Lio/ktor/http/Parameters;", "", "name", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/String;Ljava/lang/String;)Lio/ktor/http/Parameters;", "", "values", "(Ljava/lang/String;Ljava/util/List;)Lio/ktor/http/Parameters;", "", "map", "(Ljava/util/Map;)Lio/ktor/http/Parameters;", "", "Lkotlin/Pair;", "pairs", "([Lkotlin/Pair;)Lio/ktor/http/Parameters;", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "builder", "parameters", "(Lkotlin/jvm/functions/Function1;)Lio/ktor/http/Parameters;", "other", "plus", "(Lio/ktor/http/Parameters;Lio/ktor/http/Parameters;)Lio/ktor/http/Parameters;", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ParametersKt {
    public static final ParametersBuilder ParametersBuilder(int i) {
        return new ParametersBuilderImpl(i);
    }

    public static /* synthetic */ ParametersBuilder ParametersBuilder$default(int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8;
        }
        return ParametersBuilder(i);
    }

    public static final Parameters parametersOf() {
        return Parameters.INSTANCE.getEmpty();
    }

    public static final Parameters parametersOf(String name, String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        return new ParametersSingleImpl(name, CollectionsKt.listOf(value));
    }

    public static final Parameters parametersOf(String name, List<String> values) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(values, "values");
        return new ParametersSingleImpl(name, values);
    }

    public static final Parameters parametersOf(Map<String, ? extends List<String>> map) {
        Intrinsics.checkNotNullParameter(map, "map");
        return new ParametersImpl(map);
    }

    public static final Parameters parametersOf(Pair<String, ? extends List<String>>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        return new ParametersImpl(MapsKt.toMap(ArraysKt.asList(pairs)));
    }

    public static final Parameters parameters(Function1<? super ParametersBuilder, Unit> builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Parameters.Companion companion = Parameters.INSTANCE;
        ParametersBuilder parametersBuilderParametersBuilder$default = ParametersBuilder$default(0, 1, null);
        builder.invoke(parametersBuilderParametersBuilder$default);
        return parametersBuilderParametersBuilder$default.build();
    }

    public static final Parameters plus(Parameters parameters, Parameters other) {
        Intrinsics.checkNotNullParameter(parameters, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        if (parameters.getCaseInsensitiveName() == other.getCaseInsensitiveName()) {
            if (parameters.isEmpty()) {
                return other;
            }
            if (other.isEmpty()) {
                return parameters;
            }
            Parameters.Companion companion = Parameters.INSTANCE;
            ParametersBuilder parametersBuilderParametersBuilder$default = ParametersBuilder$default(0, 1, null);
            parametersBuilderParametersBuilder$default.appendAll(parameters);
            parametersBuilderParametersBuilder$default.appendAll(other);
            return parametersBuilderParametersBuilder$default.build();
        }
        throw new IllegalArgumentException("Cannot concatenate Parameters with case-sensitive and case-insensitive names");
    }
}
