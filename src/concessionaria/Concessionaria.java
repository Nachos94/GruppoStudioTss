/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concessionaria;

import javax.swing.JOptionPane;

/**
 *
 * @author Nachos
 */
public class Concessionaria {

    static int costo_iniziale;
    static int costo;
    static int acconto;
    static int n_rate;
    static int rata;
    static String tx;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*
        
        Facciamo un programma che: Fornisce la scelta tra 3 macchine che costano 3 diversi importi,
        chiede un acconto e entro quante rate saldare il resto del totale,
                chiede inoltre quanto metti per ciascuna rata (contando che possono essere importi diversi), 
                se saldato entro le rate previste tutto ok, 
                        altrimenti don Luca ti sega un braccio
         */
        String scelta = JOptionPane.showInputDialog(" --- BENVENUTO --- \n"
                + "seleziona il modello della tua auto \n"
                + "modello S\n"
                + "modello M\n"
                + "modello F\n").toUpperCase();

        if (scelta.equals("M")) {

            ModelloS();
            Acconto();

            costo = costo_iniziale - acconto;
            Rateizza();
        }
        if (scelta.equals("F")) {

            ModelloB();
            Acconto();

            costo = costo_iniziale - acconto;
            Rateizza();
        }
        if (scelta.equals("S")) {

            ModelloF();
            Acconto();

            costo = costo_iniziale - acconto;

            Rateizza();

        }
    }

    static void ModelloS() {

        costo_iniziale = 120000;

    }

    static void ModelloB() {

        costo_iniziale = 40000;

    }

    static void ModelloF() {

        costo_iniziale = 100000;

    }

    static void Acconto() {

        tx = JOptionPane.showInputDialog("Sapendo che la tua auto costa " + costo_iniziale + " euro,"
                + "quanto intendi lasciare di acconto?");
        acconto = Integer.parseInt(tx);

    }

    static void Rateizza() {

        String report = "L'auto da te selezionata ha un costo di " + costo_iniziale + " euro\n"
                + "hai lasciato un acconto di " + acconto + " euro";

        JOptionPane.showMessageDialog(null, report);

        tx = JOptionPane.showInputDialog("In quante rate pensi di saldare il restante?");

        n_rate = Integer.parseInt(tx);

        //ora ho tutto ciò che serve per rateizzare l'auto, n_rate , acconto, costo
        //devo far richiedere quanto mette in ogni rata
        int i = 0;

        do {
            tx = JOptionPane.showInputDialog("Rata " + (i+1) + ") di " + n_rate + ")\n" 
                    + "Quanto mi paghi questa volta?"
                    + " mancano" + costo + " euro di " + costo_iniziale);

            rata = Integer.parseInt(tx);

            costo = costo - rata;

            i++;

        } while (!(i == n_rate || costo <= 0 ));

        if (costo > 0) {

            JOptionPane.showMessageDialog(null, "Adesso mi tocca segarti un braccio!!\n"
                    + "mancano " + costo + " euro!");

        } else {

            JOptionPane.showMessageDialog(null, "E' stato un piacere fare affari con lei signore\n"
                    + "mi ha regolarmente saldato " + costo_iniziale + " euro\n"
                    + "in " + i + " rate "
                    + "dando un acconto di " + acconto + " euro");
            
                    if (costo < 0)
                        
                        JOptionPane.showMessageDialog(null, "Non Dimentichi il suo resto!"
                                + "mi ha versato " + (costo*(-1)) + " euro in più del dovuto!" );

        }

    }
}
