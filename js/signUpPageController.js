class SignUpController {
    constructor(apiCommunicator) {
        this.apiCommunicator = apiCommunicator;
    }

    navigatedTo() {
        if (this.initialized)
            return;

        this.initialized = true;

        this.emailTextBox = document.getElementById("signup-email-textbox");
        this.usernameTextBox = document.getElementById("signup-username-textbox");
        this.passwordTextBox = document.getElementById("signup-password-textbox");
        this.signUpButton = document.getElementById("signup-button");

        this.signUpButton.onclick = () => {
            this.signUp();
        }
    }

    async signUp() {
        if (await this.apiCommunicator.signUp({
            username: this.emailTextBox.value,
            username: this.usernameTextBox.value,
            password: this.passwordTextBox.value
        })) {
            new Noty({
                theme: 'metroui',
                type: 'success',
                text: 'Successfully signed up.',
                timeout: 2000,
                progressBar: true,
                layout: 'bottomRight'
            }).show();

            viewController.navigateSignIn();
        }
    }
}