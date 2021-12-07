# Task Master
Task Master is an android application for android users to manage their tasks. 

--------
## Lab26:
* At the home page when user click on Add Tasks button will navigate him to Add Task page to add titel and description of the task.
* when the user Click on submit button in Add Task page will showed him label "Submitted!" and the Total tasks will increased by one.  
* At the home page when user click on All Tasks button will navigate him to All Task page.


## The pages

* Home page

![Homepage](screenshots/Homepage.png)


* The Add Task

![AddTask](screenshots/addTask.png)


* The All Tasks

![AllTasks](screenshots/AllTasks.png)

------
## Lab27:
* At Task Detail page have changable title and lourm ipsom as description.
* Update the home page to contain three buttons each button will display different title in Task Detail page. Also there is Setting button which navigate you to Setting page.
* At Setting page allow users to enter their username and hit save. When they go back to home page will see the username  above the three task buttons.
## The pages

* Setting page

![Setting](screenshots/Setting.png)

* Home page showed the username that saved in setting page

![Homepage](screenshots/Homepage2.png)


* TaskDetails page when click button1 from home page

![TaskDetails1](screenshots/task1.png)


* TaskDetails page when click button2 from home page

![TaskDetails2](screenshots/task2.png)

* TaskDetails page when click button3 from home page

![TaskDetails3](screenshots/task3.png)

----------

## Lab28:
* Add the Task model class which contain the title, body and state.
* Update the home page to contain  Recycle View.
* when the user click on each task at the Recycle View that is appear on home page will navigate him to Task Detail page. 
* At Task Detail page have changable title and body based on which task clicked.

## The pages
*  Home page update

![Homepage](screenshots/HomePage3.png)


* TaskDetails page when click on task one from home page.

![TaskDetails1](screenshots/1.png)


* TaskDetails page when click on task two from home page.

![TaskDetails2](screenshots/2.png)

* TaskDetails page when click on task three from home page.

![TaskDetails3](screenshots/3.png)

* TaskDetails page when click on task four from home page.

![TaskDetails3](screenshots/4.png)


## Lab29:
* Modify on Add Task page to save the data entered in as a Task in the local database.
* Add the Task model class which contain the title, body and state.
* Update the home page to display  all Task entities in Recycle View.
* when the user click on each task at the Recycle View that is appear on home page will navigate him to Task Detail page. 
* At Task Detail page displayed the title,description and status of a tapped task.

## The pages
*  Home page update

![Homepage](screenshots/UpdateHomePage.png)

* Add task page , Add First task
![AddTasksPage](screenshots/AddTasksPage.png)

* TaskDetails page when click on First task from home page.

![TaskDetailsFirstTask](screenshots/TaskDetailsFirstTask.png)


* TaskDetails page when click on Task two from home page.

![TaskDetailsTaskTwo](screenshots/TaskDetailsTaskTwo.png)

* TaskDetails page when click on Task three from home page.

![TaskDetailsTaskThree](screenshots/TaskDetailsTaskThree.png)

## Lab: 32 - Amplify and DynamoDB
* Using the `amplify add api command`, create a Task resource that replicates our existing Task schema.
* Update all references to the Task data to instead use AWS Amplify to access your data in DynamoDB instead of in Room.
* Modify Add Task Page - When add the Task and Click Add Task button will save the data entered in as a Task to DynamoDB.
* Refactor Home Page RecyclerView to display all Task entities in DynamoDB.

* Home Page after add four tasks
![HomePage](screenshots/HomePage_32.png)

* Add Task Page - here can entered the tasks and will added to data base.
![AddTask](screenshots/AddTask_32.png)

* Task Details page - when users clicked on any task fom RecyclerView (At home page) it will display in this page
![TaskDetails](screenshots/TaskDetails_32.png)


## Lab33
* Create a second Model Called a Team, Create relation between Task and Team (Update your tasks to be owned by a team).
* create three teams  Manually.
* Modify the add Task Page to include Radio Buttons for which team that task belongs to.
* Modify Settings page to allow the user to choose their team
* display only that team’s tasks on the homepage.

* Add Task Page - When user add the task have to choose on of the three team's which team that task will be belongs to.
![Add Task For Team 1](screenshots/AddTaskForTeam1.png)

* Setting Page - user can add user name and select their team .
 ![Setting Page For Selected Team](screenshots/SettingPageForSelectedTeam.png)

* Home Page - will display the team name that the user selected from setting and display the tasks belongs to it.
![Home Page](screenshots/HomePageTeam1.png)


## Lab36 -
* Add Cognito to Amplify setup. Add in user login and sign up flows to the application.
* Display the logged in user’s username in Home Page.
* Allow users to log out of your application.

* At home page - have Login button directed you to sign up and login flow 
![Homesignin](screenshots/Homesignin.png)

![Homesignin](screenshots/Signin.png)

* after sign in the button at home page converted to sign out to allow users to log out of application.
![Homesignin](screenshots/SignOut.png)

# Lab 37 - S3 Uploads
* On Add Task activity, allow users to optionally select a file to attach to that task. 
* When a user attaches a file to a task, that file should be uploaded to S3, and associated with that task.

* Update Add Task page 
![AddTask](screenshots/AddTask37.png)
* When click on upload file button will navigate you to select file:
![uploadFile](screenshots/uploadFile.png)

* Home page that consist the tasks, when you cklick any task will navigate you to /task details page.
![uploadFile](screenshots/HPTeam2.png)
![uploadFile](screenshots/HPTeam3.png)
* the uploded file displayed in task details for specific task.
![uploadFile](screenshots/TDpage.png)

# Lab: 41 - Intent Filters
Add an intent filter to your application such that a user can hit the “share” button on an image in another application,
choose TaskMaster as the app to share that image with, and be taken directly to the Add a Task activity with that image pre-selected.

![shareImg](screenshots/AddTask412.png)

* Add Task page

![addTaskPage](screenshots/AddTask411.png)


# Lab: 42 - Location
* Update Task schema to be included with location.
* When the user adds a task, their location will be retrieved and included in the Task details page.

* Task Details page

![taskdetail](screenshots/Location.png)












































