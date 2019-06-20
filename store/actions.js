export const getArticles = async (store, params) => {
    return await $axios.$get('http://localhost:8080/article/list', { params: params })
}

export const getTags = async (store, params) => {
    console.info($axios);
    console.info(params);
    return await $axios.$get('http://localhost:8080/tag/list', { params: params })
}

export const getCategorys = async (store, params) => {
    return await $axios.$get('http://localhost:8080/category/list', { params: params })
}