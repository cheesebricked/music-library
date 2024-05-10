package persistence;

import model.Comment;
import model.Library;
import model.Release;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class JsonTest {
    protected void addThreeReleases(Library l, Release release1, Release release2, Release release3) {
        l.addRelease(release1);
        l.addRelease(release2);
        l.addRelease(release3);
    }

    protected void testReleasesLoop(Library l, List<Release> releaseList) {
        int init = 0;
        for (Release r : l.getLibrary()) {
            assertEquals(r.getName(), releaseList.get(init).getName());
            assertEquals(r.getArtist(), releaseList.get(init).getArtist());
            genreLoop(r.getGenres(), releaseList.get(init).getGenres());
            assertEquals(r.getRating(), releaseList.get(init).getRating());
            commentLoop(r.getComments(), releaseList.get(init).getComments());
            init++;
        }
    }

    private void commentLoop(List<Comment> cl1, List<Comment> cl2) {
        int init = 0;
        for (Comment c : cl1) {
            assertEquals(c.getText(), cl2.get(init).getText());
            init++;
        }
    }

    private void genreLoop(List<String> gl1, List<String> gl2) {
        int init = 0;
        for (String g : gl1) {
            assertEquals(g, gl2.get(init));
            init++;
        }
    }
}
