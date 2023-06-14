import {createRouter, createWebHashHistory} from "vue-router";


const routes = [
    {
        path: '/firefighter',
        component: () => import('@/views/Firefighter-List.vue')
    },
    {
        path: '/login',
        component: () => import('@/views/Login.vue')
    },
    {
        path: '/index',
        component: () => import('@/views/Index.vue')
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router