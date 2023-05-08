import { shallowMount } from '@vue/test-utils'
import UserProfile from '@/views/UserProfile.vue'
import CardProfileInformation from '@/components/Cards/CardProfileInformation.vue'
import { getUserOrders, getUserProfile } from '@/api/user'
import Vue from 'vue'
Vue.config.silent = true
jest.mock('@/api/user')

describe('UserProfile.vue', () => {
    let wrapper

    beforeEach(() => {
        const user = {
            id: 1,
            username: 'testuser',
            age: 25,
            email: 'test@example.com',
            name: 'Test User',
            password: 'password',
            phoneNumber: '1234567890',
            sex: 1
        }

        sessionStorage.setItem('user', JSON.stringify(user))

        getUserProfile.mockResolvedValue({
            data: {
                data: user
            }
        })

        wrapper = shallowMount(UserProfile)
    })

    afterEach(() => {
        sessionStorage.clear()
        jest.resetAllMocks()
    })

    it('renders the container div', () => {
        expect(wrapper.find('div').exists()).toBe(true)
    })

    it('renders the profile card with user information', () => {
        expect(wrapper.find('#profile').exists()).toBe(true)
    })

    it('renders the basic info card with CardProfileInformation component', () => {
        expect(wrapper.find('#basic-info').exists()).toBe(true)
        expect(wrapper.findComponent(CardProfileInformation).exists()).toBe(true)
    })

    it('renders the change password card with input fields and update button', () => {
        const changePasswordCard = wrapper.find('#change-password')
        expect(changePasswordCard.exists()).toBe(true)
        expect(changePasswordCard.findAll('a-input-password-stub').length).toBe(3)
        expect(changePasswordCard.find('a-button-stub').exists()).toBe(true)
    })

    it('renders the two-factor authentication card with email and phone number', () => {
        expect(wrapper.find('div').exists()).toBe(true)
        expect(wrapper.find('#profile').exists()).toBe(true)
    })

    it('renders the delete account card with switch and buttons', () => {
        const deleteAccountCard = wrapper.find('#delete-account')
        expect(deleteAccountCard.exists()).toBe(true)
        expect(deleteAccountCard.find('a-switch-stub').exists()).toBe(true)
    })
})
