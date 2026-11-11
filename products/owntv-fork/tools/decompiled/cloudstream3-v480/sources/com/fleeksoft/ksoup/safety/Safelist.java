package com.fleeksoft.ksoup.safety;

import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.internal.Normalizer;
import com.fleeksoft.ksoup.nodes.Attribute;
import com.fleeksoft.ksoup.nodes.Attributes;
import com.fleeksoft.ksoup.nodes.Element;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Safelist.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 42\u00020\u0001:\u0006/01234B\u0007¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0005J\u001f\u0010\u0012\u001a\u00020\u00002\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015¢\u0006\u0002\u0010\u0016J\u001f\u0010\u0017\u001a\u00020\u00002\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015¢\u0006\u0002\u0010\u0016J'\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u00152\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015¢\u0006\u0002\u0010\u001aJ'\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u00152\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015¢\u0006\u0002\u0010\u001aJ\u001e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u0015J\u0016\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u0015J\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u0011J\u0006\u0010\u0010\u001a\u00020\u0011J/\u0010!\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00152\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015¢\u0006\u0002\u0010\"J/\u0010#\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00152\u0012\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015¢\u0006\u0002\u0010\"J\u0010\u0010$\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u0015H\u0016J \u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u00152\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0016J&\u0010+\u001a\u00020\u00112\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0,H\u0002J\u000e\u0010-\u001a\u00020.2\u0006\u0010&\u001a\u00020\u0015R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00070\nX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\f\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\r0\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\u000e\u001a \u0012\u0004\u0012\u00020\b\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/fleeksoft/ksoup/safety/Safelist;", "", "<init>", "()V", "copy", "(Lcom/fleeksoft/ksoup/safety/Safelist;)V", "tagNames", "", "Lcom/fleeksoft/ksoup/safety/Safelist$TagName;", "attributes", "", "Lcom/fleeksoft/ksoup/safety/Safelist$AttributeKey;", "enforcedAttributes", "Lcom/fleeksoft/ksoup/safety/Safelist$AttributeValue;", "protocols", "Lcom/fleeksoft/ksoup/safety/Safelist$Protocol;", "preserveRelativeLinks", "", "addTags", "tags", "", "", "([Ljava/lang/String;)Lcom/fleeksoft/ksoup/safety/Safelist;", "removeTags", "addAttributes", "tag", "(Ljava/lang/String;[Ljava/lang/String;)Lcom/fleeksoft/ksoup/safety/Safelist;", "removeAttributes", "addEnforcedAttribute", "attribute", ES6Iterator.VALUE_PROPERTY, "removeEnforcedAttribute", "preserve", "addProtocols", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcom/fleeksoft/ksoup/safety/Safelist;", "removeProtocols", "isSafeTag", "isSafeAttribute", "tagName", "el", "Lcom/fleeksoft/ksoup/nodes/Element;", "attr", "Lcom/fleeksoft/ksoup/nodes/Attribute;", "testValidProtocol", "", "getEnforcedAttributes", "Lcom/fleeksoft/ksoup/nodes/Attributes;", "TagName", "AttributeKey", "AttributeValue", "Protocol", "TypedValue", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Safelist {
    private static final String All = ":all";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Map<TagName, Set<AttributeKey>> attributes;
    private final Map<TagName, Map<AttributeKey, AttributeValue>> enforcedAttributes;
    private boolean preserveRelativeLinks;
    private final Map<TagName, Map<AttributeKey, Set<Protocol>>> protocols;
    private final Set<TagName> tagNames;

    public Safelist() {
        this.tagNames = new HashSet();
        this.attributes = new LinkedHashMap();
        this.enforcedAttributes = new LinkedHashMap();
        this.protocols = new LinkedHashMap();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Safelist(Safelist copy) {
        this();
        Intrinsics.checkNotNullParameter(copy, "copy");
        this.tagNames.addAll(copy.tagNames);
        for (Map.Entry<TagName, Set<AttributeKey>> entry : copy.attributes.entrySet()) {
            this.attributes.put(entry.getKey(), new HashSet(entry.getValue()));
        }
        for (Map.Entry<TagName, Map<AttributeKey, AttributeValue>> entry2 : copy.enforcedAttributes.entrySet()) {
            this.enforcedAttributes.put(entry2.getKey(), new HashMap(entry2.getValue()));
        }
        for (Map.Entry<TagName, Map<AttributeKey, Set<Protocol>>> entry3 : copy.protocols.entrySet()) {
            TagName key = entry3.getKey();
            Map<AttributeKey, Set<Protocol>> value = entry3.getValue();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry<AttributeKey, Set<Protocol>> entry4 : value.entrySet()) {
                linkedHashMap.put(entry4.getKey(), new HashSet(entry4.getValue()));
            }
            this.protocols.put(key, linkedHashMap);
        }
        this.preserveRelativeLinks = copy.preserveRelativeLinks;
    }

    public final Safelist addTags(String... tags) {
        Intrinsics.checkNotNullParameter(tags, "tags");
        for (String str : tags) {
            Validate.INSTANCE.notEmpty(str);
            Validate.INSTANCE.isFalse(StringsKt.equals(str, "noscript", true), "noscript is unsupported in Safelists, due to incompatibilities between parsers with and without script-mode enabled");
            this.tagNames.add(TagName.INSTANCE.valueOf(str));
        }
        return this;
    }

    public final Safelist removeTags(String... tags) {
        Intrinsics.checkNotNullParameter(tags, "tags");
        for (String str : tags) {
            Validate.INSTANCE.notEmpty(str);
            TagName tagNameValueOf = TagName.INSTANCE.valueOf(str);
            if (this.tagNames.remove(tagNameValueOf)) {
                this.attributes.remove(tagNameValueOf);
                this.enforcedAttributes.remove(tagNameValueOf);
                this.protocols.remove(tagNameValueOf);
            }
        }
        return this;
    }

    public final Safelist addAttributes(String tag, String... attributes) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Validate.INSTANCE.notEmpty(tag);
        Validate.INSTANCE.isTrue(!(attributes.length == 0), "No attribute names supplied.");
        addTags(tag);
        TagName tagNameValueOf = TagName.INSTANCE.valueOf(tag);
        HashSet hashSet = new HashSet();
        for (String str : attributes) {
            Validate.INSTANCE.notEmpty(str);
            hashSet.add(AttributeKey.INSTANCE.valueOf(str));
        }
        Map<TagName, Set<AttributeKey>> map = this.attributes;
        LinkedHashSet linkedHashSet = map.get(tagNameValueOf);
        if (linkedHashSet == null) {
            linkedHashSet = new LinkedHashSet();
            map.put(tagNameValueOf, linkedHashSet);
        }
        linkedHashSet.addAll(hashSet);
        return this;
    }

    public final Safelist removeAttributes(String tag, String... attributes) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Validate.INSTANCE.notEmpty(tag);
        Validate.INSTANCE.isTrue(!(attributes.length == 0), "No attribute names supplied.");
        TagName tagNameValueOf = TagName.INSTANCE.valueOf(tag);
        HashSet hashSet = new HashSet();
        for (String str : attributes) {
            Validate.INSTANCE.notEmpty(str);
            hashSet.add(AttributeKey.INSTANCE.valueOf(str));
        }
        if (this.tagNames.contains(tagNameValueOf) && this.attributes.containsKey(tagNameValueOf)) {
            Set<AttributeKey> set = this.attributes.get(tagNameValueOf);
            Intrinsics.checkNotNull(set);
            Set<AttributeKey> set2 = set;
            set2.removeAll(hashSet);
            if (set2.isEmpty()) {
                this.attributes.remove(tagNameValueOf);
            }
        }
        if (Intrinsics.areEqual(tag, All)) {
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

    public final Safelist addEnforcedAttribute(String tag, String attribute, String value) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        Intrinsics.checkNotNullParameter(value, "value");
        Validate.INSTANCE.notEmpty(tag);
        Validate.INSTANCE.notEmpty(attribute);
        Validate.INSTANCE.notEmpty(value);
        TagName tagNameValueOf = TagName.INSTANCE.valueOf(tag);
        this.tagNames.add(tagNameValueOf);
        AttributeKey attributeKeyValueOf = AttributeKey.INSTANCE.valueOf(attribute);
        AttributeValue attributeValueValueOf = AttributeValue.INSTANCE.valueOf(value);
        if (this.enforcedAttributes.containsKey(tagNameValueOf)) {
            Map<AttributeKey, AttributeValue> map = this.enforcedAttributes.get(tagNameValueOf);
            if (map != null) {
                map.put(attributeKeyValueOf, attributeValueValueOf);
            }
            return this;
        }
        HashMap map2 = new HashMap();
        map2.put(attributeKeyValueOf, attributeValueValueOf);
        this.enforcedAttributes.put(tagNameValueOf, map2);
        return this;
    }

    public final Safelist removeEnforcedAttribute(String tag, String attribute) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        Validate.INSTANCE.notEmpty(tag);
        Validate.INSTANCE.notEmpty(attribute);
        TagName tagNameValueOf = TagName.INSTANCE.valueOf(tag);
        if (this.tagNames.contains(tagNameValueOf) && this.enforcedAttributes.containsKey(tagNameValueOf)) {
            AttributeKey attributeKeyValueOf = AttributeKey.INSTANCE.valueOf(attribute);
            Map<AttributeKey, AttributeValue> map = this.enforcedAttributes.get(tagNameValueOf);
            if (map != null) {
                map.remove(attributeKeyValueOf);
            }
            if (map != null && map.isEmpty()) {
                this.enforcedAttributes.remove(tagNameValueOf);
            }
        }
        return this;
    }

    public final Safelist preserveRelativeLinks(boolean preserve) {
        this.preserveRelativeLinks = preserve;
        return this;
    }

    /* JADX INFO: renamed from: preserveRelativeLinks, reason: from getter */
    public final boolean getPreserveRelativeLinks() {
        return this.preserveRelativeLinks;
    }

    public final Safelist addProtocols(String tag, String attribute, String... protocols) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        Validate.INSTANCE.notEmpty(tag);
        Validate.INSTANCE.notEmpty(attribute);
        TagName tagNameValueOf = TagName.INSTANCE.valueOf(tag);
        AttributeKey attributeKeyValueOf = AttributeKey.INSTANCE.valueOf(attribute);
        Map<TagName, Map<AttributeKey, Set<Protocol>>> map = this.protocols;
        LinkedHashMap linkedHashMap = map.get(tagNameValueOf);
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap();
            map.put(tagNameValueOf, linkedHashMap);
        }
        Map<AttributeKey, Set<Protocol>> map2 = linkedHashMap;
        HashSet hashSet = map2.get(attributeKeyValueOf);
        if (hashSet == null) {
            hashSet = new HashSet();
            map2.put(attributeKeyValueOf, hashSet);
        }
        Set<Protocol> set = hashSet;
        for (String str : protocols) {
            Validate.INSTANCE.notEmpty(str);
            set.add(Protocol.INSTANCE.valueOf(str));
        }
        return this;
    }

    public final Safelist removeProtocols(String tag, String attribute, String... removeProtocols) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        Intrinsics.checkNotNullParameter(removeProtocols, "removeProtocols");
        Validate.INSTANCE.notEmpty(tag);
        Validate.INSTANCE.notEmpty(attribute);
        TagName tagNameValueOf = TagName.INSTANCE.valueOf(tag);
        AttributeKey attributeKeyValueOf = AttributeKey.INSTANCE.valueOf(attribute);
        Validate.INSTANCE.isTrue(this.protocols.containsKey(tagNameValueOf), "Cannot remove a protocol that is not set.");
        Map<AttributeKey, Set<Protocol>> map = this.protocols.get(tagNameValueOf);
        Intrinsics.checkNotNull(map);
        Map<AttributeKey, Set<Protocol>> map2 = map;
        Validate.INSTANCE.isTrue(map2.containsKey(attributeKeyValueOf), "Cannot remove a protocol that is not set.");
        Set<Protocol> set = map2.get(attributeKeyValueOf);
        Intrinsics.checkNotNull(set);
        Set<Protocol> set2 = set;
        for (String str : removeProtocols) {
            Validate.INSTANCE.notEmpty(str);
            set2.remove(Protocol.INSTANCE.valueOf(str));
        }
        if (set2.isEmpty()) {
            map2.remove(attributeKeyValueOf);
            if (map2.isEmpty()) {
                this.protocols.remove(tagNameValueOf);
            }
        }
        return this;
    }

    public boolean isSafeTag(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return this.tagNames.contains(TagName.INSTANCE.valueOf(tag));
    }

    public boolean isSafeAttribute(String tagName, Element el, Attribute attr) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        Intrinsics.checkNotNullParameter(el, "el");
        Intrinsics.checkNotNullParameter(attr, "attr");
        TagName tagNameValueOf = TagName.INSTANCE.valueOf(tagName);
        AttributeKey attributeKeyValueOf = AttributeKey.INSTANCE.valueOf(attr.getKey());
        Set<AttributeKey> set = this.attributes.get(tagNameValueOf);
        if (set != null && set.contains(attributeKeyValueOf)) {
            if (this.protocols.containsKey(tagNameValueOf)) {
                Map<AttributeKey, Set<Protocol>> map = this.protocols.get(tagNameValueOf);
                Intrinsics.checkNotNull(map);
                Map<AttributeKey, Set<Protocol>> map2 = map;
                if (map2.containsKey(attributeKeyValueOf)) {
                    Set<Protocol> set2 = map2.get(attributeKeyValueOf);
                    Intrinsics.checkNotNull(set2);
                    if (!testValidProtocol(el, attr, set2)) {
                        return false;
                    }
                }
            }
            return true;
        }
        if (this.enforcedAttributes.get(tagNameValueOf) != null) {
            Attributes enforcedAttributes = getEnforcedAttributes(tagName);
            String key = attr.getKey();
            if (enforcedAttributes.hasKeyIgnoreCase(key)) {
                return enforcedAttributes.getIgnoreCase(key).equals(attr.getValue());
            }
        }
        return !Intrinsics.areEqual(tagName, All) && isSafeAttribute(All, el, attr);
    }

    private final boolean testValidProtocol(Element el, Attribute attr, Set<Protocol> protocols) {
        String strAbsUrl = el.absUrl(attr.getKey());
        if (strAbsUrl.length() == 0) {
            strAbsUrl = attr.getValue();
        }
        if (!this.preserveRelativeLinks) {
            attr.setValue(strAbsUrl);
        }
        Iterator<Protocol> it = protocols.iterator();
        while (it.hasNext()) {
            String string = it.next().getValue();
            if (!Intrinsics.areEqual(string, "#")) {
                if (StringsKt.startsWith$default(Normalizer.INSTANCE.lowerCase(strAbsUrl), string + ":", false, 2, (Object) null)) {
                    return true;
                }
            } else if (INSTANCE.isValidAnchor(strAbsUrl)) {
                return true;
            }
        }
        return false;
    }

    public final Attributes getEnforcedAttributes(String tagName) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        Attributes attributes = new Attributes();
        TagName tagNameValueOf = TagName.INSTANCE.valueOf(tagName);
        if (this.enforcedAttributes.containsKey(tagNameValueOf)) {
            Map<AttributeKey, AttributeValue> map = this.enforcedAttributes.get(tagNameValueOf);
            Intrinsics.checkNotNull(map);
            for (Map.Entry<AttributeKey, AttributeValue> entry : map.entrySet()) {
                attributes.put(entry.getKey().getValue(), entry.getValue().getValue());
            }
        }
        return attributes;
    }

    /* JADX INFO: compiled from: Safelist.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/fleeksoft/ksoup/safety/Safelist$TagName;", "Lcom/fleeksoft/ksoup/safety/Safelist$TypedValue;", ES6Iterator.VALUE_PROPERTY, "", "<init>", "(Ljava/lang/String;)V", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class TagName extends TypedValue {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        /* JADX INFO: compiled from: Safelist.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/fleeksoft/ksoup/safety/Safelist$TagName$Companion;", "", "<init>", "()V", "valueOf", "Lcom/fleeksoft/ksoup/safety/Safelist$TagName;", ES6Iterator.VALUE_PROPERTY, "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final TagName valueOf(String value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return new TagName(Normalizer.INSTANCE.lowerCase(value));
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TagName(String value) {
            super(value);
            Intrinsics.checkNotNullParameter(value, "value");
        }
    }

    /* JADX INFO: compiled from: Safelist.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/fleeksoft/ksoup/safety/Safelist$AttributeKey;", "Lcom/fleeksoft/ksoup/safety/Safelist$TypedValue;", ES6Iterator.VALUE_PROPERTY, "", "<init>", "(Ljava/lang/String;)V", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AttributeKey extends TypedValue {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        /* JADX INFO: compiled from: Safelist.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/fleeksoft/ksoup/safety/Safelist$AttributeKey$Companion;", "", "<init>", "()V", "valueOf", "Lcom/fleeksoft/ksoup/safety/Safelist$AttributeKey;", ES6Iterator.VALUE_PROPERTY, "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final AttributeKey valueOf(String value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return new AttributeKey(Normalizer.INSTANCE.lowerCase(value));
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AttributeKey(String value) {
            super(value);
            Intrinsics.checkNotNullParameter(value, "value");
        }
    }

    /* JADX INFO: compiled from: Safelist.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/fleeksoft/ksoup/safety/Safelist$AttributeValue;", "Lcom/fleeksoft/ksoup/safety/Safelist$TypedValue;", ES6Iterator.VALUE_PROPERTY, "", "<init>", "(Ljava/lang/String;)V", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AttributeValue extends TypedValue {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        /* JADX INFO: compiled from: Safelist.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/fleeksoft/ksoup/safety/Safelist$AttributeValue$Companion;", "", "<init>", "()V", "valueOf", "Lcom/fleeksoft/ksoup/safety/Safelist$AttributeValue;", ES6Iterator.VALUE_PROPERTY, "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final AttributeValue valueOf(String value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return new AttributeValue(value);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AttributeValue(String value) {
            super(value);
            Intrinsics.checkNotNullParameter(value, "value");
        }
    }

    /* JADX INFO: compiled from: Safelist.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/fleeksoft/ksoup/safety/Safelist$Protocol;", "Lcom/fleeksoft/ksoup/safety/Safelist$TypedValue;", ES6Iterator.VALUE_PROPERTY, "", "<init>", "(Ljava/lang/String;)V", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Protocol extends TypedValue {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        /* JADX INFO: compiled from: Safelist.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/fleeksoft/ksoup/safety/Safelist$Protocol$Companion;", "", "<init>", "()V", "valueOf", "Lcom/fleeksoft/ksoup/safety/Safelist$Protocol;", ES6Iterator.VALUE_PROPERTY, "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final Protocol valueOf(String value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return new Protocol(value);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Protocol(String value) {
            super(value);
            Intrinsics.checkNotNullParameter(value, "value");
        }
    }

    /* JADX INFO: compiled from: Safelist.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b \u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0006\u001a\u00020\u0007H\u0096\u0080\u0004J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\n\u0010\u000b\u001a\u00020\u0003H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/fleeksoft/ksoup/safety/Safelist$TypedValue;", "", ES6Iterator.VALUE_PROPERTY, "", "<init>", "(Ljava/lang/String;)V", "hashCode", "", "equals", "", "other", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static abstract class TypedValue {
        private final String value;

        public TypedValue(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this.value = value;
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || getClass() != other.getClass()) {
                return false;
            }
            return Intrinsics.areEqual(this.value, ((TypedValue) other).value);
        }

        /* JADX INFO: renamed from: toString, reason: from getter */
        public String getValue() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: Safelist.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0007J\u0006\u0010\t\u001a\u00020\u0007J\u0006\u0010\n\u001a\u00020\u0007J\u0006\u0010\u000b\u001a\u00020\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/fleeksoft/ksoup/safety/Safelist$Companion;", "", "<init>", "()V", "All", "", "none", "Lcom/fleeksoft/ksoup/safety/Safelist;", "simpleText", "basic", "basicWithImages", "relaxed", "isValidAnchor", "", ES6Iterator.VALUE_PROPERTY, "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Safelist none() {
            return new Safelist();
        }

        public final Safelist simpleText() {
            return new Safelist().addTags("b", "em", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "strong", "u");
        }

        public final Safelist basic() {
            return new Safelist().addTags(CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "blockquote", "br", "cite", "code", "dd", CmcdConfiguration.KEY_DEADLINE, "dt", "em", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "li", "ol", TtmlNode.TAG_P, "pre", "q", "small", TtmlNode.TAG_SPAN, "strike", "strong", "sub", "sup", "u", "ul").addAttributes(CmcdData.OBJECT_TYPE_AUDIO_ONLY, "href").addAttributes("blockquote", "cite").addAttributes("q", "cite").addProtocols(CmcdData.OBJECT_TYPE_AUDIO_ONLY, "href", "ftp", "http", "https", "mailto").addProtocols("blockquote", "cite", "http", "https").addProtocols("cite", "cite", "http", "https").addEnforcedAttribute(CmcdData.OBJECT_TYPE_AUDIO_ONLY, LinkHeader.Parameters.Rel, "nofollow");
        }

        public final Safelist basicWithImages() {
            return basic().addTags("img").addAttributes("img", "align", "alt", "height", "src", "title", "width").addProtocols("img", "src", "http", "https");
        }

        public final Safelist relaxed() {
            return new Safelist().addTags(CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "blockquote", "br", MediaTrack.ROLE_CAPTION, "cite", "code", "col", "colgroup", "dd", TtmlNode.TAG_DIV, CmcdConfiguration.KEY_DEADLINE, "dt", "em", "h1", "h2", "h3", "h4", "h5", "h6", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "img", "li", "ol", TtmlNode.TAG_P, "pre", "q", "small", TtmlNode.TAG_SPAN, "strike", "strong", "sub", "sup", "table", "tbody", "td", "tfoot", "th", "thead", "tr", "u", "ul").addAttributes(CmcdData.OBJECT_TYPE_AUDIO_ONLY, "href", "title").addAttributes("blockquote", "cite").addAttributes("col", TtmlNode.TAG_SPAN, "width").addAttributes("colgroup", TtmlNode.TAG_SPAN, "width").addAttributes("img", "align", "alt", "height", "src", "title", "width").addAttributes("ol", "start", "type").addAttributes("q", "cite").addAttributes("table", "summary", "width").addAttributes("td", "abbr", "axis", "colspan", "rowspan", "width").addAttributes("th", "abbr", "axis", "colspan", "rowspan", "scope", "width").addAttributes("ul", "type").addProtocols(CmcdData.OBJECT_TYPE_AUDIO_ONLY, "href", "ftp", "http", "https", "mailto").addProtocols("blockquote", "cite", "http", "https").addProtocols("cite", "cite", "http", "https").addProtocols("img", "src", "http", "https").addProtocols("q", "cite", "http", "https");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isValidAnchor(String value) {
            if (StringsKt.startsWith$default(value, "#", false, 2, (Object) null)) {
                if (!new Regex(".*\\s.*").matches(value)) {
                    return true;
                }
            }
            return false;
        }
    }
}
