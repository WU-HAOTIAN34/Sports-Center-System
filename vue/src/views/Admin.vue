<!--
	This is the tables page, it uses the dashboard layout in:
	"./layouts/Dashboard.vue" .
 -->

<template>
    <div>
        <a-row :gutter="24" type="flex">
            <a-col :span="24" class="mb-24">
                <CardVenueTable
                    :data="this.venueData"
                    :columns="venueColumns"
                ></CardVenueTable>
            </a-col>
        </a-row>
    </div>
</template>

<script>

import CardVenueTable from '../components/Cards/CardVenueTable' ;

import {getAllVenues} from "@/api/venue";

const venueColumns = [
    {
        title: 'ID',
        dataIndex: 'id',
    },
    {
        title: 'Name',
        dataIndex: 'name',
    },
    {
        title: 'ADDRESS',
        dataIndex: 'address',
    },
    {
        title: 'Price',
        dataIndex: 'price',
    },
    {
        title: '',
        scopedSlots: { customRender: 'editBtn' },
        width: 50,
    },
];

export default ({
    components: {
        CardVenueTable,
    },
    data() {
        return {
            venueData: [],

            venueColumns: venueColumns,
        }
    },

    beforeCreate() {
        getAllVenues().then((response) => {
            this.venueData = response.data;
            console.log(this.venueData)
        });
    },
})

</script>

<style lang="scss">
</style>
