package tests.files;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@DisplayName("Класс с тестами на проверку файлов из архива")
public class ZipFileTest {

    public ClassLoader cl = ZipFileTest.class.getClassLoader();

    @DisplayName("Распаковываем архив, перебираем файлы и выводим имя")
    @Test
    void showZipContent() throws Exception {

        InputStream zipFileStream = getClass().getClassLoader()
                .getResourceAsStream("archive.zip");

        ZipInputStream zis = new ZipInputStream(zipFileStream);

        ZipEntry entry;

        while ((entry = zis.getNextEntry()) != null) {
            String fileName = entry.getName();
            System.out.println("Найден файл: " + fileName);
        }
        zis.close();
    }
    @Test
    void csvFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("archive.zip"))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();
                if (fileName.endsWith(".csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = reader.readAll();
                    assertEquals(88, data.size());
                    assertArrayEquals(
                            new String[] {"Хлопуша"},
                    data.get(2));
                    assertArrayEquals(
                            new String[] {"Сумасшедшая, бешеная кровавая муть!"},
                    data.get(4));
                }
            }
        }
    }
    @Test
    void pdfFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("archive.zip"))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();
                if (fileName.endsWith(".pdf")) {
                    PDF pdf = new PDF(zis);
                    String expected = "Сумасшедшая, бешеная кровавая муть!";
                    assertTrue(pdf.text.contains(expected));
                }
            }
        }
    }
    @Test
    void xlsxFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("archive.zip"))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();
                if (fileName.endsWith(".xlsx")) {
                    XLS xls = new XLS(zis);
                    System.out.println();
                    String expected = xls.excel.getSheetAt(0).getRow(7).getCell(0).getStringCellValue();
                    assertTrue(expected.contains("Я хочу видеть этого человека."));
                }
            }
        }
    }
}
