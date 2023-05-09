<template>
    <div></div>
</template>

<script>
export default {
    beforeCreate() {
        localStorage.removeItem('token')
        this.$message.success('Logout successfully')
        this.$router.push('/admin/venues')
        this.$router.go(0)
    }
}

</script>

<style scoped>

</style>
