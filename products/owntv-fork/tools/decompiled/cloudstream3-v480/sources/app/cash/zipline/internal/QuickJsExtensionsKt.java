package app.cash.zipline.internal;

import app.cash.zipline.QuickJs;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import okhttp3.internal.url._UrlKt;

/* JADX INFO: compiled from: quickJsExtensions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\u0004\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u0006*\u00020\u00032\u0006\u0010\b\u001a\u00020\u0006H\u0000\u001a\u0012\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0010\u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a \u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0000\u001a \u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0000\u001a \u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006H\u0000¨\u0006\u0013"}, d2 = {"collectModuleDependencies", "", "quickJs", "Lapp/cash/zipline/QuickJs;", "getModuleDependencies", "", "", "getGlobalThis", "key", "getLog", "initModuleLoader", "loadJsModule", "script", "id", "bytecode", "", "runApplication", "mainModuleId", "mainFunction", "zipline_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class QuickJsExtensionsKt {
    public static final void collectModuleDependencies(QuickJs quickJs) {
        Intrinsics.checkNotNullParameter(quickJs, "quickJs");
        quickJs.evaluate(CollectModuleDependenciesJsKt.COLLECT_DEPENDENCIES_DEFINE_JS, "collectDependencies.js");
    }

    public static final List<String> getModuleDependencies(QuickJs quickJs) {
        Intrinsics.checkNotNullParameter(quickJs, "quickJs");
        String globalThis = getGlobalThis(quickJs, CollectModuleDependenciesJsKt.CURRENT_MODULE_DEPENDENCIES);
        if (globalThis == null) {
            globalThis = _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
        }
        Json.Companion companion = Json.INSTANCE;
        companion.getSerializersModule();
        return (List) companion.decodeFromString(new ArrayListSerializer(StringSerializer.INSTANCE), globalThis);
    }

    public static final String getGlobalThis(QuickJs quickJs, String key) {
        Intrinsics.checkNotNullParameter(quickJs, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        return (String) quickJs.evaluate("globalThis." + key, "getGlobalThis.js");
    }

    public static final String getLog(QuickJs quickJs) {
        Intrinsics.checkNotNullParameter(quickJs, "quickJs");
        return getGlobalThis(quickJs, "log");
    }

    public static final void initModuleLoader(QuickJs quickJs) {
        Intrinsics.checkNotNullParameter(quickJs, "quickJs");
        quickJs.evaluate(DefineJsKt.DEFINE_JS, "define.js");
    }

    public static final void loadJsModule(QuickJs quickJs, String script, String id) {
        Intrinsics.checkNotNullParameter(quickJs, "quickJs");
        Intrinsics.checkNotNullParameter(script, "script");
        Intrinsics.checkNotNullParameter(id, "id");
        QuickJs.evaluate$default(quickJs, "globalThis.app_cash_zipline_currentModuleId = '" + id + "';", null, 2, null);
        quickJs.evaluate(script, id);
        QuickJs.evaluate$default(quickJs, "delete globalThis.app_cash_zipline_currentModuleId;", null, 2, null);
    }

    public static final void loadJsModule(QuickJs quickJs, String id, byte[] bytecode) {
        Intrinsics.checkNotNullParameter(quickJs, "quickJs");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(bytecode, "bytecode");
        QuickJs.evaluate$default(quickJs, "globalThis.app_cash_zipline_currentModuleId = '" + id + "';", null, 2, null);
        quickJs.execute(bytecode);
        QuickJs.evaluate$default(quickJs, "delete globalThis.app_cash_zipline_currentModuleId;", null, 2, null);
    }

    public static final void runApplication(QuickJs quickJs, String mainModuleId, String mainFunction) {
        Intrinsics.checkNotNullParameter(quickJs, "quickJs");
        Intrinsics.checkNotNullParameter(mainModuleId, "mainModuleId");
        Intrinsics.checkNotNullParameter(mainFunction, "mainFunction");
        quickJs.evaluate("require('" + mainModuleId + "')." + mainFunction + "()", "RunApplication.kt");
    }
}
