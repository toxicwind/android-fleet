package org.mozilla.javascript;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mozilla.javascript.ast.ArrayComprehension;
import org.mozilla.javascript.ast.ArrayComprehensionLoop;
import org.mozilla.javascript.ast.ArrayLiteral;
import org.mozilla.javascript.ast.Assignment;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.BigIntLiteral;
import org.mozilla.javascript.ast.Block;
import org.mozilla.javascript.ast.BreakStatement;
import org.mozilla.javascript.ast.CatchClause;
import org.mozilla.javascript.ast.ComputedPropertyKey;
import org.mozilla.javascript.ast.ConditionalExpression;
import org.mozilla.javascript.ast.ContinueStatement;
import org.mozilla.javascript.ast.DestructuringForm;
import org.mozilla.javascript.ast.DoLoop;
import org.mozilla.javascript.ast.ElementGet;
import org.mozilla.javascript.ast.EmptyExpression;
import org.mozilla.javascript.ast.ExpressionStatement;
import org.mozilla.javascript.ast.ForInLoop;
import org.mozilla.javascript.ast.ForLoop;
import org.mozilla.javascript.ast.FunctionCall;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.GeneratorExpression;
import org.mozilla.javascript.ast.GeneratorExpressionLoop;
import org.mozilla.javascript.ast.GeneratorMethodDefinition;
import org.mozilla.javascript.ast.IfStatement;
import org.mozilla.javascript.ast.InfixExpression;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.KeywordLiteral;
import org.mozilla.javascript.ast.Label;
import org.mozilla.javascript.ast.LabeledStatement;
import org.mozilla.javascript.ast.LetNode;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NewExpression;
import org.mozilla.javascript.ast.NumberLiteral;
import org.mozilla.javascript.ast.ObjectLiteral;
import org.mozilla.javascript.ast.ObjectProperty;
import org.mozilla.javascript.ast.ParenthesizedExpression;
import org.mozilla.javascript.ast.PropertyGet;
import org.mozilla.javascript.ast.RegExpLiteral;
import org.mozilla.javascript.ast.ReturnStatement;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.ast.StringLiteral;
import org.mozilla.javascript.ast.SwitchCase;
import org.mozilla.javascript.ast.SwitchStatement;
import org.mozilla.javascript.ast.TaggedTemplateLiteral;
import org.mozilla.javascript.ast.TemplateCharacters;
import org.mozilla.javascript.ast.TemplateLiteral;
import org.mozilla.javascript.ast.ThrowStatement;
import org.mozilla.javascript.ast.TryStatement;
import org.mozilla.javascript.ast.UnaryExpression;
import org.mozilla.javascript.ast.UpdateExpression;
import org.mozilla.javascript.ast.VariableDeclaration;
import org.mozilla.javascript.ast.VariableInitializer;
import org.mozilla.javascript.ast.WhileLoop;
import org.mozilla.javascript.ast.WithStatement;
import org.mozilla.javascript.ast.XmlElemRef;
import org.mozilla.javascript.ast.XmlExpression;
import org.mozilla.javascript.ast.XmlFragment;
import org.mozilla.javascript.ast.XmlLiteral;
import org.mozilla.javascript.ast.XmlMemberGet;
import org.mozilla.javascript.ast.XmlPropRef;
import org.mozilla.javascript.ast.XmlRef;
import org.mozilla.javascript.ast.XmlString;
import org.mozilla.javascript.ast.Yield;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes8.dex */
public final class IRFactory {
    private static final int ALWAYS_FALSE_BOOLEAN = -1;
    private static final int ALWAYS_TRUE_BOOLEAN = 1;
    private static final int LOOP_DO_WHILE = 0;
    private static final int LOOP_FOR = 2;
    private static final int LOOP_WHILE = 1;
    private AstNodePosition astNodePos;
    private Parser parser;

    private Node transformBigInt(BigIntLiteral bigIntLiteral) {
        return bigIntLiteral;
    }

    private Node transformBreak(BreakStatement breakStatement) {
        return breakStatement;
    }

    private Node transformContinue(ContinueStatement continueStatement) {
        return continueStatement;
    }

    private Node transformName(Name name) {
        return name;
    }

    private Node transformNumber(NumberLiteral numberLiteral) {
        return numberLiteral;
    }

    public IRFactory(CompilerEnvirons compilerEnvirons, String str) {
        this(compilerEnvirons, null, str, compilerEnvirons.getErrorReporter());
    }

    @Deprecated
    public IRFactory(CompilerEnvirons compilerEnvirons, String str, ErrorReporter errorReporter) {
        this(compilerEnvirons, null, str, errorReporter);
    }

    public IRFactory(CompilerEnvirons compilerEnvirons, String str, String str2, ErrorReporter errorReporter) {
        this.parser = new Parser(compilerEnvirons, errorReporter);
        AstNodePosition astNodePosition = new AstNodePosition(str2);
        this.astNodePos = astNodePosition;
        this.parser.currentPos = astNodePosition;
        this.parser.setSourceURI(str);
    }

