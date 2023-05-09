package com.gym1.util;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/comment/**")
                .addPathPatterns("/itemComment/**")
                .addPathPatterns("/order/**")
                .addPathPatterns("/itemOrder/**")
                .addPathPatterns("/venueState/**")
                .addPathPatterns("/speak/**")
                .addPathPatterns("/venueType/**")
                .addPathPatterns("/venue/getVenueInfo/**")
                .addPathPatterns("/venue/addVenue")
                .addPathPatterns("/venue/editVenue/**")
                .addPathPatterns("/venue/deleteVenue/**")
                .addPathPatterns("/item/addItem")
                .addPathPatterns("/item/getItemInfo/**")
                .addPathPatterns("/item/editItem/**")
                .addPathPatterns("/item/deleteItem/**")
                .addPathPatterns("/user/updateInfo")
                .addPathPatterns("/user/getUserInfo")
                .addPathPatterns("/user/updateProfile")
                .addPathPatterns("/user/admin/getAll")
                .addPathPatterns("/user/root/manageAdmin/**")
                .addPathPatterns("/user/addCard")
                .addPathPatterns("/user/deleteCard")
                .addPathPatterns("/user/subscribe")
                .addPathPatterns("/user/root/getVenueProportion")
                .addPathPatterns("/user/root/getVenueBooking")
                .addPathPatterns("/user/root/getItemBooking")
                .addPathPatterns("/user/root/getWeeklyTendency")

                .excludePathPatterns("/user/register")
                .excludePathPatterns("/user/admin/login")
                .excludePathPatterns("/user/root/login")
                .excludePathPatterns("/user/verifyUsername")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/item/getAllItem")
                .excludePathPatterns("/venue/getAll");
    }
}
