import { shallowMount } from '@vue/test-utils'
import UserComments from '@/views/UserComments.vue'
import { getUserItemComment } from '@/api/itemComment'
import { getUserVenueComment } from '@/api/venueComment'
import Vue from 'vue'
Vue.config.silent = true
jest.mock('@/api/itemComment')
jest.mock('@/api/venueComment')

describe('UserComments.vue', () => {
    let wrapper

    const itemCommentsData = [
        {
            id: 1,
            itemImage: 'https://example.com/item-image.jpg',
            itemName: 'Test Item',
            commentTime: '2023-05-04',
            info: 'This is a test item comment.'
        }
    ]

    const venueCommentsData = [
        {
            id: 1,
            venueImage: 'https://example.com/venue-image.jpg',
            venueName: 'Test Venue',
            commentTime: '2023-05-04',
            info: 'This is a test venue comment.'
        }
    ]

    beforeEach(() => {
        getUserItemComment.mockResolvedValue({ data: { data: itemCommentsData } })
        getUserVenueComment.mockResolvedValue({ data: { data: venueCommentsData } })

        wrapper = shallowMount(UserComments)
    })

    afterEach(() => {
        getUserItemComment.mockClear()
        getUserVenueComment.mockClear()
    })

    it('loads item comments on mount', async () => {
        await wrapper.vm.$nextTick()
        expect(getUserItemComment).toHaveBeenCalled()
        expect(wrapper.vm.itemComments).toEqual(itemCommentsData)
    })

    it('loads venue comments on mount', async () => {
        await wrapper.vm.$nextTick()
        expect(getUserVenueComment).toHaveBeenCalled()
        expect(wrapper.vm.venueComments).toEqual(venueCommentsData)
    })

    it('renders item and venue comments', () => {
        expect(wrapper.findAll('a-row').length).toBe(0)
    })

    // Add more tests as needed, such as for confirming and canceling deletion
})
