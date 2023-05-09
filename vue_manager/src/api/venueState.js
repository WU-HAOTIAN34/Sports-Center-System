// apis for venue state and booking

import request from '../utils/request'

// get venue state from id
export function getVenueTime(id) {
    return request({
        url: '/venueState/getTime/' + id,
        method: 'get',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

export function getAllStates() {
    return request({
        url: '/venueState/getAll',
        method: 'get'
    })
}
