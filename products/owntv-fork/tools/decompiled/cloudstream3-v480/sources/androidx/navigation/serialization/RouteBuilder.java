package androidx.navigation.serialization;

import androidx.exifinterface.media.ExifInterface;
import androidx.navigation.CollectionNavType;
import androidx.navigation.NavType;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: RouteBuilder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u001bB\u0017\b\u0016\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\u001f\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\tJ\u0006\u0010\f\u001a\u00020\bJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0002J&\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\b2\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0016J4\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\b2\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00162\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0018J \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u00142\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0016H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/navigation/serialization/RouteBuilder;", ExifInterface.GPS_DIRECTION_TRUE, "", "serializer", "Lkotlinx/serialization/KSerializer;", "<init>", "(Lkotlinx/serialization/KSerializer;)V", "path", "", "(Ljava/lang/String;Lkotlinx/serialization/KSerializer;)V", "pathArgs", "queryArgs", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "addPath", "", "addQuery", "name", ES6Iterator.VALUE_PROPERTY, "appendPattern", "index", "", "type", "Landroidx/navigation/NavType;", "appendArg", "", "computeParamType", "Landroidx/navigation/serialization/RouteBuilder$ParamType;", "ParamType", "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RouteBuilder<T> {
    private final String path;
    private String pathArgs;
    private String queryArgs;
    private final KSerializer<T> serializer;

    /* JADX INFO: compiled from: RouteBuilder.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/navigation/serialization/RouteBuilder$ParamType;", "", "<init>", "(Ljava/lang/String;I)V", "PATH", "QUERY", "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private enum ParamType {
        PATH,
        QUERY;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<ParamType> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: RouteBuilder.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ParamType.values().length];
            try {
                iArr[ParamType.PATH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ParamType.QUERY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public RouteBuilder(KSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        this.pathArgs = "";
        this.queryArgs = "";
        this.serializer = serializer;
        this.path = serializer.getDescriptor().getSerialName();
    }

    public RouteBuilder(String path, KSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        this.pathArgs = "";
        this.queryArgs = "";
        this.serializer = serializer;
        this.path = path;
    }

    public final String build() {
        return this.path + this.pathArgs + this.queryArgs;
    }

    private final void addPath(String path) {
        this.pathArgs += '/' + path;
    }

    private final void addQuery(String name, String value) {
        this.queryArgs += (this.queryArgs.length() == 0 ? "?" : "&") + name + '=' + value;
    }

    public final void appendPattern(int index, String name, NavType<Object> type) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(type, "type");
        int i = WhenMappings.$EnumSwitchMapping$0[computeParamType(index, type).ordinal()];
        if (i == 1) {
            addPath("{" + name + AbstractJsonLexerKt.END_OBJ);
            return;
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        addQuery(name, "{" + name + AbstractJsonLexerKt.END_OBJ);
    }

    public final void appendArg(int index, String name, NavType<Object> type, List<String> value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "value");
        int i = WhenMappings.$EnumSwitchMapping$0[computeParamType(index, type).ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            Iterator<T> it = value.iterator();
            while (it.hasNext()) {
                addQuery(name, (String) it.next());
            }
            return;
        }
        if (value.size() != 1) {
            throw new IllegalArgumentException(("Expected one value for argument " + name + ", found " + value.size() + "values instead.").toString());
        }
        addPath((String) CollectionsKt.first((List) value));
    }

    private final ParamType computeParamType(int index, NavType<Object> type) {
        if ((type instanceof CollectionNavType) || this.serializer.getDescriptor().isElementOptional(index)) {
            return ParamType.QUERY;
        }
        return ParamType.PATH;
    }
}
