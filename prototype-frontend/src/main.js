// https://www.primefaces.org/primevue/setup
import 'primevue/resources/themes/nova-vue/theme.css'   //theme
import 'primevue/resources/primevue.min.css'            //core css
import 'primeicons/primeicons.css'                      //icons
import 'primeflex/primeflex.css';                       //primeflex

// additional css from template
import './assets/styles/layout.scss';
//import './assets/demo/flags/flags.css';

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import PrimeVue from 'primevue/config';

const app = createApp(App);

app.use(router);
app.use(store);

// ripple is an optional animation for the supported components
// Outlined & Filled Input Styles, add {inputStyle: 'filled'}
app.use(PrimeVue, { ripple: true });

app.mount('#app');