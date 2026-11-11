package j$.nio.file.attribute;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes3.dex */
public final class AclEntry {
    private final Set flags;
    private volatile int hash;
    private final Set perms;
    private final AclEntryType type;
    private final UserPrincipal who;

    private AclEntry(AclEntryType aclEntryType, UserPrincipal userPrincipal, Set set, Set set2) {
        this.type = aclEntryType;
        this.who = userPrincipal;
        this.perms = set;
        this.flags = set2;
    }

    public static final class Builder {
        private Set flags;
        private Set perms;
        private AclEntryType type;
        private UserPrincipal who;

        private Builder(AclEntryType aclEntryType, UserPrincipal userPrincipal, Set set, Set set2) {
            this.type = aclEntryType;
            this.who = userPrincipal;
            this.perms = set;
            this.flags = set2;
        }

        public AclEntry build() {
            AclEntryType aclEntryType = this.type;
            if (aclEntryType == null) {
                throw new IllegalStateException("Missing type component");
            }
            UserPrincipal userPrincipal = this.who;
            if (userPrincipal == null) {
                throw new IllegalStateException("Missing who component");
            }
            return new AclEntry(aclEntryType, userPrincipal, this.perms, this.flags);
        }

        public Builder setType(AclEntryType aclEntryType) {
            aclEntryType.getClass();
            this.type = aclEntryType;
            return this;
        }

        public Builder setPrincipal(UserPrincipal userPrincipal) {
            userPrincipal.getClass();
            this.who = userPrincipal;
            return this;
        }

        private static void checkSet(Set set, Class cls) {
            for (Object obj : set) {
                obj.getClass();
                cls.cast(obj);
            }
        }

        public Builder setPermissions(Set<AclEntryPermission> set) {
            Set setCopyOf;
            if (set.isEmpty()) {
                setCopyOf = Collections.EMPTY_SET;
            } else {
                setCopyOf = EnumSet.copyOf((Collection) set);
                checkSet(setCopyOf, AclEntryPermission.class);
            }
            this.perms = setCopyOf;
            return this;
        }

        public Builder setFlags(AclEntryFlag... aclEntryFlagArr) {
            EnumSet enumSetNoneOf = EnumSet.noneOf(AclEntryFlag.class);
            for (AclEntryFlag aclEntryFlag : aclEntryFlagArr) {
                aclEntryFlag.getClass();
                enumSetNoneOf.add(aclEntryFlag);
            }
            this.flags = enumSetNoneOf;
            return this;
        }
    }

    public static Builder newBuilder() {
        Set set = Collections.EMPTY_SET;
        return new Builder(null, null, set, set);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof AclEntry)) {
            return false;
        }
        AclEntry aclEntry = (AclEntry) obj;
        return this.type == aclEntry.type && this.who.equals(aclEntry.who) && this.perms.equals(aclEntry.perms) && this.flags.equals(aclEntry.flags);
    }

    private static int hash(int i, Object obj) {
        return (i * 127) + obj.hashCode();
    }

    public int hashCode() {
        if (this.hash != 0) {
            return this.hash;
        }
        this.hash = hash(hash(hash(this.type.hashCode(), this.who), this.perms), this.flags);
        return this.hash;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.who.getName());
        sb.append(AbstractJsonLexerKt.COLON);
        Iterator it = this.perms.iterator();
        while (it.hasNext()) {
            sb.append(((AclEntryPermission) it.next()).name());
            sb.append('/');
        }
        sb.setLength(sb.length() - 1);
        sb.append(AbstractJsonLexerKt.COLON);
        if (!this.flags.isEmpty()) {
            Iterator it2 = this.flags.iterator();
            while (it2.hasNext()) {
                sb.append(((AclEntryFlag) it2.next()).name());
                sb.append('/');
            }
            sb.setLength(sb.length() - 1);
            sb.append(AbstractJsonLexerKt.COLON);
        }
        sb.append(this.type.name());
        return sb.toString();
    }
}
