<template>
    <a-card class="card-project">
        <img slot="cover" alt="example" :src="cover" width="1920" height="200"/>
        <div class="card-tag">id: {{ id }}</div>
        <h5>{{ name }}</h5>
        <p>
            price: {{ price }}
        </p>
        <a-row type="flex" :gutter="6" class="card-footer" align="middle">
            <a-col :span="12">
                <div>
                    <a-button size="small" @click="jump">Book</a-button>

                </div>
            </a-col>
        </a-row>
    </a-card>

</template>

<script>

export default ({
    props: {
        id: {
            type: Number,
            required: true
        },
        name: {
            type: String,
            default: ''
        },
        price: {
            type: Number,
            default: 0
        },
        cover: {
            type: String,
            default: ''
        }
    },
    data() {
        return {

        }
    },

    methods: {
        jump() {
            this.$router.push({
                path: '/itemDetail',
                query: {
                    id: this.id
                }
            })
        }
    }
})
</script>
