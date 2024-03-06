# Mobile Apps

# Steps
- [ ] Admin
  - [ ] Create Firebase Account

- [ ] Login
    - [ ] Layout with the text, edittext, and button to sign in or sign up
    - [ ] Enable Firebase Login and do integration with the layout
    - [ ] Create logic to login with firebase login
    - [ ] Create logic to sign up with firebase

- [ ] Database
    - [ ] Enable Firebase Firestore
    - [ ] Do tests to store data of the user
    - [ ] When a new run is done, save a file for each user with the data

- [ ] GPS Tracker
    - [ ] Do layout, with canvas for the map, buttons and score
    - [ ] add button in the main view and link to the created layout
    - [ ] ask for the current location if it does not have access
    - [ ] add functionality to show the map with the current location
    - [ ] add timer when the button is started, and stop when it is stopped
    - [ ] add tracking functionality
    - [ ] record km run
    - [ ] do calculations of the time it took
    - [ ] upload data to database when it is stopped

- [ ] Weather
    - [ ] create layout with views
    - [ ] create button in the main view and link to the created layout
    - [ ] ask for the current location if it does not have access
    - [ ] functionality of the api to get weather of the current location
    - [ ] integrate info with the layout

- [ ] Leaderboard
    - [ ] create layout with views
    - [ ] create button in the main view and link to the created layout
    - [ ] database endpoint to leaderboard and update layout to get from database
    - [ ] firebase cloud function that when a new record is done compare to the top
    - [ ] if it is a record upload the leaderboard
    - [ ] send notification to all users when record

# Important Todos

# Resources
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
