package Requests;

import ApplicationManager.MainApplication;
import com.jayway.restassured.response.Response;
import jsons.chat.channel.JsonChannel;
import jsons.chat.group.JsonGroupChat;
import jsons.chat.personal.JsonPersonalChat;
import Models.RegisterData;
import com.google.gson.JsonObject;

public class ChatRequests extends MainApplication {
    
    RegisterData data;

    public ChatRequests(RegisterData data) {
        this.data = data;
    }

    private final JsonChannel channel = new JsonChannel();
    private final JsonPersonalChat personal = new JsonPersonalChat();
    private final JsonGroupChat group = new JsonGroupChat();

    public JsonObject sendMessage(String account, String local_id, String body){
        String json = group.messageSend(account, local_id, body);
        String url = "/message/send";
        return post_response(json, url, data);
    }

    public JsonObject channelCreate(String local_id){
        String json = channel.createChannel(local_id);
        String url = "/chat/create";
        return post_response(json, url, data);
    }

    public JsonObject channelUserOffline(String account){
        String json = String.format("{\"account\": \"%s\"}", account);
        String url = "/chat/offline";
        return post_response(json, url, data);
    }

    public JsonObject channelUserOnline(String account){
        String json = String.format("{\"account\": \"%s\"}", account);
        String url = "/chat/online";
        return post_response(json, url, data);
    }

    public JsonObject channelEdit(String account){
        String json = channel.editChannel(account);
        String url = "/chat/edit";
        return post_response(json, url, data);
    }

    public JsonObject channelSetType(String account){
        String json = channel.channelSetType(account);
        String url = "/chat/setChannelType";
        return post_response(json, url, data);
    }

    public JsonObject channelRemove(String account){
        String json = channel.removeChanel(account);
        String url = "/chat/removeChannel";
        return post_response(json, url, data);
    }

    public JsonObject channelSendMessage(String account, String local_id){
        String json = channel.messageSend(account, local_id);
        String url = "/message/send";
        return post_response(json, url, data);
    }

    public JsonObject channelAddMembers(String account, String members){
        String json = channel.addMembers(account, members);
        String url = "/chat/modify";
        return post_response(json, url, data);
    }

    public JsonObject channelRemoveMembers(String account, String members){
        String json = channel.removeMembers(account, members);
        String url = "/chat/modify";
        return post_response(json, url, data);
    }

    public JsonObject groupChatCreate(String local_id){
        String json = group.createGroupChat(local_id);
        String url = "/chat/create";
        return post_response(json, url, data);
    }

    public JsonObject groupChatEdit(String account){
        String json = group.editGroupChat(account);
        String url = "/chat/edit";
        return post_response(json, url, data);
    }

    public JsonObject groupChatSendMessage(String account, String local_id){
        String json = group.messageSend(account, local_id);
        String url = "/message/send";
        return post_response(json, url, data);
    }

    public JsonObject groupChatRemove(String account){
        String json = group.removeGroupChat(account);
        String url = "/chat/removeChannel";
        return post_response(json, url, data);
    }

    public JsonObject personalChatCreate(String local_id, String account){
        String json = personal.createPersonalChat(local_id, account);
        String url = "/message/send";
        return post_response(json, url, data);
    }

    public JsonObject personalChatRemove(String account){
        String json = personal.removePersonalChat(account);
        String url = "/chat/remove";
        return post_response(json, url, data);
    }

    public JsonObject chatRemoveAll(){
        String json = personal.chatRemoveAll();
        String url = "/chat/removeAll";
        return post_response(json, url, data);
    }

    public JsonObject messageHistoryForAccount(String account){
        String json = personal.messageHistoryForAccount(account);
        String url = "/message/history";
        return post_response(json, url, data);
    }

    public JsonObject messageHistoryForAllChats(){
        String json = personal.messageHistoryForAllChats();
        String url = "/message/history";
        return post_response(json, url, data);
    }

