// Importamos los componentes necesarios para las rutas
import { createRouter, createWebHistory } from "vue-router";
import Home from "@/view/Home.vue";
import Usuario from "@/view/Usuario.vue";
import TipoEnergia from "@/view/TipoEnergia.vue";
import ConsumoEnergia from "@/view/ConsumoEnergia.vue";
import Consultar from "@/view/Consultar.vue";

// Definimos las rutas que aparecen en la barra de navegación
const routes = [
    { path: '/', name:'home', component: Home },
    { path: '/usuario', name:'usuario', component: Usuario },
    { path: '/tipoEnergia', name:'tipo-energia', component: TipoEnergia },
    { path: '/consumoEnergia', name:'consumo-energia', component: ConsumoEnergia },
    { path: '/consultar', name: 'consultar', component: Consultar }
]

// Creamos y exportamos el router para utilizarlo en nuestro main.js
const router = createRouter({
    history: createWebHistory(),
    routes,
})

// Exportamos el router para usarlo en nuestro main.js
export default router;