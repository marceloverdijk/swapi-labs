/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.marceloverdijk.swapi.labs.springhateoas.web.resource;

import com.github.marceloverdijk.swapi.labs.model.Film;
import com.github.marceloverdijk.swapi.labs.springhateoas.web.controller.FilmController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * The film resource assembler.
 *
 * @author Marcel Overdijk
 */
@Component
public class FilmResourceAssembler extends ResourceAssemblerSupport<Film, FilmResource> {

    public FilmResourceAssembler() {
        super(FilmController.class, FilmResource.class);
    }

    @Override
    public FilmResource toResource(final Film film) {
        FilmResource resource = new FilmResource();
        resource.setTitle(film.getTitle());
        resource.setEpisodeId(film.getEpisodeId());
        resource.setOpeningCrawl(film.getOpeningCrawl());
        resource.setDirector(film.getDirector());
        resource.setProducer(film.getProducer());
        resource.setReleaseDate(film.getReleaseDate());
        linkTo(methodOn(FilmController.class).getById(film.getId())).withSelfRel();
        resource.add(
                linkTo(FilmController.class).slash(film.getId()).withSelfRel());
        return resource;
    }
}
