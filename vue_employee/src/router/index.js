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
        path: '/admin/venuesComment',
        name: 'Venues comments management',
        layout: 'admindashboard',
        component: () => import('../views/AdminVenueComment.vue')
    },
    {
        path: '/admin/venuesOrder',
        name: 'Venues order management',
        layout: 'admindashboard',
        component: () => import('../views/AdminVenueOrder.vue')
    },
    {
        path: '/admin/venues/order/detail',
        name: 'Venues order management',
        layout: 'admindashboard',
        component: () => import('../views/AdminVenueInvoice.vue')
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
        path: '/admin/itemsComment',
        name: 'Equipments comments management',
        layout: 'admindashboard',
        component: () => import('../views/AdminItemComment.vue')
    },
    {
        path: '/admin/itemsOrder',
        name: 'Equipments orders management',
        layout: 'admindashboard',
        component: () => import('../views/AdminItemOrder.vue')
    },
    {
        path: '/admin/items/order/detail',
        name: 'Equipments order management',
        layout: 'admindashboard',
        component: () => import('../views/AdminItemInvoice.vue')
    },
    {
        path: '/admin/users',
        name: 'User management',
        layout: 'admindashboard',
        component: () => import('../views/AdminUsers.vue')
    },
    {
        path: '/admin/states',
        name: 'States management',
        layout: 'admindashboard',
        component: () => import('../views/AdminStates.vue')
    },
    {
        path: '/admin/addVenue',
        name: 'add venue',
        layout: 'admindashboard',
        component: () => import('../views/AdminAddVenue.vue')
    },
    {
        path: '/chat',
        name: 'Chat room',
        layout: 'admindashboard',
        component: () => import('../views/ChatRoom.vue')
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
            next('/admin/venues')
        } else {
            next()
        }
    }
})

export default router
