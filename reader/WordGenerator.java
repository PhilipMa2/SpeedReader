package reader;

/**
 * WordGenerator reads in text from a text file and logs stats about the text
 * that is read. 
 * 
 * @author Philip Ma
 */
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class WordGenerator {
    /**
     * The file field stores the file name that WordGenerator will be reading 
     * from. 
     */
    private String file;
    /**
     * The text field stores the scanner of each word generator. 
     */
    private Scanner text;
    /**
     * Number of words produced by the WordGenerator so far.
     */
    private int wordCount;
    /**
     * Number of sentences produced by the WordGenerator so far.
     */
    private int sentenceCount;

    /**
     * Constructs a new generator that proccesses text from the given file.
     * @param filename name of file to be read 
     */
    public WordGenerator(String filename) throws IOException {
        file = filename;
        text = new Scanner(new File(file));
    }

    /**
     * Returns true iff the underlying Scanner of this WordGenerator has text
     * left to process.
     * @return true if there are more text
     */
    public boolean hasNext() {
        return text.hasNext();
    }

    /**
     * Returns the next word of the underlying Scanner. If the Scanner does not
     * have words left, then the behavior of next() is undefined
     * @return next word of the Scanner
     */
    public String next() {
        wordCount++;
        String next = text.next();
        char last = next.charAt(next.length() - 1);
        if (last == '.' || last == '!' || last == '?')
            sentenceCount++;
        return next;
    }

    /**
     * Returns the number of words produced by the WordGenerator so far
     * @return number of words produced
     */
    public int getWordCount() {
        return wordCount;
    }

    /**
     * Define a sentence to be the number of occurrences a sentence-ending 
     * punctuation mark appears at the end of a word. 
     * @return the number of sentences produced by the WordGenerator so far
     */
    public int getSentenceCount() {
        return sentenceCount;
    }
}