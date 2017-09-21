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

ASSUMPTIONS
-----------
