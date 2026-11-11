package app.cash.zipline.internal;

import kotlin.Metadata;

/* JADX INFO: compiled from: collectModuleDependenciesJs.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0003"}, d2 = {"CURRENT_MODULE_DEPENDENCIES", "", "COLLECT_DEPENDENCIES_DEFINE_JS", "zipline_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CollectModuleDependenciesJsKt {
    public static final String COLLECT_DEPENDENCIES_DEFINE_JS = "\n  (function initJsModuleApi() {\n    globalThis.define = function() {\n      var args = Array.from(arguments);\n      var factory = args.pop();\n      var dependencies = (args.length > 0) ? args.pop() : [];\n      var currentModuleDependencies = [];\n\n      dependencies.map(dependency => {\n        if (dependency == 'exports') {\n          return {};\n        } else {\n          currentModuleDependencies.push(dependency);\n        }\n      });\n\n      globalThis.app_cash_zipline_currentModuleDependencies = JSON.stringify(currentModuleDependencies);\n    };\n\n    // By convention, we set 'define.amd' to an object to declare we confirm to the AMD spec.\n    globalThis.define.amd = {};\n  })();\n  ";
    public static final String CURRENT_MODULE_DEPENDENCIES = "app_cash_zipline_currentModuleDependencies";
}
