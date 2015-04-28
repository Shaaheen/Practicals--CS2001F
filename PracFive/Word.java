import java.util.List;

/**
 * Created by Shaaheen on 4/28/2015.
 */
public class Word implements Entry{

    WordType typeOfWord;
    Definition defOfWord;
    String descrip;

    @Override
    public String getWord() {
        return null;
    }

    @Override
    public List<Definition> getDefinitions() {
        return null;
    }

    @Override
    public void addDefinition(WordType wordType, String description) {
        this.typeOfWord = wordType;
        this.descrip = description;
    }

    @Override
    public void addDefinition(Definition definition) {
        defOfWord = definition;
    }

    @Override
    public boolean isEntryFor(String word) {
        return false;
    }
}
