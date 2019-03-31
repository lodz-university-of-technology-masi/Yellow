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
        if (await this.apiCommunicator.signIn({
            username: this.usernameTextBox.value,
            password: this.passwordTextBox.value
        })) {
            new Noty({
                theme: 'metroui',
                type: 'success',
                text: 'Successfully signed in.',
                timeout: 2000,
                progressBar: true,
                layout: 'bottomRight'
            }).show();

            viewController.navigateTests();
        }
    }
}