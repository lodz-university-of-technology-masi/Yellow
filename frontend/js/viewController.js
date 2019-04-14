class ViewController {
    constructor(
        contentContainer,
        signInController,
        signUpController,
        testsController) {
        this.contentContainer = contentContainer;

        this.pages = {};
        this.pages[SignInController.tag] = new Page(this, signInController);
        this.pages[SignUpController.tag] = new Page(this, signUpController);
        this.pages[TestsController.tag] = new Page(this, testsController);

        while (contentContainer.firstChild) {
            contentContainer.removeChild(contentContainer.firstChild);
        }
    }

    navigate(pageTag) {
        let page = this.pages[pageTag.tag];

        this.setView(page);
        page.controller.navigatedTo();
        this.currentPage = page;
    }

    setView(page) {
        if (this.currentButton)
            this.currentButton.classList.toggle("selected")

        this.currentButton = page.button;
        this.currentButton.classList.toggle("selected");

        if (this.currentChild)
            this.contentContainer.removeChild(this.currentChild);

        this.contentContainer.appendChild(page.view);
        this.currentChild = page.view;
    }
}

class Page {
    constructor(viewController, pageController) {
        this.view = document.getElementById(`nav-${pageController.tag}-page`);
        this.button = document.getElementById(`nav-${pageController.tag}-button`);
        this.controller = pageController;
        this.parent = viewController;

        this.button.onclick = () => {
            this.parent.navigate(pageController);
        };
    }
}