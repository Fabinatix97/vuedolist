import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ListView from '@/views/ListView.vue'
import SettingsView from '@/views/SettingsView.vue'
import NotFound from '@/views/NotFound.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/list/:id',
      name: 'list',
      component: ListView,
      // route level code-splitting
      // this generates a separate chunk (List.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      //component: () => import('../views/ListView.vue'),
    },
    {
      path: '/settings',
      name: 'settings',
      component: SettingsView,
    },
    // catchall 404
    {
      path: '/:catchAll(.*)',
      name: 'not-found',
      component: NotFound,
    },
  ],
})

export default router
