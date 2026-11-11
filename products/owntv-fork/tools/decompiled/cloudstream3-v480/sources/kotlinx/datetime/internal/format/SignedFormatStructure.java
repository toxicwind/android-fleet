package kotlinx.datetime.internal.format;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.internal.format.formatter.FormatterStructure;
import kotlinx.datetime.internal.format.formatter.SignedFormatter;
import kotlinx.datetime.internal.format.parser.ParserKt;
import kotlinx.datetime.internal.format.parser.ParserStructure;
import kotlinx.datetime.internal.format.parser.SignParser;

/* JADX INFO: compiled from: FormatStructure.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0013\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0016J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u001aH\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lkotlinx/datetime/internal/format/SignedFormatStructure;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/datetime/internal/format/NonConcatenatedFormatStructure;", "format", "Lkotlinx/datetime/internal/format/FormatStructure;", "withPlusSign", "", "<init>", "(Lkotlinx/datetime/internal/format/FormatStructure;Z)V", "getFormat", "()Lkotlinx/datetime/internal/format/FormatStructure;", "getWithPlusSign", "()Z", "fieldSigns", "", "Lkotlinx/datetime/internal/format/FieldSign;", "toString", "", "equals", "other", "", "hashCode", "", "parser", "Lkotlinx/datetime/internal/format/parser/ParserStructure;", "formatter", "Lkotlinx/datetime/internal/format/formatter/FormatterStructure;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SignedFormatStructure<T> implements NonConcatenatedFormatStructure<T> {
    private final Set<FieldSign<T>> fieldSigns;
    private final FormatStructure<T> format;
    private final boolean withPlusSign;

    /* JADX WARN: Multi-variable type inference failed */
    public SignedFormatStructure(FormatStructure<? super T> format, boolean z) {
        Intrinsics.checkNotNullParameter(format, "format");
        this.format = format;
        this.withPlusSign = z;
        List listBasicFormats = FormatStructureKt.basicFormats(format);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listBasicFormats.iterator();
        while (it.hasNext()) {
            FieldSign sign = ((FieldFormatDirective) it.next()).getField().getSign();
            if (sign != null) {
                arrayList.add(sign);
            }
        }
        Set<FieldSign<T>> set = CollectionsKt.toSet(arrayList);
        this.fieldSigns = set;
        if (set.isEmpty()) {
            throw new IllegalArgumentException("Signed format must contain at least one field with a sign".toString());
        }
    }

    public final FormatStructure<T> getFormat() {
        return this.format;
    }

    public final boolean getWithPlusSign() {
        return this.withPlusSign;
    }

    public String toString() {
        return "SignedFormatStructure(" + this.format + ')';
    }

    public boolean equals(Object other) {
        if (!(other instanceof SignedFormatStructure)) {
            return false;
        }
        SignedFormatStructure signedFormatStructure = (SignedFormatStructure) other;
        return Intrinsics.areEqual(this.format, signedFormatStructure.format) && this.withPlusSign == signedFormatStructure.withPlusSign;
    }

    public int hashCode() {
        return (this.format.hashCode() * 31) + UByte$$ExternalSyntheticBackport0.m(this.withPlusSign);
    }

    @Override // kotlinx.datetime.internal.format.FormatStructure
    public ParserStructure<T> parser() {
        return ParserKt.concat(CollectionsKt.listOf((Object[]) new ParserStructure[]{new ParserStructure(CollectionsKt.listOf(new SignParser(new Function2() { // from class: kotlinx.datetime.internal.format.SignedFormatStructure$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return SignedFormatStructure.parser$lambda$2(this.f$0, obj, ((Boolean) obj2).booleanValue());
            }
        }, this.withPlusSign, "sign for " + this.fieldSigns)), CollectionsKt.emptyList()), this.format.parser()}));
    }

    static final Unit parser$lambda$2(SignedFormatStructure signedFormatStructure, Object obj, boolean z) {
        for (FieldSign<T> fieldSign : signedFormatStructure.fieldSigns) {
            boolean z2 = true;
            boolean zAreEqual = Intrinsics.areEqual((Object) fieldSign.isNegative().getter(obj), (Object) true);
            Accessor<T, Boolean> accessorIsNegative = fieldSign.isNegative();
            if (z == zAreEqual) {
                z2 = false;
            }
            accessorIsNegative.trySetWithoutReassigning(obj, Boolean.valueOf(z2));
        }
        return Unit.INSTANCE;
    }

    @Override // kotlinx.datetime.internal.format.FormatStructure
    public FormatterStructure<T> formatter() {
        return new SignedFormatter(this.format.formatter(), new AnonymousClass1(this), this.withPlusSign);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> boolean formatter$checkIfAllNegative(SignedFormatStructure<? super T> signedFormatStructure, T t) {
        boolean z = false;
        for (FieldSign<? super T> fieldSign : ((SignedFormatStructure) signedFormatStructure).fieldSigns) {
            if (Intrinsics.areEqual((Object) fieldSign.isNegative().getter(t), (Object) true)) {
                z = true;
            } else if (!fieldSign.isZero(t)) {
                return false;
            }
        }
        return z;
    }

    /* JADX INFO: renamed from: kotlinx.datetime.internal.format.SignedFormatStructure$formatter$1, reason: invalid class name */
    /* JADX INFO: compiled from: FormatStructure.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<T, Boolean> {
        final /* synthetic */ SignedFormatStructure<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(SignedFormatStructure<? super T> signedFormatStructure) {
            super(1, Intrinsics.Kotlin.class, "checkIfAllNegative", "formatter$checkIfAllNegative(Lkotlinx/datetime/internal/format/SignedFormatStructure;Ljava/lang/Object;)Z", 0);
            this.this$0 = signedFormatStructure;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(T t) {
            return Boolean.valueOf(SignedFormatStructure.formatter$checkIfAllNegative(this.this$0, t));
        }
    }
}
