# My Personal Project

## Music library Management System

**What does it do?**
- This program will allow users to *add* and *remove* music releases to a library.
- They can organize these releases in different *folders*, such "favourites", or "to listen".
- Users can *rate* albums, and add *comments* to them.
- Users can *edit* attributes of a release, such as name, artist, or genre tag.

**Who will use it?**
- Music lovers who wish to keep track of what they listen too.

**Why is this project of interest to you?**
- I love music, and I love talking about music, and I know this project will be useful for those like myself.

## User stories:
**As a user, I want to:**
- Be able to add, remove, and rate releases in my library.
- View and search for specific releases in my library, by name, genre tags, or rating.
- Edit releases in my library to change the release name, artist, genre tags, or rating.
- Add comments to releases.
- Have the option to save my current library of releases, including any edits I may have made, to a file.
- Have the option to load a previous save state, from my previously made save file.

## How to use:
1. Press a to add a new release template, add as many as you like.
2. Press n and then type "New Name" to select a release template
3. Follow the instructions on the menu to edit the release
4. Press b to go back to the main menu
5. You can press l to view your current library, or press either n, at, g, or r to search for release in different ways.
- Note: Inputs are case-sensitive

# Instructions for Grader
- You can do the first action related to the user story "Be able to add, remove, and rate releases in my library" by...
  - Clicking the button labeled "Add template" to add a template for a new Release to the current library.
  - You can then press "view releases" to confirm its addition.
  - You can do this as many times as you like.
- You can do the second action related to the user story "Be able to add, remove, and rate releases in my library" by...
  - Selecting a release, finding it by one og the 4 given methods then,
  - Clicking the "delete release" button and then "Yes".
  - You can then press "view releases" to see the release you deleted is gone.
- You can locate my visual component by...
  - Looking at the background. It's an image I found online, I put the link to it in my code.
- You can save the state of my application by...
  - Clicking the "Save" button
- You can reload the state of my application by...
  - Clicking the "Load" button

# Phase 4: Task 2
Wed Apr 03 12:52:42 PDT 2024
Added release release1

Wed Apr 03 12:52:42 PDT 2024
Removed genre tag @$%#^*& in release release1

Wed Apr 03 12:52:42 PDT 2024
Added genre tag genre1 in release release1

Wed Apr 03 12:52:42 PDT 2024
Added genre tag genre2 in release release1

Wed Apr 03 12:52:42 PDT 2024
Added comment comment1 in release release1

Wed Apr 03 12:52:42 PDT 2024
Added comment comment2 in release release1

Wed Apr 03 12:52:42 PDT 2024
Added release pooper

Wed Apr 03 12:52:42 PDT 2024
Removed genre tag @$%#^*& in release pooper

Wed Apr 03 12:52:42 PDT 2024
Added genre tag no genre in release pooper

Wed Apr 03 12:52:42 PDT 2024
Added comment commented in release pooper

Wed Apr 03 12:52:42 PDT 2024
Added comment no comment in release pooper

Wed Apr 03 12:52:48 PDT 2024
Unable to find release with artist artist1

Wed Apr 03 12:53:03 PDT 2024
Added release New Name

Wed Apr 03 12:53:14 PDT 2024
Found release release1 by name

Wed Apr 03 12:53:18 PDT 2024
Unable to find release with artist john lennon

Wed Apr 03 12:53:24 PDT 2024
Changed artist name from david bowie to john lennon in release release1

Wed Apr 03 12:53:28 PDT 2024
Changed release name from release1 to imagine

Wed Apr 03 12:53:31 PDT 2024
Changed rating from 8 to 2 in release imagine

Wed Apr 03 12:53:42 PDT 2024
Added genre tag rock and roll babyyyy in release imagine

Wed Apr 03 12:53:56 PDT 2024
Found releases New Name with genre New Genre

Wed Apr 03 12:53:59 PDT 2024
Deleted release New Name

Wed Apr 03 12:54:01 PDT 2024
Saving current library...

Wed Apr 03 12:54:01 PDT 2024
Converting imagine to JSON...

Wed Apr 03 12:54:01 PDT 2024
Converted comments in release imagine to JSON

Wed Apr 03 12:54:01 PDT 2024
Converted imagine to JSON

Wed Apr 03 12:54:01 PDT 2024
Converting pooper to JSON...

Wed Apr 03 12:54:01 PDT 2024
Converted comments in release pooper to JSON

Wed Apr 03 12:54:01 PDT 2024
Converted pooper to JSON

Wed Apr 03 12:54:01 PDT 2024
Save successful


Process finished with exit code 0



# Phase 4: Task 3
I would refactor the GraphicalUserInterface class, turning all the init methods into something less repetitive.
I think I would refactor the general init() method, which initializes mostly the buttons, into a class 
probably called "Button" that initializes a button, and adds it to a panel. I would then have a method in 
GraphicalUserInterface that takes a list of strings and a panel to add the buttons too, where each string is a button
I would like to make, and makes a button for each string and adds it to the panel.
I would also refactor the find functions in Library to reduce repetitive code. I would do this by creating a base
function for finding a release, with parameters to specify if we are looking for a release by name, artist, genre tag,
or rating.