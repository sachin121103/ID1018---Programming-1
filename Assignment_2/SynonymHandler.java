// SynonymHandler

/****************************************************************

SynonymHandler handles information about synonyms for various
words.

The synonym data can be read from a file and handled in various
ways. These data consists of several lines, where each line begins
with a word, and this word is followed with a number of synonyms.

The synonym line for a given word can be obtained. It is possible
to add a synonym line, and to remove the synonym line for a given
word. Also a synonym for a particular word can be added, or
removed. The synonym data can be sorted. Lastly, the data can be
written to a given file.

Author: Fadil Galjic

****************************************************************/

import java.io.*;    // FileReader, BufferedReader, PrintWriter,
                     // IOException

class SynonymHandler
{
	// readSynonymData reads the synonym data from a given file
	// and returns the data as an array
    public static String[] readSynonymData (String synonymFile)
                                         throws IOException
    {
        BufferedReader reader = new BufferedReader(
	        new FileReader(synonymFile));
        int numberOfLines = 0;
        String synonymLine = reader.readLine();
        while (synonymLine != null)
        {
			numberOfLines++;
			synonymLine = reader.readLine();
		}
		reader.close();

		String[] synonymData = new String[numberOfLines];
        reader = new BufferedReader(new FileReader(synonymFile));
		for (int i = 0; i < numberOfLines; i++)
		    synonymData[i] = reader.readLine();
		reader.close();

		return synonymData;
    }

    // writeSynonymData writes a given synonym data to a given
    // file
    public static void writeSynonymData (String[] synonymData,
        String synonymFile) throws IOException
    {
        PrintWriter writer = new PrintWriter(synonymFile);
        for (String synonymLine : synonymData)
            writer.println(synonymLine);
        writer.close();

    }

