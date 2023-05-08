<template>
	<a-card :bordered="false" class="header-solid h-full card-profile-information" :bodyStyle="{paddingTop: 0, paddingBottom: '16px' }" :headStyle="{paddingRight: 0,}">
		<a-descriptions :title="username" :column="1">
			<a-descriptions-item label="Full Name">
				{{ name }}
			</a-descriptions-item>
			<a-descriptions-item label="Mobile">
				{{ phoneNumber }}
			</a-descriptions-item>
			<a-descriptions-item label="Email">
				{{ email }}
			</a-descriptions-item>
			<a-descriptions-item label="Age" id="age">
				{{ age }}
			</a-descriptions-item>
            <a-descriptions-item label="Gender" v-if="sex==1">
                Male
            </a-descriptions-item>
            <a-descriptions-item label="Gender" v-else>
                Female
            </a-descriptions-item>
            <a-descriptions-item label="Membership" v-if="membership!=''">
                {{ date }}
            </a-descriptions-item>
		</a-descriptions>

        <hr class="my-25">

        <p class="text-dark">
            Perhaps they will understand Maupassant's words that life may not be as good as you think, but it will not be as bad as you think. "People are more fragile and strong than you can imagine. Sometimes, a fragile sentence can bring tears to your face, and sometimes you find yourself biting your teeth and walking a long way.".
        </p>
	</a-card>

</template>

<script>

export default ({
    props: {
        id: {
            type: Number,
            default: 0
        },
        username: {
            type: String,
            default: ''
        },
        email: {
            type: String,
            default: ''
        },
        password: {
            type: String,
            default: ''
        },
        name: {
            type: String,
            default: ''
        },
        age: {
            type: Number,
            default: 0
        },
        phoneNumber: {
            type: String,
            default: ''
        },
        sex: {
            type: String,
            default: ''
        },
        membership: {
            type: Number,
            default: 0
        },
        date: {
            type: String,
            default: ''
        }
    }
})

</script>
