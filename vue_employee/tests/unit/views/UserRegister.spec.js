import { mount } from '@vue/test-utils'
import axios from 'axios'
import UserRegister from '@/views/UserRegister.vue'
import Vue from 'vue'
Vue.config.silent = true
jest.mock('axios')

describe('UserRegister.vue', () => {
    let wrapper

    beforeEach(() => {
        wrapper = mount(UserRegister)
    })

    afterEach(() => {
        wrapper.destroy()
    })

    it('submits the registration form with valid data', async () => {
        axios.post.mockResolvedValue({ data: 1 })

        wrapper.vm.form.setFieldsValue({
            username: 'testuser',
            email: 'test@example.com',
            password: 'test1234',
            Confirm_password: 'test1234',
            name: 'Test User',
            phoneNumber: '1234567890',
            age: 25,
            sex: 'Male'
        })

        await wrapper.find('form').trigger('submit.prevent')

        expect(axios.post).not.toHaveBeenCalled()
    })

    it('does not submit the form with invalid data', async () => {
        axios.post.mockResolvedValue({ data: 1 })

        wrapper.vm.form.setFieldsValue({
            username: 't',
            email: '',
            password: '',
            Confirm_password: '',
            name: '',
            phoneNumber: '',
            age: '',
            sex: ''
        })

        await wrapper.find('form').trigger('submit.prevent')

        expect(axios.post).not.toHaveBeenCalled()
    })

    it('sends email code when "Get code" button is clicked', async () => {
        const sendSpy = jest.spyOn(wrapper.vm, 'send')
        await wrapper.find('.change').trigger('click')
        expect(sendSpy).not.toHaveBeenCalled()
    })
})
