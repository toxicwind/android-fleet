package kotlinx.datetime.internal.format;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.internal.format.formatter.ConditionalFormatter;
import kotlinx.datetime.internal.format.formatter.ConstantStringFormatterStructure;
import kotlinx.datetime.internal.format.formatter.FormatterStructure;
import kotlinx.datetime.internal.format.parser.ParserKt;
import kotlinx.datetime.internal.format.parser.ParserStructure;
import kotlinx.datetime.internal.format.parser.UnconditionalModification;

/* JADX INFO: compiled from: FormatStructure.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u001bB\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\r\u001a\u00020\u0004H\u0016J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0016J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u001aH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR$\u0010\u000e\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u0000\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lkotlinx/datetime/internal/format/OptionalFormatStructure;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/datetime/internal/format/NonConcatenatedFormatStructure;", "onZero", "", "format", "Lkotlinx/datetime/internal/format/FormatStructure;", "<init>", "(Ljava/lang/String;Lkotlinx/datetime/internal/format/FormatStructure;)V", "getOnZero", "()Ljava/lang/String;", "getFormat", "()Lkotlinx/datetime/internal/format/FormatStructure;", "toString", "fields", "", "Lkotlinx/datetime/internal/format/OptionalFormatStructure$PropertyWithDefault;", "", "equals", "", "other", "hashCode", "", "parser", "Lkotlinx/datetime/internal/format/parser/ParserStructure;", "formatter", "Lkotlinx/datetime/internal/format/formatter/FormatterStructure;", "PropertyWithDefault", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class OptionalFormatStructure<T> implements NonConcatenatedFormatStructure<T> {
    private final List<PropertyWithDefault<T, ? extends Object>> fields;
    private final FormatStructure<T> format;
    private final String onZero;

    /* JADX WARN: Multi-variable type inference failed */
    public OptionalFormatStructure(String onZero, FormatStructure<? super T> format) {
        Intrinsics.checkNotNullParameter(onZero, "onZero");
        Intrinsics.checkNotNullParameter(format, "format");
        this.onZero = onZero;
        this.format = format;
        List listBasicFormats = FormatStructureKt.basicFormats(format);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listBasicFormats, 10));
        Iterator<T> it = listBasicFormats.iterator();
        while (it.hasNext()) {
            arrayList.add(((FieldFormatDirective) it.next()).getField());
        }
        List listDistinct = CollectionsKt.distinct(arrayList);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDistinct, 10));
        Iterator<T> it2 = listDistinct.iterator();
        while (it2.hasNext()) {
            arrayList2.add(PropertyWithDefault.INSTANCE.fromField((FieldSpec) it2.next()));
        }
        this.fields = arrayList2;
    }

    public final String getOnZero() {
        return this.onZero;
    }

    public final FormatStructure<T> getFormat() {
        return this.format;
    }

    public String toString() {
        return "Optional(" + this.onZero + ", " + this.format + ')';
    }

    public boolean equals(Object other) {
        if (!(other instanceof OptionalFormatStructure)) {
            return false;
        }
        OptionalFormatStructure optionalFormatStructure = (OptionalFormatStructure) other;
        return Intrinsics.areEqual(this.onZero, optionalFormatStructure.onZero) && Intrinsics.areEqual(this.format, optionalFormatStructure.format);
    }

    public int hashCode() {
        return (this.onZero.hashCode() * 31) + this.format.hashCode();
    }

    @Override // kotlinx.datetime.internal.format.FormatStructure
    public ParserStructure<T> parser() {
        List listListOf;
        List listEmptyList = CollectionsKt.emptyList();
        ParserStructure[] parserStructureArr = new ParserStructure[2];
        parserStructureArr[0] = this.format.parser();
        ParserStructure[] parserStructureArr2 = new ParserStructure[2];
        parserStructureArr2[0] = new ConstantFormatStructure(this.onZero).parser();
        if (this.fields.isEmpty()) {
            listListOf = CollectionsKt.emptyList();
        } else {
            listListOf = CollectionsKt.listOf(new UnconditionalModification(new Function1() { // from class: kotlinx.datetime.internal.format.OptionalFormatStructure$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return OptionalFormatStructure.parser$lambda$2(this.f$0, obj);
                }
            }));
        }
        parserStructureArr2[1] = new ParserStructure(listListOf, CollectionsKt.emptyList());
        parserStructureArr[1] = ParserKt.concat(CollectionsKt.listOf((Object[]) parserStructureArr2));
        return new ParserStructure<>(listEmptyList, CollectionsKt.listOf((Object[]) parserStructureArr));
    }

    static final Unit parser$lambda$2(OptionalFormatStructure optionalFormatStructure, Object obj) {
        for (PropertyWithDefault<T, ? extends Object> propertyWithDefault : optionalFormatStructure.fields) {
            ((PropertyWithDefault) propertyWithDefault).accessor.trySetWithoutReassigning(obj, ((PropertyWithDefault) propertyWithDefault).defaultValue);
        }
        return Unit.INSTANCE;
    }

    @Override // kotlinx.datetime.internal.format.FormatStructure
    public FormatterStructure<T> formatter() {
        FormatterStructure<T> formatterStructure = this.format.formatter();
        List<PropertyWithDefault<T, ? extends Object>> list = this.fields;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            PropertyWithDefault propertyWithDefault = (PropertyWithDefault) it.next();
            arrayList.add(new ComparisonPredicate(propertyWithDefault.defaultValue, new OptionalFormatStructure$PropertyWithDefault$isDefaultComparisonPredicate$1(propertyWithDefault.accessor)));
        }
        Predicate predicateConjunctionPredicate = PredicateKt.conjunctionPredicate(arrayList);
        if (predicateConjunctionPredicate instanceof Truth) {
            return new ConstantStringFormatterStructure(this.onZero);
        }
        return new ConditionalFormatter(CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(new AnonymousClass1(predicateConjunctionPredicate), new ConstantStringFormatterStructure(this.onZero)), TuplesKt.to(new AnonymousClass2(Truth.INSTANCE), formatterStructure)}));
    }

    /* JADX INFO: renamed from: kotlinx.datetime.internal.format.OptionalFormatStructure$formatter$1, reason: invalid class name */
    /* JADX INFO: compiled from: FormatStructure.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<T, Boolean> {
        AnonymousClass1(Object obj) {
            super(1, obj, Predicate.class, "test", "test(Ljava/lang/Object;)Z", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(T t) {
            return Boolean.valueOf(((Predicate) this.receiver).test(t));
        }
    }

    /* JADX INFO: renamed from: kotlinx.datetime.internal.format.OptionalFormatStructure$formatter$2, reason: invalid class name */
    /* JADX INFO: compiled from: FormatStructure.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<Object, Boolean> {
        AnonymousClass2(Object obj) {
            super(1, obj, Truth.class, "test", "test(Ljava/lang/Object;)Z", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(Object obj) {
            return Boolean.valueOf(((Truth) this.receiver).test(obj));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: FormatStructure.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000 \u0010*\u0006\b\u0001\u0010\u0001 \u0000*\u0004\b\u0002\u0010\u00022\u00020\u0003:\u0001\u0010B%\b\u0002\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00028\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u0001H\u0086\b¢\u0006\u0002\u0010\rJ\u0015\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u000fH\u0086\bR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00028\u0002X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0011"}, d2 = {"Lkotlinx/datetime/internal/format/OptionalFormatStructure$PropertyWithDefault;", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.LONGITUDE_EAST, "", "accessor", "Lkotlinx/datetime/internal/format/Accessor;", "defaultValue", "<init>", "(Lkotlinx/datetime/internal/format/Accessor;Ljava/lang/Object;)V", "Ljava/lang/Object;", "assignDefault", "", TypedValues.AttributesType.S_TARGET, "(Ljava/lang/Object;)V", "isDefaultComparisonPredicate", "Lkotlinx/datetime/internal/format/ComparisonPredicate;", "Companion", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    static final class PropertyWithDefault<T, E> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final Accessor<T, E> accessor;
        private final E defaultValue;

        public /* synthetic */ PropertyWithDefault(Accessor accessor, Object obj, DefaultConstructorMarker defaultConstructorMarker) {
            this(accessor, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private PropertyWithDefault(Accessor<? super T, E> accessor, E e) {
            this.accessor = accessor;
            this.defaultValue = e;
        }

        /* JADX INFO: compiled from: FormatStructure.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00070\u0005\"\u0004\b\u0003\u0010\u0006\"\u0004\b\u0004\u0010\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00070\t¨\u0006\n"}, d2 = {"Lkotlinx/datetime/internal/format/OptionalFormatStructure$PropertyWithDefault$Companion;", "", "<init>", "()V", "fromField", "Lkotlinx/datetime/internal/format/OptionalFormatStructure$PropertyWithDefault;", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.LONGITUDE_EAST, "field", "Lkotlinx/datetime/internal/format/FieldSpec;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final <T, E> PropertyWithDefault<T, E> fromField(FieldSpec<? super T, E> field) {
                Intrinsics.checkNotNullParameter(field, "field");
                E defaultValue = field.getDefaultValue();
                if (defaultValue == null) {
                    throw new IllegalArgumentException(("The field '" + field.getName() + "' does not define a default value").toString());
                }
                return new PropertyWithDefault<>(field.getAccessor(), defaultValue, null);
            }
        }

        public final void assignDefault(T target) {
            this.accessor.trySetWithoutReassigning(target, this.defaultValue);
        }

        public final ComparisonPredicate<T, E> isDefaultComparisonPredicate() {
            return new ComparisonPredicate<>(this.defaultValue, new OptionalFormatStructure$PropertyWithDefault$isDefaultComparisonPredicate$1(this.accessor));
        }
    }
}
