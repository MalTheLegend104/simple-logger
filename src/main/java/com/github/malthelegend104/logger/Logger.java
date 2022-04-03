/*
 * Copyright 2022 MalTheLegend104
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.malthelegend104.logger;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


/**
 *
 * This is meant to be a general use, easy to use logger.
 * This was mainly meant to just be used by me and a few fellow programmers,
 * but this is a fairly lightweight logger that I wanted to make public.
 *
 * @author MalTheLegend104 (<a href="https://github.com/MalTheLegend104">https://github.com/MalTheLegend104</a>)
 * @version 1.0
 * @since   1.0
 */
public class Logger {
    // Time format variables
    /**
     * Time in the format of [hh:mm:ss]
     */
    public static final byte HOUR_MINUTE_SECOND = 0;
    /**
     * Time in the format of [hh:mm:ss am/pm]
     */
    public static final byte HOUR_MINUTE_SECOND_12H = 1;
    /**
     * Time in the format of [MM:dd hh:mm:ss]
     */
    public static final byte MONTH_DAY_HOUR_MINUTE_SECOND = 2;
    /**
     * Time in the format of [MM:dd hh:mm:ss am/pm]
     */
    public static final byte MONTH_DAY_HOUR_MINUTE_SECOND_12H = 3;
    /**
     * Time in the format of [dd:MM hh:mm:ss]
     */
    public static final byte DAY_MONTH_HOUR_MINUTE_SECOND = 4;
    /**
     * Time in the format of [dd:MM hh:mm:ss am/pm]
     */
    public static final byte DAY_MONTH_HOUR_MINUTE_SECOND_12H = 5;
    /**
     * Time in the format of [yyyy:MM:dd hh:mm:ss]
     */
    public static final byte YEAR_MONTH_DAY_HOUR_MINUTE_SECOND = 6;
    /**
     * Time in the format of [yyyy:MM:dd hh:mm:ss am/pm]
     */
    public static final byte YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_12H = 7;
    /**
     * Time in the format of [yyyy:dd:MM hh:mm:ss]
     */
    public static final byte YEAR_DAY_MONTH_HOUR_MINUTE_SECOND = 8;
    /**
     * Time in the format of [yyyy:dd:MM hh:mm:ss am/pm]
     */
    public static final byte YEAR_DAY_MONTH_HOUR_MINUTE_SECOND_12H = 9;
    /**
     * Time in the format of [MM:dd:yyyy hh:mm:ss]
     */
    public static final byte MONTH_DAY_YEAR_HOUR_MINUTE_SECOND = 10;
    /**
     * Time in the format of [MM:dd:yyyy hh:mm:ss am/pm]
     */
    public static final byte MONTH_DAY_YEAR_HOUR_MINUTE_SECOND_12H = 11;
    /**
     * Time in the format of [dd:MM:yyyy hh:mm:ss]
     */
    public static final byte DAY_MONTH_YEAR_HOUR_MINUTE_SECOND = 12;
    /**
     * Time in the format of [dd:MM:yyyy hh:mm:ss am/pm]
     */
    public static final byte DAY_MONTH_YEAR_HOUR_MINUTE_SECOND_12H = 13;

    // Other final variables
    private static final JFrame messagePaneParent = new JFrame();

    // Instance variables
    private static byte timeFormat;
    private static DateTimeFormatter dtf;

    // Things that need to be properly initialized.
    static {
        messagePaneParent.setAlwaysOnTop(true);
        setTimeFormat(HOUR_MINUTE_SECOND);
    }


    //----------------------------------------
    //----------------------------------------
    // Setters and Getters
    //----------------------------------------
    //----------------------------------------
    /**
     * Set a custom date format.
     * @param dtf {@code DateTimeFormatter}, using your own custom format instead of a predefined one.
     */
    public static void setDateTimeFormat(DateTimeFormatter dtf) {
        Logger.dtf = dtf;
        Logger.timeFormat = -1;
    }

