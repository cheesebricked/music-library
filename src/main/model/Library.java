package model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

// This class represents the users library of music as an ArrayList
//
public class Library {

    private ArrayList<Release> library;

    // EFFECT: new empty library
    public Library() {
        library = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds release to library
    public void addRelease(Release r) {
        EventLog.getInstance().logEvent(new Event("Added release " + r.getName()));
        library.add(r);
    }

    // REQUIRES: releaseName != null
    // MODIFIES: this
    // EFFECTS: removes a release from the library.
    //          returns true if successful, false if not
    public boolean removeRelease(String releaseName) {
        for (Release r : library) {
            if (r.getName().equals(releaseName)) {
                library.remove(r);
                EventLog.getInstance().logEvent(new Event("Deleted release " + releaseName));
                return true;
            }
        }
        EventLog.getInstance().logEvent(new Event("Could not find release " + releaseName));
        return false;
    }

    // EFFECTS: finds the first in library by name, returns null if not find
    public Release findReleasesByName(String findName) {
        for (Release r : library) {
            if (r.getName().equals(findName)) {
                EventLog.getInstance().logEvent(new Event("Found release " + findName + " by name"));
                return r;
            }
        }
        EventLog.getInstance().logEvent(new Event("Unable to find release " + findName));
        return null;
    }

    // EFFECTS: finds the first in library by artist, returns null if not find
    public Release findReleasesByArtist(String findName) {
        for (Release r : library) {
            if (r.getArtist().equals(findName)) {
                EventLog.getInstance().logEvent(new Event(
                        "Found release " + r.getName() + "by artist " + findName));
                return r;
            }
        }
        EventLog.getInstance().logEvent(new Event("Unable to find release with artist " + findName));
        return null;
    }

    // EFFECTS: finds a releases in library by genre.
    public ArrayList<Release> findReleasesByGenre(String findGenre) {
        ArrayList<Release> releaseList = new ArrayList<>();
        ArrayList<String> releaseNames = new ArrayList<>();
        for (Release r : library) {
            if (r.getGenres().contains(findGenre)) {
                releaseList.add(r);
                releaseNames.add(r.getName());
            }
        }
        if (releaseList.isEmpty()) {
            EventLog.getInstance().logEvent(new Event("Unable to find release with genre " + findGenre));
        } else {
            EventLog.getInstance().logEvent(new Event(
                    "Found releases " + String.join(", ", releaseNames) + " with genre " + findGenre));
        }
        return releaseList;
    }

    // REQUIRES: int must be inputted as string
    // EFFECTS: finds the first in library by rating, returns null if not find
    public Release findReleasesByRating(String findRating) {
        for (Release r : library) {
            if (r.getRating() == Integer.parseInt(findRating)) {
                EventLog.getInstance().logEvent(new Event("Found release " + r.getName() + " by rating"));
                return r;
            }
        }
        EventLog.getInstance().logEvent(new Event("Unable to find release by rating " + findRating));
        return null;
    }

    public ArrayList<Release> getLibrary() {
        return library;
    }

    public int getLibraryLength() {
        return library.size();
    }

    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();
        EventLog.getInstance().logEvent(new Event("Saving current library..."));
        for (Release r : library) {
            EventLog.getInstance().logEvent(new Event("Converting " + r.getName() + " to JSON..."));
            jsonArray.put(r.toJson());
        }
        EventLog.getInstance().logEvent(new Event("Save successful"));
        return jsonArray;
    }

    // EFFECT: prints the current event log
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }
}

