package b.d.a.a.c;

import b.d.a.a.j;
import java.io.IOException;
import java.io.Reader;

/* JADX INFO: loaded from: classes.dex */
public class f extends b.d.a.a.a.b {
    public static final int[] F = b.d.a.a.b.a.f84c;
    public Reader G;
    public char[] H;
    public boolean I;
    public final b.d.a.a.d.b J;
    public final int K;
    public boolean L;

    public f(b.d.a.a.b.c cVar, int i, Reader reader, b.d.a.a.d.b bVar, char[] cArr, int i2, int i3, boolean z) {
        super(cVar, i);
        this.L = false;
        this.G = reader;
        this.H = cArr;
        this.m = i2;
        this.n = i3;
        this.J = bVar;
        this.K = bVar.f169c;
        this.I = z;
    }

    @Override // b.d.a.a.a.b
    public void A() {
        char[] cArr;
        b.d.a.a.d.b bVar;
        super.A();
        b.d.a.a.d.b bVar2 = this.J;
        if (bVar2.l && (bVar = bVar2.f168b) != null && bVar2.f171e) {
            bVar.a(bVar2);
            bVar2.l = false;
        }
        if (!this.I || (cArr = this.H) == null) {
            return;
        }
        this.H = null;
        this.k.c(cArr);
    }

    @Override // b.d.a.a.a.b
    public boolean B() throws IOException {
        long j = this.o;
        int i = this.n;
        this.o = j + ((long) i);
        this.q -= i;
        Reader reader = this.G;
        if (reader != null) {
            char[] cArr = this.H;
            int i2 = reader.read(cArr, 0, cArr.length);
            if (i2 > 0) {
                this.m = 0;
                this.n = i2;
                return true;
            }
            if (this.G != null) {
                if (this.k.f90c || a(b.d.a.a.g.a.AUTO_CLOSE_SOURCE)) {
                    this.G.close();
                }
                this.G = null;
            }
            if (i2 == 0) {
                StringBuilder sbA = b.a.a.a.a.a("Reader returned 0 characters when trying to read ");
                sbA.append(this.n);
                throw new IOException(sbA.toString());
            }
        }
        return false;
    }

    public char F() throws b.d.a.a.f {
        if (this.m >= this.n && !B()) {
            c(" in character escape sequence");
            throw null;
        }
        char[] cArr = this.H;
        int i = this.m;
        this.m = i + 1;
        char c2 = cArr[i];
        if (c2 == '\"' || c2 == '/' || c2 == '\\') {
            return c2;
        }
        if (c2 == 'b') {
            return '\b';
        }
        if (c2 == 'f') {
            return '\f';
        }
        if (c2 == 'n') {
            return '\n';
        }
        if (c2 == 'r') {
            return '\r';
        }
        if (c2 == 't') {
            return '\t';
        }
        if (c2 != 'u') {
            a(c2);
            return c2;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            if (this.m >= this.n && !B()) {
                c(" in character escape sequence");
                throw null;
            }
            char[] cArr2 = this.H;
            int i4 = this.m;
            this.m = i4 + 1;
            char c3 = cArr2[i4];
            int iA = b.d.a.a.b.a.a(c3);
            if (iA < 0) {
                a(c3, "expected a hex-digit for character escape sequence");
                throw null;
            }
            i2 = (i2 << 4) | iA;
        }
        return (char) i2;
    }

    public final int G() throws b.d.a.a.f {
        while (true) {
            if (this.m >= this.n && !B()) {
                StringBuilder sbA = b.a.a.a.a.a("Unexpected end-of-input within/between ");
                sbA.append(this.t.b());
                sbA.append(" entries");
                throw b(sbA.toString());
            }
            char[] cArr = this.H;
            int i = this.m;
            this.m = i + 1;
            char c2 = cArr[i];
            if (c2 > ' ') {
                if (c2 == '/') {
                    I();
                } else if (c2 != '#' || !L()) {
                    return c2;
                }
            } else if (c2 >= ' ') {
                continue;
            } else if (c2 == '\n') {
                this.p++;
                this.q = this.m;
            } else if (c2 == '\r') {
                H();
            } else if (c2 != '\t') {
                c(c2);
                throw null;
            }
        }
    }

    public final void H() {
        if (this.m < this.n || B()) {
            char[] cArr = this.H;
            int i = this.m;
            if (cArr[i] == '\n') {
                this.m = i + 1;
            }
        }
        this.p++;
        this.q = this.m;
    }

