// apis for venues

import request from '../utils/request'

// get all venues
export function getAllVenues() {
    return request({
        url: '/venue/getAll',
        method: 'post',
        data: { status: 'admin' },
        // header is the token in local storage
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// get venue information by id
export function getVenueById(id) {
    return request({
        url: '/venue/getVenueInfo/' + id,
        method: 'post',
        data: { status: 'admin' },
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// add new venue
export function addNewVenue(form) {
    return request({
        url: '/venue/addVenue',
        method: 'post',
        data: form
    })
}
