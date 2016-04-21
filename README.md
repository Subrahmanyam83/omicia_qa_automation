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
