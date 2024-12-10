<template>
    <Miheader></Miheader>
    <!-- CONSULTAR -->
    <div class="row">
        <div class="col-1"></div>
        <div class="col-10">
            <div class="row">
                <h2>Consultar Usuario</h2>
                <div class="row">
                    <div class="col-2"></div>
                    <div class="col-4">
                        <label for="usuarioDocumento" class="form-label">Documento de usuario</label>
                        <input type="text" class="form-control mb-3" id="documento" v-model="documento" required>
                    </div>
                    <div class="col-4 d-flex align-items-center mt-3">
                        <button type="button" class="btn btn-primary col-12"
                            @click="consultarPorIdUsuario">Consultar</button>
                    </div>
                </div>
            </div>

            <table>
                <tr>
                    <th>ID Consumo</th>
                    <th>Documento</th>
                    <th>Tipo de Energía</th>
                    <th>Fecha</th>
                    <th>Consumo</th>
                    <th>Unidad de medida</th>
                    <th>Opciones</th>
                </tr>
                <tr v-for="(item, index) in frm" :key="index">
                    <td>{{ item.id_consumo }}</td>
                    <td>{{ item.usuario.documento }}</td>
                    <td>{{ item.tipoEnergia.tipoEnergia }}</td>
                    <td>{{ item.fecha }}</td>
                    <td>{{ item.consumo }}</td>
                    <td>{{ item.unidadMedida }}</td>
                    <td>
                        <button @click="eliminarConsumoEnergia(item.id_consumo)" class="btn btn-danger border border-3">
                            Eliminar</button>
                        <button
                            @click="cargarActualizar = true; documento = item.id_consumo; buscarConsumoEnergia(item.id_consumo)"
                            class="btn btn-primary border border-3">
                            Actualizar
                        </button>
                    </td>
                </tr>
            </table>
            <div class="col-1"></div>
        </div>
    </div>
</template>

<script setup>
import Miheader from '@/components/MiHeader.vue';
import { ref, onMounted } from 'vue'; //solo para las consultas
import Swal from 'sweetalert2';

const frm = ref([])
const documento = ref("")

const consultarPorIdUsuario = async () => {
    try {
        const response = await fetch(`http://localhost:8080/consumoEnergia/usuario/${documento.value}`,
            {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            }
        )
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }
        frm.value = await response.json();
    } catch (error) {
        Swal.fire({
            title: "Error",
            text: "Error al consultar",
            icon: "error",
            confirmButtonText: "Aceptar"
        });
    }
}

</script>