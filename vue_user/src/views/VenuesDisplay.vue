<template>
    <div>
        <a-row class="mb-24">
            <a-col :span="12">
                <a-select default-value="all" style="width: 220px" @change="handleChange">
                    <a-select-option value="all">
                        All
                    </a-select-option>
                    <a-select-option value="Table tennis venue">
                        Table tennis venue
                    </a-select-option>
                    <a-select-option value="Basketball venue">
                        Basketball venue
                    </a-select-option>
                    <a-select-option value="Volleyball venue">
                        Volleyball venue
                    </a-select-option>
                    <a-select-option value="Badminton venue">
                        Badminton venue
                    </a-select-option>
                    <a-select-option value="Tennis venue">
                        Tennis venue
                    </a-select-option>
                </a-select>
            </a-col>
            <a-col :span="12">
                <a-input-search placeholder="input search text" style="max-width: 200px;" v-model="query" @change="onSearchChange" />
            </a-col>
        </a-row>
        <a-card :bordered="false" class="header-solid h-full mb-24" :bodyStyle="{paddingTop: '14px'}">
            <a-row type="flex" :gutter="[24,24]" align="stretch">
                <a-col :span="24" :md="12" :xl="6" v-for="(project, index) in this.projFilter" :key="index">
                    <VenueCard
                        :id="project.id"
                        :name="project.name"
                        :address="project.address"
                        :cover="project.image"
                        :price="project.price"
                        class="mb-15"
                    ></VenueCard>
                </a-col>
            </a-row>
        </a-card>
    </div>
</template>

<script>
import VenueCard from '../components/Cards/VenueCard'

import { getAllVenues } from '../api/venue'

export default ({
    components: {
        VenueCard
    },
    data() {
        return {
            a: '',
            visible: false,
            projects: [],
            projFilter: [],
            key: 0
        }
    },
    beforeCreate() {
        getAllVenues().then((response) => {
            this.projects = response.data.data
            this.projFilter = this.projects
        })
    },
    methods: {
        handleChange(value) {
            if (value === 'all') {
                this.projFilter = this.projects
            } else {
                this.projFilter = this.projects.filter((project) => {
                    return project.typeName === value
                })
            }
        },
        onSearchChange() {
            if (this.query.length > 0) {
                this.projFilter = this.projects.filter((row) => {
                    for (const key in row) {
                        if (row[key]
                            .toString()
                            .toLowerCase()
                            .includes(this.query.trim().toLowerCase())) { return true }
                    }
                    return false
                })
            } else {
                this.projFilter = this.projects
            }
        }
    }

})

</script>

<style>
</style>
