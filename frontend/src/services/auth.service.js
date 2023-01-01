import axios from 'axios';

class AuthService {

    getCsrfToken() { return axios.get('csrf/token'); }

    sessionLogin(creds) {
        var bodyFormData = new FormData();
        bodyFormData.append('username', creds.username);
        bodyFormData.append('password', creds.password);

        const headers = { "Content-Type": "application/x-www-form-urlencoded" };
        return axios.post('authenticate', bodyFormData, headers)
    }

    getAuthenticatedUser() { return axios.get('api/me') }

}

export default new AuthService();