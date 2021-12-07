package com.NumberRangeSummarizer;

import java.util.*;

public class NumberSummarizerImpl implements NumberRangeSummarizer {

    public static void main(String[] args) {
        NumberSummarizerImpl num = new NumberSummarizerImpl();
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31"; // input to Summarize
        // Try catch statement to test if it can collect and Summarize the input.
        try {
            // Wow it works
            Collection<Integer> collectionInput = (List<Integer>)num.collect(input);
            String range = num.summarizeCollection(collectionInput);

            System.out.println(range);
        } catch (ClassCastException exc) {
            // Wow it doesn't
            System.out.println("Please review the string there might be a character");
        }
    }


    public Collection<Integer> collect(String input) {
        // Separate the Number with ","
        String[] arrayOfString = input.split(",");
        int[] integers  = new int[arrayOfString.length];

        for (int i = 0; i < arrayOfString.length; i++) {
            // Converting Array to Integer Array
            integers [i] = Integer.parseInt(arrayOfString[i]);
        }

        //Sorting Numbers in ascending order
        Arrays.sort(integers );
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < integers .length; i++) {
            //adds to numbers to list
            list.add(integers [i]);
        }
        return list;
    }


    /**
     * Add more comments
     */
    public String summarizeCollection(Collection<Integer> input) {
        int count = 0;
        // Holding the Range
        StringBuilder sb = new StringBuilder();

        //Collection class
        ArrayList<Integer> inputList = new ArrayList<Integer>(input);
        int length = inputList.size();

        int start;
        int next;

        for (int a = 0; a < length; a++) {
            // Caters for the last element of the list
            if (a == length - 1) {
                if (inputList.get(length-2) != inputList.get(length-1)) {
                    sb.append(inputList.get(a) + ",");
                }
                break;
            }

            //Start of First Element
            start = (Integer)inputList.get(a);
            next = (Integer)inputList.get(a + 1);
            if (next == start + 1) {
                count ++;
                int prevStartNum = start;
                //GENERALLY IF THERE IS A CONSISTENT RANGE IN THE LIST APPEND THE BEGINNING AND END OF IT ELSE BREAK FROM THE LOOP

                // Loop until the range breaks - where the loops breaks is your upper bound for the current range
                // Taking the beginning of the range and where itt ends
                for (int i = a + 1; ;i++) {
                    start = (Integer)inputList.get(i);
                    next = (Integer)inputList.get(i + 1);

                    if (next == start + 1) {
                        count++;
                    } else {
                        a = i;
                        if (count != 0) {
                            sb.append(prevStartNum + " - " + (Integer)inputList.get(i) + ", ");
                        }
                        break;
                    }
                }
                count = 0;
            }
            else {
                sb.append(start + ", ");
            }
        }
        //Concatenation
        String ranges = sb.toString();
        return ranges.substring(0, ranges.length() - 1);
    }
}
