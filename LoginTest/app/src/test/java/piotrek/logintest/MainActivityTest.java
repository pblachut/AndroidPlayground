package piotrek.logintest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    MainActivity activity;

    @Before
    public void setUp() throws Exception {
         activity = Robolectric.setupActivity(MainActivity.class);

    }

    @Test
    public void should_have_username_textView() throws Exception {
        // prepare


        // run


        // assert
        assertNotNull(activity.usernameEditTextView);
    }

    @Test
    public void should_be_possible_to_log_in_with_mocked_interactor() throws Exception {
        // prepare
        activity.usernameEditTextView.setText("username");
        activity.passwordEditTextView.setText("password");

        activity.interactor = mock(ILoginInteractor.class);

        // run
        activity.loginButtton.performClick();

        // assert
        verify(activity.interactor).login("username", "password");
    }

    @Test
    public void should_show_toast_on_too_short_username() throws Exception {
        // prepare
        activity.usernameEditTextView.setText("a");
        activity.passwordEditTextView.setText("password");

        // run
        activity.loginButtton.performClick();

        // assert
        assertEquals("username_too_short", ShadowToast.getTextOfLatestToast());
    }
}