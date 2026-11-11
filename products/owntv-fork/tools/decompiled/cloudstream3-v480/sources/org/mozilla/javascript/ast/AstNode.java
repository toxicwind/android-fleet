package org.mozilla.javascript.ast;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;
import org.schabi.newpipe.extractor.stream.Stream;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes8.dex */
public abstract class AstNode extends Node implements Comparable<AstNode> {
    private static final String[] INDENTATIONS;
    private static final int MAX_INDENT = 42;
    private static final Map<Integer, String> operatorNames;
    protected AstNode inlineComment;
    protected int length;
    protected AstNode parent;
    protected int position;

    public abstract String toSource(int i);

    public abstract void visit(NodeVisitor nodeVisitor);

    static {
        HashMap map = new HashMap();
        operatorNames = map;
        String[] strArr = new String[43];
        INDENTATIONS = strArr;
        map.put(57, "in");
        map.put(32, "typeof");
        map.put(58, "instanceof");
        map.put(31, "delete");
        map.put(98, ",");
        map.put(116, ":");
        map.put(117, "||");
        map.put(185, "??");
        map.put(186, "?.");
        map.put(118, "&&");
        map.put(119, "++");
        map.put(120, "--");
        map.put(9, "|");
        map.put(10, "^");
        map.put(11, "&");
        map.put(12, "==");
        map.put(13, "!=");
        map.put(14, "<");
        map.put(16, ">");
        map.put(15, "<=");
        map.put(17, ">=");
        map.put(18, "<<");
        map.put(19, ">>");
        map.put(20, ">>>");
        map.put(21, Marker.ANY_NON_NULL_MARKER);
        map.put(22, "-");
        map.put(23, Marker.ANY_MARKER);
        map.put(24, "/");
        map.put(25, "%");
        map.put(81, "**");
        map.put(26, "!");
        map.put(27, "~");
        map.put(28, Marker.ANY_NON_NULL_MARKER);
        map.put(29, "-");
        map.put(51, "===");
        map.put(52, "!==");
        map.put(99, "=");
        map.put(100, "|=");
        map.put(101, "||=");
        map.put(103, "&=");
        map.put(104, "&&=");
        map.put(105, "<<=");
        map.put(106, ">>=");
        map.put(107, ">>>=");
        map.put(108, "+=");
        map.put(109, "-=");
        map.put(110, "*=");
        map.put(111, "/=");
        map.put(112, "%=");
        map.put(102, "^=");
        map.put(113, "**=");
        map.put(114, "??=");
        map.put(139, "void");
        StringBuilder sb = new StringBuilder();
        strArr[0] = "";
        for (int i = 1; i <= 42; i++) {
            sb.append("  ");
            INDENTATIONS[i] = sb.toString();
        }
    }

    public static class PositionComparator implements Comparator<AstNode>, Serializable {
        private static final long serialVersionUID = 1;

        @Override // java.util.Comparator
        public int compare(AstNode astNode, AstNode astNode2) {
            return astNode.position - astNode2.position;
        }
    }

    public AstNode() {
        super(-1);
        this.position = -1;
        this.length = 1;
    }

    public AstNode(int i) {
        this();
        this.position = i;
    }

