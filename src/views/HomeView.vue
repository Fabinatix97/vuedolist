<script setup lang="ts">
import { Icon } from '@iconify/vue'
</script>

<template>
  <main>
    <h1>Listen</h1>
    <div class="flex flex-col gap-4">
      <div class="flex gap-4 rounded-xl bg-main p-4 duration-300 hover:bg-primaryhover">
        <Icon icon="material-symbols:checklist-rounded" style="font-size: 2em" />
        <p class="font-semibold">ToDo Liste 1</p>
      </div>
      <div class="flex gap-4 rounded-xl bg-main p-4 duration-300 hover:bg-primaryhover">
        <Icon icon="material-symbols:checklist-rounded" style="font-size: 2em" />
        <p class="font-semibold">ToDo Liste 2</p>
      </div>
      <div class="flex gap-4 rounded-xl bg-main p-4 duration-300 hover:bg-primaryhover">
        <Icon icon="material-symbols:checklist-rounded" style="font-size: 2em" />
        <p class="font-semibold">Einkaufsliste</p>
      </div>
      <div class="flex justify-end">
        <div
          class="flex gap-4 rounded-xl bg-primary p-4 text-white duration-300 hover:bg-primaryhover hover:text-text"
        >
          <Icon icon="material-symbols:add-2-rounded" style="font-size: 2em" />
        </div>
      </div>
    </div>
  </main>
</template>

<style scoped></style>
