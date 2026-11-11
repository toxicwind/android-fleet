package org.jsoup.parser;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentType;
import j$.util.Objects;
import j$.util.function.Consumer$CC;
import j$.util.function.Function$CC;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import org.jsoup.helper.Validate;
import org.jsoup.internal.SharedConstants;

/* JADX INFO: loaded from: classes8.dex */
public class TagSet {
    static final TagSet HtmlTagSet = initHtmlDefault();
    private ArrayList<Consumer<Tag>> customizers;
    private final TagSet source;
    private final Map<String, Map<String, Tag>> tags;

    public static TagSet Html() {
        return new TagSet(HtmlTagSet, null);
    }

    private TagSet(TagSet tagSet, ArrayList<Consumer<Tag>> arrayList) {
        this.tags = new HashMap();
        this.source = tagSet;
        this.customizers = arrayList;
    }

    public TagSet() {
        this(null, null);
    }

    public TagSet(TagSet tagSet) {
        this(tagSet.source, copyCustomizers(tagSet));
        if (tagSet.tags.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Map<String, Tag>> entry : tagSet.tags.entrySet()) {
            HashMap map = new HashMap(entry.getValue().size());
            for (Map.Entry<String, Tag> entry2 : entry.getValue().entrySet()) {
                map.put(entry2.getKey(), entry2.getValue().clone());
            }
            this.tags.put(entry.getKey(), map);
        }
    }

    private static ArrayList<Consumer<Tag>> copyCustomizers(TagSet tagSet) {
        if (tagSet.customizers == null) {
            return null;
        }
        return new ArrayList<>(tagSet.customizers);
    }

    public TagSet add(Tag tag) {
        tag.set(Tag.Known);
        doAdd(tag);
        return this;
    }

