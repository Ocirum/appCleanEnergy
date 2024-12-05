<template>
    <Miheader></Miheader>
    <div v-if="cargarDatos"></div>
    <h2>Consultar Usuario</h2>
    <table>
        <tr>
            <th>Documento</th>
            <th>Nombre</th>
            <th>Correo</th>
        </tr>
        <tr v-for="(item, index) in data" :key="index">
            <td>{{ item.documento }}</td>
            <td>{{ item.nombre }}</td>
            <td>{{ item.correo }}</td>
        </tr>
    </table>
</template>

<script setup>
import Miheader from '@/components/MiHeader.vue';
import { ref, onMounted } from 'vue'; //solo para las consultas

const data = ref([]) //ref es para volver reactiva la variable
const cargarDatos = ref(true)
const mensaje = ref(null)

const consultarDatosCliente = async () => {
    try {
        const respuesta = await fetch("http://localhost:8080/usuario")
        if (!respuesta.ok) {
            throw new Error("Error al consultar los datos")
        }
        data.value = await respuesta.json()
    } catch (error) {
        mensaje.value = error.mensaje
    } finally {
        cargarDatos.value = false
    }
}

onMounted(() => {
        consultarDatosCliente()
})
</script>