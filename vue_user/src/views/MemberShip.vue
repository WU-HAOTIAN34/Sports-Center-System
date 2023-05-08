<template>
    <div>

        <div class="pricing-wrapper">
            <div class="container">
                <a-row type="flex" :gutter="[24,24]">
                    <a-col :span="24" :lg="8">
                        <a-card class="card-pricing header-solid" :bodyStyle="{padding: '20px'}">
                            <a-tag class="bg-gray-4 text-gray-9 font-bold">
                                1 Month
                            </a-tag>
                            <h1 class="font-semibold">
                                <small>￥</small>
                                <span>199</span>
                            </h1>
                            <ul class="list-pricing">
                                <li>
                                    <a-avatar :size="24" class="bg-dark">
                                        <a-icon type="check" />
                                    </a-avatar>
                                    Discount on venue bookings
                                </li>
                                <li>
                                    <a-avatar :size="24" class="bg-dark">
                                        <a-icon type="check" />
                                    </a-avatar>
                                    Access to member-only events
                                </li>
                                <li>
                                    <a-avatar :size="24" class="bg-dark">
                                        <a-icon type="check" />
                                    </a-avatar>
                                    Free parking
                                </li>
                            </ul>
                            <a-button type="primary" block @click="showModal(1)">
                                JOIN <a-icon type="arrow-right" class="ml-5" />
                            </a-button>
                            <a-modal
                                title="Please select a paying method"
                                :visible="visible"
                                :confirm-loading="confirmLoading"
                                @ok="handleOk"
                                @cancel="handleCancel"
                            >
                                <a-tabs default-active-key="1" @change="callback">
                                    <a-tab-pane key="1" tab="Card">
                                        <p v-if="cardNumber==null">Sorry, you haven't add any card yet. Go to profile page to add one.</p>
                                        <a-card class="payment-method-card" v-else>
                                            <img src="images/logos/visa-logo.png" alt="">
                                            <h6 class="card-number">**** **** **** {{ cardNumber }}</h6>
                                        </a-card>
                                    </a-tab-pane>
                                    <a-tab-pane key="2" tab="Cash" force-render>
                                        <img src="images/QRCode.png" alt="">
                                    </a-tab-pane>
                                </a-tabs>
                            </a-modal>
                        </a-card>

                    </a-col>
                    <a-col :span="24" :lg="8">
                        <a-card class="card-pricing header-solid" :bodyStyle="{padding: '20px'}">
                            <a-tag class="bg-gray-4 text-gray-9 font-bold">
                                3 Months
                            </a-tag>
                            <h1 class="font-semibold">
                                <small>￥</small>
                                <span>499</span>
                            </h1>

                            <ul class="list-pricing">
                                <li>
                                    <a-avatar :size="24" class="bg-dark">
                                        <a-icon type="check" />
                                    </a-avatar>
                                    All benefits of 1-month membership
                                </li>
                                <li>
                                    <a-avatar :size="24" class="bg-dark">
                                        <a-icon type="check" />
                                    </a-avatar>
                                    Priority booking access
                                </li>
                                <li>
                                    <a-avatar :size="24" class="bg-dark">
                                        <a-icon type="check" />
                                    </a-avatar>
                                    Free guest passes
                                </li>
                            </ul>

                            <a-button type="dark" block @click="showModal(3)">
                                JOIN <a-icon type="arrow-right" class="ml-5" />
                            </a-button>
                        </a-card>

                    </a-col>
                    <a-col :span="24" :lg="8">
                        <a-card class="card-pricing header-solid" :bodyStyle="{padding: '20px'}">
                            <a-tag class="bg-gray-4 text-gray-9 font-bold">
                                1 Year
                            </a-tag>
                            <h1 class="font-semibold">
                                <small>￥</small>
                                <span>899</span>
                            </h1>

                            <ul class="list-pricing">
                                <li>
                                    <a-avatar :size="24" class="bg-dark">
                                        <a-icon type="check" />
                                    </a-avatar>
                                    All benefits of 3-months membership
                                </li>
                                <li>
                                    <a-avatar :size="24" class="bg-dark">
                                        <a-icon type="check" />
                                    </a-avatar>
                                    Exclusive access to premium facilities
                                </li>
                                <li>
                                    <a-avatar :size="24" class="bg-dark">
                                        <a-icon type="check" />
                                    </a-avatar>
                                    Personalized locker
                                </li>
                            </ul>

                            <a-button type="primary" block @click="showModal(12)">
                                JOIN <a-icon type="arrow-right" class="ml-5" />
                            </a-button>
                        </a-card>
                    </a-col>
                </a-row>
                <a-row type="flex">
                    <a-col :span="24" :md="12" class="mx-auto">
                        <h2 class="text-center">Frequently Asked Questions</h2>
                    </a-col>
                </a-row>

                <a-row type="flex" class="mb-24">
                    <a-col :span="24" :md="20" class="mx-auto">

                        <a-collapse v-model="faq" :bordered="false" accordion>
                            <a-collapse-panel key="1" header="What are the operating hours of the sports venue?" :showArrow="false">
                                <p>
                                    Our sports venue is open from 9:00 to 19:00 all days.
                                </p>
                                <a-icon slot="extra" :type="faq == 1 ? 'minus' : 'plus'" />
                            </a-collapse-panel>
                            <a-collapse-panel key="2" header="Can I book a venue without a membership?" :showArrow="false">
                                <p>
                                    Yes, you can book a venue without a membership, but members enjoy discounted rates on venue bookings.
                                </p>
                                <a-icon slot="extra" :type="faq == 2 ? 'minus' : 'plus'" />
                            </a-collapse-panel>
                            <a-collapse-panel key="3" header="Are there any age restrictions for using the sports facilities?" :showArrow="false">
                                <p>
                                    Some facilities may have age restrictions. Please check the specific facility's rules and guidelines on our website or contact our customer support team for more information.
                                </p>
                                <a-icon slot="extra" :type="faq == 3 ? 'minus' : 'plus'" />
                            </a-collapse-panel>
                            <a-collapse-panel key="4" header="How can I cancel or reschedule my booking?" :showArrow="false">
                                <p>
                                    You can cancel or reschedule your booking through our website or by contacting our customer support team at least before your booking time.
                                </p>
                                <a-icon slot="extra" :type="faq == 4 ? 'minus' : 'plus'" />
                            </a-collapse-panel>
                            <a-collapse-panel key="5" header="Do you offer any group or corporate discounts?" :showArrow="false">
                                <p>
                                    Yes, we offer group and corporate discounts for large bookings and memberships. Please contact our sales team for more information.
                                </p>
                                <a-icon slot="extra" :type="faq == 5 ? 'minus' : 'plus'" />
                            </a-collapse-panel>
                            <a-collapse-panel key="6" header="Do you provide equipment rental services?" :showArrow="false">
                                <p>
                                    Yes, our sports center offers a wide range of equipment rental services to accommodate your needs. We understand the importance of having access to the right gear for your chosen sports or activities, and our rental services are designed to provide you with a convenient and cost-effective solution.
                                </p>
                                <a-icon slot="extra" :type="faq == 6 ? 'minus' : 'plus'" />
                            </a-collapse-panel>
                        </a-collapse>

                    </a-col>
                </a-row>
            </div>
        </div>
    </div>
</template>

<script>

import { getUserProfile, subscribeMembership } from '@/api/user'

export default ({
    data() {
        return {
            visible: false,
            confirmLoading: false,
            number: 0,
            cardNumber: ''
        }
    },
    beforeMount () {
        getUserProfile().then(res => {
            this.cardNumber = res.data.card
            if (this.cardNumber !== null) {
                this.cardNumber = this.cardNumber.slice(-4)
            }
        })
    },
    methods: {
        showModal(num) {
            this.visible = true
            this.number = num
        },
        handleOk(e) {
            this.confirmLoading = true
            subscribeMembership(this.number).then(res => {
                setTimeout(() => {
                    this.visible = false
                    this.confirmLoading = false
                    if (res.data.code === 7034) {
                        this.$message.success('Subscribe successfully')
                    } else {
                        this.$message.error(res.data.msg)
                    }
                }, 1000)
            })
        },
        handleCancel(e) {
            this.visible = false
        }
    }
})

</script>

<style lang="scss">
</style>
