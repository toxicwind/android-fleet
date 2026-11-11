package org.jsoup.safety;

import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import j$.util.Objects;
import j$.util.function.Function$CC;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

/* JADX INFO: loaded from: classes8.dex */
public class Safelist {
    private static final String All = ":all";
    private static final TagName AllTag = TagName.valueOf(All);
    private final Map<TagName, Set<AttributeKey>> attributes;
    private final Map<TagName, Map<AttributeKey, AttributeValue>> enforcedAttributes;
    private boolean preserveRelativeLinks;
    private final Map<TagName, Map<AttributeKey, Set<Protocol>>> protocols;
    private final Set<TagName> tagNames;

    public static Safelist none() {
        return new Safelist();
    }

    public static Safelist simpleText() {
        return new Safelist().addTags("b", "em", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "strong", "u");
    }

    public static Safelist basic() {
        return new Safelist().addTags(CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "blockquote", "br", "cite", "code", "dd", CmcdConfiguration.KEY_DEADLINE, "dt", "em", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "li", "ol", TtmlNode.TAG_P, "pre", "q", "small", TtmlNode.TAG_SPAN, "strike", "strong", "sub", "sup", "u", "ul").addAttributes(CmcdData.OBJECT_TYPE_AUDIO_ONLY, "href").addAttributes("blockquote", "cite").addAttributes("q", "cite").addProtocols(CmcdData.OBJECT_TYPE_AUDIO_ONLY, "href", "ftp", "http", "https", "mailto").addProtocols("blockquote", "cite", "http", "https").addProtocols("cite", "cite", "http", "https").addEnforcedAttribute(CmcdData.OBJECT_TYPE_AUDIO_ONLY, LinkHeader.Parameters.Rel, "nofollow");
    }

    public static Safelist basicWithImages() {
        return basic().addTags("img").addAttributes("img", "align", "alt", "height", "src", "title", "width").addProtocols("img", "src", "http", "https");
    }

    public static Safelist relaxed() {
        return new Safelist().addTags(CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "blockquote", "br", MediaTrack.ROLE_CAPTION, "cite", "code", "col", "colgroup", "dd", TtmlNode.TAG_DIV, CmcdConfiguration.KEY_DEADLINE, "dt", "em", "h1", "h2", "h3", "h4", "h5", "h6", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "img", "li", "ol", TtmlNode.TAG_P, "pre", "q", "small", TtmlNode.TAG_SPAN, "strike", "strong", "sub", "sup", "table", "tbody", "td", "tfoot", "th", "thead", "tr", "u", "ul").addAttributes(CmcdData.OBJECT_TYPE_AUDIO_ONLY, "href", "title").addAttributes("blockquote", "cite").addAttributes("col", TtmlNode.TAG_SPAN, "width").addAttributes("colgroup", TtmlNode.TAG_SPAN, "width").addAttributes("img", "align", "alt", "height", "src", "title", "width").addAttributes("ol", "start", "type").addAttributes("q", "cite").addAttributes("table", "summary", "width").addAttributes("td", "abbr", "axis", "colspan", "rowspan", "width").addAttributes("th", "abbr", "axis", "colspan", "rowspan", "scope", "width").addAttributes("ul", "type").addProtocols(CmcdData.OBJECT_TYPE_AUDIO_ONLY, "href", "ftp", "http", "https", "mailto").addProtocols("blockquote", "cite", "http", "https").addProtocols("cite", "cite", "http", "https").addProtocols("img", "src", "http", "https").addProtocols("q", "cite", "http", "https");
    }

    public Safelist() {
        this.tagNames = new HashSet();
        this.attributes = new HashMap();
        this.enforcedAttributes = new HashMap();
        this.protocols = new HashMap();
        this.preserveRelativeLinks = false;
    }

