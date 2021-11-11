package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.GmailQuickstart;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.automationanywhere.commandsdk.model.AttributeType.*;

@BotCommand
@CommandPkg(
        label = "GmailStart",
        description = "Starts session to control emails",
        icon = "sample.svg",
        name = "GmailStart"
)

public class GmailStart {

    @Sessions
    private Map<String, Object> sessionMap;

    @Execute
    public void action(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "SessionName:",default_value = "Default",default_value_type = DataType.STRING)
            @NotEmpty
                    String SessionName,
            @Idx(index = "2", type = FILE)
            @Pkg(label = "Credential File:")
            @NotEmpty
                    String Arquivo,
            @Idx(index = "3", type = SELECT, options = {
                    @Idx.Option(index = "3.1", pkg = @Pkg(label = "READONLY", value = "READONLY")),
                    @Idx.Option(index = "3.2", pkg = @Pkg(label = "COMPOSE", value = "COMPOSE")),
                    @Idx.Option(index = "3.3", pkg = @Pkg(label = "INSERT", value = "INSERT")),
                    @Idx.Option(index = "3.4", pkg = @Pkg(label = "SEND", value = "SEND")),
                    @Idx.Option(index = "3.5", pkg = @Pkg(label = "LABELS", value = "LABELS")),
                    @Idx.Option(index = "3.6", pkg = @Pkg(label = "METADATA", value = "METADATA")),
                    @Idx.Option(index = "3.7", pkg = @Pkg(label = "MODIFY", value = "MODIFY")),
                    @Idx.Option(index = "3.8", pkg = @Pkg(label = "SETTINGS_BASIC", value = "SETTINGS_BASIC")),
                    @Idx.Option(index = "3.9", pkg = @Pkg(label = "SETTINGS_SHARING", value = "SETTINGS_SHARING"))
            })
            @Pkg(label = "Permission:", description = "", default_value = "record", default_value_type = DataType.STRING)
            @NotEmpty
                    String type
    ) {
        try{
            Map<String, Object> Data = new HashMap<>();

            if (!sessionMap.containsKey(SessionName)) {
                GmailQuickstart gm = new GmailQuickstart();
                gm.CREDENTIALS_FILE_PATH = Arquivo;
                gm.setScope(type);
                gm.start();
                Data.put("gm", gm);
                sessionMap.put(SessionName, Data);
            }else{
                throw new BotCommandException("Session Already existis");
            }
        }catch(IOException | GeneralSecurityException e){
            throw new BotCommandException(e.getMessage());
        }
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

}



