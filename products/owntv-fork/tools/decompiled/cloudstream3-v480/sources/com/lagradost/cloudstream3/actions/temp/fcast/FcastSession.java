package com.lagradost.cloudstream3.actions.temp.fcast;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.safefile.SafeFileKt;
import io.ktor.http.ContentType;
import java.io.DataOutputStream;
import java.net.Socket;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: FcastSession.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\f\u001a\u00020\u000bH\u0007J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000bH\u0002J\u0006\u0010\u0010\u001a\u00020\u000eJ!\u0010\u0011\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u0002H\u0012¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/fcast/FcastSession;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "hostAddress", "", "<init>", "(Ljava/lang/String;)V", "tag", "getTag", "()Ljava/lang/String;", "socket", "Ljava/net/Socket;", TtmlNode.TEXT_EMPHASIS_MARK_OPEN, "close", "", "acquireSocket", "ping", "sendMessage", ExifInterface.GPS_DIRECTION_TRUE, "opcode", "Lcom/lagradost/cloudstream3/actions/temp/fcast/Opcode;", ContentType.Message.TYPE, "(Lcom/lagradost/cloudstream3/actions/temp/fcast/Opcode;Ljava/lang/Object;)V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class FcastSession implements AutoCloseable {
    private final String hostAddress;
    private Socket socket;
    private final String tag;

    public FcastSession(String hostAddress) {
        Intrinsics.checkNotNullParameter(hostAddress, "hostAddress");
        this.hostAddress = hostAddress;
        this.tag = "FcastSession";
    }

    public final String getTag() {
        return this.tag;
    }

    public final Socket open() {
        Socket socket = new Socket(this.hostAddress, FcastManager.TCP_PORT);
        this.socket = socket;
        return socket;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        Socket socket = this.socket;
        if (socket != null) {
            SafeFileKt.closeQuietly(socket);
        }
        this.socket = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Socket acquireSocket() {
        Socket socket = this.socket;
        return socket == null ? open() : socket;
    }

    public final void ping() {
        sendMessage(Opcode.Ping, null);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.actions.temp.fcast.FcastSession$sendMessage$1, reason: invalid class name */
    /* JADX INFO: compiled from: FcastSession.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/actions/temp/fcast/FcastSession;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.actions.temp.fcast.FcastSession$sendMessage$1", f = "FcastSession.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, FcastSession, Continuation<? super Unit>, Object> {
        final /* synthetic */ T $message;
        final /* synthetic */ Opcode $opcode;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(T t, Opcode opcode, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$message = t;
            this.$opcode = opcode;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, FcastSession fcastSession, Continuation<? super Unit> continuation) {
            return FcastSession.this.new AnonymousClass1(this.$message, this.$opcode, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:12:0x0035  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            byte[] bytes;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DataOutputStream dataOutputStream = new DataOutputStream(FcastSession.this.acquireSocket().getOutputStream());
            Object obj2 = this.$message;
            String json = obj2 != null ? AppUtils.INSTANCE.toJson(obj2) : null;
            if (json != null) {
                bytes = json.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                if (bytes == null) {
                    bytes = new byte[0];
                }
            } else {
                bytes = new byte[0];
            }
            int length = bytes.length + 1;
            byte[] bArr = new byte[4];
            for (int i = 0; i < 4; i++) {
                bArr[i] = (byte) ((length >> (i * 8)) & 255);
            }
            Log.d(FcastSession.this.getTag(), "Sending message with size: " + length + ", opcode: " + this.$opcode);
            dataOutputStream.write(bArr);
            dataOutputStream.write(new byte[]{this.$opcode.getValue()});
            dataOutputStream.write(bytes);
            return Unit.INSTANCE;
        }
    }

    public final <T> void sendMessage(Opcode opcode, T message) {
        Intrinsics.checkNotNullParameter(opcode, "opcode");
        Coroutines.INSTANCE.ioSafe(this, new AnonymousClass1(message, opcode, null));
    }
}
