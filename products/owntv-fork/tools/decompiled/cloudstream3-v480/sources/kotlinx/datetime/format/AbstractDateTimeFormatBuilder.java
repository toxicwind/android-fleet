package kotlinx.datetime.format;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.format.AbstractDateTimeFormatBuilder;
import kotlinx.datetime.internal.format.AlternativesParsingFormatStructure;
import kotlinx.datetime.internal.format.AppendableFormatStructure;
import kotlinx.datetime.internal.format.CachedFormatStructure;
import kotlinx.datetime.internal.format.ConstantFormatStructure;
import kotlinx.datetime.internal.format.OptionalFormatStructure;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DateTimeFormatBuilder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b`\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0014\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00002\u00020\u0003J\r\u0010\b\u001a\u00028\u0001H&¢\u0006\u0002\u0010\tJ\\\u0010\n\u001a\u00020\u000b24\u0010\f\u001a\u001b\u0012\u0017\b\u0001\u0012\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u000b0\u000e¢\u0006\u0002\b\u000f0\r\"\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u000b0\u000e¢\u0006\u0002\b\u000f2\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u000b0\u000e¢\u0006\u0002\b\u000fH\u0016¢\u0006\u0002\u0010\u0011J)\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\u0017\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u000b0\u000e¢\u0006\u0002\b\u000fH\u0016J\u0010\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0014H\u0016J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0016R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001aÀ\u0006\u0003"}, d2 = {"Lkotlinx/datetime/format/AbstractDateTimeFormatBuilder;", "Target", "ActualSelf", "Lkotlinx/datetime/format/DateTimeFormatBuilder;", "actualBuilder", "Lkotlinx/datetime/internal/format/AppendableFormatStructure;", "getActualBuilder", "()Lkotlinx/datetime/internal/format/AppendableFormatStructure;", "createEmpty", "()Lkotlinx/datetime/format/AbstractDateTimeFormatBuilder;", "appendAlternativeParsingImpl", "", "otherFormats", "", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "mainFormat", "([Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "appendOptionalImpl", "onZero", "", "format", "chars", ES6Iterator.VALUE_PROPERTY, JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lkotlinx/datetime/internal/format/CachedFormatStructure;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface AbstractDateTimeFormatBuilder<Target, ActualSelf extends AbstractDateTimeFormatBuilder<Target, ActualSelf>> extends DateTimeFormatBuilder {
    void appendAlternativeParsingImpl(Function1<? super ActualSelf, Unit>[] otherFormats, Function1<? super ActualSelf, Unit> mainFormat);

    void appendOptionalImpl(String onZero, Function1<? super ActualSelf, Unit> format);

    CachedFormatStructure<Target> build();

    @Override // kotlinx.datetime.format.DateTimeFormatBuilder
    void chars(String value);

    ActualSelf createEmpty();

    AppendableFormatStructure<Target> getActualBuilder();

    /* JADX INFO: renamed from: kotlinx.datetime.format.AbstractDateTimeFormatBuilder$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: DateTimeFormatBuilder.kt */
    public final /* synthetic */ class CC {
        public static void $default$appendOptionalImpl(AbstractDateTimeFormatBuilder _this, String onZero, Function1 format) {
            Intrinsics.checkNotNullParameter(onZero, "onZero");
            Intrinsics.checkNotNullParameter(format, "format");
            AppendableFormatStructure<Target> actualBuilder = _this.getActualBuilder();
            AbstractDateTimeFormatBuilder _this2 = _this.createEmpty();
            format.invoke(_this2);
            Unit unit = Unit.INSTANCE;
            actualBuilder.add(new OptionalFormatStructure(onZero, _this2.getActualBuilder().build()));
        }

        public static void $default$chars(AbstractDateTimeFormatBuilder _this, String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            _this.getActualBuilder().add(new ConstantFormatStructure(value));
        }

        public static CachedFormatStructure $default$build(AbstractDateTimeFormatBuilder _this) {
            return new CachedFormatStructure(_this.getActualBuilder().build().getFormats());
        }

        public static void $default$appendAlternativeParsingImpl(AbstractDateTimeFormatBuilder _this, Function1[] otherFormats, Function1 mainFormat) {
            Intrinsics.checkNotNullParameter(otherFormats, "otherFormats");
            Intrinsics.checkNotNullParameter(mainFormat, "mainFormat");
            ArrayList arrayList = new ArrayList(otherFormats.length);
            for (Function1 function1 : otherFormats) {
                AbstractDateTimeFormatBuilder abstractDateTimeFormatBuilderCreateEmpty = _this.createEmpty();
                function1.invoke(abstractDateTimeFormatBuilderCreateEmpty);
                arrayList.add(abstractDateTimeFormatBuilderCreateEmpty.getActualBuilder().build());
            }
            AbstractDateTimeFormatBuilder abstractDateTimeFormatBuilderCreateEmpty2 = _this.createEmpty();
            mainFormat.invoke(abstractDateTimeFormatBuilderCreateEmpty2);
            _this.getActualBuilder().add(new AlternativesParsingFormatStructure(abstractDateTimeFormatBuilderCreateEmpty2.getActualBuilder().build(), arrayList));
        }
    }

    /* JADX INFO: compiled from: DateTimeFormatBuilder.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static <Target, ActualSelf extends AbstractDateTimeFormatBuilder<Target, ActualSelf>> void appendAlternativeParsingImpl(AbstractDateTimeFormatBuilder<Target, ActualSelf> abstractDateTimeFormatBuilder, Function1<? super ActualSelf, Unit>[] otherFormats, Function1<? super ActualSelf, Unit> mainFormat) {
            Intrinsics.checkNotNullParameter(otherFormats, "otherFormats");
            Intrinsics.checkNotNullParameter(mainFormat, "mainFormat");
            CC.$default$appendAlternativeParsingImpl(abstractDateTimeFormatBuilder, otherFormats, mainFormat);
        }

        @Deprecated
        public static <Target, ActualSelf extends AbstractDateTimeFormatBuilder<Target, ActualSelf>> void appendOptionalImpl(AbstractDateTimeFormatBuilder<Target, ActualSelf> abstractDateTimeFormatBuilder, String onZero, Function1<? super ActualSelf, Unit> format) {
            Intrinsics.checkNotNullParameter(onZero, "onZero");
            Intrinsics.checkNotNullParameter(format, "format");
            CC.$default$appendOptionalImpl(abstractDateTimeFormatBuilder, onZero, format);
        }

        @Deprecated
        public static <Target, ActualSelf extends AbstractDateTimeFormatBuilder<Target, ActualSelf>> void chars(AbstractDateTimeFormatBuilder<Target, ActualSelf> abstractDateTimeFormatBuilder, String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            CC.$default$chars(abstractDateTimeFormatBuilder, value);
        }

        @Deprecated
        public static <Target, ActualSelf extends AbstractDateTimeFormatBuilder<Target, ActualSelf>> CachedFormatStructure<Target> build(AbstractDateTimeFormatBuilder<Target, ActualSelf> abstractDateTimeFormatBuilder) {
            return CC.$default$build(abstractDateTimeFormatBuilder);
        }
    }
}
