package com.atiga.moviecataloguerl.ui.movie;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import com.atiga.moviecataloguerl.R;
import com.atiga.moviecataloguerl.testing.SingleFragmentActivity;
import com.atiga.moviecataloguerl.utils.EspressoIdlingResource;
import com.atiga.moviecataloguerl.utils.RecyclerViewItemCountAssertion;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MovieFragmentTest {
    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);

    private MovieFragment movieFragment = new MovieFragment();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityRule.getActivity().setFragment(movieFragment);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void getAllMovies(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movie)).check(new RecyclerViewItemCountAssertion(20));
    }
}