    public Safelist(Safelist safelist) {
        this();
        this.tagNames.addAll(safelist.tagNames);
        for (Map.Entry<TagName, Set<AttributeKey>> entry : safelist.attributes.entrySet()) {
            this.attributes.put(entry.getKey(), new HashSet(entry.getValue()));
        }
        for (Map.Entry<TagName, Map<AttributeKey, AttributeValue>> entry2 : safelist.enforcedAttributes.entrySet()) {
            this.enforcedAttributes.put(entry2.getKey(), new HashMap(entry2.getValue()));
        }
        for (Map.Entry<TagName, Map<AttributeKey, Set<Protocol>>> entry3 : safelist.protocols.entrySet()) {
            HashMap map = new HashMap();
            for (Map.Entry<AttributeKey, Set<Protocol>> entry4 : entry3.getValue().entrySet()) {
                map.put(entry4.getKey(), new HashSet(entry4.getValue()));
            }
            this.protocols.put(entry3.getKey(), map);
        }
        this.preserveRelativeLinks = safelist.preserveRelativeLinks;
    }

    public Safelist addTags(String... strArr) {
        Validate.notNull(strArr);
        for (String str : strArr) {
            Validate.notEmpty(str);
            Validate.isFalse(str.equalsIgnoreCase("noscript"), "noscript is unsupported in Safelists, due to incompatibilities between parsers with and without script-mode enabled");
            this.tagNames.add(TagName.valueOf(str));
        }
        return this;
    }

    public Safelist removeTags(String... strArr) {
        Validate.notNull(strArr);
        for (String str : strArr) {
            Validate.notEmpty(str);
            TagName tagNameValueOf = TagName.valueOf(str);
            if (this.tagNames.remove(tagNameValueOf)) {
                this.attributes.remove(tagNameValueOf);
                this.enforcedAttributes.remove(tagNameValueOf);
                this.protocols.remove(tagNameValueOf);
            }
        }
        return this;
    }

