// apis for chat room

import request from '../utils/request'

// get all chat messages
export function getAllChatMessages() {
    return request({
        url: '/speak/getAllSpeak',
        method: 'get',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// send a message
export function sendMessage(map) {
    return request({
        url: '/speak/sendSpeak',
        method: 'post',
        data: map,
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// admin delete message
export function deleteMessage(id) {
    return request({
        url: '/speak/admin/deleteSpeak/' + id,
        method: 'post',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}
