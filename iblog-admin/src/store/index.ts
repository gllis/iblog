import { createStore } from 'vuex';

import user from './user'
import article from './article';
export default createStore({
	mutations: {
		CHANGE_STATE(state, [key, value]: [string, any]) {
			const keys = key.split('.')
			keys.slice(0, keys.length - 1).forEach(item => {
				state = state[item]
			})
			state[keys[keys.length - 1]] = value
		}
	},
	actions: {
		changeState({ commit }, data: [string, any]) {
			commit('CHANGE_STATE', data)
		}
	},
	modules: {
		user,
		article
	}
})