    public Safelist addAttributes(String str, String... strArr) {
        Validate.notEmpty(str);
        Validate.notNull(strArr);
        Validate.isTrue(strArr.length > 0, "No attribute names supplied.");
        addTags(str);
        TagName tagNameValueOf = TagName.valueOf(str);
        HashSet hashSet = new HashSet();
        for (String str2 : strArr) {
            Validate.notEmpty(str2);
            hashSet.add(AttributeKey.valueOf(str2));
        }
        ((Set) j$.util.Map.EL.computeIfAbsent(this.attributes, tagNameValueOf, new Function() { // from class: org.jsoup.safety.Safelist$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Safelist.lambda$addAttributes$0((Safelist.TagName) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        })).addAll(hashSet);
        return this;
    }

    static /* synthetic */ Set lambda$addAttributes$0(TagName tagName) {
        return new HashSet();
    }

    public Safelist removeAttributes(String str, String... strArr) {
        Validate.notEmpty(str);
        Validate.notNull(strArr);
        Validate.isTrue(strArr.length > 0, "No attribute names supplied.");
        TagName tagNameValueOf = TagName.valueOf(str);
        HashSet hashSet = new HashSet();
        for (String str2 : strArr) {
            Validate.notEmpty(str2);
            hashSet.add(AttributeKey.valueOf(str2));
        }
        if (this.tagNames.contains(tagNameValueOf) && this.attributes.containsKey(tagNameValueOf)) {
            Set<AttributeKey> set = this.attributes.get(tagNameValueOf);
            set.removeAll(hashSet);
            if (set.isEmpty()) {
                this.attributes.remove(tagNameValueOf);
            }
        }
        if (str.equals(All)) {
            Iterator<Map.Entry<TagName, Set<AttributeKey>>> it = this.attributes.entrySet().iterator();
            while (it.hasNext()) {
                Set<AttributeKey> value = it.next().getValue();
                value.removeAll(hashSet);
                if (value.isEmpty()) {
                    it.remove();
                }
            }
        }
        return this;
    }

    public Safelist addEnforcedAttribute(String str, String str2, String str3) {
        Validate.notEmpty(str);
        Validate.notEmpty(str2);
        Validate.notEmpty(str3);
        TagName tagNameValueOf = TagName.valueOf(str);
        this.tagNames.add(tagNameValueOf);
        ((Map) j$.util.Map.EL.computeIfAbsent(this.enforcedAttributes, tagNameValueOf, new Function() { // from class: org.jsoup.safety.Safelist$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Safelist.lambda$addEnforcedAttribute$1((Safelist.TagName) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        })).put(AttributeKey.valueOf(str2), AttributeValue.valueOf(str3));
        return this;
    }

    static /* synthetic */ Map lambda$addEnforcedAttribute$1(TagName tagName) {
        return new HashMap();
    }

    public Safelist removeEnforcedAttribute(String str, String str2) {
        Validate.notEmpty(str);
        Validate.notEmpty(str2);
        TagName tagNameValueOf = TagName.valueOf(str);
        if (this.tagNames.contains(tagNameValueOf) && this.enforcedAttributes.containsKey(tagNameValueOf)) {
            AttributeKey attributeKeyValueOf = AttributeKey.valueOf(str2);
            Map<AttributeKey, AttributeValue> map = this.enforcedAttributes.get(tagNameValueOf);
            map.remove(attributeKeyValueOf);
            if (map.isEmpty()) {
                this.enforcedAttributes.remove(tagNameValueOf);
            }
        }
        return this;
    }

    public Safelist preserveRelativeLinks(boolean z) {
        this.preserveRelativeLinks = z;
        return this;
    }

    public boolean preserveRelativeLinks() {
        return this.preserveRelativeLinks;
    }

    public Safelist addProtocols(String str, String str2, String... strArr) {
        Validate.notEmpty(str);
        Validate.notEmpty(str2);
        Validate.notNull(strArr);
        Set set = (Set) j$.util.Map.EL.computeIfAbsent((Map) j$.util.Map.EL.computeIfAbsent(this.protocols, TagName.valueOf(str), new Function() { // from class: org.jsoup.safety.Safelist$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Safelist.lambda$addProtocols$2((Safelist.TagName) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }), AttributeKey.valueOf(str2), new Function() { // from class: org.jsoup.safety.Safelist$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Safelist.lambda$addProtocols$3((Safelist.AttributeKey) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        });
        for (String str3 : strArr) {
            Validate.notEmpty(str3);
            set.add(Protocol.valueOf(str3));
        }
        return this;
    }

    static /* synthetic */ Map lambda$addProtocols$2(TagName tagName) {
        return new HashMap();
    }

    static /* synthetic */ Set lambda$addProtocols$3(AttributeKey attributeKey) {
        return new HashSet();
    }

    public Safelist removeProtocols(String str, String str2, String... strArr) {
        Validate.notEmpty(str);
        Validate.notEmpty(str2);
        Validate.notNull(strArr);
        TagName tagNameValueOf = TagName.valueOf(str);
        AttributeKey attributeKeyValueOf = AttributeKey.valueOf(str2);
        Validate.isTrue(this.protocols.containsKey(tagNameValueOf), "Cannot remove a protocol that is not set.");
        Map<AttributeKey, Set<Protocol>> map = this.protocols.get(tagNameValueOf);
        Validate.isTrue(map.containsKey(attributeKeyValueOf), "Cannot remove a protocol that is not set.");
        Set<Protocol> set = map.get(attributeKeyValueOf);
        for (String str3 : strArr) {
            Validate.notEmpty(str3);
            set.remove(Protocol.valueOf(str3));
        }
        if (set.isEmpty()) {
            map.remove(attributeKeyValueOf);
            if (map.isEmpty()) {
                this.protocols.remove(tagNameValueOf);
            }
        }
        return this;
    }

    public boolean isSafeTag(String str) {
        return this.tagNames.contains(TagName.valueOf(str));
    }

    public boolean isSafeAttribute(String str, Element element, Attribute attribute) {
        TagName tagNameValueOf = TagName.valueOf(str);
        AttributeKey attributeKeyValueOf = AttributeKey.valueOf(attribute.getKey());
        Set<AttributeKey> set = this.attributes.get(tagNameValueOf);
        if (set != null && set.contains(attributeKeyValueOf)) {
            if (this.protocols.containsKey(tagNameValueOf)) {
                Map<AttributeKey, Set<Protocol>> map = this.protocols.get(tagNameValueOf);
                if (map.containsKey(attributeKeyValueOf) && !isSafeProtocol(getProtocolValue(element, attribute), map.get(attributeKeyValueOf))) {
                    return false;
                }
            }
            return true;
        }
        Map<AttributeKey, AttributeValue> map2 = this.enforcedAttributes.get(tagNameValueOf);
        if (map2 == null || !map2.containsKey(attributeKeyValueOf)) {
            return !str.equals(All) && isSafeAttribute(All, element, attribute);
        }
        return map2.get(attributeKeyValueOf).equals(AttributeValue.valueOf(attribute.getValue()));
    }

    private String getProtocolValue(Element element, Attribute attribute) {
        String strAbsUrl = element.absUrl(attribute.getKey());
        return strAbsUrl.isEmpty() ? attribute.getValue() : strAbsUrl;
    }

    private boolean isSafeProtocol(String str, Set<Protocol> set) {
        Iterator<Protocol> it = set.iterator();
        while (it.hasNext()) {
            String string = it.next().toString();
            if (string.equals("#")) {
                if (isValidAnchor(str)) {
                    return true;
                }
            } else {
                if (Normalizer.lowerCase(str).startsWith(string + ":")) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean shouldAbsUrl(String str, String str2) {
        if (this.preserveRelativeLinks) {
            return false;
        }
        return shouldAbsUrl(TagName.valueOf(str), AttributeKey.valueOf(str2));
    }

    private boolean shouldAbsUrl(TagName tagName, AttributeKey attributeKey) {
        Set<AttributeKey> set = this.attributes.get(tagName);
        if (set != null && set.contains(attributeKey)) {
            Map<AttributeKey, Set<Protocol>> map = this.protocols.get(tagName);
            return map != null && map.containsKey(attributeKey);
        }
        Map<AttributeKey, AttributeValue> map2 = this.enforcedAttributes.get(tagName);
        if (map2 != null && map2.containsKey(attributeKey)) {
            return false;
        }
        TagName tagName2 = AllTag;
        return !tagName.equals(tagName2) && shouldAbsUrl(tagName2, attributeKey);
    }

    private static boolean isValidAnchor(String str) {
        return str.startsWith("#") && !str.matches(".*\\s.*");
    }

    public Attributes getEnforcedAttributes(String str) {
        Attributes attributes = new Attributes();
        TagName tagNameValueOf = TagName.valueOf(str);
        if (this.enforcedAttributes.containsKey(tagNameValueOf)) {
            for (Map.Entry<AttributeKey, AttributeValue> entry : this.enforcedAttributes.get(tagNameValueOf).entrySet()) {
                attributes.put(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        return attributes;
    }

    static class TagName extends TypedValue {
        TagName(String str) {
            super(str);
        }

        static TagName valueOf(String str) {
            return new TagName(Normalizer.lowerCase(str));
        }
    }

    static class AttributeKey extends TypedValue {
        AttributeKey(String str) {
            super(str);
        }

        static AttributeKey valueOf(String str) {
            return new AttributeKey(Normalizer.lowerCase(str));
        }
    }

    static class AttributeValue extends TypedValue {
        AttributeValue(String str) {
            super(str);
        }

        static AttributeValue valueOf(String str) {
            return new AttributeValue(str);
        }
    }

    static class Protocol extends TypedValue {
        Protocol(String str) {
            super(str);
        }

        static Protocol valueOf(String str) {
            return new Protocol(str);
        }
    }

    static abstract class TypedValue {
        private final String value;

        TypedValue(String str) {
            Validate.notNull(str);
            this.value = str;
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return Objects.equals(this.value, ((TypedValue) obj).value);
        }

        public String toString() {
            return this.value;
        }
    }
}
