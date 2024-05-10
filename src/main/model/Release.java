package model;

import org.json.JSONObject;

import java.util.ArrayList;

// This class represents a release of music, as either an LP, EP, or Single
//
public class Release {

    private String name;
    String artist;
    private ArrayList<String> genreTags;
    private int rating;
    private ArrayList<Comment> comments;

    // creates a new release with a name, artist list, genre list, rating, and comment list
    public Release(String name, String artist, String genre, int rating) {
        this.name = name;
        this.artist = artist;
        genreTags = new ArrayList<>();
        genreTags.add(genre);
        this.rating = rating;
        comments = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECT: changes the name of the release
    public void changeName(String name) {
        EventLog.getInstance().logEvent(new Event(
                "Changed release name from " + this.name + " to " + name));
        this.name = name;
    }

    // MODIFIES: this
    // EFFECT: changes the name of the artist
    public void changeArtist(String artist) {
        EventLog.getInstance().logEvent(new Event(
                "Changed artist name from " + this.artist + " to " + artist + " in release " + this.name));
        this.artist = artist;
    }

    // REQUIRES: genre != null
    // MODIFIES: this
    // EFFECT: adds an genre to the list of genres
    public void addGenre(String genre) {
        genreTags.add(genre);
        EventLog.getInstance().logEvent(new Event("Added genre tag " + genre + " in release " + this.name));
    }

    // REQUIRES: genreCandidate != null
    // MODIFIES: this
    // EFFECTS: removes an genre from the list.
    //          returns true if successful, false if not
    public boolean removeGenre(String genreCandidate) {
        for (String genre : genreTags) {
            if (genre.equals(genreCandidate)) {
                genreTags.remove(genre);
                EventLog.getInstance().logEvent(new Event(
                        "Removed genre tag " + genreCandidate + " in release " + this.name));
                return true;
            }
        }
        EventLog.getInstance().logEvent(new Event(
                "Unable to find genre " + genreCandidate + " to remove in release " + this.name));
        return false;
    }

    // REQUIRES: 0 <= rating <= 10
    // MODIFIES: this
    // EFFECT: changes the rating of the release
    public void changeRating(int rating) {
        EventLog.getInstance().logEvent(new Event(
                "Changed rating from " + this.rating + " to " + rating + " in release " + this.name));
        this.rating = rating;
    }

    // MODIFIES: this
    // EFFECT: creates a new comment with given text and adds it to the comment list
    public void addComment(String text) {
        comments.add(new Comment(text));
        EventLog.getInstance().logEvent(new Event(
                "Added comment " + text + " in release " + this.name));
    }

    // REQUIRES: commentCandidate != null
    // MODIFIES: this
    // EFFECTS: removes an genre from the list.
    //          returns true if successful, false if not
    public boolean removeComment(String commentCandidate) {
        for (Comment c : comments) {
            if (c.getText().equals(commentCandidate)) {
                comments.remove(c);
                EventLog.getInstance().logEvent(new Event(
                        "Removed genre tag " + commentCandidate + " in release " + this.name));
                return true;
            }
        }
        EventLog.getInstance().logEvent(new Event(
                "Unable to find comment " + commentCandidate + " to remove in release " + this.name));
        return false;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("artist", artist);
        json.put("genres", genreTags);
        json.put("rating", rating);
        json.put("comments", getCommentsToJson());
        EventLog.getInstance().logEvent(new Event("Converted " + this.name + " to JSON"));
        return json;
    }

    public ArrayList<String> getCommentsToJson() {
        ArrayList<String> retList = new ArrayList<>();
        if (comments.size() > 0) {
            for (Comment c : comments) {
                retList.add(c.getText());
            }
        }
        EventLog.getInstance().logEvent(new Event("Converted comments in release " + this.name + " to JSON"));
        return retList;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public ArrayList<String> getGenres() {
        return genreTags;
    }

    public int getGenresLength() {
        return genreTags.size();
    }

    public int getRating() {
        return rating;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public int getCommentsLength() {
        return comments.size();
    }
}