    /**
     * Set the time format, using predefined types.
     * @param timeFormat {@code byte} corresponding to the defined type.
     */
    public static void setDateTimeFormat(byte timeFormat) {
        Logger.timeFormat = timeFormat;
        setTimeFormat();
    }


    //----------------------------------------
    //----------------------------------------
    // Util Methods
    //----------------------------------------
    //----------------------------------------
    private static void setTimeFormat(byte timeFormat){
        setDateTimeFormat(timeFormat);
    }

    private static void setTimeFormat(){
        if (timeFormat == 0){
            dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        } else if (timeFormat == 1){
            dtf = DateTimeFormatter.ofPattern("hh:mm:ss aa");
        } else if (timeFormat == 2){
            dtf = DateTimeFormatter.ofPattern("MM/dd hh:mm:ss");
        } else if (timeFormat == 3){
            dtf = DateTimeFormatter.ofPattern("MM/dd hh:mm:ss aa");
        } else if (timeFormat == 4){
            dtf = DateTimeFormatter.ofPattern("dd/MM hh:mm:ss");
        } else if (timeFormat == 5){
            dtf = DateTimeFormatter.ofPattern("dd/MM hh:mm:ss aa");
        } else if (timeFormat == 6){
            dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
        } else if (timeFormat == 7){
            dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss aa");
        } else if (timeFormat == 8){
            dtf = DateTimeFormatter.ofPattern("yyyy/dd/MM hh:mm:ss");
        } else if (timeFormat == 9){
            dtf = DateTimeFormatter.ofPattern("yyyy/dd/MM hh:mm:ss aa");
        } else if (timeFormat == 10){
            dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss");
        } else if (timeFormat == 11){
            dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss aa");
        } else if (timeFormat == 12){
            dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        } else if (timeFormat == 13){
            dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss aa");
        }
    }

    private static String getTime(){
        return dtf.format(LocalDateTime.now());
    }

    //----------------------------------------
    //----------------------------------------
    // Static Logger Stuff
    //----------------------------------------
    //----------------------------------------
    /**
     * Log given String to console.
     * @param message {@code String} that you want to output to console.
     */
    public static void log(String message){
        System.out.println('[' + getTime() + "]\t" + message);
    }
    /**
     * Log given object to console.
     * @param message {@code Object} that you want to output to console.
     */
    public static void log(Object message){
        System.out.println('[' + getTime() + "]\t" + message);
    }

    /**
     * Log given primitive to console.
     * @param message {@code int} that you want to output to console.
     */
    public static void log(int message){
        System.out.println('[' + getTime() + "]\t" + message);
    }
    /**
     * Log given primitive to console.
     * @param message {@code double} that you want to output to console.
     */
    public static void log(double message){
        System.out.println('[' + getTime() + "]\t" + message);
    }
    /**
     * Log given primitive to console.
     * @param message {@code char} that you want to output to console.
     */
    public static void log(char message){
        System.out.println('[' + getTime() + "]\t" + message);
    }
    /**
     * Log given primitive to console.
     * @param message {@code byte} that you want to output to console.
     */
    public static void log(byte message){
        System.out.println('[' + getTime() + "]\t" + message);
    }
    /**
     * Log given primitive to console.
     * @param message {@code short} that you want to output to console.
     */
    public static void log(short message){
        System.out.println('[' + getTime() + "]\t" + message);
    }
    /**
     * Log given primitive to console.
     * @param message {@code long} that you want to output to console.
     */
    public static void log(long message){
        System.out.println('[' + getTime() + "]\t" + message);
    }
    /**
     * Log given primitive to console.
     * @param message {@code float} that you want to output to console.
     */
    public static void log(float message){
        System.out.println('[' + getTime() + "]\t" + message);
    }
    /**
     * Log given primitive to console.
     * @param message {@code boolean} that you want to output to console.
     */
    public static void log(boolean message){
        System.out.println('[' + getTime() + "]\t" + message);
    }


