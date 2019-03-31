class ApiCommunicator {

    constructor() {
        this.baseAddress = "https://localhost:5000/api";
    }

    async signIn(userData) {
        return true;
        
        let json = await this.httpPostAsync(
            `${this.baseAddress}/signIn`, JSON.stringify({
                username: userData.username,
                password: userData.password
            }));
        return JSON.parse(json);
    }

    async signUp(userData) {
        window.localStorage.setItem("authToken", "token");
        return true;

        let json = await this.httpPostAsync(
            `${this.baseAddress}/signUp`, JSON.stringify({
                email: userData.email,
                username: userData.username,
                password: userData.password
            }));
        return JSON.parse(json);
    }

    httpGetAsync(url) {
        return this.httpAsync(url, "GET", null);
    }

    httpPostAsync(url, body) {
        return this.httpAsync(url, "POST", body);
    }

    httpDeleteAsync(url) {
        return this.httpAsync(url, "DELETE", null);
    }

    httpAsync(url, method, body) {

        let token = window.localStorage.getItem("authToken");
        if (token == null) {
            new Noty({
                theme: 'metroui',
                type: 'warning',
                text: 'Access token has not been set.',
                timeout: false,
                layout: 'bottomRight'
            }).show();
            throw "No token is set"
        }

        return new Promise((resolve, reject) => {
            let xmlHttp = new XMLHttpRequest();
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState == 4) {
                    if (xmlHttp.status == 200) {
                        resolve(xmlHttp.responseText);
                    }
                    else {
                        if (xmlHttp.status == 401) {
                            new Noty({
                                theme: 'metroui',
                                type: 'warning',
                                text: 'Failed to authorize with provided token.',
                                timeout: 2000,
                                progressBar: true,
                                layout: 'bottomRight'
                            }).show();
                        }
                        else {
                            new Noty({
                                theme: 'metroui',
                                type: 'error',
                                text: 'Failed to communicate with the server.',
                                timeout: 2000,
                                progressBar: true,
                                layout: 'bottomRight'
                            }).show();
                        }

                        reject();
                    }
                }
            }
            xmlHttp.con
            xmlHttp.open(method, url, true);
            xmlHttp.setRequestHeader("Authorization", token);
            xmlHttp.setRequestHeader("Content-Type", "application/json");
            xmlHttp.send(body);
        });
    }
}
