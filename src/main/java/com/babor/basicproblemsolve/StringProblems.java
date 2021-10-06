package com.babor.basicproblemsolve;

/**
 *
 *
 * Problem
 *
 * Write a code that will print all the combinations of string from a given string and a given length?
 * (you can only write a method with two parameter and your solution should use recursive function)
 *
 * input
 * ABCD
 * 3
 *
 * output
 * A B C
 * A B D
 * A C D
 * B C D
 *
 * @author Shofiullah Babor
 * @email babu12f@gmail.com
 *
 */
public class StringProblems {
    public static void main( String[] args ) {
        printAllCombinationOfGivenLength("ABCD", 3);
    }

    public static void printAllCombinationOfGivenLength(String str, int len) {
        printCombine(str, len, "");
    }

    public static void printCombine(String str, int len, String baseString) {
        if (len>0 && str.isEmpty()) {
            return;
        }
        if (len>0) {
            for (int i=0; i<str.length(); i++) {
                String newBaseString = baseString + str.charAt(i);
                printCombine(str.substring(i+1), len-1, newBaseString);
            }
        }
        else {
            System.out.println(baseString);
            return;
        }
    }
}
