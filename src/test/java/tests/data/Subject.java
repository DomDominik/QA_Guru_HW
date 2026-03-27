package tests.data;

import com.github.javafaker.Faker;

public enum Subject {
    ACCOUNTING("Accounting"),
    ARTS("Arts"),
    BIOLOGY("Biology"),
    CHEMISTRY("Chemistry"),
    CIVICS("Civics"),
    COMMERCE("Commerce"),
    COMPUTER_SCIENCE("Computer Science"),
    ECONOMICS("Economics"),
    ENGLISH("English"),
    HINDI("Hindi"),
    HISTORY("History"),
    MATHS("Maths"),
    PHYSICS("Physics"),
    SOCIAL_STUDIES("Social Studies");

    private final String displayName;

    Subject(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static String getRandomSubject() {
        Faker faker = new Faker();
        return faker.options().option(Subject.values()).getDisplayName();
    }
}
