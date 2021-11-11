import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;

import javax.swing.*;

public class uteisTest {
    public uteisTest(){}



    public static String padLeft(String s, int n) {
        String val = s;
        if(s.length() > n){
            val = s.substring(0,n-4) + "...";
        }
        return String.format("%" + n + "s", val);
    }

    public void alert(String text){
        JOptionPane.showMessageDialog(null, text, "InfoBox: Title", JOptionPane.INFORMATION_MESSAGE);
    }

}
