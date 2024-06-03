<script setup lang="ts">
</script>

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
                    <form action="#" method="post" id="contact_form">
                        <div class="name">
                        <label for="name"></label>
                        <input type="text" placeholder="Tu nombre" name="name" id="name_input" required>
                        </div>
                        <div class="email">
                        <label for="email"></label>
                        <input type="email" placeholder="Tu email" name="email" id="email_input" required>
                        </div>
                        <div class="telephone">
                        <label for="name"></label>
                        <input type="text" placeholder="Tu teléfono" name="telephone" id="telephone_input" required>
                        </div>
                        <div class="subject">
                        <label for="subject"></label>
                        <select placeholder="Subject line" name="subject" id="subject_input" required>
                            <option disabled hidden selected>Me gustaría hablar:</option>
                            <option>Ser profesor</option>
                            <option>Ser estudiante</option>
                            <option>Otra cosa</option>
                        </select>
                        </div>
                        <div class="message">
                        <label for="message"></label>
                        <textarea name="message" placeholder="Me gustaría pedir información sobre..." id="message_input" cols="30" rows="5" required></textarea>
                        </div>
                        <div class="submit">
                        <input type="submit" value="Send Message" id="form_button" />
                        </div>
                    </form>
                    </div>
            </div>
        </div>
    </div>
</template>

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
