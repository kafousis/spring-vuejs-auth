import AuthService from '../services/auth.service';

const auth = {

	namespaced: true,

	// data
    state: {
		username: 'admin',
        password: "admin",
        rememberMe: false,
        errorMessage: null,
        user: null
    },

	// computed properties for stores
    getters: {
    },

    // methods that can change state data, only sychronous code
    // mutations expect two arguments: state and payload
    // commit a mutation
    mutations: {
        updateUsername(state, username) {
            //console.log("updateUsername")
            state.username = username
        },
        updatePassword(state, password) {
            //console.log("updatePassword")
            state.password = password
        },
        updateRememeberMe(state, rememberMe) {
            //console.log("updateRememeberMe")
            state.rememberMe = rememberMe
        },
        loginSuccess(state) {
            console.log("loginSuccess")
            state.errorMessage = "";
        },
        loginFailure(state, errorMessage) {
            console.log("loginFailure")
            state.errorMessage = errorMessage;
        },
        updateUser(state, user) {
            state.user = user
        }
    },

    // methods that cannot change state data, asychronous code (e.g. api calls)
    // receive a context object which contains mutations, state, getters, actions
    // dispach an action
    actions: {
        sessionLogin({ state, commit, dispatch }) {
            console.log("sessionLogin")

            AuthService.sessionLogin({
                username: state.username,
                password: state.password
            })
                .then( ()=> {
                    console.log('login successful')
                    dispatch('getAuthenticatedUser', state.username)
                }, error => {
                    commit("loginFailure", error)
                });
        },
        getAuthenticatedUser({ commit }, username) {
            console.log("getAuthenticatedUser")
            
            AuthService.getAuthenticatedUser(username)
                .then(response => {
                    console.log(response.data)
                    commit("updateUser", response.data)
                    commit('loginSuccess');
                }, error => {
                    commit("loginFailure", error)

                })
        },
    }
}

export default auth;