package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    Library library;
    Release okComputer;
    Release marqueeMoon;
    Release altRockAlbum;

    @BeforeEach
    void before() {
        library = new Library();
        okComputer = new Release("Ok Computer", "Radiohead", "Alt-rock", 10);
        marqueeMoon = new Release("Marquee Moon", "Television","Art-punk",9);
        altRockAlbum = new Release("Alt Rock Album","Huh","Alt-rock",0);
    }

    @Test
    void testAddRelease() {
        assertEquals(0, library.getLibraryLength());
        library.addRelease(okComputer);
        assertEquals(1, library.getLibraryLength());
        library.addRelease(marqueeMoon);
        assertEquals(2, library.getLibraryLength());
    }

    @Test
    void testRemoveRelease() {
        library.addRelease(okComputer);
        library.addRelease(marqueeMoon);
        assertTrue(library.removeRelease("Marquee Moon"));
        library.removeRelease("Marquee Moon");
        assertFalse(library.removeRelease("Pooper"));
        assertEquals(1, library.getLibraryLength());
        assertTrue(library.removeRelease("Ok Computer"));
        library.removeRelease("Ok Computer");
        assertEquals(0, library.getLibraryLength());
    }

    @Test
    void testFindReleaseByName() {
        library.addRelease(okComputer);
        library.addRelease(marqueeMoon);
        library.findReleasesByName("Ok Computer");
        assertEquals(okComputer, library.findReleasesByName("Ok Computer"));
    }

    @Test
    void testFindReleaseByNameSecond() {
        library.addRelease(okComputer);
        library.addRelease(marqueeMoon);
        library.findReleasesByName("Marquee Moon");
        assertEquals(marqueeMoon, library.findReleasesByName("Marquee Moon"));
    }

    @Test
    void testFindReleaseByArtist() {
        library.addRelease(okComputer);
        library.addRelease(marqueeMoon);
        assertEquals(okComputer, library.findReleasesByArtist("Radiohead"));
    }

    @Test
    void testFindReleaseByArtistSecond() {
        library.addRelease(okComputer);
        library.addRelease(marqueeMoon);
        assertEquals(marqueeMoon, library.findReleasesByArtist("Television"));
    }

    @Test
    void testFindReleaseByArtistNull() {
        library.addRelease(okComputer);
        library.addRelease(marqueeMoon);
        assertNull(library.findReleasesByArtist("Pooper"));
    }

    @Test
    void testFindReleaseByRating() {
        library.addRelease(okComputer);
        library.addRelease(marqueeMoon);
        assertEquals(okComputer, library.findReleasesByRating("10"));
    }

    @Test
    void testFindReleaseByRatingSecond() {
        library.addRelease(okComputer);
        library.addRelease(marqueeMoon);
        assertEquals(marqueeMoon, library.findReleasesByRating("9"));
    }

    @Test
    void testFindReleaseByRatingNull() {
        library.addRelease(okComputer);
        library.addRelease(marqueeMoon);
        assertNull(library.findReleasesByRating("0"));
    }


    @Test
    void testFindReleaseByNameNull() {
        library.addRelease(okComputer);
        library.addRelease(marqueeMoon);
        library.findReleasesByName("Pooper");
        assertNull(library.findReleasesByName("Pooper"));
    }


    @Test
    void testFindReleasesByGenre() {
        library.addRelease(okComputer);
        library.addRelease(marqueeMoon);
        library.addRelease(altRockAlbum);
        ArrayList<Release> testList = new ArrayList<Release>() {{
            add(okComputer);
            add(altRockAlbum);
        }};
        library.findReleasesByGenre("Alt-rock");
        assertEquals(testList, library.findReleasesByGenre("Alt-rock"));
    }

    @Test
    void testGetLibrary() {
        library.addRelease(okComputer);
        library.addRelease(marqueeMoon);
        ArrayList<Release> testList = new ArrayList<Release>() {{
            add(okComputer);
            add(marqueeMoon);
        }};
        assertEquals(testList, library.getLibrary());
    }
}
