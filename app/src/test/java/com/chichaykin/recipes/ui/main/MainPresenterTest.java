package com.chichaykin.recipes.ui.main;

import com.chichaykin.recipes.network.RecipeApi;
import com.chichaykin.recipes.network.SearchResponse;
import com.chichaykin.recipes.rx.ImmediateSchedulersRule;
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

public class MainPresenterTest {

    final static String QUERY = "cake";

    @Rule
    public final ImmediateSchedulersRule schedulers = new ImmediateSchedulersRule();

    @Mock
    RecipeApi api;

    @Mock
    MainView view;

    private MainPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter(api);
        presenter.attachView(view);
    }

    @Test
    public void testSearch() {
        SearchResponse response = new SearchResponse();
        when(api.search(eq(QUERY))).thenReturn(
                Observable.just(response));

        presenter.search(QUERY);

        verify(view).show(eq(response.getList()));
    }

    @Test
    public void testNetworkError() {
        when(api.search(eq(QUERY))).thenReturn(Observable.error(IOException::new));

        presenter.search(QUERY);

        verify(view).showErrorMessage(anyString());
    }
}
