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
        path: '/',
        name: 'Home',
        redirect: '/home'
    },
    {
        path: '/home',
        name: 'Home',
        layout: 'dashboard',
        component: () => import('../views/StadiumHome.vue')
    },
    {
        path: '/logout',
        name: 'Logout',
        layout: 'dashboard',
        component: () => import('../views/UserLogout.vue')
    },
    {
        path: '/venues',
        name: 'Venues',
        layout: 'dashboard',
        component: () => import('../views/VenuesDisplay.vue')
    },
    {
        path: '/login',
        name: 'login',
        layout: 'dashboard',
        component: () => import('../views/UserLogin.vue')
    },
    {
        path: '/register',
        name: 'register',
        layout: 'dashboard',
        component: () => import('../views/UserRegister.vue')
    },
    {
        path: '/profile',
        name: 'profile',
        layout: 'dashboard',
        component: () => import('../views/UserProfile.vue')
    },
    {
        path: '/venueDetail',
        name: 'venueDetail',
        layout: 'dashboard',
        component: () => import('../views/VenueDetail.vue')
    },
    {
        path: '/Orders',
        name: 'Orders',
        layout: 'dashboard',
        component: () => import('../views/UserOrders.vue')
    },
    {
        path: '/equipments',
        name: 'Equipments',
        layout: 'dashboard',
        component: () => import('../views/ItemsDisplay.vue')
    },
    {
        path: '/itemDetail',
        name: 'Item detail',
        layout: 'dashboard',
        component: () => import('../views/ItemDetail.vue')
    },
    {
        path: '/comments',
        name: 'Comments',
        layout: 'dashboard',
        component: () => import('../views/UserComments.vue')
    },
    {
        path: '/chat',
        name: 'Chat',
        layout: 'dashboard',
        component: () => import('../views/ChatRoom.vue')
    },
    {
        path: '/avatar',
        name: 'Avatar',
        layout: 'dashboard',
        component: () => import('../views/UserAvatar.vue')
    },
    {
        path: '/membership',
        name: 'Membership',
        layout: 'dashboard',
        component: () => import('../views/MemberShip.vue')
    },
    {
        path: '/itemInvoice',
        name: 'Item Receipt',
        layout: 'dashboard',
        component: () => import('../views/ItemInvoice.vue')
    },
    {
        path: '/venueInvoice',
        name: 'Venue Receipt',
        layout: 'dashboard',
        component: () => import('../views/VenueInvoice.vue')
    }
]

// Adding layout property from each route to the meta
// object so it can be accessed later.
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
    if (token === null || token === '') {
        if (to.path === '/login' || to.path === '/register' || to.path === '/home' || to.path === '/venues' ||
            to.path === '/equipments' || to.path === '/venueDetail' || to.path === '/chat' ||
            to.path === '/itemDetail') {
            next()
        } else {
            next('/home')
        }
    } else {
        if (to.path === '/login' || to.path === '/register') {
            next('/home')
        } else {
            next()
        }
    }
})

export default router
