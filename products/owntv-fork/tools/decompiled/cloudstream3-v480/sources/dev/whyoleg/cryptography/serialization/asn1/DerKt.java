package dev.whyoleg.cryptography.serialization.asn1;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.cast.MediaError;
import io.ktor.http.ContentType;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Der.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a-\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\r\u001a\u00020\u00012\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f¢\u0006\u0002\b\u0012H\u0087\u0080\u0004\u001a-\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\r\u001a\u00020\u00012\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f¢\u0006\u0002\b\u0012H\u0086\u0080\u0004*D\b\u0007\u0010\u0000\"\u00020\u00012\u00020\u0001B6\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\u001c\b\u0005\u0012\u0018\b\u000bB\u0014\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0006\b\t\u0012\u0002\b\f\u0012\n\b\n\u0012\u0006\b\n0\u000b8\f¨\u0006\u0013"}, d2 = {"DER", "Ldev/whyoleg/cryptography/serialization/asn1/Der;", "Lkotlin/Deprecated;", ContentType.Message.TYPE, "Renamed to Der", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "Der", "imports", "level", "Lkotlin/DeprecationLevel;", MediaError.ERROR_TYPE_ERROR, TypedValues.TransitionType.S_FROM, "builderAction", "Lkotlin/Function1;", "Ldev/whyoleg/cryptography/serialization/asn1/Der$Builder;", "", "Lkotlin/ExtensionFunctionType;", "cryptography-serialization-asn1"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DerKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Renamed to Der", replaceWith = @ReplaceWith(expression = "Der", imports = {}))
    public static /* synthetic */ void DER$annotations() {
    }

    public static /* synthetic */ Der DER$default(Der der, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            der = Der.INSTANCE;
        }
        return DER(der, function1);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Renamed to Der", replaceWith = @ReplaceWith(expression = "Der(from, builderAction)", imports = {}))
    public static final Der DER(Der from, Function1<? super Der.Builder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        return Der(from, builderAction);
    }

    public static /* synthetic */ Der Der$default(Der der, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            der = Der.INSTANCE;
        }
        return Der(der, function1);
    }

    public static final Der Der(Der from, Function1<? super Der.Builder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        Der.Builder builder = new Der.Builder(from);
        builderAction.invoke(builder);
        return new DerImpl(builder);
    }
}
