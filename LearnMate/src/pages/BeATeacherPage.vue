<script setup lang="ts">

</script>

<template>
    <div class="ad">
        <div class="left">
            <h1>Hazte profesor particular y forma parte de nuestra comunidad internacinal</h1>
            <p>Disfruta de horarios flexibles, gana dinero y forma parte de nuestra comunidad internacional de profesores particulares</p>
            <router-link to="/login"><button>Inscríbete como profesor</button></router-link>
        </div>
        <div class="right">
            <img src="../assets/adprueba.jpg" alt="">
        </div>
    </div>
    <div class="row-ad">
        <div class="ser-profe">
            <h1>Empieza tu aventura como profesor online</h1>
            <p>No pierdes nada por probar, este es el inicio de un bonito viaje.</p>
        </div>
    </div>
    <div class="row-exp">
        <div class="left">
            <img src="../assets/list-img.jpg" alt="">
        </div>
        <div class="center">
            <div class="encabezado">
                <h2>Enseña a estudiantes de más de 30 países</h2>
                <p>Los profesores de LearnMate enseñan a mas de 1000 estudiantes en todo el mundo.
                    Únete a nosotros y tendrás todo lo que necesitgas para enseñar con éxito.
                </p>
            </div>
            <div class="list">
                <div class="row-1">
                    <div class="check-pic">
                    <img class="check" src="../assets/check.png" alt="Check" />
                    </div>
                    <p>Flujo constante de nuevos estudiantes</p>
                </div>
                <div class="row-1">
                    <div class="check-pic">
                    <img class="check" src="../assets/check.png" alt="Check" />
                    </div>
                    <p>Calendario inteligente</p>
                </div>
                <div class="row-1">
                    <div class="check-pic">
                    <img class="check" src="../assets/check.png" alt="Check" />
                    </div>
                    <p>Aula interactiva</p>
                </div>
                <div class="row-1">
                    <div class="check-pic">
                    <img class="check" src="../assets/check.png" alt="Check" />
                    </div>
                    <p>Métodos de pago convenientes</p>
                </div>
                <div class="row-1">
                    <div class="check-pic">
                    <img class="check" src="../assets/check.png" alt="Check" />
                    </div>
                    <p>Seminarios web de desarrollo profesional</p>
                </div>
                <div class="row-1">
                    <div class="check-pic">
                    <img class="check" src="../assets/check.png" alt="Check" />
                    </div>
                    <p>Apoyo por parte de la comunidad de profesores</p>
                </div>
            </div>
        </div>
        <div class="right">
            <img src="../assets/list-right.jpg" alt="">
        </div>

    </div>
    <div class="row-title">
        <h1>Preguntas frecuentes</h1>
    </div>
    <div class="row">
        <div class="faq">
            <details open>
                <summary>
                    ¿Qué tipo de profesores busca LearnMate?
                </summary>
                <div>
                    <p> <b>¡No se requiere una certificación específica o experiencia docente!</b></p> 
                    Damos la bienvenida a profesores quedisfrutan compartiendo conocimientos, tienen habilidades de comunicación sobresalientes
                    y están dispuestos a brindar una experiencia de aprendizaje personalizada a estudiantes internacionales
                </div>
            </details>
            <details>
                <summary>
                    ¿Es gratis crear un perfil de profesor en LearnMate?
                </summary>
                <div>
                    <p> <b>Sí. ¡Es completamente gratuito!</b></p> 
                    Es gratis crear un perfil de profesor, estar visible a los estudiantes y utilizar las herramientas
                    y los materiales de LearnMate. Sólo cobramos una comisión por las clases que enseñas.
                    La comisión para las clases comienza en el 33 % y disminuye al 18 %: cuantas más horas enseñes en LearnMate, menor será la tasa de comisión.
                </div>
            </details>
            <details>
                <summary>
                    ¿Cómo puedo hacer que mi perfil sea aprobado rápidamente?
                </summary>
                <div>
                    <p><b>Una forma segura de obtener la aprobación es seguir los consejos que se dan en cada paso del proceso de registro.</b></p>
                    Asegúrate de evitar mencionar datos de contacto, precios de las clases o información engañosa en la descripción y el video de tu perfil. Te pediremos que elimines esta información, lo que ralentizará el proceso de aprobación.
                </div>
            </details>
            <details>
                <summary>
                    ¿Por qué debería enseñar en LearnMate?
                </summary>
                <div>
                    <p><b>¡Porque es muy fácil y flexible!</b></p>
                    Ganas compartiendo sobre lo que sabes, enseñas cuando y donde quieras,
                    utilizas métodos de pago seguros (Paypal) y obtienes el apoyo de nuestro amable equipo
                     a través de seminarios web de desarrollo profesional, chats en vivo y correos electrónicos

                </div>
            </details>
        </div>
    </div>

