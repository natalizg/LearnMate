<script setup lang="ts">

</script>

<template>
    <div class="teacher-card">
        <div class="card">
            <img src="../assets/asian-woman.jpg" alt="">
            <div class="card-content">
                <div class="name-description">
                    <h2>Natalia Zg</h2>
                    <p>Soy una profesora de Química de Sevilla experimentada. Me saqué la carrera hace 3 años y desde entonces
                        me dedico a dar clases a niños.
                    </p>
                </div>
                <div class="professions">
                    <div class="profession">
                        Química
                    </div>
                    <div class="profession">
                        Matemáticas (ESO)
                    </div>
                    <div class="profession">
                        Inglés (BACH)
                    </div>
                    <div class="profession">
                        Música
                    </div>
                </div>
                <div class="show-more">
                    <a href="">Show More</a>
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="scss" scoped>
.teacher-card{
    margin:20px;
    border: 1px solid black;
    border-radius:10px;
    height:630px;
    width:350px;
    .card{
        overflow:hidden;
        img{
            max-width: 100%;
            height: 300px;
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
            }
            .professions{
                margin: 20px 35px;
                display:flex;
                flex-wrap:wrap;
                .profession{
                    background-color:lightcoral;
                    margin: 5px 0px;
                    margin-right:15px;
                    padding: 5px;
                    border-radius: 15px;
                }
            }
            .show-more{
                text-align: right;
                a{
                    font-size: 17px;
                    font-weight:700;
                    color:black;
                    margin-right: 60px;
                }
            }

        }
    }
}

</style>
