
# Deliverable 2

## Feature Branch

Before starting this work, ensure that you have merged in your `f/deliverable01`
feature branch.

Now it's time to work on deliverable 2.

Make sure `master` has the latest and greatest code.
(Do not blindly follow these instructions, professor and TA not responsible for lost work)

```
git fetch
git checkout master
git reset --hard origin/master # <---- THIS WILL OVERWRITE LOCAL WORK
```

Ok, now the first contribute can create the branch using

```
git checkout -b f/deliverable02
git push -u origin HEAD
```

Other contributors will need to checkout that new branch

```
git fetch
git checkout f/deliverable02
```

Please consider using feature branches for individual work and using
pull requests back into `f/deliverable02` as a workflow to coordinate
between team members.  Make them SMALL, focused and merge them often.

## Initial requirements

The user of the app must be able to sign-in as admin.
The following features as outlined in the marking scheme should only be present for the admin user.

## Building Your APK

For more info: https://developer.android.com/studio/run/

* Go to `Build` -> `Build Bundle(s) / APK(s)` > `Build APK(s)`
* Android Studio saves the APKs you build in `project-name/module-name/build/outputs/apk/`
* Use the `_Debug` one for submission.

## Submission

You can install [Hub](https://github.com/github/hub)
a command line tool to create pull requests
from your terminal directly into GitHub.

For deliverable 2, create a Pull Request with
the following information

```
hub pull-request -o -b master -m "Deliverable 2"
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
| APK submitted and working (see notes below)<br>Make sure you test your APK. An invalid APK will receive 0. | 10 |
| 5 Unit test cases (simple local tests).<br>No need to include instrumentation or Espresso Tests (UI)<br>Test cases must be relevant to the features of deliverable 1 and 2. | 15 |
| Can add services. A service has a name and hourly rate.<br>Ex. Furniture assembly, 100$ hourly rate. | 20 |
| Can remove services no longer offered. | 20 |
| Can edit services.<br>Ex. Furniture assemble, 120$ hourly rate. | 15 |
| All fields are validated.<br>For instance, you should not be able to create<br>a service with no name. <br>This should be implemented along with valid error messages.<br>(-1 for each field in which the user input is not validated) | 10 |
| OPTIONAL – Integration with [CircleCI](https://circleci.com/) to see the automated builds and automated testing.<br>This will become mandatory for deliverable 4. | +15 |

## Notes

* Ensure your [README](/README.md) includes instructions to sign-in as an admin
* Categories and subcategories of services are optional. This can however help you organize
your screens (imaging listing 50 services in a single screen).
* Make sure your apk is correctly generated.
