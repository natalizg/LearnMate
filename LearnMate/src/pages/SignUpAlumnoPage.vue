<template>
  <div class="login-container">
      <div class="position">
          <form @submit.prevent="handleSubmit" class="login-form">
              <h2>Registro como estudiante</h2>
              <div class="login">
                  <p>¿Ya tienes una cuenta?</p>
                  <router-link to="/login"><a href="">Ingresar</a></router-link>
              </div>
              <div class="intermedio">
                  <div class="separator">
                      <hr class="line" />
                      <span class="text">o</span>
                      <hr class="line" />
                  </div>
              </div>
              <div class="form-group">
                  <label for="nombre">Nombre</label>
                  <input type="text" id="nombre" v-model="nombre" required placeholder="Tu nombre">
              </div>
              <div class="form-group">
                  <label for="apellidos">Apellidos</label>
                  <input type="text" id="apellidos" v-model="apellidos" required placeholder="Tus Apellidos">
              </div>
              <div class="form-group" :class="{ 'error': !isTelefonoValido }">
                  <label for="telefono">Teléfono</label>
                  <div class="phone-container">
                      <input type="text" id="telefono" v-model="telefono" required placeholder="+34 695 337 520" />
                      <div class="phone-flag">
                          <img src="../assets/spain.png" alt="flag" />
                      </div>
                  </div>
                  <p v-if="!isTelefonoValido" class="error-message">Por favor introduzca un teléfono de 9 dígitos consecutivos</p>
              </div>
              <div class="form-group" :class="{ 'error': !isEmailValido }">
                  <label for="email">Correo Electrónico</label>
                  <input type="email" id="email" v-model="email" required placeholder="Tu correo electrónico">
                  <p v-if="!isEmailValido" class="error-message">El correo introducido ya está registrado. Por favor, pruebe con otro.</p>
              </div>
              <div class="form-group">
                  <label for="password">Contraseña:</label>
                  <div class="password-container">
                      <input :type="passwordFieldType" id="password" v-model="password" required placeholder="Tu contraseña" />
                      <div class="show-hide-password">
                          <button type="button" @click="togglePasswordVisibility">
                              <img :src="passwordFieldType === 'password' ? showIcon : hideIcon" alt="Toggle Visibility" />
                          </button>
                      </div>
                  </div>
              </div>
              <button type="submit">Regístrate</button>
          </form>
          <div class="policy">
              Al hacer clic en Continuar o Regístrarse, aceptas las <br/>
              <a href="">Condiciones de uso de LearnMare</a>, incluidas las <br/>
              <a href="">Condiciones de suscripción</a> y la <a href="">Poítica de privaciodad</a>
          </div>
      </div>
  </div>
</template>
  
  <script setup lang="ts">

    import { ref } from 'vue';
    import showIcon from '../assets/eye.png';
    import hideIcon from '../assets/hidden.png';
    import { PostStudent } from '../types/PostUserType/PostStudentType';
    import useLogin from '../composables/useLogin';

    const { signStudent } = useLogin();
    const email = ref('');
    const password = ref('');
    const nombre = ref('');
    const apellidos = ref('');
    const telefono = ref('');
    const passwordFieldType = ref<'password' | 'text'>('password');
    const isTelefonoValido=ref(true);
    const isEmailValido = ref(true);
  
    const togglePasswordVisibility = () => {
        passwordFieldType.value = passwordFieldType.value === 'password' ? 'text' : 'password';
    };
  
    const handleSubmit = async () => {
      isTelefonoValido.value = /^\d{9}$/.test(telefono.value);
      if(isTelefonoValido.value){
        try {
          const newUser: PostStudent = {
              email: email.value,
              nombre: nombre.value,
              apellidos: apellidos.value,
              telefono: parseInt(telefono.value),
              profesor: null,
              estudiante: null
          }
          await signStudent(newUser, password.value);
        } catch(error: any) {
          console.log(error.response.data.message)
          isEmailValido.value = false;
        }
      }

    };


  </script>
  
  <style lang="scss" scoped>
  .login-container {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom:100px;
    .registro{
        .registro-button{
            display:flex;
            justify-content: center;
            text-align: center;
            cursor:pointer;
            padding: 6px 0;
            border: 2px solid black;
            margin: 10px 0;
            border-radius: 10px;
            .left{
                width:10%;
                img{
                    padding-top:1px;
                    width:30px;
                }
            }
            .right{
                align-items: left;
                padding-top:6px;
                margin-left:5px;
                a{
                    margin-bottom:10px;
                    text-decoration: none;
                    color:black;
                    font-weight: 600;
                    font-size: 18px;
                }
            }
            &:hover {
                background-color: #dcdcdc;
            }
        }
    }
    .policy{
        margin-top:10px;
        font-size:14px;
        width:auto;
        text-align:center;
        a{
            color:black;
            font-weight: 600;
    }
  }
  
  .login-form {
    width:400px;
    margin:auto;
    background-color: white;
    padding: 20px;
    border-radius: 10px;
    .login{
        display:flex;
        p{
            font-weight: 600;
        }
        a{
            font-size: 14px;
            margin-top:17px;
            margin-left: 10px;
            color:black;
            &:hover{
                color:#54c4f6;
;
            }
        }
        }

    }
    .intermedio{
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        padding:0;
        .separator {
            display: flex;
            align-items: center;
            width: 100%;
            margin: 10px 0;
            padding:0;
            .line {
                flex-grow: 1;
                border: none;
                border-top: 1px solid #dcdcdc;
                margin: 0 10px;
            }
            .text{
                margin:5px;
            }

        }
    }
    h2 {
      margin-bottom: 20px;
      font-size: 30px;
    }
  
    .form-group {
      margin-bottom: 15px;
      text-align: left;
  
      label {
        display: block;
        margin-bottom: 10px;
      }
  
      input {
        width: 100%;
        padding: 15px;
        margin-bottom: 5px;
        border: 2px solid #dcdce4;
        border-radius: 10px;
        box-sizing: border-box;
      }
  
      .password-container {
        position: relative;
      }
  
      .show-hide-password {
        position: absolute;
        right: 10px;
        top: 50%;
        transform: translateY(-50%);
  
        button {
          cursor: pointer;
          border: none;
          background: none;
          display: flex;
          align-items: center;
          justify-content: center;
        }
  
        img {
          width: 20px;
          height: 20px;
        }
      }
      .phone-container {
        position: relative;

        input {
          padding-left: 40px;
        }
      }

      .phone-flag {
        position: absolute;
        left: 10px;
        top: 50%;
        transform: translateY(-50%);
      }
    }
    .error input {
        border: 2px solid red;
        background-color: #fcdfdf;
    }

    .error-message {
        color: red;
        font-size: 16px;
        margin:0;
    }
  
    button[type="submit"] {
      width: 100%;
      padding: 10px;
      border: 2px solid black;
      border-radius: 10px;
      background-color: #fbde68;
      color: black;
      cursor: pointer;
      font-size: 18px;
      font-weight: 600;
  
      &:hover {
        background-color: #ffba24;
      }
    }
  }
  
  input::placeholder {
    font-size: 16px;
  }
  </style>
  