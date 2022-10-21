import { createRouter, createWebHistory } from 'vue-router'
import LoginForm from '../views/LoginForm.vue';
import Dashboard from '../views/Dashboard.vue'

const routes = [
	{
        // must have root path
        // if not, warning is shown
        path: '/',
		name: 'root',
        redirect: '/dashboard',
        meta: {
            requiresAuth: true
        }
    },
	{
		path: '/login',
        name: 'login',
        component: LoginForm,
        meta: {
            requiresAuth: false
        }
    },
	{
        path: '/dashboard',
        name: 'dashboard',
        component: Dashboard,
        meta: {
            requiresAuth: true
        }
    },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// called before each route is processed
// to is where the user wishes to go, 
// from is where the user is coming from, 
// next is a callback function that continues the processing of the user request

router.beforeEach((to, from, next) => {

    // console.log(to);
    // console.log(from);

    const authenticated = false; // TODO

    if (to.matched.some(record => record.meta.requiresAuth)) {

        // view requires auth
        if (authenticated) {
            next()
        } else {
            // user NOT logged in
            // redirect to login view
            router.push('login')
        }

    } else {

        if (authenticated && to.name == 'login') {
            //console.log("returning to login view");
            router.push('dashboard')
        } else {
            next()
        }
    }
})

export default router
