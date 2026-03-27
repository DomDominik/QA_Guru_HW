package tests.data.utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class RandomUtils {
    public static String[] generateBirthDate() {
        var parts = new SimpleDateFormat("yyyy|MMMM|dd", Locale.ENGLISH)
                .format(new Faker(Locale.of("en")).date().birthday(14, 88))
                .split("\\|");
        return new String[]{parts[0], parts[1], parts[2]};
    }
}
