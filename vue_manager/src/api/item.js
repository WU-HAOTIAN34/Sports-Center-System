// apis for items

import request from '../utils/request'

// get all items
export function getAllItems() {
    return request({
        url: '/item/getAllItem',
        method: 'post',
        data: { status: 'admin' },
        // header is the token in local storage
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// get item info by id
export function getItemInfo(id) {
    return request({
        url: '/item/getItemInfo/' + id,
        method: 'post',
        data: { status: 'admin' },
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// manager add an item
export function addItem(map) {
    return request({
        url: '/item/addItem',
        method: 'post',
        data: map,
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// manager delete an item
export function deleteItem(id) {
    return request({
        url: '/item/deleteItem/' + id,
        method: 'post',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// manager edit an item
export function editItem(id, map) {
    return request({
        url: '/item/editItem/' + id,
        method: 'post',
        data: map,
        headers: {
            token: localStorage.getItem('token')
        }
    })
}
