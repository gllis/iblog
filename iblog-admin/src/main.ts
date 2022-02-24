import { createApp } from "vue";
import App from "./App.vue";
import router from './router';
import store from './store';
import mavonEditor from 'mavon-editor'

// import "~/styles/element/index.scss";

// import ElementPlus from "element-plus";
// import all element css, uncommented next line
// import "element-plus/dist/index.css";

// or use cdn, uncomment cdn link in `index.html`

import "~/styles/index.scss";

// If you want to use ElMessage, import it.
import "element-plus/theme-chalk/src/message.scss"
import "mavon-editor/dist/css/index.css"

const app = createApp(App);
// app.use(ElementPlus);
app.use(mavonEditor);
app.use(store);
app.use(router);
app.mount("#app");
