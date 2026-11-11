package org.schabi.newpipe.extractor.services.youtube;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.grack.nanojson.JsonObject;
import j$.util.Comparator;
import j$.util.function.Consumer$CC;
import j$.util.function.Function$CC;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.text.Typography;
import org.jsoup.nodes.Entities;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class YoutubeDescriptionHelper {
    private static final String BOLD_CLOSE = "</b>";
    private static final String BOLD_OPEN = "<b>";
    private static final String ITALIC_CLOSE = "</i>";
    private static final String ITALIC_OPEN = "<i>";
    private static final String LINK_CLOSE = "</a>";
    private static final Pattern LINK_CONTENT_CLEANER_REGEX = Pattern.compile("(?s)^ +[/•] +(.*?) +$");
    private static final String STRIKETHROUGH_CLOSE = "</s>";
    private static final String STRIKETHROUGH_OPEN = "<s>";

    static /* synthetic */ String lambda$getTransformContentFun$4(String str, String str2) {
        return str;
    }

    private YoutubeDescriptionHelper() {
    }

    static final class Run {

        @Nonnull
        final String close;

        @Nonnull
        final String open;
        int openPosInOutput;
        final int pos;

        @Nullable
        final Function<String, String> transformContent;

        Run(@Nonnull String str, @Nonnull String str2, int i) {
            this(str, str2, i, null);
        }

        Run(@Nonnull String str, @Nonnull String str2, int i, @Nullable Function<String, String> function) {
            this.openPosInOutput = -1;
            this.open = str;
            this.close = str2;
            this.pos = i;
            this.transformContent = function;
        }

        public boolean sameOpen(@Nonnull Run run) {
            return this.open.equals(run.open);
        }
    }

    @Nullable
    public static String attributedDescriptionToHtml(@Nullable JsonObject jsonObject) {
        String string;
        if (Utils.isNullOrEmpty(jsonObject) || (string = jsonObject.getString("content")) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        addAllCommandRuns(jsonObject, arrayList, arrayList2);
        addAllStyleRuns(jsonObject, arrayList, arrayList2);
        Collections.sort(arrayList, Comparator.CC.comparingInt(new ToIntFunction() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeDescriptionHelper$$ExternalSyntheticLambda4
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((YoutubeDescriptionHelper.Run) obj).pos;
            }
        }));
        Collections.sort(arrayList2, Comparator.CC.comparingInt(new ToIntFunction() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeDescriptionHelper$$ExternalSyntheticLambda5
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((YoutubeDescriptionHelper.Run) obj).pos;
            }
        }));
        return runsToHtml(arrayList, arrayList2, string);
    }

    static String runsToHtml(@Nonnull List<Run> list, @Nonnull List<Run> list2, @Nonnull String str) {
        int iMin;
        String strReplace = str.replace(Typography.nbsp, ' ');
        Stack stack = new Stack();
        Stack stack2 = new Stack();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < list2.size()) {
            if (i3 < list.size()) {
                iMin = Math.min(list2.get(i).pos, list.get(i3).pos);
            } else {
                iMin = list2.get(i).pos;
            }
            sb.append(Entities.escape(strReplace.substring(i2, iMin)));
            if (list2.get(i).pos == iMin) {
                Run run = list2.get(i);
                i++;
                while (!stack.empty()) {
                    Run run2 = (Run) stack.pop();
                    if (run2.sameOpen(run)) {
                        if (run2.transformContent != null && run2.openPosInOutput >= 0) {
                            sb.replace(run2.openPosInOutput, sb.length(), run2.transformContent.apply(sb.substring(run2.openPosInOutput)));
                        }
                        sb.append(run2.close);
                        break;
                    }
                    sb.append(run2.close);
                    stack2.push(run2);
                }
                while (!stack2.empty()) {
                    Run run3 = (Run) stack2.pop();
                    sb.append(run3.open);
                    stack.push(run3);
                }
            } else {
                Run run4 = list.get(i3);
                sb.append(run4.open);
                run4.openPosInOutput = sb.length();
                stack.push(run4);
                i3++;
            }
            i2 = iMin;
        }
        sb.append(Entities.escape(strReplace.substring(i2)));
        return sb.toString().replace("\n", "<br>").replace("  ", " &nbsp;");
    }

    private static void addAllCommandRuns(@Nonnull JsonObject jsonObject, @Nonnull final List<Run> list, @Nonnull final List<Run> list2) {
        jsonObject.getArray("commandRuns").streamAsJsonObjects().forEach(new Consumer() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeDescriptionHelper$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                YoutubeDescriptionHelper.lambda$addAllCommandRuns$2(list, list2, (JsonObject) obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
    }

    static /* synthetic */ void lambda$addAllCommandRuns$2(List list, List list2, JsonObject jsonObject) {
        String urlFromNavigationEndpoint;
        JsonObject object = jsonObject.getObject("onTap").getObject("innertubeCommand");
        int i = jsonObject.getInt("startIndex", -1);
        boolean z = false;
        int i2 = jsonObject.getInt("length", 0);
        if (i < 0 || i2 < 1 || object == null || (urlFromNavigationEndpoint = YoutubeParsingHelper.getUrlFromNavigationEndpoint(object)) == null) {
            return;
        }
        try {
            URL url = new URL(urlFromNavigationEndpoint);
            if (YoutubeParsingHelper.isYoutubeURL(url) || YoutubeParsingHelper.isYoutubeServiceURL(url)) {
                z = true;
            }
        } catch (MalformedURLException unused) {
        }
        String str = "<a href=\"" + Entities.escape(urlFromNavigationEndpoint) + "\">";
        Function<String, String> transformContentFun = getTransformContentFun(jsonObject, z);
        list.add(new Run(str, LINK_CLOSE, i, transformContentFun));
        list2.add(new Run(str, LINK_CLOSE, i + i2, transformContentFun));
    }

    private static Function<String, String> getTransformContentFun(JsonObject jsonObject, boolean z) {
        final String strReplaceFirst = jsonObject.getObject("onTapOptions").getObject("accessibilityInfo").getString("accessibilityLabel", "").replaceFirst(" Channel Link", "");
        if (z || strReplaceFirst.isEmpty() || strReplaceFirst.startsWith("YouTube: ")) {
            return new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeDescriptionHelper$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return YoutubeDescriptionHelper.lambda$getTransformContentFun$3((String) obj);
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            };
        }
        return new Function() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeDescriptionHelper$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return YoutubeDescriptionHelper.lambda$getTransformContentFun$4(strReplaceFirst, (String) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        };
    }

    static /* synthetic */ String lambda$getTransformContentFun$3(String str) {
        Matcher matcher = LINK_CONTENT_CLEANER_REGEX.matcher(str);
        return matcher.find() ? matcher.group(1) : str;
    }

    private static void addAllStyleRuns(@Nonnull JsonObject jsonObject, @Nonnull final List<Run> list, @Nonnull final List<Run> list2) {
        jsonObject.getArray("styleRuns").streamAsJsonObjects().forEach(new Consumer() { // from class: org.schabi.newpipe.extractor.services.youtube.YoutubeDescriptionHelper$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                YoutubeDescriptionHelper.lambda$addAllStyleRuns$5(list, list2, (JsonObject) obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
    }

    static /* synthetic */ void lambda$addAllStyleRuns$5(List list, List list2, JsonObject jsonObject) {
        int i = jsonObject.getInt("startIndex", -1);
        int i2 = jsonObject.getInt("length", 0);
        if (i < 0 || i2 < 1) {
            return;
        }
        int i3 = i2 + i;
        if (jsonObject.has("strikethrough")) {
            list.add(new Run(STRIKETHROUGH_OPEN, STRIKETHROUGH_CLOSE, i));
            list2.add(new Run(STRIKETHROUGH_OPEN, STRIKETHROUGH_CLOSE, i3));
        }
        if (jsonObject.getBoolean(TtmlNode.ITALIC, false)) {
            list.add(new Run(ITALIC_OPEN, ITALIC_CLOSE, i));
            list2.add(new Run(ITALIC_OPEN, ITALIC_CLOSE, i3));
        }
        if (!jsonObject.has("weightLabel") || "FONT_WEIGHT_NORMAL".equals(jsonObject.getString("weightLabel"))) {
            return;
        }
        list.add(new Run(BOLD_OPEN, BOLD_CLOSE, i));
        list2.add(new Run(BOLD_OPEN, BOLD_CLOSE, i3));
    }
}
