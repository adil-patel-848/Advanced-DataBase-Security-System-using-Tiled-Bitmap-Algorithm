
package db_security_tbm;

import client_operation.ClientLoginFrame;
import java.awt.*;

public class Db_security_tbm {

    public static void main(String[] args) {
        ClientLoginFrame clf=new ClientLoginFrame();
        Dimension dm=Toolkit.getDefaultToolkit().getScreenSize();
        clf.setSize(dm);
        clf.setVisible(true);
               
    }
}
