package com.userDatabase.userDatabase.model;

import java.io.Serializable;
import java.util.Objects;

public class MembershipId implements Serializable {
    private Long user;
    private Long group;

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MembershipId)) return false;
        MembershipId that = (MembershipId) o;
        return Objects.equals(user, that.user) &&
               Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, group);
    }
}
