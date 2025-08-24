<script setup lang="ts">
import { ref, onMounted, onBeforeUnmount } from 'vue'

defineProps<{
  items: Array<{ icon: string; label: string; action: () => void }>
}>()

// Emits click when an item is selected
const showMenu = ref(false)

function toggleMenu() {
  showMenu.value = !showMenu.value
}

// Close menu when clicking outside
let menuEl: HTMLElement | null = null
function handleClickOutside(event: MouseEvent) {
  if (menuEl && !menuEl.contains(event.target as Node)) {
    showMenu.value = false
  }
}

onMounted(() => {
  document.addEventListener('click', handleClickOutside)
})
onBeforeUnmount(() => {
  document.removeEventListener('click', handleClickOutside)
})
</script>

<template>
  <div class="relative inline-block" ref="el => (menuEl = el)">
    <!-- Trigger slot -->
    <div @click.stop="toggleMenu">
      <slot name="trigger">
        <!-- Default trigger if none provided -->
        <button aria-haspopup="true" :aria-expanded="showMenu">
          <slot name="default-trigger">☰</slot>
        </button>
      </slot>
    </div>

    <!-- Dropdown Panel -->
    <transition name="fade">
      <div
        v-if="showMenu"
        class="absolute right-0 z-50 mt-2 w-48 rounded-lg border bg-white shadow-lg"
        role="menu"
      >
        <ul class="divide-y">
          <li
            v-for="item in items"
            :key="item.label"
            class="flex cursor-pointer items-center px-4 py-2 hover:bg-gray-100"
            @click.stop.prevent="
              () => {
                item.action()
                showMenu = false
              }
            "
            role="menuitem"
          >
            <Icon :icon="item.icon" class="mr-2" />
            <span>{{ item.label }}</span>
          </li>
        </ul>
      </div>
    </transition>
  </div>
</template>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s;
}
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
