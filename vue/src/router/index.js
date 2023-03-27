import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

let routes = [
	{
		// will match everything
		path: '*',
		component: () => import('../views/404.vue'),
	},
	{
		path: '/',
		name: 'Home',
		redirect: '/login',
	},
	{
		path: '/home',
		name: 'Home',
		layout: "dashboard",
		component: () => import(/* webpackChunkName: "dashboard" */ '../views/Home.vue'),
	},
	{
		path: '/login',
		name: 'login',
		component: () => import('../views/Login.vue'),
	},
	{
		path: '/register',
		name: 'register',
		component: () => import('../views/Register.vue'),
	},
	{
		path: '/profile',
		name: 'profile',
		layout: "dashboard",
		component: () => import('../views/Profile.vue'),
	},
	{
		path: '/admin',
		name: 'admin',
		layout: "dashboard",
		component: () => import('../views/Admin.vue'),
	},
	{
		path: '/timepicker',
		name: 'time',
		component: () => import('../views/TimePicker.vue'),
	},
	{
		path: '/timepicker2',
		name: 'time2',
		component: () => import('../views/TimePicker2.vue'),
	}
]

// Adding layout property from each route to the meta
// object so it can be accessed later.
function addLayoutToRoute( route, parentLayout = "default" )
{
	route.meta = route.meta || {} ;
	route.meta.layout = route.layout || parentLayout ;

	if( route.children )
	{
		route.children = route.children.map( ( childRoute ) => addLayoutToRoute( childRoute, route.meta.layout ) ) ;
	}
	return route ;
}

routes = routes.map( ( route ) => addLayoutToRoute( route ) ) ;

const router = new VueRouter({
	mode: 'hash',
	base: process.env.BASE_URL,
	routes,
	scrollBehavior (to, from, savedPosition) {
		if ( to.hash ) {
			return {
				selector: to.hash,
				behavior: 'smooth',
			}
		}
		return {
			x: 0,
			y: 0,
			behavior: 'smooth',
		}
	}
})

export default router
