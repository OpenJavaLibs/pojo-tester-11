package com.java.pojo.issue153;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

class Message {

    private final UUID id = UUID.randomUUID();
    private final Set<Person> readers = new LinkedHashSet<>();

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final Message that = (Message) obj;

        return new EqualsBuilder().append(id, that.id)
                                  .append(readers, that.readers)
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id)
                                    .append(readers)
                                    .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id)
                                        .append("readers", readers)
                                        .toString();
    }
}
