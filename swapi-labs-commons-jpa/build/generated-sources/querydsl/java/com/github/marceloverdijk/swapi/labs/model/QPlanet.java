package com.github.marceloverdijk.swapi.labs.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlanet is a Querydsl query type for Planet
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPlanet extends EntityPathBase<Planet> {

    private static final long serialVersionUID = 1631872035L;

    public static final QPlanet planet = new QPlanet("planet");

    public final QBaseModel _super = new QBaseModel(this);

    public final StringPath climate = createString("climate");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final NumberPath<Integer> diameter = createNumber("diameter", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> edited = _super.edited;

    public final ListPath<Film, QFilm> films = this.<Film, QFilm>createList("films", Film.class, QFilm.class, PathInits.DIRECT2);

    public final StringPath gravity = createString("gravity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> orbitalPeriod = createNumber("orbitalPeriod", Integer.class);

    public final NumberPath<Long> population = createNumber("population", Long.class);

    public final ListPath<Person, QPerson> residents = this.<Person, QPerson>createList("residents", Person.class, QPerson.class, PathInits.DIRECT2);

    public final NumberPath<Integer> rotationPeriod = createNumber("rotationPeriod", Integer.class);

    public final NumberPath<Integer> surfaceWater = createNumber("surfaceWater", Integer.class);

    public final StringPath terrain = createString("terrain");

    public QPlanet(String variable) {
        super(Planet.class, forVariable(variable));
    }

    public QPlanet(Path<? extends Planet> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlanet(PathMetadata metadata) {
        super(Planet.class, metadata);
    }

}

