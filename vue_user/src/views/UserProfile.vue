<template>
	<div>
        <a-row type="flex" :gutter="[24,24]">

            <a-col :span="24" :lg="6">

                <!-- Page Anchors -->
                <a-affix :offset-top=" navbarFixed ? 100 : 10 ">
                    <a-card :bordered="false" class="header-solid mb-24">
                        <a-anchor :targetOffset=" navbarFixed ? 100 : 10 " :affix="false">
                            <a-anchor-link href="#profile">
                                <div slot="title" class="ant-list-item-meta">
                                    <a-icon type="appstore" theme="filled" class="text-gray-6 text-lg" />
                                    <h4 class="ant-list-item-meta-title">
                                        <span class="font-regular">Profile</span>
                                    </h4>
                                </div>
                            </a-anchor-link>
                            <a-anchor-link href="#basic-info">
                                <div slot="title" class="ant-list-item-meta">
                                    <a-icon type="snippets" theme="filled" class="text-gray-6 text-lg" />
                                    <h4 class="ant-list-item-meta-title">
                                        <span class="font-regular">Basic Info</span>
                                    </h4>
                                </div>
                            </a-anchor-link>
                            <a-anchor-link href="#change-profile">
                                <div slot="title" class="ant-list-item-meta">
                                    <a-icon type="unlock" theme="filled" class="text-gray-6 text-lg" />
                                    <h4 class="ant-list-item-meta-title">
                                        <span class="font-regular">Change Profile</span>
                                    </h4>
                                </div>
                            </a-anchor-link>
                            <a-anchor-link href="#payment">
                                <div slot="title" class="ant-list-item-meta">
                                    <a-icon type="safety-certificate" theme="filled" class="text-gray-6 text-lg" />
                                    <h4 class="ant-list-item-meta-title">
                                        <span class="font-regular">Payment</span>
                                    </h4>
                                </div>
                            </a-anchor-link>
                        </a-anchor>
                    </a-card>
                </a-affix>
                <!-- / Page Anchors -->

            </a-col>
            <a-col :span="24" :lg="18">
                <a-card :bordered="false" id="profile" class="card-profile-head" :bodyStyle="{padding: 0,}">
                    <template #title>
                        <a-row type="flex" align="middle">
                            <a-col :span="24" :md="10" class="col-info">
                                <a-avatar :size="74" shape="square" :src="userData.image" />
                                <div class="avatar-info">
                                    <h4 class="font-semibold m-0">{{userData.username}}</h4>
                                </div>
                            </a-col>
                            <a-col :md="4">
                                <a-button @click="jump">change avatar</a-button>
                            </a-col>
                        </a-row>
                    </template>
                </a-card>
                <a-card :bordered="false" id="basic-info" class="header-solid mb-24">
                    <template #title>
                        <h5 class="mb-0 font-semibold">Basic Info</h5>
                    </template>
                    <a-col :span="24" :md="24" class="mb-24">
                        <CardProfileInformation
                            :id="userData.id"
                            :username="userData.username"
                            :age="userData.age"
                            :email="userData.email"
                            :name="userData.name"
                            :phoneNumber="userData.phoneNumber"
                            :sex="userData.sex"
                            :membership="userData.isMember"
                            :date="userData.membership"
                        ></CardProfileInformation>
                    </a-col>
                </a-card>
                <a-card :bordered="false" id="change-profile" class="header-solid mb-24">
                    <template #title>
                        <h5 class="mb-0 font-semibold">Update profile</h5>
                    </template>
                    <a-form
                        :form="form"
                        @submit="handleSubmit"
                    >
                        <a-row :gutter="[24]">
                            <a-col :span="12">
                                <a-form-item class="mb-10" label="Password" :colon="false">
                                    <a-input-password
                                        v-decorator="[
                                            'password',
                                            { rules: [{ required: true, message: 'Please input your Password!' }] },
                                        ]"
                                        type="password"
                                        placeholder="******"
                                    >
                                    </a-input-password>
                                </a-form-item>
                            </a-col>
                            <a-col :span="12">
                                <a-form-item class="mb-10" label="Confirm Password" has-feedback>
                                    <a-input
                                        v-decorator="[
                                          'confirm',
                                          {
                                            rules: [
                                              {
                                                required: true,
                                                message: 'Please confirm your password!',
                                              },
                                              {
                                                validator: compareToFirstPassword,
                                              },
                                            ],
                                          },
                                        ]"
                                        type="password"
                                        @blur="handleConfirmBlur"
                                    />
                                </a-form-item>
                            </a-col>
                            <a-col :span="12">
                                <a-form-item class="mb-10" label="Name" :colon="false">
                                    <a-input
                                        v-decorator="[
                                            'name',
                                            { rules: [{ required: true, message: 'Please input your name!' }] },
                                        ]"
                                        placeholder="Name"
                                    >
                                    </a-input>
                                </a-form-item>
                            </a-col>
                            <a-col :span="12">
                                <a-form-item class="mb-10" label="Phone Number" :colon="false">
                                    <a-input
                                        v-decorator="[
                                            'phoneNumber',
                                            { rules: [{ required: true, message: 'Please input your phone number!' }] },
                                        ]"
                                        placeholder="phone number"
                                    >
                                    </a-input>
                                </a-form-item>
                            </a-col>
                            <a-col :span="12">
                                <a-form-item class="mb-10" label="Age" :colon="false">
                                    <a-input
                                        v-decorator="['age',
                                            { rules: [{ required: true, message: 'Please input your age!' }] },]"
                                        type="number"
                                        placeholder="Age"
                                    >
                                    </a-input>
                                </a-form-item>
                            </a-col>
                            <a-col :span="12">
                                <a-form-item class="mb-10" label="Gender" :colon="false">
                                    <a-select
                                        v-decorator="['sex',
                                            { rules: [{ required: true, message: 'Please input your gender!' }] },]"
                                        @change="handleSelectChange"
                                    >
                                        <a-select-option value="Male">
                                            Male
                                        </a-select-option>
                                        <a-select-option value="Female">
                                            Female
                                        </a-select-option>
                                    </a-select>
                                </a-form-item>
                            </a-col>
                        </a-row>
                        <a-row :gutter="[24]">
                            <a-col :span="12">
                            </a-col>
                            <a-col :span="12" class="text-right">
                                <a-button type="primary" html-type="submit" class="px-25">SUBMIT</a-button>
                            </a-col>
                        </a-row>
                    </a-form>
                </a-card>
                <a-card :bordered="false" id="payment" class="header-solid mb-24">
                    <template #title>
                        <a-row type="flex" align="middle">
                            <a-col :span="24" :md="12">
                                <h6 class="font-semibold m-0">Payment Methods</h6>
                            </a-col>
                            <a-col v-if="cardNumber==null"></a-col>
                            <a-col v-else :span="24" :md="12" style="display: flex; align-items: center; justify-content: flex-end">
                                <a-popconfirm
                                    title="Are you sure delete this card?"
                                    ok-text="Yes"
                                    cancel-text="No"
                                    @confirm="confirm"
                                    @cancel="cancel"
                                >
                                    <a-button type="danger" href="#">
                                        DELETE THE CARD
                                    </a-button>
                                </a-popconfirm>
                            </a-col>
                        </a-row>
                    </template>
                    <a-row :gutter="[24, 24]">
                        <a-col :span="24" :md="12">
                            <a-card class="payment-method-card" v-if="cardNumber==null">
                                <a-button @click="showModal">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
                                        <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                        <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                                    </svg>
                                    add a new card
                                </a-button>
                                <a-modal v-model="visible" title="Card" @ok="handleOk">
                                    <a-form
                                        :form="form2"
                                    >
                                        <a-form-item class="mb-10" label="Card Number" :colon="false">
                                            <a-input
                                                v-decorator="['card', { rules: [{ required: true, message: 'Please input your card number!' }] }]"
                                                type="number"
                                                placeholder="eg. 1111 1111 1111 1111"
                                            >
                                            </a-input>
                                        </a-form-item>
                                    </a-form>
                                </a-modal>
                            </a-card>
                            <a-card class="payment-method-card" v-else>
                                <img src="images/logos/visa-logo.png" alt="">
                                <h6 class="card-number">**** **** **** {{ cardNumber }}</h6>
                                <a-button type="link" @click="showModal">
                                    <svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path class="fill-gray-7" d="M13.5858 3.58579C14.3668 2.80474 15.6332 2.80474 16.4142 3.58579C17.1953 4.36683 17.1953 5.63316 16.4142 6.41421L15.6213 7.20711L12.7929 4.37868L13.5858 3.58579Z"/>
                                        <path class="fill-gray-7" d="M11.3787 5.79289L3 14.1716V17H5.82842L14.2071 8.62132L11.3787 5.79289Z"/>
                                    </svg>
                                </a-button>
                                <a-modal v-model="visible" title="Card" @ok="handleOk">
                                    <a-form
                                        :form="form2"
                                    >
                                        <a-form-item class="mb-10" label="Card Number" :colon="false">
                                            <a-input
                                                v-decorator="['card', { rules: [{ required: true, message: 'Please input your card number!' }] }]"
                                                type="number"
                                                placeholder="eg. 1111 1111 1111 1111"
                                            >
                                            </a-input>
                                        </a-form-item>
                                    </a-form>
                                </a-modal>
                            </a-card>

                        </a-col>
                    </a-row>
                </a-card>
            </a-col>

        </a-row>
	</div>
