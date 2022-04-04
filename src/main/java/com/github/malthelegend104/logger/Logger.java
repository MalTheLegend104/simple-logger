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


    // Console Colors
    /**
     * Passed into a setColor method to set the output to black
     * @see Logger#setNormalColor
     */
    public static final byte CONSOLE_COLOR_BLACK = 0;
    /**
     * Passed into a setColor method to set the output to red
     * @see Logger#setNormalColor
     */
    public static final byte CONSOLE_COLOR_RED = 1;
    /**
     * Passed into a setColor method to set the output to green
     * @see Logger#setNormalColor
     */
    public static final byte CONSOLE_COLOR_GREEN = 2;
    /**
     * Passed into a setColor method to set the output to yellow
     * @see Logger#setNormalColor
     */
    public static final byte CONSOLE_COLOR_YELLOW = 3;
    /**
     * Passed into a setColor method to set the output to blue
     * @see Logger#setNormalColor
     */
    public static final byte CONSOLE_COLOR_BLUE = 4;
    /**
     * Passed into a setColor method to set the output to purple
     * @see Logger#setNormalColor
     */
    public static final byte CONSOLE_COLOR_PURPLE = 5;
    /**
     * Passed into a setColor method to set the output to cyan
     * @see Logger#setNormalColor
     */
    public static final byte CONSOLE_COLOR_CYAN = 6;
    /**
     * Passed into a setColor method to set the output to white
     * @see Logger#setNormalColor
     */
    public static final byte CONSOLE_COLOR_WHITE = 7;
    /**
     * Passed into a setColor method to set the output to bright black
     * @see Logger#setNormalColor
     */
    public static final byte CONSOLE_COLOR_BLACK_BRIGHT = 8;
    /**
     * Passed into a setColor method to set the output to bright red
     * @see Logger#setNormalColor
     */
    public static final byte CONSOLE_COLOR_RED_BRIGHT = 9;
    /**
     * Passed into a setColor method to set the output to bright green
     * @see Logger#setNormalColor
     */
    public static final byte CONSOLE_COLOR_GREEN_BRIGHT = 10;
    /**
     * Passed into a setColor method to set the output to bright yellow
     * @see Logger#setNormalColor
     */
    public static final byte CONSOLE_COLOR_YELLOW_BRIGHT = 11;
    /**
     * Passed into a setColor method to set the output to bright blue
     * @see Logger#setNormalColor
     */
    public static final byte CONSOLE_COLOR_BLUE_BRIGHT = 12;
    /**
     * Passed into a setColor method to set the output to bright purple
     * @see Logger#setNormalColor
     */
    public static final byte CONSOLE_COLOR_PURPLE_BRIGHT = 13;
    /**
     * Passed into a setColor method to set the output to bright cyan
     * @see Logger#setNormalColor
     */
    public static final byte CONSOLE_COLOR_CYAN_BRIGHT = 14;
    /**
     * Passed into a setColor method to set the output to bright white
     * @see Logger#setNormalColor
     */
    public static final byte CONSOLE_COLOR_WHITE_BRIGHT = 15;

    // Console colors
    private static final String CONSOLE_COLOR_RESET = "\u001B[0m";
    private static String currentWarn = "\u001B[0;93m"; // Default to yellow
    private static String currentNormal = "\033[0;97m";
    private static String currentError = "\033[0;91m";
    private static String currentInfo = "\033[0;96m";

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

    /**
     * Set the color for {@link Logger#warn}
     * @param warnColor {@code byte} corresponding to the wanted color.
     * @see Logger#CONSOLE_COLOR_YELLOW_BRIGHT
     */
    public static void setWarnColor(byte warnColor){
        if (warnColor == 0){
            currentWarn = "\u001B[30m"; // Black
        } else if (warnColor == 1){
            currentWarn = "\u001B[31m";
        } else if (warnColor == 2){
            currentWarn = "\u001B[32m";
        } else if (warnColor == 3){
            currentWarn = "\u001B[33m";
        } else if (warnColor == 4){
            currentWarn = "\u001B[34m";
        } else if (warnColor == 5){
            currentWarn = "\u001B[35m";
        } else if (warnColor == 6){
            currentWarn = "\u001B[36m";
        } else if (warnColor == 7){
            currentWarn = "\u001B[37m";
        } else if (warnColor == 8){
            currentWarn = "\033[0;90m";
        } else if (warnColor == 9){
            currentWarn = "\033[0;91m";
        } else if (warnColor == 10){
            currentWarn = "\033[0;92m";
        } else if (warnColor == 11){
            currentWarn = "\033[0;93m";
        } else if (warnColor == 12){
            currentWarn = "\033[0;94m";
        } else if (warnColor == 13){
            currentWarn = "\033[0;95m";
        } else if (warnColor == 14){
            currentWarn = "\033[0;96m";
        } else if (warnColor == 15){
            currentWarn = "\033[0;97m";
        } else {
            setWarnColor(CONSOLE_COLOR_YELLOW_BRIGHT);
        }
    }

    /**
     * Set the color for {@link Logger#log}
     * @param normalColor {@code byte} corresponding to the wanted color.
     * @see Logger#CONSOLE_COLOR_WHITE_BRIGHT
     */
    public static void setNormalColor(byte normalColor){
        if (normalColor == 0){
            currentNormal = "\u001B[30m"; // Black
        } else if (normalColor == 1){
            currentNormal = "\u001B[31m";
        } else if (normalColor == 2){
            currentNormal = "\u001B[32m";
        } else if (normalColor == 3){
            currentNormal = "\u001B[33m";
        } else if (normalColor == 4){
            currentNormal = "\u001B[34m";
        } else if (normalColor == 5){
            currentNormal = "\u001B[35m";
        } else if (normalColor == 6){
            currentNormal = "\u001B[36m";
        } else if (normalColor == 7){
            currentNormal = "\u001B[37m";
        } else if (normalColor == 8){
            currentNormal = "\033[0;90m";
        } else if (normalColor == 9){
            currentNormal = "\033[0;91m";
        } else if (normalColor == 10){
            currentNormal = "\033[0;92m";
        } else if (normalColor == 11){
            currentNormal = "\033[0;93m";
        } else if (normalColor == 12){
            currentNormal = "\033[0;94m";
        } else if (normalColor == 13){
            currentNormal = "\033[0;95m";
        } else if (normalColor == 14){
            currentNormal = "\033[0;96m";
        } else if (normalColor == 15){
            currentNormal = "\033[0;97m";
        } else {
            setNormalColor(CONSOLE_COLOR_WHITE_BRIGHT);
        }
    }

    /**
     * Set the color for {@link Logger#err}
     * @param errorColor {@code byte} corresponding to the wanted color.
     * @see Logger#CONSOLE_COLOR_RED_BRIGHT
     */
    public static void setErrorColor(byte errorColor){
        if (errorColor == 0){
            currentError = "\u001B[30m"; // Black
        } else if (errorColor == 1){
            currentError = "\u001B[31m";
        } else if (errorColor == 2){
            currentError = "\u001B[32m";
        } else if (errorColor == 3){
            currentError = "\u001B[33m";
        } else if (errorColor == 4){
            currentError = "\u001B[34m";
        } else if (errorColor == 5){
            currentError = "\u001B[35m";
        } else if (errorColor == 6){
            currentError = "\u001B[36m";
        } else if (errorColor == 7){
            currentError = "\u001B[37m";
        } else if (errorColor == 8){
            currentError = "\033[0;90m";
        } else if (errorColor == 9){
            currentError = "\033[0;91m";
        } else if (errorColor == 10){
            currentError = "\033[0;92m";
        } else if (errorColor == 11){
            currentError = "\033[0;93m";
        } else if (errorColor == 12){
            currentError = "\033[0;94m";
        } else if (errorColor == 13){
            currentError = "\033[0;95m";
        } else if (errorColor == 14){
            currentError = "\033[0;96m";
        } else if (errorColor == 15){
            currentError = "\033[0;97m";
        } else {
            setErrorColor(CONSOLE_COLOR_RED_BRIGHT);
        }
    }

    /**
     * Set the color for {@link Logger#warn}
     * @param infoColor {@code byte} corresponding to the wanted color.
     * @see Logger#CONSOLE_COLOR_CYAN_BRIGHT
     */
    public static void setInfoColor(byte infoColor){
        if (infoColor == 0){
            currentInfo = "\u001B[30m"; // Black
        } else if (infoColor == 1){
            currentInfo = "\u001B[31m";
        } else if (infoColor == 2){
            currentInfo = "\u001B[32m";
        } else if (infoColor == 3){
            currentInfo = "\u001B[33m";
        } else if (infoColor == 4){
            currentInfo = "\u001B[34m";
        } else if (infoColor == 5){
            currentInfo = "\u001B[35m";
        } else if (infoColor == 6){
            currentInfo = "\u001B[36m";
        } else if (infoColor == 7){
            currentInfo = "\u001B[37m";
        } else if (infoColor == 8){
            currentInfo = "\033[0;90m";
        } else if (infoColor == 9){
            currentInfo = "\033[0;91m";
        } else if (infoColor == 10){
            currentInfo = "\033[0;92m";
        } else if (infoColor == 11){
            currentInfo = "\033[0;93m";
        } else if (infoColor == 12){
            currentInfo = "\033[0;94m";
        } else if (infoColor == 13){
            currentInfo = "\033[0;95m";
        } else if (infoColor == 14){
            currentInfo = "\033[0;96m";
        } else if (infoColor == 15){
            currentInfo = "\033[0;97m";
        } else {
            setInfoColor(CONSOLE_COLOR_CYAN_BRIGHT);
        }
    }


    private static void setTimeFormat(){
        if (timeFormat == 0){
            dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        } else if (timeFormat == 1){
            dtf = DateTimeFormatter.ofPattern("hh:mm:ss a");
        } else if (timeFormat == 2){
            dtf = DateTimeFormatter.ofPattern("MM/dd hh:mm:ss");
        } else if (timeFormat == 3){
            dtf = DateTimeFormatter.ofPattern("MM/dd hh:mm:ss a");
        } else if (timeFormat == 4){
            dtf = DateTimeFormatter.ofPattern("dd/MM hh:mm:ss");
        } else if (timeFormat == 5){
            dtf = DateTimeFormatter.ofPattern("dd/MM hh:mm:ss a");
        } else if (timeFormat == 6){
            dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
        } else if (timeFormat == 7){
            dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss a");
        } else if (timeFormat == 8){
            dtf = DateTimeFormatter.ofPattern("yyyy/dd/MM hh:mm:ss");
        } else if (timeFormat == 9){
            dtf = DateTimeFormatter.ofPattern("yyyy/dd/MM hh:mm:ss a");
        } else if (timeFormat == 10){
            dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss");
        } else if (timeFormat == 11){
            dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        } else if (timeFormat == 12){
            dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        } else if (timeFormat == 13){
            dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
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
        System.out.println(currentNormal + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given object to console.
     * @param message {@code Object} that you want to output to console.
     */
    public static void log(Object message){
        System.out.println(currentNormal + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console.
     * @param message {@code int} that you want to output to console.
     */
    public static void log(int message){
        System.out.println(currentNormal + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console.
     * @param message {@code double} that you want to output to console.
     */
    public static void log(double message){
        System.out.println(currentNormal + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console.
     * @param message {@code char} that you want to output to console.
     */
    public static void log(char message){
        System.out.println(currentNormal + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console.
     * @param message {@code byte} that you want to output to console.
     */
    public static void log(byte message){
        System.out.println(currentNormal + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console.
     * @param message {@code short} that you want to output to console.
     */
    public static void log(short message){
        System.out.println(currentNormal + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console.
     * @param message {@code long} that you want to output to console.
     */
    public static void log(long message){
        System.out.println(currentNormal + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console.
     * @param message {@code float} that you want to output to console.
     */
    public static void log(float message){
        System.out.println(currentNormal + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console.
     * @param message {@code boolean} that you want to output to console.
     */
    public static void log(boolean message){
        System.out.println(currentNormal + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }

    // Logs for warnings
    /**
     * Log given String to console, using the set warning color.
     * @param message {@code String} that you want to output to console.
     */
    public static void warn(String message){
        System.out.println(currentWarn + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given object to console, using the set warning color.
     * @param message {@code Object} that you want to output to console.
     */
    public static void warn(Object message){
        System.out.println(currentWarn + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using the set warning color.
     * @param message {@code int} that you want to output to console.
     */
    public static void warn(int message){
        System.out.println(currentWarn + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using the set warning color.
     * @param message {@code double} that you want to output to console.
     */
    public static void warn(double message){
        System.out.println(currentWarn + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using the set warning color.
     * @param message {@code char} that you want to output to console.
     */
    public static void warn(char message){
        System.out.println(currentWarn + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using the set warning color.
     * @param message {@code byte} that you want to output to console.
     */
    public static void warn(byte message){
        System.out.println(currentWarn + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using the set warning color.
     * @param message {@code short} that you want to output to console.
     */
    public static void warn(short message){
        System.out.println(currentWarn + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using the set warning color.
     * @param message {@code long} that you want to output to console.
     */
    public static void warn(long message){
        System.out.println(currentWarn + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using the set warning color.
     * @param message {@code float} that you want to output to console.
     */
    public static void warn(float message){
        System.out.println(currentWarn + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using the set warning color.
     * @param message {@code boolean} that you want to output to console.
     */
    public static void warn(boolean message){
        System.out.println(currentWarn + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }

    // Logs for Info
    /**
     * Log given String to console, using the set info color.
     * @param message {@code String} that you want to output to console.
     */
    public static void info(String message){
        System.out.println(currentInfo + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given object to console, using the set info color.
     * @param message {@code Object} that you want to output to console.
     */
    public static void info(Object message){
        System.out.println(currentInfo + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using the set info color.
     * @param message {@code int} that you want to output to console.
     */
    public static void info(int message){
        System.out.println(currentInfo + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using the set info color.
     * @param message {@code double} that you want to output to console.
     */
    public static void info(double message){
        System.out.println(currentInfo + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using the set info color.
     * @param message {@code char} that you want to output to console.
     */
    public static void info(char message){
        System.out.println(currentInfo + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using the set info color.
     * @param message {@code byte} that you want to output to console.
     */
    public static void info(byte message){
        System.out.println(currentInfo + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using the set info color.
     * @param message {@code short} that you want to output to console.
     */
    public static void info(short message){
        System.out.println(currentInfo + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using the set info color.
     * @param message {@code long} that you want to output to console.
     */
    public static void info(long message){
        System.out.println(currentInfo + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using the set info color.
     * @param message {@code float} that you want to output to console.
     */
    public static void info(float message){
        System.out.println(currentInfo + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using the set info color.
     * @param message {@code boolean} that you want to output to console.
     */
    public static void info(boolean message){
        System.out.println(currentInfo + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }

    // Logs for error out.
    /**
     * Log given String to console, using stderr.
     * @param message {@code String} that you want to output to console.
     */
    public static void err(String message){
        System.err.println(currentError + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given object to console, using stderr.
     * @param message {@code Object} that you want to output to console.
     */
    public static void err(Object message){
        System.err.println(currentError + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using stderr.
     * @param message {@code int} that you want to output to console.
     */
    public static void err(int message){
        System.err.println(currentError + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using stderr.
     * @param message {@code double} that you want to output to console.
     */
    public static void err(double message){
        System.err.println(currentError + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using stderr.
     * @param message {@code char} that you want to output to console.
     */
    public static void err(char message){
        System.err.println(currentError + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using stderr.
     * @param message {@code byte} that you want to output to console.
     */
    public static void err(byte message){
        System.err.println(currentError + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using stderr.
     * @param message {@code short} that you want to output to console.
     */
    public static void err(short message){
        System.err.println(currentError + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using stderr.
     * @param message {@code long} that you want to output to console.
     */
    public static void err(long message){
        System.err.println(currentError + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using stderr.
     * @param message {@code float} that you want to output to console.
     */
    public static void err(float message){
        System.err.println(currentError + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
    }
    /**
     * Log given primitive to console, using stderr.
     * @param message {@code boolean} that you want to output to console.
     */
    public static void err(boolean message){
        System.err.println(currentError + '[' + getTime() + "]\t" + message + CONSOLE_COLOR_RESET);
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

                writer.write(
                        outputCapturer.getBuffer()
                                .replace(currentNormal, "")
                                .replace(currentInfo, "")
                                .replace(currentWarn, "")
                                .replace(CONSOLE_COLOR_RESET, "")
                );

                if (bothStreams){
                    writer.write("\r\n\r\n\r\n------Error Out------\r\n");
                    writer.write(errCapturer.getBuffer().replace(currentError, "").replace(CONSOLE_COLOR_RESET, ""));
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
