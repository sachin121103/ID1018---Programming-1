// NumberSequenceTest.java

/****************************************************************

NumberSequenceTest is a test program for the classes
ArrayNumberSequence and LinkedNumberSequence.

Author
Fadil Galjic

****************************************************************/

import java.util.Arrays;

import static java.lang.System.out;

class NumberSequenceTest
{
    public static void main (String[] args)
    {
		double[] realNumbers =
		    {8.0, 2.0, 16.0, 5.0, 1.0, 12.0, 4.0};
        NumberSequence sequence = null;
        // sequence = new ArrayNumberSequence(realNumbers);
        sequence = new LinkedNumberSequence(realNumbers);
        out.println("The sequence:");
        out.println(sequence);
        out.println();

        // add code here

        out.println("Adding elements: "); // Testing Add
        sequence.add(10.0);
        sequence.add(14.0);
        out.println(sequence); // Should be {8.0, 2.0, 16.0, 5.0, 1.0, 12.0, 4.0, 10,0, 14,0}
        out.println();

        out.println("Remove elements at position 2: "); // Testing removeAt
        sequence.removeAt(2);
        out.println(sequence); // Should be {8.0, 2.0, 5.0, 1.0, 12.0, 4.0, 10,0, 14,0}
        out.println();

        out.println("Inserting element at index 3: "); // Testing insert
        sequence.insert(3, 7.5);
        out.println(sequence); // Should be {8.0, 2.0, 5.0, 7.5, 1.0, 12.0, 4.0, 10,0, 14,0}
        out.println();

        out.println("Accessing element at index 4: "); // Testing numberAt
        out.println("The number at index 4 is " + sequence.numberAt(4)); // Should be 1.0
        out.println();

        out.println("Is the sequence increasing? " + sequence.isIncreasing()); // False
        out.println("Is the sequence decreasing? " + sequence.isDecreasing()); // False
        out.println(); // Testing isIncreasing and isDecreasing

        out.println("The length of the sequence is " + sequence.length()); // Testing length
        out.println(); // Should be 9

        out.println("The upper bound of the sequence is " + sequence.upperBound()); // Testing upper bound and should be 14
        out.println("The lower bound of the sequence is " + sequence.lowerBound()); // Testing lower bound and should be 1
        out.println();

        out.println("Returning the position of different numbers in the sequence: ");
        out.println("The number 5 is at position " + sequence.positionOf(5.0)); // Should be 2
        out.println("The number 41 is at position " + sequence.positionOf(41.0)); // Testing positionOf and should be -1
        out.println();

        out.println("Checking if the array contains certain values: "); // Testing contains
        out.println("Does the array contain 12: " + sequence.contains(12.0)); // Should be true
        out.println("Does the array contain 91.6: " + sequence.contains(91.6)); // Should be false
        out.println();

        double[] copied_arr = sequence.asArray(); // Testing asArray
        out.println(sequence);
        out.println(Arrays.toString(copied_arr));

        out.println("Testing edge cases:");

        try {
            sequence.numberAt(-1);
        } catch (IndexOutOfBoundsException e) {
            out.println("Caught exception: " + e.getMessage());
        }

        try {
            sequence.removeAt(10);
        } catch (IndexOutOfBoundsException e) {
            out.println("Caught exception: " + e.getMessage());
        }

        try {
            sequence.insert(20, 4.0);
        } catch (IndexOutOfBoundsException e) {
            out.println("Caught exception: " + e.getMessage());
        }

    }
}