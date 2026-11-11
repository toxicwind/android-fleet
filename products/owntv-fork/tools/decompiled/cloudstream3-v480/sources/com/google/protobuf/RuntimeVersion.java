package com.google.protobuf;

import java.util.Locale;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes6.dex */
public final class RuntimeVersion {
    public static final RuntimeDomain DOMAIN;
    public static final int MAJOR = 4;
    private static final int MAX_WARNING_COUNT = 20;
    public static final int MINOR = 35;
    public static final RuntimeDomain OSS_DOMAIN;
    public static final int OSS_MAJOR = 4;
    public static final int OSS_MINOR = 35;
    public static final int OSS_PATCH = 0;
    public static final String OSS_SUFFIX = "";
    public static final int PATCH = 0;
    public static final String SUFFIX = "";
    private static final String VERSION_STRING;
    private static final Logger logger;
    static int majorWarningLoggedCount;
    static int minorWarningLoggedCount;
    static boolean preleaseRuntimeWarningLogged;

    public enum RuntimeDomain {
        GOOGLE_INTERNAL,
        PUBLIC
    }

    static {
        RuntimeDomain runtimeDomain = RuntimeDomain.PUBLIC;
        OSS_DOMAIN = runtimeDomain;
        DOMAIN = runtimeDomain;
        majorWarningLoggedCount = 0;
        minorWarningLoggedCount = 0;
        preleaseRuntimeWarningLogged = false;
        VERSION_STRING = versionString(4, 35, 0, "");
        logger = Logger.getLogger(RuntimeVersion.class.getName());
    }

    public static void validateProtobufGencodeVersion(RuntimeDomain domain, int major, int minor, int patch, String suffix, String location) {
        validateProtobufGencodeVersionImpl(domain, major, minor, patch, suffix, location);
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0070  */
    private static void validateProtobufGencodeVersionImpl(RuntimeDomain domain, int major, int minor, int patch, String suffix, String location) {
        String strVersionString;
        if (checkDisabled()) {
            return;
        }
        if (major < 0 || minor < 0 || patch < 0) {
            throw new ProtobufRuntimeVersionException("Invalid gencode version: " + versionString(major, minor, patch, suffix));
        }
        RuntimeDomain runtimeDomain = DOMAIN;
        if (domain != runtimeDomain) {
            throw new ProtobufRuntimeVersionException(String.format(Locale.ROOT, "Detected mismatched Protobuf Gencode/Runtime domains when loading %s: gencode %s, runtime %s. Cross-domain usage of Protobuf is not supported.", location, domain, runtimeDomain));
        }
        if (major == 4 && minor == 35 && patch == 0 && suffix.equals("")) {
            return;
        }
        if (major == 4) {
            strVersionString = null;
        } else if (major == 3) {
            if (majorWarningLoggedCount < 20) {
                strVersionString = versionString(major, minor, patch, suffix);
                logger.warning(String.format(Locale.US, " Protobuf gencode version %s is exactly one major version older than the runtime version %s at %s. Please update the gencode to avoid compatibility violations in the next runtime release.", strVersionString, VERSION_STRING, location));
                majorWarningLoggedCount++;
            } else {
                strVersionString = null;
            }
        } else {
            throw new ProtobufRuntimeVersionException(String.format(Locale.US, "Detected mismatched Protobuf Gencode/Runtime major versions when loading %s: gencode %s, runtime %s. Same major version is required.", location, versionString(major, minor, patch, suffix), VERSION_STRING));
        }
        if (35 < minor || (minor == 35 && patch > 0)) {
            if (strVersionString == null) {
                strVersionString = versionString(major, minor, patch, suffix);
            }
            throw new ProtobufRuntimeVersionException(String.format(Locale.US, "Detected incompatible Protobuf Gencode/Runtime versions when loading %s: gencode %s, runtime %s. Runtime version cannot be older than the linked gencode version.", location, strVersionString, VERSION_STRING));
        }
        if (suffix.isEmpty()) {
            return;
        }
        if (!suffix.isEmpty()) {
            if (strVersionString == null) {
                strVersionString = versionString(major, minor, patch, suffix);
            }
            throw new ProtobufRuntimeVersionException(String.format(Locale.US, "Detected mismatched Protobuf Gencode/Runtime version suffixes when loading %s: gencode %s, runtime %s. Prerelease gencode must be used with the same runtime.", location, strVersionString, VERSION_STRING));
        }
        if (major == 4 && minor == 35 && patch == 0) {
            if (strVersionString == null) {
                strVersionString = versionString(major, minor, patch, suffix);
            }
            throw new ProtobufRuntimeVersionException(String.format(Locale.US, "Detected mismatched Protobuf Gencode/Runtime version suffixes when loading %s: gencode %s, runtime %s. Prelease runtimes must only be used with exact match gencode (including suffix) or non-prerelease gencode versions of a lower version.", location, strVersionString, VERSION_STRING));
        }
    }

    public static final class ProtobufRuntimeVersionException extends RuntimeException {
        public ProtobufRuntimeVersionException(String message) {
            super(message);
        }
    }

    private static String versionString(int major, int minor, int patch, String suffix) {
        return String.format(Locale.US, "%d.%d.%d%s", Integer.valueOf(major), Integer.valueOf(minor), Integer.valueOf(patch), suffix);
    }

    private static boolean checkDisabled() {
        String str = System.getenv("TEMPORARILY_DISABLE_PROTOBUF_VERSION_CHECK");
        return str != null && str.equals("true");
    }

    private RuntimeVersion() {
    }
}
