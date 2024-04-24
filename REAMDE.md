# Mobile Apps

# Steps
- [-] Admin
  - [x] Create Firebase Account
  - [ ] IMPORTANT change firebase account to the one created by the uni

- [x] Login
    - [x] Layout with the text, edittext, and button to sign in or sign up
    - [x] Enable Firebase Login and do integration with the layout
    - [x] Create logic to login with firebase login
    - [x] Create logic to sign up with firebase

- [ ] Database
    - [x] Enable Firebase Firestore
    - [x] Do tests to store data of the user
    - [ ] When a new run is done, save a file for each user with the data

- [ ] GPS Tracker
    - [x] Do layout, with canvas for the map, buttons and score
    - [x] add button in the main view and link to the created layout
    - [ ] ask for the current location if it does not have access
    - [ ] add functionality to show the map with the current location
    - [ ] add timer when the button is started, and stop when it is stopped
    - [ ] add tracking functionality
    - [ ] record km run
    - [ ] do calculations of the time it took
    - [ ] upload data to database when it is stopped

- [ ] Weather
    - [x] create layout with views
    - [x] create button in the main view and link to the created layout
    - [X] functionality of the api to get weather of a location
    - [X] integrate info with the layout
    - [ ] ask for the current location if it does not have access

- [ ] Leaderboard
    - [x] create layout with views
    - [x] create button in the main view and link to the created layout
    - [ ] database endpoint to leaderboard and update layout to get from database
    - [ ] firebase cloud function that when a new record is done compare to the top
    - [ ] if it is a record upload the leaderboard
    - [ ] send notification to all users when record

# Important Todos

# Resources
TOP RESOURCE https://github.com/psyfb2/Android-Runner-Tracker/tree/master/app/src/main/java/com/example/runnertracker
https://www.kodeco.com/28767779-how-to-make-an-android-run-tracking-app][link]

https://github.com/anmol-dhar/CodeClauseInternship_EasyAuthenticationApp/

# Sign Report Gradle
> Task :app:signingReport
Variant: debug
Config: debug
Store: /home/god/.config/.android/debug.keystore
Alias: AndroidDebugKey
MD5: CA:66:6E:6F:90:C2:14:D4:C1:40:E5:FF:50:EE:FC:D5
SHA1: 64:79:0F:35:DC:41:CB:39:78:87:25:EE:AE:68:E4:B7:3B:B5:7F:A1
SHA-256: 66:A4:31:F8:4E:33:59:0C:67:B7:DC:D6:F4:64:CE:48:8B:2D:1A:A6:CF:47:D1:9C:EA:40:C2:D7:B0:23:0B:97
Valid until: Thursday, December 4, 2053
----------
Variant: release
Config: null
Store: null
Alias: null
----------
Variant: debugAndroidTest
Config: debug
Store: /home/god/.config/.android/debug.keystore
Alias: AndroidDebugKey
MD5: CA:66:6E:6F:90:C2:14:D4:C1:40:E5:FF:50:EE:FC:D5
SHA1: 64:79:0F:35:DC:41:CB:39:78:87:25:EE:AE:68:E4:B7:3B:B5:7F:A1
SHA-256: 66:A4:31:F8:4E:33:59:0C:67:B7:DC:D6:F4:64:CE:48:8B:2D:1A:A6:CF:47:D1:9C:EA:40:C2:D7:B0:23:0B:97
Valid until: Thursday, December 4, 2053
----------

BUILD SUCCESSFUL in 14s
1 actionable task: 1 executed
