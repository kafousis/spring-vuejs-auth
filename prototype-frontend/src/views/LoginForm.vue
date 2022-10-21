<template>
<div class="flex justify-content-center">
    <div class="surface-card p-4 shadow-2 border-round w-full lg:w-4 md:w-6 sm:w-8">
        <div class="text-center mb-5">
            <img src="images/logo.svg" alt="Image" height="50" class="mb-3">
            <div class="text-900 text-3xl font-medium mb-3">Welcome Back</div>
        </div>

        <div>
            <label for="username" class="block text-900 font-medium mb-2">Username</label>
            <InputText id="username" type="text" class="w-full mb-3" />

            <label for="password" class="block text-900 font-medium mb-2">Password</label>
            <InputText id="password" type="password" class="w-full mb-3" />

            <div class="flex align-items-center justify-content-between mb-6">
                <div class="flex align-items-center">
                    <Checkbox id="rememberme" class="mr-2"></Checkbox>
                    <label for="rememberme">Remember me</label>
                </div>
                <a class="font-medium no-underline ml-2 text-blue-500 text-right cursor-pointer">Forgot password?</a>
            </div>

            <Button label="Sign In" icon="pi pi-user" class="w-full"></Button>
        </div>
    </div>
</div>
</template>

<script>
    export default {
        
    }
</script>