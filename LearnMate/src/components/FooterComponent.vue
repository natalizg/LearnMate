<script setup lang="ts">
</script>

<template>
    <footer>
        <div class="container">
            <div class="content">
            <div class="col">
                <p>Contacto</p>
                <p>
                    ¡Hola! Siempre estamos abiertos a colaboraciones y sugerencias <br/>
                    contacta con nosotros y comparte tu idea.
                </p>
                <div class="flex">
                <div class="col-info">
                    <p>
                    TELÉFONO <br />
                    <span>+34 695 337 520</span>
                    </p>
                    <p>
                    DIRECCIÓN <br />
                    <span>Calle Da Vinci, La Cartuja<br />
                        41702 ESPAÑA</span>
                    </p>
                </div>
                <div class="col-info">
                    <p>
                    EMAIL <br />
                    <span>natalizg1999@gmail.com</span>
                    </p>

                    <p>
                    HORAS DE TRABAJO <br />
                    <span>Lu-Vi  8:00am - 17:00pm</span>
                    </p>
                </div>
                </div>
            </div>
            <div class="col">
                <div class="column">
                <p>Información</p>
                <p>Sobre nosotros</p>
                <p>Información de cobros</p>
                <p>Política de privacidad</p>
                <p>Marcas</p>
                <p>Contacto</p>
                <p>Devoluciones</p>
                <p>Google Maps</p>
                </div>
            </div>
            <div class="col" id="col-news">
                <p>Newsletter</p>
                <p>
                    Introduce tu email para suscribirte a nuestra newsletter <br/>
                    y mantenerte al día con nuestros descuentos y ofertas especiales.
                </p>
                <div class="email">
                <input type="email" placeholder="ejemplo@gmail.com" />
                <button>Subscribe</button>
                </div>
                <p>Síguenos en nuestras redes sociales:</p>
                <div class="social">
                <a href="https://x.com/"><img src="../assets/twitter.png" alt="" /></a>
                <a href="https://www.linkedin.com/in/natalizg/"><img src="../assets/linkedin.png" alt="" /></a>
                <a href="https://github.com/natalizg"><img src="../assets/github.png" alt="" /></a>
                <a href="https://www.youtube.com/"><img src="../assets/youtube.png" alt="" /></a>
                <a href="https://web.telegram.org/k/"><img src="../assets/telegram.png" alt="" /></a>
                </div>
            </div>
            </div>
        </div>
    </footer>
</template>

<style lang="scss" scoped>
* {
  box-sizing: border-box;
  margin: 0%;
  padding: 0%;
}

footer {
  background-color: rgb(58, 58, 58);
  .container {
    display: flex;
    justify-content: center;
    align-items: center;
    .content {
      display: flex;
      gap:100px;
      padding: 50px;
      justify-content: space-between;
      align-items: flex-start;
      .col {
        display: flex;
        flex-direction: column;
        justify-content: center;

        p:first-child:not(.flex p:first-child) {
          color: white;
          font-size: 22px;
        }
        p:not(.col, .column p:first-child) {
          color: rgb(136, 136, 136);
          margin-bottom: 10px;
        }
        &:first-child {
          gap: 25px;
          .flex {
            display: flex;
            gap: 50px;
            .col-info {
              display: flex;
              flex-direction: column;
              gap: 20px;
              p {
                font-size: 14px;
                span {
                  color: white;
                  font-size: 17px;
                }
              }
            }
          }
        }
        .column:first-child {
          p:first-child {
            margin-bottom: 25px;
          }
          p:not(p:first-child) {
            margin-bottom: 12px;
          }
        }
        &:last-child {
          gap: 15px;
          .email {
            display: flex;
            input {
              width: 300px;
              height: 40px;
              font-size: 17px;
              color: white;
              background-color: rgb(76, 76, 76);
              padding-left: 10px;
              border: 0;
              outline: 0;
            }
            button {
              width: 200px;
              height: 40px;
              color: black;
              background-color: #fbde68;
              padding-left: 10px;
              border: 0;
              outline: 0;
              text-align: center;
              margin-left: -30px;
              font-weight: bold;
              font-size: 18px;
              display: flex;
              justify-content: center;
              align-items: center;
              border-radius: 3px;
            }
          }
          .social {
            display: flex;
            gap: 20px;
            img {
              width: 40px;
            }
          }
        }
      }
    }
  }
}

.my-name{
  margin-left:45px;
  padding-bottom:30px;
  color: rgb(136, 136, 136);
  font-size: 18px;
  span{
    color:white;
  }
}

</style>
