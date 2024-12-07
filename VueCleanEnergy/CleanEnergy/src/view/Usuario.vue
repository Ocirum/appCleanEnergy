<template>
    <Miheader></Miheader>
    <!-- INSERTAR -->
    <fieldset>
        <label for="">Registro de Clientes</label>
        <form @submit.prevent="insertarUsuario">
            <div>
                <label for="documento">Documento</label>
                <input type="text" id="documento" v-model="frm.documento" required>
            </div>
            <div>
                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" v-model="frm.nombre" required>
            </div>
            <div>
                <label for="correo">Correo</label>
                <input type="email" id="correo" v-model="frm.correo" required>
            </div>
            <div>
                <label for="correo">Password</label>
                <input type="password" id="password" v-model="frm.password" required>
            </div>
            <div>
                <button type="submit">Registrar</button>
            </div>
        </form>
        <div>
            {{ mensajeConsulta }}
        </div>
    </fieldset>
    <!-- CONSULTAR -->
    <div v-if="cargarDatos">
        <fieldset>
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
                        <button @click="eliminarUsuario(item.documento)">Eliminar</button>
                        <button @click="cargarActualizar = true; documento = item.documento; buscarUsuario(item.documento)">
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
                <div>Datos del cliente</div>
                <form @submit.prevent="actualizarUsuario">
                    <div>
                        <label for="">Documento</label>
                        <input type="text" v-model="dataActualizar.documento" disabled>
                    </div>
                    <div>
                        <label for="">Nombre</label>
                        <input type="text" v-model="dataActualizar.nombre" required>
                    </div>
                    <div>
                        <label for="">Correo</label>
                        <input type="text" v-model="dataActualizar.correo" required>
                    </div>
                    <div>
                        <label for="">Password</label>
                        <input type="password" v-model="dataActualizar.password" required>
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
    margin: 0 0 1em 0;
    caption-side: top;
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