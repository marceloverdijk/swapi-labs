package com.github.marceloverdijk.swapi.labs.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFilm is a Querydsl query type for Film
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFilm extends EntityPathBase<Film> {

    private static final long serialVersionUID = 1503071951L;

    public static final QFilm film = new QFilm("film");

    public final QBaseModel _super = new QBaseModel(this);

    public final ListPath<Person, QPerson> characters = this.<Person, QPerson>createList("characters", Person.class, QPerson.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final StringPath director = createString("director");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> edited = _super.edited;

    public final NumberPath<Integer> episodeId = createNumber("episodeId", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath openingCrawl = createString("openingCrawl");

    public final ListPath<Planet, QPlanet> planets = this.<Planet, QPlanet>createList("planets", Planet.class, QPlanet.class, PathInits.DIRECT2);

    public final StringPath producer = createString("producer");

    public final DatePath<java.time.LocalDate> releaseDate = createDate("releaseDate", java.time.LocalDate.class);

    public final ListPath<Species, QSpecies> species = this.<Species, QSpecies>createList("species", Species.class, QSpecies.class, PathInits.DIRECT2);

    public final ListPath<Starship, QStarship> starships = this.<Starship, QStarship>createList("starships", Starship.class, QStarship.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final ListPath<Vehicle, QVehicle> vehicles = this.<Vehicle, QVehicle>createList("vehicles", Vehicle.class, QVehicle.class, PathInits.DIRECT2);

    public QFilm(String variable) {
        super(Film.class, forVariable(variable));
    }

    public QFilm(Path<? extends Film> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFilm(PathMetadata metadata) {
        super(Film.class, metadata);
    }

}

