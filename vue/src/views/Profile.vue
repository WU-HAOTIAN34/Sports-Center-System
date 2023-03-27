<template>
	<div>
		<div class="profile-nav-bg" style="background-image: url('images/bg-profile.jpg')"></div>

		<!-- User Profile Card -->
		<a-card :bordered="false" class="card-profile-head" :bodyStyle="{padding: 0,}">
			<template #title>
				<a-row type="flex" align="middle">
					<a-col :span="24" :md="12" class="col-info">
						<a-avatar :size="74" shape="square" src="images/face-1.jpg" />
						<div class="avatar-info">
							<h4 class="font-semibold m-0">{{username}}</h4>
						</div>
					</a-col>
					<a-col :span="24" :md="12" style="display: flex; align-items: center; justify-content: flex-end">
                        <a-button size="small">Edit </a-button>
					</a-col>
				</a-row>
			</template>
		</a-card>
		<!-- User Profile Card -->

		<a-row type="flex" :gutter="24">
			<a-col :span="24" :md="8" class="mb-24">
				<CardProfileInformation></CardProfileInformation>
			</a-col>

			<a-col :span="24" :md="8" class="mb-24">
				<CardConversations
					:data="conversationsData"
				></CardConversations>
			</a-col>
		</a-row>
	</div>
</template>

<script>

	import CardPlatformSettings from "../components/Cards/CardPlatformSettings"
	import CardProfileInformation from "../components/Cards/CardProfileInformation"
	import CardConversations from "../components/Cards/CardConversations"

	// Conversation's list data.
	const conversationsData = [
		{
			id: "1",
			title: "Sophie B.",
			code: "Hi! I need more information…",
			avatar: "images/face-3.jpg",
		},
		{
			id: "2",
			title: "Anne Marie",
			code: "Awesome work, can you…",
			avatar: "images/face-4.jpg",
		},
		{
			id: "3",
			title: "Ivan",
			code: "About files I can…",
			avatar: "images/face-5.jpeg",
		},
		{
			id: "4",
			title: "Peterson",
			code: "Have a great afternoon…",
			avatar: "images/face-6.jpeg",
		},
		{
			id: "5",
			title: "Nick Daniel",
			code: "Hi! I need more information…",
			avatar: "images/face-2.jpg",
		},
	] ;

	export default ({
		components: {
			CardPlatformSettings,
			CardProfileInformation,
			CardConversations,
		},
		data() {
			return {
                username: "admin",
				profileHeaderBtns: 'overview',
				conversationsData,

			}
		},
	})

</script>

<style lang="scss">
</style>
