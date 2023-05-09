import { shallowMount, createLocalVue } from '@vue/test-utils'
import VenueDetail from '@/views/VenueDetail.vue'
import antd from 'ant-design-vue'
import VueRouter from 'vue-router'
import Vue from 'vue'
Vue.config.silent = true
const localVue = createLocalVue()
localVue.use(antd)
localVue.use(VueRouter)
const router = new VueRouter({ routes: [{ path: '/', name: 'VenueDetail' }] })

describe('VenueDetail.vue', () => {
    let wrapper

    beforeEach(() => {
        wrapper = shallowMount(VenueDetail, { localVue, router })
    })

    it('renders the venue detail card', () => {
        expect(wrapper.find('.card-product').exists()).toBe(true)
    })

    it('renders the venue name', () => {
        expect(wrapper.find('h3').exists()).toBe(true)
    })

    it('renders the venue rating', () => {
        expect(wrapper.find('.rating').exists()).toBe(true)
    })

    it('renders the venue price', () => {
        expect(wrapper.find('h5.mb-0.mt-20.font-semibold').exists()).toBe(true)
    })

    it('renders the venue address', () => {
        expect(wrapper.find('h5.mb-0.mt-20.font-semibold + h5.mb-0.mt-20.font-semibold').exists()).toBe(true)
    })

    it('renders the booking button', () => {
        expect(wrapper.find('a-button[type="primary"]').exists()).toBe(false)
    })

    it('renders the comment list', () => {
        expect(wrapper.find('.comment-list').exists()).toBe(true)
    })

})
