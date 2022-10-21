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
        }
    },

    // methods that cannot change state data, asychronous code (e.g. api calls)
    // receive a context object which contains mutations, state, getters, actions
    // dispach an action
    actions: {
    }
}

export default auth;