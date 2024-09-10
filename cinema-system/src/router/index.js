// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import LoginPage from '../components/LoginPage.vue';
import HomePage from '../components/HomePage.vue';

const routes = [
    {
        path: '/login',
        name: 'Login',
        component: LoginPage,
    },
    {
        path: '/',
        name: 'Home',
        component: HomePage,
        meta: { requiresAuth: true }
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;

