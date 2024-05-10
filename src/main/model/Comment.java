package model;

// This class represents a comment on a release
public class Comment {

    private String text;

    // EFFECTS: creates a comment with text
    public Comment(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
