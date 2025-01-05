<script setup lang="ts">
import { Icon } from "@iconify/vue";
</script>

<template>
  <main>
    <h1>Listen</h1>
    <div class="flex flex-col gap-4">
      <div class="flex gap-4 bg-main hover:bg-primaryhover duration-300 rounded-xl p-4">
        <Icon icon="material-symbols:checklist-rounded" style="font-size: 2em;" />
        <p class="font-semibold">ToDo Liste 1</p>
      </div>
      <div class="flex gap-4 bg-main hover:bg-primaryhover duration-300 rounded-xl p-4">
        <Icon icon="material-symbols:checklist-rounded" style="font-size: 2em;" />
        <p class="font-semibold">ToDo Liste 2</p>
      </div>
      <div class="flex gap-4 bg-main hover:bg-primaryhover duration-300 rounded-xl p-4">
        <Icon icon="material-symbols:checklist-rounded" style="font-size: 2em;" />
        <p class="font-semibold">Einkaufsliste</p>
      </div>
      <div class="flex justify-end">
        <div class="flex gap-4 bg-primary text-white hover:bg-primaryhover hover:text-text duration-300 rounded-xl p-4">
          <Icon icon="material-symbols:add-2-rounded" style="font-size: 2em;"/>
        </div>
      </div>
    </div>
  </main>
</template>

<style scoped>

</style>
