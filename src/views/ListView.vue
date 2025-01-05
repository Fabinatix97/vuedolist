<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { ref } from 'vue'

const tasks = ref([
  { name: 'Aufgabe 1', done: true },
  { name: 'Aufgabe 2', done: false },
  { name: 'Aufgabe 3', done: false },
])

const shown = ref(false)
const newTask = ref('')
const hoveredTask = ref<string | null>(null)

function changeStatus(taskToChange: { name: string; done: boolean }) {
  taskToChange.done = !taskToChange.done
}

function addNewTask() {
  tasks.value.push({ name: newTask.value, done: false })
  newTask.value = ''
  shown.value = false
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
          />
          <Icon
            v-else
            icon="material-symbols:circle-outline"
            style="font-size: 2em"
            @click="changeStatus(task)"
          />
          <p :class="[task.done ? 'line-through' : '', 'font-semibold']">{{ task.name }}</p>
        </div>
        <Icon
          v-show="hoveredTask === task.name"
          icon="material-symbols:delete-forever-rounded"
          style="font-size: 2em"
        />
      </div>
      <div v-if="shown" class="flex gap-4 rounded-xl bg-primaryhover p-4 text-primary duration-300">
        <Icon icon="material-symbols:circle-outline" style="font-size: 2em" />
        <input
          v-model="newTask"
          placeholder="Aufgabe eingeben"
          class="bg-transparent font-semibold outline-none"
        />
        <Icon icon="tdesign:enter" @click="addNewTask()" style="font-size: 2em" />
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
