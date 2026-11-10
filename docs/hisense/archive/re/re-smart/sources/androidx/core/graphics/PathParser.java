package androidx.core.graphics;

import android.graphics.Path;
import android.util.Log;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class PathParser {
    public static final String LOGTAG = "PathParser";

    private static class ExtractFloatResult {
        public int mEndPosition;
        public boolean mEndWithNegOrDot;
    }

    public static void addNode(ArrayList<PathDataNode> list, char cmd, float[] val) {
        list.add(new PathDataNode(cmd, val));
    }

    public static boolean canMorph(@Nullable PathDataNode[] nodesFrom, @Nullable PathDataNode[] nodesTo) {
        if (nodesFrom == null || nodesTo == null || nodesFrom.length != nodesTo.length) {
            return false;
        }
        for (int i = 0; i < nodesFrom.length; i++) {
            if (nodesFrom[i].mType != nodesTo[i].mType || nodesFrom[i].mParams.length != nodesTo[i].mParams.length) {
                return false;
            }
        }
        return true;
    }

    public static float[] copyOfRange(float[] original, int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException();
        }
        int length = original.length;
        if (start < 0 || start > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i = end - start;
        int iMin = Math.min(i, length - start);
        float[] fArr = new float[i];
        System.arraycopy(original, start, fArr, 0, iMin);
        return fArr;
    }

    public static PathDataNode[] createNodesFromPathData(String pathData) {
        if (pathData == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 1;
        int i2 = 0;
        while (i < pathData.length()) {
            int iNextStart = nextStart(pathData, i);
            String strTrim = pathData.substring(i2, iNextStart).trim();
            if (strTrim.length() > 0) {
                addNode(arrayList, strTrim.charAt(0), getFloats(strTrim));
            }
            i2 = iNextStart;
            i = iNextStart + 1;
        }
        if (i - i2 == 1 && i2 < pathData.length()) {
            addNode(arrayList, pathData.charAt(i2), new float[0]);
        }
        return (PathDataNode[]) arrayList.toArray(new PathDataNode[arrayList.size()]);
    }

    public static Path createPathFromPathData(String pathData) {
        Path path = new Path();
        PathDataNode[] pathDataNodeArrCreateNodesFromPathData = createNodesFromPathData(pathData);
        if (pathDataNodeArrCreateNodesFromPathData == null) {
            return null;
        }
        try {
            PathDataNode.nodesToPath(pathDataNodeArrCreateNodesFromPathData, path);
            return path;
        } catch (RuntimeException e2) {
            throw new RuntimeException(a.a("Error in parsing ", pathData), e2);
        }
    }

    public static PathDataNode[] deepCopyNodes(PathDataNode[] source) {
        if (source == null) {
            return null;
        }
        PathDataNode[] pathDataNodeArr = new PathDataNode[source.length];
        for (int i = 0; i < source.length; i++) {
            pathDataNodeArr[i] = new PathDataNode(source[i]);
        }
        return pathDataNodeArr;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:21:0x0035  */
    public static void extract(String s, int start, ExtractFloatResult result) {
        result.mEndWithNegOrDot = false;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (int i = start; i < s.length(); i++) {
            char cCharAt = s.charAt(i);
            if (cCharAt == ' ') {
                z = false;
                z3 = true;
            } else if (cCharAt != 'E' && cCharAt != 'e') {
                switch (cCharAt) {
                    case ',':
                        z = false;
                        z3 = true;
                        break;
                    case '-':
                        if (i == start || z) {
                            z = false;
                        } else {
                            result.mEndWithNegOrDot = true;
                            z = false;
                            z3 = true;
                        }
                        break;
                    case '.':
                        if (z2) {
                            result.mEndWithNegOrDot = true;
                            z = false;
                            z3 = true;
                        } else {
                            z = false;
                            z2 = true;
                        }
                        break;
                    default:
                        z = false;
                        break;
                }
            } else {
                z = true;
            }
            if (z3) {
                result.mEndPosition = i;
            }
        }
        result.mEndPosition = i;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x004a  */
    /* JADX WARN: Code duplicated, block: B:27:0x0050 A[PHI: r7
      0x0050: PHI (r7v3 boolean) = (r7v1 boolean), (r7v1 boolean), (r7v5 boolean) binds: [B:13:0x0033, B:18:0x003d, B:24:0x004a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:32:0x005a A[Catch: NumberFormatException -> 0x0073, TryCatch #0 {NumberFormatException -> 0x0073, blocks: (B:7:0x0013, B:10:0x0027, B:12:0x002d, B:18:0x003d, B:30:0x0055, B:32:0x005a, B:35:0x006b, B:36:0x006e), top: B:43:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:46:0x006b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:47:0x0069 A[SYNTHETIC] */
    public static float[] getFloats(String s) {
        if (s.charAt(0) == 'z' || s.charAt(0) == 'Z') {
            return new float[0];
        }
        try {
            float[] fArr = new float[s.length()];
            int length = s.length();
            int i = 0;
            int i2 = 1;
            while (i2 < length) {
                boolean z = false;
                boolean z2 = false;
                boolean z3 = false;
                boolean z4 = false;
                for (int i3 = i2; i3 < s.length(); i3++) {
                    char cCharAt = s.charAt(i3);
                    if (cCharAt == ' ') {
                        z3 = false;
                        z4 = true;
                    } else if (cCharAt != 'E' && cCharAt != 'e') {
                        switch (cCharAt) {
                            case ',':
                                z3 = false;
                                z4 = true;
                                break;
                            case '-':
                                if (i3 == i2 || z3) {
                                    z3 = false;
                                } else {
                                    z = true;
                                    z3 = false;
                                    z4 = true;
                                }
                                break;
                            case '.':
                                if (z2) {
                                    z = true;
                                    z3 = false;
                                    z4 = true;
                                } else {
                                    z3 = false;
                                    z2 = true;
                                }
                                break;
                            default:
                                z3 = false;
                                break;
                        }
                    } else {
                        z3 = true;
                    }
                    if (z4) {
                        if (i2 < i3) {
                            fArr[i] = Float.parseFloat(s.substring(i2, i3));
                            i++;
                        }
                        i2 = z ? i3 : i3 + 1;
                    }
                }
                if (i2 < i3) {
                    fArr[i] = Float.parseFloat(s.substring(i2, i3));
                    i++;
                }
                if (z) {
                }
            }
            return copyOfRange(fArr, 0, i);
        } catch (NumberFormatException e2) {
            throw new RuntimeException(a.a("error in parsing \"", s, "\""), e2);
        }
    }

    public static boolean interpolatePathDataNodes(PathDataNode[] target, PathDataNode[] from, PathDataNode[] to, float fraction) {
        if (target == null || from == null || to == null) {
            throw new IllegalArgumentException("The nodes to be interpolated and resulting nodes cannot be null");
        }
        if (target.length != from.length || from.length != to.length) {
            throw new IllegalArgumentException("The nodes to be interpolated and resulting nodes must have the same length");
        }
        if (!canMorph(from, to)) {
            return false;
        }
        for (int i = 0; i < target.length; i++) {
            target[i].interpolatePathDataNode(from[i], to[i], fraction);
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0018  */
    public static int nextStart(String s, int end) {
        while (end < s.length()) {
            char cCharAt = s.charAt(end);
            if ((cCharAt - 'Z') * (cCharAt - 'A') > 0) {
                if ((cCharAt - 'z') * (cCharAt - 'a') > 0) {
                    continue;
                } else if (cCharAt != 'e' && cCharAt != 'E') {
                    return end;
                }
            } else if (cCharAt != 'e') {
                continue;
            }
            end++;
        }
        return end;
    }

    public static void updateNodes(PathDataNode[] target, PathDataNode[] source) {
        for (int i = 0; i < source.length; i++) {
            target[i].mType = source[i].mType;
            for (int i2 = 0; i2 < source[i].mParams.length; i2++) {
                target[i].mParams[i2] = source[i].mParams[i2];
            }
        }
    }

    public static class PathDataNode {
        public float[] mParams;
        public char mType;

        public PathDataNode(char type, float[] params) {
            this.mType = type;
            this.mParams = params;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public static void addCommand(Path path, float[] fArr, char c2, char c3, float[] fArr2) {
            int i;
            int i2;
            int i3;
            float f2;
            float f3;
            float f4;
            float f5;
            float f6;
            float f7;
            float f8;
            float f9;
            char c4 = c3;
            boolean z = false;
            float f10 = fArr[0];
            float f11 = fArr[1];
            float f12 = fArr[2];
            float f13 = fArr[3];
            float f14 = fArr[4];
            float f15 = fArr[5];
            switch (c4) {
                case 'A':
                case 'a':
                    i = 7;
                    i2 = i;
                    break;
                case 'C':
                case 'c':
                    i = 6;
                    i2 = i;
                    break;
                case 'H':
                case 'V':
                case 'h':
                case 'v':
                    i2 = 1;
                    break;
                case 'L':
                case 'M':
                case 'T':
                case 'l':
                case 'm':
                case 't':
                default:
                    i2 = 2;
                    break;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    i2 = 4;
                    break;
                case 'Z':
                case 'z':
                    path.close();
                    path.moveTo(f14, f15);
                    f10 = f14;
                    f12 = f10;
                    f11 = f15;
                    f13 = f11;
                    i2 = 2;
                    break;
            }
            float f16 = f10;
            float f17 = f11;
            float f18 = f14;
            float f19 = f15;
            int i4 = 0;
            char c5 = c2;
            while (i4 < fArr2.length) {
                if (c4 != 'A') {
                    if (c4 == 'C') {
                        i3 = i4;
                        int i5 = i3 + 2;
                        int i6 = i3 + 3;
                        int i7 = i3 + 4;
                        int i8 = i3 + 5;
                        path.cubicTo(fArr2[i3 + 0], fArr2[i3 + 1], fArr2[i5], fArr2[i6], fArr2[i7], fArr2[i8]);
                        f16 = fArr2[i7];
                        float f20 = fArr2[i8];
                        float f21 = fArr2[i5];
                        float f22 = fArr2[i6];
                        f17 = f20;
                        f13 = f22;
                        f12 = f21;
                    } else if (c4 == 'H') {
                        i3 = i4;
                        int i9 = i3 + 0;
                        path.lineTo(fArr2[i9], f17);
                        f16 = fArr2[i9];
                    } else if (c4 == 'Q') {
                        i3 = i4;
                        int i10 = i3 + 0;
                        int i11 = i3 + 1;
                        int i12 = i3 + 2;
                        int i13 = i3 + 3;
                        path.quadTo(fArr2[i10], fArr2[i11], fArr2[i12], fArr2[i13]);
                        float f23 = fArr2[i10];
                        float f24 = fArr2[i11];
                        f16 = fArr2[i12];
                        f17 = fArr2[i13];
                        f12 = f23;
                        f13 = f24;
                    } else if (c4 == 'V') {
                        i3 = i4;
                        int i14 = i3 + 0;
                        path.lineTo(f16, fArr2[i14]);
                        f17 = fArr2[i14];
                    } else if (c4 != 'a') {
                        if (c4 != 'c') {
                            if (c4 == 'h') {
                                int i15 = i4 + 0;
                                path.rLineTo(fArr2[i15], 0.0f);
                                f16 += fArr2[i15];
                            } else if (c4 != 'q') {
                                if (c4 == 'v') {
                                    int i16 = i4 + 0;
                                    path.rLineTo(0.0f, fArr2[i16]);
                                    f5 = fArr2[i16];
                                } else if (c4 == 'L') {
                                    int i17 = i4 + 0;
                                    int i18 = i4 + 1;
                                    path.lineTo(fArr2[i17], fArr2[i18]);
                                    f16 = fArr2[i17];
                                    f17 = fArr2[i18];
                                } else if (c4 == 'M') {
                                    int i19 = i4 + 0;
                                    f16 = fArr2[i19];
                                    int i20 = i4 + 1;
                                    f17 = fArr2[i20];
                                    if (i4 > 0) {
                                        path.lineTo(fArr2[i19], fArr2[i20]);
                                    } else {
                                        path.moveTo(fArr2[i19], fArr2[i20]);
                                        f19 = f17;
                                        f18 = f16;
                                    }
                                } else if (c4 == 'S') {
                                    if (c5 == 'c' || c5 == 's' || c5 == 'C' || c5 == 'S') {
                                        f16 = (f16 * 2.0f) - f12;
                                        f17 = (f17 * 2.0f) - f13;
                                    }
                                    float f25 = f17;
                                    int i21 = i4 + 0;
                                    int i22 = i4 + 1;
                                    int i23 = i4 + 2;
                                    int i24 = i4 + 3;
                                    path.cubicTo(f16, f25, fArr2[i21], fArr2[i22], fArr2[i23], fArr2[i24]);
                                    f2 = fArr2[i21];
                                    f3 = fArr2[i22];
                                    f16 = fArr2[i23];
                                    f17 = fArr2[i24];
                                    f12 = f2;
                                    f13 = f3;
                                } else if (c4 == 'T') {
                                    if (c5 == 'q' || c5 == 't' || c5 == 'Q' || c5 == 'T') {
                                        f16 = (f16 * 2.0f) - f12;
                                        f17 = (f17 * 2.0f) - f13;
                                    }
                                    int i25 = i4 + 0;
                                    int i26 = i4 + 1;
                                    path.quadTo(f16, f17, fArr2[i25], fArr2[i26]);
                                    float f26 = fArr2[i25];
                                    float f27 = fArr2[i26];
                                    f13 = f17;
                                    f12 = f16;
                                    i3 = i4;
                                    f16 = f26;
                                    f17 = f27;
                                } else if (c4 == 'l') {
                                    int i27 = i4 + 0;
                                    int i28 = i4 + 1;
                                    path.rLineTo(fArr2[i27], fArr2[i28]);
                                    f16 += fArr2[i27];
                                    f5 = fArr2[i28];
                                } else if (c4 == 'm') {
                                    int i29 = i4 + 0;
                                    f16 += fArr2[i29];
                                    int i30 = i4 + 1;
                                    f17 += fArr2[i30];
                                    if (i4 > 0) {
                                        path.rLineTo(fArr2[i29], fArr2[i30]);
                                    } else {
                                        path.rMoveTo(fArr2[i29], fArr2[i30]);
                                        f19 = f17;
                                        f18 = f16;
                                    }
                                } else if (c4 == 's') {
                                    if (c5 == 'c' || c5 == 's' || c5 == 'C' || c5 == 'S') {
                                        float f28 = f16 - f12;
                                        f6 = f17 - f13;
                                        f7 = f28;
                                    } else {
                                        f7 = 0.0f;
                                        f6 = 0.0f;
                                    }
                                    int i31 = i4 + 0;
                                    int i32 = i4 + 1;
                                    int i33 = i4 + 2;
                                    int i34 = i4 + 3;
                                    path.rCubicTo(f7, f6, fArr2[i31], fArr2[i32], fArr2[i33], fArr2[i34]);
                                    f2 = fArr2[i31] + f16;
                                    f3 = fArr2[i32] + f17;
                                    f16 += fArr2[i33];
                                    f4 = fArr2[i34];
                                } else if (c4 == 't') {
                                    if (c5 == 'q' || c5 == 't' || c5 == 'Q' || c5 == 'T') {
                                        f8 = f16 - f12;
                                        f9 = f17 - f13;
                                    } else {
                                        f9 = 0.0f;
                                        f8 = 0.0f;
                                    }
                                    int i35 = i4 + 0;
                                    int i36 = i4 + 1;
                                    path.rQuadTo(f8, f9, fArr2[i35], fArr2[i36]);
                                    float f29 = f8 + f16;
                                    float f30 = f9 + f17;
                                    f16 += fArr2[i35];
                                    f17 += fArr2[i36];
                                    f13 = f30;
                                    f12 = f29;
                                }
                                f17 += f5;
                            } else {
                                int i37 = i4 + 0;
                                int i38 = i4 + 1;
                                int i39 = i4 + 2;
                                int i40 = i4 + 3;
                                path.rQuadTo(fArr2[i37], fArr2[i38], fArr2[i39], fArr2[i40]);
                                f2 = fArr2[i37] + f16;
                                f3 = fArr2[i38] + f17;
                                f16 += fArr2[i39];
                                f4 = fArr2[i40];
                            }
                            i3 = i4;
                        } else {
                            int i41 = i4 + 2;
                            int i42 = i4 + 3;
                            int i43 = i4 + 4;
                            int i44 = i4 + 5;
                            path.rCubicTo(fArr2[i4 + 0], fArr2[i4 + 1], fArr2[i41], fArr2[i42], fArr2[i43], fArr2[i44]);
                            f2 = fArr2[i41] + f16;
                            f3 = fArr2[i42] + f17;
                            f16 += fArr2[i43];
                            f4 = fArr2[i44];
                        }
                        f17 += f4;
                        f12 = f2;
                        f13 = f3;
                        i3 = i4;
                    } else {
                        int i45 = i4 + 5;
                        float f31 = fArr2[i45] + f16;
                        int i46 = i4 + 6;
                        float f32 = fArr2[i46] + f17;
                        float f33 = fArr2[i4 + 0];
                        float f34 = fArr2[i4 + 1];
                        float f35 = fArr2[i4 + 2];
                        float f36 = f16;
                        i3 = i4;
                        drawArc(path, f16, f17, f31, f32, f33, f34, f35, fArr2[i4 + 3] != 0.0f, fArr2[i4 + 4] != 0.0f);
                        f16 = f36 + fArr2[i45];
                        f17 += fArr2[i46];
                    }
                    i4 = i3 + i2;
                    c5 = c3;
                    c4 = c5;
                    z = false;
                } else {
                    i3 = i4;
                    int i47 = i3 + 5;
                    int i48 = i3 + 6;
                    drawArc(path, f16, f17, fArr2[i47], fArr2[i48], fArr2[i3 + 0], fArr2[i3 + 1], fArr2[i3 + 2], fArr2[i3 + 3] != 0.0f, fArr2[i3 + 4] != 0.0f);
                    f16 = fArr2[i47];
                    f17 = fArr2[i48];
                }
                f13 = f17;
                f12 = f16;
                i4 = i3 + i2;
                c5 = c3;
                c4 = c5;
                z = false;
            }
            fArr[z ? 1 : 0] = f16;
            fArr[1] = f17;
            fArr[2] = f12;
            fArr[3] = f13;
            fArr[4] = f18;
            fArr[5] = f19;
        }

        public static void arcToBezier(Path p, double cx, double cy, double a2, double b2, double e1x, double e1y, double theta, double start, double sweep) {
            double d2 = a2;
            int iCeil = (int) Math.ceil(Math.abs((sweep * 4.0d) / 3.141592653589793d));
            double dCos = Math.cos(theta);
            double dSin = Math.sin(theta);
            double dCos2 = Math.cos(start);
            double dSin2 = Math.sin(start);
            double d3 = -d2;
            double d4 = d3 * dCos;
            double d5 = b2 * dSin;
            double d6 = (d4 * dSin2) - (d5 * dCos2);
            double d7 = d3 * dSin;
            double d8 = b2 * dCos;
            double d9 = (dCos2 * d8) + (dSin2 * d7);
            double d10 = sweep / ((double) iCeil);
            double d11 = e1y;
            double d12 = d9;
            double d13 = d6;
            int i = 0;
            double d14 = e1x;
            double d15 = start;
            while (i < iCeil) {
                double d16 = d15 + d10;
                double dSin3 = Math.sin(d16);
                double dCos3 = Math.cos(d16);
                double d17 = (((d2 * dCos) * dCos3) + cx) - (d5 * dSin3);
                double d18 = (d8 * dSin3) + (d2 * dSin * dCos3) + cy;
                double d19 = (d4 * dSin3) - (d5 * dCos3);
                double d20 = (dCos3 * d8) + (dSin3 * d7);
                double d21 = d16 - d15;
                double dTan = Math.tan(d21 / 2.0d);
                double dSqrt = ((Math.sqrt(((dTan * 3.0d) * dTan) + 4.0d) - 1.0d) * Math.sin(d21)) / 3.0d;
                p.rLineTo(0.0f, 0.0f);
                p.cubicTo((float) ((d13 * dSqrt) + d14), (float) ((d12 * dSqrt) + d11), (float) (d17 - (dSqrt * d19)), (float) (d18 - (dSqrt * d20)), (float) d17, (float) d18);
                i++;
                d10 = d10;
                iCeil = iCeil;
                dSin = dSin;
                d11 = d18;
                dCos = dCos;
                d7 = d7;
                d15 = d16;
                d12 = d20;
                d13 = d19;
                d2 = a2;
                d14 = d17;
            }
        }

        public static void drawArc(Path p, float x0, float y0, float x1, float y1, float a2, float b2, float theta, boolean isMoreThanHalf, boolean isPositiveArc) {
            double d2;
            double d3;
            double radians = Math.toRadians(theta);
            double dCos = Math.cos(radians);
            double dSin = Math.sin(radians);
            double d4 = x0;
            double d5 = d4 * dCos;
            double d6 = y0;
            double d7 = a2;
            double d8 = ((d6 * dSin) + d5) / d7;
            double d9 = b2;
            double d10 = ((d6 * dCos) + (((double) (-x0)) * dSin)) / d9;
            double d11 = y1;
            double d12 = ((d11 * dSin) + (((double) x1) * dCos)) / d7;
            double d13 = ((d11 * dCos) + (((double) (-x1)) * dSin)) / d9;
            double d14 = d8 - d12;
            double d15 = d10 - d13;
            double d16 = (d8 + d12) / 2.0d;
            double d17 = (d10 + d13) / 2.0d;
            double d18 = (d15 * d15) + (d14 * d14);
            if (d18 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                Log.w(PathParser.LOGTAG, " Points are coincident");
                return;
            }
            double d19 = (1.0d / d18) - 0.25d;
            if (d19 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                Log.w(PathParser.LOGTAG, "Points are too far apart " + d18);
                float fSqrt = (float) (Math.sqrt(d18) / 1.99999d);
                drawArc(p, x0, y0, x1, y1, a2 * fSqrt, b2 * fSqrt, theta, isMoreThanHalf, isPositiveArc);
                return;
            }
            double dSqrt = Math.sqrt(d19);
            double d20 = d14 * dSqrt;
            double d21 = dSqrt * d15;
            if (isMoreThanHalf == isPositiveArc) {
                d2 = d16 - d21;
                d3 = d17 + d20;
            } else {
                d2 = d16 + d21;
                d3 = d17 - d20;
            }
            double dAtan2 = Math.atan2(d10 - d3, d8 - d2);
            double dAtan3 = Math.atan2(d13 - d3, d12 - d2) - dAtan2;
            if (isPositiveArc != (dAtan3 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
                dAtan3 = dAtan3 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? dAtan3 - 6.283185307179586d : dAtan3 + 6.283185307179586d;
            }
            double d22 = d2 * d7;
            double d23 = d3 * d9;
            arcToBezier(p, (d22 * dCos) - (d23 * dSin), (d23 * dCos) + (d22 * dSin), d7, d9, d4, d6, radians, dAtan2, dAtan3);
        }

        public static void nodesToPath(PathDataNode[] node, Path path) {
            float[] fArr = new float[6];
            char c2 = 'm';
            for (int i = 0; i < node.length; i++) {
                addCommand(path, fArr, c2, node[i].mType, node[i].mParams);
                c2 = node[i].mType;
            }
        }

        public void interpolatePathDataNode(PathDataNode nodeFrom, PathDataNode nodeTo, float fraction) {
            this.mType = nodeFrom.mType;
            int i = 0;
            while (true) {
                float[] fArr = nodeFrom.mParams;
                if (i >= fArr.length) {
                    return;
                }
                this.mParams[i] = (nodeTo.mParams[i] * fraction) + ((1.0f - fraction) * fArr[i]);
                i++;
            }
        }

        public PathDataNode(PathDataNode n) {
            this.mType = n.mType;
            float[] fArr = n.mParams;
            this.mParams = PathParser.copyOfRange(fArr, 0, fArr.length);
        }
    }
}
