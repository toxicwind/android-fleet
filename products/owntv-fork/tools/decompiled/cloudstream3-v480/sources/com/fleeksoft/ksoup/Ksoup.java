package com.fleeksoft.ksoup;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.fleeksoft.ksoup.model.MetaData;
import com.fleeksoft.ksoup.nodes.Document;
import com.fleeksoft.ksoup.nodes.Element;
import com.fleeksoft.ksoup.parser.Parser;
import com.fleeksoft.ksoup.safety.Cleaner;
import com.fleeksoft.ksoup.safety.Safelist;
import com.google.android.gms.actions.SearchIntents;
import java.io.Reader;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Ksoup.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007J$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\b\u001a\u00020\u0007H\u0007J(\u0010\u0004\u001a\u00020\u00052\n\u0010\u000b\u001a\u00060\fj\u0002`\r2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\b\u001a\u00020\u0007H\u0007J\u0018\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007J\u0018\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007J.\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJV\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072:\b\u0002\u0010\u001d\u001a4\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020#\u0018\u00010\u001eH\u0007J=\u0010$\u001a\u00020\u001a2\u0006\u0010\b\u001a\u00020\u00072\b\u0010%\u001a\u0004\u0018\u00010\u00072#\u0010&\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b((\u0012\u0006\u0012\u0004\u0018\u00010\u001c0'¨\u0006)"}, d2 = {"Lcom/fleeksoft/ksoup/Ksoup;", "", "<init>", "()V", "parse", "Lcom/fleeksoft/ksoup/nodes/Document;", "html", "", "baseUri", "parser", "Lcom/fleeksoft/ksoup/parser/Parser;", "reader", "Ljava/io/Reader;", "Lcom/fleeksoft/io/Reader;", "parseXml", "xml", "parseBodyFragment", "bodyHtml", "clean", "safelist", "Lcom/fleeksoft/ksoup/safety/Safelist;", "outputSettings", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "isValid", "", "parseMetaData", "Lcom/fleeksoft/ksoup/model/MetaData;", "element", "Lcom/fleeksoft/ksoup/nodes/Element;", "interceptor", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", TtmlNode.TAG_HEAD, "metaData", "", "parseMetaDataInternal", "title", "selectFirst", "Lkotlin/Function1;", SearchIntents.EXTRA_QUERY, "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Ksoup {
    public static final Ksoup INSTANCE = new Ksoup();

    private Ksoup() {
    }

    public static /* synthetic */ Document parse$default(Ksoup ksoup, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return ksoup.parse(str, str2);
    }

    public final Document parse(String html, String baseUri) {
        Intrinsics.checkNotNullParameter(html, "html");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        return Parser.INSTANCE.parse(html, baseUri);
    }

    public static /* synthetic */ Document parse$default(Ksoup ksoup, String str, Parser parser, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            parser = Parser.INSTANCE.htmlParser();
        }
        if ((i & 4) != 0) {
            str2 = "";
        }
        return ksoup.parse(str, parser, str2);
    }

    public final Document parse(String html, Parser parser, String baseUri) {
        Intrinsics.checkNotNullParameter(html, "html");
        Intrinsics.checkNotNullParameter(parser, "parser");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        return parser.parseInput(html, baseUri);
    }

    public static /* synthetic */ Document parse$default(Ksoup ksoup, Reader reader, Parser parser, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            parser = Parser.INSTANCE.htmlParser();
        }
        if ((i & 4) != 0) {
            str = "";
        }
        return ksoup.parse(reader, parser, str);
    }

    public final Document parse(Reader reader, Parser parser, String baseUri) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(parser, "parser");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        return parser.parseInput(reader, baseUri);
    }

    public static /* synthetic */ Document parseXml$default(Ksoup ksoup, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return ksoup.parseXml(str, str2);
    }

    public final Document parseXml(String xml, String baseUri) {
        Intrinsics.checkNotNullParameter(xml, "xml");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        return Parser.INSTANCE.xmlParser().parseInput(xml, baseUri);
    }

    public static /* synthetic */ Document parseBodyFragment$default(Ksoup ksoup, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return ksoup.parseBodyFragment(str, str2);
    }

    public final Document parseBodyFragment(String bodyHtml, String baseUri) {
        Intrinsics.checkNotNullParameter(bodyHtml, "bodyHtml");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        return Parser.INSTANCE.parseBodyFragment(bodyHtml, baseUri);
    }

    public static /* synthetic */ String clean$default(Ksoup ksoup, String str, Safelist safelist, String str2, Document.OutputSettings outputSettings, int i, Object obj) {
        if ((i & 2) != 0) {
            safelist = Safelist.INSTANCE.relaxed();
        }
        if ((i & 4) != 0) {
            str2 = "";
        }
        if ((i & 8) != 0) {
            outputSettings = null;
        }
        return ksoup.clean(str, safelist, str2, outputSettings);
    }

    public final String clean(String bodyHtml, Safelist safelist, String baseUri, Document.OutputSettings outputSettings) {
        Intrinsics.checkNotNullParameter(bodyHtml, "bodyHtml");
        Intrinsics.checkNotNullParameter(safelist, "safelist");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        if (baseUri.length() == 0 && safelist.getPreserveRelativeLinks()) {
            baseUri = "https://dummy.example/";
        }
        Document documentClean = new Cleaner(safelist).clean(parseBodyFragment(bodyHtml, baseUri));
        if (outputSettings != null) {
            documentClean.outputSettings(outputSettings);
        }
        return documentClean.body().html();
    }

    public static /* synthetic */ boolean isValid$default(Ksoup ksoup, String str, Safelist safelist, int i, Object obj) {
        if ((i & 2) != 0) {
            safelist = Safelist.INSTANCE.relaxed();
        }
        return ksoup.isValid(str, safelist);
    }

    public final boolean isValid(String bodyHtml, Safelist safelist) {
        Intrinsics.checkNotNullParameter(bodyHtml, "bodyHtml");
        Intrinsics.checkNotNullParameter(safelist, "safelist");
        return new Cleaner(safelist).isValidBodyHtml(bodyHtml);
    }

    public final MetaData parseMetaData(Element element) {
        final Element elementHeadOrNull;
        Intrinsics.checkNotNullParameter(element, "element");
        if (!(element instanceof Document) || (elementHeadOrNull = ((Document) element).headOrNull()) == null) {
            elementHeadOrNull = element;
        }
        Element elementSelectFirst = elementHeadOrNull.selectFirst("title");
        return parseMetaDataInternal(element.baseUri(), elementSelectFirst != null ? elementSelectFirst.text() : null, new Function1() { // from class: com.fleeksoft.ksoup.Ksoup$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Ksoup.parseMetaData$lambda$0(elementHeadOrNull, (String) obj);
            }
        });
    }

    static final Element parseMetaData$lambda$0(Element element, String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return element.selectFirst(query);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MetaData parseMetaData$default(Ksoup ksoup, String str, String str2, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        if ((i & 4) != 0) {
            function2 = null;
        }
        return ksoup.parseMetaData(str, str2, function2);
    }

    public final MetaData parseMetaData(String html, String baseUri, Function2<? super Element, ? super MetaData, Unit> interceptor) {
        Intrinsics.checkNotNullParameter(html, "html");
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        Document document = parse(html, baseUri);
        final Document documentHeadOrNull = document.headOrNull();
        if (documentHeadOrNull == null) {
            documentHeadOrNull = document;
        }
        Element elementSelectFirst = documentHeadOrNull.selectFirst("title");
        MetaData metaDataInternal = parseMetaDataInternal(baseUri, elementSelectFirst != null ? elementSelectFirst.text() : null, new Function1() { // from class: com.fleeksoft.ksoup.Ksoup$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Ksoup.parseMetaData$lambda$2(documentHeadOrNull, (String) obj);
            }
        });
        if (interceptor != null) {
            interceptor.invoke(documentHeadOrNull, metaDataInternal);
        }
        return metaDataInternal;
    }

    static final Element parseMetaData$lambda$2(Element element, String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return element.selectFirst(query);
    }

    public final MetaData parseMetaDataInternal(String baseUri, String title, Function1<? super String, ? extends Element> selectFirst) {
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        Intrinsics.checkNotNullParameter(selectFirst, "selectFirst");
        Element elementInvoke = selectFirst.invoke("meta[property=og:title]");
        String strAttr = elementInvoke != null ? elementInvoke.attr("content") : null;
        Element elementInvoke2 = selectFirst.invoke("meta[property=og:site_name]");
        String strAttr2 = elementInvoke2 != null ? elementInvoke2.attr("content") : null;
        Element elementInvoke3 = selectFirst.invoke("meta[property=og:type]");
        String strAttr3 = elementInvoke3 != null ? elementInvoke3.attr("content") : null;
        Element elementInvoke4 = selectFirst.invoke("meta[property=og:locale]");
        String strAttr4 = elementInvoke4 != null ? elementInvoke4.attr("content") : null;
        Element elementInvoke5 = selectFirst.invoke("meta[property=og:description]");
        String strAttr5 = elementInvoke5 != null ? elementInvoke5.attr("content") : null;
        Element elementInvoke6 = selectFirst.invoke("meta[property=og:image]");
        String strAttr6 = elementInvoke6 != null ? elementInvoke6.attr("content") : null;
        Element elementInvoke7 = selectFirst.invoke("meta[property=og:url]");
        String strAttr7 = elementInvoke7 != null ? elementInvoke7.attr("content") : null;
        Element elementInvoke8 = selectFirst.invoke("meta[name=twitter:title]");
        String strAttr8 = elementInvoke8 != null ? elementInvoke8.attr("content") : null;
        Element elementInvoke9 = selectFirst.invoke("meta[name=twitter:card]");
        String strAttr9 = elementInvoke9 != null ? elementInvoke9.attr("content") : null;
        Element elementInvoke10 = selectFirst.invoke("meta[name=twitter:description]");
        String strAttr10 = elementInvoke10 != null ? elementInvoke10.attr("content") : null;
        Element elementInvoke11 = selectFirst.invoke("meta[name=twitter:image]");
        String strAttr11 = elementInvoke11 != null ? elementInvoke11.attr("content") : null;
        Element elementInvoke12 = selectFirst.invoke("meta[name=title]");
        String strAttr12 = elementInvoke12 != null ? elementInvoke12.attr("content") : null;
        Element elementInvoke13 = selectFirst.invoke("meta[name=description]");
        String strAttr13 = elementInvoke13 != null ? elementInvoke13.attr("content") : null;
        Element elementInvoke14 = selectFirst.invoke("meta[name=author]");
        String strAttr14 = elementInvoke14 != null ? elementInvoke14.attr("content") : null;
        Element elementInvoke15 = selectFirst.invoke("link[rel=canonical]");
        String strAttr15 = elementInvoke15 != null ? elementInvoke15.attr("href") : null;
        Element elementInvoke16 = selectFirst.invoke("link[rel~=icon]");
        String strAttr16 = elementInvoke16 != null ? elementInvoke16.attr("href") : null;
        if (strAttr16 != null && !StringsKt.startsWith(strAttr16, "http", true) && baseUri.length() > 0) {
            strAttr16 = baseUri + strAttr16;
        }
        String str = strAttr16;
        Element elementInvoke17 = selectFirst.invoke("link[rel~=shortcut icon]");
        String strAttr17 = elementInvoke17 != null ? elementInvoke17.attr("href") : null;
        if (strAttr17 != null && !StringsKt.startsWith(strAttr17, "http", true) && baseUri.length() > 0) {
            strAttr17 = baseUri + strAttr17;
        }
        return new MetaData(strAttr, strAttr2, strAttr5, strAttr6, strAttr7, strAttr3, strAttr4, strAttr9, strAttr8, strAttr10, strAttr11, strAttr12, strAttr13, strAttr15, title, strAttr14, str, strAttr17);
    }
}
