package com.lagradost.nicehttp;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: Requests.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J-\u0010\u0002\u001a\u0002H\u0003\"\b\b\u0000\u0010\u0003*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0007H&¢\u0006\u0002\u0010\bJ/\u0010\t\u001a\u0004\u0018\u0001H\u0003\"\b\b\u0000\u0010\u0003*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0007H&¢\u0006\u0002\u0010\bJ\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0001H&¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lcom/lagradost/nicehttp/ResponseParser;", "", "parse", ExifInterface.GPS_DIRECTION_TRUE, "text", "", "kClass", "Lkotlin/reflect/KClass;", "(Ljava/lang/String;Lkotlin/reflect/KClass;)Ljava/lang/Object;", "parseSafe", "writeValueAsString", "obj", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface ResponseParser {
    <T> T parse(String text, KClass<T> kClass);

    <T> T parseSafe(String text, KClass<T> kClass);

    String writeValueAsString(Object obj);
}
