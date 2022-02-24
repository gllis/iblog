import { StoreOptions } from 'vuex'
import router from '~/router/index'

export default <StoreOptions<{ [name: string]: any }>>{
    namespaced: true,
    state: {
        info: {},
    },
    mutations: {
        // 设置文章
        SET_ARTICLE_INFO(state, info) {
            state.info = info;
        }
    },
    actions: {
        set({ commit }, info) {
            commit('SET_ARTICLE_INFO', info)
        }
    }
}