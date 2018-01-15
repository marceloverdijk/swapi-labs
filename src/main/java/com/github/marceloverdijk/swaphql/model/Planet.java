package com.github.marceloverdijk.swaphql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "planet")
public class Planet extends DateTimeModel<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "rotation_period")
    private String rotationPeriod;

    @Column(name = "orbital_period")
    private String orbitalPeriod;

    @Column(name = "diameter")
    private String diameter;

    @Column(name = "climate")
    private String climate;

    @Column(name = "gravity")
    private String gravity;

    @Column(name = "terrain")
    private String terrain;

    @Column(name = "surface_water")
    private String surfaceWater;

    @Column(name = "population")
    private String population;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getRotationPeriod() {
        return rotationPeriod;
    }

    public void setRotationPeriod(final String rotationPeriod) {
        this.rotationPeriod = rotationPeriod;
    }

    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(final String orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(final String diameter) {
        this.diameter = diameter;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(final String climate) {
        this.climate = climate;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(final String gravity) {
        this.gravity = gravity;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(final String terrain) {
        this.terrain = terrain;
    }

    public String getSurfaceWater() {
        return surfaceWater;
    }

    public void setSurfaceWater(final String surfaceWater) {
        this.surfaceWater = surfaceWater;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(final String population) {
        this.population = population;
    }
}
