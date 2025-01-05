import { ref } from 'vue'
import { defineStore } from 'pinia'
import { useLocalStorage } from '@vueuse/core'

export const listStore = defineStore('list', () => {
  const lists = ref(
    useLocalStorage('lists', [
      { id: 40006, name: 'ToDo Liste' },
      { id: 10909, name: 'Einkaufszettel' },
      { id: 61200, name: 'Packliste' },
    ]),
  )

  const shown = ref(false)
  const newList = ref('')

  function getRandomArbitrary(min: number, max: number) {
    return Math.floor(Math.random() * (max - min) + min)
  }

  function addNewList() {
    lists.value.push({ id: getRandomArbitrary(10000, 99999), name: newList.value })
    newList.value = ''
    shown.value = false
  }

  function deleteList(listId: number) {
    if (confirm('Wollen Sie die Liste wirklich löschen?')) {
      const index = lists.value.findIndex((list) => list.id === listId)
      if (index || index === 0) {
        lists.value.splice(index, 1)
      } else {
        console.log('Index not found')
      }
    }
  }

  return {
    lists,
    shown,
    newList,
    addNewList,
    deleteList,
  }
})
