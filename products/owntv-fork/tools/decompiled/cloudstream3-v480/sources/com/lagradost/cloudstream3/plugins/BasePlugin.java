package com.lagradost.cloudstream3.plugins;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lagradost.api.Log;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import io.ktor.http.ContentDisposition;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: BasePlugin.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001:\u0001\u0017B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\tJ\b\u0010\n\u001a\u00020\u0005H\u0016J\b\u0010\u000b\u001a\u00020\u0005H\u0016R.\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\f\u001a\u0004\u0018\u00010\r8F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\b\u000f\u0010\u0003\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/plugins/BasePlugin;", "", "<init>", "()V", "registerMainAPI", "", "element", "Lcom/lagradost/cloudstream3/MainAPI;", "registerExtractorAPI", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "beforeUnload", "load", ES6Iterator.VALUE_PROPERTY, "", "__filename", "get__filename$annotations", "get__filename", "()Ljava/lang/String;", "set__filename", "(Ljava/lang/String;)V", ContentDisposition.Parameters.FileName, "getFilename", "setFilename", "Manifest", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class BasePlugin {
    private String filename;

    @Deprecated(level = DeprecationLevel.ERROR, message = "Renamed to `filename` to follow conventions", replaceWith = @ReplaceWith(expression = ContentDisposition.Parameters.FileName, imports = {}))
    public static /* synthetic */ void get__filename$annotations() {
    }

    public void beforeUnload() throws Throwable {
    }

    public void load() throws Throwable {
    }

    public final void registerMainAPI(MainAPI element) {
        Intrinsics.checkNotNullParameter(element, "element");
        Log.INSTANCE.i(BasePluginKt.PLUGIN_TAG, "Adding " + element.getName() + " (" + element.getMainUrl() + ") MainAPI");
        element.setSourcePlugin(this.filename);
        APIHolder.INSTANCE.getAllProviders().add(element);
        APIHolder.INSTANCE.addPluginMapping(element);
    }

    public final void registerExtractorAPI(ExtractorApi element) {
        Intrinsics.checkNotNullParameter(element, "element");
        Log.INSTANCE.i(BasePluginKt.PLUGIN_TAG, "Adding " + element.getName() + " (" + element.getMainUrl() + ") ExtractorApi");
        element.setSourcePlugin(this.filename);
        ExtractorApiKt.getExtractorApis().add(element);
    }

    public final String get__filename() {
        return this.filename;
    }

    public final void set__filename(String str) {
        this.filename = str;
    }

    /* JADX INFO: compiled from: BasePlugin.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 +2\u00020\u0001:\u0002*+B\u0007¢\u0006\u0004\b\u0002\u0010\u0003BA\b\u0010\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0002\u0010\u000eJ%\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0001¢\u0006\u0002\b)R&\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000f\u0010\u0003\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R&\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0014\u0010\u0003\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R$\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0017\u0010\u0003\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR(\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0016\n\u0002\u0010!\u0012\u0004\b\u001c\u0010\u0003\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006,"}, d2 = {"Lcom/lagradost/cloudstream3/plugins/BasePlugin$Manifest;", "", "<init>", "()V", "seen0", "", "name", "", "pluginClassName", "requiresResources", "", "version", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;ZLjava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getName$annotations", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getPluginClassName$annotations", "getPluginClassName", "setPluginClassName", "getRequiresResources$annotations", "getRequiresResources", "()Z", "setRequiresResources", "(Z)V", "getVersion$annotations", "getVersion", "()Ljava/lang/Integer;", "setVersion", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final class Manifest {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("name")
        private String name;

        @JsonProperty("pluginClassName")
        private String pluginClassName;

        @JsonProperty("requiresResources")
        private boolean requiresResources;

        @JsonProperty("version")
        private Integer version;

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("pluginClassName")
        public static /* synthetic */ void getPluginClassName$annotations() {
        }

        @SerialName("requiresResources")
        public static /* synthetic */ void getRequiresResources$annotations() {
        }

        @SerialName("version")
        public static /* synthetic */ void getVersion$annotations() {
        }

        /* JADX INFO: compiled from: BasePlugin.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/plugins/BasePlugin$Manifest$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/plugins/BasePlugin$Manifest;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Manifest> serializer() {
                return BasePlugin$Manifest$$serializer.INSTANCE;
            }
        }

        public Manifest() {
        }

        public /* synthetic */ Manifest(int i, String str, String str2, boolean z, Integer num, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.name = null;
            } else {
                this.name = str;
            }
            if ((i & 2) == 0) {
                this.pluginClassName = null;
            } else {
                this.pluginClassName = str2;
            }
            if ((i & 4) == 0) {
                this.requiresResources = false;
            } else {
                this.requiresResources = z;
            }
            if ((i & 8) == 0) {
                this.version = null;
            } else {
                this.version = num;
            }
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(Manifest self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.name != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.name);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.pluginClassName != null) {
                output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.pluginClassName);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 2) || self.requiresResources) {
                output.encodeBooleanElement(serialDesc, 2, self.requiresResources);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 3) && self.version == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 3, IntSerializer.INSTANCE, self.version);
        }

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            this.name = str;
        }

        public final String getPluginClassName() {
            return this.pluginClassName;
        }

        public final void setPluginClassName(String str) {
            this.pluginClassName = str;
        }

        public final boolean getRequiresResources() {
            return this.requiresResources;
        }

        public final void setRequiresResources(boolean z) {
            this.requiresResources = z;
        }

        public final Integer getVersion() {
            return this.version;
        }

        public final void setVersion(Integer num) {
            this.version = num;
        }
    }

    public final String getFilename() {
        return this.filename;
    }

    public final void setFilename(String str) {
        this.filename = str;
    }
}
