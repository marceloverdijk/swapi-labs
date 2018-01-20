package com.github.marceloverdijk.swapi.labs.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPerson is a Querydsl query type for Person
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPerson extends EntityPathBase<Person> {

    private static final long serialVersionUID = 1625918944L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPerson person = new QPerson("person");

    public final QBaseModel _super = new QBaseModel(this);

    public final StringPath birthYear = createString("birthYear");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> edited = _super.edited;

    public final StringPath eyeColor = createString("eyeColor");

    public final ListPath<Film, QFilm> films = this.<Film, QFilm>createList("films", Film.class, QFilm.class, PathInits.DIRECT2);

    public final StringPath gender = createString("gender");

    public final StringPath hairColor = createString("hairColor");

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final QPlanet homeworld;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> mass = createNumber("mass", Double.class);

    public final StringPath name = createString("name");

    public final StringPath skinColor = createString("skinColor");

    public final ListPath<Species, QSpecies> species = this.<Species, QSpecies>createList("species", Species.class, QSpecies.class, PathInits.DIRECT2);

    public final ListPath<Starship, QStarship> starships = this.<Starship, QStarship>createList("starships", Starship.class, QStarship.class, PathInits.DIRECT2);

    public final ListPath<Vehicle, QVehicle> vehicles = this.<Vehicle, QVehicle>createList("vehicles", Vehicle.class, QVehicle.class, PathInits.DIRECT2);

    public QPerson(String variable) {
        this(Person.class, forVariable(variable), INITS);
    }

    public QPerson(Path<? extends Person> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPerson(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPerson(PathMetadata metadata, PathInits inits) {
        this(Person.class, metadata, inits);
    }

    public QPerson(Class<? extends Person> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.homeworld = inits.isInitialized("homeworld") ? new QPlanet(forProperty("homeworld")) : null;
    }

}

