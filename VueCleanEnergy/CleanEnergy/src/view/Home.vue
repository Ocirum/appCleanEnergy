<template>
    <Miheader></Miheader>
    <div id="carouselExampleIndicators" class="carousel slide">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
                aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                aria-label="Slide 3"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3"
                aria-label="Slide 4"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="4"
                aria-label="Slide 5"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="5"
                aria-label="Slide 6"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="6"
                aria-label="Slide 7"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="../../public/img/Presentación_page-0001.jpg" class="d-block w-100 h-100" alt="...">
            </div>
            <div class="carousel-item">
                <img src="../../public/img/Presentación_page-0002.jpg" class="d-block w-100 h-100" alt="...">
            </div>
            <div class="carousel-item">
                <img src="../../public/img/Presentación_page-0003.jpg" class="d-block w-100 h-100" alt="...">
            </div>
            <div class="carousel-item active">
                <img src="../../public/img/Presentación_page-0004.jpg" class="d-block w-100 h-100" alt="...">
            </div>
            <div class="carousel-item active">
                <img src="../../public/img/Presentación_page-0005.jpg" class="d-block w-100 h-100" alt="...">
            </div>
            <div class="carousel-item active">
                <img src="../../public/img/Presentación_page-0006.jpg" class="d-block w-100 h-100" alt="...">
            </div>
            <div class="carousel-item active">
                <img src="../../public/img/Presentación_page-0007.jpg" class="d-block w-100 h-100" alt="...">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
            data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
            data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</template>

<script setup>
import Miheader from '@/components/MiHeader.vue';
import 'bootstrap'
</script>

<style>

</style>