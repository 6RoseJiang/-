import {postAction, getAction, putAction, deleteAction} from "@/api/manage";


export const addFlow = (param) => postAction("/flow/add", param)
export const listFlow = (param) => getAction("/flow/list", param)
export const detailFlow = (id) => getAction("/flow/detail/" + id, null)
export const deleteFlow = (id) => deleteAction("/flow/delete/" + id, null)
export const editFlow = (id, param) => putAction("/flow/edit/" + id, param)
export const addUser = (param) => postAction("/login/submit", param)
export const totalFlow = (id) => getAction("/flow/total/" + id, null)
export const listFireFighter = (param) => getAction("/firefighter/listFireFighter", param)
export const deleteFireFighter = (id) => deleteAction("/firefighter/deleteFireFighter/" + id, null)
export const addFireFighter = (param) => postAction("/firefighter/addFireFighter", param)
