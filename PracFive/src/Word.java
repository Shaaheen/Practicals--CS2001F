import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shaaheen on 4/28/2015.
 * Word object that has all entry methods and contains a word and all its definitions
 */

public class Word implements Entry{
    String word;
    ArrayList<Definition> definitions = new ArrayList<Definition>(); //list of words definitions
    int probe; //Number of probes it took to insert this word

    public Word(String word,Definition def){ //Constructor method
        this.word = word;
        definitions.add(def);

    }

    public Word() {}

    //adds to the number of robes taken
    public void addProbe(){
        probe++;
    }
    @Override
    public String getWord() {
        return word;
    }

    //gets list of definitions
    @Override
    public List<Definition> getDefinitions() {
        return definitions;
    }

    //add a definition to a word definition list
    @Override
    public void addDefinition(WordType wordType, String description) {
        definitions.add(new Definition(wordType,description));
    }

    //Add a definition to the definition list
    @Override
    public void addDefinition(Definition definition) {
        definitions.add(definition);
    }

    //checks if the word object is an entry for a specific word
    @Override
    public boolean isEntryFor(String word) {
        return  this.word == word;
    }
}
