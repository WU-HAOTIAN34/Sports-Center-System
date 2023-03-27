<template>
    <component :is="navbarFixed ? 'a-affix' : 'div'" :offset-top="top">
        <a-layout-header>
            <a-row type="flex">
                <a-col :span="24" :md="6">
                    <div class="ant-page-header-heading">
                        <span class="ant-page-header-heading-title">{{ this.$route.name }}</span>
                    </div>
                </a-col>

                <a-col :span="24" :md="18" class="header-control">
                    <a-dropdown :trigger="['click']" overlayClassName="header-notifications-dropdown" :getPopupContainer="() => wrapper"></a-dropdown>
                    <a-button type="link" class="sidebar-toggler" @click="$emit('toggleSidebar', ! sidebarCollapsed) , resizeEventHandler()">
                        <svg width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path d="M16 132h416c8.837 0 16-7.163 16-16V76c0-8.837-7.163-16-16-16H16C7.163 60 0 67.163 0 76v40c0 8.837 7.163 16 16 16zm0 160h416c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16H16c-8.837 0-16 7.163-16 16v40c0 8.837 7.163 16 16 16zm0 160h416c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16H16c-8.837 0-16 7.163-16 16v40c0 8.837 7.163 16 16 16z"/></svg>
                    </a-button>

                    <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Type here…" @search="onSearch" :loading='searchLoading'>
                        <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z" fill="#111827"/>
                        </svg>
                    </a-input-search>
                </a-col>
            </a-row>
        </a-layout-header>
    </component>
</template>

<script>

export default ({
    props: {
        // Header fixed status.
        navbarFixed: {
            type: Boolean,
            default: false,
        },

        // Sidebar collapsed status.
        sidebarCollapsed: {
            type: Boolean,
            default: false,
        },
    },
    data() {
        return {
            // Fixed header/sidebar-footer ( Affix component ) top offset.
            top: 0,

            // Search input loading status.
            searchLoading: false,

            // The wrapper element to attach dropdowns to.
            wrapper: document.body,
        }
    },
    methods: {
        resizeEventHandler(){
            this.top = this.top ? 0 : -0.01 ;
            // To refresh the header if the window size changes.
            // Reason for the negative value is that it doesn't activate the affix unless
            // scroller is anywhere but the top of the page.
        },
        onSearch(value){
        }
    },
    mounted: function(){
        // Set the wrapper to the proper element, layout wrapper.
        this.wrapper = document.getElementById('layout-dashboard') ;
    },
    created() {
        // Registering window resize event listener to fix affix elements size
        // error while resizing.
        window.addEventListener("resize", this.resizeEventHandler);
    },
    destroyed() {
        // Removing window resize event listener.
        window.removeEventListener("resize", this.resizeEventHandler);
    },
})

</script>
