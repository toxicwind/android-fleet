package org.schabi.newpipe.extractor.utils;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonArray$$ExternalSyntheticLambda0;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import j$.util.Collection;
import j$.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.jsoup.Jsoup;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.services.youtube.extractors.YoutubeChannelExtractor$$ExternalSyntheticLambda1;

/* JADX INFO: loaded from: classes8.dex */
public final class JsonUtils {
    private JsonUtils() {
    }

    @Nonnull
    public static Object getValue(@Nonnull JsonObject jsonObject, @Nonnull String str) throws ParsingException {
        List listAsList = Arrays.asList(str.split("\\."));
        JsonObject object = getObject(jsonObject, (List<String>) listAsList.subList(0, listAsList.size() - 1));
        if (object == null) {
            throw new ParsingException("Unable to get " + str);
        }
        Object obj = object.get(listAsList.get(listAsList.size() - 1));
        if (obj != null) {
            return obj;
        }
        throw new ParsingException("Unable to get " + str);
    }

    private static <T> T getInstanceOf(@Nonnull JsonObject jsonObject, @Nonnull String str, @Nonnull Class<T> cls) throws ParsingException {
        Object value = getValue(jsonObject, str);
        if (cls.isInstance(value)) {
            return cls.cast(value);
        }
        throw new ParsingException("Wrong data type at path " + str);
    }

    @Nonnull
    public static String getString(@Nonnull JsonObject jsonObject, @Nonnull String str) throws ParsingException {
        return (String) getInstanceOf(jsonObject, str, String.class);
    }

    @Nonnull
    public static Boolean getBoolean(@Nonnull JsonObject jsonObject, @Nonnull String str) throws ParsingException {
        return (Boolean) getInstanceOf(jsonObject, str, Boolean.class);
    }

    @Nonnull
    public static Number getNumber(@Nonnull JsonObject jsonObject, @Nonnull String str) throws ParsingException {
        return (Number) getInstanceOf(jsonObject, str, Number.class);
    }

    @Nonnull
    public static JsonObject getObject(@Nonnull JsonObject jsonObject, @Nonnull String str) throws ParsingException {
        return (JsonObject) getInstanceOf(jsonObject, str, JsonObject.class);
    }

    @Nonnull
    public static JsonArray getArray(@Nonnull JsonObject jsonObject, @Nonnull String str) throws ParsingException {
        return (JsonArray) getInstanceOf(jsonObject, str, JsonArray.class);
    }

    @Nonnull
    public static List<Object> getValues(@Nonnull JsonArray jsonArray, @Nonnull String str) throws ParsingException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jsonArray.size(); i++) {
            arrayList.add(getValue(jsonArray.getObject(i), str));
        }
        return arrayList;
    }

    @Nullable
    private static JsonObject getObject(@Nonnull JsonObject jsonObject, @Nonnull List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext() && (jsonObject = jsonObject.getObject(it.next())) != null) {
        }
        return jsonObject;
    }

    public static JsonArray toJsonArray(String str) throws ParsingException {
        try {
            return JsonParser.array().from(str);
        } catch (JsonParserException e) {
            throw new ParsingException("Could not parse JSON", e);
        }
    }

    public static JsonObject toJsonObject(String str) throws ParsingException {
        try {
            return JsonParser.object().from(str);
        } catch (JsonParserException e) {
            throw new ParsingException("Could not parse JSON", e);
        }
    }

    public static JsonObject getJsonData(String str, String str2) throws JsonParserException, ArrayIndexOutOfBoundsException {
        return JsonParser.object().from(Jsoup.parse(str).getElementsByAttribute(str2).attr(str2));
    }

    public static List<String> getStringListFromJsonArray(@Nonnull JsonArray jsonArray) {
        return (List) Collection.EL.stream(jsonArray).filter(new JsonArray$$ExternalSyntheticLambda0(String.class)).map(new YoutubeChannelExtractor$$ExternalSyntheticLambda1(String.class)).collect(Collectors.toList());
    }
}
