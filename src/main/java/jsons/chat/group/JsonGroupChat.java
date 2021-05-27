package jsons.chat.group;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonGroupChat {

    public String createGroupChat(String local_id){
        JsonArray members_arr = new JsonArray();
        members_arr.add( "400613755@mtalker.mangotele.com");
        members_arr.add( "401530378@mtalker.mangotele.com");

        JsonObject main_json = new JsonObject();
        main_json.addProperty("description","The_Group_chat_for_authotest");
        main_json.addProperty("isChannel",false);
        main_json.addProperty("localId",local_id);
        main_json.add("members", members_arr);
        main_json.addProperty("topic","Group authotest" + System.currentTimeMillis());

        return main_json.toString();
    }

    public String editGroupChat(String account){
        JsonObject main_json = new JsonObject();
        main_json.addProperty("account", account);
        main_json.addProperty("topic", "Group_chat_authotest" + System.currentTimeMillis()/2);
        main_json.addProperty("description", "Changed description");
        return main_json.toString();
    }

    public String messageSend(String channel_id, String local_id){
        JsonObject messages_into = new JsonObject();
        messages_into.addProperty("localId",local_id);
        JsonObject payload_into = new JsonObject();
        payload_into.addProperty("body","New message into new group chat");
        messages_into.add("payload", payload_into);
        messages_into.addProperty("to", channel_id);
        messages_into.addProperty("type","text");

        JsonArray messages_arr = new JsonArray();
        messages_arr.add(messages_into);

        JsonObject main_json = new JsonObject();
        main_json.add("messages", messages_arr);
        return main_json.toString();
    }

    public String messageSend(String channel_id, String local_id, String body){
        JsonObject messages_into = new JsonObject();
        messages_into.addProperty("localId",local_id);
        JsonObject payload_into = new JsonObject();
        payload_into.addProperty("body",body);
        messages_into.add("payload", payload_into);
        messages_into.addProperty("to", channel_id);
        messages_into.addProperty("type","text");

        JsonArray messages_arr = new JsonArray();
        messages_arr.add(messages_into);

        JsonObject main_json = new JsonObject();
        main_json.add("messages", messages_arr);
        return main_json.toString();
    }

    public String removeGroupChat(String account){
        JsonObject main_json = new JsonObject();
        main_json.addProperty("account", account);
        return main_json.toString();
    }

    public String chatOnline(String account){
        JsonObject json_chat_online = new JsonObject();
        json_chat_online.addProperty("account", account);
        return json_chat_online.toString();
    }

    public String chatOffline(String account){
        JsonObject json_chat_online = new JsonObject();
        json_chat_online.addProperty("account", account);
        return json_chat_online.toString();
    }

    public String chatMuteOn(String account){
        JsonObject json_chat_mute_on = new JsonObject();
        json_chat_mute_on.addProperty("account",account);
        json_chat_mute_on.addProperty("mute", true);
        return json_chat_mute_on.toString();
    }

    public String chatMuteOff(String account){
        JsonObject json_chat_mute_on = new JsonObject();
        json_chat_mute_on.addProperty("account",account);
        json_chat_mute_on.addProperty("mute", false);
        return json_chat_mute_on.toString();
    }
}
