package androidx.work.impl.model;

import android.net.Uri;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.room.TypeConverter;
import androidx.work.BackoffPolicy;
import androidx.work.ContentUriTriggers;
import androidx.work.NetworkType;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo;
import b.a.a.a.a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class WorkTypeConverters {

    /* JADX INFO: renamed from: androidx.work.impl.model.WorkTypeConverters$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$androidx$work$BackoffPolicy;
        public static final /* synthetic */ int[] $SwitchMap$androidx$work$NetworkType;
        public static final /* synthetic */ int[] $SwitchMap$androidx$work$OutOfQuotaPolicy = new int[OutOfQuotaPolicy.values().length];
        public static final /* synthetic */ int[] $SwitchMap$androidx$work$WorkInfo$State;

        static {
            try {
                $SwitchMap$androidx$work$OutOfQuotaPolicy[OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$work$OutOfQuotaPolicy[OutOfQuotaPolicy.DROP_WORK_REQUEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$androidx$work$NetworkType = new int[NetworkType.values().length];
            try {
                $SwitchMap$androidx$work$NetworkType[NetworkType.NOT_REQUIRED.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$work$NetworkType[NetworkType.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$androidx$work$NetworkType[NetworkType.UNMETERED.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$androidx$work$NetworkType[NetworkType.NOT_ROAMING.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$androidx$work$NetworkType[NetworkType.METERED.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            $SwitchMap$androidx$work$BackoffPolicy = new int[BackoffPolicy.values().length];
            try {
                $SwitchMap$androidx$work$BackoffPolicy[BackoffPolicy.EXPONENTIAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$androidx$work$BackoffPolicy[BackoffPolicy.LINEAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            $SwitchMap$androidx$work$WorkInfo$State = new int[WorkInfo.State.values().length];
            try {
                $SwitchMap$androidx$work$WorkInfo$State[WorkInfo.State.ENQUEUED.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$androidx$work$WorkInfo$State[WorkInfo.State.RUNNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$androidx$work$WorkInfo$State[WorkInfo.State.SUCCEEDED.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$androidx$work$WorkInfo$State[WorkInfo.State.FAILED.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$androidx$work$WorkInfo$State[WorkInfo.State.BLOCKED.ordinal()] = 5;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$androidx$work$WorkInfo$State[WorkInfo.State.CANCELLED.ordinal()] = 6;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    public interface BackoffPolicyIds {
        public static final int EXPONENTIAL = 0;
        public static final int LINEAR = 1;
    }

    public interface NetworkTypeIds {
        public static final int CONNECTED = 1;
        public static final int METERED = 4;
        public static final int NOT_REQUIRED = 0;
        public static final int NOT_ROAMING = 3;
        public static final int TEMPORARILY_UNMETERED = 5;
        public static final int UNMETERED = 2;
    }

    public interface OutOfPolicyIds {
        public static final int DROP_WORK_REQUEST = 1;
        public static final int RUN_AS_NON_EXPEDITED_WORK_REQUEST = 0;
    }

    public interface StateIds {
        public static final int BLOCKED = 4;
        public static final int CANCELLED = 5;
        public static final String COMPLETED_STATES = "(2, 3, 5)";
        public static final int ENQUEUED = 0;
        public static final int FAILED = 3;
        public static final int RUNNING = 1;
        public static final int SUCCEEDED = 2;
    }

    @TypeConverter
    public static int backoffPolicyToInt(BackoffPolicy backoffPolicy) {
        int iOrdinal = backoffPolicy.ordinal();
        if (iOrdinal == 0) {
            return 0;
        }
        if (iOrdinal == 1) {
            return 1;
        }
        throw new IllegalArgumentException(a.a("Could not convert ", backoffPolicy, " to int"));
    }

    /* JADX WARN: Code duplicated, block: B:57:0x005a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @TypeConverter
    public static ContentUriTriggers byteArrayToContentUriTriggers(byte[] bytes) throws Throwable {
        ObjectInputStream objectInputStream;
        Throwable th;
        IOException e2;
        ContentUriTriggers contentUriTriggers = new ContentUriTriggers();
        if (bytes == null) {
            return contentUriTriggers;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        try {
            try {
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
                try {
                    try {
                        for (int i = objectInputStream.readInt(); i > 0; i--) {
                            contentUriTriggers.add(Uri.parse(objectInputStream.readUTF()), objectInputStream.readBoolean());
                        }
                        try {
                            objectInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        byteArrayInputStream.close();
                    } catch (IOException e4) {
                        e2 = e4;
                        e2.printStackTrace();
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        byteArrayInputStream.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    try {
                        byteArrayInputStream.close();
                        throw th;
                    } catch (IOException e7) {
                        e7.printStackTrace();
                        throw th;
                    }
                }
            } catch (IOException e8) {
                e8.printStackTrace();
            }
        } catch (IOException e9) {
            objectInputStream = null;
            e2 = e9;
        } catch (Throwable th3) {
            objectInputStream = null;
            th = th3;
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            byteArrayInputStream.close();
            throw th;
        }
        return contentUriTriggers;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.ObjectOutputStream] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.ObjectOutputStream] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.ObjectOutputStream] */
    @TypeConverter
    public static byte[] contentUriTriggersToByteArray(ContentUriTriggers contentUriTriggers) throws Throwable {
        ?? objectOutputStream;
        boolean zHasNext;
        ?? r1 = 0;
        ?? r2 = 0;
        r1 = 0;
        if (contentUriTriggers.size() == 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                try {
                    objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    try {
                        objectOutputStream.writeInt(contentUriTriggers.size());
                        Iterator<ContentUriTriggers.Trigger> it = contentUriTriggers.getTriggers().iterator();
                        while (true) {
                            zHasNext = it.hasNext();
                            if (zHasNext) {
                                ContentUriTriggers.Trigger next = it.next();
                                objectOutputStream.writeUTF(next.getUri().toString());
                                objectOutputStream.writeBoolean(next.shouldTriggerForDescendants());
                            } else {
                                try {
                                    break;
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                        objectOutputStream.close();
                        byteArrayOutputStream.close();
                        r1 = zHasNext;
                    } catch (IOException e3) {
                        e = e3;
                        r2 = objectOutputStream;
                        e.printStackTrace();
                        if (r2 != 0) {
                            try {
                                r2.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        byteArrayOutputStream.close();
                        r1 = r2;
                    } catch (Throwable th) {
                        th = th;
                        if (objectOutputStream != 0) {
                            try {
                                objectOutputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        try {
                            byteArrayOutputStream.close();
                            throw th;
                        } catch (IOException e6) {
                            e6.printStackTrace();
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    objectOutputStream = r1;
                }
            } catch (IOException e7) {
                e = e7;
            }
        } catch (IOException e8) {
            e8.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    @TypeConverter
    public static BackoffPolicy intToBackoffPolicy(int value) {
        if (value == 0) {
            return BackoffPolicy.EXPONENTIAL;
        }
        if (value == 1) {
            return BackoffPolicy.LINEAR;
        }
        throw new IllegalArgumentException(a.g("Could not convert ", value, " to BackoffPolicy"));
    }

    @TypeConverter
    public static NetworkType intToNetworkType(int value) {
        if (value == 0) {
            return NetworkType.NOT_REQUIRED;
        }
        if (value == 1) {
            return NetworkType.CONNECTED;
        }
        if (value == 2) {
            return NetworkType.UNMETERED;
        }
        if (value == 3) {
            return NetworkType.NOT_ROAMING;
        }
        if (value == 4) {
            return NetworkType.METERED;
        }
        if (Build.VERSION.SDK_INT < 30 || value != 5) {
            throw new IllegalArgumentException(a.g("Could not convert ", value, " to NetworkType"));
        }
        return NetworkType.TEMPORARILY_UNMETERED;
    }

    @NonNull
    @TypeConverter
    public static OutOfQuotaPolicy intToOutOfQuotaPolicy(int value) {
        if (value == 0) {
            return OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST;
        }
        if (value == 1) {
            return OutOfQuotaPolicy.DROP_WORK_REQUEST;
        }
        throw new IllegalArgumentException(a.g("Could not convert ", value, " to OutOfQuotaPolicy"));
    }

    @TypeConverter
    public static WorkInfo.State intToState(int value) {
        if (value == 0) {
            return WorkInfo.State.ENQUEUED;
        }
        if (value == 1) {
            return WorkInfo.State.RUNNING;
        }
        if (value == 2) {
            return WorkInfo.State.SUCCEEDED;
        }
        if (value == 3) {
            return WorkInfo.State.FAILED;
        }
        if (value == 4) {
            return WorkInfo.State.BLOCKED;
        }
        if (value == 5) {
            return WorkInfo.State.CANCELLED;
        }
        throw new IllegalArgumentException(a.g("Could not convert ", value, " to State"));
    }

    @TypeConverter
    public static int networkTypeToInt(NetworkType networkType) {
        int iOrdinal = networkType.ordinal();
        if (iOrdinal == 0) {
            return 0;
        }
        int i = 1;
        if (iOrdinal != 1) {
            i = 2;
            if (iOrdinal != 2) {
                i = 3;
                if (iOrdinal != 3) {
                    i = 4;
                    if (iOrdinal != 4) {
                        if (Build.VERSION.SDK_INT < 30 || networkType != NetworkType.TEMPORARILY_UNMETERED) {
                            throw new IllegalArgumentException(a.a("Could not convert ", networkType, " to int"));
                        }
                        return 5;
                    }
                }
            }
        }
        return i;
    }

    @TypeConverter
    public static int outOfQuotaPolicyToInt(@NonNull OutOfQuotaPolicy policy) {
        int iOrdinal = policy.ordinal();
        if (iOrdinal == 0) {
            return 0;
        }
        if (iOrdinal == 1) {
            return 1;
        }
        throw new IllegalArgumentException(a.a("Could not convert ", policy, " to int"));
    }

    @TypeConverter
    public static int stateToInt(WorkInfo.State state) {
        int iOrdinal = state.ordinal();
        if (iOrdinal == 0) {
            return 0;
        }
        int i = 1;
        if (iOrdinal != 1) {
            i = 2;
            if (iOrdinal != 2) {
                i = 3;
                if (iOrdinal != 3) {
                    i = 4;
                    if (iOrdinal != 4) {
                        if (iOrdinal == 5) {
                            return 5;
                        }
                        throw new IllegalArgumentException(a.a("Could not convert ", state, " to int"));
                    }
                }
            }
        }
        return i;
    }
}
