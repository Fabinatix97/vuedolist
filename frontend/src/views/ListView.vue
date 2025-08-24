<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { nextTick, onMounted, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { createTodo, deleteTodo, fetchTodoList, updateTodo, updateTodoList } from '@/api/VueDoApi'
import type { TodoListDto } from '@/api/models/TodoListDto'
import { TodoStatus } from '@/api/models/TodoStatus'
import type { TodoPriority } from '@/api/models/TodoPriority'
import DropdownMenu from '@/components/DropdownMenu.vue'

const hoveredTask = ref<string | null>(null)
const router = useRouter()
const route = useRoute()
const todoListId = ref<string>(route.params.id as string)

const todoList = ref<TodoListDto>()
const todoListDescriptionInput = ref<HTMLInputElement | null>(null)
const newTodoListDescription = ref<string>('')
const todoListDescription = ref<string>('')

const newTodoTitleInput = ref<HTMLInputElement | null>(null)
const newTodoTitle = ref<string>('')

const showInputDescription = ref<boolean>(false)
const showInputTodo = ref<boolean>(false)
const editingTodoId = ref<string | null>(null)
const editInputRefs: Record<string, HTMLInputElement | null> = {}

const menuItems = [
  { icon: 'material-symbols:edit', label: 'Update description', action: handleAddDescription },
  { icon: 'material-symbols:delete-forever-rounded', label: 'Delete list', action: handleDeleteList },
]

function handleAddDescription() {
  showInputDescription.value = true
  nextTick(() => {
    todoListDescriptionInput.value?.focus()
  })
}

function handleDeleteList() {}

function handleClick() {
  showInputTodo.value = true
  nextTick(() => {
    newTodoTitleInput.value?.focus()
  })
}

function handleUpdateTodoList(updates: { title?: string; description?: string }) {
  updateTodoList(todoListId.value, updates.title, updates.description)
    .then(() => {
      return fetchTodoList(todoListId.value)
    })
    .then((result) => {
      todoListDescription.value = result.description
    })
  showInputDescription.value = false
}

function handleCreateTodo() {
  createTodo(todoListId.value, newTodoTitle.value)
    .then(() => {
      newTodoTitle.value = ''
      showInputTodo.value = false
      return fetchTodoList(todoListId.value)
    })
    .then((result) => {
      todoList.value = result
    })
}

function handleEditTodo(todoId: string) {
  editingTodoId.value = todoId
  nextTick(() => {
    const el = editInputRefs[todoId]
    el?.focus()
  })
}

function handleUpdateTodo(
  todoId: string,
  updates: {
    title?: string
    description?: string
    status?: TodoStatus
    priority?: TodoPriority
  },
) {
  updateTodo(
    todoListId.value,
    todoId,
    updates.title,
    updates.description,
    updates.status,
    updates.priority,
  )
    .then(() => {
      return fetchTodoList(todoListId.value)
    })
    .then((result) => {
      todoList.value = result
    })

  if (editingTodoId.value !== null) {
    editingTodoId.value = null
  }
}

function handleDeleteTodo(todoId: string) {
  deleteTodo(todoListId.value, todoId)
    .then(() => {
      return fetchTodoList(todoListId.value)
    })
    .then((result) => {
      todoList.value = result
    })
}

onMounted(() => {
  fetchTodoList(todoListId.value).then((result) => {
    todoList.value = result
    todoListDescription.value = result.description
  })
})
</script>

<template>
  <div class="about">
    <div class="mt-10 flex items-center justify-between">
      <div class="flex items-center gap-4">
        <Icon
          icon="material-symbols:arrow-back-rounded"
          class="cursor-pointer text-3xl hover:text-primary"
          @click="router.push({ name: 'home' })"
        />
        <h1 class="mt-0">{{ todoList?.title }}</h1>
      </div>
      <DropdownMenu :items="menuItems">
        <template #trigger>
          <!-- Your custom trigger -->
          <Icon icon="material-symbols:more-vert" class="text-3xl" />
        </template>
      </DropdownMenu>
    </div>
    <div class="mb-4 ml-4 text-lg">
      <div v-if="todoListDescription && !showInputDescription">
        {{ todoListDescription }}
      </div>
      <div v-if="showInputDescription" class="flex rounded-xl bg-main p-2 duration-300">
        <!-- TODO: make the placeholder dynamic depending on whether a description already exists or not -->
        <input
          ref="todoListDescriptionInput"
          v-model="newTodoListDescription"
          placeholder="Update description..."
          class="cursor-pointer bg-transparent text-lg font-semibold outline-none placeholder:text-info"
          @keyup.enter="handleUpdateTodoList({ description: newTodoListDescription })"
        />
      </div>
    </div>
    <div class="flex flex-col gap-4">
      <div
        v-for="todo in todoList?.todos"
        :key="todo.id"
        class="group flex w-full rounded-xl bg-main p-4 duration-300 hover:bg-primaryhover"
        @mouseover="hoveredTask = todo.id"
        @mouseleave="hoveredTask = null"
      >
        <div class="flex grow gap-4">
          <Icon
            v-if="todo.status === TodoStatus.DONE"
            icon="material-symbols:check-circle-rounded"
            @click="handleUpdateTodo(todo.id, { status: TodoStatus.OPEN })"
            class="cursor-pointer text-3xl text-info group-hover:text-primary"
          />
          <Icon
            v-else
            icon="material-symbols:circle-outline"
            @click="handleUpdateTodo(todo.id, { status: TodoStatus.DONE })"
            class="cursor-pointer text-3xl group-hover:text-primary"
          />
          <input
            :ref="
              (el) => {
                if (editingTodoId === todo.id) {
                  editInputRefs[todo.id] = el as HTMLInputElement
                }
              }
            "
            v-if="editingTodoId === todo.id"
            v-model="newTodoTitle"
            placeholder="Edit title..."
            class="cursor-pointer bg-transparent text-lg font-semibold outline-none placeholder:text-info"
            @keyup.enter="handleUpdateTodo(todo.id, { title: newTodoTitle })"
          />
          <p
            v-if="editingTodoId !== todo.id"
            :class="[
              todo.status === TodoStatus.DONE
                ? 'text-info line-through group-hover:text-primary'
                : 'group-hover:text-primary',
              'font-semibold',
            ]"
          >
            {{ todo.title }}
          </p>
        </div>
        <Icon
          v-show="hoveredTask === todo.id"
          icon="material-symbols:edit"
          class="cursor-pointer text-3xl text-primary hover:text-text"
          @click="handleEditTodo(todo.id)"
        />
        <Icon
          v-show="hoveredTask === todo.id"
          icon="material-symbols:delete-forever-rounded"
          class="cursor-pointer text-3xl text-primary hover:text-text"
          @click="handleDeleteTodo(todo.id)"
        />
      </div>
      <div
        v-if="showInputTodo"
        class="flex gap-4 rounded-xl bg-primaryhover p-4 text-primary duration-300"
      >
        <Icon icon="material-symbols:circle-outline" class="text-3xl" />
        <input
          ref="newTodoTitleInput"
          v-model="newTodoTitle"
          placeholder="Add todo..."
          class="cursor-pointer bg-transparent text-lg font-semibold outline-none placeholder:text-primary"
          @keyup.enter="handleCreateTodo"
        />
        <Icon
          icon="material-symbols:subdirectory-arrow-left-rounded"
          @click="handleCreateTodo"
          class="cursor-pointer text-3xl"
        />
      </div>
      <div class="flex justify-end">
        <div
          class="flex gap-4 rounded-xl bg-primary p-4 text-white duration-300 hover:bg-primaryhover hover:text-text"
          @click="handleClick"
        >
          <Icon icon="material-symbols:add-2-rounded" class="text-3xl" />
        </div>
      </div>
    </div>
  </div>
</template>
