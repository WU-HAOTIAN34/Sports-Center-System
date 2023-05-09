import { shallowMount } from '@vue/test-utils'
import VenueCard from '@/components/Cards/VenueCard.vue'
import Vue from 'vue'
Vue.config.silent = true
describe('VenueCard.vue', () => {
    let wrapper
    const propsData = {
        id: 1,
        name: 'Test Project',
        address: '123 Test Street',
        cover: 'https://example.com/cover.jpg',
        price: 100
    }

    beforeEach(() => {
        wrapper = shallowMount(VenueCard, { propsData })
    })

    it('renders the correct id', () => {
        expect(wrapper.find('.card-tag').text()).toContain(`id: ${propsData.id}`)
    })

    it('renders the correct name', () => {
        expect(wrapper.find('h5').text()).toBe(propsData.name)
    })

    it('renders the correct address', () => {
        expect(wrapper.find('p').text()).toBe(propsData.address)
    })

    it('renders the correct price', () => {
        expect(wrapper.findAll('p').at(1).text()).toContain(`price: ${propsData.price} CNY/hour`)
    })

    it('calls "jump" method when "Book" button is clicked', async () => {
        expect(wrapper.find('.card-tag').text()).toContain(`id: ${propsData.id}`)
    })

    it('navigates to the correct route when "jump" method is called', () => {
        const pushSpy = jest.fn()
        wrapper.vm.$router = { push: pushSpy }
        wrapper.vm.jump()
        expect(pushSpy).toHaveBeenCalledWith({ path: '/venueDetail', query: { id: propsData.id } })
    })
})
