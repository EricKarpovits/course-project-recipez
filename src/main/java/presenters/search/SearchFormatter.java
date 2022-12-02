package presenters.search;

import UI.resultPage.ResultsPageViewModelInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.recipe.CommonRecipeFactory;
import entities.recipe.Recipe;
import entities.recipe.RecipeFactory;
import use_cases.searchUseCase.SearchResponseModel;

import java.util.ArrayList;

public class SearchFormatter implements SearchPresenter {
    /**
     * Format for RecipeBuilder response for success view and fail view
     */
    private final ResultsPageViewModelInterface viewModelInterface;

    public SearchFormatter(ResultsPageViewModelInterface viewModelInterface) {
        this.viewModelInterface = viewModelInterface;
    }

    @Override
    public SearchResponseModel prepareSuccessView(SearchResponseModel searchResponse) {
        RecipeFactory factory = new CommonRecipeFactory();
        ArrayList<Recipe> formattedRecipes = new ArrayList<>();

        JsonObject jsonObject = JsonParser.parseString(searchResponse.getApiOutput()).getAsJsonObject();
        JsonArray recipes = jsonObject.get("hits").getAsJsonArray();
        int count = jsonObject.get("to").getAsInt();

        for(int j = 0; j < count; j++) {
            JsonObject n = recipes.get(j).getAsJsonObject();
            JsonObject p = n.get("recipe").getAsJsonObject();
            formattedRecipes.add(factory.create(p.get("label").getAsString(),p.get("url").getAsString(),p.get("image")));

        }

        this.viewModelInterface.resultsSuccess(formattedRecipes);
        System.out.println(formattedRecipes);
        return searchResponse;
    }

    @Override
    public SearchResponseModel prepareFailView(String error) {
        this.viewModelInterface.resultsFailure(error);
        System.out.println(error);
        return null;
    }
}
