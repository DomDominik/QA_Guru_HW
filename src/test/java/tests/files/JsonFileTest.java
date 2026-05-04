package tests.files;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import tests.files.model.JsonFileModel;
import java.io.InputStreamReader;
import java.io.Reader;
import static org.junit.jupiter.api.Assertions.*;


public class JsonFileTest {
    private ClassLoader cl = JsonFileTest.class.getClassLoader();
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("разбор json файла")
    void pdfFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("JsonFile"))
        ) {
            JsonFileModel actual = mapper.readValue(reader, JsonFileModel.class);
            assertEquals("Тестирование веб-API", actual.getTitle());
            assertEquals("Марк Винтерингем", actual.getAuthor());
            assertEquals(2024, actual.getYear());
            assertEquals(304, actual.getPages());
            assertEquals("русский", actual.getLanguage());
            String[] expected = {"тестирование", "API", "автоматизация"};
            assertArrayEquals(expected, actual.getTags());
            assertTrue(actual.getAvailability().getInStock());
        }
    }
}