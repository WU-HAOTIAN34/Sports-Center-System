<template>
    <div>
        <div class="sign-up-header" style="background-image: url('images/a.jpg')">
            <div class="content">
                <h1 class="mb-5">Sign In</h1>
            </div>
        </div>

        <a-card :bordered="false" class="card-signup header-solid h-full" :bodyStyle="{paddingTop: 0}">
            <a-form
                id="components-form-demo-normal-login"
                :form="form"
                class="login-form my-25"
                @submit="handleSubmit"
                :hideRequiredMark="true"
            >
                <a-form-item class="mb-10">
                    <a-input
                        v-decorator="[
						'username',
						{ rules: [{ required: true, message: 'Please input your username!' }] },
						]"
                        placeholder="Userame"
                    >
                    </a-input>
                </a-form-item>
                <a-form-item class="mb-5">
                    <a-input
                        v-decorator="[
						'password',
						{ rules: [{ required: true, message: 'Please input your Password!' }] },
						]"
                        type="password"
                        placeholder="Password"
                    >
                    </a-input>
                </a-form-item>
                <a-form-item class="mb-10">
                    <a-switch v-model="rememberMe" /> Remember Me
                </a-form-item>
                <a-form-item>
                    <a-button type="primary" block html-type="submit" class="login-form-button">
                        SIGN IN
                    </a-button>
                </a-form-item>
            </a-form>
            <p class="font-semibold text-muted">Don't have an account? <router-link to="/register" class="font-bold text-dark">Sign Up</router-link></p>
        </a-card>

    </div>
</template>

<script>
import { userLogin } from '../api/user'
export default ({
    data () {
        return {
            rememberMe: false
        }
    },
    beforeCreate () {
        localStorage.removeItem('token')
        this.form = this.$form.createForm(this, { name: 'normal_login' })
    },
    methods: {
        handleSubmit (e) {
            e.preventDefault()
            this.form.validateFields((err, values) => {
                if (!err) {
                    userLogin(values).then(res => {
                        if (res.data.code === 7002) {
                            localStorage.setItem('token', res.data.data)
                            this.$router.go(0)
                            this.$message.success(res.data.message)
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

<style>
	body {
        background-color: #ffffff;
	}
</style>
