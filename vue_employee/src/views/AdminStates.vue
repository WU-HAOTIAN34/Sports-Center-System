<template>
    <div>
        <a-card>
            <div>
                <a-button-group>
                    <a-button @click="getDayTime(0)">{{ day0 }}</a-button>
                    <a-button @click="getDayTime(1)">{{ day1 }}</a-button>
                    <a-button @click="getDayTime(2)">{{ day2 }}</a-button>
                    <a-button @click="getDayTime(3)">{{ day3 }}</a-button>
                </a-button-group>
            </div>
            <h5>please select a day and choose time slots you want to open or close</h5>
            <TimePicker :data="ModalData" :key="day" :day="day" :headers="headers"></TimePicker>
        </a-card>
    </div>
</template>

<script>

import { getAllStatesByDay, getDay } from '@/api/venueState'
import TimePicker from '@/components/Cards/TimePicker.vue'
import { getAllVenues } from '@/api/venue'

export default ({
    components: {
        TimePicker
    },
    beforeMount() {
        getAllVenues().then((response) => {
            for (let i = 0; i < response.data.data.length; i++) {
                this.headers.push(response.data.data[i].name)
            }
        }).catch((error) => {
            console.log(error)
        })
        for (let i = 0; i < 4; i++) {
            getDay(i).then((response) => {
                if (i === 0) {
                    this.day0 = response.data.data
                } else if (i === 1) {
                    this.day1 = response.data.data
                } else if (i === 2) {
                    this.day2 = response.data.data
                } else {
                    this.day3 = response.data.data
                }
            }).catch((error) => {
                console.log(error)
            })
        }
    },
    data() {
        return {
            day: 5,
            day0: '',
            day1: '',
            day2: '',
            day3: '',
            numberVenues: 0,
            ModalData: [],
            headers: []
        }
    },
    methods: {
        getDayTime (day) {
            getAllStatesByDay(day).then((response) => {
                this.ModalData = response.data.data
                console.log(this.ModalData)
                this.day = day
            }).catch((error) => {
                console.log(error)
            })
        }
    }
})

</script>

<style>
</style>
