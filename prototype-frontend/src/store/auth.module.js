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
        }
    },

    // methods that cannot change state data, asychronous code (e.g. api calls)
    // receive a context object which contains mutations, state, getters, actions
    // dispach an action
    actions: {
        login({ state, commit }) {
            console.log("action -> login")

            AuthService.formLogin({
                username: state.username,
                password: state.password
            })
                .then(response => {
                    console.log(response);
                    //dispatch('getAuthUser', state.username)
                }, error => {
                    commit("loginFailure", error);
                });
        },
    }
}

export default auth;