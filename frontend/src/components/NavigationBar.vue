<script setup lang="ts">
import { RouterLink } from 'vue-router'
import { Icon } from '@iconify/vue'
import { ref, watch } from 'vue'

const theme = ref(
  localStorage.getItem('theme') ||
    (window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light'),
)

function applyTheme() {
  document.documentElement.setAttribute('data-theme', theme.value)
}

function toggleTheme() {
  theme.value = theme.value === 'light' ? 'dark' : 'light'
  localStorage.setItem('theme', theme.value)
}

applyTheme()
watch(theme, applyTheme)
</script>

<template>
  <nav class="mt-8 flex w-full justify-center gap-4 text-center text-xs">
    <RouterLink
      :to="{ name: 'home' }"
      class="rounded-2xl bg-main p-4 text-text hover:bg-primaryhover"
      ><Icon icon="material-symbols:home" style="font-size: 2.5em"
    /></RouterLink>
    <RouterLink
      :to="{ name: 'settings' }"
      class="rounded-2xl bg-main p-4 text-text hover:bg-primaryhover"
      ><Icon icon="material-symbols:settings-rounded" style="font-size: 2.5em"
    /></RouterLink>
    <div class="rounded-2xl bg-main p-4 text-text hover:bg-primaryhover">
      <Icon
        v-if="theme === 'light'"
        icon="material-symbols:clear-night"
        @click="toggleTheme"
        style="font-size: 2.5em"
      />
      <Icon
        v-else
        icon="material-symbols:sunny-rounded"
        @click="toggleTheme"
        style="font-size: 2.5em"
      />
    </div>
  </nav>
</template>

<style scoped>
nav a.router-link-exact-active {
  color: var(--primary);
}
</style>
