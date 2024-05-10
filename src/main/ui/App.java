package ui;

import model.Comment;
import model.Library;
import model.Release;
import persistence.JsonRead;
import persistence.JsonWrite;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// UI functionality inspired by TellerApp
// save functionality inspired by JsonSerializationDemo

public class App {

    private Library library;
    private Scanner input;
    Release currentRelease;
    JsonWrite writer;
    JsonRead reader;
    private static final String JSON_STORE = "./data/data.json";

    enum State {
        INIT,
        FIND_NAME,
        FIND_ARTIST,
        FIND_GENRE,
        FIND_RATING,
        EDIT
    }

    enum EditState {
        NONE,
        EDIT_NAME,
        EDIT_ARTIST,
        EDIT_GENRE,
        EDIT_RATING,
        ADD_COMMENT
    }

    State state = State.INIT;
    EditState editState = EditState.NONE;

    public App() {
        run();
    }

    // EFFECT: runs application
    public void run() {
        boolean running = true;
        String command;

        init();

        while (running) {
            displayMenu();
            command = input.next();
            //command = command.toLowerCase();

            if (command.equals("exit")) {
                System.out.println("Exiting...");
                running = false;
            } else if (command.equals("reset")) {
                state = State.INIT;
                editState = EditState.NONE;
            } else {
                processCommand(command);
            }
        }
    }