</template>

<script>
import { getUserProfile, updateUserProfile } from '../api/user'
import CardProfileInformation from '../components/Cards/CardProfileInformation'
import { addCard, deleteCard } from '@/api/card'

export default ({
    components: {
        CardProfileInformation
    },
    data() {
        return {
            visible: false,
            form: this.$form.createForm(this),
            form2: this.$form.createForm(this),
            profileHeaderBtns: 'overview',
            userData: [],
            cardNumber: ''
        }
    },
    beforeMount() {
        getUserProfile().then(res => {
            this.userData = res.data.data
            this.cardNumber = res.data.card
            // get last for digit
            if (this.cardNumber !== null) {
                this.cardNumber = this.cardNumber.slice(-4)
            }
            this.$forceUpdate()
        })
    },
    methods: {
        handleConfirmBlur(e) {
            const value = e.target.value
            this.confirmDirty = this.confirmDirty || !!value
        },
        compareToFirstPassword(rule, value, callback) {
            const form = this.form
            if (value && value !== form.getFieldValue('password')) {
                // eslint-disable-next-line node/no-callback-literal
                callback('Two passwords that you enter is inconsistent!')
            } else {
                callback()
            }
        },
        showModal() {
            this.visible = true
        },
        handleOk(e) {
            this.form2.validateFields((err, values) => {
                if (!err) {
                    if (values.card.length !== 16) {
                        this.$message.error('Please input 16 digits card number!')
                    } else {
                        addCard(values).then(res => {
                            if (res.data.code === 7028) {
                                this.$message.success('Success!')
                                this.visible = false
                                getUserProfile().then(res => {
                                    this.userData = res.data.data
                                    this.cardNumber = res.data.card
                                    // get last for digit
                                    this.cardNumber = this.cardNumber.slice(-4)
                                    this.$forceUpdate()
                                })
                            } else {
                                this.$message.error(res.data.msg)
                            }
                        })
                    }
                }
            })
        },
        confirm() {
            deleteCard().then(res => {
                if (res.data.code === 7031) {
                    this.$message.success('Success!')
                    this.$router.go(0)
                } else {
                    this.$message.error(res.data.msg)
                }
            })
        },
        jump() {
            this.$router.push('/avatar')
        },
        handleChange(info) {
            if (info.file.status !== 'uploading') {
                console.log(info.file, info.fileList)
            }
            if (info.file.status === 'done') {
                this.$message.success(`${info.file.name} file uploaded successfully`)
            } else if (info.file.status === 'error') {
                this.$message.error(`${info.file.name} file upload failed.`)
            }
        },
        handleSubmit(e) {
            e.preventDefault()
            this.form.validateFields((err, values) => {
                if (!err) {
                    updateUserProfile(values).then(res => {
                        getUserProfile().then(res => {
                            this.userData = res.data.data
                            this.$forceUpdate()
                        })
                        this.form.resetFields()
                        this.$message.success('Profile updated successfully.')
                    })
                }
            })
        }
    }
})

</script>

<style>
</style>
