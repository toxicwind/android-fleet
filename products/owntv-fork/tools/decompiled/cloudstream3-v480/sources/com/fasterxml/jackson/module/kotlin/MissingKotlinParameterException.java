package com.fasterxml.jackson.module.kotlin;

import androidx.core.app.NotificationCompat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import java.io.Closeable;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KParameter;

/* JADX INFO: compiled from: Exceptions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B#\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/MissingKotlinParameterException;", "Lcom/fasterxml/jackson/databind/exc/MismatchedInputException;", "parameter", "Lkotlin/reflect/KParameter;", "processor", "Ljava/io/Closeable;", NotificationCompat.CATEGORY_MESSAGE, "", "(Lkotlin/reflect/KParameter;Ljava/io/Closeable;Ljava/lang/String;)V", "Lcom/fasterxml/jackson/core/JsonParser;", "(Lkotlin/reflect/KParameter;Lcom/fasterxml/jackson/core/JsonParser;Ljava/lang/String;)V", "getParameter", "()Lkotlin/reflect/KParameter;", "jackson-module-kotlin"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class MissingKotlinParameterException extends MismatchedInputException {
    private final KParameter parameter;

    public /* synthetic */ MissingKotlinParameterException(KParameter kParameter, JsonParser jsonParser, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(kParameter, (i & 2) != 0 ? null : jsonParser, str);
    }

    public final KParameter getParameter() {
        return this.parameter;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MissingKotlinParameterException(KParameter parameter, JsonParser jsonParser, String msg) {
        super(jsonParser, msg);
        Intrinsics.checkNotNullParameter(parameter, "parameter");
        Intrinsics.checkNotNullParameter(msg, "msg");
        this.parameter = parameter;
    }

    public /* synthetic */ MissingKotlinParameterException(KParameter kParameter, Closeable closeable, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(kParameter, (i & 2) != 0 ? null : closeable, str);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "Use main constructor", replaceWith = @ReplaceWith(expression = "MissingKotlinParameterException(KParameter, JsonParser?, String)", imports = {}))
    public MissingKotlinParameterException(KParameter parameter, Closeable closeable, String msg) {
        this(parameter, (JsonParser) closeable, msg);
        Intrinsics.checkNotNullParameter(parameter, "parameter");
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (closeable == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.fasterxml.jackson.core.JsonParser");
        }
    }
}
