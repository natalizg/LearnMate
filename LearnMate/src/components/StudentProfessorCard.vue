<script setup lang="ts">

</script>

<template>
    <div class="teacher-card">
        <div class="card">
            <img src="../assets/asian-woman.jpg" alt="">
            <div class="card-content">
                <div class="name-description">
                    <h2>Natalia Zarzuela</h2>
                    <div class="profession">
                        Química
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="scss" scoped>
.teacher-card{
    background-color:white;
    margin:20px;
    border: 1px solid black;
    border-radius:10px;
    height:150px;
    width:300px;
    .card{
        display: flex;
        overflow:hidden;
        img{
            max-width: 100%;
            height: 150px;
            width:150px;
            border-radius: 10px;
            object-fit: cover;
            margin:0;
        }
        .card-content{
            margin:0;
            .name-description{
                margin: 0px 35px;
                p{
                    font-size:17px;
                }
                .profession{
                    background-color:lightcoral;
                    margin: 5px 0px;
                    margin-right:15px;
                    padding: 5px;
                    border-radius: 15px;
                    text-align: center;
                }
            }

        }
    }
}

</style>
