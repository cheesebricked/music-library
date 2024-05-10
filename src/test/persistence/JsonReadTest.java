package persistence;

import model.Library;
import model.Release;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReadTest extends JsonTest {

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
    void testReaderNonExistentFile() {
        reader = new JsonRead("./datkkka/noSuchjjjjFile.json");
        try {
            library = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLibrary() {
        try {
            reader = new JsonRead("./data/testReaderEmptyLibrary.json");

            Library lib = reader.read();

            assertEquals(lib.getLibrary(), new ArrayList<>());
            assertEquals(lib.getLibrary().size(), 0);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testReaderNormalLibrary() {
        Release r1;
        Release r2;
        Release r3;

        try {
            reader = new JsonRead("./data/testReaderNormalLibrary.json");

            r1 = new Release("release1", "artist1", "genre1", 5);
            r2 = new Release("pooper","h","no genre", 10);
            r3 = new Release("New Name", "New Artist", "New Genre", 0);

            r1.addComment("comment1");
            r1.addComment("comment2");
            r3.addGenre("New Genre 2");

            addThreeReleases(library, r1, r2, r3);

            Library lib = reader.read();

            assertEquals(lib.getLibrary().size(), 3);

            testReleasesLoop(lib, library.getLibrary());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
