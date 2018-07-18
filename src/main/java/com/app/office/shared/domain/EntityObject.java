package com.app.office.shared.domain;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

@MappedSuperclass
public abstract class EntityObject implements Comparable<EntityObject>, Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int compareTo(EntityObject o) {
        return Objects.compare(this, o, Comparator.comparingLong(this::getIdForComparison));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityObject that = (EntityObject) o;
        if (id == null) return this == that;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return super.hashCode();
        }
        return Objects.hashCode(id);
    }

    @Override
    @Transient
    public boolean isNew() {
        return id == null;
    }

    private long getIdForComparison(EntityObject value) {
        return Optional
                .ofNullable(value)
                .map(EntityObject::getId)
                .orElse(-1L);
    }
}
