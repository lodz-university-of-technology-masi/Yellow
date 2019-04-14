class SignInController {
    constructor(apiCommunicator) {
        this.apiCommunicator = apiCommunicator;

        this.initialized = false;
    }

    navigatedTo() {
        if (this.initialized)
            return;

        this.initialized = true;

        this.usernameTextBox = document.getElementById("signin-username-textbox");
        this.passwordTextBox = document.getElementById("signin-password-textbox");
        this.signInButton = document.getElementById("signin-button");
        this.signUpButton = document.getElementById("signin-signup-button");

        this.signInButton.onclick = () => {
            this.signIn();
        }

        this.signUpButton.onclick = () => {
            viewController.navigateSignUp();
        }
    }

    async signIn() {
        if (this.usernameTextBox.value.length >= 6 &&
            this.passwordTextBox.value.length >= 6) {
            try {
                let result = await this.apiCommunicator.signIn({
                    username: this.usernameTextBox.value,
                    password: this.passwordTextBox.value
                });

                if (result.valid) {
                    window.localStorage.setItem("auth-token", result.username);
                    NotificationManager.showSuccess('Successfully signed in!');

                    viewController.navigate(TestsController);
                } else {
                    NotificationManager.showError('Failed to sign in.')
                }
            } catch {
                //notification displayed by ApiCommunicator class
                //no need for customization
            }
        } else {
            NotificationManager.showWarning('Make sure that both password and username are longer than 6 characters.');
        }
    }
}