    // Logs for error out.
    /**
     * Log given String to console, using stderr.
     * @param message {@code String} that you want to output to console.
     */
    public static void err(String message){
        System.err.println('[' + getTime() + "]\t" + message);
    }
    /**
     * Log given object to console, using stderr.
     * @param message {@code Object} that you want to output to console.
     */
    public static void err(Object message){
        System.err.println('[' + getTime() + "]\t" + message);
    }
    /**
     * Log given primitive to console, using stderr.
     * @param message {@code int} that you want to output to console.
     */
    public static void err(int message){
        System.out.println('[' + getTime() + "]\t" + message);
    }
    /**
     * Log given primitive to console, using stderr.
     * @param message {@code double} that you want to output to console.
     */
    public static void err(double message){
        System.out.println('[' + getTime() + "]\t" + message);
    }
    /**
     * Log given primitive to console, using stderr.
     * @param message {@code char} that you want to output to console.
     */
    public static void err(char message){
        System.out.println('[' + getTime() + "]\t" + message);
    }
    /**
     * Log given primitive to console, using stderr.
     * @param message {@code byte} that you want to output to console.
     */
    public static void err(byte message){
        System.out.println('[' + getTime() + "]\t" + message);
    }
    /**
     * Log given primitive to console, using stderr.
     * @param message {@code short} that you want to output to console.
     */
    public static void err(short message){
        System.out.println('[' + getTime() + "]\t" + message);
    }
    /**
     * Log given primitive to console, using stderr.
     * @param message {@code long} that you want to output to console.
     */
    public static void err(long message){
        System.out.println('[' + getTime() + "]\t" + message);
    }
    /**
     * Log given primitive to console, using stderr.
     * @param message {@code float} that you want to output to console.
     */
    public static void err(float message){
        System.out.println('[' + getTime() + "]\t" + message);
    }
    /**
     * Log given primitive to console, using stderr.
     * @param message {@code boolean} that you want to output to console.
     */
    public static void err(boolean message){
        System.out.println('[' + getTime() + "]\t" + message);
    }




    /**
     * Create a message pane with the given message. The pane will always be on top, until closed.
     * @param message {@code String} containing the message you wish to display.
     */
    public static void messagePane(String message){
        JOptionPane.showMessageDialog(messagePaneParent, message);
    }

