<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import { Icon } from "@iconify/vue";
import { ref, watch, computed } from 'vue';

const theme = ref(localStorage.getItem('theme') || (window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light'));

const themeIconName = computed(() => { return theme.value === 'light' ? 'material-symbols:nightlight' : 'material-symbols:sunny'; });

function applyTheme() {
  console.log(`Applying theme: ${theme.value}`);
  document.documentElement.setAttribute('data-theme', theme.value);
}

function toggleTheme() {
  theme.value = theme.value === 'light' ? 'dark' : 'light';
  localStorage.setItem('theme', theme.value);
}

applyTheme();
watch(theme, applyTheme);

</script>

<template>
  <div>
    <RouterView class="border-2 border-red-500" />
    <nav class="flex gap-4 border-2 border-primary">
      <RouterLink to="/"><Icon icon="material-symbols:home" style="font-size: 2.5em;" /></RouterLink>
      <RouterLink to="/about"><Icon icon="material-symbols:menu-rounded" style="font-size: 2.5em;" /></RouterLink>
      <RouterLink to="/settings"><Icon icon="material-symbols:settings-rounded" style="font-size: 2.5em;" /></RouterLink>
    </nav>
  </div>
  <div class="mt-10 justify-self-center">
    <Icon :icon="themeIconName" style="font-size: 2.5em;" @click="toggleTheme()" />
  </div>
</template>

<style scoped>
nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--primary);
}

nav a:hover {
  background-color: var(--primaryhover);
}

nav a {
  display: inline-block;
  padding: 1rem;
  border-radius: 1rem;
  color: var(--text);
  background-color: var(--main);
}

@media (min-width: 1024px) {
  nav {
    text-align: left;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}
</style>
