<template>
	<div>
        <div class="mx-auto mt-50" style="max-width: 800px;">

            <div class="mb-24">
                <a-card :bordered="false" class="header-solid" :bodyStyle="{paddingTop: 0 }" :headStyle="{paddingBottom: '0' }">
                    <template #title>
                        <h5 class="mb-0">Sign up</h5>
                    </template>
                    <a-form
                        :form="form"
                        @submit="handleSubmit"
                    >
                        <a-row :gutter="[24]">
                            <a-col :span="24">
                                <a-form-item class="mb-10" label="Username" :colon="false">
                                    <a-input
                                        v-decorator="[
                                            'username',
                                            { rules: [{ required: true, message: 'Please input your username!' },
                                                        { max: 20, message: 'Max length of username is 20'},
                                                        { min: 4, message: 'Min length of username is 4'}] },
                                        ]"
                                        placeholder="Username"
                                    >
                                    </a-input>
                                </a-form-item>
                            </a-col>
                            <a-col :span="12">
                                <a-form-item class="mb-10" label="Email Address" :colon="false">
                                    <a-input
                                        v-decorator="[
                                          'email',
                                          {
                                            rules: [
                                              {
                                                type: 'email',
                                                message: 'The input is not valid E-mail!',
                                              },
                                              {
                                                required: true,
                                                message: 'Please input your E-mail!',
                                              },
                                            ],
                                          },
                                        ]"
                                        placeholder="Email"
                                    >
                                    </a-input>
                                </a-form-item>
                            </a-col>
                            <a-col :span="12">
                                <a-form-item class="mb-10" label="verification code" :colon="false">
                                    <a-input
                                        v-decorator="[
                                            'code',
                                            { rules: [{ required: true, message: 'Please input email verification code!' }, {validator: validateToCode } ] },
                                        ]"
                                        placeholder="6 digits"
                                    >
                                    </a-input>
                                    <a-button @click="getCode" :loading="loading">{{ buttonText }}</a-button>
                                </a-form-item>
                            </a-col>
                            <a-col :span="12">
                                <a-form-item class="mb-10" label="Password" has-feedback :colon="false">
                                    <a-input
                                        v-decorator="[
                                          'password',
                                          {
                                            rules: [
                                              { required: true, message: 'Please input your password!' },
                                              { validator: validateToNextPassword },
                                              { min: 6, message: 'Min length of password is 6' },
                                              { max: 20, message: 'Max length of password is 20' }
                                            ],
                                          },
                                        ]"
                                        type="password"
                                        placeholder="******"
                                    />
                                </a-form-item>
                            </a-col>
                            <a-col :span="12">
                                <a-form-item class="mb-10" label="Confirm Password" has-feedback :colon="false">
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
                                        placeholder="******"
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
                                        v-decorator="['age',{ rules: [{ required: true, message: 'Please input your age!' }] },]"
                                        type="number"
                                        placeholder="Age"
                                    >
                                    </a-input>
                                </a-form-item>
                            </a-col>
                            <a-col :span="12">
                                <a-form-item class="mb-10" label="Gender" :colon="false">
                                    <a-select
                                        v-decorator="['sex',{ rules: [{ required: true, message: 'Please input your gender!' }] },]"
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
                                <a-button type="primary" html-type="submit" class="px-25">SIGN UP</a-button>
                            </a-col>
                        </a-row>
                    </a-form>
                </a-card>
            </div>

        </div>
	</div>

</template>

<script>

import { userRegister, verifyUsernameAndEmail } from '@/api/user'

export default ({
    data() {
        return {
            loading: false,
            codeStorage: '',
            buttonText: 'Get Code'
        }
    },
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'normal_login' })
    },
    methods: {
        getCode() {
            // check if username and email is entered
            const username = this.form.getFieldValue('username')
            const email = this.form.getFieldValue('email')
            if (!username || !email) {
                this.$message.error('Please enter username and email')
                return
            }
            verifyUsernameAndEmail(username, email).then(res => {
                if (res.data.code === 7036) {
                    this.$message.success('code sent successfully')
                    this.codeStorage = res.data.data
                    this.loading = true
                    this.startCountdown()
                } else {
                    this.$message.warning(res.data.msg)
                }
            })
        },
        handleSubmit(e) {
            e.preventDefault()
            this.form.validateFields((err, values) => {
                if (!err) {
                    userRegister(values).then(res => {
                        if (res.data.code === 7007) {
                            this.$message.success('sign up successfully')
                            this.$router.push({ path: '/login' })
                        } else {
                            this.$message.error(res.data.msg)
                        }
                    })
                }
            })
        },
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
        validateToNextPassword(rule, value, callback) {
            const form = this.form
            if (value && this.confirmDirty) {
                form.validateFields(['confirm'], { force: true })
            }
            callback()
        },
        validateToCode(rule, value, callback) {
            const decode = this.codeStorage[5] + this.codeStorage[11] + this.codeStorage[17] + this.codeStorage[23] + this.codeStorage[29] + this.codeStorage[35]
            if (value && value !== decode) {
                // eslint-disable-next-line node/no-callback-literal
                callback('Code is not correct')
            } else {
                callback()
            }
        },
        startCountdown() {
            let count = 60
            const timer = setInterval(() => {
                if (count === 0) {
                    this.loading = false
                    this.buttonText = 'Get Code'
                    clearInterval(timer)
                } else {
                    count--
                    this.buttonText = `${count}s`
                }
            }, 1000)
        }
    }
})

</script>

<style>
</style>
