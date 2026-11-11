package j$.nio.channels;

/* JADX INFO: loaded from: classes3.dex */
public interface CompletionHandler {

    public final /* synthetic */ class VivifiedWrapper implements CompletionHandler {
        public final /* synthetic */ java.nio.channels.CompletionHandler wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.channels.CompletionHandler completionHandler) {
            this.wrappedValue = completionHandler;
        }

        public static /* synthetic */ CompletionHandler convert(java.nio.channels.CompletionHandler completionHandler) {
            if (completionHandler == null) {
                return null;
            }
            return completionHandler instanceof Wrapper ? CompletionHandler.this : new VivifiedWrapper(completionHandler);
        }

        @Override // j$.nio.channels.CompletionHandler
        public /* synthetic */ void completed(Object obj, Object obj2) {
            this.wrappedValue.completed(obj, obj2);
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.channels.CompletionHandler completionHandler = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return completionHandler.equals(obj);
        }

        @Override // j$.nio.channels.CompletionHandler
        public /* synthetic */ void failed(Throwable th, Object obj) {
            this.wrappedValue.failed(th, obj);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.channels.CompletionHandler {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.channels.CompletionHandler convert(CompletionHandler completionHandler) {
            if (completionHandler == null) {
                return null;
            }
            return completionHandler instanceof VivifiedWrapper ? ((VivifiedWrapper) completionHandler).wrappedValue : completionHandler.new Wrapper();
        }

        @Override // java.nio.channels.CompletionHandler
        public /* synthetic */ void completed(Object obj, Object obj2) {
            CompletionHandler.this.completed(obj, obj2);
        }

        public /* synthetic */ boolean equals(Object obj) {
            CompletionHandler completionHandler = CompletionHandler.this;
            if (obj instanceof Wrapper) {
                obj = CompletionHandler.this;
            }
            return completionHandler.equals(obj);
        }

        @Override // java.nio.channels.CompletionHandler
        public /* synthetic */ void failed(Throwable th, Object obj) {
            CompletionHandler.this.failed(th, obj);
        }

        public /* synthetic */ int hashCode() {
            return CompletionHandler.this.hashCode();
        }
    }

    void completed(Object obj, Object obj2);

    void failed(Throwable th, Object obj);
}
