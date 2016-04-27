package piotrek.logintest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.concurrent.atomic.AtomicBoolean;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Admin on 2016-04-27.
 */
public class LoginManagerTests {

    private LoginManager sut;
    private ICredentialsStorage credentialsStorage;
    private ILoginApi api;
    private ILoginView view;

    @Test
    public void should_be_possible_to_create_sut(){
        // prepare
        // run
        // assert
        assertNotNull(sut);
    }

    @Test
    public void should_be_possible_to_login() throws Exception {
        // prepare
        // run
        sut.login("username", "password");

        // assert
        assertEquals("username", sut.getLoggedUser());
    }


    @Test
    public void should_store_username_in_storage_with_mock() throws Exception {
        // prepare
        // run
        sut.login("username", "password");

        // assert
        verify(credentialsStorage).storeUsername("username");
    }

    @Test
    public void should_not_store_username_on_invalid_password() throws Exception {
        // prepare
        when(api.login("username", "invalid"))
                .thenReturn("NOT_OK");

        // run
        sut.login("username", "invalid");

        // assert
        verify(credentialsStorage, never()).storeUsername("username");

    }

    @Test
    public void should_show_error_received_from_api() throws Exception {
        // prepare



        // run

        // assert
    }

    @Before
    public void setUp() throws Exception {
        credentialsStorage = mock(ICredentialsStorage.class);
        api = mock(ILoginApi.class);
        when(api.login(anyString(), anyString()))
                .thenReturn("OK");

        sut = new LoginManager(credentialsStorage, api, view);
    }
}