    public AstNode(int i, int i2) {
        this();
        this.position = i;
        this.length = i2;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public int getAbsolutePosition() {
        int position = this.position;
        for (AstNode parent = this.parent; parent != null; parent = parent.getParent()) {
            position += parent.getPosition();
        }
        return position;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int i) {
        this.length = i;
    }

    public void setBounds(int i, int i2) {
        setPosition(i);
        setLength(i2 - i);
    }

    public void setRelative(int i) {
        this.position -= i;
    }

    public AstNode getParent() {
        return this.parent;
    }

    public void setParent(AstNode astNode) {
        AstNode astNode2 = this.parent;
        if (astNode == astNode2) {
            return;
        }
        if (astNode2 != null) {
            setRelative(-astNode2.getAbsolutePosition());
        }
        this.parent = astNode;
        if (astNode != null) {
            setRelative(astNode.getAbsolutePosition());
        }
    }

    public void addChild(AstNode astNode) {
        assertNotNull(astNode);
        setLength((astNode.getPosition() + astNode.getLength()) - getPosition());
        addChildToBack(astNode);
        astNode.setParent(this);
    }

    public AstRoot getAstRoot() {
        while (this != null && !(this instanceof AstRoot)) {
            this = this.getParent();
        }
        return (AstRoot) this;
    }

    public String toSource() {
        return toSource(0);
    }

    public String makeIndent(int i) {
        return INDENTATIONS[Math.min(42, Math.max(0, i))];
    }

    public String shortName() {
        String name = getClass().getName();
        return name.substring(name.lastIndexOf(".") + 1);
    }

    public static String operatorToString(int i) {
        String str = operatorNames.get(Integer.valueOf(i));
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("Invalid operator: " + i);
    }

    @Override // org.mozilla.javascript.Node
    public boolean hasSideEffects() {
        int type = getType();
        if (type == 30 || type == 31 || type == 55 || type == 56 || type == 61 || type == 62 || type == 90 || type == 91 || type == 119 || type == 120) {
            return true;
        }
        switch (type) {
            case -1:
            case 37:
            case 41:
            case 43:
            case 70:
            case 78:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 143:
            case 144:
            case 145:
            case 146:
            case 148:
            case 149:
            case 153:
            case 154:
            case 155:
            case 156:
            case 167:
            case 168:
            case 172:
            case 173:
            case 179:
                return true;
            default:
                switch (type) {
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        return true;
                    default:
                        switch (type) {
                            case 74:
                            case 75:
                            case 76:
                                return true;
                            default:
                                switch (type) {
                                    case 122:
                                    case 123:
                                    case 124:
                                    case 125:
                                    case 126:
                                    case 127:
                                        return true;
                                    default:
                                        switch (type) {
                                            case 130:
                                            case 131:
                                            case 132:
                                            case 133:
                                            case 134:
                                            case 135:
                                            case 136:
                                            case 137:
                                            case 138:
                                                return true;
                                            default:
                                                return false;
                                        }
                                }
                        }
                }
        }
    }

    protected void assertNotNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("arg cannot be null");
        }
    }

    protected <T extends AstNode> void printList(List<T> list, StringBuilder sb) {
        int size = list.size();
        int i = 0;
        for (T t : list) {
            sb.append(t.toSource(0));
            int i2 = i + 1;
            if (i < size - 1) {
                sb.append(", ");
            } else if (t instanceof EmptyExpression) {
                sb.append(",");
            }
            i = i2;
        }
    }

    public static RuntimeException codeBug() throws RuntimeException {
        throw Kit.codeBug();
    }

    public FunctionNode getEnclosingFunction() {
        AstNode parent = getParent();
        while (parent != null && !(parent instanceof FunctionNode)) {
            parent = parent.getParent();
        }
        return (FunctionNode) parent;
    }

    public Scope getEnclosingScope() {
        AstNode parent = getParent();
        while (parent != null && !(parent instanceof Scope)) {
            parent = parent.getParent();
        }
        return (Scope) parent;
    }

    @Override // java.lang.Comparable
    public int compareTo(AstNode astNode) {
        if (equals(astNode)) {
            return 0;
        }
        int absolutePosition = getAbsolutePosition();
        int absolutePosition2 = astNode.getAbsolutePosition();
        if (absolutePosition < absolutePosition2) {
            return -1;
        }
        if (absolutePosition2 < absolutePosition) {
            return 1;
        }
        int length = getLength();
        int length2 = astNode.getLength();
        if (length < length2) {
            return -1;
        }
        if (length2 < length) {
            return 1;
        }
        return hashCode() - astNode.hashCode();
    }

    public int depth() {
        AstNode astNode = this.parent;
        if (astNode == null) {
            return 0;
        }
        return astNode.depth() + 1;
    }

    protected static class DebugPrintVisitor implements NodeVisitor {
        private static final int DEBUG_INDENT = 2;
        private StringBuilder buffer;

        public DebugPrintVisitor(StringBuilder sb) {
            this.buffer = sb;
        }

        public String toString() {
            return this.buffer.toString();
        }

        private static String makeIndent(int i) {
            int i2 = i * 2;
            StringBuilder sb = new StringBuilder(i2);
            for (int i3 = 0; i3 < i2; i3++) {
                sb.append(Stream.ID_UNKNOWN);
            }
            return sb.toString();
        }

        @Override // org.mozilla.javascript.ast.NodeVisitor
        public boolean visit(AstNode astNode) {
            int type = astNode.getType();
            String strTypeToName = Token.typeToName(type);
            StringBuilder sb = this.buffer;
            sb.append(astNode.getAbsolutePosition());
            sb.append("\t");
            this.buffer.append(makeIndent(astNode.depth()));
            StringBuilder sb2 = this.buffer;
            sb2.append(strTypeToName);
            sb2.append(Stream.ID_UNKNOWN);
            StringBuilder sb3 = this.buffer;
            sb3.append(astNode.getPosition());
            sb3.append(Stream.ID_UNKNOWN);
            this.buffer.append(astNode.getLength());
            if (type == 44) {
                StringBuilder sb4 = this.buffer;
                sb4.append(Stream.ID_UNKNOWN);
                sb4.append(((Name) astNode).getIdentifier());
            } else if (type == 46) {
                StringBuilder sb5 = this.buffer;
                sb5.append(Stream.ID_UNKNOWN);
                sb5.append(((StringLiteral) astNode).getValue(true));
            }
            this.buffer.append("\n");
            return true;
        }
    }

    @Override // org.mozilla.javascript.Node
    public int getLineno() {
        if (this.lineno != -1) {
            return this.lineno;
        }
        AstNode astNode = this.parent;
        if (astNode != null) {
            return astNode.getLineno();
        }
        return -1;
    }

    public String debugPrint() {
        DebugPrintVisitor debugPrintVisitor = new DebugPrintVisitor(new StringBuilder(1000));
        visit(debugPrintVisitor);
        return debugPrintVisitor.toString();
    }

    public AstNode getInlineComment() {
        return this.inlineComment;
    }

    public void setInlineComment(AstNode astNode) {
        this.inlineComment = astNode;
    }
}
