<template>
    <div class="container">
      <div class="contact-us">
        <div class="left">
          <div class="title">
            <h1>Contáctanos</h1>
            <p>Estamos a tu disposición. Rellena tus datos y 
              nos pondremos en contacto contigo para darte 
              la información que necesites.
            </p>
          </div>
          <div class="girl">
            <img src="../assets/girl-sit.jpg" alt="">
          </div>
        </div>
        <div class="right">
          <div id="container-form">
            <form @submit.prevent="sendEmail" id="contact_form">
              <div class="name">
                <label for="name"></label>
                <input type="text" placeholder="Tu nombre" v-model="name" required>
              </div>
              <div class="email">
                <label for="email"></label>
                <input type="email" placeholder="Tu email" v-model="email" required>
              </div>
              <div class="telephone">
                <label for="telephone"></label>
                <input type="text" placeholder="Tu teléfono" v-model="telephone" required>
              </div>
              <div class="subject">
                <label for="subject"></label>
                <select v-model="subject" required>
                  <option disabled hidden value="">Me gustaría hablar:</option>
                  <option value="Ser profesor">Ser profesor</option>
                  <option value="Ser estudiante">Ser estudiante</option>
                  <option value="Otra cosa">Otra cosa</option>
                </select>
              </div>
              <div class="message">
                <label for="message"></label>
                <textarea placeholder="Me gustaría pedir información sobre..." v-model="message" cols="30" rows="5" required></textarea>
              </div>
              <div class="submit">
                <input id="form_button" type="submit" value="Enviar mensaje" />
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup lang="ts">
import { ref } from 'vue';
import emailjs from 'emailjs-com';

// Datos del formulario
const name = ref('');
const email = ref('');
const telephone = ref('');
const subject = ref('');
const message = ref('');

// Mensajes estándar
const standardMessages: Record<string, string> = {
  'Ser profesor': 'Gracias por tu interés en convertirte en profesor. Nuestro equipo se pondrá en contacto contigo pronto.',
  'Ser estudiante': 'Gracias por tu interés en convertirte en estudiante. Nuestro equipo se pondrá en contacto contigo pronto.',
  'Otra cosa': 'Gracias por contactarnos. Nuestro equipo se pondrá en contacto contigo pronto.'
};

// Configuración de EmailJS
const serviceID = 'service_qimzfen';
const templateID = 'template_hxe2dir';
const publicKey = 'joBNN1b0wQPQOD3bu';

const sendEmail = () => {
  // Mensaje basado en la selección del usuario
  const finalMessage: string = standardMessages[subject.value] || '';

  // Parámetros a enviar
  const templateParams = {
    name: name.value,
    email: email.value,
    telephone: telephone.value,
    subject: subject.value,
    message: finalMessage
  };

  // Enviar correo usando EmailJS
  emailjs.send(serviceID, templateID, templateParams, publicKey)
    .then((response) => {
      console.log('Correo enviado con éxito', response.status, response.text);
      alert('Correo enviado con éxito');
    })
    .catch((error) => {
      console.error('Error al enviar el correo', error);
      alert('Hubo un error al enviar el correo');
    });
};
</script>

<style lang="scss" scoped>
.container{
    margin-top:50px;
    display:flex;
    justify-content: center;
    align-items: center;
    .contact-us{
        display:flex;
        justify-content: center;
        align-items: flex-start;
        gap:100px;
        .left{
            width:600px;
            img{
                width:600px;
                margin-top:20px;
            }
            h1{
                font-size:50px;
            }
            p{
                font-size:18px;
                
            }
        }
        .right{
            width:600px;
            display: flex;
            justify-content: center;
            align-items: flex-start;
        }
    }
}


form {
  padding: 37.5px;
}




.email {
	float: right;
	width: 45%;
}

input[type='text'], [type='email'], select, textarea {
	background: none;
    border: none;
	border-bottom: solid 2px black;
	color: #545f6d;
	font-size: 16px;
    font-weight: 400;
	margin: 0em 0 1.875em 0;
	padding: 0 0 0.875em 0;
    text-transform: uppercase;
	width: 100%;
	box-sizing: border-box;
}

input[type='text']:focus, [type='email']:focus, textarea:focus {
	outline: none;
	padding: 0 0 0.875em 0;
}

.message {
	float: none;
}

.name {
	float: left;
	width: 45%;
}

.subject {
  width: 100%;
}

.telephone {
  width: 100%;
}

textarea {
	line-height: 150%;
	height: 150px;
	resize: none;
  width: 100%;
}


#form_button {
  background: black;
  border: solid 2px #474544;
  color: white;
  cursor: pointer;
  display: inline-block;
  font-size: 0.875em;
  font-weight: bold;
  outline: none;
  padding: 20px 35px;
  text-transform: uppercase;
}

#form_button:hover {
  background: #474544;
  color: #F2F3EB;
}



</style>
