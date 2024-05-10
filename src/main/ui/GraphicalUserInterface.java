package ui;

import model.Comment;
import model.EventLog;
import model.Library;
import model.Release;
import persistence.JsonRead;
import persistence.JsonWrite;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// inspired by DrawingPlayer proved by the CPSC210 team
// this class is for the gui
public class GraphicalUserInterface extends JFrame implements ActionListener {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    private static final int BORDER = 90;

    private Library library;
    private Release currentRelease;
    private JsonWrite writer;
    private JsonRead reader;
    private static final String JSON_STORE = "./data/data.json";

    private JFrame frame;
    private JPanel panel;

    private Image background;

    // row 1 //done
    private JButton findByName;
    private JButton findByArtist;
    private JButton findByGenre;
    private JButton findByRating;

    // row 2 //done
    private JButton addTemplate;
    private JButton viewLibrary;
    private JButton saveButton;
    private JButton loadButton;

    //row 3 // done
    private JButton editName;
    private JButton editArtist;
    private JButton editGenre;
    private JButton editRating;

    // row 4 // done
    private JButton addComment;
    private JButton viewComments;
    private JButton deleteRelease;
    private JButton exitRelease;

    // row 5 //done
    private JLabel nameLabel;
    private JLabel artistLabel;
    private JLabel genresLabel;
    private JLabel ratingLabel;

    // row 6 //done
    private JLabel name;
    private JLabel artist;
    private JLabel genres;
    private JLabel rating;


    // EFFECT: initalizes the gui
    public GraphicalUserInterface() {
        try {
            initFields();
            initBackground();
            initJFrame();
            initButtons();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Could not find image!");
        }
    }

    // EFFECT: initializes fields
    private void initFields() {
        library = new Library();
        writer = new JsonWrite(JSON_STORE);
        reader = new JsonRead(JSON_STORE);
    }

    // EFFECT: inits background
    private void initBackground() throws IOException {
        background = ImageIO.read(new File("src/main/ui/bg.jpg")); // BACKGROUND FROM: https://pngtree.com/freebackground/blue-musical-notes-background_1986629.html
        frame = new JFrame();
        frame.setContentPane(new BackgroundImage(background));
    }


