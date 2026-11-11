package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.mozilla.javascript.Node;

/* JADX INFO: loaded from: classes8.dex */
public class ScriptNode extends Scope {
    private List<FunctionNode> EMPTY_LIST;
    private Object compilerData;
    private int endLineno;
    private List<FunctionNode> functions;
    private boolean inStrictMode;
    private boolean[] isConsts;
    private boolean isMethodDefinition;
    private int paramCount;
    private String rawSource;
    private int rawSourceEnd;
    private int rawSourceStart;
    private List<RegExpLiteral> regexps;
    private String sourceName;
    private List<Symbol> symbols;
    private int tempNumber;
    private List<TemplateLiteral> templateLiterals;
    private String[] variableNames;

    public List<Object> getDefaultParams() {
        return null;
    }

    public List<Node[]> getDestructuringRvalues() {
        return null;
    }

    public boolean hasRestParameter() {
        return false;
    }

    public void putDestructuringRvalues(Node node, Node node2) {
    }

    public ScriptNode() {
        this.rawSourceStart = -1;
        this.rawSourceEnd = -1;
        this.endLineno = -1;
        this.EMPTY_LIST = Collections.EMPTY_LIST;
        this.symbols = new ArrayList(4);
        this.paramCount = 0;
        this.tempNumber = 0;
        this.top = this;
        this.type = 150;
    }

    public ScriptNode(int i) {
        super(i);
        this.rawSourceStart = -1;
        this.rawSourceEnd = -1;
        this.endLineno = -1;
        this.EMPTY_LIST = Collections.EMPTY_LIST;
        this.symbols = new ArrayList(4);
        this.paramCount = 0;
        this.tempNumber = 0;
        this.top = this;
        this.type = 150;
    }

    public String getSourceName() {
        return this.sourceName;
    }

    public void setSourceName(String str) {
        this.sourceName = str;
    }

    public int getRawSourceStart() {
        return this.rawSourceStart;
    }

    public void setRawSourceStart(int i) {
        this.rawSourceStart = i;
    }

    public int getRawSourceEnd() {
        return this.rawSourceEnd;
    }

    public void setRawSourceEnd(int i) {
        this.rawSourceEnd = i;
    }

    public void setRawSourceBounds(int i, int i2) {
        this.rawSourceStart = i;
        this.rawSourceEnd = i2;
    }

    public void setRawSource(String str) {
        this.rawSource = str;
    }

    public String getRawSource() {
        return this.rawSource;
    }

    public int getBaseLineno() {
        return this.lineno;
    }

    public void setBaseLineno(int i) {
        if (i < 0 || this.lineno >= 0) {
            codeBug();
        }
        this.lineno = i;
    }

    public int getEndLineno() {
        return this.endLineno;
    }

    public void setEndLineno(int i) {
        if (i < 0 || this.endLineno >= 0) {
            codeBug();
        }
        this.endLineno = i;
    }

