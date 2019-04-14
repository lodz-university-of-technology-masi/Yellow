class ApiCommunicator {

    constructor() {
        this.baseAddress = "https://localhost:5000/api/v1";
    }

    async signIn(userData) {
        let json = await this.httpPostAsync(
            `${this.baseAddress}/login`, JSON.stringify({
                username: userData.username,
                password: userData.password
            }));
        return JSON.parse(json);
    }

    async signUp(userData) {
        let json = await this.httpPostAsync(
            `${this.baseAddress}/register`, JSON.stringify({
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
        return new Promise((resolve, reject) => {
            let xmlHttp = new XMLHttpRequest();
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState == 4) {
                    if (xmlHttp.status == 200) {
                        resolve(xmlHttp.responseText);
                    }
                    else {
                        if (xmlHttp.status == 401 || xmlHttp.status == 403) {
                            NotificationManager.showError('Failed to authorize.');
                        }
                        else {
                            NotificationManager.showError('Failed to communicate with the server.');
                            reject();
                        }
                    }
                }
                xmlHttp.con
                xmlHttp.open(method, url, true);
                xmlHttp.setRequestHeader("Authorization", token);
                xmlHttp.setRequestHeader("Content-Type", "application/json");
                xmlHttp.send(body);
            }
        })
    };
}

