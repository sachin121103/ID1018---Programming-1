// LinkedNumberSequence.java

/****************************************************************

LinkedNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses linked nodes to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class LinkedNumberSequence implements NumberSequence
{
	private class Node
	{
		public double number;
		public Node next;

		public Node (double number)
		{
			this.number = number;
			next = null;

		}
	}


	// the first node in the node-sequence
    private Node first;

    // create the sequence
    public LinkedNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

        first = new Node(numbers[ 0]);
        Node n = first;
		for (int i = 1; i < numbers.length; i++)
		{
			n.next = new Node(numbers[i]);
			n = n.next;
		}
	}

    // toString returns the character string representing this
    // sequence
	public String toString ()
	{
		String s = "";
		Node n = first;
		while (n.next != null)
		{
		    s = s + n.number + ", ";
		    n = n.next;
		}
		s = s + n.number;

		return s;
	}

    // add code here


	@Override
	public int length() { // Here, we initialise a counter that increases every time we jump nodes until the node is null.
		int length = 0;
		Node node = first;

		while (node != null){
			length++;
			node = node.next;
		}

		return length;
	}

	@Override
	public double upperBound() { // We store the value of the first node in max, and then if any subsequent values are greater than or equal to max, it becomes max.
		Node current = first;
		double max = first.number;

		while (current != null) {
			if (current.number >= max){
				max = current.number;
			}
			current = current.next;
		}

		return max;
	}

	@Override
	public double lowerBound() { // We store the value of the first node in min, and then if any subsequent values are lesser than or equal to max, it becomes min.
		Node current = first;
		double min = first.number;

		while (current != null) {
			if (current.number <= min){
				min = current.number;
			}
			current = current.next;
		}

		return min;
	}

	@Override
	public double numberAt(int position) throws IndexOutOfBoundsException { // We iterate all the way to the position in the node and return its value
		if (position < 0 || position > length()){
			throw new IndexOutOfBoundsException("Position is out of scope of the linked list");
		}

		Node current = first;

		for (int i = 0; i < position; i++) {
			current = current.next;
		}

		return current.number;
	}

	@Override
	public int positionOf(double number) { // We return the index value when the node's value is equal to the value we are checking for.
		Node current = first;
		int i = 0;

		while (current != null){
			if (current.number == number){
				return i;
			}
			current = current.next;
			i++;
		}

		return -1;
	}

	@Override
	public boolean isIncreasing() {
		Node current = first;

		if (first == null){
			return true;
		}

		while (current.next != null){
			if (current.next.number <= current.number){
				return false;
			}
			current = current.next;
		}
		return true;
	}

	@Override
	public boolean isDecreasing() {
		Node current = first;

		if (first == null){
			return true;
		}

		while (current.next != null){
			if (current.next.number >= current.number){
				return false;
			}
			current = current.next;
		}
		return true;
	}

	@Override
	public boolean contains(double number) { // If positionOf returns a value, the linked list HAS to contain the number.
		return positionOf(number) != -1;
	}

	@Override
	public void add(double number) { // We create a new node, which becomes the first node if it doesn't exist. Then, until the next node is null, we keep going through nodes.
									 // Then, the next node becomes the node we created.
		Node new_node = new Node(number);

		if (first == null)
		{
			first = new_node;
		}

		else {
			Node current = first;
			while (current.next != null)
			{
				current = current.next;
			}
			current.next = new_node;
		}
	}

	@Override
	public void insert(int position, double number) throws IndexOutOfBoundsException {
		if (position < 0 || position > length()){
			throw new IndexOutOfBoundsException("Position is out of scope of the linked list");
		}

		Node new_node = new Node(number);
		Node current = first;

		// To add when we are inserting at position = 0

		if (position == 0)
		{
			current.next = first;
			first = current;
		}

		else {
			for (int i = 0; i < position - 1; i++) {
				current = current.next;
			}

			new_node.next = current.next;
			current.next = new_node;

		}

	} // Similar to add, but we make sure the OG nodes point to the new node and the new node points to the next node.

	@Override
	public double[] asArray() {
		double[] linked_arr = new double[length()];
		Node current = first;
		int index = 0;

		while (current != null){
			linked_arr[index] = current.number;
			current = current.next;
			index++;
		}

		return linked_arr;
	}

	@Override
	public void removeAt(int position) throws IndexOutOfBoundsException, IllegalStateException {

		if (length() == 2){
			throw new IllegalStateException("Linked List only has 2 elements.");
		} else if (position < 0 || position >= length()) {
			throw new IndexOutOfBoundsException("Position is out of bounds of the linked list");
		}

		if (position == 0){
			first = first.next;
			return; // The return exists to break out the loop
		}

		Node current = first;

		for (int i = 0; i < position-1; i++) {
			current = current.next;
		}

		current.next = current.next.next;  // The goal here is to just "skip" through what next was pointing at and point to the one after, effectively removing it.

	}
}