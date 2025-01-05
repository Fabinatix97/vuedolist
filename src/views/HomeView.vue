<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { ref } from 'vue'

const lists = ref([
  { id: 40006, name: 'ToDo Liste 1' },
  { id: 10909, name: 'ToDo Liste 2' },
  { id: 61200, name: 'Einkaufsliste' },
])

const shown = ref(false)
const newList = ref('')
const hoveredList = ref<string | null>(null)

function getRandomArbitrary(min: number, max: number) {
  return Math.floor(Math.random() * (max - min) + min)
}

function addNewList() {
  lists.value.push({ id: getRandomArbitrary(10000, 99999), name: newList.value })
  newList.value = ''
  shown.value = false
}

function deleteList(listId: number) {
  const index = lists.value.findIndex((list) => list.id === listId)
  if (index || index === 0) {
    lists.value.splice(index, 1)
  } else {
    console.log('Index not found')
  }
}
</script>

<template>
  <main>
    <h1>Listen</h1>
    <div class="flex flex-col gap-4">
      <div
        v-for="list in lists"
        :key="list.name"
        class="flex rounded-xl bg-main p-4 duration-300 hover:bg-primaryhover"
        @mouseover="hoveredList = list.name"
        @mouseleave="hoveredList = null"
      >
        <div class="flex grow gap-4">
          <Icon icon="material-symbols:checklist-rounded" style="font-size: 2em" />
          <p class="font-semibold">
            {{ list.name }}
          </p>
        </div>
        <Icon
          v-show="hoveredList === list.name"
          icon="material-symbols:delete-forever-rounded"
          style="font-size: 2em"
          class="cursor-pointer text-info hover:text-text"
          @click="deleteList(list.id)"
        />
      </div>
      <div v-if="shown" class="flex gap-4 rounded-xl bg-primaryhover p-4 text-primary duration-300">
        <Icon icon="material-symbols:circle-outline" style="font-size: 2em" />
        <input
          v-model="newList"
          placeholder="Liste benennen"
          class="cursor-pointer bg-transparent font-semibold outline-none"
        />
        <Icon
          icon="tdesign:enter"
          @click="addNewList()"
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
  </main>
</template>

<style scoped></style>
