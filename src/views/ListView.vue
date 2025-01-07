<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { computed, ref, nextTick } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { listStore } from '../stores/lists'

const store = listStore()
const router = useRouter()
const route = useRoute()
const listId = parseInt(route.params.id as string)
const list = computed(() => store.lists.find((l) => l.id === listId))
const listName = list.value?.name
const tasks = computed(() => list.value?.tasks || [])
const hoveredTask = ref<string | null>(null)

const newTask = ref<HTMLInputElement | null>(null)
const newTaskInput = ref('') // Lokale Eingabe

const handleAdd = () => {
  store.shown = !store.shown
  if (store.shown) {
    nextTick(() => {
      if (newTask.value) {
        newTask.value.focus()
      }
    })
  }
}

const addTask = () => {
  store.addNewTask(listId, newTaskInput.value)
  newTaskInput.value = ''
}
</script>

<template>
  <div class="about">
    <div class="mt-10 flex items-center justify-between">
      <div class="flex items-center gap-4">
        <Icon
          icon="material-symbols:arrow-back-rounded"
          style="font-size: 2em"
          class="cursor-pointer text-text hover:text-primary"
          @click="router.push({ name: 'home' })"
        />
        <h1 class="mt-0">{{ listName }}</h1>
      </div>
      <Icon
        icon="charm:menu-kebab"
        style="font-size: 2em"
        class="cursor-pointer text-text hover:text-primary"
      />
    </div>
    <div class="flex flex-col gap-4">
      <div
        v-for="task in tasks"
        :key="task.id"
        class="flex w-full rounded-xl bg-main p-4 duration-300 hover:bg-primaryhover"
        @mouseover="hoveredTask = task.id"
        @mouseleave="hoveredTask = null"
      >
        <div class="flex grow gap-4">
          <Icon
            v-if="task.done"
            icon="material-symbols:check-circle-rounded"
            style="font-size: 2em"
            @click="store.changeStatus(task)"
            class="cursor-pointer text-info"
          />
          <Icon
            v-else
            icon="material-symbols:circle-outline"
            style="font-size: 2em"
            @click="store.changeStatus(task)"
            class="cursor-pointer"
          />
          <p :class="[task.done ? 'text-info line-through' : '', 'font-semibold']">
            {{ task.name }}
          </p>
        </div>
        <Icon
          v-show="hoveredTask === task.id"
          icon="material-symbols:delete-forever-rounded"
          style="font-size: 2em"
          class="cursor-pointer text-info hover:text-text"
          @click="store.deleteTask(listId, task.id)"
        />
      </div>
      <div
        v-if="store.shown"
        class="flex gap-4 rounded-xl bg-primaryhover p-4 text-primary duration-300"
      >
        <Icon icon="material-symbols:circle-outline" style="font-size: 2em" />
        <input
          v-model="newTaskInput"
          placeholder="Aufgabe eingeben"
          class="cursor-pointer bg-transparent text-lg font-semibold outline-none placeholder:text-primary"
          @keyup.enter="addTask"
          ref="newTask"
        />
        <Icon
          icon="material-symbols:subdirectory-arrow-left-rounded"
          @click="addTask"
          style="font-size: 2em"
          class="cursor-pointer"
        />
      </div>
      <div class="flex justify-end">
        <div
          class="flex gap-4 rounded-xl bg-primary p-4 text-white duration-300 hover:bg-primaryhover hover:text-text"
          @click="handleAdd()"
        >
          <Icon icon="material-symbols:add-2-rounded" style="font-size: 2em" />
        </div>
      </div>
    </div>
  </div>
</template>
