<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import { Icon } from '@iconify/vue'
import { ref } from 'vue'

const theme = ref(
  localStorage.getItem('theme') ||
    (window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light'),
)

function applyTheme() {
  document.documentElement.setAttribute('data-theme', theme.value)
}

applyTheme()
</script>

<template>
  <div>
    <RouterView />
    <nav class="mt-8 flex w-full justify-center gap-4 text-center text-xs">
      <RouterLink
        :to="{ name: 'home' }"
        class="rounded-2xl bg-main p-4 text-text hover:bg-primaryhover"
        ><Icon icon="material-symbols:home" style="font-size: 2.5em"
      /></RouterLink>
      <div class="rounded-2xl bg-main p-4 text-text hover:bg-primaryhover">
        <Icon icon="material-symbols:menu-rounded" style="font-size: 2.5em" />
      </div>
      <RouterLink
        :to="{ name: 'settings' }"
        class="rounded-2xl bg-main p-4 text-text hover:bg-primaryhover"
        ><Icon icon="material-symbols:settings-rounded" style="font-size: 2.5em"
      /></RouterLink>
    </nav>
  </div>
</template>

<style scoped>
nav a.router-link-exact-active {
  color: var(--primary);
}
</style>
