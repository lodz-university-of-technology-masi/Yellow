class SignUpController {
    constructor(apiCommunicator) {
        this.apiCommunicator = apiCommunicator;
    }

    navigatedTo() {
        if (this.initialized)
            return;

        this.initialized = true;

        this.usernameTextBox = document.getElementById("signup-username-textbox");
        this.passwordTextBox = document.getElementById("signup-password-textbox");
        this.passwordRepeatTextBox = document.getElementById("signup-password-repeat-textbox");
        this.signUpButton = document.getElementById("signup-button");

        this.signUpButton.onclick = () => {
            this.signUp();
        }
    }

    async signUp() {
        if (this.usernameTextBox.value.length >= 6 &&
            this.passwordTextBox.value.length >= 6 &&
            this.passwordTextBox.value == this.passwordRepeatTextBox.value) {

            try {
                await this.apiCommunicator.signUp({
                    username: this.usernameTextBox.value,
                    password: this.passwordTextBox.value
                });
                NotificationManager.showSuccess('You have successfully signed up! Proceed to sign in.')

                viewController.navigate(SignInController);
            } catch {
                //notification displayed by ApiCommunicator class
                //no need for customization
            }
        } else {
            NotificationManager.showWarning('Make sure that both password and username are longer than 6 characters and passwords match.');
        }
    }
}