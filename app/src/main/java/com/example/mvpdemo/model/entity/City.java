package com.example.mvpdemo.model.entity;

/**
 * 城市实体
 * Created by Administrator on 2017/4/15.
 */

public class City {
    /**
     * {
     * "id": 1,
     * "name": "Kabul",
     * "countryCode": "AFG",
     * "district": "Kabol",
     * "population": 1780000
     * }
     */

    private final int id;
    private final String name;
    private final String countryCode;
    private final String district;
    private final int population;

    private City(Builder builder) {
        id = builder.id;
        name = builder.name;
        countryCode = builder.countryCode;
        district = builder.district;
        population = builder.population;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public int getPopulation() {
        return population;
    }

    public static class Builder {
        private int id;
        private String name;
        private String countryCode;
        private String district;
        private int population;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder population(int population) {
            this.population = population;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder district(String district) {
            this.district = district;
            return this;
        }

        public City build() {
            return new City(this);
        }

    }

}
