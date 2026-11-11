package org.schabi.newpipe.extractor.services.youtube;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Consumer$CC;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import j$.util.stream.Collectors;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.MetaInfo;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class YoutubeMetaInfoHelper {
    private YoutubeMetaInfoHelper() {
    }

    @Nonnull
    public static List<MetaInfo> getMetaInfo(@Nonnull JsonArray jsonArray) throws ParsingException {
        final ArrayList arrayList = new ArrayList();
        Iterator<Object> it = jsonArray.iterator();
        while (it.hasNext()) {
            JsonObject jsonObject = (JsonObject) it.next();
            if (jsonObject.has("itemSectionRenderer")) {
                for (JsonObject jsonObject2 : jsonObject.getObject("itemSectionRenderer").getArray("contents")) {
                    if (jsonObject2.has("infoPanelContentRenderer")) {
                        arrayList.add(getInfoPanelContent(jsonObject2.getObject("infoPanelContentRenderer")));
                    }
                    if (jsonObject2.has("clarificationRenderer")) {
                        arrayList.add(getClarificationRenderer(jsonObject2.getObject("clarificationRenderer")));
                    }
                    if (jsonObject2.has("emergencyOneboxRenderer")) {
                        getEmergencyOneboxRenderer(jsonObject2.getObject("emergencyOneboxRenderer"), new Consumer() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeMetaInfoHelper$$ExternalSyntheticLambda2
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                arrayList.add((MetaInfo) obj);
                            }

                            public /* synthetic */ Consumer andThen(Consumer consumer) {
                                return Consumer$CC.$default$andThen(this, consumer);
                            }
                        });
                    }
                }
            }
        }
        return arrayList;
    }

    @Nonnull
    private static MetaInfo getInfoPanelContent(@Nonnull JsonObject jsonObject) throws ParsingException {
        String textFromObject;
        MetaInfo metaInfo = new MetaInfo();
        StringBuilder sb = new StringBuilder();
        for (Object obj : jsonObject.getArray("paragraphs")) {
            if (sb.length() != 0) {
                sb.append("<br>");
            }
            sb.append(YoutubeParsingHelper.getTextFromObject((JsonObject) obj));
        }
        metaInfo.setContent(new Description(sb.toString(), 1));
        if (!jsonObject.has("sourceEndpoint")) {
            return metaInfo;
        }
        try {
            metaInfo.addUrl(new URL((String) Objects.requireNonNull(YoutubeParsingHelper.extractCachedUrlIfNeeded(YoutubeParsingHelper.getUrlFromNavigationEndpoint(jsonObject.getObject("sourceEndpoint"))))));
            if (jsonObject.has("inlineSource")) {
                textFromObject = YoutubeParsingHelper.getTextFromObject(jsonObject.getObject("inlineSource"));
            } else {
                textFromObject = YoutubeParsingHelper.getTextFromObject(jsonObject.getObject("disclaimer"));
            }
            if (Utils.isNullOrEmpty(textFromObject)) {
                throw new ParsingException("Could not get metadata info link text.");
            }
            metaInfo.addUrlText(textFromObject);
            return metaInfo;
        } catch (NullPointerException | MalformedURLException e) {
            throw new ParsingException("Could not get metadata info URL", e);
        }
    }

    @Nonnull
    private static MetaInfo getClarificationRenderer(@Nonnull JsonObject jsonObject) throws ParsingException {
        String urlFromNavigationEndpoint;
        MetaInfo metaInfo = new MetaInfo();
        String textFromObject = YoutubeParsingHelper.getTextFromObject(jsonObject.getObject("contentTitle"));
        String textFromObject2 = YoutubeParsingHelper.getTextFromObject(jsonObject.getObject("text"));
        if (textFromObject == null || textFromObject2 == null) {
            throw new ParsingException("Could not extract clarification renderer content");
        }
        metaInfo.setTitle(textFromObject);
        metaInfo.setContent(new Description(textFromObject2, 3));
        if (jsonObject.has("actionButton")) {
            JsonObject object = jsonObject.getObject("actionButton").getObject("buttonRenderer");
            try {
                metaInfo.addUrl(new URL((String) Objects.requireNonNull(YoutubeParsingHelper.extractCachedUrlIfNeeded(YoutubeParsingHelper.getUrlFromNavigationEndpoint(object.getObject("command"))))));
                String textFromObject3 = YoutubeParsingHelper.getTextFromObject(object.getObject("text"));
                if (Utils.isNullOrEmpty(textFromObject3)) {
                    throw new ParsingException("Could not get metadata info link text.");
                }
                metaInfo.addUrlText(textFromObject3);
            } catch (NullPointerException | MalformedURLException e) {
                throw new ParsingException("Could not get metadata info URL", e);
            }
        }
        if (!jsonObject.has("secondaryEndpoint") || !jsonObject.has("secondarySource") || (urlFromNavigationEndpoint = YoutubeParsingHelper.getUrlFromNavigationEndpoint(jsonObject.getObject("secondaryEndpoint"))) == null || YoutubeParsingHelper.isGoogleURL(urlFromNavigationEndpoint)) {
            return metaInfo;
        }
        try {
            metaInfo.addUrl(new URL(urlFromNavigationEndpoint));
            String textFromObject4 = YoutubeParsingHelper.getTextFromObject(jsonObject.getObject("secondarySource"));
            if (textFromObject4 != null) {
                urlFromNavigationEndpoint = textFromObject4;
            }
            metaInfo.addUrlText(urlFromNavigationEndpoint);
            return metaInfo;
        } catch (MalformedURLException e2) {
            throw new ParsingException("Could not get metadata info secondary URL", e2);
        }
    }

    private static void getEmergencyOneboxRenderer(@Nonnull JsonObject jsonObject, Consumer<MetaInfo> consumer) throws ParsingException {
        String string;
        List<JsonObject> list = (List) Collection.EL.stream(jsonObject.values()).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeMetaInfoHelper$$ExternalSyntheticLambda0
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return YoutubeMetaInfoHelper.lambda$getEmergencyOneboxRenderer$0(obj);
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeMetaInfoHelper$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject("singleActionEmergencySupportRenderer");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new ParsingException("Could not extract any meta info from emergency renderer");
        }
        for (JsonObject jsonObject2 : list) {
            MetaInfo metaInfo = new MetaInfo();
            String textFromObjectOrThrow = YoutubeParsingHelper.getTextFromObjectOrThrow(jsonObject2.getObject("title"), "title");
            if (jsonObject2.has("actionText")) {
                string = "\n" + YoutubeParsingHelper.getTextFromObjectOrThrow(jsonObject2.getObject("actionText"), "action");
            } else if (jsonObject2.has("contacts")) {
                JsonArray array = jsonObject2.getArray("contacts");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < array.size(); i++) {
                    sb.append("\n");
                    sb.append(YoutubeParsingHelper.getTextFromObjectOrThrow(array.getObject(i).getObject("actionText"), "contacts.actionText"));
                }
                string = sb.toString();
            } else {
                string = "";
            }
            String textFromObjectOrThrow2 = YoutubeParsingHelper.getTextFromObjectOrThrow(jsonObject2.getObject("detailsText"), "details");
            String textFromObjectOrThrow3 = YoutubeParsingHelper.getTextFromObjectOrThrow(jsonObject2.getObject("navigationText"), "urlText");
            metaInfo.setTitle(textFromObjectOrThrow);
            metaInfo.setContent(new Description(textFromObjectOrThrow2 + string, 3));
            metaInfo.addUrlText(textFromObjectOrThrow3);
            String urlFromNavigationEndpoint = YoutubeParsingHelper.getUrlFromNavigationEndpoint(jsonObject2.getObject("navigationEndpoint"));
            if (urlFromNavigationEndpoint == null) {
                throw new ParsingException("Could not extract emergency renderer url");
            }
            try {
                metaInfo.addUrl(new URL(Utils.replaceHttpWithHttps(urlFromNavigationEndpoint)));
                consumer.accept(metaInfo);
            } catch (MalformedURLException e) {
                throw new ParsingException("Could not parse emergency renderer url", e);
            }
        }
    }

    static /* synthetic */ boolean lambda$getEmergencyOneboxRenderer$0(Object obj) {
        return (obj instanceof JsonObject) && ((JsonObject) obj).has("singleActionEmergencySupportRenderer");
    }
}
