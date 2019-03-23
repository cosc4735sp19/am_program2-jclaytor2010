# Program # 2

Name:  Jacob Claytor

Cosc 4735

Description:  Button at the bottom handles camera. Once you get an image, click on the marker to look at the image up close. Use the close button to make the image disappear. If you have difficulty seeing multiple images, I added a title for each that displays what number photo it is for the current session. Hopefully that helps you see that there are definitely photos being added.

Note: Worked with Easton Tuttle

Anything that doesn't work: Not that I am aware of. I am sure you are aware, but you need to zoom in very far to see all of the images. I also attempted to resize the markers because the images were so large as icons, but it ruins the resolution of the picture. I think it helps being able to see multiple images.

*Please let me know if you need other forms of keys, etc. I was struggling understanding what keys you needed. The API key should be in the build and the debugger key is in the root folder. *

# Graded: 47/50 #

* Last location updates are handled when the camera is opened, which creates issues with marker placement when pictures are cancelled. *(-3 points)*

**For example:**

**Test Case:**
Open app at the Engineering Building. Open the camera, but do not take a picture and back out of the camera. Walk over to the Union, take a picture. Walk to the Ben Franklin statue and take a picture

**Result:**
Picture at the Union shows as though it was taken at Engineering and picture of the Ben Franklin statue shows as though it was taken at the Union.

While it may seem intuitive to only get a location when it is needed (i.e. when you want to take a picture and place a marker), it is best just to recieve a constant stream of updating locations on a set interval. Alternatively, you just need to ensure that you are handling the case that no picture is taken. For some reason it still wants to use a location even if you are updating it again the next time you open the camera.

Overall, everything works perfectly if you are only testing for the ideal use case (i.e. everytime the user opens the camera you assume that a picture is taken), but it is important to account for any strange things the user might do when using the app. These are the weird sort of things I will be looking at when testing your apps.
