INTRODUCTION
------------
The "Notepad" program reads in text files that contain #hashtags and @mentions.

A note has a title denoted with a exclamation point, as well as references to other notes' titles with ^.

The program parses these files, organizes the information, and generates reports based on things like #hashtags, @mentions, and ^references.

Given the initial requirements for this assignment, we made the whole thing more complex than it needed to be. We only realized this after clarifications were made. At that point, we decided just to move forward with what we had instead of making things simpler/cleaner/less-bulky.

REQUIREMENTS
------------
Make sure Java is installed/updated.

INSTALLATION/CONFIGURATION
--------------------------
1. Put the text files you wish to parse within the "notes" folder of this package. There are test notes in there, and you can remove them if you wish.
2. CD into the "Notepad" directory of this program in terminal.
3. Run by copy/pasting the command "./gradlew run"
    ** "gradlew run" on windows
4. Perform tests by copy/pasting the command "./gradlew test"
    ** "gradlew test" on windows

INSTRUCTIONS
------------
1. The program requires user input; simply type the number of the option you want and press enter
2. Gradle may display something like "BUILD 75%" at the cursor; ignore this and type your input normally

OUR THOUGHTS
------------
We originally had an incorrect concept of the requirements of this project, and this led the program to being more complicated than necessary. However, at the point that we got clarifications, we decided to simply move forward with what we had instead of trying to go backwards. So, at some points our code may look bulky. We did our best to document and make the code clean where we could.

We decided to use gradle. Testing does not currently work as "gradle build" fails due to file processing errors. This is because the program depends on user input to get the filepath. We do, however, have some test files. We did not get to finish them in time unfortunately, so there are some missing.

Thankfully, running the program works with gradle.

If you have any issues getting the program to read your files, please make sure the filepath doesn't have any extra spaces and ends with '/'. If you're having issues and your notes are in the specified "notes" folder, please contact me or Joe on basecamp.
