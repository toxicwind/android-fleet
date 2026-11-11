package kotlinx.datetime.internal.format.parser;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: Parser.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0003H\u0000\u001a\u0016\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003H\u0002¨\u0006\b"}, d2 = {"concat", "Lkotlinx/datetime/internal/format/parser/ParserStructure;", ExifInterface.GPS_DIRECTION_TRUE, "", "formatError", "", "errors", "Lkotlinx/datetime/internal/format/parser/ParseError;", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ParserKt {
    private static final <T> ParserStructure<T> concat$mergeOperations(List<? extends ParserOperation<? super T>> list, List<? extends NumberConsumer<? super T>> list2, List<UnconditionalModification<T>> list3, ParserStructure<? super T> parserStructure) {
        List<ParserOperation<? super T>> operations = parserStructure.getOperations();
        ParserOperation parserOperation = (ParserOperation) CollectionsKt.firstOrNull((List) operations);
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.addAll(list);
        if (list2 == null) {
            listCreateListBuilder.addAll(operations);
        } else if (parserOperation instanceof NumberSpanParserOperation) {
            listCreateListBuilder.add(new NumberSpanParserOperation(CollectionsKt.plus((Collection) list2, (Iterable) ((NumberSpanParserOperation) parserOperation).getConsumers())));
            int lastIndex = CollectionsKt.getLastIndex(operations);
            int i = 1;
            if (1 <= lastIndex) {
                while (true) {
                    listCreateListBuilder.add(operations.get(i));
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
        } else {
            listCreateListBuilder.add(new NumberSpanParserOperation(list2));
            listCreateListBuilder.addAll(operations);
        }
        listCreateListBuilder.addAll(list3);
        return new ParserStructure<>(CollectionsKt.build(listCreateListBuilder), parserStructure.getFollowedBy());
    }

    private static final <T> ParserStructure<T> concat$simplifyAndAppend(ParserStructure<? super T> parserStructure, ParserStructure<? super T> parserStructure2) {
        List listListOf;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        List mutableList = null;
        for (ParserOperation<? super T> parserOperation : parserStructure.getOperations()) {
            if (parserOperation instanceof NumberSpanParserOperation) {
                if (mutableList != null) {
                    mutableList.addAll(((NumberSpanParserOperation) parserOperation).getConsumers());
                } else {
                    mutableList = CollectionsKt.toMutableList((Collection) ((NumberSpanParserOperation) parserOperation).getConsumers());
                }
            } else if (parserOperation instanceof UnconditionalModification) {
                arrayList2.add(parserOperation);
            } else {
                if (mutableList != null) {
                    arrayList.add(new NumberSpanParserOperation(mutableList));
                    arrayList.addAll(arrayList2);
                    arrayList2.clear();
                    mutableList = null;
                }
                arrayList.add(parserOperation);
            }
        }
        List<ParserStructure<? super T>> followedBy = parserStructure.getFollowedBy();
        ArrayList arrayList3 = new ArrayList();
        Iterator<T> it = followedBy.iterator();
        while (it.hasNext()) {
            ParserStructure parserStructureConcat$simplifyAndAppend = concat$simplifyAndAppend((ParserStructure) it.next(), parserStructure2);
            if (parserStructureConcat$simplifyAndAppend.getOperations().isEmpty()) {
                List followedBy2 = parserStructureConcat$simplifyAndAppend.getFollowedBy();
                if (followedBy2.isEmpty()) {
                    followedBy2 = CollectionsKt.listOf(parserStructureConcat$simplifyAndAppend);
                }
                listListOf = followedBy2;
            } else {
                listListOf = CollectionsKt.listOf(parserStructureConcat$simplifyAndAppend);
            }
            CollectionsKt.addAll(arrayList3, listListOf);
        }
        ArrayList followedBy3 = arrayList3;
        if (followedBy3.isEmpty()) {
            if (!parserStructure2.getOperations().isEmpty()) {
                return concat$mergeOperations(arrayList, mutableList, arrayList2, parserStructure2);
            }
            followedBy3 = parserStructure2.getFollowedBy();
        }
        List list = followedBy3;
        if (mutableList != null || arrayList.isEmpty()) {
            List list2 = list;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                Iterator<T> it2 = list2.iterator();
                while (it2.hasNext()) {
                    if (CollectionsKt.firstOrNull(((ParserStructure) it2.next()).getOperations()) instanceof NumberSpanParserOperation) {
                        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                        Iterator<T> it3 = list2.iterator();
                        while (it3.hasNext()) {
                            arrayList4.add(concat$mergeOperations(CollectionsKt.emptyList(), mutableList, arrayList2, (ParserStructure) it3.next()));
                        }
                        return new ParserStructure<>(arrayList, arrayList4);
                    }
                }
            }
        }
        if (mutableList != null) {
            arrayList.add(new NumberSpanParserOperation(mutableList));
        }
        arrayList.addAll(arrayList2);
        return new ParserStructure<>(arrayList, list);
    }

    public static final <T> ParserStructure<T> concat(List<? extends ParserStructure<? super T>> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (T) new ParserStructure(CollectionsKt.emptyList(), CollectionsKt.emptyList());
        ArrayList arrayList = new ArrayList();
        for (ParserStructure parserStructure : CollectionsKt.asReversed(list)) {
            if (parserStructure.getFollowedBy().isEmpty()) {
                arrayList.add(parserStructure.getOperations());
            } else {
                concat$flushAccumulatedOperations(arrayList, objectRef);
                objectRef.element = (T) concat$simplifyAndAppend(parserStructure, (ParserStructure) objectRef.element);
            }
        }
        concat$flushAccumulatedOperations(arrayList, objectRef);
        return (ParserStructure) objectRef.element;
    }

    private static final <T> void concat$flushAccumulatedOperations(List<List<ParserOperation<T>>> list, Ref.ObjectRef<ParserStructure<T>> objectRef) {
        if (list.isEmpty()) {
            return;
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        Iterator it = CollectionsKt.asReversedMutable(list).iterator();
        while (it.hasNext()) {
            listCreateListBuilder.addAll((List) it.next());
        }
        objectRef.element = (T) concat$simplifyAndAppend(new ParserStructure(CollectionsKt.build(listCreateListBuilder), CollectionsKt.emptyList()), objectRef.element);
        list.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String formatError(List<ParseError> list) {
        if (list.size() == 1) {
            return "Position " + list.get(0).getPosition() + ": " + list.get(0).getMessage().invoke();
        }
        String string = ((StringBuilder) CollectionsKt.joinTo(list, new StringBuilder(list.size() * 33), (60 & 2) != 0 ? ", " : ", ", (60 & 4) != 0 ? "" : "Errors: ", (60 & 8) != 0 ? "" : null, (60 & 16) != 0 ? -1 : 0, (60 & 32) != 0 ? "..." : null, (60 & 64) != 0 ? null : new Function1() { // from class: kotlinx.datetime.internal.format.parser.ParserKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ParserKt.formatError$lambda$7((ParseError) obj);
            }
        })).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    static final CharSequence formatError$lambda$7(ParseError it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "position " + it.getPosition() + ": '" + it.getMessage().invoke() + '\'';
    }
}
