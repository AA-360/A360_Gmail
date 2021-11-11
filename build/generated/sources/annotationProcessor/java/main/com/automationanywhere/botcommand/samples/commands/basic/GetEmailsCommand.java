package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class GetEmailsCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(GetEmailsCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    GetEmails command = new GetEmails();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("SessionName") && parameters.get("SessionName") != null && parameters.get("SessionName").get() != null) {
      convertedParameters.put("SessionName", parameters.get("SessionName").get());
      if(convertedParameters.get("SessionName") !=null && !(convertedParameters.get("SessionName") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","SessionName", "String", parameters.get("SessionName").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("SessionName") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","SessionName"));
    }

    if(parameters.containsKey("UserId") && parameters.get("UserId") != null && parameters.get("UserId").get() != null) {
      convertedParameters.put("UserId", parameters.get("UserId").get());
      if(convertedParameters.get("UserId") !=null && !(convertedParameters.get("UserId") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","UserId", "String", parameters.get("UserId").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("UserId") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","UserId"));
    }

    if(parameters.containsKey("Query") && parameters.get("Query") != null && parameters.get("Query").get() != null) {
      convertedParameters.put("Query", parameters.get("Query").get());
      if(convertedParameters.get("Query") !=null && !(convertedParameters.get("Query") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","Query", "String", parameters.get("Query").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("Query") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","Query"));
    }

    command.setSessionMap(sessionMap);
    try {
      Optional<Value> result =  Optional.ofNullable(command.action((String)convertedParameters.get("SessionName"),(String)convertedParameters.get("UserId"),(String)convertedParameters.get("Query")));
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","action"));
    }
    catch (BotCommandException e) {
      logger.fatal(e.getMessage(),e);
      throw e;
    }
    catch (Throwable e) {
      logger.fatal(e.getMessage(),e);
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.NotBotCommandException",e.getMessage()),e);
    }
  }
}
