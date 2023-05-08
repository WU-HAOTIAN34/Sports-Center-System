import { shallowMount, createLocalVue } from '@vue/test-utils'
import UserLogin from '@/views/UserLogin.vue'
import antd from 'ant-design-vue'
import VueRouter from 'vue-router'
import Vue from 'vue'
Vue.config.silent = true
const localVue = createLocalVue()
localVue.use(antd)
localVue.use(VueRouter)
const router = new VueRouter()

describe('UserLogin.vue', () => {
    let wrapper

    beforeEach(() => {
        wrapper = shallowMount(UserLogin, { localVue, router })
    })

    it('renders the login form container', () => {
        expect(wrapper.find('.card-signup').exists()).toBe(true)
    })

    it('renders the login form with username and password input fields', () => {
        expect(wrapper.find('a-input-stub[placeholder="Userame"]').exists()).toBe(true)
        expect(wrapper.find('a-input-stub[placeholder="Password"]').exists()).toBe(true)
    })

    it('renders the remember me switch', () => {
        expect(wrapper.find('a-switch-stub').exists()).toBe(true)
    })

    it('renders the submit button', () => {
        expect(wrapper.find('a-button-stub[type="primary"]').exists()).toBe(true)
    })

    it('renders the sign up link', () => {
        expect(wrapper.find('router-link-stub[to="/register"]').exists()).toBe(true)
    })

    // Add more tests specific to your component's functionality
})
