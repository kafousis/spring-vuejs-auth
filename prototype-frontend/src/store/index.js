import { createStore } from 'vuex'
import VuexPersistence from 'vuex-persist'
import auth from './auth.module'

const store = createStore({
	modules: {
		auth
	},
	plugins: [new VuexPersistence().plugin]
})

// one default export per file
// @ importing you have to specify a name
export default store;