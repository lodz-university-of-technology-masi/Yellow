
let apiCommunicator;
let viewController;
let signInController;
let signUpController;
let slotsController;

document.addEventListener("DOMContentLoaded", function (ev) {
    apiCommunicator = new ApiCommunicator();
    signInController = new SignInController(apiCommunicator);
    signUpController = new SignUpController(apiCommunicator);

    viewController = new ViewController(
        document.getElementById("content"),
        signInController,
        signUpController);

    viewController.navigateSignIn();
});


Date.prototype.addHours = function (h) {
    this.setTime(this.getTime() + (h * 60 * 60 * 1000));
    return this;
}
