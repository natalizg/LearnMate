<script setup lang="ts">
</script>

<template>

<div class="slider-container">

    <div class="slider">
        <input type="radio" name="slider" title="slide1" class="slider__nav"/>
        <input type="radio" name="slider" title="slide2" class="slider__nav"/>
        <input type="radio" name="slider" title="slide3" class="slider__nav"/>
        <input type="radio" name="slider" title="slide4" class="slider__nav"/>
        <div class="slider-inner">
            <div class="slider-contents">
                <div class="section">
                    <div class="column">
                        <img src="../assets/profesora1.png" alt="">
                    </div>
                    <div class="column">
                        <div class="slider-testimony">
                            <h2 class="slider-caption">"La energía que aporta a cada clase es increíble"</h2>
                            <p>Ismael</p>
                            <p class="slider-txt">Lorem ipsum dolor sit amet, consectetur adipisicing elit. At cupiditate omnis possimus illo quos, corporis minima!</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="slider-contents">
                <div class="section">
                    <div class="column">
                        <img src="../assets/profesora2.png" alt="">
                    </div>
                    <div class="column">
                        <div class="slider-testimony">
                            <h2 class="slider-caption">"Con sólo unas pocas clases, ya se nota la diferencia"</h2>
                            <p>Filip</p>
                            <p class="slider-txt">Lorem ipsum dolor sit amet, consectetur adipisicing elit. At cupiditate omnis possimus illo quos, corporis minima!</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="slider-contents">
                <div class="section">
                    <div class="column">
                        <img src="../assets/profesora3.png" alt="">
                    </div>
                    <div class="column">
                        <div class="slider-testimony">
                            <h2 class="slider-caption">"La mejor elección que he tomado para mi desarrollo en mucho tiempo"</h2>
                            <p>Natalia</p>
                            <p class="slider-txt">Lorem ipsum dolor sit amet, consectetur adipisicing elit. At cupiditate omnis possimus illo quos, corporis minima!</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="slider-contents">
                <div class="section">
                    <div class="column">
                        <img src="../assets/profesora2.png" alt="">
                    </div>
                    <div class="column">
                        <div class="slider-testimony">
                            <h2 class="slider-caption">"La energía que aporta a cada clase es increíble"</h2>
                            <p>Ismael</p>
                            <p class="slider-txt">Lorem ipsum dolor sit amet, consectetur adipisicing elit. At cupiditate omnis possimus illo quos, corporis minima!</p>
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

.slider-container{
    height:800px;
    .slider {
        height: 100%;
        position: relative;
        overflow: hidden;
        display: -webkit-box;
        display: -webkit-flex;
        display: -ms-flexbox;
        -webkit-flex-flow: row nowrap;
        -ms-flex-flow: row nowrap;
        flex-flow: row nowrap;
        -webkit-box-align: end;
        -webkit-align-items: flex-end;
        -ms-flex-align: end;
        align-items: flex-end;
        -webkit-box-pack: center;
        -webkit-justify-content: center;
        -ms-flex-pack: center;
        justify-content: center;
        
        .slider-inner{
            position: absolute;
            top: 0;
            left: 0;
            width: 400%;
            height: 100%;
            -webkit-transition: left 0.4s;
            transition: left 0.4s;
            display: flex;
            .slider-contents{
                height: 100%;
                padding: 2rem;
                display: flex;
                flex: 1;
                align-items: center;
                justify-content: center;

                .section{
                    display:flex;
                    .column{
                        width:50%;
                        display: flex;
                        justify-content: flex-end;
                        margin: 0 70px;
                        .slider-testimony{
   
                            h2{
                                font-size:50px;
                                max-width: 500px;
                            }
                            p{
                                font-weight: 700;
                                max-width: 350px;
                            }
                            .slider-txt{
                                color: #999;
                                font-weight:normal;
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
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;
}
.slider__nav:checked {
    -webkit-animation: check 0.4s linear forwards;
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



@-webkit-keyframes check {
  50% {
    outline-color: #ff7aac;
  }
  100% {
    outline-color: #ff7aac;
  }
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
