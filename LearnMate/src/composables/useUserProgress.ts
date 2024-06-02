import { ref } from "vue";

export default function useUserProgress() {
    const isOpenClassReserv = ref(false);
    function openClassReserv () {isOpenClassReserv.value = true;}
    function closeClassReserv () { isOpenClassReserv.value = false}
    
    return {
        isOpenClassReserv,
        openClassReserv,
        closeClassReserv
    }
}