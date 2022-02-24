import { createRouter, createWebHistory } from 'vue-router'


export default createRouter({
	// 指定路由模式
	history: createWebHistory(),
	// 路由地址
	routes: [
		{
			path: '/',
			redirect: '/home'
		},
		{
			path: '/login',
			name: 'Login',
			component: () => import('~/views/Login.vue')
		},
		{
			path: '/home',
			redirect: '/setting'
		},
		{
			path: '/home',
			name: 'Home',
			component: () => import('~/components/layouts/BaseLayout.vue'),
			children: [
				{
					path: '/setting',
					name: 'Setting',
					component: ()=> import('~/views/Setting.vue')
				},
				{
					path: '/tag',
					name: 'Tag',
					component: () => import('~/views/Tag.vue')
				},
				{
					path: '/article/list',
					name: 'ArticleList',
					component: () => import('~/views/article/List.vue')
				},
				{
					path: '/article/publish',
					name: 'ArticlePublish',
					component: () => import('~/views/article/publish.vue')
				}
			]
		},

	]
})