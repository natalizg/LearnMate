<script setup lang="ts">

</script>

<template>
    <div class="teacher-card">
        <div class="card">
            <img src="../assets/asian-woman.jpg" alt="">
            <div class="card-content">
                <div class="name-description">
                    <h2>Química</h2>
                    <div class="profession">
                        <p>13/2/24</p>
                        <p>16:30</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="scss" scoped>
.teacher-card{
    background-color:#54c4f6;
    margin:20px;
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
                    margin:0;
                }
                .profession{
                    font-weight: 600;

                    text-align: center;
                }
            }

        }
    }
}

</style>
