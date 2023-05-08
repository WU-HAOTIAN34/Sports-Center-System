import { shallowMount } from '@vue/test-utils'
import StadiumHome from '@/views/StadiumHome.vue'
import CardOrderHistory from '@/components/Cards/CardOrderHistory.vue'
import Vue from 'vue'
Vue.config.silent = true
describe('StadiumHome.vue', () => {
    let wrapper

    beforeEach(() => {
        wrapper = shallowMount(StadiumHome)
    })

    it('renders the correct number of counter widgets', () => {
        const widgets = wrapper.findAllComponents(WidgetCounter)
        expect(widgets.length).toBe(1)
    })

    it('renders the CardOrderHistory component', () => {
        expect(wrapper.findComponent(CardOrderHistory).exists()).toBe(true)
    })
})
