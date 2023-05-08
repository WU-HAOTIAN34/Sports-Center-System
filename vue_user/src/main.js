import Vue from 'vue'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import App from './App.vue'
import DashboardLayout from './layouts/DashboardLayout.vue'
import router from './router'

import './scss/app.scss'

Vue.use(Antd)

Vue.config.productionTip = false

Vue.component('layout-dashboard', DashboardLayout)

new Vue({
    el: '#app',
    components: { App },
    template: '<App/>',
    router,
    render: h => h(App)
}).$mount('#app')