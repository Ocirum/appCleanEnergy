<template>
    <Miheader></Miheader>
    <!-- INSERTAR -->
    <fieldset>
        <label for="">Registro de Consumo de Energía</label>
        <form @submit.prevent="insertarConsumoEnergia">
            <div>
                <label for="usuarioDocumento">Documento de usuario</label>
                <input type="text" id="documento" v-model="frm.usuario.documento" required>
                <button type="button" @click="cargarUsuario(frm.usuario.documento)">Consultar</button>
            </div>
            <div v-if="cargarDatosUsuario">
                <label for="tipoEnergia">Tipo de energia</label>
                <input type="text" id="documento" v-model="frm.tipoEnergia.tipoEnergia" required>
                <button type="button" @click="cargarTipoEnergia(frm.tipoEnergia.tipoEnergia)">Consultar</button>
            </div>
            <div v-if="cargarDatosTipoEnergia">
                <div>
                    <label for="fecha">Fecha</label>
                    <input type="date" id="fecha" v-model="frm.fecha" required>
                </div>
                <div>
                    <label for="consumo">Consumo</label>
                    <input type="text" id="consumo" v-model="frm.consumo" required>
                </div>
                <div>
                    <label for="unidadMedida">Unidad de medida</label>
                    <input type="text" id="unidadMedida" v-model="frm.unidadMedida" required>
                </div>
            </div>
            <div v-if="cargarDatosTipoEnergia">
                <button type="submit">Registrar</button>
            </div>
        </form>
    </fieldset>
    <!-- CONSULTAR -->
    <div v-if="cargarDatos">
        <fieldset>
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
                        <button @click="eliminarConsumoEnergia(item.id_consumo)">Eliminar</button>
                        <button
                            @click="cargarActualizar = true; documento = item.id_consumo; buscarConsumoEnergia(item.id_consumo)">
                            Actualizar
                        </button>
                    </td>
                </tr>
            </table>
        </fieldset>
    </div>
    <!-- ACTUALIZAR -->
    <fieldset v-if="cargarActualizar">
        <section>
            <div>Datos del registro de consumo</div>
            <form @submit.prevent="actualizarConsumoEnergia">
                <div>
                    <label for="">ID</label>
                    <input type="text" v-model="dataActualizar.id_consumo" disabled>
                </div>
                <div>
                    <label for="">Fecha</label>
                    <input type="date" id="fecha" v-model="dataActualizar.fecha" required>
                </div>
                <div>
                    <label for="">Consumo</label>
                    <input type="text" v-model="dataActualizar.consumo" required>
                </div>
                <div>
                    <label for="">Unidad de medida</label>
                    <input type="text" v-model="dataActualizar.unidadMedida" required>
                </div>
                <button type="submit">Actualizar</button>
            </form>
        </section>
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
            text: "Consulta usuario ok",
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