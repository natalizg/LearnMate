<script setup lang="ts">
import { ref } from 'vue';
import useProfClassInfo  from '../composables/useProfClassInfo';
import FilterComponent from '../components/FilterComponent.vue';
import ClassReservation from '../components/ClassReservation.vue';
import TeacherCardComponent from '../components/TeacherCardComponent.vue';

const { filteredProfs, fetchProfs } = useProfClassInfo()
const pDesplegado = ref(false);

const showHideP = () => {
    pDesplegado.value = !pDesplegado.value;
};

// Iniciamos los primeros profs:
fetchProfs(1, 35, 2,'1,2,3,4,5');


//FILTER THINGS: 

type LearnOption = { id: number, name: string };
type TimeSlot = { id: number, name: string };

const learnOptions: LearnOption[] = [
  { id: 1, name: 'Español' },
  { id: 2, name: 'Inglés' },
  { id: 3, name: 'Francés' },
  { id: 4, name: 'Chino' },
  { id: 5, name: 'Alemán' },
  { id: 6, name: 'Coreano' },
  { id: 7, name: 'Japonés' },
  { id: 8, name: 'Portugués' },
  { id: 9, name: 'Italiano' },
  { id: 10, name: 'Árabe' },
  { id: 11, name: 'Ruso' }
];
const selectedLearnOption = ref<number>(2);

const minPrice = ref(1);
const maxPrice = ref(35);
const selectedMinPrice = ref(minPrice.value);
const selectedMaxPrice = ref(maxPrice.value);

const timeSlots: TimeSlot[] = [
  { id: 1, name: '9-12' },
  { id: 2, name: '12-15' },
  { id: 3, name: '15-18' },
  { id: 4, name: '18-21' },
  { id: 5, name: '21-24' }
];
const selectedTimeSlots = ref<string>('1,2,3,4,5');

const toggleTimeSlot = (slotId: number) => {
  const selectedIds = selectedTimeSlots.value.split(',').map(Number);
  const index = selectedIds.indexOf(slotId);

  if (index === -1) {
    selectedIds.push(slotId);
  } else {
    selectedIds.splice(index, 1);
  }

  selectedTimeSlots.value = selectedIds.join(',');
};

const getTimeSlotsLabel = () => {
  if (selectedTimeSlots.value.split(',').length === timeSlots.length) {
    return 'Cualquier Hora';
  }
  return selectedTimeSlots.value.split(',').map(id => timeSlots.find(slot => slot.id === parseInt(id))?.name).join(', ');
};

const isTimeSlotSelected = (slotId: number) => {
  return selectedTimeSlots.value.split(',').includes(slotId.toString());
};

const getLearnOptionLabel = (id: number) => {
  return learnOptions.find(option => option.id === id)?.name || '';
};

const learnMenuOpen = ref(false);
const priceMenuOpen = ref(false);
const timeMenuOpen = ref(false);

const toggleLearnMenu = () => {
  learnMenuOpen.value = !learnMenuOpen.value;
  closeOtherMenus('learn');
};

const togglePriceMenu = () => {
  priceMenuOpen.value = !priceMenuOpen.value;
  closeOtherMenus('price');
};

const toggleTimeMenu = () => {
  timeMenuOpen.value = !timeMenuOpen.value;
  closeOtherMenus('time');
};

const closeOtherMenus = (current: string) => {
  if (current !== 'learn') learnMenuOpen.value = false;
  if (current !== 'price') priceMenuOpen.value = false;
  if (current !== 'time') timeMenuOpen.value = false;
};

const selectLearnOption = (optionId: number) => {
  selectedLearnOption.value = optionId;
  learnMenuOpen.value = false;
};

const handleSubmit = () => {

  fetchProfs(
    selectedMinPrice.value,
    selectedMaxPrice.value,
    selectedLearnOption.value,
    selectedTimeSlots.value
  );
};
</script>

<template>
<div class="ad">
    <div class="ad-interior">
        <div class="left">
            <h1>Clases particulares de inglés online: encuentra tu profesor ahora</h1>
            <div class="p-desplegable">
                <p>LearMate es la plataforma online líder de aprendizaje de idiomas donde podrás  
                    <a href="#" id="leerMas" v-show="!pDesplegado" @click="showHideP">... Leer Más</a>
                </p>
            </div>
            <p id="desplegar" v-show="pDesplegado"> 
                encontrar un profesor que hable tu idioma y que pueda enseñarte chino en clases
                particulares. En LearnMate hay una gran variedad profesores disponibles especializados
                en diferentes niveles y con diferentes precios y horarios, para que puedas elegir en función de tu nivel
                y de tu presupuesto. Programa una clase online a cualquier hora del día con profesores de todo el mundo. 
                ¿A qué estás esperando?
            </p>
            <a href="#" id="ocultar" v-show="pDesplegado" @click="showHideP">Ocultar</a>
        </div>
        <div class="right">
            <img class="first" src="../assets/videocall2.png" height="450px" alt="VideoLlamada">
        </div>
    </div>
