// ArrayNumberSequence.java

/****************************************************************

ArrayNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses an array to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class ArrayNumberSequence implements NumberSequence
{
	// numbers in the sequence
    private double[] numbers;

    // create the sequence
    public ArrayNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

		this.numbers = new double[numbers.length];
		for (int i = 0; i < numbers.length; i++)
		    this.numbers[i] = numbers[i];
	}

    // toString returns the character string representing this
    // sequence
	public String toString ()
	{
		String s = "";
		for (int i = 0; i < numbers.length - 1; i++)
		    s = s + numbers[i] + ", ";
		s = s + numbers[numbers.length - 1];

		return s;
	}

    // add code here

	@Override
	public int length() {
		return numbers.length; // Straightforward implementation by returning the length of the array.
	}

	public double upperBound() {
		double max = numbers[0];
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] > max){
				max = numbers[i];
			}
		}

		return max;

		// Initialise max to the first value of the array. Looping through the array, if any value is greater than max, then it becomes max. We then return max.
	}

	public double lowerBound() {
		double min = numbers[0];
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] < min){
				min = numbers[i];
			}
		}

		return min;
		// Same as upperBound() except we are getting the lowest value of the array instead, and we return that.
	}

	public double numberAt(int position) throws IndexOutOfBoundsException {
		if (position < 0 || position >= numbers.length){
			throw new IndexOutOfBoundsException("The position is out of bounds");
		}

		return numbers[position];

		// We return the value at the specified position. If the position is not on the array, then we throw the exception.
	}

	public int positionOf(double number) {
		// Since indexOf wasn't working for some reason, I'm just going to search and return the first occurrence of the number.

		for (int i = 0; i < numbers.length; i++) {
			int comparison = Double.compare(numbers[i], number);
			if (comparison == 0){
				return i;
			}
		}
		return -1;

		// Here, we are comparing the number we are sorting through the array with the number to check. If it exists, we return i, the position. Else, -1.
	}

	public boolean contains(double number) {
        return positionOf(number) != -1;
		// Here, if the positionOf returns -1, it means that the value is not in the array. Therefore, if the method returns -1, we return false. Else, we return true.
    }

	@Override
	public void add(double number) {
		double[] new_arr = new double[numbers.length+1];

		for (int i = 0; i < numbers.length; i++) {
			new_arr[i] = numbers[i];
		}

		new_arr[new_arr.length-1] = number;

		numbers = new_arr;

		// We create a new array that is longer by 1. We then copy the old array into the new array and then add the number at the end. Numbers is equated to this array.
	}

	@Override
	public double[] asArray() {
		double[] new_arr = new double[numbers.length];

		for (int i = 0; i < numbers.length; i++) {
			new_arr[i] = numbers[i];
		}

		return new_arr;

		// We create a new copy array that is returned
	}

	@Override
	public void insert(int position, double number) throws IndexOutOfBoundsException {
		if (position < 0 || position >= numbers.length){
			throw new IndexOutOfBoundsException("The position is not in the array");
		}

		double[] new_arr = new double[numbers.length+1];
		int j = 0;

		for (int i = 0; i < new_arr.length ; i++) {
			if (i == position){
				new_arr[i] = number;
				continue;
			}
			else {
				new_arr[i] = numbers[j];
				j++;
			}
		}
		
		numbers = new_arr;

		// It works the same way as add, but instead of inserting a number at the end, it inserts it as a given position.
		// The two counters allow us to track the length correctly given there are 2 arrays.
	}

	@Override
	public void removeAt(int position) throws IndexOutOfBoundsException, IllegalStateException {
		if (numbers.length <= 2){
			throw new IllegalStateException("There are 2 or lesser elements in the array.");
		}

		if (position < 0 || position >= numbers.length){
			throw new IndexOutOfBoundsException("The position is not in the array");
		}

		double[] removed_arr = new double[numbers.length-1];
		int j = 0;

		for (int i = 0; i < numbers.length; i++) {
			if (i == position){
				continue;
			}
			removed_arr[j] = numbers[i];
			j++;
		}

		numbers = removed_arr;

		// Here, we create a new array and copy every element but the one we need.
		// j acts as the second counter.

	}

	@Override
	public boolean isDecreasing() {
		for (int i = 0; i < numbers.length-1; i++) {
			if (numbers[i+1] >= numbers[i]){
				return false;
			}
		}
		return true;

		// We check if the next number (i+1) is greater than the present one (i) and return false if that happens.
		// If it doesn't, then we return true.
	}

	@Override
	public boolean isIncreasing() {
		for (int i = 0; i < numbers.length-1; i++) {
			if (numbers[i+1] <= numbers[i]){
				return false;
			}
		}
		return true;
	}
	// We check if the next number (i+1) is lesser than the present one (i) and return false if that happens.
	// If it doesn't, then we return true.
}