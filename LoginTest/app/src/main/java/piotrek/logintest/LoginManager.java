package piotrek.logintest;

/**
 * Created by Admin on 2016-04-27.
 */
public class LoginManager {
    private String username;
    private ICredentialsStorage storage;
    private ILoginApi api;

    public LoginManager(ICredentialsStorage storage, ILoginApi api){

        this.storage = storage;
        this.api = api;
    }

    public void login(String username, String password) {

        String result = api.login(username, password);

        if ("OK".equals(result)){
            storage.storeUsername(username);
            this.username = username;
        }


    }

    public String getLoggedUser() {

        return username;
    }
}
