// apis for orders

import request from '../utils/request'

// get orders of current user
export function getUserOrders() {
    return request({
        url: '/order/getAllOrder',
        method: 'get',
        // header is the token in local storage
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// get venue orders that have not been commented
export function getUncommentedVenueOrders() {
    return request({
        url: '/order/getAllUncommentOrder',
        method: 'get',
        // header is the token in local storage
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// get item orders of current user
export function getUserItemOrders() {
    return request({
        url: '/itemOrder/getAllOrder',
        method: 'get',
        // header is the token in local storage
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// get item orders that have not been commented
export function getUncommentedItemOrders() {
    return request({
        url: '/itemOrder/getAllUncommentOrder',
        method: 'get',
        // header is the token in local storage
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// book venue by id
export function bookVenue(id) {
    return request({
        url: '/order/makeOrder/' + id,
        method: 'post',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// delete order by id
export function deleteOrder(id) {
    return request({
        url: '/order/deleteOrder/' + id,
        method: 'post',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// get all item orders
export function getAllItemOrders() {
    return request({
        url: '/itemOrder/admin/getAll',
        method: 'get',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// get all venue orders
export function getAllVenueOrders() {
    return request({
        url: '/order/admin/getAll',
        method: 'get',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// get venue order's receipt
export function getVenueOrderReceipt(id) {
    return request({
        url: '/order/getReceipt/' + id,
        method: 'get',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// get item order's receipt
export function getItemOrderReceipt(id) {
    return request({
        url: '/itemOrder/getReceipt/' + id,
        method: 'get',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}
