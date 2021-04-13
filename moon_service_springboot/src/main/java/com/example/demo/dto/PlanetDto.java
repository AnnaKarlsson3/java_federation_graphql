package com.example.demo.dto;

import java.util.List;

public class PlanetDto {
    private int id;
    private List<MoonDto> moons;

    public PlanetDto(int id, List<MoonDto> moons) {
        this.id = id;
        this.moons = moons;
    }

    public PlanetDto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MoonDto> getMoons() {
        return moons;
    }

    public void setMoons(List<MoonDto> moons) {
        this.moons = moons;
    }
}
