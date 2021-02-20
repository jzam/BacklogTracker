# BacklogTracker
An Android app to keep track of a user's backlog of shows to watch, books to read, and games to play.

## Purpose
This is a project to learn and apply Android development concepts. I am also planning to personally use it to keep track of my entertainment backlog.

## Project Status

Basic functionality has been implemented. The main view is a saved backlog list with items on your backlog with some basic information for each. You can edit, add, and delete items. You can set a numeric sort value for each item to order the list. You can also keep track of progress with a simple X/Y format (i.e. 5/12 episodes for a TV show).

## Future Functionality Ideas
- Add data validation for user input
- Add "status" field support - sublist buttons on top ("unstarted", "in-progress", "completed")
- Add "PROG UP" button to item card to increment progress
- Add dates started/completed fields for item
- Add notes text field for item
- Add menu options - About, Preferences
- Look into methods of getting rid of soft keyboard when user clicks out of editable fields
- Look into improving design/visuals

## Concepts Used So Far
- The Model-View-ViewModel (MVVM) pattern of Android development
- Navigation graph for moving through fragments within the app
- ViewModel, LiveData, and data binding to handle UI presentation and data
- Room database and coroutines to save and manipulate backlog data
- RecyclerView to efficiently display the backlog list.
