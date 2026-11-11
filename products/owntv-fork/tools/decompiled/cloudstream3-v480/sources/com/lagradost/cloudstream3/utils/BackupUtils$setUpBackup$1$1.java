package com.lagradost.cloudstream3.utils;

import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: BackupUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Landroidx/fragment/app/FragmentActivity;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.BackupUtils$setUpBackup$1$1", f = "BackupUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class BackupUtils$setUpBackup$1$1 extends SuspendLambda implements Function3<CoroutineScope, FragmentActivity, Continuation<? super Unit>, Object> {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ FragmentActivity $this_setUpBackup;
    final /* synthetic */ Uri $uri;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BackupUtils$setUpBackup$1$1(FragmentActivity fragmentActivity, Uri uri, FragmentActivity fragmentActivity2, Continuation<? super BackupUtils$setUpBackup$1$1> continuation) {
        super(3, continuation);
        this.$activity = fragmentActivity;
        this.$uri = uri;
        this.$this_setUpBackup = fragmentActivity2;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, FragmentActivity fragmentActivity, Continuation<? super Unit> continuation) {
        BackupUtils$setUpBackup$1$1 backupUtils$setUpBackup$1$1 = new BackupUtils$setUpBackup$1$1(this.$activity, this.$uri, this.$this_setUpBackup, continuation);
        backupUtils$setUpBackup$1$1.L$0 = coroutineScope;
        return backupUtils$setUpBackup$1$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        try {
            InputStream inputStreamOpenInputStream = this.$activity.getContentResolver().openInputStream(this.$uri);
            if (inputStreamOpenInputStream == null) {
                return Unit.INSTANCE;
            }
            Reader inputStreamReader = new InputStreamReader(inputStreamOpenInputStream, Charsets.UTF_8);
            String text = TextStreamsKt.readText(inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192));
            AppUtils appUtils = AppUtils.INSTANCE;
            try {
                Result.Companion companion = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(BackupUtils.BackupFile.INSTANCE.serializer());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(BackupUtils.BackupFile.class), null, 2, null));
                } catch (Throwable th2) {
                    Result.Companion companion4 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                }
            }
            if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                objM8936constructorimpl = null;
            }
            KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
            if (kSerializer != null) {
                try {
                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, text);
                } catch (SerializationException e) {
                    ArchComponentExtKt.logError(e);
                    objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<BackupUtils.BackupFile>() { // from class: com.lagradost.cloudstream3.utils.BackupUtils$setUpBackup$1$1$invokeSuspend$$inlined$parseJson$1
                    });
                } catch (Throwable unused) {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<BackupUtils.BackupFile>() { // from class: com.lagradost.cloudstream3.utils.BackupUtils$setUpBackup$1$1$invokeSuspend$$inlined$parseJson$1
                    });
                }
            } else {
                objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<BackupUtils.BackupFile>() { // from class: com.lagradost.cloudstream3.utils.BackupUtils$setUpBackup$1$1$invokeSuspend$$inlined$parseJson$1
                });
            }
            BackupUtils.INSTANCE.restore(this.$activity, (BackupUtils.BackupFile) objDecodeFromString, true, true);
            final FragmentActivity fragmentActivity = this.$activity;
            fragmentActivity.runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.utils.BackupUtils$setUpBackup$1$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    fragmentActivity.recreate();
                }
            });
        } catch (Exception e2) {
            ArchComponentExtKt.logError(e2);
            Coroutines.INSTANCE.main(coroutineScope, new AnonymousClass2(this.$this_setUpBackup, e2, null));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.BackupUtils$setUpBackup$1$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: BackupUtils.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.BackupUtils$setUpBackup$1$1$2", f = "BackupUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Exception $e;
        final /* synthetic */ FragmentActivity $this_setUpBackup;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(FragmentActivity fragmentActivity, Exception exc, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$this_setUpBackup = fragmentActivity;
            this.$e = exc;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$this_setUpBackup, this.$e, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CommonActivity commonActivity = CommonActivity.INSTANCE;
            String string = this.$this_setUpBackup.getString(R.string.restore_failed_format);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String str = String.format(string, Arrays.copyOf(new Object[]{this.$e.toString()}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            CommonActivity.showToast$default(commonActivity, str, (Integer) null, 2, (Object) null);
            return Unit.INSTANCE;
        }
    }
}
