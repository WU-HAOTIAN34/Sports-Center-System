// apis for user

import request from '../utils/request'

// login function
export function userLogin(map) {
    return request({
        url: '/user/login',
        method: 'post',
        data: map
    })
}

// register function
export function userRegister(map) {
    return request({
        url: '/user/register',
        method: 'post',
        data: map,
        headers: {
            token: localStorage.getItem('token')
        }
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

// subscribe membership
export function subscribeMembership(num) {
    return request({
        url: '/user/subscribe',
        method: 'post',
        data: { num: num },
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// verify username and email
export function verifyUsernameAndEmail(username, email) {
    return request({
        url: '/user/verifyUsername',
        method: 'post',
        data: { username: username, email: email }
    })
}
