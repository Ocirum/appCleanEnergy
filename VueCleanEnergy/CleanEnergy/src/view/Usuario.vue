<template>
    <div class="row">
        <div class="col-12">
            <Miheader></Miheader>
        </div>
    </div>
    <!-- INSERTAR -->
    <div class="row">
        <div class="col-4"></div>
        <form @submit.prevent="insertarUsuario" class="col-4">
            <h2>Registro de Usuarios</h2>
            <div class="row">
                <div class="col-6">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" class="form-control" id="nombre" v-model="frm.nombre" required>
                </div>
                <div class="col-6">
                    <label for="documento" class="form-label">Documento</label>
                    <input type="text" class="form-control" id="documento" v-model="frm.documento" required>
                </div>
            </div>
            <div>
                <label for="correo" class="form-label">Correo</label>
                <input type="email" class="form-control" id="correo" v-model="frm.correo" required>
            </div>
            <div>
                <label for="correo" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" v-model="frm.password" required>
            </div>
            <button type="submit" class="btn btn-primary col-12 mt-3">Registrar</button>
        </form>
        <div class="col-4"></div>
    </div>
    <div>
        {{ mensajeConsulta }}
    </div>
    <!-- CONSULTAR -->
    <div class="row">
        <div class="col-4"></div>
        <button @click="consultaTodo=true" class="btn btn-success col-4 mt-5 pt-3 pb-3 borde border-3" id="ConsultaTotal"><b>Consultar</b></button>
        <div class="col-4"></div>
    </div>
    <div class="row" v-if="consultaTodo">
        <div class="col-1"></div>
        <div v-if="cargarDatos" class="col-10">
            <h2>Consultar Usuario</h2>
            <table>
                <tr>
                    <th>Documento</th>
                    <th>Nombre</th>
                    <th>Correo</th>
                    <th>Opciones</th>
                </tr>
                <tr v-for="(item, index) in dataConsulta" :key="index">
                    <td>{{ item.documento }}</td>
                    <td>{{ item.nombre }}</td>
                    <td>{{ item.correo }}</td>
                    <td>
                        <button @click="eliminarUsuario(item.documento)"
                            class="btn btn-danger border border-3">Eliminar</button>
                        <button
                            @click="cargarActualizar = true; documento = item.documento; buscarUsuario(item.documento)"
                            class="btn btn-primary border border-3">
                            Actualizar
                        </button>
                    </td>
                </tr>
            </table>
        </div>
        <div class="col-1"></div>
    </div>
    <!-- ACTUALIZAR -->
    <fieldset v-if="cargarActualizar">
        <div class="row">
            <div class="col-4"></div>
            <div class="col-4">
                <div>Datos del cliente</div>
                <form @submit.prevent="actualizarUsuario">
                    <div class="row">
                        <div class="col-6">
                            <label for="" class="form-label">Nombre</label>
                            <input type="text" class="form-control" v-model="dataActualizar.nombre" required>
                        </div>
                        <div class="col-6">
                            <label for="" class="form-label">Documento</label>
                            <input type="text" class="form-control" v-model="dataActualizar.documento" disabled>
                        </div>
                    </div>
                    <div>
                        <label for="" class="form-label">Correo</label>
                        <input type="text" class="form-control" v-model="dataActualizar.correo" required>
                    </div>
                    <div>
                        <label for="" class="form-label">Password</label>
                        <input type="password" class="form-control" v-model="dataActualizar.password" required>
                    </div>
                    <button type="submit" class="btn btn-primary col-12 mt-3">Actualizar</button>
                </form>
            </div>
        </div>
    </fieldset>
</template>

<script setup>
import Miheader from '@/components/MiHeader.vue';
import { ref, onMounted } from 'vue'; //solo para las consultas
import Swal from 'sweetalert2';

const consultaTodo = ref(false)
// INSERTAR
const frm = ref({
    documento: "",
    nombre: "",
    correo: "",
    password: ""
})
const insertarUsuario = async () => {
    try {
        const respuesta = await fetch("http://localhost:8080/usuario",
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
            text: "Registro de usuario exitoso",
            icon: "success",
            confirmButtonText: "Aceptar"
        });
        frm.value = {
            documento: "",
            nombre: "",
            correo: ""
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

const consultarDatosUsuario = async () => {
    try {
        const respuesta = await fetch("http://localhost:8080/usuario")
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
    consultarDatosUsuario()
})

//ACTUALIZAR
//Consultar por ID
const cargarActualizar = ref(false)
const dataActualizar = ref([])

const buscarUsuario = async (id) => {
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

const actualizarUsuario = async () => {
    try {
        const respuesta = await fetch(`http://localhost:8080/usuario/${documento.value}`,
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
const eliminarUsuario = async (documento) => {
    try {
        const respuesta = await fetch(`http://localhost:8080/usuario/${documento}`,
            {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            }
        )
        Swal.fire({
            title: "Cliente eliminado",
            text: "Cliente eliminado correctamente",
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
    border-left: 1px solid #000;
}

th:lastchild,
td:lastchild {
    border-right: 0;
}

th {
    width: 25%;
    background: rgb(0, 0, 29);
    border-top: 1px solid #000;
    border-left: 1px solid #000;
    color: #fff;
}
#ConsultaTotal{
    color: #000;
}
</style>