import components.set.Set;

public interface WordAssociationNetwork extends WordAssociationNetworkKernel {

    /**
     * Retrieves the top X words based on frequency of occurrence in sentences associated with {@code this}, excluding common words {@code commonWords}.
     *
     * @param x the number of words to retrieve
     * @param commonWords a set of common words to exclude
     * @return a set of the top X words
     * @requires x > 0
     * @ensures Set  = [top X words based on frequency]
     */
    Set<String> getTopXWords(int x, Set<String> commonWords);

    /**
     * Adds multiple sentences at once to this.
     *
     * @param sentences an array of sentences to add
     * @updates this
     * @requires sentences != null
     * @ensures this = #this + sentences
     */
    void addMultipleSentences(String[] sentences);


    /**
     * Retrieves all sentences associated with the specified word.
     *
     * @param word the word to analyze
     * @return a set of sentences include the word
     * @requires word != null && word is in {@code this.sentences}
     * @ensures returns a set of sentences associated with this including the {@code word}
     */
    Set<String> getSentences(String word);

    /**
     * Clears all sentences associated with {@code this} including {@code word}
     *
     * @param word the word to clear data for
     * @updates this
     * @return a set of sentences that were removed
     * @requires word != null
     * @ensures this = #this - [sentences associated with word]
     */
    Set<String> clearWordData(String word);

    /**
     * Retrieves the number of sentences associated with the specified word.
     *
     * @param word the word to analyze
     * @return the number of sentences associated with the specified word
     * @requires word != null
     * @ensures returns the number of sentences associated with this
     */
    int getNumSentences(String word);


}
