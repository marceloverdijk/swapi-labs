package com.github.marceloverdijk.swaphql.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class DateTimeModel<PK extends Serializable>  {

    @Id
    @Column(name = "id")
    private PK id;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "edited")
    private LocalDateTime edited;

    public PK getId() {
        return id;
    }

    public void setId(final PK id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(final LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getEdited() {
        return edited;
    }

    public void setEdited(final LocalDateTime edited) {
        this.edited = edited;
    }
}