    // EFFECT: initalizes jframe
    private void initJFrame() {
        // window
        panel = new JPanel();
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(BORDER * 2, BORDER, BORDER + (BORDER / 2), BORDER));
        panel.setLayout(new GridLayout(0, 4, 5, 5));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("My Musical Library");
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                library.printLog(EventLog.getInstance());
                System.exit(0);
            }
        });
    }



    // EFFECT: init buttons and labels
    private void initButtons() {
        initRow1();
        initRow2();
        initRow3();
        initRow4();
        initRow5();
        initRow6();
    }

    // EFFECT: initalizes first row of buttons
    private void initRow1() {
        findByName = new JButton("Find release by name");
        findByName.addActionListener(this);
        panel.add(findByName);

        findByArtist = new JButton("Find release by artist");
        findByArtist.addActionListener(this);
        panel.add(findByArtist);

        findByGenre = new JButton("Find release by genre");
        findByGenre.addActionListener(this);
        panel.add(findByGenre);

        findByRating = new JButton("Find release by rating");
        findByRating.addActionListener(this);
        panel.add(findByRating);
    }

    // EFFECT: initalizes second row of buttons
    private void initRow2() {
        addTemplate = new JButton("Add new release template");
        addTemplate.addActionListener(this);
        panel.add(addTemplate);

        viewLibrary = new JButton("View Library");
        viewLibrary.addActionListener(this);
        panel.add(viewLibrary);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        panel.add(saveButton);

        loadButton = new JButton("Load");
        loadButton.addActionListener(this);
        panel.add(loadButton);
    }

    // EFFECT: initalizes third row of buttons
    private void initRow3() {
        editName = new JButton("Change release name");
        editName.addActionListener(this);
        panel.add(editName);

        editArtist = new JButton("Change release artist");
        editArtist.addActionListener(this);
        panel.add(editArtist);

        editGenre = new JButton("add/remove release genre tag");
        editGenre.addActionListener(this);
        panel.add(editGenre);

        editRating = new JButton("Change release rating");
        editRating.addActionListener(this);
        panel.add(editRating);
    }

    // EFFECT: initalizes fourth row of buttons
    private void initRow4() {
        addComment = new JButton("Add comment");
        addComment.addActionListener(this);
        panel.add(addComment);

        viewComments = new JButton("View comments");
        viewComments.addActionListener(this);
        panel.add(viewComments);

        deleteRelease = new JButton("Delete release");
        deleteRelease.addActionListener(this);
        panel.add(deleteRelease);

        exitRelease = new JButton("Exit release");
        exitRelease.addActionListener(this);
        panel.add(exitRelease);
    }

    // EFFECT: initalizes labels
    private void initRow5() {
        nameLabel = new JLabel("Name:");
        nameLabel.setOpaque(true);
        panel.add(nameLabel);
        artistLabel = new JLabel("Artist:");
        artistLabel.setOpaque(true);
        panel.add(artistLabel);
        genresLabel = new JLabel("Genres:");
        genresLabel.setOpaque(true);
        panel.add(genresLabel);
        ratingLabel = new JLabel("Rating:");
        ratingLabel.setOpaque(true);
        panel.add(ratingLabel);
    }

    // EFFECT: initalizes labels for release, starts blank
    private void initRow6() {
        name = new JLabel("");
        name.setOpaque(true);
        panel.add(name);
        artist = new JLabel("");
        artist.setOpaque(true);
        panel.add(artist);
        genres = new JLabel("");
        genres.setOpaque(true);
        panel.add(genres);
        rating = new JLabel("");
        rating.setOpaque(true);
        panel.add(rating);
    }


    private void setCurrentRelease() {
        if (currentRelease != null) {
            name.setText(currentRelease.getName());
            artist.setText(currentRelease.getArtist());
            genres.setText(currentRelease.getGenres().toString());
            rating.setText(Integer.toString(currentRelease.getRating()));
        } else {
            name.setText("");
            artist.setText("");
            genres.setText("");
            rating.setText("");
        }
    }


    @Override
    // EFFECT: handles action events
    public void actionPerformed(ActionEvent e) {
        saveLoadHandler(e);
        findReleaseHandler(e);
        if (currentRelease != null) {
            editReleaseHandler(e);
            commentHandler(e);
        }
    }

    // EFFECT: handles release finding
    private void findReleaseHandler(ActionEvent e) {
        if (e.getSource() == findByName) {
            findRelease();
        } else if (e.getSource() == findByArtist) {
            findArtist();
        } else if (e.getSource() == findByGenre) {
            findGenre();
        } else if (e.getSource() == findByRating) {
            findRating();
        } else if (e.getSource() == exitRelease) {
            exitRelease();
        } else if (e.getSource() == addTemplate) {
            addTemplate();
        } else if (e.getSource() == viewLibrary) {
            viewLibrary();
        }
    }

    // EFFECT: handles the editing of releases
    private void editReleaseHandler(ActionEvent e) {
        if (e.getSource() == editName) {
            currentRelease.changeName(JOptionPane.showInputDialog("Enter new name: "));
            setCurrentRelease();
        } else if (e.getSource() == editArtist) {
            currentRelease.changeArtist(JOptionPane.showInputDialog("Enter new artist: "));
            setCurrentRelease();
        } else if (e.getSource() == editGenre) {
            editGenre();
        } else if (e.getSource() == editRating) {
            editRating();
        } else if (e.getSource() == deleteRelease) {
            int reply = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete this release?",
                    "Delete?",  JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                library.removeRelease(currentRelease.getName());
                currentRelease = null;
                setCurrentRelease();
            }
        }
    }

    // EFFECT: handles comments
    private void commentHandler(ActionEvent e) {
        if (e.getSource() == addComment) {
            currentRelease.addComment(JOptionPane.showInputDialog("Add comment: "));
        } else if (e.getSource() == viewComments) {
            String retString = "Comments:";
            for (Comment c : currentRelease.getComments()) {
                retString += ("\n" + c.getText());
            }
            JOptionPane.showMessageDialog(null, retString);
        }
    }

    // EFFECT: edits genre
    private void editGenre() {
        String asker = "Enter new genre tag, or type \"@d\" to delete most recent tag: ";
        String genreCandidate = JOptionPane.showInputDialog(asker);
        if (genreCandidate.equals("@d")) {
            if (!currentRelease.getGenres().isEmpty()) {
                String removedGenre = currentRelease.getGenres().get(0);
                currentRelease.getGenres().remove(removedGenre);
                JOptionPane.showMessageDialog(null, "Genre " + removedGenre + " removed");
            } else {
                JOptionPane.showMessageDialog(null, "No genre tags to remove");
            }
        } else {
            currentRelease.addGenre(genreCandidate);
        }
        setCurrentRelease();
    }

    // EFFECT: edits release
    private void editRating() {
        int ratingCandidate = Integer.parseInt(JOptionPane.showInputDialog("Enter new rating: "));
        if (ratingCandidate > 10) {
            ratingCandidate = 10;
        }
        if (ratingCandidate < 0) {
            ratingCandidate = 0;
        }
        currentRelease.changeRating(ratingCandidate);
        setCurrentRelease();
    }

    // EFFECTS: shows current release library
    private void viewLibrary() {
        String displayString = "Releases in library:";
        for (Release r : library.getLibrary()) {
            displayString += ("\n" + r.getName());
        }
        JOptionPane.showMessageDialog(null, displayString);
    }

    // EFFECT: exits current release
    private void exitRelease() {
        currentRelease = null;
        setCurrentRelease();
    }

    // EFFECT: adds new release template
    private void addTemplate() {
        library.addRelease(new Release("New Name", "New Artist", "New Genre", 0));
        JOptionPane.showMessageDialog(null, "New release template added");
    }

    // EFFECT: find release with name
    private void findRelease() {
        String findName = JOptionPane.showInputDialog("Enter release name: ");
        Release finder = library.findReleasesByName(findName);
        if (finder != null) {
            currentRelease = finder;
            setCurrentRelease();
            JOptionPane.showMessageDialog(null, "Release " + finder.getName() + " found!");
        } else {
            JOptionPane.showMessageDialog(null, "Release not found");
        }
    }

    // EFFECT: find release with artist
    private void findArtist() {
        String findName = JOptionPane.showInputDialog("Enter release artist: ");
        Release finder = library.findReleasesByArtist(findName);
        if (finder != null) {
            currentRelease = finder;
            setCurrentRelease();
            JOptionPane.showMessageDialog(null, "Release with artist " + finder.getArtist() + " found!");
        } else {
            JOptionPane.showMessageDialog(null, "Release not found");
        }
    }

    // EFFECT: find release with artist
    private void findGenre() {
        String findName = JOptionPane.showInputDialog("Enter release genre tag: ");
        ArrayList<Release> finder = library.findReleasesByGenre(findName);
        if (!finder.isEmpty()) {
            currentRelease = finder.get(0);
            setCurrentRelease();
            JOptionPane.showMessageDialog(null, "Release with genre tag " + findName + " found!");
        } else {
            JOptionPane.showMessageDialog(null, "Release not found");
        }
    }

    // EFFECT: find release with artist
    private void findRating() {
        String findName = JOptionPane.showInputDialog("Enter release rating: ");
        Release finder = library.findReleasesByRating(findName);
        if (finder != null) {
            currentRelease = finder;
            setCurrentRelease();
            JOptionPane.showMessageDialog(null, "Release with rating " + finder.getRating() + " found!");
        } else {
            JOptionPane.showMessageDialog(null, "Release not found");
        }
    }

    private void saveLoadHandler(ActionEvent e) {
        if (e.getSource() == saveButton && library != null) {
            trySave();
            JOptionPane.showMessageDialog(null, "Saved!");
        } else if (e.getSource() == loadButton) {
            tryLoad();
            JOptionPane.showMessageDialog(null, "Loaded!");
        } else if (e.getSource() == saveButton && library == null) {
            JOptionPane.showMessageDialog(null, "Error: Library is null!");
        }
    }



    // SAVE FUNCTIONALITY

    // MODIFIES: data.json
    // EFFECT: tries to save to file
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

    // EFFECT: tries to load data
    public void tryLoad() {
        System.out.println("Loading...");
        try {
            library = reader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}
