
let apiCommunicator;
let viewController;
let signInController;
let signUpController;
let testsController;

document.addEventListener("DOMContentLoaded", function (ev) {
    apiCommunicator = new ApiCommunicator();
    signInController = new SignInController(apiCommunicator);
    signUpController = new SignUpController(apiCommunicator);
    signUpController = new SignUpController(apiCommunicator);
    testsController = new TestsController(apiCommunicator);

    viewController = new ViewController(
        document.getElementById("content"),
        signInController,
        signUpController,
        testsController);

    viewController.navigate(SignInController);
});
