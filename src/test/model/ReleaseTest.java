package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ReleaseTest {
    Release okComputer;

    @BeforeEach
    void before() {
        okComputer = new Release("Ok Computer", "Radiohead", "Alt-rock", 10);
    }

    @Test
    void testChangeName() {
        okComputer.changeName("Ok Computah!");
        assertEquals("Ok Computah!", okComputer.getName());
    }

    @Test
    void testChangeArtist() {
        okComputer.changeArtist("Thom and the Radioheads");
        assertEquals("Thom and the Radioheads", okComputer.getArtist());
    }

    @Test
    void testAddGenre() {
        okComputer.addGenre("Experimental Rock");
        assertEquals(2, okComputer.getGenresLength());
    }

    @Test
    void testRemoveGenre() {
        okComputer.addGenre("Experimental Rock");
        okComputer.addGenre("Poopcore");
        assertEquals(3, okComputer.getGenresLength());
        okComputer.removeGenre("Poopcore");
        assertEquals(2, okComputer.getGenresLength());
        okComputer.removeGenre("Experimental Rock");
        assertEquals(1, okComputer.getGenresLength());
        okComputer.removeGenre("Alt-rock");
        assertEquals(0, okComputer.getGenresLength());
    }

    @Test
    void testRemoveGenreNotExist() {
        okComputer.addGenre("Experimental Rock");
        okComputer.addGenre("Poopcore");
        assertEquals(3, okComputer.getGenresLength());
        okComputer.removeGenre("akuwyjfbg");
        assertFalse(okComputer.removeGenre("akuwyjfbg"));
    }

    @Test
    void testChangeRating() {
        okComputer.changeRating(0);
        assertEquals(0, okComputer.getRating());
        okComputer.changeRating(9);
        assertEquals(9, okComputer.getRating());
        okComputer.changeRating(1);
        assertEquals(1, okComputer.getRating());
        okComputer.changeRating(10);
        assertEquals(10, okComputer.getRating());
    }

    @Test
    void testAddComment() {
        okComputer.addComment("This rocks!");
        assertEquals(1, okComputer.getCommentsLength());
        okComputer.addComment("Pooper dooper");
        assertEquals(2, okComputer.getCommentsLength());
    }

    @Test
    void testRemoveComment() {
        okComputer.addComment("This rocks!");
        okComputer.addComment("Pooper dooper");
        assertEquals(2, okComputer.getCommentsLength());
        okComputer.removeComment("Pooper dooper");
        assertEquals(1, okComputer.getCommentsLength());
        okComputer.removeComment("This rocks!");
        assertEquals(0, okComputer.getCommentsLength());
    }

    @Test
    void testRemoveCommentNotExist() {
        okComputer.addComment("This rocks!");
        okComputer.addComment("Pooper dooper");
        assertEquals(2, okComputer.getCommentsLength());
        okComputer.removeComment("akuwyjfbg");
        assertFalse(okComputer.removeComment("akuwyjfbg"));
    }

    @Test
    void testGetComment() {
        okComputer.addComment("This rocks!");
        okComputer.addComment("Pooper dooper");
        ArrayList<Comment> testList = new ArrayList<Comment>() {{
            add(okComputer.getComments().get(0));
            add(okComputer.getComments().get(1));
        }};
        assertEquals(testList, okComputer.getComments());
    }

    @Test
    void testGetCommentsToJson() {
        okComputer.addComment("This rocks!");
        okComputer.addComment("Pooper dooper");
        assertEquals(okComputer.getCommentsToJson(), new ArrayList<String>(){{
            add("This rocks!");
            add("Pooper dooper");
        }});
    }

    @Test
    void testGetCommentsToJsonEmpty() {
        assertEquals(okComputer.getCommentsToJson(), new ArrayList<String>());
    }
}