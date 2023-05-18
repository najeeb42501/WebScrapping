
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.Buffer;

public class Filing {
    public void write(ObservableList<News> ob){
        File file = new File("DATA-BBC.csv");
        if(file.exists())
            file.delete();
        try{
            file.createNewFile();
            BufferedWriter bf= new BufferedWriter(new FileWriter(file));
            for (News n: ob) {
                bf.append(n.cat).append(",").append(n.headLine).append(",").append(n.details).append(",").append(n.topWords);
                bf.append("\n");
            }
            bf.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<News> read(){
        try{
            File file = new File("DATA-BBC.csv");
            BufferedReader br = new BufferedReader(new FileReader(file));
            ObservableList<News> ob = FXCollections.observableArrayList();
            String line="";
            while((line = br.readLine())!=null){
                ob.add(new News(line.split(",")[1], line.split(",")[2], line.split(",")[0]));
            }
            return ob;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
