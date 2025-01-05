<script setup lang="ts">
import { Icon } from "@iconify/vue";
import ToggleSwitch from '@/components/ToggleSwitch.vue';
</script>

<template>
  <main>
    <h1>Einstellungen</h1>
    <h3>Allgemeines</h3>
    <div class="flex justify-between">
      <p>Dark-Mode</p>
      <Icon icon="material-symbols:switches" style="font-size: 2em;" />
      <ToggleSwitch />
    </div>
  </main>
</template>

<style scoped>

</style>
