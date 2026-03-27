package tests.data;

import com.github.javafaker.Faker;

public enum CountryAndCity {
    NCR("NCR", "Delhi", "Gurgaon", "Noida"),
    UTTAR_PRADESH("Uttar Pradesh", "Agra", "Lucknow", "Merrut"),
    HARYANA("Haryana", "Karnal", "Panipat"),
    RAJASTHAN("Rajasthan", "Jaipur", "Jaiselmer");

    private final String country;
    private final String[] cities;

    CountryAndCity(String country, String... cities) {
        this.country = country;
        this.cities = cities;
    }

    public String getCountry() {
        return country;
    }

    public String getRandomCity(Faker faker) {
        return faker.options().nextElement(cities);
    }
}
