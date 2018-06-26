
import java.io.File;
import java.io.FileReader;

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

        File f = new File(DATA_DIR + "Gatto5.jpg");

        

        
        System.err.println(f.getName());
        f.renameTo(new File(DATA_DIR + "/Datadir/" + f.getName()));

    }
}
