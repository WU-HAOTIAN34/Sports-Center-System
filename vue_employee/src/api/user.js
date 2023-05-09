// apis for user

import request from '../utils/request'

// admin login function
export function adminLogin(map) {
    return request({
        url: '/user/admin/login',
        method: 'post',
        data: map
    })
}

// get user profile
export function getUserProfile() {
    return request({
        url: '/user/getUserInfo',
        method: 'get',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// edit user profile
export function editUserProfile(map) {
    return request({
        url: '/user/editProfile',
        method: 'post',
        data: map
    })
}

// get all users
export function getAllUsers() {
    return request({
        url: '/user/getAll',
        method: 'get'
    })
}

// log out
export function userLogout() {
    return request({
        url: '/user/logout',
        method: 'get'
    })
}

// update user profile
export function updateUserProfile(map) {
    return request({
        url: '/user/updateInfo',
        method: 'post',
        data: map,
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// update user avatar
export function updateUserAvatar(map) {
    return request({
        url: '/user/updateProfile',
        method: 'post',
        data: map,
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// admin get all users
export function adminGetAllUsers() {
    return request({
        url: '/user/admin/getAll',
        method: 'get',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}
