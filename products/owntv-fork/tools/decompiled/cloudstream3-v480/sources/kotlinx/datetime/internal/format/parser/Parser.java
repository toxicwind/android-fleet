package kotlinx.datetime.internal.format.parser;

import androidx.tvprovider.media.tv.TvContractCompat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.internal.format.parser.Copyable;

/* JADX INFO: compiled from: Parser.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0081@\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003:\u0001\"B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0006\u0010\u0007J^\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\t0\u00122\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t0\u0015H\u0082\b¢\u0006\u0004\b\u0016\u0010\u0017J'\u0010\u0018\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00028\u00002\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u0019\u0010\u001aJ)\u0010\u001b\u001a\u0004\u0018\u00018\u00002\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00028\u00002\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u001c\u0010\u001aJ\u0013\u0010\u001d\u001a\u00020\u00102\b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\t\u0010\u001f\u001a\u00020\rHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0004\u0092\u0001\b\u0012\u0004\u0012\u0002H\u00010\u0005¨\u0006#"}, d2 = {"Lkotlinx/datetime/internal/format/parser/Parser;", "Output", "Lkotlinx/datetime/internal/format/parser/Copyable;", "", "commands", "Lkotlinx/datetime/internal/format/parser/ParserStructure;", "constructor-impl", "(Lkotlinx/datetime/internal/format/parser/ParserStructure;)Lkotlinx/datetime/internal/format/parser/ParserStructure;", "parse", "", TvContractCompat.PARAM_INPUT, "", "startIndex", "", "initialContainer", "allowDanglingInput", "", "onError", "Lkotlin/Function1;", "Lkotlinx/datetime/internal/format/parser/ParseError;", "onSuccess", "Lkotlin/Function2;", "parse-impl", "(Lkotlinx/datetime/internal/format/parser/ParserStructure;Ljava/lang/CharSequence;ILkotlinx/datetime/internal/format/parser/Copyable;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "match", "match-impl", "(Lkotlinx/datetime/internal/format/parser/ParserStructure;Ljava/lang/CharSequence;Lkotlinx/datetime/internal/format/parser/Copyable;I)Lkotlinx/datetime/internal/format/parser/Copyable;", "matchOrNull", "matchOrNull-impl", "equals", "other", "hashCode", "toString", "", "ParserState", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
public final class Parser<Output extends Copyable<Output>> {
    private final ParserStructure<Output> commands;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Parser m10589boximpl(ParserStructure parserStructure) {
        return new Parser(parserStructure);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static <Output extends Copyable<Output>> ParserStructure<Output> m10590constructorimpl(ParserStructure<? super Output> commands) {
        Intrinsics.checkNotNullParameter(commands, "commands");
        return commands;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m10591equalsimpl(ParserStructure<? super Output> parserStructure, Object obj) {
        return (obj instanceof Parser) && Intrinsics.areEqual(parserStructure, ((Parser) obj).getCommands());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m10592equalsimpl0(ParserStructure<? super Output> parserStructure, ParserStructure<? super Output> parserStructure2) {
        return Intrinsics.areEqual(parserStructure, parserStructure2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m10593hashCodeimpl(ParserStructure<? super Output> parserStructure) {
        return parserStructure.hashCode();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m10599toStringimpl(ParserStructure<? super Output> parserStructure) {
        return "Parser(commands=" + parserStructure + ')';
    }

    public boolean equals(Object other) {
        return m10591equalsimpl(this.commands, other);
    }

    public int hashCode() {
        return m10593hashCodeimpl(this.commands);
    }

    public String toString() {
        return m10599toStringimpl(this.commands);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ ParserStructure getCommands() {
        return this.commands;
    }

    private /* synthetic */ Parser(ParserStructure parserStructure) {
        this.commands = parserStructure;
    }

    /* JADX INFO: renamed from: parse-impl, reason: not valid java name */
    private static final void m10598parseimpl(ParserStructure<? super Output> parserStructure, CharSequence charSequence, int i, Output output, boolean z, Function1<? super ParseError, Unit> function1, Function2<? super Integer, ? super Output, Unit> function2) {
        List listMutableListOf = CollectionsKt.mutableListOf(new ParserState(output, parserStructure, i));
        while (true) {
            ParserState parserState = (ParserState) CollectionsKt.removeLastOrNull(listMutableListOf);
            if (parserState != null) {
                Copyable copyable = (Copyable) ((Copyable) parserState.getOutput()).copy();
                int inputPosition = parserState.getInputPosition();
                ParserStructure parserStructure2 = parserState.getParserStructure();
                int size = parserStructure2.getOperations().size();
                int i2 = 0;
                while (true) {
                    if (i2 < size) {
                        Object objMo10577consumeFANa98k = ((ParserOperation) parserStructure2.getOperations().get(i2)).mo10577consumeFANa98k(copyable, charSequence, inputPosition);
                        if (!(objMo10577consumeFANa98k instanceof Integer)) {
                            if (objMo10577consumeFANa98k instanceof ParseError) {
                                function1.invoke((ParseError) objMo10577consumeFANa98k);
                                break;
                            } else {
                                throw new IllegalStateException(("Unexpected parse result: " + objMo10577consumeFANa98k).toString());
                            }
                        }
                        inputPosition = ((Number) objMo10577consumeFANa98k).intValue();
                        i2++;
                    } else {
                        if (parserStructure2.getFollowedBy().isEmpty()) {
                            if (z || inputPosition == charSequence.length()) {
                                function2.invoke(Integer.valueOf(inputPosition), copyable);
                                break;
                            } else {
                                function1.invoke(new ParseError(inputPosition, Parser$parse$1$3.INSTANCE));
                                break;
                            }
                        }
                        int size2 = parserStructure2.getFollowedBy().size() - 1;
                        if (size2 < 0) {
                            break;
                        }
                        while (true) {
                            int i3 = size2 - 1;
                            listMutableListOf.add(new ParserState(copyable, (ParserStructure) parserStructure2.getFollowedBy().get(size2), inputPosition));
                            if (i3 < 0) {
                                break;
                            } else {
                                size2 = i3;
                            }
                        }
                    }
                }
            } else {
                return;
            }
        }
    }

    /* JADX INFO: renamed from: match-impl$default, reason: not valid java name */
    public static /* synthetic */ Copyable m10595matchimpl$default(ParserStructure parserStructure, CharSequence charSequence, Copyable copyable, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return m10594matchimpl(parserStructure, charSequence, copyable, i);
    }

    /* JADX INFO: renamed from: match-impl, reason: not valid java name */
    public static final Output m10594matchimpl(ParserStructure<? super Output> parserStructure, CharSequence input, Output initialContainer, int i) throws ParseException {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(initialContainer, "initialContainer");
        ArrayList arrayList = new ArrayList();
        List listMutableListOf = CollectionsKt.mutableListOf(new ParserState(initialContainer, parserStructure, i));
        while (true) {
            ParserState parserState = (ParserState) CollectionsKt.removeLastOrNull(listMutableListOf);
            if (parserState != null) {
                Output output = (Output) ((Copyable) parserState.getOutput()).copy();
                int inputPosition = parserState.getInputPosition();
                ParserStructure parserStructure2 = parserState.getParserStructure();
                int size = parserStructure2.getOperations().size();
                int i2 = 0;
                while (true) {
                    if (i2 < size) {
                        Object objMo10577consumeFANa98k = ((ParserOperation) parserStructure2.getOperations().get(i2)).mo10577consumeFANa98k(output, input, inputPosition);
                        if (!(objMo10577consumeFANa98k instanceof Integer)) {
                            if (objMo10577consumeFANa98k instanceof ParseError) {
                                arrayList.add((ParseError) objMo10577consumeFANa98k);
                                break;
                            }
                            throw new IllegalStateException(("Unexpected parse result: " + objMo10577consumeFANa98k).toString());
                        }
                        inputPosition = ((Number) objMo10577consumeFANa98k).intValue();
                        i2++;
                    } else {
                        if (parserStructure2.getFollowedBy().isEmpty()) {
                            if (inputPosition != input.length()) {
                                arrayList.add(new ParseError(inputPosition, Parser$parse$1$3.INSTANCE));
                                break;
                            }
                            return output;
                        }
                        int size2 = parserStructure2.getFollowedBy().size() - 1;
                        if (size2 < 0) {
                            break;
                        }
                        while (true) {
                            int i3 = size2 - 1;
                            listMutableListOf.add(new ParserState(output, (ParserStructure) parserStructure2.getFollowedBy().get(size2), inputPosition));
                            if (i3 < 0) {
                                break;
                            }
                            size2 = i3;
                        }
                    }
                }
            } else {
                if (arrayList.size() > 1) {
                    CollectionsKt.sortWith(arrayList, new Comparator() { // from class: kotlinx.datetime.internal.format.parser.Parser$match-impl$$inlined$sortByDescending$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return ComparisonsKt.compareValues(Integer.valueOf(((ParseError) t2).getPosition()), Integer.valueOf(((ParseError) t).getPosition()));
                        }
                    });
                }
                throw new ParseException(arrayList);
            }
        }
    }

    /* JADX INFO: renamed from: matchOrNull-impl$default, reason: not valid java name */
    public static /* synthetic */ Copyable m10597matchOrNullimpl$default(ParserStructure parserStructure, CharSequence charSequence, Copyable copyable, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return m10596matchOrNullimpl(parserStructure, charSequence, copyable, i);
    }

    /* JADX INFO: compiled from: Parser.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B%\u0012\u0006\u0010\u0003\u001a\u00028\u0001\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lkotlinx/datetime/internal/format/parser/Parser$ParserState;", "Output", "", "output", "parserStructure", "Lkotlinx/datetime/internal/format/parser/ParserStructure;", "inputPosition", "", "<init>", "(Ljava/lang/Object;Lkotlinx/datetime/internal/format/parser/ParserStructure;I)V", "getOutput", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getParserStructure", "()Lkotlinx/datetime/internal/format/parser/ParserStructure;", "getInputPosition", "()I", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class ParserState<Output> {
        private final int inputPosition;
        private final Output output;
        private final ParserStructure<Output> parserStructure;

        /* JADX WARN: Multi-variable type inference failed */
        public ParserState(Output output, ParserStructure<? super Output> parserStructure, int i) {
            Intrinsics.checkNotNullParameter(parserStructure, "parserStructure");
            this.output = output;
            this.parserStructure = parserStructure;
            this.inputPosition = i;
        }

        public final Output getOutput() {
            return this.output;
        }

        public final ParserStructure<Output> getParserStructure() {
            return this.parserStructure;
        }

        public final int getInputPosition() {
            return this.inputPosition;
        }
    }

    /* JADX INFO: renamed from: matchOrNull-impl, reason: not valid java name */
    public static final Output m10596matchOrNullimpl(ParserStructure<? super Output> parserStructure, CharSequence input, Output initialContainer, int i) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(initialContainer, "initialContainer");
        List listMutableListOf = CollectionsKt.mutableListOf(new ParserState(initialContainer, parserStructure, i));
        while (true) {
            ParserState parserState = (ParserState) CollectionsKt.removeLastOrNull(listMutableListOf);
            if (parserState != null) {
                Output output = (Output) ((Copyable) parserState.getOutput()).copy();
                int inputPosition = parserState.getInputPosition();
                ParserStructure parserStructure2 = parserState.getParserStructure();
                int size = parserStructure2.getOperations().size();
                int i2 = 0;
                while (true) {
                    if (i2 < size) {
                        Object objMo10577consumeFANa98k = ((ParserOperation) parserStructure2.getOperations().get(i2)).mo10577consumeFANa98k(output, input, inputPosition);
                        if (!(objMo10577consumeFANa98k instanceof Integer)) {
                            if (objMo10577consumeFANa98k instanceof ParseError) {
                                break;
                            }
                            throw new IllegalStateException(("Unexpected parse result: " + objMo10577consumeFANa98k).toString());
                        }
                        inputPosition = ((Number) objMo10577consumeFANa98k).intValue();
                        i2++;
                    } else {
                        if (parserStructure2.getFollowedBy().isEmpty()) {
                            if (inputPosition != input.length()) {
                                new ParseError(inputPosition, Parser$parse$1$3.INSTANCE);
                                break;
                            }
                            return output;
                        }
                        int size2 = parserStructure2.getFollowedBy().size() - 1;
                        if (size2 < 0) {
                            break;
                        }
                        while (true) {
                            int i3 = size2 - 1;
                            listMutableListOf.add(new ParserState(output, (ParserStructure) parserStructure2.getFollowedBy().get(size2), inputPosition));
                            if (i3 < 0) {
                                break;
                            }
                            size2 = i3;
                        }
                    }
                }
            } else {
                return null;
            }
        }
    }
}
