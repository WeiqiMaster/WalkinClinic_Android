
# Deliverable 3

## Feature Branch

Before starting this work, ensure that you have merged in your `f/deliverable02`
feature branch.

Now it's time to work on deliverable 3.

Make sure `master` has the latest and greatest code.
(Do not blindly follow these instructions, professor and TA not responsible for lost work)

```
git fetch
git checkout master
git reset --hard origin/master # <---- THIS WILL OVERWRITE LOCAL WORK
```

Ok, now the first contribute can create the branch using

```
git checkout -b f/deliverable03
git push -u origin HEAD
```

Other contributors will need to checkout that new branch

```
git fetch
git checkout f/deliverable03
```

Please consider using feature branches for individual work and using
pull requests back into `f/deliverable03` as a workflow to coordinate
between team members.  Make them SMALL, focused and merge them often.

## Initial requirements

The user of the app must be able to sign-in as service provider.
The following features outlined below and in the marking scheme should only be present for the service provider.

### Managing Profile Information

The process of completing the service provider's profile information includes:

* Enter address (mandatory field) and phone number (mandatory field)
* Name of the company (mandatory field)
* General description (e.g. I have 20 years of experience fixing washer and dryers)
* Licensed (Yes/No).

The fields above need to be included, and `mandatory field` means that the user must specify a value.

You can include additional fields you find necessary.

### Managing Serivce Provider Availability

You are free to implement this feature as you want.

That is, with a calendar or by selecting predefined timeslots, by week, by day, etc.
The service provider should be able to create/edit/update/delete their availability,
as well as view their own availability.

For example:

```
Service provider is available during the following times:
- Monday 9h00-12h00
- Tuesday 9h00-14h00
- Friday 6h00-12h00
```

Usability is key for this feature!

Your marks for this feature will be split between

* Creating (20)
* Viewing (10)
* Editing (BONUS +10)

## Building Your APK

For more info: https://developer.android.com/studio/run/

* Go to `Build` -> `Build Bundle(s) / APK(s)` > `Build APK(s)`
* Android Studio saves the APKs you build in `project-name/module-name/build/outputs/apk/`
* Use the `_Debug` one for submission.

## Submission

You can install [Hub](https://github.com/github/hub)
a command line tool to create pull requests
from your terminal directly into GitHub.

For deliverable 3, create a Pull Request with
the following information

```
hub pull-request -o -b master -m "Deliverable 3"
```

Your PR description (and text file to submit to BrightSpace)
will look similar to the following:

```
Forward Inc.
Team Members
Andrew Forward, 1484511 
James Url | 1929204 
Ayana Nurse | 2128439

PR:
https://github.com/professor-forward/walkinclinic/pull/2

Last Commit:
https://github.com/professor-forward/walkinclinic/commit/564d6484cce8af2ae6c15891178b2b086a4cb9ff

Instructions

+ Additional instructions about this deliverable
```

You will upload this file to BrightSpace before the deadline.

## Marking Scheme

| Feature or Task | Weight (/100) |
| --- | --- |
| UML Class diagram of your domain model (-2 for each missing class)<br>(-2 for each missing class)<br>(-2 for incorrect generalization)<br>(-0.5 for each incorrect multiplicity)<br>(-0.5 for each missing attribute) | 10 |
| APK submitted and working (see notes below)<br>Make sure you test your APK. An invalid APK will receive 0. | 5 |
| 2 Unit test cases (simple local tests).<br>No need to include instrumentation or Espresso Tests (UI)<br>Test cases must be relevant to the features of deliverable 3. | 5 |
| Can complete profile information<br>Refer to `Managing Profile Information` above | 20 |
| Can add services to their profile<br>(From the list of services added by the admin,<br>the service provider can select one or more services.) | 10 |
| Can delete services from their profile | 10 |
| Can specify their own availabilities<br>Refer to `Managing Serivce Provider Availability` above | 20 |
| Can see the list of their own availability times.<br>Refer to `Managing Serivce Provider Availability` above  | 10 |
| All fields are validated.<br>For instance, you should not be able to select<br>a date in the past when creating a timeslot or entering an invalid address.<br>This should be implemented along with valid error messages.<br>(-1 for each field in which the user input is not validated) | 10 |
| OPTIONAL – Can edit the availabilities | +10 |

## Notes

* Ensure your [README](/README.md) includes instructions to sign-in as a service provider, and instructions on creating a service provider account from scatch.
* Make sure your apk is correctly generated.