    public int getFunctionCount() {
        List<FunctionNode> list = this.functions;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public FunctionNode getFunctionNode(int i) {
        return this.functions.get(i);
    }

    public List<FunctionNode> getFunctions() {
        List<FunctionNode> list = this.functions;
        return list == null ? this.EMPTY_LIST : list;
    }

    public int addFunction(FunctionNode functionNode) {
        if (functionNode == null) {
            codeBug();
        }
        if (this.functions == null) {
            this.functions = new ArrayList();
        }
        this.functions.add(functionNode);
        return this.functions.size() - 1;
    }

    public int getRegexpCount() {
        List<RegExpLiteral> list = this.regexps;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public String getRegexpString(int i) {
        return this.regexps.get(i).getValue();
    }

    public String getRegexpFlags(int i) {
        return this.regexps.get(i).getFlags();
    }

    public void addRegExp(RegExpLiteral regExpLiteral) {
        if (regExpLiteral == null) {
            codeBug();
        }
        if (this.regexps == null) {
            this.regexps = new ArrayList();
        }
        this.regexps.add(regExpLiteral);
        regExpLiteral.putIntProp(4, this.regexps.size() - 1);
    }

    public int getTemplateLiteralCount() {
        List<TemplateLiteral> list = this.templateLiterals;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public List<TemplateCharacters> getTemplateLiteralStrings(int i) {
        return this.templateLiterals.get(i).getTemplateStrings();
    }

    public void addTemplateLiteral(TemplateLiteral templateLiteral) {
        if (templateLiteral == null) {
            codeBug();
        }
        if (this.templateLiterals == null) {
            this.templateLiterals = new ArrayList();
        }
        this.templateLiterals.add(templateLiteral);
        templateLiteral.putIntProp(27, this.templateLiterals.size() - 1);
    }

    public int getIndexForNameNode(Node node) {
        if (this.variableNames == null) {
            codeBug();
        }
        Scope scope = node.getScope();
        Symbol symbol = (scope == null || !(node instanceof Name)) ? null : scope.getSymbol(((Name) node).getIdentifier());
        if (symbol == null) {
            return -1;
        }
        return symbol.getIndex();
    }

    public String getParamOrVarName(int i) {
        if (this.variableNames == null) {
            codeBug();
        }
        return this.variableNames[i];
    }

    public int getParamCount() {
        return this.paramCount;
    }

    public int getParamAndVarCount() {
        if (this.variableNames == null) {
            codeBug();
        }
        return this.symbols.size();
    }

    public String[] getParamAndVarNames() {
        if (this.variableNames == null) {
            codeBug();
        }
        return this.variableNames;
    }

    public boolean[] getParamAndVarConst() {
        if (this.variableNames == null) {
            codeBug();
        }
        return this.isConsts;
    }

    void addSymbol(Symbol symbol) {
        if (this.variableNames != null) {
            codeBug();
        }
        if (symbol.getDeclType() == 96) {
            this.paramCount++;
        }
        this.symbols.add(symbol);
    }

    public List<Symbol> getSymbols() {
        return this.symbols;
    }

    public void setSymbols(List<Symbol> list) {
        this.symbols = list;
    }

    public void flattenSymbolTable(boolean z) {
        if (!z) {
            ArrayList arrayList = new ArrayList();
            if (this.symbolTable != null) {
                for (Symbol symbol : this.symbols) {
                    if (symbol.getContainingTable() == this) {
                        arrayList.add(symbol);
                    }
                }
            }
            this.symbols = arrayList;
        }
        this.variableNames = new String[this.symbols.size()];
        this.isConsts = new boolean[this.symbols.size()];
        for (int i = 0; i < this.symbols.size(); i++) {
            Symbol symbol2 = this.symbols.get(i);
            this.variableNames[i] = symbol2.getName();
            this.isConsts[i] = symbol2.getDeclType() == 168;
            symbol2.setIndex(i);
        }
    }

    public Object getCompilerData() {
        return this.compilerData;
    }

    public void setCompilerData(Object obj) {
        assertNotNull(obj);
        if (this.compilerData != null) {
            throw new IllegalStateException();
        }
        this.compilerData = obj;
    }

    public String getNextTempName() {
        int i = this.tempNumber;
        this.tempNumber = i + 1;
        return "$" + i;
    }

    public void setInStrictMode(boolean z) {
        this.inStrictMode = z;
    }

    public boolean isInStrictMode() {
        return this.inStrictMode;
    }

    public boolean isMethodDefinition() {
        return this.isMethodDefinition;
    }

    public void setMethodDefinition(boolean z) {
        this.isMethodDefinition = z;
    }

    @Override // org.mozilla.javascript.ast.Scope, org.mozilla.javascript.ast.Jump, org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            Iterator<Node> it = iterator();
            while (it.hasNext()) {
                ((AstNode) it.next()).visit(nodeVisitor);
            }
        }
    }
}