    public JsonObject messageHistorySync(){
        String json = "{\"sinceId\":\"389745629874365\",\"limit\":2}";
        String url = "/message/sync";
        return post_response(json, url, data);
    }

    public JsonObject smsListGet(){
        String json = personal.smsListGet();
        String url = "/smslist/get";
        return post_response(json, url, data);
    }

    public JsonObject messageNotifyRead(String account){
        String json = personal.messageNotifyRead(account);
        String url = "/message/notifyRead";
        return post_response(json, url, data);
    }

    public JsonObject chatRemoveAvatar(String account){
        String json = personal.chatRemoveAvatar(account);
        String url = "/chat/removeAvatar";
        return post_response(json, url, data);
    }

    public JsonObject chatUploadAvatar(String account, String file_name, String file_extension){
        String url = "/chat/uploadAvatar";
        return post_response(file_name, file_extension, url, data, account);
    }

    public Response getAvatar(String avatar_id){
        String url = data.getUrl_fs() + "/avatars/" + avatar_id;
        return get_response(url);
    }

    public JsonObject chatOnline(String account){
        String url = "/chat/online";
        String json = group.chatOnline(account);
        return post_response(json,url,data);
    }

    public JsonObject chatOffline(String account){
        String url = "/chat/offline";
        String json = group.chatOffline(account);
        return post_response(json,url,data);
    }

    public JsonObject chatMuteOn(String account){
        String url = "/chat/mute";
        String json = group.chatMuteOn(account);
        return post_response(json,url,data);
    }

    public JsonObject chatMuteOff(String account){
        String url = "/chat/mute";
        String json = group.chatMuteOff(account);
        return post_response(json,url,data);
    }

    public JsonObject messageEdit(String account, String sid, String localId){
        String url = "/message/edit";
        String json = personal.messageEdit(account, sid, localId);
        return post_response(json,url,data);
    }

    public JsonObject messageSendSms(String local_id){
        String url = "/message/sendSms";
        String json = personal.messageSendSms(local_id);
        return post_response(json,url,data);
    }

    public JsonObject messageResendSms(String sid){
        String url = "/message/resendSms";
        String json = personal.messageResendSms(sid);
        return post_response(json,url,data);
    }

    public JsonObject messageResendFax(String sid){
        String url = "/message/resendFax";
        String json = personal.messageResendFax(sid);
        return post_response(json,url,data);
    }

    public JsonObject messageRemove(String account, String sid){
        String url = "/message/remove";
        String json = personal.messageRemove(account, sid);
        return post_response(json,url,data);
    }

    public JsonObject messageHistory(String account){
        String url = "/message/history";
        String json = personal.messageHistory(account);
        return post_response(json,url,data);
    }

    public JsonObject messageNotifyTyping(String account){
        String url = "/message/notifyTyping";
        String json = personal.messageNotifyTyping(account);
        return post_response(json,url,data);
    }

    public JsonObject messageNotifyDelivered(String account, String sid){
        String url = "/message/notifyDelivered";
        String json = personal.messageNotifyDelivered(account, sid);
        return post_response(json,url,data);
    }

    public JsonObject messageNotifyReadAll(String account, String sid){
        String url = "/message/notifyReadAll";
        String json = personal.messageNotifyReadAll(account, sid);
        return post_response(json,url,data);
    }

    public JsonObject messageForward(String to, String sid, String local_id){
        String url = "/message/forward";
        String json = personal.messageForward(to, sid, local_id);
        return post_response(json,url,data);
    }

    public JsonObject messageSearchInPersonalChat(String account){
        String url = "/message/search";
        String json = personal.messageSearchInPersonalChat(account);
        return post_response(json,url,data);
    }

    public JsonObject messageRemoveByType(String account, String type){
        String url = "/message/forward";
        String json = personal.messageRemoveByType(account, type);
        return post_response(json,url,data);
    }

}
