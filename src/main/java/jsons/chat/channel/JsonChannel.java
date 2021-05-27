package jsons.chat.channel;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonChannel {

    public String createChannel(String local_id){
        JsonArray members_arr = new JsonArray();
        members_arr.add( "401809841@mtalker.mangotele.com");
        members_arr.add( "401530378@mtalker.mangotele.com");

        JsonObject main_json = new JsonObject();
        main_json.addProperty("channelType","open");
        main_json.addProperty("description","The_channel_for_authotest");
        main_json.addProperty("isChannel",true);
        main_json.addProperty("localId",local_id);
        main_json.add("members", members_arr);
        main_json.addProperty("topic","Channel_authotest" + System.currentTimeMillis()/2);

        return main_json.toString();
    }

    public String editChannel(String account){
        // {"account":"{a8b60a44-3ecf-429e-b510-453b8db40a06}@conference.mtalker.mangotele.com","topic":"Chanel 19384u2i1o3ufudyd","description":""}
        JsonObject main_json = new JsonObject();
        main_json.addProperty("account", account);
        main_json.addProperty("topic", "Channel_authotest" + System.currentTimeMillis()/2);
        main_json.addProperty("description", "Changed description");
        return main_json.toString();
    }

    public String channelSetType(String account){
        JsonObject main_json = new JsonObject();
        main_json.addProperty("account", account);
        main_json.addProperty("channelType","closed");
        return main_json.toString();
    }

    public String removeChanel(String account){
        JsonObject main_json = new JsonObject();
        main_json.addProperty("account", account);
        return main_json.toString();
    }

    public String messageSend(String channel_id, String local_id){
        // {"messages":[{"localId":"85b89737998c48718786392da7aeb68a","payload":{"body":"Fjfiiffi"},"to":"{f36630a5-f9be-4c3c-ad29-2c7a85723b8d}@conference.mtalker.mangotele.com","type":"text"}]}
        JsonObject messages_into = new JsonObject();
        messages_into.addProperty("localId",local_id);
        JsonObject payload_into = new JsonObject();
        payload_into.addProperty("body","New message into new channel");
        messages_into.add("payload", payload_into);
        messages_into.addProperty("to", channel_id);
        messages_into.addProperty("type","text");

        JsonArray messages_arr = new JsonArray();
        messages_arr.add(messages_into);

        JsonObject main_json = new JsonObject();
        main_json.add("messages", messages_arr);
        return main_json.toString();
    }

    public String addMembers(String account, String members){
        // {"account":"{f36630a5-f9be-4c3c-ad29-2c7a85723b8d}@conference.mtalker.mangotele.com","add":["401809841@mtalker.mangotele.com"]}
        JsonArray add_arr = new JsonArray();
        add_arr.add(members);

        JsonObject main_json = new JsonObject();
        main_json.addProperty("account", account);
        main_json.add("add", add_arr);
        return main_json.toString();
    }

    public String removeMembers(String account, String members){
        // {"account":"{f36630a5-f9be-4c3c-ad29-2c7a85723b8d}@conference.mtalker.mangotele.com","remove":["401809841@mtalker.mangotele.com"]}
        JsonArray add_arr = new JsonArray();
        add_arr.add(members);

        JsonObject main_json = new JsonObject();
        main_json.addProperty("account", account);
        main_json.add("remove", add_arr);
        return main_json.toString();
    }

}