    public final void I() throws b.d.a.a.f {
        if (!a(b.d.a.a.g.a.ALLOW_COMMENTS)) {
            a(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
            throw null;
        }
        if (this.m >= this.n && !B()) {
            c(" in a comment");
            throw null;
        }
        char[] cArr = this.H;
        int i = this.m;
        this.m = i + 1;
        char c2 = cArr[i];
        if (c2 == '/') {
            J();
            return;
        }
        if (c2 != '*') {
            a(c2, "was expecting either '*' or '/' for a comment");
            throw null;
        }
        while (true) {
            if (this.m >= this.n && !B()) {
                break;
            }
            char[] cArr2 = this.H;
            int i2 = this.m;
            this.m = i2 + 1;
            char c3 = cArr2[i2];
            if (c3 <= '*') {
                if (c3 == '*') {
                    if (this.m >= this.n && !B()) {
                        break;
                    }
                    char[] cArr3 = this.H;
                    int i3 = this.m;
                    if (cArr3[i3] == '/') {
                        this.m = i3 + 1;
                        return;
                    }
                } else if (c3 >= ' ') {
                    continue;
                } else if (c3 == '\n') {
                    this.p++;
                    this.q = this.m;
                } else if (c3 == '\r') {
                    H();
                } else if (c3 != '\t') {
                    c(c3);
                    throw null;
                }
            }
        }
        c(" in a comment");
        throw null;
    }

    public final void J() throws b.d.a.a.f {
        while (true) {
            if (this.m >= this.n && !B()) {
                return;
            }
            char[] cArr = this.H;
            int i = this.m;
            this.m = i + 1;
            char c2 = cArr[i];
            if (c2 < ' ') {
                if (c2 == '\n') {
                    this.p++;
                    this.q = this.m;
                    return;
                } else if (c2 == '\r') {
                    H();
                    return;
                } else if (c2 != '\t') {
                    c(c2);
                    throw null;
                }
            }
        }
    }

    public final int K() throws b.d.a.a.f {
        while (true) {
            if (this.m >= this.n && !B()) {
                w();
                return -1;
            }
            char[] cArr = this.H;
            int i = this.m;
            this.m = i + 1;
            char c2 = cArr[i];
            if (c2 > ' ') {
                if (c2 == '/') {
                    I();
                } else if (c2 != '#' || !L()) {
                    return c2;
                }
            } else if (c2 == ' ') {
                continue;
            } else if (c2 == '\n') {
                this.p++;
                this.q = this.m;
            } else if (c2 == '\r') {
                H();
            } else if (c2 != '\t') {
                c(c2);
                throw null;
            }
        }
    }

    public final boolean L() throws b.d.a.a.f {
        if (!a(b.d.a.a.g.a.ALLOW_YAML_COMMENTS)) {
            return false;
        }
        J();
        return true;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r8v0 ??, r8v1 ??, r8v20 ??, r8v13 ??, r8v8 ??, r8v7 ??, r8v5 ??, r8v3 ??, r8v11 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    public final b.d.a.a.j a(
    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r8v0 ??, r8v1 ??, r8v20 ??, r8v13 ??, r8v8 ??, r8v7 ??, r8v5 ??, r8v3 ??, r8v11 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r8v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:215)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:150)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:415)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
        	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
        	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:295)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:284)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:268)
        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:160)
        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:104)
        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
        	at jadx.core.ProcessClass.process(ProcessClass.java:89)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:127)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:405)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:393)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:343)
        */

    public final j b(boolean z, int i) throws b.d.a.a.f {
        int i2;
        char cF;
        boolean z2;
        int i3;
        int i4;
        int i5;
        char c2;
        if (z) {
            i++;
        }
        this.m = i;
        char[] cArrD = this.v.d();
        if (z) {
            cArrD[0] = '-';
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i6 = this.m;
        if (i6 < this.n) {
            char[] cArr = this.H;
            this.m = i6 + 1;
            cF = cArr[i6];
        } else {
            cF = f("No digit following minus sign");
        }
        if (cF == '0') {
            int i7 = this.m;
            if ((i7 < this.n && ((c2 = this.H[i7]) < '0' || c2 > '9')) || ((this.m >= this.n && !B()) || (cF = this.H[this.m]) < '0' || cF > '9')) {
                cF = '0';
                break;
            }
            if (!a(b.d.a.a.g.a.ALLOW_NUMERIC_LEADING_ZEROS)) {
                d("Leading zeroes not allowed");
                throw null;
            }
            this.m++;
            if (cF == '0') {
                do {
                    if (this.m >= this.n && !B()) {
                        break;
                    }
                    char[] cArr2 = this.H;
                    int i8 = this.m;
                    cF = cArr2[i8];
                    if (cF < '0' || cF > '9') {
                        cF = '0';
                        break;
                    }
                    this.m = i8 + 1;
                } while (cF == '0');
            }
        }
        int i9 = i2;
        char[] cArrF = cArrD;
        int i10 = 0;
        while (true) {
            if (cF >= '0' && cF <= '9') {
                i10++;
                if (i9 >= cArrF.length) {
                    cArrF = this.v.f();
                    i9 = 0;
                }
                int i11 = i9 + 1;
                cArrF[i9] = cF;
                if (this.m >= this.n && !B()) {
                    cF = 0;
                    i9 = i11;
                    z2 = true;
                    break;
                }
                char[] cArr3 = this.H;
                int i12 = this.m;
                this.m = i12 + 1;
                cF = cArr3[i12];
                i9 = i11;
            } else {
                z2 = false;
                break;
            }
        }
        if (i10 == 0) {
            StringBuilder sbA = b.a.a.a.a.a("Missing integer part (next char ");
            sbA.append(b.d.a.a.a.c.b(cF));
            sbA.append(")");
            d(sbA.toString());
            throw null;
        }
        if (cF == '.') {
            int i13 = i9 + 1;
            cArrF[i9] = cF;
            char c3 = cF;
            char[] cArrF2 = cArrF;
            int i14 = 0;
            while (true) {
                if (this.m >= this.n && !B()) {
                    z2 = true;
                    break;
                }
                char[] cArr4 = this.H;
                int i15 = this.m;
                this.m = i15 + 1;
                c3 = cArr4[i15];
                if (c3 < '0' || c3 > '9') {
                    break;
                }
                i14++;
                if (i13 >= cArrF2.length) {
                    cArrF2 = this.v.f();
                    i13 = 0;
                }
                cArrF2[i13] = c3;
                i13++;
            }
            if (i14 == 0) {
                c(c3, "Decimal point not followed by a digit");
                throw null;
            }
            i3 = i14;
            cArrF = cArrF2;
            cF = c3;
            i9 = i13;
        } else {
            i3 = 0;
        }
        if (cF == 'e' || cF == 'E') {
            if (i9 >= cArrF.length) {
                cArrF = this.v.f();
                i9 = 0;
            }
            int i16 = i9 + 1;
            cArrF[i9] = cF;
            int i17 = this.m;
            if (i17 < this.n) {
                char[] cArr5 = this.H;
                this.m = i17 + 1;
                cF = cArr5[i17];
            } else {
                cF = f("expected a digit for number exponent");
            }
            if (cF == '-' || cF == '+') {
                if (i16 >= cArrF.length) {
                    cArrF = this.v.f();
                    i16 = 0;
                }
                i4 = i16 + 1;
                cArrF[i16] = cF;
                int i18 = this.m;
                if (i18 < this.n) {
                    char[] cArr6 = this.H;
                    this.m = i18 + 1;
                    cF = cArr6[i18];
                } else {
                    cF = f("expected a digit for number exponent");
                }
            } else {
                i4 = i16;
            }
            char[] cArrF3 = cArrF;
            i5 = 0;
            while (true) {
                if (cF <= '9' && cF >= '0') {
                    i5++;
                    if (i4 >= cArrF3.length) {
                        cArrF3 = this.v.f();
                        i4 = 0;
                    }
                    int i19 = i4 + 1;
                    cArrF3[i4] = cF;
                    if (this.m >= this.n && !B()) {
                        z2 = true;
                        i9 = i19;
                        break;
                    }
                    char[] cArr7 = this.H;
                    int i20 = this.m;
                    this.m = i20 + 1;
                    cF = cArr7[i20];
                    i4 = i19;
                } else {
                    i9 = i4;
                    break;
                }
            }
            if (i5 == 0) {
                c(cF, "Exponent indicator not followed by a digit");
                throw null;
            }
        } else {
            i5 = 0;
        }
        if (!z2) {
            this.m--;
            if (this.t.e()) {
                e(cF);
            }
        }
        this.v.j = i9;
        if (i3 >= 1 || i5 >= 1) {
            this.D = z;
            this.E = i10;
            this.x = 0;
            return j.VALUE_NUMBER_FLOAT;
        }
        this.D = z;
        this.E = i10;
        this.x = 0;
        return j.VALUE_NUMBER_INT;
    }

    public final void e(int i) throws b.d.a.a.f {
        this.m++;
        if (i != 9) {
            if (i == 10) {
                this.p++;
                this.q = this.m;
            } else if (i == 13) {
                H();
            } else {
                if (i == 32) {
                    return;
                }
                a(i, "Expected space separating root-level values");
                throw null;
            }
        }
    }

    public char f(String str) throws b.d.a.a.f {
        if (this.m >= this.n && !B()) {
            c(str);
            throw null;
        }
        char[] cArr = this.H;
        int i = this.m;
        this.m = i + 1;
        return cArr[i];
    }

    /* JADX WARN: Code duplicated, block: B:19:0x004f  */
    /* JADX WARN: Code duplicated, block: B:20:0x0053  */
    /* JADX WARN: Code duplicated, block: B:22:0x0057  */
    /* JADX WARN: Code duplicated, block: B:25:0x0065  */
    /* JADX WARN: Code duplicated, block: B:28:0x0074  */
    /* JADX WARN: Code duplicated, block: B:29:0x007d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:30:0x007f  */
    /* JADX WARN: Code duplicated, block: B:47:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:49:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:50:0x00de  */
    /* JADX WARN: Code duplicated, block: B:52:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:55:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:77:0x00d0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:0x00f1 A[SYNTHETIC] */
    @Override // b.d.a.a.g
    public final String s() throws b.d.a.a.f {
        b.d.a.a.e.f fVar;
        char[] cArr;
        int i;
        int i2;
        char[] cArr2;
        int i3;
        int i4;
        char[] cArrG;
        int i5;
        int length;
        char cF;
        j jVar = this.f70b;
        if (jVar != j.VALUE_STRING) {
            if (jVar == null) {
                return null;
            }
            int i6 = jVar.r;
            if (i6 != 5) {
                return (i6 == 6 || i6 == 7 || i6 == 8) ? this.v.c() : jVar.a();
            }
            return this.t.f139f;
        }
        if (this.L) {
            this.L = false;
            int i7 = this.m;
            int i8 = this.n;
            if (i7 < i8) {
                int[] iArr = F;
                int length2 = iArr.length;
                do {
                    char[] cArr3 = this.H;
                    char c2 = cArr3[i7];
                    if (c2 >= length2 || iArr[c2] == 0) {
                        i7++;
                    } else if (c2 == '\"') {
                        b.d.a.a.e.f fVar2 = this.v;
                        int i9 = this.m;
                        fVar2.a(cArr3, i9, i7 - i9);
                        this.m = i7 + 1;
                    }
                } while (i7 < i8);
                fVar = this.v;
                cArr = this.H;
                i = this.m;
                i2 = i7 - i;
                fVar.f208c = null;
                fVar.f209d = -1;
                fVar.f210e = 0;
                fVar.k = null;
                fVar.l = null;
                if (fVar.g) {
                    fVar.a();
                } else if (fVar.i == null) {
                    fVar.i = fVar.a(i2);
                }
                fVar.h = 0;
                fVar.j = 0;
                if (fVar.f209d >= 0) {
                    fVar.c(i2);
                }
                fVar.k = null;
                fVar.l = null;
                cArr2 = fVar.i;
                int length3 = cArr2.length;
                i3 = fVar.j;
                i4 = length3 - i3;
                if (i4 >= i2) {
                    System.arraycopy(cArr, i, cArr2, i3, i2);
                    fVar.j += i2;
                } else {
                    if (i4 > 0) {
                        System.arraycopy(cArr, i, cArr2, i3, i4);
                        i += i4;
                        i2 -= i4;
                    }
                    do {
                        fVar.b(i2);
                        int iMin = Math.min(fVar.i.length, i2);
                        System.arraycopy(cArr, i, fVar.i, 0, iMin);
                        fVar.j += iMin;
                        i += iMin;
                        i2 -= iMin;
                    } while (i2 > 0);
                }
                this.m = i7;
                cArrG = this.v.g();
                i5 = this.v.j;
                int[] iArr2 = F;
                length = iArr2.length;
                while (true) {
                    if (this.m < this.n && !B()) {
                        c(": was expecting closing quote for a string value");
                        throw null;
                    }
                    char[] cArr4 = this.H;
                    int i10 = this.m;
                    this.m = i10 + 1;
                    cF = cArr4[i10];
                    if (cF < length && iArr2[cF] != 0) {
                        if (cF == '\"') {
                            this.v.j = i5;
                        } else if (cF == '\\') {
                            cF = F();
                        } else if (cF < ' ') {
                            b(cF, "string value");
                        }
                    }
                    if (i5 >= cArrG.length) {
                        cArrG = this.v.f();
                        i5 = 0;
                    }
                    cArrG[i5] = cF;
                    i5++;
                }
            } else {
                fVar = this.v;
                cArr = this.H;
                i = this.m;
                i2 = i7 - i;
                fVar.f208c = null;
                fVar.f209d = -1;
                fVar.f210e = 0;
                fVar.k = null;
                fVar.l = null;
                if (fVar.g) {
                    fVar.a();
                } else if (fVar.i == null) {
                    fVar.i = fVar.a(i2);
                }
                fVar.h = 0;
                fVar.j = 0;
                if (fVar.f209d >= 0) {
                    fVar.c(i2);
                }
                fVar.k = null;
                fVar.l = null;
                cArr2 = fVar.i;
                int length4 = cArr2.length;
                i3 = fVar.j;
                i4 = length4 - i3;
                if (i4 >= i2) {
                    System.arraycopy(cArr, i, cArr2, i3, i2);
                    fVar.j += i2;
                } else {
                    if (i4 > 0) {
                        System.arraycopy(cArr, i, cArr2, i3, i4);
                        i += i4;
                        i2 -= i4;
                    }
                    do {
                        fVar.b(i2);
                        int iMin2 = Math.min(fVar.i.length, i2);
                        System.arraycopy(cArr, i, fVar.i, 0, iMin2);
                        fVar.j += iMin2;
                        i += iMin2;
                        i2 -= iMin2;
                    } while (i2 > 0);
                }
                this.m = i7;
                cArrG = this.v.g();
                i5 = this.v.j;
                int[] iArr3 = F;
                length = iArr3.length;
                while (true) {
                    if (this.m < this.n) {
                    }
                    char[] cArr5 = this.H;
                    int i11 = this.m;
                    this.m = i11 + 1;
                    cF = cArr5[i11];
                    if (cF < length) {
                        if (cF == '\"') {
                            this.v.j = i5;
                        } else if (cF == '\\') {
                            cF = F();
                        } else if (cF < ' ') {
                            b(cF, "string value");
                        }
                    }
                    if (i5 >= cArrG.length) {
                        cArrG = this.v.f();
                        i5 = 0;
                    }
                    cArrG[i5] = cF;
                    i5++;
                }
            }
        }
        return this.v.c();
    }

    /* JADX WARN: Code duplicated, block: B:164:0x02a6  */
    /* JADX WARN: Code duplicated, block: B:169:0x02cd  */
    /* JADX WARN: Code duplicated, block: B:172:0x02d4  */
    /* JADX WARN: Code duplicated, block: B:174:0x02dc  */
    /* JADX WARN: Code duplicated, block: B:177:0x02e1  */
    /* JADX WARN: Code duplicated, block: B:229:0x03c6  */
    /* JADX WARN: Code duplicated, block: B:230:0x03ce  */
    /* JADX WARN: Code duplicated, block: B:328:0x053b  */
    /* JADX WARN: Code duplicated, block: B:330:0x0550  */
    /* JADX WARN: Code duplicated, block: B:350:0x0595  */
    /* JADX WARN: Code duplicated, block: B:367:0x05ca  */
    /* JADX WARN: Code duplicated, block: B:386:0x0606  */
    /* JADX WARN: Code duplicated, block: B:458:0x02ad A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:460:? A[LOOP:4: B:156:0x0279->B:460:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:462:0x02e7 A[EDGE_INSN: B:462:0x02e7->B:179:0x02e7 BREAK  A[LOOP:5: B:167:0x02c7->B:231:0x03cf], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:463:0x02e7 A[EDGE_INSN: B:463:0x02e7->B:179:0x02e7 BREAK  A[LOOP:5: B:167:0x02c7->B:231:0x03cf], SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v165, types: [int] */
    /* JADX WARN: Type inference failed for: r0v166, types: [char, int] */
    /* JADX WARN: Type inference failed for: r0v169, types: [int] */
    /* JADX WARN: Type inference failed for: r0v18, types: [int] */
    /* JADX WARN: Type inference failed for: r0v180 */
    /* JADX WARN: Type inference failed for: r0v19, types: [int] */
    /* JADX WARN: Type inference failed for: r0v191 */
    /* JADX WARN: Type inference failed for: r0v192 */
    /* JADX WARN: Type inference failed for: r0v193 */
    /* JADX WARN: Type inference failed for: r0v194 */
    /* JADX WARN: Type inference failed for: r0v195 */
    /* JADX WARN: Type inference failed for: r0v196 */
    /* JADX WARN: Type inference failed for: r0v197 */
    /* JADX WARN: Type inference failed for: r0v198 */
    /* JADX WARN: Type inference failed for: r0v199 */
    /* JADX WARN: Type inference failed for: r0v20, types: [int] */
    /* JADX WARN: Type inference failed for: r0v200 */
    /* JADX WARN: Type inference failed for: r0v201 */
    /* JADX WARN: Type inference failed for: r0v202 */
    /* JADX WARN: Type inference failed for: r0v203 */
    /* JADX WARN: Type inference failed for: r0v21, types: [int] */
    /* JADX WARN: Type inference failed for: r0v3, types: [char, int] */
    /* JADX WARN: Type inference failed for: r0v5, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6, types: [char, int] */
    /* JADX WARN: Type inference failed for: r0v9, types: [int] */
    /* JADX WARN: Type inference failed for: r17v0, types: [b.d.a.a.a.b, b.d.a.a.a.c, b.d.a.a.c.f, b.d.a.a.g] */
    @Override // b.d.a.a.g
    public final j u() throws IOException {
        char cK;
        j jVarA;
        int i;
        char c2;
        int i2;
        char c3;
        int i3;
        char c4;
        char[] cArrG;
        int i4;
        int length;
        char c5;
        int i5;
        String strA;
        this.x = 0;
        if (this.f70b == j.FIELD_NAME) {
            j jVar = this.u;
            this.u = null;
            if (jVar == j.START_ARRAY) {
                this.t = this.t.a(this.r, this.s);
            } else if (jVar == j.START_OBJECT) {
                this.t = this.t.b(this.r, this.s);
            }
            this.f70b = jVar;
            return jVar;
        }
        if (this.L) {
            this.L = false;
            int i6 = this.m;
            int i7 = this.n;
            char[] cArr = this.H;
            while (true) {
                if (i6 >= i7) {
                    this.m = i6;
                    if (!B()) {
                        c(": was expecting closing quote for a string value");
                        throw null;
                    }
                    i6 = this.m;
                    i7 = this.n;
                }
                int i8 = i6 + 1;
                char c6 = cArr[i6];
                if (c6 <= '\\') {
                    if (c6 == '\\') {
                        this.m = i8;
                        F();
                        i6 = this.m;
                        i7 = this.n;
                    } else if (c6 <= '\"') {
                        if (c6 == '\"') {
                            this.m = i8;
                            break;
                        }
                        if (c6 < ' ') {
                            this.m = i8;
                            b(c6, "string value");
                        }
                    }
                }
                i6 = i8;
            }
        }
        char c7 = '/';
        if (this.m < this.n || B()) {
            char[] cArr2 = this.H;
            int i9 = this.m;
            this.m = i9 + 1;
            cK = cArr2[i9];
            if (cK <= 32) {
                if (cK != 32) {
                    if (cK == 10) {
                        this.p++;
                        this.q = this.m;
                    } else if (cK == 13) {
                        H();
                    } else if (cK != 9) {
                        c(cK);
                        throw null;
                    }
                }
                while (true) {
                    int i10 = this.m;
                    if (i10 >= this.n) {
                        cK = K();
                        break;
                    }
                    char[] cArr3 = this.H;
                    this.m = i10 + 1;
                    cK = cArr3[i10];
                    if (cK > 32) {
                        if (cK != 47 && cK != 35) {
                            break;
                        }
                        this.m--;
                        cK = K();
                        break;
                    }
                    if (cK != 32) {
                        if (cK == 10) {
                            this.p++;
                            this.q = this.m;
                        } else if (cK == 13) {
                            H();
                        } else if (cK != 9) {
                            c(cK);
                            throw null;
                        }
                    }
                }
            } else if (cK == 47 || cK == 35) {
                this.m--;
                cK = K();
            }
        } else {
            w();
            cK = -1;
        }
        if (cK < 0) {
            close();
            this.f70b = null;
            return null;
        }
        long j = this.o;
        int i11 = this.m;
        this.r = this.p;
        this.s = (i11 - this.q) - 1;
        if (cK == 93) {
            if (!this.t.c()) {
                a(cK, '}');
                throw null;
            }
            this.t = this.t.f136c;
            j jVar2 = j.END_ARRAY;
            this.f70b = jVar2;
            return jVar2;
        }
        if (cK == 125) {
            if (!this.t.d()) {
                a(cK, ']');
                throw null;
            }
            this.t = this.t.f136c;
            j jVar3 = j.END_OBJECT;
            this.f70b = jVar3;
            return jVar3;
        }
        d dVar = this.t;
        int i12 = dVar.f221b + 1;
        dVar.f221b = i12;
        if (dVar.f220a != 0 && i12 > 0) {
            if (cK != 44) {
                StringBuilder sbA = b.a.a.a.a.a("was expecting comma to separate ");
                sbA.append(this.t.b());
                sbA.append(" entries");
                a(cK, sbA.toString());
                throw null;
            }
            while (true) {
                int i13 = this.m;
                if (i13 >= this.n) {
                    cK = G();
                    break;
                }
                char[] cArr4 = this.H;
                this.m = i13 + 1;
                cK = cArr4[i13];
                if (cK > 32) {
                    if (cK != 47 && cK != 35) {
                        break;
                    }
                    this.m--;
                    cK = G();
                    break;
                }
                if (cK < 32) {
                    if (cK == 10) {
                        this.p++;
                        this.q = this.m;
                    } else if (cK == 13) {
                        H();
                    } else if (cK != 9) {
                        c(cK);
                        throw null;
                    }
                }
            }
        }
        boolean zD = this.t.d();
        ?? A = cK;
        if (zD) {
            if (cK == 34) {
                int i14 = this.m;
                int i15 = this.K;
                int[] iArr = F;
                while (true) {
                    if (i14 < this.n) {
                        char[] cArr5 = this.H;
                        char c8 = cArr5[i14];
                        if (c8 >= iArr.length || iArr[c8] == 0) {
                            i15 = (i15 * 33) + c8;
                            i14++;
                        } else if (c8 == '\"') {
                            int i16 = this.m;
                            this.m = i14 + 1;
                            strA = this.J.a(cArr5, i16, i14 - i16, i15);
                            break;
                        }
                    }
                    int i17 = this.m;
                    this.m = i14;
                    strA = a(i17, i15, 34);
                    break;
                }
            }
            if (cK == 39 && a(b.d.a.a.g.a.ALLOW_SINGLE_QUOTES)) {
                int i18 = this.m;
                int i19 = this.K;
                int i20 = this.n;
                if (i18 >= i20) {
                    int i21 = this.m;
                    this.m = i18;
                    strA = a(i21, i19, 39);
                    break;
                }
                int[] iArr2 = F;
                int length2 = iArr2.length;
                while (true) {
                    char[] cArr6 = this.H;
                    char c9 = cArr6[i18];
                    if (c9 != '\'') {
                        if (c9 >= length2 || iArr2[c9] == 0) {
                            i19 = (i19 * 33) + c9;
                            i18++;
                            if (i18 >= i20) {
                            }
                        }
                        int i22 = this.m;
                        this.m = i18;
                        strA = a(i22, i19, 39);
                        break;
                    }
                    int i23 = this.m;
                    this.m = i18 + 1;
                    strA = this.J.a(cArr6, i23, i18 - i23, i19);
                    break;
                }
            }
            if (!a(b.d.a.a.g.a.ALLOW_UNQUOTED_FIELD_NAMES)) {
                a(cK, "was expecting double-quote to start field name");
                throw null;
            }
            int[] iArr3 = b.d.a.a.b.a.f86e;
            int length3 = iArr3.length;
            if (!(cK < length3 ? iArr3[cK] == 0 : Character.isJavaIdentifierPart((char) cK))) {
                a(cK, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
                throw null;
            }
            int i24 = this.m;
            int i25 = this.K;
            int i26 = this.n;
            if (i24 < i26) {
                while (true) {
                    char[] cArr7 = this.H;
                    char c10 = cArr7[i24];
                    if (c10 < length3) {
                        if (iArr3[c10] != 0) {
                            int i27 = this.m - 1;
                            this.m = i24;
                            strA = this.J.a(cArr7, i27, i24 - i27, i25);
                        } else {
                            i25 = (i25 * 33) + c10;
                            i24++;
                            if (i24 >= i26) {
                                int i28 = this.m - 1;
                                this.m = i24;
                                this.v.a(this.H, i28, this.m - i28);
                                cArrG = this.v.g();
                                i4 = this.v.j;
                                length = iArr3.length;
                                while (true) {
                                    if (this.m < this.n && !B()) {
                                        break;
                                    }
                                    c5 = this.H[this.m];
                                    if (c5 <= length) {
                                        if (iArr3[c5] != 0) {
                                            break;
                                        }
                                        this.m++;
                                        i25 = (i25 * 33) + c5;
                                        i5 = i4 + 1;
                                        cArrG[i4] = c5;
                                        if (i5 >= cArrG.length) {
                                            cArrG = this.v.f();
                                            i4 = 0;
                                        } else {
                                            i4 = i5;
                                        }
                                        c7 = '/';
                                    } else {
                                        if (!Character.isJavaIdentifierPart(c5)) {
                                            break;
                                        }
                                        this.m++;
                                        i25 = (i25 * 33) + c5;
                                        i5 = i4 + 1;
                                        cArrG[i4] = c5;
                                        if (i5 >= cArrG.length) {
                                            cArrG = this.v.f();
                                            i4 = 0;
                                        } else {
                                            i4 = i5;
                                        }
                                        c7 = '/';
                                    }
                                }
                                b.d.a.a.e.f fVar = this.v;
                                fVar.j = i4;
                                strA = this.J.a(fVar.h(), fVar.i(), fVar.k(), i25);
                            }
                        }
                    } else if (Character.isJavaIdentifierPart(c10)) {
                        i25 = (i25 * 33) + c10;
                        i24++;
                        if (i24 >= i26) {
                            int i29 = this.m - 1;
                            this.m = i24;
                            this.v.a(this.H, i29, this.m - i29);
                            cArrG = this.v.g();
                            i4 = this.v.j;
                            length = iArr3.length;
                            while (true) {
                                if (this.m < this.n) {
                                    c5 = this.H[this.m];
                                    if (c5 <= length) {
                                        if (iArr3[c5] != 0) {
                                            break;
                                            break;
                                        }
                                        this.m++;
                                        i25 = (i25 * 33) + c5;
                                        i5 = i4 + 1;
                                        cArrG[i4] = c5;
                                        if (i5 >= cArrG.length) {
                                            cArrG = this.v.f();
                                            i4 = 0;
                                        } else {
                                            i4 = i5;
                                        }
                                        c7 = '/';
                                    } else {
                                        if (!Character.isJavaIdentifierPart(c5)) {
                                            break;
                                            break;
                                        }
                                        this.m++;
                                        i25 = (i25 * 33) + c5;
                                        i5 = i4 + 1;
                                        cArrG[i4] = c5;
                                        if (i5 >= cArrG.length) {
                                            cArrG = this.v.f();
                                            i4 = 0;
                                        } else {
                                            i4 = i5;
                                        }
                                        c7 = '/';
                                    }
                                } else {
                                    c5 = this.H[this.m];
                                    if (c5 <= length) {
                                        if (iArr3[c5] != 0) {
                                            break;
                                            break;
                                        }
                                        this.m++;
                                        i25 = (i25 * 33) + c5;
                                        i5 = i4 + 1;
                                        cArrG[i4] = c5;
                                        if (i5 >= cArrG.length) {
                                            cArrG = this.v.f();
                                            i4 = 0;
                                        } else {
                                            i4 = i5;
                                        }
                                        c7 = '/';
                                    } else {
                                        if (!Character.isJavaIdentifierPart(c5)) {
                                            break;
                                            break;
                                        }
                                        this.m++;
                                        i25 = (i25 * 33) + c5;
                                        i5 = i4 + 1;
                                        cArrG[i4] = c5;
                                        if (i5 >= cArrG.length) {
                                            cArrG = this.v.f();
                                            i4 = 0;
                                        } else {
                                            i4 = i5;
                                        }
                                        c7 = '/';
                                    }
                                }
                            }
                            b.d.a.a.e.f fVar2 = this.v;
                            fVar2.j = i4;
                            strA = this.J.a(fVar2.h(), fVar2.i(), fVar2.k(), i25);
                        }
                    } else {
                        int i30 = this.m - 1;
                        this.m = i24;
                        strA = this.J.a(this.H, i30, i24 - i30, i25);
                    }
                }
            } else {
                int i210 = this.m - 1;
                this.m = i24;
                this.v.a(this.H, i210, this.m - i210);
                cArrG = this.v.g();
                i4 = this.v.j;
                length = iArr3.length;
                while (true) {
                    if (this.m < this.n) {
                        c5 = this.H[this.m];
                        if (c5 <= length) {
                            if (iArr3[c5] != 0) {
                                break;
                                break;
                            }
                            this.m++;
                            i25 = (i25 * 33) + c5;
                            i5 = i4 + 1;
                            cArrG[i4] = c5;
                            if (i5 >= cArrG.length) {
                                cArrG = this.v.f();
                                i4 = 0;
                            } else {
                                i4 = i5;
                            }
                            c7 = '/';
                        } else {
                            if (!Character.isJavaIdentifierPart(c5)) {
                                break;
                                break;
                            }
                            this.m++;
                            i25 = (i25 * 33) + c5;
                            i5 = i4 + 1;
                            cArrG[i4] = c5;
                            if (i5 >= cArrG.length) {
                                cArrG = this.v.f();
                                i4 = 0;
                            } else {
                                i4 = i5;
                            }
                            c7 = '/';
                        }
                    } else {
                        c5 = this.H[this.m];
                        if (c5 <= length) {
                            if (iArr3[c5] != 0) {
                                break;
                                break;
                            }
                            this.m++;
                            i25 = (i25 * 33) + c5;
                            i5 = i4 + 1;
                            cArrG[i4] = c5;
                            if (i5 >= cArrG.length) {
                                cArrG = this.v.f();
                                i4 = 0;
                            } else {
                                i4 = i5;
                            }
                            c7 = '/';
                        } else {
                            if (!Character.isJavaIdentifierPart(c5)) {
                                break;
                                break;
                            }
                            this.m++;
                            i25 = (i25 * 33) + c5;
                            i5 = i4 + 1;
                            cArrG[i4] = c5;
                            if (i5 >= cArrG.length) {
                                cArrG = this.v.f();
                                i4 = 0;
                            } else {
                                i4 = i5;
                            }
                            c7 = '/';
                        }
                    }
                }
                b.d.a.a.e.f fVar3 = this.v;
                fVar3.j = i4;
                strA = this.J.a(fVar3.h(), fVar3.i(), fVar3.k(), i25);
            }
            this.t.a(strA);
            this.f70b = j.FIELD_NAME;
            int i31 = this.m;
            if (i31 + 4 >= this.n) {
                A = a(false);
            } else {
                char[] cArr8 = this.H;
                char c11 = cArr8[i31];
                if (c11 == ':') {
                    int i32 = i31 + 1;
                    this.m = i32;
                    char c12 = cArr8[i32];
                    if (c12 > ' ') {
                        if (c12 == c7 || c12 == '#') {
                            A = a(true);
                        } else {
                            this.m++;
                            A = c12;
                        }
                    } else if (c12 == ' ' || c12 == '\t') {
                        char[] cArr9 = this.H;
                        int i33 = this.m + 1;
                        this.m = i33;
                        char c13 = cArr9[i33];
                        if (c13 <= ' ' || c13 == c7 || c13 == '#') {
                            A = a(true);
                        } else {
                            this.m++;
                            A = c13;
                        }
                    } else {
                        A = a(true);
                    }
                } else {
                    if (c11 == ' ' || c11 == '\t') {
                        char[] cArr10 = this.H;
                        int i34 = this.m + 1;
                        this.m = i34;
                        c11 = cArr10[i34];
                    }
                    if (c11 == ':') {
                        char[] cArr11 = this.H;
                        int i35 = this.m + 1;
                        this.m = i35;
                        char c14 = cArr11[i35];
                        if (c14 > ' ') {
                            if (c14 == c7 || c14 == '#') {
                                A = a(true);
                            } else {
                                this.m++;
                                A = c14;
                            }
                        } else if (c14 == ' ' || c14 == '\t') {
                            char[] cArr12 = this.H;
                            int i36 = this.m + 1;
                            this.m = i36;
                            char c15 = cArr12[i36];
                            if (c15 <= ' ' || c15 == c7 || c15 == '#') {
                                A = a(true);
                            } else {
                                this.m++;
                                A = c15;
                            }
                        } else {
                            A = a(true);
                        }
                    } else {
                        A = a(false);
                    }
                }
            }
        }
        if (A == 34) {
            this.L = true;
            jVarA = j.VALUE_STRING;
        } else if (A == 45) {
            int i37 = this.m;
            int i38 = i37 - 1;
            int i39 = this.n;
            if (i37 >= i39) {
                jVarA = b(true, i38);
            } else {
                int i40 = i37 + 1;
                char c16 = this.H[i37];
                if (c16 > '9' || c16 < '0') {
                    this.m = i40;
                    jVarA = a(c16, true);
                } else if (c16 == '0') {
                    jVarA = b(true, i38);
                } else {
                    int i41 = 1;
                    while (true) {
                        if (i40 >= i39) {
                            jVarA = b(true, i38);
                            break;
                        }
                        int i42 = i40 + 1;
                        char c17 = this.H[i40];
                        if (c17 < '0' || c17 > '9') {
                            if (c17 != '.' && c17 != 'e' && c17 != 'E') {
                                int i43 = i42 - 1;
                                this.m = i43;
                                if (this.t.e()) {
                                    e(c17);
                                }
                                this.v.a(this.H, i38, i43 - i38);
                                jVarA = a(true, i41);
                                break;
                            }
                            this.m = i42;
                            jVarA = a(c17, i38, i42, true, i41);
                            break;
                        }
                        i41++;
                        i40 = i42;
                    }
                }
            }
        } else {
            if (A != 91) {
                if (A != 93) {
                    if (A != 102) {
                        if (A != 110) {
                            if (A != 116) {
                                if (A != 123) {
                                    if (A != 125) {
                                        switch (A) {
                                            case 48:
                                            case 49:
                                            case 50:
                                            case 51:
                                            case 52:
                                            case 53:
                                            case 54:
                                            case 55:
                                            case 56:
                                            case 57:
                                                int i44 = this.m;
                                                int i45 = i44 - 1;
                                                int i46 = this.n;
                                                if (A == 48) {
                                                    jVarA = b(false, i45);
                                                } else {
                                                    boolean z = false;
                                                    int i47 = 1;
                                                    while (true) {
                                                        if (i44 >= i46) {
                                                            this.m = i45;
                                                            jVarA = b(z, i45);
                                                        } else {
                                                            int i48 = i44 + 1;
                                                            char c18 = this.H[i44];
                                                            if (c18 >= '0' && c18 <= '9') {
                                                                i47++;
                                                                z = false;
                                                                i44 = i48;
                                                            } else if (c18 == '.' || c18 == 'e' || c18 == 'E') {
                                                                this.m = i48;
                                                                jVarA = a(c18, i45, i48, false, i47);
                                                            } else {
                                                                int i49 = i48 - 1;
                                                                this.m = i49;
                                                                if (this.t.e()) {
                                                                    e(c18);
                                                                }
                                                                this.v.a(this.H, i45, i49 - i45);
                                                                jVarA = a(false, i47);
                                                            }
                                                        }
                                                    }
                                                }
                                                break;
                                            default:
                                                if (A == 39) {
                                                    if (a(b.d.a.a.g.a.ALLOW_SINGLE_QUOTES)) {
                                                        char[] cArrD = this.v.d();
                                                        int i50 = this.v.j;
                                                        while (true) {
                                                            if (this.m >= this.n && !B()) {
                                                                c(": was expecting closing quote for a string value");
                                                                throw null;
                                                            }
                                                            char[] cArr13 = this.H;
                                                            int i51 = this.m;
                                                            this.m = i51 + 1;
                                                            char cF = cArr13[i51];
                                                            if (cF <= '\\') {
                                                                if (cF == '\\') {
                                                                    cF = F();
                                                                } else if (cF <= '\'') {
                                                                    if (cF == '\'') {
                                                                        this.v.j = i50;
                                                                        jVarA = j.VALUE_STRING;
                                                                    } else if (cF < ' ') {
                                                                        b(cF, "string value");
                                                                    }
                                                                }
                                                            }
                                                            if (i50 >= cArrD.length) {
                                                                cArrD = this.v.f();
                                                                i50 = 0;
                                                            }
                                                            cArrD[i50] = cF;
                                                            i50++;
                                                            break;
                                                        }
                                                    }
                                                    if (Character.isJavaIdentifierStart((int) A)) {
                                                        a(A, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
                                                        throw null;
                                                    }
                                                    StringBuilder sbA2 = b.a.a.a.a.a("");
                                                    sbA2.append((char) A);
                                                    a(sbA2.toString(), "('true', 'false' or 'null')");
                                                    throw null;
                                                }
                                                if (A != 43) {
                                                    if (A != 73) {
                                                        if (A == 78) {
                                                            a("NaN", 1);
                                                            if (!a(b.d.a.a.g.a.ALLOW_NON_NUMERIC_NUMBERS)) {
                                                                throw b("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                                                            }
                                                            jVarA = a("NaN", Double.NaN);
                                                            break;
                                                        }
                                                        if (Character.isJavaIdentifierStart((int) A)) {
                                                            a(A, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
                                                            throw null;
                                                        }
                                                        StringBuilder sbA3 = b.a.a.a.a.a("");
                                                        sbA3.append((char) A);
                                                        a(sbA3.toString(), "('true', 'false' or 'null')");
                                                        throw null;
                                                    }
                                                    a("Infinity", 1);
                                                    if (!a(b.d.a.a.g.a.ALLOW_NON_NUMERIC_NUMBERS)) {
                                                        throw b("Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                                                    }
                                                    jVarA = a("Infinity", Double.POSITIVE_INFINITY);
                                                } else {
                                                    if (this.m >= this.n && !B()) {
                                                        y();
                                                        throw null;
                                                    }
                                                    char[] cArr14 = this.H;
                                                    int i52 = this.m;
                                                    this.m = i52 + 1;
                                                    jVarA = a(cArr14[i52], false);
                                                }
                                                break;
                                                break;
                                        }
                                    }
                                } else {
                                    if (!zD) {
                                        this.t = this.t.b(this.r, this.s);
                                    }
                                    jVarA = j.START_OBJECT;
                                }
                            } else {
                                int i53 = this.m;
                                if (i53 + 3 < this.n) {
                                    char[] cArr15 = this.H;
                                    if (cArr15[i53] == 'r') {
                                        int i54 = i53 + 1;
                                        if (cArr15[i54] == 'u') {
                                            int i55 = i54 + 1;
                                            if (cArr15[i55] == 'e' && ((c4 = cArr15[(i3 = i55 + 1)]) < '0' || c4 == ']' || c4 == '}')) {
                                                this.m = i3;
                                            } else {
                                                a("true", 1);
                                            }
                                        } else {
                                            a("true", 1);
                                        }
                                    } else {
                                        a("true", 1);
                                    }
                                } else {
                                    a("true", 1);
                                }
                                jVarA = j.VALUE_TRUE;
                            }
                        } else {
                            int i56 = this.m;
                            if (i56 + 3 < this.n) {
                                char[] cArr16 = this.H;
                                if (cArr16[i56] == 'u') {
                                    int i57 = i56 + 1;
                                    if (cArr16[i57] == 'l') {
                                        int i58 = i57 + 1;
                                        if (cArr16[i58] == 'l' && ((c3 = cArr16[(i2 = i58 + 1)]) < '0' || c3 == ']' || c3 == '}')) {
                                            this.m = i2;
                                        } else {
                                            a("null", 1);
                                        }
                                    } else {
                                        a("null", 1);
                                    }
                                } else {
                                    a("null", 1);
                                }
                            } else {
                                a("null", 1);
                            }
                            jVarA = j.VALUE_NULL;
                        }
                    } else {
                        int i59 = this.m;
                        if (i59 + 4 < this.n) {
                            char[] cArr17 = this.H;
                            if (cArr17[i59] == 'a') {
                                int i60 = i59 + 1;
                                if (cArr17[i60] == 'l') {
                                    int i61 = i60 + 1;
                                    if (cArr17[i61] == 's') {
                                        int i62 = i61 + 1;
                                        if (cArr17[i62] == 'e' && ((c2 = cArr17[(i = i62 + 1)]) < '0' || c2 == ']' || c2 == '}')) {
                                            this.m = i;
                                        } else {
                                            a("false", 1);
                                        }
                                    } else {
                                        a("false", 1);
                                    }
                                } else {
                                    a("false", 1);
                                }
                            } else {
                                a("false", 1);
                            }
                        } else {
                            a("false", 1);
                        }
                        jVarA = j.VALUE_FALSE;
                    }
                }
                a(A, "expected a value");
                throw null;
            }
            if (!zD) {
                this.t = this.t.a(this.r, this.s);
            }
            jVarA = j.START_ARRAY;
        }
        if (zD) {
            this.u = jVarA;
            return this.f70b;
        }
        this.f70b = jVarA;
        return jVarA;
    }

    @Override // b.d.a.a.a.b
    public void z() throws IOException {
        if (this.G != null) {
            if (this.k.f90c || a(b.d.a.a.g.a.AUTO_CLOSE_SOURCE)) {
                this.G.close();
            }
            this.G = null;
        }
    }

    public void e(String str) throws b.d.a.a.f {
        a(str, "'null', 'true', 'false' or NaN");
        throw null;
    }

    public f(b.d.a.a.b.c cVar, int i, Reader reader, b.d.a.a.d.b bVar) {
        super(cVar, i);
        this.L = false;
        this.G = reader;
        cVar.a((Object) cVar.g);
        char[] cArrA = cVar.f91d.a(0, 0);
        cVar.g = cArrA;
        this.H = cArrA;
        this.m = 0;
        this.n = 0;
        this.J = bVar;
        this.K = bVar.f169c;
        this.I = true;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r10v0 ??, r10v1 ??, r10v4 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    public b.d.a.a.j a(
    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r10v0 ??, r10v1 ??, r10v4 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r10v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:215)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:150)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:415)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
        	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
        	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:295)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:284)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:268)
        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:160)
        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:104)
        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
        	at jadx.core.ProcessClass.process(ProcessClass.java:89)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:127)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:405)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:393)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:343)
        */

    public final String a(int i, int i2, int i3) throws b.d.a.a.f {
        char cF;
        this.v.a(this.H, i, this.m - i);
        char[] cArrG = this.v.g();
        int i4 = this.v.j;
        while (true) {
            if (this.m >= this.n && !B()) {
                StringBuilder sbA = b.a.a.a.a.a(": was expecting closing '");
                sbA.append((char) i3);
                sbA.append("' for name");
                c(sbA.toString());
                throw null;
            }
            char[] cArr = this.H;
            int i5 = this.m;
            this.m = i5 + 1;
            char c2 = cArr[i5];
            if (c2 > '\\') {
                cF = c2;
            } else if (c2 == '\\') {
                cF = F();
            } else {
                if (c2 <= i3) {
                    if (c2 == i3) {
                        b.d.a.a.e.f fVar = this.v;
                        fVar.j = i4;
                        return this.J.a(fVar.h(), fVar.i(), fVar.k(), i2);
                    }
                    if (c2 < ' ') {
                        b(c2, "name");
                    }
                }
                cF = c2;
            }
            i2 = (i2 * 33) + c2;
            int i6 = i4 + 1;
            cArrG[i4] = cF;
            if (i6 >= cArrG.length) {
                cArrG = this.v.f();
                i4 = 0;
            } else {
                i4 = i6;
            }
        }
    }

    public final int a(boolean z) throws b.d.a.a.f {
        while (true) {
            if (this.m >= this.n) {
                C();
            }
            char[] cArr = this.H;
            int i = this.m;
            this.m = i + 1;
            char c2 = cArr[i];
            if (c2 > ' ') {
                if (c2 == '/') {
                    I();
                } else if (c2 != '#' || !L()) {
                    if (z) {
                        return c2;
                    }
                    if (c2 != ':') {
                        if (c2 < ' ') {
                            c(c2);
                            throw null;
                        }
                        a(c2, "was expecting a colon to separate field name and value");
                        throw null;
                    }
                    z = true;
                }
            } else if (c2 >= ' ') {
                continue;
            } else if (c2 == '\n') {
                this.p++;
                this.q = this.m;
            } else if (c2 == '\r') {
                H();
            } else if (c2 != '\t') {
                c(c2);
                throw null;
            }
        }
    }

    public final void a(String str, int i) throws b.d.a.a.f {
        char c2;
        int length = str.length();
        do {
            if (this.m >= this.n && !B()) {
                e(str.substring(0, i));
                throw null;
            }
            if (this.H[this.m] == str.charAt(i)) {
                this.m++;
                i++;
            } else {
                e(str.substring(0, i));
                throw null;
            }
        } while (i < length);
        if ((this.m < this.n || B()) && (c2 = this.H[this.m]) >= '0' && c2 != ']' && c2 != '}' && Character.isJavaIdentifierPart(c2)) {
            e(str.substring(0, i));
            throw null;
        }
    }

    public void a(String str, String str2) throws b.d.a.a.f {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this.m >= this.n && !B()) {
                break;
            }
            char c2 = this.H[this.m];
            if (!Character.isJavaIdentifierPart(c2)) {
                break;
            }
            this.m++;
            sb.append(c2);
        }
        StringBuilder sbA = b.a.a.a.a.a("Unrecognized token '");
        sbA.append(sb.toString());
        sbA.append("': was expecting ");
        sbA.append(str2);
        throw b(sbA.toString());
    }
}
