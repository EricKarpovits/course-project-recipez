package presenters.create_user_and_fridge;

import UI.startPageUI.startPageViewModelInterface;
import use_cases.create_user_and_fridge.CreateUserAndFridgeResponseModel;

public class CreateUserAndFridgeFormatter implements CreateUserAndFridgePresenter {

    private final startPageViewModelInterface viewModel;

    public CreateUserAndFridgeFormatter(startPageViewModelInterface viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public CreateUserAndFridgeResponseModel prepareSuccessView(CreateUserAndFridgeResponseModel responseModel) {
        this.viewModel.registration_success(responseModel.getCommonUser().getName());
        return responseModel;
    }

    @Override
    public CreateUserAndFridgeResponseModel prepareFailView(String error) {
        this.viewModel.registration_failure(error);
        return null;
    }
}
