import components.map.*;
import components.sequence.*;
import components.set.*;


/**
 * {@code WordAssociationNetwork} represented as a
 * {@code Map<String, Sequence<String>>} with implementation of primary methods.
 *
 * @convention <pre>
 * [all characters in the keys and values of this.rep.key are letters from a-z]
 * </pre>
 * @correspondence <pre>
 * this = $this.rep and this = {key: $this.rep.key, value: $this.rep.value}
 * </pre>
 *
 * @author Zhuoyang Li
 */
public class WordAssociationNetwork1 extends WordAssociationNetworkSecondary{
    /*
     * private members----------------------------------------------------------
     */

     /**
      * The map that represents {@code this}.
      */
    private Map<String, Sequence<String>> rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = new Map1L<String, Sequence<String>>();
}

/**
 * Constructors --------------------------------------------------------------
 */

 /**
  * Default constructor.
  *
  * @param word
  *          {@code String} to be added
  */
    public WordAssociationNetwork1(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Word cannot be null.");
        }
        //create without example sentence, sequence is empty
        this.createNewRep();
        this.rep.add(word, new Sequence1L<String>());
    }


    /**
     * Constructor from a {@code Map<String, Set<String>>}.
     *
     * @param newNetwork
     *          {@code Map<String, List<String>>} to be added
     */
    public WordAssociationNetwork1(Map<String, Sequence<String>> newNetwork) {
        if (newNetwork == null) {
            throw new IllegalArgumentException("New network map cannot be null.");
        }

        this.createNewRep();
        this.rep.transferFrom(newNetwork);
}

    /*
     * Standard methods---------------------------------------------------------
     */
    @Override
    public final String toString() {
        return this.rep.toString();
    }

    @Override
    public final boolean equals(Object obj) {
        return this.rep.equals(obj);
    }

    @Override
    public final WordAssociationNetwork newInstance() {
        try{
            return this.getClass().getConstructor().newInstance();
        } catch(ReflectiveOperationException e) {
            throw new RuntimeException("No constructor available");
        }
    }

    @Override
    public final void transferFrom(WordAssociationNetwork source) {
        WordAssociationNetwork1 localSource = (WordAssociationNetwork1) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    @Override
    public final void clear(){
        this.createNewRep();
    }

    /*
     * Kernel methods-----------------------------------------------------------
     */

    @Override
    public final void addSentence(String sentence, int index) {
      assert sentence != null : "Violation of: sentence is not null";
      assert index >= 0 : "Violation of: index >= 0";
      assert index <= this.rep.value(sentence).length() : "Violation of: index <= |this.sentences|";

      //the method does not know the key, so we need to find the key first
      Map.Pair<String, Sequence<String>> pair = this.rep.removeAny();
      String key = pair.key();

      //add the sentence to the sequence
      Sequence<String> value = pair.value();
        value.add(index, key);
      //add the pair back to the map
      this.rep.add(key, value);
    }

    @Override
    public final String deleteSentence(int index) {
      assert index >= 0 : "Violation of: index >= 0";

        //the method does not know the key, so we need to find the key first
        Map.Pair<String, Sequence<String>> pair = this.rep.removeAny();
        String key = pair.key();
        Sequence<String> value = pair.value();

        //delete the sentence from the sequence
        String sentence = value.remove(index);
        //add the pair back to the map
        this.rep.add(key, value);

        return sentence;
   }

   @Override
   public final int isInNetwork(String sentence) {
    int index = -1;
    //find the sequence
    Map.Pair<String, Sequence<String>> pair = this.rep.removeAny();
    String key = pair.key();
    Sequence<String> value = pair.value();
    for (int i = 0; i < value.length(); i++) {
        if (value.entry(i).equals(sentence)) {
            this.rep.add(key, value);
            index = i;
        }
    }
    this.rep.add(key, value);
    return index;
   }

    @Override
    public final Set<String> getAllSentences() {
        Set<String> allSentences = new Set1L<String>();
        //find the pair
        Map.Pair<String, Sequence<String>> pair = this.rep.removeAny();
        String key = pair.key();
        Sequence<String> value = pair.value();
        for (int i = 0; i < value.length(); i++) {
            allSentences.add(value.entry(i));
        }
        this.rep.add(key, value);
        return allSentences;
    }

    @Override
    public final int totalSentences() {
       int total = 0;
         //find the pair
            Map.Pair<String, Sequence<String>> pair = this.rep.removeAny();
            String key = pair.key();
            Sequence<String> value = pair.value();
            total = value.length();
            this.rep.add(key, value);
            return total;
    }














}
