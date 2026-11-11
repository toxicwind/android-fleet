package com.lagradost.cloudstream3.syncproviders;

import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.cast.MediaTrack;
import com.lagradost.cloudstream3.subtitles.AbstractSubtitleEntities;
import com.lagradost.cloudstream3.subtitles.SubtitleResource;
import java.util.List;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;

/* JADX INFO: compiled from: SubtitleAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0097@¢\u0006\u0002\u0010\u000bJ\"\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\u0006H\u0097@¢\u0006\u0002\u0010\u000fJ$\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\u0006H\u0097@¢\u0006\u0002\u0010\u0013J \u0010\u0014\u001a\u00020\u00122\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\u0006H\u0087@¢\u0006\u0002\u0010\u000f¨\u0006\u0015"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/SubtitleAPI;", "Lcom/lagradost/cloudstream3/syncproviders/AuthAPI;", "<init>", "()V", "search", "", "Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleEntity;", "auth", "Lcom/lagradost/cloudstream3/syncproviders/AuthData;", SearchIntents.EXTRA_QUERY, "Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleSearch;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleSearch;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", "", MediaTrack.ROLE_SUBTITLE, "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getResources", "", "Lcom/lagradost/cloudstream3/subtitles/SubtitleResource;", "(Lcom/lagradost/cloudstream3/subtitles/SubtitleResource;Lcom/lagradost/cloudstream3/syncproviders/AuthData;Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resource", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class SubtitleAPI extends AuthAPI {

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.SubtitleAPI$getResources$1, reason: invalid class name */
    /* JADX INFO: compiled from: SubtitleAPI.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.SubtitleAPI", f = "SubtitleAPI.kt", i = {0, 0, 0, 0}, l = {27}, m = "getResources$suspendImpl", n = {"$this", "$receiver", "auth", MediaTrack.ROLE_SUBTITLE}, nl = {28}, s = {"L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubtitleAPI.getResources$suspendImpl(SubtitleAPI.this, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.SubtitleAPI$resource$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SubtitleAPI.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.SubtitleAPI", f = "SubtitleAPI.kt", i = {0, 0, 0, 0}, l = {34}, m = "resource", n = {"auth", MediaTrack.ROLE_SUBTITLE, "$this$resource_u24lambda_u240", "$i$a$-apply-SubtitleAPI$resource$2"}, nl = {35}, s = {"L$0", "L$1", "L$3", "I$0"}, v = 2)
    static final class C07681 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C07681(Continuation<? super C07681> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubtitleAPI.this.resource(null, null, this);
        }
    }

    public Object getResources(SubtitleResource subtitleResource, AuthData authData, AbstractSubtitleEntities.SubtitleEntity subtitleEntity, Continuation<? super Unit> continuation) {
        return getResources$suspendImpl(this, subtitleResource, authData, subtitleEntity, continuation);
    }

    public Object load(AuthData authData, AbstractSubtitleEntities.SubtitleEntity subtitleEntity, Continuation<? super String> continuation) {
        return load$suspendImpl(this, authData, subtitleEntity, continuation);
    }

    public Object search(AuthData authData, AbstractSubtitleEntities.SubtitleSearch subtitleSearch, Continuation<? super List<AbstractSubtitleEntities.SubtitleEntity>> continuation) {
        return search$suspendImpl(this, authData, subtitleSearch, continuation);
    }

    static /* synthetic */ Object search$suspendImpl(SubtitleAPI subtitleAPI, AuthData authData, AbstractSubtitleEntities.SubtitleSearch subtitleSearch, Continuation<? super List<AbstractSubtitleEntities.SubtitleEntity>> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    static /* synthetic */ Object load$suspendImpl(SubtitleAPI subtitleAPI, AuthData authData, AbstractSubtitleEntities.SubtitleEntity subtitleEntity, Continuation<? super String> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    static /* synthetic */ Object getResources$suspendImpl(SubtitleAPI subtitleAPI, SubtitleResource subtitleResource, AuthData authData, AbstractSubtitleEntities.SubtitleEntity subtitleEntity, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = subtitleAPI.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = subtitleAPI.new AnonymousClass1(continuation);
        }
        Object objLoad = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objLoad);
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(subtitleAPI);
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(subtitleResource);
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(authData);
            anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(subtitleEntity);
            anonymousClass1.L$4 = subtitleResource;
            anonymousClass1.label = 1;
            objLoad = subtitleAPI.load(authData, subtitleEntity, anonymousClass1);
            if (objLoad == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            subtitleResource = (SubtitleResource) anonymousClass1.L$4;
            ResultKt.throwOnFailure(objLoad);
        }
        SubtitleResource.addUrl$default(subtitleResource, (String) objLoad, null, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object resource(AuthData authData, AbstractSubtitleEntities.SubtitleEntity subtitleEntity, Continuation<? super SubtitleResource> continuation) {
        C07681 c07681;
        if (continuation instanceof C07681) {
            c07681 = (C07681) continuation;
            if ((c07681.label & Integer.MIN_VALUE) != 0) {
                c07681.label -= Integer.MIN_VALUE;
            } else {
                c07681 = new C07681(continuation);
            }
        } else {
            c07681 = new C07681(continuation);
        }
        Object obj = c07681.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07681.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c07681.I$0;
            SubtitleResource subtitleResource = (SubtitleResource) c07681.L$2;
            ResultKt.throwOnFailure(obj);
            return subtitleResource;
        }
        ResultKt.throwOnFailure(obj);
        SubtitleResource subtitleResource2 = new SubtitleResource();
        c07681.L$0 = SpillingKt.nullOutSpilledVariable(authData);
        c07681.L$1 = SpillingKt.nullOutSpilledVariable(subtitleEntity);
        c07681.L$2 = subtitleResource2;
        c07681.L$3 = SpillingKt.nullOutSpilledVariable(subtitleResource2);
        c07681.I$0 = 0;
        c07681.label = 1;
        return getResources(subtitleResource2, authData, subtitleEntity, c07681) == coroutine_suspended ? coroutine_suspended : subtitleResource2;
    }
}