    // EFFECT: initializes library
    public void init() {
        library = new Library();
        writer = new JsonWrite(JSON_STORE);
        reader = new JsonRead(JSON_STORE);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECT: prints current release in library
    public void printReleaseList(List<Release> list) {
        for (Release r : list) {
            System.out.println(r.getName());
        }
    }

    // MODIFIES: this
    // EFFECT: finds release by name, and sets currentRelease to it
    public void findByRelease(String command) {
        if (library.findReleasesByName(command) != null) {
            currentRelease = library.findReleasesByName(command);
            System.out.println("Found" + currentRelease.getName() + "\n");
            state = State.EDIT;
        } else {
            System.out.println(command + "not found\n");
        }
    }

    // MODIFIES: this
    // EFFECT: finds release by artist, and sets currentRelease to it
    public void findByArtist(String command) {
        if (library.findReleasesByArtist(command) != null) {
            currentRelease = library.findReleasesByArtist(command);
            System.out.println("Found" + currentRelease.getName() + "\n");
            state = State.EDIT;
        } else {
            System.out.println(command + "not found\n");
        }
    }

    // MODIFIES: this
    // EFFECT: finds release by genre, and sets currentRelease to it
    public void findByGenre(String command) {
        if (library.findReleasesByGenre(command) != null) {
            currentRelease = library.findReleasesByGenre(command).get(0);
            System.out.println("Found" + currentRelease.getName() + "\n");
            state = State.EDIT;
        } else {
            System.out.println(command + "not found\n");
        }
    }

    // MODIFIES: this
    // EFFECT: finds release by rating, and sets currentRelease to it
    public void findByRating(String command) {
        if (library.findReleasesByRating(command) != null) {
            currentRelease = library.findReleasesByRating(command);
            System.out.println("Found: " + currentRelease.getName() + "\n");
            state = State.EDIT;
        } else {
            System.out.println(command + "not found\n");
        }
    }

    // EFFECT: prints release information
    public void viewRelease(Release r) {
        if (currentRelease == null) {
            System.out.println("Please select a release\n");
        } else {
            System.out.println("Name: " + r.getName());
            System.out.println("Artist: " + r.getArtist());
            System.out.println("Genres: " + String.join(", ", r.getGenres()));
            System.out.println("Rating: " + r.getRating());
            printComments();
            System.out.println("\n");
        }
    }

    // EFFECT: Prints comments
    public void printComments() {
        if (currentRelease.getComments().size() == 0) {
            System.out.println("No comments");
        } else {
            System.out.println("Comments:");
            for (Comment c : currentRelease.getComments()) {
                System.out.println(c.getText());
            }
        }
    }


    // MODIFIES: this
    // EFFECT: processes commands
    public void processCommand(String command) {
        switch (state) {
            case INIT:
                initMenu(command);
                break;
            case FIND_NAME:
                findByRelease(command);
                break;
            case FIND_ARTIST:
                findByArtist(command);
                break;
            case FIND_GENRE:
                findByGenre(command);
                break;
            case FIND_RATING:
                findByRating(command);
                break;
            case EDIT :
                editMenu(command);
                break;
        }
    }

    //
    // EFFECT: runs the main menu
    public void initMenu(String command) {
        if (command.equals("a")) {
            addReleaseTemplate();
        } else if (command.equals("l")) {
            viewLibrary();
        } else if (command.equals("n")) {
            System.out.println("Please enter the release's name:");
            state = State.FIND_NAME;
        } else if (command.equals("at")) {
            System.out.println("Please enter the release's artist:");
            state = State.FIND_ARTIST;
        } else if (command.equals("g")) {
            System.out.println("Please enter a genre in the release:");
            state = State.FIND_GENRE;
        } else if (command.equals("r")) {
            System.out.println("Please enter the release's rating:");
            state = State.FIND_RATING;
        } else if (command.equals("s")) {
            trySave();
        } else if (command.equals("ld")) {
            tryLoad();
        } else {
            System.out.println("Invalid Input\n");
        }
    }

    public void trySave() {
        System.out.println("Saving...");
        try {
            writer.open();
            writer.write(library);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    public void tryLoad() {
        System.out.println("Loading...");
        try {
            library = reader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    public void addReleaseTemplate() {
        library.addRelease(new Release("New Name", "New Artist", "New Genre", 0));
        System.out.println("New release template added\n");
    }

    public void viewLibrary() {
        System.out.println("Current library: ");
        printReleaseList(library.getLibrary());
    }

    // EFFECT: runs the edit menu
    public void editMenu(String command) {
        switch (editState) {
            case NONE:
                runEditCaseNone(command);
                break;
            case EDIT_NAME:
                editName(command);
                break;
            case EDIT_ARTIST:
                editArtist(command);
                break;
            case EDIT_GENRE:
                editGenre(command);
                break;
            case EDIT_RATING:
                editRating(command);
                break;
            case ADD_COMMENT:
                addComment(command);
                break;
        }
    }

    // MODIFIES: this
    // EFFECT: lets user edit the name of a release
    public void editName(String command) {
        currentRelease.changeName(command);
        editState = EditState.NONE;
        state = State.EDIT;
    }

    // MODIFIES: this
    // EFFECT: lets user edit the artist of a release
    public void editArtist(String command) {
        currentRelease.changeArtist(command);
        editState = EditState.NONE;
        state = State.EDIT;
    }

    // MODIFIES: this
    // EFFECT: lets user add a genre tag, or remove the oldest one
    public void editGenre(String command) {
        if (command.equals("d")) {
            currentRelease.getGenres().remove(currentRelease.getGenres().get(0));
        } else {
            currentRelease.addGenre(command);
        }
        editState = EditState.NONE;
        state = State.EDIT;
    }

    // MODIFIES: this
    // EFFECT: lets user edit the rating of a release
    public void editRating(String command) {
        currentRelease.changeRating(Integer.parseInt(command));
        editState = EditState.NONE;
        state = State.EDIT;
    }

    // MODIFIES: this
    // EFFECT: lets user add a comment to a release
    public void addComment(String command) {
        currentRelease.addComment(command);
        editState = EditState.NONE;
        state = State.EDIT;
    }

    // MODIFIES: this
    // EFFECT: lets user delete current release
    public void deleteCurrentRelease() {
        System.out.println("Deleting...");
        library.removeRelease(currentRelease.getName());
        currentRelease = null;
        editState = EditState.NONE;
        state = State.INIT;
    }

    // EFFECT: runs initial edit menu
    public void runEditCaseNone(String command) {
        if (command.equals("n")) {
            System.out.println("Please rename the release:");
            editState = EditState.EDIT_NAME;
        } else if (command.equals("a")) {
            System.out.println("Please rename the release's artist:");
            editState = EditState.EDIT_ARTIST;
        } else if (command.equals("g")) {
            System.out.println("Add new genre tag, or key d to delete oldest release tag");
            editState = EditState.EDIT_GENRE;
        } else if (command.equals("r")) {
            System.out.println("Please rerate the release:");
            editState = EditState.EDIT_RATING;
        } else if (command.equals("d")) {
            deleteCurrentRelease();
        } else if (command.equals("c")) {
            System.out.println("Enter comment:");
            editState = EditState.ADD_COMMENT;
        } else if (command.equals("v")) {
            viewRelease(currentRelease);
        } else if (command.equals("b")) {
            editState = EditState.NONE;
            state = State.INIT;
        }
    }

    // EFFECT: chooses which menu to display
    public void displayMenu() {
        switch (state) {
            case INIT:
                displayMenuInit();
                break;
            case EDIT:
                displayMenuEdit();
                break;
        }
    }

    // EFFECT: displays main menu instructions
    public void displayMenuInit() {
        System.out.println("MENU:");
        System.out.println("a -> add new release template");
        System.out.println("l -> view current library");
        System.out.println("n -> find and select release by name");
        System.out.println("at -> find and select release by artist");
        System.out.println("g -> find and select release by genre tag");
        System.out.println("r -> find and select release by rating");
        System.out.println("s -> save");
        System.out.println("ld -> load");
        System.out.println("exit -> exit\n");
    }

    // EFFECT: displays main menu instructions
    public void displayMenuEdit() {
        System.out.println("MENU:");
        System.out.println("n -> change name");
        System.out.println("a -> change artist");
        System.out.println("g -> change genre tag");
        System.out.println("r -> change rating");
        System.out.println("d -> delete release");
        System.out.println("c -> add comment");
        System.out.println("v -> view release");
        System.out.println("b -> back");
        System.out.println("exit -> exit\n");
    }
}