// apis for venues' comments

import request from '../utils/request'

export function getVenueCommentById(id) {
    return request({
        url: '/comment/getVenueComment/' + id,
        method: 'get',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// get current user's venue comments
export function getUserVenueComment() {
    return request({
        url: '/comment/getUserComment',
        method: 'get',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// make comment
export function makeVenueComment(id, map) {
    return request({
        url: '/comment/makeComment/' + id,
        method: 'post',
        data: map,
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// delete venue comment by id
export function deleteVenueComment(id) {
    return request({
        url: '/comment/deleteComment/' + id,
        method: 'post',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}
