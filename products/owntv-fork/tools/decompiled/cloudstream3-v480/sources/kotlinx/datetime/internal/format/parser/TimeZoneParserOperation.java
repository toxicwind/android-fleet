package kotlinx.datetime.internal.format.parser;

import androidx.tvprovider.media.tv.TvContractCompat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.datetime.internal.UtilKt;

/* JADX INFO: compiled from: ParserOperation.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u0000 \u0011*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u0011B\u001b\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlinx/datetime/internal/format/parser/TimeZoneParserOperation;", "Output", "Lkotlinx/datetime/internal/format/parser/ParserOperation;", "setter", "Lkotlinx/datetime/internal/format/parser/AssignableField;", "", "<init>", "(Lkotlinx/datetime/internal/format/parser/AssignableField;)V", "consume", "Lkotlinx/datetime/internal/format/parser/ParseResult;", "storage", TvContractCompat.PARAM_INPUT, "", "startIndex", "", "consume-FANa98k", "(Ljava/lang/Object;Ljava/lang/CharSequence;I)Ljava/lang/Object;", "Companion", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TimeZoneParserOperation<Output> implements ParserOperation<Output> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final AssignableField<Output, String> setter;

    /* JADX WARN: Multi-variable type inference failed */
    public TimeZoneParserOperation(AssignableField<? super Output, String> setter) {
        Intrinsics.checkNotNullParameter(setter, "setter");
        this.setter = setter;
    }

    @Override // kotlinx.datetime.internal.format.parser.ParserOperation
    /* JADX INFO: renamed from: consume-FANa98k */
    public Object mo10577consumeFANa98k(Output storage, CharSequence input, int startIndex) {
        Intrinsics.checkNotNullParameter(input, "input");
        int iValidateTimeZone = INSTANCE.validateTimeZone(input, startIndex);
        if (iValidateTimeZone > startIndex) {
            ParserOperationKt.setWithoutReassigning(this.setter, storage, input.subSequence(startIndex, iValidateTimeZone).toString(), startIndex, iValidateTimeZone);
            return ParseResult.INSTANCE.m10588OkQi1bsqg(iValidateTimeZone);
        }
        return ParseResult.INSTANCE.m10587ErrorRg3Co2E(startIndex, new Function0() { // from class: kotlinx.datetime.internal.format.parser.TimeZoneParserOperation$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TimeZoneParserOperation.consume_FANa98k$lambda$0();
            }
        });
    }

    static final String consume_FANa98k$lambda$0() {
        return "Invalid timezone format";
    }

    /* JADX INFO: compiled from: ParserOperation.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u000fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0082\bJ\u001b\u0010\t\u001a\u00020\u0005*\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0082\bJ\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bH\u0002¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/internal/format/parser/TimeZoneParserOperation$Companion;", "", "<init>", "()V", "onTrue", "", "action", "Lkotlin/Function0;", "", "onFalse", "validateTimeZone", "", TvContractCompat.PARAM_INPUT, "", "startIndex", "State", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: ParserOperation.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000e\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lkotlinx/datetime/internal/format/parser/TimeZoneParserOperation$Companion$State;", "", "<init>", "(Ljava/lang/String;I)V", "START", "AFTER_PREFIX", "AFTER_SIGN", "AFTER_INIT_SIGN", "AFTER_HOUR", "AFTER_INIT_HOUR", "AFTER_MINUTE", "AFTER_COLON_MINUTE", "IN_PART", "AFTER_SLASH", "END", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
        private enum State {
            START,
            AFTER_PREFIX,
            AFTER_SIGN,
            AFTER_INIT_SIGN,
            AFTER_HOUR,
            AFTER_INIT_HOUR,
            AFTER_MINUTE,
            AFTER_COLON_MINUTE,
            IN_PART,
            AFTER_SLASH,
            END;

            private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

            public static EnumEntries<State> getEntries() {
                return $ENTRIES;
            }
        }

        /* JADX INFO: compiled from: ParserOperation.kt */
        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[State.values().length];
                try {
                    iArr[State.START.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[State.AFTER_PREFIX.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[State.AFTER_SIGN.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[State.AFTER_INIT_SIGN.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[State.AFTER_HOUR.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[State.AFTER_INIT_HOUR.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[State.AFTER_MINUTE.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                try {
                    iArr[State.AFTER_COLON_MINUTE.ordinal()] = 8;
                } catch (NoSuchFieldError unused8) {
                }
                try {
                    iArr[State.IN_PART.ordinal()] = 9;
                } catch (NoSuchFieldError unused9) {
                }
                try {
                    iArr[State.AFTER_SLASH.ordinal()] = 10;
                } catch (NoSuchFieldError unused10) {
                }
                try {
                    iArr[State.END.ordinal()] = 11;
                } catch (NoSuchFieldError unused11) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final boolean onTrue(boolean z, Function0<Unit> function0) {
            if (!z) {
                return false;
            }
            function0.invoke();
            return true;
        }

        private final boolean onFalse(boolean z, Function0<Unit> function0) {
            if (z) {
                return true;
            }
            function0.invoke();
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int validateTimeZone(CharSequence input, int startIndex) {
            int i;
            Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = startIndex;
            State state = State.START;
            while (true) {
                i = 1;
                if (intRef.element < input.length()) {
                    switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
                        case 1:
                            if (validateTimeZone$validatePrefix(input, intRef, CollectionsKt.listOf((Object[]) new String[]{"UTC", "GMT", "UT"}))) {
                                state = State.AFTER_PREFIX;
                            } else if (validateTimeZone$validateSign(input, intRef)) {
                                state = State.AFTER_INIT_SIGN;
                            } else if (validateTimeZone$validateTimeZoneInitial(input, intRef)) {
                                state = State.IN_PART;
                            }
                            break;
                        case 2:
                            if (validateTimeZone$validateSign(input, intRef)) {
                                state = State.AFTER_SIGN;
                            } else {
                                state = State.IN_PART;
                                continue;
                            }
                            break;
                        case 3:
                            if (validateTimeZone$validateTimeComponent(intRef, input, 2)) {
                                state = State.AFTER_HOUR;
                            } else {
                                state = State.IN_PART;
                                continue;
                            }
                            break;
                        case 4:
                            if (validateTimeZone$validateTimeComponent(intRef, input, 2)) {
                                state = State.AFTER_INIT_HOUR;
                            } else if (validateTimeZone$validateTimeComponent(intRef, input, 1)) {
                                state = State.END;
                            }
                            break;
                        case 5:
                            if (validateTimeZone$validateTimeComponentWithColon(input, intRef)) {
                                state = State.AFTER_COLON_MINUTE;
                            } else {
                                state = State.IN_PART;
                                continue;
                            }
                            break;
                        case 6:
                            if (validateTimeZone$validateTimeComponentWithColon(input, intRef)) {
                                state = State.AFTER_COLON_MINUTE;
                            } else if (validateTimeZone$validateTimeComponent(intRef, input, 2)) {
                                state = State.AFTER_MINUTE;
                            }
                            break;
                        case 7:
                            if (validateTimeZone$validateTimeComponent(intRef, input, 2)) {
                                state = State.END;
                            }
                            break;
                        case 8:
                            if (validateTimeZone$validateTimeComponentWithColon(input, intRef)) {
                                state = State.END;
                            }
                            break;
                        case 9:
                            if (validateTimeZone$validateTimeZoneChar(input, intRef)) {
                                state = State.IN_PART;
                            } else if (validateTimeZone$validateSlash(input, intRef)) {
                                state = State.AFTER_SLASH;
                            }
                            break;
                        case 10:
                            if (validateTimeZone$validateTimeZoneInitial(input, intRef)) {
                                state = State.IN_PART;
                            }
                            break;
                        case 11:
                            break;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                }
            }
            int i2 = intRef.element;
            if (state != State.AFTER_SLASH && state != State.AFTER_INIT_SIGN) {
                i = 0;
            }
            return i2 - i;
        }

        private static final boolean validateTimeZone$validatePrefix(CharSequence charSequence, Ref.IntRef intRef, List<String> list) {
            String str;
            Object next;
            Iterator<T> it = list.iterator();
            while (true) {
                str = null;
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                CharSequence charSequence2 = charSequence;
                if (StringsKt.startsWith$default(charSequence2, (CharSequence) next, intRef.element, false, 4, (Object) null)) {
                    break;
                }
                charSequence = charSequence2;
            }
            String str2 = (String) next;
            if (str2 != null) {
                intRef.element += str2.length();
                str = str2;
            }
            return str != null;
        }

        private static final boolean validateTimeZone$validateSign(CharSequence charSequence, Ref.IntRef intRef) {
            Companion companion = TimeZoneParserOperation.INSTANCE;
            if (!CollectionsKt.listOf((Object[]) new Character[]{'+', '-'}).contains(Character.valueOf(charSequence.charAt(intRef.element)))) {
                return false;
            }
            intRef.element++;
            return true;
        }

        private static final boolean validateTimeZone$validateTimeComponent(Ref.IntRef intRef, CharSequence charSequence, int i) {
            boolean z;
            Companion companion = TimeZoneParserOperation.INSTANCE;
            Iterable iterableUntil = RangesKt.until(intRef.element, intRef.element + i);
            if (!(iterableUntil instanceof Collection) || !((Collection) iterableUntil).isEmpty()) {
                Iterator it = iterableUntil.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = true;
                        break;
                    }
                    Character orNull = StringsKt.getOrNull(charSequence, ((IntIterator) it).nextInt());
                    if (!(orNull != null ? UtilKt.isAsciiDigit(orNull.charValue()) : false)) {
                        z = false;
                        break;
                    }
                }
            } else {
                z = true;
                break;
            }
            if (!z) {
                return false;
            }
            intRef.element += i;
            return true;
        }

        private static final boolean validateTimeZone$validateTimeComponentWithColon(CharSequence charSequence, Ref.IntRef intRef) {
            Companion companion = TimeZoneParserOperation.INSTANCE;
            if (charSequence.charAt(intRef.element) == ':') {
                intRef.element++;
                Companion companion2 = TimeZoneParserOperation.INSTANCE;
                if (validateTimeZone$validateTimeComponent(intRef, charSequence, 2)) {
                    return true;
                }
                intRef.element--;
            }
            return false;
        }

        private static final boolean validateTimeZone$isTimeZoneInitial(char c) {
            return UtilKt.isAsciiLetter(c) || c == '.' || c == '_';
        }

        private static final boolean validateTimeZone$isTimeZoneChar(char c) {
            return validateTimeZone$isTimeZoneInitial(c) || UtilKt.isAsciiDigit(c) || c == '-' || c == '+';
        }

        private static final boolean validateTimeZone$validateTimeZoneInitial(CharSequence charSequence, Ref.IntRef intRef) {
            Companion companion = TimeZoneParserOperation.INSTANCE;
            if (!validateTimeZone$isTimeZoneInitial(charSequence.charAt(intRef.element))) {
                return false;
            }
            intRef.element++;
            return true;
        }

        private static final boolean validateTimeZone$validateTimeZoneChar(CharSequence charSequence, Ref.IntRef intRef) {
            Companion companion = TimeZoneParserOperation.INSTANCE;
            if (!validateTimeZone$isTimeZoneChar(charSequence.charAt(intRef.element))) {
                return false;
            }
            intRef.element++;
            return true;
        }

        private static final boolean validateTimeZone$validateSlash(CharSequence charSequence, Ref.IntRef intRef) {
            Companion companion = TimeZoneParserOperation.INSTANCE;
            if (!(charSequence.charAt(intRef.element) == '/')) {
                return false;
            }
            intRef.element++;
            return true;
        }
    }
}
