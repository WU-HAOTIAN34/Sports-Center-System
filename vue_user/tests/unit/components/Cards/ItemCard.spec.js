import { shallowMount } from '@vue/test-utils'
import ItemCard from '@/components/Cards/ItemCard.vue'
import Vue from 'vue'
Vue.config.silent = true
describe('ItemCard.vue', () => {
    let wrapper
    const defaultProps = {
        id: 1,
        name: 'Test Item',
        price: 100,
        cover: 'https://example.com/cover.jpg'
    }

    beforeEach(() => {
        wrapper = shallowMount(ItemCard, {
            propsData: defaultProps
        })
    })

    it('renders the component with the provided props', () => {
        expect(wrapper.find('img').attributes('src')).toBe(defaultProps.cover)
    })

    it('renders the component with the provided prop', () => {
        expect(wrapper.find('h5').text()).toBe(defaultProps.name)
    })

    it('displays the correct price', () => {
        const priceElement = wrapper.find('p')
        expect(priceElement.text()).toBe(`price: ${defaultProps.price} CNY`)
    })
})
