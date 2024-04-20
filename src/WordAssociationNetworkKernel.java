import components.standard.Standard;
import components.set.Set;


/**
 * Interface for a Word Association Network component, managing words and their associations.
 *
 * @mathsubtypes <pre>
 * WORD_ASSOCIATION_NETWORK is set of string
 * </pre>
 * @mathmodel type WordAssociationNetwork is modeled by WORD_ASSOCIATION_NETWORK
 * @initially <pre>
 * ():
 *  ensures
 *   this = {}
 * </pre>
 */
public interface WordAssociationNetworkKernel extends Standard<WordAssociationNetwork> {

    /**
     * Adds a sentence to this at the specified index.
     *
     * @param sentence the sentence to add
     * @param index the index to add the sentence at
     * @updates this
     * @requires sentence != null && index >= 0 && index <= |this.sentences|
     * @ensures this = #this + sentence
     */
    void addSentence(String sentence, int index);

   /**
     * Deletes a sentence from this at the specified index.
     *
     * @param index the index to delete the sentence at
     * @return the deleted sentence
     * @updates this
     * @requires index >= 0 && index < |this.sentences|
     * @ensures this = #this - [sentence at index]
     */
    String deleteSentence(int index);

    /**
     * Returns -1 if the sentence is not in the network, otherwise returns the index of the sentence.
     * @param sentence the sentence to check
     * @return the index of the sentence if it is in the network, -1 otherwise
     * @requires sentence != null
     * @ensures isInNetwork = -1 || 0 <= isInNetwork <= |this.sentences|
     */
    int isInNetwork(String sentence);

    /**
     * Retrieves all sentences currently in the network.
     *
     * @return a set of all sentences in the network
     * @ensures returns a list containing all sentences stored in the network
     */
    Set<String> getAllSentences();

    /**
     * Returns the total number of sentences in the network.
     *
     * @return the total number of sentences
     * @ensures returns the number of sentences currently stored in the network
     */
    int totalSentences();

}
