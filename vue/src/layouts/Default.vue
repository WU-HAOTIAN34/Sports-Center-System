<!--
	This is the default layout, used in sign-in and sign-up pages.
 -->

<template>
	<div>
		<a-layout class="layout-default" id="layout-default" :class="[layoutClass]">
			<!-- Page Content -->
			<a-layout-content>
				<router-view />
			</a-layout-content>
			<!-- / Page Content -->

			<!-- Layout Footer -->
			<Footer></Footer>
			<!-- / Layout Footer -->

		</a-layout>
		<!-- / Default Layout -->

	</div>
</template>

<script>
	import Footer from '../components/Footer' ;

	export default ({
		components: {
			Footer,
		},
		data() {
			return {
			}
		},
		computed: {
			// Sets layout's element's class based on route's meta data.
			layoutClass() {
				return this.$route.meta.layoutClass ;
			}
		},
	})

</script>
