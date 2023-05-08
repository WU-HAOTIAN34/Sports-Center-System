// apis for user's card

import request from '../utils/request'

// delete card
export function deleteCard() {
    return request({
        url: '/user/deleteCard',
        method: 'post',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// add card
export function addCard(map) {
    return request({
        url: '/user/addCard',
        method: 'post',
        data: map,
        headers: {
            token: localStorage.getItem('token')
        }
    })
}
