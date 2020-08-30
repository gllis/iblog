import Vue from 'vue';
import {
  Pagination,
  Menu,
  Submenu,
  MenuItem,
  Input,
  Select,
  Option,
  Button,
  Table,
  TableColumn,
  Form,
  FormItem,
  Tabs,
  Tag,
  Icon,
  Row,
  Col,
  Transfer,
  Container,
  Header,
  Aside,
  Main,
  Footer,
  Loading,
  Notification,
  Dropdown,
  DropdownMenu,
  DropdownItem,
  Dialog,
  ColorPicker,
  Backtop
} from 'element-ui';

Vue.prototype.$ELEMENT = { size: 'small', zIndex: 3000 };
Vue.use(Pagination);
Vue.use(Menu);
Vue.use(Submenu);
Vue.use(MenuItem);
Vue.use(Input);
Vue.use(Select);
Vue.use(Option);
Vue.use(Button);
Vue.use(Table);
Vue.use(TableColumn);
Vue.use(Form);
Vue.use(FormItem);
Vue.use(Tabs);
Vue.use(Tag);
Vue.use(Icon);
Vue.use(Row);
Vue.use(Col);
Vue.use(Transfer);
Vue.use(Container);
Vue.use(Header);
Vue.use(Aside);
Vue.use(Main);
Vue.use(Footer);
Vue.use(Dropdown);
Vue.use(DropdownMenu);
Vue.use(DropdownItem);
Vue.use(Dialog);
Vue.use(ColorPicker);
Vue.use(Backtop);


Vue.use(Loading.directive);
Vue.prototype.$loading = Loading.service;
Vue.prototype.$notify = Notification;