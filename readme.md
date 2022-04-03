# Simple Logger
This is meant to be a general use, easy to use logger. 
This was mainly meant to just be used by me and a few fellow programmers, 
but this is a fairly lightweight logger that I wanted to make public.

>### Information
>This logger has no dependencies and was built using 
[Java 18](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html). <br>
This could very likely be implemented, without error, in older java versions, although nothing is guaranteed.

## Usage
Most common usages can be found here:
```java
public static void main(String[] args) {
        /*
         * General logging & creating log files.
         * You only need a logger object if you wish to log to a file.
         */
        // You can specify what way you want the time to appear in console.
        Logger.setDateTimeFormat(Logger.HOUR_MINUTE_SECOND_12H);


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
        // Logs to err, instead of out.
        Logger.err(i);
        }
        // This will only log System.err, and not System.out
        errorLogger.logConsole();


        /*
         * Message Panes
         */
        // Will show a JOptionPane to the user, detailing the message.
        Logger.messagePane("This is a message");
        // Same as above, although you can set the title.
        Logger.messagePane("This is a message", "This is a title.");

        // Same as messagePane, although this is for errors.
        Logger.errorPane("This is a message");
        // Same as above, although you can set the title.
        Logger.errorPane("This is a message", "This is a title.");


        // If you want to only log certain sections of code, you can do the following
        Logger example2 = new Logger("", false);
        example2.stopCapture(); // Capturing is started at object instantiation
        doSomething();
        example2.startCapture();
        doSomethingElse();
        example2.logConsole(); // Only things printed by doSomethingElse() will be logged, nothing from doSomething() will appear in the log.

        /*
         * There's a lot more to this logger than just these, check out the java docs, or source code, to see what else is possible.
         */
    }

```

## JavaDocs
