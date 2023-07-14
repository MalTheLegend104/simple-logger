# Simple Logger
This is meant to be a general use, easy to use logger.
This was mainly meant to just be used by me and a few fellow programmers,
but this is a fairly lightweight logger that I wanted to make public.

>### Information
>This logger has no dependencies and was built using
[Java 18](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html). <br>
This could very likely be implemented, without error, in older java versions, although nothing is guaranteed.

## Include
- Simply copy `Logger.java` into your project.  
OR
- Add the following to your dependencies in `pom.xml`  
    > This is just in theory. I never really bothered to properly set this up, and I think that any end user needs to configure maven to actually get the package from github.
    > just do it the other way, it's simpler anyway.
```xml
<dependency>
  <groupId>com.github.malthelegend104</groupId>
  <artifactId>logger</artifactId>
  <version>1.0</version>
</dependency> 
```  


## Usage
Methods and constructors not detailed below can be found in the [javadocs.](#javadocs)

Most common usages can be found here:
```java
public static void main(String[] args) {
        /*
         * General logging & creating log files.
         * You only need a logger object if you wish to log to a file.
         */
        // You can specify what way you want the time to appear in console.
        Logger.setDateTimeFormat(Logger.HOUR_MINUTE_SECOND_12H);

        // All 4 types available to log
        Logger.warn("this is a warning");
        Logger.info("this is an info message");
        Logger.log("this is a message");
        Logger.err("this is an error");
        
        // You can set the colors for each individually.
        Logger.setWarnColor(Logger.CONSOLE_COLOR_BLACK_BRIGHT);     // Default is Bright Yellow
        Logger.setInfoColor(Logger.CONSOLE_COLOR_BLUE);             // Default is Bright Cyan
        Logger.setNormalColor(Logger.CONSOLE_COLOR_GREEN_BRIGHT);   // Default is Bright White
        Logger.setErrorColor(Logger.CONSOLE_COLOR_PURPLE_BRIGHT);   // Default is Bright Red

        // Leaving this blank will result in the log file being created in the same folder as the jar.
        // See javadocs for other constructors.
        Logger exampleLogger = new Logger("");
        for (int i = 0; i < 100; i++) {
            // Logs to normal out.
            Logger.log(i);
        }

        // This will log everything in console until now.
        exampleLogger.logConsole();

        for (int i = 0; i < 100; i++) {
            // Logs to err, instead of out.
            Logger.err(i);
        }

        // Logs console, and alerts the user that an error has occurred.
        exampleLogger.dumpConsole();


        /*
         * Log only one PrintStream
         */
        // This is an example for only logging error output.
        // You can replace System.err with any PrintStream you wish to log.
        Logger errorLogger = new Logger("", true, System.err);
        for (int i = 0; i < 100; i++) {
            Logger.err(i);
        }
        errorLogger.logConsole();


        /*
         * Message Panes
         */
        // Will show a JOptionPane to the user, displaying the message.
        Logger.messagePane("This is a message");
        // Same as above, although you can set the title.
        Logger.messagePane("This is a message", "This is a title.");

        // This can be done with error messages too.
        Logger.errorPane("This is a message");
        Logger.errorPane("This is a message", "This is a title.");


        /*
         * Log only certain sections of code.
         */
        Logger example2 = new Logger("", false);
        example2.stopCapture(); // Capturing is started at object instantiation
        doSomething();
        example2.startCapture();
        doSomethingElse();
        // Only things printed by doSomethingElse() will be logged.
        // Nothing from doSomething() will appear in the log.
        example2.logConsole();
         

        /*
         * There's a lot more to this logger than just these.
         * Check out the java docs, or source code, to see what else is possible.
         */
    }

```

## Example Output
Example of all logger types:  
![logger types](https://raw.githubusercontent.com/MalTheLegend104/simple-logger/main/readme-assets/log_types.png)

Example of a log file:  
![log file](https://raw.githubusercontent.com/MalTheLegend104/simple-logger/main/readme-assets/logger-logconsole.jpg)  

Example of `Logger.errorPane()`:  
![logger error pane](https://raw.githubusercontent.com/MalTheLegend104/simple-logger/main/readme-assets/logger-errorpane.jpg)  

## JavaDocs
The javadocs can be downloaded in this projects [releases.](https://github.com/MalTheLegend104/simple-logger/releases/latest)
> Every single method and field that is usable is documented.  
> If you wish to add to this project, please do me a favor and add javadocs for anything you implement.
