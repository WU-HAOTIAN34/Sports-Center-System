// api for items' comments

import request from '../utils/request'

// get item's comments by id
export function getItemCommentById(id) {
    return request({
        url: '/itemComment/getItemComment/' + id,
        method: 'get',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// get current user's item comments
export function getUserItemComment() {
    return request({
        url: '/itemComment/getUserComment',
        method: 'get',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// make item comment
export function makeItemComment(id, map) {
    return request({
        url: '/itemComment/makeItemComment/' + id,
        method: 'post',
        data: map,
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// delete item comment by id
export function deleteItemComment(id) {
    return request({
        url: '/itemComment/deleteComment/' + id,
        method: 'post',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}
