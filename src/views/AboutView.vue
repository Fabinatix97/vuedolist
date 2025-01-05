<script setup lang="ts">
import { Icon } from "@iconify/vue";
</script>

<template>
  <div class="about">
    <h1>ToDo Liste 1</h1>
    <div class="flex flex-col gap-4">
      <div class="flex gap-4 bg-main hover:bg-primaryhover duration-300 rounded-xl p-4">
        <Icon icon="material-symbols:check-circle-rounded" style="font-size: 2em;" />
        <p class="font-semibold line-through">Aufgabe 1</p>
      </div>
      <div class="flex gap-4 bg-main hover:bg-primaryhover duration-300 rounded-xl p-4">
        <Icon icon="material-symbols:circle-outline" style="font-size: 2em;" />
        <p class="font-semibold">Aufgabe 2</p>
      </div>
      <div class="flex gap-4 bg-main hover:bg-primaryhover duration-300 rounded-xl p-4">
        <Icon icon="material-symbols:circle-outline" style="font-size: 2em;" />
        <p class="font-semibold">Aufgabe 3</p>
      </div>
      <div class="flex justify-end">
        <div class="flex gap-4 bg-primary text-white hover:bg-primaryhover hover:text-text duration-300 rounded-xl p-4">
          <Icon icon="material-symbols:add-2-rounded" style="font-size: 2em;"/>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
