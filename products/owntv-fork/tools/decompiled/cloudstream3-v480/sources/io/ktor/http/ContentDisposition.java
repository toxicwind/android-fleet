package io.ktor.http;

import coil3.util.UtilsKt;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: ContentDisposition.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001d\u001eB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ'\u0010\r\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0010\u001a\u00020\u00002\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0014\u001a\u00020\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0096\u0082\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0017\u001a\u00020\u0016H\u0096\u0080\u0004¢\u0006\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0003\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001a¨\u0006\u001f"}, d2 = {"Lio/ktor/http/ContentDisposition;", "Lio/ktor/http/HeaderValueWithParameters;", "", "disposition", "", "Lio/ktor/http/HeaderValueParam;", "parameters", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "key", ES6Iterator.VALUE_PROPERTY, "", "encodeValue", "withParameter", "(Ljava/lang/String;Ljava/lang/String;Z)Lio/ktor/http/ContentDisposition;", "newParameters", "withParameters", "(Ljava/util/List;)Lio/ktor/http/ContentDisposition;", "", "other", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "getDisposition", "()Ljava/lang/String;", "getName", "name", "Companion", "Parameters", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ContentDisposition extends HeaderValueWithParameters {
    private static final ContentDisposition Attachment;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ContentDisposition File;
    private static final ContentDisposition Inline;
    private static final ContentDisposition Mixed;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentDisposition(String disposition, List<HeaderValueParam> parameters) {
        super(disposition, parameters);
        Intrinsics.checkNotNullParameter(disposition, "disposition");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
    }

    public /* synthetic */ ContentDisposition(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final String getDisposition() {
        return getContent();
    }

    public final String getName() {
        return parameter("name");
    }

    public static /* synthetic */ ContentDisposition withParameter$default(ContentDisposition contentDisposition, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return contentDisposition.withParameter(str, str2, z);
    }

    public final ContentDisposition withParameter(String key, String value, boolean encodeValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        if (encodeValue) {
            value = ContentDispositionKt.encodeContentDispositionAttribute(key, value);
        }
        return new ContentDisposition(getDisposition(), CollectionsKt.plus((Collection<? extends HeaderValueParam>) getParameters(), new HeaderValueParam(key, value)));
    }

    public final ContentDisposition withParameters(List<HeaderValueParam> newParameters) {
        Intrinsics.checkNotNullParameter(newParameters, "newParameters");
        return new ContentDisposition(getDisposition(), CollectionsKt.plus((Collection) getParameters(), (Iterable) newParameters));
    }

    public boolean equals(Object other) {
        if (!(other instanceof ContentDisposition)) {
            return false;
        }
        ContentDisposition contentDisposition = (ContentDisposition) other;
        return Intrinsics.areEqual(getDisposition(), contentDisposition.getDisposition()) && Intrinsics.areEqual(getParameters(), contentDisposition.getParameters());
    }

    public int hashCode() {
        return (getDisposition().hashCode() * 31) + getParameters().hashCode();
    }

    /* JADX INFO: compiled from: ContentDisposition.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0017\u0010\u0011\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\f¨\u0006\u0013"}, d2 = {"Lio/ktor/http/ContentDisposition$Companion;", "", "<init>", "()V", "", ES6Iterator.VALUE_PROPERTY, "Lio/ktor/http/ContentDisposition;", "parse", "(Ljava/lang/String;)Lio/ktor/http/ContentDisposition;", "File", "Lio/ktor/http/ContentDisposition;", "getFile", "()Lio/ktor/http/ContentDisposition;", "Mixed", "getMixed", "Attachment", "getAttachment", "Inline", "getInline", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ContentDisposition getFile() {
            return ContentDisposition.File;
        }

        public final ContentDisposition getMixed() {
            return ContentDisposition.Mixed;
        }

        public final ContentDisposition getAttachment() {
            return ContentDisposition.Attachment;
        }

        public final ContentDisposition getInline() {
            return ContentDisposition.Inline;
        }

        public final ContentDisposition parse(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            HeaderValueWithParameters.Companion companion = HeaderValueWithParameters.INSTANCE;
            HeaderValue headerValue = (HeaderValue) CollectionsKt.last((List) HttpHeaderValueParserKt.parseHeaderValue(value));
            return new ContentDisposition(headerValue.getValue(), headerValue.getParams());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        int i = 2;
        File = new ContentDisposition(UtilsKt.SCHEME_FILE, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0);
        Mixed = new ContentDisposition("mixed", 0 == true ? 1 : 0, i, 0 == true ? 1 : 0);
        Attachment = new ContentDisposition("attachment", 0 == true ? 1 : 0, i, 0 == true ? 1 : 0);
        Inline = new ContentDisposition("inline", 0 == true ? 1 : 0, i, 0 == true ? 1 : 0);
    }

    /* JADX INFO: compiled from: ContentDisposition.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000b\u0010\u0006R\u0014\u0010\f\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\r\u0010\u0006¨\u0006\u000e"}, d2 = {"Lio/ktor/http/ContentDisposition$Parameters;", "", "<init>", "()V", "", "FileName", "Ljava/lang/String;", "FileNameAsterisk", "Name", "CreationDate", "ModificationDate", "ReadDate", "Size", "Handling", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Parameters {
        public static final String CreationDate = "creation-date";
        public static final String FileName = "filename";
        public static final String FileNameAsterisk = "filename*";
        public static final String Handling = "handling";
        public static final Parameters INSTANCE = new Parameters();
        public static final String ModificationDate = "modification-date";
        public static final String Name = "name";
        public static final String ReadDate = "read-date";
        public static final String Size = "size";

        private Parameters() {
        }
    }
}
