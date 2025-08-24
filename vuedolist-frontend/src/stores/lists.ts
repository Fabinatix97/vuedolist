import { ref } from 'vue'
import { defineStore } from 'pinia'
import { useLocalStorage } from '@vueuse/core'

export const listStore = defineStore('list', () => {
  const lists = ref(
    useLocalStorage('lists', [
      {
        id: 40006,
        name: 'ToDo Liste',
        tasks: [
          { id: 1, name: 'Aufgabe 1', done: true },
          { id: 2, name: 'Aufgabe 2', done: false },
        ],
      },
      {
        id: 10909,
        name: 'Einkaufszettel',
        tasks: [
          { id: 1, name: 'Milch kaufen', done: false },
          { id: 2, name: 'Brot kaufen', done: false },
        ],
      },
      {
        id: 61200,
        name: 'Packliste',
        tasks: [{ id: 1, name: 'Zahnbürste einpacken', done: true }],
      },
    ]),
  )

  const shown = ref(false)
  const newList = ref('')

  function getRandomArbitrary(min: number, max: number) {
    return Math.floor(Math.random() * (max - min) + min)
  }

  function addNewList() {
    lists.value.push({ id: getRandomArbitrary(10000, 99999), name: newList.value, tasks: [] })
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

  function addNewTask(listId: number, taskName: string) {
    console.log('Adding task:', taskName)
    const tasks = lists.value.find((list) => list.id === listId)?.tasks
    if (tasks) {
      tasks.push({ id: getRandomArbitrary(10000, 99999), name: taskName, done: false })
    } else {
      console.log('Tasks not found for listId:', listId)
    }
    shown.value = false
  }

  function deleteTask(listId: number, taskId: number) {
    const tasks = lists.value.find((list) => list.id === listId)?.tasks
    if (tasks) {
      const index = tasks.findIndex((task) => task.id === taskId)
      if (index !== -1) {
        tasks.splice(index, 1)
      } else {
        console.log('Index not found')
      }
    } else {
      console.log('Tasks not found for listId:', listId)
    }
  }

  function changeStatus(taskToChange: { name: string; done: boolean }) {
    //hier lieber eine ID statt name verwenden
    taskToChange.done = !taskToChange.done
  }

  return {
    lists,
    shown,
    newList,
    addNewList,
    deleteList,
    addNewTask,
    deleteTask,
    changeStatus,
  }
})
