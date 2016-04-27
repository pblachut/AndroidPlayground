package piotrek.logintest;

/**
 * Created by Admin on 2016-04-27.
 */
public class LoginManager implements ILoginInteractor{
    private String username;
    private ICredentialsStorage storage;
    private ILoginApi api;
    private ILoginView view;

    public LoginManager(ICredentialsStorage storage, ILoginApi api, ILoginView view){

        this.storage = storage;
        this.api = api;
        this.view = view;
    }

    @Override
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