    // synonymLineIndex accepts synonym data, and returns the
    // index of the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	private static int synonymLineIndex (String[] synonymData,
        String word) throws IllegalArgumentException
    {
		int delimiterIndex = 0;
		String w = "";
		int i = 0;
		boolean wordFound = false;
		while (!wordFound  &&  i < synonymData.length)
		{
		    delimiterIndex = synonymData[i].indexOf('|');
		    w = synonymData[i].substring(0, delimiterIndex).trim();
		    if (w.equalsIgnoreCase(word))
				wordFound = true;
			else
				i++;
	    }

	    if (!wordFound)
	        throw new IllegalArgumentException(
		        word + " not present");

	    return i;
	}

    // getSynonymLine accepts synonym data, and returns
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
    public static String getSynonymLine (String[] synonymData,
        String word) throws IllegalArgumentException
    {
		int index = synonymLineIndex(synonymData, word);

	    return synonymData[index];
	}

    // addSynonymLine accepts synonym data, and adds a given
    // synonym line to the data.
	public static String[] addSynonymLine (String[] synonymData,
	    String synonymLine)
	{
		String[] synData = new String[synonymData.length + 1];
		for (int i = 0; i < synonymData.length; i++)
		    synData[i] = synonymData[i];
		synData[synData.length - 1] = synonymLine;

	    return synData;
	}

    // removeSynonymLine accepts synonym data, and removes
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.

	// Approach here is to use the synonymLineIndex to get the index of the word I want to remove the
	// line of, and then copy the remaining lines (except the one to remove) into a new array
	// and return that array

	public static String[] removeSynonymLine (String[] synonymData,
	    String word) throws IllegalArgumentException
	{
		// add code here
		int word_index = synonymLineIndex(synonymData, word); // Get word index of the word from the array.

		if (word_index == -1){
			throw new IllegalArgumentException(word + " not present"); // Throw exception if word isn't in array.
		}

		int j = 0; // Create counter j and initialise to 0
		String[] output_synonym = new String[synonymData.length-1]; // Create array that is shorter by 1 for the new array.

		for (int i = 0; i < synonymData.length; i++) {
			if (i == word_index){
				continue;
            } // Skip the word in the position of the word index we previously got.
			else {
				output_synonym[j] = synonymData[i];
				j++;
			} // Copy the array (with the exception of the word we don't want).
		}
		return output_synonym;
	}

    // getSynonyms returns synonyms in a given synonym line.
	private static String[] getSynonyms (String synonymLine)
	{
        // add code here
		String[] splitSynonym = new String[2];
		splitSynonym = synonymLine.split("\\|");

		String synonyms = splitSynonym[0].trim();
        return synonyms.split(",");

	}
    // addSynonym accepts synonym data, and adds a given
    // synonym for a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.

	// Approach here would be to get the index of the word, then locate the exact word and add it to it using
	// StringBuilder, then converting it back to

	public static void addSynonym (String[] synonymData,
	    String word, String synonym) throws IllegalArgumentException
	{
        // add code here

		int word_index = synonymLineIndex(synonymData, word); // access the data of the of the line

		String line_needed = synonymData[word_index]; //
		StringBuilder sb = new StringBuilder(line_needed); //create a new string builder object

		String attach_synonym = ", " + synonym; // create new string and attach the comma and space

		sb.append(attach_synonym); // add synonym to the stringbuilder object.
		String final_synonym = sb.toString(); // Convert stringbuilder object back to string.

		synonymData[word_index] = final_synonym; // Add above string to the synonymData array.
	}

    // removeSynonym accepts synonym data, and removes a given
    // synonym for a given word.
    // If the given word or the given synonym is not present, an
    // exception of the type IllegalArgumentException is thrown.
    // If there is only one synonym for the given word, an
    // exception of the type IllegalStateException is thrown.


	// Find the line we are removing the word from (Using the index method)
	// Split the line into 2 (Get synonyms probably won't work here because I'm trying to get the left side as well)
	// Break the synonyms up and remove the one we want to get rid of
	// Put it back together and output it

	public static void removeSynonym(String[] synonymData, String word, String synonym)
			throws IllegalArgumentException, IllegalStateException {
		// Find the index of the word in the array
		int wordIndex = synonymLineIndex(synonymData, word);

		// Ensure that the word is in the array
		if (wordIndex == -1) {
			throw new IllegalArgumentException(word + " not present");
		}

		// Split the row into key and synonyms
		String[] parts = synonymData[wordIndex].split("\\|");
		String key = parts[0].trim();
		String[] synonyms = parts[1].split(",");

		// Create Stringbuilder object.
		StringBuilder sb = new StringBuilder();

		for (String syn : synonyms) {
			if (!syn.trim().equalsIgnoreCase(synonym)) { // Compare the synonym to remove with the word highlighted with syn.
				if (sb.length() > 0) {
					sb.append(", "); // Adds commas if the word to remove is NOT the first word.
				}
				sb.append(syn.trim()); // Adds the synonyms (except the one to be removed)
			}
		}

		// Ensure at least one synonym remains
		if (sb.length() == 0) {
			throw new IllegalStateException("Cannot remove the last synonym for " + word);
		}

		String updatedSyn = key + " | " + sb.toString(); // Adds the word and synonyms into the correct format.
		synonymData[wordIndex] = updatedSyn; // Puts back the updated word and synonym into the array.
	}


    // sortIgnoreCase sorts an array of strings, using
    // the selection sort algorithm

	// I am assuming that we want to just sort strings using a selection sort here

    private static void sortIgnoreCase (String[] strings)
    {
        // add code here
		for (int i = 0; i < strings.length - 1; i++) {
			int smallestIndex = i;
			for (int j = i + 1; j < strings.length; j++) {
				if (strings[j].compareTo(strings[smallestIndex]) < 0) {
					smallestIndex = j;
				}
			}

			if (smallestIndex != i) {
				String temp = strings[i];
				strings[i] = strings[smallestIndex];
				strings[smallestIndex] = temp;
			}
		}

		// Selection Sort implementation
	}

    // sortSynonymLine accepts a synonym line, and sorts
    // the synonyms in this line

	// I am going to presume that this sorts the synonyms only, so I just need to apply sortIgnoreCase on
	// Getsynonyms' output
    private static String sortSynonymLine (String synonymLine)
    {
	    // add code here

		String[] sortableString = getSynonyms(synonymLine);

		for (int i = 0; i < sortableString.length; i++) {
			sortableString[i] = sortableString[i].trim();
		}

		sortIgnoreCase(sortableString);

		return String.join(", ", sortableString);
	}

    // sortSynonymData accepts synonym data, and sorts its
    // synonym lines and the synonyms in these lines

	// I need to implement two sorts, one is to sort the words, and one to sort the synonyms
	// The way I might approach this is probably to split the words first, then split the synonyms
	// Issue would be to combine the right combinations, probably to be done with some for loops
	// I could sort each word, then sort their synonyms.
	// A matrix might work here as well

	public static void sortSynonymData (String[] synonymData)
	{
        // add code here

		String[] words = new String[synonymData.length]; // Creates a new array of JUST the words (left side of the pipe).
		String[] synonyms = new String[synonymData.length]; // Creates a new array of the string of all the synonyms.

		for (int i = 0; i < synonymData.length; i++) {
			String[] parts = synonymData[i].split(" \\| "); // Splits the line into words and synonyms
			words[i] = parts[0].trim(); // Adds the word to the array we created above for the words.
			synonyms[i] = parts[1].trim(); // Adds the synonym LINE (not individual) to the array we created for synonyms above.
		}

		for (int i = 0; i < words.length - 1; i++) {
			int smallestIndex = i; //
			for (int j = i + 1; j < words.length; j++) {
				if (words[j].compareToIgnoreCase(words[smallestIndex]) < 0) {
					smallestIndex = j;
				}
		}

		// Swap words
		if (smallestIndex != i) {
			String tempWord = words[i];
			words[i] = words[smallestIndex];
			words[smallestIndex] = tempWord;

			String tempSynonyms = synonyms[i];
			synonyms[i] = synonyms[smallestIndex];
			synonyms[smallestIndex] = tempSynonyms;
		}

		// Lines above are all a selection sort implementation. Create a string to hold the temp word and swap smaller words.

		}
		for (int i = 0; i < synonyms.length; i++) {
			synonyms[i] = sortSynonymLine(synonyms[i]);
		}

		// Applies private sort method to synonyms

		for (int i = 0; i < synonymData.length; i++) {
			synonymData[i] = words[i] + " | " + synonyms[i];
		}

		// Appends the sorted word with the relevant, sorted list of synonyms.
	}

}