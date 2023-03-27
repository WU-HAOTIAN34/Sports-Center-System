<template>
	<div>
		<div class="sign-up-header" style="background-image: url('images/a.jpg')">
			<div class="content">
				<h1 class="mb-5">Sign Up</h1>
			</div>
		</div>

		<!-- Sign Up Form -->
		<a-card :bordered="false" class="card-signup header-solid h-full" :bodyStyle="{paddingTop: 0}">
			<a-form
				id="components-form-demo-normal-login"
				:form="form"
				class="login-form my-25"
				@submit="handleSubmit"
			>
				<a-form-item class="mb-10">
					<a-input
						v-decorator="[
						'username',
						{ rules: [{ required: true, message: 'Please input your username!' },
						            { max: 20, message: 'Max length of username is 20'},
						            { min: 4, message: 'Min length of username is 4'}] },
						]"
						placeholder="Userame"
					>
					</a-input>
				</a-form-item>
				<a-form-item class="mb-10">
					<a-input
						v-decorator="[
						'email',
						{ rules: [{ required: true, message: 'Please input your email!' }] },
						]"
						placeholder="Email"
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
                <a-form-item class="mb-5">
                    <a-input
                        v-decorator="[
						'Confirm password',
						{ rules: [{ required: true, message: 'Please input your Password!' }] },
						]"
                        type="password"
                        placeholder="Confirm password"
                    >
                    </a-input>
                </a-form-item>
                <a-form-item class="mb-10">
                    <a-input
                        v-decorator="[
                        'name',
						{ rules: [{ required: true, message: 'Please input your name!' }] },
						]"
                        placeholder="Name"
                    >
                    </a-input>
                </a-form-item>
                <a-form-item class="mb-10">
                    <a-input
                        v-decorator="[
                        'phoneNumber',
						{ rules: [{ required: true, message: 'Please input your phoneNumber!' }] },
						]"
                        placeholder="phone number"
                    >
                    </a-input>
                </a-form-item>
                <a-form-item class="mb-10">
                    <a-input
                        v-decorator="['age']"
                        type="number"
                        placeholder="Age"
                    >
                    </a-input>
                </a-form-item>
                <a-form-item class="mb-10">
                    <a-select
                        v-decorator="['sex']"
                        placeholder="Select a option and change input text above"
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
				<a-form-item>
					<a-button type="primary" block html-type="submit" class="login-form-button">
						SIGN UP
					</a-button>
				</a-form-item>
			</a-form>
			<p class="font-semibold text-muted text-center">Already have an account? <router-link to="/login" class="font-bold text-dark">Sign In</router-link></p>
		</a-card>
		<!-- / Sign Up Form -->
	</div>
</template>

<script>

	import {userRegister} from "@/api/user";

    export default ({
		data() {
			return {
			}
		},
		beforeCreate() {
			this.form = this.$form.createForm(this, { name: 'normal_login' });
		},
		methods: {
			handleSubmit(e) {
				e.preventDefault();
				this.form.validateFields((err, values) => {
					if ( !err ) {
                        userRegister(values).then(res => {
                            console.log(res);
                            if(res.data==-1){
                                this.$message.error("username already exists")
                            }else{
                                this.$message.success("sign up successfully")
                                this.$router.push({path:"/login"})
                            }
                        })
						console.log('Received values of form: ', values) ;
					}
				});
			},
		},
	})

</script>

<style lang="scss">
</style>
