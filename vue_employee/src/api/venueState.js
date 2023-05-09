// apis for venue state and booking

import request from '../utils/request'

// get day, 0 for today, 1 for tomorrow, etc.
export function getDay(day) {
    return request({
        url: '/venueState/admin/getDay/' + day,
        method: 'get',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// get all states by day
export function getAllStatesByDay(day) {
    return request({
        url: '/venueState/admin/getAllTime/' + day,
        method: 'get',
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// set venue state as open
export function setOpen(day, map) {
    return request({
        url: '/venueState/admin/openVenue/' + day,
        method: 'post',
        data: { list: map },
        headers: {
            token: localStorage.getItem('token')
        }
    })
}

// set venue state as close
export function setClose(day, map) {
    return request({
        url: '/venueState/admin/closeVenue/' + day,
        method: 'post',
        data: { list: map },
        headers: {
            token: localStorage.getItem('token')
        }
    })
}
