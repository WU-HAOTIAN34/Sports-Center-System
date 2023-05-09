<template>
    <div class="sign-in">

        <a-row type="flex" :gutter="[24,24]" align="middle" class="row-main my-0">

            <!-- Sign Up Form Column -->
            <a-col :span="24" :md="12" :lg="{span: 12, offset: 0}" :xl="{span: 6, offset: 5}" class="col-form">

                <h3 class="text-dark mb-15">Manager Login</h3>

                <!-- Sign Up Form -->
                <a-form
                    id="components-form-demo-normal-signup"
                    :form="form"
                    class="signup-form"
                    @submit="handleSubmit"
                    :hideRequiredMark="true"
                >
                    <a-form-item class="mb-10" label="Username" :colon="false">
                        <a-input
                            v-decorator="[
						'username',
						{ rules: [{ required: true, message: 'Please input your username!' }] },
						]" placeholder="Username" />
                    </a-form-item>
                    <a-form-item class="mb-5" label="Password" :colon="false">
                        <a-input
                            v-decorator="[
						'password',
						{ rules: [{ required: true, message: 'Please input your password!' }] },
						]" type="password" placeholder="Password" />
                    </a-form-item>
                    <a-form-item>
                        <a-button type="primary" block html-type="submit" class="signup-form-button">
                            SIGN IN
                        </a-button>
                    </a-form-item>
                </a-form>
            </a-col>
        </a-row>
    </div>
</template>

<script>
import { userLogin } from '@/api/user'
export default ({
    data() {
        return {
            // Sign up form object.
            form: this.$form.createForm(this, { name: 'signup_cover' })
        }
    },
    methods: {
        handleSubmit (e) {
            e.preventDefault()
            this.form.validateFields((err, values) => {
                if (!err) {
                    userLogin(values).then(res => {
                        console.log(res.data)
                        if (res.data.code === 7019) {
                            localStorage.setItem('token', res.data.data)
                            this.$router.go(0)
                            // this.$notification.success({
                            //     message: 'Notification Title',
                            //     description:
                            //         'This is the content of the notification. This is the content of the notification. This is the content of the notification.'
                            // })
                            this.$router.push('/admin/venues')
                            this.$message.success('Login Success')
                        } else {
                            this.$message.error(res.data.message)
                        }
                    })
                }
            })
        }
    }
})

</script>

<style lang="scss">
</style>
