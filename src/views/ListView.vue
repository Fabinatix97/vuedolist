<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { ref } from 'vue'

const tasks = ref([
  { id: 40006, name: 'Aufgabe 1', done: true },
  { id: 10909, name: 'Aufgabe 2', done: false },
  { id: 61200, name: 'Aufgabe 3', done: false },
])

const shown = ref(false)
const newTask = ref('')
const hoveredTask = ref<string | null>(null)

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
</script>

<template>
  <div class="about">
    <h1>ToDo Liste 1</h1>
    <div class="flex flex-col gap-4">
      <div
        v-for="task in tasks"
        :key="task.name"
        class="flex rounded-xl bg-main p-4 duration-300 hover:bg-primaryhover"
        @mouseover="hoveredTask = task.name"
        @mouseleave="hoveredTask = null"
      >
        <div class="flex grow gap-4">
          <Icon
            v-if="task.done"
            icon="material-symbols:check-circle-rounded"
            style="font-size: 2em"
            @click="changeStatus(task)"
            class="cursor-pointer text-info"
          />
          <Icon
            v-else
            icon="material-symbols:circle-outline"
            style="font-size: 2em"
            @click="changeStatus(task)"
            class="cursor-pointer"
          />
          <p :class="[task.done ? 'text-info line-through' : '', 'font-semibold']">
            {{ task.name }}
          </p>
        </div>
        <Icon
          v-show="hoveredTask === task.name"
          icon="material-symbols:delete-forever-rounded"
          style="font-size: 2em"
          class="cursor-pointer text-info hover:text-text"
          @click="deleteTask(task.id)"
        />
      </div>
      <div v-if="shown" class="flex gap-4 rounded-xl bg-primaryhover p-4 text-primary duration-300">
        <Icon icon="material-symbols:circle-outline" style="font-size: 2em" />
        <input
          v-model="newTask"
          placeholder="Aufgabe eingeben"
          class="cursor-pointer bg-transparent font-semibold outline-none"
        />
        <Icon
          icon="tdesign:enter"
          @click="addNewTask()"
          style="font-size: 2em"
          class="cursor-pointer"
        />
      </div>
      <div class="flex justify-end">
        <div
          class="flex gap-4 rounded-xl bg-primary p-4 text-white duration-300 hover:bg-primaryhover hover:text-text"
          @click="shown = !shown"
        >
          <Icon icon="material-symbols:add-2-rounded" style="font-size: 2em" />
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
