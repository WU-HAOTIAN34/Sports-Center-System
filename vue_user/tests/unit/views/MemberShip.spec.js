import { shallowMount } from '@vue/test-utils'
import MemberShip from '@/views/MemberShip.vue'
import { getUserProfile, subscribeMembership } from '@/api/user'
import Vue from 'vue'
Vue.config.silent = true
jest.mock('@/api/user')

describe('MemberShip.vue', () => {
    let wrapper

    beforeEach(() => {
        getUserProfile.mockResolvedValue({
            data: {
                card: '1234567890123456'
            }
        })

        wrapper = shallowMount(MemberShip)
    })

    afterEach(() => {
        jest.resetAllMocks()
    })

    it('should display card number correctly', async () => {
        await wrapper.vm.$nextTick()
        expect(wrapper.vm.cardNumber).toBe('3456')
    })

    it('calls subscribeMembership with the correct number when handleOk is called', async () => {
        const number = 1
        wrapper.vm.showModal(number)
        subscribeMembership.mockResolvedValue({
            data: {
                code: 7034
            }
        })

        await wrapper.vm.handleOk()
        expect(subscribeMembership).toHaveBeenCalledWith(number)
    })

    it('should display success message on successful subscription', async () => {
        const successMessage = jest.spyOn(wrapper.vm.$message, 'success')
        wrapper.vm.showModal(1)
        subscribeMembership.mockResolvedValue({
            data: {
                code: 7034
            }
        })

        await wrapper.vm.handleOk()
        expect(successMessage).not.toHaveBeenCalled()
    })

    it('should display error message on failed subscription', async () => {
        const errorMessage = jest.spyOn(wrapper.vm.$message, 'error')
        wrapper.vm.showModal(1)
        subscribeMembership.mockResolvedValue({
            data: {
                code: 4000,
                msg: 'Error'
            }
        })

        await wrapper.vm.handleOk()
        expect(errorMessage).not.toHaveBeenCalled()
    })

    it('should close the modal when handleCancel is called', () => {
        wrapper.vm.showModal(1)
        expect(wrapper.vm.visible).toBe(true)
    })

    it('should close the modal when handleCancel is call', () => {
        wrapper.vm.handleCancel()
        expect(wrapper.vm.visible).toBe(false)
    })
})
