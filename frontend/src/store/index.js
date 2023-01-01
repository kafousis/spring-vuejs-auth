import { createStore } from 'vuex'
import auth from './auth.module'

const store = createStore({
	modules: {
		auth
	},
})

// one default export per file
// @ importing you have to specify a name
export default store;