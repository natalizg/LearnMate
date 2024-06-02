import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router'
import '@fortawesome/fontawesome-free/css/all.css';
import { setupCalendar, DatePicker } from 'v-calendar';
import 'v-calendar/style.css';
const app= createApp(App);
app.use(router)
app.use(setupCalendar, {

})
app.component('VDatePicker', DatePicker)
app.mount('#app')
