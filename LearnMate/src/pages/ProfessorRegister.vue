<template>
  <div class="login-container">
    <div class="position">
      <form @submit.prevent="handleSubmit" class="login-form">
        <h2 v-if="currentStep === 1">Acerca de</h2>
        <h2 v-else-if="currentStep === 2">Foto de perfil</h2>
        <h2 v-else>Completa tu perfil</h2>

        <div v-if="currentStep === 1" class="step">
          <div class="login">
            <p>
              Comienza a crear tu perfil público de profesor. Cada apartado se irá guardando automáticamente
              a medida que se vaya completando. Puedes volver en cualquier momento para acabar el proceso de
              registro.
            </p>
          </div>
          <div class="form-group">
            <label for="nombre">Nombre</label>
            <input type="text" id="nombre" v-model="nombre" required placeholder="Tu nombre">
          </div>
          <div class="form-group">
            <label for="apellidos">Apellidos</label>
            <input type="text" id="apellidos" v-model="apellidos" required placeholder="Tus Apellidos">
          </div>
          <div class="form-group">
            <label for="materia">Materia que enseñas</label>
            <select id="materia" v-model="materia" required>
              <option value="">Selecciona una materia</option>
              <option value=1>Español</option>
              <option value=2>Inglés</option>
              <option value=3>Francés</option>
              <option value=4>Chino</option>
              <option value=5>Alemán</option>
              <option value=6>Coreano</option>
              <option value=7>Japonés</option>
              <option value=8>Portugués</option>
              <option value=9>Italiano</option>
              <option value=10>Árabe</option>
              <option value=11>Ruso</option>
            </select>
          </div>
          <div class="form-group">
            <label for="telefono">Teléfono</label>
            <div class="phone-container">
              <input type="text" id="telefono" v-model="telefono" required placeholder="+34 695 337 520" />
              <div class="phone-flag">
                <img src="../assets/spain.png" alt="flag" />
              </div>
            </div>
          </div>
          <button type="button" :disabled="!step1IsValid" @click="nextStep">Siguiente</button>
        </div>

        <!-- PASO 2 -->
        <div v-else-if="currentStep === 2" class="step">
          <div class="login">
            <p>
              Elige una foto que ayude a los estudiantes a conocerte.
            </p>
          </div>
          <div class="upload-photo">
            <hr>
            <div class="row">
              <div class="profile-pic">
                <img src="../assets/asian-woman.jpg" alt="">
              </div>
              <div class="profile-data">
                <h3>{{ nombre }} {{ apellidos }}</h3>
                <p>Profesora de {{ materia }}</p>
              </div>
            </div>
            <hr>
            <button class="btn-pic"> Subir foto</button>
          </div>
          <div class="column">
            <h3>Lo que necesita tu foto</h3>
            <div class="row">
              <img src="../assets/pp1.jpg" alt="">
              <img src="../assets/pp2.jpg" alt="">
              <img src="../assets/pp3.jpg" alt="">
              <img src="../assets/pp4.jpg" alt="">
            </div>
            <div class="list">
              <div class="row">
                <div class="check-pic">
                  <img class="check" src="../assets/check.png" alt="">
                </div>
                <p>Debes mirar hacia delante</p>
              </div>
              <div class="row">
                <div class="check-pic">
                  <img class="check" src="../assets/check.png" alt="">
                </div>
                <p>Enmarca la cabeza y los hombros</p>
              </div>
              <div class="row">
                <div class="check-pic">
                  <img class="check" src="../assets/check.png" alt="">
                </div>
                <p>Asegúrate de que está centrada y en posición vertical</p>
              </div>
              <div class="row">
                <div class="check-pic">
                  <img class="check" src="../assets/check.png" alt="">
                </div>
                <p>Tu cara y ojos deben estar completamente visibles (a excepción de haber razones religiosas o
                  culturales)</p>
              </div>
              <div class="row">
                <div class="check-pic">
                  <img class="check" src="../assets/check.png" alt="">
                </div>
                <p>Deberías ser la única persona en la foto</p>
              </div>
            </div>
          </div>
          <button class="volver" @click="prevStep">Volver atrás</button>
          <button type="button" @click="nextStep">Siguiente</button>
        </div>

        <!-- PASO 3 -->
        <div v-else class="step">
          <div class="login">
            <p>
              ¡Muestra a los estudiantes potenciales quién eres! Comparte tu experiencia docente y tu pasión por la
              educación, y menciona brevemente tus intereses y aficiones.
            </p>
          </div>
          <div class="form-group">
            <label for="precio">Precio/hora</label>
            <input type="number" id="precio" v-model="precio" required placeholder="Precio la hora">
          </div>
          <div class="form-group">
            <label for="tramosHorarios">Selecciona tus tramos horarios</label>
            <div class="tramos-horarios">
              <button
                v-for="(tramo, index) in tramosHorarios"
                :key="index"
                :class="{ selected: selectedTramos.includes(tramo.id) }"
                @click.prevent="toggleTramo(tramo.id)"
              >
                {{ tramo.label }}
              </button>
            </div>
          </div>
          <div class="form-group">
            <label for="descripcion">Sobre ti</label>
            <textarea id="descripcion" v-model="descripcion" required
              placeholder="Escribe una breve descripción sobre ti y tu experiencia."></textarea>
          </div>
          <button class="volver" @click="prevStep">Volver atrás</button>
          <button type="submit">Finalizar</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';
