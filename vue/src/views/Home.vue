<template>
    <div>
        <a-row :gutter="24">
            <a-col :span="24" :lg="12" :xl="6" class="mb-24" v-for="(stat, index) in stats" :key="index">
                <WidgetCounter
                    :title="stat.title"
                    :value="stat.value"
                    :prefix="stat.prefix"
                    :suffix="stat.suffix"
                    :icon="stat.icon"
                    :status="stat.status"
                ></WidgetCounter>
            </a-col>
        </a-row>


        <a-card :bordered="false" class="header-solid h-full mb-24" :bodyStyle="{paddingTop: '14px'}">
            <template #title>
                <h6 class="font-semibold">{{a}}</h6>
            </template>

            <a-row type="flex" :gutter="[24,24]" align="stretch">
                <a-col :span="24" :md="12" :xl="6" v-for="(project, index) in projects" :key="index">
                    <CardProject
                        :id="project.id"
                        :name="project.name"
                        :address="project.address"
                        :cover="project.cover"
                        :price="project.price"
                        class="mb-15"
                    ></CardProject>
                </a-col>
            </a-row>
        </a-card>
    </div>
</template>

<script>
// Counter Widgets
import WidgetCounter from '../components/Widgets/WidgetCounter' ;
import CardProject from "../components/Cards/CardProject";

// Counter Widgets stats
const stats = [
    {
        title: "Real-time attendance",
        value: 32,
        icon: `
						<svg width="22" height="22" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
							<path d="M9 6C9 7.65685 7.65685 9 6 9C4.34315 9 3 7.65685 3 6C3 4.34315 4.34315 3 6 3C7.65685 3 9 4.34315 9 6Z" fill="#111827"/>
							<path d="M17 6C17 7.65685 15.6569 9 14 9C12.3431 9 11 7.65685 11 6C11 4.34315 12.3431 3 14 3C15.6569 3 17 4.34315 17 6Z" fill="#111827"/>
							<path d="M12.9291 17C12.9758 16.6734 13 16.3395 13 16C13 14.3648 12.4393 12.8606 11.4998 11.6691C12.2352 11.2435 13.0892 11 14 11C16.7614 11 19 13.2386 19 16V17H12.9291Z" fill="#111827"/>
							<path d="M6 11C8.76142 11 11 13.2386 11 16V17H1V16C1 13.2386 3.23858 11 6 11Z" fill="#111827"/>
						</svg>`,
    },
] ;

import {getAllVenues} from '../api/venue';
import {getUserSession} from "@/api/user";

export default ({
    components: {
        WidgetCounter,
        CardProject
    },
    data() {
        return {
            a:'',
            visible: false,
            stats,
            projects:[],
        }
    },

    beforeCreate() {
        getUserSession(12345678).then((response) => {
            console.log(response)
            window.sessionStorage.setItem("user", response.data);
        });
        getAllVenues().then((response) => {
            this.a = window.sessionStorage.getItem('user');
            console.log(this.a);
            let img = ['images/venue-1.jpeg', 'images/venue-2.jpeg', 'images/venue-3.jpeg', "images/venue-4.jpeg", "images/venue-5.jpeg"];
            this.projects = response.data;
            for (let i = 0; i < this.projects.length; i++) {
                let index = parseInt(Math.random() * img.length);
                this.projects[i].cover = img[index];
            }
        });
    },

    methods: {
        showModal() {
            this.visible = true;
        },
        handleOk(e) {
            console.log(e);
            this.visible = false;
        },
    },
})



</script>

<style lang="scss">
</style>
