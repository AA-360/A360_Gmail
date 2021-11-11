import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.samples.commands.basic.GetEmails;
import com.automationanywhere.botcommand.samples.commands.basic.GmailStart;
import com.automationanywhere.botcommand.samples.commands.utils.GmailQuickstart;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.Message;
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class google {
    Map<String, Object> theMap = new HashMap<String, Object>();

    @Test
    private void testAct() {
        GmailStart a = new GmailStart();
        GetEmails b = new GetEmails();
        a.setSessionMap(theMap);
        b.setSessionMap(theMap);
        String type="READONLY";
        type="MODIFY";
        a.action("hehe","C:\\Users\\melque\\Documents\\credentials.json",type);
        ListValue<String> mails = b.action("hehe","me","in:TESTE");
        for(Value m: mails.get()){
            System.out.println(m.toString());
        }

    }

    private void test(){
        try{

            GmailQuickstart gm = new GmailQuickstart();
            Gmail service = gm.start();

            //labels(service);
            List<Message> emails = gm.emails("me","in:TESTE is:unread");
            for(Message mail: emails){
                System.out.printf("- %s\n", mail.getId());
            }

            //emails(service);

        }catch(IOException | GeneralSecurityException e){
            System.out.println(e.getMessage());
        }
    }

    public void labels(Gmail service)throws IOException, GeneralSecurityException{
        String user = "me";
        ListLabelsResponse listResponse = service.users().labels().list(user).execute();
        List<Label> labels = listResponse.getLabels();
        if (labels.isEmpty()) {
            System.out.println("No labels found.");
        } else {
            System.out.println("Labels:");
            for (Label label : labels) {
                System.out.printf("- %s\n", label.getName());
            }
        }
    }
}
