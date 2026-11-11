package kotlin.io.path;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import j$.nio.file.Path;
import kotlin.Metadata;

/* JADX INFO: compiled from: CopyActionContext.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J#\u0010\u0007\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlin/io/path/CopyActionContext;", "", "j$/nio/file/Path", TypedValues.AttributesType.S_TARGET, "", "followLinks", "Lkotlin/io/path/CopyActionResult;", "copyToIgnoringExistingDirectory", "(Lj$/nio/file/Path;Lj$/nio/file/Path;Z)Lkotlin/io/path/CopyActionResult;", "kotlin-stdlib-jdk7"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface CopyActionContext {
    CopyActionResult copyToIgnoringExistingDirectory(Path path, Path path2, boolean z);
}
