import { StoreOptions } from 'vuex'
import Storages from '~/utils/storages'
import Apis from '~/api/login'
import router from '~/router/index'

export default <StoreOptions<{ [name: string]: any }>>{
    namespaced: true,
    state: {
        token: Storages.get('token'),
        userInfo: Storages.get('userInfo') ?? {}
    },
    mutations: {
        // 登录
        LOGIN(state, params) {
            // 获取路由列表
            Apis.login(params).then(({ data: { data } }) => {
                Storages.set('userInfo', JSON.stringify(data))
                router.push('/home')
            })
        },
        // 退出登录
        LOGIN_OUT(state) {
            Storages.remove('token')
            Storages.remove('userInfo')
            state.token = null
            state.userInfo = {}
            router.push('/login')
        },
        // 修改密码
        MODIFY_PWD(state, params) {
            Apis.modifyPwd(params).then((resp) => {
                state.commit('LOGIN_OUT')
            })
        }
    },
    actions: {
        login({ commit }, params) {
            commit('LOGIN', params)
        },
        loginOut({ commit }) {
            commit('LOGIN_OUT')
        },
        modifyPwd({ commit}, params) {
            commit('MODIFY_PWD', params)
        }
    }
}