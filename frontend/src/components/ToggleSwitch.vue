<script setup lang="ts">
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
  <div class="toggle-container" @click="toggleTheme" role="switch" :aria-checked="theme === 'dark'">
    <div class="toggle-switch" :class="{ active: theme === 'dark' }">
      <div class="toggle-circle" :class="{ active: theme === 'dark' }"></div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.toggle-container {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.toggle-switch {
  width: 4rem;
  height: 2rem;
  background-color: var(--info);
  border-radius: 2rem;
  position: relative;
  margin: 0 0.5rem;

  &.active {
    background-color: var(--primary);

    .toggle-circle {
      transform: translateX(2rem);
    }
  }

  .toggle-circle {
    color: var(--main);
    width: 1.5rem;
    height: 1.5rem;
    background-color: var(--main);
    border-radius: 2rem;
    position: absolute;
    top: 0.25rem;
    left: 0.25rem;
    transition: transform 0.3s;

    &.active {
      background-color: var(--main);
    }
  }
}

span {
  font-size: 1rem;
  font-weight: bold;
  transition: color 0.3s;

  &.active-text {
    color: var(--heading);
  }
}
</style>