    public ScriptNode transformTree(AstRoot astRoot) {
        this.parser.currentScriptOrFn = astRoot;
        this.parser.inUseStrictDirective = astRoot.isInStrictMode();
        this.astNodePos.push(astRoot);
        try {
            return (ScriptNode) transform(astRoot);
        } catch (Parser.ParserException unused) {
            this.parser.reportErrorsIfExists(astRoot.getLineno());
            return null;
        } finally {
            this.astNodePos.pop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:120:0x0164 A[RETURN] */
    public Node transform(AstNode astNode) {
        int type = astNode.getType();
        if (type == 71) {
            return transformArrayLiteral((ArrayLiteral) astNode);
        }
        if (type == 72) {
            return transformObjectLiteral((ObjectLiteral) astNode);
        }
        if (type != 78) {
            if (type == 79) {
                this.parser.setRequiresActivation();
                return transformLiteral(astNode);
            }
            if (type == 89) {
                return transformBigInt((BigIntLiteral) astNode);
            }
            if (type == 90) {
                return transformTry((TryStatement) astNode);
            }
            if (type != 179) {
                if (type == 180) {
                    return transformTemplateLiteral((TemplateLiteral) astNode);
                }
                switch (type) {
                    case 4:
                        return transformReturn((ReturnStatement) astNode);
                    case 30:
                        return transformNewExpr((NewExpression) astNode);
                    case 33:
                        return transformPropertyGet((PropertyGet) astNode);
                    case 39:
                        return transformElementGet((ElementGet) astNode);
                    case 53:
                        return transformRegExp((RegExpLiteral) astNode);
                    case 55:
                        return transformThrow((ThrowStatement) astNode);
                    case 115:
                        return transformCondExpr((ConditionalExpression) astNode);
                    case 122:
                        return transformFunction((FunctionNode) astNode);
                    case 125:
                        return transformIf((IfStatement) astNode);
                    case 127:
                        return transformSwitch((SwitchStatement) astNode);
                    case 136:
                        return transformWith((WithStatement) astNode);
                    case 141:
                        return astNode;
                    case 143:
                        return transformBlock(astNode);
                    case 150:
                        return transformScript((ScriptNode) astNode);
                    case 171:
                        return transformArrayComp((ArrayComprehension) astNode);
                    case 183:
                        return transformTemplateLiteralCall((TaggedTemplateLiteral) astNode);
                    case 186:
                        if (astNode instanceof ElementGet) {
                            return transformElementGet((ElementGet) astNode);
                        }
                        return transformPropertyGet((PropertyGet) astNode);
                    default:
                        switch (type) {
                            case 43:
                                return transformFunctionCall((FunctionCall) astNode);
                            case 44:
                                return transformName((Name) astNode);
                            case 45:
                                return transformNumber((NumberLiteral) astNode);
                            case 46:
                                return transformString((StringLiteral) astNode);
                            default:
                                switch (type) {
                                    case 130:
                                        return transformWhileLoop((WhileLoop) astNode);
                                    case 131:
                                        return transformDoLoop((DoLoop) astNode);
                                    case 132:
                                        if (astNode instanceof ForInLoop) {
                                            return transformForInLoop((ForInLoop) astNode);
                                        }
                                        return transformForLoop((ForLoop) astNode);
                                    case 133:
                                        return transformBreak((BreakStatement) astNode);
                                    case 134:
                                        return transformContinue((ContinueStatement) astNode);
                                    default:
                                        switch (type) {
                                            case 174:
                                                break;
                                            case 175:
                                                return astNode;
                                            case 176:
                                                return transformGenExpr((GeneratorExpression) astNode);
                                            default:
                                                if (astNode instanceof ExpressionStatement) {
                                                    return transformExprStmt((ExpressionStatement) astNode);
                                                }
                                                if (astNode instanceof Assignment) {
                                                    return transformAssignment((Assignment) astNode);
                                                }
                                                if (astNode instanceof UnaryExpression) {
                                                    return transformUnary((UnaryExpression) astNode);
                                                }
                                                if (astNode instanceof UpdateExpression) {
                                                    return transformUpdate((UpdateExpression) astNode);
                                                }
                                                if (astNode instanceof XmlMemberGet) {
                                                    return transformXmlMemberGet((XmlMemberGet) astNode);
                                                }
                                                if (astNode instanceof InfixExpression) {
                                                    return transformInfix((InfixExpression) astNode);
                                                }
                                                if (astNode instanceof VariableDeclaration) {
                                                    return transformVariables((VariableDeclaration) astNode);
                                                }
                                                if (astNode instanceof ParenthesizedExpression) {
                                                    return transformParenExpr((ParenthesizedExpression) astNode);
                                                }
                                                if (astNode instanceof ComputedPropertyKey) {
                                                    return transformComputedPropertyKey((ComputedPropertyKey) astNode);
                                                }
                                                if (astNode instanceof LabeledStatement) {
                                                    return transformLabeledStatement((LabeledStatement) astNode);
                                                }
                                                if (astNode instanceof LetNode) {
                                                    return transformLetNode((LetNode) astNode);
                                                }
                                                if (astNode instanceof XmlRef) {
                                                    return transformXmlRef((XmlRef) astNode);
                                                }
                                                if (astNode instanceof XmlLiteral) {
                                                    return transformXmlLiteral((XmlLiteral) astNode);
                                                }
                                                if (astNode instanceof GeneratorMethodDefinition) {
                                                    return transformGeneratorMethodDefinition((GeneratorMethodDefinition) astNode);
                                                }
                                                throw new IllegalArgumentException("Can't transform: " + String.valueOf(astNode));
                                        }
                                        break;
                                }
                            case 47:
                            case 48:
                            case 49:
                            case 50:
                                return transformLiteral(astNode);
                        }
                        break;
                }
            }
        }
        return transformYield((Yield) astNode);
    }

    private Node transformArrayComp(ArrayComprehension arrayComprehension) {
        int lineno = arrayComprehension.getLineno();
        int column = arrayComprehension.getColumn();
        Scope scopeCreateScopeNode = this.parser.createScopeNode(171, lineno, column);
        String nextTempName = this.parser.currentScriptOrFn.getNextTempName();
        this.parser.pushScope(scopeCreateScopeNode);
        try {
            this.astNodePos.push(arrayComprehension);
            try {
                this.parser.defineSymbol(167, nextTempName, false);
                Node node = new Node(143);
                node.setLineColumnNumber(lineno, column);
                node.addChildToBack(new Node(147, createAssignment(99, this.parser.createName(nextTempName), createCallOrNew(30, this.parser.createName("Array"))), lineno, column));
                node.addChildToBack(arrayCompTransformHelper(arrayComprehension, nextTempName));
                scopeCreateScopeNode.addChildToBack(node);
                scopeCreateScopeNode.addChildToBack(this.parser.createName(nextTempName));
                this.astNodePos.pop();
                this.parser.popScope();
                return scopeCreateScopeNode;
            } catch (Throwable th) {
                this.astNodePos.pop();
                throw th;
            }
        } catch (Throwable th2) {
            this.parser.popScope();
            throw th2;
        }
    }

    private Node arrayCompTransformHelper(ArrayComprehension arrayComprehension, String str) throws Throwable {
        String string;
        int lineno = arrayComprehension.getLineno();
        int column = arrayComprehension.getColumn();
        Node nodeTransform = transform(arrayComprehension.getResult());
        List<ArrayComprehensionLoop> loops = arrayComprehension.getLoops();
        int size = loops.size();
        Node[] nodeArr = new Node[size];
        Node[] nodeArr2 = new Node[size];
        int i = 0;
        Node nodeCreateBinary = nodeTransform;
        for (int i2 = 0; i2 < size; i2++) {
            ArrayComprehensionLoop arrayComprehensionLoop = loops.get(i2);
            AstNode iterator = arrayComprehensionLoop.getIterator();
            this.astNodePos.push(iterator);
            try {
                if (iterator.getType() == 44) {
                    string = iterator.getString();
                } else {
                    String nextTempName = this.parser.currentScriptOrFn.getNextTempName();
                    this.parser.defineSymbol(96, nextTempName, false);
                    nodeCreateBinary = createBinary(98, createAssignment(99, iterator, this.parser.createName(nextTempName)), nodeCreateBinary);
                    string = nextTempName;
                }
                Node nodeCreateName = this.parser.createName(string);
                this.parser.defineSymbol(167, string, false);
                nodeArr[i2] = nodeCreateName;
                this.astNodePos.pop();
                nodeArr2[i2] = transform(arrayComprehensionLoop.getIteratedObject());
            } catch (Throwable th) {
                this.astNodePos.pop();
                throw th;
            }
        }
        Node nodeCreateCallOrNew = createCallOrNew(43, createPropertyGet(this.parser.createName(str), null, "push", 0, arrayComprehension.type));
        Node node = new Node(147, nodeCreateCallOrNew);
        node.setLineColumnNumber(lineno, column);
        Node node2 = null;
        if (arrayComprehension.getFilter() != null) {
            node = createIf(transform(arrayComprehension.getFilter()), node, null, lineno, column);
        }
        Node nodeCreateForIn = node;
        int i3 = size - 1;
        int i4 = 0;
        while (i3 >= 0) {
            try {
                ArrayComprehensionLoop arrayComprehensionLoop2 = loops.get(i3);
                Scope scopeCreateLoopNode = createLoopNode(node2, arrayComprehensionLoop2.getLineno(), arrayComprehensionLoop2.getColumn());
                this.parser.pushScope(scopeCreateLoopNode);
                int i5 = i4 + 1;
                Node node3 = node2;
                try {
                    nodeCreateForIn = createForIn(167, scopeCreateLoopNode, nodeArr[i3], nodeArr2[i3], nodeCreateForIn, arrayComprehensionLoop2, arrayComprehensionLoop2.isForEach(), arrayComprehensionLoop2.isForOf());
                    i3--;
                    i4 = i5;
                    node2 = node3;
                } catch (Throwable th2) {
                    th = th2;
                    i4 = i5;
                    while (i < i4) {
                        this.parser.popScope();
                        i++;
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        while (i < i4) {
            this.parser.popScope();
            i++;
        }
        nodeCreateCallOrNew.addChildToBack(nodeCreateBinary);
        return nodeCreateForIn;
    }

    private Node transformArrayLiteral(ArrayLiteral arrayLiteral) {
        if (arrayLiteral.isDestructuring()) {
            return arrayLiteral;
        }
        List<AstNode> elements = arrayLiteral.getElements();
        Node node = new Node(71);
        ArrayList arrayList = null;
        for (int i = 0; i < elements.size(); i++) {
            AstNode astNode = elements.get(i);
            if (astNode.getType() != 141) {
                node.addChildToBack(transform(astNode));
            } else {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(Integer.valueOf(i));
            }
        }
        node.putIntProp(21, arrayLiteral.getDestructuringLength());
        if (arrayList != null) {
            int[] iArr = new int[arrayList.size()];
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                iArr[i2] = ((Integer) arrayList.get(i2)).intValue();
            }
            node.putProp(11, iArr);
        }
        return node;
    }

    private Node transformAssignment(Assignment assignment) {
        AstNode right = assignment.getRight();
        AstNode astNodeTransformAssignmentLeft = transformAssignmentLeft(assignment, this.parser.removeParens(assignment.getLeft()), right);
        Node nodeTransform = isDestructuring(astNodeTransformAssignmentLeft) ? astNodeTransformAssignmentLeft : transform(astNodeTransformAssignmentLeft);
        this.astNodePos.push(astNodeTransformAssignmentLeft);
        try {
            return createAssignment(assignment.getType(), nodeTransform, transform(right));
        } finally {
            this.astNodePos.pop();
        }
    }

    private AstNode transformAssignmentLeft(Assignment assignment, AstNode astNode, AstNode astNode2) {
        Name functionName;
        if (astNode2.getType() == 47 && assignment.getType() == 99 && (astNode instanceof Name) && (astNode2 instanceof KeywordLiteral)) {
            String identifier = ((Name) astNode).getIdentifier();
            for (AstNode parent = assignment.getParent(); parent != null; parent = parent.getParent()) {
                if ((parent instanceof FunctionNode) && (functionName = ((FunctionNode) parent).getFunctionName()) != null && functionName.getIdentifier().equals(identifier)) {
                    PropertyGet propertyGet = new PropertyGet();
                    KeywordLiteral keywordLiteral = new KeywordLiteral();
                    keywordLiteral.setType(48);
                    propertyGet.setLeft(keywordLiteral);
                    propertyGet.setRight(astNode);
                    assignment.setLeft(propertyGet);
                    return propertyGet;
                }
            }
        }
        return astNode;
    }

    private Node transformBlock(AstNode astNode) {
        boolean z = astNode instanceof Scope;
        if (z) {
            this.parser.pushScope((Scope) astNode);
        }
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<Node> it = astNode.iterator();
            while (it.hasNext()) {
                arrayList.add(transform((AstNode) it.next()));
            }
            astNode.removeChildren();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                astNode.addChildToBack((Node) it2.next());
            }
            return astNode;
        } finally {
            if (z) {
                this.parser.popScope();
            }
        }
    }

    private Node transformCondExpr(ConditionalExpression conditionalExpression) {
        return createCondExpr(transform(conditionalExpression.getTestExpression()), transform(conditionalExpression.getTrueExpression()), transform(conditionalExpression.getFalseExpression()));
    }

    private Node transformDoLoop(DoLoop doLoop) {
        doLoop.setType(146);
        this.parser.pushScope(doLoop);
        try {
            return createLoop(doLoop, 0, transform(doLoop.getBody()), transform(doLoop.getCondition()), null, null);
        } finally {
            this.parser.popScope();
        }
    }

    private Node transformElementGet(ElementGet elementGet) {
        Node nodeTransform = transform(elementGet.getTarget());
        Node node = new Node(39, nodeTransform, transform(elementGet.getElement()));
        if (elementGet.type == 186) {
            node.putIntProp(30, 1);
        }
        if (nodeTransform.getType() == 79) {
            node.putIntProp(31, 1);
        }
        return node;
    }

    private Node transformExprStmt(ExpressionStatement expressionStatement) {
        return new Node(expressionStatement.getType(), transform(expressionStatement.getExpression()), expressionStatement.getLineno(), expressionStatement.getColumn());
    }

    private Node transformForInLoop(ForInLoop forInLoop) throws Throwable {
        IRFactory iRFactory;
        Throwable th;
        int type;
        forInLoop.setType(146);
        this.parser.pushScope(forInLoop);
        try {
            AstNode iterator = forInLoop.getIterator();
            if (iterator instanceof VariableDeclaration) {
                try {
                    type = iterator.getType();
                } catch (Throwable th2) {
                    th = th2;
                    iRFactory = this;
                    iRFactory.parser.popScope();
                    throw th;
                }
            } else {
                type = -1;
            }
            iRFactory = this;
            try {
                Node nodeCreateForIn = iRFactory.createForIn(type, forInLoop, transform(iterator), transform(forInLoop.getIteratedObject()), transform(forInLoop.getBody()), forInLoop, forInLoop.isForEach(), forInLoop.isForOf());
                iRFactory.parser.popScope();
                return nodeCreateForIn;
            } catch (Throwable th3) {
                th = th3;
                th = th;
                iRFactory.parser.popScope();
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            iRFactory = this;
        }
    }

    private Node transformForLoop(ForLoop forLoop) {
        forLoop.setType(146);
        Scope scope = this.parser.currentScope;
        this.parser.currentScope = forLoop;
        try {
            return createFor(forLoop, transform(forLoop.getInitializer()), transform(forLoop.getCondition()), transform(forLoop.getIncrement()), transform(forLoop.getBody()));
        } finally {
            this.parser.currentScope = scope;
        }
    }

    /* JADX WARN: Code duplicated, block: B:18:0x00b9  */
    private Node transformFunction(FunctionNode functionNode) throws Throwable {
        Parser.PerFunctionVariables perFunctionVariables;
        Node nodeDecompileFunctionHeader = decompileFunctionHeader(functionNode);
        int iAddFunction = this.parser.currentScriptOrFn.addFunction(functionNode);
        Parser.PerFunctionVariables perFunctionVariablesCreatePerFunctionVariables = this.parser.createPerFunctionVariables(functionNode);
        boolean z = true;
        try {
            Node node = (Node) functionNode.getProp(23);
            functionNode.removeProp(23);
            int lineno = functionNode.getBody().getLineno();
            int column = functionNode.getBody().getColumn();
            this.parser.nestingOfFunction++;
            Node nodeTransform = transform(functionNode.getBody());
            List<Object> defaultParams = functionNode.getDefaultParams();
            if (defaultParams != null) {
                int size = defaultParams.size() - 1;
                while (size > 0) {
                    if (defaultParams.get(size) instanceof AstNode) {
                        int i = size - 1;
                        if (defaultParams.get(i) instanceof String) {
                            AstNode astNode = (AstNode) defaultParams.get(size);
                            String str = (String) defaultParams.get(i);
                            try {
                                perFunctionVariables = perFunctionVariablesCreatePerFunctionVariables;
                                try {
                                    nodeTransform.addChildToFront(createIf(createBinary(51, this.parser.createName(str), this.parser.createName("undefined")), new Node(147, createAssignment(99, this.parser.createName(str), transform(astNode)), nodeTransform.getLineno(), nodeTransform.getColumn()), null, nodeTransform.getLineno(), nodeTransform.getColumn()));
                                } catch (Throwable th) {
                                    th = th;
                                    this.parser.nestingOfFunction--;
                                    perFunctionVariables.restore();
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                perFunctionVariables = perFunctionVariablesCreatePerFunctionVariables;
                                this.parser.nestingOfFunction--;
                                perFunctionVariables.restore();
                                throw th;
                            }
                        } else {
                            perFunctionVariables = perFunctionVariablesCreatePerFunctionVariables;
                        }
                    } else {
                        perFunctionVariables = perFunctionVariablesCreatePerFunctionVariables;
                    }
                    size -= 2;
                    z = z;
                    perFunctionVariablesCreatePerFunctionVariables = perFunctionVariables;
                }
            }
            perFunctionVariables = perFunctionVariablesCreatePerFunctionVariables;
            boolean z2 = z;
            List<Node[]> destructuringRvalues = functionNode.getDestructuringRvalues();
            if (destructuringRvalues != null) {
                for (Node[] nodeArr : destructuringRvalues) {
                    Node node2 = nodeArr[0];
                    Node node3 = nodeArr[z2 ? 1 : 0];
                    if (node3 instanceof AstNode) {
                        AstNode astNode2 = (AstNode) node3;
                        node2.replaceChild(astNode2, transform(astNode2));
                    }
                }
            }
            if (node != null) {
                nodeTransform.addChildToFront(new Node(147, node, lineno, column));
            }
            int functionType = functionNode.getFunctionType();
            Node nodeInitFunction = initFunction(functionNode, iAddFunction, nodeTransform, functionType);
            if (nodeDecompileFunctionHeader != null) {
                this.astNodePos.push(functionNode);
                try {
                    nodeInitFunction = createAssignment(99, nodeDecompileFunctionHeader, nodeInitFunction);
                    this.astNodePos.pop();
                    if (functionType != 2) {
                        nodeInitFunction = createExprStatementNoReturn(nodeInitFunction, functionNode.getLineno(), functionNode.getColumn());
                    }
                } catch (Throwable th3) {
                    this.astNodePos.pop();
                    throw th3;
                }
            }
            this.parser.nestingOfFunction--;
            perFunctionVariables.restore();
            return nodeInitFunction;
        } catch (Throwable th4) {
            th = th4;
            perFunctionVariables = perFunctionVariablesCreatePerFunctionVariables;
        }
    }

    private Node transformFunctionCall(FunctionCall functionCall) {
        this.astNodePos.push(functionCall);
        try {
            Node nodeTransform = transform(functionCall.getTarget());
            Node nodeCreateCallOrNew = createCallOrNew(43, nodeTransform);
            nodeCreateCallOrNew.setLineColumnNumber(functionCall.getLineno(), functionCall.getColumn());
            List<AstNode> arguments = functionCall.getArguments();
            for (int i = 0; i < arguments.size(); i++) {
                nodeCreateCallOrNew.addChildToBack(transform(arguments.get(i)));
            }
            if (functionCall.isOptionalCall()) {
                nodeCreateCallOrNew.putIntProp(30, 1);
            }
            if (nodeTransform.getIntProp(31, 0) == 1) {
                nodeCreateCallOrNew.putIntProp(31, 1);
            }
            return nodeCreateCallOrNew;
        } finally {
            this.astNodePos.pop();
        }
    }

    private Node transformGenExpr(GeneratorExpression generatorExpression) {
        FunctionNode functionNode = new FunctionNode();
        functionNode.setSourceName(this.parser.currentScriptOrFn.getNextTempName());
        functionNode.setIsGenerator();
        functionNode.setFunctionType(2);
        functionNode.setRequiresActivation();
        Node nodeDecompileFunctionHeader = decompileFunctionHeader(functionNode);
        int iAddFunction = this.parser.currentScriptOrFn.addFunction(functionNode);
        Parser.PerFunctionVariables perFunctionVariablesCreatePerFunctionVariables = this.parser.createPerFunctionVariables(functionNode);
        try {
            Node node = (Node) functionNode.getProp(23);
            functionNode.removeProp(23);
            int lineno = generatorExpression.getLineno();
            int column = generatorExpression.getColumn();
            this.parser.nestingOfFunction++;
            Node nodeGenExprTransformHelper = genExprTransformHelper(generatorExpression);
            if (node != null) {
                nodeGenExprTransformHelper.addChildToFront(new Node(147, node, lineno, column));
            }
            int functionType = functionNode.getFunctionType();
            Node nodeInitFunction = initFunction(functionNode, iAddFunction, nodeGenExprTransformHelper, functionType);
            if (nodeDecompileFunctionHeader != null) {
                this.astNodePos.push(functionNode);
                try {
                    nodeInitFunction = createAssignment(99, nodeDecompileFunctionHeader, nodeInitFunction);
                    this.astNodePos.pop();
                    if (functionType != 2) {
                        nodeInitFunction = createExprStatementNoReturn(nodeInitFunction, functionNode.getLineno(), functionNode.getColumn());
                    }
                } catch (Throwable th) {
                    this.astNodePos.pop();
                    throw th;
                }
            }
            this.parser.nestingOfFunction--;
            perFunctionVariablesCreatePerFunctionVariables.restore();
            Node nodeCreateCallOrNew = createCallOrNew(43, nodeInitFunction);
            nodeCreateCallOrNew.setLineColumnNumber(generatorExpression.getLineno(), generatorExpression.getColumn());
            return nodeCreateCallOrNew;
        } catch (Throwable th2) {
            this.parser.nestingOfFunction--;
            perFunctionVariablesCreatePerFunctionVariables.restore();
            throw th2;
        }
    }

    private Node genExprTransformHelper(GeneratorExpression generatorExpression) throws Throwable {
        String string;
        int lineno = generatorExpression.getLineno();
        int column = generatorExpression.getColumn();
        Node nodeTransform = transform(generatorExpression.getResult());
        List<GeneratorExpressionLoop> loops = generatorExpression.getLoops();
        int size = loops.size();
        Node[] nodeArr = new Node[size];
        Node[] nodeArr2 = new Node[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            GeneratorExpressionLoop generatorExpressionLoop = loops.get(i2);
            AstNode iterator = generatorExpressionLoop.getIterator();
            this.astNodePos.push(iterator);
            try {
                if (iterator.getType() == 44) {
                    string = iterator.getString();
                } else {
                    String nextTempName = this.parser.currentScriptOrFn.getNextTempName();
                    this.parser.defineSymbol(96, nextTempName, false);
                    nodeTransform = createBinary(98, createAssignment(99, iterator, this.parser.createName(nextTempName)), nodeTransform);
                    string = nextTempName;
                }
                Node nodeCreateName = this.parser.createName(string);
                this.parser.defineSymbol(167, string, false);
                nodeArr[i2] = nodeCreateName;
                this.astNodePos.pop();
                nodeArr2[i2] = transform(generatorExpressionLoop.getIteratedObject());
            } catch (Throwable th) {
                this.astNodePos.pop();
                throw th;
            }
        }
        Node node = new Node(147, new Node(78, nodeTransform, generatorExpression.getLineno(), generatorExpression.getColumn()), lineno, column);
        if (generatorExpression.getFilter() != null) {
            node = createIf(transform(generatorExpression.getFilter()), node, null, lineno, column);
        }
        Node nodeCreateForIn = node;
        int i3 = size - 1;
        int i4 = 0;
        while (i3 >= 0) {
            try {
                GeneratorExpressionLoop generatorExpressionLoop2 = loops.get(i3);
                Scope scopeCreateLoopNode = createLoopNode(null, generatorExpressionLoop2.getLineno(), generatorExpressionLoop2.getColumn());
                this.parser.pushScope(scopeCreateLoopNode);
                int i5 = i4 + 1;
                try {
                    nodeCreateForIn = createForIn(167, scopeCreateLoopNode, nodeArr[i3], nodeArr2[i3], nodeCreateForIn, generatorExpressionLoop2, generatorExpressionLoop2.isForEach(), generatorExpressionLoop2.isForOf());
                    i3--;
                    i4 = i5;
                } catch (Throwable th2) {
                    th = th2;
                    i4 = i5;
                    while (i < i4) {
                        this.parser.popScope();
                        i++;
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        while (i < i4) {
            this.parser.popScope();
            i++;
        }
        return nodeCreateForIn;
    }

    private Node transformIf(IfStatement ifStatement) {
        return createIf(transform(ifStatement.getCondition()), transform(ifStatement.getThenPart()), ifStatement.getElsePart() != null ? transform(ifStatement.getElsePart()) : null, ifStatement.getLineno(), ifStatement.getColumn());
    }

    private Node transformInfix(InfixExpression infixExpression) {
        Node nodeTransform = transform(infixExpression.getLeft());
        Node nodeTransform2 = transform(infixExpression.getRight());
        Node nodeCreateBinary = createBinary(infixExpression.getType(), nodeTransform, nodeTransform2);
        if (nodeCreateBinary != nodeTransform && nodeCreateBinary != nodeTransform2) {
            nodeCreateBinary.setLineColumnNumber(infixExpression.getLineno(), infixExpression.getColumn());
        }
        return nodeCreateBinary;
    }

    private Node transformLabeledStatement(LabeledStatement labeledStatement) {
        Label firstLabel = labeledStatement.getFirstLabel();
        Node nodeTransform = transform(labeledStatement.getStatement());
        Node nodeNewTarget = Node.newTarget();
        Node node = new Node(143, firstLabel, nodeTransform, nodeNewTarget);
        firstLabel.target = nodeNewTarget;
        return node;
    }

    private Node transformLetNode(LetNode letNode) {
        this.parser.pushScope(letNode);
        try {
            letNode.addChildToBack(transformVariableInitializers(letNode.getVariables()));
            letNode.getType();
            if (letNode.getBody() != null) {
                letNode.addChildToBack(transform(letNode.getBody()));
            }
            return letNode;
        } finally {
            this.parser.popScope();
        }
    }

    private Node transformLiteral(AstNode astNode) {
        if ((astNode.getParent() instanceof FunctionCall) && astNode.getType() == 79) {
            this.parser.reportError("msg.super.shorthand.function");
        }
        return astNode;
    }

    private Node transformNewExpr(NewExpression newExpression) {
        Node nodeCreateCallOrNew = createCallOrNew(30, transform(newExpression.getTarget()));
        nodeCreateCallOrNew.setLineColumnNumber(newExpression.getLineno(), newExpression.getColumn());
        List<AstNode> arguments = newExpression.getArguments();
        for (int i = 0; i < arguments.size(); i++) {
            nodeCreateCallOrNew.addChildToBack(transform(arguments.get(i)));
        }
        if (newExpression.getInitializer() != null) {
            nodeCreateCallOrNew.addChildToBack(transformObjectLiteral(newExpression.getInitializer()));
        }
        return nodeCreateCallOrNew;
    }

    private Node transformObjectLiteral(ObjectLiteral objectLiteral) {
        Object[] objArr;
        int i;
        if (objectLiteral.isDestructuring()) {
            return objectLiteral;
        }
        List<ObjectProperty> elements = objectLiteral.getElements();
        Node node = new Node(72);
        node.setLineColumnNumber(objectLiteral.getLineno(), objectLiteral.getColumn());
        if (elements.isEmpty()) {
            objArr = ScriptRuntime.emptyArgs;
        } else {
            Object[] objArr2 = new Object[elements.size()];
            int i2 = 0;
            for (ObjectProperty objectProperty : elements) {
                Object propKey = Parser.getPropKey(objectProperty.getLeft());
                if (propKey == null) {
                    i = i2 + 1;
                    objArr2[i2] = transform(objectProperty.getLeft());
                } else {
                    i = i2 + 1;
                    objArr2[i2] = propKey;
                }
                i2 = i;
                Node nodeTransform = transform(objectProperty.getRight());
                if (objectProperty.isGetterMethod()) {
                    nodeTransform = createUnary(165, nodeTransform);
                } else if (objectProperty.isSetterMethod()) {
                    nodeTransform = createUnary(166, nodeTransform);
                } else if (objectProperty.isNormalMethod()) {
                    nodeTransform = createUnary(177, nodeTransform);
                }
                node.addChildToBack(nodeTransform);
            }
            objArr = objArr2;
        }
        node.putProp(12, objArr);
        return node;
    }

    private Node transformParenExpr(ParenthesizedExpression parenthesizedExpression) {
        AstNode expression = parenthesizedExpression.getExpression();
        while (expression instanceof ParenthesizedExpression) {
            expression = ((ParenthesizedExpression) expression).getExpression();
        }
        Node nodeTransform = transform(expression);
        nodeTransform.putProp(19, Boolean.TRUE);
        return nodeTransform;
    }

    private Node transformComputedPropertyKey(ComputedPropertyKey computedPropertyKey) {
        return new Node(computedPropertyKey.type, transform(computedPropertyKey.getExpression()));
    }

    private Node transformPropertyGet(PropertyGet propertyGet) {
        return createPropertyGet(transform(propertyGet.getTarget()), null, propertyGet.getProperty().getIdentifier(), 0, propertyGet.type);
    }

    private Node transformTemplateLiteral(TemplateLiteral templateLiteral) {
        List<AstNode> elements = templateLiteral.getElements();
        Node nodeNewString = Node.newString("");
        for (AstNode astNode : elements) {
            if (astNode.getType() != 181) {
                nodeNewString = createBinary(21, nodeNewString, transform(astNode));
            } else {
                String value = ((TemplateCharacters) astNode).getValue();
                if (value.length() > 0) {
                    nodeNewString = createBinary(21, nodeNewString, Node.newString(value));
                }
            }
        }
        return nodeNewString;
    }

    private Node transformTemplateLiteralCall(TaggedTemplateLiteral taggedTemplateLiteral) {
        Node nodeTransform = transform(taggedTemplateLiteral.getTarget());
        Node nodeCreateCallOrNew = createCallOrNew(43, nodeTransform);
        nodeCreateCallOrNew.setLineColumnNumber(taggedTemplateLiteral.getLineno(), taggedTemplateLiteral.getColumn());
        if (nodeTransform.getIntProp(31, 0) == 1) {
            nodeCreateCallOrNew.putIntProp(31, 1);
        }
        TemplateLiteral templateLiteral = (TemplateLiteral) taggedTemplateLiteral.getTemplateLiteral();
        List<AstNode> elements = templateLiteral.getElements();
        nodeCreateCallOrNew.addChildToBack(templateLiteral);
        for (AstNode astNode : elements) {
            if (astNode.getType() != 181) {
                nodeCreateCallOrNew.addChildToBack(transform(astNode));
            }
        }
        this.parser.currentScriptOrFn.addTemplateLiteral(templateLiteral);
        return nodeCreateCallOrNew;
    }

    private Node transformRegExp(RegExpLiteral regExpLiteral) {
        this.parser.currentScriptOrFn.addRegExp(regExpLiteral);
        return regExpLiteral;
    }

    private Node transformReturn(ReturnStatement returnStatement) {
        AstNode returnValue = returnStatement.getReturnValue();
        Node nodeTransform = returnValue == null ? null : transform(returnValue);
        if (returnValue == null) {
            return new Node(4, returnStatement.getLineno(), returnStatement.getColumn());
        }
        return new Node(4, nodeTransform, returnStatement.getLineno(), returnStatement.getColumn());
    }

    private Node transformScript(ScriptNode scriptNode) {
        if (this.parser.currentScope != null) {
            Kit.codeBug();
        }
        this.parser.currentScope = scriptNode;
        Node node = new Node(143);
        Iterator<Node> it = scriptNode.iterator();
        while (it.hasNext()) {
            node.addChildToBack(transform((AstNode) it.next()));
        }
        scriptNode.removeChildren();
        Node firstChild = node.getFirstChild();
        if (firstChild != null) {
            scriptNode.addChildrenToBack(firstChild);
        }
        return scriptNode;
    }

    private Node transformString(StringLiteral stringLiteral) {
        Node nodeNewString = Node.newString(stringLiteral.getValue());
        nodeNewString.setLineColumnNumber(stringLiteral.getLineno(), stringLiteral.getColumn());
        return nodeNewString;
    }

    private Node transformSwitch(SwitchStatement switchStatement) {
        switchStatement.addChildToBack(transform(switchStatement.getExpression()));
        Node node = new Node(143, switchStatement, switchStatement.getLineno(), switchStatement.getColumn());
        for (SwitchCase switchCase : switchStatement.getCases()) {
            AstNode expression = switchCase.getExpression();
            Node nodeTransform = expression != null ? transform(expression) : null;
            List<AstNode> statements = switchCase.getStatements();
            Block block = new Block();
            if (statements != null) {
                Iterator<AstNode> it = statements.iterator();
                while (it.hasNext()) {
                    block.addChildToBack(transform(it.next()));
                }
            }
            addSwitchCase(node, nodeTransform, block);
        }
        closeSwitch(node);
        return node;
    }

    private Node transformThrow(ThrowStatement throwStatement) {
        Node nodeTransform = transform(throwStatement.getExpression());
        nodeTransform.setLineColumnNumber(throwStatement.getLineno(), throwStatement.getColumn());
        Node node = new Node(55, nodeTransform);
        node.setLineColumnNumber(throwStatement.getLineno(), throwStatement.getColumn());
        return node;
    }

    private Node transformTry(TryStatement tryStatement) {
        Node node;
        Node node2;
        Node emptyExpression;
        Node nodeTransform = transform(tryStatement.getTryBlock());
        Block block = new Block();
        Iterator<CatchClause> it = tryStatement.getCatchClauses().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CatchClause next = it.next();
            Name varName = next.getVarName();
            if (varName != null) {
                Node nodeCreateName = this.parser.createName(varName.getIdentifier());
                AstNode catchCondition = next.getCatchCondition();
                if (catchCondition != null) {
                    emptyExpression = this.transform(catchCondition);
                } else {
                    emptyExpression = new EmptyExpression();
                }
                node = nodeCreateName;
                node2 = emptyExpression;
            } else {
                node = null;
                node2 = null;
            }
            IRFactory iRFactory = this;
            block.addChildToBack(iRFactory.createCatch(node, node2, this.transform(next.getBody()), next.getLineno(), next.getColumn()));
            this = iRFactory;
        }
        IRFactory iRFactory2 = this;
        return iRFactory2.createTryCatchFinally(nodeTransform, block, tryStatement.getFinallyBlock() != null ? iRFactory2.transform(tryStatement.getFinallyBlock()) : null, tryStatement.getLineno(), tryStatement.getColumn());
    }

    private Node transformUnary(UnaryExpression unaryExpression) {
        int type = unaryExpression.getType();
        if (type == 82) {
            return transformDefaultXmlNamespace(unaryExpression);
        }
        return createUnary(type, transform(unaryExpression.getOperand()));
    }

    private Node transformUpdate(UpdateExpression updateExpression) {
        return createIncDec(updateExpression.getType(), updateExpression.isPostfix(), transform(updateExpression.getOperand()));
    }

    private Node transformVariables(VariableDeclaration variableDeclaration) {
        transformVariableInitializers(variableDeclaration);
        return variableDeclaration;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private Node transformVariableInitializers(VariableDeclaration variableDeclaration) {
        Node nodeTransform;
        for (VariableInitializer variableInitializer : variableDeclaration.getVariables()) {
            AstNode target = variableInitializer.getTarget();
            AstNode initializer = variableInitializer.getInitializer();
            if (!variableInitializer.isDestructuring()) {
                nodeTransform = target;
                nodeTransform = transform(target);
            }
            Node nodeTransform2 = initializer != null ? transform(initializer) : null;
            if (!variableInitializer.isDestructuring()) {
                if (nodeTransform2 != null) {
                    nodeTransform.addChildToBack(nodeTransform2);
                }
                variableDeclaration.addChildToBack(nodeTransform);
            } else if (nodeTransform2 == null) {
                variableDeclaration.addChildToBack(nodeTransform);
            } else {
                this.astNodePos.push(variableInitializer);
                try {
                    variableDeclaration.addChildToBack(this.parser.createDestructuringAssignment(variableDeclaration.getType(), nodeTransform, nodeTransform2, new IRFactory$$ExternalSyntheticLambda0(this)));
                    this.astNodePos.pop();
                } catch (Throwable th) {
                    this.astNodePos.pop();
                    throw th;
                }
            }
        }
        return variableDeclaration;
    }

    private Node transformWhileLoop(WhileLoop whileLoop) {
        whileLoop.setType(146);
        this.parser.pushScope(whileLoop);
        try {
            return createLoop(whileLoop, 1, transform(whileLoop.getBody()), transform(whileLoop.getCondition()), null, null);
        } finally {
            this.parser.popScope();
        }
    }

    private Node transformWith(WithStatement withStatement) {
        return createWith(transform(withStatement.getExpression()), transform(withStatement.getStatement()), withStatement.getLineno(), withStatement.getColumn());
    }

    private Node transformYield(Yield yield) {
        Node nodeTransform = yield.getValue() == null ? null : transform(yield.getValue());
        if (nodeTransform != null) {
            return new Node(yield.getType(), nodeTransform, yield.getLineno(), yield.getColumn());
        }
        return new Node(yield.getType(), yield.getLineno(), yield.getColumn());
    }

    private Node transformXmlLiteral(XmlLiteral xmlLiteral) {
        Node nodeTransform;
        Node nodeCreateUnary;
        Node node = new Node(30, xmlLiteral.getLineno(), xmlLiteral.getColumn());
        List<XmlFragment> fragments = xmlLiteral.getFragments();
        node.addChildToBack(this.parser.createName(((XmlString) fragments.get(0)).getXml().trim().startsWith("<>") ? "XMLList" : "XML"));
        Node nodeCreateString = null;
        for (XmlFragment xmlFragment : fragments) {
            if (xmlFragment instanceof XmlString) {
                String xml = ((XmlString) xmlFragment).getXml();
                if (nodeCreateString == null) {
                    nodeCreateString = createString(xml);
                } else {
                    nodeCreateString = createBinary(21, nodeCreateString, createString(xml));
                }
            } else {
                XmlExpression xmlExpression = (XmlExpression) xmlFragment;
                boolean zIsXmlAttribute = xmlExpression.isXmlAttribute();
                if (xmlExpression.getExpression() instanceof EmptyExpression) {
                    nodeTransform = createString("");
                } else {
                    nodeTransform = transform(xmlExpression.getExpression());
                }
                if (zIsXmlAttribute) {
                    nodeCreateUnary = createBinary(21, createBinary(21, createString("\""), createUnary(83, nodeTransform)), createString("\""));
                } else {
                    nodeCreateUnary = createUnary(84, nodeTransform);
                }
                nodeCreateString = createBinary(21, nodeCreateString, nodeCreateUnary);
            }
        }
        node.addChildToBack(nodeCreateString);
        return node;
    }

    private Node transformXmlMemberGet(XmlMemberGet xmlMemberGet) {
        XmlRef memberRef = xmlMemberGet.getMemberRef();
        Node nodeTransform = transform(xmlMemberGet.getLeft());
        int i = memberRef.isAttributeAccess() ? 2 : 0;
        if (xmlMemberGet.getType() == 157) {
            i |= 4;
        }
        return transformXmlRef(nodeTransform, memberRef, i);
    }

    private Node transformXmlRef(XmlRef xmlRef) {
        return transformXmlRef(null, xmlRef, xmlRef.isAttributeAccess() ? 2 : 0);
    }

    private Node transformXmlRef(Node node, XmlRef xmlRef, int i) {
        Name namespace = xmlRef.getNamespace();
        String identifier = namespace != null ? namespace.getIdentifier() : null;
        if (xmlRef instanceof XmlPropRef) {
            return createPropertyGet(node, identifier, ((XmlPropRef) xmlRef).getPropName().getIdentifier(), i, xmlRef.type);
        }
        return createElementGet(node, identifier, transform(((XmlElemRef) xmlRef).getExpression()), i);
    }

    private Node transformDefaultXmlNamespace(UnaryExpression unaryExpression) {
        return createUnary(82, transform(unaryExpression.getOperand()));
    }

    private Node transformGeneratorMethodDefinition(GeneratorMethodDefinition generatorMethodDefinition) {
        return transform(generatorMethodDefinition.getMethodName());
    }

    private static void addSwitchCase(Node node, Node node2, Node node3) {
        if (node.getType() != 143) {
            throw Kit.codeBug();
        }
        Jump jump = (Jump) node.getFirstChild();
        if (jump.getType() != 127) {
            throw Kit.codeBug();
        }
        Node nodeNewTarget = Node.newTarget();
        if (node2 != null) {
            Jump jump2 = new Jump(128, node2);
            jump2.target = nodeNewTarget;
            jump.addChildToBack(jump2);
        } else {
            jump.setDefault(nodeNewTarget);
        }
        node.addChildToBack(nodeNewTarget);
        node.addChildToBack(node3);
    }

    private static void closeSwitch(Node node) {
        if (node.getType() != 143) {
            throw Kit.codeBug();
        }
        Jump jump = (Jump) node.getFirstChild();
        if (jump.getType() != 127) {
            throw Kit.codeBug();
        }
        Node nodeNewTarget = Node.newTarget();
        jump.target = nodeNewTarget;
        Node node2 = jump.getDefault();
        if (node2 == null) {
            node2 = nodeNewTarget;
        }
        node.addChildAfter(makeJump(5, node2), jump);
        node.addChildToBack(nodeNewTarget);
    }

    private static Node createExprStatementNoReturn(Node node, int i, int i2) {
        return new Node(147, node, i, i2);
    }

    private static Node createString(String str) {
        return Node.newString(str);
    }

    private Node createCatch(Node node, Node node2, Node node3, int i, int i2) {
        if (node == null) {
            node = new Node(141);
        }
        Node node4 = node;
        if (node2 == null) {
            node2 = new Node(141);
        }
        return new Node(137, node4, node2, node3, i, i2);
    }

    private static Node initFunction(FunctionNode functionNode, int i, Node node, int i2) {
        Name functionName;
        functionNode.setFunctionType(i2);
        functionNode.addChildToBack(node);
        if (functionNode.getFunctionCount() != 0) {
            functionNode.setRequiresActivation();
        }
        if (i2 == 2 && (functionName = functionNode.getFunctionName()) != null && functionName.length() != 0 && functionNode.getSymbol(functionName.getIdentifier()) == null) {
            functionNode.putSymbol(new org.mozilla.javascript.ast.Symbol(122, functionName.getIdentifier()));
            node.addChildrenToFront(new Node(147, new Node(8, Node.newString(54, functionName.getIdentifier()), new Node(69))));
        }
        Node lastChild = node.getLastChild();
        if (lastChild == null || lastChild.getType() != 4) {
            node.addChildToBack(new Node(4));
        }
        Node nodeNewString = Node.newString(122, functionNode.getName());
        nodeNewString.putIntProp(1, i);
        return nodeNewString;
    }

    private Scope createLoopNode(Node node, int i, int i2) {
        Scope scopeCreateScopeNode = this.parser.createScopeNode(146, i, i2);
        if (node != null) {
            ((Jump) node).setLoop(scopeCreateScopeNode);
        }
        return scopeCreateScopeNode;
    }

    private static Node createFor(Scope scope, Node node, Node node2, Node node3, Node node4) {
        if (node.getType() == 167) {
            Scope scopeSplitScope = Scope.splitScope(scope);
            scopeSplitScope.setType(167);
            scopeSplitScope.addChildrenToBack(node);
            scopeSplitScope.addChildToBack(createLoop(scope, 2, node4, node2, new Node(141), node3));
            return scopeSplitScope;
        }
        return createLoop(scope, 2, node4, node2, node, node3);
    }

    private static Node createLoop(Jump jump, int i, Node node, Node node2, Node node3, Node node4) {
        Node nodeNewTarget = Node.newTarget();
        Node nodeNewTarget2 = Node.newTarget();
        if (i == 2 && node2.getType() == 141) {
            node2 = new Node(50);
        }
        Jump jump2 = new Jump(6, node2);
        jump2.target = nodeNewTarget;
        Node nodeNewTarget3 = Node.newTarget();
        jump.addChildToBack(nodeNewTarget);
        jump.addChildrenToBack(node);
        if (i == 1 || i == 2) {
            jump.addChildrenToBack(new Node(141, jump.getLineno(), jump.getColumn()));
        }
        jump.addChildToBack(nodeNewTarget2);
        jump.addChildToBack(jump2);
        jump.addChildToBack(nodeNewTarget3);
        jump.target = nodeNewTarget3;
        if (i == 1 || i == 2) {
            jump.addChildToFront(makeJump(5, nodeNewTarget2));
            if (i == 2) {
                int type = node3.getType();
                if (type != 141) {
                    if (type != 135 && type != 167) {
                        node3 = new Node(147, node3);
                    }
                    jump.addChildToFront(node3);
                }
                nodeNewTarget2 = Node.newTarget();
                jump.addChildAfter(nodeNewTarget2, node);
                if (node4.getType() != 141) {
                    jump.addChildAfter(new Node(147, node4), nodeNewTarget2);
                }
            }
        }
        jump.setContinue(nodeNewTarget2);
        return jump;
    }

    /* JADX WARN: Code duplicated, block: B:36:0x0080  */
    /* JADX WARN: Code duplicated, block: B:37:0x0083 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:38:0x0085  */
    /* JADX WARN: Code duplicated, block: B:39:0x0088 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:40:0x008a  */
    /* JADX WARN: Code duplicated, block: B:41:0x008d  */
    /* JADX WARN: Code duplicated, block: B:45:0x00b9 A[Catch: all -> 0x0109, TRY_ENTER, TryCatch #0 {all -> 0x0109, blocks: (B:3:0x000b, B:11:0x0026, B:13:0x002c, B:34:0x0077, B:42:0x008f, B:45:0x00b9, B:51:0x00cd, B:53:0x00d9, B:58:0x0104, B:57:0x0101, B:52:0x00d5, B:18:0x003a, B:20:0x003e, B:22:0x0048, B:28:0x0059, B:29:0x0064, B:30:0x006a, B:32:0x006e), top: B:63:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:47:0x00c6 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:52:0x00d5 A[Catch: all -> 0x0109, TryCatch #0 {all -> 0x0109, blocks: (B:3:0x000b, B:11:0x0026, B:13:0x002c, B:34:0x0077, B:42:0x008f, B:45:0x00b9, B:51:0x00cd, B:53:0x00d9, B:58:0x0104, B:57:0x0101, B:52:0x00d5, B:18:0x003a, B:20:0x003e, B:22:0x0048, B:28:0x0059, B:29:0x0064, B:30:0x006a, B:32:0x006e), top: B:63:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:55:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:57:0x0101 A[Catch: all -> 0x0109, TryCatch #0 {all -> 0x0109, blocks: (B:3:0x000b, B:11:0x0026, B:13:0x002c, B:34:0x0077, B:42:0x008f, B:45:0x00b9, B:51:0x00cd, B:53:0x00d9, B:58:0x0104, B:57:0x0101, B:52:0x00d5, B:18:0x003a, B:20:0x003e, B:22:0x0048, B:28:0x0059, B:29:0x0064, B:30:0x006a, B:32:0x006e), top: B:63:0x000b }] */
    private Node createForIn(int i, Node node, Node node2, Node node3, Node node4, AstNode astNode, boolean z, boolean z2) {
        Node nodeNewString;
        int i2;
        Node node5;
        Parser parser;
        Node nodeSimpleAssignment;
        Node nodeCreateLoop;
        this.astNodePos.push(astNode);
        try {
            int type = node2.getType();
            Node node6 = null;
            int destructuringLength = 0;
            if (type == 135 || type == 167) {
                Node lastChild = node2.getLastChild();
                type = lastChild.getType();
                if (type == 71 || type == 72) {
                    destructuringLength = lastChild instanceof ArrayLiteral ? ((ArrayLiteral) lastChild).getDestructuringLength() : 0;
                    nodeNewString = lastChild;
                    type = type;
                    node6 = new Node(155);
                    if (z) {
                        i2 = 64;
                    } else if (z2) {
                        i2 = 66;
                    } else if (type != -1) {
                        i2 = 65;
                    } else {
                        i2 = 63;
                    }
                    Node node7 = new Node(i2, node3);
                    node7.putProp(3, node6);
                    Node node8 = new Node(67);
                    node8.putProp(3, node6);
                    node5 = new Node(68);
                    node5.putProp(3, node6);
                    Node node9 = new Node(143);
                    parser = this.parser;
                    if (type != -1) {
                        nodeSimpleAssignment = parser.createDestructuringAssignment(i, nodeNewString, node5, new IRFactory$$ExternalSyntheticLambda0(this));
                        if (!z && !z2 && (type == 72 || destructuringLength != 2)) {
                            this.parser.reportError("msg.bad.for.in.destruct");
                        }
                    } else {
                        nodeSimpleAssignment = parser.simpleAssignment(nodeNewString, node5);
                    }
                    node9.addChildToBack(new Node(147, nodeSimpleAssignment));
                    node9.addChildToBack(node4);
                    nodeCreateLoop = createLoop((Jump) node, 1, node9, node8, null, null);
                    nodeCreateLoop.addChildToFront(node7);
                    if (type != 135 || type == 167) {
                        nodeCreateLoop.addChildToFront(node2);
                    }
                    node6.addChildToBack(nodeCreateLoop);
                } else if (type != 44) {
                    this.parser.reportError("msg.bad.for.in.lhs");
                } else {
                    nodeNewString = Node.newString(44, lastChild.getString());
                    type = -1;
                    node6 = new Node(155);
                    if (z) {
                        i2 = 64;
                    } else if (z2) {
                        i2 = 66;
                    } else if (type != -1) {
                        i2 = 65;
                    } else {
                        i2 = 63;
                    }
                    Node node10 = new Node(i2, node3);
                    node10.putProp(3, node6);
                    Node node11 = new Node(67);
                    node11.putProp(3, node6);
                    node5 = new Node(68);
                    node5.putProp(3, node6);
                    Node node12 = new Node(143);
                    parser = this.parser;
                    if (type != -1) {
                        nodeSimpleAssignment = parser.createDestructuringAssignment(i, nodeNewString, node5, new IRFactory$$ExternalSyntheticLambda0(this));
                        if (!z) {
                            this.parser.reportError("msg.bad.for.in.destruct");
                        }
                    } else {
                        nodeSimpleAssignment = parser.simpleAssignment(nodeNewString, node5);
                    }
                    node12.addChildToBack(new Node(147, nodeSimpleAssignment));
                    node12.addChildToBack(node4);
                    nodeCreateLoop = createLoop((Jump) node, 1, node12, node11, null, null);
                    nodeCreateLoop.addChildToFront(node10);
                    if (type != 135) {
                        nodeCreateLoop.addChildToFront(node2);
                    } else {
                        nodeCreateLoop.addChildToFront(node2);
                    }
                    node6.addChildToBack(nodeCreateLoop);
                }
            } else if (type == 71 || type == 72) {
                destructuringLength = node2 instanceof ArrayLiteral ? ((ArrayLiteral) node2).getDestructuringLength() : 0;
                nodeNewString = node2;
                type = type;
                node6 = new Node(155);
                if (z) {
                    i2 = 64;
                } else if (z2) {
                    i2 = 66;
                } else if (type != -1) {
                    i2 = 65;
                } else {
                    i2 = 63;
                }
                Node node13 = new Node(i2, node3);
                node13.putProp(3, node6);
                Node node14 = new Node(67);
                node14.putProp(3, node6);
                node5 = new Node(68);
                node5.putProp(3, node6);
                Node node15 = new Node(143);
                parser = this.parser;
                if (type != -1) {
                    nodeSimpleAssignment = parser.createDestructuringAssignment(i, nodeNewString, node5, new IRFactory$$ExternalSyntheticLambda0(this));
                    if (!z) {
                        this.parser.reportError("msg.bad.for.in.destruct");
                    }
                } else {
                    nodeSimpleAssignment = parser.simpleAssignment(nodeNewString, node5);
                }
                node15.addChildToBack(new Node(147, nodeSimpleAssignment));
                node15.addChildToBack(node4);
                nodeCreateLoop = createLoop((Jump) node, 1, node15, node14, null, null);
                nodeCreateLoop.addChildToFront(node13);
                if (type != 135) {
                    nodeCreateLoop.addChildToFront(node2);
                } else {
                    nodeCreateLoop.addChildToFront(node2);
                }
                node6.addChildToBack(nodeCreateLoop);
            } else {
                nodeNewString = makeReference(node2);
                if (nodeNewString == null) {
                    this.parser.reportError("msg.bad.for.in.lhs");
                } else {
                    type = -1;
                    node6 = new Node(155);
                    if (z) {
                        i2 = 64;
                    } else if (z2) {
                        i2 = 66;
                    } else if (type != -1) {
                        i2 = 65;
                    } else {
                        i2 = 63;
                    }
                    Node node16 = new Node(i2, node3);
                    node16.putProp(3, node6);
                    Node node17 = new Node(67);
                    node17.putProp(3, node6);
                    node5 = new Node(68);
                    node5.putProp(3, node6);
                    Node node18 = new Node(143);
                    parser = this.parser;
                    if (type != -1) {
                        nodeSimpleAssignment = parser.createDestructuringAssignment(i, nodeNewString, node5, new IRFactory$$ExternalSyntheticLambda0(this));
                        if (!z) {
                            this.parser.reportError("msg.bad.for.in.destruct");
                        }
                    } else {
                        nodeSimpleAssignment = parser.simpleAssignment(nodeNewString, node5);
                    }
                    node18.addChildToBack(new Node(147, nodeSimpleAssignment));
                    node18.addChildToBack(node4);
                    nodeCreateLoop = createLoop((Jump) node, 1, node18, node17, null, null);
                    nodeCreateLoop.addChildToFront(node16);
                    if (type != 135) {
                        nodeCreateLoop.addChildToFront(node2);
                    } else {
                        nodeCreateLoop.addChildToFront(node2);
                    }
                    node6.addChildToBack(nodeCreateLoop);
                }
            }
            return node6;
        } finally {
            this.astNodePos.pop();
        }
    }

    private Node createTryCatchFinally(Node node, Node node2, Node node3, int i, int i2) {
        boolean z = false;
        boolean z2 = node3 != null && (node3.getType() != 143 || node3.hasChildren());
        if (node.getType() != 143 || node.hasChildren() || z2) {
            boolean zHasChildren = node2.hasChildren();
            if (z2 || zHasChildren) {
                Node node4 = new Node(155);
                Jump jump = new Jump(90, node);
                jump.setLineColumnNumber(i, i2);
                int i3 = 3;
                jump.putProp(3, node4);
                if (zHasChildren) {
                    Node nodeNewTarget = Node.newTarget();
                    jump.addChildToBack(makeJump(5, nodeNewTarget));
                    Node nodeNewTarget2 = Node.newTarget();
                    jump.target = nodeNewTarget2;
                    jump.addChildToBack(nodeNewTarget2);
                    Node node5 = new Node(155);
                    Node firstChild = node2.getFirstChild();
                    int i4 = 0;
                    while (firstChild != null) {
                        int lineno = firstChild.getLineno();
                        int column = firstChild.getColumn();
                        Node firstChild2 = firstChild.getFirstChild();
                        Node next = firstChild2.getNext();
                        Node next2 = next.getNext();
                        firstChild.removeChild(firstChild2);
                        firstChild.removeChild(next);
                        firstChild.removeChild(next2);
                        next2.addChildToBack(new Node(i3));
                        next2.addChildToBack(makeJump(5, nodeNewTarget));
                        if (next.getType() == 141) {
                            z = true;
                        } else {
                            next2 = createIf(next, next2, null, lineno, column);
                        }
                        Node node6 = new Node(62, firstChild2, createUseLocal(node4));
                        node6.putProp(3, node5);
                        node6.putIntProp(14, i4);
                        node5.addChildToBack(node6);
                        node5.addChildToBack(createWith(createUseLocal(node5), next2, lineno, column));
                        firstChild = firstChild.getNext();
                        i4++;
                        i3 = 3;
                    }
                    jump.addChildToBack(node5);
                    if (!z) {
                        Node node7 = new Node(56);
                        node7.putProp(3, node4);
                        jump.addChildToBack(node7);
                    }
                    jump.addChildToBack(nodeNewTarget);
                }
                if (z2) {
                    Node nodeNewTarget3 = Node.newTarget();
                    jump.setFinally(nodeNewTarget3);
                    jump.addChildToBack(makeJump(149, nodeNewTarget3));
                    Node nodeNewTarget4 = Node.newTarget();
                    jump.addChildToBack(makeJump(5, nodeNewTarget4));
                    jump.addChildToBack(nodeNewTarget3);
                    Node node8 = new Node(138, node3);
                    node8.putProp(3, node4);
                    jump.addChildToBack(node8);
                    jump.addChildToBack(nodeNewTarget4);
                }
                node4.addChildToBack(jump);
                return node4;
            }
        }
        return node;
    }

    private Node createWith(Node node, Node node2, int i, int i2) {
        this.parser.setRequiresActivation();
        Node node3 = new Node(143, i, i2);
        node3.addChildToBack(new Node(2, node));
        node3.addChildrenToBack(new Node(136, node2, i, i2));
        node3.addChildToBack(new Node(3));
        return node3;
    }

    private static Node createIf(Node node, Node node2, Node node3, int i, int i2) {
        int iIsAlwaysDefinedBoolean = isAlwaysDefinedBoolean(node);
        if (iIsAlwaysDefinedBoolean == 1) {
            return node2;
        }
        if (iIsAlwaysDefinedBoolean == -1) {
            return node3 != null ? node3 : new Node(143, i, i2);
        }
        Node node4 = new Node(143, i, i2);
        Node nodeNewTarget = Node.newTarget();
        Jump jump = new Jump(7, node);
        jump.target = nodeNewTarget;
        node4.addChildToBack(jump);
        node4.addChildrenToBack(node2);
        if (node3 != null) {
            Node nodeNewTarget2 = Node.newTarget();
            node4.addChildToBack(makeJump(5, nodeNewTarget2));
            node4.addChildToBack(nodeNewTarget);
            node4.addChildrenToBack(node3);
            node4.addChildToBack(nodeNewTarget2);
        } else {
            node4.addChildToBack(nodeNewTarget);
        }
        if (node.getFirstChild() != null) {
            Node firstChild = node.getFirstChild();
            node4.setLineColumnNumber(firstChild.getLineno(), firstChild.getColumn());
        }
        return node4;
    }

    private static Node createCondExpr(Node node, Node node2, Node node3) {
        int iIsAlwaysDefinedBoolean = isAlwaysDefinedBoolean(node);
        if (iIsAlwaysDefinedBoolean == 1) {
            return node2;
        }
        return iIsAlwaysDefinedBoolean == -1 ? node3 : new Node(115, node, node2, node3);
    }

    private static Node createUnary(int i, Node node) {
        Node node2;
        int type = node.getType();
        switch (i) {
            case 26:
                int iIsAlwaysDefinedBoolean = isAlwaysDefinedBoolean(node);
                if (iIsAlwaysDefinedBoolean != 0) {
                    int i2 = iIsAlwaysDefinedBoolean == 1 ? 49 : 50;
                    if (type == 50 || type == 49) {
                        node.setType(i2);
                        return node;
                    }
                    return new Node(i2);
                }
                break;
            case 27:
                if (type == 45) {
                    node.setDouble(~ScriptRuntime.toInt32(node.getDouble()));
                    return node;
                }
                break;
            case 29:
                if (type == 45) {
                    node.setDouble(-node.getDouble());
                    return node;
                }
                break;
            case 31:
                if (type == 44) {
                    node.setType(54);
                    node2 = new Node(i, node, Node.newString(node.getString()));
                } else if (type == 33 || type == 39) {
                    Node firstChild = node.getFirstChild();
                    Node lastChild = node.getLastChild();
                    node.removeChild(firstChild);
                    node.removeChild(lastChild);
                    node2 = new Node(i, firstChild, lastChild);
                } else if (type == 73) {
                    Node firstChild2 = node.getFirstChild();
                    node.removeChild(firstChild2);
                    node2 = new Node(75, firstChild2);
                } else {
                    node2 = new Node(i, new Node(50), node);
                }
                if (node.getIntProp(31, 0) == 1) {
                    node2.putIntProp(31, 1);
                }
                return node2;
            case 32:
                if (type == 44) {
                    node.setType(151);
                    return node;
                }
                break;
        }
        return new Node(i, node);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0038  */
    private Node createCallOrNew(int i, Node node) {
        int i2 = 1;
        if (node.getType() == 44) {
            String string = node.getString();
            if (!string.equals("eval")) {
                if (string.equals("With")) {
                    i2 = 2;
                } else {
                    i2 = 0;
                }
            }
        } else if (node.getType() != 33 || !node.getLastChild().getString().equals("eval")) {
            i2 = 0;
        }
        Node node2 = new Node(i, node);
        if (i2 != 0) {
            this.parser.setRequiresActivation();
            node2.putIntProp(10, i2);
        }
        return node2;
    }

    private static Node createIncDec(int i, boolean z, Node node) {
        Node nodeMakeReference = makeReference(node);
        int type = nodeMakeReference.getType();
        if (type == 33 || type == 39 || type == 44 || type == 73) {
            Node node2 = new Node(i, nodeMakeReference);
            int i2 = i == 120 ? 1 : 0;
            if (z) {
                i2 |= 2;
            }
            node2.putIntProp(13, i2);
            return node2;
        }
        throw Kit.codeBug();
    }

    private Node createPropertyGet(Node node, String str, String str2, int i, int i2) {
        if (str == null && i == 0) {
            Parser parser = this.parser;
            if (node == null) {
                return parser.createName(str2);
            }
            parser.checkActivationName(str2, 33);
            if (ScriptRuntime.isSpecialProperty(str2)) {
                if (node.getType() == 79) {
                    if (!(node instanceof KeywordLiteral)) {
                        throw Kit.codeBug();
                    }
                    KeywordLiteral keywordLiteral = (KeywordLiteral) node;
                    KeywordLiteral keywordLiteral2 = new KeywordLiteral(keywordLiteral.getPosition(), keywordLiteral.getLength(), 48);
                    keywordLiteral2.setLineColumnNumber(keywordLiteral.getLineno(), keywordLiteral.getColumn());
                    node = keywordLiteral2;
                }
                Node node2 = new Node(77, node);
                node2.putProp(17, str2);
                Node node3 = new Node(73, node2);
                if (i2 == 186) {
                    node2.putIntProp(30, 1);
                    node3.putIntProp(30, 1);
                }
                return node3;
            }
            Node node4 = new Node(33, node, Node.newString(str2));
            if (i2 == 186) {
                node4.putIntProp(30, 1);
            }
            if (node.getType() == 79) {
                node4.putIntProp(31, 1);
            }
            return node4;
        }
        return createMemberRefGet(node, str, Node.newString(str2), i | 1);
    }

    private Node createElementGet(Node node, String str, Node node2, int i) {
        if (str != null || i != 0) {
            return createMemberRefGet(node, str, node2, i);
        }
        if (node == null) {
            throw Kit.codeBug();
        }
        return new Node(39, node, node2);
    }

    private Node createMemberRefGet(Node node, String str, Node node2, int i) {
        Node nodeCreateName;
        Node node3;
        if (str == null) {
            nodeCreateName = null;
        } else if (str.equals(Marker.ANY_MARKER)) {
            nodeCreateName = new Node(47);
        } else {
            nodeCreateName = this.parser.createName(str);
        }
        if (node == null) {
            if (str == null) {
                node3 = new Node(87, node2);
            } else {
                node3 = new Node(88, nodeCreateName, node2);
            }
        } else if (str == null) {
            node3 = new Node(85, node, node2);
        } else {
            node3 = new Node(86, node, nodeCreateName, node2);
        }
        if (i != 0) {
            node3.putIntProp(16, i);
        }
        return new Node(73, node3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:74:0x00f7, code lost:
    
        if (r0 == 1) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0101, code lost:
    
        if (r0 == (-1)) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0103, code lost:
    
        return r9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static org.mozilla.javascript.Node createBinary(int r7, org.mozilla.javascript.Node r8, org.mozilla.javascript.Node r9) {
        /*
            Method dump skipped, instruction units count: 278
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.IRFactory.createBinary(int, org.mozilla.javascript.Node, org.mozilla.javascript.Node):org.mozilla.javascript.Node");
    }

    private Node createAssignment(int i, Node node, Node node2) {
        int i2;
        Node nodeMakeReference = makeReference(node);
        if (nodeMakeReference == null) {
            if (node.getType() == 71 || node.getType() == 72) {
                Parser parser = this.parser;
                if (i != 99) {
                    parser.reportError("msg.bad.destruct.op");
                    return node2;
                }
                return parser.createDestructuringAssignment(-1, node, node2, new IRFactory$$ExternalSyntheticLambda0(this));
            }
            this.parser.reportError("msg.bad.assign.left");
            return node2;
        }
        switch (i) {
            case 99:
                return propagateSuperFromLhs(this.parser.simpleAssignment(nodeMakeReference, node2), nodeMakeReference);
            case 100:
                i2 = 9;
                break;
            case 101:
                i2 = 117;
                break;
            case 102:
                i2 = 10;
                break;
            case 103:
                i2 = 11;
                break;
            case 104:
                i2 = 118;
                break;
            case 105:
                i2 = 18;
                break;
            case 106:
                i2 = 19;
                break;
            case 107:
                i2 = 20;
                break;
            case 108:
                i2 = 21;
                break;
            case 109:
                i2 = 22;
                break;
            case 110:
                i2 = 23;
                break;
            case 111:
                i2 = 24;
                break;
            case 112:
                i2 = 25;
                break;
            case 113:
                i2 = 81;
                break;
            case 114:
                i2 = 185;
                break;
            default:
                throw Kit.codeBug();
        }
        int type = nodeMakeReference.getType();
        if (type == 33 || type == 39) {
            return propagateSuperFromLhs(new Node(type == 33 ? 153 : 154, nodeMakeReference.getFirstChild(), nodeMakeReference.getLastChild(), new Node(i2, new Node(152), node2)), nodeMakeReference);
        }
        if (type == 44) {
            return propagateSuperFromLhs(new Node(8, Node.newString(54, nodeMakeReference.getString()), new Node(i2, nodeMakeReference, node2)), nodeMakeReference);
        }
        if (type == 73) {
            Node firstChild = nodeMakeReference.getFirstChild();
            this.parser.checkMutableReference(firstChild);
            return propagateSuperFromLhs(new Node(156, firstChild, new Node(i2, new Node(152), node2)), nodeMakeReference);
        }
        throw Kit.codeBug();
    }

    private Node propagateSuperFromLhs(Node node, Node node2) {
        if (node2.getIntProp(31, 0) == 1) {
            node.putIntProp(31, 1);
        }
        return node;
    }

    private static Node createUseLocal(Node node) {
        if (155 != node.getType()) {
            throw Kit.codeBug();
        }
        Node node2 = new Node(59);
        node2.putProp(3, node);
        return node2;
    }

    private static Jump makeJump(int i, Node node) {
        Jump jump = new Jump(i);
        jump.target = node;
        return jump;
    }

    private static Node makeReference(Node node) {
        int type = node.getType();
        if (type != 33 && type != 39 && type != 73) {
            if (type == 43) {
                node.setType(76);
                return new Node(73, node);
            }
            if (type != 44) {
                return null;
            }
        }
        return node;
    }

    private static int isAlwaysDefinedBoolean(Node node) {
        int type = node.getType();
        if (type == 45) {
            double d = node.getDouble();
            return (Double.isNaN(d) || d == 0.0d) ? -1 : 1;
        }
        if (type == 47 || type == 49) {
            return -1;
        }
        return type != 50 ? 0 : 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    boolean isDestructuring(Node node) {
        return (node instanceof DestructuringForm) && ((DestructuringForm) node).isDestructuring();
    }

    Node decompileFunctionHeader(FunctionNode functionNode) {
        if (functionNode.getFunctionName() == null && functionNode.getMemberExprNode() != null) {
            return transform(functionNode.getMemberExprNode());
        }
        return null;
    }

    public static class AstNodePosition implements Parser.CurrentPositionReporter {
        private String savedLine;
        private int savedLineOffset;
        private String sourceString;
        private int savedLineno = -1;
        private ArrayDeque<AstNode> stack = new ArrayDeque<>();

        public AstNodePosition(String str) {
            this.sourceString = str;
        }

        public void push(AstNode astNode) {
            this.stack.push(astNode);
        }

        public void pop() {
            this.stack.pop();
        }

        @Override // org.mozilla.javascript.Parser.CurrentPositionReporter
        public int getPosition() {
            return this.stack.peek().getAbsolutePosition();
        }

        @Override // org.mozilla.javascript.Parser.CurrentPositionReporter
        public int getLength() {
            return this.stack.peek().getLength();
        }

        @Override // org.mozilla.javascript.Parser.CurrentPositionReporter
        public int getLineno() {
            return this.stack.peek().getLineno();
        }

        private void cutAndSaveLine() {
            int lineno = getLineno();
            if (this.savedLineno == lineno) {
                return;
            }
            int i = 0;
            boolean z = false;
            int i2 = 1;
            while (i < this.sourceString.length()) {
                char cCharAt = this.sourceString.charAt(i);
                if (!z || cCharAt != '\n') {
                    z = cCharAt == '\r';
                    if (i2 == lineno) {
                        break;
                    } else if (ScriptRuntime.isJSLineTerminator(cCharAt)) {
                        i2++;
                    }
                }
                i++;
            }
            int i3 = i;
            while (i3 < this.sourceString.length() && !ScriptRuntime.isJSLineTerminator(this.sourceString.charAt(i3))) {
                i3++;
            }
            this.savedLineno = lineno;
            if (i3 == 0) {
                this.savedLine = "";
                this.savedLineOffset = 0;
            } else {
                this.savedLine = this.sourceString.substring(i, i3);
                this.savedLineOffset = (getPosition() - i) + 1;
            }
        }

        @Override // org.mozilla.javascript.Parser.CurrentPositionReporter
        public String getLine() {
            cutAndSaveLine();
            return this.savedLine;
        }

        @Override // org.mozilla.javascript.Parser.CurrentPositionReporter
        public int getOffset() {
            cutAndSaveLine();
            return this.savedLineOffset;
        }
    }
}
