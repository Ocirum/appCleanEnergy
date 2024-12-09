<template>
    <Miheader></Miheader>
    <!-- INSERTAR -->
    <div class="row">
        <div class="col-4"></div>
        <form @submit.prevent="insertarConsumoEnergia" class="col-4">
            <h2>Registro de Consumo de Energía</h2>
            <div class="row">
                <div class="col-6">
                    <label for="usuarioDocumento" class="form-label">Documento de usuario</label>
                    <input type="text" class="form-control" id="documento" v-model="frm.usuario.documento" required>
                </div>
                <div class="col-6 d-flex align-items-center">
                    <button type="button" class="btn btn-primary col-12"
                        @click="cargarUsuario(frm.usuario.documento)">Consultar</button>
                </div>
            </div>
            <div v-if="cargarDatosUsuario" class="row">
                <div class="col-6">
                    <label for="tipoEnergia" class="form-label">Tipo de energia</label>
                    <input type="text" class="form-control" id="documento" v-model="frm.tipoEnergia.tipoEnergia"
                        required>
                </div>
                <div class="col-6 d-flex align-items-center">
                    <button type="button" class="btn btn-primary col-12"
                        @click="cargarTipoEnergia(frm.tipoEnergia.tipoEnergia)">Consultar</button>
                </div>

            </div>
            <div v-if="cargarDatosTipoEnergia">
                <div>
                    <label for="fecha" class="form-label">Fecha</label>
                    <input type="date" class="form-control" id="fecha" v-model="frm.fecha" required>
                </div>
                <div>
                    <label for="consumo" class="form-label">Consumo</label>
                    <input type="text" class="form-control" id="consumo" v-model="frm.consumo" required>
                </div>
                <div>
                    <label for="unidadMedida" class="form-label">Unidad de medida</label>
                    <input type="text" class="form-control" id="unidadMedida" v-model="frm.unidadMedida" required>
                </div>
            </div>
            <div v-if="cargarDatosTipoEnergia">
                <button type="submit" class="btn btn-primary col-12 mt-3">Registrar</button>
            </div>
        </form>
    </div>
    <!-- CONSULTAR -->
    <div class="row">
        <div class="col-1"></div>
        <div v-if="cargarDatos" class="col-10">

            <h2>Consultar Usuario</h2>
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
                <tr v-for="(item, index) in dataConsulta" :key="index">
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
    <!-- ACTUALIZAR -->
    <fieldset v-if="cargarActualizar">
        <div class="row">
            <div class="col-4"></div>
            <section class="col-4">
                <div>Datos del registro de consumo</div>
                <form @submit.prevent="actualizarConsumoEnergia">
                    <div>
                        <label for="" class="form-label">ID</label>
                        <input type="text" class="form-control" v-model="dataActualizar.id_consumo" disabled>
                    </div>
                    <div>
                        <label for="" class="form-label">Fecha</label>
                        <input type="date" class="form-control" id="fecha" v-model="dataActualizar.fecha" required>
                    </div>
                    <div>
                        <label for="" class="form-label">Consumo</label>
                        <input type="text" class="form-control" v-model="dataActualizar.consumo" required>
                    </div>
                    <div>
                        <label for="" class="form-label">Unidad de medida</label>
                        <input type="text" class="form-control" v-model="dataActualizar.unidadMedida" required>
                    </div>
                    <button type="submit" class="btn btn-primary col-12 mt-3">Actualizar</button>
                </form>
            </section>
        </div>
        <div class="col-4"></div>
    </fieldset>
</template>

<script setup>
import Miheader from '@/components/MiHeader.vue';
import { ref, onMounted } from 'vue'; //solo para las consultas
import Swal from 'sweetalert2';

// INSERTAR
const frm = ref({
    id_consumo: null,
    usuario: {
        documento: "",
        nombre: "",
        correo: "",
        password: ""
    },
    tipoEnergia: {
        idEnergia: "",
        tipoEnergia: ""
    },
    fecha: "",
    consumo: "",
    unidadMedida: ""
},)
//Consulta por ID del USUARIO
const dataUsuario = ref([])
const cargarDatosUsuario = ref(false)
const cargarUsuario = async (id) => {
    try {
        const response = await fetch(`http://localhost:8080/usuario/${id}`,
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
        dataUsuario.value = await response.json()
        frm.value.usuario = dataUsuario.value
        Swal.fire({
            title: "Consulta",
            text: "Consulta usuario ok",
            icon: "success",
            confirmButtonText: "Aceptar"
        });
        cargarDatosUsuario.value = true
    } catch (error) {
        Swal.fire({
            title: "Error",
            text: "Error al consultar",
            icon: "error",
            confirmButtonText: "Aceptar"
        });
    }
}
//Consultar por tipo de energia
const dataTipoEnergia = ref([])
const cargarDatosTipoEnergia = ref(false)
const cargarTipoEnergia = async (tipoEnergia) => {
    try {
        const response = await fetch(`http://localhost:8080/tipoEnergia/tipo/${tipoEnergia}`,
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
        dataTipoEnergia.value = await response.json()
        frm.value.tipoEnergia.idEnergia = dataTipoEnergia.value[0].idEnergia
        frm.value.tipoEnergia.tipoEnergia = dataTipoEnergia.value[0].tipoEnergia
        Swal.fire({
            title: "Consulta",
            text: "Consulta tipo de energía ok",
            icon: "success",
            confirmButtonText: "Aceptar"
        });
        cargarDatosTipoEnergia.value = true
    } catch (error) {
        Swal.fire({
            title: "Error",
            text: "Error al consultar",
            icon: "error",
            confirmButtonText: "Aceptar"
        });
    }
}

const insertarConsumoEnergia = async () => {
    try {
        const respuesta = await fetch("http://localhost:8080/consumoEnergia",
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
            text: "Registro de consumo exitoso",
            icon: "success",
            confirmButtonText: "Aceptar"
        });
        frm.value = {
            id_consumo: null,
            usuario: {
                documento: "",
                nombre: "",
                correo: "",
                password: ""
            },
            tipoEnergia: {
                idEnergia: "",
                tipoEnergia: ""
            },
            fecha: "",
            consumo: "",
            unidadMedida: ""
        }
        cargarDatosUsuario.value = false
        cargarDatosTipoEnergia.value = false
    } catch (error) {
        Swal.fire({
            title: "Error",
            text: "Error al registrar",
            icon: "error",
            confirmButtonText: "Aceptar"
        });
    }
}

const dataConsulta = ref([])
const cargarDatos = ref(true)
const mensajeConsulta = ref(null)

//CONSUTLAR
const consultarConsumoEnergia = async () => {
    try {
        const respuesta = await fetch("http://localhost:8080/consumoEnergia")
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
    consultarConsumoEnergia()
})

//ACTUALIZAR
//Consultar por ID
const cargarActualizar = ref(false)
const dataActualizar = ref([])

const buscarConsumoEnergia = async (id) => {
    try {
        const response = await fetch(`http://localhost:8080/consumoEnergia/${id}`,
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
            text: "Consulta usuario ok",
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

const actualizarConsumoEnergia = async () => {
    try {
        const respuesta = await fetch(`http://localhost:8080/consumoEnergia/${documento.value}`,
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
            title: "Usuario actualizado",
            text: "Usuario actualizado correctamente",
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
const eliminarConsumoEnergia = async (id_consumo) => {
    try {
        const respuesta = await fetch(`http://localhost:8080/consumoEnergia/${id_consumo}`,
            {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            }
        )
        Swal.fire({
            title: "Registro eliminado",
            text: "Registro eliminado correctamente",
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