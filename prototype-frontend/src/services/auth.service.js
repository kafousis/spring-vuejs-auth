import axios from 'axios';

class AuthService {

    hello() { return axios.get('hello') }

    jwtLogin(creds) { return axios.post('login', creds) }

    formLogin(creds) {

        var bodyFormData = new FormData();
        bodyFormData.append('username', creds.username);
        bodyFormData.append('password', creds.password);

        const headers = { "Content-Type": "application/x-www-form-urlencoded" };
        return axios.post('login', bodyFormData, headers)
    }

    getCsrfToken(){
        return axios.get('csrf/token');
    }

    //getAuthUser(username) { return axios.get('api/users/username=' + username) }
}

export default new AuthService();