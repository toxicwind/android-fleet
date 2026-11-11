package com.lagradost.cloudstream3.ui.settings.utils;

import android.content.Context;
import android.net.Uri;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.safefile.SafeFile;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DirectoryPicker.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\u001aN\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001*\u00020\u00032:\u0010\u0004\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0005¨\u0006\f"}, d2 = {"getChooseFolderLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/net/Uri;", "Landroidx/fragment/app/Fragment;", "dirSelected", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "uri", "", "path", "", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DirectoryPickerKt {
    public static final ActivityResultLauncher<Uri> getChooseFolderLauncher(final Fragment fragment, final Function2<? super Uri, ? super String, Unit> dirSelected) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(dirSelected, "dirSelected");
        ActivityResultLauncher<Uri> activityResultLauncherRegisterForActivityResult = fragment.registerForActivityResult(new ActivityResultContracts.OpenDocumentTree(), new ActivityResultCallback() { // from class: com.lagradost.cloudstream3.ui.settings.utils.DirectoryPickerKt$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                DirectoryPickerKt.getChooseFolderLauncher$lambda$0(dirSelected, fragment, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        return activityResultLauncherRegisterForActivityResult;
    }

    static final void getChooseFolderLauncher$lambda$0(Function2 function2, Fragment fragment, Uri uri) {
        if (uri == null) {
            function2.invoke(null, null);
            return;
        }
        Context context = fragment.getContext();
        if (context == null && (context = CloudStreamApp.INSTANCE.getContext()) == null) {
            return;
        }
        context.getContentResolver().takePersistableUriPermission(uri, 3);
        SafeFile safeFileFromUri = SafeFile.INSTANCE.fromUri(context, uri);
        String strFilePath = safeFileFromUri != null ? safeFileFromUri.filePath() : null;
        System.out.println((Object) ("Selected URI path: " + uri + " - Full path: " + strFilePath));
        function2.invoke(uri, strFilePath);
    }
}
