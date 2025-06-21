<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { nextTick, onMounted, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { createTodo, deleteTodo, fetchTodoList, updateTodo } from '@/api/VueDoApi'
import type { TodoListDto } from '@/api/models/TodoListDto'
import { TodoStatus } from '@/api/models/TodoStatus'
import type { TodoPriority } from '@/api/models/TodoPriority'

const hoveredTask = ref<string | null>(null)
const router = useRouter()
const route = useRoute()
const todoListId = ref<string>(route.params.id as string);

const todoList = ref<TodoListDto>();

const newTodo = ref<HTMLInputElement | null>(null)
const newTodoTitle = ref('')

const show = ref(false)

function handleClick() {
  show.value = true
  nextTick(() => {
    newTodo.value?.focus()
  })
}

function handleCreateTodo() {
  createTodo(todoListId.value, newTodoTitle.value)
    .then(() => {
      newTodoTitle.value = '';
      show.value = false;
      return fetchTodoList(todoListId.value)
    })
    .then(result => {
      todoList.value = result;
    })
}

function handleUpdateTodo(
  todoId: string,
  updates: {
    title?: string,
    description?: string,
    status?: TodoStatus,
    priority?: TodoPriority
  }
) {
  updateTodo(todoListId.value, todoId, updates.title, updates.description, updates.status, updates.priority)
    .then(() => {
      return fetchTodoList(todoListId.value)
    })
    .then(result => {
      todoList.value = result;
    })
}

function handleDeleteTodo(
  todoId: string
) {
  deleteTodo(todoListId.value, todoId)
    .then(() => {
      return fetchTodoList(todoListId.value)
    })
    .then(result => {
      todoList.value = result;
    })
}

onMounted(() => {
  fetchTodoList(todoListId.value)
    .then(result => {
      todoList.value = result;
    })
})

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
        <h1 class="mt-0">{{ todoList?.title }}</h1>
      </div>
      <Icon
        icon="charm:menu-kebab"
        style="font-size: 2em"
        class="cursor-pointer text-text hover:text-primary"
      />
    </div>
    <div class="flex flex-col gap-4">
      <div
        v-for="todo in todoList?.todos"
        :key="todo.id"
        class="flex w-full rounded-xl bg-main p-4 duration-300 hover:bg-primaryhover"
        @mouseover="hoveredTask = todo.id"
        @mouseleave="hoveredTask = null"
      >
        <div class="flex grow gap-4">
          <Icon
            v-if="todo.status === TodoStatus.DONE"
            icon="material-symbols:check-circle-rounded"
            style="font-size: 2em"
            @click="handleUpdateTodo(todo.id, { status: TodoStatus.OPEN })"
            class="cursor-pointer text-info"
          />
          <Icon
            v-else
            icon="material-symbols:circle-outline"
            style="font-size: 2em"
            @click="handleUpdateTodo(todo.id, { status: TodoStatus.DONE })"
            class="cursor-pointer"
          />
          <p :class="[todo.status === TodoStatus.DONE ? 'text-info line-through' : '', 'font-semibold']">
            {{ todo.title }}
          </p>
        </div>
        <Icon
          v-show="hoveredTask === todo.id"
          icon="material-symbols:delete-forever-rounded"
          style="font-size: 2em"
          class="cursor-pointer text-info hover:text-text"
          @click="handleDeleteTodo(todo.id)"
        />
      </div>
      <div
        v-if="show"
        class="flex gap-4 rounded-xl bg-primaryhover p-4 text-primary duration-300"
      >
        <Icon icon="material-symbols:circle-outline" style="font-size: 2em" />
        <input
          ref="newTodo"
          v-model="newTodoTitle"
          placeholder="Aufgabe eingeben"
          class="cursor-pointer bg-transparent text-lg font-semibold outline-none placeholder:text-primary"
          @keyup.enter="handleCreateTodo"
        />
        <Icon
          icon="material-symbols:subdirectory-arrow-left-rounded"
          @click="handleCreateTodo"
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
  </div>
</template>
