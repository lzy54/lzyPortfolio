import components.set.Set;
import components.set.Set1L;
import components.map.Map1L;
import components.map.Map.Pair;;
public abstract class WordAssociationNetworkSecondary implements WordAssociationNetwork{



    @Override
    public void addMultipleSentences(String[] sentences) {
        for (String sentence : sentences) {
            this.addSentence(sentence, totalSentences());
        }
    }

    @Override
    public Set<String> clearWordData(String word) {
        Set<String> sentences = this.getAllSentences();
        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                //isInNetwork returns the index of the sentence

               sentences.add( this.deleteSentence(isInNetwork(sentence)));
        }
    }
        return sentences;
}

    @Override
    public Set<String> getSentences(String word) {
        Set<String> sentences = this.getAllSentences();
        Set<String> result = new Set1L<String>();
        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                result.add(sentence);
            }
        }
        return result;
    }

    @Override
    public int getNumSentences(String word) {
        int count = 0;
        Set<String> sentences = this.getAllSentences();
        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Set<String> getTopXWords(int x, Set<String> commonWords) {
        Set<String> sentences = this.getAllSentences();
        Map1L<String, Integer> wordCount = new Map1L<String, Integer>();
        for (String sentence : sentences) {
            String[] words = sentence.split(" ");
            for (String word : words) {
                if (!commonWords.contains(word)) {
                    if (wordCount.hasKey(word)) {
                        wordCount.replaceValue(word, wordCount.value(word) + 1);
                    } else {
                        wordCount.add(word, 1);
                    }
                }
            }
        }
        Set<String> result = new Set1L<String>();
        for (int i = 0; i < x; i++) {
            Pair<String, Integer> max = wordCount.removeAny();
            result.add(max.key());
        }
        return result;
    }




    /**
     * Returns a string representation of the network.
     * Typically, this can be the list of all sentences, formatted for easy reading.
     */
    @Override
    public String toString() {
        String result = "This word contains " + this.totalSentences() + " sentences: \n";
        for (String sentence : this.getAllSentences()) {
            result += sentence + "\n";
        }
        return result;
    }

    /**
     * Checks equality based on the content of the network.
     * Two networks are considered equal if they contain the same sentences in the same order.
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if(this.getClass().equals(obj.getClass())){
            WordAssociationNetworkSecondary other = (WordAssociationNetworkSecondary) obj;
            if (this.totalSentences() == other.totalSentences()) {
                result = true;
                for (String sentence : this.getAllSentences()) {
                    if (!other.getAllSentences().contains(sentence)) {
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }

}