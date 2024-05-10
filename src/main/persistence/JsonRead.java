package persistence;

import model.Library;
import model.Release;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// class for loading from file
public class JsonRead {
    private String destination;

    // EFFECT: constructs a json reader with a file path
    public JsonRead(String destination) {
        this.destination = destination;
    }

    // EFFECTS: reads library from file and returns it;
    // throws IOException if theres an error
    public Library read() throws IOException {
        String jsonData = readFile(destination);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECT: parses current library
    private Library parseLibrary(JSONObject jsonObj) {
        Library retLib = new Library();
        addReleases(retLib, jsonObj);
        return retLib;
    }

    // EFFECT: adds releases to the file
    private void addReleases(Library lib, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("releases");
        for (Object json : jsonArray) {
            JSONObject next = (JSONObject) json;
            addRelease(lib, next);
        }
    }

    // EFFECT: adds a release to the file
    private void addRelease(Library lib, JSONObject jsonObject) {
        List<String> genreList = new ArrayList<>();
        List<String> commentList = new ArrayList<>();

        String name = jsonObject.getString("name");
        String artist = jsonObject.getString("artist");
        JSONArray genresJson = jsonObject.getJSONArray("genres");
        int rating = jsonObject.getInt("rating");
        JSONArray commentsJson = jsonObject.getJSONArray("comments");

        for (int i = 0; i < genresJson.length(); i++) {
            genreList.add(genresJson.getString(i));
        }
        for (int i = 0; i < commentsJson.length(); i++) {
            commentList.add(commentsJson.getString(i));
        }

        Release r = new Release(name, artist, "@$%#^*&", rating);
        lib.addRelease(r);
        r.removeGenre("@$%#^*&");
        for (String g : genreList) {
            r.addGenre(g);
        }
        for (String c : commentList) {
            r.addComment(c);
        }
    }
}
