<template>
    <div>
        <template>
            <a-card title="Welcome to the Sports Centre System, your one-stop destination for all your sports and fitness needs." class="mb-24">
                <p>Diverse sports facilities: Enjoy basketball, tennis, badminton, and more in our well-maintained venues.</p>
                <p>Flexible memberships: Choose from 1, 3, or 12-month plans with exclusive discounts on bookings and perks.</p>
                <p>Vibrant community: Connect with fellow sports enthusiasts in our welcoming and supportive environment.</p>
                <p>Equipment purchase: Access high-quality sports gear through our convenient purchase services.</p>
            </a-card>
        </template>
        <a-row type="flex" justify="space-around" align="middle">
            <a-col :span="24" :md="13">
                <a-card>
                    <a-carousel arrows>
                        <div
                            slot="prevArrow"
                            class="custom-slick-arrow"
                            style="left: 10px;zIndex: 1"
                        >
                            <a-icon type="left-circle" />
                        </div>
                        <div slot="nextArrow" class="custom-slick-arrow" style="right: 10px">
                            <a-icon type="right-circle" />
                        </div>
                        <div><img src="images/carousel-1.jpeg" alt="" width="100%"></div>
                        <div><img src="images/carousel-2.jpeg" alt="" width="100%"></div>
                        <div><img src="images/carousel-3.jpeg" alt="" width="100%"></div>
                        <div><img src="images/carousel-4.jpeg" alt="" width="100%"></div>
                        <div><img src="images/carousel-5.jpeg" alt="" width="100%"></div>
                    </a-carousel>
                </a-card>
            </a-col>
            <a-col :span="24" :md="9" class="mb-24">
                <CardOrderHistory></CardOrderHistory>
            </a-col>
        </a-row>
    </div>
</template>

<script>
import CardOrderHistory from '../components/Cards/CardOrderHistory'

export default ({
    components: {
        CardOrderHistory
    },
    data() {
        return {
        }
    }
})

</script>

<style scoped>
/* For demo */
.ant-carousel >>> .slick-slide {
    text-align: center;
    background: #364d79;
    overflow: hidden;
}

.ant-carousel >>> .custom-slick-arrow {
    width: 25px;
    height: 25px;
    font-size: 25px;
    color: #fff;
    background-color: rgba(31, 45, 61, 0.41);
    opacity: 0.3;
}
.ant-carousel >>> .custom-slick-arrow:before {
    display: none;
}
.ant-carousel >>> .custom-slick-arrow:hover {
    opacity: 0.5;
}

.ant-carousel >>> .slick-slide h3 {
    color: #fff;
}
</style>
