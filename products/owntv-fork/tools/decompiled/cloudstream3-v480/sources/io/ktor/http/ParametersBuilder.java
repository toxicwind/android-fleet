package io.ktor.http;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.ktor.util.StringValuesBuilder;
import kotlin.Metadata;

/* JADX INFO: compiled from: Parameters.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005À\u0006\u0003"}, d2 = {"Lio/ktor/http/ParametersBuilder;", "Lio/ktor/util/StringValuesBuilder;", "Lio/ktor/http/Parameters;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "()Lio/ktor/http/Parameters;", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface ParametersBuilder extends StringValuesBuilder {
    @Override // io.ktor.util.StringValuesBuilder
    Parameters build();
}
