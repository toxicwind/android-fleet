package org.mozilla.javascript;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: loaded from: classes8.dex */
public class ImplementationVersion {
    private static final ImplementationVersion version = new ImplementationVersion();
    private String versionString;

    public static String get() {
        return version.versionString;
    }

    private ImplementationVersion() {
        try {
            Enumeration<URL> resources = ImplementationVersion.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
            while (resources.hasMoreElements()) {
                try {
                    InputStream inputStreamOpenStream = resources.nextElement().openStream();
                    try {
                        Attributes mainAttributes = new Manifest(inputStreamOpenStream).getMainAttributes();
                        if ("Mozilla Rhino".equals(mainAttributes.getValue("Implementation-Title"))) {
                            StringBuilder sb = new StringBuilder(23);
                            sb.append("Rhino ");
                            sb.append(mainAttributes.getValue("Implementation-Version"));
                            String value = mainAttributes.getValue("Built-Date");
                            if (value != null) {
                                String strReplaceAll = value.replaceAll("-", Stream.ID_UNKNOWN);
                                sb.append(' ');
                                sb.append(strReplaceAll);
                            }
                            this.versionString = sb.toString();
                            if (inputStreamOpenStream != null) {
                                inputStreamOpenStream.close();
                                return;
                            }
                            return;
                        }
                        if (inputStreamOpenStream != null) {
                            inputStreamOpenStream.close();
                        }
                    } catch (Throwable th) {
                        if (inputStreamOpenStream != null) {
                            try {
                                inputStreamOpenStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                } catch (IOException unused) {
                    continue;
                }
                continue;
            }
            this.versionString = "Rhino Snapshot";
        } catch (IOException unused2) {
        }
    }
}
