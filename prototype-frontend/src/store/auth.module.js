import AuthService from '../services/auth.service';
import router from "../router";
import { LocalStorageService } from '@/services/local.storage.service';

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
        },
        saveUser(payload){
            LocalStorageService.setAuthUserId(payload.user.id)
        },
    },

    // methods that cannot change state data, asychronous code (e.g. api calls)
    // receive a context object which contains mutations, state, getters, actions
    // dispatch an action
    actions: {
        sessionLogin({ state, commit, dispatch }) {
            console.log("sessionLogin")

            AuthService.sessionLogin({
                username: state.username,
                password: state.password
            })
                .then( ()=> {
                    console.log('login successful')
                    commit('loginSuccess');
                    dispatch('getAuthenticatedUser')
                }, error => {
                    commit("loginFailure", error)
                });
        },
        getAuthenticatedUser({ commit }) {
            console.log("getAuthenticatedUser")

            AuthService.getAuthenticatedUser()
                .then(response => {
                    console.log("user retrieved")                    
                    commit("updateUser", response.data)
                    commit("saveUser", response.data)
                    router.push('/dashboard')
                }, error => {
                    commit("loginFailure", error)
                })
        },
    }
}

export default auth;