    /**
     * Create a message pane with the given message, with the given title. The pane will always be on top, until closed.
     * @param message {@code String} containing the message you wish to display.
     * @param title {@code String} containing what you wish the title to be.
     */
    public static void messagePane(String message, String title){
        JOptionPane.showMessageDialog(messagePaneParent, message, title, JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Displays an error message, containing the given message. The pane will always be on top.
     * @param message {@code String} containing the message you wish to display.
     */
    public static void errorPane(String message){
        JOptionPane.showMessageDialog(messagePaneParent, message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays an error message, containing the given message, with the given title. The pane will always be on top.
     * @param message {@code String} containing the message you wish to display.
     * @param title {@code String} containing what you wish the title to be.
     */
    public static void errorPane(String message, String title){
        JOptionPane.showMessageDialog(messagePaneParent, message, title, JOptionPane.ERROR_MESSAGE);
    }


    //----------------------------------------
    //----------------------------------------
    // Non Static Logger Stuff
    //----------------------------------------
    //----------------------------------------
    private final PrintStream out;
    private PrintStream err;
    private final boolean bothStreams;
    private PrintStreamOutputCapturer outputCapturer;
    private PrintStreamOutputCapturer errCapturer;
    private String dumpPath;
    private boolean continueCapture;

    /**
     * The base constructor for the logger. Defaults to logging {@link System#out} and {@link System#err}; ContinueCapture is set to true.
     * @param dumpPath {@code String} of the path where you want the files to be created.
     */
    public Logger(String dumpPath){
        this.out = System.out;
        this.err = System.err;
        this.dumpPath = dumpPath;
        this.continueCapture = true;
        bothStreams = true;
        createPrintStreamOutputCapturers();
    }

    /**
     * Defaults to logging {@link System#out} and {@link System#err}; allows you to set continueCapture.
     * @param dumpPath {@code String} of the path where you want the files to be created.
     * @param continueCapture {@code boolean} true if you wish to continue capturing to the same buffer when a
     * {@link Logger#stopCapture()} and a {@link Logger#startCapture()} are called. False if you wish to reset the buffer.
     */
    public Logger(String dumpPath, boolean continueCapture){
        this.out = System.out;
        this.err = System.err;
        this.dumpPath = dumpPath;
        this.continueCapture = continueCapture;
        bothStreams = true;
        createPrintStreamOutputCapturers();
    }

    /**
     * Allows you to only log one {@link PrintStream}.
     * @param dumpPath {@code String} of the path where you want the files to be created.
     * @param continueCapture {@code boolean} true if you wish to continue capturing to the same buffer when a
     * {@link Logger#stopCapture()} and a {@link Logger#startCapture()} are called. False if you wish to reset the buffer.
     * @param out {@code PrintStream} of whatever you wish to log. Ex.
     */
    public Logger(String dumpPath, boolean continueCapture, PrintStream out){
        this.out = out;
        this.dumpPath = dumpPath;
        bothStreams =false;
        this.continueCapture = continueCapture;
        createPrintStreamOutputCapturers();
    }

    /**
     * Allows you to log any two {@link PrintStream}'s.
     * @param dumpPath {@code String} of the path where you want the files to be created.
     * @param continueCapture {@code boolean} true if you wish to continue capturing to the same buffer when a
     * {@link Logger#stopCapture()} and a {@link Logger#startCapture()} are called. False if you wish to reset the buffer.
     * @param out {@code PrintStream} of whatever you wish to be considered the "standard" output (Ex {@link System#out})
     * @param err {@code PrintStream} of whatever you wish to be considered the "error" output (Ex {@link System#err})
     */
    public Logger(String dumpPath, boolean continueCapture, PrintStream out, PrintStream err){
        this.out = out;
        this.err = err;
        this.dumpPath = dumpPath;
        bothStreams = true;
        this.continueCapture = continueCapture;
        createPrintStreamOutputCapturers();
    }

    /**
     * Set the bool continue capture.
     * @param continueCapture {@code boolean}
     * @see Logger#Logger(String dumpPath, boolean continueCapture)
     */
    public void setContinueCapture(boolean continueCapture){
        this.continueCapture = continueCapture;
    }

    /**
     * Sets the path for log files.
     * @param path {@code String} path for the files, must be a directory.
     */
    public void setDumpPath(String path){
        this.dumpPath = path;
    }

    /**
     * Returns a string telling you what is and isn't being captured.
     * @return {@code String} containing information about what is being captured.
     */
    public String whatIsCapturing(){
        String result = "";
        if (outputCapturer.isCapturing()) result += "Output is Capturing.\r\n";
        if (bothStreams){
            if (errCapturer.isCapturing()) result += "Error is Capturing.\r\n";
        }
        return result;
    }

    /**
     * Tells you whether streams are being captured or not. Run {@link Logger#whatIsCapturing()} to see what is being captured.
     * @return {@code byte} 0 if nothing is capturing. 1 if something is capturing. 2 if both are being captured.
     */
    public byte isCapturing(){
        byte value = 0;
        if (outputCapturer.isCapturing()) value++;
        if (bothStreams){
            if (errCapturer.isCapturing()) value++;
        }
        return value;
    }

    // Creates the Capturers
    private void createPrintStreamOutputCapturers(){
        outputCapturer = new PrintStreamOutputCapturer(out, continueCapture, (byte) 0);
        outputCapturer.start();
        if (bothStreams){
            errCapturer = new PrintStreamOutputCapturer(err, continueCapture, (byte) 1);
            errCapturer.start();
        }
    }

    // Creates the log file with the given name
    private void createLogFile(String fileName){
        // Verify dumpPath is a dir
        if (Files.isDirectory(Path.of(dumpPath))){
            File file = new File(dumpPath + fileName);
            try {
                if (!file.createNewFile()){
                    Logger.log("File with the same time & date already exist.");
                    return;
                }
                FileWriter writer = new FileWriter(file);
                writer.write("------Standard Out------\r\n");
                writer.write(outputCapturer.getBuffer());
                if (bothStreams){
                    writer.write("\r\n\r\n\r\n------Error Out------\r\n");
                    writer.write(errCapturer.getBuffer());
                }
                writer.close();
            } catch (IOException e) {
                Logger.log("Failed to create the file.");
                e.printStackTrace();
            }
        } else {
            Logger.log("Dump Path is not a directory.");
        }
    }

    /**
     * Create a dump file, used for debugging. Should only be used in the case of a fatal error.
     */
    public void dumpConsole(){
        if (Files.notExists(Path.of(dumpPath))){
            Logger.log("Cannot create dump. Path is invalid or nonexistent");
        }
        String fileName = DateTimeFormatter.ofPattern("yyyy_MM_dd HH_mm_ss").format(LocalDateTime.now()) + ".txt";
        createLogFile(fileName);
        errorPane("A fatal error has occurred. You can read the file dump here:\r\n" + dumpPath + fileName);
    }

    /**
     * Log Console to a file.
     */
    public void logConsole(){
        if (Files.notExists(Path.of(dumpPath))){
            Logger.log("Cannot create dump. Path is invalid or nonexistent");
        }
        String fileName = DateTimeFormatter.ofPattern("yyyy_MM_dd HH_mm_ss").format(LocalDateTime.now()) + ".txt";
        createLogFile(fileName);
    }

    /**
     * Stops the capture for any streams that are being captured.
     */
    public void stopCapture(){
        outputCapturer.stop();
        if (bothStreams) errCapturer.stop();
    }

    /**
     * Starts the capture for any streams that are being captured.
     */
    public void startCapture(){
        outputCapturer.start();
        if (bothStreams) errCapturer.start();
    }


    //----------------------------------------
    //----------------------------------------
    // Private Classes
    //----------------------------------------
    //----------------------------------------
    /**
     * Creates a capturer that allows it to print to two output streams at once.
     * This mainly interfaces with {@link OutputStreamCombiner}.
     */
    private static class PrintStreamOutputCapturer {
        private ByteArrayOutputStream baos;
        private PrintStream previous;
        private boolean capturing;
        private boolean continueCapture;
        private final byte type;

        public PrintStreamOutputCapturer(PrintStream printStream, boolean continueCapture, byte type){
            this.previous = printStream;
            this.continueCapture = continueCapture;
            this.baos = new ByteArrayOutputStream();
            this.type = type;
        }

        public void start() {
            if (capturing) {
                Logger.err("Capturing is already active.");
                return;
            }

            capturing = true;
            if (!continueCapture){
                baos = new ByteArrayOutputStream();
            }

            OutputStream outputStreamCombiner = new OutputStreamCombiner(Arrays.asList(previous, baos));
            PrintStream custom = new PrintStream(outputStreamCombiner);

            if (type == 0) System.setOut(custom);
            if (type == 1) System.setErr(custom);
        }

        public void stop() {
            if (!capturing) {
                return;
            }
            if (type == 0) System.setOut(previous);
            if (type == 1) System.setErr(previous);

            if (!continueCapture){
                baos = null;
            }

            previous = null;
            capturing = false;
        }

        public void setContinueCapture(boolean continueCapture){
            this.continueCapture = continueCapture;
        }

        public boolean isCapturing() {
            return capturing;
        }

        public String getBuffer(){
            return baos.toString();
        }
    }

    /**
     * Combines {@link OutputStream}'s allowing as many outputs for one output as you wish.
     */
    private static class OutputStreamCombiner extends OutputStream {
        private final List<OutputStream> outputStreams;

        public OutputStreamCombiner(List<OutputStream> outputStreams) {
            this.outputStreams = outputStreams;
        }

        public void write(int b) throws IOException {
            for (OutputStream os : outputStreams) {
                os.write(b);
            }
        }

        public void flush() throws IOException {
            for (OutputStream os : outputStreams) {
                os.flush();
            }
        }

        public void close() throws IOException {
            for (OutputStream os : outputStreams) {
                os.close();
            }
        }
    }
}
