<template>
    <div class="row">
        <header>

            <div class="logotitulo">
                <h1>CleanEnergy</h1>
                <img src="../../public/img/IconCleanEnergy.png"
                    alt="Android.png">
            </div>

            <Minavbar></Minavbar>
        </header>
    </div>
</template>

<script setup>
import Minavbar from './Minavbar.vue';
</script>

<style>
header {
    background: rgb(0, 0, 29);
    display: flex;
    height: 200px;
}

h1 {
    background-color: 0;
    display: flex;
    align-items: center;
    margin-left: 20px;
    color: #fff;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

.logotitulo {
    display: flex;
    align-items: center;
    justify-content: space-evenly;
    background: 0;
}

img {
    background-size: contain;
    width: 50px;
    height: 50px;


}

nav {
    background-color: 0;
    margin-left: auto;
    display: flex;
    align-items: center;
}

ul {
    background-color: 0;
}

li {
    background-color: rgba(255, 255, 255, 0.2);
    display: inline;
    margin-right: 10px;
    padding: 20px;
    border-radius: 50px 10px;
}

a {
    text-decoration: none;
    color: #05d6fa;
    font-size: 18px;
    font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
}

#contenedor1 {
    background-color: 0;
    display: flex;
    flex-wrap: nowrap;

}

.art {
    background-color: rgb(6, 124, 228);
    height: 100%;
    width: 40%;
    margin: 10px;
}

#contenedor2 {
    background: red;
    height: 600px;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-evenly;
}

.div {

    height: 200px;
    background: blue;
    margin: 2px;
    width: 30%;

}
</style>