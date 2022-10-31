import axios from 'axios';

class AuthService {

    getCsrfToken(){ return axios.get('csrf/token'); }

    sessionLogin(creds) {
        var bodyFormData = new FormData();
        bodyFormData.append('username', creds.username);
        bodyFormData.append('password', creds.password);

        const headers = { "Content-Type": "application/x-www-form-urlencoded" };
        return axios.post('authenticate', bodyFormData, headers)
    }

    jwtTokenLogin(creds) { return axios.post('login', creds) }

    getAuthenticatedUser(username) { return axios.get('api/users/username=' + username) }
}

export default new AuthService();