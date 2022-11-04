---
layout: page
title: User Guide
subtitle: Explore our guide to use Arrow
---

Welcome to **Arrow** User Guide! This user guide will give a descriptive instruction on how to use **Arrow**.
The _words_ _in_ _italic_ are terms you may be unfamiliar with. And if that is the case, 
do not hesitate checking them out in [Glossary](#glossary).

**Arrow** is a **desktop app** that **help software project managers keep track of their members’ tasks and deliverables**. 
By associating tasks to specific team members, users can **keep track of what needs to be done and have quick access to 
contact information should they wish to reach out to the member**. More specifically, apart from the _command box_, 
there are two main lists which store the tasks list and the contact list. In this way, our target users (software project manager)
can use two lists to organize the team project easily.

**Arrow** uses a _Command Line Interface_ (CLI)at the same time possessing convenience of a _Graphical User Interface_ (GUI). The main target audiences are software
project managers, which assumptively equipped with _Command Line Interface_ (CLI) skills. And if you can type fast,
**Arrow** can help you manage your team members in an exceptionally quick way.

### Note

<div markdown="block" class="alert alert-info">

**:information_source: If you are unfamiliar with Command Line Interface (CLI):**<br>

**Arrow** has the target audiences of software project team leads. 
In this circumstance, we assume users having some experience of using command line tools.<br>
However, it's perfectly fine if you do not know how to use CLI.<br>
Click [here](#tutorial-for-cli) to learn more about what is CLI.

</div>

--------------------------------------------------------------------------------------------------------------------

## Table of Contents

[**Glossary**](#glossary)<br>
[**Quick Start**](#quick-start)<br>
[**Address Book**](#address-book-commands)<br>
[**Task Management**](#task-management-commands)<br>
[**Notes for Commands**](#notes-for-commands)<br>
[**FAQ**](#faq)<br>
[**Command Summary**](#command-summary)<br>
[**Tutorial for CLI**](#tutorial-for-cli)

--------------------------------------------------------------------------------------------------------------------

## Glossary

* **CLI** : [Command Line Interface](https://en.wikipedia.org/wiki/Command-line_interface) is a text-based _user interface_ (UI) used to run programs, 
manage computer files and interact with the computer.<br> 
Click [here](#tutorial-for-cli) if you are not familiar with CLI.
* **User Interface** : The point at which human users interact with a computer, website or application.
* **GUI** : [Graphical User Interface](https://en.wikipedia.org/wiki/Graphical_user_interface) is a type of user interface
through which users interact with electronic devices via visual indicator representations.
* **Home Folder** : The folder/directory where Arrow stores all information.
* **Main Window** : The main window of the application that shows the list of students.
* **Command Box** : The text box where you can enter commands.
* **Extraneous Parameters** : The specified  command contains more positional parameters than is allowed.

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer. If you are unsure of how to check, refer to the [FAQ](#faq).

2. Download the latest `arrow.jar` from [here](https://github.com/AY2223S1-CS2103T-T08-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for Arrow.
4. Double-click the file to start the app. The _GUI_ or _main window_ should appear in a few seconds.
Note how the app contains some sample data. If the app does not start and an `error` appears on your screen, do refer to the [FAQ](#faq).<br>

5. The picture below shows the _main window_ of **Arrow**.
* The command box is boxed in <span style="color:red">*red*</span>, where you enter commands.
* The task panel list is boxed in <span style="color:blue">*blue*</span>, where you can see the current task list.
* The address book is boxed in <span style="color:green">*green*</span>, where you can see the contact information of your team members.
![Ui](images/Ui.png)

6. To start using **Arrow**, type the command in the _command box_ and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`task add`** `Task 1` : Adds a task titled `Task 1` to the Task Panel.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

7. Read the [Notes](#notes) to know more about the formats of commands, so that you can enter `commands` more easily.
8. Refer to [Address Book](#address-book-commands) for details of AddressBook commands and contact lists. 
9. Refer to [Task Management](#task-management-commands) for details of Task commands to arrange tasks quickly.

--------------------------------------------------------------------------------------------------------------------

## Notes for Commands

If you are new to **Arrow**, here provides some tips for you to enter commands efficiently.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* _Extraneous parameters_ for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

## Address Book Commands

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`


### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`


### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.


### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.


### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`


## Task Management Commands

### Adding a task: `task add`

Adds a task to the task panel.

Format: `task add TITLE by/DEADLINE [#PROJECT] [@PERSON_INDEX]...`

* `TITLE` refers to the task and **cannot be empty**.
* You can use English to describe the deadline (e.g. `today`, `tomorrow`, `next thursday`, `14 november`)
* If task does not have a deadline, you can put `by/?` and there will be no deadline.
* `PERSON_INDEX` refers to the index number shown in the displayed person list.

Examples:
* `task add Task 1 by/next friday #CS2103T @1`
* `task add Finish GUI by/sunday #CS2100 @2`


### Viewing tasks of a contact: `task list @`

Lists all the task(s) that have been assigned to the specified person.

Format: `task list @PERSON_INDEX`
* Shows the tasks of the specified `PERSON_INDEX`.
* The contact refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `task list @2` shows the incomplete tasks assigned to the 2nd person in the address book.
* `find Betsy` followed by `task list @1` shows the incomplete tasks assigned to the 1st person in the result of the `find` command.


### Marking tasks: `task mark`

Marks a task as complete.

Format: `task mark TASK_INDEX`
* Marks the task at the specified `TASK_INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `task list` followed by `task mark 1` marks the 1st task in the task list as complete.

### Unmarking tasks: `task unmark`

Unmarks a task and sets it as incomplete.

Format: `task unmark TASK_INDEX`
* Unmarks the task at the specified `TASK_INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `task list` followed by `task unmark 1` unmarks the 1st task in the task list and sets it as incomplete.



### Clearing all tasks from Task Panel: `task clear`

Clears the tasks from tasks list.

Format: `task clear`
* Clear the `task list` and create a new empty one.

Examples:
* `task list` followed by `task clear` clear the entire task list.


### Deleting tasks: `task delete`

Deletes the specified task from the task panel.

Format: `task delete TASK_INDEX`

* Deletes the task at the specified `TASK_INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `task list` followed by `task delete 2` deletes the 2nd task in the task panel.


### Setting deadlines to a task: `task do ... by/`

You can use the `task do ... by/` command to set (or remove) a deadline for some task.

Format: `task do TASK_INDEX [by/DATE]...`
- The `task do` command sets the deadline specified by `DATE` to the task at the specified `TASK_INDEX` from the task list.
- You can use plain English to describe the intended deadline, such as `today`, `tomorrow`, `next Thursday`, `14 November`, and so on.
- If the application is unable to determine a date from your input, an error message will be displayed, and you will be prompted to try a different input.
- To **remove** the deadline from a task, you can use the special character `?`.

##### Examples:
* `task do 1 by/tomorrow` sets the deadline for the 1st task in the list to tomorrow.
* `task do 1 by/?` **removes** the deadline from the 1st task in the list.

<div markdown="block" class="alert alert-info">
**:information_source: Additional Notes:**<br>
* As we foresee that most of your tasks should fall within the current year, the deadline of such tasks have their year omitted in the task panel.<br>
* You may refer to the screenshot below for a better understanding
</div>

![Task Deadline](images/deadline-ui.png)

### Sorting tasks by deadline: `task sort`

Sorts the task list by deadline.

Format: `task sort`


### Filtering the Tasks List

The `task list` command is a powerful command that allows you to focus only on the tasks that matter to you.

Format: `task list [KEYWORD] [#PROJECT_NAME]... [@PERSON_INDEX]... [before/ DATE] [after/ DATE] [-a] [-c]`

The format above may look daunting, but this is only because the `task list` command allows you to potentially describe complex queries.

However, all the parameters are optional, and in most cases, you will only need a simple query that uses just one or two parameters.

For ease of understanding, many of these common use cases have been detailed below.

#### 1. Filtering by Completion Status

`Format: task list [-a] [-c]`
- The simplest form of the command is simply `task list`. This allows you to view all incomplete tasks.
- You may find this command useful to reset your task list back to its default state.

##### Examples
- `task list -a` returns **all** tasks, regardless of completion status.
- `task list -c` returns all **completed** tasks only.

<div markdown="block" class="alert alert-info">
**:information_source: Additional Notes:**<br>
* By default, the command returns all incomplete tasks that match the query.<br>
* In the following examples, ‘all tasks’ refers to ‘all incomplete tasks’ for brevity.<br>
</div>

#### 2. Filtering by Description
`Format: task list KEYWORD`
- The `KEYWORD` parameter allows you to search for tasks that contain `KEYWORD`.

##### Example
- `task list fix` returns all tasks whose description contains the keyword `fix`.

#### 3. Filtering by Project
`Format: task list #PROJECT_NAME...`
- The `#` parameter allows you to search for tasks that are assigned to **any** of the project(s) you specify.

##### Example
- `task list #CS2101 #CS2103T` returns all tasks that are **either** under the project `CS2101` **or** `CS2103T`.

#### 4. Filtering by Assigned Contact(s)
`Format: task list @PERSON_INDEX...`
- The `@` parameter allows you to search for tasks that are assigned to **all** the contact(s) you specify.

##### Example
- `task list @1 @2` returns all tasks that are assigned to **both** the 1st and 2nd persons from the address book.

#### 5. Filtering by Deadline
`Format: task list before/ DATE after/ DATE`
- The `before/` and `after/` parameters allow you to specify a date range to filter the tasks by, according to their deadline.
- You can choose to use only one of the parameters, as shown in the examples below

##### Examples
- `task list before/ next Monday after/ tomorrow` returns all tasks whose deadline is after tomorrow but before next Monday.
- `task list before/ end of the month` returns all tasks whose deadline is before the end of the month.

<div markdown="span" class="alert alert-primary">:bulb: **Tips for Advanced Users:**
* You can use any combination of the parameters above to build a query that matches your unique requirements!
* Do note that the `KEYWORD` parameter **must** come first, if any other parameters are used.
</div>


### Assigning contacts to a task: `task assign`

Assigns or unassigns the specified persons from address book to a task from task panel.

Format: `task assign TASK_INDEX [+@PERSON_INDEX]…​ [+@PERSON_NAME]…​ [-@PERSON_INDEX]…​ [-@PERSON_NAME]…​`
* +@: Assigns the persons at the specified PERSON_INDEXs, or with the PERSON_NAME, from `address book` to task at the specified TASK_INDEX from `task list`.
* -@: Unassigns the persons at the specified PERSON_INDEXs, or with the PERSON_NAME, from `address book` from task at the specified TASK_INDEX from `task list`.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A task can have any number of assigned contacts (including 0)
</div>

Examples:
* `task assign 1 +@1 +@Alex Yeoh -@Bernice Yu` assigns the 1st person and "Alex Yeoh" from the address book to the 1st task from task list, and unassigns "Bernice Yu" from the same task.


### Editing a task: `task edit`

Edits an existing task in the task list.

Format: `task edit TASK_INDEX [ti/TITLE] [by/DEADLINE] [#/PROJECT]`

* Edits the person at the specified `TASK_INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.


### Listing all projects : `task project`

Lists all the projects present in the task list.

Format: `task project`


### Exiting the program : `exit`

Exits the program.

Format: `exit`


### Saving the data

Arrow data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


### Editing the data file

Arrow data are saved as a JSON file `[JAR file location]/data/arrow.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>


### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I check my Java version?<br>
**A**: Open Terminal if you're on Mac or Command Prompt if you're on Windows. Type "java -version" and press Enter.

**Q**: How do I install `java 11`?<br>
**A**: Download `java 11` to your computer via this [link](https://www.oracle.com/java/technologies/downloads/).

**Q**: If I already have java on my computer, but not `java 11`, how do I switch java version to `java 11`?<br>
**A**: For MacOS user, check this [tutorial](https://medium.com/@devkosal/switching-java-jdk-versions-on-macos-80bc868e686a).
For Windows user, check this [tutorial](https://www.happycoders.eu/java/how-to-switch-multiple-java-versions-windows/).

**Q**: What if the app does not open when I double-click it?<br>
**A**: Follow the steps listed to open the app.
1. Ensure that you have done Step 1 to 3 in the Quick Start.
2. Open Terminal app if you are on a Mac or the Command Prompt if you are on Windows.
3. Type "java -jar " into Terminal or Command Prompt (do not do anything else).
4. Go to the folder where you just copied `arrow.jar` to.
5. Drag `arrow.jar` and drop anywhere into Terminal or Command Prompt.
6. Press Enter and the command will run, the GUI will pop up.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command Summary

This summary provides a list of useful and straightforward instructions on how to use the command lines more efficiently.

| Action          | Format, Examples                                                                                                                                                      |
|-----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**         | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Task add**    | ``task add TITLE by/DEADLINE [#PROJECT] [@PERSON_INDEX]...``<br> e.g., `task add Task 1 by/next friday #CS2103T @1`                                                   |
| **Tasks**       | `tasks INDEX`<br> e.g., `tasks 2`                                                                                                                                     |
| **Task mark**   | `task mark TASK_INDEX`<br> e.g., `task mark 1`                                                                                                                        |
| **Task unmark** | `task unmark TASK_INDEX`<br> e.g., `task unmark 1`                                                                                                                    |
| **Task clear**  | `task clear`<br> e.g., `task clear`                                                                                                                                   |
| **Task delete** | `task delete TASK_INDEX`<br> e.g., `task delete 2`                                                                                                                    |
| **Task assign** | `task assign TASK_INDEX [+@/PERSON_INDEX]…​ [+@/PERSON_NAME]…​ [cd/PERSON_INDEX]…​ [-@/PERSON_NAME]…​`<br> e.g., `task assign 3 +@/1 +@/Alex Yeoh @-/2`               |
| **Clear**       | `clear`                                                                                                                                                               |
| **Delete**      | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **Edit**        | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Find**        | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            |
| **List**        | `list`                                                                                                                                                                |
| **Help**        | `help`                                                                                                                                                                |

--------------------------------------------------------------------------------------------------------------------

## Tutorial for CLI

A command-line interface (CLI) is a text-based user interface (UI) used to run programs, 
manage computer files and interact with the computer.

[This article](https://www.freecodecamp.org/news/command-line-commands-cli-tutorial/) showed you several “unknown-to-many” commands 
you can use to get access to hidden functionalities on your personal computer.

--------------------------------------------------------------------------------------------------------------------

## Behind Everything

### Why we are using two different lists?

* **Task Panel List**

    As software project team leaders, it is important to have a clear vision of tasks to be implemented,
since there could be many projects and each project may have so many items to keep in track.
Thus, **Arrow** has a task panel list to keep every task organized. 
Details of each task can be added, which helps the team managers no fear of leaving out any progress.


* **Contact List**

    The most important part of a software project is the team members serving at different positions,
**Arrow** helps tracking details of every team member and links them to their tasks to be finished.

--------------------------------------------------------------------------------------------------------------------

### This is the end of Arrow user guide, if you reach this far, thank you for your reading and hope you enjoying using our app :)
