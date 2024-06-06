import { ref } from "vue";

export default function useUserProgress() {
    const isOpenClassReserv = ref(false);
    function openClassReserv () {isOpenClassReserv.value = true;}
    function closeClassReserv () { isOpenClassReserv.value = false}
    const isOpenClassEdit = ref(false);
    function openClassEdit () {isOpenClassEdit.value = true;}
    function closeClassEdit () {isOpenClassEdit.value = false;}
    return {
        isOpenClassReserv,
        isOpenClassEdit,
        openClassReserv,
        closeClassReserv,
        openClassEdit,
        closeClassEdit
    }
}