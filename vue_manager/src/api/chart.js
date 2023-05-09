// apis for making charts

import request from '../utils/request'

// venue proportion
export function getVenueProportion() {
    return request({
        url: '/user/root/getVenueProportion',
        method: 'get',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// get venue booking
export function getVenueBooking() {
    return request({
        url: '/user/root/getVenueBooking',
        method: 'get',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// get item booking
export function getItemBooking() {
    return request({
        url: '/user/root/getItemBooking',
        method: 'get',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// get weekly tendency
export function getWeeklyTendency() {
    return request({
        url: '/user/root/getWeeklyTendency',
        method: 'get',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}
