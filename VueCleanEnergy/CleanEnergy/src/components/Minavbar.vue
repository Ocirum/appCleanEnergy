<template>
    <nav>
        <ul>
            <li><router-link to="/">Home</router-link></li>
            <li><router-link to="/usuario">Usuarios</router-link></li>
            <li><router-link to="/tipoEnergia">Tipos de Energía</router-link></li>
            <li><router-link to="/consumoEnergia">Consumos de Energía</router-link></li>
            <li><router-link to="/consultar">Consultar</router-link></li>
        </ul>
    </nav>
</template>

<style>
nav ul {
    display: flex;
    gap: 10px;
    list-style: none;
}

nav ul li a {
    text-decoration: none;
    color: rgb(253, 253, 255);
}

nav ul li a:hover {
    text-decoration: underline;
    background-color: rgb(4, 196, 255);
}
</style>