package persistence;

import model.Library;
import model.Release;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriteTest extends JsonTest {

    Library library;
    JsonWrite writer;
    JsonRead reader;

    @BeforeEach
    void before() {
        library = new Library();
        writer = new JsonWrite("./data/data.json");
        reader = new JsonRead("./data/data.json");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            writer = new JsonWrite("./dat;;a/the-bronx.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptyLibrary() {
        try {
            writer.open();
            writer.write(library);
            writer.close();

            Library lib = reader.read();

            assertEquals(lib.getLibrary(), new ArrayList<>());
            assertEquals(lib.getLibrary().size(), 0);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNormalLibrary() {
        try {
            Release r1 = new Release("release1", "artist1", "genre1", 5);
            Release r2 = new Release("pooper","h","no genre", 10);
            Release r3 = new Release("New Name", "New Artist", "New Genre", 0);

            List<Release> testList = new ArrayList<>();
            testList.add(r1);
            testList.add(r2);
            testList.add(r3);
            addThreeReleases(library, r1, r2, r3);
            writer.open();
            writer.write(library);
            writer.close();

            library = reader.read();
            assertEquals(library.getLibrary().size(), 3);

            testReleasesLoop(library, testList);

        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
