package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.GmailQuickstart;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.automationanywhere.commandsdk.model.AttributeType.*;

@BotCommand
@CommandPkg(
        label = "GetEmails",
        description = "Get Emails ids",
        icon = "sample.svg",
        name = "GetEmails",
        return_type = DataType.LIST,
        return_required = true,
        return_sub_type = DataType.STRING
)

public class GetEmails {

    @Sessions
    private Map<String, Object> sessionMap;

    @Execute
    public ListValue<String> action(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "SessionName:",default_value = "Default",default_value_type = DataType.STRING)
            @NotEmpty
                    String SessionName,
            @Idx(index = "2", type = TEXT)
            @Pkg(label = "userId:",default_value = "me",default_value_type = DataType.STRING)
            @NotEmpty
                    String UserId,
            @Idx(index = "3", type = TEXT)
            @Pkg(label = "Query:")
            @NotEmpty
                    String Query
    ) {
        List<Value> lista = new ArrayList<>();
        ListValue<String> OutPut = new ListValue<String>();
        Map<String, Object> Data = new HashMap<>();
        GmailQuickstart gmail;
        Gmail service;

        if (!sessionMap.containsKey(SessionName)) {
            throw new BotCommandException("Session '" + SessionName + "' not found!");
        }else{
            Data = ((Map<String, Object>) sessionMap.get(SessionName));
            gmail = ((GmailQuickstart) Data.get("gm"));
        }

        try{
            List<Message> emails =  gmail.emails(UserId,Query);
            for(Message mail: emails){
                lista.add(new StringValue(mail.getId()));
                System.out.println(mail.getSnippet());
            }
            OutPut.set(lista);
            return OutPut;

        }catch(IOException | GeneralSecurityException e){
            throw new BotCommandException(e.getMessage());
        }
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

}



