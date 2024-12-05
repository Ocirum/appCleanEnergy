// Importamos los componentes necesarios para las rutas
import { createRouter, createWebHistory } from "vue-router";
import Home from "@/view/Home.vue";

// Definimos las rutas que aparecen en la barra de navegación
const routes = [
    { path: '/', name:'home', component: Home },
]

// Creamos y exportamos el router para utilizarlo en nuestro main.js
const router = createRouter({
    history: createWebHistory(),
    routes,
})

// Exportamos el router para usarlo en nuestro main.js
export default router;