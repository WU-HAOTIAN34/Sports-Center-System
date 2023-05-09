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

// manager delete a venue
export function deleteVenue(id) {
    return request({
        url: '/venue/deleteVenue/' + id,
        method: 'post',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// manager edit a venue
export function editVenue(id, form) {
    return request({
        url: '/venue/editVenue/' + id,
        method: 'post',
        data: form,
        headers: {
            token: localStorage.getItem('token')
        }
    })
}
