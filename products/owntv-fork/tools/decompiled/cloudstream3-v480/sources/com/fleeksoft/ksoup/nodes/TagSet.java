package com.fleeksoft.ksoup.nodes;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.internal.SharedConstants;
import com.fleeksoft.ksoup.parser.ParseSettings;
import com.fleeksoft.ksoup.parser.Tag;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TagSet.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\u0018\u0000 -2\u00020\u0001:\u0001-BI\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0000\u00124\b\u0002\u0010\u0003\u001a.\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0018\u00010\u0004j\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0018\u0001`\b¢\u0006\u0004\b\t\u0010\nB\t\b\u0016¢\u0006\u0004\b\t\u0010\u000bB\u0011\b\u0016\u0012\u0006\u0010\f\u001a\u00020\u0000¢\u0006\u0004\b\t\u0010\rJ\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0006J\u0010\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0006H\u0002J\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0010J(\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00102\b\u0010\u001d\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001fJ\"\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u00102\b\b\u0002\u0010 \u001a\u00020!H\u0007J\u001a\u0010\"\u001a\u00020\u00002\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005J\u0014\u0010$\u001a\u00020\u001f2\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\n\u0010&\u001a\u00020'H\u0096\u0080\u0004J7\u0010(\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u00102\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00100*2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\u0002¢\u0006\u0002\u0010,R)\u0010\u000e\u001a\u001a\u0012\u0004\u0012\u00020\u0010\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00060\u000f0\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R:\u0010\u0003\u001a.\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0018\u00010\u0004j\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0018\u0001`\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/fleeksoft/ksoup/nodes/TagSet;", "", "original", "customizers", "Ljava/util/ArrayList;", "Lkotlin/Function1;", "Lcom/fleeksoft/ksoup/parser/Tag;", "", "Lkotlin/collections/ArrayList;", "<init>", "(Lcom/fleeksoft/ksoup/nodes/TagSet;Ljava/util/ArrayList;)V", "()V", "template", "(Lcom/fleeksoft/ksoup/nodes/TagSet;)V", "tags", "", "", "getTags", "()Ljava/util/Map;", "source", "getSource", "()Lcom/fleeksoft/ksoup/nodes/TagSet;", "add", "tag", "doAdd", "get", "tagName", "namespace", "valueOf", "normalNameParam", "preserveTagCase", "", "settings", "Lcom/fleeksoft/ksoup/parser/ParseSettings;", "onNewTag", "customizer", "equals", "other", "hashCode", "", "setupTags", "tagNames", "", "tagModifier", "(Ljava/lang/String;[Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Lcom/fleeksoft/ksoup/nodes/TagSet;", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TagSet {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<TagSet> HtmlTagSet$delegate = LazyKt.lazy(new Function0() { // from class: com.fleeksoft.ksoup.nodes.TagSet$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return TagSet.INSTANCE.initHtmlDefault();
        }
    });
    private ArrayList<Function1<Tag, Unit>> customizers;
    private final TagSet source;
    private final Map<String, Map<String, Tag>> tags;

    public final Tag valueOf(String tagName, String namespace) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        return valueOf$default(this, tagName, namespace, null, 4, null);
    }

    public final Map<String, Map<String, Tag>> getTags() {
        return this.tags;
    }

    public final TagSet getSource() {
        return this.source;
    }

    public TagSet(TagSet tagSet, ArrayList<Function1<Tag, Unit>> arrayList) {
        this.tags = new HashMap();
        this.source = tagSet;
        this.customizers = arrayList;
    }

    public /* synthetic */ TagSet(TagSet tagSet, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(tagSet, (i & 2) != 0 ? null : arrayList);
    }

    public TagSet() {
        this(null, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TagSet(TagSet template) {
        this(template.source, INSTANCE.copyCustomizers(template));
        Intrinsics.checkNotNullParameter(template, "template");
        if (template.tags.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Map<String, Tag>> entry : template.tags.entrySet()) {
            HashMap map = new HashMap(entry.getValue().size());
            for (Map.Entry<String, Tag> entry2 : entry.getValue().entrySet()) {
                map.put(entry2.getKey(), entry2.getValue().clone2());
            }
            this.tags.put(entry.getKey(), map);
        }
    }

    public final TagSet add(Tag tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        tag.set(1);
        doAdd(tag);
        return this;
    }

    private final void doAdd(Tag tag) {
        ArrayList<Function1<Tag, Unit>> arrayList = this.customizers;
        if (arrayList != null) {
            Iterator<Function1<Tag, Unit>> it = arrayList.iterator();
            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
            while (it.hasNext()) {
                Function1<Tag, Unit> next = it.next();
                Intrinsics.checkNotNullExpressionValue(next, "next(...)");
                next.invoke(tag);
            }
        }
        Map<String, Map<String, Tag>> map = this.tags;
        String strNamespace = tag.namespace();
        HashMap map2 = map.get(strNamespace);
        if (map2 == null) {
            map2 = new HashMap();
            map.put(strNamespace, map2);
        }
        map2.put(tag.getTagName(), tag);
    }

    public final Tag get(String tagName, String namespace) {
        Tag tag;
        Tag tag2;
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        Map<String, Tag> map = this.tags.get(namespace);
        if (map != null && (tag2 = map.get(tagName)) != null) {
            return tag2;
        }
        TagSet tagSet = this.source;
        if (tagSet == null || (tag = tagSet.get(tagName, namespace)) == null) {
            return null;
        }
        Tag tagClone2 = tag.clone2();
        doAdd(tagClone2);
        return tagClone2;
    }

    public final Tag valueOf(String tagName, String normalNameParam, String namespace, boolean preserveTagCase) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        String string = StringsKt.trim((CharSequence) tagName).toString();
        Validate.INSTANCE.notEmpty(string);
        Tag tag = get(string, namespace);
        if (tag != null) {
            return tag;
        }
        if (normalNameParam == null) {
            normalNameParam = ParseSettings.INSTANCE.normalName(tagName);
        }
        if (!preserveTagCase) {
            string = normalNameParam;
        }
        Tag tag2 = get(normalNameParam, namespace);
        if (tag2 != null) {
            if (!preserveTagCase || Intrinsics.areEqual(string, normalNameParam)) {
                return tag2;
            }
            Tag tagClone2 = tag2.clone2();
            tagClone2.setTagName(string);
            doAdd(tagClone2);
            return tagClone2;
        }
        Tag tag3 = new Tag(string, normalNameParam, namespace);
        doAdd(tag3);
        return tag3;
    }

    public static /* synthetic */ Tag valueOf$default(TagSet tagSet, String str, String str2, ParseSettings parseSettings, int i, Object obj) {
        if ((i & 4) != 0) {
            parseSettings = ParseSettings.INSTANCE.getPreserveCase();
        }
        return tagSet.valueOf(str, str2, parseSettings);
    }

    public final Tag valueOf(String tagName, String namespace, ParseSettings settings) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        Intrinsics.checkNotNullParameter(namespace, "namespace");
        Intrinsics.checkNotNullParameter(settings, "settings");
        return valueOf(tagName, null, namespace, settings.getPreserveTagCase());
    }

    public final TagSet onNewTag(Function1<? super Tag, Unit> customizer) {
        Intrinsics.checkNotNullParameter(customizer, "customizer");
        if (this.customizers == null) {
            this.customizers = new ArrayList<>();
        }
        ArrayList<Function1<Tag, Unit>> arrayList = this.customizers;
        if (arrayList != null) {
            arrayList.add(customizer);
        }
        return this;
    }

    public boolean equals(Object other) {
        if (other instanceof TagSet) {
            return Intrinsics.areEqual(this.tags, ((TagSet) other).tags);
        }
        return false;
    }

    public int hashCode() {
        return this.tags.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TagSet setupTags(String namespace, String[] tagNames, Function1<? super Tag, Unit> tagModifier) {
        for (String str : tagNames) {
            Tag tag = get(str, namespace);
            if (tag == null) {
                tag = new Tag(str, str, namespace);
                tag.setOptions(0);
                add(tag);
            }
            tagModifier.invoke(tag);
        }
        return this;
    }

    /* JADX INFO: compiled from: TagSet.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\n\u001a\u00020\u0005J:\u0010\u000b\u001a.\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r\u0018\u00010\fj\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r\u0018\u0001`\u00102\u0006\u0010\u0011\u001a\u00020\u0005J\u0006\u0010\u0012\u001a\u00020\u0005R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/TagSet$Companion;", "", "<init>", "()V", "HtmlTagSet", "Lcom/fleeksoft/ksoup/nodes/TagSet;", "getHtmlTagSet", "()Lcom/fleeksoft/ksoup/nodes/TagSet;", "HtmlTagSet$delegate", "Lkotlin/Lazy;", "Html", "copyCustomizers", "Ljava/util/ArrayList;", "Lkotlin/Function1;", "Lcom/fleeksoft/ksoup/parser/Tag;", "", "Lkotlin/collections/ArrayList;", TtmlNode.RUBY_BASE, "initHtmlDefault", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TagSet getHtmlTagSet() {
            return (TagSet) TagSet.HtmlTagSet$delegate.getValue();
        }

        public final TagSet Html() {
            return new TagSet(getHtmlTagSet(), null);
        }

        public final ArrayList<Function1<Tag, Unit>> copyCustomizers(TagSet base) {
            Intrinsics.checkNotNullParameter(base, "base");
            if (base.customizers == null) {
                return null;
            }
            ArrayList arrayList = base.customizers;
            Intrinsics.checkNotNull(arrayList);
            return new ArrayList<>(arrayList);
        }

        public final TagSet initHtmlDefault() {
            String[] strArr = {"html", TtmlNode.TAG_HEAD, TtmlNode.TAG_BODY, "frameset", "script", "noscript", "style", "meta", "link", "title", TypedValues.AttributesType.S_FRAME, "noframes", "section", "nav", "aside", "hgroup", "header", "footer", TtmlNode.TAG_P, "h1", "h2", "h3", "h4", "h5", "h6", "button", "ul", "ol", "pre", TtmlNode.TAG_DIV, "blockquote", "hr", "address", "figure", "figcaption", "form", "fieldset", "ins", "del", CmcdConfiguration.KEY_DEADLINE, "dt", "dd", "li", "table", MediaTrack.ROLE_CAPTION, "thead", "tfoot", "tbody", "colgroup", "col", "tr", "th", "td", "video", "audio", "canvas", "details", "menu", "plaintext", "template", "article", MediaTrack.ROLE_MAIN, TtmlNode.CENTER, "template", "dir", "applet", "marquee", "listing", "#root"};
            String[] strArr2 = {"object", TtmlNode.RUBY_BASE, ContentType.Font.TYPE, TtmlNode.TAG_TT, CmcdData.OBJECT_TYPE_INIT_SEGMENT, "b", "u", "big", "small", "em", "strong", "dfn", "code", "samp", "kbd", "var", "cite", "abbr", "time", "acronym", "mark", TtmlNode.ATTR_TTS_RUBY, "rt", "rp", "rtc", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "img", "wbr", "map", "q", "sub", "sup", "bdo", "iframe", "embed", TtmlNode.TAG_SPAN, TvContractCompat.PARAM_INPUT, "select", "textarea", "label", "optgroup", "option", "legend", "datalist", "keygen", "output", "progress", "meter", "area", "param", "source", "track", "summary", "command", "device", "area", "basefont", "bgsound", "menuitem", "param", "source", "track", "data", "bdi", CmcdData.STREAMING_FORMAT_SS, "strike", "nobr", "rb"};
            String[] strArr3 = {"title", CmcdData.OBJECT_TYPE_AUDIO_ONLY, TtmlNode.TAG_P, "h1", "h2", "h3", "h4", "h5", "h6", "pre", "address", "li", "th", "td", "script", "style", "ins", "del", CmcdData.STREAMING_FORMAT_SS, "button"};
            String[] strArr4 = {"meta", "link", TtmlNode.RUBY_BASE, TypedValues.AttributesType.S_FRAME, "img", "br", "wbr", "embed", "hr", TvContractCompat.PARAM_INPUT, "keygen", "col", "command", "device", "area", "basefont", "bgsound", "menuitem", "param", "source", "track"};
            return new TagSet().setupTags("http://www.w3.org/1999/xhtml", strArr, new Function1() { // from class: com.fleeksoft.ksoup.nodes.TagSet$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TagSet.Companion.initHtmlDefault$lambda$0((Tag) obj);
                }
            }).setupTags("http://www.w3.org/1999/xhtml", strArr2, new Function1() { // from class: com.fleeksoft.ksoup.nodes.TagSet$Companion$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TagSet.Companion.initHtmlDefault$lambda$1((Tag) obj);
                }
            }).setupTags("http://www.w3.org/1999/xhtml", strArr3, new Function1() { // from class: com.fleeksoft.ksoup.nodes.TagSet$Companion$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TagSet.Companion.initHtmlDefault$lambda$2((Tag) obj);
                }
            }).setupTags("http://www.w3.org/1999/xhtml", strArr4, new Function1() { // from class: com.fleeksoft.ksoup.nodes.TagSet$Companion$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TagSet.Companion.initHtmlDefault$lambda$3((Tag) obj);
                }
            }).setupTags("http://www.w3.org/1999/xhtml", new String[]{"pre", "plaintext", "title", "textarea", "script"}, new Function1() { // from class: com.fleeksoft.ksoup.nodes.TagSet$Companion$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TagSet.Companion.initHtmlDefault$lambda$4((Tag) obj);
                }
            }).setupTags("http://www.w3.org/1999/xhtml", new String[]{"title", "textarea"}, new Function1() { // from class: com.fleeksoft.ksoup.nodes.TagSet$Companion$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TagSet.Companion.initHtmlDefault$lambda$5((Tag) obj);
                }
            }).setupTags("http://www.w3.org/1999/xhtml", new String[]{"iframe", "noembed", "noframes", "script", "style", "xmp"}, new Function1() { // from class: com.fleeksoft.ksoup.nodes.TagSet$Companion$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TagSet.Companion.initHtmlDefault$lambda$6((Tag) obj);
                }
            }).setupTags("http://www.w3.org/1999/xhtml", SharedConstants.INSTANCE.getFormSubmitTags(), new Function1() { // from class: com.fleeksoft.ksoup.nodes.TagSet$Companion$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TagSet.Companion.initHtmlDefault$lambda$7((Tag) obj);
                }
            }).setupTags("http://www.w3.org/1998/Math/MathML", new String[]{"math"}, new Function1() { // from class: com.fleeksoft.ksoup.nodes.TagSet$Companion$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TagSet.Companion.initHtmlDefault$lambda$8((Tag) obj);
                }
            }).setupTags("http://www.w3.org/1998/Math/MathML", new String[]{"mi", "mo", "msup", "mn", "mtext"}, new Function1() { // from class: com.fleeksoft.ksoup.nodes.TagSet$Companion$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TagSet.Companion.initHtmlDefault$lambda$9((Tag) obj);
                }
            }).setupTags("http://www.w3.org/2000/svg", new String[]{"svg", "femerge", "femergenode"}, new Function1() { // from class: com.fleeksoft.ksoup.nodes.TagSet$Companion$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TagSet.Companion.initHtmlDefault$lambda$10((Tag) obj);
                }
            }).setupTags("http://www.w3.org/2000/svg", new String[]{"text"}, new Function1() { // from class: com.fleeksoft.ksoup.nodes.TagSet$Companion$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TagSet.Companion.initHtmlDefault$lambda$11((Tag) obj);
                }
            }).setupTags("http://www.w3.org/2000/svg", new String[]{"script"}, new Function1() { // from class: com.fleeksoft.ksoup.nodes.TagSet$Companion$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TagSet.Companion.initHtmlDefault$lambda$12((Tag) obj);
                }
            });
        }

        static final Unit initHtmlDefault$lambda$0(Tag it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.set(4);
            return Unit.INSTANCE;
        }

        static final Unit initHtmlDefault$lambda$1(Tag it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.set(0);
            return Unit.INSTANCE;
        }

        static final Unit initHtmlDefault$lambda$2(Tag it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.set(8);
            return Unit.INSTANCE;
        }

        static final Unit initHtmlDefault$lambda$3(Tag it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.set(2);
            return Unit.INSTANCE;
        }

        static final Unit initHtmlDefault$lambda$4(Tag it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.set(64);
            return Unit.INSTANCE;
        }

        static final Unit initHtmlDefault$lambda$5(Tag it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.set(128);
            return Unit.INSTANCE;
        }

        static final Unit initHtmlDefault$lambda$6(Tag it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.set(256);
            return Unit.INSTANCE;
        }

        static final Unit initHtmlDefault$lambda$7(Tag it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.set(512);
            return Unit.INSTANCE;
        }

        static final Unit initHtmlDefault$lambda$8(Tag it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.set(4);
            return Unit.INSTANCE;
        }

        static final Unit initHtmlDefault$lambda$9(Tag it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.set(0);
            return Unit.INSTANCE;
        }

        static final Unit initHtmlDefault$lambda$10(Tag it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.set(4);
            return Unit.INSTANCE;
        }

        static final Unit initHtmlDefault$lambda$11(Tag it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.set(0);
            return Unit.INSTANCE;
        }

        static final Unit initHtmlDefault$lambda$12(Tag it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.set(256);
            return Unit.INSTANCE;
        }
    }
}
