import { shallowMount } from '@vue/test-utils'
import VenueCard from '@/components/Cards/VenueCard.vue'
import Vue from 'vue'
Vue.config.silent = true
describe('VenueCard.vue', () => {
    let wrapper
    const defaultProps = {
        id: 1,
        name: 'Test Venue',
        address: '123 Test Street',
        cover: 'https://example.com/cover.jpg',
        price: 50
    }

    beforeEach(() => {
        wrapper = shallowMount(VenueCard, {
            propsData: defaultProps
        })
    })

    it('renders the component with the provided prop', () => {
        expect(wrapper.find('h5').text()).toBe(defaultProps.name)
    })

    it('renders the component with th provided props', () => {
        expect(wrapper.find('p').text()).toBe(defaultProps.address)
    })

    it('render the component with the provided props', () => {
        expect(wrapper.find('img').attributes('src')).toBe(defaultProps.cover)
    })

    it('displays the correct price per hour', () => {
        const priceElement = wrapper.findAll('p').at(1)
        expect(priceElement.text()).toBe(`price: ${defaultProps.price} CNY/hour`)
    })
})