    private void doAdd(Tag tag) {
        ArrayList<Consumer<Tag>> arrayList = this.customizers;
        if (arrayList != null) {
            Iterator<Consumer<Tag>> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().accept(tag);
            }
        }
        ((Map) j$.util.Map.EL.computeIfAbsent(this.tags, tag.namespace, new Function() { // from class: org.jsoup.parser.TagSet$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TagSet.lambda$doAdd$0((String) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        })).put(tag.tagName, tag);
    }

    static /* synthetic */ Map lambda$doAdd$0(String str) {
        return new HashMap();
    }

    public Tag get(String str, String str2) {
        Tag tag;
        Tag tag2;
        Validate.notNull(str);
        Validate.notNull(str2);
        Map<String, Tag> map = this.tags.get(str2);
        if (map != null && (tag2 = map.get(str)) != null) {
            return tag2;
        }
        TagSet tagSet = this.source;
        if (tagSet == null || (tag = tagSet.get(str, str2)) == null) {
            return null;
        }
        Tag tagM10842clone = tag.clone();
        doAdd(tagM10842clone);
        return tagM10842clone;
    }

    Tag valueOf(String str, String str2, String str3, boolean z) {
        Validate.notNull(str);
        Validate.notNull(str3);
        String strTrim = str.trim();
        Validate.notEmpty(strTrim);
        Tag tag = get(strTrim, str3);
        if (tag != null) {
            return tag;
        }
        if (str2 == null) {
            str2 = ParseSettings.normalName(strTrim);
        }
        if (!z) {
            strTrim = str2;
        }
        Tag tag2 = get(str2, str3);
        if (tag2 != null) {
            if (!z || strTrim.equals(str2)) {
                return tag2;
            }
            Tag tagM10842clone = tag2.clone();
            tagM10842clone.tagName = strTrim;
            doAdd(tagM10842clone);
            return tagM10842clone;
        }
        Tag tag3 = new Tag(strTrim, str2, str3);
        doAdd(tag3);
        return tag3;
    }

    public Tag valueOf(String str, String str2, ParseSettings parseSettings) {
        return valueOf(str, null, str2, parseSettings.preserveTagCase());
    }

    public Tag valueOf(String str, String str2) {
        return valueOf(str, str2, ParseSettings.preserveCase);
    }

    public TagSet onNewTag(Consumer<Tag> consumer) {
        Validate.notNull(consumer);
        if (this.customizers == null) {
            this.customizers = new ArrayList<>();
        }
        this.customizers.add(consumer);
        return this;
    }

    public boolean equals(Object obj) {
        if (obj instanceof TagSet) {
            return Objects.equals(this.tags, ((TagSet) obj).tags);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.tags);
    }

    static TagSet initHtmlDefault() {
        String[] strArr = {"html", TtmlNode.TAG_HEAD, TtmlNode.TAG_BODY, "frameset", "script", "noscript", "style", "meta", "link", "title", TypedValues.AttributesType.S_FRAME, "noframes", "section", "nav", "aside", "hgroup", "header", "footer", TtmlNode.TAG_P, "h1", "h2", "h3", "h4", "h5", "h6", "dialog", "search", "ul", "ol", "pre", TtmlNode.TAG_DIV, "blockquote", "hr", "address", "figure", "figcaption", "form", "fieldset", CmcdConfiguration.KEY_DEADLINE, "dt", "dd", "li", "table", MediaTrack.ROLE_CAPTION, "thead", "tfoot", "tbody", "colgroup", "col", "tr", "th", "td", "details", "menu", "plaintext", "template", "article", MediaTrack.ROLE_MAIN, TtmlNode.CENTER, "dir", "applet", "marquee", "listing", "#root"};
        String[] strArr2 = {"object", TtmlNode.RUBY_BASE, ContentType.Font.TYPE, TtmlNode.TAG_TT, CmcdData.OBJECT_TYPE_INIT_SEGMENT, "b", "u", "big", "small", "em", "strong", "dfn", "code", "samp", "kbd", "var", "cite", "abbr", "time", "acronym", "mark", TtmlNode.ATTR_TTS_RUBY, "rt", "rp", "rtc", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "img", "wbr", "map", "q", "sub", "sup", "bdo", "iframe", "embed", TtmlNode.TAG_SPAN, TvContractCompat.PARAM_INPUT, "select", "textarea", "label", "audio", "video", "canvas", "optgroup", "option", "legend", "datalist", "keygen", "output", "progress", "meter", "area", "param", "source", "track", "summary", "command", "device", "basefont", "bgsound", "menuitem", "data", "bdi", CmcdData.STREAMING_FORMAT_SS, "strike", "nobr", "ins", "del", "button", "picture", "slot", "rb"};
        String[] strArr3 = {"title", TtmlNode.TAG_P, "h1", "h2", "h3", "h4", "h5", "h6", "pre", "address", "li", "th", "td", "script", "style"};
        String[] strArr4 = {"meta", "link", TtmlNode.RUBY_BASE, TypedValues.AttributesType.S_FRAME, "img", "br", "wbr", "embed", "hr", TvContractCompat.PARAM_INPUT, "keygen", "col", "command", "device", "area", "basefont", "bgsound", "menuitem", "param", "source", "track"};
        String[] strArr5 = SharedConstants.FormSubmitTags;
        return new TagSet().setupTags("http://www.w3.org/1999/xhtml", strArr, new Consumer() { // from class: org.jsoup.parser.TagSet$$ExternalSyntheticLambda6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Tag) obj).set(Tag.Block);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        }).setupTags("http://www.w3.org/1999/xhtml", strArr2, new Consumer() { // from class: org.jsoup.parser.TagSet$$ExternalSyntheticLambda11
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Tag) obj).set(0);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        }).setupTags("http://www.w3.org/1999/xhtml", strArr3, new Consumer() { // from class: org.jsoup.parser.TagSet$$ExternalSyntheticLambda12
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Tag) obj).set(Tag.InlineContainer);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        }).setupTags("http://www.w3.org/1999/xhtml", strArr4, new Consumer() { // from class: org.jsoup.parser.TagSet$$ExternalSyntheticLambda13
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Tag) obj).set(Tag.Void);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        }).setupTags("http://www.w3.org/1999/xhtml", new String[]{"pre", "plaintext", "title", "textarea", "script"}, new Consumer() { // from class: org.jsoup.parser.TagSet$$ExternalSyntheticLambda14
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Tag) obj).set(Tag.PreserveWhitespace);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        }).setupTags("http://www.w3.org/1999/xhtml", new String[]{"title", "textarea"}, new Consumer() { // from class: org.jsoup.parser.TagSet$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Tag) obj).set(Tag.RcData);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        }).setupTags("http://www.w3.org/1999/xhtml", new String[]{"iframe", "noembed", "noframes", "script", "style", "xmp"}, new Consumer() { // from class: org.jsoup.parser.TagSet$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Tag) obj).set(Tag.Data);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        }).setupTags("http://www.w3.org/1999/xhtml", strArr5, new Consumer() { // from class: org.jsoup.parser.TagSet$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Tag) obj).set(Tag.FormSubmittable);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        }).setupTags("http://www.w3.org/1999/xhtml", new String[]{"button", TvContractCompat.PARAM_INPUT, "select", "textarea", "option", "output", "progress", "meter", "img", "picture", "audio", "video", "canvas", "object", "embed", "iframe"}, new Consumer() { // from class: org.jsoup.parser.TagSet$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Tag) obj).set(Tag.TextBoundary);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        }).setupTags("http://www.w3.org/1998/Math/MathML", new String[]{"math"}, new Consumer() { // from class: org.jsoup.parser.TagSet$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Tag) obj).set(Tag.Block);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        }).setupTags("http://www.w3.org/1998/Math/MathML", new String[]{"mi", "mo", "msup", "mn", "mtext"}, new Consumer() { // from class: org.jsoup.parser.TagSet$$ExternalSyntheticLambda7
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Tag) obj).set(0);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        }).setupTags("http://www.w3.org/2000/svg", new String[]{"svg", "femerge", "femergenode"}, new Consumer() { // from class: org.jsoup.parser.TagSet$$ExternalSyntheticLambda8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Tag) obj).set(Tag.Block);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        }).setupTags("http://www.w3.org/2000/svg", new String[]{"text"}, new Consumer() { // from class: org.jsoup.parser.TagSet$$ExternalSyntheticLambda9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Tag) obj).set(0);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        }).setupTags("http://www.w3.org/2000/svg", new String[]{"script"}, new Consumer() { // from class: org.jsoup.parser.TagSet$$ExternalSyntheticLambda10
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Tag) obj).set(Tag.Data);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
    }

    private TagSet setupTags(String str, String[] strArr, Consumer<Tag> consumer) {
        for (String str2 : strArr) {
            Tag tag = get(str2, str);
            if (tag == null) {
                tag = new Tag(str2, str2, str);
                tag.options = 0;
                add(tag);
            }
            consumer.accept(tag);
        }
        return this;
    }
}
