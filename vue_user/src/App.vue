
<template>
	<div id="app">
		<component :is="layout">
			<router-view />
		</component>
	</div>
</template>

<script>

export default ({
    computed: {
        layout() {
            return 'layout-' + (this.$route.meta.layout || 'default').toLowerCase()
        }
    },
    methods: {
        reload () {
            this.isRouterAlive = false
            this.$nextTick(function () {
                this.isRouterAlive = true
            })
        }
    }
})

</script>

<style>
</style>
