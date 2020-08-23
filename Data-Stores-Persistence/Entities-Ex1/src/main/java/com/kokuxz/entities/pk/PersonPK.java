package com.kokuxz.entities.pk;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PersonPK implements Serializable {
    private Long flowerId;
    private String personName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonPK personPK = (PersonPK) o;
        return flowerId == personPK.flowerId &&
                personName.equals(personPK.personName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flowerId, personName);
    }
}
