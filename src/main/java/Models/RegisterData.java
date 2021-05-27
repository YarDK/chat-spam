package Models;

public class RegisterData {

    private String username;
    private String password;
    private String device_id; // Любое 16-битное число общей длиной 32 символа
    private String device_name;
    private String os;
    private String default_status; // Status Online
    private String textStatus; // Пустое передается, не понятно зачем оно вообще нужно
    private String model; // Скорее всего опционалный параметр
    private String application;

    private String url_auth;
    private String url_4talk;
    private String url_mobigate;
    private String url_apivks;
    private String url_clientapi;
    private String url_fs;
    private String url_ab;

    private String account;
    private String hash;
    private String auth_token;
    private String refresh_token;
    private String ticket;

    private String abonent_id;
    private String product_id;
    private String last_seen;
    private long number_id;
    private String number;
    private String protocol;
    private String lastSid;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDevice_id() {
        return device_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public String getOs() {
        return os;
    }

    public String getDefault_status() {
        return default_status;
    }

    public String getTextStatus() {
        return textStatus;
    }

    public String getModel() {
        return model;
    }

    public String getApplication() {
        return application;
    }

    public String getUrl_auth() {
        return url_auth;
    }

    public String getUrl_4talk() {
        return url_4talk;
    }

    public String getUrl_mobigate() {
        return url_mobigate;
    }

    public String getUrl_apivks() {
        return url_apivks;
    }

    public String getUrl_clientapi() {
        return url_clientapi;
    }

    public String getUrl_fs() {
        return url_fs;
    }

    public String getUrl_ab() {
        return url_ab;
    }

    public String getAccount(){
        return account;
    }

    public String getHash() {
        return hash;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getTicket() {
        return ticket;
    }

    public String getAbonent_id() {
        return abonent_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getLast_seen() {
        return last_seen;
    }

    public long getNumber_id() {
        return number_id;
    }

    public String getNumber() {
        return number;
    }

    public String getProtocol(){
        return protocol;
    }

    public String getLastSid() {
        return lastSid;
    }

    public RegisterData withUsername(String username) {
        this.username = username;
        return this;
    }

    public RegisterData withPassword(String password) {
        this.password = password;
        return this;
    }

    public RegisterData withDevice_id(String device_id) {
        this.device_id = device_id;
        return this;
    }

    public RegisterData withDevice_name(String device_name) {
        this.device_name = device_name;
        return this;
    }

    public RegisterData withOs(String os) {
        this.os = os;
        return this;
    }

    public RegisterData withDefault_status(String default_status) {
        this.default_status = default_status;
        return this;
    }

    public RegisterData withTextStatus(String textStatus) {
        this.textStatus = textStatus;
        return this;
    }

    public RegisterData withModel(String model) {
        this.model = model;
        return this;
    }

    public RegisterData withApplication(String application) {
        this.application = application;
        return this;
    }

    public RegisterData withUrl_auth(String url_auth) {
        this.url_auth = url_auth;
        return this;
    }

    public RegisterData withUrl_4talk(String url_4talk) {
        this.url_4talk = url_4talk;
        return this;
    }

    public RegisterData withUrl_mobigate(String url_mobigate) {
        this.url_mobigate = url_mobigate;
        return this;
    }

    public RegisterData withUrl_apivks(String url_apivks) {
        this.url_apivks = url_apivks;
        return this;
    }

    public RegisterData withUrl_clientapi(String url_clientapi) {
        this.url_clientapi = url_clientapi;
        return this;
    }

    public RegisterData withUrl_fs(String url_fs) {
        this.url_fs = url_fs;
        return this;
    }

    public RegisterData withUrl_ab(String url_ab) {
        this.url_ab = url_ab;
        return this;
    }

    public RegisterData withAccount(String account){
        this.account = account;
        return this;
    }

    public RegisterData withHash(String hash) {
        this.hash = hash;
        return this;
    }

    public RegisterData withAuth_token(String auth_token) {
        this.auth_token = auth_token;
        return this;
    }

    public RegisterData withRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
        return this;
    }

    public RegisterData withTicket(String ticket){
        this.ticket = ticket;
        return this;
    }


    public RegisterData withCredentials(String credentials){
        this.username = credentials.split(":")[0];
        this.password = credentials.split(":")[1];
        return this;
    }

    public RegisterData withAbonent_id(String abonent_id) {
        this.abonent_id = abonent_id;
        return this;
    }

    public RegisterData withProduct_id(String product_id) {
        this.product_id = product_id;
        return this;
    }

    public RegisterData withLast_seen(String last_seen) {
        this.last_seen = last_seen;
        return this;
    }

    public RegisterData withNumber_id(long number_id) {
        this.number_id = number_id;
        return this;
    }

    public RegisterData withNumber(String number) {
        this.number = number;
        return this;
    }

    public RegisterData withProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public RegisterData withLastSid(String lastSid) {
        this.lastSid = lastSid;
        return this;
    }
}
