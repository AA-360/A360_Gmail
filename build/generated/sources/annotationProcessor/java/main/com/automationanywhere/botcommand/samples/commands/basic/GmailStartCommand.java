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

public final class GmailStartCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(GmailStartCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    GmailStart command = new GmailStart();
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

    if(parameters.containsKey("Arquivo") && parameters.get("Arquivo") != null && parameters.get("Arquivo").get() != null) {
      convertedParameters.put("Arquivo", parameters.get("Arquivo").get());
      if(convertedParameters.get("Arquivo") !=null && !(convertedParameters.get("Arquivo") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","Arquivo", "String", parameters.get("Arquivo").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("Arquivo") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","Arquivo"));
    }

    if(parameters.containsKey("type") && parameters.get("type") != null && parameters.get("type").get() != null) {
      convertedParameters.put("type", parameters.get("type").get());
      if(convertedParameters.get("type") !=null && !(convertedParameters.get("type") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","type", "String", parameters.get("type").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("type") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","type"));
    }
    if(convertedParameters.get("type") != null) {
      switch((String)convertedParameters.get("type")) {
        case "READONLY" : {

        } break;
        case "COMPOSE" : {

        } break;
        case "INSERT" : {

        } break;
        case "SEND" : {

        } break;
        case "LABELS" : {

        } break;
        case "METADATA" : {

        } break;
        case "MODIFY" : {

        } break;
        case "SETTINGS_BASIC" : {

        } break;
        case "SETTINGS_SHARING" : {

        } break;
        default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","type"));
      }
    }

    command.setSessionMap(sessionMap);
    try {
      command.action((String)convertedParameters.get("SessionName"),(String)convertedParameters.get("Arquivo"),(String)convertedParameters.get("type"));Optional<Value> result = Optional.empty();
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
