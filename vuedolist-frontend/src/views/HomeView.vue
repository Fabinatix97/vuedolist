<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { nextTick, onMounted, ref } from 'vue'
import { RouterLink } from 'vue-router'
import { createTodoList, deleteTodoList, fetchTodoLists } from '@/api/VueDoApi'
import type { TodoListDto } from '@/api/models/TodoListDto'

const hoveredList = ref<string | null>(null)
const newTodoListTitle = ref('')
const inputRef = ref<HTMLInputElement | null>(null)
const todoLists = ref<TodoListDto[]>([])
const show = ref(false)

function handleClick() {
  show.value = true
  nextTick(() => {
    inputRef.value?.focus()
  })
}

function handleCreateList() {
  createTodoList(newTodoListTitle.value)
    .then(() => {
      newTodoListTitle.value = ''
      show.value = false
      return fetchTodoLists()
    })
    .then((result) => {
      todoLists.value = result
    })
}

function handleDeleteList(id: string) {
  deleteTodoList(id)
    .then(() => {
      return fetchTodoLists()
    })
    .then((result) => {
      todoLists.value = result
    })
}

onMounted(() => {
  fetchTodoLists().then((result) => {
    todoLists.value = result
  })
})
</script>

<template>
  <main>
    <h1>Listen</h1>
    <div class="flex flex-col gap-4">
      <div
        v-for="list in todoLists"
        :key="list.id"
        class="flex rounded-xl bg-main p-4 duration-300 hover:bg-primaryhover"
        @mouseover="hoveredList = list.id"
        @mouseleave="hoveredList = null"
      >
        <RouterLink :to="{ name: 'list', params: { id: list.id } }" class="flex w-full grow gap-4">
          <Icon icon="material-symbols:checklist-rounded" style="font-size: 2em" />
          <p class="font-semibold">
            {{ list.title }}
          </p>
        </RouterLink>
        <Icon
          v-show="hoveredList === list.id"
          icon="material-symbols:delete-forever-rounded"
          style="font-size: 2em"
          class="flex-none cursor-pointer text-info hover:text-text"
          @click="handleDeleteList(list.id)"
        />
      </div>
      <div v-if="show" class="flex gap-4 rounded-xl bg-primaryhover p-4 text-primary duration-300">
        <Icon icon="material-symbols:checklist-rounded" style="font-size: 2em" />
        <input
          ref="inputRef"
          v-model="newTodoListTitle"
          placeholder="Liste benennen"
          class="cursor-pointer bg-transparent text-lg font-semibold outline-none placeholder:text-primary"
          @keyup.enter="handleCreateList"
        />
        <Icon
          icon="tdesign:enter"
          @click="handleCreateList"
          style="font-size: 2em"
          class="cursor-pointer"
        />
      </div>
      <div class="flex justify-end">
        <div
          class="flex gap-4 rounded-xl bg-primary p-4 text-white duration-300 hover:bg-primaryhover hover:text-text"
          @click="handleClick"
        >
          <Icon icon="material-symbols:add-2-rounded" style="font-size: 2em" />
        </div>
      </div>
    </div>
  </main>
</template>

<style scoped></style>
