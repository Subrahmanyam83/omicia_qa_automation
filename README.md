# Installing Dependencies

### Gradle
Download from: [Gradle.org](http://gradle.org/gradle-download/)
Gradle does not require an actual install, just deploy the zip file to a convenient location.  Then add the proper lines of to your `.bashrc`:

```
export GRADLE_HOME=/Users/mfalcioni/Work/gradle-2.12
export PATH=$PATH:$GRADLE_HOME/bin
```

### Groovy
Download instructions: [Groovy-lang.org](http://groovy-lang.org/download.html). The sdk way is quite painless. 

### IntelliJ
Download the community edition: [IntelliJ](https://www.jetbrains.com/idea/#chooseYourEdition).  Before you can 
open or create a project, follow these instructions [Set up IntelliJ](http://stackoverflow.com/questions/31215452/intellij-idea-importing-gradle-project-getting-java-home-not-defined-yet).

### Drivers
#### Chrome
Chrome drivers are here [Chromedriver](http://chromedriver.storage.googleapis.com/index.html?path=2.14/)
#### Safari
Safari drivers are here [SafariDriver](https://github.com/SeleniumHQ/selenium/wiki/SafariDriver).
There are known issues that prevent tests of upload scenarios.

# Open this project in IntelliJ
Clone this project and open it in IntelliJ

# Run Tests
# Run Tests LOCALLY:
There are two ways of running test cases locally.
1. Through IDE: Right click on the test case or class or package -> Run
   For this the driver will be taken from the GebConfig driver parameter.
2. Through cmd: Follow tye steps to run particular test cases locally
    a. Edit the project.properties
    b. Open cmd -> Go to the project root directory and execute command: gradle <browser>Test
    Note: browser value could be either of these : chrome,firefox,safari,ie
    eg: to run all tests under smoke group, edit:
    group = smoke;
    package.name = Specs.*

NOTE: Please note we need to provide these details in project.properties to execute our tests.
    1. op.sys.name = UNIX 
            if running on UNIX or MAC Machines, blank for any other Operating Systems.
    2. geb.build.baseUrl = http://dev1.omicia-private.com
            if running on DEV Environment.
    3. browser = chrome
            if running the test cases on chrome browser.
            
# Run Tests in JENKINS:
1. Open the Job : http://jenkins.omicia-private.com/job/omicia_qa_automation-develop/
2. Build with parameters.
3. Edit all the parameters accordingly and Build.
4. Reports can be checked from JUNIT Report and EXTENT Reports Link on the Job Dashboard.