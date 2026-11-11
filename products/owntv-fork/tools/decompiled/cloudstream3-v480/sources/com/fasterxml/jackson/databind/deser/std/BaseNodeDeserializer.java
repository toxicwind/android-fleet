package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: JsonNodeDeserializer.java */
/* JADX INFO: loaded from: classes5.dex */
abstract class BaseNodeDeserializer<T extends JsonNode> extends StdDeserializer<T> {
    protected final Boolean _supportsUpdates;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public boolean isCachable() {
        return true;
    }

    public BaseNodeDeserializer(Class<T> cls, Boolean bool) {
        super((Class<?>) cls);
        this._supportsUpdates = bool;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.Untyped;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return this._supportsUpdates;
    }

    protected void _handleDuplicateField(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory, String str, ObjectNode objectNode, JsonNode jsonNode, JsonNode jsonNode2) throws IOException {
        if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY)) {
            deserializationContext.reportInputMismatch(JsonNode.class, "Duplicate field '%s' for `ObjectNode`: not allowed when `DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY` enabled", str);
        }
        if (deserializationContext.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES)) {
            if (jsonNode.isArray()) {
                ((ArrayNode) jsonNode).add(jsonNode2);
                objectNode.replace(str, jsonNode);
            } else {
                ArrayNode arrayNode = jsonNodeFactory.arrayNode();
                arrayNode.add(jsonNode);
                arrayNode.add(jsonNode2);
                objectNode.replace(str, arrayNode);
            }
        }
    }

    protected final ObjectNode _deserializeObjectAtName(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory, ContainerStack containerStack) throws IOException {
        JsonNode jsonNode_deserializeContainerNoRecursion;
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        String strCurrentName = jsonParser.currentName();
        while (strCurrentName != null) {
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (jsonTokenNextToken == null) {
                jsonTokenNextToken = JsonToken.NOT_AVAILABLE;
            }
            int iId = jsonTokenNextToken.id();
            if (iId == 1) {
                jsonNode_deserializeContainerNoRecursion = _deserializeContainerNoRecursion(jsonParser, deserializationContext, jsonNodeFactory, containerStack, jsonNodeFactory.objectNode());
            } else if (iId == 3) {
                jsonNode_deserializeContainerNoRecursion = _deserializeContainerNoRecursion(jsonParser, deserializationContext, jsonNodeFactory, containerStack, jsonNodeFactory.arrayNode());
            } else {
                jsonNode_deserializeContainerNoRecursion = _deserializeAnyScalar(jsonParser, deserializationContext);
            }
            JsonNode jsonNode = jsonNode_deserializeContainerNoRecursion;
            JsonNode jsonNodeReplace = objectNode.replace(strCurrentName, jsonNode);
            if (jsonNodeReplace != null) {
                _handleDuplicateField(jsonParser, deserializationContext, jsonNodeFactory, strCurrentName, objectNode, jsonNodeReplace, jsonNode);
            }
            strCurrentName = jsonParser.nextFieldName();
        }
        return objectNode;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x005b  */
    /* JADX WARN: Code duplicated, block: B:29:0x0060  */
    /* JADX WARN: Code duplicated, block: B:32:0x0069  */
    /* JADX WARN: Code duplicated, block: B:34:0x006c  */
    /* JADX WARN: Code duplicated, block: B:36:0x006f  */
    /* JADX WARN: Code duplicated, block: B:38:0x0072  */
    /* JADX WARN: Code duplicated, block: B:39:0x0075  */
    /* JADX WARN: Code duplicated, block: B:41:0x007b  */
    /* JADX WARN: Code duplicated, block: B:42:0x0080  */
    /* JADX WARN: Code duplicated, block: B:43:0x0086  */
    /* JADX WARN: Code duplicated, block: B:44:0x008b  */
    /* JADX WARN: Code duplicated, block: B:45:0x0090  */
    /* JADX WARN: Code duplicated, block: B:46:0x0099  */
    /* JADX WARN: Code duplicated, block: B:47:0x00a3  */
    protected final JsonNode updateObject(JsonParser jsonParser, DeserializationContext deserializationContext, ObjectNode objectNode, ContainerStack containerStack) throws IOException {
        String strCurrentName;
        JsonParser jsonParser2;
        DeserializationContext deserializationContext2;
        ContainerStack containerStack2;
        int iId;
        BaseNodeDeserializer<T> baseNodeDeserializer;
        JsonNode jsonNode_deserializeContainerNoRecursion;
        if (jsonParser.isExpectedStartObjectToken()) {
            strCurrentName = jsonParser.nextFieldName();
        } else {
            if (!jsonParser.hasToken(JsonToken.FIELD_NAME)) {
                return deserialize(jsonParser, deserializationContext);
            }
            strCurrentName = jsonParser.currentName();
        }
        JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
        while (strCurrentName != null) {
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            JsonNode jsonNode = objectNode.get(strCurrentName);
            if (jsonNode != null) {
                if (jsonNode instanceof ObjectNode) {
                    if (jsonTokenNextToken == JsonToken.START_OBJECT) {
                        JsonNode jsonNodeUpdateObject = this.updateObject(jsonParser, deserializationContext, (ObjectNode) jsonNode, containerStack);
                        if (jsonNodeUpdateObject != jsonNode) {
                            objectNode.set(strCurrentName, jsonNodeUpdateObject);
                        }
                        baseNodeDeserializer = this;
                        jsonParser2 = jsonParser;
                        deserializationContext2 = deserializationContext;
                        containerStack2 = containerStack;
                    } else {
                        jsonParser2 = jsonParser;
                        deserializationContext2 = deserializationContext;
                        containerStack2 = containerStack;
                        if (jsonTokenNextToken == null) {
                            jsonTokenNextToken = JsonToken.NOT_AVAILABLE;
                        }
                        iId = jsonTokenNextToken.id();
                        if (iId == 1) {
                            baseNodeDeserializer = this;
                            jsonNode_deserializeContainerNoRecursion = baseNodeDeserializer._deserializeContainerNoRecursion(jsonParser2, deserializationContext2, nodeFactory, containerStack2, nodeFactory.objectNode());
                        } else if (iId != 3) {
                            if (iId == 6) {
                                jsonNode_deserializeContainerNoRecursion = nodeFactory.textNode(jsonParser2.getText());
                            } else if (iId == 7) {
                                jsonNode_deserializeContainerNoRecursion = this._fromInt(jsonParser2, deserializationContext2, nodeFactory);
                            } else {
                                switch (iId) {
                                    case 9:
                                        jsonNode_deserializeContainerNoRecursion = nodeFactory.booleanNode(true);
                                        break;
                                    case 10:
                                        jsonNode_deserializeContainerNoRecursion = nodeFactory.booleanNode(false);
                                        break;
                                    case 11:
                                        jsonNode_deserializeContainerNoRecursion = nodeFactory.nullNode();
                                        break;
                                    default:
                                        jsonNode_deserializeContainerNoRecursion = this._deserializeRareScalar(jsonParser2, deserializationContext2);
                                        break;
                                }
                            }
                            baseNodeDeserializer = this;
                        } else {
                            baseNodeDeserializer = this;
                            jsonNode_deserializeContainerNoRecursion = baseNodeDeserializer._deserializeContainerNoRecursion(jsonParser2, deserializationContext2, nodeFactory, containerStack2, nodeFactory.arrayNode());
                        }
                        objectNode.set(strCurrentName, jsonNode_deserializeContainerNoRecursion);
                    }
                } else if ((jsonNode instanceof ArrayNode) && jsonTokenNextToken == JsonToken.START_ARRAY) {
                    ArrayNode arrayNode = (ArrayNode) jsonNode;
                    baseNodeDeserializer = this;
                    jsonParser2 = jsonParser;
                    deserializationContext2 = deserializationContext;
                    containerStack2 = containerStack;
                    baseNodeDeserializer._deserializeContainerNoRecursion(jsonParser2, deserializationContext2, nodeFactory, containerStack2, arrayNode);
                } else {
                    jsonParser2 = jsonParser;
                    deserializationContext2 = deserializationContext;
                    containerStack2 = containerStack;
                    if (jsonTokenNextToken == null) {
                        jsonTokenNextToken = JsonToken.NOT_AVAILABLE;
                    }
                    iId = jsonTokenNextToken.id();
                    if (iId == 1) {
                        baseNodeDeserializer = this;
                        jsonNode_deserializeContainerNoRecursion = baseNodeDeserializer._deserializeContainerNoRecursion(jsonParser2, deserializationContext2, nodeFactory, containerStack2, nodeFactory.objectNode());
                    } else if (iId != 3) {
                        if (iId == 6) {
                            jsonNode_deserializeContainerNoRecursion = nodeFactory.textNode(jsonParser2.getText());
                        } else if (iId == 7) {
                            jsonNode_deserializeContainerNoRecursion = this._fromInt(jsonParser2, deserializationContext2, nodeFactory);
                        } else {
                            switch (iId) {
                                case 9:
                                    jsonNode_deserializeContainerNoRecursion = nodeFactory.booleanNode(true);
                                    break;
                                case 10:
                                    jsonNode_deserializeContainerNoRecursion = nodeFactory.booleanNode(false);
                                    break;
                                case 11:
                                    jsonNode_deserializeContainerNoRecursion = nodeFactory.nullNode();
                                    break;
                                default:
                                    jsonNode_deserializeContainerNoRecursion = this._deserializeRareScalar(jsonParser2, deserializationContext2);
                                    break;
                            }
                        }
                        baseNodeDeserializer = this;
                    } else {
                        baseNodeDeserializer = this;
                        jsonNode_deserializeContainerNoRecursion = baseNodeDeserializer._deserializeContainerNoRecursion(jsonParser2, deserializationContext2, nodeFactory, containerStack2, nodeFactory.arrayNode());
                    }
                    objectNode.set(strCurrentName, jsonNode_deserializeContainerNoRecursion);
                }
            } else {
                jsonParser2 = jsonParser;
                deserializationContext2 = deserializationContext;
                containerStack2 = containerStack;
                if (jsonTokenNextToken == null) {
                    jsonTokenNextToken = JsonToken.NOT_AVAILABLE;
                }
                iId = jsonTokenNextToken.id();
                if (iId == 1) {
                    baseNodeDeserializer = this;
                    jsonNode_deserializeContainerNoRecursion = baseNodeDeserializer._deserializeContainerNoRecursion(jsonParser2, deserializationContext2, nodeFactory, containerStack2, nodeFactory.objectNode());
                } else if (iId != 3) {
                    if (iId == 6) {
                        jsonNode_deserializeContainerNoRecursion = nodeFactory.textNode(jsonParser2.getText());
                    } else if (iId == 7) {
                        jsonNode_deserializeContainerNoRecursion = this._fromInt(jsonParser2, deserializationContext2, nodeFactory);
                    } else {
                        switch (iId) {
                            case 9:
                                jsonNode_deserializeContainerNoRecursion = nodeFactory.booleanNode(true);
                                break;
                            case 10:
                                jsonNode_deserializeContainerNoRecursion = nodeFactory.booleanNode(false);
                                break;
                            case 11:
                                jsonNode_deserializeContainerNoRecursion = nodeFactory.nullNode();
                                break;
                            default:
                                jsonNode_deserializeContainerNoRecursion = this._deserializeRareScalar(jsonParser2, deserializationContext2);
                                break;
                        }
                    }
                    baseNodeDeserializer = this;
                } else {
                    baseNodeDeserializer = this;
                    jsonNode_deserializeContainerNoRecursion = baseNodeDeserializer._deserializeContainerNoRecursion(jsonParser2, deserializationContext2, nodeFactory, containerStack2, nodeFactory.arrayNode());
                }
                objectNode.set(strCurrentName, jsonNode_deserializeContainerNoRecursion);
            }
            strCurrentName = jsonParser2.nextFieldName();
            this = baseNodeDeserializer;
            jsonParser = jsonParser2;
            deserializationContext = deserializationContext2;
            containerStack = containerStack2;
        }
        return objectNode;
    }

    protected final ContainerNode<?> _deserializeContainerNoRecursion(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory, ContainerStack containerStack, ContainerNode<?> containerNode) throws IOException {
        JsonNode jsonNodeTextNode;
        JsonNodeFactory jsonNodeFactory2 = jsonNodeFactory;
        int deserializationFeatures = deserializationContext.getDeserializationFeatures() & F_MASK_INT_COERCIONS;
        ContainerNode containerNodeObjectNode = containerNode;
        do {
            if (containerNodeObjectNode instanceof ObjectNode) {
                ContainerNode containerNode2 = containerNodeObjectNode;
                ObjectNode objectNode = (ObjectNode) containerNodeObjectNode;
                String strNextFieldName = jsonParser.nextFieldName();
                while (true) {
                    if (strNextFieldName != null) {
                        JsonToken jsonTokenNextToken = jsonParser.nextToken();
                        if (jsonTokenNextToken == null) {
                            jsonTokenNextToken = JsonToken.NOT_AVAILABLE;
                        }
                        int iId = jsonTokenNextToken.id();
                        if (iId == 1) {
                            ObjectNode objectNode2 = jsonNodeFactory.objectNode();
                            JsonNode jsonNodeReplace = objectNode.replace(strNextFieldName, objectNode2);
                            if (jsonNodeReplace != null) {
                                jsonNodeFactory2 = jsonNodeFactory;
                                _handleDuplicateField(jsonParser, deserializationContext, jsonNodeFactory2, strNextFieldName, objectNode, jsonNodeReplace, objectNode2);
                            } else {
                                jsonNodeFactory2 = jsonNodeFactory;
                            }
                            containerStack.push(containerNode2);
                            objectNode = objectNode2;
                            containerNode2 = objectNode;
                        } else if (iId == 3) {
                            ContainerNode containerNodeArrayNode = jsonNodeFactory.arrayNode();
                            JsonNode jsonNodeReplace2 = objectNode.replace(strNextFieldName, containerNodeArrayNode);
                            if (jsonNodeReplace2 != null) {
                                _handleDuplicateField(jsonParser, deserializationContext, jsonNodeFactory, strNextFieldName, objectNode, jsonNodeReplace2, containerNodeArrayNode);
                            }
                            containerStack.push(containerNode2);
                            jsonNodeFactory2 = jsonNodeFactory;
                            containerNodeObjectNode = containerNodeArrayNode;
                        } else {
                            switch (iId) {
                                case 6:
                                    jsonNodeTextNode = jsonNodeFactory2.textNode(jsonParser.getText());
                                    break;
                                case 7:
                                    jsonNodeTextNode = _fromInt(jsonParser, deserializationFeatures, jsonNodeFactory2);
                                    break;
                                case 8:
                                    jsonNodeTextNode = _fromFloat(jsonParser, deserializationContext, jsonNodeFactory);
                                    break;
                                case 9:
                                    jsonNodeTextNode = jsonNodeFactory2.booleanNode(true);
                                    break;
                                case 10:
                                    jsonNodeTextNode = jsonNodeFactory2.booleanNode(false);
                                    break;
                                case 11:
                                    jsonNodeTextNode = jsonNodeFactory2.nullNode();
                                    break;
                                default:
                                    jsonNodeTextNode = _deserializeRareScalar(jsonParser, deserializationContext);
                                    break;
                            }
                            JsonNode jsonNode = jsonNodeTextNode;
                            JsonNode jsonNodeReplace3 = objectNode.replace(strNextFieldName, jsonNode);
                            if (jsonNodeReplace3 != null) {
                                _handleDuplicateField(jsonParser, deserializationContext, jsonNodeFactory2, strNextFieldName, objectNode, jsonNodeReplace3, jsonNode);
                            }
                            jsonNodeFactory2 = jsonNodeFactory;
                        }
                        strNextFieldName = jsonParser.nextFieldName();
                    } else {
                        containerNodeObjectNode = containerStack.popOrNull();
                    }
                }
            } else {
                ArrayNode arrayNode = (ArrayNode) containerNodeObjectNode;
                while (true) {
                    JsonToken jsonTokenNextToken2 = jsonParser.nextToken();
                    if (jsonTokenNextToken2 == null) {
                        jsonTokenNextToken2 = JsonToken.NOT_AVAILABLE;
                    }
                    switch (jsonTokenNextToken2.id()) {
                        case 1:
                            containerStack.push(containerNodeObjectNode);
                            containerNodeObjectNode = jsonNodeFactory2.objectNode();
                            arrayNode.add(containerNodeObjectNode);
                            break;
                        case 2:
                        case 5:
                        default:
                            arrayNode.add(_deserializeRareScalar(jsonParser, deserializationContext));
                            break;
                        case 3:
                            containerStack.push(containerNodeObjectNode);
                            containerNodeObjectNode = jsonNodeFactory2.arrayNode();
                            arrayNode.add(containerNodeObjectNode);
                            break;
                        case 4:
                            containerNodeObjectNode = containerStack.popOrNull();
                            break;
                        case 6:
                            arrayNode.add(jsonNodeFactory2.textNode(jsonParser.getText()));
                            break;
                        case 7:
                            arrayNode.add(_fromInt(jsonParser, deserializationFeatures, jsonNodeFactory2));
                            break;
                        case 8:
                            arrayNode.add(_fromFloat(jsonParser, deserializationContext, jsonNodeFactory));
                            break;
                        case 9:
                            arrayNode.add(jsonNodeFactory2.booleanNode(true));
                            break;
                        case 10:
                            arrayNode.add(jsonNodeFactory2.booleanNode(false));
                            break;
                        case 11:
                            arrayNode.add(jsonNodeFactory2.nullNode());
                            break;
                    }
                }
            }
        } while (containerNodeObjectNode != null);
        return containerNode;
    }

    protected final JsonNode _deserializeAnyScalar(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
        int iCurrentTokenId = jsonParser.currentTokenId();
        if (iCurrentTokenId == 2) {
            return nodeFactory.objectNode();
        }
        switch (iCurrentTokenId) {
            case 6:
                return nodeFactory.textNode(jsonParser.getText());
            case 7:
                return _fromInt(jsonParser, deserializationContext, nodeFactory);
            case 8:
                return _fromFloat(jsonParser, deserializationContext, nodeFactory);
            case 9:
                return nodeFactory.booleanNode(true);
            case 10:
                return nodeFactory.booleanNode(false);
            case 11:
                return nodeFactory.nullNode();
            case 12:
                return _fromEmbedded(jsonParser, deserializationContext);
            default:
                return (JsonNode) deserializationContext.handleUnexpectedToken(handledType(), jsonParser);
        }
    }

    protected final JsonNode _deserializeRareScalar(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        int iCurrentTokenId = jsonParser.currentTokenId();
        if (iCurrentTokenId == 2) {
            return deserializationContext.getNodeFactory().objectNode();
        }
        if (iCurrentTokenId == 8) {
            return _fromFloat(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
        }
        if (iCurrentTokenId == 12) {
            return _fromEmbedded(jsonParser, deserializationContext);
        }
        return (JsonNode) deserializationContext.handleUnexpectedToken(handledType(), jsonParser);
    }

    protected final JsonNode _fromInt(JsonParser jsonParser, int i, JsonNodeFactory jsonNodeFactory) throws IOException {
        if (i != 0) {
            if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(i)) {
                return jsonNodeFactory.numberNode(jsonParser.getBigIntegerValue());
            }
            return jsonNodeFactory.numberNode(jsonParser.getLongValue());
        }
        JsonParser.NumberType numberType = jsonParser.getNumberType();
        if (numberType == JsonParser.NumberType.INT) {
            return jsonNodeFactory.numberNode(jsonParser.getIntValue());
        }
        if (numberType == JsonParser.NumberType.LONG) {
            return jsonNodeFactory.numberNode(jsonParser.getLongValue());
        }
        return jsonNodeFactory.numberNode(jsonParser.getBigIntegerValue());
    }

    protected final JsonNode _fromInt(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonParser.NumberType numberType;
        int deserializationFeatures = deserializationContext.getDeserializationFeatures();
        if ((F_MASK_INT_COERCIONS & deserializationFeatures) != 0) {
            if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(deserializationFeatures)) {
                numberType = JsonParser.NumberType.BIG_INTEGER;
            } else if (DeserializationFeature.USE_LONG_FOR_INTS.enabledIn(deserializationFeatures)) {
                numberType = JsonParser.NumberType.LONG;
            } else {
                numberType = jsonParser.getNumberType();
            }
        } else {
            numberType = jsonParser.getNumberType();
        }
        if (numberType == JsonParser.NumberType.INT) {
            return jsonNodeFactory.numberNode(jsonParser.getIntValue());
        }
        if (numberType == JsonParser.NumberType.LONG) {
            return jsonNodeFactory.numberNode(jsonParser.getLongValue());
        }
        return jsonNodeFactory.numberNode(jsonParser.getBigIntegerValue());
    }

    protected final JsonNode _fromFloat(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonParser.NumberType numberType = jsonParser.getNumberType();
        if (numberType == JsonParser.NumberType.BIG_DECIMAL) {
            return jsonNodeFactory.numberNode(jsonParser.getDecimalValue());
        }
        if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            if (jsonParser.isNaN()) {
                return jsonNodeFactory.numberNode(jsonParser.getDoubleValue());
            }
            return jsonNodeFactory.numberNode(jsonParser.getDecimalValue());
        }
        if (numberType == JsonParser.NumberType.FLOAT) {
            return jsonNodeFactory.numberNode(jsonParser.getFloatValue());
        }
        return jsonNodeFactory.numberNode(jsonParser.getDoubleValue());
    }

    protected final JsonNode _fromEmbedded(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
        Object embeddedObject = jsonParser.getEmbeddedObject();
        if (embeddedObject == null) {
            return nodeFactory.nullNode();
        }
        if (embeddedObject.getClass() == byte[].class) {
            return nodeFactory.binaryNode((byte[]) embeddedObject);
        }
        if (embeddedObject instanceof RawValue) {
            return nodeFactory.rawValueNode((RawValue) embeddedObject);
        }
        if (embeddedObject instanceof JsonNode) {
            return (JsonNode) embeddedObject;
        }
        return nodeFactory.pojoNode(embeddedObject);
    }

    /* JADX INFO: compiled from: JsonNodeDeserializer.java */
    static final class ContainerStack {
        private int _end;
        private ContainerNode[] _stack;
        private int _top;

        public int size() {
            return this._top;
        }

        public void push(ContainerNode containerNode) {
            int i = this._top;
            int i2 = this._end;
            ContainerNode[] containerNodeArr = this._stack;
            if (i < i2) {
                this._top = i + 1;
                containerNodeArr[i] = containerNode;
                return;
            }
            if (containerNodeArr == null) {
                this._end = 10;
                this._stack = new ContainerNode[10];
            } else {
                int iMin = i2 + Math.min(4000, Math.max(20, i2 >> 1));
                this._end = iMin;
                this._stack = (ContainerNode[]) Arrays.copyOf(this._stack, iMin);
            }
            ContainerNode[] containerNodeArr2 = this._stack;
            int i3 = this._top;
            this._top = i3 + 1;
            containerNodeArr2[i3] = containerNode;
        }

        public ContainerNode popOrNull() {
            int i = this._top;
            if (i == 0) {
                return null;
            }
            ContainerNode[] containerNodeArr = this._stack;
            int i2 = i - 1;
            this._top = i2;
            return containerNodeArr[i2];
        }
    }
}
