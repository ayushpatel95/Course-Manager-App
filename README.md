# Course-Manager-App
In this assignment you will be developing a course manager app to create and display courses. You will learn to use Fragments instead of multiple activities. 
Part 1: Login and Sign Up Screens (15 Points)
The first screen of the app is a user authentication screen. Please follow the
instructions:
1. You need to have a username-password map in the database.
2. You cannot allow more than one users having the same username.
3. The app should ask you to login.
4. If you are not registered, you can Sign Up. Clicking on Sign Up should get you to
Sign Up screen.
5. All the inputs are mandatory for sign up. Clicking on Register button should add a
new user and register them to the database.
6. After you click on Register, it automatically signs you in to the application, and opens
the Course Manager screen.
Part 2: Course Manager Screen (22 Points)
In this screen you should display the list of courses already created, see figure 1(a).
Please follow the instructions:
1. There should be an add action floating button to add new courses. Clicking on the
add button should take you to the Create Course fragment.
2. Below that there should be a list of created courses. Use either RecyclerView, or
CardView to implement that. Do not use ListView.
3. Clicking on any of the courses should take you to Display Course fragment.
4. Long clicking on any of the course should prompt you for deleting the course.
Use alert dialog to implement the prompt.
5. For all screens, four menu options should be there:
1. Home
2. Instructors
3. Add Instructor
4. Logout
5. Exit
6. Clicking on Home should take you to the main screen.
7. Clicking on Instructors should take you to the Instructors screen.
8. Clicking on Add Instructor should take you to Create Instructor Profile screen.
9. Clicking on Logout should log you out and take you to the Login Screen.
10. Clicking on Exit should close the app.
Part 3: Instructors Screen (15 points)
You have to design a usable GUI for this screen. The requirements are:
1. This screen displays a list of instructors.
2. Use RecyclerView/CardView to implement the list.
3. Each element should include name, profile photo, and email of the instructor.
4. You should replicate Main Screen actions to manage instructors in this screen.
Part 4: Add Instructor Screen (20 points)
You are responsible for a usable GUI for this screen.
1. The elements of each instructor profile must include: name, profile photo, email, and
personal website. 
To select a profile photo, use Gallery, and Camera apps.
3. There should be two buttons: Add, and Reset.
4. Clicking on Reset button should prompt you for resetting all the input fields. Use
alert dialog to implement the prompt.
Part 5: Create Course Screen (28 points)
Clicking on the Add action button from the main screen should take you to the Create
course fragment, see Figure 1(b). Please follow the instructions below:
1. If you haven’t created any instructor yet, you should display a text in the place of the
Instructor list stating, “You haven’t added any instructor yet, please add at least one
instructor to continue.” The Create button should be disabled.
2. There should be a InputText for Title.
3. Next, there should be a horizontal RecyclerView to select from a list already created
instructors.
4. Then, you should be able to select a day from Monday to Friday using a spinner.
5. You need to put the time using two InputTexts with a Colon in between them. The
first InputText should only allow you to put a number between 1 to 12. For the
second InputText you should only be allowed to put a number between 0 to 59.
6. There should be a spinner to select between AM, and PM.
7. There should be two buttons: Reset, and Create.
8. Clicking on the Reset button should prompt you to reset all the fields.
9. Clicking on the Create button should save the course. 
