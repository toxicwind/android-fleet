package androidx.compose.ui.text;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: compiled from: String.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b`\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H&¢\u0006\u0002\u0010\bJ!\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H&¢\u0006\u0002\u0010\bJ!\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H&¢\u0006\u0002\u0010\bJ!\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H&¢\u0006\u0002\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/text/PlatformStringDelegate;", "", "toUpperCase", "", TypedValues.Custom.S_STRING, "locale", "Ljava/util/Locale;", "Landroidx/compose/ui/text/intl/PlatformLocale;", "(Ljava/lang/String;Ljava/util/Locale;)Ljava/lang/String;", "toLowerCase", "capitalize", "decapitalize", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface PlatformStringDelegate {
    String capitalize(String string, Locale locale);

    String decapitalize(String string, Locale locale);

    String toLowerCase(String string, Locale locale);

    String toUpperCase(String string, Locale locale);
}
