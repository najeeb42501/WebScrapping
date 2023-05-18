/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webscrap;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class WebScrap extends javax.swing.JFrame {

    //variables 
    
    int count = 0;
    int totalPosts = 0;
    public static int uniqueWords;
    
    int max = 0;
    int min =0; 
    
    File file = new File("DATA-BBC.csv");
    boolean f;
    FileWriter fw;
    BufferedWriter bw;
      
    
    
    public WebScrap() throws IOException {
        
        initComponents();
        setBounds(500,100,1000,800);
        
        //creating objects for file writing 
        f = file.createNewFile();
        fw = new FileWriter(file, true);
        bw = new BufferedWriter(fw);
   
    }
    
//********************         methods for scraping data from web.  *********************
    
    // opening page/category method 
    
    public void openTab(String link) throws IOException{
        
        String titlesLink ;
        int i =1;
           
        Document doc = Jsoup.connect(link).get();   
        System.out.println("Page Title : " + doc.title());
        Elements page = doc.getElementsByClass("qa-heading-link lx-stream-post__header-link");     
        
        
        
        for(Element p : page){
                
            String Href = p.attr("href");                  
            titlesLink = "https://www.bbc.com" + Href;

            System.out.println("Title HREF : " + i + " ==> " + titlesLink);
            
            String linkText = p.text(); 
            System.out.println("Post Title : " + linkText);
            
            count++;
            totalPosts++;
             
            System.out.println("+++++++++++++           + +++++++++++    +++++++ Count = " + count)  ;
            
            //opening posts one by one    
            openPost(titlesLink,doc.title());
           

            i++;
        
        }
        
         
            if(count==20){               
                count =0;
            } 
 
            System.out.println("++++++++++++++++++++++++++++++++++++          Count : " + count );
            if(count!=0){
                 nextPage(link);
            }
    

        System.out.println("=============================== >>>>>            Total Posts :  " + totalPosts );    
    }
    
    // opening post method 
    
    public void openPost(String link,String pageName) throws IOException{
        
       
        String postText;
        int words=0;
        
        Document doc = Jsoup.connect(link).get();   
        String title = doc.title(); 
        System.out.println("New Title : " + title);
        Elements page = doc.getElementsByClass("bbc-4wucq3 e57qer20");       
        
        System.out.println( "Story/News : ");
        for(Element p : page){
                
            postText = p.text();
            System.out.println( postText);
            
            calUniqueWords(postText); 
            words = postText.length() + title.length();
 
            bw.write("PAGE NAME : " + doc.title() );
            bw.write(",");
            bw.write("POST TITLE : " + title);
            bw.write(",");
            bw.write("NEWS  " + postText);
            bw.write(",");
            bw.write("TOTAL WORDS COUNT : " + words);
            bw.write(",");
            bw.write("\n");
          
        }
           
                  
         System.out.println(" 444444444444444444444 444444                   444444444     Unique Words : " + uniqueWords );
        
         
      
}
    
   //method to get netxt page link from current age  
 
 public void nextPage(String l) throws IOException{
        
        System.out.println("NEXT PAGE  -----------");
        
        Document doc = Jsoup.connect(l).get();       
        Elements page = doc.getElementsByClass("qa-pagination-next-page"); 
        String link;
        
        for(Element p : page){    
            
            String Href = p.attr("href");  
            link = "https://www.bbc.com" + Href;
            System.out.println("HREF == " + link);
            
           //opening next page again and again 
            openTab(link);
            
        }      
        
        System.out.println("END-----------");
       
        
    }
    
 
 //calculating unique words method 
 
 public void calUniqueWords(String data){
     
        boolean status;
        data = data.replaceAll("[^A-Za-z0-9]"," ");
        String []words = data.split(" ");
        
        
        for(int i=0;i<words.length;i++)
        {
            status = true;
            for(int j=0;j<words.length;j++)
            {
                if(i!=j)
                {
                    if(Objects.equals(words[i], words[j]))
                    {
                        status = false;
                        break;
                    }
                }
            }
            
            if(status){
                
                uniqueWords++;
  
            }
        }
 }
    
    
    
    

    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        webLink = new javax.swing.JTextField();
        startButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        uWords = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        topTenWords = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        maxAndMin = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        webLink.setText("bbc.com/urdu");
        webLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webLinkActionPerformed(evt);
            }
        });
        jPanel1.add(webLink, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 240, 40));

        startButton.setFont(new java.awt.Font("Britannic Bold", 0, 11)); // NOI18N
        startButton.setText("Start Scraping ");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        jPanel1.add(startButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 150, 40));

        uWords.setColumns(20);
        uWords.setRows(5);
        jScrollPane1.setViewportView(uWords);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 220, 50));

        topTenWords.setColumns(20);
        topTenWords.setRows(5);
        jScrollPane3.setViewportView(topTenWords);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, 220, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Top Ten Words : ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, -1, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Unique Words :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, 40));

        maxAndMin.setColumns(20);
        maxAndMin.setRows(5);
        jScrollPane2.setViewportView(maxAndMin);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 220, 50));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Max and In Story :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, -1, 40));

        jButton1.setText("Calculate ");
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 360, -1, -1));

        jButton2.setText("Calculate ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, -1, -1));

        jButton3.setText("Calculate ");
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 250, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 720, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void webLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webLinkActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_webLinkActionPerformed

    
    //Start Button method
    
    
    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
       
         
// Start Button action mehtod 
        

        String web = "https://www." + webLink.getText();       
        Document doc;   
        
        try {
            
            doc = Jsoup.connect(web).get();
            Elements tab = doc.getElementsByClass("bbc-1x1owac");
            
             //String[] pageNames = new String[6];     
            String[] pages = new String[6];    
            int i = 0;
            
            for (Element link : tab) { 
            
                 String linkHref = link.attr("href");
            
                if (!linkHref.contains("media/video")) {
                    pages[i] = "https://www.bbc.com" + linkHref;
                    System.out.println("Page Links : " + pages[i]);
                    i++;
                    
                    
                }
                
            }
            
            
            WebScrap o = new WebScrap();
            
            
            
            for (int j = 0; j <1; j++) {
                System.out.println("Unique : 55555555   5555555555      55555555 " + uniqueWords);
                o.openTab(pages[j]);
               
                  
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(WebScrap.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
    }//GEN-LAST:event_startButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
       // String w = Integer.toString(uniqueWords);
        System.out.println(" Unique Words : " + uniqueWords);
        uWords.setText(" Count : " + uniqueWords);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    
    
    
    public static void main(String args[]) throws IOException {
        
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WebScrap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WebScrap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WebScrap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WebScrap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                try {
                    new WebScrap().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(WebScrap.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //scrapping data
         
        
        System.out.println("Unique : " + uniqueWords);
        
        

        
        
    }
    
    
    
        
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea maxAndMin;
    private javax.swing.JButton startButton;
    private javax.swing.JTextArea topTenWords;
    private javax.swing.JTextArea uWords;
    private javax.swing.JTextField webLink;
    // End of variables declaration//GEN-END:variables


    
    //end webscrap class
}
