package com.github.marceloverdijk.swapi.labs.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSpecies is a Querydsl query type for Species
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSpecies extends EntityPathBase<Species> {

    private static final long serialVersionUID = 1828823057L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSpecies species = new QSpecies("species");

    public final QBaseModel _super = new QBaseModel(this);

    public final NumberPath<Integer> averageHeight = createNumber("averageHeight", Integer.class);

    public final NumberPath<Integer> averageLifespan = createNumber("averageLifespan", Integer.class);

    public final StringPath classification = createString("classification");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final StringPath designation = createString("designation");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> edited = _super.edited;

    public final StringPath eye_colors = createString("eye_colors");

    public final ListPath<Film, QFilm> films = this.<Film, QFilm>createList("films", Film.class, QFilm.class, PathInits.DIRECT2);

    public final StringPath hair_colors = createString("hair_colors");

    public final QPlanet homeworld;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath language = createString("language");

    public final StringPath name = createString("name");

    public final ListPath<Person, QPerson> persons = this.<Person, QPerson>createList("persons", Person.class, QPerson.class, PathInits.DIRECT2);

    public final StringPath skin_colors = createString("skin_colors");

    public QSpecies(String variable) {
        this(Species.class, forVariable(variable), INITS);
    }

    public QSpecies(Path<? extends Species> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSpecies(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSpecies(PathMetadata metadata, PathInits inits) {
        this(Species.class, metadata, inits);
    }

    public QSpecies(Class<? extends Species> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.homeworld = inits.isInitialized("homeworld") ? new QPlanet(forProperty("homeworld")) : null;
    }

}

