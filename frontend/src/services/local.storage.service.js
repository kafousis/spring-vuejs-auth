const USER_ID_KEY = 'auth_user_id'

const LocalStorageService = {

    getAuthUserId() {
        return localStorage.getItem(USER_ID_KEY)
    },

    setAuthUserId(user_id) {
        localStorage.setItem(USER_ID_KEY, user_id)
        //console.log(user)
    },
}

// named export
export { LocalStorageService }