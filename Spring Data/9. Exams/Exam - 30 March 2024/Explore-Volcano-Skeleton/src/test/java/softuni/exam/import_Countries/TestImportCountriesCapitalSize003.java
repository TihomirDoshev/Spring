package softuni.exam.import_Countries;
//TestImportCountriesCapitalSize003

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import softuni.exam.service.impl.CountryServiceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportCountriesCapitalSize003 {

    @Autowired
    private CountryServiceImpl countryService;

    @Test
    void importCountriesCapitalSize() throws IOException {

        String expected = "Successfully imported country RSA - Capetown\n" +
                "Invalid country\n" +
                "Successfully imported country Japan - Tokyo";
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        copyRewriteFileForTest();
        String actual = countryService.importCountries();
        String[] actualSplit = actual.split("\\r\\n?|\\n");

        returnOriginalValue();
        Assertions.assertArrayEquals(expectedSplit,actualSplit);
    }

    private void copyRewriteFileForTest() throws IOException {
        File originalJsonFile = new File("src/main/resources/files/json/countries.json");

        String testJSON = "[\n" +
                "  {\n" +
                "    \"name\": \"RSA\",\n" +
                "    \"capital\": \"Capetown\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Italy\",\n" +
                "    \"capital\": \"R\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Japan\",\n" +
                "    \"capital\": \"Tokyo\"\n" +
                "  }\n" +
                "]";

        try {
            FileWriter f2 = new FileWriter(originalJsonFile, false);
            f2.write(testJSON);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void returnOriginalValue() {
        File originalJsonFileSrc = new File("src/main/resources/files/json/countries.json");

        try {
            FileWriter f2 = new FileWriter(originalJsonFileSrc, false);
            String testOriginalFile = Files.readString(Path.of("src/test/resources/original-files/countries.json"));
            f2.write(testOriginalFile);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
