package org.java_websocket.framing;

import java.nio.ByteBuffer;
import org.java_websocket.enums.Opcode;

/* JADX INFO: loaded from: classes.dex */
public interface Framedata {
    Opcode getOpcode();

    ByteBuffer getPayloadData();

    boolean isFin();

    boolean isRSV1();

    boolean isRSV2();

    boolean isRSV3();
}
