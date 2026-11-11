package io.ktor.http.parsing;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: Debug.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a\u001d\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001f\u0010\b\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lio/ktor/http/parsing/Grammar;", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "printDebug", "(Lio/ktor/http/parsing/Grammar;I)V", "", "node", "printlnWithOffset", "(ILjava/lang/Object;)V", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DebugKt {
    public static /* synthetic */ void printDebug$default(Grammar grammar, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        printDebug(grammar, i);
    }

    public static final void printDebug(Grammar grammar, int i) {
        Intrinsics.checkNotNullParameter(grammar, "<this>");
        if (grammar instanceof StringGrammar) {
            printlnWithOffset(i, "STRING[" + Regex.INSTANCE.escape(((StringGrammar) grammar).getValue()) + AbstractJsonLexerKt.END_LIST);
            return;
        }
        if (grammar instanceof RawGrammar) {
            printlnWithOffset(i, "STRING[" + ((RawGrammar) grammar).getValue() + AbstractJsonLexerKt.END_LIST);
            return;
        }
        if (grammar instanceof NamedGrammar) {
            StringBuilder sb = new StringBuilder("NAMED[");
            NamedGrammar namedGrammar = (NamedGrammar) grammar;
            sb.append(namedGrammar.getName());
            sb.append(AbstractJsonLexerKt.END_LIST);
            printlnWithOffset(i, sb.toString());
            printDebug(namedGrammar.getGrammar(), i + 2);
            return;
        }
        if (grammar instanceof SequenceGrammar) {
            printlnWithOffset(i, "SEQUENCE");
            Iterator<T> it = ((SequenceGrammar) grammar).getGrammars().iterator();
            while (it.hasNext()) {
                printDebug((Grammar) it.next(), i + 2);
            }
            return;
        }
        if (grammar instanceof OrGrammar) {
            printlnWithOffset(i, "OR");
            Iterator<T> it2 = ((OrGrammar) grammar).getGrammars().iterator();
            while (it2.hasNext()) {
                printDebug((Grammar) it2.next(), i + 2);
            }
            return;
        }
        if (grammar instanceof MaybeGrammar) {
            printlnWithOffset(i, "MAYBE");
            printDebug(((MaybeGrammar) grammar).getGrammar(), i + 2);
            return;
        }
        if (grammar instanceof ManyGrammar) {
            printlnWithOffset(i, "MANY");
            printDebug(((ManyGrammar) grammar).getGrammar(), i + 2);
            return;
        }
        if (grammar instanceof AtLeastOne) {
            printlnWithOffset(i, "MANY_NOT_EMPTY");
            printDebug(((AtLeastOne) grammar).getGrammar(), i + 2);
            return;
        }
        if (grammar instanceof AnyOfGrammar) {
            printlnWithOffset(i, "ANY_OF[" + Regex.INSTANCE.escape(((AnyOfGrammar) grammar).getValue()) + AbstractJsonLexerKt.END_LIST);
            return;
        }
        if (!(grammar instanceof RangeGrammar)) {
            throw new NoWhenBranchMatchedException();
        }
        StringBuilder sb2 = new StringBuilder("RANGE[");
        RangeGrammar rangeGrammar = (RangeGrammar) grammar;
        sb2.append(rangeGrammar.getFrom());
        sb2.append('-');
        sb2.append(rangeGrammar.getTo());
        sb2.append(AbstractJsonLexerKt.END_LIST);
        printlnWithOffset(i, sb2.toString());
    }

    private static final void printlnWithOffset(int i, Object obj) {
        System.out.println((Object) (StringsKt.repeat(Stream.ID_UNKNOWN, i) + (i / 2) + ": " + obj));
    }
}
