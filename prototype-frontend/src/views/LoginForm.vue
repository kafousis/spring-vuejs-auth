<template>
<div class="flex justify-content-center">
	<div class="surface-card p-4 shadow-2 border-round w-full lg:w-4 md:w-6 sm:w-8">
		<div class="text-center mb-5">
			<img src="images/logo.svg" alt="Image" height="50" class="mb-3">
			<div class="text-900 text-3xl font-medium mb-3">Welcome Back</div>
		</div>

		<Message severity="error" :closable="false" v-if="errorMessage">{{errorMessage}}</Message>

		<div>
			<div class="mb-3">
				<label for="username" class="block text-900 font-medium mb-2">Username</label>
				<InputText id="username" name="username" type="text" class="w-full mb-1" v-model="username" :class="{ 'p-invalid': v$.username.$error }" autofocus />
				<small id="username-error" class="p-error" v-if="v$.username.$error">{{ v$.username.$errors[0].$message }}</small>
			</div>

			<div class="mb-3">
				<label for="password" class="block text-900 font-medium mb-2">Password</label>
				<InputText id="password" name="password" type="password" class="w-full mb-1" v-model="password" :class="{'p-invalid': v$.password.$error}" />
				<small id="password-error" class="p-error" v-if="v$.password.$error">{{ v$.password.$errors[0].$message }}</small>
			</div>

			<div class="flex align-items-center justify-content-between mb-6">
				<div class="flex align-items-center">
					<Checkbox id="remember-me" name="remember-me" class="mr-2" :binary="true" v-model="rememberMe"></Checkbox>
					<label for="remember-me">Remember me</label>
				</div>
				<a class="font-medium no-underline ml-2 text-blue-500 text-right cursor-pointer">Forgot password?</a>
			</div>

			<Button label="Sign In" icon="pi pi-user" class="w-full" @click="validate()"></Button>
		</div>
	</div>
</div>
</template>

<script>
import AuthService from '../services/auth.service';
import useVuelidate from '@vuelidate/core';
import { required } from '@vuelidate/validators';

export default {
	data () {
		return { 
			v$: useVuelidate() 
		}
	},
	methods: {
		validate() {
			this.v$.$validate();
			if (!this.v$.$error) {
				//console.log("form validation successful");
				this.$store.dispatch('auth/sessionLogin')
			}
		}
	},
	validations() {
		return {
			username: { required },
			password: { required }
		}
	},
	mounted() {
		AuthService.getCsrfToken()
			.then(response => {
				console.log('Initial csrf token: ' + response.data.token);
			}, error => {
				console.log(error);
			});
	},
	computed: {
		username: {
			get() {
				return this.$store.state.auth.username;
			},
			set(newUsername) {
				this.$store.commit("auth/updateUsername", newUsername);
			},
		},
		password: {
			get() {
				return this.$store.state.auth.password;
			},
			set(newPassword) {
				this.$store.commit("auth/updatePassword", newPassword);
			},
		},
		rememberMe: {
			get() {
				return this.$store.state.auth.rememberMe;
			},
			set(value) {
				this.$store.commit("auth/updateRememeberMe", value);
			},
		},
		errorMessage: {
			get() {
				return this.$store.state.auth.errorMessage;
			},
		},
	},
}
</script>