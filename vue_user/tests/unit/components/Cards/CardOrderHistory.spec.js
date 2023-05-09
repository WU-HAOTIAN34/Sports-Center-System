import { shallowMount } from '@vue/test-utils'
import CardOrderHistory from '@/components/Cards/CardOrderHistory.vue'
import Vue from 'vue'
Vue.config.silent = true

describe('CardOrderHistory.vue', () => {
    let wrapper

    beforeEach(() => {
        wrapper = shallowMount(CardOrderHistory)
    })

    it('renders timeline with the correct initial state', () => {
        const timeline = wrapper.find('a-timeline-stub')
        expect(timeline.attributes('pending')).toBe('Recording...')
        expect(timeline.attributes('reverse')).toBe(undefined)
    })

    it('renders imeline-items with correct colors and texts', () => {
        const timelineItems = wrapper.findAll('a-timeline-item-stub')
        expect(timelineItems.at(0).attributes('color')).toBe('green')
        expect(timelineItems.at(0).text()).toContain('The gym is closed today due to the weather')
    })

    it('renders timeline-items with correct colors and texs', () => {
        const timelineItems = wrapper.findAll('a-timeline-item-stub')
        expect(timelineItems.at(1).attributes('color')).toBe('green')
        expect(timelineItems.at(1).text()).toContain('The gym is reopened')
    })

    it('renders timeline-items with correct colors and exts', () => {
        const timelineItems = wrapper.findAll('a-timeline-item-stub')
        expect(timelineItems.at(2).attributes('color')).toBe('blue')
        expect(timelineItems.at(2).text()).toContain('Company server payments')
    })

    it('renders timeline-items with correct colos and texts', () => {
        const timelineItems = wrapper.findAll('a-timeline-item-stub')
        expect(timelineItems.at(3).attributes('color')).toBe('blue')
        expect(timelineItems.at(3).text()).toContain('The gym is closed today due to the weather')
    })

    it('renders timeline-items with corect colors and texts', () => {
        const timelineItems = wrapper.findAll('a-timeline-item-stub')
        expect(timelineItems.at(4).attributes('color')).toBe('gray')
        expect(timelineItems.at(4).text()).toContain('New order #46282344')
    })
})
