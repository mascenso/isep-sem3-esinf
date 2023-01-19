
package PL_Trees.Trees;

/**
 *
 * @author DEI-ESINF
 */
public class TextWord implements Comparable<TextWord>{
    
    private String word;
    private int occurrences;
    
    public TextWord(String word, int occurrences){
        setWord(word, occurrences);
    }
    
    public void setWord(String word, int occurrences){
        this.word=word;
        this.occurrences =occurrences;
    }
    public void incOccurrences(){
        this.occurrences++;
    }
    public String getWord(){
        return word;
    }
    public int getOccurrences(){
        return occurrences;
    }

    @Override
    public int compareTo(TextWord o) {
        return word.compareTo(o.getWord());
    }
    
    public String toString(){
    return "<"+word+">:"+ occurrences;
}
}
