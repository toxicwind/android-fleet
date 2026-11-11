package org.mozilla.javascript.optimizer;

import jdk.dynalink.NamedOperation;
import jdk.dynalink.Namespace;
import jdk.dynalink.NamespaceOperation;
import jdk.dynalink.Operation;

/* JADX INFO: loaded from: classes8.dex */
class ParsedOperation {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final String name;
    private final Namespace namespace;
    private final Operation operation;
    private final Operation root;

    ParsedOperation(Operation operation) {
        this.root = operation;
        Object name = NamedOperation.getName(operation);
        if (name instanceof String) {
            this.name = (String) name;
        } else {
            if (name != null) {
                throw new UnsupportedOperationException(operation.toString());
            }
            this.name = "";
        }
        NamespaceOperation baseOperation = NamedOperation.getBaseOperation(operation);
        this.namespace = baseOperation.getNamespace(0);
        this.operation = baseOperation.getBaseOperation();
    }

    boolean isNamespace(Namespace namespace) {
        return namespace.equals(this.namespace);
    }

    boolean isOperation(Operation operation) {
        return operation.equals(this.operation);
    }

    boolean isOperation(Operation operation, Operation operation2) {
        return operation.equals(this.operation) || operation2.equals(this.operation);
    }

    String getName() {
        return this.name;
    }

    public String toString() {
        return this.root.toString();
    }
}
