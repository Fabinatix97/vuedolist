import { ref } from 'vue'
import { defineStore } from 'pinia'
import { useLocalStorage } from '@vueuse/core'

export const taskStore = defineStore('task', () => {
  const tasks = ref(
    useLocalStorage('tasks', [
      { id: 40006, name: 'Aufgabe 1', done: true },
      { id: 10909, name: 'Aufgabe 2', done: false },
      { id: 61200, name: 'Aufgabe 3', done: false },
    ]),
  )

  const shown = ref(false)
  const newTask = ref('')

  function changeStatus(taskToChange: { name: string; done: boolean }) {
    taskToChange.done = !taskToChange.done
  }

  function getRandomArbitrary(min: number, max: number) {
    return Math.floor(Math.random() * (max - min) + min)
  }

  function addNewTask() {
    tasks.value.push({ id: getRandomArbitrary(10000, 99999), name: newTask.value, done: false })
    newTask.value = ''
    shown.value = false
  }

  function deleteTask(taskId: number) {
    const index = tasks.value.findIndex((task) => task.id === taskId)
    if (index || index === 0) {
      tasks.value.splice(index, 1)
    } else {
      console.log('Index not found')
    }
  }

  return {
    tasks,
    shown,
    newTask,
    changeStatus,
    addNewTask,
    deleteTask,
  }
})
