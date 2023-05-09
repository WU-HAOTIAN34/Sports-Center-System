import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

let routes = [
    {
        // will match everything
        path: '*',
        component: () => import('../views/404Page.vue')
    },
    {
        path: '/login',
        name: 'login',
        layout: 'admindashboard',
        component: () => import('../views/AdminLogin.vue')
    },
    {
        path: '/admin/home',
        name: 'admin home',
        layout: 'admindashboard',
        component: () => import('../views/AdminHome.vue')
    },
    {
        path: '/admin/venues',
        name: 'Venues management',
        layout: 'admindashboard',
        component: () => import('../views/AdminVenues.vue')
    },
    {
        path: '/admin/venues/detail',
        name: 'Venues management',
        layout: 'admindashboard',
        component: () => import('../views/AdminVenueDetail.vue')
    },
    {
        path: '/admin/venues/add',
        name: 'Venues management',
        layout: 'admindashboard',
        component: () => import('../views/AdminVenuesAdd.vue')
    },
    {
        path: '/admin/users',
        name: 'User management',
        layout: 'admindashboard',
        component: () => import('../views/AdminUsers.vue')
    },
    {
        path: '/admin/items',
        name: 'Equipments management',
        layout: 'admindashboard',
        component: () => import('../views/AdminItems.vue')
    },
    {
        path: '/admin/items/detail',
        name: 'Equipments management',
        layout: 'admindashboard',
        component: () => import('../views/AdminItemDetail.vue')
    },
    {
        path: '/admin/items/add',
        name: 'Equipments management',
        layout: 'admindashboard',
        component: () => import('../views/AdminItemsAdd.vue')
    },
    {
        path: '/admin/items/edit',
        name: 'Equipments management',
        layout: 'admindashboard',
        component: () => import('../views/AdminItemEdit.vue')
    },
    {
        path: '/admin/venues/edit',
        name: 'Venues management',
        layout: 'admindashboard',
        component: () => import('../views/AdminVenueEdit.vue')
    },
    {
        path: '/logout',
        name: 'logout',
        layout: 'admindashboard',
        component: () => import('../views/UserLogout.vue')
    }
]

function addLayoutToRoute(route, parentLayout = 'default') {
    route.meta = route.meta || {}
    route.meta.layout = route.layout || parentLayout

    if (route.children) {
        route.children = route.children.map((childRoute) => addLayoutToRoute(childRoute, route.meta.layout))
    }
    return route
}

routes = routes.map((route) => addLayoutToRoute(route))

const router = new VueRouter({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes,
    scrollBehavior (to, from, savedPosition) {
        if (to.hash) {
            return {
                selector: to.hash,
                behavior: 'smooth'
            }
        }
        return {
            x: 0,
            y: 0,
            behavior: 'smooth'
        }
    }
})

router.beforeEach((to, from, next) => {
    const token = localStorage.getItem('token')
    if (to.path === '/' || to.path === '/home') {
        next('/login')
    }
    if (token === null || token === '') {
        if (to.path === '/login') {
            next()
        } else {
            next('/login')
        }
    } else {
        if (to.path === '/login') {
            next('/admin/home')
        } else {
            next()
        }
    }
})

export default router
