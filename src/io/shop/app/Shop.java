package io.shop.app;

import io.shop.view.MainView;
import java.util.Locale;

/**
 *
 * @author Rahman S
 * @date   29 June 2022
 */
public class Shop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Locale.setDefault(new Locale("id", "ID"));
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }
    
}
