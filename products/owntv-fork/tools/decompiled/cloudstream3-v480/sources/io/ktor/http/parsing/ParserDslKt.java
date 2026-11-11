package io.ktor.http.parsing;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ParserDsl.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\u001a\u0017\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0017\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0002\u0010\u0006\u001a.\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\f2\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\nH\u0000¢\u0006\u0004\b\u0002\u0010\r\u001a\u001c\u0010\u000e\u001a\u00020\u0000*\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u0000H\u0080\u0004¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u001c\u0010\u000e\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0080\u0004¢\u0006\u0004\b\u000e\u0010\u0010\u001a\u001c\u0010\u000e\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0080\u0004¢\u0006\u0004\b\u000e\u0010\u0011\u001a\u001c\u0010\u0012\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0080\u0004¢\u0006\u0004\b\u0012\u0010\u0010\u001a\u001c\u0010\u0012\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0080\u0004¢\u0006\u0004\b\u0012\u0010\u0011\u001a\u001c\u0010\u0012\u001a\u00020\u0000*\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u0000H\u0080\u0004¢\u0006\u0004\b\u0012\u0010\u000f\u001a\u0017\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0013\u0010\u0003\u001a\u0017\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0014\u0010\u0003\u001a\u001b\u0010\u0016\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0016\u0010\u0011\u001a\u0017\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0017\u0010\u0006\u001a\u001c\u0010\u001a\u001a\u00020\u0000*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0080\u0004¢\u0006\u0004\b\u001a\u0010\u001b\u001a,\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00000\u001e\"\n\b\u0000\u0010\u001d\u0018\u0001*\u00020\u001c*\b\u0012\u0004\u0012\u00020\u00000\u001eH\u0080\b¢\u0006\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lio/ktor/http/parsing/Grammar;", "grammar", "maybe", "(Lio/ktor/http/parsing/Grammar;)Lio/ktor/http/parsing/Grammar;", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/String;)Lio/ktor/http/parsing/Grammar;", "Lkotlin/Function1;", "Lio/ktor/http/parsing/GrammarBuilder;", "", "Lkotlin/ExtensionFunctionType;", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function1;)Lkotlin/jvm/functions/Function0;", "then", "(Ljava/lang/String;Lio/ktor/http/parsing/Grammar;)Lio/ktor/http/parsing/Grammar;", "(Lio/ktor/http/parsing/Grammar;Lio/ktor/http/parsing/Grammar;)Lio/ktor/http/parsing/Grammar;", "(Lio/ktor/http/parsing/Grammar;Ljava/lang/String;)Lio/ktor/http/parsing/Grammar;", "or", "many", "atLeastOne", "name", "named", "anyOf", "", "other", TypedValues.TransitionType.S_TO, "(CC)Lio/ktor/http/parsing/Grammar;", "Lio/ktor/http/parsing/ComplexGrammar;", ExifInterface.GPS_DIRECTION_TRUE, "", "flatten", "(Ljava/util/List;)Ljava/util/List;", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ParserDslKt {
    public static final Grammar maybe(Grammar grammar) {
        Intrinsics.checkNotNullParameter(grammar, "grammar");
        return new MaybeGrammar(grammar);
    }

    public static final Grammar maybe(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new MaybeGrammar(new StringGrammar(value));
    }

    public static final Function0<Grammar> maybe(final Function1<? super GrammarBuilder, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return new Function0() { // from class: io.ktor.http.parsing.ParserDslKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ParserDslKt.maybe$lambda$0(block);
            }
        };
    }

    static final Grammar maybe$lambda$0(Function1 function1) {
        GrammarBuilder grammarBuilder = new GrammarBuilder();
        function1.invoke(grammarBuilder);
        return maybe(grammarBuilder.build());
    }

    public static final Grammar then(String str, Grammar grammar) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(grammar, "grammar");
        return then(new StringGrammar(str), grammar);
    }

    public static final Grammar then(Grammar grammar, Grammar grammar2) {
        Intrinsics.checkNotNullParameter(grammar, "<this>");
        Intrinsics.checkNotNullParameter(grammar2, "grammar");
        return new SequenceGrammar(CollectionsKt.listOf((Object[]) new Grammar[]{grammar, grammar2}));
    }

    public static final Grammar then(Grammar grammar, String value) {
        Intrinsics.checkNotNullParameter(grammar, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        return then(grammar, new StringGrammar(value));
    }

    public static final Grammar or(Grammar grammar, Grammar grammar2) {
        Intrinsics.checkNotNullParameter(grammar, "<this>");
        Intrinsics.checkNotNullParameter(grammar2, "grammar");
        return new OrGrammar(CollectionsKt.listOf((Object[]) new Grammar[]{grammar, grammar2}));
    }

    public static final Grammar or(Grammar grammar, String value) {
        Intrinsics.checkNotNullParameter(grammar, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        return or(grammar, new StringGrammar(value));
    }

    public static final Grammar or(String str, Grammar grammar) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(grammar, "grammar");
        return or(new StringGrammar(str), grammar);
    }

    public static final Grammar many(Grammar grammar) {
        Intrinsics.checkNotNullParameter(grammar, "grammar");
        return new ManyGrammar(grammar);
    }

    public static final Grammar atLeastOne(Grammar grammar) {
        Intrinsics.checkNotNullParameter(grammar, "grammar");
        return new AtLeastOne(grammar);
    }

    public static final Grammar named(Grammar grammar, String name) {
        Intrinsics.checkNotNullParameter(grammar, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        return new NamedGrammar(name, grammar);
    }

    public static final Grammar anyOf(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new AnyOfGrammar(value);
    }

    public static final Grammar to(char c, char c2) {
        return new RangeGrammar(c, c2);
    }

    public static final /* synthetic */ <T extends ComplexGrammar> List<Grammar> flatten(List<? extends Grammar> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (obj instanceof ComplexGrammar) {
                CollectionsKt.addAll(arrayList, ((ComplexGrammar) obj).getGrammars());
            } else {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
