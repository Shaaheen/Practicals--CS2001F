import java.util.List;

/**
 * Created by Shaaheen on 4/28/2015.
 */
public class Word implements Entry{
    String word;
    List<Definition> definitions;

    public Word(String word,Definition def){
        this.word = word;
        definitions.add(def);
    }
    @Override
    public String getWord() {
        return word;
    }

    @Override
    public List<Definition> getDefinitions() {
        return definitions;
    }

    @Override
    public void addDefinition(WordType wordType, String description) {
        definitions.add(new Definition(wordType,description));
    }

    @Override
    public void addDefinition(Definition definition) {
        definitions.add(definition);
    }

    @Override
    public boolean isEntryFor(String word) {
        return  this.word == word;
    }
}
