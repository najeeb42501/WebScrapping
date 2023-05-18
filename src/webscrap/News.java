package webscrap;

public class News {
    public String headLine;
    public String details;
    public String cat;
    public String topWords;
    public News next;

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getTopWords() {
        return topWords;
    }

    public void setTopWords(String topWords) {
        this.topWords = topWords;
    }

    public News(){}


    public News(String headLine, String details, String cat) {
        this.headLine = headLine;
        this.details = details;
        this.cat = cat;
        this.topWords = String.join(" | ",this.topTenWords());
    }

    public MaxHeap<Integer, String> wordsHeap(){
        return StringManipulation.topFrquency(details);
    }

    public String[] topTenWords(){
        MaxHeap<Integer, String> mh = wordsHeap();
//        wordsHeap().display();
        String[] topTenWords = new String[10];
        String n = "";
        for (int i = 0; i < 10 && n!=null ; i++) {
            Node<Integer, String> nm = mh.extractMax();
            if(nm==null)
                break;
            topTenWords[i] = n = nm.value;
        }
        return topTenWords;
    }

    @Override
    public String toString() {
        System.out.print("Words:     "+topWords);
//        wordsHeap().display();
        System.out.println();
        return  "headLine:  " + headLine +"\n"+
                "details:   " + details + "\n"+
                "cat:       " + cat+
                "  \n\n\n_______________________________" ;
    }
}
