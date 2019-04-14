
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

/**
 * Facade for streamlined display of generic notifications.
 */
class NotificationManager {
    static showSuccess(text) {
        new Noty({
            theme: 'metroui',
            type: 'success',
            text: text,
            timeout: 2000,
            progressBar: true,
            layout: 'bottomRight'
        }).show();
    }

    static showError(text) {
        new Noty({
            theme: 'metroui',
            type: 'error',
            text: text,
            timeout: 2000,
            progressBar: true,
            layout: 'bottomRight'
        }).show();
    }

    static showWarning(text) {
        new Noty({
            theme: 'metroui',
            type: 'warning',
            text: text,
            timeout: 2000,
            progressBar: true,
            layout: 'bottomRight'
        }).show();
    }
}

Date.prototype.addHours = function (h) {
    this.setTime(this.getTime() + (h * 60 * 60 * 1000));
    return this;
}
