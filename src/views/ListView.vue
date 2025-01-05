<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { ref, nextTick } from 'vue'
import { taskStore } from '../stores/tasks'

const store = taskStore()
const tasks = ref(store.tasks)
const hoveredTask = ref<string | null>(null)

const newTask = ref<HTMLInputElement | null>(null);

const handleAdd = () => {
  store.shown = !store.shown;
  if (store.shown) {
    nextTick(() => {
      if (newTask.value) {
        newTask.value.focus();
      }
    });
  }
};
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
          v-show="hoveredTask === task.name"
          icon="material-symbols:delete-forever-rounded"
          style="font-size: 2em"
          class="cursor-pointer text-info hover:text-text"
          @click="store.deleteTask(task.id)"
        />
      </div>
      <div v-if="store.shown" class="flex gap-4 rounded-xl bg-primaryhover p-4 text-primary duration-300">
        <Icon icon="material-symbols:circle-outline" style="font-size: 2em" />
        <input
          v-model="store.newTask"
          placeholder="Aufgabe eingeben"
          class="cursor-pointer bg-transparent font-semibold outline-none text-lg placeholder:text-primary"
          v-on:keyup.enter="store.addNewTask()"
          ref="newTask"
        />
        <Icon
          icon="tdesign:enter"
          @click="store.addNewTask()"
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

<style scoped>
</style>
