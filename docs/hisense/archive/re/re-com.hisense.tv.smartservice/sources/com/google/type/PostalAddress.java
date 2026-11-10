package com.google.type;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class PostalAddress extends GeneratedMessageLite<PostalAddress, Builder> implements PostalAddressOrBuilder {
    public static final int ADDRESS_LINES_FIELD_NUMBER = 9;
    public static final int ADMINISTRATIVE_AREA_FIELD_NUMBER = 6;
    public static final PostalAddress DEFAULT_INSTANCE;
    public static final int LANGUAGE_CODE_FIELD_NUMBER = 3;
    public static final int LOCALITY_FIELD_NUMBER = 7;
    public static final int ORGANIZATION_FIELD_NUMBER = 11;
    public static volatile Parser<PostalAddress> PARSER = null;
    public static final int POSTAL_CODE_FIELD_NUMBER = 4;
    public static final int RECIPIENTS_FIELD_NUMBER = 10;
    public static final int REGION_CODE_FIELD_NUMBER = 2;
    public static final int REVISION_FIELD_NUMBER = 1;
    public static final int SORTING_CODE_FIELD_NUMBER = 5;
    public static final int SUBLOCALITY_FIELD_NUMBER = 8;
    public int revision_;
    public String regionCode_ = "";
    public String languageCode_ = "";
    public String postalCode_ = "";
    public String sortingCode_ = "";
    public String administrativeArea_ = "";
    public String locality_ = "";
    public String sublocality_ = "";
    public Internal.ProtobufList<String> addressLines_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<String> recipients_ = GeneratedMessageLite.emptyProtobufList();
    public String organization_ = "";

    /* JADX INFO: renamed from: com.google.type.PostalAddress$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = new int[GeneratedMessageLite.MethodToInvoke.values().length];

        static {
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    static {
        PostalAddress postalAddress = new PostalAddress();
        DEFAULT_INSTANCE = postalAddress;
        GeneratedMessageLite.defaultInstanceMap.put(PostalAddress.class, postalAddress);
    }

    public static /* synthetic */ void access$2600(PostalAddress postalAddress, Iterable iterable) {
        postalAddress.ensureAddressLinesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) postalAddress.addressLines_);
    }

    public static /* synthetic */ void access$3100(PostalAddress postalAddress, Iterable iterable) {
        postalAddress.ensureRecipientsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) postalAddress.recipients_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAddressLines(String str) {
        str.getClass();
        ensureAddressLinesIsMutable();
        this.addressLines_.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAddressLinesBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        ensureAddressLinesIsMutable();
        this.addressLines_.add(byteString.toStringUtf8());
    }

    private void addAllAddressLines(Iterable<String> iterable) {
        ensureAddressLinesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.addressLines_);
    }

    private void addAllRecipients(Iterable<String> iterable) {
        ensureRecipientsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.recipients_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRecipients(String str) {
        str.getClass();
        ensureRecipientsIsMutable();
        this.recipients_.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRecipientsBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        ensureRecipientsIsMutable();
        this.recipients_.add(byteString.toStringUtf8());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAddressLines() {
        this.addressLines_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAdministrativeArea() {
        this.administrativeArea_ = DEFAULT_INSTANCE.getAdministrativeArea();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLanguageCode() {
        this.languageCode_ = DEFAULT_INSTANCE.getLanguageCode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLocality() {
        this.locality_ = DEFAULT_INSTANCE.getLocality();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearOrganization() {
        this.organization_ = DEFAULT_INSTANCE.getOrganization();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPostalCode() {
        this.postalCode_ = DEFAULT_INSTANCE.getPostalCode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRecipients() {
        this.recipients_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRegionCode() {
        this.regionCode_ = DEFAULT_INSTANCE.getRegionCode();
    }

    private void clearRevision() {
        this.revision_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSortingCode() {
        this.sortingCode_ = DEFAULT_INSTANCE.getSortingCode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSublocality() {
        this.sublocality_ = DEFAULT_INSTANCE.getSublocality();
    }

    private void ensureAddressLinesIsMutable() {
        Internal.ProtobufList<String> protobufList = this.addressLines_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.addressLines_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureRecipientsIsMutable() {
        Internal.ProtobufList<String> protobufList = this.recipients_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.recipients_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static PostalAddress getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static PostalAddress parseDelimitedFrom(InputStream inputStream) {
        return (PostalAddress) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static PostalAddress parseFrom(ByteBuffer byteBuffer) {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<PostalAddress> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAddressLines(int i, String str) {
        str.getClass();
        ensureAddressLinesIsMutable();
        this.addressLines_.set(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAdministrativeArea(String str) {
        str.getClass();
        this.administrativeArea_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAdministrativeAreaBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.administrativeArea_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLanguageCode(String str) {
        str.getClass();
        this.languageCode_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLanguageCodeBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.languageCode_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLocality(String str) {
        str.getClass();
        this.locality_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLocalityBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.locality_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOrganization(String str) {
        str.getClass();
        this.organization_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOrganizationBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.organization_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPostalCode(String str) {
        str.getClass();
        this.postalCode_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPostalCodeBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.postalCode_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRecipients(int i, String str) {
        str.getClass();
        ensureRecipientsIsMutable();
        this.recipients_.set(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRegionCode(String str) {
        str.getClass();
        this.regionCode_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRegionCodeBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.regionCode_ = byteString.toStringUtf8();
    }

    private void setRevision(int i) {
        this.revision_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSortingCode(String str) {
        str.getClass();
        this.sortingCode_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSortingCodeBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.sortingCode_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSublocality(String str) {
        str.getClass();
        this.sublocality_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSublocalityBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.sublocality_ = byteString.toStringUtf8();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u000b\u0000\u0000\u0001\u000b\u000b\u0000\u0002\u0000\u0001\u0004\u0002Ȉ\u0003Ȉ\u0004Ȉ\u0005Ȉ\u0006Ȉ\u0007Ȉ\bȈ\tȚ\nȚ\u000bȈ", new Object[]{"revision_", "regionCode_", "languageCode_", "postalCode_", "sortingCode_", "administrativeArea_", "locality_", "sublocality_", "addressLines_", "recipients_", "organization_"});
            case NEW_MUTABLE_INSTANCE:
                return new PostalAddress();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<PostalAddress> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (PostalAddress.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                }
                return defaultInstanceBasedParser;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getAddressLines(int i) {
        return this.addressLines_.get(i);
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getAddressLinesBytes(int i) {
        return ByteString.copyFromUtf8(this.addressLines_.get(i));
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public int getAddressLinesCount() {
        return this.addressLines_.size();
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public List<String> getAddressLinesList() {
        return this.addressLines_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getAdministrativeArea() {
        return this.administrativeArea_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getAdministrativeAreaBytes() {
        return ByteString.copyFromUtf8(this.administrativeArea_);
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getLanguageCode() {
        return this.languageCode_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getLanguageCodeBytes() {
        return ByteString.copyFromUtf8(this.languageCode_);
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getLocality() {
        return this.locality_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getLocalityBytes() {
        return ByteString.copyFromUtf8(this.locality_);
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getOrganization() {
        return this.organization_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getOrganizationBytes() {
        return ByteString.copyFromUtf8(this.organization_);
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getPostalCode() {
        return this.postalCode_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getPostalCodeBytes() {
        return ByteString.copyFromUtf8(this.postalCode_);
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getRecipients(int i) {
        return this.recipients_.get(i);
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getRecipientsBytes(int i) {
        return ByteString.copyFromUtf8(this.recipients_.get(i));
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public int getRecipientsCount() {
        return this.recipients_.size();
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public List<String> getRecipientsList() {
        return this.recipients_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getRegionCode() {
        return this.regionCode_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getRegionCodeBytes() {
        return ByteString.copyFromUtf8(this.regionCode_);
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public int getRevision() {
        return this.revision_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getSortingCode() {
        return this.sortingCode_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getSortingCodeBytes() {
        return ByteString.copyFromUtf8(this.sortingCode_);
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getSublocality() {
        return this.sublocality_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getSublocalityBytes() {
        return ByteString.copyFromUtf8(this.sublocality_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PostalAddress, Builder> implements PostalAddressOrBuilder {
        public Builder() {
            super(PostalAddress.DEFAULT_INSTANCE);
        }

        public Builder addAddressLines(String str) {
            copyOnWrite();
            ((PostalAddress) this.instance).addAddressLines(str);
            return this;
        }

        public Builder addAddressLinesBytes(ByteString byteString) {
            copyOnWrite();
            ((PostalAddress) this.instance).addAddressLinesBytes(byteString);
            return this;
        }

        public Builder addAllAddressLines(Iterable<String> iterable) {
            copyOnWrite();
            PostalAddress.access$2600((PostalAddress) this.instance, iterable);
            return this;
        }

        public Builder addAllRecipients(Iterable<String> iterable) {
            copyOnWrite();
            PostalAddress.access$3100((PostalAddress) this.instance, iterable);
            return this;
        }

        public Builder addRecipients(String str) {
            copyOnWrite();
            ((PostalAddress) this.instance).addRecipients(str);
            return this;
        }

        public Builder addRecipientsBytes(ByteString byteString) {
            copyOnWrite();
            ((PostalAddress) this.instance).addRecipientsBytes(byteString);
            return this;
        }

        public Builder clearAddressLines() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearAddressLines();
            return this;
        }

        public Builder clearAdministrativeArea() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearAdministrativeArea();
            return this;
        }

        public Builder clearLanguageCode() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearLanguageCode();
            return this;
        }

        public Builder clearLocality() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearLocality();
            return this;
        }

        public Builder clearOrganization() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearOrganization();
            return this;
        }

        public Builder clearPostalCode() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearPostalCode();
            return this;
        }

        public Builder clearRecipients() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearRecipients();
            return this;
        }

        public Builder clearRegionCode() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearRegionCode();
            return this;
        }

        public Builder clearRevision() {
            copyOnWrite();
            ((PostalAddress) this.instance).revision_ = 0;
            return this;
        }

        public Builder clearSortingCode() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearSortingCode();
            return this;
        }

        public Builder clearSublocality() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearSublocality();
            return this;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getAddressLines(int i) {
            return ((PostalAddress) this.instance).getAddressLines(i);
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getAddressLinesBytes(int i) {
            return ((PostalAddress) this.instance).getAddressLinesBytes(i);
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public int getAddressLinesCount() {
            return ((PostalAddress) this.instance).getAddressLinesCount();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public List<String> getAddressLinesList() {
            return Collections.unmodifiableList(((PostalAddress) this.instance).getAddressLinesList());
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getAdministrativeArea() {
            return ((PostalAddress) this.instance).getAdministrativeArea();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getAdministrativeAreaBytes() {
            return ((PostalAddress) this.instance).getAdministrativeAreaBytes();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getLanguageCode() {
            return ((PostalAddress) this.instance).getLanguageCode();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getLanguageCodeBytes() {
            return ((PostalAddress) this.instance).getLanguageCodeBytes();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getLocality() {
            return ((PostalAddress) this.instance).getLocality();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getLocalityBytes() {
            return ((PostalAddress) this.instance).getLocalityBytes();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getOrganization() {
            return ((PostalAddress) this.instance).getOrganization();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getOrganizationBytes() {
            return ((PostalAddress) this.instance).getOrganizationBytes();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getPostalCode() {
            return ((PostalAddress) this.instance).getPostalCode();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getPostalCodeBytes() {
            return ((PostalAddress) this.instance).getPostalCodeBytes();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getRecipients(int i) {
            return ((PostalAddress) this.instance).getRecipients(i);
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getRecipientsBytes(int i) {
            return ((PostalAddress) this.instance).getRecipientsBytes(i);
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public int getRecipientsCount() {
            return ((PostalAddress) this.instance).getRecipientsCount();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public List<String> getRecipientsList() {
            return Collections.unmodifiableList(((PostalAddress) this.instance).getRecipientsList());
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getRegionCode() {
            return ((PostalAddress) this.instance).getRegionCode();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getRegionCodeBytes() {
            return ((PostalAddress) this.instance).getRegionCodeBytes();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public int getRevision() {
            return ((PostalAddress) this.instance).getRevision();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getSortingCode() {
            return ((PostalAddress) this.instance).getSortingCode();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getSortingCodeBytes() {
            return ((PostalAddress) this.instance).getSortingCodeBytes();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getSublocality() {
            return ((PostalAddress) this.instance).getSublocality();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getSublocalityBytes() {
            return ((PostalAddress) this.instance).getSublocalityBytes();
        }

        public Builder setAddressLines(int i, String str) {
            copyOnWrite();
            ((PostalAddress) this.instance).setAddressLines(i, str);
            return this;
        }

        public Builder setAdministrativeArea(String str) {
            copyOnWrite();
            ((PostalAddress) this.instance).setAdministrativeArea(str);
            return this;
        }

        public Builder setAdministrativeAreaBytes(ByteString byteString) {
            copyOnWrite();
            ((PostalAddress) this.instance).setAdministrativeAreaBytes(byteString);
            return this;
        }

        public Builder setLanguageCode(String str) {
            copyOnWrite();
            ((PostalAddress) this.instance).setLanguageCode(str);
            return this;
        }

        public Builder setLanguageCodeBytes(ByteString byteString) {
            copyOnWrite();
            ((PostalAddress) this.instance).setLanguageCodeBytes(byteString);
            return this;
        }

        public Builder setLocality(String str) {
            copyOnWrite();
            ((PostalAddress) this.instance).setLocality(str);
            return this;
        }

        public Builder setLocalityBytes(ByteString byteString) {
            copyOnWrite();
            ((PostalAddress) this.instance).setLocalityBytes(byteString);
            return this;
        }

        public Builder setOrganization(String str) {
            copyOnWrite();
            ((PostalAddress) this.instance).setOrganization(str);
            return this;
        }

        public Builder setOrganizationBytes(ByteString byteString) {
            copyOnWrite();
            ((PostalAddress) this.instance).setOrganizationBytes(byteString);
            return this;
        }

        public Builder setPostalCode(String str) {
            copyOnWrite();
            ((PostalAddress) this.instance).setPostalCode(str);
            return this;
        }

        public Builder setPostalCodeBytes(ByteString byteString) {
            copyOnWrite();
            ((PostalAddress) this.instance).setPostalCodeBytes(byteString);
            return this;
        }

        public Builder setRecipients(int i, String str) {
            copyOnWrite();
            ((PostalAddress) this.instance).setRecipients(i, str);
            return this;
        }

        public Builder setRegionCode(String str) {
            copyOnWrite();
            ((PostalAddress) this.instance).setRegionCode(str);
            return this;
        }

        public Builder setRegionCodeBytes(ByteString byteString) {
            copyOnWrite();
            ((PostalAddress) this.instance).setRegionCodeBytes(byteString);
            return this;
        }

        public Builder setRevision(int i) {
            copyOnWrite();
            ((PostalAddress) this.instance).revision_ = i;
            return this;
        }

        public Builder setSortingCode(String str) {
            copyOnWrite();
            ((PostalAddress) this.instance).setSortingCode(str);
            return this;
        }

        public Builder setSortingCodeBytes(ByteString byteString) {
            copyOnWrite();
            ((PostalAddress) this.instance).setSortingCodeBytes(byteString);
            return this;
        }

        public Builder setSublocality(String str) {
            copyOnWrite();
            ((PostalAddress) this.instance).setSublocality(str);
            return this;
        }

        public Builder setSublocalityBytes(ByteString byteString) {
            copyOnWrite();
            ((PostalAddress) this.instance).setSublocalityBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(PostalAddress.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(PostalAddress postalAddress) {
        return DEFAULT_INSTANCE.createBuilder(postalAddress);
    }

    public static PostalAddress parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (PostalAddress) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static PostalAddress parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static PostalAddress parseFrom(ByteString byteString) {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static PostalAddress parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static PostalAddress parseFrom(byte[] bArr) {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static PostalAddress parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static PostalAddress parseFrom(InputStream inputStream) {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static PostalAddress parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static PostalAddress parseFrom(CodedInputStream codedInputStream) {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static PostalAddress parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
