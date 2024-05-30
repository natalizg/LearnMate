import { ref } from "vue";

export default function useUserProgress() {
    const isOpenClassReserv = ref(false);
    function openClassReserv () { console.log(isOpenClassReserv.value);isOpenClassReserv.value = true;console.log(isOpenClassReserv.value);}
    function closeClassReserv () { isOpenClassReserv.value = false}
    
    return {
        isOpenClassReserv,
        openClassReserv,
        closeClassReserv
    }
}