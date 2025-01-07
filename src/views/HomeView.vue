<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { ref, nextTick } from 'vue'
import { listStore } from '../stores/lists'
import { RouterLink } from 'vue-router'

const store = listStore()
const lists = ref(store.lists)
const hoveredList = ref<string | null>(null)

const newList = ref<HTMLInputElement | null>(null)

const handleAdd = () => {
  store.shown = !store.shown
  if (store.shown) {
    nextTick(() => {
      if (newList.value) {
        newList.value.focus()
      }
    })
  }
}
</script>

<template>
  <main>
    <h1>Listen</h1>
    <div class="flex flex-col gap-4">
      <div
        v-for="list in lists"
        :key="list.id"
        class="flex rounded-xl bg-main p-4 duration-300 hover:bg-primaryhover"
        @mouseover="hoveredList = list.id"
        @mouseleave="hoveredList = null"
      >
        <RouterLink :to="{ name: 'list', params: { id: list.id } }" class="flex w-full grow gap-4">
          <Icon icon="material-symbols:checklist-rounded" style="font-size: 2em" />
          <p class="font-semibold">
            {{ list.name }}
          </p>
        </RouterLink>
        <Icon
          v-show="hoveredList === list.id"
          icon="material-symbols:delete-forever-rounded"
          style="font-size: 2em"
          class="flex-none cursor-pointer text-info hover:text-text"
          @click="store.deleteList(list.id)"
        />
      </div>
      <div
        v-if="store.shown"
        class="flex gap-4 rounded-xl bg-primaryhover p-4 text-primary duration-300"
      >
        <Icon icon="material-symbols:checklist-rounded" style="font-size: 2em" />
        <input
          v-model="store.newList"
          placeholder="Liste benennen"
          class="cursor-pointer bg-transparent text-lg font-semibold outline-none placeholder:text-primary"
          v-on:keyup.enter="store.addNewList()"
          ref="newList"
        />
        <Icon
          icon="tdesign:enter"
          @click="store.addNewList()"
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
  </main>
</template>

<style scoped></style>
