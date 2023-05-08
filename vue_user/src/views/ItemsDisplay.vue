<template>
    <div>
        <a-card :bordered="false" class="header-solid h-full mb-24" :bodyStyle="{paddingTop: '14px'}">
            <a-row type="flex" :gutter="[24,24]" align="stretch">
                <a-col :span="24" :md="12" :xl="6" v-for="(project, index) in projects" :key="index">
                    <ItemCard
                        :id="project.id"
                        :name="project.itemName"
                        :cover="project.image"
                        :price="project.price"
                        class="mb-15"
                    ></ItemCard>
                </a-col>
            </a-row>
        </a-card>
    </div>
</template>

<script>
import ItemCard from '../components/Cards/ItemCard'

import { getAllItems } from '../api/item'

export default ({
    components: {
        ItemCard
    },
    data() {
        return {
            a: '',
            visible: false,
            projects: []
        }
    },

    beforeCreate() {
        getAllItems().then((response) => {
            this.projects = response.data.data
        })
    }

})

</script>

<style>
</style>
