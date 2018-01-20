package com.github.marceloverdijk.swapi.labs.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStarship is a Querydsl query type for Starship
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStarship extends EntityPathBase<Starship> {

    private static final long serialVersionUID = 13624153L;

    public static final QStarship starship = new QStarship("starship");

    public final QBaseModel _super = new QBaseModel(this);

    public final NumberPath<Long> cargoCapacity = createNumber("cargoCapacity", Long.class);

    public final StringPath consumables = createString("consumables");

    public final NumberPath<Long> costInCredits = createNumber("costInCredits", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final NumberPath<Integer> crew = createNumber("crew", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> edited = _super.edited;

    public final ListPath<Film, QFilm> films = this.<Film, QFilm>createList("films", Film.class, QFilm.class, PathInits.DIRECT2);

    public final NumberPath<Double> hyperdriveRating = createNumber("hyperdriveRating", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> length = createNumber("length", Integer.class);

    public final StringPath manufacturer = createString("manufacturer");

    public final NumberPath<Integer> maxAtmospheringSpeed = createNumber("maxAtmospheringSpeed", Integer.class);

    public final NumberPath<Integer> MGLT = createNumber("MGLT", Integer.class);

    public final StringPath model = createString("model");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> passengers = createNumber("passengers", Integer.class);

    public final ListPath<Person, QPerson> pilots = this.<Person, QPerson>createList("pilots", Person.class, QPerson.class, PathInits.DIRECT2);

    public final StringPath starshipClass = createString("starshipClass");

    public QStarship(String variable) {
        super(Starship.class, forVariable(variable));
    }

    public QStarship(Path<? extends Starship> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStarship(PathMetadata metadata) {
        super(Starship.class, metadata);
    }

}