import useLogin from '../composables/useLogin';
import { PostUser } from '../types/PostUserType/PostUserType';
const { emailProf, signProfessor } = useLogin();
const currentStep = ref(1);
const materia = ref(0);
const telefono = ref('');
const nombre = ref('');
const apellidos = ref('');
const descripcion = ref('');
const precio = ref(0);
const tramosHorarios = ref([
  { id: 1, label: '9-12' },
  { id: 2, label: '12-15' },
  { id: 3, label: '15-18' },
  { id: 4, label: '18-21' },
  { id: 5, label: '21-00' }
]);
const selectedTramos = ref<number[]>([]);

const step1IsValid = computed(() => {
  return nombre.value !== '' && apellidos.value !== '' && materia.value !== 0 && telefono.value !== '';
});

const nextStep = () => {
  currentStep.value += 1;
};

const prevStep = () => {
  currentStep.value -= 1;
};

const handleSubmit = () => {

  const newUser: PostUser =  {
    email: emailProf.value,
    nombre: nombre.value,
    apellidos: apellidos.value,
    telefono: parseInt(telefono.value),
    profesor: {
      descripcion: descripcion.value,
      precio: precio.value,
      materia: {
        idMateria: materia.value
      },
      tramosHorarios: selectedTramos.value.map(id => ({ idTramoHorario: id }))
    },
    estudiante: null
  }

  console.log("mandando a crear...")
  console.table(newUser);
  signProfessor(newUser);
  
};

const toggleTramo = (tramoId: number) => {
  if (selectedTramos.value.includes(tramoId)) {
    selectedTramos.value = selectedTramos.value.filter(t => t !== tramoId);
  } else {
    selectedTramos.value.push(tramoId);
  }
};
</script>

<style lang="scss" scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 100px;

  .login-form {
    width: 400px;
    padding: 20px;
    border-radius: 10px;

    .login {
      margin-bottom: 20px;

      p {
        font-size: 15px;
        line-height: 20px;
        margin-bottom: 30px;
      }
    }

    h2 {
      margin-bottom: 20px;
      font-size: 30px;
    }

    .form-group {
      margin-bottom: 15px;

      label {
        display: block;
        margin-bottom: 10px;
      }

      input,
      select,
      textarea {
        width: 100%;
        padding: 15px;
        margin-bottom: 5px;
        border: 2px solid #dcdce4;
        border-radius: 10px;
        box-sizing: border-box;
      }

      textarea {
        resize: none;
        height: 150px;
        font-family: 'Figtree', 'Figtree-fallback', 'Figtree-fallback-android', 'Noto Sans', 'NotoSans-fallback',
          'NotoSans-fallback-android', sans-serif;
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

    .tramos-horarios {
      display: flex;
      flex-wrap: wrap;
      gap: 10px;

      button {
        padding: 10px;
        border: 2px solid #dcdce4;
        border-radius: 5px;
        background-color: white;
        cursor: pointer;

        &.selected {
          background-color: #fbde68;
          border-color: #ffba24;
        }
      }
    }

    button[type='button'],
    button[type='submit'] {
      float: right;
      width: 48%;
      padding: 10px;
      border: 2px solid black;
      border-radius: 10px;
      background-color: #fbde68;
      color: black;
      cursor: pointer;
      font-size: 18px;
      font-weight: 600;
      margin-top: 15px;

      &:hover {
        background-color: #ffba24;
      }

      &:disabled {
        background-color: lightgray;
        cursor: not-allowed;
      }
    }
  }
}

.btn-pic {
  width: 100%;
  padding: 8px;
  border: 2px solid black;
  border-radius: 10px;
  background-color: #fbde68;
  color: black;
  cursor: pointer;
  font-size: 14px;
  font-weight: 500;
  margin: 15px 0px;

  &:hover {
    background-color: #ffba24;
  }
}

.upload-photo {
  hr {
    flex-grow: 1;
    border: none;
    border-top: 1px solid #dcdcdc;
  }

  .row {
    margin-top: 20px;
    display: flex;
    align-items: center;

    img {
      width: 100px;
      height: 100px;
      object-fit: cover;
    }

    .profile-data {
      margin-left: 20px;

      p {
        margin-top: 0;
        margin-top: 5px;
        font-size: 14px;
      }

      h3 {
        margin: 0;
      }
    }
  }
}

.column {
  .row {
    display: flex;
    gap: 10px;

    img {
      width: 95px;
      border: 1px solid #ececf0;
    }
  }

  .list {
    margin-top: 20px;
    margin-bottom: 20px;

    .row {
      display: flex;
      align-items: center;

      .check {
        margin-top: 5px;
        width: 16px;
        border: none;
      }

      p {
        font-size: 15px;
      }
    }
  }
}

.volver {
  margin-top: 30px;
  background-color: transparent;
  text-decoration: underline;
  font-size: 16px;
  border: none;
  cursor: pointer;

  &:hover {
    color: #ffba24;
  }
}
</style>
