<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { UserType } from '../types/UserType';
import axios from 'axios';
import router from '../router';
import useLogin from '../composables/useLogin';

const { user, routeSecurity } = useLogin(); // Importa el estado global del usuario

routeSecurity();
const idUser = ref<number>(-1);

const fetchData = () => {
  if (user.value) {
    idUser.value = user.value.idUsuario;
  }
};

onMounted(() => {
  fetchData();
});

const file = ref<File | null>(null);
const previewImageUrl = ref<string | null>(null);

const handleFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement;
  if (target.files && target.files[0]) {
    file.value = target.files[0];

    // Create a preview URL for the selected image
    const reader = new FileReader();
    reader.readAsDataURL(file.value);
    reader.onload = () => {
      previewImageUrl.value = reader.result as string;
    };
  }
};

const handleSubmit = async () => {
  if (file.value) {
    const formData = new FormData();
    formData.append('foto', file.value);
    formData.append('idUsuario', idUser.value.toString());

    try {
      const response = await axios.put(
        `https://localhost:8443/learnmateback/usuarios/setFotoUsuario`,
        formData,
        {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        }
      );
      // Aquí puedes actualizar la foto del usuario si la respuesta incluye la nueva imagen
      user.value = { ...user.value, foto: response.data.foto } as UserType;
      localStorage.setItem("user", JSON.stringify(user.value));
    } catch (error) {
      console.error('Error al subir la foto:', error);
    }
  } else {
    console.warn('No se ha seleccionado ningún archivo.');
  }
  router.push('/dashboard');
};

</script>

<template>
  <div class="container">
    <div class="colum">
      <div class="cambia-foto">
        <h2>Elige una foto para ayudar a conocerte.</h2>
      </div>
      <div class="upload-photo">
        <hr />
        <div class="row">
          <div class="profile-pic">
            <img v-if="!previewImageUrl && !user?.foto" src="../assets/user-standar.jpg" alt="User Standard" />
            <img v-else-if="previewImageUrl" :src="previewImageUrl" alt="Preview" />
            <img v-else :src="'data:image/jpeg;base64,' + user?.foto" alt="User Photo" />
          </div>
          <div class="profile-data">
            <h3>{{ user?.nombre }} {{ user?.apellidos }}</h3>
            <input type="file" id="file" @change="handleFileChange" />
          </div>
        </div>
        <hr />
        <button class="btn-pic" :class="{ 'disabled': !file }" @click="handleSubmit" :disabled="!file">Subir foto</button>
      </div>
      <div class="column">
        <h3>Lo que necesita tu foto</h3>
        <div class="row">
          <img src="../assets/pp1.jpg" alt="Example 1" />
          <img src="../assets/pp2.jpg" alt="Example 2" />
          <img src="../assets/pp3.jpg" alt="Example 3" />
          <img src="../assets/pp4.jpg" alt="Example 4" />
        </div>
        <div class="list">
          <div class="row">
            <div class="check-pic">
              <img class="check" src="../assets/check.png" alt="Check" />
            </div>
            <p>Debes mirar hacia delante</p>
          </div>
          <div class="row">
            <div class="check-pic">
              <img class="check" src="../assets/check.png" alt="Check" />
            </div>
            <p>Enmarca la cabeza y los hombros</p>
          </div>
          <div class="row">
            <div class="check-pic">
              <img class="check" src="../assets/check.png" alt="Check" />
            </div>
            <p>Asegúrate de que está centrada y en posición vertical</p>
          </div>
          <div class="row">
            <div class="check-pic">
              <img class="check" src="../assets/check.png" alt="Check" />
            </div>
            <p>Tu cara y ojos deben estar completamente visibles (a excepción de haber razones religiosas o culturales)</p>
          </div>
          <div class="row">
            <div class="check-pic">
              <img class="check" src="../assets/check.png" alt="Check" />
            </div>
            <p>Deberías ser la única persona en la foto</p>
          </div>
        </div>
      </div>
      <button @click="() => router.push('/dashboard')">Saltar paso</button>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container {
  max-width: 100%;
  margin-top:50px;
  display: flex;
  justify-content: center;
  margin-bottom: 100px;
  align-items: center;
}

.cambia-foto {
  text-align: center;
  margin-bottom: 20px;
}

.upload-photo {
  hr {
    border: none;
    border-top: 1px solid #dcdcdc;
  }

  .row {
    display: flex;
    align-items: center;
    margin-top: 20px;

    img {
      width: 100px;
      height: 100px;
      object-fit: cover;
    }

    .profile-data {
      margin-left: 20px;

      h3 {
        margin: 0;
        margin-bottom:20px;
      }

      p {
        margin: 5px 0 0;
        font-size: 16px;
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
  font-size: 16px;
  font-weight: 700;
  margin: 15px 0;

  &:hover {
    background-color: #ffba24;
  }
}

.column {
  max-width: 500px;
  h3 {
    margin-bottom: 10px;
  }

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

    .row {
      display: flex;
      align-items: center;
      margin-top: 10px;

      .check {
        width: 16px;
        margin-right: 10px;
        border: none;
      }

      p {
        font-size: 17px;
        margin: 0;
      }
    }
  }
}
input[type="file"]{
  font-family:'Figtree', 'Figtree-fallback', 'Figtree-fallback-android', 'Noto Sans', 'NotoSans-fallback', 'NotoSans-fallback-android', sans-serif;
  font-size: 14px;
  &::file-selector-button {
    padding: 6px;
    background-color:#fbde68;
    border: none;
    border-radius: 15px;;
    font-weight: 600;
    margin-right:15px;
    cursor: pointer;
    font-size: 16px;
    font-family: 'Figtree', 'Figtree-fallback', 'Figtree-fallback-android', 'Noto Sans', 'NotoSans-fallback', 'NotoSans-fallback-android', sans-serif;
  }
}

.btn-pic {
  background-color: #fbde68;
  color: black;

  &:hover {
    background-color: #ffba24;
  }

  &.disabled {
    background-color: #cccccc;
    cursor: not-allowed;
  }
}
button {
  width: 100%;
  padding: 8px;
  border: 2px solid black;
  border-radius: 10px;
  background-color: #fbde68;
  color: black;
  cursor: pointer;
  font-size: 16px;
  font-weight: 700;
  margin: 15px 0;

  &:hover {
    background-color: #ffba24;
  }
}
</style>