</template>

<style lang="scss" scoped>


.row{
    display:flex;
    justify-content: center;
    align-items: center;
    .faq{
        width:50%;
        font-size: 18px;
    }
    margin-bottom:150px;
}
*, *:after, *:before {
	box-sizing: border-box;
}

:root {
	font-size: 16px;
}


*:focus {
	outline: none;
}

body > div {
	width: 90%;
	max-width: 600px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 5rem;
	margin-bottom: 5rem;
}

details div {
	border-left: 2px solid #000;
	border-right: 2px solid #000;
	border-bottom: 2px solid #000;
	padding: 1.5em;
}

details div > * + * {
	margin-top: 1.5em;
}

details + details {
	margin-top: .5rem;
}

summary {
	list-style: none;
}

summary::-webkit-details-marker {
	display: none;
}

summary {
	border: 2px solid #000;
	padding: .75em 1em;
	cursor: pointer;
	position: relative;
	padding-left: calc(1.75rem + .75rem + .75rem);
}

summary:before {
	position: absolute;
	top: 50%;
	transform: translateY(-50%);
	left: .75rem;
	content: "↓";
	width: 1.75rem;
	height: 1.75rem;
	background-color: #000;
	color: #FFF;
	display: inline-flex;
	justify-content: center;
	align-items: center;
	flex-shrink: 0;
}

details[open] summary {
	background-color: #eee;
}

details[open] summary:before {
	content: "↑";
}

summary:hover {
	background-color: #eee;
}

a {
	color: inherit;
	font-weight: 600;
	text-decoration: none;
	box-shadow: 0 1px 0 0;
}

a:hover {
	box-shadow: 0 3px 0 0;
}

.ad{
    display:flex;
    justify-content: center;
    padding-bottom:0px;
    .left{
        width:35%;
        margin-top:50px;
        padding-bottom:100px;
        h1{
            font-size: 50px;
        }
        p{
            font-size: 20px;
            margin-right:200px;
        }
        button{
            width: 25ch;
            padding: 10px;
            border: 2px solid black;
            border-radius: 10px;
            background-color: #fbde68;
            color: black;
            cursor: pointer;
            font-size: 25px;
            font-weight: 600;

        }
    }
    .right{
        width:30%;
        display:flex;
        align-items: flex-end;
        img{
            width:700px;
        }
    }
}
.row-ad{
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgb(88, 194, 255);
    margin-bottom:50px;
    .ser-profe{
        width:80%;
        h1{
            font-size:100px;
            text-align: center;
            margin-bottom:0px;
        }
        p{
            text-align: center;
            font-size:20px;
        }
        margin-bottom:30px;
    }

}

.row-title{
    display:flex;
    justify-content: center;
    align-items: center;
    h1{
        font-size: 60px;
        margin:0; 
        margin-bottom:50px;
    }
}
.list {
    margin-top: 20px;
    .row-1 {
      display: flex;
      align-items: center;
      margin-top: 10px;

      .check {
        width: 16px;
        margin-right: 10px;
        border: none;
      }

      p {
        font-size: 20px;
        margin: 0;
      }
    }
  }

  .row-exp{
    display:flex;
    justify-content: center;
    align-items: flex-start;
    margin-bottom:100px;
    .left{
        width:400px;
        img{
            width:400px;
        }
    }
    .center{
        width:500px;
        margin-top:50px;
        .encabezado{
            margin-bottom:70px;
            font-size:18px;
            p{
                font-size:16px;
                color:#a1a7b3;
            }
        }
    }
    .right{
        width:400px;
        img{
            width:400px;
        }
    }
  }
</style>
