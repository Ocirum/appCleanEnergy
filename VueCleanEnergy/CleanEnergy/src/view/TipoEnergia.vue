<template>
    <Miheader></Miheader>
    <!-- INSERTAR -->
        <div class="row">
            <div class="col-4"></div>
            <form @submit.prevent="insertarTipoEnergia" class="col-4">
                <h2>Registro de Tipo de Energía</h2>
                <div>
                    <label for="tipoEnergia" class="form-label">Tipo de Energía</label>
                    <input type="text" class="form-control" id="tipoEnergia" v-model="frm.tipoEnergia" required>
                </div>
                <div>
                    <button type="submit" class="btn btn-primary col-12 mt-3">Registrar</button>
                </div>
            </form>
            <div>
                {{ mensajeConsulta }}
            </div>
        </div>
    <!-- CONSULTAR -->
    <div class="row">
        <div class="col-1"></div>
        <div v-if="cargarDatos" class="col-10">
            <fieldset>
                <h2>Consultar Tipo de energia</h2>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Tipo de Energía</th>
                        <th>Opciones</th>
                    </tr>
                    <tr v-for="(item, index) in dataConsulta" :key="index">
                        <td>{{ item.idEnergia }}</td>
                        <td>{{ item.tipoEnergia }}</td>
                        <td>
                            <button @click="eliminarTipoEnergia(item.idEnergia)"
                                class="btn btn-danger border border-3">Eliminar</button>
                            <button
                                @click="cargarActualizar = true; documento = item.idEnergia; buscarTipoEnergia(item.idEnergia)"
                                class="btn btn-primary border border-3">
                                Actualizar
                            </button>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
    </div>
    <!-- ACTUALIZAR -->
    <fieldset v-if="cargarActualizar">
        <div class="row">
            <div class="col-4"></div>
            <section class="col-4">
                <div>Datos del tipo de energia</div>
                <form @submit.prevent="actualizarTipoEnergia">
                    <div class="row">
                        <div class="col-6">
                            <label for="" class="form-label">ID</label>
                            <input type="text" class="form-control" v-model="dataActualizar.idEnergia" disabled>
                        </div>
                        <div class="col-6">
                            <label for="" class="form-label">Tipo de Energia</label>
                            <input type="text" class="form-control" v-model="dataActualizar.tipoEnergia" required>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary col-12 mt-3">Actualizar</button>
                </form>
            </section>
        </div>
    </fieldset>
</template>

<script setup>
import Miheader from '@/components/MiHeader.vue';
import { ref, onMounted } from 'vue'; //solo para las consultas
import Swal from 'sweetalert2';

// INSERTAR
const frm = ref({
    idEnergia: null,
    tipoEnergia: ""
})
const insertarTipoEnergia = async () => {
    try {
        const respuesta = await fetch("http://localhost:8080/tipoEnergia",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(frm.value)
            })
        if (!respuesta.ok) {
            throw new Error("No se registraron los datos")
        }
        Swal.fire({
            title: "Registro Ok",
            text: "Registro de tipo de energía exitoso",
            icon: "success",
            confirmButtonText: "Aceptar"
        });
        frm.value = {
            tipoEnergia: ""
        }
    } catch (error) {
        Swal.fire({
            title: "Error",
            text: "Error al registrar",
            icon: "error",
            confirmButtonText: "Aceptar"
        });
    }
}

//CONSULTAR
const dataConsulta = ref([]) //ref es para volver reactiva la variable
const cargarDatos = ref(true)
const mensajeConsulta = ref(null)

const consultarDatosTipoEnergia = async () => {
    try {
        const respuesta = await fetch("http://localhost:8080/tipoEnergia")
        if (!respuesta.ok) {
            throw new Error("Error al consultar los datos")
        }
        dataConsulta.value = await respuesta.json()
    } catch (error) {
        mensajeConsulta.value = error.mensaje
        cargarDatos.value = false
    }
}

onMounted(() => {
    consultarDatosTipoEnergia()
})

//ACTUALIZAR
//Consultar por ID
const cargarActualizar = ref(false)
const dataActualizar = ref([])

const buscarTipoEnergia = async (id) => {
    try {
        const response = await fetch(`http://localhost:8080/tipoEnergia/${id}`,
            {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            }
        )
        if (!response.ok) {
            throw new Error("Datos no se consultaron")
        }
        dataActualizar.value = await response.json()
        Swal.fire({
            title: "Consulta",
            text: "Consulta de tipo de energia exitoso",
            icon: "success",
            confirmButtonText: "Aceptar"
        });
    } catch (error) {
        Swal.fire({
            title: "Error",
            text: "Error al consultar",
            icon: "error",
            confirmButtonText: "Aceptar"
        });
    }
}
//Actualizad datos nuevos
const documento = ref("");

const actualizarTipoEnergia = async () => {
    try {
        const respuesta = await fetch(`http://localhost:8080/tipoEnergia/${documento.value}`,
            {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(dataActualizar.value)
            }
        )
        if (!respuesta.ok) {
            throw new Error("No se actualizó el cliente")
        }
        Swal.fire({
            title: "Tipo de Energia actualizado",
            text: "Tipo de Energia actualizado correctamente",
            icon: "success",
            confirmButtonText: "Aceptar"
        });
    } catch (error) {
        Swal.fire({
            title: "Error",
            text: "Error al actualizar",
            icon: "error",
            confirmButtonText: "Aceptar"
        });
    }
}
//ELIMINAR
const eliminarTipoEnergia = async (documento) => {
    try {
        const respuesta = await fetch(`http://localhost:8080/tipoEnergia/${documento}`,
            {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            }
        )
        Swal.fire({
            title: "Tipo de energia eliminado",
            text: "Tipo de energía eliminado correctamente",
            icon: "success",
            confirmButtonText: "Aceptar"
        });
        if (!respuesta.ok) {
            throw new Error("No se pudo eliminar")
        }
    } catch (error) {
        Swal.fire({
            title: "Error",
            text: "Error al eliminar",
            icon: "error",
            confirmButtonText: "Aceptar"
        });
    }
}
</script>

<style>
table {
    width: 100%;
    text-align: left;
    border-collapse: collapse;
    margin: 0 0 1em 0;
    caption-side: top;
    table-layout: fixed
}

caption,
td,
th {
    padding: 0.3em;
}

th,
td {
    border-bottom: 1px solid #000;
    border-right: 1px solid #000;
}

th:lastchild,
td:lastchild {
    border-right: 0;
}

th {
    width: 25%;
    background: rgb(0, 0, 29);
    border-top: 1px solid #000;
    color: #fff;
}
</style>