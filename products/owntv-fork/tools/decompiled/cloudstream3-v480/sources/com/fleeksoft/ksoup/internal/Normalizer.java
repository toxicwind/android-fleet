package com.fleeksoft.ksoup.internal;

import androidx.tvprovider.media.tv.TvContractCompat;
import com.fleeksoft.ksoup.nodes.Attribute;
import com.fleeksoft.ksoup.nodes.Document;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Normalizer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u0007\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\u0018\u0010\u0007\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u0005¨\u0006\f"}, d2 = {"Lcom/fleeksoft/ksoup/internal/Normalizer;", "", "<init>", "()V", "lowerCase", "", TvContractCompat.PARAM_INPUT, "normalize", "isStringLiteral", "", "xmlSafeTagName", "tagname", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Normalizer {
    public static final Normalizer INSTANCE = new Normalizer();

    private Normalizer() {
    }

    public final String lowerCase(String input) {
        if (input == null) {
            return "";
        }
        String lowerCase = input.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase == null ? "" : lowerCase;
    }

    public final String normalize(String input) {
        String strLowerCase = lowerCase(input);
        int length = strLowerCase.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) strLowerCase.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        return strLowerCase.subSequence(i, length + 1).toString();
    }

    public final String normalize(String input, boolean isStringLiteral) {
        return isStringLiteral ? lowerCase(input) : normalize(input);
    }

    public final String xmlSafeTagName(String tagname) {
        Intrinsics.checkNotNullParameter(tagname, "tagname");
        return Attribute.INSTANCE.getValidKey(tagname, Document.OutputSettings.Syntax.xml);
    }
}
