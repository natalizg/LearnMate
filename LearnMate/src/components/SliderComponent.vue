<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue';

const currentIndex = ref(0);
const slideCount = 4;
let interval: number | undefined;

onMounted(() => {
  interval = window.setInterval(() => {
    currentIndex.value = (currentIndex.value + 1) % slideCount;
    document.querySelectorAll<HTMLInputElement>('.slider__nav')[currentIndex.value].checked = true;
  }, 4000);
});

onUnmounted(() => {
  if (interval) {
    clearInterval(interval);
  }
});
</script>

<template>
  <div class="slider-container">
    <div class="slider">
      <input type="radio" name="slider" title="slide1" class="slider__nav" />
      <input type="radio" name="slider" title="slide2" class="slider__nav" />
      <input type="radio" name="slider" title="slide3" class="slider__nav" />
      <input type="radio" name="slider" title="slide4" class="slider__nav" />
      <div class="slider-inner">
        <div class="slider-contents">
          <div class="section">
            <div class="column">
              <img src="../assets/profesora1.png" alt="" />
            </div>
            <div class="column">
              <div class="slider-testimony">
                <h2 class="slider-caption">
                  "La energía que aporta a cada clase es increíble"
                </h2>
                <p>Clara</p>
                <p class="slider-txt">
                  Lorem ipsum dolor sit amet, consectetur adipisicing elit. At
                  cupiditate omnis possimus illo quos, corporis minima!
                </p>
              </div>
            </div>
          </div>
        </div>
        <div class="slider-contents">
          <div class="section">
            <div class="column">
              <img src="../assets/profesora2.png" alt="" />
            </div>
            <div class="column">
              <div class="slider-testimony">
                <h2 class="slider-caption">
                  "Con sólo unas pocas clases, ya se nota la diferencia"
                </h2>
                <p>Filip</p>
                <p class="slider-txt">
                  Lorem ipsum dolor sit amet, consectetur adipisicing elit. At
                  cupiditate omnis possimus illo quos, corporis minima!
                </p>
              </div>
            </div>
          </div>
        </div>
        <div class="slider-contents">
          <div class="section">
            <div class="column">
              <img src="../assets/profesora3.png" alt="" />
            </div>
            <div class="column">
              <div class="slider-testimony">
                <h2 class="slider-caption">
                  "La mejor elección que he tomado para mi desarrollo en mucho
                  tiempo"
                </h2>
                <p>Natalia</p>
                <p class="slider-txt">
                  Lorem ipsum dolor sit amet, consectetur adipisicing elit. At
                  cupiditate omnis possimus illo quos, corporis minima!
                </p>
              </div>
            </div>
          </div>
        </div>
        <div class="slider-contents">
          <div class="section">
            <div class="column">
              <img src="../assets/last-slider.jpg" alt="" />
            </div>
            <div class="column">
              <div class="slider-testimony">
                <h2 class="slider-caption">
                  "La energía que aporta a cada clase es increíble"
                </h2>
                <p>Emma</p>
                <p class="slider-txt">
                  Lorem ipsum dolor sit amet, consectetur adipisicing elit. At
                  cupiditate omnis possimus illo quos, corporis minima!
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
*, *:before, *:after {
  box-sizing: border-box;
}

.slider-container {
  height: 800px;
  margin-bottom: 200px;
  .slider {
    height: 100%;
    position: relative;
    overflow: hidden;
    display: flex;
    flex-flow: row nowrap;
    align-items: flex-end;
    justify-content: center;

    .slider-inner {
      position: absolute;
      top: 0;
      left: 0;
      width: 400%;
      height: 100%;
      transition: left 0.4s;
      display: flex;
      .slider-contents {
        height: 100%;
        padding: 2rem;
        display: flex;
        flex: 1;
        align-items: center;
        justify-content: center;

        .section {
          display: flex;
          .column {
            width: 50%;
            display: flex;
            justify-content: flex-end;
            margin: 0 70px;
            img {
              width: 400px;
            }
            .slider-testimony {
              h2 {
                font-size: 50px;
                max-width: 500px;
              }
              p {
                font-weight: 700;
                max-width: 350px;
              }
              .slider-txt {
                color: #999;
                font-weight: normal;
              }
            }
          }
        }
      }
    }
  }
}

.slider__nav {
  width: 12px;
  height: 12px;
  margin: 2rem 12px;
  border-radius: 50%;
  z-index: 10;
  outline: 6px solid #ccc;
  outline-offset: -6px;
  cursor: pointer;
  appearance: none;
  backface-visibility: hidden;
}
.slider__nav:checked {
  animation: check 0.4s linear forwards;
}
.slider__nav:checked:nth-of-type(1) ~ .slider-inner {
  left: 0%;
}
.slider__nav:checked:nth-of-type(2) ~ .slider-inner {
  left: -100%;
}
.slider__nav:checked:nth-of-type(3) ~ .slider-inner {
  left: -200%;
}
.slider__nav:checked:nth-of-type(4) ~ .slider-inner {
  left: -300%;
}

@keyframes check {
  50% {
    outline-color: #ff7aac;
  }
  100% {
    outline-color: #ff7aac;
  }
}
</style>
