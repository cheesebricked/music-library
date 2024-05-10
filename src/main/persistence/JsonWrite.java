package persistence;

import java.io.*;

import model.Library;
import org.json.JSONObject;

// class for saving to file
public class JsonWrite {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer with desitination file
    public JsonWrite(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination not found
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON to file
    public void write(Library l) {
        JSONObject json = new JSONObject();
        json.put("releases", l.toJson());
        writer.print((json.toString(TAB)));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }
}
