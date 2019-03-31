class ViewController {
    constructor(
        contentContainer,
        signInController,
        signUpController) {
            
        this.contentContainer = contentContainer;
        this.signInController = signInController;
        this.signUpController = signUpController;

        this.signUpView = document.getElementById("signup-page");
        this.signInView = document.getElementById("signin-page");
        this.testsView = document.getElementById("tests-page");

        this.signInButton = document.getElementById("button-signin");
        this.signUpButton = document.getElementById("button-signup");
        this.testsButton = document.getElementById("button-tests");

        this.setUpNavButtons();

        while (contentContainer.firstChild) {
            contentContainer.removeChild(contentContainer.firstChild);
        }
    }

    navigateSignIn() {
        this.setView(this.signInView, this.signInButton);
        this.signInController.navigatedTo();
        this.currentController = this.signInController;
    }

    navigateSignUp() {
        this.setView(this.signUpView, this.signUpButton);
        this.signUpController.navigatedTo();
        this.currentController = this.signUpController;
    }

    navigateTests() {
        this.setView(this.testsView, this.testsButton);
        this.slotsController.navigatedTo();
        this.currentController = this.slotsController;
    }

    setView(view, button) {
        if (this.currentButton)
            this.currentButton.classList.toggle("selected")

        this.currentButton = button;
        button.classList.toggle("selected");

        if (this.currentChild)
            this.contentContainer.removeChild(this.currentChild);

        this.contentContainer.appendChild(view);
        this.currentChild = view;
    }

    setUpNavButtons() {
        this.signInButton.onclick = () => {
            this.navigateSignIn();
        };

        this.signUpButton.onclick = () => {
            this.navigateSignUp();
        };

        this.testsButton.onclick = () => {
            this.navigateTests();
        };
    }
}