package com.chichaykin.recipes.ui.detail;

import com.chichaykin.recipes.network.GetResponse;
import com.chichaykin.recipes.network.Recipe;
import com.chichaykin.recipes.network.RecipeApi;
import com.chichaykin.recipes.rx.ImmediateSchedulersRule;
import com.chichaykin.recipes.ui.main.MainPresenter;
import com.chichaykin.recipes.ui.main.MainView;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailPresenterTest {

    final static String TEST_ID = "cake";

    @Rule
    public final ImmediateSchedulersRule schedulers = new ImmediateSchedulersRule();

    @Mock
    RecipeApi api;

    @Mock
    DetailView view;

    private DetailPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new DetailPresenter(api);
        presenter.attachView(view);
    }

    @Test
    public void testSearch() {
        GetResponse response = new GetResponse();
        response.setRecipe(new Recipe());

        when(api.get(eq(TEST_ID))).thenReturn(Observable.just(response));

        presenter.subscribeToIngredients(TEST_ID);

        verify(view).show(eq(response.getRecipe().getIngredients()));
    }

    @Test
    public void testNetworkError() {
        when(api.get(eq(TEST_ID))).thenReturn(Observable.error(IOException::new));

        presenter.subscribeToIngredients(TEST_ID);

        verify(view).showError(anyString());
    }
}
