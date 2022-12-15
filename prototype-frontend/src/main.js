// https://www.primefaces.org/primevue/setup
import 'primevue/resources/themes/nova-vue/theme.css'   //theme
import 'primevue/resources/primevue.min.css'            //core css
import 'primeicons/primeicons.css'                      //icons
import 'primeflex/primeflex.css';                       //primeflex

// additional css from template
import './assets/styles/layout.scss';

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080/spring-vuejs-prototype';

// indicates whether or not requests should be made using credentials 
// such as cookies, authorization headers or TLS client certificates
axios.defaults.withCredentials = true;

import PrimeVue from 'primevue/config';
import InputText from 'primevue/inputtext';
import Checkbox from 'primevue/checkbox';
import Button from 'primevue/button';
import Message from 'primevue/message';

const app = createApp(App);

app.use(router);
app.use(store);

// ripple is an optional animation for the supported components
// Outlined & Filled Input Styles, add {inputStyle: 'filled'}
app.use(PrimeVue, { ripple: true });

app.component('InputText', InputText);
app.component('Checkbox', Checkbox);
app.component('Button', Button);
app.component('Message', Message);

app.mount('#app');