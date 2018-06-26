
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tss
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    
    
    private static final String DATA_DIR = "/home/tss/Documenti/";
    
    
    public static void main(String[] args) throws Exception {
       
       File f = new File(DATA_DIR + "fileprova.txt");
        
       FileWriter fw = new FileWriter(f);
        
        
        
       
    }
    
}
