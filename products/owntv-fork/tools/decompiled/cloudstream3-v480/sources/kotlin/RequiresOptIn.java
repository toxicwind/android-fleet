package kotlin;

import com.google.android.gms.cast.MediaError;
import io.ktor.http.ContentType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.MustBeDocumented;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: OptIn.kt */
/* JADX INFO: loaded from: classes7.dex */
@Target({ElementType.ANNOTATION_TYPE})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.ANNOTATION_CLASS})
@Retention(RetentionPolicy.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
@MustBeDocumented
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\u0002\u0018\u00002\u00020\u0001:\u0001\nB$\bF\u0012\u000e\b\u0002\u0010\u0002\u001a\u00020\u0003B\u0004\b\b(\u0004\u0012\u0010\b\u0002\u0010\u0005\u001a\u00020\u0006B\u0006\b\n0\u00068\u0007R\u0013\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0002\u0010\bR\u0013\u0010\u0005\u001a\u00020\u0006X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0005\u0010\t¨\u0006\u000b"}, d2 = {"Lkotlin/RequiresOptIn;", "", ContentType.Message.TYPE, "", "", "level", "Lkotlin/RequiresOptIn$Level;", MediaError.ERROR_TYPE_ERROR, "()Ljava/lang/String;", "()Lkotlin/RequiresOptIn$Level;", "Level", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Documented
public @interface RequiresOptIn {

    /* JADX INFO: compiled from: OptIn.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/RequiresOptIn$Level;", "", "<init>", "(Ljava/lang/String;I)V", "WARNING", MediaError.ERROR_TYPE_ERROR, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum Level {
        WARNING,
        ERROR;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Level> getEntries() {
            return $ENTRIES;
        }
    }

    Level level() default Level.ERROR;

    String message() default "";
}
