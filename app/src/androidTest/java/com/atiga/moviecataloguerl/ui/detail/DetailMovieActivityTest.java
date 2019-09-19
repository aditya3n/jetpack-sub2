package com.atiga.moviecataloguerl.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.atiga.moviecataloguerl.R;
import com.atiga.moviecataloguerl.model.MovieResultsItem;
import com.atiga.moviecataloguerl.utils.EspressoIdlingResource;
import com.atiga.moviecataloguerl.utils.FakeDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;

public class DetailMovieActivityTest {

    private MovieResultsItem dummyMovie = FakeDataDummy.generateDummyMovies().get(0);

    @Rule
    public ActivityTestRule<DetailActivity> activityRule = new ActivityTestRule<DetailActivity>(DetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailActivity.class);
            result.putExtra(DetailActivity.EXTRA_MOVIE_ID, dummyMovie.getId());
            return result;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void showMovieDetail(){
        onView(withId(R.id.toolbar_detail)).check(matches(isDisplayed()));
//        onView(allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar_detail))))
//                .check(matches(withText(dummyMovie.getTitle())));
        onView(withId(R.id.text_date)).check(matches(isDisplayed()));
//        onView(withId(R.id.text_date)).check(matches(withText(dummyMovie.getReleaseDate())));
        onView(withId(R.id.text_genre)).check(matches(isDisplayed()));
//        onView(withId(R.id.text_genre)).check(matches(withText(dummyMovie.getGenreText())));
        onView(withId(R.id.text_rating)).check(matches(isDisplayed()));
//        onView(withId(R.id.text_rating)).check(matches(withText(String.valueOf(dummyMovie.getVoteAverage()))));
        onView(withId(R.id.text_overview)).check(matches(isDisplayed()));
//        onView(withId(R.id.text_overview)).check(matches(withText(dummyMovie.getOverview())));
    }

}