package Requests;

import ApplicationManager.MainApplication;
import Models.ExecuteData;
import Models.RegisterData;
import com.google.gson.*;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.testng.Assert;


public class SessionsRequests extends MainApplication {
    public RegisterData data;
    public ExecuteData execute_data;
    private int register_try_count = 0;

    public SessionsRequests(RegisterData data, ExecuteData executeData) {
        this.data = data;
        this.execute_data = executeData;
    }

    // Автотизация на сервере и получение токена
    public void authorisation() {
        String url_auth = data.getUrl_auth() + "/auth/vpbx";
        String auth_vpbx = RestAssured.given()
                .parameter("username", data.getUsername())
                .parameter("password", data.getPassword())
                .parameter("deviceId", data.getDevice_id())
                .parameter("app", data.getApplication())
                .post(url_auth).asString();

        System.out.println("Response auth_vpbx:\n" + auth_vpbx);
        JsonObject json_auth_token = JsonParser.parseString(auth_vpbx).getAsJsonObject();
        Assert.assertEquals(
                json_auth_token.getAsJsonObject().get("result").getAsInt(),
                1000,
                "Authorisation failed, result not 1000");

        data.withAuth_token(json_auth_token.get("auth_token").getAsString());
        data.withRefresh_token(json_auth_token.get("refresh_token").getAsString());

        // Дополнительный метод для получения ticket
        String url_ticket = data.getUrl_auth() + "/auth/ticket/step1";
        String ticket_response = RestAssured.given()
                .parameter("auth_token", data.getAuth_token())
                .parameter("refresh_token", data.getRefresh_token())
                .post(url_ticket).asString();

        JsonObject json_ticket_response = JsonParser.parseString(ticket_response).getAsJsonObject();
        data.withTicket(json_ticket_response.get("ticket").getAsString());

        System.out.println("Auth_ticket response:\n" + ticket_response);
        System.out.println("\nAuth_token:\n" + data.getAuth_token());
    }


    // Получение Credentials (пара логин и пароль)
    public void credentials(){
        String url = String.format("%s/credentials/sip?auth_token=%s", data.getUrl_auth(), data.getAuth_token());
        String credentials = RestAssured.get(url).asString();
        System.out.println("\nResponse credentials:\n" + credentials);
        JsonObject json_credentials = JsonParser.parseString(credentials).getAsJsonObject();
        Assert.assertEquals(
                json_credentials.getAsJsonObject().get("result").getAsInt(),
                1000,
                "Credentials failed, result not 1000");

        data.withCredentials(json_credentials.getAsJsonObject().get("data").getAsString());

    }

    public void startSession(){
        String url = data.getUrl_4talk() + "/startSession";
        String start_session = RestAssured.given()
                .auth()
                .preemptive()
                .basic(data.getAccount(), data.getHash())
                .parameter("status", data.getDefault_status())
                .parameter("textStatus", data.getTextStatus())
                .parameter("model", data.getModel())
                .parameter("os", data.getOs())
                .post(url).asString();
        System.out.println("\nResponse start_session:\n" + start_session);

        JsonObject response_start_session = JsonParser.parseString(start_session).getAsJsonObject();
        Assert.assertEquals(
                response_start_session.getAsJsonObject().get("statusCode").getAsInt(),
                200,
                "Start session failed, result not 200");

        data.withAbonent_id(response_start_session
                .getAsJsonObject("data")
                .getAsJsonObject("user")
                .getAsJsonObject("vcard")
                .getAsJsonObject("mangoExtra")
                .getAsJsonObject("general")
                .get("abonent_id").getAsString());

        data.withProduct_id (response_start_session
                .getAsJsonObject("data")
                .getAsJsonObject("user")
                .get("productId").getAsString());

        data.withLast_seen(response_start_session
                .getAsJsonObject("data")
                .getAsJsonObject("user")
                .getAsJsonObject("status")
                .get("lastSeen").getAsString());

        data.withLastSid(response_start_session
                .getAsJsonObject("data")
                .get("lastSid").getAsString());

        // По идеи не работает для карточки, где несколько номеров
        try {
            JsonObject numbers = response_start_session
                    .getAsJsonObject("data")
                    .getAsJsonObject("user")
                    .getAsJsonObject("vcard")
                    .getAsJsonObject("mangoExtra")
                    .getAsJsonObject("telephony")
                    .getAsJsonArray("numbers").get(0).getAsJsonObject();

            data.withNumber_id(numbers.get("number_id").getAsLong());

            data.withNumber(numbers.get("number").getAsString());

            data.withProtocol(numbers.get("protocol").getAsString());

        } catch (IndexOutOfBoundsException e){
            System.out.println("!!! WARNING:");
            e.printStackTrace();
        }

    }

