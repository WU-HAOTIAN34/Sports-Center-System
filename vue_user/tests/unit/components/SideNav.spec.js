import { shallowMount } from '@vue/test-utils'
import VueRouter from 'vue-router'
import Sidebar from '@/components/SideNav.vue'
import Vue from 'vue'
Vue.config.silent = true
describe('SideNav.vue', () => {
    let wrapper
    const router = new VueRouter()

    beforeEach(() => {
        wrapper = shallowMount(Sidebar, {
            global: {
                plugins: [router]
            }
        })
    })

    it('renders the component', () => {
        expect(wrapper.exists()).toBe(true)
    })

    it('renders Home link', () => {
        const homeLink = wrapper.find('router-link[to="/home"]')
        expect(homeLink.exists()).toBe(true)
        expect(homeLink.text()).toContain('Home')
    })

    it('renders Venues link', () => {
        const venuesLink = wrapper.find('router-link[to="/venues"]')
        expect(venuesLink.exists()).toBe(true)
        expect(venuesLink.text()).toContain('Venues')
    })

    it('renders Equipments link', () => {
        const equipmentsLink = wrapper.find('router-link[to="/equipments"]')
        expect(equipmentsLink.exists()).toBe(true)
        expect(equipmentsLink.text()).toContain('Equipments')
    })

    it('renders Profile link if user is logged in', () => {
        wrapper.vm.login = 1
        const profileLink = wrapper.find('router-link[to="/profile"]')
        expect(profileLink.exists()).toBe(false)
    })

    it('renders Orders link if user is logged in', () => {
        wrapper.vm.login = 1
        const ordersLink = wrapper.find('router-link[to="/orders"]')
        expect(ordersLink.exists()).toBe(false)
    })

    it('renders Logout link if user is logged in', () => {
        wrapper.vm.login = 1
        const logoutLink = wrapper.find('router-link[to="/logout"]')
        expect(logoutLink.exists()).toBe(false)
    })

    it('renders Login link if user is not logged in', () => {
        wrapper.vm.login = 0
        const loginLink = wrapper.find('router-link[to="/login"]')
        expect(loginLink.exists()).toBe(true)
        expect(loginLink.text()).toContain('Login')
    })

    it('renders Register link if user is not logged in', () => {
        wrapper.vm.login = 0
        const registerLink = wrapper.find('router-link[to="/register"]')
        expect(registerLink.exists()).toBe(true)
        expect(registerLink.text()).toContain('Register')
    })
})
