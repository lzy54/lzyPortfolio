import components.map.Map;
import components.map.Map2;
import components.set.Set;
import components.set.Set2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Represents a word association network for language learning. This class
 * allows for the addition of words and their associations, retrieving
 * associated words and their relevance, and building a network graph based on
 * these associations.
 *
 * @author Zhuoyang Li
 *
 */
public class WordAssociationNetwork {

    // private variables

    /**
     * representation of the network
     */
    private Map<String, Map<String, Double>> network;

    /**
     * creator of initial representation
     */
    private void createNewRep() {
        this.network = new Map2<String, Map<String, Double>>();
    }

    /*
     * A set of words to be ignored in the network
     */
    private Set<String> ignoredWords = new Set2<String>();

    // Constructors

    //default constructor
    public WordAssociationNetwork() {
        this.createNewRep();
    }

    //copy constructor
    public WordAssociationNetwork(WordAssociationNetwork network) {
        this.network = network.network.newInstance();
    }

    // Constructor from a given network
    public WordAssociationNetwork(Map<String, Map<String, Double>> network) {
        this.network = network.newInstance();
    }

    // Constructor from a given set of ignored words
    public WordAssociationNetwork(Set<String> ignoredWords) {
        this.ignoredWords = ignoredWords.newInstance();
    }

    // Constructor from a given network and a set of ignored words
    public WordAssociationNetwork(Map<String, Map<String, Double>> network,
            Set<String> ignoredWords) {
        this.network = network.newInstance();
        this.ignoredWords = ignoredWords.newInstance();
    }

    //kernel methods

    /**
     * Adds a new word to the network.
     *
     * @param word
     *            The word to add to the network.
     */
    public void addWord(String word) {

        this.network.add(word, new Map2<String, Double>());
    }

    public void deleteWord(String word) {
        this.network.remove(word);
    }

    public void addIgnoredWord(SimpleReader file) {

        while (!file.atEOS()) {
            this.ignoredWords.add(file.nextLine());
        }
    }

    /**
     * Adds an association between two words along with their relevance
     * percentage.
     *
     * @param word
     *            The primary word.
     * @param associatedWord
     *            The word associated with the primary word.
     * @param times
     *            The time that association appears
     */
    public void addAssociation(String word, String associatedWord, int times) {
        // Implementation goes here
    }

    //methods from Project: Glossary
    public void addExamples(String word, SimpleReader file) {
        // Implementation goes here
        while (!file.atEOS()) {
            String line = file.nextLine();
            String[] words = line.split(" ");
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(word)) {
                    if (i > 0) {
                        this.addAssociation(word, words[i - 1], 1);
                    }
                    if (i < words.length - 1) {
                        this.addAssociation(word, words[i + 1], 1);
                    }
                }
            }
        }

    }



    /**
     * Builds a HTML page for network graph, connecting it to a specified number
     * of words with which it has the highest relevance exceeding a certain
     * percentage.
     *
     * @param word
     *            The word to build the network graph for.
     */
    public void buildNetworkGraph(String word) {
        // Implementation goes here
    }

    /**
     * Main method for testing the WordAssociationNetwork class.
     *
     * @param args
     *            Command line arguments.
     */
    public static void main(String[] args) {
        // Test the WordAssociationNetwork class
        WordAssociationNetwork network = new WordAssociationNetwork();
        SimpleWriter out = new SimpleWriter1L();
        out.println("Give me a word to add to the network.");
        String word = new SimpleReader1L().nextLine();
        network.addWord(word);

        out.println("Give me examples of " + word);
        network.addExamples(word, new SimpleReader1L("example.txt"));
        network.buildNetworkGraph(word);
        out.println("The network graph has been built.");
    }

}