</div>
<div class="filter-container">
    <div class="filter-container-component">
        <div class="select-container">
            <!-- Overlay -->
            <div v-if="learnMenuOpen || priceMenuOpen || timeMenuOpen" class="overlay" @click="closeOtherMenus('')"></div>

            <!-- Learn Selector -->
            <div class="select-box" @click="toggleLearnMenu" :class="{ open: learnMenuOpen }">
                <div class="select-box__current">
                <span>Quiero aprender: {{ getLearnOptionLabel(selectedLearnOption) }}</span>
                <img class="select-box__icon" src="http://cdn.onlinewebfonts.com/svg/img_295694.svg" alt="Arrow Icon">
                </div>
                <ul class="select-box__list" v-if="learnMenuOpen">
                <li v-for="(option, index) in learnOptions" :key="index" @click.stop="selectLearnOption(option.id)">
                    {{ option.name }}
                </li>
                </ul>
            </div>

            <!-- Price Range Selector -->
            <div class="select-box" :class="{ open: priceMenuOpen }">
                <div class="select-box__current" @click="togglePriceMenu">
                <span>Rango de precio: {{ selectedMinPrice }} - {{ selectedMaxPrice }} € </span>
                <img class="select-box__icon" src="http://cdn.onlinewebfonts.com/svg/img_295694.svg" alt="Arrow Icon">
                </div>
                <div class="price-slider" v-if="priceMenuOpen" @click.stop>
                <span>Mín.</span>
                <input type="range" :min="minPrice" :max="maxPrice" v-model="selectedMinPrice">
                <span>Máx.</span>
                <input type="range" :min="minPrice" :max="maxPrice" v-model="selectedMaxPrice">
                </div>
            </div>

            <!-- Time Slots Selector -->
            <div class="select-box" @click="toggleTimeMenu" :class="{ open: timeMenuOpen }">
                <div class="select-box__current">
                <span>Disponibilidad: {{ getTimeSlotsLabel() }}</span>
                <img class="select-box__icon" src="http://cdn.onlinewebfonts.com/svg/img_295694.svg" alt="Arrow Icon">
                </div>
                <ul class="select-box__list" v-if="timeMenuOpen">
                <li v-for="(slot, index) in timeSlots" :key="index" @click.stop="toggleTimeSlot(slot.id)">
                    <input type="checkbox" :checked="isTimeSlotSelected(slot.id)">
                    {{ slot.name }}
                </li>
                </ul>
            </div>
            <button type="submit" @click="handleSubmit">Buscar</button>
        </div>
    </div>
</div>
<div class="teachers">
    <TeacherCardComponent 
        v-for="(prof, index) in filteredProfs" 
        :key="index"
        :id = "prof.id"
        :nombre="prof.nombre"
        :apellidos="prof.apellidos"
        :foto="prof.foto"
        :materia="prof.materia"
        :idMateria="prof.idMateria"
        :descripcion="prof.descripcion"
        :precio="prof.precio"
        :idTramos="prof.idTramos"
    />
</div>
</template>


<style lang="scss" scoped>
.ad {
    display: flex;
    margin: auto;
    justify-content: center;
    align-items: center;

    .ad-interior {
        display: flex;
        gap: 200px;
    }

    .left {
        margin-top: 150px;
        width: 600px;

        h1 {
            font-size: 64px;
            font-weight: 650;
            letter-spacing: -0.32px;
            line-height: 68px;
            font-family: 'Platform', 'Platform-fallback', 'Platform-fallback-android', 'Noto Sans',
            'NotoSans-fallback', 'NotoSans-fallback-android', sans-serif;
            margin: 0;
            padding: 0;
            letter-spacing: -4px;
        }

        h3 {
            margin-right: 80px;
        }

        .p-desplegable {
            display: flex;
            p {
                margin-bottom: 0px;
            }
        }
        #desplegar{
            margin-top:0px;
        }


        a {
            color: black;

            &:hover {
                color: #ff7aac;
            }
        }
    }

    .right {
        margin-top: 100px;
        width: 400px;
    }
}

.filter-container {
    margin-top: 100px;
    margin-bottom: 100px;
}

.teachers {
    margin-top: 100px;
    margin-bottom:150px;
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    
}


//FILTER STYLES: 

.filter-container-component {
  margin-left: 11%;
  margin-right: 11%;
}

.select-container {
  display: flex;
  gap: 20px;
}

.select-box {
  position: relative;
  display: block;
  width: 100%;
  max-height: 52px;
  margin: 0 auto;
  cursor: pointer;
  border: 2px solid #dcdce4;
  border-radius: 5px;
  font-size: 18px;
  color: #60666d;
  background-color: #fff;
  transition: border-color 0.2s;
  &:hover {
    border: 2px solid black;
  }
  &__current {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px;
  }

  &__icon {
    width: 20px;
    transition: transform 0.2s;
  }

  &__list {
    position: absolute;
    top: 100%;
    left: 0;
    width: 100%;
    border: 1px solid #dcdce4;
    border-top: none;
    border-radius: 0 0 5px 5px;
    background-color: #fff;
    box-shadow: 0 15px 30px -10px rgba(0, 0, 0, 0.1);
    list-style: none;
    padding: 0;
    margin: 0;
    z-index: 10;

    li {
      padding: 15px;
      cursor: pointer;
      &:hover {
        background-color: #f0f0f0;
      }
    }
  }

  &.open &__icon {
    transform: rotate(180deg);
  }
}

.price-slider {
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 15px;
  border: 1px solid #dcdce4;
  border-top: none;
  border-radius: 0 0 5px 5px;
  background-color: #fff;
  box-shadow: 0 15px 30px -10px rgba(0, 0, 0, 0.1);
}

.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: none;
  z-index: 5;
}

.open .overlay {
  display: block;
}

button[type="submit"] {
  width: 300px;
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

input[type='range'] {
  accent-color: #fbde68;
}

input[type='checkbox'] {
  accent-color: #fbde68;
}
</style>