    // Регистрация учетки и получение хеша авторизации
    public void registration() {
        String url = data.getUrl_4talk() + "/register";
        String register = RestAssured.given()
                .auth()
                .preemptive()
                .basic(data.getUsername(), data.getPassword())
                .parameter("deviceId", data.getDevice_id())
                .parameter("deviceName", data.getDevice_name())
                .parameter("os", data.getOs())
                .post(url).asString();
        System.out.println("\nResponse register:\n" + register);

        JsonObject json_account_hash = JsonParser.parseString(register).getAsJsonObject();
        int status_code = json_account_hash.getAsJsonObject().get("statusCode").getAsInt();

        // Рекурсия, если словили тротлинг
        if(status_code == 503 && register_try_count < 5){
            System.out.println("Register timeOut, try wait");
            register_try_count++;
            waiter(4000 + (register_try_count * 1000));
            registration();
        }

        Assert.assertEquals(
                status_code,
                200,
                "Registration failed, result not 200");

        data.withAccount(json_account_hash.getAsJsonObject().get("data").getAsJsonObject().get("account").getAsString());
        data.withHash(json_account_hash.getAsJsonObject().get("data").getAsJsonObject().get("hash").getAsString());
    }

    // Разрегистрация учетки
    public void unregister() {
        String url = data.getUrl_4talk() + "/removeDevice";
        String unregister = RestAssured.given()
                .auth()
                .preemptive()
                .basic(data.getAccount(), data.getHash())
                .parameter("deviceId", data.getDevice_id())
                .post(url).asString();
        System.out.println("\nResponse unregister:\n" + unregister);

        JsonObject json_account_hash = JsonParser.parseString(unregister).getAsJsonObject();
        Assert.assertEquals(
                json_account_hash.getAsJsonObject().get("statusCode").getAsInt(),
                200,
                "Registration failed, result not 200");
    }

    // EXECUTE-тище!!!!!!!
    // выполнять, исполнять, осуществлять, казнить, убивать по политическим мотивам
    public void execute(){
        String url = data.getUrl_4talk() + "/execute";
        String json = "{\"get-data\":[\"vcards\",\"devices\",\"confrooms\",\"personal\"],\"withAvatars\":0,\"from_ts\":0}";
        String execute_response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(data.getAccount(), data.getHash())
                .contentType(ContentType.JSON)
                .body(json)
                .post(url).asString();
        System.out.println("\nResponse execute done!\n");
        int status_cod = 0;
        JsonObject execute_json = new JsonObject();

        try {
            execute_json = JsonParser.parseString(execute_response).getAsJsonObject();
            status_cod = execute_json.get("statusCode").getAsInt();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            execute_json.addProperty("response", execute_response);
        }


        if(status_cod == 200){
            JsonArray vcards_arr = execute_json.getAsJsonObject("data").getAsJsonArray("vcards");
            JsonArray devices_arr = execute_json.getAsJsonObject("data").getAsJsonArray("devices");
            JsonArray confrooms_arr = execute_json.getAsJsonObject("data").getAsJsonArray("confrooms");
            JsonArray personal_arr = execute_json.getAsJsonObject("data").getAsJsonArray("personal");

            execute_data
                    .withPersonal(createSetFromJsonArray(personal_arr))
                    .withConfrooms(createSetFromJsonArray(confrooms_arr))
                    .withDevices(createSetFromJsonArray(devices_arr))
                    .withVcards(createSetFromJsonArray(vcards_arr));

        } else {
            Assert.fail("ERROR# Request execute failed!");
        }
    }



    public JsonObject logOut(){
        String url = "/logOut";
        String json = "{}";
        return post_response(json, url, data);
    }

    public JsonObject isServiceEnabled(String service_code){
        String url = "/isServiceEnabled";
        String json = "{\"serviceCode\": \""+service_code+"\"}";
        return post_response(json, url, data);
    }

    public Response serverTime(){
        String url = data.getUrl_4talk() + "/time";
        return get_response(url);
    }

    // Уникальный метод для Минского API.
    public JsonObject check(String auth_token){
        String url = data.getUrl_auth() + "/check";
        String get_request = RestAssured.given().parameter("auth_token", auth_token).get(url).asString();
        System.out.println("\nResponse for " + url + ":\n" + get_request);
        return JsonParser.parseString(get_request).getAsJsonObject();
    }

    public JsonObject pollEvents(String lastSid){
        String url = "/pollEvents";
        String json = "{\"lastSid\": \""+lastSid+"\"}";
        return post_response(json, url, data);
    }

}
